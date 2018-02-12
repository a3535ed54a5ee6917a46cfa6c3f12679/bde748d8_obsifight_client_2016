package fr.thisismac.injector.customclass;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class ItemDebuff extends Item {
   public ItemDebuff() {
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }

   public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
      if(!p_77654_3_.capabilities.isCreativeMode) {
         --p_77654_1_.stackSize;
      }

      if(!p_77654_2_.isClient) {
         if(p_77654_3_.isPotionActive(Potion.blindness)) {
            p_77654_3_.removePotionEffect(15);
         }

         if(p_77654_3_.isPotionActive(Potion.confusion)) {
            p_77654_3_.removePotionEffect(9);
         }

         if(p_77654_3_.isPotionActive(Potion.digSlowdown)) {
            p_77654_3_.removePotionEffect(4);
         }

         if(p_77654_3_.isPotionActive(Potion.hunger)) {
            p_77654_3_.removePotionEffect(17);
         }

         if(p_77654_3_.isPotionActive(Potion.wither)) {
            p_77654_3_.removePotionEffect(20);
         }

         if(p_77654_3_.isPotionActive(Potion.weakness)) {
            p_77654_3_.removePotionEffect(18);
         }

         if(p_77654_3_.isPotionActive(Potion.poison)) {
            p_77654_3_.removePotionEffect(19);
         }

         if(p_77654_3_.isPotionActive(Potion.moveSlowdown)) {
            p_77654_3_.removePotionEffect(2);
         }
      }

      if(!p_77654_3_.capabilities.isCreativeMode) {
         if(p_77654_1_.stackSize <= 0) {
            return new ItemStack(Items.glass_bottle);
         }

         p_77654_3_.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
      }

      return p_77654_1_;
   }

   public int getMaxItemUseDuration(ItemStack p_77626_1_) {
      return 32;
   }

   public EnumAction getItemUseAction(ItemStack p_77661_1_) {
      return EnumAction.drink;
   }

   public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
      return p_77659_1_;
   }
}
