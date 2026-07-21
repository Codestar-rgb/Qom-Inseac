package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanHaveBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SRPCommandEvolution implements ICommand {
   private final List aliases = new ArrayList();

   private static void msg(ICommandSender sender, String key, Object... args) {
      sender.func_145747_a(new TextComponentTranslation(key, args));
   }

   public SRPCommandEvolution() {
      this.aliases.add("srpevolution");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srpevolution";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srpevolution <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (!SRPConfigSystems.useEvolution) {
            msg(sender, "command.srpevolution.error.evolution_disabled");
            return;
         }

         if (argString.length == 0) {
            msg(sender, "command.srpevolution.error.invalid_argument");
            return;
         }

         if (argString[0].equals("set_evolutionloss")) {
            SRPSaveData data = SRPSaveData.get(world, 57);
            if (argString[1].equals("true")) {
               data.setLoss(true, world.field_73011_w.getDimension());
               msg(sender, "command.srpevolution.evolutionloss.set.true");
            } else if (argString[1].equals("false")) {
               data.setLoss(false, world.field_73011_w.getDimension());
               msg(sender, "command.srpevolution.evolutionloss.set.false");
            } else {
               msg(sender, "command.srpevolution.error.invalid_arg");
            }

            return;
         }

         if (argString[0].equals("set_evolutiongaining")) {
            SRPSaveData data = SRPSaveData.get(world, 58);
            if (argString[1].equals("true")) {
               data.setGaining(true, world.field_73011_w.getDimension());
               msg(sender, "command.srpevolution.evolutiongaining.set.true");
            } else if (argString[1].equals("false")) {
               data.setGaining(false, world.field_73011_w.getDimension());
               msg(sender, "command.srpevolution.evolutiongaining.set.false");
            } else {
               msg(sender, "command.srpevolution.error.invalid_arg");
            }

            return;
         }

         if (argString[0].equals("getphase")) {
            List<Entity> serverList = world.field_72996_f;
            int count = 0;
            int coth = 0;

            for (int x = 0; x < serverList.size(); x++) {
               if (serverList.get(x) instanceof EntityParasiteBase) {
                  if (serverList.get(x) instanceof EntityCanHaveBodies) {
                     EntityCanHaveBodies bodies = (EntityCanHaveBodies)serverList.get(x);
                     if (bodies.getBodyNumber() == 0) {
                        count++;
                     }
                  } else {
                     count++;
                  }
               }
            }

            for (int xx = 0; xx < serverList.size(); xx++) {
               if (serverList.get(xx) instanceof EntityLivingBase && ((EntityLivingBase)serverList.get(xx)).func_70644_a(SRPPotions.COTH_E)) {
                  coth++;
               }
            }

            SRPSaveData data = SRPSaveData.get(world, 59);
            int players = world.field_73010_i.size();
            players *= SRPConfig.worldMobCapPlusPlayer;
            int id = world.field_73011_w.getDimension();
            ArrayList halo = SRPWorldData.get(world).getorigins("x");
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

            msg(sender, "command.srpevolution.getphase.header");
            msg(sender, "command.srpevolution.getphase.dimension", world.field_73011_w.getDimension());
            msg(sender, "command.srpevolution.getphase.phase", data.getEvolutionPhase(id));
            msg(sender, "command.srpevolution.getphase.total_points", data.getTotalKills(id));
            msg(sender, "command.srpevolution.getphase.points_next", getNeededPoints((byte)(data.getEvolutionPhase(id) + 1)));
            double progress = (double)data.getTotalKills(id) / getNeededPoints((byte)(data.getEvolutionPhase(id) + 1)) * 100.0;
            String progStr = String.format(Locale.ROOT, "%.1f", progress);
            msg(sender, "command.srpevolution.getphase.progress", progStr);
            msg(sender, "command.srpevolution.getphase.cooldown", data.getCooldown(world, id));
            msg(sender, "command.srpevolution.getphase.gaining", String.valueOf(data.getCanGain(id)));
            msg(sender, "command.srpevolution.getphase.loss", String.valueOf(data.getCanLoss(id)));
            msg(sender, "command.srpevolution.getphase.dislodgement");
            msg(sender, "command.srpevolution.getphase.dislodgement.value", atm);
            msg(sender, "command.srpevolution.getphase.mobcap", SRPConfig.worldMobCap + players);
            msg(sender, "command.srpevolution.getphase.generation", data.getGeneration(id));
            msg(sender, "command.srpevolution.getphase.gen_ticks", data.getGenerationNeededTime(world, id));
            msg(sender, "command.srpevolution.getphase.udl", data.getDeveLevel());
            msg(sender, "command.srpevolution.getphase.parasite_count", count);
            msg(sender, "command.srpevolution.getphase.coth_count", coth);
            msg(sender, "command.srpevolution.getphase.eivs", halo == null ? 0 : halo.size());
            if (sender instanceof EntityPlayer) {
               EntityPlayer playerIn = (EntityPlayer)sender;
               BlockPos pos = SRPWorldData.get(world).nearestInfectionPosition(false, playerIn.func_180425_c());
               boolean flag = pos != null;
               if (flag) {
                  msg(sender, " -> WITHIN VECTOR: " + flag);
               } else if (halo.size() > 0) {
                  pos = SRPWorldData.get(world).nearestInfectionPosition(true, playerIn.func_180425_c());
                  int dis = (int)playerIn.func_70011_f(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
                  int area = SRPWorldData.get(world).nearestInfectionValueArea(playerIn.func_180425_c(), false);
                  msg(sender, " -> WITHIN VECTOR: " + flag);
                  msg(sender, " -> Distance to nearest EIV: " + (dis - area));
               }
            }

            msg(sender, "command.srpevolution.getphase.footer");
            return;
         }

         if (argString[0].equals("evolutionlock_reset")) {
            SRPSaveData data = SRPSaveData.get(world, 60);
            data.resetLock();
            msg(sender, "command.srpevolution.evolutionlock.reset");
            return;
         }

         if (argString[0].equals("evolutionlock_unlockall")) {
            SRPSaveData data = SRPSaveData.get(world, 60);
            data.unlockAllParasite();
            msg(sender, "command.srpevolution.evolutionlock.unlockall");
            return;
         }

         if (argString[0].equals("evolutionlock_getlist")) {
            SRPSaveData data = SRPSaveData.get(world, 65);
            ArrayList<Integer> nodesX = data.getLockedList();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < nodesX.size(); i++) {
               sb.append(nodesX.get(i)).append(" ");
            }

            msg(sender, "command.srpevolution.evolutionlock.getlist", sb.toString());
            return;
         }

         if (argString[0].equals("addpoints")) {
            try {
               String nani = argString[1];
               int var49 = Integer.parseInt(nani);
            } catch (NumberFormatException var22) {
               msg(sender, "command.srpevolution.error.invalid_arg");
               return;
            }

            SRPSaveData data = SRPSaveData.get(world, 61);
            int id = world.field_73011_w.getDimension();
            if (data.getEvolutionPhase(id) == -2) {
               msg(sender, "command.srpevolution.addpoints.error.phase_minus2");
               return;
            }

            if (data.getCooldown(world, id) != 0) {
               msg(sender, "command.srpevolution.addpoints.error.cooldown");
               return;
            }

            String nani = argString[1];
            int option = Integer.parseInt(nani);
            if (!data.getCanGain(id) && option > 0) {
               msg(sender, "command.srpevolution.addpoints.error.cannot_gain");
               return;
            }

            if (!data.getCanLoss(id) && option < 0) {
               msg(sender, "command.srpevolution.addpoints.error.cannot_lose");
               return;
            }

            boolean flagg = data.setTotalKills(world.field_73011_w.getDimension(), option, true, world, true, true, 47);
            if (flagg) {
               if (Math.abs(option) == 1) {
                  msg(sender, "command.srpevolution.addpoints.success.one", option);
               } else {
                  msg(sender, "command.srpevolution.addpoints.success.many", option);
               }

               return;
            }

            msg(sender, "command.srpevolution.error.generic");
            return;
         }

         if (argString[0].equals("setcooldown")) {
            try {
               String nanix = argString[1];
               int var47 = Integer.parseInt(nanix);
            } catch (NumberFormatException var23) {
               msg(sender, "command.srpevolution.error.invalid_arg");
               return;
            }

            String nanix = argString[1];
            int optionx = Integer.parseInt(nanix);
            if (optionx < 0) {
               msg(sender, "command.srpevolution.cooldown.error.negative");
               return;
            }

            SRPSaveData datax = SRPSaveData.get(world, 62);
            msg(sender, "command.srpevolution.cooldown.set", optionx);
            datax.setCooldown(optionx, world, world.field_73011_w.getDimension(), false);
            return;
         }

         if (argString[0].equals("addcooldown")) {
            try {
               String nanix = argString[1];
               int var45 = Integer.parseInt(nanix);
            } catch (NumberFormatException var24) {
               msg(sender, "command.srpevolution.error.invalid_arg");
               return;
            }

            String nanix = argString[1];
            int optionx = Integer.parseInt(nanix);
            if (optionx < 0) {
               msg(sender, "command.srpevolution.cooldown.error.negative");
               return;
            }

            SRPSaveData datax = SRPSaveData.get(world, 64);
            msg(sender, "command.srpevolution.cooldown.set", optionx);
            datax.setCooldown(optionx, world, world.field_73011_w.getDimension(), true);
            return;
         }

         if (argString[0].equals("setphase")) {
            try {
               String nanix = argString[1];
               int optionx = Integer.parseInt(nanix);
            } catch (NumberFormatException var26) {
               msg(sender, "command.srpevolution.error.invalid_arg");
               return;
            }

            String nanix = argString[1];
            int optionx = Integer.parseInt(nanix);
            SRPSaveData datax = SRPSaveData.get(world, 63);
            if (optionx >= 11) {
               msg(sender, "command.srpevolution.setphase.error.phase_too_high");
               return;
            }

            if (optionx <= -3) {
               msg(sender, "command.srpevolution.setphase.error.phase_too_low");
               return;
            }

            if (optionx == -1) {
               datax.setEvolutionPhase(world.field_73011_w.getDimension(), (byte)optionx, true, world);
               datax.setTotalKills(world.field_73011_w.getDimension(), 100 * optionx, false, world, true, 48);
            } else if (optionx == -2) {
               datax.setTotalKills(world.field_73011_w.getDimension(), 100 * optionx, false, world, true, 49);
               datax.setEvolutionPhase(world.field_73011_w.getDimension(), (byte)optionx, true, world);
            } else {
               datax.setEvolutionPhase(world.field_73011_w.getDimension(), (byte)optionx, true, world);
            }

            if (argString.length == 2) {
               msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
               return;
            }

            try {
               nanix = argString[2];
               optionx = Integer.parseInt(nanix);
            } catch (NumberFormatException var25) {
               msg(sender, "command.srpevolution.setphase.error.invalid_arg_evo");
               return;
            }

            nanix = argString[2];
            optionx = Integer.parseInt(nanix);
            if (optionx >= 6) {
               msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
               msg(sender, "command.srpevolution.setphase.error.generation_too_high");
               return;
            }

            if (optionx <= -1) {
               msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
               msg(sender, "command.srpevolution.setphase.error.generation_too_low");
               return;
            }

            datax.setGeneration((byte)optionx, world.field_73011_w.getDimension());
            datax.setGenerationTime(0, world.field_73011_w.getDimension());
            msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
            msg(sender, "command.srpevolution.setphase.changed_generation", argString[2]);
            return;
         }

         msg(sender, "command.srpevolution.error.invalid_command");
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
      atm.add("evolutionlock_reset");
      atm.add("evolutionlock_unlockall");
      atm.add("evolutionlock_getlist");
      atm.add("toggle_evolutiongaining");
      atm.add("toggle_evolutionloss");
      atm.add("addpoints");
      atm.add("setcooldown");
      atm.add("getphase");
      atm.add("setphase");
      atm.add("addcooldown");
      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }

   public static int getNeededPoints(byte in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseKillsOne;
         case 2:
            return SRPConfigSystems.phaseKillsTwo;
         case 3:
            return SRPConfigSystems.phaseKillsThree;
         case 4:
            return SRPConfigSystems.phaseKillsFour;
         case 5:
            return SRPConfigSystems.phaseKillsFive;
         case 6:
            return SRPConfigSystems.phaseKillsSix;
         case 7:
            return SRPConfigSystems.phaseKillsSeven;
         case 8:
            return SRPConfigSystems.phaseKillsEight;
         case 9:
            return SRPConfigSystems.phaseKillsNine;
         case 10:
            return SRPConfigSystems.phaseKillsTen;
         default:
            return 0;
      }
   }

   public static int getVectorPointCap(int in) {
      switch (in) {
         case -1:
            return SRPConfigSystems.phaseVectorPointCapZero / 2;
         case 0:
            return SRPConfigSystems.phaseVectorPointCapZero;
         case 1:
            return SRPConfigSystems.phaseVectorPointCapOne;
         case 2:
            return SRPConfigSystems.phaseVectorPointCapTwo;
         case 3:
            return SRPConfigSystems.phaseVectorPointCapThree;
         case 4:
            return SRPConfigSystems.phaseVectorPointCapFour;
         case 5:
            return SRPConfigSystems.phaseVectorPointCapFive;
         case 6:
            return SRPConfigSystems.phaseVectorPointCapSix;
         case 7:
            return SRPConfigSystems.phaseVectorPointCapSeven;
         case 8:
            return SRPConfigSystems.phaseVectorPointCapEight;
         case 9:
            return SRPConfigSystems.phaseVectorPointCapNine;
         default:
            return Integer.MAX_VALUE;
      }
   }

   public static int getVectorHealthBonus(int in) {
      switch (in) {
         case -1:
            return SRPConfigSystems.phaseVectorMultBonusMinusOne;
         case 0:
            return SRPConfigSystems.phaseVectorMultBonusZero;
         case 1:
            return SRPConfigSystems.phaseVectorMultBonusOne;
         case 2:
            return SRPConfigSystems.phaseVectorMultBonusTwo;
         case 3:
            return SRPConfigSystems.phaseVectorMultBonusThree;
         case 4:
            return SRPConfigSystems.phaseVectorMultBonusFour;
         case 5:
            return SRPConfigSystems.phaseVectorMultBonusFive;
         case 6:
            return SRPConfigSystems.phaseVectorMultBonusSix;
         case 7:
            return SRPConfigSystems.phaseVectorMultBonusSeven;
         case 8:
            return SRPConfigSystems.phaseVectorMultBonusEight;
         case 9:
            return SRPConfigSystems.phaseVectorMultBonusNine;
         default:
            return SRPConfigSystems.phaseVectorMultBonusTen;
      }
   }
}
