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
package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialNightData;
import com.dhanantry.scapeandrunparasites.world.celestial.PacketCelestialNightState;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CelestialEventManager {
    private static final Random RAND = new Random();

    public static boolean isActive(World world, String id) {
        if (world == null || world.field_72995_K) {
            return false;
        }
        int dim = world.field_73011_w.getDimension();
        CelestialNightData.DimState s = CelestialNightData.get(world).getState(dim);
        if (s == null) {
            return false;
        }
        return s.forced.contains(id) || s.active.contains(id);
    }

    public static void force(World world, String id, boolean enabled) {
        if (world == null || world.field_72995_K) {
            return;
        }
        int dim = world.field_73011_w.getDimension();
        CelestialNightData data = CelestialNightData.get(world);
        CelestialNightData.DimState s = data.getOrCreate(dim);
        if (enabled) {
            s.forced.add(id);
        } else {
            s.forced.remove(id);
        }
        data.func_76185_a();
        CelestialEventManager.syncDim(world, dim);
    }

    public static void clearForced(World world) {
        if (world == null || world.field_72995_K) {
            return;
        }
        int dim = world.field_73011_w.getDimension();
        CelestialNightData data = CelestialNightData.get(world);
        CelestialNightData.DimState s = data.getOrCreate(dim);
        s.forced.clear();
        data.func_76185_a();
        CelestialEventManager.syncDim(world, dim);
    }

    public static void syncDim(World world, int dim) {
        if (world == null || world.field_72995_K) {
            return;
        }
        CelestialNightData.DimState s = CelestialNightData.get(world).getState(dim);
        if (s == null) {
            return;
        }
        PacketCelestialNightState pkt = new PacketCelestialNightState(dim, s.phase, s.nightIndex, s.active, s.forced);
        for (EntityPlayerMP p : world.func_73046_m().func_184103_al().func_181057_v()) {
            if (p.field_70170_p.field_73011_w.getDimension() != dim) continue;
            SRPNetwork.CHANNEL.sendTo((IMessage)pkt, p);
        }
    }

    private static void rollIfNeeded(World world) {
        if (world == null || world.field_72995_K) {
            return;
        }
        if (!world.field_73011_w.func_76569_d()) {
            return;
        }
        int dim = world.field_73011_w.getDimension();
        long nightIndex = world.func_82737_E() / 24000L;
        CelestialNightData data = CelestialNightData.get(world);
        CelestialNightData.DimState s = data.getOrCreate(dim);
        if (s.nightIndex == nightIndex) {
            return;
        }
        s.nightIndex = nightIndex;
        s.active.clear();
        long baseSeed = world.func_72905_C() ^ nightIndex * 918273L;
        for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
            if (!def.isPhaseAllowed(s.phase)) continue;
            long seed = baseSeed + (long)def.id.hashCode() * 31L;
            RAND.setSeed(seed);
            if (!(RAND.nextFloat() <= def.chancePerNight)) continue;
            s.active.add(def.id);
        }
        data.func_76185_a();
        CelestialEventManager.syncDim(world, dim);
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        World w = e.world;
        if (w == null || w.field_72995_K) {
            return;
        }
        CelestialEventManager.rollIfNeeded(w);
    }
}

