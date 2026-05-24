/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.particle.ParticleManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumBlockRenderType
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.tileentity.TileEntityNodeRelay;
import com.subspaceparasite.tileentity.TileEntityRelayController;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNodeRelay
extends Block {
    private static final AxisAlignedBB NODE_AABB = field_185505_j;

    public BlockNodeRelay(String name) {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149713_g(0);
        this.func_149647_a(null);
        SPBlocks.SP_BLOCKS.add(this);
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityNodeRelay();
    }

    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        return NODE_AABB;
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return NODE_AABB;
    }

    public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    public boolean func_176209_a(IBlockState state, boolean hitIfLiquid) {
        return true;
    }

    public float func_176195_g(IBlockState state, World worldIn, BlockPos pos) {
        return 3.0f;
    }

    public float func_149638_a(Entity exploder) {
        return 10.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, ParticleManager manager) {
        return true;
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos controllerPos;
        if (world.field_72995_K) {
            return true;
        }
        TileEntity te = world.func_175625_s(pos);
        if (te instanceof TileEntityNodeRelay && (controllerPos = ((TileEntityNodeRelay)te).getControllerPos()) != null) {
            player.openGui((Object)"subspaceparasite", 0, world, controllerPos.func_177958_n(), controllerPos.func_177956_o(), controllerPos.func_177952_p());
            return true;
        }
        return false;
    }

    private void tryDismantle(World world, BlockPos nodePos, @Nullable EntityPlayer player) {
        TileEntity cte;
        if (world.field_72995_K) {
            return;
        }
        TileEntity te = world.func_175625_s(nodePos);
        if (!(te instanceof TileEntityNodeRelay)) {
            return;
        }
        BlockPos ctrlPos = ((TileEntityNodeRelay)te).getControllerPos();
        TileEntityRelayController controller = null;
        if (ctrlPos != null && world.func_175667_e(ctrlPos) && (cte = world.func_175625_s(ctrlPos)) instanceof TileEntityRelayController) {
            controller = (TileEntityRelayController)cte;
        }
        if (controller == null) {
            controller = this.findNearbyController(world, nodePos, 8);
        }
        if (controller != null) {
            controller.dismantle();
        } else if (player != null) {
            player.func_146105_b((ITextComponent)new TextComponentString("Relay controller missing or unloaded."), true);
        }
    }

    @Nullable
    private TileEntityRelayController findNearbyController(World world, BlockPos origin, int radius) {
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= radius; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    TileEntity te;
                    BlockPos p = origin.func_177982_a(dx, dy, dz);
                    if (!world.func_175667_e(p) || !((te = world.func_175625_s(p)) instanceof TileEntityRelayController)) continue;
                    return (TileEntityRelayController)te;
                }
            }
        }
        return null;
    }

    public void func_176208_a(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        this.tryDismantle(world, pos, player);
        super.func_176208_a(world, pos, state, player);
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        this.tryDismantle(worldIn, pos, null);
        super.func_180663_b(worldIn, pos, state);
    }

    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
        return ItemStack.field_190927_a;
    }

    public int func_149745_a(Random random) {
        return 0;
    }
}

