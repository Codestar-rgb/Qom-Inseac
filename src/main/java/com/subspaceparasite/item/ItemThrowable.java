/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.CooldownTracker
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.entity.projectile.EntityThrowableAntiInfestedBlock;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemThrowable
extends Item {
    public ItemThrowable(String name, int maxStack) {
        this.setRegistryName(name);
        this.func_77655_b("subspaceparasite." + name);
        this.func_77637_a(SPMain.SP_CREATIVETAB);
        this.field_77777_bU = maxStack;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.func_184586_b(handIn);
        if (!playerIn.field_71075_bZ.field_75098_d) {
            itemstack.func_190918_g(1);
        }
        worldIn.func_184148_a((EntityPlayer)null, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundEvents.field_187511_aA, SoundCategory.PLAYERS, 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!worldIn.field_72995_K) {
            EntityThrowableAntiInfestedBlock entityegg = new EntityThrowableAntiInfestedBlock(worldIn, (EntityLivingBase)playerIn);
            entityegg.func_184538_a((Entity)playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 0.5f, 5.0f);
            CooldownTracker track = playerIn.func_184811_cZ();
            track.func_185145_a(itemstack.func_77973_b(), 20);
            worldIn.func_72838_d((Entity)entityegg);
        }
        playerIn.func_71029_a(StatList.func_188057_b((Item)this));
        return new ActionResult(EnumActionResult.SUCCESS, (Object)itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.AQUA + I18n.func_135052_a((String)"tootip.subspaceparasite.quench", (Object[])new Object[0]));
    }
}

