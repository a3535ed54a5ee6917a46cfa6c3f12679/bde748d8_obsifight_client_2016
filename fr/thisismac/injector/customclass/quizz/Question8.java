package fr.thisismac.injector.customclass.quizz;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class Question8 extends GuiScreen {
   private int xSize = 230;
   private int ySize = 162;
   protected String screenTitle = "Menu Personnaliser";
   protected GuiScreen guiScreen;
   private World world;

   public Question8(Minecraft minecraft, World theWorld) {
      mc = minecraft;
      this.world = theWorld;
      this.xSize = 256;
      this.ySize = 256;
   }

   public void initGui() {
      StringTranslate stringtranslate = StringTranslate.getInstance();
      this.screenTitle = stringtranslate.translateKey("Menu Personnaliser");
      int i = this.height / 4 + 100;
      int j = this.height / 4 + 140;
      this.buttonList.add(new GuiButtonQuestion(2, this.width / 2 - 85, this.height / 4 + 50 + 0));
      this.buttonList.add(new GuiButtonQuestion(3, this.width / 2 - 85, this.height / 4 + 95 + 0));
      this.buttonList.add(new GuiButtonQuestion(4, this.width / 2 - 85, this.height / 4 + 140 + 0));
      this.buttonList.add(new GuiButtonQuestion(5, this.width / 2 - 85, this.height / 4 + 185 + 0));
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
      drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 - 100 + 140, this.height - 16, -267739136);
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.GRAY + "Progression", this.width / 2, this.height - 40, 16777215);
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.GREEN + "70%", this.width / 2, this.height - 13, 16777215);
      super.drawScreen(i, j, f);
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }

   protected void drawBackgroundImage() {
      int displayX = (this.width - this.xSize) / 2;
      int displayY = (this.height - this.ySize) / 2;
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/custom/social/da.png"));
      this.drawTexturedModalRect(displayX, displayY, 0, 0, this.xSize, this.ySize);
      this.drawString(this.fontRendererObj, "xcxcxcxcxcx ?", this.width / 2 - 85, this.height / 4 + 20 + 0, 10526880);
      this.drawString(this.fontRendererObj, "Speed", this.width / 2 - 55, this.height / 4 + 55 + 0, 10526880);
      this.drawString(this.fontRendererObj, "Force", this.width / 2 - 55, this.height / 4 + 100 + 0, 10526880);
      this.drawString(this.fontRendererObj, "Fire resistance", this.width / 2 - 55, this.height / 4 + 145 + 0, 10526880);
      this.drawString(this.fontRendererObj, "Regeneration", this.width / 2 - 55, this.height / 4 + 190 + 0, 10526880);
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.enabled) {
         if(guibutton.id == 2) {
            ++Calculator.points;
            Calculator.question8 = true;
            mc.displayGuiScreen(new Question9(mc, mc.theWorld));
         }

         if(guibutton.id == 3 || guibutton.id == 4 || guibutton.id == 5) {
            Calculator.question8 = false;
            mc.displayGuiScreen(new Question9(mc, mc.theWorld));
         }
      }
   }
}
