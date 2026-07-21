package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelEffect;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBiomassVenkrol extends ModelEffect {
   public ModelRenderer mainbodysi;
   public ModelRenderer mainbodysii;
   public ModelRenderer mainbodysiii;
   public ModelRenderer core1;
   public ModelRenderer dec;
   public ModelRenderer dec_1;
   public ModelRenderer dec_2;
   public ModelRenderer dec_3;
   public ModelRenderer core2;
   public ModelRenderer core3;
   public ModelRenderer core2_1;
   public ModelRenderer dec_4;
   public ModelRenderer dec_5;
   public ModelRenderer dec_6;
   public ModelRenderer dec_7;
   public ModelRenderer core3_1;
   public ModelRenderer core4;
   public ModelRenderer core2_2;
   public ModelRenderer core5;
   public ModelRenderer dec_8;
   public ModelRenderer dec_9;
   public ModelRenderer dec_10;
   public ModelRenderer dec_11;
   public ModelRenderer core3_2;
   public ModelRenderer core4_1;
   public ModelRenderer core6;

   public ModelBiomassVenkrol() {
      this.field_78090_t = 80;
      this.field_78089_u = 64;
      this.core2_2 = new ModelRenderer(this, 4, 14);
      this.core2_2.func_78793_a(0.0F, -2.5F, 0.0F);
      this.core2_2.func_78790_a(-4.0F, -3.0F, -4.0F, 8, 3, 8, 0.0F);
      this.dec_1 = new ModelRenderer(this, 64, 0);
      this.dec_1.func_78793_a(-2.3F, -1.0F, 0.0F);
      this.dec_1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
      this.setRotateAngle(this.dec_1, 0.0F, 0.0F, -1.1170107F);
      this.core3_1 = new ModelRenderer(this, 71, 11);
      this.core3_1.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core3_1.func_78790_a(-1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F);
      this.dec_7 = new ModelRenderer(this, 0, 12);
      this.dec_7.func_78793_a(-2.5F, -1.2F, 0.0F);
      this.dec_7.func_78790_a(-1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F);
      this.setRotateAngle(this.dec_7, 0.0F, 0.0F, 0.55850536F);
      this.core5 = new ModelRenderer(this, 26, 15);
      this.core5.func_78793_a(0.0F, -0.5F, 0.0F);
      this.core5.func_78790_a(-5.0F, -3.0F, -5.0F, 10, 2, 10, 0.0F);
      this.setRotateAngle(this.core5, (float) -Math.PI, 0.0F, 0.0F);
      this.mainbodysiii = new ModelRenderer(this, 22, 0);
      this.mainbodysiii.func_78793_a(0.0F, 19.5F, 0.0F);
      this.mainbodysiii.func_78790_a(-5.0F, -3.0F, -5.0F, 10, 4, 10, 0.0F);
      this.setRotateAngle(this.mainbodysiii, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.dec_11 = new ModelRenderer(this, 48, 31);
      this.dec_11.func_78793_a(0.0F, -1.0F, -4.6F);
      this.dec_11.func_78790_a(-2.5F, -3.5F, -1.5F, 5, 7, 3, 0.0F);
      this.setRotateAngle(this.dec_11, (float) (Math.PI / 10), 0.0F, 0.0F);
      this.core1 = new ModelRenderer(this, 52, 0);
      this.core1.func_78793_a(0.0F, -1.5F, 0.0F);
      this.core1.func_78790_a(-1.5F, -3.0F, -1.5F, 3, 2, 3, 0.0F);
      this.core3_2 = new ModelRenderer(this, 0, 33);
      this.core3_2.func_78793_a(0.0F, -2.0F, 0.0F);
      this.core3_2.func_78790_a(-3.0F, -3.0F, -3.0F, 6, 2, 6, 0.0F);
      this.core4_1 = new ModelRenderer(this, 56, 16);
      this.core4_1.func_78793_a(0.0F, -2.5F, 0.0F);
      this.core4_1.func_78790_a(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
      this.core4 = new ModelRenderer(this, 28, 0);
      this.core4.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core4.func_78790_a(-0.5F, -3.0F, -0.5F, 1, 2, 1, 0.0F);
      this.mainbodysi = new ModelRenderer(this, 0, 0);
      this.mainbodysi.func_78793_a(0.0F, 24.0F, 0.0F);
      this.mainbodysi.func_78790_a(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
      this.setRotateAngle(this.mainbodysi, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.core3 = new ModelRenderer(this, 0, 0);
      this.core3.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core3.func_78790_a(-0.5F, -3.0F, -0.5F, 1, 2, 1, 0.0F);
      this.dec = new ModelRenderer(this, 12, 0);
      this.dec.func_78793_a(2.3F, -1.0F, 0.0F);
      this.dec.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
      this.setRotateAngle(this.dec, 0.0F, 0.0F, 1.1170107F);
      this.dec_2 = new ModelRenderer(this, 70, 2);
      this.dec_2.func_78793_a(0.0F, -1.0F, 2.3F);
      this.dec_2.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
      this.setRotateAngle(this.dec_2, -1.1170107F, 0.0F, 0.0F);
      this.dec_8 = new ModelRenderer(this, 0, 25);
      this.dec_8.func_78793_a(-4.6F, -1.0F, 0.0F);
      this.dec_8.func_78790_a(-3.5F, -1.5F, -2.5F, 7, 3, 5, 0.0F);
      this.setRotateAngle(this.dec_8, 0.0F, 0.0F, (float) (Math.PI * 2.0 / 5.0));
      this.dec_9 = new ModelRenderer(this, 24, 27);
      this.dec_9.func_78793_a(4.6F, -1.0F, 0.0F);
      this.dec_9.func_78790_a(-3.5F, -1.5F, -2.5F, 7, 3, 5, 0.0F);
      this.setRotateAngle(this.dec_9, 0.0F, 0.0F, (float) (-Math.PI * 2.0 / 5.0));
      this.core2_1 = new ModelRenderer(this, 67, 6);
      this.core2_1.func_78793_a(0.0F, -1.5F, 0.0F);
      this.core2_1.func_78790_a(-1.5F, -3.0F, -1.5F, 3, 2, 3, 0.0F);
      this.dec_10 = new ModelRenderer(this, 63, 24);
      this.dec_10.func_78793_a(0.0F, -1.0F, 4.6F);
      this.dec_10.func_78790_a(-2.5F, -3.5F, -1.5F, 5, 7, 3, 0.0F);
      this.setRotateAngle(this.dec_10, (float) (-Math.PI / 10), 0.0F, 0.0F);
      this.core2 = new ModelRenderer(this, 52, 5);
      this.core2.func_78793_a(0.0F, -1.0F, 0.0F);
      this.core2.func_78790_a(-1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F);
      this.dec_5 = new ModelRenderer(this, 10, 7);
      this.dec_5.func_78793_a(0.0F, -1.2F, 2.5F);
      this.dec_5.func_78790_a(-1.5F, -1.5F, -1.0F, 3, 3, 2, 0.0F);
      this.setRotateAngle(this.dec_5, -1.1170107F, 0.0F, 0.0F);
      this.mainbodysii = new ModelRenderer(this, 16, 0);
      this.mainbodysii.func_78793_a(0.0F, 24.0F, 0.0F);
      this.mainbodysii.func_78790_a(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
      this.setRotateAngle(this.mainbodysii, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.dec_6 = new ModelRenderer(this, 59, 11);
      this.dec_6.func_78793_a(2.5F, -1.2F, 0.0F);
      this.dec_6.func_78790_a(-1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F);
      this.setRotateAngle(this.dec_6, 0.0F, 0.0F, -0.55850536F);
      this.core6 = new ModelRenderer(this, 16, 35);
      this.core6.func_78793_a(0.0F, -2.0F, 0.0F);
      this.core6.func_78790_a(-4.0F, -3.0F, -4.0F, 8, 2, 8, 0.0F);
      this.dec_3 = new ModelRenderer(this, 62, 4);
      this.dec_3.func_78793_a(0.0F, -1.0F, -2.3F);
      this.dec_3.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
      this.setRotateAngle(this.dec_3, 1.1170107F, 0.0F, 0.0F);
      this.dec_4 = new ModelRenderer(this, 0, 7);
      this.dec_4.func_78793_a(0.0F, -1.2F, -2.5F);
      this.dec_4.func_78790_a(-1.5F, -1.5F, -1.0F, 3, 3, 2, 0.0F);
      this.setRotateAngle(this.dec_4, 1.1170107F, 0.0F, 0.0F);
      this.mainbodysiii.func_78792_a(this.core2_2);
      this.mainbodysi.func_78792_a(this.dec_1);
      this.core2_1.func_78792_a(this.core3_1);
      this.mainbodysii.func_78792_a(this.dec_7);
      this.mainbodysiii.func_78792_a(this.core5);
      this.mainbodysiii.func_78792_a(this.dec_11);
      this.mainbodysi.func_78792_a(this.core1);
      this.core2_2.func_78792_a(this.core3_2);
      this.core3_2.func_78792_a(this.core4_1);
      this.core3_1.func_78792_a(this.core4);
      this.core2.func_78792_a(this.core3);
      this.mainbodysi.func_78792_a(this.dec);
      this.mainbodysi.func_78792_a(this.dec_2);
      this.mainbodysiii.func_78792_a(this.dec_8);
      this.mainbodysiii.func_78792_a(this.dec_9);
      this.mainbodysii.func_78792_a(this.core2_1);
      this.mainbodysiii.func_78792_a(this.dec_10);
      this.core1.func_78792_a(this.core2);
      this.mainbodysii.func_78792_a(this.dec_5);
      this.mainbodysii.func_78792_a(this.dec_6);
      this.core5.func_78792_a(this.core6);
      this.mainbodysi.func_78792_a(this.dec_3);
      this.mainbodysii.func_78792_a(this.dec_4);
   }

   public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
      super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
      this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5, entityIn);
      EntityBiomass pod = (EntityBiomass)entityIn;
      switch (pod.getSkin()) {
         case 1:
            this.renderTwo(this.mainbodysi, ageInTicks, f5, pod.getGrowW(), pod.getGrowHeight(), 0.6, 0.8F, 0.05, 0.8);
            break;
         case 2:
            this.renderTwo(this.mainbodysii, ageInTicks, f5, pod.getGrowW(), pod.getGrowHeight(), 0.6, 0.8F, 0.05, 0.8);
            break;
         case 3:
            this.renderTwo(this.mainbodysiii, ageInTicks, f5, pod.getGrowW(), pod.getGrowHeight(), 0.6, 0.8F, 0.05, 0.8);
      }
   }

   public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
   }
}
