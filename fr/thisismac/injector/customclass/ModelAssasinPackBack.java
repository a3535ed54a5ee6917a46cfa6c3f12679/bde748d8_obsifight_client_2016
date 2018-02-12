package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAssasinPackBack extends ModelBase {
   ModelRenderer LeftStrap1;
   ModelRenderer LeftStrap2;
   ModelRenderer LeftStrap3;
   ModelRenderer LeftStrap4;
   ModelRenderer RightStrap1;
   ModelRenderer RightStrap2;
   ModelRenderer RightStrap3;
   ModelRenderer RightStrap4;
   ModelRenderer FrontStrap1;
   ModelRenderer FrontStrap2;
   ModelRenderer BackStrap1;
   ModelRenderer BackStrap2;
   ModelRenderer BackStrap3;
   ModelRenderer BackStrap4;
   ModelRenderer BladeSheath;
   ModelRenderer BladeHilt;
   ModelRenderer BladeGrip;
   ModelRenderer BladePommel;
   ModelRenderer QuickBox;
   ModelRenderer SmallPouchL;
   ModelRenderer SmallPouchR;
   ModelRenderer DaggerHandleL;
   ModelRenderer DaggerL;
   ModelRenderer DaggerPointL;
   ModelRenderer DaggerHandleR;
   ModelRenderer DaggerR;
   ModelRenderer DaggerPointR;
   ModelRenderer StrongBox;
   ModelRenderer StrongBoxLid;
   ModelRenderer StrongBoxClasp;
   ModelRenderer Bead1;
   ModelRenderer Bead2;
   ModelRenderer Bead3;
   ModelRenderer Jar;
   ModelRenderer JarTop;
   ModelRenderer JarCork;
   ModelRenderer Poison;

   public ModelAssasinPackBack() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.LeftStrap1 = new ModelRenderer(this, 4, 0);
      this.LeftStrap1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.LeftStrap1.setRotationPoint(3.6F, -0.5F, 3.0F);
      this.LeftStrap1.setTextureSize(64, 64);
      this.LeftStrap1.mirror = true;
      this.setRotation(this.LeftStrap1, -((float)Math.PI / 2F), 0.0F, 0.0F);
      this.LeftStrap2 = new ModelRenderer(this, 10, 0);
      this.LeftStrap2.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 1);
      this.LeftStrap2.setRotationPoint(4.6F, 0.5F, -3.0F);
      this.LeftStrap2.setTextureSize(64, 64);
      this.LeftStrap2.mirror = true;
      this.setRotation(this.LeftStrap2, 0.0F, 0.0F, 0.1396263F);
      this.LeftStrap3 = new ModelRenderer(this, 10, 0);
      this.LeftStrap3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.LeftStrap3.setRotationPoint(3.0F, 4.5F, -3.0F);
      this.LeftStrap3.setTextureSize(64, 64);
      this.LeftStrap3.mirror = true;
      this.setRotation(this.LeftStrap3, 0.0F, 0.0F, -0.0872665F);
      this.LeftStrap4 = new ModelRenderer(this, 4, 0);
      this.LeftStrap4.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.LeftStrap4.setRotationPoint(3.4F, 8.0F, 3.0F);
      this.LeftStrap4.setTextureSize(64, 64);
      this.LeftStrap4.mirror = true;
      this.setRotation(this.LeftStrap4, -((float)Math.PI / 2F), 0.0F, 0.0F);
      this.RightStrap1 = new ModelRenderer(this, 4, 0);
      this.RightStrap1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.RightStrap1.setRotationPoint(-4.6F, -0.5F, 3.0F);
      this.RightStrap1.setTextureSize(64, 64);
      this.RightStrap1.mirror = true;
      this.setRotation(this.RightStrap1, -((float)Math.PI / 2F), 0.0F, 0.0F);
      this.RightStrap2 = new ModelRenderer(this, 10, 0);
      this.RightStrap2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.RightStrap2.setRotationPoint(-4.6F, 0.5F, -3.0F);
      this.RightStrap2.setTextureSize(64, 64);
      this.RightStrap2.mirror = true;
      this.setRotation(this.RightStrap2, 0.0F, 0.0F, -0.2094395F);
      this.RightStrap3 = new ModelRenderer(this, 10, 0);
      this.RightStrap3.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.RightStrap3.setRotationPoint(-4.0F, 2.5F, -3.0F);
      this.RightStrap3.setTextureSize(64, 64);
      this.RightStrap3.mirror = true;
      this.setRotation(this.RightStrap3, 0.0F, 0.0F, 0.0698132F);
      this.RightStrap4 = new ModelRenderer(this, 4, 0);
      this.RightStrap4.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.RightStrap4.setRotationPoint(-4.4F, 8.0F, 3.0F);
      this.RightStrap4.setTextureSize(64, 64);
      this.RightStrap4.mirror = true;
      this.setRotation(this.RightStrap4, -((float)Math.PI / 2F), 0.0F, 0.0F);
      this.FrontStrap1 = new ModelRenderer(this, 10, 0);
      this.FrontStrap1.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1);
      this.FrontStrap1.setRotationPoint(-4.0F, 3.0F, -3.0F);
      this.FrontStrap1.setTextureSize(64, 64);
      this.FrontStrap1.mirror = true;
      this.setRotation(this.FrontStrap1, 0.0F, 0.0F, -1.466077F);
      this.FrontStrap2 = new ModelRenderer(this, 10, 0);
      this.FrontStrap2.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1);
      this.FrontStrap2.setRotationPoint(-4.0F, 5.0F, -3.0F);
      this.FrontStrap2.setTextureSize(64, 64);
      this.FrontStrap2.mirror = true;
      this.setRotation(this.FrontStrap2, 0.0F, 0.0F, -1.518436F);
      this.BackStrap1 = new ModelRenderer(this, 0, 0);
      this.BackStrap1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
      this.BackStrap1.setRotationPoint(-4.5F, 0.5F, 2.0F);
      this.BackStrap1.setTextureSize(64, 64);
      this.BackStrap1.mirror = true;
      this.setRotation(this.BackStrap1, 0.0F, 0.0F, -0.6806784F);
      this.BackStrap2 = new ModelRenderer(this, 0, 0);
      this.BackStrap2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.BackStrap2.setRotationPoint(-0.3F, 5.8F, 2.0F);
      this.BackStrap2.setTextureSize(64, 64);
      this.BackStrap2.mirror = true;
      this.setRotation(this.BackStrap2, 0.0F, 0.0F, -0.9075712F);
      this.BackStrap3 = new ModelRenderer(this, 0, 0);
      this.BackStrap3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
      this.BackStrap3.setRotationPoint(3.8F, -0.3F, 2.0F);
      this.BackStrap3.setTextureSize(64, 64);
      this.BackStrap3.mirror = true;
      this.setRotation(this.BackStrap3, 0.0F, 0.0F, ((float)Math.PI / 5F));
      this.BackStrap4 = new ModelRenderer(this, 0, 0);
      this.BackStrap4.addBox(0.0F, 0.4F, 0.0F, 1, 5, 1);
      this.BackStrap4.setRotationPoint(0.0F, 5.0F, 2.0F);
      this.BackStrap4.setTextureSize(64, 64);
      this.BackStrap4.mirror = true;
      this.setRotation(this.BackStrap4, 0.0F, 0.0F, 0.9424778F);
      this.BladeSheath = new ModelRenderer(this, 0, 44);
      this.BladeSheath.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2);
      this.BladeSheath.setRotationPoint(4.0F, -1.0F, 2.5F);
      this.BladeSheath.setTextureSize(64, 64);
      this.BladeSheath.mirror = true;
      this.setRotation(this.BladeSheath, 0.1047198F, 0.0F, 0.5585054F);
      this.BladeHilt = new ModelRenderer(this, 4, 58);
      this.BladeHilt.addBox(-0.5F, -1.0F, 0.5F, 3, 1, 1);
      this.BladeHilt.setRotationPoint(4.0F, -1.0F, 2.5F);
      this.BladeHilt.setTextureSize(64, 64);
      this.BladeHilt.mirror = true;
      this.setRotation(this.BladeHilt, 0.1047198F, 0.0F, 0.5585054F);
      this.BladeGrip = new ModelRenderer(this, 0, 59);
      this.BladeGrip.addBox(0.5F, -5.0F, 0.6F, 1, 4, 1);
      this.BladeGrip.setRotationPoint(4.0F, -1.0F, 2.5F);
      this.BladeGrip.setTextureSize(64, 64);
      this.BladeGrip.mirror = true;
      this.setRotation(this.BladeGrip, 0.1047198F, 0.0F, 0.5585054F);
      this.BladePommel = new ModelRenderer(this, 4, 60);
      this.BladePommel.addBox(-3.5F, -4.5F, 0.5F, 1, 1, 1);
      this.BladePommel.setRotationPoint(4.0F, -1.0F, 2.5F);
      this.BladePommel.setTextureSize(64, 64);
      this.BladePommel.mirror = true;
      this.setRotation(this.BladePommel, 0.1047198F, 0.0F, 1.396263F);
      this.QuickBox = new ModelRenderer(this, 8, 16);
      this.QuickBox.addBox(0.0F, 0.0F, 0.0F, 3, 4, 2);
      this.QuickBox.setRotationPoint(-5.0F, 1.5F, 3.0F);
      this.QuickBox.setTextureSize(64, 64);
      this.QuickBox.mirror = true;
      this.setRotation(this.QuickBox, 0.0F, 0.0F, -0.5410521F);
      this.SmallPouchL = new ModelRenderer(this, 0, 15);
      this.SmallPouchL.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3);
      this.SmallPouchL.setRotationPoint(4.5F, 7.0F, 1.0F);
      this.SmallPouchL.setTextureSize(64, 64);
      this.SmallPouchL.mirror = true;
      this.setRotation(this.SmallPouchL, 0.0698132F, -0.2443461F, 0.0698132F);
      this.SmallPouchR = new ModelRenderer(this, 0, 15);
      this.SmallPouchR.addBox(0.0F, 0.0F, -3.0F, 1, 4, 3);
      this.SmallPouchR.setRotationPoint(-4.5F, 7.0F, 1.0F);
      this.SmallPouchR.setTextureSize(64, 64);
      this.SmallPouchR.mirror = true;
      this.setRotation(this.SmallPouchR, -0.0698132F, -2.897247F, 0.0698132F);
      this.DaggerHandleL = new ModelRenderer(this, 8, 49);
      this.DaggerHandleL.addBox(0.5F, -3.0F, 0.0F, 1, 3, 1);
      this.DaggerHandleL.setRotationPoint(2.0F, 5.0F, -4.0F);
      this.DaggerHandleL.setTextureSize(64, 64);
      this.DaggerHandleL.mirror = true;
      this.setRotation(this.DaggerHandleL, 0.0F, -0.3839724F, -0.2617994F);
      this.DaggerL = new ModelRenderer(this, 8, 44);
      this.DaggerL.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
      this.DaggerL.setRotationPoint(2.0F, 5.0F, -4.0F);
      this.DaggerL.setTextureSize(64, 64);
      this.DaggerL.mirror = true;
      this.setRotation(this.DaggerL, 0.0F, -0.3839724F, -0.2617994F);
      this.DaggerPointL = new ModelRenderer(this, 8, 44);
      this.DaggerPointL.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.DaggerPointL.setRotationPoint(2.5F, 9.0F, -3.8F);
      this.DaggerPointL.setTextureSize(64, 64);
      this.DaggerPointL.mirror = true;
      this.setRotation(this.DaggerPointL, 0.0F, -0.3839724F, -1.029744F);
      this.DaggerHandleR = new ModelRenderer(this, 8, 49);
      this.DaggerHandleR.addBox(-1.5F, -3.0F, 0.0F, 1, 3, 1);
      this.DaggerHandleR.setRotationPoint(-2.0F, 5.0F, -4.0F);
      this.DaggerHandleR.setTextureSize(64, 64);
      this.DaggerHandleR.mirror = true;
      this.setRotation(this.DaggerHandleR, 0.0F, 0.3839724F, 0.2617994F);
      this.DaggerR = new ModelRenderer(this, 8, 44);
      this.DaggerR.addBox(-2.0F, 0.0F, 0.0F, 2, 4, 1);
      this.DaggerR.setRotationPoint(-2.0F, 5.0F, -4.0F);
      this.DaggerR.setTextureSize(64, 64);
      this.DaggerR.mirror = true;
      this.setRotation(this.DaggerR, 0.0F, 0.3839724F, 0.2617994F);
      this.DaggerPointR = new ModelRenderer(this, 8, 44);
      this.DaggerPointR.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 1);
      this.DaggerPointR.setRotationPoint(-2.5F, 9.0F, -3.8F);
      this.DaggerPointR.setTextureSize(64, 64);
      this.DaggerPointR.mirror = true;
      this.setRotation(this.DaggerPointR, 0.0F, 0.3839724F, 1.099557F);
      this.StrongBox = new ModelRenderer(this, 0, 37);
      this.StrongBox.addBox(0.0F, 0.0F, -3.0F, 5, 5, 2);
      this.StrongBox.setRotationPoint(2.5F, 2.0F, 4.0F);
      this.StrongBox.setTextureSize(64, 64);
      this.StrongBox.mirror = true;
      this.setRotation(this.StrongBox, 0.0F, (float)Math.PI, 0.0F);
      this.StrongBoxLid = new ModelRenderer(this, 0, 32);
      this.StrongBoxLid.addBox(0.0F, 0.0F, 0.0F, 6, 3, 2);
      this.StrongBoxLid.setRotationPoint(3.0F, 0.5F, 5.0F);
      this.StrongBoxLid.setTextureSize(64, 64);
      this.StrongBoxLid.mirror = true;
      this.setRotation(this.StrongBoxLid, -1.029744F, (float)Math.PI, 0.0F);
      this.StrongBoxClasp = new ModelRenderer(this, 14, 37);
      this.StrongBoxClasp.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.StrongBoxClasp.setRotationPoint(0.5F, 2.1F, 7.5F);
      this.StrongBoxClasp.setTextureSize(64, 64);
      this.StrongBoxClasp.mirror = true;
      this.setRotation(this.StrongBoxClasp, 0.0F, (float)Math.PI, 0.0F);
      this.Bead1 = new ModelRenderer(this, 0, 9);
      this.Bead1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Bead1.setRotationPoint(5.0F, 1.0F, 4.0F);
      this.Bead1.setTextureSize(64, 64);
      this.Bead1.mirror = true;
      this.setRotation(this.Bead1, -0.1396263F, -0.3490659F, 0.2268928F);
      this.Bead2 = new ModelRenderer(this, 0, 9);
      this.Bead2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Bead2.setRotationPoint(4.0F, 3.0F, 4.0F);
      this.Bead2.setTextureSize(64, 64);
      this.Bead2.mirror = true;
      this.setRotation(this.Bead2, -0.1396263F, -0.3490659F, 0.0872665F);
      this.Bead3 = new ModelRenderer(this, 0, 9);
      this.Bead3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Bead3.setRotationPoint(3.0F, 4.5F, 4.0F);
      this.Bead3.setTextureSize(64, 64);
      this.Bead3.mirror = true;
      this.setRotation(this.Bead3, 0.1570796F, -0.3490659F, 0.2268928F);
      this.Jar = new ModelRenderer(this, 0, 23);
      this.Jar.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
      this.Jar.setRotationPoint(0.5F, 8.3F, 2.0F);
      this.Jar.setTextureSize(64, 64);
      this.Jar.mirror = true;
      this.setRotation(this.Jar, 0.0F, 0.0F, 0.0F);
      this.JarTop = new ModelRenderer(this, 0, 29);
      this.JarTop.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.JarTop.setRotationPoint(1.0F, 7.3F, 2.5F);
      this.JarTop.setTextureSize(64, 64);
      this.JarTop.mirror = true;
      this.setRotation(this.JarTop, 0.0F, 0.0F, 0.0F);
      this.JarCork = new ModelRenderer(this, 12, 28);
      this.JarCork.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.JarCork.setRotationPoint(1.5F, 7.0F, 3.0F);
      this.JarCork.setTextureSize(64, 64);
      this.JarCork.mirror = true;
      this.setRotation(this.JarCork, 0.0F, 0.0F, 0.0F);
      this.Poison = new ModelRenderer(this, 12, 23);
      this.Poison.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
      this.Poison.setRotationPoint(0.5F, 9.3F, 2.0F);
      this.Poison.setTextureSize(64, 64);
      this.Poison.mirror = true;
      this.setRotation(this.Poison, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.LeftStrap1.render(f5);
      this.LeftStrap2.render(f5);
      this.LeftStrap3.render(f5);
      this.LeftStrap4.render(f5);
      this.RightStrap1.render(f5);
      this.RightStrap2.render(f5);
      this.RightStrap3.render(f5);
      this.RightStrap4.render(f5);
      this.FrontStrap1.render(f5);
      this.FrontStrap2.render(f5);
      this.BackStrap1.render(f5);
      this.BackStrap2.render(f5);
      this.BackStrap3.render(f5);
      this.BackStrap4.render(f5);
      this.BladeSheath.render(f5);
      this.BladeHilt.render(f5);
      this.BladeGrip.render(f5);
      this.BladePommel.render(f5);
      this.QuickBox.render(f5);
      this.SmallPouchL.render(f5);
      this.SmallPouchR.render(f5);
      this.DaggerHandleL.render(f5);
      this.DaggerL.render(f5);
      this.DaggerPointL.render(f5);
      this.DaggerHandleR.render(f5);
      this.DaggerR.render(f5);
      this.DaggerPointR.render(f5);
      this.StrongBox.render(f5);
      this.StrongBoxLid.render(f5);
      this.StrongBoxClasp.render(f5);
      this.Bead1.render(f5);
      this.Bead2.render(f5);
      this.Bead3.render(f5);
      this.Jar.render(f5);
      this.JarTop.render(f5);
      this.JarCork.render(f5);
      this.Poison.render(f5);
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
