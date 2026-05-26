/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.IItemPropertyGetter
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.item.ItemBase;
import com.subspaceparasite.network.SPPacketClock;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemClockEvolution
extends ItemBase {
    public static int cooldown = 0;
    public static int phase = 0;

    public ItemClockEvolution(String name, int maxStack, byte v) {
        super(name, maxStack, v);
        this.func_185043_a(new ResourceLocation("phase"), new IItemPropertyGetter(){

            @SideOnly(value=Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0f;
                }
                ItemStack main = entityIn.func_184614_ca();
                ItemStack off = entityIn.func_184592_cb();
                if (main == stack || off == stack || ItemStack.func_77989_b((ItemStack)main, (ItemStack)stack) || ItemStack.func_77989_b((ItemStack)off, (ItemStack)stack)) {
                    return phase;
                }
                return 0.0f;
            }
        });
    }

    public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        SPSaveData data;
        if (!worldIn.field_72995_K && entityIn.field_70173_aa % 20 == 0 && (data = SPSaveData.get(worldIn, -11)) != null) {
            cooldown = data.getCooldown(worldIn, worldIn.field_73011_w.getDimension());
            phase = data.getEvolutionPhase(worldIn.field_73011_w.getDimension());
            SPMain.network.sendToAll((IMessage)new SPPacketClock(cooldown, phase, data.getDeveLevel(), 2));
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        SPSaveData data = SPSaveData.get(worldIn, -11);
        tooltip.add(TextFormatting.AQUA + I18n.func_135052_a((String)("-> " + cooldown), (Object[])new Object[0]));
    }
}

