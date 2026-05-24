/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.AxisAlignedBB
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIGetFollowers
extends EntityAIBase {
    private final EntityParasiteBase parent;
    private int version;
    private int searchRange;

    public EntityAIGetFollowers(EntityParasiteBase in, int mobVersion, int range) {
        this.parent = in;
        this.version = mobVersion;
        this.searchRange = range;
    }

    public boolean func_75250_a() {
        return this.parent.field_70173_aa % 20 == 0 && this.parent.getParasiteFollowing() == null && this.parent.func_70638_az() == null;
    }

    public void func_75246_d() {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_72314_b((double)this.searchRange, 2.0, (double)this.searchRange);
        List moblist = this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        block0 : switch (this.version) {
            case 1: {
                for (EntityParasiteBase mob : moblist) {
                    if (mob == this.parent || !this.parent.func_70685_l((Entity)mob) || !mob.func_70089_S() || mob.getParasiteType() >= 31) continue;
                    if (mob.getParasiteFollowing() == null) {
                        mob.setParasiteToFollow(this.parent);
                        break block0;
                    }
                    if (mob.getParasiteFollowing().getParasiteType() > 10) continue;
                    mob.setParasiteToFollow(this.parent);
                    break block0;
                }
                break;
            }
            case 2: {
                for (EntityParasiteBase mob : moblist) {
                    if (mob == this.parent || !this.parent.func_70685_l((Entity)mob) || !mob.func_70089_S() || mob.getParasiteType() >= 41) continue;
                    if (mob.getParasiteFollowing() == null) {
                        mob.setParasiteToFollow(this.parent);
                        break block0;
                    }
                    if (mob.getParasiteFollowing().getParasiteType() > 30) continue;
                    mob.setParasiteToFollow(this.parent);
                    break block0;
                }
                break;
            }
            case 3: {
                for (EntityParasiteBase mob : moblist) {
                    if (mob == this.parent || !this.parent.func_70685_l((Entity)mob) || !mob.func_70089_S() || mob.getParasiteType() >= 41) continue;
                    if (mob.getParasiteFollowing() == null) {
                        mob.setParasiteToFollow(this.parent);
                        break block0;
                    }
                    if (mob.getParasiteFollowing().getParasiteType() > 40) continue;
                    mob.setParasiteToFollow(this.parent);
                    break block0;
                }
                break;
            }
            case 4: {
                for (EntityParasiteBase mob : moblist) {
                    if (mob == this.parent || !this.parent.func_70685_l((Entity)mob) || !mob.func_70089_S() || mob.getParasiteType() >= 61) continue;
                    if (mob.getParasiteFollowing() == null) {
                        mob.setParasiteToFollow(this.parent);
                        break block0;
                    }
                    if (mob.getParasiteFollowing().getParasiteType() > 60) continue;
                    mob.setParasiteToFollow(this.parent);
                    break block0;
                }
                break;
            }
        }
    }
}

