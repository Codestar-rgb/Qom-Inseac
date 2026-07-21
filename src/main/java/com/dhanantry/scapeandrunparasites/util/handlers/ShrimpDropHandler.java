package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.world.CelestialNightData;
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
      if (event.getEntityLiving() instanceof EntityLivingBase) {
         if (event.getEntity().field_70170_p != null && !event.getEntity().field_70170_p.field_72995_K) {
            EntityLivingBase entity = event.getEntityLiving();
            if (this.isSRPMob(entity)) {
               if (this.isArrowCelestialActive(entity)) {
                  Random rand = entity.func_70681_au();
                  if (!(rand.nextFloat() >= 0.25F)) {
                     int count = 1 + rand.nextInt(5);
                     ItemStack stack = new ItemStack(SRPItems.shrimp, count);
                     BlockPos pos = entity.func_180425_c();
                     EntityItem drop = new EntityItem(
                        entity.field_70170_p, pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5, stack
                     );
                     event.getDrops().add(drop);
                  }
               }
            }
         }
      }
   }

   private boolean isSRPMob(EntityLivingBase entity) {
      return entity instanceof EntityParasiteBase;
   }

   private boolean isArrowCelestialActive(EntityLivingBase entity) {
      if (entity == null || entity.field_70170_p == null || entity.field_70170_p.field_72995_K) {
         return false;
      } else if (!entity.field_70170_p.field_73011_w.func_76569_d()) {
         return false;
      } else {
         int dim = entity.field_70170_p.field_73011_w.getDimension();
         CelestialNightData nightData = CelestialNightData.get(entity.field_70170_p);
         if (nightData == null) {
            return false;
         } else {
            CelestialNightData.DimState state = nightData.getOrCreate(dim);
            return state.active.contains("arrow") || state.forced.contains("arrow");
         }
      }
   }
}
