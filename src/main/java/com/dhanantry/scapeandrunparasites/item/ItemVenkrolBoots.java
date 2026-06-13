/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVenkrolBoots
extends ItemArmor {
    private static final ItemArmor.ArmorMaterial VENKROL_BOOT_MAT = EnumHelper.addArmorMaterial((String)"VENKROL_BOOT", (String)"srparasites:venkrol_boot", (int)40, (int[])new int[]{4, 7, 9, 4}, (int)20, (SoundEvent)SoundEvents.field_187716_o, (float)3.0f);

    public ItemVenkrolBoots(String name) {
        super(VENKROL_BOOT_MAT, 0, EntityEquipmentSlot.FEET);
        this.setRegistryName("srparasites", name);
        this.func_77655_b("srparasites." + name);
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "srparasites:textures/models/armor/venkrol_boot_layer_1.png";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.func_135052_a((String)"tooltip.srparasites.item.venkrol_boots.0", (Object[])new Object[0]));
    }
}

