package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIGetFollowers extends EntityAIBase {
   private final EntityParasiteBase parent;
   private int version;
   private int searchRange;

   public EntityAIGetFollowers(EntityParasiteBase in, int mobVersion, int range) {
      this.parent = in;
      this.version = mobVersion;
      this.searchRange = range;
   }

   public boolean func_75250_a() {
      return this.parent.field_70173_aa % 20 == 0 && this.parent.getParasiteFollowing() == null && this.parent.func_70638_az() == null;
   }

   public void func_75246_d() {
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            this.parent.field_70165_t,
            this.parent.field_70163_u,
            this.parent.field_70161_v,
            this.parent.field_70165_t + 1.0,
            this.parent.field_70163_u + 1.0,
            this.parent.field_70161_v + 1.0
         )
         .func_72314_b(this.searchRange, 2.0, this.searchRange);
      List<EntityParasiteBase> moblist = this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
      switch (this.version) {
         case 1:
            for (EntityParasiteBase mobxxx : moblist) {
               if (mobxxx != this.parent && this.parent.func_70685_l(mobxxx) && mobxxx.func_70089_S() && mobxxx.getParasiteType() < 31) {
                  if (mobxxx.getParasiteFollowing() == null) {
                     mobxxx.setParasiteToFollow(this.parent);
                     break;
                  }

                  if (mobxxx.getParasiteFollowing().getParasiteType() <= 10) {
                     mobxxx.setParasiteToFollow(this.parent);
                     break;
                  }
               }
            }
            break;
         case 2:
            for (EntityParasiteBase mobxx : moblist) {
               if (mobxx != this.parent && this.parent.func_70685_l(mobxx) && mobxx.func_70089_S() && mobxx.getParasiteType() < 41) {
                  if (mobxx.getParasiteFollowing() == null) {
                     mobxx.setParasiteToFollow(this.parent);
                     break;
                  }

                  if (mobxx.getParasiteFollowing().getParasiteType() <= 30) {
                     mobxx.setParasiteToFollow(this.parent);
                     break;
                  }
               }
            }
            break;
         case 3:
            for (EntityParasiteBase mobx : moblist) {
               if (mobx != this.parent && this.parent.func_70685_l(mobx) && mobx.func_70089_S() && mobx.getParasiteType() < 41) {
                  if (mobx.getParasiteFollowing() == null) {
                     mobx.setParasiteToFollow(this.parent);
                     break;
                  }

                  if (mobx.getParasiteFollowing().getParasiteType() <= 40) {
                     mobx.setParasiteToFollow(this.parent);
                     break;
                  }
               }
            }
            break;
         case 4:
            for (EntityParasiteBase mob : moblist) {
               if (mob != this.parent && this.parent.func_70685_l(mob) && mob.func_70089_S() && mob.getParasiteType() < 61) {
                  if (mob.getParasiteFollowing() == null) {
                     mob.setParasiteToFollow(this.parent);
                     break;
                  }

                  if (mob.getParasiteFollowing().getParasiteType() <= 60) {
                     mob.setParasiteToFollow(this.parent);
                     break;
                  }
               }
            }
      }
   }
}
