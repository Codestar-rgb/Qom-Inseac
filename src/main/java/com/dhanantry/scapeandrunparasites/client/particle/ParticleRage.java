package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleRage extends Particle {
   public static final ResourceLocation RAGE1 = new ResourceLocation("srparasites", "particle/rage1");
   public static final ResourceLocation RAGE2 = new ResourceLocation("srparasites", "particle/rage2");
   public static final ResourceLocation RAGE3 = new ResourceLocation("srparasites", "particle/rage3");

   protected ParticleRage(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(RAGE1.toString()));
      if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(RAGE2.toString()));
      } else if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(RAGE3.toString()));
      }

      this.field_70544_f = 2.0F;
      this.field_70547_e = 30;
      if (ySpeedIn != 0.0 || xSpeedIn != 0.0 || zSpeedIn != 0.0) {
         this.field_187129_i = xSpeedIn;
         this.field_187130_j = ySpeedIn;
         this.field_187131_k = zSpeedIn;
      }
   }

   public int func_189214_a(float p_189214_1_) {
      float f = (this.field_70546_d + p_189214_1_) / this.field_70547_e;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      int i = super.func_189214_a(p_189214_1_);
      int j = i & 0xFF;
      int k = i >> 16 & 0xFF;
      j += (int)(f * 15.0F * 16.0F);
      if (j > 240) {
         j = 240;
      }

      return j | k << 16;
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      int age = this.field_70546_d++;
      float mow = (float)(Math.random() - Math.random());
      float meow = (float)((Math.random() - Math.random()) * 0.1);
      float mreow = (float)(Math.random() * 0.2);
      this.field_82339_as -= 0.05F;
      this.field_70544_f -= 0.08F;
      this.field_70552_h = 1.0F - mreow;
      this.field_70553_i = 1.0F - mreow;
      this.field_70551_j = 1.0F - mreow;
      this.field_187127_g -= 0.01F + meow;
      this.field_187126_f += meow;
      this.field_187128_h += meow;
      this.field_190014_F = 0.01F * age * mow;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
