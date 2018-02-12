package fr.thisismac.injector.customclass;

import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoorCustom extends BlockDoor {
   private IIcon[] field_150017_a;
   private IIcon[] field_150016_b;

   public BlockDoorCustom(Material p_i45402_1_) {
      super(p_i45402_1_);
      this.setHardness(5.0F);
   }

   public int getRenderType() {
      return 7;
   }

   public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
      try {
         return p_149673_1_.getBlock(p_149673_2_, p_149673_3_ - 1, p_149673_4_) == this?p_149673_1_.getBlock(p_149673_2_, p_149673_3_ - 2, p_149673_4_).getIcon(p_149673_5_, p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_ - 2, p_149673_4_)):p_149673_1_.getBlock(p_149673_2_, p_149673_3_ - 1, p_149673_4_).getIcon(p_149673_5_, p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_ - 1, p_149673_4_));
      } catch (Exception var7) {
         return super.getBlockTextureFromSide(p_149673_5_);
      }
   }

   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
      return (p_149650_1_ & 8) != 0?null:Items.customDoor;
   }

   public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
      return Items.customDoor;
   }
}
