/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockBase;
import java.util.ArrayDeque;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDiseasedSponge
extends BlockBase {
    public BlockDiseasedSponge() {
        super(Material.field_151583_m, "diseased_sponge", 0.6f, true, false);
        this.func_149672_a(SoundType.field_185854_g);
    }

    public void func_176213_c(World world, BlockPos pos, IBlockState state) {
        super.func_176213_c(world, pos, state);
    }

    public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.func_189540_a(state, world, pos, blockIn, fromPos);
    }

    public static boolean absorbDeadBlood(World world, BlockPos origin, Block deadBlood) {
        if (deadBlood == null || deadBlood == Blocks.field_150350_a) {
            return false;
        }
        int removed = 0;
        ArrayDeque<Node> q = new ArrayDeque<Node>();
        q.add(new Node(origin, 0));
        while (!q.isEmpty() && removed < 64) {
            Node n = (Node)q.poll();
            for (EnumFacing f : EnumFacing.values()) {
                BlockPos p = n.pos.func_177972_a(f);
                IBlockState st = world.func_180495_p(p);
                if (st.func_177230_c() != deadBlood) continue;
                world.func_175698_g(p);
                ++removed;
                if (n.depth >= 6) continue;
                q.add(new Node(p, n.depth + 1));
            }
        }
        return removed > 0;
    }

    private static class Node {
        final BlockPos pos;
        final int depth;

        Node(BlockPos pos, int depth) {
            this.pos = pos;
            this.depth = depth;
        }
    }
}

