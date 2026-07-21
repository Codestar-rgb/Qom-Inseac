package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeNotGround;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSwim;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityInfSquid extends EntityPInfected implements EntityCanSwim, EntityCutomAttack {
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityInfSquid.class, DataSerializers.field_187191_a);

   public EntityInfSquid(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.9F, 0.9F);
      this.field_70765_h = new EntityInfSquid.AIMoveControl(this);
      this.canModRender = 0;
      this.type = 15;
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70714_bg.func_85156_a(this.aiWander);
   }

   @Override
   public int getParasiteIDRegister() {
      return 307;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infsquidCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeNotGround(this, 3.0, SRPConfig.primitiveFollow, 0.05, false, 0, 1));
      this.field_70714_bg.func_75776_a(6, new EntityInfSquid.AIMoveRandom());
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFSQUID_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFSQUID_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFSQUID_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFSQUID_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
   }

   protected PathNavigate func_175447_b(World worldIn) {
      return new PathNavigateSwimmer(this, worldIn);
   }

   @Override
   public void func_70636_d() {
      if (!this.func_175446_cd()) {
         this.liquidLeap = 0;
         super.func_70636_d();
         this.func_189654_d(this.field_70171_ac);
         this.liquidLeap = 0;
      }
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.8F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDSQUID_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.INFECTEDSQUID_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.INFECTEDSQUID_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SoundEvents.field_187869_gK, 0.15F, 1.0F);
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      return this.func_70652_k(entityIn);
   }

   public boolean func_70648_aU() {
      return true;
   }

   @Override
   public boolean func_70601_bi() {
      return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && SRPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
   }

   public boolean func_70058_J() {
      return this.field_70170_p.func_72917_a(this.func_174813_aQ(), this);
   }

   public int func_70627_aG() {
      return 0;
   }

   @Override
   public boolean func_70692_ba() {
      return true;
   }

   protected int func_70693_a(EntityPlayer player) {
      return 1 + this.field_70170_p.field_73012_v.nextInt(3);
   }

   public void func_70030_z() {
      int i = this.func_70086_ai();
      super.func_70030_z();
      if (!this.func_175446_cd()) {
         if (this.func_70089_S() && !this.func_70090_H()) {
            this.func_70050_g(--i);
            if (this.func_70086_ai() == -20) {
               this.func_70050_g(0);
               this.func_70097_a(DamageSource.field_76369_e, 2.0F);
            }
         } else {
            this.func_70050_g(300);
         }
      }
   }

   public boolean func_96092_aw() {
      return false;
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

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityInfSquid vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityInfSquid.this.field_70165_t;
            double d1 = this.field_75647_c - EntityInfSquid.this.field_70163_u;
            double d2 = this.field_75644_d - EntityInfSquid.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityInfSquid.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityInfSquid.this.field_70159_w *= 0.5;
               EntityInfSquid.this.field_70181_x *= 0.5;
               EntityInfSquid.this.field_70179_y *= 0.5;
            } else {
               EntityInfSquid.this.field_70159_w = EntityInfSquid.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityInfSquid.this.field_70181_x = EntityInfSquid.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityInfSquid.this.field_70179_y = EntityInfSquid.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityInfSquid.this.func_70638_az() == null) {
                  EntityInfSquid.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityInfSquid.this.field_70159_w, EntityInfSquid.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntityInfSquid.this.field_70761_aq = EntityInfSquid.this.field_70177_z;
               } else {
                  double d4 = EntityInfSquid.this.func_70638_az().field_70165_t - EntityInfSquid.this.field_70165_t;
                  double d5 = EntityInfSquid.this.func_70638_az().field_70161_v - EntityInfSquid.this.field_70161_v;
                  EntityInfSquid.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityInfSquid.this.field_70761_aq = EntityInfSquid.this.field_70177_z;
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
         return EntityInfSquid.this.field_70146_Z.nextInt(7) == 0;
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityInfSquid.this);
         byte flag = 1;
         double speed = 0.13;
         if (EntityInfSquid.this.func_70638_az() != null) {
            if (EntityInfSquid.this.func_70068_e(EntityInfSquid.this.func_70638_az()) > 100.0) {
               blockpos = new BlockPos(EntityInfSquid.this.func_70638_az());
               flag = 2;
            } else if (EntityInfSquid.this.func_70068_e(EntityInfSquid.this.func_70638_az()) < 36.0) {
               blockpos = new BlockPos(EntityInfSquid.this.func_70638_az());
               flag = 3;
            }
         }

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityInfSquid.this.field_70146_Z.nextInt(15) - 7,
               EntityInfSquid.this.field_70146_Z.nextInt(11) - 5,
               EntityInfSquid.this.field_70146_Z.nextInt(15) - 7
            );
            if (flag == 2) {
               blockpos1 = blockpos.func_177982_a(
                  EntityInfSquid.this.field_70146_Z.nextInt(6) - 2,
                  EntityInfSquid.this.field_70146_Z.nextInt(7) - 2,
                  EntityInfSquid.this.field_70146_Z.nextInt(6) - 2
               );
            } else if (flag == 3) {
               blockpos1 = blockpos.func_177982_a(
                  EntityInfSquid.this.field_70146_Z.nextInt(4) + 3,
                  EntityInfSquid.this.field_70146_Z.nextInt(5) + 4,
                  EntityInfSquid.this.field_70146_Z.nextInt(4) + 3
               );
            }

            if (EntityInfSquid.this.field_70170_p.func_180495_p(blockpos1).func_185904_a() == Material.field_151586_h) {
               EntityInfSquid.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, speed);
               if (EntityInfSquid.this.func_70638_az() == null) {
                  EntityInfSquid.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
