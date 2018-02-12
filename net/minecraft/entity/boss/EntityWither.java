package net.minecraft.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWither extends EntityMob implements IBossDisplayData, IRangedAttackMob {
   private float[] field_82220_d = new float[2];
   private float[] field_82221_e = new float[2];
   private float[] field_82217_f = new float[2];
   private float[] field_82218_g = new float[2];
   private int[] field_82223_h = new int[2];
   private int[] field_82224_i = new int[2];
   private int field_82222_j;
   private static final IEntitySelector attackEntitySelector = new IEntitySelector() {
      private static final String __OBFID = "CL_00001662";
      private static final String __OBFID = "CL_00001662";

      public boolean isEntityApplicable(Entity p_82704_1_) {
         return p_82704_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_82704_1_).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
      }
   };
   private static final String __OBFID = "CL_00001661";
   private static final String __OBFID = "CL_00001661";

   public EntityWither(World p_i1701_1_) {
      super(p_i1701_1_);
      this.setHealth(this.getMaxHealth());
      this.setSize(0.9F, 4.0F);
      this.isImmuneToFire = true;
      this.getNavigator().setCanSwim(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, attackEntitySelector));
      this.experienceValue = 50;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(17, new Integer(0));
      this.dataWatcher.addObject(18, new Integer(0));
      this.dataWatcher.addObject(19, new Integer(0));
      this.dataWatcher.addObject(20, new Integer(0));
   }

   public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
      super.writeEntityToNBT(p_70014_1_);
      p_70014_1_.setInteger("Invul", this.func_82212_n());
   }

   public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
      super.readEntityFromNBT(p_70037_1_);
      this.func_82215_s(p_70037_1_.getInteger("Invul"));
   }

   public float getShadowSize() {
      return this.height / 8.0F;
   }

   protected String getLivingSound() {
      return "mob.wither.idle";
   }

   protected String getHurtSound() {
      return "mob.wither.hurt";
   }

   protected String getDeathSound() {
      return "mob.wither.death";
   }

   public void onLivingUpdate() {
      this.motionY *= 0.6000000238418579D;
      double var4;
      double var6;
      double var8;
      if(!this.worldObj.isClient && this.getWatchedTargetId(0) > 0) {
         Entity var20 = this.worldObj.getEntityByID(this.getWatchedTargetId(0));
         if(var20 != null) {
            if(this.posY < var20.posY || !this.isArmored() && this.posY < var20.posY + 5.0D) {
               if(this.motionY < 0.0D) {
                  this.motionY = 0.0D;
               }

               this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
            }

            double var22 = var20.posX - this.posX;
            var4 = var20.posZ - this.posZ;
            var6 = var22 * var22 + var4 * var4;
            if(var6 > 9.0D) {
               var8 = (double)MathHelper.sqrt_double(var6);
               this.motionX += (var22 / var8 * 0.5D - this.motionX) * 0.6000000238418579D;
               this.motionZ += (var4 / var8 * 0.5D - this.motionZ) * 0.6000000238418579D;
            }
         }
      }

      if(this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D) {
         this.rotationYaw = (float)Math.atan2(this.motionZ, this.motionX) * (180F / (float)Math.PI) - 90.0F;
      }

      super.onLivingUpdate();

      int var201;
      for(var201 = 0; var201 < 2; ++var201) {
         this.field_82218_g[var201] = this.field_82221_e[var201];
         this.field_82217_f[var201] = this.field_82220_d[var201];
      }

      double var23;
      double var5;
      double var7;
      int var211;
      for(var201 = 0; var201 < 2; ++var201) {
         var211 = this.getWatchedTargetId(var201 + 1);
         Entity var21 = null;
         if(var211 > 0) {
            var21 = this.worldObj.getEntityByID(var211);
         }

         if(var21 != null) {
            var4 = this.func_82214_u(var201 + 1);
            var6 = this.func_82208_v(var201 + 1);
            var8 = this.func_82213_w(var201 + 1);
            var23 = var21.posX - var4;
            var5 = var21.posY + (double)var21.getEyeHeight() - var6;
            var7 = var21.posZ - var8;
            double var16 = (double)MathHelper.sqrt_double(var23 * var23 + var7 * var7);
            float var18 = (float)(Math.atan2(var7, var23) * 180.0D / Math.PI) - 90.0F;
            float var19 = (float)(-(Math.atan2(var5, var16) * 180.0D / Math.PI));
            this.field_82220_d[var201] = this.func_82204_b(this.field_82220_d[var201], var19, 40.0F);
            this.field_82221_e[var201] = this.func_82204_b(this.field_82221_e[var201], var18, 10.0F);
         } else {
            this.field_82221_e[var201] = this.func_82204_b(this.field_82221_e[var201], this.renderYawOffset, 10.0F);
         }
      }

      boolean var221 = this.isArmored();

      for(var211 = 0; var211 < 3; ++var211) {
         var23 = this.func_82214_u(var211);
         var5 = this.func_82208_v(var211);
         var7 = this.func_82213_w(var211);
         this.worldObj.spawnParticle("smoke", var23 + this.rand.nextGaussian() * 0.30000001192092896D, var5 + this.rand.nextGaussian() * 0.30000001192092896D, var7 + this.rand.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
         if(var221 && this.worldObj.rand.nextInt(4) == 0) {
            this.worldObj.spawnParticle("mobSpell", var23 + this.rand.nextGaussian() * 0.30000001192092896D, var5 + this.rand.nextGaussian() * 0.30000001192092896D, var7 + this.rand.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
         }
      }

      if(this.func_82212_n() > 0) {
         for(var211 = 0; var211 < 3; ++var211) {
            this.worldObj.spawnParticle("mobSpell", this.posX + this.rand.nextGaussian() * 1.0D, this.posY + (double)(this.rand.nextFloat() * 3.3F), this.posZ + this.rand.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
         }
      }
   }

   protected void updateAITasks() {
      int var1;
      if(this.func_82212_n() > 0) {
         var1 = this.func_82212_n() - 1;
         if(var1 <= 0) {
            this.worldObj.newExplosion(this, this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, 7.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            this.worldObj.playBroadcastSound(1013, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
         }

         this.func_82215_s(var1);
         if(this.ticksExisted % 10 == 0) {
            this.heal(10.0F);
         }
      } else {
         super.updateAITasks();

         int var12;
         int var15;
         for(var1 = 1; var1 < 3; ++var1) {
            if(this.ticksExisted >= this.field_82223_h[var1 - 1]) {
               this.field_82223_h[var1 - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);
               int var17;
               if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL || this.worldObj.difficultySetting == EnumDifficulty.HARD) {
                  var15 = var1 - 1;
                  var17 = this.field_82224_i[var1 - 1];
                  this.field_82224_i[var15] = this.field_82224_i[var1 - 1] + 1;
                  if(var17 > 15) {
                     float var18 = 10.0F;
                     float var19 = 5.0F;
                     double var7 = MathHelper.getRandomDoubleInRange(this.rand, this.posX - (double)var18, this.posX + (double)var18);
                     double var9 = MathHelper.getRandomDoubleInRange(this.rand, this.posY - (double)var19, this.posY + (double)var19);
                     double var11 = MathHelper.getRandomDoubleInRange(this.rand, this.posZ - (double)var18, this.posZ + (double)var18);
                     this.func_82209_a(var1 + 1, var7, var9, var11, true);
                     this.field_82224_i[var1 - 1] = 0;
                  }
               }

               var12 = this.getWatchedTargetId(var1);
               if(var12 > 0) {
                  Entity var13 = this.worldObj.getEntityByID(var12);
                  if(var13 != null && var13.isEntityAlive() && this.getDistanceSqToEntity(var13) <= 900.0D && this.canEntityBeSeen(var13)) {
                     this.func_82216_a(var1 + 1, (EntityLivingBase)var13);
                     this.field_82223_h[var1 - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
                     this.field_82224_i[var1 - 1] = 0;
                  } else {
                     this.func_82211_c(var1, 0);
                  }
               } else {
                  List var14 = this.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, 8.0D, 20.0D), attackEntitySelector);

                  for(var17 = 0; var17 < 10 && !var14.isEmpty(); ++var17) {
                     EntityLivingBase var16 = (EntityLivingBase)var14.get(this.rand.nextInt(var14.size()));
                     if(var16 != this && var16.isEntityAlive() && this.canEntityBeSeen(var16)) {
                        if(var16 instanceof EntityPlayer) {
                           if(!((EntityPlayer)var16).capabilities.disableDamage) {
                              this.func_82211_c(var1, var16.getEntityId());
                           }
                        } else {
                           this.func_82211_c(var1, var16.getEntityId());
                        }
                        break;
                     }

                     var14.remove(var16);
                  }
               }
            }
         }

         if(this.getAttackTarget() != null) {
            this.func_82211_c(0, this.getAttackTarget().getEntityId());
         } else {
            this.func_82211_c(0, 0);
         }

         if(this.field_82222_j > 0) {
            --this.field_82222_j;
            if(this.field_82222_j == 0 && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
               var1 = MathHelper.floor_double(this.posY);
               var12 = MathHelper.floor_double(this.posX);
               var15 = MathHelper.floor_double(this.posZ);
               boolean var151 = false;

               for(int var171 = -1; var171 <= 1; ++var171) {
                  for(int var181 = -1; var181 <= 1; ++var181) {
                     for(int var191 = 0; var191 <= 3; ++var191) {
                        int var20 = var12 + var171;
                        int var201 = var1 + var191;
                        int var10 = var15 + var181;
                        Block var21 = this.worldObj.getBlock(var20, var201, var10);
                        if(var21.getMaterial() != Material.air && var21 != Blocks.bedrock && var21 != Blocks.end_portal && var21 != Blocks.end_portal_frame && var21 != Blocks.command_block) {
                           var151 = this.worldObj.func_147480_a(var20, var201, var10, true) || var151;
                        }
                     }
                  }
               }

               if(var151) {
                  this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1012, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
               }
            }
         }

         if(this.ticksExisted % 20 == 0) {
            this.heal(1.0F);
         }
      }
   }

   public void func_82206_m() {
      this.func_82215_s(220);
      this.setHealth(this.getMaxHealth() / 3.0F);
   }

   public void setInWeb() {}

   public int getTotalArmorValue() {
      return 4;
   }

   private double func_82214_u(int p_82214_1_) {
      if(p_82214_1_ <= 0) {
         return this.posX;
      } else {
         float var2 = (this.renderYawOffset + (float)(180 * (p_82214_1_ - 1))) / 180.0F * (float)Math.PI;
         float var3 = MathHelper.cos(var2);
         return this.posX + (double)var3 * 1.3D;
      }
   }

   private double func_82208_v(int p_82208_1_) {
      return p_82208_1_ <= 0?this.posY + 3.0D:this.posY + 2.2D;
   }

   private double func_82213_w(int p_82213_1_) {
      if(p_82213_1_ <= 0) {
         return this.posZ;
      } else {
         float var2 = (this.renderYawOffset + (float)(180 * (p_82213_1_ - 1))) / 180.0F * (float)Math.PI;
         float var3 = MathHelper.sin(var2);
         return this.posZ + (double)var3 * 1.3D;
      }
   }

   private float func_82204_b(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
      float var4 = MathHelper.wrapAngleTo180_float(p_82204_2_ - p_82204_1_);
      if(var4 > p_82204_3_) {
         var4 = p_82204_3_;
      }

      if(var4 < -p_82204_3_) {
         var4 = -p_82204_3_;
      }

      return p_82204_1_ + var4;
   }

   private void func_82216_a(int p_82216_1_, EntityLivingBase p_82216_2_) {
      this.func_82209_a(p_82216_1_, p_82216_2_.posX, p_82216_2_.posY + (double)p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.posZ, p_82216_1_ == 0 && this.rand.nextFloat() < 0.001F);
   }

   private void func_82209_a(int p_82209_1_, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_) {
      this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
      double var9 = this.func_82214_u(p_82209_1_);
      double var11 = this.func_82208_v(p_82209_1_);
      double var13 = this.func_82213_w(p_82209_1_);
      double var15 = p_82209_2_ - var9;
      double var17 = p_82209_4_ - var11;
      double var19 = p_82209_6_ - var13;
      EntityWitherSkull var21 = new EntityWitherSkull(this.worldObj, this, var15, var17, var19);
      if(p_82209_8_) {
         var21.setInvulnerable(true);
      }

      var21.posY = var11;
      var21.posX = var9;
      var21.posZ = var13;
      this.worldObj.spawnEntityInWorld(var21);
   }

   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
      this.func_82216_a(0, p_82196_1_);
   }

   public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(p_70097_1_ == DamageSource.drown) {
         return false;
      } else if(this.func_82212_n() > 0) {
         return false;
      } else {
         Entity var3;
         if(this.isArmored()) {
            var3 = p_70097_1_.getSourceOfDamage();
            if(var3 instanceof EntityArrow) {
               return false;
            }
         }

         var3 = p_70097_1_.getEntity();
         if(var3 != null && !(var3 instanceof EntityPlayer) && var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).getCreatureAttribute() == this.getCreatureAttribute()) {
            return false;
         } else {
            if(this.field_82222_j <= 0) {
               this.field_82222_j = 20;
            }

            for(int var4 = 0; var4 < this.field_82224_i.length; ++var4) {
               this.field_82224_i[var4] += 3;
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
         }
      }
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      this.func_145779_a(Items.nether_star, 1);
      if(!this.worldObj.isClient) {
         Iterator var3 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(50.0D, 100.0D, 50.0D)).iterator();

         while(var3.hasNext()) {
            EntityPlayer var4 = (EntityPlayer)var3.next();
            var4.triggerAchievement(AchievementList.field_150964_J);
         }
      }
   }

   public void despawnEntity() {
      this.entityAge = 0;
   }

   public int getBrightnessForRender(float p_70070_1_) {
      return 15728880;
   }

   protected void fall(float p_70069_1_) {}

   public void addPotionEffect(PotionEffect p_70690_1_) {}

   protected boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
   }

   public float func_82207_a(int p_82207_1_) {
      return this.field_82221_e[p_82207_1_];
   }

   public float func_82210_r(int p_82210_1_) {
      return this.field_82220_d[p_82210_1_];
   }

   public int func_82212_n() {
      return this.dataWatcher.getWatchableObjectInt(20);
   }

   public void func_82215_s(int p_82215_1_) {
      this.dataWatcher.updateObject(20, Integer.valueOf(p_82215_1_));
   }

   public int getWatchedTargetId(int p_82203_1_) {
      return this.dataWatcher.getWatchableObjectInt(17 + p_82203_1_);
   }

   public void func_82211_c(int p_82211_1_, int p_82211_2_) {
      this.dataWatcher.updateObject(17 + p_82211_1_, Integer.valueOf(p_82211_2_));
   }

   public boolean isArmored() {
      return this.getHealth() <= this.getMaxHealth() / 2.0F;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public void mountEntity(Entity p_70078_1_) {
      this.ridingEntity = null;
   }
}
