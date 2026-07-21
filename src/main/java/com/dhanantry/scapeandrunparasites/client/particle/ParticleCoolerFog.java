package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleCoolerFog extends Particle {
   private float speedHor;
   private float speedVer;
   private float ang;
   public static final ResourceLocation FOG_INTRO1 = new ResourceLocation("srparasites", "particle/fog/fog_intro1");
   public static final ResourceLocation FOG_INTRO2 = new ResourceLocation("srparasites", "particle/fog/fog_intro2");
   public static final ResourceLocation FOG_INTRO3 = new ResourceLocation("srparasites", "particle/fog/fog_intro3");
   public static final ResourceLocation FOG_INTRO4 = new ResourceLocation("srparasites", "particle/fog/fog_intro4");
   public static final ResourceLocation FOG_INTRO5 = new ResourceLocation("srparasites", "particle/fog/fog_intro5");
   public static final ResourceLocation FOG1 = new ResourceLocation("srparasites", "particle/fog/fog1");
   public static final ResourceLocation FOG2 = new ResourceLocation("srparasites", "particle/fog/fog2");
   public static final ResourceLocation FOG3 = new ResourceLocation("srparasites", "particle/fog/fog3");
   public static final ResourceLocation FOG4 = new ResourceLocation("srparasites", "particle/fog/fog4");

   protected ParticleCoolerFog(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO1.toString()));
      this.field_70544_f = (float)(10.0 + Math.random() * 0.5);
      this.field_70547_e = 144;
      this.field_82339_as = 0.9F;
      float sXZ = 0.01F;
      this.speedHor = (float)(Math.random() * sXZ - Math.random() * sXZ);
      float sY = 0.005F;
      this.speedVer = (float)(Math.random() * sY - Math.random() * sY);
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      int age = this.field_70546_d++;
      this.field_187127_g = this.field_187127_g - this.speedVer;
      this.field_187126_f = this.field_187126_f + this.speedHor;
      this.field_187128_h = this.field_187128_h + this.speedHor;
      if (age >= 8) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO2.toString()));
      }

      if (age >= 16) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO3.toString()));
      }

      if (age >= 24) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO4.toString()));
      }

      if (age >= 32) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO5.toString()));
      }

      if (age >= 40) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG1.toString()));
      }

      if (age >= 48) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG2.toString()));
      }

      if (age >= 56) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG3.toString()));
      }

      if (age >= 64) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG4.toString()));
      }

      if (age >= 72) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG1.toString()));
      }

      if (age >= 80) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG2.toString()));
      }

      if (age >= 88) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG3.toString()));
      }

      if (age >= 96) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG4.toString()));
      }

      if (age >= 104) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO5.toString()));
      }

      if (age >= 112) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO4.toString()));
      }

      if (age >= 120) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO3.toString()));
      }

      if (age >= 128) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO2.toString()));
      }

      if (age >= 136) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(FOG_INTRO1.toString()));
      }

      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
