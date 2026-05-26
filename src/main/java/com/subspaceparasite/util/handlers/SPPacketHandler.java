/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.network.SPPacketBiomeChange;
import com.subspaceparasite.network.SPPacketBiomeChangeBatch;
import com.subspaceparasite.network.SPPacketClock;
import com.subspaceparasite.network.SPPacketCompass;
import com.subspaceparasite.network.SPPacketEntityBodyDead;
import com.subspaceparasite.network.SPPacketEntityBodyHit;
import com.subspaceparasite.network.SPPacketFog;
import com.subspaceparasite.network.SPPacketGuiDistortionState;
import com.subspaceparasite.network.SPPacketMeleeRange;
import com.subspaceparasite.network.SPPacketMovingSound;
import com.subspaceparasite.network.SPPacketMusicTrackCancelUpdateClientEvoPhase;
import com.subspaceparasite.network.SPPacketParticle;
import com.subspaceparasite.network.SPPacketRequestEvoPhaseClient;
import com.subspaceparasite.network.SPPacketUpdateEvoPhaseClient;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class SPPacketHandler {
    private static int packetId = 0;

    public static int nextID() {
        return packetId++;
    }

    public static void init() {
        SPMain.network = NetworkRegistry.INSTANCE.newSimpleChannel("SPParasites");
        SPMain.network.registerMessage(SPPacketMeleeRange.Handler.class, SPPacketMeleeRange.class, SPPacketHandler.nextID(), Side.SERVER);
        SPMain.network.registerMessage(SPPacketEntityBodyHit.Handler.class, SPPacketEntityBodyHit.class, SPPacketHandler.nextID(), Side.SERVER);
        SPMain.network.registerMessage(SPPacketRequestEvoPhaseClient.Handler.class, SPPacketRequestEvoPhaseClient.class, SPPacketHandler.nextID(), Side.SERVER);
        SPMain.network.registerMessage(SPPacketBiomeChange.Handler.class, SPPacketBiomeChange.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketBiomeChangeBatch.Handler.class, SPPacketBiomeChangeBatch.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketMovingSound.Handler.class, SPPacketMovingSound.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketParticle.Handler.class, SPPacketParticle.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketEntityBodyDead.Handler.class, SPPacketEntityBodyDead.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketFog.Handler.class, SPPacketFog.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketCompass.Handler.class, SPPacketCompass.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketClock.Handler.class, SPPacketClock.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketUpdateEvoPhaseClient.Handler.class, SPPacketUpdateEvoPhaseClient.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketMusicTrackCancelUpdateClientEvoPhase.Handler.class, SPPacketMusicTrackCancelUpdateClientEvoPhase.class, SPPacketHandler.nextID(), Side.CLIENT);
        SPMain.network.registerMessage(SPPacketGuiDistortionState.Handler.class, SPPacketGuiDistortionState.class, SPPacketHandler.nextID(), Side.CLIENT);
    }
}

