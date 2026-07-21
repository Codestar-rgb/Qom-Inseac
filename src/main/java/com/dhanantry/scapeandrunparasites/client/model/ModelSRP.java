package com.dhanantry.scapeandrunparasites.client.model;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public abstract class ModelSRP extends ModelBase {
   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.field_78795_f = x;
      modelRenderer.field_78796_g = y;
      modelRenderer.field_78808_h = z;
   }

   public void underground(EntityParasiteBase parasite, float ageInTicks, ModelRenderer mainbody) {
   }

   public void swingX(ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78795_f = (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed) * limbSwingAmount);
   }

   public void swingX(ModelRenderer modelRenderer, float speed, float degree, int invert, float offset, float weight, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78795_f = (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed + offset) + weight * limbSwingAmount);
   }

   public void swingX(float pref, ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78795_f = pref + (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed) * limbSwingAmount);
   }

   public void swingY(ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78796_g = (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed) * limbSwingAmount);
   }

   public void swingY(ModelRenderer modelRenderer, float speed, float degree, int invert, float offset, float weight, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78796_g = (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed + offset) + weight * limbSwingAmount);
   }

   public void swingY(float pref, ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78796_g = pref + (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed) * limbSwingAmount);
   }

   public void swingZ(ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78808_h = (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed) * limbSwingAmount);
   }

   public void swingZ(ModelRenderer modelRenderer, float speed, float degree, int invert, float offset, float weight, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78808_h = (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed + offset) + weight * limbSwingAmount);
   }

   public void swingZ(float pref, ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
      modelRenderer.field_78808_h = pref + (float)(invert * limbSwingAmount * degree * Math.cos(limbSwing * speed) * limbSwingAmount);
   }

   public void moveY(ModelRenderer modelRenderer, float speed, int invert, float f, float f1, float distance) {
      modelRenderer.field_82908_p = invert * MathHelper.func_76134_b(f * speed) * f1 * distance;
   }

   public void setRotationAnglesCosmical(
      float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn
   ) {
   }

   public void renderC(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
   }

   public void setLivingAnimations(Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
   }
}
