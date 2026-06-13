/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.input.Mouse
 */
package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.BestiaryEntry;
import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.BlocksPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.CelestialEventsPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.ParasitesPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.StatsPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.StatusEffectsPage;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.SystemsPage;
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
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class GuiBestiary
extends GuiScreen {
    private final EntityPlayer player;
    private BestiaryPage page = BestiaryPage.HOME;
    private ParasiteTier selectedTier = null;
    private final ParasitesPage parasitesPage;
    private boolean isJumbled;
    private BestiaryEntry selectedMob = null;
    private final List<ParasiteTier> visibleTiers = new ArrayList<ParasiteTier>();
    private final List<BestiaryEntry> visibleMobs = new ArrayList<BestiaryEntry>();
    private final Map<String, EntityLivingBase> entityCache = new HashMap<String, EntityLivingBase>();
    private float spinDeg = 0.0f;
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
    private static final float UI_THUMB_SHRINK = 0.92f;
    private static final float UI_ICON_SHRINK = 0.92f;
    private static final float UI_DETAIL_SHRINK = 0.88f;
    private static final float UI_SCALE_MIN = 6.0f;
    private static final float UI_SCALE_MAX = 110.0f;

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
        ArrayList<ParasiteTier> list = new ArrayList<ParasiteTier>(Arrays.asList(ParasiteTier.values()));
        list.sort(Comparator.comparing(t -> I18n.func_135052_a((String)GuiBestiary.tierLangKey(t), (Object[])new Object[0]).toLowerCase(Locale.ROOT)));
        return list;
    }

    private void applyTierScrollLayout() {
        int contentH = this.visibleTiers.size() * 36;
        int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - 50));
        this.scrollTiers = Math.max(0, Math.min(this.scrollTiers, maxScroll));
        for (GuiButton b : this.field_146292_n) {
            boolean inView;
            if (!(b instanceof ListButton) || b.field_146127_k < 100 || b.field_146127_k >= 200) continue;
            ListButton lb = (ListButton)b;
            int rowY = lb.rowTopY - this.scrollTiers;
            b.field_146129_i = rowY + 4;
            b.field_146125_m = inView = rowY + 28 > 50 && rowY < this.LIST_BOTTOM();
        }
    }

    private void applyMobScrollLayout() {
        int contentH = this.visibleMobs.size() * 36;
        int maxScroll = Math.max(0, contentH - (this.LIST_BOTTOM() - 50));
        this.scrollMobs = Math.max(0, Math.min(this.scrollMobs, maxScroll));
        for (GuiButton b : this.field_146292_n) {
            boolean inView;
            if (!(b instanceof ListButton) || b.field_146127_k < 200 || b.field_146127_k >= 300) continue;
            ListButton lb = (ListButton)b;
            int rowY = lb.rowTopY - this.scrollMobs;
            b.field_146129_i = rowY + 4;
            b.field_146125_m = inView = rowY + 28 > 50 && rowY < this.LIST_BOTTOM();
        }
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        int dWheel = Mouse.getEventDWheel();
        if (dWheel == 0) {
            return;
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
        try {
            ResourceLocation rl = new ResourceLocation(mobId);
            return "lore." + rl.func_110624_b() + "." + rl.func_110623_a();
        }
        catch (Exception ignored) {
            int i = mobId.indexOf(58);
            if (i > 0 && i < mobId.length() - 1) {
                return "lore." + mobId.substring(0, i) + "." + mobId.substring(i + 1);
            }
            return "lore." + mobId;
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
            case HOME: {
                int cx = this.field_146294_l / 2;
                int startY = 75;
                int gap = 24;
                int w = 150;
                int x = cx - w / 2;
                this.field_146292_n.add(new GuiButton(10, x, startY + gap * 0, w, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.parasites", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(11, x, startY + gap * 1, w, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.blocks", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(12, x, startY + gap * 2, w, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.celestial", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(13, x, startY + gap * 3, w, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.effects", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(14, x, startY + gap * 4, w, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.systems", (Object[])new Object[0]))));
                this.field_146292_n.add(new GuiButton(15, x, startY + gap * 5, w, 20, this.distort(I18n.func_135052_a((String)"bestiary.tab.stats", (Object[])new Object[0]))));
                break;
            }
            case MOB_LIST: {
                this.visibleMobs.clear();
                IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
                if (prog != null && this.selectedTier != null) {
                    for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
                        if (e.tier != this.selectedTier || !GuiBestiary.isKnown(prog, e)) continue;
                        this.visibleMobs.add(e);
                    }
                }
                int rowTop = 50;
                for (int i = 0; i < this.visibleMobs.size(); ++i) {
                    BestiaryEntry e = this.visibleMobs.get(i);
                    String name = this.distort(I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]));
                    this.field_146292_n.add(new ListButton(200 + i, 30, rowTop, 140, 20, name));
                    rowTop += 36;
                }
                this.applyMobScrollLayout();
                this.field_146292_n.add(new GuiButton(2, 10, 10, 80, 20, this.distort(I18n.func_135052_a((String)"bestiary.nav.tiers", (Object[])new Object[0]))));
                break;
            }
            case MOB_DETAIL: {
                this.field_146292_n.add(new GuiButton(3, 10, 10, 100, 20, I18n.func_135052_a((String)"bestiary.nav.mob_list", (Object[])new Object[0])));
            }
        }
    }

    private void drawPanel(int x, int y, int w, int h) {
        GuiBestiary.func_73734_a((int)(x - 1), (int)(y - 1), (int)(x + w + 1), (int)(y + h + 1), (int)-1442840576);
        GuiBestiary.func_73734_a((int)x, (int)y, (int)(x + w), (int)(y + h), (int)-2013265920);
    }

    private void drawHeaderBar(int x, int y, int w, int h) {
        GuiBestiary.func_73734_a((int)x, (int)y, (int)(x + w), (int)(y + h), (int)-1441722095);
        GuiBestiary.func_73734_a((int)x, (int)(y + h - 1), (int)(x + w), (int)(y + h), (int)-1442840576);
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
        if (button.field_146127_k == 1) {
            this.page = BestiaryPage.HOME;
            this.selectedTier = null;
            this.selectedMob = null;
            this.func_73866_w_();
            return;
        }
        switch (this.page) {
            case HOME: {
                if (button.field_146127_k == 10) {
                    this.parasitesPage.openParasitesRoot();
                    this.field_146297_k.func_147108_a((GuiScreen)this.parasitesPage);
                    return;
                }
                if (button.field_146127_k == 11) {
                    this.field_146297_k.func_147108_a((GuiScreen)new BlocksPage(this.player, this));
                    return;
                }
                if (button.field_146127_k == 12) {
                    this.field_146297_k.func_147108_a((GuiScreen)new CelestialEventsPage(this.player, this));
                    return;
                }
                if (button.field_146127_k == 13) {
                    this.field_146297_k.func_147108_a((GuiScreen)new StatusEffectsPage(this.player, this));
                    return;
                }
                if (button.field_146127_k == 14) {
                    this.field_146297_k.func_147108_a((GuiScreen)new SystemsPage(this.player, this));
                    return;
                }
                if (button.field_146127_k != 15) break;
                this.field_146297_k.func_147108_a((GuiScreen)new StatsPage(this.player, this));
                return;
            }
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        String title = this.distort(I18n.func_135052_a((String)"lore.srparasites.compendium", (Object[])new Object[0]));
        int titleW = this.field_146289_q.func_78256_a(title);
        this.func_73731_b(this.field_146289_q, title, this.field_146294_l - titleW - 10, 12, 0xFFFFFF);
        this.spinDeg += partialTicks * 1.5f;
        switch (this.page) {
            case HOME: {
                int cardW = 170;
                int cardH = 166;
                int cardX = (this.field_146294_l - cardW) / 2;
                int cardY = 55;
                this.drawPanel(cardX, cardY, cardW, cardH);
                this.drawHeaderBar(cardX, cardY, cardW, 16);
                this.func_73732_a(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.select_category", (Object[])new Object[0])), this.field_146294_l / 2, cardY + 4, 0xAAAAAA);
                break;
            }
            case PARASITES: {
                this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.parasite_tiers", (Object[])new Object[0])), 20, 50, 0xFFFFFF);
                if (this.selectedTier == null) break;
                this.drawTierPreview(this.selectedTier, 180, 70);
                break;
            }
            case MOB_LIST: {
                int leftX = 22;
                int leftY = 36;
                int leftW = 156;
                int leftH = this.LIST_BOTTOM() - 50 + 22;
                this.drawPanel(leftX, leftY, leftW, leftH);
                this.drawHeaderBar(leftX, leftY, leftW, 16);
                this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.mob_list", (Object[])new Object[0])), leftX + 6, leftY + 4, 0xCCCCCC);
                int rightX = 182;
                int rightY = 36;
                int rightW = this.field_146294_l - rightX - 12;
                int rightH = leftH;
                this.drawPanel(rightX, rightY, rightW, rightH);
                this.drawHeaderBar(rightX, rightY, rightW, 16);
                break;
            }
            case MOB_DETAIL: {
                if (this.selectedMob == null) break;
                this.drawMobDetail(this.selectedMob, 20, 40);
            }
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    public void drawTierPreview(ParasiteTier tier, int x, int y) {
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        int shown = 0;
        for (BestiaryEntry e : SRPBestiaryRegistry.all()) {
            if (e.tier != tier || !GuiBestiary.isKnown(prog, e)) continue;
            String name = this.distort(I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]));
            this.field_146289_q.func_78276_b(name, x + 30, y + 4, 0xDDDDDD);
            float scale = SRPBestiaryRegistry.getRenderScale(e.mobId);
            this.renderEntityPreview(e.mobId, x + 14, y + 14, 28, 28, this.spinDeg, 3, 0.92f);
            y += 32;
            if (++shown < 5) continue;
            break;
        }
        if (shown == 0) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a((String)"bestiary.unlock_by_kill", (Object[])new Object[0])), x, y, 0x777777);
        }
    }

    private void drawMobListWithRenders(List<BestiaryEntry> mobs, int xRight) {
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        int rowY = 50 - this.scrollMobs;
        for (BestiaryEntry e : mobs) {
            int rowTop = rowY;
            int rowBot = rowTop + 28;
            if (rowBot > 50 && rowTop < this.LIST_BOTTOM()) {
                int centerY = rowTop + 14;
                this.renderEntityPreview(e.mobId, xRight + 14, centerY, 28, 28, this.spinDeg, 6, 0.92f);
                int textY = centerY - this.field_146289_q.field_78288_b / 2;
                int kills = prog.getKills(e.mobId);
                String rowPlain = I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]) + " (" + I18n.func_135052_a((String)"bestiary.kills_label", (Object[])new Object[0]) + ": " + kills + ")";
                String rowDraw = this.isJumbled ? GuiDistortionHelper.jamText(rowPlain) : rowPlain;
                this.field_146289_q.func_78276_b(rowDraw, xRight + 28 + 8, textY, 0xCCCCCC);
            }
            rowY += 36;
        }
        if (mobs.isEmpty()) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a((String)"bestiary.no_mobs_unlocked_in_tier", (Object[])new Object[0])), xRight, 50, 0x777777);
        }
    }

    private void drawMobDetail(BestiaryEntry e, int x, int y) {
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            this.field_146289_q.func_78276_b(this.distort(I18n.func_135052_a((String)"bestiary.cap_missing_client", (Object[])new Object[0])), x, y, 0xFF5555);
            return;
        }
        int kills = prog.getKills(e.mobId);
        String nameLinePlain = I18n.func_135052_a((String)e.nameKey, (Object[])new Object[0]) + " (" + I18n.func_135052_a((String)"bestiary.kills", (Object[])new Object[0]) + ": " + kills + ")";
        String nameLine = this.isJumbled ? GuiDistortionHelper.jamText(nameLinePlain) : nameLinePlain;
        this.field_146289_q.func_78276_b(nameLine, x, y, 0xFFFFFF);
        int boxW = 160;
        int boxH = 120;
        int cx = x + 80;
        int cy = (y += 6) + 10 + 60;
        this.renderEntityPreview(e.mobId, cx, cy, 160, 120, this.spinDeg, 12, 0.88f);
        int infoX = x + 160 + 14;
        int infoY = y + 4;
        if (kills >= 3) {
            double hp = e.baseHp > 0 ? (double)e.baseHp : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111267_a);
            double dmg = e.baseDamage > 0.0f ? (double)e.baseDamage : this.readEntityStat(e.mobId, SharedMonsterAttributes.field_111264_e);
            String statsLine = this.distort(I18n.func_135052_a((String)"bestiary.stats", (Object[])new Object[]{String.valueOf((int)Math.round(hp)), String.valueOf((float)dmg)}));
            this.field_146289_q.func_78276_b(statsLine, infoX, infoY, 0xAAAAAA);
            infoY += 12;
        } else {
            String gate3 = this.distort(I18n.func_135052_a((String)"bestiary.more_info_at_n", (Object[])new Object[]{3}));
            this.field_146289_q.func_78276_b(gate3, infoX, infoY, 0x666666);
            infoY += 12;
        }
        y = y + 10 + 120 + 10;
        if (kills >= 10) {
            String lore = I18n.func_135052_a((String)GuiBestiary.loreKeyFromMobId(e.mobId), (Object[])new Object[0]);
            this.field_146289_q.func_78279_b(lore, x, y + 6, this.field_146294_l - x - 20, 0xCCCCCC);
        } else {
            String gate10 = this.distort(I18n.func_135052_a((String)"bestiary.lore_unlocks_at_n", (Object[])new Object[]{10}));
            this.field_146289_q.func_78276_b(gate10, x, y + 6, 0x666666);
        }
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void renderEntityPreview(String mobId, int cx, int cy, int boxW, int boxH, float yawDeg, int marginPx, float extraShrink) {
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
        ent.field_70173_aa = 0;
        ent.field_184618_aE = 0.0f;
        ent.field_70721_aZ = 0.0f;
        ent.field_184619_aG = 0.0f;
        ent.field_70127_C = 0.0f;
        ent.field_70125_A = 0.0f;
        ent.field_70761_aq = ent.field_70760_ar = yawDeg;
        ent.field_70759_as = ent.field_70758_at = yawDeg;
        ent.field_70177_z = ent.field_70126_B = yawDeg;
        int innerW = Math.max(0, boxW - (marginPx << 1));
        int innerH = Math.max(0, boxH - (marginPx << 1));
        float scale = this.computeAutoScale(ent, innerW, innerH);
        scale *= SRPBestiaryRegistry.getRenderScale(mobId);
        if (extraShrink > 0.0f) {
            scale *= extraShrink;
        }
        scale = Math.max(6.0f, Math.min(scale, 110.0f));
        GlStateManager.func_179094_E();
        try {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179126_j();
            GlStateManager.func_179140_f();
            RenderHelper.func_74519_b();
            GlStateManager.func_179109_b((float)cx, (float)cy, (float)50.0f);
            GlStateManager.func_179152_a((float)(-scale), (float)scale, (float)scale);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
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
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
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

    public boolean func_73868_f() {
        return false;
    }

    public static class TierButton
    extends GuiButton {
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
            if (!this.field_146125_m) {
                return;
            }
            int iconX = this.field_146128_h + 4;
            int iconY = this.field_146129_i + (this.field_146121_g - 16) / 2;
            RenderHelper.func_74520_c();
            mc.func_175599_af().func_180450_b(this.icon, iconX, iconY);
            RenderHelper.func_74518_a();
        }
    }

    private static class ListButton
    extends GuiButton {
        final int rowTopY;

        ListButton(int id, int x, int rowTopY, int w, int h, String txt) {
            super(id, x, rowTopY, w, h, txt);
            this.rowTopY = rowTopY;
        }

        public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (!this.field_146125_m) {
                return;
            }
            boolean hover = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int bg = hover ? 0x66222222 : 0x44111111;
            ListButton.func_73734_a((int)(this.field_146128_h - 2), (int)(this.field_146129_i - 2), (int)(this.field_146128_h + this.field_146120_f + 2), (int)(this.field_146129_i + this.field_146121_g + 2), (int)-1442840576);
            ListButton.func_73734_a((int)(this.field_146128_h - 1), (int)(this.field_146129_i - 1), (int)(this.field_146128_h + this.field_146120_f + 1), (int)(this.field_146129_i + this.field_146121_g + 1), (int)bg);
            int color = hover ? 0xFFFFFF : 0xDDDDDD;
            this.func_73731_b(mc.field_71466_p, this.field_146126_j, this.field_146128_h + 6, this.field_146129_i + 6, color);
        }
    }

    private static enum BestiaryPage {
        HOME,
        PARASITES,
        MOB_LIST,
        MOB_DETAIL,
        STATS;

    }
}

