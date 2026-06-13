/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyBase;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyBS2
extends WorldGenParasiteColonyBase {
    private IBlockState floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    private IBlockState tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
    private IBlockState wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));

    public WorldGenParasiteColonyBS2(boolean notify, int stage) {
        super(notify, stage);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        int hz;
        int hx;
        int i;
        BlockPos enter = posss;
        this.replaceCircleGround(worldIn, posss.func_177977_b(), 12, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(2), 8, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(3), 8, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        int missing = 40;
        int radius = 7;
        double theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        int height = 28;
        int xx = 1;
        int zz = 2;
        int tic = 1;
        int cool = 1;
        int changeX = 0;
        int changeZ = 0;
        for (i = 0; i < height; ++i) {
            --changeZ;
            if (worldIn.field_73012_v.nextInt(2) == 0 && --changeX <= 0) {
                if (worldIn.field_73012_v.nextInt(2) == 0) {
                    xx = Math.min(2, xx + 1);
                    zz = Math.min(1, zz + 1);
                } else {
                    xx = Math.max(1, xx - 1);
                    zz = Math.max(1, zz - 1);
                }
                changeX = cool;
            }
            this.generateCircle(SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = enter;
        radius = 7;
        theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        height = 47;
        xx = 1;
        zz = 1;
        tic = 1;
        cool = 1;
        changeX = 0;
        changeZ = 0;
        for (i = 0; i < height; ++i) {
            --changeZ;
            if (worldIn.field_73012_v.nextInt(2) == 0 && --changeX <= 0) {
                if (worldIn.field_73012_v.nextInt(2) == 0) {
                    xx = Math.min(2, xx + 1);
                    zz = Math.min(1, zz + 1);
                } else {
                    xx = Math.max(1, xx - 1);
                    zz = Math.max(1, zz - 1);
                }
                changeX = cool;
            }
            this.generateCircle(SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = enter;
        radius = 2;
        theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        height = 17;
        xx = 1;
        zz = 1;
        tic = 1;
        cool = 1;
        changeX = 0;
        changeZ = 0;
        for (i = 0; i < height; ++i) {
            --changeZ;
            if (worldIn.field_73012_v.nextInt(2) == 0 && --changeX <= 0) {
                if (worldIn.field_73012_v.nextInt(2) == 0) {
                    xx = Math.min(2, xx + 1);
                    zz = Math.min(1, zz + 1);
                } else {
                    xx = Math.max(1, xx - 1);
                    zz = Math.max(1, zz - 1);
                }
                changeX = cool;
            }
            this.generateCircle(SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        return true;
    }
}

