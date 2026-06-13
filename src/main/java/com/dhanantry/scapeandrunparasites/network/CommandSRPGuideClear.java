/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package com.dhanantry.scapeandrunparasites.network;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class CommandSRPGuideClear
extends CommandBase {
    public String func_71517_b() {
        return "srpguideclear";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/srpguideclear";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.func_145747_a((ITextComponent)new TextComponentString("Players only."));
            return;
        }
        server.func_71187_D().func_71556_a(sender, "srpguide clear");
    }
}

