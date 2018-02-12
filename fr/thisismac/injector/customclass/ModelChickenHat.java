package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChickenHat extends ModelBase {
   ModelRenderer Shape15;
   ModelRenderer Shape14;
   ModelRenderer Shape13;
   ModelRenderer Shape12;
   ModelRenderer Shape11;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;

   public ModelChickenHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape15 = new ModelRenderer(this, 35, 14);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape15.setRotationPoint(0.0F, 23.0F, -3.0F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 35, 14);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape14.setRotationPoint(2.0F, 23.0F, -3.0F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 37, 15);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape13.setRotationPoint(1.0F, 23.0F, -1.0F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 35, 14);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape12.setRotationPoint(-2.0F, 23.0F, -3.0F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 35, 14);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape11.setRotationPoint(-3.0F, 23.0F, -1.0F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 35, 14);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape1.setRotationPoint(-4.0F, 23.0F, -3.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 35, 14);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape2.setRotationPoint(1.0F, 19.0F, 0.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 35, 14);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape3.setRotationPoint(-3.0F, 19.0F, 0.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
      this.Shape4.setRotationPoint(-4.0F, 15.0F, -4.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 14);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 7, 6, 5);
      this.Shape5.setRotationPoint(-4.0F, 9.0F, -4.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 5, 4, 1);
      this.Shape6.setRotationPoint(-3.0F, 15.0F, 3.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 45, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape7.setRotationPoint(-1.5F, 7.0F, -3.5F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 31, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3);
      this.Shape8.setRotationPoint(-1.5F, 11.0F, -7.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 47, 0);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape9.setRotationPoint(-1.5F, 13.0F, -6.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 0);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 9, 2, 4);
      this.Shape10.setRotationPoint(-5.0F, 16.0F, -2.0F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape15.render(f5);
      this.Shape14.render(f5);
      this.Shape13.render(f5);
      this.Shape12.render(f5);
      this.Shape11.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
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
