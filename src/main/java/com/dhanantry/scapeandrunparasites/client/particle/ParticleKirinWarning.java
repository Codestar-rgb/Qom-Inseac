/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleKirinWarning
extends Particle {
    private static final ResourceLocation TEX = new ResourceLocation("srparasites", "textures/particle/kirin_warning.png");
    private final float sizeBlocks;
    private final float yaw;
    private final int max;

    public ParticleKirinWarning(World world, double x, double y, double z, float sizeBlocks, float yawRad, int maxAgeTicks) {
        super(world, x, y, z);
        this.sizeBlocks = sizeBlocks;
        this.yaw = yawRad;
        this.field_70547_e = this.max = Math.max(1, maxAgeTicks);
        this.field_190017_n = false;
        this.field_70545_g = 0.0f;
        this.field_187131_k = 0.0;
        this.field_187130_j = 0.0;
        this.field_187129_i = 0.0;
        this.field_82339_as = 1.0f;
        this.func_70538_b(1.0f, 1.0f, 1.0f);
    }

    public int func_70537_b() {
        return 3;
    }

    public void func_189213_a() {
        this.field_187123_c = this.field_187126_f;
        this.field_187124_d = this.field_187127_g;
        this.field_187125_e = this.field_187128_h;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_187112_i();
        }
    }

    public void func_180434_a(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEX);
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
        GlStateManager.func_179129_p();
        double x = this.field_187126_f - field_70556_an;
        double y = this.field_187127_g - field_70554_ao;
        double z = this.field_187128_h - field_70555_ap;
        GlStateManager.func_179137_b((double)x, (double)y, (double)z);
        GlStateManager.func_179114_b((float)((float)Math.toDegrees(this.yaw)), (float)0.0f, (float)1.0f, (float)0.0f);
        float half = this.sizeBlocks * 0.5f;
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder buf = tess.func_178180_c();
        buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        buf.func_181662_b((double)(-half), 0.0, (double)(-half)).func_187315_a(0.0, 0.0).func_181669_b(255, 255, 255, (int)(255.0f * this.field_82339_as)).func_181675_d();
        buf.func_181662_b((double)(-half), 0.0, (double)half).func_187315_a(0.0, 1.0).func_181669_b(255, 255, 255, (int)(255.0f * this.field_82339_as)).func_181675_d();
        buf.func_181662_b((double)half, 0.0, (double)half).func_187315_a(1.0, 1.0).func_181669_b(255, 255, 255, (int)(255.0f * this.field_82339_as)).func_181675_d();
        buf.func_181662_b((double)half, 0.0, (double)(-half)).func_187315_a(1.0, 0.0).func_181669_b(255, 255, 255, (int)(255.0f * this.field_82339_as)).func_181675_d();
        tess.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
    }
}

