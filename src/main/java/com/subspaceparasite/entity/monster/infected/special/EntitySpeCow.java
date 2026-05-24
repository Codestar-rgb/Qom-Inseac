/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAreaEffectCloud
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.infected.special;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIAttackRangedStatus;
import com.subspaceparasite.entity.ai.EntityAIGetFollowers;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.misc.EntityPAssimara;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.spawn.ParasiteSummon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySpeCow
extends EntityPAssimara
implements IRangedAttackMob {
    int vomit;

    public EntitySpeCow(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.9f, 1.4f);
        this.canModRender = 1;
        this.type = (byte)11;
        this.fuseTime = 40;
    }

    @Override
    public int getIDSpawn() {
        return 13;
    }

    @Override
    public int getParasiteIDRegister() {
        return 322;
    }

    @Override
    public int canSpawnByIDData() {
        return SPConfigMobs.infcowCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackMeleeRangeSwitch(this, 3.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackRangedStatus(this, 1.0, 200, 7.0f, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.MARCOW_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.MARCOW_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.2f);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.MARCOW_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.MARCOW_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.assimaraFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.vomit > 0) {
            --this.vomit;
            for (int i = 0; i < 6; ++i) {
                Vec3d vec3d = this.func_70676_i(1.0f);
                double bon = 1.2;
                double offsetX = this.field_70165_t + vec3d.field_72450_a * bon;
                double offsetY = this.field_70163_u + (double)this.func_70047_e() - 0.2;
                double offsetZ = this.field_70161_v + vec3d.field_72449_c * bon;
                double motionX = (double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f))) * 0.2;
                double motionZ = (double)MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * 0.2;
                double motionY = 0.01 + this.field_70146_Z.nextDouble() * 0.1;
                double spreadFactor = 0.25;
                this.spawnParticles(SPEnumParticle.GCLOUD, 123, 0, 196, offsetX, offsetY, offsetZ, motionX += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor, motionY, motionZ += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor);
            }
        }
    }

    @Override
    protected void selfExplode() {
        super.selfExplode();
        ParasiteSummon.spawnM(this, new String[]{SPConfigMobs.infcowmob}, 0, false, this.func_95999_t());
    }

    public float func_70047_e() {
        return 1.3f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.INFECTEDCOW_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.INFECTEDCOW_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.INFECTEDCOW_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SoundEvents.field_187566_ao, 0.15f, 1.0f);
    }

    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
        this.field_70170_p.func_72960_a((Entity)this, (byte)100);
        Vec3d vec3d = this.func_70676_i(1.0f);
        double bon = 4.5;
        EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.field_70170_p, this.field_70165_t + vec3d.field_72450_a * bon, this.field_70163_u, this.field_70161_v + vec3d.field_72449_c * bon);
        entityareaeffectcloud.func_184483_a(3.0f);
        entityareaeffectcloud.func_184486_b(100);
        entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / (float)entityareaeffectcloud.func_184489_o());
        entityareaeffectcloud.func_184496_a(new PotionEffect(SPPotions.VOMIT_E, 300, 0, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(SPPotions.VIRA_E, 300, 20, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76437_t, 300, 20, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76438_s, 300, 20, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_82731_v, 300, 20, false, true));
        this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
        this.lookAt((Entity)target);
        this.setWait(60);
    }

    public void func_184724_a(boolean swingingArms) {
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 100) {
            this.vomit = 40;
        } else {
            super.func_70103_a(id);
        }
    }
}

