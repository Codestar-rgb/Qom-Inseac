package com.srp.draconite.init;

import com.srp.draconite.entity.EntityDraconite;
import com.srp.draconite.entity.EntityDraconiteBodyPart;
import com.srp.draconite.entity.EntityDraconiteFireball;
import com.srp.draconite.entity.EntityOrbBoom;
import com.srp.draconite.entity.EntityOrbScary;
import com.srp.draconite.entity.EntityToxicCloud;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, "srpdraconite");
    public static final RegistryObject<EntityType<EntityDraconite>> DRACONITE = ENTITY_TYPES.register("draconite", () -> EntityType.Builder.of(EntityDraconite::new, MobCategory.MONSTER).dimensions(2.4f, 3.8f).clientTrackingRange(32).updateInterval(3).build("draconite"));
    public static final RegistryObject<EntityType<EntityDraconiteFireball>> DRACONITE_FIREBALL = ENTITY_TYPES.register("draconite_fireball", () -> EntityType.Builder.of(EntityDraconiteFireball::new, MobCategory.MISC).dimensions(0.3f, 0.3f).clientTrackingRange(16).updateInterval(1).noSummon().build("draconite_fireball"));
    public static final RegistryObject<EntityType<EntityDraconiteBodyPart>> DRACONITE_BODY_PART = ENTITY_TYPES.register("draconite_body_part", () -> EntityType.Builder.of(EntityDraconiteBodyPart::new, MobCategory.MISC).dimensions(1.5f, 2.0f).clientTrackingRange(16).updateInterval(3).noSummon().build("draconite_body_part"));
    public static final RegistryObject<EntityType<EntityToxicCloud>> TOXIC_CLOUD = ENTITY_TYPES.register("toxic_cloud", () -> EntityType.Builder.of(EntityToxicCloud::new, MobCategory.MISC).dimensions(6.0f, 0.5f).clientTrackingRange(16).updateInterval(10).build("toxic_cloud"));
    public static final RegistryObject<EntityType<EntityOrbScary>> ORB_SCARY = ENTITY_TYPES.register("orb_scary", () -> EntityType.Builder.of(EntityOrbScary::new, MobCategory.MISC).dimensions(0.6f, 0.6f).clientTrackingRange(16).updateInterval(3).noSummon().build("orb_scary"));
    public static final RegistryObject<EntityType<EntityOrbBoom>> ORB_BOOM = ENTITY_TYPES.register("orb_boom", () -> EntityType.Builder.of(EntityOrbBoom::new, MobCategory.MISC).dimensions(0.5f, 0.5f).clientTrackingRange(16).updateInterval(2).noSummon().build("orb_boom"));
}
