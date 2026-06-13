/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCrude;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityInhooS
extends EntityPCrude {
    public boolean disloNumberTwenty = false;

    public EntityInhooS(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.6f, 0.85f);
        this.canModRender = 0;
        this.type = (byte)11;
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, SRPConfig.infectedSneakPen, SRPConfig.infectedInviPen));
        }
    }

    @Override
    public int getParasiteIDRegister() {
        return 39;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, 0.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INHOOS_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INHOOS_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.12);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INHOOS_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INHOOS_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (SRPConfigSystems.disloGiveBodies && this.func_70089_S() && this.srpTicks == 10 && this.disloNumberTwenty) {
            ParasiteEventEntity.spawnNext(this, ParasiteEventEntity.getRandomFeral(this.field_70170_p), true, false);
            return;
        }
    }

    public float func_70047_e() {
        return this.field_70131_O;
    }

    @Override
    protected void placeNidus() {
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INHOOS_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.INHOOS_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.INHOOS_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SRPSounds.LITE_FLESH_SLIDE, 0.3f, 1.0f);
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74757_a("dsltwenty", this.disloNumberTwenty);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("dsltwenty", 99)) {
            this.disloNumberTwenty = compound.func_74767_n("dsltwenty");
        }
    }
}

