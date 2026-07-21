package com.dhanantry.scapeandrunparasites.advancements;

import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.MsgQlipShake;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class DispatcherStage4DetectedAdvancement {
   private static final ResourceLocation ADV_ID = new ResourceLocation("srparasites", "qliphoth_overload");
   private static final String CRITERION = "detected_dispatcher_siv";
   private static final double RANGE_SQ = 65536.0;

   public static void register() {
      MinecraftForge.EVENT_BUS.register(new DispatcherStage4DetectedAdvancement());
      System.out.println("[SRP][Qlip] listener registered");
   }

   @SubscribeEvent
   public void onEntityJoinWorld(EntityJoinWorldEvent e) {
      if (!e.getWorld().field_72995_K) {
         Entity ent = e.getEntity();
         ResourceLocation id = EntityList.func_191301_a(ent);
         if (id != null) {
            if ("srparasites".equals(id.func_110624_b())) {
               if ("dispatcher_siv".equals(id.func_110623_a())) {
                  WorldServer ws = (WorldServer)e.getWorld();

                  for (EntityPlayerMP p : ws.func_73046_m().func_184103_al().func_181057_v()) {
                     if (p.field_70170_p.field_73011_w.getDimension() == ws.field_73011_w.getDimension() && !(p.func_70068_e(ent) > 65536.0)) {
                        Advancement adv = p.func_184102_h().func_191949_aK().func_192778_a(ADV_ID);
                        boolean granted = adv != null && p.func_192039_O().func_192750_a(adv, "detected_dispatcher_siv");
                        if (!granted) {
                           System.out.println("[SRP][Qlip] grant failed or already done for " + p.func_70005_c_());
                        } else {
                           SoundEvent se = SRPSounds.DISLO_28;
                           if (se != null) {
                              p.field_71135_a
                                 .func_147359_a(new SPacketSoundEffect(se, SoundCategory.MASTER, p.field_70165_t, p.field_70163_u, p.field_70161_v, 1.0F, 1.0F));
                              p.field_70170_p.func_184148_a(null, p.field_70165_t, p.field_70163_u, p.field_70161_v, se, SoundCategory.MASTER, 1.0F, 1.0F);
                              System.out
                                 .println(
                                    "[SRP][Qlip] played sound "
                                       + (se.getRegistryName() == null ? "<no id>" : se.getRegistryName().toString())
                                       + " for "
                                       + p.func_70005_c_()
                                 );
                           } else {
                              System.out.println("[SRP][Qlip] DISLO_28 is null (not registered)");
                           }

                           SRPNetwork.CHANNEL.sendTo(new MsgQlipShake(0, 20, true, true, 4.0F), p);
                           System.out.println("[SRP][Qlip] sent shake packet to " + p.func_70005_c_());
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
