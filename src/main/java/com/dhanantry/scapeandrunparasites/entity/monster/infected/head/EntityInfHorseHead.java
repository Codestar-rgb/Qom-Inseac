package com.dhanantry.scapeandrunparasites.entity.monster.infected.head;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAvoidEntityStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAvoidOrAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHorse;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityInfHorseHead extends EntityPInfected {
   public EntityInfHorseHead(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.7F, 0.9F);
      this.killcount = -10.0;
      this.attackSpeedT = 15;
   }

   @Override
   public int getParasiteIDRegister() {
      return 45;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infhorseCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISkill(this, 40, 100, 3, true, 14));
      this.setskillLeapValues(0.7F, 2.5, 0);
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.4F));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
      this.field_70714_bg.func_75776_a(4, new EntityAIAvoidOrAttack(this, 0.5F, 10, 2));
      this.field_70714_bg
         .func_75776_a(
            5,
            new EntityAIAvoidEntityStatus(
               this,
               EntityLivingBase.class,
               new Predicate<EntityLivingBase>() {
                  public boolean apply(@Nullable EntityLivingBase entity) {
                     return !(entity instanceof EntityWaterMob)
                        && !(entity instanceof EntityCreeper)
                        && !(entity instanceof EntityParasiteBase)
                        && !(entity instanceof EntityAnimal);
                  }
               },
               8.0F,
               1.3
            )
         );
      this.field_70715_bh.func_75776_a(5, new EntityAINearestAttackableTarget(this, EntityInhooM.class, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFHORSE_HEADHEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFHORSE_HEADDAMAGE);
   }

   public void func_70110_aj() {
   }

   public float func_70047_e() {
      return 0.8F;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      if (entityIn instanceof EntityInhooM && entityIn.func_70089_S() && this.func_70089_S()) {
         ParasiteEventEntity.spawnNext(this, new EntityInfHorse(this.field_70170_p), true, false);
         ((EntityParasiteBase)entityIn).particleStatus((byte)7);
         entityIn.func_70106_y();
         return true;
      } else {
         return super.func_70652_k(entityIn);
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K
         && SRPConfigSystems.disloGiveBodies
         && this.func_70089_S()
         && this.srpTicks == 10
         && SRPSaveData.get(this.field_70170_p, 44).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 20) >= 1) {
         ParasiteEventEntity.spawnNext(this, new EntityInfHorse(this.field_70170_p), true, false);
      }
   }

   @Override
   public boolean func_70686_a(Class<? extends EntityLivingBase> cls) {
      if (cls != EntityPlayer.class && cls != EntityPlayerMP.class) {
         String name = null;

         try {
            name = EntityList.func_191306_a(cls).toString();
         } catch (Exception var4) {
            return true;
         }

         if (name == null) {
            return true;
         } else {
            return name.contains("srparasites") && cls != EntityInhooM.class
               ? false
               : !SRPConfig.mobAttackingFull || !ParasiteEventEntity.checkName(name, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
         }
      } else {
         return true;
      }
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      super.func_180430_e(distance, damageMultiplier * 0.3F);
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDHEAD_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.INFECTEDHEAD_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.INFECTEDHEAD_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
   }

   protected SoundEvent getStepSound() {
      return SRPSounds.SMALL_STEPS;
   }
}
