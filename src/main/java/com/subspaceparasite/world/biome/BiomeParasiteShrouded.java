/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome$BiomeProperties
 *  net.minecraft.world.chunk.ChunkPrimer
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.world.biome;

import com.subspaceparasite.block.BlockParasiteBush;
import com.subspaceparasite.block.BlockParasiteSapling;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteBall;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteBigBall;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteBush;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteGenAbstract;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteMouth;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteSpine;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTallFlower;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTenFlower;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTree;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTreeThin;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeParasiteShrouded
extends BiomeParasiteBase {
    public static WorldGenAbstractTree treeP = new WorldGenParasiteTree(false);
    public static WorldGenAbstractTree treePT = new WorldGenParasiteTreeThin(false);
    public WorldGenerator grassP1 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.EYE, 1);
    public WorldGenerator grassP2 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.POP, 2);

    public BiomeParasiteShrouded() {
        super(new Biome.BiomeProperties("Parasite Biome Shrouded").func_185398_c(0.13f).func_185400_d(0.5f));
        this.field_76760_I.field_76832_z = 1;
        this.field_76760_I.field_189870_A = 0.0f;
        this.field_76760_I.field_76803_B = 15;
        this.field_76760_I.field_76802_A = 4;
        this.field_76760_I.field_76804_C = 2;
        this.field_76760_I.field_76800_F = 15;
        this.field_76760_I.field_76807_J = 1;
    }

    public WorldGenAbstractTree func_150567_a(Random rand) {
        if (rand.nextInt(3) == 0) {
            return treePT;
        }
        return treeP;
    }

    public WorldGenerator func_76730_b(Random rand) {
        if (rand.nextInt(2) == 0) {
            return this.grassP2;
        }
        return this.grassP1;
    }

    public void func_180624_a(World worldIn, Random rand, BlockPos pos) {
        super.func_180624_a(worldIn, rand, pos);
    }

    public void func_180622_a(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        super.func_180622_a(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
        double d0 = 1.0;
        if (d0 > 0.0) {
            int i = x & 0xF;
            int j = z & 0xF;
            for (int k = 255; k >= 0; --k) {
                if (chunkPrimerIn.func_177856_a(j, k, i).func_185904_a() == Material.field_151579_a) continue;
                if (k != 62 || chunkPrimerIn.func_177856_a(j, k, i).func_177230_c() == Blocks.field_150355_j) break;
                chunkPrimerIn.func_177855_a(j, k, i, field_185372_h);
                break;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76731_a(float currentTemperature) {
        return SPConfigWorld.biomeOneSkyColor;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_180627_b(BlockPos blockPos) {
        return SPConfigWorld.biomeOneGrassColor;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_180625_c(BlockPos blockPos) {
        return SPConfigWorld.biomeOneFoliageColor;
    }

    public int getWaterColorMultiplier() {
        return SPConfigWorld.biomeOneWaterColor;
    }

    @Override
    public float getRedValue() {
        return SPConfigWorld.biomeOneFogRed / 255.0f;
    }

    @Override
    public float getGreenValue() {
        return SPConfigWorld.biomeOneFogGreen / 255.0f;
    }

    @Override
    public float getBlueValue() {
        return SPConfigWorld.biomeOneFogBlue / 255.0f;
    }

    @Override
    public String[] getBlockList() {
        return SPConfigWorld.biomeOneBlockList;
    }

    @Override
    public String getDirt() {
        return "subspaceparasite:parasitestain:0";
    }

    @Override
    public String getGravel() {
        return "subspaceparasite:parasitestain:5";
    }

    @Override
    public String getLog() {
        return "subspaceparasite:parasitetrunk:0";
    }

    @Override
    public String getStone() {
        return "subspaceparasite:parasiterubble:2";
    }

    @Override
    public String getCobblestone() {
        return "subspaceparasite:parasiterubble:0";
    }

    @Override
    public String getSand() {
        return "subspaceparasite:parasitestain:1";
    }

    @Override
    public String getSandstone() {
        return "subspaceparasite:parasiterubble:1";
    }

    @Override
    public String getLeaves() {
        return "minecraft:air:0";
    }

    @Override
    public String getLeavesG() {
        return "minecraft:air:0";
    }

    @Override
    public String getPlank() {
        return "subspaceparasite:parasiteplank:0";
    }

    @Override
    public String getBush() {
        return "subspaceparasite:parasitebush:0";
    }

    @Override
    public void spawnGenFeatureParasite(World worldIn, BlockPos pos, Random rand) {
        WorldGenParasiteGenAbstract gen;
        double bonus = 0.0;
        if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos).func_185904_a() == Material.field_151586_h) {
            pos = ParasiteEventEntity.getFloor(worldIn, pos, 10);
            bonus = 3.0E-5;
            if (pos == null) {
                return;
            }
        }
        if (rand.nextDouble() < 0.0015) {
            if (rand.nextDouble() < 0.2) {
                WorldGenParasiteTreeThin gen2 = new WorldGenParasiteTreeThin(false);
                if (!gen2.func_180709_b(worldIn, rand, pos) && worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
                    worldIn.func_175656_a(pos, SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                }
                return;
            }
            WorldGenParasiteTree gen3 = new WorldGenParasiteTree(false);
            if (!gen3.func_180709_b(worldIn, rand, pos) && worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
                worldIn.func_175656_a(pos, SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREETHIN)));
            }
            return;
        }
        if (rand.nextDouble() < 0.001) {
            WorldGenParasiteTallFlower gen4 = new WorldGenParasiteTallFlower(false);
            if (!gen4.func_180709_b(worldIn, rand, pos) && worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
                worldIn.func_175656_a(pos, SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.FLOWERTALL)));
            }
            return;
        }
        if (rand.nextDouble() < 5.0E-4) {
            WorldGenParasiteSpine gen5 = new WorldGenParasiteSpine(false);
            if (!gen5.func_180709_b(worldIn, rand, pos)) {
                // empty if block
            }
            return;
        }
        if (rand.nextDouble() < 1.0E-4) {
            WorldGenParasiteTenFlower gen6 = new WorldGenParasiteTenFlower(false);
            if (!gen6.func_180709_b(worldIn, rand, pos)) {
                // empty if block
            }
            return;
        }
        if (rand.nextDouble() < 7.0E-5 + bonus) {
            WorldGenParasiteBall gen7 = new WorldGenParasiteBall(false);
            if (!gen7.func_180709_b(worldIn, rand, pos)) {
                // empty if block
            }
            return;
        }
        if (rand.nextDouble() < 2.0E-5 + bonus) {
            WorldGenParasiteBigBall gen8 = new WorldGenParasiteBigBall(false);
            if (!gen8.func_180709_b(worldIn, rand, pos)) {
                // empty if block
            }
            return;
        }
        if (rand.nextInt(500) == 0) {
            gen = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.TENDRIL, 4);
            ((WorldGenParasiteBush)gen).func_180709_b(worldIn, rand, pos);
        }
        if (rand.nextInt(250) == 0) {
            switch (rand.nextInt(3)) {
                case 0: {
                    WorldGenParasiteBush gen1 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.POP, 1);
                    gen1.func_180709_b(worldIn, rand, pos);
                    break;
                }
                case 1: {
                    WorldGenParasiteBush gen2 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.EYE, 2);
                    gen2.func_180709_b(worldIn, rand, pos);
                    break;
                }
                case 2: {
                    WorldGenParasiteBush gen3 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.TOOH, 3);
                    gen3.func_180709_b(worldIn, rand, pos);
                }
            }
        }
        if (rand.nextDouble() < 3.0E-4 && ((WorldGenParasiteMouth)(gen = new WorldGenParasiteMouth(false))).func_180709_b(worldIn, rand, pos)) {
            return;
        }
    }

    @Override
    public void spawnGenRoofParasite(World worldIn, BlockPos pos, Random rand) {
        if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
            return;
        }
        worldIn.func_175656_a(pos, SPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)BlockParasiteBush.EnumType.BINE)));
        pos = pos.func_177977_b();
        if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
            return;
        }
        worldIn.func_175656_a(pos, SPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)BlockParasiteBush.EnumType.BINE)));
        pos = pos.func_177977_b();
        if (rand.nextInt(2) == 0) {
            if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                return;
            }
            worldIn.func_175656_a(pos, SPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)BlockParasiteBush.EnumType.BINE)));
            pos = pos.func_177977_b();
            if (rand.nextInt(2) == 0) {
                if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                    return;
                }
                worldIn.func_175656_a(pos, SPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)BlockParasiteBush.EnumType.BINE)));
                pos = pos.func_177977_b();
                if (rand.nextInt(2) == 0) {
                    if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                        return;
                    }
                    worldIn.func_175656_a(pos, SPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)BlockParasiteBush.EnumType.BINE)));
                }
            }
        }
    }
}

