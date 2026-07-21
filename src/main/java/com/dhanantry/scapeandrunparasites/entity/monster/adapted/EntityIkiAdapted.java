package com.dhanantry.scapeandrunparasites.entity.monster.adapted;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAdapted;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityViin;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityIkiAdapted extends EntityPAdapted implements EntityCanShoot, EntityCanFly {
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityIkiAdapted.class, DataSerializers.field_187191_a);
   private int count;

   public EntityIkiAdapted(World worldIn) {
      super(worldIn);
      this.field_70765_h = new EntityIkiAdapted.AIMoveControl(this);
      this.func_70105_a(1.1F, 1.4F);
      this.func_189654_d(true);
      this.field_70714_bg.func_85156_a(this.folow);
      this.borderOrb = -1;
      this.field_70158_ak = true;
      if (SRPConfigMobs.emanaMaxY != 256) {
         this.field_70714_bg.func_75776_a(3, new EntityAIFlightLimits(this, SRPConfigMobs.emanaMaxY, true));
      }
   }

   @Override
   public int getParasiteIDRegister() {
      return 92;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.AIKI_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.AIKI_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.AIKI_DEATH;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.adaptedFollow));
      this.field_70714_bg.func_75776_a(5, new EntityIkiAdapted.AIBomb(this));
      this.field_70714_bg.func_75776_a(7, new EntityIkiAdapted.AIMoveRandom());
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.IKI_A_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.IKI_A_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.IKI_A_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.IKI_A_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.adaptedFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K) {
         if (this.srpTicks == 10) {
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
         }
      } else if (this.field_70146_Z.nextInt(25) == 0) {
         for (int i = 0; i <= 20; i++) {
            if (i % 5 == 0) {
               this.spawnParticlesGoreMouth(SRPEnumParticle.GSPLASH, 0, -1, -1, 0.2, 0.0);
            }
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

   public float func_70047_e() {
      return 0.7F;
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
            ParasiteEventEntity.spawnNext(this, new EntityLesh(this.field_70170_p), true, false);
         }
      }
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

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 100) {
         for (int i = 0; i <= 1; i++) {
            this.spawnParticles(EnumParticleTypes.FLAME);
         }
      } else {
         super.func_70103_a(id);
      }
   }

   public EntityFireball getProj(double accelX, double accelY, double accelZ) {
      float pit = 1.0F;
      if (this.count == 4) {
         pit = 1.5F;
      }

      this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0F, pit);
      if (this.count >= 4) {
         this.count = 0;
         return new EntityProjectileNade(this.field_70170_p, this, accelX, accelY, accelZ, 3, 60);
      } else {
         EntityProjectileSpineball ball = new EntityProjectileSpineball(this.field_70170_p, this, accelX, accelY, accelZ, SRPAttributes.EMANA_RANGED_DAMAGE);
         ball.setDurationAmplifier(SRPConfigMobs.emanaPoisonDuration, SRPConfigMobs.emanaPoisonAmplifier);
         ball.setGearDamage(SRPConfigMobs.emanaGearD);
         return ball;
      }
   }

   @Override
   public void playProjSound() {
      this.count++;
      if (this.count == 4) {
         this.field_70170_p.func_72960_a(this, (byte)100);
         float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F + 2.0F;
         this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0F, v);
      } else {
         this.func_184185_a(SRPSounds.EMANA_SHOOTINGPOST, 2.0F, 1.0F);
      }
   }

   public boolean isCharging() {
      return this.getVexFlag(1);
   }

   public void setCharging(boolean charging) {
      this.setVexFlag(1, charging);
   }

   class AIBomb extends EntityAIBase {
      private final EntityParasiteBase parent;
      private int ccc;

      public AIBomb(EntityParasiteBase parentIn) {
         this.parent = parentIn;
         this.ccc = 0;
      }

      public boolean func_75250_a() {
         this.ccc++;
         if (this.ccc >= 20) {
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
         List<Entity> serverList = this.parent.field_70170_p.field_72996_f;
         int count = 0;

         for (int x = 0; x < serverList.size(); x++) {
            if (serverList.get(x) instanceof EntityViin) {
               count++;
            }
         }

         if (count < SRPConfig.worldGnatCap) {
            EntityViin out = new EntityViin(this.parent.field_70170_p);
            if (this.parent.func_70638_az() != null) {
               out.func_82149_j(this.parent);
               this.parent.field_70170_p.func_72838_d(out);
               SRPMain.network
                  .sendToAll(new SRPPacketParticle(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 0.5F, 0.5F, (byte)10));
            }
         } else {
            EntityBomb bomb = new EntityBomb(this.parent.field_70170_p, this.parent, false);
            if (this.parent.func_70638_az() != null) {
               if (this.parent.func_70638_az().field_70163_u > this.parent.field_70163_u) {
                  return;
               }

               bomb.func_82149_j(this.parent);
               bomb.setFuse(60);
               bomb.setStren(0.0F);
               bomb.setSkin(1);
               bomb.setDamage((float)this.parent.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b(), 2);
               bomb.field_70125_A -= -20.0F;
               this.parent.field_70170_p.func_72838_d(bomb);
               bomb.updateSTR();
               SRPMain.network
                  .sendToAll(new SRPPacketParticle(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 0.5F, 0.5F, (byte)10));
            }
         }
      }
   }

   class AIChargeAttack extends EntityAIBase {
      public AIChargeAttack() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return EntityIkiAdapted.this.func_70638_az() != null && EntityIkiAdapted.this.field_70146_Z.nextInt(5) == 0
            ? EntityIkiAdapted.this.func_70068_e(EntityIkiAdapted.this.func_70638_az()) > 4.0
            : false;
      }

      public boolean func_75253_b() {
         return EntityIkiAdapted.this.func_70605_aq().func_75640_a()
            && EntityIkiAdapted.this.isCharging()
            && EntityIkiAdapted.this.func_70638_az() != null
            && EntityIkiAdapted.this.func_70638_az().func_70089_S();
      }

      public void func_75249_e() {
         EntityLivingBase entitylivingbase = EntityIkiAdapted.this.func_70638_az();
         Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
         EntityIkiAdapted.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 7.0, vec3d.field_72449_c, 0.2);
         EntityIkiAdapted.this.setCharging(true);
      }

      public void func_75251_c() {
         EntityIkiAdapted.this.setCharging(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = EntityIkiAdapted.this.func_70638_az();
         if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
            if (EntityIkiAdapted.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
               EntityIkiAdapted.this.func_70652_k(entitylivingbase);
               EntityIkiAdapted.this.setCharging(false);
            } else {
               double d0 = EntityIkiAdapted.this.func_70068_e(entitylivingbase);
               if (d0 < 9.0) {
                  Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                  EntityIkiAdapted.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 7.0, vec3d.field_72449_c, 1.0);
               } else {
                  Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                  EntityIkiAdapted.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 7.0, vec3d.field_72449_c, 1.1);
               }
            }
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityIkiAdapted vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityIkiAdapted.this.field_70165_t;
            double d1 = this.field_75647_c - EntityIkiAdapted.this.field_70163_u;
            double d2 = this.field_75644_d - EntityIkiAdapted.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityIkiAdapted.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityIkiAdapted.this.field_70159_w *= 0.5;
               EntityIkiAdapted.this.field_70181_x *= 0.5;
               EntityIkiAdapted.this.field_70179_y *= 0.5;
            } else {
               EntityIkiAdapted.this.field_70159_w = EntityIkiAdapted.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityIkiAdapted.this.field_70181_x = EntityIkiAdapted.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityIkiAdapted.this.field_70179_y = EntityIkiAdapted.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityIkiAdapted.this.func_70638_az() == null) {
                  EntityIkiAdapted.this.field_70177_z = -(
                        (float)MathHelper.func_181159_b(EntityIkiAdapted.this.field_70159_w, EntityIkiAdapted.this.field_70179_y)
                     )
                     * (180.0F / (float)Math.PI);
                  EntityIkiAdapted.this.field_70761_aq = EntityIkiAdapted.this.field_70177_z;
               } else {
                  double d4 = EntityIkiAdapted.this.func_70638_az().field_70165_t - EntityIkiAdapted.this.field_70165_t;
                  double d5 = EntityIkiAdapted.this.func_70638_az().field_70161_v - EntityIkiAdapted.this.field_70161_v;
                  EntityIkiAdapted.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityIkiAdapted.this.field_70761_aq = EntityIkiAdapted.this.field_70177_z;
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
         return !EntityIkiAdapted.this.func_70605_aq().func_75640_a() && EntityIkiAdapted.this.field_70146_Z.nextInt(7) == 0;
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityIkiAdapted.this);
         byte flag = 1;
         double speed = 0.5;
         if (EntityIkiAdapted.this.func_70638_az() != null) {
            if (EntityIkiAdapted.this.func_70068_e(EntityIkiAdapted.this.func_70638_az()) > 225.0) {
               blockpos = new BlockPos(EntityIkiAdapted.this.func_70638_az());
               flag = 2;
               speed += 0.25;
            } else if (EntityIkiAdapted.this.func_70068_e(EntityIkiAdapted.this.func_70638_az()) < 36.0) {
               blockpos = new BlockPos(EntityIkiAdapted.this.func_70638_az());
               flag = 3;
               speed += 0.25;
            }
         }

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityIkiAdapted.this.field_70146_Z.nextInt(15) - 7,
               EntityIkiAdapted.this.field_70146_Z.nextInt(11) - 5,
               EntityIkiAdapted.this.field_70146_Z.nextInt(15) - 7
            );
            if (EntityIkiAdapted.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityIkiAdapted.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, speed);
               if (EntityIkiAdapted.this.func_70638_az() == null) {
                  EntityIkiAdapted.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
