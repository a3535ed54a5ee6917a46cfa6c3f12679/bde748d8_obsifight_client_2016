package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrystalHat extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;

   public ModelCrystalHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 20, 0);
      this.Shape1.addBox(0.0F, -16.0F, 0.0F, 3, 16, 3);
      this.Shape1.setRotationPoint(-1.5F, 24.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.2845197F, -0.2792527F, 0.122173F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, -7.0F, 0.0F, 1, 7, 1);
      this.Shape2.setRotationPoint(0.0F, 24.0F, 3.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.1858931F, ((float)Math.PI / 10F), 0.0698132F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, -7.0F, 0.0F, 2, 7, 2);
      this.Shape3.setRotationPoint(-2.0F, 24.0F, 3.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, -0.122173F, 0.1570796F, -0.2094395F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, -5.0F, 0.0F, 2, 5, 2);
      this.Shape5.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, -0.4833219F, 2.044824F, -0.0371786F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, -9.0F, 0.0F, 2, 9, 2);
      this.Shape6.setRotationPoint(-2.0F, 24.0F, 3.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, -0.0607251F, (float)Math.PI, 0.2997009F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, -4.0F, 0.0F, 3, 4, 3);
      this.Shape7.setRotationPoint(-2.0F, 24.0F, 1.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 2.700407F, 0.1396263F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, -7.0F, 0.0F, 1, 7, 1);
      this.Shape8.setRotationPoint(-2.0F, 24.0F, -2.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.4712389F, 0.0698132F, 0.0349066F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, -6.0F, 0.0F, 1, 6, 1);
      this.Shape8.setRotationPoint(-1.0F, 24.0F, -2.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.2443461F, 0.2792527F, 0.1919862F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(0.0F, -6.0F, 0.0F, 1, 6, 1);
      this.Shape9.setRotationPoint(1.0F, 24.0F, 2.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, -0.2617994F, 0.9599311F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(0.0F, -9.0F, 0.0F, 1, 9, 1);
      this.Shape9.setRotationPoint(1.0F, 24.0F, 1.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.2094395F, 0.3665191F);
      this.Shape10 = new ModelRenderer(this, 0, 0);
      this.Shape10.addBox(-1.0F, -5.0F, 0.0F, 1, 5, 1);
      this.Shape10.setRotationPoint(2.0F, 24.0F, 0.0F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.4537856F, 0.7679449F);
      this.Shape11 = new ModelRenderer(this, 0, 0);
      this.Shape11.addBox(0.0F, -6.0F, 0.0F, 1, 6, 1);
      this.Shape11.setRotationPoint(-3.0F, 24.0F, 3.0F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, -0.1919862F, 0.0F, -0.3316126F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(0.0F, -2.0F, 0.0F, 2, 2, 2);
      this.Shape12.setRotationPoint(1.6F, 24.0F, 1.0F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.122173F, 0.122173F, 0.4712389F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1);
      this.Shape13.setRotationPoint(1.0F, 24.0F, 1.8F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, -0.4886922F, 0.1047198F, 0.1919862F);
      this.Shape14 = new ModelRenderer(this, 0, 0);
      this.Shape14.addBox(-1.0F, -3.0F, -1.0F, 1, 3, 1);
      this.Shape14.setRotationPoint(-0.5F, 24.0F, -2.0F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.8028515F, -0.1396263F, -0.0174533F);
      this.Shape15 = new ModelRenderer(this, 0, 0);
      this.Shape15.addBox(0.0F, -7.0F, 0.0F, 1, 7, 1);
      this.Shape15.setRotationPoint(-3.0F, 24.0F, 0.0F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, -0.3839724F, -0.2974289F);
      this.Shape1 = new ModelRenderer(this, 33, 0);
      this.Shape1.addBox(0.0F, -16.0F, 0.0F, 4, 16, 4);
      this.Shape1.setRotationPoint(-2.0F, 24.0F, -1.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.2496131F, -0.2792527F, 0.122173F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape13.render(f5);
      this.Shape14.render(f5);
      this.Shape15.render(f5);
      this.Shape1.render(f5);
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
