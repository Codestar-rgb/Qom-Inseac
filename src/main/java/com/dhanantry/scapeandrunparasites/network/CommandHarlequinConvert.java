package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.convert.HarlequinBlockConverter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandHarlequinConvert extends CommandBase {
   public String func_71517_b() {
      return "harlequin_convert";
   }

   public String func_71518_a(ICommandSender sender) {
      return "/harlequin_convert [radius] [sampleChance] [depth] [blotchChance] [blotchMinDiam] [blotchMaxDiam]";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      World world = sender.func_130014_f_();
      if (world.field_72995_K) {
         sender.func_145747_a(new TextComponentString("[HarlequinConvert] (client) – no changes made"));
      } else {
         int radius = args.length >= 1 ? func_175764_a(args[0], 1, 256) : 16;
         int sampleChance = args.length >= 2 ? func_175764_a(args[1], 1, 128) : 1;
         int depth = args.length >= 3 ? func_175764_a(args[2], 1, 256) : 32;
         int blotchChance = args.length >= 4 ? func_175764_a(args[3], 1, 256) : 18;
         int blotchMinDiam = args.length >= 5 ? func_175764_a(args[4], 1, 64) : 3;
         int blotchMaxDiam = args.length >= 6 ? func_175764_a(args[5], 1, 64) : 5;
         BlockPos center = sender.func_180425_c();
         Random rand = world.field_73012_v;
         MutableBlockPos pos = new MutableBlockPos(center);
         HarlequinBlockConverter.Config cfg = new HarlequinBlockConverter.Config(
            SRPBlocks.HarleskinnBlock,
            SRPBlocks.HarlequinnGrass,
            SRPBlocks.Alveoli,
            SRPBlocks.AlveoliGrowth,
            SRPBlocks.LipomaMass,
            SRPBlocks.TressesHair,
            SRPBlocks.HirsuteHair
         );
         int changed = 0;
         Set<Long> blotchXZ = new HashSet<>();
         int blotchesSeeded = 0;
         int blotchCellsMarked = 0;
         int blotchCellsConverted = 0;

         for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
               int x = center.func_177958_n() + dx;
               int z = center.func_177952_p() + dz;
               boolean seedThisColumn = blotchChance <= 1 || rand.nextInt(Math.max(2, blotchChance)) == 0;
               if (seedThisColumn) {
                  int minD = Math.min(blotchMinDiam, blotchMaxDiam);
                  int maxD = Math.max(blotchMinDiam, blotchMaxDiam);
                  int diameter = minD + rand.nextInt(maxD - minD + 1);
                  int r = diameter / 2;
                  boolean anyMarked = false;

                  for (int ox = -r; ox <= r; ox++) {
                     for (int oz = -r; oz <= r; oz++) {
                        if (ox * ox + oz * oz <= r * r) {
                           int sx = x + ox;
                           int sz = z + oz;
                           int ySurf = world.func_175645_m(new BlockPos(sx, 0, sz)).func_177956_o() - 1;
                           if (ySurf >= 0) {
                              BlockPos top = new BlockPos(sx, ySurf, sz);
                              IBlockState sTop = world.func_180495_p(top);
                              Block topB = sTop.func_177230_c();
                              boolean wouldBecomeHarlequinn = world.func_175678_i(top.func_177984_a())
                                 && (
                                    sTop.func_177230_c() instanceof BlockSand
                                       || sTop.func_177230_c() instanceof BlockSandStone
                                       || topB == Blocks.field_180395_cM
                                       || topB == Blocks.field_150348_b
                                 );
                              if ((topB == Blocks.field_150349_c || wouldBecomeHarlequinn) && blotchXZ.add(keyXZ(sx, sz))) {
                                 blotchCellsMarked++;
                                 anyMarked = true;
                              }
                           }
                        }
                     }
                  }

                  if (anyMarked) {
                     blotchesSeeded++;
                  }
               }
            }
         }

         for (int dx = -radius; dx <= radius; dx++) {
            for (int dzx = -radius; dzx <= radius; dzx++) {
               int x = center.func_177958_n() + dx;
               int z = center.func_177952_p() + dzx;
               int yTop = world.func_175645_m(new BlockPos(x, 0, z)).func_177956_o() - 1;
               if (yTop >= 0) {
                  for (int y = yTop; y >= Math.max(5, yTop - depth); y--) {
                     pos.func_181079_c(x, y, z);
                     IBlockState state = world.func_180495_p(pos);
                     Block b = state.func_177230_c();
                     boolean blotchHere = blotchXZ.contains(keyXZ(x, z));
                     if (blotchHere || b instanceof BlockLeaves || rand.nextInt(Math.max(1, sampleChance)) == 0) {
                        Material mat = state.func_185904_a();
                        if (mat != Material.field_151579_a && mat != Material.field_151586_h && mat != Material.field_151587_i) {
                           Block replacedWith = HarlequinBlockConverter.convert(world, pos, state, blotchHere, rand, cfg);
                           if (replacedWith != null) {
                              if (replacedWith != cfg.ALVEOLI && rand.nextInt(100) < 30) {
                                 BlockPos below = pos.func_177977_b();
                                 if (world.func_175623_d(below)) {
                                    world.func_180501_a(below, cfg.LIPOMA.func_176223_P(), 2);
                                 }
                              }

                              changed++;
                              if (blotchHere && state.func_177230_c() == Blocks.field_150349_c && replacedWith == cfg.HARLESKINN) {
                                 blotchCellsConverted++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         sender.func_145747_a(
            new TextComponentString(
               "Harlequin convert: changed ~"
                  + changed
                  + " | blotches: "
                  + blotchesSeeded
                  + " | cells marked: "
                  + blotchCellsMarked
                  + " | cells converted: "
                  + blotchCellsConverted
                  + " | radius "
                  + radius
                  + " | 1-in-"
                  + sampleChance
                  + " | depth "
                  + depth
                  + " | blotchChance 1-in-"
                  + Math.max(1, blotchChance)
                  + " | blotchDiam "
                  + Math.min(blotchMinDiam, blotchMaxDiam)
                  + ".."
                  + Math.max(blotchMinDiam, blotchMaxDiam)
            )
         );
      }
   }

   private static long keyXZ(int x, int z) {
      return (long)x << 32 ^ z & 4294967295L;
   }
}
