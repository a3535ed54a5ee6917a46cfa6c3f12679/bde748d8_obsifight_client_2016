package fr.thisismac.injector.customclass;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class RenderKingZombie extends RenderBiped {
   private static final ResourceLocation zombiePigmanTextures = new ResourceLocation("textures/entity/zombie/king_zombie.png");
   private ModelBiped field_82434_o;
   private ModelZombieVillager zombieVillagerModel;
   protected ModelBiped field_82437_k;
   protected ModelBiped field_82435_l;
   protected ModelBiped field_82436_m;
   protected ModelBiped field_82433_n;
   private int field_82431_q = 1;

   public RenderKingZombie() {
      super(new ModelZombie(), 0.5F, 1.0F);
      this.field_82434_o = this.modelBipedMain;
      this.zombieVillagerModel = new ModelZombieVillager();
   }

   protected void func_82421_b() {
      this.field_82423_g = new ModelZombie(1.5F, true);
      this.field_82425_h = new ModelZombie(0.5F, true);
      this.field_82437_k = this.field_82423_g;
      this.field_82435_l = this.field_82425_h;
      this.field_82436_m = new ModelZombieVillager(0.5F, 1.0F, true);
      this.field_82433_n = new ModelZombieVillager(0.5F, 1.0F, true);
   }

   protected int shouldRenderPass(EntityKingZombie p_77032_1_, int p_77032_2_, float p_77032_3_) {
      this.func_82427_a(p_77032_1_);
      return super.shouldRenderPass((EntityLiving)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   public void doRender(EntityKingZombie p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      BossStatus.setBossStatus(p_76986_1_, true);
      this.func_82427_a(p_76986_1_);
      super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation getEntityTexture(EntityKingZombie p_110775_1_) {
      return zombiePigmanTextures;
   }

   protected void renderEquippedItems(EntityKingZombie p_77029_1_, float p_77029_2_) {
      this.func_82427_a(p_77029_1_);
      super.renderEquippedItems((EntityLiving)p_77029_1_, p_77029_2_);
   }

   private void func_82427_a(EntityKingZombie p_82427_1_) {
      this.mainModel = this.field_82434_o;
      this.field_82423_g = this.field_82437_k;
      this.field_82425_h = this.field_82435_l;
      this.modelBipedMain = (ModelBiped)this.mainModel;
   }

   protected void rotateCorpse(EntityKingZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
   }

   protected void renderEquippedItems(EntityLiving p_77029_1_, float p_77029_2_) {
      this.renderEquippedItems((EntityKingZombie)p_77029_1_, p_77029_2_);
   }

   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
      return this.getEntityTexture((EntityKingZombie)p_110775_1_);
   }

   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityKingZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected int shouldRenderPass(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntityKingZombie)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntityKingZombie)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) {
      this.renderEquippedItems((EntityKingZombie)p_77029_1_, p_77029_2_);
   }

   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      this.rotateCorpse((EntityKingZombie)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
   }

   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityKingZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return this.getEntityTexture((EntityKingZombie)p_110775_1_);
   }

   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityKingZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }
}
