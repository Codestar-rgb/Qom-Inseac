/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.bestiary.blocks;

import com.subspaceparasite.bestiary.blocks.BlockBestiaryEntry;
import com.subspaceparasite.bestiary.blocks.SPBlockCompendiumRegistry;
import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import com.subspaceparasite.bestiary.net.BestiaryNetwork;
import com.subspaceparasite.bestiary.net.PacketBestiarySync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

@Mod.EventBusSubscriber(modid="subspaceparasite")
public class BlockDiscoveryHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        EntityPlayer player = e.player;
        if (player == null || player.field_70170_p == null || player.field_70170_p.field_72995_K) {
            return;
        }
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP p = (EntityPlayerMP)player;
        IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        boolean changed = false;
        if (p.field_71071_by != null) {
            changed |= BlockDiscoveryHandler.scanInventory((IInventory)p.field_71071_by, prog);
        }
        if (changed) {
            BestiaryNetwork.CH.sendTo((IMessage)new PacketBestiarySync(prog), p);
        }
    }

    private static boolean scanInventory(IInventory inv, IBestiaryProgress prog) {
        boolean changed = false;
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            BlockBestiaryEntry entry;
            ItemBlock itemBlock;
            ResourceLocation id;
            ItemStack st = inv.func_70301_a(i);
            if (st.func_190926_b() || !(st.func_77973_b() instanceof ItemBlock) || (id = (itemBlock = (ItemBlock)st.func_77973_b()).func_179223_d().getRegistryName()) == null || (entry = SPBlockCompendiumRegistry.get(id)) == null || prog.hasSeenBlock(id)) continue;
            prog.markBlockSeen(id);
            changed = true;
        }
        return changed;
    }
}

