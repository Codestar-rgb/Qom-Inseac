/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 */
package com.dhanantry.scapeandrunparasites.item.hijacked;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public final class HijackedHitEffects {
    public static final int BLEED_TICKS = 100;
    public static final int BLEED_AMP = 0;
    public static final int RAGE_TICKS = 60;
    public static final int RAGE_AMP = 0;
    public static final float PARASITE_BONUS_DAMAGE = 3.0f;

    private HijackedHitEffects() {
    }

    public static void apply(EntityLivingBase attacker, EntityLivingBase target) {
        float max;
        if (target == null || target.field_70128_L) {
            return;
        }
        target.func_70690_d(new PotionEffect(SRPPotions.BLEED_E, 100, 0, false, true));
        if (target instanceof EntityParasiteBase) {
            DamageSource src = attacker instanceof EntityPlayer ? DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)attacker)) : DamageSource.func_76358_a((EntityLivingBase)attacker);
            target.func_70097_a(src, 3.0f);
        }
        if ((max = target.func_110138_aP()) > 0.0f && target.func_110143_aJ() <= max * 0.1f) {
            target.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 60, 0, false, true));
        }
    }
}

