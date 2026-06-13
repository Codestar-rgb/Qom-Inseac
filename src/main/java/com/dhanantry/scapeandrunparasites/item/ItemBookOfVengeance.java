/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.item.VengeanceGrappleHandler;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBookOfVengeance
extends Item {
    private static final int RANGE = 32;
    private static final int COOLDOWN_TICKS = 60;
    private static final int KB_COOLDOWN_TICKS = 80;
    private static final String NBT_KB_NEXT = "srp_kb_next";
    private static final double KB_RADIUS = 5.0;
    private static final double KB_STRENGTH = 2.2;
    private static final double KB_UP = 0.4;
    private static final double KB_SELF_JUMP_Y = 0.42;
    private static final boolean KB_CANCEL_DOWNWARD = true;

    public ItemBookOfVengeance() {
        this.setRegistryName("book_of_vengeance");
        this.func_77655_b("srparasites.book_of_vengeance");
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        String base = "item.srparasites.book_of_vengeance.";
        tooltip.add(I18n.func_135052_a((String)(base + "vengeance0"), (Object[])new Object[0]));
        tooltip.add(I18n.func_135052_a((String)(base + "vengeance1"), (Object[])new Object[0]));
        tooltip.add(I18n.func_135052_a((String)(base + "vengeance3"), (Object[])new Object[0]));
        tooltip.add(I18n.func_135052_a((String)(base + "vengeance5"), (Object[])new Object[0]));
        tooltip.add(I18n.func_135052_a((String)(base + "vengeance6"), (Object[])new Object[0]));
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (player.func_70093_af()) {
            if (!world.field_72995_K) {
                if (!(world instanceof WorldServer)) {
                    return new ActionResult(EnumActionResult.FAIL, (Object)stack);
                }
                WorldServer ws = (WorldServer)world;
                if (ItemBookOfVengeance.isKnockbackOnCooldown(stack, ws.func_82737_E())) {
                    return new ActionResult(EnumActionResult.FAIL, (Object)stack);
                }
                ItemBookOfVengeance.doKnockbackBurst(ws, player);
                ItemBookOfVengeance.setKnockbackCooldown(stack, ws.func_82737_E() + 80L);
                player.func_184609_a(hand);
            }
            return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
        }
        if (player.func_184811_cZ().func_185141_a((Item)this)) {
            return new ActionResult(EnumActionResult.FAIL, (Object)stack);
        }
        if (!world.field_72995_K) {
            EntityLivingBase target = ItemBookOfVengeance.findLookTargetLiving(player, 32.0);
            if (target == null) {
                return new ActionResult(EnumActionResult.FAIL, (Object)stack);
            }
            if (!player.func_70685_l((Entity)target)) {
                return new ActionResult(EnumActionResult.FAIL, (Object)stack);
            }
            VengeanceGrappleHandler.start(player, target);
            player.func_184811_cZ().func_185145_a((Item)this, 60);
            player.func_184609_a(hand);
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
    }

    private static void doKnockbackBurst(WorldServer ws, EntityPlayer player) {
        ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SRPSounds.VENGEANCE_CHAIN_IMPACT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SRPSounds.VENGEANCE_IMPACT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        ItemBookOfVengeance.spawnPinkBurst(ws, new Vec3d(player.field_70165_t, player.field_70163_u + 0.9, player.field_70161_v), 26);
        AxisAlignedBB box = player.func_174813_aQ().func_72314_b(5.0, 1.5, 5.0);
        List ents = ws.func_72872_a(EntityLivingBase.class, box);
        for (EntityLivingBase ent : ents) {
            if (ent == null || ent.field_70128_L || ent == player) continue;
            double dx = ent.field_70165_t - player.field_70165_t;
            double dz = ent.field_70161_v - player.field_70161_v;
            double distSq = dx * dx + dz * dz;
            if (distSq < 1.0E-4) {
                dx = (ws.field_73012_v.nextDouble() - 0.5) * 0.2;
                dz = (ws.field_73012_v.nextDouble() - 0.5) * 0.2;
                distSq = dx * dx + dz * dz;
            }
            double dist = Math.sqrt(distSq);
            double nx = dx / dist;
            double nz = dz / dist;
            double falloff = 1.0 - Math.min(1.0, dist / 5.0);
            double push = 2.2 * (0.45 + 0.55 * falloff);
            ent.field_70159_w += nx * push;
            ent.field_70179_y += nz * push;
            ent.field_70181_x = Math.max(ent.field_70181_x, 0.4 + 0.15 * falloff);
            ent.field_70133_I = true;
        }
        player.field_70181_x = Math.max(player.field_70181_x, 0.42);
        player.field_70133_I = true;
        player.field_70122_E = false;
        player.field_70143_R = 0.0f;
        ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187730_dW, SoundCategory.PLAYERS, 0.6f, 1.6f);
        ws.func_175739_a(EnumParticleTypes.CLOUD, player.field_70165_t, player.field_70163_u + 0.1, player.field_70161_v, 8, 0.25, 0.05, 0.25, 0.02, new int[0]);
    }

    private static void spawnPinkBurst(WorldServer ws, Vec3d center, int points) {
        double r = 1.0;
        double g = 0.25;
        double b = 0.85;
        for (int i = 0; i < points; ++i) {
            double ox = (ws.field_73012_v.nextDouble() - 0.5) * 1.1;
            double oy = (ws.field_73012_v.nextDouble() - 0.5) * 0.6;
            double oz = (ws.field_73012_v.nextDouble() - 0.5) * 1.1;
            ws.func_175739_a(EnumParticleTypes.REDSTONE, center.field_72450_a + ox, center.field_72448_b + oy, center.field_72449_c + oz, 0, 1.0, 0.25, 0.85, 1.0, new int[0]);
        }
    }

    private static boolean isKnockbackOnCooldown(ItemStack stack, long worldTime) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag == null) {
            return false;
        }
        return tag.func_74763_f(NBT_KB_NEXT) > worldTime;
    }

    private static void setKnockbackCooldown(ItemStack stack, long nextUseTick) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        tag.func_74772_a(NBT_KB_NEXT, nextUseTick);
        stack.func_77982_d(tag);
    }

    private static EntityLivingBase findLookTargetLiving(EntityPlayer player, double range) {
        World world = player.field_70170_p;
        Vec3d eye = player.func_174824_e(1.0f);
        Vec3d look = player.func_70676_i(1.0f);
        Vec3d end = eye.func_72441_c(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range);
        RayTraceResult blockHit = world.func_147447_a(eye, end, false, true, false);
        if (blockHit != null && blockHit.field_72313_a == RayTraceResult.Type.BLOCK) {
            end = blockHit.field_72307_f;
        }
        AxisAlignedBB searchBox = player.func_174813_aQ().func_72321_a(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range).func_186662_g(1.0);
        List entities = world.func_72839_b((Entity)player, searchBox);
        EntityLivingBase best = null;
        double bestDist = Double.MAX_VALUE;
        for (Entity e : entities) {
            double dist;
            AxisAlignedBB bb;
            RayTraceResult hit;
            EntityLivingBase living;
            if (!(e instanceof EntityLivingBase) || !e.func_70067_L() || (living = (EntityLivingBase)e) == player || living.field_70128_L || (hit = (bb = living.func_174813_aQ().func_186662_g(0.3)).func_72327_a(eye, end)) == null || !((dist = eye.func_72438_d(hit.field_72307_f)) < bestDist)) continue;
            bestDist = dist;
            best = living;
        }
        if (best != null && !player.func_70685_l(best)) {
            return null;
        }
        return best;
    }
}

