package net.minecraft.client.renderer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.shader.TesselatorVertexState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import optifine.Config;
import optifine.Reflector;
import org.lwjgl.opengl.GL11;

public class WorldRenderer {
   protected TesselatorVertexState vertexState;
   public World worldObj;
   protected int glRenderList;
   protected Tessellator tessellator;
   public static volatile int chunksUpdated;
   public int posX;
   public int posY;
   public int posZ;
   public int posXMinus;
   public int posYMinus;
   public int posZMinus;
   public int posXClip;
   public int posYClip;
   public int posZClip;
   public boolean isInFrustum;
   public boolean[] skipRenderPass;
   public int posXPlus;
   public int posYPlus;
   public int posZPlus;
   public volatile boolean needsUpdate;
   public AxisAlignedBB rendererBoundingBox;
   public int chunkIndex;
   public boolean isVisible;
   public boolean isWaitingOnOcclusionQuery;
   public int glOcclusionQuery;
   public boolean isChunkLit;
   protected boolean isInitialized;
   public List tileEntityRenderers;
   protected List tileEntities;
   protected int bytesDrawn;
   public boolean isVisibleFromPosition;
   public double visibleFromX;
   public double visibleFromY;
   public double visibleFromZ;
   public boolean isInFrustrumFully;
   protected boolean needsBoxUpdate;
   public volatile boolean isUpdating;
   public float sortDistanceToEntitySquared;
   public double distanceToEntityXzSq;
   protected boolean skipAllRenderPasses;
   public boolean inSortedWorldRenderers;
   public boolean needVertexStateResort;
   public RenderGlobal renderGlobal;
   public static int globalChunkOffsetX = 0;
   public static int globalChunkOffsetZ = 0;
   private static final String __OBFID = "CL_00000942";
   private static final String __OBFID = "CL_00000942";

   public WorldRenderer(World par1World, List par2List, int par3, int par4, int par5, int par6) {
      this.tessellator = Tessellator.instance;
      this.skipRenderPass = new boolean[]{true, true};
      this.tileEntityRenderers = new ArrayList();
      this.isVisibleFromPosition = false;
      this.isInFrustrumFully = false;
      this.needsBoxUpdate = false;
      this.isUpdating = false;
      this.skipAllRenderPasses = true;
      this.renderGlobal = Minecraft.getMinecraft().renderGlobal;
      this.glRenderList = -1;
      this.isInFrustum = false;
      this.isVisible = true;
      this.isInitialized = false;
      this.worldObj = par1World;
      this.vertexState = null;
      this.tileEntities = par2List;
      this.glRenderList = par6;
      this.posX = -999;
      this.setPosition(par3, par4, par5);
      this.needsUpdate = false;
   }

   public void setPosition(int par1, int par2, int par3) {
      if(par1 != this.posX || par2 != this.posY || par3 != this.posZ) {
         this.setDontDraw();
         this.posX = par1;
         this.posY = par2;
         this.posZ = par3;
         this.posXPlus = par1 + 8;
         this.posYPlus = par2 + 8;
         this.posZPlus = par3 + 8;
         this.posXClip = par1 & 1023;
         this.posYClip = par2;
         this.posZClip = par3 & 1023;
         this.posXMinus = par1 - this.posXClip;
         this.posYMinus = par2 - this.posYClip;
         this.posZMinus = par3 - this.posZClip;
         this.rendererBoundingBox = AxisAlignedBB.getBoundingBox((double)par1, (double)par2, (double)par3, (double)(par1 + 16), (double)(par2 + 16), (double)(par3 + 16));
         this.needsBoxUpdate = true;
         this.markDirty();
         this.isVisibleFromPosition = false;
      }
   }

   protected void setupGLTranslation() {
      GL11.glTranslatef((float)this.posXClip, (float)this.posYClip, (float)this.posZClip);
   }

   public void updateRenderer(EntityLivingBase p_147892_1_) {
      if(this.worldObj != null) {
         if(this.needsBoxUpdate) {
            GL11.glNewList(this.glRenderList + 2, GL11.GL_COMPILE);
            RenderItem.renderAABB(AxisAlignedBB.getBoundingBox((double)this.posXClip, (double)this.posYClip, (double)this.posZClip, (double)(this.posXClip + 16), (double)(this.posYClip + 16), (double)(this.posZClip + 16)));
            GL11.glEndList();
            this.needsBoxUpdate = false;
         }

         this.isVisible = true;
         this.isVisibleFromPosition = false;
         if(this.needsUpdate) {
            this.needsUpdate = false;
            int xMin = this.posX;
            int yMin = this.posY;
            int zMain = this.posZ;
            int xMax = this.posX + 16;
            int yMax = this.posY + 16;
            int zMax = this.posZ + 16;

            for(int var30 = 0; var30 < 2; ++var30) {
               this.skipRenderPass[var30] = true;
            }

            this.skipAllRenderPasses = true;
            if(Reflector.LightCache.exists()) {
               Object var29 = Reflector.getFieldValue(Reflector.LightCache_cache);
               Reflector.callVoid(var29, Reflector.LightCache_clear, new Object[0]);
               Reflector.callVoid(Reflector.BlockCoord_resetPool, new Object[0]);
            }

            Chunk.isLit = false;
            HashSet var301 = new HashSet();
            var301.addAll(this.tileEntityRenderers);
            this.tileEntityRenderers.clear();
            Minecraft var9 = Minecraft.getMinecraft();
            EntityLivingBase var10 = var9.renderViewEntity;
            int viewEntityPosX = MathHelper.floor_double(var10.posX);
            int viewEntityPosY = MathHelper.floor_double(var10.posY);
            int viewEntityPosZ = MathHelper.floor_double(var10.posZ);
            byte var14 = 1;
            ChunkCache chunkcache = new ChunkCache(this.worldObj, xMin - var14, yMin - var14, zMain - var14, xMax + var14, yMax + var14, zMax + var14, var14);
            if(!chunkcache.extendedLevelsInChunkCache()) {
               ++chunksUpdated;
               RenderBlocks var31 = new RenderBlocks(chunkcache);
               Reflector.callVoid(Reflector.ForgeHooksClient_setWorldRendererRB, new Object[]{var31});
               this.bytesDrawn = 0;
               this.vertexState = null;
               this.tessellator = Tessellator.instance;
               boolean hasForge = Reflector.ForgeHooksClient.exists();

               for(int renderPass = 0; renderPass < 2; ++renderPass) {
                  boolean renderNextPass = false;
                  boolean hasRenderedBlocks = false;
                  boolean hasGlList = false;

                  for(int y = yMin; y < yMax; ++y) {
                     for(int z = zMain; z < zMax; ++z) {
                        for(int x = xMin; x < xMax; ++x) {
                           Block block = chunkcache.getBlock(x, y, z);
                           if(block.getMaterial() != Material.air) {
                              if(!hasGlList) {
                                 hasGlList = true;
                                 this.preRenderBlocks(renderPass);
                              }

                              boolean hasTileEntity = false;
                              if(hasForge) {
                                 hasTileEntity = Reflector.callBoolean(block, Reflector.ForgeBlock_hasTileEntity, new Object[]{Integer.valueOf(chunkcache.getBlockMetadata(x, y, z))});
                              } else {
                                 hasTileEntity = block.hasTileEntity();
                              }

                              if(renderPass == 0 && hasTileEntity) {
                                 TileEntity var32 = chunkcache.getTileEntity(x, y, z);
                                 if(TileEntityRendererDispatcher.instance.hasSpecialRenderer(var32)) {
                                    this.tileEntityRenderers.add(var32);
                                 }
                              }

                              int var321 = block.getRenderBlockPass();
                              if(var321 > renderPass) {
                                 renderNextPass = true;
                              }

                              boolean canRender = var321 == renderPass;
                              if(Reflector.ForgeBlock_canRenderInPass.exists()) {
                                 canRender = Reflector.callBoolean(block, Reflector.ForgeBlock_canRenderInPass, new Object[]{Integer.valueOf(renderPass)});
                              }

                              if(canRender) {
                                 hasRenderedBlocks |= var31.renderBlockByRenderType(block, x, y, z);
                                 if(block.getRenderType() == 0 && x == viewEntityPosX && y == viewEntityPosY && z == viewEntityPosZ) {
                                    var31.setRenderFromInside(true);
                                    var31.setRenderAllFaces(true);
                                    var31.renderBlockByRenderType(block, x, y, z);
                                    var31.setRenderFromInside(false);
                                    var31.setRenderAllFaces(false);
                                 }
                              }
                           }
                        }
                     }
                  }

                  if(hasRenderedBlocks) {
                     this.skipRenderPass[renderPass] = false;
                  }

                  if(hasGlList) {
                     this.postRenderBlocks(renderPass, p_147892_1_);
                  } else {
                     hasRenderedBlocks = false;
                  }

                  if(!renderNextPass) {
                     break;
                  }
               }

               Reflector.callVoid(Reflector.ForgeHooksClient_setWorldRendererRB, new Object[]{(RenderBlocks)null});
            }

            HashSet var311 = new HashSet();
            var311.addAll(this.tileEntityRenderers);
            var311.removeAll(var301);
            this.tileEntities.addAll(var311);
            var301.removeAll(this.tileEntityRenderers);
            this.tileEntities.removeAll(var301);
            this.isChunkLit = Chunk.isLit;
            this.isInitialized = true;
            this.skipAllRenderPasses = this.skipRenderPass[0] && this.skipRenderPass[1];
            this.updateFinished();
         }
      }
   }

   protected void preRenderBlocks(int renderpass) {
      GL11.glNewList(this.glRenderList + renderpass, GL11.GL_COMPILE);
      this.tessellator.setRenderingChunk(true);
      if(Config.isFastRender()) {
         Reflector.callVoid(Reflector.ForgeHooksClient_onPreRenderWorld, new Object[]{this, Integer.valueOf(renderpass)});
         this.tessellator.startDrawingQuads();
         this.tessellator.setTranslation((double)(-globalChunkOffsetX), 0.0D, (double)(-globalChunkOffsetZ));
      } else {
         GL11.glPushMatrix();
         this.setupGLTranslation();
         float var2 = 1.000001F;
         GL11.glTranslatef(-8.0F, -8.0F, -8.0F);
         GL11.glScalef(var2, var2, var2);
         GL11.glTranslatef(8.0F, 8.0F, 8.0F);
         Reflector.callVoid(Reflector.ForgeHooksClient_onPreRenderWorld, new Object[]{this, Integer.valueOf(renderpass)});
         this.tessellator.startDrawingQuads();
         this.tessellator.setTranslation((double)(-this.posX), (double)(-this.posY), (double)(-this.posZ));
      }
   }

   protected void postRenderBlocks(int renderpass, EntityLivingBase entityLiving) {
      if(Config.isTranslucentBlocksFancy() && renderpass == 1 && !this.skipRenderPass[renderpass]) {
         this.vertexState = this.tessellator.getVertexState((float)entityLiving.posX, (float)entityLiving.posY, (float)entityLiving.posZ);
      }

      this.bytesDrawn += this.tessellator.draw();
      Reflector.callVoid(Reflector.ForgeHooksClient_onPostRenderWorld, new Object[]{this, Integer.valueOf(renderpass)});
      this.tessellator.setRenderingChunk(false);
      if(!Config.isFastRender()) {
         GL11.glPopMatrix();
      }

      GL11.glEndList();
      this.tessellator.setTranslation(0.0D, 0.0D, 0.0D);
   }

   public void updateRendererSort(EntityLivingBase p_147889_1_) {
      if(this.vertexState != null && !this.skipRenderPass[1]) {
         this.tessellator = Tessellator.instance;
         this.preRenderBlocks(1);
         this.tessellator.setVertexState(this.vertexState);
         this.postRenderBlocks(1, p_147889_1_);
      }
   }

   public float distanceToEntitySquared(Entity par1Entity) {
      float var2 = (float)(par1Entity.posX - (double)this.posXPlus);
      float var3 = (float)(par1Entity.posY - (double)this.posYPlus);
      float var4 = (float)(par1Entity.posZ - (double)this.posZPlus);
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public void setDontDraw() {
      for(int var1 = 0; var1 < 2; ++var1) {
         this.skipRenderPass[var1] = true;
      }

      this.skipAllRenderPasses = true;
      this.isInFrustum = false;
      this.isInitialized = false;
      this.vertexState = null;
   }

   public void stopRendering() {
      this.setDontDraw();
      this.worldObj = null;
   }

   public int getGLCallListForPass(int par1) {
      return this.glRenderList + par1;
   }

   public void updateInFrustum(ICamera par1ICamera) {
      this.isInFrustum = par1ICamera.isBoundingBoxInFrustum(this.rendererBoundingBox);
      if(this.isInFrustum && Config.isOcclusionFancy()) {
         this.isInFrustrumFully = par1ICamera.isBoundingBoxInFrustumFully(this.rendererBoundingBox);
      } else {
         this.isInFrustrumFully = false;
      }
   }

   public void callOcclusionQueryList() {
      GL11.glCallList(this.glRenderList + 2);
   }

   public boolean skipAllRenderPasses() {
      return this.skipAllRenderPasses;
   }

   public void markDirty() {
      this.needsUpdate = true;
   }

   protected void updateFinished() {
      if(!this.skipAllRenderPasses && !this.inSortedWorldRenderers) {
         Minecraft.getMinecraft().renderGlobal.addToSortedWorldRenderers(this);
      }
   }

   public void updateDistanceToEntitySquared(Entity entity) {
      double dx = entity.posX - (double)this.posXPlus;
      double dy = entity.posY - (double)this.posYPlus;
      double dz = entity.posZ - (double)this.posZPlus;
      double dXzSq = dx * dx + dz * dz;
      this.distanceToEntityXzSq = dXzSq;
      this.sortDistanceToEntitySquared = (float)(dXzSq + dy * dy);
   }
}
