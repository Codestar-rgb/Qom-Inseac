/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.block.BlockGore;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.EntityRemain;
import com.subspaceparasite.entity.ai.EntityAIEvade;
import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityCanSpawn;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import com.subspaceparasite.entity.projectile.EntityGore;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPFeral
extends EntityParasiteBase
implements EntityCanSpawn {
    protected float regen;
    protected int regenEff;
    protected int regenUse;

    public EntityPFeral(World worldIn) {
        super(worldIn);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, false, null, SPConfig.feralSneakPen, SPConfig.feralInviPen));
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, entity -> !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite), SPConfig.feralSneakPen, SPConfig.feralInviPen));
        }
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 30, 5, 3.0));
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70728_aV = SPAttributes.XP_FERAL;
        this.damageCap = SPConfig.feralCap;
        this.canD = SPConfig.feraldespawn;
        this.MiniDamage = SPConfig.feralMinDamage;
        this.oneMindDeathValue = SPConfig.feralOneMindDeathV;
        this.foodSteal = SPConfig.feralFoodSteal;
        this.regen = SPConfig.feralRegen * SPConfig.globalHealthMultiplier;
        this.regenEff = 10;
        this.cothSpread = SPConfigSystems.cothFeral;
        this.valueEvDeath = SPConfig.feralLoosingEPValue;
        this.attackSpeedT = 14;
        this.setScentHPMultiplier(1.5f);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            if (this.field_70146_Z.nextInt(25) == 0) {
                for (int i = 0; i <= 1; ++i) {
                    this.spawnParticlesGoreBox(SPEnumParticle.GSPLASH, 0, -1, -1, 0.1, 0.0);
                }
            }
        } else if (this.srpTicks == 10 && !this.field_70128_L && this.func_110143_aJ() > 0.0f && !this.func_70027_ad() && this.regen > 0.0f && this.killcount > 1.0 && this.func_110143_aJ() < this.func_110138_aP()) {
            this.func_70606_j(this.func_110143_aJ() + this.regen);
            --this.regenUse;
            if (this.regenUse <= 0) {
                this.killcount -= 1.0;
                this.regenUse = this.regenEff;
            }
        }
    }

    @Override
    protected void fearPlayer(EntityLivingBase player) {
    }

    protected void fearPlayer(EntityLivingBase player, float damageDealt) {
        if (player == null || player.field_70170_p.field_72995_K) {
            return;
        }
        if (damageDealt <= 8.0f) {
            return;
        }
        int level = 1 + Math.max(0, (int)Math.floor((damageDealt - 8.0f) / 4.0f));
        int cap = 3;
        level = Math.min(level, cap);
        int duration = 300 + 40 * (level - 1);
        duration = MathHelper.func_76125_a((int)duration, (int)200, (int)500);
        int amplifier = level - 1;
        player.func_70690_d(new PotionEffect(SPPotions.FEAR_E, duration, amplifier, false, true));
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        EntityLivingBase attacker;
        if (this.field_70170_p.field_72995_K) {
            return super.func_70097_a(source, amount);
        }
        if (source.func_76346_g() instanceof EntityLivingBase && (attacker = (EntityLivingBase)source.func_76346_g()).func_70644_a(SPPotions.KILLFER_E)) {
            int amp = Objects.requireNonNull(attacker.func_70660_b(SPPotions.KILLFER_E)).func_76458_c();
            float totalRed = MathHelper.func_76131_a((float)(SPConfigSystems.parasiteKillingReduction * ((float)amp + 1.0f)), (float)0.0f, (float)0.95f);
            float reduced = amount * (1.0f - totalRed);
            return super.func_70097_a(source, Math.max(0.0f, reduced));
        }
        boolean tookDamage = super.func_70097_a(source, amount);
        if (tookDamage && source.func_76346_g() instanceof EntityLivingBase && this.field_70146_Z.nextDouble() < SPConfig.feralMult) {
            double d2;
            double d5;
            double d1;
            double d4;
            double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
            double d3 = d0 - this.field_70165_t;
            double d6 = MathHelper.func_76133_a((double)(d3 * d3 + (d4 = (d1 = (double)((float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat())) - this.field_70163_u) * d4 + (d5 = (d2 = (double)((float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat())) - this.field_70161_v) * d5));
            if (d6 < 1.0E-4) {
                d6 = 1.0E-4;
            }
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.8 / (d6 / 4.0 + 0.1);
            d3 = d3 * (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3f)) * 0.5;
            d4 = d4 * d7 * 1.0;
            d5 = d5 * d7 * 0.5;
            EntityGore bomb = new EntityGore(this.field_70170_p);
            bomb.setType((byte)111);
            bomb.entityName = EntityList.func_191301_a((Entity)this) != null ? Objects.requireNonNull(EntityList.func_191301_a((Entity)this)).toString() : "unknown";
            bomb.func_82149_j((Entity)this);
            bomb.setMotion(d3, d4, d5, 0.2, 0.5);
            this.field_70170_p.func_72838_d((Entity)bomb);
        }
        return tookDamage;
    }

    @Override
    protected void attackEntityFromEffects(int range, int count) {
        this.particleStatus((byte)51);
        double i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        int counttt = 0;
        for (int k2 = -1 * range; k2 <= range && SPConfig.paraGore; ++k2) {
            for (int l2 = -1 * range; l2 <= range; ++l2) {
                double i3 = l1 + (double)k2;
                double l = i2 + (double)l2;
                BlockPos blockpos = new BlockPos(i3, i1, l);
                Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
                Block blockDown = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
                if (block != Blocks.field_150350_a || blockDown == Blocks.field_150350_a || !this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_185913_b() || blockDown == SPBlocks.InfestedStain || this.field_70170_p.field_73012_v.nextInt(4) != 0) continue;
                this.field_70170_p.func_175656_a(blockpos, SPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, (Comparable)((Object)BlockGore.EnumType.FLAT)));
                if (++counttt < count) continue;
                return;
            }
        }
    }

    @Override
    protected void attackEntityFromCap(int go) {
        for (int i = 0; i < go && SPConfig.paraGore; ++i) {
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
            double d7 = 0.8 / (d6 / 4.0 + 0.1);
            d4 = d4 * d7 * 2.0;
            EntityGore bomb = new EntityGore(this.field_70170_p);
            bomb.setType((byte)1);
            bomb.func_82149_j((Entity)this);
            bomb.setMotion(d3 *= (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3f)), d4, d5 *= d7, 0.2, 0.8);
            this.field_70170_p.func_72838_d((Entity)bomb);
        }
    }

    @Override
    protected void spawnGore() {
        this.attackEntityFromEffects(2, 100);
        List serverList = this.field_70170_p.field_72996_f;
        int count = 0;
        for (Entity entity : serverList) {
            if (!(entity instanceof EntityParasiteBase)) continue;
            ++count;
        }
        if (count < SPConfig.worldMobCap) {
            EntityAta ata = new EntityAta(this.field_70170_p);
            ata.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)ata);
        }
        if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b() && (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a)) {
            this.field_70170_p.func_175656_a(this.func_180425_c(), SPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, (Comparable)((Object)BlockGore.EnumType.BIG)));
            EntityRemain nnn = new EntityRemain(this.field_70170_p);
            nnn.func_70012_b((double)this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), (double)this.func_180425_c().func_177952_p() + 0.5, 0.0f, 0.0f);
            nnn.setParasite(Objects.requireNonNull(EntityList.func_191301_a((Entity)this)).toString());
            nnn.setSkin((byte)this.getSkin());
            nnn.setGoal(20 * SPConfig.feralRemainValue);
            this.field_70170_p.func_72838_d((Entity)nnn);
        }
        this.attackEntityFromCap(3);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        float after;
        float dealt;
        if (!(entityIn instanceof EntityLivingBase)) {
            return super.func_70652_k(entityIn);
        }
        EntityLivingBase target = (EntityLivingBase)entityIn;
        float before = target.func_110143_aJ() + target.func_110139_bj();
        boolean hit = super.func_70652_k(entityIn);
        if (hit && !this.field_70170_p.field_72995_K && (dealt = Math.max(0.0f, before - (after = target.func_110143_aJ() + target.func_110139_bj()))) > 0.0f) {
            this.fearPlayer(target, dealt);
        }
        return hit;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        super.func_70074_a(entityLivingIn);
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        if (!SPConfigSystems.disloSameVersionDyeing || !this.disloNumberTwentytwo) {
            return super.onDeathDislo(cause);
        }
        boolean flag = super.onDeathDislo(cause);
        if (!flag && cause.func_76346_g() instanceof EntityLivingBase) {
            int count = SPSaveData.get(this.field_70170_p, 31).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 22);
            EntityLivingBase target = (EntityLivingBase)cause.func_76346_g();
            if (count >= 1) {
                target.func_184596_c(SPPotions.KILLPRI_E);
                target.func_184596_c(SPPotions.KILLADA_E);
                target.func_184596_c(SPPotions.KILLPUR_E);
                target.func_184596_c(SPPotions.KILLCRU_E);
                target.func_184596_c(SPPotions.KILLNEX_E);
                SPPotions.applyStackPotion(SPPotions.KILLFER_E, target, 1200, count);
            }
        }
        return flag;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void spawnEffectsGore() {
        int i;
        for (i = 0; i <= 100; ++i) {
            if (i % 5 != 0) continue;
            this.spawnParticlesGore(SPEnumParticle.GSPLASH, 0, -1, -1);
        }
        for (i = 0; i <= 20; ++i) {
            if (i % 5 == 0) {
                this.spawnParticles(SPEnumParticle.GCLOUD, 127, 0, 0);
            }
            if (i % 5 != 0) continue;
            this.spawnParticles(SPEnumParticle.GSPLASH, 0, -1, -1);
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
    }
}

