/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteGenAbstract;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTenFlower
extends WorldGenParasiteGenAbstract {
    public WorldGenParasiteTenFlower(boolean notify) {
        super(notify);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        int i = 12;
        boolean flag = true;
        if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
            return false;
        }
        if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
            int dir;
            int zs;
            int xs;
            for (int j = position.func_177956_o(); j <= position.func_177956_o() + 1 + i; ++j) {
                int k = 4;
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
                            this.placeTrunk(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs));
                        }
                    }
                }
            }
            current = position;
            lag = rand.nextInt(2) + 1;
            for (int yyy = 0; yyy <= lag; ++yyy) {
                for (xs = -1; xs <= 1; ++xs) {
                    for (zs = -1; zs <= 1; ++zs) {
                        this.placeTrunk(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs));
                    }
                }
                current = current.func_177984_a();
            }
            BlockPos helper = current = current.func_177977_b();
            int times = 0;
            int dirHelper = 0;
            for (dir = 0; dir <= 3; ++dir) {
                helper = current;
                helper = this.getDirectionRoot(helper, dir, 2);
                this.placeTrunk(worldIn, helper);
            }
            current = current.func_177984_a();
            for (dir = 0; dir <= 3; ++dir) {
                helper = current;
                lag = rand.nextInt(2) + 3;
                for (times = 1; times <= lag; ++times) {
                    helper = this.getDirectionRoot(helper, dir, 1);
                    this.placeTrunk(worldIn, helper);
                }
                helper = helper.func_177984_a();
                this.placeTrunk(worldIn, helper);
                helper = this.getDirectionRoot(helper, dir, 1);
                this.placeTrunk(worldIn, helper);
                BlockPos posHelper0 = helper;
                for (int help = 1; help <= 3; help += 2) {
                    BlockPos posHelper1;
                    int firrr;
                    helper = posHelper0;
                    dirHelper = (dir + help) % 4;
                    helper = this.getDirectionRoot(helper, dirHelper, 1);
                    helper = helper.func_177984_a();
                    BlockPos posHelper2 = helper = this.placeColumn(worldIn, helper, rand.nextInt(3) + 1, rand, 0.0);
                    int n = firrr = help == 1 && (dir == 0 || dir == 3) ? 0 : 1;
                    if (dir == 2 && help == 3) {
                        firrr = 0;
                    }
                    if (dir == 1 && help == 3) {
                        firrr = 0;
                    }
                    helper = posHelper2;
                    helper = this.directionToGrow(helper.func_177977_b(), dir * 10 + firrr, true);
                    helper = posHelper1 = (helper = this.placeColumn(worldIn, helper, rand.nextInt(3) + 1, rand, 0.0));
                    helper = this.directionToGrow(helper, dir * 10 + firrr, true);
                    helper = this.placeColumn(worldIn, helper.func_177977_b(), rand.nextInt(3) + 1, rand, 0.0);
                }
            }
            current = this.placeColumn(worldIn, current, rand.nextInt(4) + 4, rand, 0.0);
            for (dir = 0; dir <= 3; ++dir) {
                helper = this.placeColumn(worldIn, this.getDirectionRoot(current, dir, 1), rand.nextInt(3) + 2, rand, 0.0);
                int glob = 3;
                helper = helper.func_177979_c(2);
                for (int kkk = 0; kkk <= glob; ++kkk) {
                    if (kkk == 0) {
                        this.placeColumn(worldIn, this.getDirectionRoot(helper, dir, 1), 1, rand, 0.0);
                        helper = helper.func_177984_a();
                    }
                    helper = this.getDirectionRoot(helper, dir, kkk == 0 ? 2 : 1);
                    helper = this.placeColumn(worldIn, helper, rand.nextInt(4) + 1, rand, 0.0);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private void placeTrunk(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)));
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

    private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
        int current;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        while (current < atm + in) {
            this.placeTrunk(worldIn, newPos);
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

