package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAdvancedHat extends ModelBase {
   ModelRenderer Shape8;
   ModelRenderer LT;
   ModelRenderer LB;
   ModelRenderer LL;
   ModelRenderer LR;
   ModelRenderer RL;
   ModelRenderer RT;
   ModelRenderer RB;
   ModelRenderer RR;
   ModelRenderer Leftln;
   ModelRenderer Rgithln;
   ModelRenderer Shape2;
   ModelRenderer Shape1;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Top;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;

   public ModelAdvancedHat() {
      this.textureWidth = 64;
      this.textureHeight = 54;
      this.Shape8 = new ModelRenderer(this, 14, 20);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape8.setRotationPoint(-1.0F, 11.5F, -5.0F);
      this.Shape8.setTextureSize(64, 54);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, -0.1115358F, 0.0F, 0.0F);
      this.LT = new ModelRenderer(this, 4, 17);
      this.LT.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.LT.setRotationPoint(-4.0F, 10.0F, -4.8F);
      this.LT.setTextureSize(64, 54);
      this.LT.mirror = true;
      this.setRotation(this.LT, -0.1745329F, 0.0F, 0.0F);
      this.LB = new ModelRenderer(this, 4, 22);
      this.LB.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.LB.setRotationPoint(-4.0F, 13.0F, -5.4F);
      this.LB.setTextureSize(64, 54);
      this.LB.mirror = true;
      this.setRotation(this.LB, -0.1745329F, 0.0F, 0.0F);
      this.LL = new ModelRenderer(this, 0, 19);
      this.LL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.LL.setRotationPoint(-5.0F, 11.0F, -5.0F);
      this.LL.setTextureSize(64, 54);
      this.LL.mirror = true;
      this.setRotation(this.LL, -0.1745329F, 0.0F, 0.0F);
      this.LR = new ModelRenderer(this, 10, 19);
      this.LR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.LR.setRotationPoint(-2.0F, 11.0F, -5.0F);
      this.LR.setTextureSize(64, 54);
      this.LR.mirror = true;
      this.setRotation(this.LR, -0.1745329F, 0.0F, 0.0F);
      this.RL = new ModelRenderer(this, 20, 19);
      this.RL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.RL.setRotationPoint(1.0F, 11.0F, -5.0F);
      this.RL.setTextureSize(64, 54);
      this.RL.mirror = true;
      this.setRotation(this.RL, -0.1745329F, 0.0F, 0.0F);
      this.RT = new ModelRenderer(this, 24, 17);
      this.RT.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.RT.setRotationPoint(2.0F, 10.0F, -4.8F);
      this.RT.setTextureSize(64, 54);
      this.RT.mirror = true;
      this.setRotation(this.RT, -0.1745329F, 0.0F, 0.0F);
      this.RB = new ModelRenderer(this, 24, 22);
      this.RB.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.RB.setRotationPoint(2.0F, 13.0F, -5.4F);
      this.RB.setTextureSize(64, 54);
      this.RB.mirror = true;
      this.setRotation(this.RB, -0.1745329F, 0.0F, 0.0F);
      this.RR = new ModelRenderer(this, 30, 19);
      this.RR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.RR.setRotationPoint(4.0F, 11.0F, -5.0F);
      this.RR.setTextureSize(64, 54);
      this.RR.mirror = true;
      this.setRotation(this.RR, -0.1745329F, 0.0F, 0.0F);
      this.Leftln = new ModelRenderer(this, 0, 29);
      this.Leftln.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Leftln.setRotationPoint(-4.0F, 11.0F, -4.8F);
      this.Leftln.setTextureSize(64, 54);
      this.Leftln.mirror = true;
      this.setRotation(this.Leftln, -0.1745329F, 0.0F, 0.0F);
      this.Rgithln = new ModelRenderer(this, 7, 29);
      this.Rgithln.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Rgithln.setRotationPoint(2.0F, 11.0F, -4.8F);
      this.Rgithln.setTextureSize(64, 54);
      this.Rgithln.mirror = true;
      this.setRotation(this.Rgithln, -0.1745329F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 11, 10);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape2.setRotationPoint(-4.5F, 12.8F, -1.0F);
      this.Shape2.setTextureSize(64, 54);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.2268928F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 10);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape1.setRotationPoint(-4.9F, 11.5F, -4.466667F);
      this.Shape1.setTextureSize(64, 54);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, -0.3490659F, 0.1115358F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 22, 12);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape3.setRotationPoint(-4.5F, 13.6F, 2.1F);
      this.Shape3.setTextureSize(64, 54);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, -0.122173F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 1);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape4.setRotationPoint(3.9F, 11.5F, -4.5F);
      this.Shape4.setTextureSize(64, 54);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, -0.3490659F, -0.111544F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 11, 1);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape5.setRotationPoint(3.5F, 12.8F, -1.0F);
      this.Shape5.setTextureSize(64, 54);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, -0.2268928F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 22, 3);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape6.setRotationPoint(3.5F, 13.6F, 2.133333F);
      this.Shape6.setTextureSize(64, 54);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, -0.122173F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 7);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1);
      this.Shape7.setRotationPoint(-4.5F, 13.8F, 4.0F);
      this.Shape7.setTextureSize(64, 54);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Top = new ModelRenderer(this, 35, 3);
      this.Top.addBox(-3.5F, -16.0F, -3.5F, 7, 7, 7);
      this.Top.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Top.setTextureSize(64, 54);
      this.Top.mirror = true;
      this.setRotation(this.Top, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 28, 32);
      this.Shape9.addBox(-4.5F, -10.0F, -4.5F, 9, 2, 9);
      this.Shape9.setRotationPoint(0.0F, 25.0F, 0.0F);
      this.Shape9.setTextureSize(64, 54);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 12, 33);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 3, 6);
      this.Shape10.setRotationPoint(2.0F, 5.0F, -3.0F);
      this.Shape10.setTextureSize(64, 54);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 12, 33);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 3, 6);
      this.Shape11.setRotationPoint(-2.0F, 5.0F, -3.0F);
      this.Shape11.setTextureSize(64, 54);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 33);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
      this.Shape12.setRotationPoint(-1.0F, 3.0F, -5.5F);
      this.Shape12.setTextureSize(64, 54);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 46);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 3, 1, 7);
      this.Shape13.setRotationPoint(-1.0F, 5.0F, -1.0F);
      this.Shape13.setTextureSize(64, 54);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, -0.4363323F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 40, 19);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 3, 4, 5);
      this.Shape14.setRotationPoint(-1.0F, 3.0F, -2.5F);
      this.Shape14.setTextureSize(64, 54);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, -0.3839724F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 42, 45);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape15.setRotationPoint(0.0F, 4.0F, -7.0F);
      this.Shape15.setTextureSize(64, 54);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.2617994F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 42, 45);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape16.setRotationPoint(0.0F, 4.0F, -6.8F);
      this.Shape16.setTextureSize(64, 54);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, -0.2617994F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape8.render(f5);
      this.LT.render(f5);
      this.LB.render(f5);
      this.LL.render(f5);
      this.LR.render(f5);
      this.RL.render(f5);
      this.RT.render(f5);
      this.RB.render(f5);
      this.RR.render(f5);
      this.Leftln.render(f5);
      this.Rgithln.render(f5);
      this.Shape2.render(f5);
      this.Shape1.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Top.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape13.render(f5);
      this.Shape14.render(f5);
      this.Shape15.render(f5);
      this.Shape16.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}