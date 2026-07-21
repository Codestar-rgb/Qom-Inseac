package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBiomes;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSpawning;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class SRPCommandRoot implements ICommand {
   private final List aliases = new ArrayList();

   public SRPCommandRoot() {
      this.aliases.add("srparasites");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srparasites";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srparasites <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (argString[0].equals("setgeneration")) {
            try {
               String nani = argString[1];
               int var23 = Integer.parseInt(nani);
            } catch (NumberFormatException var13) {
               sender.func_145747_a(new TextComponentString("Invalid arg"));
               return;
            }

            SRPSaveData data = SRPSaveData.get(world, 68);
            int id = world.field_73011_w.getDimension();
            String nani = argString[1];
            int option = Integer.parseInt(nani);
            if (option <= 5 && option >= 0) {
               data.setGeneration((byte)option, id);
               data.setGenerationTime((int)world.field_73011_w.getWorldTime(), id);
               sender.func_145747_a(new TextComponentString("Parasite Generation set to " + option));
               return;
            }

            sender.func_145747_a(new TextComponentString("Number too low or too high, please enter a number between 0 - 5"));
            return;
         }

         if (argString[0].equals("getgeneration")) {
            sender.func_145747_a(
               new TextComponentString("Current Parasite Generation " + SRPSaveData.get(world, 69).getGeneration(world.field_73011_w.getDimension()))
            );
            return;
         }

         if (argString[0].equals("spawnmeteor")) {
            int x;
            int y;
            int z;
            int rad;
            try {
               x = Integer.parseInt(argString[1]);
               y = Integer.parseInt(argString[2]);
               z = Integer.parseInt(argString[3]);
               rad = Integer.parseInt(argString[4]);
            } catch (NumberFormatException var15) {
               sender.func_145747_a(new TextComponentString("Invalid/Missing argument"));
               return;
            }

            ParasiteSummon.spawnMeteor(x, y, z, rad, rad, world);
         } else {
            if (argString[0].equals("readconfigurationfile")) {
               try {
                  if (SRPConfig.readConfig() && SRPConfigMobs.readConfig() && SRPConfigSystems.readConfig() && SRPConfigWorld.readConfig()) {
                     SRPAttributes.reset();
                     SRPAttributes.init();
                     SRPBlocks.init();
                     SRPBiomes.clearMobSpawnList();
                     SRPSpawning.initBiome();
                     SRPSpawning.removeInit();
                     SRPSpawning.init();
                     sender.func_145747_a(
                        new TextComponentString(
                           "Configutarion files were read successfully \n NOTE: Does not work for all options, such as registry or client-side options"
                        )
                     );
                  }
               } catch (Exception var14) {
                  SRPMain.logger.log(Level.ERROR, "Problem while reading configuration file", var14);
                  sender.func_145747_a(
                     new TextComponentString(
                        "There was a problem while reading configuration file, check inputs \n NOTE: Does not work for all options, such as registry or client-side options"
                     )
                  );
               }

               return;
            }

            if (argString[0].equals("toggle_dotiledrops")) {
               boolean current = !SRPConfig.doTileDrops;
               SRPConfig.doTileDrops = current;
               sender.func_145747_a(new TextComponentString("Current doTileDrop value is " + current));
               return;
            }

            if (argString[0].equals("toggle_domobevolution")) {
               boolean current = !ParasiteEventEntity.canSpawnNext;
               ParasiteEventEntity.canSpawnNext = current;
               sender.func_145747_a(new TextComponentString("Current doMobEvolution value is " + current));
               return;
            }

            if (argString[0].equals("resetdatafile")) {
               SRPWorldData data = SRPWorldData.get(world);
               data.resetInstance(world);
               sender.func_145747_a(new TextComponentString("Data file of this dimension has been reset"));
               return;
            }

            if (argString[0].equals("parasites")) {
               List<Entity> serverList = world.field_72996_f;
               int count = 0;

               for (int xx = 0; xx < serverList.size(); xx++) {
                  if (serverList.get(xx) instanceof EntityParasiteBase) {
                     count++;
                  }
               }

               int players = world.field_73010_i.size();
               players *= SRPConfig.worldMobCapPlusPlayer;
               SRPSaveData data = SRPSaveData.get(world, 70);
               int id = world.field_73011_w.getDimension();
               String[] here = new String[20];
               here = data.getCurrentCodeU(id).split(";");
               String atm = "";

               for (int k = 0; k < here.length; k++) {
                  if (k == here.length - 1) {
                     atm = atm + here[k];
                  } else {
                     atm = atm + here[k] + " ";
                  }
               }

               sender.func_145747_a(
                  new TextComponentString(
                     " ====== \n -> Dislodgment code: \n"
                        + atm
                        + " \n -> Current Parasite Mob Cap: "
                        + (SRPConfig.worldMobCap + players)
                        + " \n -> Number of current parasites: "
                        + count
                  )
               );
               return;
            }
         }
      }
   }

   public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
      return sender.func_70003_b(2, this.func_71517_b());
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
      List<String> atm = new ArrayList<>();
      if (args.length == 1) {
         atm.add("parasites");
         atm.add("spawnmeteor");
         atm.add("readconfigurationfile");
         atm.add("resetdatafile");
         atm.add("toggle_dotiledrops");
         atm.add("toggle_domobevolution");
         atm.add("setgeneration");
         atm.add("getgeneration");
      }

      if (args[0].equals("spawnmeteor") && args.length == 2) {
         atm.add("" + sender.func_180425_c().func_177958_n());
      }

      if (args.length == 3) {
         atm.add("" + sender.func_180425_c().func_177956_o());
      }

      if (args.length == 4) {
         atm.add("" + sender.func_180425_c().func_177952_p());
      }

      if (args.length == 5) {
         atm.add("" + new Random().nextInt(130));
      }

      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }
}
