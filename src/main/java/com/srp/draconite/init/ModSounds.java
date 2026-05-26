package com.srp.draconite.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, "srpdraconite");
    public static final RegistryObject<SoundEvent> HEBLU_GROWL = SOUNDS.register("entity.draconite.growl", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.draconite.growl")));
    public static final RegistryObject<SoundEvent> HEBLU_HURT = SOUNDS.register("entity.draconite.hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.draconite.hurt")));
    public static final RegistryObject<SoundEvent> HEBLU_DEATH = SOUNDS.register("entity.draconite.death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.draconite.death")));
    public static final RegistryObject<SoundEvent> HEBLU_STEP = SOUNDS.register("entity.draconite.step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.draconite.step")));
    public static final RegistryObject<SoundEvent> HEBLU_SHOOT = SOUNDS.register("entity.draconite.shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.draconite.shoot")));
    public static final RegistryObject<SoundEvent> ORB_SUMMON = SOUNDS.register("entity.orb.summon", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.orb.summon")));
    public static final RegistryObject<SoundEvent> ORB_IDLE = SOUNDS.register("entity.orb.idle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.orb.idle")));
    public static final RegistryObject<SoundEvent> ORB_S = SOUNDS.register("entity.orb.s", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.orb.s")));
    public static final RegistryObject<SoundEvent> ORB_E = SOUNDS.register("entity.orb.e", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.orb.e")));
    public static final RegistryObject<SoundEvent> ADAPTATION_PARTIAL = SOUNDS.register("entity.adaptation.partial", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.adaptation.partial")));
    public static final RegistryObject<SoundEvent> ADAPTATION_FULL = SOUNDS.register("entity.adaptation.full", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.adaptation.full")));
    public static final RegistryObject<SoundEvent> ADAPTATION_IMMUNE = SOUNDS.register("entity.adaptation.immune", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "entity.adaptation.immune")));
    public static final RegistryObject<SoundEvent> TENDRIL_BREAK = SOUNDS.register("misc.tendril", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "misc.tendril")));
    public static final RegistryObject<SoundEvent> MOB_EXPLOSION = SOUNDS.register("misc.explosion", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "misc.explosion")));
    public static final RegistryObject<SoundEvent> MOB_HITGROUND = SOUNDS.register("misc.hitground", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "misc.hitground")));
    public static final RegistryObject<SoundEvent> MOB_SILENCE = SOUNDS.register("misc.silence", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "misc.silence")));
    public static final RegistryObject<SoundEvent> MOB_SWIPE = SOUNDS.register("misc.swipe", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("srpdraconite", "misc.swipe")));
}
