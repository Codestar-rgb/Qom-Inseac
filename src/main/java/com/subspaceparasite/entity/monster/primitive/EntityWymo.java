/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNodeType
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.primitive;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.subspaceparasite.entity.ai.EntityAIFollowBodies;
import com.subspaceparasite.entity.ai.misc.EntityCanHaveBodies;
import com.subspaceparasite.entity.ai.misc.EntityCutomAttack;
import com.subspaceparasite.entity.ai.misc.EntityPPrimitive;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.adapted.EntityWymoAdapted;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityWymo
extends EntityPPrimitive
implements EntityCanHaveBodies,
EntityCutomAttack {
    private UUID following;
    private boolean canF;
    private BlockPos digTarget;
    public float diggingModel;
    public final float heightTwo = 2.4f;
    private int bodiesT;
    private int bodiesTOut;
    private float attackTimer;
    private boolean up;
    private static final DataParameter<Byte> PART = EntityDataManager.func_187226_a(EntityWymo.class, (DataSerializer)DataSerializers.field_187191_a);
    private static final DataParameter<Boolean> TAIL = EntityDataManager.func_187226_a(EntityWymo.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> DIG = EntityDataManager.func_187226_a(EntityWymo.class, (DataSerializer)DataSerializers.field_187198_h);

    public EntityWymo(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.978f, 1.2f);
        this.field_70138_W = 1.0f;
        this.canModRender = 1;
        this.borderOrb = -1;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.func_184644_a(PathNodeType.WATER, -1.0f);
        this.diggingModel = 0.0f;
        this.canF = true;
        this.bodiesT = 0;
    }

    @Override
    public int getParasiteIDRegister() {
        return 37;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(PART, (Object)0);
        this.field_70180_af.func_187214_a(TAIL, (Object)false);
        this.field_70180_af.func_187214_a(DIG, (Object)false);
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIFollowBodies(this, 1.7f, 0.26, 10, SPConfig.adaptedKills, 200));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 0.0, 3.0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.WYMO_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.WYMO_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.26);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.WYMO_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.primitiveFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.handleDigging();
        if (this.up) {
            this.attackTimer = (float)((double)this.attackTimer + 0.3);
            if ((double)this.attackTimer > 1.5) {
                this.up = false;
            }
        } else {
            this.attackTimer = (float)((double)this.attackTimer - 0.2);
        }
    }

    @Override
    public boolean func_70692_ba() {
        return super.func_70692_ba() && this.following == null;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (this.getDigging()) {
            return false;
        }
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        this.up = true;
        this.attackTimer = 0.0f;
        this.field_70170_p.func_72960_a((Entity)this, (byte)12);
        boolean flag = false;
        this.func_184185_a(SPSounds.SWIPE, 2.0f, 1.0f);
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, entityIn.field_70165_t + 1.0, entityIn.field_70163_u + 1.0, entityIn.field_70161_v + 1.0).func_186662_g(1.5);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob instanceof EntityParasiteBase || !this.func_70685_l((Entity)mob) || !this.func_70652_k((Entity)mob)) continue;
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        this.madeRng = 0;
        if (source == DamageSource.field_76368_d) {
            return false;
        }
        if (this.bodiesT - 1 >= this.getBodyNumber()) {
            return false;
        }
        boolean flag = super.func_70097_a(source, amount);
        if (flag && this.following != null) {
            Entity follo = this.getFather(this.following);
            if (follo == null) {
                this.following = null;
                return flag;
            }
            if (!follo.func_70089_S()) {
                this.following = null;
                return flag;
            }
            follo.func_70097_a(source, amount * 0.5f);
        }
        return flag;
    }

    @Override
    protected void attackEntityFromCap(int go) {
        if (this.getBodyNumber() != 0) {
            return;
        }
        super.attackEntityFromCap(go);
    }

    public void func_70108_f(Entity entityIn) {
        if (this.getDigging()) {
            return;
        }
        super.func_70108_f(entityIn);
    }

    public float func_70047_e() {
        return 1.0f;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SoundEvents.field_187939_hm, 0.15f, 1.0f);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        return floo;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void spawnEffectsGore() {
        for (int i = 0; i <= 60; ++i) {
            if (i % 4 == 0) {
                this.spawnParticles(SPEnumParticle.GCLOUD, 150, 0, 0);
            }
            if (i % 5 != 0) continue;
            this.spawnParticles(SPEnumParticle.GSPLASH, 2, -1, -1);
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        if (this.following != null) {
            compound.func_186854_a("wormhead", this.following);
        }
        compound.func_74757_a("wormcanf", this.canF);
        compound.func_74774_a("wormpart", this.getBodyNumber());
        compound.func_74757_a("wormtail", this.getBodyTail());
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_186855_b("wormhead")) {
            this.following = compound.func_186857_a("wormhead");
        }
        if (compound.func_150297_b("wormcanf", 99)) {
            this.canF = compound.func_74767_n("wormcanf");
        }
        if (compound.func_150297_b("wormpart", 99)) {
            this.setBodyNumber(compound.func_74771_c("wormpart"));
        }
        if (compound.func_150297_b("wormtail", 99)) {
            this.setBodyTail(compound.func_74767_n("wormtail"));
        }
    }

    @Override
    public void skillBreakBlocks() {
        if (this.getBodyNumber() == 0) {
            super.skillBreakBlocks();
        }
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        if (this.getBodyNumber() == 0) {
            super.func_70074_a(entityLivingIn);
            return;
        }
        EntityParasiteBase head = this.getHead();
        if (head != null) {
            head.setKillC(1.0);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 12) {
            this.up = true;
            this.attackTimer = 0.0f;
        } else {
            super.func_70103_a(id);
        }
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        if (this.getBodyNumber() == 0) {
            super.func_70624_b(entitylivingbaseIn);
        }
    }

    @Override
    public void setTargetPos(BlockPos in) {
        this.digTarget = in;
    }

    @Override
    public BlockPos getTargetPos() {
        return this.digTarget;
    }

    @Override
    public boolean getDigging() {
        return (Boolean)this.field_70180_af.func_187225_a(DIG);
    }

    @Override
    public void setDigging(boolean in) {
        if (in) {
            this.func_184185_a(SPSounds.WYMO_DIG, 2.0f, this.func_70647_i());
        }
        this.field_70180_af.func_187227_b(DIG, (Object)in);
    }

    @Override
    public float getDigModel() {
        return this.diggingModel;
    }

    @Override
    public int getBodiesT() {
        return this.bodiesT;
    }

    @Override
    public void setBodiesT(int in) {
        this.bodiesT = in;
    }

    @Override
    public byte getBodyNumber() {
        return (Byte)this.field_70180_af.func_187225_a(PART);
    }

    @Override
    public void setBodyNumber(int texture) {
        this.field_70180_af.func_187227_b(PART, (Object)((byte)texture));
    }

    @Override
    public boolean getBodyTail() {
        return (Boolean)this.field_70180_af.func_187225_a(TAIL);
    }

    @Override
    public void setBodyTail(boolean in) {
        this.field_70180_af.func_187227_b(TAIL, (Object)in);
    }

    @Override
    public void setFollowing(EntityCanHaveBodies in) {
        if (in == null) {
            return;
        }
        this.following = in.getEntity().func_110124_au();
    }

    @Override
    public UUID getFollowing() {
        return this.following;
    }

    @Override
    public int getBodyLength() {
        return 2;
    }

    @Override
    public EntityCanHaveBodies getAnotherBody(World in) {
        return new EntityWymo(in);
    }

    @Override
    public void copyCopy(EntityCanHaveBodies in) {
        this.func_82149_j(in.getEntity());
        this.field_70163_u += 0.5;
    }

    @Override
    public Entity getEntity() {
        return this;
    }

    @Override
    public void onSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        this.func_180482_a(difficulty, livingdata);
    }

    @Override
    public void setCanF(boolean in) {
        this.canF = in;
    }

    @Override
    public boolean getCanF() {
        return this.canF;
    }

    @Override
    public void bodyPartEffect() {
        if (this.getDigging()) {
            return;
        }
        if (this.getBodyNumber() == 1) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.func_180425_c()).func_186662_g(3.0);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                EntityPlayer player;
                if (mob instanceof EntityParasiteBase || mob instanceof EntityPlayer && (player = (EntityPlayer)mob).func_184812_l_() || !this.attackEntityAsMobAOE((Entity)mob)) continue;
                return;
            }
        }
    }

    private void spawnGroundParticles() {
        IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
        if (state.func_177230_c() != Blocks.field_150350_a) {
            int id = Block.func_176210_f((IBlockState)state);
            for (int i = 0; i < 15; ++i) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 3.0f) - (double)this.field_70130_N, this.field_70163_u, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 3.0f) - (double)this.field_70130_N, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, new int[]{id});
            }
        }
    }

    @Override
    public double getKillPoints() {
        return this.killcount;
    }

    @Override
    public EntityParasiteBase getEvolution(World in) {
        return new EntityWymoAdapted(in);
    }

    @Override
    public EntityParasiteBase getHead() {
        if (this.following != null) {
            EntityCanHaveBodies follo = (EntityCanHaveBodies)this.getFather(this.following);
            if (follo == null) {
                return null;
            }
            if (!follo.getEntity().func_70089_S()) {
                return null;
            }
            return follo.getHead();
        }
        return this;
    }

    @Override
    public Entity getFather(UUID uuid) {
        List serverList = this.field_70170_p.field_72996_f;
        for (int x = 0; x < serverList.size(); ++x) {
            if (!uuid.equals(((Entity)serverList.get(x)).func_110124_au())) continue;
            return (Entity)serverList.get(x);
        }
        return null;
    }

    @Override
    public void handleDigging() {
        if (this.getDigging()) {
            this.bodiesTOut = 0;
            if (this.getBodyNumber() == 0) {
                this.spawnGroundParticles();
                if (this.field_70131_O > 0.25f) {
                    this.func_70105_a(this.field_70130_N, this.field_70131_O - 0.15f);
                }
                if (this.diggingModel < 2.4f) {
                    this.diggingModel += 0.08f;
                }
            } else {
                if (this.srpTicks == 10) {
                    ++this.bodiesT;
                }
                if (this.bodiesT >= this.getBodyNumber()) {
                    this.spawnGroundParticles();
                    if (this.field_70131_O > 0.25f) {
                        this.func_70105_a(this.field_70130_N, this.field_70131_O - 0.15f);
                    }
                    if (this.diggingModel < 2.4f) {
                        this.diggingModel += 0.08f;
                    }
                }
            }
        } else {
            this.bodiesT = 0;
            if (this.getBodyNumber() == 0) {
                if (this.field_70131_O < 1.2f) {
                    this.func_70105_a(this.field_70130_N, this.field_70131_O + 0.13f);
                    this.spawnGroundParticles();
                }
                if (this.diggingModel >= 0.0f) {
                    this.diggingModel -= 0.08f;
                }
            } else if (this.field_70165_t != this.field_70169_q && this.field_70161_v != this.field_70166_s) {
                if (this.field_70131_O < 1.2f) {
                    this.func_70105_a(this.field_70130_N, this.field_70131_O + 0.13f);
                    this.spawnGroundParticles();
                }
                if (this.diggingModel >= 0.0f) {
                    this.diggingModel -= 0.08f;
                }
            }
        }
    }
}

