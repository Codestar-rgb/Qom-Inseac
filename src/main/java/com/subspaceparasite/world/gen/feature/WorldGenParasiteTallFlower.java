/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.block.BlockParasiteTrunk;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteGenAbstract;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTallFlower
extends WorldGenParasiteGenAbstract {
    private IBlockState plant = SPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, (Comparable)((Object)BlockParasiteTrunk.EnumType.PLANT));
    private IBlockState petal = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
    private IBlockState base = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH));

    public WorldGenParasiteTallFlower(boolean notify) {
        super(notify);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        int i = 25;
        boolean flag = true;
        if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
            return false;
        }
        if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
            int dir;
            int zs;
            int xs;
            for (int j = position.func_177956_o(); j <= position.func_177956_o() + 1 + i; ++j) {
                int k = 2;
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
                for (int l = position.func_177958_n() - k; l <= position.func_177958_n() + k && flag; ++l) {
                    for (int i1 = position.func_177952_p() - k; i1 <= position.func_177952_p() + k && flag; ++i1) {
                        if (j >= 0 && j < worldIn.func_72800_K()) {
                            if (this.isReplaceable(worldIn, (BlockPos)blockpos$mutableblockpos.func_181079_c(l, j, i1))) continue;
                            flag = false;
                            continue;
                        }
                        flag = false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            int lag = 0;
            BlockPos current = position;
            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
                current = current.func_177977_b();
                for (int yyy = 0; yyy <= lag; ++yyy) {
                    for (xs = -1; xs <= 1; ++xs) {
                        for (zs = -1; zs <= 1; ++zs) {
                            this.placeBlock(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs), this.base);
                        }
                    }
                }
            }
            current = position;
            lag = rand.nextInt(2) + 1;
            for (int yyy = 0; yyy < lag; ++yyy) {
                for (xs = -1; xs <= 1; ++xs) {
                    for (zs = -1; zs <= 1; ++zs) {
                        this.placeBlock(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs), this.base);
                    }
                }
                current = current.func_177984_a();
            }
            BlockPos helper = current = current.func_177977_b();
            boolean times = false;
            boolean dirHelper = false;
            current = current.func_177984_a();
            this.placeBlock(worldIn, current, this.base);
            for (dir = 0; dir <= 3; ++dir) {
                helper = current;
                helper = this.getDirectionRoot(helper, dir, 1);
                this.placeColumn(worldIn, helper, 2, rand, 0.0, this.base);
            }
            current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            if (rand.nextInt(8) != 0) {
                current = this.getDirectionRoot(current, rand.nextInt(4), 1);
            }
            current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            if (rand.nextInt(8) != 0) {
                current = this.getDirectionRoot(current, rand.nextInt(4), 1);
            }
            current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            if (rand.nextInt(5) != 0) {
                if (rand.nextInt(8) != 0) {
                    current = this.getDirectionRoot(current, rand.nextInt(4), 1);
                }
                current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            }
            for (dir = 0; dir <= 3; ++dir) {
                helper = current;
                helper = this.getDirectionRoot(helper, dir, 1);
                this.placeBlock(worldIn, helper, this.petal);
            }
            current = this.placeColumn(worldIn, current, 1, rand, 0.0, this.petal);
            lag = 2;
            for (int yyy = 0; yyy < lag; ++yyy) {
                for (int xs2 = -1; xs2 <= 1; ++xs2) {
                    for (int zs2 = -1; zs2 <= 1; ++zs2) {
                        this.placeBlock(worldIn, new BlockPos(current.func_177958_n() + xs2, current.func_177956_o(), current.func_177952_p() + zs2), this.petal);
                    }
                }
                current = current.func_177984_a();
            }
            this.placeBlock(worldIn, current, this.petal);
            for (dir = 0; dir <= 3; ++dir) {
                helper = current;
                helper = this.getDirectionRoot(helper.func_177979_c(2), dir, 2);
                helper = this.placeColumn(worldIn, helper, 4, rand, 0.0, this.petal);
                helper = this.getDirectionRoot(helper, dir, 1);
                helper = this.placeColumn(worldIn, helper, 2, rand, 0.0, this.petal);
                helper = this.getDirectionRoot(helper, dir, 1);
                helper = this.placeColumn(worldIn, helper, 1, rand, 0.0, this.petal);
            }
            for (dir = 0; dir <= 3; ++dir) {
                helper = current;
                helper = this.getDirectionRoot(helper.func_177979_c(2), dir, 2);
                helper = this.directionToGrow(helper, (dir + 1) % 4, false);
                helper = this.directionToGrow(helper, (dir + 1) % 4, false);
                this.placeColumn(worldIn, helper, 3, rand, 0.0, this.petal);
            }
        } else {
            return false;
        }
        return true;
    }

    private void placeBlock(World worldIn, BlockPos pos, IBlockState state) {
        this.func_175903_a(worldIn, pos, state);
    }

    private BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
        switch (direction) {
            case 0: {
                return center.func_177964_d(times);
            }
            case 1: {
                return center.func_177965_g(times);
            }
            case 2: {
                return center.func_177970_e(times);
            }
        }
        return center.func_177985_f(times);
    }

    private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance, IBlockState state) {
        int current;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        while (current < atm + in) {
            this.placeBlock(worldIn, newPos, state);
            newPos = newPos.func_177984_a();
            ++current;
            ++times;
        }
        return newPos;
    }

    private BlockPos directionToGrow(BlockPos atm, int choice, boolean sideCurse) {
        atm = atm.func_177984_a();
        if (sideCurse) {
            switch (choice) {
                case 0: {
                    atm = atm.func_177978_c();
                    atm = atm.func_177974_f();
                    break;
                }
                case 1: {
                    atm = atm.func_177978_c();
                    atm = atm.func_177976_e();
                    break;
                }
                case 10: {
                    atm = atm.func_177974_f();
                    atm = atm.func_177978_c();
                    break;
                }
                case 11: {
                    atm = atm.func_177974_f();
                    atm = atm.func_177968_d();
                    break;
                }
                case 20: {
                    atm = atm.func_177968_d();
                    atm = atm.func_177974_f();
                    break;
                }
                case 21: {
                    atm = atm.func_177968_d();
                    atm = atm.func_177976_e();
                    break;
                }
                case 30: {
                    atm = atm.func_177976_e();
                    atm = atm.func_177978_c();
                    break;
                }
                default: {
                    atm = atm.func_177976_e();
                    atm = atm.func_177968_d();
                }
            }
            return atm;
        }
        switch (choice) {
            case 0: {
                atm = atm.func_177978_c();
                break;
            }
            case 1: {
                atm = atm.func_177974_f();
                break;
            }
            case 3: {
                atm = atm.func_177976_e();
                break;
            }
            default: {
                atm = atm.func_177968_d();
            }
        }
        return atm;
    }
}

