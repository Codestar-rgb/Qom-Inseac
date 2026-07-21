package com.dhanantry.scapeandrunparasites.client.shader;

import com.dhanantry.scapeandrunparasites.client.fog.SRPFogManager;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BreatheShaderCommand extends CommandBase implements IClientCommand {
   public String func_71517_b() {
      return "srp_breathe";
   }

   public String func_71518_a(ICommandSender sender) {
      return "/srp_breathe <on|off|toggle>";
   }

   public int func_82362_a() {
      return 0;
   }

   public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
      return false;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) {
      String mode = args.length == 0 ? "toggle" : args[0].toLowerCase();
      boolean enable;
      switch (mode) {
         case "on":
            enable = true;
            break;
         case "off":
            enable = false;
            break;
         case "toggle":
         default:
            enable = Minecraft.func_71410_x().field_71460_t.func_147706_e() == null;
      }

      if (enable) {
         BreatheShaderManager.get().enable();
         SRPFogManager.get().enable();
         sender.func_145747_a(new TextComponentString("[SRP] Breathe shader: ON"));
      } else {
         BreatheShaderManager.get().disable();
         SRPFogManager.get().disable();
         sender.func_145747_a(new TextComponentString("[SRP] Breathe shader: OFF"));
      }
   }

   public static void register() {
      ClientCommandHandler.instance.func_71560_a(new BreatheShaderCommand());
   }
}
