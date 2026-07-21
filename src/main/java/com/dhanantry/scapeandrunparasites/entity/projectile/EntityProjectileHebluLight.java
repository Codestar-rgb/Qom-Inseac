package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityProjectileHebluLight extends Entity {
   private EntityLivingBase owner;
   private EntityLivingBase target;
   private static long lastSpeedSoundTick = -100L;
   private static long lastImpactSoundTick = -100L;
   public static boolean DEBUG_IGNORE_CREATIVE_AND_SPECTATOR_PLAYERS = true;
   private static final int PARRY_IMMUNITY_TICKS = 25;
   private static final Map<UUID, Long> PLAYER_PARRY_IMMUNITY = new HashMap<>();
   private static final DataParameter<Boolean> SYNC_PARRIED = EntityDataManager.func_187226_a(EntityProjectileHebluLight.class, DataSerializers.field_187198_h);
   private static final DataParameter<Float> SYNC_DANGER = EntityDataManager.func_187226_a(EntityProjectileHebluLight.class, DataSerializers.field_187193_c);
   private int age;
   private int parryAge;
   private boolean parried;
   private double arcX;
   private double arcY;
   private double arcZ;
   private double stopHomingDistance;

   public EntityProjectileHebluLight(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.65F, 0.65F);
      this.field_70145_X = false;
      this.field_70158_ak = true;
   }

   public EntityProjectileHebluLight(World worldIn, EntityLivingBase ownerIn, EntityLivingBase targetIn, double x, double y, double z) {
      this(worldIn, ownerIn, targetIn, x, y, z, false);
   }

   public EntityProjectileHebluLight(World worldIn, EntityLivingBase ownerIn, EntityLivingBase targetIn, double x, double y, double z, boolean forceTarget) {
      super(worldIn);
      this.owner = ownerIn;
      this.target = targetIn;
      if (!forceTarget && this.target instanceof EntityPlayer && shouldIgnorePlayer((EntityPlayer)this.target)) {
         this.target = null;
      }

      if (this.target == null) {
         this.target = this.findFallbackPlayerTarget(x, y, z);
      }

      this.func_70105_a(0.65F, 0.65F);
      this.func_70107_b(x, y, z);
      Vec3d toTarget;
      if (this.target != null) {
         toTarget = new Vec3d(this.target.field_70165_t - x, this.target.field_70163_u + this.target.func_70047_e() * 0.5 - y, this.target.field_70161_v - z);
         if (toTarget.func_72433_c() < 1.0E-4) {
            toTarget = new Vec3d(0.0, 0.0, 1.0);
         }

         toTarget = toTarget.func_72432_b();
      } else {
         toTarget = new Vec3d(this.field_70146_Z.nextDouble() - 0.5, this.field_70146_Z.nextDouble() - 0.2, this.field_70146_Z.nextDouble() - 0.5)
            .func_72432_b();
      }

      Vec3d side = toTarget.func_72431_c(new Vec3d(0.0, 1.0, 0.0));
      if (side.func_72433_c() < 1.0E-4) {
         side = new Vec3d(1.0, 0.0, 0.0);
      }

      side = side.func_72432_b();
      Vec3d up = side.func_72431_c(toTarget).func_72432_b();
      double angle = this.field_70146_Z.nextDouble() * Math.PI * 2.0;
      double sideAmount = Math.cos(angle);
      double upAmount = Math.sin(angle);
      Vec3d outward = side.func_186678_a(sideAmount).func_178787_e(up.func_186678_a(upAmount)).func_178787_e(toTarget.func_186678_a(-0.25));
      if (outward.func_72433_c() < 1.0E-4) {
         outward = side;
      }

      outward = outward.func_72432_b();
      this.arcX = outward.field_72450_a;
      this.arcY = outward.field_72448_b;
      this.arcZ = outward.field_72449_c;
      double startSpeed = 1.65 + this.field_70146_Z.nextDouble() * 1.15;
      this.field_70159_w = outward.field_72450_a * startSpeed;
      this.field_70181_x = outward.field_72448_b * startSpeed;
      this.field_70179_y = outward.field_72449_c * startSpeed;
      this.stopHomingDistance = 5.0 + this.field_70146_Z.nextDouble() * 5.0;
   }

   protected void func_70088_a() {
      this.field_70180_af.func_187214_a(SYNC_PARRIED, false);
      this.field_70180_af.func_187214_a(SYNC_DANGER, 0.0F);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if (this.field_70170_p.field_72995_K || this.owner != null && !this.owner.field_70128_L) {
         this.age++;
         this.updateSyncedRenderState();
         if (this.age > 95) {
            this.pop();
         } else {
            if (this.parried) {
               this.updateParriedMotion();
            } else {
               this.updateLightMotion();
            }

            Vec3d from = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3d to = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            RayTraceResult blockHit = this.field_70170_p.func_147447_a(from, to, false, true, false);
            if (blockHit != null) {
               if (!this.field_70170_p.field_72995_K) {
                  this.pop();
               }
            } else {
               this.func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
               if (!this.field_70170_p.field_72995_K) {
                  this.checkEntityImpact();
               }

               this.spawnClientParticles();
            }
         }
      } else {
         this.func_70106_y();
      }
   }

   private void updateLightMotion() {
      if (this.age < 5) {
         this.field_70159_w *= 0.995;
         this.field_70181_x *= 0.995;
         this.field_70179_y *= 0.995;
      } else if (this.age < 10) {
         this.field_70159_w *= 0.88;
         this.field_70181_x *= 0.88;
         this.field_70179_y *= 0.88;
      } else {
         if (this.age == 10 && !this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72960_a(this, (byte)4);
         }

         if (this.target != null && !this.target.field_70128_L) {
            double var10004 = this.target.field_70131_O;
            Vec3d targetPos = new Vec3d(this.target.field_70165_t, this.target.func_174813_aQ().field_72338_b + var10004 * 0.55, this.target.field_70161_v);
            Vec3d toTarget = targetPos.func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            double dist = toTarget.func_72433_c();
            if (dist <= this.stopHomingDistance) {
               this.target = null;
               this.field_70159_w *= 1.12;
               this.field_70181_x *= 1.12;
               this.field_70179_y *= 1.12;
               this.limitSpeed(2.65);
            } else {
               int homingEnd = 54;
               if (this.age >= homingEnd) {
                  this.target = null;
                  this.field_70159_w *= 1.04;
                  this.field_70181_x *= 1.04;
                  this.field_70179_y *= 1.04;
                  this.limitSpeed(2.45);
               } else if (!(dist < 1.0E-4)) {
                  double progress = MathHelper.func_151237_a((this.age - 10) / 44.0, 0.0, 1.0);
                  Vec3d desired = toTarget.func_72432_b();
                  double arcStrength = (1.0 - progress) * (1.0 - progress) * 1.05;
                  desired = desired.func_72441_c(this.arcX * arcStrength, this.arcY * arcStrength, this.arcZ * arcStrength).func_72432_b();
                  double targetSpeed = 0.95 + progress * 2.15;
                  double turnStrength = 0.25 + progress * 0.17;
                  this.field_70159_w = this.field_70159_w + (desired.field_72450_a * targetSpeed - this.field_70159_w) * turnStrength;
                  this.field_70181_x = this.field_70181_x + (desired.field_72448_b * targetSpeed - this.field_70181_x) * turnStrength;
                  this.field_70179_y = this.field_70179_y + (desired.field_72449_c * targetSpeed - this.field_70179_y) * turnStrength;
                  this.limitSpeed(targetSpeed + 0.55);
               }
            }
         } else {
            this.field_70159_w *= 0.99;
            this.field_70181_x *= 0.99;
            this.field_70179_y *= 0.99;
         }
      }
   }

   private void updateParriedMotion() {
      this.parryAge++;
      if (this.target == null || this.target.field_70128_L) {
         this.field_70159_w *= 0.985;
         this.field_70181_x *= 0.985;
         this.field_70179_y *= 0.985;
         if (this.parryAge > 35) {
            this.pop();
         }
      } else if (this.parryAge < 13) {
         this.field_70159_w *= 0.975;
         this.field_70181_x *= 0.975;
         this.field_70179_y *= 0.975;
      } else {
         double var10004 = this.target.field_70131_O;
         Vec3d targetPos = new Vec3d(this.target.field_70165_t, this.target.func_174813_aQ().field_72338_b + var10004 * 0.55, this.target.field_70161_v);
         Vec3d toTarget = targetPos.func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         if (!(toTarget.func_72433_c() < 1.0E-4)) {
            Vec3d desired = toTarget.func_72432_b();
            double targetSpeed = 2.25;
            double turnStrength = 0.28;
            this.field_70159_w = this.field_70159_w + (desired.field_72450_a * targetSpeed - this.field_70159_w) * turnStrength;
            this.field_70181_x = this.field_70181_x + (desired.field_72448_b * targetSpeed - this.field_70181_x) * turnStrength;
            this.field_70179_y = this.field_70179_y + (desired.field_72449_c * targetSpeed - this.field_70179_y) * turnStrength;
            this.limitSpeed(2.65);
         }
      }
   }

   private void limitSpeed(double max) {
      double speed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
      if (speed > max && speed > 1.0E-4) {
         double scale = max / speed;
         this.field_70159_w *= scale;
         this.field_70181_x *= scale;
         this.field_70179_y *= scale;
      }
   }

   private static void giveParryImmunity(EntityLivingBase entity, World world) {
      if (entity instanceof EntityPlayer) {
         PLAYER_PARRY_IMMUNITY.put(entity.func_110124_au(), world.func_82737_E() + 25L);
      }
   }

   private static boolean hasParryImmunity(EntityLivingBase entity, World world) {
      if (!(entity instanceof EntityPlayer)) {
         return false;
      } else {
         Long endTime = PLAYER_PARRY_IMMUNITY.get(entity.func_110124_au());
         if (endTime == null) {
            return false;
         } else if (world.func_82737_E() > endTime) {
            PLAYER_PARRY_IMMUNITY.remove(entity.func_110124_au());
            return false;
         } else {
            return true;
         }
      }
   }

   private void checkEntityImpact() {
      AxisAlignedBB box = this.func_174813_aQ().func_186662_g(0.45);

      for (Entity entity : this.field_70170_p.func_72839_b(this, box)) {
         if ((entity != this.owner || this.parried) && !(entity instanceof EntityProjectileHebluLight) && entity.func_70067_L()) {
            if (!(entity instanceof EntityLivingBase)) {
               this.pop();
               return;
            }

            EntityLivingBase living = (EntityLivingBase)entity;
            if (this.parried || living != this.owner) {
               if (!this.parried && living instanceof EntityPlayer) {
                  EntityPlayer player = (EntityPlayer)living;
                  if (!shouldIgnorePlayer(player) && !hasParryImmunity(living, this.field_70170_p)) {
                     living.func_70097_a(new EntityProjectileHebluLight.DamageSourceHebluLight(this, this.owner), 10.0F);
                     living.func_70690_d(new PotionEffect(SRPPotions.DISTORTED_ENLIGHTENMENT_E, 400, 0, false, true));
                     this.pop();
                     return;
                  }
               } else {
                  if (!this.parried) {
                     living.func_70097_a(new EntityProjectileHebluLight.DamageSourceHebluLightNeutral(), 7.0F);
                     this.pop();
                     return;
                  }

                  if (living == this.target) {
                     living.func_70097_a(new EntityProjectileHebluLight.DamageSourceHebluLightNeutral(), 24.0F);
                     this.pop();
                     return;
                  }
               }
            }
         }
      }
   }

   private EntityLivingBase findParryTarget(Entity attacker) {
      AxisAlignedBB box = this.func_174813_aQ().func_72314_b(32.0, 18.0, 32.0);
      List<EntityLivingBase> list = this.field_70170_p.func_72872_a(EntityLivingBase.class, box);
      EntityLivingBase best = null;
      double bestDist = Double.MAX_VALUE;

      for (EntityLivingBase living : list) {
         if (living != null && !living.field_70128_L && living != this.owner && living != attacker && !(living instanceof EntityPlayer)) {
            String className = living.getClass().getName();
            if (className.startsWith("com.dhanantry.scapeandrunparasites.entity.monster.")) {
               double dist = living.func_70068_e(this);
               if (dist < bestDist) {
                  bestDist = dist;
                  best = living;
               }
            }
         }
      }

      return best;
   }

   private EntityLivingBase getThrower() {
      return this.owner;
   }

   public boolean func_70097_a(DamageSource source, float amount) {
      if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         Entity trueSource = source.func_76346_g();
         if (trueSource instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase)trueSource;
            if (!isSentientParryWeapon(attacker)) {
               return false;
            } else {
               this.parryNearbyLights(attacker, this);
               return true;
            }
         } else {
            this.pop();
            return true;
         }
      } else {
         return true;
      }
   }

   private void parryNearbyLights(EntityLivingBase attacker, EntityProjectileHebluLight mainLight) {
      double radiusXZ = 6.0;
      double radiusY = 4.0;
      AxisAlignedBB box = mainLight.func_174813_aQ().func_72314_b(radiusXZ, radiusY, radiusXZ);
      List<EntityProjectileHebluLight> nearby = this.field_70170_p.func_72872_a(EntityProjectileHebluLight.class, box);
      boolean playedEffects = false;

      for (EntityProjectileHebluLight light : nearby) {
         if (light != null && !light.field_70128_L && !light.parried) {
            light.parryFrom(attacker, !playedEffects);
            playedEffects = true;
         }
      }

      if (!playedEffects && mainLight != null && !mainLight.field_70128_L && !mainLight.parried) {
         mainLight.parryFrom(attacker, true);
      }
   }

   private void parryFrom(EntityLivingBase attacker, boolean playEffects) {
      Vec3d look = attacker.func_70040_Z();
      if (look == null || look.func_72433_c() < 1.0E-4) {
         look = new Vec3d(this.field_70165_t - attacker.field_70165_t, this.field_70163_u - attacker.field_70163_u, this.field_70161_v - attacker.field_70161_v)
            .func_72432_b();
      }

      Vec3d randomSpread = new Vec3d(
         this.field_70146_Z.nextDouble() - 0.5, this.field_70146_Z.nextDouble() * 0.75 - 0.15, this.field_70146_Z.nextDouble() - 0.5
      );
      if (randomSpread.func_72433_c() < 1.0E-4) {
         randomSpread = new Vec3d(0.0, 0.3, 1.0);
      }

      randomSpread = randomSpread.func_72432_b();
      Vec3d bounce = look.func_186678_a(0.75).func_178787_e(randomSpread.func_186678_a(1.25));
      if (bounce.func_72433_c() < 1.0E-4) {
         bounce = look;
      }

      bounce = bounce.func_72432_b();
      this.parried = true;
      this.field_70180_af.func_187227_b(SYNC_PARRIED, true);
      this.field_70180_af.func_187227_b(SYNC_DANGER, 0.0F);
      this.parryAge = 0;
      this.age = 20;
      this.target = this.findParryTarget(attacker);
      this.field_70159_w = bounce.field_72450_a * (1.85 + this.field_70146_Z.nextDouble() * 0.65);
      this.field_70181_x = bounce.field_72448_b * (1.85 + this.field_70146_Z.nextDouble() * 0.65) + 0.12;
      this.field_70179_y = bounce.field_72449_c * (1.85 + this.field_70146_Z.nextDouble() * 0.65);
      giveParryImmunity(attacker, this.field_70170_p);
      if (playEffects) {
         this.func_184185_a(SRPSounds.HEBLU_PARRY, 1.2F, 0.95F + this.field_70146_Z.nextFloat() * 0.12F);
         if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p)
               .func_180505_a(
                  EnumParticleTypes.SWEEP_ATTACK,
                  true,
                  attacker.field_70165_t + look.field_72450_a * 1.2,
                  attacker.field_70163_u + attacker.func_70047_e() * 0.65,
                  attacker.field_70161_v + look.field_72449_c * 1.2,
                  1,
                  0.15,
                  0.15,
                  0.15,
                  0.0,
                  new int[0]
               );
            ((WorldServer)this.field_70170_p)
               .func_180505_a(
                  EnumParticleTypes.SWEEP_ATTACK,
                  true,
                  attacker.field_70165_t - look.field_72449_c * 0.75,
                  attacker.field_70163_u + attacker.func_70047_e() * 0.7,
                  attacker.field_70161_v + look.field_72450_a * 0.75,
                  1,
                  0.1,
                  0.1,
                  0.1,
                  0.0,
                  new int[0]
               );

            for (int i = 0; i < 12; i++) {
               ((WorldServer)this.field_70170_p)
                  .func_180505_a(
                     EnumParticleTypes.FIREWORKS_SPARK,
                     true,
                     attacker.field_70165_t + look.field_72450_a * 1.1,
                     attacker.field_70163_u + attacker.func_70047_e() * 0.65,
                     attacker.field_70161_v + look.field_72449_c * 1.1,
                     1,
                     0.45,
                     0.28,
                     0.45,
                     0.09,
                     new int[0]
                  );
            }
         }
      }
   }

   private void pop() {
      if (!this.field_70170_p.field_72995_K) {
         this.field_70170_p.func_72960_a(this, (byte)3);
         this.func_70106_y();
      }
   }

   public void func_70103_a(byte id) {
      if (id == 3) {
         long time = this.field_70170_p.func_82737_E();
         if (time - lastImpactSoundTick > 2L) {
            lastImpactSoundTick = time;
            this.field_70170_p
               .func_184134_a(
                  this.field_70165_t,
                  this.field_70163_u,
                  this.field_70161_v,
                  SRPSounds.HEBLU_LIGHT_IMPACT,
                  this.func_184176_by(),
                  0.48F,
                  0.92F + this.field_70146_Z.nextFloat() * 0.16F,
                  false
               );
         }

         for (int i = 0; i < 8; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.FIREWORKS_SPARK,
                  this.field_70165_t,
                  this.field_70163_u,
                  this.field_70161_v,
                  (this.field_70146_Z.nextDouble() - 0.5) * 0.22,
                  (this.field_70146_Z.nextDouble() - 0.5) * 0.22,
                  (this.field_70146_Z.nextDouble() - 0.5) * 0.22,
                  new int[0]
               );
         }

         this.field_70170_p
            .func_184134_a(
               this.field_70165_t,
               this.field_70163_u,
               this.field_70161_v,
               SoundEvents.field_187619_bk,
               this.func_184176_by(),
               0.22F,
               1.8F + this.field_70146_Z.nextFloat() * 0.25F,
               false
            );
      } else if (id == 4) {
         for (int i = 0; i < 10; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.FIREWORKS_SPARK,
                  this.field_70165_t,
                  this.field_70163_u,
                  this.field_70161_v,
                  (this.field_70146_Z.nextDouble() - 0.5) * 0.35,
                  (this.field_70146_Z.nextDouble() - 0.5) * 0.35,
                  (this.field_70146_Z.nextDouble() - 0.5) * 0.35,
                  new int[0]
               );
         }

         for (int i = 0; i < 4; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.END_ROD,
                  this.field_70165_t,
                  this.field_70163_u,
                  this.field_70161_v,
                  -this.field_70159_w * 0.12 + (this.field_70146_Z.nextDouble() - 0.5) * 0.08,
                  -this.field_70181_x * 0.12 + (this.field_70146_Z.nextDouble() - 0.5) * 0.08,
                  -this.field_70179_y * 0.12 + (this.field_70146_Z.nextDouble() - 0.5) * 0.08,
                  new int[0]
               );
         }

         this.field_70170_p
            .func_184134_a(
               this.field_70165_t,
               this.field_70163_u,
               this.field_70161_v,
               SoundEvents.field_187619_bk,
               this.func_184176_by(),
               0.18F,
               2.0F + this.field_70146_Z.nextFloat() * 0.2F,
               false
            );
      } else {
         super.func_70103_a(id);
      }
   }

   private void spawnClientParticles() {
      if (this.field_70170_p.field_72995_K) {
         if (this.field_70173_aa % 3 == 0) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.END_ROD,
                  this.field_70165_t - this.field_70159_w * 0.25,
                  this.field_70163_u - this.field_70181_x * 0.25,
                  this.field_70161_v - this.field_70179_y * 0.25,
                  -this.field_70159_w * 0.015,
                  -this.field_70181_x * 0.015,
                  -this.field_70179_y * 0.015,
                  new int[0]
               );
         }
      }
   }

   public boolean isParriedLight() {
      return (Boolean)this.field_70180_af.func_187225_a(SYNC_PARRIED);
   }

   public float getPlayerDangerColorAmount() {
      return (Float)this.field_70180_af.func_187225_a(SYNC_DANGER);
   }

   private static boolean shouldIgnorePlayer(EntityPlayer player) {
      return !DEBUG_IGNORE_CREATIVE_AND_SPECTATOR_PLAYERS ? false : player.func_184812_l_() || player.func_175149_v();
   }

   protected void func_70037_a(NBTTagCompound compound) {
      this.age = compound.func_74762_e("Age");
      this.parryAge = compound.func_74762_e("ParryAge");
      this.parried = compound.func_74767_n("Parried");
      this.arcX = compound.func_74769_h("ArcX");
      this.arcY = compound.func_74769_h("ArcY");
      this.arcZ = compound.func_74769_h("ArcZ");
      this.stopHomingDistance = compound.func_74769_h("StopHomingDistance");
   }

   protected void func_70014_b(NBTTagCompound compound) {
      compound.func_74768_a("Age", this.age);
      compound.func_74768_a("ParryAge", this.parryAge);
      compound.func_74757_a("Parried", this.parried);
      compound.func_74780_a("ArcX", this.arcX);
      compound.func_74780_a("ArcY", this.arcY);
      compound.func_74780_a("ArcZ", this.arcZ);
      compound.func_74780_a("StopHomingDistance", this.stopHomingDistance);
   }

   public boolean func_70067_L() {
      return true;
   }

   public boolean func_70075_an() {
      return true;
   }

   public float func_70111_Y() {
      return 1.15F;
   }

   private static boolean isSentientParryWeapon(EntityLivingBase attacker) {
      if (!(attacker instanceof EntityPlayer)) {
         return false;
      } else {
         EntityPlayer player = (EntityPlayer)attacker;
         return isSentientParryWeaponStack(player.func_184614_ca()) || isSentientParryWeaponStack(player.func_184592_cb());
      }
   }

   private static boolean isSentientParryWeaponStack(ItemStack stack) {
      if (stack != null && !stack.func_190926_b() && stack.func_77973_b() != null) {
         ResourceLocation id = stack.func_77973_b().getRegistryName();
         if (id != null && "srparasites".equals(id.func_110624_b())) {
            String path = id.func_110623_a();
            return "weapon_scythe_sentient".equals(path)
               || "weapon_axe_sentient".equals(path)
               || "weapon_sword_sentient".equals(path)
               || "weapon_cleaver_sentient".equals(path)
               || "weapon_maul_sentient".equals(path)
               || "weapon_lance_sentient".equals(path);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void updateSyncedRenderState() {
      if (!this.field_70170_p.field_72995_K) {
         this.field_70180_af.func_187227_b(SYNC_PARRIED, this.parried);
         if (!this.parried && this.target != null && !this.target.field_70128_L && this.target instanceof EntityPlayer) {
            double dist = this.func_70032_d(this.target);
            float danger;
            if (dist >= 18.0) {
               danger = 0.0F;
            } else if (dist <= 5.0) {
               danger = 1.0F;
            } else {
               danger = (float)((18.0 - dist) / 13.0);
            }

            this.field_70180_af.func_187227_b(SYNC_DANGER, danger);
         } else {
            this.field_70180_af.func_187227_b(SYNC_DANGER, 0.0F);
         }
      }
   }

   private EntityLivingBase findFallbackPlayerTarget(double x, double y, double z) {
      EntityPlayer best = null;
      double bestDist = Double.MAX_VALUE;

      for (EntityPlayer player : this.field_70170_p.field_73010_i) {
         if (player != null && !player.field_70128_L && !player.func_175149_v() && !shouldIgnorePlayer(player)) {
            double dist = player.func_70092_e(x, y, z);
            if (dist < bestDist) {
               bestDist = dist;
               best = player;
            }
         }
      }

      return best;
   }

   public static class DamageSourceHebluLight extends DamageSource {
      private final Entity projectile;
      private final Entity owner;

      public DamageSourceHebluLight(Entity projectile, Entity owner) {
         super("hebluLight");
         this.projectile = projectile;
         this.owner = owner;
         this.func_82726_p();
      }

      public Entity func_76364_f() {
         return this.projectile;
      }

      public Entity func_76346_g() {
         return this.owner;
      }
   }

   public static class DamageSourceHebluLightNeutral extends DamageSource {
      public DamageSourceHebluLightNeutral() {
         super("hebluLightNeutral");
         this.func_82726_p();
      }
   }
}
