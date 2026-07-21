package com.dhanantry.scapeandrunparasites.bestiary.net;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBestiarySeenCelestial implements IMessage {
   private String id;

   public PacketBestiarySeenCelestial() {
   }

   public PacketBestiarySeenCelestial(String id) {
      this.id = id;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.id == null ? "" : this.id);
   }

   public void fromBytes(ByteBuf buf) {
      this.id = ByteBufUtils.readUTF8String(buf);
      if (this.id != null && this.id.isEmpty()) {
         this.id = null;
      }
   }

   private static void grantAdvancement(EntityPlayerMP mp, String advancementPath) {
      if (mp != null && mp.field_71133_b != null) {
         Advancement adv = mp.field_71133_b.func_191949_aK().func_192778_a(new ResourceLocation("srparasites", advancementPath));
         if (adv != null) {
            PlayerAdvancements pa = mp.func_192039_O();
            AdvancementProgress progress = pa.func_192747_a(adv);

            for (String criterion : progress.func_192107_d()) {
               pa.func_192750_a(adv, criterion);
            }
         }
      }
   }

   private static void checkCelestialDiscoveryAdvancements(EntityPlayerMP mp, IBestiaryProgress prog) {
      if (mp != null && prog != null) {
         int total = CelestialObjectRegistry.getObjectCount();
         if (total > 0) {
            int discovered = prog.getSeenCelestials().size();
            int halfThreshold = CelestialObjectRegistry.getHalfDiscoveryThreshold();
            if (discovered >= halfThreshold) {
               grantAdvancement(mp, "columbus");
            }

            if (discovered >= total) {
               grantAdvancement(mp, "stolas");
            }
         }
      }
   }

   public static class Handler implements IMessageHandler<PacketBestiarySeenCelestial, IMessage> {
      public IMessage onMessage(PacketBestiarySeenCelestial msg, MessageContext ctx) {
         EntityPlayerMP mp = ctx.getServerHandler().field_147369_b;
         if (mp != null && msg != null && msg.id != null) {
            mp.func_71121_q().func_152344_a(() -> {
               IBestiaryProgress prog = (IBestiaryProgress)mp.getCapability(BestiaryCapability.CAP, null);
               if (prog != null) {
                  if (!prog.hasSeenCelestial(msg.id)) {
                     prog.markCelestialSeen(msg.id);
                  }

                  PacketBestiarySeenCelestial.checkCelestialDiscoveryAdvancements(mp, prog);
                  BestiaryNetwork.CH.sendTo(new PacketBestiarySync(prog), mp);
               }
            });
            return null;
         } else {
            return null;
         }
      }
   }
}
