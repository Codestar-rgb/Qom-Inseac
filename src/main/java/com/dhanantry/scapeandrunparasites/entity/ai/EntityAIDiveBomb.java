package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;

public class EntityAIDiveBomb extends EntityAIBase {
   private final EntityLiving host;
   private final int cooldownTicks;
   private final int hoverTicksMax;
   private final double diveSpeed;
   private final float explosionPower;
   private static final double ASCEND_HEIGHT = 20.0;
   private static final double ASCEND_STEP_MAX = 0.6;
   private static final int MAX_ASCEND_TICKS = 120;
   private EntityAIDiveBomb.Phase phase = EntityAIDiveBomb.Phase.IDLE;
   private long nextAllowedTick = 0L;
   private int hoverTicks = 0;
   private int diveTicks = 0;
   private int ascendTicks = 0;
   private double ascendStartY = 0.0;
   private double ascendTargetY = 0.0;
   private double hoverY = 0.0;
   private Vec3d lockedTargetPos = Vec3d.field_186680_a;

   public EntityAIDiveBomb(EntityLiving host, int cooldownTicks, int hoverTicks, double diveSpeed, float explosionPower) {
      this.host = host;
      this.cooldownTicks = cooldownTicks;
      this.hoverTicksMax = hoverTicks;
      this.diveSpeed = diveSpeed;
      this.explosionPower = explosionPower;
      this.func_75248_a(7);
   }

   public boolean func_75250_a() {
      if (this.host.field_70170_p.field_72995_K) {
         return false;
      } else if (this.host.field_70170_p.func_82737_E() < this.nextAllowedTick) {
         return false;
      } else if (!(this.host instanceof EntityNogla)) {
         return false;
      } else if (!((EntityNogla)this.host).isRicardoVariant()) {
         return false;
      } else {
         EntityLivingBase tgt = this.host.func_70638_az();
         return tgt != null && tgt.func_70089_S();
      }
   }

   public boolean func_75253_b() {
      if (!(this.host instanceof EntityNogla)) {
         return false;
      } else {
         return !((EntityNogla)this.host).isRicardoVariant() ? false : this.phase != EntityAIDiveBomb.Phase.IDLE;
      }
   }

   public void func_75249_e() {
      this.phase = EntityAIDiveBomb.Phase.ASCEND;
      this.hoverTicks = 0;
      this.diveTicks = 0;
      this.ascendTicks = 0;
      this.ascendStartY = this.host.field_70163_u;
      this.ascendTargetY = this.ascendStartY + 20.0;
      this.host.func_70661_as().func_75499_g();
      this.host.field_70143_R = 0.0F;
      this.host.func_189654_d(true);
      this.host.field_70159_w = this.host.field_70181_x = this.host.field_70179_y = 0.0;
   }

   public void func_75246_d() {
      if (!this.host.field_70170_p.field_72995_K) {
         switch (this.phase) {
            case ASCEND:
               this.host.func_189654_d(true);
               this.ascendTicks++;
               double remaining = this.ascendTargetY - this.host.field_70163_u;
               if (!(remaining <= 0.05) && this.ascendTicks < 120) {
                  double step = Math.min(0.6, remaining);
                  Vec3d startx = new Vec3d(this.host.field_70165_t, this.host.field_70163_u, this.host.field_70161_v);
                  Vec3d end = new Vec3d(this.host.field_70165_t, this.host.field_70163_u + step, this.host.field_70161_v);
                  RayTraceResult up = this.host.field_70170_p.func_147447_a(startx, end, false, true, false);
                  if (up != null && up.field_72313_a == Type.BLOCK) {
                     double yAtHit = up.field_72307_f.field_72448_b - 0.05;
                     this.host.func_70634_a(this.host.field_70165_t, yAtHit, this.host.field_70161_v);
                     this.hoverY = this.host.field_70163_u;
                     this.enterHover();
                  } else {
                     this.host.func_70634_a(this.host.field_70165_t, this.host.field_70163_u + step, this.host.field_70161_v);
                     this.host.field_70159_w = this.host.field_70181_x = this.host.field_70179_y = 0.0;
                     this.host.field_70143_R = 0.0F;
                     EntityLivingBase tA = this.host.func_70638_az();
                     if (tA != null) {
                        this.faceTowards(tA.field_70165_t, tA.field_70163_u + tA.func_70047_e() * 0.5, tA.field_70161_v);
                     }
                  }
               } else {
                  this.hoverY = this.host.field_70163_u;
                  this.enterHover();
               }
               break;
            case HOVER:
               this.host.func_189654_d(true);
               double dy = this.hoverY - this.host.field_70163_u;
               if (Math.abs(dy) > 0.05) {
                  double nudge = Math.copySign(Math.min(0.2, Math.abs(dy)), dy);
                  this.host.func_70634_a(this.host.field_70165_t, this.host.field_70163_u + nudge, this.host.field_70161_v);
               }

               this.host.field_70159_w = this.host.field_70181_x = this.host.field_70179_y = 0.0;
               this.host.field_70143_R = 0.0F;
               EntityLivingBase tH = this.host.func_70638_az();
               if (tH == null || !tH.func_70089_S()) {
                  this.finish(false);
                  return;
               }

               this.faceTowards(tH.field_70165_t, tH.field_70163_u + tH.func_70047_e() * 0.5, tH.field_70161_v);
               this.hoverTicks++;
               int need = 20;
               if (this.hoverTicks >= need) {
                  this.lockedTargetPos = new Vec3d(tH.field_70165_t, tH.field_70163_u + tH.func_70047_e() * 0.5, tH.field_70161_v);
                  this.phase = EntityAIDiveBomb.Phase.DIVE;
                  this.host.func_189654_d(true);
                  this.diveTicks = 0;
                  this.applyDiveVector(this.lockedTargetPos, 1.6);
               }
               break;
            case DIVE:
               this.host.func_70661_as().func_75499_g();
               this.host.func_189654_d(true);
               this.host.field_70143_R = 0.0F;
               this.diveTicks++;
               Vec3d start = new Vec3d(this.host.field_70165_t, this.host.field_70163_u, this.host.field_70161_v);
               Vec3d next = new Vec3d(
                  this.host.field_70165_t + this.host.field_70159_w,
                  this.host.field_70163_u + this.host.field_70181_x,
                  this.host.field_70161_v + this.host.field_70179_y
               );
               RayTraceResult r = this.host.field_70170_p.func_147447_a(start, next, false, true, false);
               if (r != null && r.field_72313_a == Type.BLOCK) {
                  this.explodeAndFinish();
                  return;
               }

               EntityLivingBase tD = this.host.func_70638_az();
               if (tD != null && tD.func_70089_S()) {
                  if (this.host.func_174813_aQ().func_72326_a(tD.func_174813_aQ())) {
                     this.explodeAndFinish();
                     return;
                  }

                  if (this.host.func_70068_e(tD) < 2.25) {
                     this.explodeAndFinish();
                     return;
                  }

                  Vec3d aim = new Vec3d(tD.field_70165_t, tD.field_70163_u + tD.func_70047_e() * 0.5, tD.field_70161_v);
                  double spd = this.computeDiveSpeed();
                  this.applyDiveVector(aim, spd);
               } else {
                  double spd = this.computeDiveSpeed();
                  this.applyDiveVector(this.lockedTargetPos, spd);
               }

               if (this.diveTicks > 80) {
                  this.explodeAndFinish();
               }
         }
      }
   }

   private void enterHover() {
      this.phase = EntityAIDiveBomb.Phase.HOVER;
      this.hoverTicks = 0;
      this.host.field_70159_w = this.host.field_70181_x = this.host.field_70179_y = 0.0;
      this.host.field_70133_I = true;
      this.host.field_70143_R = 0.0F;
   }

   private double computeDiveSpeed() {
      double base = this.diveSpeed <= 0.0 ? 2.8 : this.diveSpeed;
      double accel = 0.35 * this.diveTicks;
      double cap = Math.max(base, 4.5);
      return Math.min(base + accel, cap);
   }

   private void applyDiveVector(Vec3d target, double speed) {
      Vec3d from = new Vec3d(this.host.field_70165_t, this.host.field_70163_u, this.host.field_70161_v);
      Vec3d dir = target.func_178788_d(from).func_72432_b();
      this.host.field_70159_w = dir.field_72450_a * speed;
      this.host.field_70181_x = dir.field_72448_b * speed;
      this.host.field_70179_y = dir.field_72449_c * speed;
      this.host.field_70133_I = true;
      this.faceTowards(
         this.host.field_70165_t + this.host.field_70159_w,
         this.host.field_70163_u + this.host.field_70181_x,
         this.host.field_70161_v + this.host.field_70179_y
      );
   }

   private void explodeAndFinish() {
      this.host
         .field_70170_p
         .func_72885_a(this.host, this.host.field_70165_t, this.host.field_70163_u, this.host.field_70161_v, this.explosionPower, false, false);
      this.finish(true);
   }

   private void finish(boolean setCd) {
      this.host.func_189654_d(false);
      this.host.field_70159_w = this.host.field_70181_x = this.host.field_70179_y = 0.0;
      this.host.field_70133_I = true;
      this.host.field_70143_R = 0.0F;
      this.phase = EntityAIDiveBomb.Phase.IDLE;
      if (setCd) {
         this.nextAllowedTick = this.host.field_70170_p.func_82737_E() + this.cooldownTicks;
      }
   }

   public void func_75251_c() {
      this.finish(true);
   }

   private void faceTowards(double x, double y, double z) {
      double dx = x - this.host.field_70165_t;
      double dz = z - this.host.field_70161_v;
      float yaw = (float)(MathHelper.func_181159_b(dz, dx) * (180.0 / Math.PI)) - 90.0F;
      this.host.field_70177_z = yaw;
      this.host.field_70759_as = yaw;
      this.host.field_70761_aq = yaw;
   }

   private static enum Phase {
      IDLE,
      ASCEND,
      HOVER,
      DIVE;
   }
}
