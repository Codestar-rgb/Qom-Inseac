package com.dhanantry.scapeandrunparasites.util;

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public final class SRPDebugRules {
   public static final String RULE_FORCE_HARLEQUIN = "srpForceHarlequin";

   private SRPDebugRules() {
   }

   @SubscribeEvent
   public static void onWorldLoad(Load e) {
      if (!e.getWorld().field_72995_K) {
         GameRules rules = e.getWorld().func_82736_K();
         if (!rules.func_82765_e("srpForceHarlequin")) {
            rules.func_180262_a("srpForceHarlequin", "false", ValueType.BOOLEAN_VALUE);
         }
      }
   }
}
