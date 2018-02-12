package fr.thisismac.injector.customclass;

import fr.thisismac.injector.customclass.shop.GuiSell;
import fr.thisismac.injector.customclass.shop.GuiShop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ChatComponentText;

public class NetworkTrigger {
   public static String textToRender = null;
   public static int timeToRender = 0;
   public static boolean timeInCombat = false;
   public static String msg_kill = null;
   public static String currentTerritory = null;

   public void onTerritoryMessage(String msg) {
      currentTerritory = msg.split(" ")[2];
   }

   public void onKill(String msg) {
      Minecraft.getMinecraft().thePlayer.sendChatMessage("test");
      Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(msg.replace("[KILLS]", "")));
   }

   public void onShaders(String msg) {
      String xd = msg.replace("[RE]", "").replace("\u00a7r", "").replace(" ", "");
      if(xd.equals("stop")) {
         EntityRenderer.theShaderGroup.deleteShaderGroup();
         EntityRenderer.theShaderGroup = null;
      } else if(OpenGlHelper.shadersSupported) {
         EntityRenderer.shaders(Integer.parseInt(xd));
      }
   }

   public void onCombatMessage(String msg) {
      timeInCombat = true;
   }

   public void onNoCombatMessage(String msg) {
      timeInCombat = false;
   }

   public void onBroadcastMessage(String msg) {
      textToRender = msg.replace("[A] ", "");
      timeToRender = 135;
   }

   public void onStreamEnable(String msg) {
      CustomSoundThread.running = true;
   }

   public void onStreamDisable(String msg) {
      CustomSoundThread.running = false;
   }

   public void onStreamSound(String msg) {
      Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.RADIO, 1.0F);
   }

   public void onObsiShop(String msg) {
      String d = msg.replace("[OBSISHOP]", "").replace("\u00a7r", "");
      String[] data = d.split(";");
      String type = data[0].toUpperCase();
      String item = data[1];
      String prix = data[2];
      String bal = data[3];
      if(type.equals("BUY")) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiShop(Minecraft.getMinecraft(), item, "1", bal, prix));
      } else if(type.equals("SELL")) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiSell(Minecraft.getMinecraft(), item, "1", bal, prix));
      } else {
         Minecraft.getMinecraft().thePlayer.sendChatMessage("type error");
      }
   }
}
