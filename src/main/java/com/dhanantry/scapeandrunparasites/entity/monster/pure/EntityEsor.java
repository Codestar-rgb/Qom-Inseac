package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvade;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityTendril;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyDead;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.PathNavigateClimberStatus;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityEsor extends EntityPPure implements EntityCutomAttack, EntityBodyParts {
   private float attackTimer;
   private boolean up;
   private EntityBody leftTendril;
   private EntityBody rightTendril;
   private float leftTendrilHealth;
   private float rightTendrilHealth;
   private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityEsor.class, DataSerializers.field_187191_a);
   private int border;
   private boolean skillEnraged;

   public EntityEsor(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.901F, 4.2F);
      this.field_70138_W = 1.0F;
      this.leftTendril = new EntityBody(this, 0.6F, 2.0F, 1.0F, 0.9F, 1.5F, 1, 1, true);
      this.rightTendril = new EntityBody(this, 0.6F, 2.0F, 1.0F, 0.9F, 1.5F, -1, 2, true);
      this.leftTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.rightTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.field_70158_ak = true;
      this.skillEnraged = false;
   }

   @Override
   public int getParasiteIDRegister() {
      return 50;
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISkill(this, 100, 100, 10, true, 14));
      this.setskillLeapValues(1.2F, 2.5, 7);
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 100, 7, true, 1));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.12));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 7));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 5.0));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvade(this, 50, 10, 4.0));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ESOR_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ESOR_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.255);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.ESOR_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.ESOR_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.up) {
         this.attackTimer = (float)(this.attackTimer + 0.2);
         if (this.attackTimer > 1.0F) {
            this.up = false;
         }
      } else {
         this.attackTimer = (float)(this.attackTimer - 0.1);
      }

      if (this.leftTendrilHealth > 0.0F) {
         this.leftTendril.func_70071_h_();
      }

      if (this.rightTendrilHealth > 0.0F) {
         this.rightTendril.func_70071_h_();
      }

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

   @Override
   public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
      if (this.border <= 0) {
         super.func_70624_b(entitylivingbaseIn);
      }
   }

   protected PathNavigate func_175447_b(World worldIn) {
      return new PathNavigateClimberStatus(this, worldIn);
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
         entityIn.field_70181_x += 0.5000000059604645;
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
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.ESOR_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.ESOR_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.ESOR_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.esorOrbEffects, mobs);
      }

      return flag;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SRPSounds.HEAVY_STEPS, 0.15F, 1.0F);
   }

   @Override
   public void func_70106_y() {
      if (this.leftTendril != null) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      }

      if (this.rightTendril != null) {
         this.field_70170_p.func_72973_f(this.rightTendril);
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
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else {
         boolean flag = this.func_70097_a(source, amount);
         if (!flag) {
            return false;
         } else {
            if (this.leftTendril.getId() == id) {
               this.leftTendrilHealth -= amount;
               if (this.leftTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(5);
                  tendril.func_82149_j(this.leftTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.leftTendril);
                  this.field_70170_p.func_72960_a(this, (byte)11);
                  this.cutResistances(SRPConfig.purePointDamCap / 2);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            } else if (this.rightTendril.getId() == id) {
               this.rightTendrilHealth -= amount;
               if (this.rightTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(5);
                  tendril.func_82149_j(this.rightTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.rightTendril);
                  this.field_70170_p.func_72960_a(this, (byte)22);
                  this.cutResistances(SRPConfig.purePointDamCap / 2);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            }

            return flag;
         }
      }
   }

   @Override
   public void setBodyPartDead(int id) {
      if (this.leftTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      } else if (this.rightTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      }
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
   public float getLeft() {
      return this.leftTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   public float getRight() {
      return this.rightTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 11) {
         this.leftTendrilHealth = 0.0F;
      } else if (id == 12) {
         this.up = true;
         this.attackTimer = 0.0F;
      } else if (id == 22) {
         this.rightTendrilHealth = 0.0F;
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
            return this.skillEnraged;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillEnraged = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.smash();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void smash() {
      this.miniCapA = true;
      if (!this.field_70122_E) {
         this.skillEnraged = true;
         this.setParasiteStatus(0);
         this.miniCapA = false;
         this.border = 0;
      } else {
         if (this.border == 2) {
            float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F + 2.0F;
            this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0F, v);
         }

         if (this.border < 20) {
            this.field_70170_p.func_72960_a(this, (byte)100);
            this.setParasiteStatus(25);
            this.func_70661_as().func_75499_g();
            this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 110, 100, false, false));
         }

         this.border++;
         if (this.border >= 20) {
            this.setParasiteStatus(3);
            if (this.border == 0) {
               this.func_184185_a(SRPSounds.SWIPE, 5.0F, 1.0F);
            }

            if (this.border % 7 == 0) {
               this.func_184185_a(SRPSounds.SWIPE, 5.0F, 1.0F);
            }

            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
               )
               .func_72314_b(6.0, 3.0, 6.0);

            for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
               if (mob != this && !(mob instanceof EntityParasiteBase) && this.func_70685_l(mob)) {
                  this.func_70652_k(mob);
                  mob.func_70653_a(mob, 2.0F, mob.field_70165_t - this.field_70165_t, mob.field_70161_v - this.field_70161_v);
               }
            }
         }

         if (this.border > 100) {
            this.skillEnraged = true;
            this.setParasiteStatus(0);
            this.miniCapA = false;
            this.border = 0;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
               )
               .func_72314_b(24.0, 5.0, 24.0);

            for (EntityParasiteBase mobx : this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
               if (mobx != this && this.func_70685_l(mobx) && SRPConfigSystems.rageEnable) {
                  mobx.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 1200, 1, false, false));
               }
            }
         }
      }
   }
}
