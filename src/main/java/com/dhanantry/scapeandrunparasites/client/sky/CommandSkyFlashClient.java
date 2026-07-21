package com.dhanantry.scapeandrunparasites.client.sky;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CommandSkyFlashClient extends CommandBase {
   public String func_71517_b() {
      return "Apophis";
   }

   public String func_71518_a(ICommandSender s) {
      return "/Apophis [ticks] [span] [alt] [count] [bank]";
   }

   public int func_82362_a() {
      return 0;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      int ticks = 15;
      double span = 180.0;
      double alt = 120.0;
      int count = 10;
      float bank = -15.0F;
      if (args.length > 0) {
         ticks = func_175764_a(args[0], 1, 600);
      }

      if (args.length > 1) {
         span = func_175765_c(args[1]);
      }

      if (args.length > 2) {
         alt = func_175765_c(args[2]);
      }

      if (args.length > 3) {
         count = func_175764_a(args[3], 1, 100);
      }

      if (args.length > 4) {
         bank = (float)func_175765_c(args[4]);
      }

      if (Minecraft.func_71410_x().field_71441_e != null) {
         Random rng = new Random();

         for (int i = 0; i < count; i++) {
            double yawOffsetDeg = rng.nextDouble() * 120.0 - 60.0;
            double forwardOffset = rng.nextDouble() * 200.0 - 100.0;
            double lateralOffset = rng.nextDouble() * 300.0 - 150.0;
            double altJitter = alt + rng.nextDouble() * 60.0 - 30.0;
            float bankJitter = bank + (float)(rng.nextDouble() * 20.0 - 10.0);
            SkyFlashRenderer.triggerOffset(ticks, span, altJitter, bankJitter, yawOffsetDeg, forwardOffset, lateralOffset);
         }

         sender.func_145747_a(new TextComponentString(String.format("%d %dt %.1f %.1f", count, ticks, span, alt)));
      }
   }
}
