package com.dhanantry.scapeandrunparasites.feature;

import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.network.msg.S2CSetEscapeOffer;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EscapeOnDeathHandler {
   private static final String PERSIST_TAG = "PlayerPersisted";
   private static final String OFFER_TAG = "srp_offer_escape";
   private static final String WINDOW_TAG = "srp_death_window";
   private static final long WINDOW_MS = 60000L;
   private static final int THRESHOLD = 5;
   private static final Map<UUID, Deque<Long>> streaks = new HashMap<>();

   private static boolean isSrparasitesCause(LivingDeathEvent e) {
      if (e.getSource() == null) {
         return false;
      } else if (e.getSource().func_76346_g() != null && e.getSource().func_76346_g().getClass().getName().startsWith("com.dhanantry.scapeandrunparasites")) {
         return true;
      } else if (e.getSource().func_76364_f() != null && e.getSource().func_76364_f().getClass().getName().startsWith("com.dhanantry.scapeandrunparasites")) {
         return true;
      } else {
         String dmg = e.getSource().field_76373_n == null ? "" : e.getSource().field_76373_n;
         return dmg.contains("srparasites");
      }
   }

   @SubscribeEvent
   public void onDeath(LivingDeathEvent e) {
      if (e.getEntity() instanceof EntityPlayerMP) {
         if (SRPConfigWorld.escapeEnabled) {
            if (isSrparasitesCause(e)) {
               EntityPlayerMP p = (EntityPlayerMP)e.getEntity();
               long nowMs = p.field_70170_p.func_82737_E() * 50L;
               Deque<Long> q = streaks.computeIfAbsent(p.func_110124_au(), k -> new ArrayDeque<>());
               q.addLast(nowMs);

               while (!q.isEmpty() && nowMs - q.peekFirst() > 60000L) {
                  q.removeFirst();
               }

               boolean offer = q.size() > 5;
               NBTTagCompound persisted = p.getEntityData().func_74775_l("PlayerPersisted");
               persisted.func_74757_a("srp_offer_escape", offer);
               persisted.func_74772_a("srp_death_window", nowMs);
               p.getEntityData().func_74782_a("PlayerPersisted", persisted);
               SRPNetwork.CHANNEL.sendTo(new S2CSetEscapeOffer(offer), p);
            }
         }
      }
   }

   public static void clearOffer(EntityPlayerMP p) {
      NBTTagCompound persisted = p.getEntityData().func_74775_l("PlayerPersisted");
      persisted.func_74757_a("srp_offer_escape", false);
      p.getEntityData().func_74782_a("PlayerPersisted", persisted);
      SRPNetwork.CHANNEL.sendTo(new S2CSetEscapeOffer(false), p);
   }
}
