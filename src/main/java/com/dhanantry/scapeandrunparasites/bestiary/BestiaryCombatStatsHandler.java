/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.bestiary;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BestiaryCombatStatsHandler {
    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        EntityPlayer player;
        IBestiaryProgress prog;
        if (event == null || event.isCanceled()) {
            return;
        }
        EntityLivingBase target = event.getEntityLiving();
        if (target == null) {
            return;
        }
        float amount = event.getAmount();
        if (amount <= 0.0f) {
            return;
        }
        Entity trueAttacker = BestiaryCombatStatsHandler.getTrueAttacker(event.getSource());
        if (BestiaryCombatStatsHandler.isParasite((Entity)target) && trueAttacker instanceof EntityPlayer) {
            EntityPlayer player2 = (EntityPlayer)trueAttacker;
            IBestiaryProgress prog2 = (IBestiaryProgress)player2.getCapability(BestiaryCapability.CAP, null);
            if (prog2 != null) {
                prog2.addDamageToParasites(amount);
            }
            return;
        }
        if (target instanceof EntityPlayer && BestiaryCombatStatsHandler.isParasite(trueAttacker) && (prog = (IBestiaryProgress)(player = (EntityPlayer)target).getCapability(BestiaryCapability.CAP, null)) != null) {
            prog.addDamageFromParasites(amount);
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        IBestiaryProgress prog;
        if (event == null || event.isCanceled()) {
            return;
        }
        EntityLivingBase dead = event.getEntityLiving();
        if (!(dead instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)dead;
        Entity trueAttacker = BestiaryCombatStatsHandler.getTrueAttacker(event.getSource());
        if (BestiaryCombatStatsHandler.isParasite(trueAttacker) && (prog = (IBestiaryProgress)player.getCapability(BestiaryCapability.CAP, null)) != null) {
            prog.addDeathsByParasites(1);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (event == null) {
            return;
        }
        if (event.getOriginal() == null || event.getEntityPlayer() == null) {
            return;
        }
        IBestiaryProgress oldProg = (IBestiaryProgress)event.getOriginal().getCapability(BestiaryCapability.CAP, null);
        IBestiaryProgress newProg = (IBestiaryProgress)event.getEntityPlayer().getCapability(BestiaryCapability.CAP, null);
        if (oldProg != null && newProg != null) {
            newProg.copyCombatStatsFrom(oldProg);
        }
    }

    private static Entity getTrueAttacker(DamageSource source) {
        Entity shooter;
        if (source == null) {
            return null;
        }
        Entity trueSource = source.func_76346_g();
        if (trueSource != null) {
            return trueSource;
        }
        Entity immediate = source.func_76364_f();
        if (immediate instanceof EntityArrow && (shooter = ((EntityArrow)immediate).field_70250_c) != null) {
            return shooter;
        }
        if (immediate instanceof IProjectile) {
            return immediate;
        }
        return immediate;
    }

    private static boolean isParasite(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityParasiteBase) {
            return true;
        }
        ResourceLocation key = EntityListSafe.getKey(entity);
        return key != null && "srparasites".equals(key.func_110624_b());
    }

    private static final class EntityListSafe {
        private EntityListSafe() {
        }

        private static ResourceLocation getKey(Entity entity) {
            try {
                return EntityList.func_191301_a((Entity)entity);
            }
            catch (Throwable t) {
                return null;
            }
        }
    }
}

