package fr.thisismac.injector.customclass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButtonAnimated extends GuiButton {
   private static final ResourceLocation texture = new ResourceLocation("textures/gui/button_animated.png");
   private int offset = 0;
   private long lastTime;

   public GuiButtonAnimated(int p_i46323_1_, int p_i46323_2_, int p_i46323_3_, int p_i46323_4_, int p_i46323_5_, String p_i46323_6_) {
      super(p_i46323_1_, p_i46323_2_, p_i46323_3_, p_i46323_4_, p_i46323_5_, p_i46323_6_);
   }

   public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      if(this.field_146125_m) {
         FontRenderer var4 = p_146112_1_.fontRenderer;
         p_146112_1_.getTextureManager().bindTexture(texture);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
         int var5 = this.getHoverState(this.field_146123_n);
         if(var5 > 1) {
            long var6 = System.currentTimeMillis();
            if(var6 - this.lastTime > 150L) {
               this.lastTime = var6;
               this.updateOffset();
            }
         } else {
            this.offset = 0;
         }

         GL11.glEnable(GL11.GL_BLEND);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         this.drawTexturedModalRect(this.field_146128_h, this.field_146129_i, 0, var5 > 1?this.offset:0, this.field_146120_f / 2, this.field_146121_g);
         this.drawTexturedModalRect(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, var5 > 1?this.offset:0, this.field_146120_f / 2, this.field_146121_g);
         this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
         int var61 = 14737632;
         if(!this.enabled) {
            var61 = 10526880;
         } else if(this.field_146123_n) {
            var61 = 16777120;
         }

         this.drawCenteredString(var4, this.displayString, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, var61);
      }
   }

   private void updateOffset() {
      this.offset += 20;
      if(this.offset > 120) {
         this.offset = 20;
      }
   }
}
