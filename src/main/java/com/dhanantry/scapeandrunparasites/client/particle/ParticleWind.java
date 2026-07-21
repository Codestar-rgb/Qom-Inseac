package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleWind extends Particle {
   public static final ResourceLocation WIND1 = new ResourceLocation("srparasites", "particle/wind/wind1");
   public static final ResourceLocation WIND2 = new ResourceLocation("srparasites", "particle/wind/wind2");
   public static final ResourceLocation WIND3 = new ResourceLocation("srparasites", "particle/wind/wind3");
   public static final ResourceLocation WIND4 = new ResourceLocation("srparasites", "particle/wind/wind4");
   public static final ResourceLocation WIND5 = new ResourceLocation("srparasites", "particle/wind/wind5");
   public static final ResourceLocation WIND6 = new ResourceLocation("srparasites", "particle/wind/wind6");
   public static final ResourceLocation WIND7 = new ResourceLocation("srparasites", "particle/wind/wind7");
   public static final ResourceLocation WIND8 = new ResourceLocation("srparasites", "particle/wind/wind8");
   public static final ResourceLocation WIND9 = new ResourceLocation("srparasites", "particle/wind/wind9");
   public static final ResourceLocation WIND10 = new ResourceLocation("srparasites", "particle/wind/wind10");
   public static final ResourceLocation WIND11 = new ResourceLocation("srparasites", "particle/wind/wind11");
   public static final ResourceLocation WIND12 = new ResourceLocation("srparasites", "particle/wind/wind12");
   public static final ResourceLocation WIND13 = new ResourceLocation("srparasites", "particle/wind/wind13");

   protected ParticleWind(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND1.toString()));
      this.field_70544_f = 2.0F;
      this.field_70547_e = 52;
      this.field_190017_n = true;
      this.field_82339_as = 0.5F;
      if (ySpeedIn != 0.0 || xSpeedIn != 0.0 || zSpeedIn != 0.0) {
         this.field_187129_i = xSpeedIn;
         this.field_187130_j = ySpeedIn;
         this.field_187131_k = zSpeedIn;
      }
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }

      int age = this.field_70546_d++;
      this.field_187127_g = this.field_187127_g + age * this.field_187130_j;
      this.field_187126_f = this.field_187126_f + (this.field_187129_i + (Math.random() * (age * 0.005) - Math.random() * (age * 0.005)));
      this.field_187128_h = this.field_187128_h + (this.field_187131_k + (Math.random() * (age * 0.005) - Math.random() * (age * 0.005)));
      if (age >= 4) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND2.toString()));
      }

      if (age >= 8) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND3.toString()));
      }

      if (age >= 12) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND4.toString()));
      }

      if (age >= 16) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND5.toString()));
      }

      if (age >= 20) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND6.toString()));
      }

      if (age >= 24) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND7.toString()));
      }

      if (age >= 28) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND8.toString()));
      }

      if (age >= 32) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND9.toString()));
      }

      if (age >= 36) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND10.toString()));
      }

      if (age >= 40) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND11.toString()));
      }

      if (age >= 44) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND12.toString()));
      }

      if (age >= 48) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WIND13.toString()));
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
