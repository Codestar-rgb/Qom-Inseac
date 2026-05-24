/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockParasiteSpreading;
import com.subspaceparasite.block.IStagedBlock;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPSoundTypes;
import com.subspaceparasite.util.convert.BeckonBlockInfestation;
import java.util.Locale;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHarleskinnFence
extends BlockFence {
    public BlockHarleskinnFence(String registryName) {
        super(Material.field_151575_d, MapColor.field_151663_o);
        this.func_149672_a(SPSoundTypes.FLESH);
        this.setRegistryName(registryName);
        this.func_149663_c("subspaceparasite." + registryName);
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        this.func_149675_a(true);
        SPBlocks.SP_BLOCKS.add((Block)this);
    }

    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
        super.func_176213_c(worldIn, pos, state);
        if (!worldIn.field_72995_K) {
            worldIn.func_175684_a(pos, (Block)this, 10);
        }
    }

    public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
        if (!worldIn.field_72995_K) {
            worldIn.func_175684_a(pos, (Block)this, 10);
        }
    }

    public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        this.func_180650_b(worldIn, pos, state, rand);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.field_72995_K) {
            return;
        }
        if (BlockHarleskinnFence.touchingAnyInfestation(worldIn, pos)) {
            BeckonBlockInfestation.beckonInfestation(worldIn, pos, rand, 1, false);
            worldIn.func_175684_a(pos, (Block)this, 20);
        }
    }

    private static boolean touchingAnyInfestation(World worldIn, BlockPos pos) {
        for (int dir = 0; dir <= 5; ++dir) {
            String path;
            BlockPos helper = BlockParasiteSpreading.directionToSpread(pos, dir);
            IBlockState st = worldIn.func_180495_p(helper);
            Block b = st.func_177230_c();
            if (b instanceof IStagedBlock) {
                return true;
            }
            ResourceLocation rl = b.getRegistryName();
            if (rl == null || !"subspaceparasite".equals(rl.func_110624_b()) || !(path = rl.func_110623_a().toLowerCase(Locale.ROOT)).contains("infest")) continue;
            return true;
        }
        return false;
    }
}

