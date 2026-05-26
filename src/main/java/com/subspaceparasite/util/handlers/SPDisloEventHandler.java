/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Finish
 *  net.minecraftforge.event.entity.living.LivingHealEvent
 *  net.minecraftforge.event.entity.player.EntityItemPickupEvent
 *  net.minecraftforge.event.entity.player.PlayerContainerEvent$Close
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.event.entity.player.PlayerPickupXpEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfigSystems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SPDisloEventHandler {
    @SubscribeEvent
    public void eventDislo(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() == EnumHand.MAIN_HAND) {
            return;
        }
        ParasiteEventWorld.setDisloWorldPhase(event.getWorld(), SPAttributes.EVENTRIGHTCLICKBLOCK, SPConfigSystems.chanceEventRightClickB, SPConfigSystems.disloCOTHSpy, event.getPos());
    }

    @SubscribeEvent
    public void eventDislo(PlayerPickupXpEvent event) {
        ParasiteEventWorld.setDisloWorldPhase(event.getEntity().field_70170_p, SPAttributes.EVENTXPPICKUP, SPConfigSystems.chanceEventXPPickUp, SPConfigSystems.disloCOTHSpy, event.getEntityPlayer().func_180425_c());
    }

    @SubscribeEvent
    public void eventDislo(EntityItemPickupEvent event) {
        ParasiteEventWorld.setDisloWorldPhase(event.getEntity().field_70170_p, SPAttributes.EVENTITEMPICKUP, SPConfigSystems.chanceEventItemPickUp, SPConfigSystems.disloCOTHSpy, event.getEntityPlayer().func_180425_c());
    }

    @SubscribeEvent
    public void eventDislo(LivingHealEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            return;
        }
        ParasiteEventWorld.setDisloWorldPhase(event.getEntity().field_70170_p, SPAttributes.EVENTHEALING, SPConfigSystems.chanceEventHealing, SPConfigSystems.disloCOTHSpy, event.getEntityLiving().func_180425_c());
    }

    @SubscribeEvent
    public void eventDislo(LivingEntityUseItemEvent.Finish event) {
        ParasiteEventWorld.setDisloWorldPhase(event.getEntity().field_70170_p, SPAttributes.EVENTUSEITEM, SPConfigSystems.chanceEventUsteItem, SPConfigSystems.disloCOTHSpy, event.getEntityLiving().func_180425_c());
    }

    @SubscribeEvent
    public void eventDislo(PlayerContainerEvent.Close event) {
        ParasiteEventWorld.setDisloWorldPhase(event.getEntity().field_70170_p, SPAttributes.EVENTMENUCLOSE, SPConfigSystems.chanceEventMEnuClose, SPConfigSystems.disloCOTHSpy, event.getEntityPlayer().func_180425_c());
    }
}

