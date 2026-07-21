package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityTendril;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAngedball;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyDead;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnged extends EntityPPure implements IRangedAttackMob, EntityBodyParts {
   private EntityBody leftTendril;
   private EntityBody rightTendril;
   private float leftTendrilHealth;
   private float rightTendrilHealth;

   public EntityAnged(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.6F, 3.1F);
      this.type = 51;
      this.field_70138_W = 1.0F;
      this.leftTendril = new EntityBody(this, 0.7F, 0.9F, 1.0F, 1.1F, 2.3F, 1, 1, true);
      this.rightTendril = new EntityBody(this, 0.7F, 0.9F, 1.0F, 1.1F, 2.3F, -1, 2, true);
      this.leftTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.rightTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
   }

   @Override
   public int getParasiteIDRegister() {
      return 25;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 5.0F));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.5, 20, (float)SRPConfig.pureFollow / 2.0F, false));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ANGED_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ANGED_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.ANGED_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.ANGED_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.leftTendrilHealth > 0.0F) {
         this.leftTendril.func_70071_h_();
      }

      if (this.rightTendrilHealth > 0.0F) {
         this.rightTendril.func_70071_h_();
      }
   }

   public float func_70047_e() {
      return 3.0F;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && entityIn instanceof EntityLivingBase) {
         ((EntityLivingBase)entityIn).func_70653_a(entityIn, 1.0F, this.field_70165_t - entityIn.field_70165_t, this.field_70161_v - entityIn.field_70161_v);
      }

      return flag;
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else {
         boolean flag = this.func_70097_a(source, amount);
         if (!flag) {
            return false;
         } else {
            if (this.leftTendril.getId() == id) {
               this.leftTendrilHealth -= amount;
               if (this.leftTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(6);
                  tendril.func_82149_j(this.leftTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.leftTendril);
                  this.field_70170_p.func_72960_a(this, (byte)11);
                  this.cutResistances(SRPConfig.purePointDamCap / 2);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            } else if (this.rightTendril.getId() == id) {
               this.rightTendrilHealth -= amount;
               if (this.rightTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(6);
                  tendril.func_82149_j(this.rightTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.rightTendril);
                  this.field_70170_p.func_72960_a(this, (byte)22);
                  this.cutResistances(SRPConfig.purePointDamCap / 2);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            }

            return flag;
         }
      }
   }

   @Override
   public void setBodyPartDead(int id) {
      if (this.leftTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      } else if (this.rightTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      }
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.ANGED_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.ANGED_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.ANGED_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.angedOrbEffects, mobs);
      }

      return flag;
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance
         || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant
         || this.canChangeVariant) {
         switch (this.field_70146_Z.nextInt(1)) {
            case 0:
               this.setSkin(7);
         }
      }

      return floo;
   }

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
      Vec3d vec3d = this.func_70676_i(1.0F);
      double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
      double d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 2.0F - (0.5 + this.field_70163_u + this.field_70131_O / 2.0F);
      double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
      this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0F, 1.0F);
      d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 4.0F - (1.0 + this.field_70163_u + this.field_70131_O / 2.0F);
      EntityProjectileAngedball entitylargefireball = new EntityProjectileAngedball(this.field_70170_p, this, d2, d3, d4);
      entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
      entitylargefireball.field_70163_u = this.field_70163_u + this.func_70047_e() - 0.2;
      entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
      this.field_70170_p.func_72838_d(entitylargefireball);
   }

   public void func_184724_a(boolean swingingArms) {
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74776_a("parasiteleftTendril", this.leftTendrilHealth);
      compound.func_74776_a("parasiterightTendril", this.rightTendrilHealth);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("parasiteleftTendril", 99)) {
         this.leftTendrilHealth = compound.func_74760_g("parasiteleftTendril");
         if (this.leftTendrilHealth <= 0.0F) {
            this.field_70170_p.func_72960_a(this, (byte)11);
         }
      }

      if (compound.func_150297_b("parasiterightTendril", 99)) {
         this.rightTendrilHealth = compound.func_74760_g("parasiterightTendril");
         if (this.rightTendrilHealth <= 0.0F) {
            this.field_70170_p.func_72960_a(this, (byte)22);
         }
      }
   }

   @Override
   public void func_70106_y() {
      if (this.leftTendril != null) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      }

      if (this.rightTendril != null) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      }

      super.func_70106_y();
   }

   @SideOnly(Side.CLIENT)
   public float getLeft() {
      return this.leftTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   public float getRight() {
      return this.rightTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 11) {
         this.leftTendrilHealth = 0.0F;
      } else if (id == 22) {
         this.rightTendrilHealth = 0.0F;
      } else {
         super.func_70103_a(id);
      }
   }
}
