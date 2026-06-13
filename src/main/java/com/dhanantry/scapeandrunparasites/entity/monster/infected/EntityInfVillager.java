/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.monster.AbstractSkeleton
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfVillagerHead;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfVillager
extends EntityPInfected
implements EntityCanMelt {
    private static final DataParameter<Float> HEIGH = EntityDataManager.func_187226_a(EntityInfVillager.class, (DataSerializer)DataSerializers.field_187193_c);
    private static final DataParameter<Boolean> MELTING = EntityDataManager.func_187226_a(EntityInfVillager.class, (DataSerializer)DataSerializers.field_187198_h);
    private float aSize;
    private int sound;
    private int host;

    public EntityInfVillager(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.6f, 1.95f);
        this.aSize = 1.0f;
        this.sound = 0;
        this.canModRender = 1;
        this.type = (byte)11;
        this.thisMelting = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 27;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infvillagerCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFVILLAGER_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFVILLAGER_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.230000004172325);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFVILLAGER_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFVILLAGER_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(HEIGH, (Object)Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(MELTING, (Object)false);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.melting();
    }

    @Override
    protected void func_70609_aI() {
        super.func_70609_aI();
        if (this.getTHeigh() < 1.57f && !this.field_70170_p.field_72995_K) {
            this.setTHeigh(0.13f);
        }
        if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() <= SRPAttributes.INFVILLAGER_HEADCHANCE) {
            EntityInfVillagerHead out = new EntityInfVillagerHead(this.field_70170_p);
            out.func_82149_j((Entity)this);
            out.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)this)), null);
            out.setSkin(this.getSkin());
            out.cannotDespawn(false);
            this.field_70170_p.func_72838_d((Entity)out);
        }
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        if (!this.field_70170_p.field_72995_K) {
            if (SRPConfigMobs.hostEnabled && entityLivingIn instanceof AbstractSkeleton) {
                ++this.host;
                if (this.host >= SRPConfigMobs.hostSkele) {
                    this.particleStatus((byte)7);
                    EntityHost host = new EntityHost(this.field_70170_p);
                    host.func_82149_j((Entity)this);
                    host.func_180482_a(this.field_70170_p.func_175649_E(this.func_180425_c()), null);
                    this.field_70170_p.func_72838_d((Entity)host);
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
        if (flag && this.field_70146_Z.nextDouble() < (double)SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
            SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
        }
        return flag;
    }

    @Override
    public void melt() {
        this.setWait(1000);
        this.field_70180_af.func_187227_b(HEIGH, (Object)Float.valueOf(1.95f));
        this.field_70180_af.func_187227_b(MELTING, (Object)true);
    }

    @Override
    public void melting() {
        if (this.isMelting()) {
            if (this.sound % 20 == 0) {
                this.func_184185_a(SRPSounds.INFECTED_MELT, 1.0f, 1.0f);
            }
            ++this.sound;
            if ((double)this.getTHeigh() > 0.7) {
                this.setaSize(-0.005f);
                this.setTHeigh(-0.01f);
                this.func_70105_a(this.field_70130_N, this.getTHeigh());
            }
            if (!this.field_70170_p.field_72995_K) {
                if ((double)this.getTHeigh() <= 0.7 || this.sound >= 127) {
                    EntityLesh out = new EntityLesh(this.field_70170_p);
                    out.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                    if (this.func_95999_t() != null) {
                        out.func_96094_a(this.func_95999_t());
                    }
                    this.func_70106_y();
                    this.field_70170_p.func_72838_d((Entity)out);
                    out.setLegs(SRPAttributes.INFVILLAGER_V, false);
                }
            } else {
                this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 106, 0);
                this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
            }
        }
    }

    @Override
    public boolean isMelting() {
        return (Boolean)this.field_70180_af.func_187225_a(MELTING);
    }

    @Override
    public float getTHeigh() {
        return ((Float)this.field_70180_af.func_187225_a(HEIGH)).floatValue();
    }

    @Override
    public void setTHeigh(float in) {
        this.field_70180_af.func_187227_b(HEIGH, (Object)Float.valueOf(in += this.getTHeigh()));
    }

    @Override
    public float getaSize() {
        return this.aSize;
    }

    @Override
    public void setaSize(float in) {
        this.aSize += in;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensity2() {
        return this.aSize;
    }

    public float func_70047_e() {
        return 1.73f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INFECTEDHUMAN_GROWL;
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
        return new EntityFerVillager(in);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), 0.15f, 1.0f);
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
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
            this.setSkin(1);
        }
        return floo;
    }
}

