/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPRooter;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationaryArchitect;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAINexusGrow
extends EntityAIBase {
    private final EntityPStationaryArchitect parent;
    private byte venkrolCurrentStage;
    private double tickss;
    private byte type;
    private boolean canGrow;
    private int dimLock;

    public EntityAINexusGrow(EntityPStationaryArchitect venkrol, int CURRENTstage) {
        this.parent = venkrol;
        this.tickss = 20.0;
        this.type = 1;
        this.venkrolCurrentStage = (byte)CURRENTstage;
        this.canGrow = true;
        this.dimLock = -1;
        String[] here = new String[2];
        for (int i = 0; i < SRPConfigSystems.maximumStageList.length; ++i) {
            int dimension;
            here = SRPConfigSystems.maximumStageList[i].split(";");
            if (here[0].isEmpty() || (dimension = Integer.parseInt(here[0])) != this.parent.field_70170_p.field_73011_w.getDimension()) continue;
            this.dimLock = Integer.parseInt(here[1]);
        }
    }

    public EntityAINexusGrow(EntityPStationaryArchitect venkrol, int CURRENTstage, byte in) {
        this(venkrol, CURRENTstage);
        this.type = in;
    }

    public boolean func_75250_a() {
        if (this.parent.canChangeVariant) {
            return true;
        }
        this.tickss -= 1.0;
        if (this.tickss < 0.0) {
            this.tickss = 20.0;
            this.checkPhase();
            this.spawnLeem();
            return this.canGrow;
        }
        return false;
    }

    public void func_75251_c() {
    }

    private void spawnLeem() {
        if (SRPConfigSystems.evolutionNests < this.parent.getPhaseCreated() && SRPConfigSystems.deveNestsUse < this.parent.getLevelCreated()) {
            return;
        }
        if (this.parent.field_70170_p.field_73012_v.nextDouble() >= 0.02) {
            return;
        }
        if ((this.venkrolCurrentStage == 3 || this.venkrolCurrentStage == 2) && this.type != 3) {
            List serverList = this.parent.field_70170_p.field_72996_f;
            int count = 0;
            for (int x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityPRooter) || ++count <= SRPConfig.nexusLeemCap && !(this.parent.func_70068_e((Entity)serverList.get(x)) < (double)(SRPConfig.nexusLeemDis * SRPConfig.nexusLeemDis))) continue;
                return;
            }
            if (ParasiteSummon.SummonM((EntityLivingBase)this.parent, new String[]{"srparasites:rooter_si;1;0"}, 12, 20, null) && SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloGrowlNoise) {
                    if (SRPSaveData.get(this.parent.field_70170_p, 11).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 15) == 0) {
                        this.parent.func_184185_a(SRPSounds.LEEMSI, 4.0f, 1.0f);
                    }
                } else {
                    this.parent.func_184185_a(SRPSounds.LEEMSI, 4.0f, 1.0f);
                }
            }
        }
    }

    public void func_75246_d() {
        if (this.venkrolCurrentStage == 3 && this.parent.field_70170_p.func_180494_b(this.parent.func_180425_c()) instanceof BiomeParasiteBase) {
            this.tickss = 20000.0;
        } else {
            int j = this.parent.getActualT();
            this.parent.setActualT(++j);
            if (j > this.parent.neededTime) {
                this.parent.setConvert(false);
                switch (this.type) {
                    case 1: {
                        this.upgradeV();
                        break;
                    }
                    case 2: {
                        this.upgradeD();
                        break;
                    }
                    case 3: {
                        this.upgradeL();
                    }
                }
            }
        }
    }

    private void checkPhase() {
        if (this.parent.field_70170_p.field_72995_K) {
            return;
        }
        if (this.dimLock >= this.venkrolCurrentStage + 1) {
            this.canGrow = false;
            return;
        }
        if (SRPConfigSystems.useEvolution) {
            Random rand = new Random();
            block0 : switch (SRPSaveData.get(this.parent.field_70170_p, 12).getEvolutionPhase(this.parent.field_70170_p.field_73011_w.getDimension())) {
                case -1: 
                case 0: {
                    this.canGrow = false;
                    break;
                }
                case 1: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyOne);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyOne);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyOne);
                        }
                    }
                    break;
                }
                case 2: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyTwo);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyTwo);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyTwo);
                        }
                    }
                    break;
                }
                case 3: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyThree);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyThree);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyThree);
                        }
                    }
                    break;
                }
                case 4: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyFour);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyFour);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyFour);
                        }
                    }
                    break;
                }
                case 5: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyFive);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyFive);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyFive);
                        }
                    }
                    break;
                }
                case 6: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltySix);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltySix);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltySix);
                        }
                    }
                    break;
                }
                case 7: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltySeven);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltySeven);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltySeven);
                        }
                    }
                    break;
                }
                case 8: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyEight);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyEight);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyEight);
                        }
                    }
                    break;
                }
                case 9: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyNine);
                            break;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyNine);
                            break;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyNine);
                        }
                    }
                    break;
                }
                case 10: {
                    switch (this.venkrolCurrentStage) {
                        case 1: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIGrowPenaltyTen);
                            break block0;
                        }
                        case 2: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIGrowPenaltyTen);
                            break block0;
                        }
                        case 3: {
                            this.canGrow = !(rand.nextDouble() < SRPConfigSystems.beckonStageIIIGrowPenaltyTen);
                        }
                    }
                }
            }
        }
    }

    private void upgradeV() {
        if (!ParasiteEventEntity.canSpawnNext) {
            return;
        }
        if (this.venkrolCurrentStage == 1) {
            EntityVenkrolSII out = new EntityVenkrolSII(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 13).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.VENKROLSII, 10.0f, 1.0f);
                if (SRPConfig.nexusStructures && this.parent.field_70170_p.field_73012_v.nextDouble() < 0.5) {
                    this.parent.generateStructure();
                }
            }
        } else if (this.venkrolCurrentStage == 2) {
            EntityVenkrolSIII out = new EntityVenkrolSIII(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 16).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.VENKROLSIII, 9.0f, 1.0f);
            }
        } else if (this.venkrolCurrentStage == 3) {
            SRPSaveData dataS;
            if (SRPConfigSystems.useEvolution && (dataS = SRPSaveData.get(this.parent.field_70170_p, 240)).getEvolutionPhase(this.parent.field_70170_p.field_73011_w.getDimension()) < SRPConfigSystems.evolutionNodeUnlock && dataS.getDeveLevel() < SRPConfigSystems.deveNodesUse) {
                this.tickss = 20000.0;
                return;
            }
            if (ParasiteEventWorld.canBiomeStillExist(this.parent.field_70170_p, this.parent.func_180425_c(), false) >= 1) {
                this.tickss = 20000.0;
                return;
            }
            EntityVenkrolSIV out = new EntityVenkrolSIV(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 14).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.VENKROLSIV, 10.0f, 1.0f);
            }
        }
    }

    private void upgradeD() {
        if (!ParasiteEventEntity.canSpawnNext) {
            return;
        }
        if (this.venkrolCurrentStage == 1) {
            EntityDodSII out = new EntityDodSII(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 15).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.DODSII, 10.0f, 1.0f);
                if (SRPConfig.nexusStructures && this.parent.field_70170_p.field_73012_v.nextDouble() < 0.3) {
                    this.parent.generateStructure();
                }
            }
        } else if (this.venkrolCurrentStage == 2) {
            EntityDodSIII out = new EntityDodSIII(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 17).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.DODSIII, 9.0f, 1.0f);
            }
        } else if (this.venkrolCurrentStage == 3) {
            SRPSaveData dataS;
            if (SRPConfigSystems.useEvolution && (dataS = SRPSaveData.get(this.parent.field_70170_p, 240)).getEvolutionPhase(this.parent.field_70170_p.field_73011_w.getDimension()) < SRPConfigSystems.evolutionColonyUnlock && dataS.getDeveLevel() < SRPConfigSystems.deveColoniesUse) {
                this.tickss = 20000.0;
                return;
            }
            if (ParasiteEventWorld.rangeOfColony(this.parent.field_70170_p, this.parent.func_180425_c(), true) != null) {
                this.tickss = 20000.0;
                return;
            }
            EntityDodSIV out = new EntityDodSIV(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 18).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.DODSIV, 10.0f, 1.0f);
            }
        }
    }

    private void upgradeL() {
        if (!ParasiteEventEntity.canSpawnNext) {
            return;
        }
        if (this.venkrolCurrentStage == 1) {
            EntityLeemSII out = new EntityLeemSII(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 19).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.LEEMSII, 10.0f, 1.0f);
                if (SRPConfig.nexusStructures && this.parent.field_70170_p.field_73012_v.nextDouble() < 0.3) {
                    this.parent.generateStructure();
                }
            }
        } else if (this.venkrolCurrentStage == 2) {
            EntityLeemSIII out = new EntityLeemSIII(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 20).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.LEEMSIII, 9.0f, 1.0f);
            }
        } else if (this.venkrolCurrentStage == 3) {
            SRPSaveData dataS;
            if (SRPConfigSystems.useEvolution && (dataS = SRPSaveData.get(this.parent.field_70170_p, 240)).getEvolutionPhase(this.parent.field_70170_p.field_73011_w.getDimension()) < SRPConfigSystems.evolutionHives && dataS.getDeveLevel() < SRPConfigSystems.deveHivesUse) {
                this.tickss = 20000.0;
                return;
            }
            EntityLeemSIV out = new EntityLeemSIV(this.parent.field_70170_p);
            out.cannotDespawn(this.parent.func_70692_ba());
            ParasiteEventEntity.spawnNext(this.parent, out, true, false);
            if (SRPConfigSystems.rsSounds) {
                if (SRPConfigSystems.disloWalkNoise && SRPSaveData.get(this.parent.field_70170_p, 21).getCurrentCode(this.parent.field_70170_p.field_73011_w.getDimension(), 16) >= 1) {
                    return;
                }
                this.parent.func_184185_a(SRPSounds.LEEMSIV, 10.0f, 1.0f);
            }
        }
    }
}

