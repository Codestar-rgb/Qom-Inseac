/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.block.BlockInfestedRemain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public final class SRPRemainHooks {
    private SRPRemainHooks() {
    }

    @SubscribeEvent
    public static void onLightningSpawn(EntityJoinWorldEvent e) {
        if (!(e.getEntity() instanceof EntityLightningBolt)) {
            return;
        }
        World w = e.getWorld();
        if (w.field_72995_K) {
            return;
        }
        BlockPos strike = e.getEntity().func_180425_c();
        int r = 5;
        BlockPos.MutableBlockPos cur = new BlockPos.MutableBlockPos();
        for (int dx = -r; dx <= r; ++dx) {
            for (int dy = -r; dy <= r; ++dy) {
                for (int dz = -r; dz <= r; ++dz) {
                    cur.func_181079_c(strike.func_177958_n() + dx, strike.func_177956_o() + dy, strike.func_177952_p() + dz);
                    IBlockState s = w.func_180495_p((BlockPos)cur);
                    if (!(s.func_177230_c() instanceof BlockInfestedRemain)) continue;
                    w.func_175698_g((BlockPos)cur);
                }
            }
        }
    }
}

