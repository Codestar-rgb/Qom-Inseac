package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiBestiary;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiaryRequest;
import com.dhanantry.scapeandrunparasites.client.FieldGuideClientSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemSRPFieldGuide extends Item {
   public ItemSRPFieldGuide() {
      this.setRegistryName("srp_field_guide");
      this.func_77655_b("srparasites.srp_field_guide");
      this.func_77625_d(1);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
   }

   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
      return world.field_72995_K
         ? ItemSRPFieldGuide.ClientHandler.handleClient(player, hand)
         : new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
   }

   private static class ClientHandler {
      public static ActionResult<ItemStack> handleClient(EntityPlayer player, EnumHand hand) {
         if (player.func_70093_af()) {
            FieldGuideClientSettings.bestiarySounds = !FieldGuideClientSettings.bestiarySounds;
            player.func_145747_a(
               new TextComponentTranslation(FieldGuideClientSettings.bestiarySounds ? "srpguide.sounds.on" : "srpguide.sounds.off", new Object[0])
            );
            return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
         } else {
            BestiaryNetwork.CH.sendToServer(new PacketBestiaryRequest());
            Minecraft.func_71410_x().func_147108_a(new GuiBestiary(player));
            return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
         }
      }
   }
}
