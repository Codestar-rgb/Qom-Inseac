/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.world.CelestialNightData;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ShrimpDropHandler {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntityLiving() instanceof EntityLivingBase)) {
            return;
        }
        if (event.getEntity().field_70170_p == null || event.getEntity().field_70170_p.field_72995_K) {
            return;
        }
        EntityLivingBase entity = event.getEntityLiving();
        if (!this.isSPMob(entity)) {
            return;
        }
        if (!this.isArrowCelestialActive(entity)) {
            return;
        }
        Random rand = entity.func_70681_au();
        if (rand.nextFloat() >= 0.25f) {
            return;
        }
        int count = 1 + rand.nextInt(5);
        ItemStack stack = new ItemStack(SPItems.shrimp, count);
        BlockPos pos = entity.func_180425_c();
        EntityItem drop = new EntityItem(entity.field_70170_p, (double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o() + 0.5, (double)pos.func_177952_p() + 0.5, stack);
        event.getDrops().add(drop);
    }

    private boolean isSPMob(EntityLivingBase entity) {
        return entity instanceof EntityParasiteBase;
    }

    private boolean isArrowCelestialActive(EntityLivingBase entity) {
        if (entity == null || entity.field_70170_p == null || entity.field_70170_p.field_72995_K) {
            return false;
        }
        if (!entity.field_70170_p.field_73011_w.func_76569_d()) {
            return false;
        }
        int dim = entity.field_70170_p.field_73011_w.getDimension();
        CelestialNightData nightData = CelestialNightData.get(entity.field_70170_p);
        if (nightData == null) {
            return false;
        }
        CelestialNightData.DimState state = nightData.getOrCreate(dim);
        return state.active.contains("arrow") || state.forced.contains("arrow");
    }
}

