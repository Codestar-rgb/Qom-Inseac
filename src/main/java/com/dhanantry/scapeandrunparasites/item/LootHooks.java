package com.dhanantry.scapeandrunparasites.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public final class LootHooks {
   private static final List<LootHooks.DropSpec> SPECS = new ArrayList<>();

   private LootHooks() {
   }

   public static void register(Item item, Class<? extends EntityLivingBase> mobClass, float chance, int min, int max) {
      SPECS.add(new LootHooks.DropSpec(item, mobClass, null, LootHooks.Mode.CHANCE, clamp(chance), Math.max(1, min), Math.max(min, max)));
   }

   public static void registerById(Item item, ResourceLocation mobId, float chance, int min, int max) {
      SPECS.add(new LootHooks.DropSpec(item, null, mobId, LootHooks.Mode.CHANCE, clamp(chance), Math.max(1, min), Math.max(min, max)));
   }

   public static void registerSkeletonRateById(Item item, ResourceLocation mobId) {
      SPECS.add(new LootHooks.DropSpec(item, null, mobId, LootHooks.Mode.SKELETON_RATE, 1.0F, 0, 0));
   }

   private static float clamp(float v) {
      return v < 0.0F ? 0.0F : (v > 1.0F ? 1.0F : v);
   }

   @SubscribeEvent
   public static void onLivingDrops(LivingDropsEvent e) {
      EntityLivingBase mob = e.getEntityLiving();
      World w = mob.field_70170_p;
      if (!w.field_72995_K) {
         Random r = w.field_73012_v;
         int looting = e.getLootingLevel();
         ResourceLocation thisId = EntityList.func_191301_a(mob);

         for (LootHooks.DropSpec spec : SPECS) {
            boolean match = false;
            if (spec.mobClass != null && spec.mobClass.isInstance(mob)) {
               match = true;
            } else if (spec.mobId != null && spec.mobId.equals(thisId)) {
               match = true;
            }

            if (match) {
               int count = 0;
               if (spec.mode == LootHooks.Mode.SKELETON_RATE) {
                  count = r.nextInt(3) + r.nextInt(1 + Math.max(0, looting));
               } else {
                  float chance = spec.chance + looting * 0.02F;
                  if (r.nextFloat() <= clamp(chance)) {
                     int min = spec.min;
                     int max = spec.max;
                     count = min + r.nextInt(max - min + 1);
                  }
               }

               if (count > 0) {
                  ItemStack stack = new ItemStack(spec.item, count);
                  e.getDrops().add(new EntityItem(w, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, stack));
               }
            }
         }
      }
   }

   private static class DropSpec {
      final Item item;
      final Class<? extends EntityLivingBase> mobClass;
      final ResourceLocation mobId;
      final LootHooks.Mode mode;
      final float chance;
      final int min;
      final int max;

      DropSpec(Item item, Class<? extends EntityLivingBase> mobClass, ResourceLocation mobId, LootHooks.Mode mode, float chance, int min, int max) {
         this.item = item;
         this.mobClass = mobClass;
         this.mobId = mobId;
         this.mode = mode;
         this.chance = chance;
         this.min = min;
         this.max = max;
      }
   }

   private static enum Mode {
      CHANCE,
      SKELETON_RATE;
   }
}
