/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.GameRules
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.item;

import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.util.config.SPConfigMobs;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="subspaceparasite")
public class PearlEvents {
    private static final ResourceLocation SIM = new ResourceLocation("subspaceparasite", "sim_enderman");
    private static final ResourceLocation FERAL = new ResourceLocation("subspaceparasite", "fer_enderman");
    private static final ResourceLocation ASSIMARA = new ResourceLocation("subspaceparasite", "mar_enderman");
    private static final ResourceLocation SIM_HEAD = new ResourceLocation("subspaceparasite", "sim_endermanhead");

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent e) {
        if (e.getEntity().field_70170_p.field_72995_K) {
            return;
        }
        ResourceLocation id = EntityList.func_191301_a((Entity)e.getEntity());
        if (id == null) {
            return;
        }
        float chance = 0.0f;
        if (id.equals((Object)SIM)) {
            chance = 0.1f;
        } else if (id.equals((Object)FERAL)) {
            chance = 0.3f;
        } else if (id.equals((Object)ASSIMARA)) {
            chance = 0.4f;
        }
        if (chance <= 0.0f) {
            return;
        }
        if (e.getEntity().field_70170_p.field_73012_v.nextFloat() < chance) {
            ItemStack drop = new ItemStack(SPItems.pearl);
            e.getDrops().add(new EntityItem(e.getEntity().field_70170_p, e.getEntity().field_70165_t, e.getEntity().field_70163_u, e.getEntity().field_70161_v, drop));
        }
    }

    @SubscribeEvent
    public static void onPlayerDrops(PlayerDropsEvent e) {
        boolean isBeholder;
        EntityPlayer player = e.getEntityPlayer();
        GameRules rules = player.field_70170_p.func_82736_K();
        if (!SPConfigMobs.pearlDestroyedOnBeholderKill) {
            return;
        }
        if (rules.func_82766_b("keepInventory")) {
            return;
        }
        DamageSource src = e.getSource();
        if (src == null) {
            return;
        }
        Entity killer = src.func_76346_g();
        if (!(killer instanceof EntityLivingBase)) {
            return;
        }
        ResourceLocation killerId = EntityList.func_191301_a((Entity)killer);
        if (killerId == null) {
            return;
        }
        boolean bl = isBeholder = killerId.equals((Object)SIM) || killerId.equals((Object)SIM_HEAD) || killerId.equals((Object)FERAL) || killerId.equals((Object)ASSIMARA);
        if (!isBeholder) {
            return;
        }
        Iterator it = e.getDrops().iterator();
        while (it.hasNext()) {
            EntityItem ent = (EntityItem)it.next();
            ItemStack s = ent.func_92059_d();
            if (s == null || s.func_77973_b() != SPItems.pearl) continue;
            it.remove();
        }
    }
}

