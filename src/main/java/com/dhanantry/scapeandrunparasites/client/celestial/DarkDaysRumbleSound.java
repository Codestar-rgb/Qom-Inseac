package com.dhanantry.scapeandrunparasites.client.celestial;

import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;

public class DarkDaysRumbleSound extends MovingSound {
   public DarkDaysRumbleSound() {
      super(SRPSounds.DARK_DAYS_RUMBLE, SoundCategory.AMBIENT);
      this.field_147659_g = true;
      this.field_147665_h = 0;
      this.field_147662_b = 0.75F;
      this.field_147663_c = 1.0F;
      this.field_147666_i = AttenuationType.NONE;
   }

   public void func_73660_a() {
      Minecraft mc = Minecraft.func_71410_x();
      EntityPlayerSP player = mc.field_71439_g;
      if (player != null && BlackSkyClient.isDarkDaysActive()) {
         this.field_147660_d = (float)player.field_70165_t;
         this.field_147661_e = (float)player.field_70163_u;
         this.field_147658_f = (float)player.field_70161_v;
      } else {
         this.field_147668_j = true;
      }
   }
}
