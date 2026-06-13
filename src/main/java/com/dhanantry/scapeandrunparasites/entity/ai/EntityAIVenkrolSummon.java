/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.AxisAlignedBB
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationaryArchitect;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIVenkrolSummon
extends EntityAIBase {
    private final EntityPStationaryArchitect parentEntity;
    private int attackTimer = 0;
    private int limit = 0;
    private int sLimit;
    private int sCooldown;
    private int sStage;
    private boolean locked;
    private boolean counted;
    private boolean flag;
    private int extraV;
    private double extraM;
    private int totalV;
    private double totalM;

    public EntityAIVenkrolSummon(EntityPStationaryArchitect venkrol, int limit, int cooldown, int stage, int CAV, double CAM) {
        this.parentEntity = venkrol;
        this.sCooldown = cooldown;
        this.sLimit = limit;
        this.sStage = stage;
        this.locked = false;
        this.flag = false;
        this.counted = false;
        if (!SRPConfigSystems.useEvolution && !SRPConfigSystems.rsIgnoreCooldownAtSpawn) {
            this.attackTimer = -this.sCooldown;
        }
        this.totalV = CAV;
        this.totalM = CAM;
    }

    public void func_75249_e() {
        this.parentEntity.setParasiteStatus(1);
    }

    public boolean func_75250_a() {
        return this.parentEntity.func_70638_az() != null;
    }

    public void func_75251_c() {
        this.limit = 0;
        this.parentEntity.setParasiteStatus(0);
    }

    public void func_75246_d() {
        if (this.locked) {
            --this.attackTimer;
            if (this.attackTimer <= -200) {
                this.locked = false;
                this.attackTimer = -this.sCooldown;
            }
        } else if (this.parentEntity.func_70638_az() == null) {
            if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        } else if (this.parentEntity.func_70638_az().field_70128_L) {
            if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        } else {
            EntityLivingBase target = this.parentEntity.func_70638_az();
            if (this.parentEntity.func_70685_l((Entity)target)) {
                ++this.attackTimer;
                this.parentEntity.field_70170_p.func_72960_a((Entity)this.parentEntity, (byte)12);
                if (this.attackTimer % 20 == 0 && this.attackTimer > 0) {
                    String[] air;
                    String[] ground;
                    EntityPStationaryArchitect parent;
                    if (this.attackTimer == 20) {
                        this.extraV = this.countNeraby(this.parentEntity.func_145782_y());
                        this.extraM = Math.floor((double)this.extraV * this.totalM);
                        this.flag = this.extraV >= this.totalV;
                        this.setNearbyNonConted(this.parentEntity.func_145782_y());
                        parent = this.parentEntity;
                        parent.checkID();
                    }
                    parent = this.parentEntity;
                    parent.checkID();
                    if (parent.getActualParasites() < parent.getTotalParasites() && this.limit < this.sLimit) {
                        if (this.sStage == 4) {
                            if (ParasiteSummon.SummonM(this.parentEntity, SRPAttributes.VENKROLSIV_MOBTABLEG, 12, target.field_70165_t, target.field_70163_u, target.field_70161_v, target, false)) {
                                if (this.flag) {
                                    this.setNerabyLocked(this.parentEntity.func_145782_y());
                                }
                                ++this.limit;
                            }
                        } else {
                            ground = SRPAttributes.VENKROL_MOBTABLEG;
                            air = null;
                            switch (this.sStage) {
                                case 2: {
                                    ground = SRPAttributes.VENKROLSII_MOBTABLEG;
                                    air = SRPAttributes.VENKROLSII_MOBTABLEA;
                                    break;
                                }
                                case 3: {
                                    ground = SRPAttributes.VENKROLSIII_MOBTABLEG;
                                    air = SRPAttributes.VENKROLSIII_MOBTABLEA;
                                }
                            }
                            if (ParasiteEventEntity.spawnBiomassFromBeckon(this.parentEntity, this.sStage, target, true, ground, air)) {
                                if (this.flag) {
                                    this.setNerabyLocked(this.parentEntity.func_145782_y());
                                }
                                ++this.limit;
                                int j = this.parentEntity.getActualT();
                                this.parentEntity.setActualT(++j);
                            }
                        }
                    }
                    if (this.limit >= this.sLimit) {
                        if (this.flag) {
                            if (this.extraM > 0.0 && this.attackTimer > this.sLimit * 20) {
                                if (this.sStage == 4) {
                                    if (ParasiteSummon.SummonM(this.parentEntity, SRPAttributes.VENKROLSIV_MOBTABLEG, 5, this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.func_70638_az(), false)) {
                                        this.extraM -= 1.0;
                                    }
                                } else {
                                    ground = SRPAttributes.VENKROL_MOBTABLEG;
                                    air = null;
                                    switch (this.sStage) {
                                        case 2: {
                                            ground = SRPAttributes.VENKROLSII_MOBTABLEG;
                                            air = SRPAttributes.VENKROLSII_MOBTABLEA;
                                            break;
                                        }
                                        case 3: {
                                            ground = SRPAttributes.VENKROLSIII_MOBTABLEG;
                                            air = SRPAttributes.VENKROLSIII_MOBTABLEA;
                                        }
                                    }
                                    if (ParasiteEventEntity.spawnBiomassFromBeckon(this.parentEntity, this.sStage, target, true, ground, air)) {
                                        this.extraM -= 1.0;
                                    }
                                }
                            } else if (this.extraM <= 0.0) {
                                this.extraM = 0.0;
                                this.flag = false;
                                this.attackTimer = -this.sCooldown;
                                this.setNearbyFree(this.parentEntity.func_145782_y());
                                this.limit = 0;
                            }
                        } else {
                            this.attackTimer = -this.sCooldown;
                            this.setNearbyFree(this.parentEntity.func_145782_y());
                            this.limit = 0;
                        }
                    }
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        }
    }

    public void setNerabyLocked(int id) {
        switch (this.sStage) {
            case 1: {
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblist = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrol.class, axisalignedbb);
                for (EntityVenkrol mob : moblist) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || mob.summonV.getLocker() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setlocker(true);
                    mob.summonV.setNerabyLocked(id);
                }
                break;
            }
            case 2: {
                AxisAlignedBB axisalignedbbsii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSII.class, axisalignedbbsii);
                for (EntityVenkrolSII mob : moblistsii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || mob.summonV.getLocker() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setlocker(true);
                    mob.summonV.setNerabyLocked(id);
                }
                break;
            }
            case 3: {
                AxisAlignedBB axisalignedbbsiii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsiii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSIII.class, axisalignedbbsiii);
                for (EntityVenkrolSIII mob : moblistsiii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || mob.summonV.getLocker() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setlocker(true);
                    mob.summonV.setNerabyLocked(id);
                }
                break;
            }
        }
    }

    public void setNearbyFree(int id) {
        switch (this.sStage) {
            case 1: {
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblist = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrol.class, axisalignedbb);
                for (EntityVenkrol mob : moblist) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.summonV.getLocker() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setlocker(false);
                    mob.summonV.setCooldownZero(-this.sCooldown);
                    mob.summonV.setNearbyFree(id);
                }
                break;
            }
            case 2: {
                AxisAlignedBB axisalignedbbsii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSII.class, axisalignedbbsii);
                for (EntityVenkrolSII mob : moblistsii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.summonV.getLocker() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setlocker(false);
                    mob.summonV.setCooldownZero(-this.sCooldown);
                    mob.summonV.setNearbyFree(id);
                }
                break;
            }
            case 3: {
                AxisAlignedBB axisalignedbbsiii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsiii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSIII.class, axisalignedbbsiii);
                for (EntityVenkrolSIII mob : moblistsiii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.summonV.getLocker() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setlocker(false);
                    mob.summonV.setCooldownZero(-this.sCooldown);
                    mob.summonV.setNearbyFree(id);
                }
                break;
            }
        }
    }

    public void setCooldownZero(int in) {
        this.attackTimer = in;
    }

    public void setlocker(boolean in) {
        if (in) {
            this.attackTimer = -1;
        }
        this.locked = in;
    }

    public boolean getLocker() {
        return this.locked;
    }

    public int countNeraby(int id) {
        int count = 1;
        switch (this.sStage) {
            case 1: {
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblist = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrol.class, axisalignedbb);
                for (EntityVenkrol mob : moblist) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.func_70685_l((Entity)this.parentEntity) || mob.summonV.getCounted()) continue;
                    mob.summonV.setCounted(true);
                    count += mob.summonV.countNeraby(id);
                }
                break;
            }
            case 2: {
                AxisAlignedBB axisalignedbbsii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSII.class, axisalignedbbsii);
                for (EntityVenkrolSII mob : moblistsii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.func_70685_l((Entity)this.parentEntity) || mob.summonV.getCounted()) continue;
                    mob.summonV.setCounted(true);
                    count += mob.summonV.countNeraby(id);
                }
                break;
            }
            case 3: {
                AxisAlignedBB axisalignedbbsiii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsiii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSIII.class, axisalignedbbsiii);
                for (EntityVenkrolSIII mob : moblistsiii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.func_70685_l((Entity)this.parentEntity) || mob.summonV.getCounted()) continue;
                    mob.summonV.setCounted(true);
                    count += mob.summonV.countNeraby(id);
                }
                break;
            }
        }
        return count;
    }

    public void setNearbyNonConted(int id) {
        switch (this.sStage) {
            case 1: {
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblist = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrol.class, axisalignedbb);
                for (EntityVenkrol mob : moblist) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.summonV.getCounted() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setCounted(false);
                    mob.summonV.setNearbyNonConted(id);
                }
                break;
            }
            case 2: {
                AxisAlignedBB axisalignedbbsii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSII.class, axisalignedbbsii);
                for (EntityVenkrolSII mob : moblistsii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.summonV.getCounted() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setCounted(false);
                    mob.summonV.setNearbyNonConted(id);
                }
                break;
            }
            case 3: {
                AxisAlignedBB axisalignedbbsiii = new AxisAlignedBB(this.parentEntity.field_70165_t, this.parentEntity.field_70163_u, this.parentEntity.field_70161_v, this.parentEntity.field_70165_t + 1.0, this.parentEntity.field_70163_u + 1.0, this.parentEntity.field_70161_v + 1.0).func_186662_g(this.parentEntity.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() + 5.0);
                List moblistsiii = this.parentEntity.field_70170_p.func_72872_a(EntityVenkrolSIII.class, axisalignedbbsiii);
                for (EntityVenkrolSIII mob : moblistsiii) {
                    if (mob.func_145782_y() == id || !mob.func_70089_S() || !mob.summonV.getCounted() || !mob.func_70685_l((Entity)this.parentEntity)) continue;
                    mob.summonV.setCounted(false);
                    mob.summonV.setNearbyNonConted(id);
                }
                break;
            }
        }
    }

    public void setCounted(boolean in) {
        this.counted = in;
    }

    public boolean getCounted() {
        return this.counted;
    }
}

