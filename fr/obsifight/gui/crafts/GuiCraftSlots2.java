package fr.obsifight.gui.crafts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GuiCraftSlots2 extends Slot {
   private EntityPlayer GuiCraft_a3ri944k56;
   private int GuiCraft_m24a7l36ar;

   public GuiCraftSlots2(EntityPlayer var1, GuiRecipesInventory var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.GuiCraft_a3ri944k56 = var1;
   }

   public boolean isItemValid(ItemStack var1) {
      return true;
   }

   public void GuiCraft_3rj490m27s(ItemStack var1) {}

   protected void onCrafting(ItemStack var1, int var2) {}

   protected void onCrafting(ItemStack var1) {}
}
