package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;

public class SRPCommandGuiDistortion implements ICommand {
   private final List<String> aliases = new ArrayList<>();

   public SRPCommandGuiDistortion() {
      this.aliases.add("srpguidist");
      this.aliases.add("srpdistortion");
   }

   public int compareTo(ICommand other) {
      return this.func_71517_b().compareTo(other.func_71517_b());
   }

   public String func_71517_b() {
      return "srpguidistortion";
   }

   public String func_71518_a(ICommandSender sender) {
      return "commands.srparasites.guidistortion.usage";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (args.length < 1) {
         sender.func_145747_a(
            new TextComponentTranslation(
               "commands.srparasites.guidistortion.usage.text", new Object[]{"/" + this.func_71517_b() + " <on|off|toggle|status> [player|all]"}
            )
         );
      } else {
         String mode = args[0].toLowerCase();
         if (!"on".equals(mode) && !"off".equals(mode) && !"toggle".equals(mode) && !"status".equals(mode)) {
            sender.func_145747_a(new TextComponentTranslation("commands.srparasites.guidistortion.invalid_argument", new Object[0]));
         } else {
            List<EntityPlayerMP> targets = this.getTargets(server, sender, args);
            if (!targets.isEmpty()) {
               for (EntityPlayerMP target : targets) {
                  this.handleTarget(sender, target, mode, targets.size() > 1);
               }

               if (targets.size() > 1 && !"status".equals(mode)) {
                  sender.func_145747_a(new TextComponentTranslation("commands.srparasites.guidistortion.set_all", new Object[]{targets.size()}));
               }
            }
         }
      }
   }

   private List<EntityPlayerMP> getTargets(MinecraftServer server, ICommandSender sender, String[] args) {
      if (args.length >= 2) {
         String targetName = args[1];
         if (!"all".equalsIgnoreCase(targetName) && !"@a".equalsIgnoreCase(targetName)) {
            EntityPlayerMP target = server.func_184103_al().func_152612_a(targetName);
            if (target == null) {
               sender.func_145747_a(new TextComponentTranslation("commands.srparasites.guidistortion.player_not_found", new Object[]{targetName}));
               return Collections.emptyList();
            } else {
               List<EntityPlayerMP> one = new ArrayList<>();
               one.add(target);
               return one;
            }
         } else {
            return new ArrayList<>(server.func_184103_al().func_181057_v());
         }
      } else if (sender.func_174793_f() instanceof EntityPlayerMP) {
         List<EntityPlayerMP> one = new ArrayList<>();
         one.add((EntityPlayerMP)sender.func_174793_f());
         return one;
      } else {
         sender.func_145747_a(new TextComponentTranslation("commands.srparasites.guidistortion.console_requires_player", new Object[0]));
         return Collections.emptyList();
      }
   }

   private void handleTarget(ICommandSender sender, EntityPlayerMP target, String mode, boolean multiTarget) {
      NBTTagCompound persisted = getPersistedData(target);
      boolean currentlyDisabled = persisted.func_74767_n("SRPGuiDistortionDisabled");
      boolean creativeOverride = persisted.func_74767_n("SRPGuiDistortionCreativeOverride");
      boolean creativeOrSpectator = isCreativeOrSpectator(target);
      boolean effectivelyDisabled = currentlyDisabled || creativeOrSpectator && !creativeOverride;
      if ("status".equals(mode)) {
         sender.func_145747_a(
            new TextComponentTranslation(
               "commands.srparasites.guidistortion.status.detailed",
               new Object[]{
                  target.func_70005_c_(),
                  currentlyDisabled
                     ? new TextComponentTranslation("commands.srparasites.guidistortion.state.disabled", new Object[0])
                     : new TextComponentTranslation("commands.srparasites.guidistortion.state.enabled", new Object[0]),
                  creativeOverride
                     ? new TextComponentTranslation("commands.srparasites.guidistortion.creative_override.enabled", new Object[0])
                     : new TextComponentTranslation("commands.srparasites.guidistortion.creative_override.disabled", new Object[0]),
                  creativeOrSpectator
                     ? new TextComponentTranslation("commands.srparasites.guidistortion.gamemode.creative_or_spectator", new Object[0])
                     : new TextComponentTranslation("commands.srparasites.guidistortion.gamemode.normal", new Object[0])
               }
            )
         );
      } else {
         boolean newCreativeOverride = creativeOverride;
         boolean newDisabled;
         if ("on".equals(mode)) {
            newDisabled = false;
            if (creativeOrSpectator) {
               newCreativeOverride = true;
            }
         } else if ("off".equals(mode)) {
            newDisabled = true;
            newCreativeOverride = false;
         } else if (effectivelyDisabled) {
            newDisabled = false;
            if (creativeOrSpectator) {
               newCreativeOverride = true;
            }
         } else {
            newDisabled = true;
            newCreativeOverride = false;
         }

         persisted.func_74757_a("SRPGuiDistortionDisabled", newDisabled);
         persisted.func_74757_a("SRPGuiDistortionCreativeOverride", newCreativeOverride);
         SRPMain.network.sendTo(new SRPPacketGuiDistortionState(newDisabled, newCreativeOverride), target);
         if (!multiTarget) {
            sender.func_145747_a(
               new TextComponentTranslation(
                  "commands.srparasites.guidistortion.set_other",
                  new Object[]{
                     target.func_70005_c_(),
                     newDisabled
                        ? new TextComponentTranslation("commands.srparasites.guidistortion.state.disabled", new Object[0])
                        : new TextComponentTranslation("commands.srparasites.guidistortion.state.enabled", new Object[0])
                  }
               )
            );
         }

         if (target != sender) {
            target.func_145747_a(
               new TextComponentTranslation(
                  "commands.srparasites.guidistortion.set_self",
                  new Object[]{
                     newDisabled
                        ? new TextComponentTranslation("commands.srparasites.guidistortion.state.disabled", new Object[0])
                        : new TextComponentTranslation("commands.srparasites.guidistortion.state.enabled", new Object[0])
                  }
               )
            );
         }
      }
   }

   private static NBTTagCompound getPersistedData(EntityPlayerMP player) {
      NBTTagCompound entityData = player.getEntityData();
      NBTTagCompound persisted;
      if (entityData.func_150297_b("PlayerPersisted", 10)) {
         persisted = entityData.func_74775_l("PlayerPersisted");
      } else {
         persisted = new NBTTagCompound();
         entityData.func_74782_a("PlayerPersisted", persisted);
      }

      return persisted;
   }

   private static boolean isCreativeOrSpectator(EntityPlayerMP player) {
      if (player == null) {
         return false;
      } else if (player.func_175149_v()) {
         return true;
      } else if (player.field_71134_c == null) {
         return false;
      } else {
         GameType type = player.field_71134_c.func_73081_b();
         return type == GameType.CREATIVE || type == GameType.SPECTATOR;
      }
   }

   public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
      return sender.func_70003_b(2, this.func_71517_b());
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
      List<String> out = new ArrayList<>();
      if (args.length == 1) {
         out.add("on");
         out.add("off");
         out.add("toggle");
         out.add("status");
         return CommandBase.func_175762_a(args, out);
      } else if (args.length != 2) {
         return out;
      } else {
         out.add("all");
         out.add("@a");
         String[] names = server.func_71213_z();

         for (String name : names) {
            out.add(name);
         }

         return CommandBase.func_175762_a(args, out);
      }
   }

   public boolean func_82358_a(String[] args, int index) {
      return index == 1;
   }
}
