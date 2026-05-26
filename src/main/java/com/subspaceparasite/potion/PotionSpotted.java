/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.entity.ai.misc.EntityPAdapted;
import com.subspaceparasite.entity.ai.misc.EntityPInfected;
import com.subspaceparasite.entity.ai.misc.EntityPPrimitive;
import com.subspaceparasite.entity.ai.misc.EntityPPure;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.potion.SPEffectBase;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPWorldData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class PotionSpotted
extends SPEffectBase {
    public PotionSpotted(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (entity.field_70170_p.field_72995_K) {
            return;
        }
        this.effectSpotted(entity, amplifier);
    }

    private void effectSpotted(EntityLivingBase entity, int amplifier) {
        if (entity.field_70173_aa % 200 == 0) {
            EntityParasiteBase pin;
            EntityPlayer pa;
            if (entity instanceof EntityPlayer && ((pa = (EntityPlayer)entity).func_175149_v() || pa.func_184812_l_())) {
                return;
            }
            if (entity instanceof EntityParasiteBase || SPWorldData.get(entity.field_70170_p).nearestInfectionValue(entity.func_180425_c(), true) < SPConfigWorld.originSpotted) {
                entity.func_184596_c(SPPotions.SPOT_E);
                return;
            }
            int test = 32;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(entity.func_180425_c()).func_72314_b((double)test, 16.0, (double)test);
            List moblist = entity.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
            EntityParasiteBase entityParasiteBase = pin = moblist.isEmpty() ? null : (EntityParasiteBase)moblist.get(0);
            if (pin == null) {
                return;
            }
            List serverList = entity.field_70170_p.field_72996_f;
            int parasiteCount = 0;
            for (Entity value : serverList) {
                if (!(value instanceof EntityParasiteBase)) continue;
                ++parasiteCount;
            }
            if (parasiteCount >= SPConfig.worldMobCap) {
                return;
            }
            String[] mobs = new String[]{""};
            int min = 2;
            int max = 4;
            if (entity instanceof EntityPInfected) {
                mobs = new String[]{"subspaceparasite:sim_bigspider", "subspaceparasite:sim_human", "subspaceparasite:sim_cow", "subspaceparasite:sim_sheep", "subspaceparasite:sim_wolf", "subspaceparasite:sim_pig", "subspaceparasite:sim_villager", "subspaceparasite:sim_horse", "subspaceparasite:sim_bear", "subspaceparasite:sim_enderman"};
                min = 2;
                max = 4;
            } else if (entity instanceof EntityPPrimitive) {
                mobs = new String[]{"subspaceparasite:sim_bigspider", "subspaceparasite:sim_human", "subspaceparasite:sim_cow", "subspaceparasite:sim_sheep", "subspaceparasite:sim_wolf", "subspaceparasite:sim_pig", "subspaceparasite:sim_villager", "subspaceparasite:sim_horse", "subspaceparasite:sim_bear", "subspaceparasite:sim_enderman", "subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:pri_viscera"};
                min = 2;
                max = 3;
            } else if (entity instanceof EntityPAdapted) {
                mobs = new String[]{"subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:pri_viscera", "subspaceparasite:ada_longarms", "subspaceparasite:ada_manducater", "subspaceparasite:ada_reeker", "subspaceparasite:ada_yelloweye", "subspaceparasite:ada_summoner", "subspaceparasite:ada_bolster", "subspaceparasite:ada_arachnida", "subspaceparasite:ada_vermin", "subspaceparasite:ada_viscera"};
                min = 1;
                max = 3;
            } else if (entity instanceof EntityPPure) {
                mobs = new String[]{"subspaceparasite:ada_longarms", "subspaceparasite:ada_manducater", "subspaceparasite:ada_reeker", "subspaceparasite:ada_yelloweye", "subspaceparasite:ada_summoner", "subspaceparasite:ada_bolster", "subspaceparasite:ada_arachnida", "subspaceparasite:ada_vermin", "subspaceparasite:ada_viscera", "subspaceparasite:overseer", "subspaceparasite:vigilante", "subspaceparasite:warden", "subspaceparasite:bomber_light", "subspaceparasite:marauder", "subspaceparasite:monarch", "subspaceparasite:grunt"};
                min = 1;
                max = 2;
            }
            if (mobs[0].equals("")) {
                return;
            }
            ParasiteEventEntity.spawnUnitFromRof(entity.field_70170_p, entity, this.getRandomPosInCircle(entity.field_70170_p.field_73012_v, entity.func_180425_c(), 5.0), mobs, min, max);
        }
    }
}

