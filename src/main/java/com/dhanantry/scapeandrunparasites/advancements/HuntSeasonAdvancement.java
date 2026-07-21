package com.dhanantry.scapeandrunparasites.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class HuntSeasonAdvancement {
   private static final ResourceLocation ADV_ID = new ResourceLocation("srparasites", "hunt_season");
   private static final String CRITERION = "fifty_in_day";
   private static final int TARGET = 50;
   private static final long WINDOW_TICKS = 24000L;

   public static void register() {
      MinecraftForge.EVENT_BUS.register(new HuntSeasonAdvancement());
   }

   @SubscribeEvent
   public void onLivingDeath(LivingDeathEvent e) {
      if (!e.getEntityLiving().field_70170_p.field_72995_K) {
         Entity src = e.getSource().func_76346_g();
         if (src instanceof EntityPlayerMP) {
            EntityLivingBase victim = e.getEntityLiving();
            ResourceLocation id = EntityList.func_191301_a(victim);
            if (id != null) {
               if ("srparasites".equals(id.func_110624_b())) {
                  EntityPlayerMP killer = (EntityPlayerMP)src;
                  long now = victim.field_70170_p.func_82737_E();
                  NBTTagCompound entityData = killer.getEntityData();
                  NBTTagCompound persisted = entityData.func_74775_l("PlayerPersisted");
                  long start = persisted.func_74763_f("srpHuntStart");
                  int count = persisted.func_74762_e("srpHuntCount");
                  if (start != 0L && now - start <= 24000L) {
                     count++;
                  } else {
                     start = now;
                     count = 1;
                  }

                  persisted.func_74772_a("srpHuntStart", start);
                  persisted.func_74768_a("srpHuntCount", count);
                  entityData.func_74782_a("PlayerPersisted", persisted);
                  if (count >= 50) {
                     Advancement adv = killer.func_184102_h().func_191949_aK().func_192778_a(ADV_ID);
                     if (adv != null) {
                        killer.func_192039_O().func_192750_a(adv, "fifty_in_day");
                     }
                  }
               }
            }
         }
      }
   }
}
