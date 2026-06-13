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

public class WorldGenParasiteColonyB4
extends WorldGenParasiteColonyBase {
    public WorldGenParasiteColonyB4(boolean notify, int stage) {
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
        this.generateSphere(worldIn, posss, 4, 3, rand, false, 7, false, 3, 1, 5, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = posss.func_177981_b(16);
        int radius = 6;
        double theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        this.generateSphere(worldIn, posss, 5, 3, rand, false, 3, false, 2, 1, 5, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        posss = posss.func_177981_b(16);
        if (rand.nextBoolean()) {
            this.addEntrance(worldIn, rand, enter, 5);
            return true;
        }
        radius = 3;
        theta = rand.nextDouble() * 2.0 * Math.PI;
        posss = this.getCirclePoint(posss, radius, theta);
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 3, false, 2, 2, 5, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL)), SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.BRICKS)), Blocks.field_150350_a.func_176223_P(), missing);
        this.addEntrance(worldIn, rand, enter, 5);
        return true;
    }
}

