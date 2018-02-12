package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCactus extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape4;
   ModelRenderer Shape3;
   ModelRenderer Shape5;

   public ModelCactus() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 9, 1, 9);
      this.Shape1.setRotationPoint(-4.5F, 16.5F, -4.5F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 25);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
      this.Shape2.setRotationPoint(-8.5F, 16.0F, -2.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 25);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
      this.Shape2.setRotationPoint(-8.5F, 16.0F, 1.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 10, 24);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape3.setRotationPoint(-8.5F, 16.0F, -1.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 10, 24);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape3.setRotationPoint(-5.5F, 16.0F, -1.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 14);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
      this.Shape4.setRotationPoint(-7.5F, 12.0F, -1.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 25);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
      this.Shape2.setRotationPoint(4.5F, 16.0F, -2.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 25);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
      this.Shape2.setRotationPoint(4.5F, 16.0F, 1.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 14);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
      this.Shape4.setRotationPoint(5.5F, 12.0F, -1.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 10, 24);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape3.setRotationPoint(4.5F, 16.0F, -1.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 10, 24);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape3.setRotationPoint(7.5F, 16.0F, -1.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 36, 4);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
      this.Shape5.setRotationPoint(-9.0F, 16.6F, -2.5F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 36, 4);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
      this.Shape5.setRotationPoint(4.0F, 16.6F, -2.5F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape2.render(f5);
      this.Shape2.render(f5);
      this.Shape4.render(f5);
      this.Shape3.render(f5);
      this.Shape3.render(f5);
      this.Shape5.render(f5);
      this.Shape5.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
   }
}
