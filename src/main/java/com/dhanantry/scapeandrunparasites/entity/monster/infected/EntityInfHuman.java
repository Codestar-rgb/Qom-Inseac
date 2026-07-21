package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityAICircleGroup;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHumanHead;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfHuman extends EntityPInfected implements EntityCanMelt {
   private static final DataParameter<Float> HEIGH = EntityDataManager.func_187226_a(EntityInfHuman.class, DataSerializers.field_187193_c);
   private static final DataParameter<Boolean> MELTING = EntityDataManager.func_187226_a(EntityInfHuman.class, DataSerializers.field_187198_h);
   private float aSize;
   private int sound;
   private int host;
   private BlockPos barrierGoal = null;
   private int barrierRetryTicks = 0;
   private double _lastX = 0.0;
   private double _lastZ = 0.0;
   private int _stuckTicks = 0;
   private int _shoveTicks = 0;
   private BlockPos lastHeardSoundPos;
   private int soundMemoryTicks;
   private static final UUID SHOVE_SPEED_UUID = UUID.fromString("8f5b3f68-7aa5-4c2c-8d9e-2e4a5f5583c3");
   private static final AttributeModifier SHOVE_SPEED_MOD = new AttributeModifier(SHOVE_SPEED_UUID, "gate_shove_boost", 0.6, 2);

   public EntityInfHuman(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 1.95F);
      this.aSize = 1.0F;
      this.sound = 0;
      this.canModRender = 1;
      this.type = 11;
      this.thisMelting = true;
      if (this.func_70661_as() instanceof PathNavigateGround) {
         PathNavigateGround nav = (PathNavigateGround)this.func_70661_as();
         nav.func_179691_c(true);
         if (nav.func_189566_q() instanceof WalkNodeProcessor) {
            WalkNodeProcessor proc = (WalkNodeProcessor)nav.func_189566_q();
            proc.func_186317_a(true);
         }
      }

      this.func_184644_a(PathNodeType.DOOR_WOOD_CLOSED, 0.0F);
      this.func_184644_a(PathNodeType.DOOR_OPEN, 0.0F);
      this.func_184644_a(PathNodeType.DOOR_IRON_CLOSED, -1.0F);
   }

   @Override
   public int getParasiteIDRegister() {
      return 6;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infhumanCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(1, new EntityAIOpenDoor(this, true));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
      this.field_70714_bg.func_75776_a(4, new EntityAICircleGroup(this, 1.15, 8, 4.0, 10.0, 16, e -> e instanceof EntityInfHuman));
      this.field_70714_bg.func_75776_a(5, new EntityAIGetFollowers(this, 1, 16));
      this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFHUMAN_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFHUMAN_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.230000004172325);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFHUMAN_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFHUMAN_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(HEIGH, 0.0F);
      this.field_70180_af.func_187214_a(MELTING, Boolean.FALSE);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.melting();
      if (this.func_70661_as() instanceof PathNavigateGround) {
         ((PathNavigateGround)this.func_70661_as()).func_179691_c(true);
      }

      if (this.getSkin() == 111) {
         this.tickSoundMemory();
         if (this.getHeardSoundPos() == null) {
            AxisAlignedBB scanBox = this.func_174813_aQ().func_72314_b(16.0, 4.0, 16.0);
            List<EntityPlayer> nearby = this.field_70170_p
               .func_175647_a(EntityPlayer.class, scanBox, p -> !p.func_175149_v() && !p.field_71075_bZ.field_75098_d && p.func_70089_S());
            EntityPlayer loudest = null;
            double loudestSpeedSq = 0.0;

            for (EntityPlayer p : nearby) {
               float walkedThisTick = p.field_70140_Q - p.field_70141_P;
               boolean moving = walkedThisTick > 0.01F || p.func_70051_ag();
               if (moving) {
                  double loudness = walkedThisTick;
                  if (p.func_70051_ag()) {
                     loudness *= 3.0;
                  }

                  if (loudness > loudestSpeedSq) {
                     loudestSpeedSq = loudness;
                     loudest = p;
                  }
               }
            }

            if (loudest != null) {
               BlockPos soundPos = new BlockPos(loudest.field_70165_t, loudest.field_70163_u, loudest.field_70161_v);
               this.notifyHeardSound(soundPos, 60);
               this.func_70624_b(loudest);
            }
         }

         if (this.getHeardSoundPos() == null && this.func_70643_av() == null && this.func_70638_az() != null) {
            this.func_70624_b(null);
         }
      } else {
         this.clearHeardSound();
      }

      double dx = this.field_70165_t - this._lastX;
      double dz = this.field_70161_v - this._lastZ;
      if (dx * dx + dz * dz < 0.0025) {
         this._stuckTicks++;
      } else {
         this._stuckTicks = 0;
      }

      this._lastX = this.field_70165_t;
      this._lastZ = this.field_70161_v;
      if (this.func_70638_az() != null) {
         if (!this.func_70661_as().func_75500_f() && this._stuckTicks <= 20) {
            if (this.barrierRetryTicks > 0) {
               this.barrierRetryTicks--;
            }
         } else if (this.barrierRetryTicks <= 0 && this.retargetToNearestEntrance(12)) {
            this.barrierRetryTicks = 40;
         }

         if (this.barrierGoal != null) {
            double d2 = this.func_174818_b(this.barrierGoal);
            if (d2 <= 4.0) {
               IBlockState st = this.field_70170_p.func_180495_p(this.barrierGoal);
               this.openBarrierAt(this.barrierGoal, st);
               this.barrierGoal = null;
               this.func_70661_as().func_75499_g();
               this.func_70661_as().func_75497_a(this.func_70638_az(), 1.5);
            }
         }

         if (!this.func_70661_as().func_75500_f()) {
            this.tryOpenNextPathBarrier();
         }

         this.tryOpenNearbyBarriers();
         if (this._shoveTicks == 0 && this._stuckTicks >= 15 && this.nearOpenBarrier(1.5)) {
            this._shoveTicks = 8;
            this._stuckTicks = 0;
         }

         if (this._shoveTicks > 0) {
            IAttributeInstance speedAttr = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
            if (!speedAttr.func_180374_a(SHOVE_SPEED_MOD)) {
               speedAttr.func_111121_a(SHOVE_SPEED_MOD);
            }

            Vec3d look = this.func_70040_Z();
            this.func_70024_g(look.field_72450_a * 0.15, 0.0, look.field_72449_c * 0.15);
            if (this.field_70122_E && this._shoveTicks == 8) {
               this.field_70181_x += 0.05;
            }

            for (EntityLivingBase e : this.field_70170_p
               .func_175647_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(0.6, 0.2, 0.6), exx -> exx != this)) {
               double ex = e.field_70165_t - this.field_70165_t;
               double ez = e.field_70161_v - this.field_70161_v;
               double dSq = ex * ex + ez * ez + 1.0E-4;
               e.func_70024_g(ex / dSq * 0.08, 0.0, ez / dSq * 0.08);
            }

            this._shoveTicks--;
            if (this._shoveTicks == 0 && speedAttr.func_180374_a(SHOVE_SPEED_MOD)) {
               speedAttr.func_111124_b(SHOVE_SPEED_MOD);
            }
         }
      }
   }

   @Override
   public void func_70624_b(@Nullable EntityLivingBase entitylivingbaseIn) {
      if (entitylivingbaseIn instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entitylivingbaseIn;
         if (player.func_175149_v() || player.field_71075_bZ.field_75098_d) {
            super.func_70624_b(null);
            return;
         }
      }

      super.func_70624_b(entitylivingbaseIn);
   }

   private boolean nearOpenBarrier(double radius) {
      BlockPos base = new BlockPos(this);
      int r = (int)Math.ceil(radius);

      for (int y = 0; y <= 1; y++) {
         for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
               BlockPos p = base.func_177982_a(dx, y, dz);
               IBlockState st = this.field_70170_p.func_180495_p(p);
               if (st.func_177230_c() instanceof BlockDoor) {
                  BlockDoor d = (BlockDoor)st.func_177230_c();
                  if (d.func_149688_o(st) == Material.field_151575_d) {
                     if (st.func_177229_b(BlockDoor.field_176523_O) == EnumDoorHalf.UPPER) {
                        p = p.func_177977_b();
                        st = this.field_70170_p.func_180495_p(p);
                        if (!(st.func_177230_c() instanceof BlockDoor)) {
                           continue;
                        }
                     }

                     if ((Boolean)st.func_177229_b(BlockDoor.field_176519_b)) {
                        return true;
                     }
                  }
               } else if (st.func_177230_c() instanceof BlockFenceGate
                  && st.func_185904_a() == Material.field_151575_d
                  && (Boolean)st.func_177229_b(BlockFenceGate.field_176466_a)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean retargetToNearestEntrance(int radius) {
      BlockPos me = new BlockPos(this);
      BlockPos bestBarrier = null;
      double bestDist2 = Double.MAX_VALUE;

      for (int dy = 0; dy <= 1; dy++) {
         for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
               BlockPos p = me.func_177982_a(dx, dy, dz);
               IBlockState st = this.field_70170_p.func_180495_p(p);
               if (isWoodDoor(st) || isWoodGate(st)) {
                  if (isWoodDoor(st)) {
                     p = this.normalizeDoorPos(p, st);
                  }

                  double d2 = p.func_177951_i(me);
                  if (d2 < bestDist2) {
                     bestDist2 = d2;
                     bestBarrier = p;
                  }
               }
            }
         }
      }

      if (bestBarrier == null) {
         return false;
      } else {
         List<BlockPos> around = Arrays.asList(
            bestBarrier.func_177978_c(), bestBarrier.func_177968_d(), bestBarrier.func_177974_f(), bestBarrier.func_177976_e()
         );
         around.sort(Comparator.comparingDouble(pos -> pos.func_177954_c(this.field_70165_t, this.field_70163_u, this.field_70161_v)));

         for (BlockPos ap : around) {
            if (this.field_70170_p.func_180495_p(ap).func_185904_a().func_76222_j()) {
               boolean ok = this.func_70661_as().func_75492_a(ap.func_177958_n() + 0.5, ap.func_177956_o() + 0.0, ap.func_177952_p() + 0.5, 1.5);
               if (ok) {
                  this.barrierGoal = bestBarrier;
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static boolean isWoodDoor(IBlockState s) {
      if (!(s.func_177230_c() instanceof BlockDoor)) {
         return false;
      } else {
         BlockDoor d = (BlockDoor)s.func_177230_c();
         return d.func_149688_o(s) == Material.field_151575_d;
      }
   }

   private static boolean isWoodGate(IBlockState s) {
      return !(s.func_177230_c() instanceof BlockFenceGate) ? false : s.func_185904_a() == Material.field_151575_d;
   }

   private BlockPos normalizeDoorPos(BlockPos pos, IBlockState state) {
      return state.func_177230_c() instanceof BlockDoor && state.func_177229_b(BlockDoor.field_176523_O) == EnumDoorHalf.UPPER ? pos.func_177977_b() : pos;
   }

   private void openBarrierAt(BlockPos pos, IBlockState state) {
      if (isWoodDoor(state)) {
         pos = this.normalizeDoorPos(pos, state);
         state = this.field_70170_p.func_180495_p(pos);
         if (!(Boolean)state.func_177229_b(BlockDoor.field_176519_b)) {
            ((BlockDoor)state.func_177230_c()).func_176512_a(this.field_70170_p, pos, true);
         }
      } else {
         if (isWoodGate(state)) {
            if ((Boolean)state.func_177229_b(BlockFenceGate.field_176466_a)) {
               return;
            }

            this.field_70170_p.func_180501_a(pos, state.func_177226_a(BlockFenceGate.field_176466_a, true), 10);
         }
      }
   }

   private void tryOpenNextPathBarrier() {
      Path path = this.func_70661_as().func_75505_d();
      if (path != null) {
         int i = path.func_75873_e();
         if (i < path.func_75874_d()) {
            PathPoint next = path.func_75877_a(i);
            if (next != null) {
               BlockPos pos = new BlockPos(next.field_75839_a, next.field_75837_b, next.field_75838_c);
               this.openBarrierAt(pos, this.field_70170_p.func_180495_p(pos));
            }
         }
      }
   }

   private void tryOpenNearbyBarriers() {
      BlockPos base = new BlockPos(this);
      EnumFacing face = this.func_174811_aO();
      BlockPos[] spots = new BlockPos[]{
         base,
         base.func_177984_a(),
         base.func_177972_a(face),
         base.func_177972_a(face).func_177984_a(),
         base.func_177972_a(face.func_176746_e()),
         base.func_177972_a(face.func_176746_e()).func_177984_a(),
         base.func_177972_a(face.func_176735_f()),
         base.func_177972_a(face.func_176735_f()).func_177984_a()
      };

      for (BlockPos p : spots) {
         IBlockState st = this.field_70170_p.func_180495_p(p);
         if (isWoodDoor(st) || isWoodGate(st)) {
            this.openBarrierAt(p, st);
         }
      }
   }

   @Override
   protected void func_70609_aI() {
      super.func_70609_aI();
      if (this.getTHeigh() < 1.57F && !this.field_70170_p.field_72995_K) {
         this.setTHeigh(0.13F);
      }

      if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() <= SRPAttributes.INFHUMAN_HEADCHANCE) {
         EntityInfHumanHead out = new EntityInfHumanHead(this.field_70170_p);
         out.func_82149_j(this);
         out.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(this)), (IEntityLivingData)null);
         out.setSkin(this.getSkin());
         out.cannotDespawn(false);
         this.field_70170_p.func_72838_d(out);
      }
   }

   public void notifyHeardSound(BlockPos pos, int lifeTicks) {
      if (this.getSkin() == 111) {
         this.lastHeardSoundPos = pos;
         this.soundMemoryTicks = lifeTicks;
      }
   }

   public BlockPos getHeardSoundPos() {
      return this.lastHeardSoundPos;
   }

   public void tickSoundMemory() {
      if (this.soundMemoryTicks > 0) {
         this.soundMemoryTicks--;
         if (this.soundMemoryTicks <= 0) {
            this.lastHeardSoundPos = null;
         }
      }
   }

   public void clearHeardSound() {
      this.lastHeardSoundPos = null;
      this.soundMemoryTicks = 0;
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      if (!this.field_70170_p.field_72995_K) {
         if (SRPConfigMobs.hostEnabled && entityLivingIn instanceof AbstractSkeleton) {
            this.host++;
            if (this.host >= SRPConfigMobs.hostSkele) {
               this.particleStatus((byte)7);
               EntityHost host = new EntityHost(this.field_70170_p);
               host.func_82149_j(this);
               host.func_180482_a(this.field_70170_p.func_175649_E(this.func_180425_c()), (IEntityLivingData)null);
               this.field_70170_p.func_72838_d(host);
               host.particleStatus((byte)7);
               this.func_70106_y();
            }
         } else {
            super.func_70074_a(entityLivingIn);
         }
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && this.field_70146_Z.nextDouble() < SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
      }

      return flag;
   }

   @Override
   public void melt() {
      this.setWait(1000);
      this.field_70180_af.func_187227_b(HEIGH, 1.95F);
      this.field_70180_af.func_187227_b(MELTING, Boolean.TRUE);
   }

   @Override
   public void melting() {
      if (this.isMelting()) {
         if (this.sound % 20 == 0) {
            this.func_184185_a(SRPSounds.INFECTED_MELT, 1.0F, 1.0F);
         }

         this.sound++;
         if (this.getTHeigh() > 0.7) {
            this.setaSize(-0.005F);
            this.setTHeigh(-0.01F);
            this.func_70105_a(this.field_70130_N, this.getTHeigh());
         }

         if (!this.field_70170_p.field_72995_K) {
            if (this.getTHeigh() <= 0.7 || this.sound >= 127) {
               EntityLesh out = new EntityLesh(this.field_70170_p);
               out.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
               if (this.func_95999_t() != null) {
                  out.func_96094_a(this.func_95999_t());
               }

               this.func_70106_y();
               this.field_70170_p.func_72838_d(out);
               out.setLegs(SRPAttributes.INFHUMAN_V, false);
            }
         } else {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 106, 0);
            this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
         }
      }
   }

   public boolean func_70685_l(Entity entityIn) {
      if (this.getSkin() == 111) {
         double dSq = this.func_70068_e(entityIn);
         return dSq <= 4.0;
      } else {
         return super.func_70685_l(entityIn);
      }
   }

   @Override
   public boolean isMelting() {
      return (Boolean)this.field_70180_af.func_187225_a(MELTING);
   }

   @Override
   public float getTHeigh() {
      return (Float)this.field_70180_af.func_187225_a(HEIGH);
   }

   @Override
   public void setTHeigh(float in) {
      in += this.getTHeigh();
      this.field_70180_af.func_187227_b(HEIGH, in);
   }

   @Override
   public float getaSize() {
      return this.aSize;
   }

   @Override
   public void setaSize(float in) {
      this.aSize += in;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public float getSelfeFlashIntensity2() {
      return this.aSize;
   }

   public float func_70047_e() {
      return 1.73F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDHUMAN_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.INFECTEDHUMAN_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.INFECTEDHUMAN_DEATH;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.field_187939_hm;
   }

   @Override
   public EntityPFeral getFeral(World in) {
      return new EntityFerHuman(in);
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74768_a("parasitehost", this.host);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("parasitehost", 99)) {
         this.host = compound.func_74762_e("parasitehost");
      }
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData data = super.func_180482_a(difficulty, livingdata);
      if (!(this.field_70146_Z.nextDouble() < SRPConfig.variantChance) && this.phaseCreated < SRPConfigSystems.evolutionParasiteAlwaysVariant) {
         this.setSkin(0);
      } else {
         this.setSkin(this.field_70170_p.field_73012_v.nextInt(3) + 1);
         if (this.field_70146_Z.nextDouble() < 0.01F) {
            this.setSkin(111);
            if (this.func_110148_a(SharedMonsterAttributes.field_111265_b) != null) {
               this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(12.0);
            }

            IAttributeInstance moveAttr = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
            if (moveAttr != null) {
               moveAttr.func_111128_a(0.32);
            }
         }
      }

      return data;
   }
}
