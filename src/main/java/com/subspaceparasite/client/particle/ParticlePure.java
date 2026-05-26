/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.world.World
 */
package com.subspaceparasite.client.particle;

import com.subspaceparasite.client.particle.SPParticleRegistry;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;

public class ParticlePure
extends Particle {
    public static final int KIND_WAVE = 0;
    public static final int KIND_PULSE = 1;
    private final double cx;
    private final double cy;
    private final double cz;
    private final int kind;
    private double angle;
    private double radius;
    private double omega;
    private double up;
    private double out;
    private float startScale;

    public ParticlePure(World world, double x, double y, double z, int kind, TextureAtlasSprite sprite) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.cx = x;
        this.cy = y;
        this.cz = z;
        this.kind = kind;
        this.field_70547_e = 22 + this.field_187136_p.nextInt(14);
        this.field_190017_n = false;
        this.field_70545_g = 0.0f;
        this.field_82339_as = 0.95f;
        this.angle = this.field_187136_p.nextDouble() * Math.PI * 2.0;
        this.radius = 0.15 + this.field_187136_p.nextDouble() * 0.25;
        this.omega = 0.2 + this.field_187136_p.nextDouble() * 0.2;
        this.up = 0.01 + this.field_187136_p.nextDouble() * 0.02;
        this.out = 0.07 + this.field_187136_p.nextDouble() * 0.05;
        this.startScale = 0.9f + this.field_187136_p.nextFloat() * 0.5f;
        this.field_70544_f = 0.0f;
        this.func_187117_a(sprite);
        this.field_187126_f += (this.field_187136_p.nextDouble() - 0.5) * 0.05;
        this.field_187127_g += this.field_187136_p.nextDouble() * 0.03;
        this.field_187128_h += (this.field_187136_p.nextDouble() - 0.5) * 0.05;
    }

    public void func_189213_a() {
        TextureAtlasSprite alt;
        int age;
        this.field_187123_c = this.field_187126_f;
        this.field_187124_d = this.field_187127_g;
        this.field_187125_e = this.field_187128_h;
        if ((age = this.field_70546_d++) >= this.field_70547_e) {
            this.func_187112_i();
            return;
        }
        float t = (float)age / (float)this.field_70547_e;
        float easeIn = (float)Math.min(1.0, (double)t * 2.0);
        float easeOut = (float)(1.0 - Math.max(0.0, (double)(t - 0.5f) * 2.0));
        this.field_70544_f = this.startScale * easeIn * easeOut;
        this.field_82339_as = 0.95f * easeOut + 0.05f;
        if (age % 4 == 0 && this.field_187136_p.nextBoolean() && (alt = SPParticleRegistry.randPureSprite(this.field_187136_p)) != null) {
            this.func_187117_a(alt);
        }
        if (this.kind == 0) {
            this.angle += this.omega;
            this.radius *= 0.992;
            double sx = Math.cos(this.angle) * this.radius;
            double sz = Math.sin(this.angle) * this.radius;
            double wob = Math.sin((double)age * 0.2 + this.angle) * 0.01;
            this.field_187126_f = this.cx + sx + wob;
            this.field_187128_h = this.cz + sz - wob;
            this.field_187127_g += this.up;
        } else {
            double theta = this.angle;
            double r = (double)age * this.out;
            double curl = Math.sin((double)age * 0.25) * 0.06;
            double sx = Math.cos(theta) * r - Math.sin(theta) * curl;
            double sz = Math.sin(theta) * r + Math.cos(theta) * curl;
            this.field_187126_f = this.cx + sx;
            this.field_187128_h = this.cz + sz;
            this.field_187127_g = this.cy + Math.sin((double)age * 0.25) * 0.03;
        }
    }

    public int func_70537_b() {
        return 1;
    }
}

