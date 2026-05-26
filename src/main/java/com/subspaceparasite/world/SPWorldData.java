/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.MapStorage
 *  net.minecraft.world.storage.WorldSavedData
 *  org.apache.logging.log4j.Level
 */
package com.subspaceparasite.world;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldEntitySpawner;
import java.util.ArrayList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import org.apache.logging.log4j.Level;

public class SPWorldData
extends WorldSavedData {
    private static final String DATA_NAME = "subspaceparasite_data";
    private static SPWorldData instance;
    private ArrayList<Integer> nodeX = new ArrayList();
    private ArrayList<Integer> nodeY = new ArrayList();
    private ArrayList<Integer> nodeZ = new ArrayList();
    private ArrayList<Integer> nodeA = new ArrayList();
    private ArrayList<Byte> nodeT = new ArrayList();
    private ArrayList<Integer> originX = new ArrayList();
    private ArrayList<Integer> originY = new ArrayList();
    private ArrayList<Integer> originZ = new ArrayList();
    private ArrayList<Integer> originA = new ArrayList();
    private ArrayList<Integer> originH = new ArrayList();
    private ArrayList<Integer> colonyX = new ArrayList();
    private ArrayList<Integer> colonyY = new ArrayList();
    private ArrayList<Integer> colonyZ = new ArrayList();
    private ArrayList<Integer> colonyA = new ArrayList();
    private ArrayList<Integer> resistanceI = new ArrayList();
    private ArrayList<String> resistanceS = new ArrayList();
    private boolean dimMeteor;
    private static SPWorldData clientInstance;

    public SPWorldData() {
        super(DATA_NAME);
    }

    public SPWorldData(String name) {
        super(name);
    }

    public static SPWorldData get(World world) {
        if (world == null) {
            return null;
        }
        if (world.field_72995_K) {
            return clientInstance;
        }
        MapStorage storage = world.getPerWorldStorage();
        instance = (SPWorldData)storage.func_75742_a(SPWorldData.class, DATA_NAME);
        if (instance == null) {
            instance = SPWorldData.create(world, storage);
        } else {
            instance.updateDays(world);
        }
        return instance;
    }

    private static SPWorldData create(World world, MapStorage storage) {
        System.out.println("----creating world data data------------------ ");
        instance = new SPWorldData();
        storage.func_75745_a(DATA_NAME, (WorldSavedData)instance);
        boolean flag = true;
        for (int i : SPConfigWorld.meteorBlacklistDims) {
            if (i != world.field_73011_w.getDimension()) continue;
            flag = false;
            break;
        }
        if (flag) {
            instance.setTriggerMet(SPWorldEntitySpawner.triggerSPAWNING || SPConfigWorld.meteorActive);
        } else {
            instance.setTriggerMet(false);
        }
        instance.func_76185_a();
        return instance;
    }

    public void resetInstance(World world) {
        MapStorage storage = world.getPerWorldStorage();
        instance = SPWorldData.create(world, storage);
    }

    public void func_76184_a(NBTTagCompound compound) {
        int type;
        NBTTagCompound tagT;
        int age;
        NBTTagCompound tagA;
        int coordz;
        NBTTagCompound tagZ;
        int coordy;
        NBTTagCompound tagY;
        int coordx;
        NBTTagCompound tagX;
        int i;
        NBTTagList tagListT;
        NBTTagList tagListA;
        NBTTagList tagListZ;
        NBTTagList tagListY;
        NBTTagList tagListX;
        if (compound.func_74764_b("srpmeteor")) {
            this.dimMeteor = compound.func_74767_n("srpmeteor");
        }
        if (compound.func_74764_b("srpnodescoordsx")) {
            tagListX = compound.func_150295_c("srpnodescoordsx", 10);
            tagListY = compound.func_150295_c("srpnodescoordsy", 10);
            tagListZ = compound.func_150295_c("srpnodescoordsz", 10);
            tagListA = compound.func_150295_c("srpnodesages", 10);
            tagListT = compound.func_150295_c("srpnodestypes", 10);
            if (tagListX.func_74745_c() != tagListY.func_74745_c() || tagListX.func_74745_c() != tagListZ.func_74745_c() || tagListX.func_74745_c() != tagListA.func_74745_c() || tagListX.func_74745_c() != tagListT.func_74745_c()) {
                SPMain.logger.log(Level.ERROR, "Problem while reading nodes coords");
            } else {
                for (i = 0; i < tagListX.func_74745_c(); ++i) {
                    tagX = tagListX.func_150305_b(i);
                    coordx = tagX.func_74762_e("nodex" + i);
                    this.nodeX.add(i, coordx);
                    tagY = tagListY.func_150305_b(i);
                    coordy = tagY.func_74762_e("nodey" + i);
                    this.nodeY.add(i, coordy);
                    tagZ = tagListZ.func_150305_b(i);
                    coordz = tagZ.func_74762_e("nodez" + i);
                    this.nodeZ.add(i, coordz);
                    tagA = tagListA.func_150305_b(i);
                    age = tagA.func_74762_e("nodea" + i);
                    this.nodeA.add(i, age);
                    tagT = tagListT.func_150305_b(i);
                    type = tagT.func_74762_e("nodet" + i);
                    this.nodeT.add(i, (byte)type);
                }
            }
        }
        if (compound.func_74764_b("srporiginscoordsx")) {
            tagListX = compound.func_150295_c("srporiginscoordsx", 10);
            tagListY = compound.func_150295_c("srporiginscoordsy", 10);
            tagListZ = compound.func_150295_c("srporiginscoordsz", 10);
            tagListA = compound.func_150295_c("srporiginsareas", 10);
            tagListT = compound.func_150295_c("srporiginshealths", 10);
            if (tagListX.func_74745_c() != tagListY.func_74745_c() || tagListX.func_74745_c() != tagListZ.func_74745_c() || tagListX.func_74745_c() != tagListA.func_74745_c() || tagListX.func_74745_c() != tagListT.func_74745_c()) {
                SPMain.logger.log(Level.ERROR, "Problem while reading origins coords");
            } else {
                for (i = 0; i < tagListX.func_74745_c(); ++i) {
                    tagX = tagListX.func_150305_b(i);
                    coordx = tagX.func_74762_e("originx" + i);
                    this.originX.add(i, coordx);
                    tagY = tagListY.func_150305_b(i);
                    coordy = tagY.func_74762_e("originy" + i);
                    this.originY.add(i, coordy);
                    tagZ = tagListZ.func_150305_b(i);
                    coordz = tagZ.func_74762_e("originz" + i);
                    this.originZ.add(i, coordz);
                    tagA = tagListA.func_150305_b(i);
                    age = tagA.func_74762_e("origina" + i);
                    this.originA.add(i, age);
                    tagT = tagListT.func_150305_b(i);
                    type = tagT.func_74762_e("originh" + i);
                    this.originH.add(i, type);
                }
            }
        }
        if (compound.func_74764_b("srpcoloniescoordsx")) {
            tagListX = compound.func_150295_c("srpcoloniescoordsx", 10);
            tagListY = compound.func_150295_c("srpcoloniescoordsy", 10);
            tagListZ = compound.func_150295_c("srpcoloniescoordsz", 10);
            tagListA = compound.func_150295_c("srpcoloniesages", 10);
            if (tagListX.func_74745_c() != tagListY.func_74745_c() || tagListX.func_74745_c() != tagListZ.func_74745_c() || tagListX.func_74745_c() != tagListA.func_74745_c()) {
                SPMain.logger.log(Level.ERROR, "Problem while reading colonies coords");
            } else {
                for (int i2 = 0; i2 < tagListX.func_74745_c(); ++i2) {
                    NBTTagCompound tagX2 = tagListX.func_150305_b(i2);
                    int coordx2 = tagX2.func_74762_e("colonyx" + i2);
                    this.colonyX.add(i2, coordx2);
                    NBTTagCompound tagY2 = tagListY.func_150305_b(i2);
                    int coordy2 = tagY2.func_74762_e("colonyy" + i2);
                    this.colonyY.add(i2, coordy2);
                    NBTTagCompound tagZ2 = tagListZ.func_150305_b(i2);
                    int coordz2 = tagZ2.func_74762_e("colonyz" + i2);
                    this.colonyZ.add(i2, coordz2);
                    NBTTagCompound tagA2 = tagListA.func_150305_b(i2);
                    int age2 = tagA2.func_74762_e("colonya" + i2);
                    this.colonyA.add(i2, age2);
                }
            }
        }
        if (compound.func_74764_b("srpcolonyresistances")) {
            tagListX = compound.func_150295_c("srpcolonyresistancei", 10);
            tagListY = compound.func_150295_c("srpcolonyresistances", 10);
            if (tagListX.func_74745_c() != tagListY.func_74745_c()) {
                SPMain.logger.log(Level.ERROR, "Problem while reading resistance");
            } else {
                for (int i3 = 0; i3 < tagListX.func_74745_c(); ++i3) {
                    NBTTagCompound tagX3 = tagListX.func_150305_b(i3);
                    int coordx3 = tagX3.func_74762_e("resistance" + i3);
                    this.resistanceI.add(i3, coordx3);
                    NBTTagCompound tagY3 = tagListY.func_150305_b(i3);
                    String coordy3 = tagY3.func_74779_i("resistance" + i3);
                    this.resistanceS.add(i3, coordy3);
                }
            }
        }
    }

    public NBTTagCompound func_189551_b(NBTTagCompound compound) {
        NBTTagCompound tagT;
        NBTTagCompound tagA;
        NBTTagCompound tagZ;
        NBTTagCompound tagY;
        NBTTagCompound tagX;
        int type;
        int age;
        int coordz;
        int coordy;
        int coordx;
        int i;
        NBTTagList tagListT;
        NBTTagList tagListA;
        NBTTagList tagListZ;
        NBTTagList tagListY;
        NBTTagList tagListX;
        compound.func_74757_a("srpmeteor", SPWorldEntitySpawner.triggerSPAWNING);
        if (this.nodeX.size() != this.nodeY.size() || this.nodeX.size() != this.nodeZ.size() || this.nodeX.size() != this.nodeA.size() || this.nodeX.size() != this.nodeT.size()) {
            SPMain.logger.log(Level.ERROR, "Problem while writing nodes coords");
        } else {
            tagListX = new NBTTagList();
            tagListY = new NBTTagList();
            tagListZ = new NBTTagList();
            tagListA = new NBTTagList();
            tagListT = new NBTTagList();
            for (i = 0; i < this.nodeX.size(); ++i) {
                coordx = this.nodeX.get(i);
                coordy = this.nodeY.get(i);
                coordz = this.nodeZ.get(i);
                age = this.nodeA.get(i);
                type = this.nodeT.get(i).byteValue();
                tagX = new NBTTagCompound();
                tagX.func_74768_a("nodex" + i, coordx);
                tagListX.func_74742_a((NBTBase)tagX);
                tagY = new NBTTagCompound();
                tagY.func_74768_a("nodey" + i, coordy);
                tagListY.func_74742_a((NBTBase)tagY);
                tagZ = new NBTTagCompound();
                tagZ.func_74768_a("nodez" + i, coordz);
                tagListZ.func_74742_a((NBTBase)tagZ);
                tagA = new NBTTagCompound();
                tagA.func_74768_a("nodea" + i, age);
                tagListA.func_74742_a((NBTBase)tagA);
                tagT = new NBTTagCompound();
                tagT.func_74768_a("nodet" + i, type);
                tagListT.func_74742_a((NBTBase)tagT);
            }
            compound.func_74782_a("srpnodescoordsx", (NBTBase)tagListX);
            compound.func_74782_a("srpnodescoordsy", (NBTBase)tagListY);
            compound.func_74782_a("srpnodescoordsz", (NBTBase)tagListZ);
            compound.func_74782_a("srpnodesages", (NBTBase)tagListA);
            compound.func_74782_a("srpnodestypes", (NBTBase)tagListT);
        }
        if (this.originX.size() != this.originY.size() || this.originX.size() != this.originZ.size() || this.originX.size() != this.originA.size() || this.originX.size() != this.originH.size()) {
            SPMain.logger.log(Level.ERROR, "Problem while writing origins coords");
        } else {
            tagListX = new NBTTagList();
            tagListY = new NBTTagList();
            tagListZ = new NBTTagList();
            tagListA = new NBTTagList();
            tagListT = new NBTTagList();
            for (i = 0; i < this.originX.size(); ++i) {
                coordx = this.originX.get(i);
                coordy = this.originY.get(i);
                coordz = this.originZ.get(i);
                age = this.originA.get(i);
                type = this.originH.get(i);
                tagX = new NBTTagCompound();
                tagX.func_74768_a("originx" + i, coordx);
                tagListX.func_74742_a((NBTBase)tagX);
                tagY = new NBTTagCompound();
                tagY.func_74768_a("originy" + i, coordy);
                tagListY.func_74742_a((NBTBase)tagY);
                tagZ = new NBTTagCompound();
                tagZ.func_74768_a("originz" + i, coordz);
                tagListZ.func_74742_a((NBTBase)tagZ);
                tagA = new NBTTagCompound();
                tagA.func_74768_a("origina" + i, age);
                tagListA.func_74742_a((NBTBase)tagA);
                tagT = new NBTTagCompound();
                tagT.func_74768_a("originh" + i, type);
                tagListT.func_74742_a((NBTBase)tagT);
            }
            compound.func_74782_a("srporiginscoordsx", (NBTBase)tagListX);
            compound.func_74782_a("srporiginscoordsy", (NBTBase)tagListY);
            compound.func_74782_a("srporiginscoordsz", (NBTBase)tagListZ);
            compound.func_74782_a("srporiginsareas", (NBTBase)tagListA);
            compound.func_74782_a("srporiginshealths", (NBTBase)tagListT);
        }
        if (this.colonyX.size() != this.colonyY.size() || this.colonyX.size() != this.colonyZ.size() || this.colonyX.size() != this.colonyA.size()) {
            SPMain.logger.log(Level.ERROR, "Problem while writing colonies coords");
        } else {
            tagListX = new NBTTagList();
            tagListY = new NBTTagList();
            tagListZ = new NBTTagList();
            tagListA = new NBTTagList();
            for (int i2 = 0; i2 < this.colonyX.size(); ++i2) {
                int coordx2 = this.colonyX.get(i2);
                int coordy2 = this.colonyY.get(i2);
                int coordz2 = this.colonyZ.get(i2);
                int age2 = this.colonyA.get(i2);
                NBTTagCompound tagX2 = new NBTTagCompound();
                tagX2.func_74768_a("colonyx" + i2, coordx2);
                tagListX.func_74742_a((NBTBase)tagX2);
                NBTTagCompound tagY2 = new NBTTagCompound();
                tagY2.func_74768_a("colonyy" + i2, coordy2);
                tagListY.func_74742_a((NBTBase)tagY2);
                NBTTagCompound tagZ2 = new NBTTagCompound();
                tagZ2.func_74768_a("colonyz" + i2, coordz2);
                tagListZ.func_74742_a((NBTBase)tagZ2);
                NBTTagCompound tagA2 = new NBTTagCompound();
                tagA2.func_74768_a("colonya" + i2, age2);
                tagListA.func_74742_a((NBTBase)tagA2);
            }
            compound.func_74782_a("srpcoloniescoordsx", (NBTBase)tagListX);
            compound.func_74782_a("srpcoloniescoordsy", (NBTBase)tagListY);
            compound.func_74782_a("srpcoloniescoordsz", (NBTBase)tagListZ);
            compound.func_74782_a("srpcoloniesages", (NBTBase)tagListA);
        }
        if (this.resistanceI.size() != this.resistanceS.size()) {
            SPMain.logger.log(Level.ERROR, "Problem while writing resistance");
        } else {
            tagListX = new NBTTagList();
            tagListY = new NBTTagList();
            for (int i3 = 0; i3 < this.resistanceI.size(); ++i3) {
                int coordx3 = this.resistanceI.get(i3);
                String coordy3 = this.resistanceS.get(i3);
                NBTTagCompound tagX3 = new NBTTagCompound();
                tagX3.func_74768_a("resistance" + i3, coordx3);
                tagListX.func_74742_a((NBTBase)tagX3);
                NBTTagCompound tagY3 = new NBTTagCompound();
                tagY3.func_74778_a("resistance" + i3, coordy3);
                tagListY.func_74742_a((NBTBase)tagY3);
            }
            compound.func_74782_a("srpcolonyresistancei", (NBTBase)tagListX);
            compound.func_74782_a("srpcolonyresistances", (NBTBase)tagListY);
        }
        return compound;
    }

    public void setTriggerMet(boolean in) {
        this.dimMeteor = in;
    }

    public boolean getTriggerMet() {
        return this.dimMeteor;
    }

    private long getDistanceSQ(double rootx, double rooty, double rootz, double standingx, double standingy, double standingz) {
        double d0 = rootx - standingx;
        double d1 = rooty - standingy;
        double d2 = rootz - standingz;
        return (long)(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public void clearOriginList() {
        this.originX = new ArrayList();
        this.originY = new ArrayList();
        this.originZ = new ArrayList();
        this.originA = new ArrayList();
        this.originH = new ArrayList();
        this.func_76185_a();
    }

    public ArrayList<Integer> getorigins(String i) {
        if (i.equals("x")) {
            return this.originX;
        }
        if (i.equals("y")) {
            return this.originY;
        }
        if (i.equals("z")) {
            return this.originZ;
        }
        if (i.equals("a")) {
            return this.originA;
        }
        if (i.equals("h")) {
            return this.originH;
        }
        return null;
    }

    public int setOrigin(World world, int x, int y, int z, int heatlh, int radius) {
        if (!this.canOriginBeMade(new BlockPos(x, y, z))) {
            return 6;
        }
        SPSaveData data = SPSaveData.get(world, 201);
        if (this.originX.size() >= this.getOriginCap(world, data)) {
            return 7;
        }
        int canAdd = 1;
        for (int i = 0; i < this.originX.size(); ++i) {
            if (this.originX.get(i) != x || this.originY.get(i) != y || this.originZ.get(i) != z) continue;
            canAdd = 10;
        }
        if (canAdd == 1) {
            this.originX.add(x);
            this.originY.add(y);
            this.originZ.add(z);
            this.originA.add(radius);
            this.originH.add(heatlh);
            this.func_76185_a();
            this.setEIVHealthToData(world, data);
        }
        if (data.getEvolutionPhase(world.field_73011_w.getDimension()) == -1) {
            canAdd = 2;
        }
        return canAdd;
    }

    private int getOriginCap(World in, SPSaveData data) {
        if (!SPConfigSystems.useEvolution) {
            return SPConfigWorld.originCap;
        }
        switch (data.getEvolutionPhase(in.field_73011_w.getDimension())) {
            case -1: {
                return SPConfigSystems.phaseOriginMinusOne;
            }
            case 0: {
                return SPConfigSystems.phaseOriginZero;
            }
            case 1: {
                return SPConfigSystems.phaseOriginOne;
            }
            case 2: {
                return SPConfigSystems.phaseOriginTwo;
            }
            case 3: {
                return SPConfigSystems.phaseOriginThree;
            }
            case 4: {
                return SPConfigSystems.phaseOriginFour;
            }
            case 5: {
                return SPConfigSystems.phaseOriginFive;
            }
            case 6: {
                return SPConfigSystems.phaseOriginSix;
            }
            case 7: {
                return SPConfigSystems.phaseOriginSeven;
            }
            case 8: {
                return SPConfigSystems.phaseOriginEight;
            }
            case 9: {
                return SPConfigSystems.phaseOriginNine;
            }
            case 10: {
                return SPConfigSystems.phaseOriginTen;
            }
        }
        return 0;
    }

    public boolean canOriginBeMade(BlockPos pos) {
        int distance = SPConfigWorld.originMinimumDistance * SPConfigWorld.originMinimumDistance;
        for (int i = 0; i < this.originX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            if (!(distanceSQ <= (double)distance)) continue;
            return false;
        }
        return true;
    }

    public boolean removeOrigin(int x, int y, int z, World worldIn) {
        for (int i = 0; i < this.originX.size(); ++i) {
            if (this.originX.get(i) != x || this.originY.get(i) != y || this.originZ.get(i) != z) continue;
            this.originX.remove(i);
            this.originY.remove(i);
            this.originZ.remove(i);
            this.originA.remove(i);
            this.originH.remove(i);
            this.func_76185_a();
            this.setEIVHealthToData(worldIn, SPSaveData.get(worldIn, 260));
            return true;
        }
        return false;
    }

    public int nearestInfectionValue(BlockPos pos, boolean health) {
        int heart = this.nearestInfectionIndex(pos);
        if (heart > -1) {
            if (health) {
                return this.originH.get(heart);
            }
            return this.originA.get(heart);
        }
        return heart;
    }

    public int nearestInfectionValueArea(BlockPos pos, boolean health) {
        int heart = this.nearestInfectionPositionIndex(true, pos);
        if (heart > -1) {
            if (health) {
                return this.originH.get(heart);
            }
            return this.originA.get(heart);
        }
        return heart;
    }

    public BlockPos nearestInfectionPosition(Boolean outside, BlockPos pos) {
        BlockPos heart = null;
        double distance = -1.0;
        for (int i = 0; i < this.originX.size(); ++i) {
            long distanceSQ = this.getDistanceSQ(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            long ageDistance = this.originA.get(i).intValue();
            if (distanceSQ > (ageDistance *= ageDistance) && (!outside.booleanValue() || distance != -1.0)) continue;
            if (distance == -1.0) {
                heart = new BlockPos(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue());
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= (double)distanceSQ)) continue;
            heart = new BlockPos(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue());
            distance = distanceSQ;
        }
        return heart;
    }

    public int nearestInfectionPositionIndex(Boolean outside, BlockPos pos) {
        int heart = -1;
        double distance = -1.0;
        for (int i = 0; i < this.originX.size(); ++i) {
            long distanceSQ = this.getDistanceSQ(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            long ageDistance = this.originA.get(i).intValue();
            if (distanceSQ > (ageDistance *= ageDistance) && (!outside.booleanValue() || distance != -1.0)) continue;
            if (distance == -1.0) {
                heart = i;
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= (double)distanceSQ)) continue;
            heart = i;
            distance = distanceSQ;
        }
        return heart;
    }

    public int nearestInfectionIndex(BlockPos pos) {
        int heart = -1;
        double distance = -1.0;
        for (int i = 0; i < this.originX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            int ageDistance = this.originA.get(i);
            if (!(distanceSQ <= (double)(ageDistance *= ageDistance))) continue;
            if (distance == -1.0) {
                heart = i;
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= distanceSQ)) continue;
            heart = i;
            distance = distanceSQ;
        }
        return heart;
    }

    public void updateOriginValues(World world, int updates, SPSaveData data) {
        int dim = world.field_73011_w.getDimension();
        byte phase = data.getEvolutionPhase(dim);
        while (updates > 0) {
            for (int i = 0; i < this.originA.size(); ++i) {
                int size = this.originA.get(i);
                int health = this.originH.get(i);
                size = (int)((double)size + (double)size * (SPConfigWorld.originDailySize + SPWorldData.getPhaseSize(phase)));
                health = (int)((double)health + (double)size * (SPConfigWorld.originDailyHealth + SPWorldData.getPhaseHealth(phase)));
                this.originA.set(i, Math.min(size, SPConfigWorld.originRadiusCap));
                this.originH.set(i, Math.min(health, SPConfigWorld.originHealthCap));
            }
            --updates;
        }
        this.func_76185_a();
        this.setEIVHealthToData(world, data);
    }

    public void setEIVHealthToData(World in, SPSaveData data) {
        int health = 0;
        int area = 0;
        for (int i = 0; i < this.originH.size(); ++i) {
            health += this.originH.get(i).intValue();
            area += this.originA.get(i).intValue();
        }
        data.setEIVHealthArea(in.field_73011_w.getDimension(), health, area);
    }

    public static double getPhaseSize(int in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseOriginBonusSizeOne;
            }
            case 2: {
                return SPConfigSystems.phaseOriginBonusSizeTwo;
            }
            case 3: {
                return SPConfigSystems.phaseOriginBonusSizeThree;
            }
            case 4: {
                return SPConfigSystems.phaseOriginBonusSizeFour;
            }
            case 5: {
                return SPConfigSystems.phaseOriginBonusSizeFive;
            }
            case 6: {
                return SPConfigSystems.phaseOriginBonusSizeSix;
            }
            case 7: {
                return SPConfigSystems.phaseOriginBonusSizeSeven;
            }
            case 8: {
                return SPConfigSystems.phaseOriginBonusSizeEight;
            }
            case 9: {
                return SPConfigSystems.phaseOriginBonusSizeNine;
            }
            case 10: {
                return SPConfigSystems.phaseOriginBonusSizeTen;
            }
        }
        return 0.0;
    }

    public static double getPhaseHealth(int in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseOriginBonusHealthOne;
            }
            case 2: {
                return SPConfigSystems.phaseOriginBonusHealthTwo;
            }
            case 3: {
                return SPConfigSystems.phaseOriginBonusHealthThree;
            }
            case 4: {
                return SPConfigSystems.phaseOriginBonusHealthFour;
            }
            case 5: {
                return SPConfigSystems.phaseOriginBonusHealthFive;
            }
            case 6: {
                return SPConfigSystems.phaseOriginBonusHealthSix;
            }
            case 7: {
                return SPConfigSystems.phaseOriginBonusHealthSeven;
            }
            case 8: {
                return SPConfigSystems.phaseOriginBonusHealthEight;
            }
            case 9: {
                return SPConfigSystems.phaseOriginBonusHealthNine;
            }
            case 10: {
                return SPConfigSystems.phaseOriginBonusHealthTen;
            }
        }
        return 0.0;
    }

    public boolean setOriginHealth(World worldIn, BlockPos pos, int amount, boolean plus) {
        int healthID = -1;
        double distance = -1.0;
        for (int i = 0; i < this.originX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.originX.get(i).intValue(), this.originY.get(i).intValue(), this.originZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            int ageDistance = this.originA.get(i);
            if (!(distanceSQ <= (double)(ageDistance *= ageDistance))) continue;
            if (distance == -1.0) {
                healthID = i;
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= distanceSQ)) continue;
            healthID = i;
            distance = distanceSQ;
        }
        if (healthID != -1) {
            SPSaveData data = SPSaveData.get(worldIn, 260);
            if (plus) {
                int current;
                if (data.getEvolutionPhase(worldIn.field_73011_w.getDimension()) == -1 && amount > 0) {
                    amount = (int)((double)amount * SPConfigSystems.phaseOriginMinusOnePenalty);
                }
                if ((current = this.originH.get(healthID) + amount) <= 0) {
                    this.originX.remove(healthID);
                    this.originY.remove(healthID);
                    this.originZ.remove(healthID);
                    this.originA.remove(healthID);
                    this.originH.remove(healthID);
                } else {
                    this.originH.set(healthID, current);
                }
            } else if (amount <= 0) {
                this.originX.remove(healthID);
                this.originY.remove(healthID);
                this.originZ.remove(healthID);
                this.originA.remove(healthID);
                this.originH.remove(healthID);
            } else {
                this.originH.set(healthID, amount);
            }
            this.func_76185_a();
            this.setEIVHealthToData(worldIn, data);
            return true;
        }
        return false;
    }

    public void clearNodeList() {
        this.nodeX = new ArrayList();
        this.nodeY = new ArrayList();
        this.nodeZ = new ArrayList();
        this.nodeA = new ArrayList();
        this.nodeT = new ArrayList();
        this.func_76185_a();
    }

    public ArrayList<Integer> getNodes(String i) {
        if (i.equals("x")) {
            return this.nodeX;
        }
        if (i.equals("y")) {
            return this.nodeY;
        }
        if (i.equals("z")) {
            return this.nodeZ;
        }
        if (i.equals("a")) {
            return this.nodeA;
        }
        if (i.equals("t")) {
            ArrayList<Integer> eee = new ArrayList<Integer>();
            for (byte b : this.nodeT) {
                eee.add(Integer.valueOf(b));
            }
            return eee;
        }
        return null;
    }

    public int setNode(int x, int y, int z, int type) {
        if (!this.canNodeBeMade(new BlockPos(x, y, z))) {
            return 8;
        }
        if (this.nodeX.size() >= SPConfigWorld.maximumNumberNodes) {
            return 9;
        }
        int canAdd = 1;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            if (this.nodeX.get(i) != x || this.nodeY.get(i) != y || this.nodeZ.get(i) != z) continue;
            if (this.nodeA.get(i) != 1) {
                this.removeNode(x, y, z);
                this.setNode(x, y, z, type);
            }
            canAdd = 10;
        }
        if (canAdd == 1) {
            this.nodeX.add(x);
            this.nodeY.add(y);
            this.nodeZ.add(z);
            this.nodeA.add(1);
            this.nodeT.add((byte)type);
            this.func_76185_a();
        }
        return canAdd;
    }

    public boolean canNodeBeMade(BlockPos pos) {
        int distance = SPConfigWorld.minimumDistanceBetweenNodes * SPConfigWorld.minimumDistanceBetweenNodes;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            if (!(distanceSQ <= (double)distance)) continue;
            return false;
        }
        return true;
    }

    public boolean removeNode(int x, int y, int z) {
        for (int i = 0; i < this.nodeX.size(); ++i) {
            if (this.nodeX.get(i) != x || this.nodeY.get(i) != y || this.nodeZ.get(i) != z) continue;
            this.nodeX.remove(i);
            this.nodeY.remove(i);
            this.nodeZ.remove(i);
            this.nodeA.remove(i);
            this.nodeT.remove(i);
            this.func_76185_a();
            return true;
        }
        return false;
    }

    public int isInRangeOfHeart(BlockPos pos, double distance) {
        distance += 1.0;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            if (!(distanceSQ < distance * distance)) continue;
            return (int)distanceSQ;
        }
        return -1;
    }

    public int nearestHeartAge(BlockPos pos, boolean spread, int currentDay) {
        int heart = -1;
        double distance = -1.0;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            int ageDistance;
            double distanceSQ = this.getDistanceSQ(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            int currentage = ageDistance = this.convertDayToAgeNode(this.nodeA.get(i));
            ageDistance = spread ? this.getDistanceSpreadByAge(ageDistance, true) : this.getDistanceEffectByAge(ageDistance, true);
            if (!(distanceSQ <= (double)ageDistance)) continue;
            if (distance == -1.0) {
                heart = currentage;
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= distanceSQ)) continue;
            heart = currentage;
            distance = distanceSQ;
        }
        return heart;
    }

    public int nearestHeartType(BlockPos pos, boolean spread, int currentDay) {
        int type = -1;
        double distance = -1.0;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            int ageDistance;
            double distanceSQ = this.getDistanceSQ(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            int currentage = ageDistance = this.convertDayToAgeNode(this.nodeA.get(i));
            ageDistance = spread ? this.getDistanceSpreadByAge(ageDistance, true) : this.getDistanceEffectByAge(ageDistance, true);
            if (!(distanceSQ <= (double)ageDistance)) continue;
            if (distance == -1.0) {
                type = this.nodeT.get(i).byteValue();
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= distanceSQ)) continue;
            type = this.nodeT.get(i).byteValue();
            distance = distanceSQ;
        }
        return type;
    }

    public BlockPos nearestHeartAgePosition(BlockPos pos, int currentDay) {
        BlockPos heart = null;
        double distance = -1.0;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            int ageDistance;
            double distanceSQ = this.getDistanceSQ(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            int currentage = ageDistance = this.convertDayToAgeNode(this.nodeA.get(i));
            if (!(distanceSQ <= (double)(ageDistance = this.getDistanceEffectByAge(ageDistance, true)))) continue;
            if (distance == -1.0) {
                heart = new BlockPos(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue());
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= distanceSQ)) continue;
            heart = new BlockPos(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue());
            distance = distanceSQ;
        }
        return heart;
    }

    private int convertDayToAgeNode(int nodeBirthday) {
        int age = nodeBirthday;
        if (age >= SPConfigWorld.timeNeedeToNodeThree) {
            return 3;
        }
        if (age >= SPConfigWorld.timeNeedeToNodeTwo) {
            return 2;
        }
        return 1;
    }

    public int getDistanceSpreadByAge(int age, boolean sq) {
        switch (age) {
            case 1: {
                if (sq) {
                    return SPConfigWorld.nodeRangeSpreadOne * SPConfigWorld.nodeRangeSpreadOne;
                }
                return SPConfigWorld.nodeRangeSpreadOne;
            }
            case 2: {
                if (sq) {
                    return SPConfigWorld.nodeRangeSpreadTwo * SPConfigWorld.nodeRangeSpreadTwo;
                }
                return SPConfigWorld.nodeRangeSpreadTwo;
            }
            case 3: {
                if (sq) {
                    return SPConfigWorld.nodeRangeSpreadThree * SPConfigWorld.nodeRangeSpreadThree;
                }
                return SPConfigWorld.nodeRangeSpreadThree;
            }
        }
        return -1;
    }

    public int getDistanceEffectByAge(int age, boolean sq) {
        switch (age) {
            case 1: {
                if (sq) {
                    return SPConfigWorld.nodeRangeEffectsOne * SPConfigWorld.nodeRangeEffectsOne;
                }
                return SPConfigWorld.nodeRangeEffectsOne;
            }
            case 2: {
                if (sq) {
                    return SPConfigWorld.nodeRangeEffectsTwo * SPConfigWorld.nodeRangeEffectsTwo;
                }
                return SPConfigWorld.nodeRangeEffectsTwo;
            }
            case 3: {
                if (sq) {
                    return SPConfigWorld.nodeRangeEffectsThree * SPConfigWorld.nodeRangeEffectsThree;
                }
                return SPConfigWorld.nodeRangeEffectsThree;
            }
        }
        return -1;
    }

    public void checkHeartExistance(World worldIn) {
        for (int i = 0; i < this.nodeX.size(); ++i) {
            BlockPos pos = new BlockPos(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue());
            if (!worldIn.func_175697_a(pos, 3) || worldIn.func_180495_p(pos).func_177230_c() == SPBlocks.BiomeHeart) continue;
            this.removeNode(this.nodeX.get(i), this.nodeY.get(i), this.nodeZ.get(i));
        }
    }

    public int getHeartPocition(BlockPos pos, int currentDay) {
        for (int i = 0; i < this.nodeX.size(); ++i) {
            if (this.nodeX.get(i).intValue() != pos.func_177958_n() || this.nodeY.get(i).intValue() != pos.func_177956_o() || this.nodeZ.get(i).intValue() != pos.func_177952_p()) continue;
            return this.convertDayToAgeNode(this.nodeA.get(i));
        }
        return -1;
    }

    public int totalNodePoints(int currentDay) {
        int points = 0;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            int age = this.convertDayToAgeNode(this.nodeA.get(i));
            points += age;
        }
        return points;
    }

    public void clearColonyList() {
        this.colonyX = new ArrayList();
        this.colonyY = new ArrayList();
        this.colonyZ = new ArrayList();
        this.colonyA = new ArrayList();
        this.func_76185_a();
    }

    public ArrayList<Integer> getColonies(String i) {
        if (i.equals("x")) {
            return this.colonyX;
        }
        if (i.equals("y")) {
            return this.colonyY;
        }
        if (i.equals("z")) {
            return this.colonyZ;
        }
        if (i.equals("a")) {
            return this.colonyA;
        }
        return null;
    }

    public int setColony(int x, int y, int z) {
        if (!this.canColonyBeMade(new BlockPos(x, y, z))) {
            return 6;
        }
        if (this.colonyX.size() >= SPConfigWorld.maximumNumberColonies) {
            return 7;
        }
        int canAdd = 1;
        for (int i = 0; i < this.colonyX.size(); ++i) {
            if (this.colonyX.get(i) != x || this.colonyY.get(i) != y || this.colonyZ.get(i) != z) continue;
            if (this.colonyA.get(i) != 1) {
                this.removeColony(x, y, z);
                this.setColony(x, y, z);
            }
            canAdd = 10;
        }
        if (canAdd == 1) {
            this.colonyX.add(x);
            this.colonyY.add(y);
            this.colonyZ.add(z);
            this.colonyA.add(1);
            this.func_76185_a();
        }
        return canAdd;
    }

    public boolean canColonyBeMade(BlockPos pos) {
        int distance = SPConfigWorld.minimumDistanceBetweenColonies * SPConfigWorld.minimumDistanceBetweenColonies;
        for (int i = 0; i < this.colonyX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.colonyX.get(i).intValue(), this.colonyY.get(i).intValue(), this.colonyZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            if (!(distanceSQ <= (double)distance)) continue;
            return false;
        }
        return true;
    }

    public boolean removeColony(int x, int y, int z) {
        for (int i = 0; i < this.colonyX.size(); ++i) {
            if (this.colonyX.get(i) != x || this.colonyY.get(i) != y || this.colonyZ.get(i) != z) continue;
            this.colonyX.remove(i);
            this.colonyY.remove(i);
            this.colonyZ.remove(i);
            this.colonyA.remove(i);
            this.func_76185_a();
            return true;
        }
        return false;
    }

    public BlockPos nearestColonyPosition(BlockPos pos, boolean effect) {
        BlockPos posColony = null;
        double distance = -1.0;
        for (int i = 0; i < this.colonyX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.colonyX.get(i).intValue(), this.colonyY.get(i).intValue(), this.colonyZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            int ageDistance = this.convertDayToPointsColony(this.colonyA.get(i));
            if (!(distanceSQ <= (double)(ageDistance = this.getColonyDistanceSpreadByPoints(ageDistance, true, effect)))) continue;
            if (distance == -1.0) {
                posColony = new BlockPos(this.colonyX.get(i).intValue(), this.colonyY.get(i).intValue(), this.colonyZ.get(i).intValue());
                distance = distanceSQ;
                continue;
            }
            if (!(distance <= distanceSQ)) continue;
            posColony = new BlockPos(this.colonyX.get(i).intValue(), this.colonyY.get(i).intValue(), this.colonyZ.get(i).intValue());
            distance = distanceSQ;
        }
        return posColony;
    }

    private int convertDayToPointsColony(int nodeBirthday) {
        int age = nodeBirthday;
        return Math.min(age, SPConfigWorld.colonyPointCap);
    }

    public int colonunumber() {
        return this.colonyA.size();
    }

    public int getColonyDistanceSpreadByPoints(int points, boolean sq, boolean effect) {
        if (effect) {
            int baseRange = SPConfigWorld.colonyBaseEffectRadiusValue;
            int a = points / SPConfigWorld.colonySpreadEffectPoint;
            int b = a * SPConfigWorld.colonySpreadEffectValue + baseRange;
            if (sq) {
                return b * b;
            }
            return b;
        }
        int baseRange = SPConfigWorld.colonyBaseRadiusValue;
        int a = points / SPConfigWorld.colonySpreadPoint;
        int b = a * SPConfigWorld.colonySpreadValue + baseRange;
        if (sq) {
            return b * b;
        }
        return b;
    }

    public int getColonyDistanceSpreadByPosition(BlockPos pos, boolean sq) {
        int x = pos.func_177958_n();
        int y = pos.func_177956_o();
        int z = pos.func_177952_p();
        for (int i = 0; i < this.colonyX.size(); ++i) {
            if (this.colonyX.get(i) != x || this.colonyY.get(i) != y || this.colonyZ.get(i) != z) continue;
            int ageDistance = this.convertDayToPointsColony(this.colonyA.get(i));
            ageDistance = this.getColonyDistanceSpreadByPoints(ageDistance, sq, false);
            return ageDistance;
        }
        return 0;
    }

    public BlockPos isInRangeOfColony(BlockPos pos, double distance) {
        distance += 1.0;
        for (int i = 0; i < this.nodeX.size(); ++i) {
            double distanceSQ = this.getDistanceSQ(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            if (!(distanceSQ < distance * distance)) continue;
            return new BlockPos(this.nodeX.get(i).intValue(), this.nodeY.get(i).intValue(), this.nodeZ.get(i).intValue());
        }
        return null;
    }

    public int totalColonyPoints(int currentDay) {
        int points = 0;
        for (int i = 0; i < this.colonyX.size(); ++i) {
            int age = this.convertDayToPointsColony(this.colonyA.get(i));
            points += age;
        }
        return Math.min(points, SPConfigWorld.colonyTotalPointCap);
    }

    public void checkColonyExistance(World worldIn) {
        for (int i = 0; i < this.colonyX.size(); ++i) {
            BlockPos pos = new BlockPos(this.colonyX.get(i).intValue(), this.colonyY.get(i).intValue(), this.colonyZ.get(i).intValue());
            if (!worldIn.func_175697_a(pos, 3) || worldIn.func_180495_p(pos).func_177230_c() == SPBlocks.ColonyHeart) continue;
            this.removeColony(this.colonyX.get(i), this.colonyY.get(i), this.colonyZ.get(i));
        }
    }

    public void addGlobalResistance(String damage) {
        boolean flag = true;
        for (int i = 0; i < this.resistanceS.size(); ++i) {
            if (!this.resistanceS.get(i).equals(damage)) continue;
            int iiii = this.resistanceI.get(i) + 1;
            this.resistanceI.set(i, iiii);
            flag = false;
            break;
        }
        if (flag) {
            this.resistanceS.add(damage);
            this.resistanceI.add(1);
        }
        this.func_76185_a();
    }

    public String getMostCommonDamageS() {
        ArrayList<String> resistanceAAAS = this.resistanceS;
        ArrayList<Integer> resistanceAAAI = this.resistanceI;
        String atm = null;
        int times = 0;
        for (int i = 0; i < resistanceAAAI.size(); ++i) {
            if (resistanceAAAI.get(i) <= times) continue;
            times = resistanceAAAI.get(i);
            atm = resistanceAAAS.get(i);
        }
        return atm;
    }

    public int getMostCommonDamageI() {
        ArrayList<Integer> resistanceAAAI = this.resistanceI;
        int times = 0;
        for (int i = 0; i < resistanceAAAI.size(); ++i) {
            if (resistanceAAAI.get(i) <= times) continue;
            times = resistanceAAAI.get(i);
        }
        return times;
    }

    public void resetGlobalAdaptation() {
        this.resistanceI = new ArrayList();
        this.resistanceS = new ArrayList();
        this.func_76185_a();
    }

    public ArrayList<String> getAdaptationS() {
        return this.resistanceS;
    }

    public ArrayList<Integer> getAdaptationI() {
        return this.resistanceI;
    }

    public void updateDays(World world) {
        int atm;
        int i;
        SPSaveData data = SPSaveData.get(world, 2120);
        int count = data.getUpdateNumber(world.field_73011_w.getDimension());
        if (count == 0) {
            return;
        }
        for (i = 0; i < this.nodeA.size(); ++i) {
            atm = this.nodeA.get(i) + count;
            this.nodeA.set(i, atm);
        }
        for (i = 0; i < this.colonyA.size(); ++i) {
            atm = this.colonyA.get(i) + count;
            this.colonyA.set(i, atm);
        }
        this.func_76185_a();
        this.updateOriginValues(world, count, data);
    }

    static {
        clientInstance = null;
    }
}

