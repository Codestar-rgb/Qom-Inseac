/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.adapted;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIBlockResidue;
import com.subspaceparasite.entity.ai.EntityAIEvade;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityPAdapted;
import com.subspaceparasite.entity.monster.EntityTendril;
import com.subspaceparasite.entity.monster.primitive.EntityHull;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.SPPacketEntityBodyDead;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHullAdapted
extends EntityPAdapted
implements EntityBodyParts {
    private static final double HULL_SEARCH_RADIUS = 48.0;
    private static final int HULL_FAKE_COOLDOWN_MIN_T = 400;
    private static final int HULL_FAKE_COOLDOWN_MAX_T = 600;
    private static final boolean HULL_FAKE_DEBUG = false;
    private static final boolean HULL_FAKE_FORCE_ENABLE = true;
    private static final double HULL_FAKE_MIN_DIST = 22.0;
    private static final double HULL_FAKE_MAX_DIST = 46.0;
    private static final float HULL_FAKE_VOLUME = 1.0f;
    private static final float HULL_FAKE_PITCH_JIT = 0.1f;
    private static final SoundEvent[] HULL_FAKE_SOUNDS = new SoundEvent[]{SoundEvents.field_187532_aV, SoundEvents.field_187689_f, SoundEvents.field_187817_fK, SoundEvents.field_187579_bV};
    private int hullFakeCooldown = 0;
    private EntityBody leftTendril;
    private EntityBody rightTendril;
    private float leftTendrilHealth;
    private float rightTendrilHealth;
    private int timer = 0;
    private static final DataParameter<Boolean> CAM = EntityDataManager.func_187226_a(EntityHullAdapted.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntityHullAdapted.class, (DataSerializer)DataSerializers.field_187192_b);
    private EntityLivingBase targetedEntity;
    private int pulling;
    private boolean canPull;
    private static final int CLOAK_WARMUP_TICKS = 40;
    private static final int DECLOAK_WARMUP_TICKS = 40;
    private static final float VIB_FREQ_MIN_HZ = 12.0f;
    private static final float VIB_FREQ_MAX_HZ = 108.0f;
    private static final float VIB_MAX_AMP_BLOCKS = 0.09f;
    private static final float VIB_JITTER_FRAC = 0.35f;
    private static final float VIB_MAX_RADIUS = 0.12f;
    private static final float VIB_AXIS_DRIFT_DEG = 3000.0f;
    private static final float VIB_PHASE_JUMP_PROB = 3000.12f;
    private static final float VIB_BURST_PROB = 3000.12f;
    private static final float VIB_STUTTER_PROB = 3000.1f;
    private CloakPhase cloakPhase = CloakPhase.IDLE;
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
        this.func_70105_a(1.4f, 2.7f);
        this.field_70138_W = 1.0f;
        this.adaptationCap = 0.95f;
        this.attackSpeedT = 4;
        this.leftTendril = new EntityBody(this, 0.6f, 1.1f, 1.0f, 0.9f, 2.1f, 1, 1, true);
        this.rightTendril = new EntityBody(this, 0.6f, 1.1f, 1.0f, 0.9f, 2.1f, -1, 2, true);
        this.leftTendrilHealth = (float)((double)this.func_110138_aP() * SPConfig.tendrilHealth);
        this.rightTendrilHealth = (float)((double)this.func_110138_aP() * SPConfig.tendrilHealth);
    }

    @Override
    public int getParasiteIDRegister() {
        return 52;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(CAM, (Object)false);
        this.field_70180_af.func_187214_a(TARGET_ENTITY, (Object)0);
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.11));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 25, 10, 4.0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
        if (SPConfig.parasiteGenResidue) {
            this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIBlockResidue(this, 2));
        }
    }

    private void hullBroadcast(String msg) {
    }

    public void func_82142_c(boolean invisible) {
        if (!this.field_70170_p.field_72995_K) {
            boolean currentlyInvisible = super.func_82150_aj();
            if (invisible && !currentlyInvisible) {
                if (this.cloakPhase != CloakPhase.PRE_CLOAK && this.cloakPhase != CloakPhase.CLOAKED) {
                    this.startPreCloak();
                    return;
                }
            } else if (!invisible && currentlyInvisible && this.cloakPhase != CloakPhase.PRE_DECLOAK && this.cloakPhase != CloakPhase.IDLE) {
                this.startPreDecloak();
                return;
            }
        }
        super.func_82142_c(invisible);
    }

    private void startPreCloak() {
        this.cloakPhase = CloakPhase.PRE_CLOAK;
        this.cloakPhaseTicks = 0;
        this.pickVibeAxis();
        this.vibLastZ = 0.0;
        this.vibLastX = 0.0;
        this.vibPhase = 0.0;
    }

    private void startPreDecloak() {
        this.cloakPhase = CloakPhase.PRE_DECLOAK;
        this.cloakPhaseTicks = 0;
        this.pickVibeAxis();
        this.vibLastZ = 0.0;
        this.vibLastX = 0.0;
        this.vibPhase = 0.0;
    }

    private void pickVibeAxis() {
        double ang = this.field_70146_Z.nextDouble() * Math.PI * 2.0;
        this.vibAxisX = Math.cos(ang);
        this.vibAxisZ = Math.sin(ang);
    }

    private void tickCloakVibration() {
        double maxR;
        double r2;
        double nz;
        double sin;
        double dAng;
        double cos;
        double nx;
        double nLen;
        if (this.cloakPhase != CloakPhase.PRE_CLOAK && this.cloakPhase != CloakPhase.PRE_DECLOAK) {
            this.cloakPhase = super.func_82150_aj() ? CloakPhase.CLOAKED : CloakPhase.IDLE;
            return;
        }
        if (this.vibLastX != 0.0 || this.vibLastZ != 0.0) {
            this.func_70107_b(this.field_70165_t - this.vibLastX, this.field_70163_u, this.field_70161_v - this.vibLastZ);
            this.vibLastZ = 0.0;
            this.vibLastX = 0.0;
        }
        int duration = this.cloakPhase == CloakPhase.PRE_CLOAK ? 40 : 40;
        float tRaw = (float)this.cloakPhaseTicks / (float)duration;
        float ramp = tRaw * tRaw * (3.0f - 2.0f * tRaw);
        float fRamp = 1.0f - (float)Math.pow(1.0f - ramp, 3.0);
        float freqHz = 12.0f + fRamp * 96.0f;
        double dPhase = Math.PI * 2 * (double)freqHz / 20.0;
        this.vibPhase += dPhase;
        if (this.vibStutterTicks > 0) {
            --this.vibStutterTicks;
            this.vibLastX = this.vibHoldX;
            this.vibLastZ = this.vibHoldZ;
            this.func_70107_b(this.field_70165_t + this.vibLastX, this.field_70163_u, this.field_70161_v + this.vibLastZ);
            ++this.cloakPhaseTicks;
            if (this.cloakPhaseTicks >= duration) {
                this.func_70107_b(this.field_70165_t - this.vibLastX, this.field_70163_u, this.field_70161_v - this.vibLastZ);
                this.vibLastZ = 0.0;
                this.vibLastX = 0.0;
                if (this.cloakPhase == CloakPhase.PRE_CLOAK) {
                    super.func_82142_c(true);
                    this.cloakPhase = CloakPhase.CLOAKED;
                } else {
                    super.func_82142_c(false);
                    this.cloakPhase = CloakPhase.IDLE;
                }
            }
            return;
        }
        if (this.field_70146_Z.nextFloat() < 3000.12f * ramp) {
            double jump = Math.PI * (0.7 + this.field_70146_Z.nextDouble() * 0.6);
            if (this.field_70146_Z.nextBoolean()) {
                jump = -jump;
            }
            this.vibPhase += jump;
        }
        if (ramp > 0.0f && (nLen = Math.sqrt((nx = this.vibAxisX * (cos = Math.cos(dAng = Math.toRadians(3000.0) * (double)ramp * (this.field_70146_Z.nextDouble() * 2.0 - 1.0))) - this.vibAxisZ * (sin = Math.sin(dAng))) * nx + (nz = this.vibAxisX * sin + this.vibAxisZ * cos) * nz)) > 1.0E-6) {
            this.vibAxisX = nx / nLen;
            this.vibAxisZ = nz / nLen;
        }
        double baseMag = (double)(0.09f * ramp) * Math.sin(this.vibPhase);
        double jitterAmp = 0.0315f * ramp;
        double j1 = (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * jitterAmp;
        double j2 = (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * jitterAmp;
        double axX = this.vibAxisX;
        double axZ = this.vibAxisZ;
        double pxX = -this.vibAxisZ;
        double pxZ = this.vibAxisX;
        double offX = axX * (baseMag + j1) + pxX * j2;
        double offZ = axZ * (baseMag + j1) + pxZ * j2;
        if (this.field_70146_Z.nextFloat() < 3000.12f * ramp) {
            double mult = 1.4 + this.field_70146_Z.nextDouble() * 0.8;
            offX *= mult;
            offZ *= mult;
        }
        if ((r2 = offX * offX + offZ * offZ) > (maxR = (double)(0.12f * ramp)) * maxR && r2 > 0.0) {
            double s = maxR / Math.sqrt(r2);
            offX *= s;
            offZ *= s;
        }
        this.vibLastX = offX;
        this.vibLastZ = offZ;
        this.func_70107_b(this.field_70165_t + this.vibLastX, this.field_70163_u, this.field_70161_v + this.vibLastZ);
        if (this.field_70146_Z.nextFloat() < 3000.1f * ramp) {
            this.vibStutterTicks = 1;
            this.vibHoldX = this.vibLastX;
            this.vibHoldZ = this.vibLastZ;
        }
        ++this.cloakPhaseTicks;
        if (this.cloakPhaseTicks >= duration) {
            if (this.vibLastX != 0.0 || this.vibLastZ != 0.0) {
                this.func_70107_b(this.field_70165_t - this.vibLastX, this.field_70163_u, this.field_70161_v - this.vibLastZ);
                this.vibLastZ = 0.0;
                this.vibLastX = 0.0;
            }
            if (this.cloakPhase == CloakPhase.PRE_CLOAK) {
                super.func_82142_c(true);
                this.cloakPhase = CloakPhase.CLOAKED;
            } else {
                super.func_82142_c(false);
                this.cloakPhase = CloakPhase.IDLE;
            }
        }
    }

    private void hullDebugTo(EntityPlayer player, String msg) {
    }

    private boolean hullPlayFakeSoundNearPlayer(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        double angle = this.field_70146_Z.nextDouble() * Math.PI * 2.0;
        double dist = 22.0 + this.field_70146_Z.nextDouble() * 24.0;
        double fx = player.field_70165_t + Math.cos(angle) * dist;
        double fz = player.field_70161_v + Math.sin(angle) * dist;
        double fy = player.field_70163_u + (double)(this.field_70146_Z.nextInt(3) - 1);
        BlockPos pos = new BlockPos(fx, fy, fz);
        if (!this.field_70170_p.func_175667_e(pos) && !this.field_70170_p.func_175667_e(pos = new BlockPos(fx = (fx + player.field_70165_t) * 0.5, fy, fz = (fz + player.field_70161_v) * 0.5))) {
            return false;
        }
        SoundEvent chosen = HULL_FAKE_SOUNDS[this.field_70146_Z.nextInt(HULL_FAKE_SOUNDS.length)];
        float pitch = 1.0f + (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
        if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p).func_184148_a(null, fx, fy, fz, chosen, SoundCategory.HOSTILE, 1.0f, pitch);
            ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.SMOKE_LARGE, fx, fy + 0.2, fz, 10, 0.1, 0.1, 0.1, 0.01, new int[0]);
        }
        if (player instanceof EntityPlayerMP) {
            SPacketSoundEffect pkt = new SPacketSoundEffect(chosen, SoundCategory.HOSTILE, fx, fy, fz, 1.0f, pitch);
            ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)pkt);
        }
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.HULL_HEALTH + SPAttributes.HULL_A_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.HULL_ARMOR + SPAttributes.HULL_A_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.HULL_ATTACK_DAMAGE + SPAttributes.HULL_A_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.HULL_KD_RESISTANCE + SPAttributes.HULL_A_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.adaptedFollow);
    }

    private void hullDbg(String msg) {
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.leftTendrilHealth > 0.0f) {
            this.leftTendril.func_70071_h_();
        }
        if (this.rightTendrilHealth > 0.0f) {
            this.rightTendril.func_70071_h_();
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.srpTicks == 10) {
                float currentH = this.func_110143_aJ() / this.func_110138_aP();
                if (this.getSSS()) {
                    this.func_70690_d(new PotionEffect(MobEffects.field_76441_p, 25, 1, false, false));
                    this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 25, 2, false, false));
                    if (this.field_70173_aa % 2 == 0) {
                        this.func_184185_a(SPSounds.HULL_C, 0.2f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
                    }
                    if ((double)currentH < SPConfigMobs.hulladaptedneededhealth) {
                        this.setSSS(false);
                    }
                } else if ((double)currentH >= SPConfigMobs.hulladaptedneededhealth) {
                    ++this.timer;
                    if ((float)this.timer > SPConfigMobs.hulladaptedneededtime) {
                        this.setSSS(true);
                        this.particleStatus((byte)6);
                        this.timer = 0;
                    }
                }
            }
            if (!this.canPull) {
                --this.pulling;
                if (this.pulling == 0) {
                    this.canPull = true;
                }
            }
            if (this.func_70638_az() != null) {
                if (!this.func_70638_az().func_70089_S()) {
                    this.func_70624_b(null);
                    this.setTargetedEntity(0);
                } else if (this.func_70685_l((Entity)this.func_70638_az()) && this.func_70068_e((Entity)this.func_70638_az()) > 0.0 && this.canPull && this.getTargetedEntity() != null) {
                    this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 1, false, false));
                    this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 1, false, false));
                    this.lookAt((Entity)this.getTargetedEntity());
                    this.attackEntityAsMobMinimum(this.func_70638_az(), 0.02f);
                    this.setParasiteStatus(3);
                    ++this.pulling;
                    if (this.pulling > 200 || this.func_70068_e((Entity)this.func_70638_az()) > 9.0) {
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
        if (this.getTargetedEntity() != null && this.func_70068_e((Entity)this.getTargetedEntity()) > 0.0) {
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
            target.field_70159_w += (deltaX /= distance) * str;
            target.field_70181_x += (deltaY /= distance) * str;
            target.field_70179_y += (deltaZ /= distance) * str;
        }
        if (!this.field_70170_p.field_72995_K) {
            this.tickCloakVibration();
        }
        if (!this.field_70170_p.field_72995_K) {
            EntityPlayer target = this.field_70170_p.func_72890_a((Entity)this, 48.0);
            boolean invisibleEnough = true;
            if (this.hullFakeCooldown > 0) {
                --this.hullFakeCooldown;
            } else if (target != null && invisibleEnough) {
                IllusionType type = this.pickIllusionType();
                boolean ok = this.hullPlayTypedIllusion_STRONG(target, type);
                this.hullDbg("Attempt type=" + (Object)((Object)type) + " -> " + (ok ? "OK" : "FAILED"));
                this.hullFakeCooldown = 400 + this.field_70146_Z.nextInt(201);
            } else {
                this.hullFakeCooldown = 400 + this.field_70146_Z.nextInt(201);
            }
        }
    }

    private boolean hullPlayTypedIllusion_STRONG(EntityPlayer player, IllusionType type) {
        List<SoundEvent> pool;
        if (player == null) {
            return false;
        }
        IllusionProfile prof = this.profileFor(type);
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
        if ((pool = this.hullBuildSoundPool(type)).isEmpty()) {
            SoundEvent amb = this.func_184639_G();
            pool = Collections.singletonList(amb != null ? amb : SoundEvents.field_187532_aV);
            this.hullDbg("SPSounds pool empty for " + (Object)((Object)type) + " \u2014 using fallback.");
        }
        SoundEvent chosen = pool.get(this.field_70146_Z.nextInt(pool.size()));
        float pitch = prof.basePitch + (this.field_70146_Z.nextFloat() - 0.5f) * (prof.pitchJitter * 2.0f);
        IllusionLoudness loud = this.loudnessFor(type);
        float volume = EntityHullAdapted.clampf(loud.base + (this.field_70146_Z.nextFloat() - 0.5f) * (loud.jitter * 2.0f), 0.01f, 1.0f);
        if (this.field_70170_p instanceof WorldServer) {
            this.field_70170_p.func_184148_a(null, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, chosen, SoundCategory.HOSTILE, volume, pitch);
            ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.SMOKE_LARGE, pos.field_72450_a, pos.field_72448_b + 0.2, pos.field_72449_c, 8, 0.08, 0.08, 0.08, 0.01, new int[0]);
        }
        if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new SPacketSoundEffect(chosen, SoundCategory.HOSTILE, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, volume, pitch));
        }
        return true;
    }

    @Override
    protected void handleParasiteStatus() {
        byte k = this.getParasiteStatus();
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
        if (!this.canPull && entityId != 0) {
            return;
        }
        this.pulling = 0;
        this.canPull = true;
        this.field_70180_af.func_187227_b(TARGET_ENTITY, (Object)entityId);
    }

    public boolean hasTargetedEntity() {
        if (!this.canPull) {
            return false;
        }
        return (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
    }

    public EntityLivingBase getTargetedEntity() {
        if (!this.hasTargetedEntity()) {
            return null;
        }
        if (this.field_70170_p.field_72995_K) {
            if (this.targetedEntity != null) {
                return this.targetedEntity;
            }
            Entity entity = this.field_70170_p.func_73045_a(((Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY)).intValue());
            if (entity instanceof EntityLivingBase) {
                this.targetedEntity = (EntityLivingBase)entity;
                return this.targetedEntity;
            }
            return null;
        }
        return this.func_70638_az();
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
        }
        boolean flag = this.func_70097_a(source, amount);
        if (!flag) {
            return false;
        }
        if (this.leftTendril.getId() == id) {
            this.leftTendrilHealth -= amount;
            if (this.leftTendrilHealth <= 0.0f) {
                EntityTendril tendril = new EntityTendril(this.field_70170_p);
                tendril.setSkin(3);
                tendril.func_82149_j(this.leftTendril);
                this.field_70170_p.func_72838_d((Entity)tendril);
                this.field_70170_p.func_72973_f((Entity)this.leftTendril);
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
                this.cutResistances(SPConfig.adaptedPointDamCap / 2);
                SPMain.network.sendToAll((IMessage)new SPPacketEntityBodyDead(this.func_145782_y(), id));
            }
        } else if (this.rightTendril.getId() == id) {
            this.rightTendrilHealth -= amount;
            if (this.rightTendrilHealth <= 0.0f) {
                EntityTendril tendril = new EntityTendril(this.field_70170_p);
                tendril.setSkin(3);
                tendril.func_82149_j(this.rightTendril);
                this.field_70170_p.func_72838_d((Entity)tendril);
                this.field_70170_p.func_72973_f((Entity)this.rightTendril);
                this.field_70170_p.func_72960_a((Entity)this, (byte)22);
                this.cutResistances(SPConfig.adaptedPointDamCap / 2);
                SPMain.network.sendToAll((IMessage)new SPPacketEntityBodyDead(this.func_145782_y(), id));
            }
        }
        return flag;
    }

    private IllusionType pickIllusionType() {
        float r = this.field_70146_Z.nextFloat();
        return r < 0.55f ? IllusionType.FOOTSTEP : (r < 0.9f ? IllusionType.AMBIENT : IllusionType.GROWL);
    }

    private SoundEvent srpSound(String fieldName) {
        try {
            Field f = SPSounds.class.getField(fieldName);
            Object v = f.get(null);
            if (v instanceof SoundEvent) {
                return (SoundEvent)v;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return null;
    }

    private List<SoundEvent> srpFindMatchingSounds(String[] whoTokens, String[] whatTokens) {
        ArrayList<SoundEvent> out = new ArrayList<SoundEvent>();
        try {
            for (Field f : SPSounds.class.getDeclaredFields()) {
                if (!SoundEvent.class.isAssignableFrom(f.getType())) continue;
                f.setAccessible(true);
                Object v = f.get(null);
                if (!(v instanceof SoundEvent)) continue;
                String name = f.getName().toUpperCase();
                boolean whoOk = false;
                boolean whatOk = false;
                for (String w : whoTokens) {
                    if (!name.contains(w)) continue;
                    whoOk = true;
                    break;
                }
                for (String w : whatTokens) {
                    if (!name.contains(w)) continue;
                    whatOk = true;
                    break;
                }
                if (!whoOk || !whatOk) continue;
                out.add((SoundEvent)v);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
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
        Vec3d dir = this.field_70146_Z.nextBoolean() ? look.func_186678_a(-1.0).func_178787_e(side.func_186678_a((this.field_70146_Z.nextDouble() - 0.5) * 0.6)).func_72432_b() : side.func_186678_a(this.field_70146_Z.nextBoolean() ? 1.0 : -1.0);
        return new Vec3d(p.field_70165_t + dir.field_72450_a * dist, p.field_70163_u + (double)(this.field_70146_Z.nextInt(3) - 1), p.field_70161_v + dir.field_72449_c * dist);
    }

    private boolean hullPlayTypedIllusion(EntityPlayer player, IllusionType type) {
        List<SoundEvent> pool;
        if (player == null) {
            return false;
        }
        IllusionProfile prof = this.profileFor(type);
        Vec3d pos = this.pickOffset(player, prof.minDist, prof.maxDist);
        BlockPos bp = new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
        if (!this.field_70170_p.func_175667_e(bp)) {
            pos = new Vec3d((pos.field_72450_a + player.field_70165_t) * 0.5, pos.field_72448_b, (pos.field_72449_c + player.field_70161_v) * 0.5);
            bp = new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
            if (!this.field_70170_p.func_175667_e(bp)) {
                return false;
            }
        }
        if ((pool = this.hullBuildSoundPool(type)).isEmpty()) {
            return false;
        }
        SoundEvent chosen = pool.get(this.field_70146_Z.nextInt(pool.size()));
        float pitch = prof.basePitch + (this.field_70146_Z.nextFloat() - 0.5f) * (prof.pitchJitter * 2.0f);
        if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p).func_184148_a(null, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, chosen, SoundCategory.HOSTILE, 1.0f, pitch);
        }
        if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new SPacketSoundEffect(chosen, SoundCategory.HOSTILE, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 1.0f, pitch));
        }
        return true;
    }

    private IllusionProfile profileFor(IllusionType t) {
        switch (t) {
            case FOOTSTEP: {
                return new IllusionProfile(8.0, 14.0, 1.0f, 0.1f);
            }
            case AMBIENT: {
                return new IllusionProfile(12.0, 22.0, 1.0f, 0.12f);
            }
            case GROWL: {
                return new IllusionProfile(16.0, 28.0, 0.98f, 0.14f);
            }
        }
        return new IllusionProfile(12.0, 22.0, 1.0f, 0.12f);
    }

    private IllusionLoudness loudnessFor(IllusionType t) {
        switch (t) {
            case FOOTSTEP: {
                return new IllusionLoudness(0.35f, 0.08f);
            }
            case AMBIENT: {
                return new IllusionLoudness(0.4f, 0.1f);
            }
            case GROWL: {
                return new IllusionLoudness(0.5f, 0.12f);
            }
        }
        return new IllusionLoudness(0.4f, 0.1f);
    }

    private static float clampf(float v, float lo, float hi) {
        return v < lo ? lo : (v > hi ? hi : v);
    }

    @Override
    public void setBodyPartDead(int id) {
        if (this.leftTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        } else if (this.rightTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            if (this.getSSS()) {
                float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * SPConfigMobs.hulladaptedstealthdamage;
                if (entityIn instanceof EntityLivingBase) {
                    f += EnchantmentHelper.func_152377_a((ItemStack)this.func_184614_ca(), (EnumCreatureAttribute)((EntityLivingBase)entityIn).func_70668_bt());
                }
                entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f);
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
        return 2.0f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityHull(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    @Override
    public void func_70106_y() {
        if (this.leftTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        }
        if (this.rightTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
        }
        super.func_70106_y();
    }

    public boolean getSSS() {
        return (Boolean)this.field_70180_af.func_187225_a(CAM);
    }

    public void setSSS(boolean in) {
        this.field_70180_af.func_187227_b(CAM, (Object)in);
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0 || this.func_70644_a(MobEffects.field_76441_p)) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.AHULL_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.AHULL_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.AHULL_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SPConfigMobs.hulladaptedOrbEffects, mobs);
        }
        return flag;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SPSounds.HEAVY_STEPS_TWO, 0.15f, 1.0f);
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
        if (this.field_70146_Z.nextDouble() < SPConfig.variantChance || this.phaseCreated >= SPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(1)) {
                case 0: {
                    this.setSkin(7);
                }
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
            if (this.leftTendrilHealth <= 0.0f) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
            }
        }
        if (compound.func_150297_b("parasiterightTendril", 99)) {
            this.rightTendrilHealth = compound.func_74760_g("parasiterightTendril");
            if (this.rightTendrilHealth <= 0.0f) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)22);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getLeft() {
        return this.leftTendrilHealth;
    }

    @SideOnly(value=Side.CLIENT)
    public float getRight() {
        return this.rightTendrilHealth;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 11) {
            this.leftTendrilHealth = 0.0f;
        } else if (id == 22) {
            this.rightTendrilHealth = 0.0f;
        } else {
            super.func_70103_a(id);
        }
    }

    private static boolean anyContains(String hay, String ... needles) {
        for (String n : needles) {
            if (!hay.contains(n)) continue;
            return true;
        }
        return false;
    }

    private List<SoundEvent> hullBuildSoundPool(IllusionType type) {
        String[] what;
        String[] who = new String[]{"HULL", "MANDUCATOR"};
        switch (type) {
            case FOOTSTEP: {
                what = new String[]{"STEP", "FOOT", "WALK", "MOVE"};
                break;
            }
            case AMBIENT: {
                what = new String[]{"AMBIENT", "IDLE", "BREATH"};
                break;
            }
            case GROWL: {
                what = new String[]{"GROWL", "ROAR", "ALERT", "SNARL", "SCREAM"};
                break;
            }
            default: {
                what = new String[]{"AMBIENT"};
            }
        }
        ArrayList<SoundEvent> out = new ArrayList<SoundEvent>();
        try {
            for (Field f : SPSounds.class.getDeclaredFields()) {
                boolean whatOk;
                if (!SoundEvent.class.isAssignableFrom(f.getType())) continue;
                f.setAccessible(true);
                Object v = f.get(null);
                if (!(v instanceof SoundEvent)) continue;
                SoundEvent s = (SoundEvent)v;
                String fieldName = f.getName().toUpperCase();
                String regName = String.valueOf(s.func_187503_a()).toUpperCase();
                if (regName.contains("SILENCE") || fieldName.contains("SILENCE")) continue;
                boolean whoOk = EntityHullAdapted.anyContains(fieldName, who) || EntityHullAdapted.anyContains(regName, who);
                boolean bl = whatOk = EntityHullAdapted.anyContains(fieldName, what) || EntityHullAdapted.anyContains(regName, what);
                if (!whoOk || !whatOk) continue;
                out.add(s);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return out;
    }

    private void dumpSPSoundsOnce() {
        if (this.hullDumpedSounds || this.field_70170_p.field_72995_K) {
            return;
        }
        this.hullDumpedSounds = true;
        StringBuilder sb = new StringBuilder("[Hull] SPSounds detected:");
        try {
            for (Field f : SPSounds.class.getDeclaredFields()) {
                if (!SoundEvent.class.isAssignableFrom(f.getType())) continue;
                f.setAccessible(true);
                Object v = f.get(null);
                if (!(v instanceof SoundEvent)) continue;
                SoundEvent s = (SoundEvent)v;
                sb.append("\n - ").append(f.getName()).append(" -> ").append(s.func_187503_a());
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        System.out.println(sb.toString());
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

    private static enum CloakPhase {
        IDLE,
        PRE_CLOAK,
        CLOAKED,
        PRE_DECLOAK;

    }
}

