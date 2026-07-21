package com.dhanantry.scapeandrunparasites.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SRPCommand implements ICommand {
   private final List aliases = new ArrayList();
   protected String fullEntityName;
   protected Entity conjuredEntity;

   public SRPCommand() {
      this.aliases.add("conjure");
      this.aliases.add("conj");
   }

   public int compareTo(ICommand arg0) {
      return 0;
   }

   public String func_71517_b() {
      return "conjure";
   }

   public String func_71518_a(ICommandSender sender) {
      return "conjure <text>";
   }

   public List<String> func_71514_a() {
      return this.aliases;
   }

   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
      World world = sender.func_130014_f_();
      if (!world.field_72995_K) {
         if (argString.length == 0) {
            sender.func_145747_a(new TextComponentString("Invalid argument"));
            return;
         }

         sender.func_145747_a(new TextComponentString("Conjuring: [" + argString[0] + "]"));
         this.fullEntityName = "srparasites:" + argString[0];
         ResourceLocation ccc = new ResourceLocation(this.fullEntityName);
         if (EntityList.func_180125_b(ccc)) {
            this.conjuredEntity = EntityList.func_188429_b(ccc, world);
            this.conjuredEntity.func_82149_j(sender.func_174793_f());
            world.func_72838_d(this.conjuredEntity);
         } else {
            sender.func_145747_a(new TextComponentString("Entity not found"));
         }
      }
   }

   public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
      return sender.func_70003_b(2, this.func_71517_b());
   }

   public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
      List<String> atm = new ArrayList<>();
      atm.add("sentry");
      atm.add("carrier_heavy");
      return args.length == 1 ? CommandBase.func_175762_a(args, atm) : atm;
   }

   public boolean func_82358_a(String[] args, int index) {
      return false;
   }
}
