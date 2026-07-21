package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.block.BlockThornshade;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

@EventBusSubscriber(modid = "srparasites")
public class ThornshadeBonemealBlocker {
   @SubscribeEvent
   public static void onBonemeal(BonemealEvent event) {
      World world = event.getWorld();
      BlockPos pos = event.getPos();
      IBlockState state = world.func_180495_p(pos);
      if (state.func_177230_c() instanceof BlockThornshade) {
         ItemStack stack = event.getStack();
         if (!stack.func_190926_b()) {
            if (stack.func_77973_b() == Items.field_151100_aR && stack.func_77960_j() == 15) {
               event.setCanceled(true);
               event.setResult(Result.DENY);
            }
         }
      }
   }
}
