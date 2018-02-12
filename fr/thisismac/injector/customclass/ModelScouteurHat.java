package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScouteurHat extends ModelBase {
   ModelRenderer Band;
   ModelRenderer BandLeft;
   ModelRenderer Shape1;
   ModelRenderer Shape3;
   ModelRenderer Shape2;
   ModelRenderer BandLeft2;
   ModelRenderer BandLeft3;
   ModelRenderer BandLeft4;
   ModelRenderer BandLeft5;
   ModelRenderer BandLeft6;
   ModelRenderer BandLeft7;
   ModelRenderer BandLeft8;

   public ModelScouteurHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Band = new ModelRenderer(this, 16, 5);
      this.Band.addBox(-4.5F, 0.0F, -0.5F, 9, 1, 1);
      this.Band.setRotationPoint(0.0F, 15.0F, 0.0F);
      this.Band.setTextureSize(64, 32);
      this.Band.mirror = true;
      this.setRotation(this.Band, 0.0F, 0.0F, 0.0F);
      this.BandLeft = new ModelRenderer(this, 16, 8);
      this.BandLeft.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2);
      this.BandLeft.setRotationPoint(-5.0F, 17.0F, 0.0F);
      this.BandLeft.setTextureSize(64, 32);
      this.BandLeft.mirror = true;
      this.setRotation(this.BandLeft, ((float)Math.PI / 4F), 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 13);
      this.Shape1.addBox(0.0F, 0.0F, -6.0F, 0, 1, 6);
      this.Shape1.setRotationPoint(5.0F, 17.5F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 1, 22);
      this.Shape3.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 4);
      this.Shape3.setRotationPoint(5.0F, 18.0F, -5.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, -((float)Math.PI / 2F), 0.0F);
      this.Shape2 = new ModelRenderer(this, 1, 28);
      this.Shape2.addBox(-4.0F, 0.0F, 0.0F, 4, 3, 0);
      this.Shape2.setRotationPoint(4.0F, 18.0F, -5.5F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.BandLeft2 = new ModelRenderer(this, 0, 9);
      this.BandLeft2.addBox(0.0F, -0.5F, -0.5F, 1, 1, 1);
      this.BandLeft2.setRotationPoint(4.5F, 17.0F, 0.0F);
      this.BandLeft2.setTextureSize(64, 32);
      this.BandLeft2.mirror = true;
      this.setRotation(this.BandLeft2, ((float)Math.PI / 4F), 0.0F, 0.0F);
      this.BandLeft3 = new ModelRenderer(this, 16, 8);
      this.BandLeft3.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2);
      this.BandLeft3.setRotationPoint(4.0F, 17.0F, 0.0F);
      this.BandLeft3.setTextureSize(64, 32);
      this.BandLeft3.mirror = true;
      this.setRotation(this.BandLeft3, ((float)Math.PI / 4F), 0.0F, 0.0F);
      this.BandLeft4 = new ModelRenderer(this, 0, 9);
      this.BandLeft4.addBox(0.0F, -0.5F, -0.5F, 1, 1, 1);
      this.BandLeft4.setRotationPoint(-5.5F, 17.0F, 0.0F);
      this.BandLeft4.setTextureSize(64, 32);
      this.BandLeft4.mirror = true;
      this.setRotation(this.BandLeft4, ((float)Math.PI / 4F), 0.0F, 0.0F);
      this.BandLeft5 = new ModelRenderer(this, 24, 8);
      this.BandLeft5.addBox(0.0F, -0.5F, -0.5F, 0, 1, 6);
      this.BandLeft5.setRotationPoint(-5.0F, 17.5F, 0.0F);
      this.BandLeft5.setTextureSize(64, 32);
      this.BandLeft5.mirror = true;
      this.setRotation(this.BandLeft5, -2.141282F, 0.0F, 0.0F);
      this.BandLeft6 = new ModelRenderer(this, 31, 26);
      this.BandLeft6.addBox(0.0F, -0.5F, -0.5F, 0, 1, 3);
      this.BandLeft6.setRotationPoint(-4.5F, 22.0F, -6.5F);
      this.BandLeft6.setTextureSize(64, 32);
      this.BandLeft6.mirror = true;
      this.setRotation(this.BandLeft6, 0.0F, ((float)Math.PI / 2F), 0.0F);
      this.BandLeft7 = new ModelRenderer(this, 28, 16);
      this.BandLeft7.addBox(0.0F, -0.5F, -4.5F, 0, 1, 4);
      this.BandLeft7.setRotationPoint(-5.0F, 22.0F, -2.0F);
      this.BandLeft7.setTextureSize(64, 32);
      this.BandLeft7.mirror = true;
      this.setRotation(this.BandLeft7, 0.0F, 0.0F, 0.0F);
      this.BandLeft8 = new ModelRenderer(this, 26, 28);
      this.BandLeft8.addBox(2.0F, -0.5F, -0.5F, 1, 1, 1);
      this.BandLeft8.setRotationPoint(-4.5F, 22.0F, -6.5F);
      this.BandLeft8.setTextureSize(64, 32);
      this.BandLeft8.mirror = true;
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Band.render(f5);
      this.BandLeft.render(f5);
      this.Shape1.render(f5);
      this.Shape3.render(f5);
      this.Shape2.render(f5);
      this.BandLeft2.render(f5);
      this.BandLeft3.render(f5);
      this.BandLeft4.render(f5);
      this.BandLeft5.render(f5);
      this.BandLeft6.render(f5);
      this.BandLeft7.render(f5);
      this.BandLeft8.render(f5);
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
