/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.entity.tile.TileEntityTrophy;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEntityTrophy
extends Block
implements ITileEntityProvider {
    private final ResourceLocation mobId;
    private final boolean animate;
    private final TrophyTextureMode textureMode;
    private final boolean hasSounds;
    private final float yOffset;
    private final float scale;

    public BlockEntityTrophy(Material material, String registryName, String mobId, boolean animate, TrophyTextureMode textureMode, boolean hasSounds, float yOffset, float scale) {
        super(material);
        this.mobId = new ResourceLocation(mobId);
        this.animate = animate;
        this.textureMode = textureMode;
        this.hasSounds = hasSounds;
        this.yOffset = yOffset;
        this.scale = scale;
        this.setRegistryName(registryName);
        this.func_149663_c("subspaceparasite." + registryName);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(SoundType.field_185851_d);
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        SPBlocks.SP_BLOCKS.add(this);
        ItemBlock itemBlock = this instanceof IMetaName ? ((IMetaName)((Object)this)).getItemBlock() : new ItemBlock((Block)this);
        SPItems.SP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileEntityTrophy();
    }

    public ResourceLocation getMobId() {
        return this.mobId;
    }

    public boolean isAnimate() {
        return this.animate;
    }

    public TrophyTextureMode getTextureMode() {
        return this.textureMode;
    }

    public boolean hasSounds() {
        return this.hasSounds;
    }

    public float getYOffset() {
        return this.yOffset;
    }

    public float getScale() {
        return this.scale;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.field_72995_K) {
            return true;
        }
        if (!this.hasSounds) {
            return true;
        }
        Entity e = EntityList.func_188429_b((ResourceLocation)this.mobId, (World)worldIn);
        if (e instanceof EntityLiving) {
            EntityLiving living = (EntityLiving)e;
            living.func_70642_aH();
        }
        return true;
    }

    public static enum TrophyTextureMode {
        DEFAULT,
        STONE;

    }
}

