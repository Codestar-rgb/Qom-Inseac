/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.deterrent;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityLeemB
extends EntityPStationary {
    public EntityLeemB(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.4f, 1.4f);
        this.field_70728_aV = 0;
        this.type = (byte)40;
        this.MiniDamage = SRPConfig.turretMinDamage;
        this.valueEvDeath = 0;
    }

    @Override
    public int getParasiteIDRegister() {
        return 314;
    }

    protected void func_184651_r() {
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(10.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.srpTicks == 5) {
            this.func_184596_c(SRPPotions.PIVOT_E);
        }
    }

    @Override
    public int hasResistance(String damage, byte type) {
        return 0;
    }

    public float func_70047_e() {
        return 1.8f;
    }
}

