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
      SRPMain.network.registerMessage(SRPPacketMeleeRange.Handler.class, SRPPacketMeleeRange.class, nextID(), Side.SERVER);
      SRPMain.network.registerMessage(SRPPacketEntityBodyHit.Handler.class, SRPPacketEntityBodyHit.class, nextID(), Side.SERVER);
      SRPMain.network.registerMessage(SRPPacketRequestEvoPhaseClient.Handler.class, SRPPacketRequestEvoPhaseClient.class, nextID(), Side.SERVER);
      SRPMain.network.registerMessage(SRPPacketBiomeChange.Handler.class, SRPPacketBiomeChange.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketBiomeChangeBatch.Handler.class, SRPPacketBiomeChangeBatch.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketMovingSound.Handler.class, SRPPacketMovingSound.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketParticle.Handler.class, SRPPacketParticle.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketEntityBodyDead.Handler.class, SRPPacketEntityBodyDead.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketFog.Handler.class, SRPPacketFog.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketCompass.Handler.class, SRPPacketCompass.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketClock.Handler.class, SRPPacketClock.class, nextID(), Side.CLIENT);
      SRPMain.network.registerMessage(SRPPacketUpdateEvoPhaseClient.Handler.class, SRPPacketUpdateEvoPhaseClient.class, nextID(), Side.CLIENT);
      SRPMain.network
         .registerMessage(
            SRPPacketMusicTrackCancelUpdateClientEvoPhase.Handler.class, SRPPacketMusicTrackCancelUpdateClientEvoPhase.class, nextID(), Side.CLIENT
         );
      SRPMain.network.registerMessage(SRPPacketGuiDistortionState.Handler.class, SRPPacketGuiDistortionState.class, nextID(), Side.CLIENT);
   }
}
