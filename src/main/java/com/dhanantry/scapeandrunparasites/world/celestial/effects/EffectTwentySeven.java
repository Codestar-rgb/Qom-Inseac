package com.dhanantry.scapeandrunparasites.world.celestial.effects;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.world.celestial.ICelestialEventEffect;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

public class EffectTwentySeven implements ICelestialEventEffect {
   private static final int DURATION = 12000;

   @Override
   public void onNightStart(World world, int dim, int phase, long nightIndex) {
      for (EntityParasiteBase parasite : world.func_175644_a(EntityParasiteBase.class, EntitySelectors.field_94557_a)) {
         parasite.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 12000, 1, false, false));
      }
   }

   @Override
   public void onParasiteSpawn(EntityParasiteBase parasite, @Nullable EntityLivingBase spawner) {
      parasite.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 12000, 1, false, false));
   }
}
