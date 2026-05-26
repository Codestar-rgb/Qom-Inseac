/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 */
package com.subspaceparasite.item;

import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.item.ItemBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemThornshadeDecanter
extends ItemBase {
    private static final int EFFECT_DURATION_TICKS = 400;

    public ItemThornshadeDecanter() {
        super("thornshade_decanter", 16, (byte)29);
    }

    public int func_77626_a(ItemStack stack) {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        playerIn.func_184598_c(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
    }

    public ItemStack func_77654_b(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entityLiving;
            if (!worldIn.field_72995_K) {
                player.func_70690_d(new PotionEffect(SPPotions.THORNSHADE_THORNS_E, 400, 0, false, true));
            }
            if (!player.field_71075_bZ.field_75098_d) {
                stack.func_190918_g(1);
            }
        }
        return stack;
    }
}

