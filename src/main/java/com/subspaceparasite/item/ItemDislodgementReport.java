/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.client.gui.GuiDislodgementReport;
import com.subspaceparasite.item.ItemBase;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDislodgementReport
extends ItemBase {
    public static final String NBT_PRINT_DAY = "PrintDay";
    public static final String NBT_PRINT_TIME = "PrintTime";
    public static final String NBT_CODE = "DislodgementCode";

    public ItemDislodgementReport(String name, int maxStack, byte id) {
        super(name, maxStack, id);
        this.func_77625_d(1);
    }

    public static NBTTagCompound getOrCreate(ItemStack stack) {
        NBTTagCompound t = stack.func_77978_p();
        if (t == null) {
            t = new NBTTagCompound();
            stack.func_77982_d(t);
        }
        return t;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (worldIn.field_72995_K) {
            this.openGui(stack);
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
    }

    @SideOnly(value=Side.CLIENT)
    private void openGui(ItemStack stack) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiDislodgementReport(stack));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GRAY + I18n.func_135052_a((String)"tooltip.subspaceparasite.dislodgement_report", (Object[])new Object[0]));
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && tag.func_74764_b(NBT_PRINT_DAY)) {
            int day = tag.func_74762_e(NBT_PRINT_DAY);
            int time = tag.func_74762_e(NBT_PRINT_TIME);
            tooltip.add(TextFormatting.DARK_GRAY + I18n.func_135052_a((String)"tooltip.subspaceparasite.printed", (Object[])new Object[]{day, time}));
        }
    }

    private static int safeParseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        }
        catch (Throwable t) {
            return fallback;
        }
    }

    private static String getDislodgementEventKey(int event) {
        switch (event) {
            case 0: {
                return "subspaceparasite.dislodgement.event.0";
            }
            case 1: {
                return "subspaceparasite.dislodgement.event.1";
            }
            case 2: {
                return "subspaceparasite.dislodgement.event.2";
            }
            case 3: {
                return "subspaceparasite.dislodgement.event.3";
            }
            case 4: {
                return "subspaceparasite.dislodgement.event.4";
            }
            case 6: {
                return "subspaceparasite.dislodgement.event.6";
            }
            case 7: {
                return "subspaceparasite.dislodgement.event.7";
            }
            case 8: {
                return "subspaceparasite.dislodgement.event.8";
            }
            case 9: {
                return "subspaceparasite.dislodgement.event.9";
            }
            case 10: {
                return "subspaceparasite.dislodgement.event.10";
            }
            case 11: {
                return "subspaceparasite.dislodgement.event.11";
            }
            case 12: {
                return "subspaceparasite.dislodgement.event.12";
            }
            case 13: {
                return "subspaceparasite.dislodgement.event.13";
            }
            case 14: {
                return "subspaceparasite.dislodgement.event.14";
            }
            case 15: {
                return "subspaceparasite.dislodgement.event.15";
            }
            case 16: {
                return "subspaceparasite.dislodgement.event.16";
            }
            case 17: {
                return "subspaceparasite.dislodgement.event.17";
            }
            case 18: {
                return "subspaceparasite.dislodgement.event.18";
            }
            case 19: {
                return "subspaceparasite.dislodgement.event.19";
            }
            case 20: {
                return "subspaceparasite.dislodgement.event.20";
            }
            case 21: {
                return "subspaceparasite.dislodgement.event.21";
            }
            case 22: {
                return "subspaceparasite.dislodgement.event.22";
            }
            case 25: {
                return "subspaceparasite.dislodgement.event.25";
            }
        }
        return "subspaceparasite.dislodgement.event.unknown";
    }

    private static String formatDislodgementEventMeaningClient(int event, String valueStr) {
        String key = ItemDislodgementReport.getDislodgementEventKey(event);
        switch (event) {
            case 0: 
            case 11: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 20: {
                return I18n.func_135052_a((String)key, (Object[])new Object[0]);
            }
        }
        return I18n.func_135052_a((String)key, (Object[])new Object[]{valueStr});
    }
}

