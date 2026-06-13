/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNodeType
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
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
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityNuuh;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHostII
extends EntityPMalleable
implements IRangedAttackMob,
EntityCutomAttack {
    private boolean open;
    private float buried;
    private int blockI;
    private int buriedC;
    private static final DataParameter<Boolean> UP = EntityDataManager.func_187226_a(EntityHostII.class, (DataSerializer)DataSerializers.field_187198_h);
    private float attackTimer;
    private boolean up;
    private int border;
    private boolean skillshockwave;

    public EntityHostII(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.5f, 0.25f);
        this.borderOrb = -1;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.func_184644_a(PathNodeType.WATER, -1.0f);
        this.field_70158_ak = true;
        this.canModRender = 0;
        this.type = (byte)41;
        this.buried = 8.3f;
        this.field_70138_W = 1.0f;
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, SRPConfig.adaptedWalls, false, null, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen));
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, SRPConfig.adaptedWalls, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen));
        }
        this.field_70728_aV = SRPAttributes.XP_ADAPTED;
        this.canD = SRPConfig.adapteddespawn;
        this.damageCap = SRPConfig.adaptedCap;
        this.foodSteal = SRPConfig.adaptedFoodSteal;
        this.pointCap = SRPConfig.adaptedPointCap;
        this.pointReduction = SRPConfig.adaptedPointRed;
        this.chanceLearn = SRPConfig.adaptedChanceLe;
        this.chanceLearnFire = SRPConfig.adaptedChanceLeFire;
        this.DamageTypeCap = SRPConfig.adaptedPointDamCap;
        this.MiniDamage = SRPConfig.adaptedMinDamage;
        this.regen = SRPConfig.adaptedRegen * SRPConfig.globalHealthMultiplier;
        this.oneMindDeathValue = SRPConfig.adaptedOneMindDeathV;
        this.regenEff = 2;
        this.adaptationCap = 0.95f;
    }

    @Override
    public int getParasiteIDRegister() {
        return 75;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 0.0, 5.0, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackMeleeRangeSwitch(this, 5.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackRangedStatus(this, 1.3, 10, 16.0f, false));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 20, (int)SRPConfig.adaptedFollow, 2, false, 1, true));
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(UP, (Object)false);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.HERD_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.HERD_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.12);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.HERD_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.adaptedFollow);
    }

    @Override
    public void func_70636_d() {
        if (this.func_175446_cd()) {
            this.setParasiteStatus(3);
            this.setBurrowed(true);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
            this.buriedC = 80;
            if (this.buriedC > 0) {
                --this.buriedC;
            }
            ++this.blockI;
            if (this.up) {
                this.attackTimer = (float)((double)this.attackTimer + 0.2);
                if (this.attackTimer > 1.0f) {
                    this.up = false;
                }
            } else {
                this.attackTimer = (float)((double)this.attackTimer - 0.1);
            }
            this.checkBurrowed();
            return;
        }
        super.func_70636_d();
        if (this.buriedC > 0) {
            --this.buriedC;
        }
        ++this.blockI;
        if (this.up) {
            this.attackTimer = (float)((double)this.attackTimer + 0.2);
            if (this.attackTimer > 1.0f) {
                this.up = false;
            }
        } else {
            this.attackTimer = (float)((double)this.attackTimer - 0.1);
        }
        this.spawnRupters();
        this.checkBurrowed();
        this.checkSpeed();
        this.teleportByGround();
        if (!(this.getBurrowed() || this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s)) {
            this.spawnGroundParticles();
        }
    }

    private void spawnRupters() {
        if (!SRPConfigMobs.nuuhEnabled) {
            return;
        }
        if (!this.field_70170_p.field_72995_K && !this.getBurrowed()) {
            AxisAlignedBB axisalignedbb;
            List moblist;
            if (this.func_70638_az() != null && this.field_70146_Z.nextInt(150) == 0) {
                AxisAlignedBB axisalignedbb2 = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(16.0);
                List moblist2 = this.field_70170_p.func_72872_a(EntityNuuh.class, axisalignedbb2);
                if (moblist2.size() <= 4) {
                    EntityNuuh out = new EntityNuuh(this.field_70170_p);
                    out.func_82149_j((Entity)this);
                    this.field_70170_p.func_72838_d((Entity)out);
                    out.func_70624_b(this.func_70638_az());
                }
            } else if (this.field_70146_Z.nextInt(400) == 0 && (moblist = this.field_70170_p.func_72872_a(EntityNuuh.class, axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(16.0))).size() <= 3) {
                EntityNuuh out = new EntityNuuh(this.field_70170_p);
                out.func_82149_j((Entity)this);
                this.field_70170_p.func_72838_d((Entity)out);
            }
        }
    }

    private void checkBurrowed() {
        if (this.getBurrowed()) {
            if (this.field_70131_O < 7.5f) {
                this.func_70105_a(1.5f, this.field_70131_O + 0.09f);
            }
            if (this.buried >= 0.0f) {
                this.buried -= 0.1f;
                this.spawnGroundParticles();
                this.open = false;
                this.field_70170_p.func_72960_a((Entity)this, (byte)12);
            } else {
                this.open = true;
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
            }
        } else {
            if (this.field_70131_O > 0.25f) {
                this.func_70105_a(1.5f, this.field_70131_O - 0.09f);
            }
            if (this.buried < 8.3f) {
                this.buried += 0.1f;
                this.spawnGroundParticles();
                this.open = false;
                this.field_70170_p.func_72960_a((Entity)this, (byte)12);
            }
        }
    }

    private void checkSpeed() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.func_70638_az() != null) {
                if (this.func_70068_e((Entity)this.func_70638_az()) <= 9.0) {
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
                if (!(block.func_177230_c() instanceof IMetaName) && block.func_185913_b() && this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a) {
                    this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.InfestRemain.func_176203_a(1));
                }
            }
        }
    }

    private void spawnGroundParticles() {
        IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
        if (state.func_177230_c() != Blocks.field_150350_a) {
            int id = Block.func_176210_f((IBlockState)state);
            for (int i = 0; i < 15; ++i) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 3.0f) - (double)this.field_70130_N, this.field_70163_u, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 3.0f) - (double)this.field_70130_N, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, new int[]{id});
            }
        }
    }

    private void teleportByGround() {
        EntityLivingBase target;
        if (this.srpTicks != 10) {
            return;
        }
        if (!this.getBurrowed() && this.field_70146_Z.nextInt(2) == 0 && this.field_70131_O <= 0.25f && (target = this.func_70638_az()) != null && target.func_70068_e((Entity)this) > 49.0 && ParasiteEventEntity.teleportDigging(this, 15.0f, target.func_180425_c(), 5, 1)) {
            this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 30, 30, false, false));
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
        }
        if (source == DamageSource.field_76368_d) {
            return false;
        }
        if (source == DamageSource.field_76380_i) {
            return super.func_70097_a(source, amount);
        }
        if (!this.getBurrowed() && this.field_70131_O < 1.0f) {
            if (source == DamageSource.field_76369_e) {
                return false;
            }
            if (source.func_76346_g() == null) {
                return super.func_70097_a(source, amount);
            }
            return false;
        }
        return super.func_70097_a(source, amount);
    }

    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
        if (this.func_70644_a(MobEffects.field_76421_d) && !this.getBurrowed()) {
            return;
        }
        if (this.field_70146_Z.nextDouble() < 0.9 && !this.getBurrowed()) {
            if (this.func_70661_as().func_75505_d() == null) {
                this.func_70661_as().func_75497_a((Entity)target, 0.5);
            }
            return;
        }
        this.buriedC = 120;
        if (this.getBurrowed() && this.field_70131_O > 1.0f) {
            EntityProjectileSpineball entitylargefireball;
            if (this.field_70146_Z.nextInt(4) == 0 && target.func_70068_e((Entity)this) < 225.0 && target.func_70068_e((Entity)this) > 25.0) {
                double d0 = target.field_70163_u + (double)target.func_70047_e() - (double)1.1f;
                double d1 = target.field_70165_t + target.field_70159_w - this.field_70165_t;
                double d2 = d0 - this.field_70163_u;
                double d3 = target.field_70161_v + target.field_70179_y - this.field_70161_v;
                float f = MathHelper.func_76133_a((double)(d1 * d1 + d3 * d3));
                EntityBomb bomb = new EntityBomb(this.field_70170_p, this, false);
                bomb.setFuse(40);
                bomb.setStren(0.0f);
                bomb.setSkin(1);
                bomb.setDamage((float)SRPAttributes.HERD_BOMBDAMAGE, 5);
                bomb.field_70125_A -= -20.0f;
                bomb.shootTwo(d1, d2 + (double)(f * 0.2f), d3, 0.75f, 8.0f);
                this.field_70170_p.func_72838_d((Entity)bomb);
                bomb.updateSTR();
            }
            if (this.field_70146_Z.nextInt(2) == 0) {
                return;
            }
            double d1 = 4.0;
            Vec3d vec3d = this.func_70676_i(1.0f);
            double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
            double d3 = target.func_174813_aQ().field_72338_b + (double)(target.field_70131_O / 2.0f) - (2.5 + this.field_70163_u + (double)(this.field_70131_O / 1.5f));
            double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
            EntityProjectileSpineball ball = entitylargefireball = new EntityProjectileSpineball(this.field_70170_p, (EntityLivingBase)this, d2, d3, d4, SRPAttributes.EMANA_RANGED_DAMAGE);
            ball.setDurationAmplifier(SRPConfigMobs.emanaPoisonDuration, SRPConfigMobs.emanaPoisonAmplifier);
            ball.setGearDamage(SRPConfigMobs.emanaGearD);
            entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
            entitylargefireball.field_70163_u = this.field_70163_u + (double)this.func_70047_e() - 0.2;
            entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
            this.field_70170_p.func_72838_d((Entity)entitylargefireball);
        } else {
            this.setParasiteStatus(3);
            this.setBurrowed(true);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
            this.func_70661_as().func_75499_g();
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (!this.getBurrowed() || this.field_70131_O < 1.0f) {
            if (entityIn == null) {
                return false;
            }
            if (this.func_70068_e(entityIn) > 4.0) {
                return false;
            }
            this.setParasiteStatus(3);
            this.setBurrowed(true);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
            this.buriedC = 160;
            return false;
        }
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            this.buriedC += 160;
            if (this.field_70146_Z.nextInt(3) == 0) {
                // empty if block
            }
        }
        return flag;
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        this.up = true;
        this.attackTimer = 0.0f;
        this.field_70170_p.func_72960_a((Entity)this, (byte)13);
        boolean flag = false;
        if (!this.getBurrowed() || this.field_70131_O < 1.0f) {
            return false;
        }
        this.func_184185_a(SRPSounds.SWIPE, 1.0f, 1.0f);
        this.func_184185_a(SRPSounds.SWIPE, 1.0f, 1.25f);
        this.func_184185_a(SRPSounds.SWIPE, 2.0f, 1.0f);
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(3.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob == this || mob instanceof EntityParasiteBase || !this.func_70685_l((Entity)mob) || !this.func_70652_k((Entity)mob)) continue;
            flag = true;
        }
        return flag;
    }

    public boolean func_96092_aw() {
        return super.func_96092_aw();
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SRPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityHost(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    @Override
    public boolean func_70687_e(PotionEffect potioneffectIn) {
        return super.func_70687_e(potioneffectIn);
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

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 0.5f;
    }

    public boolean getBurrowed() {
        return (Boolean)this.field_70180_af.func_187225_a(UP);
    }

    public void setBurrowed(boolean in) {
        this.field_70180_af.func_187227_b(UP, (Object)in);
    }

    public float func_70047_e() {
        return this.field_70131_O;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    @SideOnly(value=Side.CLIENT)
    public float getBurrowTimer() {
        return this.buried;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean getOpen() {
        return this.open;
    }

    @SideOnly(value=Side.CLIENT)
    public float getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 11) {
            this.open = true;
        } else if (id == 12) {
            this.open = false;
        } else if (id == 13) {
            this.up = true;
            this.attackTimer = 0.0f;
        } else if (id == 100) {
            for (int i = 0; i <= 1; ++i) {
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
            case 1: {
                return this.skillshockwave;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillshockwave = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.shockwave();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void shockwave() {
        this.func_70661_as().func_75499_g();
        if (this.border == 0) {
            float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4f + 2.0f;
            this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0f, v);
            ++this.border;
            return;
        }
        if (this.border <= 2) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)100);
        }
        if (this.field_70173_aa % 20 != 0) {
            return;
        }
        ++this.border;
        if (this.func_70638_az() == null) {
            this.skillshockwave = true;
            this.border = 0;
            return;
        }
        if (this.border == 2) {
            this.spawnShock();
            this.up = true;
            this.attackTimer = 0.0f;
            this.field_70170_p.func_72960_a((Entity)this, (byte)12);
            this.func_184185_a(SRPSounds.SWIPE, 2.0f, 1.0f);
        }
        if (this.border > 3) {
            this.skillshockwave = true;
            this.border = 0;
        }
    }

    private void spawnShock() {
        EntityWave wa = new EntityWave(this.field_70170_p);
        float f19 = MathHelper.func_76126_a((float)(this.field_70177_z * ((float)Math.PI / 180) - this.field_70704_bt * 0.01f));
        float f14 = 0.17453292f;
        float f16 = MathHelper.func_76134_b((float)f14);
        float f4 = MathHelper.func_76134_b((float)(this.field_70177_z * ((float)Math.PI / 180) - this.field_70704_bt * 0.01f));
        wa.func_70634_a(this.field_70165_t + -1.0 * (double)(f19 * 3.0f * f16), this.field_70163_u, this.field_70161_v - -1.0 * (double)(f4 * 3.0f * f16));
        wa.setDamages(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.3, this.MiniDamage, 1, 60);
        this.field_70170_p.func_72838_d((Entity)wa);
        wa.func_70624_b(this.func_70638_az());
    }
}

