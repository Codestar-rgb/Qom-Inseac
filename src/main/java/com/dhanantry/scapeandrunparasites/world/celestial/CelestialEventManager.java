package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Random;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

@EventBusSubscriber(modid = "srparasites")
public class CelestialEventManager {
   private static final Random RAND = new Random();
   public static final int DARK_DAYS_DURATION_TICKS = 6000;
   public static final int DARK_DAYS_END_WARNING_TICKS = 200;
   public static final int DARK_DAYS_INTRO_DELAY_TICKS = 160;
   public static final int DARK_DAYS_OUTRO_DELAY_TICKS = 200;
   public static final long DARK_DAYS_ROLL_TIME = 800L;
   public static final long DARK_DAYS_ACTIVATION_TIME = 1000L;

   public static boolean isActive(World world, String id) {
      if (world != null && !world.field_72995_K) {
         int dim = world.field_73011_w.getDimension();
         CelestialNightData.DimState s = CelestialNightData.get(world).getState(dim);
         return s == null ? false : s.forced.contains(id) || s.active.contains(id);
      } else {
         return false;
      }
   }

   public static void force(World world, String id, boolean enabled) {
      if (world != null && !world.field_72995_K) {
         int dim = world.field_73011_w.getDimension();
         CelestialNightData data = CelestialNightData.get(world);
         CelestialNightData.DimState s = data.getOrCreate(dim);
         if (enabled) {
            s.forced.add(id);
         } else {
            s.forced.remove(id);
            s.active.remove(id);
            if ("dark_days".equals(id)) {
               s.darkDaysStartTime = -1L;
               s.darkDaysEndTime = -1L;
               s.darkDaysEndingSoundPlayed = false;
            }
         }

         if (s.forced.contains("dark_days")) {
            s.active.clear();
            s.forced.clear();
            s.forced.add("dark_days");
         }

         data.func_76185_a();
         syncDim(world, dim);
      }
   }

   private static void clearWeatherForDarkDays(World world) {
      if (world != null) {
         world.func_72912_H().func_76084_b(false);
         world.func_72912_H().func_76069_a(false);
         world.func_72912_H().func_76080_g(0);
         world.func_72912_H().func_76090_f(0);
         world.func_72912_H().func_176142_i(6400);
      }
   }

   public static void clearForced(World world) {
      if (world != null && !world.field_72995_K) {
         int dim = world.field_73011_w.getDimension();
         CelestialNightData data = CelestialNightData.get(world);
         CelestialNightData.DimState s = data.getOrCreate(dim);
         s.forced.clear();
         s.active.remove("dark_days");
         s.darkDaysStartTime = -1L;
         s.darkDaysEndTime = -1L;
         s.darkDaysEndingSoundPlayed = false;
         data.func_76185_a();
         syncDim(world, dim);
      }
   }

   public static void startDarkDays(World world) {
      if (world != null && !world.field_72995_K) {
         if (world.field_73011_w.func_76569_d()) {
            clearWeatherForDarkDays(world);
            int dim = world.field_73011_w.getDimension();
            CelestialNightData data = CelestialNightData.get(world);
            CelestialNightData.DimState s = data.getOrCreate(dim);
            s.active.clear();
            s.forced.clear();
            s.darkDaysStartTime = world.func_72820_D() + 160L;
            s.darkDaysEndTime = -1L;
            s.darkDaysEndingSoundPlayed = false;
            data.func_76185_a();
            syncDim(world, dim);
            playToDimension(world, SRPSounds.DARK_DAYS_START, 1.0F);
         }
      }
   }

   public static void stopDarkDays(World world) {
      if (world != null && !world.field_72995_K) {
         if (world.field_73011_w.func_76569_d()) {
            int dim = world.field_73011_w.getDimension();
            CelestialNightData data = CelestialNightData.get(world);
            CelestialNightData.DimState s = data.getOrCreate(dim);
            boolean active = s.forced.contains("dark_days") || s.active.contains("dark_days");
            if (!active) {
               s.darkDaysStartTime = -1L;
               s.darkDaysEndTime = -1L;
               s.darkDaysEndingSoundPlayed = false;
               data.func_76185_a();
               syncDim(world, dim);
            } else {
               s.darkDaysStartTime = -1L;
               s.darkDaysEndTime = world.func_72820_D() + 200L;
               s.darkDaysEndingSoundPlayed = true;
               data.func_76185_a();
               syncDim(world, dim);
               playToDimension(world, SRPSounds.DARK_DAYS_ENDING, 1.0F);
            }
         }
      }
   }

   public static void syncDim(World world, int dim) {
      if (world != null && !world.field_72995_K) {
         CelestialNightData.DimState s = CelestialNightData.get(world).getState(dim);
         if (s != null) {
            PacketCelestialNightState pkt = new PacketCelestialNightState(dim, s.phase, s.nightIndex, s.active, s.forced);

            for (EntityPlayerMP p : world.func_73046_m().func_184103_al().func_181057_v()) {
               if (p.field_70170_p.field_73011_w.getDimension() == dim) {
                  SRPNetwork.CHANNEL.sendTo(pkt, p);
               }
            }
         }
      }
   }

   private static void rollIfNeeded(World world) {
      if (world != null && !world.field_72995_K) {
         if (world.field_73011_w.func_76569_d()) {
            int dim = world.field_73011_w.getDimension();
            long nightIndex = world.func_72820_D() / 24000L;
            CelestialNightData data = CelestialNightData.get(world);
            CelestialNightData.DimState s = data.getOrCreate(dim);
            if (!s.forced.contains("dark_days")) {
               if (s.nightIndex != nightIndex) {
                  s.nightIndex = nightIndex;
                  s.active.clear();
                  long baseSeed = world.func_72905_C() ^ nightIndex * 918273L;

                  for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
                     if (!"dark_days".equals(def.id) && !SRPConfigWorld.isCelestialEventBlacklisted(def.id) && def.isPhaseAllowed(s.phase)) {
                        long seed = baseSeed + def.id.hashCode() * 31L;
                        RAND.setSeed(seed);
                        if (RAND.nextFloat() <= def.chancePerNight) {
                           s.active.add(def.id);
                        }
                     }
                  }

                  if (s.active.contains("dark_days")) {
                     s.active.clear();
                     s.active.add("dark_days");
                  }

                  data.func_76185_a();
                  syncDim(world, dim);
               }
            }
         }
      }
   }

   private static void tickDarkDays(World world) {
      if (world != null && !world.field_72995_K) {
         if (world.field_73011_w.func_76569_d()) {
            int dim = world.field_73011_w.getDimension();
            CelestialNightData data = CelestialNightData.get(world);
            CelestialNightData.DimState s = data.getOrCreate(dim);
            long now = world.func_72820_D();
            if (s.darkDaysStartTime > 0L && now >= s.darkDaysStartTime) {
               s.darkDaysStartTime = -1L;
               s.active.clear();
               s.forced.clear();
               s.forced.add("dark_days");
               s.darkDaysEndTime = now + 6000L;
               s.darkDaysEndingSoundPlayed = false;
               data.func_76185_a();
               syncDim(world, dim);
            }

            boolean active = s.forced.contains("dark_days") || s.active.contains("dark_days");
            if (active) {
               if (s.darkDaysEndTime <= 0L) {
                  s.darkDaysEndTime = now + 6000L;
                  s.darkDaysEndingSoundPlayed = false;
                  data.func_76185_a();
                  syncDim(world, dim);
               } else {
                  if (!s.darkDaysEndingSoundPlayed && now >= s.darkDaysEndTime - 200L) {
                     s.darkDaysEndingSoundPlayed = true;
                     data.func_76185_a();
                     playToDimension(world, SRPSounds.DARK_DAYS_ENDING, 1.0F);
                  }

                  if (now >= s.darkDaysEndTime) {
                     grantDarkDaysAdvancement(world);
                     s.forced.remove("dark_days");
                     s.active.remove("dark_days");
                     s.darkDaysStartTime = -1L;
                     s.darkDaysEndTime = -1L;
                     s.darkDaysEndingSoundPlayed = false;
                     data.func_76185_a();
                     syncDim(world, dim);
                  }
               }
            }
         }
      }
   }

   private static void playToDimension(World world, SoundEvent sound, float pitch) {
      if (world != null && sound != null && world.func_73046_m() != null) {
         int dim = world.field_73011_w.getDimension();

         for (EntityPlayerMP p : world.func_73046_m().func_184103_al().func_181057_v()) {
            if (p.field_70170_p.field_73011_w.getDimension() == dim) {
               p.field_70170_p.func_184148_a(null, p.field_70165_t, p.field_70163_u, p.field_70161_v, sound, SoundCategory.AMBIENT, 10000.0F, pitch);
            }
         }
      }
   }

   private static void rollDarkDaysIfNeeded(World world) {
      if (world != null && !world.field_72995_K) {
         if (world.field_73011_w.func_76569_d()) {
            int dim = world.field_73011_w.getDimension();
            CelestialNightData data = CelestialNightData.get(world);
            CelestialNightData.DimState s = data.getOrCreate(dim);
            boolean activeOrQueued = s.forced.contains("dark_days") || s.active.contains("dark_days") || s.darkDaysStartTime > 0L || s.darkDaysEndTime > 0L;
            if (!activeOrQueued) {
               long worldTime = world.func_72820_D();
               long dayStart = worldTime - worldTime % 24000L;
               long day = dayStart / 24000L;
               long dayTime = worldTime % 24000L;
               if (s.darkDaysLastRollDay != day) {
                  if (dayTime >= 800L && dayTime < 1000L) {
                     s.darkDaysLastRollDay = day;
                     CelestialObjectDefinition def = CelestialObjectRegistry.getById("dark_days");
                     if (def == null) {
                        data.func_76185_a();
                     } else {
                        long seed = world.func_72905_C() ^ day * 918273L ^ "dark_days".hashCode() * 31L;
                        RAND.setSeed(seed);
                        if (RAND.nextFloat() <= def.chancePerNight) {
                           s.active.clear();
                           s.forced.clear();
                           clearWeatherForDarkDays(world);
                           s.darkDaysStartTime = dayStart + 1000L;
                           s.darkDaysEndTime = -1L;
                           s.darkDaysEndingSoundPlayed = false;
                           playToDimension(world, SRPSounds.DARK_DAYS_START, 1.0F);
                        }

                        data.func_76185_a();
                        syncDim(world, dim);
                     }
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public static void onWorldTick(WorldTickEvent e) {
      if (e.phase == Phase.END) {
         World w = e.world;
         if (w != null && !w.field_72995_K) {
            rollIfNeeded(w);
            rollDarkDaysIfNeeded(w);
            tickDarkDays(w);
         }
      }
   }

   private static void grantDarkDaysAdvancement(World world) {
      if (world != null && world.func_73046_m() != null) {
         Advancement adv = world.func_73046_m().func_191949_aK().func_192778_a(new ResourceLocation("srparasites", "dark_days"));
         if (adv != null) {
            int dim = world.field_73011_w.getDimension();

            for (EntityPlayerMP p : world.func_73046_m().func_184103_al().func_181057_v()) {
               if (p.field_70170_p.field_73011_w.getDimension() == dim) {
                  AdvancementProgress progress = p.func_192039_O().func_192747_a(adv);
                  if (!progress.func_192105_a()) {
                     for (String criterion : progress.func_192107_d()) {
                        p.func_192039_O().func_192750_a(adv, criterion);
                     }
                  }
               }
            }
         }
      }
   }
}
