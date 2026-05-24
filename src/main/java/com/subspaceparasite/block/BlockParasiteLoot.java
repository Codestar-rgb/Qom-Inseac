/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.client.particle.ParticleSpawner;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPSoundTypes;
import com.subspaceparasite.item.ItemBlockVariant;
import com.subspaceparasite.tileentity.TileEntityParasiteLoot;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

public class BlockParasiteLoot
extends BlockBase
implements IMetaName {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);

    public BlockParasiteLoot(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material, name, hardness, creative, tickRandom);
        this.setHarvestLevel("pickaxe", 1);
        this.field_149784_t = 10;
        this.func_149672_a(SPSoundTypes.FLESH);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.COMMON)));
    }

    public int func_180651_a(IBlockState state) {
        return this.func_176201_c(state);
    }

    public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType variant : EnumType.values()) {
            items.add((Object)new ItemStack((Block)this, 1, variant.ordinal()));
        }
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a(VARIANT, (Comparable)((Object)EnumType.values()[meta]));
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal();
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.func_150898_a((Block)this), 1, this.func_176201_c(world.func_180495_p(pos)));
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        TileEntity te = world.func_175625_s(pos);
        if (te instanceof TileEntityParasiteLoot) {
            player.openGui((Object)SPMain.instance, 101, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return false;
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity te = worldIn.func_175625_s(pos);
        if (te instanceof TileEntityParasiteLoot) {
            TileEntityParasiteLoot chest = (TileEntityParasiteLoot)te;
            chest.func_174888_l();
        }
        super.func_180663_b(worldIn, pos, state);
    }

    @Nullable
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityParasiteLoot();
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{VARIANT});
    }

    @Override
    public Enum[] getVariants() {
        return EnumType.values();
    }

    public void func_190948_a(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(TextFormatting.GREEN + I18n.func_135052_a((String)"tooltip.subspaceparasite.parasite_loot", (Object[])new Object[0]));
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant(this);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != SPBlocks.ParasiteBush) {
            return;
        }
        if (rand.nextDouble() <= (double)SPConfigSystems.rsBlockParticleS) {
            double d0 = (double)pos.func_177958_n() + rand.nextDouble();
            double d1 = (double)pos.func_177956_o() + 2.5;
            double d2 = (double)pos.func_177952_p() + rand.nextDouble();
            ParticleSpawner.spawnParticle(SPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
        }
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        Item item = null;
        if (item == null) {
            return super.func_180660_a(state, rand, fortune);
        }
        return item;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    private Item loot(Random rand, String[] drop) {
        try {
            Item item;
            if (drop.length != 0 && (item = Item.func_111206_d((String)drop[rand.nextInt(drop.length)])) != null) {
                return item;
            }
        }
        catch (Exception e) {
            SPMain.logger.log(Level.ERROR, "Problem with loot event", (Throwable)e);
        }
        return null;
    }

    public static enum EnumType implements IStringSerializable
    {
        COMMON,
        UNCOMMON,
        RARE;


        public String func_176610_l() {
            return this.name().toLowerCase();
        }

        public String toString() {
            return this.func_176610_l();
        }
    }
}

