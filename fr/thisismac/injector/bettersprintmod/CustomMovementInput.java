package fr.thisismac.injector.bettersprintmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;

public class CustomMovementInput {
   public static boolean held = false;
   public static int stoptime = 0;
   public static boolean sprint;
   public static boolean sprintToggle;
   public static boolean sneakToggle;
   public static boolean hasToggledSprint;
   public static boolean hasToggledSneak;

   public static void update(Minecraft mc, MovementInputFromOptions options) {
      options.moveStrafe = 0.0F;
      options.moveForward = 0.0F;
      GameSettings settings = mc.gameSettings;
      if(settings.keyBindForward.getIsKeyPressed()) {
         ++options.moveForward;
      }

      if(settings.keyBindBack.getIsKeyPressed()) {
         --options.moveForward;
      }

      if(settings.keyBindLeft.getIsKeyPressed()) {
         ++options.moveStrafe;
      }

      if(settings.keyBindRight.getIsKeyPressed()) {
         --options.moveStrafe;
      }

      sprint = mc.gameSettings.keyBindSprint.getIsKeyPressed();
      if(!sprint) {
         if(!ClientModManager.isModDisabled() && ClientModManager.keyBindSprintToggle.getIsKeyPressed()) {
            if(!hasToggledSprint) {
               sprintToggle = !sprintToggle;
               hasToggledSprint = true;
            }
         } else {
            hasToggledSprint = false;
         }

         sprint = sprintToggle;
      } else {
         sprintToggle = false;
      }

      options.jump = settings.keyBindJump.getIsKeyPressed();
      options.sneak = settings.keyBindSneak.getIsKeyPressed();
      if(!options.sneak) {
         if(!ClientModManager.isModDisabled() && ClientModManager.keyBindSneakToggle.getIsKeyPressed()) {
            if(!hasToggledSneak) {
               sneakToggle = !sneakToggle;
               hasToggledSneak = true;
            }
         } else {
            hasToggledSneak = false;
         }

         options.sneak = sneakToggle;
      }

      if(options.sneak) {
         options.moveStrafe *= 0.3F;
         options.moveForward *= 0.3F;
      }
   }
}
