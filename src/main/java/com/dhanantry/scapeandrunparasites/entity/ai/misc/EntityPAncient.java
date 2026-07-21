package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public abstract class EntityPAncient extends EntityPMalleable {
   public EntityPAncient(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, false, false, null, SRPConfig.preeminentSneakPen, SRPConfig.preeminentInviPen
            )
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  !SRPConfigSystems.useOneMind,
                  !SRPConfigSystems.useOneMind,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !(entity instanceof EntityVillager)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.preeminentSneakPen,
                  SRPConfig.preeminentInviPen
               )
            );
      }

      this.field_70728_aV = SRPAttributes.XP_ADAPTED;
      this.canD = SRPConfig.ancientdespawn;
      this.damageCap = SRPConfig.ancientCap;
      this.canModRender = 1;
      this.killcount = -10.0;
      this.field_70158_ak = true;
      this.pointCap = SRPConfig.ancientPointCap;
      this.pointReduction = SRPConfig.ancientPointRed;
      this.chanceLearn = SRPConfig.ancientChanceLe;
      this.chanceLearnFire = SRPConfig.ancientChanceLeFire;
      this.DamageTypeCap = SRPConfig.ancientPointDamCap;
      this.MiniDamage = SRPConfig.ancientMinDamage;
      this.regen = SRPConfig.ancientRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.ancientOneMindDeathV;
      this.valueEvDeath = SRPConfig.ancientLoosingEPValue;
      this.setScentHPMultiplier(0.25F);
   }

   @Override
   protected void fearPlayer(EntityLivingBase player) {
      try {
         if (player == null) {
            return;
         }

         if (!this.func_70685_l(player)) {
            return;
         }

         if (!player.func_70644_a(SRPPotions.FEAR_E)) {
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, 300, 3, false, false));
         } else if (player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() < 3) {
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, 300, 3, false, false));
         }
      } catch (Exception var3) {
      }
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 100.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
      }

      return flag;
   }
}
