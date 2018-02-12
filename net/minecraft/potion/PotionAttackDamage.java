package net.minecraft.potion;

import fr.thisismac.injector.Core;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class PotionAttackDamage extends Potion {
   private static final String __OBFID = "CL_00001525";
   private static final String __OBFID = "CL_00001525";

   protected PotionAttackDamage(int p_i1570_1_, boolean p_i1570_2_, int p_i1570_3_) {
      super(p_i1570_1_, p_i1570_2_, p_i1570_3_);
   }

   public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
      Core.get().getClass();
      return this.id == Potion.damageBoost.id?0.2D * (double)(p_111183_1_ + 1):(this.id == Potion.weakness.id?(double)(-0.5F * (float)(p_111183_1_ + 1)):1.3D * (double)(p_111183_1_ + 1));
   }
}
