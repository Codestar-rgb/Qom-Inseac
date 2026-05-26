/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityPStationaryArchitect;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAINexusNest
extends EntityAIBase {
    private final EntityPStationaryArchitect parent;
    private double tickss;

    public EntityAINexusNest(EntityPStationaryArchitect venkrol) {
        this.parent = venkrol;
        this.tickss = 20.0;
    }

    public boolean func_75250_a() {
        return true;
    }

    public void func_75246_d() {
    }
}

