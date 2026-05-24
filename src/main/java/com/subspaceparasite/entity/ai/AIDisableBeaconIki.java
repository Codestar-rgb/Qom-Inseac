/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityBeacon
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.monster.primitive.EntityIki;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class AIDisableBeaconIki
extends EntityAIBase {
    private static final double SCAN_RADIUS_D = 64.0;
    private static final int PICKUP_RADIUS = 6;
    private static final int RESCAN_TICKS = 100;
    private static final double REACH_SQ = 4.0;
    private static final int STALL_TICKS = 40;
    private static final int PHASE_BUDGET = 200;
    private final EntityIki host;
    private IBlockState carried = null;
    private TileEntityBeacon targetBeacon = null;
    private BlockPos beaconPos = null;
    private BlockPos placePos = null;
    private int timeout = 0;
    private int cooldown = 0;
    private Phase phase = Phase.SEARCH;
    private BlockPos pickupPos = null;
    private int progressTicks = 0;
    private double lastDistSq = Double.MAX_VALUE;

    public AIDisableBeaconIki(EntityIki host) {
        this.host = host;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        if (this.host.func_70638_az() != null) {
            return false;
        }
        return !this.host.field_70170_p.field_72995_K && ForgeEventFactory.getMobGriefingEvent((World)this.host.field_70170_p, (Entity)this.host) && this.host.func_70089_S();
    }

    public boolean func_75253_b() {
        if (this.host.func_70638_az() != null) {
            return false;
        }
        return this.func_75250_a();
    }

    public void func_75251_c() {
        this.targetBeacon = null;
        this.beaconPos = null;
        this.placePos = null;
        this.pickupPos = null;
        this.carried = null;
        this.timeout = 0;
        this.progressTicks = 0;
        this.lastDistSq = Double.MAX_VALUE;
        this.phase = Phase.SEARCH;
    }

    public void func_75246_d() {
        if (this.host.field_70170_p.field_72995_K) {
            return;
        }
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        switch (this.phase) {
            case SEARCH: {
                if (this.cooldown > 0) break;
                this.targetBeacon = this.findNearestEmittingBeacon();
                if (this.targetBeacon != null) {
                    this.beaconPos = this.targetBeacon.func_174877_v();
                    this.placePos = null;
                    this.pickupPos = null;
                    this.phase = this.carried == null ? Phase.PICK_TARGET_BLOCK : Phase.MOVE_TO_BEAM;
                    this.timeout = 200;
                    break;
                }
                this.cooldown = 100;
                break;
            }
            case PICK_TARGET_BLOCK: {
                this.pickupPos = this.findNearbyPickupBlock();
                this.progressTicks = 0;
                this.lastDistSq = Double.MAX_VALUE;
                if (this.pickupPos != null) {
                    this.phase = Phase.MOVE_TO_BLOCK;
                    this.timeout = 200;
                    break;
                }
                this.cooldown = 100;
                this.phase = Phase.COOLDOWN;
                break;
            }
            case MOVE_TO_BLOCK: {
                if (this.pickupPos == null) {
                    this.phase = Phase.PICK_TARGET_BLOCK;
                    break;
                }
                if (!this.isPickupStillValid(this.pickupPos)) {
                    this.phase = Phase.PICK_TARGET_BLOCK;
                    break;
                }
                this.flyTo(this.pickupPos, 1.1);
                double d2 = this.host.func_70092_e((double)this.pickupPos.func_177958_n() + 0.5, (double)this.pickupPos.func_177956_o() + 0.5, (double)this.pickupPos.func_177952_p() + 0.5);
                if (d2 + 0.01 < this.lastDistSq) {
                    this.lastDistSq = d2;
                    this.progressTicks = 0;
                } else {
                    ++this.progressTicks;
                }
                if (d2 <= 4.0) {
                    this.phase = Phase.PICKUP;
                    break;
                }
                if (--this.timeout > 0 && this.progressTicks < 40) break;
                this.phase = Phase.PICK_TARGET_BLOCK;
                break;
            }
            case PICKUP: {
                if (this.pickupPos != null && this.isPickupStillValid(this.pickupPos)) {
                    this.carried = this.host.field_70170_p.func_180495_p(this.pickupPos);
                    this.host.field_70170_p.func_175698_g(this.pickupPos);
                }
                this.pickupPos = null;
                this.progressTicks = 0;
                this.lastDistSq = Double.MAX_VALUE;
                this.phase = Phase.MOVE_TO_BEAM;
                this.timeout = 200;
                break;
            }
            case MOVE_TO_BEAM: {
                BlockPos alt;
                if (!this.isBeaconEmitting(this.beaconPos)) {
                    this.phase = Phase.SEARCH;
                    break;
                }
                if (this.placePos == null) {
                    this.placePos = this.pickBeamPlacement(this.beaconPos);
                    if (this.placePos == null) {
                        this.cooldown = 100;
                        this.phase = Phase.COOLDOWN;
                        break;
                    }
                    this.progressTicks = 0;
                    this.lastDistSq = Double.MAX_VALUE;
                }
                this.flyTo(this.placePos, 1.1);
                double d2 = this.host.func_70092_e((double)this.placePos.func_177958_n() + 0.5, (double)this.placePos.func_177956_o() + 0.5, (double)this.placePos.func_177952_p() + 0.5);
                if (d2 + 0.01 < this.lastDistSq) {
                    this.lastDistSq = d2;
                    this.progressTicks = 0;
                } else {
                    ++this.progressTicks;
                }
                if (this.progressTicks == 20 && (alt = this.pickBeamPlacement(this.beaconPos)) != null && !alt.equals((Object)this.placePos)) {
                    this.placePos = alt;
                    this.progressTicks = 0;
                    this.lastDistSq = Double.MAX_VALUE;
                }
                if (d2 <= 4.0) {
                    this.phase = Phase.PLACE;
                    break;
                }
                if (--this.timeout > 0 && this.progressTicks < 40) break;
                this.cooldown = 100;
                this.phase = Phase.COOLDOWN;
                break;
            }
            case PLACE: {
                if (this.carried != null && this.placePos != null && this.canPlaceAt(this.placePos)) {
                    this.host.field_70170_p.func_180501_a(this.placePos, this.carried, 3);
                }
                this.carried = null;
                this.cooldown = 100;
                this.phase = Phase.COOLDOWN;
                break;
            }
            case COOLDOWN: {
                if (this.cooldown > 0) break;
                this.phase = Phase.SEARCH;
            }
        }
    }

    private boolean canPlaceAt(BlockPos pos) {
        if (!this.host.field_70170_p.func_175667_e(pos)) {
            return false;
        }
        IBlockState st = this.host.field_70170_p.func_180495_p(pos);
        return st.func_185904_a().func_76222_j() || st.func_177230_c() == Blocks.field_150350_a;
    }

    private boolean isBeaconStillActive() {
        if (this.targetBeacon == null) {
            return false;
        }
        TileEntity te = this.host.field_70170_p.func_175625_s(this.targetBeacon.func_174877_v());
        if (!(te instanceof TileEntityBeacon)) {
            return false;
        }
        return ((TileEntityBeacon)te).func_191979_s() > 0;
    }

    private void flyTo(BlockPos pos, double speed) {
        this.host.func_70605_aq().func_75642_a((double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o(), (double)pos.func_177952_p() + 0.5, speed);
        this.host.func_70671_ap().func_75650_a((double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o() + 0.5, (double)pos.func_177952_p() + 0.5, 30.0f, 30.0f);
    }

    private TileEntityBeacon findNearestEmittingBeacon() {
        double bestD2 = 4096.0;
        TileEntityBeacon best = null;
        for (TileEntity te : this.host.field_70170_p.field_147482_g) {
            BlockPos p;
            double d2;
            if (!(te instanceof TileEntityBeacon) || (d2 = this.host.func_70092_e((double)(p = te.func_174877_v()).func_177958_n() + 0.5, (double)p.func_177956_o() + 0.5, (double)p.func_177952_p() + 0.5)) > bestD2 || !this.isBeaconEmitting(p)) continue;
            best = (TileEntityBeacon)te;
            bestD2 = d2;
        }
        return best;
    }

    private boolean isPickupStillValid(BlockPos p) {
        if (!this.host.field_70170_p.func_175667_e(p)) {
            return false;
        }
        if (!this.host.field_70170_p.func_175623_d(p.func_177984_a())) {
            return false;
        }
        IBlockState st = this.host.field_70170_p.func_180495_p(p);
        if (this.host.field_70170_p.func_175625_s(p) != null) {
            return false;
        }
        return this.isPickupWhitelisted(st.func_177230_c());
    }

    private boolean isBeaconEmitting(BlockPos beacon) {
        if (beacon == null) {
            return false;
        }
        TileEntity te = this.host.field_70170_p.func_175625_s(beacon);
        if (!(te instanceof TileEntityBeacon)) {
            return false;
        }
        if (((TileEntityBeacon)te).func_191979_s() <= 0) {
            return false;
        }
        int top = this.host.field_70170_p.func_72800_K();
        for (int y = beacon.func_177956_o() + 1; y < top; ++y) {
            BlockPos q = new BlockPos(beacon.func_177958_n(), y, beacon.func_177952_p());
            Block b = this.host.field_70170_p.func_180495_p(q).func_177230_c();
            if (b == Blocks.field_150350_a || b == Blocks.field_150359_w || b == Blocks.field_150399_cn || b == Blocks.field_150397_co) continue;
            return false;
        }
        return true;
    }

    private BlockPos pickBeamPlacement(BlockPos beacon) {
        BlockPos p = this.pickBeamPlacementStrict(beacon);
        if (p == null) {
            p = this.pickBeamPlacementLoose(beacon);
        }
        return p;
    }

    private BlockPos pickBeamPlacementStrict(BlockPos beacon) {
        BlockPos c;
        int y;
        int bx = beacon.func_177958_n();
        int by = beacon.func_177956_o();
        int bz = beacon.func_177952_p();
        int top = this.host.field_70170_p.func_72800_K();
        for (y = Math.min(top - 1, by + 64); y >= by + 1; --y) {
            c = new BlockPos(bx, y, bz);
            if (!this.canPlaceAt(c) || !this.hasAttachSupport(c) || !this.hasLineOfSightTo(c)) continue;
            return c;
        }
        for (y = by + 1; y < Math.min(top, by + 64); ++y) {
            c = new BlockPos(bx, y, bz);
            if (!this.canPlaceAt(c) || !this.hasAttachSupport(c) || !this.hasLineOfSightTo(c)) continue;
            return c;
        }
        return null;
    }

    private boolean hasAttachSupport(BlockPos pos) {
        for (EnumFacing f : EnumFacing.values()) {
            IBlockState ns;
            BlockPos n = pos.func_177972_a(f);
            if (!this.host.field_70170_p.func_175667_e(n) || (ns = this.host.field_70170_p.func_180495_p(n)).func_177230_c() == Blocks.field_150350_a || !ns.isSideSolid((IBlockAccess)this.host.field_70170_p, n, f.func_176734_d())) continue;
            return true;
        }
        return false;
    }

    private BlockPos pickBeamPlacementLoose(BlockPos beacon) {
        BlockPos c;
        int y;
        int bx = beacon.func_177958_n();
        int by = beacon.func_177956_o();
        int bz = beacon.func_177952_p();
        int top = this.host.field_70170_p.func_72800_K();
        for (y = Math.min(top - 1, by + 64); y >= by + 1; --y) {
            c = new BlockPos(bx, y, bz);
            if (!this.canPlaceAt(c) || !this.hasAttachSupport(c)) continue;
            return c;
        }
        for (y = by + 1; y < Math.min(top, by + 64); ++y) {
            c = new BlockPos(bx, y, bz);
            if (!this.canPlaceAt(c) || !this.hasAttachSupport(c)) continue;
            return c;
        }
        return null;
    }

    private boolean hasLineOfSightTo(BlockPos pos) {
        Vec3d end;
        double ez;
        double ex = this.host.field_70165_t;
        double ey = this.host.field_70163_u + (double)this.host.func_70047_e();
        Vec3d start = new Vec3d(ex, ey, ez = this.host.field_70161_v);
        RayTraceResult hit = this.host.field_70170_p.func_147447_a(start, end = new Vec3d((double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o() + 0.5, (double)pos.func_177952_p() + 0.5), false, true, false);
        return hit == null || hit.field_72313_a == RayTraceResult.Type.MISS;
    }

    private BlockPos findNearbyPickupBlock() {
        BlockPos origin = new BlockPos((Entity)this.host);
        int r = 6;
        for (int dy = -1; dy <= 1; ++dy) {
            for (int dx = -r; dx <= r; ++dx) {
                for (int dz = -r; dz <= r; ++dz) {
                    IBlockState st;
                    Block b;
                    BlockPos p = origin.func_177982_a(dx, dy, dz);
                    if (!this.host.field_70170_p.func_175667_e(p) || !this.isPickupWhitelisted(b = (st = this.host.field_70170_p.func_180495_p(p)).func_177230_c()) || this.host.field_70170_p.func_175625_s(p) != null || !this.host.field_70170_p.func_175623_d(p.func_177984_a()) || b == Blocks.field_150461_bJ) continue;
                    return p;
                }
            }
        }
        return null;
    }

    private boolean isPickupWhitelisted(Block b) {
        return b == Blocks.field_150346_d || b == Blocks.field_150349_c || b == Blocks.field_150354_m || b == Blocks.field_150351_n || b == Blocks.field_150347_e || b == Blocks.field_150424_aL;
    }

    private TileEntityBeacon findNearestActiveBeacon() {
        double bestD2 = 4096.0;
        TileEntityBeacon best = null;
        for (TileEntity te : this.host.field_70170_p.field_147482_g) {
            TileEntityBeacon b;
            BlockPos p;
            double d2;
            if (!(te instanceof TileEntityBeacon) || (d2 = this.host.func_70092_e((double)(p = te.func_174877_v()).func_177958_n() + 0.5, (double)p.func_177956_o() + 0.5, (double)p.func_177952_p() + 0.5)) > bestD2 || (b = (TileEntityBeacon)te).func_191979_s() <= 0) continue;
            best = b;
            bestD2 = d2;
        }
        return best;
    }

    private static enum Phase {
        SEARCH,
        PICK_TARGET_BLOCK,
        MOVE_TO_BLOCK,
        PICKUP,
        MOVE_TO_BEAM,
        PLACE,
        COOLDOWN;

    }
}

