package fr.thisismac.injector.customclass;

import java.util.Random;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRenforcedGlass extends BlockBreakable {
   public BlockRenforcedGlass(Material p_i45408_1_, boolean p_i45408_2_) {
      super("renforced_glass", p_i45408_1_, p_i45408_2_);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setHardness(4.0F);
   }

   public int quantityDropped(Random p_149745_1_) {
      return 1;
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
