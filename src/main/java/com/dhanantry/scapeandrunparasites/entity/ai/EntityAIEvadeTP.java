/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIEvadeTP
extends EntityAIBase {
    private final EntityParasiteBase parent;
    private int eCooldown;
    private int cooldown;
    private int blockDistance;
    private int dash;
    private int currentDash;
    private int maxDis;
    private double targetIniDis;
    private double targetIniDisSegment;

    public EntityAIEvadeTP(EntityParasiteBase in, int cooldown, int distance) {
        this.parent = in;
        this.eCooldown = cooldown;
        this.cooldown = cooldown + 1;
        this.blockDistance = distance * distance;
        this.dash = 3;
        this.maxDis = 225;
    }

    public EntityAIEvadeTP(EntityParasiteBase in, int cooldown, int distance, int jumps, int maxD) {
        this(in, cooldown, distance);
        this.dash = jumps;
        this.maxDis = maxD * maxD;
    }

    public boolean func_75250_a() {
        return this.parent.func_70638_az() != null && this.parent.getParasiteStatus() > 0 && this.parent.getParasiteStatus() < 3 && this.parent.field_70122_E;
    }

    public void func_75251_c() {
        this.cooldown = 0;
        this.parent.field_70702_br = 0.0f;
    }

    public void func_75246_d() {
        EntityLivingBase target = this.parent.func_70638_az();
        if (target != null) {
            if (this.parent.func_70068_e((Entity)target) > (double)this.blockDistance && this.parent.func_70685_l((Entity)target) && this.parent.func_70068_e((Entity)target) < (double)this.maxDis && this.cooldown < this.eCooldown) {
                ++this.cooldown;
                if (this.cooldown >= this.eCooldown) {
                    this.targetIniDis = this.parent.func_70068_e((Entity)target);
                    this.targetIniDisSegment = this.targetIniDis / (double)(this.dash + 1);
                }
            }
            if (this.cooldown >= this.eCooldown) {
                this.parent.particleStatus((byte)10);
                Random rand = new Random();
                double x = target.field_70165_t;
                double y = target.field_70163_u;
                double z = target.field_70161_v;
                this.parent.func_70661_as().func_75499_g();
                this.func_75251_c();
            }
        }
    }

    protected boolean teleportRandomly() {
        double d0 = this.parent.field_70165_t + (this.parent.field_70170_p.field_73012_v.nextDouble() - 0.5) * 64.0;
        double d1 = this.parent.field_70163_u + (double)(this.parent.field_70170_p.field_73012_v.nextInt(64) - 32);
        double d2 = this.parent.field_70161_v + (this.parent.field_70170_p.field_73012_v.nextDouble() - 0.5) * 64.0;
        if (this.parent.func_70638_az() != null && this.parent.func_70638_az().func_70011_f(d0, d1, d2) < 10.0) {
            return false;
        }
        return this.teleportTo(d0, d1, d2);
    }

    private boolean teleportTo(double x, double y, double z) {
        boolean flag = this.parent.func_184595_k(x, y, z);
        if (flag) {
            this.parent.field_70170_p.func_184148_a((EntityPlayer)null, this.parent.field_70169_q, this.parent.field_70167_r, this.parent.field_70166_s, SRPSounds.INFECTEDENDERMAN_PORTAL, this.parent.func_184176_by(), 1.0f, 1.0f);
            this.parent.func_184185_a(SRPSounds.INFECTEDENDERMAN_PORTAL, 1.0f, 1.0f);
        }
        return flag;
    }
}

