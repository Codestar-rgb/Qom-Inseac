/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvadeDash;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanColony;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityPheon
extends EntityPPreeminent
implements EntityCanColony,
EntityBodyParts,
IRangedAttackMob,
EntityCutomAttack,
EntityCanShoot {
    private EntityBody head;
    private EntityBody middle;

    public EntityPheon(World worldIn) {
        super(worldIn);
        this.func_70105_a(2.0f, 3.6f);
        this.field_70158_ak = true;
        this.type = (byte)63;
        this.borderOrb = -1;
        this.field_70138_W = 1.0f;
        this.head = new EntityBody(this, 2.0f, 7.3f, 1.0f, -2.5f, 0.0f, -1, 1, false, 0.2f);
        this.middle = new EntityBody(this, 1.9f, 3.5f, 1.0f, 0.0f, 3.7f, 1, 2, false, 0.2f);
    }

    @Override
    public int getParasiteIDRegister() {
        return 87;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.15));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackMeleeRangeSwitch(this, 10.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.0, false, 100.0, 5.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackRangedStatus(this, 1.0, 40, 40.0f, false));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackProjectile(this, 60, 10, 3));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SRPConfig.preeminentFollow, true, 3));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvadeDash(this, 40, 2, 4, 5.0, 100));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.PHEON_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.PHEON_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.283);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.PHEON_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.preeminentFollow);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.head.func_70071_h_();
        this.middle.func_70071_h_();
    }

    @Override
    protected void func_70619_bc() {
        super.func_70619_bc();
    }

    public void func_96094_a(String name) {
        super.func_96094_a(name);
    }

    public void func_184178_b(EntityPlayerMP player) {
    }

    public void func_184203_c(EntityPlayerMP player) {
        super.func_184203_c(player);
    }

    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, entityIn.field_70165_t + 1.0, entityIn.field_70163_u + 1.0, entityIn.field_70161_v + 1.0).func_72314_b(5.0, 2.0, 5.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (moblist.size() > 4) {
            axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b(5.0, 3.0, 5.0);
            moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            float luck = (float)(SRPAttributes.TERLA_ATTACK_DAMAGE * 2.0);
            for (EntityLivingBase mob : moblist) {
                if (mob == null || mob instanceof EntityParasiteBase || mob == this) continue;
                EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, 0.0f, (EntityLivingBase)this, luck, false, 3.0f);
                this.field_70170_p.func_72838_d((Entity)damage);
            }
            return true;
        }
        for (EntityLivingBase mob : moblist) {
            if (mob == null || mob instanceof EntityParasiteBase) continue;
            this.func_70652_k((Entity)mob);
        }
        return !moblist.isEmpty();
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        boolean flag = super.func_70097_a(source, amount);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        boolean flag = this.func_70097_a(source, amount);
        return flag;
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    public int func_184649_cE() {
        return 3;
    }

    @Override
    public boolean func_70687_e(PotionEffect potioneffectIn) {
        return potioneffectIn.func_188419_a() == MobEffects.field_76436_u ? false : super.func_70687_e(potioneffectIn);
    }

    public float func_70047_e() {
        return 4.7f;
    }

    protected SoundEvent func_184639_G() {
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.MOBSILENCE;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.pheonOrbEffects, mobs);
        }
        return flag;
    }

    protected float func_70599_aP() {
        return 5.0f;
    }

    @Override
    public void func_70106_y() {
        if (this.head != null) {
            this.field_70170_p.func_72973_f((Entity)this.head);
        }
        if (this.middle != null) {
            this.field_70170_p.func_72973_f((Entity)this.middle);
        }
        super.func_70106_y();
    }

    public void func_184724_a(boolean swingingArms) {
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
            switch (this.field_70146_Z.nextInt(1)) {
                case 0: {
                    this.setSkin(1);
                    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.PHEON_HEALTH * 0.5);
                    this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.PHEON_ATTACK_DAMAGE * 1.5);
                    this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
                }
            }
        }
        return floo;
    }

    @Override
    public Entity getProj(double accelX, double accelY, double accelZ) {
        this.func_184185_a(SRPSounds.DORPA_RANGE, 2.0f, 1.0f);
        Vec3d vec3d = this.func_70676_i(1.0f);
        EntityProjectileHomming entitylargefireball = new EntityProjectileHomming(this.field_70170_p, (EntityLivingBase)this, (Entity)this.func_70638_az(), SRPAttributes.ORONCO_ATTACK_DAMAGE);
        entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
        entitylargefireball.field_70163_u = this.field_70163_u + (double)this.func_70047_e() - 0.2;
        entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
        return entitylargefireball;
    }

    @Override
    public void playProjSound() {
    }

    @Override
    public void skillBreakBlocks() {
        EntityLivingBase target;
        if (this.getBlockH() == 0.0f) {
            return;
        }
        int blocksbroke = 0;
        int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        int offsetT = 0;
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
                for (int j = 1 + offsetT; j <= 8 + offsetT; ++j) {
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

    @Override
    public boolean onlySpawnInside() {
        return false;
    }
}

