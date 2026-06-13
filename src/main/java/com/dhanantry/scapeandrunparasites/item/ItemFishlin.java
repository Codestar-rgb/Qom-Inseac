/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.item.ItemBase;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFishlin
extends ItemBase {
    private static final int HUNGER = 3;
    private static final float SATURATION = 0.2f;
    private final boolean alwaysEdible = true;

    public ItemFishlin(String name) {
        super(name, 64, (byte)0);
        this.func_77625_d(64);
    }

    public int func_77626_a(ItemStack stack) {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.EAT;
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        player.func_184598_c(hand);
        return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityLivingBase eater) {
        if (eater instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)eater;
            if (!p.field_71075_bZ.field_75098_d) {
                stack.func_190918_g(1);
            }
            p.func_71024_bL().func_75122_a(3, 0.2f);
            world.func_184148_a(null, p.field_70165_t, p.field_70163_u, p.field_70161_v, SoundEvents.field_187739_dZ, SoundCategory.PLAYERS, 0.8f, 1.0f);
            p.func_70097_a(DamageSource.field_76376_m, 8.0f);
            p.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 4800, 1, false, false));
        }
        return stack;
    }

    public String func_77658_a() {
        return "item.srparasites." + this.getRegistryName().func_110623_a();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.func_135052_a((String)"tooltip.srparasites.fishlin", (Object[])new Object[0]));
    }
}

