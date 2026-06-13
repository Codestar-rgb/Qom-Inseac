/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityMultiPart
 */
package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.IEntityMultiPart;

public interface IHitboxedEntity
extends IEntityMultiPart {
    public EntityParasiteBase getParent();
}

