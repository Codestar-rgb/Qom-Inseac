package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleFlash extends Particle {
   public static final ResourceLocation FLASH = new ResourceLocation("srparasites", "particle/flash");
   private final int R;
   private final int G;
   private final int B;

   protected ParticleFlash(
      World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int r, int g, int b
   ) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, r, g, b);
      this.R = r;
      this.G = g;
      this.B = b;
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FLASH.toString()));
      this.field_70544_f = 6.0F;
      this.field_70547_e = 8;
      if (ySpeedIn != 0.0 || xSpeedIn != 0.0 || zSpeedIn != 0.0) {
         this.field_187129_i = xSpeedIn;
         this.field_187130_j = ySpeedIn;
         this.field_187131_k = zSpeedIn;
      }
   }

   public int func_189214_a(float p_189214_1_) {
      int i = super.func_189214_a(p_189214_1_);
      return 240 | i << 16;
   }

   public void func_189213_a() {
      int age = this.field_70546_d++;
      float rot = 20.0F;
      if (age >= this.field_70547_e) {
         this.func_187112_i();
      } else {
         this.field_70544_f = 8.0F / (age * 0.5F);
         this.field_82339_as = age / 0.1F;
         this.field_190014_F = (float)(Math.sin(rot * age) * Math.cos(rot * age));
         this.field_70552_h = age * this.R;
         this.field_70551_j = age * this.B;
         this.field_70553_i = age * this.G;
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
