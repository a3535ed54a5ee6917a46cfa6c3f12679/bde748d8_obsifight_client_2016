package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDye;
import net.minecraft.util.IIcon;

public class d extends Block {
   public String[] sub = new String[]{"xd", "lol", "mdr"};
   private boolean field_149985_a;
   private IIcon field_149984_b;
   private IIcon side;
   private IIcon[] field_150033_a;

   public d() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return p_149691_1_ == 1?this.field_149984_b:(p_149691_1_ == 0?this.field_149984_b:(p_149691_2_ == 2 && p_149691_1_ == 2?this.side:(p_149691_2_ == 3 && p_149691_1_ == 5?this.side:(p_149691_2_ == 0 && p_149691_1_ == 3?this.side:(p_149691_2_ == 1 && p_149691_1_ == 4?this.side:this.blockIcon)))));
   }

   public static int func_150031_c(int p_150031_0_) {
      return ~p_150031_0_ & 15;
   }

   public void registerBlockIcons(IIconRegister p_149651_1_) {
      this.field_150033_a = new IIcon[16];

      for(int var2 = 0; var2 < this.field_150033_a.length; ++var2) {
         this.field_150033_a[var2] = p_149651_1_.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[func_150031_c(var2)]);
      }
   }
}
