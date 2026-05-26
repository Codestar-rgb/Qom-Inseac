/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$Profile
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.client.renderer.LayerCosmicalHacking;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class RenderCosmical<T extends EntityPCosmical>
extends RenderMalleable<T> {
    protected ModelSP mainModel2;
    private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/layer/cosmichasking.png");

    public RenderCosmical(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
        this.mainModel2 = (ModelSP)modelbaseIn;
        this.func_177094_a(new LayerCosmicalHacking(this, this.mainModel2));
    }

    public RenderCosmical(RenderManager rendermanagerIn, ModelBase modelbaseIn, ModelSP modelbaseIn2, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
        this.mainModel2 = modelbaseIn2;
    }

    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
        this.doRenderCosmical(entity, x, y, z, entityYaw, partialTicks);
        this.doRenderLaser(entity, x, y, z, entityYaw, partialTicks);
    }

    public void doRenderLaser(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        for (EntityLivingBase entitylivingbase : ((EntityPCosmical)entity).getTargetedEntityVictims()) {
            if (entitylivingbase == null) continue;
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
            float f2 = (float)((EntityPCosmical)entity).field_70170_p.func_82737_E() + partialTicks;
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
            int j = 178 + (int)(f7 * 191.0f);
            int k = 0 + (int)(f7 * 191.0f);
            int l = 250 - (int)(f7 * 64.0f);
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
            if (((EntityPCosmical)entity).field_70173_aa % 2 == 0) {
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

    public void doRenderCosmical(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        boolean shouldSit;
        if (((EntityPCosmical)entity).getCloneC()) {
            return;
        }
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre(entity, (RenderLivingBase)this, partialTicks, x, y, z))) {
            return;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179129_p();
        this.mainModel2.field_78095_p = this.func_77040_d((EntityLivingBase)entity, partialTicks);
        this.mainModel2.field_78093_q = shouldSit = entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit();
        this.mainModel2.field_78091_s = entity.func_70631_g_();
        try {
            float f = this.func_77034_a(((EntityPCosmical)entity).field_70760_ar, ((EntityPCosmical)entity).field_70761_aq, partialTicks);
            float f1 = this.func_77034_a(((EntityPCosmical)entity).field_70758_at, ((EntityPCosmical)entity).field_70759_as, partialTicks);
            float f2 = f1 - f;
            if (shouldSit && entity.func_184187_bx() instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)entity.func_184187_bx();
                f = this.func_77034_a(entitylivingbase.field_70760_ar, entitylivingbase.field_70761_aq, partialTicks);
                f2 = f1 - f;
                float f3 = MathHelper.func_76142_g((float)f2);
                if (f3 < -85.0f) {
                    f3 = -85.0f;
                }
                if (f3 >= 85.0f) {
                    f3 = 85.0f;
                }
                f = f1 - f3;
                if (f3 * f3 > 2500.0f) {
                    f += f3 * 0.2f;
                }
                f2 = f1 - f;
            }
            float f7 = ((EntityPCosmical)entity).field_70127_C + (((EntityPCosmical)entity).field_70125_A - ((EntityPCosmical)entity).field_70127_C) * partialTicks;
            this.func_77039_a((EntityLivingBase)entity, x, y, z);
            float f8 = this.func_77044_a((EntityLivingBase)entity, partialTicks);
            this.func_77043_a((EntityLivingBase)entity, f8, f, partialTicks);
            float f4 = this.prepareScaleCosmical(entity, partialTicks);
            float f5 = 0.0f;
            float f6 = 0.0f;
            if (!entity.func_184218_aH()) {
                f5 = ((EntityPCosmical)entity).field_184618_aE + (((EntityPCosmical)entity).field_70721_aZ - ((EntityPCosmical)entity).field_184618_aE) * partialTicks;
                f6 = ((EntityPCosmical)entity).field_184619_aG - ((EntityPCosmical)entity).field_70721_aZ * (1.0f - partialTicks);
                if (entity.func_70631_g_()) {
                    f6 *= 3.0f;
                }
                if (f5 > 1.0f) {
                    f5 = 1.0f;
                }
                f2 = f1 - f;
            }
            GlStateManager.func_179141_d();
            this.mainModel2.func_78086_a((EntityLivingBase)entity, f6, f5, partialTicks);
            this.mainModel2.setRotationAnglesCosmical(f6, f5, f8, f2, f7, f4, (Entity)entity);
            if (this.field_188301_f) {
                boolean flag1 = this.func_177088_c((EntityLivingBase)entity);
                GlStateManager.func_179142_g();
                GlStateManager.func_187431_e((int)this.func_188298_c((Entity)entity));
                if (!this.field_188323_j) {
                    this.renderModelCosmical(entity, f6, f5, f8, f2, f7, f4);
                }
                this.func_177093_a((EntityLivingBase)entity, f6, f5, partialTicks, f8, f2, f7, f4);
                GlStateManager.func_187417_n();
                GlStateManager.func_179119_h();
                if (flag1) {
                    this.func_180565_e();
                }
            } else {
                this.renderModelCosmical(entity, f6, f5, f8, f2, f7, f4);
                GlStateManager.func_179132_a((boolean)true);
                this.func_177093_a((EntityLivingBase)entity, f6, f5, partialTicks, f8, f2, f7, f4);
            }
            GlStateManager.func_179101_C();
        }
        catch (Exception exception) {
            // empty catch block
        }
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179098_w();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        GlStateManager.func_179089_o();
        GlStateManager.func_179121_F();
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(entity, (RenderLivingBase)this, partialTicks, x, y, z));
    }

    protected void renderModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag1;
        if (((EntityPCosmical)entitylivingbaseIn).getCloneC()) {
            boolean flag12;
            boolean flag = this.func_193115_c((EntityLivingBase)entitylivingbaseIn);
            boolean bl = flag12 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
            if (flag || flag12) {
                if (!this.bindEntityTextureCosmical(entitylivingbaseIn)) {
                    return;
                }
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
                GlStateManager.func_179132_a((boolean)true);
                GlStateManager.func_179147_l();
                GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.func_179092_a((int)516, (float)0.003921569f);
                if (((EntityPCosmical)entitylivingbaseIn).getShadowStatus()) {
                    this.mainModel2.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                }
                GlStateManager.func_179084_k();
                GlStateManager.func_179092_a((int)516, (float)0.1f);
                GlStateManager.func_179132_a((boolean)true);
            }
            return;
        }
        boolean flag = this.func_193115_c((EntityLivingBase)entitylivingbaseIn);
        boolean bl = flag1 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (flag || flag1) {
            if (!this.func_180548_c((Entity)entitylivingbaseIn)) {
                return;
            }
            if (flag1) {
                GlStateManager.func_187408_a((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            this.field_77045_g.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            if (flag1) {
                GlStateManager.func_187440_b((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

    protected void renderModelCosmical(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag1;
        boolean flag = this.func_193115_c((EntityLivingBase)entitylivingbaseIn);
        boolean bl = flag1 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (flag || flag1) {
            if (!this.bindEntityTextureCosmical(entitylivingbaseIn)) {
                return;
            }
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)((EntityPCosmical)entitylivingbaseIn).shadowDamageR);
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.func_179092_a((int)516, (float)0.003921569f);
            if (((EntityPCosmical)entitylivingbaseIn).getShadowStatus()) {
                this.mainModel2.renderC((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }
            GlStateManager.func_179084_k();
            GlStateManager.func_179092_a((int)516, (float)0.1f);
            GlStateManager.func_179132_a((boolean)true);
        }
    }

    protected abstract ResourceLocation getEntityTextureCosmical(T var1);

    protected boolean bindEntityTextureCosmical(T entity) {
        ResourceLocation resourcelocation = this.getEntityTextureCosmical(entity);
        if (resourcelocation == null) {
            return false;
        }
        this.func_110776_a(resourcelocation);
        return true;
    }

    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
        if (((EntityPCosmical)entitylivingbaseIn).getCloneC()) {
            GlStateManager.func_179139_a((double)1.2, (double)1.2, (double)1.2);
        }
    }

    protected float prepareScaleCosmical(T entitylivingbaseIn, float partialTicks) {
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.preRenderCallbackCosmical(entitylivingbaseIn, partialTicks);
        float f = 0.0625f;
        GlStateManager.func_179109_b((float)0.0f, (float)-1.85f, (float)0.0f);
        return 0.0625f;
    }

    protected void preRenderCallbackCosmical(T entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179139_a((double)1.2, (double)1.2, (double)1.2);
    }
}

