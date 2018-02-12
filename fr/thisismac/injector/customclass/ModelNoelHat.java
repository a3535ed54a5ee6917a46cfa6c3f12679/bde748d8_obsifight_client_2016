package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNoelHat extends ModelBase {
   ModelRenderer Rim;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Top;

   public ModelNoelHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Rim = new ModelRenderer(this, 0, 21);
      this.Rim.addBox(0.0F, 0.0F, 0.0F, 8, 3, 8);
      this.Rim.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Rim.setTextureSize(64, 32);
      this.Rim.mirror = true;
      this.setRotation(this.Rim, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 7, 3, 7);
      this.Shape1.setRotationPoint(0.5F, -2.933333F, 1.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, -0.1487144F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6);
      this.Shape2.setRotationPoint(1.0F, -5.5F, 2.3F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.2974289F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 5, 3, 5);
      this.Shape3.setRotationPoint(1.5F, -8.0F, 4.3F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, -0.4833219F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4);
      this.Shape4.setRotationPoint(2.0F, -10.0F, 6.6F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, -0.6320364F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
      this.Shape5.setRotationPoint(2.5F, -11.5F, 9.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, -0.8551081F, 0.0F, 0.0F);
      this.Top = new ModelRenderer(this, 0, 24);
      this.Top.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
      this.Top.setRotationPoint(2.0F, -13.6F, 11.5F);
      this.Top.setTextureSize(64, 32);
      this.Top.mirror = true;
      this.setRotation(this.Top, -1.003822F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Rim.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Top.render(f5);
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
