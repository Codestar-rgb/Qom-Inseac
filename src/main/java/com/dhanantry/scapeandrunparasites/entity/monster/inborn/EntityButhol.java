package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackSwell;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityButhol extends EntityParasiteBase implements EntityCanFly {
   private EntityAIFlightLimits flightLimit;
   private int maxY;
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityButhol.class, DataSerializers.field_187191_a);

   public EntityButhol(World worldIn) {
      super(worldIn);
      this.flightLimit = new EntityAIFlightLimits(this, this.maxY, true);
      this.maxY = SRPAttributes.BUTHOL_MAXY;
      this.field_70715_bh
         .func_75776_a(
            4, new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, true, false, null, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen)
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  true,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.adaptedSneakPen,
                  SRPConfig.adaptedInviPen
               )
            );
      }

      this.field_70765_h = new EntityButhol.AIMoveControl(this);
      this.func_70105_a(1.4F, 2.4F);
      this.func_189654_d(true);
      this.field_70728_aV = SRPAttributes.XP_INFECTED * 2;
      this.field_70714_bg.func_85156_a(this.folow);
      if (this.maxY != 256) {
         this.field_70714_bg.func_75776_a(3, this.flightLimit);
      }

      this.fuseTime = 30;
      this.killcount = -10.0;
      this.type = 31;
   }

   @Override
   public int getParasiteIDRegister() {
      return 11;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackSwell(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, 32.0));
      this.field_70714_bg.func_75776_a(4, new EntityButhol.AIChargeAttack());
      this.field_70714_bg.func_75776_a(6, new EntityButhol.AIMoveRandom());
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.BUTHOL_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.BUTHOL_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.BUTHOL_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.BUTHOL_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70122_E && !this.field_70170_p.field_72995_K) {
         this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
      }
   }

   @Override
   protected void func_70609_aI() {
      if (this.func_70027_ad()) {
         super.func_70609_aI();
      } else {
         this.setSelfeState(1);
         this.dyingBurst(true, 1);
      }
   }

   public void func_180430_e(float distance, float damageMultiplier) {
   }

   public float func_70047_e() {
      return 2.4F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.CARRIER_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.CARRIER_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.CARRIER_DEATH;
   }

   public void func_70091_d(MoverType type, double x, double y, double z) {
      super.func_70091_d(type, x, y, z);
      this.func_145775_I();
   }

   public void func_70071_h_() {
      if (this.func_70089_S()) {
         this.lastActiveTime = this.timeSinceIgnited;
         this.dyingBurst(false, 1);
      }

      this.func_189654_d(true);
      super.func_70071_h_();
   }

   @Override
   protected void selfExplode() {
      switch (this.getSkin()) {
         case 1:
            boolean flag = ForgeEventFactory.getMobGriefingEvent(this.field_70170_p, this) && SRPConfigMobs.ButholGriefing;
            ParasiteEventEntity.createExplosion(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0F, flag);
            if (!this.field_70170_p.field_72995_K) {
               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
                  )
                  .func_186662_g(4.0);

               for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
                  if (!(mob instanceof EntityParasiteBase)) {
                     mob.func_70097_a(DamageSource.field_82727_n, (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                     SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 400, 1);
                     mob.func_70690_d(new PotionEffect(SRPPotions.VOMIT_E, 400, 0, false, true));
                  }
               }

               this.func_184185_a(SRPSounds.BUTHOL_BOOM, 1.0F, 1.0F);
               this.field_70729_aU = true;
               this.func_70106_y();
               this.spawnLingeringCloud();
            }

            return;
         default:
            boolean flag = ForgeEventFactory.getMobGriefingEvent(this.field_70170_p, this) && SRPConfigMobs.ButholGriefing;
            ParasiteEventEntity.createExplosion(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0F, flag);
            if (!this.field_70170_p.field_72995_K) {
               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
                  )
                  .func_186662_g(4.0);

               for (EntityLivingBase mobx : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
                  if (!(mobx instanceof EntityParasiteBase)) {
                     SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mobx, 400, 1);
                     mobx.func_70690_d(new PotionEffect(SRPPotions.VOMIT_E, 400, 0, false, true));
                  }
               }

               this.func_184185_a(SRPSounds.BUTHOL_BOOM, 1.0F, 1.0F);
               this.field_70729_aU = true;
               this.func_70106_y();
               this.spawnLingeringCloud();
               ParasiteSummon.spawnM(this, SRPConfigMobs.butholMobs, 0, false, this.func_95999_t());
            }
      }
   }

   private void spawnLingeringCloud() {
      switch (this.getSkin()) {
         case 1: {
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityareaeffectcloud.setRadius(this.field_70130_N * 3.5F, 0.5F);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 2));
            entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 3600, 2, false, false));
            entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.VIRA_E, 3600, 2, false, false));
            this.field_70170_p.func_72838_d(entityareaeffectcloud);
            return;
         }
         default: {
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityareaeffectcloud.setRadius(this.field_70130_N * 3.5F, 0.5F);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 0));
            entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
            entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.VIRA_E, 3600, 0, false, false));
            this.field_70170_p.func_72838_d(entityareaeffectcloud);
         }
      }
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
         switch (this.field_70146_Z.nextInt(1)) {
            case 0:
               this.setSkin(1);
         }
      }

      return floo;
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(VEX_FLAGS, (byte)0);
   }

   private boolean getVexFlag(int mask) {
      int i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
      return (i & mask) != 0;
   }

   private void setVexFlag(int mask, boolean value) {
      int i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.field_70180_af.func_187227_b(VEX_FLAGS, (byte)(i & 0xFF));
   }

   public boolean isCharging() {
      return this.getVexFlag(1);
   }

   public void setCharging(boolean charging) {
      this.setVexFlag(1, charging);
   }

   class AIChargeAttack extends EntityAIBase {
      public AIChargeAttack() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return EntityButhol.this.func_70638_az() != null
               && !EntityButhol.this.func_70605_aq().func_75640_a()
               && EntityButhol.this.field_70146_Z.nextInt(7) == 0
            ? EntityButhol.this.func_70068_e(EntityButhol.this.func_70638_az()) > 4.0
            : false;
      }

      public boolean func_75253_b() {
         return EntityButhol.this.func_70605_aq().func_75640_a()
            && EntityButhol.this.isCharging()
            && EntityButhol.this.func_70638_az() != null
            && EntityButhol.this.func_70638_az().func_70089_S();
      }

      public void func_75249_e() {
         EntityLivingBase entitylivingbase = EntityButhol.this.func_70638_az();
         Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
         EntityButhol.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.0);
         EntityButhol.this.setCharging(true);
      }

      public void func_75251_c() {
         EntityButhol.this.setCharging(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = EntityButhol.this.func_70638_az();
         if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
            if (EntityButhol.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
               EntityButhol.this.func_70652_k(entitylivingbase);
               EntityButhol.this.setCharging(false);
            } else {
               double d0 = EntityButhol.this.func_70068_e(entitylivingbase);
               if (d0 < 9.0) {
                  Vec3d vec3d = entitylivingbase.func_174824_e(1.0F);
                  EntityButhol.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.0);
               }
            }
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityButhol vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityButhol.this.field_70165_t;
            double d1 = this.field_75647_c - EntityButhol.this.field_70163_u;
            double d2 = this.field_75644_d - EntityButhol.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityButhol.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityButhol.this.field_70159_w *= 0.5;
               EntityButhol.this.field_70181_x *= 0.5;
               EntityButhol.this.field_70179_y *= 0.5;
            } else {
               EntityButhol.this.field_70159_w = EntityButhol.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityButhol.this.field_70181_x = EntityButhol.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityButhol.this.field_70179_y = EntityButhol.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityButhol.this.func_70638_az() == null) {
                  EntityButhol.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityButhol.this.field_70159_w, EntityButhol.this.field_70179_y))
                     * (180.0F / (float)Math.PI);
                  EntityButhol.this.field_70761_aq = EntityButhol.this.field_70177_z;
               } else {
                  double d4 = EntityButhol.this.func_70638_az().field_70165_t - EntityButhol.this.field_70165_t;
                  double d5 = EntityButhol.this.func_70638_az().field_70161_v - EntityButhol.this.field_70161_v;
                  EntityButhol.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityButhol.this.field_70761_aq = EntityButhol.this.field_70177_z;
               }
            }
         }
      }
   }

   class AIMoveRandom extends EntityAIBase {
      public AIMoveRandom() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return !EntityButhol.this.func_70605_aq().func_75640_a() && EntityButhol.this.field_70146_Z.nextInt(7) == 0;
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityButhol.this);

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityButhol.this.field_70146_Z.nextInt(15) - 7,
               EntityButhol.this.field_70146_Z.nextInt(11) - 5,
               EntityButhol.this.field_70146_Z.nextInt(15) - 7
            );
            if (EntityButhol.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityButhol.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 0.25);
               if (EntityButhol.this.func_70638_az() == null) {
                  EntityButhol.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
