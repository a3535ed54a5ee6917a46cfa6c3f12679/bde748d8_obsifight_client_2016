package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockxRay extends Block {
   public BlockxRay() {
      super(Material.rock);
      this.setHardness(2.5F);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public boolean isOpaqueCube() {
      return true;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
      return null;
   }
}
