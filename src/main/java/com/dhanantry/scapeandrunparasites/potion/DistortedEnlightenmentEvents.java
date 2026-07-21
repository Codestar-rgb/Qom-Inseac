package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public final class DistortedEnlightenmentEvents {
   private DistortedEnlightenmentEvents() {
   }

   @SubscribeEvent
   public static void onLivingHurt(LivingHurtEvent event) {
      EntityLivingBase victim = event.getEntityLiving();
      if (victim != null && !victim.field_70170_p.field_72995_K) {
         Entity trueSource = event.getSource().func_76346_g();
         if (trueSource instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase)trueSource;
            boolean victimHasEffect = victim.func_70644_a(SRPPotions.DISTORTED_ENLIGHTENMENT_E);
            boolean attackerHasEffect = attacker.func_70644_a(SRPPotions.DISTORTED_ENLIGHTENMENT_E);
            float amount = event.getAmount();
            if (victimHasEffect && isSRPParasite(attacker)) {
               if (isDraconite(attacker)) {
                  amount *= 5.0F;
               } else {
                  amount *= 0.8F;
               }
            }

            if (attackerHasEffect && isSRPParasite(victim)) {
               amount *= 0.8F;
            }

            event.setAmount(amount);
         }
      }
   }

   private static boolean isSRPParasite(Entity entity) {
      if (entity == null) {
         return false;
      } else {
         ResourceLocation id = EntityList.func_191301_a(entity);
         if (id != null && "srparasites".equals(id.func_110624_b())) {
            return true;
         } else {
            String className = entity.getClass().getName();
            return className.startsWith("com.dhanantry.scapeandrunparasites.entity.monster.");
         }
      }
   }

   private static boolean isDraconite(Entity entity) {
      if (entity == null) {
         return false;
      } else {
         ResourceLocation id = EntityList.func_191301_a(entity);
         if (id != null && "srparasites".equals(id.func_110624_b())) {
            String path = id.func_110623_a();
            return path != null && path.toLowerCase().contains("draconite");
         } else {
            String className = entity.getClass().getName().toLowerCase();
            return className.contains("draconite");
         }
      }
   }
}
