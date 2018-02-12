package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHornsHat extends ModelBase {
   ModelRenderer Horn1;
   ModelRenderer Horn2;
   ModelRenderer Horn3;
   ModelRenderer Horn4;
   ModelRenderer Horn5;
   ModelRenderer Horn6;
   ModelRenderer Horn7;
   ModelRenderer Horn8;
   ModelRenderer Ring9;
   ModelRenderer Ring10;
   ModelRenderer Ring11;
   ModelRenderer Ring12;
   ModelRenderer Ring13;
   ModelRenderer Ring14;
   ModelRenderer Ring15;
   ModelRenderer Ring16;

   public ModelHornsHat() {
      this.textureWidth = 46;
      this.textureHeight = 29;
      this.Horn1 = new ModelRenderer(this, 16, 6);
      this.Horn1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.Horn1.setRotationPoint(-8.0F, 18.0F, -2.0F);
      this.Horn1.setTextureSize(46, 29);
      this.Horn1.mirror = true;
      this.setRotation(this.Horn1, 0.0F, 0.0F, 0.0F);
      this.Horn2 = new ModelRenderer(this, 0, 0);
      this.Horn2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
      this.Horn2.setRotationPoint(7.0F, 17.0F, -2.0F);
      this.Horn2.setTextureSize(46, 29);
      this.Horn2.mirror = true;
      this.setRotation(this.Horn2, 0.0F, 0.0F, 0.0F);
      this.Horn3 = new ModelRenderer(this, 0, 0);
      this.Horn3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Horn3.setRotationPoint(-9.8F, 15.0F, -1.5F);
      this.Horn3.setTextureSize(46, 29);
      this.Horn3.mirror = true;
      this.setRotation(this.Horn3, 0.0F, 0.0F, 0.0F);
      this.Horn4 = new ModelRenderer(this, 0, 0);
      this.Horn4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Horn4.setRotationPoint(-9.5F, 14.0F, -1.0F);
      this.Horn4.setTextureSize(46, 29);
      this.Horn4.mirror = true;
      this.setRotation(this.Horn4, 0.0F, 0.0F, 0.0F);
      this.Horn5 = new ModelRenderer(this, 16, 0);
      this.Horn5.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.Horn5.setRotationPoint(4.0F, 18.0F, -2.0F);
      this.Horn5.setTextureSize(46, 29);
      this.Horn5.mirror = true;
      this.setRotation(this.Horn5, 0.0F, 0.0F, 0.0F);
      this.Horn6 = new ModelRenderer(this, 0, 0);
      this.Horn6.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
      this.Horn6.setRotationPoint(-10.0F, 17.0F, -2.0F);
      this.Horn6.setTextureSize(46, 29);
      this.Horn6.mirror = true;
      this.setRotation(this.Horn6, 0.0F, 0.0F, 0.0F);
      this.Horn7 = new ModelRenderer(this, 0, 0);
      this.Horn7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Horn7.setRotationPoint(7.8F, 15.0F, -1.5F);
      this.Horn7.setTextureSize(46, 29);
      this.Horn7.mirror = true;
      this.setRotation(this.Horn7, 0.0F, 0.0F, 0.0F);
      this.Horn8 = new ModelRenderer(this, 0, 0);
      this.Horn8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Horn8.setRotationPoint(8.5F, 14.0F, -1.0F);
      this.Horn8.setTextureSize(46, 29);
      this.Horn8.mirror = true;
      this.setRotation(this.Horn8, 0.0F, 0.0F, 0.0F);
      this.Ring9 = new ModelRenderer(this, 0, 10);
      this.Ring9.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Ring9.setRotationPoint(6.0F, 18.0F, 2.0F);
      this.Ring9.setTextureSize(46, 29);
      this.Ring9.mirror = true;
      this.setRotation(this.Ring9, 0.0F, 0.0F, 0.0F);
      this.Ring10 = new ModelRenderer(this, 0, 10);
      this.Ring10.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Ring10.setRotationPoint(6.0F, 18.0F, -4.0F);
      this.Ring10.setTextureSize(46, 29);
      this.Ring10.mirror = true;
      this.setRotation(this.Ring10, 0.0F, 0.0F, 0.0F);
      this.Ring11 = new ModelRenderer(this, 0, 9);
      this.Ring11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Ring11.setRotationPoint(6.0F, 17.0F, -3.0F);
      this.Ring11.setTextureSize(46, 29);
      this.Ring11.mirror = true;
      this.setRotation(this.Ring11, 0.0F, 0.0F, 0.0F);
      this.Ring12 = new ModelRenderer(this, 0, 9);
      this.Ring12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Ring12.setRotationPoint(6.0F, 21.0F, -3.0F);
      this.Ring12.setTextureSize(46, 29);
      this.Ring12.mirror = true;
      this.setRotation(this.Ring12, 0.0F, 0.0F, 0.0F);
      this.Ring13 = new ModelRenderer(this, 0, 9);
      this.Ring13.addBox(-10.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Ring13.setRotationPoint(3.0F, 17.0F, -3.0F);
      this.Ring13.setTextureSize(46, 29);
      this.Ring13.mirror = true;
      this.setRotation(this.Ring13, 0.0F, 0.0F, 0.0F);
      this.Ring14 = new ModelRenderer(this, 0, 9);
      this.Ring14.addBox(-10.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Ring14.setRotationPoint(3.0F, 21.0F, -3.0F);
      this.Ring14.setTextureSize(46, 29);
      this.Ring14.mirror = true;
      this.setRotation(this.Ring14, 0.0F, 0.0F, 0.0F);
      this.Ring15 = new ModelRenderer(this, 0, 10);
      this.Ring15.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Ring15.setRotationPoint(-7.0F, 18.0F, 2.0F);
      this.Ring15.setTextureSize(46, 29);
      this.Ring15.mirror = true;
      this.setRotation(this.Ring15, 0.0F, 0.0F, 0.0F);
      this.Ring16 = new ModelRenderer(this, 0, 10);
      this.Ring16.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Ring16.setRotationPoint(-7.0F, 18.0F, -4.0F);
      this.Ring16.setTextureSize(46, 29);
      this.Ring16.mirror = true;
      this.setRotation(this.Ring16, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Horn1.render(f5);
      this.Horn2.render(f5);
      this.Horn3.render(f5);
      this.Horn4.render(f5);
      this.Horn5.render(f5);
      this.Horn6.render(f5);
      this.Horn7.render(f5);
      this.Horn8.render(f5);
      this.Ring9.render(f5);
      this.Ring10.render(f5);
      this.Ring11.render(f5);
      this.Ring12.render(f5);
      this.Ring13.render(f5);
      this.Ring14.render(f5);
      this.Ring15.render(f5);
      this.Ring16.render(f5);
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
