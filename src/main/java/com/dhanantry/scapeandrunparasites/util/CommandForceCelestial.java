package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialPhaseClient;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialEventManager;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialNightData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandForceCelestial extends CommandBase {
   public String func_71517_b() {
      return "srp_celestial";
   }

   public String func_71518_a(ICommandSender sender) {
      return "/srp_celestial <list|debug|clear|all|dark_days|dark_days_end|id>";
   }

   public int func_82362_a() {
      return 2;
   }

   private static long getNightIndex(World world) {
      return world.func_72820_D() / 24000L;
   }

   private static long getNextTimeOfDay(World world, long targetDayTime) {
      long current = world.func_72820_D();
      long dayStart = current - current % 24000L;
      long target = dayStart + targetDayTime;
      if (target <= current) {
         target += 24000L;
      }

      return target;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (!SRPConfigWorld.enableCelestialObjects) {
         sender.func_145747_a(new TextComponentString("SRP celestial objects are disabled in the config (enableCelestialObjects = false)."));
      } else if (args.length < 1) {
         throw new WrongUsageException("Usage: /srp_celestial <list|debug|clear|all|dark_days|dark_days_end|id>", new Object[0]);
      } else {
         String sub = args[0];
         World world = sender.func_130014_f_();
         int dim = world.field_73011_w.getDimension();
         if ("list".equalsIgnoreCase(sub)) {
            List<String> ids = CelestialObjectRegistry.getAllIds();
            if (ids.isEmpty()) {
               sender.func_145747_a(new TextComponentString("No celestial events are registered."));
            } else {
               sender.func_145747_a(new TextComponentString("Celestial events: " + String.join(", ", ids)));
            }

            sender.func_145747_a(new TextComponentString("Special commands: dark_days, dark_days_end"));
         } else if ("debug".equalsIgnoreCase(sub)) {
            if (!world.field_72995_K) {
               SRPSaveData save = SRPSaveData.get(world, dim);
               int phase = save == null ? -1 : save.getEvolutionPhase(dim);
               CelestialNightData nightData = CelestialNightData.get(world);
               CelestialNightData.DimState state = nightData.getOrCreate(dim);
               sender.func_145747_a(
                  new TextComponentString(
                     "[SERVER] dim="
                        + dim
                        + " phase="
                        + phase
                        + " day="
                        + getNightIndex(world)
                        + " dayTime="
                        + world.func_72820_D() % 24000L
                        + " active="
                        + state.active.size()
                        + " forced="
                        + state.forced.size()
                        + " darkDaysEndTime="
                        + state.darkDaysEndTime
                        + " darkDaysEndingSoundPlayed="
                        + state.darkDaysEndingSoundPlayed
                  )
               );
               sender.func_145747_a(new TextComponentString("[SERVER] active=" + String.join(", ", state.active)));
               sender.func_145747_a(new TextComponentString("[SERVER] forced=" + String.join(", ", state.forced)));
            } else {
               int phase = CelestialPhaseClient.getPhase(dim);
               long night = CelestialPhaseClient.getNightIndex(dim);
               Set<String> a = CelestialPhaseClient.getActiveIds(dim);
               Set<String> f = CelestialPhaseClient.getForcedIds(dim);
               sender.func_145747_a(
                  new TextComponentString("[CLIENT] dim=" + dim + " phase=" + phase + " night=" + night + " active=" + a.size() + " forced=" + f.size())
               );
               sender.func_145747_a(new TextComponentString("[CLIENT] active=" + String.join(", ", a)));
               sender.func_145747_a(new TextComponentString("[CLIENT] forced=" + String.join(", ", f)));
            }
         } else if (world.field_72995_K) {
            sender.func_145747_a(new TextComponentString("This command must be run server-side."));
         } else {
            SRPSaveData save = SRPSaveData.get(world, dim);
            if (save == null) {
               throw new CommandException("SRPSaveData is null for dim " + dim, new Object[0]);
            } else {
               int phase = save.getEvolutionPhase(dim);
               CelestialNightData nightData = CelestialNightData.get(world);
               CelestialNightData.DimState state = nightData.getOrCreate(dim);
               long nightIndex = getNightIndex(world);
               if ("clear".equalsIgnoreCase(sub) || "none".equalsIgnoreCase(sub) || "off".equalsIgnoreCase(sub)) {
                  state.forced.clear();
                  state.active.remove("dark_days");
                  state.darkDaysStartTime = -1L;
                  state.darkDaysEndTime = -1L;
                  state.darkDaysEndingSoundPlayed = false;
                  nightData.func_76185_a();
                  CelestialEventManager.clearForced(world);
                  CelestialEventManager.syncDim(world, dim);
                  sender.func_145747_a(new TextComponentString("Cleared all forced celestial events in dimension " + dim));
               } else if ("dark_days".equalsIgnoreCase(sub)) {
                  if (!SRPConfigWorld.darkDaysEnabled) {
                     sender.func_145747_a(new TextComponentString("Dark Days is disabled in the config."));
                  } else {
                     world.func_72877_b(getNextTimeOfDay(world, 1000L));
                     state.active.clear();
                     state.forced.clear();
                     state.darkDaysStartTime = world.func_72820_D() + 160L;
                     state.darkDaysEndTime = -1L;
                     state.darkDaysEndingSoundPlayed = false;
                     nightData.func_76185_a();
                     CelestialEventManager.startDarkDays(world);
                     sender.func_145747_a(new TextComponentString("Queued Celestial Displacement. CD DD will activate in 8 seconds in dimension " + dim));
                  }
               } else if ("dark_days_end".equalsIgnoreCase(sub)) {
                  boolean wasActive = state.forced.contains("dark_days") || state.active.contains("dark_days");
                  if (!wasActive) {
                     sender.func_145747_a(new TextComponentString("Celestial Displacement is not active in dimension " + dim));
                  } else {
                     state.darkDaysStartTime = -1L;
                     state.darkDaysEndTime = world.func_72820_D() + 200L;
                     state.darkDaysEndingSoundPlayed = true;
                     nightData.func_76185_a();
                     CelestialEventManager.stopDarkDays(world);
                     sender.func_145747_a(new TextComponentString("Queued Celestial Realignment. Displacement will end in 10 seconds in dimension " + dim));
                  }
               } else if ("all".equalsIgnoreCase(sub)) {
                  List<String> ids = CelestialObjectRegistry.getAllIds();
                  if (ids.isEmpty()) {
                     sender.func_145747_a(new TextComponentString("No celestial events are registered."));
                  } else {
                     List<String> toForce = new ArrayList<>();

                     for (String eventId : ids) {
                        if (!"dark_days".equals(eventId) && !SRPConfigWorld.isCelestialEventBlacklisted(eventId)) {
                           toForce.add(eventId);
                        }
                     }

                     state.active.remove("dark_days");
                     state.darkDaysStartTime = -1L;
                     state.darkDaysEndTime = -1L;
                     state.darkDaysEndingSoundPlayed = false;
                     CelestialEventManager.clearForced(world);
                     state.forced.clear();

                     for (String eventIdx : toForce) {
                        state.forced.add(eventIdx);
                        CelestialEventManager.force(world, eventIdx, true);
                     }

                     nightData.func_76185_a();
                     CelestialEventManager.syncDim(world, dim);
                     sender.func_145747_a(
                        new TextComponentString(
                           "Forced " + toForce.size() + " compatible celestial events in dimension " + dim + ". Celestial Displacement was skipped."
                        )
                     );
                  }
               } else if (!CelestialObjectRegistry.getAllIds().contains(sub)) {
                  throw new CommandException("Unknown celestial id: " + sub, new Object[0]);
               } else if (!SRPConfigWorld.isCelestialEventBlacklisted(sub) && (!"dark_days".equals(sub) || SRPConfigWorld.darkDaysEnabled)) {
                  boolean nowForced;
                  if (state.forced.contains(sub)) {
                     state.forced.remove(sub);
                     state.active.remove(sub);
                     nowForced = false;
                     CelestialEventManager.force(world, sub, false);
                  } else {
                     state.forced.add(sub);
                     nowForced = true;
                     if ("dark_days".equals(sub)) {
                        state.active.clear();
                        state.forced.clear();
                        state.forced.add("dark_days");
                        state.darkDaysEndTime = world.func_72820_D() + 6000L;
                        state.darkDaysEndingSoundPlayed = false;
                        CelestialEventManager.startDarkDays(world);
                     } else {
                        CelestialEventManager.force(world, sub, true);
                     }
                  }

                  if (!SRPConfigWorld.darkDaysEnabled) {
                     state.active.remove("dark_days");
                     state.forced.remove("dark_days");
                     state.darkDaysStartTime = -1L;
                     state.darkDaysEndTime = -1L;
                     state.darkDaysEndingSoundPlayed = false;
                  }

                  nightData.func_76185_a();
                  CelestialEventManager.syncDim(world, dim);
                  sender.func_145747_a(new TextComponentString((nowForced ? "Forced" : "Unforced") + " celestial event '" + sub + "' in dimension " + dim));
               } else {
                  sender.func_145747_a(new TextComponentString("Celestial event '" + sub + "' is disabled in the config."));
               }
            }
         }
      }
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         List<String> options = new ArrayList<>();
         options.add("list");
         options.add("debug");
         options.add("clear");
         options.add("none");
         options.add("off");
         options.add("all");
         options.add("dark_days");
         options.add("dark_days_end");
         options.addAll(CelestialObjectRegistry.getAllIds());
         return func_175762_a(args, options);
      } else {
         return Collections.emptyList();
      }
   }
}
