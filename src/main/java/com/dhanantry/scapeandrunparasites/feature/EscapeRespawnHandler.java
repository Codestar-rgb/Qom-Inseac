/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 */
package com.dhanantry.scapeandrunparasites.feature;

import com.dhanantry.scapeandrunparasites.feature.EscapeOnDeathHandler;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EscapeRespawnHandler {
    private static final String PERSIST_TAG = "PlayerPersisted";
    private static final String PENDING_TAG = "srp_escape_pending";

    @SubscribeEvent
    public void onRespawn(PlayerEvent.PlayerRespawnEvent e) {
        if (e.player.field_70170_p.field_72995_K) {
            return;
        }
        if (!SRPConfigWorld.escapeEnabled) {
            return;
        }
        EntityPlayerMP p = (EntityPlayerMP)e.player;
        boolean pending = p.getEntityData().func_74775_l(PERSIST_TAG).func_74767_n(PENDING_TAG);
        if (!pending) {
            return;
        }
        p.getEntityData().func_74775_l(PERSIST_TAG).func_74757_a(PENDING_TAG, false);
        EscapeOnDeathHandler.clearOffer(p);
        BlockPos origin = p.func_180425_c();
        int min = Math.max(0, SRPConfigWorld.escapeMinDistance);
        int max = Math.max(min, SRPConfigWorld.escapeMaxDistance);
        BlockPos target = EscapeRespawnHandler.findSafeRandom((WorldServer)p.field_70170_p, origin, min, max, 24);
        if (target != null) {
            p.field_71135_a.func_147364_a((double)target.func_177958_n() + 0.5, (double)target.func_177956_o(), (double)target.func_177952_p() + 0.5, p.field_70177_z, p.field_70125_A);
        }
    }

    private static BlockPos findSafeRandom(WorldServer world, BlockPos origin, int min, int max, int tries) {
        Random r = world.field_73012_v;
        for (int i = 0; i < tries; ++i) {
            int dz;
            double ang = r.nextDouble() * Math.PI * 2.0;
            int dist = min + r.nextInt(Math.max(1, max - min + 1));
            int dx = origin.func_177958_n() + (int)Math.round(Math.cos(ang) * (double)dist);
            BlockPos top = world.func_175672_r(new BlockPos(dx, 0, dz = origin.func_177952_p() + (int)Math.round(Math.sin(ang) * (double)dist)));
            BlockPos solid = EscapeRespawnHandler.descendToSolid(world, top);
            if (solid == null || !EscapeRespawnHandler.isSafe(world, solid)) continue;
            return solid.func_177984_a();
        }
        return null;
    }

    private static BlockPos descendToSolid(WorldServer w, BlockPos start) {
        BlockPos pos = start;
        for (int i = 0; i < 16; ++i) {
            Material m = w.func_180495_p(pos).func_185904_a();
            if (m.func_76220_a()) {
                return pos;
            }
            if ((pos = pos.func_177977_b()).func_177956_o() <= 4) break;
        }
        return null;
    }

    private static boolean isSafe(WorldServer w, BlockPos solid) {
        if (solid == null) {
            return false;
        }
        Material m = w.func_180495_p(solid).func_185904_a();
        if (!m.func_76220_a() || m == Material.field_151584_j) {
            return false;
        }
        BlockPos feet = solid.func_177984_a();
        BlockPos head = feet.func_177984_a();
        if (!w.func_175623_d(feet) || !w.func_175623_d(head)) {
            return false;
        }
        if (w.func_180495_p(feet).func_185904_a().func_76224_d()) {
            return false;
        }
        return !w.func_180495_p(head).func_185904_a().func_76224_d();
    }
}

