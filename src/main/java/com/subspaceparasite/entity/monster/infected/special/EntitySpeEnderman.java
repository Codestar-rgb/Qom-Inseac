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
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.subspaceparasite.entity.monster.infected.special;

import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIEvade;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.misc.EntityPAssimara;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EntitySpeEnderman
extends EntityPAssimara {
    private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", (double)0.15f, 0).func_111168_a(false);
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.func_187226_a(EntitySpeEnderman.class, (DataSerializer)DataSerializers.field_187198_h);
    private int lastCreepySound;
    private int targetChangeTime;
    private int toTeleCool;
    private int spotCool;
    private String tpPlayerName;
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntitySpeEnderman.class, (DataSerializer)DataSerializers.field_187192_b);
    private EntityLivingBase targetedEntity;
    private int pulling;
    private boolean canPull;

    public EntitySpeEnderman(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.6f, 2.9f);
        this.canModRender = 0;
        this.type = (byte)14;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.killcount = -10.0;
        this.field_70138_W = 1.0f;
        this.tpPlayerName = null;
        this.field_70158_ak = true;
        this.canPull = true;
    }

    @Override
    public int getIDSpawn() {
        return 59;
    }

    @Override
    public int getParasiteIDRegister() {
        return 321;
    }

    @Override
    public int canSpawnByIDData() {
        return SPConfigMobs.infendermanCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.2, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 20, 0, 1.35, true, 7, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.MARENDERMAN_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.MARENDERMAN_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.1496);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.MARENDERMAN_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
    }

    @Override
    public void func_70624_b(@Nullable EntityLivingBase entitylivingbaseIn) {
        boolean flag = this.func_70638_az() == null;
        super.func_70624_b(entitylivingbaseIn);
        IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
        if (entitylivingbaseIn == null) {
            this.targetChangeTime = 0;
            this.field_70180_af.func_187227_b(SCREAMING, (Object)false);
            iattributeinstance.func_111124_b(ATTACKING_SPEED_BOOST);
        } else {
            this.targetChangeTime = this.field_70173_aa;
            this.field_70180_af.func_187227_b(SCREAMING, (Object)true);
            if (flag) {
                this.spotCool = SPConfigMobs.infendermansaw;
                if (entitylivingbaseIn instanceof EntityPlayer) {
                    if (this.tpPlayerName != null) {
                        if (!this.tpPlayerName.equals(((EntityPlayer)entitylivingbaseIn).func_70005_c_())) {
                            this.tpPlayerName = ((EntityPlayer)entitylivingbaseIn).func_70005_c_();
                            this.field_70170_p.func_184148_a((EntityPlayer)null, entitylivingbaseIn.field_70169_q, entitylivingbaseIn.field_70167_r, entitylivingbaseIn.field_70166_s, SPSounds.ASSENDERMAN_PORTAL, this.func_184176_by(), 0.3f, 1.0f);
                        }
                    } else {
                        this.tpPlayerName = ((EntityPlayer)entitylivingbaseIn).func_70005_c_();
                        this.field_70170_p.func_184148_a((EntityPlayer)null, entitylivingbaseIn.field_70169_q, entitylivingbaseIn.field_70167_r, entitylivingbaseIn.field_70166_s, SPSounds.ASSENDERMAN_PORTAL, this.func_184176_by(), 0.3f, 1.0f);
                    }
                } else {
                    this.tpPlayerName = null;
                }
            }
            if (!iattributeinstance.func_180374_a(ATTACKING_SPEED_BOOST)) {
                iattributeinstance.func_111121_a(ATTACKING_SPEED_BOOST);
            }
        }
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SCREAMING, (Object)false);
        this.field_70180_af.func_187214_a(TARGET_ENTITY, (Object)0);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 60.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    public void playEndermanSound() {
        if (this.field_70173_aa >= this.lastCreepySound + 400) {
            this.lastCreepySound = this.field_70173_aa;
            if (!this.func_174814_R()) {
                this.field_70170_p.func_184134_a(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v, SoundEvents.field_187533_aW, this.func_184176_by(), 2.5f, 1.0f, false);
            }
        }
    }

    public void func_184206_a(DataParameter<?> key) {
        super.func_184206_a(key);
        if (TARGET_ENTITY.equals(key)) {
            this.targetedEntity = null;
        }
    }

    public void func_70108_f(Entity entityIn) {
        if (this.getTargetedEntity() != null && this.getTargetedEntity() == entityIn) {
            return;
        }
        super.func_70108_f(entityIn);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 2; ++i) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5) * 2.0, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5) * 2.0, new int[0]);
            }
        } else {
            if (this.func_70638_az() != null && this.field_70173_aa % 20 == 0 && this.func_70068_e((Entity)this.func_70638_az()) > 4.0 && this.field_70146_Z.nextInt(SPConfigMobs.infendermantelefreq) == 0) {
                this.teleportRandomly();
            }
            if (this.spotCool >= 0) {
                --this.spotCool;
            }
            if (this.toTeleCool >= 0) {
                --this.toTeleCool;
            }
            if (this.srpTicks == 10 && this.func_70644_a(SPPotions.RAGE_E)) {
                this.spotCool = 0;
                this.toTeleCool = 0;
            }
            if (!this.canPull) {
                --this.pulling;
                if (this.pulling == 0) {
                    this.canPull = true;
                }
            }
            if (this.func_70638_az() != null) {
                if (!this.func_70638_az().func_70089_S()) {
                    this.func_70624_b(null);
                    this.setTargetedEntity(0);
                } else if (this.func_70685_l((Entity)this.func_70638_az()) && this.func_70068_e((Entity)this.func_70638_az()) > 0.0 && this.canPull && this.getTargetedEntity() != null) {
                    this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 1, false, false));
                    this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 1, false, false));
                    this.lookAt((Entity)this.getTargetedEntity());
                    this.attackEntityAsMobMinimum(this.func_70638_az(), 0.02f);
                    this.setParasiteStatus(3);
                    ++this.pulling;
                    if (this.pulling > 200 || this.func_70068_e((Entity)this.func_70638_az()) > 9.0) {
                        this.setTargetedEntity(0);
                        this.canPull = false;
                    }
                } else {
                    this.setTargetedEntity(0);
                }
            } else {
                this.setTargetedEntity(0);
            }
        }
        this.field_70703_bu = false;
        if (this.getTargetedEntity() != null && this.func_70068_e((Entity)this.getTargetedEntity()) > 0.0) {
            EntityLivingBase target = this.getTargetedEntity();
            target.func_184210_p();
            double str = 0.3;
            double deltaX = this.field_70165_t - target.field_70165_t;
            double deltaY = this.field_70163_u - target.field_70163_u;
            double deltaZ = this.field_70161_v - target.field_70161_v;
            str = 0.13;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (distance == 0.0) {
                return;
            }
            target.field_70159_w += (deltaX /= distance) * str;
            target.field_70181_x += (deltaY /= distance) * str;
            target.field_70179_y += (deltaZ /= distance) * str;
        }
    }

    @Override
    protected void handleParasiteStatus() {
        byte k = this.getParasiteStatus();
        if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2 || k == 3) {
            if (this.getAttackCooldownAni() != 0) {
                int i = this.getAttackCooldownAni() - 1;
                this.setAttackCooldownAni(i);
            }
            if (k == 1 || k == 2 || k == 3) {
                if (this.func_70638_az() != null) {
                    if (!this.func_70638_az().func_70089_S()) {
                        this.func_70624_b(null);
                        this.setParasiteStatus(0);
                    } else if (!this.canPull) {
                        this.setParasiteStatus(Math.min(k, 2));
                    }
                } else {
                    this.setParasiteStatus(0);
                    this.func_70624_b(null);
                }
            }
        }
    }

    public void setTargetedEntity(int entityId) {
        if (!this.canPull && entityId != 0) {
            return;
        }
        this.pulling = 0;
        this.canPull = true;
        this.field_70180_af.func_187227_b(TARGET_ENTITY, (Object)entityId);
    }

    public boolean hasTargetedEntity() {
        if (!this.canPull) {
            return false;
        }
        return (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
    }

    public EntityLivingBase getTargetedEntity() {
        if (!this.hasTargetedEntity()) {
            return null;
        }
        if (this.field_70170_p.field_72995_K) {
            if (this.targetedEntity != null) {
                return this.targetedEntity;
            }
            Entity entity = this.field_70170_p.func_73045_a(((Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY)).intValue());
            if (entity instanceof EntityLivingBase) {
                this.targetedEntity = (EntityLivingBase)entity;
                return this.targetedEntity;
            }
            return null;
        }
        return this.func_70638_az();
    }

    protected boolean teleportRandomly() {
        if (this.spotCool > 0) {
            return false;
        }
        if (this.getTargetedEntity() != null) {
            return false;
        }
        if (this.getTargetedEntity() != null) {
            return false;
        }
        double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
        double d1 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(64) - 32);
        double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
        if (this.func_70638_az() != null && this.func_70638_az().func_70011_f(d0, d1, d2) < 10.0) {
            return false;
        }
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
        if (this.func_70638_az() != null && this.func_70638_az().func_70011_f(d1, d2, d3) < 10.0) {
            return false;
        }
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
            this.field_70170_p.func_184148_a((EntityPlayer)null, this.field_70169_q, this.field_70167_r, this.field_70166_s, SPSounds.ASSENDERMAN_PORTAL, this.func_184176_by(), 1.0f, 1.0f);
            this.func_184185_a(SPSounds.ASSENDERMAN_PORTAL, 1.0f, 1.0f);
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

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        this.spotCool = 0;
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
        if (source.func_76363_c() && this.field_70146_Z.nextInt(SPConfigMobs.infendermantelefreq) == 0) {
            this.teleportRandomly();
        }
        return flag;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            if (!this.hasTargetedEntity()) {
                this.setTargetedEntity(entityIn.func_145782_y());
                ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 3, false, false));
            }
            if (this.field_70146_Z.nextDouble() < (double)SPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
                SPPotions.applyStackPotion(SPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
            }
            if (this.field_70146_Z.nextInt(SPConfigMobs.infendermantelefreq) == 0) {
                this.teleportRandomly();
            }
        }
        return flag;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        super.func_70074_a(entityLivingIn);
        this.tpPlayerName = null;
    }

    public boolean isScreaming() {
        return (Boolean)this.field_70180_af.func_187225_a(SCREAMING);
    }

    public float func_70047_e() {
        return this.field_70131_O * 0.88f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ASSENDERMAN_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.ASSENDERMAN_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.ASSENDERMAN_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.field_187939_hm;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), 0.15f, 1.0f);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        return floo;
    }
}

