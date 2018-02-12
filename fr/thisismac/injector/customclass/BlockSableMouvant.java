package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSableMouvant extends Block {
   private static IIcon field_149837_b;

   public BlockSableMouvant() {
      super(Material.rock);
      this.setHardness(2.5F);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
      p_149670_5_.setInWeb();
   }

   public boolean isOpaqueCube() {
      return true;
   }

   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return field_149837_b;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
      return null;
   }

   public void registerBlockIcons(IIconRegister p_149651_1_) {
      field_149837_b = p_149651_1_.registerIcon("stone");
   }
}
