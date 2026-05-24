/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 */
package com.subspaceparasite.network;

import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SPCommandDislodgment
implements ICommand {
    private final List aliases = new ArrayList();

    public SPCommandDislodgment() {
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
                sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument"));
                return;
            }
            if (argString[0].equals("codes_reset")) {
                SPSaveData.get(world, 54).reduceCodesCooldown(world.field_73011_w.getDimension(), 1000000000, world);
                sender.func_145747_a((ITextComponent)new TextComponentString("Dislodgment codes back to 0"));
                return;
            }
            if (argString[0].equals("random_code")) {
                int x;
                try {
                    x = Integer.parseInt(argString[1]);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument, please enter: duration"));
                    return;
                }
                boolean looop = false;
                for (int gggg = 10; gggg > 0 && !looop; --gggg) {
                    int code = world.field_73012_v.nextInt(30);
                    int val = world.field_73012_v.nextInt(6) + 1;
                    looop = SPSaveData.get(world, 55).setCurrentCode(world.field_73011_w.getDimension(), code, val, x, world, true, 0);
                    if (!looop) continue;
                    sender.func_145747_a((ITextComponent)new TextComponentString("Dislodgment Code: " + code + " Value: " + val + " Duration: " + x));
                    return;
                }
                sender.func_145747_a((ITextComponent)new TextComponentString("Dislo Error"));
                return;
            }
            if (argString[0].equals("set_code")) {
                int val;
                int code;
                int x;
                if (argString.length != 4) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument, please enter: duration - code - value"));
                    return;
                }
                try {
                    x = Integer.parseInt(argString[1]);
                    code = Integer.parseInt(argString[2]);
                    val = Integer.parseInt(argString[3]);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument, please enter: duration - code - value"));
                    return;
                }
                if (SPSaveData.get(world, 56).setCurrentCode(world.field_73011_w.getDimension(), code, val, x, world, true, 0)) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Dislodgment Code: " + code + " Value: " + val + " Duration: " + x));
                } else {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Dislodgment Code " + code + " in use, in cooldown or invalid args, please enter: duration - code - value"));
                }
                return;
            }
        }
        sender.func_145747_a((ITextComponent)new TextComponentString("Invalid command"));
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        for (int i = 0; i < args.length; ++i) {
        }
        ArrayList<String> atm = new ArrayList<String>();
        atm.add("codes_reset");
        atm.add("random_code");
        atm.add("set_code");
        if (args.length == 1) {
            return CommandBase.func_175762_a((String[])args, atm);
        }
        return atm;
    }

    public boolean func_82358_a(String[] args, int index) {
        return false;
    }

    public static byte[] getDisloPhase(int id) {
        switch (id) {
            case 1: {
                return SPConfigSystems.disloPhaseOne;
            }
            case 2: {
                return SPConfigSystems.disloPhaseTwo;
            }
            case 3: {
                return SPConfigSystems.disloPhaseThree;
            }
            case 4: {
                return SPConfigSystems.disloPhaseFour;
            }
            case 5: {
                return SPConfigSystems.disloPhaseFive;
            }
            case 6: {
                return SPConfigSystems.disloPhaseSix;
            }
            case 7: {
                return SPConfigSystems.disloPhaseSeven;
            }
            case 8: {
                return SPConfigSystems.disloPhaseEight;
            }
            case 9: {
                return SPConfigSystems.disloPhaseNine;
            }
            case 10: {
                return SPConfigSystems.disloPhaseTen;
            }
        }
        return null;
    }

    public static int getDisloPointPrice(byte in) {
        switch (in) {
            case 0: {
                return SPConfigSystems.disloCOTHIgnoreAmpPrice;
            }
            case 1: {
                return SPConfigSystems.disloCOTHTiersPrice;
            }
            case 2: {
                return SPConfigSystems.disloSummonByDeathPrice;
            }
            case 3: {
                return SPConfigSystems.disloPotiEffPrice;
            }
            case 4: {
                return SPConfigSystems.dislostatsPrice;
            }
            case 5: {
                return SPConfigSystems.disloDeathRaidPrice;
            }
            case 6: {
                return SPConfigSystems.disloItemDuraPrice;
            }
            case 7: {
                return SPConfigSystems.disloHealingDeathPrice;
            }
            case 8: {
                return SPConfigSystems.disloDamageDeathPrice;
            }
            case 9: {
                return SPConfigSystems.disloFoodDeathPrice;
            }
            case 10: {
                return SPConfigSystems.disloDeathHighVerionsPrice;
            }
            case 11: {
                return SPConfigSystems.disloParasiteNoPotionPrice;
            }
            case 12: {
                return SPConfigSystems.disloHealthDrainingPrice;
            }
            case 13: {
                return SPConfigSystems.disloFoodDrainingPrice;
            }
            case 14: {
                return SPConfigSystems.disloNextPhaseLPrice;
            }
            case 15: {
                return SPConfigSystems.disloGrowlNoisePrice;
            }
            case 16: {
                return SPConfigSystems.disloWalkNoisePrice;
            }
            case 17: {
                return SPConfigSystems.disloShieldFoodPrice;
            }
            case 18: {
                return SPConfigSystems.disloLootXpCancPrice;
            }
            case 19: {
                return SPConfigSystems.disloKillcountIncPrice;
            }
            case 20: {
                return SPConfigSystems.disloGiveBodiesPrice;
            }
            case 21: {
                return SPConfigSystems.disloBurningDeathPrice;
            }
            case 22: {
                return SPConfigSystems.disloSameVersionDyeingPrice;
            }
            case 23: {
                return SPConfigSystems.disloColonyNoLimitPrice;
            }
            case 24: {
                return SPConfigSystems.disloNexusGrowthPrice;
            }
            case 25: {
                return SPConfigSystems.disloParasiteBlockPrice;
            }
        }
        return 0;
    }

    public static int getDisloDuration(byte in) {
        switch (in) {
            case 0: {
                return SPConfigSystems.disloCOTHIgnoreAmpDuration;
            }
            case 1: {
                return SPConfigSystems.disloCOTHTiersDuration;
            }
            case 2: {
                return SPConfigSystems.disloSummonByDeathDuration;
            }
            case 3: {
                return SPConfigSystems.disloPotiEffDuration;
            }
            case 4: {
                return SPConfigSystems.dislostatsDuration;
            }
            case 5: {
                return SPConfigSystems.disloDeathRaidDuration;
            }
            case 6: {
                return SPConfigSystems.disloItemDuraDuration;
            }
            case 7: {
                return SPConfigSystems.disloHealingDeathDuration;
            }
            case 8: {
                return SPConfigSystems.disloDamageDeathDuration;
            }
            case 9: {
                return SPConfigSystems.disloFoodDeathDuration;
            }
            case 10: {
                return SPConfigSystems.disloDeathHighVerionsDuration;
            }
            case 11: {
                return SPConfigSystems.disloParasiteNoPotionDuration;
            }
            case 12: {
                return SPConfigSystems.disloHealthDrainingDuration;
            }
            case 13: {
                return SPConfigSystems.disloFoodDrainingDuration;
            }
            case 14: {
                return SPConfigSystems.disloNextPhaseLDuration;
            }
            case 15: {
                return SPConfigSystems.disloGrowlNoiseDuration;
            }
            case 16: {
                return SPConfigSystems.disloWalkNoiseDuration;
            }
            case 17: {
                return SPConfigSystems.disloShieldFoodDuration;
            }
            case 18: {
                return SPConfigSystems.disloLootXpCancDuration;
            }
            case 19: {
                return SPConfigSystems.disloKillcountIncDuration;
            }
            case 20: {
                return SPConfigSystems.disloGiveBodiesDuration;
            }
            case 21: {
                return SPConfigSystems.disloBurningDeathDuration;
            }
            case 22: {
                return SPConfigSystems.disloSameVersionDyeingDuration;
            }
            case 23: {
                return SPConfigSystems.disloColonyNoLimitDuration;
            }
            case 24: {
                return SPConfigSystems.disloNexusGrowthDuration;
            }
            case 25: {
                return SPConfigSystems.disloParasiteBlockDuration;
            }
        }
        return 0;
    }

    public static int getDisloValue(byte in) {
        switch (in) {
            case 0: {
                return 1;
            }
            case 1: {
                return SPConfigSystems.disloCOTHTiersValue;
            }
            case 2: {
                return SPConfigSystems.disloSummonByDeathValue;
            }
            case 3: {
                return SPConfigSystems.disloPotiEffValue;
            }
            case 4: {
                return SPConfigSystems.dislostatsValue;
            }
            case 5: {
                return SPConfigSystems.disloDeathRaidValue;
            }
            case 6: {
                return SPConfigSystems.disloItemDuraValue;
            }
            case 7: {
                return SPConfigSystems.disloHealingDeathValue;
            }
            case 8: {
                return SPConfigSystems.disloDamageDeathValue;
            }
            case 9: {
                return SPConfigSystems.disloFoodDeathValue;
            }
            case 10: {
                return SPConfigSystems.disloDeathHighVerionsValue;
            }
            case 11: {
                return 1;
            }
            case 12: {
                return SPConfigSystems.disloHealthDrainingValue;
            }
            case 13: {
                return SPConfigSystems.disloFoodDrainingValue;
            }
            case 14: {
                return SPConfigSystems.disloNextPhaseLValue;
            }
            case 15: {
                return 1;
            }
            case 16: {
                return 1;
            }
            case 17: {
                return 1;
            }
            case 18: {
                return 1;
            }
            case 19: {
                return SPConfigSystems.disloKillcountIncValue;
            }
            case 20: {
                return 1;
            }
            case 21: {
                return 1;
            }
            case 22: {
                return SPConfigSystems.disloSameVersionDyeingValue;
            }
            case 23: {
                return SPConfigSystems.disloColonyNoLimitValue;
            }
            case 24: {
                return SPConfigSystems.disloNexusGrowthValue;
            }
            case 25: {
                return SPConfigSystems.disloParasiteBlockValue;
            }
        }
        return 0;
    }

    public static int getDisloCooldown(int code) {
        switch (code) {
            case 0: {
                return SPConfigSystems.disloCOTHIgnoreAmpCooldown;
            }
            case 1: {
                return SPConfigSystems.disloCOTHTiersCooldown;
            }
            case 2: {
                return SPConfigSystems.disloSummonByDeathCooldown;
            }
            case 3: {
                return SPConfigSystems.disloPotiEffCooldown;
            }
            case 4: {
                return SPConfigSystems.dislostatsCooldown;
            }
            case 5: {
                return SPConfigSystems.disloDeathRaidCooldown;
            }
            case 6: {
                return SPConfigSystems.disloItemDuraCooldown;
            }
            case 7: {
                return SPConfigSystems.disloHealingDeathCooldown;
            }
            case 8: {
                return SPConfigSystems.disloDamageDeathCooldown;
            }
            case 9: {
                return SPConfigSystems.disloFoodDeathCooldown;
            }
            case 10: {
                return SPConfigSystems.disloDeathHighVerionsCooldown;
            }
            case 11: {
                return SPConfigSystems.disloParasiteNoPotionCooldown;
            }
            case 12: {
                return SPConfigSystems.disloHealthDrainingCooldown;
            }
            case 13: {
                return SPConfigSystems.disloFoodDrainingCooldown;
            }
            case 14: {
                return SPConfigSystems.disloNextPhaseLCooldown;
            }
            case 15: {
                return SPConfigSystems.disloGrowlNoiseCooldown;
            }
            case 16: {
                return SPConfigSystems.disloWalkNoiseCooldown;
            }
            case 17: {
                return SPConfigSystems.disloShieldFoodCooldown;
            }
            case 18: {
                return SPConfigSystems.disloLootXpCancCooldown;
            }
            case 19: {
                return SPConfigSystems.disloKillcountIncCooldown;
            }
            case 20: {
                return SPConfigSystems.disloGiveBodiesCooldown;
            }
            case 21: {
                return SPConfigSystems.disloBurningDeathCooldown;
            }
            case 22: {
                return SPConfigSystems.disloSameVersionDyeingCooldown;
            }
            case 23: {
                return SPConfigSystems.disloColonyNoLimitCooldown;
            }
            case 24: {
                return SPConfigSystems.disloNexusGrowthCooldown;
            }
            case 25: {
                return SPConfigSystems.disloParasiteBlockCooldown;
            }
        }
        return 0;
    }

    public static double getDisloPhaseCooldown(byte in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseDisloCooldownOne;
            }
            case 2: {
                return SPConfigSystems.phaseDisloCooldownTwo;
            }
            case 3: {
                return SPConfigSystems.phaseDisloCooldownThree;
            }
            case 4: {
                return SPConfigSystems.phaseDisloCooldownFour;
            }
            case 5: {
                return SPConfigSystems.phaseDisloCooldownFive;
            }
            case 6: {
                return SPConfigSystems.phaseDisloCooldownSix;
            }
            case 7: {
                return SPConfigSystems.phaseDisloCooldownSeven;
            }
            case 8: {
                return SPConfigSystems.phaseDisloCooldownEight;
            }
            case 9: {
                return SPConfigSystems.phaseDisloCooldownNine;
            }
            case 10: {
                return SPConfigSystems.phaseDisloCooldownTen;
            }
        }
        return 1.0;
    }

    public static double getDisloPhaseDuration(byte in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseDisloDurationOne;
            }
            case 2: {
                return SPConfigSystems.phaseDisloDurationTwo;
            }
            case 3: {
                return SPConfigSystems.phaseDisloDurationThree;
            }
            case 4: {
                return SPConfigSystems.phaseDisloDurationFour;
            }
            case 5: {
                return SPConfigSystems.phaseDisloDurationFive;
            }
            case 6: {
                return SPConfigSystems.phaseDisloDurationSix;
            }
            case 7: {
                return SPConfigSystems.phaseDisloDurationSeven;
            }
            case 8: {
                return SPConfigSystems.phaseDisloDurationEight;
            }
            case 9: {
                return SPConfigSystems.phaseDisloDurationNine;
            }
            case 10: {
                return SPConfigSystems.phaseDisloDurationTen;
            }
        }
        return 0.0;
    }

    public static double getDisloPhaseCost(byte in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseDisloPointCostOne;
            }
            case 2: {
                return SPConfigSystems.phaseDisloPointCostTwo;
            }
            case 3: {
                return SPConfigSystems.phaseDisloPointCostThree;
            }
            case 4: {
                return SPConfigSystems.phaseDisloPointCostFour;
            }
            case 5: {
                return SPConfigSystems.phaseDisloPointCostFive;
            }
            case 6: {
                return SPConfigSystems.phaseDisloPointCostSix;
            }
            case 7: {
                return SPConfigSystems.phaseDisloPointCostSeven;
            }
            case 8: {
                return SPConfigSystems.phaseDisloPointCostEight;
            }
            case 9: {
                return SPConfigSystems.phaseDisloPointCostNine;
            }
            case 10: {
                return SPConfigSystems.phaseDisloPointCostTen;
            }
        }
        return 0.0;
    }

    public static double getDisloPhaseValue(byte in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseDisloMoreValueOne;
            }
            case 2: {
                return SPConfigSystems.phaseDisloMoreValueTwo;
            }
            case 3: {
                return SPConfigSystems.phaseDisloMoreValueThree;
            }
            case 4: {
                return SPConfigSystems.phaseDisloMoreValueFour;
            }
            case 5: {
                return SPConfigSystems.phaseDisloMoreValueFive;
            }
            case 6: {
                return SPConfigSystems.phaseDisloMoreValueSix;
            }
            case 7: {
                return SPConfigSystems.phaseDisloMoreValueSeven;
            }
            case 8: {
                return SPConfigSystems.phaseDisloMoreValueEight;
            }
            case 9: {
                return SPConfigSystems.phaseDisloMoreValueNine;
            }
            case 10: {
                return SPConfigSystems.phaseDisloMoreValueTen;
            }
        }
        return 0.0;
    }
}

