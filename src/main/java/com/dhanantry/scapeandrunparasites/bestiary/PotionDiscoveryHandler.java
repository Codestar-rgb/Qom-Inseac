/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.event.entity.living.PotionEvent$PotionAddedEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.dhanantry.scapeandrunparasites.bestiary;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiarySync;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PotionDiscoveryHandler {
    @SubscribeEvent
    public void onPotionAdded(PotionEvent.PotionAddedEvent e) {
        if (e.getEntityLiving() == null || e.getEntityLiving().field_70170_p == null) {
            return;
        }
        if (e.getEntityLiving().field_70170_p.field_72995_K) {
            return;
        }
        if (!(e.getEntityLiving() instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP p = (EntityPlayerMP)e.getEntityLiving();
        PotionEffect pe = e.getPotionEffect();
        if (pe == null) {
            return;
        }
        Potion potion = pe.func_188419_a();
        ResourceLocation rl = potion.getRegistryName();
        if (rl == null) {
            return;
        }
        if (!"srparasites".equals(rl.func_110624_b())) {
            return;
        }
        IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        String id = rl.func_110623_a();
        if (!prog.hasSeenEffect(id)) {
            prog.markEffectSeen(id);
            BestiaryNetwork.CH.sendTo((IMessage)new PacketBestiarySync(prog), p);
        }
    }
}

