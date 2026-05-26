/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.deterrent.nexus;

import com.subspaceparasite.entity.ai.EntityAIBlockInfest;
import com.subspaceparasite.entity.ai.misc.EntityPBeckon;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfigMobs;
import java.util.Arrays;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.world.World;

public class EntityVenkrolSV
extends EntityPBeckon {
    private EntityAIBlockInfest infestation = new EntityAIBlockInfest(this, 2);

    public EntityVenkrolSV(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.0f, 8.8f);
        this.field_70158_ak = true;
        this.totalP = SPConfigMobs.venkrolTotalActiveMobs;
        this.mobID = new int[this.totalP + SPConfigMobs.venkrollimit];
        this.mobPT = new int[this.totalP + SPConfigMobs.venkrollimit];
        this.stage = (byte)3;
        this.field_70728_aV = SPAttributes.XP_ADAPTED * 2;
        if (SPAttributes.rsBlockI) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.infestation);
        }
        Arrays.fill(this.mobID, -777);
        this.setBODY(1.0f);
    }

    @Override
    public int getParasiteIDRegister() {
        return 42;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.VENKROLSII_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.VENKROLSII_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.VENKROLSII_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(24.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.getParasiteStatus() == 0) {
            this.setBODY(0.04f);
        } else {
            this.setBODY(-0.04f);
        }
    }

    public float func_70047_e() {
        return 8.5f;
    }

    public void setBODY(float in) {
        if ((in += this.getBODY()) > 0.5f) {
            in = 0.5f;
        }
        if (in < 0.0f) {
            in = 0.0f;
        }
        this.body = in;
    }

    @Override
    public float getBombDamage() {
        return (float)SPAttributes.VENKROLSIV_ATTACK_DAMAGE;
    }
}

