/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelNULL;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWaveShock;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWaveShock
extends RenderLiving<EntityWaveShock> {
    public static final ResourceLocation TEXTURESS = new ResourceLocation("srparasites:textures/entity/monster/tendrilshyco.png");

    public RenderWaveShock(RenderManager manager) {
        super(manager, (ModelBase)new ModelNULL(), 0.0f);
    }

    protected ResourceLocation getEntityTexture(EntityWaveShock entity) {
        return TEXTURESS;
    }
}

