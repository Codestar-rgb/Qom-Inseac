/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.world.gen.feature;

import com.subspaceparasite.block.BlockParasiteRubbleDense;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBase;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNexusProtection1
extends WorldGenParasiteColonyBase {
    public WorldGenParasiteNexusProtection1(boolean notify, int stage) {
        super(notify, stage);
        this.wall = SPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));
        this.tacle = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
        this.floor = SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
        this.generateSphere(worldIn, posss, 3, 3, rand, false, 6, false, 1, 1, 5, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)), SPBlocks.ParasiteFog.func_176223_P(), 2);
        this.generateCircle(SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)), SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)), worldIn, worldIn.field_73012_v, posss.func_177979_c(5), 6, 6, 5, 2, 0);
        this.generateCircle(SPBlocks.ParasiteFog.func_176223_P(), SPBlocks.ParasiteFog.func_176223_P(), worldIn, worldIn.field_73012_v, posss.func_177979_c(5), 4, 4, 5, 20000000, 0);
        this.replaceCircleGround(worldIn, posss, 8, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)));
        return true;
    }
}

