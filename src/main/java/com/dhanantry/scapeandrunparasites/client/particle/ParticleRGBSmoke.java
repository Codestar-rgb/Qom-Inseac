package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleRGBSmoke extends Particle {
   float oSize;

   protected ParticleRGBSmoke(
      World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i1221_8_, double p_i1221_10_, double p_i1221_12_, int r, int g, int b
   ) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      float f = 2.5F;
      this.field_187129_i *= 0.1F;
      this.field_187130_j *= 0.1F;
      this.field_187131_k *= 0.1F;
      this.field_187129_i += p_i1221_8_;
      this.field_187130_j += p_i1221_10_;
      this.field_187131_k += p_i1221_12_;
      this.field_70552_h = r / 255.0F;
      this.field_70553_i = g / 255.0F;
      this.field_70551_j = b / 255.0F;
      this.field_70544_f *= 0.75F;
      this.field_70544_f *= 2.5F;
      this.oSize = this.field_70544_f * 0.5F;
      this.field_70547_e = (int)(8.0 / (Math.random() * 0.8 + 0.3));
      this.field_70547_e = (int)(this.field_70547_e * 2.5F);
   }

   public void func_180434_a(
      BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ
   ) {
      float f = (this.field_70546_d + partialTicks) / this.field_70547_e * 32.0F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      this.field_70544_f = this.oSize * f;
      super.func_180434_a(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }

      this.func_70536_a(7 - this.field_70546_d * 8 / this.field_70547_e);
      this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
      this.field_187129_i *= 0.96F;
      this.field_187130_j *= 0.96F;
      this.field_187131_k *= 0.96F;
      EntityPlayer entityplayer = this.field_187122_b.func_184137_a(this.field_187126_f, this.field_187127_g, this.field_187128_h, 2.0, false);
      if (entityplayer != null) {
         AxisAlignedBB axisalignedbb = entityplayer.func_174813_aQ();
         if (this.field_187127_g > axisalignedbb.field_72338_b) {
            this.field_187127_g = this.field_187127_g + (axisalignedbb.field_72338_b - this.field_187127_g) * 0.2;
            this.field_187130_j = this.field_187130_j + (entityplayer.field_70181_x - this.field_187130_j) * 0.2;
            this.func_187109_b(this.field_187126_f, this.field_187127_g, this.field_187128_h);
         }
      }

      if (this.field_187132_l) {
         this.field_187129_i *= 0.7F;
         this.field_187131_k *= 0.7F;
      }
   }
}
