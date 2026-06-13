/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.shader.BreatheShaderManager;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAlveolarFluid
extends Item {
    private static final int DURATION = 600;

    private void init(String name) {
        this.setRegistryName(name);
        this.func_77655_b("srparasites." + name);
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
        this.func_77625_d(1);
        SRPItems.SRP_ITEMS.add(this);
    }

    public ItemAlveolarFluid(String name) {
        this.init(name);
    }

    public int func_77626_a(ItemStack stack) {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public ActionResult<ItemStack> func_77659_a(World w, EntityPlayer p, EnumHand hand) {
        p.func_184598_c(hand);
        return new ActionResult(EnumActionResult.SUCCESS, (Object)p.func_184586_b(hand));
    }

    public ItemStack func_77654_b(ItemStack stack, World w, EntityLivingBase entity) {
        EntityPlayer player;
        if (!w.field_72995_K) {
            entity.func_70690_d(new PotionEffect(MobEffects.field_76422_e, 600, 0));
            entity.func_70690_d(new PotionEffect(MobEffects.field_76424_c, 600, 0));
            entity.func_70690_d(new PotionEffect(SRPPotions.VIRA_E, 600, 2));
        }
        if (w.field_72995_K && entity instanceof EntityPlayer) {
            BreatheShaderManager.get().enableFor(600);
        }
        EntityPlayer entityPlayer = player = entity instanceof EntityPlayer ? (EntityPlayer)entity : null;
        if (player == null || !player.field_71075_bZ.field_75098_d) {
            stack.func_190918_g(1);
            if (player != null) {
                ItemStack bottle = new ItemStack(Items.field_151069_bo);
                if (stack.func_190926_b()) {
                    return bottle;
                }
                if (!player.field_71071_by.func_70441_a(bottle)) {
                    player.func_71019_a(bottle, false);
                }
            }
        }
        return stack;
    }
}

