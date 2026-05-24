/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class EnemyOfMyEnemyAdvancement {
    private static final ResourceLocation ADV_ID = new ResourceLocation("subspaceparasite", "enemy_enemy");
    private static final String CRITERION = "creeper_killed_parasite";
    private static final double RADIUS_SQ = 4096.0;

    public static void register() {
        MinecraftForge.EVENT_BUS.register((Object)new EnemyOfMyEnemyAdvancement());
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent e) {
        if (e.getEntityLiving().field_70170_p.field_72995_K) {
            return;
        }
        Entity killer = e.getSource().func_76346_g();
        if (!(killer instanceof EntityCreeper)) {
            return;
        }
        EntityLivingBase victim = e.getEntityLiving();
        ResourceLocation id = EntityList.func_191301_a((Entity)victim);
        if (id == null) {
            return;
        }
        if (!"subspaceparasite".equals(id.func_110624_b())) {
            return;
        }
        WorldServer ws = (WorldServer)e.getEntity().field_70170_p;
        for (EntityPlayerMP p : ws.func_73046_m().func_184103_al().func_181057_v()) {
            Advancement adv;
            if (p.field_70170_p.field_73011_w.getDimension() != ws.field_73011_w.getDimension() || p.func_70068_e(e.getEntity()) > 4096.0 || (adv = p.func_184102_h().func_191949_aK().func_192778_a(ADV_ID)) == null) continue;
            p.func_192039_O().func_192750_a(adv, CRITERION);
        }
    }
}

