package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleBiomass extends Particle {
   public static final ResourceLocation BIOMASS1 = new ResourceLocation("srparasites", "particle/biomass1");
   public static final ResourceLocation BIOMASS2 = new ResourceLocation("srparasites", "particle/biomass2");
   public static final ResourceLocation BIOMASS3 = new ResourceLocation("srparasites", "particle/biomass3");
   public static final ResourceLocation BIOMASS4 = new ResourceLocation("srparasites", "particle/biomass4");

   protected ParticleBiomass(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      ParticleSpawner.spawnParticle(SRPEnumParticle.DOT, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0, 165, 255, 0);
      ParticleSpawner.spawnParticle(SRPEnumParticle.DOT, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0, 60, 229, 27);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BIOMASS1.toString()));
      if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BIOMASS2.toString()));
      } else if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BIOMASS3.toString()));
      } else if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BIOMASS4.toString()));
      }

      this.field_70544_f = 3.0F;
      this.field_70547_e = 45;
      this.field_187129_i = xSpeedIn;
      this.field_187130_j = ySpeedIn;
      this.field_187131_k = zSpeedIn;
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
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }

      if (this.field_70546_d >= 25) {
         this.field_70544_f -= 0.1F;
         this.field_70552_h -= 0.03F;
         this.field_70553_i -= 0.02F;
         this.field_70551_j -= 0.025F;
      }

      this.field_187130_j += 0.005;
      this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
      this.field_187129_i *= 0.98F;
      this.field_187130_j *= 0.98F;
      this.field_187131_k *= 0.98F;
      if (this.field_187132_l) {
         this.field_187129_i *= 0.7F;
         this.field_187131_k *= 0.7F;
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
