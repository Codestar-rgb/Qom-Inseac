/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.block.PurifyMappings;
import com.subspaceparasite.block.TileEntityInfestationPurifier;
import com.subspaceparasite.init.SPSoundTypes;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.MsgSpawnPureParticles;
import com.subspaceparasite.network.SPNetwork;
import com.google.common.base.Predicate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BlockInfestationPurifier
extends BlockBase {
    private static final int ENTITY_SEARCH_RADIUS = 200;
    private static final int CONNECT_MAX_NODES = 40000;
    private static final int CONNECT_MAX_DISTANCE = 200;
    private static final String MAPPING_FILE = "subspaceparasite_purify_mappings.txt";
    private static final ResourceLocation[] BECKON_IDS = new ResourceLocation[]{new ResourceLocation("subspaceparasite", "beckon_si"), new ResourceLocation("subspaceparasite", "beckon_sii"), new ResourceLocation("subspaceparasite", "beckon_siii"), new ResourceLocation("subspaceparasite", "beckon_siv")};

    public BlockInfestationPurifier(String name) {
        super(Material.field_151576_e, name, 2.0f, true, false);
        this.func_149675_a(false);
        this.func_149672_a(SPSoundTypes.PURIFIER);
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityInfestationPurifier();
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = player.func_184586_b(hand);
        boolean hasDiamond = !held.func_190926_b() && held.func_77973_b() == Items.field_151045_i;
        BlockPos below = pos.func_177977_b();
        IBlockState belowState = world.func_180495_p(below);
        PurifyMappings.ensureLoaded(world, MAPPING_FILE);
        if (!hasDiamond) {
            if (!world.field_72995_K) {
                player.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.purifier_use_diamond", new Object[0]), true);
            }
            return true;
        }
        if (!PurifyMappings.isSrp(belowState)) {
            if (!world.field_72995_K) {
                player.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.purifier_not_over_srp", new Object[0]), true);
            }
            return true;
        }
        if (world.field_72995_K) {
            return true;
        }
        int connectedCount = this.checkAndGlowConnectedBeckons(world, pos, below);
        if (connectedCount > 0) {
            player.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.purifier_entities_marked", new Object[]{connectedCount}), true);
            return true;
        }
        TileEntity te = world.func_175625_s(pos);
        if (te instanceof TileEntityInfestationPurifier) {
            world.func_184133_a(null, pos, SPSounds.PURIFIER_USE, SoundCategory.BLOCKS, 0.9f, 1.0f);
            double cx = (double)pos.func_177958_n() + 0.5;
            double cy = (double)pos.func_177956_o() + 0.9;
            double cz = (double)pos.func_177952_p() + 0.5;
            SPNetwork.CHANNEL.sendToAllAround((IMessage)new MsgSpawnPureParticles(cx, cy, cz, 24, 1), new NetworkRegistry.TargetPoint(world.field_73011_w.getDimension(), cx, cy, cz, 64.0));
            this.spawnStartPulse(world, pos);
            ((TileEntityInfestationPurifier)te).startAt(below, player.func_110124_au());
            player.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.purifier_started", new Object[0]), true);
        }
        return true;
    }

    private int checkAndGlowConnectedBeckons(final World world, BlockPos pos, BlockPos startSrp) {
        AxisAlignedBB box = new AxisAlignedBB((double)(pos.func_177958_n() - 200), (double)(pos.func_177956_o() - 96), (double)(pos.func_177952_p() - 200), (double)(pos.func_177958_n() + 200 + 1), (double)(pos.func_177956_o() + 96), (double)(pos.func_177952_p() + 200 + 1));
        final HashMap<BlockPos, List<EntityLivingBase>> beckonsByFoot = new HashMap<BlockPos, List<EntityLivingBase>>();
        List _unused = world.func_175647_a(Entity.class, box, (Predicate)new Predicate<Entity>(){

            public boolean apply(Entity e) {
                boolean isBeckon;
                if (!(e instanceof EntityLivingBase)) {
                    return false;
                }
                ResourceLocation id = EntityList.func_191301_a((Entity)e);
                if (id == null) {
                    return false;
                }
                boolean bl = isBeckon = id.equals((Object)new ResourceLocation("subspaceparasite", "beckon_si")) || id.equals((Object)new ResourceLocation("subspaceparasite", "beckon_sii")) || id.equals((Object)new ResourceLocation("subspaceparasite", "beckon_siii")) || id.equals((Object)new ResourceLocation("subspaceparasite", "beckon_siv"));
                if (!isBeckon) {
                    return false;
                }
                BlockPos under = new BlockPos(MathHelper.func_76128_c((double)e.field_70165_t), MathHelper.func_76128_c((double)e.field_70163_u) - 1, MathHelper.func_76128_c((double)e.field_70161_v));
                if (!PurifyMappings.isSrp(world.func_180495_p(under))) {
                    return false;
                }
                beckonsByFoot.computeIfAbsent(under, k -> new ArrayList()).add((EntityLivingBase)e);
                return true;
            }
        });
        if (beckonsByFoot.isEmpty()) {
            return 0;
        }
        return this.bfsGlowConnected(world, startSrp, beckonsByFoot);
    }

    private int bfsGlowConnected(World world, BlockPos start, Map<BlockPos, List<EntityLivingBase>> beckonsByFoot) {
        ArrayDeque<BlockPos> q = new ArrayDeque<BlockPos>();
        HashSet<Long> seen = new HashSet<Long>();
        int maxDistSq = 40000;
        q.add(start);
        seen.add(start.func_177986_g());
        HashSet<Long> targets = new HashSet<Long>();
        for (BlockPos bp : beckonsByFoot.keySet()) {
            targets.add(bp.func_177986_g());
        }
        int nodes = 0;
        int connected = 0;
        while (!q.isEmpty() && nodes < 40000 && !targets.isEmpty()) {
            BlockPos cur = (BlockPos)q.poll();
            ++nodes;
            long curKey = cur.func_177986_g();
            if (targets.contains(curKey)) {
                List<EntityLivingBase> here = beckonsByFoot.get(cur);
                if (here != null) {
                    for (EntityLivingBase elb : here) {
                        elb.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 600, 0, false, true));
                    }
                    connected += here.size();
                }
                targets.remove(curKey);
            }
            for (EnumFacing f : EnumFacing.field_82609_l) {
                BlockPos nxt = cur.func_177972_a(f);
                long k = nxt.func_177986_g();
                if (!seen.add(k) || start.func_177951_i((Vec3i)nxt) > (double)maxDistSq || !PurifyMappings.isSrp(world.func_180495_p(nxt))) continue;
                q.add(nxt);
            }
        }
        return connected;
    }

    static IBlockState tryCopyCommonProps(IBlockState from, IBlockState to) {
        IBlockState out = to;
        for (IProperty propTo : to.func_177227_a()) {
            if (!from.func_177227_a().contains(propTo)) continue;
            try {
                IProperty raw = propTo;
                Comparable val = from.func_177229_b(raw);
                if (val == null || !raw.func_177700_c().contains(val)) continue;
                out = out.func_177226_a(raw, val);
            }
            catch (Exception exception) {}
        }
        return out;
    }

    private void spawnStartPulse(World world, BlockPos pos) {
        if (!(world instanceof WorldServer)) {
            return;
        }
        WorldServer ws = (WorldServer)world;
        double cx = (double)pos.func_177958_n() + 0.5;
        double cy = (double)pos.func_177956_o() + 0.8;
        double cz = (double)pos.func_177952_p() + 0.5;
        for (int i = 0; i < 28; ++i) {
            double angle = Math.PI * 2 * (double)i / 28.0;
            double radius = 0.9;
            double x = cx + Math.cos(angle) * radius;
            double z = cz + Math.sin(angle) * radius;
            ws.func_175739_a(EnumParticleTypes.REDSTONE, x, cy, z, 0, 0.1, 0.35, 1.0, 1.0, new int[0]);
            ws.func_175739_a(EnumParticleTypes.PORTAL, x, cy + 0.05, z, 1, 0.0, 0.01, 0.0, 0.0, new int[0]);
        }
    }
}

