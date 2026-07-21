package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.entity.EntityParasiticScent;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySoo extends EntityPPure implements EntityCanFly {
   private int scentCool;
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntitySoo.class, DataSerializers.field_187191_a);

   public EntitySoo(World worldIn) {
      super(worldIn);
      this.field_70765_h = new EntitySoo.AIMoveControl(this);
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

      this.adaptationCap = 0.95F;
      this.scentCool = 800;
   }

   @Override
   public int getParasiteIDRegister() {
      return 82;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, 64.0));
      this.field_70714_bg.func_75776_a(6, new EntitySoo.AIMoveRandom());
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

      this.scentCool--;
      if (this.scentCool < 0 && this.srpTicks == 10 && SRPConfigSystems.useScent && this.getLevelCreated() >= SRPConfigSystems.deveScentUse) {
         List<Entity> serverList = this.field_70170_p.field_72996_f;
         int count = 0;

         for (int x = 0; x < serverList.size(); x++) {
            if (serverList.get(x) instanceof EntityParasiticScent) {
               count++;
            }
         }

         if (count > SRPConfigSystems.scentCap) {
            return;
         }

         if (this.func_70638_az() != null) {
            EntityParasiticScent sss = new EntityParasiticScent(this.field_70170_p);
            sss.func_82149_j(this.func_70638_az());
            sss.setTargetToKill(this.func_70638_az(), false);
            sss.setDieToE(true);
            sss.setCanFollow(true);
            this.field_70170_p.func_72838_d(sss);
            this.field_70708_bq = 0;
            this.scentCool = 800;
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
      return 1.6F;
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
      return super.func_180482_a(difficulty, livingdata);
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

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntitySoo vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntitySoo.this.field_70165_t;
            double d1 = this.field_75647_c - EntitySoo.this.field_70163_u;
            double d2 = this.field_75644_d - EntitySoo.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntitySoo.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntitySoo.this.field_70159_w *= 0.5;
               EntitySoo.this.field_70181_x *= 0.5;
               EntitySoo.this.field_70179_y *= 0.5;
            } else {
               EntitySoo.this.field_70159_w = EntitySoo.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntitySoo.this.field_70181_x = EntitySoo.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntitySoo.this.field_70179_y = EntitySoo.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntitySoo.this.func_70638_az() == null) {
                  EntitySoo.this.field_70177_z = -((float)MathHelper.func_181159_b(EntitySoo.this.field_70159_w, EntitySoo.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntitySoo.this.field_70761_aq = EntitySoo.this.field_70177_z;
               } else {
                  double d4 = EntitySoo.this.func_70638_az().field_70165_t - EntitySoo.this.field_70165_t;
                  double d5 = EntitySoo.this.func_70638_az().field_70161_v - EntitySoo.this.field_70161_v;
                  EntitySoo.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntitySoo.this.field_70761_aq = EntitySoo.this.field_70177_z;
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
         EntityMoveHelper entitymovehelper = EntitySoo.this.func_70605_aq();
         if (!entitymovehelper.func_75640_a()) {
            return true;
         } else {
            double d0 = entitymovehelper.func_179917_d() - EntitySoo.this.field_70165_t;
            double d1 = entitymovehelper.func_179919_e() - EntitySoo.this.field_70163_u;
            double d2 = entitymovehelper.func_179918_f() - EntitySoo.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0 || d3 > 3600.0;
         }
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         if (EntitySoo.this.func_70638_az() != null) {
            BlockPos blockpos = new BlockPos(EntitySoo.this);
            byte flag = 1;
            double speed = 0.11;
            if (EntitySoo.this.func_70068_e(EntitySoo.this.func_70638_az()) > 400.0) {
               blockpos = new BlockPos(EntitySoo.this.func_70638_az());
               flag = 2;
               speed += 0.11;
            } else if (EntitySoo.this.func_70068_e(EntitySoo.this.func_70638_az()) < 100.0) {
               blockpos = new BlockPos(EntitySoo.this.func_70638_az());
               flag = 3;
               speed += 0.11;
            }

            for (int i = 0; i < 3; i++) {
               BlockPos blockpos1 = blockpos.func_177982_a(
                  EntitySoo.this.field_70146_Z.nextInt(15) - 7, EntitySoo.this.field_70146_Z.nextInt(9) - 5, EntitySoo.this.field_70146_Z.nextInt(15) - 7
               );
               if (flag == 2) {
                  blockpos1 = blockpos.func_177982_a(
                     EntitySoo.this.field_70146_Z.nextInt(6) - 2, EntitySoo.this.field_70146_Z.nextInt(7) - 2, EntitySoo.this.field_70146_Z.nextInt(6) - 2
                  );
               } else if (flag == 3) {
                  blockpos1 = blockpos.func_177982_a(
                     EntitySoo.this.field_70146_Z.nextInt(4) + 3, EntitySoo.this.field_70146_Z.nextInt(5) + 4, EntitySoo.this.field_70146_Z.nextInt(4) + 3
                  );
               }

               if (EntitySoo.this.field_70170_p.func_175623_d(blockpos1)) {
                  EntitySoo.this.field_70765_h
                     .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, speed);
                  if (EntitySoo.this.func_70638_az() == null) {
                     EntitySoo.this.func_70671_ap()
                        .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
                  }
                  break;
               }
            }
         } else {
            Random random = EntitySoo.this.func_70681_au();
            double d0 = EntitySoo.this.field_70165_t + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = EntitySoo.this.field_70163_u + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = EntitySoo.this.field_70161_v + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            EntitySoo.this.func_70605_aq().func_75642_a(d0, d1, d2, 0.5);
         }
      }
   }
}
