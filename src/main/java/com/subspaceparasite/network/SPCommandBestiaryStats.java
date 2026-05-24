/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 */
package com.subspaceparasite.network;

import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class SPCommandBestiaryStats
implements ICommand {
    private final List<String> aliases = new ArrayList<String>();

    public SPCommandBestiaryStats() {
        this.aliases.add("srpstats");
        this.aliases.add("srpbestiarystats");
    }

    public int compareTo(ICommand other) {
        return this.func_71517_b().compareTo(other.func_71517_b());
    }

    public String func_71517_b() {
        return "srpbestiarystats";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/srpbestiarystats clear [player]";
    }

    public List<String> func_71514_a() {
        return this.aliases;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        IBestiaryProgress prog;
        EntityPlayerMP target;
        if (args.length < 1 || !"clear".equalsIgnoreCase(args[0])) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.bestiary_stats.usage", new Object[]{this.func_71518_a(sender)}));
            return;
        }
        if (args.length >= 2) {
            target = server.func_184103_al().func_152612_a(args[1]);
            if (target == null) {
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.bestiary_stats.player_not_found", new Object[]{args[1]}));
                return;
            }
        } else {
            if (!(sender.func_174793_f() instanceof EntityPlayerMP)) {
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.bestiary_stats.console_requires_player", new Object[0]));
                return;
            }
            target = (EntityPlayerMP)sender.func_174793_f();
        }
        if ((prog = (IBestiaryProgress)target.getCapability(BestiaryCapability.CAP, null)) == null) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.bestiary_stats.cap_missing", new Object[]{target.func_70005_c_()}));
            return;
        }
        prog.clearStatsPageData();
        sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.bestiary_stats.cleared_other", new Object[]{target.func_70005_c_()}));
        if (sender.func_174793_f() != target) {
            target.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.bestiary_stats.cleared_self", new Object[0]));
        }
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        ArrayList<String> out = new ArrayList<String>();
        if (args.length == 1) {
            out.add("clear");
            return CommandBase.func_175762_a((String[])args, out);
        }
        if (args.length == 2) {
            return CommandBase.func_71530_a((String[])args, (String[])server.func_71213_z());
        }
        return out;
    }

    public boolean func_82358_a(String[] args, int index) {
        return index == 1;
    }
}

