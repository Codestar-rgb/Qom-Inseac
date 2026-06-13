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
package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMudo
extends RenderLiving<EntityMudo> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/mudo.png");
    public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/mudov.png");
    public static final ResourceLocation TEXTUREB = new ResourceLocation("srparasites:textures/entity/monster/mudob.png");
    public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/smudo.png");

    public RenderMudo(RenderManager manager) {
        super(manager, (ModelBase)new ModelMudo(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityMudo entity) {
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

    protected void applyRotations(EntityMudo entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

