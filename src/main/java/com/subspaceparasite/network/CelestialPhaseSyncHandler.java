/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$WorldTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.network;

import com.subspaceparasite.client.celestial.CelestialObjectDefinition;
import com.subspaceparasite.client.celestial.CelestialObjectRegistry;
import com.subspaceparasite.network.MsgSyncCelestialPhase;
import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.CelestialNightData;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.celestial.CelestialEffectRegistry;
import com.subspaceparasite.world.celestial.ICelestialEventEffect;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CelestialPhaseSyncHandler {
    private static final boolean DEBUG = false;
    private static final Random RAND = new Random();
    private static final Map<Integer, Boolean> WAS_NIGHT = new HashMap<Integer, Boolean>();

    private static void debug(String msg) {
    }

    private static long getNightIndex(World world) {
        return world.func_82737_E() / 24000L;
    }

    private static boolean isNight(World world) {
        long dayTime = world.func_72820_D() % 24000L;
        return dayTime >= 13000L && dayTime <= 23000L;
    }

    private static void rollNight(World world, int dim, int phase, CelestialNightData.DimState state) {
        state.active.clear();
        long nightIndex = state.nightIndex;
        long baseSeed = world.func_72905_C() ^ nightIndex * 918273L;
        for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
            if (!def.isPhaseAllowed(phase)) continue;
            long seed = baseSeed + (long)def.id.hashCode() * 31L;
            RAND.setSeed(seed);
            if (!(RAND.nextFloat() <= def.chancePerNight)) continue;
            state.active.add(def.id);
        }
    }

    private static void callNightStart(World world, int dim, int phase, long nightIndex, Set<String> started) {
        for (String id : started) {
            ICelestialEventEffect fx = CelestialEffectRegistry.get(id);
            if (fx == null) continue;
            fx.onNightStart(world, dim, phase, nightIndex);
        }
    }

    private static void callNightEnd(World world, int dim, int phase, long nightIndex, Set<String> ended) {
        for (String id : ended) {
            ICelestialEventEffect fx = CelestialEffectRegistry.get(id);
            if (fx == null) continue;
            fx.onNightEnd(world, dim, phase, nightIndex);
        }
    }

    public static void syncDim(World world, int dim, int phase, long nightIndex, Set<String> activeSet, Set<String> forcedSet) {
        if (world.func_73046_m() == null) {
            CelestialPhaseSyncHandler.debug("world.getMinecraftServer() is null, cannot send (dim=" + dim + ")");
            return;
        }
        for (EntityPlayerMP player : world.func_73046_m().func_184103_al().func_181057_v()) {
            if (player.field_71093_bK != dim) continue;
            CelestialPhaseSyncHandler.debug("Sending phase=" + phase + " dim=" + dim + " night=" + nightIndex + " to " + player.func_70005_c_());
            SPNetwork.CHANNEL.sendTo((IMessage)new MsgSyncCelestialPhase(dim, phase, nightIndex, activeSet, forcedSet), player);
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        HashSet<String> prev;
        boolean nightPrev;
        World world = event.world;
        if (world.field_72995_K || event.phase != TickEvent.Phase.END) {
            return;
        }
        if (!SPConfigWorld.enableCelestialObjects) {
            return;
        }
        if (!world.field_73011_w.func_76569_d()) {
            return;
        }
        int dim = world.field_73011_w.getDimension();
        SPSaveData data = SPSaveData.get(world, dim);
        if (data == null) {
            CelestialPhaseSyncHandler.debug("SPSaveData is null for dim " + dim);
            return;
        }
        byte phase = data.getEvolutionPhase(dim);
        CelestialPhaseSyncHandler.debug("SERVER phase=" + phase + " dim=" + dim);
        CelestialNightData nightData = CelestialNightData.get(world);
        CelestialNightData.DimState state = nightData.getOrCreate(dim);
        boolean nightNow = CelestialPhaseSyncHandler.isNight(world);
        boolean bl = nightPrev = WAS_NIGHT.get(dim) != null ? WAS_NIGHT.get(dim) : false;
        if (nightNow && !nightPrev) {
            prev = new HashSet<String>(state.active);
            prev.addAll(state.forced);
            state.nightIndex = CelestialPhaseSyncHandler.getNightIndex(world);
            CelestialPhaseSyncHandler.rollNight(world, dim, phase, state);
            HashSet<String> next = new HashSet<String>(state.active);
            next.addAll(state.forced);
            HashSet<String> started = new HashSet<String>(next);
            started.removeAll(prev);
            HashSet<String> ended = new HashSet<String>(prev);
            ended.removeAll(next);
            CelestialPhaseSyncHandler.callNightEnd(world, dim, phase, state.nightIndex, ended);
            CelestialPhaseSyncHandler.callNightStart(world, dim, phase, state.nightIndex, started);
            nightData.func_76185_a();
            CelestialPhaseSyncHandler.syncDim(world, dim, phase, state.nightIndex, state.active, state.forced);
        }
        if (!nightNow && nightPrev) {
            prev = new HashSet<String>(state.active);
            prev.addAll(state.forced);
            CelestialPhaseSyncHandler.callNightEnd(world, dim, phase, state.nightIndex, prev);
        }
        WAS_NIGHT.put(dim, nightNow);
    }
}

