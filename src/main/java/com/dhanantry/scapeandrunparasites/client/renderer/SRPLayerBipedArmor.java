package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.client.model.entity.SRPModelBiped;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPlayer;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SRPLayerBipedArmor extends LayerArmorBase<ModelBiped> {
   private final RenderLivingBase<?> renderer;
   private float alpha = 1.0F;
   private float colorR = 1.0F;
   private float colorG = 1.0F;
   private float colorB = 1.0F;
   private boolean skipRenderGlint;
   private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();

   public SRPLayerBipedArmor(RenderLivingBase<?> rendererIn) {
      super(rendererIn);
      this.renderer = rendererIn;
   }

   protected void func_177177_a() {
      this.field_177189_c = new SRPModelBiped(0.5F);
      this.field_177186_d = new SRPModelBiped(1.0F);
   }

   public void func_177141_a(
      EntityLivingBase entitylivingbaseIn,
      float limbSwing,
      float limbSwingAmount,
      float partialTicks,
      float ageInTicks,
      float netHeadYaw,
      float headPitch,
      float scale
   ) {
      byte type = 0;
      if (entitylivingbaseIn instanceof EntityInfPlayer) {
         type = 1;
      }

      this.renderArmorLayer(
         entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.LEGS, type
      );
      this.renderArmorLayer(
         entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.FEET, type
      );
      this.renderArmorLayer(
         entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.HEAD, type
      );
   }

   private void renderArmorLayer(
      EntityLivingBase entityLivingBaseIn,
      float limbSwing,
      float limbSwingAmount,
      float partialTicks,
      float ageInTicks,
      float netHeadYaw,
      float headPitch,
      float scale,
      EntityEquipmentSlot slotIn,
      byte in
   ) {
      ItemStack itemstack = entityLivingBaseIn.func_184582_a(slotIn);
      if (itemstack.func_77973_b() instanceof ItemArmor) {
         ItemArmor itemarmor = (ItemArmor)itemstack.func_77973_b();
         if (itemarmor.func_185083_B_() == slotIn) {
            if (!(this.func_188360_a(slotIn) instanceof ModelBiped)) {
               return;
            }

            SRPModelBiped t = (SRPModelBiped)this.func_188360_a(slotIn);
            if (this.getArmorModelHook(entityLivingBaseIn, itemstack, slotIn, t) instanceof ModelBiped) {
               t = (SRPModelBiped)this.getArmorModelHook(entityLivingBaseIn, itemstack, slotIn, t);
            }

            t.setParent(in);
            t.func_178686_a(this.renderer.func_177087_b());
            t.func_78086_a(entityLivingBaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.setModelSlotVisible2(t, slotIn);
            this.renderer.func_110776_a(this.getArmorResource(entityLivingBaseIn, itemstack, slotIn, null));
            if (itemarmor.hasOverlay(itemstack)) {
               int i = itemarmor.func_82814_b(itemstack);
               float f = (i >> 16 & 0xFF) / 255.0F;
               float f1 = (i >> 8 & 0xFF) / 255.0F;
               float f2 = (i & 0xFF) / 255.0F;
               GlStateManager.func_179131_c(this.colorR * f, this.colorG * f1, this.colorB * f2, this.alpha);
               t.func_78088_a(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
               this.renderer.func_110776_a(this.getArmorResource(entityLivingBaseIn, itemstack, slotIn, "overlay"));
            }

            GlStateManager.func_179131_c(this.colorR, this.colorG, this.colorB, this.alpha);
            t.func_78088_a(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            if (!this.skipRenderGlint && itemstack.func_77962_s()) {
               func_188364_a(this.renderer, entityLivingBaseIn, t, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }
         }
      }
   }

   protected void setModelSlotVisible2(SRPModelBiped model, EntityEquipmentSlot slotIn) {
      this.setModelVisible(model);
      switch (slotIn) {
         case HEAD:
            model.jointH.field_78806_j = true;
            model.jointHW.field_78806_j = true;
            break;
         case CHEST:
            model.body.field_78806_j = true;
            model.rightA.field_78806_j = true;
            model.leftA.field_78806_j = true;
            break;
         case LEGS:
            model.body.field_78806_j = true;
            model.rightL.field_78806_j = true;
            model.leftL.field_78806_j = true;
            break;
         case FEET:
            model.rightL.field_78806_j = true;
            model.leftL.field_78806_j = true;
      }
   }

   protected void setModelVisible(ModelBiped model) {
      model.func_178719_a(false);
   }

   protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model) {
      return ForgeHooksClient.getArmorModel(entity, itemStack, slot, model);
   }

   protected void setModelSlotVisible(ModelBiped model, EntityEquipmentSlot slotIn) {
      if (model instanceof SRPModelBiped) {
         this.setModelSlotVisible2((SRPModelBiped)model, slotIn);
      }
   }
}
