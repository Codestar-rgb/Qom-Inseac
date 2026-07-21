package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ForgeHooks;

public class EntityAINearestAttackableTargetStatus<T extends EntityLivingBase> extends EntityAIBase {
   protected Class<T> targetClass;
   protected final EntityParasiteBase taskOwner;
   private int targetChance;
   protected EntityAINearestAttackableTargetStatus.Sorter sorter;
   protected Predicate<? super T> targetEntitySelector;
   protected T targetEntity;
   protected double sneakPPP;
   protected float inviPPP;
   private int targetUnseenTicks;
   private boolean nearbyOnly;
   private int targetSearchStatus;
   private int targetSearchDelay;
   protected int unseenMemoryTicks = 60;
   protected boolean shouldCheckSight;

   public EntityAINearestAttackableTargetStatus(EntityParasiteBase creature, Class<T> classTarget, boolean checkSight) {
      this(creature, classTarget, checkSight, false);
   }

   public EntityAINearestAttackableTargetStatus(EntityParasiteBase creature, Class<T> classTarget, boolean checkSight, boolean onlyNearby) {
      this(creature, classTarget, 10, checkSight, onlyNearby, (Predicate<? super T>)null, 0.8F, 0.7F);
   }

   public EntityAINearestAttackableTargetStatus(
      EntityParasiteBase creature,
      Class<T> classTarget,
      int chance,
      boolean checkSight,
      boolean onlyNearby,
      @Nullable final Predicate<? super T> targetSelector,
      double sneakP,
      float inviP
   ) {
      this.taskOwner = creature;
      this.shouldCheckSight = checkSight;
      this.nearbyOnly = onlyNearby;
      this.targetClass = classTarget;
      this.targetChance = chance;
      this.sorter = new EntityAINearestAttackableTargetStatus.Sorter(creature);
      this.func_75248_a(1);
      this.targetEntitySelector = new Predicate<T>() {
         public boolean apply(@Nullable T p_apply_1_) {
            if (p_apply_1_ == null) {
               return false;
            } else if (targetSelector != null && !targetSelector.apply(p_apply_1_)) {
               return false;
            } else {
               return !EntitySelectors.field_180132_d.apply(p_apply_1_) ? false : EntityAINearestAttackableTargetStatus.this.isSuitableTarget(p_apply_1_, true);
            }
         }
      };
      this.sneakPPP = sneakP;
      this.inviPPP = inviP;
   }

   public boolean func_75250_a() {
      if ((this.targetChance <= 0 || this.taskOwner.func_70681_au().nextInt(this.targetChance) == 0) && this.taskOwner.getParasiteStatus() == 0) {
         if (this.targetClass != EntityPlayer.class && this.targetClass != EntityPlayerMP.class) {
            List<T> list = this.taskOwner
               .field_70170_p
               .func_175647_a(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);
            if (list.isEmpty()) {
               return false;
            } else {
               Collections.sort(list, this.sorter);
               this.targetEntity = list.get(0);
               EntityLivingBase entitylivingbase = this.taskOwner.func_70638_az();
               return entitylivingbase == null ? true : !entitylivingbase.func_70089_S();
            }
         } else {
            this.targetEntity = (T)this.getNearest(
               this.taskOwner.field_70165_t,
               this.taskOwner.field_70163_u + this.taskOwner.func_70047_e(),
               this.taskOwner.field_70161_v,
               this.getTargetDistance(),
               this.getTargetDistance(),
               this.targetEntitySelector
            );
            return this.targetEntity != null;
         }
      } else {
         return false;
      }
   }

   public boolean func_75253_b() {
      EntityLivingBase entitylivingbase = this.taskOwner.func_70638_az();
      if (entitylivingbase == null) {
         return false;
      } else if (!entitylivingbase.func_70089_S()) {
         return false;
      } else {
         Team team = this.taskOwner.func_96124_cp();
         Team team1 = entitylivingbase.func_96124_cp();
         if (team != null && team1 == team) {
            return false;
         } else {
            double d0 = this.getTargetDistance();
            if (this.taskOwner.func_70068_e(entitylivingbase) > d0 * d0) {
               return false;
            } else {
               if (this.shouldCheckSight || !this.taskOwner.getGeneMod(2)) {
                  if (this.taskOwner.func_70635_at().func_75522_a(entitylivingbase)) {
                     this.targetUnseenTicks = 0;
                  } else if (++this.targetUnseenTicks > this.unseenMemoryTicks) {
                     return false;
                  }
               }

               if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).field_71075_bZ.field_75102_a) {
                  return false;
               } else {
                  this.taskOwner.func_70624_b(entitylivingbase);
                  return true;
               }
            }
         }
      }
   }

   private EntityPlayer getNearest(
      double posX, double posY, double posZ, double maxXZDistance, double maxYDistance, @Nullable Predicate<EntityPlayer> predicate
   ) {
      double d0 = -1.0;
      EntityPlayer entityplayer = null;

      for (int j2 = 0; j2 < this.taskOwner.field_70170_p.field_73010_i.size(); j2++) {
         EntityPlayer entityplayer1 = (EntityPlayer)this.taskOwner.field_70170_p.field_73010_i.get(j2);
         if (!entityplayer1.field_71075_bZ.field_75102_a
            && entityplayer1.func_70089_S()
            && !entityplayer1.func_175149_v()
            && (predicate == null || predicate.apply(entityplayer1))) {
            double d1 = entityplayer1.func_70092_e(posX, entityplayer1.field_70163_u, posZ);
            double d2 = maxXZDistance;
            if (entityplayer1.func_70093_af()) {
               d2 = maxXZDistance * this.sneakPPP;
            }

            if (entityplayer1.func_82150_aj()) {
               float f = entityplayer1.func_82243_bO();
               if (f < 0.1F && this.inviPPP != 1.0F) {
                  f = 0.1F;
               }

               d2 *= this.inviPPP * f;
            }

            d2 = ForgeHooks.getPlayerVisibilityDistance(entityplayer1, d2, maxYDistance);
            if ((maxYDistance < 0.0 || Math.abs(entityplayer1.field_70163_u - posY) < maxYDistance * maxYDistance)
               && (maxXZDistance < 0.0 || d1 < d2 * d2)
               && (d0 == -1.0 || d1 < d0)) {
               d0 = d1;
               entityplayer = entityplayer1;
            }
         }
      }

      return entityplayer;
   }

   protected AxisAlignedBB getTargetableArea(double targetDistance) {
      return this.taskOwner.func_174813_aQ().func_72314_b(targetDistance, 4.0, targetDistance);
   }

   public void func_75249_e() {
      double d0 = this.getTargetDistance();
      if (this.taskOwner.func_70068_e(this.targetEntity) > d0 * d0) {
         this.targetEntity = null;
      }

      this.taskOwner.func_70624_b(this.targetEntity);
      this.targetSearchStatus = 0;
      this.targetSearchDelay = 0;
      this.targetUnseenTicks = 0;
   }

   protected boolean isSuitableTarget(@Nullable EntityLivingBase target, boolean includeInvincibles) {
      if (!isSuitableTarget(this.taskOwner, target, includeInvincibles, this.shouldCheckSight || !this.taskOwner.getGeneMod(2))) {
         return false;
      } else if (!this.taskOwner.func_180485_d(new BlockPos(target))) {
         return false;
      } else {
         if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
               this.targetSearchStatus = 0;
            }

            if (this.targetSearchStatus == 0) {
               this.targetSearchStatus = this.canEasilyReach(target) ? 1 : 2;
            }

            if (this.targetSearchStatus == 2) {
               return false;
            }
         }

         return true;
      }
   }

   protected double getTargetDistance() {
      IAttributeInstance iattributeinstance = this.taskOwner.func_110148_a(SharedMonsterAttributes.field_111265_b);
      return iattributeinstance == null ? 16.0 : iattributeinstance.func_111126_e();
   }

   public static boolean isSuitableTarget(EntityLiving attacker, @Nullable EntityLivingBase target, boolean includeInvincibles, boolean checkSight) {
      if (target == null) {
         return false;
      } else if (target == attacker) {
         return false;
      } else if (!target.func_70089_S()) {
         return false;
      } else if (!attacker.func_70686_a(target.getClass())) {
         return false;
      } else if (attacker.func_184191_r(target)) {
         return false;
      } else {
         if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).func_184753_b() != null) {
            if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).func_184753_b().equals(((IEntityOwnable)target).func_184753_b())) {
               return false;
            }

            if (target == ((IEntityOwnable)attacker).func_70902_q()) {
               return false;
            }
         } else if (target instanceof EntityPlayer && !includeInvincibles && ((EntityPlayer)target).field_71075_bZ.field_75102_a) {
            return false;
         }

         return !checkSight || attacker.func_70635_at().func_75522_a(target);
      }
   }

   private boolean canEasilyReach(EntityLivingBase target) {
      this.targetSearchDelay = 10 + this.taskOwner.func_70681_au().nextInt(5);
      Path path = this.taskOwner.func_70661_as().func_75494_a(target);
      if (path == null) {
         return false;
      } else {
         PathPoint pathpoint = path.func_75870_c();
         if (pathpoint == null) {
            return false;
         } else {
            int i = pathpoint.field_75839_a - MathHelper.func_76128_c(target.field_70165_t);
            int j = pathpoint.field_75838_c - MathHelper.func_76128_c(target.field_70161_v);
            return i * i + j * j <= 2.25;
         }
      }
   }

   public static class Sorter implements Comparator<Entity> {
      private final Entity entity;

      public Sorter(Entity entityIn) {
         this.entity = entityIn;
      }

      public int compare(Entity p_compare_1_, Entity p_compare_2_) {
         double d0 = this.entity.func_70068_e(p_compare_1_);
         double d1 = this.entity.func_70068_e(p_compare_2_);
         if (d0 < d1) {
            return -1;
         } else {
            return d0 > d1 ? 1 : 0;
         }
      }
   }
}
