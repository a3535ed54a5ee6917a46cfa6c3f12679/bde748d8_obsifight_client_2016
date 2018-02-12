package fr.thisismac.injector.customclass;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.thisismac.injector.Core;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CustomLoadingScreen extends GuiScreen {
   private short updateCounter = 0;
   private Random rand = new Random();
   private ResourceLocation background = new ResourceLocation("");

   public void initGui() {
      this.buttonList.add(new GuiButton(0, this.width - 100, this.height - 30, 60, 20, "Annuler"));
   }

   public void drawTextureWithOptionalSize(int x, int y, int u, int v, int width, int height, int uSize, int vSize) {
      float scaledX = 1.0F / (float)uSize;
      float scaledY = 1.0F / (float)vSize;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)this.zLevel, (double)((float)(u + 0) * scaledX), (double)((float)(v + height) * scaledY));
      tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)this.zLevel, (double)((float)(u + width) * scaledX), (double)((float)(v + height) * scaledY));
      tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)this.zLevel, (double)((float)(u + width) * scaledX), (double)((float)(v + 0) * scaledY));
      tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)this.zLevel, (double)((float)(u + 0) * scaledX), (double)((float)(v + 0) * scaledY));
      tessellator.draw();
   }

   public void drawLogo() {
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/hardfight/logo.png"));
      this.drawTextureWithOptionalSize(this.width / 2 - this.width / 4, this.height / 8, 0, 0, this.width / 2, this.height / 3, this.width / 2, this.height / 3);
   }

   public void drawBackground() {
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDisable(GL11.GL_FOG);
      Tessellator var2 = Tessellator.instance;
      GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
      mc.getTextureManager().bindTexture(this.background);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var2.startDrawingQuads();
      var2.addVertexWithUV(0.0D, (double)this.height, 0.0D, 0.0D, 1.0D);
      var2.addVertexWithUV((double)this.width, (double)this.height, 0.0D, 1.0D, 1.0D);
      var2.addVertexWithUV((double)this.width, 0.0D, 0.0D, 1.0D, 0.0D);
      var2.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      var2.draw();
   }

   public void updateScreen() {
      ++this.updateCounter;
      if(this.updateCounter == 100) {
         Minecraft var10000 = mc;
         GuiConnecting var10001 = new GuiConnecting;
         Minecraft var10004 = mc;
         Core.get().getClass();
         var10001.<init>(this, var10004, "bungee.obsifight.fr", 25565);
         var10000.displayGuiScreen(var10001);
      } else if(this.updateCounter > 100) {
         mc.displayGuiScreen(new CustomMainMenu());
      }

      super.updateScreen();
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawBackground();
      this.drawLogo();
      this.drawHorizontalLine(0, this.width, this.height - 47, -16777216);
      this.drawHorizontalLine(0, this.width, this.height - 46, -9211021);
      drawRect(0, this.height - 45, this.width, this.height, -13421773);
      drawRect(this.width / 2 - 101, this.height - 28, this.width / 2 + 101, this.height - 15, -41054848);
      drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 + 100, this.height - 16, -268435456);
      drawRect(this.width / 2 - 100, this.height - 27, this.width / 2 - 100 + this.updateCounter * 2, this.height - 16, -267739136);
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.GRAY + "Chargement...", this.width / 2, this.height - 40, 16777215);
      this.drawCenteredString(this.fontRendererObj, ChatFormatting.GREEN + "" + this.updateCounter + "%", this.width / 2, this.height - 13, 16777215);
      this.drawString(this.fontRendererObj, ChatFormatting.RED + "HardFight", 10, this.height - 40, -1);
      this.drawString(this.fontRendererObj, ChatFormatting.GRAY + "Network " + ChatFormatting.RED + "PVP", 10, this.height - 28, -1);
      this.drawString(this.fontRendererObj, ChatFormatting.GRAY + "Depuis " + ChatFormatting.RED + "2012", 10, this.height - 18, -1);
      super.drawScreen(par1, par2, par3);
   }

   public void actionPerformed(GuiButton button) {
      if(button.id == 0) {
         mc.displayGuiScreen(new CustomMainMenu());
      }
   }
}
