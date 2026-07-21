package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAdapted;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPrimitive;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class PotionSpotted extends SRPEffectBase {
   public PotionSpotted(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         this.effectSpotted(entity, amplifier);
      }
   }

   private void effectSpotted(EntityLivingBase entity, int amplifier) {
      if (entity.field_70173_aa % 200 == 0) {
         if (entity instanceof EntityPlayer) {
            EntityPlayer pa = (EntityPlayer)entity;
            if (pa.func_175149_v() || pa.func_184812_l_()) {
               return;
            }
         }

         if (entity instanceof EntityParasiteBase
            || SRPWorldData.get(entity.field_70170_p).nearestInfectionValue(entity.func_180425_c(), true) < SRPConfigWorld.originSpotted) {
            entity.func_184596_c(SRPPotions.SPOT_E);
            return;
         }

         int test = 32;
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(entity.func_180425_c()).func_72314_b(test, 16.0, test);
         List<EntityParasiteBase> moblist = entity.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
         EntityParasiteBase pin = moblist.isEmpty() ? null : moblist.get(0);
         if (pin == null) {
            return;
         }

         List<Entity> serverList = entity.field_70170_p.field_72996_f;
         int parasiteCount = 0;

         for (Entity value : serverList) {
            if (value instanceof EntityParasiteBase) {
               parasiteCount++;
            }
         }

         if (parasiteCount >= SRPConfig.worldMobCap) {
            return;
         }

         String[] mobs = new String[]{""};
         int min = 2;
         int max = 4;
         if (entity instanceof EntityPInfected) {
            mobs = new String[]{
               "srparasites:sim_bigspider",
               "srparasites:sim_human",
               "srparasites:sim_cow",
               "srparasites:sim_sheep",
               "srparasites:sim_wolf",
               "srparasites:sim_pig",
               "srparasites:sim_villager",
               "srparasites:sim_horse",
               "srparasites:sim_bear",
               "srparasites:sim_enderman"
            };
            min = 2;
            max = 4;
         } else if (entity instanceof EntityPPrimitive) {
            mobs = new String[]{
               "srparasites:sim_bigspider",
               "srparasites:sim_human",
               "srparasites:sim_cow",
               "srparasites:sim_sheep",
               "srparasites:sim_wolf",
               "srparasites:sim_pig",
               "srparasites:sim_villager",
               "srparasites:sim_horse",
               "srparasites:sim_bear",
               "srparasites:sim_enderman",
               "srparasites:pri_longarms",
               "srparasites:pri_manducater",
               "srparasites:pri_reeker",
               "srparasites:pri_yelloweye",
               "srparasites:pri_summoner",
               "srparasites:pri_bolster",
               "srparasites:pri_arachnida",
               "srparasites:pri_vermin",
               "srparasites:pri_viscera"
            };
            min = 2;
            max = 3;
         } else if (entity instanceof EntityPAdapted) {
            mobs = new String[]{
               "srparasites:pri_longarms",
               "srparasites:pri_manducater",
               "srparasites:pri_reeker",
               "srparasites:pri_yelloweye",
               "srparasites:pri_summoner",
               "srparasites:pri_bolster",
               "srparasites:pri_arachnida",
               "srparasites:pri_vermin",
               "srparasites:pri_viscera",
               "srparasites:ada_longarms",
               "srparasites:ada_manducater",
               "srparasites:ada_reeker",
               "srparasites:ada_yelloweye",
               "srparasites:ada_summoner",
               "srparasites:ada_bolster",
               "srparasites:ada_arachnida",
               "srparasites:ada_vermin",
               "srparasites:ada_viscera"
            };
            min = 1;
            max = 3;
         } else if (entity instanceof EntityPPure) {
            mobs = new String[]{
               "srparasites:ada_longarms",
               "srparasites:ada_manducater",
               "srparasites:ada_reeker",
               "srparasites:ada_yelloweye",
               "srparasites:ada_summoner",
               "srparasites:ada_bolster",
               "srparasites:ada_arachnida",
               "srparasites:ada_vermin",
               "srparasites:ada_viscera",
               "srparasites:overseer",
               "srparasites:vigilante",
               "srparasites:warden",
               "srparasites:bomber_light",
               "srparasites:marauder",
               "srparasites:monarch",
               "srparasites:grunt"
            };
            min = 1;
            max = 2;
         }

         if (mobs[0].equals("")) {
            return;
         }

         ParasiteEventEntity.spawnUnitFromRof(
            entity.field_70170_p, entity, this.getRandomPosInCircle(entity.field_70170_p.field_73012_v, entity.func_180425_c(), 5.0), mobs, min, max
         );
      }
   }
}
