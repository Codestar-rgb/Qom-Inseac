/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.BestiaryEntry;
import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.BlocksPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.CelestialEventsPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiBestiary;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.StatusEffectsPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.SystemsPage;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiaryRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ParasitesPage
extends GuiScreen {
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
    private BestiaryPage page = BestiaryPage.HOME;
    private ParasiteTier selectedTier = null;
    private BestiaryEntry selectedMob = null;
    private boolean suppressPoseFieldUpdates = false;
    private final List<ParasiteTier> visibleTiers = new ArrayList<ParasiteTier>();
    private final List<BestiaryEntry> visibleMobs = new ArrayList<BestiaryEntry>();
    private final Map<String, EntityLivingBase> entityCache = new HashMap<String, EntityLivingBase>();
    private float spinDeg = 0.0f;
    private int scrollTiers = 0;
    private int scrollMobs = 0;
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
    private static final float UI_ICON_SHRINK = 0.92f;
    private static final int UI_DARK_GRAY = 0x2E2E2E;
    private static final int BTN_HOME = 1000;
    private static final int BTN_TIERS = 1001;
    private static final int BTN_MOBLIST = 1002;
    private static final int BTN_LORE = 1012;
    private boolean lorePopupOpen = false;
    private final GuiScreen parent;
    private float tierListAnim = 1.0f;
    private float tierListAnimTarget = 1.0f;
    private long tierListAnimLastMs = 0L;
    private BestiaryPage pendingPage3 = null;
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
    private float manualYawDeg = 0.0f;
    private int dragStartMouseX = 0;
    private float dragStartYawDeg = 0.0f;
    private boolean autoRotateModel = true;
    private float manualPitchDeg = 0.0f;
    private int dragStartMouseY = 0;
    private float dragStartPitchDeg = 0.0f;
    private static final int BTN_ROTATE = 1004;
    private int modelRectX;
    private int modelRectY;
    private int modelRectW;
    private int modelRectH;
    private float modelZoom = 1.0f;
    private static final float MODEL_ZOOM_MIN = 0.35f;
    private static final float MODEL_ZOOM_MAX = 2.75f;
    private static final int BTN_GREENSCREEN = 1005;
    private boolean modelGreenscreen = false;
    private static final int BTN_APPLY_POSE = 1010;
    private static final int BTN_RESET_POSE = 1011;
    private float loreAnim = 0.0f;
    private float loreAnimTarget = 0.0f;
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
    private float mobDetailAnim = 1.0f;
    private float mobDetailAnimTarget = 1.0f;
    private long mobDetailAnimLastMs = 0L;
    private float mobListAnim = 1.0f;
    private float mobListAnimTarget = 1.0f;
    private long mobListAnimLastMs = 0L;
    private boolean tierListEnterFromRight = false;
    private BestiaryPage pendingPage2 = null;
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
    private BestiaryPage pendingPage = null;
    private ParasiteTier pendingTier = null;
    private BestiaryEntry pendingMob = null;
    private int lastW = -1;
    private int lastH = -1;
    private boolean poseControlsInit = false;
    private static final boolean DEBUG_DROPS = false;
    private static final Map<String, String> CFG_CATEGORY_TO_FAMILY = new HashMap<String, String>();
    private boolean dropsLoaded = false;
    private long dropsLastModified = -1L;
    private final Map<String, List<DropEntry>> dropsByCategory = new HashMap<String, List<DropEntry>>();
    private final Set<String> knownCategories = new HashSet<String>();
    private boolean modelPanActive = false;
    private int panStartMouseX = 0;
    private int panStartMouseY = 0;
    private int modelPanX = 0;
    private int modelPanY = 0;
    private int panStartX = 0;
    private int panStartY = 0;
    private static final Map<ParasiteTier, ItemStack> TIER_ICONS = new HashMap<ParasiteTier, ItemStack>();
    private static final ItemStack DEFAULT_TIER_ICON;

    private int LIST_VIEW_TOP() {
        return 68;
    }

    private int LIST_BOTTOM() {
        return this.field_146295_m - 40;
    }

    private void startMobListExit(BestiaryPage nextPage, ParasiteTier nextTier, BestiaryEntry nextMob) {
        this.pendingPage2 = nextPage;
        this.pendingTier2 = nextTier;
        this.pendingMob2 = nextMob;
        this.mobListAnimTarget = 0.0f;
        this.mobListAnimLastMs = 0L;
        if (nextPage == BestiaryPage.MOB_DETAIL) {
            this.mobDetailAnim = 0.0f;
            this.mobDetailAnimTarget = 1.0f;
            this.mobDetailAnimLastMs = 0L;
        }
    }

    private void startTierListExit(BestiaryPage nextPage, ParasiteTier nextTier, BestiaryEntry nextMob) {
        this.pendingPage3 = nextPage;
        this.pendingTier3 = nextTier;
        this.pendingMob3 = nextMob;
        this.tierListAnimTarget = 0.0f;
        this.tierListAnimLastMs = 0L;
    }

    public ParasitesPage(EntityPlayer player, GuiScreen parent) {
        this.player = player;
        this.parent = parent;
    }

    public void openParasitesRoot() {
        this.page = BestiaryPage.PARASITES;
        this.selectedTier = null;
        this.selectedMob = null;
        this.visibleTiers.clear();
        this.visibleMobs.clear();
        this.tierListEnterFromRight = false;
        this.tierListAnim = 0.0f;
        this.tierListAnimTarget = 1.0f;
        this.tierListAnimLastMs = 0L;
    }

    private static float clamp01(float v) {
        return Math.max(0.0f, Math.min(1.0f, v));
    }

    private static float smoothstep(float a) {
        a = ParasitesPage.clamp01(a);
        return a * a * (3.0f - 2.0f * a);
    }

    private void startMobDetailExit(BestiaryPage nextPage, ParasiteTier nextTier, BestiaryEntry nextMob) {
        this.pendingPage = nextPage;
        this.pendingTier = nextTier;
        this.pendingMob = nextMob;
        this.mobDetailAnimTarget = 0.0f;
        this.mobDetailAnimLastMs = 0L;
        this.loreAnimTarget = 0.0f;
        this.lorePopupClosingRefresh = false;
        if (nextPage == BestiaryPage.MOB_LIST) {
            this.mobListAnim = 0.0f;
            this.mobListAnimTarget = 1.0f;
            this.mobListAnimLastMs = 0L;
        }
    }

    private File getMobsCfgFile() {
        return new File(this.field_146297_k.field_71412_D, "config/srparasites/SRParasitesMobs.cfg");
    }

    private void drawAnimatedStripVertical(ResourceLocation sheet, int frames, int x, int y, int w, int h, int frameW, int frameH, int frameDurationMs) {
        long nowMs = System.nanoTime() / 1000000L;
        long index = nowMs / (long)Math.max(1, frameDurationMs) % (long)Math.max(1, frames);
        boolean u = false;
        int v = (int)index * frameH;
        int sheetW = frameW;
        int sheetH = frames * frameH;
        this.field_146297_k.func_110434_K().func_110577_a(sheet);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Gui.func_152125_a((int)x, (int)y, (float)((float)u), (float)v, (int)frameW, (int)frameH, (int)w, (int)h, (float)sheetW, (float)sheetH);
    }

    private void applyTierScrollLayout() {
        int contentH = this.visibleTiers.size() * 36;
        int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - this.LIST_VIEW_TOP()));
        this.scrollTiers = Math.max(0, Math.min(this.scrollTiers, maxScroll));
        for (GuiButton b : this.field_146292_n) {
            boolean inView;
            if (!(b instanceof ListButton) || b.field_146127_k < 100 || b.field_146127_k >= 200) continue;
            ListButton lb = (ListButton)b;
            int rowY = lb.rowTopY - this.scrollTiers;
            b.field_146129_i = rowY + 4;
            b.field_146125_m = inView = rowY + 28 > this.LIST_VIEW_TOP() && rowY < this.LIST_BOTTOM();
        }
    }

    private void applyMobScrollLayout() {
        int contentH = this.visibleMobs.size() * 36;
        int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - this.LIST_VIEW_TOP()));
        this.scrollMobs = Math.max(0, Math.min(this.scrollMobs, maxScroll));
        for (GuiButton b : this.field_146292_n) {
            boolean inView;
            if (!(b instanceof ListButton) || b.field_146127_k < 200 || b.field_146127_k >= 300) continue;
            ListButton lb = (ListButton)b;
            int rowY = lb.rowTopY - this.scrollMobs;
            b.field_146129_i = rowY + 4;
            b.field_146125_m = inView = rowY + 28 > this.LIST_VIEW_TOP() && rowY < this.LIST_BOTTOM();
        }
    }

    private void drawPanel(ResourceLocation tex, int x, int y, int w, int h) {
        this.field_146297_k.func_110434_K().func_110577_a(tex);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Gui.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)256, (int)256, (int)w, (int)h, (float)256.0f, (float)256.0f);
    }

    public void func_146274_d() throws IOException {
        int my;
        int mx;
        super.func_146274_d();
        int dWheel = Mouse.getEventDWheel();
        if (dWheel == 0) {
            return;
        }
        if (this.lorePopupOpen) {
            int wheelDir = (int)Math.signum(dWheel);
            int step = 14;
            this.loreScrollPx += (wheelDir < 0 ? step : -step) * 3;
            this.clampLoreScroll();
            return;
        }
        int wheelDir = (int)Math.signum(dWheel);
        if (!this.lorePopupOpen && this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && dWheel != 0) {
            boolean overDrops;
            mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            boolean bl = overDrops = mx >= this.dropsViewX && mx < this.dropsViewX + this.dropsViewW && my >= this.dropsViewY && my < this.dropsViewY + this.dropsViewH;
            if (overDrops && this.dropsContentH > this.dropsViewH) {
                int step = 14;
                this.dropsScrollPx += (wheelDir < 0 ? step : -step) * 3;
                this.clampDropsScroll();
                return;
            }
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null) {
            boolean overModel;
            mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            boolean bl = overModel = mx >= this.modelRectX && mx < this.modelRectX + this.modelRectW && my >= this.modelRectY && my < this.modelRectY + this.modelRectH;
            if (this.modelGreenscreen || overModel) {
                float step = 0.1f;
                this.modelZoom = wheelDir > 0 ? (this.modelZoom += step) : (this.modelZoom -= step);
                this.modelZoom = Math.max(0.35f, Math.min(2.75f, this.modelZoom));
                this.syncPoseFieldsFromState();
                return;
            }
        }
        int delta = (int)Math.signum(dWheel) * -18;
        switch (this.page) {
            case PARASITES: {
                this.scrollTiers += delta * 2;
                this.applyTierScrollLayout();
                break;
            }
            case MOB_LIST: {
                this.scrollMobs += delta * 2;
                this.applyMobScrollLayout();
                break;
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
        Keyboard.enableRepeatEvents((boolean)true);
        this.field_146292_n.clear();
        if (!this.syncRequested) {
            this.syncRequested = true;
            BestiaryNetwork.CH.sendToServer((IMessage)new PacketBestiaryRequest());
        }
        int navY = 10;
        int navHomeX = 10;
        int navHomeW = 60;
        int navTiersX = 74;
        int navTiersW = 60;
        int navMobListX = 138;
        int navMobListW = 80;
        switch (this.page) {
            case HOME: {
                int cx = this.field_146294_l / 2;
                this.field_146292_n.add(new GuiButton(10, cx - 60, 70, 120, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.parasites", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(11, cx - 60, 95, 120, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.blocks", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(12, cx - 60, 120, 120, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.celestial", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(14, cx - 60, 170, 120, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.systems", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(13, cx - 60, 145, 120, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.effects", (Object[])new Object[0]))));
                break;
            }
            case PARASITES: {
                this.visibleMobs.clear();
                this.visibleTiers.clear();
                IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
                if (prog != null) {
                    for (ParasiteTier t : GuiBestiary.getDisplayOrderLocalized()) {
                        if (!prog.isTierSeen(t)) continue;
                        this.visibleTiers.add(t);
                    }
                }
                int rowTop = this.LIST_VIEW_TOP();
                for (int i = 0; i < this.visibleTiers.size(); ++i) {
                    ParasiteTier t = this.visibleTiers.get(i);
                    String tierLabel = this.distort(I18n.func_135052_a((String)ParasitesPage.tierLangKey(t), (Object[])new Object[0]));
                    ItemStack icon = TIER_ICONS.get((Object)t);
                    if (icon == null || icon.func_190926_b()) {
                        icon = DEFAULT_TIER_ICON;
                    }
                    this.field_146292_n.add(new TierButton(100 + i, 30, rowTop, 140, 20, tierLabel, t, icon));
                    rowTop += 36;
                }
                this.scrollTiers = 0;
                this.applyTierScrollLayout();
                this.field_146292_n.add(new AnimatedButton(1000, 10, 10, 60, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.home", (Object[])new Object[0])), 0));
                break;
            }
            case MOB_LIST: {
                this.visibleMobs.clear();
                IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
                if (prog != null && this.selectedTier != null) {
                    for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
                        if (e.tier != this.selectedTier || !ParasitesPage.isKnown(prog, e)) continue;
                        this.visibleMobs.add(e);
                    }
                }
                int rowTop = this.LIST_VIEW_TOP();
                for (int i = 0; i < this.visibleMobs.size(); ++i) {
                    BestiaryEntry e = this.visibleMobs.get(i);
                    String name = this.distort(I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]));
                    this.field_146292_n.add(new MobListEntryButton(200 + i, 30, rowTop, 140, 20, name));
                    rowTop += 36;
                }
                this.applyMobScrollLayout();
                this.field_146292_n.add(new AnimatedButton(1000, 10, 10, 60, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.home", (Object[])new Object[0])), 0));
                this.field_146292_n.add(new AnimatedButton(1001, 74, 10, 60, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.tiers", (Object[])new Object[0])), 0));
                break;
            }
            case MOB_DETAIL: {
                if (this.modelGreenscreen) {
                    int bottomY = this.field_146295_m - 24;
                    int gap = 6;
                    int gsW = 140;
                    int rotW = 130;
                    int runW = 90;
                    int x = this.field_146294_l - 10;
                    this.field_146292_n.add(new GuiButton(1005, x -= gsW, bottomY, gsW, 20, this.distort(I18n.func_135052_a((String)"bestiary.controls.greenscreen.on", (Object[])new Object[0]))));
                    x -= gap;
                    this.field_146292_n.add(new GuiButton(1004, x -= rotW, bottomY, rotW, 20, this.autoRotateModel ? this.distort(I18n.func_135052_a((String)"bestiary.controls.rotation.on", (Object[])new Object[0])) : this.distort(I18n.func_135052_a((String)"bestiary.controls.rotation.off", (Object[])new Object[0]))));
                    x -= gap;
                    this.field_146292_n.add(new GuiButton(1003, x -= runW, bottomY, runW, 20, this.modelRun ? this.distort(I18n.func_135052_a((String)"bestiary.controls.run.on", (Object[])new Object[0])) : this.distort(I18n.func_135052_a((String)"bestiary.controls.run.off", (Object[])new Object[0]))));
                    break;
                }
                this.field_146292_n.add(new AnimatedButton(1000, 10, 10, 60, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.home", (Object[])new Object[0])), 0));
                this.field_146292_n.add(new AnimatedButton(1001, 74, 10, 60, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.tiers", (Object[])new Object[0])), 0));
                this.field_146292_n.add(new AnimatedButton(1002, 138, 10, 80, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.mob_list", (Object[])new Object[0])), 0));
                int runX = 222;
                int runW = 90;
                this.field_146292_n.add(new AnimatedButton(1003, runX, 10, runW, 20, this.modelRun ? this.distort(I18n.func_135052_a((String)"bestiary.controls.run.on", (Object[])new Object[0])) : this.distort(I18n.func_135052_a((String)"bestiary.controls.run.off", (Object[])new Object[0])), 0));
                int rotX = runX + runW + 4;
                int rotW = 130;
                this.field_146292_n.add(new AnimatedButton(1004, rotX, 10, rotW, 20, this.autoRotateModel ? this.distort(I18n.func_135052_a((String)"bestiary.controls.rotation.on", (Object[])new Object[0])) : this.distort(I18n.func_135052_a((String)"bestiary.controls.rotation.off", (Object[])new Object[0])), 0));
                int gsW = 140;
                int gsBtnX = this.field_146294_l - 10 - gsW;
                int gsBtnY = this.field_146295_m - 18 - 22;
                this.field_146292_n.add(new AnimatedButton(1005, gsBtnX, gsBtnY, gsW, 20, this.distort(I18n.func_135052_a((String)"bestiary.controls.greenscreen.off", (Object[])new Object[0])), 1));
                int loreW = 110;
                int loreX = gsBtnX - 6 - loreW;
                int loreY = gsBtnY;
                IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
                int kills = 0;
                if (prog != null && this.selectedMob != null) {
                    kills = prog.getKills(this.selectedMob.mobId);
                }
                int loreMin = 10;
                if (this.selectedMob != null && this.selectedMob.minLoreKill > 0) {
                    loreMin = this.selectedMob.minLoreKill;
                }
                String loreLabel = kills >= loreMin ? this.distort(I18n.func_188566_a((String)"bestiary.lore") ? I18n.func_135052_a((String)"bestiary.lore", (Object[])new Object[0]) : I18n.func_135052_a((String)"bestiary.lore_fallback", (Object[])new Object[0])) : this.distort(I18n.func_135052_a((String)"bestiary.lore_unlocks_at_n", (Object[])new Object[]{loreMin}));
                this.field_146292_n.add(new AnimatedButton(1012, loreX, loreY, loreW, 20, loreLabel, 1));
                break;
            }
        }
    }

    private static boolean isKnown(IBestiaryProgress prog, BestiaryEntry e) {
        if (prog == null || e == null) {
            return false;
        }
        if (prog.getKills(e.mobId) > 0) {
            return true;
        }
        return prog.isMobSeen(e.mobId);
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        if (this.lorePopupOpen && button.field_146127_k != 1012) {
            this.loreAnimTarget = 0.0f;
            this.lorePopupClosingRefresh = false;
        }
        if (button.field_146127_k == 1000) {
            if (this.page == BestiaryPage.MOB_DETAIL) {
                this.startMobDetailExit(BestiaryPage.HOME, null, null);
                return;
            }
            if (this.page == BestiaryPage.MOB_LIST) {
                this.startMobListExit(BestiaryPage.HOME, null, null);
                return;
            }
            this.lorePopupOpen = false;
            this.resetModelPan();
            if (this.parent != null) {
                this.field_146297_k.func_147108_a(this.parent);
                return;
            }
            this.page = BestiaryPage.HOME;
            this.selectedTier = null;
            this.selectedMob = null;
            this.visibleMobs.clear();
            this.visibleTiers.clear();
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 1001) {
            if (this.page == BestiaryPage.MOB_DETAIL) {
                this.startMobDetailExit(BestiaryPage.PARASITES, null, null);
                return;
            }
            if (this.page == BestiaryPage.MOB_LIST) {
                this.startMobListExit(BestiaryPage.PARASITES, null, null);
                return;
            }
            this.resetModelPan();
            this.lorePopupOpen = false;
            this.tierListEnterFromRight = false;
            this.tierListAnim = 0.0f;
            this.tierListAnimTarget = 1.0f;
            this.tierListAnimLastMs = 0L;
            this.page = BestiaryPage.PARASITES;
            this.selectedMob = null;
            this.visibleMobs.clear();
            this.selectedTier = null;
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 1010) {
            this.applyPoseFromFields();
            this.syncPoseFieldsFromState();
            return;
        }
        if (button.field_146127_k == 1011) {
            this.resetPoseFields();
            this.syncPoseFieldsFromState();
            return;
        }
        if (button.field_146127_k == 1003) {
            this.modelRun = !this.modelRun;
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 1004) {
            boolean bl = this.autoRotateModel = !this.autoRotateModel;
            if (this.autoRotateModel) {
                this.modelDragActive = false;
            }
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 1005) {
            boolean bl = this.modelGreenscreen = !this.modelGreenscreen;
            if (this.modelGreenscreen) {
                this.autoRotateModel = false;
                this.modelDragActive = false;
                this.modelPanActive = false;
                this.ensurePoseFields();
                this.syncPoseFieldsFromState();
            } else {
                this.modelPanActive = false;
            }
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 1012) {
            if (this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null) {
                IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
                int kills = prog != null ? prog.getKills(this.selectedMob.mobId) : 0;
                int loreMin = 10;
                if (this.selectedMob.minLoreKill > 0) {
                    loreMin = this.selectedMob.minLoreKill;
                }
                if (kills >= loreMin) {
                    if (!this.lorePopupOpen) {
                        this.lorePopupOpen = true;
                        this.loreAnim = 0.0f;
                        this.loreAnimTarget = 1.0f;
                        this.loreAnimLastMs = 0L;
                        this.lorePopupClosingRefresh = false;
                        this.func_73866_w_();
                    } else {
                        this.loreAnimTarget = this.loreAnimTarget > 0.5f ? 0.0f : 1.0f;
                        this.lorePopupClosingRefresh = false;
                    }
                }
            }
            return;
        }
        if (button.field_146127_k == 1002) {
            if (this.page == BestiaryPage.MOB_DETAIL) {
                this.startMobDetailExit(BestiaryPage.MOB_LIST, this.selectedTier, null);
                return;
            }
            this.lorePopupOpen = false;
            this.resetModelPan();
            this.resetModelView();
            this.modelRun = false;
            this.modelZoom = 1.0f;
            this.page = BestiaryPage.MOB_LIST;
            this.selectedMob = null;
            this.func_73866_w_();
            return;
        }
        switch (this.page) {
            case HOME: {
                if (button.field_146127_k == 10) {
                    this.page = BestiaryPage.PARASITES;
                    this.selectedTier = null;
                    this.selectedMob = null;
                    this.tierListEnterFromRight = false;
                    this.tierListAnim = 0.0f;
                    this.tierListAnimTarget = 1.0f;
                    this.tierListAnimLastMs = 0L;
                    this.func_73866_w_();
                    break;
                }
                if (button.field_146127_k == 11) {
                    this.field_146297_k.func_147108_a((GuiScreen)new BlocksPage(this.player, this));
                    break;
                }
                if (button.field_146127_k == 12) {
                    this.field_146297_k.func_147108_a((GuiScreen)new CelestialEventsPage(this.player, this));
                    break;
                }
                if (button.field_146127_k == 13) {
                    this.field_146297_k.func_147108_a((GuiScreen)new StatusEffectsPage(this.player, this));
                    break;
                }
                if (button.field_146127_k != 14) break;
                this.field_146297_k.func_147108_a((GuiScreen)new SystemsPage(this.player, this));
                break;
            }
            case PARASITES: {
                int idx = button.field_146127_k - 100;
                if (idx < 0 || idx >= this.visibleTiers.size()) break;
                this.lorePopupOpen = false;
                this.selectedTier = this.visibleTiers.get(idx);
                this.startTierListExit(BestiaryPage.MOB_LIST, this.selectedTier, null);
                return;
            }
            case MOB_LIST: {
                int idx = button.field_146127_k - 200;
                if (idx < 0 || idx >= this.visibleMobs.size()) break;
                this.lorePopupOpen = false;
                BestiaryEntry next = this.visibleMobs.get(idx);
                this.resetModelView();
                this.modelRun = false;
                this.modelZoom = 1.0f;
                this.startMobListExit(BestiaryPage.MOB_DETAIL, this.selectedTier, next);
                return;
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
        float t = ((float)this.player.field_70173_aa + partialTicks) / 10.0f;
        long nowMs = Minecraft.func_71386_F();
        if (this.loreAnimLastMs == 0L) {
            this.loreAnimLastMs = nowMs;
        }
        float dt = (float)(nowMs - this.loreAnimLastMs) / 1000.0f;
        this.loreAnimLastMs = nowMs;
        float speed = 12.0f;
        float k = 1.0f - (float)Math.exp(-speed * Math.max(0.0f, Math.min(dt, 0.1f)));
        this.loreAnim += (this.loreAnimTarget - this.loreAnim) * k;
        long nowMs2 = Minecraft.func_71386_F();
        if (this.mobDetailAnimLastMs == 0L) {
            this.mobDetailAnimLastMs = nowMs2;
        }
        float dt2 = (float)(nowMs2 - this.mobDetailAnimLastMs) / 1000.0f;
        this.mobDetailAnimLastMs = nowMs2;
        float speed2 = 12.0f;
        float k2 = 1.0f - (float)Math.exp(-speed2 * Math.max(0.0f, Math.min(dt2, 0.1f)));
        this.mobDetailAnim += (this.mobDetailAnimTarget - this.mobDetailAnim) * k2;
        if (this.pendingPage != null && this.mobDetailAnimTarget == 0.0f && this.mobDetailAnim <= 0.001f) {
            BestiaryPage next = this.pendingPage;
            this.page = this.pendingPage;
            if (this.page == BestiaryPage.PARASITES) {
                this.tierListEnterFromRight = false;
                this.tierListAnim = 0.0f;
                this.tierListAnimTarget = 1.0f;
                this.tierListAnimLastMs = 0L;
            }
            this.selectedTier = this.pendingTier;
            this.selectedMob = this.pendingMob;
            this.pendingPage = null;
            this.pendingTier = null;
            this.pendingMob = null;
            if (next == BestiaryPage.MOB_LIST) {
                this.mobListAnim = 0.0f;
                this.mobListAnimTarget = 1.0f;
                this.mobListAnimLastMs = Minecraft.func_71386_F();
            } else if (next == BestiaryPage.MOB_DETAIL) {
                this.mobDetailAnim = 0.0f;
                this.mobDetailAnimTarget = 1.0f;
                this.mobDetailAnimLastMs = Minecraft.func_71386_F();
            } else {
                this.mobDetailAnim = 1.0f;
                this.mobDetailAnimTarget = 1.0f;
                this.mobDetailAnimLastMs = Minecraft.func_71386_F();
            }
            this.func_73866_w_();
        }
        long nowMs3 = Minecraft.func_71386_F();
        if (this.mobListAnimLastMs == 0L) {
            this.mobListAnimLastMs = nowMs3;
        }
        float dt3 = (float)(nowMs3 - this.mobListAnimLastMs) / 1000.0f;
        this.mobListAnimLastMs = nowMs3;
        float speed3 = 12.0f;
        float k3 = 1.0f - (float)Math.exp(-speed3 * Math.max(0.0f, Math.min(dt3, 0.1f)));
        this.mobListAnim += (this.mobListAnimTarget - this.mobListAnim) * k3;
        if (this.pendingPage2 != null && this.mobListAnimTarget == 0.0f && this.mobListAnim <= 0.001f) {
            this.page = this.pendingPage2;
            if (this.page == BestiaryPage.HOME) {
                this.selectedTier = null;
                this.selectedMob = null;
                this.visibleMobs.clear();
                this.visibleTiers.clear();
            }
            if (this.page == BestiaryPage.PARASITES) {
                this.tierListEnterFromRight = false;
                this.tierListAnim = 0.0f;
                this.tierListAnimTarget = 1.0f;
                this.tierListAnimLastMs = 0L;
            }
            this.selectedTier = this.pendingTier2;
            this.selectedMob = this.pendingMob2;
            this.pendingPage2 = null;
            this.pendingTier2 = null;
            this.pendingMob2 = null;
            this.mobListAnim = 1.0f;
            this.mobListAnimTarget = 1.0f;
            this.mobListAnimLastMs = 0L;
            if (this.page == BestiaryPage.MOB_DETAIL) {
                this.mobDetailAnim = 0.0f;
                this.mobDetailAnimTarget = 1.0f;
                this.mobDetailAnimLastMs = 0L;
            }
            this.func_73866_w_();
        }
        if (this.lorePopupOpen && this.loreAnimTarget == 0.0f && this.loreAnim <= 0.001f) {
            this.loreAnim = 0.0f;
            this.lorePopupOpen = false;
            if (!this.lorePopupClosingRefresh) {
                this.lorePopupClosingRefresh = true;
                this.func_73866_w_();
            }
        } else if (this.loreAnimTarget > 0.0f) {
            this.lorePopupClosingRefresh = false;
        }
        String title = this.distort(I18n.func_135052_a((String)"item.srparasites.srp_field_guide.name", (Object[])new Object[0]));
        long nowMs4 = Minecraft.func_71386_F();
        if (this.tierListAnimLastMs == 0L) {
            this.tierListAnimLastMs = nowMs4;
        }
        float dt4 = (float)(nowMs4 - this.tierListAnimLastMs) / 1000.0f;
        this.tierListAnimLastMs = nowMs4;
        float speed4 = 12.0f;
        float k4 = 1.0f - (float)Math.exp(-speed4 * Math.max(0.0f, Math.min(dt4, 0.1f)));
        this.tierListAnim += (this.tierListAnimTarget - this.tierListAnim) * k4;
        if (this.pendingPage3 != null && this.tierListAnimTarget == 0.0f && this.tierListAnim <= 0.001f) {
            BestiaryPage next = this.pendingPage3;
            this.page = this.pendingPage3;
            this.selectedTier = this.pendingTier3;
            this.selectedMob = this.pendingMob3;
            this.pendingPage3 = null;
            this.pendingTier3 = null;
            this.pendingMob3 = null;
            this.tierListAnim = 1.0f;
            this.tierListAnimTarget = 1.0f;
            this.tierListAnimLastMs = 0L;
            if (next == BestiaryPage.MOB_LIST) {
                this.mobListAnim = 0.0f;
                this.mobListAnimTarget = 1.0f;
                this.mobListAnimLastMs = 0L;
            } else if (next == BestiaryPage.MOB_DETAIL) {
                this.mobDetailAnim = 0.0f;
                this.mobDetailAnimTarget = 1.0f;
                this.mobDetailAnimLastMs = 0L;
            }
            this.func_73866_w_();
        }
        int titleW = this.field_146289_q.func_78256_a(title);
        int titleX = this.field_146294_l - titleW - 10;
        int titleY = 12;
        boolean hoverTitle = mouseX >= titleX && mouseX <= titleX + titleW && mouseY >= titleY && mouseY <= titleY + this.field_146289_q.field_78288_b;
        float wiggleX = 0.0f;
        float wiggleY = 0.0f;
        if (hoverTitle) {
            float phase = t;
            wiggleX = (float)Math.sin(phase * 2.0f) * 0.4f;
            wiggleY = (float)Math.sin(phase * 3.0f) * 0.6f;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)wiggleX, (float)wiggleY, (float)0.0f);
        this.func_73731_b(this.field_146289_q, title, titleX, titleY, 0xFFFFFF);
        GlStateManager.func_179121_F();
        if (this.page != BestiaryPage.MOB_DETAIL || this.autoRotateModel) {
            this.spinDeg += partialTicks * 1.5f;
        }
        switch (this.page) {
            case HOME: {
                this.func_73732_a(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.select_category", (Object[])new Object[0])), this.field_146294_l / 2, 35, 0xAAAAAA);
                break;
            }
            case PARASITES: {
                float a = ParasitesPage.smoothstep(this.tierListAnim);
                int xOff = this.getTierPageXOff();
                GlStateManager.func_179094_E();
                GlStateManager.func_179147_l();
                GlStateManager.func_179109_b((float)xOff, (float)0.0f, (float)0.0f);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
                this.drawTierPanelBackground(18);
                this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.parasite_tiers", (Object[])new Object[0])), 20, 40, 0xFFFFFF);
                if (this.selectedTier != null && (this.pendingPage3 != BestiaryPage.MOB_LIST || this.tierListAnimTarget != 0.0f)) {
                    this.drawTierPreview(this.selectedTier, 180, 70);
                }
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179084_k();
                GlStateManager.func_179121_F();
                break;
            }
            case MOB_LIST: {
                if (this.selectedTier == null) break;
                float a = ParasitesPage.smoothstep(this.mobListAnim);
                int leftOffX = -230;
                int rightOffX = this.field_146294_l + 60;
                int leftXOff = (int)((float)leftOffX * (1.0f - a));
                int rightXOff = (int)((float)rightOffX * (1.0f - a));
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b((float)leftXOff, (float)0.0f, (float)0.0f);
                this.drawTierPanelBackground(18);
                String tierLabel = this.distort(I18n.func_135052_a((String)("bestiary.tier." + this.selectedTier.name().toLowerCase(Locale.ROOT)), (Object[])new Object[0]));
                this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.tier_label", (Object[])new Object[]{tierLabel})), 20, 40, 0xFFFFFF);
                GlStateManager.func_179121_F();
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b((float)rightXOff, (float)0.0f, (float)0.0f);
                this.drawMobListWithRenders(this.visibleMobs, 190);
                GlStateManager.func_179121_F();
                break;
            }
            case MOB_DETAIL: {
                if (this.selectedMob == null) break;
                float a = ParasitesPage.smoothstep(this.mobDetailAnim);
                int offX = (int)((1.0f - a) * (float)(this.field_146294_l + 60));
                GlStateManager.func_179094_E();
                GlStateManager.func_179147_l();
                GlStateManager.func_179109_b((float)offX, (float)0.0f, (float)0.0f);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
                this.drawMobDetail(this.selectedMob, 20, 40, mouseX - offX, mouseY, partialTicks);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179084_k();
                GlStateManager.func_179121_F();
            }
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && this.lorePopupOpen && !this.modelGreenscreen && this.loreAnim > 0.0f) {
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
                float a = Math.max(0.0f, Math.min(1.0f, this.loreAnim));
                float eased = a * a * (3.0f - 2.0f * a);
                int offY = this.field_146295_m + 20;
                int animY = (int)((float)offY + (float)(boxY - offY) * eased);
                this.lorePopupX = boxX;
                this.lorePopupY = animY;
                this.lorePopupW = boxW;
                this.lorePopupH = boxH;
                int dimA = (int)(136.0f * eased);
                ParasitesPage.func_73734_a((int)0, (int)0, (int)this.field_146294_l, (int)this.field_146295_m, (int)(dimA << 24));
                GlStateManager.func_179094_E();
                GlStateManager.func_179147_l();
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)eased);
                this.drawPanel(TEX_LORE_BG, boxX, animY, boxW, boxH);
                String lore = this.distort(I18n.func_135052_a((String)ParasitesPage.loreKeyFromMobId(this.selectedMob.mobId), (Object[])new Object[0]));
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
                int textA = (int)(255.0f * eased);
                int loreColor = textA << 24 | 0x2E2E2E;
                List loreLines = this.field_146289_q.func_78271_c(lore, this.loreTextW);
                int lineH = this.field_146289_q.field_78288_b;
                int lineStep = lineH + 1;
                this.loreContentH = Math.max(0, loreLines.size() * lineStep);
                this.clampLoreScroll();
                this.enableScissor(this.loreTextX, this.loreTextY, this.loreTextW, this.loreTextH);
                int drawY = this.loreTextY - this.loreScrollPx;
                for (int i = 0; i < loreLines.size(); ++i) {
                    int yLineTop = drawY;
                    int yLineBot = yLineTop + lineH;
                    if (yLineBot >= this.loreTextY && yLineTop <= this.loreTextY + this.loreTextH) {
                        this.field_146289_q.func_78276_b((String)loreLines.get(i), this.loreTextX, yLineTop, loreColor);
                    }
                    drawY += lineStep;
                }
                this.disableScissor();
                this.loreScrollTrackX = tx + textW + sbPad;
                this.loreScrollTrackY = ty;
                this.loreScrollTrackW = sbW;
                this.loreScrollTrackH = th;
                if (this.loreContentH > this.loreTextH) {
                    int trackBgA = (int)(85.0f * eased);
                    int thumbA = (int)(170.0f * eased);
                    int trackBg = trackBgA << 24 | 0;
                    int thumbBg = thumbA << 24 | 0;
                    ParasitesPage.func_73734_a((int)this.loreScrollTrackX, (int)this.loreScrollTrackY, (int)(this.loreScrollTrackX + this.loreScrollTrackW), (int)(this.loreScrollTrackY + this.loreScrollTrackH), (int)trackBg);
                    int maxScroll = this.loreContentH - this.loreTextH;
                    int thumbH = (int)((float)this.loreTextH * (float)this.loreTextH / (float)this.loreContentH);
                    thumbH = Math.max(10, Math.min(this.loreTextH, thumbH));
                    int thumbY = this.loreTextY;
                    if (maxScroll > 0) {
                        float frac = (float)this.loreScrollPx / (float)maxScroll;
                        thumbY = this.loreTextY + (int)((float)(this.loreTextH - thumbH) * frac);
                    }
                    this.loreScrollThumbY = thumbY;
                    this.loreScrollThumbH = thumbH;
                    ParasitesPage.func_73734_a((int)this.loreScrollTrackX, (int)thumbY, (int)(this.loreScrollTrackX + this.loreScrollTrackW), (int)(thumbY + thumbH), (int)thumbBg);
                } else {
                    this.loreScrollThumbY = this.loreTextY;
                    this.loreScrollThumbH = this.loreTextH;
                }
                this.loreBackW = 80;
                this.loreBackH = 20;
                this.loreBackX = boxX + boxW - this.loreBackW - 10;
                this.loreBackY = animY + boxH - this.loreBackH - 10;
                int btnBgA = (int)(170.0f * eased);
                int btnBg = btnBgA << 24 | 0;
                ParasitesPage.func_73734_a((int)this.loreBackX, (int)this.loreBackY, (int)(this.loreBackX + this.loreBackW), (int)(this.loreBackY + this.loreBackH), (int)btnBg);
                String back = this.distort(I18n.func_135052_a((String)"gui.back", (Object[])new Object[0]));
                int backW = this.field_146289_q.func_78256_a(back);
                int backX = this.loreBackX + (this.loreBackW - backW) / 2;
                int backY = this.loreBackY + (this.loreBackH - this.field_146289_q.field_78288_b) / 2;
                int backColor = textA << 24 | 0xFFFFFF;
                this.field_146289_q.func_78276_b(back, backX, backY, backColor);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179084_k();
                GlStateManager.func_179121_F();
            } else {
                this.loreAnimTarget = 0.0f;
                this.lorePopupClosingRefresh = false;
            }
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.modelGreenscreen && this.selectedMob != null) {
            this.ensurePoseFields();
            int panelX = 10;
            int panelY = this.field_146295_m - 96;
            ParasitesPage.func_73734_a((int)(panelX - 4), (int)(panelY - 6), (int)(panelX + 170), (int)(panelY + 52), (int)-1442840576);
            String yawL = this.distort(I18n.func_135052_a((String)"bestiary.pose.yaw", (Object[])new Object[0]));
            String pitchL = this.distort(I18n.func_135052_a((String)"bestiary.pose.pitch", (Object[])new Object[0]));
            String zoomL = this.distort(I18n.func_135052_a((String)"bestiary.pose.zoom", (Object[])new Object[0]));
            String panXL = this.distort(I18n.func_135052_a((String)"bestiary.pose.panx", (Object[])new Object[0]));
            String panYL = this.distort(I18n.func_135052_a((String)"bestiary.pose.pany", (Object[])new Object[0]));
            this.field_146289_q.func_78276_b(yawL, panelX, panelY + 2, 0xFFFFFF);
            this.field_146289_q.func_78276_b(pitchL, panelX, panelY + 16, 0xFFFFFF);
            this.field_146289_q.func_78276_b(zoomL, panelX, panelY + 30, 0xFFFFFF);
            this.field_146289_q.func_78276_b(panXL, panelX + 74, panelY + 2, 0xFFFFFF);
            this.field_146289_q.func_78276_b(panYL, panelX + 74, panelY + 16, 0xFFFFFF);
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
            if (b == null || !b.field_146125_m || b.field_146127_k != 1003) continue;
            runBtn = b;
            break;
        }
        if (runBtn == null) {
            return;
        }
        if (mouseX >= runBtn.field_146128_h && mouseX < runBtn.field_146128_h + runBtn.field_146120_f && mouseY >= runBtn.field_146129_i && mouseY < runBtn.field_146129_i + runBtn.field_146121_g) {
            List<String> tip = Collections.singletonList(this.distort(I18n.func_135052_a((String)"bestiary.tooltip.run_inaccurate", (Object[])new Object[0])));
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            this.func_146283_a(tip, mouseX, mouseY);
            GlStateManager.func_179126_j();
            GlStateManager.func_179145_e();
        }
    }

    private static int clampInt(int v, int lo, int hi) {
        if (v < lo) {
            return lo;
        }
        if (v > hi) {
            return hi;
        }
        return v;
    }

    private void clampDropsScroll() {
        int maxScroll = Math.max(0, this.dropsContentH - this.dropsViewH);
        this.dropsScrollPx = ParasitesPage.clampInt(this.dropsScrollPx, 0, maxScroll);
    }

    private void clampLoreScroll() {
        int maxScroll = Math.max(0, this.loreContentH - this.loreTextH);
        this.loreScrollPx = ParasitesPage.clampInt(this.loreScrollPx, 0, maxScroll);
    }

    private void enableScissor(int x, int y, int w, int h) {
        ScaledResolution sr = new ScaledResolution(this.field_146297_k);
        int scale = sr.func_78325_e();
        int sx = x * scale;
        int sy = this.field_146297_k.field_71440_d - (y + h) * scale;
        int sw = w * scale;
        int sh = h * scale;
        GL11.glEnable((int)3089);
        GL11.glScissor((int)sx, (int)sy, (int)sw, (int)sh);
    }

    private void disableScissor() {
        GL11.glDisable((int)3089);
    }

    private int getTierPageXOff() {
        int off;
        float a = ParasitesPage.smoothstep(this.tierListAnim);
        if (this.tierListEnterFromRight) {
            int rightOffX = this.field_146294_l + 60;
            off = (int)((float)rightOffX * (1.0f - a));
        } else {
            int leftOffX = -230;
            off = (int)((float)leftOffX * (1.0f - a));
        }
        return off;
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        if (this.page == BestiaryPage.MOB_DETAIL && this.modelGreenscreen && this.selectedMob != null) {
            boolean used;
            this.ensurePoseFields();
            boolean bl = used = this.tfYaw.func_146201_a(typedChar, keyCode) || this.tfPitch.func_146201_a(typedChar, keyCode) || this.tfZoom.func_146201_a(typedChar, keyCode) || this.tfPanX.func_146201_a(typedChar, keyCode) || this.tfPanY.func_146201_a(typedChar, keyCode);
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
        ParasitesPage.func_73734_a((int)(panelX - 1), (int)(panelY - 1), (int)(panelX + panelW + 1), (int)(panelY + panelH + 1), (int)-1442840576);
        ParasitesPage.func_73734_a((int)panelX, (int)panelY, (int)(panelX + panelW), (int)(panelY + panelH), (int)-2013265920);
    }

    private boolean isMouseOverPoseFields(int mouseX, int mouseY) {
        if (!this.poseControlsInit) {
            return false;
        }
        return this.tfYaw != null && this.tfYaw.func_146176_q() && this.tfYaw.func_146206_l() || this.isOver(this.tfYaw, mouseX, mouseY) || this.tfPitch != null && this.tfPitch.func_146176_q() && this.tfPitch.func_146206_l() || this.isOver(this.tfPitch, mouseX, mouseY) || this.tfZoom != null && this.tfZoom.func_146176_q() && this.tfZoom.func_146206_l() || this.isOver(this.tfZoom, mouseX, mouseY) || this.tfPanX != null && this.tfPanX.func_146176_q() && this.tfPanX.func_146206_l() || this.isOver(this.tfPanX, mouseX, mouseY) || this.tfPanY != null && this.tfPanY.func_146176_q() && this.tfPanY.func_146206_l() || this.isOver(this.tfPanY, mouseX, mouseY);
    }

    private boolean isOver(GuiTextField tf, int mx, int my) {
        if (tf == null) {
            return false;
        }
        return mx >= tf.field_146209_f && mx < tf.field_146209_f + tf.field_146218_h && my >= tf.field_146210_g && my < tf.field_146210_g + tf.field_146219_i;
    }

    public void func_146281_b() {
        super.func_146281_b();
        Keyboard.enableRepeatEvents((boolean)false);
    }

    private void drawTierPreview(ParasiteTier tier, int x, int y) {
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        int shown = 0;
        for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
            if (e.tier != tier || !ParasitesPage.isKnown(prog, e)) continue;
            String name = this.distort(I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]));
            this.field_146289_q.func_78276_b(name, x + 30, y + 4, 0xDDDDDD);
            float scale = SRPBestiaryRegistry.getRenderScale(e.mobId);
            this.renderEntityPreview(e.mobId, x + 14, y + 14, 28, 28, this.spinDeg, 3, 0.92f);
            y += 32;
            if (++shown < 5) continue;
            break;
        }
        if (shown == 0) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a((String)"bestiary.parasites.unlock_tier_hint", (Object[])new Object[0])), x, y, 0x777777);
        }
    }

    private void syncPoseFieldsFromState() {
        if (!this.poseControlsInit) {
            return;
        }
        if (this.suppressPoseFieldUpdates) {
            return;
        }
        if (this.page != BestiaryPage.MOB_DETAIL || !this.modelGreenscreen || this.selectedMob == null) {
            return;
        }
        this.suppressPoseFieldUpdates = true;
        try {
            this.tfYaw.func_146180_a(String.format(Locale.US, "%.1f", Float.valueOf(this.manualYawDeg)));
            this.tfPitch.func_146180_a(String.format(Locale.US, "%.1f", Float.valueOf(this.manualPitchDeg)));
            this.tfZoom.func_146180_a(String.format(Locale.US, "%.2f", Float.valueOf(this.modelZoom)));
            this.tfPanX.func_146180_a(Integer.toString(this.modelPanX));
            this.tfPanY.func_146180_a(Integer.toString(this.modelPanY));
        }
        finally {
            this.suppressPoseFieldUpdates = false;
        }
    }

    private void resetModelView() {
        this.modelDragActive = false;
        this.autoRotateModel = true;
        this.manualYawDeg = 0.0f;
        this.manualPitchDeg = 0.0f;
        this.dragStartMouseX = 0;
        this.dragStartMouseY = 0;
        this.dragStartYawDeg = 0.0f;
        this.dragStartPitchDeg = 0.0f;
        this.modelZoom = 1.0f;
        this.modelRun = false;
    }

    private void drawMobListWithRenders(List<BestiaryEntry> mobs, int xRight) {
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        int panelPadX = 8;
        int panelPadY = 4;
        int iconBoxW = 28;
        int iconBoxH = 28;
        int textGap = 8;
        int panelX = xRight - 8;
        int panelW = this.field_146294_l - 8 - panelX;
        int rowY = this.LIST_VIEW_TOP() - this.scrollMobs;
        for (BestiaryEntry e : mobs) {
            int rowTop = rowY;
            int rowBot = rowTop + 28;
            if (rowBot > this.LIST_VIEW_TOP() && rowTop < this.LIST_BOTTOM()) {
                int centerY = rowTop + 14;
                int pY = rowTop - 4;
                int pH = 36;
                ParasitesPage.func_73734_a((int)(panelX - 1), (int)(pY - 1), (int)(panelX + panelW + 1), (int)(pY + pH + 1), (int)-1442840576);
                ParasitesPage.func_73734_a((int)panelX, (int)pY, (int)(panelX + panelW), (int)(pY + pH), (int)-2013265920);
                int iconCenterX = xRight + 14;
                int iconCenterY = centerY;
                float rs = SRPBestiaryRegistry.getRenderScale(e.mobId);
                float thumbShrink = 0.8f / Math.max(1.0f, rs);
                int thumbMargin = 10;
                this.renderEntityPreview(e.mobId, iconCenterX, iconCenterY, 28, 28, this.spinDeg, thumbMargin, thumbShrink);
                int kills = prog.getKills(e.mobId);
                String name = this.distort(I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]));
                String killsLabel = this.distort(I18n.func_135052_a((String)"bestiary.kills_label", (Object[])new Object[0]));
                String raw = name + "  (\u00a77" + killsLabel + ": " + kills + "\u00a7r)";
                int textX = xRight + 28 + 8;
                int avail = panelX + panelW - textX - 6;
                String shown = this.field_146289_q.func_78269_a(raw, Math.max(32, avail));
                int textY = centerY - this.field_146289_q.field_78288_b / 2;
                this.field_146289_q.func_78276_b(shown, textX, textY, 0xDDDDDD);
            }
            rowY += 36;
        }
        if (mobs.isEmpty()) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a((String)"bestiary.no_mobs_unlocked_in_tier", (Object[])new Object[0])), xRight, this.LIST_VIEW_TOP(), 0x777777);
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && this.lorePopupOpen && !this.modelGreenscreen && this.loreAnim > 0.0f && mouseButton == 0 && mouseX >= this.loreBackX && mouseX < this.loreBackX + this.loreBackW && mouseY >= this.loreBackY && mouseY < this.loreBackY + this.loreBackH) {
            this.loreAnimTarget = 0.0f;
            this.lorePopupClosingRefresh = false;
            return;
        }
        if (this.lorePopupOpen) {
            if (mouseButton == 0 && this.loreContentH > this.loreTextH) {
                boolean overThumb;
                boolean bl = overThumb = mouseX >= this.loreScrollTrackX && mouseX < this.loreScrollTrackX + this.loreScrollTrackW && mouseY >= this.loreScrollThumbY && mouseY < this.loreScrollThumbY + this.loreScrollThumbH;
                if (overThumb) {
                    this.loreScrollDrag = true;
                    this.loreScrollDragStartMouseY = mouseY;
                    this.loreScrollDragStartScrollPx = this.loreScrollPx;
                    return;
                }
            }
            return;
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.modelGreenscreen && this.selectedMob != null) {
            this.ensurePoseFields();
            this.tfYaw.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfPitch.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfZoom.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfPanX.func_146192_a(mouseX, mouseY, mouseButton);
            this.tfPanY.func_146192_a(mouseX, mouseY, mouseButton);
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && mouseButton == 0) {
            boolean overModel;
            boolean bl = overModel = mouseX >= this.modelRectX && mouseX < this.modelRectX + this.modelRectW && mouseY >= this.modelRectY && mouseY < this.modelRectY + this.modelRectH;
            if ((this.modelGreenscreen || overModel) && !this.isMouseOverAnyButton(mouseX, mouseY) && !this.isMouseOverPoseFields(mouseX, mouseY)) {
                this.autoRotateModel = false;
                this.modelDragActive = true;
                this.dragStartMouseX = mouseX;
                this.dragStartMouseY = mouseY;
                this.dragStartYawDeg = this.manualYawDeg;
                this.dragStartPitchDeg = this.manualPitchDeg;
                this.func_73866_w_();
            }
        }
        if (!this.lorePopupOpen && this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && mouseButton == 0 && this.dropsContentH > this.dropsViewH) {
            boolean overThumb;
            boolean bl = overThumb = mouseX >= this.dropsScrollTrackX && mouseX < this.dropsScrollTrackX + this.dropsScrollTrackW && mouseY >= this.dropsScrollThumbY && mouseY < this.dropsScrollThumbY + this.dropsScrollThumbH;
            if (overThumb) {
                this.dropsScrollDrag = true;
                this.dropsScrollDragStartMouseY = mouseY;
                this.dropsScrollDragStartScrollPx = this.dropsScrollPx;
                return;
            }
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && mouseButton == 1 && this.modelGreenscreen) {
            this.modelPanActive = true;
            this.panStartMouseX = mouseX;
            this.panStartMouseY = mouseY;
            this.panStartX = this.modelPanX;
            this.panStartY = this.modelPanY;
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
        if (id == null || id.isEmpty()) {
            return ItemStack.field_190927_a;
        }
        Item it = Item.func_111206_d((String)(id = id.trim()));
        if (it == null) {
            return ItemStack.field_190927_a;
        }
        return new ItemStack(it);
    }

    private void renderItemIconSway(ItemStack stack, int x, int y, float t, int index) {
        if (stack == null || stack.func_190926_b()) {
            return;
        }
        float phase = t * 0.9f + (float)index * 0.35f;
        float ang = (float)Math.sin(phase) * 6.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)(x + 8), (float)(y + 8), (float)0.0f);
        GlStateManager.func_179114_b((float)ang, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179109_b((float)(-(x + 8)), (float)(-(y + 8)), (float)0.0f);
        RenderHelper.func_74520_c();
        this.field_146297_k.func_175599_af().func_180450_b(stack, x, y);
        RenderHelper.func_74518_a();
        GlStateManager.func_179121_F();
    }

    private void loadDropCacheIfNeeded() {
        File f = this.getMobsCfgFile();
        if (f == null || !f.exists() || !f.isFile()) {
            this.dropsLoaded = true;
            this.dropsByCategory.clear();
            this.knownCategories.clear();
            this.dropsLastModified = -1L;
            return;
        }
        long lm = f.lastModified();
        if (this.dropsLoaded && lm == this.dropsLastModified) {
            return;
        }
        this.dropsLoaded = true;
        this.dropsLastModified = lm;
        this.dropsByCategory.clear();
        this.knownCategories.clear();
        String currentCategory = null;
        boolean readingLoot = false;
        ArrayList<DropEntry> currentLoot = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(f), StandardCharsets.UTF_8));){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts;
                String t = line.trim();
                if (t.isEmpty() || t.startsWith("#")) continue;
                if (!readingLoot && t.startsWith("\"") && t.endsWith("{")) {
                    int q2 = t.indexOf(34, 1);
                    if (q2 <= 1) continue;
                    currentCategory = t.substring(1, q2);
                    this.knownCategories.add(currentCategory);
                    continue;
                }
                if (!readingLoot && t.startsWith("}")) {
                    currentCategory = null;
                    continue;
                }
                if (!readingLoot && currentCategory != null && t.startsWith("S:\"") && t.contains("Loot Table\" <")) {
                    readingLoot = true;
                    currentLoot = new ArrayList<DropEntry>();
                    continue;
                }
                if (!readingLoot) continue;
                if (t.startsWith(">")) {
                    readingLoot = false;
                    if (currentCategory != null && currentLoot != null) {
                        this.dropsByCategory.put(currentCategory, currentLoot);
                    }
                    currentLoot = null;
                    continue;
                }
                if (t.startsWith("#") || (parts = t.split(";")).length < 4) continue;
                String itemId = parts[0].trim();
                int chance = ParasitesPage.parseIntSafe(parts[1], 0);
                int amount = ParasitesPage.parseIntSafe(parts[2], 1);
                boolean looting = "true".equalsIgnoreCase(parts[3].trim());
                if (itemId.isEmpty()) continue;
                currentLoot.add(new DropEntry(itemId, chance, amount, looting));
            }
        }
        catch (Throwable ex) {
            System.out.println("[SRP][BESTIARY][DROPS] Failed reading cfg: " + ex);
            this.dropsByCategory.clear();
            this.knownCategories.clear();
        }
    }

    private List<String> buildCategoryCandidates(String mobId) {
        String[] prefixes;
        ArrayList<String> out = new ArrayList<String>();
        if (mobId == null || mobId.isEmpty()) {
            return out;
        }
        ResourceLocation rl = mobId.indexOf(58) >= 0 ? new ResourceLocation(mobId) : new ResourceLocation("srparasites", mobId);
        String domain = rl.func_110624_b();
        String path = rl.func_110623_a();
        out.add(domain + ":" + path);
        for (String p : prefixes = new String[]{"pri_", "ada_", "sim_", "fer_"}) {
            if (!path.startsWith(p) || path.length() <= p.length()) continue;
            out.add(domain + ":" + path.substring(p.length()));
        }
        int us = path.indexOf(95);
        if (us > 0 && us + 1 < path.length()) {
            out.add(domain + ":" + path.substring(us + 1));
        }
        if (path.startsWith("carrier_") && path.length() > "carrier_".length()) {
            out.add(domain + ":" + path.substring("carrier_".length()));
        }
        LinkedHashSet<String> uniq = new LinkedHashSet<String>(out);
        return new ArrayList<String>(uniq);
    }

    private String resolveCfgCategoryForMob(String mobId) {
        if (mobId == null) {
            return null;
        }
        String override = CFG_CATEGORY_TO_FAMILY.get(mobId);
        if (override != null && !override.isEmpty()) {
            return override;
        }
        if (this.knownCategories.contains(mobId)) {
            return mobId;
        }
        for (String c : this.buildCategoryCandidates(mobId)) {
            if (!this.knownCategories.contains(c)) continue;
            return c;
        }
        return null;
    }

    private List<DropEntry> getDropsForMob(String mobId) {
        this.loadDropCacheIfNeeded();
        String cat = this.resolveCfgCategoryForMob(mobId);
        if (cat == null) {
            return Collections.emptyList();
        }
        List<DropEntry> drops = this.dropsByCategory.get(cat);
        return drops != null ? drops : Collections.emptyList();
    }

    protected void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        float pixelsPerScroll;
        int trackTravel;
        int maxScroll;
        int dy;
        int dx;
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if (this.page == BestiaryPage.MOB_DETAIL && this.modelDragActive && clickedMouseButton == 0) {
            dx = mouseX - this.dragStartMouseX;
            dy = mouseY - this.dragStartMouseY;
            float yawSens = 0.6f;
            float pitchSens = 0.6f;
            this.manualYawDeg = this.dragStartYawDeg - (float)dx * yawSens;
            this.manualPitchDeg = this.dragStartPitchDeg + (float)dy * pitchSens;
            this.manualPitchDeg = Math.max(-60.0f, Math.min(60.0f, this.manualPitchDeg));
            this.syncPoseFieldsFromState();
        }
        if (!this.lorePopupOpen && this.page == BestiaryPage.MOB_DETAIL && this.selectedMob != null && this.dropsScrollDrag && this.dropsContentH > this.dropsViewH) {
            maxScroll = this.dropsContentH - this.dropsViewH;
            trackTravel = Math.max(1, this.dropsViewH - this.dropsScrollThumbH);
            int dy2 = mouseY - this.dropsScrollDragStartMouseY;
            pixelsPerScroll = (float)maxScroll / (float)trackTravel;
            this.dropsScrollPx = this.dropsScrollDragStartScrollPx + (int)((float)dy2 * pixelsPerScroll);
            this.clampDropsScroll();
            return;
        }
        if (this.lorePopupOpen && this.loreScrollDrag && this.loreContentH > this.loreTextH) {
            maxScroll = this.loreContentH - this.loreTextH;
            trackTravel = Math.max(1, this.loreTextH - this.loreScrollThumbH);
            int dy3 = mouseY - this.loreScrollDragStartMouseY;
            pixelsPerScroll = (float)maxScroll / (float)trackTravel;
            this.loreScrollPx = this.loreScrollDragStartScrollPx + (int)((float)dy3 * pixelsPerScroll);
            this.clampLoreScroll();
            return;
        }
        if (this.page == BestiaryPage.MOB_DETAIL && this.modelPanActive && clickedMouseButton == 1 && this.modelGreenscreen) {
            dx = mouseX - this.panStartMouseX;
            dy = mouseY - this.panStartMouseY;
            this.modelPanX = this.panStartX + dx;
            this.modelPanY = this.panStartY + dy;
            this.syncPoseFieldsFromState();
        }
    }

    private void ensurePoseFields() {
        if (this.poseControlsInit && this.lastW == this.field_146294_l && this.lastH == this.field_146295_m) {
            return;
        }
        String yawTxt = this.tfYaw != null ? this.tfYaw.func_146179_b() : String.format(Locale.US, "%.1f", Float.valueOf(this.manualYawDeg));
        String pitchTxt = this.tfPitch != null ? this.tfPitch.func_146179_b() : String.format(Locale.US, "%.1f", Float.valueOf(this.manualPitchDeg));
        String zoomTxt = this.tfZoom != null ? this.tfZoom.func_146179_b() : String.format(Locale.US, "%.2f", Float.valueOf(this.modelZoom));
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

    private void resetPoseFields() {
        this.manualYawDeg = 0.0f;
        this.manualPitchDeg = 0.0f;
        this.modelZoom = 1.0f;
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
        }
        catch (Throwable t) {
            return def;
        }
    }

    private static int parseIntSafe(String s, int def) {
        try {
            return Integer.parseInt(s.trim());
        }
        catch (Throwable t) {
            return def;
        }
    }

    private void applyPoseFromFields() {
        if (!this.poseControlsInit) {
            return;
        }
        float yaw = ParasitesPage.parseFloatSafe(this.tfYaw.func_146179_b(), this.manualYawDeg);
        float pitch = ParasitesPage.parseFloatSafe(this.tfPitch.func_146179_b(), this.manualPitchDeg);
        float zoom = ParasitesPage.parseFloatSafe(this.tfZoom.func_146179_b(), this.modelZoom);
        int panX = ParasitesPage.parseIntSafe(this.tfPanX.func_146179_b(), this.modelPanX);
        int panY = ParasitesPage.parseIntSafe(this.tfPanY.func_146179_b(), this.modelPanY);
        pitch = Math.max(-90.0f, Math.min(90.0f, pitch));
        zoom = Math.max(0.35f, Math.min(2.75f, zoom));
        this.manualYawDeg = yaw;
        this.manualPitchDeg = pitch;
        this.modelZoom = zoom;
        this.modelPanX = panX;
        this.modelPanY = panY;
        this.autoRotateModel = false;
        this.modelDragActive = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drawMobDetail(BestiaryEntry e, int x, int y, int mouseX, int mouseY, float partialTicks) {
        float yaw;
        String statsLine;
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a((String)"bestiary.capability_missing_client", (Object[])new Object[0])), x, y, 0xFF5555);
            return;
        }
        int kills = prog.getKills(e.mobId);
        String name = this.distort(I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]));
        float t = ((float)this.player.field_70173_aa + partialTicks) / 10.0f;
        int pad = 6;
        int sectionGap = 6;
        int lineH = this.field_146289_q.field_78288_b;
        int pageLeft = x;
        int pageRight = this.field_146294_l - 8;
        int pageW = Math.max(140, pageRight - pageLeft);
        int loreMinH = lineH * 3 + 8;
        int descW = (int)((float)pageW * 0.55f);
        int modelW = pageW - descW - 6;
        int curY = y;
        int bgX = pageLeft - 6;
        int bgY = curY - 6;
        int bgW = pageW + 12;
        int bgH = 200;
        this.drawPanel(TEX_BG, bgX, bgY, bgW, bgH);
        String label = this.distort(I18n.func_135052_a((String)"lore.srparasites.compendium", (Object[])new Object[0]));
        int labelAvailW = Math.max(80, this.field_146294_l - x - 8);
        curY = this.drawTextPanelLeftWiggle(TEX_LABEL_BG, label, x, curY, labelAvailW, 0x2E2E2E, mouseX, mouseY, t) + 2;
        String killsLabel = this.distort(I18n.func_135052_a((String)"bestiary.kills_label", (Object[])new Object[0]));
        String nameText = String.format("%s  (%s: %d)", name, killsLabel, kills);
        int nameAvailW = Math.max(80, this.field_146294_l - x - 8);
        curY = this.drawTextPanelLeftWiggle(TEX_NAME_BG, nameText, x, curY, nameAvailW, 0x2E2E2E, mouseX, mouseY, t) + 6;
        int descLeft = pageLeft;
        int modelLeft = pageLeft + descW + 6;
        int contentTop = curY;
        String descKey = "bestiary." + e.mobId.replace(':', '.') + ".desc";
        String desc = I18n.func_188566_a((String)descKey) ? this.distort(I18n.func_135052_a((String)descKey, (Object[])new Object[0])) : "";
        List lines = this.field_146289_q.func_78271_c(desc, descW);
        int textY = contentTop;
        if (lines.isEmpty()) {
            textY = contentTop + lineH;
        } else {
            for (String line : lines) {
                this.field_146289_q.func_78276_b(line, descLeft, textY, 0xCCCCCC);
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
        if (hasStats) {
            hp = e.baseHp > 0 ? (double)e.baseHp : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111267_a);
            dmg = e.baseDamage > 0.0f ? (double)e.baseDamage : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111264_e);
            statsLine = this.distort(I18n.func_135052_a((String)"bestiary.stats", (Object[])new Object[]{String.valueOf((int)Math.round(hp)), String.valueOf((float)dmg)}));
        } else {
            statsLine = this.distort(I18n.func_135052_a((String)"bestiary.more_info_at_n", (Object[])new Object[]{statMin}));
        }
        int statsPadX = 12;
        int statsPadY = 6;
        int statsTextW = this.field_146289_q.func_78256_a(statsLine);
        int statsW = Math.min(descW, statsTextW + 24);
        int statsPanelH = this.field_146289_q.field_78288_b + 6;
        boolean hoverStats = mouseX >= descLeft && mouseX <= descLeft + statsW && mouseY >= statsTop && mouseY <= statsTop + statsPanelH;
        float statsWiggleX = 0.0f;
        float statsWiggleY = 0.0f;
        if (hoverStats) {
            float phase = t;
            statsWiggleX = (float)Math.sin(phase * 2.0f) * 0.4f;
            statsWiggleY = (float)Math.sin(phase * 3.0f) * 0.6f;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)statsWiggleX, (float)statsWiggleY, (float)0.0f);
        this.drawPanel(TEX_STATS_BG, descLeft, statsTop, statsW, statsPanelH);
        int statsTextX = descLeft + (statsW - statsTextW) / 2;
        int statsTextY = statsTop + (statsPanelH - this.field_146289_q.field_78288_b) / 2;
        this.field_146289_q.func_78276_b(statsLine, statsTextX, statsTextY, 0x2E2E2E);
        GlStateManager.func_179121_F();
        int leftBottom = statsTop + statsPanelH;
        if (!this.modelGreenscreen) {
            int dropsMin = statMin;
            int dropTop = leftBottom + 6;
            int dropBoxX = descLeft;
            int dropBoxW = descW;
            int dropPadX = 6;
            int dropPadY = 6;
            String dropTitle = this.distort(I18n.func_188566_a((String)"bestiary.drops") ? I18n.func_135052_a((String)"bestiary.drops", (Object[])new Object[0]) : I18n.func_135052_a((String)"bestiary.drops_fallback", (Object[])new Object[0]));
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
            boolean hoverDrops = mouseX >= dropBoxX && mouseX <= dropBoxX + dropBoxW && mouseY >= dropTop && mouseY <= dropTop + dropBoxH;
            float dropWiggleX = 0.0f;
            float dropWiggleY = 0.0f;
            if (hoverDrops) {
                float phase = t;
                dropWiggleX = (float)Math.sin(phase * 2.0f) * 0.4f;
                dropWiggleY = (float)Math.sin(phase * 3.0f) * 0.6f;
            }
            int wX = (int)dropWiggleX;
            int wY = (int)dropWiggleY;
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)dropWiggleX, (float)dropWiggleY, (float)0.0f);
            this.drawPanel(TEX_DROP_BG, dropBoxX, dropTop, dropBoxW, dropBoxH);
            int titleX = dropBoxX + dropPadX;
            int titleY = dropTop + dropPadY;
            this.field_146289_q.func_78276_b(this.distort(dropTitle + ":"), titleX, titleY, 0x2E2E2E);
            int textX = dropBoxX + dropPadX;
            int dropTextY = titleY + lineStep;
            int textW = dropBoxW - dropPadX * 2;
            int textH = textAreaH;
            this.dropsViewX = textX + wX;
            this.dropsViewY = dropTextY + wY;
            this.dropsViewW = textW;
            this.dropsViewH = textH;
            List<Object> drops = Collections.emptyList();
            if (kills < dropsMin) {
                String msg = this.distort(I18n.func_188566_a((String)"bestiary.loot_unlocks_at_n") ? I18n.func_135052_a((String)"bestiary.loot_unlocks_at_n", (Object[])new Object[]{String.valueOf(dropsMin)}) : I18n.func_135052_a((String)"bestiary.loot_unlocks_at_n_fallback", (Object[])new Object[]{String.valueOf(dropsMin)}));
                this.enableScissor(this.dropsViewX, this.dropsViewY, this.dropsViewW, this.dropsViewH);
                this.field_146289_q.func_78276_b(msg, textX, dropTextY, 0x555555);
                this.disableScissor();
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
                    String none = this.distort(I18n.func_188566_a((String)"bestiary.drops.none") ? I18n.func_135052_a((String)"bestiary.drops.none", (Object[])new Object[0]) : I18n.func_135052_a((String)"bestiary.drops.none_fallback", (Object[])new Object[0]));
                    this.field_146289_q.func_78276_b(none, textX, drawY, 0x555555);
                } else {
                    for (int i = 0; i < drops.size(); ++i) {
                        DropEntry d0 = (DropEntry)drops.get(i);
                        int yLineTop = drawY;
                        int yLineBot = yLineTop + dropLineH;
                        if (yLineBot >= dropTextY && yLineTop <= dropTextY + this.dropsViewH) {
                            ItemStack st = ParasitesPage.stackFromId(d0.itemId);
                            if (!st.func_190926_b()) {
                                int iconY = yLineTop + (lineStep - 16) / 2;
                                this.renderItemIconSway(st, textX, iconY, t, i);
                            }
                            String displayName = !st.func_190926_b() ? ParasitesPage.forceBlack(st.func_82833_r()) : d0.itemId;
                            String lootingTag = d0.looting ? " " + I18n.func_135052_a((String)"bestiary.loot_looting", (Object[])new Object[0]) : "";
                            String rowPlain = displayName + " x" + d0.amount + " (" + d0.chance + "%)" + lootingTag;
                            int avail = Math.max(40, this.dropsViewW - 20);
                            String rowDraw = this.isJumbled ? GuiDistortionHelper.jamText(rowPlain) : this.field_146289_q.func_78269_a(rowPlain, avail);
                            int textYRow = yLineTop + (lineStep - dropLineH) / 2;
                            this.field_146289_q.func_78276_b(rowDraw, textX + 20, textYRow, 0x2E2E2E);
                        }
                        drawY += lineStep;
                    }
                }
                this.disableScissor();
            }
            GlStateManager.func_179121_F();
            leftBottom = dropTop + dropBoxH;
        }
        int modelH = Math.max(90, leftBottom - contentTop);
        this.modelRectX = modelLeft;
        this.modelRectY = contentTop;
        this.modelRectW = modelW;
        this.modelRectH = modelH;
        int cx = modelLeft + modelW / 2 + (this.modelGreenscreen ? this.modelPanX : 0);
        int cy = contentTop + modelH / 2 + (this.modelGreenscreen ? this.modelPanY : 0);
        int innerMargin = 12;
        int baseW = Math.max(40, modelW - 24);
        int baseH = Math.max(40, modelH - 24);
        float extraShrink = 0.88f;
        int zoomW = Math.max(10, (int)((float)baseW * 0.88f * this.modelZoom));
        int zoomH = Math.max(10, (int)((float)baseH * 0.88f * this.modelZoom));
        if (this.modelGreenscreen) {
            ParasitesPage.func_73734_a((int)0, (int)0, (int)this.field_146294_l, (int)this.field_146295_m, (int)-16711936);
        } else {
            this.drawPanel(TEX_MODEL_BG, modelLeft, contentTop, modelW, modelH);
        }
        float pitch = this.manualPitchDeg;
        float f = yaw = this.autoRotateModel ? this.spinDeg : this.manualYawDeg;
        if (this.modelGreenscreen && this.poseControlsInit && !this.tfYaw.func_146206_l()) {
            this.suppressPoseFieldUpdates = true;
            try {
                this.tfYaw.func_146180_a(String.format(Locale.US, "%.1f", Float.valueOf(yaw)));
            }
            finally {
                this.suppressPoseFieldUpdates = false;
            }
        }
        this.renderEntityPreviewDetail(e.mobId, cx, cy, zoomW, zoomH, yaw, pitch);
        if (!this.modelGreenscreen) {
            float a2 = this.page == BestiaryPage.MOB_DETAIL ? ParasitesPage.smoothstep(this.mobDetailAnim) : 1.0f;
            int warnH = 18;
            int warnW = this.field_146294_l;
            int warnX = 0;
            int warnY = this.field_146295_m - warnH;
            int warnOffDown = this.field_146295_m + 60;
            int warnAnimY = warnY + (int)((float)warnOffDown * (1.0f - a2));
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a2);
            this.drawAnimatedStripVertical(TEX_WARNING_SHEET, 80, warnX, warnAnimY, warnW, warnH, 400, 16, 20);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }

    private boolean isMouseOverAnyButton(int mouseX, int mouseY) {
        for (GuiButton b : this.field_146292_n) {
            if (b == null || !b.field_146125_m || mouseX < b.field_146128_h || mouseX >= b.field_146128_h + b.field_146120_f || mouseY < b.field_146129_i || mouseY >= b.field_146129_i + b.field_146121_g) continue;
            return true;
        }
        return false;
    }

    private static String forceBlack(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        if (s.length() >= 2 && s.charAt(0) == '\u00a7') {
            return "\u00a70" + s.substring(2);
        }
        return "\u00a70" + s;
    }

    private double readEntityStat(String mobId, IAttribute attr) {
        Entity ent = EntityList.func_188429_b((ResourceLocation)new ResourceLocation(mobId), (World)this.player.field_70170_p);
        if (!(ent instanceof EntityLivingBase)) {
            return -1.0;
        }
        EntityLivingBase living = (EntityLivingBase)ent;
        IAttributeInstance inst = living.func_110148_a(attr);
        return inst != null ? inst.func_111125_b() : -1.0;
    }

    private void resetModelPan() {
        this.modelPanActive = false;
        this.modelPanX = 0;
        this.modelPanY = 0;
        this.panStartMouseY = 0;
        this.panStartMouseX = 0;
        this.panStartY = 0;
        this.panStartX = 0;
    }

    private int drawTextPanelLeftWiggle(ResourceLocation tex, String text, int leftX, int topY, int maxWidth, int textColor, int mouseX, int mouseY, float t) {
        int padX = 12;
        int padY = 8;
        int textW = this.field_146289_q.func_78256_a(text);
        int panelW = Math.min(Math.max(80, maxWidth), textW + 24);
        int panelH = this.field_146289_q.field_78288_b + 8;
        boolean hovered = mouseX >= leftX && mouseX <= leftX + panelW && mouseY >= topY && mouseY <= topY + panelH;
        float wiggleX = 0.0f;
        float wiggleY = 0.0f;
        if (hovered) {
            float phase = t;
            wiggleX = (float)Math.sin(phase * 2.0f) * 0.4f;
            wiggleY = (float)Math.sin(phase * 3.0f) * 0.6f;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)wiggleX, (float)wiggleY, (float)0.0f);
        this.drawPanel(tex, leftX, topY, panelW, panelH);
        this.field_146289_q.func_78276_b(text, leftX + 12, topY + (panelH - this.field_146289_q.field_78288_b) / 2, textColor);
        GlStateManager.func_179121_F();
        return topY + panelH;
    }

    private float computeAutoScale(EntityLivingBase ent, int boxW, int boxH) {
        float padW = Math.max(0, boxW - 4);
        float padH = Math.max(0, boxH - 4);
        float w = Math.max(0.6f, ent.field_70130_N);
        float h = Math.max(0.6f, ent.field_70131_O);
        float scaleW = padW / w;
        float scaleH = padH / h;
        float scale = Math.min(scaleW, scaleH);
        scale = Math.max(8.0f, Math.min(scale, 120.0f));
        return scale;
    }

    private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, float pitchDeg) {
        this.renderEntityPreview(mobId, cx, cy, boxW, boxH, yawDeg, pitchDeg, 0, 1.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, float pitchDeg, int marginPx, float extraShrink) {
        EntityLivingBase ent = this.entityCache.computeIfAbsent(mobId, id -> {
            Entity created = EntityList.func_188429_b((ResourceLocation)new ResourceLocation(id), (World)this.player.field_70170_p);
            return created instanceof EntityLivingBase ? (EntityLivingBase)created : null;
        });
        if (ent == null) {
            return;
        }
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
            float age = (float)this.player.field_70173_aa + this.field_146297_k.func_184121_ak();
            ent.field_184619_aG = age * 0.9f;
            ent.field_184618_aE = ent.field_70721_aZ = 1.2f;
            ent.func_70031_b(true);
        } else {
            ent.field_184619_aG = 0.0f;
            ent.field_70721_aZ = 0.0f;
            ent.field_184618_aE = 0.0f;
            ent.func_70031_b(false);
        }
        ent.field_70125_A = 0.0f;
        ent.field_70127_C = 0.0f;
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
        if (extraShrink > 0.0f) {
            scale *= extraShrink;
        }
        GlStateManager.func_179094_E();
        try {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179126_j();
            GlStateManager.func_179140_f();
            RenderHelper.func_74519_b();
            GlStateManager.func_179086_m((int)256);
            GlStateManager.func_179109_b((float)cx, (float)cy, (float)150.0f);
            GlStateManager.func_179152_a((float)(-scale), (float)scale, (float)scale);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.func_179114_b((float)pitchDeg, (float)1.0f, (float)0.0f, (float)0.0f);
            RenderManager rm = this.field_146297_k.func_175598_ae();
            boolean prevShadow = rm.func_178627_a();
            float prevViewY = rm.field_78735_i;
            rm.func_178631_a(180.0f);
            rm.func_178633_a(false);
            rm.func_188391_a((Entity)ent, 0.0, 0.0, 0.0, 0.0f, this.field_146297_k.func_184121_ak(), true);
            rm.func_178633_a(prevShadow);
            rm.func_178631_a(prevViewY);
            RenderHelper.func_74518_a();
            GlStateManager.func_179101_C();
            GlStateManager.func_179097_i();
            GlStateManager.func_179140_f();
            GlStateManager.func_179084_k();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179098_w();
            GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
            GlStateManager.func_179090_x();
            GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
            GlStateManager.func_179098_w();
        }
        finally {
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

    private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, int marginPx, float extraShrink) {
        this.renderEntityPreview(mobId, cx, cy, boxW, boxH, yawDeg, 0.0f, marginPx, extraShrink);
    }

    public boolean func_73868_f() {
        return false;
    }

    static {
        Function<String, ItemStack> byId = id -> {
            Item it = Item.func_111206_d((String)id);
            return it != null ? new ItemStack(it) : ItemStack.field_190927_a;
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
        Item it = Item.func_111206_d((String)"srparasites:itemtab");
        DEFAULT_TIER_ICON = it != null ? new ItemStack(it) : ItemStack.field_190927_a;
    }

    private class AnimatedButton
    extends GuiButton {
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
            if (!this.field_146125_m) {
                return;
            }
            float a = ParasitesPage.this.page == BestiaryPage.MOB_DETAIL ? ParasitesPage.smoothstep(ParasitesPage.this.mobDetailAnim) : (ParasitesPage.this.page == BestiaryPage.MOB_LIST ? ParasitesPage.smoothstep(ParasitesPage.this.mobListAnim) : (ParasitesPage.this.page == BestiaryPage.PARASITES ? ParasitesPage.smoothstep(ParasitesPage.this.tierListAnim) : 1.0f));
            int x = this.baseX;
            int y = this.baseY;
            if (ParasitesPage.this.page == BestiaryPage.MOB_DETAIL || ParasitesPage.this.page == BestiaryPage.MOB_LIST || ParasitesPage.this.page == BestiaryPage.PARASITES) {
                if (this.group == 0) {
                    int offUp = -(this.baseY + this.field_146121_g + 8);
                    y = this.baseY + (int)((float)offUp * (1.0f - a));
                } else if (this.group == 1) {
                    int offDown = mc.field_71440_d + 60;
                    y = this.baseY + (int)((float)offDown * (1.0f - a));
                }
            }
            int ox = this.field_146128_h;
            int oy = this.field_146129_i;
            this.field_146128_h = x;
            this.field_146129_i = y;
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
            super.func_191745_a(mc, mouseX, mouseY, partialTicks);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
            this.field_146128_h = ox;
            this.field_146129_i = oy;
        }
    }

    private static class TierButton
    extends ListButton {
        final ParasiteTier tier;
        final ItemStack icon;

        TierButton(int id, int x, int rowTopY, int w, int h, String text, ParasiteTier tier, ItemStack icon) {
            super(id, x, rowTopY, w, h, text);
            this.tier = tier;
            this.icon = icon == null ? ItemStack.field_190927_a : icon;
        }

        public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            ParasitesPage g;
            if (!this.field_146125_m) {
                return;
            }
            GuiScreen s = mc.field_71462_r;
            float a = 1.0f;
            int xOff = 0;
            if (s instanceof ParasitesPage && (g = (ParasitesPage)s).page == BestiaryPage.PARASITES) {
                a = ParasitesPage.smoothstep(g.tierListAnim);
                xOff = g.getTierPageXOff();
            }
            int ox = this.field_146128_h;
            int oy = this.field_146129_i;
            this.field_146128_h = ox + xOff;
            boolean hover = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int oxx = this.field_146128_h;
            if (hover) {
                this.field_146128_h = oxx + 2;
            }
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
            super.func_191745_a(mc, mouseX - xOff, mouseY, partialTicks);
            if (!this.icon.func_190926_b()) {
                int iconX = this.field_146128_h - 5;
                int iconY = this.field_146129_i - 5;
                RenderHelper.func_74520_c();
                mc.func_175599_af().func_180450_b(this.icon, iconX, iconY);
                RenderHelper.func_74518_a();
            }
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
            this.field_146128_h = ox;
            this.field_146129_i = oy;
        }
    }

    private static class MobListEntryButton
    extends ListButton {
        MobListEntryButton(int id, int x, int rowTopY, int w, int h, String txt) {
            super(id, x, rowTopY, w, h, txt);
        }

        public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (!this.field_146125_m) {
                return;
            }
            GuiScreen s = mc.field_71462_r;
            if (!(s instanceof ParasitesPage)) {
                super.func_191745_a(mc, mouseX, mouseY, partialTicks);
                return;
            }
            ParasitesPage g = (ParasitesPage)s;
            float a = ParasitesPage.smoothstep(g.mobListAnim);
            int leftOffX = -230;
            int xOff = (int)((float)leftOffX * (1.0f - a));
            int ox = this.field_146128_h;
            int oy = this.field_146129_i;
            this.field_146128_h = ox + xOff;
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
            super.func_191745_a(mc, mouseX - xOff, mouseY, partialTicks);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
            this.field_146128_h = ox;
            this.field_146129_i = oy;
        }
    }

    private static class ListButton
    extends GuiButton {
        final int rowTopY;

        ListButton(int id, int x, int rowTopY, int w, int h, String txt) {
            super(id, x, rowTopY, w, h, txt);
            this.rowTopY = rowTopY;
        }
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

    private static enum BestiaryPage {
        HOME,
        PARASITES,
        MOB_LIST,
        MOB_DETAIL;

    }
}

