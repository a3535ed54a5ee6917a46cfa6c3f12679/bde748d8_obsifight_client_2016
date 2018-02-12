package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHellCow extends EntityAnimal {
   public int attackTimer;

   public EntityHellCow(World p_i1683_1_) {
      super(p_i1683_1_);
      this.setSize(0.9F, 1.3F);
      this.getNavigator().setAvoidsWater(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
      this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.wheat, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
      this.tasks.addTask(8, new EntityAIAttackOnCollide(this, 1.0D, true));
      this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.isImmuneToFire = true;
   }

   public boolean isAIEnabled() {
      return true;
   }

   public boolean attackEntityAsMob(Entity p_70652_1_) {
      this.attackTimer = 10;
      this.worldObj.setEntityState(this, (byte)4);
      boolean var2 = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));
      if(var2) {
         p_70652_1_.motionY += 0.4000000059604645D;
      }

      p_70652_1_.attackEntityFrom(DamageSource.onFire, 3.0F);
      this.playSound("mob.irongolem.throw", 1.0F, 2.0F);
      return var2;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(this.attackTimer > 0) {
         --this.attackTimer;
      }

      if(this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
         int var1 = MathHelper.floor_double(this.posX);
         int var2 = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
         int var3 = MathHelper.floor_double(this.posZ);
         Block var4 = this.worldObj.getBlock(var1, var2, var3);
         if(var4.getMaterial() != Material.air) {
            this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(var4) + "_" + this.worldObj.getBlockMetadata(var1, var2, var3), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
         }
      }
   }

   protected String getLivingSound() {
      return "mob.cow.say";
   }

   protected String getHurtSound() {
      return "mob.cow.hurt";
   }

   protected String getDeathSound() {
      return "mob.cow.hurt";
   }

   protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
      this.playSound("mob.cow.step", 0.15F, 1.0F);
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   protected Item func_146068_u() {
      return Items.magma_cream;
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);

      for(int var4 = 0; var4 < var3; ++var4) {
         this.func_145779_a(Items.magma_cream, 1);
      }

      var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_70628_2_);

      for(int var5 = 0; var5 < var3; ++var5) {
         if(this.isBurning()) {
            this.func_145779_a(Items.magma_cream, 1);
         } else {
            this.func_145779_a(Items.magma_cream, 1);
         }
      }
   }

   public boolean interact(EntityPlayer p_70085_1_) {
      ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
      if(var2 != null && var2.getItem() == Items.bucket && !p_70085_1_.capabilities.isCreativeMode) {
         if(var2.stackSize-- == 1) {
            p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(Items.lava_bucket));
         } else if(!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(Items.lava_bucket))) {
            p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(Items.lava_bucket, 1, 0), false);
         }

         return true;
      } else {
         return super.interact(p_70085_1_);
      }
   }

   public EntityHellCow createChild(EntityAgeable p_90011_1_) {
      return new EntityHellCow(this.worldObj);
   }

   public EntityAgeable createChild(EntityAgeable var1) {
      return this.createChild(var1);
   }
}
