package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileAncientball extends EntitySRPProjectile {
   public EntityProjectileAncientball(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
   }

   public EntityProjectileAncientball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.SLIME;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         if (result.field_72308_g != null && result.field_72308_g instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)result.field_72308_g;
            if (target instanceof EntityParasiteBase && !(target instanceof EntityNak)) {
               this.func_70106_y();
               return;
            }

            float damageR = SRPAttributes.ORONCO_ATTACK_DAMAGE;
            DamageSource damagesource;
            if (this.field_70235_a == null) {
               damagesource = DamageSource.func_76356_a(this, this);
            } else {
               damagesource = DamageSource.func_76356_a(this, this.field_70235_a);
            }

            target.func_70097_a(damagesource, damageR);
            target.func_70690_d(new PotionEffect(MobEffects.field_82731_v, 60, 0));
            this.attackEntityAsMobMinimum(target, (EntityParasiteBase)this.field_70235_a);
         }

         EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
         entityareaeffectcloud.func_184483_a(this.field_70130_N * 4.0F);
         entityareaeffectcloud.func_184495_b(-0.5F);
         entityareaeffectcloud.func_184485_d(5);
         entityareaeffectcloud.func_184486_b(entityareaeffectcloud.func_184489_o());
         entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / entityareaeffectcloud.func_184489_o());
         entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_82731_v, 300, 0));
         entityareaeffectcloud.func_184496_a(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
         this.field_70170_p.func_72838_d(entityareaeffectcloud);
         this.func_70106_y();
      }
   }
}
