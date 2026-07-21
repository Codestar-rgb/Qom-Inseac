package com.dhanantry.scapeandrunparasites.gui;

import com.dhanantry.scapeandrunparasites.container.ContainerParasiteLoot;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityParasiteLoot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiParasiteLoot extends GuiContainer {
   private static final ResourceLocation BG = new ResourceLocation("srparasites", "textures/gui/parasite_loot.png");
   private static final ResourceLocation BUBBLE_TEX = new ResourceLocation("srparasites", "textures/gui/blood_bubble.png");
   private final List<GuiParasiteLoot.Bubble> bubbles = new ArrayList<>();
   private final Random fxRand = new Random();
   private int guiTick = 0;
   private static final int MAX_BUBBLES = 28;
   private static final float MIN_SIZE = 8.0F;
   private static final float MAX_SIZE = 24.0F;
   private static final float MIN_SPEED = 0.25F;
   private static final float MAX_SPEED = 0.9F;
   private static final float MAX_ROTSPD = 0.6F;
   private int boostStartTick = -1;
   private int boostActiveUntil = -1;
   private int boostCooldownUntil = 0;
   private static final int BOOST_DURATION_TICKS = 10;
   private static final int BOOST_COOLDOWN_TICKS = 10;
   private static final float BOOST_STRENGTH = 1.25F;
   private int prevFullnessScaled = -1;
   private final TileEntityParasiteLoot te;
   private int fullnessScaled = 0;

   public GuiParasiteLoot(InventoryPlayer inv, TileEntityParasiteLoot te) {
      super(new ContainerParasiteLoot(inv, te, inv.field_70458_d));
      this.te = te;
      this.field_146999_f = 176;
      this.field_147000_g = 166;
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      super.func_73863_a(mouseX, mouseY, partialTicks);
      this.func_191948_b(mouseX, mouseY);
   }

   protected void func_146979_b(int mouseX, int mouseY) {
   }

   protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(BUBBLE_TEX);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179140_f();
      float oldZ = this.field_73735_i;
      this.field_73735_i = -100.0F;

      for (GuiParasiteLoot.Bubble b : this.bubbles) {
         GlStateManager.func_179094_E();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, b.alpha);
         GlStateManager.func_179109_b(b.x + b.size / 2.0F, b.y + b.size / 2.0F, 0.0F);
         GlStateManager.func_179114_b(b.rot, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179109_b(-b.size / 2.0F, -b.size / 2.0F, 0.0F);
         func_146110_a(0, 0, 0.0F, 0.0F, (int)b.size, (int)b.size, (int)b.size, (int)b.size);
         GlStateManager.func_179121_F();
      }

      this.field_73735_i = oldZ;
      GlStateManager.func_179084_k();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(BG);
      int x = (this.field_146294_l - this.field_146999_f) / 2;
      int y = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
      int barMax = 160;
      int barH = 6;
      float f = this.fullnessScaled / 1000.0F;
      int barFilled = Math.round(barMax * f);
      int barX = x + 8;
      int barY = y + 16;
      func_73734_a(barX, barY, barX + barMax, barY + barH, -14671840);
      int fillColor = colorRedYellowGreen(f);
      func_73734_a(barX, barY, barX + barFilled, barY + barH, fillColor);
   }

   private void triggerBubbleBoost() {
      if (this.guiTick >= this.boostCooldownUntil) {
         this.boostStartTick = this.guiTick;
         this.boostActiveUntil = this.guiTick + 10;
         this.boostCooldownUntil = this.guiTick + 10;
      }
   }

   private float currentBoostMultiplier() {
      if (this.guiTick < this.boostActiveUntil && this.boostStartTick >= 0) {
         float t = (this.guiTick - this.boostStartTick) / 10.0F;
         float pulse = (float)Math.sin(Math.PI * t);
         return 1.0F + 1.25F * pulse;
      } else {
         return 1.0F;
      }
   }

   private void spawnBubble() {
      GuiParasiteLoot.Bubble b = new GuiParasiteLoot.Bubble();
      b.size = 8.0F + this.fxRand.nextFloat() * 16.0F;
      b.x = this.fxRand.nextFloat() * (this.field_146294_l - b.size);
      b.y = this.field_146295_m + b.size;
      b.rot = this.fxRand.nextFloat() * 360.0F;
      b.rotSpeed = (this.fxRand.nextFloat() * 2.0F - 1.0F) * 0.6F;
      b.speed = 0.25F + this.fxRand.nextFloat() * 0.65F;
      b.alpha = 0.35F + this.fxRand.nextFloat() * 0.35F;
      b.vx = (this.fxRand.nextFloat() * 2.0F - 1.0F) * 0.15F;
      if (this.fxRand.nextFloat() < 0.35F) {
         b.splitTick = this.guiTick + 100 + this.fxRand.nextInt(101);
      } else {
         b.splitTick = -1;
      }

      this.bubbles.add(b);
   }

   private List<GuiParasiteLoot.Bubble> makeChildBubbles(GuiParasiteLoot.Bubble parent) {
      int room = Math.max(0, 28 - this.bubbles.size());
      if (room <= 0) {
         return Collections.emptyList();
      } else if (parent.size < 12.8F) {
         return Collections.emptyList();
      } else {
         int count = Math.min(2, room);
         List<GuiParasiteLoot.Bubble> out = new ArrayList<>(count);

         for (int i = 0; i < count; i++) {
            GuiParasiteLoot.Bubble c = new GuiParasiteLoot.Bubble();
            float scale = 0.5F + this.fxRand.nextFloat() * 0.2F;
            c.size = Math.max(8.0F, parent.size * scale);
            float offset = i == 0 ? -c.size * 0.4F : c.size * 0.4F;
            c.x = Math.max(0.0F, Math.min(this.field_146294_l - c.size, parent.x + offset));
            c.y = parent.y + this.fxRand.nextFloat() * 2.0F;
            c.rot = this.fxRand.nextFloat() * 360.0F;
            c.rotSpeed = (this.fxRand.nextFloat() * 2.0F - 1.0F) * 0.6F;
            c.speed = Math.min(1.125F, parent.speed * (1.05F + this.fxRand.nextFloat() * 0.15F));
            c.alpha = Math.min(1.0F, parent.alpha * (0.9F + this.fxRand.nextFloat() * 0.2F));
            c.vx = (this.fxRand.nextFloat() * 2.0F - 1.0F) * 0.2F;
            if (c.size > 14.4F && this.fxRand.nextFloat() < 0.15F) {
               c.splitTick = this.guiTick + 80 + this.fxRand.nextInt(81);
            } else {
               c.splitTick = -1;
            }

            out.add(c);
         }

         return out;
      }
   }

   private static int colorRedYellowGreen(float f) {
      if (f < 0.0F) {
         f = 0.0F;
      } else if (f > 1.0F) {
         f = 1.0F;
      }

      int r;
      int g;
      if (f < 0.5F) {
         r = 255;
         g = (int)(f / 0.5F * 255.0F);
      } else {
         r = (int)((1.0F - f) / 0.5F * 255.0F);
         g = 255;
      }

      int a = 255;
      int b = 0;
      return a << 24 | r << 16 | g << 8 | b;
   }

   public void func_73876_c() {
      super.func_73876_c();
      this.guiTick++;
      int newFullnessScaled = this.te.func_174887_a_(0);
      if (this.prevFullnessScaled == -1) {
         this.prevFullnessScaled = newFullnessScaled;
      } else if (newFullnessScaled != this.prevFullnessScaled) {
         this.triggerBubbleBoost();
         this.prevFullnessScaled = newFullnessScaled;
      }

      this.fullnessScaled = newFullnessScaled;
      float boost = this.currentBoostMultiplier();
      List<GuiParasiteLoot.Bubble> toAdd = null;
      Iterator<GuiParasiteLoot.Bubble> it = this.bubbles.iterator();

      while (it.hasNext()) {
         GuiParasiteLoot.Bubble b = it.next();
         b.y = b.y - b.speed * boost;
         b.x = b.x + b.vx;
         b.rot = b.rot + b.rotSpeed * (0.5F + 0.5F * boost);
         if (b.x < -b.size) {
            b.x = -b.size;
         }

         if (b.x > this.field_146294_l) {
            b.x = this.field_146294_l;
         }

         if (b.splitTick > 0 && this.guiTick >= b.splitTick) {
            List<GuiParasiteLoot.Bubble> children = this.makeChildBubbles(b);
            if (!children.isEmpty()) {
               if (toAdd == null) {
                  toAdd = new ArrayList<>(children.size());
               }

               toAdd.addAll(children);
            }

            it.remove();
         } else if (b.y + b.size < 0.0F) {
            it.remove();
         }
      }

      if (toAdd != null && !toAdd.isEmpty()) {
         int allowed = Math.max(0, 28 - this.bubbles.size());
         if (allowed > 0) {
            if (toAdd.size() > allowed) {
               toAdd = toAdd.subList(0, allowed);
            }

            this.bubbles.addAll(toAdd);
         }
      }

      if (this.bubbles.size() < 28) {
         float f = this.fullnessScaled / 1000.0F;
         float spawnChance = 0.18F + (1.0F - f) * 0.22F;
         if (this.fxRand.nextFloat() < spawnChance) {
            this.spawnBubble();
         }
      }
   }

   private static class Bubble {
      float x;
      float y;
      float size;
      float rot;
      float rotSpeed;
      float speed;
      float alpha;
      float vx;
      int splitTick;

      private Bubble() {
      }
   }
}
