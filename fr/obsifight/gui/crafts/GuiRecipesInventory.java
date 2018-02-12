package fr.obsifight.gui.crafts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class GuiRecipesInventory implements IInventory {
   private String GuiCraft_46t6s5xmgn;
   private int GuiCraft_kzjl3492nt;
   private ItemStack[] GuiCraft_xr158qiy46;

   public GuiRecipesInventory(String var1, int var2) {
      this.GuiCraft_46t6s5xmgn = var1;
      this.GuiCraft_kzjl3492nt = var2;
      this.GuiCraft_xr158qiy46 = new ItemStack[var2];
   }

   public ItemStack getStackInSlot(int var1) {
      return this.GuiCraft_xr158qiy46[var1];
   }

   public ItemStack decrStackSize(int var1, int var2) {
      return null;
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      this.GuiCraft_xr158qiy46[var1] = null;
      return null;
   }

   public int getSizeInventory() {
      return this.GuiCraft_kzjl3492nt;
   }

   public String GuiCraft_921851xskg() {
      return this.GuiCraft_46t6s5xmgn;
   }

   public int getInventoryStackLimit() {
      return 0;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return false;
   }

   public ItemStack GuiCraft_9h444i05z9() {
      return GuiCraft_76646zc22d(this.GuiCraft_xr158qiy46[0]);
   }

   public static ItemStack GuiCraft_76646zc22d(ItemStack var0) {
      return var0 == null?null:var0;
   }

   public void GuiCraft_q8o484xbkz(ItemStack var1) {
      var1 = new ItemStack(var1.getItem(), 1, var1.getItemDamage());
      this.GuiCraft_xr158qiy46[0] = var1.copy();
   }

   public void GuiCraft_g9j7p8xs4z() {
      this.GuiCraft_xr158qiy46[0] = null;
   }

   public void GuiCraft_g75m285sa8(ItemStack var1) {
      this.GuiCraft_xr158qiy46[0] = var1;
   }

   public ItemStack GuiCraft_0l2z6vr0w3() {
      return this.GuiCraft_xr158qiy46[0];
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {}

   public void onInventoryChanged() {}

   public void GuiCraft_76646zc22d(int var1, ItemStack var2) {
      this.GuiCraft_xr158qiy46[var1] = null;
      this.GuiCraft_xr158qiy46[var1] = var2.copy();
   }

   public void GuiCraft_3cirau7j5p() {
      for(int var1 = 0; var1 < this.GuiCraft_kzjl3492nt; ++var1) {
         this.GuiCraft_xr158qiy46[var1] = null;
      }
   }

   public String getInventoryName() {
      return null;
   }

   public boolean isInventoryNameLocalized() {
      return false;
   }

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return false;
   }

   public void openInventory() {}

   public void closeInventory() {}
}
