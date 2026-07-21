package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWave;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHost extends EntityPMalleable implements IRangedAttackMob, EntityCutomAttack {
   private boolean open;
   private float buried;
   private int blockI;
   private int buriedC;
   private static final DataParameter<Boolean> UP = EntityDataManager.func_187226_a(EntityHost.class, DataSerializers.field_187198_h);
   private float attackTimer;
   private boolean up;
   private int border;
   private boolean skillshockwave;

   public EntityHost(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.9F, 0.25F);
      this.borderOrb = -1;
      this.field_70714_bg.func_85156_a(this.folow);
      this.func_184644_a(PathNodeType.WATER, -1.0F);
      this.canModRender = 0;
      this.type = 11;
      this.buried = 4.8F;
      this.field_70138_W = 1.0F;
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, SRPConfig.primitiveWalls, false, null, SRPConfig.primitiveSneakPen, SRPConfig.primitiveInviPen
            )
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  SRPConfig.primitiveWalls,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !(entity instanceof EntityVillager)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.primitiveSneakPen,
                  SRPConfig.primitiveInviPen
               )
            );
      }

      this.field_70728_aV = SRPAttributes.XP_PRIMITIVE;
      this.damageCap = SRPConfig.primitiveCap;
      this.canD = SRPConfig.primitivedespawn;
      this.foodSteal = SRPConfig.primitiveFoodSteal;
      this.pointCap = SRPConfig.primitivePointCap;
      this.pointReduction = SRPConfig.primitivePointRed;
      this.chanceLearn = SRPConfig.primitiveChanceLe;
      this.chanceLearnFire = SRPConfig.primitiveChanceLeFire;
      this.DamageTypeCap = SRPConfig.primitivePointDamCap;
      this.MiniDamage = SRPConfig.primitiveMinDamage;
      this.regen = SRPConfig.primitiveRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.primitiveOneMindDeathV;
      this.regenEff = 1;
      this.adaptationCap = 0.95F;
      this.type = 11;
   }

   @Override
   public int getParasiteIDRegister() {
      return 48;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 0.0, 3.0, true));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 8.0F));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.3, 50, 8.0F, false));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 40, (int)SRPConfig.primitiveFollow, 2, false, 1, true));
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(UP, false);
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.HOST_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.HOST_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.12);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.HOST_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
   }

   @Override
   public void func_70636_d() {
      if (this.func_175446_cd()) {
         this.setParasiteStatus(3);
         this.setBurrowed(true);
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
         this.buriedC = 80;
         if (this.buriedC > 0) {
            this.buriedC--;
         }

         this.blockI++;
         if (this.up) {
            this.attackTimer = (float)(this.attackTimer + 0.2);
            if (this.attackTimer > 1.0F) {
               this.up = false;
            }
         } else {
            this.attackTimer = (float)(this.attackTimer - 0.1);
         }

         this.checkBurrowed();
      } else {
         super.func_70636_d();
         if (this.buriedC > 0) {
            this.buriedC--;
         }

         this.blockI++;
         if (this.up) {
            this.attackTimer = (float)(this.attackTimer + 0.2);
            if (this.attackTimer > 1.0F) {
               this.up = false;
            }
         } else {
            this.attackTimer = (float)(this.attackTimer - 0.1);
         }

         this.spawnRupters();
         this.checkBurrowed();
         this.checkSpeed();
         this.teleportByGround();
         if (!this.getBurrowed() && (this.field_70165_t != this.field_70169_q || this.field_70161_v != this.field_70166_s)) {
            this.spawnGroundParticles();
         }

         if (!this.field_70170_p.field_72995_K && this.srpTicks == 10 && this.killcount > SRPConfigMobs.hostHerd && ParasiteEventEntity.canSpawnNext) {
            ParasiteEventEntity.spawnNext(this, new EntityHostII(this.field_70170_p), true, true);
         }
      }
   }

   private void spawnRupters() {
      if (SRPConfigMobs.mudoEnabled) {
         if (!this.field_70170_p.field_72995_K && !this.getBurrowed()) {
            if (this.func_70638_az() != null && this.field_70146_Z.nextInt(150) == 0) {
               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
                  )
                  .func_186662_g(16.0);
               List<EntityMudo> moblist = this.field_70170_p.func_72872_a(EntityMudo.class, axisalignedbb);
               if (moblist.size() <= 4) {
                  EntityMudo out = new EntityMudo(this.field_70170_p);
                  out.func_82149_j(this);
                  this.field_70170_p.func_72838_d(out);
                  out.func_70624_b(this.func_70638_az());
               }
            } else if (this.field_70146_Z.nextInt(400) == 0) {
               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
                  )
                  .func_186662_g(16.0);
               List<EntityMudo> moblist = this.field_70170_p.func_72872_a(EntityMudo.class, axisalignedbb);
               if (moblist.size() <= 3) {
                  EntityMudo out = new EntityMudo(this.field_70170_p);
                  out.func_82149_j(this);
                  this.field_70170_p.func_72838_d(out);
               }
            }
         }
      }
   }

   private void checkBurrowed() {
      if (this.getBurrowed()) {
         if (this.field_70131_O < 3.5F) {
            this.func_70105_a(0.9F, this.field_70131_O + 0.05F);
         }

         if (this.buried >= 0.0F) {
            this.buried -= 0.08F;
            this.spawnGroundParticles();
            this.open = false;
            this.field_70170_p.func_72960_a(this, (byte)12);
         } else {
            this.open = true;
            this.field_70170_p.func_72960_a(this, (byte)11);
         }
      } else {
         if (this.field_70131_O > 0.25F) {
            this.func_70105_a(0.9F, this.field_70131_O - 0.05F);
         }

         if (this.buried < 4.8F) {
            this.buried += 0.08F;
            this.spawnGroundParticles();
            this.open = false;
            this.field_70170_p.func_72960_a(this, (byte)12);
         }
      }
   }

   private void checkSpeed() {
      if (!this.field_70170_p.field_72995_K) {
         if (this.func_70638_az() != null) {
            if (this.func_70068_e(this.func_70638_az()) <= 9.0) {
               this.setParasiteStatus(3);
               this.setBurrowed(true);
               this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
               this.buriedC = 80;
            } else if (this.buriedC <= 0) {
               this.setParasiteStatus(0);
               this.setBurrowed(false);
               this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.12);
            }
         } else if (this.getBurrowed() && this.buriedC <= 0) {
            this.setParasiteStatus(0);
            this.setBurrowed(false);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.12);
         }

         if (this.blockI > 20) {
            this.blockI = 0;
            IBlockState block = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
            if (!(block.func_177230_c() instanceof IMetaName)
               && block.func_185913_b()
               && this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a) {
               this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.InfestRemain.func_176203_a(1));
            }
         }
      }
   }

   private void spawnGroundParticles() {
      IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
      if (state.func_177230_c() != Blocks.field_150350_a) {
         int id = Block.func_176210_f(state);

         for (int i = 0; i < 15; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.BLOCK_CRACK,
                  this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 3.0F - this.field_70130_N,
                  this.field_70163_u,
                  this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 3.0F - this.field_70130_N,
                  this.field_70146_Z.nextGaussian() * 0.02,
                  this.field_70146_Z.nextGaussian() * 0.02,
                  this.field_70146_Z.nextGaussian() * 0.02,
                  new int[]{id}
               );
         }
      }
   }

   private void teleportByGround() {
      if (this.srpTicks == 10) {
         if (!this.getBurrowed() && this.field_70146_Z.nextInt(3) == 0 && this.field_70131_O <= 0.25F) {
            EntityLivingBase target = this.func_70638_az();
            if (target != null && target.func_70068_e(this) > 49.0 && ParasiteEventEntity.teleportDigging(this, 10.0F, target.func_180425_c(), 4, 1)) {
               this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 30, 30, false, false));
            }
         }
      }
   }

   public void func_70024_g(double x, double y, double z) {
      if (this.getBurrowed()) {
         super.func_70024_g(x, y, z);
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else if (source == DamageSource.field_76368_d) {
         return false;
      } else if (source == DamageSource.field_76380_i) {
         return super.func_70097_a(source, amount);
      } else if (this.getBurrowed() || !(this.field_70131_O < 1.0F)) {
         return super.func_70097_a(source, amount);
      } else if (source == DamageSource.field_76369_e) {
         return false;
      } else {
         return source.func_76346_g() == null ? super.func_70097_a(source, amount) : false;
      }
   }

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
      if (!this.func_70644_a(MobEffects.field_76421_d) || this.getBurrowed()) {
         if (!(target.func_70068_e(this) > 169.0)) {
            this.buriedC = 80;
            if (this.getBurrowed() && this.field_70131_O > 1.0F) {
               double d0 = target.field_70163_u + target.func_70047_e() - 1.1F;
               double d1 = target.field_70165_t + target.field_70159_w - this.field_70165_t;
               double d2 = d0 - this.field_70163_u;
               double d3 = target.field_70161_v + target.field_70179_y - this.field_70161_v;
               float f = MathHelper.func_76133_a(d1 * d1 + d3 * d3);
               EntityBomb bomb = new EntityBomb(this.field_70170_p, this, false);
               bomb.setFuse(80);
               bomb.setStren(0.0F);
               bomb.setSkin(1);
               bomb.setDamage((float)SRPAttributes.HOST_BOMBDAMAGE, 4);
               bomb.field_70125_A -= -20.0F;
               bomb.shootTwo(d1, d2 + f * 0.2F, d3, 0.75F, 8.0F);
               this.field_70170_p.func_72838_d(bomb);
               bomb.updateSTR();
            } else {
               this.setParasiteStatus(3);
               this.setBurrowed(true);
               this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
               this.func_70661_as().func_75499_g();
            }
         }
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      if (this.getBurrowed() && !(this.field_70131_O < 1.0F)) {
         boolean flag = super.func_70652_k(entityIn);
         if (flag) {
            this.buriedC += 160;
            if (this.field_70146_Z.nextInt(3) == 0) {
            }
         }

         return flag;
      } else if (entityIn == null) {
         return false;
      } else if (this.func_70068_e(entityIn) > 4.0) {
         return false;
      } else {
         this.setParasiteStatus(3);
         this.setBurrowed(true);
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
         this.buriedC = 160;
         return false;
      }
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      this.up = true;
      this.attackTimer = 0.0F;
      this.field_70170_p.func_72960_a(this, (byte)13);
      boolean flag = false;
      if (this.getBurrowed() && !(this.field_70131_O < 1.0F)) {
         this.func_184185_a(SRPSounds.SWIPE, 1.0F, 1.0F);
         this.func_184185_a(SRPSounds.SWIPE, 1.0F, 1.25F);
         this.func_184185_a(SRPSounds.SWIPE, 2.0F, 1.0F);
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_186662_g(3.0);

         for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (mob != this && !(mob instanceof EntityParasiteBase) && this.func_70685_l(mob) && this.func_70652_k(mob)) {
               flag = true;
            }
         }

         return flag;
      } else {
         return false;
      }
   }

   public boolean func_96092_aw() {
      return super.func_96092_aw();
   }

   @Override
   public boolean func_70687_e(PotionEffect potioneffectIn) {
      return super.func_70687_e(potioneffectIn);
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      this.particleStatus((byte)5);
      if (!this.field_70170_p.field_72995_K && this.killcount > SRPConfigMobs.hostHerd && !this.field_70128_L && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this, new EntityHostII(this.field_70170_p), true, true);
      }
   }

   protected SoundEvent func_184639_G() {
      return !this.getBurrowed() ? SRPSounds.HOST_UN : SRPSounds.HOST_EM;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.HOST_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.HOST_DEATH;
   }

   public boolean getBurrowed() {
      return (Boolean)this.field_70180_af.func_187225_a(UP);
   }

   public void setBurrowed(boolean in) {
      this.field_70180_af.func_187227_b(UP, in);
   }

   public float func_70047_e() {
      return this.field_70131_O;
   }

   @SideOnly(Side.CLIENT)
   public float getBurrowTimer() {
      return this.buried;
   }

   @SideOnly(Side.CLIENT)
   public boolean getOpen() {
      return this.open;
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimer() {
      return this.attackTimer;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 11) {
         this.open = true;
      } else if (id == 12) {
         this.open = false;
      } else if (id == 13) {
         this.up = true;
         this.attackTimer = 0.0F;
      } else if (id == 100) {
         for (int i = 0; i <= 1; i++) {
            this.spawnParticles(EnumParticleTypes.FLAME);
         }
      } else {
         super.func_70103_a(id);
      }
   }

   public void func_184724_a(boolean swingingArms) {
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillshockwave;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillshockwave = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.shockwave();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void shockwave() {
      this.func_70661_as().func_75499_g();
      if (this.border == 0) {
         float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F + 2.0F;
         this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0F, v);
         this.border++;
      } else {
         if (this.border <= 2) {
            this.field_70170_p.func_72960_a(this, (byte)100);
         }

         if (this.field_70173_aa % 20 == 0) {
            this.border++;
            if (this.func_70638_az() == null) {
               this.skillshockwave = true;
               this.border = 0;
            } else {
               if (this.border == 2) {
                  this.spawnShock();
                  this.up = true;
                  this.attackTimer = 0.0F;
                  this.field_70170_p.func_72960_a(this, (byte)12);
                  this.func_184185_a(SRPSounds.SWIPE, 2.0F, 1.0F);
               }

               if (this.border > 3) {
                  this.skillshockwave = true;
                  this.border = 0;
               }
            }
         }
      }
   }

   private void spawnShock() {
      EntityWave wa = new EntityWave(this.field_70170_p);
      float f19 = MathHelper.func_76126_a(this.field_70177_z * (float) (Math.PI / 180.0) - this.field_70704_bt * 0.01F);
      float f14 = (float) (Math.PI / 18);
      float f16 = MathHelper.func_76134_b(f14);
      float f4 = MathHelper.func_76134_b(this.field_70177_z * (float) (Math.PI / 180.0) - this.field_70704_bt * 0.01F);
      wa.func_70634_a(this.field_70165_t + -1.0 * (f19 * 3.0F * f16), this.field_70163_u, this.field_70161_v - -1.0 * (f4 * 3.0F * f16));
      wa.setDamages(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.3, this.MiniDamage, 1, 60);
      this.field_70170_p.func_72838_d(wa);
      wa.func_70624_b(this.func_70638_az());
   }
}
