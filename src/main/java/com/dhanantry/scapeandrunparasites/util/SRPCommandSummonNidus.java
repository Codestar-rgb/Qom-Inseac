package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteNexusProtection1;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SRPCommandSummonNidus extends CommandBase {
   public String func_71517_b() {
      return "srp_summon_nidus";
   }

   public String func_71518_a(ICommandSender sender) {
      return "/srp_summon_nidus [x y z] [stage]";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      World world = sender.func_130014_f_();
      if (world.field_72995_K) {
         sender.func_145747_a(new TextComponentString("This command must be run server-side."));
      } else {
         int stage = 1;
         BlockPos pos;
         if (args.length == 0) {
            pos = sender.func_180425_c();
         } else {
            if (args.length != 3 && args.length != 4) {
               throw new WrongUsageException(this.func_71518_a(sender), new Object[0]);
            }

            pos = func_175757_a(sender, args, 0, false);
            if (args.length == 4) {
               stage = func_180528_a(args[3], 1);
            }
         }

         boolean generated = new WorldGenParasiteNexusProtection1(false, stage).func_180709_b(world, new Random(), pos.func_177977_b());
         sender.func_145747_a(
            new TextComponentString(
               generated
                  ? "Generated Nidus/Nexus protection structure at "
                     + pos.func_177958_n()
                     + " "
                     + pos.func_177956_o()
                     + " "
                     + pos.func_177952_p()
                     + " with stage "
                     + stage
                  : "Nidus/Nexus protection structure generation returned false."
            )
         );
      }
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length >= 1 && args.length <= 3) {
         return func_175771_a(args, 0, targetPos);
      } else {
         return args.length == 4 ? func_71530_a(args, new String[]{"1", "2", "3", "4"}) : Collections.emptyList();
      }
   }
}
