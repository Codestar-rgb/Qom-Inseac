/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.bestiary.net;

import com.subspaceparasite.bestiary.net.PacketBestiaryRequest;
import com.subspaceparasite.bestiary.net.PacketBestiarySeenCelestial;
import com.subspaceparasite.bestiary.net.PacketBestiarySync;
import com.subspaceparasite.network.PacketVengeanceFX;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class BestiaryNetwork {
    public static final String CH_NAME = "srp_bestiary";
    public static final SimpleNetworkWrapper CH = NetworkRegistry.INSTANCE.newSimpleChannel("srp_bestiary");
    private static int ID = 0;
    private static boolean registered = false;

    private static int nextId() {
        return ID++;
    }

    private BestiaryNetwork() {
    }

    public static void register() {
        if (registered) {
            return;
        }
        ID = 0;
        CH.registerMessage(PacketBestiaryRequest.Handler.class, PacketBestiaryRequest.class, BestiaryNetwork.nextId(), Side.SERVER);
        CH.registerMessage(PacketBestiarySync.Handler.class, PacketBestiarySync.class, BestiaryNetwork.nextId(), Side.CLIENT);
        CH.registerMessage(PacketVengeanceFX.Handler.class, PacketVengeanceFX.class, BestiaryNetwork.nextId(), Side.CLIENT);
        CH.registerMessage(PacketBestiarySeenCelestial.Handler.class, PacketBestiarySeenCelestial.class, BestiaryNetwork.nextId(), Side.SERVER);
        System.out.println("[SRP][BESTIARY][NET] Registered packets on channel 'srp_bestiary'");
        registered = true;
    }
}

