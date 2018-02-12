package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPenguinHat extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer nose;

   public ModelPenguinHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 16);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 12);
      this.Shape1.setRotationPoint(5.0F, 17.0F, -6.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.3490659F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 16);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 12);
      this.Shape2.setRotationPoint(-6.0F, 17.0F, -6.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, -0.3490659F, 0.0F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
      this.head.setRotationPoint(0.0F, 17.0F, -7.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 28, 8);
      this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8);
      this.body.setRotationPoint(0.0F, 16.0F, 2.0F);
      this.body.setTextureSize(64, 32);
      this.body.mirror = true;
      this.setRotation(this.body, ((float)Math.PI / 2F), 0.0F, 0.0F);
      this.nose = new ModelRenderer(this, 16, 16);
      this.nose.addBox(-2.0F, -1.0F, -1.0F, 4, 3, 1);
      this.nose.setRotationPoint(0.0F, 11.0F, -15.0F);
      this.nose.setTextureSize(64, 32);
      this.nose.mirror = true;
      this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.head.render(f5);
      this.body.render(f5);
      this.nose.render(f5);
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
