package fr.thisismac.injector.customclass;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemJoint extends ItemFood {
   public ItemJoint(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
      super(p_i45339_1_, p_i45339_3_);
   }

   public boolean hasEffect(ItemStack p_77636_1_) {
      return p_77636_1_.getItemDamage() > 0;
   }

   protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
      if(!p_77849_2_.isClient) {
         p_77849_3_.addPotionEffect(new PotionEffect(Potion.confusion.id, 1500, 0));
         p_77849_3_.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 3000, 1));
      }
   }
}
