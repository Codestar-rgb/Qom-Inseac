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

public class SRPCommandDislodgment implements ICommand {
   private final List aliases = new ArrayList();

   public SRPCommandDislodgment() {
      this.aliases.add("srpdislodgment");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "srpdislodgment";
   }

   public String func_71518_a(ICommandSender sender) {
      return "srpdislodgment <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (argString.length == 0) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         if (argString[0].equals("codes_reset")) {
            SRPSaveData.get(world, 54).reduceCodesCooldown(world.field_73011_w.getDimension(), 1000000000, world);
            sender.func_145747_a(new TextComponentString("Dislodgment codes back to 0"));
            return;
         }

         if (argString[0].equals("random_code")) {
            int x;
            try {
               x = Integer.parseInt(argString[1]);
            } catch (NumberFormatException var10) {
               sender.func_145747_a(new TextComponentString("Invalid argument, please enter: duration"));
               return;
            }

            boolean looop = false;
            int gggg = 10;

            while (gggg > 0 && !looop) {
               int code = world.field_73012_v.nextInt(30);
               int val = world.field_73012_v.nextInt(6) + 1;
               gggg--;
               looop = SRPSaveData.get(world, 55).setCurrentCode(world.field_73011_w.getDimension(), code, val, x, world, true, 0);
               if (looop) {
                  sender.func_145747_a(new TextComponentString("Dislodgment Code: " + code + " Value: " + val + " Duration: " + x));
                  return;
               }
            }

            sender.func_145747_a(new TextComponentString("Dislo Error"));
            return;
         }

         if (argString[0].equals("set_code")) {
            if (argString.length != 4) {
               sender.func_145747_a(new TextComponentString("Invalid argument, please enter: duration - code - value"));
               return;
            }

            int x;
            int code;
            int val;
            try {
               x = Integer.parseInt(argString[1]);
               code = Integer.parseInt(argString[2]);
               val = Integer.parseInt(argString[3]);
            } catch (NumberFormatException var11) {
               sender.func_145747_a(new TextComponentString("Invalid argument, please enter: duration - code - value"));
               return;
            }

            if (SRPSaveData.get(world, 56).setCurrentCode(world.field_73011_w.getDimension(), code, val, x, world, true, 0)) {
               sender.func_145747_a(new TextComponentString("Dislodgment Code: " + code + " Value: " + val + " Duration: " + x));
            } else {
               sender.func_145747_a(
                  new TextComponentString("Dislodgment Code " + code + " in use, in cooldown or invalid args, please enter: duration - code - value")
               );
            }

            return;
         }
      }

      sender.func_145747_a(new TextComponentString("Invalid command"));
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
      atm.add("codes_reset");
      atm.add("random_code");
      atm.add("set_code");
      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }

   public static byte[] getDisloPhase(int id) {
      switch (id) {
         case 1:
            return SRPConfigSystems.disloPhaseOne;
         case 2:
            return SRPConfigSystems.disloPhaseTwo;
         case 3:
            return SRPConfigSystems.disloPhaseThree;
         case 4:
            return SRPConfigSystems.disloPhaseFour;
         case 5:
            return SRPConfigSystems.disloPhaseFive;
         case 6:
            return SRPConfigSystems.disloPhaseSix;
         case 7:
            return SRPConfigSystems.disloPhaseSeven;
         case 8:
            return SRPConfigSystems.disloPhaseEight;
         case 9:
            return SRPConfigSystems.disloPhaseNine;
         case 10:
            return SRPConfigSystems.disloPhaseTen;
         default:
            return null;
      }
   }

   public static int getDisloPointPrice(byte in) {
      switch (in) {
         case 0:
            return SRPConfigSystems.disloCOTHIgnoreAmpPrice;
         case 1:
            return SRPConfigSystems.disloCOTHTiersPrice;
         case 2:
            return SRPConfigSystems.disloSummonByDeathPrice;
         case 3:
            return SRPConfigSystems.disloPotiEffPrice;
         case 4:
            return SRPConfigSystems.dislostatsPrice;
         case 5:
            return SRPConfigSystems.disloDeathRaidPrice;
         case 6:
            return SRPConfigSystems.disloItemDuraPrice;
         case 7:
            return SRPConfigSystems.disloHealingDeathPrice;
         case 8:
            return SRPConfigSystems.disloDamageDeathPrice;
         case 9:
            return SRPConfigSystems.disloFoodDeathPrice;
         case 10:
            return SRPConfigSystems.disloDeathHighVerionsPrice;
         case 11:
            return SRPConfigSystems.disloParasiteNoPotionPrice;
         case 12:
            return SRPConfigSystems.disloHealthDrainingPrice;
         case 13:
            return SRPConfigSystems.disloFoodDrainingPrice;
         case 14:
            return SRPConfigSystems.disloNextPhaseLPrice;
         case 15:
            return SRPConfigSystems.disloGrowlNoisePrice;
         case 16:
            return SRPConfigSystems.disloWalkNoisePrice;
         case 17:
            return SRPConfigSystems.disloShieldFoodPrice;
         case 18:
            return SRPConfigSystems.disloLootXpCancPrice;
         case 19:
            return SRPConfigSystems.disloKillcountIncPrice;
         case 20:
            return SRPConfigSystems.disloGiveBodiesPrice;
         case 21:
            return SRPConfigSystems.disloBurningDeathPrice;
         case 22:
            return SRPConfigSystems.disloSameVersionDyeingPrice;
         case 23:
            return SRPConfigSystems.disloColonyNoLimitPrice;
         case 24:
            return SRPConfigSystems.disloNexusGrowthPrice;
         case 25:
            return SRPConfigSystems.disloParasiteBlockPrice;
         default:
            return 0;
      }
   }

   public static int getDisloDuration(byte in) {
      switch (in) {
         case 0:
            return SRPConfigSystems.disloCOTHIgnoreAmpDuration;
         case 1:
            return SRPConfigSystems.disloCOTHTiersDuration;
         case 2:
            return SRPConfigSystems.disloSummonByDeathDuration;
         case 3:
            return SRPConfigSystems.disloPotiEffDuration;
         case 4:
            return SRPConfigSystems.dislostatsDuration;
         case 5:
            return SRPConfigSystems.disloDeathRaidDuration;
         case 6:
            return SRPConfigSystems.disloItemDuraDuration;
         case 7:
            return SRPConfigSystems.disloHealingDeathDuration;
         case 8:
            return SRPConfigSystems.disloDamageDeathDuration;
         case 9:
            return SRPConfigSystems.disloFoodDeathDuration;
         case 10:
            return SRPConfigSystems.disloDeathHighVerionsDuration;
         case 11:
            return SRPConfigSystems.disloParasiteNoPotionDuration;
         case 12:
            return SRPConfigSystems.disloHealthDrainingDuration;
         case 13:
            return SRPConfigSystems.disloFoodDrainingDuration;
         case 14:
            return SRPConfigSystems.disloNextPhaseLDuration;
         case 15:
            return SRPConfigSystems.disloGrowlNoiseDuration;
         case 16:
            return SRPConfigSystems.disloWalkNoiseDuration;
         case 17:
            return SRPConfigSystems.disloShieldFoodDuration;
         case 18:
            return SRPConfigSystems.disloLootXpCancDuration;
         case 19:
            return SRPConfigSystems.disloKillcountIncDuration;
         case 20:
            return SRPConfigSystems.disloGiveBodiesDuration;
         case 21:
            return SRPConfigSystems.disloBurningDeathDuration;
         case 22:
            return SRPConfigSystems.disloSameVersionDyeingDuration;
         case 23:
            return SRPConfigSystems.disloColonyNoLimitDuration;
         case 24:
            return SRPConfigSystems.disloNexusGrowthDuration;
         case 25:
            return SRPConfigSystems.disloParasiteBlockDuration;
         default:
            return 0;
      }
   }

   public static int getDisloValue(byte in) {
      switch (in) {
         case 0:
            return 1;
         case 1:
            return SRPConfigSystems.disloCOTHTiersValue;
         case 2:
            return SRPConfigSystems.disloSummonByDeathValue;
         case 3:
            return SRPConfigSystems.disloPotiEffValue;
         case 4:
            return SRPConfigSystems.dislostatsValue;
         case 5:
            return SRPConfigSystems.disloDeathRaidValue;
         case 6:
            return SRPConfigSystems.disloItemDuraValue;
         case 7:
            return SRPConfigSystems.disloHealingDeathValue;
         case 8:
            return SRPConfigSystems.disloDamageDeathValue;
         case 9:
            return SRPConfigSystems.disloFoodDeathValue;
         case 10:
            return SRPConfigSystems.disloDeathHighVerionsValue;
         case 11:
            return 1;
         case 12:
            return SRPConfigSystems.disloHealthDrainingValue;
         case 13:
            return SRPConfigSystems.disloFoodDrainingValue;
         case 14:
            return SRPConfigSystems.disloNextPhaseLValue;
         case 15:
            return 1;
         case 16:
            return 1;
         case 17:
            return 1;
         case 18:
            return 1;
         case 19:
            return SRPConfigSystems.disloKillcountIncValue;
         case 20:
            return 1;
         case 21:
            return 1;
         case 22:
            return SRPConfigSystems.disloSameVersionDyeingValue;
         case 23:
            return SRPConfigSystems.disloColonyNoLimitValue;
         case 24:
            return SRPConfigSystems.disloNexusGrowthValue;
         case 25:
            return SRPConfigSystems.disloParasiteBlockValue;
         default:
            return 0;
      }
   }

   public static int getDisloCooldown(int code) {
      switch (code) {
         case 0:
            return SRPConfigSystems.disloCOTHIgnoreAmpCooldown;
         case 1:
            return SRPConfigSystems.disloCOTHTiersCooldown;
         case 2:
            return SRPConfigSystems.disloSummonByDeathCooldown;
         case 3:
            return SRPConfigSystems.disloPotiEffCooldown;
         case 4:
            return SRPConfigSystems.dislostatsCooldown;
         case 5:
            return SRPConfigSystems.disloDeathRaidCooldown;
         case 6:
            return SRPConfigSystems.disloItemDuraCooldown;
         case 7:
            return SRPConfigSystems.disloHealingDeathCooldown;
         case 8:
            return SRPConfigSystems.disloDamageDeathCooldown;
         case 9:
            return SRPConfigSystems.disloFoodDeathCooldown;
         case 10:
            return SRPConfigSystems.disloDeathHighVerionsCooldown;
         case 11:
            return SRPConfigSystems.disloParasiteNoPotionCooldown;
         case 12:
            return SRPConfigSystems.disloHealthDrainingCooldown;
         case 13:
            return SRPConfigSystems.disloFoodDrainingCooldown;
         case 14:
            return SRPConfigSystems.disloNextPhaseLCooldown;
         case 15:
            return SRPConfigSystems.disloGrowlNoiseCooldown;
         case 16:
            return SRPConfigSystems.disloWalkNoiseCooldown;
         case 17:
            return SRPConfigSystems.disloShieldFoodCooldown;
         case 18:
            return SRPConfigSystems.disloLootXpCancCooldown;
         case 19:
            return SRPConfigSystems.disloKillcountIncCooldown;
         case 20:
            return SRPConfigSystems.disloGiveBodiesCooldown;
         case 21:
            return SRPConfigSystems.disloBurningDeathCooldown;
         case 22:
            return SRPConfigSystems.disloSameVersionDyeingCooldown;
         case 23:
            return SRPConfigSystems.disloColonyNoLimitCooldown;
         case 24:
            return SRPConfigSystems.disloNexusGrowthCooldown;
         case 25:
            return SRPConfigSystems.disloParasiteBlockCooldown;
         default:
            return 0;
      }
   }

   public static double getDisloPhaseCooldown(byte in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseDisloCooldownOne;
         case 2:
            return SRPConfigSystems.phaseDisloCooldownTwo;
         case 3:
            return SRPConfigSystems.phaseDisloCooldownThree;
         case 4:
            return SRPConfigSystems.phaseDisloCooldownFour;
         case 5:
            return SRPConfigSystems.phaseDisloCooldownFive;
         case 6:
            return SRPConfigSystems.phaseDisloCooldownSix;
         case 7:
            return SRPConfigSystems.phaseDisloCooldownSeven;
         case 8:
            return SRPConfigSystems.phaseDisloCooldownEight;
         case 9:
            return SRPConfigSystems.phaseDisloCooldownNine;
         case 10:
            return SRPConfigSystems.phaseDisloCooldownTen;
         default:
            return 1.0;
      }
   }

   public static double getDisloPhaseDuration(byte in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseDisloDurationOne;
         case 2:
            return SRPConfigSystems.phaseDisloDurationTwo;
         case 3:
            return SRPConfigSystems.phaseDisloDurationThree;
         case 4:
            return SRPConfigSystems.phaseDisloDurationFour;
         case 5:
            return SRPConfigSystems.phaseDisloDurationFive;
         case 6:
            return SRPConfigSystems.phaseDisloDurationSix;
         case 7:
            return SRPConfigSystems.phaseDisloDurationSeven;
         case 8:
            return SRPConfigSystems.phaseDisloDurationEight;
         case 9:
            return SRPConfigSystems.phaseDisloDurationNine;
         case 10:
            return SRPConfigSystems.phaseDisloDurationTen;
         default:
            return 0.0;
      }
   }

   public static double getDisloPhaseCost(byte in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseDisloPointCostOne;
         case 2:
            return SRPConfigSystems.phaseDisloPointCostTwo;
         case 3:
            return SRPConfigSystems.phaseDisloPointCostThree;
         case 4:
            return SRPConfigSystems.phaseDisloPointCostFour;
         case 5:
            return SRPConfigSystems.phaseDisloPointCostFive;
         case 6:
            return SRPConfigSystems.phaseDisloPointCostSix;
         case 7:
            return SRPConfigSystems.phaseDisloPointCostSeven;
         case 8:
            return SRPConfigSystems.phaseDisloPointCostEight;
         case 9:
            return SRPConfigSystems.phaseDisloPointCostNine;
         case 10:
            return SRPConfigSystems.phaseDisloPointCostTen;
         default:
            return 0.0;
      }
   }

   public static double getDisloPhaseValue(byte in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseDisloMoreValueOne;
         case 2:
            return SRPConfigSystems.phaseDisloMoreValueTwo;
         case 3:
            return SRPConfigSystems.phaseDisloMoreValueThree;
         case 4:
            return SRPConfigSystems.phaseDisloMoreValueFour;
         case 5:
            return SRPConfigSystems.phaseDisloMoreValueFive;
         case 6:
            return SRPConfigSystems.phaseDisloMoreValueSix;
         case 7:
            return SRPConfigSystems.phaseDisloMoreValueSeven;
         case 8:
            return SRPConfigSystems.phaseDisloMoreValueEight;
         case 9:
            return SRPConfigSystems.phaseDisloMoreValueNine;
         case 10:
            return SRPConfigSystems.phaseDisloMoreValueTen;
         default:
            return 0.0;
      }
   }
}
