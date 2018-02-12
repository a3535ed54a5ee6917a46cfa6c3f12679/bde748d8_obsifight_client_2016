package fr.thisismac.injector.customclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class CustomSettings {
   public static KeyBinding keyBindArmorGui;
   public static KeyBinding keyBindFaction;
   public static KeyBinding keyBindMacro1;
   public static KeyBinding keyBindMacro2;
   public static KeyBinding keyBindMacro3;
   public static KeyBinding keyBindMacro4;
   public static KeyBinding keyBindRadio;
   public static KeyBinding test;
   public static String macro1 = "";
   public static String macro2 = "";
   public static String macro3 = "";
   public static String macro4 = "";
   static File optionsFile;
   static File macroFile;
   public KeyBinding[] keyBindings;

   public CustomSettings(Minecraft game) {
      keyBindArmorGui = new KeyBinding("key.armorgui", 88, "key.categories.multiplayer");
      keyBindFaction = new KeyBinding("key.faction", 33, "key.categories.multiplayer");
      keyBindRadio = new KeyBinding("key.radio", 0, "key.categories.multiplayer");
      test = new KeyBinding("key.test", 47, "key.categories.multiplayer");
      Object var1 = null;
      Object varMacro = null;
      optionsFile = new File(game.mcDataDir, "options.txt");

      try {
         new BufferedReader(new FileReader(optionsFile));
      } catch (FileNotFoundException var5) {
         ;
      }
   }

   public static String getSetting(String name) throws IOException {
      BufferedReader var1 = new BufferedReader(new FileReader(Minecraft.getMinecraft().gameSettings.optionsFile));
      String var2 = "";

      while((var2 = var1.readLine()) != null) {
         try {
            String[] var8 = var2.split(":");
            if(var8[0].equals(name)) {
               return var8.length > 1?var8[1]:"Rien";
            }
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

      var1.close();
      return "";
   }
}
