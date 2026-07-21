package com.dhanantry.scapeandrunparasites.client.fx;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.ParticleSnowShovel;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleBlizzard extends ParticleSnowShovel {
   private final EntityPlayerSP focus;

   public ParticleBlizzard(World w, double x, double y, double z, double vx, double vy, double vz, EntityPlayerSP focus) {
      super(w, x, y, z, vx, vy, vz);
      this.focus = focus;
      this.field_70544_f = 0.8F + this.field_187136_p.nextFloat() * 0.6F;
      this.field_70545_g = 0.06F;
      this.field_82339_as = 0.95F;
      this.field_190017_n = true;
      this.field_70547_e = 200;
      this.field_187129_i = vx;
      this.field_187130_j = vy;
      this.field_187131_k = vz;
   }

   public void func_189213_a() {
      this.field_187130_j -= 0.02;
      super.func_189213_a();
      if (this.field_187132_l) {
         this.func_187112_i();
      } else {
         if (this.focus != null && this.focus.func_70092_e(this.field_187126_f, this.field_187127_g, this.field_187128_h) > 400.0) {
            this.func_187112_i();
         }
      }
   }
}
