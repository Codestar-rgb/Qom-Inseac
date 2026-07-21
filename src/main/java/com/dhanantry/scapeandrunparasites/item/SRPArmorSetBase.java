package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
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
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SRPArmorSetBase extends ItemArmor {
   public static final int BOOTS_SAVE_COOLDOWN_TICKS = 2400;
   public static final int DEBUFF_TICKS = 200;
   public static final int BOOTS_MIN_FALL_DIST = 6;
   private static final String NBT_ROOT = "SRPArmorCD";
   private static final String NBT_BOOT_CD = "bootsCD";
   private static final String NBT_SET_CD = "setCD";
   private static final String NBT_BOOT_READY = "bootsReadyPlayed";
   private static final String NBT_SET_READY = "setReadyPlayed";
   private final String setKey;
   public static final ArmorMaterial HIJACKED_MAT = EnumHelper.addArmorMaterial(
      "HIJACKED", "srparasites:hijacked_iron", 40, new int[]{4, 7, 9, 4}, 15, SRPSounds.FLESH_GROW, 3.0F
   );

   public SRPArmorSetBase(String registryName, ArmorMaterial material, EntityEquipmentSlot slot, String setKey) {
      super(material, 1, slot);
      this.setKey = setKey;
      this.setRegistryName(new ResourceLocation("srparasites", registryName));
      this.func_77655_b("srparasites." + registryName);
      this.func_77637_a(CreativeTabs.field_78037_j);
   }

   public SRPArmorSetBase(String registryName) {
      this(registryName, inferMaterial(registryName), inferSlot(registryName), inferSetKey(registryName));
   }

   public String getSetKey() {
      return this.setKey;
   }

   public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
      if (!world.field_72995_K) {
         if (isWearingFullSet(player, this.setKey) && player.func_70644_a(SRPPotions.BLEED_E)) {
            player.func_184596_c(SRPPotions.BLEED_E);
         }
      }
   }

   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      String layer = slot == EntityEquipmentSlot.LEGS ? "2" : "1";
      return "srparasites:textures/models/armor/" + this.setKey + "_layer_" + layer + ".png";
   }

   public static boolean isWearingFullSet(EntityPlayer player, String setKey) {
      if (player != null && setKey != null && !setKey.isEmpty()) {
         NonNullList<ItemStack> armor = player.field_71071_by.field_70460_b;
         if (armor.size() < 4) {
            return false;
         } else {
            for (ItemStack st : armor) {
               if (st.func_190926_b() || !(st.func_77973_b() instanceof SRPArmorSetBase)) {
                  return false;
               }

               if (!((SRPArmorSetBase)st.func_77973_b()).setKey.equals(setKey)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static String getAnySetKey(EntityPlayer p) {
      if (p == null) {
         return "";
      } else {
         ItemStack head = p.func_184582_a(EntityEquipmentSlot.HEAD);
         if (!head.func_190926_b() && head.func_77973_b() instanceof SRPArmorSetBase) {
            return ((SRPArmorSetBase)head.func_77973_b()).getSetKey();
         } else {
            ItemStack chest = p.func_184582_a(EntityEquipmentSlot.CHEST);
            if (!chest.func_190926_b() && chest.func_77973_b() instanceof SRPArmorSetBase) {
               return ((SRPArmorSetBase)chest.func_77973_b()).getSetKey();
            } else {
               ItemStack legs = p.func_184582_a(EntityEquipmentSlot.LEGS);
               if (!legs.func_190926_b() && legs.func_77973_b() instanceof SRPArmorSetBase) {
                  return ((SRPArmorSetBase)legs.func_77973_b()).getSetKey();
               } else {
                  ItemStack feet = p.func_184582_a(EntityEquipmentSlot.FEET);
                  return !feet.func_190926_b() && feet.func_77973_b() instanceof SRPArmorSetBase ? ((SRPArmorSetBase)feet.func_77973_b()).getSetKey() : "";
               }
            }
         }
      }
   }

   private static ArmorMaterial inferMaterial(String regName) {
      String s = regName.toLowerCase(Locale.ROOT);
      if (s.startsWith("hijacked_")) {
         return HIJACKED_MAT;
      } else if (s.contains("leather")) {
         return ArmorMaterial.LEATHER;
      } else if (s.contains("chain")) {
         return ArmorMaterial.CHAIN;
      } else if (s.contains("gold")) {
         return ArmorMaterial.GOLD;
      } else {
         return s.contains("diamond") ? ArmorMaterial.DIAMOND : ArmorMaterial.IRON;
      }
   }

   private static EntityEquipmentSlot inferSlot(String regName) {
      String s = regName.toLowerCase(Locale.ROOT);
      if (s.endsWith("_helmet") || s.endsWith("_helm")) {
         return EntityEquipmentSlot.HEAD;
      } else if (s.endsWith("_chestpiece") || s.endsWith("_chestplate") || s.endsWith("_chest")) {
         return EntityEquipmentSlot.CHEST;
      } else if (s.endsWith("_leggings") || s.endsWith("_legs")) {
         return EntityEquipmentSlot.LEGS;
      } else if (s.endsWith("_boots")) {
         return EntityEquipmentSlot.FEET;
      } else {
         throw new IllegalArgumentException("Cannot infer armor slot from registry name: " + regName);
      }
   }

   private static String inferSetKey(String regName) {
      int i = regName.lastIndexOf(95);
      return i > 0 ? regName.substring(0, i) : regName;
   }

   private static NBTTagCompound getCDTag(EntityPlayer p) {
      NBTTagCompound root = p.getEntityData();
      if (!root.func_74764_b("SRPArmorCD")) {
         root.func_74782_a("SRPArmorCD", new NBTTagCompound());
      }

      return root.func_74775_l("SRPArmorCD");
   }

   private static int getBootsCD(EntityPlayer p) {
      return getCDTag(p).func_74762_e("bootsCD");
   }

   private static int getSetCD(EntityPlayer p) {
      return getCDTag(p).func_74762_e("setCD");
   }

   private static void setBootsCD(EntityPlayer p, int v) {
      NBTTagCompound t = getCDTag(p);
      t.func_74768_a("bootsCD", v);
      t.func_74757_a("bootsReadyPlayed", false);
   }

   private static void setSetCD(EntityPlayer p, int v) {
      NBTTagCompound t = getCDTag(p);
      t.func_74768_a("setCD", v);
      t.func_74757_a("setReadyPlayed", false);
   }

   private static void bloodImpact(World w, Entity e) {
      if (w instanceof WorldServer) {
         WorldServer ws = (WorldServer)w;
         double x = e.field_70165_t;
         double y = e.field_70163_u + e.field_70131_O * 0.5;
         double z = e.field_70161_v;
         ws.func_175739_a(EnumParticleTypes.REDSTONE, x, y, z, 0, 0.9, 0.05, 0.05, 1.3, new int[0]);
         ws.func_175739_a(EnumParticleTypes.DAMAGE_INDICATOR, x, y, z, 24, 0.45, 0.35, 0.45, 0.2, new int[0]);
      }
   }

   private static void applyCrashDebuffs(EntityLivingBase ent) {
      ent.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 200, 1, false, true));
      ent.func_70690_d(new PotionEffect(MobEffects.field_76437_t, 200, 1, false, true));
   }

   @SubscribeEvent
   public static void onLivingHurt(LivingHurtEvent e) {
      if (e.getEntityLiving() instanceof EntityPlayer) {
         EntityPlayer p = (EntityPlayer)e.getEntityLiving();
         String key = getAnySetKey(p);
         if (isHijackedSetKey(key) && isWearingFullSet(p, key)) {
            if (isFireDamage(e.getSource())) {
               float mult = (float)Math.max(0.0, SRPConfigSystems.hijackedArmorFireMult);
               if (mult != 1.0F) {
                  e.setAmount(e.getAmount() * mult);
               }
            }
         }
      }
   }

   private static boolean isFireDamage(DamageSource src) {
      return src == null
         ? false
         : src.func_76347_k()
            || src == DamageSource.field_76371_c
            || src == DamageSource.field_190095_e
            || "inFire".equals(src.func_76355_l())
            || "onFire".equals(src.func_76355_l())
            || "lava".equals(src.func_76355_l());
   }

   private static boolean isHijackedSetKey(String key) {
      if (key != null && !key.isEmpty()) {
         String s = key.toLowerCase(Locale.ROOT);
         return s.startsWith("hijacked_");
      } else {
         return false;
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      Minecraft mc = Minecraft.func_71410_x();
      EntityPlayer client = mc.field_71439_g;
      if (isHijackedSetKey(this.getSetKey())) {
         tooltip.add(TextFormatting.AQUA + I18n.func_135052_a("tooltip.srparasites.hijacked.fullset_bleed", new Object[0]));
         tooltip.add(
            TextFormatting.RED
               + I18n.func_135052_a(
                  "tooltip.srparasites.hijacked.fire_penalty", new Object[]{String.format(Locale.ROOT, "%.2f", SRPConfigSystems.hijackedArmorFireMult)}
               )
         );
         tooltip.add(TextFormatting.BLUE + I18n.func_135052_a("tooltip.srparasites.hijacked.boots_save", new Object[0]));
      }

      if (client != null) {
         ItemStack feet = client.func_184582_a(EntityEquipmentSlot.FEET);
         if (!feet.func_190926_b() && feet.func_77973_b() instanceof SRPArmorSetBase) {
            SRPArmorSetBase bootsItem = (SRPArmorSetBase)feet.func_77973_b();
            if (isHijackedSetKey(bootsItem.getSetKey()) && bootsItem.getSetKey().equals(this.getSetKey())) {
               int bootCd = getBootsCD(client);
               if (bootCd > 0) {
                  tooltip.add(TextFormatting.BLUE + I18n.func_135052_a("tooltip.srparasites.hijacked.boots_cd", new Object[]{formatTicks(bootCd)}));
               } else {
                  tooltip.add(TextFormatting.BLUE + I18n.func_135052_a("tooltip.srparasites.ready", new Object[0]));
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   private static String formatTicks(int ticks) {
      if (ticks <= 0) {
         return "0:00";
      } else {
         int s = (ticks + 19) / 20;
         int m = s / 60;
         int ss = s % 60;
         return String.format(Locale.ROOT, "%d:%02d", m, ss);
      }
   }

   @EventBusSubscriber(modid = "srparasites")
   public static class Events {
      @SubscribeEvent
      public static void onLivingUpdate(LivingUpdateEvent e) {
         if (e.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)e.getEntityLiving();
            NBTTagCompound cd = SRPArmorSetBase.getCDTag(p);
            int boot = cd.func_74762_e("bootsCD");
            int set = cd.func_74762_e("setCD");
            if (boot > 0) {
               cd.func_74768_a("bootsCD", boot - 1);
            }

            if (set > 0) {
               cd.func_74768_a("setCD", set - 1);
            }

            boolean hasHijackedBoots = false;
            boolean hasFullHijackedSet = false;
            ItemStack feet = p.func_184582_a(EntityEquipmentSlot.FEET);
            if (!feet.func_190926_b() && feet.func_77973_b() instanceof SRPArmorSetBase) {
               SRPArmorSetBase bootsItem = (SRPArmorSetBase)feet.func_77973_b();
               hasHijackedBoots = SRPArmorSetBase.isHijackedSetKey(bootsItem.getSetKey());
            }

            String key = SRPArmorSetBase.getAnySetKey(p);
            if (SRPArmorSetBase.isHijackedSetKey(key) && SRPArmorSetBase.isWearingFullSet(p, key)) {
               hasFullHijackedSet = true;
            }

            if (cd.func_74764_b("bootsCD") && cd.func_74762_e("bootsCD") == 0 && !cd.func_74767_n("bootsReadyPlayed") && hasHijackedBoots) {
               cd.func_74757_a("bootsReadyPlayed", true);
               p.field_70170_p.func_184133_a(null, p.func_180425_c(), SoundEvents.field_187839_fV, SoundCategory.PLAYERS, 0.5F, 1.5F);
            }

            if (cd.func_74764_b("setCD") && cd.func_74762_e("setCD") == 0 && !cd.func_74767_n("setReadyPlayed") && hasFullHijackedSet) {
               cd.func_74757_a("setReadyPlayed", true);
               p.field_70170_p.func_184133_a(null, p.func_180425_c(), SoundEvents.field_190021_aL, SoundCategory.PLAYERS, 0.7F, 1.0F);
            }
         }
      }

      @SubscribeEvent
      public static void onLivingFall(LivingFallEvent e) {
         if (e.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)e.getEntityLiving();
            ItemStack boots = p.func_184582_a(EntityEquipmentSlot.FEET);
            if (!boots.func_190926_b() && boots.func_77973_b() instanceof SRPArmorSetBase) {
               SRPArmorSetBase bootsItem = (SRPArmorSetBase)boots.func_77973_b();
               if (SRPArmorSetBase.isHijackedSetKey(bootsItem.getSetKey())) {
                  if (!(e.getDistance() < 6.0F)) {
                     if (SRPArmorSetBase.getBootsCD(p) <= 0) {
                        e.setCanceled(true);
                        p.field_70143_R = 0.0F;
                        SRPArmorSetBase.bloodImpact(p.field_70170_p, p);
                        SRPArmorSetBase.applyCrashDebuffs(p);
                        SRPArmorSetBase.setBootsCD(p, 2400);
                     }
                  }
               }
            }
         }
      }
   }
}
