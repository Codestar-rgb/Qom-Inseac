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
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNavigateSwimmer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.dhanantry.scapeandrunparasites.entity.monster.primitive;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeNotGround;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSwim;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPrimitive;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityLum
extends EntityPPrimitive
implements EntityCanSwim,
EntityCutomAttack {
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityLum.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityLum(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.3f, 1.8f);
        this.field_70765_h = new AIMoveControl(this);
        this.borderOrb = -1;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.field_70158_ak = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 66;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeNotGround(this, 4.0, SRPConfig.primitiveFollow, 0.08, false, 0, 3));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.LUM_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.LUM_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.LUM_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.LUM_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
    }

    protected PathNavigate func_175447_b(World worldIn) {
        return new PathNavigateSwimmer((EntityLiving)this, worldIn);
    }

    @Override
    public void func_70636_d() {
        if (this.func_175446_cd()) {
            return;
        }
        this.liquidLeap = 0;
        super.func_70636_d();
        if (this.field_70171_ac) {
            this.func_189654_d(true);
        } else {
            this.func_189654_d(false);
        }
        this.liquidLeap = 0;
        if (!this.field_70170_p.field_72995_K) {
            if (this.func_70638_az() != null) {
                this.setParasiteStatus(1);
            }
            if (this.field_70173_aa % 20 == 0) {
                // empty if block
            }
        }
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        return super.func_70097_a(source, amount);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            entityIn.field_70181_x += -0.5645;
        }
        return flag;
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        return this.func_70652_k(entityIn);
    }

    public float func_70047_e() {
        return 1.0f;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        super.func_70074_a(entityLivingIn);
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.LUM_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.LUM_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.LUM_DEATH;
    }

    public boolean func_70648_aU() {
        return true;
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && SRPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
    }

    public boolean func_70058_J() {
        return this.field_70170_p.func_72917_a(this.func_174813_aQ(), (Entity)this);
    }

    public int func_70627_aG() {
        return 0;
    }

    @Override
    public boolean func_70692_ba() {
        return true;
    }

    protected int func_70693_a(EntityPlayer player) {
        return 1 + this.field_70170_p.field_73012_v.nextInt(3);
    }

    public void func_70030_z() {
        int i = this.func_70086_ai();
        super.func_70030_z();
        if (this.func_175446_cd()) {
            return;
        }
        if (this.func_70089_S() && !this.func_70090_H()) {
            this.func_70050_g(--i);
            if (this.func_70086_ai() == -20) {
                this.func_70050_g(0);
                this.func_70097_a(DamageSource.field_76369_e, 2.0f);
            }
        } else {
            this.func_70050_g(300);
        }
    }

    public boolean func_96092_aw() {
        return false;
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(1)) {
                case 0: {
                    this.setSkin(7);
                }
            }
        }
        return floo;
    }

    @Override
    public void skillBreakBlocks() {
        EntityLivingBase target;
        if (this.field_70128_L) {
            return;
        }
        if (this.getBlockH() == 0.0f) {
            return;
        }
        int blocksbroke = 0;
        if (!ForgeEventFactory.getMobGriefingEvent((World)this.field_70170_p, (Entity)this)) {
            return;
        }
        int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        int offsetT = -2;
        if (this.func_70638_az() != null && (target = this.func_70638_az()).func_70092_e(this.field_70165_t, target.field_70163_u, this.field_70161_v) < 9.0) {
            if (target.field_70163_u - this.field_70163_u < -1.0) {
                offsetT -= 2;
                if (!this.field_70122_E) {
                    --offsetT;
                }
            } else if (target.field_70163_u - this.field_70163_u > 2.0) {
                ++offsetT;
                this.BGrange = 0;
            }
        }
        for (int k2 = -1 * this.BGrange; k2 <= 1 * this.BGrange; ++k2) {
            for (int l2 = -1 * this.BGrange; l2 <= 1 * this.BGrange; ++l2) {
                for (int j = 1 + offsetT; j <= this.BGheight; ++j) {
                    String name;
                    double i3 = l1 + (double)k2;
                    double k = i1 + j;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    Block block = iblockstate.func_177230_c();
                    float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                    if (!(bHard <= this.getBlockH()) || !(bHard >= 0.0f) || block instanceof IMetaName && block != SRPBlocks.ParasiteCanister || block == SRPBlocks.BiomeHeart || block == SRPBlocks.ColonyHeart || block == SRPBlocks.ParasiteRubbleDense || block == SRPBlocks.ParasiteCanisterActive || block == SRPBlocks.dodN || block instanceof BlockLiquid || block instanceof BlockPortal || block instanceof BlockEndGateway || block instanceof BlockEndPortalFrame || iblockstate.func_185904_a() == Material.field_151567_E || this.blockException(name = block.getRegistryName().toString()) || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this, (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
                    if (SRPConfig.cystActive) {
                        int meta = block.func_176201_c(iblockstate);
                        boolean bl = flag = this.destroyBlockPos(blockpos, false) || flag;
                        if (SRPConfig.doTileDrops) {
                            this.addToBlockInv(name + ";" + meta);
                        }
                    } else {
                        this.destroyBlockPos(blockpos, SRPConfig.doTileDrops);
                    }
                    if (SRPConfigMobs.lumWaterPlacement) {
                        this.field_70170_p.func_180501_a(blockpos, Blocks.field_150355_j.func_176223_P(), 3);
                    }
                    ++blocksbroke;
                }
            }
        }
        this.BGrange = Brangeatm;
        this.SkillBGflag = true;
    }

    public void func_70091_d(MoverType type, double x, double y, double z) {
        super.func_70091_d(type, x, y, z);
        this.func_145775_I();
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(VEX_FLAGS, (Object)0);
    }

    private boolean getVexFlag(int mask) {
        byte i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
        return (i & mask) != 0;
    }

    private void setVexFlag(int mask, boolean value) {
        int i = ((Byte)this.field_70180_af.func_187225_a(VEX_FLAGS)).byteValue();
        i = value ? (i |= mask) : (i &= ~mask);
        this.field_70180_af.func_187227_b(VEX_FLAGS, (Object)((byte)(i & 0xFF)));
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            return EntityLum.this.field_70146_Z.nextInt(7) == 0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityLum.this);
            int flag = 1;
            double speed = 0.19;
            if (EntityLum.this.func_70638_az() != null) {
                if (EntityLum.this.func_70068_e((Entity)EntityLum.this.func_70638_az()) > 100.0) {
                    blockpos = new BlockPos((Entity)EntityLum.this.func_70638_az());
                    flag = 2;
                } else if (EntityLum.this.func_70068_e((Entity)EntityLum.this.func_70638_az()) < 36.0) {
                    blockpos = new BlockPos((Entity)EntityLum.this.func_70638_az());
                    flag = 3;
                }
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityLum.this.field_70146_Z.nextInt(15) - 7, EntityLum.this.field_70146_Z.nextInt(11) - 5, EntityLum.this.field_70146_Z.nextInt(15) - 7);
                if (flag == 2) {
                    blockpos1 = blockpos.func_177982_a(EntityLum.this.field_70146_Z.nextInt(6) - 2, EntityLum.this.field_70146_Z.nextInt(7) - 2, EntityLum.this.field_70146_Z.nextInt(6) - 2);
                } else if (flag == 3) {
                    blockpos1 = blockpos.func_177982_a(EntityLum.this.field_70146_Z.nextInt(4) + 3, EntityLum.this.field_70146_Z.nextInt(5) + 4, EntityLum.this.field_70146_Z.nextInt(4) + 3);
                }
                if (EntityLum.this.field_70170_p.func_180495_p(blockpos1).func_185904_a() != Material.field_151586_h) continue;
                EntityLum.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                if (EntityLum.this.func_70638_az() != null) break;
                EntityLum.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityLum vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityLum.this.field_70165_t;
                double d1 = this.field_75647_c - EntityLum.this.field_70163_u;
                double d2 = this.field_75644_d - EntityLum.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityLum.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityLum.this.field_70159_w *= 0.5;
                    EntityLum.this.field_70181_x *= 0.5;
                    EntityLum.this.field_70179_y *= 0.5;
                } else {
                    EntityLum.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityLum.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityLum.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityLum.this.func_70638_az() == null) {
                        EntityLum.this.field_70761_aq = EntityLum.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityLum.this.field_70159_w, (double)EntityLum.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityLum.this.func_70638_az().field_70165_t - EntityLum.this.field_70165_t;
                        double d5 = EntityLum.this.func_70638_az().field_70161_v - EntityLum.this.field_70161_v;
                        EntityLum.this.field_70761_aq = EntityLum.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

