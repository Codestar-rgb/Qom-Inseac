/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.AxisAlignedBB
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIGiveEffectsArea
extends EntityAIBase {
    EntityParasiteBase parent;
    private int attackTimer = 0;
    private int bRange;
    private int bCooldown;
    private String[] effects;

    public EntityAIGiveEffectsArea(EntityParasiteBase parent, int cooldown, int range, String[] in) {
        this.parent = parent;
        this.bRange = range;
        this.bCooldown = cooldown;
        this.effects = in;
    }

    public boolean func_75250_a() {
        return true;
    }

    public void func_75246_d() {
        ++this.attackTimer;
        if (this.attackTimer >= 60) {
            this.BuffParasites();
            this.attackTimer -= this.bCooldown;
        }
    }

    protected void BuffParasites() {
        String[] here = new String[3];
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.func_180425_c()).func_186662_g((double)this.bRange);
        List moblist = this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        for (String i : this.effects) {
            here = i.split(";");
            Potion potionE = Potion.func_180142_b((String)here[2]);
            if (potionE == null) continue;
            int duration = Integer.parseInt(here[0]) * 20;
            int amp = Integer.parseInt(here[1]);
            for (EntityParasiteBase mob : moblist) {
                if (mob == this.parent) continue;
                mob.func_70690_d(new PotionEffect(potionE, duration, amp, false, false));
            }
        }
    }
}

