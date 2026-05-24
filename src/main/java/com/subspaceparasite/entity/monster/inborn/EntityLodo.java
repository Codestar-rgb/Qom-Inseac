/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.inborn;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.inborn.EntityMudo;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLodo
extends EntityParasiteBase {
    private int totalGrowtime = 10;
    private int actualGrowtime = 0;
    protected double buried;

    public EntityLodo(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.5f, 0.3f);
        this.field_70728_aV = SPAttributes.XP_LiTTLE;
        this.totalGrowtime = this.field_70146_Z.nextInt(60) + 60;
        this.killcount = -10.0;
        this.type = 1;
        this.buried = -1.0;
    }

    @Override
    public int getParasiteIDRegister() {
        return 5;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityLivingBase.class, (Predicate)new Predicate<EntityLivingBase>(){

            public boolean apply(@Nullable EntityLivingBase entity) {
                return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityCreeper) && !(entity instanceof EntityParasiteBase) && !(entity instanceof EntityAnimal);
            }
        }, 8.0f, 1.0, 1.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.LODO_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.LODO_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.LODO_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.LODO_KD_RESISTANCE);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.growTimer();
        this.growStage();
        this.buried();
    }

    public boolean buried() {
        if (this.buried >= 0.0) {
            this.func_70661_as().func_75499_g();
            this.field_70165_t = this.field_70169_q;
            this.field_70161_v = this.field_70166_s;
            int id = Block.func_176210_f((IBlockState)this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v).func_177977_b()));
            this.buried -= 0.02;
            for (int i = 0; i < 2; ++i) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, new int[]{id});
            }
            return true;
        }
        if (this.getParasiteStatus() == 3) {
            this.setParasiteStatus(0);
        }
        return false;
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
        if (entityIn instanceof EntityLivingBase) {
            SPPotions.applyStackPotion(SPPotions.COTH_E, (EntityLivingBase)entityIn, 100, 1);
        }
    }

    protected void growStage() {
        if (!this.field_70170_p.field_72995_K && (this.actualGrowtime > this.totalGrowtime && ParasiteEventEntity.canSpawnNext || this.killcount > 1000.0)) {
            this.func_184185_a(SPSounds.LODO_MUDO, 1.0f, 1.0f);
            ParasiteEventEntity.spawnNext(this, new EntityMudo(this.field_70170_p), true, false);
        }
    }

    protected void growTimer() {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
            ++this.actualGrowtime;
        }
    }

    @Override
    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        return super.func_184645_a(player, hand);
    }

    public float func_70047_e() {
        return 0.3f;
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        return false;
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.LODO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.LODO_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.LODO_DEATH;
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74768_a("ruptergrow", this.actualGrowtime);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("ruptergrow", 99)) {
            this.actualGrowtime = compound.func_74762_e("ruptergrow");
        }
    }

    public void setFloorTimer() {
        this.buried = 1.0;
    }

    @SideOnly(value=Side.CLIENT)
    public double getFloorTimer() {
        return this.buried;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 50) {
            this.buried = 1.0;
        } else {
            super.func_70103_a(id);
        }
    }
}

