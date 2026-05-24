/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.infected.head;

import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIAvoidEntityStatus;
import com.subspaceparasite.entity.ai.EntityAIAvoidOrAttack;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.misc.EntityPInfected;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityInfPlayerHead
extends EntityPInfected {
    public EntityInfPlayerHead(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.7f, 0.9f);
        this.killcount = -10.0;
        this.attackSpeedT = 15;
    }

    @Override
    public int getParasiteIDRegister() {
        return 71;
    }

    @Override
    public int canSpawnByIDData() {
        return SPConfigMobs.infhumanCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISkill(this, 40, 100, 3, true, 14));
        this.setskillLeapValues(0.7f, 2.5, 0);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAvoidOrAttack(this, 0.5f, 10, 2));
        this.field_70714_bg.func_75776_a(5, new EntityAIAvoidEntityStatus<EntityLivingBase>(this, EntityLivingBase.class, new Predicate<EntityLivingBase>(){

            public boolean apply(@Nullable EntityLivingBase e) {
                return !(e instanceof EntityWaterMob) && !(e instanceof EntityCreeper) && !(e instanceof EntityParasiteBase) && !(e instanceof EntityAnimal);
            }
        }, 8.0f, 1.3));
        this.field_70715_bh.func_75776_a(5, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityInhooM.class, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.INFADVENTURER_HEADHEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.INFADVENTURER_HEADDAMAGE);
    }

    public void func_70110_aj() {
    }

    public float func_70047_e() {
        return 0.8f;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (entityIn instanceof EntityInhooM && entityIn.func_70089_S() && this.func_70089_S()) {
            ParasiteEventEntity.spawnNext(this, new EntityInfPlayer(this.field_70170_p), true, false);
            ((EntityParasiteBase)entityIn).particleStatus((byte)7);
            entityIn.func_70106_y();
            return true;
        }
        return super.func_70652_k(entityIn);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && SPConfigSystems.disloGiveBodies && this.func_70089_S() && this.srpTicks == 10 && SPSaveData.get(this.field_70170_p, 44).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 20) >= 1) {
            ParasiteEventEntity.spawnNext(this, new EntityInfPlayer(this.field_70170_p), true, false);
            return;
        }
    }

    @Override
    public boolean func_70686_a(Class<? extends EntityLivingBase> cls) {
        if (cls == EntityPlayer.class || cls == EntityPlayerMP.class) {
            return true;
        }
        String name = null;
        try {
            name = EntityList.func_191306_a(cls).toString();
        }
        catch (Exception e) {
            return true;
        }
        if (name == null) {
            return true;
        }
        if (name.contains("subspaceparasite") && cls != EntityInhooM.class) {
            return false;
        }
        return !SPConfig.mobAttackingFull || !ParasiteEventEntity.checkName(name, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        super.func_180430_e(distance, damageMultiplier * 0.3f);
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.INFECTEDHEAD_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.INFECTEDHEAD_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.INFECTEDHEAD_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SPSounds.SMALL_STEPS;
    }
}

