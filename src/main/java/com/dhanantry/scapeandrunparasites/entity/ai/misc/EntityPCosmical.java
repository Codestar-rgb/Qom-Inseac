package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.EntityOrbScary;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPCosmical extends EntityPMalleable {
   private static final DataParameter<Boolean> SHADOW = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187198_h);
   private static final DataParameter<Boolean> CLONE = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187198_h);
   protected int cloneLife;
   protected float hackHeal;
   protected String[] hackEffects;
   private ArrayList<DataParameter<Integer>> victims = new ArrayList<>();
   private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187192_b);
   private static final DataParameter<Integer> TARGET_ENTITY1 = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187192_b);
   private static final DataParameter<Integer> TARGET_ENTITY2 = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187192_b);
   private static final DataParameter<Integer> TARGET_ENTITY3 = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187192_b);
   private static final DataParameter<Integer> TARGET_ENTITY4 = EntityDataManager.func_187226_a(EntityPCosmical.class, DataSerializers.field_187192_b);
   public int limitClones;
   protected int limitClonesLife;
   protected int limitClonesCooldown;
   protected float shadowDamage;
   protected int shadowDamageTick;
   public float shadowDamageR;
   public int shadowDamageRCooldown;
   protected int shakeee;
   protected int showC;
   private boolean skillHack;
   protected int borderHack;

   public EntityPCosmical(World worldIn) {
      super(worldIn);
      this.victims.add(TARGET_ENTITY);
      this.victims.add(TARGET_ENTITY1);
      this.victims.add(TARGET_ENTITY2);
      this.victims.add(TARGET_ENTITY3);
      this.victims.add(TARGET_ENTITY4);
      this.hackHeal = 0.01F;
      this.cothSpread = 1.0F;
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 160, 100, 10, false, 32, true));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 240, 100, 0, false, 33, true));
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(SHADOW, true);
      this.field_70180_af.func_187214_a(CLONE, false);
      this.field_70180_af.func_187214_a(TARGET_ENTITY, 0);
      this.field_70180_af.func_187214_a(TARGET_ENTITY1, 0);
      this.field_70180_af.func_187214_a(TARGET_ENTITY2, 0);
      this.field_70180_af.func_187214_a(TARGET_ENTITY3, 0);
      this.field_70180_af.func_187214_a(TARGET_ENTITY4, 0);
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74757_a("SRPShadow", this.getShadowStatus());
      compound.func_74757_a("SRPClone", this.getCloneC());
      compound.func_74768_a("SRPLimitClones", this.limitClones);
      compound.func_74768_a("SRPLimitClonesLife", this.limitClonesLife);
      compound.func_74768_a("SRPLimitClonesCooldown", this.limitClonesCooldown);
      compound.func_74776_a("SRPShadowDamage", this.shadowDamage);
      compound.func_74768_a("SRPShadowDamageTick", this.shadowDamageTick);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_74764_b("SRPShadow")) {
         this.field_70180_af.func_187227_b(SHADOW, compound.func_74767_n("SRPShadow"));
      }

      if (compound.func_74764_b("SRPClone")) {
         this.field_70180_af.func_187227_b(CLONE, compound.func_74767_n("SRPClone"));
      }

      if (compound.func_74764_b("SRPLimitClones")) {
         this.limitClones = compound.func_74762_e("SRPLimitClones");
      }

      if (compound.func_74764_b("SRPLimitClonesLife")) {
         this.limitClonesLife = compound.func_74762_e("SRPLimitClonesLife");
      }

      if (compound.func_74764_b("SRPLimitClonesCooldown")) {
         this.limitClonesCooldown = compound.func_74762_e("SRPLimitClonesCooldown");
      }

      if (compound.func_74764_b("SRPShadowDamage")) {
         this.shadowDamage = compound.func_74760_g("SRPShadowDamage");
      }

      if (compound.func_74764_b("SRPShadowDamageTick")) {
         this.shadowDamageTick = compound.func_74762_e("SRPShadowDamageTick");
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.shakeee > 0) {
         this.shakeee--;
      }

      if (this.shadowDamageRCooldown > 0.0F) {
         this.shadowDamageRCooldown--;
      }

      if (this.shadowDamageR > 0.0F && this.shadowDamageRCooldown == 0) {
         this.shadowDamageR -= 0.01F;
      }

      if (this.showC > -10) {
         this.showC--;
      }

      if (this.field_70146_Z.nextInt(10) == 0 && this.showC <= -10) {
         this.showC = this.field_70146_Z.nextInt(1) * 60;
      }

      if (this.srpTicks == 10 && this.field_70146_Z.nextInt(10) == 0) {
         this.shadowDamageR = 0.6F;
         this.shadowDamageRCooldown = 40;
         this.field_70170_p.func_72960_a(this, (byte)42);
      }

      if (!this.field_70170_p.field_72995_K) {
         if (this.srpTicks == 5 || this.srpTicks == 15) {
            this.setEffectsTargets(true);
         }

         this.shadowDamageTick--;
         if (this.shadowDamageTick <= 0) {
            this.shadowDamage = 0.0F;
         }

         if (this.srpTicks == 10) {
            this.checkShadow(100);
         }
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else if (source == DamageSource.field_76380_i || source == DamageSource.field_76368_d) {
         return super.func_70097_a(source, amount);
      } else if (!this.getShadowStatus() && !this.getCloneC()) {
         boolean flag = super.func_70097_a(source, amount);
         if (flag && source.func_76346_g() instanceof EntityLivingBase) {
            ((EntityLivingBase)source.func_76346_g()).func_70097_a(DamageSource.func_76358_a(this), this.damageAmountReduced / 2.0F);
            this.attackEntityAsMobMinimum((EntityLivingBase & EntityLivingBase)source.func_76346_g(), this.damageAmountReduced / 2.0F);
         }

         return flag;
      } else if (this.field_70170_p.func_175724_o(this.func_180425_c()) <= 0.46666667F
         && this.field_70170_p.func_175642_b(EnumSkyBlock.BLOCK, this.func_180425_c()) <= 7) {
         this.shakeee = 15;
         this.shadowDamageR = 0.6F;
         this.shadowDamageRCooldown = 40;
         this.showC = 15;
         this.field_70170_p.func_72960_a(this, (byte)41);
         boolean flag = super.func_70097_a(source, 0.0F);
         if (flag) {
            this.field_70180_af.func_187227_b(HIT, (byte)3);
         }

         return flag;
      } else {
         this.shadowDamage += amount;
         this.shadowDamageTick = 100;
         if (this.shadowDamage >= this.func_110138_aP() * 0.1F && !this.getCloneC()) {
            this.setShadowStatus(false);
            EntityPCosmical lol = this.getThis();
            if (lol != null) {
               this.spawnCloneCosmical(lol);
            }

            this.shadowDamageTick = 200;
            this.shadowDamage = 0.0F;
         }

         this.shakeee = 15;
         this.shadowDamageR = 0.6F;
         this.shadowDamageRCooldown = 40;
         this.showC = 15;
         this.field_70170_p.func_72960_a(this, (byte)41);
         this.field_70180_af.func_187227_b(HIT, (byte)3);
         boolean flag = super.func_70097_a(source, 0.0F);
         if (flag) {
            this.field_70180_af.func_187227_b(HIT, (byte)3);
         }

         return flag;
      }
   }

   @Override
   public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
      super.func_70624_b(entitylivingbaseIn);
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      super.func_70645_a(cause);
      if (!this.field_70170_p.field_72995_K) {
         Entity flag = this.field_70170_p.func_73045_a(this.limitClones);
         if (flag != null && !this.getCloneC()) {
            flag.func_70097_a(DamageSource.field_76380_i, 100000.0F);
         }
      }
   }

   protected abstract EntityPCosmical getThis();

   public void func_180430_e(float distance, float damageMultiplier) {
   }

   protected void checkShadow(int chance) {
      if (this.getParasiteStatus() < 3) {
         if (this.getCloneC()) {
            this.limitClonesLife++;
            if (this.limitClonesLife > 22) {
               this.particleStatus((byte)7);
               Entity flag = this.field_70170_p.func_73045_a(this.limitClones);
               if (flag instanceof EntityPCosmical) {
                  EntityPCosmical original = (EntityPCosmical)flag;
                  original.particleStatus((byte)7);
                  original.limitClones = 0;
                  original.setShadowStatus(true);
                  original.limitClonesCooldown = 10;
               }

               this.func_70106_y();
            }
         } else {
            if (this.getShadowStatus()) {
               this.limitClonesCooldown--;
               if (this.field_70146_Z.nextInt(chance) == 0 && this.limitClonesCooldown <= 0) {
                  EntityPCosmical lol = this.getThis();
                  if (lol != null) {
                     this.spawnCloneCosmical(lol);
                  }
               }
            } else {
               Entity flag = this.field_70170_p.func_73045_a(this.limitClones);
               if (flag == null) {
                  if (this.shadowDamageTick <= 0) {
                     this.particleStatus((byte)7);
                     this.limitClones = 0;
                     this.setShadowStatus(true);
                     this.limitClonesCooldown = 10;
                  }
               } else if (this.func_70638_az() != null) {
                  ((EntityPCosmical)flag).func_70624_b(this.func_70638_az());
               }
            }
         }
      }
   }

   protected void spawnCloneCosmical(EntityPCosmical entityout) {
      entityout.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
      entityout.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(entityout)), null);
      if (this.func_145818_k_()) {
         entityout.func_96094_a("--" + this.func_95999_t() + "--");
         entityout.func_174805_g(this.func_174833_aM());
      }

      this.field_70170_p.func_72838_d(entityout);
      entityout.particleStatus((byte)7);
      this.limitClones = entityout.func_145782_y();
      entityout.limitClones = this.func_145782_y();
      this.setShadowStatus(false);
      entityout.setCloneC();
      entityout.func_110148_a(SharedMonsterAttributes.field_111263_d)
         .func_111128_a(entityout.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() * 1.33);
      entityout.func_110148_a(SharedMonsterAttributes.field_111264_e)
         .func_111128_a(entityout.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * 0.5);
   }

   public int shakingC() {
      return this.shakeee;
   }

   public int showC() {
      return this.showC;
   }

   public boolean getShadowStatus() {
      return (Boolean)this.field_70180_af.func_187225_a(SHADOW);
   }

   public void setShadowStatus(boolean in) {
      this.field_70180_af.func_187227_b(SHADOW, in);
      if (in) {
         this.shadowDamageR = 0.6F;
         this.shadowDamageRCooldown = 40;
         this.field_70170_p.func_72960_a(this, (byte)41);
      }
   }

   public boolean getCloneC() {
      return (Boolean)this.field_70180_af.func_187225_a(CLONE);
   }

   public void setCloneC() {
      this.field_70180_af.func_187227_b(CLONE, true);
   }

   private void setTargetedEntity(int entityId) {
      for (DataParameter<Integer> mob : this.victims) {
         if ((Integer)this.field_70180_af.func_187225_a(mob) == entityId) {
            return;
         }
      }

      for (DataParameter<Integer> mobx : this.victims) {
         if ((Integer)this.field_70180_af.func_187225_a(mobx) == 0) {
            this.field_70180_af.func_187227_b(mobx, entityId);
            return;
         }
      }
   }

   private void updateTargets() {
      for (DataParameter<Integer> mob : this.victims) {
         if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) {
            Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(mob));
            if (entity == null) {
               this.field_70180_af.func_187227_b(mob, 0);
            } else {
               if (!((EntityLivingBase)entity).func_70089_S()) {
                  this.field_70180_af.func_187227_b(mob, 0);
                  return;
               }

               if (((EntityLivingBase)entity).func_70068_e(this) > 576.0) {
                  this.field_70180_af.func_187227_b(mob, 0);
                  return;
               }
            }
         }
      }
   }

   private boolean hasTargetedEntity() {
      this.updateTargets();

      for (DataParameter<Integer> mob : this.victims) {
         if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) {
            return true;
         }
      }

      return false;
   }

   private boolean fullTargets() {
      this.updateTargets();
      int size = this.victims.size();
      int count = 0;

      for (DataParameter<Integer> mob : this.victims) {
         if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) {
            count++;
         }
      }

      return size == count;
   }

   private void resetTargets() {
      for (DataParameter<Integer> mob : this.victims) {
         this.field_70180_af.func_187227_b(mob, 0);
      }
   }

   private void setEffectsTargets(boolean effects) {
      for (DataParameter<Integer> mob : this.victims) {
         if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) {
            Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(mob));
            if (entity == null) {
               this.field_70180_af.func_187227_b(mob, 0);
            } else {
               EntityLivingBase mobis = (EntityLivingBase)entity;
               this.shadowDamageR = 0.6F;
               this.shadowDamageRCooldown = 40;
               this.field_70170_p.func_72960_a(this, (byte)41);
               if (!mobis.func_70089_S()) {
                  this.field_70180_af.func_187227_b(mob, 0);
               } else {
                  Collection<PotionEffect> potionsTarget = mobis.func_70651_bq();
                  List<Potion> potionsToRemove = new ArrayList<>();

                  for (PotionEffect potion : potionsTarget) {
                     if (!potion.func_188419_a().func_76398_f()) {
                        float amp = potion.func_76458_c() + 1;
                        potionsToRemove.add(potion.func_188419_a());
                        this.func_70691_i(this.func_110138_aP() * (this.hackHeal * amp));
                     }
                  }

                  for (Potion potionx : potionsToRemove) {
                     mobis.func_184589_d(potionx);
                  }

                  if (effects && this.hackEffects.length > 0) {
                     String rando = this.hackEffects[this.field_70146_Z.nextInt(this.hackEffects.length)];
                     String[] here = rando.split(";");
                     Potion potionx = Potion.func_180142_b(here[1]);
                     if (potionx != null) {
                        SRPPotions.applyStackPotion(potionx, mobis, Integer.parseInt(here[0]), Integer.parseInt(here[2]));
                     }
                  }
               }
            }
         }
      }
   }

   public ArrayList<EntityLivingBase> getTargetedEntityVictims() {
      if (!this.hasTargetedEntity()) {
         return new ArrayList<>();
      } else {
         ArrayList<EntityLivingBase> mobs = new ArrayList<>();

         for (DataParameter<Integer> mob : this.victims) {
            if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) {
               Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(mob));
               if (entity != null) {
                  mobs.add((EntityLivingBase)entity);
               }
            }
         }

         return mobs;
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      switch (id) {
         case 41:
            this.shakeee = 15;
            this.shadowDamageR = 0.6F;
            this.shadowDamageRCooldown = 40;
            this.showC = 15;
            break;
         case 42:
            this.shadowDamageR = 0.6F;
            this.shadowDamageRCooldown = 40;
            break;
         default:
            super.func_70103_a(id);
      }
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 32:
            return this.skillOrb;
         case 33:
            return this.skillHack;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 32:
            this.skillOrb = in;
            return;
         case 33:
            this.skillHack = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 32:
            this.cosmicOrb();
            return;
         case 33:
            this.cosmicHacking();
         default:
            super.doSpecialSkill(id);
      }
   }

   private void cosmicOrb() {
      if (this.func_70638_az() == null) {
         this.skillOrb = true;
         this.setParasiteStatus(0);
         this.borderOrb = 0;
         this.limitOrb = 0;
      } else if (this.borderOrb != -1 && (this.orbVersionCooldown <= 0 || this.borderOrb != 0) && this.func_70638_az().func_70089_S() && !this.getCloneC()) {
         if (this.borderOrb == 0) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
               )
               .func_186662_g(this.fuseOrb / 2 + 1);

            for (EntityPMalleable mob : this.field_70170_p.func_72872_a(EntityPMalleable.class, axisalignedbb)) {
               if (mob != this && mob.getParasiteType() <= this.getParasiteType()) {
                  mob.setOrbVersionCooldown(4);
               }
            }
         }

         this.setParasiteStatus(19);
         this.func_70661_as().func_75499_g();
         if (this.field_70173_aa % 20 == 0) {
            this.borderOrb++;
            if (this.borderOrb < 6) {
               if (this.borderOrb % 2 != 0) {
                  Entity ttt = new EntityOrbScary(this.field_70170_p, this, this.fuseOrb, this.orbStartTimer, false);
                  ttt.func_82149_j(this.func_70638_az());
                  ttt.field_70163_u = ttt.field_70163_u - this.func_70638_az().func_70047_e();
                  this.field_70170_p.func_72838_d(ttt);
                  this.func_184185_a(SRPSounds.ORB_S, 1.0F, 1.0F);
               }
            } else {
               if (this.borderOrb > 10) {
                  this.skillOrb = true;
                  this.setParasiteStatus(0);
                  this.borderOrb = 0;
                  this.limitOrb = 0;
               }
            }
         }
      } else {
         this.skillOrb = true;
         this.setParasiteStatus(0);
         this.borderOrb = 0;
         this.limitOrb = 0;
      }
   }

   private void cosmicHacking() {
      if (this.func_70638_az() != null && !this.getCloneC() && this.field_70122_E && this.getShadowStatus()) {
         this.setParasiteStatus(20);
         this.func_70661_as().func_75499_g();
         if (this.field_70173_aa % 20 == 0) {
            this.borderHack++;
            if (this.borderHack >= 3) {
               if (!this.fullTargets()) {
                  AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                        this.field_70165_t,
                        this.field_70163_u,
                        this.field_70161_v,
                        this.field_70165_t + 1.0,
                        this.field_70163_u + 1.0,
                        this.field_70161_v + 1.0
                     )
                     .func_186662_g(24.0);

                  for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
                     if (mob != this && !(mob instanceof EntityParasiteBase) && mob.func_70089_S()) {
                        if (mob instanceof EntityPlayer) {
                           if (((EntityPlayer)mob).func_184812_l_()) {
                              continue;
                           }

                           this.func_184195_f(true);
                        }

                        this.setTargetedEntity(mob.func_145782_y());
                     }
                  }
               }
            } else {
               this.shadowDamageR = 0.6F;
               this.shadowDamageRCooldown = 40;
               this.field_70170_p.func_72960_a(this, (byte)41);
               this.particleStatus((byte)7);
            }

            if (this.borderHack >= 7) {
               this.resetTargets();
               this.skillHack = true;
               this.setParasiteStatus(0);
               this.func_184195_f(false);
               this.borderHack = 0;
            }
         }
      } else {
         this.resetTargets();
         this.skillHack = true;
         this.setParasiteStatus(0);
         this.borderHack = 0;
         this.func_184195_f(false);
      }
   }
}
