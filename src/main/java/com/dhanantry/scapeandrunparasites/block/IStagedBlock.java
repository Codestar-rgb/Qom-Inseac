/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.IBlockState
 */
package com.dhanantry.scapeandrunparasites.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;

public interface IStagedBlock {
    public PropertyInteger getStageProperty();

    default public IBlockState withStage(IBlockState base, int stage) {
        PropertyInteger prop = this.getStageProperty();
        if (prop != null && base.func_177227_a().contains(prop)) {
            return base.func_177226_a((IProperty)prop, (Comparable)Integer.valueOf(stage));
        }
        return base;
    }
}

