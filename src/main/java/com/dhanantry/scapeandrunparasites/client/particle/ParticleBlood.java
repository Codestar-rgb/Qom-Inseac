package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleBlood extends Particle {
   public static final ResourceLocation BLOOD1 = new ResourceLocation("srparasites", "particle/blood1");
   public static final ResourceLocation BLOOD2 = new ResourceLocation("srparasites", "particle/blood2");
   public static final ResourceLocation BLOOD_LAND = new ResourceLocation("srparasites", "particle/blood_land");
   private final int type;
   private final float mroew = (float)((Math.random() - Math.random()) * 0.05);

   protected ParticleBlood(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int r) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.type = r;
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BLOOD1.toString()));
      this.field_70544_f = 2.0F;
      this.field_70547_e = 40;
      this.field_82339_as = 0.9F;
      if (this.type == 1) {
         this.field_187129_i = this.mroew;
         this.field_187130_j = ySpeedIn;
         this.field_187131_k = this.mroew;
      }
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      int age = this.field_70546_d++;
      this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
      if (this.type == 0) {
         this.field_187130_j -= 0.5;
      }

      if (this.type == 1) {
         this.func_187114_a(80);
         this.field_187123_c = this.field_187126_f;
         this.field_187124_d = this.field_187127_g;
         this.field_187125_e = this.field_187128_h;
         this.field_187130_j = 0.31;
         this.field_187129_i = this.field_187129_i + this.mroew * 0.5;
         this.field_187131_k = this.field_187131_k + this.mroew * 0.5;
         this.field_70545_g = 0.04F;
      }

      if (age >= 4) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BLOOD2.toString()));
      }

      if (this.field_187132_l) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(BLOOD_LAND.toString()));
         this.field_187129_i /= 10.0;
         this.field_187131_k /= 10.0;
      }

      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }
   }

   public int func_70537_b() {
      return 1;
   }
}
