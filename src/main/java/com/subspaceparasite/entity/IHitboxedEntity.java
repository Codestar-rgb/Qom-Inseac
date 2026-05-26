/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityMultiPart
 */
package com.subspaceparasite.entity;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.IEntityMultiPart;

public interface IHitboxedEntity
extends IEntityMultiPart {
    public EntityParasiteBase getParent();
}

