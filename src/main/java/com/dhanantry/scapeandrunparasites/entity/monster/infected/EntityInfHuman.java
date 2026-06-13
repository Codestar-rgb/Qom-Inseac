/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.BlockDoor$EnumDoorHalf
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.monster.AbstractSkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigateGround
 *  net.minecraft.pathfinding.PathNodeType
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.pathfinding.WalkNodeProcessor
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
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
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityAICircleGroup;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHumanHead;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfHuman
extends EntityPInfected
implements EntityCanMelt {
    private static final DataParameter<Float> HEIGH = EntityDataManager.func_187226_a(EntityInfHuman.class, (DataSerializer)DataSerializers.field_187193_c);
    private static final DataParameter<Boolean> MELTING = EntityDataManager.func_187226_a(EntityInfHuman.class, (DataSerializer)DataSerializers.field_187198_h);
    private float aSize;
    private int sound;
    private int host;
    private BlockPos barrierGoal = null;
    private int barrierRetryTicks = 0;
    private double _lastX = 0.0;
    private double _lastZ = 0.0;
    private int _stuckTicks = 0;
    private int _shoveTicks = 0;
    private BlockPos lastHeardSoundPos;
    private int soundMemoryTicks;
    private static final UUID SHOVE_SPEED_UUID = UUID.fromString("8f5b3f68-7aa5-4c2c-8d9e-2e4a5f5583c3");
    private static final AttributeModifier SHOVE_SPEED_MOD = new AttributeModifier(SHOVE_SPEED_UUID, "gate_shove_boost", 0.6, 2);

    public EntityInfHuman(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.6f, 1.95f);
        this.aSize = 1.0f;
        this.sound = 0;
        this.canModRender = 1;
        this.type = (byte)11;
        this.thisMelting = true;
        if (this.func_70661_as() instanceof PathNavigateGround) {
            PathNavigateGround nav = (PathNavigateGround)this.func_70661_as();
            nav.func_179691_c(true);
            if (nav.func_189566_q() instanceof WalkNodeProcessor) {
                WalkNodeProcessor proc = (WalkNodeProcessor)nav.func_189566_q();
                proc.func_186317_a(true);
            }
        }
        this.func_184644_a(PathNodeType.DOOR_WOOD_CLOSED, 0.0f);
        this.func_184644_a(PathNodeType.DOOR_OPEN, 0.0f);
        this.func_184644_a(PathNodeType.DOOR_IRON_CLOSED, -1.0f);
    }

    @Override
    public int getParasiteIDRegister() {
        return 6;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infhumanCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAICircleGroup((EntityCreature)this, 1.15, 8, 4.0, 10.0, 16, (Predicate<? super Entity>)((Predicate)e -> e instanceof EntityInfHuman)));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFHUMAN_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFHUMAN_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.230000004172325);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFHUMAN_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFHUMAN_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(HEIGH, (Object)Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(MELTING, (Object)Boolean.FALSE);
    }

    @Override
    public void func_70636_d() {
        double dz;
        double dx;
        super.func_70636_d();
        this.melting();
        if (this.func_70661_as() instanceof PathNavigateGround) {
            ((PathNavigateGround)this.func_70661_as()).func_179691_c(true);
        }
        if (this.getSkin() == 111) {
            this.tickSoundMemory();
            if (this.getHeardSoundPos() == null) {
                AxisAlignedBB scanBox = this.func_174813_aQ().func_72314_b(16.0, 4.0, 16.0);
                List nearby = this.field_70170_p.func_175647_a(EntityPlayer.class, scanBox, p -> !p.func_175149_v() && !p.field_71075_bZ.field_75098_d && p.func_70089_S());
                EntityPlayer loudest = null;
                double loudestSpeedSq = 0.0;
                for (EntityPlayer p2 : nearby) {
                    boolean moving;
                    float walkedThisTick = p2.field_70140_Q - p2.field_70141_P;
                    boolean bl = moving = walkedThisTick > 0.01f || p2.func_70051_ag();
                    if (!moving) continue;
                    double loudness = walkedThisTick;
                    if (p2.func_70051_ag()) {
                        loudness *= 3.0;
                    }
                    if (!(loudness > loudestSpeedSq)) continue;
                    loudestSpeedSq = loudness;
                    loudest = p2;
                }
                if (loudest != null) {
                    BlockPos soundPos = new BlockPos(loudest.field_70165_t, loudest.field_70163_u, loudest.field_70161_v);
                    this.notifyHeardSound(soundPos, 60);
                    this.func_70624_b((EntityLivingBase)loudest);
                }
            }
            if (this.getHeardSoundPos() == null && this.func_70643_av() == null && this.func_70638_az() != null) {
                this.func_70624_b(null);
            }
        } else {
            this.clearHeardSound();
        }
        this._stuckTicks = (dx = this.field_70165_t - this._lastX) * dx + (dz = this.field_70161_v - this._lastZ) * dz < 0.0025 ? ++this._stuckTicks : 0;
        this._lastX = this.field_70165_t;
        this._lastZ = this.field_70161_v;
        if (this.func_70638_az() != null) {
            double d2;
            if (this.func_70661_as().func_75500_f() || this._stuckTicks > 20) {
                if (this.barrierRetryTicks <= 0 && this.retargetToNearestEntrance(12)) {
                    this.barrierRetryTicks = 40;
                }
            } else if (this.barrierRetryTicks > 0) {
                --this.barrierRetryTicks;
            }
            if (this.barrierGoal != null && (d2 = this.func_174818_b(this.barrierGoal)) <= 4.0) {
                IBlockState st = this.field_70170_p.func_180495_p(this.barrierGoal);
                this.openBarrierAt(this.barrierGoal, st);
                this.barrierGoal = null;
                this.func_70661_as().func_75499_g();
                this.func_70661_as().func_75497_a((Entity)this.func_70638_az(), 1.5);
            }
            if (!this.func_70661_as().func_75500_f()) {
                this.tryOpenNextPathBarrier();
            }
            this.tryOpenNearbyBarriers();
            if (this._shoveTicks == 0 && this._stuckTicks >= 15 && this.nearOpenBarrier(1.5)) {
                this._shoveTicks = 8;
                this._stuckTicks = 0;
            }
            if (this._shoveTicks > 0) {
                IAttributeInstance speedAttr = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
                if (!speedAttr.func_180374_a(SHOVE_SPEED_MOD)) {
                    speedAttr.func_111121_a(SHOVE_SPEED_MOD);
                }
                Vec3d look = this.func_70040_Z();
                this.func_70024_g(look.field_72450_a * 0.15, 0.0, look.field_72449_c * 0.15);
                if (this.field_70122_E && this._shoveTicks == 8) {
                    this.field_70181_x += 0.05;
                }
                List crowd = this.field_70170_p.func_175647_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(0.6, 0.2, 0.6), e -> e != this);
                for (EntityLivingBase e2 : crowd) {
                    double ex = e2.field_70165_t - this.field_70165_t;
                    double ez = e2.field_70161_v - this.field_70161_v;
                    double dSq = ex * ex + ez * ez + 1.0E-4;
                    e2.func_70024_g(ex / dSq * 0.08, 0.0, ez / dSq * 0.08);
                }
                --this._shoveTicks;
                if (this._shoveTicks == 0 && speedAttr.func_180374_a(SHOVE_SPEED_MOD)) {
                    speedAttr.func_111124_b(SHOVE_SPEED_MOD);
                }
            }
        }
    }

    @Override
    public void func_70624_b(@Nullable EntityLivingBase entitylivingbaseIn) {
        EntityPlayer player;
        if (entitylivingbaseIn instanceof EntityPlayer && ((player = (EntityPlayer)entitylivingbaseIn).func_175149_v() || player.field_71075_bZ.field_75098_d)) {
            super.func_70624_b(null);
            return;
        }
        super.func_70624_b(entitylivingbaseIn);
    }

    private boolean nearOpenBarrier(double radius) {
        BlockPos base = new BlockPos((Entity)this);
        int r = (int)Math.ceil(radius);
        for (int y = 0; y <= 1; ++y) {
            for (int dx = -r; dx <= r; ++dx) {
                for (int dz = -r; dz <= r; ++dz) {
                    BlockDoor d;
                    BlockPos p = base.func_177982_a(dx, y, dz);
                    IBlockState st = this.field_70170_p.func_180495_p(p);
                    if (!(st.func_177230_c() instanceof BlockDoor ? (d = (BlockDoor)st.func_177230_c()).func_149688_o(st) == Material.field_151575_d && (st.func_177229_b((IProperty)BlockDoor.field_176523_O) != BlockDoor.EnumDoorHalf.UPPER || (st = this.field_70170_p.func_180495_p(p = p.func_177977_b())).func_177230_c() instanceof BlockDoor) && (Boolean)st.func_177229_b((IProperty)BlockDoor.field_176519_b) != false : st.func_177230_c() instanceof BlockFenceGate && st.func_185904_a() == Material.field_151575_d && (Boolean)st.func_177229_b((IProperty)BlockFenceGate.field_176466_a) != false)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean retargetToNearestEntrance(int radius) {
        BlockPos me = new BlockPos((Entity)this);
        BlockPos bestBarrier = null;
        double bestDist2 = Double.MAX_VALUE;
        for (int dy = 0; dy <= 1; ++dy) {
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    double d2;
                    BlockPos p = me.func_177982_a(dx, dy, dz);
                    IBlockState st = this.field_70170_p.func_180495_p(p);
                    if (!EntityInfHuman.isWoodDoor(st) && !EntityInfHuman.isWoodGate(st)) continue;
                    if (EntityInfHuman.isWoodDoor(st)) {
                        p = this.normalizeDoorPos(p, st);
                    }
                    if (!((d2 = p.func_177951_i((Vec3i)me)) < bestDist2)) continue;
                    bestDist2 = d2;
                    bestBarrier = p;
                }
            }
        }
        if (bestBarrier == null) {
            return false;
        }
        List<BlockPos> around = Arrays.asList(bestBarrier.func_177978_c(), bestBarrier.func_177968_d(), bestBarrier.func_177974_f(), bestBarrier.func_177976_e());
        around.sort(Comparator.comparingDouble(pos -> pos.func_177954_c(this.field_70165_t, this.field_70163_u, this.field_70161_v)));
        for (BlockPos ap : around) {
            boolean ok;
            if (!this.field_70170_p.func_180495_p(ap).func_185904_a().func_76222_j() || !(ok = this.func_70661_as().func_75492_a((double)ap.func_177958_n() + 0.5, (double)ap.func_177956_o() + 0.0, (double)ap.func_177952_p() + 0.5, 1.5))) continue;
            this.barrierGoal = bestBarrier;
            return true;
        }
        return false;
    }

    private static boolean isWoodDoor(IBlockState s) {
        if (!(s.func_177230_c() instanceof BlockDoor)) {
            return false;
        }
        BlockDoor d = (BlockDoor)s.func_177230_c();
        return d.func_149688_o(s) == Material.field_151575_d;
    }

    private static boolean isWoodGate(IBlockState s) {
        if (!(s.func_177230_c() instanceof BlockFenceGate)) {
            return false;
        }
        return s.func_185904_a() == Material.field_151575_d;
    }

    private BlockPos normalizeDoorPos(BlockPos pos, IBlockState state) {
        if (state.func_177230_c() instanceof BlockDoor && state.func_177229_b((IProperty)BlockDoor.field_176523_O) == BlockDoor.EnumDoorHalf.UPPER) {
            return pos.func_177977_b();
        }
        return pos;
    }

    private void openBarrierAt(BlockPos pos, IBlockState state) {
        if (EntityInfHuman.isWoodDoor(state)) {
            if (((Boolean)(state = this.field_70170_p.func_180495_p(pos = this.normalizeDoorPos(pos, state))).func_177229_b((IProperty)BlockDoor.field_176519_b)).booleanValue()) {
                return;
            }
            ((BlockDoor)state.func_177230_c()).func_176512_a(this.field_70170_p, pos, true);
            return;
        }
        if (EntityInfHuman.isWoodGate(state)) {
            if (((Boolean)state.func_177229_b((IProperty)BlockFenceGate.field_176466_a)).booleanValue()) {
                return;
            }
            this.field_70170_p.func_180501_a(pos, state.func_177226_a((IProperty)BlockFenceGate.field_176466_a, (Comparable)Boolean.valueOf(true)), 10);
        }
    }

    private void tryOpenNextPathBarrier() {
        Path path = this.func_70661_as().func_75505_d();
        if (path == null) {
            return;
        }
        int i = path.func_75873_e();
        if (i >= path.func_75874_d()) {
            return;
        }
        PathPoint next = path.func_75877_a(i);
        if (next == null) {
            return;
        }
        BlockPos pos = new BlockPos(next.field_75839_a, next.field_75837_b, next.field_75838_c);
        this.openBarrierAt(pos, this.field_70170_p.func_180495_p(pos));
    }

    private void tryOpenNearbyBarriers() {
        BlockPos[] spots;
        BlockPos base = new BlockPos((Entity)this);
        EnumFacing face = this.func_174811_aO();
        for (BlockPos p : spots = new BlockPos[]{base, base.func_177984_a(), base.func_177972_a(face), base.func_177972_a(face).func_177984_a(), base.func_177972_a(face.func_176746_e()), base.func_177972_a(face.func_176746_e()).func_177984_a(), base.func_177972_a(face.func_176735_f()), base.func_177972_a(face.func_176735_f()).func_177984_a()}) {
            IBlockState st = this.field_70170_p.func_180495_p(p);
            if (!EntityInfHuman.isWoodDoor(st) && !EntityInfHuman.isWoodGate(st)) continue;
            this.openBarrierAt(p, st);
        }
    }

    @Override
    protected void func_70609_aI() {
        super.func_70609_aI();
        if (this.getTHeigh() < 1.57f && !this.field_70170_p.field_72995_K) {
            this.setTHeigh(0.13f);
        }
        if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() <= SRPAttributes.INFHUMAN_HEADCHANCE) {
            EntityInfHumanHead out = new EntityInfHumanHead(this.field_70170_p);
            out.func_82149_j((Entity)this);
            out.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)this)), null);
            out.setSkin(this.getSkin());
            out.cannotDespawn(false);
            this.field_70170_p.func_72838_d((Entity)out);
        }
    }

    public void notifyHeardSound(BlockPos pos, int lifeTicks) {
        if (this.getSkin() != 111) {
            return;
        }
        this.lastHeardSoundPos = pos;
        this.soundMemoryTicks = lifeTicks;
    }

    public BlockPos getHeardSoundPos() {
        return this.lastHeardSoundPos;
    }

    public void tickSoundMemory() {
        if (this.soundMemoryTicks > 0) {
            --this.soundMemoryTicks;
            if (this.soundMemoryTicks <= 0) {
                this.lastHeardSoundPos = null;
            }
        }
    }

    public void clearHeardSound() {
        this.lastHeardSoundPos = null;
        this.soundMemoryTicks = 0;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        if (!this.field_70170_p.field_72995_K) {
            if (SRPConfigMobs.hostEnabled && entityLivingIn instanceof AbstractSkeleton) {
                ++this.host;
                if (this.host >= SRPConfigMobs.hostSkele) {
                    this.particleStatus((byte)7);
                    EntityHost host = new EntityHost(this.field_70170_p);
                    host.func_82149_j((Entity)this);
                    host.func_180482_a(this.field_70170_p.func_175649_E(this.func_180425_c()), null);
                    this.field_70170_p.func_72838_d((Entity)host);
                    host.particleStatus((byte)7);
                    this.func_70106_y();
                }
            } else {
                super.func_70074_a(entityLivingIn);
            }
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && this.field_70146_Z.nextDouble() < (double)SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
            SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
        }
        return flag;
    }

    @Override
    public void melt() {
        this.setWait(1000);
        this.field_70180_af.func_187227_b(HEIGH, (Object)Float.valueOf(1.95f));
        this.field_70180_af.func_187227_b(MELTING, (Object)Boolean.TRUE);
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
                if ((double)this.getTHeigh() <= 0.7 || this.sound >= 127) {
                    EntityLesh out = new EntityLesh(this.field_70170_p);
                    out.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                    if (this.func_95999_t() != null) {
                        out.func_96094_a(this.func_95999_t());
                    }
                    this.func_70106_y();
                    this.field_70170_p.func_72838_d((Entity)out);
                    out.setLegs(SRPAttributes.INFHUMAN_V, false);
                }
            } else {
                this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 106, 0);
                this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
            }
        }
    }

    public boolean func_70685_l(Entity entityIn) {
        if (this.getSkin() == 111) {
            double dSq = this.func_70068_e(entityIn);
            return dSq <= 4.0;
        }
        return super.func_70685_l(entityIn);
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

    public float func_70047_e() {
        return 1.73f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INFECTEDHUMAN_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.INFECTEDHUMAN_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.INFECTEDHUMAN_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.field_187939_hm;
    }

    @Override
    public EntityPFeral getFeral(World in) {
        return new EntityFerHuman(in);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), 0.15f, 1.0f);
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74768_a("parasitehost", this.host);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("parasitehost", 99)) {
            this.host = compound.func_74762_e("parasitehost");
        }
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData data = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
            this.setSkin(this.field_70170_p.field_73012_v.nextInt(3) + 1);
            if (this.field_70146_Z.nextDouble() < (double)0.01f) {
                IAttributeInstance moveAttr;
                this.setSkin(111);
                if (this.func_110148_a(SharedMonsterAttributes.field_111265_b) != null) {
                    this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(12.0);
                }
                if ((moveAttr = this.func_110148_a(SharedMonsterAttributes.field_111263_d)) != null) {
                    moveAttr.func_111128_a(0.32);
                }
            }
        } else {
            this.setSkin(0);
        }
        return data;
    }
}

