package fr.thisismac.injector.customclass;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class CustomEnchantNoBack extends Enchantment {
   public CustomEnchantNoBack(int p_i1920_1_, int p_i1920_2_) {
      super(p_i1920_1_, p_i1920_2_, EnumEnchantmentType.bow);
      this.setName("noBack");
   }

   public int getMinEnchantability(int p_77321_1_) {
      return 5;
   }

   public int getMaxEnchantability(int p_77317_1_) {
      return 50;
   }

   public int getMaxLevel() {
      return 2;
   }
}
