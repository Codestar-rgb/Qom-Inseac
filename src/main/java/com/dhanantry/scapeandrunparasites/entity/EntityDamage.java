/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityDamage
extends Entity {
    private int lifeTicks = 10;
    private EntityLivingBase caster;
    private UUID casterUuid;
    private float mobDamage;
    private boolean pulling;
    private float str;
    private boolean follow;
    private Entity ffollow;

    public EntityDamage(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.2f, 0.9f);
    }

    public EntityDamage(World worldIn, double x, double y, double z, float rotation, EntityLivingBase casterIn, float damage, boolean pull, float strenght) {
        this(worldIn);
        this.setCaster(casterIn);
        this.field_70177_z = rotation * 57.295776f;
        this.func_70107_b(x, y, z);
        this.mobDamage = damage;
        this.pulling = pull;
        this.str = strenght;
    }

    public EntityDamage(World worldIn, double x, double y, double z, float rotation, EntityLivingBase casterIn, float damage, boolean pull, float strenght, float w, float h) {
        this(worldIn, x, y, z, rotation, casterIn, damage, pull, strenght);
        this.func_70105_a(w, h);
    }

    protected void func_70088_a() {
    }

    public void setCaster(@Nullable EntityLivingBase in) {
        this.caster = in;
        this.casterUuid = in == null ? null : in.func_110124_au();
    }

    public void setFollower(Entity in) {
        this.follow = true;
        this.ffollow = in;
    }

    @Nullable
    public EntityLivingBase getCaster() {
        Entity entity;
        if (this.caster == null && this.casterUuid != null && this.field_70170_p instanceof WorldServer && (entity = ((WorldServer)this.field_70170_p).func_175733_a(this.casterUuid)) instanceof EntityLivingBase) {
            this.caster = (EntityLivingBase)entity;
        }
        return this.caster;
    }

    protected void func_70037_a(NBTTagCompound compound) {
        this.casterUuid = compound.func_186857_a("OwnerUUID");
    }

    protected void func_70014_b(NBTTagCompound compound) {
        if (this.casterUuid != null) {
            compound.func_186854_a("OwnerUUID", this.casterUuid);
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.follow) {
                if (this.ffollow == null) {
                    if (--this.lifeTicks < 0) {
                        this.func_70106_y();
                    }
                } else {
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(1.5);
                    List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase entityIn : moblist) {
                        if (this.field_70170_p.func_147447_a(new Vec3d(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v), new Vec3d(entityIn.field_70165_t, entityIn.field_70163_u + (double)entityIn.func_70047_e(), entityIn.field_70161_v), false, true, false) != null) continue;
                        this.damage(entityIn);
                    }
                    int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
                    double l1 = this.field_70165_t;
                    double i2 = this.field_70161_v;
                    boolean flag = false;
                    int offsetT = 0;
                    int BGrange = 2;
                    int BGheight = 2;
                    for (int k2 = -1 * BGrange; k2 <= BGrange; ++k2) {
                        for (int l2 = -1 * BGrange; l2 <= BGrange; ++l2) {
                            for (int j = 1 + offsetT; j <= BGheight + offsetT; ++j) {
                                double i3 = l1 + (double)k2;
                                double k = i1 + j;
                                double l = i2 + (double)l2;
                                BlockPos blockpos = new BlockPos(i3, k, l);
                                IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                                Block block = iblockstate.func_177230_c();
                                float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                                if (!((double)bHard <= 0.3 && bHard >= 0.0f) && !(block instanceof IMetaName) || block == SRPBlocks.BiomeHeart || block == SRPBlocks.ColonyHeart || block == SRPBlocks.ParasiteRubbleDense || block == SRPBlocks.ParasiteCanisterActive || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this.getCaster(), (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
                                flag = this.field_70170_p.func_175655_b(blockpos, true) || flag;
                            }
                        }
                    }
                    this.field_70165_t = this.ffollow.field_70165_t;
                    this.field_70163_u = this.ffollow.field_70163_u;
                    this.field_70161_v = this.ffollow.field_70161_v;
                    if (!this.ffollow.func_70089_S()) {
                        this.ffollow = null;
                    }
                }
            } else {
                for (EntityLivingBase entitylivingbase : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(0.3, 0.0, 0.2))) {
                    this.damage(entitylivingbase);
                }
                if (--this.lifeTicks < 0) {
                    this.func_70106_y();
                }
            }
        }
    }

    private void damage(EntityLivingBase in) {
        if (in instanceof EntityParasiteBase) {
            return;
        }
        EntityLivingBase entitylivingbase = this.getCaster();
        if (in.func_70089_S() && !in.func_190530_aW() && in != entitylivingbase) {
            if (entitylivingbase == null) {
                in.func_70097_a(DamageSource.field_76377_j.func_76348_h(), this.mobDamage);
            } else {
                if (entitylivingbase.func_184191_r((Entity)in)) {
                    return;
                }
                if (this.pulling) {
                    if (in instanceof EntityPlayer) {
                        this.knockBack2((Entity)in, this.str, in.field_70165_t - this.caster.field_70165_t, in.field_70161_v - this.caster.field_70161_v);
                    } else {
                        this.knockBack2((Entity)in, this.str / 3.0f, in.field_70165_t - this.caster.field_70165_t, in.field_70161_v - this.caster.field_70161_v);
                    }
                } else if (in instanceof EntityPlayer) {
                    this.knockBack2((Entity)in, this.str, this.caster.field_70165_t - in.field_70165_t, this.caster.field_70161_v - in.field_70161_v);
                } else {
                    this.knockBack2((Entity)in, this.str, this.caster.field_70165_t - in.field_70165_t, this.caster.field_70161_v - in.field_70161_v);
                }
                entitylivingbase.func_70652_k((Entity)in);
            }
        }
    }

    private void knockBack2(Entity entityIn, float strength, double xRatio, double zRatio) {
        float f = MathHelper.func_76133_a((double)(xRatio * xRatio + zRatio * zRatio));
        entityIn.field_70159_w /= 2.0;
        entityIn.field_70179_y /= 2.0;
        entityIn.field_70159_w -= xRatio / (double)f * (double)strength;
        entityIn.field_70179_y -= zRatio / (double)f * (double)strength;
        if (entityIn.field_70122_E) {
            entityIn.field_70181_x /= 2.0;
            entityIn.field_70181_x += (double)strength;
            if (entityIn.field_70181_x > (double)0.4f) {
                entityIn.field_70181_x = 0.4f;
            }
        }
    }
}

