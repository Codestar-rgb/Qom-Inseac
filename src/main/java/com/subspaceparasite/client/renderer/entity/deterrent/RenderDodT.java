/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.deterrent;

import com.subspaceparasite.client.model.entity.deterrent.ModelDodT;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.deterrent.EntityDodT;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderDodT
extends RenderMalleable<EntityDodT> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/dodt.png");

    public RenderDodT(RenderManager manager) {
        super(manager, new ModelDodT(), 0.4f);
    }

    protected ResourceLocation getEntityTexture(EntityDodT entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityDodT entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

