package optifine;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class PlayerItemParser {
   private static JsonParser jsonParser = new JsonParser();
   public static final String ITEM_TYPE = "type";
   public static final String ITEM_TEXTURE_SIZE = "textureSize";
   public static final String ITEM_USE_PLAYER_TEXTURE = "usePlayerTexture";
   public static final String ITEM_MODELS = "models";
   public static final String MODEL_ID = "id";
   public static final String MODEL_BASE_ID = "baseId";
   public static final String MODEL_TYPE = "type";
   public static final String MODEL_ATTACH_TO = "attachTo";
   public static final String MODEL_INVERT_AXIS = "invertAxis";
   public static final String MODEL_MIRROR_TEXTURE = "mirrorTexture";
   public static final String MODEL_TRANSLATE = "translate";
   public static final String MODEL_ROTATE = "rotate";
   public static final String MODEL_SCALE = "scale";
   public static final String MODEL_BOXES = "boxes";
   public static final String MODEL_SPRITES = "sprites";
   public static final String MODEL_SUBMODEL = "submodel";
   public static final String MODEL_SUBMODELS = "submodels";
   public static final String BOX_TEXTURE_OFFSET = "textureOffset";
   public static final String BOX_COORDINATES = "coordinates";
   public static final String BOX_SIZE_ADD = "sizeAdd";
   public static final String ITEM_TYPE_MODEL = "PlayerItem";
   public static final String MODEL_TYPE_BOX = "ModelBox";

   public static PlayerItemModel parseItemModel(JsonObject obj) {
      String type = Json.getString(obj, "type");
      if(!Config.equals(type, "PlayerItem")) {
         throw new JsonParseException("Unknown model type: " + type);
      } else {
         int[] textureSize = Json.parseIntArray(obj.get("textureSize"), 2);
         checkNull(textureSize, "Missing texture size");
         Dimension textureDim = new Dimension(textureSize[0], textureSize[1]);
         boolean usePlayerTexture = Json.getBoolean(obj, "usePlayerTexture", false);
         JsonArray models = (JsonArray)obj.get("models");
         checkNull(models, "Missing elements");
         HashMap mapModelJsons = new HashMap();
         ArrayList listModels = new ArrayList();
         new ArrayList();

         for(int var16 = 0; var16 < models.size(); ++var16) {
            JsonObject elem = (JsonObject)models.get(var16);
            String baseId = Json.getString(elem, "baseId");
            if(baseId != null) {
               JsonObject var17 = (JsonObject)mapModelJsons.get(baseId);
               if(var17 == null) {
                  Config.warn("BaseID not found: " + baseId);
                  continue;
               }

               Set var18 = var17.entrySet();
               Iterator iterator = var18.iterator();

               while(iterator.hasNext()) {
                  Entry entry = (Entry)iterator.next();
                  if(!elem.has((String)entry.getKey())) {
                     elem.add((String)entry.getKey(), (JsonElement)entry.getValue());
                  }
               }
            }

            String var161 = Json.getString(elem, "id");
            if(var161 != null) {
               if(!mapModelJsons.containsKey(var161)) {
                  mapModelJsons.put(var161, elem);
               } else {
                  Config.warn("Duplicate model ID: " + var161);
               }
            }

            PlayerItemRenderer var171 = parseItemRenderer(elem, textureDim);
            if(var171 != null) {
               listModels.add(var171);
            }
         }

         PlayerItemRenderer[] var15 = (PlayerItemRenderer[])((PlayerItemRenderer[])((PlayerItemRenderer[])listModels.toArray(new PlayerItemRenderer[listModels.size()])));
         return new PlayerItemModel(textureDim, usePlayerTexture, var15);
      }
   }

   private static void checkNull(Object obj, String msg) {
      if(obj == null) {
         throw new JsonParseException(msg);
      }
   }

   private static ResourceLocation makeResourceLocation(String texture) {
      int pos = texture.indexOf(58);
      if(pos < 0) {
         return new ResourceLocation(texture);
      } else {
         String domain = texture.substring(0, pos);
         String path = texture.substring(pos + 1);
         return new ResourceLocation(domain, path);
      }
   }

   private static int parseAttachModel(String attachModelStr) {
      if(attachModelStr == null) {
         return 0;
      } else if(attachModelStr.equals("body")) {
         return 0;
      } else if(attachModelStr.equals("head")) {
         return 1;
      } else if(attachModelStr.equals("leftArm")) {
         return 2;
      } else if(attachModelStr.equals("rightArm")) {
         return 3;
      } else if(attachModelStr.equals("leftLeg")) {
         return 4;
      } else if(attachModelStr.equals("rightLeg")) {
         return 5;
      } else if(attachModelStr.equals("cape")) {
         return 6;
      } else {
         Config.warn("Unknown attachModel: " + attachModelStr);
         return 0;
      }
   }

   private static PlayerItemRenderer parseItemRenderer(JsonObject elem, Dimension textureDim) {
      String type = Json.getString(elem, "type");
      if(!Config.equals(type, "ModelBox")) {
         Config.warn("Unknown model type: " + type);
         return null;
      } else {
         String attachToStr = Json.getString(elem, "attachTo");
         int attachTo = parseAttachModel(attachToStr);
         float scale = Json.getFloat(elem, "scale", 1.0F);
         ModelPlayerItem modelBase = new ModelPlayerItem();
         modelBase.textureWidth = textureDim.width;
         modelBase.textureHeight = textureDim.height;
         ModelRenderer mr = parseModelRenderer(elem, modelBase);
         PlayerItemRenderer pir = new PlayerItemRenderer(attachTo, scale, mr);
         return pir;
      }
   }

   private static ModelRenderer parseModelRenderer(JsonObject elem, ModelBase modelBase) {
      ModelRenderer mr = new ModelRenderer(modelBase);
      String invertAxis = Json.getString(elem, "invertAxis", "").toLowerCase();
      boolean invertX = invertAxis.contains("x");
      boolean invertY = invertAxis.contains("y");
      boolean invertZ = invertAxis.contains("z");
      float[] translate = Json.parseFloatArray(elem.get("translate"), 3, new float[3]);
      if(invertX) {
         translate[0] = -translate[0];
      }

      if(invertY) {
         translate[1] = -translate[1];
      }

      if(invertZ) {
         translate[2] = -translate[2];
      }

      float[] rotateAngles = Json.parseFloatArray(elem.get("rotate"), 3, new float[3]);

      for(int var19 = 0; var19 < rotateAngles.length; ++var19) {
         rotateAngles[var19] = rotateAngles[var19] / 180.0F * (float)Math.PI;
      }

      if(invertX) {
         rotateAngles[0] = -rotateAngles[0];
      }

      if(invertY) {
         rotateAngles[1] = -rotateAngles[1];
      }

      if(invertZ) {
         rotateAngles[2] = -rotateAngles[2];
      }

      mr.setRotationPoint(translate[0], translate[1], translate[2]);
      mr.rotateAngleX = rotateAngles[0];
      mr.rotateAngleY = rotateAngles[1];
      mr.rotateAngleZ = rotateAngles[2];
      String var201 = Json.getString(elem, "mirrorTexture", "").toLowerCase();
      boolean invertU = var201.contains("u");
      boolean invertV = var201.contains("v");
      if(invertU) {
         mr.mirror = true;
      }

      if(invertV) {
         mr.mirrorV = true;
      }

      JsonArray boxes = elem.getAsJsonArray("boxes");
      JsonObject submodel;
      if(boxes != null) {
         for(int var20 = 0; var20 < boxes.size(); ++var20) {
            submodel = boxes.get(var20).getAsJsonObject();
            int[] var24 = Json.parseIntArray(submodel.get("textureOffset"), 2);
            if(var24 == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] var26 = Json.parseFloatArray(submodel.get("coordinates"), 6);
            if(var26 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if(invertX) {
               var26[0] = -var26[0] - var26[3];
            }

            if(invertY) {
               var26[1] = -var26[1] - var26[4];
            }

            if(invertZ) {
               var26[2] = -var26[2] - var26[5];
            }

            float var28 = Json.getFloat(submodel, "sizeAdd", 0.0F);
            mr.setTextureOffset(var24[0], var24[1]);
            mr.addBox(var26[0], var26[1], var26[2], (int)var26[3], (int)var26[4], (int)var26[5], var28);
         }
      }

      JsonArray var21 = elem.getAsJsonArray("sprites");
      if(var21 != null) {
         for(int var22 = 0; var22 < var21.size(); ++var22) {
            JsonObject var25 = var21.get(var22).getAsJsonObject();
            int[] var27 = Json.parseIntArray(var25.get("textureOffset"), 2);
            if(var27 == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] var29 = Json.parseFloatArray(var25.get("coordinates"), 6);
            if(var29 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if(invertX) {
               var29[0] = -var29[0] - var29[3];
            }

            if(invertY) {
               var29[1] = -var29[1] - var29[4];
            }

            if(invertZ) {
               var29[2] = -var29[2] - var29[5];
            }

            float subMr = Json.getFloat(var25, "sizeAdd", 0.0F);
            mr.setTextureOffset(var27[0], var27[1]);
            mr.addSprite(var29[0], var29[1], var29[2], (int)var29[3], (int)var29[4], (int)var29[5], subMr);
         }
      }

      submodel = (JsonObject)elem.get("submodel");
      if(submodel != null) {
         ModelRenderer var23 = parseModelRenderer(submodel, modelBase);
         mr.addChild(var23);
      }

      JsonArray var241 = (JsonArray)elem.get("submodels");
      if(var241 != null) {
         for(int var261 = 0; var261 < var241.size(); ++var261) {
            JsonObject var281 = (JsonObject)var241.get(var261);
            ModelRenderer var291 = parseModelRenderer(var281, modelBase);
            mr.addChild(var291);
         }
      }

      return mr;
   }
}
