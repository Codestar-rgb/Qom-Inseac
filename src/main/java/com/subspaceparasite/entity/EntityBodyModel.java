/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 */
package com.subspaceparasite.entity;

import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

public class EntityBodyModel
extends EntityBody {
    private static final DataParameter<Byte> SKIN = EntityDataManager.func_187226_a(EntityBodyModel.class, (DataSerializer)DataSerializers.field_187191_a);
    public float prevRenderYawOffset;
    public float renderYawOffset;
    public float prevRotationYawHead;
    public float rotationYawHead;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    public int hurtTime;
    public int deathTime;

    public EntityBodyModel(EntityParasiteBase parent, float width, float height, float damageMultiplier, float offset, float offheight, int inv, int id, boolean side) {
        super(parent, width, height, damageMultiplier, offset, offheight, inv, id, side);
    }

    public EntityBodyModel(EntityParasiteBase parent, float width, float height, float damageMultiplier, float offset, float offheight, int inv, int id, boolean side, float eyes) {
        super(parent, width, height, damageMultiplier, offset, offheight, inv, id, side, eyes);
    }

    @Override
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(SKIN, (Object)0);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        this.prevRenderYawOffset = this.parent.field_70760_ar;
        this.renderYawOffset = this.parent.field_70761_aq;
        this.prevRotationYawHead = this.parent.field_70758_at;
        this.rotationYawHead = this.parent.field_70759_as;
        this.prevLimbSwingAmount = this.parent.field_184618_aE;
        this.limbSwingAmount = this.parent.field_70721_aZ;
        this.limbSwing = this.parent.field_184619_aG;
        this.hurtTime = this.parent.field_70737_aN;
        this.deathTime = this.parent.field_70725_aQ;
        this.field_70173_aa = this.parent.field_70173_aa;
    }

    public byte getSkin() {
        return (byte)this.parent.getSkin();
    }

    public void setSkin(int texture) {
        this.field_70180_af.func_187227_b(SKIN, (Object)((byte)texture));
    }
}

