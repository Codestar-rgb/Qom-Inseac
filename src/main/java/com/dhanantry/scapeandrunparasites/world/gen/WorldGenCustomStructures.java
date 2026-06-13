/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.IChunkGenerator
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.fml.common.IWorldGenerator
 */
package com.dhanantry.scapeandrunparasites.world.gen;

import com.dhanantry.scapeandrunparasites.world.gen.structure.WorldGenStructure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomStructures
implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?> ... classes) {
        ArrayList classList = new ArrayList(Arrays.asList(classes));
        int x = chunkX * 16 + random.nextInt(15);
        int z = chunkZ * 16 + random.nextInt(15);
        int y = WorldGenCustomStructures.calculateHeight(world, x, z, topBlock);
        BlockPos pos = new BlockPos(x, y, z);
        Class<?> biome = world.field_73011_w.getBiomeForCoords(pos).getClass();
        boolean debug = true;
        if ((world.func_175624_G() != WorldType.field_77138_c || debug) && (classList.contains(biome) || debug) && random.nextInt(chance) == 0) {
            generator.func_180709_b(world, random, pos);
        }
    }

    private static int calculateHeight(World world, int x, int z, Block topBlock) {
        int y = world.func_72800_K();
        boolean flag = false;
        while (!flag && y-- >= 0) {
            Block block = world.func_180495_p(new BlockPos(x, y, z)).func_177230_c();
            flag = block == topBlock;
        }
        return y;
    }

    public static void generateInPosition(WorldGenStructure generator, Random random, World world, BlockPos pos, int offsetX, int offsetY, int offsetZ) {
        generator.func_180709_b(world, random, new BlockPos(pos.func_177958_n() + offsetX, pos.func_177956_o() + offsetY, pos.func_177952_p() + offsetZ));
    }
}

