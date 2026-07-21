package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class SRPCommandBestiaryStats implements ICommand {
   private final List<String> aliases = new ArrayList<>();

   public SRPCommandBestiaryStats() {
      this.aliases.add("srpstats");
      this.aliases.add("srpbestiarystats");
   }

   public int compareTo(ICommand other) {
      return this.func_71517_b().compareTo(other.func_71517_b());
   }

   public String func_71517_b() {
      return "srpbestiarystats";
   }

   public String func_71518_a(ICommandSender sender) {
      return "/srpbestiarystats clear [player]";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (args.length >= 1 && "clear".equalsIgnoreCase(args[0])) {
         EntityPlayerMP target;
         if (args.length >= 2) {
            target = server.func_184103_al().func_152612_a(args[1]);
            if (target == null) {
               sender.func_145747_a(new TextComponentTranslation("commands.srparasites.bestiary_stats.player_not_found", new Object[]{args[1]}));
               return;
            }
         } else {
            if (!(sender.func_174793_f() instanceof EntityPlayerMP)) {
               sender.func_145747_a(new TextComponentTranslation("commands.srparasites.bestiary_stats.console_requires_player", new Object[0]));
               return;
            }

            target = (EntityPlayerMP)sender.func_174793_f();
         }

         IBestiaryProgress prog = (IBestiaryProgress)target.getCapability(BestiaryCapability.CAP, null);
         if (prog == null) {
            sender.func_145747_a(new TextComponentTranslation("commands.srparasites.bestiary_stats.cap_missing", new Object[]{target.func_70005_c_()}));
         } else {
            prog.clearStatsPageData();
            sender.func_145747_a(new TextComponentTranslation("commands.srparasites.bestiary_stats.cleared_other", new Object[]{target.func_70005_c_()}));
            if (sender.func_174793_f() != target) {
               target.func_145747_a(new TextComponentTranslation("commands.srparasites.bestiary_stats.cleared_self", new Object[0]));
            }
         }
      } else {
         sender.func_145747_a(new TextComponentTranslation("commands.srparasites.bestiary_stats.usage", new Object[]{this.func_71518_a(sender)}));
      }
   }

   public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
      return sender.func_70003_b(2, this.func_71517_b());
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
      List<String> out = new ArrayList<>();
      if (args.length == 1) {
         out.add("clear");
         return CommandBase.func_175762_a(args, out);
      } else {
         return args.length == 2 ? CommandBase.func_71530_a(args, server.func_71213_z()) : out;
      }
   }

   public boolean func_82358_a(String[] args, int index) {
      return index == 1;
   }
}
