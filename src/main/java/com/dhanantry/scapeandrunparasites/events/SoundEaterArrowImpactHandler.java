package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.entity.ai.SoundEaterSoundHelper;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public class SoundEaterArrowImpactHandler {
   @SubscribeEvent
   public static void onProjectileImpact(ProjectileImpactEvent event) {
      if (event.getRayTraceResult() != null) {
         if (event.getEntity() instanceof IProjectile) {
            World world = event.getEntity().field_70170_p;
            if (!world.field_72995_K) {
               BlockPos hitPos = event.getRayTraceResult().func_178782_a();
               if (hitPos == null) {
                  hitPos = new BlockPos(event.getEntity());
               }

               SoundEaterSoundHelper.broadcastSound(world, hitPos, 18.0, 120);
            }
         }
      }
   }
}
