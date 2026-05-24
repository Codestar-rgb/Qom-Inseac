/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$EnumOffsetType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyDirection
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.particle.ParticleManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumBlockRenderType
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Plane
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.tileentity.TileEntityNodeRelay;
import com.subspaceparasite.tileentity.TileEntityRelayController;
import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRelayController
extends Block {
    public static final PropertyDirection FACING = PropertyDirection.func_177712_a((String)"facing", (Predicate)EnumFacing.Plane.HORIZONTAL);

    public BlockRelayController(String name) {
        super(Material.field_151573_f);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
        this.func_149713_g(0);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.NORTH));
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        SPBlocks.SP_BLOCKS.add(this);
    }

    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{FACING});
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.func_176731_b((int)(meta & 3)));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, ParticleManager manager) {
        return true;
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumFacing)state.func_177229_b((IProperty)FACING)).func_176736_b();
    }

    public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.NORTH);
    }

    private static void spawnBreakSmoke(World world, BlockPos pos) {
        if (world.field_72995_K) {
            return;
        }
        if (!(world instanceof WorldServer)) {
            return;
        }
        WorldServer ws = (WorldServer)world;
        Random r = world.field_73012_v;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                int puffs = dx == 0 && dz == 0 ? 10 : 6;
                for (int i = 0; i < puffs; ++i) {
                    double x = (double)pos.func_177958_n() + 0.5 + (double)dx + (r.nextDouble() - 0.5) * 0.9;
                    double y = (double)pos.func_177956_o() + 0.05 + r.nextDouble() * 0.3;
                    double z = (double)pos.func_177952_p() + 0.5 + (double)dz + (r.nextDouble() - 0.5) * 0.9;
                    double vx = (r.nextDouble() - 0.5) * 0.04;
                    double vy = 0.08 + r.nextDouble() * 0.06;
                    double vz = (r.nextDouble() - 0.5) * 0.04;
                    ws.func_175739_a(EnumParticleTypes.SMOKE_LARGE, x, y, z, 1, vx, vy, vz, 0.0, new int[0]);
                }
            }
        }
        ws.func_175739_a(EnumParticleTypes.SMOKE_LARGE, (double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o() + 1.0, (double)pos.func_177952_p() + 0.5, 10, 0.35, 0.45, 0.35, 0.0, new int[0]);
        ws.func_175739_a(EnumParticleTypes.SMOKE_LARGE, (double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o() + 1.6, (double)pos.func_177952_p() + 0.5, 6, 0.25, 0.35, 0.25, 0.0, new int[0]);
    }

    public void func_180663_b(World world, BlockPos pos, IBlockState state) {
        BlockRelayController.spawnBreakSmoke(world, pos);
        super.func_180663_b(world, pos, state);
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityRelayController();
    }

    public Block.EnumOffsetType func_176218_Q() {
        return Block.EnumOffsetType.NONE;
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        BlockPos controllerPos = pos;
        player.openGui((Object)"subspaceparasite", 0, world, controllerPos.func_177958_n(), controllerPos.func_177956_o(), controllerPos.func_177952_p());
        return true;
    }

    public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (world.field_72995_K) {
            return;
        }
        TileEntity te = world.func_175625_s(pos);
        if (!(te instanceof TileEntityRelayController)) {
            return;
        }
        TileEntityRelayController ctrl = (TileEntityRelayController)te;
        EnumFacing facing = (EnumFacing)state.func_177229_b((IProperty)FACING);
        List<BlockPos> nodes = this.computeNodePositions(pos, facing);
        ArrayList<BlockPos> toClear = new ArrayList<BlockPos>();
        for (BlockPos p : nodes) {
            if (world.func_175623_d(p)) continue;
            IBlockState s = world.func_180495_p(p);
            Block b = s.func_177230_c();
            if (b.func_176200_f((IBlockAccess)world, p) || s.func_185904_a().func_76222_j()) {
                toClear.add(p);
                continue;
            }
            if (placer instanceof EntityPlayer) {
                ((EntityPlayer)placer).func_146105_b((ITextComponent)new TextComponentTranslation("block.subspaceparasite.relay_controller.no_space", new Object[0]), true);
            }
            world.func_175655_b(pos, true);
            return;
        }
        for (BlockPos p : toClear) {
            world.func_175698_g(p);
        }
        for (BlockPos p : nodes) {
            world.func_180501_a(p, SPBlocks.NODE_RELAY.func_176223_P(), 2);
            TileEntity nte = world.func_175625_s(p);
            if (!(nte instanceof TileEntityNodeRelay)) continue;
            ((TileEntityNodeRelay)nte).setControllerPos(pos);
        }
        ctrl.setChildPositions(nodes);
        ctrl.setFormed(true);
        ctrl.func_70296_d();
    }

    public void func_176208_a(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileEntity te;
        if (!world.field_72995_K && (te = world.func_175625_s(pos)) instanceof TileEntityRelayController) {
            ((TileEntityRelayController)te).dismantle();
        }
        super.func_176208_a(world, pos, state, player);
    }

    private List<BlockPos> computeNodePositions(BlockPos origin, EnumFacing facingIgnored) {
        int y;
        ArrayList<BlockPos> out = new ArrayList<BlockPos>();
        ArrayList<BlockPos> rel = new ArrayList<BlockPos>();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                if (dx == 0 && dz == 0) continue;
                rel.add(new BlockPos(dx, 0, dz));
            }
        }
        for (y = 1; y <= 3; ++y) {
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dz = -1; dz <= 1; ++dz) {
                    rel.add(new BlockPos(dx, y, dz));
                }
            }
        }
        for (y = 4; y <= 6; ++y) {
            rel.add(new BlockPos(0, y, 0));
        }
        for (BlockPos r : rel) {
            out.add(origin.func_177982_a(r.func_177958_n(), r.func_177956_o(), r.func_177952_p()));
        }
        return out;
    }
}

