package com.dhanantry.scapeandrunparasites.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.util.text.TextFormatting;

public class SRPConfigList extends GuiSlot {
   private static final int MAX_VALUE_PX = 210;
   private static final int MAX_HEADER_PX = 300;
   private static final int RIGHT_PADDING = 24;
   private static final int SCROLLBAR_OUTSET = 10;
   private static final int SCROLLBAR_VISIBLE_PX = 3;
   private final GuiSRPConfigView screen;
   private final List<SRPConfigList.Entry> entries;

   public SRPConfigList(
      GuiSRPConfigView screen, Minecraft mc, int width, int height, int topIn, int bottomIn, int slotHeightIn, List<SRPConfigList.Entry> entries
   ) {
      super(mc, width, height, topIn, bottomIn, slotHeightIn);
      this.screen = screen;
      this.entries = (List<SRPConfigList.Entry>)(entries != null ? entries : new ArrayList<>());
      this.field_148152_e = 10;
      this.field_148151_d = width - 10;
   }

   protected int func_148137_d() {
      return this.field_148151_d - 6;
   }

   protected int func_148127_b() {
      return this.entries.size();
   }

   private String ellipsizeToWidth(String s, int maxPx) {
      if (s == null) {
         return "";
      } else {
         String plain = stripFormatting(s);
         if (this.field_148161_k.field_71466_p.func_78256_a(plain) <= maxPx) {
            return s;
         } else {
            String dots = "...";
            int dotsW = this.field_148161_k.field_71466_p.func_78256_a(dots);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < plain.length(); i++) {
               sb.append(plain.charAt(i));
               int w = this.field_148161_k.field_71466_p.func_78256_a(sb.toString());
               if (w + dotsW > maxPx) {
                  String cut = sb.toString().trim();
                  return cut + dots;
               }
            }

            return plain;
         }
      }
   }

   protected void func_148144_a(int index, boolean doubleClick, int mouseX, int mouseY) {
      SRPConfigList.Entry e = this.entries.get(index);
      if (e != null && !e.header) {
         this.field_148161_k.func_147108_a(new GuiSRPConfigEdit(this.screen, e));
      }
   }

   protected boolean func_148131_a(int index) {
      return false;
   }

   protected void func_148123_a() {
   }

   private static String prettifyCategoryName(String raw) {
      if (raw == null) {
         return "";
      } else {
         String s = raw;
         if (raw.startsWith("srparasites:")) {
            s = raw.substring("srparasites:".length());
         }

         s = s.replace('.', ' ');
         String[] parts = s.split("_");
         StringBuilder out = new StringBuilder();

         for (String p : parts) {
            if (p != null) {
               p = p.trim();
               if (!p.isEmpty()) {
                  if (out.length() > 0) {
                     out.append(' ');
                  }

                  String lower = p.toLowerCase(Locale.ROOT);
                  out.append(Character.toUpperCase(lower.charAt(0)));
                  if (lower.length() > 1) {
                     out.append(lower.substring(1));
                  }
               }
            }
         }

         return out.toString().trim();
      }
   }

   protected void func_192637_a(int idx, int right, int top, int height, int mouseX, int mouseY, float partialTicks) {
      SRPConfigList.Entry e = this.entries.get(idx);
      if (e.header) {
         String pretty = prettifyCategoryName(e.headerText);
         String txt = TextFormatting.YELLOW.toString() + TextFormatting.BOLD + pretty;
         String headerDraw = this.ellipsizeToWidth(txt, 300);
         int x = this.field_148152_e + 6;
         int y = top + 4;
         this.field_148161_k.field_71466_p.func_78276_b(headerDraw, x, y, 16777215);
         int textW = this.field_148161_k.field_71466_p.func_78256_a(stripFormatting(headerDraw));
         int lineX1 = x + textW + 6;
         int lineX2 = this.field_148151_d - 6;
         int lineY = y + 8;
         if (lineX2 > lineX1) {
            Gui.func_73734_a(lineX1, lineY, lineX2, lineY + 1, 1442840575);
         }
      } else {
         String leftText = e.key;
         int xLeft = this.field_148152_e + 6;
         this.field_148161_k.field_71466_p.func_78276_b(leftText, xLeft, top + 4, 16777215);
         String rawRight = TextFormatting.GRAY + (e.value == null ? "" : e.value);
         String rightText = TextFormatting.GRAY + this.ellipsizeToWidth(rawRight, 210);
         int rightBoxLeft = this.field_148151_d - 6 - 210;
         int w = this.field_148161_k.field_71466_p.func_78256_a(stripFormatting(rightText));
         int xRight = rightBoxLeft + Math.max(0, 210 - w);
         this.field_148161_k.field_71466_p.func_78276_b(rightText, xRight, top + 4, 16777215);
         if (mouseX >= this.field_148152_e
            && mouseX <= this.field_148151_d
            && mouseY >= top
            && mouseY <= top + height
            && e.comment != null
            && !e.comment.trim().isEmpty()) {
            List<String> tip = this.wrapTooltip(e.comment.trim(), 260);
            this.screen.setHoverTooltip(tip);
         }
      }
   }

   private static String stripFormatting(String s) {
      return s == null ? "" : TextFormatting.func_110646_a(s);
   }

   private List<String> wrapTooltip(String text, int maxWidthPx) {
      List<String> out = new ArrayList<>();
      if (text == null) {
         return out;
      } else {
         String[] rawLines = text.split("\n");

         for (String line : rawLines) {
            String cur = "";

            for (String word : line.split(" ")) {
               String test = cur.isEmpty() ? word : cur + " " + word;
               if (this.field_148161_k.field_71466_p.func_78256_a(test) > maxWidthPx && !cur.isEmpty()) {
                  out.add(cur);
                  cur = word;
               } else {
                  cur = test;
               }
            }

            if (!cur.isEmpty()) {
               out.add(cur);
            }
         }

         return out;
      }
   }

   public static class Entry {
      public final boolean header;
      public final String headerText;
      public final String category;
      public final String key;
      public final String value;
      public final String comment;

      private Entry(boolean header, String headerText, String category, String key, String value, String comment) {
         this.header = header;
         this.headerText = headerText;
         this.category = category;
         this.key = key;
         this.value = value;
         this.comment = comment;
      }

      public static SRPConfigList.Entry header(String text) {
         return new SRPConfigList.Entry(true, text, null, null, null, null);
      }

      public static SRPConfigList.Entry prop(String category, String key, String value, String comment) {
         return new SRPConfigList.Entry(false, null, category, key, value, comment);
      }
   }
}
