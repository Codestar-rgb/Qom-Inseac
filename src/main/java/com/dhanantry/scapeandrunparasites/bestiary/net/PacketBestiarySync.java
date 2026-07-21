package com.dhanantry.scapeandrunparasites.bestiary.net;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.CelestialEventsPage;
import com.dhanantry.scapeandrunparasites.client.FieldGuideClientSettings;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialSkyRenderer;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBestiarySync implements IMessage {
   private static int pendingUnlockMask = 0;
   private static final int PEND_BLOCK = 1;
   private static final int PEND_MOB = 2;
   private static final int PEND_CEL = 4;
   private static long nextAllowedUnlockSoundMs = 0L;
   private static final long UNLOCK_SOUND_COOLDOWN_MS = 650L;
   private static long worldJoinTimeMs = -1L;
   private static boolean initialSyncDone = false;
   private static int lastDimension = Integer.MIN_VALUE;
   private static long soundArmedUntilMs = 0L;
   private static final long HARD_JOIN_MUTE_MS = 2500L;
   private NBTTagCompound nbt;

   private static boolean tryConsumeUnlockSoundSlot() {
      long now = System.currentTimeMillis();
      if (now < nextAllowedUnlockSoundMs) {
         return false;
      } else {
         nextAllowedUnlockSoundMs = now + 650L;
         return true;
      }
   }

   public static boolean tryConsumeUnlockSoundSlotPublic() {
      return tryConsumeUnlockSoundSlot();
   }

   public static void armUnlockSounds(int ms) {
      soundArmedUntilMs = System.currentTimeMillis() + Math.max(0, ms);
   }

   public static boolean isUnlockSoundArmed() {
      return System.currentTimeMillis() <= soundArmedUntilMs;
   }

   public static boolean isTriggerActive(Minecraft mc) {
      if (mc == null || mc.field_71439_g == null) {
         return false;
      } else if (isUnlockSoundArmed()) {
         return true;
      } else {
         GuiScreen s = mc.field_71462_r;
         if (s == null) {
            return false;
         } else {
            String n = s.getClass().getName().toLowerCase();
            return n.contains("bestiary") || n.contains("fieldguide") || n.contains("compendium");
         }
      }
   }

   public PacketBestiarySync() {
      this.nbt = new NBTTagCompound();
   }

   public PacketBestiarySync(IBestiaryProgress prog) {
      this.nbt = prog != null ? prog.serializeNBT() : new NBTTagCompound();
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeTag(buf, this.nbt == null ? new NBTTagCompound() : this.nbt);
   }

   public void fromBytes(ByteBuf buf) {
      NBTTagCompound read = ByteBufUtils.readTag(buf);
      this.nbt = read == null ? new NBTTagCompound() : read;
   }

   private static boolean hasFieldGuide(EntityPlayer p) {
      if (p == null) {
         return false;
      } else {
         Item guide = SRPItems.SRP_FIELD_GUIDE;

         for (ItemStack st : p.field_71071_by.field_70462_a) {
            if (!st.func_190926_b() && st.func_77973_b() == guide) {
               return true;
            }
         }

         for (ItemStack stx : p.field_71071_by.field_184439_c) {
            if (!stx.func_190926_b() && stx.func_77973_b() == guide) {
               return true;
            }
         }

         return false;
      }
   }

   private static Set<String> readStringSet(NBTTagCompound tag, String key) {
      Set<String> out = new HashSet<>();
      if (tag != null && key != null) {
         NBTTagList list = tag.func_150295_c(key, 8);

         for (int i = 0; i < list.func_74745_c(); i++) {
            out.add(list.func_150307_f(i));
         }

         return out;
      } else {
         return out;
      }
   }

   public static class Handler implements IMessageHandler<PacketBestiarySync, IMessage> {
      public IMessage onMessage(PacketBestiarySync msg, MessageContext ctx) {
         if (ctx.side.isClient()) {
            PacketBestiarySync.Handler.ClientHandler.handleClient(msg);
         }

         return null;
      }

      private static void refreshGui(Minecraft mc) {
         GuiScreen screen = mc.field_71462_r;
         if (screen instanceof CelestialEventsPage) {
            try {
               ((CelestialEventsPage)screen).refreshFromCapability();
            } catch (Throwable var4) {
            }
         } else if (screen != null) {
            try {
               screen.func_73866_w_();
            } catch (Throwable var3) {
            }
         }
      }

      private static class ClientHandler {
         private static final boolean DEBUG = false;

         public static void handleClient(PacketBestiarySync msg) {
            Minecraft mc = Minecraft.func_71410_x();
            mc.func_152344_a(() -> {
               if (mc.field_71439_g != null && mc.field_71441_e != null) {
                  if (PacketBestiarySync.worldJoinTimeMs < 0L) {
                     PacketBestiarySync.worldJoinTimeMs = System.currentTimeMillis();
                     PacketBestiarySync.initialSyncDone = false;
                     PacketBestiarySync.lastDimension = mc.field_71439_g.field_71093_bK;
                     PacketBestiarySync.pendingUnlockMask = 0;
                  } else if (mc.field_71439_g.field_71093_bK != PacketBestiarySync.lastDimension) {
                     PacketBestiarySync.worldJoinTimeMs = System.currentTimeMillis();
                     PacketBestiarySync.initialSyncDone = false;
                     PacketBestiarySync.lastDimension = mc.field_71439_g.field_71093_bK;
                     PacketBestiarySync.pendingUnlockMask = 0;
                  }

                  IBestiaryProgress prog = (IBestiaryProgress)mc.field_71439_g.getCapability(BestiaryCapability.CAP, null);
                  if (prog != null) {
                     NBTTagCompound before = prog.serializeNBT();
                     Set<String> oldMobs = PacketBestiarySync.readStringSet(before, "seenMobs");
                     Set<String> oldBlocks = PacketBestiarySync.readStringSet(before, "seenBlocks");
                     Set<String> oldCel = PacketBestiarySync.readStringSet(before, "seenCelestials");
                     prog.deserializeNBT(msg.nbt);
                     NBTTagCompound after = prog.serializeNBT();
                     Set<String> newMobs = PacketBestiarySync.readStringSet(after, "seenMobs");
                     Set<String> newBlocks = PacketBestiarySync.readStringSet(after, "seenBlocks");
                     Set<String> newCel = PacketBestiarySync.readStringSet(after, "seenCelestials");
                     if (!oldCel.equals(newCel)) {
                        try {
                           CelestialSkyRenderer.clearSeenCacheClient();
                        } catch (Throwable var21) {
                        }
                     }

                     if (!PacketBestiarySync.initialSyncDone) {
                        PacketBestiarySync.initialSyncDone = true;
                        PacketBestiarySync.Handler.refreshGui(mc);
                     } else {
                        Set<String> addedMobs = new HashSet<>(newMobs);
                        addedMobs.removeAll(oldMobs);
                        Set<String> addedBlocks = new HashSet<>(newBlocks);
                        addedBlocks.removeAll(oldBlocks);
                        Set<String> addedCel = new HashSet<>(newCel);
                        addedCel.removeAll(oldCel);
                        if (!addedBlocks.isEmpty()) {
                           PacketBestiarySync.pendingUnlockMask = PacketBestiarySync.pendingUnlockMask | 1;
                        }

                        if (!addedMobs.isEmpty()) {
                           PacketBestiarySync.pendingUnlockMask = PacketBestiarySync.pendingUnlockMask | 2;
                        }

                        if (!addedCel.isEmpty()) {
                           PacketBestiarySync.pendingUnlockMask = PacketBestiarySync.pendingUnlockMask | 4;
                        }

                        boolean hasBookInInventory = PacketBestiarySync.hasFieldGuide(mc.field_71439_g);
                        boolean soundsEnabled = FieldGuideClientSettings.bestiarySounds;
                        long joinAge = System.currentTimeMillis() - PacketBestiarySync.worldJoinTimeMs;
                        boolean hardJoinMuteOver = joinAge >= 2500L;
                        boolean triggerActive = PacketBestiarySync.isTriggerActive(mc);
                        boolean canMakeNoise = hasBookInInventory && soundsEnabled && hardJoinMuteOver && triggerActive;
                        if (canMakeNoise && PacketBestiarySync.pendingUnlockMask != 0 && PacketBestiarySync.tryConsumeUnlockSoundSlot()) {
                           if ((PacketBestiarySync.pendingUnlockMask & 4) != 0) {
                              PacketBestiarySync.pendingUnlockMask = PacketBestiarySync.pendingUnlockMask & -5;
                              mc.field_71439_g.func_184185_a(SRPSounds.BOOK_UNLOCK_CELESTIAL, 1.0F, 1.0F);
                           } else if ((PacketBestiarySync.pendingUnlockMask & 2) != 0) {
                              PacketBestiarySync.pendingUnlockMask = PacketBestiarySync.pendingUnlockMask & -3;
                              mc.field_71439_g.func_184185_a(SRPSounds.BOOK_UNLOCK_ENTITY, 1.0F, 1.0F);
                           } else if ((PacketBestiarySync.pendingUnlockMask & 1) != 0) {
                              PacketBestiarySync.pendingUnlockMask = PacketBestiarySync.pendingUnlockMask & -2;
                              mc.field_71439_g.func_184185_a(SRPSounds.BOOK_UNLOCK_BLOCK, 1.0F, 1.0F);
                           }
                        }

                        PacketBestiarySync.Handler.refreshGui(mc);
                     }
                  }
               } else {
                  PacketBestiarySync.worldJoinTimeMs = -1L;
                  PacketBestiarySync.initialSyncDone = false;
                  PacketBestiarySync.lastDimension = Integer.MIN_VALUE;
                  PacketBestiarySync.pendingUnlockMask = 0;
               }
            });
         }
      }
   }
}
