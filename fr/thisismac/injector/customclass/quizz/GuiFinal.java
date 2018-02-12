package fr.thisismac.injector.customclass.quizz;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiFinal extends GuiScreen {
   private int xSize = 230;
   private int ySize = 162;
   private short updateCounter = 0;
   protected String screenTitle = "Menu Personnaliser";
   protected GuiScreen guiScreen;
   private World world;

   public GuiFinal(Minecraft minecraft, World theWorld) {
      mc = minecraft;
      this.world = theWorld;
      this.xSize = 256;
      this.ySize = 256;
   }

   public void initGui() {
      StringTranslate stringtranslate = StringTranslate.getInstance();
      this.screenTitle = stringtranslate.translateKey("Menu Personnalis\u00e9");
      int i = this.height / 4 + 100;
      int j = this.height / 4 + 140;
      this.buttonList.add(new GuiButton(16, this.width / 2 - 250, this.height / 4 + 350 + 0, 88, 20, stringtranslate.translateKey("Ok")));
      this.buttonList.add(new GuiButton(5, this.width / 2 + 150, this.height / 4 + 350 + 0, 88, 20, stringtranslate.translateKey("Recommencer")));
   }

   public void updateScreen() {
      super.updateScreen();
   }

   protected void keyTyped(char par1, int par2) {}

   public void drawScreen(int i, int j, float f) {
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      this.drawDefaultBackground();
      this.drawBackgroundImage();
      this.drawHorizontalLine(0, this.width, this.height - 47, -16777216);
      this.drawHorizontalLine(0, this.width, this.height - 46, -9211021);
      drawRect(0, this.height - 45, this.width, this.height, -13421773);
      drawRect(this.width / 2 - 101, this.height - 28, this.width / 2 + 101, this.height - 15, -41054848);
      drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 + 100, this.height - 16, -268435456);
      drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 - 100 + Calculator.points * 20, this.height - 16, -267739136);
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.GRAY + "Votre score", this.width / 2, this.height - 40, 16777215);
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.GREEN + "" + Calculator.points * 10 + "%", this.width / 2, this.height - 13, 16777215);
      super.drawScreen(i, j, f);
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }

   protected void drawBackgroundImage() {
      int displayX = (this.width - this.xSize) / 2;
      int displayY = (this.height - this.ySize) / 2;
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/custom/social/da.png"));
      this.drawTexturedModalRect(displayX, displayY, 0, 0, this.xSize, this.ySize);
      int fpts = Calculator.points;
      String erreur = "";
      if(!Calculator.question1) {
         erreur = erreur + "1";
      }

      if(!Calculator.question2) {
         erreur = erreur + ", 2";
      }

      if(!Calculator.question3) {
         erreur = erreur + ", 3";
      }

      if(!Calculator.question4) {
         erreur = erreur + ", 4";
      }

      if(!Calculator.question5) {
         erreur = erreur + ", 5";
      }

      if(!Calculator.question6) {
         erreur = erreur + ", 6";
      }

      if(!Calculator.question7) {
         erreur = erreur + ", 7";
      }

      if(!Calculator.question8) {
         erreur = erreur + ", 8";
      }

      if(!Calculator.question9) {
         erreur = erreur + ", 9";
      }

      if(!Calculator.question10) {
         erreur = erreur + ", 10";
      }

      this.drawString(this.fontRendererObj, "bla bla bla", this.width / 2 - 35, this.height / 4 + 20 + 0, 10526880);
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.enabled) {
         if(guibutton.id == 16) {
            mc.displayGuiScreen((GuiScreen)null);
            Calculator.points = 0;
         }

         if(guibutton.id == 5) {
            mc.displayGuiScreen(new GuiPute(mc, mc.theWorld));
            Calculator.points = 0;
         }
      }
   }
}
