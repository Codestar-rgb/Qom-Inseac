package com.dhanantry.scapeandrunparasites.entity.monster.adapted;

import com.dhanantry.scapeandrunparasites.entity.EntityHitbox;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeNotGround;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityEmanaAdapted extends EntityPAdapted implements EntityCutomAttack, EntityCanShoot, EntityCanFly {
   private final EntityHitbox tendril_1;
   private final EntityHitbox tendril_2;
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityEmanaAdapted.class, DataSerializers.field_187191_a);
   private int count;

   public EntityEmanaAdapted(World worldIn) {
      super(worldIn);
      this.field_70765_h = new EntityEmanaAdapted.AIMoveControl(this);
      this.func_70105_a(1.3F, 2.9F);
      this.func_189654_d(true);
      this.field_70714_bg.func_85156_a(this.folow);
      this.borderOrb = -1;
      if (SRPConfigMobs.emanaMaxY != 256) {
         this.field_70714_bg.func_75776_a(3, new EntityAIFlightLimits(this, SRPConfigMobs.emanaMaxY, true));
      }

      this.adaptationCap = 0.95F;
      this.tendril_1 = new EntityHitbox(this, 0.2F, 1.1F, 0.1F, 0.9F, 2.6F, 1.25F);
      this.tendril_2 = new EntityHitbox(this, 3.0F, 1.1F, 0.1F, 0.9F, 2.6F, 1.25F);
      this.hitboxes = new EntityHitbox[]{this.tendril_1, this.tendril_2};
   }

   @Override
   public int getParasiteIDRegister() {
      return 55;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.adaptedFollow));
      this.field_70714_bg.func_75776_a(4, new EntityEmanaAdapted.AIChargeAttack());
      this.field_70714_bg.func_75776_a(6, new EntityEmanaAdapted.AIMoveRandom());
      this.field_70714_bg.func_75776_a(1, new EntityAIAttackProjectile(this, 60, 20, 2, true));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeNotGround(this, 2.5, 16.0, 0.04, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.EMANA_HEALTH + SRPAttributes.EMANA_A_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.EMANA_ARMOR + SRPAttributes.EMANA_A_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.EMANA_KD_RESISTANCE + SRPAttributes.EMANA_A_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.adaptedFollow);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.EMANA_A_MELLE);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K && this.srpTicks == 10) {
         if (this.field_70122_E) {
            this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
         }

         if ((
               this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a
                  || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a
            )
            && this.func_70638_az() != null) {
            this.field_70181_x = 0.5;
            return;
         }
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.func_189654_d(true);
   }

   @Override
   public void func_180430_e(float distance, float damageMultiplier) {
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      return this.func_70652_k(entityIn);
   }

   public float func_70047_e() {
      return 2.1F;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      if (!this.field_70170_p.field_72995_K) {
         if (!SRPConfigWorld.coloniesActivated && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else if (ParasiteEventWorld.numberofColonies(this.field_70170_p) < 1 && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else {
            ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
            ParasiteEventEntity.spawnNext(this, new EntityEmana(this.field_70170_p), true, false);
         }
      }
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.AEMANA_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.AEMANA_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.AEMANA_DEATH;
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance
         || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant
         || this.canChangeVariant) {
         switch (this.field_70146_Z.nextInt(1)) {
            case 0:
               this.setSkin(7);
         }
      }

      return floo;
   }

   public void func_70091_d(MoverType type, double x, double y, double z) {
      super.func_70091_d(type, x, y, z);
      this.func_145775_I();
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(VEX_FLAGS, (byte)0);
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

   public boolean isCharging() {
      return this.getVexFlag(1);
   }

   public void setCharging(boolean charging) {
      this.setVexFlag(1, charging);
   }

   public EntityFireball getProj(double accelX, double accelY, double accelZ) {
      float pit = 1.0F;
      if (this.count == 3) {
         pit = 1.5F;
      }

      this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0F, pit);
      if (this.count >= 3) {
         this.count = 0;
         return new EntityProjectileNade(this.field_70170_p, this, accelX, accelY, accelZ, 4, 100);
      } else {
         EntityProjectileSpineball ball = new EntityProjectileSpineball(this.field_70170_p, this, accelX, accelY, accelZ, SRPAttributes.EMANA_A_RANGED_DAMAGE);
         ball.setDurationAmplifier(SRPConfigMobs.emanaPoisonDuration * 2, SRPConfigMobs.emanaPoisonAmplifier + 1);
         ball.setGearDamage(SRPConfigMobs.emanaadaptedgeard);
         return ball;
      }
   }

   @Override
   public void playProjSound() {
      this.count++;
      if (this.count == 3) {
         this.field_70170_p.func_72960_a(this, (byte)100);
         float v = this.field_70146_Z.nextFloat() * 0.4F + 1.0F;
         this.func_184185_a(SRPSounds.ATTACKEMANA, 4.0F, v);
      } else {
         this.func_184185_a(SRPSounds.AEMANA_SHOOTINGPOST, 2.0F, 1.0F);
      }
   }

   class AIChargeAttack extends EntityAIBase {
      public AIChargeAttack() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return EntityEmanaAdapted.this.func_70638_az() != null
               && !EntityEmanaAdapted.this.func_70605_aq().func_75640_a()
               && EntityEmanaAdapted.this.field_70146_Z.nextInt(7) == 0
            ? EntityEmanaAdapted.this.func_70068_e(EntityEmanaAdapted.this.func_70638_az()) > 4.0
            : false;
      }

      public boolean func_75253_b() {
         return EntityEmanaAdapted.this.func_70605_aq().func_75640_a()
            && EntityEmanaAdapted.this.isCharging()
            && EntityEmanaAdapted.this.func_70638_az() != null
            && EntityEmanaAdapted.this.func_70638_az().func_70089_S();
      }

      public void func_75249_e() {
         EntityLivingBase entitylivingbase = EntityEmanaAdapted.this.func_70638_az();
         Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
         EntityEmanaAdapted.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.5);
         EntityEmanaAdapted.this.setCharging(true);
      }

      public void func_75251_c() {
         EntityEmanaAdapted.this.setCharging(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = EntityEmanaAdapted.this.func_70638_az();
         if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
            if (EntityEmanaAdapted.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
               EntityEmanaAdapted.this.func_70652_k(entitylivingbase);
               EntityEmanaAdapted.this.setCharging(false);
            } else {
               double d0 = EntityEmanaAdapted.this.func_70068_e(entitylivingbase);
               if (d0 < 9.0) {
                  Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                  EntityEmanaAdapted.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.5);
               }
            }
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityEmanaAdapted vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityEmanaAdapted.this.field_70165_t;
            double d1 = this.field_75647_c - EntityEmanaAdapted.this.field_70163_u;
            double d2 = this.field_75644_d - EntityEmanaAdapted.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityEmanaAdapted.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityEmanaAdapted.this.field_70159_w *= 0.5;
               EntityEmanaAdapted.this.field_70181_x *= 0.5;
               EntityEmanaAdapted.this.field_70179_y *= 0.5;
            } else {
               EntityEmanaAdapted.this.field_70159_w = EntityEmanaAdapted.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityEmanaAdapted.this.field_70181_x = EntityEmanaAdapted.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityEmanaAdapted.this.field_70179_y = EntityEmanaAdapted.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityEmanaAdapted.this.func_70638_az() == null) {
                  EntityEmanaAdapted.this.field_70177_z = -(
                        (float)MathHelper.func_181159_b(EntityEmanaAdapted.this.field_70159_w, EntityEmanaAdapted.this.field_70179_y)
                     )
                     * (180.0F / (float)Math.PI);
                  EntityEmanaAdapted.this.field_70761_aq = EntityEmanaAdapted.this.field_70177_z;
               } else {
                  double d4 = EntityEmanaAdapted.this.func_70638_az().field_70165_t - EntityEmanaAdapted.this.field_70165_t;
                  double d5 = EntityEmanaAdapted.this.func_70638_az().field_70161_v - EntityEmanaAdapted.this.field_70161_v;
                  EntityEmanaAdapted.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityEmanaAdapted.this.field_70761_aq = EntityEmanaAdapted.this.field_70177_z;
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
         return !EntityEmanaAdapted.this.func_70605_aq().func_75640_a() && EntityEmanaAdapted.this.field_70146_Z.nextInt(7) == 0;
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityEmanaAdapted.this);

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityEmanaAdapted.this.field_70146_Z.nextInt(15) - 7,
               EntityEmanaAdapted.this.field_70146_Z.nextInt(11) - 5,
               EntityEmanaAdapted.this.field_70146_Z.nextInt(15) - 7
            );
            if (EntityEmanaAdapted.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityEmanaAdapted.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 0.25);
               if (EntityEmanaAdapted.this.func_70638_az() == null) {
                  EntityEmanaAdapted.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
