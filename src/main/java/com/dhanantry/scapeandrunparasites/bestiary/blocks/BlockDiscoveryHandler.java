package com.dhanantry.scapeandrunparasites.bestiary.blocks;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiarySync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber(modid = "srparasites")
public class BlockDiscoveryHandler {
   @SubscribeEvent
   public static void onPlayerTick(PlayerTickEvent e) {
      if (e.phase == Phase.END) {
         EntityPlayer player = e.player;
         if (player != null && player.field_70170_p != null && !player.field_70170_p.field_72995_K) {
            if (player instanceof EntityPlayerMP) {
               EntityPlayerMP p = (EntityPlayerMP)player;
               IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
               if (prog != null) {
                  boolean changed = false;
                  if (p.field_71071_by != null) {
                     changed |= scanInventory(p.field_71071_by, prog);
                  }

                  if (changed) {
                     BestiaryNetwork.CH.sendTo(new PacketBestiarySync(prog), p);
                  }
               }
            }
         }
      }
   }

   private static boolean scanInventory(IInventory inv, IBestiaryProgress prog) {
      boolean changed = false;

      for (int i = 0; i < inv.func_70302_i_(); i++) {
         ItemStack st = inv.func_70301_a(i);
         if (!st.func_190926_b() && st.func_77973_b() instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock)st.func_77973_b();
            ResourceLocation id = itemBlock.func_179223_d().getRegistryName();
            if (id != null) {
               BlockBestiaryEntry entry = SRPBlockCompendiumRegistry.get(id);
               if (entry != null && !prog.hasSeenBlock(id)) {
                  prog.markBlockSeen(id);
                  changed = true;
               }
            }
         }
      }

      return changed;
   }
}
