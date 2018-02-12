package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTerrazine extends Block {
   private IIcon field_150008_b;
   private IIcon field_150010_M;

   public BlockTerrazine() {
      super(Material.rock);
   }

   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      if(p_149691_2_ == 2) {
         if(p_149691_1_ == 1) {
            return this.field_150008_b;
         }

         if(p_149691_1_ != 0) {
            return this.field_150010_M;
         }
      }

      return this.blockIcon;
   }

   public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      return p_149727_1_.isClient;
   }
}
