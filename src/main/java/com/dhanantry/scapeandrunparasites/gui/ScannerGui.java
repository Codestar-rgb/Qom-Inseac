package com.dhanantry.scapeandrunparasites.gui;

import com.dhanantry.scapeandrunparasites.container.ScannerContainer;
import com.dhanantry.scapeandrunparasites.network.MsgRequestScan;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class ScannerGui extends GuiContainer {
   private static final ResourceLocation BG = new ResourceLocation("srparasites", "textures/gui/scanner_gui.png");
   private static final int MODULE_SLOT_INDEX = 0;
   private final TileEntityRelayController te;
   private GuiButton scanBtn;
   private int cdTotalTicks = 0;
   private long cdEndMs = 0L;
   private float cdSlide = 0.0F;

   public ScannerGui(InventoryPlayer playerInv, TileEntityRelayController te) {
      super(new ScannerContainer(playerInv, te));
      this.te = te;
      this.field_146999_f = 176;
      this.field_147000_g = 166;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      int x = (this.field_146294_l - this.field_146999_f) / 2;
      int y = (this.field_146295_m - this.field_147000_g) / 2;
      this.scanBtn = new GuiButton(0, x + 120, y + 34, 48, 20, I18n.func_135052_a("chat.srparasites.relay.scan_text", new Object[0]));
      this.field_146292_n.add(this.scanBtn);

      try {
         int remaining = this.te.getCooldownRemainingTicks();
         int total = this.te.getCooldownTotalTicks();
         if (remaining > 0 && total > 0) {
            this.cdTotalTicks = total;
            this.cdEndMs = System.currentTimeMillis() + remaining * 50L;
            this.cdSlide = 1.0F;
         } else {
            this.cdTotalTicks = total;
            this.cdEndMs = 0L;
            this.cdSlide = 0.0F;
         }
      } catch (Throwable var5) {
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
      } else {
         long now = System.currentTimeMillis();
         return now < this.cdEndMs;
      }
   }

   private void updateScanButtonEnabled() {
      if (this.scanBtn != null) {
         boolean hasModule = false;
         if (!this.field_147002_h.field_75151_b.isEmpty()) {
            Slot moduleSlot = this.field_147002_h.func_75139_a(0);
            hasModule = moduleSlot != null && moduleSlot.func_75216_d();
         }

         boolean formed = this.te != null && this.te.formed;
         this.scanBtn.field_146124_l = hasModule && formed && !this.isCooldownVisible();
      }
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      if (button == this.scanBtn) {
         SRPNetwork.CHANNEL.sendToServer(new MsgRequestScan(this.te.func_174877_v()));
      }

      super.func_146284_a(button);
   }

   public void onCooldownUpdate(int totalTicks, int remainingTicks, boolean started) {
      this.cdTotalTicks = Math.max(0, totalTicks);
      long now = System.currentTimeMillis();
      this.cdEndMs = now + Math.max(0, remainingTicks) * 50L;
      if (this.isCooldownVisible() && this.cdSlide < 0.1F) {
         this.cdSlide = 0.1F;
      }

      this.updateScanButtonEnabled();
   }

   private void animateCooldownBar() {
      boolean visible = this.isCooldownVisible();
      float target = visible ? 1.0F : 0.0F;
      this.cdSlide = this.cdSlide + (target - this.cdSlide) * 0.25F;
      if (!visible && this.cdSlide < 0.01F) {
         this.cdSlide = 0.0F;
      }
   }

   private void drawCooldownBar(int mouseX, int mouseY, float partialTicks) {
      if (!(this.cdSlide <= 0.0F) && this.cdTotalTicks > 0) {
         int barH = 20;
         int x = this.field_147003_i;
         int w = this.field_146999_f;
         int yHidden = this.field_147009_r + this.field_147000_g + barH;
         int yShown = this.field_147009_r + this.field_147000_g;
         int y = (int)(yHidden + (yShown - yHidden) * this.cdSlide);
         func_73734_a(x, y, x + w, y + barH, -1442840576);
         long now = System.currentTimeMillis();
         float remainingMs = (float)Math.max(0L, this.cdEndMs - now);
         float totalMs = this.cdTotalTicks * 50.0F;
         float progress = totalMs > 0.0F ? 1.0F - remainingMs / totalMs : 0.0F;
         if (progress < 0.0F) {
            progress = 0.0F;
         }

         if (progress > 1.0F) {
            progress = 1.0F;
         }

         int pad = 2;
         int filled = (int)((w - pad * 2) * progress);
         func_73734_a(x + pad, y + pad, x + pad + filled, y + barH - pad, -11141291);
         int secs = (int)Math.ceil(remainingMs / 1000.0);
         String label = I18n.func_135052_a("gui.srparasites.scanner.cooldown", new Object[]{secs});
         this.field_146289_q.func_78276_b(label, x + 6, y + 6, 16777215);
      }
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
