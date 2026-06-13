/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
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

public class PacketBestiarySync
implements IMessage {
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
        }
        nextAllowedUnlockSoundMs = now + 650L;
        return true;
    }

    public static boolean tryConsumeUnlockSoundSlotPublic() {
        return PacketBestiarySync.tryConsumeUnlockSoundSlot();
    }

    public static void armUnlockSounds(int ms) {
        soundArmedUntilMs = System.currentTimeMillis() + (long)Math.max(0, ms);
    }

    public static boolean isUnlockSoundArmed() {
        return System.currentTimeMillis() <= soundArmedUntilMs;
    }

    public static boolean isTriggerActive(Minecraft mc) {
        if (mc == null || mc.field_71439_g == null) {
            return false;
        }
        if (PacketBestiarySync.isUnlockSoundArmed()) {
            return true;
        }
        GuiScreen s = mc.field_71462_r;
        if (s != null) {
            String n = s.getClass().getName().toLowerCase();
            return n.contains("bestiary") || n.contains("fieldguide") || n.contains("compendium");
        }
        return false;
    }

    public PacketBestiarySync() {
        this.nbt = new NBTTagCompound();
    }

    public PacketBestiarySync(IBestiaryProgress prog) {
        this.nbt = prog != null ? prog.serializeNBT() : new NBTTagCompound();
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)(this.nbt == null ? new NBTTagCompound() : this.nbt));
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound read = ByteBufUtils.readTag((ByteBuf)buf);
        this.nbt = read == null ? new NBTTagCompound() : read;
    }

    private static boolean hasFieldGuide(EntityPlayer p) {
        if (p == null) {
            return false;
        }
        Item guide = SRPItems.SRP_FIELD_GUIDE;
        for (ItemStack st : p.field_71071_by.field_70462_a) {
            if (st.func_190926_b() || st.func_77973_b() != guide) continue;
            return true;
        }
        for (ItemStack st : p.field_71071_by.field_184439_c) {
            if (st.func_190926_b() || st.func_77973_b() != guide) continue;
            return true;
        }
        return false;
    }

    private static Set<String> readStringSet(NBTTagCompound tag, String key) {
        HashSet<String> out = new HashSet<String>();
        if (tag == null || key == null) {
            return out;
        }
        NBTTagList list = tag.func_150295_c(key, 8);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            out.add(list.func_150307_f(i));
        }
        return out;
    }

    public static class Handler
    implements IMessageHandler<PacketBestiarySync, IMessage> {
        public IMessage onMessage(PacketBestiarySync msg, MessageContext ctx) {
            if (ctx.side.isClient()) {
                ClientHandler.handleClient(msg);
            }
            return null;
        }

        private static void refreshGui(Minecraft mc) {
            GuiScreen screen = mc.field_71462_r;
            if (screen instanceof CelestialEventsPage) {
                try {
                    ((CelestialEventsPage)screen).refreshFromCapability();
                }
                catch (Throwable throwable) {}
            } else if (screen != null) {
                try {
                    screen.func_73866_w_();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
        }

        private static class ClientHandler {
            private static final boolean DEBUG = false;

            private ClientHandler() {
            }

            public static void handleClient(PacketBestiarySync msg) {
                Minecraft mc = Minecraft.func_71410_x();
                mc.func_152344_a(() -> {
                    boolean canMakeNoise;
                    if (mc.field_71439_g == null || mc.field_71441_e == null) {
                        worldJoinTimeMs = -1L;
                        initialSyncDone = false;
                        lastDimension = Integer.MIN_VALUE;
                        pendingUnlockMask = 0;
                        return;
                    }
                    if (worldJoinTimeMs < 0L) {
                        worldJoinTimeMs = System.currentTimeMillis();
                        initialSyncDone = false;
                        lastDimension = mc.field_71439_g.field_71093_bK;
                        pendingUnlockMask = 0;
                    } else if (mc.field_71439_g.field_71093_bK != lastDimension) {
                        worldJoinTimeMs = System.currentTimeMillis();
                        initialSyncDone = false;
                        lastDimension = mc.field_71439_g.field_71093_bK;
                        pendingUnlockMask = 0;
                    }
                    IBestiaryProgress prog = (IBestiaryProgress)mc.field_71439_g.getCapability(BestiaryCapability.CAP, null);
                    if (prog == null) {
                        return;
                    }
                    NBTTagCompound before = prog.serializeNBT();
                    Set oldMobs = PacketBestiarySync.readStringSet(before, "seenMobs");
                    Set oldBlocks = PacketBestiarySync.readStringSet(before, "seenBlocks");
                    Set oldCel = PacketBestiarySync.readStringSet(before, "seenCelestials");
                    prog.deserializeNBT(msg.nbt);
                    NBTTagCompound after = prog.serializeNBT();
                    Set newMobs = PacketBestiarySync.readStringSet(after, "seenMobs");
                    Set newBlocks = PacketBestiarySync.readStringSet(after, "seenBlocks");
                    Set newCel = PacketBestiarySync.readStringSet(after, "seenCelestials");
                    if (!oldCel.equals(newCel)) {
                        try {
                            CelestialSkyRenderer.clearSeenCacheClient();
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                    }
                    if (!initialSyncDone) {
                        initialSyncDone = true;
                        Handler.refreshGui(mc);
                        return;
                    }
                    HashSet addedMobs = new HashSet(newMobs);
                    addedMobs.removeAll(oldMobs);
                    HashSet addedBlocks = new HashSet(newBlocks);
                    addedBlocks.removeAll(oldBlocks);
                    HashSet addedCel = new HashSet(newCel);
                    addedCel.removeAll(oldCel);
                    if (!addedBlocks.isEmpty()) {
                        pendingUnlockMask = pendingUnlockMask | 1;
                    }
                    if (!addedMobs.isEmpty()) {
                        pendingUnlockMask = pendingUnlockMask | 2;
                    }
                    if (!addedCel.isEmpty()) {
                        pendingUnlockMask = pendingUnlockMask | 4;
                    }
                    boolean hasBookInInventory = PacketBestiarySync.hasFieldGuide((EntityPlayer)mc.field_71439_g);
                    boolean soundsEnabled = FieldGuideClientSettings.bestiarySounds;
                    long joinAge = System.currentTimeMillis() - worldJoinTimeMs;
                    boolean hardJoinMuteOver = joinAge >= 2500L;
                    boolean triggerActive = PacketBestiarySync.isTriggerActive(mc);
                    boolean bl = canMakeNoise = hasBookInInventory && soundsEnabled && hardJoinMuteOver && triggerActive;
                    if (canMakeNoise && pendingUnlockMask != 0 && PacketBestiarySync.tryConsumeUnlockSoundSlot()) {
                        if ((pendingUnlockMask & 4) != 0) {
                            pendingUnlockMask = pendingUnlockMask & 0xFFFFFFFB;
                            mc.field_71439_g.func_184185_a(SRPSounds.BOOK_UNLOCK_CELESTIAL, 1.0f, 1.0f);
                        } else if ((pendingUnlockMask & 2) != 0) {
                            pendingUnlockMask = pendingUnlockMask & 0xFFFFFFFD;
                            mc.field_71439_g.func_184185_a(SRPSounds.BOOK_UNLOCK_ENTITY, 1.0f, 1.0f);
                        } else if ((pendingUnlockMask & 1) != 0) {
                            pendingUnlockMask = pendingUnlockMask & 0xFFFFFFFE;
                            mc.field_71439_g.func_184185_a(SRPSounds.BOOK_UNLOCK_BLOCK, 1.0f, 1.0f);
                        }
                    }
                    Handler.refreshGui(mc);
                });
            }
        }
    }
}

