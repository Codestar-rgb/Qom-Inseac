/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.registry.IRenderFactory
 *  net.minecraftforge.fml.client.registry.RenderingRegistry
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.client.SPProjectile;
import com.subspaceparasite.client.renderer.entity.HitboxNoRender;
import com.subspaceparasite.client.renderer.entity.RenderDamage;
import com.subspaceparasite.client.renderer.entity.RenderProjectileHomming;
import com.subspaceparasite.client.renderer.entity.RenderScent;
import com.subspaceparasite.client.renderer.entity.RenderSource;
import com.subspaceparasite.client.renderer.entity.RenderTCloud;
import com.subspaceparasite.client.renderer.entity.abomination.RenderAboFaces;
import com.subspaceparasite.client.renderer.entity.adapted.RenderBanoAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderCanraAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderEmanaAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderGimAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderHullAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderIkiAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderLumAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderNoglaAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderRanracAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderShycoAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderWymoAdapted;
import com.subspaceparasite.client.renderer.entity.adapted.RenderZaaAdapted;
import com.subspaceparasite.client.renderer.entity.ancient.RenderAncientPod;
import com.subspaceparasite.client.renderer.entity.ancient.RenderOronco;
import com.subspaceparasite.client.renderer.entity.ancient.RenderOroncoTen;
import com.subspaceparasite.client.renderer.entity.ancient.RenderTerla;
import com.subspaceparasite.client.renderer.entity.awakened.RenderOroncoAW;
import com.subspaceparasite.client.renderer.entity.crude.RenderCruxA;
import com.subspaceparasite.client.renderer.entity.crude.RenderCruxB;
import com.subspaceparasite.client.renderer.entity.crude.RenderDone;
import com.subspaceparasite.client.renderer.entity.crude.RenderHeed;
import com.subspaceparasite.client.renderer.entity.crude.RenderHost;
import com.subspaceparasite.client.renderer.entity.crude.RenderHostII;
import com.subspaceparasite.client.renderer.entity.crude.RenderInhooM;
import com.subspaceparasite.client.renderer.entity.crude.RenderInhooS;
import com.subspaceparasite.client.renderer.entity.crude.RenderLeer;
import com.subspaceparasite.client.renderer.entity.crude.RenderMes;
import com.subspaceparasite.client.renderer.entity.derived.RenderHeblu;
import com.subspaceparasite.client.renderer.entity.derived.RenderKirin;
import com.subspaceparasite.client.renderer.entity.deterrent.RenderDodT;
import com.subspaceparasite.client.renderer.entity.deterrent.RenderLeemB;
import com.subspaceparasite.client.renderer.entity.deterrent.RenderNak;
import com.subspaceparasite.client.renderer.entity.deterrent.RenderRof;
import com.subspaceparasite.client.renderer.entity.deterrent.RenderTonro;
import com.subspaceparasite.client.renderer.entity.deterrent.RenderUnvo;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderDod;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderDodSII;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderDodSIII;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderDodSIV;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderLeem;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderLeemSII;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderLeemSIII;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderLeemSIV;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderVenkrol;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderVenkrolSII;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderVenkrolSIII;
import com.subspaceparasite.client.renderer.entity.deterrent.nexus.RenderVenkrolSIV;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerBear;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerCow;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerEnderman;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerHorse;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerHuman;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerPig;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerSheep;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerVillager;
import com.subspaceparasite.client.renderer.entity.feral.RenderFerWolf;
import com.subspaceparasite.client.renderer.entity.hijacked.RenderHiBlaze;
import com.subspaceparasite.client.renderer.entity.hijacked.RenderHiGolem;
import com.subspaceparasite.client.renderer.entity.hijacked.RenderHiSkeleton;
import com.subspaceparasite.client.renderer.entity.inborn.RenderAta;
import com.subspaceparasite.client.renderer.entity.inborn.RenderButhol;
import com.subspaceparasite.client.renderer.entity.inborn.RenderGothol;
import com.subspaceparasite.client.renderer.entity.inborn.RenderKol;
import com.subspaceparasite.client.renderer.entity.inborn.RenderLesh;
import com.subspaceparasite.client.renderer.entity.inborn.RenderLodo;
import com.subspaceparasite.client.renderer.entity.inborn.RenderMudo;
import com.subspaceparasite.client.renderer.entity.inborn.RenderNuuh;
import com.subspaceparasite.client.renderer.entity.inborn.RenderRathol;
import com.subspaceparasite.client.renderer.entity.inborn.RenderViin;
import com.subspaceparasite.client.renderer.entity.infected.RenderDorpa;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfBear;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfCow;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfDragonE;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfEnderman;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfHorse;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfHuman;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfPig;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfPlayer;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfSheep;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfSquid;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfVillager;
import com.subspaceparasite.client.renderer.entity.infected.RenderInfWolf;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfCowHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfDragonEHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfEndermanHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfHorseHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfHumanHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfPigHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfPlayerHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfSheepHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfVillagerHead;
import com.subspaceparasite.client.renderer.entity.infected.head.RenderInfWolfHead;
import com.subspaceparasite.client.renderer.entity.infected.special.RenderSpeBear;
import com.subspaceparasite.client.renderer.entity.infected.special.RenderSpeCow;
import com.subspaceparasite.client.renderer.entity.infected.special.RenderSpeEnderman;
import com.subspaceparasite.client.renderer.entity.infected.special.RenderSpeHuman;
import com.subspaceparasite.client.renderer.entity.infected.special.RenderSpeSheep;
import com.subspaceparasite.client.renderer.entity.infected.special.RenderSpeVillager;
import com.subspaceparasite.client.renderer.entity.misc.RenderBiomass;
import com.subspaceparasite.client.renderer.entity.misc.RenderBomb;
import com.subspaceparasite.client.renderer.entity.misc.RenderEntityBody;
import com.subspaceparasite.client.renderer.entity.misc.RenderEntityBodyModel;
import com.subspaceparasite.client.renderer.entity.misc.RenderGore;
import com.subspaceparasite.client.renderer.entity.misc.RenderMeteor;
import com.subspaceparasite.client.renderer.entity.misc.RenderNade;
import com.subspaceparasite.client.renderer.entity.misc.RenderOrbBoom;
import com.subspaceparasite.client.renderer.entity.misc.RenderOrbScary;
import com.subspaceparasite.client.renderer.entity.misc.RenderOrbVoid;
import com.subspaceparasite.client.renderer.entity.misc.RenderRemain;
import com.subspaceparasite.client.renderer.entity.misc.RenderTendril;
import com.subspaceparasite.client.renderer.entity.misc.RenderWave;
import com.subspaceparasite.client.renderer.entity.misc.RenderWaveShock;
import com.subspaceparasite.client.renderer.entity.primitive.RenderBano;
import com.subspaceparasite.client.renderer.entity.primitive.RenderCanra;
import com.subspaceparasite.client.renderer.entity.primitive.RenderEmana;
import com.subspaceparasite.client.renderer.entity.primitive.RenderGim;
import com.subspaceparasite.client.renderer.entity.primitive.RenderHull;
import com.subspaceparasite.client.renderer.entity.primitive.RenderIki;
import com.subspaceparasite.client.renderer.entity.primitive.RenderLum;
import com.subspaceparasite.client.renderer.entity.primitive.RenderNogla;
import com.subspaceparasite.client.renderer.entity.primitive.RenderRanrac;
import com.subspaceparasite.client.renderer.entity.primitive.RenderShyco;
import com.subspaceparasite.client.renderer.entity.primitive.RenderWymo;
import com.subspaceparasite.client.renderer.entity.primitive.RenderZaa;
import com.subspaceparasite.client.renderer.entity.pure.RenderAlafha;
import com.subspaceparasite.client.renderer.entity.pure.RenderAnged;
import com.subspaceparasite.client.renderer.entity.pure.RenderEsor;
import com.subspaceparasite.client.renderer.entity.pure.RenderFlog;
import com.subspaceparasite.client.renderer.entity.pure.RenderGanro;
import com.subspaceparasite.client.renderer.entity.pure.RenderOmboo;
import com.subspaceparasite.client.renderer.entity.pure.RenderOrch;
import com.subspaceparasite.client.renderer.entity.pure.RenderRond;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderElvia;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderFlam;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderJinjo;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderLencia;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderPheon;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderTenn;
import com.subspaceparasite.client.renderer.entity.pure.preeminent.RenderVesta;
import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.EntityBodyModel;
import com.subspaceparasite.entity.EntityDamage;
import com.subspaceparasite.entity.EntityHitbox;
import com.subspaceparasite.entity.EntityOrbBoom;
import com.subspaceparasite.entity.EntityOrbScary;
import com.subspaceparasite.entity.EntityOrbVoid;
import com.subspaceparasite.entity.EntityParasiticScent;
import com.subspaceparasite.entity.EntityRemain;
import com.subspaceparasite.entity.EntitySource;
import com.subspaceparasite.entity.EntityToxicCloud;
import com.subspaceparasite.entity.monster.EntityBiomass;
import com.subspaceparasite.entity.monster.EntityTendril;
import com.subspaceparasite.entity.monster.EntityWave;
import com.subspaceparasite.entity.monster.EntityWaveShock;
import com.subspaceparasite.entity.monster.abomination.EntityAboBodies;
import com.subspaceparasite.entity.monster.adapted.EntityBanoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityCanraAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityEmanaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityGimAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityHullAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityIkiAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityLumAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityNoglaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityRanracAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityShycoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityWymoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityZaaAdapted;
import com.subspaceparasite.entity.monster.ancient.EntityOronco;
import com.subspaceparasite.entity.monster.ancient.EntityOroncoTen;
import com.subspaceparasite.entity.monster.ancient.EntityTerla;
import com.subspaceparasite.entity.monster.awakened.EntityOroncoAW;
import com.subspaceparasite.entity.monster.crude.EntityCruxA;
import com.subspaceparasite.entity.monster.crude.EntityCruxB;
import com.subspaceparasite.entity.monster.crude.EntityDone;
import com.subspaceparasite.entity.monster.crude.EntityHeed;
import com.subspaceparasite.entity.monster.crude.EntityHost;
import com.subspaceparasite.entity.monster.crude.EntityHostII;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.entity.monster.crude.EntityLeer;
import com.subspaceparasite.entity.monster.crude.EntityLesh;
import com.subspaceparasite.entity.monster.crude.EntityMes;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import com.subspaceparasite.entity.monster.derived.EntityKirin;
import com.subspaceparasite.entity.monster.deterrent.EntityDodT;
import com.subspaceparasite.entity.monster.deterrent.EntityLeemB;
import com.subspaceparasite.entity.monster.deterrent.EntityNak;
import com.subspaceparasite.entity.monster.deterrent.EntityRof;
import com.subspaceparasite.entity.monster.deterrent.EntityTonro;
import com.subspaceparasite.entity.monster.deterrent.EntityUnvo;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDod;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeem;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.subspaceparasite.entity.monster.feral.EntityFerBear;
import com.subspaceparasite.entity.monster.feral.EntityFerCow;
import com.subspaceparasite.entity.monster.feral.EntityFerEnderman;
import com.subspaceparasite.entity.monster.feral.EntityFerHorse;
import com.subspaceparasite.entity.monster.feral.EntityFerHuman;
import com.subspaceparasite.entity.monster.feral.EntityFerPig;
import com.subspaceparasite.entity.monster.feral.EntityFerSheep;
import com.subspaceparasite.entity.monster.feral.EntityFerVillager;
import com.subspaceparasite.entity.monster.feral.EntityFerWolf;
import com.subspaceparasite.entity.monster.hijacked.EntityHiBlaze;
import com.subspaceparasite.entity.monster.hijacked.EntityHiGolem;
import com.subspaceparasite.entity.monster.hijacked.EntityHiSkeleton;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import com.subspaceparasite.entity.monster.inborn.EntityButhol;
import com.subspaceparasite.entity.monster.inborn.EntityGothol;
import com.subspaceparasite.entity.monster.inborn.EntityKol;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import com.subspaceparasite.entity.monster.inborn.EntityMudo;
import com.subspaceparasite.entity.monster.inborn.EntityNuuh;
import com.subspaceparasite.entity.monster.inborn.EntityRathol;
import com.subspaceparasite.entity.monster.inborn.EntityViin;
import com.subspaceparasite.entity.monster.infected.EntityDorpa;
import com.subspaceparasite.entity.monster.infected.EntityInfBear;
import com.subspaceparasite.entity.monster.infected.EntityInfCow;
import com.subspaceparasite.entity.monster.infected.EntityInfDragonE;
import com.subspaceparasite.entity.monster.infected.EntityInfEnderman;
import com.subspaceparasite.entity.monster.infected.EntityInfHorse;
import com.subspaceparasite.entity.monster.infected.EntityInfHuman;
import com.subspaceparasite.entity.monster.infected.EntityInfPig;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import com.subspaceparasite.entity.monster.infected.EntityInfSheep;
import com.subspaceparasite.entity.monster.infected.EntityInfSquid;
import com.subspaceparasite.entity.monster.infected.EntityInfVillager;
import com.subspaceparasite.entity.monster.infected.EntityInfWolf;
import com.subspaceparasite.entity.monster.infected.head.EntityInfCowHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfDragonEHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfEndermanHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHorseHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHumanHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPigHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPlayerHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfSheepHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfVillagerHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfWolfHead;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeBear;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeCow;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeEnderman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeHuman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeSheep;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeVillager;
import com.subspaceparasite.entity.monster.primitive.EntityBano;
import com.subspaceparasite.entity.monster.primitive.EntityCanra;
import com.subspaceparasite.entity.monster.primitive.EntityEmana;
import com.subspaceparasite.entity.monster.primitive.EntityGim;
import com.subspaceparasite.entity.monster.primitive.EntityHull;
import com.subspaceparasite.entity.monster.primitive.EntityIki;
import com.subspaceparasite.entity.monster.primitive.EntityLum;
import com.subspaceparasite.entity.monster.primitive.EntityNogla;
import com.subspaceparasite.entity.monster.primitive.EntityRanrac;
import com.subspaceparasite.entity.monster.primitive.EntityShyco;
import com.subspaceparasite.entity.monster.primitive.EntityWymo;
import com.subspaceparasite.entity.monster.primitive.EntityZaa;
import com.subspaceparasite.entity.monster.pure.EntityAlafha;
import com.subspaceparasite.entity.monster.pure.EntityAnged;
import com.subspaceparasite.entity.monster.pure.EntityEsor;
import com.subspaceparasite.entity.monster.pure.EntityFlog;
import com.subspaceparasite.entity.monster.pure.EntityGanro;
import com.subspaceparasite.entity.monster.pure.EntityOmboo;
import com.subspaceparasite.entity.monster.pure.EntityOrch;
import com.subspaceparasite.entity.monster.pure.EntityRond;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityElvia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityFlam;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityJinjo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityLencia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityPheon;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityTenn;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityVesta;
import com.subspaceparasite.entity.projectile.EntityBomb;
import com.subspaceparasite.entity.projectile.EntityDropPod;
import com.subspaceparasite.entity.projectile.EntityGore;
import com.subspaceparasite.entity.projectile.EntityMeteor;
import com.subspaceparasite.entity.projectile.EntityNade;
import com.subspaceparasite.entity.projectile.EntityProjectileAlafhaBall;
import com.subspaceparasite.entity.projectile.EntityProjectileAncientball;
import com.subspaceparasite.entity.projectile.EntityProjectileAngedball;
import com.subspaceparasite.entity.projectile.EntityProjectileBiomass;
import com.subspaceparasite.entity.projectile.EntityProjectileDragonE;
import com.subspaceparasite.entity.projectile.EntityProjectileEffects;
import com.subspaceparasite.entity.projectile.EntityProjectileElviaBall;
import com.subspaceparasite.entity.projectile.EntityProjectileHomming;
import com.subspaceparasite.entity.projectile.EntityProjectileLenciaBall;
import com.subspaceparasite.entity.projectile.EntityProjectileNade;
import com.subspaceparasite.entity.projectile.EntityProjectilePullball;
import com.subspaceparasite.entity.projectile.EntityProjectileSpineball;
import com.subspaceparasite.entity.projectile.EntityProjectileWebball;
import com.subspaceparasite.entity.projectile.EntityThrowableAntiInfestedBlock;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class SPRenderHandler {
    public static void registryEntityRenders() {
        if (!SPConfig.allowMobs) {
            return;
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityKirin.class, (IRenderFactory)new IRenderFactory<EntityKirin>(){

            public Render<? super EntityKirin> createRenderFor(RenderManager manager) {
                return new RenderKirin(manager);
            }
        });
        if (SPConfigMobs.hebluEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHeblu.class, (IRenderFactory)new IRenderFactory<EntityHeblu>(){

                public Render<? super EntityHeblu> createRenderFor(RenderManager manager) {
                    return new RenderHeblu(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityOroncoAW.class, (IRenderFactory)new IRenderFactory<EntityOroncoAW>(){

            public Render<? super EntityOroncoAW> createRenderFor(RenderManager manager) {
                return new RenderOroncoAW(manager);
            }
        });
        if (SPConfigMobs.ratholEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityRathol.class, (IRenderFactory)new IRenderFactory<EntityRathol>(){

                public Render<? super EntityRathol> createRenderFor(RenderManager manager) {
                    return new RenderRathol(manager);
                }
            });
        }
        if (SPConfigMobs.gotholEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityGothol.class, (IRenderFactory)new IRenderFactory<EntityGothol>(){

                public Render<? super EntityGothol> createRenderFor(RenderManager manager) {
                    return new RenderGothol(manager);
                }
            });
        }
        if (SPConfigMobs.lodoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLodo.class, (IRenderFactory)new IRenderFactory<EntityLodo>(){

                public Render<? super EntityLodo> createRenderFor(RenderManager manager) {
                    return new RenderLodo(manager);
                }
            });
        }
        if (SPConfigMobs.butholEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityButhol.class, (IRenderFactory)new IRenderFactory<EntityButhol>(){

                public Render<? super EntityButhol> createRenderFor(RenderManager manager) {
                    return new RenderButhol(manager);
                }
            });
        }
        if (SPConfigMobs.mudoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMudo.class, (IRenderFactory)new IRenderFactory<EntityMudo>(){

                public Render<? super EntityMudo> createRenderFor(RenderManager manager) {
                    return new RenderMudo(manager);
                }
            });
        }
        if (SPConfigMobs.ataEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityAta.class, (IRenderFactory)new IRenderFactory<EntityAta>(){

                public Render<? super EntityAta> createRenderFor(RenderManager manager) {
                    return new RenderAta(manager);
                }
            });
        }
        if (SPConfigMobs.ataEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityViin.class, (IRenderFactory)new IRenderFactory<EntityViin>(){

                public Render<? super EntityViin> createRenderFor(RenderManager manager) {
                    return new RenderViin(manager);
                }
            });
        }
        if (SPConfigMobs.nuuhEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityNuuh.class, (IRenderFactory)new IRenderFactory<EntityNuuh>(){

                public Render<? super EntityNuuh> createRenderFor(RenderManager manager) {
                    return new RenderNuuh(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityVenkrol.class, (IRenderFactory)new IRenderFactory<EntityVenkrol>(){

            public Render<? super EntityVenkrol> createRenderFor(RenderManager manager) {
                return new RenderVenkrol(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityVenkrolSII.class, (IRenderFactory)new IRenderFactory<EntityVenkrolSII>(){

            public Render<? super EntityVenkrolSII> createRenderFor(RenderManager manager) {
                return new RenderVenkrolSII(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityVenkrolSIII.class, (IRenderFactory)new IRenderFactory<EntityVenkrolSIII>(){

            public Render<? super EntityVenkrolSIII> createRenderFor(RenderManager manager) {
                return new RenderVenkrolSIII(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityVenkrolSIV.class, (IRenderFactory)new IRenderFactory<EntityVenkrolSIV>(){

            public Render<? super EntityVenkrolSIV> createRenderFor(RenderManager manager) {
                return new RenderVenkrolSIV(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDodT.class, (IRenderFactory)new IRenderFactory<EntityDodT>(){

            public Render<? super EntityDodT> createRenderFor(RenderManager manager) {
                return new RenderDodT(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLeemB.class, (IRenderFactory)new IRenderFactory<EntityLeemB>(){

            public Render<? super EntityLeemB> createRenderFor(RenderManager manager) {
                return new RenderLeemB(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityRof.class, (IRenderFactory)new IRenderFactory<EntityRof>(){

            public Render<? super EntityRof> createRenderFor(RenderManager manager) {
                return new RenderRof(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDod.class, (IRenderFactory)new IRenderFactory<EntityDod>(){

            public Render<? super EntityDod> createRenderFor(RenderManager manager) {
                return new RenderDod(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDodSII.class, (IRenderFactory)new IRenderFactory<EntityDodSII>(){

            public Render<? super EntityDodSII> createRenderFor(RenderManager manager) {
                return new RenderDodSII(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDodSIII.class, (IRenderFactory)new IRenderFactory<EntityDodSIII>(){

            public Render<? super EntityDodSIII> createRenderFor(RenderManager manager) {
                return new RenderDodSIII(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDodSIV.class, (IRenderFactory)new IRenderFactory<EntityDodSIV>(){

            public Render<? super EntityDodSIV> createRenderFor(RenderManager manager) {
                return new RenderDodSIV(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLeem.class, (IRenderFactory)new IRenderFactory<EntityLeem>(){

            public Render<? super EntityLeem> createRenderFor(RenderManager manager) {
                return new RenderLeem(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLeemSII.class, (IRenderFactory)new IRenderFactory<EntityLeemSII>(){

            public Render<? super EntityLeemSII> createRenderFor(RenderManager manager) {
                return new RenderLeemSII(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLeemSIII.class, (IRenderFactory)new IRenderFactory<EntityLeemSIII>(){

            public Render<? super EntityLeemSIII> createRenderFor(RenderManager manager) {
                return new RenderLeemSIII(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLeemSIV.class, (IRenderFactory)new IRenderFactory<EntityLeemSIV>(){

            public Render<? super EntityLeemSIV> createRenderFor(RenderManager manager) {
                return new RenderLeemSIV(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLesh.class, (IRenderFactory)new IRenderFactory<EntityLesh>(){

            public Render<? super EntityLesh> createRenderFor(RenderManager manager) {
                return new RenderLesh(manager);
            }
        });
        if (SPConfigMobs.tonroEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityTonro.class, (IRenderFactory)new IRenderFactory<EntityTonro>(){

                public Render<? super EntityTonro> createRenderFor(RenderManager manager) {
                    return new RenderTonro(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityNak.class, (IRenderFactory)new IRenderFactory<EntityNak>(){

            public Render<? super EntityNak> createRenderFor(RenderManager manager) {
                return new RenderNak(manager);
            }
        });
        if (SPConfigMobs.unvoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityUnvo.class, (IRenderFactory)new IRenderFactory<EntityUnvo>(){

                public Render<? super EntityUnvo> createRenderFor(RenderManager manager) {
                    return new RenderUnvo(manager);
                }
            });
        }
        if (SPConfigMobs.kolEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityKol.class, (IRenderFactory)new IRenderFactory<EntityKol>(){

                public Render<? super EntityKol> createRenderFor(RenderManager manager) {
                    return new RenderKol(manager);
                }
            });
        }
        if (SPConfigMobs.terlaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityTerla.class, (IRenderFactory)new IRenderFactory<EntityTerla>(){

                public Render<? super EntityTerla> createRenderFor(RenderManager manager) {
                    return new RenderTerla(manager);
                }
            });
        }
        if (SPConfigMobs.oroncoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOronco.class, (IRenderFactory)new IRenderFactory<EntityOronco>(){

                public Render<? super EntityOronco> createRenderFor(RenderManager manager) {
                    return new RenderOronco(manager);
                }
            });
        }
        if (SPConfigMobs.oroncoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOroncoTen.class, (IRenderFactory)new IRenderFactory<EntityOroncoTen>(){

                public Render<? super EntityOroncoTen> createRenderFor(RenderManager manager) {
                    return new RenderOroncoTen(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityDropPod.class, (IRenderFactory)new IRenderFactory<EntityDropPod>(){

            public Render<? super EntityDropPod> createRenderFor(RenderManager manager) {
                return new RenderAncientPod(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityAboBodies.class, (IRenderFactory)new IRenderFactory<EntityAboBodies>(){

            public Render<? super EntityAboBodies> createRenderFor(RenderManager manager) {
                return new RenderAboFaces(manager);
            }
        });
        if (SPConfigMobs.leerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLeer.class, (IRenderFactory)new IRenderFactory<EntityLeer>(){

                public Render<? super EntityLeer> createRenderFor(RenderManager manager) {
                    return new RenderLeer(manager);
                }
            });
        }
        if (SPConfigMobs.doneEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityDone.class, (IRenderFactory)new IRenderFactory<EntityDone>(){

                public Render<? super EntityDone> createRenderFor(RenderManager manager) {
                    return new RenderDone(manager);
                }
            });
        }
        if (SPConfigMobs.hostEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHost.class, (IRenderFactory)new IRenderFactory<EntityHost>(){

                public Render<? super EntityHost> createRenderFor(RenderManager manager) {
                    return new RenderHost(manager);
                }
            });
        }
        if (SPConfigMobs.hostEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHostII.class, (IRenderFactory)new IRenderFactory<EntityHostII>(){

                public Render<? super EntityHostII> createRenderFor(RenderManager manager) {
                    return new RenderHostII(manager);
                }
            });
        }
        if (SPConfigMobs.thrallEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMes.class, (IRenderFactory)new IRenderFactory<EntityMes>(){

                public Render<? super EntityMes> createRenderFor(RenderManager manager) {
                    return new RenderMes(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityInhooS.class, (IRenderFactory)new IRenderFactory<EntityInhooS>(){

            public Render<? super EntityInhooS> createRenderFor(RenderManager manager) {
                return new RenderInhooS(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityInhooM.class, (IRenderFactory)new IRenderFactory<EntityInhooM>(){

            public Render<? super EntityInhooM> createRenderFor(RenderManager manager) {
                return new RenderInhooM(manager);
            }
        });
        if (SPConfigMobs.dorpaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityDorpa.class, (IRenderFactory)new IRenderFactory<EntityDorpa>(){

                public Render<? super EntityDorpa> createRenderFor(RenderManager manager) {
                    return new RenderDorpa(manager);
                }
            });
        }
        if (SPConfigMobs.infendermanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfEnderman.class, (IRenderFactory)new IRenderFactory<EntityInfEnderman>(){

                public Render<? super EntityInfEnderman> createRenderFor(RenderManager manager) {
                    return new RenderInfEnderman(manager);
                }
            });
        }
        if (SPConfigMobs.infendermanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfEndermanHead.class, (IRenderFactory)new IRenderFactory<EntityInfEndermanHead>(){

                public Render<? super EntityInfEndermanHead> createRenderFor(RenderManager manager) {
                    return new RenderInfEndermanHead(manager);
                }
            });
        }
        if (SPConfigMobs.infhumanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHuman.class, (IRenderFactory)new IRenderFactory<EntityInfHuman>(){

                public Render<? super EntityInfHuman> createRenderFor(RenderManager manager) {
                    return new RenderInfHuman(manager);
                }
            });
        }
        if (SPConfigMobs.infhumanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHumanHead.class, (IRenderFactory)new IRenderFactory<EntityInfHumanHead>(){

                public Render<? super EntityInfHumanHead> createRenderFor(RenderManager manager) {
                    return new RenderInfHumanHead(manager);
                }
            });
        }
        if (SPConfigMobs.infcowEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfCow.class, (IRenderFactory)new IRenderFactory<EntityInfCow>(){

                public Render<? super EntityInfCow> createRenderFor(RenderManager manager) {
                    return new RenderInfCow(manager);
                }
            });
        }
        if (SPConfigMobs.infsquidEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfSquid.class, (IRenderFactory)new IRenderFactory<EntityInfSquid>(){

                public Render<? super EntityInfSquid> createRenderFor(RenderManager manager) {
                    return new RenderInfSquid(manager);
                }
            });
        }
        if (SPConfigMobs.infcowEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfCowHead.class, (IRenderFactory)new IRenderFactory<EntityInfCowHead>(){

                public Render<? super EntityInfCowHead> createRenderFor(RenderManager manager) {
                    return new RenderInfCowHead(manager);
                }
            });
        }
        if (SPConfigMobs.infsheepEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfSheep.class, (IRenderFactory)new IRenderFactory<EntityInfSheep>(){

                public Render<? super EntityInfSheep> createRenderFor(RenderManager manager) {
                    return new RenderInfSheep(manager);
                }
            });
        }
        if (SPConfigMobs.infsheepEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfSheepHead.class, (IRenderFactory)new IRenderFactory<EntityInfSheepHead>(){

                public Render<? super EntityInfSheepHead> createRenderFor(RenderManager manager) {
                    return new RenderInfSheepHead(manager);
                }
            });
        }
        if (SPConfigMobs.infwolfEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfWolf.class, (IRenderFactory)new IRenderFactory<EntityInfWolf>(){

                public Render<? super EntityInfWolf> createRenderFor(RenderManager manager) {
                    return new RenderInfWolf(manager);
                }
            });
        }
        if (SPConfigMobs.infwolfEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfWolfHead.class, (IRenderFactory)new IRenderFactory<EntityInfWolfHead>(){

                public Render<? super EntityInfWolfHead> createRenderFor(RenderManager manager) {
                    return new RenderInfWolfHead(manager);
                }
            });
        }
        if (SPConfigMobs.infpigEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPig.class, (IRenderFactory)new IRenderFactory<EntityInfPig>(){

                public Render<? super EntityInfPig> createRenderFor(RenderManager manager) {
                    return new RenderInfPig(manager);
                }
            });
        }
        if (SPConfigMobs.infpigEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPigHead.class, (IRenderFactory)new IRenderFactory<EntityInfPigHead>(){

                public Render<? super EntityInfPigHead> createRenderFor(RenderManager manager) {
                    return new RenderInfPigHead(manager);
                }
            });
        }
        if (SPConfigMobs.infvillagerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfVillager.class, (IRenderFactory)new IRenderFactory<EntityInfVillager>(){

                public Render<? super EntityInfVillager> createRenderFor(RenderManager manager) {
                    return new RenderInfVillager(manager);
                }
            });
        }
        if (SPConfigMobs.infvillagerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfVillagerHead.class, (IRenderFactory)new IRenderFactory<EntityInfVillagerHead>(){

                public Render<? super EntityInfVillagerHead> createRenderFor(RenderManager manager) {
                    return new RenderInfVillagerHead(manager);
                }
            });
        }
        if (SPConfigMobs.infhorseEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHorse.class, (IRenderFactory)new IRenderFactory<EntityInfHorse>(){

                public Render<? super EntityInfHorse> createRenderFor(RenderManager manager) {
                    return new RenderInfHorse(manager);
                }
            });
        }
        if (SPConfigMobs.infhorseEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHorseHead.class, (IRenderFactory)new IRenderFactory<EntityInfHorseHead>(){

                public Render<? super EntityInfHorseHead> createRenderFor(RenderManager manager) {
                    return new RenderInfHorseHead(manager);
                }
            });
        }
        if (SPConfigMobs.infadventurerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPlayer.class, (IRenderFactory)new IRenderFactory<EntityInfPlayer>(){

                public Render<? super EntityInfPlayer> createRenderFor(RenderManager manager) {
                    return new RenderInfPlayer(manager);
                }
            });
        }
        if (SPConfigMobs.infadventurerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPlayerHead.class, (IRenderFactory)new IRenderFactory<EntityInfPlayerHead>(){

                public Render<? super EntityInfPlayerHead> createRenderFor(RenderManager manager) {
                    return new RenderInfPlayerHead(manager);
                }
            });
        }
        if (SPConfigMobs.infbearEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfBear.class, (IRenderFactory)new IRenderFactory<EntityInfBear>(){

                public Render<? super EntityInfBear> createRenderFor(RenderManager manager) {
                    return new RenderInfBear(manager);
                }
            });
        }
        if (SPConfigMobs.infdragoneEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfDragonE.class, (IRenderFactory)new IRenderFactory<EntityInfDragonE>(){

                public Render<? super EntityInfDragonE> createRenderFor(RenderManager manager) {
                    return new RenderInfDragonE(manager);
                }
            });
        }
        if (SPConfigMobs.infdragoneEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfDragonEHead.class, (IRenderFactory)new IRenderFactory<EntityInfDragonEHead>(){

                public Render<? super EntityInfDragonEHead> createRenderFor(RenderManager manager) {
                    return new RenderInfDragonEHead(manager);
                }
            });
        }
        if (SPConfigMobs.ferbearEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerBear.class, (IRenderFactory)new IRenderFactory<EntityFerBear>(){

                public Render<? super EntityFerBear> createRenderFor(RenderManager manager) {
                    return new RenderFerBear(manager);
                }
            });
        }
        if (SPConfigMobs.fercowEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerCow.class, (IRenderFactory)new IRenderFactory<EntityFerCow>(){

                public Render<? super EntityFerCow> createRenderFor(RenderManager manager) {
                    return new RenderFerCow(manager);
                }
            });
        }
        if (SPConfigMobs.ferendermanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerEnderman.class, (IRenderFactory)new IRenderFactory<EntityFerEnderman>(){

                public Render<? super EntityFerEnderman> createRenderFor(RenderManager manager) {
                    return new RenderFerEnderman(manager);
                }
            });
        }
        if (SPConfigMobs.ferhorseEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerHorse.class, (IRenderFactory)new IRenderFactory<EntityFerHorse>(){

                public Render<? super EntityFerHorse> createRenderFor(RenderManager manager) {
                    return new RenderFerHorse(manager);
                }
            });
        }
        if (SPConfigMobs.fervillagerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerVillager.class, (IRenderFactory)new IRenderFactory<EntityFerVillager>(){

                public Render<? super EntityFerVillager> createRenderFor(RenderManager manager) {
                    return new RenderFerVillager(manager);
                }
            });
        }
        if (SPConfigMobs.ferhumanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerHuman.class, (IRenderFactory)new IRenderFactory<EntityFerHuman>(){

                public Render<? super EntityFerHuman> createRenderFor(RenderManager manager) {
                    return new RenderFerHuman(manager);
                }
            });
        }
        if (SPConfigMobs.fersheepEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerSheep.class, (IRenderFactory)new IRenderFactory<EntityFerSheep>(){

                public Render<? super EntityFerSheep> createRenderFor(RenderManager manager) {
                    return new RenderFerSheep(manager);
                }
            });
        }
        if (SPConfigMobs.ferpigEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerPig.class, (IRenderFactory)new IRenderFactory<EntityFerPig>(){

                public Render<? super EntityFerPig> createRenderFor(RenderManager manager) {
                    return new RenderFerPig(manager);
                }
            });
        }
        if (SPConfigMobs.ferwolfEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerWolf.class, (IRenderFactory)new IRenderFactory<EntityFerWolf>(){

                public Render<? super EntityFerWolf> createRenderFor(RenderManager manager) {
                    return new RenderFerWolf(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntitySpeVillager.class, (IRenderFactory)new IRenderFactory<EntitySpeVillager>(){

            public Render<? super EntitySpeVillager> createRenderFor(RenderManager manager) {
                return new RenderSpeVillager(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySpeHuman.class, (IRenderFactory)new IRenderFactory<EntitySpeHuman>(){

            public Render<? super EntitySpeHuman> createRenderFor(RenderManager manager) {
                return new RenderSpeHuman(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySpeCow.class, (IRenderFactory)new IRenderFactory<EntitySpeCow>(){

            public Render<? super EntitySpeCow> createRenderFor(RenderManager manager) {
                return new RenderSpeCow(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySpeEnderman.class, (IRenderFactory)new IRenderFactory<EntitySpeEnderman>(){

            public Render<? super EntitySpeEnderman> createRenderFor(RenderManager manager) {
                return new RenderSpeEnderman(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySpeSheep.class, (IRenderFactory)new IRenderFactory<EntitySpeSheep>(){

            public Render<? super EntitySpeSheep> createRenderFor(RenderManager manager) {
                return new RenderSpeSheep(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySpeBear.class, (IRenderFactory)new IRenderFactory<EntitySpeBear>(){

            public Render<? super EntitySpeBear> createRenderFor(RenderManager manager) {
                return new RenderSpeBear(manager);
            }
        });
        if (SPConfigMobs.higolemEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHiGolem.class, (IRenderFactory)new IRenderFactory<EntityHiGolem>(){

                public Render<? super EntityHiGolem> createRenderFor(RenderManager manager) {
                    return new RenderHiGolem(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityHiSkeleton.class, (IRenderFactory)new IRenderFactory<EntityHiSkeleton>(){

            public Render<? super EntityHiSkeleton> createRenderFor(RenderManager manager) {
                return new RenderHiSkeleton(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityHiBlaze.class, (IRenderFactory)new IRenderFactory<EntityHiBlaze>(){

            public Render<? super EntityHiBlaze> createRenderFor(RenderManager manager) {
                return new RenderHiBlaze(manager);
            }
        });
        if (SPConfigMobs.emanaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityEmana.class, (IRenderFactory)new IRenderFactory<EntityEmana>(){

                public Render<? super EntityEmana> createRenderFor(RenderManager manager) {
                    return new RenderEmana(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityEmanaAdapted.class, (IRenderFactory)new IRenderFactory<EntityEmanaAdapted>(){

                public Render<? super EntityEmanaAdapted> createRenderFor(RenderManager manager) {
                    return new RenderEmanaAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.lumEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLum.class, (IRenderFactory)new IRenderFactory<EntityLum>(){

                public Render<? super EntityLum> createRenderFor(RenderManager manager) {
                    return new RenderLum(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityLumAdapted.class, (IRenderFactory)new IRenderFactory<EntityLumAdapted>(){

                public Render<? super EntityLumAdapted> createRenderFor(RenderManager manager) {
                    return new RenderLumAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.hullEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHull.class, (IRenderFactory)new IRenderFactory<EntityHull>(){

                public Render<? super EntityHull> createRenderFor(RenderManager manager) {
                    return new RenderHull(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityHullAdapted.class, (IRenderFactory)new IRenderFactory<EntityHullAdapted>(){

                public Render<? super EntityHullAdapted> createRenderFor(RenderManager manager) {
                    return new RenderHullAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.canraEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCanra.class, (IRenderFactory)new IRenderFactory<EntityCanra>(){

                public Render<? super EntityCanra> createRenderFor(RenderManager manager) {
                    return new RenderCanra(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityCanraAdapted.class, (IRenderFactory)new IRenderFactory<EntityCanraAdapted>(){

                public Render<? super EntityCanraAdapted> createRenderFor(RenderManager manager) {
                    return new RenderCanraAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.noglaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityNogla.class, (IRenderFactory)new IRenderFactory<EntityNogla>(){

                public Render<? super EntityNogla> createRenderFor(RenderManager manager) {
                    return new RenderNogla(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityNoglaAdapted.class, (IRenderFactory)new IRenderFactory<EntityNoglaAdapted>(){

                public Render<? super EntityNoglaAdapted> createRenderFor(RenderManager manager) {
                    return new RenderNoglaAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.zetmoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityBano.class, (IRenderFactory)new IRenderFactory<EntityBano>(){

                public Render<? super EntityBano> createRenderFor(RenderManager manager) {
                    return new RenderBano(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityBanoAdapted.class, (IRenderFactory)new IRenderFactory<EntityBanoAdapted>(){

                public Render<? super EntityBanoAdapted> createRenderFor(RenderManager manager) {
                    return new RenderBanoAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.shycoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityShyco.class, (IRenderFactory)new IRenderFactory<EntityShyco>(){

                public Render<? super EntityShyco> createRenderFor(RenderManager manager) {
                    return new RenderShyco(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityShycoAdapted.class, (IRenderFactory)new IRenderFactory<EntityShycoAdapted>(){

                public Render<? super EntityShycoAdapted> createRenderFor(RenderManager manager) {
                    return new RenderShycoAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.wymoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityWymo.class, (IRenderFactory)new IRenderFactory<EntityWymo>(){

                public Render<? super EntityWymo> createRenderFor(RenderManager manager) {
                    return new RenderWymo(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityWymoAdapted.class, (IRenderFactory)new IRenderFactory<EntityWymoAdapted>(){

                public Render<? super EntityWymoAdapted> createRenderFor(RenderManager manager) {
                    return new RenderWymoAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.ikiEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityIki.class, (IRenderFactory)new IRenderFactory<EntityIki>(){

                public Render<? super EntityIki> createRenderFor(RenderManager manager) {
                    return new RenderIki(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityIkiAdapted.class, (IRenderFactory)new IRenderFactory<EntityIkiAdapted>(){

                public Render<? super EntityIkiAdapted> createRenderFor(RenderManager manager) {
                    return new RenderIkiAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.arachnidaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityRanrac.class, (IRenderFactory)new IRenderFactory<EntityRanrac>(){

                public Render<? super EntityRanrac> createRenderFor(RenderManager manager) {
                    return new RenderRanrac(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityRanracAdapted.class, (IRenderFactory)new IRenderFactory<EntityRanracAdapted>(){

                public Render<? super EntityRanracAdapted> createRenderFor(RenderManager manager) {
                    return new RenderRanracAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.zaaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityZaa.class, (IRenderFactory)new IRenderFactory<EntityZaa>(){

                public Render<? super EntityZaa> createRenderFor(RenderManager manager) {
                    return new RenderZaa(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityZaaAdapted.class, (IRenderFactory)new IRenderFactory<EntityZaaAdapted>(){

                public Render<? super EntityZaaAdapted> createRenderFor(RenderManager manager) {
                    return new RenderZaaAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.gimEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityGim.class, (IRenderFactory)new IRenderFactory<EntityGim>(){

                public Render<? super EntityGim> createRenderFor(RenderManager manager) {
                    return new RenderGim(manager);
                }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityGimAdapted.class, (IRenderFactory)new IRenderFactory<EntityGimAdapted>(){

                public Render<? super EntityGimAdapted> createRenderFor(RenderManager manager) {
                    return new RenderGimAdapted(manager);
                }
            });
        }
        if (SPConfigMobs.alafhaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityAlafha.class, (IRenderFactory)new IRenderFactory<EntityAlafha>(){

                public Render<? super EntityAlafha> createRenderFor(RenderManager manager) {
                    return new RenderAlafha(manager);
                }
            });
        }
        if (SPConfigMobs.ganroEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityGanro.class, (IRenderFactory)new IRenderFactory<EntityGanro>(){

                public Render<? super EntityGanro> createRenderFor(RenderManager manager) {
                    return new RenderGanro(manager);
                }
            });
        }
        if (SPConfigMobs.angedEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityAnged.class, (IRenderFactory)new IRenderFactory<EntityAnged>(){

                public Render<? super EntityAnged> createRenderFor(RenderManager manager) {
                    return new RenderAnged(manager);
                }
            });
        }
        if (SPConfigMobs.ombooEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOmboo.class, (IRenderFactory)new IRenderFactory<EntityOmboo>(){

                public Render<? super EntityOmboo> createRenderFor(RenderManager manager) {
                    return new RenderOmboo(manager);
                }
            });
        }
        if (SPConfigMobs.rondEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityRond.class, (IRenderFactory)new IRenderFactory<EntityRond>(){

                public Render<? super EntityRond> createRenderFor(RenderManager manager) {
                    return new RenderRond(manager);
                }
            });
        }
        if (SPConfigMobs.jinjoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityJinjo.class, (IRenderFactory)new IRenderFactory<EntityJinjo>(){

                public Render<? super EntityJinjo> createRenderFor(RenderManager manager) {
                    return new RenderJinjo(manager);
                }
            });
        }
        if (SPConfigMobs.flamEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFlam.class, (IRenderFactory)new IRenderFactory<EntityFlam>(){

                public Render<? super EntityFlam> createRenderFor(RenderManager manager) {
                    return new RenderFlam(manager);
                }
            });
        }
        if (SPConfigMobs.pheonEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityPheon.class, (IRenderFactory)new IRenderFactory<EntityPheon>(){

                public Render<? super EntityPheon> createRenderFor(RenderManager manager) {
                    return new RenderPheon(manager);
                }
            });
        }
        if (SPConfigMobs.elviaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityElvia.class, (IRenderFactory)new IRenderFactory<EntityElvia>(){

                public Render<? super EntityElvia> createRenderFor(RenderManager manager) {
                    return new RenderElvia(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityTenn.class, (IRenderFactory)new IRenderFactory<EntityTenn>(){

            public Render<? super EntityTenn> createRenderFor(RenderManager manager) {
                return new RenderTenn(manager);
            }
        });
        if (SPConfigMobs.lenciaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLencia.class, (IRenderFactory)new IRenderFactory<EntityLencia>(){

                public Render<? super EntityLencia> createRenderFor(RenderManager manager) {
                    return new RenderLencia(manager);
                }
            });
        }
        if (SPConfigMobs.vestaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityVesta.class, (IRenderFactory)new IRenderFactory<EntityVesta>(){

                public Render<? super EntityVesta> createRenderFor(RenderManager manager) {
                    return new RenderVesta(manager);
                }
            });
        }
        if (SPConfigMobs.esorEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityEsor.class, (IRenderFactory)new IRenderFactory<EntityEsor>(){

                public Render<? super EntityEsor> createRenderFor(RenderManager manager) {
                    return new RenderEsor(manager);
                }
            });
        }
        if (SPConfigMobs.orchEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOrch.class, (IRenderFactory)new IRenderFactory<EntityOrch>(){

                public Render<? super EntityOrch> createRenderFor(RenderManager manager) {
                    return new RenderOrch(manager);
                }
            });
        }
        if (SPConfigMobs.flogEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFlog.class, (IRenderFactory)new IRenderFactory<EntityFlog>(){

                public Render<? super EntityFlog> createRenderFor(RenderManager manager) {
                    return new RenderFlog(manager);
                }
            });
        }
        if (SPConfigMobs.heedEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHeed.class, (IRenderFactory)new IRenderFactory<EntityHeed>(){

                public Render<? super EntityHeed> createRenderFor(RenderManager manager) {
                    return new RenderHeed(manager);
                }
            });
        }
        if (SPConfigMobs.cruxaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCruxA.class, (IRenderFactory)new IRenderFactory<EntityCruxA>(){

                public Render<? super EntityCruxA> createRenderFor(RenderManager manager) {
                    return new RenderCruxA(manager);
                }
            });
        }
        if (SPConfigMobs.cruxaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCruxB.class, (IRenderFactory)new IRenderFactory<EntityCruxB>(){

                public Render<? super EntityCruxB> createRenderFor(RenderManager manager) {
                    return new RenderCruxB(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileWebball.class, (IRenderFactory)new IRenderFactory<EntityProjectileWebball>(){

            public Render<EntityProjectileWebball> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/webball.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpineball.class, (IRenderFactory)new IRenderFactory<EntityProjectileSpineball>(){

            public Render<EntityProjectileSpineball> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/spineball.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileNade.class, (IRenderFactory)new IRenderFactory<EntityProjectileNade>(){

            public Render<EntityProjectileNade> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/nade.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileAlafhaBall.class, (IRenderFactory)new IRenderFactory<EntityProjectileAlafhaBall>(){

            public Render<EntityProjectileAlafhaBall> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/alafha.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileAngedball.class, (IRenderFactory)new IRenderFactory<EntityProjectileAngedball>(){

            public Render<EntityProjectileAngedball> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/anged.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectilePullball.class, (IRenderFactory)new IRenderFactory<EntityProjectilePullball>(){

            public Render<EntityProjectilePullball> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/pullingweb.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileAncientball.class, (IRenderFactory)new IRenderFactory<EntityProjectileAncientball>(){

            public Render<EntityProjectileAncientball> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/ancient.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableAntiInfestedBlock.class, (IRenderFactory)new IRenderFactory<EntityThrowableAntiInfestedBlock>(){

            public Render<EntityThrowableAntiInfestedBlock> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/cleaner.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileBiomass.class, (IRenderFactory)new IRenderFactory<EntityProjectileBiomass>(){

            public Render<EntityProjectileBiomass> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/biomass.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileDragonE.class, (IRenderFactory)new IRenderFactory<EntityProjectileDragonE>(){

            public Render<EntityProjectileDragonE> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/dragone.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileLenciaBall.class, (IRenderFactory)new IRenderFactory<EntityProjectileLenciaBall>(){

            public Render<EntityProjectileLenciaBall> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/lencia.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileElviaBall.class, (IRenderFactory)new IRenderFactory<EntityProjectileElviaBall>(){

            public Render<EntityProjectileElviaBall> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/elvia.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileEffects.class, (IRenderFactory)new IRenderFactory<EntityProjectileEffects>(){

            public Render<EntityProjectileEffects> createRenderFor(RenderManager manager) {
                return new SPProjectile(manager, 0.5f, new ResourceLocation("subspaceparasite", "textures/entity/projectile/elvia.png"));
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityTendril.class, (IRenderFactory)new IRenderFactory<EntityTendril>(){

            public Render<? super EntityTendril> createRenderFor(RenderManager manager) {
                return new RenderTendril(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDamage.class, (IRenderFactory)new IRenderFactory<EntityDamage>(){

            public Render<? super EntityDamage> createRenderFor(RenderManager manager) {
                return new RenderDamage(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, (IRenderFactory)new IRenderFactory<EntityBomb>(){

            public Render<? super EntityBomb> createRenderFor(RenderManager manager) {
                return new RenderBomb(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBiomass.class, (IRenderFactory)new IRenderFactory<EntityBiomass>(){

            public Render<? super EntityBiomass> createRenderFor(RenderManager manager) {
                return new RenderBiomass(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityGore.class, (IRenderFactory)new IRenderFactory<EntityGore>(){

            public Render<? super EntityGore> createRenderFor(RenderManager manager) {
                return new RenderGore(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileHomming.class, (IRenderFactory)new IRenderFactory<EntityProjectileHomming>(){

            public Render<? super EntityProjectileHomming> createRenderFor(RenderManager manager) {
                return new RenderProjectileHomming(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBody.class, (IRenderFactory)new IRenderFactory<EntityBody>(){

            public Render<? super EntityBody> createRenderFor(RenderManager manager) {
                return new RenderEntityBody(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBodyModel.class, (IRenderFactory)new IRenderFactory<EntityBodyModel>(){

            public Render<? super EntityBodyModel> createRenderFor(RenderManager manager) {
                return new RenderEntityBodyModel(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityToxicCloud.class, (IRenderFactory)new IRenderFactory<EntityToxicCloud>(){

            public Render<? super EntityToxicCloud> createRenderFor(RenderManager manager) {
                return new RenderTCloud(manager);
            }
        });
        if (!SPConfigSystems.oneMindDebug) {
            RenderingRegistry.registerEntityRenderingHandler(EntityParasiticScent.class, (IRenderFactory)new IRenderFactory<EntityParasiticScent>(){

                public Render<? super EntityParasiticScent> createRenderFor(RenderManager manager) {
                    return new RenderScent(manager);
                }
            });
        }
        RenderingRegistry.registerEntityRenderingHandler(EntitySource.class, (IRenderFactory)new IRenderFactory<EntitySource>(){

            public Render<? super EntitySource> createRenderFor(RenderManager manager) {
                return new RenderSource(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityOrbScary.class, (IRenderFactory)new IRenderFactory<EntityOrbScary>(){

            public Render<? super EntityOrbScary> createRenderFor(RenderManager manager) {
                return new RenderOrbScary(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityOrbVoid.class, (IRenderFactory)new IRenderFactory<EntityOrbVoid>(){

            public Render<? super EntityOrbVoid> createRenderFor(RenderManager manager) {
                return new RenderOrbVoid(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityOrbBoom.class, (IRenderFactory)new IRenderFactory<EntityOrbBoom>(){

            public Render<? super EntityOrbBoom> createRenderFor(RenderManager manager) {
                return new RenderOrbBoom(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityNade.class, (IRenderFactory)new IRenderFactory<EntityNade>(){

            public Render<? super EntityNade> createRenderFor(RenderManager manager) {
                return new RenderNade(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, (IRenderFactory)new IRenderFactory<EntityMeteor>(){

            public Render<? super EntityMeteor> createRenderFor(RenderManager manager) {
                return new RenderMeteor(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityWave.class, (IRenderFactory)new IRenderFactory<EntityWave>(){

            public Render<? super EntityWave> createRenderFor(RenderManager manager) {
                return new RenderWave(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityWaveShock.class, RenderWaveShock::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRemain.class, RenderRemain::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHitbox.class, HitboxNoRender::new);
    }
}

