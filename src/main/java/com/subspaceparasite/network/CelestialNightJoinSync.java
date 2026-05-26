/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.network;

import com.subspaceparasite.network.MsgSyncCelestialPhase;
import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.world.CelestialNightData;
import com.subspaceparasite.world.SPSaveData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CelestialNightJoinSync {
    private static long getNightIndex(World world) {
        return world.func_82737_E() / 24000L;
    }

    private static void sync(EntityPlayerMP player) {
        World world = player.field_70170_p;
        if (world == null || world.field_72995_K) {
            return;
        }
        int dim = player.field_71093_bK;
        SPSaveData save = SPSaveData.get(world, dim);
        if (save == null) {
            return;
        }
        byte phase = save.getEvolutionPhase(dim);
        CelestialNightData nightData = CelestialNightData.get(world);
        CelestialNightData.DimState state = nightData.getOrCreate(dim);
        long nightIndex = CelestialNightJoinSync.getNightIndex(world);
        SPNetwork.CHANNEL.sendTo((IMessage)new MsgSyncCelestialPhase(dim, phase, nightIndex, state.active, state.forced), player);
    }

    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent e) {
        if (e.player instanceof EntityPlayerMP) {
            CelestialNightJoinSync.sync((EntityPlayerMP)e.player);
        }
    }

    @SubscribeEvent
    public void onDimChange(PlayerEvent.PlayerChangedDimensionEvent e) {
        if (e.player instanceof EntityPlayerMP) {
            CelestialNightJoinSync.sync((EntityPlayerMP)e.player);
        }
    }
}

