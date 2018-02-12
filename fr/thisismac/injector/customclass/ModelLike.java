package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLike extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;

   public ModelLike() {
      this.textureWidth = 32;
      this.textureHeight = 16;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(-6.5F, -3.0F, -0.5F, 13, 6, 1);
      this.Shape1.setRotationPoint(0.0F, 10.0F, 0.0F);
      this.Shape1.setTextureSize(32, 16);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 8);
      this.Shape2.addBox(-3.0F, 0.0F, -0.5F, 6, 1, 1);
      this.Shape2.setRotationPoint(2.5F, 6.0F, 0.0F);
      this.Shape2.setTextureSize(32, 16);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 11);
      this.Shape3.addBox(-2.5F, 0.0F, -0.5F, 5, 1, 1);
      this.Shape3.setRotationPoint(3.0F, 13.0F, 0.0F);
      this.Shape3.setTextureSize(32, 16);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 11);
      this.Shape4.addBox(-2.0F, 0.0F, -0.5F, 4, 1, 1);
      this.Shape4.setRotationPoint(-4.5F, 13.0F, 0.0F);
      this.Shape4.setTextureSize(32, 16);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 15, 8);
      this.Shape5.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 1);
      this.Shape5.setRotationPoint(2.0F, 4.5F, 0.0F);
      this.Shape5.setTextureSize(32, 16);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 11);
      this.Shape6.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1);
      this.Shape6.setRotationPoint(2.5F, 2.0F, 0.0F);
      this.Shape6.setTextureSize(32, 16);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
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
