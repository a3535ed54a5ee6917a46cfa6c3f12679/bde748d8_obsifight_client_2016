package fr.thisismac.injector.bettersprintmod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import net.minecraft.client.Minecraft;

public class ClientSettings {
   private static File optionsFile;
   public static byte flySpeedBoost = 3;
   public static boolean enableDoubleTap = true;
   public static boolean enableAllDirs = false;
   public static boolean disableMod = false;

   public ClientSettings(Minecraft game) {
      optionsFile = new File(game.mcDataDir, "bettersprint.conf");
      load();
   }

   public static void load() {
      BufferedReader var1 = null;

      try {
         var1 = new BufferedReader(new FileReader(optionsFile));
      } catch (FileNotFoundException var31) {
         ;
      }

      String tmp = "";
      if(var1 != null) {
         try {
            while((tmp = var1.readLine()) != null) {
               String[] var3 = tmp.split(":");
               if(var3[0].equals(ClientModManager.keyBindSprintToggle.getKeyDescription())) {
                  ClientModManager.keyBindSprintToggle.setKeyCode(Integer.parseInt(var3[1]));
               } else if(var3[0].equals(ClientModManager.keyBindSneakToggle.getKeyDescription())) {
                  ClientModManager.keyBindSneakToggle.setKeyCode(Integer.parseInt(var3[1]));
               } else if(var3[0].equals(ClientModManager.keyBindOptionsMenu.getKeyDescription())) {
                  ClientModManager.keyBindOptionsMenu.setKeyCode(Integer.parseInt(var3[1]));
               } else if(var3[0].equals("flySpeedBoost")) {
                  flySpeedBoost = Byte.valueOf(var3[1]).byteValue();
               } else if(var3[0].equals("enableDoubleTap")) {
                  enableDoubleTap = Boolean.valueOf(var3[1]).booleanValue();
               } else if(var3[0].equals("enableAllDirs")) {
                  enableAllDirs = Boolean.valueOf(var3[1]).booleanValue();
               }
            }
         } catch (NumberFormatException var4) {
            ;
         } catch (IOException var5) {
            ;
         }
      }
   }

   public static void save() {
      PrintWriter out = null;

      try {
         out = new PrintWriter(new BufferedWriter(new FileWriter(optionsFile, false)));
      } catch (IOException var2) {
         ;
      }

      out.println(ClientModManager.keyBindSprintToggle.getKeyDescription() + ":" + ClientModManager.keyBindSprintToggle.getKeyCode());
      out.println(ClientModManager.keyBindSneakToggle.getKeyDescription() + ":" + ClientModManager.keyBindSneakToggle.getKeyCode());
      out.println(ClientModManager.keyBindOptionsMenu.getKeyDescription() + ":" + ClientModManager.keyBindOptionsMenu.getKeyCode());
      out.println("flySpeedBoost:" + flySpeedBoost);
      out.println("enableDoubleTap:" + enableDoubleTap);
      out.println("enableAllDirs:" + enableAllDirs);
      out.flush();
      out.close();
   }
}
