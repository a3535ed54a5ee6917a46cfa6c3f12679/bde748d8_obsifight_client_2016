package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelReindeer extends ModelBase {
   ModelRenderer nose;
   ModelRenderer Shape1;
   ModelRenderer Shape4;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape12;
   ModelRenderer Shape32;
   ModelRenderer Shape42;
   ModelRenderer Shape22;

   public ModelReindeer() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.nose = new ModelRenderer(this, 0, 28);
      this.nose.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.nose.setRotationPoint(-1.0F, 28.0F, -6.0F);
      this.nose.setTextureSize(64, 32);
      this.nose.mirror = true;
      this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1);
      this.Shape1.setRotationPoint(-3.0F, 25.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, (float)Math.PI, -0.1858931F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape4.setRotationPoint(-9.1F, 23.86667F, 0.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, (float)Math.PI, -1.115358F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape2.setRotationPoint(-11.3F, 23.65F, 0.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, (float)Math.PI, -0.7807508F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape3.setRotationPoint(-5.1F, 24.66667F, 0.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, (float)Math.PI, -1.115358F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1);
      this.Shape12.setRotationPoint(3.0F, 25.0F, -1.0F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, -0.1858931F);
      this.Shape32 = new ModelRenderer(this, 0, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape32.setRotationPoint(4.9F, 24.66667F, -1.0F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, -1.115358F);
      this.Shape42 = new ModelRenderer(this, 0, 0);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape42.setRotationPoint(8.9F, 23.86667F, -1.0F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, -1.115358F);
      this.Shape22 = new ModelRenderer(this, 0, 0);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape22.setRotationPoint(11.3F, 23.65F, -1.0F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, -0.7807508F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.nose.render(f5);
      this.Shape1.render(f5);
      this.Shape4.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape12.render(f5);
      this.Shape32.render(f5);
      this.Shape42.render(f5);
      this.Shape22.render(f5);
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
