/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.item;

import com.subspaceparasite.item.ItemCompass;
import com.subspaceparasite.world.SPWorldData;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CompassColony
extends ItemCompass {
    public static BlockPos orig;

    public CompassColony(String name) {
        super(name, 2);
    }

    @Override
    public BlockPos getOrigin(Entity entityIn, World worldIn) {
        SPWorldData aaa = SPWorldData.get(worldIn);
        return aaa.nearestColonyPosition(entityIn.func_180425_c(), true);
    }

    @Override
    public BlockPos getOri() {
        return orig;
    }
}

