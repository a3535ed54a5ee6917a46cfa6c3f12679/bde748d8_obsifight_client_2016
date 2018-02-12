package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BoneBlock extends BlockRotatedPillar {
   public BoneBlock() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setHardness(2.0F);
      this.setStepSound(Block.soundTypeStone);
   }

   protected IIcon func_150163_b(int p_150163_1_) {
      return null;
   }
}
