/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityArrow$PickupStatus
 *  net.minecraft.entity.projectile.EntityTippedArrow
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.IItemPropertyGetter
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArrow
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item.tool;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponToolRangeBase
extends ItemBow {
    private int bonus;
    private int damage;
    private int damageCap;
    private boolean calling;
    private byte idTool;

    public WeaponToolRangeBase(String name, int durability, int bonus, int damageC, int damage, boolean fear, byte id) {
        this.setRegistryName("weapon_" + name);
        this.func_77655_b("srparasites.weapon_" + name);
        this.field_77777_bU = 1;
        this.bonus = bonus;
        this.damageCap = damageC;
        this.damage = damage;
        this.calling = fear;
        this.idTool = id;
        this.func_77656_e(durability);
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
        this.func_185043_a(new ResourceLocation("pull"), new IItemPropertyGetter(){

            @SideOnly(value=Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0f;
                }
                return !(entityIn.func_184607_cu().func_77973_b() instanceof WeaponToolRangeBase) ? 0.0f : (float)(stack.func_77988_m() - entityIn.func_184605_cv()) / 50.0f;
            }
        });
        this.func_185043_a(new ResourceLocation("pulling"), new IItemPropertyGetter(){

            @SideOnly(value=Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.func_184587_cr() && entityIn.func_184607_cu() == stack ? 1.0f : 0.0f;
            }
        });
        this.func_185043_a(new ResourceLocation("vinni"), new IItemPropertyGetter(){

            @SideOnly(value=Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return 0.0f;
            }
        });
    }

    protected ItemStack func_185060_a(EntityPlayer player) {
        if (this.func_185058_h_(player.func_184586_b(EnumHand.OFF_HAND))) {
            return player.func_184586_b(EnumHand.OFF_HAND);
        }
        if (this.func_185058_h_(player.func_184586_b(EnumHand.MAIN_HAND))) {
            return player.func_184586_b(EnumHand.MAIN_HAND);
        }
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack itemstack = player.field_71071_by.func_70301_a(i);
            if (!this.func_185058_h_(itemstack)) continue;
            return itemstack;
        }
        return ItemStack.field_190927_a;
    }

    protected boolean func_185058_h_(ItemStack stack) {
        return stack.func_77973_b() instanceof ItemArrow;
    }

    public void func_77615_a(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185312_x, (ItemStack)stack) > 0;
            ItemStack itemstack = this.func_185060_a(entityplayer);
            int i = this.func_77626_a(stack) - timeLeft;
            if ((i = ForgeEventFactory.onArrowLoose((ItemStack)stack, (World)worldIn, (EntityPlayer)entityplayer, (int)i, (!itemstack.func_190926_b() || flag ? 1 : 0) != 0)) < 0) {
                return;
            }
            double seconds = (72000 - timeLeft) / 20;
            double bonusD = seconds * (double)this.bonus;
            if (!itemstack.func_190926_b() || flag) {
                float f;
                if (itemstack.func_190926_b()) {
                    itemstack = new ItemStack(Items.field_151032_g);
                }
                if ((double)(f = WeaponToolRangeBase.func_185059_b((int)i)) >= 0.1) {
                    boolean flag1;
                    boolean bl = flag1 = entityplayer.field_71075_bZ.field_75098_d || itemstack.func_77973_b() instanceof ItemArrow && ((ItemArrow)itemstack.func_77973_b()).isInfinite(itemstack, stack, entityplayer);
                    if (!worldIn.field_72995_K) {
                        int k;
                        int j;
                        ItemArrow itemarrow = (ItemArrow)(itemstack.func_77973_b() instanceof ItemArrow ? itemstack.func_77973_b() : Items.field_151032_g);
                        EntityArrow entityarrow = itemarrow.func_185052_a(worldIn, itemstack, (EntityLivingBase)entityplayer);
                        if (entityarrow instanceof EntityTippedArrow) {
                            EntityTippedArrow tipped = (EntityTippedArrow)entityarrow;
                            tipped.func_184558_a(new PotionEffect(SRPPotions.BLEED_E, 200, 0, false, true));
                            tipped.func_184558_a(new PotionEffect(SRPPotions.DOD_SMOKE_TRAIL_E, 200, 0, false, true));
                        }
                        entityarrow.func_184547_a((Entity)entityplayer, entityplayer.field_70125_A, entityplayer.field_70177_z, 0.0f, f * 4.4f, 0.0f);
                        if (f == 1.0f) {
                            entityarrow.func_70243_d(true);
                        }
                        if ((j = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185309_u, (ItemStack)stack)) > 0) {
                            entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)j * 0.5 + 0.5);
                        }
                        if ((k = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185310_v, (ItemStack)stack)) > 0) {
                            entityarrow.func_70240_a(k);
                        }
                        if (EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185311_w, (ItemStack)stack) > 0) {
                            entityarrow.func_70015_d(100);
                        }
                        stack.func_77972_a(1, (EntityLivingBase)entityplayer);
                        if (flag1 || entityplayer.field_71075_bZ.field_75098_d && (itemstack.func_77973_b() == Items.field_185166_h || itemstack.func_77973_b() == Items.field_185167_i)) {
                            entityarrow.field_70251_a = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }
                        if (bonusD > (double)(this.damage * this.damageCap)) {
                            bonusD = this.damage * this.damageCap;
                        }
                        entityarrow.func_70239_b(entityarrow.func_70242_d() * bonusD);
                        entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)this.damage);
                        worldIn.func_72838_d((Entity)entityarrow);
                    }
                    worldIn.func_184148_a((EntityPlayer)null, entityplayer.field_70165_t, entityplayer.field_70163_u, entityplayer.field_70161_v, SoundEvents.field_187737_v, SoundCategory.PLAYERS, 1.0f, 1.0f / (field_77697_d.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
                    if (!flag1 && !entityplayer.field_71075_bZ.field_75098_d) {
                        itemstack.func_190918_g(1);
                        if (itemstack.func_190926_b()) {
                            entityplayer.field_71071_by.func_184437_d(itemstack);
                        }
                    }
                    entityplayer.func_71029_a(StatList.func_188057_b((Item)this));
                }
            }
        }
    }

    public int func_77619_b() {
        return 10;
    }

    public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.field_72995_K && this.calling && SRPConfigSystems.useScent && worldIn.field_73012_v.nextInt(10) == 0 && entityIn.field_70173_aa % 40 == 0 && SRPSaveData.get(worldIn, 222).getDeveLevel() >= SRPConfigSystems.deveScentUse) {
            ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(SRPPotions.PREY_E, 1200, 0, false, false));
        }
    }

    public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.func_77622_d(stack, worldIn, playerIn);
    }

    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.YELLOW + I18n.func_135052_a((String)("tootip.srparasites.weaponr." + this.idTool), (Object[])new Object[0]));
        tooltip.add(TextFormatting.RED + I18n.func_135052_a((String)("tootip.srparasites.weaponr." + this.idTool * 10), (Object[])new Object[0]));
        if (this.calling && SRPConfigSystems.useScent) {
            tooltip.add(TextFormatting.BLACK + I18n.func_135052_a((String)("tootip.srparasites.weaponr." + this.idTool * 100), (Object[])new Object[0]));
        }
    }
}

