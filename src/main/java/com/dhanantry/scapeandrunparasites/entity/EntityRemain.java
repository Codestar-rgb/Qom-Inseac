/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRemain
extends Entity {
    private int plus;
    private int count;
    private int goal;
    private boolean active;
    private String parasite;
    private float health;
    private byte skin;

    public EntityRemain(World in) {
        super(in);
        this.func_70105_a(0.5f, 0.5f);
        this.active = false;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 20 == 0) {
                if (!(this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockGore) || this.parasite == null) {
                    this.func_70106_y();
                }
                if (!this.getActive() && SRPConfigSystems.disloGiveBodies && SRPSaveData.get(this.field_70170_p, 50).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 20) >= 1) {
                    this.setPlus(SRPConfigMobs.canraadaptedremainplus);
                    this.setHealth(SRPConfigMobs.canraadaptedremainhealth);
                }
            }
            if (this.active) {
                this.count += this.plus;
                if (this.count > this.goal) {
                    List serverList = this.field_70170_p.field_72996_f;
                    int count = 0;
                    for (int x = 0; x < serverList.size(); ++x) {
                        if (serverList.get(x) instanceof EntityParasiteBase) {
                            ++count;
                        }
                        if (count <= SRPConfig.worldMobCap) continue;
                        this.count = 0;
                        return;
                    }
                    EntityParasiteBase out = (EntityParasiteBase)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(this.parasite), (World)this.field_70170_p);
                    if (out == null) {
                        return;
                    }
                    if (!out.field_70170_p.func_184144_a((Entity)out, out.func_174813_aQ()).isEmpty()) {
                        out.func_70106_y();
                        return;
                    }
                    out.func_82149_j(this);
                    out.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)out)), null);
                    out.setSkin(this.skin);
                    out.func_70606_j(out.func_110138_aP() * this.health);
                    this.field_70170_p.func_72838_d((Entity)out);
                    out.particleStatus((byte)7);
                    out.func_70690_d(new PotionEffect(SRPPotions.DEBAR_E, 400, 0, false, false));
                    this.field_70170_p.func_175656_a(this.func_180425_c(), Blocks.field_150350_a.func_176223_P());
                    this.func_70106_y();
                }
                if (this.count % 10 == 0) {
                    this.field_70170_p.func_72960_a((Entity)this, (byte)18);
                }
            }
        }
    }

    public float func_70047_e() {
        return this.field_70131_O;
    }

    public boolean func_70067_L() {
        return true;
    }

    public void setParasite(String in) {
        this.parasite = in;
    }

    public void setGoal(int g) {
        this.goal = g;
    }

    public void setPlus(int p) {
        if (this.plus >= p) {
            return;
        }
        this.plus = p;
        this.active = true;
    }

    public void setSkin(byte in) {
        this.skin = in;
    }

    public void setHealth(float in) {
        if (this.health >= in) {
            return;
        }
        this.health = in;
    }

    public boolean getActive() {
        return this.active;
    }

    protected void func_70037_a(NBTTagCompound compound) {
        if (compound.func_150297_b("parasiteparasite", 8)) {
            this.parasite = compound.func_74779_i("parasiteparasite");
        }
        if (compound.func_150297_b("parasiteactive", 99)) {
            this.active = compound.func_74767_n("parasiteactive");
        }
        if (compound.func_150297_b("parasitepoint", 99)) {
            this.count = compound.func_74762_e("parasitepoint");
        }
        if (compound.func_150297_b("parasiteplus", 99)) {
            this.plus = compound.func_74762_e("parasiteplus");
        }
        if (compound.func_150297_b("parasitegoal", 99)) {
            this.goal = compound.func_74762_e("parasitegoal");
        }
        if (compound.func_150297_b("parasiteskin", 99)) {
            this.skin = compound.func_74771_c("parasiteskin");
        }
        if (compound.func_150297_b("parasitehealth", 99)) {
            this.health = compound.func_74760_g("parasitehealth");
        }
    }

    protected void func_70014_b(NBTTagCompound compound) {
        if (this.parasite != null) {
            compound.func_74778_a("parasiteparasite", this.parasite);
        }
        compound.func_74757_a("parasiteactive", this.active);
        compound.func_74768_a("parasitepoint", this.count);
        compound.func_74768_a("parasiteplus", this.plus);
        compound.func_74768_a("parasitegoal", this.goal);
        compound.func_74774_a("parasiteskin", this.skin);
        compound.func_74776_a("parasitehealth", this.health);
    }

    public void func_70103_a(byte id) {
        if (id == 18) {
            for (int i = 0; i <= 1; ++i) {
                this.spawnParticles(SRPEnumParticle.BIOMASS, 0, 0, 0);
            }
        } else {
            super.func_70103_a(id);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(SRPEnumParticle particleType, int r, int g, int b) {
        double d0 = this.field_70146_Z.nextGaussian() * 0.02;
        double d1 = this.field_70146_Z.nextGaussian() * 0.02;
        double d2 = this.field_70146_Z.nextGaussian() * 0.02;
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)this.field_70146_Z.nextFloat() * ((double)this.field_70131_O + 0.5), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, r, g, b);
    }

    protected void func_70088_a() {
    }
}

