/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockParasiteRubble;
import com.subspaceparasite.block.BlockParasiteRubbleDense;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBase;
import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNexusProtection3
extends WorldGenParasiteColonyBase {
    public WorldGenParasiteNexusProtection3(boolean notify, int stage) {
        super(notify, stage);
        this.wall = SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));
        this.tacle = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
        this.floor = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        int max;
        int min;
        int radius = 8;
        int steps = 64;
        IBlockState pillarBlock = SPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FUNGUS));
        IBlockState pillarBlock2 = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
        List<BlockPos> circle = this.getCirclePoints(posss, radius, steps);
        for (BlockPos pos : circle) {
            min = 1;
            max = 5;
            this.generatePillar(worldIn, pos, worldIn.field_73012_v.nextInt(max - min + 1) + min, pillarBlock, pillarBlock2);
        }
        pillarBlock = SPBlocks.ParasiteFog.func_176223_P();
        while (radius > 0) {
            circle = this.getCirclePoints(posss, --radius, steps);
            for (BlockPos pos : circle) {
                min = 7;
                max = 14;
                this.generatePillar(worldIn, pos, worldIn.field_73012_v.nextInt(max - min + 1) + min, pillarBlock, pillarBlock);
            }
        }
        this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss.func_177979_c(5), 8, 8, 5, 2, 0);
        this.generateCircle(SPBlocks.ParasiteFog.func_176223_P(), SPBlocks.ParasiteFog.func_176223_P(), worldIn, worldIn.field_73012_v, posss.func_177979_c(5), 4, 4, 5, 20000000, 0);
        this.replaceCircleGround(worldIn, posss.func_177977_b(), 8, pillarBlock2);
        return true;
    }
}

