package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public final class PottedFlowerHooks {
   @SubscribeEvent
   public static void onRightClick(RightClickBlock e) {
      if (e.getFace() != EnumFacing.UP) {
         if (e.getWorld().func_180495_p(e.getPos()).func_177230_c() == Blocks.field_150457_bL) {
            TileEntity te = e.getWorld().func_175625_s(e.getPos());
            if (te instanceof TileEntityFlowerPot) {
               if (((TileEntityFlowerPot)te).func_184403_b().func_190926_b()) {
                  ItemStack held = e.getItemStack();
                  if (!held.func_190926_b()) {
                     Item item = held.func_77973_b();
                     Block potted = null;
                     if (item == Item.func_150898_a(SRPBlocks.ASSIMILATED_BLOSSOM)) {
                        potted = SRPBlocks.POTTED_ASSIMILATED_BLOSSOM;
                     }

                     if (potted != null) {
                        if (!e.getWorld().field_72995_K) {
                           e.getWorld().func_180501_a(e.getPos(), potted.func_176223_P(), 3);
                           if (!e.getEntityPlayer().field_71075_bZ.field_75098_d) {
                              held.func_190918_g(1);
                           }
                        }

                        e.setCanceled(true);
                        e.setCancellationResult(EnumActionResult.SUCCESS);
                     }
                  }
               }
            }
         }
      }
   }
}
