package com.dhanantry.scapeandrunparasites.network;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandSRPGuideClear extends CommandBase {
   public String func_71517_b() {
      return "srpguideclear";
   }

   public String func_71518_a(ICommandSender sender) {
      return "/srpguideclear";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (!(sender instanceof EntityPlayerMP)) {
         sender.func_145747_a(new TextComponentString("Players only."));
      } else {
         server.func_71187_D().func_71556_a(sender, "srpguide clear");
      }
   }
}
