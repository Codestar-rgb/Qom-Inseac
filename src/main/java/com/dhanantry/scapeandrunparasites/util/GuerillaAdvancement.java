/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.advancements.AdvancementProgress
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public final class GuerillaAdvancement {
    private static final ResourceLocation ADV_ID = new ResourceLocation("srparasites", "guerilla");
    private static final String CRITERION = "kill_fear_splashed";
    private static final String TAG_APPLIER = "srpFearApplier";
    private static final String TAG_UNTIL = "srpFearUntil";

    private GuerillaAdvancement() {
    }

    public static void register(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(GuerillaAdvancement.class);
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayerMP)) {
            return;
        }
        if (event.getEntityLiving().field_70170_p.field_72995_K) {
            return;
        }
        Entity trueSrc = event.getSource().func_76346_g();
        if (!(trueSrc instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP victim = (EntityPlayerMP)event.getEntityLiving();
        EntityPlayerMP killer = (EntityPlayerMP)trueSrc;
        NBTTagCompound tag = victim.getEntityData();
        if (!tag.func_74764_b(TAG_APPLIER)) {
            return;
        }
        if (!killer.func_110124_au().toString().equals(tag.func_74779_i(TAG_APPLIER))) {
            return;
        }
        long until = tag.func_74763_f(TAG_UNTIL);
        if (victim.field_70170_p.func_82737_E() > until) {
            return;
        }
        if (!victim.func_70644_a(SRPPotions.FEAR_E)) {
            return;
        }
        Advancement adv = killer.func_184102_h().func_191949_aK().func_192778_a(ADV_ID);
        if (adv == null) {
            return;
        }
        AdvancementProgress prog = killer.func_192039_O().func_192747_a(adv);
        if (!prog.func_192105_a()) {
            prog.func_192109_a(CRITERION);
        }
        tag.func_82580_o(TAG_APPLIER);
        tag.func_82580_o(TAG_UNTIL);
    }
}

