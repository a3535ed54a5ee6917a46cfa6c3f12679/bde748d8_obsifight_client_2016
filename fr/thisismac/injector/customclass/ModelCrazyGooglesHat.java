package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrazyGooglesHat extends ModelBase {
   ModelRenderer Shape3l;
   ModelRenderer LT;
   ModelRenderer LB;
   ModelRenderer LL;
   ModelRenderer LR;
   ModelRenderer RL;
   ModelRenderer RT;
   ModelRenderer RB;
   ModelRenderer RR;
   ModelRenderer Leftln;
   ModelRenderer Rgithln;
   ModelRenderer Shape2la;
   ModelRenderer Shape1lz;
   ModelRenderer Shape3ld;
   ModelRenderer Shape1le;
   ModelRenderer Shape2lr;
   ModelRenderer Shape3ls;
   ModelRenderer Shape4lw;

   public ModelCrazyGooglesHat() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape3l = new ModelRenderer(this, 14, 20);
      this.Shape3l.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape3l.setRotationPoint(-1.0F, 14.5F, -5.0F);
      this.Shape3l.setTextureSize(64, 32);
      this.Shape3l.mirror = true;
      this.setRotation(this.Shape3l, -0.1115358F, 0.0F, 0.0F);
      this.LT = new ModelRenderer(this, 4, 17);
      this.LT.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.LT.setRotationPoint(-4.0F, 13.0F, -4.8F);
      this.LT.setTextureSize(64, 32);
      this.LT.mirror = true;
      this.setRotation(this.LT, -0.1745329F, 0.0F, 0.0F);
      this.LB = new ModelRenderer(this, 4, 22);
      this.LB.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.LB.setRotationPoint(-4.0F, 16.0F, -5.4F);
      this.LB.setTextureSize(64, 32);
      this.LB.mirror = true;
      this.setRotation(this.LB, -0.1745329F, 0.0F, 0.0F);
      this.LL = new ModelRenderer(this, 0, 19);
      this.LL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.LL.setRotationPoint(-5.0F, 14.0F, -5.0F);
      this.LL.setTextureSize(64, 32);
      this.LL.mirror = true;
      this.setRotation(this.LL, -0.1745329F, 0.0F, 0.0F);
      this.LR = new ModelRenderer(this, 10, 19);
      this.LR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.LR.setRotationPoint(-2.0F, 14.0F, -5.0F);
      this.LR.setTextureSize(64, 32);
      this.LR.mirror = true;
      this.setRotation(this.LR, -0.1745329F, 0.0F, 0.0F);
      this.RL = new ModelRenderer(this, 20, 19);
      this.RL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.RL.setRotationPoint(1.0F, 14.0F, -5.0F);
      this.RL.setTextureSize(64, 32);
      this.RL.mirror = true;
      this.setRotation(this.RL, -0.1745329F, 0.0F, 0.0F);
      this.RT = new ModelRenderer(this, 24, 17);
      this.RT.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.RT.setRotationPoint(2.0F, 13.0F, -4.8F);
      this.RT.setTextureSize(64, 32);
      this.RT.mirror = true;
      this.setRotation(this.RT, -0.1745329F, 0.0F, 0.0F);
      this.RB = new ModelRenderer(this, 24, 22);
      this.RB.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.RB.setRotationPoint(2.0F, 16.0F, -5.4F);
      this.RB.setTextureSize(64, 32);
      this.RB.mirror = true;
      this.setRotation(this.RB, -0.1745329F, 0.0F, 0.0F);
      this.RR = new ModelRenderer(this, 30, 19);
      this.RR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.RR.setRotationPoint(4.0F, 14.0F, -5.0F);
      this.RR.setTextureSize(64, 32);
      this.RR.mirror = true;
      this.setRotation(this.RR, -0.1745329F, 0.0F, 0.0F);
      this.Leftln = new ModelRenderer(this, 0, 29);
      this.Leftln.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Leftln.setRotationPoint(-4.0F, 14.0F, -4.8F);
      this.Leftln.setTextureSize(64, 32);
      this.Leftln.mirror = true;
      this.setRotation(this.Leftln, -0.1745329F, 0.0F, 0.0F);
      this.Rgithln = new ModelRenderer(this, 7, 29);
      this.Rgithln.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Rgithln.setRotationPoint(2.0F, 14.0F, -4.8F);
      this.Rgithln.setTextureSize(64, 32);
      this.Rgithln.mirror = true;
      this.setRotation(this.Rgithln, -0.1745329F, 0.0F, 0.0F);
      this.Shape2la = new ModelRenderer(this, 11, 10);
      this.Shape2la.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape2la.setRotationPoint(-4.5F, 15.8F, -1.0F);
      this.Shape2la.setTextureSize(64, 32);
      this.Shape2la.mirror = true;
      this.setRotation(this.Shape2la, -0.2268928F, 0.0F, 0.0F);
      this.Shape1lz = new ModelRenderer(this, 0, 10);
      this.Shape1lz.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape1lz.setRotationPoint(-4.9F, 14.5F, -4.466667F);
      this.Shape1lz.setTextureSize(64, 32);
      this.Shape1lz.mirror = true;
      this.setRotation(this.Shape1lz, -0.3490659F, 0.1115358F, 0.0F);
      this.Shape3ld = new ModelRenderer(this, 22, 12);
      this.Shape3ld.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape3ld.setRotationPoint(-4.5F, 16.6F, 2.1F);
      this.Shape3ld.setTextureSize(64, 32);
      this.Shape3ld.mirror = true;
      this.setRotation(this.Shape3ld, -0.122173F, 0.0F, 0.0F);
      this.Shape1le = new ModelRenderer(this, 0, 1);
      this.Shape1le.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape1le.setRotationPoint(3.9F, 14.5F, -4.5F);
      this.Shape1le.setTextureSize(64, 32);
      this.Shape1le.mirror = true;
      this.setRotation(this.Shape1le, -0.3490659F, -0.111544F, 0.0F);
      this.Shape2lr = new ModelRenderer(this, 11, 1);
      this.Shape2lr.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape2lr.setRotationPoint(3.5F, 15.8F, -1.0F);
      this.Shape2lr.setTextureSize(64, 32);
      this.Shape2lr.mirror = true;
      this.setRotation(this.Shape2lr, -0.2268928F, 0.0F, 0.0F);
      this.Shape3ls = new ModelRenderer(this, 22, 3);
      this.Shape3ls.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape3ls.setRotationPoint(3.5F, 16.6F, 2.133333F);
      this.Shape3ls.setTextureSize(64, 32);
      this.Shape3ls.mirror = true;
      this.setRotation(this.Shape3ls, -0.122173F, 0.0F, 0.0F);
      this.Shape4lw = new ModelRenderer(this, 0, 7);
      this.Shape4lw.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1);
      this.Shape4lw.setRotationPoint(-4.5F, 16.8F, 4.0F);
      this.Shape4lw.setTextureSize(64, 32);
      this.Shape4lw.mirror = true;
      this.setRotation(this.Shape4lw, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape3l.render(f5);
      this.LT.render(f5);
      this.LB.render(f5);
      this.LL.render(f5);
      this.LR.render(f5);
      this.RL.render(f5);
      this.RT.render(f5);
      this.RB.render(f5);
      this.RR.render(f5);
      this.Leftln.render(f5);
      this.Rgithln.render(f5);
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
