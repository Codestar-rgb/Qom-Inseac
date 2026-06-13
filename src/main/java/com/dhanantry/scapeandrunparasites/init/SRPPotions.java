/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.potion.PotionType
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.dhanantry.scapeandrunparasites.init;

import com.dhanantry.scapeandrunparasites.potion.EffectDodSmokeTrail;
import com.dhanantry.scapeandrunparasites.potion.PotionBleed;
import com.dhanantry.scapeandrunparasites.potion.PotionContamination;
import com.dhanantry.scapeandrunparasites.potion.PotionCorrosion;
import com.dhanantry.scapeandrunparasites.potion.PotionFoster;
import com.dhanantry.scapeandrunparasites.potion.PotionNeedler;
import com.dhanantry.scapeandrunparasites.potion.PotionOverheat;
import com.dhanantry.scapeandrunparasites.potion.PotionPrey;
import com.dhanantry.scapeandrunparasites.potion.PotionSpotted;
import com.dhanantry.scapeandrunparasites.potion.SRPEffectBase;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class SRPPotions {
    public static final Potion COTH_E = new SRPEffectBase("coth", false, 5046283, 0, 0);
    public static final Potion DOD_SMOKE_TRAIL_E = new EffectDodSmokeTrail();
    public static final Potion THORNSHADE_THORNS_E = new SRPEffectBase("thornshade_thorns", false, 4333438, 1, 0);
    public static final Potion FEAR_E = new SRPEffectBase("fear", false, 0x111114, 0, 0);
    public static final Potion RES_E = new SRPEffectBase("antimall", true, 8938092, 0, 0);
    public static final Potion BLEED_E = new PotionBleed("bleed", true, 6162438, 0, 0);
    public static final Potion CORRO_E = new PotionCorrosion("corrosive", true, 8020058, 0, 0);
    public static final Potion VIRA_E = new SRPEffectBase("viral", true, 1270580, 0, 0);
    public static final Potion VOMIT_E = new SRPEffectBase("vomit", false, 7498817, 0, 0).func_111184_a(SharedMonsterAttributes.field_111265_b, UUID.randomUUID().toString(), 0.9, 2);
    public static final Potion RAGE_E = new SRPEffectBase("rage", false, 16270147, 0, 0).func_111184_a(SharedMonsterAttributes.field_111263_d, UUID.randomUUID().toString(), SRPConfigSystems.rageSpeed, 2).func_111184_a(SharedMonsterAttributes.field_111264_e, UUID.randomUUID().toString(), SRPConfigSystems.rageDamage, 2);
    public static final Potion EPEL_E = new SRPEffectBase("repel", false, 4434992, 0, 0);
    public static final Potion SENS_E = new SRPEffectBase("senses", false, 9346775, 0, 0).func_111184_a(SharedMonsterAttributes.field_111265_b, UUID.randomUUID().toString(), 0.1, 2);
    public static final Potion PREY_E = new PotionPrey("prey", true, 4800055, 0, 0);
    public static final Potion DEBAR_E = new SRPEffectBase("debar", false, 10359627, 0, 0);
    public static final Potion DLER_E = new PotionNeedler("needler", false, 13086595, 0, 0);
    public static final Potion FOSTER_E = new PotionFoster("foster", false, 5804908, 0, 0);
    public static final Potion LINK_E = new SRPEffectBase("link", false, 16741781, 0, 0);
    public static final Potion PIVOT_E = new SRPEffectBase("pivot", false, 16757187, 0, 0);
    public static final Potion JUGG_E = new SRPEffectBase("jugg", false, 12433541, 0, 0);
    public static final Potion PARATE_E = new SRPEffectBase("parate", false, 11753270, 0, 0);
    public static final Potion KILLPRI_E = new SRPEffectBase("primitive", false, 9391173, 0, 0);
    public static final Potion KILLADA_E = new SRPEffectBase("adapted", false, 8345678, 0, 0);
    public static final Potion KILLPUR_E = new SRPEffectBase("pure", false, 894258, 0, 0);
    public static final Potion KILLCRU_E = new SRPEffectBase("crude", false, 894258, 0, 0);
    public static final Potion KILLFER_E = new SRPEffectBase("feral", false, 0x993030, 0, 0);
    public static final Potion KILLNEX_E = new SRPEffectBase("nexus", false, 0x487848, 0, 0);
    public static final Potion SPOT_E = new PotionSpotted("spotted", false, 8149607, 0, 0);
    public static final Potion BRAINING_E = new SRPEffectBase("braining", false, 7958149, 0, 0);
    public static final Potion NOVISION_E = new SRPEffectBase("novision", false, 1582649, 0, 0);
    public static final Potion INDEAF_E = new SRPEffectBase("indeaf", false, 0xFFDD00, 0, 0);
    public static final Potion OVERHEATING_E = new PotionOverheat("overheating", true, 16746246, 0, 0);
    public static final Potion CONTA_E = new PotionContamination("conta", true, 10350848, 0, 0);
    public static final Potion MUSCLEOUT_E = new SRPEffectBase("muscleout", true, 15499138, 0, 0);
    public static final Potion EFFECTPOS_E = new SRPEffectBase("effectpos", true, 12095688, 0, 0);
    public static final Potion EFFECTNEG_E = new SRPEffectBase("effectneg", true, 7318708, 0, 0);
    public static final Potion THE_SIGN_E = new SRPEffectBase("the_sign", false, 8970751, 0, 0);
    public static final PotionType COTH_P = (PotionType)new PotionType("srparasites:coth", new PotionEffect[]{new PotionEffect(COTH_E, 2400)}).setRegistryName("srparasites:coth");
    public static final PotionType FEAR_P = (PotionType)new PotionType("srparasites:fear", new PotionEffect[]{new PotionEffect(FEAR_E, 2400)}).setRegistryName("srparasites:fear");
    public static final PotionType RES_P = (PotionType)new PotionType("srparasites:antimall", new PotionEffect[]{new PotionEffect(RES_E, 2400)}).setRegistryName("srparasites:res");
    public static final PotionType CORRO_P = (PotionType)new PotionType("srparasites:corrosive", new PotionEffect[]{new PotionEffect(CORRO_E, 2400)}).setRegistryName("srparasites:corro");
    public static final PotionType VIRA_P = (PotionType)new PotionType("srparasites:viral", new PotionEffect[]{new PotionEffect(VIRA_E, 2400)}).setRegistryName("srparasites:vira");
    public static final PotionType VOMIT_P = (PotionType)new PotionType("srparasites:vomit", new PotionEffect[]{new PotionEffect(VOMIT_E, 2400)}).setRegistryName("srparasites:vomit");
    public static final PotionType RAGE_P = (PotionType)new PotionType("srparasites:rage", new PotionEffect[]{new PotionEffect(RAGE_E, 2400)}).setRegistryName("srparasites:rage");
    public static final PotionType EPEL_P = (PotionType)new PotionType("srparasites:repel", new PotionEffect[]{new PotionEffect(EPEL_E, 2400)}).setRegistryName("srparasites:repel");
    public static final PotionType SENS_P = (PotionType)new PotionType("srparasites:senses", new PotionEffect[]{new PotionEffect(SENS_E, 2400)}).setRegistryName("srparasites:senses");
    public static final PotionType DEBAR_P = (PotionType)new PotionType("srparasites:debar", new PotionEffect[]{new PotionEffect(DEBAR_E, 2400)}).setRegistryName("srparasites:debar");
    public static final PotionType FOSTER_P = (PotionType)new PotionType("srparasites:foster", new PotionEffect[]{new PotionEffect(FOSTER_E, 2400)}).setRegistryName("srparasites:foster");
    public static final PotionType LINK_P = (PotionType)new PotionType("srparasites:link", new PotionEffect[]{new PotionEffect(LINK_E, 2400)}).setRegistryName("srparasites:link");
    public static final PotionType PIVOT_P = (PotionType)new PotionType("srparasites:pivot", new PotionEffect[]{new PotionEffect(PIVOT_E, 2400)}).setRegistryName("srparasites:pivot");
    public static final PotionType JUGG_P = (PotionType)new PotionType("srparasites:jugg", new PotionEffect[]{new PotionEffect(JUGG_E, 2400)}).setRegistryName("srparasites:jugg");
    public static final PotionType PARATE_P = (PotionType)new PotionType("srparasites:parate", new PotionEffect[]{new PotionEffect(PARATE_E, 2400)}).setRegistryName("srparasites:parate");
    public static final PotionType KILLPRI_P = (PotionType)new PotionType("srparasites:primitive", new PotionEffect[]{new PotionEffect(KILLPRI_E, 2400)}).setRegistryName("srparasites:primitive");
    public static final PotionType KILLADA_P = (PotionType)new PotionType("srparasites:adapted", new PotionEffect[]{new PotionEffect(KILLADA_E, 2400)}).setRegistryName("srparasites:adapted");
    public static final PotionType KILLPUR_P = (PotionType)new PotionType("srparasites:pure", new PotionEffect[]{new PotionEffect(KILLPUR_E, 2400)}).setRegistryName("srparasites:pure");
    public static final PotionType KILLCRU_P = (PotionType)new PotionType("srparasites:crude", new PotionEffect[]{new PotionEffect(KILLCRU_E, 2400)}).setRegistryName("srparasites:crude");
    public static final PotionType KILLFER_P = (PotionType)new PotionType("srparasites:feral", new PotionEffect[]{new PotionEffect(KILLFER_E, 2400)}).setRegistryName("srparasites:feral");
    public static final PotionType KILLNEX_P = (PotionType)new PotionType("srparasites:nexus", new PotionEffect[]{new PotionEffect(KILLNEX_E, 2400)}).setRegistryName("srparasites:nexus");
    public static final PotionType SPOT_P = (PotionType)new PotionType("srparasites:spotted", new PotionEffect[]{new PotionEffect(SPOT_E, 2400)}).setRegistryName("srparasites:spotted");
    public static final PotionType BRAINING_P = (PotionType)new PotionType("srparasites:braining", new PotionEffect[]{new PotionEffect(BRAINING_E, 2400)}).setRegistryName("srparasites:braining");
    public static final PotionType NOVISION_P = (PotionType)new PotionType("srparasites:novision", new PotionEffect[]{new PotionEffect(NOVISION_E, 2400)}).setRegistryName("srparasites:novision");
    public static final PotionType THE_SIGN_P = (PotionType)new PotionType("srparasites:the_sign", new PotionEffect[]{new PotionEffect(THE_SIGN_E, 2400)}).setRegistryName("srparasites:the_sign");
    public static final PotionType INDEAF_P = (PotionType)new PotionType("srparasites:indeaf", new PotionEffect[]{new PotionEffect(INDEAF_E, 2400)}).setRegistryName("srparasites:indeaf");
    public static final PotionType OVERHEATING_P = (PotionType)new PotionType("srparasites:overheating", new PotionEffect[]{new PotionEffect(OVERHEATING_E, 2400)}).setRegistryName("srparasites:overheating");
    public static final PotionType CONTA_P = (PotionType)new PotionType("srparasites:conta", new PotionEffect[]{new PotionEffect(CONTA_E, 2400)}).setRegistryName("srparasites:conta");
    public static final PotionType MUSCLEOUT_P = (PotionType)new PotionType("srparasites:muscleout", new PotionEffect[]{new PotionEffect(MUSCLEOUT_E, 2400)}).setRegistryName("srparasites:muscleout");
    public static final PotionType EFFECTPOS_P = (PotionType)new PotionType("srparasites:effectpos", new PotionEffect[]{new PotionEffect(EFFECTPOS_E, 2400)}).setRegistryName("srparasites:effectpos");
    public static final PotionType EFFECTNEG_P = (PotionType)new PotionType("srparasites:effectneg", new PotionEffect[]{new PotionEffect(EFFECTNEG_E, 2400)}).setRegistryName("srparasites:effectneg");
    public static final PotionType THORNSHADE_THORNS_P = (PotionType)new PotionType("srparasites:thornshade_thorns", new PotionEffect[]{new PotionEffect(THORNSHADE_THORNS_E, 60)}).setRegistryName("srparasites:thornshade_thorns");

    public static void applyStackPotion(Potion effect, EntityLivingBase in, int duration, int amp) {
        if (amp + 1 >= 256) {
            return;
        }
        if (amp - 1 <= -256) {
            return;
        }
        PotionEffect flag = in.func_70660_b(effect);
        if (flag != null) {
            String name = effect.getRegistryName().toString();
            int lockAmp = 0;
            int newDur = flag.func_76459_b();
            newDur = newDur + 40 <= duration ? duration : (newDur += 10);
            String[] here = new String[2];
            for (int i = 0; i < SRPConfig.stackablePotionsLimit.length; ++i) {
                here = SRPConfig.stackablePotionsLimit[i].split(";");
                if (!here[0].equals(name)) continue;
                lockAmp = Integer.parseInt(here[1]);
                if (amp <= lockAmp && flag.func_76458_c() + 1 <= lockAmp) break;
                in.func_70690_d(new PotionEffect(effect, newDur, lockAmp, false, false));
                return;
            }
            if (flag.func_76458_c() < amp) {
                in.func_70690_d(new PotionEffect(effect, newDur, amp, false, false));
                return;
            }
            int newAmp = flag.func_76458_c();
            newAmp = newAmp < 0 ? --newAmp : ++newAmp;
            in.func_70690_d(new PotionEffect(effect, newDur, newAmp, false, false));
        } else {
            in.func_70690_d(new PotionEffect(effect, duration, amp, false, false));
        }
    }

    public static boolean applySense(EntityLivingBase in, int duration, double rangeToCover, int withLimit) {
        return true;
    }

    @Mod.EventBusSubscriber(modid="srparasites")
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEventE(RegistryEvent.Register<Potion> event) {
            IForgeRegistry registry = event.getRegistry();
            registry.register((IForgeRegistryEntry)COTH_E);
            registry.register((IForgeRegistryEntry)DOD_SMOKE_TRAIL_E);
            registry.register((IForgeRegistryEntry)THORNSHADE_THORNS_E);
            registry.register((IForgeRegistryEntry)FEAR_E);
            registry.register((IForgeRegistryEntry)RES_E);
            registry.register((IForgeRegistryEntry)BLEED_E);
            registry.register((IForgeRegistryEntry)CORRO_E);
            registry.register((IForgeRegistryEntry)VIRA_E);
            registry.register((IForgeRegistryEntry)VOMIT_E);
            registry.register((IForgeRegistryEntry)RAGE_E);
            registry.register((IForgeRegistryEntry)EPEL_E);
            registry.register((IForgeRegistryEntry)SENS_E);
            registry.register((IForgeRegistryEntry)PREY_E);
            registry.register((IForgeRegistryEntry)DEBAR_E);
            registry.register((IForgeRegistryEntry)DLER_E);
            registry.register((IForgeRegistryEntry)FOSTER_E);
            registry.register((IForgeRegistryEntry)LINK_E);
            registry.register((IForgeRegistryEntry)PIVOT_E);
            registry.register((IForgeRegistryEntry)JUGG_E);
            registry.register((IForgeRegistryEntry)PARATE_E);
            registry.register((IForgeRegistryEntry)KILLPRI_E);
            registry.register((IForgeRegistryEntry)KILLADA_E);
            registry.register((IForgeRegistryEntry)KILLPUR_E);
            registry.register((IForgeRegistryEntry)KILLCRU_E);
            registry.register((IForgeRegistryEntry)KILLFER_E);
            registry.register((IForgeRegistryEntry)KILLNEX_E);
            registry.register((IForgeRegistryEntry)SPOT_E);
            registry.register((IForgeRegistryEntry)BRAINING_E);
            registry.register((IForgeRegistryEntry)NOVISION_E);
            registry.register((IForgeRegistryEntry)INDEAF_E);
            registry.register((IForgeRegistryEntry)OVERHEATING_E);
            registry.register((IForgeRegistryEntry)CONTA_E);
            registry.register((IForgeRegistryEntry)MUSCLEOUT_E);
            registry.register((IForgeRegistryEntry)EFFECTPOS_E);
            registry.register((IForgeRegistryEntry)EFFECTNEG_E);
            registry.register((IForgeRegistryEntry)THE_SIGN_E);
        }

        @SubscribeEvent
        public static void onEventP(RegistryEvent.Register<PotionType> event) {
            IForgeRegistry registry = event.getRegistry();
            registry.register((IForgeRegistryEntry)COTH_P);
            registry.register((IForgeRegistryEntry)THORNSHADE_THORNS_P);
            registry.register((IForgeRegistryEntry)FEAR_P);
            registry.register((IForgeRegistryEntry)RES_P);
            registry.register((IForgeRegistryEntry)CORRO_P);
            registry.register((IForgeRegistryEntry)VIRA_P);
            registry.register((IForgeRegistryEntry)VOMIT_P);
            registry.register((IForgeRegistryEntry)RAGE_P);
            registry.register((IForgeRegistryEntry)EPEL_P);
            registry.register((IForgeRegistryEntry)SENS_P);
            registry.register((IForgeRegistryEntry)DEBAR_P);
            registry.register((IForgeRegistryEntry)FOSTER_P);
            registry.register((IForgeRegistryEntry)LINK_P);
            registry.register((IForgeRegistryEntry)PIVOT_P);
            registry.register((IForgeRegistryEntry)JUGG_P);
            registry.register((IForgeRegistryEntry)PARATE_P);
            registry.register((IForgeRegistryEntry)KILLPRI_P);
            registry.register((IForgeRegistryEntry)KILLADA_P);
            registry.register((IForgeRegistryEntry)KILLPUR_P);
            registry.register((IForgeRegistryEntry)KILLCRU_P);
            registry.register((IForgeRegistryEntry)KILLFER_P);
            registry.register((IForgeRegistryEntry)KILLNEX_P);
            registry.register((IForgeRegistryEntry)SPOT_P);
            registry.register((IForgeRegistryEntry)BRAINING_P);
            registry.register((IForgeRegistryEntry)NOVISION_P);
            registry.register((IForgeRegistryEntry)INDEAF_P);
            registry.register((IForgeRegistryEntry)OVERHEATING_P);
            registry.register((IForgeRegistryEntry)CONTA_P);
            registry.register((IForgeRegistryEntry)MUSCLEOUT_P);
            registry.register((IForgeRegistryEntry)EFFECTPOS_P);
            registry.register((IForgeRegistryEntry)EFFECTNEG_P);
            registry.register((IForgeRegistryEntry)THE_SIGN_P);
        }
    }
}

