package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSpore extends Particle {
   public static final ResourceLocation WHITE1 = new ResourceLocation("srparasites", "particle/spore/spore_white1");
   public static final ResourceLocation WHITE2 = new ResourceLocation("srparasites", "particle/spore/spore_white2");
   public static final ResourceLocation WHITE3 = new ResourceLocation("srparasites", "particle/spore/spore_white3");
   public static final ResourceLocation WHITE4 = new ResourceLocation("srparasites", "particle/spore/spore_white4");

   public ParticleSpore(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn + 0.0, yCoordIn, zCoordIn + 0.0, xSpeedIn, ySpeedIn, zSpeedIn);
      this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WHITE1.toString()));
      if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WHITE2.toString()));
      } else if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WHITE3.toString()));
      } else if (Math.random() <= 0.5) {
         this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(WHITE4.toString()));
      }

      this.field_187129_i = xSpeedIn * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.field_187130_j = ySpeedIn * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.field_187131_k = zSpeedIn * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.field_70544_f = 0.0F;
      this.field_70547_e = 200;
      this.field_70545_g = 0.0F;
      float color = (float)(1.0 - Math.random() * 0.5);
      this.field_82339_as = 0.6F;
      this.field_70552_h = color;
      this.field_70553_i = color;
      this.field_70551_j = color;
   }

   public Particle func_70543_e(float multiplier) {
      this.field_187129_i *= multiplier;
      this.field_187130_j *= multiplier;
      this.field_187131_k *= multiplier;
      return this;
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      if (this.field_70544_f < 0.7 && this.field_70546_d < 175) {
         this.field_70544_f += 0.05F;
      }

      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }

      if (this.field_70546_d >= 175) {
         this.field_70544_f -= 0.0278F;
         this.field_82339_as -= 0.01F;
      }

      this.field_187130_j = this.field_187130_j - 0.04 * this.field_70545_g;
      this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
      this.field_187129_i *= 0.98F;
      this.field_187130_j += 1.0E-16;
      this.field_187131_k *= 0.98F;
      if (this.field_187132_l) {
         this.field_187129_i *= 0.7F;
         this.field_187131_k *= 0.7F;
         this.field_187130_j += 1.0E-5;
      }
   }

   public int func_70537_b() {
      return 1;
   }

   public boolean func_187111_c() {
      return false;
   }
}
