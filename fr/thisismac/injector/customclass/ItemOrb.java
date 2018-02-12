package fr.thisismac.injector.customclass;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemOrb extends Item {
   public ItemOrb() {
      this.setMaxDamage(2);
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      boolean used = false;

      for(int i = 0; i < 4; ++i) {
         ItemStack item = par3EntityPlayer.getCurrentArmor(i);
         if(item != null && item.getItemDamage() - item.getMaxDamage() / 10 > 0) {
            item.setItemDamage(item.getItemDamage() - item.getMaxDamage() / 10);
            used = true;
         }
      }

      if(used) {
         par1ItemStack.damageItem(1, par3EntityPlayer);
      }

      return par1ItemStack;
   }

   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
      super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
      par3List.add(EnumChatFormatting.AQUA + "Permet de r\u00e9parer les stuffs !");
      par3List.add(EnumChatFormatting.AQUA + "utilisable 3 fois");
   }

   public boolean hasEffect(ItemStack stack) {
      return true;
   }
}
