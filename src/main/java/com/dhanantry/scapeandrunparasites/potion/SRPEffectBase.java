package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SRPEffectBase extends Potion {
   private ResourceLocation icon;
   private static final boolean SHOW_RAGE_PARTICLES_SELF = true;
   private static final boolean SHOW_BLOOD_PARTICLES_SELF = true;

   public SRPEffectBase(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(isBadEffectIn, liquidColorIn);
      this.setRegistryName("srparasites:" + name);
      this.func_76390_b("mob_effect." + this.getRegistryName());
      this.func_76399_b(IconIndexX, IconIndexY);
      this.icon = new ResourceLocation("srparasites:textures/gui/potion_" + name + ".png");
   }

   public boolean func_76400_d() {
      return false;
   }

   public List<ItemStack> getCurativeItems() {
      return new ArrayList<>();
   }

   public boolean func_76397_a(int duration, int amplifier) {
      if (this != SRPPotions.BLEED_E
         && this != SRPPotions.EPEL_E
         && this != SRPPotions.DLER_E
         && this != SRPPotions.CORRO_E
         && this != SRPPotions.FOSTER_E
         && this != SRPPotions.CONTA_E) {
         return true;
      } else {
         int j = 25 >> amplifier;
         return j > 0 ? duration % j == 0 : true;
      }
   }

   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         if (this == SRPPotions.RAGE_E) {
            for (int i = 0; i <= 3; i++) {
               if (entity.field_70170_p.field_73012_v.nextFloat() < 0.1F) {
                  this.sendParticle(entity, (byte)12, true);
               }
            }

            if (entity.field_70170_p.field_73012_v.nextFloat() < 0.8F) {
               this.sendParticle(entity, (byte)2, true);
            }
         }

         if (this == SRPPotions.BLEED_E) {
            for (int ix = 0; ix <= 3; ix++) {
               if (entity.field_70170_p.field_73012_v.nextFloat() < 0.8F) {
                  this.sendParticle(entity, (byte)13, true);
               }
            }
         }

         if (this == SRPPotions.INDEAF_E) {
            entity.field_70159_w = 0.0;
            entity.field_70179_y = 0.0;
            if (entity instanceof EntityPlayer) {
               EntityPlayer player = (EntityPlayer)entity;
               player.field_191988_bg = 0.0F;
               player.field_70702_br = 0.0F;
            }
         } else if (this == SRPPotions.EFFECTPOS_E) {
            this.effectEffectPos(entity, amplifier);
         } else if (this == SRPPotions.EFFECTNEG_E) {
            this.effectEffectNeg(entity, amplifier);
         }
      }
   }

   private void sendParticle(EntityLivingBase entity, byte type, boolean showForSelf) {
      SRPPacketParticle packet = new SRPPacketParticle(
         entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70130_N, entity.field_70131_O, type
      );
      SRPMain.network.sendToAllTracking(packet, entity);
      if (showForSelf && entity instanceof EntityPlayerMP) {
         SRPMain.network.sendTo(packet, (EntityPlayerMP)entity);
      }
   }

   protected void InfectNearby(EntityLivingBase entity, int range) {
      if (SRPConfigSystems.cothAura > 0) {
         if (!entity.func_70644_a(SRPPotions.EPEL_E)) {
            if (entity instanceof EntityPlayer) {
               EntityPlayer player = (EntityPlayer)entity;
               if (player.field_71075_bZ.field_75098_d) {
                  return;
               }
            }

            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  entity.field_70165_t,
                  entity.field_70163_u,
                  entity.field_70161_v,
                  entity.field_70165_t + 1.0,
                  entity.field_70163_u + 1.0,
                  entity.field_70161_v + 1.0
               )
               .func_186662_g(range);

            for (EntityLivingBase mob : entity.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
               if (entity.func_70685_l(mob) && mob != entity && !mob.func_70644_a(SRPPotions.COTH_E) && !mob.func_70644_a(SRPPotions.EPEL_E)) {
                  mob.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 4800, 0, false, false));
               }
            }
         }
      }
   }

   protected BlockPos getRandomPosInCircle(Random rand, BlockPos center, double radius) {
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      double r = Math.sqrt(rand.nextDouble()) * radius;
      int dx = (int)Math.floor(Math.cos(theta) * r);
      int dz = (int)Math.floor(Math.sin(theta) * r);
      return center.func_177982_a(dx, 0, dz);
   }

   protected void effectEffectPos(EntityLivingBase entity, int amplifier) {
      if (entity.field_70173_aa % 20 == 0) {
         for (PotionEffect pe : entity.func_70651_bq()) {
            Potion p = pe.func_188419_a();
            if (!p.func_76398_f()) {
               entity.func_70097_a(DamageSource.field_82727_n, 0.5F * (pe.func_76458_c() + 1));
            }
         }
      }
   }

   protected void effectEffectNeg(EntityLivingBase entity, int amplifier) {
      if (entity.field_70173_aa % 20 == 0) {
         for (PotionEffect pe : entity.func_70651_bq()) {
            Potion p = pe.func_188419_a();
            if (p.func_76398_f()) {
               SRPPotions.applyStackPotion(p, entity, 20, amplifier);
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
      if (mc.field_71462_r != null) {
         mc.func_110434_K().func_110577_a(this.icon);
         Gui.func_146110_a(x + 6, y + 7, 0.0F, 0.0F, 18, 18, 18.0F, 18.0F);
      }
   }

   public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
      mc.func_110434_K().func_110577_a(this.icon);
      Gui.func_146110_a(x + 3, y + 3, 0.0F, 0.0F, 18, 18, 18.0F, 18.0F);
   }
}
