/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.IGrowable
 *  net.minecraftforge.event.terraingen.SaplingGrowTreeEvent
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.config.SPConfigWorld;
import net.minecraft.block.IGrowable;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SPEventHandlerTerrainGen {
    @SubscribeEvent
    public void allowTreeGrowthTick(SaplingGrowTreeEvent event) {
        if (!(event.getWorld().func_180495_p(event.getPos()).func_177230_c() instanceof IGrowable)) {
            return;
        }
        if (event.getWorld().func_180495_p(event.getPos()).func_177230_c() == SPBlocks.ParasiteSapling) {
            return;
        }
        int heart = ParasiteEventWorld.canBiomeStillExist(event.getWorld(), event.getPos(), false);
        switch (heart) {
            case 1: {
                if (!(event.getWorld().field_73012_v.nextDouble() < (double)SPConfigWorld.nodeCropStopNodeOne)) break;
                event.setResult(Event.Result.DENY);
                break;
            }
            case 2: {
                if (!(event.getWorld().field_73012_v.nextDouble() < (double)SPConfigWorld.nodeCropStopNodeTwo)) break;
                event.setResult(Event.Result.DENY);
                break;
            }
            case 3: {
                if (!(event.getWorld().field_73012_v.nextDouble() < (double)SPConfigWorld.nodeCropStopNodeThree)) break;
                event.setResult(Event.Result.DENY);
                break;
            }
        }
    }
}

