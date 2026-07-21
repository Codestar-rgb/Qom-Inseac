package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileSpineball extends EntitySRPProjectile {
   private float damage;
   private int duration;
   private int amp;
   private double item;

   public EntityProjectileSpineball(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntityProjectileSpineball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float projDamage) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
      this.damage = projDamage;
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.SLIME;
   }

   public void setDurationAmplifier(int duration, int amplifier) {
      this.duration = duration * 20;
      this.amp = amplifier - 1;
   }

   public void setGearDamage(double in) {
      this.item = in;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         if (result.field_72308_g != null && result.field_72308_g instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)result.field_72308_g;
            if (target instanceof EntityParasiteBase && !(target instanceof EntityNak)) {
               this.func_70106_y();
               return;
            }

            boolean primitive = false;
            EntityParasiteBase shooter = (EntityParasiteBase)this.field_70235_a;
            if (shooter instanceof EntityEmana) {
               primitive = true;
            }

            DamageSource damagesource;
            if (this.field_70235_a == null) {
               damagesource = DamageSource.func_76356_a(this, this);
            } else {
               damagesource = DamageSource.func_76356_a(this, this.field_70235_a);
            }

            target.func_70097_a(damagesource, this.damage);
            target.func_70690_d(new PotionEffect(MobEffects.field_76436_u, this.duration, this.amp));
            this.attackEntityAsMobMinimum(target, (EntityParasiteBase)this.field_70235_a);
            this.damageArmor(target, this.item);
            if (target.func_110143_aJ() <= 0.0F && primitive && shooter.func_70089_S()) {
               double k = shooter.getKillC();
               shooter.setKillC(++k);
               shooter.particleStatus((byte)5);
               if (k > SRPConfig.adaptedKills && ParasiteEventEntity.canSpawnNext) {
                  ParasiteEventEntity.spawnNext(shooter, new EntityEmanaAdapted(this.field_70170_p), true, true);
               }
            }
         }

         this.func_70106_y();
      }
   }

   private void damageArmor(EntityLivingBase target, double percen) {
      ArrayList<ItemStack> off = new ArrayList<>();
      Iterable<ItemStack> gear = target.func_184193_aE();
      if (gear != null && !gear.equals(Collections.emptyList())) {
         for (ItemStack part : gear) {
            if (!part.func_190926_b() && part.func_77984_f()) {
               off.add(part);
            }
         }

         if (!off.isEmpty()) {
            for (ItemStack partx : off) {
               if (partx.func_77958_k() * SRPConfigSystems.corrNot < partx.func_77958_k() - partx.func_77952_i()) {
                  partx.func_77972_a((int)(partx.func_77958_k() * percen), target);
               }
            }
         }
      }
   }
}
