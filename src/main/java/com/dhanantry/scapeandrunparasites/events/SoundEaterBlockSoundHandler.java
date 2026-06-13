/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.entity.ai.SoundEaterSoundHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public class SoundEaterBlockSoundHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        World world = event.getWorld();
        if (world.field_72995_K) {
            return;
        }
        SoundEaterSoundHelper.broadcastSound(world, event.getPos(), 16.0, 100);
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.PlaceEvent event) {
        World world = event.getWorld();
        if (world.field_72995_K) {
            return;
        }
        SoundEaterSoundHelper.broadcastSound(world, event.getPos(), 12.0, 80);
    }
}

