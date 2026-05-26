/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;

public abstract class RenderSP<T extends EntityParasiteBase>
extends RenderLiving<T> {
    private int ticklag;
    private boolean tickflag;

    public RenderSP(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
        ++this.ticklag;
        if (this.ticklag > 20) {
            this.ticklag = 0;
            this.tickflag = Minecraft.func_71410_x().field_71439_g.func_70644_a(SPPotions.BRAINING_E);
        }
        if (this.tickflag) {
            return false;
        }
        return super.func_177071_a(livingEntity, camera, camX, camY, camZ);
    }
}

