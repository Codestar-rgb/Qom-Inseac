package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityHeblu;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public class HebluDebugAttackHandler {
   private static final ResourceLocation DEBUG_ITEM_ID = new ResourceLocation("srparasites", "itemmobspawner_heblu");
   public static boolean DEBUG_FORCE_ENABLE_HEBLU_ITEM = false;
   public static boolean DEBUG_ALLOW_CREATIVE_PLAYER_TARGET = false;
   public static boolean DEBUG_TARGET_PLAYER_IF_NO_TARGET = false;

   @SubscribeEvent
   public static void onRightClickItem(RightClickItem event) {
      tryTrigger(event.getEntityPlayer(), event.getHand(), event);
   }

   @SubscribeEvent
   public static void onRightClickBlock(RightClickBlock event) {
      tryTrigger(event.getEntityPlayer(), event.getHand(), event);
   }

   private static void tryTrigger(EntityPlayer player, EnumHand hand, PlayerInteractEvent event) {
      if (SRPConfigMobs.hebluDebugSpecialAttack || DEBUG_FORCE_ENABLE_HEBLU_ITEM) {
         if (player != null && !player.field_70170_p.field_72995_K) {
            if (hand == EnumHand.MAIN_HAND) {
               ItemStack stack = player.func_184586_b(hand);
               if (!stack.func_190926_b()) {
                  ResourceLocation heldId = stack.func_77973_b().getRegistryName();
                  if (heldId != null && DEBUG_ITEM_ID.equals(heldId)) {
                     EntityHeblu closestHeblu = findClosestHeblu(player);
                     if (closestHeblu != null) {
                        EntityLivingBase target = findClosestValidTarget(closestHeblu, player);
                        if (target == null && DEBUG_TARGET_PLAYER_IF_NO_TARGET) {
                           target = player;
                        }

                        if (!closestHeblu.getFlyingState()) {
                           closestHeblu.changeStateTo(true);
                        }

                        if (target != null) {
                           closestHeblu.func_70624_b(target);
                        }

                        closestHeblu.spawnLightBarrage(target, true);
                        closestHeblu.resetIdleTime();
                        event.setCanceled(true);
                        event.setCancellationResult(EnumActionResult.SUCCESS);
                     }
                  }
               }
            }
         }
      }
   }

   private static EntityHeblu findClosestHeblu(EntityPlayer player) {
      AxisAlignedBB box = player.func_174813_aQ().func_72314_b(48.0, 32.0, 48.0);
      List<EntityHeblu> list = player.field_70170_p.func_72872_a(EntityHeblu.class, box);
      EntityHeblu closest = null;
      double closestDist = Double.MAX_VALUE;

      for (EntityHeblu heblu : list) {
         if (heblu != null && !heblu.field_70128_L) {
            double dist = heblu.func_70068_e(player);
            if (dist < closestDist) {
               closestDist = dist;
               closest = heblu;
            }
         }
      }

      return closest;
   }

   private static EntityLivingBase findClosestValidTarget(EntityHeblu heblu, EntityPlayer playerUsingItem) {
      AxisAlignedBB box = heblu.func_174813_aQ().func_72314_b(48.0, 32.0, 48.0);
      List<EntityLivingBase> list = heblu.field_70170_p.func_72872_a(EntityLivingBase.class, box);
      EntityLivingBase closest = null;
      double closestDist = Double.MAX_VALUE;

      for (EntityLivingBase living : list) {
         if (living != null && !living.field_70128_L && living != heblu && !isSRParasitesMob(living)) {
            if (living instanceof EntityPlayer) {
               EntityPlayer targetPlayer = (EntityPlayer)living;
               if (targetPlayer.func_175149_v() || targetPlayer.func_184812_l_() && !DEBUG_ALLOW_CREATIVE_PLAYER_TARGET) {
                  continue;
               }
            }

            if (heblu.func_70685_l(living)) {
               double dist = living.func_70068_e(heblu);
               if (dist < closestDist) {
                  closestDist = dist;
                  closest = living;
               }
            }
         }
      }

      return closest;
   }

   private static boolean isSRParasitesMob(EntityLivingBase living) {
      String className = living.getClass().getName();
      return className.startsWith("com.dhanantry.scapeandrunparasites.entity.monster.");
   }
}
