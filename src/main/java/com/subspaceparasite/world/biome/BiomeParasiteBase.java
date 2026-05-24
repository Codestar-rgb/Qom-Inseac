/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.BlockSandStone
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.biome.Biome$BiomeProperties
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package com.subspaceparasite.world.biome;

import com.subspaceparasite.block.BlockParasiteRubble;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.biome.BiomeParasiteDecorator;
import java.util.Collection;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class BiomeParasiteBase
extends Biome {
    protected WorldGenAbstractTree tree;

    public BiomeParasiteBase(Biome.BiomeProperties properties) {
        super(properties);
    }

    public void setBlocks() {
        String[] blockList = this.getDirt().split(":");
        Block one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
        this.field_76752_A = one.func_176203_a(Integer.parseInt(blockList[2]));
        blockList = this.getStone().split(":");
        one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
        this.field_76753_B = one.func_176203_a(Integer.parseInt(blockList[2]));
        this.field_76760_I = new BiomeParasiteDecorator(this);
    }

    public void mobListClear() {
        this.field_76762_K.clear();
        this.field_76761_J.clear();
        this.field_76755_L.clear();
        this.field_82914_M.clear();
    }

    public abstract float getRedValue();

    public abstract float getGreenValue();

    public abstract float getBlueValue();

    public abstract String[] getBlockList();

    public abstract String getDirt();

    public abstract String getGravel();

    public abstract String getSand();

    public abstract String getLog();

    public abstract String getStone();

    public abstract String getCobblestone();

    public abstract String getSandstone();

    public abstract String getLeaves();

    public abstract String getLeavesG();

    public abstract String getPlank();

    public abstract String getBush();

    public abstract void spawnGenFeatureParasite(World var1, BlockPos var2, Random var3);

    public abstract void spawnGenRoofParasite(World var1, BlockPos var2, Random var3);

    public int convertBlock(BlockPos helper, World worldIn, Random rand) {
        Block one;
        String[] blockList;
        int currentMeta;
        IBlockState lookingState = worldIn.func_180495_p(helper);
        Material mat = lookingState.func_185904_a();
        Block lookingBlock = lookingState.func_177230_c();
        if (lookingBlock instanceof IMetaName) {
            return 0;
        }
        if (mat == Material.field_151579_a || mat == Material.field_151586_h || mat == Material.field_151587_i) {
            return 0;
        }
        ResourceLocation rl = lookingBlock.getRegistryName();
        if (rl == null) {
            return 0;
        }
        String lookingName = rl.toString();
        if (BiomeParasiteBase.transformBlockList(helper, worldIn, lookingName, currentMeta = lookingBlock.func_176201_c(lookingState), blockList = this.getBlockList())) {
            return 1;
        }
        if (ParasiteEventWorld.blockException(worldIn, helper, lookingBlock, lookingState, SPConfigWorld.blockBBiomeList, SPConfigWorld.blockBBiomeListWhite, SPConfigWorld.biomeBlockIMaxH)) {
            return 0;
        }
        if (BiomeParasiteBase.transformBlockList(helper, worldIn, lookingName, currentMeta, blockList)) {
            return 1;
        }
        if (lookingBlock.isWood((IBlockAccess)worldIn, helper) || lookingBlock == SPBlocks.InfestedTrunk) {
            blockList = this.getLog().split(":");
            Block one2 = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one2.func_176203_a(Integer.parseInt(blockList[2])));
            return 1;
        }
        if (lookingBlock.isLeaves(lookingState, (IBlockAccess)worldIn, helper)) {
            BlockPos below;
            blockList = this.getLeaves().split(":");
            Block one3 = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one3.func_176203_a(Integer.parseInt(blockList[2])));
            if (rand.nextInt(100) < 30 && worldIn.func_175623_d(below = helper.func_177977_b())) {
                blockList = this.getLeavesG().split(":");
                one3 = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
                IBlockState growth = BiomeParasiteBase.setFacingIfPresent(one3.func_176203_a(Integer.parseInt(blockList[2])), EnumFacing.DOWN);
                worldIn.func_180501_a(below, growth, 2);
            }
            return 1;
        }
        if (lookingBlock == SPBlocks.InfestedStain) {
            blockList = this.getDirt().split(":");
            one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
        }
        if (mat == Material.field_151578_c || mat == Material.field_151577_b) {
            blockList = this.getDirt().split(":");
            one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
            return 1;
        }
        if (lookingBlock instanceof BlockSand) {
            blockList = this.getSand().split(":");
            one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
            return 1;
        }
        if (lookingBlock instanceof BlockSandStone) {
            blockList = this.getSandstone().split(":");
            one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
            return 1;
        }
        if (lookingBlock == Blocks.field_150351_n || mat == Material.field_151595_p) {
            blockList = this.getGravel().split(":");
            one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
            return 1;
        }
        if (lookingBlock == Blocks.field_150347_e) {
            blockList = this.getCobblestone().split(":");
            one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
            worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
            return 1;
        }
        if (mat == Material.field_151576_e) {
            if (lookingBlock == SPBlocks.BiomeHeart || lookingBlock == SPBlocks.ColonyHeart) {
                return 0;
            }
            if (lookingBlock == Blocks.field_150343_Z) {
                worldIn.func_175656_a(helper, SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.OBSIDIAN)));
            } else if (lookingBlock.getRegistryName().toString().contains("brick")) {
                worldIn.func_175656_a(helper, SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)));
            } else {
                blockList = this.getStone().split(":");
                one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
                worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
            }
            this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
            this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
            return 1;
        }
        if (mat == Material.field_151585_k) {
            worldIn.func_175656_a(helper, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)));
            return 1;
        }
        if (mat == Material.field_151588_w || mat == Material.field_151598_x) {
            worldIn.func_175656_a(helper, SPBlocks.BloodyIce.func_176223_P());
            return 1;
        }
        if (mat == Material.field_151573_f) {
            worldIn.func_175656_a(helper, SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.METAL)));
            return 1;
        }
        if (mat == Material.field_151575_d && lookingBlock != SPBlocks.InfestedTrunk) {
            if (lookingBlock.getRegistryName().toString().contains("mushroom")) {
                worldIn.func_175656_a(helper, SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FUNGUS)));
            } else {
                worldIn.func_175656_a(helper, SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.WOOD)));
            }
            return 1;
        }
        return 0;
    }

    private static IBlockState setFacingIfPresent(IBlockState state, EnumFacing face) {
        Collection props = state.func_177227_a();
        for (IProperty p : props) {
            IProperty pf;
            if (!p.func_177701_a().equalsIgnoreCase("facing") || p.func_177699_b() != EnumFacing.class || !(pf = p).func_177700_c().contains(face)) continue;
            return state.func_177226_a(pf, (Comparable)face);
        }
        return state;
    }

    public static boolean transformBlockList(BlockPos helper, World worldIn, String name, int meta, String[] rules) {
        if (rules == null || rules.length == 0) {
            return false;
        }
        for (String rule : rules) {
            Block putting;
            String[] nm;
            String[] dst;
            String[] src;
            String[] parts = rule.split(";");
            if (parts.length != 2 || (src = parts[0].split(":")).length != 3 || (dst = parts[1].split(":")).length != 3 || (nm = name.split(":")).length != 2) continue;
            if ("minecraft:glass".equals(name)) {
                System.out.println("[SRP] transformBlockList evaluating GLASS: rule=" + rule + " meta=" + meta);
            }
            if (!nm[0].equals(src[0]) || !nm[1].equals(src[1]) || meta != Integer.parseInt(src[2]) || (putting = Block.func_149684_b((String)(dst[0] + ":" + dst[1]))) == null) continue;
            int dstMeta = Integer.parseInt(dst[2]);
            worldIn.func_180501_a(helper, putting.func_176203_a(dstMeta), 3);
            return true;
        }
        return false;
    }
}

