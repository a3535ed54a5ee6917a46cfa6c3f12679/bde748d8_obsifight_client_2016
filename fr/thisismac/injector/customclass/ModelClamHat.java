package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelClamHat extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape24;
   ModelRenderer Shape3;

   public ModelClamHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 6, 1, 8);
      this.Shape1.setRotationPoint(-3.0F, 23.0F, -3.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 2, 12);
      this.Shape2.addBox(-3.0F, -1.0F, -7.0F, 6, 1, 7);
      this.Shape2.setRotationPoint(0.0F, 24.0F, 4.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.4461433F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 10, 23);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 4, 1, 7);
      this.Shape24.setRotationPoint(-2.0F, 20.0F, -1.0F);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, -0.4461433F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 1, 24);
      this.Shape3.addBox(0.0F, -1.0F, -1.0F, 1, 1, 1);
      this.Shape3.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape24.render(f5);
      this.Shape3.render(f5);
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
