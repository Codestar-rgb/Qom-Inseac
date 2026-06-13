/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHarleskinnStairs
extends BlockStairs {
    public BlockHarleskinnStairs(IBlockState modelState, String registryName) {
        super(modelState);
        this.setRegistryName(registryName);
        this.func_149663_c("srparasites." + registryName);
        this.func_149711_c(1.5f);
        this.func_149752_b(10.0f);
        this.func_149672_a(SRPSoundTypes.FLESH);
        this.field_149783_u = true;
        this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        this.func_149675_a(true);
        SRPBlocks.SRP_BLOCKS.add((Block)this);
    }

    public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.field_72995_K) {
            return;
        }
        BeckonBlockInfestation.beckonInfestation(worldIn, pos, rand, 0, false);
    }
}

