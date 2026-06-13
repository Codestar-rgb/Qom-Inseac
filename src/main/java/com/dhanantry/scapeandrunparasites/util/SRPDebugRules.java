/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.GameRules$ValueType
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.util;

import net.minecraft.world.GameRules;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public final class SRPDebugRules {
    public static final String RULE_FORCE_HARLEQUIN = "srpForceHarlequin";

    private SRPDebugRules() {
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load e) {
        if (e.getWorld().field_72995_K) {
            return;
        }
        GameRules rules = e.getWorld().func_82736_K();
        if (!rules.func_82765_e(RULE_FORCE_HARLEQUIN)) {
            rules.func_180262_a(RULE_FORCE_HARLEQUIN, "false", GameRules.ValueType.BOOLEAN_VALUE);
        }
    }
}

