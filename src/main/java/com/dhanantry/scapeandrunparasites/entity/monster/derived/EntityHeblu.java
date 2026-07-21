package com.dhanantry.scapeandrunparasites.entity.monster.derived;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCosmical;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDerived;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAlafhaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHebluLight;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHeblu extends EntityPDerived implements EntityCutomAttack, EntityBodyParts, IRangedAttackMob, EntityCanFly {
   protected static final DataParameter<Boolean> FLYING = EntityDataManager.func_187226_a(EntityHeblu.class, DataSerializers.field_187198_h);
   private int flying;
   private float aaa;
   private float sss;
   private int hebluLightBarrageSoundTimer = 0;
   private EntityBody leftTendril;
   private EntityBody rightTendril;
   private EntityBody head;
   private float leftTendrilHealth;
   private float rightTendrilHealth;
   private float headlHealth;
   public int vomit;
   private BlockPos vomitPos;
   public boolean raining;
   private int rainingOrbs = 0;
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityHeblu.class, DataSerializers.field_187191_a);
   private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityHeblu.class, DataSerializers.field_187198_h);
   private int limit;
   private boolean skillFlame;
   private double tttX;
   private double tttY;
   private double tttZ;
   private double tttH;
   private double tttHH;

   public EntityHeblu(World worldIn) {
      super(worldIn);
      this.func_70105_a(2.4F, 3.8F);
      this.field_70138_W = 1.0F;
      this.canModRender = 0;
      this.type = 14;
      this.killcount = -10.0;
      this.field_70714_bg.func_85156_a(this.folow);
      this.flying = 0;
      this.skillFlame = false;
      this.leftTendril = new EntityBody(this, 3.3F, 2.5F, 1.0F, 4.1F, 3.3F, 1, 1, true);
      this.rightTendril = new EntityBody(this, 3.3F, 2.5F, 1.0F, 4.1F, 3.3F, -1, 2, true);
      this.head = new EntityBody(this, 2.2F, 2.2F, 1.0F, 4.0F, 2.0F, -1, 3, false, 0.2F);
      this.leftTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.rightTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.headlHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.field_70158_ak = true;
      this.field_70765_h = new EntityHeblu.AIMoveControl(this);
   }

   @Override
   public int getParasiteIDRegister() {
      return 309;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 9.0));
      this.field_70714_bg.func_75776_a(5, new EntityHeblu.AIMoveRandom());
      this.field_70714_bg.func_75776_a(6, new EntityHeblu.AIFireballAttack(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.derivedFollow, true, 3));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 12.0F));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.3, 100, 40.0F, false));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.HEBLU_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.HEBLU_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.HEBLU_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.HEBLU_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.derivedFollow);
   }

   @Override
   public void func_70636_d() {
      if (!this.func_175446_cd()) {
         super.func_70636_d();
         this.killcount = -10.0;
         if (!this.field_70170_p.field_72995_K && this.hebluLightBarrageSoundTimer > 0) {
            this.hebluLightBarrageSoundTimer--;
            if (this.hebluLightBarrageSoundTimer == 0) {
               this.field_70170_p
                  .func_184148_a(
                     null,
                     this.field_70165_t,
                     this.field_70163_u + this.field_70131_O * 0.6,
                     this.field_70161_v,
                     SRPSounds.HEBLU_LIGHT_IMPACT,
                     SoundCategory.HOSTILE,
                     1.2F,
                     1.45F + this.field_70146_Z.nextFloat() * 0.18F
                  );
            }
         }

         if (this.headlHealth > 0.0F) {
            this.head.func_70071_h_();
         }

         if (this.leftTendrilHealth > 0.0F) {
            this.leftTendril.func_70071_h_();
         }

         if (this.rightTendrilHealth > 0.0F) {
            this.rightTendril.func_70071_h_();
         }

         if (!this.field_70170_p.field_72995_K && this.srpTicks == 10) {
            if ((
                  this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a
                     || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a
               )
               && this.getFlyingState()
               && this.field_70146_Z.nextInt(3) == 0) {
               this.field_70181_x = 0.5;
            }

            if (this.rainingOrbs > 0) {
               this.rainingOrbs--;
               if (this.rainingOrbs <= 15) {
                  double radius = 10.0;
                  double x = this.field_70165_t + (this.field_70146_Z.nextDouble() * 2.0 - 1.0) * radius;
                  double y = this.field_70163_u + 20.0;
                  double z = this.field_70161_v + (this.field_70146_Z.nextDouble() * 2.0 - 1.0) * radius;
                  if (this.func_70638_az() != null && this.field_70146_Z.nextBoolean()) {
                     x = this.func_70638_az().field_70165_t + (this.field_70146_Z.nextDouble() * 2.0 - 1.0) * radius;
                     z = this.func_70638_az().field_70161_v + (this.field_70146_Z.nextDouble() * 2.0 - 1.0) * radius;
                  }

                  new BlockPos(x, y, z);
                  EntityProjectileAlafhaBall entitylargefireball = new EntityProjectileAlafhaBall(this.field_70170_p, this, 0.0, -10.0, 0.0);
                  entitylargefireball.field_70165_t = x;
                  entitylargefireball.field_70163_u = y;
                  entitylargefireball.field_70161_v = z;
                  this.field_70170_p.func_72838_d(entitylargefireball);
               }
            }

            if (this.field_70146_Z.nextInt(25) == 0 && !this.getFlyingState() && this.vomit <= 0) {
               this.changeStateTo(true);
               return;
            }
         }

         if (this.vomit > 0) {
            if (!this.field_70170_p.field_72995_K) {
               this.lookAt(this.vomitPos.func_177958_n(), this.vomitPos.func_177956_o(), this.vomitPos.func_177952_p());
            }

            this.vomit--;
            if (this.field_70170_p.field_72995_K) {
               for (int i = 0; i < 19; i++) {
                  Vec3d vec3d = this.func_70676_i(1.0F);
                  double bon = 8.2;
                  double offsetX = this.field_70165_t + vec3d.field_72450_a * bon;
                  double offsetY = this.field_70163_u + this.func_70047_e() + 2.2;
                  double offsetZ = this.field_70161_v + vec3d.field_72449_c * bon;
                  if (this.raining) {
                     if (this.getFlyingState()) {
                        bon = 4.3;
                        offsetX = this.field_70165_t + vec3d.field_72450_a * bon;
                        offsetY = this.field_70163_u + this.func_70047_e() + 7.5;
                        offsetZ = this.field_70161_v + vec3d.field_72449_c * bon;
                     } else {
                        bon = 6.1;
                        offsetX = this.field_70165_t + vec3d.field_72450_a * bon;
                        offsetY = this.field_70163_u + this.func_70047_e() + 7.2;
                        offsetZ = this.field_70161_v + vec3d.field_72449_c * bon;
                     }
                  }

                  double motionX = -MathHelper.func_76126_a(this.field_70177_z * (float) Math.PI / 180.0F) * 1.4;
                  double motionZ = MathHelper.func_76134_b(this.field_70177_z * (float) Math.PI / 180.0F) * 1.4;
                  double motionY = -0.55 + this.field_70146_Z.nextDouble() * 0.5;
                  double spreadFactor = 0.55;
                  motionX += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor;
                  motionZ += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor;
                  double rain = 1.0;
                  if (this.raining) {
                     motionY = 4.5 + this.field_70146_Z.nextDouble() * 0.3;
                     spreadFactor = 0.2;
                     motionX += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor;
                     motionZ += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor;
                     rain = 0.2;
                  }

                  this.spawnParticles(EnumParticleTypes.FLAME, offsetX, offsetY, offsetZ, motionX * rain, motionY * rain, motionZ * rain);
                  this.spawnParticles(SRPEnumParticle.GCLOUD, -255, 0, 0, offsetX, offsetY, offsetZ, motionX * rain, motionY * rain, motionZ * rain);
               }
            }
         } else {
            this.raining = false;
         }

         if (this.flying >= 1) {
            this.flying++;
         }

         if (this.getFlyingState()) {
            this.aaa += 0.08F;
            this.sss += 0.782F;
            if (this.sss >= 24.0F) {
               this.func_184185_a(SoundEvents.field_187524_aN, 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F);
               this.sss = 0.0F;
            }

            if (this.field_70122_E && !this.field_70170_p.field_72995_K && this.flying > 40) {
               this.changeStateTo(false);
            }
         } else {
            this.aaa = 0.08F;
            this.sss = 0.0F;
         }
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.getFlyingState()) {
         this.func_189654_d(true);
      } else {
         this.func_189654_d(false);
      }
   }

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
      this.field_70708_bq = 0;
      this.vomitPos = target.func_180425_c();
      if (!(target.field_70163_u > this.field_70163_u + 5.0) && !(target.field_70163_u < this.field_70163_u)) {
         this.vomit = 40;
         if (this.field_70146_Z.nextBoolean()) {
            this.raining = true;
            this.rainingOrbs = 19;
            this.field_70170_p.func_72960_a(this, (byte)100);
            this.func_184185_a(
               SRPSounds.HEBLU_SHOOT, this.func_70599_aP() * 2.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F
            );
         } else {
            this.field_70170_p.func_72960_a(this, (byte)101);
         }

         if (!this.raining) {
            Vec3d vec3d = this.func_70676_i(1.0F);
            double bon = 12.5;
            float rad = 2.0F;

            for (int i = 0; i < 3; i++) {
               EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(
                  this.field_70170_p,
                  this.field_70165_t + vec3d.field_72450_a * bon,
                  Math.max(this.field_70163_u, target.field_70163_u),
                  this.field_70161_v + vec3d.field_72449_c * bon
               );
               entityareaeffectcloud.setRadius(rad + 1.0F, 0.9F);
               entityareaeffectcloud.setDuration(100);
               entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
               entityareaeffectcloud.setOwner(this);
               this.field_70170_p.func_72960_a(entityareaeffectcloud, (byte)77);
               entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 300, 0, false, true));
               this.field_70170_p.func_72838_d(entityareaeffectcloud);
               if (i == 1) {
                  bon += 4.0;
               }

               bon += 7.5 + i;
               rad += 2.0F;
            }

            this.vomitPos = new BlockPos(this.field_70165_t + vec3d.field_72450_a * bon, this.field_70163_u, this.field_70161_v + vec3d.field_72449_c * bon);
         }

         this.setWait(80);
      } else {
         double d1 = 4.0;
         Vec3d vec3d = this.func_70676_i(1.0F);
         double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a * 4.0);
         double d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 2.0F - (0.5 + this.field_70163_u + this.field_70131_O / 2.0F);
         double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c * 4.0);
         this.field_70170_p.func_180498_a((EntityPlayer)null, 1016, this.func_180425_c(), 0);
         EntityProjectileAlafhaBall entitylargefireball = new EntityProjectileAlafhaBall(this.field_70170_p, this, d2, d3, d4);
         entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a * 4.0;
         entitylargefireball.field_70163_u = this.field_70163_u + this.field_70131_O / 2.0F + 0.5;
         entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c * 4.0;
         this.field_70170_p.func_72838_d(entitylargefireball);

         for (int i = 0; i <= 2; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.FLAME,
                  this.field_70165_t + vec3d.field_72450_a * 4.0,
                  this.field_70163_u + this.field_70131_O / 2.0F + 0.5,
                  this.field_70161_v + vec3d.field_72449_c * 4.0,
                  0.0,
                  -1.0,
                  0.0,
                  new int[0]
               );
         }
      }
   }

   public void spawnLightBarrage(EntityLivingBase target) {
      this.spawnLightBarrage(target, false);
   }

   public void spawnLightBarrage(EntityLivingBase target, boolean forceTarget) {
      if (!this.field_70170_p.field_72995_K) {
         if (forceTarget || target != null) {
            if (forceTarget || !isInvalidLightBarrageTarget(target)) {
               Vec3d look = this.func_70676_i(1.0F);
               double startX = this.field_70165_t + look.field_72450_a * 2.5;
               double startY = this.field_70163_u + this.field_70131_O * 0.7 + 1.2;
               double startZ = this.field_70161_v + look.field_72449_c * 2.5;
               this.field_70170_p
                  .func_184148_a(
                     null,
                     this.field_70165_t,
                     this.field_70163_u + this.field_70131_O * 0.6,
                     this.field_70161_v,
                     SRPSounds.HEBLU_BELL_CHARGEUP,
                     SoundCategory.HOSTILE,
                     5.0F,
                     0.75F + this.field_70146_Z.nextFloat() * 0.08F
                  );
               this.hebluLightBarrageSoundTimer = 10;
               int count = Math.max(1, SRPConfigMobs.hebluLightBarrageCount);

               for (int i = 0; i < count; i++) {
                  double x = startX + (this.field_70146_Z.nextDouble() - 0.5) * 3.5;
                  double y = startY + (this.field_70146_Z.nextDouble() - 0.5) * 2.5;
                  double z = startZ + (this.field_70146_Z.nextDouble() - 0.5) * 3.5;
                  EntityProjectileHebluLight shard = new EntityProjectileHebluLight(this.field_70170_p, this, target, x, y, z, forceTarget);
                  this.field_70170_p.func_72838_d(shard);
               }
            }
         }
      }
   }

   public void func_184724_a(boolean swingingArms) {
   }

   public float getaaa() {
      return this.aaa;
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag && this.field_70146_Z.nextInt(12) == 0 && !this.getFlyingState()) {
         this.changeStateTo(true);
      }

      return flag;
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else {
         boolean flag = this.func_70097_a(source, amount);
         return !flag ? false : flag;
      }
   }

   @Override
   public void setBodyPartDead(int id) {
      if (this.leftTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      } else if (this.rightTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      } else if (this.head.getId() == id) {
         this.field_70170_p.func_72973_f(this.head);
      }
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      if (this.leftTendril != null) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      }

      if (this.rightTendril != null) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      }

      super.func_70106_y();
   }

   @Override
   protected void spawnCloneCosmical(EntityPCosmical entityout) {
      entityout.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
      entityout.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(entityout)), null);
      if (this.func_145818_k_()) {
         entityout.func_96094_a("--" + this.func_95999_t() + "--");
         entityout.func_174805_g(this.func_174833_aM());
      }

      this.field_70170_p.func_72838_d(entityout);
      entityout.particleStatus((byte)7);
      this.limitClones = entityout.func_145782_y();
      entityout.limitClones = this.func_145782_y();
      this.setShadowStatus(false);
      entityout.setCloneC();
      entityout.func_110148_a(SharedMonsterAttributes.field_111263_d)
         .func_111128_a(entityout.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() * 1.33);
      entityout.func_110148_a(SharedMonsterAttributes.field_111264_e)
         .func_111128_a(entityout.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * 0.5);
      if (this.getFlyingState()) {
         ((EntityHeblu)entityout).changeStateTo(true);
      }
   }

   public void changeStateTo(boolean fly) {
      if (this.limit < 1) {
         if (fly) {
            if (!this.getFlyingState()) {
               if (this.leftTendrilHealth <= 0.0F || this.rightTendrilHealth <= 0.0F) {
                  return;
               }

               this.field_70765_h = new EntityHeblu.AIMoveControl(this);
               this.setParasiteStatus(3);
               this.field_70180_af.func_187227_b(FLYING, true);
               this.field_70181_x = 0.5;
               this.aaa += 0.08F;
               this.flying = 1;
               this.sss = 19.85F;
            }
         } else if (this.getFlyingState()) {
            this.field_70765_h = new EntityMoveHelper(this);
            this.setParasiteStatus(0);
            this.field_70180_af.func_187227_b(FLYING, false);
            this.flying = 0;
            this.aaa = 0.0F;
            this.sss = 0.0F;
         }
      }
   }

   public float func_70047_e() {
      return 1.75F;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      return super.func_70652_k(entityIn);
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      return this.func_70652_k(entityIn);
   }

   @Override
   protected void selfExplode() {
   }

   @Override
   protected void spawnGore() {
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.HEBLU_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.HEBLU_HURT;
   }

   protected float func_70599_aP() {
      return 5.0F;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.HEBLU_DEATH;
   }

   protected SoundEvent getStepSound() {
      return SRPSounds.HEAVY_STEPS;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }

   public boolean getFlyingState() {
      return (Boolean)this.field_70180_af.func_187225_a(FLYING);
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74776_a("parasiteleftTendril", this.leftTendrilHealth);
      compound.func_74776_a("parasiterightTendril", this.rightTendrilHealth);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("parasiteleftTendril", 99)) {
         this.leftTendrilHealth = compound.func_74760_g("parasiteleftTendril");
         if (this.leftTendrilHealth <= 0.0F) {
            this.field_70170_p.func_72960_a(this, (byte)11);
         }
      }

      if (compound.func_150297_b("parasiterightTendril", 99)) {
         this.rightTendrilHealth = compound.func_74760_g("parasiterightTendril");
         if (this.rightTendrilHealth <= 0.0F) {
            this.field_70170_p.func_72960_a(this, (byte)22);
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public float getLeft() {
      return this.leftTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   public float getRight() {
      return this.rightTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   public float getHead() {
      return this.headlHealth;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 11) {
         this.leftTendrilHealth = 0.0F;
      } else if (id == 22) {
         this.rightTendrilHealth = 0.0F;
      } else if (id == 33) {
         this.headlHealth = 0.0F;
      } else if (id == 100) {
         this.vomit = 40;
         this.raining = true;
      } else if (id == 101) {
         this.vomit = 40;
      } else {
         super.func_70103_a(id);
      }
   }

   @Override
   protected EntityPCosmical getThis() {
      return new EntityHeblu(this.field_70170_p);
   }

   public void func_70091_d(MoverType type, double x, double y, double z) {
      super.func_70091_d(type, x, y, z);
      this.func_145775_I();
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(VEX_FLAGS, (byte)0);
      this.field_70180_af.func_187214_a(FLYING, true);
      this.field_70180_af.func_187214_a(ATTACKING, false);
   }

   private boolean getVexFlag(int mask) {
      int i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
      return (i & mask) != 0;
   }

   private void setVexFlag(int mask, boolean value) {
      int i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.field_70180_af.func_187227_b(VEX_FLAGS, (byte)(i & 0xFF));
   }

   @SideOnly(Side.CLIENT)
   public boolean isAttacking() {
      return (Boolean)this.field_70180_af.func_187225_a(ATTACKING);
   }

   public void setAttacking(boolean attacking) {
      this.field_70180_af.func_187227_b(ATTACKING, attacking);
   }

   private static boolean isInvalidLightBarrageTarget(EntityLivingBase target) {
      if (!(target instanceof EntityPlayer)) {
         return false;
      } else {
         EntityPlayer player = (EntityPlayer)target;
         return player.func_184812_l_() || player.func_175149_v();
      }
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillFlame;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillFlame = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.flame();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void flame() {
      if (!this.getFlyingState() && !(this.headlHealth <= 0.0F)) {
         if (this.limit == 0) {
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if (entitylivingbase == null) {
               this.skillFlame = true;
               this.limit = 0;
               return;
            }

            this.tttX = entitylivingbase.field_70165_t;
            this.tttY = entitylivingbase.field_70163_u;
            this.tttZ = entitylivingbase.field_70161_v;
            this.tttH = entitylivingbase.func_174813_aQ().field_72338_b;
            this.tttHH = entitylivingbase.field_70131_O;
         }

         this.limit++;
         this.setParasiteStatus(10);
         this.func_70661_as().func_75492_a(this.tttX, this.tttY, this.tttZ, 0.0);
         this.resetIdleTime();
         if (this.field_70173_aa % 10 == 0) {
            double d1 = 4.0;
            Vec3d vec3d = this.func_70676_i(1.0F);
            double d2 = this.tttX - (this.field_70165_t + vec3d.field_72450_a * 4.0);
            double d3 = this.tttH + this.tttHH / 4.0 - (0.5 + this.field_70163_u + this.field_70131_O / 4.0F);
            double d4 = this.tttZ - (this.field_70161_v + vec3d.field_72449_c * 4.0);
            this.field_70170_p.func_180498_a((EntityPlayer)null, 1016, new BlockPos(this), 0);
            EntityProjectileAlafhaBall entitylargefireball = new EntityProjectileAlafhaBall(this.field_70170_p, this, d2, d3, d4);
            entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a * 4.0;
            entitylargefireball.field_70163_u = this.field_70163_u + this.field_70131_O / 2.0F + 0.5;
            entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c * 4.0;
            this.field_70170_p.func_72838_d(entitylargefireball);
            if (this.limit >= 60) {
               this.skillFlame = true;
               this.setParasiteStatus(0);
               this.limit = 0;
            }
         }
      } else {
         this.skillFlame = true;
         this.limit = 0;
      }
   }

   static class AIFireballAttack extends EntityAIBase {
      private final EntityHeblu parentEntity;
      public int attackTimer;

      public AIFireballAttack(EntityHeblu ghast) {
         this.parentEntity = ghast;
      }

      public boolean func_75250_a() {
         return this.parentEntity.func_70638_az() != null && this.parentEntity.getFlyingState() && this.parentEntity.headlHealth > 0.0F;
      }

      public void func_75249_e() {
         this.attackTimer = 0;
      }

      public void func_75251_c() {
         this.parentEntity.setAttacking(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
         double d0 = 64.0;
         if (entitylivingbase != null) {
            if (entitylivingbase.func_70068_e(this.parentEntity) < 4096.0 && this.parentEntity.func_70685_l(entitylivingbase)) {
               World world = this.parentEntity.field_70170_p;
               this.attackTimer++;
               if (this.parentEntity.func_70644_a(SRPPotions.RAGE_E)) {
                  this.attackTimer++;
               }

               this.parentEntity.resetIdleTime();
               if (this.attackTimer == 10) {
               }

               if (this.attackTimer == 20) {
                  int specialAttack = this.parentEntity.field_70170_p.field_73012_v.nextInt(2);
                  if (specialAttack == 0 && entitylivingbase.field_70122_E) {
                     this.parentEntity.vomitPos = entitylivingbase.func_180425_c();
                     this.parentEntity.vomit = 40;
                     this.parentEntity.raining = true;
                     this.parentEntity.rainingOrbs = 19;
                     world.func_72960_a(this.parentEntity, (byte)100);
                     this.parentEntity
                        .func_184185_a(
                           SRPSounds.HEBLU_SHOOT,
                           this.parentEntity.func_70599_aP() * 2.0F,
                           (this.parentEntity.field_70170_p.field_73012_v.nextFloat() - this.parentEntity.field_70170_p.field_73012_v.nextFloat()) * 0.2F
                              + 1.0F
                        );
                     this.attackTimer = -60;
                     return;
                  }

                  if (specialAttack == 1 && !EntityHeblu.isInvalidLightBarrageTarget(entitylivingbase)) {
                     this.parentEntity.spawnLightBarrage(entitylivingbase);
                     this.attackTimer = -85;
                     return;
                  }

                  double d1 = 4.0;
                  Vec3d vec3d = this.parentEntity.func_70676_i(1.0F);
                  double d2 = entitylivingbase.field_70165_t - (this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0);
                  double d3 = entitylivingbase.func_174813_aQ().field_72338_b
                     + entitylivingbase.field_70131_O / 2.0F
                     - (0.5 + this.parentEntity.field_70163_u + this.parentEntity.field_70131_O / 2.0F);
                  double d4 = entitylivingbase.field_70161_v - (this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0);
                  world.func_180498_a((EntityPlayer)null, 1016, new BlockPos(this.parentEntity), 0);
                  EntityProjectileAlafhaBall entitylargefireball = new EntityProjectileAlafhaBall(world, this.parentEntity, d2, d3, d4);
                  entitylargefireball.field_70165_t = this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0;
                  entitylargefireball.field_70163_u = this.parentEntity.field_70163_u + this.parentEntity.field_70131_O / 2.0F + 0.5;
                  entitylargefireball.field_70161_v = this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0;
                  world.func_72838_d(entitylargefireball);
                  this.parentEntity.field_70708_bq = 0;
                  this.attackTimer = -45;

                  for (int i = 0; i <= 2; i++) {
                     this.parentEntity
                        .field_70170_p
                        .func_175688_a(
                           EnumParticleTypes.FLAME,
                           this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0,
                           this.parentEntity.field_70163_u + this.parentEntity.field_70131_O / 2.0F + 0.5,
                           this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0,
                           0.0,
                           -1.0,
                           0.0,
                           new int[0]
                        );
                  }
               }
            } else if (this.attackTimer > 0) {
               this.attackTimer--;
            }

            this.parentEntity.setAttacking(this.attackTimer > 10);
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityHeblu vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityHeblu.this.field_70165_t;
            double d1 = this.field_75647_c - EntityHeblu.this.field_70163_u;
            double d2 = this.field_75644_d - EntityHeblu.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityHeblu.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityHeblu.this.field_70159_w *= 0.5;
               EntityHeblu.this.field_70181_x *= 0.5;
               EntityHeblu.this.field_70179_y *= 0.5;
            } else {
               EntityHeblu.this.field_70159_w = EntityHeblu.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityHeblu.this.field_70181_x = EntityHeblu.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityHeblu.this.field_70179_y = EntityHeblu.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityHeblu.this.func_70638_az() == null) {
                  EntityHeblu.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityHeblu.this.field_70159_w, EntityHeblu.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntityHeblu.this.field_70761_aq = EntityHeblu.this.field_70177_z;
               } else {
                  double d4 = EntityHeblu.this.func_70638_az().field_70165_t - EntityHeblu.this.field_70165_t;
                  double d5 = EntityHeblu.this.func_70638_az().field_70161_v - EntityHeblu.this.field_70161_v;
                  EntityHeblu.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityHeblu.this.field_70761_aq = EntityHeblu.this.field_70177_z;
               }
            }
         }
      }
   }

   class AIMoveRandom extends EntityAIBase {
      public AIMoveRandom() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return !EntityHeblu.this.func_70605_aq().func_75640_a() && EntityHeblu.this.field_70146_Z.nextInt(5) == 0 && EntityHeblu.this.getFlyingState();
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityHeblu.this);
         byte flag = 1;
         double speed = 0.5;
         if (EntityHeblu.this.func_70638_az() != null) {
            if (EntityHeblu.this.func_70068_e(EntityHeblu.this.func_70638_az()) > 100.0) {
               blockpos = new BlockPos(EntityHeblu.this.func_70638_az());
               flag = 2;
               speed += 0.25;
            } else if (EntityHeblu.this.func_70068_e(EntityHeblu.this.func_70638_az()) < 36.0) {
               blockpos = new BlockPos(EntityHeblu.this.func_70638_az());
               flag = 3;
               speed += 0.25;
            }
         }

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityHeblu.this.field_70146_Z.nextInt(15) - 7, EntityHeblu.this.field_70146_Z.nextInt(11) - 5, EntityHeblu.this.field_70146_Z.nextInt(15) - 7
            );
            if (flag == 2) {
               blockpos1 = blockpos.func_177982_a(
                  EntityHeblu.this.field_70146_Z.nextInt(6) - 2, EntityHeblu.this.field_70146_Z.nextInt(7) - 2, EntityHeblu.this.field_70146_Z.nextInt(6) - 2
               );
            } else if (flag == 3) {
               blockpos1 = blockpos.func_177982_a(
                  EntityHeblu.this.field_70146_Z.nextInt(4) + 3, EntityHeblu.this.field_70146_Z.nextInt(5) + 4, EntityHeblu.this.field_70146_Z.nextInt(4) + 3
               );
            }

            if (EntityHeblu.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityHeblu.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, speed);
               if (EntityHeblu.this.func_70638_az() == null) {
                  EntityHeblu.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
