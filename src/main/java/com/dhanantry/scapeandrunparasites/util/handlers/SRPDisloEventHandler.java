package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent.Finish;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent.Close;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SRPDisloEventHandler {
   @SubscribeEvent
   public void eventDislo(RightClickBlock event) {
      if (event.getHand() != EnumHand.MAIN_HAND) {
         ParasiteEventWorld.setDisloWorldPhase(
            event.getWorld(), SRPAttributes.EVENTRIGHTCLICKBLOCK, SRPConfigSystems.chanceEventRightClickB, SRPConfigSystems.disloCOTHSpy, event.getPos()
         );
      }
   }

   @SubscribeEvent
   public void eventDislo(PlayerPickupXpEvent event) {
      ParasiteEventWorld.setDisloWorldPhase(
         event.getEntity().field_70170_p,
         SRPAttributes.EVENTXPPICKUP,
         SRPConfigSystems.chanceEventXPPickUp,
         SRPConfigSystems.disloCOTHSpy,
         event.getEntityPlayer().func_180425_c()
      );
   }

   @SubscribeEvent
   public void eventDislo(EntityItemPickupEvent event) {
      ParasiteEventWorld.setDisloWorldPhase(
         event.getEntity().field_70170_p,
         SRPAttributes.EVENTITEMPICKUP,
         SRPConfigSystems.chanceEventItemPickUp,
         SRPConfigSystems.disloCOTHSpy,
         event.getEntityPlayer().func_180425_c()
      );
   }

   @SubscribeEvent
   public void eventDislo(LivingHealEvent event) {
      if (event.getEntity() instanceof EntityPlayer) {
         ParasiteEventWorld.setDisloWorldPhase(
            event.getEntity().field_70170_p,
            SRPAttributes.EVENTHEALING,
            SRPConfigSystems.chanceEventHealing,
            SRPConfigSystems.disloCOTHSpy,
            event.getEntityLiving().func_180425_c()
         );
      }
   }

   @SubscribeEvent
   public void eventDislo(Finish event) {
      ParasiteEventWorld.setDisloWorldPhase(
         event.getEntity().field_70170_p,
         SRPAttributes.EVENTUSEITEM,
         SRPConfigSystems.chanceEventUsteItem,
         SRPConfigSystems.disloCOTHSpy,
         event.getEntityLiving().func_180425_c()
      );
   }

   @SubscribeEvent
   public void eventDislo(Close event) {
      ParasiteEventWorld.setDisloWorldPhase(
         event.getEntity().field_70170_p,
         SRPAttributes.EVENTMENUCLOSE,
         SRPConfigSystems.chanceEventMEnuClose,
         SRPConfigSystems.disloCOTHSpy,
         event.getEntityPlayer().func_180425_c()
      );
   }
}
