package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleEen extends Particle {
   public static final ResourceLocation RED = new ResourceLocation("srparasites", "particle/spore/spore_red1");
   private final float oSize;
   private final double coordX;
   private final double coordY;
   private final double coordZ;

   protected ParticleEen(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(RED.toString()));
      this.field_187129_i = xSpeedIn;
      this.field_187130_j = ySpeedIn;
      this.field_187131_k = zSpeedIn;
      this.coordX = xCoordIn;
      this.coordY = yCoordIn;
      this.coordZ = zCoordIn;
      this.field_187123_c = xCoordIn + xSpeedIn;
      this.field_187124_d = yCoordIn + ySpeedIn;
      this.field_187125_e = zCoordIn + zSpeedIn;
      this.field_187126_f = this.field_187123_c;
      this.field_187127_g = this.field_187124_d;
      this.field_187128_h = this.field_187125_e;
      this.oSize = this.field_70544_f;
      this.field_70547_e = (int)(Math.random() * 10.0) + 30;
   }

   public void func_187110_a(double x, double y, double z) {
      this.func_187108_a(this.func_187116_l().func_72317_d(x, y, z));
      this.func_187118_j();
   }

   public int func_189214_a(float partialTick) {
      int i = super.func_189214_a(partialTick);
      float f = (float)this.field_70546_d / this.field_70547_e;
      f *= f;
      f *= f;
      int j = i & 0xFF;
      int k = i >> 16 & 0xFF;
      k += (int)(f * 15.0F * 16.0F);
      if (k > 240) {
         k = 240;
      }

      return j | k << 16;
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      float f = (float)this.field_70546_d / this.field_70547_e;
      f = 1.0F - f;
      float f1 = 1.0F - f;
      f1 *= f1;
      f1 *= f1;
      this.field_187126_f = this.coordX + this.field_187129_i * f;
      this.field_187127_g = this.coordY + this.field_187130_j * f - f1 * 1.2F;
      this.field_187128_h = this.coordZ + this.field_187131_k * f;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }
   }

   public int func_70537_b() {
      return 1;
   }

   public boolean func_187111_c() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public static class Factory implements IParticleFactory {
      public Particle func_178902_a(
         int particleID,
         World worldIn,
         double xCoordIn,
         double yCoordIn,
         double zCoordIn,
         double xSpeedIn,
         double ySpeedIn,
         double zSpeedIn,
         int... p_178902_15_
      ) {
         return new ParticleEen(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      }
   }
}
