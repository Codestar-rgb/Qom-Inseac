package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCrude;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCruxA extends EntityPCrude implements EntityCutomAttack {
   private float attackTimerM;
   private boolean upM;
   private float attackTimerR;
   private boolean upR;
   private int extraDamageCap;
   private int currentDamageTimes;
   private double baseDamage;
   private byte cooldownBack;
   private boolean canBack;
   private int limit;
   private int border;
   private double targetX;
   private double targetZ;
   private double mottY;
   private boolean skillThrow;

   public EntityCruxA(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.13333F, 3.3F);
      this.field_70138_W = 1.0F;
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, false, false, null, SRPConfig.primitiveSneakPen, SRPConfig.primitiveInviPen)
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  false,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.primitiveSneakPen,
                  SRPConfig.primitiveInviPen
               )
            );
      }

      this.type = 41;
      this.skillThrow = false;
      this.currentDamageTimes = 0;
      this.extraDamageCap = SRPConfigMobs.cruxaDamageCap;
      this.baseDamage = -1.0;
      this.cooldownBack = 0;
      this.canBack = true;
   }

   @Override
   public int getParasiteIDRegister() {
      return 62;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.11));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 60, 20, 12, true, 1));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.3, true, 0.0, 3.0));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.CRUXA_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.CRUXA_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.24);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.CRUXA_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.CRUXA_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.upM) {
         this.attackTimerM = (float)(this.attackTimerM + 0.2);
         if (this.attackTimerM > 0.9) {
            this.upM = false;
         }
      } else {
         this.attackTimerM = (float)(this.attackTimerM - 0.1);
      }

      if (this.upR) {
         this.attackTimerR = (float)(this.attackTimerR + 0.4);
         if (this.attackTimerR > 2.2) {
            this.upR = false;
         }
      } else {
         this.attackTimerR = (float)(this.attackTimerR - 0.2);
      }
   }

   private void popBack() {
      if (this.canBack) {
         if (this.srpTicks == 10) {
            this.cooldownBack++;
         }

         if (this.cooldownBack < 20) {
            return;
         }

         this.canBack = false;
         this.func_184185_a(SRPSounds.MOBEXPLOTION, 3.0F, 1.0F);
         this.cooldownBack = 0;
         if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i <= 180; i++) {
               if (i % 5 == 0) {
                  this.spawnParticlesGore(SRPEnumParticle.GSPLASH, 0, -1, -1, 5.0, 9.0);
               }
            }

            for (int ix = 0; ix <= 80; ix++) {
               if (ix % 5 == 0) {
                  this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
               }

               if (ix % 5 == 0) {
                  this.spawnParticles(SRPEnumParticle.GSPLASH, 0, -1, -1);
               }
            }

            return;
         }

         int range = 4;
         double i1 = MathHelper.func_76128_c(this.field_70163_u + 0.1);
         double l1 = this.field_70165_t;
         double i2 = this.field_70161_v;

         for (int k2 = -1 * range; k2 <= 1 * range && SRPConfig.paraGore; k2++) {
            for (int l2 = -1 * range; l2 <= 1 * range; l2++) {
               double i3 = l1 + k2;
               double l = i2 + l2;
               BlockPos blockpos = new BlockPos(i3, i1, l);
               Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
               Block blockDown = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
               if (block == Blocks.field_150350_a
                  && blockDown != Blocks.field_150350_a
                  && this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_185913_b()
                  && blockDown != SRPBlocks.InfestedStain
                  && this.field_70170_p.field_73012_v.nextInt(4) == 0) {
                  this.field_70170_p.func_175656_a(blockpos, SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
               }
            }
         }

         for (int ix = 0; ix < 7 && SRPConfig.paraGore; ix++) {
            double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
            double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
            double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
            double d3 = d0 - this.field_70165_t;
            double d4 = d1 - this.field_70163_u;
            double d5 = d2 - this.field_70161_v;
            double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5 / (d6 / 4.0 + 0.1);
            d7 *= this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3F;
            d3 = d3 * d7 * 7.0;
            d4 = d4 * d7 * 12.0;
            d5 = d5 * d7 * 7.0;
            EntityGore bomb = new EntityGore(this.field_70170_p);
            bomb.setType((byte)1);
            bomb.func_82149_j(this);
            bomb.setMotion(d3, d4, d5, 0.28, 0.55);
            bomb.field_70163_u += 3.5;
            this.field_70170_p.func_72838_d(bomb);
         }
      } else {
         if (this.srpTicks == 10) {
            this.cooldownBack++;
         }

         if (this.cooldownBack > 15) {
            this.cooldownBack = 0;
            this.canBack = true;
         }
      }
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      if (this.extraDamageCap <= this.currentDamageTimes) {
         this.currentDamageTimes++;
         if (this.baseDamage == -1.0) {
            this.baseDamage = this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * SRPConfigMobs.cruxaDamageGain;
         }

         double getD = this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
         this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(getD + this.baseDamage);
      }
   }

   public float func_70047_e() {
      return 3.3F;
   }

   @SideOnly(Side.CLIENT)
   public boolean getBack() {
      return this.canBack;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.CRUX_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.CRUX_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.CRUX_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SRPSounds.MONSTER_STEP, 0.15F, 1.0F);
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      this.upM = true;
      this.attackTimerM = 0.0F;
      this.field_70170_p.func_72960_a(this, (byte)22);
      boolean flag = false;
      this.func_184185_a(SRPSounds.SWIPE, 2.0F, 1.0F);
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            entityIn.field_70165_t,
            entityIn.field_70163_u,
            entityIn.field_70161_v,
            entityIn.field_70165_t + 1.0,
            entityIn.field_70163_u + 1.0,
            entityIn.field_70161_v + 1.0
         )
         .func_186662_g(1.0);

      for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
         if (mob instanceof EntityParasiteBase) {
            if (this.func_70638_az() == mob) {
               this.func_70624_b(null);
               return false;
            }
         } else if (mob != this && this.func_70685_l(mob) && this.func_70652_k(mob)) {
            flag = true;
         }
      }

      return flag;
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (SRPConfigSystems.rageEnable) {
         this.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 1333320, 0, false, false));
      }

      return floo;
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74780_a("parasitecruxdamage", this.baseDamage);
      compound.func_74768_a("parasitecruxtimes", this.currentDamageTimes);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("parasitecruxdamage", 99)) {
         this.baseDamage = compound.func_74769_h("parasitecruxdamage");
      }

      if (compound.func_150297_b("parasitecruxtimes", 99)) {
         this.currentDamageTimes = compound.func_74762_e("parasitecruxtimes");
      }
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimerM() {
      return this.attackTimerM;
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimerR() {
      return this.attackTimerR;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 22) {
         this.upM = true;
         this.attackTimerM = 0.0F;
      } else if (id == 23) {
         this.upR = true;
         this.attackTimerR = 0.0F;
      } else if (id == 24) {
         this.canBack = true;
      } else if (id == 25) {
         this.canBack = false;
      } else {
         super.func_70103_a(id);
      }
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillThrow;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillThrow = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.throwBlock();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void throwBlock() {
      EntityLivingBase entitylivingbase = this.func_70638_az();
      if (entitylivingbase == null) {
         this.skillThrow = true;
         this.setParasiteStatus(0);
         this.border = 0;
         this.limit = 0;
      } else {
         this.targetX = entitylivingbase.field_70165_t;
         this.targetZ = entitylivingbase.field_70161_v;
         if (!(this.func_70068_e(entitylivingbase) < 144.0)
            && !(entitylivingbase.field_70163_u < this.field_70163_u)
            && !(entitylivingbase.field_70163_u > this.field_70163_u + 3.0)) {
            this.mottY = this.func_70068_e(entitylivingbase);
            if (this.field_70173_aa % 20 == 0) {
               this.border++;
               IBlockState b = this.blockNotAvailable();
               if (b == null) {
                  this.limit++;
                  if (this.limit >= 5) {
                     this.skillThrow = true;
                     this.setParasiteStatus(0);
                     this.border = 0;
                     this.limit = 0;
                  }
               } else {
                  this.skillBreakBlocks();
                  this.setParasiteStatus(10);
                  this.func_70661_as().func_75492_a(this.targetX, entitylivingbase.field_70163_u, this.targetZ, 0.0);
                  this.field_70170_p.func_72960_a(this, (byte)23);
                  BlockPos pos = this.func_180425_c();
                  Vec3d vec3d = this.func_70676_i(1.0F);
                  EntityFallingBlock entityfallingblock = new EntityFallingBlock(
                     this.field_70170_p,
                     this.field_70165_t + vec3d.field_72450_a,
                     this.field_70163_u + this.func_70047_e() - 0.7,
                     this.field_70161_v + vec3d.field_72449_c,
                     b
                  );
                  entityfallingblock.field_145812_b = -100;
                  this.targetX = this.targetX + (Math.random() * 2.0 - 1.0);
                  this.targetZ = this.targetZ + (Math.random() * 2.0 - 1.0);
                  double d0 = this.targetX - this.field_70165_t;
                  double d1 = this.targetZ - this.field_70161_v;
                  double f = MathHelper.func_76133_a(d0 * d0 + d1 * d1);
                  entityfallingblock.field_70181_x = this.mottY * 0.0;
                  if (entitylivingbase.field_70163_u >= this.field_70163_u + 2.0) {
                     entityfallingblock.field_70181_x += 0.5;
                  }

                  if (entitylivingbase.field_70163_u <= this.field_70163_u - 2.0) {
                     entityfallingblock.field_70181_x += -0.5;
                  }

                  double mmx = d0 / f * (this.mottY * 0.009) * 0.9 + entityfallingblock.field_70159_w * 0.3;
                  double mmz = d1 / f * (this.mottY * 0.009) * 0.9 + entityfallingblock.field_70179_y * 0.3;
                  entityfallingblock.field_70159_w += mmx;
                  entityfallingblock.field_70179_y += mmz;
                  EntityDamage damage = new EntityDamage(
                     this.field_70170_p,
                     entityfallingblock.field_70165_t,
                     entityfallingblock.field_70163_u,
                     entityfallingblock.field_70161_v,
                     0.0F,
                     this,
                     10.0F + b.func_185887_b(this.field_70170_p, this.func_180425_c()),
                     false,
                     0.0F
                  );
                  damage.setFollower(entityfallingblock);
                  this.field_70170_p.func_72838_d(entityfallingblock);
                  this.field_70170_p.func_72838_d(damage);
                  if (this.limit >= 5 || this.border > 8) {
                     this.skillThrow = true;
                     this.setParasiteStatus(0);
                     this.border = 0;
                     this.limit = 0;
                  }
               }
            }
         } else {
            this.skillThrow = true;
            this.setParasiteStatus(0);
            this.border = 0;
            this.limit = 0;
         }
      }
   }

   private IBlockState blockNotAvailable() {
      IBlockState b = null;
      int range = 2;
      int newX = this.field_70146_Z.nextInt(range) + 1;
      int newZ = this.field_70146_Z.nextInt(range) + 1;
      if (this.field_70146_Z.nextBoolean()) {
         newX *= -1;
      }

      if (this.field_70146_Z.nextBoolean()) {
         newZ *= -1;
      }

      BlockPos pos = ParasiteEventEntity.getFloor(this.field_70170_p, new BlockPos(this.field_70165_t + newX, this.field_70163_u, this.field_70161_v + newZ), 4);
      if (pos != null) {
         pos = pos.func_177977_b();
         if (this.checkBlock(this.field_70170_p.func_180495_p(pos).func_177230_c(), this.field_70170_p.func_180495_p(pos), pos)) {
            return null;
         }

         b = this.field_70170_p.func_180495_p(pos);
         this.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
      }

      return b;
   }

   private boolean checkBlock(Block in, IBlockState state, BlockPos pos) {
      return in == Blocks.field_150350_a
         || in instanceof BlockLiquid
         || !state.func_185913_b()
         || state.func_185887_b(this.field_70170_p, pos) <= 0.0F
         || this.blockException(in.getRegistryName().toString());
   }
}
