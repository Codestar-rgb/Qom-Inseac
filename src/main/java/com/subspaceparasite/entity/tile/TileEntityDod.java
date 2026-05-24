/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ITickable
 *  net.minecraft.util.math.AxisAlignedBB
 */
package com.subspaceparasite.entity.tile;

import com.subspaceparasite.entity.ai.misc.EntityPDispatcher;
import com.subspaceparasite.entity.ai.misc.EntityPStationaryArchitect;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDod;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIV;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityDod
extends TileEntity
implements ITickable {
    private int ticksSinceSync;
    private int killC;

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        if (compound.func_150297_b("parasitekillc", 3)) {
            this.killC = compound.func_74762_e("parasitekillc");
        }
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("parasitekillc", this.killC);
        return compound;
    }

    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c() == Blocks.field_150350_a) {
                this.killSelfTile();
                return;
            }
            ++this.ticksSinceSync;
            if (this.ticksSinceSync % 20 != 0) {
                return;
            }
            double coll = 0.0;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)this.field_174879_c.func_177958_n(), (double)this.field_174879_c.func_177956_o(), (double)this.field_174879_c.func_177952_p(), (double)(this.field_174879_c.func_177958_n() + 1), (double)(this.field_174879_c.func_177956_o() + 1), (double)(this.field_174879_c.func_177952_p() + 1)).func_186662_g(16.0);
            List moblist = this.field_145850_b.func_72872_a(EntityParasiteBase.class, axisalignedbb);
            for (Object mob : moblist) {
                if (!(((EntityParasiteBase)mob).getKillC() > 1.0)) continue;
                coll += ((EntityParasiteBase)mob).getKillC();
                ((EntityParasiteBase)mob).setKillC(0.0);
            }
            if (coll != 0.0) {
                this.killC += (int)coll;
            }
            if (this.killC > SPConfig.reinforcerNidusValue) {
                assert (Blocks.field_150350_a != null);
                int ccc = 0;
                axisalignedbb = new AxisAlignedBB((double)this.field_174879_c.func_177958_n(), (double)this.field_174879_c.func_177956_o(), (double)this.field_174879_c.func_177952_p(), (double)(this.field_174879_c.func_177958_n() + 1), (double)(this.field_174879_c.func_177956_o() + 1), (double)(this.field_174879_c.func_177952_p() + 1)).func_186662_g(SPConfig.turretFollow);
                moblist = this.field_145850_b.func_72872_a(EntityPStationaryArchitect.class, axisalignedbb);
                for (EntityParasiteBase mob : moblist) {
                    if (!(mob instanceof EntityDod) && !(mob instanceof EntityDodSII) && !(mob instanceof EntityDodSIII) && !(mob instanceof EntityDodSIV)) continue;
                    ++ccc;
                }
                if (ccc == 0) {
                    List serverList = this.field_145850_b.field_72996_f;
                    int count = 0;
                    for (int x = 0; x < serverList.size(); ++x) {
                        if (!(serverList.get(x) instanceof EntityPDispatcher) || ++count <= SPConfig.nexusDodCap && !(this.getDistanceSq((Entity)serverList.get(x)) < (double)(SPConfig.nexusDodDis * SPConfig.nexusDodDis))) continue;
                        if (SPConfigSystems.useEvolution) {
                            SPSaveData data = SPSaveData.get(this.field_145850_b, 49);
                            data.setTotalKills(this.field_145850_b.field_73011_w.getDimension(), this.killC * SPConfig.reinforcerNidusMult, true, this.field_145850_b, true, 45);
                        }
                        return;
                    }
                    if (SPConfigSystems.useEvolution) {
                        SPSaveData data = SPSaveData.get(this.field_145850_b, 47);
                        if (ParasiteEventEntity.getRSchance(this.field_145850_b) == 0.0) {
                            data.setTotalKills(this.field_145850_b.field_73011_w.getDimension(), this.killC * SPConfig.reinforcerNidusMult, true, this.field_145850_b, true, 46);
                            this.field_145850_b.func_175656_a(this.func_174877_v(), Blocks.field_150350_a.func_176223_P());
                            this.killSelfTile();
                            return;
                        }
                    }
                    EntityDod dod = new EntityDod(this.field_145850_b);
                    dod.func_70107_b((double)this.field_174879_c.func_177958_n() + 0.5, this.field_174879_c.func_177956_o() + 1, (double)this.field_174879_c.func_177952_p() + 0.5);
                    if (!dod.field_70170_p.func_184144_a((Entity)dod, dod.func_174813_aQ()).isEmpty()) {
                        dod.func_70106_y();
                    } else {
                        this.field_145850_b.func_72838_d((Entity)dod);
                        dod.particleStatus((byte)7);
                        if (SPConfigSystems.rsSounds) {
                            if (SPConfigSystems.disloGrowlNoise) {
                                if (SPSaveData.get(this.field_145850_b, 48).getCurrentCode(this.field_145850_b.field_73011_w.getDimension(), 15) == 0) {
                                    dod.func_184185_a(SPSounds.DODSI, 4.0f, 1.0f);
                                }
                            } else {
                                dod.func_184185_a(SPSounds.DODSI, 4.0f, 1.0f);
                            }
                        }
                    }
                }
                this.field_145850_b.func_175656_a(this.func_174877_v(), Blocks.field_150350_a.func_176223_P());
                this.killSelfTile();
                return;
            }
        }
    }

    private void killSelfTile() {
        if (this.field_145850_b == null) {
            return;
        }
        this.func_145843_s();
        this.field_145850_b.func_175713_t(this.field_174879_c);
    }

    public double getDistanceSq(Entity entityIn) {
        double d0 = (double)this.field_174879_c.func_177958_n() - entityIn.field_70165_t;
        double d1 = (double)this.field_174879_c.func_177956_o() - entityIn.field_70163_u;
        double d2 = (double)this.field_174879_c.func_177952_p() - entityIn.field_70161_v;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }
}

