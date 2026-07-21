package com.dhanantry.scapeandrunparasites.util.config;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.proxy.CommonProxy;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

public class SRPConfig {
   private static final String CATEGORY_GENERAL = "configuration_general";
   public static boolean allowMobs = true;
   public static boolean worldGIU = true;
   public static boolean mobsKilledDropLoot = true;
   public static float globalHealthMultiplier = 1.0F;
   public static float globalArmorMultiplier = 1.0F;
   public static float globalDamageMultiplier = 1.0F;
   public static float globalKDResistanceMultiplier = 1.0F;
   public static int[] blackListedDimensions = new int[]{-1, 1};
   public static boolean blackListedDimensionsWhite = false;
   public static String[] entitiesWillAttack = new String[]{"minecraft:villager_golem"};
   public static boolean entitiesWillAttackWhite = false;
   public static String[] entitiesWillAvoid = new String[]{"minecraft:villager"};
   public static boolean entitiesWillAvoidWhite = false;
   public static boolean vanillaEggs = false;
   public static boolean ignoreL = false;
   public static int spawnDays = 0;
   public static int dayTickValue = 24000;
   public static boolean thunderEnable = true;
   public static boolean easterEggs = true;
   public static double blockParticleChance = 0.05;
   public static double blockDropChance = 0.05;
   public static double blockSoundChance = 0.05;
   public static double parasiteNameTagChance = 1.0;
   public static boolean musicTrue = true;
   public static int musicMin = 14420;
   public static int musicMax = 18200;
   private static final String PROPERTIES_GENERAL = "parasite_properties";
   public static int worldMobCap = 40;
   public static int worldSpawningMobCap = 20;
   public static int worldGnatCap = 20;
   public static int worldBeckonSpawnsCap = 20;
   public static int worldWaterCap = 3;
   public static int worldAirCap = 3;
   public static int worldMobCapPlusPlayer = 5;
   public static int incompleteCap = 10;
   public static int nexusVenkrolCap = 5;
   public static int nexusVenkrolDis = 32;
   public static int nexusDodCap = 5;
   public static int nexusDodDis = 32;
   public static int nexusLeemCap = 5;
   public static int nexusLeemDis = 32;
   public static boolean nexusStructures = true;
   public static boolean canOrbAttack = true;
   public static boolean mobattacking = true;
   public static boolean paraGore = true;
   public static boolean explotionDamPara = true;
   public static String[] mobattackingBlackList = new String[]{"srmonstress", "minecraft:creeper", "minecraft:bat"};
   public static int BlockInfestedLimit = 1000;
   public static int BlockParasiteLimit = 2000;
   public static int BlockInfestedLimitCD = 300;
   public static int BlockParasiteLimitCD = 300;
   public static boolean mobattackingBlackListWhite = false;
   public static boolean mobAttackingFull = false;
   public static double killcountplus = 0.0;
   public static float firemultyplier = 4.0F;
   public static boolean infectedAssimilation = true;
   public static float miniminiCap = 5.0F;
   public static String[] parasiteGriefing = new String[]{
      "srparasites:sim_dragone;3;60;4",
      "srparasites:hi_golem;3;40;2",
      "srparasites:mar_cow;1;40;1",
      "srparasites:mar_enderman;1;40;1",
      "srparasites:mar_bear;1;40;1",
      "srparasites:pri_longarms;1;60;1",
      "srparasites:pri_reeker;1;60;1",
      "srparasites:pri_summoner;1;60;1",
      "srparasites:pri_manducater;1;60;1",
      "srparasites:pri_yelloweye;1;60;1",
      "srparasites:pri_arachnida;1;60;1",
      "srparasites:pri_bolster;1;60;1",
      "srparasites:pri_vermin;1;60;1",
      "srparasites:pri_devourer;1;900;2",
      "srparasites:beckon_si;3;60;1",
      "srparasites:dispatcher_si;3;60;1",
      "srparasites:ada_longarms;3;40;1",
      "srparasites:ada_reeker;3;40;1",
      "srparasites:ada_summoner;3;40;1",
      "srparasites:ada_manducater;3;40;2",
      "srparasites:ada_yelloweye;3;40;2",
      "srparasites:ada_arachnida;3;40;2",
      "srparasites:ada_bolster;3.5;20;2",
      "srparasites:ada_devourer;3.5;540;3",
      "srparasites:beckon_sii;5.5;20;2",
      "srparasites:dispatcher_sii;5.5;20;2",
      "srparasites:warden;5;20;2",
      "srparasites:marauder;5;10;3",
      "srparasites:vigilante;5;20;2",
      "srparasites:overseer;5;20;2",
      "srparasites:bomber_light;5;20;2",
      "srparasites:grunt;3;20;1",
      "srparasites:monarch;5;20;4",
      "srparasites:beckon_siii;7;20;4",
      "srparasites:dispatcher_siii;7;20;4",
      "srparasites:wraith;15;60;5",
      "srparasites:bogle;15;60;5",
      "srparasites:haunter;15;60;5",
      "srparasites:carrier_colony;15;60;5",
      "srparasites:succor;15;60;2",
      "srparasites:bomber_heavy;15;60;5",
      "srparasites:beckon_siv;18;20;5",
      "srparasites:dispatcher_siv;18;20;5",
      "srparasites:kyphosis;7;20;3",
      "srparasites:sentry;7;20;3",
      "srparasites:anc_dreadnaut;9;5;4",
      "srparasites:anc_overlord;9;5;4",
      "srparasites:draconite;27;80;6",
      "srparasites:kirin;27;60;3",
      "srparasites:crux;4;30;2"
   };
   public static String[] parasiteWeakToMobs = new String[0];
   public static String[] parasiteWeakToItems = new String[0];
   public static String[] parasiteWeakToElse = new String[0];
   public static String[] adaptationDimStrong = new String[0];
   public static String[] parasiteGriefingBlackList = new String[0];
   public static boolean parasiteGriefingWhite = false;
   public static double tendrilHealth = 0.4;
   public static int fireTickWindow = 10;
   public static int adaptationNewDamageCooldon = 20;
   public static boolean parasiteGenResidue = true;
   public static boolean cystActive = true;
   public static int cystDelay = 25;
   public static int cystConsumePoint = 2;
   public static boolean convertedDespawn = false;
   public static boolean rsDespawn = false;
   public static boolean doTileDrops = true;
   public static int orbFoodMult = 5;
   public static double variantChance = 0.33;
   public static double infectedFollow = 16.0;
   public static int infectedCap = 2;
   public static boolean infecteddespawn = true;
   public static float infectedBleedingChance = 0.2F;
   public static float infectedMinDamage = 0.5F;
   public static int infectedOneMindDeathV = 5;
   public static int infectedXPValue = 8;
   public static int infectedRemainValue = 10;
   public static int infectedLoosingEPValue = 1;
   public static double infectedSneakPen = 0.8F;
   public static float infectedInviPen = 0.7F;
   public static double assimaraFollow = 24.0;
   public static int assimaraCap = 5;
   public static boolean assimaradespawn = true;
   public static float assimaraMinDamage = 1.1F;
   public static int assimaraOneMindDeathV = 5;
   public static int assimaraLoosingEPValue = 5;
   public static double assimaraSneakPen = 0.8F;
   public static float assimaraInviPen = 0.7F;
   public static double feralFollow = 20.0;
   public static int feralCap = 3;
   public static boolean feraldespawn = true;
   public static float feralMinDamage = 0.75F;
   public static int feralOneMindDeathV = 15;
   public static int feralXPValue = 16;
   public static int feralRemainValue = 12;
   public static int feralLoosingEPValue = 5;
   public static double feralSneakPen = 1.0;
   public static float feralInviPen = 1.0F;
   public static double feralKills = 60.0;
   public static float feralFoodSteal = 1.0F;
   public static float feralRegen = 3.0F;
   public static double feralMult = 0.3;
   public static double hijackedFollow = 24.0;
   public static int hijackedCap = 5;
   public static boolean hijackeddespawn = true;
   public static float hijackedMinDamage = 1.3F;
   public static int hijackedOneMindDeathV = 5;
   public static int hijackedXPValue = 11;
   public static int hijackedLoosingEPValue = 5;
   public static double hijackedSneakPen = 0.8F;
   public static float hijackedInviPen = 0.7F;
   public static float primitivePointRed = 0.05F;
   public static int primitivePointCap = 12;
   public static int primitivePointDamCap = 5;
   public static double primitiveChanceLe = 0.7;
   public static double primitiveChanceLeFire = 0.7;
   public static double primitiveFollow = 24.0;
   public static double primitiveKills = 10.0;
   public static int primitiveCap = 6;
   public static boolean primitivedespawn = true;
   public static float primitiveMinDamage = 2.0F;
   public static float primitiveRegen = 4.0F;
   public static int primitiveOneMindDeathV = 20;
   public static int primitiveXPValue = 30;
   public static int primitiveRemainValue = 15;
   public static int primitiveLoosingEPValue = 10;
   public static float primitiveFoodSteal = 0.5F;
   public static int primitiveItemOrbCooldown = 5;
   public static double primitiveFoodChance = 0.1;
   public static int primitiveFoodAmount = 3;
   public static double primitiveSneakPen = 1.0;
   public static float primitiveInviPen = 1.0F;
   public static boolean primitiveWalls = false;
   public static float adaptedPointRed = 0.1F;
   public static int adaptedPointCap = 10;
   public static int adaptedPointDamCap = 8;
   public static double adaptedChanceLe = 0.8;
   public static double adaptedChanceLeFire = 0.5;
   public static double adaptedFollow = 32.0;
   public static double adaptedKills = 30.0;
   public static int adaptedCap = 9;
   public static boolean adapteddespawn = true;
   public static float adaptedMinDamage = 4.0F;
   public static float adaptedRegen = 7.0F;
   public static int adaptedOneMindDeathV = 60;
   public static int adaptedXPValue = 55;
   public static int adaptedRemainValue = 20;
   public static int adaptedLoosingEPValue = 200;
   public static float adaptedFoodSteal = 1.0F;
   public static int adaptedItemOrbCooldown = 10;
   public static double adaptedFoodChance = 0.2;
   public static int adaptedFoodAmount = 5;
   public static double adaptedSneakPen = 1.0;
   public static float adaptedInviPen = 1.0F;
   public static boolean adaptedWalls = false;
   public static float ancientPointRed = 0.1F;
   public static int ancientPointCap = 10;
   public static int ancientPointDamCap = 5;
   public static double ancientChanceLe = 0.9F;
   public static double ancientChanceLeFire = 0.1F;
   public static double ancientFollow = 64.0;
   public static int ancientCap = 5;
   public static boolean ancientdespawn = true;
   public static float ancientMinDamage = 2.5F;
   public static float ancientRegen = 30.0F;
   public static int ancientOneMindDeathV;
   public static int ancientXPValue = 5000;
   public static int ancientLoosingEPValue = 1;
   public static float purePointRed = 0.125F;
   public static int purePointCap = 8;
   public static int purePointDamCap = 12;
   public static double pureChanceLe = 0.95;
   public static double pureChanceLeFire = 0.3;
   public static double pureFollow = 32.0;
   public static int pureCap = 13;
   public static boolean puredespawn = true;
   public static float pureMinDamage = 7.0F;
   public static float pureRegen = 14.0F;
   public static int pureOneMindDeathV = 130;
   public static int pureXPValue = 75;
   public static int pureRemainValue = 25;
   public static int pureLoosingEPValue = 1000;
   public static float pureFoodSteal = 2.0F;
   public static int pureExpSteal = 180;
   public static int pureItemOrbCooldown = 17;
   public static double pureFoodChance = 0.3;
   public static int pureFoodAmount = 8;
   public static int pureSeiZ = 3;
   public static double pureSneakPen = 1.0;
   public static float pureInviPen = 1.0F;
   public static boolean pureWalls = false;
   public static float preeminentPointRed = 0.2F;
   public static int preeminentPointCap = 5;
   public static int preeminentPointDamCap = 20;
   public static double preeminentChanceLe = 1.0;
   public static double preeminentChanceLeFire = 0.3;
   public static double preeminentFollow = 80.0;
   public static int preeminentCap = 18;
   public static boolean preeminentdespawn = true;
   public static float preeminentMinDamage = 10.0F;
   public static float preeminentRegen = 20.0F;
   public static int preeminentOneMindDeathV;
   public static int preeminentXPValue = 200;
   public static int preeminentRemainValue = 25;
   public static int preeminentLoosingEPValue = 30000;
   public static float preeminentFoodSteal = 5.0F;
   public static int preeminentExpSteal = 340;
   public static int preeminentItemOrbCooldown = 30;
   public static int preeminentFlamTotal = 3;
   public static double preeminentFoodChance = 0.4;
   public static int preeminentFoodAmount = 12;
   public static double preeminentSneakPen = 1.0;
   public static float preeminentInviPen = 1.0F;
   public static boolean preeminentWalls = false;
   public static float derivedPointRed = 0.2F;
   public static int derivedPointCap = 5;
   public static int derivedPointDamCap = 30;
   public static double derivedChanceLe = 1.0;
   public static double derivedChanceLeFire = 0.1;
   public static double derivedFollow = 80.0;
   public static int derivedCap = 25;
   public static boolean deriveddespawn = true;
   public static float derivedMinDamage = 14.0F;
   public static float derivedRegen = 25.0F;
   public static int derivedOneMindDeathV;
   public static int derivedXPValue = 350;
   public static int derivedRemainValue = 20;
   public static int derivedLoosingEPValue = 45000;
   public static float derivedFoodSteal = 8.0F;
   public static int derivedItemOrbCooldown = 20;
   public static double derivedFoodChance = 0.5;
   public static int derivedFoodAmount = 10;
   public static double derivedSneakPen = 1.0;
   public static float derivedInviPen = 1.0F;
   public static boolean derivedWalls = true;
   public static float derivedHackHealing = 0.01F;
   public static String[] derivedHackingEffects = new String[]{
      "140;minecraft:mining_fatigue;1",
      "140;minecraft:nausea;1",
      "140;minecraft:blindness;1",
      "140;minecraft:hunger;1",
      "140;minecraft:weakness;1",
      "140;srparasites:bleed;1",
      "140;srparasites:effectneg;1",
      "140;srparasites:effectpos;1",
      "140;srparasites:muscleout;1",
      "140;srparasites:overheating;1",
      "140;srparasites:indeaf;1",
      "140;srparasites:novision;1",
      "140;srparasites:braining;1"
   };
   public static float nexussiPointRed = 0.07F;
   public static int nexussiPointCap = 10;
   public static int nexussiPointDamCap = 5;
   public static double nexussiChanceLe = 0.7F;
   public static double nexussiChanceLeFire = 0.7F;
   public static double nexussiFollow = 16.0;
   public static int nexussiCap = 4;
   public static int nexussiLoosingEPValue = 3;
   public static int nexusMinGrowTime = 240;
   public static int nexusMaxGrowTime = 300;
   public static float nexussiiPointRed = 0.125F;
   public static int nexussiiPointCap = 8;
   public static int nexussiiPointDamCap = 10;
   public static double nexussiiChanceLe = 0.8F;
   public static double nexussiiChanceLeFire = 0.5;
   public static double nexussiiFollow = 32.0;
   public static int nexussiiCap = 8;
   public static int nexussiiLoosingEPValue = 15;
   public static int nexussiiMinGrowTime = 300;
   public static int nexussiiMaxGrowTime = 360;
   public static float nexussiiiPointRed = 0.17F;
   public static int nexussiiiPointCap = 6;
   public static int nexussiiiPointDamCap = 15;
   public static double nexussiiiChanceLe = 0.9F;
   public static double nexussiiiChanceLeFire = 0.3F;
   public static double nexussiiiFollow = 48.0;
   public static int nexussiiiCap = 14;
   public static int nexussiiiLoosingEPValue = 150;
   public static int nexussiiiMinGrowTime = 600;
   public static int nexussiiiMaxGrowTime = 1200;
   public static float nexussivPointRed = 0.25F;
   public static int nexussivPointCap = 4;
   public static int nexussivPointDamCap = 23;
   public static double nexussivChanceLe = 0.9F;
   public static double nexussivChanceLeFire = 0.1F;
   public static double nexussivFollow = 128.0;
   public static int nexussivCap = 20;
   public static int nexussivCapUpgrade = 30;
   public static int nexussivLoosingEPValue = 20000;
   public static float turretPointRed = 0.16F;
   public static int turretPointCap = 6;
   public static int turretPointDamCap = 10;
   public static double turretChanceLe = 0.85F;
   public static double turretChanceLeFire = 0.5;
   public static double turretFollow = 32.0;
   public static int turretCap = 6;
   public static float turretMinDamage = 2.0F;
   public static float turretRegen = 10.0F;
   public static int turretXPValue = 75;
   public static int turretLoosingEPValue = 1000;
   public static String[] damageTypeBlackListMob = new String[0];
   public static String[] damageTypeBlackListItem = new String[0];
   public static String[] damageTypeBlackListElse = new String[0];
   public static boolean damageTypeBlackListWhite = false;
   public static String[] damageCapBlackListMob = new String[0];
   public static String[] damageCapBlackListItem = new String[0];
   public static String[] damageCapBlackListElse = new String[0];
   public static boolean damageCapBlackListWhite = false;
   public static String[] cystItemBlackList = new String[0];
   public static int reinforcerNidusValue = 40;
   public static int reinforcerNidusMult = 3;
   public static int reinforcerNidusStart = 15;
   public static boolean canTargetBlock = true;
   public static String[] stackablePotionsLimit = new String[]{"srparasites:coth;2"};
   private static final String PROPERTIES_GENERALSIM = "parasite_properties_assimilated";
   private static final String PROPERTIES_GENERALMAR = "parasite_properties_assimara";
   private static final String PROPERTIES_GENERALFERAL = "parasite_properties_feral";
   private static final String PROPERTIES_GENERALHI = "parasite_properties_hijacked";
   private static final String PROPERTIES_GENERALPRI = "parasite_properties_primitive";
   private static final String PROPERTIES_GENERALADA = "parasite_properties_adapted";
   private static final String PROPERTIES_GENERALPUR = "parasite_properties_pure";
   private static final String PROPERTIES_GENERALPRE = "parasite_properties_preeminent";
   private static final String PROPERTIES_GENERALDER = "parasite_properties_derived";
   private static final String PROPERTIES_GENERALANC = "parasite_properties_ancient";
   private static final String PROPERTIES_GENERALNEX = "parasite_properties_nexus";
   private static final String PROPERTIES_GENERALTURR = "parasite_properties_deterrent";
   private static final String GEAR_CATEGORY = "tools";
   public static int weapon_living_durability = 1000;
   public static int weapon_livingSentient_HP_needed = 50000;
   public static int weapon_livingSentient_DAMAGE_needed = 90000;
   public static boolean armorCoth = true;
   public static int livingDura = 1000;
   public static int livingHelm = 4;
   public static int livingChest = 10;
   public static int livingLegs = 8;
   public static int livingBoots = 4;
   public static float livingToughness = 3.0F;
   public static float livingPointReduction = 0.0125F;
   public static int livingDamageCap = 4;
   public static int livingPointCap = 18;
   public static double livingChanceLe = 0.2;
   public static int sentienDura = 1500;
   public static int sentientHelm = 5;
   public static int sentientChest = 12;
   public static int sentientLegs = 10;
   public static int sentientBoots = 5;
   public static float sentientToughness = 4.0F;
   public static float sentientPointReduction = 0.018F;
   public static int sentientDamageCap = 7;
   public static int sentientPointCap = 13;
   public static double sentientChanceLe = 0.5;
   public static String[] armorDamageTypeBlackListMob = new String[0];
   public static String[] armorDamageTypeBlackListElse = new String[0];
   public static boolean armorDamageTypeBlackListWhite = false;
   public static boolean weaponCancelPacket = false;
   public static int weapon_scythe_damage = 17;
   public static float weapon_scythe_range = 4.0F;
   public static int weapon_scythe_S_damage = 34;
   public static float weapon_scythe_S_range = 5.0F;
   public static int weapon_axe_damage = 21;
   public static float weapon_axe_range = 4.0F;
   public static int weapon_axe_S_damage = 42;
   public static float weapon_axe_S_range = 5.0F;
   public static int weapon_sword_damage = 19;
   public static float weapon_sword_range = 4.5F;
   public static int weapon_sword_S_damage = 38;
   public static float weapon_sword_S_range = 6.0F;
   public static int weapon_cleaver_damage = 19;
   public static float weapon_cleaver_range = 4.5F;
   public static int weapon_cleaver_S_damage = 38;
   public static float weapon_cleaver_S_range = 6.0F;
   public static int weapon_maul_damage = 21;
   public static float weapon_maul_range = 4.0F;
   public static int weapon_maul_S_damage = 42;
   public static float weapon_maul_S_range = 5.0F;
   public static int weapon_lance_damage = 17;
   public static float weapon_lance_range = 5.0F;
   public static int weapon_lance_S_damage = 34;
   public static float weapon_lance_S_range = 7.0F;
   public static int weapon_bow_damage = 1;
   public static int weapon_bow_bonus = 1;
   public static int weapon_bow_damageCap = 2;
   public static int weapon_bow_sentient_damage = 1;
   public static int weapon_bow_sentient_bonus = 1;
   public static int weapon_bow_sentient_damageCap = 2;

   private static void initGeneralConfig(Configuration cfg) {
      cfg.addCustomCategoryComment(
         "configuration_general",
         "General configuration \nVersion:1.10.7\n\nItems IDs:\nsrparasites:ada_summoner_drop \nsrparasites:ada_yelloweye_drop \nsrparasites:ada_manducater_drop \nsrparasites:ada_reeker_drop \nsrparasites:ada_longarms_drop \nsrparasites:ada_bolster_drop \nsrparasites:ada_arachnida_drop \nsrparasites:ada_devourer_drop \nsrparasites:assimilated_flesh \nsrparasites:beckon_drop \nsrparasites:dispatcher_drop \nsrparasites:itemtab \nsrparasites:itemthrow \nsrparasites:evclock \nsrparasites:dried_tendons \nsrparasites:infectious_blade_fragment \nsrparasites:living_core \nsrparasites:vile_shell \nsrparasites:hardened_bone_handle \nsrparasites:bone \nsrparasites:lurecomponent1 \nsrparasites:lurecomponent2 \nsrparasites:lurecomponent3 \nsrparasites:lurecomponent4 \nsrparasites:lurecomponent5 \nsrparasites:lurecomponent6 \nsrparasites:itemmobspawner_dorpa \nsrparasites:itemmobspawner_infbear \nsrparasites:itemmobspawner_infhuman \nsrparasites:itemmobspawner_infhumanhead \nsrparasites:itemmobspawner_infenderman \nsrparasites:itemmobspawner_infendermanhead \nsrparasites:itemmobspawner_infcow \nsrparasites:itemmobspawner_infcowhead \nsrparasites:itemmobspawner_infsheep \nsrparasites:itemmobspawner_infsheephead \nsrparasites:itemmobspawner_infwolf \nsrparasites:itemmobspawner_infwolfhead \nsrparasites:itemmobspawner_infpig \nsrparasites:itemmobspawner_infpighead \nsrparasites:itemmobspawner_infvillager \nsrparasites:itemmobspawner_infvillagerhead \nsrparasites:itemmobspawner_inhoos \nsrparasites:itemmobspawner_inhoom \nsrparasites:itemmobspawner_infhorse \nsrparasites:itemmobspawner_infhorsehead \nsrparasites:itemmobspawner_infplayer \nsrparasites:itemmobspawner_infplayerhead \nsrparasites:itemmobspawner_infdragone \nsrparasites:itemmobspawner_infdragonehead \nsrparasites:itemmobspawner_host \nsrparasites:itemmobspawner_hostii \nsrparasites:itemmobspawner_heed \nsrparasites:itemmobspawner_cruxa \nsrparasites:itemmobspawner_mes \nsrparasites:itemmobspawner_lesh \nsrparasites:itemmobspawner_shyco \nsrparasites:itemmobspawner_canra \nsrparasites:itemmobspawner_nogla \nsrparasites:itemmobspawner_hull \nsrparasites:itemmobspawner_emana \nsrparasites:itemmobspawner_bano \nsrparasites:itemmobspawner_ranrac \nsrparasites:itemmobspawner_lum \nsrparasites:itemmobspawner_shycoadapted \nsrparasites:itemmobspawner_canraadapted \nsrparasites:itemmobspawner_noglaadapted \nsrparasites:itemmobspawner_hulladapted \nsrparasites:itemmobspawner_emanaadapted \nsrparasites:itemmobspawner_banoadapted \nsrparasites:itemmobspawner_ranracadapted \nsrparasites:itemmobspawner_lodo \nsrparasites:itemmobspawner_mudo \nsrparasites:itemmobspawner_nuuh \nsrparasites:itemmobspawner_rathol \nsrparasites:itemmobspawner_buthol \nsrparasites:itemmobspawner_venkrol \nsrparasites:itemmobspawner_venkrolsii \nsrparasites:itemmobspawner_venkrolsiii \nsrparasites:itemmobspawner_venkrolsiv \nsrparasites:itemmobspawner_tonro \nsrparasites:itemmobspawner_unvo \nsrparasites:itemmobspawner_nak \nsrparasites:itemmobspawner_dod \nsrparasites:itemmobspawner_dodsii \nsrparasites:itemmobspawner_dodsiii \nsrparasites:itemmobspawner_dodsiv \nsrparasites:itemmobspawner_alafha \nsrparasites:itemmobspawner_ganro \nsrparasites:itemmobspawner_omboo \nsrparasites:itemmobspawner_esor \nsrparasites:itemmobspawner_orch \nsrparasites:itemmobspawner_flog \nsrparasites:itemmobspawner_anged \nsrparasites:itemmobspawner_jinjo \nsrparasites:itemmobspawner_vesta \nsrparasites:itemmobspawner_pheon \nsrparasites:itemmobspawner_lencia \nsrparasites:itemmobspawner_elvia \nsrparasites:itemmobspawner_oronco \nsrparasites:itemmobspawner_terla \nsrparasites:itemmobspawner_pod \nsrparasites:weapon_scythe \nsrparasites:weapon_scythe_sentient \nsrparasites:weapon_axe \nsrparasites:weapon_axe_sentient \nsrparasites:weapon_sword \nsrparasites:weapon_sword_sentient \nsrparasites:weapon_cleaver \nsrparasites:weapon_cleaver_sentient \nsrparasites:weapon_bow \nsrparasites:weapon_bow_sentient \n "
      );
      allowMobs = cfg.getBoolean("Mobs Enabled", "configuration_general", allowMobs, "Set to false if you want to disable mobs.");
      worldGIU = cfg.getBoolean(
         "World Creation UI Enabled", "configuration_general", worldGIU, "Set to false if you want to disable SRP world creation options."
      );
      globalHealthMultiplier = cfg.getFloat(
         "Global Health Multiplier", "configuration_general", 1.0F, 0.01F, 100.0F, "Global health multiplier for all the mobs."
      );
      globalArmorMultiplier = cfg.getFloat("Global Armor Multiplier", "configuration_general", 1.0F, 0.01F, 100.0F, "Global armor multiplier for all the mobs.");
      globalDamageMultiplier = cfg.getFloat(
         "Global Damage Multiplier", "configuration_general", 1.0F, 0.01F, 100.0F, "Global damage multiplier for all the mobs."
      );
      globalKDResistanceMultiplier = cfg.getFloat(
         "Global Damage Multiplier", "configuration_general", 1.0F, 0.01F, 100.0F, "Global damage multiplier for all the mobs."
      );
      blackListedDimensions = cfg.get(
            "configuration_general",
            "Mobs Blacklisted Dimensions",
            blackListedDimensions,
            "Parasites can't spawn in these dimensions (This list is ignored if Evolution Phases are enabled, it has its own option)"
         )
         .getIntList();
      blackListedDimensionsWhite = cfg.getBoolean(
         "Mobs Blacklisted Dimensions Inverted", "configuration_general", blackListedDimensionsWhite, "Set to true if you want to use the list as a WhiteList."
      );
      entitiesWillAttack = cfg.getStringList(
         "Mobs with new target task",
         "configuration_general",
         entitiesWillAttack,
         "Mobs that will target the parasites. Ex: \"minecraft:zombie\" or just \"minecraft\" for a whole mod."
      );
      entitiesWillAttackWhite = cfg.getBoolean(
         "Mobs with new target task Inverted", "configuration_general", entitiesWillAttackWhite, "Set to true if you want to use the list as a BlackList."
      );
      entitiesWillAvoid = cfg.getStringList(
         "Mobs with new avoid task",
         "configuration_general",
         entitiesWillAvoid,
         "Mobs that will avoid the parasites. Ex: \"minecraft:zombie\" or just \"minecraft\" for a whole mod."
      );
      entitiesWillAvoidWhite = cfg.getBoolean(
         "Mobs with new avoid task Inverted", "configuration_general", entitiesWillAvoidWhite, "Set to true if you want to use the list as a BlackList."
      );
      vanillaEggs = cfg.getBoolean(
         "Vanilla Mob Eggs", "configuration_general", vanillaEggs, "Set to true if you want to active the vanilla spawn eggs for the mobs."
      );
      ignoreL = cfg.getBoolean(
         "Mobs Ignore Sun",
         "configuration_general",
         ignoreL,
         "Set to true to allow mobs to ignore sunlight when naturally spawning, this option is ignored if playing with Evolution Phases."
      );
      spawnDays = cfg.getInt("Mobs ticks required", "configuration_general", spawnDays, 0, 2147483640, "Number of ticks required for the mobs to spawn.");
      dayTickValue = cfg.getInt(
         "Day Tick Value",
         "configuration_general",
         dayTickValue,
         1,
         2147483640,
         "How long in ticks a complete day is (useful if Nodes or Colonies are activated)."
      );
      thunderEnable = cfg.getBoolean(
         "Mod LightningBolt", "configuration_general", thunderEnable, "Set to false if you want to disable the use of EntityLightningBolt by this mod."
      );
      easterEggs = cfg.getBoolean("Mod Easter Eggs", "configuration_general", easterEggs, "Set to false if you want to disable Easter Eggs.");
      blockParticleChance = cfg.getFloat(
         "Block Particle Spawn",
         "configuration_general",
         (float)blockParticleChance,
         0.0F,
         1.0F,
         "Chance to spawn (1=100%) block particles when broken by parasites."
      );
      blockDropChance = cfg.getFloat(
         "Block Drops Spawn", "configuration_general", (float)blockDropChance, 0.0F, 1.0F, "Chance to spawn (1=100%) block drops when broken by parasites."
      );
      blockSoundChance = cfg.getFloat(
         "Block Sound Play", "configuration_general", (float)blockSoundChance, 0.0F, 1.0F, "Chance to play (1=100%) block sound when broken by parasites."
      );
      parasiteNameTagChance = cfg.getFloat(
         "Mod Easter Egg (Nametags)", "configuration_general", (float)parasiteNameTagChance, 0.0F, 1.0F, "Chance to spawn an adventurer/thrall with a nametag."
      );
      mobsKilledDropLoot = cfg.getBoolean(
         "Mobs No Loot If Parasite", "configuration_general", mobsKilledDropLoot, "Set to false if you want mobs to drop loot if killed by parasites."
      );
      musicMin = cfg.getInt("Music Phase Min", "configuration_general", musicMin, 1, 2147483640, "Minimum value in ticks at which music will be played.");
      musicMax = cfg.getInt("Music Phase Max", "configuration_general", musicMax, 1, 2147483640, "Maximum value in ticks at which music will be played.");
      musicTrue = cfg.getBoolean("Music Enabled", "configuration_general", musicTrue, "Set to false if you want to disable OST.");
   }

   private static void initPropertiesConfig(Configuration cfg) {
      String listD = "\n Damage sources in Vanilla: \n inFire\n lightningBolt\n onFire\n lava\n hotFloor\n inWall\n cramming\n drown\n starve\n cactus\n fall\n flyIntoWall\n outOfWorld\n generic\n magic\n wither\n anvil\n fallingBlock\n dragonBreath\n fireworks\n player\n mob\n arrow\n indirectMagic\n thorns";
      String mobG = " Ex. \"srparasites:adapted_longarms;1;3;2\" Where: \n \"srparasites:adapted_longarms\" is the entity (note: this only works with mobs of this mod), \n \"1\" is the maximum hardness of the block it can break, \n \"3\" is the cooldown in ticks, \n \"2\" is the range. \n";
      String mobW = " Ex. \"inFire;2\" Where: \n \"inFire\" is the damage type, \n \"2\" is the multiplier. \n";
      String mobW2 = " Ex. \"1;3\" Where: \n \"1\" is the dimension, \n \"3\" is the bonus (this value will be multiplied by their chance to learn). \n";
      String mobW3 = " Ex. \"minecraft:poison;3\" Where: \n \"minecraft:poison\" is the effect, \n \"3\" is the amplifier limit. \n";
      cfg.addCustomCategoryComment("parasite_properties", "Parasite Properties");
      mobattacking = cfg.getBoolean("Mob Attacking", "parasite_properties", mobattacking, "Set to false if you want the parasites to only target the player.");
      mobattackingBlackList = cfg.getStringList(
         "Mob Attacking blacklist",
         "parasite_properties",
         mobattackingBlackList,
         "List of mobs that the parasites will no longer attack if \"Mob Attacking\" is true, Ex: \"minecraft:zombie\" or just \"minecraft\" for a whole mod"
      );
      mobattackingBlackListWhite = cfg.getBoolean(
         "Mob Attacking blacklist Inverted", "parasite_properties", mobattackingBlackListWhite, "Set to true if you want to use the list as a WhiteList."
      );
      mobAttackingFull = cfg.getBoolean(
         "Mob Attacking Friendly",
         "parasite_properties",
         mobAttackingFull,
         "Set to true if you want parasites to be fully friendly with mobs from Mob Attacking blacklist."
      );
      killcountplus = cfg.getFloat(
         "Killcount Plus",
         "parasite_properties",
         (float)killcountplus,
         0.0F,
         100.0F,
         "Each second the killcount will go up by this amount (only if difficulty is set to hard or hardcore (This is ignored if Evolution Phases are enabled, it has its own option))."
      );
      firemultyplier = cfg.getFloat("Mob Fire Damage Multiplier", "parasite_properties", firemultyplier, 1.0F, 100.0F, "Fire damage multiplier for the mobs.");
      parasiteGriefing = cfg.getStringList(
         "Mobs with new griefing task", "parasite_properties", parasiteGriefing, "Mobs that will break block in the way when chasing its target." + mobG
      );
      parasiteWeakToMobs = cfg.getStringList(
         "Damage Additional Weakness Mobs", "parasite_properties", parasiteWeakToMobs, "Additional weakness (Only Mobs) for the parasites." + mobW
      );
      parasiteWeakToItems = cfg.getStringList(
         "Damage Additional Weakness Items", "parasite_properties", parasiteWeakToItems, "Additional weakness (Only Items) for the parasites." + mobW
      );
      parasiteWeakToElse = cfg.getStringList(
         "Damage Additional Weakness Else",
         "parasite_properties",
         parasiteWeakToElse,
         "Additional weakness (Everything that is not Mob or Item) for the parasites." + mobW
      );
      parasiteGriefingBlackList = cfg.getStringList(
         "Mobs with Griefing Task Blacklist",
         "parasite_properties",
         parasiteGriefingBlackList,
         "List of block that can't be broken by the griefing task, Ex: \"minecraft:stonebrick\" or just \"minecraft\" for a whole mod"
      );
      parasiteGriefingWhite = cfg.getBoolean(
         "Mobs with Griefing Task Blacklist Inverted", "parasite_properties", parasiteGriefingWhite, "Set to true if you want to use the list as a WhiteList."
      );
      damageTypeBlackListMob = cfg.getStringList(
         "Damage Types BlackList Mobs", "parasite_properties", damageTypeBlackListMob, "Damage (Only Mobs) that cannot be learned by the parasites."
      );
      damageTypeBlackListItem = cfg.getStringList(
         "Damage Types BlackList Items", "parasite_properties", damageTypeBlackListItem, "Damage (Only Items) that cannot be learned by the parasites."
      );
      damageTypeBlackListElse = cfg.getStringList(
         "Damage Types BlackList Else",
         "parasite_properties",
         damageTypeBlackListElse,
         "Damage (Everything that is not Mob or Item) that cannot be learned by the parasites (\"inWall\" and \"outOfWorld\" will be by default)." + listD
      );
      damageTypeBlackListWhite = cfg.getBoolean(
         "Damage Types BlackList Inverted", "parasite_properties", damageTypeBlackListWhite, "Set to true if you want to use the list as a WhiteList."
      );
      tendrilHealth = cfg.getFloat("Tendril Health", "parasite_properties", (float)tendrilHealth, 0.5F, 100.0F, "Tendril health from its parent (1=100%).");
      fireTickWindow = cfg.getInt(
         "Fire Adaptation Window", "parasite_properties", fireTickWindow, 0, 1024, "Duration in ticks of the window when adaptation fail due to fire."
      );
      adaptationNewDamageCooldon = cfg.getInt(
         "Adaptation New Damage Cooldown",
         "parasite_properties",
         adaptationNewDamageCooldon,
         0,
         1024,
         "Cooldown added when learning/improving adaptation (ticks)."
      );
      damageCapBlackListMob = cfg.getStringList(
         "Damage Cap BlackList Mobs", "parasite_properties", damageCapBlackListMob, "Damage (Only Mobs) that will ignore damage cap"
      );
      damageCapBlackListItem = cfg.getStringList(
         "Damage Cap BlackList Items", "parasite_properties", damageCapBlackListItem, "Damage (Only Items) that will ignore damage cap"
      );
      damageCapBlackListElse = cfg.getStringList(
         "Damage Cap BlackList Else", "parasite_properties", damageCapBlackListElse, "Damage (Everything that is not Mob or Item) that will ignore damage cap"
      );
      damageCapBlackListWhite = cfg.getBoolean(
         "Damage Cap BlackList Inverted", "parasite_properties", damageCapBlackListWhite, "Set to true if you want to use the list as a WhiteList."
      );
      cystItemBlackList = cfg.getStringList("Cyst Item BlackList", "parasite_properties", cystItemBlackList, "Items that cannot be consumed by the cyst block");
      cystDelay = cfg.getInt("Cyst Delay", "parasite_properties", cystDelay, 0, 1024, "How many seconds does a Cyst Block have to wait before it eats items.");
      BlockInfestedLimit = cfg.getInt(
         "Block Limit For Beckon Infestation",
         "parasite_properties",
         BlockInfestedLimit,
         0,
         1024,
         "Every x number of transformed blocks there will be a cooldown."
      );
      BlockParasiteLimit = cfg.getInt(
         "Block Limit For Biome Infestation",
         "parasite_properties",
         BlockParasiteLimit,
         0,
         1024,
         "Every x number of transformed blocks there will be a cooldown."
      );
      BlockInfestedLimitCD = cfg.getInt(
         "Block Limit For Beckon Infestation Cooldown", "parasite_properties", BlockInfestedLimitCD, 0, 1024, "Cooldown value in ticks."
      );
      BlockParasiteLimitCD = cfg.getInt(
         "Block Limit For Biome Infestation Cooldown", "parasite_properties", BlockParasiteLimitCD, 0, 1024, "Cooldown value in ticks."
      );
      paraGore = cfg.getBoolean(
         "Remain Blocks", "parasite_properties", paraGore, "Set to true if you want parasites to place sometimes Remain Blocks when killed."
      );
      explotionDamPara = cfg.getBoolean(
         "Carrier Explotion", "parasite_properties", explotionDamPara, "Set to true if you want explotions from Carriers to not damage parasites."
      );
      adaptationDimStrong = cfg.getStringList("Adaptation Bonus", "parasite_properties", adaptationDimStrong, "Bonus to adaptation in dimensions." + mobW2);
      rsDespawn = cfg.getBoolean("Nexus Versions Despawn", "parasite_properties", rsDespawn, "Set to true for Nexus versions to despawn.");
      parasiteGenResidue = cfg.getBoolean(
         "Mobs Residue", "parasite_properties", parasiteGenResidue, "Set to true if you want mobs to place down residue blocks."
      );
      cystActive = cfg.getBoolean(
         "Cyst Enabled",
         "parasite_properties",
         cystActive,
         "Set to true if you want parasites to store blocks in their inventory and then place them in a cyst."
      );
      cystConsumePoint = cfg.getInt(
         "Cyst Item Value", "parasite_properties", cystConsumePoint, 0, 1024, "Amount of points (EP) generated each time a Cyst block consume an item."
      );
      convertedDespawn = cfg.getBoolean(
         "Mobs Converted Despawn", "parasite_properties", convertedDespawn, "Set to true if you want converted mobs or merged ones to despawn."
      );
      worldGnatCap = cfg.getInt(
         "Mob Gnat Cap",
         "parasite_properties",
         worldGnatCap,
         0,
         50000,
         "Gnats will not be spawned by Vermin or spawned naturally if the local number is greater than this. Natural Gnat spawns will respect the base + player caps."
      );
      worldWaterCap = cfg.getInt(
         "Mob Water Cap", "parasite_properties", worldWaterCap, 0, 500, "Water mobs will not be spawned naturally if the local number is greater than this."
      );
      worldAirCap = cfg.getInt(
         "Mob Air Cap", "parasite_properties", worldAirCap, 0, 500, "Air mobs will not be spawned naturally if the local number is greater than this."
      );
      worldMobCap = cfg.getInt(
         "Mob Cap Total", "parasite_properties", worldMobCap, 0, 50000, "Parasites will not spawn (by Scent/Effects) if local number is greater than this."
      );
      worldSpawningMobCap = cfg.getInt(
         "Mob Cap Spawning", "parasite_properties", worldSpawningMobCap, 0, 50000, "Parasites will not naturally spawn if local number is greater than this."
      );
      worldBeckonSpawnsCap = cfg.getInt(
         "Beckon Mob Spawn Caps",
         "parasite_properties",
         worldBeckonSpawnsCap,
         0,
         50000,
         "Beckons will summon biomass should the mob cap not exceed the base cap and this limit added together."
      );
      worldMobCapPlusPlayer = cfg.getInt(
         "Mob Cap Per Player", "parasite_properties", worldMobCapPlusPlayer, 0, 50000, "Each Player will increase the mob cap by this value."
      );
      doTileDrops = cfg.getBoolean("Mob doTileDrops", "parasite_properties", doTileDrops, "Set to true if the blocks broken by parasites should have drops.");
      orbFoodMult = cfg.getInt("Orb Exhaustion Multiplier", "parasite_properties", orbFoodMult, 0, 100, "Exhaustion multiplier if affected by an orb.");
      variantChance = cfg.getFloat(
         "Variant Spawn Chance", "parasite_properties", (float)variantChance, 0.0F, 1.0F, "Chance (1=100%) for parasites to spawn as a variant if available."
      );
      canTargetBlock = cfg.getBoolean(
         "Target Blocks", "parasite_properties", canTargetBlock, "Set to false if you dont want parasites to target light/circuit blocks."
      );
      canOrbAttack = cfg.getBoolean("Orb Attacking", "parasite_properties", canOrbAttack, "Set to false if you dont want the parasites to spawn orbs.");
      stackablePotionsLimit = cfg.getStringList(
         "Limit Potion Amplifiers", "parasite_properties", stackablePotionsLimit, "List of stackable effects amplifiers limits." + mobW3
      );
      incompleteCap = cfg.getInt(
         "Incomplete Cap", "parasite_properties", incompleteCap, 0, 50, "Incomplete forms will not spawn from COTH if local number is greater than this."
      );
      nexusVenkrolCap = cfg.getInt(
         "Nexus Beckon Cap",
         "parasite_properties",
         nexusVenkrolCap,
         0,
         50,
         "Beckons will not spawn from RS, Remains, Infested Blocks if the local number is greater than this."
      );
      nexusVenkrolDis = cfg.getInt(
         "Nexus Beckon Distance", "parasite_properties", nexusVenkrolDis, 0, 50, "Minimum distance Beckons need to be from each other in order to spawn*."
      );
      nexusDodCap = cfg.getInt(
         "Nexus Dispatcher Cap", "parasite_properties", nexusDodCap, 0, 50, "Dispatcher will not spawn from Nidus if the local number is greater than this."
      );
      nexusDodDis = cfg.getInt(
         "Nexus Dispatcher Distance", "parasite_properties", nexusDodDis, 0, 50, "Minimum distance Dispatcher need to be from each other in order to spawn*."
      );
      nexusLeemCap = cfg.getInt(
         "Nexus Rooter Cap",
         "parasite_properties",
         nexusLeemCap,
         0,
         50,
         "Rooters will not spawn from other Nexus versions if the local number is greater than this."
      );
      nexusLeemDis = cfg.getInt(
         "Nexus Rooter Distance", "parasite_properties", nexusLeemDis, 0, 50, "Minimum distance Rooters need to be from each other in order to spawn*."
      );
      miniminiCap = cfg.getFloat(
         "Minimum Damage Special Attack Cap",
         "parasite_properties",
         miniminiCap,
         0.0F,
         1000.0F,
         "Special attacks will not deal minimum damage if the victim's life is below this value."
      );
      nexusStructures = cfg.getBoolean(
         "Nexus Structures", "parasite_properties", nexusStructures, "Set to false if you dont want nexus versions to generate structures."
      );
   }

   private static void initPropertiesConfigSim(Configuration cfg) {
      infectedCap = cfg.getInt(
         "Version Assimilated Damage Cap",
         "parasite_properties_assimilated",
         infectedCap,
         1,
         100,
         "Minimum number of hits required to kill Assimilated versions."
      );
      infecteddespawn = cfg.getBoolean(
         "Version Assimilated Despawn", "parasite_properties_assimilated", infecteddespawn, "Set to true for Assimilated versions to despawn."
      );
      infectedAssimilation = cfg.getBoolean(
         "Version Assimilated Hiding",
         "parasite_properties_assimilated",
         infectedAssimilation,
         "Set to false if you don't want assimilated versions to hide by transforming into other mobs."
      );
      primitiveKills = cfg.getFloat(
         "Kills for Primitive Stage",
         "parasite_properties_assimilated",
         (float)primitiveKills,
         0.0F,
         100.0F,
         "How many kills Assimilated versions need for the Primitive stage."
      );
      infectedFollow = cfg.getFloat("Version Assimilated Follow Range", "parasite_properties_assimilated", (float)infectedFollow, 0.0F, 128.0F, "Follow range.");
      infectedBleedingChance = cfg.getFloat(
         "Version Assimilated Bleeding",
         "parasite_properties_assimilated",
         infectedBleedingChance,
         0.0F,
         1.0F,
         "Chance for Human/Villager/Enderman to cause bleeding in its targets (1=100%)."
      );
      infectedMinDamage = cfg.getFloat(
         "Version Assimilated Minimum Damage", "parasite_properties_assimilated", infectedMinDamage, 0.0F, 1024.0F, "Minimum Damage for Assimilated versions."
      );
      infectedOneMindDeathV = cfg.getInt(
         "Version Assimilated Scent Death Value",
         "parasite_properties_assimilated",
         infectedOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      infectedXPValue = cfg.getInt("Version Assimilated XP Value", "parasite_properties_assimilated", infectedXPValue, 1, 50000, "XP value.");
      infectedRemainValue = cfg.getInt(
         "Version Assimilated Remain Value", "parasite_properties_assimilated", infectedRemainValue, 1, 50000, "Life points required to be rebuilt."
      );
      infectedLoosingEPValue = cfg.getInt(
         "Version Assimilated Death Penalty Evolution Value",
         "parasite_properties_assimilated",
         infectedLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      infectedSneakPen = cfg.getFloat(
         "Version Assimilated Sneak Penalty",
         "parasite_properties_assimilated",
         (float)infectedSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      infectedInviPen = cfg.getFloat(
         "Version Assimilated Invisible Penalty",
         "parasite_properties_assimilated",
         infectedInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      feralKills = cfg.getFloat(
         "Kills for Feral Stage",
         "parasite_properties_assimilated",
         (float)feralKills,
         0.0F,
         100.0F,
         "How many kills Assimilated versions need for the Feral stage."
      );
   }

   private static void initPropertiesConfigMar(Configuration cfg) {
      assimaraCap = cfg.getInt(
         "Version Assimara Damage Cap", "parasite_properties_assimara", assimaraCap, 1, 100, "Minimum number of hits required to kill Assimara versions."
      );
      assimaradespawn = cfg.getBoolean(
         "Version Assimara Despawn", "parasite_properties_assimara", assimaradespawn, "Set to true for Assimara versions to despawn."
      );
      assimaraFollow = cfg.getFloat("Version Assimara Follow Range", "parasite_properties_assimara", (float)assimaraFollow, 0.0F, 128.0F, "Follow range.");
      assimaraMinDamage = cfg.getFloat(
         "Version Assimara Minimum Damage", "parasite_properties_assimara", assimaraMinDamage, 0.0F, 1024.0F, "Minimum Damage for Assimara versions."
      );
      assimaraOneMindDeathV = cfg.getInt(
         "Version Assimara Scent Death Value",
         "parasite_properties_assimara",
         assimaraOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      assimaraLoosingEPValue = cfg.getInt(
         "Version Assimara Death Penalty Evolution Value",
         "parasite_properties_assimara",
         assimaraLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      assimaraSneakPen = cfg.getFloat(
         "Version Assimara Sneak Penalty",
         "parasite_properties_assimara",
         (float)assimaraSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      assimaraInviPen = cfg.getFloat(
         "Version Assimara Invisible Penalty",
         "parasite_properties_assimara",
         assimaraInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
   }

   private static void initPropertiesConfigFeral(Configuration cfg) {
      feralCap = cfg.getInt(
         "Version Feral Damage Cap", "parasite_properties_feral", feralCap, 1, 100, "Minimum number of hits required to kill Feral versions."
      );
      feraldespawn = cfg.getBoolean("Version Feral Despawn", "parasite_properties_feral", feraldespawn, "Set to true for Feral versions to despawn.");
      feralFollow = cfg.getFloat("Version Feral Follow Range", "parasite_properties_feral", (float)feralFollow, 0.0F, 128.0F, "Follow range.");
      feralMinDamage = cfg.getFloat(
         "Version Feral Minimum Damage", "parasite_properties_feral", feralMinDamage, 0.0F, 1024.0F, "Minimum Damage for Feral versions."
      );
      feralOneMindDeathV = cfg.getInt(
         "Version Feral Scent Death Value",
         "parasite_properties_feral",
         feralOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      feralXPValue = cfg.getInt("Version Feral XP Value", "parasite_properties_feral", feralXPValue, 1, 50000, "XP value.");
      feralRemainValue = cfg.getInt(
         "Version Feral Remain Value", "parasite_properties_feral", feralRemainValue, 1, 50000, "Life points required to be rebuilt."
      );
      feralLoosingEPValue = cfg.getInt(
         "Version Feral Death Penalty Evolution Value",
         "parasite_properties_feral",
         feralLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      feralSneakPen = cfg.getFloat(
         "Version Feral Sneak Penalty",
         "parasite_properties_feral",
         (float)feralSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      feralInviPen = cfg.getFloat(
         "Version Feral Invisible Penalty",
         "parasite_properties_feral",
         feralInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      feralFoodSteal = cfg.getFloat(
         "Version Feral Exhaustion", "parasite_properties_feral", feralFoodSteal, 0.0F, 1.0F, "Will increase exhaustion level by this value when it hits you."
      );
      feralRegen = cfg.getFloat(
         "Version Feral Regen", "parasite_properties_feral", feralRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      feralMult = cfg.getFloat(
         "Version Feral Remain Spawn", "parasite_properties_feral", (float)feralMult, 0.0F, 1.0F, "Chance to spawn remains when receiving damage"
      );
   }

   private static void initPropertiesConfigHi(Configuration cfg) {
      hijackedCap = cfg.getInt(
         "Version Hijacked Damage Cap", "parasite_properties_hijacked", hijackedCap, 1, 100, "Minimum number of hits required to kill Hijacked versions."
      );
      hijackeddespawn = cfg.getBoolean(
         "Version Hijacked Despawn", "parasite_properties_hijacked", hijackeddespawn, "Set to true for Hijacked versions to despawn."
      );
      hijackedFollow = cfg.getFloat("Version Hijacked Follow Range", "parasite_properties_hijacked", (float)hijackedFollow, 0.0F, 128.0F, "Follow range.");
      hijackedMinDamage = cfg.getFloat(
         "Version Hijacked Minimum Damage", "parasite_properties_hijacked", hijackedMinDamage, 0.0F, 1024.0F, "Minimum Damage for Hijacked versions."
      );
      hijackedOneMindDeathV = cfg.getInt(
         "Version Hijacked Scent Death Value",
         "parasite_properties_hijacked",
         hijackedOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      hijackedXPValue = cfg.getInt("Version Hijacked XP Value", "parasite_properties_hijacked", hijackedXPValue, 1, 50000, "XP value.");
      hijackedLoosingEPValue = cfg.getInt(
         "Version Hijacked Death Penalty Evolution Value",
         "parasite_properties_hijacked",
         hijackedLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      hijackedSneakPen = cfg.getFloat(
         "Version Hijacked Sneak Penalty",
         "parasite_properties_hijacked",
         (float)hijackedSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      hijackedInviPen = cfg.getFloat(
         "Version Hijacked Invisible Penalty",
         "parasite_properties_hijacked",
         hijackedInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
   }

   private static void initPropertiesConfigPri(Configuration cfg) {
      primitivePointRed = cfg.getFloat(
         "Version Primitive Point Reduction Value",
         "parasite_properties_primitive",
         primitivePointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      primitivePointCap = cfg.getInt(
         "Version Primitive Point Reduction Cap", "parasite_properties_primitive", primitivePointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      primitivePointDamCap = cfg.getInt(
         "Version Primitive Point Reduction Total Cap",
         "parasite_properties_primitive",
         primitivePointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      primitiveChanceLe = cfg.getFloat(
         "Version Primitive Point Chance to Learn",
         "parasite_properties_primitive",
         (float)primitiveChanceLe,
         0.0F,
         1.0F,
         "Chance to adapt (1=100%) to the damage."
      );
      primitiveChanceLeFire = cfg.getFloat(
         "Version Primitive Point Chance to Fail",
         "parasite_properties_primitive",
         (float)primitiveChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      primitiveCap = cfg.getInt(
         "Version Primitive Damage Cap", "parasite_properties_primitive", primitiveCap, 1, 100, "Minimum number of hits required to kill Primitive versions."
      );
      primitivedespawn = cfg.getBoolean(
         "Version Primitive Despawn", "parasite_properties_primitive", primitivedespawn, "Set to true for primitive versions to despawn."
      );
      adaptedKills = cfg.getFloat(
         "Kills for Adapted Stage",
         "parasite_properties_primitive",
         (float)adaptedKills,
         0.0F,
         100.0F,
         "How many kills Primitive versions need for the Adapted stage."
      );
      primitiveFollow = cfg.getFloat("Version Primitive Follow Range", "parasite_properties_primitive", (float)primitiveFollow, 0.0F, 128.0F, "Follow range.");
      primitiveMinDamage = cfg.getFloat(
         "Version Primitive Minimum Damage", "parasite_properties_primitive", primitiveMinDamage, 0.0F, 1024.0F, "Minimum Damage for Primitive versions."
      );
      primitiveRegen = cfg.getFloat(
         "Version Primitive Regen", "parasite_properties_primitive", primitiveRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      primitiveOneMindDeathV = cfg.getInt(
         "Version Primitive Scent Death Value",
         "parasite_properties_primitive",
         primitiveOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      primitiveXPValue = cfg.getInt("Version Primitive XP Value", "parasite_properties_primitive", primitiveXPValue, 1, 50000, "XP value.");
      primitiveRemainValue = cfg.getInt(
         "Version Primitive Remain Value", "parasite_properties_primitive", primitiveRemainValue, 1, 50000, "Life points required to be rebuilt."
      );
      primitiveLoosingEPValue = cfg.getInt(
         "Version Primitive Death Penalty Evolution Value",
         "parasite_properties_primitive",
         primitiveLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      primitiveFoodSteal = cfg.getFloat(
         "Version Primitive Exhaustion",
         "parasite_properties_primitive",
         primitiveFoodSteal,
         0.0F,
         100.0F,
         "Will increase exhaustion level by this value when it hits you."
      );
      primitiveItemOrbCooldown = cfg.getInt(
         "Version Primitive Orb Item Cooldown",
         "parasite_properties_primitive",
         primitiveItemOrbCooldown,
         0,
         1000000000,
         "Cooldown (seconds) added to items by their orb."
      );
      primitiveFoodChance = cfg.getFloat(
         "Version Primitive Food Item Chance",
         "parasite_properties_primitive",
         (float)primitiveFoodChance,
         0.0F,
         1.0F,
         "Chance (1=100%) of contaminating food when hitting you."
      );
      primitiveFoodAmount = cfg.getInt(
         "Version Primitive Food Item Amount",
         "parasite_properties_primitive",
         primitiveFoodAmount,
         0,
         1000000000,
         "Maximum amount of food to be contaminated."
      );
      primitiveSneakPen = cfg.getFloat(
         "Version Primitive Sneak Penalty",
         "parasite_properties_primitive",
         (float)primitiveSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      primitiveInviPen = cfg.getFloat(
         "Version Primitive Invisible Penalty",
         "parasite_properties_primitive",
         primitiveInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      primitiveWalls = cfg.getBoolean(
         "Version Primitive Can't See Through Walls",
         "parasite_properties_primitive",
         primitiveWalls,
         "Set to false if you want the view check to be performed when searching for enemies."
      );
   }

   private static void initPropertiesConfigAda(Configuration cfg) {
      adaptedPointRed = cfg.getFloat(
         "Version Adapted Point Reduction Value",
         "parasite_properties_adapted",
         adaptedPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      adaptedPointCap = cfg.getInt(
         "Version Adapted Point Reduction Cap", "parasite_properties_adapted", adaptedPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      adaptedPointDamCap = cfg.getInt(
         "Version Adapted Point Reduction Total Cap",
         "parasite_properties_adapted",
         adaptedPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      adaptedChanceLe = cfg.getFloat(
         "Version Adapted Point Chance to Learn", "parasite_properties_adapted", (float)adaptedChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      adaptedChanceLeFire = cfg.getFloat(
         "Version Adapted Point Chance to Fail",
         "parasite_properties_adapted",
         (float)adaptedChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      adaptedCap = cfg.getInt(
         "Version Adapted Damage Cap", "parasite_properties_adapted", adaptedCap, 1, 100, "Minimum number of hits required to kill Adapted versions."
      );
      adapteddespawn = cfg.getBoolean("Version Adapted Despawn", "parasite_properties_adapted", adapteddespawn, "Set to true for adapted versions to despawn.");
      adaptedFollow = cfg.getFloat("Version Adapted Follow Range", "parasite_properties_adapted", (float)adaptedFollow, 0.0F, 128.0F, "Follow range.");
      adaptedMinDamage = cfg.getFloat(
         "Version Adapted Minimum Damage", "parasite_properties_adapted", adaptedMinDamage, 0.0F, 1024.0F, "Minimum Damage for Adapted versions."
      );
      adaptedRegen = cfg.getFloat(
         "Version Adapted Regen", "parasite_properties_adapted", adaptedRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      adaptedOneMindDeathV = cfg.getInt(
         "Version Adapted Scent Death Value",
         "parasite_properties_adapted",
         adaptedOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      adaptedXPValue = cfg.getInt("Version Adapted XP Value", "parasite_properties_adapted", adaptedXPValue, 1, 50000, "XP value.");
      adaptedRemainValue = cfg.getInt(
         "Version Adapted Remain Value", "parasite_properties_adapted", adaptedRemainValue, 1, 50000, "Life points required to be rebuilt."
      );
      adaptedLoosingEPValue = cfg.getInt(
         "Version Adapted Death Penalty Evolution Value",
         "parasite_properties_adapted",
         adaptedLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      adaptedFoodSteal = cfg.getFloat(
         "Version Adapted Exhaustion",
         "parasite_properties_adapted",
         adaptedFoodSteal,
         0.0F,
         100.0F,
         "Will increase exhaustion level by this value when it hits you."
      );
      adaptedItemOrbCooldown = cfg.getInt(
         "Version Adapted Orb Item Cooldown",
         "parasite_properties_adapted",
         adaptedItemOrbCooldown,
         0,
         1000000000,
         "Cooldown (seconds) added to items by their orb."
      );
      adaptedFoodChance = cfg.getFloat(
         "Version Adapted Food Item Chance",
         "parasite_properties_adapted",
         (float)adaptedFoodChance,
         0.0F,
         1.0F,
         "Chance (1=100%) of contaminating food when hitting you."
      );
      adaptedFoodAmount = cfg.getInt(
         "Version Adapted Food Item Amount", "parasite_properties_adapted", adaptedFoodAmount, 0, 1000000000, "Maximum amount of food to be contaminated."
      );
      adaptedSneakPen = cfg.getFloat(
         "Version Adapted Sneak Penalty",
         "parasite_properties_adapted",
         (float)adaptedSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      adaptedInviPen = cfg.getFloat(
         "Version Adapted Invisible Penalty",
         "parasite_properties_adapted",
         adaptedInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      adaptedWalls = cfg.getBoolean(
         "Version Adapted Can't See Through Walls",
         "parasite_properties_adapted",
         adaptedWalls,
         "Set to false if you want the view check to be performed when searching for enemies."
      );
   }

   private static void initPropertiesConfigPure(Configuration cfg) {
      purePointRed = cfg.getFloat(
         "Version Pure Point Reduction Value", "parasite_properties_pure", purePointRed, 0.0F, 100.0F, "Value for each point, used to reduce damage taken."
      );
      purePointCap = cfg.getInt(
         "Version Pure Point Reduction Cap", "parasite_properties_pure", purePointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      purePointDamCap = cfg.getInt(
         "Version Pure Point Reduction Total Cap",
         "parasite_properties_pure",
         purePointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      pureChanceLe = cfg.getFloat(
         "Version Pure Point Chance to Learn", "parasite_properties_pure", (float)pureChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      pureChanceLeFire = cfg.getFloat(
         "Version Pure Point Chance to Fail",
         "parasite_properties_pure",
         (float)pureChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      pureCap = cfg.getInt("Version Pure Damage Cap", "parasite_properties_pure", pureCap, 1, 100, "Minimum number of hits required to kill Pure versions.");
      puredespawn = cfg.getBoolean("Version Pure Despawn", "parasite_properties_pure", puredespawn, "Set to true for pure mobs to despawn.");
      pureFollow = cfg.getFloat("Version Pure Follow Range", "parasite_properties_pure", (float)pureFollow, 0.0F, 128.0F, "Follow range.");
      pureMinDamage = cfg.getFloat("Version Pure Minimum Damage", "parasite_properties_pure", pureMinDamage, 0.0F, 1024.0F, "Minimum Damage for Pure versions.");
      pureRegen = cfg.getFloat("Version Pure Regen", "parasite_properties_pure", pureRegen, 0.0F, 1024.0F, "How much they will heal each second if available.");
      pureOneMindDeathV = cfg.getInt(
         "Version Pure Scent Death Value",
         "parasite_properties_pure",
         pureOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      pureXPValue = cfg.getInt("Version Pure XP Value", "parasite_properties_pure", pureXPValue, 1, 50000, "XP value.");
      pureRemainValue = cfg.getInt("Version Pure Remain Value", "parasite_properties_pure", pureRemainValue, 1, 50000, "Life points required to be rebuilt.");
      pureLoosingEPValue = cfg.getInt(
         "Version Pure Death Penalty Evolution Value",
         "parasite_properties_pure",
         pureLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      pureFoodSteal = cfg.getFloat(
         "Version Pure Exhaustion", "parasite_properties_pure", pureFoodSteal, 0.0F, 100.0F, "Will increase exhaustion level by this value when it hits you."
      );
      pureExpSteal = cfg.getInt("Version Pure Orb Xp Steal", "parasite_properties_pure", pureExpSteal, 1, 50000, "Amount of xp it will steal.");
      pureItemOrbCooldown = cfg.getInt(
         "Version Pure Orb Item Cooldown", "parasite_properties_pure", pureItemOrbCooldown, 0, 1000000000, "Cooldown (seconds) added to items by their orb."
      );
      pureFoodChance = cfg.getFloat(
         "Version Pure Food Item Chance",
         "parasite_properties_pure",
         (float)pureFoodChance,
         0.0F,
         1.0F,
         "Chance (1=100%) of contaminating food when hitting you."
      );
      pureFoodAmount = cfg.getInt(
         "Version Pure Food Item Amount", "parasite_properties_pure", pureFoodAmount, 0, 1000000000, "Maximum amount of food to be contaminated."
      );
      pureSeiZ = cfg.getInt("Version Pure Seizer", "parasite_properties_pure", pureSeiZ, 0, 10, "Number of Seizers it will spawn in combat.");
      pureSneakPen = cfg.getFloat(
         "Version Pure Sneak Penalty",
         "parasite_properties_pure",
         (float)pureSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      pureInviPen = cfg.getFloat(
         "Version Pure Invisible Penalty",
         "parasite_properties_pure",
         pureInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      pureWalls = cfg.getBoolean(
         "Version Pure Can't See Through Walls",
         "parasite_properties_pure",
         pureWalls,
         "Set to false if you want the view check to be performed when searching for enemies."
      );
   }

   private static void initPropertiesConfigPreeminent(Configuration cfg) {
      preeminentPointRed = cfg.getFloat(
         "Version Preeminent Point Reduction Value",
         "parasite_properties_preeminent",
         preeminentPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      preeminentPointCap = cfg.getInt(
         "Version Preeminent Point Reduction Cap", "parasite_properties_preeminent", preeminentPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      preeminentPointDamCap = cfg.getInt(
         "Version Preeminent Point Reduction Total Cap",
         "parasite_properties_preeminent",
         preeminentPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      preeminentChanceLe = cfg.getFloat(
         "Version Preeminent Point Chance to Learn",
         "parasite_properties_preeminent",
         (float)preeminentChanceLe,
         0.0F,
         1.0F,
         "Chance to adapt (1=100%) to the damage."
      );
      preeminentChanceLeFire = cfg.getFloat(
         "Version Preeminent Point Chance to Fail",
         "parasite_properties_preeminent",
         (float)preeminentChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      preeminentCap = cfg.getInt(
         "Version Preeminent Damage Cap",
         "parasite_properties_preeminent",
         preeminentCap,
         1,
         100,
         "Minimum number of hits required to kill Preeminent versions."
      );
      preeminentdespawn = cfg.getBoolean(
         "Version Preeminent Despawn", "parasite_properties_preeminent", preeminentdespawn, "Set to true for preeminent mobs to despawn."
      );
      preeminentFollow = cfg.getFloat(
         "Version Preeminent Follow Range", "parasite_properties_preeminent", (float)preeminentFollow, 0.0F, 128.0F, "Follow range."
      );
      preeminentMinDamage = cfg.getFloat(
         "Version Preeminent Minimum Damage", "parasite_properties_preeminent", preeminentMinDamage, 0.0F, 1024.0F, "Minimum Damage for Preeminent versions."
      );
      preeminentRegen = cfg.getFloat(
         "Version Preeminent Regen", "parasite_properties_preeminent", preeminentRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      preeminentOneMindDeathV = cfg.getInt(
         "Version Preeminent Scent Death Value",
         "parasite_properties_preeminent",
         preeminentOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      preeminentXPValue = cfg.getInt("Version Preeminent XP Value", "parasite_properties_preeminent", preeminentXPValue, 1, 50000, "XP value.");
      preeminentRemainValue = cfg.getInt(
         "Version Preeminent Remain Value", "parasite_properties_preeminent", preeminentRemainValue, 1, 50000, "Life points required to be rebuilt."
      );
      preeminentLoosingEPValue = cfg.getInt(
         "Version Preeminent Death Penalty Evolution Value",
         "parasite_properties_preeminent",
         preeminentLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      preeminentFoodSteal = cfg.getFloat(
         "Version Preeminent Exhaustion",
         "parasite_properties_preeminent",
         preeminentFoodSteal,
         0.0F,
         100.0F,
         "Will increase exhaustion level by this value when it hits you."
      );
      preeminentExpSteal = cfg.getInt(
         "Version Preeminent Orb Xp Steal", "parasite_properties_preeminent", preeminentExpSteal, 1, 50000, "Amount of xp it will steal."
      );
      preeminentItemOrbCooldown = cfg.getInt(
         "Version Preeminent Orb Item Cooldown",
         "parasite_properties_preeminent",
         preeminentItemOrbCooldown,
         0,
         1000000000,
         "Cooldown (seconds) added to items by their orb."
      );
      preeminentFoodChance = cfg.getFloat(
         "Version Preeminent Food Item Chance",
         "parasite_properties_preeminent",
         (float)preeminentFoodChance,
         0.0F,
         1.0F,
         "Chance (1=100%) of contaminating food when hitting you."
      );
      preeminentFoodAmount = cfg.getInt(
         "Version Preeminent Food Item Amount",
         "parasite_properties_preeminent",
         preeminentFoodAmount,
         0,
         1000000000,
         "Maximum amount of food to be contaminated."
      );
      preeminentSneakPen = cfg.getFloat(
         "Version Preeminent Sneak Penalty",
         "parasite_properties_preeminent",
         (float)preeminentSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      preeminentInviPen = cfg.getFloat(
         "Version Preeminent Invisible Penalty",
         "parasite_properties_preeminent",
         preeminentInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      preeminentWalls = cfg.getBoolean(
         "Version Preeminent Can't See Through Walls",
         "parasite_properties_preeminent",
         preeminentWalls,
         "Set to false if you want the view check to be performed when searching for enemies."
      );
   }

   private static void initPropertiesConfigDer(Configuration cfg) {
      derivedPointRed = cfg.getFloat(
         "Version Derived Point Reduction Value",
         "parasite_properties_derived",
         derivedPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      derivedPointCap = cfg.getInt(
         "Version Derived Point Reduction Cap", "parasite_properties_derived", derivedPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      derivedPointDamCap = cfg.getInt(
         "Version Derived Point Reduction Total Cap",
         "parasite_properties_derived",
         derivedPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      derivedChanceLe = cfg.getFloat(
         "Version Derived Point Chance to Learn", "parasite_properties_derived", (float)derivedChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      derivedChanceLeFire = cfg.getFloat(
         "Version Derived Point Chance to Fail",
         "parasite_properties_derived",
         (float)derivedChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      derivedCap = cfg.getInt(
         "Version Derived Damage Cap", "parasite_properties_derived", derivedCap, 1, 100, "Minimum number of hits required to kill Derived versions."
      );
      deriveddespawn = cfg.getBoolean("Version Derived Despawn", "parasite_properties_derived", deriveddespawn, "Set to true for derived versions to despawn.");
      derivedFollow = cfg.getFloat("Version Derived Follow Range", "parasite_properties_derived", (float)derivedFollow, 0.0F, 128.0F, "Follow range.");
      derivedMinDamage = cfg.getFloat(
         "Version Derived Minimum Damage", "parasite_properties_derived", derivedMinDamage, 0.0F, 1024.0F, "Minimum Damage for Derived versions."
      );
      derivedRegen = cfg.getFloat(
         "Version Derived Regen", "parasite_properties_derived", derivedRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      derivedOneMindDeathV = cfg.getInt(
         "Version Derived Scent Death Value",
         "parasite_properties_derived",
         derivedOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      derivedXPValue = cfg.getInt("Version Derived XP Value", "parasite_properties_derived", derivedXPValue, 1, 50000, "XP value.");
      derivedRemainValue = cfg.getInt(
         "Version Derived Remain Value", "parasite_properties_derived", derivedRemainValue, 1, 50000, "Life points required to be rebuilt."
      );
      derivedLoosingEPValue = cfg.getInt(
         "Version Derived Death Penalty Evolution Value",
         "parasite_properties_derived",
         derivedLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      derivedFoodSteal = cfg.getFloat(
         "Version Derived Exhaustion",
         "parasite_properties_derived",
         derivedFoodSteal,
         0.0F,
         100.0F,
         "Will increase exhaustion level by this value when it hits you."
      );
      derivedItemOrbCooldown = cfg.getInt(
         "Version Derived Orb Item Cooldown",
         "parasite_properties_derived",
         derivedItemOrbCooldown,
         0,
         1000000000,
         "Cooldown (seconds) added to items by their orb."
      );
      derivedFoodChance = cfg.getFloat(
         "Version Derived Food Item Chance",
         "parasite_properties_derived",
         (float)derivedFoodChance,
         0.0F,
         1.0F,
         "Chance (1=100%) of contaminating food when hitting you."
      );
      derivedFoodAmount = cfg.getInt(
         "Version Derived Food Item Amount", "parasite_properties_derived", derivedFoodAmount, 0, 1000000000, "Maximum amount of food to be contaminated."
      );
      derivedSneakPen = cfg.getFloat(
         "Version Derived Sneak Penalty",
         "parasite_properties_derived",
         (float)derivedSneakPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is sneaking, the lower the value the higher the chance of not being seen, vanilla value is 0.800000011920929."
      );
      derivedInviPen = cfg.getFloat(
         "Version Derived Invisible Penalty",
         "parasite_properties_derived",
         derivedInviPen,
         0.0F,
         1.0F,
         "Penalty for parasites when someone is invisible, the lower the value the higher the chance of not being seen, vanilla value is 0.7."
      );
      derivedWalls = cfg.getBoolean(
         "Version Derived Can't See Through Walls",
         "parasite_properties_derived",
         derivedWalls,
         "Set to false if you want the view check to be performed when searching for enemies."
      );
      derivedHackHealing = cfg.getFloat(
         "Version Derived NeuroLock Healing",
         "parasite_properties_derived",
         derivedHackHealing,
         0.0F,
         1.0F,
         "Each positive effect will heal this by % amount, multiplied by amplifier -ticks:potioneffect:startingamplifier-"
      );
      derivedHackingEffects = cfg.getStringList(
         "Version Derived NeuroLock Effects", "parasite_properties_derived", derivedHackingEffects, "List of effects what will apply to its target"
      );
   }

   private static void initPropertiesConfigAnc(Configuration cfg) {
      ancientPointRed = cfg.getFloat(
         "Version Ancient Point Reduction Value",
         "parasite_properties_ancient",
         ancientPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      ancientPointCap = cfg.getInt(
         "Version Ancient Point Reduction Cap", "parasite_properties_ancient", ancientPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      ancientPointDamCap = cfg.getInt(
         "Version Ancient Point Reduction Total Cap",
         "parasite_properties_ancient",
         ancientPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      ancientChanceLe = cfg.getFloat(
         "Version Ancient Point Chance to Learn", "parasite_properties_ancient", (float)ancientChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      ancientChanceLeFire = cfg.getFloat(
         "Version Ancient Point Chance to Fail",
         "parasite_properties_ancient",
         (float)ancientChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      ancientCap = cfg.getInt(
         "Version Ancient Damage Cap", "parasite_properties_ancient", ancientCap, 1, 100, "Minimum number of hits required to kill Ancient versions."
      );
      ancientdespawn = cfg.getBoolean("Version Ancient Despawn", "parasite_properties_ancient", ancientdespawn, "Set to true for ancient mobs to despawn.");
      ancientFollow = cfg.getFloat("Version Ancient Follow Range", "parasite_properties_ancient", (float)ancientFollow, 0.0F, 128.0F, "Follow range.");
      ancientMinDamage = cfg.getFloat(
         "Version Ancient Minimum Damage", "parasite_properties_ancient", ancientMinDamage, 0.0F, 1024.0F, "Minimum Damage for Ancient versions."
      );
      ancientRegen = cfg.getFloat(
         "Version Ancient Regen", "parasite_properties_ancient", ancientRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      ancientOneMindDeathV = cfg.getInt(
         "Version Ancient Scent Death Value",
         "parasite_properties_ancient",
         ancientOneMindDeathV,
         1,
         1000,
         "Death value set in EntityParasiticScent, used if parasite_collective_consciousness is enabled."
      );
      ancientXPValue = cfg.getInt("Version Ancient XP Value", "parasite_properties_ancient", ancientXPValue, 1, 50000, "XP value.");
      ancientLoosingEPValue = cfg.getInt(
         "Version Ancient Death Penalty Evolution Value",
         "parasite_properties_ancient",
         ancientLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
   }

   private static void initPropertiesConfigNex(Configuration cfg) {
      nexussiPointRed = cfg.getFloat(
         "Nexus Stage I Point Reduction Value",
         "parasite_properties_nexus",
         nexussiPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      nexussiPointCap = cfg.getInt(
         "Nexus Stage I Point Reduction Cap", "parasite_properties_nexus", nexussiPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      nexussiPointDamCap = cfg.getInt(
         "Nexus Stage I Point Reduction Total Cap",
         "parasite_properties_nexus",
         nexussiPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      nexussiChanceLe = cfg.getFloat(
         "Nexus Stage I Point Chance to Learn", "parasite_properties_nexus", (float)nexussiChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      nexussiChanceLeFire = cfg.getFloat(
         "Nexus Stage I Point Chance to Fail",
         "parasite_properties_nexus",
         (float)nexussiChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      nexussiCap = cfg.getInt(
         "Nexus Stage I Damage Cap", "parasite_properties_nexus", nexussiCap, 1, 100, "Minimum number of hits required to kill a Nexus Stage I."
      );
      nexussiFollow = cfg.getFloat("Nexus Stage I Follow Range", "parasite_properties_nexus", (float)nexussiFollow, 0.0F, 128.0F, "Follow range.");
      nexusMinGrowTime = cfg.getInt(
         "Nexus Stage I Min GrowTime",
         "parasite_properties_nexus",
         nexusMinGrowTime,
         1,
         10000,
         "Minimum Growtime (in seconds) from Nexus Stage I to Nexus Stage I."
      );
      nexusMaxGrowTime = cfg.getInt(
         "Nexus Stage I Max GrowTime",
         "parasite_properties_nexus",
         nexusMaxGrowTime,
         1,
         10000,
         "Maximum Growtime (in seconds) from Nexus Stage I to Nexus Stage II (max must be greater than min)."
      );
      nexussiLoosingEPValue = cfg.getInt(
         "Nexus Stage I Death Penalty Evolution Value",
         "parasite_properties_nexus",
         nexussiLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      reinforcerNidusValue = cfg.getInt(
         "Nexus Block Dispatcher",
         "parasite_properties_nexus",
         reinforcerNidusValue,
         1,
         10000,
         "Killcount required for a Dispatcher Nidus Block to spawn a Dispatcher Stage I."
      );
      reinforcerNidusMult = cfg.getInt(
         "Nexus Block Bonus",
         "parasite_properties_nexus",
         reinforcerNidusMult,
         1,
         10000,
         "If Dispatcher Nidus block cannot spawn a Dispatcher, it will multiply collected killcount by this amount."
      );
      reinforcerNidusStart = cfg.getInt(
         "Nexus Block Start", "parasite_properties_nexus", reinforcerNidusStart, 1, 10000, "Collective killcount required to place a Dispatcher Nidus Block."
      );
      nexussiiPointRed = cfg.getFloat(
         "Nexus Stage II Point Reduction Value",
         "parasite_properties_nexus",
         nexussiiPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      nexussiiPointCap = cfg.getInt(
         "Nexus Stage II Point Reduction Cap", "parasite_properties_nexus", nexussiiPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      nexussiiPointDamCap = cfg.getInt(
         "Nexus Stage II Point Reduction Total Cap",
         "parasite_properties_nexus",
         nexussiiPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      nexussiiChanceLe = cfg.getFloat(
         "Nexus Stage II Point Chance to Learn", "parasite_properties_nexus", (float)nexussiiChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      nexussiiChanceLeFire = cfg.getFloat(
         "Nexus Stage II Point Chance to Fail",
         "parasite_properties_nexus",
         (float)nexussiiChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      nexussiiCap = cfg.getInt(
         "Nexus Stage II Damage Cap", "parasite_properties_nexus", nexussiiCap, 1, 100, "Minimum number of hits required to kill a Nexus Stage II."
      );
      nexussiiFollow = cfg.getFloat("Nexus Stage II Follow Range", "parasite_properties_nexus", (float)nexussiiFollow, 0.0F, 128.0F, "Follow range.");
      nexussiiMinGrowTime = cfg.getInt(
         "Nexus Stage II Min GrowTime",
         "parasite_properties_nexus",
         nexussiiMinGrowTime,
         1,
         10000,
         "Minimum Growtime (in seconds) from Nexus Stage I to Nexus Stage II."
      );
      nexussiiMaxGrowTime = cfg.getInt(
         "Nexus Stage II Max GrowTime",
         "parasite_properties_nexus",
         nexussiiMaxGrowTime,
         1,
         10000,
         "Maximum Growtime (in seconds) from Nexus Stage I to Nexus Stage III (max must be greater than min)."
      );
      nexussiiLoosingEPValue = cfg.getInt(
         "Nexus Stage II Death Penalty Evolution Value",
         "parasite_properties_nexus",
         nexussiiLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      nexussiiiPointRed = cfg.getFloat(
         "Nexus Stage III Point Reduction Value",
         "parasite_properties_nexus",
         nexussiiiPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      nexussiiiPointCap = cfg.getInt(
         "Nexus Stage III Point Reduction Cap", "parasite_properties_nexus", nexussiiiPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      nexussiiiPointDamCap = cfg.getInt(
         "Nexus Stage III Point Reduction Total Cap",
         "parasite_properties_nexus",
         nexussiiiPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      nexussiiiChanceLe = cfg.getFloat(
         "Nexus Stage III Point Chance to Learn", "parasite_properties_nexus", (float)nexussiiiChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      nexussiiiChanceLeFire = cfg.getFloat(
         "Nexus Stage III Point Chance to Fail",
         "parasite_properties_nexus",
         (float)nexussiiiChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      nexussiiiCap = cfg.getInt(
         "Nexus Stage III Damage Cap", "parasite_properties_nexus", nexussiiiCap, 1, 100, "Minimum number of hits required to kill a Nexus Stage III."
      );
      nexussiiiFollow = cfg.getFloat("Nexus Stage III Follow Range", "parasite_properties_nexus", (float)nexussiiiFollow, 0.0F, 128.0F, "Follow range.");
      nexussiiiMinGrowTime = cfg.getInt(
         "Nexus Stage III Min GrowTime",
         "parasite_properties_nexus",
         nexussiiiMinGrowTime,
         1,
         10000,
         "Minimum Growtime (in seconds) from Nexus Stage I to Nexus Stage III."
      );
      nexussiiiMaxGrowTime = cfg.getInt(
         "Nexus Stage III Max GrowTime",
         "parasite_properties_nexus",
         nexussiiiMaxGrowTime,
         1,
         10000,
         "Maximum Growtime (in seconds) from Nexus Stage I to Nexus Stage IV (max must be greater than min)."
      );
      nexussiiiLoosingEPValue = cfg.getInt(
         "Nexus Stage III Death Penalty Evolution Value",
         "parasite_properties_nexus",
         nexussiiiLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
      nexussivPointRed = cfg.getFloat(
         "Nexus Stage IV Point Reduction Value",
         "parasite_properties_nexus",
         nexussivPointRed,
         0.0F,
         100.0F,
         "Value for each point, used to reduce damage taken."
      );
      nexussivPointCap = cfg.getInt(
         "Nexus Stage IV Point Reduction Cap", "parasite_properties_nexus", nexussivPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      nexussivPointDamCap = cfg.getInt(
         "Nexus Stage IV Point Reduction Total Cap",
         "parasite_properties_nexus",
         nexussivPointDamCap,
         0,
         1000,
         "How many damage types the version can learn and adapt."
      );
      nexussivChanceLe = cfg.getFloat(
         "Nexus Stage IV Point Chance to Learn", "parasite_properties_nexus", (float)nexussivChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      nexussivChanceLeFire = cfg.getFloat(
         "Nexus Stage IV Point Chance to Fail",
         "parasite_properties_nexus",
         (float)nexussivChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      nexussivCap = cfg.getInt(
         "Nexus Stage IV Damage Cap", "parasite_properties_nexus", nexussivCap, 1, 100, "Minimum number of hits required to kill a Nexus Stage IV."
      );
      nexussivFollow = cfg.getFloat("Nexus Stage IV Follow Range", "parasite_properties_nexus", (float)nexussivFollow, 0.0F, 128.0F, "Follow range.");
      nexussivCapUpgrade = cfg.getInt(
         "Nexus Stage IV Cap Upgrade",
         "parasite_properties_nexus",
         nexussivCapUpgrade,
         1,
         100,
         "If parasites exceed this number, the Nexus will not upgrade them."
      );
      nexussivLoosingEPValue = cfg.getInt(
         "Nexus Stage IV Death Penalty Evolution Value",
         "parasite_properties_nexus",
         nexussivLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
   }

   private static void initPropertiesConfigDet(Configuration cfg) {
      turretPointRed = cfg.getFloat(
         "Deterrent Point Reduction Value", "parasite_properties_deterrent", turretPointRed, 0.0F, 100.0F, "Value for each point, used to reduce damage taken."
      );
      turretPointCap = cfg.getInt(
         "Deterrent Point Reduction Cap", "parasite_properties_deterrent", turretPointCap, 0, 100, "Cap of points used to reduce damage taken."
      );
      turretPointDamCap = cfg.getInt(
         "Deterrent Point Reduction Total Cap",
         "parasite_properties_deterrent",
         turretPointDamCap,
         0,
         10,
         "How many damage types the version can learn and adapt."
      );
      turretChanceLe = cfg.getFloat(
         "Deterrent Point Chance to Learn", "parasite_properties_deterrent", (float)turretChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      turretChanceLeFire = cfg.getFloat(
         "Deterrent Point Chance to Fail",
         "parasite_properties_deterrent",
         (float)turretChanceLeFire,
         0.0F,
         1.0F,
         "Chance to fail adaptation if its on fire (1=100%)."
      );
      turretCap = cfg.getInt("Deterrent Damage Cap", "parasite_properties_deterrent", turretCap, 1, 100, "Minimum number of hits required to kill a Deterrent.");
      turretFollow = cfg.getFloat("Deterrent Follow Range", "parasite_properties_deterrent", (float)turretFollow, 0.0F, 128.0F, "Follow range.");
      turretMinDamage = cfg.getFloat(
         "Deterrent Minimum Damage", "parasite_properties_deterrent", turretMinDamage, 0.0F, 1024.0F, "Minimum Damage for Deterrent versions."
      );
      turretRegen = cfg.getFloat(
         "Version Deterrent Regen", "parasite_properties_deterrent", turretRegen, 0.0F, 1024.0F, "How much they will heal each second if available."
      );
      turretXPValue = cfg.getInt("Version Deterrent XP Value", "parasite_properties_deterrent", turretXPValue, 1, 50000, "XP value.");
      turretLoosingEPValue = cfg.getInt(
         "Version Deterrent Death Penalty Evolution Value",
         "parasite_properties_deterrent",
         turretLoosingEPValue,
         0,
         1000000000,
         "How many points parasites will lose when it is killed."
      );
   }

   private static void initgearConfig(Configuration cfg) {
      String listD = "\n Damage sources in Vanilla: \n inFire\n lightningBolt\n onFire\n lava\n hotFloor\n inWall\n cramming\n drown\n starve\n cactus\n fall\n flyIntoWall\n outOfWorld\n generic\n magic\n wither\n anvil\n fallingBlock\n dragonBreath\n fireworks\n player\n mob\n arrow\n indirectMagic\n thorns";
      cfg.addCustomCategoryComment("tools", "Tools");
      weapon_living_durability = cfg.getInt("Living Weapons Durability", "tools", weapon_living_durability, 0, 10000, "Durability for the Living Weapons.");
      weapon_livingSentient_HP_needed = cfg.getInt(
         "Living Weapons HP Evolve",
         "tools",
         weapon_livingSentient_HP_needed,
         0,
         2000000000,
         "Value needed in order for Living weapons to evolve into Sentient Weapons."
      );
      weapon_livingSentient_DAMAGE_needed = cfg.getInt(
         "Living Armor DAMAGE Evolve",
         "tools",
         weapon_livingSentient_DAMAGE_needed,
         0,
         2000000000,
         "Value needed in order for Living armor to evolve into Sentient Armor."
      );
      weapon_scythe_damage = cfg.getInt("Scythe Living Damage", "tools", weapon_scythe_damage, 0, 1024, "Damage for the Living Scythe.");
      weapon_scythe_range = cfg.getFloat("Scythe Living Range", "tools", weapon_scythe_range, 0.0F, 100.0F, "Range for the Living Scythe.");
      weapon_scythe_S_damage = cfg.getInt("Scythe Sentient Damage", "tools", weapon_scythe_S_damage, 0, 1024, "Damage for the Sentient Scythe.");
      weapon_scythe_S_range = cfg.getFloat("Scythe Sentient Range", "tools", weapon_scythe_S_range, 0.0F, 100.0F, "Range for the Sentient Scythe.");
      weapon_axe_damage = cfg.getInt("Axe Living Damage", "tools", weapon_axe_damage, 0, 1024, "Damage for the Living Axe.");
      weapon_axe_range = cfg.getFloat("Axe Living Range", "tools", weapon_axe_range, 0.0F, 100.0F, "Range for the Living Axe.");
      weapon_axe_S_damage = cfg.getInt("Axe Sentient Damage", "tools", weapon_axe_S_damage, 0, 1024, "Damage for the Sentient Axe.");
      weapon_axe_S_range = cfg.getFloat("Axe Sentient Range", "tools", weapon_axe_S_range, 0.0F, 100.0F, "Range for the Sentient Axe.");
      weapon_sword_damage = cfg.getInt("Sword Living Damage", "tools", weapon_sword_damage, 0, 1024, "Damage for the Living Sword.");
      weapon_sword_range = cfg.getFloat("Sword Living Range", "tools", weapon_sword_range, 0.0F, 100.0F, "Range for the Living Sword.");
      weapon_sword_S_damage = cfg.getInt("Sword Sentient Damage", "tools", weapon_sword_S_damage, 0, 1024, "Damage for the Sentient Sword.");
      weapon_sword_S_range = cfg.getFloat("Sword Sentient Range", "tools", weapon_sword_S_range, 0.0F, 100.0F, "Range for the Sentient Sword.");
      weapon_cleaver_damage = cfg.getInt("Cleaver Living Damage", "tools", weapon_cleaver_damage, 0, 1024, "Damage for the Living Cleaver.");
      weapon_cleaver_range = cfg.getFloat("Cleaver Living Range", "tools", weapon_cleaver_range, 0.0F, 100.0F, "Range for the Living Cleaver.");
      weapon_cleaver_S_damage = cfg.getInt("Cleaver Sentient Damage", "tools", weapon_cleaver_S_damage, 0, 1024, "Damage for the Sentient Cleaver.");
      weapon_cleaver_S_range = cfg.getFloat("Cleaver Sentient Range", "tools", weapon_cleaver_S_range, 0.0F, 100.0F, "Range for the Sentient Cleaver.");
      weapon_maul_damage = cfg.getInt("Maul Living Damage", "tools", weapon_maul_damage, 0, 1024, "Damage for the Living Maul.");
      weapon_maul_range = cfg.getFloat("Maul Living Range", "tools", weapon_maul_range, 0.0F, 100.0F, "Range for the Living Maul.");
      weapon_maul_S_damage = cfg.getInt("Maul Sentient Damage", "tools", weapon_maul_S_damage, 0, 1024, "Damage for the Sentient Maul.");
      weapon_maul_S_range = cfg.getFloat("Maul Sentient Range", "tools", weapon_maul_S_range, 0.0F, 100.0F, "Range for the Sentient Maul.");
      weapon_lance_damage = cfg.getInt("Lance Living Damage", "tools", weapon_lance_damage, 0, 1024, "Damage for the Living Lance.");
      weapon_lance_range = cfg.getFloat("Lance Living Range", "tools", weapon_lance_range, 0.0F, 100.0F, "Range for the Living Lance.");
      weapon_lance_S_damage = cfg.getInt("Lance Sentient Damage", "tools", weapon_lance_S_damage, 0, 1024, "Damage for the Sentient Lance.");
      weapon_lance_S_range = cfg.getFloat("Lance Sentient Range", "tools", weapon_lance_S_range, 0.0F, 100.0F, "Range for the Sentient Lance.");
      weapon_bow_damage = cfg.getInt("Greatbow Damage", "tools", weapon_bow_damage, 0, 1024, "Damage for the Living Greatbow.");
      weapon_bow_bonus = cfg.getInt("Greatbow Bonus", "tools", weapon_bow_bonus, 0, 1024, "Damage multiplier per second (holding) for the Living Greatbow.");
      weapon_bow_damageCap = cfg.getInt("Greatbow Damage Cap", "tools", weapon_bow_damageCap, 0, 1024, "Damage multiplier cap for the Living Greatbow.");
      weapon_bow_sentient_damage = cfg.getInt("Greatbow Sentient Damage", "tools", weapon_bow_sentient_damage, 0, 1024, "Damage for the Sentient Greatbow.");
      weapon_bow_sentient_bonus = cfg.getInt(
         "Greatbow Sentient Bonus", "tools", weapon_bow_sentient_bonus, 0, 1024, "Damage multiplier per second (holding) for the Sentient Greatbow."
      );
      weapon_bow_sentient_damageCap = cfg.getInt(
         "Greatbow Sentient Damage Cap", "tools", weapon_bow_sentient_damageCap, 0, 1024, "Damage multiplier cap for the Sentient Greatbow."
      );
      armorCoth = cfg.getBoolean("Armor COTH", "tools", armorCoth, "Set to true for the armor to apply COTH to the user.");
      armorDamageTypeBlackListMob = cfg.getStringList(
         "Armor Damage Types BlackList Mobs", "tools", armorDamageTypeBlackListMob, "Damage (Only Mobs) that cannot be learned by the armor." + listD
      );
      armorDamageTypeBlackListElse = cfg.getStringList(
         "Armor Damage Types BlackList Else",
         "tools",
         armorDamageTypeBlackListElse,
         "Damage (Everything that is not Mob or Item) that cannot be learned by the armor." + listD
      );
      armorDamageTypeBlackListWhite = cfg.getBoolean(
         "Armor Damage Types BlackList Inverted", "tools", armorDamageTypeBlackListWhite, "Set to true if you want to use the list as a WhiteList."
      );
      weaponCancelPacket = cfg.getBoolean(
         "Weapon Packet Cancel",
         "tools",
         weaponCancelPacket,
         "Set to true if you want to cancel packets from the weapons, instead will use reach Attribute from the Player."
      );
      livingBoots = cfg.getInt("Living Armor Boots", "tools", livingBoots, 0, 1024, "Armor value for Living Boots.");
      livingLegs = cfg.getInt("Living Armor Pants", "tools", livingLegs, 0, 1024, "Armor value for Living Pants.");
      livingChest = cfg.getInt("Living Armor Chest", "tools", livingChest, 0, 1024, "Armor value for Living Chest.");
      livingHelm = cfg.getInt("Living Armor Helm", "tools", livingHelm, 0, 1024, "Armor value for Living Helm.");
      livingToughness = cfg.getFloat("Living Armor Toughness", "tools", livingToughness, 0.0F, 1000.0F, "Toughness value for Living Armor.");
      livingPointCap = cfg.getInt("Living Armor Point Cap", "tools", livingPointCap, 0, 1024, "Cap of points used to reduce damage taken.");
      livingDamageCap = cfg.getInt("Living Armor Damage Type Cap", "tools", livingDamageCap, 0, 1024, "How many damage types the version can learn and adapt.");
      livingPointReduction = cfg.getFloat(
         "Living Armor Point Reduction Value", "tools", livingPointReduction, 0.0F, 1000.0F, "Value for each point, used to reduce damage taken."
      );
      livingChanceLe = cfg.getFloat("Living Armor Point Chance to Learn", "tools", (float)livingChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage.");
      livingDura = cfg.getInt("Living Armor Durability", "tools", livingDura, 0, 100000, "Armor Durability for Living set.");
      sentientBoots = cfg.getInt("Sentient Armor Boots", "tools", sentientBoots, 0, 1024, "Armor value for Sentient Boots.");
      sentientLegs = cfg.getInt("Sentient Armor Pants", "tools", sentientLegs, 0, 1024, "Armor value for Sentient Pants.");
      sentientChest = cfg.getInt("Sentient Armor Chest", "tools", sentientChest, 0, 1024, "Armor value for Sentient Chest.");
      sentientHelm = cfg.getInt("Sentient Armor Helm", "tools", sentientHelm, 0, 1024, "Armor value for Sentient Helm.");
      sentientToughness = cfg.getFloat("Sentient Armor Toughness", "tools", sentientToughness, 0.0F, 1000.0F, "Toughness value for Sentient Armor.");
      sentientPointCap = cfg.getInt("Sentient Armor Point Cap", "tools", sentientPointCap, 0, 1024, "Cap of points used to reduce damage taken.");
      sentientDamageCap = cfg.getInt(
         "Sentient Armor Damage Type Cap", "tools", sentientDamageCap, 0, 1024, "How many damage types the version can learn and adapt."
      );
      sentientPointReduction = cfg.getFloat(
         "Sentient Armor Point Reduction Value", "tools", sentientPointReduction, 0.0F, 1000.0F, "Value for each point, used to reduce damage taken."
      );
      sentientChanceLe = cfg.getFloat(
         "Sentient Armor Point Chance to Learn", "tools", (float)sentientChanceLe, 0.0F, 1.0F, "Chance to adapt (1=100%) to the damage."
      );
      sentienDura = cfg.getInt("Sentient Armor Durability", "tools", sentienDura, 0, 100000, "Armor Durability for Sentient set.");
   }

   public static void initConfig(FMLPreInitializationEvent e) {
      File directory = e.getModConfigurationDirectory();
      CommonProxy.config = new Configuration(new File(directory.getPath(), "srparasites/SRParasites.cfg"));
      readConfig();
   }

   public static boolean readConfig() {
      Configuration cfg = CommonProxy.config;

      try {
         cfg.load();
         initGeneralConfig(cfg);
         initPropertiesConfig(cfg);
         initPropertiesConfigSim(cfg);
         initPropertiesConfigMar(cfg);
         initPropertiesConfigFeral(cfg);
         initPropertiesConfigHi(cfg);
         initPropertiesConfigPri(cfg);
         initPropertiesConfigAda(cfg);
         initPropertiesConfigPure(cfg);
         initPropertiesConfigPreeminent(cfg);
         initPropertiesConfigDer(cfg);
         initPropertiesConfigAnc(cfg);
         initPropertiesConfigNex(cfg);
         initPropertiesConfigDet(cfg);
         initgearConfig(cfg);
         return true;
      } catch (Exception var5) {
         SRPMain.logger.log(Level.ERROR, "Problem loading configuration file", var5);
      } finally {
         if (cfg.hasChanged()) {
            cfg.save();
         }
      }

      return false;
   }
}
