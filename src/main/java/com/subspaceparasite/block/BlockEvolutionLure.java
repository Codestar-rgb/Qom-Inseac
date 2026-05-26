/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.entity.EntityParasiticScent;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPSoundTypes;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.item.ItemBlockVariant;
import com.subspaceparasite.network.SPPacketParticle;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BlockEvolutionLure
extends BlockBase
implements IMetaName {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);

    public BlockEvolutionLure(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material, name, hardness, creative, tickRandom);
        this.func_149672_a(SPSoundTypes.LURE);
        this.func_149715_a(0.1f);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.ONE)));
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K && SPConfigSystems.useEvolution) {
            SPSaveData data;
            ItemStack head = new ItemStack(playerIn.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b());
            if (head.func_77973_b() != Items.field_190931_a) {
                return false;
            }
            worldIn.func_184133_a(null, pos, SPSounds.LURE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (this.checkBlocks(worldIn, pos, (EnumType)((Object)state.func_177229_b(VARIANT)))) {
                if (SPSaveData.get(worldIn, 2).getCooldown(worldIn, worldIn.field_73011_w.getDimension()) > 0) {
                    return false;
                }
                worldIn.func_175656_a(pos.func_177964_d(3).func_177965_g(3), Blocks.field_150350_a.func_176223_P());
                worldIn.func_175656_a(pos.func_177964_d(3).func_177985_f(3), Blocks.field_150350_a.func_176223_P());
                worldIn.func_175656_a(pos.func_177970_e(3).func_177965_g(3), Blocks.field_150350_a.func_176223_P());
                worldIn.func_175656_a(pos.func_177970_e(3).func_177985_f(3), Blocks.field_150350_a.func_176223_P());
                SPMain.network.sendToAll((IMessage)new SPPacketParticle((double)pos.func_177964_d(3).func_177965_g(3).func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177964_d(3).func_177965_g(3).func_177952_p() + 0.5, 0.5f, 0.5f, 2));
                SPMain.network.sendToAll((IMessage)new SPPacketParticle((double)pos.func_177964_d(3).func_177985_f(3).func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177964_d(3).func_177985_f(3).func_177952_p() + 0.5, 0.5f, 0.5f, 2));
                SPMain.network.sendToAll((IMessage)new SPPacketParticle((double)pos.func_177970_e(3).func_177965_g(3).func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177970_e(3).func_177965_g(3).func_177952_p() + 0.5, 0.5f, 0.5f, 2));
                SPMain.network.sendToAll((IMessage)new SPPacketParticle((double)pos.func_177970_e(3).func_177985_f(3).func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177970_e(3).func_177985_f(3).func_177952_p() + 0.5, 0.5f, 0.5f, 2));
                playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lurec", new Object[0]), true);
                data = SPSaveData.get(worldIn, 3);
                worldIn.func_184133_a(null, pos, SPSounds.CARCASS_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                switch ((EnumType)((Object)state.func_177229_b(VARIANT))) {
                    case EIGHT: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueEightCool, true, worldIn, true, 23);
                        break;
                    }
                    case FIVE: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueFiveCool, true, worldIn, true, 24);
                        break;
                    }
                    case FOUR: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueFourCool, true, worldIn, true, 25);
                        break;
                    }
                    case ONE: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueOneCool, true, worldIn, true, 26);
                        break;
                    }
                    case SEVEN: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueSevenCool, true, worldIn, true, 27);
                        break;
                    }
                    case SIX: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueSixCool, true, worldIn, true, 28);
                        break;
                    }
                    case THREE: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueThreeCool, true, worldIn, true, 29);
                        break;
                    }
                    case TWO: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueTwoCool, true, worldIn, true, 30);
                        break;
                    }
                    case NINE: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueNineCool, true, worldIn, true, 31);
                        break;
                    }
                    case TEN: {
                        data.setTotalKills(worldIn.field_73011_w.getDimension(), -SPConfigSystems.luredValueTenCool, true, worldIn, true, 32);
                        break;
                    }
                }
                if (!playerIn.field_71075_bZ.field_75102_a && SPConfigSystems.useScent) {
                    EntityParasiticScent sss = new EntityParasiticScent(worldIn);
                    sss.setScentLevel(this.getLevelByPhase(state));
                    sss.func_82149_j((Entity)playerIn);
                    sss.setTargetToKill((EntityLivingBase)playerIn, false);
                    sss.setDieToE(true);
                    sss.setCanFollow(true);
                    worldIn.func_72838_d((Entity)sss);
                }
                worldIn.func_72942_c((Entity)new EntityLightningBolt(worldIn, (double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o(), (double)pos.func_177952_p() + 0.5, true));
            } else {
                data = SPSaveData.get(worldIn, 4);
                if (data.getEvolutionPhase(worldIn.field_73011_w.getDimension()) <= -1) {
                    return false;
                }
                switch ((EnumType)((Object)state.func_177229_b(VARIANT))) {
                    case EIGHT: {
                        data.setCooldown(SPConfigSystems.luredValueEight, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case FIVE: {
                        data.setCooldown(SPConfigSystems.luredValueFive, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case FOUR: {
                        data.setCooldown(SPConfigSystems.luredValueFour, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case ONE: {
                        data.setCooldown(SPConfigSystems.luredValueOne, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case SEVEN: {
                        data.setCooldown(SPConfigSystems.luredValueSeven, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case SIX: {
                        data.setCooldown(SPConfigSystems.luredValueSix, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case THREE: {
                        data.setCooldown(SPConfigSystems.luredValueThree, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case TWO: {
                        data.setCooldown(SPConfigSystems.luredValueTwo, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case NINE: {
                        data.setCooldown(SPConfigSystems.luredValueNine, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                    case TEN: {
                        data.setCooldown(SPConfigSystems.luredValueTen, worldIn, worldIn.field_73011_w.getDimension(), true);
                        playerIn.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.lureb", new Object[0]), true);
                        break;
                    }
                }
            }
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
            for (int i = 0; i <= 3; ++i) {
                SPMain.network.sendToAll((IMessage)new SPPacketParticle((double)pos.func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177952_p() + 0.5, 0.5f, 0.5f, 2));
            }
        }
        return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{VARIANT});
    }

    @Override
    public Enum[] getVariants() {
        return EnumType.values();
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant(this);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        this.func_176208_a(world, pos, state, player);
        return world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), world.field_72995_K ? 11 : 3);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    }

    public void func_190948_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        IBlockState data = this.func_176203_a(stack.func_77960_j());
        int i = 0;
        switch ((EnumType)((Object)data.func_177229_b(VARIANT))) {
            case ONE: {
                i = SPConfigSystems.luredValueOne;
                break;
            }
            case TWO: {
                i = SPConfigSystems.luredValueTwo;
                break;
            }
            case THREE: {
                i = SPConfigSystems.luredValueThree;
                break;
            }
            case FOUR: {
                i = SPConfigSystems.luredValueFour;
                break;
            }
            case FIVE: {
                i = SPConfigSystems.luredValueFive;
                break;
            }
            case SIX: {
                i = SPConfigSystems.luredValueSix;
                break;
            }
            case SEVEN: {
                i = SPConfigSystems.luredValueSeven;
                break;
            }
            case EIGHT: {
                i = SPConfigSystems.luredValueEight;
                break;
            }
            case NINE: {
                i = SPConfigSystems.luredValueEight;
                break;
            }
            case TEN: {
                i = SPConfigSystems.luredValueEight;
            }
        }
        tooltip.add(I18n.func_135052_a((String)("tooltip." + this.func_149739_a()), (Object[])new Object[]{i}));
    }

    private int getLevelByPhase(IBlockState state) {
        switch ((EnumType)((Object)state.func_177229_b(VARIANT))) {
            case EIGHT: {
                return SPConfigSystems.eightLevelDeploy;
            }
            case FIVE: {
                return SPConfigSystems.fiveLevelDeploy;
            }
            case FOUR: {
                return SPConfigSystems.fourLevelDeploy;
            }
            case ONE: {
                return SPConfigSystems.oneLevelDeploy;
            }
            case SEVEN: {
                return SPConfigSystems.sevenLevelDeploy;
            }
            case SIX: {
                return SPConfigSystems.sixLevelDeploy;
            }
            case THREE: {
                return SPConfigSystems.threeLevelDeploy;
            }
            case TWO: {
                return SPConfigSystems.twoLevelDeploy;
            }
            case NINE: {
                return SPConfigSystems.nineLevelDeploy;
            }
            case TEN: {
                return SPConfigSystems.tenLevelDeploy;
            }
        }
        return 0;
    }

    private boolean checkBlocks(World worldIn, BlockPos pos, EnumType t) {
        return worldIn.func_180495_p(pos.func_177964_d(3).func_177965_g(3)).func_177230_c() == SPBlocks.evolutionLure && worldIn.func_180495_p(pos.func_177964_d(3).func_177985_f(3)).func_177230_c() == SPBlocks.evolutionLure && worldIn.func_180495_p(pos.func_177970_e(3).func_177965_g(3)).func_177230_c() == SPBlocks.evolutionLure && worldIn.func_180495_p(pos.func_177970_e(3).func_177985_f(3)).func_177230_c() == SPBlocks.evolutionLure;
    }

    public static enum EnumType implements IStringSerializable
    {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN;


        public String func_176610_l() {
            return this.name().toLowerCase();
        }

        public String toString() {
            return this.func_176610_l();
        }
    }
}

