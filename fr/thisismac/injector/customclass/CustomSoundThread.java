package fr.thisismac.injector.customclass;

import java.net.MalformedURLException;
import java.net.URL;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;

public class CustomSoundThread implements Runnable {
   private BasicPlayer player = new BasicPlayer();
   public static boolean running = false;
   public static boolean isOpen = false;

   public void run() {
      while(true) {
         if(!running && isOpen) {
            try {
               this.player.stop();
               isOpen = false;
            } catch (BasicPlayerException var7) {
               var7.printStackTrace();
            }
         }

         running = running;

         while(running) {
            if(!isOpen) {
               try {
                  this.player.open(new URL("http://stream.obsifight.fr:8000/obsi.mp3"));
                  this.player.play();
                  isOpen = true;
               } catch (MalformedURLException var3) {
                  var3.printStackTrace();
               } catch (BasicPlayerException var4) {
                  var4.printStackTrace();
               }
            }

            try {
               this.player.setGain((double)Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.RADIO));
            } catch (BasicPlayerException var6) {
               var6.printStackTrace();
            }

            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var5) {
               var5.printStackTrace();
            }
         }

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }
      }
   }
}
