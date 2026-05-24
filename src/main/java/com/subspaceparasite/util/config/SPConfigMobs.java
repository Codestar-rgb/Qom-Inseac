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
import com.subspaceparasite.util.SPAttributes;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

public class SPConfigMobs {
    private static final String CATEGORY_GENERAL_MOBS = "configuration_mobs";
    private static String health = " Health Multiplier";
    private static String damage = " Damage Multiplier";
    private static String armor = " Armor Multiplier";
    private static String kd = " Knockback Resistance Multiplier";
    private static String lootXp = " Where: \n \"minecraft:nether_star\" is the item, \n \"100\" is the chance to drop, \n \"5\" is the max number of items, \n \"true\" is for the item to always roll, if false the item will be unique and only 1 will be choosen. \n";
    private static final String SHYCO_CATEGORY = "subspaceparasite:longarms";
    public static float shycoHealthMultiplier = 1.0f;
    public static float shycoDamageMultiplier = 1.0f;
    public static float shycoArmorMultiplier = 1.0f;
    public static float shycoKDResistanceMultiplier = 1.0f;
    public static double shycoDamageIncreased = 0.5;
    public static float shycoadaptedhealth = 50.0f;
    public static float shycoadapteddamage = 12.0f;
    public static float shycoadaptedarmor = 7.0f;
    public static float shycoadaptedkdresistance = 0.3f;
    public static double shycoadapteddamageincrease = 1.0;
    public static String[] shycoadaptedloot = new String[]{"subspaceparasite:ada_longarms_drop;60;2;true", "subspaceparasite:lurecomponent4;80;1;false", "subspaceparasite:bone;20;2;true"};
    public static String[] shycoadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "0;15;2;minecraft:slowness;0;0"};
    public static boolean infendermanWaterDamageEnabled = true;
    public static boolean pearlDestroyedOnBeholderKill = true;
    public static int shycoSpawnRate = 15;
    public static int shycoASpawnRate = 15;
    public static boolean shycoEnabled = true;
    public static String[] shycoLoot = new String[]{"subspaceparasite:lurecomponent3;60;2;false", "subspaceparasite:ada_longarms_drop;40;2;true", "subspaceparasite:bone;10;1;true"};
    public static String[] shycoOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "0;15;1;minecraft:slowness;0;0"};
    private static final String GIM_CATEGORY = "subspaceparasite:viscera";
    public static float gimHealthMultiplier = 1.0f;
    public static float gimDamageMultiplier = 1.0f;
    public static float gimArmorMultiplier = 1.0f;
    public static float gimKDResistanceMultiplier = 1.0f;
    public static float gimadaptedhealth = 50.0f;
    public static float gimadapteddamage = 12.0f;
    public static float gimadaptedarmor = 7.0f;
    public static float gimadaptedkdresistance = 0.3f;
    public static String[] gimadaptedloot = new String[]{"subspaceparasite:ada_viscera_drop;60;2;true", "subspaceparasite:lurecomponent4;80;1;false", "subspaceparasite:bone;20;2;true"};
    public static String[] gimadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "0;15;2;minecraft:slowness;0;0"};
    public static int gimSpawnRate = 15;
    public static int gimASpawnRate = 15;
    public static boolean gimEnabled = true;
    public static String[] gimLoot = new String[]{"subspaceparasite:lurecomponent3;60;2;false", "subspaceparasite:ada_viscera_drop;40;2;true", "subspaceparasite:bone;10;1;true"};
    public static String[] gimOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "0;15;1;minecraft:slowness;0;0"};
    private static final String ZAA_CATEGORY = "subspaceparasite:burrower";
    public static float zaaHealthMultiplier = 1.0f;
    public static float zaaDamageMultiplier = 1.0f;
    public static float zaaArmorMultiplier = 1.0f;
    public static float zaaKDResistanceMultiplier = 1.0f;
    public static float zaaadaptedhealth = 50.0f;
    public static float zaaadapteddamage = 12.0f;
    public static float zaaadaptedarmor = 7.0f;
    public static float zaaadaptedkdresistance = 0.3f;
    public static String[] zaaadaptedloot = new String[]{"subspaceparasite:ada_burrower_drop;60;2;true", "subspaceparasite:lurecomponent4;80;1;false", "subspaceparasite:bone;20;2;true"};
    public static String[] zaaadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "0;15;2;minecraft:slowness;0;0"};
    public static int zaaSpawnRate = 15;
    public static int zaaASpawnRate = 15;
    public static boolean zaaEnabled = true;
    public static String[] zaaLoot = new String[]{"subspaceparasite:lurecomponent3;60;2;false", "subspaceparasite:ada_burrower_drop;40;2;true", "subspaceparasite:bone;10;1;true"};
    public static String[] zaaOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "0;15;1;minecraft:slowness;0;0"};
    private static final String DORPA_CATEGORY = "subspaceparasite:sim_bigspider";
    public static float dorpaHealthMultiplier = 1.0f;
    public static float dorpaDamageMultiplier = 1.0f;
    public static float dorpaArmorMultiplier = 1.0f;
    public static float dorpaKDResistanceMultiplier = 1.0f;
    public static float dorpaRangedAttackDamageMultiplier = 1.0f;
    public static int dorpaSpawnRate = 10;
    public static boolean dorpaEnabled = true;
    public static boolean dorpaWeb = true;
    public static String[] dorpaLoot = new String[0];
    public static String dorpamob = "subspaceparasite:buglin;5;5";
    private static final String RATHOL_CATEGORY = "subspaceparasite:carrier_heavy";
    public static float ratholHealthMultiplier = 1.0f;
    public static float ratholDamageMultiplier = 1.0f;
    public static float ratholArmorMultiplier = 1.0f;
    public static float ratholKDResistanceMultiplier = 1.0f;
    public static int ratholSpawnRate = 20;
    public static boolean ratholEnabled = true;
    public static boolean ratholGriefing = false;
    public static String[] ratholLoot = new String[0];
    public static String[] ratholMobs = new String[]{"subspaceparasite:rupter;5;4", "subspaceparasite:gnat;7;5", "subspaceparasite:pri_yelloweye;3;3"};
    private static final String GOTHOL_CATEGORY = "subspaceparasite:carrier_light";
    public static float gotholHealthMultiplier = 1.0f;
    public static float gotholDamageMultiplier = 1.0f;
    public static float gotholArmorMultiplier = 1.0f;
    public static float gotholKDResistanceMultiplier = 1.0f;
    public static int gotholSpawnRate = 20;
    public static boolean gotholEnabled = true;
    public static boolean gotholGriefing = false;
    public static String[] gotholLoot = new String[0];
    public static String[] gotholMobs = new String[]{"subspaceparasite:rupter;3;2", "subspaceparasite:gnat;5;4"};
    private static final String EMANA_CATEGORY = "subspaceparasite:yelloweye";
    public static float emanaHealthMultiplier = 1.0f;
    public static float emanaDamageMultiplier = 1.0f;
    public static float emanaArmorMultiplier = 1.0f;
    public static float emanaKDResistanceMultiplier = 1.0f;
    public static int emanaSpawnRate = 10;
    public static int emanaASpawnRate = 10;
    public static boolean emanaEnabled = true;
    public static int emanaPoisonDuration = 3;
    public static int emanaPoisonAmplifier = 1;
    public static String[] emanaLoot = new String[]{"subspaceparasite:lurecomponent3;60;2;false", "subspaceparasite:ada_yelloweye_drop;40;1;true", "subspaceparasite:bone;4;1;true"};
    public static int emanaMaxY = 256;
    public static double emanaGearD = 0.04;
    public static float emanaadaptedhealth = 25.0f;
    public static float emanaadapteddamage = 5.0f;
    public static float emanaadaptedarmor = 10.0f;
    public static float emanaadaptedkdresistance = 0.4f;
    public static double emanaadaptedgeard = 0.07;
    public static double emanaadaptedmelee = 17.0;
    public static String[] emanaadaptedloot = new String[]{"subspaceparasite:ada_yelloweye_drop;80;1;true", "subspaceparasite:lurecomponent4;80;1;false", "subspaceparasite:bone;20;1;true"};
    private static final String LODO_CATEGORY = "subspaceparasite:buglin";
    public static float lodoHealthMultiplier = 1.0f;
    public static float lodoDamageMultiplier = 1.0f;
    public static float lodoArmorMultiplier = 1.0f;
    public static float lodoKDResistanceMultiplier = 1.0f;
    public static int lodoSpawnRate = 30;
    public static boolean lodoEnabled = true;
    public static String[] LodoLoot = new String[]{"srpmeshi:raw_buglin;50;1;true"};
    private static final String HULL_CATEGORY = "subspaceparasite:manducater";
    public static float hullHealthMultiplier = 1.0f;
    public static float hullDamageMultiplier = 1.0f;
    public static float hullArmorMultiplier = 1.0f;
    public static float hullKDResistanceMultiplier = 1.0f;
    public static double hullNeededHealth = 0.7;
    public static float hullNeededTime = 15.0f;
    public static float hullStealthDamageMultiplier = 2.0f;
    public static float hulladaptedhealth = 15.0f;
    public static float hulladapteddamage = 12.0f;
    public static float hulladaptedarmor = 6.0f;
    public static float hulladaptedkdresistance = 0.5f;
    public static double hulladaptedneededhealth = 0.4;
    public static float hulladaptedneededtime = 7.0f;
    public static float hulladaptedstealthdamage = 2.0f;
    public static String[] hulladaptedloot = new String[]{"subspaceparasite:ada_manducater_drop;60;1;false", "subspaceparasite:lurecomponent4;80;1;false", "subspaceparasite:bone;20;2;true"};
    public static String[] hulladaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "1;20;2;minecraft:invisibility;2;2"};
    public static int hullSpawnRate = 30;
    public static int hullASpawnRate = 30;
    public static boolean hullEnabled = true;
    public static String[] hullLoot = new String[]{"subspaceparasite:lurecomponent3;40;2;false", "subspaceparasite:ada_manducater_drop;40;1;false", "subspaceparasite:bone;10;1;true"};
    public static String[] hullOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "1;20;1;minecraft:invisibility;2;2"};
    private static final String CANRA_CATEGORY = "subspaceparasite:summoner";
    public static float canraHealthMultiplier = 1.0f;
    public static float canraDamageMultiplier = 1.0f;
    public static float canraArmorMultiplier = 1.0f;
    public static float canraKDResistanceMultiplier = 1.0f;
    public static int canraSummoningCooldown = 10;
    public static int canraTotalActiveMobs = 4;
    public static int canraLimit = 2;
    public static int canraRemainPlus = 1;
    public static float canraRemainHealth = 1.0f;
    public static String[] canraMobList = new String[]{"subspaceparasite:rupter;1;1"};
    public static float canraadaptedhealth = 60.0f;
    public static float canraadapteddamage = 15.0f;
    public static float canraadaptedarmor = 10.0f;
    public static float canraadaptedkdresistance = 0.5f;
    public static int canraadaptedsummoningcooldown = 8;
    public static String[] canraadaptedloot = new String[]{"subspaceparasite:ada_summoner_drop;80;1;false", "subspaceparasite:lurecomponent4;60;1;false", "subspaceparasite:bone;20;2;true"};
    public static String[] canraadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "2;30;2;minecraft:speed;0;0"};
    public static int canraadaptedtotalactivemobs = 6;
    public static int canraadaptedlimit = 1;
    public static int canraadaptedremainplus = 3;
    public static float canraadaptedremainhealth = 1.5f;
    public static String[] canraadaptedmoblist = new String[]{"subspaceparasite:rupter;0.1;1", "subspaceparasite:sim_human;0.3;2", "subspaceparasite:sim_cow;0.3;2", "subspaceparasite:sim_wolf;0.3;2"};
    public static int canraSpawnRate = 5;
    public static int canraASpawnRate = 5;
    public static boolean canraEnabled = true;
    public static String[] canraLoot = new String[]{"subspaceparasite:lurecomponent3;60;2;false", "subspaceparasite:ada_summoner_drop;40;1;false", "subspaceparasite:bone;10;1;true"};
    public static String[] canraOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "2;30;1;minecraft:speed;0;0"};
    private static final String ALAFHA_CATEGORY = "subspaceparasite:overseer";
    public static float alafhaHealthMultiplier = 1.0f;
    public static float alafhaDamageMultiplier = 1.0f;
    public static float alafhaArmorMultiplier = 1.0f;
    public static float alafhaKDResistanceMultiplier = 1.0f;
    public static int alafhaSpawnRate = 5;
    public static boolean alafhaEnabled = true;
    public static boolean alafhaGriefing = false;
    public static String[] alafhaLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    public static int alafhaSummoningCooldown = 10;
    public static int alafhaTotalActiveMobs = 6;
    public static int alafhaLimit = 6;
    public static String[] alafhaMobList = new String[]{"subspaceparasite:rupter;1;1", "subspaceparasite:grunt;0.5;1"};
    public static int alafhaMaxY = 256;
    public static double alafhaMelee = 45.0;
    private static final String NOGLA_CATEGORY = "subspaceparasite:reeker";
    public static float noglaHealthMultiplier = 1.0f;
    public static float noglaDamageMultiplier = 1.0f;
    public static float noglaArmorMultiplier = 1.0f;
    public static float noglaKDResistanceMultiplier = 1.0f;
    public static boolean noglaRicardoVariantEnabled = false;
    public static float noglaadaptedhealth = 50.0f;
    public static float noglaadapteddamage = 20.0f;
    public static float noglaadaptedarmor = 15.0f;
    public static float noglaadaptedkdresistance = 0.4f;
    public static String[] noglaadaptedloot = new String[]{"subspaceparasite:ada_reeker_drop;60;3;false", "subspaceparasite:lurecomponent4;80;1;false", "subspaceparasite:bone;20;2;true"};
    public static String[] noglaadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "0;15;2;minecraft:nausea;0;0"};
    public static int noglaSpawnRate = 10;
    public static int noglaASpawnRate = 10;
    public static boolean noglaEnabled = true;
    public static String[] noglaLoot = new String[]{"subspaceparasite:lurecomponent3;40;2;false", "subspaceparasite:ada_reeker_drop;40;1;false", "subspaceparasite:bone;10;1;true"};
    public static String[] noglaOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "0;15;1;minecraft:nausea;0;0"};
    private static final String BUTHOL_CATEGORY = "subspaceparasite:carrier_flying";
    public static float butholHealthMultiplier = 1.0f;
    public static float butholDamageMultiplier = 1.0f;
    public static float butholArmorMultiplier = 1.0f;
    public static float butholKDResistanceMultiplier = 1.0f;
    public static int butholSpawnRate = 15;
    public static boolean butholEnabled = true;
    public static boolean ButholGriefing = false;
    public static String[] butholLoot = new String[0];
    public static int butholMaxY = 256;
    public static String[] butholMobs = new String[]{"subspaceparasite:rupter;4;3", "subspaceparasite:buglin;3;2"};
    private static final String MUDO_CATEGORY = "subspaceparasite:rupter";
    public static float mudoHealthMultiplier = 1.0f;
    public static float mudoDamageMultiplier = 1.0f;
    public static float mudoArmorMultiplier = 1.0f;
    public static float mudoKDResistanceMultiplier = 1.0f;
    public static int mudoSpawnRate = 30;
    public static boolean mudoEnabled = true;
    public static float mudoMinDamage = 0.1f;
    public static String[] mudoLoot = new String[]{"subspaceparasite:lurecomponent1;70;2;true"};
    public static boolean mudoAnimalAttacking = true;
    public static int mudoTunnelValue = 5;
    public static byte mudoTunnelPhase = (byte)3;
    public static int mudoMangler = 30;
    private static final String NUUH_CATEGORY = "subspaceparasite:mangler";
    public static float nuuhHealthMultiplier = 1.0f;
    public static float nuuhDamageMultiplier = 1.0f;
    public static float nuuhArmorMultiplier = 1.0f;
    public static float nuuhKDResistanceMultiplier = 1.0f;
    public static int nuuhSpawnRate = 30;
    public static boolean nuuhEnabled = true;
    public static float nuuhMinDamage = 0.3f;
    public static String[] nuuhLoot = new String[]{"subspaceparasite:lurecomponent1;60;2;true"};
    public static boolean nuuhAnimalAttacking = true;
    private static final String ATA_CATEGORY = "subspaceparasite:gnat";
    public static float ataHealthMultiplier = 1.0f;
    public static float ataDamageMultiplier = 1.0f;
    public static float ataArmorMultiplier = 1.0f;
    public static float ataKDResistanceMultiplier = 1.0f;
    public static int ataSpawnRate = 30;
    public static boolean ataEnabled = true;
    public static float ataMinDamage = 0.3f;
    public static String[] ataLoot = new String[]{"subspaceparasite:lurecomponent1;30;2;true", "subspaceparasite:hijacked_drop;30;2;true"};
    public static boolean ataAnimalAttacking = true;
    private static final String VIIN_CATEGORY = "subspaceparasite:lice";
    public static float viinHealthMultiplier = 1.0f;
    public static float viinDamageMultiplier = 1.0f;
    public static float viinArmorMultiplier = 1.0f;
    public static float viinKDResistanceMultiplier = 1.0f;
    public static int viinSpawnRate = 30;
    public static boolean viinEnabled = true;
    public static float viinMinDamage = 0.3f;
    public static String[] viinLoot = new String[]{"subspaceparasite:lurecomponent1;30;2;true", "subspaceparasite:hijacked_drop;30;2;true"};
    public static boolean viinAnimalAttacking = true;
    private static final String HOST_CATEGORY = "subspaceparasite:host";
    public static float hostHealthMultiplier = 1.0f;
    public static float hostDamageMultiplier = 1.0f;
    public static float hostArmorMultiplier = 1.0f;
    public static float hostKDResistanceMultiplier = 1.0f;
    public static float hostDamage = 7.0f;
    public static int hostSpawnRate = 0;
    public static boolean hostEnabled = true;
    public static int hostSkele = 5;
    public static int hostHerd = 40;
    public static String[] hostLoot = new String[]{"subspaceparasite:assimilated_flesh;80;7;false", "subspaceparasite:lurecomponent2;10;4;true"};
    private static final String HERD_CATEGORY = "subspaceparasite:herd";
    public static float herdHealthMultiplier = 1.0f;
    public static float herdDamageMultiplier = 1.0f;
    public static float herdArmorMultiplier = 1.0f;
    public static float herdKDResistanceMultiplier = 1.0f;
    public static float herdDamage = 7.0f;
    public static int herdSpawnRate = 0;
    public static boolean herdEnabled = true;
    public static String[] herdLoot = new String[0];
    private static final String THRALL_CATEGORY = "subspaceparasite:thrall";
    public static float thrallHealthMultiplier = 1.0f;
    public static float thrallDamageMultiplier = 1.0f;
    public static float thrallArmorMultiplier = 1.0f;
    public static float thrallKDResistanceMultiplier = 1.0f;
    public static int thrallSpawnRate = 30;
    public static boolean thrallEnabled = true;
    public static String[] thrallLoot = new String[]{"subspaceparasite:assimilated_flesh;80;1;false", "subspaceparasite:lurecomponent3;10;1;true", "subspaceparasite:bone;10;1;true"};
    private static final String INFBEAR_CATEGORY = "subspaceparasite:sim_bear";
    public static float infbearHealthMultiplier = 1.0f;
    public static float infbearDamageMultiplier = 1.0f;
    public static float infbearArmorMultiplier = 1.0f;
    public static float infbearKDResistanceMultiplier = 1.0f;
    public static int infbearSpawnRate = 30;
    public static int infbearCanSpawnAssimilatedNat = 2;
    public static boolean infbearEnabled = true;
    public static String[] infbearLoot = new String[]{"subspaceparasite:assimilated_flesh;80;1;false", "subspaceparasite:lurecomponent2;10;1;true", "subspaceparasite:fishlin;20;3;true"};
    private static final String INFENDERMAN_CATEGORY = "subspaceparasite:sim_enderman";
    public static float infendermanHealthMultiplier = 1.0f;
    public static float infendermanDamageMultiplier = 1.0f;
    public static float infendermanArmorMultiplier = 1.0f;
    public static float infendermanTeleDist = 10.0f;
    public static float infendermanKDResistanceMultiplier = 1.0f;
    public static int infendermanSpawnRate = 5;
    public static int infendermanCanSpawnAssimilatedNat = 9;
    public static boolean infendermanEnabled = true;
    public static int infendermantelefreq = 2;
    public static boolean infendermanteleally = true;
    public static String[] infendermanLoot = new String[]{"subspaceparasite:pearl;10;1;false"};
    public static String[] infendermanheadLoot = new String[0];
    public static float infendermanHealthHead = 0.3f;
    public static float infendermanDamageHead = 0.3f;
    public static float infendermanheadchance = 0.5f;
    public static int infendermanallyCool = 40;
    public static int infendermansaw = 80;
    public static float infendermanTeleDamage = 2.0f;
    private static final String INFHUMAN_CATEGORY = "subspaceparasite:sim_human";
    public static float infhumanHealthMultiplier = 1.0f;
    public static float infhumanDamageMultiplier = 1.0f;
    public static float infhumanArmorMultiplier = 1.0f;
    public static float infhumanKDResistanceMultiplier = 1.0f;
    public static int infhumanSpawnRate = 30;
    public static int infhumanCanSpawnAssimilatedNat = 5;
    public static boolean infhumanEnabled = true;
    public static String[] infhumanLoot = new String[]{"subspaceparasite:assimilated_flesh;80;1;false", "subspaceparasite:lurecomponent2;10;1;true"};
    public static String[] infhumanheadLoot = new String[0];
    public static float infhumanHealthHead = 0.3f;
    public static float infhumanDamageHead = 0.3f;
    public static float infhumanheadchance = 0.5f;
    private static final String INFSQUID_CATEGORY = "subspaceparasite:sim_squid";
    public static float infsquidHealthMultiplier = 1.0f;
    public static float infsquidDamageMultiplier = 1.0f;
    public static float infsquidArmorMultiplier = 1.0f;
    public static float infsquidKDResistanceMultiplier = 1.0f;
    public static int infsquidSpawnRate = 25;
    public static int infsquidCanSpawnAssimilatedNat = -1;
    public static boolean infsquidEnabled = true;
    public static String[] infsquidLoot = new String[]{"subspaceparasite:assimilated_flesh;80;2;false", "subspaceparasite:lurecomponent2;10;1;true"};
    private static final String INFCOW_CATEGORY = "subspaceparasite:sim_cow";
    public static float infcowHealthMultiplier = 1.0f;
    public static float infcowDamageMultiplier = 1.0f;
    public static float infcowArmorMultiplier = 1.0f;
    public static float infcowKDResistanceMultiplier = 1.0f;
    public static int infcowSpawnRate = 25;
    public static int infcowCanSpawnAssimilatedNat = 4;
    public static boolean infcowEnabled = true;
    public static String[] infcowLoot = new String[]{"subspaceparasite:assimilated_flesh;80;2;false", "subspaceparasite:lurecomponent2;10;1;true", "srpmeshi:vilebeefr;30;1;true"};
    public static String[] infcowheadLoot = new String[0];
    public static float infcowHealthHead = 0.3f;
    public static float infcowDamageHead = 0.3f;
    public static float infcowheadchance = 0.5f;
    public static String infcowmob = "subspaceparasite:buglin;4;3";
    private static final String INFSHEEP_CATEGORY = "subspaceparasite:sim_sheep";
    public static float infsheepHealthMultiplier = 1.0f;
    public static float infsheepDamageMultiplier = 1.0f;
    public static float infsheepArmorMultiplier = 1.0f;
    public static float infsheepKDResistanceMultiplier = 1.0f;
    public static int infsheepSpawnRate = 25;
    public static int infsheepCanSpawnAssimilatedNat = 3;
    public static boolean infsheepEnabled = true;
    public static String[] infsheepLoot = new String[]{"subspaceparasite:assimilated_flesh;80;1;false", "subspaceparasite:lurecomponent2;10;1;true"};
    public static String[] infsheepheadLoot = new String[0];
    public static float infsheepHealthHead = 0.3f;
    public static float infsheepDamageHead = 0.3f;
    public static float infsheepheadchance = 0.5f;
    public static String infsheepmob = "subspaceparasite:buglin;3;3";
    private static final String INFWOLF_CATEGORY = "subspaceparasite:sim_wolf";
    public static float infwolfHealthMultiplier = 1.0f;
    public static float infwolfDamageMultiplier = 1.0f;
    public static float infwolfArmorMultiplier = 1.0f;
    public static float infwolfKDResistanceMultiplier = 1.0f;
    public static int infwolfSpawnRate = 25;
    public static int infwolfCanSpawnAssimilatedNat = 2;
    public static boolean infwolfEnabled = true;
    public static String[] infwolfLoot = new String[]{"subspaceparasite:assimilated_flesh;20;1;false", "subspaceparasite:lurecomponent2;10;1;true"};
    public static String[] infwolfheadLoot = new String[0];
    public static float infwolfHealthHead = 0.3f;
    public static float infwolfDamageHead = 0.3f;
    public static float infwolfheadchance = 0.5f;
    public static String infwolfmob = "subspaceparasite:buglin;2;2";
    private static final String INFPIG_CATEGORY = "subspaceparasite:sim_pig";
    public static float infpigHealthMultiplier = 1.0f;
    public static float infpigDamageMultiplier = 1.0f;
    public static float infpigArmorMultiplier = 1.0f;
    public static float infpigKDResistanceMultiplier = 1.0f;
    public static int infpigSpawnRate = 25;
    public static int infpigCanSpawnAssimilatedNat = 4;
    public static boolean infpigEnabled = true;
    public static String[] infpigLoot = new String[]{"subspaceparasite:assimilated_flesh;80;2;false", "subspaceparasite:lurecomponent2;10;1;true"};
    public static String[] infpigheadLoot = new String[0];
    public static float infpigHealthHead = 0.3f;
    public static float infpigDamageHead = 0.3f;
    public static float infpigheadchance = 0.5f;
    public static String infpigmob = "subspaceparasite:buglin;2;2";
    private static final String INFVILLAGER_CATEGORY = "subspaceparasite:sim_villager";
    public static float infvillagerHealthMultiplier = 1.0f;
    public static float infvillagerDamageMultiplier = 1.0f;
    public static float infvillagerArmorMultiplier = 1.0f;
    public static float infvillagerKDResistanceMultiplier = 1.0f;
    public static int infvillagerSpawnRate = 25;
    public static int infvillagerCanSpawnAssimilatedNat = 6;
    public static boolean infvillagerEnabled = true;
    public static String[] infvillagerLoot = new String[]{"subspaceparasite:assimilated_flesh;80;1;false", "subspaceparasite:lurecomponent2;10;1;true", "subspaceparasite:false_apple;5;1;true"};
    public static String[] infvillagerheadLoot = new String[0];
    public static float infvillagerHealthHead = 0.3f;
    public static float infvillagerDamageHead = 0.3f;
    public static float infvillagerheadchance = 0.5f;
    public static String infvillagermob = "subspaceparasite:buglin;2;2";
    private static final String INFHORSE_CATEGORY = "subspaceparasite:sim_horse";
    public static float infhorseHealthMultiplier = 1.0f;
    public static float infhorseDamageMultiplier = 1.0f;
    public static float infhorseArmorMultiplier = 1.0f;
    public static float infhorseKDResistanceMultiplier = 1.0f;
    public static float infhorseExplotionMult = 2.0f;
    public static int infhorseSpawnRate = 25;
    public static int infhorseCanSpawnAssimilatedNat = 3;
    public static boolean infhorseEnabled = true;
    public static String[] infhorseLoot = new String[]{"subspaceparasite:assimilated_flesh;80;2;false", "subspaceparasite:lurecomponent2;10;1;true"};
    public static String[] infhorseheadLoot = new String[0];
    public static float infhorseHealthHead = 0.3f;
    public static float infhorseDamageHead = 0.3f;
    public static float infhorseheadchance = 0.5f;
    public static String infhorsemob = "subspaceparasite:buglin;2;2";
    private static final String INFADVENTURER_CATEGORY = "subspaceparasite:sim_adventurer";
    public static float infadventurerHealthMultiplier = 1.0f;
    public static float infadventurerDamageMultiplier = 1.0f;
    public static float infadventurerArmorMultiplier = 1.0f;
    public static float infadventurerKDResistanceMultiplier = 1.0f;
    public static int infadventurerSpawnRate = 15;
    public static int infadventurerThrall = 15;
    public static boolean infadventurerEnabled = true;
    public static boolean infadventurerSpawnBy = true;
    public static String infadventurermob = "subspaceparasite:buglin;4;3";
    public static String[] infadventurerLoot = new String[]{"subspaceparasite:assimilated_flesh;40;1;false", "subspaceparasite:lurecomponent2;5;1;true"};
    public static String[] infadventurerheadLoot = new String[0];
    public static float infadventurerHealthHead = 0.3f;
    public static float infadventurerDamageHead = 0.3f;
    public static float infadventurerheadchance = 0.5f;
    private static final String INFDRAGONE_CATEGORY = "subspaceparasite:sim_dragone";
    public static float infdragoneHealthMultiplier = 1.0f;
    public static float infdragoneDamageMultiplier = 1.0f;
    public static float infdragoneArmorMultiplier = 1.0f;
    public static float infdragoneKDResistanceMultiplier = 1.0f;
    public static float infdragoneRangeDamageMultiplier = 1.0f;
    public static int infdragoneSpawnRate = 2;
    public static int infdragoneCanSpawnAssimilatedNat = -1;
    public static boolean infdragoneEnabled = true;
    public static String[] infdragoneLoot = new String[0];
    public static String[] infdragoneheadLoot = new String[0];
    public static float infdragoneHealthHead = 0.3f;
    public static float infdragoneDamageHead = 0.3f;
    private static final String FERBEAR_CATEGORY = "subspaceparasite:fer_bear";
    public static float ferbearHealthMultiplier = 1.0f;
    public static float ferbearDamageMultiplier = 1.0f;
    public static float ferbearArmorMultiplier = 1.0f;
    public static float ferbearKDResistanceMultiplier = 1.0f;
    public static int ferbearSpawnRate = 20;
    public static boolean ferbearEnabled = true;
    public static String[] ferbearLoot = new String[]{"subspaceparasite:fishlin;20;3;true"};
    private static final String FERCOW_CATEGORY = "subspaceparasite:fer_cow";
    public static float fercowHealthMultiplier = 1.0f;
    public static float fercowDamageMultiplier = 1.0f;
    public static float fercowArmorMultiplier = 1.0f;
    public static float fercowKDResistanceMultiplier = 1.0f;
    public static int fercowSpawnRate = 20;
    public static boolean fercowEnabled = true;
    public static String[] fercowLoot = new String[]{"srpmeshi:vilebeefr;40;1;true"};
    private static final String FERENDERMAN_CATEGORY = "subspaceparasite:fer_enderman";
    public static float ferendermanHealthMultiplier = 1.0f;
    public static float ferendermanDamageMultiplier = 1.0f;
    public static float ferendermanArmorMultiplier = 1.0f;
    public static float ferendermanTeleDist = 7.0f;
    public static float ferendermanKDResistanceMultiplier = 1.0f;
    public static int ferendermanSpawnRate = 20;
    public static boolean ferendermanEnabled = true;
    public static String[] ferendermanLoot = new String[0];
    public static int feralendermanallyCool = 10;
    public static int feralendermansaw = 30;
    public static float feralendermanTeleDamage = 0.5f;
    public static int feralendermantelefreq = 2;
    public static boolean feralendermanteleally = true;
    private static final String FERHORSE_CATEGORY = "subspaceparasite:fer_horse";
    public static float ferhorseHealthMultiplier = 1.0f;
    public static float ferhorseDamageMultiplier = 1.0f;
    public static float ferhorseArmorMultiplier = 1.0f;
    public static float ferhorseKDResistanceMultiplier = 1.0f;
    public static int ferhorseSpawnRate = 20;
    public static boolean ferhorseEnabled = true;
    public static String[] ferhorseLoot = new String[0];
    private static final String FERHUMAN_CATEGORY = "subspaceparasite:fer_human";
    public static float ferhumanHealthMultiplier = 1.0f;
    public static float ferhumanDamageMultiplier = 1.0f;
    public static float ferhumanArmorMultiplier = 1.0f;
    public static float ferhumanKDResistanceMultiplier = 1.0f;
    public static int ferhumanSpawnRate = 20;
    public static boolean ferhumanEnabled = true;
    public static String[] ferhumanLoot = new String[0];
    private static final String FERPIG_CATEGORY = "subspaceparasite:fer_pig";
    public static float ferpigHealthMultiplier = 1.0f;
    public static float ferpigDamageMultiplier = 1.0f;
    public static float ferpigArmorMultiplier = 1.0f;
    public static float ferpigKDResistanceMultiplier = 1.0f;
    public static int ferpigSpawnRate = 20;
    public static boolean ferpigEnabled = true;
    public static String[] ferpigLoot = new String[0];
    private static final String FERSHEEP_CATEGORY = "subspaceparasite:fer_sheep";
    public static float fersheepHealthMultiplier = 1.0f;
    public static float fersheepDamageMultiplier = 1.0f;
    public static float fersheepArmorMultiplier = 1.0f;
    public static float fersheepKDResistanceMultiplier = 1.0f;
    public static int fersheepSpawnRate = 20;
    public static boolean fersheepEnabled = true;
    public static String[] fersheepLoot = new String[0];
    private static final String FERVILLAGER_CATEGORY = "subspaceparasite:fer_villager";
    public static float fervillagerHealthMultiplier = 1.0f;
    public static float fervillagerDamageMultiplier = 1.0f;
    public static float fervillagerArmorMultiplier = 1.0f;
    public static float fervillagerKDResistanceMultiplier = 1.0f;
    public static int fervillagerSpawnRate = 20;
    public static boolean fervillagerEnabled = true;
    public static String[] fervillagerLoot = new String[0];
    private static final String FERWOLF_CATEGORY = "subspaceparasite:fer_wolf";
    public static float ferwolfHealthMultiplier = 1.0f;
    public static float ferwolfDamageMultiplier = 1.0f;
    public static float ferwolfArmorMultiplier = 1.0f;
    public static float ferwolfKDResistanceMultiplier = 1.0f;
    public static int ferwolfSpawnRate = 20;
    public static boolean ferwolfEnabled = true;
    public static String[] ferwolfLoot = new String[0];
    private static final String MARBEAR_CATEGORY = "subspaceparasite:mar_bear";
    public static float marbearHealthMultiplier = 1.0f;
    public static float marbearDamageMultiplier = 1.0f;
    public static float marbearArmorMultiplier = 1.0f;
    public static float marbearKDResistanceMultiplier = 1.0f;
    public static int marbearSpawnRate = 20;
    public static boolean marbearEnabled = true;
    public static String[] marbearLoot = new String[]{"subspaceparasite:fishlin;20;3;true"};
    private static final String MARCOW_CATEGORY = "subspaceparasite:mar_cow";
    public static float marcowHealthMultiplier = 1.0f;
    public static float marcowDamageMultiplier = 1.0f;
    public static float marcowArmorMultiplier = 1.0f;
    public static float marcowKDResistanceMultiplier = 1.0f;
    public static int marcowSpawnRate = 20;
    public static boolean marcowEnabled = true;
    public static String[] marcowLoot = new String[0];
    private static final String MARENDERMAN_CATEGORY = "subspaceparasite:mar_enderman";
    public static float marendermanHealthMultiplier = 1.0f;
    public static float marendermanDamageMultiplier = 1.0f;
    public static float marendermanArmorMultiplier = 1.0f;
    public static float marendermanKDResistanceMultiplier = 1.0f;
    public static int marendermanSpawnRate = 20;
    public static boolean marendermanEnabled = true;
    public static String[] marendermanLoot = new String[0];
    public static int maralendermanallyCool = 10;
    public static int maralendermansaw = 30;
    public static float maralendermanTeleDamage = 0.5f;
    public static int maralendermantelefreq = 2;
    public static boolean maralendermanteleally = true;
    private static final String MARHORSE_CATEGORY = "subspaceparasite:mar_horse";
    public static float marhorseHealthMultiplier = 1.0f;
    public static float marhorseDamageMultiplier = 1.0f;
    public static float marhorseArmorMultiplier = 1.0f;
    public static float marhorseKDResistanceMultiplier = 1.0f;
    public static int marhorseSpawnRate = 20;
    public static boolean marhorseEnabled = true;
    public static String[] marhorseLoot = new String[0];
    private static final String MARHUMAN_CATEGORY = "subspaceparasite:mar_human";
    public static float marhumanHealthMultiplier = 1.0f;
    public static float marhumanDamageMultiplier = 1.0f;
    public static float marhumanArmorMultiplier = 1.0f;
    public static float marhumanKDResistanceMultiplier = 1.0f;
    public static int marhumanSpawnRate = 20;
    public static boolean marhumanEnabled = true;
    public static String[] marhumanLoot = new String[0];
    private static final String MARPIG_CATEGORY = "subspaceparasite:mar_pig";
    public static float marpigHealthMultiplier = 1.0f;
    public static float marpigDamageMultiplier = 1.0f;
    public static float marpigArmorMultiplier = 1.0f;
    public static float marpigKDResistanceMultiplier = 1.0f;
    public static int marpigSpawnRate = 20;
    public static boolean marpigEnabled = true;
    public static String[] marpigLoot = new String[0];
    private static final String MARSHEEP_CATEGORY = "subspaceparasite:mar_sheep";
    public static float marsheepHealthMultiplier = 1.0f;
    public static float marsheepDamageMultiplier = 1.0f;
    public static float marsheepArmorMultiplier = 1.0f;
    public static float marsheepKDResistanceMultiplier = 1.0f;
    public static int marsheepSpawnRate = 20;
    public static boolean marsheepEnabled = true;
    public static String[] marsheepLoot = new String[0];
    private static final String MARVILLAGER_CATEGORY = "subspaceparasite:mar_villager";
    public static float marvillagerHealthMultiplier = 1.0f;
    public static float marvillagerDamageMultiplier = 1.0f;
    public static float marvillagerArmorMultiplier = 1.0f;
    public static float marvillagerKDResistanceMultiplier = 1.0f;
    public static int marvillagerSpawnRate = 20;
    public static boolean marvillagerEnabled = true;
    public static String[] marvillagerLoot = new String[]{"subspaceparasite:false_apple;10;1;true"};
    private static final String MARWOLF_CATEGORY = "subspaceparasite:mar_wolf";
    public static float marwolfHealthMultiplier = 1.0f;
    public static float marwolfDamageMultiplier = 1.0f;
    public static float marwolfArmorMultiplier = 1.0f;
    public static float marwolfKDResistanceMultiplier = 1.0f;
    public static int marwolfSpawnRate = 20;
    public static boolean marwolfEnabled = true;
    public static String[] marwolfLoot = new String[0];
    private static final String HIBLAZE_CATEGORY = "subspaceparasite:hi_blaze";
    public static float hiblazeHealthMultiplier = 1.0f;
    public static float hiblazeDamageMultiplier = 1.0f;
    public static float hiblazeArmorMultiplier = 1.0f;
    public static float hiblazeKDResistanceMultiplier = 1.0f;
    public static int hiblazeSpawnRate = 20;
    public static boolean hiblazeEnabled = true;
    public static String[] hiblazeLoot = new String[]{"subspaceparasite:bloody_rod;20;3;true"};
    private static final String HIGOLEM_CATEGORY = "subspaceparasite:hi_golem";
    public static float higolemHealthMultiplier = 1.0f;
    public static float higolemDamageMultiplier = 1.0f;
    public static float higolemArmorMultiplier = 1.0f;
    public static float higolemKDResistanceMultiplier = 1.0f;
    public static int higolemSpawnRate = 20;
    public static int higolemCanSpawnAssimilatedNat = 6;
    public static boolean higolemEnabled = true;
    public static String[] higolemLoot = new String[]{"subspaceparasite:hijacked_drop;20;1;true", "subspaceparasite:bloody_iron_ingot;20;3;true"};
    private static final String HISKELETON_CATEGORY = "subspaceparasite:hi_skeleton";
    public static float hiskeletonHealthMultiplier = 1.0f;
    public static float hiskeletonDamageMultiplier = 1.0f;
    public static float hiskeletonArmorMultiplier = 1.0f;
    public static float hiskeletonKDResistanceMultiplier = 1.0f;
    public static int hiskeletonSpawnRate = 20;
    public static boolean hiskeletonEnabled = true;
    public static String[] hiskeletonLoot = new String[]{"subspaceparasite:bloody_bone;20;3;true"};
    private static final String ZETMO_CATEGORY = "subspaceparasite:bolster";
    public static float zetmoHealthMultiplier = 1.0f;
    public static float zetmoDamageMultiplier = 1.0f;
    public static float zetmoArmorMultiplier = 1.0f;
    public static float zetmoKDResistanceMultiplier = 1.0f;
    public static int zetmoCD = 30;
    public static int zetmoRange = 16;
    public static float zetmoadaptedhealth = 70.0f;
    public static float zetmoadapteddamage = 30.0f;
    public static float zetmoadaptedarmor = 15.0f;
    public static float zetmoadaptedkdresistance = 0.65f;
    public static int zetmoadaptedcd = 60;
    public static int zetmoadaptedrange = 24;
    public static String[] zetmoadaptedloot = new String[]{"subspaceparasite:lurecomponent4;60;3;false", "subspaceparasite:ada_bolster_drop;80;1;true", "subspaceparasite:bone;20;3;true"};
    public static String[] zetmoadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "2;30;2;minecraft:speed;0;0"};
    public static String[] zetmoadaptedeffects = new String[]{"30;3;minecraft:regeneration"};
    public static int zetmoSpawnRate = 10;
    public static int zetmoASpawnRate = 10;
    public static boolean zetmoEnabled = true;
    public static String[] zetmoLoot = new String[]{"subspaceparasite:lurecomponent3;40;2;false", "subspaceparasite:ada_bolster_drop;40;1;true", "subspaceparasite:bone;10;1;true"};
    public static String[] zetmoOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "2;30;1;minecraft:speed;0;0"};
    public static String[] zetmoEffects = new String[]{"30;1;minecraft:regeneration"};
    private static final String IKI_CATEGORY = "subspaceparasite:vermin";
    public static float ikiHealthMultiplier = 1.0f;
    public static float ikiDamageMultiplier = 1.0f;
    public static float ikiArmorMultiplier = 1.0f;
    public static float ikiKDResistanceMultiplier = 1.0f;
    public static float ikiadaptedhealth = 70.0f;
    public static float ikiadapteddamage = 30.0f;
    public static float ikiadaptedarmor = 15.0f;
    public static float ikiadaptedkdresistance = 0.65f;
    public static String[] ikiadaptedloot = new String[]{"subspaceparasite:lurecomponent4;60;3;false", "subspaceparasite:ada_vermin_drop;80;1;true", "subspaceparasite:bone;20;3;true"};
    public static String[] ikiadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "2;30;2;minecraft:speed;0;0"};
    public static int ikiSpawnRate = 10;
    public static int ikiASpawnRate = 10;
    public static boolean ikiEnabled = true;
    public static String[] ikiLoot = new String[]{"subspaceparasite:lurecomponent3;40;2;false", "subspaceparasite:ada_vermin_drop;40;1;true", "subspaceparasite:bone;10;1;true"};
    public static String[] ikiOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "2;30;1;minecraft:speed;0;0"};
    private static final String WYMO_CATEGORY = "subspaceparasite:tozoon";
    public static float wymoHealthMultiplier = 1.0f;
    public static float wymoDamageMultiplier = 1.0f;
    public static float wymoArmorMultiplier = 1.0f;
    public static float wymoKDResistanceMultiplier = 1.0f;
    public static float wymoadaptedhealth = 70.0f;
    public static float wymoadapteddamage = 30.0f;
    public static float wymoadaptedarmor = 15.0f;
    public static float wymoadaptedkdresistance = 0.65f;
    public static String[] wymoadaptedloot = new String[]{"subspaceparasite:lurecomponent4;60;3;false", "subspaceparasite:ada_tozoon_drop;80;1;true", "subspaceparasite:bone;20;3;true"};
    public static String[] wymoadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "2;30;2;minecraft:speed;0;0"};
    public static int wymoSpawnRate = 10;
    public static int wymoASpawnRate = 10;
    public static boolean wymoEnabled = true;
    public static String[] wymoLoot = new String[]{"subspaceparasite:lurecomponent3;40;2;false", "subspaceparasite:ada_tozoon_drop;40;1;true", "subspaceparasite:bone;10;1;true"};
    public static String[] wymoOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "2;30;1;minecraft:speed;0;0"};
    private static final String ANGED_CATEGORY = "subspaceparasite:vigilante";
    public static float angedHealthMultiplier = 1.0f;
    public static float angedDamageMultiplier = 1.0f;
    public static float angedRangeDamageMultiplier = 1.0f;
    public static float angedArmorMultiplier = 1.0f;
    public static float angedKDResistanceMultiplier = 1.0f;
    public static int angedSpawnRate = 0;
    public static boolean angedEnabled = true;
    public static String[] angedLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    public static String[] angedOrbEffects = new String[]{"0;15;3;minecraft:hunger;0;0", "0;70;3;subspaceparasite:needler;0;0", "0;15;3;minecraft:mining_fatigue;0;0", "2;30;3;minecraft:speed;0;0"};
    private static final String TONRO_CATEGORY = "subspaceparasite:kyphosis";
    public static float tonroHealthMultiplier = 1.0f;
    public static float tonroDamageMultiplier = 1.0f;
    public static float tonroSwingDamageMultiplier = 1.0f;
    public static float tonroArmorMultiplier = 1.0f;
    public static boolean tonroEnabled = true;
    public static String[] tonroLoot = new String[0];
    private static final String UNVO_CATEGORY = "subspaceparasite:sentry";
    public static float unvoHealthMultiplier = 1.0f;
    public static float unvoDamageMultiplier = 1.0f;
    public static float unvoRangeDamageMultiplier = 1.0f;
    public static float unvoArmorMultiplier = 1.0f;
    public static boolean unvoEnabled = true;
    public static String[] unvoLoot = new String[0];
    public static double unvoGearD = 0.04;
    public static int unvoPoisonDuration = 7;
    public static int unvoPoisonAmplifier = 1;
    private static final String GANRO_CATEGORY = "subspaceparasite:warden";
    public static float ganroHealthMultiplier = 1.0f;
    public static float ganroDamageMultiplier = 1.0f;
    public static float ganroArmorMultiplier = 1.0f;
    public static float ganroKDResistanceMultiplier = 1.0f;
    public static int ganroSpawnRate = 0;
    public static boolean ganroEnabled = true;
    public static String[] ganroLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    public static String[] ganroOrbEffects = new String[]{"0;15;3;minecraft:hunger;0;0", "0;70;3;subspaceparasite:needler;0;0", "0;15;3;minecraft:mining_fatigue;0;0", "2;30;3;minecraft:absorption;0;0"};
    private static final String ESOR_CATEGORY = "subspaceparasite:marauder";
    public static float esorHealthMultiplier = 1.0f;
    public static float esorDamageMultiplier = 1.0f;
    public static float esorArmorMultiplier = 1.0f;
    public static float esorKDResistanceMultiplier = 1.0f;
    public static int esorSpawnRate = 0;
    public static boolean esorEnabled = true;
    public static String[] esorLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    public static String[] esorOrbEffects = new String[]{"0;15;3;minecraft:hunger;0;0", "0;70;3;subspaceparasite:needler;0;0", "0;15;3;minecraft:mining_fatigue;0;0", "0;15;3;minecraft:weakness;0;0"};
    private static final String ORCH_CATEGORY = "subspaceparasite:monarch";
    public static float orchHealthMultiplier = 1.0f;
    public static float orchDamageMultiplier = 1.0f;
    public static float orchArmorMultiplier = 1.0f;
    public static float orchKDResistanceMultiplier = 1.0f;
    public static int orchSpawnRate = 0;
    public static boolean orchEnabled = true;
    public static String[] orchLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    public static String[] orchOrbEffects = new String[]{"0;15;3;minecraft:hunger;0;0", "0;70;3;subspaceparasite:needler;0;0", "0;15;3;minecraft:mining_fatigue;0;0", "0;15;3;minecraft:wither;0;0"};
    private static final String FLOG_CATEGORY = "subspaceparasite:grunt";
    public static float flogHealthMultiplier = 1.0f;
    public static float flogDamageMultiplier = 1.0f;
    public static float flogArmorMultiplier = 1.0f;
    public static float flogKDResistanceMultiplier = 1.0f;
    public static int flogSpawnRate = 0;
    public static boolean flogEnabled = true;
    public static String[] flogLoot = new String[]{"subspaceparasite:lurecomponent5;70;1;false"};
    private static final String ORONCO_CATEGORY = "subspaceparasite:anc_dreadnaut";
    public static float oroncoHealthMultiplier = 1.0f;
    public static float oroncoDamageMultiplier = 1.0f;
    public static float oroncoArmorMultiplier = 1.0f;
    public static float oroncoKDResistanceMultiplier = 1.0f;
    public static int oroncoSpawnRate = 1;
    public static boolean oroncoEnabled = true;
    public static int oroncoMaxY = 256;
    public static int oroncoMinY = 7;
    public static String[] oroncoLoot = new String[0];
    public static String[] oroncoMobList = new String[]{"subspaceparasite:rupter;1", "subspaceparasite:rupter;1", "subspaceparasite:rupter;1", "subspaceparasite:rupter;05", "subspaceparasite:rupter;0.5", "subspaceparasite:grunt;0.7"};
    public static boolean oroncoG = true;
    public static int oroncoMaxMobPod = 1;
    public static int oroncoPodNumber = 5;
    public static int oroncoPodCooldown = 12;
    private static final String TERLA_CATEGORY = "subspaceparasite:anc_overlord";
    public static float terlaHealthMultiplier = 1.0f;
    public static float terlaDamageMultiplier = 1.0f;
    public static float terlaArmorMultiplier = 1.0f;
    public static float terlaKDResistanceMultiplier = 1.0f;
    public static int terlaSpawnRate = 1;
    public static boolean terlaEnabled = true;
    public static String[] terlaLoot = new String[0];
    private static final String RANRAC_CATEGORY = "subspaceparasite:arachnida";
    public static float arachnidaHealthMultiplier = 1.0f;
    public static float arachnidaDamageMultiplier = 1.0f;
    public static float arachnidaArmorMultiplier = 1.0f;
    public static float arachnidaKDResistanceMultiplier = 1.0f;
    public static float arachnidaadaptedhealth = 45.0f;
    public static float arachnidaadapteddamage = 15.0f;
    public static float arachnidaadaptedarmor = 10.0f;
    public static float arachnidaadaptedkdresistance = 0.2f;
    public static String[] arachnidaadaptedloot = new String[]{"subspaceparasite:lurecomponent4;60;3;false", "subspaceparasite:ada_arachnida_drop;80;3;true", "subspaceparasite:bone;20;2;true"};
    public static String[] arachnidaadaptedOrbEffects = new String[]{"0;15;2;minecraft:hunger;0;0", "0;35;2;subspaceparasite:needler;0;0", "0;15;2;minecraft:blindness;0;0"};
    public static int arachnidaSpawnRate = 10;
    public static int arachnidaASpawnRate = 10;
    public static boolean arachnidaEnabled = true;
    public static String[] arachnidaLoot = new String[]{"subspaceparasite:lurecomponent3;40;1;false", "subspaceparasite:ada_arachnida_drop;40;2;true", "subspaceparasite:bone;10;1;true"};
    public static String[] arachnidaOrbEffects = new String[]{"0;15;1;minecraft:hunger;0;0", "0;15;1;minecraft:blindness;0;0"};
    private static final String KOL_CATEGORY = "subspaceparasite:worker";
    public static float kolHealthMultiplier = 1.0f;
    public static float kolDamageMultiplier = 1.0f;
    public static float kolArmorMultiplier = 1.0f;
    public static float kolKDResistanceMultiplier = 1.0f;
    public static int kolSpawnRate = 15;
    public static boolean kolEnabled = true;
    public static String[] kolLoot = new String[0];
    private static final String INHOOS_CATEGORY = "subspaceparasite:inhoos";
    public static float inhooSHealthMultiplier = 1.0f;
    public static float inhooSDamageMultiplier = 1.0f;
    public static float inhooSArmorMultiplier = 1.0f;
    public static float inhooSKDResistanceMultiplier = 1.0f;
    public static int inhooSSpawnRate = 15;
    public static boolean inhooSEnabled = true;
    public static String[] inhooSLoot = new String[0];
    private static final String INHOOM_CATEGORY = "subspaceparasite:inhoom";
    public static float inhooMHealthMultiplier = 1.0f;
    public static float inhooMDamageMultiplier = 1.0f;
    public static float inhooMArmorMultiplier = 1.0f;
    public static float inhooMKDResistanceMultiplier = 1.0f;
    public static int inhooMSpawnRate = 15;
    public static boolean inhooMEnabled = true;
    public static String[] inhooMLoot = new String[0];
    private static final String DONE_CATEGORY = "subspaceparasite:dredge";
    public static float doneHealthMultiplier = 1.0f;
    public static float doneDamageMultiplier = 1.0f;
    public static float doneArmorMultiplier = 1.0f;
    public static float doneKDResistanceMultiplier = 1.0f;
    public static int doneSpawnRate = 15;
    public static boolean doneEnabled = true;
    public static String[] doneLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    private static final String LEER_CATEGORY = "subspaceparasite:airscrew";
    public static float leerHealthMultiplier = 1.0f;
    public static float leerDamageMultiplier = 1.0f;
    public static float leerArmorMultiplier = 1.0f;
    public static float leerKDResistanceMultiplier = 1.0f;
    public static int leerSpawnRate = 15;
    public static boolean leerEnabled = true;
    public static String[] leerLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    private static final String QUAC_CATEGORY = "subspaceparasite:worm_carrier";
    public static float quacHealthMultiplier = 1.0f;
    public static float quacDamageMultiplier = 1.0f;
    public static float quacArmorMultiplier = 1.0f;
    public static float quacKDResistanceMultiplier = 1.0f;
    public static int quacSpawnRate = 2;
    public static boolean quacEnabled = true;
    public static String[] quacLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    private static final String ROND_CATEGORY = "subspaceparasite:carrier_worm";
    public static float rondHealthMultiplier = 1.0f;
    public static float rondDamageMultiplier = 1.0f;
    public static float rondArmorMultiplier = 1.0f;
    public static float rondKDResistanceMultiplier = 1.0f;
    public static int rondSpawnRate = 15;
    public static boolean rondEnabled = true;
    public static String[] rondLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    private static final String OMBOO_CATEGORY = "subspaceparasite:bomber_light";
    public static float ombooHealthMultiplier = 1.0f;
    public static float ombooDamageMultiplier = 1.0f;
    public static float ombooArmorMultiplier = 1.0f;
    public static float ombooKDResistanceMultiplier = 1.0f;
    public static int ombooSpawnRate = 15;
    public static float ombooDamage = 12.0f;
    public static boolean ombooEnabled = true;
    public static boolean ombooGriefing = true;
    public static String[] ombooLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false"};
    public static int ombooMaxY = 256;
    private static final String JINJO_CATEGORY = "subspaceparasite:bomber_heavy";
    public static float jinjoHealthMultiplier = 1.0f;
    public static float jinjoDamageMultiplier = 1.0f;
    public static float jinjoArmorMultiplier = 1.0f;
    public static float jinjoKDResistanceMultiplier = 1.0f;
    public static float jinjoExplotionMult = 6.0f;
    public static int jinjoSpawnRate = 15;
    public static float jinjoDamage = 12.0f;
    public static boolean jinjoEnabled = true;
    public static boolean jinjoGriefing = true;
    public static String[] jinjoLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false", "subspaceparasite:hive_scrap;80;7;false"};
    public static String[] jinjoOrbEffects = new String[]{"0;15;4;minecraft:hunger;0;0", "0;120;4;subspaceparasite:needler;0;0", "0;15;4;minecraft:mining_fatigue;0;0", "0;10;4;minecraft:wither;0;0"};
    public static int jinjoMaxY = 256;
    public static String[] jinjoMobs = new String[]{"subspaceparasite:overseer", "subspaceparasite:vigilante", "subspaceparasite:marauder", "subspaceparasite:monarch"};
    private static final String flam_CATEGORY = "subspaceparasite:succor";
    public static float flamHealthMultiplier = 1.0f;
    public static float flamArmorMultiplier = 1.0f;
    public static float flamKDResistanceMultiplier = 1.0f;
    public static boolean flamEnabled = true;
    public static String[] flamLoot = new String[]{"subspaceparasite:hive_scrap;80;3;false"};
    private static final String VESTA_CATEGORY = "subspaceparasite:carrier_colony";
    public static float vestaHealthMultiplier = 1.0f;
    public static float vestaDamageMultiplier = 1.0f;
    public static float vestaArmorMultiplier = 1.0f;
    public static float vestaKDResistanceMultiplier = 1.0f;
    public static int vestaSpawnRate = 15;
    public static int vestacd = 30;
    public static int vestarange = 60;
    public static boolean vestaEnabled = true;
    public static String[] vestaLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false", "subspaceparasite:hive_scrap;80;7;false"};
    public static String[] vestaOrbEffects = new String[]{"0;15;4;minecraft:hunger;0;0", "0;120;4;subspaceparasite:needler;0;0", "0;15;4;minecraft:mining_fatigue;0;0", "0;10;4;minecraft:wither;0;0"};
    public static String[] vestaeffects = new String[]{"30;3;minecraft:regeneration", "60;2;subspaceparasite:foster", "10;1;subspaceparasite:link"};
    private static final String ELVIA_CATEGORY = "subspaceparasite:wraith";
    public static float elviaHealthMultiplier = 1.0f;
    public static float elviaDamageMultiplier = 1.0f;
    public static float elviaArmorMultiplier = 1.0f;
    public static float elviaKDResistanceMultiplier = 1.0f;
    public static int elviaSpawnRate = 15;
    public static boolean elviaEnabled = true;
    public static double elvianeededhealth = 0.4;
    public static String[] elviaLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false", "subspaceparasite:hive_scrap;80;7;false"};
    public static String[] elviaOrbEffects = new String[]{"0;15;4;minecraft:hunger;0;0", "0;120;4;subspaceparasite:needler;0;0", "0;15;4;minecraft:mining_fatigue;0;0", "0;10;4;minecraft:wither;0;0", "0;15;-20;minecraft:levitation;0;0"};
    private static final String LENCIA_CATEGORY = "subspaceparasite:bogle";
    public static float lenciaHealthMultiplier = 1.0f;
    public static float lenciaDamageMultiplier = 1.0f;
    public static float lenciaArmorMultiplier = 1.0f;
    public static float lenciaKDResistanceMultiplier = 1.0f;
    public static int lenciaSpawnRate = 15;
    public static boolean lenciaGriefing = true;
    public static boolean lenciaEnabled = true;
    public static double lencianeededhealth = 0.4;
    public static String[] lenciaLoot = new String[]{"subspaceparasite:lurecomponent5;70;1;false", "subspaceparasite:hive_scrap;80;7;false"};
    public static String[] lenciaOrbEffects = new String[]{"0;15;4;minecraft:hunger;0;0", "0;120;6;subspaceparasite:needler;0;0", "0;15;4;minecraft:mining_fatigue;0;0", "0;10;4;minecraft:wither;0;0", "0;15;-20;minecraft:levitation;0;0"};
    private static final String PHEON_CATEGORY = "subspaceparasite:haunter";
    public static float pheonHealthMultiplier = 1.0f;
    public static float pheonDamageMultiplier = 1.0f;
    public static float pheonArmorMultiplier = 1.0f;
    public static float pheonKDResistanceMultiplier = 1.0f;
    public static int pheonSpawnRate = 15;
    public static boolean pheonEnabled = true;
    public static String[] pheonLoot = new String[]{"subspaceparasite:lurecomponent5;80;1;false", "subspaceparasite:hive_scrap;80;7;false"};
    public static String[] pheonOrbEffects = new String[]{"0;15;4;minecraft:hunger;0;0", "0;120;4;subspaceparasite:needler;0;0", "0;15;4;minecraft:mining_fatigue;0;0", "0;10;4;minecraft:wither;0;0"};
    private static final String CRUXA_CATEGORY = "subspaceparasite:cruxa";
    public static float cruxaHealthMultiplier = 1.0f;
    public static float cruxaDamageMultiplier = 1.0f;
    public static float cruxaArmorMultiplier = 1.0f;
    public static float cruxaKDResistanceMultiplier = 1.0f;
    public static int cruxaSpawnRate = 0;
    public static boolean cruxaEnabled = true;
    public static String[] cruxaLoot = new String[0];
    public static double cruxaDamageGain = 0.12;
    public static int cruxaDamageCap = 10;
    public static int cruxMinGrowTime = 20;
    public static int cruxMaxGrowTime = 60;
    private static final String HEED_CATEGORY = "subspaceparasite:heed";
    public static float heedHealthMultiplier = 1.0f;
    public static float heedDamageMultiplier = 1.0f;
    public static float heedArmorMultiplier = 1.0f;
    public static float heedKDResistanceMultiplier = 1.0f;
    public static int heedSpawnRate = 0;
    public static boolean heedEnabled = true;
    public static String[] heedLoot = new String[0];
    private static final String LUM_CATEGORY = "subspaceparasite:devourer";
    public static float lumHealthMultiplier = 1.0f;
    public static float lumDamageMultiplier = 1.0f;
    public static float lumArmorMultiplier = 1.0f;
    public static float lumKDResistanceMultiplier = 1.0f;
    public static float lumadaptedhealth = 0.0f;
    public static float lumadapteddamage = 0.0f;
    public static float lumadaptedarmor = 0.0f;
    public static float lumadaptedkdresistance = 0.0f;
    public static String[] lumadaptedloot = new String[]{"subspaceparasite:ada_devourer_drop;40;4;true", "subspaceparasite:lurecomponent4;30;3;false", "subspaceparasite:bone;10;2;true", "srpmeshi:devourercala;50;1;true"};
    public static int lumSpawnRate = 15;
    public static int lumASpawnRate = 0;
    public static boolean lumEnabled = true;
    public static boolean lumWaterPlacement = true;
    public static String[] lumLoot = new String[]{"subspaceparasite:ada_devourer_drop;40;2;true", "subspaceparasite:lurecomponent3;40;2;false", "subspaceparasite:bone;10;1;true", "srpmeshi:devourercala;30;1;true"};
    private static final String NAK_CATEGORY = "subspaceparasite:seizer";
    public static float nakHealthMultiplier = 1.0f;
    public static float nakDamageMultiplier = 1.0f;
    public static float nakArmorMultiplier = 1.0f;
    public static int nakSpawnRate = 0;
    public static boolean nakEnabled = true;
    public static String[] nakLoot = new String[0];
    private static final String POD1_CATEGORY_RSI = "subspaceparasite:anc_pod";
    public static float pod1HealthMultiplier = 1.0f;
    public static float pod1DamageMultiplier = 1.0f;
    public static float pod1ArmorMultiplier = 1.0f;
    public static String[] pod1Loot = new String[0];
    public static String[] pod1Effects = new String[]{"10;-2;minecraft:saturation"};
    public static float venkrolHealthMultiplier = 1.0f;
    public static float venkrolDamageMultiplier = 1.0f;
    public static float venkrolArmorMultiplier = 1.0f;
    public static int venkrolSpawnrate = 0;
    public static int venkrolRange = 8;
    public static int venkrolCooldown = 10;
    public static int venkrollimit = 4;
    public static int venkrolTotalActiveMobs = 4;
    public static String[] venkrolmoblist = new String[]{"ground;subspaceparasite:rupter;1;1"};
    public static String[] venkrolLoot = new String[]{"subspaceparasite:beckon_drop;20;3;true"};
    public static int venkrolCAMinimumV = 4;
    public static float venkrolCAExtraM = 0.5f;
    public static int venkrolRangeY = 4;
    public static float venkrolsiiHealthMultiplier = 1.0f;
    public static float venkrolsiiDamageMultiplier = 1.0f;
    public static float venkrolsiiArmorMultiplier = 1.0f;
    public static int venkrolsiiRange = 16;
    public static int venkrolsiiCooldown = 12;
    public static int venkrolsiilimit = 3;
    public static int venkrolsiiTotalActiveMobs = 9;
    public static String[] venkrolsiimoblist = new String[]{"ground;subspaceparasite:sim_human;0.2;3", "ground;subspaceparasite:sim_cow;0.2;3", "ground;subspaceparasite:sim_sheep;0.2;3", "ground;subspaceparasite:sim_wolf;0.2;3", "ground;subspaceparasite:sim_bigspider;0.2;3", "ground;subspaceparasite:sim_pig;0.2;3", "ground;subspaceparasite:sim_villager;0.2;3", "ground;subspaceparasite:sim_horse;0.2;3", "ground;subspaceparasite:sim_bear;0.2;3", "ground;subspaceparasite:sim_enderman;0.2;3", "ground;subspaceparasite:rupter;0.1;1", "air;subspaceparasite:carrier_flying;0.3;3"};
    public static String[] venkrolsiiLoot = new String[]{"subspaceparasite:beckon_drop;60;7;true"};
    public static int venkrolsiiCAMinimumV = 4;
    public static float venkrolsiiCAExtraM = 0.5f;
    public static int venkrolsiiRangeY = 6;
    public static float venkrolsiiiHealthMultiplier = 1.0f;
    public static float venkrolsiiiDamageMultiplier = 1.0f;
    public static float venkrolsiiiArmorMultiplier = 1.0f;
    public static int venkrolsiiiRange = 32;
    public static int venkrolsiiiCooldown = 14;
    public static int venkrolsiiilimit = 2;
    public static int venkrolsiiiTotalActiveMobs = 12;
    public static String[] venkrolsiiimoblist = new String[]{"ground;subspaceparasite:pri_summoner;0.2;6", "ground;subspaceparasite:pri_manducater;0.4;6", "ground;subspaceparasite:pri_bolster;0.1;6", "ground;subspaceparasite:pri_reeker;0.4;6", "ground;subspaceparasite:pri_longarms;0.4;6", "ground;subspaceparasite:pri_arachnida;0.4;6", "ground;subspaceparasite:pri_vermin;0.3;6", "ground;subspaceparasite:pri_tozoon;0.3;3", "ground;subspaceparasite:sim_human;0.2;3", "ground;subspaceparasite:sim_cow;0.2;3", "ground;subspaceparasite:sim_sheep;0.2;3", "ground;subspaceparasite:sim_wolf;0.2;3", "ground;subspaceparasite:sim_bigspider;0.2;3", "ground;subspaceparasite:sim_pig;0.2;3", "ground;subspaceparasite:sim_villager;0.2;3", "ground;subspaceparasite:sim_horse;0.2;3", "ground;subspaceparasite:sim_bear;0.2;3", "ground;subspaceparasite:sim_enderman;0.2;3", "ground;subspaceparasite:carrier_heavy;0.2;6", "ground;subspaceparasite:rupter;0.1;1", "ground;subspaceparasite:vigilante;0.2;6", "ground;subspaceparasite:warden;0.2;6", "air;subspaceparasite:overseer;0.2;6", "air;subspaceparasite:bomber_light;0.2;6", "air;subspaceparasite:carrier_flying;0.5;3", "air;subspaceparasite:pri_yelloweye;0.5;6"};
    public static String[] venkrolsiiiLoot = new String[]{"subspaceparasite:beckon_drop;80;10;true"};
    public static int venkrolsiiiCAMinimumV = 4;
    public static float venkrolsiiiCAExtraM = 0.35f;
    public static int venkrolsiiiRangeY = 8;
    public static float venkrolsivHealthMultiplier = 1.0f;
    public static float venkrolsivDamageMultiplier = 1.0f;
    public static float venkrolsivArmorMultiplier = 1.0f;
    public static int venkrolsivCooldown = 8;
    public static int venkrolsivlimit = 4;
    public static int venkrolsivTotalActiveMobs = 12;
    public static String[] venkrolsivmoblist = new String[]{"ground;subspaceparasite:beckon_si;0.3;3", "ground;subspaceparasite:beckon_sii;0.4;3", "ground;subspaceparasite:beckon_siii;0.3;4"};
    public static String[] venkrolsivLoot = new String[]{"subspaceparasite:beckon_drop;90;30;true"};
    public static int venkrolsivCAMinimumV = 4;
    public static float venkrolsivCAExtraM = 0.35f;
    public static float dodsiHealthMultiplier = 1.0f;
    public static float dodsiDamageMultiplier = 1.0f;
    public static float dodsiArmorMultiplier = 1.0f;
    public static int dodsiTotalActiveMobs = 3;
    public static int dodsiFollowRangeMult = 2;
    public static String[] dodsiLoot = new String[]{"subspaceparasite:dispatcher_drop;20;7;true"};
    public static String[] dodsiEffects = new String[]{"100;minecraft:speed;0", "100;subspaceparasite:debar;1", "200;subspaceparasite:link;1", "100;subspaceparasite:senses;1"};
    public static float dodsiiHealthMultiplier = 1.0f;
    public static float dodsiiDamageMultiplier = 1.0f;
    public static float dodsiiArmorMultiplier = 1.0f;
    public static int dodsiiTotalActiveMobs = 5;
    public static int dodsiiFollowRangeMult = 2;
    public static String[] dodsiiLoot = new String[]{"subspaceparasite:dispatcher_drop;65;15;true"};
    public static String[] dodsiiEffects = new String[]{"200;minecraft:speed;1", "200;minecraft:regeneration;2", "200;subspaceparasite:debar;1", "300;subspaceparasite:link;1", "200;subspaceparasite:senses;2"};
    public static float dodsiiiHealthMultiplier = 1.0f;
    public static float dodsiiiDamageMultiplier = 1.0f;
    public static float dodsiiiArmorMultiplier = 1.0f;
    public static int dodsiiiTotalActiveMobs = 7;
    public static int dodsiiiFollowRangeMult = 2;
    public static String[] dodsiiiLoot = new String[]{"subspaceparasite:dispatcher_drop;90;30;true"};
    public static String[] dodsiiiEffects = new String[]{"300;minecraft:speed;2", "300;minecraft:regeneration;3", "300;minecraft:strength;3", "300;subspaceparasite:debar;1", "400;subspaceparasite:link;2", "300;subspaceparasite:senses;3"};
    public static float dodsivHealthMultiplier = 1.0f;
    public static float dodsivDamageMultiplier = 1.0f;
    public static float dodsivArmorMultiplier = 1.0f;
    public static int dodsivTotalActiveMobs = 9;
    public static int dodsivFollowRangeMult = 2;
    public static String[] dodsivLoot = new String[]{"subspaceparasite:dispatcher_drop;100;70;true"};
    public static String[] dodsivEffects = new String[]{"400;minecraft:speed;3", "400;minecraft:regeneration;4", "400;minecraft:strength;4", "400;minecraft:absorption;4", "400;subspaceparasite:debar;1", "500;subspaceparasite:link;3", "400;subspaceparasite:senses;4"};
    public static float leemHealthMultiplier = 1.0f;
    public static float leemDamageMultiplier = 1.0f;
    public static float leemArmorMultiplier = 1.0f;
    public static int leemRange = 16;
    public static int leemRangeEffect = 20;
    public static int leemCooldown = 12;
    public static int leemlimit = 3;
    public static float leemsiiHealthMultiplier = 1.0f;
    public static float leemsiiDamageMultiplier = 1.0f;
    public static float leemsiiArmorMultiplier = 1.0f;
    public static int leemsiiRange = 32;
    public static int leemsiiRangeEffect = 30;
    public static int leemsiiCooldown = 10;
    public static int leemsiilimit = 4;
    public static float leemsiiiHealthMultiplier = 1.0f;
    public static float leemsiiiDamageMultiplier = 1.0f;
    public static float leemsiiiArmorMultiplier = 1.0f;
    public static int leemsiiiRange = 48;
    public static int leemsiiiRangeEffect = 40;
    public static int leemsiiiCooldown = 8;
    public static int leemsiiilimit = 5;
    public static float leemsivHealthMultiplier = 1.0f;
    public static float leemsivDamageMultiplier = 1.0f;
    public static float leemsivArmorMultiplier = 1.0f;
    public static int leemsivRange = 128;
    public static int leemsivRangeEffect = 56;
    public static int leemsivCooldown = 6;
    public static int leemsivlimit = 6;
    private static final String REINFORCEMENT_CATEGORY_BSI = "subspaceparasite:beckon_i";
    private static final String REINFORCEMENT_CATEGORY_BSII = "subspaceparasite:beckon_ii";
    private static final String REINFORCEMENT_CATEGORY_BSIII = "subspaceparasite:beckon_iii";
    private static final String REINFORCEMENT_CATEGORY_BSIV = "subspaceparasite:beckon_iv";
    private static final String REINFORCEMENT_CATEGORY_RSI = "subspaceparasite:dispatcher_si";
    private static final String REINFORCEMENT_CATEGORY_RSII = "subspaceparasite:dispatcher_sii";
    private static final String REINFORCEMENT_CATEGORY_RSIII = "subspaceparasite:dispatcher_siii";
    private static final String REINFORCEMENT_CATEGORY_RSIV = "subspaceparasite:dispatcher_siv";
    private static final String REINFORCEMENT_CATEGORY_RTSI = "subspaceparasite:rooter_si";
    private static final String REINFORCEMENT_CATEGORY_RTSII = "subspaceparasite:rooter_sii";
    private static final String REINFORCEMENT_CATEGORY_RTSIII = "subspaceparasite:rooter_siii";
    private static final String REINFORCEMENT_CATEGORY_RTSIV = "subspaceparasite:rooter_siv";
    private static final String HEBLU_CATEGORY = "subspaceparasite:draconite";
    public static float hebluHealthMultiplier = 1.0f;
    public static float hebluDamageMultiplier = 1.0f;
    public static float hebluArmorMultiplier = 1.0f;
    public static float hebluKDResistanceMultiplier = 1.0f;
    public static int hebluSpawnRate = 1;
    public static boolean hebluEnabled = true;
    public static String[] hebluLoot = new String[]{"subspaceparasite:false_apple;100;1;true", "subspaceparasite:trophy_boom_orb;100;1;true"};
    private static final String KIRIN_CATEGORY = "subspaceparasite:kirin";
    public static float kirinHealthMultiplier = 1.0f;
    public static float kirinDamageMultiplier = 1.0f;
    public static float kirinArmorMultiplier = 1.0f;
    public static float kirinKDResistanceMultiplier = 1.0f;
    public static int kirinSpawnRate = 1;
    public static boolean kirinEnabled = true;
    public static String[] kirinLoot = new String[]{"subspaceparasite:trophy_void_orb;100;1;true"};

    private static void initGeneralMobsConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL_MOBS, "Mob configuration \nVersion:1.10.5\n \nEntities IDs: \nsubspaceparasite:sim_bigspider \nsubspaceparasite:sim_human \nsubspaceparasite:sim_cow \nsubspaceparasite:sim_sheep \nsubspaceparasite:sim_wolf \nsubspaceparasite:sim_pig \nsubspaceparasite:sim_villager \nsubspaceparasite:sim_adventurer \nsubspaceparasite:sim_horse \nsubspaceparasite:sim_bear \nsubspaceparasite:sim_enderman \nsubspaceparasite:sim_dragone \nsubspaceparasite:sim_sheephead \nsubspaceparasite:sim_wolfhead \nsubspaceparasite:sim_cowhead \nsubspaceparasite:sim_pighead \nsubspaceparasite:sim_villagerhead \nsubspaceparasite:sim_horsehead \nsubspaceparasite:sim_humanhead \nsubspaceparasite:sim_endermanhead \nsubspaceparasite:sim_dragonehead \nsubspaceparasite:sim_adventurerhead \nsubspaceparasite:fer_bear \nsubspaceparasite:fer_cow \nsubspaceparasite:fer_enderman \nsubspaceparasite:fer_horse \nsubspaceparasite:fer_human \nsubspaceparasite:fer_pig \nsubspaceparasite:fer_sheep \nsubspaceparasite:fer_villager \nsubspaceparasite:fer_wolf \nsubspaceparasite:hi_blaze \nsubspaceparasite:hi_golem \nsubspaceparasite:hi_skeleton \nsubspaceparasite:incompleteform_small \nsubspaceparasite:incompleteform_medium \nsubspaceparasite:carrier_heavy \nsubspaceparasite:carrier_light \nsubspaceparasite:buglin \nsubspaceparasite:carrier_flying \nsubspaceparasite:rupter \nsubspaceparasite:movingflesh \nsubspaceparasite:worker \nsubspaceparasite:mangler \nsubspaceparasite:gnat \nsubspaceparasite:beckon_si \nsubspaceparasite:beckon_sii \nsubspaceparasite:beckon_siii \nsubspaceparasite:beckon_siv \nsubspaceparasite:dispatcherten \nsubspaceparasite:dispatcher_si \nsubspaceparasite:dispatcher_sii \nsubspaceparasite:dispatcher_siii \nsubspaceparasite:dispatcher_siv \nsubspaceparasite:kyphosis \nsubspaceparasite:sentry \nsubspaceparasite:seizer \nsubspaceparasite:worm \nsubspaceparasite:host \nsubspaceparasite:hostii \nsubspaceparasite:heed \nsubspaceparasite:crux \nsubspaceparasite:thrall \nsubspaceparasite:pri_longarms \nsubspaceparasite:pri_manducater \nsubspaceparasite:pri_reeker \nsubspaceparasite:pri_yelloweye \nsubspaceparasite:pri_summoner \nsubspaceparasite:pri_bolster \nsubspaceparasite:pri_arachnida \nsubspaceparasite:pri_devourer \nsubspaceparasite:pri_tozoon \nsubspaceparasite:pri_vermin \nsubspaceparasite:ada_longarms \nsubspaceparasite:ada_manducater \nsubspaceparasite:ada_reeker \nsubspaceparasite:ada_yelloweye \nsubspaceparasite:ada_summoner \nsubspaceparasite:ada_bolster \nsubspaceparasite:ada_arachnida \nsubspaceparasite:overseer \nsubspaceparasite:vigilante \nsubspaceparasite:warden \nsubspaceparasite:bomber_light \nsubspaceparasite:marauder \nsubspaceparasite:monarch \nsubspaceparasite:grunt \nsubspaceparasite:bomber_heavy \nsubspaceparasite:wraith \nsubspaceparasite:bogle \nsubspaceparasite:haunter \nsubspaceparasite:carrier_colony \nsubspaceparasite:succor \nsubspaceparasite:seeker \nsubspaceparasite:architect \nsubspaceparasite:anc_dreadnaut \nsubspaceparasite:anc_overlord \nsubspaceparasite:anc_pod \nsubspaceparasite:anc_dreadnaut_ten \nsubspaceparasite:pullingball \nsubspaceparasite:webball \nsubspaceparasite:spineball \nsubspaceparasite:nadeball \nsubspaceparasite:salivaball \nsubspaceparasite:ballball \nsubspaceparasite:ancientball \nsubspaceparasite:homming \nsubspaceparasite:antiinfestedblock \nsubspaceparasite:biomassball \nsubspaceparasite:missile \nsubspaceparasite:balltall \nsubspaceparasite:ballmall \nsubspaceparasite:kirin \nsubspaceparasite:draconite \nsubspaceparasite:orb \nsubspaceparasite:source \nsubspaceparasite:remain \nsubspaceparasite:bomb \nsubspaceparasite:cloudtoxic \nsubspaceparasite:biomass \nsubspaceparasite:gore \nsubspaceparasite:tendril \nsubspaceparasite:scent \nsubspaceparasite:wave \nsubspaceparasite:waveshock \nsubspaceparasite:nade \n");
    }

    private static void initShycoConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(SHYCO_CATEGORY, "Longarms \n Base Health: " + SPAttributes.SHYCO_HEALTH + " \n Base Damage: " + SPAttributes.SHYCO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.SHYCO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.SHYCO_KD_RESISTANCE);
        shycoEnabled = cfg.getBoolean("Primitive Longarms Enabled", SHYCO_CATEGORY, shycoEnabled, "Set to false if you want to disable Primitive Longarms.");
        shycoHealthMultiplier = cfg.getFloat("Primitive Longarms" + health, SHYCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Longarms.");
        shycoDamageMultiplier = cfg.getFloat("Primitive Longarms" + damage, SHYCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Longarms.");
        shycoArmorMultiplier = cfg.getFloat("Primitive Longarms" + armor, SHYCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Longarms.");
        shycoKDResistanceMultiplier = cfg.getFloat("Primitive Longarms" + kd, SHYCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Longarms.");
        shycoSpawnRate = cfg.getInt("Primitive Longarms SpawnWeight", SHYCO_CATEGORY, shycoSpawnRate, 0, 100, "Spawn rate for Primitive Longarms (This value is ignored if Evolution Phases are enabled, it has its own option).");
        shycoLoot = cfg.getStringList("Primitive Longarms Loot Table", SHYCO_CATEGORY, shycoLoot, "Items you want the Primitive Longarms to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        shycoDamageIncreased = cfg.getFloat("Primitive Longarms damage Increase", SHYCO_CATEGORY, (float)shycoDamageIncreased, 0.0f, 1.0f, "For every 1% HP lost its damage will increase by this amount of its total damage (1 = 100%) for Primitive Longarms.");
        shycoOrbEffects = cfg.getStringList("Primitive Longarms Orb Effects", SHYCO_CATEGORY, shycoOrbEffects, "Orb effects " + orb);
        shycoadaptedhealth = cfg.getFloat("Stage Adapted additional Health", SHYCO_CATEGORY, shycoadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Longarms.");
        shycoadapteddamage = cfg.getFloat("Stage Adapted additional Damage", SHYCO_CATEGORY, shycoadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Longarms.");
        shycoadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", SHYCO_CATEGORY, shycoadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Longarms.");
        shycoadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", SHYCO_CATEGORY, shycoadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Longarms.");
        shycoadapteddamageincrease = cfg.getFloat("Stage Adapted damage Increase", SHYCO_CATEGORY, (float)shycoadapteddamageincrease, 0.0f, 1.0f, "For every 1% HP lost its damage will increase by this amount of its total damage (1 = 100%) for Adapted Longarms.");
        shycoadaptedloot = cfg.getStringList("Stage Adapted loot Table", SHYCO_CATEGORY, shycoadaptedloot, "Items you want the Adapted Longarms to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        shycoASpawnRate = cfg.getInt("Stage Adapted spawnweight", SHYCO_CATEGORY, shycoASpawnRate, 0, 100, "Spawn rate for Adapted Longarms (This value is ignored if Evolution Phases are enabled, it has its own option).");
        shycoadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", SHYCO_CATEGORY, shycoadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initGimConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(GIM_CATEGORY, "Viscera \n Base Health: " + SPAttributes.GIM_HEALTH + " \n Base Damage: " + SPAttributes.GIM_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.GIM_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.GIM_KD_RESISTANCE);
        gimEnabled = cfg.getBoolean("Primitive Viscera Enabled", GIM_CATEGORY, gimEnabled, "Set to false if you want to disable Primitive Viscera.");
        gimHealthMultiplier = cfg.getFloat("Primitive Viscera" + health, GIM_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Viscera.");
        gimDamageMultiplier = cfg.getFloat("Primitive Viscera" + damage, GIM_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Viscera.");
        gimArmorMultiplier = cfg.getFloat("Primitive Viscera" + armor, GIM_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Viscera.");
        gimKDResistanceMultiplier = cfg.getFloat("Primitive Viscera" + kd, GIM_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Viscera.");
        gimSpawnRate = cfg.getInt("Primitive Viscera SpawnWeight", GIM_CATEGORY, gimSpawnRate, 0, 100, "Spawn rate for Primitive Viscera (This value is ignored if Evolution Phases are enabled, it has its own option).");
        gimLoot = cfg.getStringList("Primitive Viscera Loot Table", GIM_CATEGORY, gimLoot, "Items you want the Primitive Viscera to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        gimOrbEffects = cfg.getStringList("Primitive Viscera Orb Effects", GIM_CATEGORY, gimOrbEffects, "Orb effects " + orb);
        gimadaptedhealth = cfg.getFloat("Stage Adapted additional Health", GIM_CATEGORY, gimadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Viscera.");
        gimadapteddamage = cfg.getFloat("Stage Adapted additional Damage", GIM_CATEGORY, gimadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Viscera.");
        gimadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", GIM_CATEGORY, gimadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Viscera.");
        gimadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", GIM_CATEGORY, gimadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Viscera.");
        gimadaptedloot = cfg.getStringList("Stage Adapted loot Table", GIM_CATEGORY, gimadaptedloot, "Items you want the Adapted Viscera to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        gimASpawnRate = cfg.getInt("Stage Adapted spawnweight", GIM_CATEGORY, gimASpawnRate, 0, 100, "Spawn rate for Adapted Viscera (This value is ignored if Evolution Phases are enabled, it has its own option).");
        gimadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", GIM_CATEGORY, gimadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initZaaConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(ZAA_CATEGORY, "Burrower \n Base Health: " + SPAttributes.ZAA_HEALTH + " \n Base Damage: " + SPAttributes.ZAA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ZAA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ZAA_KD_RESISTANCE);
        zaaEnabled = cfg.getBoolean("Primitive Burrower Enabled", ZAA_CATEGORY, zaaEnabled, "Set to false if you want to disable Primitive Burrower.");
        zaaHealthMultiplier = cfg.getFloat("Primitive Burrower" + health, ZAA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Burrower.");
        zaaDamageMultiplier = cfg.getFloat("Primitive Burrower" + damage, ZAA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Burrower.");
        zaaArmorMultiplier = cfg.getFloat("Primitive Burrower" + armor, ZAA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Burrower.");
        zaaKDResistanceMultiplier = cfg.getFloat("Primitive Burrower" + kd, ZAA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Burrower.");
        zaaSpawnRate = cfg.getInt("Primitive Burrower SpawnWeight", ZAA_CATEGORY, zaaSpawnRate, 0, 100, "Spawn rate for Primitive Burrower (This value is ignored if Evolution Phases are enabled, it has its own option).");
        zaaLoot = cfg.getStringList("Primitive Burrower Loot Table", ZAA_CATEGORY, zaaLoot, "Items you want the Primitive Burrower to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        zaaOrbEffects = cfg.getStringList("Primitive Burrower Orb Effects", ZAA_CATEGORY, zaaOrbEffects, "Orb effects " + orb);
        zaaadaptedhealth = cfg.getFloat("Stage Adapted additional Health", ZAA_CATEGORY, zaaadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Burrower.");
        zaaadapteddamage = cfg.getFloat("Stage Adapted additional Damage", ZAA_CATEGORY, zaaadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Burrower.");
        zaaadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", ZAA_CATEGORY, zaaadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Burrower.");
        zaaadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", ZAA_CATEGORY, zaaadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Burrower.");
        zaaadaptedloot = cfg.getStringList("Stage Adapted loot Table", ZAA_CATEGORY, zaaadaptedloot, "Items you want the Adapted Burrower to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        zaaASpawnRate = cfg.getInt("Stage Adapted spawnweight", ZAA_CATEGORY, zaaASpawnRate, 0, 100, "Spawn rate for Adapted Burrower (This value is ignored if Evolution Phases are enabled, it has its own option).");
        zaaadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", ZAA_CATEGORY, zaaadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initdorpaConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(DORPA_CATEGORY, "Big Spider \n Base Health: " + SPAttributes.DORPA_HEALTH + " \n Base Damage: " + SPAttributes.DORPA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.DORPA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.DORPA_KD_RESISTANCE + " \n Base Ranged Damage: " + SPAttributes.DORPA_RANGED_DAMAGE);
        dorpaEnabled = cfg.getBoolean("Big Spider Enabled", DORPA_CATEGORY, dorpaEnabled, "Set to false if you want to disable Big Spider.");
        dorpaHealthMultiplier = cfg.getFloat("Big Spider" + health, DORPA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Big Spider.");
        dorpaDamageMultiplier = cfg.getFloat("Big Spider" + damage, DORPA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Big Spider.");
        dorpaArmorMultiplier = cfg.getFloat("Big Spider" + armor, DORPA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Big Spider.");
        dorpaKDResistanceMultiplier = cfg.getFloat("Big Spider" + kd, DORPA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Big Spider.");
        dorpaSpawnRate = cfg.getInt("Big Spider SpawnWeight", DORPA_CATEGORY, dorpaSpawnRate, 0, 100, "Spawn rate for Big Spider (This value is ignored if Evolution Phases are enabled, it has its own option).");
        dorpaWeb = cfg.getBoolean("Big Spider WebAttack", DORPA_CATEGORY, dorpaWeb, "Set to false if you want to disable Big Spider web attack blocks.");
        dorpaRangedAttackDamageMultiplier = cfg.getFloat("Big Spider Ranged" + damage, DORPA_CATEGORY, 1.0f, 0.0f, 100.0f, "Damage multiplier for Big Spider projectile attack (only if WebAttack is false).");
        dorpaLoot = cfg.getStringList("Big Spider Loot Table", DORPA_CATEGORY, new String[0], "Items you want the Big Spider to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        dorpamob = cfg.getString("Big Spider Mobs Inside", DORPA_CATEGORY, dorpamob, "Mob the Big Spider spawns when killed." + mobTable);
    }

    private static void initratholConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(RATHOL_CATEGORY, "Heavy Carrier \n Base Health: " + SPAttributes.RATHOL_HEALTH + " \n Base Damage: " + SPAttributes.RATHOL_DAMAGE + " \n Base Armor: " + SPAttributes.RATHOL_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.RATHOL_KD_RESISTANCE);
        ratholEnabled = cfg.getBoolean("Heavy Carrier Enabled", RATHOL_CATEGORY, ratholEnabled, "Set to false if you want to disable Heavy Carrier.");
        ratholHealthMultiplier = cfg.getFloat("Heavy Carrier" + health, RATHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Heavy Carrier.");
        ratholDamageMultiplier = cfg.getFloat("Heavy Carrier" + damage, RATHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Heavy Carrier.");
        ratholArmorMultiplier = cfg.getFloat("Heavy Carrier" + armor, RATHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Heavy Carrier.");
        ratholKDResistanceMultiplier = cfg.getFloat("Heavy Carrier" + kd, RATHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Heavy Carrier.");
        ratholSpawnRate = cfg.getInt("Heavy Carrier SpawnWeight", RATHOL_CATEGORY, ratholSpawnRate, 0, 100, "Spawn rate for Heavy Carrier (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ratholGriefing = cfg.getBoolean("Heavy Carrier Griefing", RATHOL_CATEGORY, ratholGriefing, "Set to true if you want the Heavy Carrier to destroy blocks on explosion.");
        ratholLoot = cfg.getStringList("Heavy Carrier Loot Table", RATHOL_CATEGORY, new String[0], "Items you want the Heavy Carrier to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        ratholMobs = cfg.getStringList("Heavy Carrier Mob Table", RATHOL_CATEGORY, ratholMobs, "Mob list for Heavy Carrier." + mobTable);
    }

    private static void initgotholConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(GOTHOL_CATEGORY, "Light Carrier \n Base Health: " + SPAttributes.GOTHOL_HEALTH + " \n Base Damage: " + SPAttributes.GOTHOL_DAMAGE + " \n Base Armor: " + SPAttributes.GOTHOL_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.GOTHOL_KD_RESISTANCE);
        gotholEnabled = cfg.getBoolean("Light Carrier Enabled", GOTHOL_CATEGORY, gotholEnabled, "Set to false if you want to disable Light Carrier.");
        gotholHealthMultiplier = cfg.getFloat("Light Carrier" + health, GOTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Light Carrier.");
        gotholDamageMultiplier = cfg.getFloat("Light Carrier" + damage, GOTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Light Carrier.");
        gotholArmorMultiplier = cfg.getFloat("Light Carrier" + armor, GOTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Light Carrier.");
        gotholKDResistanceMultiplier = cfg.getFloat("Light Carrier" + kd, GOTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Light Carrier.");
        gotholSpawnRate = cfg.getInt("Light Carrier SpawnWeight", GOTHOL_CATEGORY, gotholSpawnRate, 0, 100, "Spawn rate for Light Carrier (This value is ignored if Evolution Phases are enabled, it has its own option).");
        gotholGriefing = cfg.getBoolean("Light Carrier Griefing", GOTHOL_CATEGORY, gotholGriefing, "Set to true if you want the Light Carrier to destroy blocks on explosion.");
        gotholLoot = cfg.getStringList("Light Carrier Loot Table", GOTHOL_CATEGORY, new String[0], "Items you want the Light Carrier to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        gotholMobs = cfg.getStringList("Light Carrier Mob Table", GOTHOL_CATEGORY, gotholMobs, "Mob list for Light Carrier." + mobTable);
    }

    private static void initemanaConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(EMANA_CATEGORY, "Yelloweye \n Base Health: " + SPAttributes.EMANA_HEALTH + " \n Base Damage: " + SPAttributes.EMANA_RANGED_DAMAGE + " \n Base Armor: " + SPAttributes.EMANA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.EMANA_KD_RESISTANCE + " \n Poison Duration in seconds: " + emanaPoisonDuration + " \n Poison Amplifier: " + emanaPoisonAmplifier);
        emanaEnabled = cfg.getBoolean("Primitive Yelloweye Enabled", EMANA_CATEGORY, emanaEnabled, "Set to false if you want to disable Primitive Yelloweye.");
        emanaHealthMultiplier = cfg.getFloat("Primitive Yelloweye" + health, EMANA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Yelloweye.");
        emanaDamageMultiplier = cfg.getFloat("Primitive Yelloweye" + damage, EMANA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Yelloweye.");
        emanaArmorMultiplier = cfg.getFloat("Primitive Yelloweye" + armor, EMANA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Yelloweye.");
        emanaKDResistanceMultiplier = cfg.getFloat("Primitive Yelloweye" + kd, EMANA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Yelloweye.");
        emanaSpawnRate = cfg.getInt("Primitive Yelloweye SpawnWeight", EMANA_CATEGORY, emanaSpawnRate, 0, 100, "Spawn rate for Primitive Yelloweye (This value is ignored if Evolution Phases are enabled, it has its own option).");
        emanaPoisonDuration = cfg.getInt("Primitive Yelloweye Poison Duration", EMANA_CATEGORY, emanaPoisonDuration, 0, 100, "Poison duration in seconds for its projectile.");
        emanaPoisonAmplifier = cfg.getInt("Primitive Yelloweye Poison Amplifier", EMANA_CATEGORY, emanaPoisonAmplifier, 1, 100, "Poison amplifier for its projectile.");
        emanaLoot = cfg.getStringList("Primitive Yelloweye Loot Table", EMANA_CATEGORY, emanaLoot, "Items you want the Primitive Yelloweye to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        emanaMaxY = cfg.getInt("Primitive Yelloweye Flight Height Limit", EMANA_CATEGORY, emanaMaxY, 0, 256, "Number of blocks it can fly above the ground for Primitive Yelloweye (and Adapted version).");
        emanaGearD = cfg.getFloat("Primitive Yelloweye Gear degrade", EMANA_CATEGORY, (float)emanaGearD, 0.0f, 1.0f, "How much a shot will degrade your gear (1=100%).");
        emanaadaptedhealth = cfg.getFloat("Stage Adapted additional Health", EMANA_CATEGORY, emanaadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Yelloweye.");
        emanaadapteddamage = cfg.getFloat("Stage Adapted additional Damage", EMANA_CATEGORY, emanaadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Yelloweye.");
        emanaadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", EMANA_CATEGORY, emanaadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Yelloweye.");
        emanaadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", EMANA_CATEGORY, emanaadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Yelloweye.");
        emanaadaptedloot = cfg.getStringList("Stage Adapted loot Table", EMANA_CATEGORY, emanaadaptedloot, "Items you want the Adapted Yelloweye to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        emanaASpawnRate = cfg.getInt("Stage Adapted spawnweight", EMANA_CATEGORY, emanaASpawnRate, 0, 100, "Spawn rate for Adapted Yelloweye (This value is ignored if Evolution Phases are enabled, it has its own option).");
        emanaadaptedgeard = cfg.getFloat("Stage Adapted gear degrade", EMANA_CATEGORY, (float)emanaadaptedgeard, 0.0f, 1.0f, "How much a shot will degrade your gear (1=100%).");
        emanaadaptedmelee = cfg.getFloat("Stage Adapted melee", EMANA_CATEGORY, (float)emanaadaptedmelee, 0.0f, 1024.0f, "Damage it will do from melee for Stage Adapted.");
    }

    private static void initlodoConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(LODO_CATEGORY, "Buglin \n Base Health: " + SPAttributes.LODO_HEALTH + " \n Base Damage: " + SPAttributes.LODO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LODO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.LODO_KD_RESISTANCE);
        lodoEnabled = cfg.getBoolean("Buglin Enabled", LODO_CATEGORY, lodoEnabled, "Set to false if you want to disable Buglin.");
        lodoHealthMultiplier = cfg.getFloat("Buglin" + health, LODO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Buglin.");
        lodoDamageMultiplier = cfg.getFloat("Buglin" + damage, LODO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Buglin.");
        lodoArmorMultiplier = cfg.getFloat("Buglin" + armor, LODO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Buglin.");
        lodoKDResistanceMultiplier = cfg.getFloat("Buglin" + kd, LODO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Buglin.");
        lodoSpawnRate = cfg.getInt("Buglin SpawnWeight", LODO_CATEGORY, lodoSpawnRate, 0, 100, "Spawn rate for Buglin (This value is ignored if Evolution Phases are enabled, it has its own option).");
        LodoLoot = cfg.getStringList("Buglin Loot Table", LODO_CATEGORY, LodoLoot, "Items you want the Buglin to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void inithullConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(HULL_CATEGORY, "Manducater \n Base Health: " + SPAttributes.HULL_HEALTH + " \n Base Damage: " + SPAttributes.HULL_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HULL_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HULL_KD_RESISTANCE);
        hullEnabled = cfg.getBoolean("Primitive Manducater Enabled", HULL_CATEGORY, hullEnabled, "Set to false if you want to disable Primitive Manducater.");
        hullHealthMultiplier = cfg.getFloat("Primitive Manducater" + health, HULL_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Manducater.");
        hullDamageMultiplier = cfg.getFloat("Primitive Manducater" + damage, HULL_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Manducater.");
        hullArmorMultiplier = cfg.getFloat("Primitive Manducater" + armor, HULL_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Manducater.");
        hullKDResistanceMultiplier = cfg.getFloat("Primitive Manducater" + kd, HULL_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Manducater.");
        hullSpawnRate = cfg.getInt("Primitive Manducater SpawnWeight", HULL_CATEGORY, hullSpawnRate, 0, 100, "Spawn rate for Primitive Manducater (This value is ignored if Evolution Phases are enabled, it has its own option).");
        hullLoot = cfg.getStringList("Primitive Manducater Loot Table", HULL_CATEGORY, hullLoot, "Items you want the Primitive Manducater to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        hullNeededHealth = cfg.getFloat("Primitive Manducater Needed Health", HULL_CATEGORY, (float)hullNeededHealth, 0.0f, 1.0f, "Health (1 = 100%) needed to go invisible.");
        hullNeededTime = cfg.getFloat("Primitive Manducater Needed Time", HULL_CATEGORY, hullNeededTime, 1.0f, 100.0f, "Time (seconds) they need to go invisible.");
        hullStealthDamageMultiplier = cfg.getFloat("Primitive Manducater Stealth" + damage, HULL_CATEGORY, hullStealthDamageMultiplier, 0.01f, 100.0f, "Damage multiplier for Primitive Manducater when invisible.");
        hullOrbEffects = cfg.getStringList("Primitive Manducater Orb Effects", HULL_CATEGORY, hullOrbEffects, "Orb effects " + orb);
        hulladaptedhealth = cfg.getFloat("Stage Adapted additional Health", HULL_CATEGORY, hulladaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Manducater.");
        hulladapteddamage = cfg.getFloat("Stage Adapted additional Damage", HULL_CATEGORY, hulladapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Manducater.");
        hulladaptedarmor = cfg.getFloat("Stage Adapted additional Armor", HULL_CATEGORY, hulladaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Manducater.");
        hulladaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", HULL_CATEGORY, hulladaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Manducater.");
        hulladaptedloot = cfg.getStringList("Stage Adapted loot Table", HULL_CATEGORY, hulladaptedloot, "Items you want the Adapted Manducater to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        hulladaptedneededhealth = cfg.getFloat("Stage Adapted needed Health", HULL_CATEGORY, (float)hulladaptedneededhealth, 0.0f, 1.0f, "Health (1 = 100%) needed to go invisible.");
        hulladaptedneededtime = cfg.getFloat("Stage Adapted needed Time", HULL_CATEGORY, hulladaptedneededtime, 1.0f, 100.0f, "Time (seconds) they need to go invisible.");
        hulladaptedstealthdamage = cfg.getFloat("Stage Adapted additional Stealth Damage", HULL_CATEGORY, hulladaptedstealthdamage, 0.01f, 100.0f, "Additional damage for Adapted Manducater when invisible.");
        hullASpawnRate = cfg.getInt("Stage Adapted spawnweight", HULL_CATEGORY, hullASpawnRate, 0, 100, "Spawn rate for Adapted Manducater (This value is ignored if Evolution Phases are enabled, it has its own option).");
        hulladaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", HULL_CATEGORY, hulladaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initcanraConfig(Configuration cfg) {
        String Canraspawning = " Ex. \"minecraft:zombie;0.1;1\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn, \n \"1\" is for the cost the entity has. \n";
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(CANRA_CATEGORY, "Summoner \n Base Health: " + SPAttributes.CANRA_HEALTH + " \n Base Damage: " + SPAttributes.CANRA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.CANRA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.CANRA_KD_RESISTANCE);
        canraEnabled = cfg.getBoolean("Primitive Summoner Enabled", CANRA_CATEGORY, canraEnabled, "Set to false if you want to disable Primitive Summoner.");
        canraHealthMultiplier = cfg.getFloat("Primitive Summoner" + health, CANRA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Summoner.");
        canraDamageMultiplier = cfg.getFloat("Primitive Summoner" + damage, CANRA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Summoner.");
        canraArmorMultiplier = cfg.getFloat("Primitive Summoner" + armor, CANRA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Summoner.");
        canraKDResistanceMultiplier = cfg.getFloat("Primitive Summoner" + kd, CANRA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Summoner.");
        canraSpawnRate = cfg.getInt("Primitive Summoner SpawnWeight", CANRA_CATEGORY, canraSpawnRate, 0, 100, "Spawn rate for Primitive Summoner (This value is ignored if Evolution Phases are enabled, it has its own option).");
        canraLoot = cfg.getStringList("Primitive Summoner Loot Table", CANRA_CATEGORY, canraLoot, "Items you want the Primitive Summoner to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        canraSummoningCooldown = cfg.getInt("Primitive Summoner summoning Cooldown", CANRA_CATEGORY, canraSummoningCooldown, 0, 100, "Summoning cooldown in seconds.");
        canraTotalActiveMobs = cfg.getInt("Primitive Summoner Total Active Mobs", CANRA_CATEGORY, canraTotalActiveMobs, 0, 100, "Number of total points used in mob spawning for Primitive Summoner");
        canraMobList = cfg.getStringList("Primitive Summoner Mob List", CANRA_CATEGORY, canraMobList, "Mob list for Primitive Summoner." + Canraspawning);
        canraLimit = cfg.getInt("Primitive Summoner Limit", CANRA_CATEGORY, canraLimit, 0, 10000, "Number of attacks before its cooldown for Primitive Summoner.");
        canraRemainPlus = cfg.getInt("Primitive Summoner Life Value", CANRA_CATEGORY, canraRemainPlus, 0, 10000, "Life value for Primitive Summoner.");
        canraRemainHealth = cfg.getFloat("Primitive Summoner Rebuilt Value", CANRA_CATEGORY, canraRemainHealth, 0.01f, 2.0f, "Health (%) that Primitive Summoner's rebuilt mobs will have.");
        canraOrbEffects = cfg.getStringList("Primitive Summoner Orb Effects", CANRA_CATEGORY, canraOrbEffects, "Orb effects " + orb);
        canraadaptedhealth = cfg.getFloat("Stage Adapted additional Health", CANRA_CATEGORY, canraadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Summoner.");
        canraadapteddamage = cfg.getFloat("Stage Adapted additional Damage", CANRA_CATEGORY, canraadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Summoner.");
        canraadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", CANRA_CATEGORY, canraadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Summoner.");
        canraadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", CANRA_CATEGORY, canraadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Summoner.");
        canraadaptedsummoningcooldown = cfg.getInt("Stage Adapted summoning Cooldown", CANRA_CATEGORY, canraadaptedsummoningcooldown, 0, 100, "Summoning cooldown in seconds.");
        canraadaptedloot = cfg.getStringList("Stage Adapted loot Table", CANRA_CATEGORY, canraadaptedloot, "Items you want the Adapted Summoner to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        canraadaptedtotalactivemobs = cfg.getInt("Stage Adapted total active mobs", CANRA_CATEGORY, canraadaptedtotalactivemobs, 0, 100, "Number of total points used in mob spawning for Adapted Summoner.");
        canraadaptedmoblist = cfg.getStringList("Stage Adapted mob List", CANRA_CATEGORY, canraadaptedmoblist, "Mob list for Adapted Summoner." + Canraspawning);
        canraadaptedlimit = cfg.getInt("Stage Adapted limit", CANRA_CATEGORY, canraadaptedlimit, 0, 10000, "Number of attacks before its cooldown for Adapted Summoner.");
        canraASpawnRate = cfg.getInt("Stage Adapted spawnweight", CANRA_CATEGORY, canraASpawnRate, 0, 100, "Spawn rate for Adapted Summoner (This value is ignored if Evolution Phases are enabled, it has its own option).");
        canraadaptedremainplus = cfg.getInt("Stage Adapted Life Value", CANRA_CATEGORY, canraadaptedremainplus, 0, 10000, "Life value for Adapted Summoner.");
        canraadaptedremainhealth = cfg.getFloat("Stage Adapted Rebuilt Value", CANRA_CATEGORY, canraadaptedremainhealth, 0.01f, 2.0f, "Health (%) that Adapted Summoner's rebuilt mobs will have.");
        canraadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", CANRA_CATEGORY, canraadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initalafhaConfig(Configuration cfg) {
        String Canraspawning = " Ex. \"minecraft:zombie;0.1;1\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn, \n \"1\" is for the cost the entity has. \n";
        cfg.addCustomCategoryComment(ALAFHA_CATEGORY, "Overseer \n Base Health: " + SPAttributes.ALAFHA_HEALTH + " \n Base Damage: " + SPAttributes.ALAFHA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ALAFHA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ALAFHA_KD_RESISTANCE);
        alafhaEnabled = cfg.getBoolean("Overseer Enabled", ALAFHA_CATEGORY, alafhaEnabled, "Set to false if you want to disable Overseer.");
        alafhaHealthMultiplier = cfg.getFloat("Overseer" + health, ALAFHA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Overseer.");
        alafhaDamageMultiplier = cfg.getFloat("Overseer" + damage, ALAFHA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Overseer.");
        alafhaArmorMultiplier = cfg.getFloat("Overseer" + armor, ALAFHA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Overseer.");
        alafhaKDResistanceMultiplier = cfg.getFloat("Overseer" + kd, ALAFHA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Overseer.");
        alafhaSpawnRate = cfg.getInt("Overseer SpawnWeight", ALAFHA_CATEGORY, alafhaSpawnRate, 0, 100, "Spawn rate for Overseer (This value is ignored if Evolution Phases are enabled, it has its own option).");
        alafhaGriefing = cfg.getBoolean("Overseer Griefing", ALAFHA_CATEGORY, alafhaGriefing, "Set to true if you want its projectiles to destroy blocks.");
        alafhaLoot = cfg.getStringList("Overseer Loot Table", ALAFHA_CATEGORY, alafhaLoot, "Items you want the Overseer to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        alafhaMaxY = cfg.getInt("Overseer Flight Height Limit", ALAFHA_CATEGORY, alafhaMaxY, 0, 256, "Number of blocks it can fly above the ground for Overseer.");
        alafhaSummoningCooldown = cfg.getInt("Overseer summoning Cooldown", ALAFHA_CATEGORY, alafhaSummoningCooldown, 0, 100, "Summoning cooldown in seconds.");
        alafhaTotalActiveMobs = cfg.getInt("Overseer Total Active Mobs", ALAFHA_CATEGORY, alafhaTotalActiveMobs, 0, 100, "Number of total points used in mob spawning for Overseer");
        alafhaLimit = cfg.getInt("Overseer limit", ALAFHA_CATEGORY, alafhaLimit, 0, 10000, "Number of attacks before its cooldown for Overseer.");
        alafhaMobList = cfg.getStringList("Overseer mob List", ALAFHA_CATEGORY, alafhaMobList, "Mob list for Overseer." + Canraspawning);
        alafhaMelee = cfg.getFloat("Overseer Melee", ALAFHA_CATEGORY, (float)alafhaMelee, 0.0f, 1024.0f, "Damage it will do from melee.");
    }

    private static void initnoglaConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(NOGLA_CATEGORY, "Reeker \n Base Health: " + SPAttributes.NOGLA_HEALTH + " \n Base Damage: " + SPAttributes.NOGLA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.NOGLA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.NOGLA_KD_RESISTANCE);
        noglaEnabled = cfg.getBoolean("Primitive Reeker Enabled", NOGLA_CATEGORY, noglaEnabled, "Set to false if you want to disable Primitive Reeker.");
        noglaHealthMultiplier = cfg.getFloat("Primitive Reeker" + health, NOGLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Reeker.");
        noglaDamageMultiplier = cfg.getFloat("Primitive Reeker" + damage, NOGLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Reeker.");
        noglaArmorMultiplier = cfg.getFloat("Primitive Reeker" + armor, NOGLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Reeker.");
        noglaKDResistanceMultiplier = cfg.getFloat("Primitive Reeker" + kd, NOGLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Reeker.");
        noglaSpawnRate = cfg.getInt("Primitive Reeker SpawnWeight", NOGLA_CATEGORY, noglaSpawnRate, 0, 100, "Spawn rate for Primitive Reeker (This value is ignored if Evolution Phases are enabled, it has its own option).");
        noglaLoot = cfg.getStringList("Primitive Reeker Loot Table", NOGLA_CATEGORY, noglaLoot, "Items you want the Primitive Reeker to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        noglaOrbEffects = cfg.getStringList("Primitive Reeker Orb Effects", NOGLA_CATEGORY, noglaOrbEffects, "Orb effects " + orb);
        noglaRicardoVariantEnabled = cfg.getBoolean("Enable Ricardo variant", NOGLA_CATEGORY, noglaRicardoVariantEnabled, "If false, naming a Reeker \"Ricardo\" will not transform it into the Ricardo variant.");
        noglaadaptedhealth = cfg.getFloat("Stage Adapted additional Health", NOGLA_CATEGORY, noglaadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Reeker.");
        noglaadapteddamage = cfg.getFloat("Stage Adapted additional Damage", NOGLA_CATEGORY, noglaadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Reeker.");
        noglaadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", NOGLA_CATEGORY, noglaadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Reeker.");
        noglaadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", NOGLA_CATEGORY, noglaadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Reeker.");
        noglaadaptedloot = cfg.getStringList("Stage Adapted loot Table", NOGLA_CATEGORY, noglaadaptedloot, "Items you want the Adapted Reeker to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        noglaASpawnRate = cfg.getInt("Stage Adapted spawnweight", NOGLA_CATEGORY, noglaASpawnRate, 0, 100, "Spawn rate for Adapted Reeker (This value is ignored if Evolution Phases are enabled, it has its own option).");
        noglaadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", NOGLA_CATEGORY, noglaadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initbutholConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(BUTHOL_CATEGORY, "Flying Carrier \n Base Health: " + SPAttributes.BUTHOL_HEALTH + " \n Base Damage: " + SPAttributes.BUTHOL_DAMAGE + " \n Base Armor: " + SPAttributes.BUTHOL_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.BUTHOL_KD_RESISTANCE);
        butholEnabled = cfg.getBoolean("Flying Carrier Enabled", BUTHOL_CATEGORY, butholEnabled, "Set to false if you want to disable Flying Carrier.");
        butholHealthMultiplier = cfg.getFloat("Flying Carrier" + health, BUTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Flying Carrier.");
        butholDamageMultiplier = cfg.getFloat("Flying Carrier" + damage, BUTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Flying Carrier.");
        butholArmorMultiplier = cfg.getFloat("Flying Carrier" + armor, BUTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Flying Carrier.");
        butholKDResistanceMultiplier = cfg.getFloat("Flying Carrier" + kd, BUTHOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Flying Carrier.");
        butholSpawnRate = cfg.getInt("Flying Carrier SpawnWeight", BUTHOL_CATEGORY, butholSpawnRate, 0, 100, "Spawn rate for Flying Carrier (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ButholGriefing = cfg.getBoolean("Flying Carrier Griefing", BUTHOL_CATEGORY, ButholGriefing, "Set to true if you want the Flying Carrier to destroy blocks on explosion.");
        butholLoot = cfg.getStringList("Flying Carrier Loot Table", BUTHOL_CATEGORY, butholLoot, "Items you want the Flying Carrier to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        butholMaxY = cfg.getInt("Flying Carrier Flight Height Limit", BUTHOL_CATEGORY, butholMaxY, 0, 256, "Number of blocks it can fly above the ground for Flying Carrier.");
        butholMobs = cfg.getStringList("Flying Carrier Mob Table", BUTHOL_CATEGORY, butholMobs, "Mob list for Flying Carrier." + mobTable);
    }

    private static void initmudoConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MUDO_CATEGORY, "Rupter \n Base Health: " + SPAttributes.MUDO_HEALTH + " \n Base Damage: " + SPAttributes.MUDO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MUDO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MUDO_KD_RESISTANCE);
        mudoEnabled = cfg.getBoolean("Rupter Enabled", MUDO_CATEGORY, mudoEnabled, "Set to false if you want to disable Rupter.");
        mudoHealthMultiplier = cfg.getFloat("Rupter" + health, MUDO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Rupter.");
        mudoDamageMultiplier = cfg.getFloat("Rupter" + damage, MUDO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Rupter.");
        mudoArmorMultiplier = cfg.getFloat("Rupter" + armor, MUDO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Rupter.");
        mudoKDResistanceMultiplier = cfg.getFloat("Rupter" + kd, MUDO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Rupter.");
        mudoSpawnRate = cfg.getInt("Rupter SpawnWeight", MUDO_CATEGORY, mudoSpawnRate, 0, 100, "Spawn rate for Rupter (This value is ignored if Evolution Phases are enabled, it has its own option).");
        mudoLoot = cfg.getStringList("Rupter Loot Table", MUDO_CATEGORY, mudoLoot, "Items you want the Rupter to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        mudoAnimalAttacking = cfg.getBoolean("Rupter Passive Mob Attacking", MUDO_CATEGORY, mudoAnimalAttacking, "Set to false if you don't want the Rupter to attack passive mobs.");
        mudoMinDamage = cfg.getFloat("Rupter Minimum Damage", MUDO_CATEGORY, mudoMinDamage, 0.0f, 1024.0f, "Minimum Damage for Rupter.");
        mudoTunnelValue = cfg.getInt("Rupter Tunnel Cost", MUDO_CATEGORY, mudoTunnelValue, 0, 100, "Cost (killcount) of placing a Buglin Tunnel.");
        mudoTunnelPhase = (byte)cfg.getInt("Phase Rupter Tunnel", MUDO_CATEGORY, (int)mudoTunnelPhase, 0, 9, "From this phase on, Rupters will not place Tunnels.");
        mudoMangler = cfg.getInt("Rupter To Mangler", MUDO_CATEGORY, mudoMangler, 0, 1000, "How many kills a Rupter needs to become a Mangler.");
    }

    private static void initnuuhConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(NUUH_CATEGORY, "Mangler \n Base Health: " + SPAttributes.NUUH_HEALTH + " \n Base Damage: " + SPAttributes.NUUH_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.NUUH_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.NUUH_KD_RESISTANCE);
        nuuhEnabled = cfg.getBoolean("Mangler Enabled", NUUH_CATEGORY, nuuhEnabled, "Set to false if you want to disable Mangler.");
        nuuhHealthMultiplier = cfg.getFloat("Mangler" + health, NUUH_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Mangler.");
        nuuhDamageMultiplier = cfg.getFloat("Mangler" + damage, NUUH_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Mangler.");
        nuuhArmorMultiplier = cfg.getFloat("Mangler" + armor, NUUH_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Mangler.");
        nuuhKDResistanceMultiplier = cfg.getFloat("Mangler" + kd, NUUH_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Mangler.");
        nuuhSpawnRate = cfg.getInt("Mangler SpawnWeight", NUUH_CATEGORY, nuuhSpawnRate, 0, 100, "Spawn rate for Mangler (This value is ignored if Evolution Phases are enabled, it has its own option).");
        nuuhLoot = cfg.getStringList("Mangler Loot Table", NUUH_CATEGORY, nuuhLoot, "Items you want the Mangler to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        nuuhAnimalAttacking = cfg.getBoolean("Mangler Passive Mob Attacking", NUUH_CATEGORY, nuuhAnimalAttacking, "Set to false if you don't want the Mangler to attack passive mobs.");
        nuuhMinDamage = cfg.getFloat("Mangler Minimum Damage", NUUH_CATEGORY, nuuhMinDamage, 0.0f, 1024.0f, "Minimum Damage for Mangler.");
    }

    private static void initataConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(ATA_CATEGORY, "Gnat \n Base Health: " + SPAttributes.ATA_HEALTH + " \n Base Damage: " + SPAttributes.ATA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ATA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ATA_KD_RESISTANCE);
        ataEnabled = cfg.getBoolean("Gnat Enabled", ATA_CATEGORY, ataEnabled, "Set to false if you want to disable Gnat.");
        ataHealthMultiplier = cfg.getFloat("Gnat" + health, ATA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Gnat.");
        ataDamageMultiplier = cfg.getFloat("Gnat" + damage, ATA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Gnat.");
        ataArmorMultiplier = cfg.getFloat("Gnat" + armor, ATA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Gnat.");
        ataKDResistanceMultiplier = cfg.getFloat("Gnat" + kd, ATA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Gnat.");
        ataSpawnRate = cfg.getInt("Gnat SpawnWeight", ATA_CATEGORY, ataSpawnRate, 0, 100, "Spawn rate for Gnat (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ataLoot = cfg.getStringList("Gnat Loot Table", ATA_CATEGORY, ataLoot, "Items you want the Gnat to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        ataAnimalAttacking = cfg.getBoolean("Gnat Passive Mob Attacking", ATA_CATEGORY, ataAnimalAttacking, "Set to false if you don't want the Gnat to attack passive mobs.");
        ataMinDamage = cfg.getFloat("Gnat Minimum Damage", ATA_CATEGORY, ataMinDamage, 0.0f, 1024.0f, "Minimum Damage for Gnat.");
    }

    private static void initviinConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(VIIN_CATEGORY, "Lice \n Base Health: " + SPAttributes.VIIN_HEALTH + " \n Base Damage: " + SPAttributes.VIIN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.VIIN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.VIIN_KD_RESISTANCE);
        viinEnabled = cfg.getBoolean("Lice Enabled", VIIN_CATEGORY, viinEnabled, "Set to false if you want to disable Lice.");
        viinHealthMultiplier = cfg.getFloat("Lice" + health, VIIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Lice.");
        viinDamageMultiplier = cfg.getFloat("Lice" + damage, VIIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Lice.");
        viinArmorMultiplier = cfg.getFloat("Lice" + armor, VIIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Lice.");
        viinKDResistanceMultiplier = cfg.getFloat("Lice" + kd, VIIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Lice.");
        viinSpawnRate = cfg.getInt("Lice SpawnWeight", VIIN_CATEGORY, viinSpawnRate, 0, 100, "Spawn rate for Lice (This value is ignored if Evolution Phases are enabled, it has its own option).");
        viinLoot = cfg.getStringList("Lice Loot Table", VIIN_CATEGORY, viinLoot, "Items you want the Lice to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        viinAnimalAttacking = cfg.getBoolean("Lice Passive Mob Attacking", VIIN_CATEGORY, viinAnimalAttacking, "Set to false if you don't want the Lice to attack passive mobs.");
        viinMinDamage = cfg.getFloat("Lice Minimum Damage", VIIN_CATEGORY, viinMinDamage, 0.0f, 1024.0f, "Minimum Damage for Lice.");
    }

    private static void inithostConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HOST_CATEGORY, "Host \n Base Health: " + SPAttributes.HOST_HEALTH + " \n Base Damage: " + SPAttributes.HOST_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HOST_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HOST_KD_RESISTANCE);
        hostEnabled = cfg.getBoolean("Host Enabled", HOST_CATEGORY, hostEnabled, "Set to false if you want to disable Host.");
        hostHealthMultiplier = cfg.getFloat("Host" + health, HOST_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Host.");
        hostDamageMultiplier = cfg.getFloat("Host" + damage, HOST_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Host.");
        hostArmorMultiplier = cfg.getFloat("Host" + armor, HOST_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Host.");
        hostKDResistanceMultiplier = cfg.getFloat("Host" + kd, HOST_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Host.");
        hostSpawnRate = cfg.getInt("Host SpawnWeight", HOST_CATEGORY, hostSpawnRate, 0, 100, "Spawn rate for Host (This value is ignored if Evolution Phases are enabled, it has its own option).");
        hostLoot = cfg.getStringList("Host Loot Table", HOST_CATEGORY, hostLoot, "Items you want the Host to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        hostDamage = cfg.getFloat("Host Bomb Damage", HOST_CATEGORY, hostDamage, 1.0f, 1000.0f, "Damage of its bomb");
        hostSkele = cfg.getInt("Host From Skeletons Value", HOST_CATEGORY, hostSkele, 0, 1000, "Number of kills an Assimilated Human/Villager need in order to become a Host.");
        hostHerd = cfg.getInt("Host To Herd", HOST_CATEGORY, hostHerd, 0, 1000, "How many kills a Host needs to become a Herd.");
    }

    private static void initherdConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HERD_CATEGORY, "Herd \n Base Health: " + SPAttributes.HERD_HEALTH + " \n Base Damage: " + SPAttributes.HERD_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HERD_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HERD_KD_RESISTANCE);
        herdEnabled = cfg.getBoolean("Herd Enabled", HERD_CATEGORY, herdEnabled, "Set to false if you want to disable Herd.");
        herdHealthMultiplier = cfg.getFloat("Herd" + health, HERD_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Herd.");
        herdDamageMultiplier = cfg.getFloat("Herd" + damage, HERD_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Herd.");
        herdArmorMultiplier = cfg.getFloat("Herd" + armor, HERD_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Herd.");
        herdKDResistanceMultiplier = cfg.getFloat("Herd" + kd, HERD_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Herd.");
        herdSpawnRate = cfg.getInt("Herd SpawnWeight", HERD_CATEGORY, herdSpawnRate, 0, 100, "Spawn rate for Herd (This value is ignored if Evolution Phases are enabled, it has its own option).");
        herdLoot = cfg.getStringList("Herd Loot Table", HERD_CATEGORY, herdLoot, "Items you want the Herd to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        herdDamage = cfg.getFloat("Herd Bomb Damage", HERD_CATEGORY, herdDamage, 1.0f, 1000.0f, "Damage of its bomb");
    }

    private static void initthrallConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(THRALL_CATEGORY, "Thrall \n Base Health: " + SPAttributes.THRALL_HEALTH + " \n Base Damage: " + SPAttributes.THRALL_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.THRALL_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.THRALL_KD_RESISTANCE);
        thrallEnabled = cfg.getBoolean("Thrall Enabled", THRALL_CATEGORY, thrallEnabled, "Set to false if you want to disable Thrall.");
        thrallHealthMultiplier = cfg.getFloat("Thrall" + health, THRALL_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Thrall.");
        thrallDamageMultiplier = cfg.getFloat("Thrall" + damage, THRALL_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Thrall.");
        thrallArmorMultiplier = cfg.getFloat("Thrall" + armor, THRALL_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Thrall.");
        thrallKDResistanceMultiplier = cfg.getFloat("Thrall" + kd, THRALL_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Thrall.");
        thrallSpawnRate = cfg.getInt("Thrall SpawnWeight", THRALL_CATEGORY, thrallSpawnRate, 0, 100, "Spawn rate for Thrall (This value is ignored if Evolution Phases are enabled, it has its own option).");
        thrallLoot = cfg.getStringList("Thrall Loot Table", THRALL_CATEGORY, thrallLoot, "Items you want the Thrall to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initinfbearConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(INFBEAR_CATEGORY, "Assimilated Bear \n Base Health: " + SPAttributes.INFBEAR_HEALTH + " \n Base Damage: " + SPAttributes.INFBEAR_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFBEAR_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFBEAR_KD_RESISTANCE);
        infbearEnabled = cfg.getBoolean("Assimilated Bear Enabled", INFBEAR_CATEGORY, infbearEnabled, "Set to false if you want to disable Assimilated Bear.");
        infbearHealthMultiplier = cfg.getFloat("Assimilated Bear" + health, INFBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Bear.");
        infbearDamageMultiplier = cfg.getFloat("Assimilated Bear" + damage, INFBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Bear.");
        infbearArmorMultiplier = cfg.getFloat("Assimilated Bear" + armor, INFBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Bear.");
        infbearKDResistanceMultiplier = cfg.getFloat("Assimilated Bear" + kd, INFBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Bear.");
        infbearSpawnRate = cfg.getInt("Assimilated Bear SpawnWeight", INFBEAR_CATEGORY, infbearSpawnRate, 0, 100, "Spawn rate for Assimilated Bear (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infbearCanSpawnAssimilatedNat = cfg.getInt("Assimilated Bear Needed Assimilation Value", INFBEAR_CATEGORY, infbearCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infbearLoot = cfg.getStringList("Assimilated Bear Loot Table", INFBEAR_CATEGORY, infbearLoot, "Items you want the Assimilated Bear to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initinfendermanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(INFENDERMAN_CATEGORY, "Assimilated Enderman \n Base Health: " + SPAttributes.INFENDERMAN_HEALTH + " \n Base Damage: " + SPAttributes.INFENDERMAN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFENDERMAN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFENDERMAN_KD_RESISTANCE);
        infendermanEnabled = cfg.getBoolean("Assimilated Enderman Enabled", INFENDERMAN_CATEGORY, infendermanEnabled, "Set to false if you want to disable Assimilated Enderman.");
        infendermanHealthMultiplier = cfg.getFloat("Assimilated Enderman" + health, INFENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Enderman.");
        infendermanDamageMultiplier = cfg.getFloat("Assimilated Enderman" + damage, INFENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Enderman.");
        infendermanArmorMultiplier = cfg.getFloat("Assimilated Enderman" + armor, INFENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Enderman.");
        infendermanTeleDist = cfg.getFloat("Assimilated Enderman Minimum Teleport Distance", INFENDERMAN_CATEGORY, 8.0f, 0.01f, 20.0f, "How close the Assimilated Enderman can teleport to targets.");
        infendermanKDResistanceMultiplier = cfg.getFloat("Assimilated Enderman" + kd, INFENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Enderman.");
        infendermanSpawnRate = cfg.getInt("Assimilated Enderman SpawnWeight", INFENDERMAN_CATEGORY, infendermanSpawnRate, 0, 100, "Spawn rate for Assimilated Enderman (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infendermanCanSpawnAssimilatedNat = cfg.getInt("Assimilated Enderman Needed Assimilation Value", INFENDERMAN_CATEGORY, infendermanCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infendermanLoot = cfg.getStringList("Assimilated Enderman Loot Table", INFENDERMAN_CATEGORY, infendermanLoot, "Items you want the Assimilated Enderman to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infendermanteleally = cfg.getBoolean("Assimilated Enderman Teleport Parasites", INFENDERMAN_CATEGORY, infendermanteleally, "Set to false if you don't want the Assimilated Enderman to teleport other parasites.");
        infendermantelefreq = cfg.getInt("Assimilated Enderman Teleport Frequency", INFENDERMAN_CATEGORY, infendermantelefreq, 0, 0x7FFFFFF8, "The lower the number, the more the Assimilated Enderman will teleport.");
        infendermanheadLoot = cfg.getStringList("Assimilated Enderman Head Loot Table", INFENDERMAN_CATEGORY, infendermanheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infendermanHealthHead = cfg.getFloat("Assimilated Enderman Head Health", INFENDERMAN_CATEGORY, infendermanHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infendermanDamageHead = cfg.getFloat("Assimilated Enderman Head Damage", INFENDERMAN_CATEGORY, infendermanDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infendermanheadchance = cfg.getFloat("Assimilated Enderman Head Chance", INFENDERMAN_CATEGORY, infendermanheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
        infendermanallyCool = cfg.getInt("Assimilated Enderman Ally Teleport Cooldown", INFENDERMAN_CATEGORY, infendermanallyCool, 0, 0x7FFFFFF8, "Cooldown in ticks for teleporting allies.");
        infendermansaw = cfg.getInt("Assimilated Enderman First Sighting Teleport Cooldown", INFENDERMAN_CATEGORY, infendermansaw, 0, 0x7FFFFFF8, "Cooldown in ticks to start teleporting when engaging an enemy.");
        infendermanTeleDamage = cfg.getFloat("Assimilated Enderman Ally Teleport Damage", INFENDERMAN_CATEGORY, infendermanTeleDamage, 0.0f, 100.0f, "Damage to teleported allies.");
    }

    private static void initinfhumanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(INFHUMAN_CATEGORY, "Assimilated Human \n Base Health: " + SPAttributes.INFHUMAN_HEALTH + " \n Base Damage: " + SPAttributes.INFHUMAN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFHUMAN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFHUMAN_KD_RESISTANCE);
        infhumanEnabled = cfg.getBoolean("Assimilated Human Enabled", INFHUMAN_CATEGORY, infhumanEnabled, "Set to false if you want to disable Assimilated Human.");
        infhumanHealthMultiplier = cfg.getFloat("Assimilated Human" + health, INFHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Human.");
        infhumanDamageMultiplier = cfg.getFloat("Assimilated Human" + damage, INFHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Human.");
        infhumanArmorMultiplier = cfg.getFloat("Assimilated Human" + armor, INFHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Human.");
        infhumanKDResistanceMultiplier = cfg.getFloat("Assimilated Human" + kd, INFHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Human.");
        infhumanSpawnRate = cfg.getInt("Assimilated Human SpawnWeight", INFHUMAN_CATEGORY, infhumanSpawnRate, 0, 100, "Spawn rate for Assimilated Human (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infhumanCanSpawnAssimilatedNat = cfg.getInt("Assimilated Human Needed Assimilation Value", INFHUMAN_CATEGORY, infhumanCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infhumanLoot = cfg.getStringList("Assimilated Human Loot Table", INFHUMAN_CATEGORY, infhumanLoot, "Items you want the Assimilated Human to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infhumanheadLoot = cfg.getStringList("Assimilated Human Head Loot Table", INFHUMAN_CATEGORY, infhumanheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infhumanHealthHead = cfg.getFloat("Assimilated Human Head Health", INFHUMAN_CATEGORY, infhumanHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infhumanDamageHead = cfg.getFloat("Assimilated Human Head Damage", INFHUMAN_CATEGORY, infhumanDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infhumanheadchance = cfg.getFloat("Assimilated Human Head Chance", INFHUMAN_CATEGORY, infhumanheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfsquidConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFSQUID_CATEGORY, "Assimilated Squid \n Base Health: " + SPAttributes.INFSQUID_HEALTH + " \n Base Damage: " + SPAttributes.INFSQUID_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFSQUID_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFSQUID_KD_RESISTANCE);
        infsquidEnabled = cfg.getBoolean("Assimilated Squid Enabled", INFSQUID_CATEGORY, infsquidEnabled, "Set to false if you want to disable Assimilated Squid.");
        infsquidHealthMultiplier = cfg.getFloat("Assimilated Squid" + health, INFSQUID_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Squid.");
        infsquidDamageMultiplier = cfg.getFloat("Assimilated Squid" + damage, INFSQUID_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Squid.");
        infsquidArmorMultiplier = cfg.getFloat("Assimilated Squid" + armor, INFSQUID_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Squid.");
        infsquidKDResistanceMultiplier = cfg.getFloat("Assimilated Squid" + kd, INFSQUID_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Squid.");
        infsquidSpawnRate = cfg.getInt("Assimilated Squid SpawnWeight", INFSQUID_CATEGORY, infsquidSpawnRate, 0, 100, "Spawn rate for Assimilated Squid (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infsquidCanSpawnAssimilatedNat = cfg.getInt("Assimilated Squid Needed Assimilation Value", INFSQUID_CATEGORY, infsquidCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infsquidLoot = cfg.getStringList("Assimilated Squid Loot Table", INFSQUID_CATEGORY, infsquidLoot, "Items you want the Assimilated Squid to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initinfcowConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFCOW_CATEGORY, "Assimilated Cow \n Base Health: " + SPAttributes.INFCOW_HEALTH + " \n Base Damage: " + SPAttributes.INFCOW_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFCOW_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFCOW_KD_RESISTANCE);
        infcowEnabled = cfg.getBoolean("Assimilated Cow Enabled", INFCOW_CATEGORY, infcowEnabled, "Set to false if you want to disable Assimilated Cow.");
        infcowHealthMultiplier = cfg.getFloat("Assimilated Cow" + health, INFCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Cow.");
        infcowDamageMultiplier = cfg.getFloat("Assimilated Cow" + damage, INFCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Cow.");
        infcowArmorMultiplier = cfg.getFloat("Assimilated Cow" + armor, INFCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Cow.");
        infcowKDResistanceMultiplier = cfg.getFloat("Assimilated Cow" + kd, INFCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Cow.");
        infcowSpawnRate = cfg.getInt("Assimilated Cow SpawnWeight", INFCOW_CATEGORY, infcowSpawnRate, 0, 100, "Spawn rate for Assimilated Cow (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infcowCanSpawnAssimilatedNat = cfg.getInt("Assimilated Cow Needed Assimilation Value", INFCOW_CATEGORY, infcowCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infcowLoot = cfg.getStringList("Assimilated Cow Loot Table", INFCOW_CATEGORY, infcowLoot, "Items you want the Assimilated Cow to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infcowmob = cfg.getString("Assimilated Cow Mobs Inside", INFCOW_CATEGORY, infcowmob, "Mob the Assimilated Cow spawns when killed." + mobTable);
        infcowheadLoot = cfg.getStringList("Assimilated Cow Head Loot Table", INFCOW_CATEGORY, infcowheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infcowHealthHead = cfg.getFloat("Assimilated Cow Head Health", INFCOW_CATEGORY, infcowHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infcowDamageHead = cfg.getFloat("Assimilated Cow Head Damage", INFCOW_CATEGORY, infcowDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infcowheadchance = cfg.getFloat("Assimilated Cow Head Chance", INFCOW_CATEGORY, infcowheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfsheepConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFSHEEP_CATEGORY, "Assimilated Sheep \n Base Health: " + SPAttributes.INFSHEEP_HEALTH + " \n Base Damage: " + SPAttributes.INFSHEEP_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFSHEEP_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFSHEEP_KD_RESISTANCE);
        infsheepEnabled = cfg.getBoolean("Assimilated Sheep Enabled", INFSHEEP_CATEGORY, infsheepEnabled, "Set to false if you want to disable Assimilated Sheep.");
        infsheepHealthMultiplier = cfg.getFloat("Assimilated Sheep" + health, INFSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Sheep.");
        infsheepDamageMultiplier = cfg.getFloat("Assimilated Sheep" + damage, INFSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Sheep.");
        infsheepArmorMultiplier = cfg.getFloat("Assimilated Sheep" + armor, INFSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Sheep.");
        infsheepKDResistanceMultiplier = cfg.getFloat("Assimilated Sheep" + kd, INFSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Sheep.");
        infsheepSpawnRate = cfg.getInt("Assimilated Sheep SpawnWeight", INFSHEEP_CATEGORY, infsheepSpawnRate, 0, 100, "Spawn rate for Assimilated Sheep (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infsheepCanSpawnAssimilatedNat = cfg.getInt("Assimilated Sheep Needed Assimilation Value", INFSHEEP_CATEGORY, infsheepCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infsheepLoot = cfg.getStringList("Assimilated Sheep Loot Table", INFSHEEP_CATEGORY, infsheepLoot, "Items you want the Assimilated Sheep to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infsheepmob = cfg.getString("Assimilated Sheep Mobs Inside", INFSHEEP_CATEGORY, infsheepmob, "Mob the Assimilated Sheep spawns when killed." + mobTable);
        infsheepheadLoot = cfg.getStringList("Assimilated Sheep Head Loot Table", INFSHEEP_CATEGORY, infsheepheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infsheepHealthHead = cfg.getFloat("Assimilated Sheep Head Health", INFSHEEP_CATEGORY, infsheepHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infsheepDamageHead = cfg.getFloat("Assimilated Sheep Head Damage", INFSHEEP_CATEGORY, infsheepDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infsheepheadchance = cfg.getFloat("Assimilated Sheep Head Chance", INFSHEEP_CATEGORY, infsheepheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfwolfConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFWOLF_CATEGORY, "Assimilated Wolf \n Base Health: " + SPAttributes.INFWOLF_HEALTH + " \n Base Damage: " + SPAttributes.INFWOLF_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFWOLF_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFWOLF_KD_RESISTANCE);
        infwolfEnabled = cfg.getBoolean("Assimilated Wolf Enabled", INFWOLF_CATEGORY, infwolfEnabled, "Set to false if you want to disable Assimilated Wolf.");
        infwolfHealthMultiplier = cfg.getFloat("Assimilated Wolf" + health, INFWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Wolf.");
        infwolfDamageMultiplier = cfg.getFloat("Assimilated Wolf" + damage, INFWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Wolf.");
        infwolfArmorMultiplier = cfg.getFloat("Assimilated Wolf" + armor, INFWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Wolf.");
        infwolfKDResistanceMultiplier = cfg.getFloat("Assimilated Wolf" + kd, INFWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Wolf.");
        infwolfSpawnRate = cfg.getInt("Assimilated Wolf SpawnWeight", INFWOLF_CATEGORY, infwolfSpawnRate, 0, 100, "Spawn rate for Assimilated Wolf (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infwolfCanSpawnAssimilatedNat = cfg.getInt("Assimilated Wolf Needed Assimilation Value", INFWOLF_CATEGORY, infwolfCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infwolfLoot = cfg.getStringList("Assimilated Wolf Loot Table", INFWOLF_CATEGORY, infwolfLoot, "Items you want the Assimilated Wolf to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infwolfmob = cfg.getString("Assimilated Wolf Mobs Inside", INFWOLF_CATEGORY, infwolfmob, "Mob the Assimilated Wolf spawns when killed." + mobTable);
        infwolfheadLoot = cfg.getStringList("Assimilated Wolf Head Loot Table", INFWOLF_CATEGORY, infwolfheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infwolfHealthHead = cfg.getFloat("Assimilated Wolf Head Health", INFWOLF_CATEGORY, infwolfHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infwolfDamageHead = cfg.getFloat("Assimilated Wolf Head Damage", INFWOLF_CATEGORY, infwolfDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infwolfheadchance = cfg.getFloat("Assimilated Wolf Head Chance", INFWOLF_CATEGORY, infwolfheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfpigConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFPIG_CATEGORY, "Assimilated Pig \n Base Health: " + SPAttributes.INFPIG_HEALTH + " \n Base Damage: " + SPAttributes.INFPIG_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFPIG_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFPIG_KD_RESISTANCE);
        infpigEnabled = cfg.getBoolean("Assimilated Pig Enabled", INFPIG_CATEGORY, infpigEnabled, "Set to false if you want to disable Assimilated Pig.");
        infpigHealthMultiplier = cfg.getFloat("Assimilated Pig" + health, INFPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Pig.");
        infpigDamageMultiplier = cfg.getFloat("Assimilated Pig" + damage, INFPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Pig.");
        infpigArmorMultiplier = cfg.getFloat("Assimilated Pig" + armor, INFPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Pig.");
        infpigKDResistanceMultiplier = cfg.getFloat("Assimilated Pig" + kd, INFPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Pig.");
        infpigSpawnRate = cfg.getInt("Assimilated Pig SpawnWeight", INFPIG_CATEGORY, infpigSpawnRate, 0, 100, "Spawn rate for Assimilated Pig (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infpigCanSpawnAssimilatedNat = cfg.getInt("Assimilated Pig Needed Assimilation Value", INFPIG_CATEGORY, infpigCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infpigLoot = cfg.getStringList("Assimilated Pig Loot Table", INFPIG_CATEGORY, infpigLoot, "Items you want the Assimilated Pig to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infpigmob = cfg.getString("Assimilated Pig Mobs Inside", INFPIG_CATEGORY, infpigmob, "Mob the Assimilated Pig spawns when killed." + mobTable);
        infpigheadLoot = cfg.getStringList("Assimilated Pig Head Loot Table", INFPIG_CATEGORY, infpigheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infpigHealthHead = cfg.getFloat("Assimilated Pig Head Health", INFPIG_CATEGORY, infpigHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infpigDamageHead = cfg.getFloat("Assimilated Pig Head Damage", INFPIG_CATEGORY, infpigDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infpigheadchance = cfg.getFloat("Assimilated Pig Head Chance", INFPIG_CATEGORY, infpigheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfvillagerConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFVILLAGER_CATEGORY, "Assimilated Villager \n Base Health: " + SPAttributes.INFVILLAGER_HEALTH + " \n Base Damage: " + SPAttributes.INFVILLAGER_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFVILLAGER_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFVILLAGER_KD_RESISTANCE);
        infvillagerEnabled = cfg.getBoolean("Assimilated Villager Enabled", INFVILLAGER_CATEGORY, infvillagerEnabled, "Set to false if you want to disable Assimilated Villager.");
        infvillagerHealthMultiplier = cfg.getFloat("Assimilated Villager" + health, INFVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Villager.");
        infvillagerDamageMultiplier = cfg.getFloat("Assimilated Villager" + damage, INFVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Villager.");
        infvillagerArmorMultiplier = cfg.getFloat("Assimilated Villager" + armor, INFVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Villager.");
        infvillagerKDResistanceMultiplier = cfg.getFloat("Assimilated Villager" + kd, INFVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Villager.");
        infvillagerSpawnRate = cfg.getInt("Assimilated Villager SpawnWeight", INFVILLAGER_CATEGORY, infvillagerSpawnRate, 0, 100, "Spawn rate for Assimilated Villager (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infvillagerCanSpawnAssimilatedNat = cfg.getInt("Assimilated Villager Needed Assimilation Value", INFVILLAGER_CATEGORY, infvillagerCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infvillagerLoot = cfg.getStringList("Assimilated Villager Loot Table", INFVILLAGER_CATEGORY, infvillagerLoot, "Items you want the Assimilated Villager to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infvillagermob = cfg.getString("Assimilated Villager Mobs Inside", INFVILLAGER_CATEGORY, infvillagermob, "Mob the Assimilated Villager spawns when killed." + mobTable);
        infvillagerheadLoot = cfg.getStringList("Assimilated Villager Head Loot Table", INFVILLAGER_CATEGORY, infvillagerheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infvillagerHealthHead = cfg.getFloat("Assimilated Villager Head Health", INFVILLAGER_CATEGORY, infvillagerHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infvillagerDamageHead = cfg.getFloat("Assimilated Villager Head Damage", INFVILLAGER_CATEGORY, infvillagerDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infvillagerheadchance = cfg.getFloat("Assimilated Villager Head Chance", INFVILLAGER_CATEGORY, infvillagerheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfhorseConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFHORSE_CATEGORY, "Assimilated Horse \n Base Health: " + SPAttributes.INFHORSE_HEALTH + " \n Base Damage: " + SPAttributes.INFHORSE_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFHORSE_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFHORSE_KD_RESISTANCE);
        infhorseEnabled = cfg.getBoolean("Assimilated Horse Enabled", INFHORSE_CATEGORY, infhorseEnabled, "Set to false if you want to disable Assimilated Horse.");
        infhorseHealthMultiplier = cfg.getFloat("Assimilated Horse" + health, INFHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Horse.");
        infhorseDamageMultiplier = cfg.getFloat("Assimilated Horse" + damage, INFHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Horse.");
        infhorseArmorMultiplier = cfg.getFloat("Assimilated Horse" + armor, INFHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Horse.");
        infhorseKDResistanceMultiplier = cfg.getFloat("Assimilated Horse" + kd, INFHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Horse.");
        infhorseSpawnRate = cfg.getInt("Assimilated Horse SpawnWeight", INFHORSE_CATEGORY, infhorseSpawnRate, 0, 100, "Spawn rate for Assimilated Horse (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infhorseCanSpawnAssimilatedNat = cfg.getInt("Assimilated Horse Needed Assimilation Value", INFHORSE_CATEGORY, infhorseCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infhorseLoot = cfg.getStringList("Assimilated Horse Loot Table", INFHORSE_CATEGORY, infhorseLoot, "Items you want the Assimilated Horse to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infhorsemob = cfg.getString("Assimilated Horse Mobs Inside", INFHORSE_CATEGORY, infhorsemob, "Mob the Assimilated Horse spawns when killed." + mobTable);
        infhorseExplotionMult = cfg.getFloat("Assimilated Horse Explotion Multiplier", INFHORSE_CATEGORY, infhorseExplotionMult, 1.0f, 100.0f, "Explotion damage will take base Damage and multiply it by this value.");
        infhorseheadLoot = cfg.getStringList("Assimilated Horse Head Loot Table", INFHORSE_CATEGORY, infhorseheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infhorseHealthHead = cfg.getFloat("Assimilated Horse Head Health", INFHORSE_CATEGORY, infhorseHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infhorseDamageHead = cfg.getFloat("Assimilated Horse Head Damage", INFHORSE_CATEGORY, infhorseDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infhorseheadchance = cfg.getFloat("Assimilated Horse Head Chance", INFHORSE_CATEGORY, infhorseheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initinfadventurerConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFADVENTURER_CATEGORY, "Assimilated Adventurer \n Base Health: " + SPAttributes.INFADVENTURER_HEALTH + " \n Base Damage: " + SPAttributes.INFADVENTURER_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFADVENTURER_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFADVENTURER_KD_RESISTANCE);
        infadventurerEnabled = cfg.getBoolean("Assimilated Adventurer Enabled", INFADVENTURER_CATEGORY, infadventurerEnabled, "Set to false if you want to disable Assimilated Adventurer.");
        infadventurerHealthMultiplier = cfg.getFloat("Assimilated Adventurer" + health, INFADVENTURER_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Adventurer.");
        infadventurerDamageMultiplier = cfg.getFloat("Assimilated Adventurer" + damage, INFADVENTURER_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Adventurer.");
        infadventurerArmorMultiplier = cfg.getFloat("Assimilated Adventurer" + armor, INFADVENTURER_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Adventurer.");
        infadventurerKDResistanceMultiplier = cfg.getFloat("Assimilated Adventurer" + kd, INFADVENTURER_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Adventurer.");
        infadventurerSpawnRate = cfg.getInt("Assimilated Adventurer SpawnWeight", INFADVENTURER_CATEGORY, infadventurerSpawnRate, 0, 100, "Spawn rate for Assimilated Adventurer (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infadventurerThrall = cfg.getInt("Assimilated Adventurer To Thrall", INFADVENTURER_CATEGORY, infadventurerThrall, 0, 1000, "How many kills an Assimilated Adventurer needs to become a Thrall.");
        infadventurerLoot = cfg.getStringList("Assimilated Adventurer Loot Table", INFADVENTURER_CATEGORY, infadventurerLoot, "Items you want the Assimilated Adventurer to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infadventurerSpawnBy = cfg.getBoolean("Assimilated Adventurer Spawned By Players", INFADVENTURER_CATEGORY, infadventurerSpawnBy, "Set to false if you want to disable the spawn of Assimilated Adventurers through players.");
        infadventurermob = cfg.getString("Assimilated Adventurer Mobs Inside", INFADVENTURER_CATEGORY, infadventurermob, "Mob the Assimilated Adventurer spawns when killed." + mobTable);
        infadventurerheadLoot = cfg.getStringList("Assimilated Adventurer Head Loot Table", INFADVENTURER_CATEGORY, infadventurerheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infadventurerHealthHead = cfg.getFloat("Assimilated Adventurer Head Health", INFADVENTURER_CATEGORY, infadventurerHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infadventurerDamageHead = cfg.getFloat("Assimilated Adventurer Head Damage", INFADVENTURER_CATEGORY, infadventurerDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
        infadventurerheadchance = cfg.getFloat("Assimilated Adventurer Head Chance", INFADVENTURER_CATEGORY, infadventurerheadchance, 0.0f, 1.0f, "Chance (1 = 100%) to spawn a walking head when killed.");
    }

    private static void initINFDRAGONEConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(INFDRAGONE_CATEGORY, "Assimilated Ender Dragon \n Base Health: " + SPAttributes.INFDRAGONE_HEALTH + " \n Base Damage: " + SPAttributes.INFDRAGONE_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INFDRAGONE_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INFDRAGONE_KD_RESISTANCE);
        infdragoneEnabled = cfg.getBoolean("Assimilated Ender Dragon Enabled", INFDRAGONE_CATEGORY, infdragoneEnabled, "Set to false if you want to disable Assimilated Ender Dragon.");
        infdragoneHealthMultiplier = cfg.getFloat("Assimilated Ender Dragon" + health, INFDRAGONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimilated Ender Dragon.");
        infdragoneDamageMultiplier = cfg.getFloat("Assimilated Ender Dragon" + damage, INFDRAGONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimilated Ender Dragon.");
        infdragoneArmorMultiplier = cfg.getFloat("Assimilated Ender Dragon" + armor, INFDRAGONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimilated Ender Dragon.");
        infdragoneKDResistanceMultiplier = cfg.getFloat("Assimilated Ender Dragon" + kd, INFDRAGONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimilated Ender Dragon.");
        infdragoneSpawnRate = cfg.getInt("Assimilated Ender Dragon SpawnWeight", INFDRAGONE_CATEGORY, infdragoneSpawnRate, 0, 100, "Spawn rate for Assimilated Ender Dragon (This value is ignored if Evolution Phases are enabled, it has its own option).");
        infdragoneCanSpawnAssimilatedNat = cfg.getInt("Assimilated Ender Dragon Needed Assimilation Value", INFDRAGONE_CATEGORY, infdragoneCanSpawnAssimilatedNat, -1, 100, "Total number of assimilations required to spawn naturally.");
        infdragoneLoot = cfg.getStringList("Assimilated Ender Dragon Loot Table", INFDRAGONE_CATEGORY, new String[0], "Items you want the Assimilated Ender Dragon to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infdragoneRangeDamageMultiplier = cfg.getFloat("Assimilated Ender Dragon Range" + damage, INFDRAGONE_CATEGORY, infdragoneRangeDamageMultiplier, 0.01f, 100.0f, "Range damage multiplier for Assimilated Ender Dragon.");
        infdragoneheadLoot = cfg.getStringList("Assimilated Ender Dragon Head Loot Table", INFDRAGONE_CATEGORY, infdragoneheadLoot, "Items you want the Walking Head to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        infdragoneHealthHead = cfg.getFloat("Assimilated Ender Dragon Head Health", INFDRAGONE_CATEGORY, infdragoneHealthHead, 0.01f, 100.0f, "Percentage of health it will have fron the host.");
        infdragoneDamageHead = cfg.getFloat("Assimilated Ender Dragon Head Damage", INFDRAGONE_CATEGORY, infdragoneDamageHead, 0.01f, 100.0f, "Percentage of damage it will have fron the host.");
    }

    private static void initferbearConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERBEAR_CATEGORY, "Feral Bear \n Base Health: " + SPAttributes.FERBEAR_HEALTH + " \n Base Damage: " + SPAttributes.FERBEAR_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERBEAR_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERBEAR_KD_RESISTANCE);
        ferbearEnabled = cfg.getBoolean("Feral Bear Enabled", FERBEAR_CATEGORY, ferbearEnabled, "Set to false if you want to disable Feral Bear.");
        ferbearHealthMultiplier = cfg.getFloat("Feral Bear" + health, FERBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Bear.");
        ferbearDamageMultiplier = cfg.getFloat("Feral Bear" + damage, FERBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Bear.");
        ferbearArmorMultiplier = cfg.getFloat("Feral Bear" + armor, FERBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Bear.");
        ferbearKDResistanceMultiplier = cfg.getFloat("Feral Bear" + kd, FERBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Bear.");
        ferbearSpawnRate = cfg.getInt("Feral Bear SpawnWeight", FERBEAR_CATEGORY, ferbearSpawnRate, 0, 100, "Spawn rate for Feral Bear (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ferbearLoot = cfg.getStringList("Feral Bear Loot Table", FERBEAR_CATEGORY, ferbearLoot, "Items you want the Feral Bear to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initfercowConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERCOW_CATEGORY, "Feral Cow \n Base Health: " + SPAttributes.FERCOW_HEALTH + " \n Base Damage: " + SPAttributes.FERCOW_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERCOW_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERCOW_KD_RESISTANCE);
        fercowEnabled = cfg.getBoolean("Feral Cow Enabled", FERCOW_CATEGORY, fercowEnabled, "Set to false if you want to disable Feral Cow.");
        fercowHealthMultiplier = cfg.getFloat("Feral Cow" + health, FERCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Cow.");
        fercowDamageMultiplier = cfg.getFloat("Feral Cow" + damage, FERCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Cow.");
        fercowArmorMultiplier = cfg.getFloat("Feral Cow" + armor, FERCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Cow.");
        fercowKDResistanceMultiplier = cfg.getFloat("Feral Cow" + kd, FERCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Cow.");
        fercowSpawnRate = cfg.getInt("Feral Cow SpawnWeight", FERCOW_CATEGORY, fercowSpawnRate, 0, 100, "Spawn rate for Feral Cow (This value is ignored if Evolution Phases are enabled, it has its own option).");
        fercowLoot = cfg.getStringList("Feral Cow Loot Table", FERCOW_CATEGORY, fercowLoot, "Items you want the Feral Cow to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initferendermanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERENDERMAN_CATEGORY, "Feral Enderman \n Base Health: " + SPAttributes.FERENDERMAN_HEALTH + " \n Base Damage: " + SPAttributes.FERENDERMAN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERENDERMAN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERENDERMAN_KD_RESISTANCE);
        ferendermanEnabled = cfg.getBoolean("Feral Enderman Enabled", FERENDERMAN_CATEGORY, ferendermanEnabled, "Set to false if you want to disable Feral Enderman.");
        ferendermanHealthMultiplier = cfg.getFloat("Feral Enderman" + health, FERENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Enderman.");
        ferendermanDamageMultiplier = cfg.getFloat("Feral Enderman" + damage, FERENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Enderman.");
        ferendermanArmorMultiplier = cfg.getFloat("Feral Enderman" + armor, FERENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Enderman.");
        ferendermanTeleDist = cfg.getFloat("Feral Enderman Minimum Teleport Distance", FERENDERMAN_CATEGORY, 7.0f, 0.01f, 20.0f, "How close the Feral Endermen can teleport to targets.");
        ferendermanKDResistanceMultiplier = cfg.getFloat("Feral Enderman" + kd, FERENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Enderman.");
        ferendermanSpawnRate = cfg.getInt("Feral Enderman SpawnWeight", FERENDERMAN_CATEGORY, ferendermanSpawnRate, 0, 100, "Spawn rate for Feral Enderman (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ferendermanLoot = cfg.getStringList("Feral Enderman Loot Table", FERENDERMAN_CATEGORY, ferendermanLoot, "Items you want the Feral Enderman to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        feralendermanteleally = cfg.getBoolean("Feral Enderman Teleport Parasites", FERENDERMAN_CATEGORY, feralendermanteleally, "Set to false if you don't want the Feral Enderman to teleport other parasites.");
        feralendermantelefreq = cfg.getInt("Feral Enderman Teleport Frequency", FERENDERMAN_CATEGORY, feralendermantelefreq, 0, 0x7FFFFFF8, "The lower the number, the more the Feral Enderman will teleport.");
        feralendermanallyCool = cfg.getInt("Feral Enderman Ally Teleport Cooldown", FERENDERMAN_CATEGORY, feralendermanallyCool, 0, 0x7FFFFFF8, "Cooldown in ticks for teleporting allies.");
        feralendermansaw = cfg.getInt("Feral Enderman First Sighting Teleport Cooldown", FERENDERMAN_CATEGORY, feralendermansaw, 0, 0x7FFFFFF8, "Cooldown in ticks to start teleporting when engaging an enemy.");
        feralendermanTeleDamage = cfg.getFloat("Feral Enderman Ally Teleport Damage", FERENDERMAN_CATEGORY, feralendermanTeleDamage, 0.0f, 100.0f, "Damage to teleported allies.");
    }

    private static void initferhorseConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERHORSE_CATEGORY, "Feral Horse \n Base Health: " + SPAttributes.FERHORSE_HEALTH + " \n Base Damage: " + SPAttributes.FERHORSE_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERHORSE_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERHORSE_KD_RESISTANCE);
        ferhorseEnabled = cfg.getBoolean("Feral Horse Enabled", FERHORSE_CATEGORY, ferhorseEnabled, "Set to false if you want to disable Feral Horse.");
        ferhorseHealthMultiplier = cfg.getFloat("Feral Horse" + health, FERHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Horse.");
        ferhorseDamageMultiplier = cfg.getFloat("Feral Horse" + damage, FERHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Horse.");
        ferhorseArmorMultiplier = cfg.getFloat("Feral Horse" + armor, FERHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Horse.");
        ferhorseKDResistanceMultiplier = cfg.getFloat("Feral Horse" + kd, FERHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Horse.");
        ferhorseSpawnRate = cfg.getInt("Feral Horse SpawnWeight", FERHORSE_CATEGORY, ferhorseSpawnRate, 0, 100, "Spawn rate for Feral Horse (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ferhorseLoot = cfg.getStringList("Feral Horse Loot Table", FERHORSE_CATEGORY, ferhorseLoot, "Items you want the Feral Horse to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initferhumanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERHUMAN_CATEGORY, "Feral Human \n Base Health: " + SPAttributes.FERHUMAN_HEALTH + " \n Base Damage: " + SPAttributes.FERHUMAN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERHUMAN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERHUMAN_KD_RESISTANCE);
        ferhumanEnabled = cfg.getBoolean("Feral Human Enabled", FERHUMAN_CATEGORY, ferhumanEnabled, "Set to false if you want to disable Feral Human.");
        ferhumanHealthMultiplier = cfg.getFloat("Feral Human" + health, FERHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Human.");
        ferhumanDamageMultiplier = cfg.getFloat("Feral Human" + damage, FERHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Human.");
        ferhumanArmorMultiplier = cfg.getFloat("Feral Human" + armor, FERHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Human.");
        ferhumanKDResistanceMultiplier = cfg.getFloat("Feral Human" + kd, FERHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Human.");
        ferhumanSpawnRate = cfg.getInt("Feral Human SpawnWeight", FERHUMAN_CATEGORY, ferhumanSpawnRate, 0, 100, "Spawn rate for Feral Human (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ferhumanLoot = cfg.getStringList("Feral Human Loot Table", FERHUMAN_CATEGORY, ferhumanLoot, "Items you want the Feral Human to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initferpigConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERPIG_CATEGORY, "Feral Pig \n Base Health: " + SPAttributes.FERPIG_HEALTH + " \n Base Damage: " + SPAttributes.FERPIG_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERPIG_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERPIG_KD_RESISTANCE);
        ferpigEnabled = cfg.getBoolean("Feral Pig Enabled", FERPIG_CATEGORY, ferpigEnabled, "Set to false if you want to disable Feral Pig.");
        ferpigHealthMultiplier = cfg.getFloat("Feral Pig" + health, FERPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Pig.");
        ferpigDamageMultiplier = cfg.getFloat("Feral Pig" + damage, FERPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Pig.");
        ferpigArmorMultiplier = cfg.getFloat("Feral Pig" + armor, FERPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Pig.");
        ferpigKDResistanceMultiplier = cfg.getFloat("Feral Pig" + kd, FERPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Pig.");
        ferpigSpawnRate = cfg.getInt("Feral Pig SpawnWeight", FERPIG_CATEGORY, ferpigSpawnRate, 0, 100, "Spawn rate for Feral Pig (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ferpigLoot = cfg.getStringList("Feral Pig Loot Table", FERPIG_CATEGORY, ferpigLoot, "Items you want the Feral Pig to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initfersheepConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERSHEEP_CATEGORY, "Feral Sheep \n Base Health: " + SPAttributes.FERSHEEP_HEALTH + " \n Base Damage: " + SPAttributes.FERSHEEP_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERSHEEP_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERSHEEP_KD_RESISTANCE);
        fersheepEnabled = cfg.getBoolean("Feral Sheep Enabled", FERSHEEP_CATEGORY, fersheepEnabled, "Set to false if you want to disable Feral Sheep.");
        fersheepHealthMultiplier = cfg.getFloat("Feral Sheep" + health, FERSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Sheep.");
        fersheepDamageMultiplier = cfg.getFloat("Feral Sheep" + damage, FERSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Sheep.");
        fersheepArmorMultiplier = cfg.getFloat("Feral Sheep" + armor, FERSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Sheep.");
        fersheepKDResistanceMultiplier = cfg.getFloat("Feral Sheep" + kd, FERSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Sheep.");
        fersheepSpawnRate = cfg.getInt("Feral Sheep SpawnWeight", FERSHEEP_CATEGORY, fersheepSpawnRate, 0, 100, "Spawn rate for Feral Sheep (This value is ignored if Evolution Phases are enabled, it has its own option).");
        fersheepLoot = cfg.getStringList("Feral Sheep Loot Table", FERSHEEP_CATEGORY, fersheepLoot, "Items you want the Feral Sheep to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initfervillagerConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERVILLAGER_CATEGORY, "Feral Villager \n Base Health: " + SPAttributes.FERVILLAGER_HEALTH + " \n Base Damage: " + SPAttributes.FERVILLAGER_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERVILLAGER_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERVILLAGER_KD_RESISTANCE);
        fervillagerEnabled = cfg.getBoolean("Feral Villager Enabled", FERVILLAGER_CATEGORY, fervillagerEnabled, "Set to false if you want to disable Feral Villager.");
        fervillagerHealthMultiplier = cfg.getFloat("Feral Villager" + health, FERVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Villager.");
        fervillagerDamageMultiplier = cfg.getFloat("Feral Villager" + damage, FERVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Villager.");
        fervillagerArmorMultiplier = cfg.getFloat("Feral Villager" + armor, FERVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Villager.");
        fervillagerKDResistanceMultiplier = cfg.getFloat("Feral Villager" + kd, FERVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Villager.");
        fervillagerSpawnRate = cfg.getInt("Feral Villager SpawnWeight", FERVILLAGER_CATEGORY, fervillagerSpawnRate, 0, 100, "Spawn rate for Feral Villager (This value is ignored if Evolution Phases are enabled, it has its own option).");
        fervillagerLoot = cfg.getStringList("Feral Villager Loot Table", FERVILLAGER_CATEGORY, fervillagerLoot, "Items you want the Feral Villager to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initferwolfConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FERWOLF_CATEGORY, "Feral Wolf \n Base Health: " + SPAttributes.FERWOLF_HEALTH + " \n Base Damage: " + SPAttributes.FERWOLF_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FERWOLF_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FERWOLF_KD_RESISTANCE);
        ferwolfEnabled = cfg.getBoolean("Feral Wolf Enabled", FERWOLF_CATEGORY, ferwolfEnabled, "Set to false if you want to disable Feral Wolf.");
        ferwolfHealthMultiplier = cfg.getFloat("Feral Wolf" + health, FERWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Feral Wolf.");
        ferwolfDamageMultiplier = cfg.getFloat("Feral Wolf" + damage, FERWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Feral Wolf.");
        ferwolfArmorMultiplier = cfg.getFloat("Feral Wolf" + armor, FERWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Feral Wolf.");
        ferwolfKDResistanceMultiplier = cfg.getFloat("Feral Wolf" + kd, FERWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Feral Wolf.");
        ferwolfSpawnRate = cfg.getInt("Feral Wolf SpawnWeight", FERWOLF_CATEGORY, ferwolfSpawnRate, 0, 100, "Spawn rate for Feral Wolf (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ferwolfLoot = cfg.getStringList("Feral Wolf Loot Table", FERWOLF_CATEGORY, ferwolfLoot, "Items you want the Feral Wolf to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarbearConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARBEAR_CATEGORY, "Assimara Bear \n Base Health: " + SPAttributes.MARBEAR_HEALTH + " \n Base Damage: " + SPAttributes.MARBEAR_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARBEAR_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARBEAR_KD_RESISTANCE);
        marbearEnabled = cfg.getBoolean("Assimara Bear Enabled", MARBEAR_CATEGORY, marbearEnabled, "Set to false if you want to disable Assimara Bear.");
        marbearHealthMultiplier = cfg.getFloat("Assimara Bear" + health, MARBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Bear.");
        marbearDamageMultiplier = cfg.getFloat("Assimara Bear" + damage, MARBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Bear.");
        marbearArmorMultiplier = cfg.getFloat("Assimara Bear" + armor, MARBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Bear.");
        marbearKDResistanceMultiplier = cfg.getFloat("Assimara Bear" + kd, MARBEAR_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Bear.");
        marbearSpawnRate = cfg.getInt("Assimara Bear SpawnWeight", MARBEAR_CATEGORY, marbearSpawnRate, 0, 100, "Spawn rate for Assimara Bear (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marbearLoot = cfg.getStringList("Assimara Bear Loot Table", MARBEAR_CATEGORY, marbearLoot, "Items you want the Assimara Bear to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarcowConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARCOW_CATEGORY, "Assimara Cow \n Base Health: " + SPAttributes.MARCOW_HEALTH + " \n Base Damage: " + SPAttributes.MARCOW_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARCOW_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARCOW_KD_RESISTANCE);
        marcowEnabled = cfg.getBoolean("Assimara Cow Enabled", MARCOW_CATEGORY, marcowEnabled, "Set to false if you want to disable Assimara Cow.");
        marcowHealthMultiplier = cfg.getFloat("Assimara Cow" + health, MARCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Cow.");
        marcowDamageMultiplier = cfg.getFloat("Assimara Cow" + damage, MARCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Cow.");
        marcowArmorMultiplier = cfg.getFloat("Assimara Cow" + armor, MARCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Cow.");
        marcowKDResistanceMultiplier = cfg.getFloat("Assimara Cow" + kd, MARCOW_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Cow.");
        marcowSpawnRate = cfg.getInt("Assimara Cow SpawnWeight", MARCOW_CATEGORY, marcowSpawnRate, 0, 100, "Spawn rate for Assimara Cow (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marcowLoot = cfg.getStringList("Assimara Cow Loot Table", MARCOW_CATEGORY, marcowLoot, "Items you want the Assimara Cow to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarendermanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARENDERMAN_CATEGORY, "Assimara Enderman \n Base Health: " + SPAttributes.MARENDERMAN_HEALTH + " \n Base Damage: " + SPAttributes.MARENDERMAN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARENDERMAN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARENDERMAN_KD_RESISTANCE);
        marendermanEnabled = cfg.getBoolean("Assimara Enderman Enabled", MARENDERMAN_CATEGORY, marendermanEnabled, "Set to false if you want to disable Assimara Enderman.");
        marendermanHealthMultiplier = cfg.getFloat("Assimara Enderman" + health, MARENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Enderman.");
        marendermanDamageMultiplier = cfg.getFloat("Assimara Enderman" + damage, MARENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Enderman.");
        marendermanArmorMultiplier = cfg.getFloat("Assimara Enderman" + armor, MARENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Enderman.");
        marendermanKDResistanceMultiplier = cfg.getFloat("Assimara Enderman" + kd, MARENDERMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Enderman.");
        marendermanSpawnRate = cfg.getInt("Assimara Enderman SpawnWeight", MARENDERMAN_CATEGORY, marendermanSpawnRate, 0, 100, "Spawn rate for Assimara Enderman (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marendermanLoot = cfg.getStringList("Assimara Enderman Loot Table", MARENDERMAN_CATEGORY, marendermanLoot, "Items you want the Assimara Enderman to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        maralendermanteleally = cfg.getBoolean("Assimara Enderman Teleport Parasites", MARENDERMAN_CATEGORY, maralendermanteleally, "Set to false if you don't want the Assimara Enderman to teleport other parasites.");
        maralendermantelefreq = cfg.getInt("Assimara Enderman Teleport Frequency", MARENDERMAN_CATEGORY, maralendermantelefreq, 0, 0x7FFFFFF8, "The lower the number, the more the Assimara Enderman will teleport.");
        maralendermanallyCool = cfg.getInt("Assimara Enderman Ally Teleport Cooldown", MARENDERMAN_CATEGORY, maralendermanallyCool, 0, 0x7FFFFFF8, "Cooldown in ticks for teleporting allies.");
        maralendermansaw = cfg.getInt("Assimara Enderman First Sighting Teleport Cooldown", MARENDERMAN_CATEGORY, maralendermansaw, 0, 0x7FFFFFF8, "Cooldown in ticks to start teleporting when engaging an enemy.");
        maralendermanTeleDamage = cfg.getFloat("Assimara Enderman Ally Teleport Damage", MARENDERMAN_CATEGORY, maralendermanTeleDamage, 0.0f, 100.0f, "Damage to teleported allies.");
    }

    private static void initmarhorseConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARHORSE_CATEGORY, "Assimara Horse \n Base Health: " + SPAttributes.MARHORSE_HEALTH + " \n Base Damage: " + SPAttributes.MARHORSE_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARHORSE_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARHORSE_KD_RESISTANCE);
        marhorseEnabled = cfg.getBoolean("Assimara Horse Enabled", MARHORSE_CATEGORY, marhorseEnabled, "Set to false if you want to disable Assimara Horse.");
        marhorseHealthMultiplier = cfg.getFloat("Assimara Horse" + health, MARHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Horse.");
        marhorseDamageMultiplier = cfg.getFloat("Assimara Horse" + damage, MARHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Horse.");
        marhorseArmorMultiplier = cfg.getFloat("Assimara Horse" + armor, MARHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Horse.");
        marhorseKDResistanceMultiplier = cfg.getFloat("Assimara Horse" + kd, MARHORSE_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Horse.");
        marhorseSpawnRate = cfg.getInt("Assimara Horse SpawnWeight", MARHORSE_CATEGORY, marhorseSpawnRate, 0, 100, "Spawn rate for Assimara Horse (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marhorseLoot = cfg.getStringList("Assimara Horse Loot Table", MARHORSE_CATEGORY, marhorseLoot, "Items you want the Assimara Horse to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarhumanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARHUMAN_CATEGORY, "Assimara Human \n Base Health: " + SPAttributes.MARHUMAN_HEALTH + " \n Base Damage: " + SPAttributes.MARHUMAN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARHUMAN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARHUMAN_KD_RESISTANCE);
        marhumanEnabled = cfg.getBoolean("Assimara Human Enabled", MARHUMAN_CATEGORY, marhumanEnabled, "Set to false if you want to disable Assimara Human.");
        marhumanHealthMultiplier = cfg.getFloat("Assimara Human" + health, MARHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Human.");
        marhumanDamageMultiplier = cfg.getFloat("Assimara Human" + damage, MARHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Human.");
        marhumanArmorMultiplier = cfg.getFloat("Assimara Human" + armor, MARHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Human.");
        marhumanKDResistanceMultiplier = cfg.getFloat("Assimara Human" + kd, MARHUMAN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Human.");
        marhumanSpawnRate = cfg.getInt("Assimara Human SpawnWeight", MARHUMAN_CATEGORY, marhumanSpawnRate, 0, 100, "Spawn rate for Assimara Human (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marhumanLoot = cfg.getStringList("Assimara Human Loot Table", MARHUMAN_CATEGORY, marhumanLoot, "Items you want the Assimara Human to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarpigConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARPIG_CATEGORY, "Assimara Pig \n Base Health: " + SPAttributes.MARPIG_HEALTH + " \n Base Damage: " + SPAttributes.MARPIG_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARPIG_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARPIG_KD_RESISTANCE);
        marpigEnabled = cfg.getBoolean("Assimara Pig Enabled", MARPIG_CATEGORY, marpigEnabled, "Set to false if you want to disable Assimara Pig.");
        marpigHealthMultiplier = cfg.getFloat("Assimara Pig" + health, MARPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Pig.");
        marpigDamageMultiplier = cfg.getFloat("Assimara Pig" + damage, MARPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Pig.");
        marpigArmorMultiplier = cfg.getFloat("Assimara Pig" + armor, MARPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Pig.");
        marpigKDResistanceMultiplier = cfg.getFloat("Assimara Pig" + kd, MARPIG_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Pig.");
        marpigSpawnRate = cfg.getInt("Assimara Pig SpawnWeight", MARPIG_CATEGORY, marpigSpawnRate, 0, 100, "Spawn rate for Assimara Pig (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marpigLoot = cfg.getStringList("Assimara Pig Loot Table", MARPIG_CATEGORY, marpigLoot, "Items you want the Assimara Pig to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarsheepConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARSHEEP_CATEGORY, "Assimara Sheep \n Base Health: " + SPAttributes.MARSHEEP_HEALTH + " \n Base Damage: " + SPAttributes.MARSHEEP_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARSHEEP_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARSHEEP_KD_RESISTANCE);
        marsheepEnabled = cfg.getBoolean("Assimara Sheep Enabled", MARSHEEP_CATEGORY, marsheepEnabled, "Set to false if you want to disable Assimara Sheep.");
        marsheepHealthMultiplier = cfg.getFloat("Assimara Sheep" + health, MARSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Sheep.");
        marsheepDamageMultiplier = cfg.getFloat("Assimara Sheep" + damage, MARSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Sheep.");
        marsheepArmorMultiplier = cfg.getFloat("Assimara Sheep" + armor, MARSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Sheep.");
        marsheepKDResistanceMultiplier = cfg.getFloat("Assimara Sheep" + kd, MARSHEEP_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Sheep.");
        marsheepSpawnRate = cfg.getInt("Assimara Sheep SpawnWeight", MARSHEEP_CATEGORY, marsheepSpawnRate, 0, 100, "Spawn rate for Assimara Sheep (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marsheepLoot = cfg.getStringList("Assimara Sheep Loot Table", MARSHEEP_CATEGORY, marsheepLoot, "Items you want the Assimara Sheep to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarvillagerConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARVILLAGER_CATEGORY, "Assimara Villager \n Base Health: " + SPAttributes.MARVILLAGER_HEALTH + " \n Base Damage: " + SPAttributes.MARVILLAGER_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARVILLAGER_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARVILLAGER_KD_RESISTANCE);
        marvillagerEnabled = cfg.getBoolean("Assimara Villager Enabled", MARVILLAGER_CATEGORY, marvillagerEnabled, "Set to false if you want to disable Assimara Villager.");
        marvillagerHealthMultiplier = cfg.getFloat("Assimara Villager" + health, MARVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Villager.");
        marvillagerDamageMultiplier = cfg.getFloat("Assimara Villager" + damage, MARVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Villager.");
        marvillagerArmorMultiplier = cfg.getFloat("Assimara Villager" + armor, MARVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Villager.");
        marvillagerKDResistanceMultiplier = cfg.getFloat("Assimara Villager" + kd, MARVILLAGER_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Villager.");
        marvillagerSpawnRate = cfg.getInt("Assimara Villager SpawnWeight", MARVILLAGER_CATEGORY, marvillagerSpawnRate, 0, 100, "Spawn rate for Assimara Villager (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marvillagerLoot = cfg.getStringList("Assimara Villager Loot Table", MARVILLAGER_CATEGORY, marvillagerLoot, "Items you want the Assimara Villager to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initmarwolfConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(MARWOLF_CATEGORY, "Assimara Wolf \n Base Health: " + SPAttributes.MARWOLF_HEALTH + " \n Base Damage: " + SPAttributes.MARWOLF_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.MARWOLF_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.MARWOLF_KD_RESISTANCE);
        marwolfEnabled = cfg.getBoolean("Assimara Wolf Enabled", MARWOLF_CATEGORY, marwolfEnabled, "Set to false if you want to disable Assimara Wolf.");
        marwolfHealthMultiplier = cfg.getFloat("Assimara Wolf" + health, MARWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Assimara Wolf.");
        marwolfDamageMultiplier = cfg.getFloat("Assimara Wolf" + damage, MARWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Assimara Wolf.");
        marwolfArmorMultiplier = cfg.getFloat("Assimara Wolf" + armor, MARWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Assimara Wolf.");
        marwolfKDResistanceMultiplier = cfg.getFloat("Assimara Wolf" + kd, MARWOLF_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Assimara Wolf.");
        marwolfSpawnRate = cfg.getInt("Assimara Wolf SpawnWeight", MARWOLF_CATEGORY, marwolfSpawnRate, 0, 100, "Spawn rate for Assimara Wolf (This value is ignored if Evolution Phases are enabled, it has its own option).");
        marwolfLoot = cfg.getStringList("Assimara Wolf Loot Table", MARWOLF_CATEGORY, marwolfLoot, "Items you want the Assimara Wolf to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void inithiblazeConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HIBLAZE_CATEGORY, "Hijacked Blaze \n Base Health: " + SPAttributes.HIBLAZE_HEALTH + " \n Base Damage: " + SPAttributes.HIBLAZE_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HIBLAZE_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HIBLAZE_KD_RESISTANCE);
        hiblazeEnabled = cfg.getBoolean("Hijacked Blaze Enabled", HIBLAZE_CATEGORY, hiblazeEnabled, "Set to false if you want to disable Hijacked Blaze.");
        hiblazeHealthMultiplier = cfg.getFloat("Hijacked Blaze" + health, HIBLAZE_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Hijacked Blaze.");
        hiblazeDamageMultiplier = cfg.getFloat("Hijacked Blaze" + damage, HIBLAZE_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Hijacked Blaze.");
        hiblazeArmorMultiplier = cfg.getFloat("Hijacked Blaze" + armor, HIBLAZE_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Hijacked Blaze.");
        hiblazeKDResistanceMultiplier = cfg.getFloat("Hijacked Blaze" + kd, HIBLAZE_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Hijacked Blaze.");
        hiblazeSpawnRate = cfg.getInt("Hijacked Blaze SpawnWeight", HIBLAZE_CATEGORY, hiblazeSpawnRate, 0, 100, "Spawn rate for Hijacked Blaze (This value is ignored if Evolution Phases are enabled, it has its own option).");
        hiblazeLoot = cfg.getStringList("Hijacked Blaze Loot Table", HIBLAZE_CATEGORY, hiblazeLoot, "Items you want the Hijacked Blaze to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void inithigolemConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HIGOLEM_CATEGORY, "Hijacked Golem \n Base Health: " + SPAttributes.HIGOLEM_HEALTH + " \n Base Damage: " + SPAttributes.HIGOLEM_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HIGOLEM_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HIGOLEM_KD_RESISTANCE);
        higolemEnabled = cfg.getBoolean("Hijacked Golem Enabled", HIGOLEM_CATEGORY, higolemEnabled, "Set to false if you want to disable Hijacked Golem.");
        higolemHealthMultiplier = cfg.getFloat("Hijacked Golem" + health, HIGOLEM_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Hijacked Golem.");
        higolemDamageMultiplier = cfg.getFloat("Hijacked Golem" + damage, HIGOLEM_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Hijacked Golem.");
        higolemArmorMultiplier = cfg.getFloat("Hijacked Golem" + armor, HIGOLEM_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Hijacked Golem.");
        higolemKDResistanceMultiplier = cfg.getFloat("Hijacked Golem" + kd, HIGOLEM_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Hijacked Golem.");
        higolemSpawnRate = cfg.getInt("Hijacked Golem SpawnWeight", HIGOLEM_CATEGORY, higolemSpawnRate, 0, 100, "Spawn rate for Hijacked Golem (This value is ignored if Evolution Phases are enabled, it has its own option).");
        higolemLoot = cfg.getStringList("Hijacked Golem Loot Table", HIGOLEM_CATEGORY, higolemLoot, "Items you want the Hijacked Golem to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        higolemCanSpawnAssimilatedNat = cfg.getInt("Hijacked Golem Needed Assimilation Value", HIGOLEM_CATEGORY, higolemCanSpawnAssimilatedNat, 0, 100, "Total number of hijackings required to spawn naturally.");
    }

    private static void inithiskeletonConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HISKELETON_CATEGORY, "Hijacked Skeleton \n Base Health: " + SPAttributes.HISKELETON_HEALTH + " \n Base Damage: " + SPAttributes.HISKELETON_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HISKELETON_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HISKELETON_KD_RESISTANCE);
        hiskeletonEnabled = cfg.getBoolean("Hijacked Skeleton Enabled", HISKELETON_CATEGORY, hiskeletonEnabled, "Set to false if you want to disable Hijacked Skeleton.");
        hiskeletonHealthMultiplier = cfg.getFloat("Hijacked Skeleton" + health, HISKELETON_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Hijacked Skeleton.");
        hiskeletonDamageMultiplier = cfg.getFloat("Hijacked Skeleton" + damage, HISKELETON_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Hijacked Skeleton.");
        hiskeletonArmorMultiplier = cfg.getFloat("Hijacked Skeleton" + armor, HISKELETON_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Hijacked Skeleton.");
        hiskeletonKDResistanceMultiplier = cfg.getFloat("Hijacked Skeleton" + kd, HISKELETON_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Hijacked Skeleton.");
        hiskeletonSpawnRate = cfg.getInt("Hijacked Skeleton SpawnWeight", HISKELETON_CATEGORY, hiskeletonSpawnRate, 0, 100, "Spawn rate for Hijacked Skeleton (This value is ignored if Evolution Phases are enabled, it has its own option).");
        hiskeletonLoot = cfg.getStringList("Hijacked Skeleton Loot Table", HISKELETON_CATEGORY, hiskeletonLoot, "Items you want the Hijacked Skeleton to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initzetmoConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        String eff = " Ex. \"0;1;minecraft:fire_resistance\" Where: \n \"0\" potion duration in seconds, \n \"1\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself. \n";
        cfg.addCustomCategoryComment(ZETMO_CATEGORY, "Bolster \n Base Health: " + SPAttributes.ZETMO_HEALTH + " \n Base Damage: " + SPAttributes.ZETMO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ZETMO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ZETMO_KD_RESISTANCE);
        zetmoEnabled = cfg.getBoolean("Primitive Bolster Enabled", ZETMO_CATEGORY, zetmoEnabled, "Set to false if you want to disable Primitive Bolster.");
        zetmoHealthMultiplier = cfg.getFloat("Primitive Bolster" + health, ZETMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Bolster.");
        zetmoDamageMultiplier = cfg.getFloat("Primitive Bolster" + damage, ZETMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Bolster.");
        zetmoArmorMultiplier = cfg.getFloat("Primitive Bolster" + armor, ZETMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Bolster.");
        zetmoKDResistanceMultiplier = cfg.getFloat("Primitive Bolster" + kd, ZETMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Bolster.");
        zetmoSpawnRate = cfg.getInt("Primitive Bolster SpawnWeight", ZETMO_CATEGORY, zetmoSpawnRate, 0, 100, "Spawn rate for Primitive Bolster (This value is ignored if Evolution Phases are enabled, it has its own option).");
        zetmoLoot = cfg.getStringList("Primitive Bolster Loot Table", ZETMO_CATEGORY, zetmoLoot, "Items you want the Primitive Bolster to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        zetmoCD = cfg.getInt("Primitive Bolster Buffs Cooldown", ZETMO_CATEGORY, zetmoCD, 0, 100, "Cooldown (in seconds) to buff parasites for Primitive Bolster.");
        zetmoRange = cfg.getInt("Primitive Bolster Buffs Range", ZETMO_CATEGORY, zetmoRange, 0, 100, "Range to buff parasites for Primitive Bolster.");
        zetmoOrbEffects = cfg.getStringList("Primitive Bolster Orb Effects", ZETMO_CATEGORY, zetmoOrbEffects, "Orb effects " + orb);
        zetmoEffects = cfg.getStringList("Primitive Bolster Effects", ZETMO_CATEGORY, zetmoEffects, "Potion effects that will give the Bolster to nearby parasites " + eff);
        zetmoadaptedhealth = cfg.getFloat("Stage Adapted additional Health", ZETMO_CATEGORY, zetmoadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Bolster.");
        zetmoadapteddamage = cfg.getFloat("Stage Adapted additional Damage", ZETMO_CATEGORY, zetmoadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Bolster.");
        zetmoadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", ZETMO_CATEGORY, zetmoadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Bolster.");
        zetmoadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", ZETMO_CATEGORY, zetmoadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Bolster.");
        zetmoadaptedloot = cfg.getStringList("Stage Adapted loot Table", ZETMO_CATEGORY, zetmoadaptedloot, "Items you want the Adapted Bolster to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        zetmoadaptedcd = cfg.getInt("Stage Adapted buffs cooldown", ZETMO_CATEGORY, zetmoadaptedcd, 0, 100, "Cooldown (in seconds) to buff parasites for Adapted Bolster.");
        zetmoadaptedrange = cfg.getInt("Stage Adapted buffs range", ZETMO_CATEGORY, zetmoadaptedrange, 0, 100, "Range to buff parasites for Adapted Bolster.");
        zetmoASpawnRate = cfg.getInt("Stage Adapted spawnweight", ZETMO_CATEGORY, zetmoASpawnRate, 0, 100, "Spawn rate for Adapted Bolster (This value is ignored if Evolution Phases are enabled, it has its own option).");
        zetmoadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", ZETMO_CATEGORY, zetmoadaptedOrbEffects, "Orb effects " + orb);
        zetmoadaptedeffects = cfg.getStringList("Stage Adapted Effects", ZETMO_CATEGORY, zetmoadaptedeffects, "Potion effects that will give the Bolster to nearby parasites " + eff);
    }

    private static void initikiConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(IKI_CATEGORY, "Vermin \n Base Health: " + SPAttributes.IKI_HEALTH + " \n Base Damage: " + SPAttributes.IKI_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.IKI_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.IKI_KD_RESISTANCE);
        ikiEnabled = cfg.getBoolean("Primitive Vermin Enabled", IKI_CATEGORY, ikiEnabled, "Set to false if you want to disable Primitive Vermin.");
        ikiHealthMultiplier = cfg.getFloat("Primitive Vermin" + health, IKI_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Vermin.");
        ikiDamageMultiplier = cfg.getFloat("Primitive Vermin" + damage, IKI_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Vermin.");
        ikiArmorMultiplier = cfg.getFloat("Primitive Vermin" + armor, IKI_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Vermin.");
        ikiKDResistanceMultiplier = cfg.getFloat("Primitive Vermin" + kd, IKI_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Vermin.");
        ikiSpawnRate = cfg.getInt("Primitive Vermin SpawnWeight", IKI_CATEGORY, ikiSpawnRate, 0, 100, "Spawn rate for Primitive Vermin (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ikiLoot = cfg.getStringList("Primitive Vermin Loot Table", IKI_CATEGORY, ikiLoot, "Items you want the Primitive Vermin to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        ikiOrbEffects = cfg.getStringList("Primitive Vermin Orb Effects", IKI_CATEGORY, ikiOrbEffects, "Orb effects " + orb);
        ikiadaptedhealth = cfg.getFloat("Stage Adapted additional Health", IKI_CATEGORY, ikiadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Vermin.");
        ikiadapteddamage = cfg.getFloat("Stage Adapted additional Damage", IKI_CATEGORY, ikiadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Vermin.");
        ikiadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", IKI_CATEGORY, ikiadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Vermin.");
        ikiadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", IKI_CATEGORY, ikiadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Vermin.");
        ikiadaptedloot = cfg.getStringList("Stage Adapted loot Table", IKI_CATEGORY, ikiadaptedloot, "Items you want the Adapted Vermin to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        ikiASpawnRate = cfg.getInt("Stage Adapted spawnweight", IKI_CATEGORY, ikiASpawnRate, 0, 100, "Spawn rate for Adapted Vermin (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ikiadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", IKI_CATEGORY, ikiadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initwymoConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(WYMO_CATEGORY, "Tozoon \n Base Health: " + SPAttributes.WYMO_HEALTH + " \n Base Damage: " + SPAttributes.WYMO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.WYMO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.WYMO_KD_RESISTANCE);
        wymoEnabled = cfg.getBoolean("Primitive Tozoon Enabled", WYMO_CATEGORY, wymoEnabled, "Set to false if you want to disable Primitive Tozoon.");
        wymoHealthMultiplier = cfg.getFloat("Primitive Tozoon" + health, WYMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Tozoon.");
        wymoDamageMultiplier = cfg.getFloat("Primitive Tozoon" + damage, WYMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Tozoon.");
        wymoArmorMultiplier = cfg.getFloat("Primitive Tozoon" + armor, WYMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Tozoon.");
        wymoKDResistanceMultiplier = cfg.getFloat("Primitive Tozoon" + kd, WYMO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Tozoon.");
        wymoSpawnRate = cfg.getInt("Primitive Tozoon SpawnWeight", WYMO_CATEGORY, wymoSpawnRate, 0, 100, "Spawn rate for Primitive Tozoon (This value is ignored if Evolution Phases are enabled, it has its own option).");
        wymoLoot = cfg.getStringList("Primitive Tozoon Loot Table", WYMO_CATEGORY, wymoLoot, "Items you want the Primitive Tozoon to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        wymoOrbEffects = cfg.getStringList("Primitive Tozoon Orb Effects", WYMO_CATEGORY, wymoOrbEffects, "Orb effects " + orb);
        wymoadaptedhealth = cfg.getFloat("Stage Adapted additional Health", WYMO_CATEGORY, wymoadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Tozoon.");
        wymoadapteddamage = cfg.getFloat("Stage Adapted additional Damage", WYMO_CATEGORY, wymoadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Tozoon.");
        wymoadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", WYMO_CATEGORY, wymoadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Tozoon.");
        wymoadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", WYMO_CATEGORY, wymoadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Tozoon.");
        wymoadaptedloot = cfg.getStringList("Stage Adapted loot Table", WYMO_CATEGORY, wymoadaptedloot, "Items you want the Adapted Tozoon to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        wymoASpawnRate = cfg.getInt("Stage Adapted spawnweight", WYMO_CATEGORY, wymoASpawnRate, 0, 100, "Spawn rate for Adapted Tozoon (This value is ignored if Evolution Phases are enabled, it has its own option).");
        wymoadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", WYMO_CATEGORY, wymoadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initangedConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(ANGED_CATEGORY, "Vigilante \n Base Health: " + SPAttributes.ANGED_HEALTH + " \n Base Damage: " + SPAttributes.ANGED_ATTACK_DAMAGE + " \n Base Range Damage: " + SPAttributes.ANGED_RANGED_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ANGED_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ANGED_KD_RESISTANCE);
        angedEnabled = cfg.getBoolean("Vigilante Enabled", ANGED_CATEGORY, angedEnabled, "Set to false if you want to disable Vigilante.");
        angedHealthMultiplier = cfg.getFloat("Vigilante" + health, ANGED_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Vigilante.");
        angedDamageMultiplier = cfg.getFloat("Vigilante" + damage, ANGED_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Vigilante.");
        angedRangeDamageMultiplier = cfg.getFloat("Vigilante Range" + damage, ANGED_CATEGORY, 1.0f, 0.01f, 100.0f, "Range damage multiplier for Vigilante.");
        angedArmorMultiplier = cfg.getFloat("Vigilante" + armor, ANGED_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Vigilante.");
        angedKDResistanceMultiplier = cfg.getFloat("Vigilante" + kd, ANGED_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Vigilante.");
        angedSpawnRate = cfg.getInt("Vigilante SpawnWeight", ANGED_CATEGORY, angedSpawnRate, 0, 100, "Spawn rate for Vigilante (This value is ignored if Evolution Phases are enabled, it has its own option).");
        angedLoot = cfg.getStringList("Vigilante Loot Table", ANGED_CATEGORY, angedLoot, "Items you want the Vigilante to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        angedOrbEffects = cfg.getStringList("Vigilante Orb Effects", ANGED_CATEGORY, angedOrbEffects, "Orb effects " + orb);
    }

    private static void inittonroConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(TONRO_CATEGORY, "Kyphosis \n Base Health: " + SPAttributes.TONRO_HEALTH + " \n Base Damage: " + SPAttributes.TONRO_ATTACK_DAMAGE + " \n Base Swing Damage: " + SPAttributes.TONRO_SWING_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.TONRO_ARMOR + " \n ");
        tonroEnabled = cfg.getBoolean("Kyphosis Enabled", TONRO_CATEGORY, tonroEnabled, "Set to false if you want to disable Kyphosis.");
        tonroHealthMultiplier = cfg.getFloat("Kyphosis" + health, TONRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Kyphosis.");
        tonroDamageMultiplier = cfg.getFloat("Kyphosis" + damage, TONRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Kyphosis.");
        tonroSwingDamageMultiplier = cfg.getFloat("Kyphosis Swing" + damage, TONRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Swing damage multiplier for Kyphosis.");
        tonroArmorMultiplier = cfg.getFloat("Kyphosis" + armor, TONRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Kyphosis.");
        tonroLoot = cfg.getStringList("Kyphosis Loot Table", TONRO_CATEGORY, tonroLoot, "Items you want the Kyphosis to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initunvoConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(UNVO_CATEGORY, "Sentry \n Base Health: " + SPAttributes.UNVO_HEALTH + " \n Base Damage: " + SPAttributes.UNVO_ATTACK_DAMAGE + " \n Base Range Damage: " + SPAttributes.UNVO_RANGE_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.UNVO_ARMOR + " \n ");
        unvoEnabled = cfg.getBoolean("Sentry Enabled", UNVO_CATEGORY, unvoEnabled, "Set to false if you want to disable Sentry.");
        unvoHealthMultiplier = cfg.getFloat("Sentry" + health, UNVO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Sentry.");
        unvoDamageMultiplier = cfg.getFloat("Sentry" + damage, UNVO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Sentry.");
        unvoRangeDamageMultiplier = cfg.getFloat("Sentry Range" + damage, UNVO_CATEGORY, 1.0f, 0.01f, 100.0f, "Range damage multiplier for Sentry.");
        unvoArmorMultiplier = cfg.getFloat("Sentry" + armor, UNVO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Sentry.");
        unvoLoot = cfg.getStringList("Sentry Loot Table", UNVO_CATEGORY, unvoLoot, "Items you want the Sentry to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        unvoGearD = cfg.getFloat("Sentry Gear degrade", UNVO_CATEGORY, (float)unvoGearD, 0.0f, 1.0f, "How much a shot will degrade your gear (1=100%).");
        unvoPoisonDuration = cfg.getInt("Sentry Poison Duration", UNVO_CATEGORY, unvoPoisonDuration, 0, 100, "Poison duration in seconds for its projectile.");
        unvoPoisonAmplifier = cfg.getInt("Sentry Poison Amplifier", UNVO_CATEGORY, unvoPoisonAmplifier, 1, 100, "Poison amplifier for its projectile.");
    }

    private static void initganroConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(GANRO_CATEGORY, "Warden \n Base Health: " + SPAttributes.GANRO_HEALTH + " \n Base Damage: " + SPAttributes.GANRO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.GANRO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.GANRO_KD_RESISTANCE);
        ganroEnabled = cfg.getBoolean("Warden Enabled", GANRO_CATEGORY, ganroEnabled, "Set to false if you want to disable Warden.");
        ganroHealthMultiplier = cfg.getFloat("Warden" + health, GANRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Warden.");
        ganroDamageMultiplier = cfg.getFloat("Warden" + damage, GANRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Warden.");
        ganroArmorMultiplier = cfg.getFloat("Warden" + armor, GANRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Warden.");
        ganroKDResistanceMultiplier = cfg.getFloat("Warden" + kd, GANRO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Warden.");
        ganroSpawnRate = cfg.getInt("Warden SpawnWeight", GANRO_CATEGORY, ganroSpawnRate, 0, 100, "Spawn rate for Warden (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ganroLoot = cfg.getStringList("Warden Loot Table", GANRO_CATEGORY, ganroLoot, "Items you want the Warden to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        ganroOrbEffects = cfg.getStringList("Warden Orb Effects", GANRO_CATEGORY, ganroOrbEffects, "Orb effects " + orb);
    }

    private static void initesorConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(ESOR_CATEGORY, "Marauder \n Base Health: " + SPAttributes.ESOR_HEALTH + " \n Base Damage: " + SPAttributes.ESOR_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ESOR_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ESOR_KD_RESISTANCE);
        esorEnabled = cfg.getBoolean("Marauder Enabled", ESOR_CATEGORY, esorEnabled, "Set to false if you want to disable Marauder.");
        esorHealthMultiplier = cfg.getFloat("Marauder" + health, ESOR_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Marauder.");
        esorDamageMultiplier = cfg.getFloat("Marauder" + damage, ESOR_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Marauder.");
        esorArmorMultiplier = cfg.getFloat("Marauder" + armor, ESOR_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Marauder.");
        esorKDResistanceMultiplier = cfg.getFloat("Marauder" + kd, ESOR_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Marauder.");
        esorSpawnRate = cfg.getInt("Marauder SpawnWeight", ESOR_CATEGORY, esorSpawnRate, 0, 100, "Spawn rate for Marauder (This value is ignored if Evolution Phases are enabled, it has its own option).");
        esorLoot = cfg.getStringList("Marauder Loot Table", ESOR_CATEGORY, esorLoot, "Items you want the Marauder to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        esorOrbEffects = cfg.getStringList("Marauder Orb Effects", ESOR_CATEGORY, esorOrbEffects, "Orb effects " + orb);
    }

    private static void initorchConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(ORCH_CATEGORY, "Monarch \n Base Health: " + SPAttributes.ORCH_HEALTH + " \n Base Damage: " + SPAttributes.ORCH_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ORCH_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ORCH_KD_RESISTANCE);
        orchEnabled = cfg.getBoolean("Monarch Enabled", ORCH_CATEGORY, orchEnabled, "Set to false if you want to disable Monarch.");
        orchHealthMultiplier = cfg.getFloat("Monarch" + health, ORCH_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Monarch.");
        orchDamageMultiplier = cfg.getFloat("Monarch" + damage, ORCH_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Monarch.");
        orchArmorMultiplier = cfg.getFloat("Monarch" + armor, ORCH_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Monarch.");
        orchKDResistanceMultiplier = cfg.getFloat("Monarch" + kd, ORCH_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Monarch.");
        orchSpawnRate = cfg.getInt("Monarch SpawnWeight", ORCH_CATEGORY, orchSpawnRate, 0, 100, "Spawn rate for Monarch (This value is ignored if Evolution Phases are enabled, it has its own option).");
        orchLoot = cfg.getStringList("Monarch Loot Table", ORCH_CATEGORY, orchLoot, "Items you want the Monarch to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        orchOrbEffects = cfg.getStringList("Monarch Orb Effects", ORCH_CATEGORY, orchOrbEffects, "Orb effects " + orb);
    }

    private static void initflogConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(FLOG_CATEGORY, "Grunt \n Base Health: " + SPAttributes.FLOG_HEALTH + " \n Base Damage: " + SPAttributes.FLOG_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.FLOG_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FLOG_KD_RESISTANCE);
        flogEnabled = cfg.getBoolean("Grunt Enabled", FLOG_CATEGORY, flogEnabled, "Set to false if you want to disable Grunt.");
        flogHealthMultiplier = cfg.getFloat("Grunt" + health, FLOG_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Grunt.");
        flogDamageMultiplier = cfg.getFloat("Grunt" + damage, FLOG_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Grunt.");
        flogArmorMultiplier = cfg.getFloat("Grunt" + armor, FLOG_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Grunt.");
        flogKDResistanceMultiplier = cfg.getFloat("Grunt" + kd, FLOG_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Grunt.");
        flogSpawnRate = cfg.getInt("Grunt SpawnWeight", FLOG_CATEGORY, flogSpawnRate, 0, 100, "Spawn rate for Grunt (This value is ignored if Evolution Phases are enabled, it has its own option).");
        flogLoot = cfg.getStringList("Grunt Loot Table", FLOG_CATEGORY, flogLoot, "Items you want the Grunt to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initoroncoConfig(Configuration cfg) {
        String ancientspawning = " Ex. \"minecraft:zombie;0.1;1\"  Where: \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn. \n";
        cfg.addCustomCategoryComment(ORONCO_CATEGORY, "Ancient Dreadnaut \n Base Health: " + SPAttributes.ORONCO_HEALTH + " \n Base Damage: " + SPAttributes.ORONCO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ORONCO_ARMOR);
        oroncoEnabled = cfg.getBoolean("Ancient Dreadnaut Enabled", ORONCO_CATEGORY, oroncoEnabled, "Set to false if you want to disable Ancient Dreadnaut.");
        oroncoHealthMultiplier = cfg.getFloat("Ancient Dreadnaut" + health, ORONCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Ancient Dreadnaut.");
        oroncoDamageMultiplier = cfg.getFloat("Ancient Dreadnaut" + damage, ORONCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Ancient Dreadnaut.");
        oroncoArmorMultiplier = cfg.getFloat("Ancient Dreadnaut" + armor, ORONCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Ancient Dreadnaut.");
        oroncoKDResistanceMultiplier = cfg.getFloat("Ancient Dreadnaut" + kd, ORONCO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Ancient Dreadnaut.");
        oroncoSpawnRate = cfg.getInt("Ancient Dreadnaut SpawnWeight", ORONCO_CATEGORY, oroncoSpawnRate, 0, 100, "Spawn rate for Ancient Dreadnaut (This value is ignored if Evolution Phases are enabled, it has its own option).");
        oroncoLoot = cfg.getStringList("Ancient Dreadnaut Loot Table", ORONCO_CATEGORY, oroncoLoot, "Items you want the Ancient Dreadnaut to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        oroncoMaxY = cfg.getInt("Ancient Dreadnaut Flight Height Limit", ORONCO_CATEGORY, oroncoMaxY, 0, 256, "Number of blocks it can fly above the ground for Ancient Dreadnaut.");
        oroncoMinY = cfg.getInt("Ancient Dreadnaut Minimum Flight Height", ORONCO_CATEGORY, oroncoMinY, 0, 256, "Number of blocks it needs to fly above the ground for Ancient Dreadnaut.");
        oroncoMobList = cfg.getStringList("Ancient Dreadnaut Mob List", ORONCO_CATEGORY, oroncoMobList, "Mob list for the Ancient Dreadnaut." + ancientspawning);
        oroncoG = cfg.getBoolean("Ancient Dreadnaut Pod Grief", ORONCO_CATEGORY, oroncoG, "Set to false if you want to disable drop pods from breaking blocks on explotion.");
        oroncoMaxMobPod = cfg.getInt("Ancient Dreadnaut Pod Max Pods", ORONCO_CATEGORY, oroncoMaxMobPod, 1, 256, "Number of mobs inside a drop pod");
        oroncoPodNumber = cfg.getInt("Ancient Dreadnaut Pod Max Enemies", ORONCO_CATEGORY, oroncoPodNumber, 1, 256, "Number of drop pods spawned in an attack");
        oroncoPodCooldown = cfg.getInt("Ancient Dreadnaut Pod Cooldown", ORONCO_CATEGORY, oroncoPodCooldown, 1, 256, "Cooldown (in seconds) for the drop pod attack");
    }

    private static void initterlaConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(TERLA_CATEGORY, "Ancient Overlord \n Base Health: " + SPAttributes.TERLA_HEALTH + " \n Base Damage: " + SPAttributes.TERLA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.TERLA_ARMOR);
        terlaEnabled = cfg.getBoolean("Ancient Overlord Enabled", TERLA_CATEGORY, terlaEnabled, "Set to false if you want to disable Ancient Overlord.");
        terlaHealthMultiplier = cfg.getFloat("Ancient Overlord" + health, TERLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Ancient Overlord.");
        terlaDamageMultiplier = cfg.getFloat("Ancient Overlord" + damage, TERLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Ancient Overlord.");
        terlaArmorMultiplier = cfg.getFloat("Ancient Overlord" + armor, TERLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Ancient Overlord.");
        terlaKDResistanceMultiplier = cfg.getFloat("Ancient Overlord" + kd, TERLA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Ancient Overlord.");
        terlaSpawnRate = cfg.getInt("Ancient Overlord SpawnWeight", TERLA_CATEGORY, terlaSpawnRate, 0, 100, "Spawn rate for Ancient Overlord (This value is ignored if Evolution Phases are enabled, it has its own option).");
        terlaLoot = cfg.getStringList("Ancient Overlord Loot Table", TERLA_CATEGORY, terlaLoot, "Items you want the Ancient Overlord to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initarachnidaConfig(Configuration cfg) {
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(RANRAC_CATEGORY, "Arachnida \n Base Health: " + SPAttributes.RANRAC_HEALTH + " \n Base Damage: " + SPAttributes.RANRAC_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.RANRAC_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.RANRAC_KD_RESISTANCE);
        arachnidaEnabled = cfg.getBoolean("Primitive Arachnida Enabled", RANRAC_CATEGORY, arachnidaEnabled, "Set to false if you want to disable Primitive Arachnida.");
        arachnidaHealthMultiplier = cfg.getFloat("Primitive Arachnida" + health, RANRAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Arachnida.");
        arachnidaDamageMultiplier = cfg.getFloat("Primitive Arachnida" + damage, RANRAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Arachnida.");
        arachnidaArmorMultiplier = cfg.getFloat("Primitive Arachnida" + armor, RANRAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Arachnida.");
        arachnidaKDResistanceMultiplier = cfg.getFloat("Primitive Arachnida" + kd, RANRAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Arachnida.");
        arachnidaSpawnRate = cfg.getInt("Primitive Arachnida SpawnWeight", RANRAC_CATEGORY, arachnidaSpawnRate, 0, 100, "Spawn rate for Primitive Arachnida (This value is ignored if Evolution Phases are enabled, it has its own option).");
        arachnidaLoot = cfg.getStringList("Primitive Arachnida Loot Table", RANRAC_CATEGORY, arachnidaLoot, "Items you want the Primitive Arachnida to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        arachnidaOrbEffects = cfg.getStringList("Primitive Arachnida Orb Effects", RANRAC_CATEGORY, arachnidaOrbEffects, "Orb effects " + orb);
        arachnidaadaptedhealth = cfg.getFloat("Stage Adapted additional Health", RANRAC_CATEGORY, arachnidaadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Arachnida.");
        arachnidaadapteddamage = cfg.getFloat("Stage Adapted additional Damage", RANRAC_CATEGORY, arachnidaadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Arachnida.");
        arachnidaadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", RANRAC_CATEGORY, arachnidaadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Arachnida.");
        arachnidaadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", RANRAC_CATEGORY, arachnidaadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Arachnida.");
        arachnidaadaptedloot = cfg.getStringList("Stage Adapted loot Table", RANRAC_CATEGORY, arachnidaadaptedloot, "Items you want the Adapted Arachnida to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        arachnidaASpawnRate = cfg.getInt("Stage Adapted spawnweight", RANRAC_CATEGORY, arachnidaASpawnRate, 0, 100, "Spawn rate for Adapted Arachnida (This value is ignored if Evolution Phases are enabled, it has its own option).");
        arachnidaadaptedOrbEffects = cfg.getStringList("Stage Adapted Orb Effects", RANRAC_CATEGORY, arachnidaadaptedOrbEffects, "Orb effects " + orb);
    }

    private static void initkolConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(KOL_CATEGORY, "Worker \n Base Health: " + SPAttributes.KOL_HEALTH + " \n Base Damage: " + SPAttributes.KOL_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.KOL_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.KOL_KD_RESISTANCE + " \nNote: This mob will only spawn within the range of a Colony");
        kolEnabled = cfg.getBoolean("Worker Enabled", KOL_CATEGORY, kolEnabled, "Set to false if you want to disable Worker.");
        kolHealthMultiplier = cfg.getFloat("Worker" + health, KOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Worker.");
        kolDamageMultiplier = cfg.getFloat("Worker" + damage, KOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Worker.");
        kolArmorMultiplier = cfg.getFloat("Worker" + armor, KOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Worker.");
        kolKDResistanceMultiplier = cfg.getFloat("Worker" + kd, KOL_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Worker.");
        kolSpawnRate = cfg.getInt("Worker SpawnWeight", KOL_CATEGORY, kolSpawnRate, 0, 100, "Spawn rate for Worker (This value is ignored if Evolution Phases are enabled, it has its own option).");
        kolLoot = cfg.getStringList("Worker Loot Table", KOL_CATEGORY, kolLoot, "Items you want the Worker to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initinhooSConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(INHOOS_CATEGORY, "Incomplete Form (Small) \n Base Health: " + SPAttributes.INHOOS_HEALTH + " \n Base Damage: " + SPAttributes.INHOOS_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INHOOS_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INHOOS_KD_RESISTANCE + " \nNote: This mob will only spawn within the range of a Colony");
        inhooSEnabled = cfg.getBoolean("Incomplete Form (Small) Enabled", INHOOS_CATEGORY, inhooSEnabled, "Set to false if you want to disable Incomplete Form (Small).");
        inhooSHealthMultiplier = cfg.getFloat("Incomplete Form (Small)" + health, INHOOS_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Incomplete Form (Small).");
        inhooSDamageMultiplier = cfg.getFloat("Incomplete Form (Small)" + damage, INHOOS_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Incomplete Form (Small).");
        inhooSArmorMultiplier = cfg.getFloat("Incomplete Form (Small)" + armor, INHOOS_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Incomplete Form (Small).");
        inhooSKDResistanceMultiplier = cfg.getFloat("Incomplete Form (Small)" + kd, INHOOS_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Incomplete Form (Small).");
        inhooSSpawnRate = cfg.getInt("Incomplete Form (Small) SpawnWeight", INHOOS_CATEGORY, inhooSSpawnRate, 0, 100, "Spawn rate for Incomplete Form (Small) (This value is ignored if Evolution Phases are enabled, it has its own option).");
        inhooSLoot = cfg.getStringList("Incomplete Form (Small) Loot Table", INHOOS_CATEGORY, inhooSLoot, "Items you want the Incomplete Form (Small) to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initinhooMConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(INHOOM_CATEGORY, "Incomplete Form (Medium) \n Base Health: " + SPAttributes.INHOOM_HEALTH + " \n Base Damage: " + SPAttributes.INHOOM_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.INHOOM_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.INHOOM_KD_RESISTANCE + " \nNote: This mob will only spawn within the range of a Colony");
        inhooMEnabled = cfg.getBoolean("Incomplete Form (Medium) Enabled", INHOOM_CATEGORY, inhooMEnabled, "Set to false if you want to disable Incomplete Form (Medium).");
        inhooMHealthMultiplier = cfg.getFloat("Incomplete Form (Medium)" + health, INHOOM_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Incomplete Form (Medium).");
        inhooMDamageMultiplier = cfg.getFloat("Incomplete Form (Medium)" + damage, INHOOM_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Incomplete Form (Medium).");
        inhooMArmorMultiplier = cfg.getFloat("Incomplete Form (Medium)" + armor, INHOOM_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Incomplete Form (Medium).");
        inhooMKDResistanceMultiplier = cfg.getFloat("Incomplete Form (Medium)" + kd, INHOOM_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Incomplete Form (Medium).");
        inhooMSpawnRate = cfg.getInt("Incomplete Form (Medium) SpawnWeight", INHOOM_CATEGORY, inhooMSpawnRate, 0, 100, "Spawn rate for Incomplete Form (Medium) (This value is ignored if Evolution Phases are enabled, it has its own option).");
        inhooMLoot = cfg.getStringList("Incomplete Form (Medium) Loot Table", INHOOM_CATEGORY, inhooMLoot, "Items you want the Incomplete Form (Medium) to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initdoneConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(DONE_CATEGORY, "Dredge \n Base Health: " + SPAttributes.LEEM_HEALTH + " \n Base Damage: " + SPAttributes.LEER_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LEER_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.LEER_KD_RESISTANCE + " \nNote: This mob will only spawn within the range of a Colony");
        doneEnabled = cfg.getBoolean("Dredge Enabled", DONE_CATEGORY, doneEnabled, "Set to false if you want to disable Dredge.");
        doneHealthMultiplier = cfg.getFloat("Dredge" + health, DONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Dredge.");
        doneDamageMultiplier = cfg.getFloat("Dredge" + damage, DONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Dredge.");
        doneArmorMultiplier = cfg.getFloat("Dredge" + armor, DONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Dredge.");
        doneKDResistanceMultiplier = cfg.getFloat("Dredge" + kd, DONE_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Dredge.");
        doneSpawnRate = cfg.getInt("Dredge SpawnWeight", DONE_CATEGORY, doneSpawnRate, 0, 100, "Spawn rate for Dredge (This value is ignored if Evolution Phases are enabled, it has its own option).");
        doneLoot = cfg.getStringList("Dredge Loot Table", DONE_CATEGORY, doneLoot, "Items you want the Dredge to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initleerConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(LEER_CATEGORY, "Airscrew \n Base Health: " + SPAttributes.LEER_HEALTH + " \n Base Damage: " + SPAttributes.LEER_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LEER_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.LEER_KD_RESISTANCE + " \nNote: This mob will only spawn within the range of a Colony");
        leerEnabled = cfg.getBoolean("Airscrew Enabled", LEER_CATEGORY, leerEnabled, "Set to false if you want to disable Airscrew.");
        leerHealthMultiplier = cfg.getFloat("Airscrew" + health, LEER_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Airscrew.");
        leerDamageMultiplier = cfg.getFloat("Airscrew" + damage, LEER_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Airscrew.");
        leerArmorMultiplier = cfg.getFloat("Airscrew" + armor, LEER_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Airscrew.");
        leerKDResistanceMultiplier = cfg.getFloat("Airscrew" + kd, LEER_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Airscrew.");
        leerSpawnRate = cfg.getInt("Airscrew SpawnWeight", LEER_CATEGORY, leerSpawnRate, 0, 100, "Spawn rate for Airscrew (This value is ignored if Evolution Phases are enabled, it has its own option).");
        leerLoot = cfg.getStringList("Airscrew Loot Table", LEER_CATEGORY, leerLoot, "Items you want the Airscrew to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initquacConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(QUAC_CATEGORY, "Worm Carrier \n Base Health: " + SPAttributes.QUAC_HEALTH + " \n Base Damage: " + SPAttributes.QUAC_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.QUAC_ARMOR);
        quacEnabled = cfg.getBoolean("Worm Carrier Enabled", QUAC_CATEGORY, quacEnabled, "Set to false if you want to disable Worm Carrier.");
        quacHealthMultiplier = cfg.getFloat("Worm Carrier" + health, QUAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Worm Carrier.");
        quacDamageMultiplier = cfg.getFloat("Worm Carrier" + damage, QUAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Worm Carrier.");
        quacArmorMultiplier = cfg.getFloat("Worm Carrier" + armor, QUAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Worm Carrier.");
        quacKDResistanceMultiplier = cfg.getFloat("Worm Carrier" + kd, QUAC_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Worm Carrier.");
        quacSpawnRate = cfg.getInt("Worm Carrier SpawnWeight", QUAC_CATEGORY, quacSpawnRate, 0, 100, "Spawn rate for Worm Carrier (This value is ignored if Evolution Phases are enabled, it has its own option).");
        quacLoot = cfg.getStringList("Worm Carrier Loot Table", QUAC_CATEGORY, quacLoot, "Items you want the Worm Carrier to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initrondConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(ROND_CATEGORY, "Worm Carrier \n Base Health: " + SPAttributes.ROND_HEALTH + " \n Base Damage: " + SPAttributes.ROND_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.ROND_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ROND_KD_RESISTANCE + " \nNote: This mob will only spawn within the range of a Colony");
        rondEnabled = cfg.getBoolean("Worm Carrier Enabled", ROND_CATEGORY, rondEnabled, "Set to false if you want to disable Worm Carrier.");
        rondHealthMultiplier = cfg.getFloat("Worm Carrier" + health, ROND_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Worm Carrier.");
        rondDamageMultiplier = cfg.getFloat("Worm Carrier" + damage, ROND_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Worm Carrier.");
        rondArmorMultiplier = cfg.getFloat("Worm Carrier" + armor, ROND_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Worm Carrier.");
        rondKDResistanceMultiplier = cfg.getFloat("Worm Carrier" + kd, ROND_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Worm Carrier.");
        rondSpawnRate = cfg.getInt("Worm Carrier SpawnWeight", ROND_CATEGORY, rondSpawnRate, 0, 100, "Spawn rate for Worm Carrier (This value is ignored if Evolution Phases are enabled, it has its own option).");
        rondLoot = cfg.getStringList("Worm Carrier Loot Table", ROND_CATEGORY, rondLoot, "Items you want the Worm Carrier to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initombooConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        cfg.addCustomCategoryComment(OMBOO_CATEGORY, "Light Bomber \n Base Health: " + SPAttributes.OMBOO_HEALTH + " \n Base Armor: " + SPAttributes.OMBOO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.OMBOO_KD_RESISTANCE);
        ombooEnabled = cfg.getBoolean("Light Bomber Enabled", OMBOO_CATEGORY, ombooEnabled, "Set to false if you want to disable Light Bomber.");
        ombooHealthMultiplier = cfg.getFloat("Light Bomber" + health, OMBOO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Light Bomber.");
        ombooDamageMultiplier = cfg.getFloat("Light Bomber" + damage, OMBOO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Light Bomber.");
        ombooArmorMultiplier = cfg.getFloat("Light Bomber" + armor, OMBOO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Light Bomber.");
        ombooKDResistanceMultiplier = cfg.getFloat("Light Bomber" + kd, OMBOO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Light Bomber.");
        ombooSpawnRate = cfg.getInt("Light Bomber SpawnWeight", OMBOO_CATEGORY, ombooSpawnRate, 0, 100, "Spawn rate for Light Bomber (This value is ignored if Evolution Phases are enabled, it has its own option).");
        ombooGriefing = cfg.getBoolean("Light Bomber Griefing", OMBOO_CATEGORY, ombooGriefing, "Set to true if you want the Light Bomber to destroy blocks on explosion.");
        ombooLoot = cfg.getStringList("Light Bomber Loot Table", OMBOO_CATEGORY, ombooLoot, "Items you want the Light Bomber to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        ombooMaxY = cfg.getInt("Light Bomber Flight Height Limit", OMBOO_CATEGORY, ombooMaxY, 0, 256, "Number of blocks it can fly above the ground for Light Bomber.");
        ombooDamage = cfg.getFloat("Light Bomber Bomb Damage", OMBOO_CATEGORY, ombooDamage, 1.0f, 1000.0f, "Damage of its bomb");
    }

    private static void initjinjoConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(JINJO_CATEGORY, "Heavy Bomber \n Base Health: " + SPAttributes.JINJO_HEALTH + " \n Base Damage: " + SPAttributes.JINJO_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.JINJO_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.JINJO_KD_RESISTANCE);
        jinjoEnabled = cfg.getBoolean("Heavy Bomber Enabled", JINJO_CATEGORY, jinjoEnabled, "Set to false if you want to disable Heavy Bomber.");
        jinjoHealthMultiplier = cfg.getFloat("Heavy Bomber" + health, JINJO_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Heavy Bomber.");
        jinjoDamageMultiplier = cfg.getFloat("Heavy Bomber" + damage, JINJO_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Heavy Bomber.");
        jinjoArmorMultiplier = cfg.getFloat("Heavy Bomber" + armor, JINJO_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Heavy Bomber.");
        jinjoKDResistanceMultiplier = cfg.getFloat("Heavy Bomber" + kd, JINJO_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Heavy Bomber.");
        jinjoSpawnRate = cfg.getInt("Heavy Bomber SpawnWeight", JINJO_CATEGORY, jinjoSpawnRate, 0, 100, "Spawn rate for Heavy Bomber (This value is ignored if Evolution Phases are enabled, it has its own option).");
        jinjoGriefing = cfg.getBoolean("Heavy Bomber Griefing", JINJO_CATEGORY, jinjoGriefing, "Set to true if you want the Heavy Bomber to destroy blocks on explosion.");
        jinjoLoot = cfg.getStringList("Heavy Bomber Loot Table", JINJO_CATEGORY, jinjoLoot, "Items you want the Heavy Bomber to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        jinjoMaxY = cfg.getInt("Heavy Bomber Flight Height Limit", JINJO_CATEGORY, jinjoMaxY, 0, 256, "Number of blocks it can fly above the ground for Heavy Bomber.");
        jinjoMobs = cfg.getStringList("Heavy Bomber Mob Table", JINJO_CATEGORY, jinjoMobs, "Mob list for Heavy Bomber." + mobTable);
        jinjoDamage = cfg.getFloat("Heavy Bomber Bomb Damage", JINJO_CATEGORY, jinjoDamage, 1.0f, 1000.0f, "Damage of its bomb");
        jinjoOrbEffects = cfg.getStringList("Heavy Bomber Orb Effects", JINJO_CATEGORY, jinjoOrbEffects, "Orb effects " + orb);
        jinjoExplotionMult = cfg.getFloat("Heavy Bomber Explotion Multiplier", JINJO_CATEGORY, jinjoExplotionMult, 1.0f, 100.0f, "Explotion damage will take base Damage and multiply it by this value.");
    }

    private static void initflamConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(flam_CATEGORY, "Succor \n Base Health: " + SPAttributes.FLAM_HEALTH + " \n Base Armor: " + SPAttributes.FLAM_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.FLAM_KD_RESISTANCE);
        flamEnabled = cfg.getBoolean("Succor Enabled", flam_CATEGORY, flamEnabled, "Set to false if you want to disable Succor.");
        flamHealthMultiplier = cfg.getFloat("Succor" + health, flam_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Succor.");
        flamArmorMultiplier = cfg.getFloat("Succor" + armor, flam_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Succor.");
        flamKDResistanceMultiplier = cfg.getFloat("Succor" + kd, flam_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Succor.");
        flamLoot = cfg.getStringList("Succor Loot Table", flam_CATEGORY, flamLoot, "Items you want the Succor to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initvestaConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        String eff = " Ex. \"0;1;minecraft:fire_resistance\" Where: \n \"0\" potion duration in seconds, \n \"1\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself. \n";
        cfg.addCustomCategoryComment(VESTA_CATEGORY, "Colony Carrier \n Base Health: " + SPAttributes.VESTA_HEALTH + " \n Base Armor: " + SPAttributes.VESTA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.VESTA_KD_RESISTANCE);
        vestaEnabled = cfg.getBoolean("Colony Carrier Enabled", VESTA_CATEGORY, vestaEnabled, "Set to false if you want to disable Colony Carrier.");
        vestaHealthMultiplier = cfg.getFloat("Colony Carrier" + health, VESTA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Colony Carrier.");
        vestaDamageMultiplier = cfg.getFloat("Colony Carrier" + damage, VESTA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Colony Carrier.");
        vestaArmorMultiplier = cfg.getFloat("Colony Carrier" + armor, VESTA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Colony Carrier.");
        vestaKDResistanceMultiplier = cfg.getFloat("Colony Carrier" + kd, VESTA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Colony Carrier.");
        vestaSpawnRate = cfg.getInt("Colony Carrier SpawnWeight", VESTA_CATEGORY, vestaSpawnRate, 0, 100, "Spawn rate for Colony Carrier (This value is ignored if Evolution Phases are enabled, it has its own option).");
        vestaLoot = cfg.getStringList("Colony Carrier Loot Table", VESTA_CATEGORY, vestaLoot, "Items you want the Colony Carrier to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        vestaOrbEffects = cfg.getStringList("Colony Carrier Orb Effects", VESTA_CATEGORY, vestaOrbEffects, "Orb effects " + orb);
        vestacd = cfg.getInt("Colony Carrier Buffs Cooldown", VESTA_CATEGORY, vestacd, 0, 100, "Cooldown (in seconds) to buff parasites for Colony Carrier.");
        vestarange = cfg.getInt("Colony Carrier Buffs Range", VESTA_CATEGORY, vestarange, 0, 100, "Range to buff parasites for Colony Carrier.");
        vestaeffects = cfg.getStringList("Colony Carrier Effects", VESTA_CATEGORY, vestaeffects, "Potion effects that will give the Colony Carrier to nearby parasites " + eff);
    }

    private static void initelviaConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(ELVIA_CATEGORY, "Wraith \n Base Health: " + SPAttributes.ELVIA_HEALTH + " \n Base Armor: " + SPAttributes.ELVIA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.ELVIA_KD_RESISTANCE);
        elviaEnabled = cfg.getBoolean("Wraith Enabled", ELVIA_CATEGORY, elviaEnabled, "Set to false if you want to disable Wraith.");
        elviaHealthMultiplier = cfg.getFloat("Wraith" + health, ELVIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Wraith.");
        elviaDamageMultiplier = cfg.getFloat("Wraith" + damage, ELVIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Wraith.");
        elviaArmorMultiplier = cfg.getFloat("Wraith" + armor, ELVIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Wraith.");
        elviaKDResistanceMultiplier = cfg.getFloat("Wraith" + kd, ELVIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Wraith.");
        elviaSpawnRate = cfg.getInt("Wraith SpawnWeight", ELVIA_CATEGORY, elviaSpawnRate, 0, 100, "Spawn rate for Wraith (This value is ignored if Evolution Phases are enabled, it has its own option).");
        elviaLoot = cfg.getStringList("Wraith Loot Table", ELVIA_CATEGORY, elviaLoot, "Items you want the Wraith to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        elviaOrbEffects = cfg.getStringList("Wraith Orb Effects", ELVIA_CATEGORY, elviaOrbEffects, "Orb effects " + orb);
        elvianeededhealth = cfg.getFloat("Wraith needed Health", ELVIA_CATEGORY, (float)elvianeededhealth, 0.0f, 1.0f, "Health (1 = 100%) needed to go invisible.");
    }

    private static void initlenciaConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(LENCIA_CATEGORY, "Bogle \n Base Health: " + SPAttributes.LENCIA_HEALTH + " \n Base Armor: " + SPAttributes.LENCIA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.LENCIA_KD_RESISTANCE);
        lenciaEnabled = cfg.getBoolean("Bogle Enabled", LENCIA_CATEGORY, lenciaEnabled, "Set to false if you want to disable Bogle.");
        lenciaHealthMultiplier = cfg.getFloat("Bogle" + health, LENCIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Bogle.");
        lenciaDamageMultiplier = cfg.getFloat("Bogle" + damage, LENCIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Bogle.");
        lenciaArmorMultiplier = cfg.getFloat("Bogle" + armor, LENCIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Bogle.");
        lenciaKDResistanceMultiplier = cfg.getFloat("Bogle" + kd, LENCIA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Bogle.");
        lenciaSpawnRate = cfg.getInt("Bogle SpawnWeight", LENCIA_CATEGORY, lenciaSpawnRate, 0, 100, "Spawn rate for Bogle (This value is ignored if Evolution Phases are enabled, it has its own option).");
        lenciaLoot = cfg.getStringList("Bogle Loot Table", LENCIA_CATEGORY, lenciaLoot, "Items you want the Bogle to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        lenciaOrbEffects = cfg.getStringList("Bogle Orb Effects", LENCIA_CATEGORY, lenciaOrbEffects, "Orb effects " + orb);
        lenciaGriefing = cfg.getBoolean("Bogle Griefing", LENCIA_CATEGORY, lenciaGriefing, "Set to true if you want the Bogle to destroy blocks on explosion.");
        lencianeededhealth = cfg.getFloat("Bogle needed Health", LENCIA_CATEGORY, (float)lencianeededhealth, 0.0f, 1.0f, "Health (1 = 100%) needed to go invisible.");
    }

    private static void initpheonConfig(Configuration cfg) {
        String mobTable = " Ex. \"minecraft:zombie;4;2\" Where: \n \"minecraft:zombie\" is the entity, \n \"4\" is the maximum number of entities, \n \"2\" is the minimum number of entities. \n";
        String orb = " Ex. \"0;60;2;minecraft:fire_resistance;4;3\" Where: \n \"0\" mode of applying, 0 to non parasite mobs, 1 to self, 2 to parasite mobs, \n \"60\" potion duration in seconds, \n \"2\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself, \n \"4\" if != 0 then amplifier += (mobs inside the orb / this value), \n \"3\" if != 0 then amplifier += (mobs inside the orb / this value) * 20. \n";
        cfg.addCustomCategoryComment(PHEON_CATEGORY, "Haunter \n Base Health: " + SPAttributes.PHEON_HEALTH + " \n Base Armor: " + SPAttributes.PHEON_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.PHEON_KD_RESISTANCE);
        pheonEnabled = cfg.getBoolean("Haunter Enabled", PHEON_CATEGORY, pheonEnabled, "Set to false if you want to disable Haunter.");
        pheonHealthMultiplier = cfg.getFloat("Haunter" + health, PHEON_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Haunter.");
        pheonDamageMultiplier = cfg.getFloat("Haunter" + damage, PHEON_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Haunter.");
        pheonArmorMultiplier = cfg.getFloat("Haunter" + armor, PHEON_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Haunter.");
        pheonKDResistanceMultiplier = cfg.getFloat("Haunter" + kd, PHEON_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Haunter.");
        pheonSpawnRate = cfg.getInt("Haunter SpawnWeight", PHEON_CATEGORY, pheonSpawnRate, 0, 100, "Spawn rate for Haunter (This value is ignored if Evolution Phases are enabled, it has its own option).");
        pheonLoot = cfg.getStringList("Haunter Loot Table", PHEON_CATEGORY, pheonLoot, "Items you want the Haunter to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        pheonOrbEffects = cfg.getStringList("Haunter Orb Effects", PHEON_CATEGORY, pheonOrbEffects, "Orb effects " + orb);
    }

    private static void initcruxaConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CRUXA_CATEGORY, "Crux \n Base Health: " + SPAttributes.CRUXA_HEALTH + " \n Base Damage: " + SPAttributes.CRUXA_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.CRUXA_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.CRUXA_KD_RESISTANCE);
        cruxaEnabled = cfg.getBoolean("Crux Enabled", CRUXA_CATEGORY, cruxaEnabled, "Set to false if you want to disable Crux.");
        cruxaHealthMultiplier = cfg.getFloat("Crux" + health, CRUXA_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Crux.");
        cruxaDamageMultiplier = cfg.getFloat("Crux" + damage, CRUXA_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Crux.");
        cruxaArmorMultiplier = cfg.getFloat("Crux" + armor, CRUXA_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Crux.");
        cruxaKDResistanceMultiplier = cfg.getFloat("Crux" + kd, CRUXA_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Crux.");
        cruxaSpawnRate = cfg.getInt("Crux SpawnWeight", CRUXA_CATEGORY, cruxaSpawnRate, 0, 100, "Spawn rate for Crux (This value is ignored if Evolution Phases are enabled, it has its own option).");
        cruxaLoot = cfg.getStringList("Crux Loot Table", CRUXA_CATEGORY, cruxaLoot, "Items you want the Crux to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        cruxaDamageGain = cfg.getFloat("Crux Damage Stack Value", CRUXA_CATEGORY, (float)cruxaDamageGain, 0.01f, 1.0f, "Percentage of damage the Crux will use to increase its damage when killing mobs.");
        cruxaDamageCap = cfg.getInt("Crux Damage Stack Cap", CRUXA_CATEGORY, cruxaDamageCap, 0, 100, "Limit on how many times the Crux can increase its own damage.");
        cruxMinGrowTime = cfg.getInt("Crux Incomplete Minimum grow time", CRUXA_CATEGORY, cruxMinGrowTime, 0, 1000, "Minimum time in seconds for this to grow into a Crux.");
        cruxMaxGrowTime = cfg.getInt("Crux Incomplete Maximum grow time", CRUXA_CATEGORY, cruxMaxGrowTime, 0, 1000, "Maximum time in seconds for this to grow into a Crux.");
    }

    private static void initheedConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HEED_CATEGORY, "Heed \n Base Health: " + SPAttributes.HEED_HEALTH + " \n Base Damage: " + SPAttributes.HEED_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HEED_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HEED_KD_RESISTANCE);
        heedEnabled = cfg.getBoolean("Heed Enabled", HEED_CATEGORY, heedEnabled, "Set to false if you want to disable Heed.");
        heedHealthMultiplier = cfg.getFloat("Heed" + health, HEED_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Heed.");
        heedDamageMultiplier = cfg.getFloat("Heed" + damage, HEED_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Heed.");
        heedArmorMultiplier = cfg.getFloat("Heed" + armor, HEED_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Heed.");
        heedKDResistanceMultiplier = cfg.getFloat("Heed" + kd, HEED_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Heed.");
        heedSpawnRate = cfg.getInt("Heed SpawnWeight", HEED_CATEGORY, heedSpawnRate, 0, 100, "Spawn rate for Heed (This value is ignored if Evolution Phases are enabled, it has its own option).");
        heedLoot = cfg.getStringList("Heed Loot Table", HEED_CATEGORY, heedLoot, "Items you want the Heed to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initLumConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(LUM_CATEGORY, "Devourer \n Base Health: " + SPAttributes.LUM_HEALTH + " \n Base Damage: " + SPAttributes.LUM_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LUM_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.LUM_KD_RESISTANCE);
        lumEnabled = cfg.getBoolean("Primitive Devourer Enabled", LUM_CATEGORY, lumEnabled, "Set to false if you want to disable Primitive Devourer.");
        lumWaterPlacement = cfg.getBoolean("Devourer Water Placement", LUM_CATEGORY, lumWaterPlacement, "Set to false if you want to disable water placement when this mob breaks blocks.");
        lumHealthMultiplier = cfg.getFloat("Primitive Devourer" + health, LUM_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Primitive Devourer.");
        lumDamageMultiplier = cfg.getFloat("Primitive Devourer" + damage, LUM_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Primitive Devourer.");
        lumArmorMultiplier = cfg.getFloat("Primitive Devourer" + armor, LUM_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Primitive Devourer.");
        lumKDResistanceMultiplier = cfg.getFloat("Primitive Devourer" + kd, LUM_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Primitive Devourer.");
        lumSpawnRate = cfg.getInt("Primitive Devourer SpawnWeight", LUM_CATEGORY, lumSpawnRate, 0, 100, "Spawn rate for Primitive Devourer (This value is ignored if Evolution Phases are enabled, it has its own option).");
        lumLoot = cfg.getStringList("Primitive Devourer Loot Table", LUM_CATEGORY, lumLoot, "Items you want the Primitive Devourer to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        lumadaptedhealth = cfg.getFloat("Stage Adapted additional Health", LUM_CATEGORY, lumadaptedhealth, 0.01f, 100.0f, "Additional health for Adapted Devourer.");
        lumadapteddamage = cfg.getFloat("Stage Adapted additional Damage", LUM_CATEGORY, lumadapteddamage, 0.01f, 100.0f, "Additional damage for Adapted Devourer.");
        lumadaptedarmor = cfg.getFloat("Stage Adapted additional Armor", LUM_CATEGORY, lumadaptedarmor, 0.01f, 100.0f, "Additional armor for Adapted Devourer.");
        lumadaptedkdresistance = cfg.getFloat("Stage Adapted additional Knockback Resistance", LUM_CATEGORY, lumadaptedkdresistance, 0.01f, 100.0f, "Additional Knockback Resistance for Adapted Devourer.");
        lumadaptedloot = cfg.getStringList("Stage Adapted loot Table", LUM_CATEGORY, lumadaptedloot, "Items you want the Adapted Devourer to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        lumASpawnRate = cfg.getInt("Stage Adapted spawnweight", LUM_CATEGORY, lumASpawnRate, 0, 100, "Spawn rate for Adapted Devourer (not working) (This value is ignored if Evolution Phases are enabled, it has its own option).");
    }

    private static void initnakConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(NAK_CATEGORY, "Seizer \n Base Health: " + SPAttributes.NAK_HEALTH + " \n Base Damage: " + SPAttributes.NAK_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.NAK_ARMOR);
        nakEnabled = cfg.getBoolean("Seizer Enabled", NAK_CATEGORY, nakEnabled, "Set to false if you want to disable Seizer.");
        nakHealthMultiplier = cfg.getFloat("Seizer" + health, NAK_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Seizer.");
        nakDamageMultiplier = cfg.getFloat("Seizer" + damage, NAK_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Seizer.");
        nakArmorMultiplier = cfg.getFloat("Seizer" + armor, NAK_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Seizer.");
        nakSpawnRate = cfg.getInt("Seizer SpawnWeight", NAK_CATEGORY, nakSpawnRate, 0, 100, "Spawn rate for Seizer (This value is ignored if Evolution Phases are enabled, it has its own option).");
        nakLoot = cfg.getStringList("Seizer Loot Table", NAK_CATEGORY, nakLoot, "Items you want the Seizer to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initdroppodConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(POD1_CATEGORY_RSI, "Droppod \n Base Health: " + SPAttributes.POD1_HEALTH + " \n Base Damage: " + SPAttributes.POD1_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.POD1_ARMOR);
        String eff = " Ex. \"0;1;minecraft:fire_resistance\" Where: \n \"0\" potion duration in seconds, \n \"1\" potion amplifier, \n \"minecraft:fire_resistance\" potion itself. \n";
        pod1HealthMultiplier = cfg.getFloat("DropPod " + health, POD1_CATEGORY_RSI, 1.0f, 0.01f, 100.0f, "Health multiplier for Droppod.");
        pod1DamageMultiplier = cfg.getFloat("DropPod " + damage, POD1_CATEGORY_RSI, 1.0f, 0.01f, 100.0f, "Damage multiplier for Droppod.");
        pod1ArmorMultiplier = cfg.getFloat("DropPod " + armor, POD1_CATEGORY_RSI, 1.0f, 0.01f, 100.0f, "Armor multiplier for Droppod.");
        pod1Loot = cfg.getStringList("DropPod Loot Table", POD1_CATEGORY_RSI, pod1Loot, "Items you want the Droppod to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        pod1Effects = cfg.getStringList("DropPod Effects", POD1_CATEGORY_RSI, pod1Effects, "Potion effects that will give to nearby mobs when exploding " + eff);
    }

    private static void initbeckonSIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_BSI, "Beckon Stage I \n Base Health: " + SPAttributes.VENKROL_HEALTH + " \n Base Damage: " + SPAttributes.VENKROL_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.VENKROL_ARMOR);
        String venkrolName = "Stage I Beckon";
        String RSspawning = " Ex. \"ground;minecraft:zombie;0.1;1\"  Where: \n \"ground\" is for the entity type (ground, air) (air type spawning is triggered when the target y's value is higher than the host plus 3), \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn, \n \"1\" is for the cost the entity has. \n";
        venkrolHealthMultiplier = cfg.getFloat(venkrolName + health, REINFORCEMENT_CATEGORY_BSI, 1.0f, 0.01f, 100.0f, "Health multiplier for " + venkrolName + ".");
        venkrolDamageMultiplier = cfg.getFloat(venkrolName + damage, REINFORCEMENT_CATEGORY_BSI, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + venkrolName + ".");
        venkrolArmorMultiplier = cfg.getFloat(venkrolName + armor, REINFORCEMENT_CATEGORY_BSI, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + venkrolName + ".");
        venkrolSpawnrate = cfg.getInt(venkrolName + " Spawnweight", REINFORCEMENT_CATEGORY_BSI, venkrolSpawnrate, 0, 100, "Spawn weight for " + venkrolName + " (NOTE: They will spawn like vanilla passive creatures).");
        venkrolRange = cfg.getInt(venkrolName + " Block Infestation Range", REINFORCEMENT_CATEGORY_BSI, venkrolRange, 0, 100, "Block infestation radius for " + venkrolName + ".");
        venkrolRangeY = cfg.getInt(venkrolName + " Block Infestation Range Y", REINFORCEMENT_CATEGORY_BSI, venkrolRangeY, 0, 100, "Block infestation radius (upwards/downwards) for " + venkrolName + ".");
        venkrolCooldown = cfg.getInt(venkrolName + " Cooldown", REINFORCEMENT_CATEGORY_BSI, venkrolCooldown, 0, 10000, "Summoning cooldown (in seconds) for " + venkrolName + ".");
        venkrollimit = cfg.getInt(venkrolName + " Limit", REINFORCEMENT_CATEGORY_BSI, venkrollimit, 0, 10000, "Number of attacks before its cooldown for " + venkrolName + ".");
        venkrolTotalActiveMobs = cfg.getInt(venkrolName + " Total Active Mobs", REINFORCEMENT_CATEGORY_BSI, venkrolTotalActiveMobs, 0, 100, "Number of total points used in mob spawning for " + venkrolName + ".");
        venkrolmoblist = cfg.getStringList(venkrolName + " Mob List", REINFORCEMENT_CATEGORY_BSI, venkrolmoblist, "Mob list for " + venkrolName + "." + RSspawning + "Stage I cannot trigger the air condition. \n");
        venkrolCAMinimumV = cfg.getInt(venkrolName + " CA number", REINFORCEMENT_CATEGORY_BSI, venkrolCAMinimumV, 0, 10000, "Number  for " + venkrolName + " required to do a Collective Attack (only one will attack) for " + venkrolName + ".");
        venkrolCAExtraM = cfg.getFloat(venkrolName + " CA mobs", REINFORCEMENT_CATEGORY_BSI, venkrolCAExtraM, 0.0f, 100.0f, "Value of each " + venkrolName + " to spawn extra mobs (the mobs will not add up to the total points) for " + venkrolName + ".");
        venkrolLoot = cfg.getStringList(venkrolName + " Loot Table", REINFORCEMENT_CATEGORY_BSI, venkrolLoot, "Items you want the " + venkrolName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initbeckonSIIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_BSII, "Beckon Stage II \n Base Health: " + SPAttributes.VENKROLSII_HEALTH + " \n Base Damage: " + SPAttributes.VENKROLSII_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.VENKROLSII_ARMOR);
        String venkrolsiiName = "Stage II Beckon";
        String RSspawning = " Ex. \"ground;minecraft:zombie;0.1;1\"  Where: \n \"ground\" is for the entity type (ground, air) (air type spawning is triggered when the target y's value is higher than the host plus 3), \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn, \n \"1\" is for the cost the entity has. \n";
        venkrolsiiHealthMultiplier = cfg.getFloat(venkrolsiiName + health, REINFORCEMENT_CATEGORY_BSII, 1.0f, 0.01f, 100.0f, "Health multiplier for " + venkrolsiiName + ".");
        venkrolsiiDamageMultiplier = cfg.getFloat(venkrolsiiName + damage, REINFORCEMENT_CATEGORY_BSII, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + venkrolsiiName + ".");
        venkrolsiiArmorMultiplier = cfg.getFloat(venkrolsiiName + armor, REINFORCEMENT_CATEGORY_BSII, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + venkrolsiiName + ".");
        venkrolsiiRange = cfg.getInt(venkrolsiiName + " Block Infestation Range", REINFORCEMENT_CATEGORY_BSII, venkrolsiiRange, 0, 100, "Block infestation radius for " + venkrolsiiName + ".");
        venkrolsiiRangeY = cfg.getInt(venkrolsiiName + " Block Infestation Range Y", REINFORCEMENT_CATEGORY_BSII, venkrolsiiRangeY, 0, 100, "Block infestation radius (upwards/downwards) for " + venkrolsiiName + ".");
        venkrolsiiCooldown = cfg.getInt(venkrolsiiName + " Cooldown", REINFORCEMENT_CATEGORY_BSII, venkrolsiiCooldown, 0, 10000, "Summoning cooldown (in seconds) for " + venkrolsiiName + ".");
        venkrolsiilimit = cfg.getInt(venkrolsiiName + " Limit", REINFORCEMENT_CATEGORY_BSII, venkrolsiilimit, 0, 10000, "Number of attacks before its cooldown for " + venkrolsiiName + ".");
        venkrolsiiTotalActiveMobs = cfg.getInt(venkrolsiiName + " Total Active Mobs", REINFORCEMENT_CATEGORY_BSII, venkrolsiiTotalActiveMobs, 0, 100, "Number of total points used in mob spawning for " + venkrolsiiName + ".");
        venkrolsiimoblist = cfg.getStringList(venkrolsiiName + " Mob List", REINFORCEMENT_CATEGORY_BSII, venkrolsiimoblist, "Mob list for " + venkrolsiiName + "." + RSspawning);
        venkrolsiiCAMinimumV = cfg.getInt(venkrolsiiName + " CA number", REINFORCEMENT_CATEGORY_BSII, venkrolsiiCAMinimumV, 0, 10000, "Number  for " + venkrolsiiName + " required to do a Collective Attack (only one will attack) for " + venkrolsiiName + ".");
        venkrolsiiCAExtraM = cfg.getFloat(venkrolsiiName + " CA mobs", REINFORCEMENT_CATEGORY_BSII, venkrolsiiCAExtraM, 0.0f, 100.0f, "Value of each " + venkrolsiiName + " to spawn extra mobs (the mobs will not add up to the total points) for " + venkrolsiiName + ".");
        venkrolsiiLoot = cfg.getStringList(venkrolsiiName + " Loot Table", REINFORCEMENT_CATEGORY_BSII, venkrolsiiLoot, "Items you want the " + venkrolsiiName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initbeckonSIIIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_BSIII, "Beckon Stage III \n Base Health: " + SPAttributes.VENKROLSIII_HEALTH + " \n Base Damage: " + SPAttributes.VENKROLSIII_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.VENKROLSIII_ARMOR);
        String venkrolsiiiName = "Stage III Beckon";
        String RSspawning = " Ex. \"ground;minecraft:zombie;0.1;1\"  Where: \n \"ground\" is for the entity type (ground, air) (air type spawning is triggered when the target y's value is higher than the host plus 3), \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn, \n \"1\" is for the cost the entity has. \n";
        venkrolsiiiHealthMultiplier = cfg.getFloat(venkrolsiiiName + health, REINFORCEMENT_CATEGORY_BSIII, 1.0f, 0.01f, 100.0f, "Health multiplier for " + venkrolsiiiName + ".");
        venkrolsiiiDamageMultiplier = cfg.getFloat(venkrolsiiiName + damage, REINFORCEMENT_CATEGORY_BSIII, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + venkrolsiiiName + ".");
        venkrolsiiiArmorMultiplier = cfg.getFloat(venkrolsiiiName + armor, REINFORCEMENT_CATEGORY_BSIII, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + venkrolsiiiName + ".");
        venkrolsiiiRange = cfg.getInt(venkrolsiiiName + " Block Infestation Range", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiRange, 0, 100, "Block infestation radius for " + venkrolsiiiName + ".");
        venkrolsiiiRangeY = cfg.getInt(venkrolsiiiName + " Block Infestation Range Y", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiRangeY, 0, 100, "Block infestation radius (upwards/downwards) for " + venkrolsiiiName + ".");
        venkrolsiiiCooldown = cfg.getInt(venkrolsiiiName + " Cooldown", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiCooldown, 0, 10000, "Summoning cooldown (in seconds) for " + venkrolsiiiName + ".");
        venkrolsiiilimit = cfg.getInt(venkrolsiiiName + " Limit", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiilimit, 0, 10000, "Number attacks before its cooldown for " + venkrolsiiiName + ".");
        venkrolsiiiTotalActiveMobs = cfg.getInt(venkrolsiiiName + " Total Active Mobs", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiTotalActiveMobs, 0, 100, "Number of total points used in mob spawning for " + venkrolsiiiName + ".");
        venkrolsiiimoblist = cfg.getStringList(venkrolsiiiName + " Mob List", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiimoblist, "Mob list for " + venkrolsiiiName + "." + RSspawning);
        venkrolsiiiCAMinimumV = cfg.getInt(venkrolsiiiName + " CA number", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiCAMinimumV, 0, 10000, "Number  for " + venkrolsiiiName + " required to do a Collective Attack (only one will attack) for " + venkrolsiiiName + ".");
        venkrolsiiiCAExtraM = cfg.getFloat(venkrolsiiiName + " CA mobs", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiCAExtraM, 0.0f, 100.0f, "Value of each " + venkrolsiiiName + " to spawn extra mobs (the mobs will not add up to the total points) for " + venkrolsiiiName + ".");
        venkrolsiiiLoot = cfg.getStringList(venkrolsiiiName + " Loot Table", REINFORCEMENT_CATEGORY_BSIII, venkrolsiiiLoot, "Items you want the " + venkrolsiiiName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initbeckonSIVConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_BSIV, "Beckon Stage IV \n Base Health: " + SPAttributes.VENKROLSIV_HEALTH + " \n Base Damage: " + SPAttributes.VENKROLSIV_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.VENKROLSIV_ARMOR);
        String venkrolsivName = "Stage IV Beckon";
        String RSspawning = " Ex. \"ground;minecraft:zombie;0.1;1\"  Where: \n \"ground\" is for the entity type (ground, air) (air type spawning is triggered when the target y's value is higher than the host plus 3), \n \"minecraft:zombie\" is for the entity, \n \"0.1\" is for the chance to spawn, \n \"1\" is for the cost the entity has. \n";
        venkrolsivHealthMultiplier = cfg.getFloat(venkrolsivName + health, REINFORCEMENT_CATEGORY_BSIV, 1.0f, 0.01f, 100.0f, "Health multiplier for " + venkrolsivName + ".");
        venkrolsivDamageMultiplier = cfg.getFloat(venkrolsivName + damage, REINFORCEMENT_CATEGORY_BSIV, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + venkrolsivName + ".");
        venkrolsivArmorMultiplier = cfg.getFloat(venkrolsivName + armor, REINFORCEMENT_CATEGORY_BSIV, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + venkrolsivName + ".");
        venkrolsivCooldown = cfg.getInt(venkrolsivName + " Cooldown", REINFORCEMENT_CATEGORY_BSIV, venkrolsivCooldown, 0, 10000, "Summoning cooldown (in seconds) for " + venkrolsivName + ".");
        venkrolsivlimit = cfg.getInt(venkrolsivName + " Limit", REINFORCEMENT_CATEGORY_BSIV, venkrolsivlimit, 0, 10000, "Number attacks before its cooldown for " + venkrolsivName + ".");
        venkrolsivTotalActiveMobs = cfg.getInt(venkrolsivName + " Total Active Mobs", REINFORCEMENT_CATEGORY_BSIV, venkrolsivTotalActiveMobs, 0, 100, "Number of total points used in mob spawning for " + venkrolsivName + ".");
        venkrolsivmoblist = cfg.getStringList(venkrolsivName + " Mob List", REINFORCEMENT_CATEGORY_BSIV, venkrolsivmoblist, "Mob list for " + venkrolsivName + "." + RSspawning);
        venkrolsivCAMinimumV = cfg.getInt(venkrolsivName + " CA number", REINFORCEMENT_CATEGORY_BSIV, venkrolsivCAMinimumV, 0, 10000, "Number  for " + venkrolsivName + " required to do a Collective Attack (only one will attack) for " + venkrolsivName + ".");
        venkrolsivCAExtraM = cfg.getFloat(venkrolsivName + " CA mobs", REINFORCEMENT_CATEGORY_BSIV, venkrolsivCAExtraM, 0.0f, 100.0f, "Value of each " + venkrolsivName + " to spawn extra mobs (the mobs will not add up to the total points) for " + venkrolsivName + ".");
        venkrolsivLoot = cfg.getStringList(venkrolsivName + " Loot Table", REINFORCEMENT_CATEGORY_BSIV, venkrolsivLoot, "Items you want the " + venkrolsivName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initreinforcerSIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RSI, "Dispatcher Stage I \n Base Health: " + SPAttributes.DOD_HEALTH + " \n Base Damage: " + SPAttributes.DOD_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.DOD_ARMOR);
        String dodsiName = "Stage I Dispatcher";
        String entry2 = " Ex. \"200;minecraft:speed;3\"  Where: \n \"200\" is the time in ticks, \n \"minecraft:speed\" is the potion itself (the potion will apply regardless of whether the parasite is near a node or not), \n \"3\" is the amplifier of the effect ";
        dodsiHealthMultiplier = cfg.getFloat(dodsiName + health, REINFORCEMENT_CATEGORY_RSI, 1.0f, 0.01f, 100.0f, "Health multiplier for " + dodsiName + ".");
        dodsiDamageMultiplier = cfg.getFloat(dodsiName + damage, REINFORCEMENT_CATEGORY_RSI, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + dodsiName + ".");
        dodsiArmorMultiplier = cfg.getFloat(dodsiName + armor, REINFORCEMENT_CATEGORY_RSI, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + dodsiName + ".");
        dodsiLoot = cfg.getStringList(dodsiName + " Loot Table", REINFORCEMENT_CATEGORY_RSI, dodsiLoot, "Items you want the " + dodsiName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        dodsiFollowRangeMult = cfg.getInt(dodsiName + " Extra Follow Range", REINFORCEMENT_CATEGORY_RSI, dodsiFollowRangeMult, 1, 100, "Follow range Multiplier for " + dodsiName + ".");
        dodsiEffects = cfg.getStringList(dodsiName + " Effect List", REINFORCEMENT_CATEGORY_RSI, dodsiEffects, "List of potion effects that the " + dodsiName + " will apply to relocated parasites." + entry2);
        dodsiTotalActiveMobs = cfg.getInt(dodsiName + " Total Active Mobs", REINFORCEMENT_CATEGORY_RSI, dodsiTotalActiveMobs, 0, 100, "Number of Seizers/Sentrys this Dispatcher can spawn.");
    }

    private static void initreinforcerSIIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RSII, "Dispatcher Stage II \n Base Health: " + SPAttributes.DODSII_HEALTH + " \n Base Damage: " + SPAttributes.DODSII_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.DODSII_ARMOR);
        String dodsiiName = "Stage II Dispatcher";
        String entry2 = " Ex. \"200;minecraft:speed;3\"  Where: \n \"200\" is the time in ticks, \n \"minecraft:speed\" is the potion itself (the potion will apply regardless of whether the parasite is near a node or not), \n \"3\" is the amplifier of the effect ";
        dodsiiHealthMultiplier = cfg.getFloat(dodsiiName + health, REINFORCEMENT_CATEGORY_RSII, 1.0f, 0.01f, 100.0f, "Health multiplier for " + dodsiiName + ".");
        dodsiiDamageMultiplier = cfg.getFloat(dodsiiName + damage, REINFORCEMENT_CATEGORY_RSII, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + dodsiiName + ".");
        dodsiiArmorMultiplier = cfg.getFloat(dodsiiName + armor, REINFORCEMENT_CATEGORY_RSII, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + dodsiiName + ".");
        dodsiiLoot = cfg.getStringList(dodsiiName + " Loot Table", REINFORCEMENT_CATEGORY_RSII, dodsiiLoot, "Items you want the " + dodsiiName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        dodsiiFollowRangeMult = cfg.getInt(dodsiiName + " Extra Follow Range", REINFORCEMENT_CATEGORY_RSII, dodsiiFollowRangeMult, 1, 100, "Follow range Multiplier for " + dodsiiName + ".");
        dodsiiEffects = cfg.getStringList(dodsiiName + " Effect List", REINFORCEMENT_CATEGORY_RSII, dodsiiEffects, "List of potion effects that the " + dodsiiName + " will apply to relocated parasites." + entry2);
        dodsiiTotalActiveMobs = cfg.getInt(dodsiiName + " Total Active Mobs", REINFORCEMENT_CATEGORY_RSII, dodsiiTotalActiveMobs, 0, 100, "Number of Seizers/Sentrys this Dispatcher can spawn.");
    }

    private static void initreinforcerSIIIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RSIII, "Dispatcher Stage III \n Base Health: " + SPAttributes.DODSIII_HEALTH + " \n Base Damage: " + SPAttributes.DODSIII_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.DODSIII_ARMOR);
        String dodsiiiName = "Stage III Dispatcher";
        String entry2 = " Ex. \"200;minecraft:speed;3\"  Where: \n \"200\" is the time in ticks, \n \"minecraft:speed\" is the potion itself (the potion will apply regardless of whether the parasite is near a node or not), \n \"3\" is the amplifier of the effect ";
        dodsiiiHealthMultiplier = cfg.getFloat(dodsiiiName + health, REINFORCEMENT_CATEGORY_RSIII, 1.0f, 0.01f, 100.0f, "Health multiplier for " + dodsiiiName + ".");
        dodsiiiDamageMultiplier = cfg.getFloat(dodsiiiName + damage, REINFORCEMENT_CATEGORY_RSIII, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + dodsiiiName + ".");
        dodsiiiArmorMultiplier = cfg.getFloat(dodsiiiName + armor, REINFORCEMENT_CATEGORY_RSIII, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + dodsiiiName + ".");
        dodsiiiLoot = cfg.getStringList(dodsiiiName + " Loot Table", REINFORCEMENT_CATEGORY_RSIII, dodsiiiLoot, "Items you want the " + dodsiiiName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        dodsiiiFollowRangeMult = cfg.getInt(dodsiiiName + " Extra Follow Range", REINFORCEMENT_CATEGORY_RSIII, dodsiiiFollowRangeMult, 1, 100, "Follow range Multiplier for " + dodsiiiName + ".");
        dodsiiiEffects = cfg.getStringList(dodsiiiName + " Effect List", REINFORCEMENT_CATEGORY_RSIII, dodsiiiEffects, "List of potion effects that the " + dodsiiiName + " will apply to relocated parasites." + entry2);
        dodsiiiTotalActiveMobs = cfg.getInt(dodsiiiName + " Total Active Mobs", REINFORCEMENT_CATEGORY_RSIII, dodsiiiTotalActiveMobs, 0, 100, "Number of Seizers/Sentrys this Dispatcher can spawn.");
    }

    private static void initreinforcerSIVConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RSIV, "Dispatcher Stage IV \n Base Health: " + SPAttributes.DODSIV_HEALTH + " \n Base Damage: " + SPAttributes.DODSIV_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.DODSIV_ARMOR);
        String dodsivName = "Stage IV Dispatcher";
        String entry2 = " Ex. \"200;minecraft:speed;3\"  Where: \n \"200\" is the time in ticks, \n \"minecraft:speed\" is the potion itself (the potion will apply regardless of whether the parasite is near a node or not), \n \"3\" is the amplifier of the effect ";
        dodsivHealthMultiplier = cfg.getFloat(dodsivName + health, REINFORCEMENT_CATEGORY_RSIV, 1.0f, 0.01f, 100.0f, "Health multiplier for " + dodsivName + ".");
        dodsivDamageMultiplier = cfg.getFloat(dodsivName + damage, REINFORCEMENT_CATEGORY_RSIV, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + dodsivName + ".");
        dodsivArmorMultiplier = cfg.getFloat(dodsivName + armor, REINFORCEMENT_CATEGORY_RSIV, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + dodsivName + ".");
        dodsivLoot = cfg.getStringList(dodsivName + " Loot Table", REINFORCEMENT_CATEGORY_RSIV, dodsivLoot, "Items you want the " + dodsivName + " to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
        dodsivFollowRangeMult = cfg.getInt(dodsivName + " Extra Follow Range", REINFORCEMENT_CATEGORY_RSIV, dodsivFollowRangeMult, 1, 100, "Follow range Multiplier for " + dodsivName + ".");
        dodsivEffects = cfg.getStringList(dodsivName + " Effect List", REINFORCEMENT_CATEGORY_RSIV, dodsivEffects, "List of potion effects that the " + dodsivName + " will apply to relocated parasites." + entry2);
        dodsivTotalActiveMobs = cfg.getInt(dodsivName + " Total Active Mobs", REINFORCEMENT_CATEGORY_RSIV, dodsivTotalActiveMobs, 0, 100, "Number of Seizers/Sentrys this Dispatcher can spawn.");
    }

    private static void initrooterSIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RTSI, "Rooter Stage I \n Base Health: " + SPAttributes.LEEM_HEALTH + " \n Base Damage: " + SPAttributes.LEEM_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LEEM_ARMOR);
        String leemName = "Stage I Rooter";
        leemHealthMultiplier = cfg.getFloat(leemName + health, REINFORCEMENT_CATEGORY_RTSI, 1.0f, 0.01f, 100.0f, "Health multiplier for " + leemName + ".");
        leemDamageMultiplier = cfg.getFloat(leemName + damage, REINFORCEMENT_CATEGORY_RTSI, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + leemName + ".");
        leemArmorMultiplier = cfg.getFloat(leemName + armor, REINFORCEMENT_CATEGORY_RTSI, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + leemName + ".");
        leemRange = cfg.getInt(leemName + " Tumor Range", REINFORCEMENT_CATEGORY_RTSI, leemRange, 0, 100, "Range in which tumors will appear for " + leemName + ".");
        leemRangeEffect = cfg.getInt(leemName + " Pivot Range", REINFORCEMENT_CATEGORY_RTSI, leemRangeEffect, 0, 100, "Pivot Range effect for " + leemName + ".");
        leemCooldown = cfg.getInt(leemName + " Tumor Cooldown", REINFORCEMENT_CATEGORY_RTSI, leemCooldown, 0, 10000, "Tumor summoning cooldown (in seconds) for " + leemName + ".");
        leemlimit = cfg.getInt(leemName + " Tumor Limit", REINFORCEMENT_CATEGORY_RTSI, leemlimit, 0, 10000, "Maximum number of Tumors this can spawn before cooldown.");
    }

    private static void initrooterSIIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RTSII, "Rooter Stage II \n Base Health: " + SPAttributes.LEEMSII_HEALTH + " \n Base Damage: " + SPAttributes.LEEMSII_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LEEMSII_ARMOR);
        String leemsiiName = "Stage II Rooter";
        leemsiiHealthMultiplier = cfg.getFloat(leemsiiName + health, REINFORCEMENT_CATEGORY_RTSII, 1.0f, 0.01f, 100.0f, "Health multiplier for " + leemsiiName + ".");
        leemsiiDamageMultiplier = cfg.getFloat(leemsiiName + damage, REINFORCEMENT_CATEGORY_RTSII, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + leemsiiName + ".");
        leemsiiArmorMultiplier = cfg.getFloat(leemsiiName + armor, REINFORCEMENT_CATEGORY_RTSII, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + leemsiiName + ".");
        leemsiiRange = cfg.getInt(leemsiiName + " Tumor Range", REINFORCEMENT_CATEGORY_RTSII, leemsiiRange, 0, 100, "Range in which tumors will appear for " + leemsiiName + ".");
        leemsiiRangeEffect = cfg.getInt(leemsiiName + " Pivot Range", REINFORCEMENT_CATEGORY_RTSII, leemsiiRangeEffect, 0, 100, "Pivot Range effect for " + leemsiiName + ".");
        leemsiiCooldown = cfg.getInt(leemsiiName + " Tumor Cooldown", REINFORCEMENT_CATEGORY_RTSII, leemsiiCooldown, 0, 10000, "Tumor summoning cooldown (in seconds) for " + leemsiiName + ".");
        leemsiilimit = cfg.getInt(leemsiiName + " Tumor Limit", REINFORCEMENT_CATEGORY_RTSII, leemsiilimit, 0, 10000, "Maximum number of Tumors this can spawn before cooldown.");
    }

    private static void initrooterSIIIConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RTSIII, "Rooter Stage III \n Base Health: " + SPAttributes.LEEMSIII_HEALTH + " \n Base Damage: " + SPAttributes.LEEMSIII_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LEEMSIII_ARMOR);
        String leemsiiiName = "Stage III Rooter";
        leemsiiiHealthMultiplier = cfg.getFloat(leemsiiiName + health, REINFORCEMENT_CATEGORY_RTSIII, 1.0f, 0.01f, 100.0f, "Health multiplier for " + leemsiiiName + ".");
        leemsiiiDamageMultiplier = cfg.getFloat(leemsiiiName + damage, REINFORCEMENT_CATEGORY_RTSIII, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + leemsiiiName + ".");
        leemsiiiArmorMultiplier = cfg.getFloat(leemsiiiName + armor, REINFORCEMENT_CATEGORY_RTSIII, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + leemsiiiName + ".");
        leemsiiiRange = cfg.getInt(leemsiiiName + " Tumor Range", REINFORCEMENT_CATEGORY_RTSIII, leemsiiiRange, 0, 100, "Range in which tumors will appear for " + leemsiiiName + ".");
        leemsiiiRangeEffect = cfg.getInt(leemsiiiName + " Pivot Range", REINFORCEMENT_CATEGORY_RTSIII, leemsiiiRangeEffect, 0, 100, "Pivot Range effect for " + leemsiiiName + ".");
        leemsiiiCooldown = cfg.getInt(leemsiiiName + " Tumor Cooldown", REINFORCEMENT_CATEGORY_RTSIII, leemsiiiCooldown, 0, 10000, "Tumor summoning cooldown (in seconds) for " + leemsiiiName + ".");
        leemsiiilimit = cfg.getInt(leemsiiiName + " Tumor Limit", REINFORCEMENT_CATEGORY_RTSIII, leemsiiilimit, 0, 10000, "Maximum number of Tumors this can spawn before cooldown.");
    }

    private static void initrooterSIVConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(REINFORCEMENT_CATEGORY_RTSIV, "Rooter Stage IV \n Base Health: " + SPAttributes.LEEMSIV_HEALTH + " \n Base Damage: " + SPAttributes.LEEMSIV_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.LEEMSIV_ARMOR);
        String leemsivName = "Stage IV Rooter";
        leemsivHealthMultiplier = cfg.getFloat(leemsivName + health, REINFORCEMENT_CATEGORY_RTSIV, 1.0f, 0.01f, 100.0f, "Health multiplier for " + leemsivName + ".");
        leemsivDamageMultiplier = cfg.getFloat(leemsivName + damage, REINFORCEMENT_CATEGORY_RTSIV, 1.0f, 0.01f, 100.0f, "Damage multiplier for " + leemsivName + ".");
        leemsivArmorMultiplier = cfg.getFloat(leemsivName + armor, REINFORCEMENT_CATEGORY_RTSIV, 1.0f, 0.01f, 100.0f, "Armor multiplier for " + leemsivName + ".");
        leemsivRange = cfg.getInt(leemsivName + " Tumor Range", REINFORCEMENT_CATEGORY_RTSIV, leemsivRange, 0, 100, "Range in which tumors will appear for " + leemsivName + ".");
        leemsivRangeEffect = cfg.getInt(leemsivName + " Pivot Range", REINFORCEMENT_CATEGORY_RTSIV, leemsivRangeEffect, 0, 100, "Pivot Range effect for " + leemsivName + ".");
        leemsivCooldown = cfg.getInt(leemsivName + " Tumor Cooldown", REINFORCEMENT_CATEGORY_RTSIV, leemsivCooldown, 0, 10000, "Tumor summoning cooldown (in seconds) for " + leemsivName + ".");
        leemsivlimit = cfg.getInt(leemsivName + " Tumor Limit", REINFORCEMENT_CATEGORY_RTSIV, leemsivlimit, 0, 10000, "Maximum number of Tumors this can spawn before cooldown.");
    }

    private static void inithebluConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(HEBLU_CATEGORY, "Draconite \n Base Health: " + SPAttributes.HEBLU_HEALTH + " \n Base Damage: " + SPAttributes.HEBLU_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.HEBLU_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.HEBLU_KD_RESISTANCE);
        hebluEnabled = cfg.getBoolean("Draconite Enabled", HEBLU_CATEGORY, hebluEnabled, "Set to false if you want to disable Draconite.");
        hebluHealthMultiplier = cfg.getFloat("Draconite" + health, HEBLU_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Draconite.");
        hebluDamageMultiplier = cfg.getFloat("Draconite" + damage, HEBLU_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Draconite.");
        hebluArmorMultiplier = cfg.getFloat("Draconite" + armor, HEBLU_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Draconite.");
        hebluKDResistanceMultiplier = cfg.getFloat("Draconite" + kd, HEBLU_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Draconite.");
        hebluSpawnRate = cfg.getInt("Draconite SpawnWeight", HEBLU_CATEGORY, hebluSpawnRate, 0, 100, "Spawn rate for Draconite (This value is ignored if Evolution Phases are enabled, it has its own option).");
        hebluLoot = cfg.getStringList("Draconite Loot Table", HEBLU_CATEGORY, hebluLoot, "Items you want the Draconite to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    private static void initkirinConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(KIRIN_CATEGORY, "Kirin \n Base Health: " + SPAttributes.KIRIN_HEALTH + " \n Base Damage: " + SPAttributes.KIRIN_ATTACK_DAMAGE + " \n Base Armor: " + SPAttributes.KIRIN_ARMOR + " \n Base Knockback Resistance: " + SPAttributes.KIRIN_KD_RESISTANCE);
        kirinEnabled = cfg.getBoolean("Kirin Enabled", KIRIN_CATEGORY, kirinEnabled, "Set to false if you want to disable Kirin.");
        kirinHealthMultiplier = cfg.getFloat("Kirin" + health, KIRIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Health multiplier for Kirin.");
        kirinDamageMultiplier = cfg.getFloat("Kirin" + damage, KIRIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Damage multiplier for Kirin.");
        kirinArmorMultiplier = cfg.getFloat("Kirin" + armor, KIRIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Armor multiplier for Kirin.");
        kirinKDResistanceMultiplier = cfg.getFloat("Kirin" + kd, KIRIN_CATEGORY, 1.0f, 0.01f, 100.0f, "Knockback Resistance multiplier for Kirin.");
        kirinSpawnRate = cfg.getInt("Kirin SpawnWeight", KIRIN_CATEGORY, kirinSpawnRate, 0, 100, "Spawn rate for Kirin (This value is ignored if Evolution Phases are enabled, it has its own option).");
        kirinLoot = cfg.getStringList("Kirin Loot Table", KIRIN_CATEGORY, kirinLoot, "Items you want the Kirin to drop. Ex. \"minecraft:nether_star;100;5;true\"" + lootXp);
    }

    public static void initConfig(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        CommonProxy.configMobs = new Configuration(new File(directory.getPath(), "subspaceparasite/SParasitesMobs.cfg"));
        SPConfigMobs.readConfig();
    }

    public static boolean readConfig() {
        Configuration cfgM = CommonProxy.configMobs;
        try {
            cfgM.load();
            SPConfigMobs.initGeneralMobsConfig(cfgM);
            SPConfigMobs.initShycoConfig(cfgM);
            SPConfigMobs.initemanaConfig(cfgM);
            SPConfigMobs.inithullConfig(cfgM);
            SPConfigMobs.initcanraConfig(cfgM);
            SPConfigMobs.initnoglaConfig(cfgM);
            SPConfigMobs.initzetmoConfig(cfgM);
            SPConfigMobs.initarachnidaConfig(cfgM);
            SPConfigMobs.initLumConfig(cfgM);
            SPConfigMobs.initikiConfig(cfgM);
            SPConfigMobs.initwymoConfig(cfgM);
            SPConfigMobs.initGimConfig(cfgM);
            SPConfigMobs.initZaaConfig(cfgM);
            SPConfigMobs.initlodoConfig(cfgM);
            SPConfigMobs.initratholConfig(cfgM);
            SPConfigMobs.initbutholConfig(cfgM);
            SPConfigMobs.initmudoConfig(cfgM);
            SPConfigMobs.initkolConfig(cfgM);
            SPConfigMobs.initnuuhConfig(cfgM);
            SPConfigMobs.initataConfig(cfgM);
            SPConfigMobs.initviinConfig(cfgM);
            SPConfigMobs.initinfbearConfig(cfgM);
            SPConfigMobs.initinfendermanConfig(cfgM);
            SPConfigMobs.initdorpaConfig(cfgM);
            SPConfigMobs.initinfhumanConfig(cfgM);
            SPConfigMobs.initinfsquidConfig(cfgM);
            SPConfigMobs.initinfcowConfig(cfgM);
            SPConfigMobs.initinfsheepConfig(cfgM);
            SPConfigMobs.initinfwolfConfig(cfgM);
            SPConfigMobs.initinfpigConfig(cfgM);
            SPConfigMobs.initinfvillagerConfig(cfgM);
            SPConfigMobs.initinfhorseConfig(cfgM);
            SPConfigMobs.initinfadventurerConfig(cfgM);
            SPConfigMobs.initINFDRAGONEConfig(cfgM);
            SPConfigMobs.initferbearConfig(cfgM);
            SPConfigMobs.initfercowConfig(cfgM);
            SPConfigMobs.initferendermanConfig(cfgM);
            SPConfigMobs.initferhorseConfig(cfgM);
            SPConfigMobs.initferhumanConfig(cfgM);
            SPConfigMobs.initferpigConfig(cfgM);
            SPConfigMobs.initfersheepConfig(cfgM);
            SPConfigMobs.initfervillagerConfig(cfgM);
            SPConfigMobs.initferwolfConfig(cfgM);
            SPConfigMobs.initmarbearConfig(cfgM);
            SPConfigMobs.initmarcowConfig(cfgM);
            SPConfigMobs.initmarendermanConfig(cfgM);
            SPConfigMobs.initmarhumanConfig(cfgM);
            SPConfigMobs.initmarvillagerConfig(cfgM);
            SPConfigMobs.inithiblazeConfig(cfgM);
            SPConfigMobs.inithigolemConfig(cfgM);
            SPConfigMobs.inithiskeletonConfig(cfgM);
            SPConfigMobs.initheedConfig(cfgM);
            SPConfigMobs.initcruxaConfig(cfgM);
            SPConfigMobs.inithostConfig(cfgM);
            SPConfigMobs.initherdConfig(cfgM);
            SPConfigMobs.initthrallConfig(cfgM);
            SPConfigMobs.initinhooSConfig(cfgM);
            SPConfigMobs.initinhooMConfig(cfgM);
            SPConfigMobs.initleerConfig(cfgM);
            SPConfigMobs.initquacConfig(cfgM);
            SPConfigMobs.initdoneConfig(cfgM);
            SPConfigMobs.initnakConfig(cfgM);
            SPConfigMobs.inittonroConfig(cfgM);
            SPConfigMobs.initunvoConfig(cfgM);
            SPConfigMobs.initbeckonSIConfig(cfgM);
            SPConfigMobs.initbeckonSIIConfig(cfgM);
            SPConfigMobs.initbeckonSIIIConfig(cfgM);
            SPConfigMobs.initbeckonSIVConfig(cfgM);
            SPConfigMobs.initreinforcerSIConfig(cfgM);
            SPConfigMobs.initreinforcerSIIConfig(cfgM);
            SPConfigMobs.initreinforcerSIIIConfig(cfgM);
            SPConfigMobs.initreinforcerSIVConfig(cfgM);
            SPConfigMobs.initrooterSIConfig(cfgM);
            SPConfigMobs.initrooterSIIConfig(cfgM);
            SPConfigMobs.initrooterSIIIConfig(cfgM);
            SPConfigMobs.initrooterSIVConfig(cfgM);
            SPConfigMobs.initoroncoConfig(cfgM);
            SPConfigMobs.initterlaConfig(cfgM);
            SPConfigMobs.initdroppodConfig(cfgM);
            SPConfigMobs.initalafhaConfig(cfgM);
            SPConfigMobs.initganroConfig(cfgM);
            SPConfigMobs.initangedConfig(cfgM);
            SPConfigMobs.initombooConfig(cfgM);
            SPConfigMobs.initesorConfig(cfgM);
            SPConfigMobs.initorchConfig(cfgM);
            SPConfigMobs.initflogConfig(cfgM);
            SPConfigMobs.initjinjoConfig(cfgM);
            SPConfigMobs.initflamConfig(cfgM);
            SPConfigMobs.initvestaConfig(cfgM);
            SPConfigMobs.initpheonConfig(cfgM);
            SPConfigMobs.initelviaConfig(cfgM);
            SPConfigMobs.initlenciaConfig(cfgM);
            SPConfigMobs.inithebluConfig(cfgM);
            SPConfigMobs.initkirinConfig(cfgM);
            boolean bl = true;
            return bl;
        }
        catch (Exception e) {
            SPMain.logger.log(Level.ERROR, "Problem loading configuration file", (Throwable)e);
            boolean bl = false;
            return bl;
        }
        finally {
            if (cfgM.hasChanged()) {
                cfgM.save();
            }
        }
    }
}

