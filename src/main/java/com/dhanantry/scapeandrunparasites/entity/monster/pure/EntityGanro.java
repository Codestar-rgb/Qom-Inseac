package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvadeDash;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWaveShock;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.PathNavigateClimberStatus;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGanro extends EntityPPure implements EntityCutomAttack, EntityBodyParts {
   private float attackTimer;
   private boolean up;
   public EntityBody tendril1;
   public EntityBody tendril2;
   private byte left1;
   private byte right2;
   private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityGanro.class, DataSerializers.field_187191_a);
   private int attacking;
   private double targetX;
   private double targetY;
   private double targetZ;
   private boolean skillCharge;
   private int border;
   private boolean skillshockwave;

   public EntityGanro(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.901F, 4.2F);
      this.field_70138_W = 1.0F;
      this.tendril1 = new EntityBody(this, 0.7F, 0.9F, 1.0F, 0.7F, 3.7F, 1, 1, true);
      this.tendril2 = new EntityBody(this, 0.7F, 0.9F, 1.0F, 0.7F, 3.7F, -1, 2, true);
      this.left1 = 1;
      this.right2 = 1;
      this.adaptationCap = 0.95F;
      this.field_70158_ak = true;
      this.skillCharge = false;
   }

   @Override
   public int getParasiteIDRegister() {
      return 33;
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISkill(this, 80, 100, 10, true, 14));
      this.setskillLeapValues(1.2F, 2.5, 5);
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 40, (int)(SRPConfig.pureFollow * 0.7), 2, false, 2));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.12));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 4.0));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 40, 32, 8, true, 1));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvadeDash(this, 20, 2, 4, 3.0, 15));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.GANRO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.GANRO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.GANRO_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.GANRO_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
   }

   private void maybeLaunchTarget(Entity target) {
      if (!this.field_70170_p.field_72995_K && target instanceof EntityLivingBase) {
         if (!(this.func_70681_au().nextFloat() >= 0.1F)) {
            boolean isPlayer = target instanceof EntityPlayer;
            double BASE_Y = 1.05;
            double VERT = isPlayer ? 0.525 : 1.05;
            double HORIZ = 0.4;
            double dx = target.field_70165_t - this.field_70165_t;
            double dz = target.field_70161_v - this.field_70161_v;
            double len = Math.sqrt(dx * dx + dz * dz);
            if (len < 1.0E-4) {
               dx = this.func_70681_au().nextDouble() - 0.5;
               dz = this.func_70681_au().nextDouble() - 0.5;
               len = Math.sqrt(dx * dx + dz * dz);
            }

            dx /= len;
            dz /= len;
            target.func_70024_g(dx * 0.4, VERT, dz * 0.4);
            target.field_70133_I = true;
         }
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.up) {
         this.attackTimer = (float)(this.attackTimer + 0.2);
         if (this.attackTimer > 1.5) {
            this.up = false;
         }
      } else {
         this.attackTimer = (float)(this.attackTimer - 0.1);
      }

      this.tendril1.func_70071_h_();
      this.tendril2.func_70071_h_();
      if (!this.field_70170_p.field_72995_K) {
         this.setBesideClimbableBlock(this.field_70123_F);
      }
   }

   @Override
   protected boolean spawnT(EntityLivingBase in, int range2, int mini2, int check, int type) {
      if (this.func_70685_l(in)) {
         return false;
      } else {
         return this.field_70146_Z.nextInt(4) == 0 ? super.spawnT(in, range2, mini2, check, type) : super.spawnT(in, range2, mini2, check, 2);
      }
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(CLIMBING, (byte)0);
   }

   public boolean isBesideClimbableBlock() {
      return ((Byte)this.field_70180_af.func_187225_a(CLIMBING) & 1) != 0;
   }

   public void setBesideClimbableBlock(boolean climbing) {
      byte b0 = (Byte)this.field_70180_af.func_187225_a(CLIMBING);
      if (this.func_70638_az() != null) {
         if (!this.func_70685_l(this.func_70638_az())) {
            if (this.func_70068_e(this.func_70638_az()) < 100.0) {
               b0 = (byte)(b0 & -2);
               this.field_70180_af.func_187227_b(CLIMBING, b0);
               return;
            }
         } else if (this.func_70638_az().field_70163_u + 1.0 < this.field_70163_u) {
            b0 = (byte)(b0 & -2);
            this.field_70180_af.func_187227_b(CLIMBING, b0);
            return;
         }
      }

      if (climbing) {
         b0 = (byte)(b0 | 1);
      } else {
         b0 = (byte)(b0 & -2);
      }

      this.field_70180_af.func_187227_b(CLIMBING, b0);
   }

   public boolean func_70617_f_() {
      return this.isBesideClimbableBlock();
   }

   protected PathNavigate func_175447_b(World worldIn) {
      return new PathNavigateClimberStatus(this, worldIn);
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
         this.maybeLaunchTarget(entityIn);
      }

      return flag;
   }

   @Override
   public void func_180430_e(float distance, float damageMultiplier) {
   }

   public float func_70047_e() {
      return 3.5F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.GANRO_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.GANRO_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.GANRO_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.ganroOrbEffects, mobs);
      }

      return flag;
   }

   @Override
   public void func_70106_y() {
      if (this.tendril1 != null) {
         this.field_70170_p.func_72973_f(this.tendril1);
      }

      if (this.tendril2 != null) {
         this.field_70170_p.func_72973_f(this.tendril2);
      }

      super.func_70106_y();
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      if (this.borderOrb != 0) {
         return false;
      } else {
         this.up = true;
         this.attackTimer = 0.0F;
         this.field_70170_p.func_72960_a(this, (byte)12);
         boolean flag = false;
         this.func_184185_a(SRPSounds.SWIPE, 2.0F, 1.0F);
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               entityIn.field_70165_t,
               entityIn.field_70163_u,
               entityIn.field_70161_v,
               entityIn.field_70165_t + 1.0,
               entityIn.field_70163_u + 1.0,
               entityIn.field_70161_v + 1.0
            )
            .func_186662_g(2.0);

         for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (mob instanceof EntityParasiteBase) {
               if (this.func_70638_az() == mob) {
                  this.func_70624_b(null);
                  return false;
               }
            } else if (mob != this && this.func_70685_l(mob) && this.func_70652_k(mob)) {
               flag = true;
            }
         }

         return flag;
      }
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70146_Z.nextBoolean()) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, this, 80, 0);
      }

      return this.func_70097_a(source, amount * 3.0F);
   }

   @Override
   public void setBodyPartDead(int id) {
      if (this.tendril1.getId() == id) {
         this.tendril1.func_70106_y();
      } else if (this.tendril2.getId() == id) {
         this.tendril2.func_70106_y();
      }
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

   @SideOnly(Side.CLIENT)
   public float getAttackTimer() {
      return this.attackTimer;
   }

   @SideOnly(Side.CLIENT)
   public byte getLeft() {
      return this.left1;
   }

   @SideOnly(Side.CLIENT)
   public byte getRight() {
      return this.right2;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 11) {
         this.left1 = 0;
      } else if (id == 12) {
         this.up = true;
         this.attackTimer = 0.0F;
      } else if (id == 22) {
         this.right2 = 0;
      } else if (id == 100) {
         for (int i = 0; i <= 1; i++) {
            this.spawnParticles(EnumParticleTypes.FLAME);
         }
      } else {
         super.func_70103_a(id);
      }
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillCharge;
         case 2:
            return this.skillshockwave;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillCharge = in;
            return;
         case 2:
            this.skillshockwave = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.charge();
            return;
         case 2:
            this.shockwave();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void charge() {
      this.attacking++;
      this.miniCapA = true;
      if (this.attacking < 20) {
         this.field_70170_p.func_72960_a(this, (byte)100);
         if (this.attacking == 2) {
            float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F + 2.0F;
            this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0F, v);
         }

         EntityLivingBase entitylivingbase = this.func_70638_az();
         if (entitylivingbase == null
            || !this.field_70122_E
            || this.func_70090_H()
            || entitylivingbase.field_70163_u > this.field_70163_u && entitylivingbase.field_70122_E) {
            this.skillCharge = true;
            this.attacking = 0;
            this.miniCapA = false;
            this.setParasiteStatus(0);
            return;
         }

         if (!entitylivingbase.func_70089_S()) {
            this.skillCharge = true;
            this.attacking = 0;
            this.miniCapA = false;
            this.setParasiteStatus(0);
            return;
         }

         if (this.attacking <= 19) {
            double dis = this.func_70032_d(entitylivingbase);
            this.setParasiteStatus(3);
            this.func_70661_as().func_75499_g();
            this.targetX = this.field_70165_t + 15.0 * (entitylivingbase.field_70165_t - this.field_70165_t) / dis;
            this.targetY = this.field_70163_u + 15.0 * (entitylivingbase.field_70163_u - this.field_70163_u) / dis;
            this.targetZ = this.field_70161_v + 15.0 * (entitylivingbase.field_70161_v - this.field_70161_v) / dis;
         }
      }

      if (this.attacking == 20) {
         this.func_70661_as().func_75492_a(this.targetX, this.targetY, this.targetZ, 3.0);
      }

      if (this.attacking >= 20) {
         for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(2.0, 0.0, 2.0))) {
            if (mob != this && !(mob instanceof EntityParasiteBase)) {
               float f = (float)MathHelper.func_181159_b(mob.field_70161_v - this.field_70161_v, mob.field_70165_t - this.field_70165_t);
               EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, f, this, 1.0F, false, 0.5F);
               this.field_70170_p.func_72838_d(damage);
            }
         }
      }

      this.skillBreakBlocks();
      if (!this.field_70122_E) {
         this.field_70159_w *= 0.7;
         this.field_70179_y *= 0.7;
      }

      if (this.attacking >= 60 && this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
         this.attacking = 0;
         this.miniCapA = false;
         this.skillCharge = true;
         this.setParasiteStatus(2);
      }
   }

   private void shockwave() {
      this.setParasiteStatus(100);
      this.func_70661_as().func_75499_g();
      if (this.border == 0) {
         float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F + 2.0F;
         this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0F, v);
         this.border++;
      } else {
         if (this.border <= 2) {
            this.field_70170_p.func_72960_a(this, (byte)100);
         }

         if (this.field_70173_aa % 20 == 0) {
            this.border++;
            if (this.func_70638_az() == null) {
               this.skillshockwave = true;
               this.setParasiteStatus(0);
               this.border = 0;
            } else {
               if (this.border == 2) {
                  this.spawnShock();
                  this.up = true;
                  this.attackTimer = 0.0F;
                  this.field_70170_p.func_72960_a(this, (byte)12);
                  this.func_184185_a(SRPSounds.SWIPE, 2.0F, 1.0F);
               }

               if (this.border > 3) {
                  this.skillshockwave = true;
                  this.setParasiteStatus(0);
                  this.border = 0;
               }
            }
         }
      }
   }

   private void spawnShock() {
      EntityWaveShock wa = new EntityWaveShock(this.field_70170_p, this);
      wa.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      if (!wa.field_70170_p.func_184144_a(wa, wa.func_174813_aQ()).isEmpty()) {
         wa.func_70106_y();
         this.skillshockwave = true;
         this.setParasiteStatus(0);
         this.border = 0;
      } else {
         wa.setDamages(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.3, this.MiniDamage, 1, 60);
         this.field_70170_p.func_72838_d(wa);
         wa.func_70624_b(this.func_70638_az());
      }
   }
}
