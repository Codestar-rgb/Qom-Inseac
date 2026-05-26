/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.primitive;

import com.subspaceparasite.entity.EntityDamage;
import com.subspaceparasite.entity.EntityHitbox;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIDiveBomb;
import com.subspaceparasite.entity.ai.EntityAIEvade;
import com.subspaceparasite.entity.ai.EntityAIGetFollowers;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityPPrimitive;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.adapted.EntityNoglaAdapted;
import com.subspaceparasite.entity.monster.crude.EntityLesh;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.Locale;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityNogla
extends EntityPPrimitive {
    private EntityHitbox head;
    private static final byte STATUS_RICARDO_BURST = 77;
    private boolean ricardoBaseCaptured = false;
    private double ricardoBaseMaxHealth;
    private double ricardoBaseArmor;
    private double ricardoBaseToughness;
    private int attacking;
    private double targetX;
    private double targetY;
    private double targetZ;
    private boolean skillCharge;

    public EntityNogla(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.9f, 2.6f);
        this.field_70138_W = 1.0f;
        this.skillCharge = false;
        this.head = new EntityHitbox((EntityLiving)this, 1.6f, 0.9f, 2.2f, 0.7f, 0.8f, 1.25f);
        this.hitboxes = new EntityHitbox[]{this.head};
    }

    @Override
    public int getParasiteIDRegister() {
        return 10;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.095));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIDiveBomb((EntityLiving)this, 1200, 60, 3.8, 3.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 40, 32, 8, true, 1));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 2, 16));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 55, 10, 4.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.NOGLA_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.NOGLA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.31234);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.NOGLA_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.NOGLA_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.primitiveFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0 && this.killcount > SPConfig.adaptedKills && ParasiteEventEntity.canSpawnNext) {
            ParasiteEventEntity.spawnNext(this, new EntityNoglaAdapted(this.field_70170_p), true, true);
        }
        if (this.field_70170_p.field_72995_K && this.isRicardoVariant() && (this.field_70173_aa & 3) == 0) {
            for (int i = 0; i < 3; ++i) {
                double x = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 1.6;
                double y = this.field_70163_u + this.field_70146_Z.nextDouble() * ((double)this.field_70131_O * 0.9);
                double z = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 1.6;
                float r = 1.0f;
                float g = 0.25f;
                float b = 0.75f;
                this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB_AMBIENT, x, y, z, (double)r, (double)g, (double)b, new int[0]);
            }
        }
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
        }
    }

    public float func_70047_e() {
        return 2.4f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (this.isRicardoVariant()) {
                this.func_70099_a(new ItemStack(SPItems.bookofvengeance, 1), 0.0f);
            }
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityLesh(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            if (entityIn instanceof EntityLivingBase) {
                ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76436_u, 100));
                switch (this.getSkin()) {
                    case 5: {
                        SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
                        break;
                    }
                    case 6: {
                        SPPotions.applyStackPotion(SPPotions.BLEED_E, (EntityLivingBase)entityIn, 40, 0);
                    }
                }
            }
            if (!this.field_70170_p.field_72995_K && entityIn instanceof EntitySlime && this.func_70681_au().nextFloat() < 0.1f) {
                double dx = entityIn.field_70165_t - this.field_70165_t;
                double dz = entityIn.field_70161_v - this.field_70161_v;
                double len = Math.sqrt(dx * dx + dz * dz);
                if (len < 1.0E-4) {
                    dx = this.func_70681_au().nextDouble() - 0.5;
                    dz = this.func_70681_au().nextDouble() - 0.5;
                    len = Math.sqrt(dx * dx + dz * dz);
                }
                double horizontal = 4.75;
                double yBoost = 1.2;
                entityIn.func_70024_g((dx /= len) * horizontal, yBoost, (dz /= len) * horizontal);
                entityIn.field_70133_I = true;
            }
            if (this.isRicardoVariant() && !this.field_70170_p.field_72995_K) {
                this.field_70170_p.func_72885_a((Entity)this, entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, 1.8f, false, false);
            }
        }
        return flag;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        super.func_70074_a(entityLivingIn);
        this.particleStatus((byte)5);
        if (!this.field_70170_p.field_72995_K && this.killcount > SPConfig.adaptedKills && ParasiteEventEntity.canSpawnNext) {
            ParasiteEventEntity.spawnNext(this, new EntityNoglaAdapted(this.field_70170_p), true, true);
        }
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.NOGLA_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.NOGLA_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.NOGLA_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SPConfigMobs.noglaOrbEffects, mobs);
        }
        return flag;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SPSounds.MONSTER_STEP, 0.15f, 1.0f);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SPConfig.variantChance || this.phaseCreated >= SPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(4)) {
                case 0: {
                    this.setSkin(5);
                    break;
                }
                case 1: {
                    this.setSkin(6);
                    break;
                }
                case 2: {
                    this.setSkin(1);
                    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.NOGLA_HEALTH * 0.5);
                    this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.NOGLA_ATTACK_DAMAGE * 1.5);
                    this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
                    break;
                }
                case 3: {
                    this.setSkin(7);
                }
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            this.updateRicardoAttributes();
        }
        return floo;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        switch (id) {
            case 100: {
                for (int i = 0; i <= 1; ++i) {
                    this.spawnParticles(EnumParticleTypes.FLAME);
                }
                break;
            }
            default: {
                super.func_70103_a(id);
            }
        }
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillCharge;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillCharge = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    public boolean isRicardoVariant() {
        if (!SPConfigMobs.noglaRicardoVariantEnabled) {
            return false;
        }
        if (!this.func_145818_k_()) {
            return false;
        }
        String raw = TextFormatting.func_110646_a((String)this.func_95999_t());
        if (raw == null) {
            return false;
        }
        return raw.trim().toLowerCase(Locale.ROOT).equals("ricardo");
    }

    private void captureRicardoBaselines() {
        if (this.ricardoBaseCaptured) {
            return;
        }
        this.ricardoBaseMaxHealth = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
        this.ricardoBaseArmor = this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b();
        this.ricardoBaseToughness = this.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111125_b();
        this.ricardoBaseCaptured = true;
    }

    private void updateRicardoAttributes() {
        this.captureRicardoBaselines();
        double baseSpeed = this.isRicardoVariant() ? 0.45 : 0.3;
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(baseSpeed);
        if (this.isRicardoVariant()) {
            double MAX_HP = 3763.0;
            double ARMOR_NORMAL = 32.0;
            double ARMOR_ENRAGE = 40.0;
            double SPEED_ENRAGE = 0.58;
            double ENRAGE_PCT = 0.25;
            double BERSERK_PCT = 0.1;
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3763.0);
            double cur = this.func_110143_aJ();
            double max = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
            if (max <= 0.0) {
                max = 3763.0;
            }
            double pct = max > 0.0 ? cur / max : 1.0;
            boolean enrage = pct <= 0.25;
            boolean berserk = pct <= 0.1;
            double armorNow = 32.0;
            double speedNow = baseSpeed;
            if (enrage) {
                armorNow = 40.0;
                speedNow = 0.58;
                this.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 60, 1, false, true));
                this.func_70690_d(new PotionEffect(MobEffects.field_76420_g, 60, 0, false, true));
                if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
                    ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.CRIT_MAGIC, this.field_70165_t, this.field_70163_u + 1.2, this.field_70161_v, 12, 0.35, 0.6, 0.35, 0.02, new int[0]);
                }
            }
            if (berserk) {
                armorNow = Math.max(armorNow, 44.0);
                speedNow = Math.max(speedNow, 0.62);
                this.func_70690_d(new PotionEffect(MobEffects.field_76420_g, 60, 1, false, true));
                this.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 60, 2, false, true));
            }
            this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(armorNow);
            this.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111128_a(this.ricardoBaseToughness);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(speedNow);
            if (this.func_110143_aJ() > (float)max) {
                this.func_70606_j((float)max);
            } else if (this.func_110143_aJ() <= 0.0f) {
                this.func_70606_j((float)max);
            }
        } else {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.ricardoBaseMaxHealth);
            this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(this.ricardoBaseArmor);
            this.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111128_a(this.ricardoBaseToughness);
            if (this.func_110143_aJ() > (float)this.ricardoBaseMaxHealth) {
                this.func_70606_j((float)this.ricardoBaseMaxHealth);
            }
        }
    }

    public void func_96094_a(String name) {
        boolean wasRicardo = this.isRicardoVariant();
        super.func_96094_a(name);
        System.out.println("[SP] noglaRicardoVariantEnabled=" + SPConfigMobs.noglaRicardoVariantEnabled + " name=" + this.func_95999_t());
        if (!this.field_70170_p.field_72995_K) {
            if (!SPConfigMobs.noglaRicardoVariantEnabled) {
                this.updateRicardoAttributes();
                return;
            }
            boolean isNowRicardo = this.isRicardoVariant();
            this.updateRicardoAttributes();
            if (isNowRicardo && !wasRicardo) {
                this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
                this.field_70170_p.func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187754_de, SoundCategory.HOSTILE, 8.0f, 1.0f);
                this.field_70170_p.func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187752_dd, SoundCategory.HOSTILE, 4.0f, 1.0f);
                ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.CRIT_MAGIC, this.field_70165_t, this.field_70163_u + 1.2, this.field_70161_v, 40, 0.6, 0.8, 0.6, 0.1, new int[0]);
                this.field_70170_p.func_72960_a((Entity)this, (byte)77);
            }
        }
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.charge();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void charge() {
        ++this.attacking;
        this.miniCapA = true;
        if (this.attacking < 20) {
            EntityLivingBase entitylivingbase;
            this.field_70170_p.func_72960_a((Entity)this, (byte)100);
            if (this.attacking == 2) {
                float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4f + 2.0f;
                this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0f, v);
            }
            if ((entitylivingbase = this.func_70638_az()) == null || !this.field_70122_E || this.func_70090_H() || entitylivingbase.field_70163_u > this.field_70163_u && entitylivingbase.field_70122_E) {
                this.skillCharge = true;
                this.attacking = 0;
                this.miniCapA = false;
                this.setParasiteStatus(0);
                return;
            }
            if (!entitylivingbase.func_70089_S()) {
                this.skillCharge = true;
                this.attacking = 0;
                this.miniCapA = false;
                this.setParasiteStatus(0);
                return;
            }
            if (this.attacking <= 19) {
                double dis = this.func_70032_d((Entity)entitylivingbase);
                this.setParasiteStatus(3);
                this.func_70661_as().func_75499_g();
                this.targetX = this.field_70165_t + 15.0 * (entitylivingbase.field_70165_t - this.field_70165_t) / dis;
                this.targetY = this.field_70163_u + 15.0 * (entitylivingbase.field_70163_u - this.field_70163_u) / dis;
                this.targetZ = this.field_70161_v + 15.0 * (entitylivingbase.field_70161_v - this.field_70161_v) / dis;
            }
        }
        if (this.attacking == 20) {
            this.func_70661_as().func_75492_a(this.targetX, this.targetY, this.targetZ, 2.5);
        }
        if (this.attacking >= 20) {
            for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(2.0, 0.0, 2.0))) {
                if (mob == this || mob instanceof EntityParasiteBase) continue;
                float f = (float)MathHelper.func_181159_b((double)(mob.field_70161_v - this.field_70161_v), (double)(mob.field_70165_t - this.field_70165_t));
                EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, f, (EntityLivingBase)this, 1.0f, false, 0.5f);
                this.field_70170_p.func_72838_d((Entity)damage);
            }
        }
        this.skillBreakBlocks();
        if (!this.field_70122_E) {
            this.field_70159_w *= 0.7;
            this.field_70179_y *= 0.7;
        }
        if (this.attacking >= 60 && this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
            this.attacking = 0;
            this.miniCapA = false;
            this.skillCharge = true;
            this.setParasiteStatus(2);
        }
    }
}

