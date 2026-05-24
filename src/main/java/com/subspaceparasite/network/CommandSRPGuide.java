/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.network;

import com.subspaceparasite.bestiary.ParasiteTier;
import com.subspaceparasite.bestiary.SPBestiaryRegistry;
import com.subspaceparasite.bestiary.blocks.BlockBestiaryEntry;
import com.subspaceparasite.bestiary.blocks.SPBlockCompendiumRegistry;
import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import com.subspaceparasite.bestiary.effects.SPStatusEffectRegistry;
import com.subspaceparasite.bestiary.net.BestiaryNetwork;
import com.subspaceparasite.bestiary.net.PacketBestiarySync;
import com.subspaceparasite.client.celestial.CelestialObjectDefinition;
import com.subspaceparasite.client.celestial.CelestialObjectRegistry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CommandSPGuide
extends CommandBase {
    private static final ConcurrentHashMap<UUID, NBTTagCompound> SNAPSHOTS = new ConcurrentHashMap();

    public String func_71517_b() {
        return "srpguide";
    }

    private static void sync(EntityPlayerMP p, IBestiaryProgress prog) {
        BestiaryNetwork.CH.sendTo((IMessage)new PacketBestiarySync(prog), p);
    }

    public String func_71518_a(ICommandSender sender) {
        return this.getUsageKey();
    }

    private String getUsageKey() {
        return "command.srpguide.usage";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String sub;
        if (!(sender instanceof EntityPlayerMP)) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.players_only", new Object[0]));
            return;
        }
        EntityPlayerMP p = (EntityPlayerMP)sender;
        if (args.length == 0) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation(this.getUsageKey(), new Object[0]));
            return;
        }
        switch (sub = args[0].toLowerCase(Locale.ROOT)) {
            case "unlockall": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                SNAPSHOTS.put(p.func_110124_au(), prog.serializeNBT());
                int mobCount = 0;
                int blockCount = 0;
                int celestialCount = 0;
                int effectCount = 0;
                for (ParasiteTier t : ParasiteTier.values()) {
                    prog.markTierSeen(t);
                }
                for (Object e : SPBestiaryRegistry.all()) {
                    try {
                        Object tierObj;
                        Field fMobId = e.getClass().getField("mobId");
                        Field fTier = e.getClass().getField("tier");
                        Object idObj = fMobId.get(e);
                        if (!(idObj instanceof String)) continue;
                        String mobId = (String)idObj;
                        prog.markMobSeen(mobId);
                        int have = prog.getKills(mobId);
                        if (have < 999) {
                            prog.addKill(mobId, 999 - have);
                        }
                        if ((tierObj = fTier.get(e)) instanceof ParasiteTier) {
                            prog.markTierSeen((ParasiteTier)((Object)tierObj));
                        }
                        ++mobCount;
                    }
                    catch (Throwable fMobId) {}
                }
                for (BlockBestiaryEntry blockBestiaryEntry : SPBlockCompendiumRegistry.all()) {
                    if (prog.hasSeenBlock(blockBestiaryEntry.id)) continue;
                    prog.markBlockSeen(blockBestiaryEntry.id);
                    ++blockCount;
                }
                for (CelestialObjectDefinition celestialObjectDefinition : CelestialObjectRegistry.getObjects()) {
                    if (prog.hasSeenCelestial(celestialObjectDefinition.id)) continue;
                    prog.markCelestialSeen(celestialObjectDefinition.id);
                    ++celestialCount;
                }
                for (SPStatusEffectRegistry.Entry entry : SPStatusEffectRegistry.all()) {
                    if (entry == null || entry.id == null) continue;
                    try {
                        if (prog.hasSeenEffect(entry.id)) continue;
                        prog.markEffectSeen(entry.id);
                        ++effectCount;
                    }
                    catch (Throwable t) {
                        try {
                            Method has = prog.getClass().getMethod("hasSeenEffect", String.class);
                            Method mark = prog.getClass().getMethod("markEffectSeen", String.class);
                            Object ok = has.invoke((Object)prog, entry.id);
                            if (ok instanceof Boolean && ((Boolean)ok).booleanValue()) continue;
                            mark.invoke((Object)prog, entry.id);
                            ++effectCount;
                        }
                        catch (Throwable throwable) {}
                    }
                }
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockall_ok", new Object[]{mobCount}));
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockblocks_ok", new Object[]{blockCount}));
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockcelestial_ok", new Object[]{celestialCount}));
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockeffects_ok", new Object[]{effectCount}));
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "unlockblocks": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                int blockCount = 0;
                for (BlockBestiaryEntry entry : SPBlockCompendiumRegistry.all()) {
                    if (prog.hasSeenBlock(entry.id)) continue;
                    prog.markBlockSeen(entry.id);
                    ++blockCount;
                }
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockblocks_ok", new Object[]{blockCount}));
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "unlockcelestial": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                int count = 0;
                for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
                    if (prog.hasSeenCelestial(def.id)) continue;
                    prog.markCelestialSeen(def.id);
                    ++count;
                }
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockcelestial_ok", new Object[]{count}));
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "unlockeffects": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                int count = 0;
                for (SPStatusEffectRegistry.Entry e : SPStatusEffectRegistry.all()) {
                    if (e == null || e.id == null) continue;
                    try {
                        if (prog.hasSeenEffect(e.id)) continue;
                        prog.markEffectSeen(e.id);
                        ++count;
                    }
                    catch (Throwable t) {
                        try {
                            Method has = prog.getClass().getMethod("hasSeenEffect", String.class);
                            Method method = prog.getClass().getMethod("markEffectSeen", String.class);
                            Object ok = has.invoke((Object)prog, e.id);
                            if (ok instanceof Boolean && ((Boolean)ok).booleanValue()) continue;
                            method.invoke((Object)prog, e.id);
                            ++count;
                        }
                        catch (Throwable throwable) {}
                    }
                }
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.unlockeffects_ok", new Object[]{count}));
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "clear": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                NBTTagCompound snap = SNAPSHOTS.get(p.func_110124_au());
                if (snap != null) {
                    prog.deserializeNBT(snap.func_74737_b());
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.clear_restore_ok", new Object[0]));
                } else {
                    prog.deserializeNBT(new NBTTagCompound());
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.clear_no_snapshot", new Object[0]));
                }
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "clearall": 
            case "reset": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                prog.deserializeNBT(new NBTTagCompound());
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.clearall_ok", new Object[0]));
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "clearblocks": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                prog.getSeenBlocks().clear();
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.clearblocks_ok", new Object[0]));
                CommandSPGuide.sync(p, prog);
                break;
            }
            case "clearcelestial": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                try {
                    Method m = prog.getClass().getMethod("getSeenCelestials", new Class[0]);
                    Object o = m.invoke((Object)prog, new Object[0]);
                    if (o instanceof Set) {
                        ((Set)o).clear();
                        sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.clearcelestial_ok", new Object[0]));
                        CommandSPGuide.sync(p, prog);
                        break;
                    }
                }
                catch (Throwable m) {
                    // empty catch block
                }
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.clearcelestial_need_getter", new Object[0]));
                break;
            }
            case "cleareffects": {
                IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                    return;
                }
                try {
                    Method m = prog.getClass().getMethod("getSeenEffects", new Class[0]);
                    Object o = m.invoke((Object)prog, new Object[0]);
                    if (o instanceof Set) {
                        ((Set)o).clear();
                        sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cleareffects_ok", new Object[0]));
                        CommandSPGuide.sync(p, prog);
                        break;
                    }
                }
                catch (Throwable m) {
                    // empty catch block
                }
                try {
                    NBTTagCompound tag = prog.serializeNBT();
                    if (tag.func_74764_b("seenEffects")) {
                        tag.func_82580_o("seenEffects");
                        prog.deserializeNBT(tag);
                        sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cleareffects_ok", new Object[0]));
                        CommandSPGuide.sync(p, prog);
                        break;
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("command.srpguide.cleareffects_need_getter", new Object[0]));
                break;
            }
            default: {
                sender.func_145747_a((ITextComponent)new TextComponentTranslation(this.getUsageKey(), new Object[0]));
            }
        }
    }
}

