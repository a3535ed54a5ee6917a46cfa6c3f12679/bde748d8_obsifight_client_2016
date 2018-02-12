package optifine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class CustomItems {
   private static CustomItemProperties[][] itemProperties = (CustomItemProperties[][])((CustomItemProperties[][])null);
   private static Map mapPotionIds = null;

   public static void updateIcons(TextureMap textureMap) {
      itemProperties = (CustomItemProperties[][])((CustomItemProperties[][])null);
      if(Config.isCustomItems()) {
         IResourcePack[] rps = Config.getResourcePacks();

         for(int i = rps.length - 1; i >= 0; --i) {
            IResourcePack rp = rps[i];
            updateIcons(textureMap, rp);
         }

         updateIcons(textureMap, Config.getDefaultResourcePack());
      }
   }

   public static void updateIcons(TextureMap textureMap, IResourcePack rp) {
      String[] names = collectFiles(rp, "mcpatcher/cit/", ".properties");
      Map mapAutoProperties = makeAutoImageProperties(rp);
      if(mapAutoProperties.size() > 0) {
         Set var13 = mapAutoProperties.keySet();
         String[] var14 = (String[])((String[])((String[])var13.toArray(new String[var13.size()])));
         names = (String[])((String[])((String[])Config.addObjectsToArray(names, var14)));
      }

      Arrays.sort(names);
      List var131 = makePropertyList(itemProperties);

      for(int var141 = 0; var141 < names.length; ++var141) {
         String name = names[var141];
         Config.dbg("CustomItems: " + name);

         try {
            CustomItemProperties var12 = null;
            if(mapAutoProperties.containsKey(name)) {
               var12 = (CustomItemProperties)mapAutoProperties.get(name);
            }

            if(var12 == null) {
               ResourceLocation locFile = new ResourceLocation(name);
               InputStream in = rp.getInputStream(locFile);
               if(in == null) {
                  Config.warn("CustomItems file not found: " + name);
                  continue;
               }

               Properties props = new Properties();
               props.load(in);
               var12 = new CustomItemProperties(props, name);
            }

            if(var12.isValid(name)) {
               var12.updateIcons(textureMap);
               addToItemList(var12, var131);
            }
         } catch (FileNotFoundException var11) {
            Config.warn("CustomItems file not found: " + name);
         } catch (IOException var121) {
            var121.printStackTrace();
         }
      }

      itemProperties = propertyListToArray(var131);
   }

   private static Map makeAutoImageProperties(IResourcePack rp) {
      HashMap map = new HashMap();
      map.putAll(makePotionImageProperties(rp, false));
      map.putAll(makePotionImageProperties(rp, true));
      return map;
   }

   private static Map makePotionImageProperties(IResourcePack rp, boolean splash) {
      HashMap map = new HashMap();
      String prefix = "mcpatcher/cit/potion/";
      if(splash) {
         prefix = prefix + "splash/";
      } else {
         prefix = prefix + "normal/";
      }

      String suffix = ".png";
      String[] names = collectFiles(rp, prefix, suffix);

      for(int i = 0; i < names.length; ++i) {
         String path = names[i];
         if(path.startsWith(prefix) && path.endsWith(suffix)) {
            String name = path.substring(prefix.length(), path.length() - suffix.length());
            Properties props = makePotionProperties(name, splash, path);
            if(props != null) {
               String pathProp = path.substring(0, path.length() - suffix.length()) + ".properties";
               CustomItemProperties cip = new CustomItemProperties(props, pathProp);
               map.put(pathProp, cip);
            }
         } else {
            Config.warn("Invalid potion name: " + path);
         }
      }

      return map;
   }

   private static Properties makePotionProperties(String name, boolean splash, String path) {
      int potionItemId;
      if(name.equals("empty") && !splash) {
         potionItemId = Item.getIdFromItem(Items.glass_bottle);
         Properties var8 = new Properties();
         var8.put("type", "item");
         var8.put("items", "" + potionItemId);
         return var8;
      } else {
         potionItemId = Item.getIdFromItem(Items.potionitem);
         int[] damages = (int[])((int[])((int[])getMapPotionIds().get(name)));
         if(damages == null) {
            Config.warn("Potion not found for image: " + path);
            return null;
         } else {
            StringBuffer bufDamage = new StringBuffer();

            for(int var9 = 0; var9 < damages.length; ++var9) {
               int var10 = damages[var9];
               if(splash) {
                  var10 |= 16384;
               }

               if(var9 > 0) {
                  bufDamage.append(" ");
               }

               bufDamage.append(var10);
            }

            short var91 = 16447;
            Properties var101 = new Properties();
            var101.put("type", "item");
            var101.put("items", "" + potionItemId);
            var101.put("damage", "" + bufDamage.toString());
            var101.put("damageMask", "" + var91);
            return var101;
         }
      }
   }

   private static Map getMapPotionIds() {
      if(mapPotionIds == null) {
         mapPotionIds = new LinkedHashMap();
         mapPotionIds.put("water", new int[]{0});
         mapPotionIds.put("awkward", new int[]{16});
         mapPotionIds.put("thick", new int[]{32});
         mapPotionIds.put("potent", new int[]{48});
         mapPotionIds.put("regeneration", getPotionIds(1));
         mapPotionIds.put("moveSpeed", getPotionIds(2));
         mapPotionIds.put("fireResistance", getPotionIds(3));
         mapPotionIds.put("poison", getPotionIds(4));
         mapPotionIds.put("heal", getPotionIds(5));
         mapPotionIds.put("nightVision", getPotionIds(6));
         mapPotionIds.put("clear", getPotionIds(7));
         mapPotionIds.put("bungling", getPotionIds(23));
         mapPotionIds.put("charming", getPotionIds(39));
         mapPotionIds.put("rank", getPotionIds(55));
         mapPotionIds.put("weakness", getPotionIds(8));
         mapPotionIds.put("damageBoost", getPotionIds(9));
         mapPotionIds.put("moveSlowdown", getPotionIds(10));
         mapPotionIds.put("diffuse", getPotionIds(11));
         mapPotionIds.put("smooth", getPotionIds(27));
         mapPotionIds.put("refined", getPotionIds(43));
         mapPotionIds.put("acrid", getPotionIds(59));
         mapPotionIds.put("harm", getPotionIds(12));
         mapPotionIds.put("waterBreathing", getPotionIds(13));
         mapPotionIds.put("invisibility", getPotionIds(14));
         mapPotionIds.put("thin", getPotionIds(15));
         mapPotionIds.put("debonair", getPotionIds(31));
         mapPotionIds.put("sparkling", getPotionIds(47));
         mapPotionIds.put("stinky", getPotionIds(63));
      }

      return mapPotionIds;
   }

   private static int[] getPotionIds(int baseId) {
      return new int[]{baseId, baseId + 16, baseId + 32, baseId + 48};
   }

   private static int getPotionNameDamage(String name) {
      String fullName = "potion." + name;
      Potion[] effectPotions = Potion.potionTypes;

      for(int i = 0; i < effectPotions.length; ++i) {
         Potion potion = effectPotions[i];
         if(potion != null) {
            String potionName = potion.getName();
            if(fullName.equals(potionName)) {
               return potion.getId();
            }
         }
      }

      return -1;
   }

   private static List makePropertyList(CustomItemProperties[][] propsArr) {
      ArrayList list = new ArrayList();
      if(propsArr != null) {
         for(int i = 0; i < propsArr.length; ++i) {
            CustomItemProperties[] props = propsArr[i];
            ArrayList propList = null;
            if(props != null) {
               propList = new ArrayList(Arrays.asList(props));
            }

            list.add(propList);
         }
      }

      return list;
   }

   private static String[] collectFiles(IResourcePack rp, String prefix, String suffix) {
      if(rp instanceof DefaultResourcePack) {
         return collectFilesDefault(rp);
      } else if(!(rp instanceof AbstractResourcePack)) {
         return new String[0];
      } else {
         AbstractResourcePack arp = (AbstractResourcePack)rp;
         File tpFile = ResourceUtils.getResourcePackFile(arp);
         return tpFile == null?new String[0]:(tpFile.isDirectory()?collectFilesFolder(tpFile, "", prefix, suffix):(tpFile.isFile()?collectFilesZIP(tpFile, prefix, suffix):new String[0]));
      }
   }

   private static String[] collectFilesDefault(IResourcePack rp) {
      return new String[0];
   }

   private static String[] collectFilesFolder(File tpFile, String basePath, String prefix, String suffix) {
      ArrayList list = new ArrayList();
      String prefixAssets = "assets/minecraft/";
      File[] files = tpFile.listFiles();
      if(files == null) {
         return new String[0];
      } else {
         for(int var13 = 0; var13 < files.length; ++var13) {
            File file = files[var13];
            String dirPath;
            if(file.isFile()) {
               dirPath = basePath + file.getName();
               if(dirPath.startsWith(prefixAssets)) {
                  dirPath = dirPath.substring(prefixAssets.length());
                  if(dirPath.startsWith(prefix) && dirPath.endsWith(suffix)) {
                     list.add(dirPath);
                  }
               }
            } else if(file.isDirectory()) {
               dirPath = basePath + file.getName() + "/";
               String[] names1 = collectFilesFolder(file, dirPath, prefix, suffix);

               for(int n = 0; n < names1.length; ++n) {
                  String name = names1[n];
                  list.add(name);
               }
            }
         }

         String[] var131 = (String[])((String[])((String[])list.toArray(new String[list.size()])));
         return var131;
      }
   }

   private static String[] collectFilesZIP(File tpFile, String prefix, String suffix) {
      ArrayList list = new ArrayList();
      String prefixAssets = "assets/minecraft/";

      try {
         ZipFile var9 = new ZipFile(tpFile);
         Enumeration en = var9.entries();

         while(en.hasMoreElements()) {
            ZipEntry names1 = (ZipEntry)en.nextElement();
            String name = names1.getName();
            if(name.startsWith(prefixAssets)) {
               name = name.substring(prefixAssets.length());
               if(name.startsWith(prefix) && name.endsWith(suffix)) {
                  list.add(name);
               }
            }
         }

         var9.close();
         String[] names11 = (String[])((String[])((String[])list.toArray(new String[list.size()])));
         return names11;
      } catch (IOException var91) {
         var91.printStackTrace();
         return new String[0];
      }
   }

   private static CustomItemProperties[][] propertyListToArray(List list) {
      CustomItemProperties[][] propArr = new CustomItemProperties[list.size()][];

      for(int i = 0; i < list.size(); ++i) {
         List subList = (List)list.get(i);
         if(subList != null) {
            CustomItemProperties[] subArr = (CustomItemProperties[])((CustomItemProperties[])((CustomItemProperties[])subList.toArray(new CustomItemProperties[subList.size()])));
            Arrays.sort(subArr, new CustomItemsComparator());
            propArr[i] = subArr;
         }
      }

      return propArr;
   }

   private static void addToItemList(CustomItemProperties cp, List itemList) {
      if(cp.items != null) {
         for(int i = 0; i < cp.items.length; ++i) {
            int itemId = cp.items[i];
            if(itemId <= 0) {
               Config.warn("Invalid item ID: " + itemId);
            } else {
               addToList(cp, itemList, itemId);
            }
         }
      }
   }

   private static void addToList(CustomItemProperties cp, List list, int id) {
      while(id >= list.size()) {
         list.add((Object)null);
      }

      Object subList = (List)list.get(id);
      if(subList == null) {
         subList = new ArrayList();
         list.set(id, subList);
      }

      ((List)subList).add(cp);
   }

   public static IIcon getCustomItemTexture(ItemStack itemStack, IIcon icon) {
      if(itemProperties == null) {
         return icon;
      } else if(itemStack == null) {
         return icon;
      } else {
         Item item = itemStack.getItem();
         int itemId = Item.getIdFromItem(item);
         if(itemId >= 0 && itemId < itemProperties.length) {
            CustomItemProperties[] cips = itemProperties[itemId];
            if(cips != null) {
               for(int i = 0; i < cips.length; ++i) {
                  CustomItemProperties cip = cips[i];
                  IIcon iconNew = getCustomItemTexture(cip, itemStack, icon);
                  if(iconNew != null) {
                     return iconNew;
                  }
               }
            }
         }

         return icon;
      }
   }

   public static IIcon getCustomPotionTexture(ItemPotion item, int damage) {
      if(itemProperties == null) {
         return null;
      } else {
         int itemId = Item.getIdFromItem(item);
         if(itemId >= 0 && itemId < itemProperties.length) {
            CustomItemProperties[] cips = itemProperties[itemId];
            if(cips != null) {
               for(int i = 0; i < cips.length; ++i) {
                  CustomItemProperties cip = cips[i];
                  IIcon iconNew = getCustomPotionTexture(cip, item, damage);
                  if(iconNew != null) {
                     return iconNew;
                  }
               }
            }
         }

         return null;
      }
   }

   private static IIcon getCustomPotionTexture(CustomItemProperties cip, ItemPotion item, int damage) {
      if(cip.damage != null) {
         if(cip.damageMask != 0) {
            damage &= cip.damageMask;
         }

         if(!cip.damage.isInRange(damage)) {
            return null;
         }
      }

      return cip.textureIcon;
   }

   private static IIcon getCustomItemTexture(CustomItemProperties cip, ItemStack itemStack, IIcon icon) {
      Item item = itemStack.getItem();
      int i;
      int level;
      if(cip.damage != null) {
         i = itemStack.getItemDamage();
         if(cip.damageMask != 0) {
            i &= cip.damageMask;
         }

         level = item.getMaxDamage();
         if(cip.damagePercent) {
            i = (int)((double)(i * 100) / (double)level);
         }

         if(!cip.damage.isInRange(i)) {
            return null;
         }
      }

      if(cip.stackSize != null && !cip.stackSize.isInRange(itemStack.stackSize)) {
         return null;
      } else {
         int[] var8;
         boolean var9;
         if(cip.enchantmentIds != null) {
            var8 = getEnchantmentIds(itemStack);
            var9 = false;

            for(i = 0; i < var8.length; ++i) {
               level = var8[i];
               if(cip.enchantmentIds.isInRange(level)) {
                  var9 = true;
                  break;
               }
            }

            if(!var9) {
               return null;
            }
         }

         if(cip.enchantmentLevels != null) {
            var8 = getEnchantmentLevels(itemStack);
            var9 = false;

            for(i = 0; i < var8.length; ++i) {
               level = var8[i];
               if(cip.enchantmentLevels.isInRange(level)) {
                  var9 = true;
                  break;
               }
            }

            if(!var9) {
               return null;
            }
         }

         if(cip.nbtTagValues != null) {
            ;
         }

         return cip.textureIcon;
      }
   }

   private static int[] getEnchantmentIds(ItemStack itemStack) {
      Map map = EnchantmentHelper.getEnchantments(itemStack);
      Set keySet = map.keySet();
      int[] ids = new int[keySet.size()];
      int index = 0;

      for(Iterator it = keySet.iterator(); it.hasNext(); ++index) {
         Integer id = (Integer)it.next();
         ids[index] = id.intValue();
      }

      return ids;
   }

   private static int[] getEnchantmentLevels(ItemStack itemStack) {
      Map map = EnchantmentHelper.getEnchantments(itemStack);
      Collection values = map.values();
      int[] levels = new int[values.size()];
      int index = 0;

      for(Iterator it = values.iterator(); it.hasNext(); ++index) {
         Integer level = (Integer)it.next();
         levels[index] = level.intValue();
      }

      return levels;
   }

   public static ResourceLocation getLocationItemGlint(ItemStack par2ItemStack, ResourceLocation resItemGlint) {
      return resItemGlint;
   }
}
