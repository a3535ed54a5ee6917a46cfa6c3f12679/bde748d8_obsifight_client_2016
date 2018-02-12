package optifine;

import java.util.List;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class WrUpdaterSmooth implements IWrUpdater {
   private long lastUpdateStartTimeNs = 0L;
   private long updateStartTimeNs = 0L;
   private long updateTimeNs = 10000000L;
   private WorldRendererSmooth currentUpdateRenderer = null;
   private int renderersUpdated = 0;
   private int renderersFound = 0;

   public void initialize() {}

   public void terminate() {}

   public WorldRenderer makeWorldRenderer(World worldObj, List tileEntities, int x, int y, int z, int glRenderListBase) {
      return new WorldRendererSmooth(worldObj, tileEntities, x, y, z, glRenderListBase);
   }

   public boolean updateRenderers(RenderGlobal rg, EntityLivingBase entityliving, boolean flag) {
      this.lastUpdateStartTimeNs = this.updateStartTimeNs;
      this.updateStartTimeNs = System.nanoTime();
      long finishTimeNs = this.updateStartTimeNs + this.updateTimeNs;
      int maxNum = Config.getUpdatesPerFrame();
      if(Config.isDynamicUpdates() && !rg.isMoving(entityliving)) {
         maxNum *= 3;
      }

      this.renderersUpdated = 0;

      do {
         this.renderersFound = 0;
         this.updateRenderersImpl(rg, entityliving, flag);
      } while(this.renderersFound > 0 && System.nanoTime() - finishTimeNs < 0L);

      if(this.renderersFound > 0) {
         maxNum = Math.min(maxNum, this.renderersFound);
         long diff = 400000L;
         if(this.renderersUpdated > maxNum) {
            this.updateTimeNs -= 2L * diff;
         }

         if(this.renderersUpdated < maxNum) {
            this.updateTimeNs += diff;
         }
      } else {
         this.updateTimeNs = 0L;
         this.updateTimeNs -= 200000L;
      }

      if(this.updateTimeNs < 0L) {
         this.updateTimeNs = 0L;
      }

      return this.renderersUpdated > 0;
   }

   private void updateRenderersImpl(RenderGlobal rg, EntityLivingBase entityliving, boolean flag) {
      this.renderersFound = 0;
      boolean currentUpdateFinished = true;
      if(this.currentUpdateRenderer != null) {
         ++this.renderersFound;
         currentUpdateFinished = this.updateRenderer(this.currentUpdateRenderer);
         if(currentUpdateFinished) {
            ++this.renderersUpdated;
         }
      }

      if(rg.worldRenderersToUpdate.size() > 0) {
         byte NOT_IN_FRUSTRUM_MUL = 4;
         WorldRendererSmooth wrBest = null;
         float distSqBest = Float.MAX_VALUE;
         int indexBest = -1;

         int dstIndex;
         float var17;
         for(dstIndex = 0; dstIndex < rg.worldRenderersToUpdate.size(); ++dstIndex) {
            WorldRendererSmooth var15 = (WorldRendererSmooth)rg.worldRenderersToUpdate.get(dstIndex);
            if(var15 != null) {
               ++this.renderersFound;
               if(!var15.needsUpdate) {
                  rg.worldRenderersToUpdate.set(dstIndex, (Object)null);
               } else {
                  var17 = var15.distanceToEntitySquared(entityliving);
                  if(var17 <= 256.0F && rg.isActingNow()) {
                     var15.updateRenderer();
                     var15.needsUpdate = false;
                     rg.worldRenderersToUpdate.set(dstIndex, (Object)null);
                     ++this.renderersUpdated;
                  } else {
                     if(!var15.isInFrustum) {
                        var17 *= (float)NOT_IN_FRUSTRUM_MUL;
                     }

                     if(wrBest == null) {
                        wrBest = var15;
                        distSqBest = var17;
                        indexBest = dstIndex;
                     } else if(var17 < distSqBest) {
                        wrBest = var15;
                        distSqBest = var17;
                        indexBest = dstIndex;
                     }
                  }
               }
            }
         }

         if(this.currentUpdateRenderer == null || currentUpdateFinished) {
            int var151;
            if(wrBest != null) {
               rg.worldRenderersToUpdate.set(indexBest, (Object)null);
               if(!this.updateRenderer(wrBest)) {
                  return;
               }

               ++this.renderersUpdated;
               if(System.nanoTime() > this.updateStartTimeNs + this.updateTimeNs) {
                  return;
               }

               var17 = distSqBest / 5.0F;

               for(var151 = 0; var151 < rg.worldRenderersToUpdate.size(); ++var151) {
                  WorldRendererSmooth var16 = (WorldRendererSmooth)rg.worldRenderersToUpdate.get(var151);
                  if(var16 != null) {
                     float distSq = var16.distanceToEntitySquared(entityliving);
                     if(!var16.isInFrustum) {
                        distSq *= (float)NOT_IN_FRUSTRUM_MUL;
                     }

                     float diffDistSq = Math.abs(distSq - distSqBest);
                     if(diffDistSq < var17) {
                        rg.worldRenderersToUpdate.set(var151, (Object)null);
                        if(!this.updateRenderer(var16)) {
                           return;
                        }

                        ++this.renderersUpdated;
                        if(System.nanoTime() > this.updateStartTimeNs + this.updateTimeNs) {
                           break;
                        }
                     }
                  }
               }
            }

            if(this.renderersFound == 0) {
               rg.worldRenderersToUpdate.clear();
            }

            if(rg.worldRenderersToUpdate.size() > 100 && this.renderersFound < rg.worldRenderersToUpdate.size() * 4 / 5) {
               dstIndex = 0;

               for(var151 = 0; var151 < rg.worldRenderersToUpdate.size(); ++var151) {
                  Object var161 = rg.worldRenderersToUpdate.get(var151);
                  if(var161 != null) {
                     if(var151 != dstIndex) {
                        rg.worldRenderersToUpdate.set(dstIndex, var161);
                     }

                     ++dstIndex;
                  }
               }

               for(var151 = rg.worldRenderersToUpdate.size() - 1; var151 >= dstIndex; --var151) {
                  rg.worldRenderersToUpdate.remove(var151);
               }
            }
         }
      }
   }

   private boolean updateRenderer(WorldRendererSmooth wr) {
      long finishTime = this.updateStartTimeNs + this.updateTimeNs;
      wr.needsUpdate = false;
      boolean ready = wr.updateRenderer(finishTime);
      if(!ready) {
         this.currentUpdateRenderer = wr;
         return false;
      } else {
         wr.finishUpdate();
         this.currentUpdateRenderer = null;
         return true;
      }
   }

   public void finishCurrentUpdate() {
      if(this.currentUpdateRenderer != null) {
         this.currentUpdateRenderer.updateRenderer();
         this.currentUpdateRenderer = null;
      }
   }

   public void resumeBackgroundUpdates() {}

   public void pauseBackgroundUpdates() {}

   public void preRender(RenderGlobal rg, EntityLivingBase player) {}

   public void postRender() {}

   public void clearAllUpdates() {
      this.finishCurrentUpdate();
   }
}
