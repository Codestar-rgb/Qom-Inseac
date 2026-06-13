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
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected.head;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfDragonE;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileDragonE;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityInfDragonEHead
extends EntityPInfected {
    public EntityInfDragonEHead(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.75f, 1.95f);
        this.killcount = -10.0;
        this.attackSpeedT = 15;
    }

    @Override
    public int getParasiteIDRegister() {
        return 70;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infdragoneCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIFireballAttack(this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
        this.field_70715_bh.func_75776_a(5, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFDRAGONE_HEADHEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFDRAGONE_HEADDAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && SRPConfigSystems.disloGiveBodies && this.func_70089_S() && this.srpTicks == 10 && SRPSaveData.get(this.field_70170_p, 44).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 20) >= 1) {
            ParasiteEventEntity.spawnNext(this, new EntityInfDragonE(this.field_70170_p), true, false);
            return;
        }
    }

    public void func_70110_aj() {
    }

    public float func_70047_e() {
        return 0.8f;
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        super.func_180430_e(distance, damageMultiplier * 0.3f);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SRPSounds.SMALL_STEPS;
    }

    static class AIFireballAttack
    extends EntityAIBase {
        private final EntityInfDragonEHead parentEntity;
        public int attackTimer;

        public AIFireballAttack(EntityInfDragonEHead ghast) {
            this.parentEntity = ghast;
        }

        public boolean func_75250_a() {
            return this.parentEntity.func_70638_az() != null;
        }

        public void func_75249_e() {
            this.attackTimer = 0;
        }

        public void func_75251_c() {
        }

        public void func_75246_d() {
            EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
            double d0 = 64.0;
            if (entitylivingbase == null) {
                return;
            }
            if (entitylivingbase.func_70068_e((Entity)this.parentEntity) < 4096.0 && this.parentEntity.func_70685_l((Entity)entitylivingbase)) {
                World world = this.parentEntity.field_70170_p;
                ++this.attackTimer;
                if (this.parentEntity.func_70644_a(SRPPotions.RAGE_E)) {
                    ++this.attackTimer;
                }
                if (this.attackTimer == 15) {
                    double d1 = 4.0;
                    Vec3d vec3d = this.parentEntity.func_70676_i(1.0f);
                    double d2 = entitylivingbase.field_70165_t - (this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0);
                    double d3 = entitylivingbase.func_174813_aQ().field_72338_b + (double)(entitylivingbase.field_70131_O / 2.0f) - (0.5 + this.parentEntity.field_70163_u + (double)(this.parentEntity.field_70131_O / 2.0f));
                    double d4 = entitylivingbase.field_70161_v - (this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0);
                    world.func_180498_a((EntityPlayer)null, 1016, new BlockPos((Entity)this.parentEntity), 0);
                    EntityProjectileDragonE entitylargefireball = new EntityProjectileDragonE(world, (EntityLivingBase)this.parentEntity, d2, d3, d4);
                    entitylargefireball.field_70165_t = this.parentEntity.field_70165_t + vec3d.field_72450_a;
                    entitylargefireball.field_70163_u = this.parentEntity.field_70163_u + (double)(this.parentEntity.field_70131_O / 2.0f) + 0.5;
                    entitylargefireball.field_70161_v = this.parentEntity.field_70161_v + vec3d.field_72449_c;
                    world.func_72838_d((Entity)entitylargefireball);
                    this.attackTimer = 0;
                    for (int i = 0; i <= 2; ++i) {
                        this.parentEntity.field_70170_p.func_175688_a(EnumParticleTypes.FLAME, this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0, this.parentEntity.field_70163_u + (double)(this.parentEntity.field_70131_O / 2.0f) + 0.5, this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0, 0.0, -1.0, 0.0, new int[0]);
                    }
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        }
    }
}

