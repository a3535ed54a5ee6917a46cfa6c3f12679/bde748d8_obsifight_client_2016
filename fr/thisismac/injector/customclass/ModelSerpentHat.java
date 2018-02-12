package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSerpentHat extends ModelBase {
   public ModelRenderer head = new ModelRenderer(this, 0, 0);
   public ModelRenderer tongue;
   public ModelRenderer body1;
   public ModelRenderer body2;
   public ModelRenderer body3;
   public ModelRenderer body4;
   public ModelRenderer body5;
   public ModelRenderer body6;
   public ModelRenderer body7;
   public ModelRenderer body8;
   public ModelRenderer body9;
   public int textureWidth = 64;
   public int textureHeight = 32;
   protected double distanceMovedTotal = 0.0D;
   protected static final double CYCLES_PER_BLOCK = 3.0D;
   protected int cycleIndex = 0;
   protected float[][] undulationCycle = new float[][]{{45.0F, -45.0F, -45.0F, 0.0F, 45.0F, 45.0F, 0.0F, -45.0F}, {0.0F, 45.0F, -45.0F, -45.0F, 0.0F, 45.0F, 45.0F, 0.0F}, {-45.0F, 90.0F, 0.0F, -45.0F, -45.0F, 0.0F, 45.0F, 45.0F}, {-45.0F, 45.0F, 45.0F, 0.0F, -45.0F, -45.0F, 0.0F, 45.0F}, {0.0F, -45.0F, 45.0F, 45.0F, 0.0F, -45.0F, -45.0F, 0.0F}, {45.0F, -90.0F, 0.0F, 45.0F, 45.0F, 0.0F, -45.0F, -45.0F}};

   public ModelSerpentHat() {
      this.head.addBox(-2.5F, -1.0F, -5.0F, 5, 2, 5);
      this.head.setRotationPoint(0.0F, 23.0F, -8.0F);
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.tongue = new ModelRenderer(this, 0, 13);
      this.tongue.addBox(-0.5F, -0.5F, -10.0F, 1, 1, 5);
      this.tongue.setRotationPoint(0.0F, 23.0F, -8.0F);
      this.setRotation(this.tongue, 0.0F, 0.0F, 0.0F);
      this.body1 = new ModelRenderer(this, 20, 20);
      this.body1.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 5);
      this.body1.setRotationPoint(0.0F, 23.0F, -8.0F);
      this.setRotation(this.body1, 0.0F, 0.0F, 0.0F);
      this.body2 = new ModelRenderer(this, 20, 20);
      this.body2.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 5);
      this.body2.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.body1.addChild(this.body2);
      this.setRotation(this.body2, 0.0F, this.undulationCycle[0][0], 0.0F);
      this.body3 = new ModelRenderer(this, 20, 20);
      this.body3.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 5);
      this.body3.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body3, 0.0F, this.undulationCycle[0][1], 0.0F);
      this.body2.addChild(this.body3);
      this.body4 = new ModelRenderer(this, 20, 20);
      this.body4.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 5);
      this.body4.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body4, 0.0F, this.undulationCycle[0][2], 0.0F);
      this.body3.addChild(this.body4);
      this.body5 = new ModelRenderer(this, 20, 20);
      this.body5.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 5);
      this.body5.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body5, 0.0F, this.undulationCycle[0][3], 0.0F);
      this.body4.addChild(this.body5);
      this.body6 = new ModelRenderer(this, 20, 20);
      this.body6.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 5);
      this.body6.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body6, 0.0F, this.undulationCycle[0][4], 0.0F);
      this.body5.addChild(this.body6);
      this.body7 = new ModelRenderer(this, 30, 0);
      this.body7.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 5);
      this.body7.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body7, 0.0F, this.undulationCycle[0][5], 0.0F);
      this.body6.addChild(this.body7);
      this.body8 = new ModelRenderer(this, 30, 0);
      this.body8.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 5);
      this.body8.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body8, 0.0F, this.undulationCycle[0][6], 0.0F);
      this.body7.addChild(this.body8);
      this.body9 = new ModelRenderer(this, 22, 12);
      this.body9.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 5);
      this.body9.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.setRotation(this.body9, 0.0F, this.undulationCycle[0][7], 0.0F);
      this.body8.addChild(this.body9);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.tongue.render(f5);
      this.body1.render(f5);
      this.body2.render(f5);
      this.body3.render(f5);
      this.body4.render(f5);
      this.body5.render(f5);
      this.body6.render(f5);
      this.body7.render(f5);
      this.body8.render(f5);
      this.body9.render(f5);
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
