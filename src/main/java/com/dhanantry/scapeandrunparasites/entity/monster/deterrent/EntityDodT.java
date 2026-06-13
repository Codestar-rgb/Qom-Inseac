/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.deterrent;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityDodT
extends EntityPStationary {
    private String paraToRelo;
    private EntityPDispatcher father;
    private EntityParasiteBase tele;
    private int stage;

    public EntityDodT(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.7f, 2.5f);
        this.field_70728_aV = 0;
        this.type = (byte)40;
        this.buriedT = 4.0;
        this.damageCap = SRPConfig.turretCap;
        this.pointCap = SRPConfig.turretPointCap;
        this.pointReduction = SRPConfig.turretPointRed;
        this.chanceLearn = SRPConfig.turretChanceLe;
        this.chanceLearnFire = SRPConfig.turretChanceLeFire;
        this.DamageTypeCap = SRPConfig.turretPointDamCap;
        this.MiniDamage = SRPConfig.turretMinDamage;
        this.regen = SRPConfig.turretRegen;
        this.valueEvDeath = 0;
    }

    @Override
    public int getParasiteIDRegister() {
        return 74;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(10.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.func_70638_az() != null && this.func_70638_az().func_70068_e((Entity)this) > 256.0) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)51);
                this.up = true;
            }
            if (this.field_70173_aa > 120 && this.paraToRelo == null && this.tele == null) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)51);
                this.up = true;
            }
            if (this.srpTicks == 10 && this.father != null && this.father.func_70638_az() != null) {
                this.lookAt((Entity)this.father.func_70638_az());
            }
            if (!this.up && this.field_70173_aa > 60 && !this.buried()) {
                if (this.paraToRelo != null) {
                    EntityParasiteBase entityout = (EntityParasiteBase)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(this.paraToRelo), (World)this.field_70170_p);
                    if (entityout != null) {
                        entityout.func_82149_j((Entity)this);
                        entityout.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
                        this.field_70170_p.func_72838_d((Entity)entityout);
                        this.applyEffectsDod(entityout);
                        this.paraToRelo = null;
                        entityout.particleStatus((byte)8);
                        entityout.particleStatus((byte)8);
                        if (this.func_70027_ad()) {
                            entityout.func_70606_j(entityout.func_110138_aP() * 0.5f);
                            entityout.func_70015_d(8);
                        }
                        if (this.father != null && this.father.func_70089_S()) {
                            entityout.setDodFatherID(this.father.func_145782_y());
                        }
                        this.particleStatus((byte)8);
                        this.particleStatus((byte)8);
                        this.particleStatus((byte)8);
                    }
                } else if (this.tele != null) {
                    this.tele.func_82149_j((Entity)this);
                    this.tele.particleStatus((byte)7);
                    this.particleStatus((byte)7);
                    this.tele = null;
                }
            }
        }
    }

    @Override
    protected void retreat(boolean dead) {
        if (this.up) {
            this.buried += 0.08;
            if (this.buried > this.buriedT && !this.field_70170_p.field_72995_K) {
                this.func_70106_y();
            }
        }
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        super.func_70645_a(cause);
        if (this.father != null && this.father.func_70089_S()) {
            this.father.addParaBack(this.paraToRelo);
        }
    }

    @Override
    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (this.field_70170_p.field_72995_K) {
            return true;
        }
        Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
        if (wea != SRPItems.itembase) {
            // empty if block
        }
        if (this.father != null) {
            this.father.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 100, 1, false, false));
        }
        return super.func_184645_a(player, hand);
    }

    public void setTele(String in, int s) {
        this.paraToRelo = in;
        this.stage = s;
    }

    public void setTele(EntityParasiteBase in) {
        this.tele = in;
    }

    public String getTele() {
        return this.paraToRelo;
    }

    protected void applyEffectsDod(EntityParasiteBase in) {
        String[] pots = SRPConfigMobs.dodsiEffects;
        switch (this.stage) {
            case 2: {
                pots = SRPConfigMobs.dodsiiEffects;
                break;
            }
            case 3: {
                pots = SRPConfigMobs.dodsiiiEffects;
                break;
            }
            case 4: {
                pots = SRPConfigMobs.dodsivEffects;
            }
        }
        if (pots.length == 0) {
            return;
        }
        String[] here = new String[3];
        for (String i : pots) {
            here = i.split(";");
            int ticks = Integer.parseInt(here[0]);
            int amp = Integer.parseInt(here[2]);
            Potion potion = Potion.func_180142_b((String)here[1]);
            if (potion == null) continue;
            in.func_70690_d(new PotionEffect(potion, ticks, amp));
        }
    }

    public EntityPDispatcher getFather() {
        return this.father;
    }

    public void setFather(EntityPDispatcher in) {
        this.father = in;
    }

    public float func_70047_e() {
        return 1.8f;
    }
}

