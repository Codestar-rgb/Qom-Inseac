package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTendrilCanra extends ModelSRP {
   public ModelRenderer mainbody;
   public ModelRenderer taclejointL1;
   public ModelRenderer tentacle;
   public ModelRenderer taclejointL2;
   public ModelRenderer tentacle_1;
   public ModelRenderer taclejointL3;
   public ModelRenderer tentacle_2;
   public ModelRenderer taclejointL4;
   public ModelRenderer tentacle_3;

   public ModelTendrilCanra() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.tentacle_3 = new ModelRenderer(this, 30, 16);
      this.tentacle_3.func_78793_a(0.0F, 0.0F, 1.0F);
      this.tentacle_3.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 12, 0.0F);
      this.setRotateAngle(this.tentacle_3, 0.0F, -0.80285144F, 0.0F);
      this.mainbody = new ModelRenderer(this, 0, 0);
      this.mainbody.func_78793_a(0.0F, 21.7F, 5.0F);
      this.mainbody.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.mainbody, 0.0F, (float) Math.PI, (float) (-Math.PI / 2));
      this.taclejointL1 = new ModelRenderer(this, 4, 0);
      this.taclejointL1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.taclejointL1.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.taclejointL3 = new ModelRenderer(this, 24, 0);
      this.taclejointL3.func_78793_a(0.0F, 0.0F, 9.0F);
      this.taclejointL3.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.tentacle_2 = new ModelRenderer(this, 0, 15);
      this.tentacle_2.func_78793_a(0.0F, 0.0F, 1.0F);
      this.tentacle_2.func_78790_a(-1.5F, -1.5F, -1.0F, 3, 3, 12, 0.0F);
      this.setRotateAngle(this.tentacle_2, 0.0F, (float) (-Math.PI / 5), 0.0F);
      this.tentacle = new ModelRenderer(this, 0, 0);
      this.tentacle.func_78793_a(0.0F, 0.0F, 0.0F);
      this.tentacle.func_78790_a(-2.5F, -2.5F, -1.0F, 5, 5, 10, 0.0F);
      this.tentacle_1 = new ModelRenderer(this, 30, 0);
      this.tentacle_1.func_78793_a(0.0F, 0.0F, 1.0F);
      this.tentacle_1.func_78790_a(-2.0F, -2.0F, -1.0F, 4, 4, 12, 0.0F);
      this.setRotateAngle(this.tentacle_1, 0.0F, -0.54105204F, 0.0F);
      this.taclejointL4 = new ModelRenderer(this, 28, 0);
      this.taclejointL4.func_78793_a(0.0F, 0.0F, 9.0F);
      this.taclejointL4.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.taclejointL2 = new ModelRenderer(this, 20, 0);
      this.taclejointL2.func_78793_a(0.0F, 0.0F, 7.0F);
      this.taclejointL2.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.taclejointL4.func_78792_a(this.tentacle_3);
      this.mainbody.func_78792_a(this.taclejointL1);
      this.tentacle_1.func_78792_a(this.taclejointL3);
      this.taclejointL3.func_78792_a(this.tentacle_2);
      this.taclejointL1.func_78792_a(this.tentacle);
      this.taclejointL2.func_78792_a(this.tentacle_1);
      this.tentacle_2.func_78792_a(this.taclejointL4);
      this.tentacle.func_78792_a(this.taclejointL2);
   }

   public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.mainbody.func_78785_a(scale);
   }
}
