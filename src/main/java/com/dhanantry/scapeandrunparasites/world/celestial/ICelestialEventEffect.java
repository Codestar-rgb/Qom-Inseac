/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface ICelestialEventEffect {
    default public void onNightStart(World world, int dim, int phase, long nightIndex) {
    }

    default public void onNightEnd(World world, int dim, int phase, long nightIndex) {
    }

    default public void onParasiteSpawn(EntityParasiteBase parasite, @Nullable EntityLivingBase spawner) {
    }
}

