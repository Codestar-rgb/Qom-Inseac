/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.network;

import com.subspaceparasite.network.HitboxHit;
import com.subspaceparasite.network.MsgQlipShake;
import com.subspaceparasite.network.MsgRequestScan;
import com.subspaceparasite.network.MsgScanCooldown;
import com.subspaceparasite.network.MsgSpawnPureParticles;
import com.subspaceparasite.network.MsgSyncCelestialPhase;
import com.subspaceparasite.network.msg.C2SRequestEscape;
import com.subspaceparasite.network.msg.S2CSetEscapeOffer;
import com.subspaceparasite.world.celestial.PacketCelestialNightState;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class SPNetwork {
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("subspaceparasite");

    private SPNetwork() {
    }

    public static void register() {
        int id = 0;
        CHANNEL.registerMessage(MsgRequestScan.Handler.class, MsgRequestScan.class, id++, Side.SERVER);
        CHANNEL.registerMessage(MsgScanCooldown.Handler.class, MsgScanCooldown.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(MsgQlipShake.Handler.class, MsgQlipShake.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(MsgSpawnPureParticles.Handler.class, MsgSpawnPureParticles.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(MsgSyncCelestialPhase.Handler.class, MsgSyncCelestialPhase.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(PacketCelestialNightState.Handler.class, PacketCelestialNightState.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(S2CSetEscapeOffer.class, S2CSetEscapeOffer.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(C2SRequestEscape.class, C2SRequestEscape.class, id++, Side.SERVER);
        CHANNEL.registerMessage(HitboxHit.class, HitboxHit.class, id++, Side.CLIENT);
        CHANNEL.registerMessage(HitboxHit.class, HitboxHit.class, id++, Side.SERVER);
    }
}

