/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$FogMode
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity
 *  net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType
 *  net.minecraftforge.fluids.BlockFluidBase
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  org.lwjgl.opengl.GL11
 */
package com.subspaceparasite.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(value={Side.CLIENT}, modid="subspaceparasite")
public final class DeadBloodFogHandler {
    private static final String DEADBLOOD_BLOCK_ID = "subspaceparasite:deadblood";
    private static final String DEADBLOOD_FLUID_NAME = "deadblood";

    private static boolean isDeadBloodState(IBlockState s) {
        if (s == null) {
            return false;
        }
        Block b = s.func_177230_c();
        ResourceLocation key = b.getRegistryName();
        if (key != null && DEADBLOOD_BLOCK_ID.equals(key.toString())) {
            return true;
        }
        if (b instanceof IFluidBlock) {
            IFluidBlock fb = (IFluidBlock)b;
            return fb.getFluid() != null && DEADBLOOD_FLUID_NAME.equals(fb.getFluid().getName());
        }
        if (b instanceof BlockFluidBase) {
            BlockFluidBase bf = (BlockFluidBase)b;
            return bf.getFluid() != null && DEADBLOOD_FLUID_NAME.equals(bf.getFluid().getName());
        }
        return false;
    }

    private static boolean isEyeInDeadBlood(Entity e, double pt) {
        if (e == null || e.field_70170_p == null) {
            return false;
        }
        Vec3d eyes = e.func_174824_e((float)pt);
        BlockPos eyePos = new BlockPos(eyes);
        IBlockState s0 = e.field_70170_p.func_180495_p(eyePos);
        if (DeadBloodFogHandler.isDeadBloodState(s0)) {
            Block b = s0.func_177230_c();
            if (b instanceof BlockFluidBase) {
                float filled = ((BlockFluidBase)b).getFilledPercentage(e.field_70170_p, eyePos);
                return filled > 0.1f;
            }
            return true;
        }
        Vec3d eyesDown = eyes.func_72441_c(0.0, -0.0625, 0.0);
        IBlockState s1 = e.field_70170_p.func_180495_p(new BlockPos(eyesDown));
        return DeadBloodFogHandler.isDeadBloodState(s1);
    }

    @SubscribeEvent
    public static void onFogColors(EntityViewRenderEvent.FogColors event) {
        if (DeadBloodFogHandler.isEyeInDeadBlood(event.getEntity(), event.getRenderPartialTicks())) {
            event.setRed(0.08f);
            event.setGreen(0.2f);
            event.setBlue(0.07f);
        }
    }

    @SubscribeEvent
    public static void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (DeadBloodFogHandler.isEyeInDeadBlood(event.getEntity(), event.getRenderPartialTicks())) {
            event.setDensity(0.48f);
            event.setCanceled(true);
            GlStateManager.func_187430_a((GlStateManager.FogMode)GlStateManager.FogMode.EXP2);
        }
    }

    @SubscribeEvent
    public static void onRenderFog(EntityViewRenderEvent.RenderFogEvent event) {
        if (DeadBloodFogHandler.isEyeInDeadBlood(event.getEntity(), event.getRenderPartialTicks())) {
            GlStateManager.func_187430_a((GlStateManager.FogMode)GlStateManager.FogMode.LINEAR);
            GL11.glFogf((int)2915, (float)0.0f);
            GL11.glFogf((int)2916, (float)2.5f);
        }
    }

    @SubscribeEvent
    public static void onOverlay(RenderBlockOverlayEvent event) {
        if (event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.WATER && DeadBloodFogHandler.isEyeInDeadBlood((Entity)event.getPlayer(), 0.0)) {
            event.setCanceled(true);
        }
    }
}

