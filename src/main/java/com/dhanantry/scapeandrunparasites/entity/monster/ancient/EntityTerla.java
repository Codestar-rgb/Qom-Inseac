/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.BossInfo$Color
 *  net.minecraft.world.BossInfo$Overlay
 *  net.minecraft.world.BossInfoServer
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.ancient;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAncient;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityTerla
extends EntityPAncient
implements EntityBodyParts,
IRangedAttackMob,
EntityCutomAttack {
    private EntityBody head;
    private EntityBody middle;
    private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS).func_186741_a(false);

    public EntityTerla(World worldIn) {
        super(worldIn);
        this.func_70105_a(2.4f, 2.9f);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70158_ak = true;
        this.type = (byte)63;
        this.borderOrb = -1;
        this.head = new EntityBody(this, 2.4f, 7.5f, 1.0f, -3.0f, 0.0f, -1, 1, false, 0.2f);
        this.middle = new EntityBody(this, 2.4f, 4.5f, 1.0f, 0.0f, 3.0f, 1, 2, false, 0.2f);
    }

    @Override
    public int getParasiteIDRegister() {
        return 20;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackMeleeRangeSwitch(this, 10.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.0, false, 100.0, 5.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackRangedStatus(this, 1.0, 80, 40.0f, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.TERLA_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.TERLA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.23f);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.TERLA_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.ancientFollow);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.head.func_70071_h_();
        this.middle.func_70071_h_();
    }

    @Override
    protected void func_70619_bc() {
        super.func_70619_bc();
        this.bossInfo.func_186735_a(this.func_110143_aJ() / this.func_110138_aP());
    }

    public void func_96094_a(String name) {
        super.func_96094_a(name);
        this.bossInfo.func_186739_a(this.func_145748_c_());
    }

    public void func_184178_b(EntityPlayerMP player) {
    }

    public void func_184203_c(EntityPlayerMP player) {
        super.func_184203_c(player);
        this.bossInfo.func_186761_b(player);
    }

    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
        Vec3d vec3d = this.func_70676_i(1.0f);
        double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
        double d3 = target.func_174813_aQ().field_72338_b + (double)(target.field_70131_O / 2.0f) - (0.5 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
        this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0f, 1.0f);
        d3 = target.func_174813_aQ().field_72338_b + (double)(target.field_70131_O / 4.0f) - (1.0 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        EntityProjectileHomming entitylargefireball = new EntityProjectileHomming(this.field_70170_p, (EntityLivingBase)this, (Entity)target, SRPAttributes.ORONCO_ATTACK_DAMAGE);
        entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
        entitylargefireball.field_70163_u = this.field_70163_u + (double)this.func_70047_e() - 0.2;
        entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
        this.field_70170_p.func_72838_d((Entity)entitylargefireball);
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, entityIn.field_70165_t + 1.0, entityIn.field_70163_u + 1.0, entityIn.field_70161_v + 1.0).func_72314_b(5.0, 2.0, 5.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (moblist.size() > 4) {
            axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b(5.0, 3.0, 5.0);
            moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            float luck = (float)(SRPAttributes.TERLA_ATTACK_DAMAGE * 2.0);
            for (EntityLivingBase mob : moblist) {
                if (mob == null || mob instanceof EntityParasiteBase || mob == this) continue;
                EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, 0.0f, (EntityLivingBase)this, luck, false, 3.0f);
                this.field_70170_p.func_72838_d((Entity)damage);
            }
            return true;
        }
        for (EntityLivingBase mob : moblist) {
            if (mob == null || mob instanceof EntityParasiteBase) continue;
            this.func_70652_k((Entity)mob);
        }
        return !moblist.isEmpty();
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        boolean flag = super.func_70097_a(source, amount);
        if (flag && source.func_76346_g() instanceof EntityPlayerMP) {
            this.bossInfo.func_186760_a((EntityPlayerMP)source.func_76346_g());
        }
        return flag;
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        boolean flag = this.func_70097_a(source, amount);
        return flag;
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    public int func_184649_cE() {
        return 3;
    }

    @Override
    public boolean func_70687_e(PotionEffect potioneffectIn) {
        return potioneffectIn.func_188419_a() == MobEffects.field_76436_u ? false : super.func_70687_e(potioneffectIn);
    }

    public float func_70047_e() {
        return 4.7f;
    }

    protected SoundEvent func_184639_G() {
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.MOBSILENCE;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    protected float func_70599_aP() {
        return 5.0f;
    }

    @Override
    public void func_70106_y() {
        if (this.head != null) {
            this.field_70170_p.func_72973_f((Entity)this.head);
        }
        if (this.middle != null) {
            this.field_70170_p.func_72973_f((Entity)this.middle);
        }
        super.func_70106_y();
    }

    public void func_184724_a(boolean swingingArms) {
    }
}

