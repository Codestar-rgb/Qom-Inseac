package com.srp.draconite.init;

import com.srp.draconite.init.ModEntityTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, "srpdraconite");
    public static final RegistryObject<ForgeSpawnEggItem> DRACONITE_SPAWN_EGG = ITEMS.register("draconite_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.DRACONITE.get(), 1718894, 5227511, new Item.Properties()));
}
