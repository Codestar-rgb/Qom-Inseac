package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class DistortedGuiNewChat extends GuiNewChat {
   private final Minecraft mcRef;
   private final Field drawnChatLinesField;
   private final Field scrollPosField;
   private final Field isScrolledField;
   private final Field sentMessagesField;
   private final Field chatLinesField;

   public DistortedGuiNewChat(Minecraft mcIn) {
      super(mcIn);
      this.mcRef = mcIn;

      try {
         this.drawnChatLinesField = this.findField(GuiNewChat.class, new String[]{"drawnChatLines", "field_146253_i"}, List.class);
         this.scrollPosField = this.findField(GuiNewChat.class, new String[]{"scrollPos", "field_146250_j"}, int.class);
         this.isScrolledField = this.findField(GuiNewChat.class, new String[]{"isScrolled", "field_146251_k"}, boolean.class);
         this.sentMessagesField = this.findField(GuiNewChat.class, new String[]{"sentMessages", "field_146252_h"}, List.class);
         this.chatLinesField = this.findField(GuiNewChat.class, new String[]{"chatLines", "field_146248_g"}, List.class);
      } catch (Exception var3) {
         throw new RuntimeException("Failed to access GuiNewChat fields for distortion chat", var3);
      }
   }

   private Field findField(Class<?> clazz, String[] candidateNames, Class<?> expectedType) throws NoSuchFieldException {
      for (String name : candidateNames) {
         try {
            Field f = clazz.getDeclaredField(name);
            f.setAccessible(true);
            return f;
         } catch (NoSuchFieldException var9) {
         }
      }

      for (Field f : clazz.getDeclaredFields()) {
         if (expectedType.isAssignableFrom(f.getType()) || f.getType() == expectedType) {
            f.setAccessible(true);
            return f;
         }
      }

      throw new NoSuchFieldException(
         "Could not find field in " + clazz.getName() + " for names " + Arrays.toString((Object[])candidateNames) + " and type " + expectedType.getName()
      );
   }

   private List<ChatLine> getDrawnChatLines() {
      try {
         return (List<ChatLine>)this.drawnChatLinesField.get(this);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   private int getScrollPosReflect() {
      try {
         return this.scrollPosField.getInt(this);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   private boolean getIsScrolledReflect() {
      try {
         return this.isScrolledField.getBoolean(this);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   private boolean isLikelyPlayerChat(ITextComponent component) {
      if (component == null) {
         return false;
      } else {
         String plain = component.func_150260_c();
         if (plain == null) {
            return false;
         } else {
            plain = plain.trim();
            return plain.matches("^<[^>]+>\\s.+$");
         }
      }
   }

   public void func_146230_a(int updateCounter) {
      if (this.mcRef.field_71474_y.field_74343_n != EnumChatVisibility.HIDDEN) {
         int i = this.func_146232_i();
         List<ChatLine> drawnChatLines = this.getDrawnChatLines();
         int j = drawnChatLines.size();
         float f = this.mcRef.field_71474_y.field_74357_r * 0.9F + 0.1F;
         if (j > 0) {
            boolean flag = this.func_146241_e();
            float f1 = this.func_146244_h();
            int k = MathHelper.func_76123_f(this.func_146228_f() / f1);
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(2.0F, 8.0F, 0.0F);
            GlStateManager.func_179152_a(f1, f1, 1.0F);
            int l = 0;
            int scrollPos = this.getScrollPosReflect();

            for (int i1 = 0; i1 + scrollPos < drawnChatLines.size() && i1 < i; i1++) {
               ChatLine chatline = drawnChatLines.get(i1 + scrollPos);
               if (chatline != null) {
                  int j1 = updateCounter - chatline.func_74540_b();
                  if (j1 < 200 || flag) {
                     double d0 = j1 / 200.0;
                     d0 = 1.0 - d0;
                     d0 *= 10.0;
                     d0 = MathHelper.func_151237_a(d0, 0.0, 1.0);
                     d0 *= d0;
                     int l1 = (int)(255.0 * d0);
                     if (flag) {
                        l1 = 255;
                     }

                     l1 = (int)(l1 * f);
                     l++;
                     if (l1 > 3) {
                        int j2 = -i1 * 9;
                        func_73734_a(-2, j2 - 9, 0 + k + 4, j2, l1 / 2 << 24);
                        ITextComponent component = chatline.func_151461_a();
                        String s = component.func_150254_d();
                        if (GuiDistortionHelper.shouldDistortChat(this.mcRef) && this.isLikelyPlayerChat(component)) {
                           String plain = TextFormatting.func_110646_a(s);
                           if (plain != null && !plain.isEmpty()) {
                              s = GuiDistortionHelper.jamText(plain);
                           }
                        }

                        GlStateManager.func_179147_l();
                        this.mcRef.field_71466_p.func_175063_a(s, 0.0F, j2 - 8, 16777215 + (l1 << 24));
                        GlStateManager.func_179118_c();
                        GlStateManager.func_179084_k();
                     }
                  }
               }
            }

            if (flag) {
               int k2 = this.mcRef.field_71466_p.field_78288_b;
               GlStateManager.func_179109_b(-3.0F, 0.0F, 0.0F);
               int l2 = j * k2 + j;
               int i3 = l * k2 + l;
               int scrollPosNow = this.getScrollPosReflect();
               int j3 = scrollPosNow * i3 / j;
               int k1 = i3 * i3 / l2;
               if (l2 != i3) {
                  int k3 = j3 > 0 ? 170 : 96;
                  int l3 = this.getIsScrolledReflect() ? 13382451 : 3355562;
                  func_73734_a(0, -j3, 2, -j3 - k1, l3 + (k3 << 24));
                  func_73734_a(2, -j3, 1, -j3 - k1, 13421772 + (k3 << 24));
               }
            }

            GlStateManager.func_179121_F();
         }
      }
   }

   public void copyStateFrom(GuiNewChat oldChat) {
      try {
         this.sentMessagesField.set(this, this.sentMessagesField.get(oldChat));
         this.chatLinesField.set(this, this.chatLinesField.get(oldChat));
         this.drawnChatLinesField.set(this, this.drawnChatLinesField.get(oldChat));
         this.scrollPosField.setInt(this, this.scrollPosField.getInt(oldChat));
         this.isScrolledField.setBoolean(this, this.isScrolledField.getBoolean(oldChat));
      } catch (Exception var3) {
         throw new RuntimeException("Failed to copy old chat state into distorted chat", var3);
      }
   }
}
