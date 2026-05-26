/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.Mirror
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraft.world.gen.structure.template.PlacementSettings
 *  net.minecraft.world.gen.structure.template.Template
 *  net.minecraft.world.gen.structure.template.TemplateManager
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package com.subspaceparasite.world.gen.structure;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class WorldGenStructure
extends WorldGenerator {
    public static String structureName;

    public WorldGenStructure(String name) {
        structureName = name;
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        WorldGenStructure.generate(worldIn, position);
        return true;
    }

    public static void generate(World world, BlockPos pos) {
        ResourceLocation location;
        MinecraftServer mcServer = world.func_73046_m();
        WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(0);
        TemplateManager manager = worldServer.func_184163_y();
        Template template = manager.func_189942_b(mcServer, location = new ResourceLocation("subspaceparasite", structureName));
        if (template != null) {
            IBlockState state = world.func_180495_p(pos);
            world.func_184138_a(pos, state, state, 3);
            PlacementSettings setting = new PlacementSettings().func_186214_a(Mirror.NONE).func_186220_a(Rotation.NONE);
            template.func_186260_a(world, pos, setting);
        }
    }
}

