/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.client.model.ModelSRP;
import com.subspaceparasite.client.renderer.RenderCosmical;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class LayerCosmicalHacking
implements LayerRenderer<EntityPCosmical> {
    private static ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/layer/cosmichasking.png");
    private RenderCosmical creeperRenderer;
    private ModelSRP modelIn;

    public LayerCosmicalHacking(RenderCosmical creeperRendererIn, ModelSRP model) {
        this.creeperRenderer = creeperRendererIn;
        this.modelIn = model;
    }

    public void doRenderLayer(EntityPCosmical entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entitylivingbaseIn.getShadowStatus() && !entitylivingbaseIn.getCloneC() && !entitylivingbaseIn.getTargetedEntityVictims().isEmpty()) {
            boolean flag = entitylivingbaseIn.func_82150_aj();
            GlStateManager.func_179132_a((!flag ? 1 : 0) != 0);
            this.creeperRenderer.func_110776_a(LIGHTNING_TEXTURE);
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179096_D();
            float f = (float)entitylivingbaseIn.field_70173_aa + partialTicks;
            GlStateManager.func_179109_b((float)(f * 0.01f), (float)(f * 0.01f), (float)0.0f);
            GlStateManager.func_179128_n((int)5888);
            GlStateManager.func_179147_l();
            float f1 = 0.5f;
            GlStateManager.func_179131_c((float)178.0f, (float)0.5f, (float)250.5f, (float)1.0f);
            GlStateManager.func_179140_f();
            GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
            this.modelIn.func_178686_a(this.creeperRenderer.func_177087_b());
            Minecraft.func_71410_x().field_71460_t.func_191514_d(true);
            this.modelIn.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.func_71410_x().field_71460_t.func_191514_d(false);
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179096_D();
            GlStateManager.func_179128_n((int)5888);
            GlStateManager.func_179145_e();
            GlStateManager.func_179084_k();
            GlStateManager.func_179132_a((boolean)flag);
        }
    }

    public boolean func_177142_b() {
        return false;
    }
}

