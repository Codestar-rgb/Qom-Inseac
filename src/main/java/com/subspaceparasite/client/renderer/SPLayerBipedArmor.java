/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerArmorBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.client.model.entity.SPModelBiped;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class SPLayerBipedArmor
extends LayerArmorBase<ModelBiped> {
    private final RenderLivingBase<?> renderer;
    private float alpha = 1.0f;
    private float colorR = 1.0f;
    private float colorG = 1.0f;
    private float colorB = 1.0f;
    private boolean skipRenderGlint;
    private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();

    public SPLayerBipedArmor(RenderLivingBase<?> rendererIn) {
        super(rendererIn);
        this.renderer = rendererIn;
    }

    protected void func_177177_a() {
        this.field_177189_c = new SPModelBiped(0.5f);
        this.field_177186_d = new SPModelBiped(1.0f);
    }

    public void func_177141_a(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        byte type = 0;
        if (entitylivingbaseIn instanceof EntityInfPlayer) {
            type = 1;
        }
        this.renderArmorLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.LEGS, type);
        this.renderArmorLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.FEET, type);
        this.renderArmorLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.HEAD, type);
    }

    private void renderArmorLayer(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, byte in) {
        ItemArmor itemarmor;
        ItemStack itemstack = entityLivingBaseIn.func_184582_a(slotIn);
        if (itemstack.func_77973_b() instanceof ItemArmor && (itemarmor = (ItemArmor)itemstack.func_77973_b()).func_185083_B_() == slotIn) {
            if (!(this.func_188360_a(slotIn) instanceof ModelBiped)) {
                return;
            }
            SPModelBiped t = (SPModelBiped)this.func_188360_a(slotIn);
            if (this.getArmorModelHook(entityLivingBaseIn, itemstack, slotIn, t) instanceof ModelBiped) {
                t = (SPModelBiped)this.getArmorModelHook(entityLivingBaseIn, itemstack, slotIn, t);
            }
            t.setParent(in);
            t.func_178686_a(this.renderer.func_177087_b());
            t.func_78086_a(entityLivingBaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.setModelSlotVisible2(t, slotIn);
            this.renderer.func_110776_a(this.getArmorResource((Entity)entityLivingBaseIn, itemstack, slotIn, null));
            if (itemarmor.hasOverlay(itemstack)) {
                int i = itemarmor.func_82814_b(itemstack);
                float f = (float)(i >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                GlStateManager.func_179131_c((float)(this.colorR * f), (float)(this.colorG * f1), (float)(this.colorB * f2), (float)this.alpha);
                t.func_78088_a((Entity)entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                this.renderer.func_110776_a(this.getArmorResource((Entity)entityLivingBaseIn, itemstack, slotIn, "overlay"));
            }
            GlStateManager.func_179131_c((float)this.colorR, (float)this.colorG, (float)this.colorB, (float)this.alpha);
            t.func_78088_a((Entity)entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            if (!this.skipRenderGlint && itemstack.func_77962_s()) {
                SPLayerBipedArmor.func_188364_a(this.renderer, (EntityLivingBase)entityLivingBaseIn, (ModelBase)t, (float)limbSwing, (float)limbSwingAmount, (float)partialTicks, (float)ageInTicks, (float)netHeadYaw, (float)headPitch, (float)scale);
            }
        }
    }

    protected void setModelSlotVisible2(SPModelBiped model, EntityEquipmentSlot slotIn) {
        this.setModelVisible(model);
        switch (slotIn) {
            case HEAD: {
                model.jointH.field_78806_j = true;
                model.jointHW.field_78806_j = true;
                break;
            }
            case CHEST: {
                model.body.field_78806_j = true;
                model.rightA.field_78806_j = true;
                model.leftA.field_78806_j = true;
                break;
            }
            case LEGS: {
                model.body.field_78806_j = true;
                model.rightL.field_78806_j = true;
                model.leftL.field_78806_j = true;
                break;
            }
            case FEET: {
                model.rightL.field_78806_j = true;
                model.leftL.field_78806_j = true;
            }
        }
    }

    protected void setModelVisible(ModelBiped model) {
        model.func_178719_a(false);
    }

    protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model) {
        return ForgeHooksClient.getArmorModel((EntityLivingBase)entity, (ItemStack)itemStack, (EntityEquipmentSlot)slot, (ModelBiped)model);
    }

    protected void setModelSlotVisible(ModelBiped model, EntityEquipmentSlot slotIn) {
        if (model instanceof SPModelBiped) {
            this.setModelSlotVisible2((SPModelBiped)model, slotIn);
        }
    }
}

