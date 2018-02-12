package fr.thisismac.injector.customclass;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockStainedSand extends BlockFalling {
   public BlockStainedSand() {
      super(Material.sand);
      this.setHardness(0.5F);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setStepSound(field_149776_m);
   }
}
