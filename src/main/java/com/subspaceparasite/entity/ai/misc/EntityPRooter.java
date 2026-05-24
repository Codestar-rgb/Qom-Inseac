/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.entity.ai.misc.EntityPStationaryArchitect;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.deterrent.EntityLeemB;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.spawn.ParasiteSummon;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteNexusProtection3;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityPRooter
extends EntityPStationaryArchitect {
    protected int leemRange;
    protected int leemRangeEffect;
    protected int leemBalls;
    protected int leemCooldown;
    protected int leemCooldownReset;
    private int blockR;
    private static final DataParameter<Boolean> RTTS = EntityDataManager.func_187226_a(EntityPRooter.class, (DataSerializer)DataSerializers.field_187198_h);
    protected int rangeB = 0;

    public EntityPRooter(World worldIn) {
        super(worldIn);
        this.setScentHPMultiplier(1.0f);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(RTTS, (Object)false);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.srpTicks == 5) {
                --this.leemCooldown;
                ++this.blockR;
                if (this.blockR >= 7) {
                    this.checkRTTS();
                    this.blockR = 0;
                }
            }
            if (this.srpTicks == 10 && this.leemCooldown <= 0) {
                this.leemCooldown = this.leemCooldownReset;
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b((double)this.leemRangeEffect, (double)this.leemRangeEffect, (double)this.leemRangeEffect);
                List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
                for (EntityParasiteBase mob : moblist) {
                    if (mob == this || !mob.func_70089_S() || mob instanceof EntityPRooter || mob.getParasiteIDRegister() == 314) continue;
                    mob.func_70690_d(new PotionEffect(SPPotions.PIVOT_E, 300, this.stage - 1, false, false));
                    mob.func_70690_d(new PotionEffect(SPPotions.PARATE_E, 300, this.stage - 1, false, false));
                    mob.SetRooter(this);
                    mob.applyGene(new boolean[]{true, true, true, true, true, true, true, true, true, true}, new float[]{2.5f, 3.0f, 0.5f});
                }
            }
        }
    }

    @Override
    public void func_70690_d(PotionEffect potioneffectIn) {
        if (potioneffectIn.func_188419_a() == SPPotions.PIVOT_E) {
            return;
        }
        super.func_70690_d(potioneffectIn);
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b((double)(this.leemRange + 1), (double)this.leemRange, (double)(this.leemRange + 1));
        List moblist = this.field_70170_p.func_72872_a(EntityLeemB.class, axisalignedbb);
        if (moblist.size() > 0) {
            float part = amount / (float)moblist.size();
            for (EntityLeemB mob : moblist) {
                mob.func_70097_a(source, part);
            }
            return super.func_70097_a(source, 0.0f);
        }
        this.spawnLeemB(this.leemBalls);
        return super.func_70097_a(source, amount);
    }

    protected void spawnLeemB(int in) {
        int cap = this.field_70146_Z.nextInt(this.leemBalls) + 1;
        for (int i = 0; i < cap; ++i) {
            ParasiteSummon.SummonM((EntityLivingBase)this, new String[]{"subspaceparasite:rooterball;1;1"}, 2, 5, null);
        }
    }

    public void checkRTTS() {
        if (this.rangeB == 0) {
            return;
        }
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        for (int k2 = -1 * this.rangeB; k2 <= 1 * this.rangeB; ++k2) {
            for (int l2 = -1 * this.rangeB; l2 <= 1 * this.rangeB; ++l2) {
                double i3 = l1 + (double)k2;
                double l = i2 + (double)l2;
                IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos(i3, this.field_70163_u - 0.5, l));
                Block block = iblockstate.func_177230_c();
                if (block != Blocks.field_150350_a) continue;
                this.setRTTS(false);
                return;
            }
        }
        this.setRTTS(true);
    }

    @Override
    public void generateStructure() {
        if (SPConfig.nexusStructures) {
            WorldGenParasiteNexusProtection3 p1 = new WorldGenParasiteNexusProtection3(false, 1);
            p1.func_180709_b(this.field_70170_p, new Random(), this.func_180425_c());
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
    }

    public boolean getRTTS() {
        return (Boolean)this.field_70180_af.func_187225_a(RTTS);
    }

    public void setRTTS(boolean in) {
        this.field_70180_af.func_187227_b(RTTS, (Object)in);
    }
}

