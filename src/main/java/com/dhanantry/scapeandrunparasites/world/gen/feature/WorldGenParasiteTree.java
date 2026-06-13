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

import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanister;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTreeAbstract;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTree
extends WorldGenParasiteTreeAbstract {
    public WorldGenParasiteTree(boolean notify) {
        super(notify);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        BlockPos current;
        int i = 20;
        boolean flag = true;
        if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
            return false;
        }
        if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
            for (int j = position.func_177956_o(); j <= position.func_177956_o() + 1 + i; ++j) {
                int k = 1;
                if (j == position.func_177956_o()) {
                    k = 0;
                }
                if (j >= position.func_177956_o() + 1 + i - 2) {
                    k = 2;
                }
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
            current = position;
            int first = rand.nextInt(4);
            current = this.directionToGrow(current, first, false);
            this.placeTrunk(worldIn, current);
            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
                current = current.func_177977_b();
                this.placeTrunk(worldIn, current);
            }
            current = position;
            current = this.directionToGrow(current, ++first, false);
            this.placeTrunk(worldIn, current);
            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
                current = current.func_177977_b();
                this.placeTrunk(worldIn, current);
            }
            current = position;
            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
                current = current.func_177977_b();
                this.placeTrunk(worldIn, current);
            }
            current = position;
            current = this.placeColumn(worldIn, current, rand.nextInt(3) + 5, rand, -1.0);
            first = rand.nextInt(4);
            current = this.directionToGrow(current, first, false);
            current = this.placeColumn(worldIn, current, rand.nextInt(3) + 5, rand, 0.3);
            if (rand.nextDouble() <= 0.3) {
                return true;
            }
            current = this.directionToGrow(current, rand.nextInt(4), false);
            current = this.placeColumn(worldIn, current, rand.nextInt(3) + 5, rand, 0.3);
            if (rand.nextDouble() <= 0.3) {
                return true;
            }
        } else {
            return false;
        }
        current = this.directionToGrow(current, rand.nextInt(4), false);
        current = this.placeColumn(worldIn, current, rand.nextInt(3) + 5, rand, 0.3);
        return true;
    }

    private void placeTrunk(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, (Comparable)((Object)BlockParasiteTrunk.EnumType.TREE)));
    }

    private void placeTrunk(World worldIn, BlockPos pos, int direction) {
        switch (direction) {
            case 1: {
                this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, (Comparable)((Object)BlockParasiteTrunk.EnumType.TREE)));
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
        }
    }

    private void placeCanister(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteCanister.func_176223_P().func_177226_a(BlockParasiteCanister.VARIANT, (Comparable)((Object)BlockParasiteCanister.EnumType.SAC)));
    }

    private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
        int current;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a && rand.nextDouble() <= 0.3 + extraChance) {
            this.placeCanister(worldIn, pos.func_177977_b());
        }
        while (current < atm + in) {
            if (times == 2 && extraChance != -1.0) {
                int btwo;
                int bone = rand.nextInt(4);
                if (bone == (btwo = rand.nextInt(4))) {
                    ++bone;
                }
                this.addBranchs(worldIn, newPos, rand, rand.nextInt(3) + 1, bone);
                this.addBranchs(worldIn, newPos, rand, rand.nextInt(3) + 1, btwo);
            }
            this.placeTrunk(worldIn, newPos);
            newPos = newPos.func_177984_a();
            ++current;
            ++times;
        }
        this.placeTrunk(worldIn, newPos);
        return newPos;
    }

    private void addBranchs(World worldIn, BlockPos pos, Random rand, int size, int direction) {
        pos = this.directionToGrow(pos, direction, false);
        this.placeTrunk(worldIn, pos);
        pos = pos.func_177984_a();
        pos = this.directionToGrow(pos, direction, false);
        int curve = direction * 10 + rand.nextInt(2);
        for (int current = 0; current < size; ++current) {
            this.placeTrunk(worldIn, pos);
            if (rand.nextDouble() <= 0.25) {
                this.placeCanister(worldIn, pos.func_177977_b());
            }
            pos = rand.nextDouble() <= 0.5 ? this.directionToGrow(pos, direction, false) : this.directionToGrow(pos, curve, true);
        }
        pos = pos.func_177977_b();
        this.placeTrunk(worldIn, pos);
        if (rand.nextDouble() <= 0.25) {
            this.placeCanister(worldIn, pos.func_177977_b());
        }
        if (rand.nextDouble() <= 0.5) {
            pos = rand.nextDouble() <= 0.5 ? this.directionToGrow(pos.func_177977_b(), direction, false) : this.directionToGrow(pos.func_177977_b(), curve, true);
            this.placeTrunk(worldIn, pos);
            if (rand.nextDouble() <= 0.25) {
                this.placeCanister(worldIn, pos.func_177977_b());
            }
        }
    }

    private BlockPos directionToGrow(BlockPos atm, int choice, boolean sideCurse) {
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
                case 30: {
                    atm = atm.func_177968_d();
                    atm = atm.func_177974_f();
                    break;
                }
                case 31: {
                    atm = atm.func_177968_d();
                    atm = atm.func_177976_e();
                    break;
                }
                case 20: {
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
            case 2: {
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

