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
import net.minecraft.world.biome.Biome.TempCategory;

public class SRPCommandNode implements ICommand {
   private final List aliases = new ArrayList();

   public SRPCommandNode() {
      this.aliases.add("srpnodes");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srpnodes";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srpnodes <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (!SRPConfigWorld.nodesActivated) {
            sender.func_145747_a(new TextComponentString("Nodes are not activated"));
            return;
         }

         if (!SRPConfigWorld.biomeRegster) {
            sender.func_145747_a(new TextComponentString("Biome is not activated"));
            return;
         }

         if (argString.length == 0) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("viewall")) {
            ParasiteEventWorld.checkNodeStatus(world);
            SRPWorldData data = SRPWorldData.get(world);
            ArrayList<Integer> nodesX = data.getNodes("x");
            ArrayList<Integer> nodesY = data.getNodes("y");
            ArrayList<Integer> nodesZ = data.getNodes("z");
            ArrayList<Integer> nodesA = data.getNodes("a");
            ArrayList<Integer> nodesT = data.getNodes("t");
            String out = "Current nodes in the world (x, y, z, age, type): \n";

            for (int i = 0; i < nodesX.size(); i++) {
               out = out + "-> [" + nodesX.get(i) + ", " + nodesY.get(i) + ", " + nodesZ.get(i) + ", " + nodesA.get(i) + ", " + nodesT.get(i) + "] \n";
            }

            sender.func_145747_a(new TextComponentString(out));
            return;
         }

         if (argString[0].equals("clearworld")) {
            SRPWorldData data = SRPWorldData.get(world);
            data.clearNodeList();
            sender.func_145747_a(new TextComponentString("There are no longer nodes in this world"));
            return;
         }

         if (argString.length != 5) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         int x;
         int y;
         int z;
         int t;
         try {
            x = Integer.parseInt(argString[1]);
            y = Integer.parseInt(argString[2]);
            z = Integer.parseInt(argString[3]);
            t = Integer.parseInt(argString[4]);
         } catch (NumberFormatException var13) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("setnode")) {
            int typeB = 1;
            BlockPos posss = new BlockPos(x, y, z);
            if (world.func_180494_b(posss).func_76736_e()) {
               if (world.func_180494_b(posss).func_150561_m() == TempCategory.WARM) {
                  typeB = 1;
               } else {
                  typeB = 3;
               }
            } else if (world.func_180494_b(posss).func_150561_m() == TempCategory.COLD) {
               typeB = 4;
            } else if (world.func_180494_b(posss).func_150561_m() == TempCategory.WARM && !world.func_180494_b(posss).func_76738_d()) {
               typeB = 2;
            }

            int key = ParasiteEventWorld.placeHeartInWorld(world, posss, t != -1 && t <= 4 ? t : typeB);
            switch (key) {
               case 1:
                  sender.func_145747_a(new TextComponentString("Node placed at " + x + " " + y + " " + z));
                  break;
               case 2:
                  sender.func_145747_a(new TextComponentString("Nodes cannot be placed in this dimension"));
                  break;
               case 3:
                  sender.func_145747_a(new TextComponentString("Nodes are not activated"));
                  break;
               case 4:
                  sender.func_145747_a(new TextComponentString("Biome is not activated"));
                  break;
               case 5:
                  sender.func_145747_a(new TextComponentString("Node is too close to spawn point"));
                  break;
               case 6:
                  sender.func_145747_a(new TextComponentString("Evolution Phase or UD level is not high enough to place a Node "));
                  break;
               case 7:
                  sender.func_145747_a(new TextComponentString("Unable to find a place for the Node"));
                  break;
               case 8:
                  sender.func_145747_a(new TextComponentString("Node is too close to another Node"));
                  break;
               case 9:
                  sender.func_145747_a(new TextComponentString("Maximum number of Nodes reached"));
                  break;
               default:
                  sender.func_145747_a(new TextComponentString("Unknown - Nodes " + key));
            }

            return;
         }

         if (argString[0].equals("removenode")) {
            if (ParasiteEventWorld.removeHeartInWorld(world, new BlockPos(x, y, z))) {
               sender.func_145747_a(new TextComponentString("Node removed at " + x + " " + y + " " + z));
            } else {
               sender.func_145747_a(new TextComponentString("Node cannot be removed at " + x + " " + y + " " + z));
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
         atm.add("setnode");
         atm.add("removenode");
      }

      if ((args[0].equals("setnode") || args[0].equals("removenode")) && args.length == 2) {
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
