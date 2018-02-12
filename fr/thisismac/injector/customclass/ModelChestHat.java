package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChestHat extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape1a;
   ModelRenderer Shape2;
   ModelRenderer Shape1b;
   ModelRenderer Shape1c;
   ModelRenderer Shape2a;
   ModelRenderer Shape1d;
   ModelRenderer Shape1e;
   ModelRenderer Shape2b;
   ModelRenderer Shape2c;
   ModelRenderer Shape2d;
   ModelRenderer Shape2e;
   ModelRenderer Shape2f;
   ModelRenderer Shape1f;

   public ModelChestHat() {
      this.textureWidth = 266;
      this.textureHeight = 155;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(-7.5F, -4.0F, -6.0F, 15, 8, 12);
      this.Shape1.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape1.setTextureSize(266, 155);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape1a = new ModelRenderer(this, 54, 0);
      this.Shape1a.addBox(-8.0F, -4.0F, 4.5F, 2, 6, 2);
      this.Shape1a.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape1a.setTextureSize(266, 155);
      this.Shape1a.mirror = true;
      this.setRotation(this.Shape1a, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 63, 0);
      this.Shape2.addBox(-7.5F, -4.0F, -13.0F, 15, 4, 12);
      this.Shape2.setRotationPoint(0.0F, 16.0F, 7.0F);
      this.Shape2.setTextureSize(266, 155);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape1b = new ModelRenderer(this, 0, 21);
      this.Shape1b.addBox(-8.0F, 2.0F, -6.5F, 16, 2, 13);
      this.Shape1b.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape1b.setTextureSize(266, 155);
      this.Shape1b.mirror = true;
      this.setRotation(this.Shape1b, 0.0F, 0.0F, 0.0F);
      this.Shape1c = new ModelRenderer(this, 54, 0);
      this.Shape1c.addBox(6.0F, -4.0F, 4.5F, 2, 6, 2);
      this.Shape1c.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape1c.setTextureSize(266, 155);
      this.Shape1c.mirror = true;
      this.setRotation(this.Shape1c, 0.0F, 0.0F, 0.0F);
      this.Shape2a = new ModelRenderer(this, 63, 16);
      this.Shape2a.addBox(-8.0F, -5.5F, -13.5F, 2, 3, 13);
      this.Shape2a.setRotationPoint(0.0F, 17.0F, 7.0F);
      this.Shape2a.setTextureSize(266, 155);
      this.Shape2a.mirror = true;
      this.setRotation(this.Shape2a, 0.0F, 0.0F, 0.0F);
      this.Shape1d = new ModelRenderer(this, 54, 0);
      this.Shape1d.addBox(6.0F, -4.0F, -6.5F, 2, 6, 2);
      this.Shape1d.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape1d.setTextureSize(266, 155);
      this.Shape1d.mirror = true;
      this.setRotation(this.Shape1d, 0.0F, 0.0F, 0.0F);
      this.Shape1e = new ModelRenderer(this, 54, 0);
      this.Shape1e.addBox(-8.0F, -4.0F, -6.5F, 2, 6, 2);
      this.Shape1e.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape1e.setTextureSize(266, 155);
      this.Shape1e.mirror = true;
      this.setRotation(this.Shape1e, 0.0F, 0.0F, 0.0F);
      this.Shape2b = new ModelRenderer(this, 63, 0);
      this.Shape2b.addBox(-1.5F, -2.3F, -14.5F, 3, 4, 2);
      this.Shape2b.setRotationPoint(0.0F, 16.0F, 7.0F);
      this.Shape2b.setTextureSize(266, 155);
      this.Shape2b.mirror = true;
      this.setRotation(this.Shape2b, 0.0F, 0.0F, 0.0F);
      this.Shape2c = new ModelRenderer(this, 63, 16);
      this.Shape2c.addBox(6.0F, -5.5F, -13.5F, 2, 3, 13);
      this.Shape2c.setRotationPoint(0.0F, 17.0F, 7.0F);
      this.Shape2c.setTextureSize(266, 155);
      this.Shape2c.mirror = true;
      this.setRotation(this.Shape2c, 0.0F, 0.0F, 0.0F);
      this.Shape2d = new ModelRenderer(this, 94, 34);
      this.Shape2d.addBox(-7.5F, -6.0F, -11.0F, 15, 2, 8);
      this.Shape2d.setRotationPoint(0.0F, 16.0F, 7.0F);
      this.Shape2d.setTextureSize(266, 155);
      this.Shape2d.mirror = true;
      this.setRotation(this.Shape2d, 0.0F, 0.0F, 0.0F);
      this.Shape2e = new ModelRenderer(this, 63, 33);
      this.Shape2e.addBox(-8.0F, -7.5F, -11.5F, 2, 2, 9);
      this.Shape2e.setRotationPoint(0.0F, 17.0F, 7.0F);
      this.Shape2e.setTextureSize(266, 155);
      this.Shape2e.mirror = true;
      this.setRotation(this.Shape2e, 0.0F, 0.0F, 0.0F);
      this.Shape2f = new ModelRenderer(this, 63, 33);
      this.Shape2f.addBox(6.0F, -7.5F, -11.5F, 2, 2, 9);
      this.Shape2f.setRotationPoint(0.0F, 17.0F, 7.0F);
      this.Shape2f.setTextureSize(266, 155);
      this.Shape2f.mirror = true;
      this.setRotation(this.Shape2f, 0.0F, 0.0F, 0.0F);
      this.Shape1f = new ModelRenderer(this, 0, 21);
      this.Shape1f.addBox(-8.0F, -3.0F, -13.5F, 16, 2, 13);
      this.Shape1f.setRotationPoint(0.0F, 17.0F, 7.0F);
      this.Shape1f.setTextureSize(266, 155);
      this.Shape1f.mirror = true;
      this.setRotation(this.Shape1f, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape1a.render(f5);
      this.Shape2.render(f5);
      this.Shape1b.render(f5);
      this.Shape1c.render(f5);
      this.Shape2a.render(f5);
      this.Shape1d.render(f5);
      this.Shape1e.render(f5);
      this.Shape2b.render(f5);
      this.Shape2c.render(f5);
      this.Shape2d.render(f5);
      this.Shape2e.render(f5);
      this.Shape2f.render(f5);
      this.Shape1f.render(f5);
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
