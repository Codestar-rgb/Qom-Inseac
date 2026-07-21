package com.dhanantry.scapeandrunparasites.entity.monster.infected.special;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanPullMobs;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAssimara;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectilePullball;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.MsgQlipShake;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySpeBear extends EntityPAssimara implements EntityCanPullMobs {
   private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntitySpeBear.class, DataSerializers.field_187192_b);
   private EntityLivingBase targetedEntity;
   private int pulling;
   private boolean canPull;
   private boolean leapTo;
   private int border;
   private boolean skillpulling;

   public EntitySpeBear(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.3F, 1.4F);
      this.canModRender = 0;
      this.type = 14;
      this.field_70714_bg.func_85156_a(this.folow);
      this.killcount = -10.0;
      this.field_70138_W = 1.0F;
      this.field_70158_ak = true;
      this.canPull = true;
   }

   @Override
   public int getIDSpawn() {
      return 49;
   }

   @Override
   public int getParasiteIDRegister() {
      return 330;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infbearCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.2, false, 0.0));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 40, 300, 5, true, 1, true));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 5.0F, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.MARBEAR_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.MARBEAR_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.MARBEAR_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.MARBEAR_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(TARGET_ENTITY, 0);
   }

   public void func_184206_a(DataParameter<?> key) {
      super.func_184206_a(key);
      if (TARGET_ENTITY.equals(key)) {
         this.targetedEntity = null;
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K) {
         if (!this.canPull) {
            this.pulling--;
            if (this.pulling == 0) {
               this.canPull = true;
            }
         }

         if (this.func_70638_az() != null) {
            if (!this.func_70638_az().func_70089_S()) {
               this.func_70624_b(null);
               this.setTargetedEntity(0);
            } else if (this.func_70685_l(this.func_70638_az())
               && this.func_70068_e(this.func_70638_az()) > 0.0
               && this.canPull
               && this.getTargetedEntity() != null) {
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 1, false, false));
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 1, false, false));
               this.func_70671_ap().func_75651_a(this.getTargetedEntity(), 30.0F, 30.0F);
               if (this.srpTicks == 5 || this.srpTicks == 15) {
                  this.lookAt(this.getTargetedEntity());
               }

               this.attackEntityAsMobMinimum(this.func_70638_az(), 0.01F);
               this.setParasiteStatus(3);
               if (this.srpTicks == 10 && this.func_70638_az() instanceof EntityPlayerMP) {
                  SRPNetwork.CHANNEL.sendTo(new MsgQlipShake(250, 0, true, false, 4.0F), (EntityPlayerMP)this.func_70638_az());
               }

               this.pulling++;
               if (this.pulling > 200) {
                  this.setTargetedEntity(0);
                  this.canPull = false;
               }
            } else {
               this.setTargetedEntity(0);
            }
         } else {
            this.setTargetedEntity(0);
         }
      }

      this.field_70703_bu = false;
      if (this.getTargetedEntity() != null && this.func_70068_e(this.getTargetedEntity()) > 0.0) {
         EntityLivingBase target = this.getTargetedEntity();
         target.func_184210_p();
         double str = 0.3;
         double deltaX = this.field_70165_t - target.field_70165_t;
         double deltaY = this.field_70163_u - target.field_70163_u;
         double deltaZ = this.field_70161_v - target.field_70161_v;
         str = 0.1;
         double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
         if (distance == 0.0) {
            return;
         }

         deltaX /= distance;
         deltaY /= distance;
         deltaZ /= distance;
         target.field_70159_w += deltaX * str;
         target.field_70181_x += deltaY * str;
         target.field_70179_y += deltaZ * str;
      }
   }

   @Override
   protected void handleParasiteStatus() {
      int k = this.getParasiteStatus();
      if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2 || k == 3) {
         if (this.getAttackCooldownAni() != 0) {
            int i = this.getAttackCooldownAni() - 1;
            this.setAttackCooldownAni(i);
         }

         if (k == 1 || k == 2 || k == 3) {
            if (this.func_70638_az() != null) {
               if (!this.func_70638_az().func_70089_S()) {
                  this.func_70624_b(null);
                  this.setParasiteStatus(0);
               } else if (!this.canPull) {
                  this.setParasiteStatus(Math.min(k, 2));
               }
            } else {
               this.setParasiteStatus(0);
               this.func_70624_b(null);
            }
         }
      }
   }

   @Override
   public void setTargetedEntity(int entityId) {
      if (this.canPull || entityId == 0) {
         this.pulling = 0;
         this.canPull = true;
         this.field_70180_af.func_187227_b(TARGET_ENTITY, entityId);
      }
   }

   @Override
   public boolean hasTargetedEntity() {
      return !this.canPull ? false : (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
   }

   @Override
   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.field_70170_p.field_72995_K) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY));
            if (entity instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)entity;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.func_70638_az();
      }
   }

   @Override
   public void setPStatus(int in) {
      this.setParasiteStatus(in);
   }

   @Override
   public void setPullingMobEffects(EntityLivingBase mob) {
      mob.func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 1, false, false));
   }

   @Override
   public int getAcceleration() {
      return 1;
   }

   @Override
   public boolean checkAttackTarget(EntityLivingBase check) {
      return this.func_70638_az() == check;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && this.field_70146_Z.nextDouble() < SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
      }

      return flag;
   }

   public float func_70047_e() {
      return 1.3F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDBEAR_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.INFECTEDBEAR_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.INFECTEDBEAR_DEATH;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.field_187939_hm;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillpulling;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillpulling = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.pullingE();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void pullingE() {
      if (!this.field_70122_E || !this.canPull) {
         this.skillpulling = true;
         this.setParasiteStatus(0);
         this.border = 0;
      } else if (this.hasTargetedEntity()) {
         this.skillpulling = true;
         this.border = 0;
      } else {
         this.setParasiteStatus(11);
         this.func_70661_as().func_75499_g();
         if (this.border == 0) {
         }

         if (this.border <= 2) {
         }

         if (this.field_70173_aa % 20 == 0) {
            this.border++;
            if (this.func_70638_az() == null) {
               this.skillpulling = true;
               this.setParasiteStatus(0);
               this.border = 0;
            } else if (!this.func_70685_l(this.func_70638_az())) {
               this.skillpulling = true;
               this.setParasiteStatus(0);
               this.border = 0;
            } else {
               this.lookAt(this.func_70638_az());
               EntityLivingBase entitylivingbase = this.func_70638_az();
               Vec3d vec3d = this.func_70676_i(1.0F);
               double d2 = entitylivingbase.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
               double d4 = entitylivingbase.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
               double d3 = this.func_70638_az().func_174813_aQ().field_72338_b
                  + this.func_70638_az().field_70131_O / 4.0F
                  - (0.0 + this.field_70163_u + this.field_70131_O / 2.0F);
               EntityProjectilePullball entitylargefireball = new EntityProjectilePullball(this.field_70170_p, this, d2, d3, d4);
               entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
               entitylargefireball.field_70163_u = this.field_70163_u + this.func_70047_e() - 0.2;
               entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
               this.field_70170_p.func_72838_d(entitylargefireball);
               if (this.border > 6) {
                  this.skillpulling = true;
                  this.setParasiteStatus(0);
                  this.border = 0;
                  this.pulling = 60;
               }
            }
         }
      }
   }

   @Override
   public void resetPullSkill() {
      this.skillpulling = true;
      this.border = 0;
   }
}
