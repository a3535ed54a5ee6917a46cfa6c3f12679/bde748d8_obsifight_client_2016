package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemEnderPearl extends Item {
   private static final String __OBFID = "CL_00000027";
   private static final String __OBFID = "CL_00000027";

   public ItemEnderPearl() {
      this.maxStackSize = 16;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      --p_77659_1_.stackSize;
      p_77659_2_.playSoundAtEntity(p_77659_3_, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if(!p_77659_2_.isClient) {
         p_77659_2_.spawnEntityInWorld(new EntityEnderPearl(p_77659_2_, p_77659_3_));
      }

      return p_77659_1_;
   }
}
