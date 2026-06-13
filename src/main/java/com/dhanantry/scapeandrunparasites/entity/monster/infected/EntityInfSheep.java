/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.EnumDyeColor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerSheep;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfSheep
extends EntityPInfected
implements EntityCanMelt {
    private static final DataParameter<Float> HEIGH = EntityDataManager.func_187226_a(EntityInfSheep.class, (DataSerializer)DataSerializers.field_187193_c);
    private static final DataParameter<Boolean> MELTING = EntityDataManager.func_187226_a(EntityInfSheep.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Integer> TEX_VARIANT = EntityDataManager.func_187226_a(EntityInfSheep.class, (DataSerializer)DataSerializers.field_187192_b);
    private float aSize;
    private int sound;

    public EntityInfSheep(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.9f, 1.3f);
        this.aSize = 1.0f;
        this.canModRender = 1;
        this.type = (byte)12;
        this.fuseTime = 40;
        this.thisMelting = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 14;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infsheepCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFSHEEP_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFSHEEP_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.23f);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFSHEEP_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFSHEEP_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(HEIGH, (Object)Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(MELTING, (Object)false);
        this.field_70180_af.func_187214_a(TEX_VARIANT, (Object)0);
    }

    public int getTextureVariant() {
        return (Integer)this.field_70180_af.func_187225_a(TEX_VARIANT);
    }

    public void setTextureVariant(int v) {
        if (v < 0) {
            v = 0;
        }
        if (v > 2) {
            v = 2;
        }
        this.field_70180_af.func_187227_b(TEX_VARIANT, (Object)v);
    }

    private void rollTextureVariantWeighted() {
        float r = this.field_70146_Z.nextFloat();
        if (r < 0.1f) {
            this.setTextureVariant(2);
        } else if (r < 0.4f) {
            this.setTextureVariant(1);
        } else {
            this.setTextureVariant(0);
        }
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.melting();
    }

    @Override
    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.func_180482_a(difficulty, livingdata);
        if (!this.field_70170_p.field_72995_K) {
            this.rollTextureVariantWeighted();
        }
        return livingdata;
    }

    @Override
    protected void func_70609_aI() {
        super.func_70609_aI();
        if (this.getTHeigh() < 1.57f && !this.field_70170_p.field_72995_K) {
            this.setTHeigh(0.17f);
        }
        if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() <= SRPAttributes.INFSHEEP_HEADCHANCE) {
            ParasiteSummon.spawnM(this, new String[]{"srparasites:sim_sheephead;1;1"}, 0, false, this.func_95999_t());
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74768_a("TextureVariant", this.getTextureVariant());
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("TextureVariant", 3)) {
            this.setTextureVariant(compound.func_74762_e("TextureVariant"));
        }
    }

    @Override
    public void melt() {
        this.setWait(1000);
        this.field_70180_af.func_187227_b(HEIGH, (Object)Float.valueOf(1.3f));
        this.field_70180_af.func_187227_b(MELTING, (Object)true);
    }

    @Override
    public void melting() {
        if (this.isMelting()) {
            if (this.sound % 20 == 0) {
                this.func_184185_a(SRPSounds.INFECTED_MELT, 1.0f, 1.0f);
            }
            ++this.sound;
            if ((double)this.getTHeigh() > 0.7) {
                this.setaSize(-0.005f);
                this.setTHeigh(-0.01f);
                this.func_70105_a(this.field_70130_N, this.getTHeigh());
            }
            if (!this.field_70170_p.field_72995_K) {
                if ((double)this.getTHeigh() <= 0.7 || this.sound >= 63) {
                    EntityLesh out = new EntityLesh(this.field_70170_p);
                    out.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                    if (this.func_95999_t() != null) {
                        out.func_96094_a(this.func_95999_t());
                    }
                    this.func_70106_y();
                    this.field_70170_p.func_72838_d((Entity)out);
                    out.setLegs(SRPAttributes.INFSHEEP_V, false);
                }
            } else {
                this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 106, 0);
                this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
            }
        }
    }

    @Override
    public boolean isMelting() {
        return (Boolean)this.field_70180_af.func_187225_a(MELTING);
    }

    @Override
    public float getTHeigh() {
        return ((Float)this.field_70180_af.func_187225_a(HEIGH)).floatValue();
    }

    @Override
    public void setTHeigh(float in) {
        this.field_70180_af.func_187227_b(HEIGH, (Object)Float.valueOf(in += this.getTHeigh()));
    }

    @Override
    public float getaSize() {
        return this.aSize;
    }

    @Override
    public void setaSize(float in) {
        this.aSize += in;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensity2() {
        return this.aSize;
    }

    @Override
    protected void selfExplode() {
        super.selfExplode();
        ParasiteSummon.spawnM(this, new String[]{SRPConfigMobs.infsheepmob}, 0, false, this.func_95999_t());
    }

    public float func_70047_e() {
        return 0.95f * this.field_70131_O;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INFECTEDSHEEP_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.INFECTEDSHEEP_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.INFECTEDSHEEP_DEATH;
    }

    @Override
    public EntityPFeral getFeral(World in) {
        return new EntityFerSheep(in);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SoundEvents.field_187765_eK, 0.15f, 1.0f);
    }

    private void cycleTextureVariant() {
        int v = this.getTextureVariant();
        v = (v + 1) % 3;
        this.setTextureVariant(v);
    }

    @Override
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        int before;
        ItemStack stack = player.func_184586_b(hand);
        if (super.func_184645_a(player, hand)) {
            return true;
        }
        if (stack.func_190926_b()) {
            return false;
        }
        if (stack.func_77973_b() != Items.field_151100_aR) {
            return false;
        }
        int desired = this.variantFromDyeMeta(stack.func_77960_j());
        if (desired < 0) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K && (before = this.getTextureVariant()) != desired) {
            this.setTextureVariant(desired);
            if (!player.field_71075_bZ.field_75098_d) {
                stack.func_190918_g(1);
            }
        }
        return true;
    }

    private int variantFromDyeMeta(int meta) {
        if (meta == EnumDyeColor.WHITE.func_176767_b()) {
            return 0;
        }
        if (meta == EnumDyeColor.GRAY.func_176767_b()) {
            return 1;
        }
        if (meta == EnumDyeColor.SILVER.func_176767_b()) {
            return 1;
        }
        if (meta == EnumDyeColor.BLACK.func_176767_b()) {
            return 2;
        }
        return -1;
    }
}

