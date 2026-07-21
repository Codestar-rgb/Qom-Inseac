package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelOrbVoid extends ModelSRP {
   public ModelRenderer mainbody;
   public ModelRenderer kd;
   public ModelRenderer kd_1;
   public ModelRenderer mainbody_1;
   public ModelRenderer mainbody_2;

   public ModelOrbVoid() {
      this.field_78090_t = 128;
      this.field_78089_u = 128;
      this.kd = new ModelRenderer(this, 0, 0);
      this.kd.func_78793_a(0.0F, 28.0F, 0.0F);
      this.kd.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.kd, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.mainbody_1 = new ModelRenderer(this, 48, 16);
      this.mainbody_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.mainbody_1.func_78790_a(-8.0F, -12.0F, -8.0F, 16, 16, 16, 0.0F);
      this.setRotateAngle(this.mainbody_1, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.mainbody = new ModelRenderer(this, 0, 0);
      this.mainbody.func_78793_a(0.0F, 28.0F, 0.0F);
      this.mainbody.func_78790_a(-8.0F, -12.0F, -8.0F, 16, 16, 16, 0.0F);
      this.mainbody_2 = new ModelRenderer(this, 0, 32);
      this.mainbody_2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.mainbody_2.func_78790_a(-8.0F, -12.0F, -8.0F, 16, 16, 16, 0.0F);
      this.setRotateAngle(this.mainbody_2, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.kd_1 = new ModelRenderer(this, 4, 0);
      this.kd_1.func_78793_a(0.0F, 28.0F, 0.0F);
      this.kd_1.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.kd_1, (float) (-Math.PI / 4), 0.0F, 0.0F);
      this.kd.func_78792_a(this.mainbody_1);
      this.kd_1.func_78792_a(this.mainbody_2);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      double scale = 0.5;
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(this.mainbody.field_82906_o, this.mainbody.field_82908_p, this.mainbody.field_82907_q);
      GlStateManager.func_179109_b(this.mainbody.field_78800_c * f5, this.mainbody.field_78797_d * f5, this.mainbody.field_78798_e * f5);
      GlStateManager.func_179139_a(scale, scale, scale);
      GlStateManager.func_179109_b(-this.mainbody.field_82906_o, -this.mainbody.field_82908_p, -this.mainbody.field_82907_q);
      GlStateManager.func_179109_b(-this.mainbody.field_78800_c * f5, -this.mainbody.field_78797_d * f5, -this.mainbody.field_78798_e * f5);
      this.kd.func_78785_a(f5);
      this.mainbody.func_78785_a(f5);
      this.kd_1.func_78785_a(f5);
      GlStateManager.func_179121_F();
   }

   public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
      float f1 = -0.35F;
      this.mainbody.field_82908_p = f1;
      this.kd.field_82908_p = f1;
      this.kd_1.field_82908_p = f1;
   }
}
