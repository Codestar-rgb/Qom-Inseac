/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected.head;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EntityInfEndermanHead
extends EntityPInfected {
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.func_187226_a(EntityInfEndermanHead.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", (double)0.15f, 0).func_111168_a(false);
    private int lastCreepySound;
    private int targetChangeTime;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int ally;
    private EntityParasiteBase toTele;

    public EntityInfEndermanHead(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.7f, 0.9f);
        this.killcount = -10.0;
        this.attackSpeedT = 15;
    }

    @Override
    public int getParasiteIDRegister() {
        return 69;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infendermanCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISkill(this, 40, 100, 3, true, 14));
        this.setskillLeapValues(0.7f, 2.5, 0);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
        this.field_70715_bh.func_75776_a(5, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityInhooM.class, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFENDERMAN_HEADHEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFENDERMAN_HEADDAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
    }

    @Override
    public void func_70624_b(@Nullable EntityLivingBase entitylivingbaseIn) {
        super.func_70624_b(entitylivingbaseIn);
        IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
        if (entitylivingbaseIn == null) {
            this.targetChangeTime = 0;
            this.field_70180_af.func_187227_b(SCREAMING, (Object)false);
            iattributeinstance.func_111124_b(ATTACKING_SPEED_BOOST);
        } else {
            this.targetChangeTime = this.field_70173_aa;
            this.field_70180_af.func_187227_b(SCREAMING, (Object)true);
            if (!iattributeinstance.func_180374_a(ATTACKING_SPEED_BOOST)) {
                iattributeinstance.func_111121_a(ATTACKING_SPEED_BOOST);
            }
        }
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SCREAMING, (Object)false);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 2; ++i) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5) * 2.0, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5) * 2.0, new int[0]);
            }
        } else {
            if (this.func_70638_az() != null && this.field_70173_aa % 20 == 0 && this.func_70068_e((Entity)this.func_70638_az()) > 4.0 && this.field_70146_Z.nextInt(SRPConfigMobs.infendermantelefreq * 2) == 0 && !this.teleportAllies()) {
                this.teleportRandomly();
            }
            if (SRPConfigSystems.disloGiveBodies && this.func_70089_S() && this.srpTicks == 10 && SRPSaveData.get(this.field_70170_p, 44).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 20) >= 1) {
                ParasiteEventEntity.spawnNext(this, new EntityInfEnderman(this.field_70170_p), true, false);
                return;
            }
        }
        if (this.ally > 0 && !this.field_70170_p.field_72995_K) {
            ++this.ally;
            this.teleportAlly();
        }
        this.field_70703_bu = false;
    }

    protected boolean teleportAllies() {
        if (this.ally > 0 || !SRPConfigMobs.infendermanteleally) {
            return false;
        }
        EntityLivingBase target = this.func_70638_az();
        if (target == null) {
            return false;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(64.0);
        List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        for (EntityParasiteBase mob : moblist) {
            if (!(mob instanceof EntityMudo) || mob.func_70638_az() != null || !this.teleportToEntity((Entity)mob, 1.0)) continue;
            this.setCoordTarget(target.field_70165_t, target.field_70163_u, target.field_70161_v);
            this.toTele = mob;
            this.setWorkTask(false);
            this.ally = 1;
            return true;
        }
        for (EntityParasiteBase mob : moblist) {
            if (!(mob instanceof EntityPInfected) || mob.field_70130_N > 0.7f || mob.field_70131_O > 0.9f || mob == this || mob.func_70638_az() != null || mob.getParasiteType() > 15 || mob instanceof EntityCanMelt && ((EntityCanMelt)((Object)mob)).isMelting() || !this.teleportToEntity((Entity)mob, 1.0)) continue;
            this.setCoordTarget(target.field_70165_t, target.field_70163_u, target.field_70161_v);
            this.toTele = mob;
            this.setWorkTask(false);
            this.ally = 1;
            return true;
        }
        return false;
    }

    private void teleportAlly() {
        block8: {
            block7: {
                EntityLivingBase target;
                if (this.ally < 8) {
                    return;
                }
                if (this.toTele == null) break block7;
                if (!this.toTele.func_70089_S()) {
                    this.toTele = null;
                    this.ally = 0;
                    this.setWorkTask(true);
                    boolean flag2 = false;
                    for (int lag2 = 10; !flag2 && lag2 > 0; --lag2) {
                        flag2 = this.teleportToPos(this.targetX, this.targetY, this.targetZ, 8.0);
                    }
                    return;
                }
                boolean flag1 = false;
                for (int lag1 = 10; !flag1 && lag1 > 0 && !(flag1 = this.teleportToPos(this.targetX, this.targetY, this.targetZ, 8.0)); --lag1) {
                }
                if (!flag1) break block8;
                boolean flag2 = false;
                int lag2 = 12;
                this.toTele.func_82149_j((Entity)this);
                if (this.toTele.getParasiteIDRegister() != 59 && this.toTele.getParasiteIDRegister() != 69) {
                    this.toTele.func_70097_a(DamageSource.field_76379_h, SRPConfigMobs.infendermanTeleDamage);
                }
                if (this.func_70027_ad() && this.field_70146_Z.nextBoolean()) {
                    this.toTele.func_70015_d(8);
                }
                this.field_70170_p.func_184148_a((EntityPlayer)null, this.toTele.field_70169_q, this.toTele.field_70167_r, this.toTele.field_70166_s, SRPSounds.INFECTEDENDERMAN_PORTAL, this.func_184176_by(), 1.0f, 1.0f);
                flag2 = true;
                if (!flag2 || (target = this.func_70638_az()) == null || !target.func_70089_S()) break block8;
                this.toTele.func_70624_b(target);
                break block8;
            }
            boolean flag2 = false;
            for (int lag2 = 10; !flag2 && lag2 > 0; --lag2) {
                flag2 = this.teleportToPos(this.targetX, this.targetY, this.targetZ, 8.0);
            }
        }
        this.setWorkTask(true);
        this.ally = 0;
        this.toTele = null;
    }

    protected boolean teleportRandomly() {
        double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
        double d1 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(64) - 32);
        double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity in, double dis) {
        double d1 = in.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * dis;
        double d2 = in.field_70163_u + (double)(this.field_70146_Z.nextInt(16) - 8) * dis;
        double d3 = in.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * dis;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportToPos(double x, double y, double z, double dis) {
        double d1 = x + (this.field_70146_Z.nextDouble() - 0.5) * dis;
        double d2 = y + (double)(this.field_70146_Z.nextInt(16) - 8) * dis;
        double d3 = z + (this.field_70146_Z.nextDouble() - 0.5) * dis;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportEntityTo(EntityParasiteBase in, double x, double y, double z, double dis) {
        double d1 = x + (this.field_70146_Z.nextDouble() - 0.5) * dis;
        double d2 = y + (double)(this.field_70146_Z.nextInt(16) - 8) * dis;
        double d3 = z + (this.field_70146_Z.nextDouble() - 0.5) * dis;
        return this.teleportTo(in, d1, d2, d3);
    }

    private boolean teleportTo(double x, double y, double z) {
        EnderTeleportEvent event = new EnderTeleportEvent((EntityLivingBase)this, x, y, z, 0.0f);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return false;
        }
        boolean flag = this.func_184595_k(event.getTargetX(), event.getTargetY(), event.getTargetZ());
        if (flag) {
            this.field_70170_p.func_184148_a((EntityPlayer)null, this.field_70169_q, this.field_70167_r, this.field_70166_s, SRPSounds.INFECTEDENDERMAN_PORTAL, this.func_184176_by(), 1.0f, 1.0f);
            this.func_184185_a(SRPSounds.INFECTEDENDERMAN_PORTAL, 1.0f, 1.0f);
        }
        return flag;
    }

    public boolean func_184595_k(double x, double y, double z) {
        double d0 = this.field_70165_t;
        double d1 = this.field_70163_u;
        double d2 = this.field_70161_v;
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
        boolean flag = false;
        BlockPos blockpos = new BlockPos((Entity)this);
        World world = this.field_70170_p;
        Random random = this.func_70681_au();
        if (world.func_175667_e(blockpos)) {
            boolean flag1 = false;
            while (!flag1 && blockpos.func_177956_o() > 0) {
                BlockPos blockpos1 = blockpos.func_177977_b();
                IBlockState iblockstate = world.func_180495_p(blockpos1);
                if (iblockstate.func_185904_a().func_76230_c() || iblockstate.func_177230_c() instanceof BlockLiquid) {
                    flag1 = true;
                    continue;
                }
                this.field_70163_u -= 1.0;
                blockpos = blockpos1;
            }
            if (flag1) {
                this.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (world.func_184144_a((Entity)this, this.func_174813_aQ()).isEmpty() && !world.func_72953_d(this.func_174813_aQ())) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.func_70634_a(d0, d1, d2);
            return false;
        }
        int i = 128;
        for (int j = 0; j < 128; ++j) {
            double d6 = (double)j / 127.0;
            float f = (random.nextFloat() - 0.5f) * 0.2f;
            float f1 = (random.nextFloat() - 0.5f) * 0.2f;
            float f2 = (random.nextFloat() - 0.5f) * 0.2f;
            double d3 = d0 + (this.field_70165_t - d0) * d6 + (random.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            double d4 = d1 + (this.field_70163_u - d1) * d6 + random.nextDouble() * (double)this.field_70131_O;
            double d5 = d2 + (this.field_70161_v - d2) * d6 + (random.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            world.func_175688_a(EnumParticleTypes.PORTAL, d3, d4, d5, (double)f, (double)f1, (double)f2, new int[0]);
        }
        if (this instanceof EntityCreature) {
            this.func_70661_as().func_75499_g();
        }
        return true;
    }

    private boolean teleportTo(EntityParasiteBase in, double x, double y, double z) {
        EnderTeleportEvent event = new EnderTeleportEvent((EntityLivingBase)this, x, y, z, 0.0f);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return false;
        }
        boolean flag = in.func_184595_k(event.getTargetX(), event.getTargetY(), event.getTargetZ());
        if (flag) {
            in.field_70170_p.func_184148_a((EntityPlayer)null, in.field_70169_q, in.field_70167_r, in.field_70166_s, SoundEvents.field_187534_aX, in.func_184176_by(), 1.0f, 1.0f);
            in.func_184185_a(SoundEvents.field_187534_aX, 1.0f, 1.0f);
        }
        return flag;
    }

    private void setCoordTarget(double x, double y, double z) {
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        if (this.func_180431_b(source)) {
            return false;
        }
        if (source instanceof EntityDamageSourceIndirect) {
            for (int i = 0; i < 64; ++i) {
                if (!this.teleportRandomly()) continue;
                return true;
            }
            return false;
        }
        boolean flag = super.func_70097_a(source, amount);
        if (source.func_76363_c() && this.field_70146_Z.nextInt(SRPConfigMobs.infendermantelefreq * 2) == 0 && !this.teleportAllies()) {
            this.teleportRandomly();
        }
        return flag;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (entityIn instanceof EntityInhooM && entityIn.func_70089_S() && this.func_70089_S()) {
            ParasiteEventEntity.spawnNext(this, new EntityInfEnderman(this.field_70170_p), true, false);
            ((EntityParasiteBase)entityIn).particleStatus((byte)7);
            entityIn.func_70106_y();
            return true;
        }
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            if (this.field_70146_Z.nextDouble() < (double)SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
                SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
            }
            if (this.field_70146_Z.nextInt(SRPConfigMobs.infendermantelefreq * 2) == 0 && !this.teleportAllies()) {
                this.teleportRandomly();
            }
        }
        return flag;
    }

    public void func_70110_aj() {
    }

    public float func_70047_e() {
        return 0.8f;
    }

    @Override
    public boolean func_70686_a(Class<? extends EntityLivingBase> cls) {
        if (cls == EntityPlayer.class || cls == EntityPlayerMP.class) {
            return true;
        }
        String name = null;
        try {
            name = EntityList.func_191306_a(cls).toString();
        }
        catch (Exception e) {
            return true;
        }
        if (name == null) {
            return true;
        }
        if (name.contains("srparasites") && cls != EntityInhooM.class) {
            return false;
        }
        return !SRPConfig.mobAttackingFull || !ParasiteEventEntity.checkName(name, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
    }

    public boolean isScreaming() {
        return (Boolean)this.field_70180_af.func_187225_a(SCREAMING);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        super.func_180430_e(distance, damageMultiplier * 0.3f);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SRPSounds.SMALL_STEPS;
    }
}

