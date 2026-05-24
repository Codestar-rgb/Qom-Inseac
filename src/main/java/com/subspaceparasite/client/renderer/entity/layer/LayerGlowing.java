/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLiving
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client.renderer.entity.layer;

import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class LayerGlowing<T extends EntityPMalleable>
implements LayerRenderer<T> {
    protected final RenderMalleable<T> entity;

    public LayerGlowing(RenderMalleable<T> parasite) {
        this.entity = parasite;
    }

    public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.entity.func_110776_a(this.entity.getGlowTexture(this.entity.getBaseTexture(entitylivingbaseIn)));
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
        GlStateManager.func_179140_f();
        GlStateManager.func_179132_a((!entitylivingbaseIn.func_82150_aj() ? 1 : 0) != 0);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)61680.0f, (float)0.0f);
        GlStateManager.func_179145_e();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().field_71460_t.func_191514_d(true);
        this.entity.func_177087_b().func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.func_71410_x().field_71460_t.func_191514_d(false);
        this.entity.func_177105_a((EntityLiving)entitylivingbaseIn);
        GlStateManager.func_179084_k();
    }

    public boolean func_177142_b() {
        return false;
    }
}

