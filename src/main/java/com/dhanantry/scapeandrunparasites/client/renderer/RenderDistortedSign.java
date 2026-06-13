/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiUtilRenderComponents
 *  net.minecraft.client.model.ModelSign
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 */
package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderDistortedSign
extends TileEntitySpecialRenderer<TileEntitySign> {
    private static final ResourceLocation SIGN_TEXTURE = new ResourceLocation("textures/entity/sign.png");
    private final ModelSign model = new ModelSign();

    public void render(TileEntitySign te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        Block block = te.func_145838_q();
        GlStateManager.func_179094_E();
        if (block == Blocks.field_150472_an) {
            GlStateManager.func_179109_b((float)((float)x + 0.5f), (float)((float)y + 0.5f), (float)((float)z + 0.5f));
            float rot = (float)(te.func_145832_p() * 360) / 16.0f;
            GlStateManager.func_179114_b((float)(-rot), (float)0.0f, (float)1.0f, (float)0.0f);
            this.model.field_78165_b.field_78806_j = true;
        } else {
            int meta = te.func_145832_p();
            float rot = 0.0f;
            if (meta == 2) {
                rot = 180.0f;
            }
            if (meta == 4) {
                rot = 90.0f;
            }
            if (meta == 5) {
                rot = -90.0f;
            }
            GlStateManager.func_179109_b((float)((float)x + 0.5f), (float)((float)y + 0.5f), (float)((float)z + 0.5f));
            GlStateManager.func_179114_b((float)(-rot), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179109_b((float)0.0f, (float)-0.3125f, (float)-0.4375f);
            this.model.field_78165_b.field_78806_j = false;
        }
        if (destroyStage >= 0) {
            this.func_147499_a(field_178460_a[destroyStage]);
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)4.0f, (float)2.0f, (float)1.0f);
            GlStateManager.func_179109_b((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.func_179128_n((int)5888);
        } else {
            this.func_147499_a(SIGN_TEXTURE);
        }
        GlStateManager.func_179091_B();
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a((float)0.6666667f, (float)-0.6666667f, (float)-0.6666667f);
        this.model.func_78164_a();
        GlStateManager.func_179121_F();
        FontRenderer font = this.func_147498_b();
        GlStateManager.func_179109_b((float)0.0f, (float)0.33333334f, (float)0.046666667f);
        GlStateManager.func_179152_a((float)0.010416667f, (float)-0.010416667f, (float)0.010416667f);
        GlStateManager.func_187432_a((float)0.0f, (float)0.0f, (float)-0.010416667f);
        GlStateManager.func_179132_a((boolean)false);
        if (destroyStage < 0) {
            boolean distortSigns = GuiDistortionHelper.shouldDistortSigns(Minecraft.func_71410_x());
            for (int j = 0; j < te.field_145915_a.length; ++j) {
                String s;
                if (te.field_145915_a[j] == null) continue;
                ITextComponent itextcomponent = te.field_145915_a[j];
                List list = GuiUtilRenderComponents.func_178908_a((ITextComponent)itextcomponent, (int)90, (FontRenderer)font, (boolean)false, (boolean)true);
                String string = s = list != null && !list.isEmpty() ? ((ITextComponent)list.get(0)).func_150254_d() : "";
                if (j == te.field_145918_i) {
                    s = "> " + s + " <";
                }
                String shown = distortSigns ? GuiDistortionHelper.jamText(s) : s;
                font.func_78276_b(shown, -font.func_78256_a(shown) / 2, j * 10 - te.field_145915_a.length * 5, 0);
            }
        }
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179121_F();
        if (destroyStage >= 0) {
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n((int)5888);
        }
    }
}

