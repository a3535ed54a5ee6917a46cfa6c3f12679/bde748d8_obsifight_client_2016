package fr.thisismac.injector.customclass;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockBon extends BlockDirectional {
   private boolean field_149985_a;
   private IIcon field_149984_b;
   private IIcon side;

   public BlockBon() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return p_149691_1_ == 1?this.field_149984_b:(p_149691_1_ == 0?this.field_149984_b:(p_149691_2_ == 2 && p_149691_1_ == 2?this.side:(p_149691_2_ == 3 && p_149691_1_ == 5?this.side:(p_149691_2_ == 0 && p_149691_1_ == 3?this.side:(p_149691_2_ == 1 && p_149691_1_ == 4?this.side:this.blockIcon)))));
   }

   public void registerBlockIcons(IIconRegister p_149651_1_) {
      this.side = p_149651_1_.registerIcon("boneblock");
      this.field_149984_b = p_149651_1_.registerIcon("boneblock_top");
      this.blockIcon = p_149651_1_.registerIcon("boneblock");
   }
}
