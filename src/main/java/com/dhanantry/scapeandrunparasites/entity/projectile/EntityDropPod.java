package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityDropPod extends EntityParasiteBase {
   private byte owner;

   public EntityDropPod(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.0F, 2.0F);
      this.field_70714_bg.func_85156_a(this.aiWander);
      this.field_70714_bg.func_85156_a(this.folow);
      this.fuseTime = 80;
      this.type = 61;
      this.setParasiteStatus(1);
      this.killcount = -10.0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 34;
   }

   public EntityDropPod(World worldIn, int points) {
      this(worldIn);
   }

   public void func_70108_f(Entity entityIn) {
   }

   @Override
   protected void func_82167_n(Entity entityIn) {
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((SRPAttributes.ORONCO_HEALTH + SRPAttributes.TERLA_HEALTH) * 0.1);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(5.0);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70122_E) {
         this.setParasiteStatus(0);
         this.setSelfeState(1);
         this.dyingBurst(false, 2);
      } else {
         this.particleStatus((byte)12);
      }
   }

   @Override
   protected void selfExplode() {
      boolean flag = ForgeEventFactory.getMobGriefingEvent(this.field_70170_p, this) && SRPConfigMobs.ratholGriefing;
      ParasiteEventEntity.createExplosion(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0F, flag);
      if (!this.field_70170_p.field_72995_K) {
         this.func_184185_a(SRPSounds.RATHOL_BOOM, 1.0F, 1.0F);
         this.field_70729_aU = true;
         String[] here = new String[3];
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_186662_g(7.0);
         List<EntityLivingBase> moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);

         for (String i : SRPConfigMobs.pod1Effects) {
            here = i.split(";");
            Potion potionE = Potion.func_180142_b(here[2]);
            if (potionE != null) {
               int duration = Integer.parseInt(here[0]) * 20;
               int amp = Integer.parseInt(here[1]);

               for (EntityLivingBase mob : moblist) {
                  if (mob != this && !(mob instanceof EntityParasiteBase)) {
                     mob.func_70690_d(new PotionEffect(potionE, duration, amp, false, false));
                  }
               }
            }
         }

         this.func_70106_y();
         this.spawnLingeringCloud();
         switch (this.owner) {
            case 62:
               int limit = 0;
               int cap = 0;

               while (limit < SRPConfigMobs.oroncoMaxMobPod && cap < 5) {
                  if (ParasiteSummon.SummonM(
                     this, SRPConfigMobs.oroncoMobList, 1, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.func_70638_az(), false
                  )) {
                     limit++;
                  } else {
                     cap++;
                  }
               }
               break;
            case 63:
               ParasiteSummon.spawnM(this, SRPConfigMobs.oroncoMobList, 0, false, this.func_95999_t());
         }
      }
   }

   private void spawnLingeringCloud() {
      EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
      entityareaeffectcloud.func_184483_a(this.field_70130_N * 2.0F);
      entityareaeffectcloud.func_184485_d(5);
      entityareaeffectcloud.func_184486_b(entityareaeffectcloud.func_184489_o());
      entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / entityareaeffectcloud.func_184489_o());
      entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76436_u, 300, 0));
      entityareaeffectcloud.func_184496_a(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
      this.field_70170_p.func_72838_d(entityareaeffectcloud);
   }

   public void setOwner(byte in) {
      this.owner = in;
   }

   public void func_180430_e(float distance, float damageMultiplier) {
   }

   public float func_70047_e() {
      return 2.1F;
   }
}
