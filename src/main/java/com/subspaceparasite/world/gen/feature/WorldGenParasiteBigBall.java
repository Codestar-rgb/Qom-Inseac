/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockParasiteTrunk;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.WorldGenCustomStructures;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteGenAbstract;
import com.subspaceparasite.world.gen.structure.WorldGenStructure;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteBigBall
extends WorldGenParasiteGenAbstract {
    public WorldGenParasiteBigBall(boolean notify) {
        super(notify);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        int i = 12;
        boolean flag = true;
        if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
            return false;
        }
        if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
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
            int extra = rand.nextInt(6) + 2;
            BlockPos current = position.func_177981_b(9 + extra);
            WorldGenCustomStructures.generateInPosition(new WorldGenStructure("ballbig"), new Random(), worldIn, current, 6, 0, 6);
            BlockPos center = new BlockPos(position.func_177958_n(), position.func_177956_o(), position.func_177952_p());
            boolean skip = true;
            for (int z = 0; z <= 3; ++z) {
                if (rand.nextDouble() <= 0.15 && skip) {
                    skip = false;
                    continue;
                }
                BlockPos root = center.func_177981_b(12 + extra);
                root = this.getDirectionRoot(root, z, 4);
                root = this.placeColumn(worldIn, root, 3, rand, 0.0);
                int direction = rand.nextInt(4);
                boolean glag = true;
                boolean side = false;
                while (glag) {
                    int grow = rand.nextInt(4) + 2;
                    root = this.directionToGrow(root.func_177977_b(), direction, side);
                    if (rand.nextDouble() <= 0.2) {
                        BlockPos atm = this.directionToGrow(root, direction, !side);
                        this.placeColumn(worldIn, this.directionToGrow(atm, direction, !side), grow, rand, 0.0);
                    }
                    root = this.placeColumn(worldIn, root, grow, rand, 0.0);
                    root = this.directionToGrow(root.func_177977_b(), direction, !side);
                    root = this.placeColumn(worldIn, root, rand.nextInt(2) + 2, rand, 0.0);
                    side = !side;
                    glag = worldIn.func_180495_p(root).func_185913_b();
                    direction = rand.nextInt(4);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private void placeTrunk(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, (Comparable)((Object)BlockParasiteTrunk.EnumType.TREE)));
    }

    private BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
        switch (direction) {
            case 0: {
                return center.func_177964_d(times);
            }
            case 1: {
                return center.func_177965_g(times);
            }
            case 3: {
                return center.func_177985_f(times);
            }
        }
        return center.func_177970_e(times);
    }

    private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
        int current;
        --in;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        while (current > atm - in) {
            this.placeTrunk(worldIn, newPos);
            newPos = newPos.func_177977_b();
            --current;
            ++times;
        }
        this.placeTrunk(worldIn, newPos);
        return newPos;
    }

    private BlockPos directionToGrow(BlockPos atm, int choice, boolean reverse) {
        if (reverse) {
            switch (choice) {
                case 0: {
                    atm = atm.func_177968_d();
                    break;
                }
                case 1: {
                    atm = atm.func_177976_e();
                    break;
                }
                case 3: {
                    atm = atm.func_177974_f();
                    break;
                }
                default: {
                    atm = atm.func_177978_c();
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

