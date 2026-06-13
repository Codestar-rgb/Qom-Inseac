/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.logic;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class VenkrolTornadoLogic {
    public static void tickTornadoEffects(EntityLivingBase venkrol) {
        if (!SRPConfigWorld.venkrolTornadoEnabled) {
            return;
        }
        if (venkrol == null) {
            return;
        }
        World world = venkrol.field_70170_p;
        if (world == null || world.field_72995_K) {
            return;
        }
        if (!world.func_72896_J() || !world.func_72911_I()) {
            return;
        }
        double x = venkrol.field_70165_t;
        double maxRadius = 120.0;
        double y = venkrol.field_70163_u;
        double z = venkrol.field_70161_v;
        double height = 50.0;
        AxisAlignedBB box = new AxisAlignedBB(x - maxRadius, y, z - maxRadius, x + maxRadius, y + height, z + maxRadius);
        List targets = world.func_72872_a(EntityLivingBase.class, box);
        if (targets.isEmpty()) {
            return;
        }
        for (EntityLivingBase e : targets) {
            if (e == venkrol || !e.func_70089_S() || VenkrolTornadoLogic.isSRPParasite(e) || e.field_70163_u < venkrol.field_70163_u) continue;
            VenkrolTornadoLogic.applyTornadoForces(venkrol, e, maxRadius);
        }
    }

    private static boolean isSRPParasite(EntityLivingBase e) {
        ResourceLocation rl = EntityList.func_191301_a((Entity)e);
        if (rl == null) {
            return false;
        }
        String modid = rl.func_110624_b();
        return "srparasites".equals(modid);
    }

    private static void applyTornadoForces(EntityLivingBase venkrol, EntityLivingBase target, double maxRadius) {
        double heightAboveVenkrol;
        boolean inFlingZone;
        double horizDist;
        World world;
        if (target instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)target;
            ItemStack boots = (ItemStack)player.field_71071_by.field_70460_b.get(0);
            if (!boots.func_190926_b() && boots.func_77973_b() == SRPItems.VENKROL_BOOTS) {
                return;
            }
            if (player.func_175149_v()) {
                return;
            }
            if (player.field_71075_bZ.field_75098_d && player.field_71075_bZ.field_75100_b) {
                return;
            }
        }
        if ((world = venkrol.field_70170_p) == null) {
            return;
        }
        if (target.func_184218_aH() || target.field_70128_L) {
            return;
        }
        double dx = venkrol.field_70165_t - target.field_70165_t;
        double dz = venkrol.field_70161_v - target.field_70161_v;
        double distSq = dx * dx + dz * dz;
        if (distSq < 1.0E-4) {
            distSq = 1.0E-4;
        }
        if ((horizDist = Math.sqrt(distSq)) > maxRadius) {
            return;
        }
        double normX = dx / horizDist;
        double normZ = dz / horizDist;
        double pullTierFactor = horizDist >= 50.0 ? 0.05 : (horizDist >= 25.0 ? 0.1 : (horizDist >= 15.0 ? 0.2 : (horizDist >= 10.0 ? 0.35 : (horizDist >= 5.0 ? 0.55 : 1.0))));
        double basePullStrength = 0.08;
        double baseSwirlStrength = 0.07;
        double pullStrength = basePullStrength * pullTierFactor;
        double swirlStrength = baseSwirlStrength * pullTierFactor;
        double swirlX = -normZ;
        double swirlZ = normX;
        double innerLiftRadius = 15.0;
        double liftAccel = 0.0;
        if (horizDist <= innerLiftRadius) {
            double liftFactor = 1.0 - horizDist / innerLiftRadius;
            if (liftFactor < 0.0) {
                liftFactor = 0.0;
            }
            if (liftFactor > 1.0) {
                liftFactor = 1.0;
            }
            double baseLiftAccel = 0.25;
            liftAccel = baseLiftAccel * liftFactor;
        }
        boolean bl = inFlingZone = (heightAboveVenkrol = target.field_70163_u - venkrol.field_70163_u) > 16.0 && horizDist < 12.0;
        if (heightAboveVenkrol > 18.0 && horizDist > 18.0) {
            return;
        }
        double radialDirX = normX;
        double radialDirZ = normZ;
        if (inFlingZone) {
            radialDirX = -normX;
            radialDirZ = -normZ;
            double flingFactor = 1.0 - Math.min(horizDist / 12.0, 1.0);
            double baseFlingMult = 1.4;
            double maxFlingMult = 6.0;
            double flingMult = baseFlingMult + (maxFlingMult - baseFlingMult) * flingFactor;
            pullStrength *= flingMult;
            double baseSwirlMult = 1.0;
            double maxSwirlMult = 2.3;
            double swirlMult = baseSwirlMult + (maxSwirlMult - baseSwirlMult) * flingFactor;
            swirlStrength *= swirlMult;
            double outwardBurst = 1.1 * flingFactor;
            target.field_70159_w += radialDirX * outwardBurst;
            target.field_70179_y += radialDirZ * outwardBurst;
            double downwardBoost = -0.16 * flingFactor;
            target.field_70181_x += downwardBoost;
            if (target.field_70181_x < -1.6) {
                target.field_70181_x = -1.6;
            }
        } else if (liftAccel > 0.0 && heightAboveVenkrol < 25.0) {
            target.field_70181_x += liftAccel;
            if (target.field_70181_x > 1.2) {
                target.field_70181_x = 1.2;
            }
            target.field_70143_R = 0.0f;
        }
        double awayX = -normX;
        double awayZ = -normZ;
        double dotAway = target.field_70159_w * awayX + target.field_70179_y * awayZ;
        double forceScale = dotAway > 0.0 ? 1.0 : 0.25;
        target.field_70159_w += radialDirX * (pullStrength *= forceScale) + swirlX * swirlStrength;
        target.field_70179_y += radialDirZ * pullStrength + swirlZ * swirlStrength;
        target.field_70133_I = true;
    }
}

