/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.fluids.BlockFluidBase
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.client;

import com.subspaceparasite.init.SPFluids;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value={Side.CLIENT}, modid="subspaceparasite")
public final class DeadBloodOverlayHandler {
    private static final ResourceLocation FLOW_OVERLAY = new ResourceLocation("subspaceparasite", "textures/blocks/deadblood_flowing.png");
    private static final float OVERLAY_ALPHA = 0.55f;
    private static final float SCROLL_SPEED = 0.02f;
    private static final float UV_SCALE = 0.25f;

    private static boolean isDeadBlood(IBlockState s) {
        if (s == null) {
            return false;
        }
        Block b = s.func_177230_c();
        if (b instanceof IFluidBlock) {
            return ((IFluidBlock)b).getFluid() == SPFluids.DEADBLOOD_FLUID;
        }
        if (b instanceof BlockFluidBase) {
            return ((BlockFluidBase)b).getFluid() == SPFluids.DEADBLOOD_FLUID;
        }
        return false;
    }

    private static boolean eyesInDeadBlood(Entity e, double pt) {
        if (e == null || e.field_70170_p == null) {
            return false;
        }
        Vec3d eyes = e.func_174824_e((float)pt);
        BlockPos p0 = new BlockPos(eyes);
        IBlockState s0 = e.field_70170_p.func_180495_p(p0);
        if (DeadBloodOverlayHandler.isDeadBlood(s0)) {
            if (s0.func_177230_c() instanceof BlockFluidBase) {
                float filled = ((BlockFluidBase)s0.func_177230_c()).getFilledPercentage(e.field_70170_p, p0);
                return filled > 0.1f;
            }
            return true;
        }
        Vec3d eyesDown = eyes.func_72441_c(0.0, -0.0625, 0.0);
        return DeadBloodOverlayHandler.isDeadBlood(e.field_70170_p.func_180495_p(new BlockPos(eyesDown)));
    }

    @SubscribeEvent
    public static void onWaterOverlay(RenderBlockOverlayEvent event) {
        if (event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.WATER && DeadBloodOverlayHandler.eyesInDeadBlood((Entity)event.getPlayer(), 0.0)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        Entity viewer = mc.func_175606_aa();
        if (!DeadBloodOverlayHandler.eyesInDeadBlood(viewer, mc.func_184121_ak())) {
            return;
        }
        ScaledResolution sr = new ScaledResolution(mc);
        int w = sr.func_78326_a();
        int h = sr.func_78328_b();
        TextureAtlasSprite sprite = mc.func_147117_R().func_110572_b("subspaceparasite:blocks/deadblood_flowing");
        GlStateManager.func_179097_i();
        GlStateManager.func_179132_a((boolean)false);
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
        float alpha = 0.45f;
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)0.45f);
        float u0 = sprite.func_94209_e();
        float v0 = sprite.func_94206_g();
        float u1 = sprite.func_94212_f();
        float v1 = sprite.func_94210_h();
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder buf = tess.func_178180_c();
        buf.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        buf.func_181662_b(0.0, (double)h, -90.0).func_187315_a((double)u0, (double)v1).func_181675_d();
        buf.func_181662_b((double)w, (double)h, -90.0).func_187315_a((double)u1, (double)v1).func_181675_d();
        buf.func_181662_b((double)w, 0.0, -90.0).func_187315_a((double)u1, (double)v0).func_181675_d();
        buf.func_181662_b(0.0, 0.0, -90.0).func_187315_a((double)u0, (double)v0).func_181675_d();
        tess.func_78381_a();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179084_k();
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179126_j();
    }
}

