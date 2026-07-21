package com.dhanantry.scapeandrunparasites.bestiary.net;

import com.dhanantry.scapeandrunparasites.network.PacketVengeanceFX;
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
      if (!registered) {
         ID = 0;
         CH.registerMessage(PacketBestiaryRequest.Handler.class, PacketBestiaryRequest.class, nextId(), Side.SERVER);
         CH.registerMessage(PacketBestiarySync.Handler.class, PacketBestiarySync.class, nextId(), Side.CLIENT);
         CH.registerMessage(PacketVengeanceFX.Handler.class, PacketVengeanceFX.class, nextId(), Side.CLIENT);
         CH.registerMessage(PacketBestiarySeenCelestial.Handler.class, PacketBestiarySeenCelestial.class, nextId(), Side.SERVER);
         System.out.println("[SRP][BESTIARY][NET] Registered packets on channel 'srp_bestiary'");
         registered = true;
      }
   }
}
