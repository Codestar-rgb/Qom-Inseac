/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyBase;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyB1
extends WorldGenParasiteColonyBase {
    public WorldGenParasiteColonyB1(boolean notify, int stage) {
        super(notify, stage);
        this.wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));
        this.tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
        this.floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        BlockPos enter = posss;
        this.replaceCircleGround(worldIn, posss.func_177977_b(), 12, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(2), 12, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        this.replaceCircleGround(worldIn, posss.func_177979_c(3), 12, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.RED)));
        int missing = 40;
        int height = 22 + rand.nextInt(3);
        int xx = 2;
        int zz = 2;
        int tic = 2;
        int cool = 3;
        int changeX = 0;
        int changeZ = 0;
        for (int i = 0; i < height; ++i) {
            --changeZ;
            if (worldIn.field_73012_v.nextInt(2) == 0 && --changeX <= 0) {
                if (worldIn.field_73012_v.nextInt(3) == 0) {
                    xx = Math.min(9, xx + 2);
                    zz = Math.min(9, zz + 2);
                } else {
                    xx = Math.max(3, xx - 1);
                    zz = Math.max(3, zz - 1);
                }
                changeX = cool;
            }
            this.generateCircle(SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BONE)), SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss, xx, zz, 1, 20000, 6);
            int hx = xx - tic;
            int hz = zz - tic;
            this.generateCircle(SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss, hx, hz, 1, 20000, 6);
            posss = posss.func_177981_b(1);
        }
        BlockPos he = posss;
        int aa = zz;
        int bonusH = rand.nextInt(5);
        posss = posss.func_177981_b(18 + bonusH - zz / 2 * 2);
        if (rand.nextBoolean()) {
            this.generateSphere(worldIn, posss, zz + 1, 2, rand, false, 1, false, 1, 1, 5, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FLESH)), SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), Blocks.field_150350_a.func_176223_P(), missing);
        }
        this.generateDNAHelix(Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, he.func_177979_c(4), aa - 2, 2, 11 + bonusH);
        this.generateDNAHelix(SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, he.func_177979_c(3), aa - 2, 2, 11 + bonusH);
        this.generateDNAHelix(Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, he.func_177979_c(2), aa - 2, 2, 11 + bonusH);
        return true;
    }
}

