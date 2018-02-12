package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPartyHat extends ModelBase {
   ModelRenderer body1;
   ModelRenderer body2;
   ModelRenderer body3;
   ModelRenderer body4;
   ModelRenderer body5;
   ModelRenderer body6;
   ModelRenderer body7;
   ModelRenderer body8;

   public ModelPartyHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.body1 = new ModelRenderer(this, 22, 21);
      this.body1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.body1.setRotationPoint(-0.5F, 21.8F, -8.5F);
      this.body1.setTextureSize(64, 32);
      this.body1.mirror = true;
      this.setRotation(this.body1, -0.0698132F, 0.0F, 0.0F);
      this.body2 = new ModelRenderer(this, 39, 0);
      this.body2.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5);
      this.body2.setRotationPoint(-2.5F, 14.0F, -2.5F);
      this.body2.setTextureSize(64, 32);
      this.body2.mirror = true;
      this.setRotation(this.body2, 0.0F, 0.0F, 0.0F);
      this.body3 = new ModelRenderer(this, 22, 0);
      this.body3.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4);
      this.body3.setRotationPoint(-2.033333F, 13.0F, -2.0F);
      this.body3.setTextureSize(64, 32);
      this.body3.mirror = true;
      this.setRotation(this.body3, -0.0174533F, 0.0F, 0.0F);
      this.body4 = new ModelRenderer(this, 0, 0);
      this.body4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
      this.body4.setRotationPoint(-1.5F, 13.0F, -1.5F);
      this.body4.setTextureSize(64, 32);
      this.body4.mirror = true;
      this.setRotation(this.body4, 0.0F, 0.0F, 0.0F);
      this.body5 = new ModelRenderer(this, 4, 10);
      this.body5.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
      this.body5.setRotationPoint(-1.5F, 11.0F, -1.5F);
      this.body5.setTextureSize(64, 32);
      this.body5.mirror = true;
      this.setRotation(this.body5, -0.0349066F, 0.0F, 0.0F);
      this.body6 = new ModelRenderer(this, 32, 10);
      this.body6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.body6.setRotationPoint(-1.0F, 9.0F, -1.0F);
      this.body6.setTextureSize(64, 32);
      this.body6.mirror = true;
      this.setRotation(this.body6, -0.0523599F, 0.0F, 0.0F);
      this.body7 = new ModelRenderer(this, 22, 16);
      this.body7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.body7.setRotationPoint(-0.5F, 8.0F, -0.5F);
      this.body7.setTextureSize(64, 32);
      this.body7.mirror = true;
      this.setRotation(this.body7, -0.0698132F, 0.0F, 0.0F);
      this.body8 = new ModelRenderer(this, 31, 16);
      this.body8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.body8.setRotationPoint(-0.5F, 22.0F, -5.5F);
      this.body8.setTextureSize(64, 32);
      this.body8.mirror = true;
      this.setRotation(this.body8, -0.0698132F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.body1.render(f5);
      this.body2.render(f5);
      this.body3.render(f5);
      this.body4.render(f5);
      this.body5.render(f5);
      this.body6.render(f5);
      this.body7.render(f5);
      this.body8.render(f5);
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
