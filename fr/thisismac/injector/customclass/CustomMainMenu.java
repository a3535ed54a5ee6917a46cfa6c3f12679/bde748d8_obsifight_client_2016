package fr.thisismac.injector.customclass;

import fr.thisismac.injector.Core;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CustomMainMenu extends GuiScreen {
   private String text = Utils.readURL("http://dl.obsifight.fr/launcher/pvp/news.txt");
   private int textPosition = 440;
   private int pute = 800;
   private Random rand = new Random();
   public static final ResourceLocation background = new ResourceLocation("textures/custom/background.png");
   private List<String> dev_name = Arrays.asList(Utils.readURL("http://dl.obsifight.fr/access_dev.txt").split(","));

   public void updateScreen() {
      if(this.textPosition < 0 - mc.fontRenderer.getStringWidth(this.text)) {
         this.textPosition = this.width / 2 + 200;
      }

      this.textPosition -= 3;
   }

   public void initGui() {
      int var3 = this.height / 4 + 48;
      this.buttonList.add(new GuiButton(4, this.width / 2 - 100, var3 + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
      this.buttonList.add(new GuiButtonAnimated(1, this.width / 2 - 100, var3 + 62 + -48, 200, 20, EnumChatFormatting.BLUE + "Rejoindre ObsiFight"));
      this.buttonList.add(new GuiButton(6, this.width / 2 + 2, var3 + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
      this.buttonList.add(new GuiButtonTwitter(2, this.width / 2 - 124, var3 + 72 + 12));
      this.buttonList.add(new GuiButtonTeamspeak(3, this.width / 2 - -104, var3 + 72 + 12));
      if((this.dev_name == null || !this.dev_name.contains(mc.getSession().getUsername())) && !mc.getSession().getUsername().equals("Fenixx57")) {
         GuiButton solo;
         this.buttonList.add(solo = new GuiButton(5, this.width / 2 - 100, var3 - 10, I18n.format("menu.singleplayer", new Object[0])));
         solo.enabled = mc.isDemo();
         this.buttonList.add(new GuiButton(42, this.width / 2 - 95, this.height - 15, EnumChatFormatting.GOLD + "Version 6"));
      } else {
         this.buttonList.add(new GuiButton(7, this.width / 2 - 40, this.height - 15, 80, 20, EnumChatFormatting.GOLD + "Dev Menu"));
         this.buttonList.add(new GuiButton(5, this.width / 2 - 100, var3 - 10, I18n.format("menu.singleplayer", new Object[0])));
      }
   }

   protected void actionPerformed(GuiButton p_146284_1_) {
      URI var7;
      if(p_146284_1_.id == 10) {
         try {
            var7 = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            Desktop.getDesktop().browse(var7);
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

      if(p_146284_1_.id == 1) {
         Minecraft var10000 = mc;
         GuiConnecting var10001 = new GuiConnecting;
         GuiScreen var10003 = (GuiScreen)null;
         Minecraft var10004 = mc;
         Core.get().getClass();
         Core.get().getClass();
         var10001.<init>(var10003, var10004, "bungee.obsifight.fr", 25565);
         var10000.displayGuiScreen(var10001);
      }

      if(p_146284_1_.id == 2) {
         try {
            var7 = new URI("https://twitter.com/ObsiFight");
            Desktop.getDesktop().browse(var7);
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      if(p_146284_1_.id == 42) {
         try {
            var7 = new URI("https://forum.obsifight.fr/index.php?threads/mise-%C3%A0-jour-6-30-06-2016.19823/");
            Desktop.getDesktop().browse(var7);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

      if(p_146284_1_.id == 3) {
         try {
            var7 = new URI("ts3server://ts.obsifight.fr?nickname=" + mc.getSession().getUsername());
            Desktop.getDesktop().browse(var7);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      } else if(p_146284_1_.id == 7) {
         mc.displayGuiScreen(new GuiMultiplayer(this));
      }

      if(p_146284_1_.id == 4) {
         mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
      }

      if(p_146284_1_.id == 5) {
         mc.displayGuiScreen(new GuiSelectWorld(this));
      }

      if(p_146284_1_.id == 6) {
         mc.shutdown();
      }
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
   }

   public void drawBackground() {
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDisable(GL11.GL_FOG);
      Tessellator var2 = Tessellator.instance;
      GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
      mc.getTextureManager().bindTexture(background);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var2.startDrawingQuads();
      var2.addVertexWithUV(0.0D, (double)this.height, 0.0D, 0.0D, 1.0D);
      var2.addVertexWithUV((double)this.width, (double)this.height, 0.0D, 1.0D, 1.0D);
      var2.addVertexWithUV((double)this.width, 0.0D, 0.0D, 1.0D, 0.0D);
      var2.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      var2.draw();
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawBackground();
      this.drawLogo();
      this.drawGradientRect(0, 0, this.width, 12, 2130706433, 2130706433);
      this.drawString(this.fontRendererObj, this.text, this.textPosition, 2, 12128795);
      super.drawScreen(par1, par2, par3);
   }

   public static String readToString(String targetURL) throws IOException {
      URL url = new URL(targetURL);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder stringBuilder = new StringBuilder();

      String inputLine;
      while((inputLine = bufferedReader.readLine()) != null) {
         stringBuilder.append(inputLine);
         stringBuilder.append(System.lineSeparator());
      }

      bufferedReader.close();
      return stringBuilder.toString().trim();
   }
}
