package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public class PearlEvents {
   private static final ResourceLocation SIM = new ResourceLocation("srparasites", "sim_enderman");
   private static final ResourceLocation FERAL = new ResourceLocation("srparasites", "fer_enderman");
   private static final ResourceLocation ASSIMARA = new ResourceLocation("srparasites", "mar_enderman");
   private static final ResourceLocation SIM_HEAD = new ResourceLocation("srparasites", "sim_endermanhead");

   @SubscribeEvent
   public static void onLivingDrops(LivingDropsEvent e) {
      if (!e.getEntity().field_70170_p.field_72995_K) {
         ResourceLocation id = EntityList.func_191301_a(e.getEntity());
         if (id != null) {
            float chance = 0.0F;
            if (id.equals(SIM)) {
               chance = 0.1F;
            } else if (id.equals(FERAL)) {
               chance = 0.3F;
            } else if (id.equals(ASSIMARA)) {
               chance = 0.4F;
            }

            if (!(chance <= 0.0F)) {
               if (e.getEntity().field_70170_p.field_73012_v.nextFloat() < chance) {
                  ItemStack drop = new ItemStack(SRPItems.pearl);
                  e.getDrops()
                     .add(
                        new EntityItem(e.getEntity().field_70170_p, e.getEntity().field_70165_t, e.getEntity().field_70163_u, e.getEntity().field_70161_v, drop)
                     );
               }
            }
         }
      }
   }

   @SubscribeEvent
   public static void onPlayerDrops(PlayerDropsEvent e) {
      EntityPlayer player = e.getEntityPlayer();
      GameRules rules = player.field_70170_p.func_82736_K();
      if (SRPConfigMobs.pearlDestroyedOnBeholderKill) {
         if (!rules.func_82766_b("keepInventory")) {
            DamageSource src = e.getSource();
            if (src != null) {
               Entity killer = src.func_76346_g();
               if (killer instanceof EntityLivingBase) {
                  ResourceLocation killerId = EntityList.func_191301_a(killer);
                  if (killerId != null) {
                     boolean isBeholder = killerId.equals(SIM) || killerId.equals(SIM_HEAD) || killerId.equals(FERAL) || killerId.equals(ASSIMARA);
                     if (isBeholder) {
                        Iterator<EntityItem> it = e.getDrops().iterator();

                        while (it.hasNext()) {
                           EntityItem ent = it.next();
                           ItemStack s = ent.func_92059_d();
                           if (s != null && s.func_77973_b() == SRPItems.pearl) {
                              it.remove();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
