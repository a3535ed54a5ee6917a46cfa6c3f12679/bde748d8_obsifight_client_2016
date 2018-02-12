package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockJump extends Block {
   public BlockJump() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1, int par2, int par3, int par4) {
      return AxisAlignedBB.getBoundingBox((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - 0.125F), (double)(par4 + 1));
   }

   public void onEntityCollidedWithBlock(World par1, int par2, int par3, int par4, Entity par5) {
      if(par5.isSneaking()) {
         par5.motionY += 0.0D;
         par5.fallDistance = (float)((double)par5.fallDistance + 0.0D);
      } else {
         par5.ticksExisted = 10;
         par5.motionY += 2.0D;
         par1.playSoundAtEntity(par5, "tile.piston.in", 1.0F, 1.0F);
         par5.fallDistance = (float)((double)par5.fallDistance + 0.0D);
      }
   }
}
