package fr.thisismac.injector.customclass;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class CustomItemFood extends ItemFood {
   private boolean isRare;
   private PotionEffect[] effects;

   public CustomItemFood(boolean isRare, PotionEffect[] effects) {
      super(4, false);
      this.isRare = isRare;
      this.effects = effects;
      this.setAlwaysEdible();
   }

   public boolean hasEffect(ItemStack p_77636_1_) {
      return this.isRare;
   }

   public EnumRarity getRarity(ItemStack p_77613_1_) {
      return this.isRare?EnumRarity.epic:EnumRarity.common;
   }

   protected void onFoodEaten(ItemStack item, World world, EntityPlayer player) {
      if(!world.isClient && this.effects != null) {
         PotionEffect[] var4 = this.effects;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            PotionEffect effect = var4[var6];
            player.addPotionEffect(effect);
         }
      } else {
         super.onFoodEaten(item, world, player);
      }
   }
}
