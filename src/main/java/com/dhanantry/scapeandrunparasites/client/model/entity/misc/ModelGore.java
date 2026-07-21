package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGore extends ModelSRP {
   public ModelRenderer sim;
   public ModelRenderer pri;
   public ModelRenderer ada;
   public ModelRenderer pure;
   public ModelRenderer dec;
   public ModelRenderer dec_1;
   public ModelRenderer dec_2;
   public ModelRenderer dec_3;
   public ModelRenderer dec_4;
   public ModelRenderer dec_5;
   public ModelRenderer dec_6;
   public ModelRenderer dec_7;

   public ModelGore() {
      this.field_78090_t = 128;
      this.field_78089_u = 128;
      this.dec = new ModelRenderer(this, 80, 4);
      this.dec.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec.func_78790_a(-0.5F, -8.0F, -8.0F, 1, 16, 16, 0.0F);
      this.setRotateAngle(this.dec, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.dec_1 = new ModelRenderer(this, 48, 20);
      this.dec_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_1.func_78790_a(-0.5F, -8.0F, -8.0F, 1, 16, 16, 0.0F);
      this.setRotateAngle(this.dec_1, (float) (Math.PI / 4), (float) (Math.PI / 2), 0.0F);
      this.dec_4 = new ModelRenderer(this, 38, 52);
      this.dec_4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_4.func_78790_a(-0.5F, -10.0F, -10.0F, 1, 20, 20, 0.0F);
      this.setRotateAngle(this.dec_4, (float) (Math.PI / 4), (float) (Math.PI / 2), 0.0F);
      this.sim = new ModelRenderer(this, 0, 0);
      this.sim.func_78793_a(0.0F, 21.0F, 0.0F);
      this.sim.func_78790_a(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
      this.setRotateAngle(this.sim, 0.0F, (float) (-Math.PI / 4), 0.0F);
      this.dec_6 = new ModelRenderer(this, 58, 72);
      this.dec_6.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_6.func_78790_a(-0.5F, -11.0F, -11.0F, 1, 22, 22, 0.0F);
      this.setRotateAngle(this.dec_6, (float) (Math.PI / 4), (float) (Math.PI / 2), 0.0F);
      this.dec_3 = new ModelRenderer(this, 0, 40);
      this.dec_3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_3.func_78790_a(-0.5F, -9.0F, -9.0F, 1, 18, 18, 0.0F);
      this.setRotateAngle(this.dec_3, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.dec_5 = new ModelRenderer(this, 82, 52);
      this.dec_5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_5.func_78790_a(-0.5F, -10.0F, -10.0F, 1, 20, 20, 0.0F);
      this.setRotateAngle(this.dec_5, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.pure = new ModelRenderer(this, 0, 16);
      this.pure.func_78793_a(0.0F, 18.0F, 0.0F);
      this.pure.func_78790_a(-6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F);
      this.setRotateAngle(this.pure, 0.0F, (float) (-Math.PI / 4), 0.0F);
      this.dec_2 = new ModelRenderer(this, 64, 36);
      this.dec_2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_2.func_78790_a(-0.5F, -9.0F, -9.0F, 1, 18, 18, 0.0F);
      this.setRotateAngle(this.dec_2, (float) (Math.PI / 4), (float) (Math.PI / 2), 0.0F);
      this.pri = new ModelRenderer(this, 24, 0);
      this.pri.func_78793_a(0.0F, 20.0F, 0.0F);
      this.pri.func_78790_a(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
      this.setRotateAngle(this.pri, 0.0F, (float) (-Math.PI / 4), 0.0F);
      this.ada = new ModelRenderer(this, 56, 0);
      this.ada.func_78793_a(0.0F, 19.0F, 0.0F);
      this.ada.func_78790_a(-5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F);
      this.setRotateAngle(this.ada, 0.0F, (float) (-Math.PI / 4), 0.0F);
      this.dec_7 = new ModelRenderer(this, 0, 76);
      this.dec_7.func_78793_a(0.0F, 0.0F, 0.0F);
      this.dec_7.func_78790_a(-0.5F, -11.0F, -11.0F, 1, 22, 22, 0.0F);
      this.setRotateAngle(this.dec_7, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.sim.func_78792_a(this.dec);
      this.sim.func_78792_a(this.dec_1);
      this.ada.func_78792_a(this.dec_4);
      this.pure.func_78792_a(this.dec_6);
      this.pri.func_78792_a(this.dec_3);
      this.ada.func_78792_a(this.dec_5);
      this.pri.func_78792_a(this.dec_2);
      this.pure.func_78792_a(this.dec_7);
   }

   public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
      super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
      this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5, entityIn);
      EntityGore in = (EntityGore)entityIn;
      switch (in.getSkin()) {
         case 1:
            this.sim.func_78785_a(f5);
            break;
         case 2:
            this.pri.func_78785_a(f5);
            break;
         case 3:
            this.ada.func_78785_a(f5);
            break;
         case 4:
            this.pure.func_78785_a(f5);
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         default:
            break;
         case 10:
            this.sim.func_78785_a(f5);
      }
   }

   public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
   }
}
