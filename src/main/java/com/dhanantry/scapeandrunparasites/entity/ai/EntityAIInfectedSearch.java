package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIInfectedSearch extends EntityAIBase {
   private final EntityParasiteBase parent;
   private World world;
   private int parentID;
   private double moveSpeed;
   private EntityParasiteBase[] array = new EntityParasiteBase[4];
   private int delay;

   public EntityAIInfectedSearch(EntityParasiteBase animal, double speedIn) {
      this.parent = animal;
      this.world = animal.field_70170_p;
      this.moveSpeed = speedIn;
      this.parentID = animal.func_145782_y();
   }

   public boolean func_75250_a() {
      if (this.parent.field_70173_aa % 40 == 0) {
         return false;
      } else {
         return this.parent.func_70638_az() != null ? false : this.LeshCount();
      }
   }

   public void func_75251_c() {
   }

   public void func_75246_d() {
      if (this.parent.field_70173_aa % 20 == 0 && !(this.parent.getKillC() < 0.0)) {
         int count = 0;
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.parent.field_70165_t,
               this.parent.field_70163_u,
               this.parent.field_70161_v,
               this.parent.field_70165_t + 1.0,
               this.parent.field_70163_u + 1.0,
               this.parent.field_70161_v + 1.0
            )
            .func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
         List<EntityPInfected> moblist = this.parent.field_70170_p.func_72872_a(EntityPInfected.class, axisalignedbb);

         for (EntityLesh mob : this.parent.field_70170_p.func_72872_a(EntityLesh.class, axisalignedbb)) {
            if (mob != null) {
               count++;
            }
         }

         if (count >= 3) {
            EntityCanMelt meh = (EntityCanMelt)this.parent;
            meh.melt();
         } else {
            for (EntityPInfected mobx : moblist) {
               if (mobx != null && mobx != this.parent && mobx instanceof EntityCanMelt && this.parent.func_70685_l(mobx) && mobx.func_70638_az() == null) {
                  EntityCanMelt meh = (EntityCanMelt)mobx;
                  if (!meh.isMelting()) {
                     count++;
                     meh.melt();
                     if (count >= 3) {
                        EntityCanMelt moh = (EntityCanMelt)this.parent;
                        moh.melt();
                        return;
                     }
                  }
               }
            }

            EntityCanMelt out = (EntityCanMelt)this.parent;
            out.melt();
         }
      }
   }

   protected boolean LeshCount() {
      int countL = 0;
      int countI = 0;
      boolean more = false;
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            this.parent.field_70165_t,
            this.parent.field_70163_u,
            this.parent.field_70161_v,
            this.parent.field_70165_t + 1.0,
            this.parent.field_70163_u + 1.0,
            this.parent.field_70161_v + 1.0
         )
         .func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());

      for (EntityParasiteBase mob : this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
         if (mob != null) {
            if (mob instanceof EntityLesh && this.parent.func_70685_l(mob)) {
               EntityLesh meh = (EntityLesh)mob;
               countL += meh.getTimes();
            }

            if (mob != this.parent && this.parent.func_70685_l(mob) && mob instanceof EntityCanMelt && mob.func_70638_az() == null) {
               EntityCanMelt meh = (EntityCanMelt)mob;
               if (!meh.isMelting()) {
                  countI++;
               }
            }
         }
      }

      if (countL >= 1 && countL <= 3) {
         EntityCanMelt meh = (EntityCanMelt)this.parent;
         meh.melt();
         return false;
      } else {
         return countI >= 3;
      }
   }
}
