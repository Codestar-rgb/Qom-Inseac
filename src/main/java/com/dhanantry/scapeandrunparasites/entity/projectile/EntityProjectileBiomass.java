package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileBiomass extends EntityFireball {
   private EntityLivingBase target;
   private String parasite;
   private int point;
   private int kin;

   public EntityProjectileBiomass(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntityProjectileBiomass(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
   }

   public void setParasite(String in, int points, int type) {
      this.parasite = in;
      this.point = points;
      this.target = ((EntityParasiteBase)this.field_70235_a).func_70638_az();
      this.kin = type;
   }

   public boolean func_70027_ad() {
      return false;
   }

   public boolean func_70067_L() {
      return false;
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.EXPLOSION_NORMAL;
   }

   public boolean func_70097_a(DamageSource source, float amount) {
      return false;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         if (this.field_70235_a != null && this.field_70235_a.func_70089_S() && this.field_70235_a instanceof EntityParasiteBase) {
            EntityBiomass entityout = new EntityBiomass(this.field_70170_p, (EntityParasiteBase)this.field_70235_a, this.target);
            entityout.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            entityout.setFuse(80);
            entityout.setParasite(this.parasite, this.point);
            entityout.setSkin(this.kin);
            EntityCanSummon father = (EntityCanSummon)this.field_70235_a;
            father.setActualParasites(this.point);
            father.addID(entityout.func_145782_y(), this.point);
            this.field_70170_p.func_72838_d(entityout);
         }

         this.func_70106_y();
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.field_70173_aa > 140) {
         this.func_70106_y();
      }
   }
}
