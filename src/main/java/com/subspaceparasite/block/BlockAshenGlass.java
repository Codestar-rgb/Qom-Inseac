/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGlass
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.IStagedBlock;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAshenGlass
extends BlockGlass
implements IStagedBlock {
    public static final PropertyInteger STAGE = PropertyInteger.func_177719_a((String)"stage", (int)0, (int)5);

    public BlockAshenGlass(String name) {
        this(name, true);
    }

    public BlockAshenGlass(String name, boolean creative) {
        super(Material.field_151592_s, true);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149711_c(0.3f);
        this.func_149672_a(SoundType.field_185853_f);
        this.func_149713_g(0);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(0)));
        if (creative) {
            this.func_149647_a(SPMain.SP_CREATIVETAB);
        }
        SPBlocks.SP_BLOCKS.add((Block)this);
        ItemBlock itemBlock = new ItemBlock((Block)this);
        SPItems.SP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public PropertyInteger getStageProperty() {
        return STAGE;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{STAGE});
    }

    public int func_176201_c(IBlockState state) {
        return (Integer)state.func_177229_b((IProperty)STAGE);
    }

    public IBlockState func_176203_a(int meta) {
        if (meta < 0) {
            meta = 0;
        }
        if (meta > 5) {
            meta = 5;
        }
        return this.func_176223_P().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(meta));
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    protected boolean func_149700_E() {
        return true;
    }
}

