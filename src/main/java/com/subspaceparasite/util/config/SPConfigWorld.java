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
import com.subspaceparasite.util.config.SPConfigBlockConversions;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

public class SPConfigWorld {
    private static final String CATEGORY_GENERAL_WORLD = "configuration_world";
    public static int meteorTick = 3600;
    public static int meteorDamage = 110;
    public static int meteorRadius = 120;
    public static int meteorMinRadius = 80;
    public static double meteorChance = 0.5;
    public static boolean meteorVectorless = true;
    public static boolean meteorActive = false;
    public static int[] meteorBlacklistDims = new int[]{-1};
    public static String[] infestationConvertBlocks = new String[]{"minecraft:cactus;subspaceparasite:infested_cactus"};
    public static boolean bloodyIceBreakOnHardLanding = true;
    public static boolean venkrolTornadoEnabled = true;
    public static int fogNullifierMaxUses = 3;
    public static boolean enableCelestialObjects = true;
    public static String boughReplacementMobId = "subspaceparasite:sim_adventurer";
    public static boolean boughSpawnReplacement = true;
    public static double bloodyIceBreakFallDistance = 5.0;
    public static double frozenVariantTempThreshold = 0.15;
    public static String[] frozenVariantBiomeBlacklist = new String[0];
    public static int bloodyIceBreakDiameter = 3;
    public static boolean bushClimbingEnabled = true;
    public static boolean thornshadeGrowthEnabled = true;
    public static boolean escapeEnabled = true;
    public static int escapeMinDistance = 200;
    public static int escapeMaxDistance = 300;
    private static final String ESCAPE_CATEGORY = "EscapeSystem";
    public static int thornshadeGrowthChance = 10;
    public static boolean thornshadeRawMeatBonemeal = true;
    public static boolean parasiteLootDamageOnTake = true;
    public static boolean residueFlammableWave = true;
    private static final String WORLDBIOME_CATEGORY = "world_biome";
    public static boolean biomeRegster = true;
    public static boolean nodesActivated = true;
    public static int maximumNumberNodes = 20;
    public static int minimumDistanceBetweenNodes = 10000;
    public static int minimumDistanceFromSpawnPoint = 1;
    public static int[] blackListedDimensionsNodes = new int[]{0, 1, -1};
    public static String[] blockBBiomeList = new String[0];
    public static boolean blockBBiomeListWhite = false;
    public static float biomeBlockIMaxH = 60.0f;
    public static float biomeFogDensity = 0.06f;
    public static String nodeWarning = "Node";
    public static boolean venkrolNode = true;
    public static int biomeHeartFreq = 30;
    public static float biomeHeartVol = 0.4f;
    public static String[] biomeHealPenaltyBlackList = new String[]{"minecraft:villager_golem"};
    public static boolean biomeHealPenaltyBlackListWhite = false;
    public static String[] biomeBlackList = new String[0];
    public static boolean biomeBlackListInverted = false;
    public static String[] potionEffectForNodes = new String[]{"3;minecraft:speed;2", "4;minecraft:fire_resistance;1", "7;minecraft:invisibility;1"};
    public static int spawnerSKYLimitUp = 250;
    public static int nodeRangeSpreadOne = 200;
    public static int nodeRangeSpreadTwo = 800;
    public static int nodeRangeSpreadThree = 4000;
    public static int nodeRangeEffectsOne = 400;
    public static int nodeRangeEffectsTwo = 1600;
    public static int nodeRangeEffectsThree = 8000;
    public static float nodeCropStopNodeOne = 0.3f;
    public static float nodeCropStopNodeTwo = 0.6f;
    public static float nodeCropStopNodeThree = 1.0f;
    public static int timeNeedeToNodeOne = 8;
    public static int timeNeedeToNodeTwo = 10;
    public static int timeNeedeToNodeThree = 40;
    private static final String WORLDBIOMEONE_CATEGORY = "world_biome_01";
    public static int biomeOneWeight = 0;
    public static int biomeOneSkyColor = 0x2F0000;
    public static int biomeOneGrassColor = 0xA60000;
    public static int biomeOneFoliageColor = 0xA60000;
    public static int biomeOneWaterColor = 0xA60000;
    public static float biomeOneFogRed = 38.0f;
    public static float biomeOneFogBlue = 36.0f;
    public static float biomeOneFogGreen = 36.0f;
    public static float biomeOneHealPenalty = 0.5f;
    public static String[] biomeOneSpawnEntry = new String[]{"subspaceparasite:ada_summoner;1;1;10;0", "subspaceparasite:ada_longarms;1;1;10;0", "subspaceparasite:ada_reeker;1;1;10;0", "subspaceparasite:ada_manducater;1;1;10;0", "subspaceparasite:ada_bolster;1;1;10;0", "subspaceparasite:ada_yelloweye;1;1;10;0", "subspaceparasite:ada_arachnida;1;1;10;0", "subspaceparasite:ada_devourer;1;1;10;0", "subspaceparasite:ada_viscera;1;1;10;0", "subspaceparasite:overseer;2;4;5;0", "subspaceparasite:vigilante;1;3;5;0", "subspaceparasite:warden;1;1;5;0", "subspaceparasite:marauder;2;3;5;0", "subspaceparasite:bomber_light;2;5;5;0", "subspaceparasite:monarch;2;5;5;0", "subspaceparasite:grunt;1;1;20;0", "subspaceparasite:bomber_heavy;1;1;5;0", "subspaceparasite:wraith;2;2;5;0", "subspaceparasite:bogle;1;1;5;0", "subspaceparasite:haunter;1;1;5;0", "subspaceparasite:carrier_colony;1;1;5;0", "subspaceparasite:worker;1;1;10;0"};
    public static String[] biomeOneBlockList = new String[]{"minecraft:leaves:6;subspaceparasite:parasitestain:4"};
    private static final String WORLDBIOMETWO_CATEGORY = "world_biome_02";
    public static int biomeTwoWeight = 0;
    public static int biomeTwoSkyColor = 14415640;
    public static int biomeTwoGrassColor = 14415640;
    public static int biomeTwoFoliageColor = 14415640;
    public static int biomeTwoWaterColor = 0xA60000;
    public static float biomeTwoFogRed = 81.0f;
    public static float biomeTwoFogBlue = 8.0f;
    public static float biomeTwoFogGreen = 107.0f;
    public static float biomeTwoHealPenalty = 0.5f;
    public static String[] biomeTwoSpawnEntry = new String[]{"subspaceparasite:ada_summoner;1;1;10;0", "subspaceparasite:ada_longarms;1;1;10;0", "subspaceparasite:ada_reeker;1;1;10;0", "subspaceparasite:ada_manducater;1;1;10;0", "subspaceparasite:ada_bolster;1;1;10;0", "subspaceparasite:ada_yelloweye;1;1;10;0", "subspaceparasite:ada_arachnida;1;1;10;0", "subspaceparasite:ada_devourer;1;1;10;0", "subspaceparasite:ada_viscera;1;1;10;0", "subspaceparasite:overseer;2;4;5;0", "subspaceparasite:vigilante;1;3;5;0", "subspaceparasite:warden;1;1;5;0", "subspaceparasite:marauder;2;3;5;0", "subspaceparasite:bomber_light;2;5;5;0", "subspaceparasite:monarch;2;5;5;0", "subspaceparasite:grunt;1;1;20;0", "subspaceparasite:bomber_heavy;1;1;5;0", "subspaceparasite:wraith;2;2;5;0", "subspaceparasite:bogle;1;1;5;0", "subspaceparasite:haunter;1;1;5;0", "subspaceparasite:carrier_colony;1;1;5;0", "subspaceparasite:worker;1;1;10;0"};
    public static String[] biomeTwoBlockList = new String[]{"minecraft:leaves:6;subspaceparasite:parasitestain:4"};
    private static final String WORLDBIOMETHREE_CATEGORY = "world_biome_03";
    public static int biomeThreeWeight = 0;
    public static int biomeThreeSkyColor = 8290432;
    public static int biomeThreeGrassColor = 8290432;
    public static int biomeThreeFoliageColor = 8290432;
    public static int biomeThreeWaterColor = 0xA60000;
    public static float biomeThreeFogRed = 199.0f;
    public static float biomeThreeFogBlue = 91.0f;
    public static float biomeThreeFogGreen = 201.0f;
    public static float biomeThreeHealPenalty = 0.5f;
    public static String[] biomeThreeSpawnEntry = new String[]{"subspaceparasite:ada_summoner;1;1;10;0", "subspaceparasite:ada_longarms;1;1;10;0", "subspaceparasite:ada_reeker;1;1;10;0", "subspaceparasite:ada_manducater;1;1;10;0", "subspaceparasite:ada_bolster;1;1;10;0", "subspaceparasite:ada_yelloweye;1;1;10;0", "subspaceparasite:ada_arachnida;1;1;10;0", "subspaceparasite:ada_devourer;1;1;10;0", "subspaceparasite:ada_viscera;1;1;10;0", "subspaceparasite:overseer;2;4;5;0", "subspaceparasite:vigilante;1;3;5;0", "subspaceparasite:warden;1;1;5;0", "subspaceparasite:marauder;2;3;5;0", "subspaceparasite:bomber_light;2;5;5;0", "subspaceparasite:monarch;2;5;5;0", "subspaceparasite:grunt;1;1;20;0", "subspaceparasite:bomber_heavy;1;1;5;0", "subspaceparasite:wraith;2;2;5;0", "subspaceparasite:bogle;1;1;5;0", "subspaceparasite:haunter;1;1;5;0", "subspaceparasite:carrier_colony;1;1;5;0", "subspaceparasite:worker;1;1;10;0"};
    public static String[] biomeThreeBlockList = new String[]{"minecraft:leaves:0;subspaceparasite:alveoli:0", "minecraft:leaves:1;subspaceparasite:alveoli:0", "minecraft:leaves:2;subspaceparasite:alveoli:0", "minecraft:leaves:3;subspaceparasite:alveoli:0", "minecraft:leaves:4;subspaceparasite:alveoli:0", "minecraft:leaves:5;subspaceparasite:alveoli:0", "minecraft:leaves:6;subspaceparasite:alveoli:0", "minecraft:leaves:7;subspaceparasite:alveoli:0", "minecraft:planks:0;subspaceparasite:harleskinn_block:0", "minecraft:planks:1;subspaceparasite:harleskinn_block:0", "minecraft:planks:2;subspaceparasite:harleskinn_block:0", "minecraft:planks:3;subspaceparasite:harleskinn_block:0", "minecraft:planks:4;subspaceparasite:harleskinn_block:0", "minecraft:planks:5;subspaceparasite:harleskinn_block:0", "minecraft:cobblestone:0;subspaceparasite:harleskinn_block:0", "minecraft:mossy_cobblestone:0;subspaceparasite:harleskinn_block:0", "minecraft:grass:0;subspaceparasite:harlequinn_grass:0", "minecraft:stone:0;subspaceparasite:harleskinn_block:0", "minecraft:sand:0;subspaceparasite:harleskinn_block:0", "minecraft:sand:1;subspaceparasite:harleskinn_block:0", "minecraft:sandstone:0;subspaceparasite:harleskinn_block:0", "minecraft:sandstone:1;subspaceparasite:harleskinn_block:0", "minecraft:sandstone:2;subspaceparasite:harleskinn_block:0", "minecraft:red_sandstone:0;subspaceparasite:harleskinn_block:0", "minecraft:red_sandstone:1;subspaceparasite:harleskinn_block:0", "minecraft:red_sandstone:2;subspaceparasite:harleskinn_block:0", "minecraft:oak_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:oak_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:oak_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:oak_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:oak_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:oak_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:oak_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:oak_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:spruce_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:spruce_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:spruce_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:spruce_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:spruce_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:spruce_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:spruce_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:spruce_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:birch_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:birch_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:birch_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:birch_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:birch_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:birch_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:birch_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:birch_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:jungle_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:jungle_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:jungle_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:jungle_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:jungle_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:jungle_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:jungle_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:jungle_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:acacia_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:acacia_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:acacia_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:acacia_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:acacia_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:acacia_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:acacia_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:acacia_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:dark_oak_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:dark_oak_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:dark_oak_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:dark_oak_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:dark_oak_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:dark_oak_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:dark_oak_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:dark_oak_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:stone_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:stone_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:stone_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:stone_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:stone_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:stone_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:stone_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:stone_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:sandstone_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:sandstone_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:sandstone_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:sandstone_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:sandstone_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:sandstone_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:sandstone_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:sandstone_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:red_sandstone_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:red_sandstone_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:red_sandstone_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:red_sandstone_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:red_sandstone_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:red_sandstone_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:red_sandstone_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:red_sandstone_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:stone_brick_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:stone_brick_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:stone_brick_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:stone_brick_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:stone_brick_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:stone_brick_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:stone_brick_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:stone_brick_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:brick_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:brick_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:brick_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:brick_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:brick_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:brick_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:brick_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:brick_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:nether_brick_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:nether_brick_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:nether_brick_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:nether_brick_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:nether_brick_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:nether_brick_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:nether_brick_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:nether_brick_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:quartz_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:quartz_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:quartz_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:quartz_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:quartz_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:quartz_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:quartz_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:quartz_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:purpur_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:purpur_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:purpur_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:purpur_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:purpur_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:purpur_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:purpur_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:purpur_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:prismarine_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:prismarine_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:prismarine_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:prismarine_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:prismarine_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:prismarine_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:prismarine_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:prismarine_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:dark_prismarine_stairs:0;subspaceparasite:harleskinn_stairs:0", "minecraft:dark_prismarine_stairs:1;subspaceparasite:harleskinn_stairs:1", "minecraft:dark_prismarine_stairs:2;subspaceparasite:harleskinn_stairs:2", "minecraft:dark_prismarine_stairs:3;subspaceparasite:harleskinn_stairs:3", "minecraft:dark_prismarine_stairs:4;subspaceparasite:harleskinn_stairs:4", "minecraft:dark_prismarine_stairs:5;subspaceparasite:harleskinn_stairs:5", "minecraft:dark_prismarine_stairs:6;subspaceparasite:harleskinn_stairs:6", "minecraft:dark_prismarine_stairs:7;subspaceparasite:harleskinn_stairs:7", "minecraft:stone_slab:0;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:1;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:2;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:3;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:4;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:5;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:6;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:7;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab:8;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:9;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:10;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:11;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:12;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:13;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:14;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab:15;subspaceparasite:harleskinn_slab:8", "minecraft:stone_slab2:0;subspaceparasite:harleskinn_slab:0", "minecraft:stone_slab2:8;subspaceparasite:harleskinn_slab:8", "minecraft:wooden_slab:0;subspaceparasite:harleskinn_slab:0", "minecraft:wooden_slab:1;subspaceparasite:harleskinn_slab:0", "minecraft:wooden_slab:2;subspaceparasite:harleskinn_slab:0", "minecraft:wooden_slab:3;subspaceparasite:harleskinn_slab:0", "minecraft:wooden_slab:4;subspaceparasite:harleskinn_slab:0", "minecraft:wooden_slab:5;subspaceparasite:harleskinn_slab:0", "minecraft:wooden_slab:8;subspaceparasite:harleskinn_slab:8", "minecraft:wooden_slab:9;subspaceparasite:harleskinn_slab:8", "minecraft:wooden_slab:10;subspaceparasite:harleskinn_slab:8", "minecraft:wooden_slab:11;subspaceparasite:harleskinn_slab:8", "minecraft:wooden_slab:12;subspaceparasite:harleskinn_slab:8", "minecraft:wooden_slab:13;subspaceparasite:harleskinn_slab:8", "minecraft:purpur_slab:0;subspaceparasite:harleskinn_slab:0", "minecraft:purpur_slab:8;subspaceparasite:harleskinn_slab:8", "minecraft:fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:spruce_fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:birch_fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:jungle_fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:acacia_fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:dark_oak_fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:nether_brick_fence:0;subspaceparasite:harleskinn_fence:0", "minecraft:fence_gate:0;subspaceparasite:harleskinn_fence:0", "minecraft:spruce_fence_gate:0;subspaceparasite:harleskinn_fence:0", "minecraft:birch_fence_gate:0;subspaceparasite:harleskinn_fence:0", "minecraft:jungle_fence_gate:0;subspaceparasite:harleskinn_fence:0", "minecraft:acacia_fence_gate:0;subspaceparasite:harleskinn_fence:0", "minecraft:dark_oak_fence_gate:0;subspaceparasite:harleskinn_fence:0", "minecraft:glass:0;subspaceparasite:harlequinn_glass:0", "minecraft:glass_pane:0;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass:0;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:1;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:2;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:3;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:4;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:5;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:6;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:7;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:8;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:9;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:10;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:11;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:12;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:13;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:14;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass:15;subspaceparasite:harlequinn_glass:0", "minecraft:stained_glass_pane:0;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:1;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:2;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:3;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:4;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:5;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:6;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:7;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:8;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:9;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:10;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:11;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:12;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:13;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:14;subspaceparasite:harlequinn_glass_pane:0", "minecraft:stained_glass_pane:15;subspaceparasite:harlequinn_glass_pane:0", "minecraft:dirt:0;subspaceparasite:harleskinn_block:0", "minecraft:dirt:1;subspaceparasite:harleskinn_block:0", "minecraft:dirt:2;subspaceparasite:harleskinn_block:0", "minecraft:gravel:0;subspaceparasite:harleskinn_block:0", "minecraft:mycelium:0;subspaceparasite:harleskinn_block:0", "minecraft:clay:0;subspaceparasite:harleskinn_block:0", "minecraft:hardened_clay:0;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:0;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:1;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:2;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:3;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:4;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:5;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:6;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:7;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:8;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:9;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:10;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:11;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:12;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:13;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:14;subspaceparasite:harleskinn_block:0", "minecraft:stained_hardened_clay:15;subspaceparasite:harleskinn_block:0", "minecraft:brick_block:0;subspaceparasite:harleskinn_block:0", "minecraft:stonebrick:0;subspaceparasite:harleskinn_block:0", "minecraft:stonebrick:1;subspaceparasite:harleskinn_block:0", "minecraft:stonebrick:2;subspaceparasite:harleskinn_block:0", "minecraft:stonebrick:3;subspaceparasite:harleskinn_block:0", "minecraft:netherrack:0;subspaceparasite:harleskinn_block:0", "minecraft:nether_brick:0;subspaceparasite:harleskinn_block:0", "minecraft:end_stone:0;subspaceparasite:harleskinn_block:0", "minecraft:quartz_block:0;subspaceparasite:harleskinn_block:0", "minecraft:quartz_block:1;subspaceparasite:harleskinn_block:0", "minecraft:quartz_block:2;subspaceparasite:harleskinn_block:0", "minecraft:log:0;subspaceparasite:hair_follicle_block:0", "minecraft:log:1;subspaceparasite:hair_follicle_block:0", "minecraft:log:2;subspaceparasite:hair_follicle_block:0", "minecraft:log:3;subspaceparasite:hair_follicle_block:0", "minecraft:log:4;subspaceparasite:hair_follicle_block:4", "minecraft:log:5;subspaceparasite:hair_follicle_block:4", "minecraft:log:6;subspaceparasite:hair_follicle_block:4", "minecraft:log:7;subspaceparasite:hair_follicle_block:4", "minecraft:log:8;subspaceparasite:hair_follicle_block:8", "minecraft:log:9;subspaceparasite:hair_follicle_block:8", "minecraft:log:10;subspaceparasite:hair_follicle_block:8", "minecraft:log:11;subspaceparasite:hair_follicle_block:8", "minecraft:log:12;subspaceparasite:hair_follicle_block:12", "minecraft:log:13;subspaceparasite:hair_follicle_block:12", "minecraft:log:14;subspaceparasite:hair_follicle_block:12", "minecraft:log:15;subspaceparasite:hair_follicle_block:12", "minecraft:log2:0;subspaceparasite:hair_follicle_block:0", "minecraft:log2:1;subspaceparasite:hair_follicle_block:0", "minecraft:log2:2;subspaceparasite:hair_follicle_block:0", "minecraft:log2:3;subspaceparasite:hair_follicle_block:0", "minecraft:log2:4;subspaceparasite:hair_follicle_block:4", "minecraft:log2:5;subspaceparasite:hair_follicle_block:4", "minecraft:log2:6;subspaceparasite:hair_follicle_block:4", "minecraft:log2:7;subspaceparasite:hair_follicle_block:4", "minecraft:log2:8;subspaceparasite:hair_follicle_block:8", "minecraft:log2:9;subspaceparasite:hair_follicle_block:8", "minecraft:log2:10;subspaceparasite:hair_follicle_block:8", "minecraft:log2:11;subspaceparasite:hair_follicle_block:8", "minecraft:log2:12;subspaceparasite:hair_follicle_block:12", "minecraft:log2:13;subspaceparasite:hair_follicle_block:12", "minecraft:log2:14;subspaceparasite:hair_follicle_block:12", "minecraft:log2:15;subspaceparasite:hair_follicle_block:12", "minecraft:vine:0;minecraft:air:0", "minecraft:vine:1;minecraft:air:0", "minecraft:vine:2;minecraft:air:0", "minecraft:vine:3;minecraft:air:0", "minecraft:vine:4;minecraft:air:0", "minecraft:vine:5;minecraft:air:0", "minecraft:vine:6;minecraft:air:0", "minecraft:vine:7;minecraft:air:0", "minecraft:vine:8;minecraft:air:0", "minecraft:vine:9;minecraft:air:0", "minecraft:vine:10;minecraft:air:0", "minecraft:vine:11;minecraft:air:0", "minecraft:vine:12;minecraft:air:0", "minecraft:vine:13;minecraft:air:0", "minecraft:vine:14;minecraft:air:0", "minecraft:vine:15;minecraft:air:0", "minecraft:sapling:0;subspaceparasite:hirsute_hair:0", "minecraft:sapling:1;subspaceparasite:hirsute_hair:0", "minecraft:sapling:2;subspaceparasite:hirsute_hair:0", "minecraft:sapling:3;subspaceparasite:hirsute_hair:0", "minecraft:sapling:4;subspaceparasite:hirsute_hair:0", "minecraft:sapling:5;subspaceparasite:hirsute_hair:0", "minecraft:tallgrass:0;subspaceparasite:hirsute_hair:0", "minecraft:tallgrass:1;subspaceparasite:hirsute_hair:0", "minecraft:tallgrass:2;subspaceparasite:hirsute_hair:0", "minecraft:deadbush:0;subspaceparasite:hirsute_hair:0", "minecraft:yellow_flower:0;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:0;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:1;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:2;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:3;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:4;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:5;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:6;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:7;subspaceparasite:hirsute_hair:0", "minecraft:red_flower:8;subspaceparasite:hirsute_hair:0", "minecraft:brown_mushroom:0;subspaceparasite:hirsute_hair:0", "minecraft:red_mushroom:0;subspaceparasite:hirsute_hair:0"};
    private static final String WORLDBIOMEFOUR_CATEGORY = "world_biome_04";
    public static int biomeFourWeight = 0;
    public static int biomeFourSkyColor = 5089992;
    public static int biomeFourGrassColor = 5089992;
    public static int biomeFourFoliageColor = 5089992;
    public static int biomeFourWaterColor = 0xA60000;
    public static float biomeFourFogRed = 100.0f;
    public static float biomeFourFogBlue = 100.0f;
    public static float biomeFourFogGreen = 100.0f;
    public static float biomeFourHealPenalty = 0.5f;
    public static String[] biomeFourSpawnEntry = new String[]{"subspaceparasite:ada_summoner;1;1;10;0", "subspaceparasite:ada_longarms;1;1;10;0", "subspaceparasite:ada_reeker;1;1;10;0", "subspaceparasite:ada_manducater;1;1;10;0", "subspaceparasite:ada_bolster;1;1;10;0", "subspaceparasite:ada_yelloweye;1;1;10;0", "subspaceparasite:ada_arachnida;1;1;10;0", "subspaceparasite:ada_devourer;1;1;10;0", "subspaceparasite:ada_viscera;1;1;10;0", "subspaceparasite:overseer;2;4;5;0", "subspaceparasite:vigilante;1;3;5;0", "subspaceparasite:warden;1;1;5;0", "subspaceparasite:marauder;2;3;5;0", "subspaceparasite:bomber_light;2;5;5;0", "subspaceparasite:monarch;2;5;5;0", "subspaceparasite:grunt;1;1;20;0", "subspaceparasite:bomber_heavy;1;1;5;0", "subspaceparasite:wraith;2;2;5;0", "subspaceparasite:bogle;1;1;5;0", "subspaceparasite:haunter;1;1;5;0", "subspaceparasite:carrier_colony;1;1;5;0", "subspaceparasite:worker;1;1;10;0"};
    public static String[] biomeFourBlockList = new String[]{"minecraft:leaves:6;subspaceparasite:parasitestain:4"};
    private static final String WORLDCOLONY_CATEGORY = "world_colony";
    public static boolean coloniesActivated = true;
    public static int maximumNumberColonies = 20;
    public static int minimumDistanceBetweenColonies = 2000;
    public static int[] blackListedDimensionsColonies = new int[]{0, 1, -1};
    public static int colonySpreadPoint = 2;
    public static int colonySpreadValue = 20;
    public static int colonyBaseRadiusValue = 120;
    public static int colonySpreadEffectPoint = 1;
    public static int colonySpreadEffectValue = 40;
    public static int colonyBaseEffectRadiusValue = 300;
    public static int colonyPointCap = 100;
    public static int colonyTotalPointCap = 100000;
    public static String colonyWarning = "Colony";
    public static int colonyOutpostDistance = 2000;
    public static float colonyExtraHealthPoint = 20.0f;
    public static float colonyExtraHealthValue = 0.1f;
    public static float colonyExtraArmorPoint = 20.0f;
    public static float colonyExtraArmorValue = 0.1f;
    public static float colonyExtraKDResPoint = 20.0f;
    public static float colonyExtraKDResValue = 0.1f;
    public static float colonyExtraDamagePoint = 20.0f;
    public static float colonyExtraDamageValue = 0.1f;
    public static float colonyDamageCapPoint = 15.0f;
    public static float colonyDamageCapValue = 0.5f;
    public static float colonyPointReductionPoint = 10.0f;
    public static float colonyPointReductionValue = 0.05f;
    public static float colonyPointBoostPoint = 10.0f;
    public static float colonyPointBoostValue = 0.05f;
    public static float colonyExtraRSChancePoint = 20.0f;
    public static float colonyExtraRSChanceValue = 1.0f;
    public static boolean dodColony = true;
    public static String[] preeValues = new String[]{"328;5", "65;10", "85;30", "86;15", "87;25", "88;3", "89;1", "90;1"};
    public static boolean preeValuesBiome = false;
    public static String[] blockLootCommon = new String[]{"subspaceparasite:assimilated_flesh", "subspaceparasite:bone"};
    public static String[] blockLootUncommon = new String[]{"subspaceparasite:ada_summoner_drop", "subspaceparasite:ada_yelloweye_drop", "subspaceparasite:ada_manducater_drop", "subspaceparasite:ada_reeker_drop", "subspaceparasite:ada_longarms_drop", "subspaceparasite:ada_bolster_drop", "subspaceparasite:ada_arachnida_drop", "subspaceparasite:ada_devourer_drop", "subspaceparasite:assimilated_flesh", "subspaceparasite:bone"};
    public static String[] blockLootRare = new String[]{"subspaceparasite:beckon_drop", "subspaceparasite:dispatcher_drop", "subspaceparasite:lurecomponent4", "subspaceparasite:lurecomponent5", "subspaceparasite:lurecomponent6"};
    private static final String WORLDORIGIN_CATEGORY = "world_emerging_infestation_vector";
    public static boolean originActivated = true;
    public static int originCap = 2;
    public static int originRadius = 200;
    public static int originHealth = 350;
    public static int originRadiusCap = 2000000000;
    public static int originHealthCap = 2000000000;
    public static double originDailySize = 1.35;
    public static double originDailyHealth = 0.3;
    public static int originMinimumDistance = 10000;
    public static double originDailyEPPoints = 0.15;
    public static double originDailyEPPointsOutBreak = 0.01;
    public static int originCreatingRand = 10;
    public static int originCreatingRandZero = 3;
    public static int originCreatingDistanceMin = 1000;
    public static int originCreatingDistanceMax = 10000;
    public static int originWorldCheckDebugSpeed = 0;
    public static int originSpotted = 4500;
    public static String originNewMess = "";
    public static String originNewOutbreakMess = "";
    public static String originGone = "";
    public static String originGoneOB = "";
    public static double originTriggerKill = 0.05;
    public static double originCOTHMultiplier = 1.0;
    public static double originParasiteDeath = 1.0;
    public static double originKillMultiplier = 1.0;

    private static void initGeneralWorldConfig(Configuration cfg) {
        String u = "";
        cfg.addCustomCategoryComment(CATEGORY_GENERAL_WORLD, "World configuration \nVersion:1.10.6\n \nBlocks IDs \n \n \n \n \n \n \n \n \n ");
        infestationConvertBlocks = cfg.getStringList("Infestation Convert Blocks", CATEGORY_GENERAL_WORLD, SPConfigBlockConversions.DEFAULT_INFESTATION_CONVERT_BLOCKS, "List of specific block conversions for infestation. Format: \"from;to\".\nBlocks can include meta: \"modid:block:meta;modid:block:meta\".\nExample: minecraft:cactus;subspaceparasite:infested_cactus");
        meteorTick = cfg.getInt("Meteor Ticks", CATEGORY_GENERAL_WORLD, meteorTick, 1, Integer.MAX_VALUE, "Number of ticks required to check Meteor Spawn.");
        meteorDamage = cfg.getInt("Meteor Damage", CATEGORY_GENERAL_WORLD, meteorDamage, 1, Integer.MAX_VALUE, "Meteor Damage.");
        meteorVectorless = cfg.getBoolean("Meteor Vectorless", CATEGORY_GENERAL_WORLD, meteorVectorless, "Only spawn a Meteor if there are no EIV in the World.");
        meteorActive = cfg.getBoolean("Meteor Enabled", CATEGORY_GENERAL_WORLD, meteorActive, "Set to tru if you want meteors to spawn.");
        meteorChance = cfg.getFloat("Meteor Chance", CATEGORY_GENERAL_WORLD, (float)meteorChance, 0.0f, 1.0f, "Chance to spawn a Meteor when doing the check, needs to be greater than Minimum Radius.");
        meteorMinRadius = cfg.getInt("Meteor Minimum Radius", CATEGORY_GENERAL_WORLD, meteorMinRadius, 1, 110, "Minimum distance used at which the Meteor will spawn from a Player.");
        meteorRadius = cfg.getInt("Meteor Radius", CATEGORY_GENERAL_WORLD, meteorRadius, 1, 120, "Maximum distance used at which the Meteor will spawn from a Player.");
        meteorBlacklistDims = cfg.get(CATEGORY_GENERAL_WORLD, "Meteor Blacklisted Dimensions", meteorBlacklistDims, "Meteor will not spawn in these dimensions.").getIntList();
        bloodyIceBreakOnHardLanding = cfg.getBoolean("Bloody Ice breaks on hard landing", CATEGORY_GENERAL_WORLD, bloodyIceBreakOnHardLanding, "If true, Bloody Ice breaks when a player lands hard on it.");
        bloodyIceBreakFallDistance = cfg.getFloat("Bloody Ice break fall distance", CATEGORY_GENERAL_WORLD, (float)bloodyIceBreakFallDistance, 0.0f, 256.0f, "Minimum fall distance (in blocks) that will shatter Bloody Ice.");
        bloodyIceBreakDiameter = cfg.getInt("Bloody Ice break diameter", CATEGORY_GENERAL_WORLD, bloodyIceBreakDiameter, 1, 25, "Square diameter (odd number recommended) of ice to break around impact (e.g., 3 = 3x3).");
        frozenVariantTempThreshold = cfg.getFloat("Frozen variant temperature threshold", CATEGORY_GENERAL_WORLD, (float)frozenVariantTempThreshold, -1.0f, 2.0f, "Temperature threshold for using frozen parasite variants (Lodo, Inf Human, etc).\nIf the biome temperature at the mob's position is <= this value, it will spawn as a frozen variant,\nunless the biome is blacklisted below. Vanilla cold biomes are 0.15 or less.");
        frozenVariantBiomeBlacklist = cfg.getStringList("Frozen variant biome blacklist", CATEGORY_GENERAL_WORLD, frozenVariantBiomeBlacklist, "Biome blacklist for frozen parasite variants.\nUse biome registry names like:\n  minecraft:cold_taiga\n  minecraft:frozen_ocean\n  biomesoplenty:alps\n\nIf a biome is in this list, frozen variants will NOT be used there,\neven if the temperature is below frozenVariantTempThreshold.");
        venkrolTornadoEnabled = cfg.getBoolean("Enable Venkrol tornadoes", CATEGORY_GENERAL_WORLD, venkrolTornadoEnabled, "If false, Venkrol tornadoes will never spawn or apply any effects.\nThis disables both the visual layer and the gameplay pull / lift.");
        fogNullifierMaxUses = cfg.getInt("Fog Nullifier max uses", CATEGORY_GENERAL_WORLD, fogNullifierMaxUses, 0, Integer.MAX_VALUE, "How many fog-clears a Fog Nullifier block can perform before breaking.");
        enableCelestialObjects = cfg.getBoolean("Enable SP celestial objects", CATEGORY_GENERAL_WORLD, enableCelestialObjects, "Enable SP celestial objects in the sky.\nSet to false to completely disable SP's custom celestial rendering and logic.");
        bushClimbingEnabled = cfg.getBoolean("Enable bush climbing", CATEGORY_GENERAL_WORLD, bushClimbingEnabled, "If true, bush-type blocks can be climbed.");
        parasiteLootDamageOnTake = cfg.getBoolean("Parasite loot damages on take", CATEGORY_GENERAL_WORLD, parasiteLootDamageOnTake, "Allow Parasite loot block to damage the player when the item is taken.");
        residueFlammableWave = cfg.getBoolean("Residue flammable wave", CATEGORY_GENERAL_WORLD, residueFlammableWave, "Infested Residue can periodically ignite and create a burning wave.\nSet false to disable special burning. Warning: may be resource-heavy.");
        thornshadeGrowthEnabled = cfg.getBoolean("Enable Thornshade growth", CATEGORY_GENERAL_WORLD, thornshadeGrowthEnabled, "Enable Thornshade growth and raw-meat bonemeal.");
        thornshadeGrowthChance = cfg.getInt("Thornshade growth chance", CATEGORY_GENERAL_WORLD, thornshadeGrowthChance, 1, Short.MAX_VALUE, "Random tick chance (1 in N) for Thornshade to advance a stage.");
        thornshadeRawMeatBonemeal = cfg.getBoolean("Thornshade raw meat bonemeal", CATEGORY_GENERAL_WORLD, thornshadeRawMeatBonemeal, "Allow raw meat to act as bonemeal for Thornshade.");
        boughSpawnReplacement = cfg.getBoolean("Bough spawns replacement", CATEGORY_GENERAL_WORLD, boughSpawnReplacement, "If true, committing sepeku with the Bough spawns a replacement mob at your death location.");
        boughReplacementMobId = cfg.getString("Bough replacement mob", CATEGORY_GENERAL_WORLD, boughReplacementMobId, "Entity registry name to spawn when using the Bough.\nExamples:\n  subspaceparasite:sim_adventurer\n  minecraft:zombie\nSet to \"none\" to disable spawning.");
        spawnerSKYLimitUp = cfg.getInt("Spawner SKY Height", CATEGORY_GENERAL_WORLD, spawnerSKYLimitUp, 0, 256, "Maximum Height for mobs type.SKY to spawn.");
        cfg.addCustomCategoryComment(ESCAPE_CATEGORY, "Escape system configuration.");
        escapeEnabled = cfg.getBoolean("Enable Escape system", ESCAPE_CATEGORY, escapeEnabled, "If false, the escape system will never trigger.");
        escapeMinDistance = cfg.getInt("Escape minimum distance", ESCAPE_CATEGORY, escapeMinDistance, 0, 100000000, "Minimum distance from the source that escape teleport can choose.");
        escapeMaxDistance = cfg.getInt("Escape maximum distance", ESCAPE_CATEGORY, escapeMaxDistance, 0, 100000000, "Maximum distance from the source that escape teleport can choose.");
    }

    private static void initworldbiomeConfig(Configuration cfg) {
        String description = " \n A Node is created when an infested area has been laying around for some time, a node\n will change the biome and will have bonuses to the parasites.";
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n \"0\" is the type (0 for entityType.MONSTER, 1 for entityType.CREATURE). \n";
        String entry2 = " Ex. \"2;minecraft:speed;3\"  Where: \n \"2\" is the required total of node levels, \n \"minecraft:speed\" is the potion itself (the potion will apply regardless of whether the parasite is near a node or not), \n \"3\" is the amplifier of the effect ";
        cfg.addCustomCategoryComment(WORLDBIOME_CATEGORY, "World Biome" + description);
        biomeRegster = cfg.getBoolean("Biome Acivated", WORLDBIOME_CATEGORY, biomeRegster, "Set to false if you dont want to register the biome into your game (Needed for this category to work).");
        nodesActivated = cfg.getBoolean("Creation of Nodes", WORLDBIOME_CATEGORY, nodesActivated, "Set to false if you dont want Nodes to be created in your world.");
        maximumNumberNodes = cfg.getInt("Maximum Node Number", WORLDBIOME_CATEGORY, maximumNumberNodes, 1, 100, "Maximum number of nodes in a world.");
        minimumDistanceBetweenNodes = cfg.getInt("Minimum Distance Between Nodes", WORLDBIOME_CATEGORY, minimumDistanceBetweenNodes, 0, 100000000, "Minimum distance that one node needs to be away from the other to appear.");
        minimumDistanceFromSpawnPoint = cfg.getInt("Minimum Distance From SpawnPoint", WORLDBIOME_CATEGORY, minimumDistanceFromSpawnPoint, 0, 100000000, "Minimum distance one node needs to be away from SpawnPoint to appear.");
        blackListedDimensionsNodes = cfg.get(WORLDBIOME_CATEGORY, "WhiteList Dimensions", blackListedDimensionsNodes, "Nodes can only be made in these dimensions").getIntList();
        blockBBiomeList = cfg.getStringList("Biome Blocks BlackList", WORLDBIOME_CATEGORY, blockBBiomeList, "List of block that can't be infested, Ex: \"minecraft:stonebrick\" or just \"minecraft\" for a whole mod");
        blockBBiomeListWhite = cfg.getBoolean("Biome Blocks BlackList Inverted", WORLDBIOME_CATEGORY, blockBBiomeListWhite, "Set to true if you want to use the list as a WhiteList.");
        biomeBlockIMaxH = cfg.getFloat("Biome Hardness", WORLDBIOME_CATEGORY, biomeBlockIMaxH, 0.01f, 100.0f, "Max Hardness of the block it can spread into.");
        nodeWarning = cfg.getString("Node Warning Message", WORLDBIOME_CATEGORY, nodeWarning, "Message sent to all players in the current world when a Node is placed");
        biomeFogDensity = cfg.getFloat("Biome Fog Density", WORLDBIOME_CATEGORY, biomeFogDensity, 0.0f, 1.0f, "Amount of fog the biome will have.");
        biomeHealPenaltyBlackList = cfg.getStringList("Biome Heal Penalty BlackList", WORLDBIOME_CATEGORY, biomeHealPenaltyBlackList, "Mobs that are immune to this penalty. Ex: \"minecraft:zombie\" or just \"minecraft\" for a whole mod.");
        biomeHealPenaltyBlackListWhite = cfg.getBoolean("Biome Heal Penalty BlackList Inverted", WORLDBIOME_CATEGORY, biomeHealPenaltyBlackListWhite, "Set to true if you want to use the list as a WhiteList.");
        potionEffectForNodes = cfg.getStringList("Node Potion Effect List", WORLDBIOME_CATEGORY, potionEffectForNodes, "List of potion effects that the parasites will spawn with." + entry2);
        biomeBlackList = cfg.getStringList("Biome Spread BlackList", WORLDBIOME_CATEGORY, biomeBlackList, "List of biomes that cannot be converted. Ex: minecraft:plains or just minecraft");
        biomeBlackListInverted = cfg.getBoolean("Biome Spread BlackList Inverted", WORLDBIOME_CATEGORY, biomeBlackListInverted, "\"Set to true if you want to use the list as a WhiteList.");
        biomeHeartVol = cfg.getFloat("Biome Node Heart Volume", WORLDBIOME_CATEGORY, biomeHeartVol, 0.0f, 1000.0f, "Volume of the Core Block Node Sound.");
        biomeHeartFreq = cfg.getInt("Biome Node Heart Frequency", WORLDBIOME_CATEGORY, biomeHeartFreq, 0, 100, "Frequency of the Core Block Node Sound.");
        venkrolNode = cfg.getBoolean("Nodes Beckons", WORLDBIOME_CATEGORY, venkrolNode, "Set to false if you dont want Colonies to be created by a Beckon SIV. (This option is ignored if Evolution Phases are enabled, it has its own option)");
        nodeRangeSpreadOne = cfg.getInt("Node 1 Spread Range", WORLDBIOME_CATEGORY, nodeRangeSpreadOne, 0, 0x7FFFFFF8, "Spread range of a Node 1.");
        nodeRangeEffectsOne = cfg.getInt("Node 1 Effect Range", WORLDBIOME_CATEGORY, nodeRangeEffectsOne, 0, 0x7FFFFFF8, "Effect range of a Node 1.");
        timeNeedeToNodeOne = cfg.getInt("Node 1 Time Needed", WORLDBIOME_CATEGORY, timeNeedeToNodeOne, 0, 0x7FFFFFF8, "Days needed from a sss to a Node 1.");
        nodeRangeSpreadTwo = cfg.getInt("Node 2 Spread Range", WORLDBIOME_CATEGORY, nodeRangeSpreadTwo, 0, 0x7FFFFFF8, "Spread range of a Node 2.");
        nodeRangeEffectsTwo = cfg.getInt("Node 2 Effect Range", WORLDBIOME_CATEGORY, nodeRangeEffectsTwo, 0, 0x7FFFFFF8, "Effect range of a Node 2.");
        timeNeedeToNodeTwo = cfg.getInt("Node 2 Time Needed", WORLDBIOME_CATEGORY, timeNeedeToNodeTwo, 0, 0x7FFFFFF8, "Days needed from a Node 1 to a Node 2.");
        nodeRangeSpreadThree = cfg.getInt("Node 3 Spread Range", WORLDBIOME_CATEGORY, nodeRangeSpreadThree, 0, 0x7FFFFFF8, "Spread range of a Node 3.");
        nodeRangeEffectsThree = cfg.getInt("Node 3 Effect Range", WORLDBIOME_CATEGORY, nodeRangeEffectsThree, 0, 0x7FFFFFF8, "Effect range of a Node 3.");
        timeNeedeToNodeThree = cfg.getInt("Node 3 Time Needed", WORLDBIOME_CATEGORY, timeNeedeToNodeThree, 0, 0x7FFFFFF8, "Days needed from a Node 2 to a Node 3.");
        nodeCropStopNodeOne = cfg.getFloat("Node 1 Crop Stop", WORLDBIOME_CATEGORY, nodeCropStopNodeOne, 0.0f, 100.0f, "Chance (1 = 100%) to stop crops/trees from growing naturally.(for a tick) for a Node 1");
        nodeCropStopNodeTwo = cfg.getFloat("Node 2 Crop Stop", WORLDBIOME_CATEGORY, nodeCropStopNodeTwo, 0.0f, 100.0f, "Chance (1 = 100%) to stop crops/trees from growing naturally.(for a tick) for a Node 2");
        nodeCropStopNodeThree = cfg.getFloat("Node 3 Crop Stop", WORLDBIOME_CATEGORY, nodeCropStopNodeThree, 0.0f, 100.0f, "Chance (1 = 100%) to stop crops/trees from growing naturally.(for a tick) for a Node 3");
    }

    private static void initworldbiomeOneConfig(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n \"0\" is the type (0 for entityType.MONSTER, 1 for entityType.CREATURE). \n";
        String entry2 = " Ex. \"minecraft:grass:0;subspaceparasite:parasitestain:1\"  Where: \n \"minecraft:grass:0\" is the block source, meta value important \n \"subspaceparasite:parasitestain:1\" is the block to place instead, meta value is also important. \n";
        cfg.addCustomCategoryComment(WORLDBIOMEONE_CATEGORY, "World Biome Shrouded");
        biomeOneSkyColor = cfg.getInt("Biome Shrouded Color Sky", WORLDBIOMEONE_CATEGORY, biomeOneSkyColor, 0, 0x7FFFFFF8, "Decimal color code for the Sky of the Biome Shrouded.");
        biomeOneGrassColor = cfg.getInt("Biome Shrouded Color Grass", WORLDBIOMEONE_CATEGORY, biomeOneGrassColor, 0, 0x7FFFFFF8, "Decimal color code for the Grass of the Biome Shrouded.");
        biomeOneFoliageColor = cfg.getInt("Biome Shrouded Color Foliage", WORLDBIOMEONE_CATEGORY, biomeOneFoliageColor, 0, 0x7FFFFFF8, "Decimal color code for the Foliage of the Biome Shrouded.");
        biomeOneWaterColor = cfg.getInt("Biome Shrouded Color Water", WORLDBIOMEONE_CATEGORY, biomeOneWaterColor, 0, 0x7FFFFFF8, "Decimal color code for the Water of the Biome Shrouded.");
        biomeOneFogRed = cfg.getFloat("Biome Shrouded Fog Color Red Value", WORLDBIOMEONE_CATEGORY, biomeOneFogRed, 0.0f, 255.0f, "Red color value for the fog of the Biome Shrouded.");
        biomeOneFogGreen = cfg.getFloat("Biome Shrouded Fog Color Green Value", WORLDBIOMEONE_CATEGORY, biomeOneFogGreen, 0.0f, 255.0f, "Green color value for the fog of the Biome Shrouded.");
        biomeOneFogBlue = cfg.getFloat("Biome Shrouded Fog Color Blue Value", WORLDBIOMEONE_CATEGORY, biomeOneFogBlue, 0.0f, 255.0f, "Blue color value for the fog of the Biome Shrouded.");
        biomeOneSpawnEntry = cfg.getStringList("Biome Shrouded Spawn Entity List", WORLDBIOMEONE_CATEGORY, biomeOneSpawnEntry, "Entity List that will spawn at the Biome Shrouded." + entry);
        biomeOneBlockList = cfg.getStringList("Biome Shrouded Block List", WORLDBIOMEONE_CATEGORY, biomeOneBlockList, "List of block convertion" + entry2);
        biomeOneHealPenalty = cfg.getFloat("Biome Shrouded Heal Penalty", WORLDBIOMEONE_CATEGORY, biomeOneHealPenalty, 0.0f, 1.0f, "Amount of health reduced (1=100%) for mobs and player healing in the biomeOne.");
        biomeOneWeight = cfg.getInt("Biome Shrouded Weight", WORLDBIOMEONE_CATEGORY, biomeOneWeight, 0, 10000, "Biome Shrouded weight, registered as a SPOOKY type biomeOne");
    }

    private static void initworldbiomeTwoConfig(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n \"0\" is the type (0 for entityType.MONSTER, 1 for entityType.CREATURE). \n";
        cfg.addCustomCategoryComment(WORLDBIOMETWO_CATEGORY, "World Biome Boils");
        biomeTwoSkyColor = cfg.getInt("Biome Boils Color Sky", WORLDBIOMETWO_CATEGORY, biomeTwoSkyColor, 0, 0x7FFFFFF8, "Decimal color code for the Sky of the Biome Boils.");
        biomeTwoGrassColor = cfg.getInt("Biome Boils Color Grass", WORLDBIOMETWO_CATEGORY, biomeTwoGrassColor, 0, 0x7FFFFFF8, "Decimal color code for the Grass of the Biome Boils.");
        biomeTwoFoliageColor = cfg.getInt("Biome Boils Color Foliage", WORLDBIOMETWO_CATEGORY, biomeTwoFoliageColor, 0, 0x7FFFFFF8, "Decimal color code for the Foliage of the Biome Boils.");
        biomeTwoWaterColor = cfg.getInt("Biome Boils Color Water", WORLDBIOMETWO_CATEGORY, biomeTwoWaterColor, 0, 0x7FFFFFF8, "Decimal color code for the Water of the Biome Boils.");
        biomeTwoFogRed = cfg.getFloat("Biome Boils Fog Color Red Value", WORLDBIOMETWO_CATEGORY, biomeTwoFogRed, 0.0f, 255.0f, "Red color value for the fog of the Biome Boils.");
        biomeTwoFogGreen = cfg.getFloat("Biome Boils Fog Color Green Value", WORLDBIOMETWO_CATEGORY, biomeTwoFogGreen, 0.0f, 255.0f, "Green color value for the fog of the Biome Boils.");
        biomeTwoFogBlue = cfg.getFloat("Biome Boils Fog Color Blue Value", WORLDBIOMETWO_CATEGORY, biomeTwoFogBlue, 0.0f, 255.0f, "Blue color value for the fog of the Biome Boils.");
        biomeTwoSpawnEntry = cfg.getStringList("Biome Boils Spawn Entity List", WORLDBIOMETWO_CATEGORY, biomeTwoSpawnEntry, "Entity List that will spawn at the Biome Boils." + entry);
        biomeTwoHealPenalty = cfg.getFloat("Biome Boils Heal Penalty", WORLDBIOMETWO_CATEGORY, biomeTwoHealPenalty, 0.0f, 1.0f, "Amount of health reduced (1=100%) for mobs and player healing in the biomeTwo.");
        biomeTwoWeight = cfg.getInt("Biome Boils Weight", WORLDBIOMETWO_CATEGORY, biomeTwoWeight, 0, 10000, "Biome Boils weight, registered as a SPOOKY type biomeTwo");
    }

    private static void initworldbiomeThreeConfig(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n \"0\" is the type (0 for entityType.MONSTER, 1 for entityType.CREATURE). \n";
        String entry2 = " Ex. \"minecraft:grass:0;subspaceparasite:parasitestain:1\"  Where: \n \"minecraft:grass:0\" is the block source, meta value important \n \"subspaceparasite:parasitestain:1\" is the block to place instead, meta value is also important. \n";
        cfg.addCustomCategoryComment(WORLDBIOMETHREE_CATEGORY, "World Biome Harlequin");
        biomeThreeSkyColor = cfg.getInt("Biome Harlequin Color Sky", WORLDBIOMETHREE_CATEGORY, biomeThreeSkyColor, 0, 0x7FFFFFF8, "Decimal color code for the Sky of the Biome Harlequin.");
        biomeThreeGrassColor = cfg.getInt("Biome Harlequin Color Grass", WORLDBIOMETHREE_CATEGORY, biomeThreeGrassColor, 0, 0x7FFFFFF8, "Decimal color code for the Grass of the Biome Harlequin.");
        biomeThreeFoliageColor = cfg.getInt("Biome Harlequin Color Foliage", WORLDBIOMETHREE_CATEGORY, biomeThreeFoliageColor, 0, 0x7FFFFFF8, "Decimal color code for the Foliage of the Biome Harlequin.");
        biomeThreeWaterColor = cfg.getInt("Biome Harlequin Color Water", WORLDBIOMETHREE_CATEGORY, biomeThreeWaterColor, 0, 0x7FFFFFF8, "Decimal color code for the Water of the Biome Harlequin.");
        biomeThreeFogRed = cfg.getFloat("Biome Harlequin Fog Color Red Value", WORLDBIOMETHREE_CATEGORY, biomeThreeFogRed, 0.0f, 255.0f, "Red color value for the fog of the Biome Harlequin.");
        biomeThreeFogGreen = cfg.getFloat("Biome Harlequin Fog Color Green Value", WORLDBIOMETHREE_CATEGORY, biomeThreeFogGreen, 0.0f, 255.0f, "Green color value for the fog of the Biome Harlequin.");
        biomeThreeFogBlue = cfg.getFloat("Biome Harlequin Fog Color Blue Value", WORLDBIOMETHREE_CATEGORY, biomeThreeFogBlue, 0.0f, 255.0f, "Blue color value for the fog of the Biome Harlequin.");
        biomeThreeSpawnEntry = cfg.getStringList("Biome Harlequin Spawn Entity List", WORLDBIOMETHREE_CATEGORY, biomeThreeSpawnEntry, "Entity List that will spawn at the Biome Harlequin." + entry);
        biomeThreeBlockList = cfg.getStringList("Biome Harlequin Block List", WORLDBIOMETHREE_CATEGORY, biomeThreeBlockList, "List of block convertion" + entry2);
        biomeThreeHealPenalty = cfg.getFloat("Biome Harlequin Heal Penalty", WORLDBIOMETHREE_CATEGORY, biomeThreeHealPenalty, 0.0f, 1.0f, "Amount of health reduced (1=100%) for mobs and player healing in the biomeThree.");
        biomeThreeWeight = cfg.getInt("Biome Harlequin Weight", WORLDBIOMETHREE_CATEGORY, biomeThreeWeight, 0, 10000, "Biome Harlequin weight, registered as a SPOOKY type biomeThree");
    }

    private static void initworldbiomeFourConfig(Configuration cfg) {
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"1\" is the minimum group count. \n \"3\" is the maximum group count. \n \"90\" is spawn weight. \n \"0\" is the type (0 for entityType.MONSTER, 1 for entityType.CREATURE). \n";
        cfg.addCustomCategoryComment(WORLDBIOMEFOUR_CATEGORY, "World Biome Demen");
        biomeFourSkyColor = cfg.getInt("Biome Demen Color Sky", WORLDBIOMEFOUR_CATEGORY, biomeFourSkyColor, 0, 0x7FFFFFF8, "Decimal color code for the Sky of the Biome Demen.");
        biomeFourGrassColor = cfg.getInt("Biome Demen Color Grass", WORLDBIOMEFOUR_CATEGORY, biomeFourGrassColor, 0, 0x7FFFFFF8, "Decimal color code for the Grass of the Biome Demen.");
        biomeFourFoliageColor = cfg.getInt("Biome Demen Color Foliage", WORLDBIOMEFOUR_CATEGORY, biomeFourFoliageColor, 0, 0x7FFFFFF8, "Decimal color code for the Foliage of the Biome Demen.");
        biomeFourWaterColor = cfg.getInt("Biome Demen Color Water", WORLDBIOMEFOUR_CATEGORY, biomeFourWaterColor, 0, 0x7FFFFFF8, "Decimal color code for the Water of the Biome Demen.");
        biomeFourFogRed = cfg.getFloat("Biome Demen Fog Color Red Value", WORLDBIOMEFOUR_CATEGORY, biomeFourFogRed, 0.0f, 255.0f, "Red color value for the fog of the Biome Demen.");
        biomeFourFogGreen = cfg.getFloat("Biome Demen Fog Color Green Value", WORLDBIOMEFOUR_CATEGORY, biomeFourFogGreen, 0.0f, 255.0f, "Green color value for the fog of the Biome Demen.");
        biomeFourFogBlue = cfg.getFloat("Biome Demen Fog Color Blue Value", WORLDBIOMEFOUR_CATEGORY, biomeFourFogBlue, 0.0f, 255.0f, "Blue color value for the fog of the Biome Demen.");
        biomeFourSpawnEntry = cfg.getStringList("Biome Demen Spawn Entity List", WORLDBIOMEFOUR_CATEGORY, biomeFourSpawnEntry, "Entity List that will spawn at the Biome Demen." + entry);
        biomeFourHealPenalty = cfg.getFloat("Biome Demen Heal Penalty", WORLDBIOMEFOUR_CATEGORY, biomeFourHealPenalty, 0.0f, 1.0f, "Amount of health reduced (1=100%) for mobs and player healing in the biomeFour.");
        biomeFourWeight = cfg.getInt("Biome Demen Weight", WORLDBIOMEFOUR_CATEGORY, biomeFourWeight, 0, 10000, "Biome Demen weight, registered as a SPOOKY type biomeFour");
    }

    private static void initworldcolonyConfig(Configuration cfg) {
        escapeMinDistance = cfg.getInt("Escape Min Distance", ESCAPE_CATEGORY, escapeMinDistance, 0, 10000, "Minimum escape teleport distance in blocks.");
        escapeMaxDistance = cfg.getInt("Escape Max Distance", ESCAPE_CATEGORY, escapeMaxDistance, 0, 10000, "Maximum escape teleport distance in blocks.");
        escapeEnabled = cfg.getBoolean("Escape Enabled", ESCAPE_CATEGORY, escapeEnabled, "Enable the Escape-on-death option on the death screen.");
        String description = " \n A Node is created when an infested area has been laying around for some time, a node\n will change the biome and will have bonuses to the parasites.";
        String entry = " Ex. \"minecraft:zombie;1;3;90;0\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"1\" is for the minimum group count. \n \"3\" is for the maximum group count. \n \"90\" is for spawn weight. \n \"0\" is for the type (0 for entityType.MONSTER, 1 for entityType.CREATURE). \n";
        String valll = " Ex. \"50:1\"  Where: \n \"50\" is the parasite second id, \n \"1\" are the points required (from colonies) to spawn. \n";
        cfg.addCustomCategoryComment(WORLDCOLONY_CATEGORY, "World Colony" + description);
        coloniesActivated = cfg.getBoolean("Colonies Activated", WORLDCOLONY_CATEGORY, coloniesActivated, "Set to false if you dont want Colonies to be created in your world.");
        maximumNumberColonies = cfg.getInt("Maximum Colony Number", WORLDCOLONY_CATEGORY, maximumNumberColonies, 1, 100, "Maximum number of Colonies in a world.");
        minimumDistanceBetweenColonies = cfg.getInt("Minimum Distance Between Colonies", WORLDCOLONY_CATEGORY, minimumDistanceBetweenColonies, 0, 100000000, "Minimum distance that one Colony needs to be away from the other to appear.");
        blackListedDimensionsColonies = cfg.get(WORLDCOLONY_CATEGORY, "WhiteList Dimensions", blackListedDimensionsColonies, "Colonies can only be made in these dimensions").getIntList();
        colonyPointCap = cfg.getInt("Colonies Points Cap", WORLDCOLONY_CATEGORY, colonyPointCap, 1, 10000, "Cap of points for a single Colony.");
        colonyTotalPointCap = cfg.getInt("Colonies Total Points Cap", WORLDCOLONY_CATEGORY, colonyTotalPointCap, 1, 10000, "Total cap of points for all Colonies.");
        colonySpreadPoint = cfg.getInt("Colonies Spread Point", WORLDCOLONY_CATEGORY, colonySpreadPoint, 1, 10000, "Every x points, the colony radius will get bigger.");
        colonySpreadValue = cfg.getInt("Colonies Spread Value", WORLDCOLONY_CATEGORY, colonySpreadValue, 1, 10000, "How much the radius will grow.");
        colonyBaseRadiusValue = cfg.getInt("Colonies Base Radius Value", WORLDCOLONY_CATEGORY, colonyBaseRadiusValue, 1, 10000, "Value of the base radius of a Colony.");
        colonySpreadEffectPoint = cfg.getInt("Colonies Spread Effect Point", WORLDCOLONY_CATEGORY, colonySpreadEffectPoint, 1, 10000, "Every x points, the colony effect radius will get bigger.");
        colonySpreadEffectValue = cfg.getInt("Colonies Spread Effect Value", WORLDCOLONY_CATEGORY, colonySpreadEffectValue, 1, 10000, "How much the effect radius will grow.");
        colonyBaseEffectRadiusValue = cfg.getInt("Colonies Base Effect Radius Value", WORLDCOLONY_CATEGORY, colonyBaseEffectRadiusValue, 1, 10000, "Value of the base effect radius of a Colony.");
        colonyWarning = cfg.getString("Colonies Warning Message", WORLDCOLONY_CATEGORY, colonyWarning, "Message sent to all players in the current world when a Colony is placed");
        dodColony = cfg.getBoolean("Colonies Dispatcher", WORLDCOLONY_CATEGORY, dodColony, "Set to false if you dont want Colonies to be created by a Dispatcher SIV. (This option is ignored if Evolution Phases are enabled, it has its own option)");
        colonyOutpostDistance = cfg.getInt("Colonies Outpost Distance", WORLDCOLONY_CATEGORY, colonyOutpostDistance, 1, 10000000, "Distance from the colony to which they are going to start setting up Outposts.");
        colonyExtraHealthPoint = cfg.getFloat("Colony Bonus Health Point", WORLDCOLONY_CATEGORY, colonyExtraHealthPoint, 1.0f, 10000.0f, "Every x points, the colony will grant a bonus of health.");
        colonyExtraHealthValue = cfg.getFloat("Colony Bonus Health Value", WORLDCOLONY_CATEGORY, colonyExtraHealthValue, 0.0f, 10000.0f, "Value of the bonus.");
        colonyExtraArmorPoint = cfg.getFloat("Colony Bonus Armor Point", WORLDCOLONY_CATEGORY, colonyExtraArmorPoint, 1.0f, 10000.0f, "Every x points, the colony will grant a bonus of armor.");
        colonyExtraArmorValue = cfg.getFloat("Colony Bonus Armor Value", WORLDCOLONY_CATEGORY, colonyExtraArmorValue, 0.0f, 10000.0f, "Value of the bonus.");
        colonyExtraDamagePoint = cfg.getFloat("Colony Bonus Damage Point", WORLDCOLONY_CATEGORY, colonyExtraDamagePoint, 1.0f, 10000.0f, "Every x points, the colony will grant a bonus of damage.");
        colonyExtraDamageValue = cfg.getFloat("Colony Bonus Damage Value", WORLDCOLONY_CATEGORY, colonyExtraDamageValue, 0.0f, 10000.0f, "Value of the bonus.");
        colonyExtraKDResPoint = cfg.getFloat("Colony Bonus KDR Point", WORLDCOLONY_CATEGORY, colonyExtraKDResPoint, 1.0f, 10000.0f, "Every x points, the colony will grant a bonus of KDR.");
        colonyExtraKDResValue = cfg.getFloat("Colony Bonus KDR Value", WORLDCOLONY_CATEGORY, colonyExtraKDResValue, 0.0f, 10000.0f, "Value of the bonus.");
        colonyDamageCapPoint = cfg.getFloat("Colony Damage Cap Point", WORLDCOLONY_CATEGORY, colonyDamageCapPoint, 1.0f, 10000.0f, "Every x points, the colony will increase their damage cap.");
        colonyDamageCapValue = cfg.getFloat("Colony Damage Cap Value", WORLDCOLONY_CATEGORY, colonyDamageCapValue, 0.0f, 10000.0f, "Value (in %) of the bonus.");
        colonyPointReductionPoint = cfg.getFloat("Colony EV Reduction Point", WORLDCOLONY_CATEGORY, colonyPointReductionPoint, 1.0f, 10000.0f, "Every x points, the colony will reduce the number of points lost in the evolution phases");
        colonyPointReductionValue = cfg.getFloat("Colony EV Reduction Value", WORLDCOLONY_CATEGORY, colonyPointReductionValue, 0.0f, 1.0f, "Value (in %) of the bonus.");
        colonyPointBoostPoint = cfg.getFloat("Colony EV Boost Point", WORLDCOLONY_CATEGORY, colonyPointBoostPoint, 1.0f, 10000.0f, "Every x points, the colony will boost the number of points earned in the evolution phases");
        colonyPointBoostValue = cfg.getFloat("Colony EV Boost Value", WORLDCOLONY_CATEGORY, colonyPointBoostValue, 0.0f, 1.0f, "Value (in %) of the bonus.");
        colonyExtraRSChancePoint = cfg.getFloat("Colony RS Extra Chance Point", WORLDCOLONY_CATEGORY, colonyExtraRSChancePoint, 1.0f, 10000.0f, "Every x points, the colony will increase the RS chance to send Beckons Stage II or Stage III");
        colonyExtraRSChanceValue = cfg.getFloat("Colony RS Extra Chance Value", WORLDCOLONY_CATEGORY, colonyExtraRSChanceValue, 0.0f, 1.0f, "Value (in %) of the bonus.");
        preeValues = cfg.getStringList("Colony Parasite Values", WORLDCOLONY_CATEGORY, preeValues, "List of parasites that require Colony points to appear, parasites that are not in the list will not be affected." + valll);
        preeValuesBiome = cfg.getBoolean("Colony Parasite Values Biome", WORLDCOLONY_CATEGORY, preeValuesBiome, "Set to false if you want parasites from the list to spawn in Parasite Biomes ignoring points required");
        blockLootCommon = cfg.getStringList("Colony Loot Common", WORLDCOLONY_CATEGORY, blockLootCommon, "Items");
        blockLootRare = cfg.getStringList("Colony Loot Rare", WORLDCOLONY_CATEGORY, blockLootRare, "Items");
    }

    private static void initworldoriginConfig(Configuration cfg) {
        String description = " \n An Emerging Infestation Vector is an area where parasites are present. Without them, parasites would not be able to spawn in the world.\n Vectors have two key values: area and life.\n -The area defines how large the zone is where the parasites can naturally appear.\n -Life determines how many points the vector will contribute to the evolution phase. \n When parasites are killed within the vector, its life is reduced, and if it reaches 0, the vector disappears from the world.\n Vector gains life points when COTH transforms mobs or when parasites kill.\n \n Vectors can continue to grow even if the player is far away from the area.\n The creation of vectors can be caused by random world checks or by death caused by parasites.";
        cfg.addCustomCategoryComment(WORLDORIGIN_CATEGORY, "World Emerging Infestation Vector" + description);
        originActivated = cfg.getBoolean("Emerging Infestation Vector Activated ", WORLDORIGIN_CATEGORY, originActivated, "Set to false if you dont want Emerging Infestation Vector spawning into your world, will only work if Custom Spawner is enabled.");
        originCap = cfg.getInt("Maximum EIV Number", WORLDORIGIN_CATEGORY, originCap, 1, Integer.MAX_VALUE, "Maximum number of EIVs that can be created in a world, if using Evolution phases this value will not be used.");
        originRadius = cfg.getInt("EIV Default Radius", WORLDORIGIN_CATEGORY, originRadius, 1, Integer.MAX_VALUE, "Default Radius value when an EIV is created.");
        originHealth = cfg.getInt("EIV Default Heatlh", WORLDORIGIN_CATEGORY, originHealth, 1, Integer.MAX_VALUE, "Default Health value when an EIV is created.");
        originRadiusCap = cfg.getInt("EIV Radius Cap", WORLDORIGIN_CATEGORY, originRadiusCap, 1, Integer.MAX_VALUE, "Maximum Radius value for an EIV.");
        originHealthCap = cfg.getInt("EIV Heatlh Cap", WORLDORIGIN_CATEGORY, originHealthCap, 1, Integer.MAX_VALUE, "Maximum Health value for an EIV.");
        originDailySize = cfg.getFloat("EIV Radius Growth", WORLDORIGIN_CATEGORY, (float)originDailySize, 0.0f, 100.0f, "Day-to-day radius growth of an EIV.");
        originDailyHealth = cfg.getFloat("EIV Health Growth", WORLDORIGIN_CATEGORY, (float)originDailyHealth, 0.0f, 100.0f, "Day-to-day health growth of an EIV.");
        originMinimumDistance = cfg.getInt("Minimum EIV Distance", WORLDORIGIN_CATEGORY, originMinimumDistance, 1, 1000000000, "Minimum Distance between EIV.");
        originDailyEPPoints = cfg.getFloat("EIV Points", WORLDORIGIN_CATEGORY, (float)originDailyEPPoints, 1.0f, 1.0E9f, "Day-to-day point Generation using Heatlh value, only working if Evolution Phases are enabled.");
        originDailyEPPointsOutBreak = cfg.getFloat("EIV Points Outbreak", WORLDORIGIN_CATEGORY, (float)originDailyEPPointsOutBreak, 1.0f, 1.0E9f, "Day-to-day point Generation using Heatlh value, only working if Evolution Phases are enabled if this is an Outbreak.");
        originCreatingRand = cfg.getInt("EIV Creation Chance", WORLDORIGIN_CATEGORY, originCreatingRand, 1, Integer.MAX_VALUE, "One in X to create an EIV every day, if available.");
        originCreatingRandZero = cfg.getInt("EIV Creation Chance Zero", WORLDORIGIN_CATEGORY, originCreatingRandZero, 1, Integer.MAX_VALUE, "One in X to create an EIV every day, if there are none.");
        originCreatingDistanceMin = cfg.getInt("EIV Creation XZ Min", WORLDORIGIN_CATEGORY, originCreatingDistanceMin, 1, Integer.MAX_VALUE, "Minimum X and Z coord value (plus) from a player an EIV can be created. Applies only to random EIVs, not ones placed by other conditions.");
        originCreatingDistanceMax = cfg.getInt("EIV Creation XZ Max", WORLDORIGIN_CATEGORY, originCreatingDistanceMax, 1, Integer.MAX_VALUE, "Maximum X and Z coord value (plus) from a player an EIV can be created. Applies only to random EIVs, not ones placed by other conditions.");
        originWorldCheckDebugSpeed = cfg.getInt("EIV World Check Debug Speed", WORLDORIGIN_CATEGORY, originWorldCheckDebugSpeed, 0, 1000000, "Extra amount added to the EIV/world daily check counter each server tick. 0 = normal speed. 500 = fast debug speed. Use only for testing because high values can make EIV checks happen very often.");
        originSpotted = cfg.getInt("EIV Spotted Value", WORLDORIGIN_CATEGORY, originSpotted, 1, Integer.MAX_VALUE, "Health required for Vectors to use Spotted Effect");
        originNewMess = cfg.getString("EIV Message Creation", WORLDORIGIN_CATEGORY, originNewMess, "Message sent to all players in the current world when a Vector is created");
        originNewOutbreakMess = cfg.getString("EIV Message Outbreak", WORLDORIGIN_CATEGORY, originNewOutbreakMess, "Message sent to all players in the current world when an Outbreak is created");
        originGone = cfg.getString("EIV Message Removed", WORLDORIGIN_CATEGORY, originGone, "Message sent to all players in the current world when a Vector is removed");
        originGoneOB = cfg.getString("EIV Message Removed Outbreak", WORLDORIGIN_CATEGORY, originGoneOB, "Message sent to all players in the current world when an Outbreak is removed");
        originTriggerKill = cfg.getFloat("EIV Trigger Kill", WORLDORIGIN_CATEGORY, (float)originTriggerKill, 0.0f, 1.0f, "Chance to create an EIV by Parasites killing mobs.");
        originCOTHMultiplier = cfg.getFloat("EIV COTH Multiplier", WORLDORIGIN_CATEGORY, (float)originCOTHMultiplier, 1.0f, 200.0f, "Multiplier for adding Health by COTH.");
        originParasiteDeath = cfg.getFloat("EIV Parasite Death Multiplier", WORLDORIGIN_CATEGORY, (float)originParasiteDeath, 1.0f, 200.0f, "Multiplier for removing Health by Parasite Death.");
        originKillMultiplier = cfg.getFloat("EIV Kill Multiplier", WORLDORIGIN_CATEGORY, (float)originKillMultiplier, 1.0f, 200.0f, "Multiplier for adding Health by killing.");
    }

    public static void initConfig(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        CommonProxy.configWorld = new Configuration(new File(directory.getPath(), "subspaceparasite/SPParasitesWorld.cfg"));
        SPConfigWorld.readConfig();
    }

    public static boolean readConfig() {
        Configuration cfgW = CommonProxy.configWorld;
        try {
            cfgW.load();
            SPConfigWorld.initGeneralWorldConfig(cfgW);
            SPConfigWorld.initworldbiomeConfig(cfgW);
            SPConfigWorld.initworldbiomeOneConfig(cfgW);
            SPConfigWorld.initworldbiomeThreeConfig(cfgW);
            SPConfigWorld.initworldcolonyConfig(cfgW);
            SPConfigWorld.initworldoriginConfig(cfgW);
            boolean bl = true;
            return bl;
        }
        catch (Exception e) {
            SPMain.logger.log(Level.ERROR, "Problem loading configuration file", (Throwable)e);
        }
        finally {
            if (cfgW.hasChanged()) {
                cfgW.save();
            }
        }
        return false;
    }
}

