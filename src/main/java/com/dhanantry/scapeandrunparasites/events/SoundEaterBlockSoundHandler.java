package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.entity.ai.SoundEaterSoundHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public class SoundEaterBlockSoundHandler {
   @SubscribeEvent
   public static void onBlockBreak(BreakEvent event) {
      World world = event.getWorld();
      if (!world.field_72995_K) {
         SoundEaterSoundHelper.broadcastSound(world, event.getPos(), 16.0, 100);
      }
   }

   @SubscribeEvent
   public static void onBlockPlace(PlaceEvent event) {
      World world = event.getWorld();
      if (!world.field_72995_K) {
         SoundEaterSoundHelper.broadcastSound(world, event.getPos(), 12.0, 80);
      }
   }
}
