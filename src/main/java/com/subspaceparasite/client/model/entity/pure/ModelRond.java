/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity.pure;

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;

public class ModelRond
extends ModelSP {
    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        EntityParasiteBase parasite = (EntityParasiteBase)entityIn;
        byte i = parasite.getParasiteStatus();
        if (i == 0) {
            if (!parasite.getStillAni()) {
                float GS = 2.1f;
                float f = 0.3f;
            }
        } else if (!(i != 1 && i != 2 || parasite.getStillAni())) {
            float GS = 2.1f;
            float f = 0.3f;
        }
    }
}

