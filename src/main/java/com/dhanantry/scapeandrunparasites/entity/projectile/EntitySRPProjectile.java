package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntitySRPProjectile extends EntityFireball {
   public EntitySRPProjectile(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntitySRPProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
   }

   public boolean func_70027_ad() {
      return false;
   }

   public boolean func_70067_L() {
      return false;
   }

   public boolean func_70097_a(DamageSource source, float amount) {
      return false;
   }

   protected boolean attackEntityAsMobMinimum(Entity entityIn, EntityParasiteBase attacker) {
      if (entityIn == null || attacker == null) {
         return false;
      } else if (!attacker.func_70089_S()) {
         return false;
      } else {
         return entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase)
            ? attacker.attackEntityAsMobMinimum((EntityLivingBase)entityIn, attacker.getMiniDamage())
            : false;
      }
   }
}
