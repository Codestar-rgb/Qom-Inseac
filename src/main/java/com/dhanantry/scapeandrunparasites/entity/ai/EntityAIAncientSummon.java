package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIAncientSummon extends EntityAIBase {
   private final EntityParasiteBase parentEntity;
   private int limit = 0;
   private int sCooldown;
   private int sLimit;
   private String[] sMobs;
   private int attackTimer = 0;
   private int attacking = 0;
   private double targetX;
   private double targetY;
   private double targetZ;

   public EntityAIAncientSummon(EntityParasiteBase ancient, int cooldown, int limit, String[] mobList) {
      this.parentEntity = ancient;
      this.sCooldown = cooldown;
      this.sLimit = limit;
      this.sMobs = mobList;
   }

   public boolean func_75250_a() {
      return this.parentEntity.func_70638_az() != null || this.attacking >= 1;
   }

   public void func_75249_e() {
   }

   public void func_75251_c() {
      this.attackTimer = 0;
      this.limit = 0;
   }

   public void func_75246_d() {
      if (this.attacking >= 1) {
         this.attacking++;
         if (this.attacking == 2) {
            this.parentEntity.func_184185_a(SRPSounds.ANCIENT_POD, 5.0F, 1.0F);
         }

         if (this.attacking % 20 == 0
            && this.attacking >= 40
            && ParasiteSummon.SummonM(this.parentEntity, this.sMobs, 10, this.targetX, this.targetY, this.targetZ, this.parentEntity.func_70638_az(), true)) {
            this.limit++;
         }

         if (this.limit >= this.sLimit) {
            this.attacking = 0;
            this.attackTimer = 0;
            this.limit = 0;
         }
      } else if (this.parentEntity.func_70638_az() == null) {
         this.attackTimer = 0;
      } else if (this.parentEntity.func_70638_az().field_70128_L) {
         this.attackTimer = 0;
      } else {
         EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
         if (this.parentEntity.func_70685_l(entitylivingbase) && this.parentEntity.func_70068_e(entitylivingbase) < 2500.0) {
            if (entitylivingbase.field_70122_E || this.parentEntity.field_70170_p.field_73012_v.nextBoolean()) {
               this.attackTimer++;
            }

            if (this.attackTimer >= this.sCooldown && this.attacking == 0) {
               this.attacking++;
               this.targetX = entitylivingbase.field_70165_t;
               this.targetY = this.parentEntity.field_70163_u + 25.0;
               this.targetZ = entitylivingbase.field_70161_v;
            }
         } else {
            this.attackTimer = 0;
         }
      }
   }
}
