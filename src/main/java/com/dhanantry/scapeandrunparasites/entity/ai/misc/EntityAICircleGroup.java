package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAICircleGroup extends EntityAIBase {
   private final EntityCreature mob;
   private final double speed;
   private final int minGroup;
   private final double minRadius;
   private final double maxRadius;
   private final int scanRadius;
   private final Predicate<? super Entity> sameGroup;
   private float speedMul = 1.0F;
   private double effSpeed = 1.0;
   private float lapTicksBase = 100.0F;
   private double smCenterX;
   private double smCenterZ;
   private double smRadius;
   private double smTargetX;
   private double smTargetY;
   private double smTargetZ;
   private float smYaw;
   private int tickAge = 0;
   private float seedF;
   private float wobbleA = 0.8F;
   private float wobbleF = 0.06F;
   private float wanderA = 0.6F;
   private float wanderF = 0.09F;
   private float angJitterAmp = (float)Math.toRadians(0.6);
   private float angJitterFreq = 0.07F;
   private double centerX;
   private double centerZ;
   private double radius;
   private int dirSign = 0;
   private static final String NBT_RING_DIR = "SRP_RingDir";
   private float myAngle;
   private float angularVel;
   private int recalcCenterTicker = 0;
   private static final int RECALC_CENTER_EVERY = 10;
   private int recalcWaypointTicker = 0;
   private static final int RECALC_WAYPOINT_EVERY = 8;
   private final List<EntityCreature> groupSnapshot = new ArrayList<>();

   public EntityAICircleGroup(
      EntityCreature mob, double speed, int minGroup, double minRadius, double maxRadius, int scanRadius, Predicate<? super Entity> sameGroup
   ) {
      this.mob = mob;
      this.speed = speed;
      this.minGroup = minGroup;
      this.minRadius = minRadius;
      this.maxRadius = maxRadius;
      this.scanRadius = scanRadius;
      this.sameGroup = sameGroup == null ? Predicates.alwaysTrue() : sameGroup;
      this.func_75248_a(3);
   }

   public boolean func_75250_a() {
      if (!this.mob.func_184218_aH() && this.mob.func_70638_az() == null && !this.mob.func_70090_H()) {
         this.snapshotGroup();
         if (this.groupSnapshot.size() < this.minGroup) {
            return false;
         } else {
            this.estimateCenterAndRadius();
            this.assignInitialAngle();
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean func_75253_b() {
      if (this.mob.field_70128_L) {
         return false;
      } else if (this.mob.func_70638_az() != null) {
         return false;
      } else {
         this.snapshotGroup();
         int n = this.groupSnapshot.size();
         return n >= Math.max(2, this.minGroup - 2);
      }
   }

   public void func_75249_e() {
      this.tickAge = 0;
      this.seedF = this.mob.func_145782_y() % 997 * 0.73F;
      int s = this.mob.func_145782_y() * 1103515245 + 12345;
      float u01 = ((s ^ s >>> 16) & 2147483647) / 2.1474836E9F;
      this.speedMul = 0.65F + 0.35F * u01;
      this.effSpeed = this.speed * this.speedMul;
      this.smCenterX = this.centerX;
      this.smCenterZ = this.centerZ;
      this.smRadius = this.radius;
      this.smTargetX = this.mob.field_70165_t;
      this.smTargetY = this.mob.field_70163_u;
      this.smTargetZ = this.mob.field_70161_v;
      this.smYaw = this.mob.field_70177_z;
      this.recalcCenterTicker = 0;
      this.recalcWaypointTicker = 0;
   }

   public void func_75251_c() {
      this.mob.func_70661_as().func_75499_g();
   }

   public void func_75246_d() {
      this.tickAge++;
      if (++this.recalcCenterTicker >= 10) {
         this.recalcCenterTicker = 0;
         this.snapshotGroup();
         this.estimateCenterAndRadius();
      }

      this.smCenterX = this.smCenterX + (this.centerX - this.smCenterX) * 0.15;
      this.smCenterZ = this.smCenterZ + (this.centerZ - this.smCenterZ) * 0.15;
      this.smRadius = this.smRadius + (this.radius - this.smRadius) * 0.2;
      this.dirSign = this.getGroupDirSign();
      float wMax = (float)((Math.PI * 2) / this.lapTicksBase);
      this.angularVel = this.dirSign * wMax * this.speedMul;
      float jitter = this.angJitterAmp
         * (
            0.5F * MathHelper.func_76126_a((this.tickAge + this.seedF) * this.angJitterFreq)
               + 0.5F * MathHelper.func_76134_b((this.tickAge * 0.73F + this.seedF) * this.angJitterFreq * 0.7F)
         );
      this.myAngle = this.normalizeAngle(this.myAngle + (this.angularVel + jitter));
      double rEff = this.smRadius + this.wobbleA * MathHelper.func_76126_a((this.tickAge + this.seedF) * this.wobbleF);
      double nx = MathHelper.func_76134_b(this.myAngle);
      double nz = MathHelper.func_76126_a(this.myAngle);
      double tnx = -MathHelper.func_76126_a(this.myAngle) * this.dirSign;
      double tnz = MathHelper.func_76134_b(this.myAngle) * this.dirSign;
      double side = this.wanderA * MathHelper.func_76126_a((this.tickAge + this.seedF * 3.0F) * this.wanderF);
      double rawX = this.smCenterX + nx * rEff + tnx * side;
      double rawZ = this.smCenterZ + nz * rEff + tnz * side;
      double rawY = this.findGroundY(this.mob.field_70170_p, rawX, rawZ, this.mob.field_70163_u);
      this.smTargetX = this.smTargetX + (rawX - this.smTargetX) * 0.35;
      this.smTargetZ = this.smTargetZ + (rawZ - this.smTargetZ) * 0.35;
      double dy = rawY - this.smTargetY;
      if (dy > 0.4) {
         dy = 0.4;
      }

      if (dy < -0.4) {
         dy = -0.4;
      }

      this.smTargetY += dy;
      double dx = this.smTargetX - this.mob.field_70165_t;
      double dz = this.smTargetZ - this.mob.field_70161_v;
      double distSq = dx * dx + dz * dz;
      if (distSq > 4.0 || ++this.recalcWaypointTicker >= 8) {
         this.recalcWaypointTicker = 0;
         this.mob.func_70661_as().func_75492_a(this.smTargetX, this.smTargetY, this.smTargetZ, this.effSpeed);
      }

      float targetYaw = (float)(MathHelper.func_181159_b(tnz, tnx) * (180.0 / Math.PI)) - 90.0F;
      this.smYaw = this.approachAngle(this.smYaw, targetYaw, 20.0F);
      this.mob.field_70177_z = this.smYaw;
      this.mob.field_70759_as = this.smYaw;
      this.mob.field_70761_aq = this.smYaw;
      this.mob.func_70605_aq().func_75642_a(this.smTargetX, this.smTargetY, this.smTargetZ, this.effSpeed);
      double tangentPush = 0.03;
      this.mob.field_70159_w += tnx * 0.03;
      this.mob.field_70179_y += tnz * 0.03;
      this.mob.func_70671_ap().func_75650_a(this.smTargetX, this.smTargetY + this.mob.func_70047_e(), this.smTargetZ, 30.0F, 30.0F);
      this.pushApartSlightly(tnx, tnz);
   }

   private void snapshotGroup() {
      this.groupSnapshot.clear();
      AxisAlignedBB box = new AxisAlignedBB(
         this.mob.field_70165_t - this.scanRadius,
         this.mob.field_70163_u - 8.0,
         this.mob.field_70161_v - this.scanRadius,
         this.mob.field_70165_t + this.scanRadius,
         this.mob.field_70163_u + 8.0,
         this.mob.field_70161_v + this.scanRadius
      );
      this.groupSnapshot.add(this.mob);

      for (Entity e : this.mob.field_70170_p.func_72839_b(this.mob, box)) {
         if (e instanceof EntityCreature && this.sameGroup.apply(e)) {
            this.groupSnapshot.add((EntityCreature)e);
         }
      }

      this.groupSnapshot.sort(Comparator.comparingInt(Entity::func_145782_y));
   }

   private void estimateCenterAndRadius() {
      if (this.groupSnapshot.isEmpty()) {
         this.centerX = this.mob.field_70165_t;
         this.centerZ = this.mob.field_70161_v;
         this.radius = MathHelper.func_151237_a(3.0, this.minRadius, this.maxRadius);
      } else {
         double sx = 0.0;
         double sz = 0.0;

         for (EntityCreature e : this.groupSnapshot) {
            sx += e.field_70165_t;
            sz += e.field_70161_v;
         }

         this.centerX = sx / this.groupSnapshot.size();
         this.centerZ = sz / this.groupSnapshot.size();
         double r = 0.0;

         for (EntityCreature e : this.groupSnapshot) {
            double dx = e.field_70165_t - this.centerX;
            double dz = e.field_70161_v - this.centerZ;
            r += Math.sqrt(dx * dx + dz * dz);
         }

         r /= this.groupSnapshot.size();
         if (r < this.minRadius * 0.6) {
            r = Math.max(this.minRadius, Math.min(this.maxRadius, 1.2 * Math.sqrt(this.groupSnapshot.size())));
         }

         this.radius = MathHelper.func_151237_a(r, this.minRadius, this.maxRadius);
      }
   }

   private void assignInitialAngle() {
      int idx = 0;
      int n = this.groupSnapshot.size();

      for (int i = 0; i < n; i++) {
         if (this.groupSnapshot.get(i) == this.mob) {
            idx = i;
            break;
         }
      }

      float baseAngle = (float)(idx * ((Math.PI * 2) / Math.max(1, n)));
      this.myAngle = this.normalizeAngle(baseAngle);
   }

   private int getGroupDirSign() {
      return (this.mob.func_145782_y() & 1) == 0 ? 1 : -1;
   }

   private float normalizeAngle(float a) {
      while (a < -Math.PI) {
         a = (float)(a + (Math.PI * 2));
      }

      while (a > Math.PI) {
         a = (float)(a - (Math.PI * 2));
      }

      return a;
   }

   private float approachAngle(float current, float target, float maxStep) {
      float delta = target - current;

      while (delta < -180.0F) {
         delta += 360.0F;
      }

      while (delta > 180.0F) {
         delta -= 360.0F;
      }

      if (delta > maxStep) {
         delta = maxStep;
      }

      if (delta < -maxStep) {
         delta = -maxStep;
      }

      return current + delta;
   }

   private double findGroundY(World world, double x, double z, double fallbackY) {
      BlockPos top = world.func_175645_m(new BlockPos(MathHelper.func_76128_c(x), 0, MathHelper.func_76128_c(z)));
      int y = top.func_177956_o();
      return Math.abs(y - fallbackY) > 6.0 ? fallbackY : y + 0.2;
   }

   private void pushApartSlightly(double tnx, double tnz) {
      AxisAlignedBB bb = this.mob.func_174813_aQ().func_72314_b(0.6, 0.2, 0.6);

      for (Entity e : this.mob.field_70170_p.func_72839_b(this.mob, bb)) {
         if (e instanceof EntityLivingBase) {
            double dx = this.mob.field_70165_t - e.field_70165_t;
            double dz = this.mob.field_70161_v - e.field_70161_v;
            double d2 = dx * dx + dz * dz + 0.001;
            double strength = Math.min(0.035, 0.02 / d2);
            double px = (dx * 0.5 + tnx * 0.5) * strength;
            double pz = (dz * 0.5 + tnz * 0.5) * strength;
            this.mob.field_70159_w += px;
            this.mob.field_70179_y += pz;
         }
      }
   }
}
