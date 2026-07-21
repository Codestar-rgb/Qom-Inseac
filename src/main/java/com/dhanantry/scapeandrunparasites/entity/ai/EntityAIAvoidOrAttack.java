package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIAvoidOrAttack extends EntityAIBase {
   private final EntityParasiteBase parent;
   private int tickss;
   private float light;
   private int rangexz;
   private int rangey;

   public EntityAIAvoidOrAttack(EntityParasiteBase in, float lightR, int xz, int y) {
      this.parent = in;
      this.tickss = 20;
      this.light = lightR;
      this.rangexz = xz;
      this.rangey = y;
      this.parent.setWorkTask(false);
   }

   public boolean func_75250_a() {
      this.tickss--;
      if (this.tickss < 0) {
         this.tickss = 20;
         return this.parent.getParasiteFollowing() == null;
      } else {
         return false;
      }
   }

   public void func_75246_d() {
      if (this.parent.func_70644_a(SRPPotions.RAGE_E)) {
         this.parent.setWorkTask(true);
      } else {
         if (this.parent.func_70013_c() >= this.light) {
            boolean flag = true;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.parent.field_70165_t,
                  this.parent.field_70163_u,
                  this.parent.field_70161_v,
                  this.parent.field_70165_t + 1.0,
                  this.parent.field_70163_u + 1.0,
                  this.parent.field_70161_v + 1.0
               )
               .func_72314_b(this.rangexz, this.rangey, this.rangexz);

            for (EntityParasiteBase mob : this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
               if (mob != this.parent && this.parent.func_70685_l(mob) && mob.func_70089_S()) {
                  if (flag) {
                     flag = false;
                     this.parent.setWorkTask(true);
                  }

                  if (this.parent.getParasiteFollowing() == null) {
                     if (mob.getParasiteFollowing() == null) {
                        this.parent.setParasiteToFollow(mob);
                     } else if (mob.getParasiteFollowing().getParasiteType() >= 11) {
                        this.parent.setParasiteToFollow(mob.getParasiteFollowing());
                     }
                  }

                  mob.setWorkTask(true);
               }
            }

            if (flag) {
               this.parent.setWorkTask(false);
            }
         } else {
            this.parent.setWorkTask(true);
         }
      }
   }
}
