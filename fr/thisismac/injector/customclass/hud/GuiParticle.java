package fr.thisismac.injector.customclass.hud;

import net.minecraft.client.Minecraft;

public abstract class GuiParticle {
   public int x;
   public int y;

   public GuiParticle(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public abstract boolean update(Minecraft var1);

   public abstract void render(Minecraft var1);
}
