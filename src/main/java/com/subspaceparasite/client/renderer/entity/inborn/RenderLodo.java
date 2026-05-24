/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.inborn;

import com.subspaceparasite.client.model.entity.inborn.ModelLodo;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLodo
extends RenderLiving<EntityLodo> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/lodo.png");
    public static final ResourceLocation STEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/slodo.png");

    public RenderLodo(RenderManager manager) {
        super(manager, (ModelBase)new ModelLodo(), 0.2f);
    }

    protected ResourceLocation getEntityTexture(EntityLodo entity) {
        switch (entity.getSkin()) {
            case 120: {
                return STEXTURE;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityLodo entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

