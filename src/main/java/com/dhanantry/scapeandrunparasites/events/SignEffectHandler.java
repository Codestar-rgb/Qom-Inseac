/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public final class SignEffectHandler {
    private static final int DURATION_TICKS = 40;
    private static final String CHARM_ID = "srparasites:the_sign_charm";
    private static Item SIGN_ITEM;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END || e.player.field_70170_p.field_72995_K) {
            return;
        }
        EntityPlayer p = e.player;
        if (SIGN_ITEM == null) {
            SIGN_ITEM = Item.func_111206_d((String)CHARM_ID);
        }
        if (SIGN_ITEM == null) {
            return;
        }
        boolean hasCharm = SignEffectHandler.hasItemAnywhere(p, SIGN_ITEM);
        if (hasCharm) {
            p.func_70690_d(new PotionEffect(SRPPotions.THE_SIGN_E, 40, 0, false, false));
        }
    }

    private static boolean hasItemAnywhere(EntityPlayer p, Item item) {
        for (ItemStack s : p.field_71071_by.field_70462_a) {
            if (s.func_190926_b() || s.func_77973_b() != item) continue;
            return true;
        }
        for (ItemStack s : p.field_71071_by.field_184439_c) {
            if (s.func_190926_b() || s.func_77973_b() != item) continue;
            return true;
        }
        for (ItemStack s : p.field_71071_by.field_70460_b) {
            if (s.func_190926_b() || s.func_77973_b() != item) continue;
            return true;
        }
        return false;
    }

    private SignEffectHandler() {
    }
}

