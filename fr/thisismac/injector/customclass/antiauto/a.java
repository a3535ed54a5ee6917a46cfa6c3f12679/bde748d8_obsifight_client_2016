package fr.thisismac.injector.customclass.antiauto;

import fr.thisismac.injector.customclass.GuiCheatDetected;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;

public class a extends Thread {
   private static Minecraft mine;
   private static List<String> nick = new ArrayList();
   private static HashSet ac = new HashSet(32);
   private static final Pattern PI = Pattern.compile(",");
   public static int clics = 0;
   public static int clicsMax = 55;
   public static long lStartTime = System.nanoTime();
   private static Boolean contain = Boolean.valueOf(false);
   private static List<String> ac2 = new ArrayList();

   private static int pi(BufferedImage par1BufferedImage) {
      int pixnb = 0;
      int w = par1BufferedImage.getWidth();
      int h = par1BufferedImage.getHeight();

      for(int x = 0; x < w; ++x) {
         for(int y = 0; y < h; ++y) {
            int rgb = par1BufferedImage.getRGB(x, y);
            int alpha = rgb >> 24 & 255;
            if(alpha != 255) {
               ++pixnb;
            }
         }
      }

      return pixnb;
   }

   public a(Minecraft mine2) {
      mine = mine2;
      nick.add("matth72240");
      nick.add("alidu917");
      nick.add("guigui91");
      nick.add("goldwarrior58");
      nick.add("blacklagon");
      nick.add("thedarksky");
      if(nick.contains(mine.session.getUsername().toLowerCase())) {
         contain = Boolean.valueOf(true);
      }

      ac.add("\"zenkey.exe\"");
      ac.add("\"wonderkeys.exe\"");
      ac.add("\"autohotkey.exe\"");
      ac.add("\"autoclick2.2.exe\"");
      ac.add("\"autoclique.exe\"");
      ac.add("\"auto-clique.exe\"");
      ac.add("\"auto-clicker.exe\"");
      ac.add("\"autoclicker.exe\"");
      ac.add("\"autocliqueur.exe\"");
      ac.add("\"cheatengine-i386.exe\"");
      ac.add("\"cheatengine-x86_64.exe\"");
      ac.add("\"autoclic.exe\"");
      ac.add("\"autoclick.exe\"");
      ac.add("\"autoklick.exe\"");
      ac.add("\"auto-klick.exe\"");
      ac.add("\"klick.exe\"");
      ac.add("\"click.exe\"");
      ac.add("\"clic.exe\"");
      ac.add("\"clicker.exe\"");
      ac.add("\"cliqueur.exe\"");
      ac.add("\"auto-clic.exe\"");
      ac.add("\"auto-click.exe\"");
      ac.add("\"cheat engine.exe\"");
      ac.add("\"autoclick.exe\"");
      ac.add("\"superrapidfire.exe\"");
      ac.add("\"cheatengine-x86_64.exe\"");
      ac.add("\"cheatengine-i386.exe\"");
      ac.add("\"Cheat Engine.exe\"");
      ac.add("\"cheat engine 6.3.exe\"");
      ac.add("\"cheat engine 6.2.exe\"");
      ac.add("\"cheat engine 6.1.exe\"");
      ac.add("\"cheat engine 6.0.exe\"");
      ac.add("\"cheat engine 5.6.1.exe\"");
      ac.add("\"cheat engine 5.6.exe\"");
      ac.add("\"smarttechnology.exe\"");
      ac.add("\"autoclick by armax2001.exe\"");
      ac.add("\"autoclickbyarmax2001.exe\"");
      ac.add("\"autoclick by armax2001.exe*32\"");
      ac.add("\"speed.exe\"");
      ac.add("\"speedhack.exe\"");
      ac.add("\"speeder.exe\"");
      ac.add("\"speedcheat.exe\"");
      ac.add("\"cheatspeed.exe\"");
      ac2.add("textures/blocks/stone.png");
      ac2.add("textures/blocks/dirt.png");
      ac2.add("textures/blocks/gravel.png");
      ac2.add("textures/blocks/sand.png");
      ac2.add("textures/blocks/obsidian.png");
      ac2.add("textures/blocks/grass_side.png");
      ac2.add("textures/blocks/grass_top.png");
      ac2.add("textures/blocks/baseProtector.png");
      ac2.add("textures/blocks/netherrack.png");
      ac2.add("textures/blocks/clay.png");
   }

   public void run() {
      TimerTask timerTaskSec;
      Timer timerSec;
      if(!contain.booleanValue()) {
         timerTaskSec = new TimerTask() {
            public void run() {
               a.check();
            }
         };
         timerSec = new Timer("AllFight2");
         timerSec.scheduleAtFixedRate(timerTaskSec, 30000L, 8000L);
      }

      timerTaskSec = new TimerTask() {
         public void run() {
            a.checkClics();
         }
      };
      timerSec = new Timer("AllFight3");
      timerSec.scheduleAtFixedRate(timerTaskSec, 1000L, 1000L);
   }

   public static void check() {
      String check = li();
      if(check.equalsIgnoreCase("true") && !(mine.currentScreen instanceof GuiCheatDetected)) {
         mine.displayGuiScreen(new GuiCheatDetected());
      }
   }

   public static void checkClics() {
      long time = System.nanoTime();
      if((time - lStartTime) / 1000000000L >= 3L) {
         if(clics >= clicsMax) {
            if(!(mine.currentScreen instanceof GuiCheatDetected)) {
               mine.displayGuiScreen(new GuiCheatDetected());
            }
         } else {
            clics = 0;
         }

         lStartTime = time;
      }
   }

   private static String li() {
      if(Util.getOSType() == Util.EnumOS.WINDOWS) {
         InputStreamReader localInputStreamReader = null;
         BufferedReader localBufferedReader = null;

         try {
            ProcessBuilder var18 = new ProcessBuilder(new String[]{"tasklist.exe", "/fo", "csv", "/nh"});
            var18.redirectErrorStream(true);
            Process localIOException5 = var18.start();
            OutputStream out = localIOException5.getOutputStream();
            out.write("".getBytes());
            out.flush();
            out.close();
            localInputStreamReader = new InputStreamReader(localIOException5.getInputStream());
            localBufferedReader = new BufferedReader(localInputStreamReader);

            String ex;
            String var6;
            String var8;
            do {
               if((ex = localBufferedReader.readLine()) == null) {
                  var8 = "false";
                  return var8;
               }

               if(ex.toLowerCase().trim().contains("password") || ex.toLowerCase().trim().contains("passe") || ex.toLowerCase().trim().contains("guest") || ex.toLowerCase().trim().contains("invit\u00e9")) {
                  var6 = "exception";
                  var8 = var6;
                  return var8;
               }
            } while(!ac.contains(PI.split(ex)[0].toLowerCase()));

            var6 = "true";
            var8 = var6;
            return var8;
         } catch (IOException var20) {
            String pb = "exception";
            return pb;
         } finally {
            try {
               localBufferedReader.close();
               localInputStreamReader.close();
            } catch (IOException var19) {
               ;
            }
         }
      } else {
         return "false";
      }
   }
}
