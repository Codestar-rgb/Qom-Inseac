/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockSlab$EnumBlockHalf
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockStairs$EnumHalf
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteGenAbstract;
import java.util.Random;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteSpine
extends WorldGenParasiteGenAbstract {
    public WorldGenParasiteSpine(boolean notify) {
        super(notify);
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        int atmm;
        BlockPos twoCurrent;
        int partner;
        BlockPos current;
        int i = 28;
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
            current = position;
            partner = rand.nextInt(4);
            twoCurrent = this.directionToGrow(current, partner, false);
            atmm = (partner + 1) % 4;
            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
                current = current.func_177977_b();
                twoCurrent = twoCurrent.func_177977_b();
                this.placeTrunk(worldIn, current);
                this.placeTrunk(worldIn, twoCurrent);
                for (int z = 0; z <= 3; ++z) {
                    this.placeTrunk(worldIn, this.directionToGrow(current, z, false));
                    this.placeTrunk(worldIn, this.directionToGrow(twoCurrent, z, false));
                }
            }
        } else {
            return false;
        }
        current = position;
        twoCurrent = this.directionToGrow(current, partner, false);
        this.placeStair(worldIn, current, atmm, true, 1, false);
        current = this.placeColumn(worldIn, current, 4, rand, partner, 27, true);
        this.placeStair(worldIn, current, atmm, true, 0, false);
        this.placeColumn(worldIn, this.directionToGrow(position, (atmm + 2) % 4, false), 5, rand, partner, -1, false);
        this.placeStair(worldIn, twoCurrent, atmm, true, 1, false);
        twoCurrent = this.placeColumn(worldIn, twoCurrent, 4, rand, (partner + 2) % 4, 27, true);
        this.placeStair(worldIn, twoCurrent, atmm, true, 0, false);
        this.placeColumn(worldIn, this.directionToGrow(this.directionToGrow(position, partner, false), (atmm + 2) % 4, false), 5, rand, partner, -1, false);
        this.placeStair(worldIn, twoCurrent.func_177979_c(4), partner, true, 1, false);
        this.placeStair(worldIn, current.func_177979_c(4), (partner + 2) % 4, true, 1, false);
        current = this.directionToGrow(current.func_177984_a(), atmm, true);
        current = this.placeColumn(worldIn, current, 2, rand, partner, 14, true);
        this.placeStair(worldIn, current, atmm, true, 0, false);
        twoCurrent = this.directionToGrow(twoCurrent.func_177984_a(), atmm, true);
        twoCurrent = this.placeColumn(worldIn, twoCurrent, 2, rand, (partner + 2) % 4, 14, true);
        this.placeStair(worldIn, twoCurrent, atmm, true, 0, false);
        int randomG = rand.nextInt(4) + 3;
        current = this.directionToGrow(current.func_177984_a(), atmm, true);
        this.placeStair(worldIn, current.func_177977_b(), (atmm + 2) % 4, false, 0, false);
        current = this.placeColumn(worldIn, current, randomG, rand, partner, 7, true);
        this.placeStair(worldIn, current, atmm, true, 0, false);
        twoCurrent = this.directionToGrow(twoCurrent.func_177984_a(), atmm, true);
        this.placeStair(worldIn, twoCurrent.func_177977_b(), (atmm + 2) % 4, false, 0, false);
        twoCurrent = this.placeColumn(worldIn, twoCurrent, randomG, rand, (partner + 2) % 4, 7, true);
        this.placeStair(worldIn, twoCurrent, atmm, true, 0, false);
        randomG = rand.nextInt(5) + 6;
        current = this.directionToGrow(current.func_177984_a(), atmm, true);
        this.placeStair(worldIn, current.func_177977_b(), (atmm + 2) % 4, false, 0, false);
        current = this.placeColumn(worldIn, current, randomG, rand, partner, 2, true);
        this.placeStair(worldIn, current, atmm, true, 0, false);
        twoCurrent = this.directionToGrow(twoCurrent.func_177984_a(), atmm, true);
        this.placeStair(worldIn, twoCurrent.func_177977_b(), (atmm + 2) % 4, false, 0, false);
        twoCurrent = this.placeColumn(worldIn, twoCurrent, randomG, rand, (partner + 2) % 4, 2, true);
        this.placeStair(worldIn, twoCurrent, atmm, true, 0, false);
        randomG = rand.nextInt(5) + 6;
        current = this.directionToGrow(current.func_177984_a(), atmm, true);
        this.placeStair(worldIn, current.func_177977_b(), (atmm + 2) % 4, false, 0, false);
        current = this.placeColumn(worldIn, current, randomG, rand, partner, 0, true);
        this.placeStair(worldIn, current, atmm, true, 0, false);
        twoCurrent = this.directionToGrow(twoCurrent.func_177984_a(), atmm, true);
        this.placeStair(worldIn, twoCurrent.func_177977_b(), (atmm + 2) % 4, false, 0, false);
        twoCurrent = this.placeColumn(worldIn, twoCurrent, randomG, rand, (partner + 2) % 4, 0, true);
        this.placeStair(worldIn, twoCurrent, atmm, true, 0, false);
        return true;
    }

    private void placeStair(World worldIn, BlockPos pos, int direction, boolean bottom, int times, boolean slab) {
        switch (direction) {
            case 0: {
                if (bottom) {
                    this.func_175903_a(worldIn, pos.func_177964_d(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.SOUTH).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.BOTTOM));
                    break;
                }
                this.func_175903_a(worldIn, pos.func_177964_d(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.SOUTH).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.TOP));
                if (!slab) break;
                this.func_175903_a(worldIn, pos.func_177964_d(times + 1), SPBlocks.ParasiteRubbleSlabHalf.func_176223_P().func_177226_a((IProperty)BlockSlab.field_176554_a, (Comparable)BlockSlab.EnumBlockHalf.TOP));
                break;
            }
            case 1: {
                if (bottom) {
                    this.func_175903_a(worldIn, pos.func_177965_g(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.WEST).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.BOTTOM));
                    break;
                }
                this.func_175903_a(worldIn, pos.func_177965_g(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.WEST).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.TOP));
                if (!slab) break;
                this.func_175903_a(worldIn, pos.func_177965_g(times + 1), SPBlocks.ParasiteRubbleSlabHalf.func_176223_P().func_177226_a((IProperty)BlockSlab.field_176554_a, (Comparable)BlockSlab.EnumBlockHalf.TOP));
                break;
            }
            case 3: {
                if (bottom) {
                    this.func_175903_a(worldIn, pos.func_177985_f(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.EAST).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.BOTTOM));
                    break;
                }
                this.func_175903_a(worldIn, pos.func_177985_f(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.EAST).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.TOP));
                if (!slab) break;
                this.func_175903_a(worldIn, pos.func_177985_f(times + 1), SPBlocks.ParasiteRubbleSlabHalf.func_176223_P().func_177226_a((IProperty)BlockSlab.field_176554_a, (Comparable)BlockSlab.EnumBlockHalf.TOP));
                break;
            }
            default: {
                if (bottom) {
                    this.func_175903_a(worldIn, pos.func_177970_e(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.NORTH).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.BOTTOM));
                    break;
                }
                this.func_175903_a(worldIn, pos.func_177970_e(times), SPBlocks.ParasiteRubbleBoneStair.func_176223_P().func_177226_a((IProperty)BlockStairs.field_176309_a, (Comparable)EnumFacing.NORTH).func_177226_a((IProperty)BlockStairs.field_176308_b, (Comparable)BlockStairs.EnumHalf.TOP));
                if (!slab) break;
                this.func_175903_a(worldIn, pos.func_177970_e(times + 1), SPBlocks.ParasiteRubbleSlabHalf.func_176223_P().func_177226_a((IProperty)BlockSlab.field_176554_a, (Comparable)BlockSlab.EnumBlockHalf.TOP));
            }
        }
    }

    private void placeTrunk(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SPBlocks.ParasiteRubble.func_176223_P());
    }

    private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, int direction, int slabs, boolean stair) {
        int current;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        while (current < atm + in) {
            if (times % 2 == 0 && stair) {
                if (slabs > 20) {
                    this.placeStair(worldIn, newPos, direction, false, 2, false);
                    slabs -= 10;
                } else if (slabs > 10) {
                    this.placeStair(worldIn, newPos, direction, false, 2, false);
                    this.placeStair(worldIn, newPos.func_177984_a(), (direction + 2) % 4, true, -2, false);
                    this.placeStair(worldIn, newPos.func_177984_a(), direction, false, 3, (slabs -= 10) > 5);
                } else {
                    this.placeStair(worldIn, newPos, direction, false, 2, slabs > 0);
                    --slabs;
                }
            }
            this.placeTrunk(worldIn, newPos);
            newPos = newPos.func_177984_a();
            ++current;
            ++times;
        }
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

