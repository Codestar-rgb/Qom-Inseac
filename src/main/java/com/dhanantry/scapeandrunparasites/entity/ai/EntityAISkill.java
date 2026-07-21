package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAISkill extends EntityAIBase {
   private final EntityParasiteBase parentEntity;
   private int sCooldown;
   private int attackTimer = 0;
   private int attacking = 0;
   private boolean checV;
   private int distanceC;
   private int distanceL;
   private byte attID;
   private boolean ignoreStatus;

   public EntityAISkill(EntityParasiteBase para, int cooldown, int miniDistance, boolean needVisual, int attackID) {
      this.parentEntity = para;
      this.sCooldown = cooldown;
      this.distanceC = miniDistance * miniDistance;
      this.checV = needVisual;
      this.attID = (byte)attackID;
      this.distanceL = 0;
      this.ignoreStatus = false;
   }

   public EntityAISkill(EntityParasiteBase para, int cooldown, int miniDistance, int maxDistance, boolean needVisual, int attackID) {
      this(para, cooldown, miniDistance, needVisual, attackID);
      this.distanceL = maxDistance * maxDistance;
   }

   public EntityAISkill(EntityParasiteBase para, int cooldown, int miniDistance, int maxDistance, boolean needVisual, int attackID, boolean iS) {
      this(para, cooldown, miniDistance, maxDistance, needVisual, attackID);
      this.ignoreStatus = iS;
   }

   public boolean func_75250_a() {
      if (this.ignoreStatus) {
         return true;
      } else {
         return this.attID != 13 && this.attID != 31
            ? (this.parentEntity.getParasiteStatus() > 0 && this.parentEntity.getParasiteStatus() < 3 || this.attacking >= 1)
               && this.parentEntity.getGeneMod(5)
            : true;
      }
   }

   public void func_75249_e() {
   }

   public void func_75251_c() {
   }

   public void func_75246_d() {
      if (this.attacking >= 1) {
         this.attacking++;
         this.parentEntity.doSpecialSkill(this.attID);
         if (this.parentEntity.getFinished(this.attID)) {
            this.attacking = 0;
            this.attackTimer = 0;
            this.parentEntity.setFinished(this.attID, false);
         }
      } else if (this.parentEntity.func_70638_az() != null) {
         EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
         boolean flag = false;
         double dis = this.parentEntity.func_70068_e(entitylivingbase);
         if (dis < this.distanceC && dis >= this.distanceL) {
            if (this.checV) {
               if (this.parentEntity.func_70685_l(entitylivingbase)) {
                  this.attackTimer++;
               }
            } else {
               this.attackTimer++;
            }
         }

         if (this.parentEntity.func_70644_a(SRPPotions.RAGE_E)) {
            this.attackTimer++;
         }

         if (this.parentEntity.getSkin() == 120 && this.attID == 13) {
            this.attackTimer++;
         }

         if (this.attackTimer >= this.sCooldown) {
            this.attacking++;
         }
      }
   }
}
