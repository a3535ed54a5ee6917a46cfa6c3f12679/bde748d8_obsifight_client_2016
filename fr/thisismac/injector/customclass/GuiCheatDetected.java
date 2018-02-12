package fr.thisismac.injector.customclass;

import fr.thisismac.injector.Core;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiCheatDetected extends GuiScreen {
   public void initGui() {
      this.buttonList.clear();
      if(Core.nbt != 2) {
         GuiButton a;
         this.buttonList.add(a = new GuiButton(1, this.width / 2 - 155 + 160, this.height / 4 + 180 + 12, "J\'ai compris (" + (2 - Core.nbt) + ")"));
         a.enabled = Core.act;
      } else {
         Core.act = false;
         this.buttonList.add(new GuiButton(2, this.width / 2 - 155 + 160, this.height / 4 + 180 + 12, "Quitter ObsiFight"));
      }
   }

   protected void actionPerformed(GuiButton e) {
      if(e.id == 1) {
         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
         Core.act = false;
         ++Core.nbt;
      }

      if(e.id == 2) {
         System.exit(0);
         Minecraft.getMinecraft().shutdown();
      }
   }

   protected void keyTyped(char par1, int par2) {}

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRendererObj, "Alerte logiciel de triche d\u00e9tect\u00e9 !", this.width / 2, this.height / 4 + 20, 16777215);
      this.drawString(this.fontRendererObj, "Un logiciel de triche a \u00e9t\u00e9 d\u00e9tect\u00e9.", this.width / 2 - 140, this.height / 4 + 60 + 0, 10526880);
      this.drawString(this.fontRendererObj, "Merci de bien vouloir le d\u00e9sactiver", this.width / 2 - 140, this.height / 4 + 60 + 18, 10526880);
      this.drawString(this.fontRendererObj, "afin de pouvoir continuer \u00e0 jouer sur nos services.", this.width / 2 - 140, this.height / 4 + 60 + 27, 10526880);
      this.drawString(this.fontRendererObj, "Si vous pensez qu\'il s\'agit d\'une erreur, ", this.width / 2 - 140, this.height / 4 + 80 + 36, 10526880);
      this.drawString(this.fontRendererObj, "veuillez contacter un membre de l\'\u00e9quipe.", this.width / 2 - 140, this.height / 4 + 80 + 45, 10526880);
      this.drawString(this.fontRendererObj, "Le jitterclicking peut \u00e9galement \u00eatre la cause du probl\u00e8me.", this.width / 2 - 140, this.height / 4 + 60 + 80, 10526880);
      this.drawString(this.fontRendererObj, "Jouez de mani\u00e8re R\u00e9glementaire.", this.width / 2 - 140, this.height / 4 + 60 + 99 + 10, 10526880);
      Tessellator var4 = Tessellator.instance;
      short var5 = 274;
      int var6 = this.width / 2 - var5 / 2;
      boolean var7 = true;
      this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
      this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.drawScreen(par1, par2, par3);
   }
}
