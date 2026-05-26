/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$WorldTickEvent
 */
package com.subspaceparasite.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="subspaceparasite")
public final class SPResidueFireManager {
    private static final Map<Integer, Map<BlockPos, Integer>> TRACK = new HashMap<Integer, Map<BlockPos, Integer>>();

    private SPResidueFireManager() {
    }

    public static void lightAndTrack(World w, BlockPos pos, int ttl) {
        if (w.field_72995_K) {
            return;
        }
        if (ttl <= 0) {
            return;
        }
        if (w.func_175623_d(pos)) {
            w.func_180501_a(pos, Blocks.field_150480_ab.func_176223_P(), 3);
        }
        TRACK.computeIfAbsent(w.field_73011_w.getDimension(), d -> new HashMap()).put(pos.func_185334_h(), ttl);
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase != TickEvent.Phase.END || e.world.field_72995_K) {
            return;
        }
        int dim = e.world.field_73011_w.getDimension();
        Map<BlockPos, Integer> m = TRACK.get(dim);
        if (m == null || m.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<BlockPos, Integer>> it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<BlockPos, Integer> en = it.next();
            int left = en.getValue() - 1;
            if (left <= 0) {
                BlockPos p = en.getKey();
                if (e.world.func_180495_p(p).func_177230_c() == Blocks.field_150480_ab) {
                    e.world.func_175698_g(p);
                }
                it.remove();
                continue;
            }
            en.setValue(left);
        }
        if (m.isEmpty()) {
            TRACK.remove(dim);
        }
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload e) {
        if (e.getWorld().field_72995_K) {
            return;
        }
        TRACK.remove(e.getWorld().field_73011_w.getDimension());
    }
}

