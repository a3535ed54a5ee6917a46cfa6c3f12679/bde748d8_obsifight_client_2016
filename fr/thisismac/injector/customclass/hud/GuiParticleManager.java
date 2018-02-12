package fr.thisismac.injector.customclass.hud;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;

public class GuiParticleManager<T extends GuiParticle> {
   public final ArrayList<T> particles = new ArrayList();

   public void update(Minecraft mc) {
      Iterator iterator = this.particles.iterator();

      while(iterator.hasNext()) {
         if(((GuiParticle)iterator.next()).update(mc)) {
            iterator.remove();
         }
      }
   }

   public void renderAll(Minecraft mc) {
      Iterator var2 = this.particles.iterator();

      while(var2.hasNext()) {
         GuiParticle guiparticle = (GuiParticle)var2.next();
         guiparticle.render(mc);
      }
   }
}
