package fr.thisismac.injector.customclass;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class StainedSandstone extends Block {
   public static final String[] field_149838_a = new String[]{"black", "blue", "red", "green", "brown", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
   private static IIcon[] ico = new IIcon[16];

   public StainedSandstone() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public void registerBlockIcons(IIconRegister p_149651_1_) {
      for(int ic = 0; ic < 16; ++ic) {
         ico[ic] = p_149651_1_.registerIcon("stained_sandstone_" + ItemDye.field_150923_a[ic]);
      }
   }

   public int damageDropped(int p_149692_1_) {
      return p_149692_1_;
   }

   public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
      for(int sub = 0; sub < 16; ++sub) {
         p_149666_3_.add(new ItemStack(p_149666_1_, 1, sub));
      }
   }
}
