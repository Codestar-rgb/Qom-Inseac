package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileEffects extends EntitySRPProjectile {
   private ArrayList<PotionEffect> eff;

   public EntityProjectileEffects(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
      this.eff = new ArrayList<>();
   }

   public EntityProjectileEffects(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
      this.eff = new ArrayList<>();
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.EXPLOSION_NORMAL;
   }

   public void addEffect(PotionEffect in) {
      this.eff.add(in);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (!this.field_70170_p.field_72995_K) {
         this.field_70181_x -= 0.1;
      }
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u - 1.0, this.field_70161_v);
         entityareaeffectcloud.setRadius(2.3F, 0.5F);
         entityareaeffectcloud.setRadiusOnUse(-0.5F);
         entityareaeffectcloud.setWaitTime(10);
         entityareaeffectcloud.setDuration(100);
         entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());

         for (PotionEffect in : this.eff) {
            entityareaeffectcloud.addEffect(in);
         }

         this.field_70170_p.func_72838_d(entityareaeffectcloud);
         this.func_70106_y();
      }
   }
}
