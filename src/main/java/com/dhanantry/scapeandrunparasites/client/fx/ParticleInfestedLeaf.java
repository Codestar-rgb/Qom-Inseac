/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.client.fx;

import com.dhanantry.scapeandrunparasites.client.fx.ClientSRPParticles;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ParticleInfestedLeaf
extends Particle {
    private float yaw;
    private float pitch;
    private float rollVel;
    private float sway;
    private float swaySpeed;
    private float swayAmp;

    public ParticleInfestedLeaf(World w, double x, double y, double z) {
        super(w, x, y, z);
        int idx = w.field_73012_v.nextInt(ClientSRPParticles.INFESTED_LEAF_SPRITES.length);
        this.func_187117_a(ClientSRPParticles.INFESTED_LEAF_SPRITES[idx]);
        this.field_70544_f = 5.6f + this.field_187136_p.nextFloat() * 0.6f;
        this.field_70547_e = 80 + this.field_187136_p.nextInt(80);
        this.field_70545_g = 0.02f;
        this.field_187129_i = (this.field_187136_p.nextDouble() - 0.5) * 0.01;
        this.field_187131_k = (this.field_187136_p.nextDouble() - 0.5) * 0.01;
        this.field_187130_j = -0.01 - this.field_187136_p.nextDouble() * 0.01;
        this.yaw = (float)(this.field_187136_p.nextDouble() * Math.PI * 2.0);
        this.pitch = (float)((this.field_187136_p.nextDouble() * 0.7 - 0.35) * Math.PI * 0.25);
        this.rollVel = (this.field_187136_p.nextFloat() - 0.5f) * 0.12f;
        this.sway = this.field_187136_p.nextFloat() * (float)Math.PI * 2.0f;
        this.swaySpeed = 0.15f + this.field_187136_p.nextFloat() * 0.15f;
        this.swayAmp = 0.02f + this.field_187136_p.nextFloat() * 0.02f;
        this.field_190017_n = true;
    }

    public void func_189213_a() {
        this.field_190015_G = this.field_190014_F;
        this.field_190014_F += this.rollVel;
        this.sway += this.swaySpeed;
        double flutterX = Math.cos(this.sway) * (double)this.swayAmp;
        double flutterZ = Math.sin(this.sway * 1.3f) * ((double)this.swayAmp * 0.8);
        this.field_187129_i += flutterX * 0.2;
        this.field_187131_k += flutterZ * 0.2;
        this.yaw += (float)(Math.sin((double)this.sway * 0.7) * 0.003);
        this.pitch += (float)(Math.cos((double)this.sway * 0.9) * 0.003);
        this.field_187130_j -= 0.002 + 0.001 * Math.sin((double)this.sway * 0.5);
        if (this.field_187130_j < -0.06) {
            this.field_187130_j = -0.06;
        }
        super.func_189213_a();
        if (this.field_187133_m || this.field_187132_l) {
            this.func_187112_i();
            return;
        }
        this.field_187129_i *= 0.96;
        this.field_187131_k *= 0.96;
    }

    public void func_180434_a(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        if (this.field_187119_C == null) {
            return;
        }
        double x = this.field_187123_c + (this.field_187126_f - this.field_187123_c) * (double)partialTicks - field_70556_an;
        double y = this.field_187124_d + (this.field_187127_g - this.field_187124_d) * (double)partialTicks - field_70554_ao;
        double z = this.field_187125_e + (this.field_187128_h - this.field_187125_e) * (double)partialTicks - field_70555_ap;
        float s = 0.1f * this.field_70544_f * 0.5f;
        float roll = this.field_190015_G + (this.field_190014_F - this.field_190015_G) * partialTicks;
        float cy = (float)Math.cos(this.yaw);
        float sy = (float)Math.sin(this.yaw);
        float cp = (float)Math.cos(this.pitch);
        float sp = (float)Math.sin(this.pitch);
        float cr = (float)Math.cos(roll);
        float sr = (float)Math.sin(roll);
        float rx = cr * cy + sr * sp * sy;
        float ry = sr * cp;
        float rz = -sr * sy + cr * sp * cy;
        float ux = -sr * cy + cr * sp * sy;
        float uy = cr * cp;
        float uz = sr * sy + cr * sp * cy;
        double x1 = x - (double)(rx *= s) - (double)(ux *= s);
        double y1 = y - (double)(ry *= s) - (double)(uy *= s);
        double z1 = z - (double)(rz *= s) - (double)(uz *= s);
        double x2 = x - (double)rx + (double)ux;
        double y2 = y - (double)ry + (double)uy;
        double z2 = z - (double)rz + (double)uz;
        double x3 = x + (double)rx + (double)ux;
        double y3 = y + (double)ry + (double)uy;
        double z3 = z + (double)rz + (double)uz;
        double x4 = x + (double)rx - (double)ux;
        double y4 = y + (double)ry - (double)uy;
        double z4 = z + (double)rz - (double)uz;
        float u0 = this.field_187119_C.func_94209_e();
        float u1 = this.field_187119_C.func_94212_f();
        float v0 = this.field_187119_C.func_94206_g();
        float v1 = this.field_187119_C.func_94210_h();
        int light = this.func_189214_a(partialTicks);
        int lightU = light >> 16 & 0xFFFF;
        int lightV = light & 0xFFFF;
        int a = (int)(this.field_82339_as * 255.0f);
        int r = (int)(this.field_70552_h * 255.0f);
        int g = (int)(this.field_70553_i * 255.0f);
        int b = (int)(this.field_70551_j * 255.0f);
        buffer.func_181662_b(x1, y1, z1).func_187315_a((double)u1, (double)v1).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
        buffer.func_181662_b(x2, y2, z2).func_187315_a((double)u1, (double)v0).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
        buffer.func_181662_b(x3, y3, z3).func_187315_a((double)u0, (double)v0).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
        buffer.func_181662_b(x4, y4, z4).func_187315_a((double)u0, (double)v1).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
    }

    public int func_70537_b() {
        return 1;
    }
}

