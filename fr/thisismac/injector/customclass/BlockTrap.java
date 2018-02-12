package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrap extends Block {
   private IIcon field_150008_b;
   private IIcon field_150010_M;

   public BlockTrap() {
      super(Material.field_151569_G);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
      return null;
   }

   public boolean renderAsNormalBlock() {
      return true;
   }

   public Block getBlock(World world, int meta, int x, int y, int z) {
      switch(meta) {
      case 0:
         return world.getBlock(x + 1, y, z);
      case 1:
         return world.getBlock(x, y, z + 1);
      case 2:
         return world.getBlock(x - 1, y, z);
      case 3:
         return world.getBlock(x, y, z - 1);
      default:
         return world.getBlock(x + 1, y, z);
      }
   }

   public int getBlockMetadata(World world, int meta, int x, int y, int z) {
      switch(meta) {
      case 0:
         return world.getBlockMetadata(x + 1, y, z);
      case 1:
         return world.getBlockMetadata(x, y, z + 1);
      case 2:
         return world.getBlockMetadata(x - 1, y, z);
      case 3:
         return world.getBlockMetadata(x, y, z - 1);
      default:
         return world.getBlockMetadata(x + 1, y, z);
      }
   }

   public int getBlockBrightness(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
      WorldClient world = Minecraft.getMinecraft().theWorld;
      int meta = world.getBlockMetadata(p_149720_2_, p_149720_3_, p_149720_4_);
      Block block = this.getBlock(world, meta, p_149720_2_, p_149720_3_, p_149720_4_);
      if(!(block instanceof BlockGrass) && !(block instanceof BlockLeaves)) {
         return super.getBlockBrightness(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_);
      } else {
         int l = 0;
         int i1 = 0;
         int j1 = 0;

         for(int k1 = -1; k1 <= 1; ++k1) {
            for(int l1 = -1; l1 <= 1; ++l1) {
               int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeFoliageColor(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
               l += (i2 & 16711680) >> 16;
               i1 += (i2 & 65280) >> 8;
               j1 += i2 & 255;
            }
         }

         return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
      }
   }

   public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
      try {
         return access.getBlock(x + 1, y, z) == this?access.getBlock(x - 1, y, z).getIcon(side + 1, access.getBlockMetadata(x + 1, y, z)):access.getBlock(x + 1, y, z).getIcon(side + 1, access.getBlockMetadata(x + 1, y, z));
      } catch (Exception var7) {
         return super.getBlockTextureFromSide(side);
      }
   }
}
