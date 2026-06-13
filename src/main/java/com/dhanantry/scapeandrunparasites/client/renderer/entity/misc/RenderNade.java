/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityNade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class RenderNade
extends Render<EntityNade> {
    protected ModelSRP mainModel2 = new ModelNade();
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/nade.png");

    public RenderNade(RenderManager p_i47208_1_) {
        super(p_i47208_1_);
    }

    protected ResourceLocation getEntityTexture(EntityNade entity) {
        return TEXTURES;
    }

    public void doRender(EntityNade entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.func_76986_a((Entity)entity, x, y, z, entityYaw, partialTicks);
        this.doRenderCosmical(entity, x, y, z, entityYaw, partialTicks);
    }

    public void doRenderCosmical(EntityNade entity, double x, double y, double z, float entityYaw, float partialTicks) {
        boolean shouldSit;
        GlStateManager.func_179094_E();
        GlStateManager.func_179129_p();
        this.mainModel2.field_78093_q = shouldSit = entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit();
        try {
            float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
            float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
            float f2 = f1 - f;
            if (shouldSit && entity.func_184187_bx() instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)entity.func_184187_bx();
                f = this.interpolateRotation(entitylivingbase.field_70760_ar, entitylivingbase.field_70761_aq, partialTicks);
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
            float f7 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
            this.renderLivingAt(entity, x, y, z);
            float f8 = this.handleRotationFloat(entity, partialTicks);
            this.applyRotations(entity, f8, f, partialTicks);
            float f4 = this.prepareScaleCosmical(entity, partialTicks);
            float f5 = 0.0f;
            float f6 = 0.0f;
            if (!entity.func_184218_aH()) {
                f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
                f6 = entity.limbSwing - entity.limbSwingAmount * (1.0f - partialTicks);
                if (f5 > 1.0f) {
                    f5 = 1.0f;
                }
                f2 = f1 - f;
            }
            GlStateManager.func_179141_d();
            this.mainModel2.setLivingAnimations(entity, f6, f5, partialTicks);
            this.mainModel2.func_78087_a(f6, f5, f8, f2, f7, f4, entity);
            this.mainModel2.setRotationAnglesCosmical(f6, f5, f8, f2, f7, f4, entity);
            if (this.field_188301_f) {
                GlStateManager.func_179142_g();
                GlStateManager.func_187431_e((int)this.func_188298_c(entity));
                GlStateManager.func_187417_n();
                GlStateManager.func_179119_h();
            } else {
                this.renderModelCosmical(entity, f6, f5, f8, f2, f7, f4);
                GlStateManager.func_179132_a((boolean)true);
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
    }

    protected void renderModelCosmical(EntityNade entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag1;
        boolean flag = false;
        boolean bl = flag1 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (flag || flag1) {
            if (!this.bindEntityTextureCosmical(entitylivingbaseIn)) {
                return;
            }
            this.mainModel2.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        }
    }

    protected boolean bindEntityTextureCosmical(EntityNade entity) {
        ResourceLocation resourcelocation = this.getEntityTexture(entity);
        if (resourcelocation == null) {
            return false;
        }
        this.func_110776_a(resourcelocation);
        return true;
    }

    protected float prepareScaleCosmical(EntityNade entitylivingbaseIn, float partialTicks) {
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.preRenderCallbackCosmical(entitylivingbaseIn, partialTicks);
        float f = 0.0625f;
        GlStateManager.func_179109_b((float)0.0f, (float)-1.501f, (float)0.0f);
        return 0.0625f;
    }

    protected void preRenderCallbackCosmical(EntityNade entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.1f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        float plusX = entitylivingbaseIn.field_70130_N * 1.4f;
        float plusY = entitylivingbaseIn.field_70131_O * 1.25f;
        GlStateManager.func_179152_a((float)(plusX + f2), (float)(plusY + f3), (float)(plusX + f2));
    }

    protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
        float f;
        for (f = yawOffset - prevYawOffset; f < -180.0f; f += 360.0f) {
        }
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return prevYawOffset + partialTicks * f;
    }

    protected void renderLivingAt(EntityNade entityLivingBaseIn, double x, double y, double z) {
        GlStateManager.func_179109_b((float)((float)x), (float)((float)y), (float)((float)z));
    }

    protected float handleRotationFloat(EntityNade livingBase, float partialTicks) {
        return (float)livingBase.field_70173_aa + partialTicks;
    }

    protected void applyRotations(EntityNade entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        GlStateManager.func_179114_b((float)(180.0f - rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        if (entityLiving.deathTime > 0) {
            float f = ((float)entityLiving.deathTime + partialTicks - 1.0f) / 20.0f * 1.6f;
            if ((f = MathHelper.func_76129_c((float)f)) > 1.0f) {
                f = 1.0f;
            }
            GlStateManager.func_179114_b((float)(f * 90.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        } else {
            String s = TextFormatting.func_110646_a((String)entityLiving.func_70005_c_());
            if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s))) {
                GlStateManager.func_179109_b((float)0.0f, (float)(entityLiving.field_70131_O + 0.1f), (float)0.0f);
                GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
    }
}

