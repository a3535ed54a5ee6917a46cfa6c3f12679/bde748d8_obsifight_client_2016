package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPandaHat extends ModelBase {
   ModelRenderer left;
   ModelRenderer back;
   ModelRenderer front;
   ModelRenderer right;
   ModelRenderer top;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer front2;

   public ModelPandaHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.left = new ModelRenderer(this, 32, 1);
      this.left.addBox(4.0F, 15.0F, -5.0F, 1, 7, 10);
      this.left.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.left.setTextureSize(64, 32);
      this.left.mirror = true;
      this.setRotation(this.left, 0.0F, 0.0F, 0.0F);
      this.back = new ModelRenderer(this, 32, 21);
      this.back.addBox(-4.0F, 15.0F, 4.0F, 8, 8, 1);
      this.back.setRotationPoint(0.0F, 0.0F, 0.3F);
      this.back.setTextureSize(64, 32);
      this.back.mirror = true;
      this.setRotation(this.back, 0.0F, 0.0F, 0.0F);
      this.front = new ModelRenderer(this, 10, 26);
      this.front.addBox(-4.0F, 15.0F, -5.5F, 4, 2, 2);
      this.front.setRotationPoint(2.0F, 3.0F, -1.3F);
      this.front.setTextureSize(64, 32);
      this.front.mirror = true;
      this.setRotation(this.front, 0.0F, 0.0F, 0.0F);
      this.right = new ModelRenderer(this, 32, 1);
      this.right.addBox(-5.0F, 15.0F, -5.0F, 1, 7, 10);
      this.right.setRotationPoint(0.0F, -0.1F, 0.0F);
      this.right.setTextureSize(64, 32);
      this.right.mirror = true;
      this.setRotation(this.right, 0.0F, 0.0F, 0.0F);
      this.top = new ModelRenderer(this, 0, 0);
      this.top.addBox(-4.0F, 15.0F, -4.0F, 10, 1, 10);
      this.top.setRotationPoint(-1.0F, -0.5F, -1.0F);
      this.top.setTextureSize(64, 32);
      this.top.mirror = true;
      this.setRotation(this.top, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 21);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
      this.Shape1.setRotationPoint(-6.5F, 12.5F, -4.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 21);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
      this.Shape2.setRotationPoint(3.5F, 12.5F, -4.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.front2 = new ModelRenderer(this, 10, 18);
      this.front2.addBox(-4.0F, 15.0F, -5.5F, 8, 4, 2);
      this.front2.setRotationPoint(0.0F, 0.0F, -0.3F);
      this.front2.setTextureSize(64, 32);
      this.front2.mirror = true;
      this.setRotation(this.front2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.left.render(f5);
      this.back.render(f5);
      this.front.render(f5);
      this.right.render(f5);
      this.top.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.front2.render(f5);
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
