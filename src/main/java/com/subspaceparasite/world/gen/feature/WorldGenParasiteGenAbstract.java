/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.init.SPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenParasiteGenAbstract
extends WorldGenerator {
    public WorldGenParasiteGenAbstract(boolean notify) {
        super(notify);
    }

    public boolean canGrowInto(Block blockType) {
        Material material = blockType.func_176223_P().func_185904_a();
        return material == Material.field_151579_a || material == Material.field_151584_j || blockType == Blocks.field_150349_c || blockType == Blocks.field_150346_d || blockType == Blocks.field_150364_r || blockType == Blocks.field_150363_s || blockType == Blocks.field_150345_g || blockType == Blocks.field_150395_bd || blockType == SPBlocks.ParasiteBush;
    }

    public boolean isReplaceable(World world, BlockPos pos) {
        IBlockState state = world.func_180495_p(pos);
        return state.func_177230_c().isAir(state, (IBlockAccess)world, pos) || state.func_177230_c().isLeaves(state, (IBlockAccess)world, pos) || state.func_177230_c().isWood((IBlockAccess)world, pos) || this.canGrowInto(state.func_177230_c()) || state.func_177230_c() == SPBlocks.ParasiteBush;
    }
}

