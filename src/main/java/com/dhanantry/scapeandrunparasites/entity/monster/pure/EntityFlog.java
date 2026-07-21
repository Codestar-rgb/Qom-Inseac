package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvadeDash;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.PathNavigateClimberStatus;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFlog extends EntityPPure implements EntityCutomAttack {
   private float attackTimer;
   private boolean up;
   private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityFlog.class, DataSerializers.field_187191_a);

   public EntityFlog(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.7666F, 1.95F);
      this.borderOrb = -1;
      this.totalP = 0;
      this.adaptationCap = 0.95F;
      this.foodSteal *= 0.3F;
   }

   @Override
   public int getParasiteIDRegister() {
      return 60;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.12));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.5, false, 0.0, 3.0));
      this.setskillLeapValues(1.1F, 3.5, 0);
      this.field_70714_bg.func_75776_a(0, new EntityAISkill(this, 40, 100, 10, true, 14));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvadeDash(this, 20, 2, 4, 1.5, 15));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.FLOG_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.FLOG_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.274172325);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.FLOG_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.FLOG_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.up) {
         this.attackTimer += 0.2F;
         if (this.attackTimer > 1.5) {
            this.up = false;
         }
      } else {
         this.attackTimer -= 0.1F;
      }

      if (!this.field_70170_p.field_72995_K) {
         this.setBesideClimbableBlock(this.field_70123_F);
      }
   }

   @Override
   protected void func_82167_n(Entity entityIn) {
      super.func_82167_n(entityIn);
      if (!this.field_70170_p.field_72995_K) {
         if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
         }
      }
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
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

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && entityIn instanceof EntityLivingBase) {
         switch (this.getSkin()) {
            case 5:
               SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
               break;
            case 6:
               SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 40, 0);
         }
      }

      return flag;
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
      b0 = climbing ? (byte)(b0 | 1) : (byte)(b0 & -2);
      this.field_70180_af.func_187227_b(CLIMBING, b0);
   }

   public boolean func_70617_f_() {
      return this.isBesideClimbableBlock();
   }

   @Nonnull
   protected PathNavigate func_175447_b(@Nonnull World worldIn) {
      return new PathNavigateClimberStatus(this, worldIn);
   }

   public float func_70047_e() {
      return 1.73F;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.field_187939_hm;
   }

   protected void func_180429_a(@Nonnull BlockPos pos, @Nonnull Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimer() {
      return this.attackTimer;
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
         switch (this.field_70146_Z.nextInt(3)) {
            case 0:
               this.setSkin(5);
               break;
            case 1:
               this.setSkin(6);
               break;
            case 2:
               this.setSkin(7);
         }
      }

      return floo;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 12) {
         this.up = true;
         this.attackTimer = 0.0F;
      } else {
         super.func_70103_a(id);
      }
   }
}
