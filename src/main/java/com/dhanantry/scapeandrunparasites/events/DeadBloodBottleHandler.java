/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.init.SRPFluids;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeadBloodBottleHandler {
    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem e) {
        if (e.getHand() != EnumHand.MAIN_HAND) {
            return;
        }
        EntityPlayer player = e.getEntityPlayer();
        World world = e.getWorld();
        ItemStack held = e.getItemStack();
        if (held.func_190926_b() || held.func_77973_b() != Items.field_151069_bo) {
            return;
        }
        RayTraceResult rt = DeadBloodBottleHandler.rayTrace(world, player, true);
        if (rt == null || rt.field_72313_a != RayTraceResult.Type.BLOCK) {
            return;
        }
        BlockPos pos = rt.func_178782_a();
        IBlockState state = world.func_180495_p(pos);
        if (!(state.func_177230_c() instanceof IFluidBlock)) {
            return;
        }
        IFluidBlock fluidBlock = (IFluidBlock)state.func_177230_c();
        Fluid fluid = fluidBlock.getFluid();
        if (fluid != SRPFluids.DEADBLOOD_FLUID) {
            return;
        }
        if (!world.field_72995_K) {
            if (!player.field_71075_bZ.field_75098_d) {
                held.func_190918_g(1);
            }
            ItemStack filled = new ItemStack(SRPItems.DEADBLOOD_FLUID);
            if (held.func_190926_b()) {
                player.func_184611_a(EnumHand.MAIN_HAND, filled);
            } else if (!player.field_71071_by.func_70441_a(filled)) {
                player.func_71019_a(filled, false);
            }
            world.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187615_H, SoundCategory.PLAYERS, 0.9f, 1.0f);
        }
        e.setCanceled(true);
        e.setCancellationResult(EnumActionResult.SUCCESS);
    }

    private static RayTraceResult rayTrace(World world, EntityPlayer player, boolean useLiquids) {
        float pitch = player.field_70125_A;
        float yaw = player.field_70177_z;
        Vec3d eye = player.func_174824_e(1.0f);
        float f2 = MathHelper.func_76134_b((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f3 = MathHelper.func_76126_a((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = -MathHelper.func_76134_b((float)(-pitch * ((float)Math.PI / 180)));
        float f5 = MathHelper.func_76126_a((float)(-pitch * ((float)Math.PI / 180)));
        float x = f3 * f4;
        float y = f5;
        float z = f2 * f4;
        double reach = player.func_110148_a(EntityPlayer.REACH_DISTANCE).func_111126_e();
        Vec3d look = eye.func_72441_c((double)x * reach, (double)y * reach, (double)z * reach);
        return world.func_147447_a(eye, look, useLiquids, !useLiquids, false);
    }
}

