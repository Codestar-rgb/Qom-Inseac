package com.dhanantry.scapeandrunparasites.entity.monster.deterrent;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntitySRPProjectile;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.network.MsgQlipShake;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityNak extends EntityPStationary {
   private float attackTimer;
   private boolean up2;
   private EntityPDispatcher father;
   private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntityNak.class, DataSerializers.field_187192_b);
   private EntityLivingBase targetedEntity;
   private int tak;

   public EntityNak(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.7F, 2.5F);
      this.field_70728_aV = 0;
      this.type = 40;
      this.buriedT = 4.0;
      this.damageCap = SRPConfig.turretCap;
      this.pointCap = SRPConfig.turretPointCap;
      this.pointReduction = SRPConfig.turretPointRed;
      this.chanceLearn = SRPConfig.turretChanceLe;
      this.chanceLearnFire = SRPConfig.turretChanceLeFire;
      this.DamageTypeCap = SRPConfig.turretPointDamCap;
      this.MiniDamage = SRPConfig.turretMinDamage;
      this.regen = SRPConfig.turretRegen;
      this.valueEvDeath = 0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 72;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(TARGET_ENTITY, 0);
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.NAK_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.NAK_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.NAK_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(6.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.func_175446_cd()) {
         if (this.up2) {
            this.attackTimer += 0.15F;
            if (this.attackTimer > 1.0F) {
               this.up = false;
            }
         } else {
            this.attackTimer -= 0.2F;
         }

         if (!this.up && !this.buried()) {
            if (!this.field_70170_p.field_72995_K) {
               if (this.func_70638_az() != null) {
                  if (this.func_70638_az() instanceof EntityPlayer) {
                     EntityPlayer pa = (EntityPlayer)this.func_70638_az();
                     if (pa.field_71075_bZ.field_75098_d || pa.field_71075_bZ.field_75102_a) {
                        this.func_70624_b(null);
                        this.setTargetedEntity(0);
                        this.setParasiteStatus(0);
                        return;
                     }
                  }

                  if (!this.func_70638_az().func_70089_S()) {
                     this.func_70624_b(null);
                     this.setTargetedEntity(0);
                     this.setParasiteStatus(0);
                  } else {
                     this.tak = 0;
                     if (this.func_70685_l(this.func_70638_az()) && this.func_70068_e(this.func_70638_az()) < 25.0) {
                        this.setParasiteStatus(3);
                        this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 80, 2, false, false));
                        this.setTargetedEntity(this.func_70638_az().func_145782_y());
                        this.func_70661_as().func_75497_a(this.func_70638_az(), 0.0);
                        this.lookAt(this.func_70638_az());
                        if (this.srpTicks == 10 && this.func_70638_az() instanceof EntityPlayerMP) {
                           SRPNetwork.CHANNEL.sendTo(new MsgQlipShake(250, 0, true, false, 4.0F), (EntityPlayerMP)this.func_70638_az());
                        }
                     } else {
                        this.setParasiteStatus(2);
                        this.setTargetedEntity(0);
                     }
                  }
               } else {
                  this.tak++;
                  if (this.tak >= 60) {
                     this.field_70170_p.func_72960_a(this, (byte)51);
                     this.up = true;
                  }

                  this.setParasiteStatus(0);
                  this.setTargetedEntity(0);
               }
            }

            if (this.getTargetedEntity() != null) {
               double dis = this.func_70068_e(this.getTargetedEntity());
               if (dis < 25.0 && dis > 1.0) {
                  EntityLivingBase target = this.getTargetedEntity();
                  target.func_184210_p();
                  double str = 0.5;
                  target.field_70159_w = target.field_70159_w + (Math.signum(this.field_70165_t - target.field_70165_t) * str - target.field_70159_w) * str;
                  target.field_70179_y = target.field_70179_y + (Math.signum(this.field_70161_v - target.field_70161_v) * str - target.field_70179_y) * str;
                  if (!this.field_70170_p.field_72995_K) {
                     target.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 2, false, false));
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (source.func_76364_f() instanceof EntitySRPProjectile) {
         EntityLivingBase target = this.getTargetedEntity();
         if (target != null) {
            target.func_70097_a(source, amount * 2.0F);
         }

         return false;
      } else {
         return super.func_70097_a(source, amount);
      }
   }

   @Override
   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
      if (this.field_70170_p.field_72995_K) {
         return true;
      } else {
         Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
         if (wea != SRPItems.itembase) {
         }

         if (this.father != null) {
            this.father.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 100, 1, false, false));
         }

         return super.func_184645_a(player, hand);
      }
   }

   private void setTargetedEntity(int entityId) {
      this.field_70180_af.func_187227_b(TARGET_ENTITY, entityId);
   }

   public boolean hasTargetedEntity() {
      return (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
   }

   public EntityPDispatcher getFather() {
      return this.father;
   }

   public void setFather(EntityPDispatcher in) {
      this.father = in;
   }

   @Nullable
   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.field_70170_p.field_72995_K) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY));
            if (entity instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)entity;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.func_70638_az();
      }
   }

   public void func_184206_a(DataParameter<?> key) {
      super.func_184206_a(key);
      if (TARGET_ENTITY.equals(key)) {
         this.targetedEntity = null;
      }
   }

   public float func_70047_e() {
      return 1.8F;
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimer() {
      return this.attackTimer;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 12) {
         this.up = true;
         this.attackTimer = 0.0F;
      } else {
         super.func_70103_a(id);
      }
   }
}
