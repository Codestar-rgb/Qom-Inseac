/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive;

import com.dhanantry.scapeandrunparasites.client.model.entity.primitive.ModelNogla;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.text.Normalizer;
import java.util.Locale;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class RenderNogla
extends RenderMalleable<EntityNogla> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/nogla.png");
    public static final ResourceLocation TEXTURE2 = new ResourceLocation("srparasites:textures/entity/monster/noglasp1.png");
    public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/noglav.png");
    public static final ResourceLocation TEXTUREB = new ResourceLocation("srparasites:textures/entity/monster/noglab.png");
    public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/noglah.png");
    public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snogla.png");
    public static final ResourceLocation RICARDO_TEX = new ResourceLocation("srparasites:textures/entity/monster/ricardo.png");
    public static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/primitivereekerfrozen.png");

    public RenderNogla(RenderManager manager) {
        super(manager, new ModelNogla(), 1.3f);
    }

    protected void preRenderCallback(EntityNogla entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityNogla entity) {
        if (this.isRicardoName(entity)) {
            return RICARDO_TEX;
        }
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE2;
            }
            case 5: {
                return TEXTUREV;
            }
            case 6: {
                return TEXTUREB;
            }
            case 7: {
                return TEXTUREH;
            }
            case 120: {
                return STEXTURE;
            }
        }
        return TEXTURE;
    }

    private boolean isRicardoName(EntityNogla entity) {
        if (!SRPConfigMobs.noglaRicardoVariantEnabled) {
            return false;
        }
        if (!entity.func_145818_k_()) {
            return false;
        }
        String raw = TextFormatting.func_110646_a((String)entity.func_95999_t());
        if (raw == null) {
            return false;
        }
        String name = RenderNogla.normalizeLower(raw);
        if ("ricardo".equals(name)) {
            return true;
        }
        String localized = I18n.func_135052_a((String)"entity.srparasites.nametag.ricardo", (Object[])new Object[0]);
        return localized != null && !localized.isEmpty() && name.equals(RenderNogla.normalizeLower(localized));
    }

    private static String normalizeLower(String s) {
        String n = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return n.trim().toLowerCase(Locale.ROOT);
    }
}

