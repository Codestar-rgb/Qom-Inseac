/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiBestiary;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiaryRequest;
import com.dhanantry.scapeandrunparasites.client.FieldGuideClientSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ItemSRPFieldGuide
extends Item {
    public ItemSRPFieldGuide() {
        this.setRegistryName("srp_field_guide");
        this.func_77655_b("srparasites.srp_field_guide");
        this.func_77625_d(1);
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        if (world.field_72995_K) {
            return ClientHandler.handleClient(player, hand);
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)player.func_184586_b(hand));
    }

    private static class ClientHandler {
        private ClientHandler() {
        }

        public static ActionResult<ItemStack> handleClient(EntityPlayer player, EnumHand hand) {
            if (player.func_70093_af()) {
                FieldGuideClientSettings.bestiarySounds = !FieldGuideClientSettings.bestiarySounds;
                player.func_145747_a((ITextComponent)new TextComponentTranslation(FieldGuideClientSettings.bestiarySounds ? "srpguide.sounds.on" : "srpguide.sounds.off", new Object[0]));
                return new ActionResult(EnumActionResult.SUCCESS, (Object)player.func_184586_b(hand));
            }
            BestiaryNetwork.CH.sendToServer((IMessage)new PacketBestiaryRequest());
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiBestiary(player));
            return new ActionResult(EnumActionResult.SUCCESS, (Object)player.func_184586_b(hand));
        }
    }
}

