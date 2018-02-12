package net.minecraft.potion;

import com.google.common.collect.Maps;
import fr.thisismac.injector.Core;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StringUtils;

public class Potion {
   public static final Potion[] potionTypes = new Potion[99];
   public static final Potion field_76423_b = null;
   public static final Potion moveSpeed = (new Potion(1, false, 8171462)).setPotionName("potion.moveSpeed").setIconIndex(0, 0).func_111184_a(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
   public static final Potion moveSlowdown = (new Potion(2, true, 5926017)).setPotionName("potion.moveSlowdown").setIconIndex(1, 0).func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
   public static final Potion digSpeed = (new Potion(3, false, 14270531)).setPotionName("potion.digSpeed").setIconIndex(2, 0).setEffectiveness(1.5D);
   public static final Potion digSlowdown = (new Potion(4, true, 4866583)).setPotionName("potion.digSlowDown").setIconIndex(3, 0);
   public static final Potion damageBoost = (new PotionAttackDamage(5, false, 9643043)).setPotionName("potion.damageBoost").setIconIndex(4, 0).func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 3.0D, 2);
   public static final Potion heal = (new PotionHealth(6, false, 16262179)).setPotionName("potion.heal");
   public static final Potion harm = (new PotionHealth(7, true, 4393481)).setPotionName("potion.harm");
   public static final Potion jump = (new Potion(8, false, 2293580)).setPotionName("potion.jump").setIconIndex(2, 1);
   public static final Potion confusion = (new Potion(9, true, 5578058)).setPotionName("potion.confusion").setIconIndex(3, 1).setEffectiveness(0.25D);
   public static final Potion regeneration = (new Potion(10, false, 13458603)).setPotionName("potion.regeneration").setIconIndex(7, 0).setEffectiveness(0.25D);
   public static final Potion resistance = (new Potion(11, false, 564654541)).setPotionName("potion.resistance").setIconIndex(6, 1);
   public static final Potion fireResistance = (new Potion(12, false, 14981690)).setPotionName("potion.fireResistance").setIconIndex(7, 1);
   public static final Potion waterBreathing = (new Potion(13, false, 3035801)).setPotionName("potion.waterBreathing").setIconIndex(0, 2);
   public static final Potion invisibility = (new Potion(14, false, 8356754)).setPotionName("potion.invisibility").setIconIndex(0, 1);
   public static final Potion blindness = (new Potion(15, true, 2039587)).setPotionName("potion.blindness").setIconIndex(5, 1).setEffectiveness(0.25D);
   public static final Potion nightVision = (new Potion(16, false, 2039713)).setPotionName("potion.nightVision").setIconIndex(4, 1);
   public static final Potion hunger = (new Potion(17, true, 5797459)).setPotionName("potion.hunger").setIconIndex(1, 1);
   public static final Potion weakness = (new PotionAttackDamage(18, true, 4738376)).setPotionName("potion.weakness").setIconIndex(5, 0).func_111184_a(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
   public static final Potion poison = (new Potion(19, true, 5149489)).setPotionName("potion.poison").setIconIndex(6, 0).setEffectiveness(0.25D);
   public static final Potion wither = (new Potion(20, true, 3484199)).setPotionName("potion.wither").setIconIndex(1, 2).setEffectiveness(0.25D);
   public static final Potion field_76434_w = (new PotionHealthBoost(21, false, 16284963)).setPotionName("potion.healthBoost").setIconIndex(2, 2).func_111184_a(SharedMonsterAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
   public static final Potion field_76444_x = (new PotionAbsoption(22, false, 2445989)).setPotionName("potion.absorption").setIconIndex(2, 2);
   public static final Potion field_76443_y = (new PotionHealth(23, false, 16262179)).setPotionName("potion.saturation");
   public static final Potion field_76442_z = null;
   public static final Potion field_76409_A = null;
   public static final Potion field_76410_B = null;
   public static final Potion field_76411_C = null;
   public static final Potion field_76405_D = null;
   public static final Potion field_76406_E = null;
   public static final Potion field_76407_F = null;
   public static final Potion field_76408_G = null;
   public static final Potion waterSpeed = (new Potion(25, false, 1039713)).setPotionName("potion.waterSpeed").setIconIndex(0, 2).setPotionName("Nage Rapide");
   public static final Potion swordHat = (new Potion(26, false, 0)).setPotionName("potion.swordHat").setIconIndex(3, 2).setPotionName("Ep\u00e9e sanglante");
   public static final Potion noelHat = (new Potion(27, false, 0)).setPotionName("potion.noelHat").setIconIndex(3, 2).setPotionName("Chapeau de no\u00ebl");
   public static final Potion chickenHat = (new Potion(28, false, 0)).setPotionName("potion.chickenHat").setIconIndex(3, 2).setPotionName("Poulet malicieux");
   public static final Potion hornsHat = (new Potion(29, false, 0)).setPotionName("potion.hornsHat").setIconIndex(3, 2).setPotionName("Cornes du destin");
   public static final Potion sack = (new Potion(30, false, 0)).setPotionName("potion.sack").setIconIndex(3, 2).setPotionName("Sac du guerrier");
   public static final Potion initiatedHat = (new Potion(31, false, 0)).setPotionName("potion.initiatedHat").setIconIndex(3, 2).setPotionName("Chapeau initi\u00e9");
   public static final Potion advancedHat = (new Potion(32, false, 0)).setPotionName("potion.advancedHat").setIconIndex(3, 2).setPotionName("Chapeau ancien");
   public static final Potion octopusHat = (new Potion(33, false, 0)).setPotionName("potion.octopusHat").setIconIndex(3, 2).setPotionName("Poulpy");
   public static final Potion chestHat = (new Potion(34, false, 0)).setPotionName("potion.chestHat").setIconIndex(3, 2).setPotionName("Coffre dor\u00e9");
   public static final Potion clamHat = (new Potion(35, false, 0)).setPotionName("potion.clamHat").setIconIndex(3, 2).setPotionName("Lourde palourde");
   public static final Potion demonWings = (new Potion(36, false, 0)).setPotionName("potion.demonWings").setPotionName("Ailes d\u00e9moniaques");
   public static final Potion angelWings = (new Potion(37, false, 0)).setPotionName("potion.angelWings").setIconIndex(3, 2).setPotionName("Ailes ang\u00e9liques");
   public static final Potion westernHat = (new Potion(38, false, 0)).setPotionName("potion.westernHat").setIconIndex(3, 2).setPotionName("Chapeau western");
   public static final Potion slimeHat = (new Potion(39, false, 0)).setPotionName("potion.slimeHat").setIconIndex(3, 2).setPotionName("Slimy");
   public static final Potion redCrystalHat = (new Potion(40, false, 0)).setPotionName("potion.redCrystalHat").setIconIndex(3, 2).setPotionName("Cristaux de rubis");
   public static final Potion gravestoneHat = (new Potion(41, false, 0)).setPotionName("potion.gravestoneHat").setIconIndex(3, 2).setPotionName("Pierre tombale");
   public static final Potion iceCrystalHat = (new Potion(42, false, 0)).setPotionName("potion.iceCrystalHat").setIconIndex(3, 2).setPotionName("Cristal de glace");
   public static final Potion netherCrystalHat = (new Potion(43, false, 0)).setPotionName("potion.netherCrystalHat").setIconIndex(3, 2).setPotionName("Cristal du nether");
   public static final Potion humbleHat = (new Potion(44, false, 0)).setPotionName("potion.humbleHat").setIconIndex(3, 2).setPotionName("Humble chapeau");
   public static final Potion afroHat = (new Potion(45, false, 0)).setPotionName("potion.afroHat").setIconIndex(3, 2).setPotionName("Sympatique afro");
   public static final Potion beard = (new Potion(46, false, 0)).setPotionName("potion.beard").setIconIndex(3, 2).setPotionName("Barbiche");
   public static final Potion crownHat = (new Potion(47, false, 0)).setPotionName("potion.crownHat").setIconIndex(3, 2).setPotionName("Couronne sacr\u00e9e");
   public static final Potion cardboardHat = (new Potion(48, false, 0)).setPotionName("potion.cardboardHat").setIconIndex(3, 2).setPotionName("Carton");
   public static final Potion like = (new Potion(49, false, 0)).setPotionName("potion.like").setIconIndex(3, 2).setPotionName("Like");
   public static final Potion crazyGooglesHat = (new Potion(50, false, 0)).setPotionName("potion.crazyGooglesHat").setIconIndex(3, 2).setPotionName("Lunettes folles");
   public static final Potion youtubeHat = (new Potion(51, false, 0)).setPotionName("potion.youtubeHat").setIconIndex(3, 2).setPotionName("Stuff Youtube");
   public static final Potion pickaxeHat = (new Potion(52, false, 0)).setPotionName("potion.pickaxeHat").setIconIndex(3, 2).setPotionName("Pioche enfich\u00e9e");
   public static final Potion crazyHat = (new Potion(53, false, 0)).setPotionName("potion.crazyHat").setIconIndex(3, 2).setPotionName("Chapeau de fou");
   public static final Potion encouragementHat = (new Potion(54, false, 0)).setPotionName("potion.encouragementHat").setIconIndex(3, 2).setPotionName("Encouragement");
   public static final Potion pandaHat = (new Potion(55, false, 0)).setPotionName("potion.pandaHat").setIconIndex(3, 2).setPotionName("Chapeau panda");
   public static final Potion partyHat = (new Potion(56, false, 0)).setPotionName("potion.partyHat").setIconIndex(3, 2).setPotionName("Chapeau de f\u00eate");
   public static final Potion penguinHat = (new Potion(57, false, 0)).setPotionName("potion.penguinHat").setIconIndex(3, 2).setPotionName("Pingloo");
   public static final Potion pigHat = (new Potion(58, false, 0)).setPotionName("potion.pigHat").setIconIndex(3, 2).setPotionName("Ruben");
   public static final Potion scubaHat = (new Potion(59, false, 0)).setPotionName("potion.scubaHat").setIconIndex(3, 2).setPotionName("Masque de survie");
   public static final Potion afkHat = (new Potion(60, false, 0)).setPotionName("potion.afkHat").setIconIndex(3, 2).setPotionName("Absent\u00e9isme");
   public static final Potion armorBreak = (new Potion(61, true, 0)).setPotionName("potion.armorBreak").setIconIndex(7, 2).setPotionName("Brise-Armure").setEffectiveness(0.25D);
   public static final Potion featherFalling = (new Potion(62, false, 16579836)).setPotionName("potion.featherFalling").setIconIndex(5, 2).setPotionName("Antichute").setEffectiveness(0.5D);
   public static final Potion antiEscape = (new Potion(63, true, 16186003)).setPotionName("potion.antiEscape").setIconIndex(6, 2).setPotionName("Anti-\u00e9vasion").setEffectiveness(0.9D);
   public static final Potion circleFireAura = (new Potion(64, false, 0)).setPotionName("potion.circleFireAura").setIconIndex(3, 2).setPotionName("Aura de feu");
   public static final Potion circleSmokeAura = (new Potion(65, false, 0)).setPotionName("potion.circleSmokeAura").setIconIndex(3, 2).setPotionName("Aura de fum\u00e9e");
   public static final Potion circleRedAura = (new Potion(66, false, 0)).setPotionName("potion.circleRedAura").setIconIndex(3, 2).setPotionName("Aura de redstone");
   public static final Potion circleDarkAura = (new Potion(67, false, 0)).setPotionName("potion.circleDarkAura").setIconIndex(3, 2).setPotionName("Aura sombre");
   public static final Potion feetMagicAura = (new Potion(68, false, 0)).setPotionName("potion.feetMagicAura").setIconIndex(3, 2).setPotionName("Train\u00e9e magique");
   public static final Potion feetWhiteAura = (new Potion(69, false, 0)).setPotionName("potion.feetWhiteAura").setIconIndex(3, 2).setPotionName("Train\u00e9e blanche");
   public static final Potion feetRedAura = (new Potion(70, false, 0)).setPotionName("potion.feetRedAura").setIconIndex(3, 2).setPotionName("Train\u00e9e rouge");
   public static final Potion catears = (new Potion(71, false, 0)).setPotionName("potion.catears").setIconIndex(3, 2).setPotionName("Oreille de chat");
   public static final Potion steston = (new Potion(72, false, 0)).setPotionName("potion.steston").setIconIndex(3, 2).setPotionName("Chapeau de l\'ouest");
   public static final Potion archerBack = (new Potion(73, false, 0)).setPotionName("potion.archeback").setIconIndex(3, 2).setPotionName("Sac a dos archer");
   public static final Potion assassinBack = (new Potion(74, false, 0)).setPotionName("potion.assasinback").setIconIndex(3, 2).setPotionName("Sac a dos assassn");
   public static final Potion craftmanBack = (new Potion(75, false, 0)).setPotionName("potion.craftmanback").setIconIndex(3, 2).setPotionName("Sac a dos craftman");
   public static final Potion minerBack = (new Potion(76, false, 0)).setPotionName("potion.minerback").setIconIndex(3, 2).setPotionName("sac a dos miner");
   public static final Potion warriorback = (new Potion(77, false, 2)).setPotionName("potion.warriorback").setIconIndex(3, 2).setPotionName("Sac a dos warrior");
   public static final Potion crystal = (new Potion(78, false, 0)).setPotionName("potion.crystal").setIconIndex(3, 2).setPotionName("Crystal");
   public static final Potion reindeer = (new Potion(79, false, 0)).setPotionName("potion.reindeer").setIconIndex(3, 2).setPotionName("reindeer");
   public static final Potion test = (new Potion(80, false, 0)).setPotionName("potion.test").setIconIndex(3, 2).setPotionName("testing");
   public final int id;
   private final Map field_111188_I = Maps.newHashMap();
   private final boolean isBadEffect;
   private final int liquidColor;
   private String name = "";
   private int statusIconIndex = -1;
   private double effectiveness;
   private boolean usable;
   private static final String __OBFID = "CL_00001528";
   private static final String __OBFID = "CL_00001528";

   public Potion(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
      this.id = p_i1573_1_;
      potionTypes[p_i1573_1_] = this;
      this.isBadEffect = p_i1573_2_;
      if(p_i1573_2_) {
         this.effectiveness = 0.5D;
      } else {
         this.effectiveness = 1.0D;
      }

      this.liquidColor = p_i1573_3_;
   }

   public Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
      this.statusIconIndex = p_76399_1_ + p_76399_2_ * 8;
      return this;
   }

   public int getId() {
      return this.id;
   }

   public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_) {
      if(this.id == regeneration.id) {
         if(p_76394_1_.getHealth() < p_76394_1_.getMaxHealth()) {
            p_76394_1_.heal(1.0F);
         }
      } else if(this.id == poison.id) {
         if(p_76394_1_.getHealth() > 1.0F) {
            p_76394_1_.attackEntityFrom(DamageSource.magic, 1.0F);
         }
      } else if(this.id == wither.id) {
         p_76394_1_.attackEntityFrom(DamageSource.wither, 1.0F);
      } else if(this.id == hunger.id && p_76394_1_ instanceof EntityPlayer) {
         ((EntityPlayer)p_76394_1_).addExhaustion(0.025F * (float)(p_76394_2_ + 1));
      } else if(this.id == field_76443_y.id && p_76394_1_ instanceof EntityPlayer) {
         if(!p_76394_1_.worldObj.isClient) {
            ((EntityPlayer)p_76394_1_).getFoodStats().addStats(p_76394_2_ + 1, 1.0F);
         }
      } else if((this.id != heal.id || p_76394_1_.isEntityUndead()) && (this.id != harm.id || !p_76394_1_.isEntityUndead())) {
         if(this.id == harm.id && !p_76394_1_.isEntityUndead() || this.id == heal.id && p_76394_1_.isEntityUndead()) {
            p_76394_1_.attackEntityFrom(DamageSource.magic, (float)(2 << p_76394_2_));
         }
      } else {
         p_76394_1_.heal((float)Math.max(4 << p_76394_2_, 0));
      }
   }

   public void affectEntity(EntityLivingBase p_76402_1_, EntityLivingBase p_76402_2_, int p_76402_3_, double p_76402_4_) {
      int var6;
      if((this.id != heal.id || p_76402_2_.isEntityUndead()) && (this.id != harm.id || !p_76402_2_.isEntityUndead())) {
         if(this.id == harm.id && !p_76402_2_.isEntityUndead() || this.id == heal.id && p_76402_2_.isEntityUndead()) {
            Core.get().getClass();
            var6 = (int)(p_76402_4_ * (double)(2 << p_76402_3_) + 0.5D);
            if(p_76402_1_ == null) {
               p_76402_2_.attackEntityFrom(DamageSource.magic, (float)var6);
            } else {
               p_76402_2_.attackEntityFrom(DamageSource.causeIndirectMagicDamage(p_76402_2_, p_76402_1_), (float)var6);
            }
         }
      } else {
         var6 = (int)(p_76402_4_ * (double)(4 << p_76402_3_) + 0.5D);
         p_76402_2_.heal((float)var6);
      }
   }

   public boolean isInstant() {
      return false;
   }

   public boolean isReady(int p_76397_1_, int p_76397_2_) {
      int var3;
      if(this.id == regeneration.id) {
         var3 = 50 >> p_76397_2_;
         return var3 > 0?p_76397_1_ % var3 == 0:true;
      } else if(this.id == poison.id) {
         var3 = 25 >> p_76397_2_;
         return var3 > 0?p_76397_1_ % var3 == 0:true;
      } else if(this.id == wither.id) {
         var3 = 40 >> p_76397_2_;
         return var3 > 0?p_76397_1_ % var3 == 0:true;
      } else {
         return this.id == hunger.id;
      }
   }

   public Potion setPotionName(String p_76390_1_) {
      this.name = p_76390_1_;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public boolean hasStatusIcon() {
      return this.statusIconIndex >= 0;
   }

   public int getStatusIconIndex() {
      return this.statusIconIndex;
   }

   public boolean isBadEffect() {
      return this.isBadEffect;
   }

   public static String getDurationString(PotionEffect p_76389_0_) {
      if(p_76389_0_.getIsPotionDurationMax()) {
         return "**:**";
      } else {
         int var1 = p_76389_0_.getDuration();
         return StringUtils.ticksToElapsedTime(var1);
      }
   }

   public Potion setEffectiveness(double p_76404_1_) {
      this.effectiveness = p_76404_1_;
      return this;
   }

   public double getEffectiveness() {
      return this.effectiveness;
   }

   public boolean isUsable() {
      return this.usable;
   }

   public int getLiquidColor() {
      return this.liquidColor;
   }

   public Potion func_111184_a(IAttribute p_111184_1_, String p_111184_2_, double p_111184_3_, int p_111184_5_) {
      AttributeModifier var6 = new AttributeModifier(UUID.fromString(p_111184_2_), this.getName(), p_111184_3_, p_111184_5_);
      this.field_111188_I.put(p_111184_1_, var6);
      return this;
   }

   public Map func_111186_k() {
      return this.field_111188_I;
   }

   public void removeAttributesModifiersFromEntity(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_) {
      Iterator var4 = this.field_111188_I.entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         IAttributeInstance var6 = p_111187_2_.getAttributeInstance((IAttribute)var5.getKey());
         if(var6 != null) {
            var6.removeModifier((AttributeModifier)var5.getValue());
         }
      }
   }

   public void applyAttributesModifiersToEntity(EntityLivingBase p_111185_1_, BaseAttributeMap p_111185_2_, int p_111185_3_) {
      Iterator var4 = this.field_111188_I.entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         IAttributeInstance var6 = p_111185_2_.getAttributeInstance((IAttribute)var5.getKey());
         if(var6 != null) {
            AttributeModifier var7 = (AttributeModifier)var5.getValue();
            var6.removeModifier(var7);
            var6.applyModifier(new AttributeModifier(var7.getID(), this.getName() + " " + p_111185_3_, this.func_111183_a(p_111185_3_, var7), var7.getOperation()));
         }
      }
   }

   public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
      return p_111183_2_.getAmount() * (double)(p_111183_1_ + 1);
   }
}
