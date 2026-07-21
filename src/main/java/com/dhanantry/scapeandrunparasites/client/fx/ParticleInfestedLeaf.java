package com.dhanantry.scapeandrunparasites.client.fx;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ParticleInfestedLeaf extends Particle {
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
      this.field_70544_f = 5.6F + this.field_187136_p.nextFloat() * 0.6F;
      this.field_70547_e = 80 + this.field_187136_p.nextInt(80);
      this.field_70545_g = 0.02F;
      this.field_187129_i = (this.field_187136_p.nextDouble() - 0.5) * 0.01;
      this.field_187131_k = (this.field_187136_p.nextDouble() - 0.5) * 0.01;
      this.field_187130_j = -0.01 - this.field_187136_p.nextDouble() * 0.01;
      this.yaw = (float)(this.field_187136_p.nextDouble() * Math.PI * 2.0);
      this.pitch = (float)((this.field_187136_p.nextDouble() * 0.7 - 0.35) * Math.PI * 0.25);
      this.rollVel = (this.field_187136_p.nextFloat() - 0.5F) * 0.12F;
      this.sway = this.field_187136_p.nextFloat() * (float) Math.PI * 2.0F;
      this.swaySpeed = 0.15F + this.field_187136_p.nextFloat() * 0.15F;
      this.swayAmp = 0.02F + this.field_187136_p.nextFloat() * 0.02F;
      this.field_190017_n = true;
   }

   public void func_189213_a() {
      this.field_190015_G = this.field_190014_F;
      this.field_190014_F = this.field_190014_F + this.rollVel;
      this.sway = this.sway + this.swaySpeed;
      double flutterX = Math.cos(this.sway) * this.swayAmp;
      double flutterZ = Math.sin(this.sway * 1.3F) * (this.swayAmp * 0.8);
      this.field_187129_i += flutterX * 0.2;
      this.field_187131_k += flutterZ * 0.2;
      this.yaw = this.yaw + (float)(Math.sin(this.sway * 0.7) * 0.003);
      this.pitch = this.pitch + (float)(Math.cos(this.sway * 0.9) * 0.003);
      this.field_187130_j = this.field_187130_j - (0.002 + 0.001 * Math.sin(this.sway * 0.5));
      if (this.field_187130_j < -0.06) {
         this.field_187130_j = -0.06;
      }

      super.func_189213_a();
      if (!this.field_187133_m && !this.field_187132_l) {
         this.field_187129_i *= 0.96;
         this.field_187131_k *= 0.96;
      } else {
         this.func_187112_i();
      }
   }

   public void func_180434_a(
      BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ
   ) {
      if (this.field_187119_C != null) {
         double x = this.field_187123_c + (this.field_187126_f - this.field_187123_c) * partialTicks - field_70556_an;
         double y = this.field_187124_d + (this.field_187127_g - this.field_187124_d) * partialTicks - field_70554_ao;
         double z = this.field_187125_e + (this.field_187128_h - this.field_187125_e) * partialTicks - field_70555_ap;
         float s = 0.1F * this.field_70544_f * 0.5F;
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
         rx *= s;
         ry *= s;
         rz *= s;
         ux *= s;
         uy *= s;
         uz *= s;
         double x1 = x - rx - ux;
         double y1 = y - ry - uy;
         double z1 = z - rz - uz;
         double x2 = x - rx + ux;
         double y2 = y - ry + uy;
         double z2 = z - rz + uz;
         double x3 = x + rx + ux;
         double y3 = y + ry + uy;
         double z3 = z + rz + uz;
         double x4 = x + rx - ux;
         double y4 = y + ry - uy;
         double z4 = z + rz - uz;
         float u0 = this.field_187119_C.func_94209_e();
         float u1 = this.field_187119_C.func_94212_f();
         float v0 = this.field_187119_C.func_94206_g();
         float v1 = this.field_187119_C.func_94210_h();
         int light = this.func_189214_a(partialTicks);
         int lightU = light >> 16 & 65535;
         int lightV = light & 65535;
         int a = (int)(this.field_82339_as * 255.0F);
         int r = (int)(this.field_70552_h * 255.0F);
         int g = (int)(this.field_70553_i * 255.0F);
         int b = (int)(this.field_70551_j * 255.0F);
         buffer.func_181662_b(x1, y1, z1).func_187315_a(u1, v1).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
         buffer.func_181662_b(x2, y2, z2).func_187315_a(u1, v0).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
         buffer.func_181662_b(x3, y3, z3).func_187315_a(u0, v0).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
         buffer.func_181662_b(x4, y4, z4).func_187315_a(u0, v1).func_181669_b(r, g, b, a).func_187314_a(lightU, lightV).func_181675_d();
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
