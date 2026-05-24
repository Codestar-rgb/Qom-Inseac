/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITarget
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityCanHaveBodies;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.entity.ai.misc.EntityPDispatcher;
import com.subspaceparasite.entity.ai.misc.EntityPPreeminent;
import com.subspaceparasite.entity.ai.misc.EntityPStationary;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.deterrent.EntityDodT;
import com.subspaceparasite.entity.monster.deterrent.EntityNak;
import com.subspaceparasite.entity.monster.deterrent.EntityUnvo;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class EntityAIDodAttack
extends EntityAIBase {
    private final EntityPDispatcher parent;
    private byte stage;
    private double tickss;
    private byte maxID;
    private int maxDodT;
    private ArrayList<Integer> resistanceI = new ArrayList();
    float maxHardness;
    private int cooldownNak;

    public EntityAIDodAttack(EntityPDispatcher dod, int CURRENTstage, byte limit, float hardness) {
        this.parent = dod;
        this.tickss = 20.0;
        this.stage = (byte)CURRENTstage;
        this.maxID = limit;
        this.maxDodT = CURRENTstage * 3;
        this.maxHardness = hardness;
        this.cooldownNak = 0;
    }

    public boolean func_75250_a() {
        this.tickss -= 1.0;
        --this.cooldownNak;
        if (this.tickss < 0.0) {
            this.tickss = 80.0;
            if (this.parent.func_70638_az() != null) {
                this.tickss = 10.0;
            }
            return true;
        }
        return false;
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
        double follow = 0.0;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
        EntityLivingBase target = this.parent.func_70638_az();
        this.unhide(axisalignedbb);
        this.storePara(axisalignedbb);
        if (target == null) {
            this.findT(follow, axisalignedbb);
        } else {
            if (!this.parent.getMobList().isEmpty() && this.parent.field_70170_p.field_73012_v.nextInt(3) != 0) {
                int sel = this.parent.field_70170_p.field_73012_v.nextInt(this.stage * 2);
                if (sel < this.parent.getMobList().size()) {
                    for (int i = 0; i <= sel; ++i) {
                        this.summonTentacles(target, follow, axisalignedbb, true);
                    }
                }
            } else {
                switch (this.stage) {
                    case 1: {
                        this.parent.checkID();
                        if (this.parent.getActualParasites() < this.parent.getTotalParasites() && this.checkNak() && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                            // empty if block
                        }
                        break;
                    }
                    case 2: {
                        this.parent.checkID();
                        if (this.parent.getActualParasites() < this.parent.getTotalParasites() && this.checkNak() && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                            // empty if block
                        }
                        break;
                    }
                    case 3: {
                        if (this.parent.field_70170_p.field_73012_v.nextBoolean()) {
                            this.parent.checkID();
                            if (this.parent.getActualParasites() < this.parent.getTotalParasites() && this.spawnDet(target, new EntityUnvo(this.parent.field_70170_p), 6, 4, true)) {
                                // empty if block
                            }
                        } else {
                            this.parent.checkID();
                            if (this.parent.getActualParasites() < this.parent.getTotalParasites() && this.checkNak() && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                                // empty if block
                            }
                        }
                        break;
                    }
                    case 4: {
                        if (this.parent.field_70170_p.field_73012_v.nextBoolean()) {
                            this.parent.checkID();
                            if (this.parent.getActualParasites() < this.parent.getTotalParasites() && this.spawnDet(target, new EntityUnvo(this.parent.field_70170_p), 6, 4, true)) {
                                // empty if block
                            }
                            break;
                        }
                        this.parent.checkID();
                        if (this.parent.getActualParasites() >= this.parent.getTotalParasites() || !this.checkNak() || this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                            // empty if block
                        } else {
                            break;
                        }
                    }
                }
            }
            if (!target.func_70089_S()) {
                this.parent.func_70624_b(null);
            }
        }
    }

    private void findT(double follow, AxisAlignedBB axisalignedbb) {
        List moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob == null) continue;
            if (mob instanceof EntityPlayer) {
                if (!this.canAttackPlayer((EntityPlayer)mob)) continue;
                this.parent.func_70624_b(mob);
                return;
            }
            if (!(mob instanceof EntityLiving) || ParasiteEventEntity.checkEntity((EntityLivingBase)((EntityLiving)mob), SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite) || mob == this.parent || !this.canTargetEntity(mob) || !(mob.func_70068_e((Entity)this.parent) < 1024.0) || !mob.func_70089_S() || !this.parent.func_70686_a(mob.getClass())) continue;
            this.parent.func_70624_b(mob);
            return;
        }
    }

    private boolean canTargetEntity(EntityLivingBase in) {
        return !(in instanceof EntityParasiteBase) && !(in instanceof EntityAnimal) && !(in instanceof EntityCreeper) && !(in instanceof EntityWaterMob);
    }

    private boolean canAttackPlayer(EntityPlayer in) {
        if (in.field_71075_bZ.field_75102_a) {
            return false;
        }
        return EntityAITarget.func_179445_a((EntityLiving)this.parent, (EntityLivingBase)in, (boolean)false, (boolean)true);
    }

    private void summonTentacles(EntityLivingBase in, double follow, AxisAlignedBB axisalignedbb, boolean dod) {
        if (this.parent.field_70170_p.field_73012_v.nextInt(2) != 0) {
            return;
        }
        this.parent.checkID();
        this.checkdodts();
        if (this.resistanceI.size() >= this.maxDodT && dod) {
            return;
        }
        if (this.parent.getActualParasites() >= this.parent.getTotalParasites() && this.parent.getMobList().size() <= 0 || this.spawnDet(in, new EntityDodT(this.parent.field_70170_p), 5, 3, dod)) {
            // empty if block
        }
    }

    private void checkdodts() {
        for (int i = 0; i < this.resistanceI.size(); ++i) {
            Entity flag = this.parent.field_70170_p.func_73045_a(this.resistanceI.get(i).intValue());
            if (flag == null) {
                this.resistanceI.remove(i);
                i = 0;
                continue;
            }
            if (flag instanceof EntityDodT) {
                EntityDodT flagg = (EntityDodT)flag;
                if (flagg.func_70089_S()) continue;
                this.resistanceI.remove(i);
                i = 0;
                continue;
            }
            this.resistanceI.remove(i);
            i = 0;
        }
    }

    private boolean spawnDet(EntityLivingBase in, EntityPStationary entityout, int max, int mini, boolean addId) {
        boolean tele;
        int x;
        int count;
        List serverList;
        boolean flagN;
        boolean bl = flagN = entityout.getParasiteIDRegister() == 72;
        if (flagN) {
            serverList = this.parent.field_70170_p.field_72996_f;
            count = 0;
            for (x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityNak)) continue;
                ++count;
            }
            if (count >= 7) {
                entityout.func_70106_y();
                return false;
            }
            if (this.cooldownNak > 0) {
                entityout.func_70106_y();
                return false;
            }
        }
        if (entityout.getParasiteIDRegister() == 30) {
            serverList = this.parent.field_70170_p.field_72996_f;
            count = 0;
            for (x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityUnvo)) continue;
                ++count;
            }
            if (count >= 7) {
                return false;
            }
        }
        boolean bl2 = tele = entityout.getParasiteIDRegister() == 74;
        if (tele) {
            List serverList2 = this.parent.field_70170_p.field_72996_f;
            int count2 = 0;
            for (int x2 = 0; x2 < serverList2.size(); ++x2) {
                if (!(serverList2.get(x2) instanceof EntityParasiteBase)) continue;
                ++count2;
            }
            if (count2 >= SPConfig.worldMobCap) {
                return false;
            }
        }
        if (this.parent.getMobList().size() <= 0 && tele) {
            entityout.func_70106_y();
            return false;
        }
        Random rand = new Random();
        double x3 = in.field_70165_t;
        double y = in.field_70163_u;
        double z = in.field_70161_v;
        double randomx = rand.nextInt(max) + mini;
        double randomz = rand.nextInt(max) + mini;
        double negative = rand.nextInt(2);
        if (negative == 0.0) {
            randomx *= -1.0;
        }
        if ((negative = (double)rand.nextInt(2)) == 0.0) {
            randomz *= -1.0;
        }
        int limit = 0;
        boolean flag = true;
        while (flag) {
            if (limit >= 5) {
                entityout.func_70106_y();
                return false;
            }
            BlockPos pos = new BlockPos(x3 + randomx, y, z + randomz);
            if ((pos = ParasiteEventEntity.getFloor(this.parent.field_70170_p, pos, 5)) != null) {
                IBlockState state = this.parent.field_70170_p.func_180495_p(pos);
                if (this.parent.field_70170_p.func_180495_p(pos.func_177977_b()).func_185917_h()) {
                    float bHard = 0.0f;
                    IBlockState state2 = this.parent.field_70170_p.func_180495_p(pos.func_177977_b());
                    float atm = state2.func_185887_b(this.parent.field_70170_p, pos.func_177977_b());
                    if (atm <= 0.0f) {
                        randomx = rand.nextInt(max) + mini;
                        randomz = rand.nextInt(max) + mini;
                        negative = rand.nextInt(2);
                        if (negative == 0.0) {
                            randomx *= -1.0;
                        }
                        if ((negative = (double)rand.nextInt(2)) == 0.0) {
                            randomz *= -1.0;
                        }
                        ++limit;
                        continue;
                    }
                    bHard += atm;
                    state2 = this.parent.field_70170_p.func_180495_p(pos.func_177979_c(2));
                    atm = state2.func_185887_b(this.parent.field_70170_p, pos.func_177979_c(2));
                    if (atm <= 0.0f) {
                        randomx = rand.nextInt(max) + mini;
                        randomz = rand.nextInt(max) + mini;
                        negative = rand.nextInt(2);
                        if (negative == 0.0) {
                            randomx *= -1.0;
                        }
                        if ((negative = (double)rand.nextInt(2)) == 0.0) {
                            randomz *= -1.0;
                        }
                        ++limit;
                        continue;
                    }
                    bHard += atm;
                    state2 = this.parent.field_70170_p.func_180495_p(pos.func_177979_c(3));
                    atm = state2.func_185887_b(this.parent.field_70170_p, pos.func_177979_c(3));
                    if (atm <= 0.0f) {
                        randomx = rand.nextInt(max) + mini;
                        randomz = rand.nextInt(max) + mini;
                        negative = rand.nextInt(2);
                        if (negative == 0.0) {
                            randomx *= -1.0;
                        }
                        if ((negative = (double)rand.nextInt(2)) == 0.0) {
                            randomz *= -1.0;
                        }
                        ++limit;
                        continue;
                    }
                    if ((bHard += atm) >= this.maxHardness) {
                        randomx = rand.nextInt(max) + mini;
                        randomz = rand.nextInt(max) + mini;
                        negative = rand.nextInt(2);
                        if (negative == 0.0) {
                            randomx *= -1.0;
                        }
                        if ((negative = (double)rand.nextInt(2)) == 0.0) {
                            randomz *= -1.0;
                        }
                        ++limit;
                        continue;
                    }
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_186662_g(2.0);
                    List moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    if (moblist.isEmpty()) {
                        entityout.func_70012_b(x3 + randomx, y, z + randomz, this.parent.field_70177_z, this.parent.field_70125_A);
                        if (!this.parent.field_70170_p.func_184144_a((Entity)entityout, entityout.func_174813_aQ()).isEmpty()) {
                            randomx = rand.nextInt(max) + mini;
                            randomz = rand.nextInt(max) + mini;
                            negative = rand.nextInt(2);
                            if (negative == 0.0) {
                                randomx *= -1.0;
                            }
                            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                                randomz *= -1.0;
                            }
                            ++limit;
                            continue;
                        }
                        entityout.func_180482_a(this.parent.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
                        entityout.func_70624_b(in);
                        if (tele) {
                            this.resistanceI.add(entityout.func_145782_y());
                            String name = this.parent.getParasiteStored();
                            if (name == null) {
                                entityout.func_70106_y();
                                return false;
                            }
                            ((EntityDodT)entityout).setTele(name, this.stage);
                        } else {
                            this.parent.setActualParasites(1);
                            this.parent.addID(entityout.func_145782_y(), 1);
                        }
                        entityout.setPeek(true);
                        entityout.setBuried();
                        if (this.parent.func_70027_ad()) {
                            entityout.func_70015_d(8);
                        }
                        this.parent.field_70170_p.func_72838_d((Entity)entityout);
                        this.tickss = 10.0;
                        this.parent.field_70170_p.func_72960_a((Entity)entityout, (byte)50);
                        if (entityout.getParasiteIDRegister() == 74) {
                            ((EntityDodT)entityout).setFather(this.parent);
                        } else if (flagN) {
                            ((EntityNak)entityout).setFather(this.parent);
                            this.cooldownNak = 40;
                        }
                        flag = false;
                        return true;
                    }
                }
            }
            randomx = rand.nextInt(max) + mini;
            randomz = rand.nextInt(max) + mini;
            negative = rand.nextInt(2);
            if (negative == 0.0) {
                randomx *= -1.0;
            }
            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                randomz *= -1.0;
            }
            ++limit;
        }
        entityout.func_70106_y();
        return false;
    }

    private void unhide(AxisAlignedBB axisalignedbb) {
        List moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            int key;
            if (mob == this.parent || mob instanceof EntityParasiteBase || !mob.field_70122_E || mob.func_70090_H() || mob instanceof EntityPlayer) continue;
            if (!(mob instanceof EntityWaterMob) && this.parent.field_70170_p.field_73012_v.nextInt(1) == 0 && moblist.size() < this.parent.getMobList().size()) {
                this.summonTentacles(mob, 0.0, axisalignedbb, true);
            }
            if (!mob.func_70644_a(SPPotions.COTH_E)) continue;
            NBTTagCompound tags = mob.getEntityData();
            mob.func_70690_d(new PotionEffect(SPPotions.COTH_E, 600, 5));
            if (!tags.func_74764_b("srpcothimmunity") || (key = tags.func_74762_e("srpcothimmunity")) < 1) continue;
            tags.func_74768_a("srpcothimmunity", key += 10);
        }
    }

    private void storePara(AxisAlignedBB axisalignedbb) {
        List moblist = this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        for (EntityParasiteBase mob : moblist) {
            if (!mob.func_70089_S() || mob instanceof EntityPStationary || mob instanceof EntityPPreeminent || mob instanceof EntityPCosmical || mob instanceof EntityCanHaveBodies || mob.func_70638_az() != null || !mob.field_70122_E || mob.field_70173_aa <= 100 || mob.canBeStored() != 0) continue;
            this.parent.storeParasite(mob);
        }
    }

    private boolean checkNak() {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_72314_b(7.0, 5.0, 7.0);
        List moblist = this.parent.field_70170_p.func_72872_a(EntityNak.class, axisalignedbb);
        return moblist.isEmpty();
    }
}

