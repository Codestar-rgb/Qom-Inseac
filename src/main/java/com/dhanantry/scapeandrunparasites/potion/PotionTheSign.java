/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.potion.SRPEffectBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionTheSign
extends SRPEffectBase {
    private static final ResourceLocation ICON = new ResourceLocation("srparasites", "textures/gui/the_sign.png");

    public PotionTheSign() {
        super("the_sign", false, 8970751, 0, 0);
        this.func_76390_b("mob_effect.srparasites.the_sign");
    }

    @Override
    public boolean func_76400_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(ICON);
        Gui.func_146110_a((int)(x + 3), (int)(y + 3), (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(ICON);
        Gui.func_146110_a((int)(x + 4), (int)(y + 4), (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
    }
}

