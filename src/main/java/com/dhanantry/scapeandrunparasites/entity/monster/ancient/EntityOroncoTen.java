package com.dhanantry.scapeandrunparasites.entity.monster.ancient;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityOroncoTen extends EntityParasiteBase {
   private int ticksGround;
   private int maxMobs;

   public EntityOroncoTen(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.0F, 0.7F);
      this.field_70714_bg.func_85156_a(this.aiWander);
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70158_ak = true;
      this.canD = SRPConfig.ancientdespawn;
      this.killcount = -10.0;
      this.type = 62;
   }

   @Override
   public int getParasiteIDRegister() {
      return 35;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ORONCO_HEALTH * 0.25);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ORONCO_ARMOR * 0.25);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70122_E) {
         this.ticksGround++;
         if (this.ticksGround > 200 && !this.field_70170_p.field_72995_K) {
            if (this.ticksGround % 20 == 0 && this.nearbydangerous() && this.maxMobs < 10) {
               if (ParasiteSummon.SummonM(this, new String[]{"srparasites:lodo;1;1"}, 2, 3, this.func_70638_az())) {
                  this.maxMobs++;
               } else if (this.maxMobs >= 10) {
                  this.func_70106_y();
               }
            }
         } else if (this.field_70170_p.field_72995_K && this.ticksGround > 200) {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 91, 81, 75);
            this.spawnParticles(SRPEnumParticle.GCLOUD, 164, 174, 180);
            this.spawnParticles(SRPEnumParticle.GCLOUD, 91, 81, 75);
            this.spawnParticles(SRPEnumParticle.GCLOUD, 164, 174, 180);
         }
      }
   }

   private boolean nearbydangerous() {
      int k = 0;
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
         )
         .func_186662_g(16.0);

      for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
         if (mob != this && mob instanceof EntityParasiteBase && mob.func_70089_S()) {
            k--;
         } else if (mob.func_70089_S()) {
            k++;
         }
      }

      return k > 0;
   }

   public void func_180430_e(float distance, float damageMultiplier) {
   }

   public float func_70047_e() {
      return 0.5F;
   }
}
