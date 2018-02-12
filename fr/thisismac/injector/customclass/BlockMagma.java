package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockMagma extends Block {
   private Minecraft mc;

   public BlockMagma() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setResistance(2.4F);
      this.mc = Minecraft.getMinecraft();
      this.setLightLevel(10.0F);
   }

   public void onEntityWalking(World p_149724_1_, int p_149724_2_, int p_149724_3_, int p_149724_4_, Entity player) {
      float base_damage = 1.0F;
      boolean isprotectedtofire = player.isImmuneToFire();
      boolean isPlayer = player instanceof EntityLivingBase;
      int FireProtectionLevel = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)player);
      if(isPlayer) {
         if(FireProtectionLevel > 0 && !isprotectedtofire) {
            float final_damage = (float)Math.round(base_damage / (float)FireProtectionLevel);
            player.attackEntityFrom(DamageSource.inFire, final_damage);
         } else if(FireProtectionLevel == 0 && !isprotectedtofire) {
            player.attackEntityFrom(DamageSource.inFire, base_damage);
         }
      }

      super.onEntityWalking(p_149724_1_, p_149724_2_, p_149724_3_, p_149724_4_, player);
   }
}
