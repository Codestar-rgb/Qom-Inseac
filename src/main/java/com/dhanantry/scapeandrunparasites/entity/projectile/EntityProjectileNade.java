package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileNade extends EntitySRPProjectile {
   private int duration;
   private int fuse;

   public EntityProjectileNade(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntityProjectileNade(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, int fuse, int duration) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
      this.fuse = fuse;
      this.duration = duration;
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.SLIME;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         if (this.field_70235_a == null) {
            return;
         }

         if (!this.field_70235_a.func_70089_S()) {
            return;
         }

         EntityNade nade = new EntityNade(this.field_70170_p, this.fuse, this.duration);
         nade.setFatherS((EntityParasiteBase)this.field_70235_a);
         nade.func_82149_j(this);
         this.field_70170_p.func_72838_d(nade);
         this.func_70106_y();
      }
   }
}
