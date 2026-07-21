package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityRond extends EntityPPure {
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityRond.class, DataSerializers.field_187191_a);

   public EntityRond(World worldIn) {
      super(worldIn);
      this.field_70765_h = new EntityRond.AIMoveControl(this);
      this.func_70105_a(1.7F, 2.4F);
      this.func_189654_d(true);
      this.field_70714_bg.func_85156_a(this.folow);
      this.borderOrb = -1;
      if (SRPConfigMobs.ombooMaxY != 256) {
         this.field_70714_bg.func_75776_a(3, new EntityAIFlightLimits(this, SRPConfigMobs.ombooMaxY, true));
      }

      this.adaptationCap = 0.95F;
   }

   @Override
   public int getParasiteIDRegister() {
      return 47;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.pureFollow));
      this.field_70714_bg.func_75776_a(4, new EntityRond.AIChargeAttack());
      this.field_70714_bg.func_75776_a(6, new EntityRond.AIMoveRandom());
      this.field_70714_bg.func_75776_a(5, new EntityRond.AIBomb(this));
      this.field_70714_bg.func_75776_a(4, new EntityAIFlightLimits(this, 7, false));
      this.field_70714_bg.func_75776_a(4, new EntityAIFlightLimits(this, 20, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.OMBOO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.OMBOO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.OMBOO_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
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
      return 2.4F;
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

   class AIBomb extends EntityAIBase {
      private final EntityParasiteBase parent;
      private int ccc;

      public AIBomb(EntityParasiteBase parentIn) {
         this.parent = parentIn;
         this.ccc = 0;
      }

      public boolean func_75250_a() {
         this.ccc++;
         if (this.ccc >= 15) {
            this.ccc = 0;
            if (this.parent.func_70638_az() != null) {
               EntityLivingBase target = this.parent.func_70638_az();
               if (!target.field_70122_E) {
                  this.ccc = 7;
                  return false;
               }

               if (target.func_70092_e(this.parent.field_70165_t, target.field_70163_u, this.parent.field_70161_v) < 25.0) {
                  return true;
               }
            }
         }

         return false;
      }

      public void func_75246_d() {
         EntityBomb out = new EntityBomb(this.parent.field_70170_p, this.parent, SRPConfigMobs.ombooGriefing);
         out.func_82149_j(this.parent);
         out.setFuse(80);
         out.setStren(1.0F);
         out.setDamage((float)SRPAttributes.OMBOO_BOMBDAMAGE, 4);
         this.parent.field_70170_p.func_72838_d(out);
         out.updateSTR();
      }
   }

   class AIChargeAttack extends EntityAIBase {
      public AIChargeAttack() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return EntityRond.this.func_70638_az() != null && EntityRond.this.field_70146_Z.nextInt(7) == 0
            ? EntityRond.this.func_70068_e(EntityRond.this.func_70638_az()) > 3.0
            : false;
      }

      public boolean func_75253_b() {
         return EntityRond.this.func_70605_aq().func_75640_a()
            && EntityRond.this.isCharging()
            && EntityRond.this.func_70638_az() != null
            && EntityRond.this.func_70638_az().func_70089_S();
      }

      public void func_75249_e() {
         EntityLivingBase entitylivingbase = EntityRond.this.func_70638_az();
         Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
         EntityRond.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b + 10.0, vec3d.field_72449_c, 1.0);
         EntityRond.this.setCharging(true);
      }

      public void func_75251_c() {
         EntityRond.this.setCharging(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = EntityRond.this.func_70638_az();
         if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
            if (EntityRond.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
               EntityRond.this.func_70652_k(entitylivingbase);
               EntityRond.this.setCharging(false);
            } else {
               double d0 = EntityRond.this.func_70068_e(entitylivingbase);
               if (d0 < 9.0) {
                  Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                  EntityRond.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b + 10.0, vec3d.field_72449_c, 1.0);
               }
            }
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityRond vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityRond.this.field_70165_t;
            double d1 = this.field_75647_c - EntityRond.this.field_70163_u;
            double d2 = this.field_75644_d - EntityRond.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityRond.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityRond.this.field_70159_w *= 0.5;
               EntityRond.this.field_70181_x *= 0.5;
               EntityRond.this.field_70179_y *= 0.5;
            } else {
               EntityRond.this.field_70159_w = EntityRond.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityRond.this.field_70181_x = EntityRond.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityRond.this.field_70179_y = EntityRond.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityRond.this.func_70638_az() == null) {
                  EntityRond.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityRond.this.field_70159_w, EntityRond.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntityRond.this.field_70761_aq = EntityRond.this.field_70177_z;
               } else {
                  double d4 = EntityRond.this.func_70638_az().field_70165_t - EntityRond.this.field_70165_t;
                  double d5 = EntityRond.this.func_70638_az().field_70161_v - EntityRond.this.field_70161_v;
                  EntityRond.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityRond.this.field_70761_aq = EntityRond.this.field_70177_z;
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
         return !EntityRond.this.func_70605_aq().func_75640_a() && EntityRond.this.field_70146_Z.nextInt(7) == 0;
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityRond.this);
         byte flag = 1;
         double speed = 0.2;
         if (EntityRond.this.func_70638_az() != null) {
            if (EntityRond.this.func_70068_e(EntityRond.this.func_70638_az()) > 100.0) {
               blockpos = new BlockPos(EntityRond.this.func_70638_az());
               flag = 2;
               speed += 0.1;
            } else if (EntityRond.this.func_70068_e(EntityRond.this.func_70638_az()) < 36.0) {
               blockpos = new BlockPos(EntityRond.this.func_70638_az());
               flag = 3;
               speed += 0.1;
            }
         }

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityRond.this.field_70146_Z.nextInt(15) - 7, EntityRond.this.field_70146_Z.nextInt(11) - 5, EntityRond.this.field_70146_Z.nextInt(15) - 7
            );
            if (flag == 2) {
               blockpos1 = blockpos.func_177982_a(
                  EntityRond.this.field_70146_Z.nextInt(6) - 2, EntityRond.this.field_70146_Z.nextInt(7) - 2, EntityRond.this.field_70146_Z.nextInt(6) - 2
               );
            } else if (flag == 3) {
               blockpos1 = blockpos.func_177982_a(
                  EntityRond.this.field_70146_Z.nextInt(4) + 3, EntityRond.this.field_70146_Z.nextInt(5) + 4, EntityRond.this.field_70146_Z.nextInt(4) + 3
               );
            }

            if (EntityRond.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityRond.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 1.0, blockpos1.func_177952_p() + 0.5, speed);
               if (EntityRond.this.func_70638_az() == null) {
                  EntityRond.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 1.0, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
