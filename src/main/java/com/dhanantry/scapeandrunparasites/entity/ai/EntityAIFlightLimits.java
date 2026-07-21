package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class EntityAIFlightLimits extends EntityAIBase {
   private final EntityParasiteBase parent;
   private int limit;
   private boolean fLimit;

   public EntityAIFlightLimits(EntityParasiteBase in, int limit, boolean flighLimit) {
      this.parent = in;
      this.limit = limit;
      this.fLimit = flighLimit;
   }

   public boolean func_75250_a() {
      return true;
   }

   public void func_75246_d() {
      if (this.fLimit) {
         if (this.parent.func_70638_az() != null) {
            if (this.parent.func_70638_az().field_70163_u + this.limit > this.parent.field_70163_u) {
               this.parent.field_70181_x -= 0.04;
            }
         } else {
            boolean ground = this.howMuchNeg(this.parent.func_180425_c().func_177979_c(1), 1);
            if (ground) {
               this.parent.field_70181_x -= 0.04;
            }
         }
      } else {
         boolean ground = this.howMuchPos(this.parent.func_180425_c().func_177979_c(1), 1);
         if (ground) {
            this.parent.field_70181_x += 0.04;
         }
      }
   }

   private boolean howMuchNeg(BlockPos y, int count) {
      if (y.func_177956_o() < 1) {
         return false;
      } else if (count <= this.limit) {
         return this.parent.field_70170_p.func_180495_p(y).func_177230_c() == Blocks.field_150350_a ? this.howMuchNeg(y.func_177977_b(), ++count) : false;
      } else {
         return count > this.limit ? true : true;
      }
   }

   private boolean howMuchPos(BlockPos y, int count) {
      if (y.func_177956_o() < 1) {
         return false;
      } else if (count <= this.limit) {
         return this.parent.field_70170_p.func_180495_p(y).func_177230_c() == Blocks.field_150350_a ? this.howMuchPos(y.func_177977_b(), ++count) : true;
      } else {
         return count <= this.limit;
      }
   }
}
