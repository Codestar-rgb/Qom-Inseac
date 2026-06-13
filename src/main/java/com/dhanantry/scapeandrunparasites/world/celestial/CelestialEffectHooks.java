/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialEffectRegistry;
import com.dhanantry.scapeandrunparasites.world.celestial.ICelestialEventEffect;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CelestialEffectHooks {
    private static boolean isNight(World world) {
        long dayTime = world.func_72820_D() % 24000L;
        return dayTime >= 13000L && dayTime <= 23000L;
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        World world = e.getWorld();
        if (world == null || world.field_72995_K) {
            return;
        }
        if (!CelestialEffectHooks.isNight(world)) {
            return;
        }
        Entity ent = e.getEntity();
        if (!(ent instanceof EntityParasiteBase)) {
            return;
        }
        EntityParasiteBase parasite = (EntityParasiteBase)ent;
        for (String id : CelestialEffectRegistry.getActiveIds(world)) {
            ICelestialEventEffect fx = CelestialEffectRegistry.get(id);
            if (fx == null) continue;
            fx.onParasiteSpawn(parasite, null);
        }
    }
}

