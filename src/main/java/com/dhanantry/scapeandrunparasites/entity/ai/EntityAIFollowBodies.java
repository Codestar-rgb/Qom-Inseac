package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanHaveBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;

public class EntityAIFollowBodies extends EntityAIBase {
   private final EntityParasiteBase parent;
   private final EntityCanHaveBodies worm;
   private int digCool;
   private int digTicks;
   private double push;
   private double movementV;
   private boolean check;
   private byte checkMove;
   private double evolutionNeed;
   private int digSkillCooldown;
   private int digSkill;
   EntityCanHaveBodies follo;
   EntityLivingBase follo2;

   public EntityAIFollowBodies(EntityParasiteBase in, double pushDistance, double movement, int digCooldown, double evolution, int digSkillCool) {
      this.parent = in;
      this.worm = (EntityCanHaveBodies)in;
      this.digCool = digCooldown;
      this.push = pushDistance;
      this.movementV = movement;
      this.check = false;
      this.checkMove = 0;
      this.evolutionNeed = evolution;
      this.digSkillCooldown = digSkillCool;
   }

   public boolean func_75250_a() {
      return this.parent.func_70089_S();
   }

   public void func_75246_d() {
      this.digSkill++;
      if (this.worm.getFollowing() != null) {
         this.followHead();
      } else {
         this.diggingTeleport();
      }
   }

   private void followHead() {
      EntityCanHaveBodies follo = (EntityCanHaveBodies)this.getFather(this.worm.getFollowing());
      if (follo == null) {
         this.worm.setFollowing(null);
         this.parent.func_70106_y();
      } else {
         EntityLivingBase follo2 = (EntityLivingBase)follo.getEntity();
         if (!follo2.func_70089_S()) {
            this.parent.func_70097_a(DamageSource.field_76377_j, 1000000.0F);
         } else {
            this.parent.setKillC(follo.getKillPoints());
            if (this.parent.getKillC() > this.evolutionNeed && ParasiteEventEntity.canSpawnNext) {
               this.parent.func_70106_y();
            }

            this.parent.targetNewCool = 10;
            this.parent.aiWander.setExecutionValues(999999, 2.0F);
            this.parent.setParasiteStatus(-77);
            double dis = this.parent.func_70068_e(follo2);
            double checkDis = this.push;
            boolean flag = follo.getDigging();
            if (this.parent.srpTicks == 10) {
               this.worm.setDigging(flag);
               this.worm.bodyPartEffect();
            }

            if (this.worm.getBodiesT() - 1 >= this.worm.getBodyNumber()) {
               checkDis = 0.2;
            }

            if (dis > checkDis * checkDis) {
               double str = 0.19;
               if (flag) {
                  if (dis > 0.25) {
                     this.parent.lookAt(follo2);
                  }
               } else {
                  this.parent.lookAt(follo2);
               }

               if (dis > 9.0) {
                  this.parent.func_82149_j(follo2);
               }

               double deltaX = follo2.field_70165_t - this.parent.field_70165_t;
               double deltaY = follo2.field_70163_u - this.parent.field_70163_u;
               double deltaZ = follo2.field_70161_v - this.parent.field_70161_v;
               double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
               if (distance == 0.0) {
                  return;
               }

               deltaX /= distance;
               deltaY /= distance;
               deltaZ /= distance;
               this.parent.field_70159_w += deltaX * str;
               this.parent.field_70181_x += deltaY * str;
               this.parent.field_70179_y += deltaZ * str;
               this.parent.setWorkTask(false);
            }
         }
      }
   }

   private void diggingTeleport() {
      if (this.parent.getKillC() > this.evolutionNeed && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this.parent, this.worm.getEvolution(this.parent.field_70170_p), true, true);
      }

      if (this.parent.srpTicks == 10) {
         if (this.digCool > 0) {
            this.digCool--;
            if (this.digCool == 3) {
               this.parent.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.movementV);
               this.digSkill = 0;
            }

            return;
         }

         if (this.parent.field_70165_t == this.parent.field_70169_q && this.parent.field_70161_v == this.parent.field_70166_s) {
            this.checkMove++;
            if (this.checkMove >= 2 && !this.worm.getDigging()) {
               this.parent.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.movementV);
            }
         }

         if (this.worm.getDigging()) {
            this.parent.func_70661_as().func_75499_g();
            this.parent.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
            this.parent.setParasiteStatus(-5);
            this.worm.setDigging(true);
            this.digTicks++;
            if (this.digTicks > this.worm.getBodyLength() + 2) {
               if (this.worm.getTargetPos() != null) {
                  if (this.parent.func_70638_az() != null) {
                     this.worm.setTargetPos(this.parent.func_70638_az().func_180425_c());
                  }

                  if (ParasiteEventEntity.teleportDigging(this.parent, 10.0F, this.worm.getTargetPos(), 4, 1)) {
                     this.worm.setTargetPos(null);
                  }
               }

               if (this.digTicks > this.worm.getBodyLength() * 2 + 2) {
                  this.digTicks = 0;
                  this.digCool = 5;
                  this.worm.setDigging(false);
                  this.parent.setParasiteStatus(0);
               }
            }
         } else if (this.parent.func_70638_az() != null && this.parent.field_70122_E) {
            double distanceEn = this.parent.func_70068_e(this.parent.func_70638_az());
            if ((distanceEn > 196.0 || !this.parent.func_70685_l(this.parent.func_70638_az()) && distanceEn > 49.0) && this.digSkill > this.digSkillCooldown) {
               this.digSkill = 0;
               this.worm.setDigging(true);
               this.worm.setTargetPos(this.parent.func_70638_az().func_180425_c());
            }
         } else {
            this.worm.setDigging(false);
            this.worm.setTargetPos(null);
         }
      }
   }

   private Entity getFather(UUID uuid) {
      List<Entity> serverList = this.parent.field_70170_p.field_72996_f;

      for (int x = 0; x < serverList.size(); x++) {
         if (uuid.equals(serverList.get(x).func_110124_au())) {
            return serverList.get(x);
         }
      }

      return null;
   }
}
