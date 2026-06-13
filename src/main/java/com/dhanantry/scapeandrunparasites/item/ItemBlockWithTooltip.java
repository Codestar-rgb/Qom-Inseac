/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockWithTooltip
extends ItemBlock {
    public ItemBlockWithTooltip(Block block) {
        super(block);
        this.setRegistryName(block.getRegistryName());
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.field_150939_a == null || this.field_150939_a.getRegistryName() == null) {
            return;
        }
        String key = "tooltip." + this.field_150939_a.getRegistryName().func_110623_a();
        String translated = I18n.func_135052_a((String)key, (Object[])new Object[0]);
        if (!translated.equals(key) && !translated.isEmpty()) {
            if (translated.contains("\\n")) {
                Arrays.stream(translated.split("\\\\n")).forEach(tooltip::add);
            } else {
                tooltip.add(translated);
            }
        }
    }
}

