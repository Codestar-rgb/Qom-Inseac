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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.focused;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.subspaceparasite.entity.ai.EntityAIBlockResidue;
import com.subspaceparasite.entity.ai.EntityAIEvade;
import com.subspaceparasite.entity.ai.EntityAIGetFollowers;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityCanClimb;
import com.subspaceparasite.entity.ai.misc.EntityCutomAttack;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.entity.ai.misc.EntityPFocused;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.PathNavigateClimberStatus;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import java.util.List;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShycoFocused
extends EntityPFocused
implements EntityCutomAttack,
EntityBodyParts,
EntityCanClimb {
    private float attackTimer;
    private boolean up;
    private double extraDamage;
    private double currentDamage;
    private double hpLeft;
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityShycoFocused.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityShycoFocused(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.901f, 3.5f);
        this.field_70138_W = 1.0f;
        this.extraDamage = this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
    }

    @Override
    public int getParasiteIDRegister() {
        return 83;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 4.0));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIBlockResidue(this, 2));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 3, 32));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 25, 10, 4.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.SHYCO_HEALTH + SPAttributes.SHYCO_A_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.SHYCO_ARMOR + SPAttributes.SHYCO_A_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.SHYCO_KD_RESISTANCE + SPAttributes.SHYCO_A_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.SHYCO_ATTACK_DAMAGE + SPAttributes.SHYCO_A_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.adaptedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70146_Z.nextDouble() < this.hpLeft && this.field_70170_p.field_72995_K) {
            this.spawnParticles(SPEnumParticle.GCLOUD, 127, 106, 0);
        }
        if (this.up) {
            this.attackTimer = (float)((double)this.attackTimer + 0.2);
            if ((double)this.attackTimer > 1.2) {
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
    public byte climbStatus() {
        return (Byte)this.field_70180_af.func_187225_a(CLIMBING);
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

    protected PathNavigate func_175447_b(World worldIn) {
        return new PathNavigateClimberStatus((EntityLiving)this, worldIn);
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        boolean flag = super.func_70097_a(source, amount);
        if (flag) {
            this.currentDamage = this.extraDamage * (1.0 - (double)(this.func_110143_aJ() / this.func_110138_aP()) * SPAttributes.SHYCO_A_I_DAMAGE);
        }
        this.hpLeft = 1.0f - this.func_110143_aJ() / this.func_110138_aP();
        return flag;
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
        return flag;
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && entityIn instanceof EntityLivingBase) {
            entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.currentDamage);
        }
        this.hpLeft = 1.0f - this.func_110143_aJ() / this.func_110138_aP();
        return flag;
    }

    public float func_70047_e() {
        return 3.3f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ASHYCO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.ASHYCO_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.ASHYCO_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SPSounds.MONSTER_STEP, 0.15f, 1.0f);
    }

    @Override
    public void func_70106_y() {
        super.func_70106_y();
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        this.up = true;
        this.attackTimer = 0.0f;
        this.field_70170_p.func_72960_a((Entity)this, (byte)12);
        boolean flag = false;
        this.func_184185_a(SPSounds.SWIPE, 2.0f, 1.0f);
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, entityIn.field_70165_t + 1.0, entityIn.field_70163_u + 1.0, entityIn.field_70161_v + 1.0).func_186662_g(3.0);
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
        if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
            this.setSkin(1);
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((SPAttributes.SHYCO_HEALTH + SPAttributes.SHYCO_A_HEALTH) * 0.5);
            this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((SPAttributes.SHYCO_ATTACK_DAMAGE + SPAttributes.SHYCO_A_ATTACK_DAMAGE) * 2.0);
            this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
        }
        return floo;
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
    }

    @SideOnly(value=Side.CLIENT)
    public float getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 12) {
            this.up = true;
            this.attackTimer = 0.0f;
        } else {
            super.func_70103_a(id);
        }
    }

    @Override
    protected EntityPCosmical getThis() {
        return new EntityShycoFocused(this.field_70170_p);
    }
}

