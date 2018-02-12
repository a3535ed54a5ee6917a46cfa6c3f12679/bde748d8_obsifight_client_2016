package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButton extends Gui {
   protected static final ResourceLocation field_146122_a = new ResourceLocation("textures/gui/widgets.png");
   protected int field_146120_f;
   protected int field_146121_g;
   public int field_146128_h;
   public int field_146129_i;
   public String displayString;
   public int id;
   public boolean enabled;
   public boolean field_146125_m;
   public boolean field_146123_n;
   private static final String __OBFID = "CL_00000668";
   private static final String __OBFID = "CL_00000668";

   public GuiButton(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_) {
      this(p_i1020_1_, p_i1020_2_, p_i1020_3_, 200, 20, p_i1020_4_);
   }

   public GuiButton(int p_i46323_1_, int p_i46323_2_, int p_i46323_3_, int p_i46323_4_, int p_i46323_5_, String p_i46323_6_) {
      this.field_146120_f = 200;
      this.field_146121_g = 20;
      this.enabled = true;
      this.field_146125_m = true;
      this.id = p_i46323_1_;
      this.field_146128_h = p_i46323_2_;
      this.field_146129_i = p_i46323_3_;
      this.field_146120_f = p_i46323_4_;
      this.field_146121_g = p_i46323_5_;
      this.displayString = p_i46323_6_;
   }

   public int getHoverState(boolean p_146114_1_) {
      byte var2 = 1;
      if(!this.enabled) {
         var2 = 0;
      } else if(p_146114_1_) {
         var2 = 2;
      }

      return var2;
   }

   public void drawButton(Minecraft mc, int mx, int my) {
      if(this.field_146125_m) {
         FontRenderer var4 = mc.fontRenderer;
         mc.getTextureManager().bindTexture(field_146122_a);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = mx >= this.field_146128_h && my >= this.field_146129_i && mx < this.field_146128_h + this.field_146120_f && my < this.field_146129_i + this.field_146121_g;
         int var5 = this.getHoverState(this.field_146123_n);
         GL11.glEnable(GL11.GL_BLEND);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         this.drawTexturedModalRect(this.field_146128_h, this.field_146129_i, 0, 46 + var5 * 20, this.field_146120_f / 2, this.field_146121_g);
         this.drawTexturedModalRect(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + var5 * 20, this.field_146120_f / 2, this.field_146121_g);
         this.mouseDragged(mc, mx, my);
         int var6 = 14737632;
         if(!this.enabled) {
            var6 = 10526880;
         } else if(this.field_146123_n) {
            var6 = 16777120;
         }

         this.drawCenteredString(var4, this.displayString, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, var6);
      }
   }

   protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {}

   public void mouseReleased(int p_146118_1_, int p_146118_2_) {}

   public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
      return this.enabled && this.field_146125_m && p_146116_2_ >= this.field_146128_h && p_146116_3_ >= this.field_146129_i && p_146116_2_ < this.field_146128_h + this.field_146120_f && p_146116_3_ < this.field_146129_i + this.field_146121_g;
   }

   public boolean func_146115_a() {
      return this.field_146123_n;
   }

   public void func_146111_b(int p_146111_1_, int p_146111_2_) {}

   public void func_146113_a(SoundHandler p_146113_1_) {
      p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public int func_146117_b() {
      return this.field_146120_f;
   }

   public int func_154310_c() {
      return this.field_146121_g;
   }
}
