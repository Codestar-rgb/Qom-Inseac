/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAreaEffectCloud
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.init.MobEffects
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.adapted;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.EntityRemain;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIAttackRangedStatus;
import com.subspaceparasite.entity.ai.EntityAIBlockResidue;
import com.subspaceparasite.entity.ai.EntityAIEvade;
import com.subspaceparasite.entity.ai.EntityAIGetFollowers;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityCanSummon;
import com.subspaceparasite.entity.ai.misc.EntityPAdapted;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.EntityTendril;
import com.subspaceparasite.entity.monster.primitive.EntityCanra;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.SPPacketEntityBodyDead;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCanraAdapted
extends EntityPAdapted
implements EntityCanSummon,
EntityBodyParts,
IRangedAttackMob {
    private EntityBody leftTendril;
    private EntityBody rightTendril;
    private float leftTendrilHealth;
    private float rightTendrilHealth;
    private int totalP = SPConfigMobs.canraadaptedtotalactivemobs;
    private int actualP = 0;
    private int[] mobID = new int[this.totalP + SPConfigMobs.canraadaptedlimit];
    private int[] mobPT = new int[this.totalP + SPConfigMobs.canraadaptedlimit];
    int vomit;
    private int limit;
    private int border;
    private boolean skillSummon;

    public EntityCanraAdapted(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.3f, 3.3f);
        this.field_70138_W = 1.0f;
        for (int i = 0; i < this.mobID.length; ++i) {
            this.mobID[i] = -777;
        }
        this.leftTendril = new EntityBody(this, 0.6f, 1.7f, 1.0f, 0.9f, 1.8f, 1, 1, true);
        this.rightTendril = new EntityBody(this, 0.6f, 1.7f, 1.0f, 0.9f, 1.8f, -1, 2, true);
        this.leftTendrilHealth = (float)((double)this.func_110138_aP() * SPConfig.tendrilHealth);
        this.rightTendrilHealth = (float)((double)this.func_110138_aP() * SPConfig.tendrilHealth);
        this.skillSummon = false;
    }

    @Override
    public int getParasiteIDRegister() {
        return 53;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.11));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 20 * SPConfigMobs.canraadaptedsummoningcooldown, 16, true, 1));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackMeleeRangeSwitch(this, 5.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackRangedStatus(this, 1.0, 140, 9.0f, false));
        if (SPConfig.parasiteGenResidue) {
            this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIBlockResidue(this, 2));
        }
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 3, 32));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 25, 10, 4.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.CANRA_HEALTH + SPAttributes.CANRA_A_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.CANRA_ARMOR + SPAttributes.CANRA_A_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.29);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.CANRA_KD_RESISTANCE + SPAttributes.CANRA_A_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.CANRA_ATTACK_DAMAGE + SPAttributes.CANRA_A_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.adaptedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.leftTendrilHealth > 0.0f) {
            this.leftTendril.func_70071_h_();
        }
        if (this.rightTendrilHealth > 0.0f) {
            this.rightTendril.func_70071_h_();
        }
        if (!this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(2) == 0 && this.field_70173_aa % 60 == 0) {
            boolean flag = false;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(16.0);
            List moblist = this.field_70170_p.func_72872_a(EntityRemain.class, axisalignedbb);
            for (EntityRemain mob : moblist) {
                if (mob.getActive()) continue;
                mob.setPlus(SPConfigMobs.canraadaptedremainplus);
                mob.setHealth(SPConfigMobs.canraadaptedremainhealth);
                flag = true;
            }
            if (flag) {
                this.func_184185_a(SPSounds.ACANRA_SPECIAL, 3.0f, 1.0f);
                this.particleStatus((byte)8);
            }
        }
        if (this.field_70170_p.field_72995_K && this.vomit > 0) {
            --this.vomit;
            for (int i = 0; i < 6; ++i) {
                Vec3d vec3d = this.func_70676_i(1.0f);
                double bon = 1.2;
                double offsetX = this.field_70165_t + vec3d.field_72450_a * bon;
                double offsetY = this.field_70163_u + (double)this.func_70047_e() - 0.2;
                double offsetZ = this.field_70161_v + vec3d.field_72449_c * bon;
                double motionX = (double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f))) * 0.2;
                double motionZ = (double)MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * 0.2;
                double motionY = 0.01 + this.field_70146_Z.nextDouble() * 0.1;
                double spreadFactor = 0.25;
                this.spawnParticles(SPEnumParticle.GCLOUD, 123, 0, 196, offsetX, offsetY, offsetZ, motionX += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor, motionY, motionZ += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor);
            }
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && entityIn instanceof EntityLivingBase) {
            switch (this.getSkin()) {
                case 5: {
                    SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
                    break;
                }
                case 6: {
                    SPPotions.applyStackPotion(SPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
                }
            }
        }
        return flag;
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        boolean flag = this.func_70097_a(source, amount);
        if (!flag) {
            return false;
        }
        if (this.leftTendril.getId() == id) {
            this.leftTendrilHealth -= amount;
            if (this.leftTendrilHealth <= 0.0f) {
                EntityTendril tendril = new EntityTendril(this.field_70170_p);
                tendril.setSkin(3);
                tendril.func_82149_j(this.leftTendril);
                this.field_70170_p.func_72838_d((Entity)tendril);
                this.field_70170_p.func_72973_f((Entity)this.leftTendril);
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
                this.cutResistances(SPConfig.adaptedPointDamCap / 2);
                SPMain.network.sendToAll((IMessage)new SPPacketEntityBodyDead(this.func_145782_y(), id));
            }
        } else if (this.rightTendril.getId() == id) {
            this.rightTendrilHealth -= amount;
            if (this.rightTendrilHealth <= 0.0f) {
                EntityTendril tendril = new EntityTendril(this.field_70170_p);
                tendril.setSkin(3);
                tendril.func_82149_j(this.rightTendril);
                this.field_70170_p.func_72838_d((Entity)tendril);
                this.field_70170_p.func_72973_f((Entity)this.rightTendril);
                this.field_70170_p.func_72960_a((Entity)this, (byte)22);
                this.cutResistances(SPConfig.adaptedPointDamCap / 2);
                SPMain.network.sendToAll((IMessage)new SPPacketEntityBodyDead(this.func_145782_y(), id));
            }
        }
        return flag;
    }

    @Override
    public void setBodyPartDead(int id) {
        if (this.leftTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        } else if (this.rightTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
        }
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
        }
    }

    @Override
    public int getTotalParasites() {
        return this.totalP;
    }

    @Override
    public int getActualParasites() {
        return this.actualP;
    }

    @Override
    public void setActualParasites(int i) {
        this.actualP += i;
    }

    @Override
    public void addID(int id, int points) {
        for (int i = 0; i < this.mobID.length; ++i) {
            if (this.mobID[i] != -777) continue;
            this.mobID[i] = id;
            this.mobPT[i] = points;
            return;
        }
    }

    @Override
    public int IDable() {
        int flag = 0;
        for (int i = 0; i < this.mobID.length; ++i) {
            if (this.mobID[i] != -777) continue;
            ++flag;
        }
        if (flag > this.totalP) {
            flag = this.totalP;
        }
        return flag;
    }

    @Override
    public void checkID() {
        for (int i = 0; i < this.mobID.length; ++i) {
            Entity flag;
            if (this.mobID[i] <= 0 || (flag = this.field_70170_p.func_73045_a(this.mobID[i])) != null) continue;
            this.mobID[i] = -777;
            int negative = this.mobPT[i] * -1;
            this.setActualParasites(negative);
        }
    }

    @Override
    public int[] getIDList() {
        return null;
    }

    @Override
    public int[] getPointList() {
        return null;
    }

    public float func_70047_e() {
        return 1.8f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityCanra(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ACANRA_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ACANRA_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.ACANRA_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SPConfigMobs.canraadaptedOrbEffects, mobs);
        }
        return flag;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SPSounds.HEAVY_STEPS_TWO, 0.15f, 1.0f);
    }

    @Override
    public void func_70106_y() {
        if (this.leftTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        }
        if (this.rightTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
        }
        super.func_70106_y();
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SPConfig.variantChance || this.phaseCreated >= SPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(3)) {
                case 0: {
                    this.setSkin(5);
                    break;
                }
                case 1: {
                    this.setSkin(6);
                    break;
                }
                case 2: {
                    this.setSkin(7);
                }
            }
        }
        return floo;
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74776_a("parasiteleftTendril", this.leftTendrilHealth);
        compound.func_74776_a("parasiterightTendril", this.rightTendrilHealth);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("parasiteleftTendril", 99)) {
            this.leftTendrilHealth = compound.func_74760_g("parasiteleftTendril");
            if (this.leftTendrilHealth <= 0.0f) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
            }
        }
        if (compound.func_150297_b("parasiterightTendril", 99)) {
            this.rightTendrilHealth = compound.func_74760_g("parasiterightTendril");
            if (this.rightTendrilHealth <= 0.0f) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)22);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getLeft() {
        return this.leftTendrilHealth;
    }

    @SideOnly(value=Side.CLIENT)
    public float getRight() {
        return this.rightTendrilHealth;
    }

    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
        this.field_70170_p.func_72960_a((Entity)this, (byte)100);
        Vec3d vec3d = this.func_70676_i(1.0f);
        double bon = 6.5;
        EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.field_70170_p, this.field_70165_t + vec3d.field_72450_a * bon, this.field_70163_u, this.field_70161_v + vec3d.field_72449_c * bon);
        entityareaeffectcloud.func_184483_a(5.0f);
        entityareaeffectcloud.func_184486_b(100);
        entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / (float)entityareaeffectcloud.func_184489_o());
        entityareaeffectcloud.func_184496_a(new PotionEffect(SPPotions.VOMIT_E, 300, 0, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(SPPotions.VIRA_E, 300, 40, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76437_t, 300, 40, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76438_s, 300, 40, false, true));
        entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_82731_v, 300, 40, false, true));
        this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
        this.lookAt((Entity)target);
        this.setWait(60);
    }

    public void func_184724_a(boolean swingingArms) {
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 11) {
            this.leftTendrilHealth = 0.0f;
        } else if (id == 22) {
            this.rightTendrilHealth = 0.0f;
        } else if (id == 100) {
            this.vomit = 40;
        } else {
            super.func_70103_a(id);
        }
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillSummon;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillSummon = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.summon();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void summon() {
        this.setParasiteStatus(10);
        this.func_70661_as().func_75499_g();
        if (this.field_70173_aa % 20 != 0) {
            return;
        }
        ++this.border;
        if (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockLiquid) {
            this.skillSummon = true;
            this.setParasiteStatus(0);
            this.border = 0;
            this.limit = 0;
            return;
        }
        this.checkID();
        if (this.getActualParasites() < this.getTotalParasites() && this.limit < SPConfigMobs.canraadaptedlimit) {
            this.func_184185_a(SPSounds.ACANRA_SPECIAL, 3.0f, 1.0f);
            if (ParasiteEventEntity.spawnBiomassFromVomit(this, SPConfigMobs.canraadaptedmoblist, this.func_70638_az())) {
                ++this.limit;
                --this.border;
                this.particleStatus((byte)8);
            }
        } else {
            ++this.border;
        }
        if (this.limit >= SPConfigMobs.canraadaptedlimit || this.border > 4) {
            this.skillSummon = true;
            this.setParasiteStatus(0);
            this.border = 0;
            this.limit = 0;
        }
    }
}

