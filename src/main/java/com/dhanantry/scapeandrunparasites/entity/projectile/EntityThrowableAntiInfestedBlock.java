/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.block.BlockInfestedRubble;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityThrowableAntiInfestedBlock
extends EntityThrowable {
    public EntityThrowableAntiInfestedBlock(World worldIn) {
        super(worldIn);
    }

    public EntityThrowableAntiInfestedBlock(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityThrowableAntiInfestedBlock(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    protected void func_70184_a(RayTraceResult result) {
        if (result.field_72308_g != null) {
            result.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), 0.0f);
        }
        if (!this.field_70170_p.field_72995_K) {
            int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
            double l1 = this.field_70165_t;
            double i2 = this.field_70161_v;
            boolean flag = false;
            int offsetT = 5;
            int range = 7;
            for (int k2 = -1 * range; k2 <= 1 * range; ++k2) {
                for (int l2 = -1 * range; l2 <= 1 * range; ++l2) {
                    for (int j = -1 * offsetT; j <= 1 * offsetT; ++j) {
                        double i3 = l1 + (double)k2;
                        double k = i1 + j;
                        double l = i2 + (double)l2;
                        BlockPos blockpos = new BlockPos(i3, k, l);
                        IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                        Block block = iblockstate.func_177230_c();
                        if (block == SRPBlocks.InfestedStain) {
                            this.field_70170_p.func_175656_a(blockpos, SRPBlocks.InfestedStain.func_176223_P().func_177226_a((IProperty)BlockInfestedStain.STAGE, (Comparable)Integer.valueOf(5)));
                            this.field_70170_p.func_175654_a(blockpos, SRPBlocks.InfestedStain.func_176223_P().func_177226_a((IProperty)BlockInfestedStain.STAGE, (Comparable)Integer.valueOf(5)).func_177230_c(), 40, 5);
                        }
                        if (block != SRPBlocks.InfestedRubble) continue;
                        this.field_70170_p.func_175656_a(blockpos, SRPBlocks.InfestedRubble.func_176223_P().func_177226_a((IProperty)BlockInfestedRubble.STAGE, (Comparable)Integer.valueOf(5)));
                        this.field_70170_p.func_175654_a(blockpos, SRPBlocks.InfestedRubble.func_176223_P().func_177226_a((IProperty)BlockInfestedRubble.STAGE, (Comparable)Integer.valueOf(5)).func_177230_c(), 40, 5);
                    }
                }
            }
            this.func_70106_y();
        } else {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
        }
    }
}

