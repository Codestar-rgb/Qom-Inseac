/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class RupterKillMilestoneAdvancement {
    private static final ResourceLocation ADV_ID = new ResourceLocation("srparasites", "cut_roots");
    private static final String CRITERION = "reached_1000_rupter_kills";
    private static final int TARGET = 1000;

    public static void register() {
        MinecraftForge.EVENT_BUS.register((Object)new RupterKillMilestoneAdvancement());
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent e) {
        Advancement adv;
        if (e.getEntityLiving().field_70170_p.field_72995_K) {
            return;
        }
        Entity src = e.getSource().func_76346_g();
        if (!(src instanceof EntityPlayerMP)) {
            return;
        }
        EntityLivingBase victim = e.getEntityLiving();
        ResourceLocation id = EntityList.func_191301_a((Entity)victim);
        if (id == null) {
            return;
        }
        if (!"srparasites".equals(id.func_110624_b())) {
            return;
        }
        if (!"rupter".equals(id.func_110623_a())) {
            return;
        }
        EntityPlayerMP killer = (EntityPlayerMP)src;
        NBTTagCompound entityData = killer.getEntityData();
        NBTTagCompound persisted = entityData.func_74775_l("PlayerPersisted");
        int count = persisted.func_74762_e("srpRupterKills");
        persisted.func_74768_a("srpRupterKills", ++count);
        entityData.func_74782_a("PlayerPersisted", (NBTBase)persisted);
        if (count >= 1000 && (adv = killer.func_184102_h().func_191949_aK().func_192778_a(ADV_ID)) != null) {
            killer.func_192039_O().func_192750_a(adv, CRITERION);
        }
    }
}

