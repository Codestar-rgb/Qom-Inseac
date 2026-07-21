package com.dhanantry.scapeandrunparasites.bestiary;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BestiaryCombatStatsHandler {
   @SubscribeEvent
   public void onLivingDamage(LivingDamageEvent event) {
      if (event != null && !event.isCanceled()) {
         EntityLivingBase target = event.getEntityLiving();
         if (target != null) {
            float amount = event.getAmount();
            if (!(amount <= 0.0F)) {
               Entity trueAttacker = getTrueAttacker(event.getSource());
               if (isParasite(target) && trueAttacker instanceof EntityPlayer) {
                  EntityPlayer player = (EntityPlayer)trueAttacker;
                  IBestiaryProgress prog = (IBestiaryProgress)player.getCapability(BestiaryCapability.CAP, null);
                  if (prog != null) {
                     prog.addDamageToParasites(amount);
                  }
               } else {
                  if (target instanceof EntityPlayer && isParasite(trueAttacker)) {
                     EntityPlayer player = (EntityPlayer)target;
                     IBestiaryProgress prog = (IBestiaryProgress)player.getCapability(BestiaryCapability.CAP, null);
                     if (prog != null) {
                        prog.addDamageFromParasites(amount);
                     }
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onLivingDeath(LivingDeathEvent event) {
      if (event != null && !event.isCanceled()) {
         EntityLivingBase dead = event.getEntityLiving();
         if (dead instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)dead;
            Entity trueAttacker = getTrueAttacker(event.getSource());
            if (isParasite(trueAttacker)) {
               IBestiaryProgress prog = (IBestiaryProgress)player.getCapability(BestiaryCapability.CAP, null);
               if (prog != null) {
                  prog.addDeathsByParasites(1);
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerClone(Clone event) {
      if (event != null) {
         if (event.getOriginal() != null && event.getEntityPlayer() != null) {
            IBestiaryProgress oldProg = (IBestiaryProgress)event.getOriginal().getCapability(BestiaryCapability.CAP, null);
            IBestiaryProgress newProg = (IBestiaryProgress)event.getEntityPlayer().getCapability(BestiaryCapability.CAP, null);
            if (oldProg != null && newProg != null) {
               newProg.copyCombatStatsFrom(oldProg);
            }
         }
      }
   }

   private static Entity getTrueAttacker(DamageSource source) {
      if (source == null) {
         return null;
      } else {
         Entity trueSource = source.func_76346_g();
         if (trueSource != null) {
            return trueSource;
         } else {
            Entity immediate = source.func_76364_f();
            if (immediate instanceof EntityArrow) {
               Entity shooter = ((EntityArrow)immediate).field_70250_c;
               if (shooter != null) {
                  return shooter;
               }
            }

            return immediate instanceof IProjectile ? immediate : immediate;
         }
      }
   }

   private static boolean isParasite(Entity entity) {
      if (entity == null) {
         return false;
      } else if (entity instanceof EntityParasiteBase) {
         return true;
      } else {
         ResourceLocation key = BestiaryCombatStatsHandler.EntityListSafe.getKey(entity);
         return key != null && "srparasites".equals(key.func_110624_b());
      }
   }

   private static final class EntityListSafe {
      private static ResourceLocation getKey(Entity entity) {
         try {
            return EntityList.func_191301_a(entity);
         } catch (Throwable var2) {
            return null;
         }
      }
   }
}
