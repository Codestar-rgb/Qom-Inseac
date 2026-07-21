package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleMultipleGore extends Particle {
   public static final ResourceLocation ASSIMILATED1 = new ResourceLocation("srparasites", "particle/gore/flesh_assimilated1");
   public static final ResourceLocation ASSIMILATED2 = new ResourceLocation("srparasites", "particle/gore/flesh_assimilated2");
   public static final ResourceLocation ASSIMILATED3 = new ResourceLocation("srparasites", "particle/gore/flesh_assimilated3");
   public static final ResourceLocation PURE1 = new ResourceLocation("srparasites", "particle/gore/flesh_pure1");
   public static final ResourceLocation PURE2 = new ResourceLocation("srparasites", "particle/gore/flesh_pure2");
   public static final ResourceLocation PURE3 = new ResourceLocation("srparasites", "particle/gore/flesh_pure3");
   public static final ResourceLocation PRIMITIVE1 = new ResourceLocation("srparasites", "particle/gore/flesh_primitive1");
   public static final ResourceLocation PRIMITIVE2 = new ResourceLocation("srparasites", "particle/gore/flesh_primitive2");
   public static final ResourceLocation PRIMITIVE3 = new ResourceLocation("srparasites", "particle/gore/flesh_primitive3");
   public static final ResourceLocation ADAPTED1 = new ResourceLocation("srparasites", "particle/gore/flesh_adapted1");
   public static final ResourceLocation ADAPTED2 = new ResourceLocation("srparasites", "particle/gore/flesh_adapted2");
   public static final ResourceLocation ADAPTED3 = new ResourceLocation("srparasites", "particle/gore/flesh_adapted3");
   public static final ResourceLocation VOMIT1 = new ResourceLocation("srparasites", "particle/vomit/vomit1");
   public static final ResourceLocation VOMIT2 = new ResourceLocation("srparasites", "particle/vomit/vomit2");
   public static final ResourceLocation VOMIT3 = new ResourceLocation("srparasites", "particle/vomit/vomit3");
   public static final ResourceLocation VOMIT4 = new ResourceLocation("srparasites", "particle/vomit/vomit4");
   public static final ResourceLocation VOMIT5 = new ResourceLocation("srparasites", "particle/vomit/vomit5");
   public static final ResourceLocation VOMIT6 = new ResourceLocation("srparasites", "particle/vomit/vomit6");

   protected ParticleMultipleGore(
      World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int texture
   ) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      if (texture == 0) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(ASSIMILATED1.toString()));
         if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(ASSIMILATED2.toString()));
         } else if (Math.random() <= 0.25) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(ASSIMILATED3.toString()));
         }
      } else if (texture == 1) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PURE1.toString()));
         if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PURE2.toString()));
         } else if (Math.random() <= 0.25) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PURE3.toString()));
         }
      } else if (texture == 2) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PRIMITIVE1.toString()));
         if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PRIMITIVE2.toString()));
         } else if (Math.random() <= 0.25) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PRIMITIVE3.toString()));
         }
      } else if (texture == 3) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(ADAPTED1.toString()));
         if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(ADAPTED2.toString()));
         } else if (Math.random() <= 0.25) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(ADAPTED3.toString()));
         }
      } else if (texture == 4) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(VOMIT1.toString()));
         if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(VOMIT2.toString()));
         } else if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(VOMIT3.toString()));
         } else if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(VOMIT4.toString()));
         } else if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(VOMIT5.toString()));
         } else if (Math.random() <= 0.5) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(VOMIT6.toString()));
         }
      }

      this.field_187129_i = xSpeedIn;
      this.field_187130_j = ySpeedIn;
      this.field_187131_k = zSpeedIn;
      this.func_187115_a(0.01F, 0.01F);
      this.field_70545_g = 0.04F;
      this.field_70547_e = 20 * (this.field_187136_p.nextInt(3) + 1);
   }

   public void func_189213_a() {
      int age = this.field_70546_d++;
      float maxAge = this.field_70547_e;
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      this.field_187130_j = this.field_187130_j - this.field_70545_g;
      this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
      this.field_187129_i *= 0.98F;
      this.field_187130_j *= 0.98F;
      this.field_187131_k *= 0.98F;
      if (maxAge - age <= 5.0F & this.field_82339_as >= 0.0F) {
         this.field_82339_as -= 0.05F;
      }

      if (this.field_70547_e-- <= 0) {
         this.func_187112_i();
      }

      if (this.field_187132_l) {
         this.field_187129_i *= 0.7F;
         this.field_187131_k *= 0.7F;
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
