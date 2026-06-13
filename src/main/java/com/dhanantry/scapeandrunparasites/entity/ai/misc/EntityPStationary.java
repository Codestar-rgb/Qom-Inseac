/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPStationary
extends EntityPMalleable {
    protected double buried;
    protected double buriedT;
    protected boolean up = false;
    protected boolean onlyPeek;
    protected int peeking;
    protected boolean relocate;
    protected int delayBuried;

    public EntityPStationary(World worldIn) {
        super(worldIn);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.jumpT);
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, true, null, 1.0, 1.0f));
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, true, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, 1.0, 1.0f));
        } else {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, true, null, 1.0, 1.0f));
        }
        this.onlyPeek = false;
        this.relocate = false;
        this.valueEvDeath = SRPConfig.turretLoosingEPValue;
        this.delayBuried = 0;
        this.setScentHPMultiplier(1.5f);
    }

    @Override
    public void func_70636_d() {
        if (this.func_175446_cd()) {
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            this.field_70165_t = this.field_70169_q;
            this.field_70161_v = this.field_70166_s;
            if (!this.field_70122_E) {
                this.field_70181_x -= 0.5;
            }
            if (SRPConfigSystems.useEvolution && this.srpTicks == 20 && SRPConfigSystems.damageStationaryRS && ParasiteEventEntity.getRSchance(this.field_70170_p) == 0.0) {
                this.func_70097_a(DamageSource.field_76380_i, 1.0f);
            }
        }
        this.buried();
        this.liquidLeap = -100;
        super.func_70636_d();
        if (this.onlyPeek && this.field_70173_aa > 100) {
            if (this.func_70638_az() != null) {
                if (!this.func_70638_az().func_70089_S()) {
                    this.func_70624_b(null);
                } else {
                    this.peeking = 0;
                }
            } else {
                ++this.peeking;
                if (this.peeking > 100) {
                    this.field_70170_p.func_72960_a((Entity)this, (byte)51);
                    this.up = true;
                }
            }
        }
        this.retreat(true);
    }

    public void setPeek(boolean in) {
        this.onlyPeek = in;
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        if (this.buried > 0.0 || this.peeking > 50) {
            return;
        }
        super.func_70624_b(entitylivingbaseIn);
    }

    public boolean buried() {
        if (this.buried > 0.0) {
            IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
            if (state.func_177230_c() != Blocks.field_150350_a) {
                int id = Block.func_176210_f((IBlockState)state);
                for (int i = 0; i < 10; ++i) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, new int[]{id});
                }
            }
            --this.delayBuried;
            if (this.delayBuried > 0) {
                return true;
            }
            if (!this.up) {
                this.buried -= this.getBuriedSpeed();
            }
            return true;
        }
        if (this.getParasiteStatus() == 3) {
            this.setParasiteStatus(0);
        } else if (!this.up) {
            this.buried = -0.1;
        }
        return false;
    }

    public void setBuried() {
        this.setParasiteStatus(3);
        this.buried = this.buriedT;
    }

    protected void retreat(boolean dead) {
        if (this.up) {
            this.buried += this.getBuriedSpeed();
            this.setParasiteStatus(3);
            if (this.buried > this.buriedT && !this.field_70170_p.field_72995_K) {
                if (dead) {
                    this.func_70106_y();
                } else if (this.func_70638_az() != null && ParasiteEventEntity.teleportDigging(this, 10.0f, this.func_70638_az().func_180425_c(), 5, 2)) {
                    this.field_70170_p.func_72960_a((Entity)this, (byte)52);
                    this.up = false;
                }
            }
        }
    }

    public double getBuriedSpeed() {
        return 0.08;
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        EntityLivingBase attacker;
        if (this.field_70170_p.field_72995_K) {
            return super.func_70097_a(source, amount);
        }
        if (source.func_76346_g() instanceof EntityLivingBase && (attacker = (EntityLivingBase)source.func_76346_g()).func_70644_a(SRPPotions.KILLNEX_E)) {
            int amp = attacker.func_70660_b(SRPPotions.KILLNEX_E).func_76458_c();
            float totalRed = MathHelper.func_76131_a((float)(SRPConfigSystems.parasiteKillingReduction * ((float)amp + 1.0f)), (float)0.0f, (float)0.95f);
            float reduced = amount * (1.0f - totalRed);
            return super.func_70097_a(source, Math.max(0.0f, reduced));
        }
        boolean flag = super.func_70097_a(source, amount);
        if (flag && source == DamageSource.field_76368_d && this.getBlockH() != 0.0f) {
            this.skillBreakBlocks();
        }
        return flag;
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        if (!SRPConfigSystems.disloSameVersionDyeing || !this.disloNumberTwentytwo) {
            return super.onDeathDislo(cause);
        }
        boolean flag = super.onDeathDislo(cause);
        if (!flag && cause.func_76346_g() instanceof EntityLivingBase) {
            int count = SRPSaveData.get(this.field_70170_p, 27).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 22);
            EntityLivingBase target = (EntityLivingBase)cause.func_76346_g();
            if (count >= 1) {
                target.func_184596_c(SRPPotions.KILLPRI_E);
                target.func_184596_c(SRPPotions.KILLADA_E);
                target.func_184596_c(SRPPotions.KILLPUR_E);
                target.func_184596_c(SRPPotions.KILLFER_E);
                target.func_184596_c(SRPPotions.KILLCRU_E);
                SRPPotions.applyStackPotion(SRPPotions.KILLNEX_E, target, 1200, count);
            }
        }
        return flag;
    }

    @SideOnly(value=Side.CLIENT)
    public double getFloorTimer() {
        if (this.func_175446_cd()) {
            return -0.1;
        }
        return this.buried;
    }

    public void func_70108_f(Entity entityIn) {
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        if (entityIn instanceof EntityFishHook) {
            entityIn.func_70106_y();
        }
    }

    protected void func_85033_bc() {
        List list = this.field_70170_p.func_175674_a((Entity)this, this.func_174813_aQ().func_186662_g(1.1), EntitySelectors.field_180132_d);
        if (!list.isEmpty()) {
            int i = this.field_70170_p.func_82736_K().func_180263_c("maxEntityCramming");
            if (i > 0 && list.size() > i - 1 && this.field_70146_Z.nextInt(4) == 0) {
                int j = 0;
                for (int k = 0; k < list.size(); ++k) {
                    if (((Entity)list.get(k)).func_184218_aH()) continue;
                    ++j;
                }
                if (j > i - 1) {
                    this.func_70097_a(DamageSource.field_191291_g, 6.0f);
                }
            }
            for (int l = 0; l < list.size(); ++l) {
                Entity entity = (Entity)list.get(l);
                this.func_82167_n(entity);
            }
        }
    }

    public void func_70653_a(Entity entityIn, float strength, double xRatio, double zRatio) {
    }

    public boolean func_96092_aw() {
        return false;
    }

    public void func_70690_d(PotionEffect potioneffectIn) {
        if (potioneffectIn.func_188419_a() == MobEffects.field_188424_y) {
            return;
        }
        super.func_70690_d(potioneffectIn);
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74757_a("parasitedepeek", this.onlyPeek);
        compound.func_74757_a("parasiteuppp", this.up);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("parasitedepeek", 99)) {
            this.setPeek(compound.func_74767_n("parasitedepeek"));
        }
        if (compound.func_150297_b("parasiteuppp", 99)) {
            this.up = compound.func_74767_n("parasiteuppp");
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 50) {
            this.buried = this.buriedT;
        } else if (id == 51) {
            this.up = true;
        } else if (id == 52) {
            this.onlyPeek = true;
        } else {
            super.func_70103_a(id);
        }
    }

    @Override
    public void skillBreakBlocks() {
        if (this.getBlockH() == 0.0f) {
            return;
        }
        int blocksbroke = 0;
        int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        int offsetT = -1;
        for (int k2 = -1 * this.BGrange; k2 <= this.BGrange; ++k2) {
            for (int l2 = -1 * this.BGrange; l2 <= this.BGrange; ++l2) {
                for (int j = 1 + offsetT; j <= this.BGheight + offsetT; ++j) {
                    String name;
                    double i3 = l1 + (double)k2;
                    double k = i1 + j;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    Block block = iblockstate.func_177230_c();
                    float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                    if (!(bHard <= this.getBlockH()) || !(bHard >= 0.0f) || block instanceof IMetaName && block != SRPBlocks.ParasiteCanister || block == SRPBlocks.BiomeHeart || block == SRPBlocks.ColonyHeart || block == SRPBlocks.ParasiteRubbleDense || block == SRPBlocks.ParasiteCanisterActive || block == SRPBlocks.dodN || this.blockException(name = block.getRegistryName().toString()) || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this, (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
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

    public boolean func_70067_L() {
        return true;
    }
}

