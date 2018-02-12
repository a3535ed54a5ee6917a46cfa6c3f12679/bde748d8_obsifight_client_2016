package fr.thisismac.injector.customclass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class CustomGuiButton extends GuiButton {
   public CustomGuiButton(int i, int j, int k, String s) {
      this(i, j, k, 120, 20, s);
   }

   public CustomGuiButton(int i, int j, int k, int l, int i1, String s) {
      super(i, j, k, l, i1, s);
   }

   public void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
      drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
      drawRect(x + size, y + size, x1, y, borderC);
      drawRect(x, y, x + size, y1, borderC);
      drawRect(x1, y1, x1 - size, y + size, borderC);
      drawRect(x, y1 - size, x1, y1, borderC);
   }

   public int getHoverState(boolean flag) {
      byte byte0 = 1;
      if(!this.enabled) {
         byte0 = 0;
      } else if(flag) {
         byte0 = 2;
      }

      return byte0;
   }

   public void drawButton(Minecraft mc, int mx, int my) {
      FontRenderer fontrenderer = mc.fontRenderer;
      boolean flag = mx >= this.field_146128_h && my >= this.field_146129_i && mx < this.field_146128_h + this.field_146120_f && my < this.field_146129_i + this.field_146121_g;
      if(flag) {
         this.drawBorderedRect(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, 1, -1862270977, Integer.MIN_VALUE);
         this.drawCenteredString(fontrenderer, this.displayString, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, -1);
      } else {
         this.drawBorderedRect(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, 1, -1862270977, 1610612736);
         this.drawCenteredString(fontrenderer, this.displayString, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, -3355444);
      }
   }
}
