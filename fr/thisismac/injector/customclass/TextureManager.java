package fr.thisismac.injector.customclass;

import fr.thisismac.injector.Core;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class TextureManager implements IResourceManagerReloadListener {
   private List<String> pathToCheck = new ArrayList();

   public TextureManager() {
      this.pathToCheck.add("textures/blocks/stone.png");
      this.pathToCheck.add("textures/blocks/dirt.png");
      this.pathToCheck.add("textures/blocks/sand.png");
      this.pathToCheck.add("textures/blocks/grass_top.png");
      this.pathToCheck.add("textures/blocks/grass_side.png");
      this.pathToCheck.add("textures/blocks/compacted_cobblestone.png");
      this.pathToCheck.add("textures/blocks/gravel.png");
      this.pathToCheck.add("textures/blocks/obsidian.png");
   }

   public void onResourceManagerReload(IResourceManager resourceManager) {
      Iterator var2 = this.pathToCheck.iterator();

      while(var2.hasNext()) {
         String path = (String)var2.next();

         try {
            if(this.isContainsTransparentPixel(ImageIO.read(resourceManager.getResource(new ResourceLocation(path)).getInputStream()))) {
               Frame var10000 = new Frame();
               Core.get();
               JOptionPane.showMessageDialog(var10000, "Le cheat c\'est mal ...", "Obsifight", 0);
               Minecraft.getMinecraft().shutdown();
            }
         } catch (IOException var5) {
            ;
         }
      }
   }

   private boolean isContainsTransparentPixel(BufferedImage buff) {
      for(int x = 0; x < buff.getWidth(); ++x) {
         for(int y = 0; y < buff.getHeight(); ++y) {
            if((buff.getRGB(x, y) >> 24 & 255) != 255) {
               return true;
            }
         }
      }

      return false;
   }
}
