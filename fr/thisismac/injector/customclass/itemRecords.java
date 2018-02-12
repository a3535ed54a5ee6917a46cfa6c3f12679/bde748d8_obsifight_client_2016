package fr.thisismac.injector.customclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class itemRecords extends Item {
   private static final Map field_150928_b = new HashMap();
   public final String field_150929_a;

   public itemRecords(String p_i45350_1_) {
      this.field_150929_a = p_i45350_1_;
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabMisc);
      field_150928_b.put(p_i45350_1_, this);
   }

   public IIcon getIconFromDamage(int p_77617_1_) {
      return this.itemIcon;
   }

   public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
      if(p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) == Blocks.jukebox && p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_) == 0) {
         if(p_77648_3_.isClient) {
            return true;
         } else {
            ((BlockJukebox)Blocks.jukebox).func_149926_b(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_1_);
            p_77648_3_.playAuxSFXAtEntity((EntityPlayer)null, 1005, p_77648_4_, p_77648_5_, p_77648_6_, Item.getIdFromItem(this));
            --p_77648_1_.stackSize;
            return true;
         }
      } else {
         return false;
      }
   }

   public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
      p_77624_3_.add(this.func_150927_i());
   }

   public String func_150927_i() {
      return StatCollector.translateToLocal("item.record." + this.field_150929_a + ".desc");
   }

   public EnumRarity getRarity(ItemStack p_77613_1_) {
      return EnumRarity.rare;
   }

   public static itemRecords func_150926_b(String p_150926_0_) {
      return (itemRecords)field_150928_b.get(p_150926_0_);
   }
}
