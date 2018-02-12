package fr.thisismac.injector.customclass.craft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerWorkSurface extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 5);
   public IInventory craftResult = new InventoryCraftResult();
   private World worldObj;
   private int posX;
   private int posY;
   private int posZ;

   public ContainerWorkSurface(InventoryPlayer invPlayer, World world, int x, int y, int z) {
      this.worldObj = world;
      this.posX = x;
      this.posY = y;
      this.posZ = z;
      this.addSlotToContainer(new SlotCrafting(invPlayer.player, this.craftMatrix, this.craftResult, 0, 141, 43));

      int i;
      int k;
      for(i = 0; i < 5; ++i) {
         for(k = 0; k < 5; ++k) {
            this.addSlotToContainer(new Slot(this.craftMatrix, k + i * 5, 8 + k * 18, 7 + i * 18));
         }
      }

      for(i = 0; i < 3; ++i) {
         for(k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(invPlayer, k + i * 9 + 9, 8 + k * 18, 106 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 164));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   public void onCraftMatrixChanged(IInventory iiventory) {
      this.craftResult.setInventorySlotContents(0, WorkSurfaceCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.worldObj.getBlock(this.posX, this.posY, this.posZ) != Blocks.WorkSurface?false:player.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
   }

   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
      super.onContainerClosed(par1EntityPlayer);
      if(!this.worldObj.isClient) {
         for(int i = 0; i < 9; ++i) {
            ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);
            if(itemstack != null) {
               par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
            }
         }
      }
   }

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.inventorySlots.get(par2);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(par2 == 0) {
            if(!this.mergeItemStack(itemstack1, 10, 46, true)) {
               return null;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if(par2 >= 10 && par2 < 37) {
            if(!this.mergeItemStack(itemstack1, 37, 46, false)) {
               return null;
            }
         } else if(par2 >= 37 && par2 < 46) {
            if(!this.mergeItemStack(itemstack1, 10, 37, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(itemstack1, 10, 46, false)) {
            return null;
         }

         if(itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if(itemstack1.stackSize == itemstack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
      }

      return itemstack;
   }
}
