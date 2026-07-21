package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.item.ItemInfestedBonemeal;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class PotionCOTH extends SRPEffectBase {
   public PotionCOTH(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         boolean flagPr = entity instanceof EntityPlayer;
         if (SRPConfigSystems.cothPlayer || !flagPr) {
            NBTTagCompound tags = entity.getEntityData();
            if (tags.func_74764_b("srpcothimmunity") && tags.func_74762_e("srpcothimmunity") == 0) {
               entity.func_184596_c(SRPPotions.COTH_E);
            } else {
               boolean flag = !entity.field_70170_p.field_72995_K;
               boolean particle = false;
               int dur = entity.func_70660_b(SRPPotions.COTH_E).func_76459_b();
               if (SRPConfigSystems.disloCOTHIgnoreAmp
                  && amplifier <= 1
                  && entity.field_70173_aa % 20 == 0
                  && SRPSaveData.get(entity.field_70170_p, 106).getCurrentCode(entity.field_70170_p.field_73011_w.getDimension(), 0) > 0) {
                  entity.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 6666, 10));
                  if (tags.func_74764_b("srpcothimmunity") && tags.func_74762_e("srpcothimmunity") != 0) {
                     ParasiteEventEntity.convertEntity(entity, tags, false, SRPConfigSystems.COTHVictimParasite);
                  }
               }

               if (SRPConfigSystems.COTHPopping && amplifier >= 2) {
                  if (entity instanceof EntityPlayer) {
                     EntityPlayer player = (EntityPlayer)entity;
                     if (!(player.func_110143_aJ() > 0.0F)) {
                        BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u - 1.0, player.field_70161_v);
                        if (player.field_70170_p.func_175623_d(pos)) {
                           return;
                        }

                        ItemInfestedBonemeal.boneMealEffect(player.field_70170_p, pos, EnumFacing.DOWN);
                        player.field_70170_p.func_184133_a(null, pos, SoundEvents.field_187929_hc, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        player.func_184596_c(SRPPotions.COTH_E);
                     }
                  }
               } else {
                  boolean tickFlag = entity.field_70173_aa % 20 == 0;
                  switch (amplifier) {
                     case 0:
                        if (flag) {
                           this.effectCOTHextendDuration(dur, 200, entity, amplifier, flagPr, true, true);
                        }
                        break;
                     case 1:
                        if (flag) {
                           if (tickFlag) {
                              this.InfectNearby(entity, SRPConfigSystems.cothAura);
                              this.effectCOTHextendDuration(dur, 200, entity, amplifier, flagPr, true, true);
                           }

                           particle = this.effectCOTHTransform(entity, dur, tickFlag, flagPr, true);
                        }
                        break;
                     default:
                        if (flag) {
                           if (tickFlag) {
                              this.InfectNearby(entity, SRPConfigSystems.cothAura);
                              this.effectCOTHextendDuration(dur, 200, entity, amplifier, flagPr, true, true);
                           }

                           particle = this.effectCOTHTransform(entity, dur, tickFlag, flagPr, false);
                        }
                  }

                  if (particle) {
                     SRPMain.network
                        .sendToAll(
                           new SRPPacketParticle(
                              entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70130_N, entity.field_70131_O, (byte)1
                           )
                        );
                  }
               }
            }
         }
      }
   }

   private void effectCOTHextendDuration(
      int duration, int durationCheck, EntityLivingBase entity, int amplifier, boolean flagPr, boolean origin, boolean evoPoints
   ) {
      if (duration < durationCheck) {
         int cothDur = 3600;
         amplifier = Math.min(amplifier + 1, 2);
         NBTTagCompound tags = entity.getEntityData();
         if (flagPr) {
            entity.func_70690_d(new PotionEffect(SRPPotions.COTH_E, cothDur, amplifier, false, false));
         } else if (tags.func_74764_b("srpcothimmunity")) {
            if (tags.func_74762_e("srpcothimmunity") != 0) {
               entity.func_70690_d(new PotionEffect(SRPPotions.COTH_E, cothDur, amplifier, false, false));
               if (SRPConfigWorld.originActivated && origin) {
                  ParasiteEventWorld.setOriginInHealth(
                     entity.field_70170_p, entity.func_180425_c(), (int)(entity.func_110138_aP() * SRPConfigWorld.originCOTHMultiplier), true
                  );
               }

               if (SRPConfigSystems.useEvolution) {
                  SRPSaveData data = SRPSaveData.get(entity.field_70170_p, 107);
                  if (evoPoints) {
                     data.setTotalKills(entity.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueCOTH, true, entity.field_70170_p, true, 52);
                  }

                  if (data.getEvolutionPhase(entity.field_70170_p.field_73011_w.getDimension()) >= SRPConfigSystems.evolutionAssimilatedDehiding) {
                     int key = tags.func_74762_e("srpcothimmunity");
                     tags.func_74768_a("srpcothimmunity", ++key);
                  }
               }
            } else {
               entity.func_184596_c(SRPPotions.COTH_E);
            }
         } else {
            entity.func_184596_c(SRPPotions.COTH_E);
         }
      }
   }

   private boolean effectCOTHTransform(EntityLivingBase entity, int duration, boolean tickFlag, boolean flagPr, boolean insider) {
      if (flagPr) {
         return false;
      } else {
         boolean flagReturn = false;
         NBTTagCompound tags = entity.getEntityData();
         if (tags.func_74764_b("srpcothimmunity")) {
            int key = tags.func_74762_e("srpcothimmunity");
            if (entity.func_110138_aP() * SRPConfigSystems.cothUnhide > entity.func_110143_aJ() && entity.func_110143_aJ() > 0.0F || key > 1) {
               flagReturn = true;
               if (insider && tickFlag) {
                  ParasiteEventEntity.spawnInsider(entity, entity.field_70170_p, tags);
               } else if (tickFlag) {
                  ParasiteEventEntity.convertEntity(entity, tags, false, SRPConfigSystems.COTHVictimParasite);
               }
            }
         } else {
            entity.func_184596_c(SRPPotions.COTH_E);
         }

         return flagReturn;
      }
   }
}
