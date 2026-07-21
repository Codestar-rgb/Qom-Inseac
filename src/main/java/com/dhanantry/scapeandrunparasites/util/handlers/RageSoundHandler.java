package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public final class RageSoundHandler {
   private static final String RAGE_SOUND_TIMER = "SRPRageSoundTimer";
   private static final int RAGE_SOUND_MIN_INTERVAL = 60;
   private static final int RAGE_SOUND_MAX_INTERVAL = 120;
   private static final float RAGE_SOUND_VOLUME = 0.3F;
   private static final float RAGE_SOUND_PITCH = 1.0F;

   private RageSoundHandler() {
   }

   @SubscribeEvent
   public static void onLivingUpdate(LivingUpdateEvent event) {
      EntityLivingBase entity = event.getEntityLiving();
      if (entity != null && entity.field_70170_p != null && !entity.field_70170_p.field_72995_K) {
         if (!entity.field_70128_L && !(entity.func_110143_aJ() <= 0.0F)) {
            if (!isBlacklisted(entity)) {
               PotionEffect rage = entity.func_70660_b(SRPPotions.RAGE_E);
               if (rage == null) {
                  entity.getEntityData().func_82580_o("SRPRageSoundTimer");
               } else {
                  NBTTagCompound data = entity.getEntityData();
                  int timer = data.func_74762_e("SRPRageSoundTimer");
                  if (timer > 0) {
                     data.func_74768_a("SRPRageSoundTimer", timer - 1);
                  } else {
                     entity.field_70170_p
                        .func_184148_a(
                           null,
                           entity.field_70165_t,
                           entity.field_70163_u,
                           entity.field_70161_v,
                           SRPSounds.RAGE,
                           SoundCategory.HOSTILE,
                           0.3F,
                           1.0F + (entity.func_70681_au().nextFloat() - entity.func_70681_au().nextFloat()) * 0.1F
                        );
                     data.func_74768_a("SRPRageSoundTimer", getRandomInterval(entity));
                  }
               }
            }
         }
      }
   }

   private static int getRandomInterval(EntityLivingBase entity) {
      int min = Math.max(1, 60);
      int max = Math.max(min, 120);
      return min + entity.func_70681_au().nextInt(max - min + 1);
   }

   private static boolean isBlacklisted(EntityLivingBase entity) {
      if (entity instanceof EntityPlayer) {
         return true;
      } else if (entity instanceof EntityArmorStand) {
         return true;
      } else {
         ResourceLocation id = EntityList.func_191301_a(entity);
         if (id == null) {
            return true;
         } else {
            String name = id.toString();
            return name.equals("minecraft:player")
               || name.equals("minecraft:armor_stand")
               || name.equals("srparasites:buglin")
               || name.equals("srparasites:biomass")
               || name.equals("srparasites:beckon_si")
               || name.equals("srparasites:beckon_sii")
               || name.equals("srparasites:beckon_siii")
               || name.equals("srparasites:beckon_siv")
               || name.equals("srparasites:rooter_si")
               || name.equals("srparasites:rooter_sii")
               || name.equals("srparasites:rooter_siii")
               || name.equals("srparasites:rooter_siv")
               || name.equals("srparasites:dispatcher_si")
               || name.equals("srparasites:dispatcher_sii")
               || name.equals("srparasites:dispatcher_siii")
               || name.equals("srparasites:dispatcher_siv")
               || name.equals("srparasites:movingflesh")
               || name.equals("srparasites:worker")
               || name.equals("srparasites:dispatcherten")
               || name.equals("srparasites:rooterball")
               || name.equals("srparasites:incompleteform_small")
               || name.equals("srparasites:incompleteform_medium")
               || name.equals("srparasites:crux_incomplete")
               || name.equals("srparasites:carrier_worm")
               || name.equals("srparasites:succor")
               || name.equals("srparasites:anc_pod");
         }
      }
   }
}
