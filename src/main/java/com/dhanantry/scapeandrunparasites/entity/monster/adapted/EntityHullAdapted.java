package com.dhanantry.scapeandrunparasites.entity.monster.adapted;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIBlockResidue;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvade;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityTendril;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyDead;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHullAdapted extends EntityPAdapted implements EntityBodyParts {
   private static final double HULL_SEARCH_RADIUS = 48.0;
   private static final int HULL_FAKE_COOLDOWN_MIN_T = 400;
   private static final int HULL_FAKE_COOLDOWN_MAX_T = 600;
   private static final boolean HULL_FAKE_DEBUG = false;
   private static final boolean HULL_FAKE_FORCE_ENABLE = true;
   private static final double HULL_FAKE_MIN_DIST = 22.0;
   private static final double HULL_FAKE_MAX_DIST = 46.0;
   private static final float HULL_FAKE_VOLUME = 1.0F;
   private static final float HULL_FAKE_PITCH_JIT = 0.1F;
   private static final SoundEvent[] HULL_FAKE_SOUNDS = new SoundEvent[]{
      SoundEvents.field_187532_aV, SoundEvents.field_187689_f, SoundEvents.field_187817_fK, SoundEvents.field_187579_bV
   };
   private int hullFakeCooldown = 0;
   private EntityBody leftTendril;
   private EntityBody rightTendril;
   private float leftTendrilHealth;
   private float rightTendrilHealth;
   private int timer = 0;
   private static final DataParameter<Boolean> CAM = EntityDataManager.func_187226_a(EntityHullAdapted.class, DataSerializers.field_187198_h);
   private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntityHullAdapted.class, DataSerializers.field_187192_b);
   private EntityLivingBase targetedEntity;
   private int pulling;
   private boolean canPull;
   private static final int CLOAK_WARMUP_TICKS = 40;
   private static final int DECLOAK_WARMUP_TICKS = 40;
   private static final float VIB_FREQ_MIN_HZ = 12.0F;
   private static final float VIB_FREQ_MAX_HZ = 108.0F;
   private static final float VIB_MAX_AMP_BLOCKS = 0.09F;
   private static final float VIB_JITTER_FRAC = 0.35F;
   private static final float VIB_MAX_RADIUS = 0.12F;
   private static final float VIB_AXIS_DRIFT_DEG = 3000.0F;
   private static final float VIB_PHASE_JUMP_PROB = 3000.12F;
   private static final float VIB_BURST_PROB = 3000.12F;
   private static final float VIB_STUTTER_PROB = 3000.1F;
   private EntityHullAdapted.CloakPhase cloakPhase = EntityHullAdapted.CloakPhase.IDLE;
   private int cloakPhaseTicks = 0;
   private double vibAxisX = 0.0;
   private double vibAxisZ = 0.0;
   private double vibLastX = 0.0;
   private double vibLastZ = 0.0;
   private double vibPhase = 0.0;
   private int vibStutterTicks = 0;
   private double vibHoldX = 0.0;
   private double vibHoldZ = 0.0;
   private static final boolean HULL_SOUND_DEBUG = false;
   private boolean hullDumpedSounds = false;

   public EntityHullAdapted(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.4F, 2.7F);
      this.field_70138_W = 1.0F;
      this.adaptationCap = 0.95F;
      this.attackSpeedT = 4;
      this.leftTendril = new EntityBody(this, 0.6F, 1.1F, 1.0F, 0.9F, 2.1F, 1, 1, true);
      this.rightTendril = new EntityBody(this, 0.6F, 1.1F, 1.0F, 0.9F, 2.1F, -1, 2, true);
      this.leftTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.rightTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
   }

   @Override
   public int getParasiteIDRegister() {
      return 52;
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(CAM, false);
      this.field_70180_af.func_187214_a(TARGET_ENTITY, 0);
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.11));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvade(this, 25, 10, 4.0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
      if (SRPConfig.parasiteGenResidue) {
         this.field_70714_bg.func_75776_a(9, new EntityAIBlockResidue(this, 2));
      }
   }

   private void hullBroadcast(String msg) {
   }

   public void func_82142_c(boolean invisible) {
      if (!this.field_70170_p.field_72995_K) {
         boolean currentlyInvisible = super.func_82150_aj();
         if (invisible && !currentlyInvisible) {
            if (this.cloakPhase != EntityHullAdapted.CloakPhase.PRE_CLOAK && this.cloakPhase != EntityHullAdapted.CloakPhase.CLOAKED) {
               this.startPreCloak();
               return;
            }
         } else if (!invisible
            && currentlyInvisible
            && this.cloakPhase != EntityHullAdapted.CloakPhase.PRE_DECLOAK
            && this.cloakPhase != EntityHullAdapted.CloakPhase.IDLE) {
            this.startPreDecloak();
            return;
         }
      }

      super.func_82142_c(invisible);
   }

   private void startPreCloak() {
      this.cloakPhase = EntityHullAdapted.CloakPhase.PRE_CLOAK;
      this.cloakPhaseTicks = 0;
      this.pickVibeAxis();
      this.vibLastX = this.vibLastZ = 0.0;
      this.vibPhase = 0.0;
   }

   private void startPreDecloak() {
      this.cloakPhase = EntityHullAdapted.CloakPhase.PRE_DECLOAK;
      this.cloakPhaseTicks = 0;
      this.pickVibeAxis();
      this.vibLastX = this.vibLastZ = 0.0;
      this.vibPhase = 0.0;
   }

   private void pickVibeAxis() {
      double ang = this.field_70146_Z.nextDouble() * Math.PI * 2.0;
      this.vibAxisX = Math.cos(ang);
      this.vibAxisZ = Math.sin(ang);
   }

   private void tickCloakVibration() {
      if (this.cloakPhase != EntityHullAdapted.CloakPhase.PRE_CLOAK && this.cloakPhase != EntityHullAdapted.CloakPhase.PRE_DECLOAK) {
         this.cloakPhase = super.func_82150_aj() ? EntityHullAdapted.CloakPhase.CLOAKED : EntityHullAdapted.CloakPhase.IDLE;
      } else {
         if (this.vibLastX != 0.0 || this.vibLastZ != 0.0) {
            this.func_70107_b(this.field_70165_t - this.vibLastX, this.field_70163_u, this.field_70161_v - this.vibLastZ);
            this.vibLastX = this.vibLastZ = 0.0;
         }

         int duration = this.cloakPhase == EntityHullAdapted.CloakPhase.PRE_CLOAK ? 40 : 40;
         float tRaw = (float)this.cloakPhaseTicks / duration;
         float ramp = tRaw * tRaw * (3.0F - 2.0F * tRaw);
         float fRamp = 1.0F - (float)Math.pow(1.0F - ramp, 3.0);
         float freqHz = 12.0F + fRamp * 96.0F;
         double dPhase = (Math.PI * 2) * freqHz / 20.0;
         this.vibPhase += dPhase;
         if (this.vibStutterTicks > 0) {
            this.vibStutterTicks--;
            this.vibLastX = this.vibHoldX;
            this.vibLastZ = this.vibHoldZ;
            this.func_70107_b(this.field_70165_t + this.vibLastX, this.field_70163_u, this.field_70161_v + this.vibLastZ);
            this.cloakPhaseTicks++;
            if (this.cloakPhaseTicks >= duration) {
               this.func_70107_b(this.field_70165_t - this.vibLastX, this.field_70163_u, this.field_70161_v - this.vibLastZ);
               this.vibLastX = this.vibLastZ = 0.0;
               if (this.cloakPhase == EntityHullAdapted.CloakPhase.PRE_CLOAK) {
                  super.func_82142_c(true);
                  this.cloakPhase = EntityHullAdapted.CloakPhase.CLOAKED;
               } else {
                  super.func_82142_c(false);
                  this.cloakPhase = EntityHullAdapted.CloakPhase.IDLE;
               }
            }
         } else {
            if (this.field_70146_Z.nextFloat() < 3000.12F * ramp) {
               double jump = Math.PI * (0.7 + this.field_70146_Z.nextDouble() * 0.6);
               if (this.field_70146_Z.nextBoolean()) {
                  jump = -jump;
               }

               this.vibPhase += jump;
            }

            if (ramp > 0.0F) {
               double dAng = Math.toRadians(3000.0) * ramp * (this.field_70146_Z.nextDouble() * 2.0 - 1.0);
               double cos = Math.cos(dAng);
               double sin = Math.sin(dAng);
               double nx = this.vibAxisX * cos - this.vibAxisZ * sin;
               double nz = this.vibAxisX * sin + this.vibAxisZ * cos;
               double nLen = Math.sqrt(nx * nx + nz * nz);
               if (nLen > 1.0E-6) {
                  this.vibAxisX = nx / nLen;
                  this.vibAxisZ = nz / nLen;
               }
            }

            double baseMag = 0.09F * ramp * Math.sin(this.vibPhase);
            double jitterAmp = 0.0315F * ramp;
            double j1 = (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * jitterAmp;
            double j2 = (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * jitterAmp;
            double axX = this.vibAxisX;
            double axZ = this.vibAxisZ;
            double pxX = -this.vibAxisZ;
            double pxZ = this.vibAxisX;
            double offX = axX * (baseMag + j1) + pxX * j2;
            double offZ = axZ * (baseMag + j1) + pxZ * j2;
            if (this.field_70146_Z.nextFloat() < 3000.12F * ramp) {
               double mult = 1.4 + this.field_70146_Z.nextDouble() * 0.8;
               offX *= mult;
               offZ *= mult;
            }

            double maxR = 0.12F * ramp;
            double r2 = offX * offX + offZ * offZ;
            if (r2 > maxR * maxR && r2 > 0.0) {
               double s = maxR / Math.sqrt(r2);
               offX *= s;
               offZ *= s;
            }

            this.vibLastX = offX;
            this.vibLastZ = offZ;
            this.func_70107_b(this.field_70165_t + this.vibLastX, this.field_70163_u, this.field_70161_v + this.vibLastZ);
            if (this.field_70146_Z.nextFloat() < 3000.1F * ramp) {
               this.vibStutterTicks = 1;
               this.vibHoldX = this.vibLastX;
               this.vibHoldZ = this.vibLastZ;
            }

            this.cloakPhaseTicks++;
            if (this.cloakPhaseTicks >= duration) {
               if (this.vibLastX != 0.0 || this.vibLastZ != 0.0) {
                  this.func_70107_b(this.field_70165_t - this.vibLastX, this.field_70163_u, this.field_70161_v - this.vibLastZ);
                  this.vibLastX = this.vibLastZ = 0.0;
               }

               if (this.cloakPhase == EntityHullAdapted.CloakPhase.PRE_CLOAK) {
                  super.func_82142_c(true);
                  this.cloakPhase = EntityHullAdapted.CloakPhase.CLOAKED;
               } else {
                  super.func_82142_c(false);
                  this.cloakPhase = EntityHullAdapted.CloakPhase.IDLE;
               }
            }
         }
      }
   }

   private void hullDebugTo(EntityPlayer player, String msg) {
   }

   private boolean hullPlayFakeSoundNearPlayer(EntityPlayer player) {
      if (player == null) {
         return false;
      } else {
         double angle = this.field_70146_Z.nextDouble() * Math.PI * 2.0;
         double dist = 22.0 + this.field_70146_Z.nextDouble() * 24.0;
         double fx = player.field_70165_t + Math.cos(angle) * dist;
         double fz = player.field_70161_v + Math.sin(angle) * dist;
         double fy = player.field_70163_u + (this.field_70146_Z.nextInt(3) - 1);
         BlockPos pos = new BlockPos(fx, fy, fz);
         if (!this.field_70170_p.func_175667_e(pos)) {
            fx = (fx + player.field_70165_t) * 0.5;
            fz = (fz + player.field_70161_v) * 0.5;
            pos = new BlockPos(fx, fy, fz);
            if (!this.field_70170_p.func_175667_e(pos)) {
               return false;
            }
         }

         SoundEvent chosen = HULL_FAKE_SOUNDS[this.field_70146_Z.nextInt(HULL_FAKE_SOUNDS.length)];
         float pitch = 1.0F + (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
         if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p).func_184148_a(null, fx, fy, fz, chosen, SoundCategory.HOSTILE, 1.0F, pitch);
            ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.SMOKE_LARGE, fx, fy + 0.2, fz, 10, 0.1, 0.1, 0.1, 0.01, new int[0]);
         }

         if (player instanceof EntityPlayerMP) {
            SPacketSoundEffect pkt = new SPacketSoundEffect(chosen, SoundCategory.HOSTILE, fx, fy, fz, 1.0F, pitch);
            ((EntityPlayerMP)player).field_71135_a.func_147359_a(pkt);
         }

         return true;
      }
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.HULL_HEALTH + SRPAttributes.HULL_A_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.HULL_ARMOR + SRPAttributes.HULL_A_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.HULL_ATTACK_DAMAGE + SRPAttributes.HULL_A_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.HULL_KD_RESISTANCE + SRPAttributes.HULL_A_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.adaptedFollow);
   }

   private void hullDbg(String msg) {
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

      if (!this.field_70170_p.field_72995_K) {
         if (this.srpTicks == 10) {
            float currentH = this.func_110143_aJ() / this.func_110138_aP();
            if (this.getSSS()) {
               this.func_70690_d(new PotionEffect(MobEffects.field_76441_p, 25, 1, false, false));
               this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 25, 2, false, false));
               if (this.field_70173_aa % 2 == 0) {
                  this.func_184185_a(SRPSounds.HULL_C, 0.2F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
               }

               if (currentH < SRPConfigMobs.hulladaptedneededhealth) {
                  this.setSSS(false);
               }
            } else if (currentH >= SRPConfigMobs.hulladaptedneededhealth) {
               this.timer++;
               if (this.timer > SRPConfigMobs.hulladaptedneededtime) {
                  this.setSSS(true);
                  this.particleStatus((byte)6);
                  this.timer = 0;
               }
            }
         }

         if (!this.canPull) {
            this.pulling--;
            if (this.pulling == 0) {
               this.canPull = true;
            }
         }

         if (this.func_70638_az() != null) {
            if (!this.func_70638_az().func_70089_S()) {
               this.func_70624_b(null);
               this.setTargetedEntity(0);
            } else if (this.func_70685_l(this.func_70638_az())
               && this.func_70068_e(this.func_70638_az()) > 0.0
               && this.canPull
               && this.getTargetedEntity() != null) {
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 1, false, false));
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 1, false, false));
               this.lookAt(this.getTargetedEntity());
               this.attackEntityAsMobMinimum(this.func_70638_az(), 0.02F);
               this.setParasiteStatus(3);
               this.pulling++;
               if (this.pulling > 200 || this.func_70068_e(this.func_70638_az()) > 9.0) {
                  this.setTargetedEntity(0);
                  this.canPull = false;
               }
            } else {
               this.setTargetedEntity(0);
            }
         } else {
            this.setTargetedEntity(0);
         }
      }

      if (this.getTargetedEntity() != null && this.func_70068_e(this.getTargetedEntity()) > 0.0) {
         EntityLivingBase target = this.getTargetedEntity();
         target.func_184210_p();
         double str = 0.3;
         double deltaX = this.field_70165_t - target.field_70165_t;
         double deltaY = this.field_70163_u - target.field_70163_u;
         double deltaZ = this.field_70161_v - target.field_70161_v;
         str = 0.13;
         double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
         if (distance == 0.0) {
            return;
         }

         deltaX /= distance;
         deltaY /= distance;
         deltaZ /= distance;
         target.field_70159_w += deltaX * str;
         target.field_70181_x += deltaY * str;
         target.field_70179_y += deltaZ * str;
      }

      if (!this.field_70170_p.field_72995_K) {
         this.tickCloakVibration();
      }

      if (!this.field_70170_p.field_72995_K) {
         EntityPlayer target = this.field_70170_p.func_72890_a(this, 48.0);
         boolean invisibleEnough = true;
         if (this.hullFakeCooldown > 0) {
            this.hullFakeCooldown--;
         } else if (target != null && invisibleEnough) {
            EntityHullAdapted.IllusionType type = this.pickIllusionType();
            boolean ok = this.hullPlayTypedIllusion_STRONG(target, type);
            this.hullDbg("Attempt type=" + type + " -> " + (ok ? "OK" : "FAILED"));
            this.hullFakeCooldown = 400 + this.field_70146_Z.nextInt(201);
         } else {
            this.hullFakeCooldown = 400 + this.field_70146_Z.nextInt(201);
         }
      }
   }

   private boolean hullPlayTypedIllusion_STRONG(EntityPlayer player, EntityHullAdapted.IllusionType type) {
      if (player == null) {
         return false;
      } else {
         EntityHullAdapted.IllusionProfile prof = this.profileFor(type);
         Vec3d pos = this.pickOffset(player, prof.minDist, prof.maxDist);
         BlockPos bp = new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
         if (!this.field_70170_p.func_175667_e(bp)) {
            pos = new Vec3d((pos.field_72450_a + player.field_70165_t) * 0.5, pos.field_72448_b, (pos.field_72449_c + player.field_70161_v) * 0.5);
            bp = new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
            if (!this.field_70170_p.func_175667_e(bp)) {
               this.hullDbg("Chunk not loaded even after nudge; abort.");
               return false;
            }
         }

         List<SoundEvent> pool = this.hullBuildSoundPool(type);
         if (pool.isEmpty()) {
            SoundEvent amb = this.func_184639_G();
            pool = Collections.singletonList(amb != null ? amb : SoundEvents.field_187532_aV);
            this.hullDbg("SRPSounds pool empty for " + type + " — using fallback.");
         }

         SoundEvent chosen = pool.get(this.field_70146_Z.nextInt(pool.size()));
         float pitch = prof.basePitch + (this.field_70146_Z.nextFloat() - 0.5F) * (prof.pitchJitter * 2.0F);
         EntityHullAdapted.IllusionLoudness loud = this.loudnessFor(type);
         float volume = clampf(loud.base + (this.field_70146_Z.nextFloat() - 0.5F) * (loud.jitter * 2.0F), 0.01F, 1.0F);
         if (this.field_70170_p instanceof WorldServer) {
            this.field_70170_p.func_184148_a(null, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, chosen, SoundCategory.HOSTILE, volume, pitch);
            ((WorldServer)this.field_70170_p)
               .func_175739_a(
                  EnumParticleTypes.SMOKE_LARGE, pos.field_72450_a, pos.field_72448_b + 0.2, pos.field_72449_c, 8, 0.08, 0.08, 0.08, 0.01, new int[0]
               );
         }

         if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player)
               .field_71135_a
               .func_147359_a(new SPacketSoundEffect(chosen, SoundCategory.HOSTILE, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, volume, pitch));
         }

         return true;
      }
   }

   @Override
   protected void handleParasiteStatus() {
      int k = this.getParasiteStatus();
      if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2 || k == 3) {
         if (this.getAttackCooldownAni() != 0) {
            int i = this.getAttackCooldownAni() - 1;
            this.setAttackCooldownAni(i);
         }

         if (k == 1 || k == 2 || k == 3) {
            if (this.func_70638_az() != null) {
               if (!this.func_70638_az().func_70089_S()) {
                  this.func_70624_b(null);
                  this.setParasiteStatus(0);
               } else if (!this.canPull) {
                  this.setParasiteStatus(Math.min(k, 2));
               }
            } else {
               this.setParasiteStatus(0);
               this.func_70624_b(null);
            }
         }
      }
   }

   public void setTargetedEntity(int entityId) {
      if (this.canPull || entityId == 0) {
         this.pulling = 0;
         this.canPull = true;
         this.field_70180_af.func_187227_b(TARGET_ENTITY, entityId);
      }
   }

   public boolean hasTargetedEntity() {
      return !this.canPull ? false : (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
   }

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

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      this.setSSS(false);
      this.timer = 0;
      return super.func_70097_a(source, amount);
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
                  tendril.setSkin(3);
                  tendril.func_82149_j(this.leftTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.leftTendril);
                  this.field_70170_p.func_72960_a(this, (byte)11);
                  this.cutResistances(SRPConfig.adaptedPointDamCap / 2);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            } else if (this.rightTendril.getId() == id) {
               this.rightTendrilHealth -= amount;
               if (this.rightTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(3);
                  tendril.func_82149_j(this.rightTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.rightTendril);
                  this.field_70170_p.func_72960_a(this, (byte)22);
                  this.cutResistances(SRPConfig.adaptedPointDamCap / 2);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            }

            return flag;
         }
      }
   }

   private EntityHullAdapted.IllusionType pickIllusionType() {
      float r = this.field_70146_Z.nextFloat();
      return r < 0.55F ? EntityHullAdapted.IllusionType.FOOTSTEP : (r < 0.9F ? EntityHullAdapted.IllusionType.AMBIENT : EntityHullAdapted.IllusionType.GROWL);
   }

   private SoundEvent srpSound(String fieldName) {
      try {
         Field f = SRPSounds.class.getField(fieldName);
         Object v = f.get(null);
         if (v instanceof SoundEvent) {
            return (SoundEvent)v;
         }
      } catch (Throwable var4) {
      }

      return null;
   }

   private List<SoundEvent> srpFindMatchingSounds(String[] whoTokens, String[] whatTokens) {
      List<SoundEvent> out = new ArrayList<>();

      try {
         for (Field f : SRPSounds.class.getDeclaredFields()) {
            if (SoundEvent.class.isAssignableFrom(f.getType())) {
               f.setAccessible(true);
               Object v = f.get(null);
               if (v instanceof SoundEvent) {
                  String name = f.getName().toUpperCase();
                  boolean whoOk = false;
                  boolean whatOk = false;

                  for (String w : whoTokens) {
                     if (name.contains(w)) {
                        whoOk = true;
                        break;
                     }
                  }

                  for (String wx : whatTokens) {
                     if (name.contains(wx)) {
                        whatOk = true;
                        break;
                     }
                  }

                  if (whoOk && whatOk) {
                     out.add((SoundEvent)v);
                  }
               }
            }
         }
      } catch (Throwable var16) {
      }

      if (out.isEmpty()) {
         SoundEvent amb = this.func_184639_G();
         if (amb != null) {
            out.add(amb);
         } else {
            out.add(SoundEvents.field_187532_aV);
         }
      }

      return out;
   }

   private Vec3d pickOffset(EntityPlayer p, double minD, double maxD) {
      double dist = minD + this.field_70146_Z.nextDouble() * (maxD - minD);
      Vec3d look = p.func_70040_Z().func_72432_b();
      Vec3d side = look.func_72431_c(new Vec3d(0.0, 1.0, 0.0)).func_72432_b();
      Vec3d dir;
      if (this.field_70146_Z.nextBoolean()) {
         dir = look.func_186678_a(-1.0).func_178787_e(side.func_186678_a((this.field_70146_Z.nextDouble() - 0.5) * 0.6)).func_72432_b();
      } else {
         dir = side.func_186678_a(this.field_70146_Z.nextBoolean() ? 1.0 : -1.0);
      }

      return new Vec3d(
         p.field_70165_t + dir.field_72450_a * dist, p.field_70163_u + (this.field_70146_Z.nextInt(3) - 1), p.field_70161_v + dir.field_72449_c * dist
      );
   }

   private boolean hullPlayTypedIllusion(EntityPlayer player, EntityHullAdapted.IllusionType type) {
      if (player == null) {
         return false;
      } else {
         EntityHullAdapted.IllusionProfile prof = this.profileFor(type);
         Vec3d pos = this.pickOffset(player, prof.minDist, prof.maxDist);
         BlockPos bp = new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
         if (!this.field_70170_p.func_175667_e(bp)) {
            pos = new Vec3d((pos.field_72450_a + player.field_70165_t) * 0.5, pos.field_72448_b, (pos.field_72449_c + player.field_70161_v) * 0.5);
            bp = new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
            if (!this.field_70170_p.func_175667_e(bp)) {
               return false;
            }
         }

         List<SoundEvent> pool = this.hullBuildSoundPool(type);
         if (pool.isEmpty()) {
            return false;
         } else {
            SoundEvent chosen = pool.get(this.field_70146_Z.nextInt(pool.size()));
            float pitch = prof.basePitch + (this.field_70146_Z.nextFloat() - 0.5F) * (prof.pitchJitter * 2.0F);
            if (this.field_70170_p instanceof WorldServer) {
               ((WorldServer)this.field_70170_p)
                  .func_184148_a(null, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, chosen, SoundCategory.HOSTILE, 1.0F, pitch);
            }

            if (player instanceof EntityPlayerMP) {
               ((EntityPlayerMP)player)
                  .field_71135_a
                  .func_147359_a(new SPacketSoundEffect(chosen, SoundCategory.HOSTILE, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 1.0F, pitch));
            }

            return true;
         }
      }
   }

   private EntityHullAdapted.IllusionProfile profileFor(EntityHullAdapted.IllusionType t) {
      switch (t) {
         case FOOTSTEP:
            return new EntityHullAdapted.IllusionProfile(8.0, 14.0, 1.0F, 0.1F);
         case AMBIENT:
            return new EntityHullAdapted.IllusionProfile(12.0, 22.0, 1.0F, 0.12F);
         case GROWL:
            return new EntityHullAdapted.IllusionProfile(16.0, 28.0, 0.98F, 0.14F);
         default:
            return new EntityHullAdapted.IllusionProfile(12.0, 22.0, 1.0F, 0.12F);
      }
   }

   private EntityHullAdapted.IllusionLoudness loudnessFor(EntityHullAdapted.IllusionType t) {
      switch (t) {
         case FOOTSTEP:
            return new EntityHullAdapted.IllusionLoudness(0.35F, 0.08F);
         case AMBIENT:
            return new EntityHullAdapted.IllusionLoudness(0.4F, 0.1F);
         case GROWL:
            return new EntityHullAdapted.IllusionLoudness(0.5F, 0.12F);
         default:
            return new EntityHullAdapted.IllusionLoudness(0.4F, 0.1F);
      }
   }

   private static float clampf(float v, float lo, float hi) {
      return v < lo ? lo : (v > hi ? hi : v);
   }

   @Override
   public void setBodyPartDead(int id) {
      if (this.leftTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      } else if (this.rightTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
         if (this.getSSS()) {
            float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * SRPConfigMobs.hulladaptedstealthdamage;
            if (entityIn instanceof EntityLivingBase) {
               f += EnchantmentHelper.func_152377_a(this.func_184614_ca(), ((EntityLivingBase)entityIn).func_70668_bt());
            }

            entityIn.func_70097_a(DamageSource.func_76358_a(this), f);
            this.setSSS(false);
            this.timer = 0;
         }

         if (!this.hasTargetedEntity()) {
            this.setTargetedEntity(entityIn.func_145782_y());
            ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 3, false, false));
         }
      }

      return flag;
   }

   @Override
   public boolean attackEntityAsMobMinimum(EntityLivingBase entityIn, float damage) {
      boolean flag = super.attackEntityAsMobMinimum(entityIn, damage);
      if (flag && this.getSSS()) {
         this.setSSS(false);
         this.timer = 0;
      }

      return flag;
   }

   public float func_70047_e() {
      return 2.0F;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      if (!this.field_70170_p.field_72995_K) {
         if (!SRPConfigWorld.coloniesActivated && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else if (ParasiteEventWorld.numberofColonies(this.field_70170_p) < 1 && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else {
            ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
            ParasiteEventEntity.spawnNext(this, new EntityHull(this.field_70170_p), true, false);
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

   public boolean getSSS() {
      return (Boolean)this.field_70180_af.func_187225_a(CAM);
   }

   public void setSSS(boolean in) {
      this.field_70180_af.func_187227_b(CAM, in);
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() == 0 && !this.func_70644_a(MobEffects.field_76441_p) ? SRPSounds.AHULL_GROWL : SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.AHULL_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.AHULL_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.hulladaptedOrbEffects, mobs);
      }

      return flag;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SRPSounds.HEAVY_STEPS_TWO, 0.15F, 1.0F);
   }

   public void func_184206_a(DataParameter<?> key) {
      super.func_184206_a(key);
      if (TARGET_ENTITY.equals(key)) {
         this.targetedEntity = null;
      }
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

   private static boolean anyContains(String hay, String... needles) {
      for (String n : needles) {
         if (hay.contains(n)) {
            return true;
         }
      }

      return false;
   }

   private List<SoundEvent> hullBuildSoundPool(EntityHullAdapted.IllusionType type) {
      String[] who = new String[]{"HULL", "MANDUCATOR"};
      String[] what;
      switch (type) {
         case FOOTSTEP:
            what = new String[]{"STEP", "FOOT", "WALK", "MOVE"};
            break;
         case AMBIENT:
            what = new String[]{"AMBIENT", "IDLE", "BREATH"};
            break;
         case GROWL:
            what = new String[]{"GROWL", "ROAR", "ALERT", "SNARL", "SCREAM"};
            break;
         default:
            what = new String[]{"AMBIENT"};
      }

      List<SoundEvent> out = new ArrayList<>();

      try {
         for (Field f : SRPSounds.class.getDeclaredFields()) {
            if (SoundEvent.class.isAssignableFrom(f.getType())) {
               f.setAccessible(true);
               Object v = f.get(null);
               if (v instanceof SoundEvent) {
                  SoundEvent s = (SoundEvent)v;
                  String fieldName = f.getName().toUpperCase();
                  String regName = String.valueOf(s.func_187503_a()).toUpperCase();
                  if (!regName.contains("SILENCE") && !fieldName.contains("SILENCE")) {
                     boolean whoOk = anyContains(fieldName, who) || anyContains(regName, who);
                     boolean whatOk = anyContains(fieldName, what) || anyContains(regName, what);
                     if (whoOk && whatOk) {
                        out.add(s);
                     }
                  }
               }
            }
         }
      } catch (Throwable var15) {
      }

      return out;
   }

   private void dumpSRPSoundsOnce() {
      if (!this.hullDumpedSounds && !this.field_70170_p.field_72995_K) {
         this.hullDumpedSounds = true;
         StringBuilder sb = new StringBuilder("[Hull] SRPSounds detected:");

         try {
            for (Field f : SRPSounds.class.getDeclaredFields()) {
               if (SoundEvent.class.isAssignableFrom(f.getType())) {
                  f.setAccessible(true);
                  Object v = f.get(null);
                  if (v instanceof SoundEvent) {
                     SoundEvent s = (SoundEvent)v;
                     sb.append("\n - ").append(f.getName()).append(" -> ").append(s.func_187503_a());
                  }
               }
            }
         } catch (Throwable var8) {
         }

         System.out.println(sb.toString());
      }
   }

   private static enum CloakPhase {
      IDLE,
      PRE_CLOAK,
      CLOAKED,
      PRE_DECLOAK;
   }

   private static class IllusionLoudness {
      final float base;
      final float jitter;

      IllusionLoudness(float base, float jitter) {
         this.base = base;
         this.jitter = jitter;
      }
   }

   private static class IllusionProfile {
      final double minDist;
      final double maxDist;
      final float basePitch;
      final float pitchJitter;

      IllusionProfile(double minDist, double maxDist, float basePitch, float pitchJitter) {
         this.minDist = minDist;
         this.maxDist = maxDist;
         this.basePitch = basePitch;
         this.pitchJitter = pitchJitter;
      }
   }

   private static enum IllusionType {
      FOOTSTEP,
      AMBIENT,
      GROWL;
   }
}
