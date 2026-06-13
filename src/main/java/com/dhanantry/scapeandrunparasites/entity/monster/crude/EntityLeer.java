/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightLimits;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanPullMobs;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCrude;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectilePullball;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityLeer
extends EntityParasiteBase
implements EntityCanPullMobs {
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityLeer.class, (DataSerializer)DataSerializers.field_187191_a);
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntityLeer.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY1 = EntityDataManager.func_187226_a(EntityLeer.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY2 = EntityDataManager.func_187226_a(EntityLeer.class, (DataSerializer)DataSerializers.field_187192_b);
    private EntityLivingBase targetedEntity;
    private ArrayList<DataParameter<Integer>> tracking = new ArrayList();
    private int pulling;
    private EntityBody head;
    private int border;
    private boolean skillpulling;

    public EntityLeer(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(2.1f, 7.1f);
        this.func_189654_d(true);
        this.tracking.add(TARGET_ENTITY);
        this.tracking.add(TARGET_ENTITY1);
        this.tracking.add(TARGET_ENTITY2);
        if (SRPConfigMobs.ombooMaxY != 256) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightLimits(this, SRPConfigMobs.ombooMaxY, true));
        }
        this.head = new EntityBody(this, 2.6f, 2.5f, 1.0f, 0.0f, 7.5f, -1, 1, false, 0.2f);
        this.field_70158_ak = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 328;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SRPConfig.pureFollow));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 40, 300, 7, true, 1, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFlightLimits(this, 5, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.LEER_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.LEER_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.LEER_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.LEER_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.preeminentFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.head.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70122_E) {
                this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
            }
            if (this.srpTicks == 10 && (this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a) && this.func_70638_az() != null) {
                this.field_70181_x = 0.5;
            }
            if (this.hasTargetedEntity()) {
                this.field_70181_x = -0.01;
                this.field_70708_bq = 0;
                for (EntityLivingBase entitylivingbase : this.getTargetedEntityVictims()) {
                    double dis = this.func_70068_e((Entity)entitylivingbase);
                    if (this.func_70685_l((Entity)entitylivingbase) && dis > 0.0) {
                        entitylivingbase.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 3, false, false));
                        entitylivingbase.func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 3, false, false));
                        this.setParasiteStatus(3);
                        ++this.pulling;
                        if (this.pulling > 600) {
                            this.setParasiteStatus(2);
                            this.setTargetedEntity(0, entitylivingbase);
                        }
                        if (!(dis < 4.0)) continue;
                        this.func_70652_k((Entity)entitylivingbase);
                        continue;
                    }
                    this.setParasiteStatus(2);
                    this.setTargetedEntity(0, entitylivingbase);
                }
            } else {
                this.setParasiteStatus(0);
            }
        } else if (this.field_70146_Z.nextInt(30) == 0) {
            for (int i = 0; i <= 20; ++i) {
                if (i % 5 != 0) continue;
                this.spawnParticlesGoreMouth(SRPEnumParticle.GSPLASH, 0, -1, -1, 0.2, 0.0);
            }
        }
        for (EntityLivingBase entitylivingbase : this.getTargetedEntityVictims()) {
            if (!(this.func_70068_e((Entity)entitylivingbase) > 0.0)) continue;
            entitylivingbase.func_184210_p();
            double str = 0.4;
            double deltaX = this.field_70165_t - entitylivingbase.field_70165_t;
            double deltaY = this.field_70163_u - entitylivingbase.field_70163_u;
            double deltaZ = this.field_70161_v - entitylivingbase.field_70161_v;
            str = 0.1;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (distance == 0.0) {
                return;
            }
            entitylivingbase.field_70159_w += (deltaX /= distance) * str;
            entitylivingbase.field_70181_x += (deltaY /= distance) * str;
            entitylivingbase.field_70179_y += (deltaZ /= distance) * str;
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    @Override
    public void setTargetedEntity(int entityId) {
        this.pulling = 0;
        for (DataParameter<Integer> mob : this.tracking) {
            if ((Integer)this.field_70180_af.func_187225_a(mob) != entityId) continue;
            return;
        }
        for (DataParameter<Integer> mob : this.tracking) {
            if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) continue;
            this.field_70180_af.func_187227_b(mob, (Object)entityId);
            return;
        }
    }

    public void setTargetedEntity(int entityId, EntityLivingBase sameEntity) {
        this.pulling = 0;
        for (DataParameter<Integer> mob : this.tracking) {
            if (((Integer)this.field_70180_af.func_187225_a(mob)).intValue() != sameEntity.func_145782_y()) continue;
            this.field_70180_af.func_187227_b(mob, (Object)entityId);
        }
    }

    public void updateTargets() {
        for (DataParameter<Integer> mob : this.tracking) {
            if ((Integer)this.field_70180_af.func_187225_a(mob) == 0) continue;
            Entity entity = this.field_70170_p.func_73045_a(((Integer)this.field_70180_af.func_187225_a(mob)).intValue());
            if (entity == null) {
                this.field_70180_af.func_187227_b(mob, (Object)0);
                continue;
            }
            if (((EntityLivingBase)entity).func_70089_S()) continue;
            this.field_70180_af.func_187227_b(mob, (Object)0);
        }
    }

    @Override
    public boolean hasTargetedEntity() {
        this.updateTargets();
        for (DataParameter<Integer> mob : this.tracking) {
            if ((Integer)this.field_70180_af.func_187225_a(mob) == 0) continue;
            return true;
        }
        return false;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.killcount >= 0.0) {
            if (this.getSkin() != 120) {
                this.killcount += 1.0;
            } else if (this.field_70146_Z.nextInt(3) == 0) {
                this.killcount += 1.0;
            }
        }
        if (SRPConfigSystems.useEvolution) {
            if (this.func_70644_a(SRPPotions.PIVOT_E)) {
                int amp = this.func_70660_b(SRPPotions.PIVOT_E).func_76458_c();
                SRPSaveData.get(this.field_70170_p, 35).setTotalKills(this.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueKill * (SRPConfigSystems.pivotPointMultiplier * (amp + 1)), true, this.field_70170_p, true, 38);
            } else {
                SRPSaveData.get(this.field_70170_p, 34).setTotalKills(this.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueKill, true, this.field_70170_p, true, 39);
            }
        }
        if (SRPConfigWorld.originActivated) {
            ParasiteEventWorld.setOriginInHealth(this.field_70170_p, this.func_180425_c(), (int)((double)entityLivingIn.func_110138_aP() * SRPConfigWorld.originKillMultiplier), true);
            if (SRPConfigWorld.originTriggerKill > this.field_70170_p.field_73012_v.nextDouble()) {
                ParasiteEventWorld.placeOriginInWorld(this.field_70170_p, new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v), SRPConfigWorld.originHealth, SRPConfigWorld.originRadius);
            }
        }
        if (this.func_70644_a(SRPPotions.PARATE_E)) {
            int bonuss = this.func_70660_b(SRPPotions.PARATE_E).func_76458_c() + 1;
            if (entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111267_a) != null) {
                this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * (SRPConfigSystems.parateMuch * (double)bonuss));
            }
            if (entityLivingIn.func_110148_a(SharedMonsterAttributes.field_188791_g) != null) {
                this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() + entityLivingIn.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() * (SRPConfigSystems.parateMuch * (double)bonuss));
            }
            if (entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111264_e) != null) {
                this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() + entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * (SRPConfigSystems.parateMuch * (double)bonuss));
            }
        }
        this.func_70691_i(entityLivingIn.func_110143_aJ() * this.geneMobHealing);
        this.setWait(10);
        List serverList = this.field_70170_p.field_72996_f;
        int parasiteCount = 0;
        for (Entity value : serverList) {
            if (!(value instanceof EntityParasiteBase)) continue;
            ++parasiteCount;
        }
        if (parasiteCount >= SRPConfig.worldMobCap) {
            return;
        }
        entityLivingIn.func_70106_y();
        EntityPCrude mob = new EntityInhooM(this.field_70170_p);
        if (this.field_70146_Z.nextBoolean()) {
            mob = new EntityInhooS(this.field_70170_p);
        }
        mob.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
        mob.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)mob)), null);
        mob.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 60, 5));
        this.field_70170_p.func_72838_d((Entity)mob);
        mob.particleStatus((byte)7);
    }

    public ArrayList<EntityLivingBase> getTargetedEntityVictims() {
        if (!this.hasTargetedEntity()) {
            return new ArrayList<EntityLivingBase>();
        }
        ArrayList<EntityLivingBase> mobs = new ArrayList<EntityLivingBase>();
        for (DataParameter<Integer> mob : this.tracking) {
            Entity entity;
            if ((Integer)this.field_70180_af.func_187225_a(mob) == 0 || (entity = this.field_70170_p.func_73045_a(((Integer)this.field_70180_af.func_187225_a(mob)).intValue())) == null) continue;
            mobs.add((EntityLivingBase)entity);
        }
        return mobs;
    }

    @Override
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

    @Override
    public void setPStatus(int in) {
        this.setParasiteStatus(in);
    }

    @Override
    public void setPullingMobEffects(EntityLivingBase mob) {
        mob.func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 3, false, false));
    }

    @Override
    public int getAcceleration() {
        return 2;
    }

    @Override
    public boolean checkAttackTarget(EntityLivingBase check) {
        if (check instanceof EntityParasiteBase) {
            return false;
        }
        return !(check instanceof EntityPlayer) || !((EntityPlayer)check).func_184812_l_();
    }

    public void func_70108_f(Entity entityIn) {
        for (EntityLivingBase entitylivingbase : this.getTargetedEntityVictims()) {
            if (entitylivingbase != entityIn) continue;
            return;
        }
        super.func_70108_f(entityIn);
    }

    public void func_184206_a(DataParameter<?> key) {
        super.func_184206_a(key);
        if (TARGET_ENTITY.equals(key)) {
            this.targetedEntity = null;
        }
    }

    @Override
    public void func_70106_y() {
        if (this.head != null) {
            this.field_70170_p.func_72973_f((Entity)this.head);
        }
        super.func_70106_y();
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 0.5f;
    }

    public void func_70091_d(MoverType type, double x, double y, double z) {
        super.func_70091_d(type, x, y, z);
        this.func_145775_I();
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(VEX_FLAGS, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY1, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY2, (Object)0);
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

    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setCharging(boolean charging) {
        this.setVexFlag(1, charging);
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillpulling;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillpulling = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.pullingE();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void pullingE() {
        if (this.hasTargetedEntity()) {
            this.skillpulling = true;
            this.border = 0;
            return;
        }
        this.setParasiteStatus(11);
        this.func_70661_as().func_75499_g();
        if (this.border == 0) {
            // empty if block
        }
        if (this.border <= 2) {
            // empty if block
        }
        if (this.field_70173_aa % 20 != 0) {
            return;
        }
        ++this.border;
        if (this.func_70638_az() == null) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
            return;
        }
        if (!this.func_70685_l((Entity)this.func_70638_az())) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
            return;
        }
        this.shootEntityRed(this.func_70638_az());
        int count = 2;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.func_70638_az().func_180425_c().func_177979_c(2)).func_72314_b(18.0, 8.0, 18.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (count < 0 || mob == this || !mob.func_70089_S() || mob instanceof EntityParasiteBase) continue;
            this.shootEntityRed(mob);
            --count;
        }
        if (this.border > 5) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
        }
    }

    public void shootEntityRed(EntityLivingBase entitylivingbase) {
        Vec3d vec3d = this.func_70676_i(1.0f);
        double d2 = entitylivingbase.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
        double d3 = entitylivingbase.func_174813_aQ().field_72337_e + (double)(entitylivingbase.field_70131_O + 0.8f) - (0.5 + this.field_70163_u + (double)(this.field_70131_O / 1.0f));
        double d4 = entitylivingbase.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
        EntityProjectilePullball entitylargefireball = new EntityProjectilePullball(this.field_70170_p, this, d2, d3, d4);
        entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
        entitylargefireball.field_70163_u = this.field_70163_u + (double)this.func_70047_e() - 0.2;
        entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
        this.field_70170_p.func_72838_d((Entity)entitylargefireball);
    }

    @Override
    public void resetPullSkill() {
        this.skillpulling = true;
        this.border = 0;
    }

    class AIChargeAttack
    extends EntityAIBase {
        public AIChargeAttack() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            if (EntityLeer.this.func_70638_az() != null && EntityLeer.this.field_70146_Z.nextInt(5) == 0) {
                return EntityLeer.this.func_70068_e((Entity)EntityLeer.this.func_70638_az()) > 4.0;
            }
            return false;
        }

        public boolean func_75253_b() {
            return EntityLeer.this.func_70605_aq().func_75640_a() && EntityLeer.this.isCharging() && EntityLeer.this.func_70638_az() != null && EntityLeer.this.func_70638_az().func_70089_S();
        }

        public void func_75249_e() {
            EntityLivingBase entitylivingbase = EntityLeer.this.func_70638_az();
            Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
            EntityLeer.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 0.2);
            EntityLeer.this.setCharging(true);
        }

        public void func_75251_c() {
            EntityLeer.this.setCharging(false);
        }

        public void func_75246_d() {
            EntityLivingBase entitylivingbase = EntityLeer.this.func_70638_az();
            if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
                if (EntityLeer.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
                    EntityLeer.this.func_70652_k((Entity)entitylivingbase);
                    EntityLeer.this.setCharging(false);
                } else {
                    double d0 = EntityLeer.this.func_70068_e((Entity)entitylivingbase);
                    if (d0 < 9.0) {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityLeer.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 1.0);
                    } else {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityLeer.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 1.1);
                    }
                }
            }
        }
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            return !EntityLeer.this.func_70605_aq().func_75640_a() && EntityLeer.this.field_70146_Z.nextInt(7) == 0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityLeer.this);
            int flag = 1;
            double speed = 0.1;
            if (EntityLeer.this.func_70638_az() != null) {
                if (EntityLeer.this.func_70068_e((Entity)EntityLeer.this.func_70638_az()) > 100.0) {
                    blockpos = new BlockPos((Entity)EntityLeer.this.func_70638_az());
                    flag = 2;
                    speed += 0.05;
                } else if (EntityLeer.this.func_70068_e((Entity)EntityLeer.this.func_70638_az()) < 36.0) {
                    blockpos = new BlockPos((Entity)EntityLeer.this.func_70638_az());
                    flag = 3;
                    speed += 0.05;
                }
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityLeer.this.field_70146_Z.nextInt(15) - 7, EntityLeer.this.field_70146_Z.nextInt(11) - 5, EntityLeer.this.field_70146_Z.nextInt(15) - 7);
                if (flag == 2) {
                    blockpos1 = blockpos.func_177982_a(EntityLeer.this.field_70146_Z.nextInt(6) - 2, EntityLeer.this.field_70146_Z.nextInt(7) - 2, EntityLeer.this.field_70146_Z.nextInt(6) - 2);
                } else if (flag == 3) {
                    blockpos1 = blockpos.func_177982_a(EntityLeer.this.field_70146_Z.nextInt(4) + 3, EntityLeer.this.field_70146_Z.nextInt(5) + 4, EntityLeer.this.field_70146_Z.nextInt(4) + 3);
                }
                if (!EntityLeer.this.field_70170_p.func_175623_d(blockpos1)) continue;
                EntityLeer.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 1.0, (double)blockpos1.func_177952_p() + 0.5, speed);
                if (EntityLeer.this.func_70638_az() != null) break;
                EntityLeer.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 1.0, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityLeer vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityLeer.this.field_70165_t;
                double d1 = this.field_75647_c - EntityLeer.this.field_70163_u;
                double d2 = this.field_75644_d - EntityLeer.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityLeer.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityLeer.this.field_70159_w *= 0.5;
                    EntityLeer.this.field_70181_x *= 0.5;
                    EntityLeer.this.field_70179_y *= 0.5;
                } else {
                    EntityLeer.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityLeer.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityLeer.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityLeer.this.func_70638_az() == null) {
                        EntityLeer.this.field_70761_aq = EntityLeer.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityLeer.this.field_70159_w, (double)EntityLeer.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityLeer.this.func_70638_az().field_70165_t - EntityLeer.this.field_70165_t;
                        double d5 = EntityLeer.this.func_70638_az().field_70161_v - EntityLeer.this.field_70161_v;
                        EntityLeer.this.field_70761_aq = EntityLeer.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

