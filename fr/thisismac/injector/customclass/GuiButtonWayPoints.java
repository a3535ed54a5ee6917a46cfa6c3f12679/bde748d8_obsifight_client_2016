package fr.thisismac.injector.customclass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButtonWayPoints extends GuiButton {
   private static final ResourceLocation texture = new ResourceLocation("textures/custom/waypoint.png");

   public GuiButtonWayPoints(int p_i1041_1_, int p_i1041_2_, int p_i1041_3_) {
      super(p_i1041_1_, p_i1041_2_, p_i1041_3_, 20, 20, "");
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      if(this.field_146125_m) {
         mc.getTextureManager().bindTexture(texture);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         boolean var4 = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
         int var5 = 106;
         if(var4) {
            var5 += this.field_146121_g;
         }

         this.drawTexturedModalRect(this.field_146128_h, this.field_146129_i, 0, var5, this.field_146120_f, this.field_146121_g);
      }
   }
}
