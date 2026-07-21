package com.dhanantry.scapeandrunparasites.world.gen.structure;

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

public class WorldGenStructure extends WorldGenerator {
   public static String structureName;

   public WorldGenStructure(String name) {
      structureName = name;
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      generate(worldIn, position);
      return true;
   }

   public static void generate(World world, BlockPos pos) {
      MinecraftServer mcServer = world.func_73046_m();
      WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(0);
      TemplateManager manager = worldServer.func_184163_y();
      ResourceLocation location = new ResourceLocation("srparasites", structureName);
      Template template = manager.func_189942_b(mcServer, location);
      if (template != null) {
         IBlockState state = world.func_180495_p(pos);
         world.func_184138_a(pos, state, state, 3);
         PlacementSettings setting = new PlacementSettings().func_186214_a(Mirror.NONE).func_186220_a(Rotation.NONE);
         template.func_186260_a(world, pos, setting);
      }
   }
}
