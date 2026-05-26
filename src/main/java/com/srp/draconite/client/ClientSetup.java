package com.srp.draconite.client;

import com.srp.draconite.client.renderer.DraconiteBodyPartRenderer;
import com.srp.draconite.client.renderer.DraconiteFireballRenderer;
import com.srp.draconite.client.renderer.DraconiteRenderer;
import com.srp.draconite.client.renderer.OrbBoomRenderer;
import com.srp.draconite.client.renderer.OrbScaryRenderer;
import com.srp.draconite.client.renderer.ToxicCloudRenderer;
import com.srp.draconite.init.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid="srpdraconite", bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(ModEntityTypes.DRACONITE.get(), DraconiteRenderer::new);
            EntityRenderers.register(ModEntityTypes.DRACONITE_FIREBALL.get(), DraconiteFireballRenderer::new);
            EntityRenderers.register(ModEntityTypes.DRACONITE_BODY_PART.get(), DraconiteBodyPartRenderer::new);
            EntityRenderers.register(ModEntityTypes.TOXIC_CLOUD.get(), ToxicCloudRenderer::new);
            EntityRenderers.register(ModEntityTypes.ORB_SCARY.get(), OrbScaryRenderer::new);
            EntityRenderers.register(ModEntityTypes.ORB_BOOM.get(), OrbBoomRenderer::new);
        });
    }
}
