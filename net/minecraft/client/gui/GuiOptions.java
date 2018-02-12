package net.minecraft.client.gui;

import fr.thisismac.injector.bettersprintmod.GuiSprint;
import fr.thisismac.injector.customclass.GuiObsiFight;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.IStream;

public class GuiOptions extends GuiScreen implements GuiYesNoCallback {
   private static final GameSettings.Options[] field_146440_f = new GameSettings.Options[]{GameSettings.Options.FOV, GameSettings.Options.DIFFICULTY};
   private final GuiScreen field_146441_g;
   private final GameSettings field_146443_h;
   protected String field_146442_a = "Options";
   private static final String __OBFID = "CL_00000700";
   private static final String __OBFID = "CL_00000700";

   public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
      this.field_146441_g = p_i1046_1_;
      this.field_146443_h = p_i1046_2_;
   }

   public void initGui() {
      boolean var1 = false;
      this.field_146442_a = I18n.format("options.title", new Object[0]);
      this.buttonList.add(new GuiOptionSlider(GameSettings.Options.FOV.returnEnumOrdinal(), this.width / 2 - 155, this.height / 6 + 72 - 6, GameSettings.Options.FOV));
      this.buttonList.add(new GuiButton(106, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
      this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.video", new Object[0])));
      this.buttonList.add(new GuiButton(100, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.controls", new Object[0])));
      this.buttonList.add(new GuiButton(102, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.language", new Object[0])));
      this.buttonList.add(new GuiButton(103, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.multiplayer.title", new Object[0])));
      this.buttonList.add(new GuiButton(105, this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(201, this.width / 2 + 5, this.height / 6 + 144 - 6, 150, 20, I18n.format("Options ObsiFight", new Object[0])));
      this.buttonList.add(new GuiButton(202, this.width / 2 - 155, this.height / 6 + 45 - 6, 310, 20, I18n.format("Options BetterSprint", new Object[0])));
   }

   protected void actionPerformed(GuiButton p_146284_1_) {
      if(p_146284_1_.enabled) {
         if(p_146284_1_.id < 100 && p_146284_1_ instanceof GuiOptionButton) {
            this.field_146443_h.setOptionValue(((GuiOptionButton)p_146284_1_).func_146136_c(), 1);
            p_146284_1_.displayString = this.field_146443_h.getKeyBinding(GameSettings.Options.getEnumOptions(p_146284_1_.id));
         }

         if(p_146284_1_.id == 201) {
            mc.displayGuiScreen(new GuiObsiFight(this));
         }

         if(p_146284_1_.id == 8675309) {
            mc.entityRenderer.activateNextShader();
         }

         if(p_146284_1_.id == 202) {
            mc.displayGuiScreen(new GuiSprint(this));
         }

         if(p_146284_1_.id == 101) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiVideoSettings(this, this.field_146443_h));
         }

         if(p_146284_1_.id == 100) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiControls(this, this.field_146443_h));
         }

         if(p_146284_1_.id == 102) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiLanguage(this, this.field_146443_h, mc.getLanguageManager()));
         }

         if(p_146284_1_.id == 103) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new ScreenChatOptions(this, this.field_146443_h));
         }

         if(p_146284_1_.id == 104) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiSnooper(this, this.field_146443_h));
         }

         if(p_146284_1_.id == 200) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(this.field_146441_g);
         }

         if(p_146284_1_.id == 105) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiScreenResourcePacks(this));
         }

         if(p_146284_1_.id == 106) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.field_146443_h));
         }

         if(p_146284_1_.id == 107) {
            mc.gameSettings.saveOptions();
            IStream var2 = mc.func_152346_Z();
            if(var2.func_152936_l() && var2.func_152928_D()) {
               mc.displayGuiScreen(new GuiStreamOptions(this, this.field_146443_h));
            } else {
               GuiStreamUnavailable.func_152321_a(this);
            }
         }
      }
   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRendererObj, this.field_146442_a, this.width / 2, 15, 16777215);
      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
