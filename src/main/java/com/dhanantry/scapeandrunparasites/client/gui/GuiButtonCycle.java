package com.dhanantry.scapeandrunparasites.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

public class GuiButtonCycle extends GuiButton {
   private final String labelKey;
   private final String[] optionKeys;
   private int index;

   public GuiButtonCycle(int buttonId, int x, int y, int width, int height, String labelKey, String[] optionKeys, int defaultIndex) {
      super(buttonId, x, y, width, height, "");
      this.labelKey = labelKey;
      this.optionKeys = optionKeys;
      this.index = this.clampIndex(defaultIndex);
      this.updateDisplayString();
   }

   private int clampIndex(int i) {
      if (this.optionKeys == null || this.optionKeys.length == 0) {
         return 0;
      } else if (i < 0) {
         return 0;
      } else {
         return i >= this.optionKeys.length ? this.optionKeys.length - 1 : i;
      }
   }

   public void cycle() {
      if (this.optionKeys != null && this.optionKeys.length != 0) {
         this.index = (this.index + 1) % this.optionKeys.length;
         this.updateDisplayString();
      }
   }

   public int getIndex() {
      return this.index;
   }

   public String getCurrentOptionKey() {
      return this.optionKeys != null && this.optionKeys.length != 0 ? this.optionKeys[this.index] : "";
   }

   public void updateDisplayString() {
      String label = I18n.func_135052_a(this.labelKey, new Object[0]);
      String option = this.optionKeys != null && this.optionKeys.length > 0 ? I18n.func_135052_a(this.optionKeys[this.index], new Object[0]) : "";
      this.field_146126_j = label + ": " + option;
   }

   public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
      this.updateDisplayString();
      super.func_191745_a(mc, mouseX, mouseY, partialTicks);
   }

   private void drawScaledCenteredString(Minecraft mc, String text, int centerX, int centerY, int maxWidth, int color) {
      int textWidth = mc.field_71466_p.func_78256_a(text);
      if (textWidth <= maxWidth) {
         mc.field_71466_p.func_175063_a(text, centerX - textWidth / 2.0F, centerY, color);
      } else {
         float scale = (float)maxWidth / textWidth;
         GL11.glPushMatrix();
         GL11.glTranslatef(centerX, centerY, 0.0F);
         GL11.glScalef(scale, scale, 1.0F);
         mc.field_71466_p.func_175063_a(text, -textWidth / 2.0F, 0.0F, color);
         GL11.glPopMatrix();
      }
   }
}
