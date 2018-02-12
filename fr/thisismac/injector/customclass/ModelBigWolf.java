package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelBigWolf extends ModelQuadruped {
   public ModelBigWolf() {
      super(12, 0.0F);
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
      this.head.setRotationPoint(0.0F, 10.0F, -16.0F);
      this.head.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F);
      this.head.setTextureOffset(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
      ModelRenderer modelrenderer = this.head.setTextureOffset(26, 0);
      modelrenderer.mirror = true;
      modelrenderer.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
      this.body = new ModelRenderer(this);
      this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
      this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
      this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);
      boolean i = true;
      this.leg1 = new ModelRenderer(this, 50, 22);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
      this.leg1.setRotationPoint(-3.5F, 14.0F, 6.0F);
      this.leg2 = new ModelRenderer(this, 50, 22);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
      this.leg2.setRotationPoint(3.5F, 14.0F, 6.0F);
      this.leg3 = new ModelRenderer(this, 50, 40);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
      this.leg3.setRotationPoint(-2.5F, 14.0F, -7.0F);
      this.leg4 = new ModelRenderer(this, 50, 40);
      this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
      this.leg4.setRotationPoint(2.5F, 14.0F, -7.0F);
      --this.leg1.rotationPointX;
      ++this.leg2.rotationPointX;
      this.leg1.rotationPointZ += 0.0F;
      this.leg2.rotationPointZ += 0.0F;
      --this.leg3.rotationPointX;
      ++this.leg4.rotationPointX;
      --this.leg3.rotationPointZ;
      --this.leg4.rotationPointZ;
   }

   public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      if(this.isChild) {
         float f = 2.0F;
         float yoffset = 16.0F;
         float zoffset = 4.0F;
         GL11.glPushMatrix();
         GL11.glScaled(0.6666666865348816D, 0.6666666865348816D, 0.6666666865348816D);
         GL11.glTranslated(0.0D, (double)(yoffset * scale), (double)(zoffset * scale));
         this.head.render(scale);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslated(0.0D, (double)(24.0F * scale), 0.0D);
         this.body.render(scale);
         this.leg1.render(scale);
         this.leg2.render(scale);
         this.leg3.render(scale);
         this.leg4.render(scale);
         GL11.glPushMatrix();
      } else {
         this.head.render(scale);
         this.body.render(scale);
         this.leg1.render(scale);
         this.leg2.render(scale);
         this.leg3.render(scale);
         this.leg4.render(scale);
      }
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      float var10000 = ageInTicks - (float)entityIn.ticksExisted;
      float f1 = 10.0F;
      f1 *= f1;
      float f2 = 1.0F - f1;
      this.body.rotateAngleX = ((float)Math.PI / 2F) - f1 * (float)Math.PI * 0.35F;
      this.body.rotationPointY = 9.0F * f2 + 11.0F * f1;
      this.leg3.rotationPointY = 14.0F * f2 + -6.0F * f1;
      this.leg3.rotationPointZ = -8.0F * f2 + -4.0F * f1;
      this.leg3.rotateAngleX -= f1 * (float)Math.PI * 0.45F;
      this.leg4.rotationPointY = this.leg3.rotationPointY;
      this.leg4.rotationPointZ = this.leg3.rotationPointZ;
      this.leg4.rotateAngleX -= f1 * (float)Math.PI * 0.45F;
      this.head.rotationPointY = 10.0F * f2 + -12.0F * f1;
      this.head.rotationPointZ = -16.0F * f2 + -3.0F * f1;
      this.head.rotateAngleX += f1 * (float)Math.PI * 0.15F;
   }
}
