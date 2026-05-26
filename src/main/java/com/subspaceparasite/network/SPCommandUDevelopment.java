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
 *  net.minecraft.util.text.TextComponentTranslation
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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SPCommandUDevelopment
implements ICommand {
    private final List aliases = new ArrayList();

    private static void msg(ICommandSender sender, String key, Object ... args) {
        sender.func_145747_a((ITextComponent)new TextComponentTranslation(key, args));
    }

    public SPCommandUDevelopment() {
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
            if (!SPConfigSystems.useEvolution) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Evolution levels are not active"));
                return;
            }
            if (argString.length == 0) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument"));
                return;
            }
            if (argString[0].equals("getlevel")) {
                SPSaveData data = SPSaveData.get(world, 59);
                int id = world.field_73011_w.getDimension();
                sender.func_145747_a((ITextComponent)new TextComponentString(" ======> \n -> Current Ubiquitous Development Level: " + data.getDeveLevel()));
                return;
            }
            if (argString[0].equals("setlevel")) {
                String nani;
                try {
                    nani = argString[1];
                    int id = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid arg"));
                    return;
                }
                nani = argString[1];
                int option = Integer.parseInt(nani);
                SPSaveData data = SPSaveData.get(world, 63);
                if (option >= 5) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument: level too high"));
                    return;
                }
                if (option <= -1) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument: level too low"));
                    return;
                }
                SPSaveData.falseLevel = option;
                sender.func_145747_a((ITextComponent)new TextComponentString("Changed Ubiquitous Development Level to " + argString[1]));
                sender.func_145747_a((ITextComponent)new TextComponentString("Set this value to 0 to remove fake level"));
                return;
            }
            if (argString[0].equals("viewalldims")) {
                SPSaveData data = SPSaveData.get(world, -1);
                ArrayList<Integer> phases = data.getColonies("p");
                ArrayList<Integer> dims = data.getColonies("d");
                ArrayList<Integer> kills = data.getColonies("k");
                ArrayList<Integer> gen = data.getColonies("g");
                String out = "Current Parasite progress in dimensions \n (id, phase, points, generation): \n";
                for (int i = 0; i < dims.size(); ++i) {
                    out = out + "-> [" + dims.get(i) + ", " + phases.get(i) + ", " + kills.get(i) + ", " + gen.get(i) + "] \n";
                }
                sender.func_145747_a((ITextComponent)new TextComponentString(out));
                return;
            }
            if (argString[0].equals("setdimevolution")) {
                String nani2;
                String nani;
                try {
                    nani = argString[1];
                    nani2 = argString[2];
                    int option = Integer.parseInt(nani);
                    int kills = Integer.parseInt(nani2);
                }
                catch (NumberFormatException nfe) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.error.invalid_arg", new Object[0]);
                    return;
                }
                nani = argString[1];
                nani2 = argString[2];
                int id = Integer.parseInt(nani);
                int phase = Integer.parseInt(nani2);
                SPSaveData data = SPSaveData.get(world, 633);
                if (phase >= 11) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.error.phase_too_high", new Object[0]);
                    return;
                }
                if (phase <= -3) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.error.phase_too_low", new Object[0]);
                    return;
                }
                if (phase == -1) {
                    data.setEvolutionPhase(id, (byte)phase, true, world);
                    data.setTotalKills(id, 100 * phase, false, world, true, 50);
                } else if (phase == -2) {
                    data.setTotalKills(id, 100 * phase, false, world, true, 51);
                    data.setEvolutionPhase(id, (byte)phase, true, world);
                } else {
                    data.setEvolutionPhase(id, (byte)phase, true, world);
                }
                if (argString.length == 3) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.getphase.dimension", argString[1]);
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.changed_phase", argString[2]);
                    return;
                }
                try {
                    nani = argString[3];
                    int out = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.error.invalid_arg_evo", new Object[0]);
                    return;
                }
                nani = argString[3];
                int option = Integer.parseInt(nani);
                if (option >= 6) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.changed_phase", argString[3]);
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.error.generation_too_high", new Object[0]);
                    return;
                }
                if (option <= -1) {
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.changed_phase", argString[3]);
                    SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.error.generation_too_low", new Object[0]);
                    return;
                }
                data.setGeneration((byte)option, id);
                data.setGenerationTime(0, id);
                SPCommandUDevelopment.msg(sender, "command.srpevolution.getphase.dimension", argString[1]);
                SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.changed_phase", argString[2]);
                SPCommandUDevelopment.msg(sender, "command.srpevolution.setphase.changed_generation", argString[3]);
                return;
            }
            sender.func_145747_a((ITextComponent)new TextComponentString("Invalid command"));
        }
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        for (int i = 0; i < args.length; ++i) {
        }
        ArrayList<String> atm = new ArrayList<String>();
        atm.add("getlevel");
        atm.add("setlevel");
        atm.add("viewalldims");
        atm.add("setdimevolution");
        if (args.length == 1) {
            return CommandBase.func_175762_a((String[])args, atm);
        }
        return atm;
    }

    public boolean func_82358_a(String[] args, int index) {
        return false;
    }
}

