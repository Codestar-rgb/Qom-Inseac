/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.ancient;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.spawn.ParasiteSummon;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityOroncoTen
extends EntityParasiteBase {
    private int ticksGround;
    private int maxMobs;

    public EntityOroncoTen(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.0f, 0.7f);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70158_ak = true;
        this.canD = SPConfig.ancientdespawn;
        this.killcount = -10.0;
        this.type = (byte)62;
    }

    @Override
    public int getParasiteIDRegister() {
        return 35;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.ORONCO_HEALTH * 0.25);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.ORONCO_ARMOR * 0.25);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70122_E) {
            ++this.ticksGround;
            if (this.ticksGround > 200 && !this.field_70170_p.field_72995_K) {
                if (this.ticksGround % 20 == 0 && this.nearbydangerous() && this.maxMobs < 10) {
                    if (ParasiteSummon.SummonM((EntityLivingBase)this, new String[]{"subspaceparasite:lodo;1;1"}, 2, 3, this.func_70638_az())) {
                        ++this.maxMobs;
                    } else if (this.maxMobs >= 10) {
                        this.func_70106_y();
                    }
                }
            } else if (this.field_70170_p.field_72995_K && this.ticksGround > 200) {
                this.spawnParticles(SPEnumParticle.GCLOUD, 91, 81, 75);
                this.spawnParticles(SPEnumParticle.GCLOUD, 164, 174, 180);
                this.spawnParticles(SPEnumParticle.GCLOUD, 91, 81, 75);
                this.spawnParticles(SPEnumParticle.GCLOUD, 164, 174, 180);
            }
        }
    }

    private boolean nearbydangerous() {
        int k = 0;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(16.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob != this && mob instanceof EntityParasiteBase && mob.func_70089_S()) {
                --k;
                continue;
            }
            if (!mob.func_70089_S()) continue;
            ++k;
        }
        return k > 0;
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 0.5f;
    }
}

