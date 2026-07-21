package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
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

public class SRPCommandColony implements ICommand {
   private final List aliases = new ArrayList();

   public SRPCommandColony() {
      this.aliases.add("srpcolonies");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srpcolonies";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srpcolonies <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (!SRPConfigWorld.coloniesActivated) {
            sender.func_145747_a(new TextComponentString("Colonies are not activated"));
            return;
         }

         if (argString.length == 0) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("viewall")) {
            ParasiteEventWorld.checkColonyStatus(world);
            SRPWorldData data = SRPWorldData.get(world);
            ArrayList<Integer> coloniessX = data.getColonies("x");
            ArrayList<Integer> coloniessY = data.getColonies("y");
            ArrayList<Integer> coloniessZ = data.getColonies("z");
            ArrayList<Integer> coloniessA = data.getColonies("a");
            String out = "Current colonies in the world (x, y, z, points): \n";

            for (int i = 0; i < coloniessX.size(); i++) {
               out = out + "-> [" + coloniessX.get(i) + ", " + coloniessY.get(i) + ", " + coloniessZ.get(i) + ", " + coloniessA.get(i) + "] \n";
            }

            sender.func_145747_a(new TextComponentString(out));
            return;
         }

         if (argString[0].equals("resetglobaladaptation")) {
            SRPWorldData data = SRPWorldData.get(world);
            data.resetGlobalAdaptation();
            sender.func_145747_a(new TextComponentString("Global adaptation has been reset"));
            return;
         }

         if (argString[0].equals("viewallglobaladaptation")) {
            SRPWorldData data = SRPWorldData.get(world);
            ArrayList<Integer> a = data.getAdaptationI();
            ArrayList<String> b = data.getAdaptationS();
            String out = "Current global adaptation (Damage type, points): \n ";

            for (int i = 0; i < a.size(); i++) {
               out = out + "-> [" + b.get(i) + ", " + a.get(i) + "] \n";
            }

            sender.func_145747_a(new TextComponentString(out));
            return;
         }

         if (argString[0].equals("clearworld")) {
            SRPWorldData data = SRPWorldData.get(world);
            data.clearColonyList();
            sender.func_145747_a(new TextComponentString("There are no longer colonies in this world"));
            return;
         }

         if (argString.length != 4) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         int x;
         int y;
         int z;
         try {
            x = Integer.parseInt(argString[1]);
            y = Integer.parseInt(argString[2]);
            z = Integer.parseInt(argString[3]);
         } catch (NumberFormatException var12) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("setcolony")) {
            switch (ParasiteEventWorld.placeColonyInWorld(world, new BlockPos(x, y, z))) {
               case 1:
                  sender.func_145747_a(new TextComponentString("Colony placed at " + x + " " + y + " " + z));
                  break;
               case 2:
                  sender.func_145747_a(new TextComponentString("Colonies cannot be placed in this dimension"));
                  break;
               case 3:
                  sender.func_145747_a(new TextComponentString("Colonies are not activated"));
                  break;
               case 4:
                  sender.func_145747_a(new TextComponentString("Evolution Phase or UD level is not high enough to place a Colony"));
                  break;
               case 5:
                  sender.func_145747_a(new TextComponentString("Unable to find a place for the Colony "));
                  break;
               case 6:
                  sender.func_145747_a(new TextComponentString("Colony too close to another Colony "));
                  break;
               case 7:
                  sender.func_145747_a(new TextComponentString("Maximum number of Colonies reached "));
                  break;
               default:
                  sender.func_145747_a(new TextComponentString("Unknown - Colonies "));
            }

            return;
         }

         if (argString[0].equals("removecolony")) {
            if (ParasiteEventWorld.removeColonyInWorld(world, new BlockPos(x, y, z))) {
               sender.func_145747_a(new TextComponentString("Colony removed at " + x + " " + y + " " + z));
            } else {
               sender.func_145747_a(new TextComponentString("Colony cannot be removed at " + x + " " + y + " " + z));
            }

            return;
         }
      }
   }

   public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
      return sender.func_70003_b(2, this.func_71517_b());
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
      List<String> atm = new ArrayList<>();
      if (args.length == 1) {
         atm.add("viewall");
         atm.add("clearworld");
         atm.add("setcolony");
         atm.add("removecolony");
         atm.add("resetglobaladaptation");
         atm.add("viewallglobaladaptation");
      }

      if ((args[0].equals("setcolony") || args[0].equals("removecolony")) && args.length == 2) {
         atm.add("" + sender.func_180425_c().func_177958_n());
      }

      if (args.length == 3) {
         atm.add("" + sender.func_180425_c().func_177956_o());
      }

      if (args.length == 4) {
         atm.add("" + sender.func_180425_c().func_177952_p());
      }

      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }
}
