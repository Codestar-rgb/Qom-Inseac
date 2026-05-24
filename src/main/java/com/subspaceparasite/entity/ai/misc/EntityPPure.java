/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.block.BlockGore;
import com.subspaceparasite.entity.EntityRemain;
import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.misc.EntityCanSummon;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.entity.ai.misc.EntityPStationary;
import com.subspaceparasite.entity.monster.deterrent.EntityDodT;
import com.subspaceparasite.entity.monster.deterrent.EntityNak;
import com.subspaceparasite.entity.projectile.EntityGore;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPPure
extends EntityPMalleable
implements EntityCanSummon {
    protected int totalP;
    protected int actualP;
    protected int[] mobID;
    protected int[] mobPT;
    private int ggg;

    public EntityPPure(World worldIn) {
        super(worldIn);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, SPConfig.pureWalls, false, null, SPConfig.pureSneakPen, SPConfig.pureInviPen));
        if (SPConfig.canOrbAttack) {
            this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 60, 5, false, 21));
        }
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, SPConfig.pureWalls, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                }
            }, SPConfig.pureSneakPen, SPConfig.pureInviPen));
        }
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70728_aV = SPAttributes.XP_ADAPTED;
        this.canD = SPConfig.puredespawn;
        this.damageCap = SPConfig.pureCap;
        this.canModRender = 1;
        this.fuseTime = 70;
        this.type = (byte)51;
        this.killcount = 10.0;
        this.fuseOrb = 23;
        this.orbStartTimer = 5;
        this.foodSteal = SPConfig.pureFoodSteal;
        this.orbItemCool = SPConfig.pureItemOrbCooldown * 20;
        this.pointCap = SPConfig.purePointCap;
        this.pointReduction = SPConfig.purePointRed;
        this.chanceLearn = SPConfig.pureChanceLe;
        this.chanceLearnFire = SPConfig.pureChanceLeFire;
        this.DamageTypeCap = SPConfig.purePointDamCap;
        this.MiniDamage = SPConfig.pureMinDamage;
        this.regen = SPConfig.pureRegen * SPConfig.globalHealthMultiplier;
        this.oneMindDeathValue = SPConfig.pureOneMindDeathV;
        this.regenEff = 15;
        this.foodRott = SPConfig.pureFoodChance;
        this.foodRootNumber = SPConfig.pureFoodAmount;
        this.cothSpread = SPConfigSystems.cothPure;
        this.totalP = SPConfig.pureSeiZ;
        this.mobID = new int[this.totalP];
        this.mobPT = new int[this.totalP];
        for (int i = 0; i < this.mobID.length; ++i) {
            this.mobID[i] = -777;
        }
        this.valueEvDeath = SPConfig.pureLoosingEPValue;
        this.setScentHPMultiplier(1.0f);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        ++this.ggg;
        if (this.ggg > 60) {
            this.ggg = 0;
        }
        if (this.func_70638_az() != null) {
            this.summonTentacles(this.func_70638_az());
        }
    }

    @Override
    protected void fearPlayer(EntityLivingBase player) {
        try {
            if (player == null) {
                return;
            }
            if (!this.func_70685_l((Entity)player)) {
                return;
            }
            if (!player.func_70644_a(SPPotions.FEAR_E)) {
                player.func_70690_d(new PotionEffect(SPPotions.FEAR_E, 300, 2, false, false));
            } else if (player.func_70660_b(SPPotions.FEAR_E).func_76458_c() < 2) {
                player.func_70690_d(new PotionEffect(SPPotions.FEAR_E, 300, 2, false, false));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        EntityLivingBase attacker;
        if (this.field_70170_p.field_72995_K) {
            return super.func_70097_a(source, amount);
        }
        if (source.func_76346_g() instanceof EntityLivingBase && (attacker = (EntityLivingBase)source.func_76346_g()).func_70644_a(SPPotions.KILLPUR_E)) {
            int amp = attacker.func_70660_b(SPPotions.KILLPUR_E).func_76458_c();
            float totalRed = MathHelper.func_76131_a((float)(SPConfigSystems.parasiteKillingReduction * ((float)amp + 1.0f)), (float)0.0f, (float)0.95f);
            float reduced = amount * (1.0f - totalRed);
            boolean flag = super.func_70097_a(source, Math.max(0.0f, reduced));
            if (flag) {
                this.summonTentacles((EntityLivingBase)source.func_76346_g());
            }
            return flag;
        }
        return super.func_70097_a(source, amount);
    }

    @Override
    protected void attackEntityFromEffects(int range, int count) {
        this.particleStatus((byte)51);
        double i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        int counttt = 0;
        for (int k2 = -1 * range; k2 <= 1 * range && SPConfig.paraGore; ++k2) {
            for (int l2 = -1 * range; l2 <= 1 * range; ++l2) {
                double i3 = l1 + (double)k2;
                double l = i2 + (double)l2;
                BlockPos blockpos = new BlockPos(i3, i1, l);
                Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
                Block blockDown = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
                if (block != Blocks.field_150350_a || blockDown == Blocks.field_150350_a || !this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_185913_b() || blockDown == SPBlocks.InfestedStain || this.field_70170_p.field_73012_v.nextInt(4) != 0) continue;
                this.field_70170_p.func_175656_a(blockpos, SPBlocks.gorePur.func_176223_P().func_177226_a(BlockGore.VARIANT, (Comparable)((Object)BlockGore.EnumType.FLAT)));
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
            bomb.setType((byte)4);
            bomb.func_82149_j((Entity)this);
            bomb.setMotion(d3 *= (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3f)), d4, d5 *= d7, 0.2, 0.8);
            this.field_70170_p.func_72838_d((Entity)bomb);
        }
    }

    @Override
    protected void spawnGore() {
        this.attackEntityFromEffects(3, 100);
        if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b() && (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a)) {
            this.field_70170_p.func_175656_a(this.func_180425_c(), SPBlocks.gorePur.func_176223_P().func_177226_a(BlockGore.VARIANT, (Comparable)((Object)BlockGore.EnumType.BIG)));
            EntityRemain nnn = new EntityRemain(this.field_70170_p);
            nnn.func_70012_b((double)this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), (double)this.func_180425_c().func_177952_p() + 0.5, 0.0f, 0.0f);
            nnn.setParasite(EntityList.func_191301_a((Entity)this).toString());
            nnn.setSkin((byte)this.getSkin());
            nnn.setGoal(20 * SPConfig.pureRemainValue);
            this.field_70170_p.func_72838_d((Entity)nnn);
        }
        this.attackEntityFromCap(5);
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        if (!SPConfigSystems.disloSameVersionDyeing || !this.disloNumberTwentytwo) {
            return super.onDeathDislo(cause);
        }
        boolean flag = super.onDeathDislo(cause);
        if (!flag && cause.func_76346_g() instanceof EntityLivingBase) {
            int count = SPSaveData.get(this.field_70170_p, 28).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 22);
            EntityLivingBase target = (EntityLivingBase)cause.func_76346_g();
            if (count >= 1) {
                target.func_184596_c(SPPotions.KILLPRI_E);
                target.func_184596_c(SPPotions.KILLADA_E);
                target.func_184596_c(SPPotions.KILLFER_E);
                target.func_184596_c(SPPotions.KILLCRU_E);
                target.func_184596_c(SPPotions.KILLNEX_E);
                SPPotions.applyStackPotion(SPPotions.KILLPUR_E, target, 1200, count);
            }
        }
        return flag;
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 80.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    protected void summonTentacles(EntityLivingBase in) {
        boolean flag;
        if (this.ggg != 20) {
            return;
        }
        if (SPConfigSystems.useEvolution && ParasiteEventEntity.getRSchance(this.field_70170_p) == 0.0) {
            return;
        }
        double dis = this.func_70068_e((Entity)in);
        boolean bl = flag = !this.func_70685_l((Entity)in);
        if (dis > 64.0 && flag && this.field_70146_Z.nextInt(3) == 0 || this.field_70146_Z.nextInt(10) == 0) {
            this.checkID();
            if (this.getActualParasites() < this.getTotalParasites() && this.spawnT(in, 5, 3, 2, 2)) {
                this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 150, 120, false, false));
            }
        } else {
            if (in.func_70644_a(MobEffects.field_76421_d) && in.func_70660_b(MobEffects.field_76421_d).func_76458_c() == 2) {
                return;
            }
            this.checkID();
            if (this.getActualParasites() >= this.getTotalParasites() || this.spawnT(in, 3, 1, 16, 1)) {
                // empty if block
            }
        }
    }

    protected boolean spawnT(EntityLivingBase in, int range2, int mini2, int check, int type) {
        int range = range2;
        int mini = mini2;
        Random rand = new Random();
        double x = in.field_70165_t;
        double y = in.field_70163_u;
        double z = in.field_70161_v;
        double randomx = rand.nextInt(range) + mini;
        double randomz = rand.nextInt(range) + mini;
        double negative = rand.nextInt(2);
        if (negative == 0.0) {
            randomx *= -1.0;
        }
        if ((negative = (double)rand.nextInt(2)) == 0.0) {
            randomz *= -1.0;
        }
        int limit = 0;
        boolean flag = true;
        EntityPStationary entityout = new EntityDodT(this.field_70170_p);
        ((EntityDodT)entityout).setTele(this);
        if (type == 1) {
            List serverList = this.field_70170_p.field_72996_f;
            int count = 0;
            for (int k = 0; k < serverList.size(); ++k) {
                if (!(serverList.get(k) instanceof EntityNak)) continue;
                ++count;
            }
            if (count >= 7) {
                entityout.func_70106_y();
                return false;
            }
            entityout = new EntityNak(this.field_70170_p);
        }
        while (flag) {
            if (limit >= 5) {
                entityout.func_70106_y();
                return false;
            }
            BlockPos pos = new BlockPos(x + randomx, y, z + randomz);
            if ((pos = ParasiteEventEntity.getFloor(this.field_70170_p, pos, 5)) != null) {
                IBlockState state = this.field_70170_p.func_180495_p(pos);
                if (this.field_70170_p.func_180495_p(pos.func_177977_b()).func_185917_h()) {
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_186662_g((double)check);
                    List moblist = this.field_70170_p.func_72872_a(EntityNak.class, axisalignedbb);
                    if (moblist.size() > 10) {
                        return false;
                    }
                    axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_186662_g(2.0);
                    moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    if (moblist.isEmpty()) {
                        entityout.func_70012_b(x + randomx, y, z + randomz, this.field_70177_z, this.field_70125_A);
                        if (!this.field_70170_p.func_184144_a((Entity)entityout, entityout.func_174813_aQ()).isEmpty()) {
                            randomx = rand.nextInt(range) + mini;
                            randomz = rand.nextInt(range) + mini;
                            negative = rand.nextInt(2);
                            if (negative == 0.0) {
                                randomx *= -1.0;
                            }
                            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                                randomz *= -1.0;
                            }
                            ++limit;
                            continue;
                        }
                        entityout.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
                        entityout.func_70624_b(in);
                        this.setActualParasites(1);
                        this.addID(entityout.func_145782_y(), 1);
                        entityout.setBuried();
                        this.field_70170_p.func_72838_d((Entity)entityout);
                        this.field_70170_p.func_72960_a((Entity)entityout, (byte)50);
                        flag = false;
                        return true;
                    }
                }
            }
            randomx = rand.nextInt(range) + mini;
            randomz = rand.nextInt(range) + mini;
            negative = rand.nextInt(2);
            if (negative == 0.0) {
                randomx *= -1.0;
            }
            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                randomz *= -1.0;
            }
            ++limit;
        }
        return false;
    }

    private EntityDodT getTentacle() {
        for (int i = 0; i < this.mobID.length; ++i) {
            if (this.mobID[i] <= 0 || !(this.field_70170_p.func_73045_a(this.mobID[i]) instanceof EntityDodT)) continue;
            EntityDodT flag = (EntityDodT)this.field_70170_p.func_73045_a(this.mobID[i]);
            if (flag == null) {
                this.mobID[i] = -777;
                int negative = this.mobPT[i] * -1;
                this.setActualParasites(negative);
                continue;
            }
            if (!flag.func_70089_S() || flag.getTele() != null || flag.buried()) continue;
            return flag;
        }
        return null;
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
        return this.mobID;
    }

    @Override
    public int[] getPointList() {
        return this.mobPT;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void spawnEffectsGore() {
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag && in instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer)in;
            if (playerIn.field_71068_ca > 0) {
                playerIn.func_71023_q(-SPConfig.pureExpSteal);
                if (playerIn.field_71106_cc < 0.0f) {
                    --playerIn.field_71068_ca;
                    playerIn.field_71106_cc = 1.0f;
                }
            } else {
                playerIn.func_71023_q(-SPConfig.pureExpSteal);
                if (playerIn.field_71106_cc < 0.0f) {
                    playerIn.field_71106_cc = 0.0f;
                }
            }
        }
        return flag;
    }
}

