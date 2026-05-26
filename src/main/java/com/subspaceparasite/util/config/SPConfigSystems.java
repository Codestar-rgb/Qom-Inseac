/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.Level
 */
package com.subspaceparasite.util.config;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.proxy.CommonProxy;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

public class SPConfigSystems {
    private static final String CATEGORY_GENERAL_SISTEMS = "configuration_systems";
    private static final String REINFORCEMENT_CATEGORY = "reinforcement_system";
    public static double hijackedArmorFireMult = 5.5;
    public static int relayScannerCooldownSeconds = 20;
    public static boolean relayScannerDebugGlow = false;
    public static boolean debugSpawner = false;
    public static boolean debugEvolutionPointsCsv = false;
    public static boolean debugEvolutionPointsConsole = true;
    public static boolean debugEvolutionPointsCallerTrace = false;
    public static boolean guiDistortionEnabled = true;
    public static boolean guiDistortionAffectsWorldText = false;
    public static boolean guiDistortionAffectsItemTooltips = true;
    public static boolean guiDistortionAffectsChat = true;
    public static boolean guiDistortionAffectsSigns = true;
    public static boolean guiDistortionAffectsPotionHud = true;
    public static boolean guiDistortionAffectsItemHighlight = true;
    public static boolean guiDistortionAffectsSubtitles = true;
    public static int guiDistortionRange = 100;
    public static String[] guiDistortionMobList = new String[]{"subspaceparasite:kirin", "subspaceparasite:draconite"};
    public static boolean rsEnabled = true;
    public static boolean rsSounds = true;
    public static float rschance = 0.07f;
    public static boolean rsBlockI = true;
    public static float rsBlockIMaxH = 4.5f;
    public static boolean rsPlayer = false;
    public static int rsBlockLight = 16;
    public static int rsCooldown = 2;
    public static int rsVenkrolChance = 5000;
    public static boolean rsIgnoreCooldownAtSpawn = true;
    public static String optionalBlockDirt = "minecraft:gravel:0";
    public static String optionalBlockRubble = "minecraft:mossy_cobblestone:0";
    public static String[] blockBList = new String[0];
    public static boolean blockBListWhite = false;
    public static float rsBlockParticleS = 0.05f;
    public static float rsBlockParticleF = 0.05f;
    public static String[] maximumStageList = new String[]{"1;3"};
    public static boolean rsSky = false;
    public static boolean rsSkyResidue = false;
    public static int rsResidueY = 0;
    public static double rsVenkrolEmpty = 0.05;
    public static int rsBlockRevertStage = 1;
    private static final String MERGE_CATEGORY = "merge_System";
    public static boolean mergeRandom = true;
    public static double mergeHealth = 0.5;
    public static String[] mergeMobTable = new String[]{"subspaceparasite:pri_summoner;0", "subspaceparasite:pri_longarms;0", "subspaceparasite:pri_reeker;0", "subspaceparasite:pri_manducater;0", "subspaceparasite:pri_bolster;0", "subspaceparasite:pri_yelloweye;0", "subspaceparasite:pri_arachnida;0", "subspaceparasite:pri_vermin;0", "subspaceparasite:pri_tozoon;0"};
    public static String[] mergeInfValues = new String[]{"subspaceparasite:sim_human;1", "subspaceparasite:sim_cow;1", "subspaceparasite:sim_sheep;1", "subspaceparasite:sim_wolf;1", "subspaceparasite:sim_pig;1", "subspaceparasite:sim_villager;1", "subspaceparasite:sim_enderman;1", "subspaceparasite:sim_bear;2", "subspaceparasite:sim_horse;1"};
    private static final String STATUSEFFECTS_CATEGORY = "status_effects";
    public static boolean fearActive = true;
    public static boolean fearUnfair = true;
    public static float fearBlockChance = 0.0f;
    public static float fearItemChance = 0.0f;
    public static float fearInvChance = 0.0f;
    public static String[] fearItemBlackList = new String[0];
    public static boolean fearItemBlackListWhite = false;
    public static float fearFallDamage = 1.5f;
    public static float fearAirDamage = 1.5f;
    public static boolean cothActive = true;
    public static boolean cothPlayer = true;
    public static boolean cothLootDisable = true;
    public static int cothAura = 3;
    public static float cothConvert = 0.3f;
    public static float cothInfected = 0.1f;
    public static float cothHijacked = 0.05f;
    public static float cothFeral = 0.2f;
    public static float cothCrude = 0.4f;
    public static float cothPrimitive = 0.5f;
    public static float cothAdapted = 0.6f;
    public static float cothPure = 0.8f;
    public static float cothUnhide = 0.3f;
    public static boolean COTHPopping = false;
    public static String[] COTHVictimParasite = new String[]{"minecraft:pig;subspaceparasite:sim_pig", "minecraft:sheep;subspaceparasite:sim_sheep", "minecraft:cow;subspaceparasite:sim_cow", "minecraft:wolf;subspaceparasite:sim_wolf", "minecraft:horse;subspaceparasite:sim_horse", "minecraft:zombie;subspaceparasite:sim_human", "minecraft:husk;subspaceparasite:sim_human", "minecraft:zombie_villager;subspaceparasite:sim_villager", "minecraft:villager;subspaceparasite:sim_villager", "minecraft:polar_bear;subspaceparasite:sim_bear", "minecraft:enderman;subspaceparasite:sim_enderman", "minecraft:squid;subspaceparasite:sim_squid", "wyrmsofnyrus:creepedhumanoid;subspaceparasite:sim_human", "wyrmsofnyrus:creepedbiter;subspaceparasite:sim_cow", "wyrmsofnyrus:crawler;subspaceparasite:sim_bigspider", "wyrmsofnyrus:minos;subspaceparasite:sim_cow", "wyrmsofnyrus:prime;subspaceparasite:crux"};
    public static float hijackHealth = 0.5f;
    public static String[] HIJACKVictimParasite = new String[]{"minecraft:villager_golem;subspaceparasite:hi_golem", "minecraft:blaze;subspaceparasite:hi_blaze", "minecraft:skeleton;subspaceparasite:hi_skeleton"};
    public static String[] COTHItemPrevent = new String[]{"minecraft:golden_apple;0.1;300", "minecraft:golden_carrot;0.03;150"};
    public static String[] COTHImmuneList = new String[]{"minecraft:villager_golem", "minecraft:vex", "minecraft:creeper", "minecraft:slime", "minecraft:blaze", "minecraft:guardian", "minecraft:elder_guardian", "minecraft:stray", "minecraft:skeleton", "minecraft:skeleton_horse", "minecraft:wither_skeleton", "minecraft:magma_cube", "minecraft:ghast", "minecraft:shulker", "minecraft:snowman", "wyrmsofnyrus", "srrevenants"};
    public static boolean COTHImmuneListWhite = false;
    public static float bleedingDamage = 0.06f;
    public static float bleedingDamageCap = 100.0f;
    public static int corroValue = 3;
    public static double corrNot = 0.1;
    public static boolean viralEnable = true;
    public static float viralAmount = 0.5f;
    public static boolean rageEnable = true;
    public static double rageDamage = 0.1;
    public static double rageSpeed = 0.1;
    public static float needlerDamage = 0.4f;
    public static int needlerTerminal = 7;
    public static float needlerMaxDamPlayer = 1.0E9f;
    public static float needlerMaxDamMonster = 1.0E9f;
    public static String[] needlerImmuneList = new String[0];
    public static boolean needlerImmuneListWhite = false;
    public static float pivotDamageRHost = 0.2375f;
    public static float pivotDamageRNotHost = 0.05f;
    public static int pivotPointMultiplier = 2;
    public static float parasiteKillingReduction = 0.15f;
    public static double adapsChance = 0.2;
    public static double parateMuch = 0.5;
    public static float muscleoutDamageOut = 0.09f;
    private static final String EVOLUTION_CATEGORY = "parasite_evolution_phases";
    public static boolean useEvolution = true;
    public static boolean damageStationaryRS = true;
    public static byte evolutionNodeUnlock = (byte)11;
    public static byte evolutionColonyUnlock = (byte)11;
    public static boolean evolutionNoPlayerMultipler = false;
    public static int evolutionPointCap = 10;
    public static byte evolutionCothStopLoot = (byte)2;
    public static byte evolutionSleepDenied = (byte)6;
    public static byte evolutionAssimilatedDehiding = (byte)9;
    public static byte evolutionMudoAttack = (byte)4;
    public static byte evolutionBeckonIgnoreCooldown = (byte)5;
    public static byte evolutionSpawningIgnoreSunlight = 1;
    public static byte evolutionStopFishing = (byte)6;
    public static byte evolutionOneMind = (byte)11;
    public static byte evolutionTotalKill = (byte)9;
    public static byte evolutionFeralNoSim = (byte)7;
    public static byte evolutionNests = (byte)11;
    public static byte evolutionDislodgment = (byte)11;
    public static byte evolutionParasiteAlwaysVariant = (byte)11;
    public static byte evolutionHives = (byte)11;
    public static byte evolutionNoParasiteHealing = (byte)9;
    public static float evolutionNoParasiteHealingValue = 0.5f;
    public static byte evolutionPArasitesWithoutXP = (byte)8;
    public static byte evolutionNoParasiteSpawnDenied = (byte)10;
    public static byte evolutionParasiteStatIncrease = (byte)10;
    public static float evolutionParasiteStatIncreaseValue = 0.07f;
    public static int valueKill = 1;
    public static int valueCOTH = 6;
    public static int valueBlock = 6;
    public static int valueMerge = 9;
    public static int valueEvolutionDespawn = 100;
    public static int valueLossBlockStain = 2;
    public static int valueLossBlockTrunk = 3;
    public static int valueLossBlockRubble = 4;
    public static String[] evolutionDimStart = new String[]{"-1;-1;50", "0;0;0", "1;-1;100", "270;-1;300"};
    public static boolean evolutionDimGainInverted = false;
    public static int[] evolutionDimGain = new int[0];
    public static boolean evolutionDimLossInverted = false;
    public static int[] evolutionDimLoss = new int[0];
    public static String[] evolutionParasiteLock = new String[]{"1;3;64", "-1;7;309", "1;7;67", "1;3;59", "1;4;94"};
    public static String evolutionParasiteLockMessage = "...";
    public static int[] blackListedDimensionsEPP = new int[]{0, 1, -1};
    public static int phaseOriginMinusOne = 1;
    public static boolean phaseLightlessMinusOne = true;
    public static double phaseOriginMinusOnePenalty = 0.01;
    public static int phaseVectorMultBonusMinusOne = 10;
    public static String[] phaseSpawnEntryMinusne = new String[]{"subspaceparasite:rupter;3;6;30", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:host;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:pri_devourer;1;2;1", "subspaceparasite:pri_longarms;2;3;5", "subspaceparasite:pri_manducater;2;3;5", "subspaceparasite:pri_reeker;2;3;5", "subspaceparasite:pri_yelloweye;2;3;5", "subspaceparasite:pri_summoner;2;3;5", "subspaceparasite:pri_bolster;2;3;5", "subspaceparasite:pri_arachnida;2;3;5", "subspaceparasite:thrall;3;5;25"};
    public static int phaseOriginZero = 1;
    public static int phaseVectorMultBonusZero = 1;
    public static int phaseVectorPointCapZero = 100;
    public static String phaseWarningZero = "ZERO";
    public static int sleepPenaltyZero = 3;
    public static byte phaseMaxParasiteIDZero = (byte)5;
    public static byte phaseCancelParasiteIDZero = 0;
    public static int phaseScentBonusZero = -200;
    public static byte phaseScentReactionZero = (byte)11;
    public static String[] phaseSpawnEntryZero = new String[]{"subspaceparasite:buglin;2;6;30"};
    public static int phaseOriginOne = 2;
    public static double phaseOriginBonusHealthOne = 0.01;
    public static double phaseOriginBonusSizeOne = 0.005;
    public static byte[] disloPhaseOne = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationOne = 1.0;
    public static double phaseDisloCooldownOne = 4.0;
    public static double phaseDisloPointCostOne = 1.0;
    public static double phaseDisloMoreValueOne = 1.0;
    public static int oneLevelDeploy = 1;
    public static int phaseVectorMultBonusOne = 3;
    public static int phaseKillsOne = 800;
    public static int phaseVectorPointCapOne = 200;
    public static byte phaseMaxParasiteIDOne = (byte)11;
    public static byte phaseCancelParasiteIDOne = 0;
    public static double phaseKillCountPlusOne = 0.0;
    public static double reinforcementSystemChanceOne = 0.0;
    public static double beckonStageIGrowPenaltyOne = 1.0;
    public static double beckonStageIIGrowPenaltyOne = 1.0;
    public static double beckonStageIIIGrowPenaltyOne = 1.0;
    public static double mobSpawningCOTHChanceOne = 0.0;
    public static double cropGrowStunnedOne = 0.0;
    public static int luredValueOne = 10;
    public static int luredValueOneCool = 10;
    public static String phaseWarningOne = "One";
    public static int sleepPenaltyOne = 10;
    public static int phaseScentBonusOne = 5;
    public static byte phaseScentReactionOne = (byte)10;
    public static int phaseDelayTicksOne = 4000;
    public static int phaseResidueOne = 0;
    public static String[] phaseSpawnEntryOne = new String[]{"subspaceparasite:buglin;2;6;30", "subspaceparasite:rupter;3;6;30", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginTwo = 2;
    public static double phaseOriginBonusHealthTwo = 0.02;
    public static double phaseOriginBonusSizeTwo = 0.01;
    public static byte[] disloPhaseTwo = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationTwo = 2.0;
    public static double phaseDisloCooldownTwo = 3.0;
    public static double phaseDisloPointCostTwo = 3.0;
    public static double phaseDisloMoreValueTwo = 2.0;
    public static int twoLevelDeploy = 1;
    public static int phaseVectorMultBonusTwo = 5;
    public static int phaseKillsTwo = 1600;
    public static int phaseVectorPointCapTwo = 600;
    public static byte phaseMaxParasiteIDTwo = (byte)31;
    public static byte phaseCancelParasiteIDTwo = 0;
    public static double phaseKillCountPlusTwo = 0.0;
    public static double reinforcementSystemChanceTwo = 0.0;
    public static double beckonStageIGrowPenaltyTwo = 1.0;
    public static double beckonStageIIGrowPenaltyTwo = 1.0;
    public static double beckonStageIIIGrowPenaltyTwo = 1.0;
    public static double mobSpawningCOTHChanceTwo = 0.0;
    public static double cropGrowStunnedTwo = 0.0;
    public static int luredValueTwo = 20;
    public static int luredValueTwoCool = 20;
    public static String phaseWarningTwo = "Two";
    public static int sleepPenaltyTwo = 25;
    public static int phaseScentBonusTwo = 20;
    public static byte phaseScentReactionTwo = (byte)9;
    public static int phaseDelayTicksTwo = 4800;
    public static int phaseResidueTwo = 0;
    public static String[] phaseSpawnEntryTwo = new String[]{"subspaceparasite:buglin;2;6;30", "subspaceparasite:rupter;3;6;30", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginThree = 3;
    public static double phaseOriginBonusHealthThree = 0.05;
    public static double phaseOriginBonusSizeThree = 0.02;
    public static byte[] disloPhaseThree = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationThree = 3.0;
    public static double phaseDisloCooldownThree = 3.0;
    public static double phaseDisloPointCostThree = 6.0;
    public static double phaseDisloMoreValueThree = 3.0;
    public static int threeLevelDeploy = 1;
    public static int phaseVectorMultBonusThree = 10;
    public static int phaseKillsThree = 5000;
    public static int phaseVectorPointCapThree = 3500;
    public static byte phaseMaxParasiteIDThree = (byte)31;
    public static byte phaseCancelParasiteIDThree = 0;
    public static double phaseKillCountPlusThree = 0.05;
    public static double reinforcementSystemChanceThree = 0.04;
    public static double beckonStageIGrowPenaltyThree = 0.95;
    public static double beckonStageIIGrowPenaltyThree = 0.95;
    public static double beckonStageIIIGrowPenaltyThree = 0.95;
    public static double mobSpawningCOTHChanceThree = 0.0;
    public static double cropGrowStunnedThree = 0.0;
    public static int luredValueThree = 50;
    public static int luredValueThreeCool = 50;
    public static String phaseWarningThree = "Three";
    public static int sleepPenaltyThree = 50;
    public static int phaseScentBonusThree = 50;
    public static byte phaseScentReactionThree = (byte)9;
    public static int phaseDelayTicksThree = 4700;
    public static int phaseResidueThree = 5500;
    public static String[] phaseSpawnEntryThree = new String[]{"subspaceparasite:rupter;3;6;30", "subspaceparasite:lice;1;4;20", "subspaceparasite:carrier_flying;1;2;15", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:host;1;2;5", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginFour = 3;
    public static double phaseOriginBonusHealthFour = 0.1;
    public static double phaseOriginBonusSizeFour = 0.05;
    public static byte[] disloPhaseFour = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationFour = 4.0;
    public static double phaseDisloCooldownFour = 4.0;
    public static double phaseDisloPointCostFour = 8.0;
    public static double phaseDisloMoreValueFour = 4.0;
    public static int fourLevelDeploy = 2;
    public static int phaseVectorMultBonusFour = 40;
    public static int phaseKillsFour = 30000;
    public static int phaseVectorPointCapFour = 25000;
    public static byte phaseMaxParasiteIDFour = (byte)41;
    public static byte phaseCancelParasiteIDFour = (byte)4;
    public static double phaseKillCountPlusFour = 0.075;
    public static double reinforcementSystemChanceFour = 0.06;
    public static double beckonStageIGrowPenaltyFour = 0.0;
    public static double beckonStageIIGrowPenaltyFour = 0.95;
    public static double beckonStageIIIGrowPenaltyFour = 0.95;
    public static double mobSpawningCOTHChanceFour = 0.0;
    public static double cropGrowStunnedFour = 0.0;
    public static int luredValueFour = 250;
    public static int luredValueFourCool = 500;
    public static String phaseWarningFour = "Four";
    public static int sleepPenaltyFour = 100;
    public static int phaseScentBonusFour = 90;
    public static byte phaseScentReactionFour = (byte)8;
    public static int phaseDelayTicksFour = 4500;
    public static int phaseResidueFour = 4000;
    public static String[] phaseSpawnEntryFour = new String[]{"subspaceparasite:rupter;3;6;30", "subspaceparasite:lice;1;4;20", "subspaceparasite:carrier_flying;1;2;15", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:host;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginFive = 4;
    public static double phaseOriginBonusHealthFive = 0.2;
    public static double phaseOriginBonusSizeFive = 0.1;
    public static byte[] disloPhaseFive = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationFive = 5.0;
    public static double phaseDisloCooldownFive = 5.0;
    public static double phaseDisloPointCostFive = 10.0;
    public static double phaseDisloMoreValueFive = 5.0;
    public static int fiveLevelDeploy = 3;
    public static int phaseVectorMultBonusFive = 100;
    public static int phaseKillsFive = 200000;
    public static int phaseVectorPointCapFive = 500000;
    public static byte phaseMaxParasiteIDFive = (byte)41;
    public static byte phaseCancelParasiteIDFive = (byte)4;
    public static double phaseKillCountPlusFive = 0.1;
    public static double reinforcementSystemChanceFive = 0.08;
    public static double beckonStageIGrowPenaltyFive = 0.0;
    public static double beckonStageIIGrowPenaltyFive = 0.0;
    public static double beckonStageIIIGrowPenaltyFive = 0.95;
    public static double mobSpawningCOTHChanceFive = 0.0;
    public static double cropGrowStunnedFive = 0.0;
    public static int luredValueFive = 300;
    public static int luredValueFiveCool = 4000;
    public static String phaseWarningFive = "Five";
    public static int sleepPenaltyFive = 2500;
    public static int phaseScentBonusFive = 150;
    public static byte phaseScentReactionFive = (byte)6;
    public static int phaseDelayTicksFive = 4200;
    public static int phaseResidueFive = 1000;
    public static String[] phaseSpawnEntryFive = new String[]{"subspaceparasite:rupter;3;6;30", "subspaceparasite:lice;1;4;20", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:host;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:crux;1;2;5", "subspaceparasite:dredge;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginSix = 4;
    public static double phaseOriginBonusHealthSix = 0.3;
    public static double phaseOriginBonusSizeSix = 0.2;
    public static byte[] disloPhaseSix = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationSix = 6.0;
    public static double phaseDisloCooldownSix = 6.0;
    public static double phaseDisloPointCostSix = 13.0;
    public static double phaseDisloMoreValueSix = 6.0;
    public static int sixLevelDeploy = 4;
    public static int phaseVectorMultBonusSix = 350;
    public static int phaseKillsSix = 5000000;
    public static int phaseVectorPointCapSix = 3000000;
    public static byte phaseMaxParasiteIDSix = (byte)51;
    public static byte phaseCancelParasiteIDSix = (byte)30;
    public static double phaseKillCountPlusSix = 0.15;
    public static double reinforcementSystemChanceSix = 0.1;
    public static double beckonStageIGrowPenaltySix = 0.0;
    public static double beckonStageIIGrowPenaltySix = 0.0;
    public static double beckonStageIIIGrowPenaltySix = 0.0;
    public static double mobSpawningCOTHChanceSix = 0.2;
    public static double cropGrowStunnedSix = 0.1;
    public static int luredValueSix = 600;
    public static int luredValueSixCool = 80000;
    public static String phaseWarningSix = "Six";
    public static int sleepPenaltySix = 8500;
    public static int phaseScentBonusSix = 240;
    public static byte phaseScentReactionSix = (byte)5;
    public static int phaseDelayTicksSix = 3800;
    public static int phaseResidueSix = 500;
    public static String[] phaseSpawnEntrySix = new String[]{"subspaceparasite:rupter;3;6;30", "subspaceparasite:lice;1;4;20", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:host;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:crux;1;2;5", "subspaceparasite:dredge;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:abo_bodies;1;2;5", "subspaceparasite:mangler;3;6;30", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginSeven = 4;
    public static double phaseOriginBonusHealthSeven = 0.4;
    public static double phaseOriginBonusSizeSeven = 0.3;
    public static byte[] disloPhaseSeven = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationSeven = 7.0;
    public static double phaseDisloCooldownSeven = 7.0;
    public static double phaseDisloPointCostSeven = 17.0;
    public static double phaseDisloMoreValueSeven = 7.0;
    public static int sevenLevelDeploy = 5;
    public static int phaseVectorMultBonusSeven = 900;
    public static int phaseKillsSeven = 25000000;
    public static int phaseVectorPointCapSeven = 50000000;
    public static byte phaseMaxParasiteIDSeven = (byte)61;
    public static byte phaseCancelParasiteIDSeven = (byte)40;
    public static double phaseKillCountPlusSeven = 0.25;
    public static double reinforcementSystemChanceSeven = 0.14;
    public static double beckonStageIGrowPenaltySeven = 0.0;
    public static double beckonStageIIGrowPenaltySeven = 0.0;
    public static double beckonStageIIIGrowPenaltySeven = 0.0;
    public static double mobSpawningCOTHChanceSeven = 0.4;
    public static double cropGrowStunnedSeven = 0.3;
    public static int luredValueSeven = 600;
    public static int luredValueSevenCool = 350000;
    public static String phaseWarningSeven = "Seven";
    public static int sleepPenaltySeven = 12500;
    public static int phaseScentBonusSeven = 360;
    public static byte phaseScentReactionSeven = (byte)4;
    public static int phaseDelayTicksSeven = 3700;
    public static int phaseResidueSeven = 400;
    public static String[] phaseSpawnEntrySeven = new String[]{"subspaceparasite:rupter;3;6;30", "subspaceparasite:lice;1;4;20", "subspaceparasite:sim_squid;1;2;15", "subspaceparasite:sim_bigspider;3;5;25", "subspaceparasite:sim_human;3;5;25", "subspaceparasite:sim_cow;3;5;25", "subspaceparasite:sim_sheep;3;5;25", "subspaceparasite:sim_wolf;3;5;25", "subspaceparasite:sim_pig;3;5;25", "subspaceparasite:sim_villager;3;5;25", "subspaceparasite:sim_adventurer;3;5;25", "subspaceparasite:sim_horse;3;5;25", "subspaceparasite:sim_bear;3;5;25", "subspaceparasite:sim_enderman;1;1;1", "subspaceparasite:host;1;2;5", "subspaceparasite:hostii;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:crux;1;2;5", "subspaceparasite:dredge;1;2;5", "subspaceparasite:airscrew;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:abo_bodies;1;2;5", "subspaceparasite:mangler;3;6;30", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginEight = 5;
    public static double phaseOriginBonusHealthEight = 0.5;
    public static double phaseOriginBonusSizeEight = 0.4;
    public static byte[] disloPhaseEight = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationEight = 8.0;
    public static double phaseDisloCooldownEight = 8.0;
    public static double phaseDisloPointCostEight = 20.0;
    public static double phaseDisloMoreValueEight = 8.0;
    public static int eightLevelDeploy = 6;
    public static int phaseVectorMultBonusEight = 1300;
    public static int phaseKillsEight = 500000000;
    public static int phaseVectorPointCapEight = 100000000;
    public static byte phaseMaxParasiteIDEight = (byte)64;
    public static byte phaseCancelParasiteIDEight = (byte)40;
    public static double phaseKillCountPlusEight = 0.35;
    public static double reinforcementSystemChanceEight = 0.16;
    public static double beckonStageIGrowPenaltyEight = 0.0;
    public static double beckonStageIIGrowPenaltyEight = 0.0;
    public static double beckonStageIIIGrowPenaltyEight = 0.0;
    public static double mobSpawningCOTHChanceEight = 0.8;
    public static double cropGrowStunnedEight = 0.6;
    public static int luredValueEight = 1200;
    public static int luredValueEightCool = 6250000;
    public static String phaseWarningEight = "Eight";
    public static int sleepPenaltyEight = 15000;
    public static int phaseScentBonusEight = 500;
    public static byte phaseScentReactionEight = (byte)2;
    public static int phaseDelayTicksEight = 3700;
    public static int phaseResidueEight = 300;
    public static String[] phaseSpawnEntryEight = new String[]{"subspaceparasite:lice;1;4;20", "subspaceparasite:fer_human;4;5;25", "subspaceparasite:fer_cow;3;6;25", "subspaceparasite:fer_sheep;4;5;25", "subspaceparasite:fer_wolf;3;6;25", "subspaceparasite:fer_pig;3;5;25", "subspaceparasite:fer_villager;3;5;25", "subspaceparasite:fer_horse;3;5;25", "subspaceparasite:fer_enderman;3;5;25", "subspaceparasite:hostii;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:crux;1;2;5", "subspaceparasite:dredge;1;2;5", "subspaceparasite:airscrew;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:abo_bodies;1;2;5", "subspaceparasite:mangler;3;6;30", "subspaceparasite:bomber_light;1;1;5", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginNine = 5;
    public static double phaseOriginBonusHealthNine = 0.6;
    public static double phaseOriginBonusSizeNine = 0.5;
    public static byte[] disloPhaseNine = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationNine = 9.0;
    public static double phaseDisloCooldownNine = 9.0;
    public static double phaseDisloPointCostNine = 25.0;
    public static double phaseDisloMoreValueNine = 9.0;
    public static int nineLevelDeploy = 7;
    public static int phaseVectorMultBonusNine = 2700;
    public static int phaseKillsNine = 1000000000;
    public static int phaseVectorPointCapNine = 150000000;
    public static byte phaseMaxParasiteIDNine = (byte)68;
    public static byte phaseCancelParasiteIDNine = (byte)40;
    public static double phaseKillCountPlusNine = 0.45;
    public static double reinforcementSystemChanceNine = 0.18;
    public static double beckonStageIGrowPenaltyNine = 0.0;
    public static double beckonStageIIGrowPenaltyNine = 0.0;
    public static double beckonStageIIIGrowPenaltyNine = 0.0;
    public static double mobSpawningCOTHChanceNine = 0.9;
    public static double cropGrowStunnedNine = 1.0;
    public static int luredValueNine = 1200;
    public static int luredValueNineCool = 50000000;
    public static String phaseWarningNine = "Nine";
    public static int sleepPenaltyNine = 18000;
    public static int phaseScentBonusNine = 600;
    public static byte phaseScentReactionNine = (byte)2;
    public static int phaseDelayTicksNine = 3800;
    public static int phaseResidueNine = 250;
    public static String[] phaseSpawnEntryNine = new String[]{"subspaceparasite:lice;1;4;20", "subspaceparasite:sim_dragone;1;1;1", "subspaceparasite:fer_human;4;5;25", "subspaceparasite:fer_cow;3;6;25", "subspaceparasite:fer_sheep;4;5;25", "subspaceparasite:fer_wolf;3;6;25", "subspaceparasite:fer_pig;3;5;25", "subspaceparasite:fer_villager;3;5;25", "subspaceparasite:fer_horse;3;5;25", "subspaceparasite:fer_enderman;3;5;25", "subspaceparasite:hostii;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:crux;1;2;5", "subspaceparasite:dredge;1;2;5", "subspaceparasite:airscrew;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:abo_bodies;1;2;5", "subspaceparasite:mangler;3;6;30", "subspaceparasite:bomber_light;1;1;5", "subspaceparasite:worker;1;1;5", "subspaceparasite:architect;1;1;5", "subspaceparasite:bomber_heavy;1;2;5", "subspaceparasite:wraith;1;2;5", "subspaceparasite:bogle;1;2;5", "subspaceparasite:haunter;1;2;5", "subspaceparasite:carrier_colony;1;2;5", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static int phaseOriginTen = 5;
    public static double phaseOriginBonusHealthTen = 0.7;
    public static double phaseOriginBonusSizeTen = 0.65;
    public static byte[] disloPhaseTen = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static double phaseDisloDurationTen = 10.0;
    public static double phaseDisloCooldownTen = 10.0;
    public static double phaseDisloPointCostTen = 30.0;
    public static double phaseDisloMoreValueTen = 10.0;
    public static int tenLevelDeploy = 8;
    public static int phaseVectorMultBonusTen = 5500;
    public static int phaseKillsTen = 1800000000;
    public static byte phaseMaxParasiteIDTen = (byte)72;
    public static byte phaseCancelParasiteIDTen = (byte)40;
    public static double phaseKillCountPlusTen = 0.55;
    public static double reinforcementSystemChanceTen = 0.2;
    public static double beckonStageIGrowPenaltyTen = 0.0;
    public static double beckonStageIIGrowPenaltyTen = 0.0;
    public static double beckonStageIIIGrowPenaltyTen = 0.0;
    public static double mobSpawningCOTHChanceTen = 1.0;
    public static double cropGrowStunnedTen = 1.0;
    public static int luredValueTen = 1200;
    public static int luredValueTenCool = 72000000;
    public static String phaseWarningTen = "Ten";
    public static int sleepPenaltyTen = 1;
    public static int phaseScentBonusTen = 800;
    public static byte phaseScentReactionTen = (byte)2;
    public static int phaseDelayTicksTen = 6000;
    public static int phaseResidueTen = 150;
    public static int phaseTenTotalPoints = 2100000000;
    public static String[] phaseSpawnEntryTen = new String[]{"subspaceparasite:lice;1;4;20", "subspaceparasite:sim_dragone;1;1;1", "subspaceparasite:fer_human;4;5;25", "subspaceparasite:fer_cow;3;6;25", "subspaceparasite:fer_sheep;4;5;25", "subspaceparasite:fer_wolf;3;6;25", "subspaceparasite:fer_pig;3;5;25", "subspaceparasite:fer_villager;3;5;25", "subspaceparasite:fer_horse;3;5;25", "subspaceparasite:fer_enderman;3;5;25", "subspaceparasite:hostii;1;2;5", "subspaceparasite:heed;1;2;5", "subspaceparasite:crux;1;2;5", "subspaceparasite:dredge;1;2;5", "subspaceparasite:airscrew;1;2;5", "subspaceparasite:mar_human;1;1;1", "subspaceparasite:mar_cow;1;1;1", "subspaceparasite:mar_sheep;1;1;1", "subspaceparasite:mar_villager;1;1;1", "subspaceparasite:mar_bear;1;1;1", "subspaceparasite:mar_enderman;1;1;1", "subspaceparasite:abo_bodies;1;2;5", "subspaceparasite:mangler;3;6;30", "subspaceparasite:bomber_light;1;1;5", "subspaceparasite:worker;1;1;5", "subspaceparasite:bomber_heavy;1;1;1", "subspaceparasite:wraith;1;1;1", "subspaceparasite:bogle;1;1;1", "subspaceparasite:haunter;1;1;1", "subspaceparasite:carrier_colony;1;1;1", "subspaceparasite:kirin;1;1;1", "subspaceparasite:draconite;1;1;1"};
    public static byte defaultEvoPhase = (byte)-1;
    public static int defaultEvoPoints = -300;
    public static boolean phaseCustomSpawner = true;
    private static final String EVOLUTION_CATEGORYM1 = "parasite_evolution_phases_-1";
    private static final String EVOLUTION_CATEGORY0 = "parasite_evolution_phases_0";
    private static final String EVOLUTION_CATEGORY1 = "parasite_evolution_phases_1";
    private static final String EVOLUTION_CATEGORY2 = "parasite_evolution_phases_2";
    private static final String EVOLUTION_CATEGORY3 = "parasite_evolution_phases_3";
    private static final String EVOLUTION_CATEGORY4 = "parasite_evolution_phases_4";
    private static final String EVOLUTION_CATEGORY5 = "parasite_evolution_phases_5";
    private static final String EVOLUTION_CATEGORY6 = "parasite_evolution_phases_6";
    private static final String EVOLUTION_CATEGORY7 = "parasite_evolution_phases_7";
    private static final String EVOLUTION_CATEGORY8 = "parasite_evolution_phases_8";
    private static final String EVOLUTION_CATEGORY9 = "parasite_evolution_phases_9";
    private static final String EVOLUTION_CATEGORY10 = "parasite_evolution_phases_10";
    private static final String HIVEMIND_CATEGORY = "parasite_collective_consciousness";
    public static boolean useOneMind = true;
    public static int oneMinRangeCap = 32;
    public static boolean oneMindPlayer = false;
    public static boolean oneMindDebug = false;
    public static boolean useScent = true;
    public static boolean deleteifnoCC = true;
    public static boolean scentPlayer = false;
    public static int scentCap = 2;
    public static int scentSpacing = 40;
    public static double scentDeathSpawning = 0.07;
    public static double minAttriHealth = 20.0;
    public static double minAttriArmor = 0.0;
    public static double minAttriDamage = 4.0;
    public static int minAttriFailCount = 2;
    public static byte scentGoActive = (byte)5;
    public static int scentSpawnWaves = 12;
    public static int scentMiniDis = 7;
    public static int scentMaxDis = 16;
    public static int scentLifeDeath = 5;
    public static int scentLifeObserver = 60;
    public static int scentLifeTactical = 300;
    public static String[] scentLevelZero = new String[]{"subspaceparasite:rupter"};
    public static int scentWaveMinMobWaveZero = 3;
    public static int scentWaveMaxMobWaveZero = 4;
    public static int scentWaveMinimumZero = 1;
    public static int scentWaveMaximumZero = 2;
    public static String[] scentLevelOne = new String[]{"subspaceparasite:rupter"};
    public static int scentLevelPointsOne = 10;
    public static int scentWaveMinMobWaveOne = 4;
    public static int scentWaveMaxMobWaveOne = 6;
    public static int scentWaveMinimumOne = 1;
    public static int scentWaveMaximumOne = 3;
    public static String[] scentLevelTwo = new String[]{"subspaceparasite:rupter", "subspaceparasite:sim_adventurerhead", "subspaceparasite:sim_endermanhead", "subspaceparasite:sim_humanhead", "subspaceparasite:sim_horsehead", "subspaceparasite:sim_villagerhead", "subspaceparasite:sim_pighead", "subspaceparasite:sim_cowhead", "subspaceparasite:sim_wolfhead", "subspaceparasite:sim_sheephead", "subspaceparasite:subspaceparasite:heed"};
    public static int scentLevelPointsTwo = 25;
    public static int scentWaveMinMobWaveTwo = 4;
    public static int scentWaveMaxMobWaveTwo = 6;
    public static int scentWaveMinimumTwo = 2;
    public static int scentWaveMaximumTwo = 4;
    public static String[] scentLevelThree = new String[]{"subspaceparasite:rupter", "subspaceparasite:sim_adventurerhead", "subspaceparasite:sim_endermanhead", "subspaceparasite:sim_humanhead", "subspaceparasite:sim_horsehead", "subspaceparasite:sim_villagerhead", "subspaceparasite:sim_pighead", "subspaceparasite:sim_cowhead", "subspaceparasite:sim_wolfhead", "subspaceparasite:sim_sheephead", "subspaceparasite:sim_adventurer", "subspaceparasite:sim_enderman", "subspaceparasite:sim_human", "subspaceparasite:sim_horse", "subspaceparasite:sim_villager", "subspaceparasite:sim_pig", "subspaceparasite:sim_cow", "subspaceparasite:sim_wolf", "subspaceparasite:sim_sheep", "subspaceparasite:heed"};
    public static int scentLevelPointsThree = 75;
    public static int scentWaveMinMobWaveThree = 3;
    public static int scentWaveMaxMobWaveThree = 4;
    public static int scentWaveMinimumThree = 3;
    public static int scentWaveMaximumThree = 6;
    public static String[] scentLevelFour = new String[]{"subspaceparasite:sim_adventurer", "subspaceparasite:sim_enderman", "subspaceparasite:sim_human", "subspaceparasite:sim_horse", "subspaceparasite:sim_villager", "subspaceparasite:sim_pig", "subspaceparasite:sim_cow", "subspaceparasite:sim_wolf", "subspaceparasite:sim_sheep", "subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:heed", "subspaceparasite:crux"};
    public static int scentLevelPointsFour = 150;
    public static int scentWaveMinMobWaveFour = 3;
    public static int scentWaveMaxMobWaveFour = 6;
    public static int scentWaveMinimumFour = 3;
    public static int scentWaveMaximumFour = 4;
    public static String[] scentLevelFive = new String[]{"subspaceparasite:sim_adventurer", "subspaceparasite:sim_enderman", "subspaceparasite:sim_human", "subspaceparasite:sim_horse", "subspaceparasite:sim_villager", "subspaceparasite:sim_pig", "subspaceparasite:sim_cow", "subspaceparasite:sim_wolf", "subspaceparasite:sim_sheep", "subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:heed", "subspaceparasite:crux"};
    public static int scentLevelPointsFive = 150;
    public static int scentWaveMinMobWaveFive = 4;
    public static int scentWaveMaxMobWaveFive = 6;
    public static int scentWaveMinimumFive = 3;
    public static int scentWaveMaximumFive = 4;
    public static String[] scentLevelSix = new String[]{"subspaceparasite:sim_adventurer", "subspaceparasite:sim_enderman", "subspaceparasite:sim_human", "subspaceparasite:sim_horse", "subspaceparasite:sim_villager", "subspaceparasite:sim_pig", "subspaceparasite:sim_cow", "subspaceparasite:sim_wolf", "subspaceparasite:sim_sheep", "subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:heed", "subspaceparasite:crux"};
    public static int scentLevelPointsSix = 240;
    public static int scentWaveMinMobWaveSix = 2;
    public static int scentWaveMaxMobWaveSix = 5;
    public static int scentWaveMinimumSix = 2;
    public static int scentWaveMaximumSix = 5;
    public static String[] scentLevelSeven = new String[]{"subspaceparasite:sim_adventurer", "subspaceparasite:sim_enderman", "subspaceparasite:sim_human", "subspaceparasite:sim_horse", "subspaceparasite:sim_villager", "subspaceparasite:sim_pig", "subspaceparasite:sim_cow", "subspaceparasite:sim_wolf", "subspaceparasite:sim_sheep", "subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:heed", "subspaceparasite:crux"};
    public static int scentLevelPointsSeven = 360;
    public static int scentWaveMinMobWaveSeven = 2;
    public static int scentWaveMaxMobWaveSeven = 5;
    public static int scentWaveMinimumSeven = 2;
    public static int scentWaveMaximumSeven = 5;
    public static String[] scentLevelEight = new String[]{"subspaceparasite:sim_adventurer", "subspaceparasite:sim_enderman", "subspaceparasite:sim_human", "subspaceparasite:sim_horse", "subspaceparasite:sim_villager", "subspaceparasite:sim_pig", "subspaceparasite:sim_cow", "subspaceparasite:sim_wolf", "subspaceparasite:sim_sheep", "subspaceparasite:pri_longarms", "subspaceparasite:pri_manducater", "subspaceparasite:pri_reeker", "subspaceparasite:pri_yelloweye", "subspaceparasite:pri_summoner", "subspaceparasite:pri_bolster", "subspaceparasite:pri_arachnida", "subspaceparasite:pri_vermin", "subspaceparasite:heed", "subspaceparasite:crux"};
    public static int scentLevelPointsEight = 500;
    public static int scentWaveMinMobWaveEight = 2;
    public static int scentWaveMaxMobWaveEight = 5;
    public static int scentWaveMinimumEight = 2;
    public static int scentWaveMaximumEight = 5;
    private static final String HIVEMIND_CATEGORY0 = "parasite_collective_consciousness_scent_lvl_0";
    private static final String HIVEMIND_CATEGORY1 = "parasite_collective_consciousness_scent_lvl_1";
    private static final String HIVEMIND_CATEGORY2 = "parasite_collective_consciousness_scent_lvl_2";
    private static final String HIVEMIND_CATEGORY3 = "parasite_collective_consciousness_scent_lvl_3";
    private static final String HIVEMIND_CATEGORY4 = "parasite_collective_consciousness_scent_lvl_4";
    private static final String HIVEMIND_CATEGORY5 = "parasite_collective_consciousness_scent_lvl_5";
    private static final String HIVEMIND_CATEGORY6 = "parasite_collective_consciousness_scent_lvl_6";
    private static final String HIVEMIND_CATEGORY7 = "parasite_collective_consciousness_scent_lvl_7";
    private static final String HIVEMIND_CATEGORY8 = "parasite_collective_consciousness_scent_lvl_8";
    private static final String TOTALDEVELOPMENT_CATEGORY = "parasite_ubiquitous_development";
    public static double deveMobChance = 0.5;
    public static int deveDisloUse = 1;
    public static int deveMergeUse = 1;
    public static int deveOnemindUse = 2;
    public static int deveScentUse = 2;
    public static int deveOriginlessUse = 2;
    public static int deveColoniesUse = 4;
    public static int deveNodesUse = 4;
    public static int deveHivesUse = 4;
    public static int deveNestsUse = 3;
    public static int deveAlwaysVariantUse = 3;
    private static final String TOTALDEVELOPMENT_CATEGORY1 = "parasite_ubiquitous_development_1";
    public static int deveMiniDimsOne = 1;
    public static int devePointsOne = 4;
    public static String[] deveSpawnEntryUDOne = new String[0];
    private static final String TOTALDEVELOPMENT_CATEGORY2 = "parasite_ubiquitous_development_2";
    public static int deveMiniDimsTwo = 2;
    public static int devePointsTwo = 7;
    public static String[] deveSpawnEntryUDTwo = new String[]{"subspaceparasite:pri_devourer;1;2;5", "subspaceparasite:pri_longarms;2;3;15", "subspaceparasite:pri_manducater;2;3;15", "subspaceparasite:pri_reeker;2;3;15", "subspaceparasite:pri_yelloweye;2;3;10", "subspaceparasite:pri_summoner;2;3;15", "subspaceparasite:pri_bolster;2;3;15", "subspaceparasite:pri_arachnida;2;3;15", "subspaceparasite:thrall;3;5;25"};
    private static final String TOTALDEVELOPMENT_CATEGORY3 = "parasite_ubiquitous_development_3";
    public static int deveMiniDimsThree = 2;
    public static int devePointsThree = 10;
    public static String[] deveSpawnEntryUDThree = new String[]{"subspaceparasite:pri_devourer;1;2;5", "subspaceparasite:pri_longarms;2;3;15", "subspaceparasite:pri_manducater;2;3;15", "subspaceparasite:pri_reeker;2;3;15", "subspaceparasite:pri_yelloweye;2;3;10", "subspaceparasite:pri_summoner;2;3;15", "subspaceparasite:pri_bolster;2;3;15", "subspaceparasite:pri_arachnida;2;3;15", "subspaceparasite:thrall;3;5;25", "subspaceparasite:ada_devourer;1;2;10", "subspaceparasite:ada_longarms;2;3;20", "subspaceparasite:ada_manducater;2;3;20", "subspaceparasite:ada_reeker;2;3;20", "subspaceparasite:ada_yelloweye;2;3;15", "subspaceparasite:ada_summoner;2;3;20", "subspaceparasite:ada_bolster;2;3;20", "subspaceparasite:ada_arachnida;2;3;20"};
    private static final String TOTALDEVELOPMENT_CATEGORY4 = "parasite_ubiquitous_development_4";
    public static int deveMiniDimsFour = 2;
    public static int devePointsFour = 14;
    public static String[] deveSpawnEntryUDFour = new String[]{"subspaceparasite:ada_devourer;1;2;10", "subspaceparasite:ada_longarms;2;3;20", "subspaceparasite:ada_manducater;2;3;20", "subspaceparasite:ada_reeker;2;3;20", "subspaceparasite:ada_yelloweye;2;3;15", "subspaceparasite:ada_summoner;2;3;20", "subspaceparasite:ada_bolster;2;3;20", "subspaceparasite:ada_arachnida;2;3;20", "subspaceparasite:grunt;3;6;30", "subspaceparasite:monarch;1;2;10", "subspaceparasite:warden;1;2;10", "subspaceparasite:overseer;1;2;10", "subspaceparasite:vigilante;1;2;10", "subspaceparasite:marauder;1;2;10", "subspaceparasite:grunt;6;10;40"};
    private static final String TOTALDEVELOPMENT_CATEGORY5 = "parasite_ubiquitous_development_5";
    public static int deveMiniDimsFive = 3;
    public static int devePointsFive = 18;
    public static String[] deveSpawnEntryUDFive = new String[]{"subspaceparasite:grunt;3;6;30", "subspaceparasite:monarch;1;2;10", "subspaceparasite:warden;1;2;10", "subspaceparasite:overseer;1;2;10", "subspaceparasite:vigilante;1;2;10", "subspaceparasite:marauder;1;2;10", "subspaceparasite:grunt;6;10;40"};
    private static final String DISLO_CATEGORY = "parasite_dislodgment";
    public static int disloSeconds = 2;
    public static int disloGlobalCooldown = 200;
    public static int disloCOTHSpy = 4;
    public static byte[] disloCOTHIgnoreAmpEH = new byte[]{1, 10, 14, 16};
    public static byte[] disloCOTHTiersEH = new byte[]{12, 13, 14, 15, 16};
    public static byte[] disloSummonByDeathEH = new byte[]{10, 15, 16};
    public static byte[] disloPotiEffEH = new byte[]{4, 13, 14, 15, 16};
    public static byte[] dislostatsEH = new byte[]{14, 15, 17, 18};
    public static byte[] disloDeathRaidEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloItemDuraEH = new byte[]{4, 12, 13, 16};
    public static byte[] disloHealingDeathEH = new byte[]{1, 3, 10, 12, 16};
    public static byte[] disloDamageDeathEH = new byte[]{0, 5, 13, 16};
    public static byte[] disloFoodDeathEH = new byte[]{3, 12, 13, 16};
    public static byte[] disloDeathHighVerionsEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloParasiteNoPotionEH = new byte[]{3, 4, 16};
    public static byte[] disloHealthDrainingEH = new byte[]{14, 15, 17, 18};
    public static byte[] disloFoodDrainingEH = new byte[]{12, 13, 14, 15, 17, 18};
    public static byte[] disloNextPhaseLEH = new byte[]{15, 16, 17, 18};
    public static byte[] disloGrowlNoiseEH = new byte[]{0, 4, 10, 11};
    public static byte[] disloWalkNoiseEH = new byte[]{0, 5, 10, 11};
    public static byte[] disloShieldFoodEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloLootXpCancEH = new byte[]{2, 10, 16};
    public static byte[] disloKillcountIncEH = new byte[]{1, 10, 16};
    public static byte[] disloGiveBodiesEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloPhasePobyteEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloNodeColoNoLimitEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloColonyNoLimitEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloNexusGrowthEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static byte[] disloParasiteBlockEH = new byte[]{0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    public static double chanceEventRightClickB = 0.01;
    public static double chanceEventXPPickUp = 0.03;
    public static double chanceEventItemPickUp = 0.03;
    public static double chanceEventHealing = 0.001;
    public static double chanceEventUsteItem = 0.01;
    public static double chanceEventMEnuClose = 0.001;
    public static double chanceEventParaDeath = 0.001;
    public static double chanceEventParaBlockB = 0.1;
    public static double chanceEventParaNexusID = 0.05;
    public static double chanceEventParaNexusIID = 0.06;
    public static double chanceEventParaNexusIIID = 0.07;
    public static double chanceEventParaNexusIVD = 0.1;
    public static double chanceEventParaPurifier = 0.1;
    public static double chanceEventParaNodeC = 1.0;
    public static double chanceEventParaColonyC = 1.0;
    private static final String DISLO_CATEGORY0 = "parasite_dislodgment_000";
    public static boolean disloCOTHIgnoreAmp = true;
    public static int disloCOTHIgnoreAmpPrice = 100;
    public static int disloCOTHIgnoreAmpDuration = 60;
    public static int disloCOTHIgnoreAmpCooldown = 240;
    public static String disloCOTHIgnoreAmpStartMess = "";
    public static String disloCOTHIgnoreAmpEndMess = "";
    private static final String DISLO_CATEGORY1 = "parasite_dislodgment_001";
    public static boolean disloCOTHTiers = true;
    public static int disloCOTHTiersPrice = 200;
    public static int disloCOTHTiersValue = 1;
    public static int disloCOTHTiersDuration = 40;
    public static int disloCOTHTiersCooldown = 240;
    public static String disloCOTHTiersStartMess = "";
    public static String disloCOTHTiersEndMess = "";
    public static int disloCOTHTiersValue1 = 9;
    public static int disloCOTHTiersValue2 = 15;
    public static int disloCOTHTiersValue3 = 21;
    private static final String DISLO_CATEGORY2 = "parasite_dislodgment_002";
    public static boolean disloSummonByDeath = true;
    public static int disloSummonByDeathPrice = 200;
    public static int disloSummonByDeathValue = 1;
    public static int disloSummonByDeathDuration = 60;
    public static int disloSummonByDeathCooldown = 200;
    public static int disloSummonByDeathKilling = 5;
    public static String disloSummonByDeathStartMess = "";
    public static String disloSummonByDeathEndMess = "";
    public static String[] disloSummonByDeathMobs = new String[]{"1;subspaceparasite:sim_enderman", "50;subspaceparasite:fer_enderman", "100;subspaceparasite:warden"};
    private static final String DISLO_CATEGORY3 = "parasite_dislodgment_003";
    public static boolean disloPotiEff = true;
    public static int disloPotiEffPrice = 200;
    public static int disloPotiEffValue = 1;
    public static int disloPotiEffDuration = 120;
    public static int disloPotiEffCooldown = 300;
    public static String disloPotiEffStartMess = "";
    public static String disloPotiEffEndMess = "";
    public static String[] disloPotiEffEffects = new String[]{"minecraft:speed", "minecraft:fire_resistance", "minecraft:invisibility"};
    private static final String DISLO_CATEGORY4 = "parasite_dislodgment_004";
    public static boolean dislostats = true;
    public static int dislostatsPrice = 1000;
    public static int dislostatsValue = 2;
    public static int dislostatsDuration = 60;
    public static int dislostatsCooldown = 300;
    public static String disloStatsStartMess = "";
    public static String disloStatsEndMess = "";
    private static final String DISLO_CATEGORY5 = "parasite_dislodgment_005";
    public static boolean disloDeathRaid = true;
    public static int disloDeathRaidPrice = 10;
    public static int disloDeathRaidValue = 10;
    public static int disloDeathRaidDuration = 10;
    public static int disloDeathRaidCooldown = 10;
    public static String disloDeathRaidS = "";
    public static String disloDeathRaidE = "";
    private static final String DISLO_CATEGORY6 = "parasite_dislodgment_006";
    public static boolean disloItemDura = true;
    public static int disloItemDuraPrice = 100;
    public static int disloItemDuraValue = 2;
    public static int disloItemDuraDuration = 120;
    public static int disloItemDuraCooldown = 240;
    public static String disloItemDuraS = "";
    public static String disloItemDuraE = "";
    private static final String DISLO_CATEGORY7 = "parasite_dislodgment_007";
    public static boolean disloHealingDeath = true;
    public static int disloHealingDeathPrice = 500;
    public static int disloHealingDeathValue = 100;
    public static int disloHealingDeathDuration = 40;
    public static int disloHealingDeathCooldown = 240;
    public static String disloHealingDeathS = "";
    public static String disloHealingDeathE = "";
    private static final String DISLO_CATEGORY8 = "parasite_dislodgment_008";
    public static boolean disloDamageDeath = true;
    public static int disloDamageDeathPrice = 500;
    public static int disloDamageDeathValue = 10;
    public static int disloDamageDeathDuration = 60;
    public static int disloDamageDeathCooldown = 300;
    public static String disloDamageDeathS = "";
    public static String disloDamageDeathE = "";
    private static final String DISLO_CATEGORY9 = "parasite_dislodgment_009";
    public static boolean disloFoodDeath = true;
    public static int disloFoodDeathPrice = 500;
    public static int disloFoodDeathValue = 100;
    public static int disloFoodDeathDuration = 60;
    public static int disloFoodDeathCooldown = 240;
    public static String disloFoodDeathS = "";
    public static String disloFoodDeathE = "";
    private static final String DISLO_CATEGORY10 = "parasite_dislodgment_010";
    public static boolean disloDeathHighVerions = true;
    public static int disloDeathHighVerionsPrice = 300;
    public static int disloDeathHighVerionsValue = 1;
    public static int disloDeathHighVerionsValue1 = 12;
    public static int disloDeathHighVerionsValue2 = 21;
    public static int disloDeathHighVerionsDuration = 120;
    public static int disloDeathHighVerionsCooldown = 360;
    public static double disloDeathHighVerionsChance = 0.5;
    public static String disloDeathHighVerionsS = "";
    public static String disloDeathHighVerionsE = "";
    private static final String DISLO_CATEGORY11 = "parasite_dislodgment_011";
    public static boolean disloParasiteNoPotion = true;
    public static int disloParasiteNoPotionPrice = 100;
    public static int disloParasiteNoPotionDuration = 60;
    public static int disloParasiteNoPotionCooldown = 240;
    public static String disloParasiteNoPotionS = "";
    public static String disloParasiteNoPotionE = "";
    private static final String DISLO_CATEGORY12 = "parasite_dislodgment_012";
    public static boolean disloHealthDraining = true;
    public static int disloHealthDrainingPrice = 50000;
    public static int disloHealthDrainingValue = 10;
    public static int disloHealthDrainingDuration = 3;
    public static int disloHealthDrainingCooldown = 300;
    public static String disloHealthDrainingS = "";
    public static String disloHealthDrainingE = "";
    private static final String DISLO_CATEGORY13 = "parasite_dislodgment_013";
    public static boolean disloFoodDraining = true;
    public static int disloFoodDrainingPrice = 500;
    public static int disloFoodDrainingValue = 200;
    public static int disloFoodDrainingDuration = 3;
    public static int disloFoodDrainingCooldown = 300;
    public static String disloFoodDrainingS = "";
    public static String disloFoodDrainingE = "";
    private static final String DISLO_CATEGORY14 = "parasite_dislodgment_014";
    public static boolean disloNextPhaseL = true;
    public static int disloNextPhaseLPrice = 50000;
    public static int disloNextPhaseLValue = 1;
    public static int disloNextPhaseLDuration = 30;
    public static int disloNextPhaseLCooldown = 240;
    public static String disloNextPhaseLS = "";
    public static String disloNextPhaseLE = "";
    private static final String DISLO_CATEGORY15 = "parasite_dislodgment_015";
    public static boolean disloGrowlNoise = true;
    public static int disloGrowlNoisePrice = 100;
    public static int disloGrowlNoiseDuration = 60;
    public static int disloGrowlNoiseCooldown = 240;
    public static String disloGrowlNoiseS = "";
    public static String disloGrowlNoiseE = "";
    private static final String DISLO_CATEGORY16 = "parasite_dislodgment_016";
    public static boolean disloWalkNoise = true;
    public static int disloWalkNoisePrice = 100;
    public static int disloWalkNoiseDuration = 60;
    public static int disloWalkNoiseCooldown = 240;
    public static String disloWalkNoiseS = "";
    public static String disloWalkNoiseE = "";
    private static final String DISLO_CATEGORY17 = "parasite_dislodgment_017";
    public static boolean disloShieldFood = true;
    public static int disloShieldFoodPrice = 180;
    public static int disloShieldFoodDuration = 400;
    public static int disloShieldFoodCooldown = 550;
    public static String disloShieldFoodS = "";
    public static String disloShieldFoodE = "";
    private static final String DISLO_CATEGORY18 = "parasite_dislodgment_018";
    public static boolean disloLootXpCanc = true;
    public static int disloLootXpCancPrice = 100;
    public static int disloLootXpCancDuration = 60;
    public static int disloLootXpCancCooldown = 240;
    public static String disloLootXpCancS = "";
    public static String disloLootXpCancE = "";
    private static final String DISLO_CATEGORY19 = "parasite_dislodgment_019";
    public static boolean disloKillcountInc = true;
    public static int disloKillcountIncPrice = 100;
    public static int disloKillcountIncValue = 10;
    public static int disloKillcountIncDuration = 60;
    public static int disloKillcountIncCooldown = 240;
    public static String disloKillcountIncS = "";
    public static String disloKillcountIncE = "";
    private static final String DISLO_CATEGORY20 = "parasite_dislodgment_020";
    public static boolean disloGiveBodies = true;
    public static int disloGiveBodiesPrice = 150;
    public static int disloGiveBodiesDuration = 300;
    public static int disloGiveBodiesCooldown = 150;
    public static String disloGiveBodiesS = "";
    public static String disloGiveBodiesE = "";
    private static final String DISLO_CATEGORY21 = "parasite_dislodgment_021";
    public static boolean disloBurningDeath = true;
    public static int disloBurningDeathPrice = 50000;
    public static int disloBurningDeathDuration = 60;
    public static int disloBurningDeathCooldown = 180;
    public static String disloBurningDeathS = "";
    public static String disloBurningDeathE = "";
    private static final String DISLO_CATEGORY22 = "parasite_dislodgment_022";
    public static boolean disloSameVersionDyeing = true;
    public static int disloSameVersionDyeingPrice = 100;
    public static int disloSameVersionDyeingValue = 1;
    public static int disloSameVersionDyeingDuration = 300;
    public static int disloSameVersionDyeingCooldown = 60;
    public static String disloSameVersionDyeingS = "";
    public static String disloSameVersionDyeingE = "";
    private static final String DISLO_CATEGORY23 = "parasite_dislodgment_023";
    public static boolean disloColonyNoLimit = true;
    public static int disloColonyNoLimitPrice = 10;
    public static int disloColonyNoLimitValue = 10;
    public static int disloColonyNoLimitDuration = 10;
    public static int disloColonyNoLimitCooldown = 10;
    public static String disloColonyNoLimitS = "";
    public static String disloColonyNoLimitE = "";
    private static final String DISLO_CATEGORY24 = "parasite_dislodgment_024";
    public static boolean disloNexusGrowth = true;
    public static int disloNexusGrowthPrice = 10;
    public static int disloNexusGrowthValue = 10;
    public static int disloNexusGrowthDuration = 10;
    public static int disloNexusGrowthCooldown = 10;
    public static String disloNexusGrowthS = "";
    public static String disloNexusGrowthE = "";
    private static final String DISLO_CATEGORY25 = "parasite_dislodgment_025";
    public static boolean disloParasiteBlock = true;
    public static int disloParasiteBlockPrice = 10;
    public static int disloParasiteBlockValue = 1;
    public static int disloParasiteBlockValue1 = 9;
    public static int disloParasiteBlockValue2 = 15;
    public static int disloParasiteBlockValue3 = 21;
    public static int disloParasiteBlockDuration = 10;
    public static int disloParasiteBlockCooldown = 10;
    public static double disloParasiteBlockChance = 0.2;
    public static String disloParasiteBlockS = "";
    public static String disloParasiteBlockE = "";
    private static final String GEN_CATEGORY = "parasite_generation";
    public static boolean generationUse = true;
    public static byte generationDefa = 0;
    public static byte[] generationPhases1 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static byte[] generationPhases2 = new byte[]{3, 4, 5, 6, 7, 8, 9, 10};
    public static byte[] generationPhases3 = new byte[]{5, 6, 7, 8, 9, 10};
    public static byte[] generationPhases4 = new byte[]{7, 8, 9, 10};
    public static byte[] generationPhases5 = new byte[]{9, 10};
    public static float generationPhasePenalty = 1.5f;
    public static String[] generationDimStart = new String[0];
    private static final String GEN0_CATEGORY0 = "parasite_generation_00";
    public static float generationCOTH0 = 0.2f;
    public static boolean generationSpecialM0 = false;
    public static boolean generationSprinting0 = false;
    public static boolean generationLookWalls0 = false;
    public static boolean generationAdaptation0 = false;
    public static boolean generationDamageCap0 = false;
    public static boolean generationMiniDamage0 = false;
    public static boolean generationWaterLeap0 = false;
    public static boolean generationBlockSearch0 = false;
    public static boolean generationResidue0 = false;
    public static boolean generationOrbbox0 = false;
    public static float generationPoisonHeal0 = 0.0f;
    public static float generationMobHealing0 = 0.0f;
    public static float generationAttackSpeed0 = 1.0f;
    private static final String GEN0_CATEGORY1 = "parasite_generation_01";
    public static int generationTime1 = 25000;
    public static float generationCOTH1 = 0.3f;
    public static boolean generationSpecialM1 = false;
    public static boolean generationSprinting1 = false;
    public static boolean generationLookWalls1 = false;
    public static boolean generationAdaptation1 = false;
    public static boolean generationDamageCap1 = false;
    public static boolean generationMiniDamage1 = false;
    public static boolean generationWaterLeap1 = false;
    public static boolean generationBlockSearch1 = false;
    public static boolean generationResidue1 = false;
    public static boolean generationOrbbox1 = false;
    public static float generationPoisonHeal1 = 0.3f;
    public static float generationMobHealing1 = 0.0f;
    public static float generationAttackSpeed1 = 1.0f;
    private static final String GEN0_CATEGORY2 = "parasite_generation_02";
    public static int generationTime2 = 45000;
    public static float generationCOTH2 = 0.65f;
    public static boolean generationSpecialM2 = false;
    public static boolean generationSprinting2 = true;
    public static boolean generationLookWalls2 = false;
    public static boolean generationAdaptation2 = false;
    public static boolean generationDamageCap2 = false;
    public static boolean generationMiniDamage2 = true;
    public static boolean generationWaterLeap2 = false;
    public static boolean generationBlockSearch2 = false;
    public static boolean generationResidue2 = false;
    public static boolean generationOrbbox2 = false;
    public static float generationPoisonHeal2 = 1.0f;
    public static float generationMobHealing2 = 0.5f;
    public static float generationAttackSpeed2 = 1.0f;
    private static final String GEN0_CATEGORY3 = "parasite_generation_03";
    public static int generationTime3 = 72000;
    public static float generationCOTH3 = 1.0f;
    public static boolean generationSpecialM3 = false;
    public static boolean generationSprinting3 = true;
    public static boolean generationLookWalls3 = false;
    public static boolean generationAdaptation3 = true;
    public static boolean generationDamageCap3 = true;
    public static boolean generationMiniDamage3 = true;
    public static boolean generationWaterLeap3 = true;
    public static boolean generationBlockSearch3 = false;
    public static boolean generationResidue3 = false;
    public static boolean generationOrbbox3 = false;
    public static float generationPoisonHeal3 = 1.5f;
    public static float generationMobHealing3 = 1.0f;
    public static float generationAttackSpeed3 = 0.9f;
    private static final String GEN0_CATEGORY4 = "parasite_generation_04";
    public static int generationTime4 = 72000;
    public static float generationCOTH4 = 1.0f;
    public static boolean generationSpecialM4 = true;
    public static boolean generationSprinting4 = true;
    public static boolean generationLookWalls4 = true;
    public static boolean generationAdaptation4 = true;
    public static boolean generationDamageCap4 = true;
    public static boolean generationMiniDamage4 = true;
    public static boolean generationWaterLeap4 = true;
    public static boolean generationBlockSearch4 = false;
    public static boolean generationResidue4 = true;
    public static boolean generationOrbbox4 = false;
    public static float generationPoisonHeal4 = 2.0f;
    public static float generationMobHealing4 = 2.0f;
    public static float generationAttackSpeed4 = 0.7f;
    private static final String GEN0_CATEGORY5 = "parasite_generation_05";
    public static int generationTime5 = 72000;
    public static float generationCOTH5 = 1.0f;
    public static boolean generationSpecialM5 = true;
    public static boolean generationSprinting5 = true;
    public static boolean generationLookWalls5 = true;
    public static boolean generationAdaptation5 = true;
    public static boolean generationDamageCap5 = true;
    public static boolean generationMiniDamage5 = true;
    public static boolean generationWaterLeap5 = true;
    public static boolean generationBlockSearch5 = true;
    public static boolean generationResidue5 = true;
    public static boolean generationOrbbox5 = true;
    public static float generationPoisonHeal5 = 2.5f;
    public static float generationMobHealing5 = 3.0f;
    public static float generationAttackSpeed5 = 0.5f;

    private static void initGeneralSistemsConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL_SISTEMS, "System configuration \nVersion:1.10.6\n \nPotions IDs \nsubspaceparasite:coth \nsubspaceparasite:fear \nsubspaceparasite:antimall \nsubspaceparasite:bleed \nsubspaceparasite:corrosive \nsubspaceparasite:viral \nsubspaceparasite:rage \nsubspaceparasite:repel \nsubspaceparasite:senses \nsubspaceparasite:prey \nsubspaceparasite:debar \nsubspaceparasite:needler \nsubspaceparasite:foster \nsubspaceparasite:link \n ");
        String relayScanDesc = " \n Relay Scanner configuration:\n - Cooldown controls how long before the Relay can scan again.\n - Debug Glow makes subspaceparasite mobs glow for 10 seconds after a scan. \n";
        relayScannerCooldownSeconds = cfg.getInt("Relay Scanner Cooldown", CATEGORY_GENERAL_SISTEMS, relayScannerCooldownSeconds, 0, 0x7FFFFFF8, "Cooldown (in seconds) before the Relay Controller can scan again." + relayScanDesc);
        relayScannerDebugGlow = cfg.getBoolean("Relay Scanner Debug Glow", CATEGORY_GENERAL_SISTEMS, relayScannerDebugGlow, "Set to true if you want the Relay scan to apply Glowing to subspaceparasite mobs for 10 seconds (debug).");
        debugSpawner = cfg.getBoolean("Spawner Debug", CATEGORY_GENERAL_SISTEMS, debugSpawner, "Set to true to enable debug logging for the parasite spawner and for parasites to spawn glowing for one minute.");
        String evolutionPointDebugDesc = " \n Evolution point debug logging:\n - CSV logging writes point changes to subspaceparasite_debug/evolution_points_dimX.csv inside the world save.\n - Console logging prints each point change to the server log.\n - Caller trace records the Java class/method/line that caused the point change.\n - These options are intended for testing and can affect performance on active worlds or servers. \n";
        debugEvolutionPointsCsv = cfg.getBoolean("Evolution Points CSV Debug", CATEGORY_GENERAL_SISTEMS, debugEvolutionPointsCsv, "Set to true to write evolution point changes to CSV files. Disabled by default." + evolutionPointDebugDesc);
        debugEvolutionPointsConsole = cfg.getBoolean("Evolution Points Console Debug", CATEGORY_GENERAL_SISTEMS, debugEvolutionPointsConsole, "Set to true to print evolution point changes to the console/log. Enabled by default.");
        debugEvolutionPointsCallerTrace = cfg.getBoolean("Evolution Points Caller Trace Debug", CATEGORY_GENERAL_SISTEMS, debugEvolutionPointsCallerTrace, "Set to true to record the Java caller class/method/line for each point change. Can be expensive.");
        String guiDistortionExtendedDesc = " \n Extended distortion configuration:\n - World Text controls extra non-menu text distortion hooks.\n - The options below control which additional text elements can be distorted.\n - Chat currently affects normal player chat only.\n - Potion HUD is WIP / not implemented yet. \n";
        guiDistortionAffectsWorldText = cfg.getBoolean("GUI Distortion Affects World Text", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsWorldText, "If true, nearby distortion can also affect selected non-menu text hooks." + guiDistortionExtendedDesc);
        guiDistortionAffectsItemTooltips = cfg.getBoolean("GUI Distortion Affects Item Tooltips", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsItemTooltips, "If true, nearby distortion can scramble item names and tooltip text.");
        guiDistortionAffectsChat = cfg.getBoolean("GUI Distortion Affects Chat", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsChat, "If true, nearby distortion can scramble normal player chat messages.");
        guiDistortionAffectsSigns = cfg.getBoolean("GUI Distortion Affects Signs", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsSigns, "If true, nearby distortion can scramble in-world sign text.");
        guiDistortionAffectsPotionHud = cfg.getBoolean("GUI Distortion Affects Potion HUD", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsPotionHud, "If true, nearby distortion can scramble potion effect names and timers in the inventory HUD | WIP/not implimented yet.");
        guiDistortionAffectsItemHighlight = cfg.getBoolean("GUI Distortion Affects Item Highlight", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsItemHighlight, "If true, nearby distortion can scramble the selected-item name shown above the hotbar.");
        guiDistortionAffectsSubtitles = cfg.getBoolean("GUI Distortion Affects Subtitles", CATEGORY_GENERAL_SISTEMS, guiDistortionAffectsSubtitles, "If true, nearby distortion can scramble subtitles.");
    }

    public static int getScannerCooldownTicks() {
        int s = Math.max(0, relayScannerCooldownSeconds);
        return s * 20;
    }

    private static void initreinforcementSystemConfig(Configuration cfg) {
        String RSdescription = " \n When a parasite is killed, there is a chance for it to call reinforcements, this means that\n a Beckon will spawn nearby and will help in the fight.\n \n The Beckon has different stages and that will tell how many and which parasites it will spawn.";
        String equipS = " Ex. \"head;minecraft:iron_helmet;0.1\"  Where: \n \"head\" is for the slot (head, chest, legs, feet), \n \"minecraft:iron_helmet\" is for the item, \n \"0.1\" is for the chance to ignore block damage (1 = 100%). \n";
        String growingBeckon = " Ex. \"1;3\"  Where: \n \"1\" is for the dimension, \n \"3\" is for the stage it cannot grow up. \n";
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY, "Reinforcement System" + RSdescription);
        rsEnabled = cfg.getBoolean("Reinforcement System Enabled", REINFORCEMENT_CATEGORY, rsEnabled, "Set to false if you want to disable the Reinforcement System.");
        rschance = cfg.getFloat("Reinforcement System Chance", REINFORCEMENT_CATEGORY, rschance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a Beckon (This is ignored if Evolution Phases are enabled, it has its own option).");
        rsSounds = cfg.getBoolean("Reinforcement System Sounds", REINFORCEMENT_CATEGORY, rsSounds, "Set to false if you want to disable the Reinforcement System Sounds \n (A bell sound is played when a Beckon is summoned or when it grew up to the next stage).");
        rsBlockI = cfg.getBoolean("Reinforcement System Infestation", REINFORCEMENT_CATEGORY, rsBlockI, "Set to false if you want to disable the Reinforcement System Block Infestation.");
        rsBlockIMaxH = cfg.getFloat("Reinforcement System Hardness", REINFORCEMENT_CATEGORY, rsBlockIMaxH, 0.01f, 100.0f, "Max Hardness of the block it can infest.");
        rsPlayer = cfg.getBoolean("Reinforcement System Player Only", REINFORCEMENT_CATEGORY, rsPlayer, "Set to true if you want the Reinforcement System to only be activated by the Players.");
        blockBList = cfg.getStringList("Reinforcement System Blocks BlackList", REINFORCEMENT_CATEGORY, blockBList, "List of block that can't be infested, Ex: \"minecraft:stonebrick\" or just \"minecraft\" for a whole mod");
        blockBListWhite = cfg.getBoolean("Reinforcement System Blocks BlackList Inverted", REINFORCEMENT_CATEGORY, blockBListWhite, "Set to true if you want to use the list as a WhiteList.");
        optionalBlockDirt = cfg.getString("Reinforcement System Revert Block", REINFORCEMENT_CATEGORY, optionalBlockDirt, "What the infested block turns into when the Beckons die, meta value important");
        optionalBlockRubble = cfg.getString("Reinforcement System Revert Block 2", REINFORCEMENT_CATEGORY, optionalBlockRubble, "What the infested block 2 turns into when the Beckons die, meta value important");
        rsBlockLight = cfg.getInt("Reinforcement System Block Light Level", REINFORCEMENT_CATEGORY, rsBlockLight, 0, 16, "If the light level is higher than this, the block will not spread.");
        rsVenkrolChance = cfg.getInt("Reinforcement System Block Residue Chance", REINFORCEMENT_CATEGORY, rsVenkrolChance, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick) (This value is ignored if Evolution Phases are enabled, it has its own option).");
        rsBlockParticleS = cfg.getFloat("Reinforcement System Particle Spore Chance", REINFORCEMENT_CATEGORY, rsBlockParticleS, 0.0f, 1.0f, "Chance (1 = 100%) to spawn spore particle in an infested block (if available, every tick).");
        rsBlockParticleF = cfg.getFloat("Reinforcement System Particle Fog Chance", REINFORCEMENT_CATEGORY, rsBlockParticleF, 0.0f, 1.0f, "Chance (1 = 100%) to spawn fog particle in an infested block ((if available, every tick).");
        rsCooldown = cfg.getInt("Reinforcement System Cooldown", REINFORCEMENT_CATEGORY, rsCooldown, 1, 0x7FFFFFF8, "Cooldown (in seconds) for the Reinforcement System spawning Beckons.");
        maximumStageList = cfg.getStringList("Reinforcement System Maximum Stage", REINFORCEMENT_CATEGORY, maximumStageList, "Maximum Stage a Beckon can grow up in a Dimension." + growingBeckon);
        rsIgnoreCooldownAtSpawn = cfg.getBoolean("Reinforcement System Ignore Cooldown", REINFORCEMENT_CATEGORY, rsIgnoreCooldownAtSpawn, "Set to false if you don't want the Beckons to ignore their summoning cooldown.when spawning.");
        rsSky = cfg.getBoolean("Reinforcement System Sky", REINFORCEMENT_CATEGORY, rsSky, "Set to true if you want Beckons to only spawn if the position can see the sky (only those who are called by the death of a parasite).");
        rsSkyResidue = cfg.getBoolean("Reinforcement System Sky Residue", REINFORCEMENT_CATEGORY, rsSkyResidue, "Set to true if you want Beckons to only spawn if the position can see the sky (from residue blocks).");
        rsResidueY = cfg.getInt("Reinforcement System Block Residue Y", REINFORCEMENT_CATEGORY, rsResidueY, 0, 300, "If the y value of the block is below this, Beckons will not spawn from it.");
        rsVenkrolEmpty = cfg.getFloat("Reinforcement System Infestation Spawning Beckons", REINFORCEMENT_CATEGORY, (float)rsVenkrolEmpty, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a Beckon in Infested Blocks (S!=1) if no Beckon is around, to further infest the land.");
        rsBlockRevertStage = cfg.getInt("Reinforcement System Block Revert Stage", REINFORCEMENT_CATEGORY, rsBlockRevertStage, 1, 3, "If a Beckon is killed on an Infested block, the block will start a chain reaction and revert back all connected blocks if the meta of the block is less than or equal to this value AND the stage of the Beckon is greater than or equal to the meta value .");
    }

    private static void initmergeConfig(Configuration cfg) {
        String MergeDescription = " \n The Assimilated have the ability to learn from their battles and if the conditions are met, they will \n start to melt until they're some Moving Flesh \n If 2 of these things meet, they will fuse together and grow, if they get big enough they will spawn \n a mob from the Mob List.\n \n Conditions for the Assimilated to turn into Moving Flesh: \n \n -The first condition: the Assimilated needs to reach the primitive killcount. \n -The second condition: it needs to be next to 3 more Assimilated, regardless of their killcount/specie \n OR it needs to be next to a Moving Flesh. \n If the Assimilated with the killcount is next to atleast 3 more Assimilated, the four of them will start \n to melt BUT if it is next to a Moving flesh, only it will start to melt.";
        String mergeS = " Ex. \"minecraft:zombie;11\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"11\" is for the value the entity has (value must be an integer). \n Each Assimilated has a value, the value then passes to the Moving Flesh and when 2 Moving Flesh merge together both values will add up\n If the value matches one in the list, the corresponding entity will spawn, else it will be a random one. \n";
        String mergeI = " Ex. \"subspaceparasite:infhuman;22\"  Where: \n \"subspaceparasite:infhuman\" is for the entity (only works for Assimilated versions minus Big Spider, Enderdragon), \n \"22\" is for the value the Moving Flesh will have (value must be an integer). \n";
        cfg.addCustomCategoryComment(MERGE_CATEGORY, "Merge System" + MergeDescription);
        mergeRandom = cfg.getBoolean("Merge System Random", MERGE_CATEGORY, mergeRandom, "Set to false if you don't want to spawn random mobs from the Mob List (values will be used).");
        mergeHealth = cfg.getFloat("Merge System Mob Health", MERGE_CATEGORY, (float)mergeHealth, 0.0f, 1.0f, "Amount of health (1 = 100%) the mob spawns with.");
        mergeMobTable = cfg.getStringList("Merge System Mob List", MERGE_CATEGORY, mergeMobTable, "Mob list used in the merge system to spawn mobs." + mergeS);
        mergeInfValues = cfg.getStringList("Merge System Assimilated Values", MERGE_CATEGORY, mergeInfValues, "Assimilated mob list with their values used in the merge system to spawn mobs." + mergeI);
    }

    private static void initstatuseffectsConfig(Configuration cfg) {
        String helper = " Where: \n \"minecraft:stone\" is the item, \n \"0.1\" is the chance to apply the effect (1=100%), \n \"200\" is the duration of the effect in seconds. \n Right click an entity to give it Camouflage. \n";
        String description = " \n Fear.\n \n COTH.\n \n Needler.\n \n Bleeding.\n \n Camouflage";
        cfg.addCustomCategoryComment(STATUSEFFECTS_CATEGORY, "Status effects of the mod" + description);
        fearActive = cfg.getBoolean("Fear Acivated", STATUSEFFECTS_CATEGORY, fearActive, "Set to false if you dont want the Fear effect to work");
        fearUnfair = cfg.getBoolean("Fear Ground", STATUSEFFECTS_CATEGORY, fearUnfair, "Set to false if fear should work by true air state ");
        fearFallDamage = cfg.getFloat("Fear Fall Damage Multiplier", STATUSEFFECTS_CATEGORY, fearFallDamage, 0.0f, 100.0f, "Fear fall damage multiplier, multiplied by the amp.");
        fearAirDamage = cfg.getFloat("Fear Air Damage Multiplier", STATUSEFFECTS_CATEGORY, fearAirDamage, 0.0f, 100.0f, "Fear air damage multiplier, multiplied by the amp.");
        fearBlockChance = cfg.getFloat("Fear Block Fail Chance", STATUSEFFECTS_CATEGORY, fearBlockChance, 0.0f, 1.0f, "Chance (1 = 100%) to fail using a block under the Fear status effect (with an amplifier of 2 and up) - WARNING - EXPERIMENTAL.");
        fearItemChance = cfg.getFloat("Fear Item Fail Chance", STATUSEFFECTS_CATEGORY, fearItemChance, 0.0f, 1.0f, "Chance (1 = 100%) to to fail using an item under the Fear status effect (with an amplifier of 3 and up) - WARNING - EXPERIMENTAL.");
        fearInvChance = cfg.getFloat("Fear Inventory Fail Chance", STATUSEFFECTS_CATEGORY, fearInvChance, 0.0f, 1.0f, "Chance (1 = 100%) to to fail to open the inventory under the Fear status effect (with an amplifier of 4 and up) - WARNING - EXPERIMENTAL.");
        fearItemBlackList = cfg.getStringList("Fear Item BlackList", STATUSEFFECTS_CATEGORY, fearItemBlackList, "Items that wont be affected by this effect, Ex: \"minecraft:flint_and_steel\" or just \"minecraft\" for a whole mod");
        fearItemBlackListWhite = cfg.getBoolean("Fear Item BlackList Inverted", STATUSEFFECTS_CATEGORY, fearItemBlackListWhite, "Set to true if you want to use the list as a WhiteList.");
        cothActive = cfg.getBoolean("COTH Acivated", STATUSEFFECTS_CATEGORY, cothActive, "Set to false if you dont want the COTH effect to work");
        cothPlayer = cfg.getBoolean("COTH Player", STATUSEFFECTS_CATEGORY, cothPlayer, "Set to false if you dont want COTH to work on Players");
        COTHVictimParasite = cfg.getStringList("COTH Assimilated Transformation", STATUSEFFECTS_CATEGORY, COTHVictimParasite, "Table that will be used for converting mobs, the Rupter will also be using this");
        COTHImmuneList = cfg.getStringList("COTH Mob Immune Mob List", STATUSEFFECTS_CATEGORY, COTHImmuneList, "Mobs that are immune to the COTH effect, Ex: \"minecraft:zombie\" or just \"minecraft\" for a whole mod");
        COTHImmuneListWhite = cfg.getBoolean("COTH Mob Immune Mob List Inverted", STATUSEFFECTS_CATEGORY, COTHImmuneListWhite, "Set to true if you want to use the list as a WhiteList.");
        cothAura = cfg.getInt("COTH Aura", STATUSEFFECTS_CATEGORY, cothAura, 0, 10, "Set to false if you dont want the effect to spread from mob to mob");
        cothConvert = cfg.getFloat("COTH Convert At Kill", STATUSEFFECTS_CATEGORY, cothConvert, 0.0f, 1.0f, "Chance (1 = 100%) for any parasite to convert its victim when killed (only if the victim is under COTH effect).");
        cothInfected = cfg.getFloat("Version Assimilated COTH", STATUSEFFECTS_CATEGORY, cothInfected, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothHijacked = cfg.getFloat("Version Hijacked COTH", STATUSEFFECTS_CATEGORY, cothHijacked, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothFeral = cfg.getFloat("Version Feral COTH", STATUSEFFECTS_CATEGORY, cothFeral, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothCrude = cfg.getFloat("Version Crude COTH", STATUSEFFECTS_CATEGORY, cothCrude, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothPrimitive = cfg.getFloat("Version Primitive COTH", STATUSEFFECTS_CATEGORY, cothPrimitive, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothAdapted = cfg.getFloat("Version Adapted COTH", STATUSEFFECTS_CATEGORY, cothAdapted, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothPure = cfg.getFloat("Version Pure COTH", STATUSEFFECTS_CATEGORY, cothPure, 0.0f, 1.0f, "Chance (1 = 100%) to infect with COTH per hit.");
        cothUnhide = cfg.getFloat("COTH Health Threshold", STATUSEFFECTS_CATEGORY, cothUnhide, 0.0f, 1.0f, "Below this health threshold, the victim will attempt to unhide (if available).");
        cothLootDisable = cfg.getBoolean("COTH Looting", STATUSEFFECTS_CATEGORY, cothLootDisable, "Set to false if you want mobs with COTH to drop loot (This is ignored if Evolution Phases are enabled, it has its own option)");
        COTHPopping = cfg.getBoolean("The Rot Consumes", STATUSEFFECTS_CATEGORY, COTHPopping, "Dying while COTH is at amp 2 (III) causes players to infest the ground below them. Disabled by default.");
        bleedingDamage = cfg.getFloat("Bleeding Damage", STATUSEFFECTS_CATEGORY, bleedingDamage, 0.0f, 1.0f, "Damage (1 = 100%) this effect will deal (victims total health).");
        bleedingDamageCap = cfg.getFloat("Bleeding Damage Limit", STATUSEFFECTS_CATEGORY, bleedingDamageCap, 0.0f, 1000.0f, "Maximum damage that this effect will inflict.");
        corroValue = cfg.getInt("Corrosive Damage Value", STATUSEFFECTS_CATEGORY, corroValue, 0, 100000, "Amount of damage your armor will receive under this effect.");
        corrNot = cfg.getFloat("Corrosive Percentage To Work", STATUSEFFECTS_CATEGORY, (float)corrNot, 0.0f, 1.0f, "If the durability of the armor is below this threshold, the effect will not work.");
        viralEnable = cfg.getBoolean("Viral Acivated", STATUSEFFECTS_CATEGORY, viralEnable, "Set to false if you dont want the viral effect");
        viralAmount = cfg.getFloat("Viral Multiplier", STATUSEFFECTS_CATEGORY, viralAmount, 0.0f, 100.0f, "This value will be multiplied by the amplifier and then multiplied by the damage which will be added to the total damage.");
        rageEnable = cfg.getBoolean("Rage Acivated", STATUSEFFECTS_CATEGORY, rageEnable, "Set to false if you dont want parasites to spawn/give the rage effect");
        rageDamage = cfg.getFloat("Enraged Damage", STATUSEFFECTS_CATEGORY, (float)rageDamage, 0.0f, 100.0f, "(1 = 100%) How much this effect will increase damage.");
        rageSpeed = cfg.getFloat("Enraged Speed", STATUSEFFECTS_CATEGORY, (float)rageSpeed, 0.0f, 100.0f, "(1 = 100%) How much this effect will increase speed.");
        COTHItemPrevent = cfg.getStringList("Camouflage Item List", STATUSEFFECTS_CATEGORY, COTHItemPrevent, "List of items that can prevent an entity form getting COTH. Ex. \"minecraft:stone;0.1;200\" " + helper);
        needlerDamage = cfg.getFloat("Needler Damage", STATUSEFFECTS_CATEGORY, needlerDamage, 0.0f, 1.0f, "Damage (1 = 100%) this effect will deal (victims total health) by reaching high amplifier.");
        needlerTerminal = cfg.getInt("Needler Terminal Amplifier", STATUSEFFECTS_CATEGORY, needlerTerminal, 0, 100, "Mobs reaching this amplfifier will explode.");
        needlerImmuneList = cfg.getStringList("Needler Immune Mob List", STATUSEFFECTS_CATEGORY, needlerImmuneList, "Mobs that are immune to the Needler effect, Ex: \"minecraft:zombie\" or just \"minecraft\" for a whole mod");
        needlerImmuneListWhite = cfg.getBoolean("Needler Immune Mob List Inverted", STATUSEFFECTS_CATEGORY, needlerImmuneListWhite, "Set to true if you want to use the list as a WhiteList.");
        needlerMaxDamPlayer = cfg.getFloat("Needler Maximum Damage Player", STATUSEFFECTS_CATEGORY, needlerMaxDamPlayer, 0.0f, 2.0E9f, "Maximum damage this effect will inflict to the player.");
        needlerMaxDamMonster = cfg.getFloat("Needler Maximum Damage Monster", STATUSEFFECTS_CATEGORY, needlerMaxDamMonster, 0.0f, 2.0E9f, "Maximum damage this effect will inflict to the Monster.");
        adapsChance = cfg.getFloat("Link Chance", STATUSEFFECTS_CATEGORY, (float)adapsChance, 0.0f, 1.0f, "(1 = 100%) Chance to send their adaptation on death (multiplied by the amp + 1).");
        parateMuch = cfg.getFloat("Parate Value", STATUSEFFECTS_CATEGORY, (float)parateMuch, 0.0f, 10.0f, "(1 = 100%) How much of the attributes the parasite will absorb from its victim's attributes by killing it, affected by amplifier by multiplying it.");
        hijackHealth = cfg.getFloat("Hijacked Health Required", STATUSEFFECTS_CATEGORY, hijackHealth, 0.0f, 1.0f, "Mobs with equal or lower percentage of health may be converted to hijacked versions.");
        HIJACKVictimParasite = cfg.getStringList("Hijacked Transformation", STATUSEFFECTS_CATEGORY, HIJACKVictimParasite, "Table that will be used for hijacking mobs.");
        pivotDamageRHost = cfg.getFloat("Pivot Damage Sent", STATUSEFFECTS_CATEGORY, pivotDamageRHost, 0.0f, 1.0f, "Amount of damage % it will be sent to the Rooter, affected by amplifier.");
        pivotDamageRNotHost = cfg.getFloat("Pivot Damage Left", STATUSEFFECTS_CATEGORY, pivotDamageRNotHost, 0.0f, 1.0f, "Amount of damage % that will not be removed by the Rooter.");
        pivotPointMultiplier = cfg.getInt("Pivot Point Gained Multiplier", STATUSEFFECTS_CATEGORY, pivotPointMultiplier, 1, 100, "Point multiplier, used in Evolution Phases, affected by amplifier.");
        parasiteKillingReduction = cfg.getFloat("Parasite Damage Reduction", STATUSEFFECTS_CATEGORY, parasiteKillingReduction, 0.0f, 1.0f, "Amount of damage % it will be reduced, affected by amplifier + 1, to potion the following potion effects: Primitive, Adapted, Pure, Feral, Crude, Nexus.");
        muscleoutDamageOut = cfg.getFloat("MuscleOut Value", STATUSEFFECTS_CATEGORY, muscleoutDamageOut, 0.0f, 1.0f, "Damage reduced % while on this effect, multiplied by amplifier.");
    }

    private static void initevolutionConfig(Configuration cfg) {
        String evolutionDescription = " \n If Evolution Phases is true, the current phase will dictate if a parasite can spawn naturally \n in the world and by reaching some phases, they will unlock some bonuses\n *Spawnrates are not affected by the Phases\n  \n -> Phase 0:\n    -Buglins spawn naturally\n    -Parasites will ignore sunlight when spawning naturally\n  \n -> Phase 1:\n    -Rupters spawn naturally, with a hit and run behavior\n    -Animals and mobs infected with COTH will not mutate unless forced to low health\n    \n -> Phase 2:\n    -Assimilated versions spawn naturally\n    -Rupters will now attack as normal\n    \n -> Phase 3:\n    -Reinforcement system is unlocked\n    -Beckons will slowly grow to Stage II and beyond\n    -The passive point gaining is now active\n \t -Mobs with COTH will stop dropping loot\n    \n -> Phase 4:\n    -Normal growth to Beckons Stage II, but will slowly grow to Stage III\n    -Passive point gaining recieves a buff to the number gaining\n    -Reinforcement system chance increased\n \t -Buglins will stop spawning naturally\n\t -No more fishing\n    \n -> Phase 5:\n    -Passive point gaining recieves a buff to the number gaining\n    -Players sleep penalty points increased\n    -Normal growth to Beckons Stage III but will slowly grow  to Stage IV\n    -Feral versions spawn naturally\n    -COTH hidden mobs are now dropping their disguise\n    -Beckons will ignore summoning cooldown when spawning\n    -Reinforcement system chance increased\n    \n -> Phase 6:\n    -Normal growth to Beckons Stage IV\n    -Passive point gaining recieves a buff to the number gaining\n    -Assimilated versions and lower tier parasites will stop spawning naturally\n    -Mobs have a chance to spawn with COTH now\n    -Crop growth is now partially stunted\n    -Reinforcement system chance increased\n    \n -> Phase 7:\n    -Chance for a mob to spawn with COTH increased\n    -Passive point gaining recieves a buff to the number gaining\n    -Crop growth is now partially stunted\n    -Reinforcement system chance increased\n    \n -> Phase 8:\n    -Passive point gaining recieves a buff to the number gaining\n    -Chance for a mob to spawn with COTH increased\n    -Crop growth is now partially stunted\n    -Reinforcement system chance increased\n    \n    \n \n To reduce their evolution phase you need to craft Lure Blocks";
        cfg.addCustomCategoryComment(EVOLUTION_CATEGORY, "Evolution Phases" + evolutionDescription);
        String exa = " Where: \n \"1\" is the dimension, \n \"8\" is the evolution phase,\n \"10\" is the number of points parasites will have, if the phase is -1 these points will be negative (Set the points above 0 if the phase is -1 or -2)\n Parasites will not spawn and can't earn points if the phase is -2";
        String exa22 = " Where: \n \"1\" is the dimension, \n \"8\" is the evolution phase,\n \"10\" is the parasite ID \n\n List of IDs:\n 1 Primitive Longarms\n 2 Assimilated Big Spider\n 3 Heavy Carrier\n 4 Primitive Yelloweye\n 5 Buglin\n 6 Assimilated Human\n 7 Primitive Manducator\n 8 Primitive Summoner\n 9 Overseer\n 10 Primitive Reeker\n 11 Flying Carrier\n 12 Rupter\n 13 Assimilated Cow\n 14 Assimilated Sheep\n 15 Assimilated Wolf\n 16 Beckon Stage I\n 17 Primitive Bolster\n 18 Beckon Stage II\n 19 Beckon Stage III\n 20 Ancient Overlord\n 21 Assimilated Wolf Head\n 22 Assimilated Sheep Head\n 23 Moving Flesh\n 24 Ancient Dreadnaut\n 25 Vigilante\n 26 Assimilated Pig\n 27 Assimilated Villager\n 28 Assimilated Cow Head\n 29 Kyphosis\n 30 Sentry\n 31 Assimilated Pig Head\n 32 Assimilated Villager Head\n 33 Warden\n 34 Ancient Drop Pod\n 35 Ancient Dreadnaut Tentacle\n 36 Worker\n 37 Primitive Tozoon\n 38 Primitive Arachnida\n 39 Incomplete Form Small\n 40 Assimilated Adventurer\n 41 Beckon Stage IV\n 42 Beckon Stage V\n 43 Incomplete Form Medium\n 44 Assimilated Horse\n 45 Assimilated Horse Head\n 46 Assimilated Human Head\n 47 Light Bomber\n 48 Host\n 49 Assimilated Bear\n 50 Marauder\n 51 Adapted Longarms\n 52 Adapted Manducator\n 53 Adapted Summoner\n 54 Adapted Reeker\n 55 Adapted Yelloweye\n 56 Adapted Bolster\n 57 Adapted Tozoon\n 58 Adapted Arachnida\n 59 Assimilated Enderman\n 60 Grunt\n 61 \n 62 Crux\n 63 Heed\n 64 Assimilated Enderdragon\n 65 Heavy Bomber\n 66 Primitive Devourer\n 67 VoidWalker\n 68 Awakaned Dreadnaut\n 69 Assimilated Enderman Head\n 70 Assimilated Enderdragon Head\n 71 Assimilated Adventurer Head\n 72 Seizer\n 73 Dispatcher Stage I\n 74 Dispatcher Tentacle\n 75 Herd\n 76 Mangler\n 77 Dispatcher Stage II\n 78 Dispatcher Stage III\n 79 Dispatcher Stage IV\n 80 Thrall\n 81 Adapted Devourer\n 82 Seeker\n 83 Focused Longarms\n 84 Monarch\n 85 Bogle\n 86 Wraith\n 87 Haunter\n 88 Colony Carrier\n 89 Succor\n 90 Architect\n 91 Gnat\n 92 Primitive Vermin\n 93 Feral Cow\n 94 Feral Enderman\n 95 Feral Horse\n 96 Feral Human\n 97 Feral Pig\n 98 Feral Sheep\n 99 Feral Villager\n 300 Feral Wolf\n 301 Hijacked Golem\n 302 Hijacked Blaze\n 303 Hijacked Skeleton\n 304 Light Carrier\n 305 \n 306 Feral Bear\n 307 Assimilated Squid\n 308 Worm\n 309 Draconite";
        useEvolution = cfg.getBoolean("Evolution Phases", EVOLUTION_CATEGORY, useEvolution, "Set to false if you dont want to use evolution phases.");
        damageStationaryRS = cfg.getBoolean("Damage Deterrent RS", EVOLUTION_CATEGORY, damageStationaryRS, "Set to false if you don't want Deterrents to die if the RS chance is 0.");
        phaseCustomSpawner = cfg.getBoolean("Evolution Custom Spawner", EVOLUTION_CATEGORY, phaseCustomSpawner, "Set to false if you want to use Vanilla Spawner (parasite mob cap will not work if set to false).");
        evolutionNodeUnlock = (byte)cfg.getInt("Phase Node Unlock", EVOLUTION_CATEGORY, (int)evolutionNodeUnlock, 0, 11, "From this phase on, Nodes are unlocked.");
        evolutionColonyUnlock = (byte)cfg.getInt("Phase Colony Unlock", EVOLUTION_CATEGORY, (int)evolutionColonyUnlock, 0, 11, "From this phase on, Colonies are unlocked.");
        evolutionSleepDenied = (byte)cfg.getInt("Phase Sleep Point Multiplier", EVOLUTION_CATEGORY, (int)evolutionSleepDenied, 0, 11, "From this phase on, each time you sleep, the points obtained by sleeping will be multiplied by 5.");
        evolutionAssimilatedDehiding = (byte)cfg.getInt("Phase Assimilation Stop Hiding", EVOLUTION_CATEGORY, (int)evolutionAssimilatedDehiding, 0, 11, "From this phase on, Assimilated versions will stop hiding.");
        evolutionMudoAttack = (byte)cfg.getInt("Phase Rupter Stop Hiding", EVOLUTION_CATEGORY, (int)evolutionMudoAttack, 0, 11, "From this phase on, Rupters will stop running away from other mobs and they will directly covert.");
        evolutionBeckonIgnoreCooldown = (byte)cfg.getInt("Phase Beckon Summoning Cooldown", EVOLUTION_CATEGORY, (int)evolutionBeckonIgnoreCooldown, 0, 11, "From this phase on, Beckons will ignore their summoning cooldown when spawning.");
        evolutionSpawningIgnoreSunlight = (byte)cfg.getInt("Phase Parasites Ignore Sunlight", EVOLUTION_CATEGORY, (int)evolutionSpawningIgnoreSunlight, 0, 11, "From this phase on, Parasites will ignore sunlight when  spawning naturally.");
        evolutionDimStart = cfg.getStringList("Evolution Phases Dimension Starting Phase List", EVOLUTION_CATEGORY, evolutionDimStart, "List of dimensions that will start at a specific Evolution phase. Ex. \"1;8;10\"" + exa);
        evolutionDimGain = cfg.get(EVOLUTION_CATEGORY, "Evolution Phases Point Gain Blacklist", evolutionDimGain, "Parasites cannot earn points in these dimensions").getIntList();
        evolutionDimGainInverted = cfg.getBoolean("Evolution Phases Point Gain Blacklist Inverted", EVOLUTION_CATEGORY, evolutionDimGainInverted, "Set to true if you want to use the list as a WhiteList.");
        evolutionDimLoss = cfg.get(EVOLUTION_CATEGORY, "Evolution Phases Point Loss Blacklist", evolutionDimLoss, "Parasites cannot lose points in these dimensions").getIntList();
        evolutionDimLossInverted = cfg.getBoolean("Evolution Phases Point Loss Blacklist Inverted", EVOLUTION_CATEGORY, evolutionDimLossInverted, "Set to true if you want to use the list as a WhiteList.");
        evolutionPointCap = (byte)cfg.getInt("Point gain cap", EVOLUTION_CATEGORY, evolutionPointCap, 0, Integer.MAX_VALUE, "The maximum amount of points parasites can earn in one singular action. This is multiplied by 0.5x for Easy, 1x for Normal, and 1.5x for hard");
        evolutionCothStopLoot = (byte)cfg.getInt("Phase Loot Denied", EVOLUTION_CATEGORY, (int)evolutionCothStopLoot, 0, 11, "From this phase on, mobs with COTH will not drop loot anymore.");
        evolutionStopFishing = (byte)cfg.getInt("Phase Fishing Denied", EVOLUTION_CATEGORY, (int)evolutionStopFishing, 0, 11, "From this phase on, fishing will not give you loot anymore.");
        evolutionParasiteLock = cfg.getStringList("Evolution Parasite Lock List", EVOLUTION_CATEGORY, evolutionParasiteLock, "List of parasites that are unlocked when an evolution phase is reached. If a parasite is locked it cannot spawn naturallyEx. \"1;8;10\"" + exa22);
        evolutionParasiteLockMessage = cfg.getString("Evolution Parasite Lock Message", EVOLUTION_CATEGORY, evolutionParasiteLockMessage, "Message sent to all players when a parasite is unlocked");
        defaultEvoPhase = (byte)cfg.getInt("Default Phase Start", EVOLUTION_CATEGORY, (int)defaultEvoPhase, -2, 10, "Default initial phase value for dimensions.");
        defaultEvoPoints = cfg.getInt("Default Points Start", EVOLUTION_CATEGORY, defaultEvoPoints, -2147483640, 0x7FFFFFF8, "Default initial points for dimensions.");
        evolutionOneMind = (byte)cfg.getInt("Phase Collective Consciousness", EVOLUTION_CATEGORY, (int)evolutionOneMind, -1, 11, "From this phase on, One Mind will be active.");
        blackListedDimensionsEPP = cfg.get(EVOLUTION_CATEGORY, "Points Overtime WhiteList Dimensions", blackListedDimensionsEPP, "Parasites can only gain points overtime in these dimensions").getIntList();
        evolutionTotalKill = (byte)cfg.getInt("Phase Total Slaughter", EVOLUTION_CATEGORY, (int)evolutionTotalKill, -1, 11, "From this phase on, parasites will kill everything (not ignoring config options).");
        evolutionFeralNoSim = (byte)cfg.getInt("Phase Feral COTH", EVOLUTION_CATEGORY, (int)evolutionFeralNoSim, -1, 11, "From this phase on, COTH will spawn Feral versions instead of assimilated ones, if available.");
        evolutionNests = (byte)cfg.getInt("Phase Nest Unlock", EVOLUTION_CATEGORY, (int)evolutionNests, -1, 11, "From this phase on, Nests are unlocked.");
        evolutionDislodgment = (byte)cfg.getInt("Phase Dislodgment System", EVOLUTION_CATEGORY, (int)evolutionDislodgment, -1, 11, "From this phase on, Dislodgment System will be active.");
        evolutionParasiteAlwaysVariant = (byte)cfg.getInt("Phase Parasites Always Variant", EVOLUTION_CATEGORY, (int)evolutionParasiteAlwaysVariant, -1, 11, "From this phase on, Parasites will always spawn as a variant if available.");
        evolutionHives = (byte)cfg.getInt("Phase Hive Unlocked", EVOLUTION_CATEGORY, (int)evolutionHives, -1, 11, "From this phase on, Hives are unlocked.");
        evolutionNoParasiteHealing = (byte)cfg.getInt("Phase Healing Affected", EVOLUTION_CATEGORY, (int)evolutionNoParasiteHealing, -1, 11, "From this phase on, Healing is affected to non-parasites mobs.");
        evolutionNoParasiteHealingValue = cfg.getFloat("Phase Healing Affected Value", EVOLUTION_CATEGORY, evolutionNoParasiteHealingValue, 0.0f, 1.0f, "Value of the healing .");
        evolutionPArasitesWithoutXP = (byte)cfg.getInt("Phase Parasites Without XP", EVOLUTION_CATEGORY, (int)evolutionPArasitesWithoutXP, -1, 11, "From this phase on, Parasites will no longer drop xp.");
        evolutionParasiteStatIncrease = (byte)cfg.getInt("Phase Parasites Stats Increase", EVOLUTION_CATEGORY, (int)evolutionParasiteStatIncrease, -1, 11, "From this phase on, Parasites will spawn with an increase of stats.");
        evolutionParasiteStatIncreaseValue = cfg.getFloat("Phase Parasites Stats Increase Value", EVOLUTION_CATEGORY, evolutionParasiteStatIncreaseValue, 0.0f, 1.0f, "Parasite stats multiplier.");
        evolutionNoParasiteSpawnDenied = (byte)cfg.getInt("Phase Spawning Denied", EVOLUTION_CATEGORY, (int)evolutionNoParasiteSpawnDenied, -1, 11, "From this phase on, all non-parasites mobs will stop spawning naturally.");
        evolutionNoPlayerMultipler = cfg.getBoolean("Evolution No Players Gaining", EVOLUTION_CATEGORY, evolutionNoPlayerMultipler, "True if parasites cannot gain points when there are no players in the world.");
        valueLossBlockStain = cfg.getInt("Value Loss Block Stain", EVOLUTION_CATEGORY, valueLossBlockStain, 0, 1000000, "How many points parasites will lose when Infested Blocks type stain are mined.");
        valueLossBlockTrunk = cfg.getInt("Value Loss Block Trunk", EVOLUTION_CATEGORY, valueLossBlockTrunk, 0, 1000000, "How many points parasites will lose when Infested Blocks type trunk are mined.");
        valueLossBlockRubble = cfg.getInt("Value Loss Block Rubble", EVOLUTION_CATEGORY, valueLossBlockRubble, 0, 1000000, "How many points parasites will lose when Infested Blocks type rubble are mined.");
        valueKill = cfg.getInt("Value for kills", EVOLUTION_CATEGORY, valueKill, 0, 1000000, "Number of Points gained each kill.");
        valueCOTH = cfg.getInt("Value for COTH", EVOLUTION_CATEGORY, valueCOTH, 0, 1000000, "Number of Points gained when a mob with COTH reaches amp +2.");
        valueBlock = cfg.getInt("Value for blocks", EVOLUTION_CATEGORY, valueBlock, 0, 1000000, "Number of Points gained when converting a block.");
        valueMerge = cfg.getInt("Value for Merging", EVOLUTION_CATEGORY, valueMerge, 0, 1000000, "Number of Points gained when using the merge system to spawn Primitive Parasites.");
        valueEvolutionDespawn = cfg.getInt("Value for Despawning", EVOLUTION_CATEGORY, valueEvolutionDespawn, 0, 1000000, "Number of Points gained when Adapted versions spawned from moving flesh despawn, set this value to 0 in you dont want adapted to despawn.");
    }

    private static void initevolutionM1Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseSpawnEntryMinusne = cfg.getStringList("Phase -1 Spawn Entity List", EVOLUTION_CATEGORYM1, phaseSpawnEntryMinusne, "Entity List that will spawn at Phase 0." + entry);
        phaseOriginMinusOne = cfg.getInt("Phase -1 Emerging Infestation Vector", EVOLUTION_CATEGORYM1, phaseOriginMinusOne, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseLightlessMinusOne = cfg.getBoolean("Phase -1 Ignore Sunlight", EVOLUTION_CATEGORYM1, phaseLightlessMinusOne, "If True, Parasites will ignore sunlight when spawning naturally in this phase only if there is an active Vector.");
        phaseOriginMinusOnePenalty = cfg.getFloat("Phase -1 EIV Health Multiplier", EVOLUTION_CATEGORYM1, (float)phaseOriginMinusOnePenalty, 0.0f, 1.0f, "Parasite growth multiplier for Vector Health.");
        phaseVectorMultBonusMinusOne = cfg.getInt("Phase -1 EIV Health Bonus", EVOLUTION_CATEGORYM1, phaseVectorMultBonusMinusOne, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
    }

    private static void initevolution0Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseWarningZero = cfg.getString("Phase 0 Warning Message", EVOLUTION_CATEGORY0, phaseWarningZero, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyZero = cfg.getInt("Phase 0 Sleep Penalty", EVOLUTION_CATEGORY0, sleepPenaltyZero, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseMaxParasiteIDZero = (byte)cfg.getInt("Phase 0 Maximum Parasite ID", EVOLUTION_CATEGORY0, (int)phaseMaxParasiteIDZero, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDZero = (byte)cfg.getInt("Phase 0 Minimum Parasite ID", EVOLUTION_CATEGORY0, (int)phaseCancelParasiteIDZero, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        phaseScentBonusZero = cfg.getInt("Phase 0 Scent Death Bonus", EVOLUTION_CATEGORY0, phaseScentBonusZero, -100000, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionZero = (byte)cfg.getInt("Phase 0 Scent Reaction Bonus", EVOLUTION_CATEGORY0, (int)phaseScentReactionZero, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseSpawnEntryZero = cfg.getStringList("Phase 0 Spawn Entity List", EVOLUTION_CATEGORY0, phaseSpawnEntryZero, "Entity List that will spawn at Phase 0." + entry);
        phaseOriginZero = cfg.getInt("Phase 0 Emerging Infestation Vector", EVOLUTION_CATEGORY0, phaseOriginZero, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseVectorPointCapZero = cfg.getInt("Phase 0 Vector Point Cap", EVOLUTION_CATEGORY0, phaseVectorPointCapZero, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseVectorMultBonusZero = cfg.getInt("Phase 0 EIV Health Bonus", EVOLUTION_CATEGORY0, phaseVectorMultBonusZero, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
    }

    private static void initevolution1Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsOne = cfg.getInt("Phase 1 Points", EVOLUTION_CATEGORY1, phaseKillsOne, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 1.");
        phaseVectorPointCapOne = cfg.getInt("Phase 1 Vector Point Cap", EVOLUTION_CATEGORY1, phaseVectorPointCapOne, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusOne = cfg.getFloat("Phase 1 Killcount Plus", EVOLUTION_CATEGORY1, (float)phaseKillCountPlusOne, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDOne = (byte)cfg.getInt("Phase 1 Maximum Parasite ID", EVOLUTION_CATEGORY1, (int)phaseMaxParasiteIDOne, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDOne = (byte)cfg.getInt("Phase 1 Minimum Parasite ID", EVOLUTION_CATEGORY1, (int)phaseCancelParasiteIDOne, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceOne = cfg.getFloat("Phase 1 Reinforcement System Chance", EVOLUTION_CATEGORY1, (float)reinforcementSystemChanceOne, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyOne = cfg.getFloat("Phase 1 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY1, (float)beckonStageIGrowPenaltyOne, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyOne = cfg.getFloat("Phase 1 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY1, (float)beckonStageIIGrowPenaltyOne, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyOne = cfg.getFloat("Phase 1 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY1, (float)beckonStageIIIGrowPenaltyOne, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceOne = cfg.getFloat("Phase 1 Mob Spawn With COTH", EVOLUTION_CATEGORY1, (float)mobSpawningCOTHChanceOne, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedOne = cfg.getFloat("Phase 1 Crop Grow Stunned", EVOLUTION_CATEGORY1, (float)cropGrowStunnedOne, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueOne = cfg.getInt("Phase 1 Lure Block Cooldown Value", EVOLUTION_CATEGORY1, luredValueOne, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueOneCool = cfg.getInt("Phase 1 Carcass Value", EVOLUTION_CATEGORY1, luredValueOneCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningOne = cfg.getString("Phase 1 Warning Message", EVOLUTION_CATEGORY1, phaseWarningOne, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyOne = cfg.getInt("Phase 1 Sleep Penalty", EVOLUTION_CATEGORY1, sleepPenaltyOne, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusOne = cfg.getInt("Phase 1 Scent Death Bonus", EVOLUTION_CATEGORY1, phaseScentBonusOne, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionOne = (byte)cfg.getInt("Phase 1 Scent Reaction Bonus", EVOLUTION_CATEGORY1, (int)phaseScentReactionOne, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksOne = cfg.getInt("Phase 1 Delay", EVOLUTION_CATEGORY1, phaseDelayTicksOne, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueOne = cfg.getInt("Phase 1 Residue", EVOLUTION_CATEGORY1, phaseResidueOne, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryOne = cfg.getStringList("Phase 1 Spawn Entity List", EVOLUTION_CATEGORY1, phaseSpawnEntryOne, "Entity List that will spawn at phase 1." + entry);
        oneLevelDeploy = cfg.getInt("Phase 1 Lure Scent Level Desploy", EVOLUTION_CATEGORY1, oneLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginOne = cfg.getInt("Phase 1 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY1, phaseOriginOne, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeOne = cfg.getFloat("Phase 1 Emerging Infestation Vector Size", EVOLUTION_CATEGORY1, (float)phaseOriginBonusSizeOne, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthOne = cfg.getFloat("Phase 1 Emerging Infestation Vector Health", EVOLUTION_CATEGORY1, (float)phaseOriginBonusHealthOne, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusOne = cfg.getInt("Phase 1 EIV Health Bonus", EVOLUTION_CATEGORY1, phaseVectorMultBonusOne, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY1, "Phase 1 Dislodgment", disloPhaseOne, "Dislodgments that can be used in this phase");
        phaseDisloCooldownOne = cfg.getFloat("Phase 1 Dislodgment Cooldown", EVOLUTION_CATEGORY1, (float)phaseDisloCooldownOne, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationOne = cfg.getFloat("Phase 1 Dislodgment Duration", EVOLUTION_CATEGORY1, (float)phaseDisloDurationOne, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostOne = cfg.getFloat("Phase 1 Dislodgment Cost", EVOLUTION_CATEGORY1, (float)phaseDisloPointCostOne, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueOne = cfg.getFloat("Phase 1 Dislodgment Value", EVOLUTION_CATEGORY1, (float)phaseDisloMoreValueOne, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution2Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsTwo = cfg.getInt("Phase 2 Points", EVOLUTION_CATEGORY2, phaseKillsTwo, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 2.");
        phaseVectorPointCapTwo = cfg.getInt("Phase 2 Vector Point Cap", EVOLUTION_CATEGORY2, phaseVectorPointCapTwo, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusTwo = cfg.getFloat("Phase 2 Killcount Plus", EVOLUTION_CATEGORY2, (float)phaseKillCountPlusTwo, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDTwo = (byte)cfg.getInt("Phase 2 Maximum Parasite ID", EVOLUTION_CATEGORY2, (int)phaseMaxParasiteIDTwo, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDTwo = (byte)cfg.getInt("Phase 2 Minimum Parasite ID", EVOLUTION_CATEGORY2, (int)phaseCancelParasiteIDTwo, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceTwo = cfg.getFloat("Phase 2 Reinforcement System Chance", EVOLUTION_CATEGORY2, (float)reinforcementSystemChanceTwo, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyTwo = cfg.getFloat("Phase 2 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY2, (float)beckonStageIGrowPenaltyTwo, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyTwo = cfg.getFloat("Phase 2 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY2, (float)beckonStageIIGrowPenaltyTwo, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyTwo = cfg.getFloat("Phase 2 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY2, (float)beckonStageIIIGrowPenaltyTwo, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceTwo = cfg.getFloat("Phase 2 Mob Spawn With COTH", EVOLUTION_CATEGORY2, (float)mobSpawningCOTHChanceTwo, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedTwo = cfg.getFloat("Phase 2 Crop Grow Stunned", EVOLUTION_CATEGORY2, (float)cropGrowStunnedTwo, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueTwo = cfg.getInt("Phase 2 Lure Block Cooldown Value", EVOLUTION_CATEGORY2, luredValueTwo, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueTwoCool = cfg.getInt("Phase 2 Carcass Value", EVOLUTION_CATEGORY2, luredValueTwoCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningTwo = cfg.getString("Phase 2 Warning Message", EVOLUTION_CATEGORY2, phaseWarningTwo, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyTwo = cfg.getInt("Phase 2 Sleep Penalty", EVOLUTION_CATEGORY2, sleepPenaltyTwo, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusTwo = cfg.getInt("Phase 2 Scent Death Bonus", EVOLUTION_CATEGORY2, phaseScentBonusTwo, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionTwo = (byte)cfg.getInt("Phase 2 Scent Reaction Bonus", EVOLUTION_CATEGORY2, (int)phaseScentReactionTwo, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksTwo = cfg.getInt("Phase 2 Delay", EVOLUTION_CATEGORY2, phaseDelayTicksTwo, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueTwo = cfg.getInt("Phase 2 Residue", EVOLUTION_CATEGORY2, phaseResidueTwo, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryTwo = cfg.getStringList("Phase 2 Spawn Entity List", EVOLUTION_CATEGORY2, phaseSpawnEntryTwo, "Entity List that will spawn at phase 2." + entry);
        twoLevelDeploy = cfg.getInt("Phase 2 Lure Scent Level Desploy", EVOLUTION_CATEGORY2, twoLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginTwo = cfg.getInt("Phase 2 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY2, phaseOriginTwo, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeTwo = cfg.getFloat("Phase 2 Emerging Infestation Vector Size", EVOLUTION_CATEGORY2, (float)phaseOriginBonusSizeTwo, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthTwo = cfg.getFloat("Phase 2 Emerging Infestation Vector Health", EVOLUTION_CATEGORY2, (float)phaseOriginBonusHealthTwo, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusTwo = cfg.getInt("Phase 2 EIV Health Bonus", EVOLUTION_CATEGORY2, phaseVectorMultBonusTwo, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY2, "Phase 2 Dislodgment", disloPhaseTwo, "Dislodgments that can be used in this phase");
        phaseDisloCooldownTwo = cfg.getFloat("Phase 2 Dislodgment Cooldown", EVOLUTION_CATEGORY2, (float)phaseDisloCooldownTwo, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationTwo = cfg.getFloat("Phase 2 Dislodgment Duration", EVOLUTION_CATEGORY2, (float)phaseDisloDurationTwo, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostTwo = cfg.getFloat("Phase 2 Dislodgment Cost", EVOLUTION_CATEGORY2, (float)phaseDisloPointCostTwo, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueTwo = cfg.getFloat("Phase 2 Dislodgment Value", EVOLUTION_CATEGORY2, (float)phaseDisloMoreValueTwo, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution3Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsThree = cfg.getInt("Phase 3 Points", EVOLUTION_CATEGORY3, phaseKillsThree, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 3.");
        phaseVectorPointCapThree = cfg.getInt("Phase 3 Vector Point Cap", EVOLUTION_CATEGORY3, phaseVectorPointCapThree, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusThree = cfg.getFloat("Phase 3 Killcount Plus", EVOLUTION_CATEGORY3, (float)phaseKillCountPlusThree, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDThree = (byte)cfg.getInt("Phase 3 Maximum Parasite ID", EVOLUTION_CATEGORY3, (int)phaseMaxParasiteIDThree, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDThree = (byte)cfg.getInt("Phase 3 Minimum Parasite ID", EVOLUTION_CATEGORY3, (int)phaseCancelParasiteIDThree, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceThree = cfg.getFloat("Phase 3 Reinforcement System Chance", EVOLUTION_CATEGORY3, (float)reinforcementSystemChanceThree, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyThree = cfg.getFloat("Phase 3 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY3, (float)beckonStageIGrowPenaltyThree, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyThree = cfg.getFloat("Phase 3 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY3, (float)beckonStageIIGrowPenaltyThree, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyThree = cfg.getFloat("Phase 3 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY3, (float)beckonStageIIIGrowPenaltyThree, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceThree = cfg.getFloat("Phase 3 Mob Spawn With COTH", EVOLUTION_CATEGORY3, (float)mobSpawningCOTHChanceThree, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedThree = cfg.getFloat("Phase 3 Crop Grow Stunned", EVOLUTION_CATEGORY3, (float)cropGrowStunnedThree, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueThree = cfg.getInt("Phase 3 Lure Block Cooldown Value", EVOLUTION_CATEGORY3, luredValueThree, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueThreeCool = cfg.getInt("Phase 3 Carcass Value", EVOLUTION_CATEGORY3, luredValueThreeCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningThree = cfg.getString("Phase 3 Warning Message", EVOLUTION_CATEGORY3, phaseWarningThree, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyThree = cfg.getInt("Phase 3 Sleep Penalty", EVOLUTION_CATEGORY3, sleepPenaltyThree, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusThree = cfg.getInt("Phase 3 Scent Death Bonus", EVOLUTION_CATEGORY3, phaseScentBonusThree, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionThree = (byte)cfg.getInt("Phase 3 Scent Reaction Bonus", EVOLUTION_CATEGORY3, (int)phaseScentReactionThree, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksThree = cfg.getInt("Phase 3 Delay", EVOLUTION_CATEGORY3, phaseDelayTicksThree, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueThree = cfg.getInt("Phase 3 Residue", EVOLUTION_CATEGORY3, phaseResidueThree, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryThree = cfg.getStringList("Phase 3 Spawn Entity List", EVOLUTION_CATEGORY3, phaseSpawnEntryThree, "Entity List that will spawn at phase 3." + entry);
        threeLevelDeploy = cfg.getInt("Phase 3 Lure Scent Level Desploy", EVOLUTION_CATEGORY3, threeLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginThree = cfg.getInt("Phase 3 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY3, phaseOriginThree, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeThree = cfg.getFloat("Phase 3 Emerging Infestation Vector Size", EVOLUTION_CATEGORY3, (float)phaseOriginBonusSizeThree, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthThree = cfg.getFloat("Phase 3 Emerging Infestation Vector Health", EVOLUTION_CATEGORY3, (float)phaseOriginBonusHealthThree, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusThree = cfg.getInt("Phase 3 EIV Health Bonus", EVOLUTION_CATEGORY3, phaseVectorMultBonusThree, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY3, "Phase 3 Dislodgment", disloPhaseThree, "Dislodgments that can be used in this phase");
        phaseDisloCooldownThree = cfg.getFloat("Phase 3 Dislodgment Cooldown", EVOLUTION_CATEGORY3, (float)phaseDisloCooldownThree, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationThree = cfg.getFloat("Phase 3 Dislodgment Duration", EVOLUTION_CATEGORY3, (float)phaseDisloDurationThree, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostThree = cfg.getFloat("Phase 3 Dislodgment Cost", EVOLUTION_CATEGORY3, (float)phaseDisloPointCostThree, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueThree = cfg.getFloat("Phase 3 Dislodgment Value", EVOLUTION_CATEGORY3, (float)phaseDisloMoreValueThree, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution4Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsFour = cfg.getInt("Phase 4 Points", EVOLUTION_CATEGORY4, phaseKillsFour, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 4.");
        phaseVectorPointCapFour = cfg.getInt("Phase 4 Vector Point Cap", EVOLUTION_CATEGORY4, phaseVectorPointCapFour, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusFour = cfg.getFloat("Phase 4 Killcount Plus", EVOLUTION_CATEGORY4, (float)phaseKillCountPlusFour, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDFour = (byte)cfg.getInt("Phase 4 Maximum Parasite ID", EVOLUTION_CATEGORY4, (int)phaseMaxParasiteIDFour, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDFour = (byte)cfg.getInt("Phase 4 Minimum Parasite ID", EVOLUTION_CATEGORY4, (int)phaseCancelParasiteIDFour, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceFour = cfg.getFloat("Phase 4 Reinforcement System Chance", EVOLUTION_CATEGORY4, (float)reinforcementSystemChanceFour, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyFour = cfg.getFloat("Phase 4 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY4, (float)beckonStageIGrowPenaltyFour, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyFour = cfg.getFloat("Phase 4 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY4, (float)beckonStageIIGrowPenaltyFour, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyFour = cfg.getFloat("Phase 4 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY4, (float)beckonStageIIIGrowPenaltyFour, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceFour = cfg.getFloat("Phase 4 Mob Spawn With COTH", EVOLUTION_CATEGORY4, (float)mobSpawningCOTHChanceFour, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedFour = cfg.getFloat("Phase 4 Crop Grow Stunned", EVOLUTION_CATEGORY4, (float)cropGrowStunnedFour, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueFour = cfg.getInt("Phase 4 Lure Block Cooldown Value", EVOLUTION_CATEGORY4, luredValueFour, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueFourCool = cfg.getInt("Phase 4 Carcass Value", EVOLUTION_CATEGORY4, luredValueFourCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningFour = cfg.getString("Phase 4 Warning Message", EVOLUTION_CATEGORY4, phaseWarningFour, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyFour = cfg.getInt("Phase 4 Sleep Penalty", EVOLUTION_CATEGORY4, sleepPenaltyFour, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusFour = cfg.getInt("Phase 4 Scent Death Bonus", EVOLUTION_CATEGORY4, phaseScentBonusFour, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionFour = (byte)cfg.getInt("Phase 4 Scent Reaction Bonus", EVOLUTION_CATEGORY4, (int)phaseScentReactionFour, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksFour = cfg.getInt("Phase 4 Delay", EVOLUTION_CATEGORY4, phaseDelayTicksFour, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueFour = cfg.getInt("Phase 4 Residue", EVOLUTION_CATEGORY4, phaseResidueFour, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryFour = cfg.getStringList("Phase 4 Spawn Entity List", EVOLUTION_CATEGORY4, phaseSpawnEntryFour, "Entity List that will spawn at phase 4." + entry);
        fourLevelDeploy = cfg.getInt("Phase 4 Lure Scent Level Desploy", EVOLUTION_CATEGORY4, fourLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginFour = cfg.getInt("Phase 4 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY4, phaseOriginFour, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeFour = cfg.getFloat("Phase 4 Emerging Infestation Vector Size", EVOLUTION_CATEGORY4, (float)phaseOriginBonusSizeFour, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthFour = cfg.getFloat("Phase 4 Emerging Infestation Vector Health", EVOLUTION_CATEGORY4, (float)phaseOriginBonusHealthFour, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusFour = cfg.getInt("Phase 4 EIV Health Bonus", EVOLUTION_CATEGORY4, phaseVectorMultBonusFour, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY4, "Phase 4 Dislodgment", disloPhaseFour, "Dislodgments that can be used in this phase");
        phaseDisloCooldownFour = cfg.getFloat("Phase 4 Dislodgment Cooldown", EVOLUTION_CATEGORY4, (float)phaseDisloCooldownFour, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationFour = cfg.getFloat("Phase 4 Dislodgment Duration", EVOLUTION_CATEGORY4, (float)phaseDisloDurationFour, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostFour = cfg.getFloat("Phase 4 Dislodgment Cost", EVOLUTION_CATEGORY4, (float)phaseDisloPointCostFour, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueFour = cfg.getFloat("Phase 4 Dislodgment Value", EVOLUTION_CATEGORY4, (float)phaseDisloMoreValueFour, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution5Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsFive = cfg.getInt("Phase 5 Points", EVOLUTION_CATEGORY5, phaseKillsFive, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 5.");
        phaseVectorPointCapFive = cfg.getInt("Phase 5 Vector Point Cap", EVOLUTION_CATEGORY5, phaseVectorPointCapFive, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusFive = cfg.getFloat("Phase 5 Killcount Plus", EVOLUTION_CATEGORY5, (float)phaseKillCountPlusFive, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDFive = (byte)cfg.getInt("Phase 5 Maximum Parasite ID", EVOLUTION_CATEGORY5, (int)phaseMaxParasiteIDFive, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDFive = (byte)cfg.getInt("Phase 5 Minimum Parasite ID", EVOLUTION_CATEGORY5, (int)phaseCancelParasiteIDFive, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceFive = cfg.getFloat("Phase 5 Reinforcement System Chance", EVOLUTION_CATEGORY5, (float)reinforcementSystemChanceFive, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyFive = cfg.getFloat("Phase 5 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY5, (float)beckonStageIGrowPenaltyFive, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyFive = cfg.getFloat("Phase 5 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY5, (float)beckonStageIIGrowPenaltyFive, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyFive = cfg.getFloat("Phase 5 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY5, (float)beckonStageIIIGrowPenaltyFive, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceFive = cfg.getFloat("Phase 5 Mob Spawn With COTH", EVOLUTION_CATEGORY5, (float)mobSpawningCOTHChanceFive, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedFive = cfg.getFloat("Phase 5 Crop Grow Stunned", EVOLUTION_CATEGORY5, (float)cropGrowStunnedFive, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueFive = cfg.getInt("Phase 5 Lure Block Cooldown Value", EVOLUTION_CATEGORY5, luredValueFive, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueFiveCool = cfg.getInt("Phase 5 Carcass Value", EVOLUTION_CATEGORY5, luredValueFiveCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningFive = cfg.getString("Phase 5 Warning Message", EVOLUTION_CATEGORY5, phaseWarningFive, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyFive = cfg.getInt("Phase 5 Sleep Penalty", EVOLUTION_CATEGORY5, sleepPenaltyFive, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusFive = cfg.getInt("Phase 5 Scent Death Bonus", EVOLUTION_CATEGORY5, phaseScentBonusFive, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionFive = (byte)cfg.getInt("Phase 5 Scent Reaction Bonus", EVOLUTION_CATEGORY5, (int)phaseScentReactionFive, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksFive = cfg.getInt("Phase 5 Delay", EVOLUTION_CATEGORY5, phaseDelayTicksFive, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueFive = cfg.getInt("Phase 5 Residue", EVOLUTION_CATEGORY5, phaseResidueFive, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryFive = cfg.getStringList("Phase 5 Spawn Entity List", EVOLUTION_CATEGORY5, phaseSpawnEntryFive, "Entity List that will spawn at phase 5." + entry);
        fiveLevelDeploy = cfg.getInt("Phase 5 Lure Scent Level Desploy", EVOLUTION_CATEGORY5, fiveLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginFive = cfg.getInt("Phase 5 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY5, phaseOriginFive, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeFive = cfg.getFloat("Phase 5 Emerging Infestation Vector Size", EVOLUTION_CATEGORY5, (float)phaseOriginBonusSizeFive, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthFive = cfg.getFloat("Phase 5 Emerging Infestation Vector Health", EVOLUTION_CATEGORY5, (float)phaseOriginBonusHealthFive, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusFive = cfg.getInt("Phase 5 EIV Health Bonus", EVOLUTION_CATEGORY5, phaseVectorMultBonusFive, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY5, "Phase 5 Dislodgment", disloPhaseFive, "Dislodgments that can be used in this phase");
        phaseDisloCooldownFive = cfg.getFloat("Phase 5 Dislodgment Cooldown", EVOLUTION_CATEGORY5, (float)phaseDisloCooldownFive, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationFive = cfg.getFloat("Phase 5 Dislodgment Duration", EVOLUTION_CATEGORY5, (float)phaseDisloDurationFive, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostFive = cfg.getFloat("Phase 5 Dislodgment Cost", EVOLUTION_CATEGORY5, (float)phaseDisloPointCostFive, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueFive = cfg.getFloat("Phase 5 Dislodgment Value", EVOLUTION_CATEGORY5, (float)phaseDisloMoreValueFive, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution6Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsSix = cfg.getInt("Phase 6 Points", EVOLUTION_CATEGORY6, phaseKillsSix, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 6.");
        phaseVectorPointCapSix = cfg.getInt("Phase 6 Vector Point Cap", EVOLUTION_CATEGORY6, phaseVectorPointCapSix, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusSix = cfg.getFloat("Phase 6 Killcount Plus", EVOLUTION_CATEGORY6, (float)phaseKillCountPlusSix, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDSix = (byte)cfg.getInt("Phase 6 Maximum Parasite ID", EVOLUTION_CATEGORY6, (int)phaseMaxParasiteIDSix, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDSix = (byte)cfg.getInt("Phase 6 Minimum Parasite ID", EVOLUTION_CATEGORY6, (int)phaseCancelParasiteIDSix, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceSix = cfg.getFloat("Phase 6 Reinforcement System Chance", EVOLUTION_CATEGORY6, (float)reinforcementSystemChanceSix, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltySix = cfg.getFloat("Phase 6 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY6, (float)beckonStageIGrowPenaltySix, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltySix = cfg.getFloat("Phase 6 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY6, (float)beckonStageIIGrowPenaltySix, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltySix = cfg.getFloat("Phase 6 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY6, (float)beckonStageIIIGrowPenaltySix, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceSix = cfg.getFloat("Phase 6 Mob Spawn With COTH", EVOLUTION_CATEGORY6, (float)mobSpawningCOTHChanceSix, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedSix = cfg.getFloat("Phase 6 Crop Grow Stunned", EVOLUTION_CATEGORY6, (float)cropGrowStunnedSix, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueSix = cfg.getInt("Phase 6 Lure Block Cooldown Value", EVOLUTION_CATEGORY6, luredValueSix, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueSixCool = cfg.getInt("Phase 6 Carcass Value", EVOLUTION_CATEGORY6, luredValueSixCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningSix = cfg.getString("Phase 6 Warning Message", EVOLUTION_CATEGORY6, phaseWarningSix, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltySix = cfg.getInt("Phase 6 Sleep Penalty", EVOLUTION_CATEGORY6, sleepPenaltySix, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusSix = cfg.getInt("Phase 6 Scent Death Bonus", EVOLUTION_CATEGORY6, phaseScentBonusSix, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionSix = (byte)cfg.getInt("Phase 6 Scent Reaction Bonus", EVOLUTION_CATEGORY6, (int)phaseScentReactionSix, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksSix = cfg.getInt("Phase 6 Delay", EVOLUTION_CATEGORY6, phaseDelayTicksSix, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueSix = cfg.getInt("Phase 6 Residue", EVOLUTION_CATEGORY6, phaseResidueSix, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntrySix = cfg.getStringList("Phase 6 Spawn Entity List", EVOLUTION_CATEGORY6, phaseSpawnEntrySix, "Entity List that will spawn at phase 6." + entry);
        sixLevelDeploy = cfg.getInt("Phase 6 Lure Scent Level Desploy", EVOLUTION_CATEGORY6, sixLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginSix = cfg.getInt("Phase 6 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY6, phaseOriginSix, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeSix = cfg.getFloat("Phase 6 Emerging Infestation Vector Size", EVOLUTION_CATEGORY6, (float)phaseOriginBonusSizeSix, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthSix = cfg.getFloat("Phase 6 Emerging Infestation Vector Health", EVOLUTION_CATEGORY6, (float)phaseOriginBonusHealthSix, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusSix = cfg.getInt("Phase 6 EIV Health Bonus", EVOLUTION_CATEGORY6, phaseVectorMultBonusSix, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY6, "Phase 6 Dislodgment", disloPhaseSix, "Dislodgments that can be used in this phase");
        phaseDisloCooldownSix = cfg.getFloat("Phase 6 Dislodgment Cooldown", EVOLUTION_CATEGORY6, (float)phaseDisloCooldownSix, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationSix = cfg.getFloat("Phase 6 Dislodgment Duration", EVOLUTION_CATEGORY6, (float)phaseDisloDurationSix, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostSix = cfg.getFloat("Phase 6 Dislodgment Cost", EVOLUTION_CATEGORY6, (float)phaseDisloPointCostSix, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueSix = cfg.getFloat("Phase 6 Dislodgment Value", EVOLUTION_CATEGORY6, (float)phaseDisloMoreValueSix, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution7Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsSeven = cfg.getInt("Phase 7 Points", EVOLUTION_CATEGORY7, phaseKillsSeven, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 7.");
        phaseVectorPointCapSeven = cfg.getInt("Phase 7 Vector Point Cap", EVOLUTION_CATEGORY7, phaseVectorPointCapSeven, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusSeven = cfg.getFloat("Phase 7 Killcount Plus", EVOLUTION_CATEGORY7, (float)phaseKillCountPlusSeven, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDSeven = (byte)cfg.getInt("Phase 7 Maximum Parasite ID", EVOLUTION_CATEGORY7, (int)phaseMaxParasiteIDSeven, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDSeven = (byte)cfg.getInt("Phase 7 Minimum Parasite ID", EVOLUTION_CATEGORY7, (int)phaseCancelParasiteIDSeven, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceSeven = cfg.getFloat("Phase 7 Reinforcement System Chance", EVOLUTION_CATEGORY7, (float)reinforcementSystemChanceSeven, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltySeven = cfg.getFloat("Phase 7 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY7, (float)beckonStageIGrowPenaltySeven, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltySeven = cfg.getFloat("Phase 7 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY7, (float)beckonStageIIGrowPenaltySeven, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltySeven = cfg.getFloat("Phase 7 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY7, (float)beckonStageIIIGrowPenaltySeven, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceSeven = cfg.getFloat("Phase 7 Mob Spawn With COTH", EVOLUTION_CATEGORY7, (float)mobSpawningCOTHChanceSeven, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedSeven = cfg.getFloat("Phase 7 Crop Grow Stunned", EVOLUTION_CATEGORY7, (float)cropGrowStunnedSeven, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueSeven = cfg.getInt("Phase 7 Lure Block Cooldown Value", EVOLUTION_CATEGORY7, luredValueSeven, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueSevenCool = cfg.getInt("Phase 7 Carcass Value", EVOLUTION_CATEGORY7, luredValueSevenCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningSeven = cfg.getString("Phase 7 Warning Message", EVOLUTION_CATEGORY7, phaseWarningSeven, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltySeven = cfg.getInt("Phase 7 Sleep Penalty", EVOLUTION_CATEGORY7, sleepPenaltySeven, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusSeven = cfg.getInt("Phase 7 Scent Death Bonus", EVOLUTION_CATEGORY7, phaseScentBonusSeven, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionSeven = (byte)cfg.getInt("Phase 7 Scent Reaction Bonus", EVOLUTION_CATEGORY7, (int)phaseScentReactionSeven, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksSeven = cfg.getInt("Phase 7 Delay", EVOLUTION_CATEGORY7, phaseDelayTicksSeven, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueSeven = cfg.getInt("Phase 7 Residue", EVOLUTION_CATEGORY7, phaseResidueSeven, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntrySeven = cfg.getStringList("Phase 7 Spawn Entity List", EVOLUTION_CATEGORY7, phaseSpawnEntrySeven, "Entity List that will spawn at phase 7." + entry);
        sevenLevelDeploy = cfg.getInt("Phase 7 Lure Scent Level Desploy", EVOLUTION_CATEGORY7, sevenLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginSeven = cfg.getInt("Phase 7 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY7, phaseOriginSeven, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeSeven = cfg.getFloat("Phase 7 Emerging Infestation Vector Size", EVOLUTION_CATEGORY7, (float)phaseOriginBonusSizeSeven, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthSeven = cfg.getFloat("Phase 7 Emerging Infestation Vector Health", EVOLUTION_CATEGORY7, (float)phaseOriginBonusHealthSeven, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusSeven = cfg.getInt("Phase 7 EIV Health Bonus", EVOLUTION_CATEGORY7, phaseVectorMultBonusSeven, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY7, "Phase 7 Dislodgment", disloPhaseSeven, "Dislodgments that can be used in this phase");
        phaseDisloCooldownSeven = cfg.getFloat("Phase 7 Dislodgment Cooldown", EVOLUTION_CATEGORY7, (float)phaseDisloCooldownSeven, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationSeven = cfg.getFloat("Phase 7 Dislodgment Duration", EVOLUTION_CATEGORY7, (float)phaseDisloDurationSeven, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostSeven = cfg.getFloat("Phase 7 Dislodgment Cost", EVOLUTION_CATEGORY7, (float)phaseDisloPointCostSeven, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueSeven = cfg.getFloat("Phase 7 Dislodgment Value", EVOLUTION_CATEGORY7, (float)phaseDisloMoreValueSeven, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution8Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n";
        phaseKillsEight = cfg.getInt("Phase 8 Points", EVOLUTION_CATEGORY8, phaseKillsEight, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 8.");
        phaseVectorPointCapEight = cfg.getInt("Phase 8 Vector Point Cap", EVOLUTION_CATEGORY8, phaseVectorPointCapEight, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusEight = cfg.getFloat("Phase 8 Killcount Plus", EVOLUTION_CATEGORY8, (float)phaseKillCountPlusEight, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDEight = (byte)cfg.getInt("Phase 8 Maximum Parasite ID", EVOLUTION_CATEGORY8, (int)phaseMaxParasiteIDEight, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDEight = (byte)cfg.getInt("Phase 8 Minimum Parasite ID", EVOLUTION_CATEGORY8, (int)phaseCancelParasiteIDEight, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceEight = cfg.getFloat("Phase 8 Reinforcement System Chance", EVOLUTION_CATEGORY8, (float)reinforcementSystemChanceEight, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyEight = cfg.getFloat("Phase 8 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY8, (float)beckonStageIGrowPenaltyEight, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyEight = cfg.getFloat("Phase 8 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY8, (float)beckonStageIIGrowPenaltyEight, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyEight = cfg.getFloat("Phase 8 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY8, (float)beckonStageIIIGrowPenaltyEight, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceEight = cfg.getFloat("Phase 8 Mob Spawn With COTH", EVOLUTION_CATEGORY8, (float)mobSpawningCOTHChanceEight, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedEight = cfg.getFloat("Phase 8 Crop Grow Stunned", EVOLUTION_CATEGORY8, (float)cropGrowStunnedEight, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueEight = cfg.getInt("Phase 8 Lure Block Cooldown Value", EVOLUTION_CATEGORY8, luredValueEight, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueEightCool = cfg.getInt("Phase 8 Carcass Value", EVOLUTION_CATEGORY8, luredValueEightCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningEight = cfg.getString("Phase 8 Warning Message", EVOLUTION_CATEGORY8, phaseWarningEight, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyEight = cfg.getInt("Phase 8 Sleep Penalty", EVOLUTION_CATEGORY8, sleepPenaltyEight, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusEight = cfg.getInt("Phase 8 Scent Death Bonus", EVOLUTION_CATEGORY8, phaseScentBonusEight, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionEight = (byte)cfg.getInt("Phase 8 Scent Reaction Bonus", EVOLUTION_CATEGORY8, (int)phaseScentReactionEight, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksEight = cfg.getInt("Phase 8 Delay", EVOLUTION_CATEGORY8, phaseDelayTicksEight, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueEight = cfg.getInt("Phase 8 Residue", EVOLUTION_CATEGORY8, phaseResidueEight, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryEight = cfg.getStringList("Phase 8 Spawn Entity List", EVOLUTION_CATEGORY8, phaseSpawnEntryEight, "Entity List that will spawn at phase 8." + entry);
        eightLevelDeploy = cfg.getInt("Phase 8 Lure Scent Level Desploy", EVOLUTION_CATEGORY8, eightLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginEight = cfg.getInt("Phase 8 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY8, phaseOriginEight, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeEight = cfg.getFloat("Phase 8 Emerging Infestation Vector Size", EVOLUTION_CATEGORY8, (float)phaseOriginBonusSizeEight, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthEight = cfg.getFloat("Phase 8 Emerging Infestation Vector Health", EVOLUTION_CATEGORY8, (float)phaseOriginBonusHealthEight, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusEight = cfg.getInt("Phase 8 EIV Health Bonus", EVOLUTION_CATEGORY8, phaseVectorMultBonusEight, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY8, "Phase 8 Dislodgment", disloPhaseEight, "Dislodgments that can be used in this phase");
        phaseDisloCooldownEight = cfg.getFloat("Phase 8 Dislodgment Cooldown", EVOLUTION_CATEGORY8, (float)phaseDisloCooldownEight, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationEight = cfg.getFloat("Phase 8 Dislodgment Duration", EVOLUTION_CATEGORY8, (float)phaseDisloDurationEight, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostEight = cfg.getFloat("Phase 8 Dislodgment Cost", EVOLUTION_CATEGORY8, (float)phaseDisloPointCostEight, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueEight = cfg.getFloat("Phase 8 Dislodgment Value", EVOLUTION_CATEGORY8, (float)phaseDisloMoreValueEight, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution9Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn wnine. \n";
        phaseKillsNine = cfg.getInt("Phase 9 Points", EVOLUTION_CATEGORY9, phaseKillsNine, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 9.");
        phaseVectorPointCapNine = cfg.getInt("Phase 9 Vector Point Cap", EVOLUTION_CATEGORY9, phaseVectorPointCapNine, 0, 0x7FFFFFF8, "Point Cap Value for EIVs in this phase.");
        phaseKillCountPlusNine = cfg.getFloat("Phase 9 Killcount Plus", EVOLUTION_CATEGORY9, (float)phaseKillCountPlusNine, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDNine = (byte)cfg.getInt("Phase 9 Maximum Parasite ID", EVOLUTION_CATEGORY9, (int)phaseMaxParasiteIDNine, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDNine = (byte)cfg.getInt("Phase 9 Minimum Parasite ID", EVOLUTION_CATEGORY9, (int)phaseCancelParasiteIDNine, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceNine = cfg.getFloat("Phase 9 Reinforcement System Chance", EVOLUTION_CATEGORY9, (float)reinforcementSystemChanceNine, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyNine = cfg.getFloat("Phase 9 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY9, (float)beckonStageIGrowPenaltyNine, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyNine = cfg.getFloat("Phase 9 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY9, (float)beckonStageIIGrowPenaltyNine, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyNine = cfg.getFloat("Phase 9 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY9, (float)beckonStageIIIGrowPenaltyNine, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceNine = cfg.getFloat("Phase 9 Mob Spawn With COTH", EVOLUTION_CATEGORY9, (float)mobSpawningCOTHChanceNine, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedNine = cfg.getFloat("Phase 9 Crop Grow Stunned", EVOLUTION_CATEGORY9, (float)cropGrowStunnedNine, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueNine = cfg.getInt("Phase 9 Lure Block Cooldown Value", EVOLUTION_CATEGORY9, luredValueNine, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueNineCool = cfg.getInt("Phase 9 Carcass Value", EVOLUTION_CATEGORY9, luredValueNineCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningNine = cfg.getString("Phase 9 Warning Message", EVOLUTION_CATEGORY9, phaseWarningNine, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyNine = cfg.getInt("Phase 9 Sleep Penalty", EVOLUTION_CATEGORY9, sleepPenaltyNine, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusNine = cfg.getInt("Phase 9 Scent Death Bonus", EVOLUTION_CATEGORY9, phaseScentBonusNine, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionNine = (byte)cfg.getInt("Phase 9 Scent Reaction Bonus", EVOLUTION_CATEGORY9, (int)phaseScentReactionNine, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksNine = cfg.getInt("Phase 9 Delay", EVOLUTION_CATEGORY9, phaseDelayTicksNine, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueNine = cfg.getInt("Phase 9 Residue", EVOLUTION_CATEGORY9, phaseResidueNine, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryNine = cfg.getStringList("Phase 9 Spawn Entity List", EVOLUTION_CATEGORY9, phaseSpawnEntryNine, "Entity List that will spawn at phase 8." + entry);
        nineLevelDeploy = cfg.getInt("Phase 9 Lure Scent Level Desploy", EVOLUTION_CATEGORY9, nineLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseOriginNine = cfg.getInt("Phase 9 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY9, phaseOriginNine, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeNine = cfg.getFloat("Phase 9 Emerging Infestation Vector Size", EVOLUTION_CATEGORY9, (float)phaseOriginBonusSizeNine, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthNine = cfg.getFloat("Phase 9 Emerging Infestation Vector Health", EVOLUTION_CATEGORY9, (float)phaseOriginBonusHealthNine, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusNine = cfg.getInt("Phase 9 EIV Health Bonus", EVOLUTION_CATEGORY9, phaseVectorMultBonusNine, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY9, "Phase 9 Dislodgment", disloPhaseNine, "Dislodgments that can be used in this phase");
        phaseDisloCooldownNine = cfg.getFloat("Phase 9 Dislodgment Cooldown", EVOLUTION_CATEGORY9, (float)phaseDisloCooldownNine, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationNine = cfg.getFloat("Phase 9 Dislodgment Duration", EVOLUTION_CATEGORY9, (float)phaseDisloDurationNine, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostNine = cfg.getFloat("Phase 9 Dislodgment Cost", EVOLUTION_CATEGORY9, (float)phaseDisloPointCostNine, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueNine = cfg.getFloat("Phase 9 Dislodgment Value", EVOLUTION_CATEGORY9, (float)phaseDisloMoreValueNine, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void initevolution10Config(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn wten. \n";
        phaseKillsTen = cfg.getInt("Phase 10 Points", EVOLUTION_CATEGORY10, phaseKillsTen, 0, 0x7FFFFFF8, "Number of Points required to reach Phase 10.");
        phaseKillCountPlusTen = cfg.getFloat("Phase 10 Killcount Plus", EVOLUTION_CATEGORY10, (float)phaseKillCountPlusTen, 0.0f, 100.0f, "Each second the killcount will go up by this amount.");
        phaseMaxParasiteIDTen = (byte)cfg.getInt("Phase 10 Maximum Parasite ID", EVOLUTION_CATEGORY10, (int)phaseMaxParasiteIDTen, 0, 100, "If a parasite ID is equal to or greater than this number, the parasite will not spawn.");
        phaseCancelParasiteIDTen = (byte)cfg.getInt("Phase 10 Minimum Parasite ID", EVOLUTION_CATEGORY10, (int)phaseCancelParasiteIDTen, 0, 100, "If a parasite ID is equal to or less than this number, the parasite will not spawn.");
        reinforcementSystemChanceTen = cfg.getFloat("Phase 10 Reinforcement System Chance", EVOLUTION_CATEGORY10, (float)reinforcementSystemChanceTen, 0.0f, 1.0f, "Chance to spawn a Beckon when a parasite is killed.");
        beckonStageIGrowPenaltyTen = cfg.getFloat("Phase 10 Nexus Stage I Grow Stunned", EVOLUTION_CATEGORY10, (float)beckonStageIGrowPenaltyTen, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SI.");
        beckonStageIIGrowPenaltyTen = cfg.getFloat("Phase 10 Nexus Stage II Grow Stunned", EVOLUTION_CATEGORY10, (float)beckonStageIIGrowPenaltyTen, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SII.");
        beckonStageIIIGrowPenaltyTen = cfg.getFloat("Phase 10 Nexus Stage III Grow Stunned", EVOLUTION_CATEGORY10, (float)beckonStageIIIGrowPenaltyTen, 0.0f, 1.0f, "Chance (1=100%) to failed to grow for Nexus SIII.");
        mobSpawningCOTHChanceTen = cfg.getFloat("Phase 10 Mob Spawn With COTH", EVOLUTION_CATEGORY10, (float)mobSpawningCOTHChanceTen, 0.0f, 1.0f, "Chance (1=100%) for an entity to spawn with COTH (amp 0).");
        cropGrowStunnedTen = cfg.getFloat("Phase 10 Crop Grow Stunned", EVOLUTION_CATEGORY10, (float)cropGrowStunnedTen, 0.0f, 1.0f, "Chance (1=100%) for crop grow to be stunned.");
        luredValueTen = cfg.getInt("Phase 10 Lure Block Cooldown Value", EVOLUTION_CATEGORY10, luredValueTen, 0, 0x7FFFFFF8, "Cooldown added ot the parasites when using a Lure Block.");
        luredValueTenCool = cfg.getInt("Phase 10 Carcass Value", EVOLUTION_CATEGORY10, luredValueTenCool, 0, 0x7FFFFFF8, "Number of Points the parasites will lose when using a Carcass");
        phaseWarningTen = cfg.getString("Phase 10 Warning Message", EVOLUTION_CATEGORY10, phaseWarningTen, "Message sent to all players in the current world when parasites reach this Phase");
        sleepPenaltyTen = cfg.getInt("Phase 10 Sleep Penalty", EVOLUTION_CATEGORY10, sleepPenaltyTen, 0, 0x7FFFFFF8, "Number of Points gained when skipping the night.");
        phaseScentBonusTen = cfg.getInt("Phase 10 Scent Death Bonus", EVOLUTION_CATEGORY10, phaseScentBonusTen, 0, 1000, "Death bonus value used if parasite_collective_consciousness is enabled.");
        phaseScentReactionTen = (byte)cfg.getInt("Phase 10 Scent Reaction Bonus", EVOLUTION_CATEGORY10, (int)phaseScentReactionTen, 0, 100, "Reaction bonus value used for a scent to go active.");
        phaseDelayTicksTen = cfg.getInt("Phase 10 Delay", EVOLUTION_CATEGORY10, phaseDelayTicksTen, 0, 0x7FFFFFF8, "Parasites will not be able to earn points until this time (seconds) has passed.");
        phaseResidueTen = cfg.getInt("Phase 10 Residue", EVOLUTION_CATEGORY10, phaseResidueTen, 0, 0x7FFFFFF8, "One in X to spawn a Beckon on the Infested Block Residue (random tick).");
        phaseSpawnEntryTen = cfg.getStringList("Phase 10 Spawn Entity List", EVOLUTION_CATEGORY10, phaseSpawnEntryTen, "Entity List that will spawn at phase 8." + entry);
        tenLevelDeploy = cfg.getInt("Phase 10 Lure Scent Level Desploy", EVOLUTION_CATEGORY10, tenLevelDeploy, 1, 8, "Level that the Scent will have when it spawns from a Lure Block of this Phase.");
        phaseTenTotalPoints = cfg.getInt("Phase 10 Total Points", EVOLUTION_CATEGORY10, phaseTenTotalPoints, 0, 0x7FFFFFF8, "Parasites can't earn more points than this.");
        phaseOriginTen = cfg.getInt("Phase 10 Emerging Infestation Vector Cap", EVOLUTION_CATEGORY10, phaseOriginTen, 0, 0x7FFFFFF8, "Total number of EIVs in this Phase.");
        phaseOriginBonusSizeTen = cfg.getFloat("Phase 10 Emerging Infestation Vector Size", EVOLUTION_CATEGORY10, (float)phaseOriginBonusSizeTen, 0.0f, 10.0f, "Bonus size growth to Vectors in this Phase.");
        phaseOriginBonusHealthTen = cfg.getFloat("Phase 10 Emerging Infestation Vector Health", EVOLUTION_CATEGORY10, (float)phaseOriginBonusHealthTen, 0.0f, 10.0f, "Bonus health growth to Vectors in this Phase.");
        phaseVectorMultBonusTen = cfg.getInt("Phase 10 EIV Health Bonus", EVOLUTION_CATEGORY10, phaseVectorMultBonusTen, 1, 0x7FFFFFF8, "Health Multipler if EIVs are created in this phase.");
        SPConfigSystems.getByteVal(cfg, EVOLUTION_CATEGORY10, "Phase 10 Dislodgment", disloPhaseTen, "Dislodgments that can be used in this phase");
        phaseDisloCooldownTen = cfg.getFloat("Phase 10 Dislodgment Cooldown", EVOLUTION_CATEGORY10, (float)phaseDisloCooldownTen, 0.0f, 1.0E7f, "Extra Cooldown for each Dislodgment triggered in this phase.");
        phaseDisloDurationTen = cfg.getFloat("Phase 10 Dislodgment Duration", EVOLUTION_CATEGORY10, (float)phaseDisloDurationTen, 0.0f, 1.0E7f, "Extra Duration for each Dislodgment triggered in this phase.");
        phaseDisloPointCostTen = cfg.getFloat("Phase 10 Dislodgment Cost", EVOLUTION_CATEGORY10, (float)phaseDisloPointCostTen, 0.0f, 1.0E7f, "Extra Point Cost for each Dislodgment triggered in this phase.");
        phaseDisloMoreValueTen = cfg.getFloat("Phase 10 Dislodgment Value", EVOLUTION_CATEGORY10, (float)phaseDisloMoreValueTen, 0.0f, 1.0E7f, "Extra Value Modifier for each Dislodgment triggered in this phase.");
    }

    private static void inithivemindConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HIVEMIND_CATEGORY, "Uwu");
        useOneMind = cfg.getBoolean("Collective Consciousness", HIVEMIND_CATEGORY, useOneMind, "Set to false if you want to disable CC");
        oneMinRangeCap = cfg.getInt("Follow Range Cap", HIVEMIND_CATEGORY, oneMinRangeCap, 0, 64, ".");
        oneMindPlayer = cfg.getBoolean("CC Player Only", HIVEMIND_CATEGORY, oneMindPlayer, "Set to true for CC to only work for players.");
        deleteifnoCC = cfg.getBoolean("CC Phase Locked", HIVEMIND_CATEGORY, deleteifnoCC, "Set to true for scents to be deleted if the phase is below CC required phase.");
        oneMindDebug = cfg.getBoolean("Scent Debug", HIVEMIND_CATEGORY, oneMindDebug, "Set to true if you want to see Scent entities");
        useScent = cfg.getBoolean("Scent", HIVEMIND_CATEGORY, useScent, "Set to false if you dont want Scent to spawn in your world");
        scentPlayer = cfg.getBoolean("Scent Player", HIVEMIND_CATEGORY, scentPlayer, "Set to true if you want Scent to be activated only by Players");
        scentDeathSpawning = cfg.getFloat("Scent Spawn Death Parasite Chance", HIVEMIND_CATEGORY, (float)scentDeathSpawning, 0.0f, 1.0f, "Chance to spawn a Scent when a parasite is killed.");
        scentCap = cfg.getInt("Scent Cap", HIVEMIND_CATEGORY, scentCap, 1, 100, "Maximum number that can spawn in the world.");
        scentGoActive = (byte)cfg.getInt("Scent Death Point", HIVEMIND_CATEGORY, (int)scentGoActive, 0, 10000000, "(This list is ignored if Evolution Phases are enabled, it has its own option).");
        scentSpawnWaves = cfg.getInt("Scent Wave Point", HIVEMIND_CATEGORY, scentSpawnWaves, 0, 10000000, ".");
        scentMiniDis = cfg.getInt("Scent Wave Minimum Distance", HIVEMIND_CATEGORY, scentMiniDis, 0, 10000000, ".");
        scentMaxDis = cfg.getInt("Scent Wave Maximum Distance", HIVEMIND_CATEGORY, scentMaxDis, 0, 10000000, ".");
        scentLifeDeath = cfg.getInt("Scent Added Lifespan With Death", HIVEMIND_CATEGORY, scentLifeDeath, 0, 10000000, "seconds.");
        scentLifeObserver = cfg.getInt("Scent Observer Mode LifeSpan", HIVEMIND_CATEGORY, scentLifeObserver, 0, 10000000, "seconds.");
        scentLifeTactical = cfg.getInt("Scent Tactical Mode Lifespan", HIVEMIND_CATEGORY, scentLifeTactical, 0, 10000000, "seconds.");
        scentSpacing = cfg.getInt("Minimum Space Between Scents", HIVEMIND_CATEGORY, scentSpacing, 0, 10000000, "Minimum distance the scent has to be from other scents. If a scent is within this distance from another scent, the newly-created (or loaded) scent will be deleted.");
        minAttriHealth = cfg.getFloat("Scent Condition Minimum Health", HIVEMIND_CATEGORY, (float)minAttriHealth, 0.0f, 1000000.0f, "Minimum heatlh value that a mob must have for the scent to attack it.");
        minAttriArmor = cfg.getFloat("Scent Condition Minimum Armor", HIVEMIND_CATEGORY, (float)minAttriArmor, 0.0f, 1000000.0f, "Minimum armor value that a mob must have for the scent to attack it.");
        minAttriDamage = cfg.getFloat("Scent Condition Minimum Damage", HIVEMIND_CATEGORY, (float)minAttriDamage, 0.0f, 1000000.0f, "Minimum damage value that a mob must have for the scent to attack it.");
        minAttriFailCount = cfg.getInt("Scent Condition Number", HIVEMIND_CATEGORY, minAttriFailCount, 0, 3, "Number of conditions to be met for the Scent to attack a mob, set it to 0 to ignore them.");
    }

    private static void inithivemindscentlvl0Config(Configuration cfg) {
        scentLevelZero = cfg.getStringList("Scent Level 0 Mob Table", HIVEMIND_CATEGORY0, scentLevelZero, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumZero = cfg.getInt("Scent Level 0 Minimum Wave Number", HIVEMIND_CATEGORY0, scentWaveMinimumZero, 0, 100, ".");
        scentWaveMaximumZero = cfg.getInt("Scent Level 0 Maximum Wave Number", HIVEMIND_CATEGORY0, scentWaveMaximumZero, 0, 100, ".");
        scentWaveMinMobWaveZero = cfg.getInt("Scent Level 0 Minimum Mob Wave Number", HIVEMIND_CATEGORY0, scentWaveMinMobWaveZero, 0, 100, ".");
        scentWaveMaxMobWaveZero = cfg.getInt("Scent Level 0 Maximum Mob Wave Number", HIVEMIND_CATEGORY0, scentWaveMaxMobWaveZero, 0, 100, ".");
    }

    private static void inithivemindscentlvl1Config(Configuration cfg) {
        scentLevelOne = cfg.getStringList("Scent Level 1 Mob Table", HIVEMIND_CATEGORY1, scentLevelOne, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumOne = cfg.getInt("Scent Level 1 Minimum Wave Number", HIVEMIND_CATEGORY1, scentWaveMinimumOne, 0, 100, ".");
        scentWaveMaximumOne = cfg.getInt("Scent Level 1 Maximum Wave Number", HIVEMIND_CATEGORY1, scentWaveMaximumOne, 0, 100, ".");
        scentWaveMinMobWaveOne = cfg.getInt("Scent Level 1 Minimum Mob Wave Number", HIVEMIND_CATEGORY1, scentWaveMinMobWaveOne, 0, 100, ".");
        scentWaveMaxMobWaveOne = cfg.getInt("Scent Level 1 Maximum Mob Wave Number", HIVEMIND_CATEGORY1, scentWaveMaxMobWaveOne, 0, 100, ".");
        scentLevelPointsOne = cfg.getInt("Scent Level 1 Points Required", HIVEMIND_CATEGORY1, scentLevelPointsOne, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl2Config(Configuration cfg) {
        scentLevelTwo = cfg.getStringList("Scent Level 2 Mob Table", HIVEMIND_CATEGORY2, scentLevelTwo, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumTwo = cfg.getInt("Scent Level 2 Minimum Wave Number", HIVEMIND_CATEGORY2, scentWaveMinimumTwo, 0, 100, ".");
        scentWaveMaximumTwo = cfg.getInt("Scent Level 2 Maximum Wave Number", HIVEMIND_CATEGORY2, scentWaveMaximumTwo, 0, 100, ".");
        scentWaveMinMobWaveTwo = cfg.getInt("Scent Level 2 Minimum Mob Wave Number", HIVEMIND_CATEGORY2, scentWaveMinMobWaveTwo, 0, 100, ".");
        scentWaveMaxMobWaveTwo = cfg.getInt("Scent Level 2 Maximum Mob Wave Number", HIVEMIND_CATEGORY2, scentWaveMaxMobWaveTwo, 0, 100, ".");
        scentLevelPointsTwo = cfg.getInt("Scent Level 2 Points Required", HIVEMIND_CATEGORY2, scentLevelPointsTwo, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl3Config(Configuration cfg) {
        scentLevelThree = cfg.getStringList("Scent Level 3 Mob Table", HIVEMIND_CATEGORY3, scentLevelThree, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumThree = cfg.getInt("Scent Level 3 Minimum Wave Number", HIVEMIND_CATEGORY3, scentWaveMinimumThree, 0, 100, ".");
        scentWaveMaximumThree = cfg.getInt("Scent Level 3 Maximum Wave Number", HIVEMIND_CATEGORY3, scentWaveMaximumThree, 0, 100, ".");
        scentWaveMinMobWaveThree = cfg.getInt("Scent Level 3 Minimum Mob Wave Number", HIVEMIND_CATEGORY3, scentWaveMinMobWaveThree, 0, 100, ".");
        scentWaveMaxMobWaveThree = cfg.getInt("Scent Level 3 Maximum Mob Wave Number", HIVEMIND_CATEGORY3, scentWaveMaxMobWaveThree, 0, 100, ".");
        scentLevelPointsThree = cfg.getInt("Scent Level 3 Points Required", HIVEMIND_CATEGORY3, scentLevelPointsThree, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl4Config(Configuration cfg) {
        scentLevelFour = cfg.getStringList("Scent Level 4 Mob Table", HIVEMIND_CATEGORY4, scentLevelFour, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumFour = cfg.getInt("Scent Level 4 Minimum Wave Number", HIVEMIND_CATEGORY4, scentWaveMinimumFour, 0, 100, ".");
        scentWaveMaximumFour = cfg.getInt("Scent Level 4 Maximum Wave Number", HIVEMIND_CATEGORY4, scentWaveMaximumFour, 0, 100, ".");
        scentWaveMinMobWaveFour = cfg.getInt("Scent Level 4 Minimum Mob Wave Number", HIVEMIND_CATEGORY4, scentWaveMinMobWaveFour, 0, 100, ".");
        scentWaveMaxMobWaveFour = cfg.getInt("Scent Level 4 Maximum Mob Wave Number", HIVEMIND_CATEGORY4, scentWaveMaxMobWaveFour, 0, 100, ".");
        scentLevelPointsFour = cfg.getInt("Scent Level 4 Points Required", HIVEMIND_CATEGORY4, scentLevelPointsFour, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl5Config(Configuration cfg) {
        scentLevelFive = cfg.getStringList("Scent Level 5 Mob Table", HIVEMIND_CATEGORY5, scentLevelFive, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumFive = cfg.getInt("Scent Level 5 Minimum Wave Number", HIVEMIND_CATEGORY5, scentWaveMinimumFive, 0, 100, ".");
        scentWaveMaximumFive = cfg.getInt("Scent Level 5 Maximum Wave Number", HIVEMIND_CATEGORY5, scentWaveMaximumFive, 0, 100, ".");
        scentWaveMinMobWaveFive = cfg.getInt("Scent Level 5 Minimum Mob Wave Number", HIVEMIND_CATEGORY5, scentWaveMinMobWaveFive, 0, 100, ".");
        scentWaveMaxMobWaveFive = cfg.getInt("Scent Level 5 Maximum Mob Wave Number", HIVEMIND_CATEGORY5, scentWaveMaxMobWaveFive, 0, 100, ".");
        scentLevelPointsFive = cfg.getInt("Scent Level 5 Points Required", HIVEMIND_CATEGORY5, scentLevelPointsFive, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl6Config(Configuration cfg) {
        scentLevelSix = cfg.getStringList("Scent Level 6 Mob Table", HIVEMIND_CATEGORY6, scentLevelSix, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumSix = cfg.getInt("Scent Level 6 Minimum Wave Number", HIVEMIND_CATEGORY6, scentWaveMinimumSix, 0, 100, ".");
        scentWaveMaximumSix = cfg.getInt("Scent Level 6 Maximum Wave Number", HIVEMIND_CATEGORY6, scentWaveMaximumSix, 0, 100, ".");
        scentWaveMinMobWaveSix = cfg.getInt("Scent Level 6 Minimum Mob Wave Number", HIVEMIND_CATEGORY6, scentWaveMinMobWaveSix, 0, 100, ".");
        scentWaveMaxMobWaveSix = cfg.getInt("Scent Level 6 Maximum Mob Wave Number", HIVEMIND_CATEGORY6, scentWaveMaxMobWaveSix, 0, 100, ".");
        scentLevelPointsSix = cfg.getInt("Scent Level 6 Points Required", HIVEMIND_CATEGORY6, scentLevelPointsSix, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl7Config(Configuration cfg) {
        scentLevelSeven = cfg.getStringList("Scent Level 7 Mob Table", HIVEMIND_CATEGORY7, scentLevelSeven, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumSeven = cfg.getInt("Scent Level 7 Minimum Wave Number", HIVEMIND_CATEGORY7, scentWaveMinimumSeven, 0, 100, ".");
        scentWaveMaximumSeven = cfg.getInt("Scent Level 7 Maximum Wave Number", HIVEMIND_CATEGORY7, scentWaveMaximumSeven, 0, 100, ".");
        scentWaveMinMobWaveSeven = cfg.getInt("Scent Level 7 Minimum Mob Wave Number", HIVEMIND_CATEGORY7, scentWaveMinMobWaveSeven, 0, 100, ".");
        scentWaveMaxMobWaveSeven = cfg.getInt("Scent Level 7 Maximum Mob Wave Number", HIVEMIND_CATEGORY7, scentWaveMaxMobWaveSeven, 0, 100, ".");
        scentLevelPointsSeven = cfg.getInt("Scent Level 7 Points Required", HIVEMIND_CATEGORY7, scentLevelPointsSeven, 0, 1000000, ".");
    }

    private static void inithivemindscentlvl8Config(Configuration cfg) {
        scentLevelEight = cfg.getStringList("Scent Level 8 Mob Table", HIVEMIND_CATEGORY8, scentLevelEight, "Mobs the Scent will spawn at level 0");
        scentWaveMinimumEight = cfg.getInt("Scent Level 8 Minimum Wave Number", HIVEMIND_CATEGORY8, scentWaveMinimumEight, 0, 100, ".");
        scentWaveMaximumEight = cfg.getInt("Scent Level 8 Maximum Wave Number", HIVEMIND_CATEGORY8, scentWaveMaximumEight, 0, 100, ".");
        scentWaveMinMobWaveEight = cfg.getInt("Scent Level 8 Minimum Mob Wave Number", HIVEMIND_CATEGORY8, scentWaveMinMobWaveEight, 0, 100, ".");
        scentWaveMaxMobWaveEight = cfg.getInt("Scent Level 8 Maximum Mob Wave Number", HIVEMIND_CATEGORY8, scentWaveMaxMobWaveEight, 0, 100, ".");
        scentLevelPointsEight = cfg.getInt("Scent Level 8 Points Required", HIVEMIND_CATEGORY8, scentLevelPointsEight, 0, 1000000, ".");
    }

    private static void inittotaldevelopmentConfig(Configuration cfg) {
        String levelDescription = " \n In Ubiquitous Development, the progress of the parasites is undivided, regardless of the phases \n they will have at their disposal elements that will aid their dominance\n \n This works in levels and these levels increase or decrease depending on the progress of the phases in each dimension\n and how many dimensions are infected\n \n The points needed for each level are calculated by adding the current phase of each dimension\n ";
        cfg.addCustomCategoryComment(TOTALDEVELOPMENT_CATEGORY, "Ubiquitous Development" + levelDescription);
        deveMobChance = cfg.getFloat("Ubiquitous Development Mob Chance", TOTALDEVELOPMENT_CATEGORY, (float)deveMobChance, 0.0f, 1.0f, "Chance to swap Evolution mob list to Ubiquitous mob list");
        deveDisloUse = cfg.getInt("Ubiquitous Development Dislodgment", TOTALDEVELOPMENT_CATEGORY, deveDisloUse, 0, 100, "From this level on, Dislodgment System will be active.");
        deveMergeUse = cfg.getInt("Ubiquitous Development Merge", TOTALDEVELOPMENT_CATEGORY, deveMergeUse, 0, 100, "From this level on, Assimilated will be able to merge.");
        deveOnemindUse = cfg.getInt("Ubiquitous Development Collective Consciousness", TOTALDEVELOPMENT_CATEGORY, deveOnemindUse, 0, 100, "From this level on, CC will be active.");
        deveScentUse = cfg.getInt("Ubiquitous Development Scent", TOTALDEVELOPMENT_CATEGORY, deveScentUse, 0, 100, "From this level on, Scent will be active.");
        deveOriginlessUse = cfg.getInt("Ubiquitous Development Vectorless", TOTALDEVELOPMENT_CATEGORY, deveOriginlessUse, 0, 100, "From this level on, Parasites will be able to spawn outside of vectors.");
        deveColoniesUse = cfg.getInt("Ubiquitous Development Colonies", TOTALDEVELOPMENT_CATEGORY, deveColoniesUse, 0, 100, "From this level on, Colonies will be constructed.");
        deveNodesUse = cfg.getInt("Ubiquitous Development Nodes", TOTALDEVELOPMENT_CATEGORY, deveNodesUse, 0, 100, "From this level on, Nodes will spread in the world.");
        deveHivesUse = cfg.getInt("Ubiquitous Development Hives", TOTALDEVELOPMENT_CATEGORY, deveHivesUse, 0, 100, "From this level on, Hives will be constructed.");
        deveNestsUse = cfg.getInt("Ubiquitous Development Nests", TOTALDEVELOPMENT_CATEGORY, deveNestsUse, 0, 100, "From this level on, Dispatcher/Rooters will be able to spawn.");
        deveAlwaysVariantUse = cfg.getInt("Ubiquitous Development Variants", TOTALDEVELOPMENT_CATEGORY, deveAlwaysVariantUse, 0, 100, "From this level on, variants will be common.");
    }

    private static void inittotaldevelopmentOneConfig(Configuration cfg) {
        deveMiniDimsOne = cfg.getInt("Development Level 1 Minimum Dimensions", TOTALDEVELOPMENT_CATEGORY1, deveMiniDimsOne, 0, 100, "Number of infected dimensions requierd to reach this level.");
        devePointsOne = cfg.getInt("Development Level 1 Points", TOTALDEVELOPMENT_CATEGORY1, devePointsOne, 0, 100, "Points required to reach this level.");
        deveSpawnEntryUDOne = cfg.getStringList("Development Level 1 Mob List", HIVEMIND_CATEGORY0, deveSpawnEntryUDOne, "Mob list used in this level");
    }

    private static void inittotaldevelopmentTwoConfig(Configuration cfg) {
        deveMiniDimsTwo = cfg.getInt("Development Level 2 Minimum Dimensions", TOTALDEVELOPMENT_CATEGORY1, deveMiniDimsTwo, 0, 100, "Number of infected dimensions requierd to reach this level.");
        devePointsTwo = cfg.getInt("Development Level 2 Points", TOTALDEVELOPMENT_CATEGORY1, devePointsTwo, 0, 100, "Points required to reach this level.");
        deveSpawnEntryUDTwo = cfg.getStringList("Development Level 2 Mob List", HIVEMIND_CATEGORY0, deveSpawnEntryUDTwo, "Mob list used in this level");
    }

    private static void inittotaldevelopmentThreeConfig(Configuration cfg) {
        deveMiniDimsThree = cfg.getInt("Development Level 3 Minimum Dimensions", TOTALDEVELOPMENT_CATEGORY1, deveMiniDimsThree, 0, 100, "Number of infected dimensions requierd to reach this level.");
        devePointsThree = cfg.getInt("Development Level 3 Points", TOTALDEVELOPMENT_CATEGORY1, devePointsThree, 0, 100, "Points required to reach this level.");
        deveSpawnEntryUDThree = cfg.getStringList("Development Level 3 Mob List", HIVEMIND_CATEGORY0, deveSpawnEntryUDThree, "Mob list used in this level");
    }

    private static void inittotaldevelopmentFourConfig(Configuration cfg) {
        deveMiniDimsFour = cfg.getInt("Development Level 4 Minimum Dimensions", TOTALDEVELOPMENT_CATEGORY1, deveMiniDimsFour, 0, 100, "Number of infected dimensions requierd to reach this level.");
        devePointsFour = cfg.getInt("Development Level 4 Points", TOTALDEVELOPMENT_CATEGORY1, devePointsFour, 0, 100, "Points required to reach this level.");
        deveSpawnEntryUDFour = cfg.getStringList("Development Level 4 Mob List", HIVEMIND_CATEGORY0, deveSpawnEntryUDFour, "Mob list used in this level");
    }

    private static void initdisloMainConfig(Configuration cfg) {
        String dess = " \n List of triggers IDs \n \n 0 - Rightclicking a block\n 1 - Picking up xp\n 2 - Picking up an item\n 3 - Player Healing\n 4 - Drawing a bow, eating Food, Drinking Potions/Milk\n 5 - Closing UI\n 10 - Parasite Death\n 11 - Parasite Block Break\n 12 - Parasite Nexus Stage I Death\n 13 - Parasite Nexus Stage II Death\n 14 - Parasite Nexus Stage III Death\n 15 - Parasite Nexus Stage IV Death\n 16 - Purifier Block Tick\n 17 - Parasite Node Core Block Break\n 18 - Parasite Colony Core Block Break\n \n List of current codes and effects:\n \n --0-- \n COTH effect will ignore amplifier value and will trigger itself at max level\n Code value is not important\n \n --1-- \n COTH will spawn higher tier parasites depending of code value\n Higher code value is equal to higher level\n -1st level: Feral versions will spawn\n -2nd level: Primitive versions will spawn\n -3rd level: Adapted versions will spawn\n -4th and last level: Pure versions will spawn\n \n --2-- \n Killing parasites will spawn higher tier unit(s)\n Code value are deaths required\n \n --3-- \n Parasites spawn with potion effects\n Code value is amplifier of the effect\n \n --4-- \n Parasites will spawn with more health/damage/armor\n Code value is multiplier\n \n --5-- \n Owo\n \n --6-- \n Parasites will consume more item durability\n Code value is multiplier\n -When attacking parasites, your mainhand/offhand will lose extra durability\n -When attacked by parasites, your amor will lose extra durability\n \n --7-- \n Parasites when killed, will heal nearby parasites\n Code value is amount of healing\n \n --8-- \n Parasites when killed, will damage nearby non-parasite mobs\n Code value is damage dealt\n \n --9-- \n Parasites when killed, will drain food of nearby players\n Code value is amount to drain\n \n --10-- \n Killing infected versions can spawn a higer tier parasite unit\n Higher code value is equal to higher level\n -1st level: Primitive will spawn\n -2nd level: Adapted version will spawn\n -3rd and last level: Pure version will spawn\n \n --11-- \n Parasites now are immune to negative potion effects\n Code value is not important\n \n --12-- \n All non-parasite mobs will have their health drained\n Code value is amount \n \n --13-- \n All players will have their food drained\n Code value is amount\n \n --14-- \n Evolution phase will use next phase spawn list\n Code value will add up to the current phase mob list\n \n --15-- \n Parasite growl noise cancelled\n Code value is not important\n \n --16-- \n Parasite walk noise cancelled\n Code value is not important\n \n --17-- \n Parasites will disable shields and spoil food\n Code value is not important\n \n --18-- \n Parasite when killed, will not drop loot, xp\n Code value is not important\n \n --19-- \n Parasite killcount will increase overtime\n Code value is amount\n \n --20-- \n Walking Heads/Moving Flesh/Remains/Incomplete Forms will get a new body\n Code value is not important\n \n --21-- \n Parasites cannot be killef unless they are burning\n Code value is not important\n \n --22-- \n \n \n --23-- \n \n \n --24-- \n \n \n --25-- \n Parasites have a chance to spawn when breaking parasite blocks\n Higher code value is equal to higher level\n -1st level: Assimilated versions will spawn\n -2nd level: Feral versions will spawn\n -3rd level: Primitive versions will spawn\n -4th level: Adapted versions will spawn\n -5th and last level: Pure versions will spawn\n ";
        cfg.addCustomCategoryComment(DISLO_CATEGORY, "Dislodgment" + dess);
        chanceEventRightClickB = cfg.getFloat("Trigger RightClick Block", DISLO_CATEGORY, (float)chanceEventRightClickB, 0.0f, 1.0f, "Trigger 0.");
        chanceEventXPPickUp = cfg.getFloat("Trigger XP Pickup", DISLO_CATEGORY, (float)chanceEventXPPickUp, 0.0f, 1.0f, "Trigger 1.");
        chanceEventItemPickUp = cfg.getFloat("Trigger Item Pickup", DISLO_CATEGORY, (float)chanceEventItemPickUp, 0.0f, 1.0f, "Trigger 2.");
        chanceEventHealing = cfg.getFloat("Trigger Healing", DISLO_CATEGORY, (float)chanceEventHealing, 0.0f, 1.0f, "Trigger 3.");
        chanceEventUsteItem = cfg.getFloat("Trigger Item Use", DISLO_CATEGORY, (float)chanceEventUsteItem, 0.0f, 1.0f, "Trigger 4.");
        chanceEventMEnuClose = cfg.getFloat("Trigger UI Close", DISLO_CATEGORY, (float)chanceEventMEnuClose, 0.0f, 1.0f, "Trigger 5.");
        chanceEventParaDeath = cfg.getFloat("Trigger Parasite Death", DISLO_CATEGORY, (float)chanceEventParaDeath, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 10.");
        chanceEventParaBlockB = cfg.getFloat("Trigger Parasite Block Break", DISLO_CATEGORY, (float)chanceEventParaBlockB, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 11.");
        chanceEventParaNexusID = cfg.getFloat("Trigger Parasite Nexus I Death", DISLO_CATEGORY, (float)chanceEventParaNexusID, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 12.");
        chanceEventParaNexusIID = cfg.getFloat("Trigger Parasite Nexus II Death", DISLO_CATEGORY, (float)chanceEventParaNexusIID, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 13.");
        chanceEventParaNexusIIID = cfg.getFloat("Trigger Parasite Nexus III Death", DISLO_CATEGORY, (float)chanceEventParaNexusIIID, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 14.");
        chanceEventParaNexusIVD = cfg.getFloat("Trigger Parasite Nexus IV Death", DISLO_CATEGORY, (float)chanceEventParaNexusIVD, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 15.");
        chanceEventParaPurifier = cfg.getFloat("Trigger Purifier", DISLO_CATEGORY, (float)chanceEventParaPurifier, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 16.");
        chanceEventParaNodeC = cfg.getFloat("Trigger Parasite Node Core Break", DISLO_CATEGORY, (float)chanceEventParaNodeC, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 17.");
        chanceEventParaColonyC = cfg.getFloat("Trigger Parasite Colony Core Break", DISLO_CATEGORY, (float)chanceEventParaColonyC, 0.0f, 1.0f, "Chance (1=100%) to activate Trigger 18.");
        disloGlobalCooldown = cfg.getInt("Dislo Global Cooldown", DISLO_CATEGORY, disloGlobalCooldown, 0, 1000000, "Global cooldown in ticks.");
        disloSeconds = cfg.getInt("Dislo Update Cooldown", DISLO_CATEGORY, disloSeconds, 0, 500000, "Update cooldown for codes.");
        disloCOTHSpy = cfg.getInt("Dislo COTH Spying", DISLO_CATEGORY, disloCOTHSpy, 0, 70, "Non-parasite triggers need this number of mobs with coth for them to work (radius of 5 check).");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 000 Trigger List", disloCOTHIgnoreAmpEH, "List of triggers where dislodgment number 0 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 001 Trigger List", disloCOTHTiersEH, "List of triggers where dislodgment number 1 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 002 Trigger List", disloSummonByDeathEH, "List of triggers where dislodgment number 2 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 003 Trigger List", disloPotiEffEH, "List of triggers where dislodgment number 3 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 004 Trigger List", dislostatsEH, "List of triggers where dislodgment number 4 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 005 Trigger List", disloDeathRaidEH, "List of triggers where dislodgment number 5 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 006 Trigger List", disloItemDuraEH, "List of triggers where dislodgment number 6 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 007 Trigger List", disloHealingDeathEH, "List of triggers where dislodgment number 7 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 008 Trigger List", disloDamageDeathEH, "List of triggers where dislodgment number 8 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 009 Trigger List", disloFoodDeathEH, "List of triggers where dislodgment number 9 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 010 Trigger List", disloDeathHighVerionsEH, "List of triggers where dislodgment number 10 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 011 Trigger List", disloParasiteNoPotionEH, "List of triggers where dislodgment number 11 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 012 Trigger List", disloHealthDrainingEH, "List of triggers where dislodgment number 12 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 013 Trigger List", disloFoodDrainingEH, "List of triggers where dislodgment number 13 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 014 Trigger List", disloNextPhaseLEH, "List of triggers where dislodgment number 14 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 015 Trigger List", disloGrowlNoiseEH, "List of triggers where dislodgment number 15 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 016 Trigger List", disloWalkNoiseEH, "List of triggers where dislodgment number 16 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 017 Trigger List", disloShieldFoodEH, "List of triggers where dislodgment number 17 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 018 Trigger List", disloLootXpCancEH, "List of triggers where dislodgment number 18 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 019 Trigger List", disloKillcountIncEH, "List of triggers where dislodgment number 19 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 020 Trigger List", disloGiveBodiesEH, "List of triggers where dislodgment number 20 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 021 Trigger List", disloPhasePobyteEH, "List of triggers where dislodgment number 21 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 022 Trigger List", disloNodeColoNoLimitEH, "List of triggers where dislodgment number 22 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 023 Trigger List", disloColonyNoLimitEH, "List of triggers where dislodgment number 23 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 024 Trigger List", disloNexusGrowthEH, "List of triggers where dislodgment number 24 can trigger");
        SPConfigSystems.getByteVal(cfg, DISLO_CATEGORY, "Dislo 025 Trigger List", disloParasiteBlockEH, "List of triggers where dislodgment number 25 can trigger");
    }

    private static void initdislo000Config(Configuration cfg) {
        disloCOTHIgnoreAmp = cfg.getBoolean("Dislo 0 Enabled", DISLO_CATEGORY0, disloCOTHIgnoreAmp, "Set to false to disable this");
        disloCOTHIgnoreAmpPrice = cfg.getInt("Dislo 0 Point Cost", DISLO_CATEGORY0, disloCOTHIgnoreAmpPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloCOTHIgnoreAmpDuration = cfg.getInt("Dislo 0 Duration", DISLO_CATEGORY0, disloCOTHIgnoreAmpDuration, 0, 2000000000, "Base duration in seconds.");
        disloCOTHIgnoreAmpCooldown = cfg.getInt("Dislo 0 Point Cooldown", DISLO_CATEGORY0, disloCOTHIgnoreAmpCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloCOTHIgnoreAmpStartMess = cfg.getString("Dislo 0 Start", DISLO_CATEGORY0, disloCOTHIgnoreAmpStartMess, "Starting message ");
        disloCOTHIgnoreAmpEndMess = cfg.getString("Dislo 0 End", DISLO_CATEGORY0, disloCOTHIgnoreAmpEndMess, "Ending message");
    }

    private static void initdislo001Config(Configuration cfg) {
        disloCOTHTiers = cfg.getBoolean("Dislo 1 Enabled", DISLO_CATEGORY1, disloCOTHTiers, "Set to false to disable this");
        disloCOTHTiersPrice = cfg.getInt("Dislo 1 Point Cost", DISLO_CATEGORY1, disloCOTHTiersPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloCOTHTiersValue = cfg.getInt("Dislo 1 Value", DISLO_CATEGORY1, disloCOTHTiersValue, 0, 2000000000, "Base Value, will spawn Feral version.");
        disloCOTHTiersValue1 = cfg.getInt("Dislo 1 Value Primitive", DISLO_CATEGORY1, disloCOTHTiersValue1, 0, 2000000000, "Minimum value for Primitive version.");
        disloCOTHTiersValue2 = cfg.getInt("Dislo 1 Value Adapted", DISLO_CATEGORY1, disloCOTHTiersValue2, 0, 2000000000, "Minimum Value for Adapted version.");
        disloCOTHTiersValue3 = cfg.getInt("Dislo 1 Value Pure", DISLO_CATEGORY1, disloCOTHTiersValue3, 0, 2000000000, "Minimum Value for Pure version.");
        disloCOTHTiersDuration = cfg.getInt("Dislo 1 Duration", DISLO_CATEGORY1, disloCOTHTiersDuration, 0, 2000000000, "Base duration in seconds.");
        disloCOTHTiersCooldown = cfg.getInt("Dislo 1 Point Cooldown", DISLO_CATEGORY1, disloCOTHTiersCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloCOTHTiersStartMess = cfg.getString("Dislo 1 Start", DISLO_CATEGORY1, disloCOTHTiersStartMess, "Starting message ");
        disloCOTHTiersEndMess = cfg.getString("Dislo 1 End", DISLO_CATEGORY1, disloCOTHTiersEndMess, "Ending message");
    }

    private static void initdislo002Config(Configuration cfg) {
        disloSummonByDeath = cfg.getBoolean("Dislo 2 Enabled", DISLO_CATEGORY2, disloSummonByDeath, "Set to false to disable this");
        disloSummonByDeathPrice = cfg.getInt("Dislo 2 Point Cost", DISLO_CATEGORY2, disloSummonByDeathPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloSummonByDeathValue = cfg.getInt("Dislo 2 Value", DISLO_CATEGORY2, disloSummonByDeathValue, 0, 2000000000, "Base Value.");
        disloSummonByDeathDuration = cfg.getInt("Dislo 2 Duration", DISLO_CATEGORY2, disloSummonByDeathDuration, 0, 2000000000, "Base duration in seconds.");
        disloSummonByDeathCooldown = cfg.getInt("Dislo 2 Point Cooldown", DISLO_CATEGORY2, disloSummonByDeathCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloSummonByDeathKilling = cfg.getInt("Dislo 2 Deaths Requires", DISLO_CATEGORY2, disloSummonByDeathKilling, 0, 2000000000, "Parasite Death couter needed.");
        disloSummonByDeathStartMess = cfg.getString("Dislo 2 Start", DISLO_CATEGORY2, disloSummonByDeathStartMess, "Starting message ");
        disloSummonByDeathEndMess = cfg.getString("Dislo 2 End", DISLO_CATEGORY2, disloSummonByDeathEndMess, "Ending message");
        disloSummonByDeathMobs = cfg.getStringList("Dislo 2 Mob Table", DISLO_CATEGORY2, disloSummonByDeathMobs, "Mobs Dislo will spawn, the value is the mob");
    }

    private static void initdislo003Config(Configuration cfg) {
        disloPotiEff = cfg.getBoolean("Dislo 3 Enabled", DISLO_CATEGORY3, disloPotiEff, "Set to false to disable this");
        disloPotiEffPrice = cfg.getInt("Dislo 3 Point Cost", DISLO_CATEGORY3, disloPotiEffPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloPotiEffValue = cfg.getInt("Dislo 3 Value", DISLO_CATEGORY3, disloPotiEffValue, 0, 2000000000, "Base Value.");
        disloPotiEffDuration = cfg.getInt("Dislo 3 Duration", DISLO_CATEGORY3, disloPotiEffDuration, 0, 2000000000, "Base duration in seconds.");
        disloPotiEffCooldown = cfg.getInt("Dislo 3 Point Cooldown", DISLO_CATEGORY3, disloPotiEffCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloPotiEffStartMess = cfg.getString("Dislo 3 Start", DISLO_CATEGORY3, disloPotiEffStartMess, "Starting message ");
        disloPotiEffEndMess = cfg.getString("Dislo 3 End", DISLO_CATEGORY3, disloPotiEffEndMess, "Ending message");
        disloPotiEffEffects = cfg.getStringList("Dislo 3 Effect Table", DISLO_CATEGORY3, disloPotiEffEffects, "Effects that will be used");
    }

    private static void initdislo004Config(Configuration cfg) {
        dislostats = cfg.getBoolean("Dislo 4 Enabled", DISLO_CATEGORY4, dislostats, "Set to false to disable this");
        dislostatsPrice = cfg.getInt("Dislo 4 Point Cost", DISLO_CATEGORY4, dislostatsPrice, 0, 2000000000, "Base Evolution Point Cost.");
        dislostatsValue = cfg.getInt("Dislo 4 Value", DISLO_CATEGORY4, dislostatsValue, 0, 2000000000, "Base Value.");
        dislostatsDuration = cfg.getInt("Dislo 4 Duration", DISLO_CATEGORY4, dislostatsDuration, 0, 2000000000, "Base duration in seconds.");
        dislostatsCooldown = cfg.getInt("Dislo 4 Point Cooldown", DISLO_CATEGORY4, dislostatsCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloStatsStartMess = cfg.getString("Dislo 4 Start", DISLO_CATEGORY4, disloStatsStartMess, "Starting message ");
        disloStatsEndMess = cfg.getString("Dislo 4 End", DISLO_CATEGORY4, disloStatsEndMess, "Ending message");
    }

    private static void initdislo005Config(Configuration cfg) {
        disloDeathRaid = cfg.getBoolean("Dislo 5 Enabled", DISLO_CATEGORY5, disloDeathRaid, "Set to false to disable this");
        disloDeathRaidPrice = cfg.getInt("Dislo 5 Point Cost", DISLO_CATEGORY5, disloDeathRaidPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloDeathRaidValue = cfg.getInt("Dislo 5 Value", DISLO_CATEGORY5, disloDeathRaidValue, 0, 2000000000, "Base Value.");
        disloDeathRaidDuration = cfg.getInt("Dislo 5 Duration", DISLO_CATEGORY5, disloDeathRaidDuration, 0, 2000000000, "Base duration in seconds.");
        disloDeathRaidCooldown = cfg.getInt("Dislo 5 Point Cooldown", DISLO_CATEGORY5, disloDeathRaidCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloDeathRaidS = cfg.getString("Dislo 5 Start", DISLO_CATEGORY5, disloDeathRaidS, "Starting message ");
        disloDeathRaidE = cfg.getString("Dislo 5 End", DISLO_CATEGORY5, disloDeathRaidE, "Ending message");
    }

    private static void initdislo006Config(Configuration cfg) {
        disloItemDura = cfg.getBoolean("Dislo 6 Enabled", DISLO_CATEGORY6, disloItemDura, "Set to false to disable this");
        disloItemDuraPrice = cfg.getInt("Dislo 6 Point Cost", DISLO_CATEGORY6, disloItemDuraPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloItemDuraValue = cfg.getInt("Dislo 6 Value", DISLO_CATEGORY6, disloItemDuraValue, 0, 2000000000, "Base Value.");
        disloItemDuraDuration = cfg.getInt("Dislo 6 Duration", DISLO_CATEGORY6, disloItemDuraDuration, 0, 2000000000, "Base duration in seconds.");
        disloItemDuraCooldown = cfg.getInt("Dislo 6 Point Cooldown", DISLO_CATEGORY6, disloItemDuraCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloItemDuraS = cfg.getString("Dislo 6 Start", DISLO_CATEGORY6, disloItemDuraS, "Starting message ");
        disloItemDuraE = cfg.getString("Dislo 6 End", DISLO_CATEGORY6, disloItemDuraE, "Ending message");
    }

    private static void initdislo007Config(Configuration cfg) {
        disloHealingDeath = cfg.getBoolean("Dislo 7 Enabled", DISLO_CATEGORY7, disloHealingDeath, "Set to false to disable this");
        disloHealingDeathPrice = cfg.getInt("Dislo 7 Point Cost", DISLO_CATEGORY7, disloHealingDeathPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloHealingDeathValue = cfg.getInt("Dislo 7 Value", DISLO_CATEGORY7, disloHealingDeathValue, 0, 2000000000, "Base Value.");
        disloHealingDeathDuration = cfg.getInt("Dislo 7 Duration", DISLO_CATEGORY7, disloHealingDeathDuration, 0, 2000000000, "Base duration in seconds.");
        disloHealingDeathCooldown = cfg.getInt("Dislo 7 Point Cooldown", DISLO_CATEGORY7, disloHealingDeathCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloHealingDeathS = cfg.getString("Dislo 7 Start", DISLO_CATEGORY7, disloHealingDeathS, "Starting message ");
        disloHealingDeathE = cfg.getString("Dislo 7 End", DISLO_CATEGORY7, disloHealingDeathE, "Ending message");
    }

    private static void initdislo008Config(Configuration cfg) {
        disloDamageDeath = cfg.getBoolean("Dislo 8 Enabled", DISLO_CATEGORY8, disloDamageDeath, "Set to false to disable this");
        disloDamageDeathPrice = cfg.getInt("Dislo 8 Point Cost", DISLO_CATEGORY8, disloDamageDeathPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloDamageDeathValue = cfg.getInt("Dislo 8 Value", DISLO_CATEGORY8, disloDamageDeathValue, 0, 2000000000, "Base Value.");
        disloDamageDeathDuration = cfg.getInt("Dislo 8 Duration", DISLO_CATEGORY8, disloDamageDeathDuration, 0, 2000000000, "Base duration in seconds.");
        disloDamageDeathCooldown = cfg.getInt("Dislo 8 Point Cooldown", DISLO_CATEGORY8, disloDamageDeathCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloDamageDeathS = cfg.getString("Dislo 8 Start", DISLO_CATEGORY8, disloDamageDeathS, "Starting message ");
        disloDamageDeathE = cfg.getString("Dislo 8 End", DISLO_CATEGORY8, disloDamageDeathE, "Ending message");
    }

    private static void initdislo009Config(Configuration cfg) {
        disloFoodDeath = cfg.getBoolean("Dislo 9 Enabled", DISLO_CATEGORY9, disloFoodDeath, "Set to false to disable this");
        disloFoodDeathPrice = cfg.getInt("Dislo 9 Point Cost", DISLO_CATEGORY9, disloFoodDeathPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloFoodDeathValue = cfg.getInt("Dislo 9 Value", DISLO_CATEGORY9, disloFoodDeathValue, 0, 2000000000, "Base Value.");
        disloFoodDeathDuration = cfg.getInt("Dislo 9 Duration", DISLO_CATEGORY9, disloFoodDeathDuration, 0, 2000000000, "Base duration in seconds.");
        disloFoodDeathCooldown = cfg.getInt("Dislo 9 Point Cooldown", DISLO_CATEGORY9, disloFoodDeathCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloFoodDeathS = cfg.getString("Dislo 9 Start", DISLO_CATEGORY9, disloFoodDeathS, "Starting message ");
        disloFoodDeathE = cfg.getString("Dislo 9 End", DISLO_CATEGORY9, disloFoodDeathE, "Ending message");
    }

    private static void initdislo010Config(Configuration cfg) {
        disloDeathHighVerions = cfg.getBoolean("Dislo 10 Enabled", DISLO_CATEGORY10, disloDeathHighVerions, "Set to false to disable this");
        disloDeathHighVerionsPrice = cfg.getInt("Dislo 10 Point Cost", DISLO_CATEGORY10, disloDeathHighVerionsPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloDeathHighVerionsValue = cfg.getInt("Dislo 10 Value", DISLO_CATEGORY10, disloDeathHighVerionsValue, 0, 2000000000, "Base Value, will spawn Primitive version.");
        disloDeathHighVerionsValue1 = cfg.getInt("Dislo 10 Value Adapted", DISLO_CATEGORY10, disloDeathHighVerionsValue1, 0, 2000000000, "Minimum value for Adapted version.");
        disloDeathHighVerionsValue2 = cfg.getInt("Dislo 10 Value Pure", DISLO_CATEGORY10, disloDeathHighVerionsValue2, 0, 2000000000, "Minimum value for Pure version.");
        disloDeathHighVerionsDuration = cfg.getInt("Dislo 10 Duration", DISLO_CATEGORY10, disloDeathHighVerionsDuration, 0, 2000000000, "Base duration in seconds.");
        disloDeathHighVerionsCooldown = cfg.getInt("Dislo 10 Point Cooldown", DISLO_CATEGORY10, disloDeathHighVerionsCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloDeathHighVerionsS = cfg.getString("Dislo 10 Start", DISLO_CATEGORY10, disloDeathHighVerionsS, "Starting message ");
        disloDeathHighVerionsE = cfg.getString("Dislo 10 End", DISLO_CATEGORY10, disloDeathHighVerionsE, "Ending message");
        disloDeathHighVerionsChance = cfg.getFloat("Dislo 10 Chance", DISLO_CATEGORY10, (float)disloDeathHighVerionsChance, 0.0f, 1.0f, "Chance 1=100% to spawn a Parasite.");
    }

    private static void initdislo011Config(Configuration cfg) {
        disloParasiteNoPotion = cfg.getBoolean("Dislo 11 Enabled", DISLO_CATEGORY11, disloParasiteNoPotion, "Set to false to disable this");
        disloParasiteNoPotionPrice = cfg.getInt("Dislo 11 Point Cost", DISLO_CATEGORY11, disloParasiteNoPotionPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloParasiteNoPotionDuration = cfg.getInt("Dislo 11 Duration", DISLO_CATEGORY11, disloParasiteNoPotionDuration, 0, 2000000000, "Base duration in seconds.");
        disloParasiteNoPotionCooldown = cfg.getInt("Dislo 11 Point Cooldown", DISLO_CATEGORY11, disloParasiteNoPotionCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloParasiteNoPotionS = cfg.getString("Dislo 11 Start", DISLO_CATEGORY11, disloParasiteNoPotionS, "Starting message ");
        disloParasiteNoPotionE = cfg.getString("Dislo 11 End", DISLO_CATEGORY11, disloParasiteNoPotionE, "Ending message");
    }

    private static void initdislo012Config(Configuration cfg) {
        disloHealthDraining = cfg.getBoolean("Dislo 12 Enabled", DISLO_CATEGORY12, disloHealthDraining, "Set to false to disable this");
        disloHealthDrainingPrice = cfg.getInt("Dislo 12 Point Cost", DISLO_CATEGORY12, disloHealthDrainingPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloHealthDrainingValue = cfg.getInt("Dislo 12 Value", DISLO_CATEGORY12, disloHealthDrainingValue, 0, 2000000000, "Base Value.");
        disloHealthDrainingDuration = cfg.getInt("Dislo 12 Duration", DISLO_CATEGORY12, disloHealthDrainingDuration, 0, 2000000000, "Base duration in seconds.");
        disloHealthDrainingCooldown = cfg.getInt("Dislo 12 Point Cooldown", DISLO_CATEGORY12, disloHealthDrainingCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloHealthDrainingS = cfg.getString("Dislo 12 Start", DISLO_CATEGORY12, disloHealthDrainingS, "Starting message ");
        disloHealthDrainingE = cfg.getString("Dislo 12 End", DISLO_CATEGORY12, disloHealthDrainingE, "Ending message");
    }

    private static void initdislo013Config(Configuration cfg) {
        disloFoodDraining = cfg.getBoolean("Dislo 13 Enabled", DISLO_CATEGORY13, disloFoodDraining, "Set to false to disable this");
        disloFoodDrainingPrice = cfg.getInt("Dislo 13 Point Cost", DISLO_CATEGORY13, disloFoodDrainingPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloFoodDrainingValue = cfg.getInt("Dislo 13 Value", DISLO_CATEGORY13, disloFoodDrainingValue, 0, 2000000000, "Base Value.");
        disloFoodDrainingDuration = cfg.getInt("Dislo 13 Duration", DISLO_CATEGORY13, disloFoodDrainingDuration, 0, 2000000000, "Base duration in seconds.");
        disloFoodDrainingCooldown = cfg.getInt("Dislo 13 Point Cooldown", DISLO_CATEGORY13, disloFoodDrainingCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloFoodDrainingS = cfg.getString("Dislo 13 Start", DISLO_CATEGORY13, disloFoodDrainingS, "Starting message ");
        disloFoodDrainingE = cfg.getString("Dislo 13 End", DISLO_CATEGORY13, disloFoodDrainingE, "Ending message");
    }

    private static void initdislo014Config(Configuration cfg) {
        disloNextPhaseL = cfg.getBoolean("Dislo 14 Enabled", DISLO_CATEGORY14, disloNextPhaseL, "Set to false to disable this");
        disloNextPhaseLPrice = cfg.getInt("Dislo 14 Point Cost", DISLO_CATEGORY14, disloNextPhaseLPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloNextPhaseLValue = cfg.getInt("Dislo 14 Value", DISLO_CATEGORY14, disloNextPhaseLValue, 0, 2000000000, "Base Value.");
        disloNextPhaseLDuration = cfg.getInt("Dislo 14 Duration", DISLO_CATEGORY14, disloNextPhaseLDuration, 0, 2000000000, "Base duration in seconds.");
        disloNextPhaseLCooldown = cfg.getInt("Dislo 14 Point Cooldown", DISLO_CATEGORY14, disloNextPhaseLCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloNextPhaseLS = cfg.getString("Dislo 14 Start", DISLO_CATEGORY14, disloNextPhaseLS, "Starting message ");
        disloNextPhaseLE = cfg.getString("Dislo 14 End", DISLO_CATEGORY14, disloNextPhaseLE, "Ending message");
    }

    private static void initdislo015Config(Configuration cfg) {
        disloGrowlNoise = cfg.getBoolean("Dislo 15 Enabled", DISLO_CATEGORY15, disloGrowlNoise, "Set to false to disable this");
        disloGrowlNoisePrice = cfg.getInt("Dislo 15 Point Cost", DISLO_CATEGORY15, disloGrowlNoisePrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloGrowlNoiseDuration = cfg.getInt("Dislo 15 Duration", DISLO_CATEGORY15, disloGrowlNoiseDuration, 0, 2000000000, "Base duration in seconds.");
        disloGrowlNoiseCooldown = cfg.getInt("Dislo 15 Point Cooldown", DISLO_CATEGORY15, disloGrowlNoiseCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloGrowlNoiseS = cfg.getString("Dislo 15 Start", DISLO_CATEGORY15, disloGrowlNoiseS, "Starting message ");
        disloGrowlNoiseE = cfg.getString("Dislo 15 End", DISLO_CATEGORY15, disloGrowlNoiseE, "Ending message");
    }

    private static void initdislo016Config(Configuration cfg) {
        disloWalkNoise = cfg.getBoolean("Dislo 16 Enabled", DISLO_CATEGORY16, disloWalkNoise, "Set to false to disable this");
        disloWalkNoisePrice = cfg.getInt("Dislo 16 Point Cost", DISLO_CATEGORY16, disloWalkNoisePrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloWalkNoiseDuration = cfg.getInt("Dislo 16 Duration", DISLO_CATEGORY16, disloWalkNoiseDuration, 0, 2000000000, "Base duration in seconds.");
        disloWalkNoiseCooldown = cfg.getInt("Dislo 16 Point Cooldown", DISLO_CATEGORY16, disloWalkNoiseCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloWalkNoiseS = cfg.getString("Dislo 16 Start", DISLO_CATEGORY16, disloWalkNoiseS, "Starting message ");
        disloWalkNoiseE = cfg.getString("Dislo 16 End", DISLO_CATEGORY16, disloWalkNoiseE, "Ending message");
    }

    private static void initdislo017Config(Configuration cfg) {
        disloShieldFood = cfg.getBoolean("Dislo 17 Enabled", DISLO_CATEGORY17, disloShieldFood, "Set to false to disable this");
        disloShieldFoodPrice = cfg.getInt("Dislo 17 Point Cost", DISLO_CATEGORY17, disloShieldFoodPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloShieldFoodDuration = cfg.getInt("Dislo 17 Duration", DISLO_CATEGORY17, disloShieldFoodDuration, 0, 2000000000, "Base duration in seconds.");
        disloShieldFoodCooldown = cfg.getInt("Dislo 17 Point Cooldown", DISLO_CATEGORY17, disloShieldFoodCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloShieldFoodS = cfg.getString("Dislo 17 Start", DISLO_CATEGORY17, disloShieldFoodS, "Starting message ");
        disloShieldFoodE = cfg.getString("Dislo 17 End", DISLO_CATEGORY17, disloShieldFoodE, "Ending message");
    }

    private static void initdislo018Config(Configuration cfg) {
        disloLootXpCanc = cfg.getBoolean("Dislo 18 Enabled", DISLO_CATEGORY18, disloLootXpCanc, "Set to false to disable this");
        disloLootXpCancPrice = cfg.getInt("Dislo 18 Point Cost", DISLO_CATEGORY18, disloLootXpCancPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloLootXpCancDuration = cfg.getInt("Dislo 18 Duration", DISLO_CATEGORY18, disloLootXpCancDuration, 0, 2000000000, "Base duration in seconds.");
        disloLootXpCancCooldown = cfg.getInt("Dislo 18 Point Cooldown", DISLO_CATEGORY18, disloLootXpCancCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloLootXpCancS = cfg.getString("Dislo 18 Start", DISLO_CATEGORY18, disloLootXpCancS, "Starting message ");
        disloLootXpCancE = cfg.getString("Dislo 18 End", DISLO_CATEGORY18, disloLootXpCancE, "Ending message");
    }

    private static void initdislo019Config(Configuration cfg) {
        disloKillcountInc = cfg.getBoolean("Dislo 19 Enabled", DISLO_CATEGORY19, disloKillcountInc, "Set to false to disable this");
        disloKillcountIncPrice = cfg.getInt("Dislo 19 Point Cost", DISLO_CATEGORY19, disloKillcountIncPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloKillcountIncValue = cfg.getInt("Dislo 19 Value", DISLO_CATEGORY19, disloKillcountIncValue, 0, 2000000000, "Base Value.");
        disloKillcountIncDuration = cfg.getInt("Dislo 19 Duration", DISLO_CATEGORY19, disloKillcountIncDuration, 0, 2000000000, "Base duration in seconds.");
        disloKillcountIncCooldown = cfg.getInt("Dislo 19 Point Cooldown", DISLO_CATEGORY19, disloKillcountIncCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloKillcountIncS = cfg.getString("Dislo 19 Start", DISLO_CATEGORY19, disloKillcountIncS, "Starting message ");
        disloKillcountIncE = cfg.getString("Dislo 19 End", DISLO_CATEGORY19, disloKillcountIncE, "Ending message");
    }

    private static void initdislo020Config(Configuration cfg) {
        disloGiveBodies = cfg.getBoolean("Dislo 20 Enabled", DISLO_CATEGORY20, disloGiveBodies, "Set to false to disable this");
        disloGiveBodiesPrice = cfg.getInt("Dislo 20 Point Cost", DISLO_CATEGORY20, disloGiveBodiesPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloGiveBodiesDuration = cfg.getInt("Dislo 20 Duration", DISLO_CATEGORY20, disloGiveBodiesDuration, 0, 2000000000, "Base duration in seconds.");
        disloGiveBodiesCooldown = cfg.getInt("Dislo 20 Point Cooldown", DISLO_CATEGORY20, disloGiveBodiesCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloGiveBodiesS = cfg.getString("Dislo 20 Start", DISLO_CATEGORY20, disloGiveBodiesS, "Starting message ");
        disloGiveBodiesE = cfg.getString("Dislo 20 End", DISLO_CATEGORY20, disloGiveBodiesE, "Ending message");
    }

    private static void initdislo021Config(Configuration cfg) {
        disloBurningDeath = cfg.getBoolean("Dislo 21 Enabled", DISLO_CATEGORY21, disloBurningDeath, "Set to false to disable this");
        disloBurningDeathPrice = cfg.getInt("Dislo 21 Point Cost", DISLO_CATEGORY21, disloBurningDeathPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloBurningDeathDuration = cfg.getInt("Dislo 21 Duration", DISLO_CATEGORY21, disloBurningDeathDuration, 0, 2000000000, "Base duration in seconds.");
        disloBurningDeathCooldown = cfg.getInt("Dislo 21 Point Cooldown", DISLO_CATEGORY21, disloBurningDeathCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloBurningDeathS = cfg.getString("Dislo 21 Start", DISLO_CATEGORY21, disloBurningDeathS, "Starting message ");
        disloBurningDeathE = cfg.getString("Dislo 21 End", DISLO_CATEGORY21, disloBurningDeathE, "Ending message");
    }

    private static void initdislo022Config(Configuration cfg) {
        disloSameVersionDyeing = cfg.getBoolean("Dislo 22 Enabled", DISLO_CATEGORY22, disloSameVersionDyeing, "Set to false to disable this");
        disloSameVersionDyeingPrice = cfg.getInt("Dislo 22 Point Cost", DISLO_CATEGORY22, disloSameVersionDyeingPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloSameVersionDyeingValue = cfg.getInt("Dislo 22 Value", DISLO_CATEGORY22, disloSameVersionDyeingValue, 0, 2000000000, "Base Value.");
        disloSameVersionDyeingDuration = cfg.getInt("Dislo 22 Duration", DISLO_CATEGORY22, disloSameVersionDyeingDuration, 0, 2000000000, "Base duration in seconds.");
        disloSameVersionDyeingCooldown = cfg.getInt("Dislo 22 Point Cooldown", DISLO_CATEGORY22, disloSameVersionDyeingCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloSameVersionDyeingS = cfg.getString("Dislo 22 Start", DISLO_CATEGORY22, disloSameVersionDyeingS, "Starting message ");
        disloSameVersionDyeingE = cfg.getString("Dislo 22 End", DISLO_CATEGORY22, disloSameVersionDyeingE, "Ending message");
    }

    private static void initdislo023Config(Configuration cfg) {
        disloColonyNoLimit = cfg.getBoolean("Dislo 23 Enabled", DISLO_CATEGORY23, disloColonyNoLimit, "Set to false to disable this");
        disloColonyNoLimitPrice = cfg.getInt("Dislo 23 Point Cost", DISLO_CATEGORY23, disloColonyNoLimitPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloColonyNoLimitValue = cfg.getInt("Dislo 23 Value", DISLO_CATEGORY23, disloColonyNoLimitValue, 0, 2000000000, "Base Value.");
        disloColonyNoLimitDuration = cfg.getInt("Dislo 23 Duration", DISLO_CATEGORY23, disloColonyNoLimitDuration, 0, 2000000000, "Base duration in seconds.");
        disloColonyNoLimitCooldown = cfg.getInt("Dislo 23 Point Cooldown", DISLO_CATEGORY23, disloColonyNoLimitCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloColonyNoLimitS = cfg.getString("Dislo 23 Start", DISLO_CATEGORY23, disloColonyNoLimitS, "Starting message ");
        disloColonyNoLimitE = cfg.getString("Dislo 23 End", DISLO_CATEGORY23, disloColonyNoLimitE, "Ending message");
    }

    private static void initdislo024Config(Configuration cfg) {
        disloNexusGrowth = cfg.getBoolean("Dislo 24 Enabled", DISLO_CATEGORY24, disloNexusGrowth, "Set to false to disable this");
        disloNexusGrowthPrice = cfg.getInt("Dislo 24 Point Cost", DISLO_CATEGORY24, disloNexusGrowthPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloNexusGrowthValue = cfg.getInt("Dislo 24 Value", DISLO_CATEGORY24, disloNexusGrowthValue, 0, 2000000000, "Base Value.");
        disloNexusGrowthDuration = cfg.getInt("Dislo 24 Duration", DISLO_CATEGORY24, disloNexusGrowthDuration, 0, 2000000000, "Base duration in seconds.");
        disloNexusGrowthCooldown = cfg.getInt("Dislo 24 Point Cooldown", DISLO_CATEGORY24, disloNexusGrowthCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloNexusGrowthS = cfg.getString("Dislo 24 Start", DISLO_CATEGORY24, disloNexusGrowthS, "Starting message ");
        disloNexusGrowthE = cfg.getString("Dislo 24 End", DISLO_CATEGORY24, disloNexusGrowthE, "Ending message");
    }

    private static void initdislo025Config(Configuration cfg) {
        disloParasiteBlock = cfg.getBoolean("Dislo 25 Enabled", DISLO_CATEGORY25, disloParasiteBlock, "Set to false to disable this");
        disloParasiteBlockPrice = cfg.getInt("Dislo 25 Point Cost", DISLO_CATEGORY25, disloParasiteBlockPrice, 0, 2000000000, "Base Evolution Point Cost.");
        disloParasiteBlockValue = cfg.getInt("Dislo 25 Value", DISLO_CATEGORY25, disloParasiteBlockValue, 0, 2000000000, "Base Value, will spawn Feral version.");
        disloParasiteBlockValue1 = cfg.getInt("Dislo 25 Value Primitive", DISLO_CATEGORY25, disloParasiteBlockValue1, 0, 2000000000, "Minimum value for Primitive version.");
        disloParasiteBlockValue2 = cfg.getInt("Dislo 25 Value Adapted", DISLO_CATEGORY25, disloParasiteBlockValue2, 0, 2000000000, "Minimum value for Adapted version.");
        disloParasiteBlockValue3 = cfg.getInt("Dislo 25 Value Pure", DISLO_CATEGORY25, disloParasiteBlockValue3, 0, 2000000000, "Minimum value for Pure version.");
        disloParasiteBlockDuration = cfg.getInt("Dislo 25 Duration", DISLO_CATEGORY25, disloParasiteBlockDuration, 0, 2000000000, "Base duration in seconds.");
        disloParasiteBlockCooldown = cfg.getInt("Dislo 25 Point Cooldown", DISLO_CATEGORY25, disloParasiteBlockCooldown, 0, 2000000000, "Cooldown in seconds.");
        disloParasiteBlockS = cfg.getString("Dislo 25 Start", DISLO_CATEGORY25, disloParasiteBlockS, "Starting message ");
        disloParasiteBlockE = cfg.getString("Dislo 25 End", DISLO_CATEGORY25, disloParasiteBlockE, "Ending message");
        disloParasiteBlockChance = cfg.getFloat("Dislo 25 Chance", DISLO_CATEGORY25, (float)disloParasiteBlockChance, 0.0f, 1.0f, "Chance 1=100% to spawn a Parasite.");
    }

    private static void initgenerationConfig(Configuration cfg) {
        String exa = " Where: \n \"1\" is the dimension, \n \"2\" is the generation";
        String geneDescription = " \n If Parasite Generations is true, parasites will start in a very weak state \n Overtime they will gain back their strenght   \n The following features are removed from them (if available)\n -Special Moves\n -Sprinting\n -Xray Vision\n -Adaptation\n -Minimum Damage\n -Damage Cap\n -Water Leap\n -Block Searching\n -Residue Placing\n -Orb Spawning\n \n Generations will increase with time and will be limited by the phases,\n each generation have penalties, making it take longer to go up unless it corresponds to the current phase. ";
        cfg.addCustomCategoryComment(GEN_CATEGORY, "Parasite Generations" + geneDescription);
        generationUse = cfg.getBoolean("Generation Enabled", GEN_CATEGORY, generationUse, "True if you want to use Generations.");
        generationDefa = (byte)cfg.getInt("Generation Value", GEN_CATEGORY, (int)generationDefa, 0, 5, "Generation default Value when starting a world.");
        generationTime1 = cfg.getInt("Generation 1 Time Needed", GEN_CATEGORY, generationTime1, 0, 0x7FFFFFF8, "Needed time in ticks for Generation 1.");
        generationTime2 = cfg.getInt("Generation 2 Time Needed", GEN_CATEGORY, generationTime2, 0, 0x7FFFFFF8, "Needed time in ticks for Generation 2.");
        generationTime3 = cfg.getInt("Generation 3 Time Needed", GEN_CATEGORY, generationTime3, 0, 0x7FFFFFF8, "Needed time in ticks for Generation 3.");
        generationTime4 = cfg.getInt("Generation 4 Time Needed", GEN_CATEGORY, generationTime4, 0, 0x7FFFFFF8, "Needed time in ticks for Generation 4.");
        generationTime5 = cfg.getInt("Generation 5 Time Needed", GEN_CATEGORY, generationTime5, 0, 0x7FFFFFF8, "Needed time in ticks for Generation 5.");
        SPConfigSystems.getByteVal(cfg, GEN_CATEGORY, "Generation 1 Phases", generationPhases1, "If evolution is enabled, list of phases where time needed for this Generation will not have a penalty");
        SPConfigSystems.getByteVal(cfg, GEN_CATEGORY, "Generation 2 Phases", generationPhases2, "If evolution is enabled, list of phases where time needed for this Generation will not have a penalty");
        SPConfigSystems.getByteVal(cfg, GEN_CATEGORY, "Generation 3 Phases", generationPhases3, "If evolution is enabled, list of phases where time needed for this Generation will not have a penalty");
        SPConfigSystems.getByteVal(cfg, GEN_CATEGORY, "Generation 4 Phases", generationPhases4, "If evolution is enabled, list of phases where time needed for this Generation will not have a penalty");
        SPConfigSystems.getByteVal(cfg, GEN_CATEGORY, "Generation 5 Phases", generationPhases5, "If evolution is enabled, list of phases where time needed for this Generation will not have a penalty");
        generationPhasePenalty = cfg.getFloat("Generation 0th ", GEN_CATEGORY, generationPhasePenalty, 0.0f, 10.0f, "If the Generation is out of Phase, time needed will be multiplied by this value.");
        generationDimStart = cfg.getStringList("Generation Dimension Starting List", GEN_CATEGORY, generationDimStart, "List of dimensions that will start at a specific Generation. Ex. \"1;2\"" + exa);
    }

    private static void initgeneration00Config(Configuration cfg) {
        generationCOTH0 = cfg.getFloat("Generation 0 COTH Spawning Stats", GEN0_CATEGORY0, generationCOTH0, 0.0f, 6.0f, "COTH health/damage spawn stat %.");
        generationSpecialM0 = cfg.getBoolean("Generation 0 Special Moves", GEN0_CATEGORY0, generationSpecialM0, "True if parasites of this generation can do special moves.");
        generationSprinting0 = cfg.getBoolean("Generation 0 Sprinting", GEN0_CATEGORY0, generationSprinting0, "True if parasites of this generation can sprint.");
        generationLookWalls0 = cfg.getBoolean("Generation 0 X Ray", GEN0_CATEGORY0, generationLookWalls0, "True if parasites of this generation can see through walls (if available).");
        generationAdaptation0 = cfg.getBoolean("Generation 0 Adaptation", GEN0_CATEGORY0, generationAdaptation0, "True if parasites of this generation can have Adaptation (if available).");
        generationDamageCap0 = cfg.getBoolean("Generation 0 Damage Cap", GEN0_CATEGORY0, generationDamageCap0, "True if parasites of this generation can have Damage Cap.");
        generationMiniDamage0 = cfg.getBoolean("Generation 0 Minimum Damage", GEN0_CATEGORY0, generationMiniDamage0, "True if parasites of this generation can inflict Minimum Damage.");
        generationWaterLeap0 = cfg.getBoolean("Generation 0 Water Leap", GEN0_CATEGORY0, generationWaterLeap0, "True if parasites of this generation can leap while in water.");
        generationBlockSearch0 = cfg.getBoolean("Generation 0 Block Searching", GEN0_CATEGORY0, generationBlockSearch0, "True if parasites of this generation can search for certain blocks (if available).");
        generationResidue0 = cfg.getBoolean("Generation 0 Residue", GEN0_CATEGORY0, generationResidue0, "True if parasites of this generation can place Residue Blocks (if available).");
        generationOrbbox0 = cfg.getBoolean("Generation 0 Orb", GEN0_CATEGORY0, generationOrbbox0, "True if parasites of this generation can use Orbs (if available).");
        generationPoisonHeal0 = cfg.getFloat("Generation 0 Poison", GEN0_CATEGORY0, generationPoisonHeal0, 0.0f, 10.0f, "Heal multiplier if parasites are damaged with poison.");
        generationMobHealing0 = cfg.getFloat("Generation 0 Mob Healing", GEN0_CATEGORY0, generationMobHealing0, 0.0f, 100.0f, "Heal multiplier when killing mobs.");
        generationAttackSpeed0 = cfg.getFloat("Generation 0 Attack Speed", GEN0_CATEGORY0, generationAttackSpeed0, 0.0f, 1.0f, "Attack Speed multiplier.");
    }

    private static void initgeneration01Config(Configuration cfg) {
        generationCOTH1 = cfg.getFloat("Generation 1 COTH Spawning Stats", GEN0_CATEGORY1, generationCOTH1, 0.0f, 6.0f, "COTH health/damage spawn stat %.");
        generationSpecialM1 = cfg.getBoolean("Generation 1 Special Moves", GEN0_CATEGORY1, generationSpecialM1, "True if parasites of this generation can do special moves.");
        generationSprinting1 = cfg.getBoolean("Generation 1 Sprinting", GEN0_CATEGORY1, generationSprinting1, "True if parasites of this generation can sprint.");
        generationLookWalls1 = cfg.getBoolean("Generation 1 X Ray", GEN0_CATEGORY1, generationLookWalls1, "True if parasites of this generation can see through walls (if available).");
        generationAdaptation1 = cfg.getBoolean("Generation 1 Adaptation", GEN0_CATEGORY1, generationAdaptation1, "True if parasites of this generation can have Adaptation (if available).");
        generationDamageCap1 = cfg.getBoolean("Generation 1 Damage Cap", GEN0_CATEGORY1, generationDamageCap1, "True if parasites of this generation can have Damage Cap.");
        generationMiniDamage1 = cfg.getBoolean("Generation 1 Minimum Damage", GEN0_CATEGORY1, generationMiniDamage1, "True if parasites of this generation can inflict Minimum Damage.");
        generationWaterLeap1 = cfg.getBoolean("Generation 1 Water Leap", GEN0_CATEGORY1, generationWaterLeap1, "True if parasites of this generation can leap while in water.");
        generationBlockSearch1 = cfg.getBoolean("Generation 1 Block Searching", GEN0_CATEGORY1, generationBlockSearch1, "True if parasites of this generation can search for certain blocks (if available).");
        generationResidue1 = cfg.getBoolean("Generation 1 Residue", GEN0_CATEGORY1, generationResidue1, "True if parasites of this generation can place Residue Blocks (if available).");
        generationOrbbox1 = cfg.getBoolean("Generation 1 Orb", GEN0_CATEGORY1, generationOrbbox1, "True if parasites of this generation can use Orbs (if available).");
        generationPoisonHeal1 = cfg.getFloat("Generation 1 Poison", GEN0_CATEGORY1, generationPoisonHeal1, 0.0f, 10.0f, "Heal multiplier if parasites are damaged with poison.");
        generationMobHealing1 = cfg.getFloat("Generation 1 Mob Healing", GEN0_CATEGORY1, generationMobHealing1, 0.0f, 100.0f, "Heal multiplier when killing mobs.");
        generationAttackSpeed1 = cfg.getFloat("Generation 1 Attack Speed", GEN0_CATEGORY1, generationAttackSpeed1, 0.0f, 1.0f, "Attack Speed multiplier.");
    }

    private static void initgeneration02Config(Configuration cfg) {
        generationCOTH2 = cfg.getFloat("Generation 2 COTH Spawning Stats", GEN0_CATEGORY2, generationCOTH2, 0.0f, 6.0f, "COTH health/damage spawn stat %.");
        generationSpecialM2 = cfg.getBoolean("Generation 2 Special Moves", GEN0_CATEGORY2, generationSpecialM2, "True if parasites of this generation can do special moves.");
        generationSprinting2 = cfg.getBoolean("Generation 2 Sprinting", GEN0_CATEGORY2, generationSprinting2, "True if parasites of this generation can sprint.");
        generationLookWalls2 = cfg.getBoolean("Generation 2 X Ray", GEN0_CATEGORY2, generationLookWalls2, "True if parasites of this generation can see through walls (if available).");
        generationAdaptation2 = cfg.getBoolean("Generation 2 Adaptation", GEN0_CATEGORY2, generationAdaptation2, "True if parasites of this generation can have Adaptation (if available).");
        generationDamageCap2 = cfg.getBoolean("Generation 2 Damage Cap", GEN0_CATEGORY2, generationDamageCap2, "True if parasites of this generation can have Damage Cap.");
        generationMiniDamage2 = cfg.getBoolean("Generation 2 Minimum Damage", GEN0_CATEGORY2, generationMiniDamage2, "True if parasites of this generation can inflict Minimum Damage.");
        generationWaterLeap2 = cfg.getBoolean("Generation 2 Water Leap", GEN0_CATEGORY2, generationWaterLeap2, "True if parasites of this generation can leap while in water.");
        generationBlockSearch2 = cfg.getBoolean("Generation 2 Block Searching", GEN0_CATEGORY2, generationBlockSearch2, "True if parasites of this generation can search for certain blocks (if available).");
        generationResidue2 = cfg.getBoolean("Generation 2 Residue", GEN0_CATEGORY2, generationResidue2, "True if parasites of this generation can place Residue Blocks (if available).");
        generationOrbbox2 = cfg.getBoolean("Generation 2 Orb", GEN0_CATEGORY2, generationOrbbox2, "True if parasites of this generation can use Orbs (if available).");
        generationPoisonHeal2 = cfg.getFloat("Generation 2 Poison", GEN0_CATEGORY2, generationPoisonHeal2, 0.0f, 10.0f, "Heal multiplier if parasites are damaged with poison.");
        generationMobHealing2 = cfg.getFloat("Generation 2 Mob Healing", GEN0_CATEGORY2, generationMobHealing2, 0.0f, 100.0f, "Heal multiplier when killing mobs.");
        generationAttackSpeed2 = cfg.getFloat("Generation 2 Attack Speed", GEN0_CATEGORY2, generationAttackSpeed2, 0.0f, 1.0f, "Attack Speed multiplier.");
    }

    private static void initgeneration03Config(Configuration cfg) {
        generationCOTH3 = cfg.getFloat("Generation 3 COTH Spawning Stats", GEN0_CATEGORY3, generationCOTH3, 0.0f, 6.0f, "COTH health/damage spawn stat %.");
        generationSpecialM3 = cfg.getBoolean("Generation 3 Special Moves", GEN0_CATEGORY3, generationSpecialM3, "True if parasites of this generation can do special moves.");
        generationSprinting3 = cfg.getBoolean("Generation 3 Sprinting", GEN0_CATEGORY3, generationSprinting3, "True if parasites of this generation can sprint.");
        generationLookWalls3 = cfg.getBoolean("Generation 3 X Ray", GEN0_CATEGORY3, generationLookWalls3, "True if parasites of this generation can see through walls (if available).");
        generationAdaptation3 = cfg.getBoolean("Generation 3 Adaptation", GEN0_CATEGORY3, generationAdaptation3, "True if parasites of this generation can have Adaptation (if available).");
        generationDamageCap3 = cfg.getBoolean("Generation 3 Damage Cap", GEN0_CATEGORY3, generationDamageCap3, "True if parasites of this generation can have Damage Cap.");
        generationMiniDamage3 = cfg.getBoolean("Generation 3 Minimum Damage", GEN0_CATEGORY3, generationMiniDamage3, "True if parasites of this generation can inflict Minimum Damage.");
        generationWaterLeap3 = cfg.getBoolean("Generation 3 Water Leap", GEN0_CATEGORY3, generationWaterLeap3, "True if parasites of this generation can leap while in water.");
        generationBlockSearch3 = cfg.getBoolean("Generation 3 Block Searching", GEN0_CATEGORY3, generationBlockSearch3, "True if parasites of this generation can search for certain blocks (if available).");
        generationResidue3 = cfg.getBoolean("Generation 3 Residue", GEN0_CATEGORY3, generationResidue3, "True if parasites of this generation can place Residue Blocks (if available).");
        generationOrbbox3 = cfg.getBoolean("Generation 3 Orb", GEN0_CATEGORY3, generationOrbbox3, "True if parasites of this generation can use Orbs (if available).");
        generationPoisonHeal3 = cfg.getFloat("Generation 3 Poison", GEN0_CATEGORY3, generationPoisonHeal3, 0.0f, 10.0f, "Heal multiplier if parasites are damaged with poison.");
        generationMobHealing3 = cfg.getFloat("Generation 3 Mob Healing", GEN0_CATEGORY3, generationMobHealing3, 0.0f, 100.0f, "Heal multiplier when killing mobs.");
        generationAttackSpeed3 = cfg.getFloat("Generation 3 Attack Speed", GEN0_CATEGORY3, generationAttackSpeed3, 0.0f, 1.0f, "Attack Speed multiplier.");
    }

    private static void initgeneration04Config(Configuration cfg) {
        generationCOTH4 = cfg.getFloat("Generation 4 COTH Spawning Stats", GEN0_CATEGORY4, generationCOTH4, 0.0f, 6.0f, "COTH health/damage spawn stat %.");
        generationSpecialM4 = cfg.getBoolean("Generation 4 Special Moves", GEN0_CATEGORY4, generationSpecialM4, "True if parasites of this generation can do special moves.");
        generationSprinting4 = cfg.getBoolean("Generation 4 Sprinting", GEN0_CATEGORY4, generationSprinting4, "True if parasites of this generation can sprint.");
        generationLookWalls4 = cfg.getBoolean("Generation 4 X Ray", GEN0_CATEGORY4, generationLookWalls4, "True if parasites of this generation can see through walls (if available).");
        generationAdaptation4 = cfg.getBoolean("Generation 4 Adaptation", GEN0_CATEGORY4, generationAdaptation4, "True if parasites of this generation can have Adaptation (if available).");
        generationDamageCap4 = cfg.getBoolean("Generation 4 Damage Cap", GEN0_CATEGORY4, generationDamageCap4, "True if parasites of this generation can have Damage Cap.");
        generationMiniDamage4 = cfg.getBoolean("Generation 4 Minimum Damage", GEN0_CATEGORY4, generationMiniDamage4, "True if parasites of this generation can inflict Minimum Damage.");
        generationWaterLeap4 = cfg.getBoolean("Generation 4 Water Leap", GEN0_CATEGORY4, generationWaterLeap4, "True if parasites of this generation can leap while in water.");
        generationBlockSearch4 = cfg.getBoolean("Generation 4 Block Searching", GEN0_CATEGORY4, generationBlockSearch4, "True if parasites of this generation can search for certain blocks (if available).");
        generationResidue4 = cfg.getBoolean("Generation 4 Residue", GEN0_CATEGORY4, generationResidue4, "True if parasites of this generation can place Residue Blocks (if available).");
        generationOrbbox4 = cfg.getBoolean("Generation 4 Orb", GEN0_CATEGORY4, generationOrbbox4, "True if parasites of this generation can use Orbs (if available).");
        generationPoisonHeal4 = cfg.getFloat("Generation 4 Poison", GEN0_CATEGORY4, generationPoisonHeal4, 0.0f, 10.0f, "Heal multiplier if parasites are damaged with poison.");
        generationMobHealing4 = cfg.getFloat("Generation 4 Mob Healing", GEN0_CATEGORY4, generationMobHealing4, 0.0f, 100.0f, "Heal multiplier when killing mobs.");
        generationAttackSpeed4 = cfg.getFloat("Generation 4 Attack Speed", GEN0_CATEGORY4, generationAttackSpeed4, 0.0f, 1.0f, "Attack Speed multiplier.");
    }

    private static void initgeneration05Config(Configuration cfg) {
        generationCOTH5 = cfg.getFloat("Generation 5 COTH Spawning Stats", GEN0_CATEGORY5, generationCOTH5, 0.0f, 6.0f, "COTH health/damage spawn stat %.");
        generationSpecialM5 = cfg.getBoolean("Generation 5 Special Moves", GEN0_CATEGORY5, generationSpecialM5, "True if parasites of this generation can do special moves.");
        generationSprinting5 = cfg.getBoolean("Generation 5 Sprinting", GEN0_CATEGORY5, generationSprinting5, "True if parasites of this generation can sprint.");
        generationLookWalls5 = cfg.getBoolean("Generation 5 X Ray", GEN0_CATEGORY5, generationLookWalls5, "True if parasites of this generation can see through walls (if available).");
        generationAdaptation5 = cfg.getBoolean("Generation 5 Adaptation", GEN0_CATEGORY5, generationAdaptation5, "True if parasites of this generation can have Adaptation (if available).");
        generationDamageCap5 = cfg.getBoolean("Generation 5 Damage Cap", GEN0_CATEGORY5, generationDamageCap5, "True if parasites of this generation can have Damage Cap.");
        generationMiniDamage5 = cfg.getBoolean("Generation 5 Minimum Damage", GEN0_CATEGORY5, generationMiniDamage5, "True if parasites of this generation can inflict Minimum Damage.");
        generationWaterLeap5 = cfg.getBoolean("Generation 5 Water Leap", GEN0_CATEGORY5, generationWaterLeap5, "True if parasites of this generation can leap while in water.");
        generationBlockSearch5 = cfg.getBoolean("Generation 5 Block Searching", GEN0_CATEGORY5, generationBlockSearch5, "True if parasites of this generation can search for certain blocks (if available).");
        generationResidue5 = cfg.getBoolean("Generation 5 Residue", GEN0_CATEGORY5, generationResidue5, "True if parasites of this generation can place Residue Blocks (if available).");
        generationOrbbox5 = cfg.getBoolean("Generation 5 Orb", GEN0_CATEGORY5, generationOrbbox5, "True if parasites of this generation can use Orbs (if available).");
        generationPoisonHeal5 = cfg.getFloat("Generation 5 Poison", GEN0_CATEGORY5, generationPoisonHeal5, 0.0f, 10.0f, "Heal multiplier if parasites are damaged with poison.");
        generationMobHealing5 = cfg.getFloat("Generation 5 Mob Healing", GEN0_CATEGORY5, generationMobHealing5, 0.0f, 100.0f, "Heal multiplier when killing mobs.");
        generationAttackSpeed5 = cfg.getFloat("Generation 5 Attack Speed", GEN0_CATEGORY5, generationAttackSpeed5, 0.0f, 1.0f, "Attack Speed multiplier.");
    }

    public static void initConfig(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        CommonProxy.configSystems = new Configuration(new File(directory.getPath(), "subspaceparasite/SPParasitesSystems.cfg"));
        SPConfigSystems.readConfig();
    }

    public static boolean readConfig() {
        Configuration cfgS = CommonProxy.configSystems;
        try {
            cfgS.load();
            SPConfigSystems.initstatuseffectsConfig(cfgS);
            SPConfigSystems.initGeneralSistemsConfig(cfgS);
            SPConfigSystems.initmergeConfig(cfgS);
            SPConfigSystems.initevolutionConfig(cfgS);
            SPConfigSystems.initevolutionM1Config(cfgS);
            SPConfigSystems.initevolution0Config(cfgS);
            SPConfigSystems.initevolution1Config(cfgS);
            SPConfigSystems.initevolution2Config(cfgS);
            SPConfigSystems.initevolution3Config(cfgS);
            SPConfigSystems.initevolution4Config(cfgS);
            SPConfigSystems.initevolution5Config(cfgS);
            SPConfigSystems.initevolution6Config(cfgS);
            SPConfigSystems.initevolution7Config(cfgS);
            SPConfigSystems.initevolution8Config(cfgS);
            SPConfigSystems.initevolution9Config(cfgS);
            SPConfigSystems.initevolution10Config(cfgS);
            SPConfigSystems.initreinforcementSystemConfig(cfgS);
            SPConfigSystems.inithivemindConfig(cfgS);
            SPConfigSystems.inithivemindscentlvl0Config(cfgS);
            SPConfigSystems.inithivemindscentlvl1Config(cfgS);
            SPConfigSystems.inithivemindscentlvl2Config(cfgS);
            SPConfigSystems.inithivemindscentlvl3Config(cfgS);
            SPConfigSystems.inithivemindscentlvl4Config(cfgS);
            SPConfigSystems.inithivemindscentlvl5Config(cfgS);
            SPConfigSystems.inithivemindscentlvl6Config(cfgS);
            SPConfigSystems.inithivemindscentlvl7Config(cfgS);
            SPConfigSystems.inithivemindscentlvl8Config(cfgS);
            SPConfigSystems.inittotaldevelopmentConfig(cfgS);
            SPConfigSystems.inittotaldevelopmentOneConfig(cfgS);
            SPConfigSystems.inittotaldevelopmentTwoConfig(cfgS);
            SPConfigSystems.inittotaldevelopmentThreeConfig(cfgS);
            SPConfigSystems.inittotaldevelopmentFourConfig(cfgS);
            SPConfigSystems.initdisloMainConfig(cfgS);
            SPConfigSystems.initdislo000Config(cfgS);
            SPConfigSystems.initdislo000Config(cfgS);
            SPConfigSystems.initdislo001Config(cfgS);
            SPConfigSystems.initdislo002Config(cfgS);
            SPConfigSystems.initdislo003Config(cfgS);
            SPConfigSystems.initdislo004Config(cfgS);
            SPConfigSystems.initdislo005Config(cfgS);
            SPConfigSystems.initdislo006Config(cfgS);
            SPConfigSystems.initdislo007Config(cfgS);
            SPConfigSystems.initdislo008Config(cfgS);
            SPConfigSystems.initdislo009Config(cfgS);
            SPConfigSystems.initdislo010Config(cfgS);
            SPConfigSystems.initdislo011Config(cfgS);
            SPConfigSystems.initdislo012Config(cfgS);
            SPConfigSystems.initdislo013Config(cfgS);
            SPConfigSystems.initdislo014Config(cfgS);
            SPConfigSystems.initdislo015Config(cfgS);
            SPConfigSystems.initdislo016Config(cfgS);
            SPConfigSystems.initdislo017Config(cfgS);
            SPConfigSystems.initdislo018Config(cfgS);
            SPConfigSystems.initdislo019Config(cfgS);
            SPConfigSystems.initdislo021Config(cfgS);
            SPConfigSystems.initdislo021Config(cfgS);
            SPConfigSystems.initdislo022Config(cfgS);
            SPConfigSystems.initdislo023Config(cfgS);
            SPConfigSystems.initdislo024Config(cfgS);
            SPConfigSystems.initdislo025Config(cfgS);
            SPConfigSystems.initgenerationConfig(cfgS);
            SPConfigSystems.initgeneration00Config(cfgS);
            SPConfigSystems.initgeneration01Config(cfgS);
            SPConfigSystems.initgeneration02Config(cfgS);
            SPConfigSystems.initgeneration03Config(cfgS);
            SPConfigSystems.initgeneration04Config(cfgS);
            SPConfigSystems.initgeneration05Config(cfgS);
            boolean bl = true;
            return bl;
        }
        catch (Exception e) {
            SPMain.logger.log(Level.ERROR, "Problem loading configuration file", (Throwable)e);
        }
        finally {
            if (cfgS.hasChanged()) {
                cfgS.save();
            }
        }
        return false;
    }

    private static void getByteVal(Configuration cfg, String category, String key, byte[] defaultValues, String comment) {
        int i;
        int[] atm = new int[defaultValues.length];
        for (i = 0; i < defaultValues.length; ++i) {
            atm[i] = defaultValues[i];
        }
        atm = cfg.get(category, key, atm, comment).getIntList();
        for (i = 0; i < defaultValues.length; ++i) {
            defaultValues[i] = (byte)atm[i];
        }
    }
}

