/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.PotionEvent$PotionApplicableEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="srparasites")
public class ThornshadeThornsHandler {
    private static final String TAG_ROOT = "srp_thornshade_thorns";
    private static final String TAG_USES = "Uses";
    private static final String TAG_COOLDOWN_UNTIL = "CooldownUntil";
    private static final String TAG_EXPLODE_DELAY = "ExplodeDelay";
    private static final float MAX_HP_ALLOWED = 120.0f;
    private static final String TAG_HAS_EXPLODED = "HasExplodedOnce";

    @SubscribeEvent
    public static void onPotionApplicable(PotionEvent.PotionApplicableEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living == null || living.field_70170_p.field_72995_K) {
            return;
        }
        PotionEffect incoming = event.getPotionEffect();
        if (incoming == null || incoming.func_188419_a() != SRPPotions.THORNSHADE_THORNS_E) {
            return;
        }
        if (living instanceof EntityParasiteBase) {
            event.setResult(Event.Result.DENY);
            return;
        }
        if (living.func_110138_aP() > 120.0f) {
            event.setResult(Event.Result.DENY);
            return;
        }
        if (living.func_70644_a(SRPPotions.THORNSHADE_THORNS_E)) {
            event.setResult(Event.Result.DENY);
            return;
        }
        if (ThornshadeThornsHandler.isInfiniteDuration(incoming)) {
            event.setResult(Event.Result.DENY);
            return;
        }
        World world = living.field_70170_p;
        long now = world.func_82737_E();
        NBTTagCompound data = ThornshadeThornsHandler.getThornshadeData(living);
        int uses = data.func_74762_e(TAG_USES);
        if (uses >= 2) {
            event.setResult(Event.Result.DENY);
            if (!data.func_74764_b(TAG_EXPLODE_DELAY)) {
                ThornshadeThornsHandler.scheduleExplosion(living, data);
            }
            ThornshadeThornsHandler.setThornshadeData(living, data);
            return;
        }
        long cooldownUntil = data.func_74763_f(TAG_COOLDOWN_UNTIL);
        if (cooldownUntil > now) {
            event.setResult(Event.Result.DENY);
            return;
        }
        data.func_74768_a(TAG_USES, ++uses);
        int durationTicks = incoming.func_76459_b();
        long extraCooldownTicks = (long)durationTicks / 2L;
        data.func_74772_a(TAG_COOLDOWN_UNTIL, now + extraCooldownTicks);
        ThornshadeThornsHandler.setThornshadeData(living, data);
        event.setResult(Event.Result.DEFAULT);
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living == null || living.field_70170_p.field_72995_K) {
            return;
        }
        NBTTagCompound data = ThornshadeThornsHandler.getThornshadeData(living);
        if (!data.func_74764_b(TAG_EXPLODE_DELAY)) {
            return;
        }
        int delay = data.func_74762_e(TAG_EXPLODE_DELAY);
        if (delay > 0) {
            ThornshadeThornsHandler.spawnBloodParticles(living.field_70170_p, living, 15);
            data.func_74768_a(TAG_EXPLODE_DELAY, --delay);
            ThornshadeThornsHandler.setThornshadeData(living, data);
            return;
        }
        data.func_82580_o(TAG_EXPLODE_DELAY);
        ThornshadeThornsHandler.setThornshadeData(living, data);
        ThornshadeThornsHandler.doExplosion(living);
    }

    private static void spawnBloodParticles(World world, EntityLivingBase entity, int count) {
        if (!(world instanceof WorldServer)) {
            return;
        }
        WorldServer ws = (WorldServer)world;
        double x = entity.field_70165_t;
        double y = entity.field_70163_u + (double)entity.field_70131_O * 0.5;
        double z = entity.field_70161_v;
        int redDustId = Block.func_176210_f((IBlockState)Blocks.field_150451_bX.func_176223_P());
        for (int i = 0; i < count; ++i) {
            double offsetX = (world.field_73012_v.nextDouble() - 0.5) * 0.6;
            double offsetY = world.field_73012_v.nextDouble() * 0.8;
            double offsetZ = (world.field_73012_v.nextDouble() - 0.5) * 0.6;
            double motionX = (world.field_73012_v.nextDouble() - 0.5) * 0.3;
            double motionY = world.field_73012_v.nextDouble() * 0.4 + 0.1;
            double motionZ = (world.field_73012_v.nextDouble() - 0.5) * 0.3;
            ws.func_175739_a(EnumParticleTypes.BLOCK_DUST, x + offsetX, y + offsetY, z + offsetZ, 0, motionX, motionY, motionZ, 0.0, new int[]{redDustId});
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        EntityLivingBase target = event.getEntityLiving();
        if (target == null || target.field_70170_p.field_72995_K) {
            return;
        }
        PotionEffect eff = target.func_70660_b(SRPPotions.THORNSHADE_THORNS_E);
        if (eff == null) {
            return;
        }
        if (ThornshadeThornsHandler.isInfiniteDuration(eff)) {
            return;
        }
        Entity trueSourceEntity = event.getSource().func_76346_g();
        if (!(trueSourceEntity instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase attacker = (EntityLivingBase)trueSourceEntity;
        float incoming = event.getAmount();
        if (incoming <= 0.0f) {
            return;
        }
        NBTTagCompound data = ThornshadeThornsHandler.getThornshadeData(target);
        int uses = data.func_74762_e(TAG_USES);
        float reflectFactor = uses <= 1 ? 0.25f : 0.5f;
        float reflected = incoming * reflectFactor;
        if (reflected <= 0.0f) {
            return;
        }
        attacker.func_70097_a(DamageSource.func_92087_a((Entity)target), reflected);
    }

    private static void scheduleExplosion(EntityLivingBase living, NBTTagCompound data) {
        if (data.func_74767_n(TAG_HAS_EXPLODED)) {
            return;
        }
        data.func_74768_a(TAG_EXPLODE_DELAY, 20);
        World world = living.field_70170_p;
        world.func_184148_a(null, living.field_70165_t, living.field_70163_u, living.field_70161_v, SRPSounds.ADAPTATION_P, SoundCategory.PLAYERS, 1.5f, 0.8f + world.field_73012_v.nextFloat() * 0.4f);
    }

    private static void doExplosion(EntityLivingBase center) {
        double distSq;
        EntityPlayerMP player;
        Advancement adv;
        World world = center.field_70170_p;
        if (world.field_72995_K) {
            return;
        }
        NBTTagCompound centerData = ThornshadeThornsHandler.getThornshadeData(center);
        centerData.func_74757_a(TAG_HAS_EXPLODED, true);
        if (center instanceof EntityPlayerMP && (adv = (player = (EntityPlayerMP)center).func_71121_q().func_73046_m().func_191949_aK().func_192778_a(new ResourceLocation("srparasites", "thornshade_self_destruct"))) != null) {
            player.func_192039_O().func_192750_a(adv, "exploded");
        }
        ThornshadeThornsHandler.setThornshadeData(center, centerData);
        double x = center.field_70165_t;
        double y = center.field_70163_u;
        double z = center.field_70161_v;
        float innerRadius = 3.0f;
        float outerRadius = 10.0f;
        AxisAlignedBB outerBox = new AxisAlignedBB(x - (double)outerRadius, y - (double)outerRadius, z - (double)outerRadius, x + (double)outerRadius, y + (double)outerRadius, z + (double)outerRadius);
        Explosion explosion = new Explosion(world, null, x, y, z, 3.0f, false, false);
        explosion.func_77278_a();
        world.func_184148_a(null, x, y, z, SRPSounds.BUTHOL_BOOM, SoundCategory.PLAYERS, 2.0f, 1.0f);
        ThornshadeThornsHandler.spawnRadialParticles(world, x, y + (double)center.field_70131_O * 0.5, z, innerRadius, 50, EnumParticleTypes.CLOUD);
        ThornshadeThornsHandler.spawnRadialParticles(world, x, y + (double)center.field_70131_O * 0.5, z, outerRadius, 120, EnumParticleTypes.SPELL_WITCH);
        center.func_70097_a(DamageSource.field_76376_m.func_76348_h().func_151518_m(), Float.MAX_VALUE);
        AxisAlignedBB innerBox = new AxisAlignedBB(x - (double)innerRadius, y - (double)innerRadius, z - (double)innerRadius, x + (double)innerRadius, y + (double)innerRadius, z + (double)innerRadius);
        for (EntityLivingBase other : world.func_72872_a(EntityLivingBase.class, innerBox)) {
            NBTTagCompound data;
            if (other == center || !other.func_70089_S() || other instanceof EntityParasiteBase || (distSq = other.func_70092_e(x, y, z)) > (double)(innerRadius * innerRadius) || !other.func_70644_a(SRPPotions.THORNSHADE_THORNS_E) || other.func_110138_aP() > 120.0f || (data = ThornshadeThornsHandler.getThornshadeData(other)).func_74767_n(TAG_HAS_EXPLODED) || data.func_74764_b(TAG_EXPLODE_DELAY)) continue;
            data.func_74768_a(TAG_USES, Math.max(2, data.func_74762_e(TAG_USES)));
            ThornshadeThornsHandler.scheduleExplosion(other, data);
            ThornshadeThornsHandler.setThornshadeData(other, data);
        }
        for (EntityLivingBase other : world.func_72872_a(EntityLivingBase.class, outerBox)) {
            if (other == center || !other.func_70089_S() || other instanceof EntityParasiteBase || (distSq = other.func_70092_e(x, y, z)) <= (double)(innerRadius * innerRadius) || other.func_110138_aP() > 120.0f || other.func_70644_a(SRPPotions.THORNSHADE_THORNS_E)) continue;
            other.func_70690_d(new PotionEffect(SRPPotions.THORNSHADE_THORNS_E, 600, 0, false, true));
            ThornshadeThornsHandler.spawnRadialParticles(world, other.field_70165_t, other.field_70163_u + (double)other.field_70131_O * 0.5, other.field_70161_v, 1.0f, 20, EnumParticleTypes.SPELL_WITCH);
        }
    }

    private static void spawnRadialParticles(World world, double x, double y, double z, float radius, int count, EnumParticleTypes type) {
        if (!(world instanceof WorldServer)) {
            return;
        }
        WorldServer ws = (WorldServer)world;
        int bloodStateId = Block.func_176210_f((IBlockState)Blocks.field_150451_bX.func_176223_P());
        for (int i = 0; i < count; ++i) {
            double angle = world.field_73012_v.nextDouble() * 2.0 * Math.PI;
            double ringRadius = (double)radius * (0.7 + world.field_73012_v.nextDouble() * 0.3);
            double px = x + ringRadius * Math.cos(angle);
            double pz = z + ringRadius * Math.sin(angle);
            double py = y + (world.field_73012_v.nextDouble() - 0.5) * ((double)radius * 0.2);
            double dirX = px - x;
            double dirZ = pz - z;
            double len = Math.sqrt(dirX * dirX + dirZ * dirZ);
            if (len == 0.0) {
                dirX = 1.0;
                dirZ = 0.0;
                len = 1.0;
            }
            dirX /= len;
            dirZ /= len;
            double baseOut = 0.4 + world.field_73012_v.nextDouble() * 0.5;
            double mistVy = 0.05 + world.field_73012_v.nextDouble() * 0.15;
            if (type == EnumParticleTypes.SPELL_WITCH) {
                ws.func_175739_a(EnumParticleTypes.BLOCK_DUST, px, py, pz, 0, dirX * baseOut * 0.6, mistVy, dirZ * baseOut * 0.6, 0.0, new int[]{bloodStateId});
            } else {
                ws.func_175739_a(type, px, py, pz, 1, dirX * baseOut * 0.3, mistVy, dirZ * baseOut * 0.3, 0.1, new int[0]);
            }
            double chunkScale = 0.6 + world.field_73012_v.nextDouble() * 0.8;
            double gx = dirX * chunkScale;
            double gz = dirZ * chunkScale;
            double gy = 0.25 + world.field_73012_v.nextDouble() * 0.6;
            ws.func_175739_a(EnumParticleTypes.BLOCK_DUST, px, py, pz, 0, gx, gy, gz, 0.0, new int[]{bloodStateId});
        }
    }

    private static NBTTagCompound getThornshadeData(EntityLivingBase entity) {
        NBTTagCompound root = entity.getEntityData();
        if (!root.func_150297_b(TAG_ROOT, 10)) {
            NBTTagCompound data = new NBTTagCompound();
            root.func_74782_a(TAG_ROOT, (NBTBase)data);
            return data;
        }
        return root.func_74775_l(TAG_ROOT);
    }

    private static void setThornshadeData(EntityLivingBase entity, NBTTagCompound data) {
        entity.getEntityData().func_74782_a(TAG_ROOT, (NBTBase)data);
    }

    private static boolean isInfiniteDuration(PotionEffect effect) {
        return effect.func_100011_g() || effect.func_76459_b() >= 72000 || effect.func_76459_b() == Integer.MAX_VALUE;
    }
}

