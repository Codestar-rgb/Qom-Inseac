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

import com.subspaceparasite.entity.EntityParasiticScent;
import com.subspaceparasite.potion.SPEffectBase;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class PotionPrey
extends SPEffectBase {
    public PotionPrey(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        EntityPlayer pa;
        if (entity.field_70170_p.field_72995_K) {
            return;
        }
        if (entity instanceof EntityPlayer && ((pa = (EntityPlayer)entity).func_175149_v() || pa.func_184812_l_())) {
            return;
        }
        if (entity.field_70173_aa % 40 == 0) {
            List serverList = entity.field_70170_p.field_72996_f;
            int count = 0;
            for (Entity listedEntity : serverList) {
                if (!(listedEntity instanceof EntityParasiticScent)) continue;
                ++count;
            }
            if (count > SPConfigSystems.scentCap) {
                return;
            }
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70165_t + 1.0, entity.field_70163_u + 1.0, entity.field_70161_v + 1.0).func_186662_g(64.0);
            List moblist = entity.field_70170_p.func_72872_a(EntityParasiticScent.class, axisalignedbb);
            for (EntityParasiticScent mob : moblist) {
                if (mob.getTargetToKill() != entity || !mob.getCanFollow()) continue;
                return;
            }
            byte phase = SPSaveData.get(entity.field_70170_p, 109).getEvolutionPhase(entity.field_70170_p.field_73011_w.getDimension());
            EntityParasiticScent nut = new EntityParasiticScent(entity.field_70170_p, 1, entity);
            nut.func_82149_j((Entity)entity);
            nut.setScentLife(SPConfigSystems.scentLifeObserver * 20);
            nut.increaseDanger(ParasiteEventEntity.getScentBonus(phase), true);
            nut.setScentReaction(ParasiteEventEntity.getScentReactionBonus(phase), false);
            nut.setCanFollow(true);
            entity.field_70170_p.func_72838_d((Entity)nut);
        }
    }
}

