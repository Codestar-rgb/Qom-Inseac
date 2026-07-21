package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderDistortedSign extends TileEntitySpecialRenderer<TileEntitySign> {
   private static final ResourceLocation SIGN_TEXTURE = new ResourceLocation("textures/entity/sign.png");
   private final ModelSign model = new ModelSign();

   public void render(TileEntitySign te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      Block block = te.func_145838_q();
      GlStateManager.func_179094_E();
      if (block == Blocks.field_150472_an) {
         GlStateManager.func_179109_b((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
         float rot = te.func_145832_p() * 360 / 16.0F;
         GlStateManager.func_179114_b(-rot, 0.0F, 1.0F, 0.0F);
         this.model.field_78165_b.field_78806_j = true;
      } else {
         int meta = te.func_145832_p();
         float rot = 0.0F;
         if (meta == 2) {
            rot = 180.0F;
         }

         if (meta == 4) {
            rot = 90.0F;
         }

         if (meta == 5) {
            rot = -90.0F;
         }

         GlStateManager.func_179109_b((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
         GlStateManager.func_179114_b(-rot, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179109_b(0.0F, -0.3125F, -0.4375F);
         this.model.field_78165_b.field_78806_j = false;
      }

      if (destroyStage >= 0) {
         this.func_147499_a(field_178460_a[destroyStage]);
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(4.0F, 2.0F, 1.0F);
         GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.func_179128_n(5888);
      } else {
         this.func_147499_a(SIGN_TEXTURE);
      }

      GlStateManager.func_179091_B();
      GlStateManager.func_179094_E();
      GlStateManager.func_179152_a(0.6666667F, -0.6666667F, -0.6666667F);
      this.model.func_78164_a();
      GlStateManager.func_179121_F();
      FontRenderer font = this.func_147498_b();
      GlStateManager.func_179109_b(0.0F, 0.33333334F, 0.046666667F);
      GlStateManager.func_179152_a(0.010416667F, -0.010416667F, 0.010416667F);
      GlStateManager.func_187432_a(0.0F, 0.0F, -0.010416667F);
      GlStateManager.func_179132_a(false);
      if (destroyStage < 0) {
         boolean distortSigns = GuiDistortionHelper.shouldDistortSigns(Minecraft.func_71410_x());

         for (int j = 0; j < te.field_145915_a.length; j++) {
            if (te.field_145915_a[j] != null) {
               ITextComponent itextcomponent = te.field_145915_a[j];
               List<ITextComponent> list = GuiUtilRenderComponents.func_178908_a(itextcomponent, 90, font, false, true);
               String s = list != null && !list.isEmpty() ? list.get(0).func_150254_d() : "";
               if (j == te.field_145918_i) {
                  s = "> " + s + " <";
               }

               String shown = distortSigns ? GuiDistortionHelper.jamText(s) : s;
               font.func_78276_b(shown, -font.func_78256_a(shown) / 2, j * 10 - te.field_145915_a.length * 5, 0);
            }
         }
      }

      GlStateManager.func_179132_a(true);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179121_F();
      if (destroyStage >= 0) {
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5888);
      }
   }
}
