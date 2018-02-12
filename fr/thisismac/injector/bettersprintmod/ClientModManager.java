package fr.thisismac.injector.bettersprintmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public final class ClientModManager {
   public static KeyBinding keyBindSprintToggle = new KeyBinding("Activer le sprint", 34, "key.categories.movement");
   public static KeyBinding keyBindSneakToggle = new KeyBinding("Activer le sneak", 21, "key.categories.movement");
   public static KeyBinding keyBindOptionsMenu = new KeyBinding("Menu sprint", 24, "key.categories.movement");

   public static boolean inMenu(Minecraft mc) {
      return mc.thePlayer == null || mc.theWorld == null;
   }

   public static boolean canRunInAllDirs(Minecraft mc) {
      return !isModDisabled() && (inMenu(mc) || mc.isSingleplayer());
   }

   public static boolean canBoostFlying(Minecraft mc) {
      return !isModDisabled() && (inMenu(mc) || mc.isSingleplayer() || mc.thePlayer.capabilities.isCreativeMode);
   }

   public static boolean isModDisabled() {
      return ClientSettings.disableMod;
   }
}
