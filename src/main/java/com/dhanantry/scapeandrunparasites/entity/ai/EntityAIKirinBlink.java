/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityKirin;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIKirinBlink
extends EntityAIBase {
    private final EntityKirin kirin;
    private final World world;
    private BlockPos targetPos = null;
    private int chargeTicks = 0;
    private static final int CHARGE_TIME = 60;
    private static final int COOLDOWN_TICKS = 200;
    private int nextAllowedTick = 0;
    private static final double MIN_FAR_DIST_SQ = 256.0;
    private static final int RADIUS_MAX = 24;
    private static final int MAX_TRIES = 64;

    public EntityAIKirinBlink(EntityKirin kirin) {
        this.kirin = kirin;
        this.world = kirin.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.kirin.field_70173_aa < this.nextAllowedTick || this.kirin.getParasiteStatus() >= 3) {
            return false;
        }
        EntityLivingBase target = this.kirin.func_70638_az();
        if (target == null) {
            return false;
        }
        if (!this.kirin.func_70635_at().func_75522_a((Entity)target)) {
            return false;
        }
        if (this.isIndoors(target)) {
            return false;
        }
        if (this.kirin.func_70068_e((Entity)target) <= 256.0) {
            return false;
        }
        BlockPos pos = this.findBlinkSpotNear(target);
        if (pos == null) {
            return false;
        }
        this.targetPos = pos;
        return true;
    }

    public boolean func_75253_b() {
        return this.chargeTicks > 0;
    }

    public boolean func_75252_g() {
        return false;
    }

    public void func_75249_e() {
        this.chargeTicks = 60;
        this.kirin.func_70661_as().func_75499_g();
        this.kirin.field_70159_w = 0.0;
        this.kirin.field_70181_x = 0.0;
        this.kirin.field_70179_y = 0.0;
        this.kirin.setBlinkCharge(this.targetPos, this.chargeTicks);
        this.kirin.func_184185_a(SRPSounds.OMBOO_DEATH, 1.0f, 0.9f);
    }

    public void func_75246_d() {
        if (this.targetPos == null) {
            this.chargeTicks = 0;
            return;
        }
        this.kirin.func_70661_as().func_75499_g();
        this.kirin.field_70179_y = 0.0;
        this.kirin.field_70181_x = 0.0;
        this.kirin.field_70159_w = 0.0;
        if (!this.world.field_72995_K && this.chargeTicks % 10 == 0) {
            this.kirin.func_184185_a(SoundEvents.field_187685_dH, 0.9f, 1.25f);
        }
        --this.chargeTicks;
        this.kirin.setBlinkCharge(this.targetPos, Math.max(this.chargeTicks, 0));
        if (!this.world.field_72995_K && this.chargeTicks <= 0) {
            this.kirin.func_184595_k((double)this.targetPos.func_177958_n() + 0.5, this.targetPos.func_177956_o(), (double)this.targetPos.func_177952_p() + 0.5);
            this.kirin.func_184185_a(SRPSounds.INFECTEDENDERMAN_PORTAL, 1.0f, 1.0f);
            this.doBlinkLifeSteal();
            this.nextAllowedTick = this.kirin.field_70173_aa + 200;
            this.kirin.clearBlinkCharge();
            this.func_75251_c();
        }
    }

    public void func_75251_c() {
        this.targetPos = null;
        this.chargeTicks = 0;
    }

    private boolean isIndoors(EntityLivingBase target) {
        BlockPos head = new BlockPos(target.field_70165_t, target.field_70163_u + (double)target.func_70047_e(), target.field_70161_v);
        for (int i = 0; i < 3; ++i) {
            if (!this.world.func_175678_i(head.func_177981_b(i))) continue;
            return false;
        }
        return true;
    }

    private BlockPos findBlinkSpotNear(EntityLivingBase target) {
        BlockPos tpos = target.func_180425_c();
        for (int i = 0; i < 64; ++i) {
            int[] ys;
            double r = 1.5 + this.kirin.func_70681_au().nextDouble() * 22.5;
            double a = this.kirin.func_70681_au().nextDouble() * Math.PI * 2.0;
            int x = MathHelper.func_76128_c((double)((double)tpos.func_177958_n() + 0.5 + r * Math.cos(a)));
            int z = MathHelper.func_76128_c((double)((double)tpos.func_177952_p() + 0.5 + r * Math.sin(a)));
            for (int y : ys = new int[]{tpos.func_177956_o(), tpos.func_177956_o() + 1, tpos.func_177956_o() - 1, tpos.func_177956_o() + 2, tpos.func_177956_o() - 2, tpos.func_177956_o() + 3, tpos.func_177956_o() - 3, tpos.func_177956_o() + 4, tpos.func_177956_o() - 4, tpos.func_177956_o() + 6, tpos.func_177956_o() - 6, tpos.func_177956_o() + 8, tpos.func_177956_o() - 8}) {
                BlockPos p = new BlockPos(x, y, z);
                if (!this.isSpotValid(p) || !this.world.func_175678_i(p.func_177984_a()) || !this.hasLineOfSight(target, p)) continue;
                return p;
            }
        }
        return null;
    }

    private void doBlinkLifeSteal() {
        if (this.world.field_72995_K) {
            return;
        }
        double radius = 5.0;
        AxisAlignedBB box = new AxisAlignedBB(this.kirin.field_70165_t - radius, this.kirin.field_70163_u - radius, this.kirin.field_70161_v - radius, this.kirin.field_70165_t + radius, this.kirin.field_70163_u + radius, this.kirin.field_70161_v + radius);
        List nearby = this.world.func_175647_a(EntityLivingBase.class, box, e -> e != null && e != this.kirin && e.func_70089_S() && !this.isSRPEntity((EntityLivingBase)e));
        if (nearby.isEmpty()) {
            this.world.func_184148_a(null, this.kirin.field_70165_t, this.kirin.field_70163_u, this.kirin.field_70161_v, SRPSounds.ALAFHA_HURT, SoundCategory.HOSTILE, 0.7f, 0.9f + this.kirin.func_70681_au().nextFloat() * 0.2f);
            return;
        }
        EntityLivingBase target = (EntityLivingBase)nearby.get(0);
        float currentHealth = target.func_110143_aJ();
        if (currentHealth <= 0.0f) {
            return;
        }
        float stolen = currentHealth * 0.5f;
        float newHealth = currentHealth - stolen;
        if (newHealth < 0.0f) {
            newHealth = 0.0f;
        }
        target.func_70606_j(newHealth);
        this.forceHurtAnim(target);
        this.world.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.CRUX_HURT, SoundCategory.HOSTILE, 1.0f, 0.8f + this.kirin.func_70681_au().nextFloat() * 0.4f);
        if (stolen > 0.0f) {
            this.kirin.func_70691_i(stolen);
        }
    }

    public void forceHurtAnim(EntityLivingBase target) {
        target.field_70737_aN = target.field_70738_aO = 10;
        target.field_70739_aP = 0.0f;
    }

    private boolean isSRPEntity(EntityLivingBase entity) {
        String name = entity.getClass().getName();
        return name.startsWith("com.dhanantry.scapeandrunparasites.entity");
    }

    private boolean isSpotValid(BlockPos p) {
        if (!this.world.func_175667_e(p)) {
            return false;
        }
        AxisAlignedBB aabb = new AxisAlignedBB(p).func_186664_h(0.05);
        if (!this.world.func_184144_a((Entity)this.kirin, aabb).isEmpty()) {
            return false;
        }
        BlockPos below = p.func_177977_b();
        return this.world.func_180495_p(below).func_185904_a().func_76220_a();
    }

    private boolean hasLineOfSight(EntityLivingBase target, BlockPos to) {
        Vec3d dest;
        Vec3d from = new Vec3d(this.kirin.field_70165_t, this.kirin.field_70163_u + (double)this.kirin.func_70047_e(), this.kirin.field_70161_v);
        RayTraceResult r = this.world.func_147447_a(from, dest = new Vec3d((double)to.func_177958_n() + 0.5, (double)to.func_177956_o() + 0.5, (double)to.func_177952_p() + 0.5), false, true, false);
        if (r != null && r.field_72313_a == RayTraceResult.Type.BLOCK) {
            return false;
        }
        return this.kirin.func_70635_at().func_75522_a((Entity)target);
    }

    public static boolean tryBlinkToNearbyLand(EntityKirin kirin, int horizontalRange, int verticalRange) {
        World world = kirin.field_70170_p;
        BlockPos origin = kirin.func_180425_c();
        BlockPos best = null;
        double bestDistSq = Double.MAX_VALUE;
        for (int dx = -horizontalRange; dx <= horizontalRange; ++dx) {
            block1: for (int dz = -horizontalRange; dz <= horizontalRange; ++dz) {
                int x = origin.func_177958_n() + dx;
                int z = origin.func_177952_p() + dz;
                for (int dy = verticalRange; dy >= -verticalRange; --dy) {
                    int y = origin.func_177956_o() + dy;
                    BlockPos p = new BlockPos(x, y, z);
                    if (!EntityAIKirinBlink.isRecoverySpotValid(world, p)) continue;
                    double d = kirin.func_174831_c(p);
                    if (!(d < bestDistSq)) continue block1;
                    bestDistSq = d;
                    best = p;
                    continue block1;
                }
            }
        }
        if (best != null) {
            return kirin.func_184595_k((double)best.func_177958_n() + 0.5, best.func_177956_o(), (double)best.func_177952_p() + 0.5);
        }
        return false;
    }

    private static boolean isRecoverySpotValid(World world, BlockPos p) {
        return world.func_180495_p(p.func_177977_b()).func_185917_h() && world.func_175623_d(p) && world.func_175623_d(p.func_177984_a()) && world.func_175678_i(p.func_177984_a());
    }
}

