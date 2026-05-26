/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item.tool;

import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.item.tool.WeaponToolMeleeBase;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponMeleeLance
extends WeaponToolMeleeBase {
    public WeaponMeleeLance(Item.ToolMaterial material, String name, double attackspeed, float range, float attackD, boolean fear, byte id) {
        super(material, name, attackspeed, range, attackD, fear, id);
    }

    @Override
    public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.func_77622_d(stack, worldIn, playerIn);
    }

    @Override
    public Item getNext() {
        if (this == SPItems.weapon_lance) {
            return SPItems.weapon_lanceSentient;
        }
        return null;
    }

    @Override
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean flag = super.func_77644_a(stack, target, attacker);
        if (flag) {
            double chance = 0.1;
            int amp = 0;
            if (this.calling) {
                chance = 0.25;
                amp = 1;
            }
            if (attacker.field_70170_p.field_73012_v.nextDouble() < chance) {
                SPPotions.applyStackPotion(SPPotions.DLER_E, target, 200, amp);
            }
        }
        return flag;
    }

    @Override
    public EnumRarity func_77613_e(ItemStack stack) {
        return super.func_77613_e(stack);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.func_77624_a(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.YELLOW + I18n.func_135052_a((String)("tootip.subspaceparasite.weaponm." + this.idTool), (Object[])new Object[0]));
        tooltip.add(TextFormatting.RED + I18n.func_135052_a((String)("tootip.subspaceparasite.weaponm." + this.idTool * 10), (Object[])new Object[0]));
        if (this.calling && SPConfigSystems.useScent) {
            tooltip.add(TextFormatting.BLACK + I18n.func_135052_a((String)("tootip.subspaceparasite.weaponm." + this.idTool * 100), (Object[])new Object[0]));
        }
    }
}

