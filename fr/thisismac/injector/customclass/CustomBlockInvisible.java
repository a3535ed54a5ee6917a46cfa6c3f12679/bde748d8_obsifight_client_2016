package fr.thisismac.injector.customclass;

import java.util.Random;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class CustomBlockInvisible extends BlockBreakable {
   public CustomBlockInvisible(Material p_i45408_1_, boolean p_i45408_2_) {
      super("invisible", p_i45408_1_, p_i45408_2_);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public int quantityDropped(Random p_149745_1_) {
      return 0;
   }

   public int getRenderBlockPass() {
      return 0;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
