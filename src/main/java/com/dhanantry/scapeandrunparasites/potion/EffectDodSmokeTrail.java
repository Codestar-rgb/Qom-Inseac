package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

public class EffectDodSmokeTrail extends SRPEffectBase {
   public EffectDodSmokeTrail() {
      super("dod_smoke_trail", false, 4210752, 0, 0);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (entity.field_70170_p instanceof WorldServer) {
         int remaining = entity.func_70660_b(SRPPotions.DOD_SMOKE_TRAIL_E).func_76459_b();
         if (entity.field_70122_E && remaining <= 10) {
            entity.func_184596_c(SRPPotions.DOD_SMOKE_TRAIL_E);
         } else {
            WorldServer ws = (WorldServer)entity.field_70170_p;
            double x = entity.field_70165_t;
            double y = entity.field_70163_u + entity.func_70047_e();
            double z = entity.field_70161_v;
            ws.func_180505_a(EnumParticleTypes.SMOKE_NORMAL, false, x, y, z, 6, 0.15, 0.15, 0.15, 0.02, new int[0]);
         }
      }
   }

   @Override
   public boolean func_76397_a(int duration, int amplifier) {
      return true;
   }
}
