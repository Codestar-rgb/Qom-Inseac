/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockColonyCore;
import com.subspaceparasite.block.BlockParasiteRubble;
import com.subspaceparasite.block.BlockParasiteRubbleDense;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBase;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyCore
extends WorldGenParasiteColonyBase {
    public WorldGenParasiteColonyCore(boolean notify, int stage) {
        super(notify, stage);
        this.wall = SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));
        this.tacle = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
        this.floor = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        int hz;
        int hx;
        int i;
        BlockPos enter = posss;
        this.replaceCircleGround(worldIn, posss.func_177977_b(), 12, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(2), 12, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(3), 12, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        int missing = 40;
        posss = posss.func_177979_c(7);
        int height = 12 + rand.nextInt(3);
        int xx = 2;
        int zz = 2;
        int tic = 6;
        int cool = 3;
        int changeX = 0;
        int changeZ = 0;
        for (int i2 = 0; i2 < height; ++i2) {
            --changeZ;
            if (worldIn.field_73012_v.nextInt(2) == 0 && --changeX <= 0) {
                if (worldIn.field_73012_v.nextInt(3) == 0) {
                    xx = Math.min(8, xx + 2);
                    zz = Math.min(8, zz + 2);
                } else {
                    xx = Math.max(5, xx - 1);
                    zz = Math.max(5, zz - 1);
                }
                changeX = cool;
            }
            this.generateCircle(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FLESH)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            int hx2 = xx - tic;
            int hz2 = zz - tic;
            this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx2, hz2, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        int aa = zz;
        int bonusH = rand.nextInt(5);
        height = 10 + rand.nextInt(5);
        int kil = 3;
        int sec = 2;
        double spa = height / sec;
        posss = posss.func_177979_c(3);
        this.generateDNAHelix(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, kil, sec, spa);
        this.generateDNAHelix(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.SACKFLESH)), worldIn, worldIn.field_73012_v, posss.func_177981_b(1), kil, sec, spa);
        this.generateDNAHelix(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), worldIn, worldIn.field_73012_v, posss.func_177981_b(2), kil, sec, spa);
        this.generateDNAHelix(Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss.func_177981_b(3), --kil, sec, spa);
        sec = 2;
        this.generateDNAHelix(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)), worldIn, worldIn.field_73012_v, posss, kil += 2, sec, spa);
        posss = posss.func_177981_b(height);
        this.generateSphere(worldIn, posss, 8, 6, rand, false, 6, false, 2, 1, 5, SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FLESH)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_150350_a.func_176223_P(), missing);
        BlockPos atm = posss = posss.func_177981_b(22);
        int radius = 7;
        double theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        height = 7;
        xx = 1;
        zz = 2;
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
            this.generateCircle(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = atm;
        radius = 7;
        theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        height = 27;
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
            this.generateCircle(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FLESH)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = atm;
        radius = 13;
        theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        height = 10;
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
            this.generateCircle(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = atm;
        posss = posss.func_177981_b(7);
        radius = 2;
        height = 8;
        xx = 1;
        zz = 2;
        tic = 1;
        cool = 1;
        changeX = 0;
        changeZ = 0;
        for (i = 0; i < height; ++i) {
            --changeZ;
            if (worldIn.field_73012_v.nextInt(2) == 0 && --changeX <= 0) {
                if (worldIn.field_73012_v.nextInt(2) == 0) {
                    xx = Math.min(3, xx + 1);
                    zz = Math.min(2, zz + 1);
                } else {
                    xx = Math.max(1, xx - 1);
                    zz = Math.max(1, zz - 1);
                }
                changeX = cool;
            }
            this.generateCircle(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = posss.func_177981_b(10);
        if (rand.nextBoolean()) {
            this.placeCore(worldIn, enter, 1);
            return true;
        }
        radius = 4;
        theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        height = 7;
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
            this.generateCircle(SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            hx = xx - tic;
            hz = zz - tic;
            this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 1, 5, SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        this.placeCore(worldIn, enter, 1);
        return true;
    }

    private void placeCore(World worldIn, BlockPos pos, int stage) {
        this.func_175903_a(worldIn, pos, SPBlocks.ColonyHeart.func_176223_P().func_177226_a((IProperty)BlockColonyCore.ACTIVE, (Comparable)Integer.valueOf(stage)));
    }
}

