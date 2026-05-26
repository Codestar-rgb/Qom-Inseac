/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.inborn;

import com.subspaceparasite.client.model.entity.inborn.ModelNuuh;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.inborn.EntityNuuh;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderNuuh
extends RenderMalleable<EntityNuuh> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/nuuh.png");
    public static final ResourceLocation TEXTUREV = new ResourceLocation("subspaceparasite:textures/entity/monster/nuuhv.png");
    public static final ResourceLocation TEXTUREB = new ResourceLocation("subspaceparasite:textures/entity/monster/nuuhb.png");
    public static final ResourceLocation STEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/snuuh.png");

    public RenderNuuh(RenderManager manager) {
        super(manager, new ModelNuuh(), 0.7f);
    }

    protected ResourceLocation getEntityTexture(EntityNuuh entity) {
        switch (entity.getSkin()) {
            case 5: {
                return TEXTUREV;
            }
            case 6: {
                return TEXTUREB;
            }
            case 120: {
                return STEXTURE;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityNuuh entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

