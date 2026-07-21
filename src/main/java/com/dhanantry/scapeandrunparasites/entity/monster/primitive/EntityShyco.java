package com.dhanantry.scapeandrunparasites.entity.monster.primitive;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPrimitive;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWaveShock;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShyco extends EntityPPrimitive implements EntityCutomAttack {
   private float attackTimer;
   private boolean up;
   private double extraDamage;
   private double currentDamage;
   private double hpLeft;
   private int border;
   private boolean skillshockwave;

   public EntityShyco(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 3.2F);
      this.extraDamage = this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
   }

   @Override
   public int getParasiteIDRegister() {
      return 1;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.095));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 100, (int)(SRPConfig.primitiveFollow * 0.7), 2, false, 1));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 2.5));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 2, 16));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.SHYCO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.SHYCO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.SHYCO_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.SHYCO_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
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
         this.attackTimer = (float)(this.attackTimer + 0.3);
         if (this.attackTimer > 2.0) {
            this.up = false;
         }
      } else {
         this.attackTimer = (float)(this.attackTimer - 0.15);
      }

      if (this.field_70146_Z.nextDouble() < this.hpLeft && this.field_70170_p.field_72995_K) {
         this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 106, 0);
      }

      if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0 && this.killcount > SRPConfig.adaptedKills && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this, new EntityShycoAdapted(this.field_70170_p), true, true);
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

   public float func_70047_e() {
      return 2.7F;
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

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag) {
         this.currentDamage = this.extraDamage * (1.0 - this.func_110143_aJ() / this.func_110138_aP() * SRPAttributes.SHYCO_I_DAMAGE);
      }

      this.hpLeft = 1.0F - this.func_110143_aJ() / this.func_110138_aP();
      return flag;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
         if (entityIn instanceof EntityLivingBase) {
            entityIn.func_70097_a(DamageSource.func_76358_a(this), (float)this.currentDamage);
            switch (this.getSkin()) {
               case 5:
                  SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
                  break;
               case 6:
                  SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 40, 0);
            }
         }

         this.maybeLaunchTarget(entityIn);
      }

      this.hpLeft = 1.0F - this.func_110143_aJ() / this.func_110138_aP();
      return flag;
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
            .func_186662_g(1.5);

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
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      this.particleStatus((byte)5);
      if (!this.field_70170_p.field_72995_K && this.killcount > SRPConfig.adaptedKills && !this.field_70128_L && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this, new EntityShycoAdapted(this.field_70170_p), true, true);
      }
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.SHYCO_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.SHYCO_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.SHYCO_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.shycoOrbEffects, mobs);
      }

      return flag;
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance
         || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant
         || this.canChangeVariant) {
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

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SRPSounds.MONSTER_STEP, 0.15F, 1.0F);
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimer() {
      return this.attackTimer;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 12) {
         this.up = true;
         this.attackTimer = 0.0F;
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
            return this.skillshockwave;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
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
            this.shockwave();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void shockwave() {
      if (!this.field_70122_E) {
         this.skillshockwave = true;
         this.setParasiteStatus(0);
         this.border = 0;
      } else {
         this.setParasiteStatus(10);
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
               } else if (!this.func_70685_l(this.func_70638_az())) {
                  this.skillshockwave = true;
                  this.setParasiteStatus(0);
                  this.border = 0;
               } else if (!(this.func_70638_az().field_70163_u >= this.field_70163_u + 4.0) && !(this.func_70638_az().field_70163_u < this.field_70163_u - 2.0)
                  )
                {
                  if (this.border == 4) {
                     this.spawnShock();
                     this.up = true;
                     this.attackTimer = 0.0F;
                     this.field_70170_p.func_72960_a(this, (byte)12);
                     this.func_184185_a(SRPSounds.SWIPE, 2.0F, 1.0F);
                  }

                  if (this.border > 5) {
                     this.skillshockwave = true;
                     this.setParasiteStatus(0);
                     this.border = 0;
                  }
               } else {
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
         wa.setDamages(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.3, this.MiniDamage, 1, 12);
         this.field_70170_p.func_72838_d(wa);
         wa.func_70624_b(this.func_70638_az());
      }
   }
}
