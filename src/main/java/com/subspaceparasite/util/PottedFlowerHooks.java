/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityFlowerPot
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.util;

import com.subspaceparasite.block.BlockPottedSPFlower;
import com.subspaceparasite.init.SPBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="subspaceparasite")
public final class PottedFlowerHooks {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock e) {
        if (e.getFace() == EnumFacing.UP) {
            return;
        }
        if (e.getWorld().func_180495_p(e.getPos()).func_177230_c() != Blocks.field_150457_bL) {
            return;
        }
        TileEntity te = e.getWorld().func_175625_s(e.getPos());
        if (!(te instanceof TileEntityFlowerPot)) {
            return;
        }
        if (!((TileEntityFlowerPot)te).func_184403_b().func_190926_b()) {
            return;
        }
        ItemStack held = e.getItemStack();
        if (held.func_190926_b()) {
            return;
        }
        Item item = held.func_77973_b();
        BlockPottedSPFlower potted = null;
        if (item == Item.func_150898_a((Block)SPBlocks.ASSIMILATED_BLOSSOM)) {
            potted = SPBlocks.POTTED_ASSIMILATED_BLOSSOM;
        }
        if (potted == null) {
            return;
        }
        if (!e.getWorld().field_72995_K) {
            e.getWorld().func_180501_a(e.getPos(), potted.func_176223_P(), 3);
            if (!e.getEntityPlayer().field_71075_bZ.field_75098_d) {
                held.func_190918_g(1);
            }
        }
        e.setCanceled(true);
        e.setCancellationResult(EnumActionResult.SUCCESS);
    }
}

