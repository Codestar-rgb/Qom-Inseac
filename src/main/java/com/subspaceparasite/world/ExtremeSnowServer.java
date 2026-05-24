/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$WorldTickEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.world;

import com.subspaceparasite.world.ExtremeSnowData;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="subspaceparasite", value={Side.SERVER})
public final class ExtremeSnowServer {
    private ExtremeSnowServer() {
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        World w = e.world;
        ExtremeSnowData data = ExtremeSnowData.get(w);
        if (!data.isEnabled()) {
            return;
        }
        List players = w.func_73046_m() != null ? w.func_73046_m().func_184103_al().func_181057_v() : Collections.emptyList();
        int triesPerPlayer = (int)(12.0f + 48.0f * data.getIntensity());
        int radius = 18;
        for (EntityPlayerMP p : players) {
            if (p.field_70170_p != w) continue;
            for (int i = 0; i < triesPerPlayer; ++i) {
                int x = (int)(p.field_70165_t + (double)(w.field_73012_v.nextInt(radius * 2 + 1) - radius));
                int z = (int)(p.field_70161_v + (double)(w.field_73012_v.nextInt(radius * 2 + 1) - radius));
                BlockPos base = new BlockPos(x, (int)p.field_70163_u, z);
                BlockPos hit = w.func_175725_q(base);
                boolean force = ExtremeSnowData.get(w).isForceAnywhere();
                if (!w.func_175678_i(hit) || !force && !w.func_175708_f(hit, false)) continue;
                IBlockState stateAt = w.func_180495_p(hit);
                if (stateAt.func_177230_c() == Blocks.field_150431_aC) {
                    int layers = (Integer)stateAt.func_177229_b((IProperty)BlockSnow.field_176315_a);
                    if (layers >= 8) continue;
                    w.func_180501_a(hit, stateAt.func_177226_a((IProperty)BlockSnow.field_176315_a, (Comparable)Integer.valueOf(layers + 1)), 2);
                    continue;
                }
                if (!stateAt.func_177230_c().isAir(stateAt, (IBlockAccess)w, hit)) continue;
                w.func_180501_a(hit, Blocks.field_150431_aC.func_176223_P().func_177226_a((IProperty)BlockSnow.field_176315_a, (Comparable)Integer.valueOf(1)), 2);
            }
        }
    }
}

