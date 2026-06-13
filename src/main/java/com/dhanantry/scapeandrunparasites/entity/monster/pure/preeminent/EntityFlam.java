/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbScary;
import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFlam
extends EntityPPreeminent {
    private EntityPPreeminent father;
    private BlockPos targetP;
    private int activationF;
    private boolean moveM;
    private byte typeDead;
    private byte steadyD;
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityFlam.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityFlam(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(1.2f, 1.2f);
        this.func_189654_d(true);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.borderOrb = -1;
        this.canModRender = 1;
        this.field_70728_aV = SRPAttributes.XP_LiTTLE;
        if (SRPConfigMobs.emanaMaxY != 256) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightLimits(this, SRPConfigMobs.emanaMaxY, true));
        }
    }

    @Override
    public int getParasiteIDRegister() {
        return 89;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIChargeAttack());
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.FLAM_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.FLAM_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.FLAM_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.preeminentFollow);
    }

    public void setDamageATT(EntityParasiteBase in) {
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 2.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.func_175446_cd()) {
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.moveM) {
                ++this.activationF;
            }
            if (this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
                this.steadyD = (byte)(this.steadyD + 1);
            }
            if (this.steadyD > 20) {
                this.DeadDead();
                return;
            }
            if (this.targetP != null && this.father != null) {
                if (this.field_70173_aa % 20 == 0 && this.getBlockH() != 0.0f) {
                    this.skillBreakBlocks();
                }
                if (this.func_174818_b(this.targetP) < 4.0 || this.moveM) {
                    this.DeadDead();
                    return;
                }
            } else {
                this.activationF += 5000;
                this.DeadDead();
                return;
            }
            if (this.field_70173_aa > 400) {
                this.activationF += 5000;
                this.DeadDead();
                return;
            }
        }
    }

    @Override
    protected void selfExplode() {
        if (this.field_70170_p.field_72995_K) {
            this.spawnEffectsGore();
        }
        if (!this.field_70170_p.field_72995_K) {
            this.spawnGore();
            this.func_184185_a(SRPSounds.MOBEXPLOTION, 1.0f, 1.0f);
            this.field_70729_aU = true;
            this.func_70106_y();
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u - 2.0, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(4.0);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob instanceof EntityParasiteBase) continue;
                this.func_70652_k((Entity)mob);
            }
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u - 2.0, this.field_70161_v);
            entityareaeffectcloud.setRadius(this.field_70130_N * 3.5f, 0.5f);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 2));
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_82731_v, 300, 2));
            entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 3600, 2, false, false));
            this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
        }
    }

    public void DeadDead() {
        ++this.activationF;
        this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0);
        this.moveM = true;
        if (this.activationF < 20) {
            return;
        }
        block0 : switch (this.typeDead) {
            case 1: {
                this.selfExplode();
                break;
            }
            case 2: {
                EntityOrbScary ttt = new EntityOrbScary(this.field_70170_p, this.father, this.fuseOrb, this.orbStartTimer, false);
                ttt.func_82149_j((Entity)this);
                ttt.field_70163_u -= 3.0;
                this.field_70170_p.func_72838_d((Entity)ttt);
                this.func_184185_a(SRPSounds.ORB_S, 1.0f, 1.0f);
                break;
            }
            case 3: {
                if (this.targetP != null) {
                    if (this.func_174818_b(this.targetP) < 16.0) {
                        this.father.func_82149_j((Entity)this);
                        this.father.setTeleFlam(false);
                        break;
                    }
                    switch (this.field_70146_Z.nextInt(2)) {
                        case 0: {
                            this.selfExplode();
                            break;
                        }
                        case 1: {
                            EntityOrbScary ttt1 = new EntityOrbScary(this.field_70170_p, this.father, this.fuseOrb, this.orbStartTimer, false);
                            ttt1.func_82149_j((Entity)this);
                            ttt1.field_70163_u -= 3.0;
                            this.field_70170_p.func_72838_d((Entity)ttt1);
                            this.func_184185_a(SRPSounds.ORB_S, 1.0f, 1.0f);
                        }
                    }
                    break;
                }
                switch (this.field_70146_Z.nextInt(2)) {
                    case 0: {
                        this.selfExplode();
                        break block0;
                    }
                    case 1: {
                        EntityOrbScary ttt1 = new EntityOrbScary(this.field_70170_p, this.father, this.fuseOrb, this.orbStartTimer, false);
                        ttt1.func_82149_j((Entity)this);
                        ttt1.field_70163_u -= 2.0;
                        this.field_70170_p.func_72838_d((Entity)ttt1);
                        this.func_184185_a(SRPSounds.ORB_S, 1.0f, 1.0f);
                    }
                }
            }
        }
        this.spawnCyst();
        this.func_70106_y();
    }

    public void setFatherTo(EntityPPreeminent in, int typeIn) {
        this.father = in;
        this.typeDead = (byte)typeIn;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        super.func_70624_b(entitylivingbaseIn);
        if (entitylivingbaseIn != null) {
            this.targetP = entitylivingbaseIn.func_180425_c();
        }
    }

    @Override
    protected boolean summonFlam(EntityLivingBase in) {
        return false;
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 0.5f;
    }

    public void func_70091_d(MoverType type, double x, double y, double z) {
        if (!this.moveM) {
            super.func_70091_d(type, x, y, z);
        }
        this.func_145775_I();
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(VEX_FLAGS, (Object)0);
    }

    @Override
    protected void spawnGore() {
        double i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        for (int i = 0; i < 10 && SRPConfig.paraGore; ++i) {
            double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
            double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
            double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
            double d3 = d0 - this.field_70165_t;
            double d4 = d1 - this.field_70163_u;
            double d5 = d2 - this.field_70161_v;
            double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5 / (d6 / 4.0 + 0.1);
            d4 = d4 * d7 * 2.0;
            EntityGore bomb = new EntityGore(this.field_70170_p);
            bomb.setType((byte)12);
            bomb.func_82149_j((Entity)this);
            bomb.setMotion(d3 *= (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3f)), d4, d5 *= d7, 0.25, 0.65);
            this.field_70170_p.func_72838_d((Entity)bomb);
        }
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

    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setCharging(boolean charging) {
        this.setVexFlag(1, charging);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 100) {
            for (int i = 0; i <= 1; ++i) {
                this.spawnParticles(EnumParticleTypes.FLAME);
            }
        } else {
            super.func_70103_a(id);
        }
    }

    @Override
    public void skillBreakBlocks() {
        if (this.field_70128_L) {
            return;
        }
        if (this.getBlockH() == 0.0f) {
            return;
        }
        int blocksbroke = 0;
        int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        boolean offsetT = false;
        for (int k2 = -1 * this.BGrange; k2 <= 1 * this.BGrange; ++k2) {
            for (int l2 = -1 * this.BGrange; l2 <= 1 * this.BGrange; ++l2) {
                for (int j = -1; j <= this.BGheight + 1; ++j) {
                    String name;
                    double i3 = l1 + (double)k2;
                    double k = i1 + j;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    Block block = iblockstate.func_177230_c();
                    float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                    if (!(bHard <= this.getBlockH()) || !(bHard >= 0.0f) || block instanceof IMetaName && block != SRPBlocks.ParasiteCanister || block == SRPBlocks.BiomeHeart || block == SRPBlocks.ColonyHeart || block == SRPBlocks.ParasiteRubbleDense || block == SRPBlocks.ParasiteCanisterActive || this.blockException(name = block.getRegistryName().toString()) || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this, (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
                    if (SRPConfig.cystActive) {
                        int meta = block.func_176201_c(iblockstate);
                        boolean bl = flag = this.destroyBlockPos(blockpos, false) || flag;
                        if (SRPConfig.doTileDrops) {
                            this.addToBlockInv(name + ";" + meta);
                        }
                    } else {
                        this.destroyBlockPos(blockpos, SRPConfig.doTileDrops);
                    }
                    ++blocksbroke;
                }
            }
        }
        this.BGrange = Brangeatm;
        this.SkillBGflag = true;
    }

    class AIChargeAttack
    extends EntityAIBase {
        public AIChargeAttack() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            if (EntityFlam.this.targetP != null && !EntityFlam.this.func_70605_aq().func_75640_a() && EntityFlam.this.field_70146_Z.nextInt(7) == 0 && !EntityFlam.this.moveM) {
                return EntityFlam.this.func_174818_b(EntityFlam.this.targetP) > 4.0;
            }
            return false;
        }

        public boolean func_75253_b() {
            return EntityFlam.this.isCharging() && EntityFlam.this.targetP != null && !EntityFlam.this.moveM;
        }

        public void func_75249_e() {
            if (EntityFlam.this.func_70638_az() != null && EntityFlam.this.func_70638_az().func_70089_S()) {
                EntityFlam.this.targetP = EntityFlam.this.func_70638_az().func_180425_c();
            }
            EntityFlam.this.field_70765_h.func_75642_a((double)EntityFlam.this.targetP.func_177958_n() + 0.5, (double)(EntityFlam.this.targetP.func_177956_o() + 2), (double)EntityFlam.this.targetP.func_177952_p() + 0.5, 0.8);
            EntityFlam.this.setCharging(true);
        }

        public void func_75251_c() {
            EntityFlam.this.setCharging(false);
        }

        public void func_75246_d() {
            double d0;
            if (EntityFlam.this.func_70638_az() != null && EntityFlam.this.func_70638_az().func_70089_S()) {
                EntityFlam.this.targetP = EntityFlam.this.func_70638_az().func_180425_c();
            }
            if (EntityFlam.this.targetP != null && (d0 = EntityFlam.this.func_174818_b(EntityFlam.this.targetP)) < 9.0) {
                EntityFlam.this.field_70765_h.func_75642_a((double)EntityFlam.this.targetP.func_177958_n() + 0.5, (double)(EntityFlam.this.targetP.func_177956_o() + 2), (double)EntityFlam.this.targetP.func_177952_p() + 0.5, 0.8);
            }
        }
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            return !EntityFlam.this.func_70605_aq().func_75640_a() && EntityFlam.this.field_70146_Z.nextInt(7) == 0 && !EntityFlam.this.moveM;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityFlam.this);
            int flag = 1;
            double speed = 1.0;
            if (EntityFlam.this.func_70638_az() != null && EntityFlam.this.func_70638_az().func_70089_S()) {
                EntityFlam.this.targetP = EntityFlam.this.func_70638_az().func_180425_c();
            }
            if (EntityFlam.this.targetP != null) {
                blockpos = EntityFlam.this.targetP;
                flag = 2;
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityFlam.this.field_70146_Z.nextInt(15) - 7, EntityFlam.this.field_70146_Z.nextInt(11) - 5, EntityFlam.this.field_70146_Z.nextInt(15) - 7);
                if (flag == 2) {
                    blockpos1 = blockpos.func_177982_a(EntityFlam.this.field_70146_Z.nextInt(6) - 2, EntityFlam.this.field_70146_Z.nextInt(7) - 2, EntityFlam.this.field_70146_Z.nextInt(6) - 2);
                } else if (flag == 3) {
                    blockpos1 = blockpos.func_177982_a(EntityFlam.this.field_70146_Z.nextInt(4) + 3, EntityFlam.this.field_70146_Z.nextInt(5) + 4, EntityFlam.this.field_70146_Z.nextInt(4) + 3);
                }
                if (!EntityFlam.this.field_70170_p.func_175623_d(blockpos1)) continue;
                EntityFlam.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                if (EntityFlam.this.func_70638_az() != null) break;
                EntityFlam.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityFlam vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO && !EntityFlam.this.moveM) {
                double d0 = this.field_75646_b - EntityFlam.this.field_70165_t;
                double d1 = this.field_75647_c - EntityFlam.this.field_70163_u;
                double d2 = this.field_75644_d - EntityFlam.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityFlam.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityFlam.this.field_70159_w *= 0.5;
                    EntityFlam.this.field_70181_x *= 0.5;
                    EntityFlam.this.field_70179_y *= 0.5;
                } else {
                    EntityFlam.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityFlam.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityFlam.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityFlam.this.func_70638_az() == null) {
                        EntityFlam.this.field_70761_aq = EntityFlam.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityFlam.this.field_70159_w, (double)EntityFlam.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityFlam.this.func_70638_az().field_70165_t - EntityFlam.this.field_70165_t;
                        double d5 = EntityFlam.this.func_70638_az().field_70161_v - EntityFlam.this.field_70161_v;
                        EntityFlam.this.field_70761_aq = EntityFlam.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

