package optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CustomSky {
   private static CustomSkyLayer[][] worldSkyLayers = (CustomSkyLayer[][])((CustomSkyLayer[][])null);

   public static void reset() {
      worldSkyLayers = (CustomSkyLayer[][])((CustomSkyLayer[][])null);
   }

   public static void update() {
      reset();
      if(Config.isCustomSky()) {
         worldSkyLayers = readCustomSkies();
      }
   }

   private static CustomSkyLayer[][] readCustomSkies() {
      CustomSkyLayer[][] wsls = new CustomSkyLayer[10][0];
      String prefix = "mcpatcher/sky/world";
      int lastWorldId = -1;
      int worldCount = 0;

      while(worldCount < wsls.length) {
         String var17 = prefix + worldCount + "/sky";
         ArrayList var18 = new ArrayList();
         int sls = 1;

         while(true) {
            if(sls < 1000) {
               label64: {
                  String var19 = var17 + sls + ".properties";

                  try {
                     ResourceLocation var16 = new ResourceLocation(var19);
                     InputStream in = Config.getResourceStream(var16);
                     if(in == null) {
                        break label64;
                     }

                     Properties props = new Properties();
                     props.load(in);
                     in.close();
                     Config.dbg("CustomSky properties: " + var19);
                     String defSource = var17 + sls + ".png";
                     CustomSkyLayer sl = new CustomSkyLayer(props, defSource);
                     if(sl.isValid(var19)) {
                        ResourceLocation locSource = new ResourceLocation(sl.source);
                        ITextureObject tex = TextureUtils.getTexture(locSource);
                        if(tex == null) {
                           Config.log("CustomSky: Texture not found: " + locSource);
                        } else {
                           sl.textureId = tex.getGlTextureId();
                           var18.add(sl);
                           in.close();
                        }
                     }
                  } catch (FileNotFoundException var15) {
                     break label64;
                  } catch (IOException var161) {
                     var161.printStackTrace();
                  }

                  ++sls;
                  continue;
               }
            }

            if(var18.size() > 0) {
               CustomSkyLayer[] var191 = (CustomSkyLayer[])((CustomSkyLayer[])((CustomSkyLayer[])var18.toArray(new CustomSkyLayer[var18.size()])));
               wsls[worldCount] = var191;
               lastWorldId = worldCount;
            }

            ++worldCount;
            break;
         }
      }

      if(lastWorldId < 0) {
         return (CustomSkyLayer[][])((CustomSkyLayer[][])null);
      } else {
         worldCount = lastWorldId + 1;
         CustomSkyLayer[][] var171 = new CustomSkyLayer[worldCount][0];

         for(int var181 = 0; var181 < var171.length; ++var181) {
            var171[var181] = wsls[var181];
         }

         return var171;
      }
   }

   public static void renderSky(World world, TextureManager re, float celestialAngle, float rainBrightness) {
      if(worldSkyLayers != null && Config.getGameSettings().renderDistanceChunks >= 8) {
         int dimId = world.provider.dimensionId;
         if(dimId >= 0 && dimId < worldSkyLayers.length) {
            CustomSkyLayer[] sls = worldSkyLayers[dimId];
            if(sls != null) {
               long time = world.getWorldTime();
               int timeOfDay = (int)(time % 24000L);

               for(int i = 0; i < sls.length; ++i) {
                  CustomSkyLayer sl = sls[i];
                  if(sl.isActive(timeOfDay)) {
                     sl.render(timeOfDay, celestialAngle, rainBrightness);
                  }
               }

               Blender.clearBlend(rainBrightness);
            }
         }
      }
   }

   public static boolean hasSkyLayers(World world) {
      if(worldSkyLayers == null) {
         return false;
      } else if(Config.getGameSettings().renderDistanceChunks < 8) {
         return false;
      } else {
         int dimId = world.provider.dimensionId;
         if(dimId >= 0 && dimId < worldSkyLayers.length) {
            CustomSkyLayer[] sls = worldSkyLayers[dimId];
            return sls == null?false:sls.length > 0;
         } else {
            return false;
         }
      }
   }
}
