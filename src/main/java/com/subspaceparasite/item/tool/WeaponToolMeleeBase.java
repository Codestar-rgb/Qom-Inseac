/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 */
package com.subspaceparasite.item.tool;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.item.tool.IHaveReach;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WeaponToolMeleeBase
extends ItemSword
implements IHaveReach {
    private float addReach;
    private float attackDamage;
    private byte effectHit;
    protected double attackSpeed;
    protected boolean calling;
    protected byte idTool;
    protected final UUID ATTACK_RANGE_MODIFIER = UUID.randomUUID();

    public WeaponToolMeleeBase(Item.ToolMaterial material, String name, double attackspeed, float range, float attackD, boolean fear, byte id) {
        super(material);
        this.setRegistryName("weapon_" + name);
        this.func_77655_b("subspaceparasite.weapon_" + name);
        this.func_77637_a(SPMain.SP_CREATIVETAB);
        this.attackSpeed = attackspeed;
        this.addReach = range;
        this.attackDamage = attackD;
        this.calling = fear;
        this.idTool = id;
    }

    @Override
    public float getReach() {
        return this.addReach;
    }

    public Multimap<String, AttributeModifier> func_111205_h(EntityEquipmentSlot equipmentSlot) {
        Multimap multimap = super.func_111205_h(equipmentSlot);
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.clear();
            multimap.put((Object)SharedMonsterAttributes.field_111264_e.func_111108_a(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put((Object)SharedMonsterAttributes.field_188790_f.func_111108_a(), (Object)new AttributeModifier(field_185050_h, "Weapon modifier", this.attackSpeed, 0));
            if (SPConfig.weaponCancelPacket) {
                multimap.put((Object)EntityPlayer.REACH_DISTANCE.func_111108_a(), (Object)new AttributeModifier(this.ATTACK_RANGE_MODIFIER, "custom_reach", (double)this.addReach, 0));
            }
        }
        return multimap;
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean flag = super.func_77644_a(stack, target, attacker);
        if (flag && target.func_110143_aJ() <= 0.0f) {
            NBTTagCompound compound = stack.func_77978_p();
            if (compound == null) {
                compound = new NBTTagCompound();
            }
            if (compound.func_74764_b("srpkills")) {
                int key = (int)((float)compound.func_74762_e("srpkills") + target.func_110138_aP());
                compound.func_74768_a("srpkills", key);
            } else {
                compound.func_74768_a("srpkills", (int)target.func_110138_aP());
            }
            stack.func_77982_d(compound);
        }
        return flag;
    }

    public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.field_72995_K) {
            if (this.calling && SPConfigSystems.useScent && worldIn.field_73012_v.nextInt(100) == 0 && entityIn.field_70173_aa % 40 == 0 && SPSaveData.get(worldIn, 222).getDeveLevel() >= SPConfigSystems.deveScentUse) {
                ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(SPPotions.PREY_E, 1200, 0, false, false));
            }
            if (entityIn.field_70173_aa % 80 == 0) {
                int key = 0;
                NBTTagCompound compound = stack.func_77978_p();
                if (compound != null && this.getNext() != null) {
                    if (compound.func_74764_b("srpkills")) {
                        key = compound.func_74762_e("srpkills");
                    }
                    if (key > SPConfig.weapon_livingSentient_HP_needed) {
                        compound.func_74768_a("srpkills", 0);
                        stack.func_190918_g(1);
                        ItemStack stackW = new ItemStack(this.getNext(), 1);
                        EntityItem entityitem = new EntityItem(worldIn, entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, stackW);
                        entityitem.func_174869_p();
                        worldIn.func_72838_d((Entity)entityitem);
                        if (SPConfig.thunderEnable) {
                            worldIn.func_72942_c((Entity)new EntityLightningBolt(worldIn, entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, true));
                        }
                        return;
                    }
                }
            }
        }
    }

    public Item getNext() {
        return null;
    }

    public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.func_77622_d(stack, worldIn, playerIn);
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return super.func_77613_e(stack);
    }

    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound compound = stack.func_77978_p();
        if (compound != null) {
            tooltip.add(TextFormatting.BLUE + "---> " + compound.func_74762_e("srpkills"));
            tooltip.add(TextFormatting.BLUE + "  ");
        }
    }
}

