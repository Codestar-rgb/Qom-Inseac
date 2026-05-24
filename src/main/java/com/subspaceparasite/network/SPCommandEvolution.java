/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.World
 */
package com.subspaceparasite.network;

import com.subspaceparasite.entity.ai.misc.EntityCanHaveBodies;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SPCommandEvolution
implements ICommand {
    private final List aliases = new ArrayList();

    private static void msg(ICommandSender sender, String key, Object ... args) {
        sender.func_145747_a((ITextComponent)new TextComponentTranslation(key, args));
    }

    public SPCommandEvolution() {
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
            if (!SPConfigSystems.useEvolution) {
                SPCommandEvolution.msg(sender, "command.srpevolution.error.evolution_disabled", new Object[0]);
                return;
            }
            if (argString.length == 0) {
                SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_argument", new Object[0]);
                return;
            }
            if (argString[0].equals("set_evolutionloss")) {
                SPSaveData data = SPSaveData.get(world, 57);
                if (argString[1].equals("true")) {
                    data.setLoss(true, world.field_73011_w.getDimension());
                    SPCommandEvolution.msg(sender, "command.srpevolution.evolutionloss.set.true", new Object[0]);
                } else if (argString[1].equals("false")) {
                    data.setLoss(false, world.field_73011_w.getDimension());
                    SPCommandEvolution.msg(sender, "command.srpevolution.evolutionloss.set.false", new Object[0]);
                } else {
                    SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                }
                return;
            }
            if (argString[0].equals("set_evolutiongaining")) {
                SPSaveData data = SPSaveData.get(world, 58);
                if (argString[1].equals("true")) {
                    data.setGaining(true, world.field_73011_w.getDimension());
                    SPCommandEvolution.msg(sender, "command.srpevolution.evolutiongaining.set.true", new Object[0]);
                } else if (argString[1].equals("false")) {
                    data.setGaining(false, world.field_73011_w.getDimension());
                    SPCommandEvolution.msg(sender, "command.srpevolution.evolutiongaining.set.false", new Object[0]);
                } else {
                    SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                }
                return;
            }
            if (argString[0].equals("getphase")) {
                int x;
                List serverList = world.field_72996_f;
                int count = 0;
                int coth = 0;
                for (x = 0; x < serverList.size(); ++x) {
                    if (!(serverList.get(x) instanceof EntityParasiteBase)) continue;
                    if (serverList.get(x) instanceof EntityCanHaveBodies) {
                        EntityCanHaveBodies bodies = (EntityCanHaveBodies)serverList.get(x);
                        if (bodies.getBodyNumber() != 0) continue;
                        ++count;
                        continue;
                    }
                    ++count;
                }
                for (x = 0; x < serverList.size(); ++x) {
                    if (!(serverList.get(x) instanceof EntityLivingBase) || !((EntityLivingBase)serverList.get(x)).func_70644_a(SPPotions.COTH_E)) continue;
                    ++coth;
                }
                SPSaveData data = SPSaveData.get(world, 59);
                int players = world.field_73010_i.size();
                players *= SPConfig.worldMobCapPlusPlayer;
                int id = world.field_73011_w.getDimension();
                ArrayList<Integer> halo = SPWorldData.get(world).getorigins("x");
                String[] here = new String[20];
                here = data.getCurrentCodeU(id).split(";");
                String atm = "";
                for (int k = 0; k < here.length; ++k) {
                    atm = k == here.length - 1 ? atm + here[k] : atm + here[k] + " ";
                }
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.header", new Object[0]);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.dimension", world.field_73011_w.getDimension());
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.phase", data.getEvolutionPhase(id));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.total_points", data.getTotalKills(id));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.points_next", SPCommandEvolution.getNeededPoints((byte)(data.getEvolutionPhase(id) + 1)));
                double progress = (double)data.getTotalKills(id) / (double)SPCommandEvolution.getNeededPoints((byte)(data.getEvolutionPhase(id) + 1)) * 100.0;
                String progStr = String.format(Locale.ROOT, "%.1f", progress);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.progress", progStr);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.cooldown", data.getCooldown(world, id));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.gaining", String.valueOf(data.getCanGain(id)));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.loss", String.valueOf(data.getCanLoss(id)));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.dislodgement", new Object[0]);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.dislodgement.value", atm);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.mobcap", SPConfig.worldMobCap + players);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.generation", data.getGeneration(id));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.gen_ticks", data.getGenerationNeededTime(world, id));
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.udl", data.getDeveLevel());
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.parasite_count", count);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.coth_count", coth);
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.eivs", halo == null ? 0 : halo.size());
                if (sender instanceof EntityPlayer) {
                    boolean flag;
                    EntityPlayer playerIn = (EntityPlayer)sender;
                    BlockPos pos = SPWorldData.get(world).nearestInfectionPosition(false, playerIn.func_180425_c());
                    boolean bl = flag = pos != null;
                    if (flag) {
                        SPCommandEvolution.msg(sender, " -> WITHIN VECTOR: " + flag, new Object[0]);
                    } else if (halo.size() > 0) {
                        pos = SPWorldData.get(world).nearestInfectionPosition(true, playerIn.func_180425_c());
                        int dis = (int)playerIn.func_70011_f((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
                        int area = SPWorldData.get(world).nearestInfectionValueArea(playerIn.func_180425_c(), false);
                        SPCommandEvolution.msg(sender, " -> WITHIN VECTOR: " + flag, new Object[0]);
                        SPCommandEvolution.msg(sender, " -> Distance to nearest EIV: " + (dis - area), new Object[0]);
                    }
                }
                SPCommandEvolution.msg(sender, "command.srpevolution.getphase.footer", new Object[0]);
                return;
            }
            if (argString[0].equals("evolutionlock_reset")) {
                SPSaveData data = SPSaveData.get(world, 60);
                data.resetLock();
                SPCommandEvolution.msg(sender, "command.srpevolution.evolutionlock.reset", new Object[0]);
                return;
            }
            if (argString[0].equals("evolutionlock_unlockall")) {
                SPSaveData data = SPSaveData.get(world, 60);
                data.unlockAllParasite();
                SPCommandEvolution.msg(sender, "command.srpevolution.evolutionlock.unlockall", new Object[0]);
                return;
            }
            if (argString[0].equals("evolutionlock_getlist")) {
                SPSaveData data = SPSaveData.get(world, 65);
                ArrayList<Integer> nodesX = data.getLockedList();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < nodesX.size(); ++i) {
                    sb.append(nodesX.get(i)).append(" ");
                }
                SPCommandEvolution.msg(sender, "command.srpevolution.evolutionlock.getlist", sb.toString());
                return;
            }
            if (argString[0].equals("addpoints")) {
                try {
                    String nani = argString[1];
                    int nodesX = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                    return;
                }
                SPSaveData data = SPSaveData.get(world, 61);
                int id = world.field_73011_w.getDimension();
                if (data.getEvolutionPhase(id) == -2) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.addpoints.error.phase_minus2", new Object[0]);
                    return;
                }
                if (data.getCooldown(world, id) != 0) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.addpoints.error.cooldown", new Object[0]);
                    return;
                }
                String nani = argString[1];
                int option = Integer.parseInt(nani);
                if (!data.getCanGain(id) && option > 0) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.addpoints.error.cannot_gain", new Object[0]);
                    return;
                }
                if (!data.getCanLoss(id) && option < 0) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.addpoints.error.cannot_lose", new Object[0]);
                    return;
                }
                boolean flagg = data.setTotalKills(world.field_73011_w.getDimension(), option, true, world, true, true, 47);
                if (flagg) {
                    if (Math.abs(option) == 1) {
                        SPCommandEvolution.msg(sender, "command.srpevolution.addpoints.success.one", option);
                    } else {
                        SPCommandEvolution.msg(sender, "command.srpevolution.addpoints.success.many", option);
                    }
                    return;
                }
                SPCommandEvolution.msg(sender, "command.srpevolution.error.generic", new Object[0]);
                return;
            }
            if (argString[0].equals("setcooldown")) {
                String nani;
                try {
                    nani = argString[1];
                    int id = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                    return;
                }
                nani = argString[1];
                int option = Integer.parseInt(nani);
                if (option < 0) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.cooldown.error.negative", new Object[0]);
                    return;
                }
                SPSaveData data = SPSaveData.get(world, 62);
                SPCommandEvolution.msg(sender, "command.srpevolution.cooldown.set", option);
                data.setCooldown(option, world, world.field_73011_w.getDimension(), false);
                return;
            }
            if (argString[0].equals("addcooldown")) {
                int option;
                String nani;
                try {
                    nani = argString[1];
                    option = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                    return;
                }
                nani = argString[1];
                option = Integer.parseInt(nani);
                if (option < 0) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.cooldown.error.negative", new Object[0]);
                    return;
                }
                SPSaveData data = SPSaveData.get(world, 64);
                SPCommandEvolution.msg(sender, "command.srpevolution.cooldown.set", option);
                data.setCooldown(option, world, world.field_73011_w.getDimension(), true);
                return;
            }
            if (argString[0].equals("setphase")) {
                int option;
                String nani;
                try {
                    nani = argString[1];
                    option = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                    return;
                }
                nani = argString[1];
                option = Integer.parseInt(nani);
                SPSaveData data = SPSaveData.get(world, 63);
                if (option >= 11) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.error.phase_too_high", new Object[0]);
                    return;
                }
                if (option <= -3) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.error.phase_too_low", new Object[0]);
                    return;
                }
                if (option == -1) {
                    data.setEvolutionPhase(world.field_73011_w.getDimension(), (byte)option, true, world);
                    data.setTotalKills(world.field_73011_w.getDimension(), 100 * option, false, world, true, 48);
                } else if (option == -2) {
                    data.setTotalKills(world.field_73011_w.getDimension(), 100 * option, false, world, true, 49);
                    data.setEvolutionPhase(world.field_73011_w.getDimension(), (byte)option, true, world);
                } else {
                    data.setEvolutionPhase(world.field_73011_w.getDimension(), (byte)option, true, world);
                }
                if (argString.length == 2) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
                    return;
                }
                try {
                    nani = argString[2];
                    option = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.error.invalid_arg_evo", new Object[0]);
                    return;
                }
                nani = argString[2];
                option = Integer.parseInt(nani);
                if (option >= 6) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.error.generation_too_high", new Object[0]);
                    return;
                }
                if (option <= -1) {
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
                    SPCommandEvolution.msg(sender, "command.srpevolution.setphase.error.generation_too_low", new Object[0]);
                    return;
                }
                data.setGeneration((byte)option, world.field_73011_w.getDimension());
                data.setGenerationTime(0, world.field_73011_w.getDimension());
                SPCommandEvolution.msg(sender, "command.srpevolution.setphase.changed_phase", argString[1]);
                SPCommandEvolution.msg(sender, "command.srpevolution.setphase.changed_generation", argString[2]);
                return;
            }
            SPCommandEvolution.msg(sender, "command.srpevolution.error.invalid_command", new Object[0]);
        }
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        for (int i = 0; i < args.length; ++i) {
        }
        ArrayList<String> atm = new ArrayList<String>();
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
        if (args.length == 1) {
            return CommandBase.func_175762_a((String[])args, atm);
        }
        return atm;
    }

    public boolean func_82358_a(String[] args, int index) {
        return false;
    }

    public static int getNeededPoints(byte in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseKillsOne;
            }
            case 2: {
                return SPConfigSystems.phaseKillsTwo;
            }
            case 3: {
                return SPConfigSystems.phaseKillsThree;
            }
            case 4: {
                return SPConfigSystems.phaseKillsFour;
            }
            case 5: {
                return SPConfigSystems.phaseKillsFive;
            }
            case 6: {
                return SPConfigSystems.phaseKillsSix;
            }
            case 7: {
                return SPConfigSystems.phaseKillsSeven;
            }
            case 8: {
                return SPConfigSystems.phaseKillsEight;
            }
            case 9: {
                return SPConfigSystems.phaseKillsNine;
            }
            case 10: {
                return SPConfigSystems.phaseKillsTen;
            }
        }
        return 0;
    }

    public static int getVectorPointCap(int in) {
        switch (in) {
            case -1: {
                return SPConfigSystems.phaseVectorPointCapZero / 2;
            }
            case 0: {
                return SPConfigSystems.phaseVectorPointCapZero;
            }
            case 1: {
                return SPConfigSystems.phaseVectorPointCapOne;
            }
            case 2: {
                return SPConfigSystems.phaseVectorPointCapTwo;
            }
            case 3: {
                return SPConfigSystems.phaseVectorPointCapThree;
            }
            case 4: {
                return SPConfigSystems.phaseVectorPointCapFour;
            }
            case 5: {
                return SPConfigSystems.phaseVectorPointCapFive;
            }
            case 6: {
                return SPConfigSystems.phaseVectorPointCapSix;
            }
            case 7: {
                return SPConfigSystems.phaseVectorPointCapSeven;
            }
            case 8: {
                return SPConfigSystems.phaseVectorPointCapEight;
            }
            case 9: {
                return SPConfigSystems.phaseVectorPointCapNine;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static int getVectorHealthBonus(int in) {
        switch (in) {
            case -1: {
                return SPConfigSystems.phaseVectorMultBonusMinusOne;
            }
            case 0: {
                return SPConfigSystems.phaseVectorMultBonusZero;
            }
            case 1: {
                return SPConfigSystems.phaseVectorMultBonusOne;
            }
            case 2: {
                return SPConfigSystems.phaseVectorMultBonusTwo;
            }
            case 3: {
                return SPConfigSystems.phaseVectorMultBonusThree;
            }
            case 4: {
                return SPConfigSystems.phaseVectorMultBonusFour;
            }
            case 5: {
                return SPConfigSystems.phaseVectorMultBonusFive;
            }
            case 6: {
                return SPConfigSystems.phaseVectorMultBonusSix;
            }
            case 7: {
                return SPConfigSystems.phaseVectorMultBonusSeven;
            }
            case 8: {
                return SPConfigSystems.phaseVectorMultBonusEight;
            }
            case 9: {
                return SPConfigSystems.phaseVectorMultBonusNine;
            }
        }
        return SPConfigSystems.phaseVectorMultBonusTen;
    }
}

