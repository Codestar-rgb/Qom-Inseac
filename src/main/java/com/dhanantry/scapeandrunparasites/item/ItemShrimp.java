/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShrimp
extends ItemFood {
    public ItemShrimp(String name) {
        super(4, 0.4f, true);
        this.setRegistryName(name);
        this.func_77655_b("srparasites." + name);
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
        this.func_77625_d(64);
        this.func_77848_i();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.WHITE + I18n.func_135052_a((String)"tooltip.srparasites.shrimp.desc", (Object[])new Object[0]));
        tooltip.add(TextFormatting.GRAY + I18n.func_135052_a((String)"tooltip.srparasites.shrimp.arrow", (Object[])new Object[0]));
        tooltip.add(TextFormatting.DARK_AQUA + I18n.func_135052_a((String)"tooltip.srparasites.shrimp.ariral", (Object[])new Object[0]));
    }

    protected void func_77849_c(ItemStack stack, World worldIn, EntityPlayer player) {
        super.func_77849_c(stack, worldIn, player);
        if (!worldIn.field_72995_K && player != null) {
            worldIn.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SRPSounds.SHRIMP_EAT, SoundCategory.PLAYERS, 0.8f, 0.95f + worldIn.field_73012_v.nextFloat() * 0.1f);
        }
    }

    public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (!(target instanceof EntityInfEnderman)) {
            return false;
        }
        EntityInfEnderman enderman = (EntityInfEnderman)target;
        if (enderman.isAriral()) {
            return true;
        }
        if (!player.field_70170_p.field_72995_K) {
            enderman.setAriral(true);
            if (!player.field_71075_bZ.field_75098_d) {
                stack.func_190918_g(1);
            }
            player.field_70170_p.func_184148_a(null, enderman.field_70165_t, enderman.field_70163_u, enderman.field_70161_v, SoundEvents.field_187739_dZ, SoundCategory.NEUTRAL, 0.6f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f);
        }
        return true;
    }
}

