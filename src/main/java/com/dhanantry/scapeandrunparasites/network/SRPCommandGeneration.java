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
import net.minecraft.world.World;

public class SRPCommandGeneration implements ICommand {
   private final List aliases = new ArrayList();

   public SRPCommandGeneration() {
      this.aliases.add("srpgeneration");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srpgeneration";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srpgeneration <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (!SRPConfigSystems.generationUse) {
            sender.func_145747_a(new TextComponentString("Generation is not active"));
            return;
         }

         if (argString.length == 0) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("setgeneration")) {
            try {
               String nani = argString[1];
               int var17 = Integer.parseInt(nani);
            } catch (NumberFormatException var10) {
               sender.func_145747_a(new TextComponentString("Invalid arg"));
               return;
            }

            String nani = argString[1];
            int option = Integer.parseInt(nani);
            SRPSaveData data = SRPSaveData.get(world, 66);
            if (option >= 6) {
               sender.func_145747_a(new TextComponentString("Invalid argument: generation too high"));
               return;
            }

            if (option <= -1) {
               sender.func_145747_a(new TextComponentString("Invalid argument: generation too low"));
               return;
            }

            data.setGeneration((byte)option, world.field_73011_w.getDimension());
            data.setGenerationTime(0, world.field_73011_w.getDimension());
            sender.func_145747_a(new TextComponentString("Changed Generation of Parasites to " + argString[1]));
            return;
         }

         if (argString[0].equals("getgeneration")) {
            SRPSaveData datax = SRPSaveData.get(world, 67);
            sender.func_145747_a(new TextComponentString("Current Generation of Parasites: " + datax.getGeneration(world.field_73011_w.getDimension())));
            return;
         }

         if (argString[0].equals("addticks")) {
            try {
               String nanix = argString[1];
               int optionx = Integer.parseInt(nanix);
            } catch (NumberFormatException var11) {
               sender.func_145747_a(new TextComponentString("Invalid arg"));
               return;
            }

            String nanix = argString[1];
            int optionx = Integer.parseInt(nanix);
            SRPSaveData datax = SRPSaveData.get(world, 6777);
            int id = world.field_73011_w.getDimension();
            int value = datax.getGenerationTime(id) + optionx;
            datax.setGenerationTime(value, id);
            sender.func_145747_a(new TextComponentString("Added: " + optionx + " to the current Time"));
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
      atm.add("setgeneration");
      atm.add("getgeneration");
      atm.add("addticks");
      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }
}
