/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackMelee
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIParasiteFollow;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLesh
extends EntityParasiteBase {
    private int noLegs;
    private int noTimes;
    private float tSize;
    private float aSize;
    private int delay;
    private boolean st1;
    private float tSpeed;
    private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(EntityLesh.class, (DataSerializer)DataSerializers.field_187193_c);

    public EntityLesh(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.7f, 0.5f);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.noLegs = (1 + this.field_70146_Z.nextInt(2)) * 2;
        this.noTimes = 1;
        this.st1 = false;
        this.tSize = 1.0f;
        this.aSize = 1.0f;
        this.fuseTime = 70;
        this.killcount = -10.0;
        this.type = (byte)100;
    }

    @Override
    public int getParasiteIDRegister() {
        return 23;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIParasiteFollow(this, 1.3, 10.0, 2.0, false));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAILeshCombine(this, 1.1));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLesh.class, false, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityLivingBase.class, (Predicate)new Predicate<EntityLivingBase>(){

            public boolean apply(@Nullable EntityLivingBase entity) {
                return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityCreeper) && !(entity instanceof EntityParasiteBase) && !(entity instanceof EntityAnimal);
            }
        }, 8.0f, 1.0, 1.0));
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SIZE, (Object)Float.valueOf(this.tSize));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.adaptedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.func_70638_az() != null && this.field_70173_aa % 20 == 0) {
            if (!this.func_70638_az().func_70089_S()) {
                this.func_70624_b(null);
            }
            if (this.func_70638_az() instanceof EntityLesh) {
                EntityLesh meh = (EntityLesh)this.func_70638_az();
                if (this.getTimes() + meh.getTimes() > 4) {
                    this.func_70624_b(null);
                }
            }
        }
        if (this.gettSize() > this.getaSize()) {
            this.setaSize(0.01f);
            this.func_70105_a(0.7f + (this.getaSize() - 1.0f), 0.5f + (this.getaSize() - 1.0f));
        }
        if (this.getTimes() >= 4 && this.srpTicks == 10 || this.field_70173_aa > 800 && this.getTimes() > 1) {
            ++this.delay;
            this.st1 = true;
        }
        if (this.delay >= 4 && this.st1) {
            this.setSelfeState(1);
        }
        if (!this.field_70170_p.field_72995_K) {
            if (SRPConfigSystems.disloGiveBodies && this.func_70089_S() && this.srpTicks == 10 && SRPSaveData.get(this.field_70170_p, 40).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 20) >= 1) {
                ParasiteEventEntity.spawnNext(this, ParasiteEventEntity.getRandomPrimitive(this.field_70170_p), true, false);
                return;
            }
            if (!this.field_70128_L && this.func_110143_aJ() > 0.0f && this.func_110143_aJ() < this.func_110138_aP()) {
                this.func_70606_j(this.func_110143_aJ() + 0.007f);
            }
        }
    }

    public void func_70071_h_() {
        if (this.func_70089_S()) {
            this.lastActiveTime = this.timeSinceIgnited;
            this.dyingBurst(false, 2);
        }
        super.func_70071_h_();
    }

    @Override
    protected void dyingBurst(boolean fromDeath, int value) {
        int i = this.getSelfeState();
        if (i > 0 && this.timeSinceIgnited == 0) {
            this.func_184185_a(SRPSounds.FLESH_GROW, 10.0f, 1.0f);
        }
        this.timeSinceIgnited += i * value;
        if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
        }
        if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            this.selfExplode();
        }
    }

    @Override
    protected void selfExplode() {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70729_aU = true;
            this.func_70106_y();
            this.spawnPrimitive(this, this.noLegs);
            this.func_184185_a(SRPSounds.FLESH_PRIMITIVE, 1.0f, 1.0f);
        } else {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
        }
    }

    public int getLegs() {
        return this.noLegs;
    }

    public void setLegs(int i, boolean plus) {
        if (plus) {
            this.noLegs += i;
            return;
        }
        this.noLegs = i;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (!(entityIn instanceof EntityLesh)) {
            return true;
        }
        ++this.delay;
        if (this.delay >= 3) {
            EntityLesh meh = (EntityLesh)entityIn;
            if (this.getaSize() >= meh.getaSize()) {
                if (this.func_95999_t() == null && meh.func_95999_t() != null) {
                    this.func_96094_a(meh.func_95999_t());
                }
                this.setLegs(meh.getLegs(), true);
                this.setTimes(meh.getTimes());
                meh.particleStatus((byte)6);
                meh.func_70106_y();
                this.func_184185_a(SRPSounds.FLESH_EAT, 1.0f, 1.0f);
                this.settSize(0.3f);
                this.resetDelay();
                this.settSpeed((float)(this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() - 0.01));
                this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)this.tSpeed);
                this.particleStatus((byte)5);
                this.func_184185_a(SRPSounds.FLESH_GROW, 10.0f, 1.0f);
            }
        }
        return true;
    }

    public int getTimes() {
        return this.noTimes;
    }

    public void setTimes(int in) {
        this.noTimes += in;
    }

    public float func_70047_e() {
        return 0.3f;
    }

    public float gettSize() {
        return ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue();
    }

    public void settSize(float in) {
        this.tSize += in;
        this.field_70180_af.func_187227_b(SIZE, (Object)Float.valueOf(this.tSize));
    }

    public void settSpeed(float in) {
        this.tSpeed = in;
    }

    public float getaSize() {
        return this.aSize;
    }

    public void setaSize(float in) {
        this.aSize += in;
    }

    public void resetDelay() {
        this.delay = 0;
    }

    protected SoundEvent func_184639_G() {
        return SRPSounds.FLESH_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.FLESH_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.FLESH_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SRPSounds.LITE_FLESH_SLIDE, 0.3f, 1.0f);
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74776_a("leshtotalsize", this.tSize);
        compound.func_74776_a("leshactualsize", this.aSize);
        compound.func_74768_a("leshlegs", this.noLegs);
        compound.func_74768_a("leshtimes", this.noTimes);
        compound.func_74776_a("leshspeed", this.tSpeed);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("leshtotalsize", 99)) {
            this.tSize = compound.func_74760_g("leshtotalsize");
            this.field_70180_af.func_187227_b(SIZE, (Object)Float.valueOf(compound.func_74760_g("leshtotalsize")));
        }
        if (compound.func_150297_b("leshlegs", 99)) {
            this.noLegs = compound.func_74762_e("leshlegs");
        }
        if (compound.func_150297_b("leshtimes", 99)) {
            this.noTimes = compound.func_74762_e("leshtimes");
        }
        if (compound.func_150297_b("leshspeed", 99)) {
            this.tSpeed = compound.func_74760_g("leshspeed");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensityS() {
        return this.aSize;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 4) {
            this.tSize += 0.3f;
        } else {
            super.func_70103_a(id);
        }
    }

    private void spawnPrimitive(EntityLesh host, int code) {
        host.func_70106_y();
        this.merge(this, code, this.func_95999_t());
    }

    private boolean merge(EntityParasiteBase entityin, int code, String name) {
        if (SRPConfigSystems.mergeRandom) {
            entityin.particleStatus((byte)7);
            Random rand = new Random();
            int index = rand.nextInt(SRPConfigSystems.mergeMobTable.length);
            boolean flag = true;
            int limit = 0;
            while (flag) {
                if (index >= SRPConfigSystems.mergeMobTable.length) {
                    index = 0;
                    ++limit;
                }
                if (limit == 2) {
                    return false;
                }
                if (SRPConfigSystems.mergeMobTable[index] != null) {
                    String[] entityC = SRPConfigSystems.mergeMobTable[index].split(";");
                    ParasiteSummon.spawnM(entityin, new String[]{entityC[0] + ";1;1"}, 7, true, name);
                    flag = false;
                    if (SRPConfigSystems.useEvolution) {
                        SRPWorldData data = SRPWorldData.get(entityin.field_70170_p);
                        SRPSaveData.get(entityin.field_70170_p, 100).setTotalKills(entityin.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueMerge, true, entityin.field_70170_p, true, 40);
                    }
                    return true;
                }
                ++index;
            }
        } else {
            String[] entityC;
            for (int i = 0; i < SRPConfigSystems.mergeMobTable.length; ++i) {
                int points;
                if (SRPConfigSystems.mergeMobTable[i] == null || (points = Integer.parseInt((entityC = SRPConfigSystems.mergeMobTable[i].split(";"))[1])) != code) continue;
                ParasiteSummon.spawnM(entityin, new String[]{entityC[0] + ";1;1"}, 7, true, name);
                if (SRPConfigSystems.useEvolution) {
                    SRPSaveData.get(entityin.field_70170_p, 96).setTotalKills(entityin.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueMerge, true, entityin.field_70170_p, true, 41);
                }
                return true;
            }
            Random rand = new Random();
            int index = rand.nextInt(SRPConfigSystems.mergeMobTable.length);
            boolean flag = true;
            int limit = 0;
            while (flag) {
                if (index >= SRPConfigSystems.mergeMobTable.length) {
                    index = 0;
                    ++limit;
                }
                if (limit == 2) {
                    return false;
                }
                if (SRPConfigSystems.mergeMobTable[index] != null) {
                    entityC = SRPConfigSystems.mergeMobTable[index].split(";");
                    ParasiteSummon.spawnM(entityin, new String[]{entityC[0] + ";1;1"}, 7, true, name);
                    flag = false;
                    if (SRPConfigSystems.useEvolution) {
                        SRPSaveData.get(entityin.field_70170_p, 95).setTotalKills(entityin.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueMerge, true, entityin.field_70170_p, true, 42);
                    }
                    return true;
                }
                ++index;
            }
        }
        return false;
    }

    static class EntityAILeshCombine
    extends EntityAIBase {
        private final EntityLesh parent;

        public EntityAILeshCombine(EntityLesh animal, double speedIn) {
            this.parent = animal;
        }

        public boolean func_75250_a() {
            EntityLivingBase target = this.parent.func_70638_az();
            if (target == null) {
                return true;
            }
            return !target.func_70089_S();
        }

        public void func_75251_c() {
        }

        public void func_75246_d() {
            if (this.parent.field_70173_aa % 20 != 0) {
                return;
            }
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
            List moblist = this.parent.field_70170_p.func_72872_a(EntityLesh.class, axisalignedbb);
            for (EntityLesh mob : moblist) {
                if (mob == this.parent || !this.parent.func_70685_l((Entity)mob) || !mob.func_70089_S() || mob.func_94060_bK() != null || mob.getTimes() + this.parent.getTimes() > 4) continue;
                this.parent.func_70624_b((EntityLivingBase)mob);
                mob.func_70624_b((EntityLivingBase)this.parent);
                return;
            }
        }
    }
}

