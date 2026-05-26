package com.srp.draconite;

import com.srp.draconite.entity.EntityDraconite;
import com.srp.draconite.init.ModCreativeTabs;
import com.srp.draconite.init.ModEffects;
import com.srp.draconite.init.ModEntityTypes;
import com.srp.draconite.init.ModItems;
import com.srp.draconite.init.ModSounds;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value="srpdraconite")
public class SRPDraconite {
    public static final String MOD_ID = "srpdraconite";
    public static final Logger LOGGER = LogManager.getLogger();

    public SRPDraconite() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onEntityAttributeCreation);
        LOGGER.info("SRP Draconite initialized (GeckoLib-powered)");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.DRACONITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, EntityDraconite::checkDraconiteSpawnRules);
            LOGGER.info("SRP Draconite common setup complete");
        });
    }

    private void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.DRACONITE.get(), EntityDraconite.setAttributes());
    }
}

