/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.ProjectileImpactEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.entity.ai.SoundEaterSoundHelper;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public class SoundEaterArrowImpactHandler {
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() == null) {
            return;
        }
        if (!(event.getEntity() instanceof IProjectile)) {
            return;
        }
        World world = event.getEntity().field_70170_p;
        if (world.field_72995_K) {
            return;
        }
        BlockPos hitPos = event.getRayTraceResult().func_178782_a();
        if (hitPos == null) {
            hitPos = new BlockPos(event.getEntity());
        }
        SoundEaterSoundHelper.broadcastSound(world, hitPos, 18.0, 120);
    }
}

