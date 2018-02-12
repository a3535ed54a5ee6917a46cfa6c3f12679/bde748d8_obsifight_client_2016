package fr.thisismac.injector.customclass;

import fr.thisismac.injector.Core;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;

public class CustomStuff {
   private String name;
   private List<CustomStuff.StuffPart> forbidParts;
   private Map<CustomStuff.StuffPart, Object> parts = new HashMap();
   private Map<String, Object> options = new HashMap();

   public CustomStuff(String name, CustomStuff.StuffPart[] forbidParts) {
      this.name = name;
      if(forbidParts == null) {
         this.forbidParts = new ArrayList();
      } else {
         this.forbidParts = Arrays.asList(forbidParts);
      }
   }

   public void inject() {
      CustomStuff.StuffPart[] var1 = CustomStuff.StuffPart.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CustomStuff.StuffPart part = var1[var3];
         if(this.parts.get(part) != null) {
            if(part.isItem) {
               Item.itemRegistry.addObject(Core.firstAvailableItemID++, this.name + "_" + part.name(), (Item)this.parts.get(part));
            } else {
               Block block = (Block)this.parts.get(part);
               Block.blockRegistry.addObject(Core.firstAvailableBlockID, this.name + "_" + part.name(), block);
               Item.itemRegistry.addObject(Core.firstAvailableBlockID++, this.name + "_" + part.name(), new ItemBlock(block));
            }
         }
      }
   }

   public void build() {
      CustomStuff.StuffPart[] var1 = CustomStuff.StuffPart.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CustomStuff.StuffPart part = var1[var3];
         if(!this.forbidParts.contains(part)) {
            this.parts.put(part, this.buildItem(part));
         }
      }

      RenderBiped.bipedArmorFilenamePrefix.add(this.name);
   }

   public void registerRecipes(CraftingManager cm) {
      if(!((Boolean)this.getOptions("ore_loot_ingot")).booleanValue()) {
         FurnaceRecipes.smelting().func_151393_a((Block)this.parts.get(CustomStuff.StuffPart.ORE), new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.INGOT)), 0.5F);
      }

      this.registerRecipesArmor(cm);
      this.registerRecipesIngot(cm);
      this.registerRecipesTools(cm);
      this.registerRecipesWeapons(cm);
   }

   public void registerRecipesWeapons(CraftingManager cm) {
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.BOW)), new Object[]{" #X", "# X", " #X", 'X', Items.string, '#', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.SWORD)), new Object[]{"X", "X", "#", 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT), '#', Items.stick});
   }

   public void registerRecipesTools(CraftingManager cm) {
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.PICKAXE)), new Object[]{"XXX", " # ", " # ", '#', Items.stick, 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.SHOVEL)), new Object[]{"X", "#", "#", '#', Items.stick, 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.AXE)), new Object[]{"XX", "X#", " #", '#', Items.stick, 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.HOE)), new Object[]{"XX", " #", " #", '#', Items.stick, 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
   }

   public void registerRecipesArmor(CraftingManager cm) {
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.HELMET)), new Object[]{"XXX", "X X", 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.CHESTPLATE)), new Object[]{"X X", "XXX", "XXX", 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.LEGGINGS)), new Object[]{"XXX", "X X", "X X", 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.BOOTS)), new Object[]{"X X", "X X", 'X', (Item)this.parts.get(CustomStuff.StuffPart.INGOT)});
   }

   public void registerRecipesIngot(CraftingManager cm) {
      cm.addRecipe(new ItemStack((Block)this.parts.get(CustomStuff.StuffPart.BLOCK), 1), new Object[]{"###", "###", "###", '#', new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.INGOT), 9)});
      cm.addRecipe(new ItemStack((Item)this.parts.get(CustomStuff.StuffPart.INGOT), 9), new Object[]{"#", '#', (Block)this.parts.get(CustomStuff.StuffPart.BLOCK)});
   }

   public CustomStuff setOptions(String key, Object value) {
      this.options.put(key, value);
      return this;
   }

   public List<CustomStuff.StuffPart> getForbidParts() {
      return this.forbidParts;
   }

   public String getStuffName() {
      return this.name;
   }

   public Map<CustomStuff.StuffPart, Object> getParts() {
      return this.parts;
   }

   public Object getPart(CustomStuff.StuffPart part) {
      return this.parts.get(part);
   }

   public Object getOptions(String key) {
      return !this.options.containsKey(key)?Boolean.valueOf(false):this.options.get(key);
   }

   private Object buildItem(CustomStuff.StuffPart part) {
      switch(null.$SwitchMap$fr$thisismac$injector$customclass$CustomStuff$StuffPart[part.ordinal()]) {
      case 1:
         return (new ItemArmor(ItemArmor.ArmorMaterial.valueOf(this.name.toUpperCase()), Core.fistArmorAvailableID, 0)).setUnlocalizedName("helmet_" + this.name).setTextureName(this.name + "_helmet");
      case 2:
         return (new ItemArmor(ItemArmor.ArmorMaterial.valueOf(this.name.toUpperCase()), Core.fistArmorAvailableID, 1)).setUnlocalizedName("chestplate_" + this.name).setTextureName(this.name + "_chestplate");
      case 3:
         return (new ItemArmor(ItemArmor.ArmorMaterial.valueOf(this.name.toUpperCase()), Core.fistArmorAvailableID, 2)).setUnlocalizedName("leggings_" + this.name).setTextureName(this.name + "_leggings");
      case 4:
         return (new ItemArmor(ItemArmor.ArmorMaterial.valueOf(this.name.toUpperCase()), Core.fistArmorAvailableID++, 3)).setUnlocalizedName("boots_" + this.name).setTextureName(this.name + "_boots");
      case 5:
         return (new ItemPickaxe(Item.ToolMaterial.valueOf(this.name.toUpperCase()))).setUnlocalizedName("pickaxe_" + this.name).setTextureName(this.name + "_pickaxe");
      case 6:
         return (new ItemSpade(Item.ToolMaterial.valueOf(this.name.toUpperCase()))).setUnlocalizedName("shovel_" + this.name).setTextureName(this.name + "_shovel");
      case 7:
         return (new ItemSword(Item.ToolMaterial.valueOf(this.name.toUpperCase()))).setUnlocalizedName("sword_" + this.name).setTextureName(this.name + "_sword");
      case 8:
         return (new ItemAxe(Item.ToolMaterial.valueOf(this.name.toUpperCase()))).setUnlocalizedName("axe_" + this.name).setTextureName(this.name + "_axe");
      case 9:
         return (new ItemHoe(Item.ToolMaterial.valueOf(this.name.toUpperCase()))).setUnlocalizedName("hoe_" + this.name).setTextureName(this.name + "_hoe");
      case 10:
         return (new CustomBow(((Double)this.getOptions("bow_damage")).doubleValue(), ((Integer)this.getOptions("bow_dura")).intValue(), ((Boolean)this.getOptions("bow_flame")).booleanValue())).setUnlocalizedName("bow_" + this.name).setTextureName(this.name + "_bow");
      case 11:
         return (new Item()).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("ingot_" + this.name).setTextureName(this.name + "_ingot");
      case 12:
         return (new CustomOre(Core.EnumStuff.valueOf(this.name.toUpperCase()))).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("ore_" + this.name).setBlockTextureName(this.name + "_ore");
      case 13:
         return (new BlockCompressed(MapColor.field_151660_b)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName(this.name + "_block").setBlockTextureName(this.name + "_block");
      default:
         return null;
      }
   }

   public static enum StuffPart {
      HELMET("HELMET", 0, true),
      CHESTPLATE("CHESTPLATE", 1, true),
      LEGGINGS("LEGGINGS", 2, true),
      BOOTS("BOOTS", 3, true),
      PICKAXE("PICKAXE", 4, true),
      SWORD("SWORD", 5, true),
      AXE("AXE", 6, true),
      HOE("HOE", 7, true),
      SHOVEL("SHOVEL", 8, true),
      BOW("BOW", 9, true),
      INGOT("INGOT", 10, true),
      ORE("ORE", 11, false),
      BLOCK("BLOCK", 12, false);
      boolean isItem;

      private static final CustomStuff.StuffPart[] $VALUES = new CustomStuff.StuffPart[]{HELMET, CHESTPLATE, LEGGINGS, BOOTS, PICKAXE, SWORD, AXE, HOE, SHOVEL, BOW, INGOT, ORE, BLOCK};

      private StuffPart(String var1, int var2, boolean isItem) {
         this.isItem = isItem;
      }

      public boolean isItem() {
         return this.isItem;
      }
   }
}
