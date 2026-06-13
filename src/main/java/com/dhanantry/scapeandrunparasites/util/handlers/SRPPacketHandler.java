/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.network.SRPPacketBiomeChange;
import com.dhanantry.scapeandrunparasites.network.SRPPacketBiomeChangeBatch;
import com.dhanantry.scapeandrunparasites.network.SRPPacketClock;
import com.dhanantry.scapeandrunparasites.network.SRPPacketCompass;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyDead;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyHit;
import com.dhanantry.scapeandrunparasites.network.SRPPacketFog;
import com.dhanantry.scapeandrunparasites.network.SRPPacketGuiDistortionState;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMeleeRange;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMovingSound;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMusicTrackCancelUpdateClientEvoPhase;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.network.SRPPacketRequestEvoPhaseClient;
import com.dhanantry.scapeandrunparasites.network.SRPPacketUpdateEvoPhaseClient;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class SRPPacketHandler {
    private static int packetId = 0;

    public static int nextID() {
        return packetId++;
    }

    public static void init() {
        SRPMain.network = NetworkRegistry.INSTANCE.newSimpleChannel("SRParasites");
        SRPMain.network.registerMessage(SRPPacketMeleeRange.Handler.class, SRPPacketMeleeRange.class, SRPPacketHandler.nextID(), Side.SERVER);
        SRPMain.network.registerMessage(SRPPacketEntityBodyHit.Handler.class, SRPPacketEntityBodyHit.class, SRPPacketHandler.nextID(), Side.SERVER);
        SRPMain.network.registerMessage(SRPPacketRequestEvoPhaseClient.Handler.class, SRPPacketRequestEvoPhaseClient.class, SRPPacketHandler.nextID(), Side.SERVER);
        SRPMain.network.registerMessage(SRPPacketBiomeChange.Handler.class, SRPPacketBiomeChange.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketBiomeChangeBatch.Handler.class, SRPPacketBiomeChangeBatch.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketMovingSound.Handler.class, SRPPacketMovingSound.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketParticle.Handler.class, SRPPacketParticle.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketEntityBodyDead.Handler.class, SRPPacketEntityBodyDead.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketFog.Handler.class, SRPPacketFog.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketCompass.Handler.class, SRPPacketCompass.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketClock.Handler.class, SRPPacketClock.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketUpdateEvoPhaseClient.Handler.class, SRPPacketUpdateEvoPhaseClient.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketMusicTrackCancelUpdateClientEvoPhase.Handler.class, SRPPacketMusicTrackCancelUpdateClientEvoPhase.class, SRPPacketHandler.nextID(), Side.CLIENT);
        SRPMain.network.registerMessage(SRPPacketGuiDistortionState.Handler.class, SRPPacketGuiDistortionState.class, SRPPacketHandler.nextID(), Side.CLIENT);
    }
}

