/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fluids.BlockFluidBase
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client;

import com.subspaceparasite.init.SPFluids;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class DeadBloodSwimHandler {
    private static boolean isHeadInDeadBlood(EntityPlayer p) {
        double eyeY = p.field_70163_u + (double)p.func_70047_e();
        BlockPos head = new BlockPos(p.field_70165_t, eyeY, p.field_70161_v);
        IBlockState s = p.field_70170_p.func_180495_p(head);
        if (s == null) {
            return false;
        }
        if (s.func_177230_c() instanceof IFluidBlock) {
            return ((IFluidBlock)s.func_177230_c()).getFluid() == SPFluids.DEADBLOOD_FLUID;
        }
        if (s.func_177230_c() instanceof BlockFluidBase) {
            BlockFluidBase bf = (BlockFluidBase)s.func_177230_c();
            if (bf.getFluid() != SPFluids.DEADBLOOD_FLUID) {
                return false;
            }
            float filled = bf.getFilledPercentage(p.field_70170_p, head);
            return filled > 0.1f;
        }
        return false;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        KeyBinding jump;
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        if (!(e.player instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer p = e.player;
        if (p.field_70170_p.field_72995_K && DeadBloodSwimHandler.isHeadInDeadBlood(p) && (jump = Minecraft.func_71410_x().field_71474_y.field_74314_A).func_151470_d()) {
            p.field_70181_x = Math.min(p.field_70181_x + 0.045, 0.12);
        }
    }
}

