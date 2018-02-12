package fr.thisismac.injector.customclass.shop;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class Util {
   public static int canTakeThisAmount(int amount) {
      int needed = Math.round((float)amount / 64.0F + 0.5F);
      if(Minecraft.getMinecraft().thePlayer == null) {
         return -2;
      } else {
         int available = 0;
         ItemStack[] var3 = Minecraft.getMinecraft().thePlayer.inventory.mainInventory;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ItemStack is = var3[var5];
            if(is == null) {
               ++available;
            }
         }

         if(available < needed) {
            return available - needed;
         } else {
            return -1;
         }
      }
   }
}
