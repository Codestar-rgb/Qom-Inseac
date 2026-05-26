package com.srp.draconite.init;

import com.srp.draconite.init.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "srpdraconite");
    public static final RegistryObject<CreativeModeTab> SRP_DRACONITE_TAB = CREATIVE_TABS.register("srpdraconite_tab", () -> CreativeModeTab.builder().title(Component.literal("itemGroup.srpdraconite")).icon(() -> new ItemStack(ModItems.DRACONITE_SPAWN_EGG.get())).displayItems((parameters, output) -> output.accept(ModItems.DRACONITE_SPAWN_EGG.get())).build());
}
