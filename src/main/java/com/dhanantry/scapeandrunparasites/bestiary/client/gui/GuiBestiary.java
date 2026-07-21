package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.BestiaryEntry;
import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiBestiary extends GuiScreen {
   private final EntityPlayer player;
   private GuiBestiary.BestiaryPage page = GuiBestiary.BestiaryPage.HOME;
   private ParasiteTier selectedTier = null;
   private final ParasitesPage parasitesPage;
   private boolean isJumbled;
   private BestiaryEntry selectedMob = null;
   private final List<ParasiteTier> visibleTiers = new ArrayList<>();
   private final List<BestiaryEntry> visibleMobs = new ArrayList<>();
   private final Map<String, EntityLivingBase> entityCache = new HashMap<>();
   private float spinDeg = 0.0F;
   private int scrollTiers = 0;
   private int scrollMobs = 0;
   private static final int THUMB = 28;
   private static final int BTN_H = 20;
   private static final int V_PAD = 8;
   private static final int ROW_H = 36;
   private static final int LIST_X = 30;
   private static final int LIST_W = 140;
   private static final int LIST_TOP = 50;
   private static final int UI_THUMB_MARGIN_PX = 6;
   private static final int UI_ICON_MARGIN_PX = 3;
   private static final int UI_DETAIL_MARGIN_PX = 12;
   private static final float UI_THUMB_SHRINK = 0.92F;
   private static final float UI_ICON_SHRINK = 0.92F;
   private static final float UI_DETAIL_SHRINK = 0.88F;
   private static final float UI_SCALE_MIN = 6.0F;
   private static final float UI_SCALE_MAX = 110.0F;

   ParasiteTier getSelectedTier() {
      return this.selectedTier;
   }

   private int LIST_BOTTOM() {
      return this.field_146295_m - 40;
   }

   public GuiBestiary(EntityPlayer player) {
      this.player = player;
      this.parasitesPage = new ParasitesPage(player, this);
   }

   private String distort(String s) {
      return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
   }

   public static List<ParasiteTier> getDisplayOrderLocalized() {
      List<ParasiteTier> list = new ArrayList<>(Arrays.asList(ParasiteTier.values()));
      list.sort(Comparator.comparing(t -> I18n.func_135052_a(tierLangKey(t), new Object[0]).toLowerCase(Locale.ROOT)));
      return list;
   }

   private void applyTierScrollLayout() {
      int contentH = this.visibleTiers.size() * 36;
      int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - 50));
      this.scrollTiers = Math.max(0, Math.min(this.scrollTiers, maxScroll));

      for (GuiButton b : this.field_146292_n) {
         if (b instanceof GuiBestiary.ListButton && b.field_146127_k >= 100 && b.field_146127_k < 200) {
            GuiBestiary.ListButton lb = (GuiBestiary.ListButton)b;
            int rowY = lb.rowTopY - this.scrollTiers;
            b.field_146129_i = rowY + 4;
            boolean inView = rowY + 28 > 50 && rowY < this.LIST_BOTTOM();
            b.field_146125_m = inView;
         }
      }
   }

   private void applyMobScrollLayout() {
      int contentH = this.visibleMobs.size() * 36;
      int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - 50));
      this.scrollMobs = Math.max(0, Math.min(this.scrollMobs, maxScroll));

      for (GuiButton b : this.field_146292_n) {
         if (b instanceof GuiBestiary.ListButton && b.field_146127_k >= 200 && b.field_146127_k < 300) {
            GuiBestiary.ListButton lb = (GuiBestiary.ListButton)b;
            int rowY = lb.rowTopY - this.scrollMobs;
            b.field_146129_i = rowY + 4;
            boolean inView = rowY + 28 > 50 && rowY < this.LIST_BOTTOM();
            b.field_146125_m = inView;
         }
      }
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      int dWheel = Mouse.getEventDWheel();
      if (dWheel != 0) {
         int delta = (int)Math.signum((float)dWheel) * -18;
         switch (this.page) {
            case PARASITES:
               this.scrollTiers += delta * 2;
               this.applyTierScrollLayout();
               break;
            case MOB_LIST:
               this.scrollMobs += delta * 2;
               this.applyMobScrollLayout();
         }
      }
   }

   private static String loreKeyFromMobId(String mobId) {
      try {
         ResourceLocation rl = new ResourceLocation(mobId);
         return "lore." + rl.func_110624_b() + "." + rl.func_110623_a();
      } catch (Exception var3) {
         int i = mobId.indexOf(58);
         return i > 0 && i < mobId.length() - 1 ? "lore." + mobId.substring(0, i) + "." + mobId.substring(i + 1) : "lore." + mobId;
      }
   }

   public static String tierLangKey(ParasiteTier t) {
      String key = t.name().toLowerCase(Locale.ROOT);
      if ("infected".equals(key)) {
         key = "assimilated";
      }

      return "bestiary.tier." + key;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
      this.field_146292_n.clear();
      switch (this.page) {
         case MOB_LIST:
            this.visibleMobs.clear();
            IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
            if (prog != null && this.selectedTier != null) {
               for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
                  if (e.tier == this.selectedTier && isKnown(prog, e)) {
                     this.visibleMobs.add(e);
                  }
               }
            }

            int rowTop = 50;

            for (int i = 0; i < this.visibleMobs.size(); i++) {
               BestiaryEntry ex = this.visibleMobs.get(i);
               String name = this.distort(I18n.func_135052_a(ex.nameKey, new Object[0]));
               this.field_146292_n.add(new GuiBestiary.ListButton(200 + i, 30, rowTop, 140, 20, name));
               rowTop += 36;
            }

            this.applyMobScrollLayout();
            this.field_146292_n.add(new GuiButton(2, 10, 10, 80, 20, this.distort(I18n.func_135052_a("bestiary.nav.tiers", new Object[0]))));
            break;
         case HOME:
            int cx = this.field_146294_l / 2;
            int startY = 75;
            int gap = 24;
            int w = 150;
            int x = cx - w / 2;
            this.field_146292_n.add(new GuiButton(10, x, startY + gap * 0, w, 20, this.distort(I18n.func_135052_a("bestiary.tab.parasites", new Object[0]))));
            this.field_146292_n.add(new GuiButton(11, x, startY + gap * 1, w, 20, this.distort(I18n.func_135052_a("bestiary.tab.blocks", new Object[0]))));
            this.field_146292_n.add(new GuiButton(12, x, startY + gap * 2, w, 20, this.distort(I18n.func_135052_a("bestiary.tab.celestial", new Object[0]))));
            this.field_146292_n.add(new GuiButton(13, x, startY + gap * 3, w, 20, this.distort(I18n.func_135052_a("bestiary.tab.effects", new Object[0]))));
            this.field_146292_n.add(new GuiButton(14, x, startY + gap * 4, w, 20, this.distort(I18n.func_135052_a("bestiary.tab.systems", new Object[0]))));
            this.field_146292_n.add(new GuiButton(15, x, startY + gap * 5, w, 20, this.distort(I18n.func_135052_a("bestiary.tab.stats", new Object[0]))));
            break;
         case MOB_DETAIL:
            this.field_146292_n.add(new GuiButton(3, 10, 10, 100, 20, I18n.func_135052_a("bestiary.nav.mob_list", new Object[0])));
      }
   }

   private void drawPanel(int x, int y, int w, int h) {
      func_73734_a(x - 1, y - 1, x + w + 1, y + h + 1, -1442840576);
      func_73734_a(x, y, x + w, y + h, -2013265920);
   }

   private void drawHeaderBar(int x, int y, int w, int h) {
      func_73734_a(x, y, x + w, y + h, -1441722095);
      func_73734_a(x, y + h - 1, x + w, y + h, -1442840576);
   }

   private static boolean isKnown(IBestiaryProgress prog, BestiaryEntry e) {
      if (prog == null || e == null) {
         return false;
      } else {
         return prog.getKills(e.mobId) > 0 ? true : prog.isMobSeen(e.mobId);
      }
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      if (button.field_146127_k == 1) {
         this.page = GuiBestiary.BestiaryPage.HOME;
         this.selectedTier = null;
         this.selectedMob = null;
         this.func_73866_w_();
      } else {
         switch (this.page) {
            case HOME:
               if (button.field_146127_k == 10) {
                  this.parasitesPage.openParasitesRoot();
                  this.field_146297_k.func_147108_a(this.parasitesPage);
                  return;
               } else if (button.field_146127_k == 11) {
                  this.field_146297_k.func_147108_a(new BlocksPage(this.player, this));
                  return;
               } else if (button.field_146127_k == 12) {
                  this.field_146297_k.func_147108_a(new CelestialEventsPage(this.player, this));
                  return;
               } else if (button.field_146127_k == 13) {
                  this.field_146297_k.func_147108_a(new StatusEffectsPage(this.player, this));
                  return;
               } else if (button.field_146127_k == 14) {
                  this.field_146297_k.func_147108_a(new SystemsPage(this.player, this));
                  return;
               } else if (button.field_146127_k == 15) {
                  this.field_146297_k.func_147108_a(new StatsPage(this.player, this));
                  return;
               }
         }
      }
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      String title = this.distort(I18n.func_135052_a("lore.srparasites.compendium", new Object[0]));
      int titleW = this.field_146289_q.func_78256_a(title);
      this.func_73731_b(this.field_146289_q, title, this.field_146294_l - titleW - 10, 12, 16777215);
      this.spinDeg += partialTicks * 1.5F;
      switch (this.page) {
         case PARASITES:
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.parasite_tiers", new Object[0])), 20, 50, 16777215);
            if (this.selectedTier != null) {
               this.drawTierPreview(this.selectedTier, 180, 70);
            }
            break;
         case MOB_LIST:
            int leftX = 22;
            int leftY = 36;
            int leftW = 156;
            int leftH = this.LIST_BOTTOM() - 50 + 22;
            this.drawPanel(leftX, leftY, leftW, leftH);
            this.drawHeaderBar(leftX, leftY, leftW, 16);
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.mob_list", new Object[0])), leftX + 6, leftY + 4, 13421772);
            int rightX = 182;
            int rightY = 36;
            int rightW = this.field_146294_l - rightX - 12;
            this.drawPanel(rightX, rightY, rightW, leftH);
            this.drawHeaderBar(rightX, rightY, rightW, 16);
            break;
         case HOME:
            int cardW = 170;
            int cardH = 166;
            int cardX = (this.field_146294_l - cardW) / 2;
            int cardY = 55;
            this.drawPanel(cardX, cardY, cardW, cardH);
            this.drawHeaderBar(cardX, cardY, cardW, 16);
            this.func_73732_a(
               this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.select_category", new Object[0])), this.field_146294_l / 2, cardY + 4, 11184810
            );
            break;
         case MOB_DETAIL:
            if (this.selectedMob != null) {
               this.drawMobDetail(this.selectedMob, 20, 40);
            }
      }

      super.func_73863_a(mouseX, mouseY, partialTicks);
   }

   public void drawTierPreview(ParasiteTier tier, int x, int y) {
      IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
      if (prog != null) {
         int shown = 0;

         for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
            if (e.tier == tier && isKnown(prog, e)) {
               String name = this.distort(I18n.func_135052_a(e.nameKey, new Object[0]));
               this.field_146289_q.func_78276_b(name, x + 30, y + 4, 14540253);
               float scale = SRPBestiaryRegistry.getRenderScale(e.mobId);
               this.renderEntityPreview(e.mobId, x + 14, y + 14, 28, 28, this.spinDeg, 3, 0.92F);
               y += 32;
               if (++shown >= 5) {
                  break;
               }
            }
         }

         if (shown == 0) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a("bestiary.unlock_by_kill", new Object[0])), x, y, 7829367);
         }
      }
   }

   private void drawMobListWithRenders(List<BestiaryEntry> mobs, int xRight) {
      IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
      if (prog != null) {
         int rowY = 50 - this.scrollMobs;

         for (BestiaryEntry e : mobs) {
            int rowBot = rowY + 28;
            if (rowBot > 50 && rowY < this.LIST_BOTTOM()) {
               int centerY = rowY + 14;
               this.renderEntityPreview(e.mobId, xRight + 14, centerY, 28, 28, this.spinDeg, 6, 0.92F);
               int textY = centerY - this.field_146289_q.field_78288_b / 2;
               int kills = prog.getKills(e.mobId);
               String rowPlain = I18n.func_135052_a(e.nameKey, new Object[0])
                  + " ("
                  + I18n.func_135052_a("bestiary.kills_label", new Object[0])
                  + ": "
                  + kills
                  + ")";
               String rowDraw = this.isJumbled ? GuiDistortionHelper.jamText(rowPlain) : rowPlain;
               this.field_146289_q.func_78276_b(rowDraw, xRight + 28 + 8, textY, 13421772);
            }

            rowY += 36;
         }

         if (mobs.isEmpty()) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a("bestiary.no_mobs_unlocked_in_tier", new Object[0])), xRight, 50, 7829367);
         }
      }
   }

   private void drawMobDetail(BestiaryEntry e, int x, int y) {
      IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
      if (prog == null) {
         this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a("bestiary.cap_missing_client", new Object[0])), x, y, 16733525);
      } else {
         int kills = prog.getKills(e.mobId);
         String nameLinePlain = I18n.func_135052_a(e.nameKey, new Object[0]) + " (" + I18n.func_135052_a("bestiary.kills", new Object[0]) + ": " + kills + ")";
         String nameLine = this.isJumbled ? GuiDistortionHelper.jamText(nameLinePlain) : nameLinePlain;
         this.field_146289_q.func_78276_b(nameLine, x, y, 16777215);
         y += 6;
         int boxW = 160;
         int boxH = 120;
         int cx = x + 80;
         int cy = y + 10 + 60;
         this.renderEntityPreview(e.mobId, cx, cy, 160, 120, this.spinDeg, 12, 0.88F);
         int infoX = x + 160 + 14;
         int infoY = y + 4;
         if (kills >= 3) {
            double hp = e.baseHp > 0 ? e.baseHp : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111267_a);
            double dmg = e.baseDamage > 0.0F ? e.baseDamage : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111264_e);
            String statsLine = this.distort(I18n.func_135052_a("bestiary.stats", new Object[]{String.valueOf((int)Math.round(hp)), String.valueOf((float)dmg)}));
            this.field_146289_q.func_78276_b(statsLine, infoX, infoY, 11184810);
            infoY += 12;
         } else {
            String gate3 = this.distort(I18n.func_135052_a("bestiary.more_info_at_n", new Object[]{3}));
            this.field_146289_q.func_78276_b(gate3, infoX, infoY, 6710886);
            infoY += 12;
         }

         y = y + 10 + 120 + 10;
         if (kills >= 10) {
            String lore = I18n.func_135052_a(loreKeyFromMobId(e.mobId), new Object[0]);
            this.field_146289_q.func_78279_b(lore, x, y + 6, this.field_146294_l - x - 20, 13421772);
         } else {
            String gate10 = this.distort(I18n.func_135052_a("bestiary.lore_unlocks_at_n", new Object[]{10}));
            this.field_146289_q.func_78276_b(gate10, x, y + 6, 6710886);
         }
      }
   }

   private double readEntityStat(String mobId, IAttribute attr) {
      Entity ent = EntityList.func_188429_b(new ResourceLocation(mobId), this.player.field_70170_p);
      if (!(ent instanceof EntityLivingBase)) {
         return -1.0;
      } else {
         EntityLivingBase living = (EntityLivingBase)ent;
         IAttributeInstance inst = living.func_110148_a(attr);
         return inst != null ? inst.func_111125_b() : -1.0;
      }
   }

   private float computeAutoScale(EntityLivingBase ent, int boxW, int boxH) {
      float padW = Math.max(0, boxW - 4);
      float padH = Math.max(0, boxH - 4);
      float w = Math.max(0.6F, ent.field_70130_N);
      float h = Math.max(0.6F, ent.field_70131_O);
      float scaleW = padW / w;
      float scaleH = padH / h;
      float scale = Math.min(scaleW, scaleH);
      return Math.max(8.0F, Math.min(scale, 120.0F));
   }

   private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, int marginPx, float extraShrink) {
      EntityLivingBase ent = this.entityCache.computeIfAbsent(mobId, id -> {
         Entity created = EntityList.func_188429_b(new ResourceLocation(id), this.player.field_70170_p);
         return created instanceof EntityLivingBase ? (EntityLivingBase)created : null;
      });
      if (ent != null) {
         float oYawOff = ent.field_70761_aq;
         float oYawHead = ent.field_70759_as;
         float oYaw = ent.field_70177_z;
         float oPitch = ent.field_70125_A;
         float ppYawOff = ent.field_70760_ar;
         float ppYawHead = ent.field_70758_at;
         float ppYaw = ent.field_70126_B;
         float ppPitch = ent.field_70127_C;
         float oLimb = ent.field_184619_aG;
         float oLimbAmt = ent.field_70721_aZ;
         float ppLimbAmt = ent.field_184618_aE;
         int oTicks = ent.field_70173_aa;
         ent.field_70173_aa = 0;
         ent.field_184619_aG = ent.field_70721_aZ = ent.field_184618_aE = 0.0F;
         ent.field_70125_A = ent.field_70127_C = 0.0F;
         ent.field_70761_aq = ent.field_70760_ar = yawDeg;
         ent.field_70759_as = ent.field_70758_at = yawDeg;
         ent.field_70177_z = ent.field_70126_B = yawDeg;
         int innerW = Math.max(0, boxW - (marginPx << 1));
         int innerH = Math.max(0, boxH - (marginPx << 1));
         float scale = this.computeAutoScale(ent, innerW, innerH);
         scale *= SRPBestiaryRegistry.getRenderScale(mobId);
         if (extraShrink > 0.0F) {
            scale *= extraShrink;
         }

         scale = Math.max(6.0F, Math.min(scale, 110.0F));
         GlStateManager.func_179094_E();

         try {
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179126_j();
            GlStateManager.func_179140_f();
            RenderHelper.func_74519_b();
            GlStateManager.func_179109_b(cx, cy, 50.0F);
            GlStateManager.func_179152_a(-scale, scale, scale);
            GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
            RenderManager rm = this.field_146297_k.func_175598_ae();
            boolean prevShadow = rm.func_178627_a();
            float prevViewY = rm.field_78735_i;
            rm.func_178631_a(180.0F);
            rm.func_178633_a(false);
            rm.func_188391_a(ent, 0.0, 0.0, 0.0, 0.0F, this.field_146297_k.func_184121_ak(), true);
            rm.func_178633_a(prevShadow);
            rm.func_178631_a(prevViewY);
            RenderHelper.func_74518_a();
            GlStateManager.func_179101_C();
            GlStateManager.func_179097_i();
            GlStateManager.func_179140_f();
            GlStateManager.func_179084_k();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179098_w();
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
         } finally {
            GlStateManager.func_179121_F();
            ent.field_70761_aq = oYawOff;
            ent.field_70760_ar = ppYawOff;
            ent.field_70759_as = oYawHead;
            ent.field_70758_at = ppYawHead;
            ent.field_70177_z = oYaw;
            ent.field_70126_B = ppYaw;
            ent.field_70125_A = oPitch;
            ent.field_70127_C = ppPitch;
            ent.field_184619_aG = oLimb;
            ent.field_70721_aZ = oLimbAmt;
            ent.field_184618_aE = ppLimbAmt;
            ent.field_70173_aa = oTicks;
         }
      }
   }

   public boolean func_73868_f() {
      return false;
   }

   private static enum BestiaryPage {
      HOME,
      PARASITES,
      MOB_LIST,
      MOB_DETAIL,
      STATS;
   }

   private static class ListButton extends GuiButton {
      final int rowTopY;

      ListButton(int id, int x, int rowTopY, int w, int h, String txt) {
         super(id, x, rowTopY, w, h, txt);
         this.rowTopY = rowTopY;
      }

      public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         if (this.field_146125_m) {
            boolean hover = mouseX >= this.field_146128_h
               && mouseY >= this.field_146129_i
               && mouseX < this.field_146128_h + this.field_146120_f
               && mouseY < this.field_146129_i + this.field_146121_g;
            int bg = hover ? 1713512994 : 1141969169;
            func_73734_a(
               this.field_146128_h - 2,
               this.field_146129_i - 2,
               this.field_146128_h + this.field_146120_f + 2,
               this.field_146129_i + this.field_146121_g + 2,
               -1442840576
            );
            func_73734_a(
               this.field_146128_h - 1,
               this.field_146129_i - 1,
               this.field_146128_h + this.field_146120_f + 1,
               this.field_146129_i + this.field_146121_g + 1,
               bg
            );
            int color = hover ? 16777215 : 14540253;
            this.func_73731_b(mc.field_71466_p, this.field_146126_j, this.field_146128_h + 6, this.field_146129_i + 6, color);
         }
      }
   }

   public static class TierButton extends GuiButton {
      final ParasiteTier tier;
      final ItemStack icon;
      public int rowTopY;

      TierButton(int id, int x, int y, int w, int h, String text, ParasiteTier tier, ItemStack icon) {
         super(id, x, y, w, h, text);
         this.rowTopY = y;
         this.tier = tier;
         this.icon = icon == null ? ItemStack.field_190927_a : icon;
      }

      public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         super.func_191745_a(mc, mouseX, mouseY, partialTicks);
         if (this.field_146125_m) {
            int iconX = this.field_146128_h + 4;
            int iconY = this.field_146129_i + (this.field_146121_g - 16) / 2;
            RenderHelper.func_74520_c();
            mc.func_175599_af().func_180450_b(this.icon, iconX, iconY);
            RenderHelper.func_74518_a();
         }
      }
   }
}
