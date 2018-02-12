package fr.thisismac.injector.customclass;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class CustomMinerai extends Item {
   private IIcon field_111220_a;
   String name;

   public CustomMinerai(String nm) {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
      this.name = nm;
   }

   public String getUnlocalizedName(ItemStack p_77667_1_) {
      return p_77667_1_.getItemDamage() == 1?"item." + this.name + "powder":"item." + this.name;
   }

   public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
      p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
      p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
   }

   public IIcon getIconFromDamage(int p_77617_1_) {
      return p_77617_1_ == 1?this.field_111220_a:super.getIconFromDamage(p_77617_1_);
   }

   public void registerIcons(IIconRegister p_94581_1_) {
      super.registerIcons(p_94581_1_);
      this.field_111220_a = p_94581_1_.registerIcon(this.name + "powder");
   }
}
