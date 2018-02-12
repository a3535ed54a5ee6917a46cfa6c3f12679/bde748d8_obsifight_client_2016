package net.minecraft.client.gui;

import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

public class GuiIngameMenu extends GuiScreen {
   private int field_146445_a;
   private int field_146444_f;
   private static final String __OBFID = "CL_00000703";
   private static final String __OBFID = "CL_00000703";

   public void initGui() {
      this.field_146445_a = 0;
      this.buttonList.clear();
      byte var1 = -16;
      boolean var2 = true;
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, I18n.format("menu.returnToMenu", new Object[0])));
      if(!mc.isIntegratedServerRunning()) {
         ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
      }

      this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, I18n.format("menu.returnToGame", new Object[0])));
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
      GuiButton var3;
      this.buttonList.add(var3 = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
      this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements", new Object[0])));
      this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.stats", new Object[0])));
      var3.enabled = mc.isSingleplayer() && !mc.getIntegratedServer().getPublic();
   }

   protected void actionPerformed(GuiButton p_146284_1_) {
      switch(p_146284_1_.id) {
      case 0:
         mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
         break;
      case 1:
         p_146284_1_.enabled = false;
         mc.theWorld.sendQuittingDisconnectingPacket();
         mc.loadWorld((WorldClient)null);
         mc.displayGuiScreen(new GuiMainMenu());
      case 2:
      case 3:
      default:
         break;
      case 4:
         mc.displayGuiScreen((GuiScreen)null);
         mc.setIngameFocus();
         break;
      case 5:
         mc.displayGuiScreen(new GuiAchievements(this, mc.thePlayer.func_146107_m()));
         break;
      case 6:
         mc.displayGuiScreen(new GuiStats(this, mc.thePlayer.func_146107_m()));
         break;
      case 7:
         mc.displayGuiScreen(new GuiShareToLan(this));
      }
   }

   public void updateScreen() {
      super.updateScreen();
      ++this.field_146444_f;
   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
