package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.blocks.BlockBestiaryEntry;
import com.dhanantry.scapeandrunparasites.bestiary.blocks.SRPBlockCompendiumRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.effects.SRPStatusEffectRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiarySync;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandSRPGuide extends CommandBase {
   private static final ConcurrentHashMap<UUID, NBTTagCompound> SNAPSHOTS = new ConcurrentHashMap<>();

   public String func_71517_b() {
      return "srpguide";
   }

   private static void sync(EntityPlayerMP p, IBestiaryProgress prog) {
      BestiaryNetwork.CH.sendTo(new PacketBestiarySync(prog), p);
   }

   public String func_71518_a(ICommandSender sender) {
      return this.getUsageKey();
   }

   private String getUsageKey() {
      return "command.srpguide.usage";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (!(sender instanceof EntityPlayerMP)) {
         sender.func_145747_a(new TextComponentTranslation("command.srpguide.players_only", new Object[0]));
      } else {
         EntityPlayerMP p = (EntityPlayerMP)sender;
         if (args.length == 0) {
            sender.func_145747_a(new TextComponentTranslation(this.getUsageKey(), new Object[0]));
         } else {
            String sub = args[0].toLowerCase(Locale.ROOT);
            switch (sub) {
               case "unlockall":
                  IBestiaryProgress progx = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (progx == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  SNAPSHOTS.put(p.func_110124_au(), progx.serializeNBT());
                  int mobCount = 0;
                  int blockCount = 0;
                  int celestialCount = 0;
                  int effectCount = 0;

                  for (ParasiteTier t : ParasiteTier.values()) {
                     progx.markTierSeen(t);
                  }

                  for (Object be : SRPBestiaryRegistry.all()) {
                     try {
                        Field fMobId = be.getClass().getField("mobId");
                        Field fTier = be.getClass().getField("tier");
                        Object idObj = fMobId.get(be);
                        if (idObj instanceof String) {
                           String mobId = (String)idObj;
                           progx.markMobSeen(mobId);
                           int have = progx.getKills(mobId);
                           if (have < 999) {
                              progx.addKill(mobId, 999 - have);
                           }

                           Object tierObj = fTier.get(be);
                           if (tierObj instanceof ParasiteTier) {
                              progx.markTierSeen((ParasiteTier)tierObj);
                           }

                           mobCount++;
                        }
                     } catch (Throwable var21) {
                     }
                  }

                  for (BlockBestiaryEntry entryx : SRPBlockCompendiumRegistry.all()) {
                     if (!progx.hasSeenBlock(entryx.id)) {
                        progx.markBlockSeen(entryx.id);
                        blockCount++;
                     }
                  }

                  for (CelestialObjectDefinition defx : CelestialObjectRegistry.getObjects()) {
                     if (!progx.hasSeenCelestial(defx.id)) {
                        progx.markCelestialSeen(defx.id);
                        celestialCount++;
                     }
                  }

                  for (SRPStatusEffectRegistry.Entry ex : SRPStatusEffectRegistry.all()) {
                     if (ex != null && ex.id != null) {
                        try {
                           if (!progx.hasSeenEffect(ex.id)) {
                              progx.markEffectSeen(ex.id);
                              effectCount++;
                           }
                        } catch (Throwable var28) {
                           try {
                              Method has = progx.getClass().getMethod("hasSeenEffect", String.class);
                              Method mark = progx.getClass().getMethod("markEffectSeen", String.class);
                              Object ok = has.invoke(progx, ex.id);
                              if (!(ok instanceof Boolean) || !(Boolean)ok) {
                                 mark.invoke(progx, ex.id);
                                 effectCount++;
                              }
                           } catch (Throwable var27) {
                           }
                        }
                     }
                  }

                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockall_ok", new Object[]{mobCount}));
                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockblocks_ok", new Object[]{blockCount}));
                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockcelestial_ok", new Object[]{celestialCount}));
                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockeffects_ok", new Object[]{effectCount}));
                  sync(p, progx);
                  break;
               case "unlockblocks":
                  IBestiaryProgress progx = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (progx == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  int blockCount = 0;

                  for (BlockBestiaryEntry entry : SRPBlockCompendiumRegistry.all()) {
                     if (!progx.hasSeenBlock(entry.id)) {
                        progx.markBlockSeen(entry.id);
                        blockCount++;
                     }
                  }

                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockblocks_ok", new Object[]{blockCount}));
                  sync(p, progx);
                  break;
               case "unlockcelestial":
                  IBestiaryProgress progx = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (progx == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  int count = 0;

                  for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
                     if (!progx.hasSeenCelestial(def.id)) {
                        progx.markCelestialSeen(def.id);
                        count++;
                     }
                  }

                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockcelestial_ok", new Object[]{count}));
                  sync(p, progx);
                  break;
               case "unlockeffects":
                  IBestiaryProgress progx = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (progx == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  int count = 0;

                  for (SRPStatusEffectRegistry.Entry e : SRPStatusEffectRegistry.all()) {
                     if (e != null && e.id != null) {
                        try {
                           if (!progx.hasSeenEffect(e.id)) {
                              progx.markEffectSeen(e.id);
                              count++;
                           }
                        } catch (Throwable var26) {
                           try {
                              Method has = progx.getClass().getMethod("hasSeenEffect", String.class);
                              Method mark = progx.getClass().getMethod("markEffectSeen", String.class);
                              Object ok = has.invoke(progx, e.id);
                              if (!(ok instanceof Boolean) || !(Boolean)ok) {
                                 mark.invoke(progx, e.id);
                                 count++;
                              }
                           } catch (Throwable var25) {
                           }
                        }
                     }
                  }

                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.unlockeffects_ok", new Object[]{count}));
                  sync(p, progx);
                  break;
               case "clear":
                  IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (prog == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  NBTTagCompound snap = SNAPSHOTS.get(p.func_110124_au());
                  if (snap != null) {
                     prog.deserializeNBT(snap.func_74737_b());
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.clear_restore_ok", new Object[0]));
                  } else {
                     prog.deserializeNBT(new NBTTagCompound());
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.clear_no_snapshot", new Object[0]));
                  }

                  sync(p, prog);
                  break;
               case "clearall":
               case "reset":
                  IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (prog == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  prog.deserializeNBT(new NBTTagCompound());
                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.clearall_ok", new Object[0]));
                  sync(p, prog);
                  break;
               case "clearblocks":
                  IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (prog == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  prog.getSeenBlocks().clear();
                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.clearblocks_ok", new Object[0]));
                  sync(p, prog);
                  break;
               case "clearcelestial":
                  IBestiaryProgress progx = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (progx == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  try {
                     Method m = progx.getClass().getMethod("getSeenCelestials");
                     Object o = m.invoke(progx);
                     if (o instanceof Set) {
                        ((Set)o).clear();
                        sender.func_145747_a(new TextComponentTranslation("command.srpguide.clearcelestial_ok", new Object[0]));
                        sync(p, progx);
                        break;
                     }
                  } catch (Throwable var24) {
                  }

                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.clearcelestial_need_getter", new Object[0]));
                  break;
               case "cleareffects":
                  IBestiaryProgress progx = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                  if (progx == null) {
                     sender.func_145747_a(new TextComponentTranslation("command.srpguide.cap_missing", new Object[0]));
                     return;
                  }

                  try {
                     Method m = progx.getClass().getMethod("getSeenEffects");
                     Object o = m.invoke(progx);
                     if (o instanceof Set) {
                        ((Set)o).clear();
                        sender.func_145747_a(new TextComponentTranslation("command.srpguide.cleareffects_ok", new Object[0]));
                        sync(p, progx);
                        break;
                     }
                  } catch (Throwable var23) {
                  }

                  try {
                     NBTTagCompound tag = progx.serializeNBT();
                     if (tag.func_74764_b("seenEffects")) {
                        tag.func_82580_o("seenEffects");
                        progx.deserializeNBT(tag);
                        sender.func_145747_a(new TextComponentTranslation("command.srpguide.cleareffects_ok", new Object[0]));
                        sync(p, progx);
                        break;
                     }
                  } catch (Throwable var22) {
                  }

                  sender.func_145747_a(new TextComponentTranslation("command.srpguide.cleareffects_need_getter", new Object[0]));
                  break;
               default:
                  sender.func_145747_a(new TextComponentTranslation(this.getUsageKey(), new Object[0]));
            }
         }
      }
   }
}
