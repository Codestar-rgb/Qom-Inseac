/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.potion.SPEffectBase;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class PotionCorrosion
extends SPEffectBase {
    public PotionCorrosion(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (entity.field_70170_p.field_72995_K) {
            return;
        }
        this.effectCorrosive(entity, amplifier);
    }

    private void effectCorrosive(EntityLivingBase entity, int amplifier) {
        ArrayList<ItemStack> off = new ArrayList<ItemStack>();
        Iterable gear = entity.func_184193_aE();
        if (gear != null && !gear.equals(Collections.emptyList())) {
            for (ItemStack part : gear) {
                if (part.func_190926_b() || !part.func_77984_f()) continue;
                off.add(part);
            }
            if (!off.isEmpty()) {
                for (ItemStack part : off) {
                    if (!((double)part.func_77958_k() * SPConfigSystems.corrNot < (double)(part.func_77958_k() - part.func_77952_i()))) continue;
                    part.func_77972_a(SPConfigSystems.corroValue, entity);
                }
            }
        }
    }
}

