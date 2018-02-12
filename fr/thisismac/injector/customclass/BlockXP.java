package fr.thisismac.injector.customclass;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockXP extends Block {
   private IIcon field_150008_b;
   private IIcon field_150010_M;

   public BlockXP(Material p_i45394_1_) {
      super(p_i45394_1_);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setHardness(2.0F);
   }

   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      if(p_149691_2_ == 2) {
         if(p_149691_1_ == 1) {
            return this.field_150008_b;
         }

         if(p_149691_1_ != 0) {
            return this.field_150010_M;
         }
      }

      return this.blockIcon;
   }

   public int quantityDropped(Random p_149745_1_) {
      return 0;
   }

   public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
      this.dropXpOnBlockBreak(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, 50);
   }
}
