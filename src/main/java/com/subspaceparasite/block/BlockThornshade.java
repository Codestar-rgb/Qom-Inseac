/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockThornshade
extends BlockBush
implements IGrowable {
    public static final PropertyEnum<EnumThornshadeStage> STAGE = PropertyEnum.func_177709_a((String)"stage", EnumThornshadeStage.class);
    private static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.2, 0.0, 0.2, 0.8, 0.5, 0.8);
    private static final AxisAlignedBB AABB_MEDIUM = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);
    private static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.9, 1.0);

    public BlockThornshade() {
        super(Material.field_151585_k);
        this.setRegistryName("thornshade");
        this.func_149663_c("subspaceparasite.thornshade");
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        this.func_149672_a(SoundType.field_185850_c);
        this.func_149711_c(0.2f);
        this.func_149675_a(true);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(STAGE, (Comparable)((Object)EnumThornshadeStage.STAGE0_TS)));
        SPBlocks.SP_BLOCKS.add((Block)this);
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState base = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        IBlockState soil = worldIn.func_180495_p(pos.func_177977_b());
        boolean onSrpSoil = this.isSrpSoil(soil);
        boolean snowy = this.isSnowy(worldIn, pos);
        EnumThornshadeStage stage = onSrpSoil ? (snowy ? EnumThornshadeStage.STAGE0_TS_SNOW : EnumThornshadeStage.STAGE0_TS) : this.getDeadStateStage(snowy);
        return base.func_177226_a(STAGE, (Comparable)((Object)stage));
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumThornshadeStage stage = (EnumThornshadeStage)((Object)state.func_177229_b(STAGE));
        switch (stage) {
            case STAGE0_TS: 
            case STAGE0_TS_SNOW: {
                return AABB_SMALL;
            }
            case STAGE1_TS: 
            case STAGE1_TS_SNOW: 
            case STAGE2_TS_NOBERRY: 
            case STAGE2_TS_NOBERRY_SNOW: 
            case DEAD_TS: 
            case DEAD_TS_SNOW: {
                return AABB_MEDIUM;
            }
        }
        return AABB_FULL;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    protected boolean func_185514_i(IBlockState state) {
        Block block = state.func_177230_c();
        if (block == null || state.func_185904_a() == Material.field_151579_a) {
            return false;
        }
        if (block == this) {
            return false;
        }
        return !(block instanceof BlockSlab) && !(block instanceof BlockStairs) && !(block instanceof BlockFence) && !(block instanceof BlockFenceGate) && !(block instanceof BlockWall);
    }

    public boolean func_176196_c(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.func_180495_p(pos.func_177977_b());
        if (soil.func_177230_c() == this) {
            return false;
        }
        return this.func_185514_i(soil) && super.func_176196_c(worldIn, pos);
    }

    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
        super.func_176213_c(worldIn, pos, state);
        IBlockState soil = worldIn.func_180495_p(pos.func_177977_b());
        if (!this.func_185514_i(soil)) {
            if (!worldIn.field_72995_K) {
                Block.func_180635_a((World)worldIn, (BlockPos)pos, (ItemStack)new ItemStack(SPItems.itemThornshadeBerry));
            }
            worldIn.func_175698_g(pos);
        }
    }

    public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canRemainOnCurrentSoil(worldIn, pos, state)) {
            IBlockState deadState = this.getDeadState(worldIn, pos);
            worldIn.func_180501_a(pos, deadState, 2);
        }
        super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
    }

    public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
        IBlockState soil = worldIn.func_180495_p(pos.func_177977_b());
        if (soil.func_177230_c() == this) {
            return false;
        }
        return this.func_185514_i(soil);
    }

    private boolean isSrpSoil(IBlockState state) {
        Block block = state.func_177230_c();
        if (block == null || block.getRegistryName() == null) {
            return false;
        }
        return "subspaceparasite".equals(block.getRegistryName().func_110624_b());
    }

    private boolean canRemainOnCurrentSoil(World world, BlockPos pos, IBlockState state) {
        IBlockState soil = world.func_180495_p(pos.func_177977_b());
        return this.func_185514_i(soil);
    }

    private boolean isSnowy(World world, BlockPos pos) {
        boolean snowfall;
        boolean bl = snowfall = world.func_175678_i(pos.func_177984_a()) && world.func_175727_C(pos.func_177984_a()) && world.func_180494_b(pos).func_180626_a(pos) < 0.15f;
        if (snowfall) {
            return true;
        }
        for (EnumFacing face : EnumFacing.values()) {
            BlockPos checkPos = pos.func_177972_a(face);
            IBlockState state = world.func_180495_p(checkPos);
            if (state.func_177230_c() != Blocks.field_150431_aC && state.func_177230_c() != Blocks.field_150433_aE) continue;
            return true;
        }
        return false;
    }

    private EnumThornshadeStage applySnow(EnumThornshadeStage stage, boolean snowy) {
        switch (stage) {
            case STAGE0_TS: 
            case STAGE0_TS_SNOW: {
                return snowy ? EnumThornshadeStage.STAGE0_TS_SNOW : EnumThornshadeStage.STAGE0_TS;
            }
            case STAGE1_TS: 
            case STAGE1_TS_SNOW: {
                return snowy ? EnumThornshadeStage.STAGE1_TS_SNOW : EnumThornshadeStage.STAGE1_TS;
            }
            case STAGE2_TS: 
            case STAGE2_TS_SNOW: {
                return snowy ? EnumThornshadeStage.STAGE2_TS_SNOW : EnumThornshadeStage.STAGE2_TS;
            }
            case STAGE2_TS_NOBERRY: 
            case STAGE2_TS_NOBERRY_SNOW: {
                return snowy ? EnumThornshadeStage.STAGE2_TS_NOBERRY_SNOW : EnumThornshadeStage.STAGE2_TS_NOBERRY;
            }
            case DEAD_TS: 
            case DEAD_TS_SNOW: {
                return snowy ? EnumThornshadeStage.DEAD_TS_SNOW : EnumThornshadeStage.DEAD_TS;
            }
        }
        return stage;
    }

    private EnumThornshadeStage getDeadStateStage(boolean snowy) {
        return snowy ? EnumThornshadeStage.DEAD_TS_SNOW : EnumThornshadeStage.DEAD_TS;
    }

    private IBlockState getDeadState(World world, BlockPos pos) {
        boolean snowy = this.isSnowy(world, pos);
        return this.func_176223_P().func_177226_a(STAGE, (Comparable)((Object)this.getDeadStateStage(snowy)));
    }

    private boolean isDeadStage(EnumThornshadeStage stage) {
        return stage == EnumThornshadeStage.DEAD_TS || stage == EnumThornshadeStage.DEAD_TS_SNOW;
    }

    private boolean isBerryStage(EnumThornshadeStage stage) {
        return stage == EnumThornshadeStage.STAGE2_TS || stage == EnumThornshadeStage.STAGE2_TS_SNOW;
    }

    private boolean isNoBerryStage(EnumThornshadeStage stage) {
        return stage == EnumThornshadeStage.STAGE2_TS_NOBERRY || stage == EnumThornshadeStage.STAGE2_TS_NOBERRY_SNOW;
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.field_72995_K) {
            return;
        }
        EnumThornshadeStage stage = (EnumThornshadeStage)((Object)state.func_177229_b(STAGE));
        if (!this.canRemainOnCurrentSoil(worldIn, pos, state)) {
            IBlockState deadState = this.getDeadState(worldIn, pos);
            worldIn.func_180501_a(pos, deadState, 2);
            return;
        }
        IBlockState soil = worldIn.func_180495_p(pos.func_177977_b());
        if (!this.isSrpSoil(soil) && !this.isDeadStage(stage)) {
            IBlockState deadState = this.getDeadState(worldIn, pos);
            worldIn.func_180501_a(pos, deadState, 2);
            return;
        }
        boolean snowy = this.isSnowy(worldIn, pos);
        EnumThornshadeStage adjusted = this.applySnow(stage, snowy);
        if (adjusted != stage) {
            worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)adjusted)), 2);
            stage = adjusted;
        }
        if (this.isDeadStage(stage)) {
            return;
        }
        if (rand.nextInt(5) != 0) {
            return;
        }
        switch (stage) {
            case STAGE0_TS: 
            case STAGE0_TS_SNOW: {
                worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)(snowy ? EnumThornshadeStage.STAGE1_TS_SNOW : EnumThornshadeStage.STAGE1_TS))), 2);
                break;
            }
            case STAGE1_TS: 
            case STAGE1_TS_SNOW: {
                worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)(snowy ? EnumThornshadeStage.STAGE2_TS_SNOW : EnumThornshadeStage.STAGE2_TS))), 2);
                break;
            }
            case STAGE2_TS_NOBERRY: 
            case STAGE2_TS_NOBERRY_SNOW: {
                worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)(snowy ? EnumThornshadeStage.STAGE2_TS_SNOW : EnumThornshadeStage.STAGE2_TS))), 2);
                break;
            }
        }
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumThornshadeStage stage = (EnumThornshadeStage)((Object)state.func_177229_b(STAGE));
        if (!this.isBerryStage(stage)) {
            return false;
        }
        if (worldIn.field_72995_K) {
            return true;
        }
        int count = 1 + worldIn.field_73012_v.nextInt(2);
        BlockThornshade.func_180635_a((World)worldIn, (BlockPos)pos, (ItemStack)new ItemStack(SPItems.itemThornshadeBerry, count));
        boolean snowy = this.isSnowy(worldIn, pos);
        EnumThornshadeStage next = snowy ? EnumThornshadeStage.STAGE2_TS_NOBERRY_SNOW : EnumThornshadeStage.STAGE2_TS_NOBERRY;
        worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)next)), 2);
        return true;
    }

    public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add((Object)new ItemStack((Block)this, 1, EnumThornshadeStage.STAGE0_TS.ordinal()));
        items.add((Object)new ItemStack((Block)this, 1, EnumThornshadeStage.STAGE1_TS.ordinal()));
        items.add((Object)new ItemStack((Block)this, 1, EnumThornshadeStage.STAGE2_TS.ordinal()));
        items.add((Object)new ItemStack((Block)this, 1, EnumThornshadeStage.STAGE2_TS_NOBERRY.ordinal()));
        items.add((Object)new ItemStack((Block)this, 1, EnumThornshadeStage.DEAD_TS.ordinal()));
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add((Object)new ItemStack((Block)this));
    }

    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack((Block)this);
    }

    public boolean func_176473_a(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        EnumThornshadeStage stage = (EnumThornshadeStage)((Object)state.func_177229_b(STAGE));
        return !this.isDeadStage(stage) && !this.isBerryStage(stage);
    }

    public boolean func_180670_a(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    public void func_176474_b(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        EnumThornshadeStage stage = (EnumThornshadeStage)((Object)state.func_177229_b(STAGE));
        boolean snowy = this.isSnowy(worldIn, pos);
        switch (stage) {
            case STAGE0_TS: 
            case STAGE0_TS_SNOW: {
                worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)(snowy ? EnumThornshadeStage.STAGE1_TS_SNOW : EnumThornshadeStage.STAGE1_TS))), 2);
                break;
            }
            case STAGE1_TS: 
            case STAGE1_TS_SNOW: {
                worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)(snowy ? EnumThornshadeStage.STAGE2_TS_SNOW : EnumThornshadeStage.STAGE2_TS))), 2);
                break;
            }
            case STAGE2_TS_NOBERRY: 
            case STAGE2_TS_NOBERRY_SNOW: {
                worldIn.func_180501_a(pos, state.func_177226_a(STAGE, (Comparable)((Object)(snowy ? EnumThornshadeStage.STAGE2_TS_SNOW : EnumThornshadeStage.STAGE2_TS))), 2);
                break;
            }
        }
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumThornshadeStage)((Object)state.func_177229_b(STAGE))).ordinal();
    }

    public IBlockState func_176203_a(int meta) {
        EnumThornshadeStage[] values = EnumThornshadeStage.values();
        if (meta < 0 || meta >= values.length) {
            meta = 0;
        }
        return this.func_176223_P().func_177226_a(STAGE, (Comparable)((Object)values[meta]));
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{STAGE});
    }

    public static enum EnumThornshadeStage implements IStringSerializable
    {
        STAGE0_TS("stage0_ts"),
        STAGE0_TS_SNOW("stage0_ts_snow"),
        STAGE1_TS("stage1_ts"),
        STAGE1_TS_SNOW("stage1_ts_snow"),
        STAGE2_TS("stage2_ts"),
        STAGE2_TS_SNOW("stage2_ts_snow"),
        STAGE2_TS_NOBERRY("stage2_ts_noberry"),
        STAGE2_TS_NOBERRY_SNOW("stage2_ts_noberry_snow"),
        DEAD_TS("dead_ts"),
        DEAD_TS_SNOW("dead_ts_snow");

        private final String name;

        private EnumThornshadeStage(String name) {
            this.name = name;
        }

        public String func_176610_l() {
            return this.name;
        }

        public String toString() {
            return this.name;
        }
    }
}

