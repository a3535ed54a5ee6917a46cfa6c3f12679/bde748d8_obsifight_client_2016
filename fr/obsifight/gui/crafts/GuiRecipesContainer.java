package fr.obsifight.gui.crafts;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GuiRecipesContainer extends Container {
   public List GuiCraft_1eyc9rub2y = new ArrayList();
   public GuiRecipesInventory GuiCraft_sgkr6x0s4q;
   public GuiRecipesInventory GuiCraft_z59bsdf26t;

   public GuiRecipesContainer(EntityPlayer var1) {
      byte var2 = 0;
      var1.contCrafts = this;
      this.GuiCraft_sgkr6x0s4q = new GuiRecipesInventory("ref", 1);
      this.GuiCraft_z59bsdf26t = new GuiRecipesInventory("crafts", 9);
      this.addSlotToContainer(new GuiCraftSlots2(var1, this.GuiCraft_sgkr6x0s4q, 0, 128, 139));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 0, 34, 121));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 1, 52, 121));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 2, 70, 121));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 3, 34, 139));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 4, 52, 139));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 5, 70, 139));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 6, 34, 157));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 7, 52, 157));
      this.addSlotToContainer(new GuiCraftSlots1(this.GuiCraft_z59bsdf26t, 8, 70, 157));
      InventoryPlayer var3 = var1.inventory;

      for(int var4 = 0; var4 < 5; ++var4) {
         for(int var5 = 0; var5 < 9; ++var5) {
            this.addSlotToContainer(new Slot(GuiCraft.GuiCraft_1eyc9rub2y(), var4 * 9 + var5, 9 + var5 * 18, 18 + var4 * 18 - var2));
         }
      }

      this.GuiCraft_76646zc22d(0.0F);
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return true;
   }

   public void GuiCraft_76646zc22d(float var1) {
      int var2 = this.GuiCraft_1eyc9rub2y.size() / 9 - 5 + 1;
      int var3 = (int)((double)var1 * 1.28D * (double)((float)var2) + 0.5D);
      if(var3 < 0) {
         var3 = 0;
      }

      for(int var4 = 0; var4 < 5; ++var4) {
         for(int var5 = 0; var5 < 9; ++var5) {
            int var6 = var5 + (var4 + var3) * 9;
            if(var6 >= 0 && var6 < this.GuiCraft_1eyc9rub2y.size()) {
               GuiCraft.GuiCraft_1eyc9rub2y().setInventorySlotContents(var5 + var4 * 9, (ItemStack)this.GuiCraft_1eyc9rub2y.get(var6));
            } else {
               GuiCraft.GuiCraft_1eyc9rub2y().setInventorySlotContents(var5 + var4 * 9, (ItemStack)null);
            }
         }
      }
   }

   public boolean GuiCraft_g75m285sa8() {
      return this.GuiCraft_1eyc9rub2y.size() > 45;
   }

   protected void retrySlotClick(int var1, int var2, boolean var3, EntityPlayer var4) {}

   public ItemStack GuiCraft_76646zc22d(EntityPlayer var1, int var2) {
      return null;
   }
}
