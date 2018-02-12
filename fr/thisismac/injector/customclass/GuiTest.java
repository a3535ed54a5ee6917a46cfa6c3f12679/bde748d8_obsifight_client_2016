package fr.thisismac.injector.customclass;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiTest extends GuiScreen {
   public ModelBase modelbase;
   public ResourceLocation texture;
   public ResourceLocation biped;
   public float xAxis = 0.0F;
   public float zAxis = 0.0F;

   public GuiTest(ModelBase modelbase, String texture) {
      this.modelbase = modelbase;
      this.texture = new ResourceLocation(texture);
   }

   public void initGui() {
      this.buttonList.add(new GuiButton(0, this.width / 2 - 10 - 50, this.height - 30, 20, 20, "<"));
      this.buttonList.add(new GuiButton(1, this.width / 2 - 10 + 50, this.height - 30, 20, 20, ">"));
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

   public void actionPerformed(GuiButton button) {
      switch(button.id) {
      case 0:
         this.xAxis += 10.0F;
         break;
      case 1:
         this.xAxis -= 10.0F;
      }
   }

   public void drawScreen(int mx, int my, float tick) {
      ScaledResolution var5 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      this.drawDefaultBackground();
      this.drawModel();
      dro(var7 / 3, var6 / 5, 45, -50.0F, 0.0F, mc.thePlayer);
      super.drawScreen(mx, my, tick);
   }

   public void drawModel() {
      GL11.glPushMatrix();
      RenderHelper.enableStandardItemLighting();
      GL11.glTranslatef((float)(this.width / 2), (float)(this.height / 3), 0.0F);
      GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(this.xAxis, 0.0F, 1.0F, 0.0F);
      GL11.glPushMatrix();
      GL11.glEnable(GL11.GL_DEPTH_TEST);
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glDisable(GL11.GL_CULL_FACE);
      GL11.glEnable(GL12.GL_RESCALE_NORMAL);
      mc.renderEngine.bindTexture(this.texture);

      for(int i = 0; i < this.modelbase.boxList.size(); ++i) {
         ((ModelRenderer)((ModelRenderer)this.modelbase.boxList.get(i))).render(4.0F);
      }

      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      GL11.glEnable(GL11.GL_CULL_FACE);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glPopMatrix();
   }
}
