package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelEffect;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBiomassPod extends ModelEffect {
   public ModelRenderer alafha;
   public ModelRenderer pri_sum;
   public ModelRenderer ada_sum;
   public ModelRenderer core2;
   public ModelRenderer dec;
   public ModelRenderer dec_1;
   public ModelRenderer dec_2;
   public ModelRenderer dec_3;
   public ModelRenderer core3;
   public ModelRenderer core1;
   public ModelRenderer dec_4;
   public ModelRenderer dec_5;
   public ModelRenderer dec_6;
   public ModelRenderer dec_7;
   public ModelRenderer core2_1;
   public ModelRenderer core3_1;
   public ModelRenderer core2_2;
   public ModelRenderer dec_8;
   public ModelRenderer dec_9;
   public ModelRenderer dec_10;
   public ModelRenderer dec_11;
   public ModelRenderer core3_2;
   public ModelRenderer core4;

   public ModelBiomassPod() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.dec_8 = new ModelRenderer(this, 32, 15);
      this.dec_8.func_78793_a(0.0F, -1.2F, -2.5F);
      this.dec_8.func_78790_a(-1.5F, -2.0F, -1.0F, 3, 4, 2, 0.0F);
      this.setRotateAngle(this.dec_8, (float) (Math.PI * 2.0 / 9.0), 0.0F, 0.0F);
      this.dec = new ModelRenderer(this, 46, 5);
      this.dec.func_78793_a(0.0F, -1.2F, -2.5F);
      this.dec.func_78790_a(-1.5F, -1.5F, -1.0F, 3, 3, 2, 0.0F);
      this.setRotateAngle(this.dec, (float) (-Math.PI / 10), 0.0F, 0.0F);
      this.dec_11 = new ModelRenderer(this, 39, 19);
      this.dec_11.func_78793_a(-2.5F, -1.2F, 0.0F);
      this.dec_11.func_78790_a(-2.0F, -1.0F, -1.5F, 4, 2, 3, 0.0F);
      this.setRotateAngle(this.dec_11, 0.0F, 0.0F, 1.012291F);
      this.dec_2 = new ModelRenderer(this, 10, 7);
      this.dec_2.func_78793_a(2.5F, -1.2F, 0.0F);
      this.dec_2.func_78790_a(-1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F);
      this.setRotateAngle(this.dec_2, 0.0F, 0.0F, (float) (Math.PI * 4.0 / 9.0));
      this.dec_6 = new ModelRenderer(this, 8, 12);
      this.dec_6.func_78793_a(0.0F, -1.0F, 2.3F);
      this.dec_6.func_78790_a(-1.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F);
      this.setRotateAngle(this.dec_6, -2.146755F, 0.0F, 0.0F);
      this.dec_1 = new ModelRenderer(this, 0, 7);
      this.dec_1.func_78793_a(0.0F, -1.2F, 2.5F);
      this.dec_1.func_78790_a(-1.5F, -1.5F, -1.0F, 3, 3, 2, 0.0F);
      this.setRotateAngle(this.dec_1, (float) (Math.PI / 10), 0.0F, 0.0F);
      this.core3_1 = new ModelRenderer(this, 0, 0);
      this.core3_1.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core3_1.func_78790_a(-0.5F, -3.0F, -0.5F, 1, 2, 1, 0.0F);
      this.dec_10 = new ModelRenderer(this, 10, 18);
      this.dec_10.func_78793_a(2.5F, -1.2F, 0.0F);
      this.dec_10.func_78790_a(-2.0F, -1.0F, -1.5F, 4, 2, 3, 0.0F);
      this.setRotateAngle(this.dec_10, 0.0F, 0.0F, -1.012291F);
      this.dec_7 = new ModelRenderer(this, 20, 12);
      this.dec_7.func_78793_a(0.0F, -0.9F, -2.3F);
      this.dec_7.func_78790_a(-1.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F);
      this.setRotateAngle(this.dec_7, 2.146755F, 0.0F, 0.0F);
      this.alafha = new ModelRenderer(this, 0, 0);
      this.alafha.func_78793_a(0.0F, 24.0F, 0.0F);
      this.alafha.func_78790_a(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
      this.setRotateAngle(this.alafha, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.core3_2 = new ModelRenderer(this, 28, 0);
      this.core3_2.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core3_2.func_78790_a(-1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F);
      this.dec_4 = new ModelRenderer(this, 51, 10);
      this.dec_4.func_78793_a(2.3F, -1.0F, 0.0F);
      this.dec_4.func_78790_a(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
      this.setRotateAngle(this.dec_4, 0.0F, 0.0F, 2.146755F);
      this.core1 = new ModelRenderer(this, 39, 10);
      this.core1.func_78793_a(0.0F, -1.5F, 0.0F);
      this.core1.func_78790_a(-1.5F, -3.0F, -1.5F, 3, 2, 3, 0.0F);
      this.core2_2 = new ModelRenderer(this, 48, 14);
      this.core2_2.func_78793_a(0.0F, -1.5F, 0.0F);
      this.core2_2.func_78790_a(-1.5F, -3.0F, -1.5F, 3, 2, 3, 0.0F);
      this.dec_3 = new ModelRenderer(this, 22, 7);
      this.dec_3.func_78793_a(-2.5F, -1.2F, 0.0F);
      this.dec_3.func_78790_a(-1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F);
      this.setRotateAngle(this.dec_3, 0.0F, 0.0F, (float) (-Math.PI * 4.0 / 9.0));
      this.core2 = new ModelRenderer(this, 48, 0);
      this.core2.func_78793_a(0.0F, -1.5F, 0.0F);
      this.core2.func_78790_a(-1.5F, -3.0F, -1.5F, 3, 2, 3, 0.0F);
      this.dec_5 = new ModelRenderer(this, 0, 12);
      this.dec_5.func_78793_a(-2.3F, -1.0F, 0.0F);
      this.dec_5.func_78790_a(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
      this.setRotateAngle(this.dec_5, 0.0F, 0.0F, -2.146755F);
      this.ada_sum = new ModelRenderer(this, 32, 0);
      this.ada_sum.func_78793_a(0.0F, 24.0F, 0.0F);
      this.ada_sum.func_78790_a(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
      this.setRotateAngle(this.ada_sum, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.core2_1 = new ModelRenderer(this, 12, 0);
      this.core2_1.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core2_1.func_78790_a(-1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F);
      this.dec_9 = new ModelRenderer(this, 0, 16);
      this.dec_9.func_78793_a(0.0F, -1.2F, 2.5F);
      this.dec_9.func_78790_a(-1.5F, -2.0F, -1.0F, 3, 4, 2, 0.0F);
      this.setRotateAngle(this.dec_9, (float) (-Math.PI * 2.0 / 9.0), 0.0F, 0.0F);
      this.pri_sum = new ModelRenderer(this, 16, 0);
      this.pri_sum.func_78793_a(0.0F, 24.0F, 0.0F);
      this.pri_sum.func_78790_a(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
      this.setRotateAngle(this.pri_sum, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.core4 = new ModelRenderer(this, 44, 0);
      this.core4.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core4.func_78790_a(-0.5F, -3.0F, -0.5F, 1, 2, 1, 0.0F);
      this.core3 = new ModelRenderer(this, 34, 7);
      this.core3.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core3.func_78790_a(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
      this.ada_sum.func_78792_a(this.dec_8);
      this.alafha.func_78792_a(this.dec);
      this.ada_sum.func_78792_a(this.dec_11);
      this.alafha.func_78792_a(this.dec_2);
      this.pri_sum.func_78792_a(this.dec_6);
      this.alafha.func_78792_a(this.dec_1);
      this.core2_1.func_78792_a(this.core3_1);
      this.ada_sum.func_78792_a(this.dec_10);
      this.pri_sum.func_78792_a(this.dec_7);
      this.core2_2.func_78792_a(this.core3_2);
      this.pri_sum.func_78792_a(this.dec_4);
      this.pri_sum.func_78792_a(this.core1);
      this.ada_sum.func_78792_a(this.core2_2);
      this.alafha.func_78792_a(this.dec_3);
      this.alafha.func_78792_a(this.core2);
      this.pri_sum.func_78792_a(this.dec_5);
      this.core1.func_78792_a(this.core2_1);
      this.ada_sum.func_78792_a(this.dec_9);
      this.core3_2.func_78792_a(this.core4);
      this.core2.func_78792_a(this.core3);
   }

   public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
      super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
      this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5, entityIn);
      EntityBiomass pod = (EntityBiomass)entityIn;
      switch (pod.getSkin()) {
         case 4:
            this.renderTwo(this.alafha, ageInTicks, f5, pod.getGrowW(), pod.getGrowHeight(), 0.6, 0.8F, 0.05, 0.8);
            break;
         case 5:
            this.renderTwo(this.pri_sum, ageInTicks, f5, pod.getGrowW(), pod.getGrowHeight(), 0.6, 0.8F, 0.05, 0.8);
            break;
         case 6:
            this.renderTwo(this.ada_sum, ageInTicks, f5, pod.getGrowW(), pod.getGrowHeight(), 0.6, 0.8F, 0.05, 0.8);
      }
   }

   public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
   }
}
