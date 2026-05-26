/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockInfestedBush;
import com.subspaceparasite.block.BlockParasiteBush;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteGenAbstract;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WorldGenParasiteBush
extends WorldGenParasiteGenAbstract {
    private final IBlockState tallGrassState;
    private int type;
    private boolean parasite;

    public WorldGenParasiteBush(boolean notify, BlockParasiteBush.EnumType variant, int type) {
        super(notify);
        this.tallGrassState = SPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)variant));
        this.type = type;
        this.parasite = true;
    }

    public WorldGenParasiteBush(boolean notify, BlockInfestedBush.EnumType variant, int type) {
        super(notify);
        this.tallGrassState = SPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, (Comparable)((Object)variant));
        this.type = type;
        this.parasite = false;
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        if (this.type == 3) {
            this.flowerGen(worldIn, rand, position);
            return true;
        }
        if (this.type == 4) {
            for (int i = 0; i < 128; ++i) {
                BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                if (this.parasite) {
                    if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || !SPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                    this.VinGen(worldIn, rand, blockpos);
                    continue;
                }
                if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || !SPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                this.VinGen(worldIn, rand, blockpos);
            }
            this.VinGen(worldIn, rand, position);
            return true;
        }
        IBlockState iblockstate = worldIn.func_180495_p(position);
        while ((iblockstate.func_177230_c() == Blocks.field_150350_a || iblockstate.func_177230_c().isLeaves(iblockstate, (IBlockAccess)worldIn, position)) && position.func_177956_o() > 0) {
            position = position.func_177977_b();
            iblockstate = worldIn.func_180495_p(position);
        }
        switch (this.type) {
            case 1: {
                for (int i = 0; i < 128; ++i) {
                    BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                    if (this.parasite) {
                        if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || !SPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                        worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                        continue;
                    }
                    if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || !SPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                    worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                }
                break;
            }
            case 2: {
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                    if (this.parasite) {
                        if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || !SPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                        worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                        continue;
                    }
                    if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || !SPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                    worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                }
                break;
            }
        }
        return true;
    }

    public boolean flowerGen(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (this.parasite) {
                if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || blockpos.func_177956_o() >= 255 || !SPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
                worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                continue;
            }
            if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a || blockpos.func_177956_o() >= 255 || !SPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || !this.checkFloor(worldIn, blockpos)) continue;
            worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
        }
        return true;
    }

    public boolean VinGen(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 10; ++i) {
            BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.func_180495_p(blockpos).func_177230_c() != Blocks.field_150350_a) continue;
            int j = 1 + rand.nextInt(rand.nextInt(3) + 3);
            for (int k = 0; k < j; ++k) {
                if (this.parasite) {
                    if (!SPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos.func_177981_b(k), this.tallGrassState) || worldIn.func_180495_p(blockpos.func_177981_b(k)).func_177230_c() != Blocks.field_150350_a) continue;
                    worldIn.func_180501_a(blockpos.func_177981_b(k), this.tallGrassState, 2);
                    continue;
                }
                if (!SPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState) || worldIn.func_180495_p(blockpos.func_177981_b(k)).func_177230_c() != Blocks.field_150350_a) continue;
                worldIn.func_180501_a(blockpos.func_177981_b(k), this.tallGrassState, 2);
            }
        }
        return true;
    }

    private boolean checkFloor(World worldIn, BlockPos position) {
        return worldIn.func_180495_p(position.func_177977_b()).func_185917_h();
    }
}

