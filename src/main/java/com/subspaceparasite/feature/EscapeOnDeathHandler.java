/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.feature;

import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.network.msg.S2CSetEscapeOffer;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EscapeOnDeathHandler {
    private static final String PERSIST_TAG = "PlayerPersisted";
    private static final String OFFER_TAG = "srp_offer_escape";
    private static final String WINDOW_TAG = "srp_death_window";
    private static final long WINDOW_MS = 60000L;
    private static final int THRESHOLD = 5;
    private static final Map<UUID, Deque<Long>> streaks = new HashMap<UUID, Deque<Long>>();

    private static boolean isSrparasitesCause(LivingDeathEvent e) {
        if (e.getSource() == null) {
            return false;
        }
        if (e.getSource().func_76346_g() != null && e.getSource().func_76346_g().getClass().getName().startsWith("com.subspaceparasite")) {
            return true;
        }
        if (e.getSource().func_76364_f() != null && e.getSource().func_76364_f().getClass().getName().startsWith("com.subspaceparasite")) {
            return true;
        }
        String dmg = e.getSource().field_76373_n == null ? "" : e.getSource().field_76373_n;
        return dmg.contains("subspaceparasite");
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        if (!(e.getEntity() instanceof EntityPlayerMP)) {
            return;
        }
        if (!SPConfigWorld.escapeEnabled) {
            return;
        }
        if (!EscapeOnDeathHandler.isSrparasitesCause(e)) {
            return;
        }
        EntityPlayerMP p = (EntityPlayerMP)e.getEntity();
        long nowMs = p.field_70170_p.func_82737_E() * 50L;
        Deque q = streaks.computeIfAbsent(p.func_110124_au(), k -> new ArrayDeque());
        q.addLast(nowMs);
        while (!q.isEmpty() && nowMs - (Long)q.peekFirst() > 60000L) {
            q.removeFirst();
        }
        boolean offer = q.size() > 5;
        NBTTagCompound persisted = p.getEntityData().func_74775_l(PERSIST_TAG);
        persisted.func_74757_a(OFFER_TAG, offer);
        persisted.func_74772_a(WINDOW_TAG, nowMs);
        p.getEntityData().func_74782_a(PERSIST_TAG, (NBTBase)persisted);
        SPNetwork.CHANNEL.sendTo((IMessage)new S2CSetEscapeOffer(offer), p);
    }

    public static void clearOffer(EntityPlayerMP p) {
        NBTTagCompound persisted = p.getEntityData().func_74775_l(PERSIST_TAG);
        persisted.func_74757_a(OFFER_TAG, false);
        p.getEntityData().func_74782_a(PERSIST_TAG, (NBTBase)persisted);
        SPNetwork.CHANNEL.sendTo((IMessage)new S2CSetEscapeOffer(false), p);
    }
}

