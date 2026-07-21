package com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvadeDash;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanColony;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
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

public class EntityPheon extends EntityPPreeminent implements EntityCanColony, EntityBodyParts, IRangedAttackMob, EntityCutomAttack, EntityCanShoot {
   private EntityBody head;
   private EntityBody middle;

   public EntityPheon(World worldIn) {
      super(worldIn);
      this.func_70105_a(2.0F, 3.6F);
      this.field_70158_ak = true;
      this.type = 63;
      this.borderOrb = -1;
      this.field_70138_W = 1.0F;
      this.head = new EntityBody(this, 2.0F, 7.3F, 1.0F, -2.5F, 0.0F, -1, 1, false, 0.2F);
      this.middle = new EntityBody(this, 1.9F, 3.5F, 1.0F, 0.0F, 3.7F, 1, 2, false, 0.2F);
   }

   @Override
   public int getParasiteIDRegister() {
      return 87;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.15));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 10.0F));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeStatusAOE(this, 1.0, false, 100.0, 5.0));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.0, 40, 40.0F, false));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackProjectile(this, 60, 10, 3));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, SRPConfig.preeminentFollow, true, 3));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvadeDash(this, 40, 2, 4, 5.0, 100));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.PHEON_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.PHEON_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.283);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.PHEON_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.preeminentFollow);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.head.func_70071_h_();
      this.middle.func_70071_h_();
   }

   @Override
   protected void func_70619_bc() {
      super.func_70619_bc();
   }

   public void func_96094_a(String name) {
      super.func_96094_a(name);
   }

   public void func_184178_b(EntityPlayerMP player) {
   }

   public void func_184203_c(EntityPlayerMP player) {
      super.func_184203_c(player);
   }

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            entityIn.field_70165_t,
            entityIn.field_70163_u,
            entityIn.field_70161_v,
            entityIn.field_70165_t + 1.0,
            entityIn.field_70163_u + 1.0,
            entityIn.field_70161_v + 1.0
         )
         .func_72314_b(5.0, 2.0, 5.0);
      List<EntityLivingBase> moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
      if (moblist.size() > 4) {
         axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_72314_b(5.0, 3.0, 5.0);
         moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
         float luck = (float)(SRPAttributes.TERLA_ATTACK_DAMAGE * 2.0);

         for (EntityLivingBase mob : moblist) {
            if (mob != null && !(mob instanceof EntityParasiteBase) && mob != this) {
               EntityDamage damage = new EntityDamage(
                  this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, 0.0F, this, luck, false, 3.0F
               );
               this.field_70170_p.func_72838_d(damage);
            }
         }

         return true;
      } else {
         for (EntityLivingBase mobx : moblist) {
            if (mobx != null && !(mobx instanceof EntityParasiteBase)) {
               this.func_70652_k(mobx);
            }
         }

         return !moblist.isEmpty();
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag) {
      }

      return flag;
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      return this.func_70097_a(source, amount);
   }

   @Override
   public void setBodyPartDead(int id) {
   }

   public int func_184649_cE() {
      return 3;
   }

   @Override
   public boolean func_70687_e(PotionEffect potioneffectIn) {
      return potioneffectIn.func_188419_a() == MobEffects.field_76436_u ? false : super.func_70687_e(potioneffectIn);
   }

   public float func_70047_e() {
      return 4.7F;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.MOBSILENCE;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.pheonOrbEffects, mobs);
      }

      return flag;
   }

   protected float func_70599_aP() {
      return 5.0F;
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      if (this.middle != null) {
         this.field_70170_p.func_72973_f(this.middle);
      }

      super.func_70106_y();
   }

   public void func_184724_a(boolean swingingArms) {
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
         switch (this.field_70146_Z.nextInt(1)) {
            case 0:
               this.setSkin(1);
               this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.PHEON_HEALTH * 0.5);
               this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.PHEON_ATTACK_DAMAGE * 1.5);
               this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
         }
      }

      return floo;
   }

   @Override
   public Entity getProj(double accelX, double accelY, double accelZ) {
      this.func_184185_a(SRPSounds.DORPA_RANGE, 2.0F, 1.0F);
      Vec3d vec3d = this.func_70676_i(1.0F);
      EntityProjectileHomming entitylargefireball = new EntityProjectileHomming(
         this.field_70170_p, this, this.func_70638_az(), SRPAttributes.ORONCO_ATTACK_DAMAGE
      );
      entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
      entitylargefireball.field_70163_u = this.field_70163_u + this.func_70047_e() - 0.2;
      entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
      return entitylargefireball;
   }

   @Override
   public void playProjSound() {
   }

   @Override
   public void skillBreakBlocks() {
      if (this.getBlockH() != 0.0F) {
         int blocksbroke = 0;
         int i1 = MathHelper.func_76128_c(this.field_70163_u + 0.1);
         double l1 = this.field_70165_t;
         double i2 = this.field_70161_v;
         boolean flag = false;
         int Brangeatm = this.BGrange;
         int offsetT = 0;
         if (this.func_70638_az() != null) {
            EntityLivingBase target = this.func_70638_az();
            if (target.func_70092_e(this.field_70165_t, target.field_70163_u, this.field_70161_v) < 9.0) {
               if (target.field_70163_u - this.field_70163_u < -1.0) {
                  offsetT -= 2;
                  if (!this.field_70122_E) {
                     offsetT--;
                  }
               } else if (target.field_70163_u - this.field_70163_u > 2.0) {
                  offsetT++;
                  this.BGrange = 0;
               }
            }
         }

         for (int k2 = -1 * this.BGrange; k2 <= 1 * this.BGrange; k2++) {
            for (int l2 = -1 * this.BGrange; l2 <= 1 * this.BGrange; l2++) {
               for (int j = 1 + offsetT; j <= 8 + offsetT; j++) {
                  double i3 = l1 + k2;
                  double k = i1 + j;
                  double l = i2 + l2;
                  BlockPos blockpos = new BlockPos(i3, k, l);
                  IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                  Block block = iblockstate.func_177230_c();
                  float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                  if (bHard <= this.getBlockH()
                     && bHard >= 0.0F
                     && (!(block instanceof IMetaName) || block == SRPBlocks.ParasiteCanister)
                     && block != SRPBlocks.BiomeHeart
                     && block != SRPBlocks.ColonyHeart
                     && block != SRPBlocks.ParasiteRubbleDense
                     && block != SRPBlocks.ParasiteCanisterActive) {
                     String name = block.getRegistryName().toString();
                     if (!this.blockException(name)
                        && block != Blocks.field_150350_a
                        && block.canEntityDestroy(iblockstate, this.field_70170_p, blockpos, this)
                        && ForgeEventFactory.onEntityDestroyBlock(this, blockpos, iblockstate)) {
                        if (!SRPConfig.cystActive) {
                           this.destroyBlockPos(blockpos, SRPConfig.doTileDrops);
                        } else {
                           int meta = block.func_176201_c(iblockstate);
                           flag = this.destroyBlockPos(blockpos, false) || flag;
                           if (SRPConfig.doTileDrops) {
                              this.addToBlockInv(name + ";" + meta);
                           }
                        }

                        blocksbroke++;
                     }
                  }
               }
            }
         }

         this.BGrange = Brangeatm;
         this.SkillBGflag = true;
      }
   }

   @Override
   public boolean onlySpawnInside() {
      return false;
   }
}
