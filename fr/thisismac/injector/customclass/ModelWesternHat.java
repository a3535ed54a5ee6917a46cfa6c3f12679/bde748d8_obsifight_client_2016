package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWesternHat extends ModelBase {
   ModelRenderer HatBase;
   ModelRenderer HatTop;
   ModelRenderer HatTopR;
   ModelRenderer HatBrimL;
   ModelRenderer HatBrimR;
   ModelRenderer HatTopL;

   public ModelWesternHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.HatBase = new ModelRenderer(this, 7, 18);
      this.HatBase.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12);
      this.HatBase.setRotationPoint(-6.0F, 17.0F, -6.0F);
      this.HatBase.setTextureSize(64, 32);
      this.HatBase.mirror = true;
      this.setRotation(this.HatBase, 0.0F, 0.0F, 0.0F);
      this.HatTop = new ModelRenderer(this, 11, 2);
      this.HatTop.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10);
      this.HatTop.setRotationPoint(-5.0F, 16.0F, -5.0F);
      this.HatTop.setTextureSize(64, 32);
      this.HatTop.mirror = true;
      this.setRotation(this.HatTop, 0.0F, 0.0F, 0.0F);
      this.HatTopR = new ModelRenderer(this, 21, 19);
      this.HatTopR.addBox(0.0F, -1.0F, 0.0F, 5, 3, 9);
      this.HatTopR.setRotationPoint(-4.0F, 14.1F, -4.5F);
      this.HatTopR.setTextureSize(64, 32);
      this.HatTopR.mirror = true;
      this.setRotation(this.HatTopR, 0.0F, 0.0F, 0.2617994F);
      this.HatBrimL = new ModelRenderer(this, 4, 17);
      this.HatBrimL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 12);
      this.HatBrimL.setRotationPoint(6.7F, 15.8F, -6.0F);
      this.HatBrimL.setTextureSize(64, 32);
      this.HatBrimL.mirror = true;
      this.setRotation(this.HatBrimL, 0.0F, 0.0F, ((float)Math.PI / 4F));
      this.HatBrimL.mirror = false;
      this.HatBrimR = new ModelRenderer(this, 7, 17);
      this.HatBrimR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 12);
      this.HatBrimR.setRotationPoint(-7.4F, 16.5F, -6.0F);
      this.HatBrimR.setTextureSize(64, 32);
      this.HatBrimR.mirror = true;
      this.setRotation(this.HatBrimR, 0.0F, 0.0F, -((float)Math.PI / 4F));
      this.HatTopL = new ModelRenderer(this, 5, 17);
      this.HatTopL.addBox(-4.0F, -1.0F, 0.0F, 5, 3, 9);
      this.HatTopL.setRotationPoint(3.0F, 14.3F, -4.5F);
      this.HatTopL.setTextureSize(64, 32);
      this.HatTopL.mirror = true;
      this.setRotation(this.HatTopL, 0.0F, 0.0F, -0.2617994F);
      this.HatTopL.mirror = false;
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.HatBase.render(f5);
      this.HatTop.render(f5);
      this.HatTopR.render(f5);
      this.HatBrimL.render(f5);
      this.HatBrimR.render(f5);
      this.HatTopL.render(f5);
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
