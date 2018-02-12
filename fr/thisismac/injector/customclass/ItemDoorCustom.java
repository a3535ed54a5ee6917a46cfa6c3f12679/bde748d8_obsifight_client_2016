package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDoorCustom extends ItemDoor {
   private Material doorMaterial;

   public ItemDoorCustom(Material p_i45334_1_) {
      super(p_i45334_1_);
   }

   public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
      if(p_77648_7_ != 1) {
         return false;
      } else {
         ++p_77648_5_;
         Block var11 = Blocks.custom_door;
         if(p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_)) {
            if(!var11.canPlaceBlockAt(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_)) {
               return false;
            } else {
               int var12 = MathHelper.floor_double((double)((p_77648_2_.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
               func_150924_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, var12, var11);
               --p_77648_1_.stackSize;
               return true;
            }
         } else {
            return false;
         }
      }
   }
}
