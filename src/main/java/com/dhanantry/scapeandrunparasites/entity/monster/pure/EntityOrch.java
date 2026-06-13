/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.pure;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvadeDash;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileWebball;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.PathNavigateClimberStatus;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityOrch
extends EntityPPure
implements EntityCutomAttack,
EntityCanShoot {
    private float attackTimer;
    private boolean up;
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityOrch.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityOrch(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.901f, 4.1f);
        this.field_70138_W = 1.0f;
        this.field_70158_ak = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 84;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISkill(this, 40, 100, 10, true, 14));
        this.setskillLeapValues(0.5f, 3.5, 4);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.12));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackProjectile(this, 40, 15, 4));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 7));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 2.0));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvadeDash(this, 17, 2, 5, 3.5, 15));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ORCH_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ORCH_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2775);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.ORCH_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.ORCH_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.pureFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.up) {
            this.attackTimer = (float)((double)this.attackTimer + 0.2);
            if (this.attackTimer > 1.0f) {
                this.up = false;
            }
        } else {
            this.attackTimer = (float)((double)this.attackTimer - 0.1);
        }
        if (!this.field_70170_p.field_72995_K) {
            this.setBesideClimbableBlock(this.field_70123_F);
        }
    }

    @Override
    protected boolean spawnT(EntityLivingBase in, int range2, int mini2, int check, int type) {
        if (this.func_70685_l((Entity)in)) {
            return false;
        }
        if (this.field_70146_Z.nextInt(4) == 0) {
            return super.spawnT(in, range2, mini2, check, type);
        }
        return super.spawnT(in, range2, mini2, check, 2);
    }

    public void func_70110_aj() {
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(CLIMBING, (Object)0);
    }

    public boolean isBesideClimbableBlock() {
        return ((Byte)this.field_70180_af.func_187225_a(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = (Byte)this.field_70180_af.func_187225_a(CLIMBING);
        b0 = climbing ? (byte)(b0 | 1) : (byte)(b0 & 0xFFFFFFFE);
        this.field_70180_af.func_187227_b(CLIMBING, (Object)b0);
    }

    public boolean func_70617_f_() {
        return this.isBesideClimbableBlock();
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        super.func_70624_b(entitylivingbaseIn);
    }

    protected PathNavigate func_175447_b(World worldIn) {
        return new PathNavigateClimberStatus((EntityLiving)this, worldIn);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            entityIn.field_70181_x += 0.5000000059604645;
        }
        return flag;
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 3.5f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.ORCH_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.ORCH_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.ORCH_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.orchOrbEffects, mobs);
        }
        return flag;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SRPSounds.HEAVY_STEPS_MULTIPLE, 0.15f, 1.0f);
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        if (this.borderOrb != 0) {
            return false;
        }
        this.up = true;
        this.attackTimer = 0.0f;
        this.field_70170_p.func_72960_a((Entity)this, (byte)12);
        boolean flag = false;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, entityIn.field_70165_t + 1.0, entityIn.field_70163_u + 1.0, entityIn.field_70161_v + 1.0).func_186662_g(2.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob instanceof EntityParasiteBase) {
                if (this.func_70638_az() != mob) continue;
                this.func_70624_b(null);
                return false;
            }
            if (mob == this || !this.func_70685_l((Entity)mob) || !this.func_70652_k((Entity)mob)) continue;
            flag = true;
        }
        return flag;
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
            switch (this.field_70146_Z.nextInt(2)) {
                case 0: {
                    this.setSkin(1);
                    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ORCH_HEALTH * 0.5);
                    this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.ORCH_ATTACK_DAMAGE * 1.5);
                    this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
                    break;
                }
                case 1: {
                    this.setSkin(7);
                }
            }
        }
        return floo;
    }

    @SideOnly(value=Side.CLIENT)
    public float getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    protected void skillLeap() {
        if (this.leapMotionY == 0.0f) {
            return;
        }
        if (this.func_70638_az() != null && this.shouldWorkTask() && !this.func_70644_a(MobEffects.field_76421_d) && this.getParasiteStatus() <= 2) {
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if (this.attacking == 0) {
                ++this.attacking;
                this.targetX = entitylivingbase.field_70165_t;
                this.targetZ = entitylivingbase.field_70161_v;
            }
        }
        if (this.attacking >= 1) {
            ++this.attacking;
            this.skillBreakBlocks();
            if (this.attacking % 5 == 0 && this.attacking < 40 && SRPConfigMobs.lodoEnabled) {
                EntityLodo b = new EntityLodo(this.field_70170_p);
                b.func_82149_j((Entity)this);
                this.field_70170_p.func_72838_d((Entity)b);
            }
            if (this.attacking == 2 && this.field_70122_E) {
                this.setParasiteStatus(10);
                this.func_70661_as().func_75499_g();
                double d0 = this.targetX - this.field_70165_t;
                double d1 = this.targetZ - this.field_70161_v;
                double f = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1));
                this.field_70181_x = this.leapMotionY;
                this.field_70159_w += d0 / f * this.jumpSpeed * 0.9 + this.field_70159_w * 0.3;
                this.field_70179_y += d1 / f * this.jumpSpeed * 0.9 + this.field_70179_y * 0.3;
            }
            if (this.attacking > 2 && this.field_70122_E) {
                if (this.jumpR != 0) {
                    float damage = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b((double)this.jumpR, 2.0, (double)this.jumpR);
                    List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase mob : moblist) {
                        if (mob == this || mob instanceof EntityParasiteBase) continue;
                        mob.func_70653_a((Entity)mob, 2.5f, this.field_70165_t - mob.field_70165_t, this.field_70161_v - mob.field_70161_v);
                        this.func_70652_k((Entity)mob);
                    }
                }
                this.setParasiteStatus(0);
                this.attacking = 0;
                if (this.type >= 31 && this.field_70131_O > 2.0f) {
                    this.func_184185_a(SRPSounds.HITGROUND, 15.0f, 1.0f);
                }
                this.SkillLeapFlag = true;
            }
        }
    }

    @Override
    public void skillBreakBlocks() {
        EntityLivingBase target;
        if (this.getBlockH() == 0.0f) {
            return;
        }
        int blocksbroke = 0;
        int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        int offsetT = 0;
        if (this.func_70638_az() != null && (target = this.func_70638_az()).func_70092_e(this.field_70165_t, target.field_70163_u, this.field_70161_v) < 9.0) {
            if (target.field_70163_u - this.field_70163_u < -1.0) {
                offsetT -= 2;
            } else if (target.field_70163_u - this.field_70163_u > 2.0) {
                ++offsetT;
                this.BGrange = 0;
            }
        }
        for (int k2 = -1 * this.BGrange; k2 <= 1 * this.BGrange; ++k2) {
            for (int l2 = -1 * this.BGrange; l2 <= 1 * this.BGrange; ++l2) {
                for (int j = 1 + offsetT; j <= this.BGheight + offsetT; ++j) {
                    String name;
                    double i3 = l1 + (double)k2;
                    double k = i1 + j;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    Block block = iblockstate.func_177230_c();
                    float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                    if (!(bHard <= this.getBlockH()) || !(bHard >= 0.0f) || block instanceof IMetaName && block != SRPBlocks.ParasiteCanister || block == SRPBlocks.BiomeHeart || block == SRPBlocks.ColonyHeart || block == SRPBlocks.ParasiteRubbleDense || block == SRPBlocks.ParasiteCanisterActive || this.blockException(name = block.getRegistryName().toString()) || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this, (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
                    if (SRPConfig.cystActive) {
                        int meta = block.func_176201_c(iblockstate);
                        boolean bl = flag = this.destroyBlockPos(blockpos, false) || flag;
                        if (SRPConfig.doTileDrops) {
                            this.addToBlockInv(name + ";" + meta);
                        }
                    } else {
                        this.destroyBlockPos(blockpos, SRPConfig.doTileDrops);
                    }
                    ++blocksbroke;
                }
            }
        }
        this.BGrange = Brangeatm;
        this.SkillBGflag = true;
    }

    public EntityFireball getProj(double accelX, double accelY, double accelZ) {
        this.func_184185_a(SRPSounds.DORPA_RANGE, 2.0f, 1.0f);
        EntityLivingBase entitylivingbase = this.func_70638_az();
        accelY = entitylivingbase.func_174813_aQ().field_72338_b + (double)(entitylivingbase.field_70131_O / 3.0f) - (1.0 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        return new EntityProjectileWebball(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ, 2);
    }

    @Override
    public void playProjSound() {
    }
}

