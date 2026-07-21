package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAIAttackMeleeRanged extends EntityAIBase {
   private final EntityParasiteBase parentEntity;
   private double minDistance;
   private double maxDistance;
   private double prepDistance;
   private boolean pulling;
   private float str;

   public EntityAIAttackMeleeRanged(EntityParasiteBase in, double minD, double maxD, double prepD, boolean pull, float str) {
      this.parentEntity = in;
      this.minDistance = minD;
      this.maxDistance = maxD;
      this.prepDistance = prepD;
      this.pulling = pull;
      this.str = str;
   }

   public boolean func_75250_a() {
      return this.parentEntity.func_70638_az() != null;
   }

   public void func_75251_c() {
      this.parentEntity.setParasiteStatus(0);
   }

   public void func_75246_d() {
      if (this.parentEntity.func_70638_az() == null) {
         this.func_75251_c();
      } else {
         EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
         if (entitylivingbase.func_70068_e(this.parentEntity) < this.prepDistance * this.prepDistance && this.parentEntity.func_70685_l(entitylivingbase)) {
            this.parentEntity.setParasiteStatus(15);
            if (entitylivingbase.func_70068_e(this.parentEntity) < this.maxDistance * this.maxDistance
               && entitylivingbase.func_70068_e(this.parentEntity) > this.minDistance * this.minDistance
               && this.parentEntity.field_70173_aa % 20 == 0) {
               Vec3d vec3d = this.parentEntity.func_70676_i(1.0F);
               float f = (float)MathHelper.func_181159_b(
                  entitylivingbase.field_70161_v - this.parentEntity.field_70161_v, entitylivingbase.field_70165_t - this.parentEntity.field_70165_t
               );
               EntityDamage entityevokerfangs = new EntityDamage(
                  this.parentEntity.field_70170_p,
                  entitylivingbase.field_70165_t,
                  entitylivingbase.field_70163_u,
                  entitylivingbase.field_70161_v,
                  f,
                  this.parentEntity,
                  1.0F,
                  this.pulling,
                  1.0F
               );
               this.parentEntity.field_70170_p.func_72838_d(entityevokerfangs);
            }
         } else {
            this.parentEntity.setParasiteStatus(0);
         }
      }
   }
}
