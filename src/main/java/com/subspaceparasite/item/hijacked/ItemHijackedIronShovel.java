/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item.hijacked;

import com.subspaceparasite.item.hijacked.HijackedHitEffects;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHijackedIronShovel
extends ItemSpade {
    private final String name;

    public ItemHijackedIronShovel(String name, Item.ToolMaterial mat) {
        super(mat);
        this.name = name;
        this.setRegistryName(name);
        this.func_77655_b("subspaceparasite." + name);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        HijackedHitEffects.apply(attacker, target);
        return super.func_77644_a(stack, target, attacker);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String key = "item.subspaceparasite." + this.name + ".desc";
        String s = I18n.func_135052_a((String)key, (Object[])new Object[0]);
        if (!s.equals(key)) {
            tooltip.add(s);
        }
    }
}

