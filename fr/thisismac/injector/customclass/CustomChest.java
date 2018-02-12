package fr.thisismac.injector.customclass;

import net.minecraft.block.BlockChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CustomChest extends BlockChest {
   public ResourceLocation texture;

   public CustomChest(Integer p_i45397_1_, String path) {
      super(p_i45397_1_.intValue());
      this.texture = new ResourceLocation(path);
   }

   public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
      return p_149742_1_.getBlock(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof CustomChest?false:(p_149742_1_.getBlock(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof CustomChest?false:(p_149742_1_.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof CustomChest?false:!(p_149742_1_.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof CustomChest)));
   }

   public int getId() {
      return this.field_149956_a;
   }
}
