package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileElviaBall extends EntitySRPProjectile {
   public EntityProjectileElviaBall(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntityProjectileElviaBall(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.EXPLOSION_NORMAL;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         if (result.field_72308_g != null && result.field_72308_g instanceof EntityLivingBase) {
            if (result.field_72308_g instanceof EntityParasiteBase && !(result.field_72308_g instanceof EntityNak)) {
               this.func_70106_y();
               return;
            }

            DamageSource damagesource;
            if (this.field_70235_a == null) {
               damagesource = DamageSource.func_76356_a(this, this);
            } else {
               damagesource = DamageSource.func_76356_a(this, this.field_70235_a);
            }

            result.field_72308_g.func_70097_a(damagesource, (float)SRPAttributes.ELVIA_ATTACK_DAMAGE);
            this.attackEntityAsMobMinimum(result.field_72308_g, (EntityParasiteBase)this.field_70235_a);
         }

         this.func_70106_y();
      }
   }
}
