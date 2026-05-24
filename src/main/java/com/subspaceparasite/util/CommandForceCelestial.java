/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 */
package com.subspaceparasite.util;

import com.subspaceparasite.client.celestial.CelestialObjectRegistry;
import com.subspaceparasite.client.celestial.CelestialPhaseClient;
import com.subspaceparasite.network.CelestialPhaseSyncHandler;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.CelestialNightData;
import com.subspaceparasite.world.SPSaveData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandForceCelestial
extends CommandBase {
    public String func_71517_b() {
        return "srp_celestial";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/srp_celestial <list|clear|all|id>";
    }

    public int func_82362_a() {
        return 2;
    }

    private static long getNightIndex(World world) {
        return world.func_82737_E() / 24000L;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean nowForced;
        if (!SPConfigWorld.enableCelestialObjects) {
            sender.func_145747_a((ITextComponent)new TextComponentString("SRP celestial objects are disabled in the config (enableCelestialObjects = false)."));
            return;
        }
        if (args.length < 1) {
            throw new WrongUsageException("Usage: /srp_celestial <list|clear|all|id>", new Object[0]);
        }
        String sub = args[0];
        World world = sender.func_130014_f_();
        int dim = world.field_73011_w.getDimension();
        if ("list".equalsIgnoreCase(sub)) {
            List<String> ids = CelestialObjectRegistry.getAllIds();
            if (ids.isEmpty()) {
                sender.func_145747_a((ITextComponent)new TextComponentString("No celestial events are registered."));
            } else {
                sender.func_145747_a((ITextComponent)new TextComponentString("Celestial events: " + String.join((CharSequence)", ", ids)));
            }
            return;
        }
        if ("debug".equalsIgnoreCase(sub)) {
            if (!world.field_72995_K) {
                SPSaveData save = SPSaveData.get(world, dim);
                int phase = save == null ? -1 : (int)save.getEvolutionPhase(dim);
                CelestialNightData nightData = CelestialNightData.get(world);
                CelestialNightData.DimState state = nightData.getOrCreate(dim);
                sender.func_145747_a((ITextComponent)new TextComponentString("[SERVER] dim=" + dim + " phase=" + phase + " night=" + world.func_82737_E() / 24000L + " active=" + state.active.size() + " forced=" + state.forced.size()));
                sender.func_145747_a((ITextComponent)new TextComponentString("[SERVER] active=" + String.join((CharSequence)", ", state.active)));
                sender.func_145747_a((ITextComponent)new TextComponentString("[SERVER] forced=" + String.join((CharSequence)", ", state.forced)));
            } else {
                int phase = CelestialPhaseClient.getPhase(dim);
                long night = CelestialPhaseClient.getNightIndex(dim);
                Set<String> a = CelestialPhaseClient.getActiveIds(dim);
                Set<String> f = CelestialPhaseClient.getForcedIds(dim);
                sender.func_145747_a((ITextComponent)new TextComponentString("[CLIENT] dim=" + dim + " phase=" + phase + " night=" + night + " active=" + a.size() + " forced=" + f.size()));
                sender.func_145747_a((ITextComponent)new TextComponentString("[CLIENT] active=" + String.join((CharSequence)", ", a)));
                sender.func_145747_a((ITextComponent)new TextComponentString("[CLIENT] forced=" + String.join((CharSequence)", ", f)));
            }
            return;
        }
        if (world.field_72995_K) {
            sender.func_145747_a((ITextComponent)new TextComponentString("This command must be run server-side."));
            return;
        }
        SPSaveData save = SPSaveData.get(world, dim);
        if (save == null) {
            throw new CommandException("SPSaveData is null for dim " + dim, new Object[0]);
        }
        byte phase = save.getEvolutionPhase(dim);
        CelestialNightData nightData = CelestialNightData.get(world);
        CelestialNightData.DimState state = nightData.getOrCreate(dim);
        long nightIndex = CommandForceCelestial.getNightIndex(world);
        if ("clear".equalsIgnoreCase(sub) || "none".equalsIgnoreCase(sub) || "off".equalsIgnoreCase(sub)) {
            if (state.forced.isEmpty()) {
                sender.func_145747_a((ITextComponent)new TextComponentString("No celestial event is currently forced."));
            } else {
                state.forced.clear();
                nightData.func_76185_a();
                CelestialPhaseSyncHandler.syncDim(world, dim, phase, nightIndex, state.active, state.forced);
                sender.func_145747_a((ITextComponent)new TextComponentString("Cleared all forced celestial events in dimension " + dim));
            }
            return;
        }
        if ("all".equalsIgnoreCase(sub)) {
            List<String> ids = CelestialObjectRegistry.getAllIds();
            if (ids.isEmpty()) {
                sender.func_145747_a((ITextComponent)new TextComponentString("No celestial events are registered."));
                return;
            }
            state.forced.clear();
            state.forced.addAll(ids);
            nightData.func_76185_a();
            CelestialPhaseSyncHandler.syncDim(world, dim, phase, nightIndex, state.active, state.forced);
            sender.func_145747_a((ITextComponent)new TextComponentString("Forced ALL celestial events in dimension " + dim));
            return;
        }
        String id = sub;
        if (!CelestialObjectRegistry.getAllIds().contains(id)) {
            throw new CommandException("Unknown celestial id: " + id, new Object[0]);
        }
        if (state.forced.contains(id)) {
            state.forced.remove(id);
            nowForced = false;
        } else {
            state.forced.add(id);
            nowForced = true;
        }
        nightData.func_76185_a();
        CelestialPhaseSyncHandler.syncDim(world, dim, phase, nightIndex, state.active, state.forced);
        sender.func_145747_a((ITextComponent)new TextComponentString((nowForced ? "Forced" : "Unforced") + " celestial event '" + id + "' in dimension " + dim));
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            ArrayList<String> options = new ArrayList<String>();
            options.add("list");
            options.add("clear");
            options.add("all");
            options.addAll(CelestialObjectRegistry.getAllIds());
            return CommandForceCelestial.func_175762_a((String[])args, options);
        }
        return Collections.emptyList();
    }
}

