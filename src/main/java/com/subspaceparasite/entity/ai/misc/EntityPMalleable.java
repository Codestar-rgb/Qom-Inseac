/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.CooldownTracker
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.EntityOrbScary;
import com.subspaceparasite.entity.ai.EntityAIBlockLight;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.crude.EntityCruxB;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class EntityPMalleable
extends EntityParasiteBase {
    protected static final DataParameter<Byte> HIT = EntityDataManager.func_187226_a(EntityPMalleable.class, (DataSerializer)DataSerializers.field_187191_a);
    private ArrayList<String> resistanceS = new ArrayList();
    private ArrayList<Integer> resistanceI = new ArrayList();
    protected int newDamageCooldown = 0;
    protected float pointReduction = 0.1f;
    protected int pointCap = 10;
    protected int DamageTypeCap = 5;
    protected double chanceLearn = 0.5;
    protected double chanceLearnFire = 0.0;
    private int onFireA;
    private int onProj;
    protected float adaptationCap = 1.0f;
    protected float regen = 0.0f;
    protected int regenEff = 1;
    protected int regenUse = 1;
    private int stun;
    public boolean colonySpawned;
    protected int fuseOrb;
    protected int orbStartTimer;
    protected int orbItemCool;
    protected int orbVersionCooldown;
    protected float damageAmountReduced;
    protected boolean geneAdaptation = true;
    protected boolean geneBlocksearch = true;
    protected boolean geneResidue = true;
    protected boolean geneOrbbox = true;
    protected int limitOrb;
    protected int borderOrb;
    protected boolean skillOrb;

    public EntityPMalleable(World worldIn) {
        super(worldIn);
        this.foodRott = 0.0;
        this.foodRootNumber = 1;
        if (SPConfig.canTargetBlock) {
            this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIBlockLight(this, 20, 5));
        }
        this.setScentHPMultiplier(2.5f);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(HIT, (Object)0);
    }

    @Override
    public boolean getGeneMod(int id) {
        switch (id) {
            case 6: {
                return this.geneAdaptation;
            }
            case 7: {
                return this.geneBlocksearch;
            }
            case 8: {
                return this.geneResidue;
            }
            case 9: {
                return this.geneOrbbox;
            }
        }
        return super.getGeneMod(id);
    }

    @Override
    public void applyGene(boolean[] kool, float[] goon) {
        if (!SPConfigSystems.generationUse) {
            return;
        }
        this.geneMindam = kool[0];
        this.geneDamcap = kool[1];
        this.geneLookwall = kool[2];
        this.geneSprinting = kool[3];
        this.geneWaterleap = kool[4];
        this.geneSpecialmove = kool[5];
        this.geneAdaptation = kool[6];
        this.geneBlocksearch = kool[7];
        this.geneResidue = kool[8];
        this.geneOrbbox = kool[9];
        this.genePoisonHealing = goon[0];
        this.geneMobHealing = goon[1];
        this.geneAttackSpeed = goon[2];
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.srpTicks == 10) {
                if (this.func_70644_a(SPPotions.RES_E)) {
                    this.removeAllResistance(1);
                }
                if (this.orbVersionCooldown > 0) {
                    --this.orbVersionCooldown;
                }
                if (!this.field_70128_L && this.func_110143_aJ() > 0.0f && !this.func_70027_ad() && this.regen > 0.0f && this.killcount > 1.0 && this.func_110143_aJ() < this.func_110138_aP()) {
                    this.func_70606_j(this.func_110143_aJ() + this.regen);
                    --this.regenUse;
                    if (this.regenUse <= 0) {
                        this.killcount -= 1.0;
                        this.regenUse = this.regenEff;
                    }
                }
            }
            if (this.onFireA > 0) {
                --this.onFireA;
            }
            if (this.onProj > 0) {
                --this.onProj;
            }
            if (this.newDamageCooldown > 0) {
                --this.newDamageCooldown;
            }
            if (this.stun >= 0) {
                --this.stun;
                if (this.stun < 0 && this.getParasiteStatus() == 25) {
                    this.setParasiteStatus(0);
                }
            }
        }
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        boolean flag;
        ResourceLocation key;
        ItemStack stack;
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        this.field_70180_af.func_187227_b(HIT, (Object)0);
        if (SPConfigSystems.useOneMind && source != null && source.func_76346_g() instanceof EntityLivingBase && ParasiteEventEntity.alertOthers(this, (EntityLivingBase)source.func_76346_g(), this.field_70170_p, 7)) {
            SPPotions.applySense((EntityLivingBase)this, 400, this.func_70068_e(source.func_76346_g()), SPConfigSystems.oneMinRangeCap);
        }
        if (source == DamageSource.field_76376_m && this.func_70644_a(MobEffects.field_76436_u) && amount == 1.0f) {
            this.func_70691_i(1.0f * this.genePoisonHealing);
            return false;
        }
        if (source == DamageSource.field_76380_i || source == DamageSource.field_76368_d) {
            return super.func_70097_a(source, amount);
        }
        if ((source == DamageSource.field_76372_a || source == DamageSource.field_76370_b) && this.field_70146_Z.nextDouble() < this.chanceLearnFire) {
            this.onFireA = SPConfig.fireTickWindow;
        }
        Entity immediate = source.func_76364_f();
        Entity trueSrc = source.func_76346_g();
        float times = immediate instanceof EntityLivingBase ? (immediate instanceof EntityPlayer ? (!(stack = ((EntityPlayer)immediate).func_184582_a(EntityEquipmentSlot.MAINHAND)).func_190926_b() && stack.func_77973_b() != null && stack.func_77973_b().getRegistryName() != null ? (float)this.hasResistance(stack.func_77973_b().getRegistryName().toString(), (byte)1) : (float)this.hasResistance(source.func_76355_l(), (byte)2)) : ((key = EntityList.func_191301_a((Entity)immediate)) == null ? (float)this.hasResistance(source.func_76355_l(), (byte)2) : (float)this.hasResistance(key.toString(), (byte)0))) : (trueSrc instanceof EntityLivingBase ? (trueSrc instanceof EntityPlayer ? (!(stack = ((EntityPlayer)trueSrc).func_184582_a(EntityEquipmentSlot.MAINHAND)).func_190926_b() && stack.func_77973_b() != null && stack.func_77973_b().getRegistryName() != null ? (float)this.hasResistance(stack.func_77973_b().getRegistryName().toString(), (byte)1) : (float)this.hasResistance(source.func_76355_l(), (byte)2)) : ((key = EntityList.func_191301_a((Entity)trueSrc)) == null ? (float)this.hasResistance(source.func_76355_l(), (byte)2) : (float)this.hasResistance(key.toString(), (byte)0))) : (float)this.hasResistance(source.func_76355_l(), (byte)2));
        float bonus = times * this.pointReduction * amount;
        float amountTwo = amount;
        amount = Math.max(amount - bonus * this.adaptationCap, 0.0f);
        this.damageAmountReduced = amountTwo - amount;
        if (bonus != 0.0f && amount != 0.0f) {
            this.particleStatus((byte)9);
            this.particleStatus((byte)9);
        }
        if (amount == 0.0f) {
            this.particleStatus((byte)9);
            this.particleStatus((byte)9);
        }
        if (flag = super.func_70097_a(source, amount)) {
            switch (this.getHitStatus()) {
                case 1: {
                    this.func_184185_a(SPSounds.ADAPTATION_P, this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
                    return true;
                }
                case 2: {
                    this.func_184185_a(SPSounds.ADAPTATION_F, this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
                    return true;
                }
                case 3: {
                    this.func_184185_a(SPSounds.CYST_EATING, this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
                    return true;
                }
            }
        }
        return flag;
    }

    @Override
    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (hand != EnumHand.MAIN_HAND || this.field_70170_p.field_72995_K) {
            return super.func_184645_a(player, hand);
        }
        Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
        if (wea == SPItems.itembase) {
            StringBuilder out = new StringBuilder("Current adaptation for " + Objects.requireNonNull(EntityList.func_191301_a((Entity)this)) + " (Damage type, points): ");
            for (int i = 0; i < this.resistanceS.size(); ++i) {
                out.append("[").append(this.resistanceS.get(i)).append(", ").append(this.resistanceI.get(i)).append("] ");
            }
            player.func_145747_a((ITextComponent)new TextComponentString(out.toString()));
            return true;
        }
        return super.func_184645_a(player, hand);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (this.borderOrb > 0) {
            return false;
        }
        return super.func_70652_k(entityIn);
    }

    protected int hasResistance(String damage, byte type) {
        if (!this.geneAdaptation) {
            return 0;
        }
        if (this.func_70644_a(SPPotions.RES_E) || this.field_70729_aU) {
            return 0;
        }
        if (this.checkList(damage, type)) {
            return 0;
        }
        if (this.DamageTypeCap <= 0) {
            return 0;
        }
        if (this.field_70146_Z.nextDouble() < this.getChanceLearn()) {
            this.addResistance(damage);
        }
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            if (!this.resistanceS.get(i).equals(damage)) continue;
            int dama = this.resistanceI.get(i);
            if (dama <= this.pointCap) {
                this.field_70180_af.func_187227_b(HIT, (Object)1);
            } else {
                this.field_70180_af.func_187227_b(HIT, (Object)2);
            }
            return Math.min(dama, this.pointCap);
        }
        return 0;
    }

    private double getChanceLearn() {
        double bonus = 0.0;
        if (SPConfig.adaptationDimStrong.length > 0) {
            for (int i = 0; i < SPConfig.adaptationDimStrong.length; ++i) {
                String[] here = SPConfigSystems.evolutionDimStart[i].split(";");
                int dim = Integer.parseInt(here[0]);
                double b = Double.parseDouble(here[1]);
                if (dim != this.field_70170_p.field_73011_w.getDimension()) continue;
                bonus = b;
                break;
            }
        }
        return this.chanceLearn + this.chanceLearn * bonus;
    }

    public void addResistance(String damage) {
        if (this.onFireA > 0) {
            return;
        }
        if (this.newDamageCooldown > 0) {
            return;
        }
        boolean flag = true;
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            if (!this.resistanceS.get(i).equals(damage)) continue;
            int iiii = this.resistanceI.get(i) + 1;
            this.resistanceI.set(i, iiii);
            flag = false;
            if (iiii > this.pointCap) break;
            this.particleStatus((byte)5);
            this.particleStatus((byte)5);
            break;
        }
        if (flag) {
            if (this.resistanceS.size() >= this.DamageTypeCap) {
                return;
            }
            this.resistanceS.add(damage);
            this.resistanceI.add(1);
            this.particleStatus((byte)5);
            this.particleStatus((byte)5);
        }
        this.newDamageCooldown = SPConfig.adaptationNewDamageCooldon;
    }

    public void removeAllResistance(int value) {
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            int iiii = this.resistanceI.get(i) - value;
            if (iiii <= 0) {
                this.resistanceI.remove(i);
                this.resistanceS.remove(i);
                --i;
                continue;
            }
            this.resistanceI.set(i, iiii);
        }
    }

    public ArrayList<String> getResistanceS() {
        return this.resistanceS;
    }

    public ArrayList<Integer> getResistanceI() {
        return this.resistanceI;
    }

    public void copyResistancesFrom(EntityPMalleable in) {
        this.resistanceS = in.getResistanceS();
        this.resistanceI = in.getResistanceI();
    }

    public void increaseAllResistances() {
        this.resistanceI.replaceAll(integer -> integer + 1);
    }

    public void cutResistances(int in) {
        if (this.DamageTypeCap <= 0) {
            return;
        }
        this.stun += 40;
        this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 40, 100, false, false));
        this.setParasiteStatus(25);
        this.func_184185_a(SPSounds.TENDRIL, 3.0f, 1.0f);
        if (SPConfigSystems.rageEnable) {
            this.func_70690_d(new PotionEffect(SPPotions.RAGE_E, 200, 1, false, false));
        }
        SPPotions.applyStackPotion(SPPotions.BLEED_E, (EntityLivingBase)this, 100, 0);
        if (in % 2 != 0) {
            ++in;
        }
        this.DamageTypeCap -= in;
        if (this.resistanceI.size() != this.resistanceS.size()) {
            this.resistanceI = new ArrayList();
            this.resistanceS = new ArrayList();
            this.DamageTypeCap = 0;
            return;
        }
        int size = this.resistanceS.size() - in;
        if (size <= 0) {
            this.resistanceI = new ArrayList();
            this.resistanceS = new ArrayList();
        } else {
            while (in <= 0) {
                if (this.resistanceI.isEmpty()) {
                    this.DamageTypeCap = 0;
                    return;
                }
                this.resistanceI.remove(0);
                this.resistanceS.remove(0);
                --in;
            }
        }
    }

    public void increaseDamageCap(int in) {
        this.DamageTypeCap += in;
    }

    private boolean checkList(String damage, byte type) {
        switch (type) {
            case 0: {
                if (ParasiteEventEntity.checkName(damage, SPConfig.damageTypeBlackListMob, SPConfig.damageTypeBlackListWhite)) {
                    return true;
                }
            }
            case 1: {
                if (ParasiteEventEntity.checkName(damage, SPConfig.damageTypeBlackListItem, SPConfig.damageTypeBlackListWhite)) {
                    return true;
                }
            }
            case 2: {
                if (!ParasiteEventEntity.checkName(damage, SPConfig.damageTypeBlackListElse, SPConfig.damageTypeBlackListWhite)) break;
                return true;
            }
        }
        return false;
    }

    private void outOfRange(DamageSource source) {
        if (this.func_70638_az() == null) {
            Vec3d vec3d;
            if (source.func_76346_g() != null) {
                Entity sss = source.func_76346_g();
                if (sss instanceof EntityPlayer) {
                    EntityPlayer pl = (EntityPlayer)sss;
                    if (pl.field_71075_bZ.field_75102_a || pl.func_175149_v()) {
                        return;
                    }
                }
                if (this.func_70685_l(sss) && SPPotions.applySense((EntityLivingBase)this, 600, this.func_70068_e(sss), 50)) {
                    this.func_70661_as().func_75492_a(sss.field_70165_t, sss.field_70163_u, sss.field_70161_v, 1.3);
                }
            } else if (this.func_70661_as().func_75505_d() == null && (vec3d = RandomPositionGenerator.func_75461_b((EntityCreature)this, (int)16, (int)7, (Vec3d)new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v))) != null && !this.func_70661_as().func_75492_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.3) && (vec3d = RandomPositionGenerator.func_75461_b((EntityCreature)this, (int)16, (int)7, (Vec3d)new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v))) != null && !this.func_70661_as().func_75492_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.3) && this.field_70173_aa % 20 == 0) {
                this.func_70661_as().func_75499_g();
                double dd0 = vec3d.field_72450_a - this.field_70165_t;
                double dd1 = vec3d.field_72449_c - this.field_70161_v;
                float f = MathHelper.func_76133_a((double)(dd0 * dd0 + dd1 * dd1));
                this.field_70159_w += dd0 / (double)f * 1.0 * (double)0.8f + this.field_70159_w * (double)0.2f;
                this.field_70179_y += dd1 / (double)f * 1.0 * (double)0.8f + this.field_70179_y * (double)0.2f;
                this.field_70181_x = 0.15;
            }
        }
    }

    public byte getHitStatus() {
        return (Byte)this.field_70180_af.func_187225_a(HIT);
    }

    public void setOrbVersionCooldown(int in) {
        this.orbVersionCooldown = in;
    }

    public String getMostCommonDamage() {
        if (this.func_70644_a(SPPotions.RES_E)) {
            return null;
        }
        String atm = null;
        int times = 0;
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            if (this.resistanceI.get(i) <= times) continue;
            times = this.resistanceI.get(i);
            atm = this.resistanceS.get(i);
        }
        return atm;
    }

    public void removeCommonDamage(String damage, int times) {
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            if (!this.resistanceS.get(i).equals(damage)) continue;
            int neww = this.resistanceI.get(i) - times;
            if (neww <= 0) {
                this.resistanceS.remove(i);
                this.resistanceI.remove(i);
                --i;
                continue;
            }
            this.resistanceI.set(i, neww);
        }
    }

    public boolean scaryOrbEffect(EntityLivingBase in, int totalMobs) {
        if (in instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)in;
            if (player.field_71075_bZ.field_75098_d) {
                return false;
            }
            player.func_71020_j(this.foodSteal * (float)SPConfig.orbFoodMult);
            if (this.orbItemCool > 0) {
                CooldownTracker track = player.func_184811_cZ();
                for (int i = 0; i < player.field_71071_by.field_70462_a.size(); ++i) {
                    if (track.func_185143_a(((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_77973_b(), 1.0f) != 0.0f) continue;
                    track.func_185145_a(((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_77973_b(), this.orbItemCool);
                }
            }
            this.attackEntityAsMobFood((Entity)player, false, this.foodRootNumber, this.foodRott);
        } else {
            if (in == this) {
                return false;
            }
            if (in instanceof EntityPCosmical && ((EntityPCosmical)in).getCloneC()) {
                return false;
            }
        }
        in.func_70690_d(new PotionEffect(SPPotions.COTH_E, 1200, 3, false, false));
        if (!(in instanceof EntityParasiteBase)) {
            this.attackEntityAsMobMinimum(in, this.getMiniDamage() * 0.5f);
        }
        SPPotions.applyStackPotion(MobEffects.field_76436_u, in, 100, 0);
        return true;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        super.func_70645_a(cause);
        if (!this.field_70170_p.field_72995_K) {
            ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
            if (this.field_70170_p.field_73012_v.nextInt(3) == 0 && this.func_70027_ad() && this.canModRender == 1) {
                this.madeRng = 0;
                this.field_70170_p.func_72960_a((Entity)this, (byte)40);
            }
        }
    }

    @Override
    protected void selfExplode() {
        super.selfExplode();
        if (!this.field_70170_p.field_72995_K && this.func_70027_ad()) {
            EntityCruxB lol = new EntityCruxB(this.field_70170_p);
            lol.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)lol);
        }
    }

    @Override
    protected void onDeathUpdateOG() {
        ++this.field_70725_aQ;
        if (this.field_70725_aQ == 20) {
            if (!this.field_70170_p.field_72995_K && (this.func_70684_aJ() || this.field_70718_bc > 0 && this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot"))) {
                int j;
                int i = this.func_70693_a(this.field_70717_bb);
                for (i = ForgeEventFactory.getExperienceDrop((EntityLivingBase)this, (EntityPlayer)this.field_70717_bb, (int)i); i > 0; i -= j) {
                    j = EntityXPOrb.func_70527_a((int)i);
                    this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
                }
            }
            this.func_70106_y();
            if (this.field_70170_p.field_72995_K) {
                for (int k = 0; k < 20; ++k) {
                    this.spawnParticles(SPEnumParticle.GCLOUD, 127, 0, 0);
                }
            }
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74757_a("paracolony", this.colonySpawned);
        if (this.resistanceS.size() != this.resistanceI.size()) {
            return;
        }
        NBTTagList allResS = new NBTTagList();
        NBTTagList allResI = new NBTTagList();
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            String res = this.resistanceS.get(i);
            NBTTagCompound resT = new NBTTagCompound();
            resT.func_74778_a("resistance" + i, res);
            allResS.func_74742_a((NBTBase)resT);
            int resi = this.resistanceI.get(i);
            NBTTagCompound resU = new NBTTagCompound();
            resU.func_74768_a("resistance" + i, resi);
            allResI.func_74742_a((NBTBase)resU);
        }
        compound.func_74768_a("damagetypecap", this.DamageTypeCap);
        compound.func_74782_a("sprresistances", (NBTBase)allResS);
        compound.func_74782_a("sprresistancei", (NBTBase)allResI);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("paracolony", 99)) {
            this.colonySpawned = compound.func_74767_n("paracolony");
        }
        if (compound.func_150297_b("damagetypecap", 99)) {
            this.DamageTypeCap = compound.func_74762_e("damagetypecap");
        }
        if (compound.func_74764_b("sprresistances")) {
            NBTTagList allResS = compound.func_150295_c("sprresistances", 10);
            NBTTagList allResI = compound.func_150295_c("sprresistancei", 10);
            if (allResS.func_74745_c() != allResI.func_74745_c()) {
                return;
            }
            for (int i = 0; i < allResS.func_74745_c(); ++i) {
                NBTTagCompound resT = allResS.func_150305_b(i);
                String res = resT.func_74779_i("resistance" + i);
                this.resistanceS.add(i, res);
                NBTTagCompound resU = allResI.func_150305_b(i);
                int resi = resU.func_74762_e("resistance" + i);
                this.resistanceI.add(i, resi);
            }
        }
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 21: {
                return this.skillOrb;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 21: {
                this.skillOrb = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 21: {
                this.scaryOrb();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void scaryOrb() {
        if (!this.geneOrbbox) {
            this.skillOrb = true;
            return;
        }
        if (this.borderOrb == -1 || this.orbVersionCooldown > 0 && this.borderOrb == 0) {
            this.skillOrb = true;
            return;
        }
        if (this.borderOrb == 0) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g((double)this.fuseOrb / 2.0 + 1.0);
            List moblist = this.field_70170_p.func_72872_a(EntityPMalleable.class, axisalignedbb);
            for (EntityPMalleable mob : moblist) {
                if (mob == this || mob.getParasiteType() > this.getParasiteType()) continue;
                mob.setOrbVersionCooldown(4);
            }
        }
        this.setParasiteStatus(9);
        this.func_70661_as().func_75499_g();
        if (this.field_70173_aa % 20 != 0) {
            return;
        }
        ++this.borderOrb;
        if (this.borderOrb == 1) {
            EntityOrbScary ttt = new EntityOrbScary(this.field_70170_p, this, this.fuseOrb, this.orbStartTimer);
            ttt.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)ttt);
            this.func_184185_a(SPSounds.ORB_S, 1.0f, 1.0f);
        }
        if (this.borderOrb > 3) {
            this.skillOrb = true;
            this.setParasiteStatus(0);
            this.borderOrb = 0;
            this.limitOrb = 0;
        }
    }
}

