/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackMelee
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityWave
extends EntityParasiteBase {
    private int raaa;
    private EntityLivingBase target;
    private int duration;

    public EntityWave(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.5f, 0.2f);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.killcount = -10.0;
        this.MiniDamage = 0.1f;
        this.duration = 1;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0, false));
    }

    @Override
    public void applyBonuses(SPSaveData saveData, World world) {
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.45);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(20.0);
    }

    public void setDamages(double baseDamage, float min, int range, int durationF) {
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(baseDamage);
        this.MiniDamage = min;
        this.raaa = range;
        this.duration = durationF;
    }

    @Override
    public int getParasiteIDRegister() {
        return 211;
    }

    public void func_70071_h_() {
        block8: {
            block7: {
                super.func_70071_h_();
                if (!this.field_70170_p.field_72995_K) break block7;
                IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
                if (state.func_177230_c() == Blocks.field_150350_a) break block8;
                int id = Block.func_176210_f((IBlockState)state);
                for (int i = 0; i < 15; ++i) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() + 20.0, this.field_70146_Z.nextGaussian() * 0.02, new int[]{id});
                }
                break block8;
            }
            if (this.target != null && !this.target.func_70089_S()) {
                this.func_70106_y();
                return;
            }
            if (this.field_70173_aa > 40) {
                if (this.field_70165_t == this.field_70169_q || this.field_70161_v == this.field_70166_s) {
                    this.func_70106_y();
                }
                if (this.field_70173_aa > 20 * this.duration) {
                    this.func_70106_y();
                    return;
                }
            }
            if (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockLiquid) {
                this.func_70106_y();
                return;
            }
            float f = this.field_70130_N / 2.0f;
            float f1 = this.field_70131_O;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t - (double)f, this.field_70163_u, this.field_70161_v - (double)f, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f).func_72314_b(0.4, 0.2, 0.4);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob instanceof EntityParasiteBase) continue;
                this.attackEntityAsMobMinimum(mob, this.MiniDamage);
            }
        }
    }

    protected void func_70664_aZ() {
        this.func_70106_y();
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean func_70687_e(PotionEffect potioneffectIn) {
        return false;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        return false;
    }

    public AxisAlignedBB func_70046_E() {
        return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
    }

    protected void func_85033_bc() {
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        super.func_70074_a(entityLivingIn);
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.target == entityLivingIn) {
            this.func_70106_y();
        }
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        if (this.field_70173_aa > 40) {
            this.func_70106_y();
        }
        super.func_70624_b(entitylivingbaseIn);
        this.target = entitylivingbaseIn;
    }
}

