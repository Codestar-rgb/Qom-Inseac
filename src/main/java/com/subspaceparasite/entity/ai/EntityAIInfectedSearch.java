/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityCanMelt;
import com.subspaceparasite.entity.ai.misc.EntityPInfected;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.crude.EntityLesh;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIInfectedSearch
extends EntityAIBase {
    private final EntityParasiteBase parent;
    private World world;
    private int parentID;
    private double moveSpeed;
    private EntityParasiteBase[] array = new EntityParasiteBase[4];
    private int delay;

    public EntityAIInfectedSearch(EntityParasiteBase animal, double speedIn) {
        this.parent = animal;
        this.world = animal.field_70170_p;
        this.moveSpeed = speedIn;
        this.parentID = animal.func_145782_y();
    }

    public boolean func_75250_a() {
        if (this.parent.field_70173_aa % 40 == 0) {
            return false;
        }
        if (this.parent.func_70638_az() != null) {
            return false;
        }
        return this.LeshCount();
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
        if (this.parent.field_70173_aa % 20 != 0 || this.parent.getKillC() < 0.0) {
            return;
        }
        int count = 0;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
        List moblist = this.parent.field_70170_p.func_72872_a(EntityPInfected.class, axisalignedbb);
        List moblist2 = this.parent.field_70170_p.func_72872_a(EntityLesh.class, axisalignedbb);
        for (EntityParasiteBase mob : moblist2) {
            if (mob == null) continue;
            ++count;
        }
        if (count >= 3) {
            EntityCanMelt meh = (EntityCanMelt)((Object)this.parent);
            meh.melt();
            return;
        }
        for (EntityParasiteBase mob : moblist) {
            EntityCanMelt meh;
            if (mob == null || mob == this.parent || !(mob instanceof EntityCanMelt) || !this.parent.func_70685_l((Entity)mob) || mob.func_70638_az() != null || (meh = (EntityCanMelt)((Object)mob)).isMelting()) continue;
            meh.melt();
            if (++count < 3) continue;
            EntityCanMelt moh = (EntityCanMelt)((Object)this.parent);
            moh.melt();
            return;
        }
        EntityCanMelt out = (EntityCanMelt)((Object)this.parent);
        out.melt();
    }

    protected boolean LeshCount() {
        int countL = 0;
        int countI = 0;
        boolean more = false;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
        List moblist = this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        for (EntityParasiteBase mob : moblist) {
            Object meh;
            if (mob == null) continue;
            if (mob instanceof EntityLesh && this.parent.func_70685_l((Entity)mob)) {
                meh = (EntityLesh)mob;
                countL += ((EntityLesh)meh).getTimes();
            }
            if (mob == this.parent || !this.parent.func_70685_l((Entity)mob) || !(mob instanceof EntityCanMelt) || mob.func_70638_az() != null || (meh = (EntityCanMelt)((Object)mob)).isMelting()) continue;
            ++countI;
        }
        if (countL >= 1 && countL <= 3) {
            EntityCanMelt meh = (EntityCanMelt)((Object)this.parent);
            meh.melt();
            return false;
        }
        return countI >= 3;
    }
}

