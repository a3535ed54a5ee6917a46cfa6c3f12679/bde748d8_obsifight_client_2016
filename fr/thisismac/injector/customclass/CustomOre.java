package fr.thisismac.injector.customclass;

import fr.thisismac.injector.Core;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CustomOre extends BlockOre {
   public Core.EnumStuff stuff;

   public CustomOre(Core.EnumStuff stuff) {
      this.stuff = stuff;
   }

   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
      return this == Blocks.coal_ore?Items.coal:(this == Blocks.diamond_ore?Items.record_mall:(this == Blocks.lapis_ore?Items.dye:(this == Blocks.emerald_ore?Items.emerald:(this == Blocks.quartz_ore?Items.quartz:(this == Blocks.garnet_ore?Items.garnet:(this == Blocks.amethyst_ore?Items.amethyst:(this == Blocks.titanium_ore?Items.titanium:(this == Block.getBlockById(243)?Items.admin_axe:(this == Blocks.manganese_ore?Items.manganese:Items.apple)))))))));
   }

   public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
      super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
      if(this.getItemDropped(p_149690_5_, p_149690_1_.rand, p_149690_7_) != Item.getItemFromBlock(this)) {
         int var8 = 0;
         if(this == Blocks.garnet_ore) {
            var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 0, 2);
         } else if(this == Blocks.amethyst_ore) {
            var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 3, 7);
         } else if(this == Blocks.titanium_ore) {
            var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 3, 7);
         } else if(this == Blocks.xenotium_ore) {
            var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 4, 10);
         }

         this.dropXpOnBlockBreak(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, var8);
      }
   }

   public int damageDropped(int p_149692_1_) {
      return 0;
   }
}
