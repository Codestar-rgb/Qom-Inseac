/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockParasiteRubbleDense;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.world.gen.WorldGenCustomStructures;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBase;
import com.subspaceparasite.world.gen.structure.WorldGenStructure;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNexusProtection2
extends WorldGenParasiteColonyBase {
    public WorldGenParasiteNexusProtection2(boolean notify, int stage) {
        super(notify, stage);
        this.wall = SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));
        this.tacle = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
        this.floor = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        int radius = 6;
        int height = 5;
        IBlockState pillarBlock = Blocks.field_150348_b.func_176223_P();
        this.generateRandomPillar(worldIn, posss.func_177979_c(1), radius, height, pillarBlock);
        radius = 9;
        height = 9;
        pillarBlock = Blocks.field_150348_b.func_176223_P();
        this.generateRandomPillar(worldIn, posss.func_177979_c(1), radius, height, pillarBlock);
        return true;
    }

    public void generateRandomPillar(World worldIn, BlockPos center, int radius, int height, IBlockState blockState) {
        Random rand = new Random();
        double theta = rand.nextDouble() * 2.0 * Math.PI;
        BlockPos basePos = this.getCirclePoint(center, radius, theta);
        if ((basePos = ParasiteEventEntity.getFloor(worldIn, basePos, 7)) == null) {
            return;
        }
        basePos = basePos.func_177977_b();
        String out = "beckon_";
        int outt = worldIn.field_73012_v.nextInt(4) + 2;
        out = out + outt + "x" + outt;
        switch (outt) {
            case 2: {
                out = out + "_1";
                break;
            }
            case 3: {
                switch (worldIn.field_73012_v.nextInt(5) + 1) {
                    case 1: {
                        out = out + "_1";
                        break;
                    }
                    case 2: {
                        out = out + "_2";
                        break;
                    }
                    case 3: {
                        out = out + "_3";
                        break;
                    }
                    case 4: {
                        out = out + "_4";
                        break;
                    }
                    case 5: {
                        out = out + "_5";
                    }
                }
                break;
            }
            case 4: {
                switch (worldIn.field_73012_v.nextInt(2) + 1) {
                    case 1: {
                        out = out + "_1";
                        break;
                    }
                    case 2: {
                        out = out + "_2";
                    }
                }
                break;
            }
            case 5: {
                out = out + "_1";
            }
        }
        System.out.println("ds aa222 " + out);
        WorldGenCustomStructures.generateInPosition(new WorldGenStructure(out), rand, worldIn, basePos, 0, 0, 0);
    }
}

