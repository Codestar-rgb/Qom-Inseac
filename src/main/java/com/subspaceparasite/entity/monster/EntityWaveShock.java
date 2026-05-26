/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEndGateway
 *  net.minecraft.block.BlockEndPortalFrame
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockPortal
 *  net.minecraft.block.material.Material
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
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.subspaceparasite.entity.monster;

import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndGateway;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityWaveShock
extends EntityParasiteBase {
    private int raaa;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int duration;
    private boolean canTarget;
    EntityParasiteBase caster;

    public EntityWaveShock(World worldIn) {
        super(worldIn);
        this.func_70105_a(3.1f, 0.2f);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.killcount = -10.0;
        this.MiniDamage = 0.1f;
        this.canTarget = true;
        this.duration = 1;
    }

    public EntityWaveShock(World worldIn, EntityParasiteBase father) {
        this(worldIn);
        this.caster = father;
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
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
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
        return 213;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
            if (state.func_177230_c() != Blocks.field_150350_a) {
                int id = Block.func_176210_f((IBlockState)state);
                for (int i = 0; i < 35; ++i) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)this.field_70146_Z.nextFloat() * ((double)this.field_70130_N * 1.2) * 2.0 - (double)this.field_70130_N * 1.2, this.field_70163_u, this.field_70161_v + (double)this.field_70146_Z.nextFloat() * ((double)this.field_70130_N * 1.2) * 2.0 - (double)this.field_70130_N * 1.2, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() + 140.0, this.field_70146_Z.nextGaussian() * 0.02, new int[]{id});
                }
            }
        } else {
            if (this.targetX == 0.0 || this.caster == null) {
                this.func_70106_y();
                return;
            }
            if (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockLiquid) {
                this.func_70106_y();
                return;
            }
            if (this.field_70173_aa > 20) {
                if (this.field_70165_t == this.field_70169_q || this.field_70161_v == this.field_70166_s) {
                    this.func_70106_y();
                }
                if (this.field_70173_aa > 20 * this.duration) {
                    this.func_70106_y();
                    return;
                }
            }
            this.skillBreakBlocks();
            float f = this.field_70130_N / 2.0f;
            float f1 = this.field_70131_O;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t - (double)f, this.field_70163_u, this.field_70161_v - (double)f, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f).func_72314_b(1.5, 0.2, 1.5);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob instanceof EntityParasiteBase) continue;
                this.func_70652_k((Entity)mob);
            }
            if (this.func_70092_e(this.targetX, this.targetY, this.targetZ) > 2.0) {
                this.func_70605_aq().func_75642_a(this.targetX, this.targetY, this.targetZ, 0.6);
            }
            if (this.field_70165_t == this.targetX && this.field_70161_v == this.targetZ) {
                this.func_70106_y();
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
        if (this.caster == null) {
            return false;
        }
        boolean flag = this.caster.func_70652_k(entityIn);
        if (flag) {
            entityIn.field_70181_x += 0.64645;
        }
        return flag;
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
    }

    protected void func_85033_bc() {
    }

    public AxisAlignedBB func_70046_E() {
        return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        if (this.field_70173_aa > 40 || this.caster == null || entitylivingbaseIn == null) {
            this.func_70106_y();
            return;
        }
        if (this.canTarget) {
            this.targetX = entitylivingbaseIn.field_70165_t;
            this.targetY = this.caster.field_70163_u;
            this.targetZ = entitylivingbaseIn.field_70161_v;
            this.blockH = this.caster.getBlockH();
            this.BGrange = 2;
            this.BGheight = 3;
            this.canTarget = false;
        }
    }

    @Override
    public void skillBreakBlocks() {
        if (this.field_70128_L || this.caster == null) {
            return;
        }
        int blocksbroke = 0;
        if (!ForgeEventFactory.getMobGriefingEvent((World)this.field_70170_p, (Entity)this)) {
            return;
        }
        int i1 = MathHelper.func_76128_c((double)this.field_70163_u);
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        int offsetT = 0;
        for (int k2 = -1 * this.BGrange; k2 <= 1 * this.BGrange; ++k2) {
            for (int l2 = -1 * this.BGrange; l2 <= 1 * this.BGrange; ++l2) {
                for (int j = offsetT; j <= this.BGheight + offsetT; ++j) {
                    String name;
                    double i3 = l1 + (double)k2;
                    double k = i1 + j;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    Block block = iblockstate.func_177230_c();
                    float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                    if (!(bHard <= this.blockH) || !(bHard >= 0.0f) || block instanceof IMetaName && block != SPBlocks.ParasiteCanister || block == SPBlocks.BiomeHeart || block == SPBlocks.ColonyHeart || block == SPBlocks.ParasiteRubbleDense || block == SPBlocks.ParasiteCanisterActive || block == SPBlocks.dodN || block instanceof BlockLiquid || block instanceof BlockPortal || block instanceof BlockEndGateway || block instanceof BlockEndPortalFrame || iblockstate.func_185904_a() == Material.field_151567_E || this.blockException(name = block.getRegistryName().toString()) || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this, (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
                    if (SPConfig.cystActive) {
                        int meta = block.func_176201_c(iblockstate);
                        boolean bl = flag = this.destroyBlockPos(blockpos, false) || flag;
                        if (SPConfig.doTileDrops) {
                            this.caster.addToBlockInv(name + ";" + meta);
                        }
                    } else {
                        this.destroyBlockPos(blockpos, SPConfig.doTileDrops);
                    }
                    ++blocksbroke;
                }
            }
        }
        this.BGrange = Brangeatm;
        this.SkillBGflag = true;
    }
}

