package optifine;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ClearWater {
   public static void updateWaterOpacity(GameSettings settings, World world) {
      if(settings != null) {
         byte var23 = 3;
         if(settings.ofClearWater) {
            var23 = 1;
         }

         BlockUtils.setLightOpacity(Blocks.water, var23);
         BlockUtils.setLightOpacity(Blocks.flowing_water, var23);
      }

      if(world != null) {
         IChunkProvider var231 = world.getChunkProvider();
         if(var231 != null) {
            EntityLivingBase rve = Config.getMinecraft().renderViewEntity;
            if(rve != null) {
               int cViewX = (int)rve.posX / 16;
               int cViewZ = (int)rve.posZ / 16;
               int cXMin = cViewX - 512;
               int cXMax = cViewX + 512;
               int cZMin = cViewZ - 512;
               int cZMax = cViewZ + 512;
               int countUpdated = 0;

               for(int var24 = cXMin; var24 < cXMax; ++var24) {
                  for(int cz = cZMin; cz < cZMax; ++cz) {
                     if(var231.chunkExists(var24, cz)) {
                        Chunk c = var231.provideChunk(var24, cz);
                        if(c != null && !(c instanceof EmptyChunk)) {
                           int x0 = var24 << 4;
                           int z0 = cz << 4;
                           int x1 = x0 + 16;
                           int z1 = z0 + 16;

                           for(int x = x0; x < x1; ++x) {
                              int z = z0;

                              while(z < z1) {
                                 int posH = world.getPrecipitationHeight(x, z);
                                 int y = 0;

                                 while(true) {
                                    if(y < posH) {
                                       Block block = world.getBlock(x, y, z);
                                       if(block.getMaterial() != Material.water) {
                                          ++y;
                                          continue;
                                       }

                                       world.markBlocksDirtyVertical(x, z, y, posH);
                                       ++countUpdated;
                                    }

                                    ++z;
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if(countUpdated > 0) {
                  String var241 = "server";
                  if(Config.isMinecraftThread()) {
                     var241 = "client";
                  }

                  Config.dbg("ClearWater (" + var241 + ") relighted " + countUpdated + " chunks");
               }
            }
         }
      }
   }
}
