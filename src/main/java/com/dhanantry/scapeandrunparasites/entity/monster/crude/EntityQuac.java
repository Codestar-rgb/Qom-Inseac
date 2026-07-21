package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFollowBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanHaveBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityQuac extends EntityParasiteBase implements EntityCanHaveBodies {
   private UUID following;
   private boolean canF;
   private BlockPos digTarget;
   public float diggingModel;
   public final float heightTwo = 2.4F;
   private int bodiesT;
   private int bodiesTOut;
   private static final DataParameter<Byte> PART = EntityDataManager.func_187226_a(EntityQuac.class, DataSerializers.field_187191_a);
   private static final DataParameter<Boolean> TAIL = EntityDataManager.func_187226_a(EntityQuac.class, DataSerializers.field_187198_h);
   private static final DataParameter<Boolean> DIG = EntityDataManager.func_187226_a(EntityQuac.class, DataSerializers.field_187198_h);

   public EntityQuac(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.321F, 1.2F);
      this.field_70138_W = 1.0F;
      this.canModRender = 1;
      this.field_70714_bg.func_85156_a(this.folow);
      this.func_184644_a(PathNodeType.WATER, -1.0F);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, SRPConfig.primitiveWalls, false, null, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen
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
      this.type = 31;
      this.foodSteal = SRPConfig.primitiveFoodSteal;
      this.MiniDamage = SRPConfig.primitiveMinDamage;
      this.oneMindDeathValue = SRPConfig.primitiveOneMindDeathV;
      this.valueEvDeath = SRPConfig.primitiveLoosingEPValue;
      this.diggingModel = 0.0F;
      this.canF = true;
      this.bodiesT = 0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 327;
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(PART, (byte)0);
      this.field_70180_af.func_187214_a(TAIL, false);
      this.field_70180_af.func_187214_a(DIG, false);
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(1, new EntityAIFollowBodies(this, 1.9F, 0.33, 7, 10000.0, 120));
      this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, 0.0));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.QUAC_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.QUAC_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.33);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.QUAC_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.handleDigging();
   }

   @Override
   public boolean func_70692_ba() {
      return super.func_70692_ba() && this.following == null;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      if (this.getDigging()) {
         return false;
      } else {
         boolean flag = super.func_70652_k(entityIn);
         if (flag) {
         }

         return flag;
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      this.madeRng = 0;
      if (source == DamageSource.field_76368_d) {
         return false;
      } else if (this.bodiesT - 1 >= this.getBodyNumber()) {
         return false;
      } else {
         boolean flag = super.func_70097_a(source, amount);
         if (flag && this.following != null) {
            Entity follo = this.getFather(this.following);
            if (follo == null) {
               this.following = null;
               return flag;
            }

            if (!follo.func_70089_S()) {
               this.following = null;
               return flag;
            }

            follo.func_70097_a(source, amount * 0.5F);
         }

         return flag;
      }
   }

   @Override
   protected void attackEntityFromCap(int go) {
      if (this.getBodyNumber() == 0) {
         super.attackEntityFromCap(go);
      }
   }

   public void func_70108_f(Entity entityIn) {
      if (!this.getDigging()) {
         super.func_70108_f(entityIn);
      }
   }

   public float func_70047_e() {
      return 1.0F;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
   }

   @Override
   protected void spawnGore() {
      int range = 2;
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
               && this.field_70170_p.field_73012_v.nextInt(3) == 0) {
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
            }
         }
      }

      if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
         && (
            this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
               || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
         )) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
         EntityRemain nnn = new EntityRemain(this.field_70170_p);
         nnn.func_70012_b(
            this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p() + 0.5, 0.0F, 0.0F
         );
         nnn.setParasite(EntityList.func_191301_a(this).toString());
         nnn.setSkin((byte)this.getSkin());
         nnn.setGoal(20 * SRPConfig.primitiveRemainValue);
         this.field_70170_p.func_72838_d(nnn);
      }

      for (int i = 0; i < 4 && SRPConfig.paraGore; i++) {
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
         d3 *= d7;
         d4 = d4 * d7 * 2.0;
         d5 *= d7;
         EntityGore bomb = new EntityGore(this.field_70170_p);
         bomb.setType((byte)2);
         bomb.func_82149_j(this);
         bomb.setMotion(d3, d4, d5, 0.15, 0.55);
         this.field_70170_p.func_72838_d(bomb);
      }
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void spawnEffectsGore() {
      for (int i = 0; i <= 60; i++) {
         if (i % 4 == 0) {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 150, 0, 0);
         }

         if (i % 5 == 0) {
            this.spawnParticles(SRPEnumParticle.GSPLASH, 2, -1, -1);
         }
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      if (this.following != null) {
         compound.func_186854_a("wormhead", this.following);
      }

      compound.func_74757_a("wormcanf", this.canF);
      compound.func_74774_a("wormpart", this.getBodyNumber());
      compound.func_74757_a("wormtail", this.getBodyTail());
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_186855_b("wormhead")) {
         this.following = compound.func_186857_a("wormhead");
      }

      if (compound.func_150297_b("wormcanf", 99)) {
         this.canF = compound.func_74767_n("wormcanf");
      }

      if (compound.func_150297_b("wormpart", 99)) {
         this.setBodyNumber(compound.func_74771_c("wormpart"));
      }

      if (compound.func_150297_b("wormtail", 99)) {
         this.setBodyTail(compound.func_74767_n("wormtail"));
      }
   }

   @Override
   public void skillBreakBlocks() {
      if (this.getBodyNumber() == 0) {
         super.skillBreakBlocks();
      }
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      if (this.getBodyNumber() == 0) {
         super.func_70074_a(entityLivingIn);
      } else {
         EntityParasiteBase head = this.getHead();
         if (head != null) {
            head.setKillC(1.0);
         }
      }
   }

   @Override
   public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
      if (this.getBodyNumber() == 0) {
         super.func_70624_b(entitylivingbaseIn);
      }
   }

   @Override
   public void setTargetPos(BlockPos in) {
      this.digTarget = in;
   }

   @Override
   public BlockPos getTargetPos() {
      return this.digTarget;
   }

   @Override
   public boolean getDigging() {
      return (Boolean)this.field_70180_af.func_187225_a(DIG);
   }

   @Override
   public void setDigging(boolean in) {
      if (in) {
         this.func_184185_a(SRPSounds.QUAC_DIG, 2.0F, this.func_70647_i());
      }

      this.field_70180_af.func_187227_b(DIG, in);
   }

   @Override
   public float getDigModel() {
      return this.diggingModel;
   }

   @Override
   public int getBodiesT() {
      return this.bodiesT;
   }

   @Override
   public void setBodiesT(int in) {
      this.bodiesT = in;
   }

   @Override
   public byte getBodyNumber() {
      return (Byte)this.field_70180_af.func_187225_a(PART);
   }

   @Override
   public void setBodyNumber(int texture) {
      this.field_70180_af.func_187227_b(PART, (byte)texture);
   }

   @Override
   public boolean getBodyTail() {
      return (Boolean)this.field_70180_af.func_187225_a(TAIL);
   }

   @Override
   public void setBodyTail(boolean in) {
      this.field_70180_af.func_187227_b(TAIL, in);
   }

   @Override
   public void setFollowing(EntityCanHaveBodies in) {
      if (in != null) {
         this.following = in.getEntity().func_110124_au();
      }
   }

   @Override
   public UUID getFollowing() {
      return this.following;
   }

   @Override
   public int getBodyLength() {
      return 4;
   }

   @Override
   public EntityCanHaveBodies getAnotherBody(World in) {
      return new EntityQuac(in);
   }

   @Override
   public void copyCopy(EntityCanHaveBodies in) {
      this.func_82149_j(in.getEntity());
   }

   @Override
   public Entity getEntity() {
      return this;
   }

   @Override
   public void onSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      this.func_180482_a(difficulty, livingdata);
   }

   @Override
   public void setCanF(boolean in) {
      this.canF = in;
   }

   @Override
   public boolean getCanF() {
      return this.canF;
   }

   @Override
   public void bodyPartEffect() {
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

   @Override
   public double getKillPoints() {
      return this.killcount;
   }

   @Override
   public EntityParasiteBase getEvolution(World in) {
      return null;
   }

   @Override
   public EntityParasiteBase getHead() {
      if (this.following != null) {
         EntityCanHaveBodies follo = (EntityCanHaveBodies)this.getFather(this.following);
         if (follo == null) {
            return null;
         } else {
            return !follo.getEntity().func_70089_S() ? null : follo.getHead();
         }
      } else {
         return this;
      }
   }

   @Override
   public Entity getFather(UUID uuid) {
      List<Entity> serverList = this.field_70170_p.field_72996_f;

      for (int x = 0; x < serverList.size(); x++) {
         if (uuid.equals(serverList.get(x).func_110124_au())) {
            return serverList.get(x);
         }
      }

      return null;
   }

   @Override
   public void handleDigging() {
      if (this.getDigging()) {
         this.bodiesTOut = 0;
         if (this.getBodyNumber() == 0) {
            this.spawnGroundParticles();
            if (this.field_70131_O > 0.25F) {
               this.func_70105_a(this.field_70130_N, this.field_70131_O - 0.15F);
            }

            if (this.diggingModel < 2.4F) {
               this.diggingModel += 0.08F;
            }
         } else {
            if (this.srpTicks == 10) {
               this.bodiesT++;
            }

            if (this.bodiesT >= this.getBodyNumber()) {
               this.spawnGroundParticles();
               if (this.field_70131_O > 0.25F) {
                  this.func_70105_a(this.field_70130_N, this.field_70131_O - 0.15F);
               }

               if (this.diggingModel < 2.4F) {
                  this.diggingModel += 0.08F;
               }
            }
         }
      } else {
         this.bodiesT = 0;
         if (this.getBodyNumber() == 0) {
            if (this.field_70131_O < 1.2F) {
               this.func_70105_a(this.field_70130_N, this.field_70131_O + 0.13F);
               this.spawnGroundParticles();
            }

            if (this.diggingModel >= 0.0F) {
               this.diggingModel -= 0.08F;
            }
         } else if (this.field_70165_t != this.field_70169_q && this.field_70161_v != this.field_70166_s) {
            if (this.field_70131_O < 1.2F) {
               this.func_70105_a(this.field_70130_N, this.field_70131_O + 0.13F);
               this.spawnGroundParticles();
            }

            if (this.diggingModel >= 0.0F) {
               this.diggingModel -= 0.08F;
            }
         }
      }
   }
}
