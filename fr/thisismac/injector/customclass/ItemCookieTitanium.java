package fr.thisismac.injector.customclass;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCookieTitanium extends ItemFood {
   public ItemCookieTitanium(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
      super(p_i45339_1_, p_i45339_3_);
      this.maxStackSize = 3;
   }

   public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
      if(!p_77654_3_.capabilities.isCreativeMode) {
         --p_77654_1_.stackSize;
      }

      if(!p_77654_2_.isClient) {
         p_77654_3_.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 15000, 1));
      }

      return p_77654_1_;
   }

   public int getMaxItemUseDuration(ItemStack p_77626_1_) {
      return 32;
   }

   public EnumAction getItemUseAction(ItemStack p_77661_1_) {
      return EnumAction.eat;
   }

   public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
      return p_77659_1_;
   }
}
