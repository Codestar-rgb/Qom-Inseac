package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import org.apache.logging.log4j.Level;

public class PotionNeedler extends SRPEffectBase {
   public PotionNeedler(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         this.effectNeedler(entity, amplifier);
      }
   }

   private void effectNeedler(EntityLivingBase entity, int amplifier) {
      if (amplifier >= SRPConfigSystems.needlerTerminal) {
         amplifier -= SRPConfigSystems.needlerTerminal;
         entity.func_184596_c(SRPPotions.DLER_E);

         String mobname;
         try {
            mobname = EntityList.func_191301_a(entity).toString();
         } catch (Exception var12) {
            SRPMain.logger.log(Level.ERROR, "Problem with needler and an entity", var12);
            return;
         }

         if (ParasiteEventEntity.checkName(mobname, SRPConfigSystems.needlerImmuneList, SRPConfigSystems.needlerImmuneListWhite)) {
            return;
         }

         entity.func_70690_d(new PotionEffect(SRPPotions.DLER_E, 400, amplifier, false, false));
         float f1 = entity.func_110143_aJ();
         if (f1 <= 0.0F) {
            return;
         }

         float damagee = entity.func_110138_aP() * SRPConfigSystems.needlerDamage;
         damagee = entity instanceof EntityPlayer
            ? Math.min(damagee, SRPConfigSystems.needlerMaxDamPlayer)
            : Math.min(damagee, SRPConfigSystems.needlerMaxDamMonster);
         entity.func_70606_j(f1 - damagee);
         entity.field_70170_p.func_72960_a(entity, (byte)2);
         entity.field_70170_p.func_72876_a(entity, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 0.0F, false);
         if (entity.func_110143_aJ() <= 0.0F) {
            ItemStack itemstack = null;

            for (EnumHand enumhand : EnumHand.values()) {
               ItemStack itemstack1 = entity.func_184586_b(enumhand);
               if (itemstack1.func_77973_b() == Items.field_190929_cY) {
                  itemstack = itemstack1.func_77946_l();
                  itemstack1.func_190918_g(1);
                  break;
               }
            }

            if (itemstack != null) {
               if (entity instanceof EntityPlayerMP) {
                  EntityPlayerMP entityplayermp = (EntityPlayerMP)entity;
                  entityplayermp.func_71029_a(StatList.func_188057_b(Items.field_190929_cY));
                  CriteriaTriggers.field_193130_A.func_193187_a(entityplayermp, itemstack);
               }

               entity.func_70606_j(1.0F);
               entity.func_70674_bp();
               entity.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 900, 1));
               entity.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 100, 1));
               entity.field_70170_p.func_72960_a(entity, (byte)35);
            } else {
               entity.func_70645_a(DamageSource.field_76376_m);
            }
         }
      }
   }
}
