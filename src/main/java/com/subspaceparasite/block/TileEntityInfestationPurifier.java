/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ITickable
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockInfestationPurifier;
import com.subspaceparasite.block.PurifyMappings;
import com.subspaceparasite.network.MsgSpawnPureParticles;
import com.subspaceparasite.network.SPNetwork;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TileEntityInfestationPurifier
extends TileEntity
implements ITickable {
    private static final int MAX_TOTAL = 131072;
    private static final int NODES_PER_TICK = 32;
    private static final int TICK_INTERVAL = 1;
    private static final int SPREAD_RADIUS = 6;
    private static final ResourceLocation INFEST_REMAIN_RL = new ResourceLocation("subspaceparasite", "infestremain");
    private static Block CACHED_INFEST_REMAIN = null;
    private final ArrayDeque<BlockPos> queue = new ArrayDeque();
    private final HashSet<Long> visited = new HashSet();
    private final HashSet<Long> inQueue = new HashSet();
    private boolean running = false;
    private int total = 0;
    private int tickGate = 0;
    private UUID starter;

    public void startAt(BlockPos start, UUID starterUuid) {
        this.queue.clear();
        this.visited.clear();
        this.inQueue.clear();
        this.running = true;
        this.total = 0;
        this.tickGate = 0;
        this.starter = starterUuid;
        this.offer(start);
        this.func_70296_d();
    }

    private void offer(BlockPos p) {
        long k = p.func_177986_g();
        if (this.visited.contains(k) || this.inQueue.contains(k)) {
            return;
        }
        this.queue.add(p);
        this.inQueue.add(k);
    }

    public void func_73660_a() {
        BlockPos cur;
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K || !this.running) {
            return;
        }
        if (this.tickGate++ % 1 != 0) {
            return;
        }
        if (CACHED_INFEST_REMAIN == null) {
            CACHED_INFEST_REMAIN = (Block)Block.field_149771_c.func_82594_a((Object)INFEST_REMAIN_RL);
        }
        int processed = 0;
        while (processed < 32 && !this.queue.isEmpty() && this.total < 131072 && (cur = this.queue.poll()) != null) {
            IBlockState finalState;
            IBlockState st;
            long curKey = cur.func_177986_g();
            this.inQueue.remove(curKey);
            if (!this.visited.add(curKey) || !PurifyMappings.isSrp(st = this.field_145850_b.func_180495_p(cur))) continue;
            IBlockState vanilla = PurifyMappings.mapToVanillaState(st);
            if (vanilla != null && this.field_145850_b.func_180501_a(cur, finalState = BlockInfestationPurifier.tryCopyCommonProps(st, vanilla), 3)) {
                ++this.total;
                BlockPos up = cur.func_177984_a();
                if (this.field_145850_b.func_175623_d(up) && CACHED_INFEST_REMAIN != null) {
                    this.field_145850_b.func_180501_a(up, CACHED_INFEST_REMAIN.func_176223_P(), 3);
                }
                if (this.field_145850_b instanceof WorldServer && this.field_145850_b.func_72935_r() && this.field_145850_b.func_175678_i(cur.func_177984_a())) {
                    double x = (double)cur.func_177958_n() + 0.5;
                    double y = (double)cur.func_177956_o() + 1.0;
                    double z = (double)cur.func_177952_p() + 0.5;
                    SPNetwork.CHANNEL.sendToAllAround((IMessage)new MsgSpawnPureParticles(x, y, z, 8 + this.field_145850_b.field_73012_v.nextInt(4), 0), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.getDimension(), x, y, z, 64.0));
                }
            }
            BlockPos minN = cur.func_177982_a(-1, -1, -1);
            BlockPos maxN = cur.func_177982_a(1, 1, 1);
            for (BlockPos p : BlockPos.func_177980_a((BlockPos)minN, (BlockPos)maxN)) {
                if (p.equals((Object)cur) || !PurifyMappings.isSrp(this.field_145850_b.func_180495_p(p))) continue;
                this.offer(p);
            }
            BlockPos min = cur.func_177982_a(-6, -6, -6);
            BlockPos max = cur.func_177982_a(6, 6, 6);
            for (BlockPos p : BlockPos.func_177980_a((BlockPos)min, (BlockPos)max)) {
                if (p.equals((Object)cur) || !PurifyMappings.isSrp(this.field_145850_b.func_180495_p(p))) continue;
                this.offer(p);
            }
            ++processed;
        }
        if (this.queue.isEmpty() || this.total >= 131072) {
            this.running = false;
            this.notifyFinished();
        }
    }

    private void notifyFinished() {
        EntityPlayer p;
        if (!(this.field_145850_b instanceof WorldServer)) {
            return;
        }
        if (this.starter != null && (p = this.field_145850_b.func_152378_a(this.starter)) != null) {
            p.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.purifier_purified", new Object[]{this.total}), true);
        }
    }

    public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
        super.func_189515_b(nbt);
        nbt.func_74757_a("running", this.running);
        nbt.func_74768_a("total", this.total);
        if (this.starter != null) {
            nbt.func_186854_a("starter", this.starter);
        }
        return nbt;
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.running = nbt.func_74767_n("running");
        this.total = nbt.func_74762_e("total");
        this.starter = nbt.func_186855_b("starter") ? nbt.func_186857_a("starter") : null;
    }
}

