/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.BossInfo$Color
 *  net.minecraft.world.BossInfo$Overlay
 *  net.minecraft.world.BossInfoServer
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.ancient;

import com.subspaceparasite.entity.EntityBodyModel;
import com.subspaceparasite.entity.EntityDamage;
import com.subspaceparasite.entity.ai.EntityAIAncientSummon;
import com.subspaceparasite.entity.ai.EntityAIAttackProjectile;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAIFlightLimits;
import com.subspaceparasite.entity.ai.misc.EntityCanShoot;
import com.subspaceparasite.entity.ai.misc.EntityPAncient;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.ancient.EntityOroncoTen;
import com.subspaceparasite.entity.projectile.EntityProjectileAncientball;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityOronco
extends EntityPAncient
implements EntityCanShoot {
    private static final DataParameter<Boolean> URTEN = EntityDataManager.func_187226_a(EntityOronco.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> ULTEN = EntityDataManager.func_187226_a(EntityOronco.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> RATEN = EntityDataManager.func_187226_a(EntityOronco.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> LATEN = EntityDataManager.func_187226_a(EntityOronco.class, (DataSerializer)DataSerializers.field_187198_h);
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityOronco.class, (DataSerializer)DataSerializers.field_187191_a);
    private boolean health80;
    private boolean health60;
    private boolean health40;
    private boolean health20;
    private float damageR = SPAttributes.ORONCO_ATTACK_DAMAGE;
    private int maxY = 20;
    private int minY = 7;
    private EntityAIFlightLimits flightLimit = new EntityAIFlightLimits(this, this.maxY, true);
    private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS).func_186741_a(false);
    private EntityBodyModel leftTendril;
    private EntityBodyModel rightTendril;

    public EntityOronco(World worldIn) {
        super(worldIn);
        this.func_70105_a(4.0f, 4.0f);
        this.type = (byte)62;
        this.borderOrb = -1;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.field_70158_ak = true;
        this.field_70765_h = new AIMoveControl(this);
        this.func_189654_d(true);
        this.health80 = true;
        this.health60 = true;
        this.health40 = true;
        this.health20 = true;
        this.leftTendril = new EntityBodyModel(this, 0.6f, 2.0f, 1.0f, 2.17f, 1.4f, 1, 1, true);
        this.rightTendril = new EntityBodyModel(this, 0.6f, 2.0f, 1.0f, 2.17f, 1.4f, -1, 2, true);
        this.leftTendril.setSkin(1);
        this.rightTendril.setSkin(1);
    }

    @Override
    public int getParasiteIDRegister() {
        return 24;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIMoveRandom());
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIAttackProjectile(this, 60, 20, 3, true));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAncientSummon(this, 20 * SPConfigMobs.oroncoPodCooldown, SPConfigMobs.oroncoPodNumber, new String[]{"subspaceparasite:ancientpod;1;1"}));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFlightLimits(this, this.minY, false));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIFlightAttack(this, 64.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.ORONCO_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.ORONCO_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.ancientFollow);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(URTEN, (Object)true);
        this.field_70180_af.func_187214_a(ULTEN, (Object)true);
        this.field_70180_af.func_187214_a(RATEN, (Object)true);
        this.field_70180_af.func_187214_a(LATEN, (Object)true);
        this.field_70180_af.func_187214_a(VEX_FLAGS, (Object)0);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.rightTendril != null) {
            this.rightTendril.func_70071_h_();
        }
        if (this.leftTendril != null) {
            this.leftTendril.func_70071_h_();
        }
        if (this.field_70122_E && !this.field_70170_p.field_72995_K) {
            this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
        }
        if (!this.field_70170_p.field_72995_K && this.func_70638_az() != null && this.field_70173_aa % 30 == 0) {
            for (EntityLivingBase entitylivingbase : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(3.0, 3.0, 3.0))) {
                if (entitylivingbase == this || entitylivingbase instanceof EntityParasiteBase) continue;
                float f = (float)MathHelper.func_181159_b((double)(entitylivingbase.field_70161_v - this.field_70161_v), (double)(entitylivingbase.field_70165_t - this.field_70165_t));
                EntityDamage damage = new EntityDamage(this.field_70170_p, entitylivingbase.field_70165_t, entitylivingbase.field_70163_u, entitylivingbase.field_70161_v, f, (EntityLivingBase)this, 1.0f, false, 2.5f);
                this.field_70170_p.func_72838_d((Entity)damage);
            }
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    @Override
    public void func_70106_y() {
        if (this.leftTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
            this.leftTendril = null;
        }
        if (this.rightTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
            this.rightTendril = null;
        }
        super.func_70106_y();
    }

    @Override
    protected void func_70619_bc() {
        super.func_70619_bc();
        this.bossInfo.func_186735_a(this.func_110143_aJ() / this.func_110138_aP());
    }

    public void func_96094_a(String name) {
        super.func_96094_a(name);
        this.bossInfo.func_186739_a(this.func_145748_c_());
    }

    public void func_184178_b(EntityPlayerMP player) {
    }

    public void func_184203_c(EntityPlayerMP player) {
        super.func_184203_c(player);
        this.bossInfo.func_186761_b(player);
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        boolean flag = super.func_70097_a(source, amount);
        if (flag && source != DamageSource.field_76380_i) {
            if (source.func_76346_g() instanceof EntityPlayerMP) {
                this.bossInfo.func_186760_a((EntityPlayerMP)source.func_76346_g());
            }
            if ((double)this.func_110138_aP() * 0.8 >= (double)this.func_110143_aJ() && this.health80) {
                this.damageBody(this.field_70146_Z.nextInt(4));
                this.health80 = false;
                this.setParasiteStatus(77);
                this.setWait(30);
            } else if ((double)this.func_110138_aP() * 0.6 >= (double)this.func_110143_aJ() && this.health60) {
                this.damageBody(this.field_70146_Z.nextInt(4));
                this.health60 = false;
                this.setParasiteStatus(77);
                this.setWait(30);
            } else if ((double)this.func_110138_aP() * 0.4 >= (double)this.func_110143_aJ() && this.health40) {
                this.damageBody(this.field_70146_Z.nextInt(4));
                this.health40 = false;
                this.setParasiteStatus(77);
                this.setWait(30);
            } else if ((double)this.func_110138_aP() * 0.2 >= (double)this.func_110143_aJ() && this.health20) {
                this.damageBody(this.field_70146_Z.nextInt(4));
                this.health20 = false;
                this.setParasiteStatus(77);
                this.setWait(30);
            }
        }
        return flag;
    }

    private void damageBody(int in) {
        if (!(this.livingTENUR() || this.livingTENUL() || this.livingTENRA() || this.livingTENLA())) {
            return;
        }
        if (in == 0 && this.livingTENUR()) {
            this.livingTENUR(false);
            EntityOroncoTen ten = new EntityOroncoTen(this.field_70170_p);
            ten.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)ten);
            ten.setSkin(1);
        } else if (in == 1 && this.livingTENUL()) {
            this.livingTENUL(false);
            EntityOroncoTen ten = new EntityOroncoTen(this.field_70170_p);
            ten.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)ten);
            ten.setSkin(1);
        } else if (in == 2 && this.livingTENRA()) {
            this.livingTENRA(false);
            EntityOroncoTen ten = new EntityOroncoTen(this.field_70170_p);
            ten.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)ten);
        } else if (in == 3 && this.livingTENLA()) {
            this.livingTENLA(false);
            EntityOroncoTen ten = new EntityOroncoTen(this.field_70170_p);
            ten.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)ten);
        } else {
            in = (in + 1) % 4;
            this.damageBody(in);
        }
    }

    public int func_184649_cE() {
        return 3;
    }

    public float getDamageR() {
        return this.damageR;
    }

    public float func_70047_e() {
        return 2.1f;
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.ORONCO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ORONCO_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.ORONCO_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    protected float func_70599_aP() {
        return 5.0f;
    }

    public boolean livingTENUR() {
        return (Boolean)this.field_70180_af.func_187225_a(URTEN);
    }

    public boolean livingTENUL() {
        return (Boolean)this.field_70180_af.func_187225_a(ULTEN);
    }

    public boolean livingTENRA() {
        return (Boolean)this.field_70180_af.func_187225_a(RATEN);
    }

    public boolean livingTENLA() {
        return (Boolean)this.field_70180_af.func_187225_a(LATEN);
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74757_a("urten", ((Boolean)this.field_70180_af.func_187225_a(URTEN)).booleanValue());
        compound.func_74757_a("ulten", ((Boolean)this.field_70180_af.func_187225_a(ULTEN)).booleanValue());
        compound.func_74757_a("raten", ((Boolean)this.field_70180_af.func_187225_a(RATEN)).booleanValue());
        compound.func_74757_a("laten", ((Boolean)this.field_70180_af.func_187225_a(LATEN)).booleanValue());
        compound.func_74757_a("healtheight", this.health80);
        compound.func_74757_a("healthsix", this.health60);
        compound.func_74757_a("healthfour", this.health40);
        compound.func_74757_a("healthtwo", this.health20);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("urten", 99)) {
            this.livingTENUR(compound.func_74767_n("urten"));
        }
        if (compound.func_150297_b("ulten", 99)) {
            this.livingTENUL(compound.func_74767_n("ulten"));
        }
        if (compound.func_150297_b("raten", 99)) {
            this.livingTENRA(compound.func_74767_n("raten"));
        }
        if (compound.func_150297_b("laten", 99)) {
            this.livingTENLA(compound.func_74767_n("laten"));
        }
        if (compound.func_150297_b("healtheight", 99)) {
            this.health80 = compound.func_74767_n("healtheight");
        }
        if (compound.func_150297_b("healthsix", 99)) {
            this.health60 = compound.func_74767_n("healthsix");
        }
        if (compound.func_150297_b("healthfour", 99)) {
            this.health40 = compound.func_74767_n("healthfour");
        }
        if (compound.func_150297_b("healthtwo", 99)) {
            this.health20 = compound.func_74767_n("healthtwo");
        }
    }

    public void livingTENUR(boolean in) {
        this.field_70180_af.func_187227_b(URTEN, (Object)in);
    }

    public void livingTENUL(boolean in) {
        this.field_70180_af.func_187227_b(ULTEN, (Object)in);
    }

    public void livingTENRA(boolean in) {
        this.field_70180_af.func_187227_b(RATEN, (Object)in);
    }

    public void livingTENLA(boolean in) {
        this.field_70180_af.func_187227_b(LATEN, (Object)in);
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public boolean func_70617_f_() {
        return false;
    }

    public void func_70091_d(MoverType type, double x, double y, double z) {
        super.func_70091_d(type, x, y, z);
        this.func_145775_I();
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

    public EntityFireball getProj(double accelX, double accelY, double accelZ) {
        this.func_184185_a(SPSounds.ORONCO_SHOOTINGREAL, 2.0f, 1.0f);
        return new EntityProjectileAncientball(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ);
    }

    @Override
    public void playProjSound() {
        this.func_184185_a(SPSounds.ORONCO_SHOOTING, 4.0f, 1.0f);
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            double d2;
            double d1;
            EntityMoveHelper entitymovehelper = EntityOronco.this.func_70605_aq();
            if (!entitymovehelper.func_75640_a()) {
                return true;
            }
            double d0 = entitymovehelper.func_179917_d() - EntityOronco.this.field_70165_t;
            double d3 = d0 * d0 + (d1 = entitymovehelper.func_179919_e() - EntityOronco.this.field_70163_u) * d1 + (d2 = entitymovehelper.func_179918_f() - EntityOronco.this.field_70161_v) * d2;
            return d3 < 1.0 || d3 > 3600.0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            if (EntityOronco.this.func_70638_az() != null) {
                BlockPos blockpos = new BlockPos((Entity)EntityOronco.this);
                int flag = 1;
                double speed = 0.3;
                if (EntityOronco.this.func_70068_e((Entity)EntityOronco.this.func_70638_az()) > 400.0) {
                    blockpos = new BlockPos((Entity)EntityOronco.this.func_70638_az());
                    flag = 2;
                    speed += 0.1;
                } else if (EntityOronco.this.func_70068_e((Entity)EntityOronco.this.func_70638_az()) < 100.0) {
                    blockpos = new BlockPos((Entity)EntityOronco.this.func_70638_az());
                    flag = 3;
                    speed += 0.15;
                }
                for (int i = 0; i < 3; ++i) {
                    BlockPos blockpos1 = blockpos.func_177982_a(EntityOronco.this.field_70146_Z.nextInt(15) - 7, EntityOronco.this.field_70146_Z.nextInt(11) - 5, EntityOronco.this.field_70146_Z.nextInt(15) - 7);
                    if (flag == 2) {
                        blockpos1 = blockpos.func_177982_a(EntityOronco.this.field_70146_Z.nextInt(12) - 2, EntityOronco.this.field_70146_Z.nextInt(10) - 2, EntityOronco.this.field_70146_Z.nextInt(12) - 2);
                    } else if (flag == 3) {
                        blockpos1 = blockpos.func_177982_a(EntityOronco.this.field_70146_Z.nextInt(8) + 3, EntityOronco.this.field_70146_Z.nextInt(7) + 4, EntityOronco.this.field_70146_Z.nextInt(8) + 3);
                    }
                    if (!EntityOronco.this.field_70170_p.func_175623_d(blockpos1)) continue;
                    EntityOronco.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                    if (EntityOronco.this.func_70638_az() == null) {
                        EntityOronco.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                    }
                    break;
                }
            } else {
                Random random = EntityOronco.this.func_70681_au();
                double d0 = EntityOronco.this.field_70165_t + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                double d1 = EntityOronco.this.field_70163_u + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                double d2 = EntityOronco.this.field_70161_v + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                EntityOronco.this.func_70605_aq().func_75642_a(d0, d1, d2, 0.3);
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityOronco vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityOronco.this.field_70165_t;
                double d1 = this.field_75647_c - EntityOronco.this.field_70163_u;
                double d2 = this.field_75644_d - EntityOronco.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityOronco.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityOronco.this.field_70159_w *= 0.5;
                    EntityOronco.this.field_70181_x *= 0.5;
                    EntityOronco.this.field_70179_y *= 0.5;
                } else {
                    EntityOronco.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityOronco.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityOronco.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityOronco.this.func_70638_az() == null) {
                        EntityOronco.this.field_70761_aq = EntityOronco.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityOronco.this.field_70159_w, (double)EntityOronco.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityOronco.this.func_70638_az().field_70165_t - EntityOronco.this.field_70165_t;
                        double d5 = EntityOronco.this.func_70638_az().field_70161_v - EntityOronco.this.field_70161_v;
                        EntityOronco.this.field_70761_aq = EntityOronco.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

