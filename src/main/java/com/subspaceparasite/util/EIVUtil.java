/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.util;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.SPWorldEntitySpawner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EIVUtil {
    public static void createRandomOrigin(World worldIn, int min, int max) {
        if (!SPConfigWorld.originActivated) {
            return;
        }
        if (SPWorldEntitySpawner.triggerSPAWNING) {
            return;
        }
        SPWorldData data = SPWorldData.get(worldIn);
        int number = data.getorigins("x").size();
        if (number == 0 ? worldIn.field_73012_v.nextInt(SPConfigWorld.originCreatingRandZero) != 0 : worldIn.field_73012_v.nextInt(SPConfigWorld.originCreatingRand) != 0) {
            return;
        }
        if (worldIn.field_73010_i.isEmpty()) {
            return;
        }
        EntityPlayer player = (EntityPlayer)worldIn.field_73010_i.get(worldIn.field_73012_v.nextInt(worldIn.field_73010_i.size()));
        double distance = max <= min ? (double)min : (double)min + worldIn.field_73012_v.nextDouble() * (double)(max - min);
        double angle = worldIn.field_73012_v.nextDouble() * Math.PI * 2.0;
        int dx = (int)Math.round(Math.cos(angle) * distance);
        int dz = (int)Math.round(Math.sin(angle) * distance);
        BlockPos pos = player.func_180425_c().func_177982_a(dx, 64, dz);
        int key = data.setOrigin(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SPConfigWorld.originHealth, SPConfigWorld.originRadius);
        if (key == 1 && !SPConfigWorld.originNewMess.isEmpty()) {
            ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigWorld.originNewMess, 400);
        }
        if (key == 2 && !SPConfigWorld.originNewOutbreakMess.isEmpty()) {
            ParasiteEventEntity.alertAllPlayerSer(worldIn, SPConfigWorld.originNewOutbreakMess, 401);
        }
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        double trueDistance = player.func_70011_f((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
        SPMain.logger.info("[EIV DEBUG] RANDOM_ORIGIN result. Player={} playerPos={} originPos={} dx={} dz={} horizontalDistance={} trueDistance={} min={} max={} health={} radius={} resultKey={} totalOrigins={}", new Object[]{player.func_70005_c_(), player.func_180425_c(), pos, dx, dz, String.format("%.2f", horizontalDistance), String.format("%.2f", trueDistance), min, max, SPConfigWorld.originHealth, SPConfigWorld.originRadius, key, data.getorigins("x").size()});
    }
}

