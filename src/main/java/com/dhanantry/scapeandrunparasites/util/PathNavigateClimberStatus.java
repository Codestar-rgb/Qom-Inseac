package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PathNavigateClimberStatus extends PathNavigateGround {
   private BlockPos targetPosition;

   public PathNavigateClimberStatus(EntityLiving entityLivingIn, World worldIn) {
      super(entityLivingIn, worldIn);
   }

   public Path func_179680_a(BlockPos pos) {
      this.targetPosition = pos;
      return super.func_179680_a(pos);
   }

   public Path func_75494_a(Entity entityIn) {
      this.targetPosition = new BlockPos(entityIn);
      return super.func_75494_a(entityIn);
   }

   public boolean func_75497_a(Entity entityIn, double speedIn) {
      Path path = this.func_75494_a(entityIn);
      if (path != null) {
         return this.func_75484_a(path, speedIn);
      } else {
         this.targetPosition = new BlockPos(entityIn);
         this.field_75511_d = speedIn;
         return true;
      }
   }

   public void func_75501_e() {
      if (!this.func_75500_f()) {
         super.func_75501_e();
      } else if (this.targetPosition != null && ((EntityParasiteBase)this.field_75515_a).getParasiteStatus() <= 2) {
         double d0 = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N;
         if (!(this.field_75515_a.func_174831_c(this.targetPosition) >= d0)
            || !(this.field_75515_a.field_70163_u <= this.targetPosition.func_177956_o())
               && !(
                  this.field_75515_a
                        .func_174831_c(
                           new BlockPos(
                              this.targetPosition.func_177958_n(),
                              MathHelper.func_76128_c(this.field_75515_a.field_70163_u),
                              this.targetPosition.func_177952_p()
                           )
                        )
                     >= d0
               )) {
            this.targetPosition = null;
         } else {
            this.field_75515_a
               .func_70605_aq()
               .func_75642_a(this.targetPosition.func_177958_n(), this.targetPosition.func_177956_o(), this.targetPosition.func_177952_p(), this.field_75511_d);
         }
      }
   }
}
