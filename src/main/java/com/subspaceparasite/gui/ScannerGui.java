/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.gui;

import com.subspaceparasite.container.ScannerContainer;
import com.subspaceparasite.network.MsgRequestScan;
import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.tileentity.TileEntityRelayController;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ScannerGui
extends GuiContainer {
    private static final ResourceLocation BG = new ResourceLocation("subspaceparasite", "textures/gui/scanner_gui.png");
    private static final int MODULE_SLOT_INDEX = 0;
    private final TileEntityRelayController te;
    private GuiButton scanBtn;
    private int cdTotalTicks = 0;
    private long cdEndMs = 0L;
    private float cdSlide = 0.0f;

    public ScannerGui(InventoryPlayer playerInv, TileEntityRelayController te) {
        super((Container)new ScannerContainer(playerInv, te));
        this.te = te;
        this.field_146999_f = 176;
        this.field_147000_g = 166;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        this.scanBtn = new GuiButton(0, x + 120, y + 34, 48, 20, I18n.func_135052_a((String)"chat.subspaceparasite.relay.scan_text", (Object[])new Object[0]));
        this.field_146292_n.add(this.scanBtn);
        try {
            int remaining = this.te.getCooldownRemainingTicks();
            int total = this.te.getCooldownTotalTicks();
            if (remaining > 0 && total > 0) {
                this.cdTotalTicks = total;
                this.cdEndMs = System.currentTimeMillis() + (long)remaining * 50L;
                this.cdSlide = 1.0f;
            } else {
                this.cdTotalTicks = total;
                this.cdEndMs = 0L;
                this.cdSlide = 0.0f;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        this.updateScanButtonEnabled();
    }

    public void func_73876_c() {
        super.func_73876_c();
        this.animateCooldownBar();
        this.updateScanButtonEnabled();
    }

    private boolean isCooldownVisible() {
        if (this.cdTotalTicks <= 0) {
            return false;
        }
        long now = System.currentTimeMillis();
        return now < this.cdEndMs;
    }

    private void updateScanButtonEnabled() {
        if (this.scanBtn == null) {
            return;
        }
        boolean hasModule = false;
        if (!this.field_147002_h.field_75151_b.isEmpty()) {
            Slot moduleSlot = this.field_147002_h.func_75139_a(0);
            hasModule = moduleSlot != null && moduleSlot.func_75216_d();
        }
        boolean formed = this.te != null && this.te.formed;
        this.scanBtn.field_146124_l = hasModule && formed && !this.isCooldownVisible();
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        if (button == this.scanBtn) {
            SPNetwork.CHANNEL.sendToServer((IMessage)new MsgRequestScan(this.te.func_174877_v()));
        }
        super.func_146284_a(button);
    }

    public void onCooldownUpdate(int totalTicks, int remainingTicks, boolean started) {
        this.cdTotalTicks = Math.max(0, totalTicks);
        long now = System.currentTimeMillis();
        this.cdEndMs = now + (long)Math.max(0, remainingTicks) * 50L;
        if (this.isCooldownVisible() && this.cdSlide < 0.1f) {
            this.cdSlide = 0.1f;
        }
        this.updateScanButtonEnabled();
    }

    private void animateCooldownBar() {
        boolean visible = this.isCooldownVisible();
        float target = visible ? 1.0f : 0.0f;
        this.cdSlide += (target - this.cdSlide) * 0.25f;
        if (!visible && this.cdSlide < 0.01f) {
            this.cdSlide = 0.0f;
        }
    }

    private void drawCooldownBar(int mouseX, int mouseY, float partialTicks) {
        float progress;
        if (this.cdSlide <= 0.0f || this.cdTotalTicks <= 0) {
            return;
        }
        int barH = 20;
        int x = this.field_147003_i;
        int w = this.field_146999_f;
        int yHidden = this.field_147009_r + this.field_147000_g + barH;
        int yShown = this.field_147009_r + this.field_147000_g;
        int y = (int)((float)yHidden + (float)(yShown - yHidden) * this.cdSlide);
        ScannerGui.func_73734_a((int)x, (int)y, (int)(x + w), (int)(y + barH), (int)-1442840576);
        long now = System.currentTimeMillis();
        float remainingMs = Math.max(0L, this.cdEndMs - now);
        float totalMs = (float)this.cdTotalTicks * 50.0f;
        float f = progress = totalMs > 0.0f ? 1.0f - remainingMs / totalMs : 0.0f;
        if (progress < 0.0f) {
            progress = 0.0f;
        }
        if (progress > 1.0f) {
            progress = 1.0f;
        }
        int pad = 2;
        int filled = (int)((float)(w - pad * 2) * progress);
        ScannerGui.func_73734_a((int)(x + pad), (int)(y + pad), (int)(x + pad + filled), (int)(y + barH - pad), (int)-11141291);
        int secs = (int)Math.ceil((double)remainingMs / 1000.0);
        String label = I18n.func_135052_a((String)"gui.subspaceparasite.scanner.cooldown", (Object[])new Object[]{secs});
        this.field_146289_q.func_78276_b(label, x + 6, y + 6, 0xFFFFFF);
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(BG);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.drawCooldownBar(mouseX, mouseY, partialTicks);
        this.func_191948_b(mouseX, mouseY);
    }
}

