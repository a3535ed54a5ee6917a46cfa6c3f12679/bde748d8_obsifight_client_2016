package optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class RandomMobs {
   private static Map locationProperties = new HashMap();
   private static RenderGlobal renderGlobal = null;
   private static boolean initialized = false;
   private static Random random = new Random();
   private static boolean working = false;
   public static final String SUFFIX_PNG = ".png";
   public static final String SUFFIX_PROPERTIES = ".properties";
   public static final String PREFIX_TEXTURES_ENTITY = "textures/entity/";
   public static final String PREFIX_MCPATCHER_MOB = "mcpatcher/mob/";
   private static final String[] DEPENDANT_SUFFIXES = new String[]{"_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"};

   public static void entityLoaded(Entity entity, World world) {
      if(entity instanceof EntityLiving && world != null) {
         EntityLiving el = (EntityLiving)entity;
         el.spawnPosition = new BlockPos((int)el.posX, (int)el.posY, (int)el.posZ);
         el.spawnBiome = world.getBiomeGenForCoords((int)el.posX, (int)el.posZ);
         WorldServer ws = Config.getWorldServer();
         if(ws != null) {
            Entity es = ws.getEntityByID(entity.getEntityId());
            if(es instanceof EntityLiving) {
               EntityLiving els = (EntityLiving)es;
               UUID uuid = els.getUniqueID();
               long uuidLow = uuid.getLeastSignificantBits();
               int id = (int)(uuidLow & 2147483647L);
               el.randomMobsId = id;
            }
         }
      }
   }

   public static void worldChanged(World oldWorld, World newWorld) {
      if(newWorld != null) {
         List entityList = newWorld.getLoadedEntityList();

         for(int e = 0; e < entityList.size(); ++e) {
            Entity entity = (Entity)entityList.get(e);
            entityLoaded(entity, newWorld);
         }
      }
   }

   public static ResourceLocation getTextureLocation(ResourceLocation loc) {
      if(working) {
         return loc;
      } else {
         ResourceLocation name;
         try {
            working = true;
            if(!initialized) {
               initialize();
            }

            if(renderGlobal == null) {
               ResourceLocation entityLiving1 = loc;
               return entityLiving1;
            }

            Entity entity = renderGlobal.renderedEntity;
            if(entity instanceof EntityLiving) {
               EntityLiving entityLiving = (EntityLiving)entity;
               String name1 = loc.getResourcePath();
               ResourceLocation var6;
               if(!name1.startsWith("textures/entity/")) {
                  var6 = loc;
                  return var6;
               }

               RandomMobsProperties props = getProperties(loc);
               ResourceLocation var5;
               if(props != null) {
                  var5 = props.getTextureLocation(loc, entityLiving);
                  var6 = var5;
                  return var6;
               }

               var5 = loc;
               return var5;
            }

            name = loc;
         } finally {
            working = false;
         }

         return name;
      }
   }

   private static RandomMobsProperties getProperties(ResourceLocation loc) {
      String name = loc.getResourcePath();
      RandomMobsProperties props = (RandomMobsProperties)locationProperties.get(name);
      if(props == null) {
         props = makeProperties(loc);
         locationProperties.put(name, props);
      }

      return props;
   }

   private static RandomMobsProperties makeProperties(ResourceLocation loc) {
      String path = loc.getResourcePath();
      ResourceLocation propLoc = getPropertyLocation(loc);
      if(propLoc != null) {
         RandomMobsProperties variants1 = parseProperties(propLoc, loc);
         if(variants1 != null) {
            return variants1;
         }
      }

      ResourceLocation[] variants11 = getTextureVariants(loc);
      return new RandomMobsProperties(path, variants11);
   }

   private static RandomMobsProperties parseProperties(ResourceLocation propLoc, ResourceLocation resLoc) {
      try {
         String var7 = propLoc.getResourcePath();
         Config.dbg("RandomMobs: " + resLoc.getResourcePath() + ", variants: " + var7);
         InputStream in = Config.getResourceStream(propLoc);
         if(in == null) {
            Config.warn("RandomMobs properties not found: " + var7);
            return null;
         } else {
            Properties props = new Properties();
            props.load(in);
            in.close();
            RandomMobsProperties rmp = new RandomMobsProperties(props, var7, resLoc);
            return !rmp.isValid(var7)?null:rmp;
         }
      } catch (FileNotFoundException var6) {
         Config.warn("RandomMobs file not found: " + resLoc.getResourcePath());
         return null;
      } catch (IOException var71) {
         var71.printStackTrace();
         return null;
      }
   }

   private static ResourceLocation getPropertyLocation(ResourceLocation loc) {
      ResourceLocation locMcp = getMcpatcherLocation(loc);
      if(locMcp == null) {
         return null;
      } else {
         String domain = locMcp.getResourceDomain();
         String path = locMcp.getResourcePath();
         String pathBase = path;
         if(path.endsWith(".png")) {
            pathBase = path.substring(0, path.length() - ".png".length());
         }

         String pathProps = pathBase + ".properties";
         ResourceLocation locProps = new ResourceLocation(domain, pathProps);
         if(Config.hasResource(locProps)) {
            return locProps;
         } else {
            String pathParent = getParentPath(pathBase);
            if(pathParent == null) {
               return null;
            } else {
               ResourceLocation locParentProps = new ResourceLocation(domain, pathParent + ".properties");
               return Config.hasResource(locParentProps)?locParentProps:null;
            }
         }
      }
   }

   public static ResourceLocation getMcpatcherLocation(ResourceLocation loc) {
      String path = loc.getResourcePath();
      if(!path.startsWith("textures/entity/")) {
         return null;
      } else {
         String pathMcp = "mcpatcher/mob/" + path.substring("textures/entity/".length());
         return new ResourceLocation(loc.getResourceDomain(), pathMcp);
      }
   }

   public static ResourceLocation getLocationIndexed(ResourceLocation loc, int index) {
      if(loc == null) {
         return null;
      } else {
         String path = loc.getResourcePath();
         int pos = path.lastIndexOf(46);
         if(pos < 0) {
            return null;
         } else {
            String prefix = path.substring(0, pos);
            String suffix = path.substring(pos);
            String pathNew = prefix + index + suffix;
            ResourceLocation locNew = new ResourceLocation(loc.getResourceDomain(), pathNew);
            return locNew;
         }
      }
   }

   private static String getParentPath(String path) {
      for(int i = 0; i < DEPENDANT_SUFFIXES.length; ++i) {
         String suffix = DEPENDANT_SUFFIXES[i];
         if(path.endsWith(suffix)) {
            String pathParent = path.substring(0, path.length() - suffix.length());
            return pathParent;
         }
      }

      return null;
   }

   private static ResourceLocation[] getTextureVariants(ResourceLocation loc) {
      ArrayList list = new ArrayList();
      list.add(loc);
      ResourceLocation locMcp = getMcpatcherLocation(loc);
      if(locMcp == null) {
         return null;
      } else {
         for(int var6 = 1; var6 < list.size() + 10; ++var6) {
            int index = var6 + 1;
            ResourceLocation locIndex = getLocationIndexed(locMcp, index);
            if(Config.hasResource(locIndex)) {
               list.add(locIndex);
            }
         }

         if(list.size() <= 1) {
            return null;
         } else {
            ResourceLocation[] var61 = (ResourceLocation[])((ResourceLocation[])((ResourceLocation[])list.toArray(new ResourceLocation[list.size()])));
            Config.dbg("RandomMobs: " + loc.getResourcePath() + ", variants: " + var61.length);
            return var61;
         }
      }
   }

   public static void resetTextures() {
      locationProperties.clear();
      if(Config.isRandomMobs()) {
         initialize();
      }
   }

   private static void initialize() {
      renderGlobal = Config.getRenderGlobal();
      if(renderGlobal != null) {
         initialized = true;
         ArrayList list = new ArrayList();
         list.add("bat");
         list.add("blaze");
         list.add("cat/black");
         list.add("cat/ocelot");
         list.add("cat/red");
         list.add("cat/siamese");
         list.add("chicken");
         list.add("cow/cow");
         list.add("cow/mooshroom");
         list.add("creeper/creeper");
         list.add("enderman/enderman");
         list.add("enderman/enderman_eyes");
         list.add("ghast/ghast");
         list.add("ghast/ghast_shooting");
         list.add("iron_golem");
         list.add("pig/pig");
         list.add("sheep/sheep");
         list.add("sheep/sheep_fur");
         list.add("silverfish");
         list.add("skeleton/skeleton");
         list.add("skeleton/wither_skeleton");
         list.add("slime/slime");
         list.add("slime/magmacube");
         list.add("snowman");
         list.add("spider/cave_spider");
         list.add("spider/spider");
         list.add("spider_eyes");
         list.add("squid");
         list.add("villager/villager");
         list.add("villager/butcher");
         list.add("villager/farmer");
         list.add("villager/librarian");
         list.add("villager/priest");
         list.add("villager/smith");
         list.add("wither/wither");
         list.add("wither/wither_armor");
         list.add("wither/wither_invulnerable");
         list.add("wolf/wolf");
         list.add("wolf/wolf_angry");
         list.add("wolf/wolf_collar");
         list.add("wolf/wolf_tame");
         list.add("zombie_pigman");
         list.add("zombie/zombie");
         list.add("zombie/zombie_villager");

         for(int i = 0; i < list.size(); ++i) {
            String name = (String)list.get(i);
            String tex = "textures/entity/" + name + ".png";
            ResourceLocation texLoc = new ResourceLocation(tex);
            if(!Config.hasResource(texLoc)) {
               Config.warn("Not found: " + texLoc);
            }

            getProperties(texLoc);
         }
      }
   }
}