package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SRPCommandUDevelopment implements ICommand {
   private final List aliases = new ArrayList();

   private static void msg(ICommandSender sender, String key, Object... args) {
      sender.func_145747_a(new TextComponentTranslation(key, args));
   }

   public SRPCommandUDevelopment() {
      this.aliases.add("srpudevelopment");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srpudevelopment";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srpudevelopment <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (!SRPConfigSystems.useEvolution) {
            sender.func_145747_a(new TextComponentString("Evolution levels are not active"));
            return;
         }

         if (argString.length == 0) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("getlevel")) {
            SRPSaveData data = SRPSaveData.get(world, 59);
            int id = world.field_73011_w.getDimension();
            sender.func_145747_a(new TextComponentString(" ======> \n -> Current Ubiquitous Development Level: " + data.getDeveLevel()));
            return;
         }

         if (argString[0].equals("setlevel")) {
            try {
               String nani = argString[1];
               int var24 = Integer.parseInt(nani);
            } catch (NumberFormatException var12) {
               sender.func_145747_a(new TextComponentString("Invalid arg"));
               return;
            }

            String nani = argString[1];
            int option = Integer.parseInt(nani);
            SRPSaveData data = SRPSaveData.get(world, 63);
            if (option >= 5) {
               sender.func_145747_a(new TextComponentString("Invalid argument: level too high"));
               return;
            }

            if (option <= -1) {
               sender.func_145747_a(new TextComponentString("Invalid argument: level too low"));
               return;
            }

            SRPSaveData.falseLevel = option;
            sender.func_145747_a(new TextComponentString("Changed Ubiquitous Development Level to " + argString[1]));
            sender.func_145747_a(new TextComponentString("Set this value to 0 to remove fake level"));
            return;
         }

         if (argString[0].equals("viewalldims")) {
            SRPSaveData datax = SRPSaveData.get(world, -1);
            ArrayList<Integer> phases = datax.getColonies("p");
            ArrayList<Integer> dims = datax.getColonies("d");
            ArrayList<Integer> kills = datax.getColonies("k");
            ArrayList<Integer> gen = datax.getColonies("g");
            String out = "Current Parasite progress in dimensions \n (id, phase, points, generation): \n";

            for (int i = 0; i < dims.size(); i++) {
               out = out + "-> [" + dims.get(i) + ", " + phases.get(i) + ", " + kills.get(i) + ", " + gen.get(i) + "] \n";
            }

            sender.func_145747_a(new TextComponentString(out));
            return;
         }

         if (argString[0].equals("setdimevolution")) {
            try {
               String nanix = argString[1];
               String nani2 = argString[2];
               int optionx = Integer.parseInt(nanix);
               int phase = Integer.parseInt(nani2);
            } catch (NumberFormatException var14) {
               msg(sender, "command.srpevolution.error.invalid_arg");
               return;
            }

            String nanix = argString[1];
            String nani2 = argString[2];
            int id = Integer.parseInt(nanix);
            int phase = Integer.parseInt(nani2);
            SRPSaveData datax = SRPSaveData.get(world, 633);
            if (phase >= 11) {
               msg(sender, "command.srpevolution.setphase.error.phase_too_high");
               return;
            }

            if (phase <= -3) {
               msg(sender, "command.srpevolution.setphase.error.phase_too_low");
               return;
            }

            if (phase == -1) {
               datax.setEvolutionPhase(id, (byte)phase, true, world);
               datax.setTotalKills(id, 100 * phase, false, world, true, 50);
            } else if (phase == -2) {
               datax.setTotalKills(id, 100 * phase, false, world, true, 51);
               datax.setEvolutionPhase(id, (byte)phase, true, world);
            } else {
               datax.setEvolutionPhase(id, (byte)phase, true, world);
            }

            if (argString.length == 3) {
               msg(sender, "command.srpevolution.getphase.dimension", argString[1]);
               msg(sender, "command.srpevolution.setphase.changed_phase", argString[2]);
               return;
            }

            try {
               nanix = argString[3];
               int optionx = Integer.parseInt(nanix);
            } catch (NumberFormatException var13) {
               msg(sender, "command.srpevolution.setphase.error.invalid_arg_evo");
               return;
            }

            nanix = argString[3];
            int optionx = Integer.parseInt(nanix);
            if (optionx >= 6) {
               msg(sender, "command.srpevolution.setphase.changed_phase", argString[3]);
               msg(sender, "command.srpevolution.setphase.error.generation_too_high");
               return;
            }

            if (optionx <= -1) {
               msg(sender, "command.srpevolution.setphase.changed_phase", argString[3]);
               msg(sender, "command.srpevolution.setphase.error.generation_too_low");
               return;
            }

            datax.setGeneration((byte)optionx, id);
            datax.setGenerationTime(0, id);
            msg(sender, "command.srpevolution.getphase.dimension", argString[1]);
            msg(sender, "command.srpevolution.setphase.changed_phase", argString[2]);
            msg(sender, "command.srpevolution.setphase.changed_generation", argString[3]);
            return;
         }

         sender.func_145747_a(new TextComponentString("Invalid command"));
      }
   }

   public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
      return sender.func_70003_b(2, this.func_71517_b());
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
      int i = 0;

      while (i < args.length) {
         i++;
      }

      List<String> atm = new ArrayList<>();
      atm.add("getlevel");
      atm.add("setlevel");
      atm.add("viewalldims");
      atm.add("setdimevolution");
      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }
}
