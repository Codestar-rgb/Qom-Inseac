package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.BestiaryEntry;
import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiaryRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ParasitesPage extends GuiScreen {
   private static final ResourceLocation TEX_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/background.png");
   private static final ResourceLocation TEX_LABEL_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/label_background.png");
   private static final ResourceLocation TEX_MODEL_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/model_background.png");
   private static final ResourceLocation TEX_STATS_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/stats_background.png");
   private static final ResourceLocation TEX_NAME_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/name_background.png");
   private static final ResourceLocation TEX_LORE_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/lore_background.png");
   private static final ResourceLocation TEX_DROP_BG = new ResourceLocation("srparasites", "textures/gui/bestiary/drop_background.png");
   private static final ResourceLocation TEX_WARNING_SHEET = new ResourceLocation("srparasites", "textures/gui/bestiary/warning_sheet.png");
   private static final int LIST_HEADER_H = 18;
   private boolean isJumbled;
   private final EntityPlayer player;
   private ParasitesPage.BestiaryPage page = ParasitesPage.BestiaryPage.HOME;
   private ParasiteTier selectedTier = null;
   private BestiaryEntry selectedMob = null;
   private boolean suppressPoseFieldUpdates = false;
   private final List<ParasiteTier> visibleTiers = new ArrayList<>();
   private final List<BestiaryEntry> visibleMobs = new ArrayList<>();
   private final Map<String, EntityLivingBase> entityCache = new HashMap<>();
   private float spinDeg = 0.0F;
   private int scrollTiers = 0;
   private int scrollMobs = 0;
   private boolean listScrollDrag = false;
   private boolean listScrollIsTiers = false;
   private int listScrollDragStartMouseY = 0;
   private int listScrollDragStartScrollPx = 0;
   private int listScrollTrackX = 0;
   private int listScrollTrackY = 0;
   private int listScrollTrackW = 0;
   private int listScrollTrackH = 0;
   private int listScrollThumbY = 0;
   private int listScrollThumbH = 0;
   private static final int BTN_RUN = 1003;
   private boolean modelRun = false;
   private boolean renderRunThisCall = false;
   private boolean syncRequested = false;
   private static final boolean DEBUG_SYNC = false;
   private static final int THUMB = 28;
   private static final int BTN_H = 20;
   private static final int V_PAD = 8;
   private static final int ROW_H = 36;
   private static final int LIST_X = 30;
   private static final int LIST_W = 140;
   private static final int LIST_TOP = 50;
   private static final int UI_ICON_MARGIN_PX = 3;
   private static final float UI_ICON_SHRINK = 0.92F;
   private static final int UI_DARK_GRAY = 3026478;
   private static final int BTN_HOME = 1000;
   private static final int BTN_TIERS = 1001;
   private static final int BTN_MOBLIST = 1002;
   private static final int BTN_LORE = 1012;
   private boolean lorePopupOpen = false;
   private final GuiScreen parent;
   private float tierListAnim = 1.0F;
   private float tierListAnimTarget = 1.0F;
   private long tierListAnimLastMs = 0L;
   private ParasitesPage.BestiaryPage pendingPage3 = null;
   private ParasiteTier pendingTier3 = null;
   private BestiaryEntry pendingMob3 = null;
   private int loreScrollPx = 0;
   private boolean loreScrollDrag = false;
   private int loreScrollDragStartMouseY = 0;
   private int loreScrollDragStartScrollPx = 0;
   private int loreTextX = 0;
   private int loreTextY = 0;
   private int loreTextW = 0;
   private int loreTextH = 0;
   private int loreContentH = 0;
   private int loreScrollTrackX = 0;
   private int loreScrollTrackY = 0;
   private int loreScrollTrackW = 0;
   private int loreScrollTrackH = 0;
   private int loreScrollThumbY = 0;
   private int loreScrollThumbH = 0;
   private boolean modelDragActive = false;
   private float manualYawDeg = 0.0F;
   private int dragStartMouseX = 0;
   private float dragStartYawDeg = 0.0F;
   private boolean autoRotateModel = true;
   private float manualPitchDeg = 0.0F;
   private int dragStartMouseY = 0;
   private float dragStartPitchDeg = 0.0F;
   private static final int BTN_ROTATE = 1004;
   private int modelRectX;
   private int modelRectY;
   private int modelRectW;
   private int modelRectH;
   private float modelZoom = 1.0F;
   private static final float MODEL_ZOOM_MIN = 0.35F;
   private static final float MODEL_ZOOM_MAX = 2.75F;
   private static final int BTN_GREENSCREEN = 1005;
   private static final int BGSCREEN_OFF = 0;
   private static final int BGSCREEN_GREEN = 1;
   private static final int BGSCREEN_BLUE = 2;
   private int modelBgScreen = 0;
   private static final int BTN_APPLY_POSE = 1010;
   private static final int BTN_RESET_POSE = 1011;
   private float loreAnim = 0.0F;
   private float loreAnimTarget = 0.0F;
   private boolean lorePopupClosingRefresh = false;
   private long loreAnimLastMs = 0L;
   private int lorePopupX = 0;
   private int lorePopupY = 0;
   private int lorePopupW = 0;
   private int lorePopupH = 0;
   private int loreBackX = 0;
   private int loreBackY = 0;
   private int loreBackW = 80;
   private int loreBackH = 20;
   private GuiTextField tfYaw;
   private GuiTextField tfPitch;
   private GuiTextField tfZoom;
   private GuiTextField tfPanX;
   private GuiTextField tfPanY;
   private float mobDetailAnim = 1.0F;
   private float mobDetailAnimTarget = 1.0F;
   private long mobDetailAnimLastMs = 0L;
   private float mobListAnim = 1.0F;
   private float mobListAnimTarget = 1.0F;
   private long mobListAnimLastMs = 0L;
   private boolean tierListEnterFromRight = false;
   private ParasitesPage.BestiaryPage pendingPage2 = null;
   private ParasiteTier pendingTier2 = null;
   private BestiaryEntry pendingMob2 = null;
   private int dropsScrollPx = 0;
   private boolean dropsScrollDrag = false;
   private int dropsScrollDragStartMouseY = 0;
   private int dropsScrollDragStartScrollPx = 0;
   private int dropsViewX = 0;
   private int dropsViewY = 0;
   private int dropsViewW = 0;
   private int dropsViewH = 0;
   private int dropsContentH = 0;
   private int dropsScrollTrackX = 0;
   private int dropsScrollTrackY = 0;
   private int dropsScrollTrackW = 0;
   private int dropsScrollTrackH = 0;
   private int dropsScrollThumbY = 0;
   private int dropsScrollThumbH = 0;
   private ParasitesPage.BestiaryPage pendingPage = null;
   private ParasiteTier pendingTier = null;
   private BestiaryEntry pendingMob = null;
   private int lastW = -1;
   private int lastH = -1;
   private boolean poseControlsInit = false;
   private static final boolean DEBUG_DROPS = false;
   private static final Map<String, String> CFG_CATEGORY_TO_FAMILY = new HashMap<>();
   private boolean dropsLoaded = false;
   private long dropsLastModified = -1L;
   private final Map<String, List<ParasitesPage.DropEntry>> dropsByCategory = new HashMap<>();
   private final Set<String> knownCategories = new HashSet<>();
   private boolean modelPanActive = false;
   private int panStartMouseX = 0;
   private int panStartMouseY = 0;
   private int modelPanX = 0;
   private int modelPanY = 0;
   private int panStartX = 0;
   private int panStartY = 0;
   private static final Map<ParasiteTier, ItemStack> TIER_ICONS = new HashMap<>();
   private static final ItemStack DEFAULT_TIER_ICON;

   private int LIST_VIEW_TOP() {
      return 68;
   }

   private int LIST_BOTTOM() {
      return this.field_146295_m - 40;
   }

   private void startMobListExit(ParasitesPage.BestiaryPage nextPage, ParasiteTier nextTier, BestiaryEntry nextMob) {
      this.pendingPage2 = nextPage;
      this.pendingTier2 = nextTier;
      this.pendingMob2 = nextMob;
      this.mobListAnimTarget = 0.0F;
      this.mobListAnimLastMs = 0L;
      if (nextPage == ParasitesPage.BestiaryPage.MOB_DETAIL) {
         this.mobDetailAnim = 0.0F;
         this.mobDetailAnimTarget = 1.0F;
         this.mobDetailAnimLastMs = 0L;
      }
   }

   private void startTierListExit(ParasitesPage.BestiaryPage nextPage, ParasiteTier nextTier, BestiaryEntry nextMob) {
      this.pendingPage3 = nextPage;
      this.pendingTier3 = nextTier;
      this.pendingMob3 = nextMob;
      this.tierListAnimTarget = 0.0F;
      this.tierListAnimLastMs = 0L;
   }

   public ParasitesPage(EntityPlayer player, GuiScreen parent) {
      this.player = player;
      this.parent = parent;
   }

   public void openParasitesRoot() {
      this.page = ParasitesPage.BestiaryPage.PARASITES;
      this.selectedTier = null;
      this.selectedMob = null;
      this.visibleTiers.clear();
      this.visibleMobs.clear();
      this.tierListEnterFromRight = false;
      this.tierListAnim = 0.0F;
      this.tierListAnimTarget = 1.0F;
      this.tierListAnimLastMs = 0L;
   }

   private static float clamp01(float v) {
      return Math.max(0.0F, Math.min(1.0F, v));
   }

   private static float smoothstep(float a) {
      a = clamp01(a);
      return a * a * (3.0F - 2.0F * a);
   }

   private void startMobDetailExit(ParasitesPage.BestiaryPage nextPage, ParasiteTier nextTier, BestiaryEntry nextMob) {
      this.pendingPage = nextPage;
      this.pendingTier = nextTier;
      this.pendingMob = nextMob;
      this.mobDetailAnimTarget = 0.0F;
      this.mobDetailAnimLastMs = 0L;
      this.loreAnimTarget = 0.0F;
      this.lorePopupClosingRefresh = false;
      if (nextPage == ParasitesPage.BestiaryPage.MOB_LIST) {
         this.mobListAnim = 0.0F;
         this.mobListAnimTarget = 1.0F;
         this.mobListAnimLastMs = 0L;
      }
   }

   private File getMobsCfgFile() {
      return new File(this.field_146297_k.field_71412_D, "config/srparasites/SRParasitesMobs.cfg");
   }

   private void drawAnimatedStripVertical(ResourceLocation sheet, int frames, int x, int y, int w, int h, int frameW, int frameH, int frameDurationMs) {
      long nowMs = System.nanoTime() / 1000000L;
      long index = nowMs / Math.max(1, frameDurationMs) % Math.max(1, frames);
      int u = 0;
      int v = (int)index * frameH;
      int sheetH = frames * frameH;
      this.field_146297_k.func_110434_K().func_110577_a(sheet);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.func_152125_a(x, y, u, v, frameW, frameH, w, h, frameW, sheetH);
   }

   private void applyTierScrollLayout() {
      int contentH = this.visibleTiers.size() * 36;
      int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - this.LIST_VIEW_TOP()));
      this.scrollTiers = Math.max(0, Math.min(this.scrollTiers, maxScroll));

      for (GuiButton b : this.field_146292_n) {
         if (b instanceof ParasitesPage.ListButton && b.field_146127_k >= 100 && b.field_146127_k < 200) {
            ParasitesPage.ListButton lb = (ParasitesPage.ListButton)b;
            int rowY = lb.rowTopY - this.scrollTiers;
            b.field_146129_i = rowY + 4;
            boolean inView = rowY + 28 > this.LIST_VIEW_TOP() && rowY < this.LIST_BOTTOM();
            b.field_146125_m = inView;
         }
      }
   }

   private void applyMobScrollLayout() {
      int contentH = this.visibleMobs.size() * 36;
      int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - this.LIST_VIEW_TOP()));
      this.scrollMobs = Math.max(0, Math.min(this.scrollMobs, maxScroll));

      for (GuiButton b : this.field_146292_n) {
         if (b instanceof ParasitesPage.ListButton && b.field_146127_k >= 200 && b.field_146127_k < 300) {
            ParasitesPage.ListButton lb = (ParasitesPage.ListButton)b;
            int rowY = lb.rowTopY - this.scrollMobs;
            b.field_146129_i = rowY + 4;
            boolean inView = rowY + 28 > this.LIST_VIEW_TOP() && rowY < this.LIST_BOTTOM();
            b.field_146125_m = inView;
         }
      }
   }

   private void drawPanel(ResourceLocation tex, int x, int y, int w, int h) {
      this.field_146297_k.func_110434_K().func_110577_a(tex);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.func_152125_a(x, y, 0.0F, 0.0F, 256, 256, w, h, 256.0F, 256.0F);
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      int dWheel = Mouse.getEventDWheel();
      if (dWheel != 0) {
         if (this.lorePopupOpen) {
            int wheelDir = (int)Math.signum((float)dWheel);
            int step = 14;
            this.loreScrollPx += (wheelDir < 0 ? step : -step) * 3;
            this.clampLoreScroll();
         } else {
            int wheelDir = (int)Math.signum((float)dWheel);
            if (!this.lorePopupOpen && this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.selectedMob != null && dWheel != 0) {
               int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
               int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
               boolean overDrops = mx >= this.dropsViewX
                  && mx < this.dropsViewX + this.dropsViewW
                  && my >= this.dropsViewY
                  && my < this.dropsViewY + this.dropsViewH;
               if (overDrops && this.dropsContentH > this.dropsViewH) {
                  int step = 14;
                  this.dropsScrollPx += (wheelDir < 0 ? step : -step) * 3;
                  this.clampDropsScroll();
                  return;
               }
            }

            if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.selectedMob != null) {
               int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
               int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
               boolean overModel = mx >= this.modelRectX
                  && mx < this.modelRectX + this.modelRectW
                  && my >= this.modelRectY
                  && my < this.modelRectY + this.modelRectH;
               if (this.isBgScreenActive() || overModel) {
                  float step = 0.1F;
                  if (wheelDir > 0) {
                     this.modelZoom += step;
                  } else {
                     this.modelZoom -= step;
                  }

                  this.modelZoom = Math.max(0.35F, Math.min(2.75F, this.modelZoom));
                  this.syncPoseFieldsFromState();
                  return;
               }
            }

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
   }

   private static String loreKeyFromMobId(String mobId) {
      ResourceLocation rl = mobId.indexOf(58) >= 0 ? new ResourceLocation(mobId) : new ResourceLocation("srparasites", mobId);
      return "lore." + rl.func_110624_b() + "." + rl.func_110623_a();
   }

   private static String tierLangKey(ParasiteTier t) {
      String key = t.name().toLowerCase(Locale.ROOT);
      if ("infected".equals(key)) {
         key = "assimilated";
      }

      return "bestiary.tier." + key;
   }

   private String distort(String s) {
      return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
      Keyboard.enableRepeatEvents(true);
      this.field_146292_n.clear();
      if (!this.syncRequested) {
         this.syncRequested = true;
         BestiaryNetwork.CH.sendToServer(new PacketBestiaryRequest());
      }

      int navY = 10;
      int navHomeX = 10;
      int navHomeW = 60;
      int navTiersX = 74;
      int navTiersW = 60;
      int navMobListX = 138;
      int navMobListW = 80;
      switch (this.page) {
         case PARASITES:
            this.visibleMobs.clear();
            this.visibleTiers.clear();
            IBestiaryProgress progx = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
            if (progx != null) {
               for (ParasiteTier t : GuiBestiary.getDisplayOrderLocalized()) {
                  if (progx.isTierSeen(t)) {
                     this.visibleTiers.add(t);
                  }
               }
            }

            int rowTop = this.LIST_VIEW_TOP();

            for (int i = 0; i < this.visibleTiers.size(); i++) {
               ParasiteTier tx = this.visibleTiers.get(i);
               String tierLabel = this.distort(I18n.func_135052_a(tierLangKey(tx), new Object[0]));
               ItemStack icon = TIER_ICONS.get(tx);
               if (icon == null || icon.func_190926_b()) {
                  icon = DEFAULT_TIER_ICON;
               }

               this.field_146292_n.add(new ParasitesPage.TierButton(100 + i, 30, rowTop, 140, 20, tierLabel, tx, icon));
               rowTop += 36;
            }

            this.scrollTiers = 0;
            this.applyTierScrollLayout();
            this.field_146292_n
               .add(new ParasitesPage.AnimatedButton(1000, 10, 10, 60, 20, this.distort(I18n.func_135052_a("bestiary.nav.home", new Object[0])), 0));
            break;
         case MOB_LIST:
            this.visibleMobs.clear();
            IBestiaryProgress progx = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
            if (progx != null && this.selectedTier != null) {
               for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
                  if (e.tier == this.selectedTier && isKnown(progx, e)) {
                     this.visibleMobs.add(e);
                  }
               }
            }

            int rowTop = this.LIST_VIEW_TOP();

            for (int i = 0; i < this.visibleMobs.size(); i++) {
               BestiaryEntry ex = this.visibleMobs.get(i);
               String name = this.distort(I18n.func_135052_a(ex.nameKey, new Object[0]));
               this.field_146292_n.add(new ParasitesPage.MobListEntryButton(200 + i, 30, rowTop, 140, 20, name));
               rowTop += 36;
            }

            this.applyMobScrollLayout();
            this.field_146292_n
               .add(new ParasitesPage.AnimatedButton(1000, 10, 10, 60, 20, this.distort(I18n.func_135052_a("bestiary.nav.home", new Object[0])), 0));
            this.field_146292_n
               .add(new ParasitesPage.AnimatedButton(1001, 74, 10, 60, 20, this.distort(I18n.func_135052_a("bestiary.nav.tiers", new Object[0])), 0));
            break;
         case HOME:
            int cx = this.field_146294_l / 2;
            this.field_146292_n.add(new GuiButton(10, cx - 60, 70, 120, 20, this.distort(I18n.func_135052_a("bestiary.tab.parasites", new Object[0]))));
            this.field_146292_n.add(new GuiButton(11, cx - 60, 95, 120, 20, this.distort(I18n.func_135052_a("bestiary.tab.blocks", new Object[0]))));
            this.field_146292_n.add(new GuiButton(12, cx - 60, 120, 120, 20, this.distort(I18n.func_135052_a("bestiary.tab.celestial", new Object[0]))));
            this.field_146292_n.add(new GuiButton(14, cx - 60, 170, 120, 20, this.distort(I18n.func_135052_a("bestiary.tab.systems", new Object[0]))));
            this.field_146292_n.add(new GuiButton(13, cx - 60, 145, 120, 20, this.distort(I18n.func_135052_a("bestiary.tab.effects", new Object[0]))));
            break;
         case MOB_DETAIL:
            if (this.isBgScreenActive()) {
               int bottomY = this.field_146295_m - 24;
               int gap = 6;
               int gsW = 140;
               int rotW = 130;
               int runW = 90;
               int x = this.field_146294_l - 10;
               x -= gsW;
               this.field_146292_n.add(new GuiButton(1005, x, bottomY, gsW, 20, this.distort(I18n.func_135052_a(this.getBgScreenLabelKey(), new Object[0]))));
               x -= gap;
               x -= rotW;
               this.field_146292_n
                  .add(
                     new GuiButton(
                        1004,
                        x,
                        bottomY,
                        rotW,
                        20,
                        this.autoRotateModel
                           ? this.distort(I18n.func_135052_a("bestiary.controls.rotation.on", new Object[0]))
                           : this.distort(I18n.func_135052_a("bestiary.controls.rotation.off", new Object[0]))
                     )
                  );
               x -= gap;
               x -= runW;
               this.field_146292_n
                  .add(
                     new GuiButton(
                        1003,
                        x,
                        bottomY,
                        runW,
                        20,
                        this.modelRun
                           ? this.distort(I18n.func_135052_a("bestiary.controls.run.on", new Object[0]))
                           : this.distort(I18n.func_135052_a("bestiary.controls.run.off", new Object[0]))
                     )
                  );
            } else {
               this.field_146292_n
                  .add(new ParasitesPage.AnimatedButton(1000, 10, 10, 60, 20, this.distort(I18n.func_135052_a("bestiary.nav.home", new Object[0])), 0));
               this.field_146292_n
                  .add(new ParasitesPage.AnimatedButton(1001, 74, 10, 60, 20, this.distort(I18n.func_135052_a("bestiary.nav.tiers", new Object[0])), 0));
               this.field_146292_n
                  .add(new ParasitesPage.AnimatedButton(1002, 138, 10, 80, 20, this.distort(I18n.func_135052_a("bestiary.nav.mob_list", new Object[0])), 0));
               int runX = 222;
               int runW = 90;
               this.field_146292_n
                  .add(
                     new ParasitesPage.AnimatedButton(
                        1003,
                        runX,
                        10,
                        runW,
                        20,
                        this.modelRun
                           ? this.distort(I18n.func_135052_a("bestiary.controls.run.on", new Object[0]))
                           : this.distort(I18n.func_135052_a("bestiary.controls.run.off", new Object[0])),
                        0
                     )
                  );
               int rotX = runX + runW + 4;
               int rotW = 130;
               this.field_146292_n
                  .add(
                     new ParasitesPage.AnimatedButton(
                        1004,
                        rotX,
                        10,
                        rotW,
                        20,
                        this.autoRotateModel
                           ? this.distort(I18n.func_135052_a("bestiary.controls.rotation.on", new Object[0]))
                           : this.distort(I18n.func_135052_a("bestiary.controls.rotation.off", new Object[0])),
                        0
                     )
                  );
               int gsW = 140;
               int gsBtnX = this.field_146294_l - 10 - gsW;
               int gsBtnY = this.field_146295_m - 18 - 22;
               this.field_146292_n
                  .add(
                     new ParasitesPage.AnimatedButton(
                        1005, gsBtnX, gsBtnY, gsW, 20, this.distort(I18n.func_135052_a(this.getBgScreenLabelKey(), new Object[0])), 1
                     )
                  );
               int loreW = 110;
               int loreX = gsBtnX - 6 - loreW;
               IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
               int kills = 0;
               if (prog != null && this.selectedMob != null) {
                  kills = prog.getKills(this.selectedMob.mobId);
               }

               int loreMin = 10;
               if (this.selectedMob != null && this.selectedMob.minLoreKill > 0) {
                  loreMin = this.selectedMob.minLoreKill;
               }

               String loreLabel;
               if (kills >= loreMin) {
                  loreLabel = this.distort(
                     I18n.func_188566_a("bestiary.lore")
                        ? I18n.func_135052_a("bestiary.lore", new Object[0])
                        : I18n.func_135052_a("bestiary.lore_fallback", new Object[0])
                  );
               } else {
                  loreLabel = this.distort(I18n.func_135052_a("bestiary.lore_unlocks_at_n", new Object[]{loreMin}));
               }

               this.field_146292_n.add(new ParasitesPage.AnimatedButton(1012, loreX, gsBtnY, loreW, 20, loreLabel, 1));
            }
      }
   }

   private static boolean isKnown(IBestiaryProgress prog, BestiaryEntry e) {
      if (prog == null || e == null) {
         return false;
      } else {
         return prog.getKills(e.mobId) > 0 ? true : prog.isMobSeen(e.mobId);
      }
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      if (this.lorePopupOpen && button.field_146127_k != 1012) {
         this.loreAnimTarget = 0.0F;
         this.lorePopupClosingRefresh = false;
      }

      if (button.field_146127_k == 1000) {
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL) {
            this.startMobDetailExit(ParasitesPage.BestiaryPage.HOME, null, null);
         } else if (this.page == ParasitesPage.BestiaryPage.MOB_LIST) {
            this.startMobListExit(ParasitesPage.BestiaryPage.HOME, null, null);
         } else {
            this.lorePopupOpen = false;
            this.resetModelPan();
            if (this.parent != null) {
               this.field_146297_k.func_147108_a(this.parent);
            } else {
               this.page = ParasitesPage.BestiaryPage.HOME;
               this.selectedTier = null;
               this.selectedMob = null;
               this.visibleMobs.clear();
               this.visibleTiers.clear();
               this.func_73866_w_();
            }
         }
      } else if (button.field_146127_k == 1001) {
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL) {
            this.startMobDetailExit(ParasitesPage.BestiaryPage.PARASITES, null, null);
         } else if (this.page == ParasitesPage.BestiaryPage.MOB_LIST) {
            this.startMobListExit(ParasitesPage.BestiaryPage.PARASITES, null, null);
         } else {
            this.resetModelPan();
            this.lorePopupOpen = false;
            this.tierListEnterFromRight = false;
            this.tierListAnim = 0.0F;
            this.tierListAnimTarget = 1.0F;
            this.tierListAnimLastMs = 0L;
            this.page = ParasitesPage.BestiaryPage.PARASITES;
            this.selectedMob = null;
            this.visibleMobs.clear();
            this.selectedTier = null;
            this.func_73866_w_();
         }
      } else if (button.field_146127_k == 1010) {
         this.applyPoseFromFields();
         this.syncPoseFieldsFromState();
      } else if (button.field_146127_k == 1011) {
         this.resetPoseFields();
         this.syncPoseFieldsFromState();
      } else if (button.field_146127_k == 1003) {
         this.modelRun = !this.modelRun;
         this.func_73866_w_();
      } else if (button.field_146127_k == 1004) {
         this.autoRotateModel = !this.autoRotateModel;
         if (this.autoRotateModel) {
            this.modelDragActive = false;
         }

         this.func_73866_w_();
      } else if (button.field_146127_k == 1005) {
         this.modelBgScreen++;
         if (this.modelBgScreen > 2) {
            this.modelBgScreen = 0;
         }

         if (this.isBgScreenActive()) {
            this.autoRotateModel = false;
            this.modelDragActive = false;
            this.modelPanActive = false;
            this.ensurePoseFields();
            this.syncPoseFieldsFromState();
         } else {
            this.modelPanActive = false;
         }

         this.func_73866_w_();
      } else if (button.field_146127_k == 1012) {
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.selectedMob != null) {
            IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
            int kills = prog != null ? prog.getKills(this.selectedMob.mobId) : 0;
            int loreMin = 10;
            if (this.selectedMob.minLoreKill > 0) {
               loreMin = this.selectedMob.minLoreKill;
            }

            if (kills >= loreMin) {
               if (!this.lorePopupOpen) {
                  this.lorePopupOpen = true;
                  this.loreAnim = 0.0F;
                  this.loreAnimTarget = 1.0F;
                  this.loreAnimLastMs = 0L;
                  this.lorePopupClosingRefresh = false;
                  this.func_73866_w_();
               } else {
                  if (this.loreAnimTarget > 0.5F) {
                     this.loreAnimTarget = 0.0F;
                  } else {
                     this.loreAnimTarget = 1.0F;
                  }

                  this.lorePopupClosingRefresh = false;
               }
            }
         }
      } else if (button.field_146127_k == 1002) {
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL) {
            this.startMobDetailExit(ParasitesPage.BestiaryPage.MOB_LIST, this.selectedTier, null);
         } else {
            this.lorePopupOpen = false;
            this.resetModelPan();
            this.resetModelView();
            this.modelRun = false;
            this.modelZoom = 1.0F;
            this.page = ParasitesPage.BestiaryPage.MOB_LIST;
            this.selectedMob = null;
            this.func_73866_w_();
         }
      } else {
         switch (this.page) {
            case PARASITES:
               int idxx = button.field_146127_k - 100;
               if (idxx >= 0 && idxx < this.visibleTiers.size()) {
                  this.lorePopupOpen = false;
                  this.selectedTier = this.visibleTiers.get(idxx);
                  this.startTierListExit(ParasitesPage.BestiaryPage.MOB_LIST, this.selectedTier, null);
                  return;
               }
               break;
            case MOB_LIST:
               int idx = button.field_146127_k - 200;
               if (idx >= 0 && idx < this.visibleMobs.size()) {
                  this.lorePopupOpen = false;
                  BestiaryEntry next = this.visibleMobs.get(idx);
                  this.resetModelView();
                  this.modelRun = false;
                  this.modelZoom = 1.0F;
                  this.startMobListExit(ParasitesPage.BestiaryPage.MOB_DETAIL, this.selectedTier, next);
                  return;
               }
               break;
            case HOME:
               if (button.field_146127_k == 10) {
                  this.page = ParasitesPage.BestiaryPage.PARASITES;
                  this.selectedTier = null;
                  this.selectedMob = null;
                  this.tierListEnterFromRight = false;
                  this.tierListAnim = 0.0F;
                  this.tierListAnimTarget = 1.0F;
                  this.tierListAnimLastMs = 0L;
                  this.func_73866_w_();
               } else if (button.field_146127_k == 11) {
                  this.field_146297_k.func_147108_a(new BlocksPage(this.player, this));
               } else if (button.field_146127_k == 12) {
                  this.field_146297_k.func_147108_a(new CelestialEventsPage(this.player, this));
               } else if (button.field_146127_k == 13) {
                  this.field_146297_k.func_147108_a(new StatusEffectsPage(this.player, this));
               } else if (button.field_146127_k == 14) {
                  this.field_146297_k.func_147108_a(new SystemsPage(this.player, this));
               }
            case MOB_DETAIL:
         }
      }
   }

   private void renderEntityPreviewDetail(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, float pitchDeg) {
      boolean prev = this.renderRunThisCall;
      this.renderRunThisCall = this.modelRun;
      this.renderEntityPreview(mobId, cx, cy, boxW, boxH, yawDeg, pitchDeg);
      this.renderRunThisCall = prev;
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      float t = (this.player.field_70173_aa + partialTicks) / 10.0F;
      long nowMs = Minecraft.func_71386_F();
      if (this.loreAnimLastMs == 0L) {
         this.loreAnimLastMs = nowMs;
      }

      float dt = (float)(nowMs - this.loreAnimLastMs) / 1000.0F;
      this.loreAnimLastMs = nowMs;
      float speed = 12.0F;
      float k = 1.0F - (float)Math.exp(-speed * Math.max(0.0F, Math.min(dt, 0.1F)));
      this.loreAnim = this.loreAnim + (this.loreAnimTarget - this.loreAnim) * k;
      long nowMs2 = Minecraft.func_71386_F();
      if (this.mobDetailAnimLastMs == 0L) {
         this.mobDetailAnimLastMs = nowMs2;
      }

      float dt2 = (float)(nowMs2 - this.mobDetailAnimLastMs) / 1000.0F;
      this.mobDetailAnimLastMs = nowMs2;
      float speed2 = 12.0F;
      float k2 = 1.0F - (float)Math.exp(-speed2 * Math.max(0.0F, Math.min(dt2, 0.1F)));
      this.mobDetailAnim = this.mobDetailAnim + (this.mobDetailAnimTarget - this.mobDetailAnim) * k2;
      if (this.pendingPage != null && this.mobDetailAnimTarget == 0.0F && this.mobDetailAnim <= 0.001F) {
         ParasitesPage.BestiaryPage next = this.pendingPage;
         this.page = this.pendingPage;
         if (this.page == ParasitesPage.BestiaryPage.PARASITES) {
            this.tierListEnterFromRight = false;
            this.tierListAnim = 0.0F;
            this.tierListAnimTarget = 1.0F;
            this.tierListAnimLastMs = 0L;
         }

         this.selectedTier = this.pendingTier;
         this.selectedMob = this.pendingMob;
         this.pendingPage = null;
         this.pendingTier = null;
         this.pendingMob = null;
         if (next == ParasitesPage.BestiaryPage.MOB_LIST) {
            this.mobListAnim = 0.0F;
            this.mobListAnimTarget = 1.0F;
            this.mobListAnimLastMs = Minecraft.func_71386_F();
         } else if (next == ParasitesPage.BestiaryPage.MOB_DETAIL) {
            this.mobDetailAnim = 0.0F;
            this.mobDetailAnimTarget = 1.0F;
            this.mobDetailAnimLastMs = Minecraft.func_71386_F();
         } else {
            this.mobDetailAnim = 1.0F;
            this.mobDetailAnimTarget = 1.0F;
            this.mobDetailAnimLastMs = Minecraft.func_71386_F();
         }

         this.func_73866_w_();
      }

      long nowMs3 = Minecraft.func_71386_F();
      if (this.mobListAnimLastMs == 0L) {
         this.mobListAnimLastMs = nowMs3;
      }

      float dt3 = (float)(nowMs3 - this.mobListAnimLastMs) / 1000.0F;
      this.mobListAnimLastMs = nowMs3;
      float speed3 = 12.0F;
      float k3 = 1.0F - (float)Math.exp(-speed3 * Math.max(0.0F, Math.min(dt3, 0.1F)));
      this.mobListAnim = this.mobListAnim + (this.mobListAnimTarget - this.mobListAnim) * k3;
      if (this.pendingPage2 != null && this.mobListAnimTarget == 0.0F && this.mobListAnim <= 0.001F) {
         this.page = this.pendingPage2;
         if (this.page == ParasitesPage.BestiaryPage.HOME) {
            this.selectedTier = null;
            this.selectedMob = null;
            this.visibleMobs.clear();
            this.visibleTiers.clear();
         }

         if (this.page == ParasitesPage.BestiaryPage.PARASITES) {
            this.tierListEnterFromRight = false;
            this.tierListAnim = 0.0F;
            this.tierListAnimTarget = 1.0F;
            this.tierListAnimLastMs = 0L;
         }

         this.selectedTier = this.pendingTier2;
         this.selectedMob = this.pendingMob2;
         this.pendingPage2 = null;
         this.pendingTier2 = null;
         this.pendingMob2 = null;
         this.mobListAnim = 1.0F;
         this.mobListAnimTarget = 1.0F;
         this.mobListAnimLastMs = 0L;
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL) {
            this.mobDetailAnim = 0.0F;
            this.mobDetailAnimTarget = 1.0F;
            this.mobDetailAnimLastMs = 0L;
         }

         this.func_73866_w_();
      }

      if (this.lorePopupOpen && this.loreAnimTarget == 0.0F && this.loreAnim <= 0.001F) {
         this.loreAnim = 0.0F;
         this.lorePopupOpen = false;
         if (!this.lorePopupClosingRefresh) {
            this.lorePopupClosingRefresh = true;
            this.func_73866_w_();
         }
      } else if (this.loreAnimTarget > 0.0F) {
         this.lorePopupClosingRefresh = false;
      }

      String title = this.distort(I18n.func_135052_a("item.srparasites.srp_field_guide.name", new Object[0]));
      long nowMs4 = Minecraft.func_71386_F();
      if (this.tierListAnimLastMs == 0L) {
         this.tierListAnimLastMs = nowMs4;
      }

      float dt4 = (float)(nowMs4 - this.tierListAnimLastMs) / 1000.0F;
      this.tierListAnimLastMs = nowMs4;
      float speed4 = 12.0F;
      float k4 = 1.0F - (float)Math.exp(-speed4 * Math.max(0.0F, Math.min(dt4, 0.1F)));
      this.tierListAnim = this.tierListAnim + (this.tierListAnimTarget - this.tierListAnim) * k4;
      if (this.pendingPage3 != null && this.tierListAnimTarget == 0.0F && this.tierListAnim <= 0.001F) {
         ParasitesPage.BestiaryPage nextx = this.pendingPage3;
         this.page = this.pendingPage3;
         this.selectedTier = this.pendingTier3;
         this.selectedMob = this.pendingMob3;
         this.pendingPage3 = null;
         this.pendingTier3 = null;
         this.pendingMob3 = null;
         this.tierListAnim = 1.0F;
         this.tierListAnimTarget = 1.0F;
         this.tierListAnimLastMs = 0L;
         if (nextx == ParasitesPage.BestiaryPage.MOB_LIST) {
            this.mobListAnim = 0.0F;
            this.mobListAnimTarget = 1.0F;
            this.mobListAnimLastMs = 0L;
         } else if (nextx == ParasitesPage.BestiaryPage.MOB_DETAIL) {
            this.mobDetailAnim = 0.0F;
            this.mobDetailAnimTarget = 1.0F;
            this.mobDetailAnimLastMs = 0L;
         }

         this.func_73866_w_();
      }

      int titleW = this.field_146289_q.func_78256_a(title);
      int titleX = this.field_146294_l - titleW - 10;
      int titleY = 12;
      boolean hoverTitle = mouseX >= titleX && mouseX <= titleX + titleW && mouseY >= titleY && mouseY <= titleY + this.field_146289_q.field_78288_b;
      float wiggleX = 0.0F;
      float wiggleY = 0.0F;
      if (hoverTitle) {
         wiggleX = (float)Math.sin(t * 2.0F) * 0.4F;
         wiggleY = (float)Math.sin(t * 3.0F) * 0.6F;
      }

      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(wiggleX, wiggleY, 0.0F);
      this.func_73731_b(this.field_146289_q, title, titleX, titleY, 16777215);
      GlStateManager.func_179121_F();
      if (this.page != ParasitesPage.BestiaryPage.MOB_DETAIL || this.autoRotateModel) {
         this.spinDeg += partialTicks * 1.5F;
      }

      switch (this.page) {
         case PARASITES:
            float a = smoothstep(this.tierListAnim);
            int xOff = this.getTierPageXOff();
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179109_b(xOff, 0.0F, 0.0F);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, a);
            this.drawTierPanelBackground(18);
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.parasite_tiers", new Object[0])), 20, 40, 16777215);
            this.drawListScrollbar(true);
            if (this.selectedTier != null && (this.pendingPage3 != ParasitesPage.BestiaryPage.MOB_LIST || this.tierListAnimTarget != 0.0F)) {
               this.drawTierPreview(this.selectedTier, 180, 70);
            }

            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
            break;
         case MOB_LIST:
            if (this.selectedTier != null) {
               float a = smoothstep(this.mobListAnim);
               int leftOffX = -230;
               int rightOffX = this.field_146294_l + 60;
               int leftXOff = (int)(leftOffX * (1.0F - a));
               int rightXOff = (int)(rightOffX * (1.0F - a));
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b(leftXOff, 0.0F, 0.0F);
               this.drawTierPanelBackground(18);
               String tierLabel = this.distort(I18n.func_135052_a("bestiary.tier." + this.selectedTier.name().toLowerCase(Locale.ROOT), new Object[0]));
               this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.tier_label", new Object[]{tierLabel})), 20, 40, 16777215);
               this.drawListScrollbar(false);
               GlStateManager.func_179121_F();
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b(rightXOff, 0.0F, 0.0F);
               this.drawMobListWithRenders(this.visibleMobs, 190);
               GlStateManager.func_179121_F();
            }
            break;
         case HOME:
            this.func_73732_a(
               this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.select_category", new Object[0])), this.field_146294_l / 2, 35, 11184810
            );
            break;
         case MOB_DETAIL:
            if (this.selectedMob != null) {
               float a = smoothstep(this.mobDetailAnim);
               int offX = (int)((1.0F - a) * (this.field_146294_l + 60));
               GlStateManager.func_179094_E();
               GlStateManager.func_179147_l();
               GlStateManager.func_179109_b(offX, 0.0F, 0.0F);
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, a);
               this.drawMobDetail(this.selectedMob, 20, 40, mouseX - offX, mouseY, partialTicks);
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179084_k();
               GlStateManager.func_179121_F();
            }
      }

      if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL
         && this.selectedMob != null
         && this.lorePopupOpen
         && !this.isBgScreenActive()
         && this.loreAnim > 0.0F) {
         IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
         int kills = prog != null ? prog.getKills(this.selectedMob.mobId) : 0;
         int loreMin = 10;
         if (this.selectedMob.minLoreKill > 0) {
            loreMin = this.selectedMob.minLoreKill;
         }

         if (kills >= loreMin) {
            int boxW = Math.min(300, this.field_146294_l - 40);
            int boxH = Math.min(160, this.field_146295_m - 60);
            int boxX = (this.field_146294_l - boxW) / 2;
            int boxY = (this.field_146295_m - boxH) / 2;
            float a = Math.max(0.0F, Math.min(1.0F, this.loreAnim));
            float eased = a * a * (3.0F - 2.0F * a);
            int offY = this.field_146295_m + 20;
            int animY = (int)(offY + (boxY - offY) * eased);
            this.lorePopupX = boxX;
            this.lorePopupY = animY;
            this.lorePopupW = boxW;
            this.lorePopupH = boxH;
            int dimA = (int)(136.0F * eased);
            func_73734_a(0, 0, this.field_146294_l, this.field_146295_m, dimA << 24);
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, eased);
            this.drawPanel(TEX_LORE_BG, boxX, animY, boxW, boxH);
            String lore = this.distort(I18n.func_135052_a(loreKeyFromMobId(this.selectedMob.mobId), new Object[0]));
            int tx = boxX + 10;
            int ty = animY + 10;
            int tw = boxW - 20;
            int th = boxH - 40;
            int sbW = 6;
            int sbPad = 2;
            int textW = tw - (sbW + sbPad);
            this.loreTextX = tx;
            this.loreTextY = ty;
            this.loreTextW = textW;
            this.loreTextH = th;
            int textA = (int)(255.0F * eased);
            int loreColor = textA << 24 | 3026478;
            List<String> loreLines = this.field_146289_q.func_78271_c(lore, this.loreTextW);
            int lineH = this.field_146289_q.field_78288_b;
            int lineStep = lineH + 1;
            this.loreContentH = Math.max(0, loreLines.size() * lineStep);
            this.clampLoreScroll();
            this.enableScissor(this.loreTextX, this.loreTextY, this.loreTextW, this.loreTextH);
            int drawY = this.loreTextY - this.loreScrollPx;

            for (int i = 0; i < loreLines.size(); i++) {
               int yLineBot = drawY + lineH;
               if (yLineBot >= this.loreTextY && drawY <= this.loreTextY + this.loreTextH) {
                  this.field_146289_q.func_78276_b(loreLines.get(i), this.loreTextX, drawY, loreColor);
               }

               drawY += lineStep;
            }

            this.disableScissor();
            this.loreScrollTrackX = tx + textW + sbPad;
            this.loreScrollTrackY = ty;
            this.loreScrollTrackW = sbW;
            this.loreScrollTrackH = th;
            if (this.loreContentH > this.loreTextH) {
               int trackBgA = (int)(85.0F * eased);
               int thumbA = (int)(170.0F * eased);
               int trackBg = trackBgA << 24 | 0;
               int thumbBg = thumbA << 24 | 0;
               func_73734_a(
                  this.loreScrollTrackX,
                  this.loreScrollTrackY,
                  this.loreScrollTrackX + this.loreScrollTrackW,
                  this.loreScrollTrackY + this.loreScrollTrackH,
                  trackBg
               );
               int maxScroll = this.loreContentH - this.loreTextH;
               int thumbH = (int)((float)this.loreTextH * this.loreTextH / this.loreContentH);
               thumbH = Math.max(10, Math.min(this.loreTextH, thumbH));
               int thumbY = this.loreTextY;
               if (maxScroll > 0) {
                  float frac = (float)this.loreScrollPx / maxScroll;
                  thumbY = this.loreTextY + (int)((this.loreTextH - thumbH) * frac);
               }

               this.loreScrollThumbY = thumbY;
               this.loreScrollThumbH = thumbH;
               func_73734_a(this.loreScrollTrackX, thumbY, this.loreScrollTrackX + this.loreScrollTrackW, thumbY + thumbH, thumbBg);
            } else {
               this.loreScrollThumbY = this.loreTextY;
               this.loreScrollThumbH = this.loreTextH;
            }

            this.loreBackW = 80;
            this.loreBackH = 20;
            this.loreBackX = boxX + boxW - this.loreBackW - 10;
            this.loreBackY = animY + boxH - this.loreBackH - 10;
            int btnBgA = (int)(170.0F * eased);
            int btnBg = btnBgA << 24 | 0;
            func_73734_a(this.loreBackX, this.loreBackY, this.loreBackX + this.loreBackW, this.loreBackY + this.loreBackH, btnBg);
            String back = this.distort(I18n.func_135052_a("gui.back", new Object[0]));
            int backW = this.field_146289_q.func_78256_a(back);
            int backX = this.loreBackX + (this.loreBackW - backW) / 2;
            int backY = this.loreBackY + (this.loreBackH - this.field_146289_q.field_78288_b) / 2;
            int backColor = textA << 24 | 16777215;
            this.field_146289_q.func_78276_b(back, backX, backY, backColor);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
         } else {
            this.loreAnimTarget = 0.0F;
            this.lorePopupClosingRefresh = false;
         }
      }

      if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.isBgScreenActive() && this.selectedMob != null) {
         this.ensurePoseFields();
         int panelX = 10;
         int panelY = this.field_146295_m - 96;
         func_73734_a(panelX - 4, panelY - 6, panelX + 170, panelY + 52, -1442840576);
         String yawL = this.distort(I18n.func_135052_a("bestiary.pose.yaw", new Object[0]));
         String pitchL = this.distort(I18n.func_135052_a("bestiary.pose.pitch", new Object[0]));
         String zoomL = this.distort(I18n.func_135052_a("bestiary.pose.zoom", new Object[0]));
         String panXL = this.distort(I18n.func_135052_a("bestiary.pose.panx", new Object[0]));
         String panYL = this.distort(I18n.func_135052_a("bestiary.pose.pany", new Object[0]));
         this.field_146289_q.func_78276_b(yawL, panelX, panelY + 2, 16777215);
         this.field_146289_q.func_78276_b(pitchL, panelX, panelY + 16, 16777215);
         this.field_146289_q.func_78276_b(zoomL, panelX, panelY + 30, 16777215);
         this.field_146289_q.func_78276_b(panXL, panelX + 74, panelY + 2, 16777215);
         this.field_146289_q.func_78276_b(panYL, panelX + 74, panelY + 16, 16777215);
         this.tfYaw.func_146194_f();
         this.tfPitch.func_146194_f();
         this.tfZoom.func_146194_f();
         this.tfPanX.func_146194_f();
         this.tfPanY.func_146194_f();
      }

      super.func_73863_a(mouseX, mouseY, partialTicks);
      this.drawRunTooltipIfHovered(mouseX, mouseY);
   }

   private void drawRunTooltipIfHovered(int mouseX, int mouseY) {
      GuiButton runBtn = null;

      for (GuiButton b : this.field_146292_n) {
         if (b != null && b.field_146125_m && b.field_146127_k == 1003) {
            runBtn = b;
            break;
         }
      }

      if (runBtn != null) {
         if (mouseX >= runBtn.field_146128_h
            && mouseX < runBtn.field_146128_h + runBtn.field_146120_f
            && mouseY >= runBtn.field_146129_i
            && mouseY < runBtn.field_146129_i + runBtn.field_146121_g) {
            List<String> tip = Collections.singletonList(this.distort(I18n.func_135052_a("bestiary.tooltip.run_inaccurate", new Object[0])));
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            this.func_146283_a(tip, mouseX, mouseY);
            GlStateManager.func_179126_j();
            GlStateManager.func_179145_e();
         }
      }
   }

   private static int clampInt(int v, int lo, int hi) {
      if (v < lo) {
         return lo;
      } else {
         return v > hi ? hi : v;
      }
   }

   private void clampDropsScroll() {
      int maxScroll = Math.max(0, this.dropsContentH - this.dropsViewH);
      this.dropsScrollPx = clampInt(this.dropsScrollPx, 0, maxScroll);
   }

   private void clampLoreScroll() {
      int maxScroll = Math.max(0, this.loreContentH - this.loreTextH);
      this.loreScrollPx = clampInt(this.loreScrollPx, 0, maxScroll);
   }

   private void enableScissor(int x, int y, int w, int h) {
      ScaledResolution sr = new ScaledResolution(this.field_146297_k);
      int scale = sr.func_78325_e();
      int sx = x * scale;
      int sy = this.field_146297_k.field_71440_d - (y + h) * scale;
      int sw = w * scale;
      int sh = h * scale;
      GL11.glEnable(3089);
      GL11.glScissor(sx, sy, sw, sh);
   }

   private void disableScissor() {
      GL11.glDisable(3089);
   }

   private int getTierPageXOff() {
      float a = smoothstep(this.tierListAnim);
      int off;
      if (this.tierListEnterFromRight) {
         int rightOffX = this.field_146294_l + 60;
         off = (int)(rightOffX * (1.0F - a));
      } else {
         int leftOffX = -230;
         off = (int)(leftOffX * (1.0F - a));
      }

      return off;
   }

   protected void func_73869_a(char typedChar, int keyCode) throws IOException {
      if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.isBgScreenActive() && this.selectedMob != null) {
         this.ensurePoseFields();
         boolean used = this.tfYaw.func_146201_a(typedChar, keyCode)
            || this.tfPitch.func_146201_a(typedChar, keyCode)
            || this.tfZoom.func_146201_a(typedChar, keyCode)
            || this.tfPanX.func_146201_a(typedChar, keyCode)
            || this.tfPanY.func_146201_a(typedChar, keyCode);
         if (used) {
            this.applyPoseFromFields();
            return;
         }

         if (keyCode == 28 || keyCode == 156) {
            this.applyPoseFromFields();
            this.syncPoseFieldsFromState();
            return;
         }
      }

      super.func_73869_a(typedChar, keyCode);
   }

   private void drawTierPanelBackground(int extraBottom) {
      int panelX = 18;
      int panelY = this.LIST_VIEW_TOP() - 14;
      int panelW = 164;
      int btnOffset = 4;
      int panelBottom = this.LIST_BOTTOM() + btnOffset + 10 + extraBottom;
      int panelH = panelBottom - panelY;
      func_73734_a(panelX - 1, panelY - 1, panelX + panelW + 1, panelY + panelH + 1, -1442840576);
      func_73734_a(panelX, panelY, panelX + panelW, panelY + panelH, -2013265920);
   }

   private boolean isMouseOverPoseFields(int mouseX, int mouseY) {
      return !this.poseControlsInit
         ? false
         : this.tfYaw != null && this.tfYaw.func_146176_q() && this.tfYaw.func_146206_l()
            || this.isOver(this.tfYaw, mouseX, mouseY)
            || this.tfPitch != null && this.tfPitch.func_146176_q() && this.tfPitch.func_146206_l()
            || this.isOver(this.tfPitch, mouseX, mouseY)
            || this.tfZoom != null && this.tfZoom.func_146176_q() && this.tfZoom.func_146206_l()
            || this.isOver(this.tfZoom, mouseX, mouseY)
            || this.tfPanX != null && this.tfPanX.func_146176_q() && this.tfPanX.func_146206_l()
            || this.isOver(this.tfPanX, mouseX, mouseY)
            || this.tfPanY != null && this.tfPanY.func_146176_q() && this.tfPanY.func_146206_l()
            || this.isOver(this.tfPanY, mouseX, mouseY);
   }

   private boolean isOver(GuiTextField tf, int mx, int my) {
      return tf == null
         ? false
         : mx >= tf.field_146209_f && mx < tf.field_146209_f + tf.field_146218_h && my >= tf.field_146210_g && my < tf.field_146210_g + tf.field_146219_i;
   }

   public void func_146281_b() {
      super.func_146281_b();
      Keyboard.enableRepeatEvents(false);
   }

   private void drawTierPreview(ParasiteTier tier, int x, int y) {
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
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a("bestiary.parasites.unlock_tier_hint", new Object[0])), x, y, 7829367);
         }
      }
   }

   private void syncPoseFieldsFromState() {
      if (this.poseControlsInit) {
         if (!this.suppressPoseFieldUpdates) {
            if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.isBgScreenActive() && this.selectedMob != null) {
               this.suppressPoseFieldUpdates = true;

               try {
                  this.tfYaw.func_146180_a(String.format(Locale.US, "%.1f", this.manualYawDeg));
                  this.tfPitch.func_146180_a(String.format(Locale.US, "%.1f", this.manualPitchDeg));
                  this.tfZoom.func_146180_a(String.format(Locale.US, "%.2f", this.modelZoom));
                  this.tfPanX.func_146180_a(Integer.toString(this.modelPanX));
                  this.tfPanY.func_146180_a(Integer.toString(this.modelPanY));
               } finally {
                  this.suppressPoseFieldUpdates = false;
               }
            }
         }
      }
   }

   private void resetModelView() {
      this.modelDragActive = false;
      this.autoRotateModel = true;
      this.manualYawDeg = 0.0F;
      this.manualPitchDeg = 0.0F;
      this.dragStartMouseX = 0;
      this.dragStartMouseY = 0;
      this.dragStartYawDeg = 0.0F;
      this.dragStartPitchDeg = 0.0F;
      this.modelZoom = 1.0F;
      this.modelRun = false;
   }

   private void drawMobListWithRenders(List<BestiaryEntry> mobs, int xRight) {
      IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
      if (prog != null) {
         int panelPadX = 8;
         int panelPadY = 4;
         int iconBoxW = 28;
         int iconBoxH = 28;
         int textGap = 8;
         int panelX = xRight - 8;
         int panelW = this.field_146294_l - 8 - panelX;
         int rowY = this.LIST_VIEW_TOP() - this.scrollMobs;

         for (BestiaryEntry e : mobs) {
            int rowBot = rowY + 28;
            if (rowBot > this.LIST_VIEW_TOP() && rowY < this.LIST_BOTTOM()) {
               int centerY = rowY + 14;
               int pY = rowY - 4;
               int pH = 36;
               func_73734_a(panelX - 1, pY - 1, panelX + panelW + 1, pY + pH + 1, -1442840576);
               func_73734_a(panelX, pY, panelX + panelW, pY + pH, -2013265920);
               int iconCenterX = xRight + 14;
               float rs = SRPBestiaryRegistry.getRenderScale(e.mobId);
               float thumbShrink = 0.8F / Math.max(1.0F, rs);
               int thumbMargin = 10;
               this.renderEntityPreview(e.mobId, iconCenterX, centerY, 28, 28, this.spinDeg, thumbMargin, thumbShrink);
               int kills = prog.getKills(e.mobId);
               String name = this.distort(I18n.func_135052_a(e.nameKey, new Object[0]));
               String killsLabel = this.distort(I18n.func_135052_a("bestiary.kills_label", new Object[0]));
               String raw = name + "  (§7" + killsLabel + ": " + kills + "§r)";
               int textX = xRight + 28 + 8;
               int avail = panelX + panelW - textX - 6;
               String shown = this.field_146289_q.func_78269_a(raw, Math.max(32, avail));
               int textY = centerY - this.field_146289_q.field_78288_b / 2;
               this.field_146289_q.func_78276_b(shown, textX, textY, 14540253);
            }

            rowY += 36;
         }

         if (mobs.isEmpty()) {
            this.field_146289_q
               .func_78276_b(this.distort(I18n.func_135052_a("bestiary.no_mobs_unlocked_in_tier", new Object[0])), xRight, this.LIST_VIEW_TOP(), 7829367);
         }
      }
   }

   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.func_73864_a(mouseX, mouseY, mouseButton);
      if ((this.page == ParasitesPage.BestiaryPage.PARASITES || this.page == ParasitesPage.BestiaryPage.MOB_LIST) && mouseButton == 0) {
         boolean tiers = this.page == ParasitesPage.BestiaryPage.PARASITES;
         int count = tiers ? this.visibleTiers.size() : this.visibleMobs.size();
         int contentH = count * 36;
         int viewH = this.LIST_BOTTOM() - this.LIST_VIEW_TOP();
         if (contentH > viewH) {
            this.drawListScrollbar(tiers);
            boolean overThumb = mouseX >= this.listScrollTrackX
               && mouseX < this.listScrollTrackX + this.listScrollTrackW
               && mouseY >= this.listScrollThumbY
               && mouseY < this.listScrollThumbY + this.listScrollThumbH;
            if (overThumb) {
               this.listScrollDrag = true;
               this.listScrollIsTiers = tiers;
               this.listScrollDragStartMouseY = mouseY;
               this.listScrollDragStartScrollPx = tiers ? this.scrollTiers : this.scrollMobs;
               return;
            }
         }
      }

      if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL
         && this.selectedMob != null
         && this.lorePopupOpen
         && !this.isBgScreenActive()
         && this.loreAnim > 0.0F
         && mouseButton == 0
         && mouseX >= this.loreBackX
         && mouseX < this.loreBackX + this.loreBackW
         && mouseY >= this.loreBackY
         && mouseY < this.loreBackY + this.loreBackH) {
         this.loreAnimTarget = 0.0F;
         this.lorePopupClosingRefresh = false;
      } else if (this.lorePopupOpen) {
         if (mouseButton == 0 && this.loreContentH > this.loreTextH) {
            boolean overThumb = mouseX >= this.loreScrollTrackX
               && mouseX < this.loreScrollTrackX + this.loreScrollTrackW
               && mouseY >= this.loreScrollThumbY
               && mouseY < this.loreScrollThumbY + this.loreScrollThumbH;
            if (overThumb) {
               this.loreScrollDrag = true;
               this.loreScrollDragStartMouseY = mouseY;
               this.loreScrollDragStartScrollPx = this.loreScrollPx;
               return;
            }
         }
      } else {
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.isBgScreenActive() && this.selectedMob != null) {
            this.ensurePoseFields();
            this.tfYaw.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfPitch.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfZoom.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfPanX.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfPanY.func_146192_a(mouseX, mouseY, mouseButton);
         }

         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.selectedMob != null && mouseButton == 0) {
            boolean overModel = mouseX >= this.modelRectX
               && mouseX < this.modelRectX + this.modelRectW
               && mouseY >= this.modelRectY
               && mouseY < this.modelRectY + this.modelRectH;
            if ((this.isBgScreenActive() || overModel) && !this.isMouseOverAnyButton(mouseX, mouseY) && !this.isMouseOverPoseFields(mouseX, mouseY)) {
               this.autoRotateModel = false;
               this.modelDragActive = true;
               this.dragStartMouseX = mouseX;
               this.dragStartMouseY = mouseY;
               this.dragStartYawDeg = this.manualYawDeg;
               this.dragStartPitchDeg = this.manualPitchDeg;
               this.func_73866_w_();
            }
         }

         if (!this.lorePopupOpen
            && this.page == ParasitesPage.BestiaryPage.MOB_DETAIL
            && this.selectedMob != null
            && mouseButton == 0
            && this.dropsContentH > this.dropsViewH) {
            boolean overThumb = mouseX >= this.dropsScrollTrackX
               && mouseX < this.dropsScrollTrackX + this.dropsScrollTrackW
               && mouseY >= this.dropsScrollThumbY
               && mouseY < this.dropsScrollThumbY + this.dropsScrollThumbH;
            if (overThumb) {
               this.dropsScrollDrag = true;
               this.dropsScrollDragStartMouseY = mouseY;
               this.dropsScrollDragStartScrollPx = this.dropsScrollPx;
               return;
            }
         }

         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.selectedMob != null && mouseButton == 1 && this.isBgScreenActive()) {
            this.modelPanActive = true;
            this.panStartMouseX = mouseX;
            this.panStartMouseY = mouseY;
            this.panStartX = this.modelPanX;
            this.panStartY = this.modelPanY;
         }
      }
   }

   protected void func_146286_b(int mouseX, int mouseY, int state) {
      super.func_146286_b(mouseX, mouseY, state);
      this.loreScrollDrag = false;
      this.dropsScrollDrag = false;
      if (state == 0) {
         this.modelDragActive = false;
      }

      if (state == 1) {
         this.modelPanActive = false;
      }
   }

   private static ItemStack stackFromId(String id) {
      if (id != null && !id.isEmpty()) {
         id = id.trim();
         Item it = Item.func_111206_d(id);
         return it == null ? ItemStack.field_190927_a : new ItemStack(it);
      } else {
         return ItemStack.field_190927_a;
      }
   }

   private boolean isBgScreenActive() {
      return this.modelBgScreen != 0;
   }

   private int getBgScreenColor() {
      return this.modelBgScreen == 2 ? -16776961 : -16711936;
   }

   private String getBgScreenLabelKey() {
      switch (this.modelBgScreen) {
         case 1:
            return "bestiary.controls.greenscreen.green";
         case 2:
            return "bestiary.controls.greenscreen.blue";
         default:
            return "bestiary.controls.greenscreen.off";
      }
   }

   private void renderItemIconSway(ItemStack stack, int x, int y, float t, int index) {
      if (stack != null && !stack.func_190926_b()) {
         float phase = t * 0.9F + index * 0.35F;
         float ang = (float)Math.sin(phase) * 6.0F;
         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b(x + 8, y + 8, 0.0F);
         GlStateManager.func_179114_b(ang, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179109_b(-(x + 8), -(y + 8), 0.0F);
         RenderHelper.func_74520_c();
         this.field_146297_k.func_175599_af().func_180450_b(stack, x, y);
         RenderHelper.func_74518_a();
         GlStateManager.func_179121_F();
      }
   }

   private void loadDropCacheIfNeeded() {
      File f = this.getMobsCfgFile();
      if (f != null && f.exists() && f.isFile()) {
         long lm = f.lastModified();
         if (!this.dropsLoaded || lm != this.dropsLastModified) {
            this.dropsLoaded = true;
            this.dropsLastModified = lm;
            this.dropsByCategory.clear();
            this.knownCategories.clear();
            String currentCategory = null;
            boolean readingLoot = false;
            List<ParasitesPage.DropEntry> currentLoot = null;

            String line;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
               while ((line = br.readLine()) != null) {
                  String t = line.trim();
                  if (!t.isEmpty() && !t.startsWith("#")) {
                     if (!readingLoot && t.startsWith("\"") && t.endsWith("{")) {
                        int q2 = t.indexOf(34, 1);
                        if (q2 > 1) {
                           currentCategory = t.substring(1, q2);
                           this.knownCategories.add(currentCategory);
                        }
                     } else if (!readingLoot && t.startsWith("}")) {
                        currentCategory = null;
                     } else if (!readingLoot && currentCategory != null && t.startsWith("S:\"") && t.contains("Loot Table\" <")) {
                        readingLoot = true;
                        currentLoot = new ArrayList<>();
                     } else if (readingLoot) {
                        if (t.startsWith(">")) {
                           readingLoot = false;
                           if (currentCategory != null && currentLoot != null) {
                              this.dropsByCategory.put(currentCategory, currentLoot);
                           }

                           currentLoot = null;
                        } else if (!t.startsWith("#")) {
                           String[] parts = t.split(";");
                           if (parts.length >= 4) {
                              String itemId = parts[0].trim();
                              int chance = parseIntSafe(parts[1], 0);
                              int amount = parseIntSafe(parts[2], 1);
                              boolean looting = "true".equalsIgnoreCase(parts[3].trim());
                              if (!itemId.isEmpty()) {
                                 currentLoot.add(new ParasitesPage.DropEntry(itemId, chance, amount, looting));
                              }
                           }
                        }
                     }
                  }
               }
            } catch (Throwable var26) {
               System.out.println("[SRP][BESTIARY][DROPS] Failed reading cfg: " + var26);
               this.dropsByCategory.clear();
               this.knownCategories.clear();
            }
         }
      } else {
         this.dropsLoaded = true;
         this.dropsByCategory.clear();
         this.knownCategories.clear();
         this.dropsLastModified = -1L;
      }
   }

   private List<String> buildCategoryCandidates(String mobId) {
      List<String> out = new ArrayList<>();
      if (mobId != null && !mobId.isEmpty()) {
         ResourceLocation rl = mobId.indexOf(58) >= 0 ? new ResourceLocation(mobId) : new ResourceLocation("srparasites", mobId);
         String domain = rl.func_110624_b();
         String path = rl.func_110623_a();
         out.add(domain + ":" + path);
         String[] prefixes = new String[]{"pri_", "ada_", "sim_", "fer_"};

         for (String p : prefixes) {
            if (path.startsWith(p) && path.length() > p.length()) {
               out.add(domain + ":" + path.substring(p.length()));
            }
         }

         int us = path.indexOf(95);
         if (us > 0 && us + 1 < path.length()) {
            out.add(domain + ":" + path.substring(us + 1));
         }

         if (path.startsWith("carrier_") && path.length() > "carrier_".length()) {
            out.add(domain + ":" + path.substring("carrier_".length()));
         }

         LinkedHashSet<String> uniq = new LinkedHashSet<>(out);
         return new ArrayList<>(uniq);
      } else {
         return out;
      }
   }

   private String resolveCfgCategoryForMob(String mobId) {
      if (mobId == null) {
         return null;
      } else {
         String override = CFG_CATEGORY_TO_FAMILY.get(mobId);
         if (override != null && !override.isEmpty()) {
            return override;
         } else if (this.knownCategories.contains(mobId)) {
            return mobId;
         } else {
            for (String c : this.buildCategoryCandidates(mobId)) {
               if (this.knownCategories.contains(c)) {
                  return c;
               }
            }

            return null;
         }
      }
   }

   private List<ParasitesPage.DropEntry> getDropsForMob(String mobId) {
      this.loadDropCacheIfNeeded();
      String cat = this.resolveCfgCategoryForMob(mobId);
      if (cat == null) {
         return Collections.emptyList();
      } else {
         List<ParasitesPage.DropEntry> drops = this.dropsByCategory.get(cat);
         return drops != null ? drops : Collections.emptyList();
      }
   }

   protected void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      if (this.listScrollDrag && clickedMouseButton == 0) {
         int count = this.listScrollIsTiers ? this.visibleTiers.size() : this.visibleMobs.size();
         int contentH = count * 36;
         int viewH = this.LIST_BOTTOM() - this.LIST_VIEW_TOP();
         int maxScroll = Math.max(1, contentH - viewH);
         int trackTravel = Math.max(1, viewH - this.listScrollThumbH);
         int dy = mouseY - this.listScrollDragStartMouseY;
         int newScroll = this.listScrollDragStartScrollPx + (int)(dy * ((float)maxScroll / trackTravel));
         if (this.listScrollIsTiers) {
            this.scrollTiers = newScroll;
            this.applyTierScrollLayout();
         } else {
            this.scrollMobs = newScroll;
            this.applyMobScrollLayout();
         }
      } else {
         if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.modelDragActive && clickedMouseButton == 0) {
            int dx = mouseX - this.dragStartMouseX;
            int dy = mouseY - this.dragStartMouseY;
            float yawSens = 0.6F;
            float pitchSens = 0.6F;
            this.manualYawDeg = this.dragStartYawDeg - dx * yawSens;
            this.manualPitchDeg = this.dragStartPitchDeg + dy * pitchSens;
            this.manualPitchDeg = Math.max(-60.0F, Math.min(60.0F, this.manualPitchDeg));
            this.syncPoseFieldsFromState();
         }

         if (!this.lorePopupOpen
            && this.page == ParasitesPage.BestiaryPage.MOB_DETAIL
            && this.selectedMob != null
            && this.dropsScrollDrag
            && this.dropsContentH > this.dropsViewH) {
            int maxScroll = this.dropsContentH - this.dropsViewH;
            int trackTravel = Math.max(1, this.dropsViewH - this.dropsScrollThumbH);
            int dy = mouseY - this.dropsScrollDragStartMouseY;
            float pixelsPerScroll = (float)maxScroll / trackTravel;
            this.dropsScrollPx = this.dropsScrollDragStartScrollPx + (int)(dy * pixelsPerScroll);
            this.clampDropsScroll();
         } else if (this.lorePopupOpen && this.loreScrollDrag && this.loreContentH > this.loreTextH) {
            int maxScroll = this.loreContentH - this.loreTextH;
            int trackTravel = Math.max(1, this.loreTextH - this.loreScrollThumbH);
            int dy = mouseY - this.loreScrollDragStartMouseY;
            float pixelsPerScroll = (float)maxScroll / trackTravel;
            this.loreScrollPx = this.loreScrollDragStartScrollPx + (int)(dy * pixelsPerScroll);
            this.clampLoreScroll();
         } else {
            if (this.page == ParasitesPage.BestiaryPage.MOB_DETAIL && this.modelPanActive && clickedMouseButton == 1 && this.isBgScreenActive()) {
               int dx = mouseX - this.panStartMouseX;
               int dy = mouseY - this.panStartMouseY;
               this.modelPanX = this.panStartX + dx;
               this.modelPanY = this.panStartY + dy;
               this.syncPoseFieldsFromState();
            }
         }
      }
   }

   private void ensurePoseFields() {
      if (!this.poseControlsInit || this.lastW != this.field_146294_l || this.lastH != this.field_146295_m) {
         String yawTxt = this.tfYaw != null ? this.tfYaw.func_146179_b() : String.format(Locale.US, "%.1f", this.manualYawDeg);
         String pitchTxt = this.tfPitch != null ? this.tfPitch.func_146179_b() : String.format(Locale.US, "%.1f", this.manualPitchDeg);
         String zoomTxt = this.tfZoom != null ? this.tfZoom.func_146179_b() : String.format(Locale.US, "%.2f", this.modelZoom);
         String panXTxt = this.tfPanX != null ? this.tfPanX.func_146179_b() : Integer.toString(this.modelPanX);
         String panYTxt = this.tfPanY != null ? this.tfPanY.func_146179_b() : Integer.toString(this.modelPanY);
         this.lastW = this.field_146294_l;
         this.lastH = this.field_146295_m;
         int panelX = 10;
         int panelY = this.field_146295_m - 96;
         int w = 40;
         int h = 12;
         int gapY = 14;
         int col1X = panelX + 34;
         int col2X = panelX + 108;
         this.tfYaw = new GuiTextField(3001, this.field_146289_q, col1X, panelY + 0, w, h);
         this.tfPitch = new GuiTextField(3002, this.field_146289_q, col1X, panelY + gapY, w, h);
         this.tfZoom = new GuiTextField(3003, this.field_146289_q, col1X, panelY + gapY * 2, w, h);
         this.tfPanX = new GuiTextField(3004, this.field_146289_q, col2X, panelY + 0, w, h);
         this.tfPanY = new GuiTextField(3005, this.field_146289_q, col2X, panelY + gapY, w, h);
         this.tfYaw.func_146180_a(yawTxt);
         this.tfPitch.func_146180_a(pitchTxt);
         this.tfZoom.func_146180_a(zoomTxt);
         this.tfPanX.func_146180_a(panXTxt);
         this.tfPanY.func_146180_a(panYTxt);
         this.tfYaw.func_146185_a(true);
         this.tfPitch.func_146185_a(true);
         this.tfZoom.func_146185_a(true);
         this.tfPanX.func_146185_a(true);
         this.tfPanY.func_146185_a(true);
         this.tfYaw.func_146203_f(10);
         this.tfPitch.func_146203_f(10);
         this.tfZoom.func_146203_f(10);
         this.tfPanX.func_146203_f(6);
         this.tfPanY.func_146203_f(6);
         this.poseControlsInit = true;
      }
   }

   private void resetPoseFields() {
      this.manualYawDeg = 0.0F;
      this.manualPitchDeg = 0.0F;
      this.modelZoom = 1.0F;
      this.modelPanX = 0;
      this.modelPanY = 0;
      this.autoRotateModel = false;
      if (this.poseControlsInit) {
         this.tfYaw.func_146180_a("0");
         this.tfPitch.func_146180_a("0");
         this.tfZoom.func_146180_a("1.00");
         this.tfPanX.func_146180_a("0");
         this.tfPanY.func_146180_a("0");
      }
   }

   private static float parseFloatSafe(String s, float def) {
      try {
         return Float.parseFloat(s.trim());
      } catch (Throwable var3) {
         return def;
      }
   }

   private static int parseIntSafe(String s, int def) {
      try {
         return Integer.parseInt(s.trim());
      } catch (Throwable var3) {
         return def;
      }
   }

   private void applyPoseFromFields() {
      if (this.poseControlsInit) {
         float yaw = parseFloatSafe(this.tfYaw.func_146179_b(), this.manualYawDeg);
         float pitch = parseFloatSafe(this.tfPitch.func_146179_b(), this.manualPitchDeg);
         float zoom = parseFloatSafe(this.tfZoom.func_146179_b(), this.modelZoom);
         int panX = parseIntSafe(this.tfPanX.func_146179_b(), this.modelPanX);
         int panY = parseIntSafe(this.tfPanY.func_146179_b(), this.modelPanY);
         pitch = Math.max(-90.0F, Math.min(90.0F, pitch));
         zoom = Math.max(0.35F, Math.min(2.75F, zoom));
         this.manualYawDeg = yaw;
         this.manualPitchDeg = pitch;
         this.modelZoom = zoom;
         this.modelPanX = panX;
         this.modelPanY = panY;
         this.autoRotateModel = false;
         this.modelDragActive = false;
      }
   }

   private void drawListScrollbar(boolean tiers) {
      int count = tiers ? this.visibleTiers.size() : this.visibleMobs.size();
      int contentH = count * 36;
      int viewTop = this.LIST_VIEW_TOP();
      int viewH = this.LIST_BOTTOM() - viewTop;
      if (contentH > viewH) {
         int scroll = tiers ? this.scrollTiers : this.scrollMobs;
         int maxScroll = Math.max(1, contentH - viewH);
         this.listScrollTrackW = 5;
         this.listScrollTrackX = 178;
         this.listScrollTrackY = viewTop;
         this.listScrollTrackH = viewH;
         this.listScrollThumbH = Math.max(12, (int)((float)viewH / contentH * viewH));
         int travel = Math.max(1, viewH - this.listScrollThumbH);
         this.listScrollThumbY = this.listScrollTrackY + (int)((float)scroll / maxScroll * travel);
         func_73734_a(
            this.listScrollTrackX,
            this.listScrollTrackY,
            this.listScrollTrackX + this.listScrollTrackW,
            this.listScrollTrackY + this.listScrollTrackH,
            1711276032
         );
         func_73734_a(
            this.listScrollTrackX,
            this.listScrollThumbY,
            this.listScrollTrackX + this.listScrollTrackW,
            this.listScrollThumbY + this.listScrollThumbH,
            -1437248171
         );
      }
   }

   private void drawMobDetail(BestiaryEntry e, int x, int y, int mouseX, int mouseY, float partialTicks) {
      IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
      if (prog == null) {
         this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a("bestiary.capability_missing_client", new Object[0])), x, y, 16733525);
      } else {
         int kills = prog.getKills(e.mobId);
         String name = this.distort(I18n.func_135052_a(e.nameKey, new Object[0]));
         float t = (this.player.field_70173_aa + partialTicks) / 10.0F;
         int pad = 6;
         int sectionGap = 6;
         int lineH = this.field_146289_q.field_78288_b;
         int pageRight = this.field_146294_l - 8;
         int pageW = Math.max(140, pageRight - x);
         int loreMinH = lineH * 3 + 8;
         int descW = (int)(pageW * 0.55F);
         int modelW = pageW - descW - 6;
         int bgX = x - 6;
         int bgY = y - 6;
         int bgW = pageW + 12;
         int bgH = 200;
         this.drawPanel(TEX_BG, bgX, bgY, bgW, bgH);
         String label = this.distort(I18n.func_135052_a("lore.srparasites.compendium", new Object[0]));
         int labelAvailW = Math.max(80, this.field_146294_l - x - 8);
         int curY = this.drawTextPanelLeftWiggle(TEX_LABEL_BG, label, x, y, labelAvailW, 3026478, mouseX, mouseY, t) + 2;
         String killsLabel = this.distort(I18n.func_135052_a("bestiary.kills_label", new Object[0]));
         String nameText = String.format("%s  (%s: %d)", name, killsLabel, kills);
         int nameAvailW = Math.max(80, this.field_146294_l - x - 8);
         curY = this.drawTextPanelLeftWiggle(TEX_NAME_BG, nameText, x, curY, nameAvailW, 3026478, mouseX, mouseY, t) + 6;
         int descLeft = x;
         int modelLeft = x + descW + 6;
         String descKey = "bestiary." + e.mobId.replace(':', '.') + ".desc";
         String desc = I18n.func_188566_a(descKey) ? this.distort(I18n.func_135052_a(descKey, new Object[0])) : "";
         List<String> lines = this.field_146289_q.func_78271_c(desc, descW);
         int textY = curY;
         if (lines.isEmpty()) {
            textY = curY + lineH;
         } else {
            for (String line : lines) {
               this.field_146289_q.func_78276_b(line, descLeft, textY, 13421772);
               textY += lineH + 2;
            }
         }

         int statsTop = textY + 6;
         int statMin = 3;
         if (this.selectedMob != null && this.selectedMob.minStatKill > 0) {
            statMin = this.selectedMob.minStatKill;
         }

         boolean hasStats = kills >= statMin;
         double hp = 0.0;
         double dmg = 0.0;
         String statsLine;
         if (hasStats) {
            hp = e.baseHp > 0 ? e.baseHp : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111267_a);
            dmg = e.baseDamage > 0.0F ? e.baseDamage : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111264_e);
            statsLine = this.distort(I18n.func_135052_a("bestiary.stats", new Object[]{String.valueOf((int)Math.round(hp)), String.valueOf((float)dmg)}));
         } else {
            statsLine = this.distort(I18n.func_135052_a("bestiary.more_info_at_n", new Object[]{statMin}));
         }

         int statsPadX = 12;
         int statsPadY = 6;
         int statsTextW = this.field_146289_q.func_78256_a(statsLine);
         int statsW = Math.min(descW, statsTextW + 24);
         int statsPanelH = this.field_146289_q.field_78288_b + 6;
         boolean hoverStats = mouseX >= descLeft && mouseX <= descLeft + statsW && mouseY >= statsTop && mouseY <= statsTop + statsPanelH;
         float statsWiggleX = 0.0F;
         float statsWiggleY = 0.0F;
         if (hoverStats) {
            statsWiggleX = (float)Math.sin(t * 2.0F) * 0.4F;
            statsWiggleY = (float)Math.sin(t * 3.0F) * 0.6F;
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b(statsWiggleX, statsWiggleY, 0.0F);
         this.drawPanel(TEX_STATS_BG, descLeft, statsTop, statsW, statsPanelH);
         int statsTextX = descLeft + (statsW - statsTextW) / 2;
         int statsTextY = statsTop + (statsPanelH - this.field_146289_q.field_78288_b) / 2;
         this.field_146289_q.func_78276_b(statsLine, statsTextX, statsTextY, 3026478);
         GlStateManager.func_179121_F();
         int leftBottom = statsTop + statsPanelH;
         if (!this.isBgScreenActive()) {
            int dropTop = leftBottom + 6;
            int dropPadX = 6;
            int dropPadY = 6;
            String dropTitle = this.distort(
               I18n.func_188566_a("bestiary.drops")
                  ? I18n.func_135052_a("bestiary.drops", new Object[0])
                  : I18n.func_135052_a("bestiary.drops_fallback", new Object[0])
            );
            int dropLineH = this.field_146289_q.field_78288_b;
            int lineStep = Math.max(dropLineH + 4, 18);
            int bottomLimitY = this.field_146295_m - 18 - 22;
            int maxDropsBottom = bottomLimitY - 6;
            int headerH = dropPadY + lineStep;
            int availableForText = maxDropsBottom - dropTop - (headerH + dropPadY);
            int visibleLines = Math.max(2, availableForText / lineStep);
            int textAreaH = visibleLines * lineStep;
            int dropBoxH = dropPadY + lineStep + textAreaH + dropPadY;
            int iconSize = 16;
            int iconGap = 4;
            int textLeftInset = 20;
            boolean hoverDrops = mouseX >= descLeft && mouseX <= descLeft + descW && mouseY >= dropTop && mouseY <= dropTop + dropBoxH;
            float dropWiggleX = 0.0F;
            float dropWiggleY = 0.0F;
            if (hoverDrops) {
               dropWiggleX = (float)Math.sin(t * 2.0F) * 0.4F;
               dropWiggleY = (float)Math.sin(t * 3.0F) * 0.6F;
            }

            int wX = (int)dropWiggleX;
            int wY = (int)dropWiggleY;
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(dropWiggleX, dropWiggleY, 0.0F);
            this.drawPanel(TEX_DROP_BG, descLeft, dropTop, descW, dropBoxH);
            int titleX = descLeft + dropPadX;
            int titleY = dropTop + dropPadY;
            this.field_146289_q.func_78276_b(this.distort(dropTitle + ":"), titleX, titleY, 3026478);
            int textX = descLeft + dropPadX;
            int dropTextY = titleY + lineStep;
            int textW = descW - dropPadX * 2;
            this.dropsViewX = textX + wX;
            this.dropsViewY = dropTextY + wY;
            this.dropsViewW = textW;
            this.dropsViewH = textAreaH;
            List<ParasitesPage.DropEntry> drops = Collections.emptyList();
            if (kills < statMin) {
               String msg = this.distort(
                  I18n.func_188566_a("bestiary.loot_unlocks_at_n")
                     ? I18n.func_135052_a("bestiary.loot_unlocks_at_n", new Object[]{String.valueOf(statMin)})
                     : I18n.func_135052_a("bestiary.loot_unlocks_at_n_fallback", new Object[]{String.valueOf(statMin)})
               );
               this.enableScissor(this.dropsViewX, this.dropsViewY, this.dropsViewW, this.dropsViewH);
               this.field_146289_q.func_78276_b(msg, textX, dropTextY, 5592405);
               this.disableScissor();
               if (this.dropsContentH > this.dropsViewH) {
                  this.dropsScrollTrackW = 5;
                  this.dropsScrollTrackX = this.dropsViewX + this.dropsViewW - this.dropsScrollTrackW;
                  this.dropsScrollTrackY = this.dropsViewY;
                  this.dropsScrollTrackH = this.dropsViewH;
                  this.dropsScrollThumbH = Math.max(12, (int)((float)this.dropsViewH / this.dropsContentH * this.dropsViewH));
                  int maxScroll = Math.max(1, this.dropsContentH - this.dropsViewH);
                  int travel = Math.max(1, this.dropsViewH - this.dropsScrollThumbH);
                  this.dropsScrollThumbY = this.dropsScrollTrackY + (int)((float)this.dropsScrollPx / maxScroll * travel);
                  func_73734_a(
                     this.dropsScrollTrackX,
                     this.dropsScrollTrackY,
                     this.dropsScrollTrackX + this.dropsScrollTrackW,
                     this.dropsScrollTrackY + this.dropsScrollTrackH,
                     1711276032
                  );
                  func_73734_a(
                     this.dropsScrollTrackX,
                     this.dropsScrollThumbY,
                     this.dropsScrollTrackX + this.dropsScrollTrackW,
                     this.dropsScrollThumbY + this.dropsScrollThumbH,
                     -1437248171
                  );
               }

               this.dropsContentH = lineStep;
               this.dropsScrollPx = 0;
            } else {
               drops = this.getDropsForMob(e.mobId);
               int contentLines = Math.max(1, drops.size());
               this.dropsContentH = contentLines * lineStep;
               this.clampDropsScroll();
               this.enableScissor(this.dropsViewX, this.dropsViewY, this.dropsViewW, this.dropsViewH);
               int drawY = dropTextY - this.dropsScrollPx;
               if (drops.isEmpty()) {
                  String none = this.distort(
                     I18n.func_188566_a("bestiary.drops.none")
                        ? I18n.func_135052_a("bestiary.drops.none", new Object[0])
                        : I18n.func_135052_a("bestiary.drops.none_fallback", new Object[0])
                  );
                  this.field_146289_q.func_78276_b(none, textX, drawY, 5592405);
               } else {
                  for (int i = 0; i < drops.size(); i++) {
                     ParasitesPage.DropEntry d0 = drops.get(i);
                     int yLineBot = drawY + dropLineH;
                     if (yLineBot >= dropTextY && drawY <= dropTextY + this.dropsViewH) {
                        ItemStack st = stackFromId(d0.itemId);
                        if (!st.func_190926_b()) {
                           int iconY = drawY + (lineStep - 16) / 2;
                           this.renderItemIconSway(st, textX, iconY, t, i);
                        }

                        String displayName = !st.func_190926_b() ? forceBlack(st.func_82833_r()) : d0.itemId;
                        String lootingTag = d0.looting ? " " + I18n.func_135052_a("bestiary.loot_looting", new Object[0]) : "";
                        String rowPlain = displayName + " x" + d0.amount + " (" + d0.chance + "%)" + lootingTag;
                        int avail = Math.max(40, this.dropsViewW - 20);
                        String rowDraw = this.isJumbled ? GuiDistortionHelper.jamText(rowPlain) : this.field_146289_q.func_78269_a(rowPlain, avail);
                        int textYRow = drawY + (lineStep - dropLineH) / 2;
                        this.field_146289_q.func_78276_b(rowDraw, textX + 20, textYRow, 3026478);
                     }

                     drawY += lineStep;
                  }
               }

               this.disableScissor();
            }

            if (this.dropsContentH > this.dropsViewH) {
               this.dropsScrollTrackW = 5;
               this.dropsScrollTrackX = this.dropsViewX + this.dropsViewW - this.dropsScrollTrackW;
               this.dropsScrollTrackY = this.dropsViewY;
               this.dropsScrollTrackH = this.dropsViewH;
               this.dropsScrollThumbH = Math.max(12, (int)((float)this.dropsViewH / this.dropsContentH * this.dropsViewH));
               int maxScroll = Math.max(1, this.dropsContentH - this.dropsViewH);
               int travel = Math.max(1, this.dropsViewH - this.dropsScrollThumbH);
               this.dropsScrollThumbY = this.dropsScrollTrackY + (int)((float)this.dropsScrollPx / maxScroll * travel);
               func_73734_a(
                  this.dropsScrollTrackX,
                  this.dropsScrollTrackY,
                  this.dropsScrollTrackX + this.dropsScrollTrackW,
                  this.dropsScrollTrackY + this.dropsScrollTrackH,
                  1711276032
               );
               func_73734_a(
                  this.dropsScrollTrackX,
                  this.dropsScrollThumbY,
                  this.dropsScrollTrackX + this.dropsScrollTrackW,
                  this.dropsScrollThumbY + this.dropsScrollThumbH,
                  -1437248171
               );
            }

            GlStateManager.func_179121_F();
            leftBottom = dropTop + dropBoxH;
         }

         int modelH = Math.max(90, leftBottom - curY);
         this.modelRectX = modelLeft;
         this.modelRectY = curY;
         this.modelRectW = modelW;
         this.modelRectH = modelH;
         int cx = modelLeft + modelW / 2 + (this.isBgScreenActive() ? this.modelPanX : 0);
         int cy = curY + modelH / 2 + (this.isBgScreenActive() ? this.modelPanY : 0);
         int innerMargin = 12;
         int baseW = Math.max(40, modelW - 24);
         int baseH = Math.max(40, modelH - 24);
         float extraShrink = 0.88F;
         int zoomW = Math.max(10, (int)(baseW * 0.88F * this.modelZoom));
         int zoomH = Math.max(10, (int)(baseH * 0.88F * this.modelZoom));
         if (this.isBgScreenActive()) {
            func_73734_a(0, 0, this.field_146294_l, this.field_146295_m, this.getBgScreenColor());
         } else {
            this.drawPanel(TEX_MODEL_BG, modelLeft, curY, modelW, modelH);
         }

         float pitch = this.manualPitchDeg;
         float yaw = this.autoRotateModel ? this.spinDeg : this.manualYawDeg;
         if (this.isBgScreenActive() && this.poseControlsInit && !this.tfYaw.func_146206_l()) {
            this.suppressPoseFieldUpdates = true;

            try {
               this.tfYaw.func_146180_a(String.format(Locale.US, "%.1f", yaw));
            } finally {
               this.suppressPoseFieldUpdates = false;
            }
         }

         this.renderEntityPreviewDetail(e.mobId, cx, cy, zoomW, zoomH, yaw, pitch);
         if (!this.isBgScreenActive()) {
            float a2 = this.page == ParasitesPage.BestiaryPage.MOB_DETAIL ? smoothstep(this.mobDetailAnim) : 1.0F;
            int warnH = 18;
            int warnW = this.field_146294_l;
            int warnX = 0;
            int warnY = this.field_146295_m - warnH;
            int warnOffDown = this.field_146295_m + 60;
            int warnAnimY = warnY + (int)(warnOffDown * (1.0F - a2));
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, a2);
            this.drawAnimatedStripVertical(TEX_WARNING_SHEET, 80, warnX, warnAnimY, warnW, warnH, 400, 16, 20);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
         }
      }
   }

   private boolean isMouseOverAnyButton(int mouseX, int mouseY) {
      for (GuiButton b : this.field_146292_n) {
         if (b != null
            && b.field_146125_m
            && mouseX >= b.field_146128_h
            && mouseX < b.field_146128_h + b.field_146120_f
            && mouseY >= b.field_146129_i
            && mouseY < b.field_146129_i + b.field_146121_g) {
            return true;
         }
      }

      return false;
   }

   private static String forceBlack(String s) {
      if (s != null && !s.isEmpty()) {
         return s.length() >= 2 && s.charAt(0) == 167 ? "§0" + s.substring(2) : "§0" + s;
      } else {
         return "";
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

   private void resetModelPan() {
      this.modelPanActive = false;
      this.modelPanX = 0;
      this.modelPanY = 0;
      this.panStartMouseX = this.panStartMouseY = 0;
      this.panStartX = this.panStartY = 0;
   }

   private int drawTextPanelLeftWiggle(ResourceLocation tex, String text, int leftX, int topY, int maxWidth, int textColor, int mouseX, int mouseY, float t) {
      int padX = 12;
      int padY = 8;
      int textW = this.field_146289_q.func_78256_a(text);
      int panelW = Math.min(Math.max(80, maxWidth), textW + 24);
      int panelH = this.field_146289_q.field_78288_b + 8;
      boolean hovered = mouseX >= leftX && mouseX <= leftX + panelW && mouseY >= topY && mouseY <= topY + panelH;
      float wiggleX = 0.0F;
      float wiggleY = 0.0F;
      if (hovered) {
         wiggleX = (float)Math.sin(t * 2.0F) * 0.4F;
         wiggleY = (float)Math.sin(t * 3.0F) * 0.6F;
      }

      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(wiggleX, wiggleY, 0.0F);
      this.drawPanel(tex, leftX, topY, panelW, panelH);
      this.field_146289_q.func_78276_b(text, leftX + 12, topY + (panelH - this.field_146289_q.field_78288_b) / 2, textColor);
      GlStateManager.func_179121_F();
      return topY + panelH;
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

   private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, float pitchDeg) {
      this.renderEntityPreview(mobId, cx, cy, boxW, boxH, yawDeg, pitchDeg, 0, 1.0F);
   }

   private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, float pitchDeg, int marginPx, float extraShrink) {
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
         ent.func_70107_b(0.0, 0.0, 0.0);
         ent.field_70169_q = ent.field_70165_t;
         ent.field_70167_r = ent.field_70163_u;
         ent.field_70166_s = ent.field_70161_v;
         ent.field_70142_S = ent.field_70165_t;
         ent.field_70137_T = ent.field_70163_u;
         ent.field_70136_U = ent.field_70161_v;
         ent.field_70173_aa = this.player.field_70173_aa;
         if (this.renderRunThisCall) {
            float age = this.player.field_70173_aa + this.field_146297_k.func_184121_ak();
            ent.field_184619_aG = age * 0.9F;
            ent.field_70721_aZ = 1.2F;
            ent.field_184618_aE = ent.field_70721_aZ;
            ent.func_70031_b(true);
         } else {
            ent.field_184619_aG = 0.0F;
            ent.field_70721_aZ = 0.0F;
            ent.field_184618_aE = 0.0F;
            ent.func_70031_b(false);
         }

         ent.field_70125_A = 0.0F;
         ent.field_70127_C = 0.0F;
         ent.field_70761_aq = yawDeg;
         ent.field_70760_ar = yawDeg;
         ent.field_70759_as = yawDeg;
         ent.field_70758_at = yawDeg;
         ent.field_70177_z = yawDeg;
         ent.field_70126_B = yawDeg;
         int innerW = Math.max(0, boxW - (marginPx << 1));
         int innerH = Math.max(0, boxH - (marginPx << 1));
         float scale = this.computeAutoScale(ent, innerW, innerH);
         scale *= SRPBestiaryRegistry.getRenderScale(mobId);
         if (extraShrink > 0.0F) {
            scale *= extraShrink;
         }

         GlStateManager.func_179094_E();

         try {
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179126_j();
            GlStateManager.func_179140_f();
            RenderHelper.func_74519_b();
            GlStateManager.func_179086_m(256);
            GlStateManager.func_179109_b(cx, cy, 150.0F);
            GlStateManager.func_179152_a(-scale, scale, scale);
            GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179114_b(pitchDeg, 1.0F, 0.0F, 0.0F);
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
            GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
            GlStateManager.func_179090_x();
            GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
            GlStateManager.func_179098_w();
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

   private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, int marginPx, float extraShrink) {
      this.renderEntityPreview(mobId, cx, cy, boxW, boxH, yawDeg, 0.0F, marginPx, extraShrink);
   }

   public boolean func_73868_f() {
      return false;
   }

   static {
      Function<String, ItemStack> byId = id -> {
         Item itx = Item.func_111206_d(id);
         return itx != null ? new ItemStack(itx) : ItemStack.field_190927_a;
      };
      TIER_ICONS.put(ParasiteTier.INBORN, byId.apply("srparasites:lurecomponent1"));
      TIER_ICONS.put(ParasiteTier.ASSIMILATED, byId.apply("srparasites:lurecomponent3"));
      TIER_ICONS.put(ParasiteTier.HIJACKED, byId.apply("srparasites:hijacked_drop"));
      TIER_ICONS.put(ParasiteTier.FERAL, byId.apply("srparasites:lurecomponent2"));
      TIER_ICONS.put(ParasiteTier.CRUDE, byId.apply("srparasites:itemmobspawner_host"));
      TIER_ICONS.put(ParasiteTier.PRIMITIVE, byId.apply("srparasites:ada_reeker_drop"));
      TIER_ICONS.put(ParasiteTier.ADAPTED, byId.apply("srparasites:ada_longarms_drop"));
      TIER_ICONS.put(ParasiteTier.NEXUS, byId.apply("srparasites:beckon_drop"));
      TIER_ICONS.put(ParasiteTier.DETERRENT, byId.apply("srparasites:dispatcher_drop"));
      TIER_ICONS.put(ParasiteTier.PURE, byId.apply("srparasites:living_core"));
      TIER_ICONS.put(ParasiteTier.PREEMINENT, byId.apply("srparasites:itemthrow"));
      TIER_ICONS.put(ParasiteTier.ANCIENT, byId.apply("srparasites:itemmobspawner_oronco"));
      TIER_ICONS.put(ParasiteTier.ABOMINATION, byId.apply("srparasites:ada_vermin_drop"));
      TIER_ICONS.put(ParasiteTier.ASSIMARA, byId.apply("srparasites:ada_viscera_drop"));
      TIER_ICONS.put(ParasiteTier.DERIVED, byId.apply("srparasites:alveoligrowth"));
      TIER_ICONS.put(ParasiteTier.WALKING_HEAD, byId.apply("srparasites:assimilated_flesh"));
      Item it = Item.func_111206_d("srparasites:itemtab");
      DEFAULT_TIER_ICON = it != null ? new ItemStack(it) : ItemStack.field_190927_a;
   }

   private class AnimatedButton extends GuiButton {
      final int baseX;
      final int baseY;
      final int group;

      AnimatedButton(int id, int x, int y, int w, int h, String txt, int group) {
         super(id, x, y, w, h, txt);
         this.baseX = x;
         this.baseY = y;
         this.group = group;
      }

      public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         if (this.field_146125_m) {
            float a;
            if (ParasitesPage.this.page == ParasitesPage.BestiaryPage.MOB_DETAIL) {
               a = ParasitesPage.smoothstep(ParasitesPage.this.mobDetailAnim);
            } else if (ParasitesPage.this.page == ParasitesPage.BestiaryPage.MOB_LIST) {
               a = ParasitesPage.smoothstep(ParasitesPage.this.mobListAnim);
            } else if (ParasitesPage.this.page == ParasitesPage.BestiaryPage.PARASITES) {
               a = ParasitesPage.smoothstep(ParasitesPage.this.tierListAnim);
            } else {
               a = 1.0F;
            }

            int x = this.baseX;
            int y = this.baseY;
            if (ParasitesPage.this.page == ParasitesPage.BestiaryPage.MOB_DETAIL
               || ParasitesPage.this.page == ParasitesPage.BestiaryPage.MOB_LIST
               || ParasitesPage.this.page == ParasitesPage.BestiaryPage.PARASITES) {
               if (this.group == 0) {
                  int offUp = -(this.baseY + this.field_146121_g + 8);
                  y = this.baseY + (int)(offUp * (1.0F - a));
               } else if (this.group == 1) {
                  int offDown = mc.field_71440_d + 60;
                  y = this.baseY + (int)(offDown * (1.0F - a));
               }
            }

            int ox = this.field_146128_h;
            int oy = this.field_146129_i;
            this.field_146128_h = x;
            this.field_146129_i = y;
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, a);
            super.func_191745_a(mc, mouseX, mouseY, partialTicks);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
            this.field_146128_h = ox;
            this.field_146129_i = oy;
         }
      }
   }

   private static enum BestiaryPage {
      HOME,
      PARASITES,
      MOB_LIST,
      MOB_DETAIL;
   }

   private static class DropEntry {
      final String itemId;
      final int chance;
      final int amount;
      final boolean looting;

      DropEntry(String itemId, int chance, int amount, boolean looting) {
         this.itemId = itemId;
         this.chance = chance;
         this.amount = amount;
         this.looting = looting;
      }
   }

   private static class ListButton extends GuiButton {
      final int rowTopY;

      ListButton(int id, int x, int rowTopY, int w, int h, String txt) {
         super(id, x, rowTopY, w, h, txt);
         this.rowTopY = rowTopY;
      }
   }

   private static class MobListEntryButton extends ParasitesPage.ListButton {
      MobListEntryButton(int id, int x, int rowTopY, int w, int h, String txt) {
         super(id, x, rowTopY, w, h, txt);
      }

      public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         if (this.field_146125_m) {
            GuiScreen s = mc.field_71462_r;
            if (!(s instanceof ParasitesPage)) {
               super.func_191745_a(mc, mouseX, mouseY, partialTicks);
            } else {
               ParasitesPage g = (ParasitesPage)s;
               float a = ParasitesPage.smoothstep(g.mobListAnim);
               int leftOffX = -230;
               int xOff = (int)(leftOffX * (1.0F - a));
               int ox = this.field_146128_h;
               int oy = this.field_146129_i;
               this.field_146128_h = ox + xOff;
               GlStateManager.func_179094_E();
               GlStateManager.func_179147_l();
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, a);
               super.func_191745_a(mc, mouseX - xOff, mouseY, partialTicks);
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179084_k();
               GlStateManager.func_179121_F();
               this.field_146128_h = ox;
               this.field_146129_i = oy;
            }
         }
      }
   }

   private static class TierButton extends ParasitesPage.ListButton {
      final ParasiteTier tier;
      final ItemStack icon;

      TierButton(int id, int x, int rowTopY, int w, int h, String text, ParasiteTier tier, ItemStack icon) {
         super(id, x, rowTopY, w, h, text);
         this.tier = tier;
         this.icon = icon == null ? ItemStack.field_190927_a : icon;
      }

      public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         if (this.field_146125_m) {
            GuiScreen s = mc.field_71462_r;
            float a = 1.0F;
            int xOff = 0;
            if (s instanceof ParasitesPage) {
               ParasitesPage g = (ParasitesPage)s;
               if (g.page == ParasitesPage.BestiaryPage.PARASITES) {
                  a = ParasitesPage.smoothstep(g.tierListAnim);
                  xOff = g.getTierPageXOff();
               }
            }

            int ox = this.field_146128_h;
            int oy = this.field_146129_i;
            this.field_146128_h = ox + xOff;
            boolean hover = mouseX >= this.field_146128_h
               && mouseY >= this.field_146129_i
               && mouseX < this.field_146128_h + this.field_146120_f
               && mouseY < this.field_146129_i + this.field_146121_g;
            int oxx = this.field_146128_h;
            if (hover) {
               this.field_146128_h = oxx + 2;
            }

            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, a);
            super.func_191745_a(mc, mouseX - xOff, mouseY, partialTicks);
            if (!this.icon.func_190926_b()) {
               int iconX = this.field_146128_h - 5;
               int iconY = this.field_146129_i - 5;
               RenderHelper.func_74520_c();
               mc.func_175599_af().func_180450_b(this.icon, iconX, iconY);
               RenderHelper.func_74518_a();
            }

            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
            this.field_146128_h = ox;
            this.field_146129_i = oy;
         }
      }
   }
}
