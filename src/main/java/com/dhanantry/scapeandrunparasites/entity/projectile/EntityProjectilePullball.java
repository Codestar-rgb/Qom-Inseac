package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanPullMobs;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLeer;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectilePullball extends EntitySRPProjectile {
   private EntityCanPullMobs spider;

   public EntityProjectilePullball(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntityProjectilePullball(World worldIn, EntityParasiteBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
      this.spider = (EntityCanPullMobs)shooter;
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.EXPLOSION_NORMAL;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         if (result.field_72308_g != null) {
            if (result.field_72308_g instanceof EntityLivingBase) {
            }
         } else {
            this.setWebsAround();
         }

         this.func_70106_y();
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (!this.field_70170_p.field_72995_K) {
         if (this.field_70173_aa == 5) {
            this.field_70232_b = this.field_70232_b * this.spider.getAcceleration();
            this.field_70230_d = this.field_70230_d * this.spider.getAcceleration();
         }

         if (this.field_70235_a == null) {
            this.func_70106_y();
            return;
         }

         if (this.spider.hasTargetedEntity() && !(this.field_70235_a instanceof EntityLeer)) {
            this.func_70106_y();
            return;
         }

         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_186662_g(2.0);

         for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (!(mob instanceof EntityParasiteBase) && this.field_70235_a.func_70685_l(mob) && this.spider.checkAttackTarget(mob) && mob.func_70089_S()) {
               this.spider.setPStatus(3);
               this.spider.setPullingMobEffects(mob);
               this.spider.setTargetedEntity(mob.func_145782_y());
               this.spider.resetPullSkill();
               this.func_70106_y();
            }
         }
      }
   }

   private void setWebsAround() {
      int totalWebs = this.field_70146_Z.nextInt(3) + 1;
      int[] positionss = new int[]{-1, 0, 1};

      for (int i = 1; i <= totalWebs; i++) {
         int pox = positionss[this.field_70146_Z.nextInt(3)];
         int poy = positionss[this.field_70146_Z.nextInt(3)];
         int poz = positionss[this.field_70146_Z.nextInt(3)];
         if (this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t + pox, this.field_70163_u + poy, this.field_70161_v + poz)).func_177230_c()
            == Blocks.field_150350_a) {
            this.field_70170_p
               .func_175656_a(new BlockPos(this.field_70165_t + pox, this.field_70163_u + poy, this.field_70161_v + poz), SRPBlocks.SRPWeb.func_176223_P());
         }
      }
   }
}
