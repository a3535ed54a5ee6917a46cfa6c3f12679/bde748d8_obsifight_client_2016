package fr.thisismac.injector.bettersprintmod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class GuiSprint extends GuiScreen {
   private final GuiScreen parentScreen;
   private int buttonId = -1;
   private KeyBinding[] sprintBindings;
   private GuiButton btnDoubleTap;
   private GuiButton btnFlyBoost;
   private GuiButton btnAllDirs;
   private GuiButton btnDisableMod;

   public GuiSprint(GuiScreen parentScreen) {
      this.sprintBindings = new KeyBinding[]{ClientModManager.keyBindSprintToggle, ClientModManager.keyBindSneakToggle, ClientModManager.keyBindOptionsMenu};
      this.parentScreen = parentScreen;
   }

   public void initGui() {
      this.buttonList.clear();
      int left = this.getLeftColumnX();
      int top = this.height / 6;

      for(int a = 0; a < this.sprintBindings.length; ++a) {
         GuiOptionButton btn = new GuiOptionButton(a, left + a % 2 * 160, top + 24 * (a >> 1), 70, 20, this.getKeyCodeString(a));
         this.buttonList.add(btn);
         if((a == 0 || a == 1) && ClientModManager.isModDisabled()) {
            btn.enabled = false;
         }
      }

      this.buttonList.add(this.btnDoubleTap = new GuiButton(199, left, top + 72, 70, 20, ""));
      this.buttonList.add(this.btnAllDirs = new GuiButton(198, left + 160, top + 72, 70, 20, ""));
      this.buttonList.add(this.btnFlyBoost = new GuiButton(197, left, top + 96, 70, 20, ""));
      this.buttonList.add(this.btnDisableMod = new GuiButton(196, left + 160, top + 96, 70, 20, ""));
      if(ClientModManager.isModDisabled()) {
         this.btnDoubleTap.enabled = false;
      }

      if(!ClientModManager.canRunInAllDirs(mc)) {
         this.btnAllDirs.enabled = false;
      }

      if(!ClientModManager.canBoostFlying(mc)) {
         this.btnFlyBoost.enabled = false;
      }

      if(mc.thePlayer != null || mc.theWorld != null) {
         this.btnDisableMod.enabled = false;
      }

      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, top + 168, this.parentScreen == null?98:200, 20, I18n.format("gui.done", new Object[0])));
      if(this.parentScreen == null) {
         this.buttonList.add(new GuiButton(190, this.width / 2 + 2, top + 168, 98, 20, I18n.format("options.controls", new Object[0])));
      }

      this.updateButtons();
   }

   private void updateButtons() {
      this.btnDoubleTap.displayString = ClientModManager.isModDisabled()?"Indisponible":(ClientSettings.enableDoubleTap?"Activ\u00e9":"D\u00e9sactiv\u00e9");
      this.btnFlyBoost.displayString = ClientModManager.canBoostFlying(mc)?(ClientSettings.flySpeedBoost == 0?"Disabled":ClientSettings.flySpeedBoost + 1 + "x"):"Indisponible";
      this.btnAllDirs.displayString = ClientModManager.canRunInAllDirs(mc)?(ClientSettings.enableAllDirs?"Activ\u00e9":"D\u00e9sactiv\u00e9"):"Indisponible";
      this.btnDisableMod.displayString = ClientModManager.isModDisabled()?"Oui":"Non";
   }

   protected void actionPerformed(GuiButton btn) {
      for(int a = 0; a < this.sprintBindings.length; ++a) {
         ((GuiButton)this.buttonList.get(a)).displayString = this.getKeyCodeString(a);
      }

      switch(btn.id) {
      case 190:
         mc.displayGuiScreen(new GuiControls(this, mc.gameSettings));
         break;
      case 191:
      case 192:
      case 193:
      case 194:
      case 195:
      default:
         this.buttonId = btn.id;
         btn.displayString = "> " + GameSettings.getKeyDisplayString(mc.gameSettings.keyBindings[btn.id].getKeyCode()) + " <";
         break;
      case 196:
         if(ClientModManager.inMenu(mc)) {
            ClientSettings.disableMod = !ClientSettings.disableMod;
            this.initGui();
         }
         break;
      case 197:
         if(ClientModManager.canBoostFlying(mc) && ++ClientSettings.flySpeedBoost == 8) {
            ClientSettings.flySpeedBoost = 0;
         }
         break;
      case 198:
         if(ClientModManager.canRunInAllDirs(mc)) {
            ClientSettings.enableAllDirs = !ClientSettings.enableAllDirs;
         }
         break;
      case 199:
         if(!ClientSettings.disableMod) {
            ClientSettings.enableDoubleTap = !ClientSettings.enableDoubleTap;
         }
         break;
      case 200:
         ClientSettings.save();
         if(this.parentScreen == null) {
            mc.setIngameFocus();
         } else {
            mc.displayGuiScreen(this.parentScreen);
         }
      }

      this.updateButtons();
   }

   protected void mouseClicked(int mouseX, int mouseY, int button) {
      if(!this.handleInput(button - 100)) {
         super.mouseClicked(mouseX, mouseY, button);
      }
   }

   protected void keyTyped(char keyChar, int keyCode) {
      if(!this.handleInput(keyCode)) {
         super.keyTyped(keyChar, keyCode);
      }
   }

   private boolean handleInput(int keyId) {
      if(this.buttonId >= 0 && this.buttonId < 180) {
         this.sprintBindings[this.buttonId].setKeyCode(keyId);
         ((GuiButton)this.buttonList.get(this.buttonId)).displayString = this.getKeyCodeString(this.buttonId);
         this.buttonId = -1;
         KeyBinding.resetKeyBindingArrayAndHash();
         ClientSettings.save();
         return true;
      } else {
         return false;
      }
   }

   public void drawScreen(int mouseX, int mouseY, float partialTickTime) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRendererObj, "ObsiFight PvP", this.width / 2, 20, 16777215);
      int left = this.getLeftColumnX();
      int top = this.height / 6;
      int a = 0;

      int line;
      while(a < this.sprintBindings.length) {
         boolean btn = false;
         int info = 0;

         while(true) {
            if(info < this.sprintBindings.length) {
               if(info == a || this.sprintBindings[a].getKeyCode() != this.sprintBindings[info].getKeyCode()) {
                  ++info;
                  continue;
               }

               btn = true;
            }

            KeyBinding[] spl = mc.gameSettings.keyBindings;
            line = spl.length;

            for(int var11 = 0; var11 < line; ++var11) {
               KeyBinding binding = spl[var11];
               if(this.sprintBindings[a].getKeyCode() == binding.getKeyCode()) {
                  btn = true;
                  break;
               }
            }

            if(this.buttonId == a) {
               ((GuiButton)this.buttonList.get(a)).displayString = "\u00a7f> \u00a7e??? \u00a7f<";
            } else if(btn) {
               ((GuiButton)this.buttonList.get(a)).displayString = "\u00a7c" + this.getKeyCodeString(a);
            } else {
               ((GuiButton)this.buttonList.get(a)).displayString = this.getKeyCodeString(a);
            }

            this.drawString(this.fontRendererObj, this.sprintBindings[a].getKeyDescription(), left + a % 2 * 160 + 70 + 6, top + 24 * (a >> 1) + 7, -1);
            ++a;
            break;
         }
      }

      this.drawButtonTitle("Double frappe", this.btnDoubleTap);
      this.drawButtonTitle("Toutes directions", this.btnAllDirs);
      this.drawButtonTitle("Vitesse FLY", this.btnFlyBoost);
      this.drawButtonTitle("D\u00e9sactiver le mod", this.btnDisableMod);

      label58:
      for(a = 0; a < this.buttonList.size(); ++a) {
         GuiButton var13 = (GuiButton)this.buttonList.get(a);
         if(var13.getHoverState(var13.field_146123_n) == 2) {
            String var14 = "";
            switch(a) {
            case 0:
               var14 = "Appuyez une fois pour commencer ou arr\u00eater le sprint.";
               break;
            case 1:
               var14 = "Appuyez une fois pour commencer ou arr\u00eater le sneak";
               break;
            case 2:
               var14 = "Touche pour ouvrir ce menu.";
               break;
            case 3:
               var14 = "Activer ou d\u00e9sactiver le sprint en appuyant deux fois sur la touche pour avancer.";
               break;
            case 4:
               var14 = "Sprint dans toutes les directions.";
               break;
            case 5:
               var14 = "kool\u2122.";
               break;
            case 6:
               var14 = "D\u00e9sactiver toutes les fonctionnalit\u00e9es non vanilla du mod.";
            }

            String[] var15 = var14.split("#");
            line = 0;

            while(true) {
               if(line >= var15.length) {
                  break label58;
               }

               this.drawCenteredString(this.fontRendererObj, var15[line], this.width / 2, top + 143 + 10 * line - this.fontRendererObj.FONT_HEIGHT * var15.length / 2, -1);
               ++line;
            }
         }
      }

      super.drawScreen(mouseX, mouseY, partialTickTime);
   }

   private int getLeftColumnX() {
      return this.width / 2 - 155;
   }

   private String getKeyCodeString(int i) {
      return GameSettings.getKeyDisplayString(this.sprintBindings[i].getKeyCode());
   }

   private void drawButtonTitle(String title, GuiButton btn) {
      this.drawString(this.fontRendererObj, title, btn.field_146128_h + 70 + 6, btn.field_146129_i + 7, -1);
   }
}
