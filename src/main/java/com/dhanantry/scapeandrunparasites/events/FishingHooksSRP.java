/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ItemFishedEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.dhanantry.scapeandrunparasites.events;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Locale;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FishingHooksSRP {
    public static void register() {
        MinecraftForge.EVENT_BUS.register((Object)new FishingHooksSRP());
    }

    @SubscribeEvent
    public void onItemFished(ItemFishedEvent e) {
        EntityFishHook hook = e.getHookEntity();
        if (hook == null) {
            return;
        }
        World w = hook.field_70170_p;
        if (w.field_72995_K) {
            return;
        }
        BlockPos pos = new BlockPos(hook.field_70165_t, hook.field_70163_u, hook.field_70161_v);
        Biome biome = w.func_180494_b(pos);
        boolean inHarlequin = false;
        ResourceLocation bn = (ResourceLocation)Biome.field_185377_q.func_177774_c((Object)biome);
        if (bn != null) {
            String path = bn.func_110623_a();
            inHarlequin = path != null && path.toLowerCase(Locale.ROOT).contains("harlequin");
        }
        boolean inDeadblood = false;
        ResourceLocation blockName = w.func_180495_p(pos).func_177230_c().getRegistryName();
        if (blockName != null) {
            String bp = blockName.func_110623_a();
            boolean bl = inDeadblood = bp != null && bp.toLowerCase(Locale.ROOT).contains("deadblood");
        }
        if (inHarlequin || inDeadblood) {
            e.getDrops().clear();
            e.getDrops().add((Object)new ItemStack(SRPItems.fishlin));
        }
    }
}

