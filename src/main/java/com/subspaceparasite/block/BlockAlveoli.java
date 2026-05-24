/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPSoundTypes;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlveoli
extends BlockBase
implements IShearable {
    public static final PropertyBool ACTIVE = PropertyBool.func_177716_a((String)"active");
    public static final PropertyBool DEPLETED = PropertyBool.func_177716_a((String)"depleted");

    public BlockAlveoli() {
        super(Material.field_151571_B, "alveoli", 1.0f, true, true);
        this.func_149672_a(SPSoundTypes.FLESH);
        this.func_149713_g(0);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.TRUE).func_177226_a((IProperty)DEPLETED, (Comparable)Boolean.FALSE));
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{ACTIVE, DEPLETED});
    }

    public IBlockState func_176203_a(int meta) {
        boolean active = (meta & 1) != 0;
        boolean depleted = (meta & 2) != 0;
        return this.func_176223_P().func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(active)).func_177226_a((IProperty)DEPLETED, (Comparable)Boolean.valueOf(depleted));
    }

    public int func_176201_c(IBlockState state) {
        int m = 0;
        if (((Boolean)state.func_177229_b((IProperty)ACTIVE)).booleanValue()) {
            m |= 1;
        }
        if (((Boolean)state.func_177229_b((IProperty)DEPLETED)).booleanValue()) {
            m |= 2;
        }
        return m;
    }

    public boolean func_149662_c(IBlockState s) {
        return false;
    }

    public boolean func_149686_d(IBlockState s) {
        return false;
    }

    public boolean doesSideBlockRendering(IBlockState s, IBlockAccess w, BlockPos p, EnumFacing f) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public boolean func_180639_a(World w, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack held = player.func_184586_b(hand);
        if (held.func_77973_b() == Items.field_151069_bo) {
            if (!w.field_72995_K) {
                ItemStack fluid;
                if (!player.field_71075_bZ.field_75098_d) {
                    held.func_190918_g(1);
                }
                if (!player.field_71071_by.func_70441_a(fluid = new ItemStack(SPItems.ALVEOLAR_FLUID))) {
                    player.func_71019_a(fluid, false);
                }
                w.func_184133_a(null, pos, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 1.0f, 1.0f);
                this.setDepleted(w, pos, state);
            }
            return true;
        }
        return false;
    }

    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        boolean near = this.isNearFollicle(worldIn, pos, 6);
        return this.func_176223_P().func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(near)).func_177226_a((IProperty)DEPLETED, (Comparable)Boolean.valueOf(false));
    }

    public void func_176213_c(World w, BlockPos pos, IBlockState state) {
        super.func_176213_c(w, pos, state);
        if (!((Boolean)state.func_177229_b((IProperty)DEPLETED)).booleanValue()) {
            boolean near = this.isNearFollicle(w, pos, 6);
            if ((Boolean)state.func_177229_b((IProperty)ACTIVE) != near) {
                w.func_180501_a(pos, state.func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(near)), 2);
            }
        }
    }

    private void setDepleted(World w, BlockPos pos, IBlockState state) {
        IBlockState s = state.func_177226_a((IProperty)DEPLETED, (Comparable)Boolean.valueOf(true)).func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(false));
        w.func_180501_a(pos, s, 3);
        w.func_175684_a(pos, (Block)this, 1200);
    }

    public void func_180650_b(World w, BlockPos pos, IBlockState state, Random rand) {
        if (((Boolean)state.func_177229_b((IProperty)DEPLETED)).booleanValue()) {
            boolean near = this.isNearFollicle(w, pos, 6);
            w.func_180501_a(pos, state.func_177226_a((IProperty)DEPLETED, (Comparable)Boolean.valueOf(false)).func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(near)), 3);
        } else {
            boolean near = this.isNearFollicle(w, pos, 6);
            if ((Boolean)state.func_177229_b((IProperty)ACTIVE) != near) {
                w.func_180501_a(pos, state.func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(near)), 3);
            }
        }
    }

    public void func_189540_a(IBlockState state, World w, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (((Boolean)state.func_177229_b((IProperty)DEPLETED)).booleanValue()) {
            return;
        }
        boolean near = this.isNearFollicle(w, pos, 6);
        if ((Boolean)state.func_177229_b((IProperty)ACTIVE) != near) {
            w.func_180501_a(pos, state.func_177226_a((IProperty)ACTIVE, (Comparable)Boolean.valueOf(near)), 3);
        }
    }

    private boolean isNearFollicle(World w, BlockPos pos, int r) {
        BlockPos.MutableBlockPos p = new BlockPos.MutableBlockPos();
        for (int dx = -r; dx <= r; ++dx) {
            for (int dy = -r; dy <= r; ++dy) {
                for (int dz = -r; dz <= r; ++dz) {
                    String path;
                    p.func_181079_c(pos.func_177958_n() + dx, pos.func_177956_o() + dy, pos.func_177952_p() + dz);
                    IBlockState s = w.func_180495_p((BlockPos)p);
                    ResourceLocation rl = s.func_177230_c().getRegistryName();
                    if (rl == null || !(path = rl.func_110623_a()).contains("hair_follicle")) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack((Block)this));
    }
}

