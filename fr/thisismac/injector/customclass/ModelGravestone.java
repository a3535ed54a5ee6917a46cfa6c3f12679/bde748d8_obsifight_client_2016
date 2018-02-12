package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGravestone extends ModelBase {
   ModelRenderer Shape1a;
   ModelRenderer Shape1b;

   public ModelGravestone() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Shape1a = new ModelRenderer(this, 36, 0);
      this.Shape1a.addBox(0.0F, 0.0F, 0.0F, 10, 2, 3);
      this.Shape1a.setRotationPoint(-4.0F, 7.0F, 3.3F);
      this.Shape1a.setTextureSize(128, 64);
      this.Shape1a.mirror = true;
      this.setRotation(this.Shape1a, -0.1745329F, -0.0872665F, 0.0872665F);
      this.Shape1b = new ModelRenderer(this, 0, 0);
      this.Shape1b.addBox(0.0F, 0.0F, 0.0F, 12, 16, 3);
      this.Shape1b.setRotationPoint(-5.0F, 8.0F, 3.0F);
      this.Shape1b.setTextureSize(128, 64);
      this.Shape1b.mirror = true;
      this.setRotation(this.Shape1b, -0.1745329F, -0.0872665F, 0.0872665F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1a.render(f5);
      this.Shape1b.render(f5);
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
