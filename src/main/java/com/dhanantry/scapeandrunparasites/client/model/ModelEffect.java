package com.dhanantry.scapeandrunparasites.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public abstract class ModelEffect extends ModelSRP {
   public void renderTwo(ModelRenderer in, float ageInTicks, float f5, float width, float height, double n1, float n2, double n3, double n4) {
      double f1 = n1 + MathHelper.func_76126_a(ageInTicks * n2) * n3 + n4;
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(in.field_82906_o, in.field_82908_p, in.field_82907_q);
      GlStateManager.func_179109_b(in.field_78800_c * f5, in.field_78797_d * f5, in.field_78798_e * f5);
      GlStateManager.func_179139_a(f1 + width, f1 + height, f1 + width);
      GlStateManager.func_179109_b(-in.field_82906_o, -in.field_82908_p, -in.field_82907_q);
      GlStateManager.func_179109_b(-in.field_78800_c * f5, -in.field_78797_d * f5, -in.field_78798_e * f5);
      in.func_78785_a(f5);
      GlStateManager.func_179121_F();
   }
}
