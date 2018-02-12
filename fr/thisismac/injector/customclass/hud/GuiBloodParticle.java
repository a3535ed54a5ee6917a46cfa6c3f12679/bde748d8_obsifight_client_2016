package fr.thisismac.injector.customclass.hud;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

public class GuiBloodParticle extends GuiScreen {
   float opacity;
   float size;
   int rotation;
   int u;
   int v;
   public static Random rand = new Random();

   public GuiBloodParticle(int x, int y, float opacity, int rotation, float size, int u, int v) {
      this.opacity = opacity;
      this.size = size;
      this.rotation = rotation;
      this.u = u;
      this.v = v;
   }

   public static GuiBloodParticle random(int width, int height) {
      int i = rand.nextInt(width);
      int j = rand.nextInt(height);
      float f = rand.nextFloat() / 2.0F;
      int k = rand.nextInt(360);
      float f1 = 2.0F + rand.nextFloat() * 4.0F;
      int l = rand.nextInt(4);
      int i1 = rand.nextInt(4);
      return new GuiBloodParticle(i, j, f, k, f1, l, i1);
   }

   public void render(Minecraft mc) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, this.opacity);
      GL11.glTranslatef(8.0F, 9.0F, 0.0F);
      GL11.glRotatef((float)this.rotation, 0.0F, 0.0F, 1.0F);
      GL11.glScalef(this.size, this.size, 1.0F);
      mc.ingameGUI.drawTexturedModalRect(0, 0, this.u * 16, this.v * 16, 16, 16);
      GL11.glPopMatrix();
   }

   public boolean update(Minecraft mc) {
      return (this.opacity = (float)((double)this.opacity - 0.003D)) <= 0.0F;
   }
}
