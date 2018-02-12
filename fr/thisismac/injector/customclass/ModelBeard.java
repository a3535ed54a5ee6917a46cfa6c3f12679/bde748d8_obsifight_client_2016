package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBeard extends ModelBase {
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;

   public ModelBeard() {
      this.textureWidth = 32;
      this.textureHeight = 16;
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(-3.5F, 0.0F, 0.0F, 7, 3, 2);
      this.Shape2.setRotationPoint(0.0F, 23.0F, -4.5F);
      this.Shape2.setTextureSize(32, 16);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1);
      this.Shape3.setRotationPoint(-2.0F, 22.0F, -2.5F);
      this.Shape3.setTextureSize(32, 16);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1);
      this.Shape4.setRotationPoint(1.0F, 22.0F, -2.5F);
      this.Shape4.setTextureSize(32, 16);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, -2.0F, 2, 1, 1);
      this.Shape5.setRotationPoint(-1.0F, 21.0F, -2.5F);
      this.Shape5.setTextureSize(32, 16);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(-3.5F, 0.0F, -1.0F, 7, 2, 2);
      this.Shape6.setRotationPoint(0.0F, 26.0F, -3.5F);
      this.Shape6.setTextureSize(32, 16);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape7.setRotationPoint(-4.5F, 22.0F, -4.5F);
      this.Shape7.setTextureSize(32, 16);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape8.setRotationPoint(3.5F, 22.0F, -4.5F);
      this.Shape8.setTextureSize(32, 16);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2);
      this.Shape9.setRotationPoint(0.0F, 28.0F, -4.5F);
      this.Shape9.setTextureSize(32, 16);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 0);
      this.Shape10.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape10.setRotationPoint(0.0F, 29.0F, -4.0F);
      this.Shape10.setTextureSize(32, 16);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 0);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
      this.Shape11.setRotationPoint(-4.5F, 21.0F, -2.5F);
      this.Shape11.setTextureSize(32, 16);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
      this.Shape12.setRotationPoint(3.5F, 21.0F, -2.5F);
      this.Shape12.setTextureSize(32, 16);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape13.setRotationPoint(0.0F, 23.5F, -5.0F);
      this.Shape13.setTextureSize(32, 16);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape13.render(f5);
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
