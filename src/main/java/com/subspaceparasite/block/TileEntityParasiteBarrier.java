/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.SPacketUpdateTileEntity
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ITickable
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityParasiteBarrier
extends TileEntity
implements ITickable {
    private int centerChunkX;
    private int centerChunkZ;
    private int radiusChunks = 1;
    private final Map<Integer, Integer> insideTicks = new HashMap<Integer, Integer>();

    public void initCenterFromPos(BlockPos pos) {
        this.centerChunkX = pos.func_177958_n() >> 4;
        this.centerChunkZ = pos.func_177952_p() >> 4;
        this.notifySync();
    }

    public int getRadiusChunks() {
        return this.radiusChunks;
    }

    public void setRadiusChunks(int r) {
        if ((r = Math.max(1, Math.min(10, r))) != this.radiusChunks) {
            this.radiusChunks = r;
            this.notifySync();
            this.scrubMobsAroundField();
        }
    }

    public int[] currentWorldBounds() {
        int r = this.radiusChunks;
        int minChunkX = this.centerChunkX - r;
        int maxChunkX = this.centerChunkX + r;
        int minChunkZ = this.centerChunkZ - r;
        int maxChunkZ = this.centerChunkZ + r;
        int minX = minChunkX * 16;
        int maxX = (maxChunkX + 1) * 16 - 1;
        int minZ = minChunkZ * 16;
        int maxZ = (maxChunkZ + 1) * 16 - 1;
        return new int[]{minX, maxX, minZ, maxZ};
    }

    private boolean isInside(int[] bb, Entity e) {
        double x = e.field_70165_t;
        double z = e.field_70161_v;
        return x >= (double)bb[0] && x <= (double)bb[1] && z >= (double)bb[2] && z <= (double)bb[3];
    }

    private boolean isSRP(Entity e) {
        String n = e.getClass().getName();
        return n.startsWith("com.subspaceparasite") || n.contains(".subspaceparasite.");
    }

    public void func_73660_a() {
        if (this.field_145850_b == null) {
            return;
        }
        if (this.field_145850_b.field_72995_K) {
            this.spawnBorderParticlesClient();
            return;
        }
        if (this.field_145850_b.func_82737_E() % 2L != 0L) {
            return;
        }
        int[] bb = this.currentWorldBounds();
        AxisAlignedBB search = new AxisAlignedBB((double)(bb[0] - 48), 0.0, (double)(bb[2] - 48), (double)(bb[1] + 48), (double)this.field_145850_b.func_72800_K(), (double)(bb[3] + 48));
        List allLiving = this.field_145850_b.func_72872_a(EntityLiving.class, search);
        for (EntityLiving attacker : allLiving) {
            this.clearCrossFactionAggroIfTargetInside(attacker, bb);
        }
        List list = this.field_145850_b.func_175647_a(Entity.class, search, this::isSRP);
        if (list.isEmpty()) {
            this.insideTicks.clear();
            return;
        }
        for (Entity e : list) {
            this.clearAggroIfTargetInside(e, bb);
            this.clearPathIfTouchesInside(e, bb);
            if (this.isInside(bb, e)) {
                this.pushOrTeleportOutside(bb, e);
                if (this.isInside(bb, e)) {
                    int t = this.insideTicks.getOrDefault(e.func_145782_y(), 0) + 2;
                    if (t >= 100) {
                        this.pushOrTeleportOutside(bb, e);
                        this.pushOrTeleportOutside(bb, e);
                        t = 0;
                    }
                    this.insideTicks.put(e.func_145782_y(), t);
                    continue;
                }
                this.insideTicks.remove(e.func_145782_y());
                continue;
            }
            this.insideTicks.remove(e.func_145782_y());
        }
    }

    public void scrubMobsAroundField() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        int[] bb = this.currentWorldBounds();
        AxisAlignedBB search = new AxisAlignedBB((double)(bb[0] - 48), 0.0, (double)(bb[2] - 48), (double)(bb[1] + 48), (double)this.field_145850_b.func_72800_K(), (double)(bb[3] + 48));
        List list = this.field_145850_b.func_175647_a(Entity.class, search, this::isSRP);
        for (Entity e : list) {
            this.clearAggroIfTargetInside(e, bb);
            this.clearPathIfTouchesInside(e, bb);
        }
    }

    private void clearCrossFactionAggroIfTargetInside(EntityLiving attacker, int[] bb) {
        boolean targetInside;
        EntityLivingBase tgt = attacker.func_70638_az();
        if (tgt == null) {
            tgt = attacker.func_70643_av();
        }
        if (tgt == null) {
            return;
        }
        boolean bl = targetInside = tgt.field_70165_t >= (double)bb[0] && tgt.field_70165_t <= (double)bb[1] && tgt.field_70161_v >= (double)bb[2] && tgt.field_70161_v <= (double)bb[3];
        if (!targetInside) {
            return;
        }
        boolean attackerIsSRP = this.isSRP((Entity)attacker);
        boolean targetIsSRP = this.isSRP((Entity)tgt);
        if (attackerIsSRP || targetIsSRP) {
            attacker.func_70624_b(null);
            attacker.func_70604_c(null);
            if (attacker.func_70661_as() != null) {
                attacker.func_70661_as().func_75499_g();
            }
        }
    }

    private void clearAggroIfTargetInside(Entity e, int[] bb) {
        if (!(e instanceof EntityLiving)) {
            return;
        }
        EntityLiving el = (EntityLiving)e;
        EntityLivingBase tgt = el.func_70638_az();
        if (tgt != null && this.isInside(bb, (Entity)tgt)) {
            el.func_70624_b(null);
            el.func_70604_c(null);
            if (el.func_70661_as() != null) {
                el.func_70661_as().func_75499_g();
            }
            return;
        }
        EntityLivingBase rev = el.func_70643_av();
        if (rev != null && this.isInside(bb, (Entity)rev)) {
            el.func_70604_c(null);
            if (el.func_70661_as() != null) {
                el.func_70661_as().func_75499_g();
            }
        }
    }

    private void clearPathIfTouchesInside(Entity e, int[] bb) {
        if (!(e instanceof EntityLiving)) {
            return;
        }
        EntityLiving el = (EntityLiving)e;
        if (el.func_70661_as() == null) {
            return;
        }
        Path path = el.func_70661_as().func_75505_d();
        if (path == null) {
            return;
        }
        int n = path.func_75874_d();
        for (int i = 0; i < n; ++i) {
            PathPoint p = path.func_75877_a(i);
            if (p == null || p.field_75839_a < bb[0] || p.field_75839_a > bb[1] || p.field_75838_c < bb[2] || p.field_75838_c > bb[3]) continue;
            el.func_70661_as().func_75499_g();
            if (el.func_70638_az() != null && this.isInside(bb, (Entity)el.func_70638_az())) {
                el.func_70624_b(null);
                el.func_70604_c(null);
            }
            return;
        }
    }

    private void pushOrTeleportOutside(int[] bb, Entity e) {
        double x = e.field_70165_t;
        double z = e.field_70161_v;
        double dxMin = Math.abs(x - (double)bb[0]);
        double dxMax = Math.abs((double)bb[1] - x);
        double dzMin = Math.abs(z - (double)bb[2]);
        double dzMax = Math.abs((double)bb[3] - z);
        int edge = 0;
        double best = dxMin;
        if (dxMax < best) {
            best = dxMax;
            edge = 1;
        }
        if (dzMin < best) {
            best = dzMin;
            edge = 2;
        }
        if (dzMax < best) {
            best = dzMax;
            edge = 3;
        }
        double OUT = 0.6;
        double tx = x;
        double tz = z;
        if (edge == 0) {
            tx = (double)bb[0] - 0.6;
        } else if (edge == 1) {
            tx = (double)bb[1] + 0.6;
        } else {
            tz = edge == 2 ? (double)bb[2] - 0.6 : (double)bb[3] + 0.6;
        }
        BlockPos top = this.field_145850_b.func_175672_r(new BlockPos(MathHelper.func_76128_c((double)tx), 0, MathHelper.func_76128_c((double)tz)));
        double ty = (double)top.func_177956_o() + 0.1;
        e.func_70107_b(tx, ty, tz);
        e.field_70159_w *= 0.1;
        e.field_70179_y *= 0.1;
        e.field_70181_x = Math.min(e.field_70181_x, 0.0);
        e.field_70143_R = 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    private void spawnBorderParticlesClient() {
        EntityPlayerSP p = Minecraft.func_71410_x().field_71439_g;
        if (p == null) {
            return;
        }
        ItemStack mh = p.func_184614_ca();
        ItemStack oh = p.func_184592_cb();
        Predicate<ItemStack> isBarrierItem = st -> {
            if (st == null || st.func_190926_b()) {
                return false;
            }
            ResourceLocation rl = st.func_77973_b().getRegistryName();
            return rl != null && "subspaceparasite".equals(rl.func_110624_b()) && "parasite_barrier".equals(rl.func_110623_a());
        };
        if (!isBarrierItem.test(mh) && !isBarrierItem.test(oh)) {
            return;
        }
        int[] bb = this.currentWorldBounds();
        int minX = bb[0];
        int maxX = bb[1];
        int minZ = bb[2];
        int maxZ = bb[3];
        int R = 48;
        int STEP = 2;
        double y = MathHelper.func_151237_a((double)p.field_70163_u, (double)2.0, (double)(this.field_145850_b.func_72800_K() - 2));
        int px = MathHelper.func_76128_c((double)p.field_70165_t);
        int pz = MathHelper.func_76128_c((double)p.field_70161_v);
        int fromX = MathHelper.func_76125_a((int)(px - 48), (int)minX, (int)maxX);
        int toX = MathHelper.func_76125_a((int)(px + 48), (int)minX, (int)maxX);
        int fromZ = MathHelper.func_76125_a((int)(pz - 48), (int)minZ, (int)maxZ);
        int toZ = MathHelper.func_76125_a((int)(pz + 48), (int)minZ, (int)maxZ);
        double xA = (double)minX + 0.5;
        double xB = (double)maxX + 0.5;
        for (int z = fromZ; z <= toZ; z += 2) {
            double zz = (double)z + 0.5;
            this.field_145850_b.func_175688_a(EnumParticleTypes.BARRIER, xA, y, zz, 0.0, 0.0, 0.0, new int[0]);
            this.field_145850_b.func_175688_a(EnumParticleTypes.BARRIER, xB, y, zz, 0.0, 0.0, 0.0, new int[0]);
        }
        double zA = (double)minZ + 0.5;
        double zB = (double)maxZ + 0.5;
        for (int x = fromX; x <= toX; x += 2) {
            double xx = (double)x + 0.5;
            this.field_145850_b.func_175688_a(EnumParticleTypes.BARRIER, xx, y, zA, 0.0, 0.0, 0.0, new int[0]);
            this.field_145850_b.func_175688_a(EnumParticleTypes.BARRIER, xx, y, zB, 0.0, 0.0, 0.0, new int[0]);
        }
    }

    private void notifySync() {
        if (this.field_145850_b == null) {
            return;
        }
        IBlockState st = this.field_145850_b.func_180495_p(this.field_174879_c);
        this.field_145850_b.func_184138_a(this.field_174879_c, st, st, 3);
        this.func_70296_d();
    }

    public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
        super.func_189515_b(nbt);
        nbt.func_74768_a("centerChunkX", this.centerChunkX);
        nbt.func_74768_a("centerChunkZ", this.centerChunkZ);
        nbt.func_74768_a("radiusChunks", this.radiusChunks);
        return nbt;
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.centerChunkX = nbt.func_74762_e("centerChunkX");
        this.centerChunkZ = nbt.func_74762_e("centerChunkZ");
        int r = nbt.func_74762_e("radiusChunks");
        this.radiusChunks = Math.max(1, Math.min(10, r == 0 ? 1 : r));
    }

    public NBTTagCompound func_189517_E_() {
        return this.func_189515_b(new NBTTagCompound());
    }

    public void handleUpdateTag(NBTTagCompound nbt) {
        this.func_145839_a(nbt);
    }

    public SPacketUpdateTileEntity func_189518_D_() {
        return new SPacketUpdateTileEntity(this.field_174879_c, 0, this.func_189517_E_());
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.handleUpdateTag(pkt.func_148857_g());
    }
}

