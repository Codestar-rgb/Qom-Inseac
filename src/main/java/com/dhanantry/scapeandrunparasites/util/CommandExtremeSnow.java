/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.network.ExtremeSnowNetwork;
import com.dhanantry.scapeandrunparasites.world.ExtremeSnowData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommandExtremeSnow
extends CommandBase {
    public String func_71517_b() {
        return "srp_extremesnow";
    }

    public String func_71518_a(ICommandSender s) {
        return "/srp_extremesnow <on|off|toggle> [intensity 0..1] [anywhere 0|1] [windDeg 0..359] [windSpeed 0..1]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        float intensity;
        boolean enabled;
        if (args.length < 1) {
            throw new WrongUsageException(this.func_71518_a(sender), new Object[0]);
        }
        World world = sender.func_130014_f_();
        if (world.field_72995_K) {
            return;
        }
        ExtremeSnowData data = ExtremeSnowData.get(world);
        if ("on".equalsIgnoreCase(args[0])) {
            enabled = true;
        } else if ("off".equalsIgnoreCase(args[0])) {
            enabled = false;
        } else if ("toggle".equalsIgnoreCase(args[0])) {
            enabled = !data.isEnabled();
        } else {
            throw new WrongUsageException(this.func_71518_a(sender), new Object[0]);
        }
        float f = intensity = args.length >= 2 ? (float)CommandExtremeSnow.func_175756_a((String)args[1], (double)0.0, (double)1.0) : data.getIntensity();
        boolean anywhere = args.length >= 3 ? CommandExtremeSnow.func_175764_a((String)args[2], (int)0, (int)1) == 1 : data.isForceAnywhere();
        float windDeg = args.length >= 4 ? (float)CommandExtremeSnow.func_175756_a((String)args[3], (double)0.0, (double)359.0) : data.getWindDeg();
        float windSpeed = args.length >= 5 ? (float)CommandExtremeSnow.func_175756_a((String)args[4], (double)0.0, (double)1.0) : data.getWindSpeed();
        data.setEnabled(enabled);
        data.setIntensity(intensity);
        data.setForceAnywhere(anywhere);
        data.setWindDeg(windDeg);
        data.setWindSpeed(windSpeed);
        data.func_76185_a();
        if (world instanceof WorldServer) {
            ExtremeSnowNetwork.broadcast((WorldServer)world, enabled, intensity, anywhere, windDeg, windSpeed);
        }
        CommandExtremeSnow.func_152373_a((ICommandSender)sender, (ICommand)this, (String)String.format("Extreme Snow: %s (intensity=%.2f, anywhere=%s, windDeg=%.0f, windSpeed=%.2f)", enabled ? "ON" : "OFF", Float.valueOf(intensity), anywhere, Float.valueOf(windDeg), Float.valueOf(windSpeed)), (Object[])new Object[0]);
    }
}

