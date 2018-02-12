package net.minecraft.client.renderer.entity;

import fr.thisismac.injector.customclass.ModeSteston;
import fr.thisismac.injector.customclass.ModelAdvancedHat;
import fr.thisismac.injector.customclass.ModelAfkHat;
import fr.thisismac.injector.customclass.ModelAfroHat;
import fr.thisismac.injector.customclass.ModelArcherBackPack;
import fr.thisismac.injector.customclass.ModelAssasinPackBack;
import fr.thisismac.injector.customclass.ModelBeard;
import fr.thisismac.injector.customclass.ModelCactus;
import fr.thisismac.injector.customclass.ModelCardboardHat;
import fr.thisismac.injector.customclass.ModelCatEars;
import fr.thisismac.injector.customclass.ModelChestHat;
import fr.thisismac.injector.customclass.ModelChickenHat;
import fr.thisismac.injector.customclass.ModelClamHat;
import fr.thisismac.injector.customclass.ModelCraftmanBackPack;
import fr.thisismac.injector.customclass.ModelCrazyGooglesHat;
import fr.thisismac.injector.customclass.ModelCrazyHat;
import fr.thisismac.injector.customclass.ModelCrownHat;
import fr.thisismac.injector.customclass.ModelCrystal;
import fr.thisismac.injector.customclass.ModelCrystalEnder;
import fr.thisismac.injector.customclass.ModelCrystalIce;
import fr.thisismac.injector.customclass.ModelDemonWings;
import fr.thisismac.injector.customclass.ModelDisco;
import fr.thisismac.injector.customclass.ModelEncouragementHat;
import fr.thisismac.injector.customclass.ModelGravestone;
import fr.thisismac.injector.customclass.ModelHornsHat;
import fr.thisismac.injector.customclass.ModelHumbleHat;
import fr.thisismac.injector.customclass.ModelInitiatedHat;
import fr.thisismac.injector.customclass.ModelLike;
import fr.thisismac.injector.customclass.ModelMinerBackPack;
import fr.thisismac.injector.customclass.ModelNoelHat;
import fr.thisismac.injector.customclass.ModelOctopusHat;
import fr.thisismac.injector.customclass.ModelPandaHat;
import fr.thisismac.injector.customclass.ModelPartyHat;
import fr.thisismac.injector.customclass.ModelPenguinHat;
import fr.thisismac.injector.customclass.ModelPickaxeHat;
import fr.thisismac.injector.customclass.ModelPigHat;
import fr.thisismac.injector.customclass.ModelRedCrystal;
import fr.thisismac.injector.customclass.ModelReindeer;
import fr.thisismac.injector.customclass.ModelSack;
import fr.thisismac.injector.customclass.ModelScubaHat;
import fr.thisismac.injector.customclass.ModelSerpentHat;
import fr.thisismac.injector.customclass.ModelSlimeHat;
import fr.thisismac.injector.customclass.ModelSwordHat;
import fr.thisismac.injector.customclass.ModelWarriorBackPack;
import fr.thisismac.injector.customclass.ModelWesternHat;
import fr.thisismac.injector.customclass.ModelYoutubeHat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public abstract class RendererLivingEntity extends Render {
   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   protected ModelBase mainModel;
   private ModelSwordHat model_sword_hat;
   private ModelNoelHat model_noel_hat;
   private ModelHornsHat model_horns_hat;
   private ModelChickenHat model_chicken_hat;
   private ModelInitiatedHat model_initiated_hat;
   private ModelAdvancedHat model_advanced_hat;
   private ModelChestHat model_chest_hat;
   private ModelClamHat model_clam_hat;
   private ModelOctopusHat model_octopus_hat;
   private ModelSack model_sack;
   private ModelDemonWings model_demon_wings;
   private ModelWesternHat model_western_hat;
   private ModelGravestone model_gravestone_hat;
   private ModelRedCrystal model_redCrystal_hat;
   private ModelSlimeHat model_slime_hat;
   private ModelCrystalIce model_crystal_ice;
   private ModelCrystalEnder model_crystal_ender;
   private ModelHumbleHat model_humble_hat;
   private ModelLike model_like;
   private ModelCrownHat model_crown_hat;
   private ModelAfroHat model_afro_hat;
   private ModelBeard model_beard;
   private ModelCardboardHat model_cardboard_hat;
   private ModelCrazyGooglesHat model_crazyGoogles_hat;
   private ModelYoutubeHat model_youtube_hat;
   private ModelPickaxeHat model_pickaxe_hat;
   private ModelCrazyHat model_crazy_hat;
   private ModelEncouragementHat model_encouragement_hat;
   private ModelPandaHat model_panda_hat;
   private ModelPartyHat model_party_hat;
   private ModelPenguinHat model_penguin_hat;
   private ModelPigHat model_pig_hat;
   private ModelScubaHat model_scuba_hat;
   private ModelAfkHat model_afk_hat;
   public static float crystalRotation;
   private boolean crystalDescending;
   private float crystalYOffset;
   private ModelSerpentHat serpent;
   private ModeSteston OFsteston;
   private ModelCatEars OFcatEars;
   private ModelArcherBackPack archeback;
   private ModelCraftmanBackPack craftback;
   private ModelAssasinPackBack assasinback;
   private ModelMinerBackPack minerback;
   private ModelWarriorBackPack warriorback;
   private ModelCrystal crystal;
   private ModelReindeer reindeer;
   private ModelCactus cactus;
   private ModelDisco disco;
   protected boolean hasSack;
   protected ModelBase renderPassModel;
   private static final String __OBFID = "CL_00001012";
   private static final String __OBFID = "CL_00001012";

   public RendererLivingEntity(ModelBase p_i1261_1_, float p_i1261_2_) {
      this.mainModel = p_i1261_1_;
      this.shadowSize = p_i1261_2_;
      this.model_sword_hat = new ModelSwordHat();
      this.model_noel_hat = new ModelNoelHat();
      this.model_horns_hat = new ModelHornsHat();
      this.model_chicken_hat = new ModelChickenHat();
      this.model_initiated_hat = new ModelInitiatedHat();
      this.model_advanced_hat = new ModelAdvancedHat();
      this.model_chest_hat = new ModelChestHat();
      this.model_clam_hat = new ModelClamHat();
      this.model_octopus_hat = new ModelOctopusHat();
      this.model_sack = new ModelSack();
      this.model_demon_wings = new ModelDemonWings();
      this.model_western_hat = new ModelWesternHat();
      this.model_gravestone_hat = new ModelGravestone();
      this.model_redCrystal_hat = new ModelRedCrystal();
      this.model_slime_hat = new ModelSlimeHat();
      this.model_crystal_ice = new ModelCrystalIce();
      this.model_crystal_ender = new ModelCrystalEnder();
      this.model_humble_hat = new ModelHumbleHat();
      this.model_like = new ModelLike();
      this.model_crown_hat = new ModelCrownHat();
      this.model_afro_hat = new ModelAfroHat();
      this.model_beard = new ModelBeard();
      this.model_cardboard_hat = new ModelCardboardHat();
      this.model_crazyGoogles_hat = new ModelCrazyGooglesHat();
      this.model_youtube_hat = new ModelYoutubeHat();
      this.model_pickaxe_hat = new ModelPickaxeHat();
      this.model_crazy_hat = new ModelCrazyHat();
      this.model_encouragement_hat = new ModelEncouragementHat();
      this.model_panda_hat = new ModelPandaHat();
      this.model_party_hat = new ModelPartyHat();
      this.model_penguin_hat = new ModelPenguinHat();
      this.model_pig_hat = new ModelPigHat();
      this.model_scuba_hat = new ModelScubaHat();
      this.model_afk_hat = new ModelAfkHat();
      this.serpent = new ModelSerpentHat();
      this.OFsteston = new ModeSteston();
      this.OFcatEars = new ModelCatEars();
      this.archeback = new ModelArcherBackPack();
      this.craftback = new ModelCraftmanBackPack();
      this.warriorback = new ModelWarriorBackPack();
      this.minerback = new ModelMinerBackPack();
      this.assasinback = new ModelAssasinPackBack();
      this.reindeer = new ModelReindeer();
      this.cactus = new ModelCactus();
      this.crystal = new ModelCrystal();
      this.disco = new ModelDisco();
      crystalRotation = 0.0F;
      this.crystalDescending = false;
      this.crystalYOffset = 0.0F;
      this.hasSack = false;
   }

   public void setRenderPassModel(ModelBase p_77042_1_) {
      this.renderPassModel = p_77042_1_;
   }

   private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
      float var4;
      for(var4 = p_77034_2_ - p_77034_1_; var4 < -180.0F; var4 += 360.0F) {
         ;
      }

      while(var4 >= 180.0F) {
         var4 -= 360.0F;
      }

      return p_77034_1_ + p_77034_3_ * var4;
   }

   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GL11.glPushMatrix();
      GL11.glDisable(GL11.GL_CULL_FACE);
      this.mainModel.onGround = this.renderSwingProgress(p_76986_1_, p_76986_9_);
      if(this.renderPassModel != null) {
         this.renderPassModel.onGround = this.mainModel.onGround;
      }

      this.mainModel.isRiding = p_76986_1_.isRiding();
      if(this.renderPassModel != null) {
         this.renderPassModel.isRiding = this.mainModel.isRiding;
      }

      this.mainModel.isChild = p_76986_1_.isChild();
      if(this.renderPassModel != null) {
         this.renderPassModel.isChild = this.mainModel.isChild;
      }

      try {
         float var251 = this.interpolateRotation(p_76986_1_.prevRenderYawOffset, p_76986_1_.renderYawOffset, p_76986_9_);
         float var11 = this.interpolateRotation(p_76986_1_.prevRotationYawHead, p_76986_1_.rotationYawHead, p_76986_9_);
         float var13;
         if(p_76986_1_.isRiding() && p_76986_1_.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase var261 = (EntityLivingBase)p_76986_1_.ridingEntity;
            var251 = this.interpolateRotation(var261.prevRenderYawOffset, var261.renderYawOffset, p_76986_9_);
            var13 = MathHelper.wrapAngleTo180_float(var11 - var251);
            if(var13 < -85.0F) {
               var13 = -85.0F;
            }

            if(var13 >= 85.0F) {
               var13 = 85.0F;
            }

            var251 = var11 - var13;
            if(var13 * var13 > 2500.0F) {
               var251 += var13 * 0.2F;
            }
         }

         float var26 = p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_;
         this.renderLivingAt(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
         var13 = this.handleRotationFloat(p_76986_1_, p_76986_9_);
         this.rotateCorpse(p_76986_1_, var13, var251, p_76986_9_);
         float var14 = 0.0625F;
         GL11.glEnable(GL12.GL_RESCALE_NORMAL);
         GL11.glScalef(-1.0F, -1.0F, 1.0F);
         this.preRenderCallback(p_76986_1_, p_76986_9_);
         GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
         float var15 = p_76986_1_.prevLimbSwingAmount + (p_76986_1_.limbSwingAmount - p_76986_1_.prevLimbSwingAmount) * p_76986_9_;
         float var16 = p_76986_1_.limbSwing - p_76986_1_.limbSwingAmount * (1.0F - p_76986_9_);
         if(p_76986_1_.isChild()) {
            var16 *= 3.0F;
         }

         if(var15 > 1.0F) {
            var15 = 1.0F;
         }

         GL11.glEnable(GL11.GL_ALPHA_TEST);
         this.mainModel.setLivingAnimations(p_76986_1_, var16, var15, p_76986_9_);
         this.renderModel(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);

         int var18;
         float var19;
         float var20;
         float var22;
         int var29;
         float var28;
         for(int var271 = 0; var271 < 4; ++var271) {
            var18 = this.shouldRenderPass(p_76986_1_, var271, p_76986_9_);
            if(var18 > 0) {
               this.renderPassModel.setLivingAnimations(p_76986_1_, var16, var15, p_76986_9_);
               this.renderPassModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);
               if((var18 & 240) == 16) {
                  GL11.glPushMatrix();
                  this.func_82408_c(p_76986_1_, var271, p_76986_9_);
                  this.renderPassModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);
                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  GL11.glPopMatrix();
               }

               if((var18 & 15) == 15) {
                  var19 = (float)p_76986_1_.ticksExisted + p_76986_9_;
                  this.bindTexture(RES_ITEM_GLINT);
                  GL11.glEnable(GL11.GL_BLEND);
                  var20 = 0.5F;
                  GL11.glColor4f(var20, var20, var20, 1.0F);
                  GL11.glDepthFunc(GL11.GL_EQUAL);
                  GL11.glDepthMask(false);

                  for(var29 = 0; var29 < 2; ++var29) {
                     GL11.glDisable(GL11.GL_LIGHTING);
                     var22 = 0.76F;
                     GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
                     GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                     GL11.glMatrixMode(GL11.GL_TEXTURE);
                     GL11.glLoadIdentity();
                     var28 = var19 * (0.001F + (float)var29 * 0.003F) * 20.0F;
                     float var291 = 0.33333334F;
                     GL11.glScalef(var291, var291, var291);
                     GL11.glRotatef(30.0F - (float)var29 * 60.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(0.0F, var28, 0.0F);
                     GL11.glMatrixMode(GL11.GL_MODELVIEW);
                     this.renderPassModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);
                  }

                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  GL11.glMatrixMode(GL11.GL_TEXTURE);
                  GL11.glDepthMask(true);
                  GL11.glLoadIdentity();
                  GL11.glMatrixMode(GL11.GL_MODELVIEW);
                  GL11.glEnable(GL11.GL_LIGHTING);
                  GL11.glDisable(GL11.GL_BLEND);
                  GL11.glDepthFunc(GL11.GL_LEQUAL);
               }

               GL11.glDisable(GL11.GL_BLEND);
               GL11.glEnable(GL11.GL_ALPHA_TEST);
            }
         }

         GL11.glDepthMask(true);
         this.renderEquippedItems(p_76986_1_, p_76986_9_);
         float var27 = p_76986_1_.getBrightness(p_76986_9_);
         var18 = this.getColorMultiplier(p_76986_1_, var27, p_76986_9_);
         OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glDisable(GL11.GL_TEXTURE_2D);
         OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
         if((var18 >> 24 & 255) > 0 || p_76986_1_.hurtTime > 0 || p_76986_1_.deathTime > 0) {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDepthFunc(GL11.GL_EQUAL);
            if(p_76986_1_.hurtTime > 0 || p_76986_1_.deathTime > 0) {
               GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
               this.mainModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);

               for(var29 = 0; var29 < 4; ++var29) {
                  if(this.inheritRenderPass(p_76986_1_, var29, p_76986_9_) >= 0) {
                     GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                     this.renderPassModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);
                  }
               }
            }

            if((var18 >> 24 & 255) > 0) {
               var19 = (float)(var18 >> 16 & 255) / 255.0F;
               var20 = (float)(var18 >> 8 & 255) / 255.0F;
               var28 = (float)(var18 & 255) / 255.0F;
               var22 = (float)(var18 >> 24 & 255) / 255.0F;
               GL11.glColor4f(var19, var20, var28, var22);
               this.mainModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);

               for(int var281 = 0; var281 < 4; ++var281) {
                  if(this.inheritRenderPass(p_76986_1_, var281, p_76986_9_) >= 0) {
                     GL11.glColor4f(var19, var20, var28, var22);
                     this.renderPassModel.render(p_76986_1_, var16, var15, var13, var11 - var251, var26, var14);
                  }
               }
            }

            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
         }

         GL11.glDisable(GL12.GL_RESCALE_NORMAL);
      } catch (Exception var25) {
         logger.error("Couldn\'t render entity", var25);
      }

      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GL11.glEnable(GL11.GL_CULL_FACE);
      GL11.glPopMatrix();
      this.passSpecialRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
   }

   protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
      this.bindEntityTexture(p_77036_1_);
      if(!p_77036_1_.isInvisible()) {
         this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
      } else if(!p_77036_1_.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
         GL11.glDepthMask(false);
         GL11.glEnable(GL11.GL_BLEND);
         GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
         this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
         GL11.glDisable(GL11.GL_BLEND);
         GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
         GL11.glPopMatrix();
         GL11.glDepthMask(true);
      } else {
         this.mainModel.setRotationAngles(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, p_77036_1_);
      }
   }

   protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
      GL11.glTranslatef((float)p_77039_2_, (float)p_77039_4_, (float)p_77039_6_);
   }

   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      GL11.glRotatef(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
      if(p_77043_1_.deathTime > 0) {
         float var61 = ((float)p_77043_1_.deathTime + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
         var61 = MathHelper.sqrt_float(var61);
         if(var61 > 1.0F) {
            var61 = 1.0F;
         }

         GL11.glRotatef(var61 * this.getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
      } else {
         String var611 = EnumChatFormatting.getTextWithoutFormattingCodes(p_77043_1_.getCommandSenderName());
         if((var611.equals("Dinnerbone") || var611.equals("Grumm")) && (!(p_77043_1_ instanceof EntityPlayer) || !((EntityPlayer)p_77043_1_).getHideCape())) {
            GL11.glTranslatef(0.0F, p_77043_1_.height + 0.1F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }

   protected float renderSwingProgress(EntityLivingBase p_77040_1_, float p_77040_2_) {
      return p_77040_1_.getSwingProgress(p_77040_2_);
   }

   protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
      return (float)p_77044_1_.ticksExisted + p_77044_2_;
   }

   protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) {
      if(p_77029_1_ instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)p_77029_1_;
         StringBuilder var101 = new StringBuilder();
         int var110 = p_77029_1_.getActivePotionEffects().size();
         int distance = 1;

         for(Iterator var11 = p_77029_1_.getActivePotionEffects().iterator(); var11.hasNext(); ++distance) {
            Object var12 = var11.next();
            PotionEffect var13 = (PotionEffect)var12;
            if(distance == var110) {
               var101.append(Integer.toString(var13.getPotionID()));
            } else {
               var101.append(Integer.toString(var13.getPotionID()) + ";");
            }
         }

         ArrayList list = new ArrayList(Arrays.asList(var101.toString().split(";")));
         float var111;
         float var121;
         float var131;
         if(list.contains(Integer.toString(26))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/sword_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(0.0F, var131, 0.0F);
            this.model_sword_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(27))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/noel_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -0.69F;
            GL11.glTranslatef(-0.25F, var131, -0.25F);
            this.model_noel_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(31))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/advanced_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.55F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            GL11.glScalef(1.1F, 1.1F, 1.1F);
            this.model_initiated_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(32))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/advanced_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.55F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            GL11.glScalef(1.1F, 1.1F, 1.1F);
            this.model_advanced_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(34))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/chest_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.25F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_chest_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(35))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/clam_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -2.0F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_clam_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(33))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/octopus_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.8F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_octopus_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(28))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/chicken_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.25F;
            GL11.glTranslatef(0.015F, var131, 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_chicken_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(29))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/horns_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.55F;
            GL11.glTranslatef(0.0F, var131, 0.0F);
            this.model_horns_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(38))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/western_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_western_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(40))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/redCrystal_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.65F;
            GL11.glTranslatef(0.06F, var131, -0.0F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.model_redCrystal_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(39))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/slime_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.4F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            GL11.glScalef(0.6F, 0.6F, 0.6F);
            this.model_slime_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(45))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/afro_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_afro_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(47))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/crown_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = -1.52F;
            var121 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var131 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var121, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var131, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.model_crown_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(48))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/cardboard_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_cardboard_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(46))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/beard.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_beard.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(41))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/gravestone_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -2.25F;
            GL11.glTranslatef(-0.0F, var131, -0.1F);
            this.model_gravestone_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(49))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/like.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.65F;
            GL11.glTranslatef(-0.0F, var131, -0.1F);
            this.model_like.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(42))) {
            crystalRotation += 2.0F;
            if(crystalRotation > 360.0F) {
               crystalRotation = 0.0F;
            }

            this.bindTexture(new ResourceLocation("textures/models/customs/iceCrystal_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(crystalRotation, 0.0F, 1.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.1F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_crystal_ice.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(43))) {
            crystalRotation += 0.2F;
            if(crystalRotation > 360.0F) {
               crystalRotation = 0.0F;
            }

            this.bindTexture(new ResourceLocation("textures/models/customs/netherCrystal_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(crystalRotation, 0.0F, 1.0F, 0.0F);
            var131 = -1.45F;
            GL11.glTranslatef(-0.0F, var131, -0.1F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_crystal_ender.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(44))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/humble_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.63F;
            GL11.glTranslatef(-1.03F, var131, -0.1F);
            GL11.glScalef(1.2F, 1.2F, 1.2F);
            this.model_humble_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(Potion.reindeer.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFreindeer.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -2.0F;
            GL11.glTranslatef(0.0F, var131, -0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            this.reindeer.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(Potion.crystal.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFcrystal.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -2.0F;
            GL11.glTranslatef(0.0F, var131, -0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            this.crystal.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(Potion.catears.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFcatears.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.4F;
            GL11.glTranslatef(0.0F, var131, -0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            this.OFcatEars.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(Potion.steston.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFstestonHat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.6F;
            GL11.glTranslatef(0.0F, var131, -0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            this.OFsteston.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(30))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/sack.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.0F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.model_sack.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         } else {
            this.hasSack = false;
         }

         if(list.contains(Integer.toString(Potion.warriorback.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFwarriorback.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.0F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.warriorback.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         }

         if(list.contains(Integer.toString(Potion.minerBack.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFminerback.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.0F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.minerback.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         }

         if(list.contains(Integer.toString(Potion.craftmanBack.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFcraftmanback.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.0F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.craftback.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         }

         if(list.contains(Integer.toString(Potion.assassinBack.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFassasinback.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.0F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.assasinback.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         }

         if(list.contains(Integer.toString(Potion.feetRedAura.id))) {
            this.renderEquippedItems(p_77029_2_);
         }

         if(list.contains(Integer.toString(Potion.archerBack.id))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/OFarcherback.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.0F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.0F);
            this.archeback.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         }

         if(list.contains(Integer.toString(36))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/demon_wings.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.8F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glTranslatef(0.0F, var111, 0.08F);
            this.model_demon_wings.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(37))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/angel_wings.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = 0.4F;
            if(p_77029_1_.isSneaking()) {
               GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            }

            if(p_77029_1_.getEquipmentInSlot(3) != null) {
               GL11.glTranslatef(0.0F, var111, 0.16F);
            } else {
               GL11.glTranslatef(0.0F, var111, 0.08F);
            }

            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_demon_wings.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            this.hasSack = true;
         }

         if(list.contains(Integer.toString(50))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/crazyGoogles_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.1F;
            GL11.glTranslatef(0.0F, var131, 0.0F);
            GL11.glScalef(0.9F, 0.9F, 1.25F);
            this.model_crazyGoogles_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(51))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/youtube_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.45F;
            GL11.glTranslatef(0.0F, var131, 0.0F);
            this.model_youtube_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(52))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/pickaxe_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.55F;
            GL11.glTranslatef(0.05F, var131, 0.0F);
            this.model_pickaxe_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(53))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/crazy_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_crazy_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(54))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/encouragement_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            if(p_77029_1_.getEquipmentInSlot(4) != null) {
               var131 -= 0.05F;
            }

            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_encouragement_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(55))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/panda_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_panda_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(56))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/party_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.5F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            this.model_party_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(57))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/penguin_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.22F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_penguin_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(58))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/pig_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.0F;
            GL11.glTranslatef(-0.0F, var131, -0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.model_pig_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(59))) {
            this.bindTexture(new ResourceLocation("textures/models/customs/scuba_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            var131 = -1.45F;
            GL11.glTranslatef(-0.0F, var131, -0.1F);
            this.model_scuba_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }

         if(list.contains(Integer.toString(60))) {
            crystalRotation += 0.05F;
            if(crystalRotation > 360.0F) {
               crystalRotation = 0.0F;
            }

            if(!this.crystalDescending) {
               this.crystalYOffset += 3.0E-4F;
               if(this.crystalYOffset >= 0.4F) {
                  this.crystalDescending = true;
               }
            } else {
               this.crystalYOffset -= 6.0E-4F;
               if(this.crystalYOffset <= 0.0F) {
                  this.crystalDescending = false;
               }
            }

            this.bindTexture(new ResourceLocation("textures/models/customs/afk_hat.png"));
            GL11.glPushMatrix();
            if(p_77029_1_.isSneaking()) {
               GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
            }

            var111 = p_77029_1_.prevRotationYawHead + (p_77029_1_.rotationYawHead - p_77029_1_.prevRotationYawHead) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
            var121 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
            GL11.glRotatef(var111, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var121, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(crystalRotation, 0.0F, 1.0F, 0.0F);
            var131 = -1.65F;
            GL11.glTranslatef(-0.0F, var131 - this.crystalYOffset, -0.1F);
            this.model_afk_hat.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
         }
      }
   }

   protected void renderArrowsStuckInEntity(EntityLivingBase p_85093_1_, float p_85093_2_) {
      int var3 = p_85093_1_.getArrowCountInEntity();
      if(var3 > 0) {
         EntityArrow var4 = new EntityArrow(p_85093_1_.worldObj, p_85093_1_.posX, p_85093_1_.posY, p_85093_1_.posZ);
         Random var5 = new Random((long)p_85093_1_.getEntityId());
         RenderHelper.disableStandardItemLighting();

         for(int var6 = 0; var6 < var3; ++var6) {
            GL11.glPushMatrix();
            ModelRenderer var7 = this.mainModel.getRandomModelBox(var5);
            ModelBox var8 = (ModelBox)var7.cubeList.get(var5.nextInt(var7.cubeList.size()));
            var7.postRender(0.0625F);
            float var9 = var5.nextFloat();
            float var10 = var5.nextFloat();
            float var11 = var5.nextFloat();
            float var12 = (var8.posX1 + (var8.posX2 - var8.posX1) * var9) / 16.0F;
            float var13 = (var8.posY1 + (var8.posY2 - var8.posY1) * var10) / 16.0F;
            float var14 = (var8.posZ1 + (var8.posZ2 - var8.posZ1) * var11) / 16.0F;
            GL11.glTranslatef(var12, var13, var14);
            var9 = var9 * 2.0F - 1.0F;
            var10 = var10 * 2.0F - 1.0F;
            var11 = var11 * 2.0F - 1.0F;
            var9 *= -1.0F;
            var10 *= -1.0F;
            var11 *= -1.0F;
            float var15 = MathHelper.sqrt_float(var9 * var9 + var11 * var11);
            var4.prevRotationYaw = var4.rotationYaw = (float)(Math.atan2((double)var9, (double)var11) * 180.0D / Math.PI);
            var4.prevRotationPitch = var4.rotationPitch = (float)(Math.atan2((double)var10, (double)var15) * 180.0D / Math.PI);
            double var16 = 0.0D;
            double var18 = 0.0D;
            double var20 = 0.0D;
            float var22 = 0.0F;
            this.renderManager.func_147940_a(var4, var16, var18, var20, var22, p_85093_2_);
            GL11.glPopMatrix();
         }

         RenderHelper.enableStandardItemLighting();
      }
   }

   protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_) {
      return this.shouldRenderPass(p_77035_1_, p_77035_2_, p_77035_3_);
   }

   protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return -1;
   }

   protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_) {}

   protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
      return 90.0F;
   }

   protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
      return 0;
   }

   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {}

   protected void passSpecialRender(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_, double p_77033_6_) {
      GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
      if(this.func_110813_b(p_77033_1_)) {
         float var8 = 1.6F;
         float var9 = 0.016666668F * var8;
         double var10 = p_77033_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);
         float var12 = p_77033_1_.isSneaking()?32.0F:64.0F;
         if(var10 < (double)(var12 * var12)) {
            String var13 = p_77033_1_.func_145748_c_().getFormattedText();
            if(p_77033_1_.isSneaking()) {
               FontRenderer var14 = this.getFontRendererFromRenderManager();
               GL11.glPushMatrix();
               GL11.glTranslatef((float)p_77033_2_ + 0.0F, (float)p_77033_4_ + p_77033_1_.height + 0.5F, (float)p_77033_6_);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glScalef(-var9, -var9, var9);
               GL11.glDisable(GL11.GL_LIGHTING);
               GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
               GL11.glDepthMask(false);
               GL11.glEnable(GL11.GL_BLEND);
               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
               Tessellator var15 = Tessellator.instance;
               GL11.glDisable(GL11.GL_TEXTURE_2D);
               var15.startDrawingQuads();
               int var16 = var14.getStringWidth(var13) / 2;
               var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
               var15.addVertex((double)(-var16 - 1), -1.0D, 0.0D);
               var15.addVertex((double)(-var16 - 1), 8.0D, 0.0D);
               var15.addVertex((double)(var16 + 1), 8.0D, 0.0D);
               var15.addVertex((double)(var16 + 1), -1.0D, 0.0D);
               var15.draw();
               GL11.glEnable(GL11.GL_TEXTURE_2D);
               GL11.glDepthMask(true);
               var14.drawString(var13, -var14.getStringWidth(var13) / 2, 0, 553648127);
               GL11.glEnable(GL11.GL_LIGHTING);
               GL11.glDisable(GL11.GL_BLEND);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glPopMatrix();
            } else {
               this.func_96449_a(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_, var13, var9, var10);
            }
         }
      }
   }

   protected boolean func_110813_b(EntityLivingBase p_110813_1_) {
      return Minecraft.isGuiEnabled() && p_110813_1_ != this.renderManager.livingPlayer && !p_110813_1_.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) && p_110813_1_.riddenByEntity == null;
   }

   protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
      if(p_96449_1_.isPlayerSleeping()) {
         this.func_147906_a(p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_ - 1.5D, p_96449_6_, 64);
      } else {
         this.func_147906_a(p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_, p_96449_6_, 64);
      }
   }

   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected void renderEquippedItems(float p_77029_2_) {
      Tessellator var3 = Tessellator.instance;
      RenderHelper.disableStandardItemLighting();
      float var4 = (115.0F + p_77029_2_) / 200.0F;
      float var5 = 0.0F;
      if(var4 > 0.8F) {
         var5 = (var4 - 0.8F) / 0.2F;
      }

      Random var6 = new Random(432L);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      GL11.glShadeModel(GL11.GL_SMOOTH);
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
      GL11.glDisable(GL11.GL_ALPHA_TEST);
      GL11.glEnable(GL11.GL_CULL_FACE);
      GL11.glDepthMask(false);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -1.0F, -2.0F);

      for(int var7 = 0; (float)var7 < (var4 + var4 * var4) / 2.0F * 60.0F; ++var7) {
         GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(var6.nextFloat() * 360.0F + var4 * 90.0F, 0.0F, 0.0F, 1.0F);
         var3.startDrawing(6);
         float var8 = var6.nextFloat() * 5.0F + 5.0F + var5 * 10.0F;
         float var9 = var6.nextFloat() * 2.0F + 1.0F + var5 * 2.0F;
         String yolo = null;
         Random r = new Random();
         int a = showRandomInteger(1, 250, r);
         int b = showRandomInteger(250, 500, r);
         int c = showRandomInteger(500, 750, r);
         yolo = a + "" + b + "" + c;
         var3.setColorRGBA_I(Integer.parseInt(yolo), (int)(255.0F * (1.0F - var5)));
         var3.addVertex(0.0D, 0.0D, 0.0D);
         var3.setColorRGBA_I(Integer.parseInt(yolo), 0);
         var3.addVertex(-0.866D * (double)var9, (double)var8, (double)(-0.5F * var9));
         var3.addVertex(0.866D * (double)var9, (double)var8, (double)(-0.5F * var9));
         var3.addVertex(0.0D, (double)var8, (double)(1.0F * var9));
         var3.addVertex(-0.866D * (double)var9, (double)var8, (double)(-0.5F * var9));
         var3.draw();
      }

      GL11.glPopMatrix();
      GL11.glDepthMask(true);
      GL11.glDisable(GL11.GL_CULL_FACE);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glShadeModel(GL11.GL_FLAT);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      GL11.glEnable(GL11.GL_ALPHA_TEST);
      GL11.glTranslated(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posY);
      RenderHelper.enableStandardItemLighting();
   }

   private static int showRandomInteger(int aStart, int aEnd, Random aRandom) {
      if(aStart > aEnd) {
         throw new IllegalArgumentException("Start cannot exceed End.");
      } else {
         long range = (long)aEnd - (long)aStart + 1L;
         long fraction = (long)((double)range * aRandom.nextDouble());
         int randomNumber = (int)(fraction + (long)aStart);
         return randomNumber;
      }
   }
}
