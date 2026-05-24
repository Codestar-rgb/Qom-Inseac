/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockParasiteRubble;
import com.subspaceparasite.block.BlockParasiteRubbleDense;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBase;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyBS1
extends WorldGenParasiteColonyBase {
    private IBlockState floor = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    private IBlockState tacle = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
    private IBlockState wall = SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));

    public WorldGenParasiteColonyBS1(boolean notify, int stage) {
        super(notify, stage);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        int hz;
        int hx;
        int i;
        BlockPos enter = posss;
        this.replaceCircleGround(worldIn, posss.func_177977_b(), 12, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(2), 8, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(3), 8, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        int missing = 40;
        int radius = 4;
        double theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        int height = 8;
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
        return true;
    }

    private BlockPos placeWallsBottom(World worldIn, BlockPos position, int loop, Random rand, IBlockState state, int oY) {
        BlockPos currentP = position;
        int current = 0;
        while (current < loop) {
            BlockPos helpRoot = currentP;
            BlockPos helper = currentP;
            for (int i = 0; i <= 3; ++i) {
                int times;
                int o;
                BlockPos rootH = helper = this.getDirectionRoot(helpRoot, i, 2);
                for (o = 0; o < 1; ++o) {
                    for (times = 1; times <= 1; ++times) {
                        helper = o == 0 ? this.getDirectionRoot(rootH, (i + 1) % 4, times + 1) : this.getDirectionRoot(rootH, (i + 3) % 4, times + 1);
                        if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                            this.placeBlock(worldIn, helper.func_177977_b(), state);
                        }
                        this.placeBlock(worldIn, helper, state);
                    }
                }
                rootH = helper = this.getDirectionRoot(rootH, i, 1);
                for (o = 0; o < 2; ++o) {
                    for (times = 1; times <= 1; ++times) {
                        int llimitO;
                        int llimit;
                        BlockPos trunk;
                        int llimitO2;
                        BlockPos atm;
                        if (o == 0) {
                            helper = this.getDirectionRoot(rootH, (i + 1) % 4, times);
                            if (current == 2 && rand.nextInt(2) == 0) {
                                atm = helper;
                                atm = this.directionToGrow(atm, i, false);
                                this.placeBlock(worldIn, atm, this.tacle);
                                while (!worldIn.func_180495_p(atm.func_177977_b()).func_185913_b() && atm.func_177977_b().func_177956_o() >= 1) {
                                    atm = rand.nextInt(1) == 0 ? this.directionToGrow(atm, i * 10, true) : this.directionToGrow(atm, i, false);
                                    int llimit2 = atm.func_177956_o() < oY ? 5 : 2;
                                    llimitO2 = atm.func_177956_o() < oY ? 4 : 0;
                                    atm = this.placeColumn(worldIn, atm, rand.nextInt(llimit2) + (2 + llimitO2), rand, 0.0, this.tacle);
                                }
                                this.placeBlock(worldIn, atm.func_177977_b(), this.tacle);
                            }
                            if (current == loop - 3) {
                                trunk = helper;
                                if (worldIn.func_180495_p((trunk = this.directionToGrow(trunk, i, false)).func_177979_c(4)).func_177230_c() == Blocks.field_150350_a) {
                                    BlockPos atm2 = helper;
                                    atm2 = this.directionToGrow(atm2, i, false);
                                    this.placeBlock(worldIn, atm2, this.tacle);
                                    while (!worldIn.func_180495_p(atm2.func_177977_b()).func_185913_b() && atm2.func_177977_b().func_177956_o() >= 1) {
                                        atm2 = rand.nextInt(1) == 0 ? this.directionToGrow(atm2, i * 10, true) : this.directionToGrow(atm2, i, false);
                                        llimit = atm2.func_177956_o() < oY ? 5 : 2;
                                        llimitO = atm2.func_177956_o() < oY ? 4 : 0;
                                        atm2 = this.placeColumn(worldIn, atm2, rand.nextInt(llimit) + (2 + llimitO), rand, 0.0, this.tacle);
                                    }
                                    this.placeBlock(worldIn, atm2.func_177977_b(), this.tacle);
                                }
                            }
                        } else {
                            helper = this.getDirectionRoot(rootH, (i + 3) % 4, times);
                            if (current == 5 && times != 1 && rand.nextInt(2) == 0) {
                                atm = helper;
                                atm = this.directionToGrow(atm, i, false);
                                this.placeBlock(worldIn, atm, this.tacle);
                                while (!worldIn.func_180495_p(atm.func_177977_b()).func_185913_b() && atm.func_177977_b().func_177956_o() >= 1) {
                                    atm = rand.nextInt(1) == 0 ? this.directionToGrow(atm, i * 10 + 1, true) : this.directionToGrow(atm, i, false);
                                    int llimit3 = atm.func_177956_o() < oY ? 5 : 2;
                                    llimitO2 = atm.func_177956_o() < oY ? 4 : 0;
                                    atm = this.placeColumn(worldIn, atm, rand.nextInt(llimit3) + (2 + llimitO2), rand, 0.0, this.tacle);
                                }
                                this.placeBlock(worldIn, atm.func_177977_b(), this.tacle);
                            }
                            if (current == loop - 2 && times != 1) {
                                trunk = helper;
                                if (worldIn.func_180495_p((trunk = this.directionToGrow(trunk, i, false)).func_177979_c(5)).func_177230_c() == Blocks.field_150350_a) {
                                    BlockPos atm3 = helper;
                                    atm3 = this.directionToGrow(atm3, i, false);
                                    this.placeBlock(worldIn, atm3, this.tacle);
                                    while (!worldIn.func_180495_p(atm3.func_177977_b()).func_185913_b() && atm3.func_177977_b().func_177956_o() >= 1) {
                                        atm3 = rand.nextInt(1) == 0 ? this.directionToGrow(atm3, i * 10 + 1, true) : this.directionToGrow(atm3, i, false);
                                        llimit = atm3.func_177956_o() < oY ? 5 : 2;
                                        llimitO = atm3.func_177956_o() < oY ? 4 : 0;
                                        atm3 = this.placeColumn(worldIn, atm3, rand.nextInt(llimit) + (2 + llimitO), rand, 0.0, this.tacle);
                                    }
                                    this.placeBlock(worldIn, atm3.func_177977_b(), this.tacle);
                                }
                            }
                        }
                        if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                            this.placeBlock(worldIn, helper.func_177977_b(), state);
                        }
                        this.placeBlock(worldIn, helper, state);
                    }
                }
                helper = this.getDirectionRoot(rootH, i, 0);
                if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                    this.placeBlock(worldIn, helper.func_177977_b(), state);
                }
                this.placeBlock(worldIn, helper, state);
            }
            ++current;
            currentP = currentP.func_177984_a();
        }
        return currentP;
    }

    private BlockPos placeWallsTopIn(World worldIn, BlockPos position, int loop, boolean vine, Random rand, int longer, IBlockState state) {
        BlockPos currentP = position;
        int current = 0;
        while (current < loop) {
            BlockPos helpRoot = currentP;
            BlockPos helper = currentP;
            for (int i = 0; i <= 3; ++i) {
                int times;
                int o;
                BlockPos rootH = helper = this.getDirectionRoot(helpRoot, i, 3);
                for (o = 0; o < 2; ++o) {
                    for (times = 1; times <= 1; ++times) {
                        helper = o == 0 ? this.getDirectionRoot(rootH, (i + 1) % 4, times + 1) : this.getDirectionRoot(rootH, (i + 3) % 4, times + 1);
                        this.placeBlock(worldIn, helper, state);
                        if (!vine || current != 0 || !rand.nextBoolean()) continue;
                        this.addVines(worldIn, helper.func_177977_b(), rand, longer);
                    }
                }
                rootH = helper = this.getDirectionRoot(rootH, i, 1);
                for (o = 0; o < 2; ++o) {
                    for (times = 1; times <= 1; ++times) {
                        helper = o == 0 ? this.getDirectionRoot(rootH, (i + 1) % 4, times) : this.getDirectionRoot(rootH, (i + 3) % 4, times);
                        this.placeBlock(worldIn, helper, state);
                        if (!vine || current != 0 || !rand.nextBoolean()) continue;
                        this.addVines(worldIn, helper.func_177977_b(), rand, longer);
                    }
                }
                helper = this.getDirectionRoot(rootH, i, 0);
                this.placeBlock(worldIn, helper, state);
                if (!vine || current != 0 || !rand.nextBoolean()) continue;
                this.addVines(worldIn, helper.func_177977_b(), rand, longer);
            }
            ++current;
            currentP = currentP.func_177984_a();
        }
        return currentP;
    }
}

