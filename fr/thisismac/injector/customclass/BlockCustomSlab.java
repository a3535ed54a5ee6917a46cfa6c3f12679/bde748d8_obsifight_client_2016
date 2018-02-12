package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockCustomSlab extends BlockSlab {
   private IIcon field_150007_M;
   private static final String __OBFID = "CL_00000320";
   Block ax;

   public BlockCustomSlab(boolean p_i45431_1_, Block c) {
      super(p_i45431_1_, c.getMaterial());
      this.ax = c;
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public String func_150002_b(int p_150002_1_) {
      return null;
   }
}
