package com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent;

import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanColony;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileElviaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileNade;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import javax.annotation.Nonnull;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityElvia extends EntityPPreeminent implements EntityCanShoot, EntityCanColony, EntityCanFly {
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityElvia.class, DataSerializers.field_187191_a);
   private int timer = 0;
   private boolean camu;
   private int count;

   public EntityElvia(World worldIn) {
      super(worldIn);
      this.func_70105_a(4.0F, 4.0F);
      this.field_70765_h = new EntityElvia.AIMoveControl(this);
      this.func_189654_d(true);
      this.field_70714_bg.func_85156_a(this.aiWander);
      this.adaptationCap = 0.95F;
      this.field_70158_ak = true;
   }

   @Override
   public int getParasiteIDRegister() {
      return 85;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.preeminentFollow, true, 3));
      this.field_70714_bg.func_75776_a(5, new EntityAIAttackProjectile(this, 20, 10, 4, true));
      this.field_70714_bg.func_75776_a(4, new EntityElvia.AIChargeAttack());
      this.field_70714_bg.func_75776_a(6, new EntityElvia.AIMoveRandom());
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(4, new EntityAIFlightLimits(this, 10, false));
      this.field_70714_bg.func_75776_a(4, new EntityAIFlightLimits(this, 30, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ELVIA_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ELVIA_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.preeminentFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K) {
         if (this.field_70122_E) {
            this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
         }

         if (this.srpTicks == 10) {
            if (this.func_70638_az() != null) {
               for (EntityLivingBase entitylivingbase : this.field_70170_p
                  .func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(3.0, 3.0, 3.0))) {
                  if (entitylivingbase != this && !(entitylivingbase instanceof EntityParasiteBase)) {
                     float f = (float)MathHelper.func_181159_b(
                        entitylivingbase.field_70161_v - this.field_70161_v, entitylivingbase.field_70165_t - this.field_70165_t
                     );
                     EntityDamage damage = new EntityDamage(
                        this.field_70170_p,
                        entitylivingbase.field_70165_t,
                        entitylivingbase.field_70163_u,
                        entitylivingbase.field_70161_v,
                        f,
                        this,
                        1.0F,
                        false,
                        2.5F
                     );
                     this.field_70170_p.func_72838_d(damage);
                  }
               }
            }

            if ((
                  this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a
                     || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a
               )
               && this.func_70638_az() != null) {
               this.field_70181_x = 0.5;
            }

            float currentH = this.func_110143_aJ() / this.func_110138_aP();
            if (this.getSSS()) {
               this.func_70690_d(new PotionEffect(MobEffects.field_76441_p, 25, 1, false, false));
               if (currentH < SRPConfigMobs.elvianeededhealth) {
                  this.setSSS(false);
               }
            } else if (currentH >= SRPConfigMobs.elvianeededhealth) {
               this.timer++;
               if (this.timer > 2) {
                  this.setSSS(true);
                  this.particleStatus((byte)6);
                  this.timer = 0;
               }
            }
         }
      }
   }

   public boolean getSSS() {
      return this.camu;
   }

   public void setSSS(boolean in) {
      this.camu = in;
   }

   @Override
   protected boolean summonFlam(EntityLivingBase in) {
      return super.summonFlam(in);
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      this.setSSS(false);
      this.timer = 0;
      return super.func_70097_a(source, amount);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.func_189654_d(true);
   }

   @Override
   protected void func_70619_bc() {
      super.func_70619_bc();
   }

   public void func_184178_b(EntityPlayerMP player) {
   }

   public int func_184649_cE() {
      return 3;
   }

   public float func_70047_e() {
      return 2.1F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.ELVIA_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.ELVIA_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.ELVIA_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.elviaOrbEffects, mobs);
      }

      return flag;
   }

   protected float func_70599_aP() {
      return 5.0F;
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
   }

   @Override
   public void func_180430_e(float distance, float damageMultiplier) {
   }

   public boolean func_70617_f_() {
      return false;
   }

   @Override
   public boolean onlySpawnInside() {
      return false;
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
      this.setSSS(false);
      this.timer = 0;
      if (this.count >= 1) {
         this.count = 0;
         return new EntityProjectileNade(this.field_70170_p, this, accelX, accelY, accelZ, 4, 60);
      } else {
         return new EntityProjectileElviaBall(this.field_70170_p, this, accelX, accelY, accelZ);
      }
   }

   @Override
   public void playProjSound() {
      this.count++;
      this.setSSS(false);
      this.timer = 0;
   }

   class AIBomb extends EntityAIBase {
      private final EntityLiving parent;
      private int ccc;

      public AIBomb(EntityLiving parentIn) {
         this.parent = parentIn;
         this.ccc = 0;
      }

      public boolean func_75250_a() {
         this.ccc++;
         if (this.ccc >= 100) {
            this.ccc = 0;
            if (this.parent.func_70638_az() != null) {
               EntityLivingBase target = this.parent.func_70638_az();
               if (target.func_70092_e(this.parent.field_70165_t, target.field_70163_u, this.parent.field_70161_v) < 256.0) {
                  return true;
               }
            }
         }

         return false;
      }

      public void func_75246_d() {
      }
   }

   class AIChargeAttack extends EntityAIBase {
      public AIChargeAttack() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return EntityElvia.this.func_70638_az() != null && EntityElvia.this.field_70146_Z.nextInt(5) == 0
            ? EntityElvia.this.func_70068_e(EntityElvia.this.func_70638_az()) > 4.0
            : false;
      }

      public boolean func_75253_b() {
         return EntityElvia.this.func_70605_aq().func_75640_a()
            && EntityElvia.this.isCharging()
            && EntityElvia.this.func_70638_az() != null
            && EntityElvia.this.func_70638_az().func_70089_S();
      }

      public void func_75249_e() {
         EntityLivingBase entitylivingbase = EntityElvia.this.func_70638_az();
         Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
         EntityElvia.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 0.7);
         EntityElvia.this.setCharging(true);
      }

      public void func_75251_c() {
         EntityElvia.this.setCharging(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = EntityElvia.this.func_70638_az();
         if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
            if (EntityElvia.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
               EntityElvia.this.func_70652_k(entitylivingbase);
               EntityElvia.this.setCharging(false);
            } else {
               double d0 = EntityElvia.this.func_70068_e(entitylivingbase);
               if (d0 < 9.0) {
                  if (EntityElvia.this.func_70685_l(entitylivingbase)) {
                     Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                     EntityElvia.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b + 20.0, vec3d.field_72449_c, 0.7);
                  } else {
                     Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                     EntityElvia.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.1);
                  }
               } else {
                  Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                  EntityElvia.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 1.1);
               }
            }
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityElvia vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityElvia.this.field_70165_t;
            double d1 = this.field_75647_c - EntityElvia.this.field_70163_u;
            double d2 = this.field_75644_d - EntityElvia.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityElvia.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityElvia.this.field_70159_w *= 0.5;
               EntityElvia.this.field_70181_x *= 0.5;
               EntityElvia.this.field_70179_y *= 0.5;
            } else {
               EntityElvia.this.field_70159_w = EntityElvia.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityElvia.this.field_70181_x = EntityElvia.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityElvia.this.field_70179_y = EntityElvia.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityElvia.this.func_70638_az() == null) {
                  EntityElvia.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityElvia.this.field_70159_w, EntityElvia.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntityElvia.this.field_70761_aq = EntityElvia.this.field_70177_z;
               } else {
                  double d4 = EntityElvia.this.func_70638_az().field_70165_t - EntityElvia.this.field_70165_t;
                  double d5 = EntityElvia.this.func_70638_az().field_70161_v - EntityElvia.this.field_70161_v;
                  EntityElvia.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityElvia.this.field_70761_aq = EntityElvia.this.field_70177_z;
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
         return !EntityElvia.this.func_70605_aq().func_75640_a() && EntityElvia.this.field_70146_Z.nextInt(7) == 0;
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityElvia.this);
         byte flag = 1;
         double speed = 0.6;
         if (EntityElvia.this.func_70638_az() != null) {
            if (EntityElvia.this.func_70068_e(EntityElvia.this.func_70638_az()) > 100.0) {
               blockpos = new BlockPos(EntityElvia.this.func_70638_az());
               flag = 2;
               speed += 0.1;
            } else if (EntityElvia.this.func_70068_e(EntityElvia.this.func_70638_az()) < 36.0) {
               blockpos = new BlockPos(EntityElvia.this.func_70638_az());
               flag = 3;
               speed += 0.15;
            }
         }

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityElvia.this.field_70146_Z.nextInt(15) - 7, EntityElvia.this.field_70146_Z.nextInt(11) - 5, EntityElvia.this.field_70146_Z.nextInt(15) - 7
            );
            if (flag == 2) {
               blockpos1 = blockpos.func_177982_a(
                  EntityElvia.this.field_70146_Z.nextInt(6) - 2, EntityElvia.this.field_70146_Z.nextInt(7) - 2, EntityElvia.this.field_70146_Z.nextInt(6) - 2
               );
            } else if (flag == 3) {
               blockpos1 = blockpos.func_177982_a(
                  EntityElvia.this.field_70146_Z.nextInt(4) + 3, EntityElvia.this.field_70146_Z.nextInt(5) + 4, EntityElvia.this.field_70146_Z.nextInt(4) + 3
               );
            }

            if (EntityElvia.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityElvia.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 1.0, blockpos1.func_177952_p() + 0.5, speed);
               if (EntityElvia.this.func_70638_az() == null) {
                  EntityElvia.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 1.0, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
