/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.PacketVengeanceFX;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public final class VengeanceGrappleHandler {
    private static final VengeanceGrappleHandler INSTANCE = new VengeanceGrappleHandler();
    private static final Map<UUID, GrappleData> ACTIVE = new HashMap<UUID, GrappleData>();

    public static VengeanceGrappleHandler get() {
        return INSTANCE;
    }

    private VengeanceGrappleHandler() {
    }

    private static SimpleNetworkWrapper net() {
        return BestiaryNetwork.CH;
    }

    private static void fxAroundTracking(WorldServer ws, Vec3d pos, PacketVengeanceFX pkt) {
        for (EntityPlayerMP p : ws.func_73046_m().func_184103_al().func_181057_v()) {
            if (p.field_71093_bK != ws.field_73011_w.getDimension() || p.func_70092_e(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c) > 4096.0) continue;
            VengeanceGrappleHandler.net().sendTo((IMessage)pkt, p);
        }
    }

    public static void start(EntityPlayer player, EntityLivingBase target) {
        if (player == null || target == null) {
            return;
        }
        if (player.field_70128_L || target.field_70128_L) {
            return;
        }
        if (!(player.field_70170_p instanceof WorldServer)) {
            return;
        }
        GrappleData d = new GrappleData(player.func_110124_au(), target.func_110124_au(), player.field_71093_bK);
        d.stage = Stage.WINDUP;
        d.timer = 20;
        ACTIVE.put(player.func_110124_au(), d);
        WorldServer ws = (WorldServer)player.field_70170_p;
        ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SRPSounds.VENGEANCE_PAPER, SoundCategory.PLAYERS, 2.0f, 1.0f);
    }

    @SubscribeEvent
    public void onPlayerAttacked(LivingAttackEvent e) {
        if (!(e.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer p = (EntityPlayer)e.getEntityLiving();
        GrappleData d = ACTIVE.get(p.func_110124_au());
        if (d == null) {
            return;
        }
        Entity src = e.getSource().func_76346_g();
        if (src != null && src.func_110124_au().equals(d.targetId)) {
            e.setCanceled(true);
            p.field_70172_ad = 20;
        }
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent e) {
        if (!(e.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer p = (EntityPlayer)e.getEntityLiving();
        GrappleData d = ACTIVE.get(p.func_110124_au());
        if (d == null) {
            return;
        }
        if (e.getSource() == DamageSource.field_180137_b) {
            e.setCanceled(true);
            return;
        }
        String dt = e.getSource().field_76373_n;
        if ("explosion".equals(dt) || "explosion.player".equals(dt)) {
            e.setCanceled(true);
        }
    }

    private static void applyVengeanceDebuffs(EntityLivingBase ent) {
        if (ent == null || ent.field_70128_L) {
            return;
        }
        int dur = 100;
        if (SRPPotions.DOD_SMOKE_TRAIL_E != null) {
            ent.func_70690_d(new PotionEffect(SRPPotions.DOD_SMOKE_TRAIL_E, 100, 0, false, true));
        }
        if (SRPPotions.BLEED_E != null) {
            ent.func_70690_d(new PotionEffect(SRPPotions.BLEED_E, 100, 0, false, true));
        }
        if (SRPPotions.DEBAR_E != null) {
            ent.func_70690_d(new PotionEffect(SRPPotions.DEBAR_E, 100, 0, false, true));
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent e) {
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        if (ACTIVE.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<UUID, GrappleData>> it = ACTIVE.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, GrappleData> en = it.next();
            GrappleData d = en.getValue();
            EntityPlayer player = d.getPlayer();
            EntityLivingBase target = d.getTarget();
            if (player == null || target == null) {
                it.remove();
                continue;
            }
            if (player.field_70128_L || target.field_70128_L) {
                it.remove();
                continue;
            }
            if (player.field_71093_bK != d.dimension) {
                it.remove();
                continue;
            }
            if (!(player.field_70170_p instanceof WorldServer)) {
                it.remove();
                continue;
            }
            WorldServer ws = (WorldServer)player.field_70170_p;
            if (!player.func_70685_l((Entity)target)) {
                ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187646_bt, SoundCategory.PLAYERS, 0.6f, 1.4f);
                it.remove();
                continue;
            }
            switch (d.stage) {
                case WINDUP: {
                    VengeanceGrappleHandler.spawnChainParticles(ws, player, target, EnumParticleTypes.SPELL_WITCH);
                    if (d.timer % 4 == 0) {
                        Vec3d c = new Vec3d(player.field_70165_t, player.field_70163_u + 0.2, player.field_70161_v);
                        VengeanceGrappleHandler.fxAroundTracking(ws, c, new PacketVengeanceFX(0, c, 1.2f, 16));
                    }
                    if (d.timer % 6 == 0) {
                        ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_190021_aL, SoundCategory.PLAYERS, 0.25f, 1.8f);
                    }
                    --d.timer;
                    if (d.timer > 0) break;
                    ws.func_184148_a(null, target.field_70165_t, target.field_70163_u + (double)target.field_70131_O * 0.5, target.field_70161_v, SRPSounds.VENGEANCE_CHAIN_IMPACT, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    d.stage = Stage.PULL;
                    d.ticks = 0;
                    break;
                }
                case PULL: {
                    ++d.ticks;
                    VengeanceGrappleHandler.spawnChainParticles(ws, player, target, EnumParticleTypes.CRIT_MAGIC);
                    VengeanceGrappleHandler.pullPlayerToward(player, target, 1.8);
                    double dist = player.func_70032_d((Entity)target);
                    if (!(dist <= 2.2) && d.ticks <= 40) break;
                    VengeanceGrappleHandler.impact1(ws, player, target, d);
                    break;
                }
                case BOUNCE: {
                    int t;
                    if (d.timer % 4 == 0) {
                        for (UUID id : d.affected) {
                            Entity e2 = ws.func_175733_a(id);
                            if (!(e2 instanceof EntityLivingBase)) continue;
                            EntityLivingBase ent2 = (EntityLivingBase)e2;
                            if (ent2.field_70128_L) continue;
                            Vec3d c = new Vec3d(ent2.field_70165_t, ent2.field_70163_u + (double)ent2.field_70131_O * 0.5, ent2.field_70161_v);
                            VengeanceGrappleHandler.fxAroundTracking(ws, c, new PacketVengeanceFX(2, c, 0.0f, 14));
                        }
                    }
                    if ((t = d.timer) >= 12) {
                        player.field_70181_x = Math.max(player.field_70181_x, 0.55);
                        player.field_70159_w *= 0.4;
                        player.field_70179_y *= 0.4;
                        player.field_70133_I = true;
                    } else if (t >= 6) {
                        player.field_70181_x = Math.max(player.field_70181_x, -0.02);
                        player.field_70159_w *= 0.65;
                        player.field_70179_y *= 0.65;
                        player.field_70133_I = true;
                    } else {
                        VengeanceGrappleHandler.pullPlayerToward(player, target, 2.35);
                    }
                    player.field_70143_R = 0.0f;
                    --d.timer;
                    if (d.timer > 0) break;
                    VengeanceGrappleHandler.impact2AndLightning(ws, player, target, d);
                    it.remove();
                    break;
                }
            }
        }
    }

    private static void pullPlayerToward(EntityPlayer player, EntityLivingBase target, double speed) {
        Vec3d to = new Vec3d(target.field_70165_t - player.field_70165_t, target.field_70163_u + (double)target.func_70047_e() * 0.5 - (player.field_70163_u + (double)player.func_70047_e()), target.field_70161_v - player.field_70161_v);
        double len = to.func_72433_c();
        if (len < 1.0E-4) {
            return;
        }
        Vec3d dir = to.func_186678_a(1.0 / len);
        double vx = MathHelper.func_151237_a((double)(dir.field_72450_a * speed), (double)-2.8, (double)2.8);
        double vy = MathHelper.func_151237_a((double)(dir.field_72448_b * speed), (double)-2.2, (double)2.2);
        double vz = MathHelper.func_151237_a((double)(dir.field_72449_c * speed), (double)-2.8, (double)2.8);
        player.field_70159_w = vx;
        player.field_70181_x = vy;
        player.field_70179_y = vz;
        player.field_70133_I = true;
        player.field_70143_R = 0.0f;
    }

    private static void impact1(WorldServer ws, EntityPlayer player, EntityLivingBase target, GrappleData d) {
        ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.VENGEANCE_IMPACT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.VENGEANCE_ROCK, SoundCategory.PLAYERS, 0.9f, 0.9f);
        Vec3d hitPos = new Vec3d(target.field_70165_t, target.field_70163_u, target.field_70161_v);
        VengeanceGrappleHandler.fxAroundTracking(ws, hitPos, new PacketVengeanceFX(1, hitPos, 0.0f, 28));
        ws.func_175739_a(EnumParticleTypes.EXPLOSION_HUGE, target.field_70165_t, target.field_70163_u + 0.2, target.field_70161_v, 1, 0.0, 0.0, 0.0, 0.0, new int[0]);
        float blastDamage = 14.0f;
        float radius = 5.0f;
        AxisAlignedBB aabb = new AxisAlignedBB(target.field_70165_t - (double)radius, target.field_70163_u - 2.0, target.field_70161_v - (double)radius, target.field_70165_t + (double)radius, target.field_70163_u + 4.0, target.field_70161_v + (double)radius);
        List list = ws.func_72872_a(EntityLivingBase.class, aabb);
        d.affected.clear();
        for (EntityLivingBase ent : list) {
            double dist;
            if (ent == null || ent.field_70128_L || ent == player || (dist = (double)ent.func_70032_d((Entity)target)) > (double)radius) continue;
            d.affected.add(ent.func_110124_au());
            VengeanceGrappleHandler.applyVengeanceDebuffs(ent);
            if (SRPSounds.VENGEANCE_WHOOSH != null) {
                ws.func_184148_a(null, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, SRPSounds.VENGEANCE_WHOOSH, SoundCategory.PLAYERS, 0.8f, 1.2f);
            }
            ent.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), blastDamage);
            Vec3d look = player.func_70040_Z();
            double l = look.func_72433_c();
            if (l > 1.0E-4) {
                look = look.func_186678_a(1.0 / l);
            }
            double forward = 1.9;
            double upward = 0.65;
            ent.field_70159_w = look.field_72450_a * forward;
            ent.field_70179_y = look.field_72449_c * forward;
            ent.field_70181_x = Math.max(ent.field_70181_x, look.field_72448_b * forward + upward);
            ent.field_70133_I = true;
        }
        player.field_70159_w *= 0.15;
        player.field_70179_y *= 0.15;
        player.field_70143_R = 0.0f;
        player.field_70133_I = true;
        d.stage = Stage.BOUNCE;
        d.timer = 14;
    }

    private static void impact2AndLightning(WorldServer ws, EntityPlayer player, EntityLivingBase target, GrappleData d) {
        ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SoundEvents.field_187539_bB, SoundCategory.PLAYERS, 0.7f, 1.2f);
        Vec3d p = new Vec3d(target.field_70165_t, target.field_70163_u + 0.1, target.field_70161_v);
        VengeanceGrappleHandler.fxAroundTracking(ws, p, new PacketVengeanceFX(3, p, 0.0f, 0));
        ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.VENGEANCE_ROCK, SoundCategory.PLAYERS, 0.9f, 0.9f);
        ws.func_175739_a(EnumParticleTypes.EXPLOSION_LARGE, target.field_70165_t, target.field_70163_u + 0.15, target.field_70161_v, 3, 0.15, 0.1, 0.15, 0.0, new int[0]);
        if (!target.field_70128_L) {
            target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), 6.0f);
            target.field_70181_x = Math.max(target.field_70181_x, 0.35);
            target.field_70133_I = true;
        }
        float radius = 3.5f;
        float maxDmg = 10.0f;
        AxisAlignedBB aabb = new AxisAlignedBB(target.field_70165_t - 3.5, target.field_70163_u - 1.5, target.field_70161_v - 3.5, target.field_70165_t + 3.5, target.field_70163_u + 2.5, target.field_70161_v + 3.5);
        List near = ws.func_72872_a(EntityLivingBase.class, aabb);
        for (EntityLivingBase ent : near) {
            double dist;
            if (ent == null || ent.field_70128_L || ent == player || (dist = (double)ent.func_70032_d((Entity)target)) > 3.5) continue;
            float scale = 1.0f - (float)(dist / 3.5);
            float dmg = 10.0f * scale;
            ent.func_70097_a(DamageSource.func_188405_b((EntityLivingBase)player), dmg);
            Vec3d push = new Vec3d(ent.field_70165_t - target.field_70165_t, 0.0, ent.field_70161_v - target.field_70161_v);
            double len = push.func_72433_c();
            if (!(len > 1.0E-4)) continue;
            push = push.func_186678_a(1.0 / len);
            double kb = 0.85 * (double)scale;
            ent.field_70159_w += push.field_72450_a * kb;
            ent.field_70179_y += push.field_72449_c * kb;
            ent.field_70181_x = Math.max(ent.field_70181_x, 0.25 + 0.2 * (double)scale);
            ent.field_70133_I = true;
        }
        player.field_70143_R = 0.0f;
        VengeanceGrappleHandler.lightningStrike(ws, player, d);
    }

    private static void lightningStrike(WorldServer ws, EntityPlayer player, GrappleData d) {
        ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187754_de, SoundCategory.PLAYERS, 0.9f, 1.0f);
        for (UUID id : d.affected) {
            Entity e = ws.func_175733_a(id);
            if (!(e instanceof EntityLivingBase)) continue;
            EntityLivingBase ent = (EntityLivingBase)e;
            if (ent.field_70128_L) continue;
            ws.func_72942_c((Entity)new EntityLightningBolt((World)ws, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, true));
            Vec3d p = new Vec3d(ent.field_70165_t, ent.field_70163_u, ent.field_70161_v);
            VengeanceGrappleHandler.fxAroundTracking(ws, p, new PacketVengeanceFX(3, p, 0.0f, 0));
            ent.func_70097_a(DamageSource.field_180137_b, 16.0f);
            ws.func_184148_a(null, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, SoundEvents.field_187752_dd, SoundCategory.HOSTILE, 0.8f, 1.0f);
        }
    }

    private static void spawnChainParticles(WorldServer ws, EntityPlayer player, EntityLivingBase target, EnumParticleTypes type) {
        Vec3d start = new Vec3d(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e() - 0.15, player.field_70161_v);
        Vec3d end = new Vec3d(target.field_70165_t, target.field_70163_u + (double)target.func_70047_e() * 0.5, target.field_70161_v);
        Vec3d delta = end.func_178788_d(start);
        double len = delta.func_72433_c();
        if (len < 1.0E-4) {
            return;
        }
        Vec3d step = delta.func_186678_a(1.0 / len);
        int count = MathHelper.func_76125_a((int)((int)(len / 0.6)), (int)6, (int)60);
        for (int i = 0; i < count; ++i) {
            Vec3d p = start.func_178787_e(step.func_186678_a((double)i * (len / (double)count)));
            ws.func_175739_a(type, p.field_72450_a, p.field_72448_b, p.field_72449_c, 1, 0.02, 0.02, 0.02, 0.0, new int[0]);
        }
    }

    private static final class GrappleData {
        final UUID playerId;
        final UUID targetId;
        final int dimension;
        Stage stage = Stage.WINDUP;
        int ticks = 0;
        int timer = 0;
        final List<UUID> affected = new ArrayList<UUID>();

        GrappleData(UUID playerId, UUID targetId, int dimension) {
            this.playerId = playerId;
            this.targetId = targetId;
            this.dimension = dimension;
        }

        EntityPlayer getPlayer() {
            if (FMLCommonHandler.instance().getMinecraftServerInstance() == null) {
                return null;
            }
            return FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_177451_a(this.playerId);
        }

        EntityLivingBase getTarget() {
            EntityPlayer p = this.getPlayer();
            if (p == null || !(p.field_70170_p instanceof WorldServer)) {
                return null;
            }
            Entity e = ((WorldServer)p.field_70170_p).func_175733_a(this.targetId);
            return e instanceof EntityLivingBase ? (EntityLivingBase)e : null;
        }
    }

    private static enum Stage {
        WINDUP,
        PULL,
        BOUNCE;

    }
}

