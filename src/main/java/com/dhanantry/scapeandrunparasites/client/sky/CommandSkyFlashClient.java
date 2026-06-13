/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.client.sky;

import com.dhanantry.scapeandrunparasites.client.sky.SkyFlashRenderer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class CommandSkyFlashClient
extends CommandBase {
    public String func_71517_b() {
        return "Apophis";
    }

    public String func_71518_a(ICommandSender s) {
        return "/Apophis [ticks] [span] [alt] [count] [bank]";
    }

    public int func_82362_a() {
        return 0;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        int ticks = 15;
        double span = 180.0;
        double alt = 120.0;
        int count = 10;
        float bank = -15.0f;
        if (args.length > 0) {
            ticks = CommandSkyFlashClient.func_175764_a((String)args[0], (int)1, (int)600);
        }
        if (args.length > 1) {
            span = CommandSkyFlashClient.func_175765_c((String)args[1]);
        }
        if (args.length > 2) {
            alt = CommandSkyFlashClient.func_175765_c((String)args[2]);
        }
        if (args.length > 3) {
            count = CommandSkyFlashClient.func_175764_a((String)args[3], (int)1, (int)100);
        }
        if (args.length > 4) {
            bank = (float)CommandSkyFlashClient.func_175765_c((String)args[4]);
        }
        if (Minecraft.func_71410_x().field_71441_e == null) {
            return;
        }
        Random rng = new Random();
        for (int i = 0; i < count; ++i) {
            double yawOffsetDeg = rng.nextDouble() * 120.0 - 60.0;
            double forwardOffset = rng.nextDouble() * 200.0 - 100.0;
            double lateralOffset = rng.nextDouble() * 300.0 - 150.0;
            double altJitter = alt + rng.nextDouble() * 60.0 - 30.0;
            float bankJitter = bank + (float)(rng.nextDouble() * 20.0 - 10.0);
            SkyFlashRenderer.triggerOffset(ticks, span, altJitter, bankJitter, yawOffsetDeg, forwardOffset, lateralOffset);
        }
        sender.func_145747_a((ITextComponent)new TextComponentString(String.format("%d %dt %.1f %.1f", count, ticks, span, alt)));
    }
}

