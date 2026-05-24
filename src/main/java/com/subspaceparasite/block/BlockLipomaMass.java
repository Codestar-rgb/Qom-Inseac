/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPSoundTypes;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLipomaMass
extends BlockBush {
    public BlockLipomaMass(String name) {
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        this.func_149672_a(SPSoundTypes.FLESH);
        this.func_149711_c(0.0f);
        SPBlocks.SP_BLOCKS.add((Block)this);
        SPItems.SP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    private boolean isSPBlock(Block b) {
        ResourceLocation rl = b.getRegistryName();
        return rl != null && rl.func_110624_b().equals("subspaceparasite");
    }

    public boolean func_176196_c(World w, BlockPos pos) {
        BlockPos up = pos.func_177984_a();
        IBlockState above = w.func_180495_p(up);
        return this.isSPBlock(above.func_177230_c()) && w.isSideSolid(up, EnumFacing.DOWN, true);
    }

    public void func_189540_a(IBlockState state, World w, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.func_180671_f(w, pos, state)) {
            w.func_175655_b(pos, true);
        }
    }

    public boolean func_180671_f(World w, BlockPos pos, IBlockState state) {
        BlockPos up = pos.func_177984_a();
        IBlockState above = w.func_180495_p(up);
        return this.isSPBlock(above.func_177230_c()) && w.isSideSolid(up, EnumFacing.DOWN, true);
    }

    public boolean func_149662_c(IBlockState s) {
        return false;
    }

    public boolean func_149686_d(IBlockState s) {
        return false;
    }

    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }
}

