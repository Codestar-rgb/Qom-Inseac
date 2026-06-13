/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.item.ItemBase;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLure
extends ItemBase {
    int sound = 0;

    public ItemLure(String name, int maxStack, byte version) {
        super(name, maxStack, version);
        this.version = version;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            for (int i = 0; i < playerIn.field_71071_by.field_70462_a.size(); ++i) {
            }
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, playerIn.field_70165_t + 1.0, playerIn.field_70163_u + 1.0, playerIn.field_70161_v + 1.0).func_186662_g(14.0);
            List moblist = playerIn.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob != playerIn) continue;
            }
        } else {
            System.out.println("client phase " + SRPEventHandlerBus.clientCurrentEvoPhase + " client scent " + SRPEventHandlerBus.clientScent + " client vector " + SRPEventHandlerBus.clientVector + " music " + SRPEventHandlerBus.musicTimer);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.AQUA + I18n.func_135052_a((String)("tootip.srparasites.lurecomp." + this.version), (Object[])new Object[0]));
    }
}

