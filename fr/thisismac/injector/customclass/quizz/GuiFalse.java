package fr.thisismac.injector.customclass.quizz;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiFalse extends GuiScreen {
   private int xSize = 230;
   private int ySize = 162;
   protected String screenTitle = "Menu Personnaliser";
   protected GuiScreen guiScreen;
   private World world;

   public GuiFalse(Minecraft minecraft, World theWorld) {
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
      this.buttonList.add(new GuiButton(16, this.width / 2 - 100, this.height / 4 + 50 + 0, 88, 20, stringtranslate.translateKey("Ok")));
      this.buttonList.add(new GuiButton(5, this.width / 2 + 10, this.height / 4 + 50 + 0, 88, 20, stringtranslate.translateKey("Recommencer")));
   }

   public void drawScreen(int i, int j, float f) {
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      this.drawDefaultBackground();
      this.drawBackgroundImage();
      super.drawScreen(i, j, f);
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }

   protected void drawBackgroundImage() {
      int displayX = (this.width - this.xSize) / 2;
      int displayY = (this.height - this.ySize) / 2;
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/custom/social/da.png"));
      this.drawTexturedModalRect(displayX, displayY, 0, 0, this.xSize, this.ySize);
      this.drawString(this.fontRendererObj, "Mauvaise r\u00e9ponse !.", this.width / 2 - 35, this.height / 4 + 20 + 0, 10526880);
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.enabled) {
         if(guibutton.id == 16) {
            mc.displayGuiScreen((GuiScreen)null);
         }

         if(guibutton.id == 5) {
            mc.displayGuiScreen(new GuiPute(mc, mc.theWorld));
         }
      }
   }
}
