/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package com.subspaceparasite.client.renderer.entity.adapted;

import com.subspaceparasite.client.model.entity.adapted.ModelRanracAdapted;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.adapted.EntityRanracAdapted;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderRanracAdapted
extends RenderMalleable<EntityRanracAdapted> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/ranraca.png");
    public static final ResourceLocation TEXTUREV = new ResourceLocation("subspaceparasite:textures/entity/monster/ranracav.png");
    public static final ResourceLocation TEXTUREB = new ResourceLocation("subspaceparasite:textures/entity/monster/ranracab.png");
    public static final ResourceLocation TEXTUREH = new ResourceLocation("subspaceparasite:textures/entity/monster/ranracah.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");
    private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");

    public RenderRanracAdapted(RenderManager manager) {
        super(manager, new ModelRanracAdapted(), 1.0f);
    }

    protected void preRenderCallback(EntityRanracAdapted entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.5f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityRanracAdapted entity) {
        switch (entity.getSkin()) {
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
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }

    public void doRender(EntityRanracAdapted entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.func_76986_a((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
        EntityLivingBase entitylivingbase = entity.getTargetedEntity();
        if (entitylivingbase != null) {
            float f = 10.0f;
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            this.func_110776_a(GUARDIAN_BEAM_TEXTURE);
            GlStateManager.func_187421_b((int)3553, (int)10242, (int)10497);
            GlStateManager.func_187421_b((int)3553, (int)10243, (int)10497);
            GlStateManager.func_179129_p();
            GlStateManager.func_179084_k();
            GlStateManager.func_179132_a((boolean)true);
            float f1 = 240.0f;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
            GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            float f2 = (float)entity.field_70170_p.func_82737_E() + partialTicks;
            float f3 = f2 * 0.5f % 1.0f;
            float f4 = entity.func_70047_e();
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)((float)x), (float)((float)y + f4), (float)((float)z));
            Vec3d vec3d = this.getPosition(entitylivingbase, (double)entitylivingbase.field_70131_O * 0.5, partialTicks);
            Vec3d vec3d1 = this.getPosition((EntityLivingBase)entity, f4, partialTicks);
            Vec3d vec3d2 = vec3d.func_178788_d(vec3d1);
            double d0 = vec3d2.func_72433_c() + 1.0;
            vec3d2 = vec3d2.func_72432_b();
            float f5 = (float)Math.acos(vec3d2.field_72448_b);
            float f6 = (float)Math.atan2(vec3d2.field_72449_c, vec3d2.field_72450_a);
            GlStateManager.func_179114_b((float)((1.5707964f + -f6) * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)(f5 * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            boolean i = true;
            double d1 = (double)f2 * 0.05 * -1.5;
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            float f7 = f * f;
            int j = 64 + (int)(f7 * 191.0f);
            int k = 32 + (int)(f7 * 191.0f);
            int l = 128 - (int)(f7 * 64.0f);
            double d2 = 0.2;
            double d3 = 0.282;
            double d4 = 0.0 + Math.cos(d1 + 2.356194490192345) * 0.282;
            double d5 = 0.0 + Math.sin(d1 + 2.356194490192345) * 0.282;
            double d6 = 0.0 + Math.cos(d1 + 0.7853981633974483) * 0.282;
            double d7 = 0.0 + Math.sin(d1 + 0.7853981633974483) * 0.282;
            double d8 = 0.0 + Math.cos(d1 + 3.9269908169872414) * 0.282;
            double d9 = 0.0 + Math.sin(d1 + 3.9269908169872414) * 0.282;
            double d10 = 0.0 + Math.cos(d1 + 5.497787143782138) * 0.282;
            double d11 = 0.0 + Math.sin(d1 + 5.497787143782138) * 0.282;
            double d12 = 0.0 + Math.cos(d1 + Math.PI) * 0.2;
            double d13 = 0.0 + Math.sin(d1 + Math.PI) * 0.2;
            double d14 = 0.0 + Math.cos(d1 + 0.0) * 0.2;
            double d15 = 0.0 + Math.sin(d1 + 0.0) * 0.2;
            double d16 = 0.0 + Math.cos(d1 + 1.5707963267948966) * 0.2;
            double d17 = 0.0 + Math.sin(d1 + 1.5707963267948966) * 0.2;
            double d18 = 0.0 + Math.cos(d1 + 4.71238898038469) * 0.2;
            double d19 = 0.0 + Math.sin(d1 + 4.71238898038469) * 0.2;
            double d20 = 0.0;
            double d21 = 0.4999;
            double d22 = -1.0f + f3;
            double d23 = d0 * 2.5 + d22;
            bufferbuilder.func_181662_b(d12, d0, d13).func_187315_a(0.4999, d23).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d12, 0.0, d13).func_187315_a(0.4999, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d14, 0.0, d15).func_187315_a(0.0, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d14, d0, d15).func_187315_a(0.0, d23).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d16, d0, d17).func_187315_a(0.4999, d23).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d16, 0.0, d17).func_187315_a(0.4999, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d18, 0.0, d19).func_187315_a(0.0, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d18, d0, d19).func_187315_a(0.0, d23).func_181669_b(j, k, l, 255).func_181675_d();
            double d24 = 0.0;
            if (entity.field_70173_aa % 2 == 0) {
                d24 = 0.5;
            }
            bufferbuilder.func_181662_b(d4, d0, d5).func_187315_a(0.5, d24 + 0.5).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d6, d0, d7).func_187315_a(1.0, d24 + 0.5).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d10, d0, d11).func_187315_a(1.0, d24).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d8, d0, d9).func_187315_a(0.5, d24).func_181669_b(j, k, l, 255).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
        }
    }

    private Vec3d getPosition(EntityLivingBase entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
        double d0 = entityLivingBaseIn.field_70142_S + (entityLivingBaseIn.field_70165_t - entityLivingBaseIn.field_70142_S) * (double)p_177110_4_;
        double d1 = p_177110_2_ + entityLivingBaseIn.field_70137_T + (entityLivingBaseIn.field_70163_u - entityLivingBaseIn.field_70137_T) * (double)p_177110_4_;
        double d2 = entityLivingBaseIn.field_70136_U + (entityLivingBaseIn.field_70161_v - entityLivingBaseIn.field_70136_U) * (double)p_177110_4_;
        return new Vec3d(d0, d1, d2);
    }
}

