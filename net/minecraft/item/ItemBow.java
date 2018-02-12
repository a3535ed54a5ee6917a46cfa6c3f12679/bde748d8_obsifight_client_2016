package net.minecraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBow extends Item {
   public static final String[] bowPullIconNameArray = new String[]{"pulling_0", "pulling_1", "pulling_2"};
   protected IIcon[] iconArray;
   private static final String __OBFID = "CL_00001777";
   private static final String __OBFID = "CL_00001777";

   public ItemBow() {
      this.maxStackSize = 1;
      this.setMaxDamage(384);
      this.setCreativeTab(CreativeTabs.tabCombat);
   }

   public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
      boolean var5 = p_77615_3_.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, p_77615_1_) > 0;
      if(var5 || p_77615_3_.inventory.hasItem(Items.arrow)) {
         int var6 = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;
         float var7 = (float)var6 / 20.0F;
         var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
         if((double)var7 < 0.1D) {
            return;
         }

         if(var7 > 1.0F) {
            var7 = 1.0F;
         }

         EntityArrow var8 = new EntityArrow(p_77615_2_, p_77615_3_, var7 * 2.0F);
         if(var7 == 1.0F) {
            var8.setIsCritical(true);
         }

         int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, p_77615_1_);
         if(var9 > 0) {
            var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
         }

         int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, p_77615_1_);
         if(var10 > 0) {
            var8.setKnockbackStrength(var10);
         }

         if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, p_77615_1_) > 0) {
            var8.setFire(100);
         }

         p_77615_1_.damageItem(1, p_77615_3_);
         p_77615_2_.playSoundAtEntity(p_77615_3_, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
         if(var5) {
            var8.canBePickedUp = 2;
         } else {
            p_77615_3_.inventory.consumeInventoryItem(Items.arrow);
         }

         if(!p_77615_2_.isClient) {
            p_77615_2_.spawnEntityInWorld(var8);
         }
      }
   }

   public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
      return p_77654_1_;
   }

   public int getMaxItemUseDuration(ItemStack p_77626_1_) {
      return 72000;
   }

   public EnumAction getItemUseAction(ItemStack p_77661_1_) {
      return EnumAction.bow;
   }

   public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if(p_77659_3_.capabilities.isCreativeMode || p_77659_3_.inventory.hasItem(Items.arrow)) {
         p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
      }

      return p_77659_1_;
   }

   public int getItemEnchantability() {
      return 1;
   }

   public void registerIcons(IIconRegister p_94581_1_) {
      this.itemIcon = p_94581_1_.registerIcon(this.getIconString() + "_standby");
      this.iconArray = new IIcon[bowPullIconNameArray.length];

      for(int var2 = 0; var2 < this.iconArray.length; ++var2) {
         this.iconArray[var2] = p_94581_1_.registerIcon(this.getIconString() + "_" + bowPullIconNameArray[var2]);
      }
   }

   public IIcon getItemIconForUseDuration(int p_94599_1_) {
      return this.iconArray[p_94599_1_];
   }
}
