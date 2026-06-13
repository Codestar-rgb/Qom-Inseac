/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityParasiticScent;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCrude;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHeed
extends EntityPCrude
implements EntityBodyParts {
    private EntityBody head;
    private int scentCool;
    private int border;
    private boolean skillThrow;

    public EntityHeed(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.9f, 1.9f);
        this.field_70138_W = 1.0f;
        this.field_70158_ak = true;
        this.type = (byte)31;
        this.skillThrow = false;
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, !SRPConfigSystems.useOneMind, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, SRPConfig.primitiveSneakPen, SRPConfig.primitiveInviPen));
        }
        this.head = new EntityBody(this, 1.8f, 1.8f, 1.0f, 2.1f, 0.3f, 1, 1, false, 0.2f);
        this.scentCool = 1000;
        this.attackSpeedT = 10;
    }

    @Override
    public int getParasiteIDRegister() {
        return 63;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.095));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 200, 20, false, 1));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 2, 16));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.HEED_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.HEED_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.HEED_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.HEED_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        --this.scentCool;
        if (this.scentCool < 0 && this.srpTicks == 10 && SRPConfigSystems.useScent && this.getLevelCreated() >= SRPConfigSystems.deveScentUse) {
            List serverList = this.field_70170_p.field_72996_f;
            int count = 0;
            for (int x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityParasiticScent)) continue;
                ++count;
            }
            if (count > SRPConfigSystems.scentCap) {
                return;
            }
            if (this.func_70638_az() != null) {
                EntityParasiticScent sss = new EntityParasiticScent(this.field_70170_p);
                sss.func_82149_j((Entity)this.func_70638_az());
                sss.setTargetToKill(this.func_70638_az(), false);
                sss.setDieToE(true);
                sss.setCanFollow(true);
                this.field_70170_p.func_72838_d((Entity)sss);
                this.scentCool = 1000;
            }
        }
        this.head.func_70071_h_();
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        if (this.field_70146_Z.nextBoolean()) {
            SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)this, 80, 0);
        }
        return this.func_70097_a(source, amount * 3.0f);
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    @Override
    public void func_70106_y() {
        if (this.head != null) {
            this.field_70170_p.func_72973_f((Entity)this.head);
        }
        super.func_70106_y();
    }

    public float func_70047_e() {
        return 1.5f;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        super.func_70074_a(entityLivingIn);
        this.particleStatus((byte)5);
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillThrow;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillThrow = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.throwBlock();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void throwBlock() {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(1.5);
        List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        for (EntityParasiteBase entityIn : moblist) {
            if (entityIn == this || !SRPConfigSystems.rageEnable) continue;
            entityIn.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 1200, 0, false, false));
        }
        this.skillThrow = true;
        this.setParasiteStatus(0);
        this.border = 0;
    }
}

