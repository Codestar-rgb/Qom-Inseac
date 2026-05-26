/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.bestiary.event;

import com.subspaceparasite.bestiary.BestiaryEntry;
import com.subspaceparasite.bestiary.ParasiteTier;
import com.subspaceparasite.bestiary.SPBestiaryRegistry;
import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import java.lang.reflect.Field;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BestiaryEvents {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent e) {
        if (e.getSource() == null || !(e.getSource().func_76346_g() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer p = (EntityPlayer)e.getSource().func_76346_g();
        IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        EntityLivingBase mob = e.getEntityLiving();
        ResourceLocation key = EntityList.func_191301_a((Entity)mob);
        if (key == null) {
            return;
        }
        String mobId = key.toString();
        prog.addKill(mobId, 1);
        prog.markMobSeen(mobId);
        ParasiteTier tier = null;
        for (BestiaryEntry be : SPBestiaryRegistry.all()) {
            try {
                Field fMobId = be.getClass().getField("mobId");
                Field fTier = be.getClass().getField("tier");
                Object idObj = fMobId.get(be);
                if (!(idObj instanceof String) || !mobId.equals(idObj)) continue;
                Object tierObj = fTier.get(be);
                if (!(tierObj instanceof ParasiteTier)) break;
                tier = (ParasiteTier)((Object)tierObj);
                break;
            }
            catch (Throwable throwable) {
            }
        }
        if (tier != null) {
            prog.markTierSeen(tier);
        }
    }
}

