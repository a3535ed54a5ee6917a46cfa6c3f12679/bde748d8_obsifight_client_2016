package net.minecraft.client.gui;

import fr.thisismac.injector.customclass.CustomMainMenu;
import java.util.Iterator;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class GuiGameOver extends GuiScreen implements GuiYesNoCallback {
   private int field_146347_a;
   private boolean field_146346_f = false;
   private static final String __OBFID = "CL_00000690";
   private static final String __OBFID = "CL_00000690";

   public void initGui() {
      this.buttonList.clear();
      if(mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
         if(mc.isIntegratedServerRunning()) {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.deleteWorld", new Object[0])));
         } else {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.leaveServer", new Object[0])));
         }
      } else {
         this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, I18n.format("deathScreen.respawn", new Object[0])));
         this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.titleScreen", new Object[0])));
         if(mc.getSession() == null) {
            ((GuiButton)this.buttonList.get(1)).enabled = false;
         }
      }

      GuiButton var2;
      for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = false) {
         var2 = (GuiButton)var1.next();
      }
   }

   protected void keyTyped(char p_73869_1_, int p_73869_2_) {}

   protected void actionPerformed(GuiButton p_146284_1_) {
      switch(p_146284_1_.id) {
      case 0:
         mc.thePlayer.respawnPlayer();
         mc.displayGuiScreen((GuiScreen)null);
         break;
      case 1:
         GuiYesNo var2 = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm", new Object[0]), "", I18n.format("deathScreen.titleScreen", new Object[0]), I18n.format("deathScreen.respawn", new Object[0]), 0);
         mc.displayGuiScreen(var2);
         var2.func_146350_a(20);
      }
   }

   public void confirmClicked(boolean p_73878_1_, int p_73878_2_) {
      if(p_73878_1_) {
         mc.theWorld.sendQuittingDisconnectingPacket();
         mc.loadWorld((WorldClient)null);
         mc.displayGuiScreen(new CustomMainMenu());
      } else {
         mc.thePlayer.respawnPlayer();
         mc.displayGuiScreen((GuiScreen)null);
      }
   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
      GL11.glPushMatrix();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      boolean var4 = mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
      String var5 = var4?I18n.format("deathScreen.title.hardcore", new Object[0]):I18n.format("deathScreen.title", new Object[0]);
      this.drawCenteredString(this.fontRendererObj, var5, this.width / 2 / 2, 30, 16777215);
      GL11.glPopMatrix();
      if(var4) {
         this.drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]), this.width / 2, 144, 16777215);
      }

      this.drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.score", new Object[0]) + ": " + EnumChatFormatting.YELLOW + mc.thePlayer.getScore(), this.width / 2, 100, 16777215);
      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      ++this.field_146347_a;
      GuiButton var2;
      if(this.field_146347_a == 20) {
         for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = true) {
            var2 = (GuiButton)var1.next();
         }
      }
   }
}
