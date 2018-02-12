package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityKingZombie extends EntityMob implements IBossDisplayData {
   private int attackTimer;

   public EntityKingZombie(World p_i1694_1_) {
      super(p_i1694_1_);
      this.setSize(1.4F, 2.9F);
      this.getNavigator().setAvoidsWater(true);
      this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
      this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
      this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.addPotionEffect(new PotionEffect(Potion.resistance.id, 88889, 0));
      this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 88889, 0));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(25.0D);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(250000.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(100.0D);
   }

   protected int decreaseAirSupply(int p_70682_1_) {
      return p_70682_1_;
   }

   protected void collideWithEntity(Entity p_82167_1_) {
      if(p_82167_1_ instanceof IMob && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((EntityLivingBase)p_82167_1_);
      }

      super.collideWithEntity(p_82167_1_);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(this.attackTimer > 0) {
         --this.attackTimer;
      }

      this.worldObj.spawnParticle("flame", this.posX + (double)(this.rand.nextFloat() - 0.5F) * 2.5D, this.posY + 1.0D + (double)(this.rand.nextFloat() - 0.5F) * 1.0D, this.posZ + (double)(this.rand.nextFloat() - 0.5F) * 2.5D, 0.0D, 0.0D, 0.0D);
   }

   public boolean attackEntityAsMob(Entity p_70652_1_) {
      this.attackTimer = 5;
      this.worldObj.setEntityState(this, (byte)4);
      boolean var2 = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));
      if(var2) {
         p_70652_1_.motionY += 0.4000000059604645D;
      }

      this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      return var2;
   }

   public void handleHealthUpdate(byte p_70103_1_) {
      if(p_70103_1_ == 4) {
         this.attackTimer = 10;
         this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      } else {
         super.handleHealthUpdate(p_70103_1_);
      }
   }

   public int getAttackTimer() {
      return this.attackTimer;
   }

   protected String getHurtSound() {
      return "mob.zombie.hurt";
   }

   protected String getDeathSound() {
      return "mob.wither.death";
   }

   protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
      this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
   }

   public void onDeath(DamageSource p_70645_1_) {
      super.onDeath(p_70645_1_);
   }
}
