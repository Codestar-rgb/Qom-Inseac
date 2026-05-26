package com.srp.draconite.init;

import com.srp.draconite.potion.BleedEffect;
import com.srp.draconite.potion.CothEffect;
import com.srp.draconite.potion.NeedlerEffect;
import com.srp.draconite.potion.RageEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, "srpdraconite");
    public static final RegistryObject<MobEffect> COTH = EFFECTS.register("coth", CothEffect::new);
    public static final RegistryObject<MobEffect> DLER = EFFECTS.register("needler", NeedlerEffect::new);
    public static final RegistryObject<MobEffect> RAGE = EFFECTS.register("rage", RageEffect::new);
    public static final RegistryObject<MobEffect> BLEED = EFFECTS.register("bleed", BleedEffect::new);
}
