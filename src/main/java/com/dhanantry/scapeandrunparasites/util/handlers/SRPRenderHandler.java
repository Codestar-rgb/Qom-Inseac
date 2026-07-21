package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.client.SRPProjectile;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.HitboxNoRender;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.RenderDamage;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.RenderProjectileHomming;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.RenderScent;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.RenderSource;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.RenderTCloud;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.abomination.RenderAboFaces;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderBanoAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderCanraAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderEmanaAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderGimAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderHullAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderIkiAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderLumAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderNoglaAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderRanracAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderShycoAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderWymoAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted.RenderZaaAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.ancient.RenderAncientPod;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.ancient.RenderOronco;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.ancient.RenderOroncoTen;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.ancient.RenderTerla;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.awakened.RenderOroncoAW;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderCruxA;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderCruxB;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderDone;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderHeed;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderHost;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderHostII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderInhooM;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderInhooS;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderLeer;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.crude.RenderMes;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.derived.RenderHeblu;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.derived.RenderKirin;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.RenderDodT;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.RenderLeemB;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.RenderNak;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.RenderRof;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.RenderTonro;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.RenderUnvo;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderDod;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderDodSII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderDodSIII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderDodSIV;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderLeem;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderLeemSII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderLeemSIII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderLeemSIV;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderVenkrol;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderVenkrolSII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderVenkrolSIII;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus.RenderVenkrolSIV;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerBear;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerCow;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerEnderman;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerHorse;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerHuman;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerPig;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerSheep;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerVillager;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.feral.RenderFerWolf;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked.RenderHiBlaze;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked.RenderHiGolem;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked.RenderHiSkeleton;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderAta;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderButhol;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderGothol;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderKol;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderLesh;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderLodo;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderMudo;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderNuuh;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderRathol;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn.RenderViin;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderDorpa;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfBear;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfCow;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfDragonE;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfEnderman;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfHorse;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfHuman;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfPig;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfPlayer;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfSheep;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfSquid;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfVillager;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.RenderInfWolf;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfCowHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfDragonEHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfEndermanHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfHorseHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfHumanHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfPigHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfPlayerHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfSheepHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfVillagerHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head.RenderInfWolfHead;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special.RenderSpeBear;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special.RenderSpeCow;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special.RenderSpeEnderman;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special.RenderSpeHuman;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special.RenderSpeSheep;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special.RenderSpeVillager;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderBiomass;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderBomb;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderEntityBody;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderEntityBodyModel;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderGore;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderMeteor;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderNade;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderOrbBoom;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderOrbScary;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderOrbVoid;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderRemain;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderTendril;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderWave;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.misc.RenderWaveShock;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderBano;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderCanra;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderEmana;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderGim;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderHull;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderIki;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderLum;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderNogla;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderRanrac;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderShyco;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderWymo;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive.RenderZaa;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.projectile.RenderHebluLight;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderAlafha;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderAnged;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderEsor;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderFlog;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderGanro;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderOmboo;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderOrch;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.RenderRond;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderElvia;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderFlam;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderJinjo;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderLencia;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderPheon;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderTenn;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent.RenderVesta;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityBodyModel;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.EntityHitbox;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbBoom;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbScary;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbVoid;
import com.dhanantry.scapeandrunparasites.entity.EntityParasiticScent;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.EntitySource;
import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityTendril;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWave;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWaveShock;
import com.dhanantry.scapeandrunparasites.entity.monster.abomination.EntityAboBodies;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityBanoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityCanraAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityGimAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityHullAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityIkiAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityLumAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityNoglaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityRanracAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityWymoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityZaaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityOronco;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityOroncoTen;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityTerla;
import com.dhanantry.scapeandrunparasites.entity.monster.awakened.EntityOroncoAW;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxA;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxB;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityDone;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHeed;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHostII;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLeer;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityMes;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityHeblu;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityKirin;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityDodT;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityLeemB;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityRof;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityTonro;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityUnvo;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDod;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeem;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerBear;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerCow;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerPig;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiBlaze;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiGolem;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiSkeleton;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityAta;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityButhol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityGothol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityKol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityNuuh;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityRathol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityViin;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityDorpa;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfDragonE;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPig;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPlayer;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSquid;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfCowHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfDragonEHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfEndermanHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHorseHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHumanHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPigHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPlayerHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfSheepHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfVillagerHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfWolfHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityBano;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityCanra;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityGim;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityIki;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityLum;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityRanrac;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityShyco;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityWymo;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityZaa;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAlafha;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAnged;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityEsor;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityFlog;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityGanro;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOmboo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOrch;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityRond;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityElvia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityFlam;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityJinjo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityLencia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityPheon;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityTenn;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityVesta;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityDropPod;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityMeteor;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAlafhaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAncientball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAngedball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileBiomass;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileDragonE;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileEffects;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileElviaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHebluLight;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileLenciaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectilePullball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileWebball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityThrowableAntiInfestedBlock;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class SRPRenderHandler {
   public static void registryEntityRenders() {
      if (SRPConfig.allowMobs) {
         RenderingRegistry.registerEntityRenderingHandler(EntityKirin.class, new IRenderFactory<EntityKirin>() {
            public Render<? super EntityKirin> createRenderFor(RenderManager manager) {
               return new RenderKirin(manager);
            }
         });
         if (SRPConfigMobs.hebluEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHeblu.class, new IRenderFactory<EntityHeblu>() {
               public Render<? super EntityHeblu> createRenderFor(RenderManager manager) {
                  return new RenderHeblu(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityOroncoAW.class, new IRenderFactory<EntityOroncoAW>() {
            public Render<? super EntityOroncoAW> createRenderFor(RenderManager manager) {
               return new RenderOroncoAW(manager);
            }
         });
         if (SRPConfigMobs.ratholEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityRathol.class, new IRenderFactory<EntityRathol>() {
               public Render<? super EntityRathol> createRenderFor(RenderManager manager) {
                  return new RenderRathol(manager);
               }
            });
         }

         if (SRPConfigMobs.gotholEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityGothol.class, new IRenderFactory<EntityGothol>() {
               public Render<? super EntityGothol> createRenderFor(RenderManager manager) {
                  return new RenderGothol(manager);
               }
            });
         }

         if (SRPConfigMobs.lodoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLodo.class, new IRenderFactory<EntityLodo>() {
               public Render<? super EntityLodo> createRenderFor(RenderManager manager) {
                  return new RenderLodo(manager);
               }
            });
         }

         if (SRPConfigMobs.butholEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityButhol.class, new IRenderFactory<EntityButhol>() {
               public Render<? super EntityButhol> createRenderFor(RenderManager manager) {
                  return new RenderButhol(manager);
               }
            });
         }

         if (SRPConfigMobs.mudoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMudo.class, new IRenderFactory<EntityMudo>() {
               public Render<? super EntityMudo> createRenderFor(RenderManager manager) {
                  return new RenderMudo(manager);
               }
            });
         }

         if (SRPConfigMobs.ataEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityAta.class, new IRenderFactory<EntityAta>() {
               public Render<? super EntityAta> createRenderFor(RenderManager manager) {
                  return new RenderAta(manager);
               }
            });
         }

         if (SRPConfigMobs.ataEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityViin.class, new IRenderFactory<EntityViin>() {
               public Render<? super EntityViin> createRenderFor(RenderManager manager) {
                  return new RenderViin(manager);
               }
            });
         }

         if (SRPConfigMobs.nuuhEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityNuuh.class, new IRenderFactory<EntityNuuh>() {
               public Render<? super EntityNuuh> createRenderFor(RenderManager manager) {
                  return new RenderNuuh(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityVenkrol.class, new IRenderFactory<EntityVenkrol>() {
            public Render<? super EntityVenkrol> createRenderFor(RenderManager manager) {
               return new RenderVenkrol(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityVenkrolSII.class, new IRenderFactory<EntityVenkrolSII>() {
            public Render<? super EntityVenkrolSII> createRenderFor(RenderManager manager) {
               return new RenderVenkrolSII(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityVenkrolSIII.class, new IRenderFactory<EntityVenkrolSIII>() {
            public Render<? super EntityVenkrolSIII> createRenderFor(RenderManager manager) {
               return new RenderVenkrolSIII(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityVenkrolSIV.class, new IRenderFactory<EntityVenkrolSIV>() {
            public Render<? super EntityVenkrolSIV> createRenderFor(RenderManager manager) {
               return new RenderVenkrolSIV(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityDodT.class, new IRenderFactory<EntityDodT>() {
            public Render<? super EntityDodT> createRenderFor(RenderManager manager) {
               return new RenderDodT(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityLeemB.class, new IRenderFactory<EntityLeemB>() {
            public Render<? super EntityLeemB> createRenderFor(RenderManager manager) {
               return new RenderLeemB(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityRof.class, new IRenderFactory<EntityRof>() {
            public Render<? super EntityRof> createRenderFor(RenderManager manager) {
               return new RenderRof(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityDod.class, new IRenderFactory<EntityDod>() {
            public Render<? super EntityDod> createRenderFor(RenderManager manager) {
               return new RenderDod(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityDodSII.class, new IRenderFactory<EntityDodSII>() {
            public Render<? super EntityDodSII> createRenderFor(RenderManager manager) {
               return new RenderDodSII(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityDodSIII.class, new IRenderFactory<EntityDodSIII>() {
            public Render<? super EntityDodSIII> createRenderFor(RenderManager manager) {
               return new RenderDodSIII(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityDodSIV.class, new IRenderFactory<EntityDodSIV>() {
            public Render<? super EntityDodSIV> createRenderFor(RenderManager manager) {
               return new RenderDodSIV(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityLeem.class, new IRenderFactory<EntityLeem>() {
            public Render<? super EntityLeem> createRenderFor(RenderManager manager) {
               return new RenderLeem(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityLeemSII.class, new IRenderFactory<EntityLeemSII>() {
            public Render<? super EntityLeemSII> createRenderFor(RenderManager manager) {
               return new RenderLeemSII(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityLeemSIII.class, new IRenderFactory<EntityLeemSIII>() {
            public Render<? super EntityLeemSIII> createRenderFor(RenderManager manager) {
               return new RenderLeemSIII(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityLeemSIV.class, new IRenderFactory<EntityLeemSIV>() {
            public Render<? super EntityLeemSIV> createRenderFor(RenderManager manager) {
               return new RenderLeemSIV(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityLesh.class, new IRenderFactory<EntityLesh>() {
            public Render<? super EntityLesh> createRenderFor(RenderManager manager) {
               return new RenderLesh(manager);
            }
         });
         if (SRPConfigMobs.tonroEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityTonro.class, new IRenderFactory<EntityTonro>() {
               public Render<? super EntityTonro> createRenderFor(RenderManager manager) {
                  return new RenderTonro(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityNak.class, new IRenderFactory<EntityNak>() {
            public Render<? super EntityNak> createRenderFor(RenderManager manager) {
               return new RenderNak(manager);
            }
         });
         if (SRPConfigMobs.unvoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityUnvo.class, new IRenderFactory<EntityUnvo>() {
               public Render<? super EntityUnvo> createRenderFor(RenderManager manager) {
                  return new RenderUnvo(manager);
               }
            });
         }

         if (SRPConfigMobs.kolEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityKol.class, new IRenderFactory<EntityKol>() {
               public Render<? super EntityKol> createRenderFor(RenderManager manager) {
                  return new RenderKol(manager);
               }
            });
         }

         if (SRPConfigMobs.terlaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityTerla.class, new IRenderFactory<EntityTerla>() {
               public Render<? super EntityTerla> createRenderFor(RenderManager manager) {
                  return new RenderTerla(manager);
               }
            });
         }

         if (SRPConfigMobs.oroncoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOronco.class, new IRenderFactory<EntityOronco>() {
               public Render<? super EntityOronco> createRenderFor(RenderManager manager) {
                  return new RenderOronco(manager);
               }
            });
         }

         if (SRPConfigMobs.oroncoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOroncoTen.class, new IRenderFactory<EntityOroncoTen>() {
               public Render<? super EntityOroncoTen> createRenderFor(RenderManager manager) {
                  return new RenderOroncoTen(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityDropPod.class, new IRenderFactory<EntityDropPod>() {
            public Render<? super EntityDropPod> createRenderFor(RenderManager manager) {
               return new RenderAncientPod(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityAboBodies.class, new IRenderFactory<EntityAboBodies>() {
            public Render<? super EntityAboBodies> createRenderFor(RenderManager manager) {
               return new RenderAboFaces(manager);
            }
         });
         if (SRPConfigMobs.leerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLeer.class, new IRenderFactory<EntityLeer>() {
               public Render<? super EntityLeer> createRenderFor(RenderManager manager) {
                  return new RenderLeer(manager);
               }
            });
         }

         if (SRPConfigMobs.doneEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityDone.class, new IRenderFactory<EntityDone>() {
               public Render<? super EntityDone> createRenderFor(RenderManager manager) {
                  return new RenderDone(manager);
               }
            });
         }

         if (SRPConfigMobs.hostEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHost.class, new IRenderFactory<EntityHost>() {
               public Render<? super EntityHost> createRenderFor(RenderManager manager) {
                  return new RenderHost(manager);
               }
            });
         }

         if (SRPConfigMobs.hostEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHostII.class, new IRenderFactory<EntityHostII>() {
               public Render<? super EntityHostII> createRenderFor(RenderManager manager) {
                  return new RenderHostII(manager);
               }
            });
         }

         if (SRPConfigMobs.thrallEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMes.class, new IRenderFactory<EntityMes>() {
               public Render<? super EntityMes> createRenderFor(RenderManager manager) {
                  return new RenderMes(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityInhooS.class, new IRenderFactory<EntityInhooS>() {
            public Render<? super EntityInhooS> createRenderFor(RenderManager manager) {
               return new RenderInhooS(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityInhooM.class, new IRenderFactory<EntityInhooM>() {
            public Render<? super EntityInhooM> createRenderFor(RenderManager manager) {
               return new RenderInhooM(manager);
            }
         });
         if (SRPConfigMobs.dorpaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityDorpa.class, new IRenderFactory<EntityDorpa>() {
               public Render<? super EntityDorpa> createRenderFor(RenderManager manager) {
                  return new RenderDorpa(manager);
               }
            });
         }

         if (SRPConfigMobs.infendermanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfEnderman.class, new IRenderFactory<EntityInfEnderman>() {
               public Render<? super EntityInfEnderman> createRenderFor(RenderManager manager) {
                  return new RenderInfEnderman(manager);
               }
            });
         }

         if (SRPConfigMobs.infendermanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfEndermanHead.class, new IRenderFactory<EntityInfEndermanHead>() {
               public Render<? super EntityInfEndermanHead> createRenderFor(RenderManager manager) {
                  return new RenderInfEndermanHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infhumanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHuman.class, new IRenderFactory<EntityInfHuman>() {
               public Render<? super EntityInfHuman> createRenderFor(RenderManager manager) {
                  return new RenderInfHuman(manager);
               }
            });
         }

         if (SRPConfigMobs.infhumanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHumanHead.class, new IRenderFactory<EntityInfHumanHead>() {
               public Render<? super EntityInfHumanHead> createRenderFor(RenderManager manager) {
                  return new RenderInfHumanHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infcowEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfCow.class, new IRenderFactory<EntityInfCow>() {
               public Render<? super EntityInfCow> createRenderFor(RenderManager manager) {
                  return new RenderInfCow(manager);
               }
            });
         }

         if (SRPConfigMobs.infsquidEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfSquid.class, new IRenderFactory<EntityInfSquid>() {
               public Render<? super EntityInfSquid> createRenderFor(RenderManager manager) {
                  return new RenderInfSquid(manager);
               }
            });
         }

         if (SRPConfigMobs.infcowEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfCowHead.class, new IRenderFactory<EntityInfCowHead>() {
               public Render<? super EntityInfCowHead> createRenderFor(RenderManager manager) {
                  return new RenderInfCowHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infsheepEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfSheep.class, new IRenderFactory<EntityInfSheep>() {
               public Render<? super EntityInfSheep> createRenderFor(RenderManager manager) {
                  return new RenderInfSheep(manager);
               }
            });
         }

         if (SRPConfigMobs.infsheepEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfSheepHead.class, new IRenderFactory<EntityInfSheepHead>() {
               public Render<? super EntityInfSheepHead> createRenderFor(RenderManager manager) {
                  return new RenderInfSheepHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infwolfEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfWolf.class, new IRenderFactory<EntityInfWolf>() {
               public Render<? super EntityInfWolf> createRenderFor(RenderManager manager) {
                  return new RenderInfWolf(manager);
               }
            });
         }

         if (SRPConfigMobs.infwolfEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfWolfHead.class, new IRenderFactory<EntityInfWolfHead>() {
               public Render<? super EntityInfWolfHead> createRenderFor(RenderManager manager) {
                  return new RenderInfWolfHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infpigEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPig.class, new IRenderFactory<EntityInfPig>() {
               public Render<? super EntityInfPig> createRenderFor(RenderManager manager) {
                  return new RenderInfPig(manager);
               }
            });
         }

         if (SRPConfigMobs.infpigEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPigHead.class, new IRenderFactory<EntityInfPigHead>() {
               public Render<? super EntityInfPigHead> createRenderFor(RenderManager manager) {
                  return new RenderInfPigHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infvillagerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfVillager.class, new IRenderFactory<EntityInfVillager>() {
               public Render<? super EntityInfVillager> createRenderFor(RenderManager manager) {
                  return new RenderInfVillager(manager);
               }
            });
         }

         if (SRPConfigMobs.infvillagerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfVillagerHead.class, new IRenderFactory<EntityInfVillagerHead>() {
               public Render<? super EntityInfVillagerHead> createRenderFor(RenderManager manager) {
                  return new RenderInfVillagerHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infhorseEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHorse.class, new IRenderFactory<EntityInfHorse>() {
               public Render<? super EntityInfHorse> createRenderFor(RenderManager manager) {
                  return new RenderInfHorse(manager);
               }
            });
         }

         if (SRPConfigMobs.infhorseEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfHorseHead.class, new IRenderFactory<EntityInfHorseHead>() {
               public Render<? super EntityInfHorseHead> createRenderFor(RenderManager manager) {
                  return new RenderInfHorseHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infadventurerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPlayer.class, new IRenderFactory<EntityInfPlayer>() {
               public Render<? super EntityInfPlayer> createRenderFor(RenderManager manager) {
                  return new RenderInfPlayer(manager);
               }
            });
         }

         if (SRPConfigMobs.infadventurerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfPlayerHead.class, new IRenderFactory<EntityInfPlayerHead>() {
               public Render<? super EntityInfPlayerHead> createRenderFor(RenderManager manager) {
                  return new RenderInfPlayerHead(manager);
               }
            });
         }

         if (SRPConfigMobs.infbearEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfBear.class, new IRenderFactory<EntityInfBear>() {
               public Render<? super EntityInfBear> createRenderFor(RenderManager manager) {
                  return new RenderInfBear(manager);
               }
            });
         }

         if (SRPConfigMobs.infdragoneEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfDragonE.class, new IRenderFactory<EntityInfDragonE>() {
               public Render<? super EntityInfDragonE> createRenderFor(RenderManager manager) {
                  return new RenderInfDragonE(manager);
               }
            });
         }

         if (SRPConfigMobs.infdragoneEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityInfDragonEHead.class, new IRenderFactory<EntityInfDragonEHead>() {
               public Render<? super EntityInfDragonEHead> createRenderFor(RenderManager manager) {
                  return new RenderInfDragonEHead(manager);
               }
            });
         }

         if (SRPConfigMobs.ferbearEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerBear.class, new IRenderFactory<EntityFerBear>() {
               public Render<? super EntityFerBear> createRenderFor(RenderManager manager) {
                  return new RenderFerBear(manager);
               }
            });
         }

         if (SRPConfigMobs.fercowEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerCow.class, new IRenderFactory<EntityFerCow>() {
               public Render<? super EntityFerCow> createRenderFor(RenderManager manager) {
                  return new RenderFerCow(manager);
               }
            });
         }

         if (SRPConfigMobs.ferendermanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerEnderman.class, new IRenderFactory<EntityFerEnderman>() {
               public Render<? super EntityFerEnderman> createRenderFor(RenderManager manager) {
                  return new RenderFerEnderman(manager);
               }
            });
         }

         if (SRPConfigMobs.ferhorseEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerHorse.class, new IRenderFactory<EntityFerHorse>() {
               public Render<? super EntityFerHorse> createRenderFor(RenderManager manager) {
                  return new RenderFerHorse(manager);
               }
            });
         }

         if (SRPConfigMobs.fervillagerEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerVillager.class, new IRenderFactory<EntityFerVillager>() {
               public Render<? super EntityFerVillager> createRenderFor(RenderManager manager) {
                  return new RenderFerVillager(manager);
               }
            });
         }

         if (SRPConfigMobs.ferhumanEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerHuman.class, new IRenderFactory<EntityFerHuman>() {
               public Render<? super EntityFerHuman> createRenderFor(RenderManager manager) {
                  return new RenderFerHuman(manager);
               }
            });
         }

         if (SRPConfigMobs.fersheepEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerSheep.class, new IRenderFactory<EntityFerSheep>() {
               public Render<? super EntityFerSheep> createRenderFor(RenderManager manager) {
                  return new RenderFerSheep(manager);
               }
            });
         }

         if (SRPConfigMobs.ferpigEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerPig.class, new IRenderFactory<EntityFerPig>() {
               public Render<? super EntityFerPig> createRenderFor(RenderManager manager) {
                  return new RenderFerPig(manager);
               }
            });
         }

         if (SRPConfigMobs.ferwolfEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFerWolf.class, new IRenderFactory<EntityFerWolf>() {
               public Render<? super EntityFerWolf> createRenderFor(RenderManager manager) {
                  return new RenderFerWolf(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntitySpeVillager.class, new IRenderFactory<EntitySpeVillager>() {
            public Render<? super EntitySpeVillager> createRenderFor(RenderManager manager) {
               return new RenderSpeVillager(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntitySpeHuman.class, new IRenderFactory<EntitySpeHuman>() {
            public Render<? super EntitySpeHuman> createRenderFor(RenderManager manager) {
               return new RenderSpeHuman(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntitySpeCow.class, new IRenderFactory<EntitySpeCow>() {
            public Render<? super EntitySpeCow> createRenderFor(RenderManager manager) {
               return new RenderSpeCow(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntitySpeEnderman.class, new IRenderFactory<EntitySpeEnderman>() {
            public Render<? super EntitySpeEnderman> createRenderFor(RenderManager manager) {
               return new RenderSpeEnderman(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntitySpeSheep.class, new IRenderFactory<EntitySpeSheep>() {
            public Render<? super EntitySpeSheep> createRenderFor(RenderManager manager) {
               return new RenderSpeSheep(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntitySpeBear.class, new IRenderFactory<EntitySpeBear>() {
            public Render<? super EntitySpeBear> createRenderFor(RenderManager manager) {
               return new RenderSpeBear(manager);
            }
         });
         if (SRPConfigMobs.higolemEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHiGolem.class, new IRenderFactory<EntityHiGolem>() {
               public Render<? super EntityHiGolem> createRenderFor(RenderManager manager) {
                  return new RenderHiGolem(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityHiSkeleton.class, new IRenderFactory<EntityHiSkeleton>() {
            public Render<? super EntityHiSkeleton> createRenderFor(RenderManager manager) {
               return new RenderHiSkeleton(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityHiBlaze.class, new IRenderFactory<EntityHiBlaze>() {
            public Render<? super EntityHiBlaze> createRenderFor(RenderManager manager) {
               return new RenderHiBlaze(manager);
            }
         });
         if (SRPConfigMobs.emanaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityEmana.class, new IRenderFactory<EntityEmana>() {
               public Render<? super EntityEmana> createRenderFor(RenderManager manager) {
                  return new RenderEmana(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityEmanaAdapted.class, new IRenderFactory<EntityEmanaAdapted>() {
               public Render<? super EntityEmanaAdapted> createRenderFor(RenderManager manager) {
                  return new RenderEmanaAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.lumEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLum.class, new IRenderFactory<EntityLum>() {
               public Render<? super EntityLum> createRenderFor(RenderManager manager) {
                  return new RenderLum(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityLumAdapted.class, new IRenderFactory<EntityLumAdapted>() {
               public Render<? super EntityLumAdapted> createRenderFor(RenderManager manager) {
                  return new RenderLumAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.hullEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHull.class, new IRenderFactory<EntityHull>() {
               public Render<? super EntityHull> createRenderFor(RenderManager manager) {
                  return new RenderHull(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityHullAdapted.class, new IRenderFactory<EntityHullAdapted>() {
               public Render<? super EntityHullAdapted> createRenderFor(RenderManager manager) {
                  return new RenderHullAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.canraEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCanra.class, new IRenderFactory<EntityCanra>() {
               public Render<? super EntityCanra> createRenderFor(RenderManager manager) {
                  return new RenderCanra(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityCanraAdapted.class, new IRenderFactory<EntityCanraAdapted>() {
               public Render<? super EntityCanraAdapted> createRenderFor(RenderManager manager) {
                  return new RenderCanraAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.noglaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityNogla.class, new IRenderFactory<EntityNogla>() {
               public Render<? super EntityNogla> createRenderFor(RenderManager manager) {
                  return new RenderNogla(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityNoglaAdapted.class, new IRenderFactory<EntityNoglaAdapted>() {
               public Render<? super EntityNoglaAdapted> createRenderFor(RenderManager manager) {
                  return new RenderNoglaAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.zetmoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityBano.class, new IRenderFactory<EntityBano>() {
               public Render<? super EntityBano> createRenderFor(RenderManager manager) {
                  return new RenderBano(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityBanoAdapted.class, new IRenderFactory<EntityBanoAdapted>() {
               public Render<? super EntityBanoAdapted> createRenderFor(RenderManager manager) {
                  return new RenderBanoAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.shycoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityShyco.class, new IRenderFactory<EntityShyco>() {
               public Render<? super EntityShyco> createRenderFor(RenderManager manager) {
                  return new RenderShyco(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityShycoAdapted.class, new IRenderFactory<EntityShycoAdapted>() {
               public Render<? super EntityShycoAdapted> createRenderFor(RenderManager manager) {
                  return new RenderShycoAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.wymoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityWymo.class, new IRenderFactory<EntityWymo>() {
               public Render<? super EntityWymo> createRenderFor(RenderManager manager) {
                  return new RenderWymo(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityWymoAdapted.class, new IRenderFactory<EntityWymoAdapted>() {
               public Render<? super EntityWymoAdapted> createRenderFor(RenderManager manager) {
                  return new RenderWymoAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.ikiEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityIki.class, new IRenderFactory<EntityIki>() {
               public Render<? super EntityIki> createRenderFor(RenderManager manager) {
                  return new RenderIki(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityIkiAdapted.class, new IRenderFactory<EntityIkiAdapted>() {
               public Render<? super EntityIkiAdapted> createRenderFor(RenderManager manager) {
                  return new RenderIkiAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.arachnidaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityRanrac.class, new IRenderFactory<EntityRanrac>() {
               public Render<? super EntityRanrac> createRenderFor(RenderManager manager) {
                  return new RenderRanrac(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityRanracAdapted.class, new IRenderFactory<EntityRanracAdapted>() {
               public Render<? super EntityRanracAdapted> createRenderFor(RenderManager manager) {
                  return new RenderRanracAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.zaaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityZaa.class, new IRenderFactory<EntityZaa>() {
               public Render<? super EntityZaa> createRenderFor(RenderManager manager) {
                  return new RenderZaa(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityZaaAdapted.class, new IRenderFactory<EntityZaaAdapted>() {
               public Render<? super EntityZaaAdapted> createRenderFor(RenderManager manager) {
                  return new RenderZaaAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.gimEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityGim.class, new IRenderFactory<EntityGim>() {
               public Render<? super EntityGim> createRenderFor(RenderManager manager) {
                  return new RenderGim(manager);
               }
            });
            RenderingRegistry.registerEntityRenderingHandler(EntityGimAdapted.class, new IRenderFactory<EntityGimAdapted>() {
               public Render<? super EntityGimAdapted> createRenderFor(RenderManager manager) {
                  return new RenderGimAdapted(manager);
               }
            });
         }

         if (SRPConfigMobs.alafhaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityAlafha.class, new IRenderFactory<EntityAlafha>() {
               public Render<? super EntityAlafha> createRenderFor(RenderManager manager) {
                  return new RenderAlafha(manager);
               }
            });
         }

         if (SRPConfigMobs.ganroEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityGanro.class, new IRenderFactory<EntityGanro>() {
               public Render<? super EntityGanro> createRenderFor(RenderManager manager) {
                  return new RenderGanro(manager);
               }
            });
         }

         if (SRPConfigMobs.angedEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityAnged.class, new IRenderFactory<EntityAnged>() {
               public Render<? super EntityAnged> createRenderFor(RenderManager manager) {
                  return new RenderAnged(manager);
               }
            });
         }

         if (SRPConfigMobs.ombooEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOmboo.class, new IRenderFactory<EntityOmboo>() {
               public Render<? super EntityOmboo> createRenderFor(RenderManager manager) {
                  return new RenderOmboo(manager);
               }
            });
         }

         if (SRPConfigMobs.rondEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityRond.class, new IRenderFactory<EntityRond>() {
               public Render<? super EntityRond> createRenderFor(RenderManager manager) {
                  return new RenderRond(manager);
               }
            });
         }

         if (SRPConfigMobs.jinjoEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityJinjo.class, new IRenderFactory<EntityJinjo>() {
               public Render<? super EntityJinjo> createRenderFor(RenderManager manager) {
                  return new RenderJinjo(manager);
               }
            });
         }

         if (SRPConfigMobs.flamEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFlam.class, new IRenderFactory<EntityFlam>() {
               public Render<? super EntityFlam> createRenderFor(RenderManager manager) {
                  return new RenderFlam(manager);
               }
            });
         }

         if (SRPConfigMobs.pheonEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityPheon.class, new IRenderFactory<EntityPheon>() {
               public Render<? super EntityPheon> createRenderFor(RenderManager manager) {
                  return new RenderPheon(manager);
               }
            });
         }

         if (SRPConfigMobs.elviaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityElvia.class, new IRenderFactory<EntityElvia>() {
               public Render<? super EntityElvia> createRenderFor(RenderManager manager) {
                  return new RenderElvia(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityTenn.class, new IRenderFactory<EntityTenn>() {
            public Render<? super EntityTenn> createRenderFor(RenderManager manager) {
               return new RenderTenn(manager);
            }
         });
         if (SRPConfigMobs.lenciaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityLencia.class, new IRenderFactory<EntityLencia>() {
               public Render<? super EntityLencia> createRenderFor(RenderManager manager) {
                  return new RenderLencia(manager);
               }
            });
         }

         if (SRPConfigMobs.vestaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityVesta.class, new IRenderFactory<EntityVesta>() {
               public Render<? super EntityVesta> createRenderFor(RenderManager manager) {
                  return new RenderVesta(manager);
               }
            });
         }

         if (SRPConfigMobs.esorEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityEsor.class, new IRenderFactory<EntityEsor>() {
               public Render<? super EntityEsor> createRenderFor(RenderManager manager) {
                  return new RenderEsor(manager);
               }
            });
         }

         if (SRPConfigMobs.orchEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityOrch.class, new IRenderFactory<EntityOrch>() {
               public Render<? super EntityOrch> createRenderFor(RenderManager manager) {
                  return new RenderOrch(manager);
               }
            });
         }

         if (SRPConfigMobs.flogEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityFlog.class, new IRenderFactory<EntityFlog>() {
               public Render<? super EntityFlog> createRenderFor(RenderManager manager) {
                  return new RenderFlog(manager);
               }
            });
         }

         if (SRPConfigMobs.heedEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityHeed.class, new IRenderFactory<EntityHeed>() {
               public Render<? super EntityHeed> createRenderFor(RenderManager manager) {
                  return new RenderHeed(manager);
               }
            });
         }

         if (SRPConfigMobs.cruxaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCruxA.class, new IRenderFactory<EntityCruxA>() {
               public Render<? super EntityCruxA> createRenderFor(RenderManager manager) {
                  return new RenderCruxA(manager);
               }
            });
         }

         if (SRPConfigMobs.cruxaEnabled) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCruxB.class, new IRenderFactory<EntityCruxB>() {
               public Render<? super EntityCruxB> createRenderFor(RenderManager manager) {
                  return new RenderCruxB(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileWebball.class, new IRenderFactory<EntityProjectileWebball>() {
            public Render<EntityProjectileWebball> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/webball.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpineball.class, new IRenderFactory<EntityProjectileSpineball>() {
            public Render<EntityProjectileSpineball> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/spineball.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileNade.class, new IRenderFactory<EntityProjectileNade>() {
            public Render<EntityProjectileNade> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/nade.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileAlafhaBall.class, new IRenderFactory<EntityProjectileAlafhaBall>() {
            public Render<EntityProjectileAlafhaBall> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/alafha.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileHebluLight.class, new IRenderFactory<EntityProjectileHebluLight>() {
            public Render<? super EntityProjectileHebluLight> createRenderFor(RenderManager manager) {
               return new RenderHebluLight(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileAngedball.class, new IRenderFactory<EntityProjectileAngedball>() {
            public Render<EntityProjectileAngedball> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/anged.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectilePullball.class, new IRenderFactory<EntityProjectilePullball>() {
            public Render<EntityProjectilePullball> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/pullingweb.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileAncientball.class, new IRenderFactory<EntityProjectileAncientball>() {
            public Render<EntityProjectileAncientball> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/ancient.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityThrowableAntiInfestedBlock.class, new IRenderFactory<EntityThrowableAntiInfestedBlock>() {
            public Render<EntityThrowableAntiInfestedBlock> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/cleaner.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileBiomass.class, new IRenderFactory<EntityProjectileBiomass>() {
            public Render<EntityProjectileBiomass> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/biomass.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileDragonE.class, new IRenderFactory<EntityProjectileDragonE>() {
            public Render<EntityProjectileDragonE> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/dragone.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileLenciaBall.class, new IRenderFactory<EntityProjectileLenciaBall>() {
            public Render<EntityProjectileLenciaBall> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/lencia.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileElviaBall.class, new IRenderFactory<EntityProjectileElviaBall>() {
            public Render<EntityProjectileElviaBall> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/elvia.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileEffects.class, new IRenderFactory<EntityProjectileEffects>() {
            public Render<EntityProjectileEffects> createRenderFor(RenderManager manager) {
               return new SRPProjectile(manager, 0.5F, new ResourceLocation("srparasites", "textures/entity/projectile/elvia.png"));
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityTendril.class, new IRenderFactory<EntityTendril>() {
            public Render<? super EntityTendril> createRenderFor(RenderManager manager) {
               return new RenderTendril(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityDamage.class, new IRenderFactory<EntityDamage>() {
            public Render<? super EntityDamage> createRenderFor(RenderManager manager) {
               return new RenderDamage(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new IRenderFactory<EntityBomb>() {
            public Render<? super EntityBomb> createRenderFor(RenderManager manager) {
               return new RenderBomb(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityBiomass.class, new IRenderFactory<EntityBiomass>() {
            public Render<? super EntityBiomass> createRenderFor(RenderManager manager) {
               return new RenderBiomass(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityGore.class, new IRenderFactory<EntityGore>() {
            public Render<? super EntityGore> createRenderFor(RenderManager manager) {
               return new RenderGore(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityProjectileHomming.class, new IRenderFactory<EntityProjectileHomming>() {
            public Render<? super EntityProjectileHomming> createRenderFor(RenderManager manager) {
               return new RenderProjectileHomming(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityBody.class, new IRenderFactory<EntityBody>() {
            public Render<? super EntityBody> createRenderFor(RenderManager manager) {
               return new RenderEntityBody(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityBodyModel.class, new IRenderFactory<EntityBodyModel>() {
            public Render<? super EntityBodyModel> createRenderFor(RenderManager manager) {
               return new RenderEntityBodyModel(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityToxicCloud.class, new IRenderFactory<EntityToxicCloud>() {
            public Render<? super EntityToxicCloud> createRenderFor(RenderManager manager) {
               return new RenderTCloud(manager);
            }
         });
         if (!SRPConfigSystems.oneMindDebug) {
            RenderingRegistry.registerEntityRenderingHandler(EntityParasiticScent.class, new IRenderFactory<EntityParasiticScent>() {
               public Render<? super EntityParasiticScent> createRenderFor(RenderManager manager) {
                  return new RenderScent(manager);
               }
            });
         }

         RenderingRegistry.registerEntityRenderingHandler(EntitySource.class, new IRenderFactory<EntitySource>() {
            public Render<? super EntitySource> createRenderFor(RenderManager manager) {
               return new RenderSource(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityOrbScary.class, new IRenderFactory<EntityOrbScary>() {
            public Render<? super EntityOrbScary> createRenderFor(RenderManager manager) {
               return new RenderOrbScary(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityOrbVoid.class, new IRenderFactory<EntityOrbVoid>() {
            public Render<? super EntityOrbVoid> createRenderFor(RenderManager manager) {
               return new RenderOrbVoid(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityOrbBoom.class, new IRenderFactory<EntityOrbBoom>() {
            public Render<? super EntityOrbBoom> createRenderFor(RenderManager manager) {
               return new RenderOrbBoom(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityNade.class, new IRenderFactory<EntityNade>() {
            public Render<? super EntityNade> createRenderFor(RenderManager manager) {
               return new RenderNade(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new IRenderFactory<EntityMeteor>() {
            public Render<? super EntityMeteor> createRenderFor(RenderManager manager) {
               return new RenderMeteor(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityWave.class, new IRenderFactory<EntityWave>() {
            public Render<? super EntityWave> createRenderFor(RenderManager manager) {
               return new RenderWave(manager);
            }
         });
         RenderingRegistry.registerEntityRenderingHandler(EntityWaveShock.class, RenderWaveShock::new);
         RenderingRegistry.registerEntityRenderingHandler(EntityRemain.class, RenderRemain::new);
         RenderingRegistry.registerEntityRenderingHandler(EntityHitbox.class, HitboxNoRender::new);
      }
   }
}
