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

public final class RupterKillMilestoneAdvancement {
   private static final ResourceLocation ADV_ID = new ResourceLocation("srparasites", "cut_roots");
   private static final String CRITERION = "reached_1000_rupter_kills";
   private static final int TARGET = 1000;

   public static void register() {
      MinecraftForge.EVENT_BUS.register(new RupterKillMilestoneAdvancement());
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
                  if ("rupter".equals(id.func_110623_a())) {
                     EntityPlayerMP killer = (EntityPlayerMP)src;
                     NBTTagCompound entityData = killer.getEntityData();
                     NBTTagCompound persisted = entityData.func_74775_l("PlayerPersisted");
                     int count = persisted.func_74762_e("srpRupterKills");
                     persisted.func_74768_a("srpRupterKills", ++count);
                     entityData.func_74782_a("PlayerPersisted", persisted);
                     if (count >= 1000) {
                        Advancement adv = killer.func_184102_h().func_191949_aK().func_192778_a(ADV_ID);
                        if (adv != null) {
                           killer.func_192039_O().func_192750_a(adv, "reached_1000_rupter_kills");
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
