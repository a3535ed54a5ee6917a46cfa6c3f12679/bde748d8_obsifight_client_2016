package fr.thisismac.injector.customclass;

import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBigWolf extends RenderLiving {
   ResourceLocation base = new ResourceLocation("textures/entity/wolf/big/wolfblack.png");

   public RenderBigWolf(ModelBase p_i1269_1_, ModelBase p_i1269_2_, float p_i1269_3_) {
      super(p_i1269_1_, p_i1269_3_);
      this.setRenderPassModel(p_i1269_2_);
   }

   protected int shouldRenderPass(EntityBigWolf p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return -1;
   }

   protected ResourceLocation getEntityTexture(EntityBigWolf p_110775_1_) {
      Random r = new Random();
      int x = r.nextInt(9);
      String vari = "";
      if(x == 0) {
         vari = "wolfblack";
      }

      if(x == 1) {
         vari = "wolfbright";
      }

      if(x == 2) {
         vari = "wolfbrown";
      }

      if(x == 3) {
         vari = "wolfdark";
      }

      if(x == 4) {
         vari = "wolffire1";
      }

      if(x == 5) {
         vari = "wolffire2";
      }

      if(x == 6) {
         vari = "wolffire3";
      }

      if(x == 7) {
         vari = "wolftimber";
      }

      if(x == 8) {
         vari = "wolfwild";
      }

      return this.base;
   }

   protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntityBigWolf)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
      return this.handleRotationFloat((EntityBigWolf)p_77044_1_, p_77044_2_);
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return this.getEntityTexture((EntityBigWolf)p_110775_1_);
   }
}
