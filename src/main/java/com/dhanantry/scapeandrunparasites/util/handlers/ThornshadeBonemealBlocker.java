/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.BonemealEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.block.BlockThornshade;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public class ThornshadeBonemealBlocker {
    @SubscribeEvent
    public static void onBonemeal(BonemealEvent event) {
        BlockPos pos;
        World world = event.getWorld();
        IBlockState state = world.func_180495_p(pos = event.getPos());
        if (!(state.func_177230_c() instanceof BlockThornshade)) {
            return;
        }
        ItemStack stack = event.getStack();
        if (stack.func_190926_b()) {
            return;
        }
        if (stack.func_77973_b() == Items.field_151100_aR && stack.func_77960_j() == 15) {
            event.setCanceled(true);
            event.setResult(Event.Result.DENY);
        }
    }
}

