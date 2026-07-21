package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleRHappy extends Particle {
   public static final ResourceLocation REDHAPPY = new ResourceLocation("srparasites", "particle/rhappy");

   protected ParticleRHappy(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(REDHAPPY.toString()));
      this.field_70544_f = 2.0F;
      this.field_70547_e = 40;
      if (ySpeedIn == 0.0 && (xSpeedIn != 0.0 || zSpeedIn != 0.0)) {
         this.field_187129_i = xSpeedIn;
         this.field_187130_j = ySpeedIn + 0.1;
         this.field_187131_k = zSpeedIn;
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
