package net.minecraft.item;

import java.util.List;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemArmor extends Item {
   private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
   private static final int[] dura_obsidium = new int[]{1107, 1501, 1399, 1202};
   private static final int[] dura_titanium = new int[]{753, 1005, 954, 852};
   private static final int[] dura_amethyste = new int[]{601, 898, 842, 721};
   private static final int[] dura_garnet = new int[]{501, 798, 742, 621};
   private static final int[] dura_manganse = new int[]{436, 687, 602, 529};
   private static final String[] CLOTH_OVERLAY_NAMES = new String[]{"leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"};
   public static final String[] EMPTY_SLOT_NAMES = new String[]{"empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots"};
   private static final IBehaviorDispenseItem dispenserBehavior = new BehaviorDefaultDispenseItem() {
      private static final String __OBFID = "CL_00001767";
      private static final String __OBFID = "CL_00001767";

      protected ItemStack dispenseStack(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
         EnumFacing var3 = BlockDispenser.func_149937_b(p_82487_1_.getBlockMetadata());
         int var4 = p_82487_1_.getXInt() + var3.getFrontOffsetX();
         int var5 = p_82487_1_.getYInt() + var3.getFrontOffsetY();
         int var6 = p_82487_1_.getZInt() + var3.getFrontOffsetZ();
         AxisAlignedBB var7 = AxisAlignedBB.getBoundingBox((double)var4, (double)var5, (double)var6, (double)(var4 + 1), (double)(var5 + 1), (double)(var6 + 1));
         List var8 = p_82487_1_.getWorld().selectEntitiesWithinAABB(EntityLivingBase.class, var7, new IEntitySelector.ArmoredMob(p_82487_2_));
         if(var8.size() > 0) {
            EntityLivingBase var9 = (EntityLivingBase)var8.get(0);
            int var10 = var9 instanceof EntityPlayer?1:0;
            int var11 = EntityLiving.getArmorPosition(p_82487_2_);
            ItemStack var12 = p_82487_2_.copy();
            var12.stackSize = 1;
            var9.setCurrentItemOrArmor(var11 - var10, var12);
            if(var9 instanceof EntityLiving) {
               ((EntityLiving)var9).setEquipmentDropChance(var11, 2.0F);
            }

            --p_82487_2_.stackSize;
            return p_82487_2_;
         } else {
            return super.dispenseStack(p_82487_1_, p_82487_2_);
         }
      }
   };
   public final int armorType;
   public final int damageReduceAmount;
   public final int renderIndex;
   private final ItemArmor.ArmorMaterial material;
   private IIcon overlayIcon;
   private IIcon emptySlotIcon;
   private static final String __OBFID = "CL_00001766";
   private static final String __OBFID = "CL_00001766";

   public ItemArmor(ItemArmor.ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
      this.material = p_i45325_1_;
      this.armorType = p_i45325_3_;
      this.renderIndex = p_i45325_2_;
      this.damageReduceAmount = p_i45325_1_.getDamageReductionAmount(p_i45325_3_);
      this.setMaxDamage(p_i45325_1_.getDurability(p_i45325_3_));
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabCombat);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
   }

   public int getColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_) {
      if(p_82790_2_ > 0) {
         return 16777215;
      } else {
         int var3 = this.getColor(p_82790_1_);
         if(var3 < 0) {
            var3 = 16777215;
         }

         return var3;
      }
   }

   public boolean requiresMultipleRenderPasses() {
      return this.material == ItemArmor.ArmorMaterial.CLOTH;
   }

   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   public ItemArmor.ArmorMaterial getArmorMaterial() {
      return this.material;
   }

   public boolean hasColor(ItemStack p_82816_1_) {
      return this.material != ItemArmor.ArmorMaterial.CLOTH?false:(!p_82816_1_.hasTagCompound()?false:(!p_82816_1_.getTagCompound().func_150297_b("display", 10)?false:p_82816_1_.getTagCompound().getCompoundTag("display").func_150297_b("color", 3)));
   }

   public int getColor(ItemStack p_82814_1_) {
      if(this.material != ItemArmor.ArmorMaterial.CLOTH) {
         return -1;
      } else {
         NBTTagCompound var2 = p_82814_1_.getTagCompound();
         if(var2 == null) {
            return 10511680;
         } else {
            NBTTagCompound var3 = var2.getCompoundTag("display");
            return var3 == null?10511680:(var3.func_150297_b("color", 3)?var3.getInteger("color"):10511680);
         }
      }
   }

   public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_) {
      return p_77618_2_ == 1?this.overlayIcon:super.getIconFromDamageForRenderPass(p_77618_1_, p_77618_2_);
   }

   public void removeColor(ItemStack p_82815_1_) {
      if(this.material == ItemArmor.ArmorMaterial.CLOTH) {
         NBTTagCompound var2 = p_82815_1_.getTagCompound();
         if(var2 != null) {
            NBTTagCompound var3 = var2.getCompoundTag("display");
            if(var3.hasKey("color")) {
               var3.removeTag("color");
            }
         }
      }
   }

   public void func_82813_b(ItemStack p_82813_1_, int p_82813_2_) {
      if(this.material != ItemArmor.ArmorMaterial.CLOTH) {
         throw new UnsupportedOperationException("Can\'t dye non-leather!");
      } else {
         NBTTagCompound var3 = p_82813_1_.getTagCompound();
         if(var3 == null) {
            var3 = new NBTTagCompound();
            p_82813_1_.setTagCompound(var3);
         }

         NBTTagCompound var4 = var3.getCompoundTag("display");
         if(!var3.func_150297_b("display", 10)) {
            var3.setTag("display", var4);
         }

         var4.setInteger("color", p_82813_2_);
      }
   }

   public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
      return this.material.func_151685_b().getItemDamage() == p_82789_2_.getItemDamage() && this.material.func_151685_b().getItem() == p_82789_2_.getItem()?true:super.getIsRepairable(p_82789_1_, p_82789_2_);
   }

   public void registerIcons(IIconRegister p_94581_1_) {
      super.registerIcons(p_94581_1_);
      if(this.material == ItemArmor.ArmorMaterial.CLOTH) {
         this.overlayIcon = p_94581_1_.registerIcon(CLOTH_OVERLAY_NAMES[this.armorType]);
      }

      this.emptySlotIcon = p_94581_1_.registerIcon(EMPTY_SLOT_NAMES[this.armorType]);
   }

   public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      int var4 = EntityLiving.getArmorPosition(p_77659_1_) - 1;
      ItemStack var5 = p_77659_3_.getCurrentArmor(var4);
      if(var5 == null) {
         p_77659_3_.setCurrentItemOrArmor(var4, p_77659_1_.copy());
         p_77659_1_.stackSize = 0;
      }

      return p_77659_1_;
   }

   public static IIcon func_94602_b(int p_94602_0_) {
      switch(p_94602_0_) {
      case 0:
         return Items.diamond_helmet.emptySlotIcon;
      case 1:
         return Items.diamond_chestplate.emptySlotIcon;
      case 2:
         return Items.diamond_leggings.emptySlotIcon;
      case 3:
         return Items.diamond_boots.emptySlotIcon;
      default:
         return null;
      }
   }

   public static enum ArmorMaterial {
      CLOTH("CLOTH", 0, "CLOTH", 0, 5, new int[]{1, 3, 2, 1}, 15),
      CHAIN("CHAIN", 1, "CHAIN", 1, 20, new int[]{2, 5, 4, 1}, 12),
      IRON("IRON", 2, "IRON", 2, 30, new int[]{2, 6, 5, 2}, 9),
      GOLD("GOLD", 3, "GOLD", 3, 7, new int[]{2, 5, 3, 1}, 25),
      MANGANESE("MANGANESE", 4, "MANGANESE", 5, 33, new int[]{2, 5, 5, 2}, 20),
      DIAMOND("DIAMOND", 5, "DIAMOND", 4, 35, new int[]{3, 6, 5, 2}, 10),
      GARNET("GARNET", 6, "GARNET", 6, 40, new int[]{3, 7, 6, 3}, 15),
      AMETHYST("AMETHYST", 7, "AMETHYST", 7, 45, new int[]{4, 7, 7, 3}, 15),
      TITANIUM("TITANIUM", 8, "TITANIUM", 8, 50, new int[]{4, 8, 7, 4}, 10),
      OBSIDIUM("OBSIDIUM", 9, "OBSIDIUM", 10, 4000, new int[]{5, 9, 7, 5}, 5);
      private int maxDamageFactor;
      private int[] damageReductionAmountArray;
      private int enchantability;
      private static final ItemArmor.ArmorMaterial[] $VALUES = new ItemArmor.ArmorMaterial[]{CLOTH, CHAIN, IRON, GOLD, DIAMOND, MANGANESE, GARNET, AMETHYST, TITANIUM, OBSIDIUM};
      private static final String __OBFID = "CL_00001768";

      private static final ItemArmor.ArmorMaterial[] $VALUES$ = new ItemArmor.ArmorMaterial[]{CLOTH, CHAIN, IRON, GOLD, MANGANESE, DIAMOND, GARNET, AMETHYST, TITANIUM, OBSIDIUM};
      private static final String __OBFID = "CL_00001768";

      private ArmorMaterial(String p_i46413_1_, int p_i46413_2_, String p_i1827_1_, int p_i1827_2_, int p_i1827_3_, int[] p_i1827_4_, int p_i1827_5_) {
         this.maxDamageFactor = p_i1827_3_;
         this.damageReductionAmountArray = p_i1827_4_;
         this.enchantability = p_i1827_5_;
      }

      public int getDurability(int p_78046_1_) {
         return this == OBSIDIUM?ItemArmor.dura_obsidium[p_78046_1_] * 1:(this == TITANIUM?ItemArmor.dura_titanium[p_78046_1_] * 1:(this == AMETHYST?ItemArmor.dura_amethyste[p_78046_1_] * 1:(this == GARNET?ItemArmor.dura_garnet[p_78046_1_] * 1:(this == MANGANESE?ItemArmor.dura_manganse[p_78046_1_] * 1:ItemArmor.dura_obsidium[p_78046_1_] * this.maxDamageFactor))));
      }

      public int getDamageReductionAmount(int p_78044_1_) {
         return this.damageReductionAmountArray[p_78044_1_];
      }

      public int getEnchantability() {
         return this.enchantability;
      }

      public ItemStack func_151685_b() {
         return this == CLOTH?new ItemStack(Items.leather):(this == CHAIN?new ItemStack(Items.iron_ingot):(this == GOLD?new ItemStack(Items.gold_ingot):(this == IRON?new ItemStack(Items.iron_ingot):(this == DIAMOND?new ItemStack(Items.diamond):(this == AMETHYST?new ItemStack(Items.amethyst, 1, 0):(this == GARNET?new ItemStack(Items.garnet, 1, 0):(this == TITANIUM?new ItemStack(Items.titanium, 1, 0):(this == OBSIDIUM?new ItemStack(Items.xenotium, 1, 0):(this == MANGANESE?new ItemStack(Items.manganese, 1, 0):null)))))))));
      }
   }
}
