package fr.thisismac.injector.customclass;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.thisismac.injector.bettersprintmod.ClientModManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiObsiFight extends GuiScreen {
   private final GuiScreen parentScreen;
   private int buttonId = -1;
   private GuiButton f3mode;
   private GuiButton showArmure;
   private GuiButton showCords;
   private GuiButton showEffect;
   private GuiButton showFPS;
   private GuiButton showFacing;
   private GuiButton armorPos;
   private GuiButton effectPos;
   private GuiButton facingPos;
   private GuiButton armorStyle;
   private GuiButton protectArmor;
   private GuiButton showDeath;
   GuiScreenOptionsSounds ras;

   public GuiObsiFight(GuiScreen parentScreen) {
      this.ras = new GuiScreenOptionsSounds(this, mc.gameSettings);
      this.parentScreen = parentScreen;
   }

   public void initGui() {
      this.buttonList.clear();
      int left = this.getLeftColumnX();
      int top = this.height / 6;
      int right = this.getRightColumnX();
      int center = this.width / 2;
      this.buttonList.add(this.f3mode = new CustomGuiButton(199, left, top / 2, 140, 20, ""));
      this.buttonList.add(this.armorStyle = new CustomGuiButton(205, left, top / 2 + 23, 140, 20, ""));
      this.buttonList.add(this.protectArmor = new CustomGuiButton(206, left, top / 2 + 46, 140, 20, ""));
      this.buttonList.add(this.showFacing = new CustomGuiButton(204, left, top / 2 + 69, 140, 20, ""));
      this.buttonList.add(this.showArmure = new CustomGuiButton(200, right, top / 2, 140, 20, ""));
      this.buttonList.add(this.showCords = new CustomGuiButton(201, right, top / 2 + 23, 140, 20, ""));
      this.buttonList.add(this.showEffect = new CustomGuiButton(202, right, top / 2 + 46, 140, 20, ""));
      this.buttonList.add(this.showFPS = new CustomGuiButton(203, right, top / 2 + 69, 140, 20, ""));
      this.buttonList.add(this.armorPos = new CustomGuiButton(207, left - 35, top / 2 + 138, 180, 20, ""));
      this.buttonList.add(this.effectPos = new CustomGuiButton(208, right - 10, top / 2 + 138, 180, 20, ""));
      this.buttonList.add(this.facingPos = new CustomGuiButton(209, right - 10, top / 2 + 170, 180, 20, ""));
      this.buttonList.add(this.showDeath = new CustomGuiButton(210, left - 35, top / 2 + 170, 180, 20, ""));
      if(ClientModManager.isModDisabled()) {
         this.f3mode.enabled = false;
      }

      this.buttonList.add(new CustomGuiButton(300, this.width / 2 - 100, top + 168, this.parentScreen == null?98:200, 20, I18n.format("gui.done", new Object[0])));
      this.updateButtons();
   }

   private void updateButtons() {
      String armorv = "";
      String affichv = "";
      String armorposv = "";
      String effectposv = "";
      String facingposv = "";
      switch(GameSettings.protectarmor_value) {
      case 0:
         armorv = "0";
         break;
      case 1:
         armorv = "1";
         break;
      case 2:
         armorv = "2";
         break;
      case 3:
         armorv = "3";
      }

      switch(GameSettings.affichage) {
      case 0:
         affichv = "0";
         break;
      case 1:
         affichv = "1";
         break;
      case 2:
         affichv = "2";
      }

      switch(GameSettings.armorPos) {
      case 0:
         armorposv = "Haut-Gauche";
         break;
      case 1:
         armorposv = "Haut-Droit";
         break;
      case 2:
         armorposv = "Bas-Droit";
      }

      switch(GameSettings.effectPos) {
      case 0:
         effectposv = "Haut-Gauche";
         break;
      case 1:
         effectposv = "Haut-Droite";
      }

      switch(GameSettings.facingPos) {
      case 0:
         facingposv = "Haut-Droite";
         break;
      case 1:
         facingposv = "Top-Milieu";
         break;
      case 2:
         facingposv = "Haut-Gauche";
      }

      this.f3mode.displayString = "\u00a7eStyle du F3 : \u00a77" + affichv;
      this.showArmure.displayString = "\u00a7eArmures: \u00a77" + (GameSettings.showArmor?"ON":"OFF");
      this.showCords.displayString = "\u00a7eCoordon\u00e9es: \u00a77" + (GameSettings.showcoords?"ON":"OFF");
      this.showEffect.displayString = "\u00a7eEffects: \u00a77" + (GameSettings.showEffect?"ON":"OFF");
      this.showFPS.displayString = "\u00a7eFPS: \u00a77" + (GameSettings.showfps?"ON":"OFF");
      this.showFacing.displayString = "\u00a7eDirection: \u00a77" + (GameSettings.showfacing?"ON":"OFF");
      this.armorStyle.displayString = "\u00a7eAffichage armures: \u00a77" + (GameSettings.armorstyle?"Chiffre":"%");
      this.protectArmor.displayString = "\u00a7eBarre d\'armures: \u00a77" + armorv;
      this.armorPos.displayString = "\u00a7ePosition armorHUD: \u00a77" + armorposv;
      this.effectPos.displayString = "\u00a7ePosition effectHUD: \u00a77" + effectposv;
      this.facingPos.displayString = "\u00a7ePosition direction: \u00a77" + facingposv;
      this.showDeath.displayString = "\u00a7eMessage de mort: \u00a77" + (GameSettings.showDeathMessage?"ON":"OFF");
   }

   protected void actionPerformed(GuiButton btn) {
      switch(btn.id) {
      case 199:
         ++GameSettings.affichage;
         if(GameSettings.affichage > 2) {
            GameSettings.affichage = 0;
         }
         break;
      case 200:
         GameSettings.showArmor = !GameSettings.showArmor;
         mc.gameSettings.saveOptions();
         break;
      case 201:
         GameSettings.showcoords = !GameSettings.showcoords;
         mc.gameSettings.saveOptions();
         break;
      case 202:
         GameSettings.showEffect = !GameSettings.showEffect;
         mc.gameSettings.saveOptions();
         break;
      case 203:
         GameSettings.showfps = !GameSettings.showfps;
         mc.gameSettings.saveOptions();
         break;
      case 204:
         GameSettings.showfacing = !GameSettings.showfacing;
         mc.gameSettings.saveOptions();
         break;
      case 205:
         GameSettings.armorstyle = !GameSettings.armorstyle;
         mc.gameSettings.saveOptions();
         break;
      case 206:
         ++GameSettings.protectarmor_value;
         if(GameSettings.protectarmor_value > 3) {
            GameSettings.protectarmor_value = 0;
         }

         mc.gameSettings.saveOptions();
         break;
      case 207:
         ++GameSettings.armorPos;
         if(GameSettings.armorPos > 2) {
            GameSettings.armorPos = 0;
         }

         mc.gameSettings.saveOptions();
         break;
      case 208:
         ++GameSettings.effectPos;
         if(GameSettings.effectPos > 1) {
            GameSettings.effectPos = 0;
         }

         mc.gameSettings.saveOptions();
         break;
      case 209:
         ++GameSettings.facingPos;
         if(GameSettings.facingPos > 2) {
            GameSettings.facingPos = 0;
         }

         mc.gameSettings.saveOptions();
         break;
      case 210:
         GameSettings.showDeathMessage = !GameSettings.showDeathMessage;
         mc.gameSettings.saveOptions();
         break;
      case 300:
         if(this.parentScreen == null) {
            mc.setIngameFocus();
         } else {
            mc.displayGuiScreen(this.parentScreen);
         }

         mc.gameSettings.saveOptions();
         break;
      default:
         this.buttonId = btn.id;
         btn.displayString = "> " + GameSettings.getKeyDisplayString(mc.gameSettings.keyBindings[btn.id].getKeyCode()) + " <";
      }

      this.updateButtons();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTickTime) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.AQUA + "" + ChatFormatting.BOLD + ChatFormatting.UNDERLINE + "Gestion du GUI", this.width / 2, 10, 16777215);
      if(mc.gameSettings.guiScale == 1) {
         this.drawCenteredString(this.fontRendererObj, ChatFormatting.GREEN + "" + ChatFormatting.BOLD + ChatFormatting.UNDERLINE + "Disposition des \u00e9l\u00e9ments", this.width / 2, 185, 16777215);
      } else {
         this.drawCenteredString(this.fontRendererObj, ChatFormatting.GREEN + "" + ChatFormatting.BOLD + ChatFormatting.UNDERLINE + "Disposition des \u00e9l\u00e9ments", this.width / 2, 150, 16777215);
      }

      int left = this.getLeftColumnX();
      int top = this.height / 6;
      int right = this.getRightColumnX();

      label42:
      for(int a = 0; a < this.buttonList.size(); ++a) {
         GuiButton btn = (GuiButton)this.buttonList.get(a);
         if(btn.getHoverState(btn.field_146123_n) == 2) {
            String info = "";
            switch(a) {
            case 0:
               if(this.f3mode.displayString.equals("Style du F3 : 1")) {
                  info = "Le mode 1 affiche t\'int\u00e9gralit\u00e9 du F3.";
               } else if(this.f3mode.displayString.equals("Style du F3 : 2")) {
                  info = "Le mode 2 affiche l\'int\u00e9gralit\u00e9 du F3, mais cache le X et le Y";
               } else {
                  info = "Le moe 3 affiche l\'int\u00e9gralit\u00e9 du F3, mais cache le X, Y, Z et le biome.";
               }
               break;
            case 1:
               info = "Afficher ou non l\'armor HUD";
               break;
            case 2:
               info = "Afficher ou non le debug des coordonn\u00e9es";
            }

            String[] spl = info.split("#");
            int line = 0;

            while(true) {
               if(line >= spl.length) {
                  break label42;
               }

               this.drawCenteredString(this.fontRendererObj, spl[line], this.width / 2, top + 143 + 10 * line - this.fontRendererObj.FONT_HEIGHT * spl.length / 2, -1);
               ++line;
            }
         }
      }

      super.drawScreen(mouseX, mouseY, partialTickTime);
   }

   private int getLeftColumnX() {
      return this.width / 2 - 155;
   }

   private int getRightColumnX() {
      return this.width / 2 + 29;
   }

   private void drawButtonTitle(String title, GuiButton btn) {
      this.drawString(this.fontRendererObj, title, btn.field_146128_h + 70 + 6, btn.field_146129_i + 7, -1);
   }
}
