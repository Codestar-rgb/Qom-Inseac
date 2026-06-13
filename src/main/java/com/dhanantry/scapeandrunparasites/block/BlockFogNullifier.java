/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.TileEntityFogNullifier;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFogNullifier
extends BlockBase
implements ITileEntityProvider {
    private static final String TAG_USES = "UsesRemaining";

    public BlockFogNullifier(String name) {
        super(Material.field_151576_e, name, 2.0f, true, false);
        this.func_149752_b(10.0f);
        this.setHarvestLevel("pickaxe", 0);
        this.func_149713_g(0);
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    public TileEntity func_149915_a(World w, int m) {
        return new TileEntityFogNullifier();
    }

    public void func_180633_a(World w, BlockPos p, IBlockState s, EntityLivingBase placer, ItemStack stack) {
        if (!w.field_72995_K) {
            TileEntity te = w.func_175625_s(p);
            if (te instanceof TileEntityFogNullifier) {
                int from = BlockFogNullifier.getUsesFromStack(stack);
                int uses = from >= 0 ? from : Math.max(0, SRPConfigWorld.fogNullifierMaxUses);
                ((TileEntityFogNullifier)te).setUsesRemaining(uses);
                te.func_70296_d();
            }
            this.attemptConsumeUseAndClear(w, p);
        }
    }

    public void func_189540_a(IBlockState s, World w, BlockPos p, Block b, BlockPos f) {
        if (!w.field_72995_K) {
            this.attemptConsumeUseAndClear(w, p);
        }
    }

    public boolean func_180639_a(World w, BlockPos p, IBlockState s, EntityPlayer pl, EnumHand h, EnumFacing f, float x, float y, float z) {
        if (!w.field_72995_K) {
            return this.attemptConsumeUseAndClear(w, p);
        }
        return true;
    }

    public boolean func_149662_c(IBlockState s) {
        return false;
    }

    public boolean func_149686_d(IBlockState s) {
        return true;
    }

    private boolean attemptConsumeUseAndClear(World w, BlockPos p) {
        TileEntity te = w.func_175625_s(p);
        if (!(te instanceof TileEntityFogNullifier)) {
            return false;
        }
        TileEntityFogNullifier data = (TileEntityFogNullifier)te;
        if (data.getUsesRemaining() <= 0) {
            return false;
        }
        int cleared = this.clearConnectedFog(w, p);
        if (cleared > 0) {
            w.func_184133_a(null, p, SoundEvents.field_187715_dR, SoundCategory.BLOCKS, 0.9f, 1.0f);
            int left = data.getUsesRemaining() - 1;
            data.setUsesRemaining(left);
            data.func_70296_d();
            if (left <= 0) {
                w.func_184133_a(null, p, SoundEvents.field_187635_cQ, SoundCategory.BLOCKS, 0.8f, 0.9f);
                w.func_175655_b(p, false);
            }
            return true;
        }
        return false;
    }

    private int clearConnectedFog(World w, BlockPos origin) {
        ArrayDeque<BlockPos> q = new ArrayDeque<BlockPos>();
        HashSet<BlockPos> seen = new HashSet<BlockPos>();
        for (EnumFacing f : EnumFacing.field_82609_l) {
            BlockPos n = origin.func_177972_a(f);
            if (!this.isParasiteFog(w, n)) continue;
            q.add(n);
            seen.add(n);
        }
        if (q.isEmpty()) {
            return 0;
        }
        int cleared = 0;
        int cap = 500000;
        Random r = w.field_73012_v;
        while (!q.isEmpty() && cleared < cap) {
            BlockPos p = (BlockPos)q.pollFirst();
            if (!this.isParasiteFog(w, p)) continue;
            this.spawnDispelParticles(w, p, r);
            w.func_180501_a(p, Blocks.field_150350_a.func_176223_P(), 3);
            ++cleared;
            for (EnumFacing f : EnumFacing.field_82609_l) {
                BlockPos n = p.func_177972_a(f);
                if (seen.contains(n) || !this.isParasiteFog(w, n)) continue;
                seen.add(n);
                q.addLast(n);
            }
        }
        return cleared;
    }

    private void spawnDispelParticles(World w, BlockPos p, Random r) {
        for (int i = 0; i < 4; ++i) {
            double x = (double)p.func_177958_n() + 0.2 + r.nextDouble() * 0.6;
            double y = (double)p.func_177956_o() + 0.2 + r.nextDouble() * 0.6;
            double z = (double)p.func_177952_p() + 0.2 + r.nextDouble() * 0.6;
            w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0, 0.0, 0.0, new int[0]);
        }
        if (r.nextFloat() < 0.3f) {
            int extra = 3 + r.nextInt(3);
            for (int i = 0; i < extra; ++i) {
                double x = (double)p.func_177958_n() + r.nextDouble();
                double y = (double)p.func_177956_o() + r.nextDouble();
                double z = (double)p.func_177952_p() + r.nextDouble();
                w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0, 0.0, 0.0, new int[0]);
            }
        }
    }

    private boolean isParasiteFog(World w, BlockPos p) {
        Block b = w.func_180495_p(p).func_177230_c();
        try {
            if (SRPBlocks.ParasiteFog != null && b == SRPBlocks.ParasiteFog) {
                return true;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        String id = String.valueOf(b.getRegistryName());
        return "srparasites:parasitefog".equals(id) || "srparasites:parasitefog".equals(id);
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess acc, BlockPos pos, IBlockState state, int fortune) {
        int uses;
        World w;
        TileEntity te;
        if (acc instanceof World && (te = (w = (World)acc).func_175625_s(pos)) instanceof TileEntityFogNullifier && (uses = ((TileEntityFogNullifier)te).getUsesRemaining()) > 0) {
            ItemStack stack = new ItemStack((Block)this);
            BlockFogNullifier.setUsesOnStack(stack, uses);
            drops.add((Object)stack);
        }
    }

    public ItemStack getPickBlock(IBlockState s, RayTraceResult hit, World w, BlockPos pos, EntityPlayer player) {
        int uses;
        ItemStack out = new ItemStack((Block)this);
        TileEntity te = w.func_175625_s(pos);
        if (te instanceof TileEntityFogNullifier && (uses = ((TileEntityFogNullifier)te).getUsesRemaining()) >= 0) {
            BlockFogNullifier.setUsesOnStack(out, uses);
        }
        return out;
    }

    private static int getUsesFromStack(ItemStack stack) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b(TAG_USES)) {
            return stack.func_77978_p().func_74762_e(TAG_USES);
        }
        return -1;
    }

    private static void setUsesOnStack(ItemStack stack, int uses) {
        NBTTagCompound tag = stack.func_77942_o() ? stack.func_77978_p() : new NBTTagCompound();
        tag.func_74768_a(TAG_USES, Math.max(0, uses));
        stack.func_77982_d(tag);
    }
}

