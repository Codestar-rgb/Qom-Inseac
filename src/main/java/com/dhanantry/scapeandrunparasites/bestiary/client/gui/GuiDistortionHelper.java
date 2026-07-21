package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;

public final class GuiDistortionHelper {
   public static final String PLAYER_DISABLE_TAG = "SRPGuiDistortionDisabled";
   public static final String PLAYER_CREATIVE_OVERRIDE_TAG = "SRPGuiDistortionCreativeOverride";
   public static boolean clientDistortionDisabled = false;
   public static boolean clientCreativeOverride = false;
   public static boolean itemHighlightHookAvailable = true;
   public static boolean subtitleHookAvailable = true;
   public static boolean chatHookAvailable = true;
   public static boolean signHookAvailable = true;

   private GuiDistortionHelper() {
   }

   private static Set<String> getConfiguredMobIds() {
      Set<String> ids = new HashSet<>();
      if (SRPConfigSystems.guiDistortionMobList != null && SRPConfigSystems.guiDistortionMobList.length != 0) {
         for (String s : SRPConfigSystems.guiDistortionMobList) {
            if (s != null) {
               s = s.trim();
               if (!s.isEmpty()) {
                  ids.add(s.toLowerCase(Locale.ROOT));
               }
            }
         }

         if (ids.isEmpty()) {
            ids.add("srparasites:kirin");
            ids.add("srparasites:draconite");
         }

         return ids;
      } else {
         ids.add("srparasites:kirin");
         ids.add("srparasites:draconite");
         return ids;
      }
   }

   public static boolean isDistortionActive(Minecraft mc) {
      if (!SRPConfigSystems.guiDistortionEnabled) {
         return false;
      } else if (mc != null && mc.field_71441_e != null && mc.field_71439_g != null) {
         if (clientDistortionDisabled) {
            return false;
         } else if (isCreativeOrSpectator(mc) && !clientCreativeOverride) {
            return false;
         } else if (isProtectedFromDistortion(mc)) {
            return false;
         } else {
            int range = SRPConfigSystems.guiDistortionRange > 0 ? SRPConfigSystems.guiDistortionRange : 100;
            Set<String> validIds = getConfiguredMobIds();
            if (validIds.isEmpty()) {
               return false;
            } else {
               double maxDistSq = (double)range * range;

               for (Entity ent : mc.field_71441_e.field_72996_f) {
                  if (ent != null) {
                     ResourceLocation key = EntityList.func_191301_a(ent);
                     if (key != null && validIds.contains(key.toString().toLowerCase(Locale.ROOT)) && mc.field_71439_g.func_70068_e(ent) <= maxDistSq) {
                        return true;
                     }
                  }
               }

               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static boolean isCreativeOrSpectator(Minecraft mc) {
      if (mc == null || mc.field_71439_g == null) {
         return false;
      } else if (mc.field_71439_g.func_175149_v()) {
         return true;
      } else if (mc.field_71439_g.field_71075_bZ != null && mc.field_71439_g.field_71075_bZ.field_75098_d) {
         return true;
      } else if (mc.field_71442_b == null) {
         return false;
      } else {
         GameType type = mc.field_71442_b.func_178889_l();
         return type == GameType.CREATIVE || type == GameType.SPECTATOR;
      }
   }

   public static String jamText(String s) {
      if (s != null && !s.isEmpty()) {
         String clean = TextFormatting.func_110646_a(s);
         StringBuilder out = new StringBuilder();

         for (int i = 0; i < clean.length(); i++) {
            char c = clean.charAt(i);
            if (Character.isWhitespace(c)) {
               out.append(c);
            } else {
               out.append("§k").append(c).append("§r").append("§8");
            }
         }

         return "§8" + out.toString();
      } else {
         return s;
      }
   }

   public static String jamTextIfNeeded(String s, boolean jammed) {
      return jammed ? jamText(s) : s;
   }

   private static boolean isProtectedFromDistortion(Minecraft mc) {
      return mc != null && mc.field_71439_g != null && mc.field_71439_g.func_70644_a(SRPPotions.THE_SIGN_E);
   }

   public static int getJammedValue(int seedBase) {
      long t = Minecraft.func_71386_F() / 120L;
      int a = Math.abs((int)((t * 37L + seedBase * 131L) % 997L));
      int b = Math.abs((int)((t * 19L + seedBase * 73L) % 211L));
      return a + b + 1;
   }

   public static int getDisplayValue(int realValue, boolean jammed, int seedBase) {
      return jammed ? getJammedValue(seedBase) : realValue;
   }

   public static float getDisplayRatio(int realValue, int realMax, boolean jammed, int seedBase) {
      if (jammed) {
         int fakeValue = Math.max(1, getJammedValue(seedBase));
         int fakeMax = Math.max(fakeValue, getJammedValue(seedBase + 999));
         return (float)fakeValue / fakeMax;
      } else {
         return realMax <= 0 ? 0.0F : (float)realValue / realMax;
      }
   }

   public static boolean isExtendedWorldTextDistortionActive(Minecraft mc) {
      return !SRPConfigSystems.guiDistortionAffectsWorldText ? false : isDistortionActive(mc);
   }

   public static boolean shouldDistortChat(Minecraft mc) {
      if (!chatHookAvailable) {
         return false;
      } else {
         return !SRPConfigSystems.guiDistortionAffectsChat ? false : isDistortionActive(mc);
      }
   }

   public static boolean shouldDistortItemTooltips(Minecraft mc) {
      return !SRPConfigSystems.guiDistortionAffectsItemTooltips ? false : isDistortionActive(mc);
   }

   public static boolean shouldDistortPotionHud(Minecraft mc) {
      return !SRPConfigSystems.guiDistortionAffectsPotionHud ? false : isDistortionActive(mc);
   }

   public static boolean shouldDistortSigns(Minecraft mc) {
      if (!signHookAvailable) {
         return false;
      } else {
         return !SRPConfigSystems.guiDistortionAffectsSigns ? false : isDistortionActive(mc);
      }
   }

   public static boolean shouldDistortItemHighlight(Minecraft mc) {
      if (!itemHighlightHookAvailable) {
         return false;
      } else {
         return !SRPConfigSystems.guiDistortionAffectsItemHighlight ? false : isDistortionActive(mc);
      }
   }

   public static boolean shouldDistortSubtitles(Minecraft mc) {
      if (!subtitleHookAvailable) {
         return false;
      } else {
         return !SRPConfigSystems.guiDistortionAffectsSubtitles ? false : isDistortionActive(mc);
      }
   }

   public static String toneDownTextColors(String s) {
      return s == null ? "" : s.replace("§e", "§8").replace("§6", "§8").replace("§7", "§8");
   }
}
