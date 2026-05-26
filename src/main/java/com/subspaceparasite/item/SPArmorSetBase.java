/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPArmorSetBase
extends ItemArmor {
    public static final int BOOTS_SAVE_COOLDOWN_TICKS = 2400;
    public static final int DEBUFF_TICKS = 200;
    public static final int BOOTS_MIN_FALL_DIST = 6;
    private static final String NBT_ROOT = "SPArmorCD";
    private static final String NBT_BOOT_CD = "bootsCD";
    private static final String NBT_SET_CD = "setCD";
    private static final String NBT_BOOT_READY = "bootsReadyPlayed";
    private static final String NBT_SET_READY = "setReadyPlayed";
    private final String setKey;
    public static final ItemArmor.ArmorMaterial HIJACKED_MAT = EnumHelper.addArmorMaterial((String)"HIJACKED", (String)"subspaceparasite:hijacked_iron", (int)40, (int[])new int[]{4, 7, 9, 4}, (int)15, (SoundEvent)SPSounds.FLESH_GROW, (float)3.0f);

    public SPArmorSetBase(String registryName, ItemArmor.ArmorMaterial material, EntityEquipmentSlot slot, String setKey) {
        super(material, 1, slot);
        this.setKey = setKey;
        this.setRegistryName(new ResourceLocation("subspaceparasite", registryName));
        this.func_77655_b("subspaceparasite." + registryName);
        this.func_77637_a(CreativeTabs.field_78037_j);
    }

    public SPArmorSetBase(String registryName) {
        this(registryName, SPArmorSetBase.inferMaterial(registryName), SPArmorSetBase.inferSlot(registryName), SPArmorSetBase.inferSetKey(registryName));
    }

    public String getSetKey() {
        return this.setKey;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (world.field_72995_K) {
            return;
        }
        if (SPArmorSetBase.isWearingFullSet(player, this.setKey) && player.func_70644_a(SPPotions.BLEED_E)) {
            player.func_184596_c(SPPotions.BLEED_E);
        }
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        String layer = slot == EntityEquipmentSlot.LEGS ? "2" : "1";
        return "subspaceparasite:textures/models/armor/" + this.setKey + "_layer_" + layer + ".png";
    }

    public static boolean isWearingFullSet(EntityPlayer player, String setKey) {
        if (player == null || setKey == null || setKey.isEmpty()) {
            return false;
        }
        NonNullList armor = player.field_71071_by.field_70460_b;
        if (armor.size() < 4) {
            return false;
        }
        for (ItemStack st : armor) {
            if (st.func_190926_b() || !(st.func_77973_b() instanceof SPArmorSetBase)) {
                return false;
            }
            if (((SPArmorSetBase)st.func_77973_b()).setKey.equals(setKey)) continue;
            return false;
        }
        return true;
    }

    public static String getAnySetKey(EntityPlayer p) {
        if (p == null) {
            return "";
        }
        ItemStack head = p.func_184582_a(EntityEquipmentSlot.HEAD);
        if (!head.func_190926_b() && head.func_77973_b() instanceof SPArmorSetBase) {
            return ((SPArmorSetBase)head.func_77973_b()).getSetKey();
        }
        ItemStack chest = p.func_184582_a(EntityEquipmentSlot.CHEST);
        if (!chest.func_190926_b() && chest.func_77973_b() instanceof SPArmorSetBase) {
            return ((SPArmorSetBase)chest.func_77973_b()).getSetKey();
        }
        ItemStack legs = p.func_184582_a(EntityEquipmentSlot.LEGS);
        if (!legs.func_190926_b() && legs.func_77973_b() instanceof SPArmorSetBase) {
            return ((SPArmorSetBase)legs.func_77973_b()).getSetKey();
        }
        ItemStack feet = p.func_184582_a(EntityEquipmentSlot.FEET);
        if (!feet.func_190926_b() && feet.func_77973_b() instanceof SPArmorSetBase) {
            return ((SPArmorSetBase)feet.func_77973_b()).getSetKey();
        }
        return "";
    }

    private static ItemArmor.ArmorMaterial inferMaterial(String regName) {
        String s = regName.toLowerCase(Locale.ROOT);
        if (s.startsWith("hijacked_")) {
            return HIJACKED_MAT;
        }
        if (s.contains("leather")) {
            return ItemArmor.ArmorMaterial.LEATHER;
        }
        if (s.contains("chain")) {
            return ItemArmor.ArmorMaterial.CHAIN;
        }
        if (s.contains("gold")) {
            return ItemArmor.ArmorMaterial.GOLD;
        }
        if (s.contains("diamond")) {
            return ItemArmor.ArmorMaterial.DIAMOND;
        }
        return ItemArmor.ArmorMaterial.IRON;
    }

    private static EntityEquipmentSlot inferSlot(String regName) {
        String s = regName.toLowerCase(Locale.ROOT);
        if (s.endsWith("_helmet") || s.endsWith("_helm")) {
            return EntityEquipmentSlot.HEAD;
        }
        if (s.endsWith("_chestpiece") || s.endsWith("_chestplate") || s.endsWith("_chest")) {
            return EntityEquipmentSlot.CHEST;
        }
        if (s.endsWith("_leggings") || s.endsWith("_legs")) {
            return EntityEquipmentSlot.LEGS;
        }
        if (s.endsWith("_boots")) {
            return EntityEquipmentSlot.FEET;
        }
        throw new IllegalArgumentException("Cannot infer armor slot from registry name: " + regName);
    }

    private static String inferSetKey(String regName) {
        int i = regName.lastIndexOf(95);
        return i > 0 ? regName.substring(0, i) : regName;
    }

    private static NBTTagCompound getCDTag(EntityPlayer p) {
        NBTTagCompound root = p.getEntityData();
        if (!root.func_74764_b(NBT_ROOT)) {
            root.func_74782_a(NBT_ROOT, (NBTBase)new NBTTagCompound());
        }
        return root.func_74775_l(NBT_ROOT);
    }

    private static int getBootsCD(EntityPlayer p) {
        return SPArmorSetBase.getCDTag(p).func_74762_e(NBT_BOOT_CD);
    }

    private static int getSetCD(EntityPlayer p) {
        return SPArmorSetBase.getCDTag(p).func_74762_e(NBT_SET_CD);
    }

    private static void setBootsCD(EntityPlayer p, int v) {
        NBTTagCompound t = SPArmorSetBase.getCDTag(p);
        t.func_74768_a(NBT_BOOT_CD, v);
        t.func_74757_a(NBT_BOOT_READY, false);
    }

    private static void setSetCD(EntityPlayer p, int v) {
        NBTTagCompound t = SPArmorSetBase.getCDTag(p);
        t.func_74768_a(NBT_SET_CD, v);
        t.func_74757_a(NBT_SET_READY, false);
    }

    private static void bloodImpact(World w, Entity e) {
        if (!(w instanceof WorldServer)) {
            return;
        }
        WorldServer ws = (WorldServer)w;
        double x = e.field_70165_t;
        double y = e.field_70163_u + (double)e.field_70131_O * 0.5;
        double z = e.field_70161_v;
        ws.func_175739_a(EnumParticleTypes.REDSTONE, x, y, z, 0, 0.9, 0.05, 0.05, 1.3, new int[0]);
        ws.func_175739_a(EnumParticleTypes.DAMAGE_INDICATOR, x, y, z, 24, 0.45, 0.35, 0.45, 0.2, new int[0]);
    }

    private static void applyCrashDebuffs(EntityLivingBase ent) {
        ent.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 200, 1, false, true));
        ent.func_70690_d(new PotionEffect(MobEffects.field_76437_t, 200, 1, false, true));
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent e) {
        float mult;
        if (!(e.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer p = (EntityPlayer)e.getEntityLiving();
        String key = SPArmorSetBase.getAnySetKey(p);
        if (!SPArmorSetBase.isHijackedSetKey(key) || !SPArmorSetBase.isWearingFullSet(p, key)) {
            return;
        }
        if (SPArmorSetBase.isFireDamage(e.getSource()) && (mult = (float)Math.max(0.0, SPConfigSystems.hijackedArmorFireMult)) != 1.0f) {
            e.setAmount(e.getAmount() * mult);
        }
    }

    private static boolean isFireDamage(DamageSource src) {
        if (src == null) {
            return false;
        }
        return src.func_76347_k() || src == DamageSource.field_76371_c || src == DamageSource.field_190095_e || "inFire".equals(src.func_76355_l()) || "onFire".equals(src.func_76355_l()) || "lava".equals(src.func_76355_l());
    }

    private static boolean isHijackedSetKey(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        String s = key.toLowerCase(Locale.ROOT);
        return s.startsWith("hijacked_");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        SPArmorSetBase bootsItem;
        Minecraft mc = Minecraft.func_71410_x();
        EntityPlayerSP client = mc.field_71439_g;
        if (SPArmorSetBase.isHijackedSetKey(this.getSetKey())) {
            tooltip.add(TextFormatting.AQUA + I18n.func_135052_a((String)"tooltip.subspaceparasite.hijacked.fullset_bleed", (Object[])new Object[0]));
            tooltip.add(TextFormatting.RED + I18n.func_135052_a((String)"tooltip.subspaceparasite.hijacked.fire_penalty", (Object[])new Object[]{String.format(Locale.ROOT, "%.2f", SPConfigSystems.hijackedArmorFireMult)}));
            tooltip.add(TextFormatting.BLUE + I18n.func_135052_a((String)"tooltip.subspaceparasite.hijacked.boots_save", (Object[])new Object[0]));
        }
        if (client == null) {
            return;
        }
        ItemStack feet = client.func_184582_a(EntityEquipmentSlot.FEET);
        if (!feet.func_190926_b() && feet.func_77973_b() instanceof SPArmorSetBase && SPArmorSetBase.isHijackedSetKey((bootsItem = (SPArmorSetBase)feet.func_77973_b()).getSetKey()) && bootsItem.getSetKey().equals(this.getSetKey())) {
            int bootCd = SPArmorSetBase.getBootsCD((EntityPlayer)client);
            if (bootCd > 0) {
                tooltip.add(TextFormatting.BLUE + I18n.func_135052_a((String)"tooltip.subspaceparasite.hijacked.boots_cd", (Object[])new Object[]{SPArmorSetBase.formatTicks(bootCd)}));
            } else {
                tooltip.add(TextFormatting.BLUE + I18n.func_135052_a((String)"tooltip.subspaceparasite.ready", (Object[])new Object[0]));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private static String formatTicks(int ticks) {
        if (ticks <= 0) {
            return "0:00";
        }
        int s = (ticks + 19) / 20;
        int m = s / 60;
        int ss = s % 60;
        return String.format(Locale.ROOT, "%d:%02d", m, ss);
    }

    @Mod.EventBusSubscriber(modid="subspaceparasite")
    public static class Events {
        @SubscribeEvent
        public static void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
            String key;
            if (!(e.getEntityLiving() instanceof EntityPlayer)) {
                return;
            }
            EntityPlayer p = (EntityPlayer)e.getEntityLiving();
            NBTTagCompound cd = SPArmorSetBase.getCDTag(p);
            int boot = cd.func_74762_e(SPArmorSetBase.NBT_BOOT_CD);
            int set = cd.func_74762_e(SPArmorSetBase.NBT_SET_CD);
            if (boot > 0) {
                cd.func_74768_a(SPArmorSetBase.NBT_BOOT_CD, boot - 1);
            }
            if (set > 0) {
                cd.func_74768_a(SPArmorSetBase.NBT_SET_CD, set - 1);
            }
            boolean hasHijackedBoots = false;
            boolean hasFullHijackedSet = false;
            ItemStack feet = p.func_184582_a(EntityEquipmentSlot.FEET);
            if (!feet.func_190926_b() && feet.func_77973_b() instanceof SPArmorSetBase) {
                SPArmorSetBase bootsItem = (SPArmorSetBase)feet.func_77973_b();
                hasHijackedBoots = SPArmorSetBase.isHijackedSetKey(bootsItem.getSetKey());
            }
            if (SPArmorSetBase.isHijackedSetKey(key = SPArmorSetBase.getAnySetKey(p)) && SPArmorSetBase.isWearingFullSet(p, key)) {
                hasFullHijackedSet = true;
            }
            if (cd.func_74764_b(SPArmorSetBase.NBT_BOOT_CD) && cd.func_74762_e(SPArmorSetBase.NBT_BOOT_CD) == 0 && !cd.func_74767_n(SPArmorSetBase.NBT_BOOT_READY) && hasHijackedBoots) {
                cd.func_74757_a(SPArmorSetBase.NBT_BOOT_READY, true);
                p.field_70170_p.func_184133_a(null, p.func_180425_c(), SoundEvents.field_187839_fV, SoundCategory.PLAYERS, 0.5f, 1.5f);
            }
            if (cd.func_74764_b(SPArmorSetBase.NBT_SET_CD) && cd.func_74762_e(SPArmorSetBase.NBT_SET_CD) == 0 && !cd.func_74767_n(SPArmorSetBase.NBT_SET_READY) && hasFullHijackedSet) {
                cd.func_74757_a(SPArmorSetBase.NBT_SET_READY, true);
                p.field_70170_p.func_184133_a(null, p.func_180425_c(), SoundEvents.field_190021_aL, SoundCategory.PLAYERS, 0.7f, 1.0f);
            }
        }

        @SubscribeEvent
        public static void onLivingFall(LivingFallEvent e) {
            if (!(e.getEntityLiving() instanceof EntityPlayer)) {
                return;
            }
            EntityPlayer p = (EntityPlayer)e.getEntityLiving();
            ItemStack boots = p.func_184582_a(EntityEquipmentSlot.FEET);
            if (boots.func_190926_b() || !(boots.func_77973_b() instanceof SPArmorSetBase)) {
                return;
            }
            SPArmorSetBase bootsItem = (SPArmorSetBase)boots.func_77973_b();
            if (!SPArmorSetBase.isHijackedSetKey(bootsItem.getSetKey())) {
                return;
            }
            if (e.getDistance() < 6.0f) {
                return;
            }
            if (SPArmorSetBase.getBootsCD(p) > 0) {
                return;
            }
            e.setCanceled(true);
            p.field_70143_R = 0.0f;
            SPArmorSetBase.bloodImpact(p.field_70170_p, (Entity)p);
            SPArmorSetBase.applyCrashDebuffs((EntityLivingBase)p);
            SPArmorSetBase.setBootsCD(p, 2400);
        }
    }
}

