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

public class SPCommandGeneration
implements ICommand {
    private final List aliases = new ArrayList();

    public SPCommandGeneration() {
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
            if (!SPConfigSystems.generationUse) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Generation is not active"));
                return;
            }
            if (argString.length == 0) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument"));
                return;
            }
            if (argString[0].equals("setgeneration")) {
                String nani;
                try {
                    nani = argString[1];
                    int n = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid arg"));
                    return;
                }
                nani = argString[1];
                int option = Integer.parseInt(nani);
                SPSaveData data = SPSaveData.get(world, 66);
                if (option >= 6) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument: generation too high"));
                    return;
                }
                if (option <= -1) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument: generation too low"));
                    return;
                }
                data.setGeneration((byte)option, world.field_73011_w.getDimension());
                data.setGenerationTime(0, world.field_73011_w.getDimension());
                sender.func_145747_a((ITextComponent)new TextComponentString("Changed Generation of Parasites to " + argString[1]));
                return;
            }
            if (argString[0].equals("getgeneration")) {
                SPSaveData data = SPSaveData.get(world, 67);
                sender.func_145747_a((ITextComponent)new TextComponentString("Current Generation of Parasites: " + data.getGeneration(world.field_73011_w.getDimension())));
                return;
            }
            if (argString[0].equals("addticks")) {
                int option;
                String nani;
                try {
                    nani = argString[1];
                    option = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid arg"));
                    return;
                }
                nani = argString[1];
                option = Integer.parseInt(nani);
                SPSaveData data = SPSaveData.get(world, 6777);
                int id = world.field_73011_w.getDimension();
                int value = data.getGenerationTime(id) + option;
                data.setGenerationTime(value, id);
                sender.func_145747_a((ITextComponent)new TextComponentString("Added: " + option + " to the current Time"));
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
        atm.add("setgeneration");
        atm.add("getgeneration");
        atm.add("addticks");
        if (args.length == 1) {
            return CommandBase.func_175762_a((String[])args, atm);
        }
        return atm;
    }

    public boolean func_82358_a(String[] args, int index) {
        return false;
    }
}

