/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.world.WorldServer
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.potion.SPEffectBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

public class EffectDodSmokeTrail
extends SPEffectBase {
    public EffectDodSmokeTrail() {
        super("dod_smoke_trail", false, 0x404040, 0, 0);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (!(entity.field_70170_p instanceof WorldServer)) {
            return;
        }
        int remaining = entity.func_70660_b(SPPotions.DOD_SMOKE_TRAIL_E).func_76459_b();
        if (entity.field_70122_E && remaining <= 10) {
            entity.func_184596_c(SPPotions.DOD_SMOKE_TRAIL_E);
            return;
        }
        WorldServer ws = (WorldServer)entity.field_70170_p;
        double x = entity.field_70165_t;
        double y = entity.field_70163_u + (double)entity.func_70047_e();
        double z = entity.field_70161_v;
        ws.func_180505_a(EnumParticleTypes.SMOKE_NORMAL, false, x, y, z, 6, 0.15, 0.15, 0.15, 0.02, new int[0]);
    }

    @Override
    public boolean func_76397_a(int duration, int amplifier) {
        return true;
    }
}

