/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerCow;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfCow
extends EntityPInfected
implements EntityCanMelt {
    private static final DataParameter<Float> HEIGH = EntityDataManager.func_187226_a(EntityInfCow.class, (DataSerializer)DataSerializers.field_187193_c);
    private static final DataParameter<Boolean> MELTING = EntityDataManager.func_187226_a(EntityInfCow.class, (DataSerializer)DataSerializers.field_187198_h);
    private float aSize;
    private int sound;
    private int attacking;
    private double targetX;
    private double targetY;
    private double targetZ;
    private boolean skillCharge;

    public EntityInfCow(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.9f, 1.4f);
        this.aSize = 1.0f;
        this.canModRender = 1;
        this.type = (byte)11;
        this.fuseTime = 40;
        this.skillCharge = false;
        this.thisMelting = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 13;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infcowCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 60, 32, 8, true, 1));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFCOW_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFCOW_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.2f);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFCOW_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFCOW_ATTACK_DAMAGE);
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
            this.setTHeigh(0.17f);
        }
        if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() <= SRPAttributes.INFCOW_HEADCHANCE) {
            ParasiteSummon.spawnM(this, new String[]{"srparasites:sim_cowhead;1;1"}, 0, false, this.func_95999_t());
        }
    }

    @Override
    public void melt() {
        this.setWait(1000);
        this.field_70180_af.func_187227_b(HEIGH, (Object)Float.valueOf(1.4f));
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
                if ((double)this.getTHeigh() <= 0.7 || this.sound >= 73) {
                    EntityLesh out = new EntityLesh(this.field_70170_p);
                    out.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                    if (this.func_95999_t() != null) {
                        out.func_96094_a(this.func_95999_t());
                    }
                    this.func_70106_y();
                    this.field_70170_p.func_72838_d((Entity)out);
                    out.setLegs(SRPAttributes.INFCOW_V, false);
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

    @Override
    protected void selfExplode() {
        super.selfExplode();
        ParasiteSummon.spawnM(this, new String[]{SRPConfigMobs.infcowmob}, 0, false, this.func_95999_t());
    }

    public float func_70047_e() {
        return 1.3f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INFECTEDCOW_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.INFECTEDCOW_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.INFECTEDCOW_DEATH;
    }

    @Override
    public EntityPFeral getFeral(World in) {
        return new EntityFerCow(in);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SoundEvents.field_187566_ao, 0.15f, 1.0f);
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillCharge;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillCharge = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.charge();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void charge() {
        ++this.attacking;
        this.MiniDamage = 0.0f;
        if (this.attacking < 40) {
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if (entitylivingbase == null || !this.field_70122_E || this.func_70090_H() || entitylivingbase.field_70163_u > this.field_70163_u) {
                this.skillCharge = true;
                this.attacking = 0;
                this.setParasiteStatus(0);
                this.MiniDamage = SRPConfig.infectedMinDamage;
                return;
            }
            if (!entitylivingbase.func_70089_S()) {
                this.skillCharge = true;
                this.attacking = 0;
                this.setParasiteStatus(0);
                this.MiniDamage = SRPConfig.infectedMinDamage;
                return;
            }
            if (this.attacking <= 39) {
                double dis = this.func_70032_d((Entity)entitylivingbase);
                this.setParasiteStatus(3);
                this.func_70661_as().func_75499_g();
                this.targetX = this.field_70165_t + 15.0 * (entitylivingbase.field_70165_t - this.field_70165_t) / dis;
                this.targetY = this.field_70163_u + 15.0 * (entitylivingbase.field_70163_u - this.field_70163_u) / dis;
                this.targetZ = this.field_70161_v + 15.0 * (entitylivingbase.field_70161_v - this.field_70161_v) / dis;
            }
        }
        if (this.attacking == 40) {
            this.func_70661_as().func_75492_a(this.targetX, this.targetY, this.targetZ, 2.0);
        }
        if (this.attacking >= 40) {
            for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(1.0, 0.0, 1.0))) {
                if (mob == this || mob instanceof EntityParasiteBase) continue;
                float f = (float)MathHelper.func_181159_b((double)(mob.field_70161_v - this.field_70161_v), (double)(mob.field_70165_t - this.field_70165_t));
                EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, f, (EntityLivingBase)this, 1.0f, false, 0.5f);
                this.field_70170_p.func_72838_d((Entity)damage);
            }
        }
        this.skillBreakBlocks();
        if (!this.field_70122_E) {
            this.field_70159_w *= 0.7;
            this.field_70179_y *= 0.7;
        }
        if (this.attacking >= 80 && this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
            this.attacking = 0;
            this.skillCharge = true;
            this.setParasiteStatus(2);
            this.MiniDamage = SRPConfig.infectedMinDamage;
        }
    }
}

