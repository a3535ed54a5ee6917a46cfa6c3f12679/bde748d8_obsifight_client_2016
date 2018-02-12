package fr.thisismac.injector.customclass;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class CustomPauseMenu extends GuiScreen {
   private int field_146445_a;
   private int field_146444_f;
   int left = this.getLeftColumnX();
   int top;
   int right;
   int center;
   public static GuiScreenOptionsSounds.Button gg;

   public CustomPauseMenu() {
      this.top = this.height / 6;
      this.right = this.getRightColumnX();
      this.center = this.width / 2;
   }

   private int getLeftColumnX() {
      return this.width / 2 - 155;
   }

   private int getRightColumnX() {
      return this.width / 2 + 29;
   }

   public void initGui() {
      this.field_146445_a = 0;
      this.buttonList.clear();
      boolean var1 = true;
      boolean var2 = true;
      this.buttonList.add(new GuiButton(1, this.width / 2 + 60, this.height / 2 + 40, 100, 20, I18n.format("Quitter", new Object[0])));
      if(!mc.isIntegratedServerRunning()) {
         ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("Deconnexion", new Object[0]);
      }

      this.buttonList.add(new GuiButton(4, this.width / 2 - 150, this.height / 2 + 40, 100, 20, I18n.format("Retour en jeu", new Object[0])));
      this.buttonList.add(new GuiButton(7, this.width / 2 - 150, this.height / 2 - 50, 100, 20, I18n.format(EnumChatFormatting.GREEN + "Teamspeak", new Object[0])));
      this.buttonList.add(new GuiButton(3, this.width / 2 - 150, this.height / 2 - 20, 100, 20, I18n.format(EnumChatFormatting.BLUE + "Forum", new Object[0])));
      this.buttonList.add(new GuiButton(12, this.width / 2 - 150, this.height / 2 + 10, 100, 20, I18n.format("Texture Packs", new Object[0])));
      this.buttonList.add(new GuiButton(2, this.width / 2 + 60, this.height / 2 - 50, 100, 20, I18n.format(EnumChatFormatting.RED + "Site", new Object[0])));
      this.buttonList.add(new GuiButton(6, this.width / 2 + 60, this.height / 2 - 20, 100, 20, I18n.format(EnumChatFormatting.YELLOW + "Vote", new Object[0])));
      this.buttonList.add(new GuiButton(0, this.width / 2 + 60, this.height / 2 + 10, 100, 20, I18n.format("menu.options", new Object[0])));
      GuiScreenOptionsSounds ras = new GuiScreenOptionsSounds(this, mc.gameSettings);
      List var10000 = this.buttonList;
      GuiScreenOptionsSounds.Button var10001 = new GuiScreenOptionsSounds.Button;
      ras.getClass();
      var10001.<init>(SoundCategory.RADIO.getCategoryId(), this.width / 2 - 150, this.height / 2 - 80, SoundCategory.RADIO, true);
      gg = var10001;
      var10000.add(var10001);
   }

   protected void drawBack() {
      FontRenderer font = mc.fontRenderer;
      int guiScale = mc.gameSettings.guiScale;
      int displayX = (this.width + 350) / 2;
      int displayY = (this.height - 250) / 2;
      ScaledResolution var5 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      if(guiScale == 1) {
         ;
      }

      if(guiScale == 2) {
         String name = mc.getSession().getUsername().toString();
         drawRect(var6 - 300, this.height / 2 - 200, var6 - 25, var6 / 2, -1610612736);
         drawRect(var6 - 300, this.height / 2 - 200, var6 - 25, var6 / 10, -1610612736);
         this.drawString(font, ChatFormatting.BOLD + "" + name, var6 - 150 - font.getStringWidth(name), 70, 1610612);
      }

      if(guiScale == 3) {
         ;
      }
   }

   protected void actionPerformed(GuiButton p_146284_1_) {
      URI var7;
      switch(p_146284_1_.id) {
      case 0:
         mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
         break;
      case 1:
         p_146284_1_.enabled = false;
         mc.theWorld.sendQuittingDisconnectingPacket();
         mc.loadWorld((WorldClient)null);
         mc.displayGuiScreen(new CustomMainMenu());
         CustomSoundThread.running = false;
         break;
      case 2:
         try {
            var7 = new URI("http://obsifight.fr");
            if(Desktop.isDesktopSupported()) {
               Desktop.getDesktop().browse(var7);
            }
         } catch (Exception var6) {
            var6.printStackTrace();
         }
         break;
      case 3:
         try {
            var7 = new URI("http://obsifight.fr/forum/index.php");
            if(Desktop.isDesktopSupported()) {
               Desktop.getDesktop().browse(var7);
            }
         } catch (Exception var5) {
            var5.printStackTrace();
         }
         break;
      case 4:
         mc.displayGuiScreen((GuiScreen)null);
         mc.setIngameFocus();
         break;
      case 5:
         mc.displayGuiScreen(new GuiAchievements(this, mc.thePlayer.func_146107_m()));
         break;
      case 6:
         try {
            var7 = new URI("http://obsifight.fr/vote");
            if(Desktop.isDesktopSupported()) {
               Desktop.getDesktop().browse(var7);
            }
         } catch (Exception var4) {
            var4.printStackTrace();
         }
         break;
      case 7:
         try {
            var7 = new URI("ts3server://ts.obsifight.fr?nickname=" + mc.getSession().getUsername());
            if(Desktop.isDesktopSupported()) {
               Desktop.getDesktop().browse(var7);
            }
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      case 8:
      case 9:
      case 10:
      case 11:
      default:
         break;
      case 12:
         mc.gameSettings.saveOptions();
         mc.displayGuiScreen(new GuiScreenResourcePacks(this));
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

   public static void func_147046_a(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_) {
      GL11.glEnable(GL11.GL_COLOR_MATERIAL);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = p_147046_5_.renderYawOffset;
      float var7 = p_147046_5_.rotationYaw;
      float var8 = p_147046_5_.rotationPitch;
      float var9 = p_147046_5_.prevRotationYawHead;
      float var10 = p_147046_5_.rotationYawHead;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
      p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
      p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
      p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
      GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
      RenderManager.instance.playerViewY = 180.0F;
      RenderManager.instance.func_147940_a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      p_147046_5_.renderYawOffset = var6;
      p_147046_5_.rotationYaw = var7;
      p_147046_5_.rotationPitch = var8;
      p_147046_5_.prevRotationYawHead = var9;
      p_147046_5_.rotationYawHead = var10;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public static void drr(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_) {
      GL11.glEnable(GL11.GL_COLOR_MATERIAL);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = p_147046_5_.renderYawOffset;
      float var7 = p_147046_5_.rotationYaw;
      float var8 = p_147046_5_.rotationPitch;
      float var9 = p_147046_5_.prevRotationYawHead;
      float var10 = p_147046_5_.rotationYawHead;
      GL11.glRotatef(280.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
      p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
      p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
      p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
      GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
      RenderManager.instance.playerViewY = 180.0F;
      RenderManager.instance.func_147940_a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      p_147046_5_.renderYawOffset = var6;
      p_147046_5_.rotationYaw = var7;
      p_147046_5_.rotationPitch = var8;
      p_147046_5_.prevRotationYawHead = var9;
      p_147046_5_.rotationYawHead = var10;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public static void dra(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_) {
      GL11.glEnable(GL11.GL_COLOR_MATERIAL);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = p_147046_5_.renderYawOffset;
      float var7 = p_147046_5_.rotationYaw;
      float var8 = p_147046_5_.rotationPitch;
      float var9 = p_147046_5_.prevRotationYawHead;
      float var10 = p_147046_5_.rotationYawHead;
      GL11.glRotatef(200.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
      p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
      p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
      p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
      GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
      RenderManager.instance.playerViewY = 180.0F;
      RenderManager.instance.func_147940_a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      p_147046_5_.renderYawOffset = var6;
      p_147046_5_.rotationYaw = var7;
      p_147046_5_.rotationPitch = var8;
      p_147046_5_.prevRotationYawHead = var9;
      p_147046_5_.rotationYawHead = var10;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public static void dro(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_) {
      GL11.glEnable(GL11.GL_COLOR_MATERIAL);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = p_147046_5_.renderYawOffset;
      float var7 = p_147046_5_.rotationYaw;
      float var8 = p_147046_5_.rotationPitch;
      float var9 = p_147046_5_.prevRotationYawHead;
      float var10 = p_147046_5_.rotationYawHead;
      GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
      p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
      p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
      p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
      GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
      RenderManager.instance.playerViewY = 180.0F;
      RenderManager.instance.func_147940_a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      p_147046_5_.renderYawOffset = var6;
      p_147046_5_.rotationYaw = var7;
      p_147046_5_.rotationPitch = var8;
      p_147046_5_.prevRotationYawHead = var9;
      p_147046_5_.rotationYawHead = var10;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public void updateScreen() {
      super.updateScreen();
      ++this.field_146444_f;
   }

   public void drawScreen(int par1, int par2, float par3) {
      ScaledResolution var5 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      this.drawDefaultBackground();
      func_147046_a(this.width / 2 + 5, this.height / 2 + 50, 45, 210.0F - (float)par1, 80.0F - (float)par2, mc.thePlayer);
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.AQUA + "- ObsiFight Network -", this.width / 2, this.height / 8, 16777215);
      super.drawScreen(par1, par2, par3);
   }
}
