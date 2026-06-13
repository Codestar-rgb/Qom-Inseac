/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityMultiPart
 *  net.minecraft.entity.MultiPartEntityPart
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.network.HitboxHit;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHitbox
extends MultiPartEntityPart {
    private float radius;
    private float angle;
    private float yPos;
    private float dmgVuln;
    private EntityLiving parentEntity;

    public EntityHitbox(EntityLiving parent, float angle, float radius, float yOffset, float width, float height, float damageVulnerability) {
        super((IEntityMultiPart)parent, "part", width, height);
        this.parentEntity = parent;
        this.yPos = yOffset;
        this.radius = radius;
        this.angle = angle;
        this.dmgVuln = damageVulnerability;
        this.field_70158_ak = true;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getAngle() {
        return this.angle;
    }

    public float getRadius() {
        return this.radius;
    }

    public boolean func_70067_L() {
        return this.getParent().func_70089_S();
    }

    public void resize(float scale) {
        this.func_70105_a(this.field_70130_N * scale, this.field_70131_O * scale);
    }

    public EntityLiving getParent() {
        return this.parentEntity;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        if (this.field_70170_p.field_72995_K && (source.func_76364_f() instanceof EntityPlayer || source.func_76346_g() instanceof EntityPlayer)) {
            SRPNetwork.CHANNEL.sendToServer((IMessage)new HitboxHit(this.getParent(), amount * this.dmgVuln, true));
        }
        return this.getParent().func_70097_a(source, amount * this.dmgVuln);
    }

    public boolean func_184230_a(EntityPlayer player, EnumHand hand) {
        if (this.field_70170_p.field_72995_K) {
            SRPNetwork.CHANNEL.sendToServer((IMessage)new HitboxHit(this.getParent(), 0.0f, false));
        }
        return this.getParent().func_184230_a(player, hand);
    }

    public boolean func_180431_b(DamageSource source) {
        return this.func_190530_aW() && source != DamageSource.field_76380_i;
    }

    public void func_70071_h_() {
        this.func_70634_a(this.getParent().field_70165_t + (double)this.radius * Math.cos((double)this.getParent().field_70761_aq * (Math.PI / 180) + (double)this.angle), this.getParent().field_70163_u + (double)this.yPos, this.getParent().field_70161_v + (double)this.radius * Math.sin((double)this.getParent().field_70761_aq * (Math.PI / 180) + (double)this.angle));
        if (!this.field_70170_p.field_72995_K && this.field_70130_N >= this.getParent().field_70130_N) {
            this.collideWithNearbyEntities();
        }
        if (!this.getParent().func_70089_S() || this.getParent() == null || this.func_130014_f_().func_175659_aa() == EnumDifficulty.PEACEFUL) {
            this.func_130014_f_().func_72973_f((Entity)this);
        }
        super.func_70071_h_();
    }

    public void collideWithNearbyEntities() {
        List entities = this.field_70170_p.func_72839_b((Entity)this, this.func_174813_aQ().func_72321_a((double)0.2f, 0.0, (double)0.2f));
        entities.stream().filter(entity -> entity != this.field_70259_a && !(entity instanceof EntityHitbox) && entity.func_70104_M()).forEach(entity -> entity.func_70108_f((Entity)this.getParent()));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180426_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
    }
}

