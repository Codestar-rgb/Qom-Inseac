/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.head.ModelInfVillagerHead;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfVillagerHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfVillagerHead
extends RenderSRP<EntityInfVillagerHead> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/villagerh.png");
    public static final ResourceLocation TEXTURE1 = new ResourceLocation("srparasites:textures/entity/monster/villagerh1.png");

    public RenderInfVillagerHead(RenderManager manager) {
        super(manager, new ModelInfVillagerHead(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityInfVillagerHead entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE1;
            }
        }
        return TEXTURE;
    }

    protected void applyRotations(EntityInfVillagerHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

