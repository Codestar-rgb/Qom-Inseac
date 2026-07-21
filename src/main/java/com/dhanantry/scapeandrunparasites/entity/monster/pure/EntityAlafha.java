package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeNotGround;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAlafhaBall;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Random;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityAlafha extends EntityPPure implements EntityBodyParts, EntityCutomAttack, EntityCanShoot, EntityCanFly {
   private EntityBody head;
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityAlafha.class, DataSerializers.field_187191_a);

   public EntityAlafha(World worldIn) {
      super(worldIn);
      this.field_70765_h = new EntityAlafha.AIMoveControl(this);
      this.func_70105_a(1.9F, 2.6F);
      this.func_189654_d(true);
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70158_ak = true;
      this.borderOrb = -1;
      this.totalP = SRPConfigMobs.alafhaTotalActiveMobs;
      this.mobID = new int[this.totalP + SRPConfigMobs.alafhaLimit];
      this.mobPT = new int[this.totalP + SRPConfigMobs.alafhaLimit];

      for (int i = 0; i < this.mobID.length; i++) {
         this.mobID[i] = -777;
      }

      if (SRPConfigMobs.alafhaMaxY != 256) {
         this.field_70714_bg.func_75776_a(3, new EntityAIFlightLimits(this, SRPConfigMobs.alafhaMaxY, true));
      }

      this.head = new EntityBody(this, 1.2F, 1.2F, 1.0F, 3.0F, 0.0F, -1, 1, false, 0.2F);
      this.adaptationCap = 0.95F;
   }

   @Override
   public int getParasiteIDRegister() {
      return 9;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.pureFollow));
      this.field_70714_bg.func_75776_a(6, new EntityAlafha.AIMoveRandom());
      this.field_70714_bg
         .func_75776_a(
            5, new EntityAlafha.EntityAIAirVomitSummon(this, SRPConfigMobs.alafhaSummoningCooldown, SRPConfigMobs.alafhaLimit, SRPConfigMobs.alafhaMobList)
         );
      this.field_70714_bg.func_75776_a(1, new EntityAIAttackProjectile(this, 20, 10, 4));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeNotGround(this, 4.5, 16.0, 0.045, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ALAFHA_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ALAFHA_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.ALAFHA_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.ALAFHA_MELLE);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70122_E && !this.field_70170_p.field_72995_K) {
         this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
      }

      if ((
            this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a
               || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a
         )
         && this.func_70638_az() != null) {
         this.field_70181_x = 0.5;
      }

      this.head.func_70071_h_();
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.func_189654_d(true);
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      return this.func_70097_a(source, amount * 3.0F);
   }

   @Override
   public void setBodyPartDead(int id) {
   }

   @Override
   public void func_180430_e(float distance, float damageMultiplier) {
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      return this.func_70652_k(entityIn);
   }

   public float func_70047_e() {
      return 1.6F;
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      super.func_70106_y();
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.ALAFHA_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.ALAFHA_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.ALAFHA_DEATH;
   }

   protected float func_70599_aP() {
      return 2.0F;
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

   public EntityFireball getProj(double accelX, double accelY, double accelZ) {
      this.func_184185_a(SRPSounds.ALAFHA_SHOOTING, 2.0F, 1.0F);
      return new EntityProjectileAlafhaBall(this.field_70170_p, this, accelX, accelY, accelZ);
   }

   @Override
   public void playProjSound() {
      this.func_184185_a(SRPSounds.ALAFHA_SHOOTINGPOST, 2.0F, 1.0F);
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityAlafha vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityAlafha.this.field_70165_t;
            double d1 = this.field_75647_c - EntityAlafha.this.field_70163_u;
            double d2 = this.field_75644_d - EntityAlafha.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityAlafha.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityAlafha.this.field_70159_w *= 0.5;
               EntityAlafha.this.field_70181_x *= 0.5;
               EntityAlafha.this.field_70179_y *= 0.5;
            } else {
               EntityAlafha.this.field_70159_w = EntityAlafha.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityAlafha.this.field_70181_x = EntityAlafha.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityAlafha.this.field_70179_y = EntityAlafha.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityAlafha.this.func_70638_az() == null) {
                  EntityAlafha.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityAlafha.this.field_70159_w, EntityAlafha.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntityAlafha.this.field_70761_aq = EntityAlafha.this.field_70177_z;
               } else {
                  double d4 = EntityAlafha.this.func_70638_az().field_70165_t - EntityAlafha.this.field_70165_t;
                  double d5 = EntityAlafha.this.func_70638_az().field_70161_v - EntityAlafha.this.field_70161_v;
                  EntityAlafha.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityAlafha.this.field_70761_aq = EntityAlafha.this.field_70177_z;
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
         EntityMoveHelper entitymovehelper = EntityAlafha.this.func_70605_aq();
         if (!entitymovehelper.func_75640_a()) {
            return true;
         } else {
            double d0 = entitymovehelper.func_179917_d() - EntityAlafha.this.field_70165_t;
            double d1 = entitymovehelper.func_179919_e() - EntityAlafha.this.field_70163_u;
            double d2 = entitymovehelper.func_179918_f() - EntityAlafha.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0 || d3 > 3600.0;
         }
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         if (EntityAlafha.this.func_70638_az() != null) {
            BlockPos blockpos = new BlockPos(EntityAlafha.this);
            byte flag = 1;
            double speed = 0.11;
            if (EntityAlafha.this.func_70068_e(EntityAlafha.this.func_70638_az()) > 400.0) {
               blockpos = new BlockPos(EntityAlafha.this.func_70638_az());
               flag = 2;
               speed += 0.11;
            } else if (EntityAlafha.this.func_70068_e(EntityAlafha.this.func_70638_az()) < 100.0) {
               blockpos = new BlockPos(EntityAlafha.this.func_70638_az());
               flag = 3;
               speed += 0.11;
            }

            for (int i = 0; i < 3; i++) {
               BlockPos blockpos1 = blockpos.func_177982_a(
                  EntityAlafha.this.field_70146_Z.nextInt(15) - 7,
                  EntityAlafha.this.field_70146_Z.nextInt(9) - 5,
                  EntityAlafha.this.field_70146_Z.nextInt(15) - 7
               );
               if (flag == 2) {
                  blockpos1 = blockpos.func_177982_a(
                     EntityAlafha.this.field_70146_Z.nextInt(6) - 2,
                     EntityAlafha.this.field_70146_Z.nextInt(7) - 2,
                     EntityAlafha.this.field_70146_Z.nextInt(6) - 2
                  );
               } else if (flag == 3) {
                  blockpos1 = blockpos.func_177982_a(
                     EntityAlafha.this.field_70146_Z.nextInt(4) + 3,
                     EntityAlafha.this.field_70146_Z.nextInt(5) + 4,
                     EntityAlafha.this.field_70146_Z.nextInt(4) + 3
                  );
               }

               if (EntityAlafha.this.field_70170_p.func_175623_d(blockpos1)) {
                  EntityAlafha.this.field_70765_h
                     .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, speed);
                  if (EntityAlafha.this.func_70638_az() == null) {
                     EntityAlafha.this.func_70671_ap()
                        .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
                  }
                  break;
               }
            }
         } else {
            Random random = EntityAlafha.this.func_70681_au();
            double d0 = EntityAlafha.this.field_70165_t + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = EntityAlafha.this.field_70163_u + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = EntityAlafha.this.field_70161_v + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            EntityAlafha.this.func_70605_aq().func_75642_a(d0, d1, d2, 0.5);
         }
      }
   }

   static class EntityAIAirVomitSummon extends EntityAIBase {
      private final EntityParasiteBase parentEntity;
      private int limit = 0;
      private int sCooldown;
      private int sLimit;
      private String[] sMobs;
      private int attackTimer = 0;
      private int attacking = 0;
      private double targetX;
      private double targetY;
      private double targetZ;

      public EntityAIAirVomitSummon(EntityParasiteBase summoner, int cooldown, int limit, String[] mobList) {
         this.parentEntity = summoner;
         this.sCooldown = cooldown;
         this.sLimit = limit;
         this.sMobs = mobList;
      }

      public boolean func_75250_a() {
         if (this.attacking >= 1) {
            return true;
         } else {
            return this.parentEntity.func_70638_az() != null ? this.parentEntity.func_70638_az().field_70122_E : false;
         }
      }

      public void func_75249_e() {
      }

      public void func_75251_c() {
      }

      public void func_75246_d() {
         if (this.attacking >= 1) {
            this.attacking++;
            this.parentEntity.setParasiteStatus(10);
            this.parentEntity.func_70661_as().func_75499_g();
            if (this.attacking == 2) {
            }

            if (this.attacking % 20 == 0 && this.attacking >= 40) {
               EntityCanSummon parent = (EntityCanSummon)this.parentEntity;
               parent.checkID();
               if (parent.getActualParasites() < parent.getTotalParasites()
                  && this.limit < this.sLimit
                  && ParasiteEventEntity.spawnBiomassFromProjectile(this.parentEntity, this.sMobs, this.parentEntity.func_70638_az())) {
                  this.limit++;
                  this.parentEntity.particleStatus((byte)8);
               }
            }

            if (this.attacking >= 80 || this.limit >= this.sLimit) {
               this.attacking = 0;
               this.attackTimer = 0;
               this.limit = 0;
               this.parentEntity.setParasiteStatus(2);
            }
         } else if (this.parentEntity.func_70638_az() == null) {
            if (this.attackTimer > 0) {
               this.attackTimer--;
            }
         } else if (this.parentEntity.func_70638_az().field_70128_L) {
            if (this.attackTimer > 0) {
               this.attackTimer--;
            }
         } else {
            EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
            if (this.parentEntity.func_70685_l(entitylivingbase) && this.parentEntity.func_70068_e(entitylivingbase) < 256.0 && entitylivingbase.field_70122_E) {
               this.attackTimer++;
               if (this.attackTimer >= this.sCooldown && this.attacking == 0) {
                  this.attacking++;
                  this.targetX = this.parentEntity.field_70165_t;
                  this.targetY = this.parentEntity.field_70163_u;
                  this.targetZ = this.parentEntity.field_70161_v;
               }
            } else if (this.attackTimer > 0 && this.attackTimer > 0) {
               this.attackTimer--;
            }
         }
      }
   }
}
