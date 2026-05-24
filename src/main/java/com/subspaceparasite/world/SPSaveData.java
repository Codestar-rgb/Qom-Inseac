/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.storage.MapStorage
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.subspaceparasite.world;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.network.SPCommandDislodgment;
import com.subspaceparasite.network.SPCommandEvolution;
import com.subspaceparasite.network.SPPacketMusicTrackCancelUpdateClientEvoPhase;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.SPWorldEntitySpawner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SPSaveData
extends WorldSavedData {
    private static final Logger SP_LOG = LogManager.getLogger((String)"subspaceparasite");
    private static boolean warnedNoWorld = false;
    private int choice;
    private ArrayList<Integer> dimEPid = new ArrayList();
    private ArrayList<Integer> dimEPtotalKills = new ArrayList();
    private ArrayList<Byte> dimEPevolution = new ArrayList();
    private ArrayList<Integer> dimEPtimeEvolution = new ArrayList();
    private ArrayList<Boolean> dimEPcanGainPoints = new ArrayList();
    private ArrayList<Boolean> dimEPcanLossPoints = new ArrayList();
    private ArrayList<String> dimEPcurrentCodes = new ArrayList();
    private ArrayList<String> dimEPcurrentCodesDur = new ArrayList();
    private ArrayList<Byte> dimGeneration = new ArrayList();
    private ArrayList<Integer> dimGenerationTime = new ArrayList();
    private ArrayList<Integer> dimUpdates = new ArrayList();
    private ArrayList<Integer> dimEIVHealth = new ArrayList();
    private ArrayList<Integer> dimEIVArea = new ArrayList();
    private static final String DATA_NAME = "subspaceparasite_global_data";
    private static final String DISLO_R = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
    private static SPSaveData instance;
    private ArrayList<Integer> lockedParasites = new ArrayList();
    private ArrayList<Byte> simRegId = new ArrayList();
    private ArrayList<Integer> simRegIdTimes = new ArrayList();
    private static SPSaveData clientInstance;
    public static int falseLevel;

    @Nullable
    private static WorldServer getOverworldOrNull() {
        MinecraftServer srv = FMLCommonHandler.instance().getMinecraftServerInstance();
        return srv != null ? srv.func_71218_a(0) : null;
    }

    private static void warnNoWorldOnce(String where) {
        if (!warnedNoWorld) {
            warnedNoWorld = true;
            SP_LOG.warn("[SPSaveData] {} called with null world; skipping this tick until worlds are ready.", (Object)where);
        }
    }

    @Nullable
    private static WorldServer getWorldByDim(int dimId) {
        MinecraftServer srv = FMLCommonHandler.instance().getMinecraftServerInstance();
        return srv != null ? srv.func_71218_a(dimId) : null;
    }

    private static boolean debugPointConsole() {
        return SPConfigSystems.debugEvolutionPointsConsole;
    }

    private static boolean debugPointCsv() {
        return SPConfigSystems.debugEvolutionPointsCsv;
    }

    private static boolean debugPointCallerTrace() {
        return SPConfigSystems.debugEvolutionPointsCallerTrace;
    }

    private static String getPointSourceName(int srcID) {
        switch (srcID) {
            case 1: {
                return "EIV_DAILY_GROWTH";
            }
            case 2: {
                return "DIM_START_DEFAULT";
            }
            case 3: {
                return "DIM_START_OUTBREAK";
            }
            case 4: {
                return "KILL";
            }
            case 5: {
                return "ASSIMILATION";
            }
            case 6: {
                return "COMMAND";
            }
            case 7: {
                return "EVENT";
            }
            case 8: {
                return "COLONY";
            }
            case 9: {
                return "PENALTY_OR_LOSS";
            }
            case 52: {
                return "COTH_DURATION_REFRESH";
            }
        }
        return "UNKNOWN_" + srcID;
    }

    private static String[] getPointCallerInfo() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        boolean foundPointMutationMethod = false;
        for (StackTraceElement element : stack) {
            String className = element.getClassName();
            String methodName = element.getMethodName();
            if (className.endsWith("SPSaveData") && ("setTotalKills".equals(methodName) || "setCurrentCode".equals(methodName))) {
                foundPointMutationMethod = true;
                continue;
            }
            if (!foundPointMutationMethod || className.endsWith("SPSaveData")) continue;
            return new String[]{className, methodName, String.valueOf(element.getLineNumber())};
        }
        return new String[]{"unknown", "unknown", "-1"};
    }

    private static String csvSafe(String in) {
        if (in == null) {
            return "";
        }
        return "\"" + in.replace("\"", "\"\"") + "\"";
    }

    private static File getPointDebugFile(World worldIn, int dimID) {
        File folder;
        File root = DimensionManager.getCurrentSaveRootDirectory();
        if (root == null && worldIn != null && worldIn.func_72860_G() != null) {
            root = worldIn.func_72860_G().func_75765_b();
        }
        if (root == null) {
            root = new File(".");
        }
        if (!(folder = new File(root, "subspaceparasite_debug")).exists()) {
            folder.mkdirs();
        }
        return new File(folder, "evolution_points_dim" + dimID + ".csv");
    }

    private static void writePointCsv(World worldIn, int dimID, int srcID, String srcName, boolean plus, int requestedAmount, int processedAmount, int appliedDelta, int before, int after, int phaseBefore, int phaseAfter, boolean canChangePhase, boolean isFromSleep, String result) {
        if (!SPSaveData.debugPointCsv()) {
            return;
        }
        File file = SPSaveData.getPointDebugFile(worldIn, dimID);
        boolean writeHeader = !file.exists() || file.length() == 0L;
        long worldTime = worldIn != null ? worldIn.func_82737_E() : -1L;
        long day = worldTime >= 0L ? worldTime / 24000L : -1L;
        String callerClass = "";
        String callerMethod = "";
        String callerLine = "";
        if (SPSaveData.debugPointCallerTrace()) {
            String[] caller = SPSaveData.getPointCallerInfo();
            callerClass = caller[0];
            callerMethod = caller[1];
            callerLine = caller[2];
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));){
            if (writeHeader) {
                writer.write("real_time_ms,world_time,day,dim,src_id,src_name,caller_class,caller_method,caller_line,plus,requested_amount,processed_amount,applied_delta,before,after,phase_before,phase_after,can_change_phase,is_from_sleep,result");
                writer.newLine();
            }
            writer.write(System.currentTimeMillis() + "," + worldTime + "," + day + "," + dimID + "," + srcID + "," + SPSaveData.csvSafe(srcName) + "," + SPSaveData.csvSafe(callerClass) + "," + SPSaveData.csvSafe(callerMethod) + "," + callerLine + "," + plus + "," + requestedAmount + "," + processedAmount + "," + appliedDelta + "," + before + "," + after + "," + phaseBefore + "," + phaseAfter + "," + canChangePhase + "," + isFromSleep + "," + SPSaveData.csvSafe(result));
            writer.newLine();
        }
        catch (IOException e) {
            SP_LOG.warn("[SRP EP DEBUG] Failed to write point CSV for dim {} src {}.", (Object)dimID, (Object)srcName, (Object)e);
        }
    }

    private static void logPointChange(World worldIn, int dimID, int srcID, boolean plus, int requestedAmount, int processedAmount, int before, int after, int phaseBefore, int phaseAfter, boolean canChangePhase, boolean isFromSleep, String result) {
        String srcName = SPSaveData.getPointSourceName(srcID);
        int appliedDelta = after - before;
        String callerClass = "";
        String callerMethod = "";
        String callerLine = "";
        if (SPSaveData.debugPointCallerTrace()) {
            String[] caller = SPSaveData.getPointCallerInfo();
            callerClass = caller[0];
            callerMethod = caller[1];
            callerLine = caller[2];
        }
        if (SPSaveData.debugPointConsole()) {
            long worldTime = worldIn != null ? worldIn.func_82737_E() : -1L;
            long day = worldTime >= 0L ? worldTime / 24000L : -1L;
            SP_LOG.debug("[SRP EP DEBUG] result={} dim={} day={} time={} src={}({}) caller={}.{}:{} plus={} requested={} processed={} appliedDelta={} before={} after={} phase={}=>{} canChangePhase={} sleepBypass={}", new Object[]{result, dimID, day, worldTime, srcID, srcName, callerClass, callerMethod, callerLine, plus, requestedAmount, processedAmount, appliedDelta, before, after, phaseBefore, phaseAfter, canChangePhase, isFromSleep});
        }
        SPSaveData.writePointCsv(worldIn, dimID, srcID, srcName, plus, requestedAmount, processedAmount, appliedDelta, before, after, phaseBefore, phaseAfter, canChangePhase, isFromSleep, result);
    }

    private static void logPointRejected(World worldIn, int dimID, int srcID, boolean plus, int requestedAmount, int processedAmount, int before, int phaseBefore, boolean canChangePhase, boolean isFromSleep, String reason) {
        SPSaveData.logPointChange(worldIn, dimID, srcID, plus, requestedAmount, processedAmount, before, before, phaseBefore, phaseBefore, canChangePhase, isFromSleep, "REJECTED_" + reason);
    }

    public SPSaveData() {
        super(DATA_NAME);
    }

    public SPSaveData(String name) {
        super(name);
    }

    public static SPSaveData get(World world, int id) {
        if (world == null) {
            return null;
        }
        if (world.field_72995_K) {
            if (clientInstance == null) {
                SPSaveData tmpInstance = instance;
                instance = new SPSaveData();
                clientInstance = SPSaveData.createData(world, null, id);
                instance = tmpInstance;
            }
            return clientInstance;
        }
        MapStorage storage = world.func_175693_T();
        instance = (SPSaveData)storage.func_75742_a(SPSaveData.class, DATA_NAME);
        if (instance == null) {
            instance = new SPSaveData();
            storage.func_75745_a(DATA_NAME, (WorldSavedData)instance);
            instance = SPSaveData.createData(world, storage, id);
        }
        return instance;
    }

    private static SPSaveData createData(World world, MapStorage storage, int iddd) {
        int dim;
        int i;
        int currentDim = world.field_73011_w.getDimension();
        System.out.println("creating save data------ " + iddd + " world is null? " + (world == null));
        String[] here = new String[3];
        int id = 0;
        for (int i2 = 0; i2 < SPConfigSystems.evolutionParasiteLock.length; ++i2) {
            if (SPConfigSystems.evolutionParasiteLock[i2] == null) continue;
            here = SPConfigSystems.evolutionParasiteLock[i2].split(";");
            id = Integer.parseInt(here[2]);
            SPSaveData.instance.lockedParasites.add(id);
        }
        here = new String[3];
        for (i = 0; i < SPConfigSystems.evolutionDimStart.length; ++i) {
            here = SPConfigSystems.evolutionDimStart[i].split(";");
            dim = Integer.parseInt(here[0]);
            int phase = Integer.parseInt(here[1]);
            int points = Integer.parseInt(here[2]);
            SPSaveData.addDim(dim);
            instance.setEvolutionPhase(dim, (byte)phase, true, world);
            if (phase == -1) {
                points = Integer.parseInt(here[2]);
                instance.setTotalKills(dim, -points, false, world, true, 3);
                continue;
            }
            if (phase == -2) {
                instance.setGaining(false, dim);
                instance.setLoss(false, dim);
                continue;
            }
            instance.setTotalKills(dim, points, false, world, true, 2);
        }
        for (i = 0; i < SPConfigSystems.generationDimStart.length; ++i) {
            here = SPConfigSystems.generationDimStart[i].split(";");
            dim = Integer.parseInt(here[0]);
            int gen = Integer.parseInt(here[1]);
            SPSaveData.addDim(dim);
            instance.setGeneration((byte)gen, dim);
        }
        return instance;
    }

    private static void addDim(int id) {
        for (int i = 0; i < SPSaveData.instance.dimEPid.size(); ++i) {
            if (SPSaveData.instance.dimEPid.get(i) != id) continue;
            return;
        }
        SPSaveData.instance.dimEPid.add(id);
        SPSaveData.instance.dimEPcanGainPoints.add(true);
        SPSaveData.instance.dimEPcanLossPoints.add(true);
        SPSaveData.instance.dimEPevolution.add(SPConfigSystems.defaultEvoPhase);
        SPSaveData.instance.dimEPtimeEvolution.add(0);
        SPSaveData.instance.dimEPtotalKills.add(SPConfigSystems.defaultEvoPoints);
        SPSaveData.instance.dimEPcurrentCodes.add(DISLO_R);
        SPSaveData.instance.dimEPcurrentCodesDur.add(DISLO_R);
        SPSaveData.instance.dimGeneration.add(SPConfigSystems.generationDefa);
        SPSaveData.instance.dimGenerationTime.add(0);
        SPSaveData.instance.dimUpdates.add(0);
        SPSaveData.instance.dimEIVHealth.add(0);
        SPSaveData.instance.dimEIVArea.add(0);
        SPSaveData.instance.choice = SPWorldEntitySpawner.choiceNUMBER;
        instance.func_76185_a();
    }

    public void func_76184_a(NBTTagCompound compound) {
        NBTTagList tagListX;
        if (compound.func_74764_b("srpchoice")) {
            this.choice = compound.func_74762_e("srpchoice");
        }
        if (compound.func_74764_b("srpparasitepoints")) {
            NBTTagList kills = compound.func_150295_c("srpparasitepoints", 10);
            NBTTagList time = compound.func_150295_c("srpparasitetimeevoworld", 10);
            NBTTagList evolution = compound.func_150295_c("srpparasiteevolution", 10);
            NBTTagList gaining = compound.func_150295_c("srpevolutiongaining", 10);
            NBTTagList loss = compound.func_150295_c("srpevolutionloss", 10);
            NBTTagList dimid = compound.func_150295_c("srpevolutiondimid", 10);
            NBTTagList currentC = compound.func_150295_c("srpevolutioncodes", 10);
            NBTTagList currentCDur = compound.func_150295_c("srpevolutioncodesdur", 10);
            NBTTagList gene = compound.func_150295_c("srpgeneration", 10);
            NBTTagList genetime = compound.func_150295_c("srpgenerationtime", 10);
            NBTTagList updates = compound.func_150295_c("srpworldupdates", 10);
            NBTTagList eivH = compound.func_150295_c("srpeivhealth", 10);
            NBTTagList eivA = compound.func_150295_c("srpeivarea", 10);
            if (dimid.func_74745_c() != evolution.func_74745_c() || dimid.func_74745_c() != kills.func_74745_c() || dimid.func_74745_c() != gaining.func_74745_c() || dimid.func_74745_c() != loss.func_74745_c() || dimid.func_74745_c() != time.func_74745_c() || dimid.func_74745_c() != currentC.func_74745_c() || dimid.func_74745_c() != currentCDur.func_74745_c() || dimid.func_74745_c() != gene.func_74745_c() || dimid.func_74745_c() != genetime.func_74745_c() || dimid.func_74745_c() != updates.func_74745_c() || dimid.func_74745_c() != eivH.func_74745_c() || dimid.func_74745_c() != eivA.func_74745_c()) {
                SPMain.logger.log(Level.ERROR, "Problem while reading Evolution Phases from the World NBT");
            } else {
                for (int i = 0; i < dimid.func_74745_c(); ++i) {
                    NBTTagCompound tagA = kills.func_150305_b(i);
                    int k = tagA.func_74762_e("kills" + i);
                    this.dimEPtotalKills.add(i, k);
                    NBTTagCompound tagB = time.func_150305_b(i);
                    int t = tagB.func_74762_e("time" + i);
                    this.dimEPtimeEvolution.add(i, t);
                    NBTTagCompound tagC = evolution.func_150305_b(i);
                    int e = tagC.func_74762_e("ev" + i);
                    this.dimEPevolution.add(i, (byte)e);
                    NBTTagCompound tagD = gaining.func_150305_b(i);
                    boolean g = tagD.func_74767_n("gain" + i);
                    this.dimEPcanGainPoints.add(i, g);
                    NBTTagCompound tagE = loss.func_150305_b(i);
                    boolean l = tagE.func_74767_n("canLose" + i);
                    this.dimEPcanLossPoints.add(i, l);
                    NBTTagCompound tagF = dimid.func_150305_b(i);
                    int d = tagF.func_74762_e("dimid" + i);
                    this.dimEPid.add(i, d);
                    NBTTagCompound tagG = currentC.func_150305_b(i);
                    String h = tagG.func_74779_i("code" + i);
                    this.dimEPcurrentCodes.add(i, h);
                    NBTTagCompound tagH = currentCDur.func_150305_b(i);
                    String j = tagH.func_74779_i("codedur" + i);
                    this.dimEPcurrentCodesDur.add(i, j);
                    NBTTagCompound tagX = gene.func_150305_b(i);
                    byte ix = tagX.func_74771_c("generation" + i);
                    this.dimGeneration.add(i, ix);
                    NBTTagCompound tagY = genetime.func_150305_b(i);
                    int iy = tagY.func_74762_e("generationtime" + i);
                    this.dimGenerationTime.add(i, iy);
                    NBTTagCompound tagZ = updates.func_150305_b(i);
                    int iz = tagZ.func_74762_e("update" + i);
                    this.dimUpdates.add(i, iz);
                    NBTTagCompound tagEIVH = eivH.func_150305_b(i);
                    int eivh = tagEIVH.func_74762_e("eivh" + i);
                    this.dimEIVHealth.add(i, eivh);
                    NBTTagCompound tagEIVA = eivA.func_150305_b(i);
                    int eiva = tagEIVA.func_74762_e("eiva" + i);
                    this.dimEIVArea.add(i, eiva);
                }
            }
        }
        if (compound.func_74764_b("srplockedparasites")) {
            tagListX = compound.func_150295_c("srplockedparasites", 10);
            for (int i = 0; i < tagListX.func_74745_c(); ++i) {
                NBTTagCompound tagX = tagListX.func_150305_b(i);
                int id = tagX.func_74762_e("parasiteid" + i);
                this.lockedParasites.add(i, id);
            }
        }
        if (compound.func_74764_b("srpassimilatedtotal")) {
            tagListX = compound.func_150295_c("srpassimilatedtotal", 10);
            NBTTagList tagListY = compound.func_150295_c("srpassimilatedtotaltimes", 10);
            for (int i = 0; i < tagListX.func_74745_c(); ++i) {
                NBTTagCompound tagX = tagListX.func_150305_b(i);
                int ix = tagX.func_74762_e("srpassimilatedtotalid" + i);
                this.simRegId.add(i, (byte)ix);
                NBTTagCompound tagY = tagListY.func_150305_b(i);
                int iy = tagY.func_74762_e("srpassimilatedtotalidtimes" + i);
                this.simRegIdTimes.add(i, iy);
            }
        }
    }

    public NBTTagCompound func_189551_b(NBTTagCompound compound) {
        compound.func_74768_a("srpchoice", this.choice);
        if (this.dimEPid.size() != this.dimEPevolution.size() || this.dimEPid.size() != this.dimEPtotalKills.size() || this.dimEPid.size() != this.dimEPcanGainPoints.size() || this.dimEPid.size() != this.dimEPcanLossPoints.size() || this.dimEPid.size() != this.dimEPtimeEvolution.size() || this.dimEPid.size() != this.dimEPcurrentCodes.size() || this.dimEPid.size() != this.dimEPcurrentCodesDur.size() || this.dimEPid.size() != this.dimEIVHealth.size() || this.dimEPid.size() != this.dimEIVArea.size()) {
            SPMain.logger.log(Level.ERROR, "Problem while writing Evolution Phases in the World NBT");
        } else {
            NBTTagList kills = new NBTTagList();
            NBTTagList time = new NBTTagList();
            NBTTagList evolution = new NBTTagList();
            NBTTagList gaining = new NBTTagList();
            NBTTagList loss = new NBTTagList();
            NBTTagList dimid = new NBTTagList();
            NBTTagList currentC = new NBTTagList();
            NBTTagList currentCDur = new NBTTagList();
            NBTTagList tagListXX = new NBTTagList();
            NBTTagList tagListXY = new NBTTagList();
            NBTTagList tagListXZ = new NBTTagList();
            NBTTagList tagListEIVH = new NBTTagList();
            NBTTagList tagListEIVA = new NBTTagList();
            for (int i = 0; i < this.dimEPid.size(); ++i) {
                NBTTagCompound tagA = new NBTTagCompound();
                tagA.func_74768_a("kills" + i, this.dimEPtotalKills.get(i).intValue());
                kills.func_74742_a((NBTBase)tagA);
                NBTTagCompound tagB = new NBTTagCompound();
                tagB.func_74768_a("time" + i, this.dimEPtimeEvolution.get(i).intValue());
                time.func_74742_a((NBTBase)tagB);
                NBTTagCompound tagC = new NBTTagCompound();
                tagC.func_74768_a("ev" + i, (int)this.dimEPevolution.get(i).byteValue());
                evolution.func_74742_a((NBTBase)tagC);
                NBTTagCompound tagD = new NBTTagCompound();
                tagD.func_74757_a("gain" + i, this.dimEPcanGainPoints.get(i).booleanValue());
                gaining.func_74742_a((NBTBase)tagD);
                NBTTagCompound tagE = new NBTTagCompound();
                tagE.func_74757_a("canLose" + i, this.dimEPcanLossPoints.get(i).booleanValue());
                loss.func_74742_a((NBTBase)tagE);
                NBTTagCompound tagF = new NBTTagCompound();
                tagF.func_74768_a("dimid" + i, this.dimEPid.get(i).intValue());
                dimid.func_74742_a((NBTBase)tagF);
                NBTTagCompound tagG = new NBTTagCompound();
                tagG.func_74778_a("code" + i, this.dimEPcurrentCodes.get(i));
                currentC.func_74742_a((NBTBase)tagG);
                NBTTagCompound tagH = new NBTTagCompound();
                tagH.func_74778_a("codedur" + i, this.dimEPcurrentCodesDur.get(i));
                currentCDur.func_74742_a((NBTBase)tagH);
                NBTTagCompound tagX = new NBTTagCompound();
                tagX.func_74774_a("generation" + i, this.dimGeneration.get(i).byteValue());
                tagListXX.func_74742_a((NBTBase)tagX);
                NBTTagCompound tagY = new NBTTagCompound();
                tagY.func_74768_a("generationtime" + i, this.dimGenerationTime.get(i).intValue());
                tagListXY.func_74742_a((NBTBase)tagY);
                NBTTagCompound tagZ = new NBTTagCompound();
                tagZ.func_74768_a("update" + i, this.dimUpdates.get(i).intValue());
                tagListXZ.func_74742_a((NBTBase)tagZ);
                NBTTagCompound tagEIVH = new NBTTagCompound();
                tagEIVH.func_74768_a("eivh" + i, this.dimEIVHealth.get(i).intValue());
                tagListEIVH.func_74742_a((NBTBase)tagEIVH);
                NBTTagCompound tagEIVA = new NBTTagCompound();
                tagEIVA.func_74768_a("eiva" + i, this.dimEIVArea.get(i).intValue());
                tagListEIVA.func_74742_a((NBTBase)tagEIVA);
            }
            compound.func_74782_a("srpparasitepoints", (NBTBase)kills);
            compound.func_74782_a("srpparasitetimeevoworld", (NBTBase)time);
            compound.func_74782_a("srpparasiteevolution", (NBTBase)evolution);
            compound.func_74782_a("srpevolutiongaining", (NBTBase)gaining);
            compound.func_74782_a("srpevolutionloss", (NBTBase)loss);
            compound.func_74782_a("srpevolutiondimid", (NBTBase)dimid);
            compound.func_74782_a("srpevolutioncodes", (NBTBase)currentC);
            compound.func_74782_a("srpevolutioncodesdur", (NBTBase)currentCDur);
            compound.func_74782_a("srpgeneration", (NBTBase)tagListXX);
            compound.func_74782_a("srpgenerationtime", (NBTBase)tagListXY);
            compound.func_74782_a("srpworldupdates", (NBTBase)tagListXZ);
            compound.func_74782_a("srpeivhealth", (NBTBase)tagListEIVH);
            compound.func_74782_a("srpeivarea", (NBTBase)tagListEIVA);
        }
        NBTTagList tagListXX = new NBTTagList();
        for (int i = 0; i < this.lockedParasites.size(); ++i) {
            int id = this.lockedParasites.get(i);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74768_a("parasiteid" + i, id);
            tagListXX.func_74742_a((NBTBase)tagX);
        }
        compound.func_74782_a("srplockedparasites", (NBTBase)tagListXX);
        tagListXX = new NBTTagList();
        NBTTagList tagListYY = new NBTTagList();
        for (int i = 0; i < this.simRegId.size(); ++i) {
            byte ix = this.simRegId.get(i);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74768_a("srpassimilatedtotalid" + i, (int)ix);
            tagListXX.func_74742_a((NBTBase)tagX);
            int iy = this.simRegIdTimes.get(i);
            NBTTagCompound tagY = new NBTTagCompound();
            tagY.func_74768_a("srpassimilatedtotalidtimes" + i, iy);
            tagListYY.func_74742_a((NBTBase)tagY);
        }
        compound.func_74782_a("srpassimilatedtotal", (NBTBase)tagListXX);
        compound.func_74782_a("srpassimilatedtotaltimes", (NBTBase)tagListYY);
        return compound;
    }

    public int getChoice() {
        return this.choice;
    }

    public boolean checkParasiteID(int in) {
        for (int i = 0; i < this.lockedParasites.size(); ++i) {
            if (this.lockedParasites.get(i) != in) continue;
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getLockedList() {
        return this.lockedParasites;
    }

    public boolean unlockParasite(int in) {
        for (int i = 0; i < this.lockedParasites.size(); ++i) {
            if (this.lockedParasites.get(i) != in) continue;
            this.lockedParasites.remove(i);
            this.func_76185_a();
            return true;
        }
        return false;
    }

    public boolean unlockAllParasite() {
        this.lockedParasites = new ArrayList();
        this.func_76185_a();
        return false;
    }

    public void resetLock() {
        ArrayList<Integer> aaa = new ArrayList<Integer>();
        String[] here = new String[3];
        int id = 0;
        for (int i = 0; i < SPConfigSystems.evolutionParasiteLock.length; ++i) {
            if (SPConfigSystems.evolutionParasiteLock[i] == null) continue;
            here = SPConfigSystems.evolutionParasiteLock[i].split(";");
            id = Integer.parseInt(here[2]);
            aaa.add(id);
        }
        this.lockedParasites = aaa;
        this.func_76185_a();
    }

    public int getEIVHealth(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEIVHealth.get(i);
        }
        return 0;
    }

    public int getEIVArea(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEIVArea.get(i);
        }
        return 0;
    }

    public void setEIVHealthArea(int id, int health, int area) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            this.dimEIVHealth.set(i, health);
            this.dimEIVArea.set(i, area);
            this.func_76185_a();
        }
    }

    public void addUpdateNumber(int in) {
        if (SPConfigWorld.originActivated) {
            for (int i = 0; i < this.dimEPid.size(); ++i) {
                int current = this.dimUpdates.get(i) + in;
                if (current <= 0) {
                    current = 0;
                }
                this.dimUpdates.set(i, current);
                int health = this.dimEIVHealth.get(i);
                int size = this.dimEIVArea.get(i);
                size = (int)((double)size + (double)size * (SPConfigWorld.originDailySize + SPWorldData.getPhaseSize(this.dimEPevolution.get(i).byteValue())));
                health = (int)((double)health + (double)size * (SPConfigWorld.originDailyHealth + SPWorldData.getPhaseHealth(this.dimEPevolution.get(i).byteValue())));
                this.dimEIVArea.set(i, Math.min(size, SPConfigWorld.originRadiusCap));
                this.dimEIVHealth.set(i, Math.min(health, SPConfigWorld.originHealthCap));
                if (!SPSaveData.debugPointCsv()) {
                    SP_LOG.debug("[SRP EP DEBUG] VECTOR_DAY dim={} phase={} eivHealth={} eivArea={}", (Object)this.dimEPid.get(i), (Object)this.dimEPevolution.get(i), (Object)this.dimEIVHealth.get(i), (Object)this.dimEIVArea.get(i));
                }
                int points = this.dimEPevolution.get(i) == -1 ? (int)((double)this.dimEIVHealth.get(i).intValue() * SPConfigWorld.originDailyEPPointsOutBreak) : (int)((double)this.dimEIVHealth.get(i).intValue() * SPConfigWorld.originDailyEPPoints);
                points = Math.min(points, SPCommandEvolution.getVectorPointCap(this.dimEPevolution.get(i).byteValue()));
                this.setTotalKills(this.dimEPid.get(i), points, true, (World)DimensionManager.getWorld((int)this.dimEPid.get(i)), true, true, 1);
            }
        }
        this.func_76185_a();
    }

    public int getUpdateNumber(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            int u = this.dimUpdates.get(i);
            this.dimUpdates.set(i, 0);
            this.func_76185_a();
            return u;
        }
        SPSaveData.addDim(id);
        return this.getGeneration(id);
    }

    public void setGaining(boolean in, int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            this.dimEPcanGainPoints.set(i, in);
            this.func_76185_a();
        }
    }

    public boolean getCanGain(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEPcanGainPoints.get(i);
        }
        SPSaveData.addDim(id);
        return this.getCanGain(id);
    }

    public void setLoss(boolean in, int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            this.dimEPcanLossPoints.set(i, in);
            this.func_76185_a();
        }
    }

    public boolean getCanLoss(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEPcanLossPoints.get(i);
        }
        SPSaveData.addDim(id);
        return this.getCanLoss(id);
    }

    public byte getEvolutionPhase(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEPevolution.get(i);
        }
        SPSaveData.addDim(id);
        return this.getEvolutionPhase(id);
    }

    public void setCooldown(int in, World worldIn, int id, boolean adding) {
        int currentWT = (int)worldIn.func_82737_E();
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            int timer = this.dimEPtimeEvolution.get(i);
            timer = currentWT - this.getDelay(this.getEvolutionPhase(id)) * 20 + in * 20;
            if (adding) {
                this.dimEPtimeEvolution.set(i, timer + this.getCooldown(worldIn, id) * 20);
            } else {
                this.dimEPtimeEvolution.set(i, timer);
            }
            this.func_76185_a();
        }
    }

    public int getCooldown(World worldIn, int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            if (this.dimEPtimeEvolution.get(i) == 0) {
                return 0;
            }
            int currentWT = (int)worldIn.func_82737_E();
            return Math.max((this.getDelay(this.getEvolutionPhase(id)) * 20 - (currentWT - this.dimEPtimeEvolution.get(i))) / 20, 0);
        }
        return 0;
    }

    public int getTotalKills(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEPtotalKills.get(i);
        }
        SPSaveData.addDim(id);
        return this.getTotalKills(id);
    }

    public boolean setTotalKills(int dimID, int in, boolean plus, World worldIn, boolean canChangePhase, int srcID) {
        return this.setTotalKills(dimID, in, plus, worldIn, canChangePhase, false, srcID);
    }

    public boolean setTotalKills(int dimID, int in, boolean plus, World worldIn, boolean canChangePhase, boolean isFromSleep, int srcID) {
        if (!SPConfigSystems.useEvolution) {
            return false;
        }
        int requestedAmount = in;
        boolean requestedPlus = plus;
        boolean requestedCanChangePhase = canChangePhase;
        boolean change = true;
        if (in > 0) {
            switch (this.choice) {
                case 0: {
                    in = (int)((double)in * 0.5);
                    break;
                }
                case 2: {
                    in *= 3;
                    isFromSleep = true;
                    break;
                }
                case 3: {
                    in *= 10;
                    isFromSleep = true;
                }
            }
        }
        if (worldIn != null) {
            if (SPConfigSystems.evolutionNoPlayerMultipler && worldIn.field_73010_i.isEmpty()) {
                return false;
            }
            int difficultyCap = worldIn.func_175659_aa().func_151525_a() / 2 * SPConfigSystems.evolutionPointCap;
            if (!isFromSleep && plus) {
                in = Math.min(in, difficultyCap);
            }
        }
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != dimID) continue;
            if (!this.dimEPcanGainPoints.get(i).booleanValue() && plus && in > 0) {
                SPSaveData.logPointRejected(worldIn, dimID, srcID, plus, requestedAmount, in, this.dimEPtotalKills.get(i), this.dimEPevolution.get(i).byteValue(), canChangePhase, isFromSleep, "GAIN_DISABLED");
                return false;
            }
            if (!this.dimEPcanLossPoints.get(i).booleanValue() && plus && in < 0) {
                SPSaveData.logPointRejected(worldIn, dimID, srcID, plus, requestedAmount, in, this.dimEPtotalKills.get(i), this.dimEPevolution.get(i).byteValue(), canChangePhase, isFromSleep, "LOSS_DISABLED");
                return false;
            }
            if (this.dimEPevolution.get(i) == -2) {
                SPSaveData.logPointRejected(worldIn, dimID, srcID, plus, requestedAmount, in, this.dimEPtotalKills.get(i), this.dimEPevolution.get(i).byteValue(), canChangePhase, isFromSleep, "PHASE_NEGATIVE_TWO");
                return false;
            }
            if (this.getDelay(this.dimEPevolution.get(i)) != 0 && worldIn != null && this.choice != 3 && this.getCooldown(worldIn, dimID) != 0 && plus) {
                SPSaveData.logPointRejected(worldIn, dimID, srcID, plus, requestedAmount, in, this.dimEPtotalKills.get(i), this.dimEPevolution.get(i).byteValue(), canChangePhase, isFromSleep, "COOLDOWN");
                return false;
            }
            change = false;
            if (worldIn != null) {
                this.checkGeneration(dimID, worldIn);
            }
            int beforePoints = this.dimEPtotalKills.get(i);
            byte phaseBefore = this.dimEPevolution.get(i);
            if (plus) {
                int lower;
                boolean top;
                int kills = this.dimEPtotalKills.get(i);
                byte phase = this.dimEPevolution.get(i);
                boolean bl = top = kills >= 0 && in > 0;
                if (in < 0 && phase < 0) {
                    return false;
                }
                kills += in;
                if (SPConfigSystems.debugEvolutionPointsConsole) {
                    SP_LOG.debug("[SRP EP DEBUG] calculated points dim={} src={} before={} change={} after={} phase={} canChangePhase={} sleepBypass={}", (Object)dimID, (Object)srcID, (Object)beforePoints, (Object)in, (Object)kills, (Object)phase, (Object)canChangePhase, (Object)isFromSleep);
                }
                if (top && kills < 0) {
                    kills = this.dimEPtotalKills.get(i);
                }
                if (!canChangePhase && phase >= 0 && kills < (lower = SPCommandEvolution.getNeededPoints(this.getEvolutionPhase(dimID)))) {
                    kills = lower;
                }
                if (kills < 0 && phase >= 0) {
                    kills = 0;
                }
                if (kills > SPConfigSystems.phaseTenTotalPoints) {
                    kills = SPConfigSystems.phaseTenTotalPoints;
                }
                this.checkKills(dimID, kills, worldIn, this.dimEPevolution.get(i));
                this.dimEPtotalKills.set(i, kills);
                byte phaseAfter = this.dimEPevolution.get(i);
                SPSaveData.logPointChange(worldIn, dimID, srcID, requestedPlus, requestedAmount, in, beforePoints, kills, phaseBefore, phaseAfter, requestedCanChangePhase, isFromSleep, "APPLIED");
                continue;
            }
            this.dimEPtotalKills.set(i, in);
            byte phaseAfter = this.dimEPevolution.get(i);
            SPSaveData.logPointChange(worldIn, dimID, srcID, requestedPlus, requestedAmount, in, beforePoints, in, phaseBefore, phaseAfter, requestedCanChangePhase, isFromSleep, "SET_DIRECT");
        }
        if (change) {
            SPSaveData.addDim(dimID);
        }
        this.func_76185_a();
        return true;
    }

    private int getDelay(byte in) {
        switch (in) {
            case 1: {
                return SPConfigSystems.phaseDelayTicksOne;
            }
            case 2: {
                return SPConfigSystems.phaseDelayTicksTwo;
            }
            case 3: {
                return SPConfigSystems.phaseDelayTicksThree;
            }
            case 4: {
                return SPConfigSystems.phaseDelayTicksFour;
            }
            case 5: {
                return SPConfigSystems.phaseDelayTicksFive;
            }
            case 6: {
                return SPConfigSystems.phaseDelayTicksSix;
            }
            case 7: {
                return SPConfigSystems.phaseDelayTicksSeven;
            }
            case 8: {
                return SPConfigSystems.phaseDelayTicksEight;
            }
            case 9: {
                return SPConfigSystems.phaseDelayTicksNine;
            }
            case 10: {
                return SPConfigSystems.phaseDelayTicksTen;
            }
        }
        return 0;
    }

    private boolean checkKills(int id, int in, World worldIn, byte evoPhase) {
        boolean flag = false;
        boolean changed = false;
        switch (evoPhase) {
            case -1: {
                if (in <= 0 || !this.setEvolutionPhase(id, (byte)0, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningZero, 0);
                changed = true;
                break;
            }
            case 0: {
                if (in <= SPConfigSystems.phaseKillsOne || !this.setEvolutionPhase(id, (byte)1, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningOne, 1);
                changed = true;
                break;
            }
            case 1: {
                if (in > SPConfigSystems.phaseKillsTwo) {
                    if (!this.setEvolutionPhase(id, (byte)2, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningTwo, 2);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsOne || !this.setEvolutionPhase(id, (byte)0, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 2: {
                if (in > SPConfigSystems.phaseKillsThree) {
                    if (!this.setEvolutionPhase(id, (byte)3, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningThree, 3);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsTwo || !this.setEvolutionPhase(id, (byte)1, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 3: {
                if (in > SPConfigSystems.phaseKillsFour) {
                    if (!this.setEvolutionPhase(id, (byte)4, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningFour, 4);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsThree || !this.setEvolutionPhase(id, (byte)2, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 4: {
                if (in > SPConfigSystems.phaseKillsFive) {
                    if (!this.setEvolutionPhase(id, (byte)5, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningFive, 5);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsFour || !this.setEvolutionPhase(id, (byte)3, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 5: {
                if (in > SPConfigSystems.phaseKillsSix) {
                    if (!this.setEvolutionPhase(id, (byte)6, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningSix, 6);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsFive || !this.setEvolutionPhase(id, (byte)4, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 6: {
                if (in > SPConfigSystems.phaseKillsSeven) {
                    if (!this.setEvolutionPhase(id, (byte)7, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningSeven, 7);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsSix || !this.setEvolutionPhase(id, (byte)5, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 7: {
                if (in > SPConfigSystems.phaseKillsEight) {
                    if (!this.setEvolutionPhase(id, (byte)8, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningEight, 8);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsSeven || !this.setEvolutionPhase(id, (byte)6, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 8: {
                if (in > SPConfigSystems.phaseKillsNine) {
                    if (!this.setEvolutionPhase(id, (byte)9, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningNine, 9);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsEight || !this.setEvolutionPhase(id, (byte)7, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 9: {
                if (in > SPConfigSystems.phaseKillsTen) {
                    if (!this.setEvolutionPhase(id, (byte)10, false, worldIn)) break;
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SPConfigSystems.phaseWarningTen, 10);
                    changed = true;
                    break;
                }
                if (in >= SPConfigSystems.phaseKillsNine || !this.setEvolutionPhase(id, (byte)8, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
                break;
            }
            case 10: {
                if (in >= SPConfigSystems.phaseKillsTen || !this.setEvolutionPhase(id, (byte)9, false, worldIn)) break;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
        }
        if (in > 0 && changed && evoPhase > -3 && evoPhase < 11) {
            return this.checkKills(id, in, worldIn, (byte)(evoPhase + 1));
        }
        flag = true;
        return flag;
    }

    public boolean setEvolutionPhase(int id, byte in, boolean override, World worldIn) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            SPMain.network.sendToDimension((IMessage)new SPPacketMusicTrackCancelUpdateClientEvoPhase(in), id);
            if (override) {
                this.dimEPevolution.set(i, in);
                this.checkPhase(id, in, worldIn);
            } else {
                this.dimEPevolution.set(i, in);
            }
            if (worldIn != null) {
                this.dimEPtimeEvolution.set(i, (int)worldIn.func_82737_E());
            }
            this.checkForUnlock(this.getEvolutionPhase(id), id, worldIn);
        }
        this.func_76185_a();
        return true;
    }

    private void checkForUnlock(byte phase, int worldId, World w) {
        String[] here = new String[3];
        int id = 0;
        int dim = 0;
        byte p = 0;
        for (int i = 0; i < SPConfigSystems.evolutionParasiteLock.length; ++i) {
            if (SPConfigSystems.evolutionParasiteLock[i] == null || worldId != (dim = Integer.parseInt((here = SPConfigSystems.evolutionParasiteLock[i].split(";"))[0])) || (p = Byte.parseByte(here[1])) != phase || !this.unlockParasite(id = Integer.parseInt(here[2]))) continue;
            ParasiteEventEntity.alertAllPlayerSer(w, SPConfigSystems.evolutionParasiteLockMessage);
        }
    }

    private void checkPhase(int id, byte in, World worldIn) {
        switch (in) {
            case -1: {
                this.setTotalKills(id, -10, false, worldIn, true, 4);
                break;
            }
            case 0: {
                this.setTotalKills(id, 0, false, worldIn, true, 5);
                break;
            }
            case 1: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsOne, false, worldIn, true, 6);
                break;
            }
            case 2: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsTwo, false, worldIn, true, 7);
                break;
            }
            case 3: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsThree, false, worldIn, true, 8);
                break;
            }
            case 4: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsFour, false, worldIn, true, 9);
                break;
            }
            case 5: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsFive, false, worldIn, true, 10);
                break;
            }
            case 6: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsSix, false, worldIn, true, 11);
                break;
            }
            case 7: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsSeven, false, worldIn, true, 12);
                break;
            }
            case 8: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsEight, false, worldIn, true, 13);
                break;
            }
            case 9: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsNine, false, worldIn, true, 14);
                break;
            }
            case 10: {
                this.setTotalKills(id, SPConfigSystems.phaseKillsTen, false, worldIn, true, 15);
            }
        }
    }

    public int getNumberIDDataSpawn(int id) {
        for (int i = 0; i < this.simRegId.size(); ++i) {
            if (this.simRegId.get(i) != id) continue;
            return this.simRegIdTimes.get(i);
        }
        return 0;
    }

    public void addNumberIDDataSpawn(int id) {
        boolean flag = true;
        for (int i = 0; i < this.simRegId.size(); ++i) {
            if (this.simRegId.get(i) != id) continue;
            int tt = this.simRegIdTimes.get(i);
            this.simRegIdTimes.set(i, tt + 1);
            flag = false;
        }
        if (flag) {
            this.simRegId.add((byte)id);
            this.simRegIdTimes.add(1);
        }
    }

    public String getCurrentCodeU(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEPcurrentCodes.get(i);
        }
        SPSaveData.addDim(id);
        return DISLO_R;
    }

    public String getCurrentCodeUD(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimEPcurrentCodesDur.get(i);
        }
        SPSaveData.addDim(id);
        return DISLO_R;
    }

    public int getCurrentCode(int id, int position) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            String[] here = new String[30];
            here = this.dimEPcurrentCodes.get(i).split(";");
            return Integer.parseInt(here[position]);
        }
        SPSaveData.addDim(id);
        return 0;
    }

    public int[] getDisloValues(int id) {
        int[] values = new int[30];
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            String[] here = new String[30];
            here = this.dimEPcurrentCodes.get(i).split(";");
            for (int k = 0; k < here.length; ++k) {
                values[k] = Integer.parseInt(here[k]);
            }
        }
        return values;
    }

    public int getCurrentCodeDuration(int id, int position) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            String[] here = new String[30];
            here = this.dimEPcurrentCodesDur.get(i).split(";");
            return Integer.parseInt(here[position]);
        }
        SPSaveData.addDim(id);
        return 0;
    }

    public boolean setCurrentCode(int id, int position, int code, int time, World worldIn, boolean start, int pointCost) {
        if (position > 29 || position < 0) {
            return false;
        }
        if (code < 0 || time < 0 || pointCost < 0) {
            return false;
        }
        if (code == 0) {
            time = 0;
        }
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            if (this.getCurrentCodeDuration(id, position) != 0) {
                return false;
            }
            if (pointCost > 0 && SPConfigSystems.useEvolution) {
                int beforePoints = this.dimEPtotalKills.get(i);
                int point = beforePoints - pointCost;
                if (point < SPCommandEvolution.getNeededPoints(this.dimEPevolution.get(i))) {
                    return false;
                }
                byte phaseBefore = this.dimEPevolution.get(i);
                this.dimEPtotalKills.set(i, point);
                byte phaseAfter = this.dimEPevolution.get(i);
                SPSaveData.logPointChange(worldIn, id, 70 + position, true, -pointCost, -pointCost, beforePoints, point, phaseBefore, phaseAfter, false, false, "DISLODGEMENT_COST");
            }
            String[] here = new String[30];
            here = this.dimEPcurrentCodes.get(i).split(";");
            here[position] = String.valueOf(code);
            this.dimEPcurrentCodes.set(i, this.fixArray(here));
            here = this.dimEPcurrentCodesDur.get(i).split(";");
            here[position] = String.valueOf(time);
            this.dimEPcurrentCodesDur.set(i, this.fixArray(here));
            if (worldIn == null) continue;
            ParasiteEventEntity.alertAllPlayerDim(worldIn, this.getMessageStartEnd(position, start), 200 + position);
            if (!start) continue;
            this.startDislo(i, position, code, time, worldIn);
        }
        this.func_76185_a();
        return true;
    }

    public void reduceCodesCooldown(int id, int amount, World worldIn) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            String[] here = new String[30];
            here = this.dimEPcurrentCodesDur.get(i).split(";");
            for (int k = 0; k < here.length; ++k) {
                boolean positivee;
                int cool = Integer.parseInt(here[k]);
                if (cool == 0) continue;
                boolean bl = positivee = cool > 0;
                if (positivee) {
                    this.midDislo(id, k, this.getCurrentCode(id, k), worldIn);
                    cool = Math.max(cool - amount, 0);
                } else {
                    cool = Math.min(cool + amount, 0);
                }
                here[k] = String.valueOf(cool);
                if (cool != 0 || !positivee) continue;
                int cooldown = -SPCommandDislodgment.getDisloCooldown(k);
                here[k] = String.valueOf(cooldown *= (int)SPCommandDislodgment.getDisloPhaseCooldown(this.dimEPevolution.get(i)));
                String[] nohere = new String[30];
                nohere = this.dimEPcurrentCodes.get(i).split(";");
                nohere[k] = String.valueOf(0);
                this.dimEPcurrentCodes.set(i, this.fixArray(nohere));
                if (worldIn == null) continue;
                ParasiteEventEntity.alertAllPlayerDim(worldIn, this.getMessageStartEnd(k, false), 200 + k);
                this.endDislo(k, worldIn);
            }
            this.dimEPcurrentCodesDur.set(i, this.fixArray(here));
        }
        this.func_76185_a();
    }

    private String fixArray(String[] list) {
        String atm = "";
        for (int k = 0; k < list.length; ++k) {
            atm = k == list.length - 1 ? atm + list[k] : atm + list[k] + ";";
        }
        return atm;
    }

    private String getMessageStartEnd(int code, boolean start) {
        switch (code) {
            case 0: {
                if (start) {
                    return SPConfigSystems.disloCOTHIgnoreAmpStartMess;
                }
                return SPConfigSystems.disloCOTHIgnoreAmpEndMess;
            }
            case 1: {
                if (start) {
                    return SPConfigSystems.disloCOTHTiersStartMess;
                }
                return SPConfigSystems.disloCOTHTiersEndMess;
            }
            case 2: {
                if (start) {
                    return SPConfigSystems.disloSummonByDeathStartMess;
                }
                return SPConfigSystems.disloSummonByDeathEndMess;
            }
            case 3: {
                if (start) {
                    return SPConfigSystems.disloPotiEffStartMess;
                }
                return SPConfigSystems.disloPotiEffEndMess;
            }
            case 4: {
                if (start) {
                    return SPConfigSystems.disloStatsStartMess;
                }
                return SPConfigSystems.disloStatsEndMess;
            }
            case 5: {
                if (start) {
                    return SPConfigSystems.disloDeathRaidS;
                }
                return SPConfigSystems.disloDeathRaidE;
            }
            case 6: {
                if (start) {
                    return SPConfigSystems.disloItemDuraS;
                }
                return SPConfigSystems.disloItemDuraE;
            }
            case 7: {
                if (start) {
                    return SPConfigSystems.disloHealingDeathS;
                }
                return SPConfigSystems.disloHealingDeathE;
            }
            case 8: {
                if (start) {
                    return SPConfigSystems.disloDamageDeathS;
                }
                return SPConfigSystems.disloDamageDeathE;
            }
            case 9: {
                if (start) {
                    return SPConfigSystems.disloFoodDeathS;
                }
                return SPConfigSystems.disloFoodDeathE;
            }
            case 10: {
                if (start) {
                    return SPConfigSystems.disloDeathHighVerionsS;
                }
                return SPConfigSystems.disloDeathHighVerionsE;
            }
            case 11: {
                if (start) {
                    return SPConfigSystems.disloParasiteNoPotionS;
                }
                return SPConfigSystems.disloParasiteNoPotionE;
            }
            case 12: {
                if (start) {
                    return SPConfigSystems.disloHealthDrainingS;
                }
                return SPConfigSystems.disloHealthDrainingE;
            }
            case 13: {
                if (start) {
                    return SPConfigSystems.disloFoodDrainingS;
                }
                return SPConfigSystems.disloFoodDrainingE;
            }
            case 14: {
                if (start) {
                    return SPConfigSystems.disloNextPhaseLS;
                }
                return SPConfigSystems.disloNextPhaseLE;
            }
            case 15: {
                if (start) {
                    return SPConfigSystems.disloGrowlNoiseS;
                }
                return SPConfigSystems.disloGrowlNoiseE;
            }
            case 16: {
                if (start) {
                    return SPConfigSystems.disloWalkNoiseS;
                }
                return SPConfigSystems.disloWalkNoiseE;
            }
            case 17: {
                if (start) {
                    return SPConfigSystems.disloShieldFoodS;
                }
                return SPConfigSystems.disloShieldFoodE;
            }
            case 18: {
                if (start) {
                    return SPConfigSystems.disloLootXpCancS;
                }
                return SPConfigSystems.disloLootXpCancE;
            }
            case 19: {
                if (start) {
                    return SPConfigSystems.disloKillcountIncS;
                }
                return SPConfigSystems.disloKillcountIncE;
            }
            case 20: {
                if (start) {
                    return SPConfigSystems.disloGiveBodiesS;
                }
                return SPConfigSystems.disloGiveBodiesE;
            }
            case 21: {
                if (start) {
                    return SPConfigSystems.disloBurningDeathS;
                }
                return SPConfigSystems.disloBurningDeathE;
            }
            case 22: {
                if (start) {
                    return SPConfigSystems.disloSameVersionDyeingS;
                }
                return SPConfigSystems.disloSameVersionDyeingE;
            }
            case 23: {
                if (start) {
                    return SPConfigSystems.disloColonyNoLimitS;
                }
                return SPConfigSystems.disloColonyNoLimitE;
            }
            case 24: {
                if (start) {
                    return SPConfigSystems.disloNexusGrowthS;
                }
                return SPConfigSystems.disloNexusGrowthE;
            }
            case 25: {
                if (start) {
                    return SPConfigSystems.disloParasiteBlockS;
                }
                return SPConfigSystems.disloParasiteBlockE;
            }
        }
        return "missing dislo message";
    }

    private void startDislo(int id, int position, int code, int time, World world) {
        List serverList = world.field_72996_f;
        block19: for (int x = 0; x < serverList.size(); ++x) {
            boolean flag = serverList.get(x) instanceof EntityParasiteBase;
            switch (position) {
                case 2: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberTwo = true;
                    continue block19;
                }
                case 3: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberThree = true;
                    continue block19;
                }
                case 4: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberFour = true;
                    continue block19;
                }
                case 5: {
                    if (!flag) continue block19;
                    ((Entity)serverList.get(x)).func_70097_a(DamageSource.field_76380_i, 10000.0f);
                    continue block19;
                }
                case 6: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSix = true;
                    continue block19;
                }
                case 7: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSeven = true;
                    continue block19;
                }
                case 8: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberEight = true;
                    continue block19;
                }
                case 9: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberNine = true;
                    continue block19;
                }
                case 11: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberEleven = time * 20 + 50;
                    continue block19;
                }
                case 15: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberFifteen = time * 20 + 50;
                    ((EntityParasiteBase)serverList.get(x)).setDislo15(true);
                    continue block19;
                }
                case 16: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSixteen = time * 20 + 50;
                    ((EntityParasiteBase)serverList.get((int)x)).distanceWalkedOnStepModifiedDislo = ((Entity)serverList.get((int)x)).field_82151_R;
                    continue block19;
                }
                case 17: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSeventeen = time * 20 + 50;
                    continue block19;
                }
                case 18: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberEighteen = true;
                    continue block19;
                }
                case 19: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberNineteen = time * 20 + 50;
                    continue block19;
                }
                case 20: {
                    if (!flag) continue block19;
                    if (serverList.get(x) instanceof EntityInhooM) {
                        ((EntityInhooM)serverList.get((int)x)).disloNumberTwenty = true;
                    }
                    if (!(serverList.get(x) instanceof EntityInhooS)) continue block19;
                    ((EntityInhooS)serverList.get((int)x)).disloNumberTwenty = true;
                    continue block19;
                }
                case 21: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberTwentyone = true;
                    continue block19;
                }
                case 22: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberTwentytwo = true;
                    continue block19;
                }
                default: {
                    return;
                }
            }
        }
    }

    private void midDislo(int id, int position, int code, World world) {
        switch (position) {
            case 12: {
                List serverList39 = world.field_72996_f;
                for (int x = 0; x < serverList39.size(); ++x) {
                    if (!(serverList39.get(x) instanceof EntityLivingBase) || serverList39.get(x) instanceof EntityParasiteBase) continue;
                    ((Entity)serverList39.get(x)).func_70097_a(DamageSource.field_82727_n, (float)code * (float)SPConfigSystems.disloSeconds);
                }
                break;
            }
            case 13: {
                List serverList3 = world.field_73010_i;
                for (EntityPlayer pla : serverList3) {
                    pla.func_71020_j((float)code * (float)SPConfigSystems.disloSeconds);
                }
                break;
            }
        }
    }

    private void endDislo(int position, World world) {
        List serverList = world.field_72996_f;
        block19: for (int x = 0; x < serverList.size(); ++x) {
            boolean flag = serverList.get(x) instanceof EntityParasiteBase;
            switch (position) {
                case 2: {
                    ParasiteEventEntity.disloNumber2(world);
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberTwo = false;
                    continue block19;
                }
                case 3: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberThree = false;
                    continue block19;
                }
                case 4: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberFour = false;
                    continue block19;
                }
                case 5: {
                    ParasiteEventEntity.disloNumber5(null, world);
                    continue block19;
                }
                case 6: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSix = false;
                    continue block19;
                }
                case 7: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSeven = false;
                    continue block19;
                }
                case 8: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberEight = false;
                    continue block19;
                }
                case 9: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberNine = false;
                    continue block19;
                }
                case 11: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberEleven = 0;
                    continue block19;
                }
                case 15: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberFifteen = 0;
                    ((EntityParasiteBase)serverList.get(x)).setDislo15(false);
                    continue block19;
                }
                case 16: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSixteen = 0;
                    continue block19;
                }
                case 17: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberSeventeen = 0;
                    continue block19;
                }
                case 18: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberEighteen = false;
                    continue block19;
                }
                case 19: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberNineteen = 0;
                    continue block19;
                }
                case 20: {
                    if (!flag) continue block19;
                    if (serverList.get(x) instanceof EntityInhooM) {
                        ((EntityInhooM)serverList.get((int)x)).disloNumberTwenty = false;
                    }
                    if (!(serverList.get(x) instanceof EntityInhooS)) continue block19;
                    ((EntityInhooS)serverList.get((int)x)).disloNumberTwenty = false;
                    continue block19;
                }
                case 21: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberTwentyone = false;
                    continue block19;
                }
                case 22: {
                    if (!flag) continue block19;
                    ((EntityParasiteBase)serverList.get((int)x)).disloNumberTwentytwo = false;
                    continue block19;
                }
                default: {
                    return;
                }
            }
        }
    }

    public void setGeneration(byte in, int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            this.dimGeneration.set(i, in);
        }
        this.func_76185_a();
    }

    public byte getGeneration(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimGeneration.get(i);
        }
        SPSaveData.addDim(id);
        return this.getGeneration(id);
    }

    public void setGenerationTime(int in, int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            this.dimGenerationTime.set(i, in);
        }
        this.func_76185_a();
    }

    public int getGenerationTime(int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            return this.dimGenerationTime.get(i);
        }
        SPSaveData.addDim(id);
        return this.getGenerationTime(id);
    }

    public int getGenerationNeededTime(World worldIn, int id) {
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            int currentWT = (int)worldIn.func_82737_E();
            int timer = currentWT - this.dimGenerationTime.get(i);
            int needed = this.getGenerationNeededTime(this.dimGeneration.get(i));
            if (!this.generationPhaseNeeded(this.dimGeneration.get(i), i)) {
                needed = (int)((float)needed * SPConfigSystems.generationPhasePenalty);
            }
            return Math.max(needed - timer, 0);
        }
        return 0;
    }

    public boolean checkGeneration(int id, World worldIn) {
        boolean change = true;
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            change = false;
            if (this.dimGeneration.get(i) == 5) {
                return false;
            }
            int currentWT = (int)worldIn.func_82737_E();
            int timer = currentWT - this.dimGenerationTime.get(i);
            int needed = this.getGenerationNeededTime(this.dimGeneration.get(i));
            if (!this.generationPhaseNeeded(this.dimGeneration.get(i), i)) {
                needed = (int)((float)needed * SPConfigSystems.generationPhasePenalty);
            }
            if (timer <= needed) continue;
            byte gene = (byte)(this.dimGeneration.get(i) + 1);
            this.dimGeneration.set(i, gene);
            this.dimGenerationTime.set(i, currentWT);
        }
        if (change) {
            SPSaveData.addDim(id);
        }
        this.func_76185_a();
        return true;
    }

    private boolean generationPhaseNeeded(byte gene, int id) {
        switch (gene) {
            case 0: {
                for (int i = 0; i < SPConfigSystems.generationPhases1.length; ++i) {
                    if (SPConfigSystems.generationPhases1[i] != this.dimEPevolution.get(id)) continue;
                    return true;
                }
                break;
            }
            case 1: {
                for (int i = 0; i < SPConfigSystems.generationPhases2.length; ++i) {
                    if (SPConfigSystems.generationPhases2[i] != this.dimEPevolution.get(id)) continue;
                    return true;
                }
                break;
            }
            case 2: {
                for (int i = 0; i < SPConfigSystems.generationPhases3.length; ++i) {
                    if (SPConfigSystems.generationPhases3[i] != this.dimEPevolution.get(id)) continue;
                    return true;
                }
                break;
            }
            case 3: {
                for (int i = 0; i < SPConfigSystems.generationPhases4.length; ++i) {
                    if (SPConfigSystems.generationPhases4[i] != this.dimEPevolution.get(id)) continue;
                    return true;
                }
                break;
            }
            case 4: {
                for (int i = 0; i < SPConfigSystems.generationPhases5.length; ++i) {
                    if (SPConfigSystems.generationPhases5[i] != this.dimEPevolution.get(id)) continue;
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private int getGenerationNeededTime(byte in) {
        double bonus = 1.0;
        switch (this.choice) {
            case 0: {
                bonus *= 0.5;
                break;
            }
            case 2: {
                bonus *= 3.0;
                break;
            }
            case 3: {
                bonus *= 10.0;
            }
        }
        if (bonus <= 0.0) {
            bonus = 1.0;
        }
        switch (in) {
            case 0: {
                return (int)Math.max(1L, Math.round((double)SPConfigSystems.generationTime1 / bonus));
            }
            case 1: {
                return (int)Math.max(1L, Math.round((double)SPConfigSystems.generationTime2 / bonus));
            }
            case 2: {
                return (int)Math.max(1L, Math.round((double)SPConfigSystems.generationTime3 / bonus));
            }
            case 3: {
                return (int)Math.max(1L, Math.round((double)SPConfigSystems.generationTime4 / bonus));
            }
            case 4: {
                return (int)Math.max(1L, Math.round((double)SPConfigSystems.generationTime5 / bonus));
            }
        }
        return 0;
    }

    public boolean[] getGeneModi(int id) {
        boolean[] loot = new boolean[]{true};
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            switch (this.dimGeneration.get(i)) {
                case 0: {
                    return new boolean[]{SPConfigSystems.generationMiniDamage0, SPConfigSystems.generationDamageCap0, SPConfigSystems.generationLookWalls0, SPConfigSystems.generationSprinting0, SPConfigSystems.generationWaterLeap0, SPConfigSystems.generationSpecialM0, SPConfigSystems.generationAdaptation0, SPConfigSystems.generationBlockSearch0, SPConfigSystems.generationResidue0, SPConfigSystems.generationOrbbox0};
                }
                case 1: {
                    return new boolean[]{SPConfigSystems.generationMiniDamage1, SPConfigSystems.generationDamageCap1, SPConfigSystems.generationLookWalls1, SPConfigSystems.generationSprinting1, SPConfigSystems.generationWaterLeap1, SPConfigSystems.generationSpecialM1, SPConfigSystems.generationAdaptation1, SPConfigSystems.generationBlockSearch1, SPConfigSystems.generationResidue1, SPConfigSystems.generationOrbbox1};
                }
                case 2: {
                    return new boolean[]{SPConfigSystems.generationMiniDamage2, SPConfigSystems.generationDamageCap2, SPConfigSystems.generationLookWalls2, SPConfigSystems.generationSprinting2, SPConfigSystems.generationWaterLeap2, SPConfigSystems.generationSpecialM2, SPConfigSystems.generationAdaptation2, SPConfigSystems.generationBlockSearch2, SPConfigSystems.generationResidue2, SPConfigSystems.generationOrbbox2};
                }
                case 3: {
                    return new boolean[]{SPConfigSystems.generationMiniDamage3, SPConfigSystems.generationDamageCap3, SPConfigSystems.generationLookWalls3, SPConfigSystems.generationSprinting3, SPConfigSystems.generationWaterLeap3, SPConfigSystems.generationSpecialM3, SPConfigSystems.generationAdaptation3, SPConfigSystems.generationBlockSearch3, SPConfigSystems.generationResidue3, SPConfigSystems.generationOrbbox3};
                }
                case 4: {
                    return new boolean[]{SPConfigSystems.generationMiniDamage4, SPConfigSystems.generationDamageCap4, SPConfigSystems.generationLookWalls4, SPConfigSystems.generationSprinting4, SPConfigSystems.generationWaterLeap4, SPConfigSystems.generationSpecialM4, SPConfigSystems.generationAdaptation4, SPConfigSystems.generationBlockSearch4, SPConfigSystems.generationResidue4, SPConfigSystems.generationOrbbox4};
                }
                case 5: {
                    return new boolean[]{SPConfigSystems.generationMiniDamage5, SPConfigSystems.generationDamageCap5, SPConfigSystems.generationLookWalls5, SPConfigSystems.generationSprinting5, SPConfigSystems.generationWaterLeap5, SPConfigSystems.generationSpecialM5, SPConfigSystems.generationAdaptation5, SPConfigSystems.generationBlockSearch5, SPConfigSystems.generationResidue5, SPConfigSystems.generationOrbbox5};
                }
            }
            return new boolean[]{true, true, true, true, true, true, true, true, true, true};
        }
        return loot;
    }

    public float[] getGeneModi2(int id) {
        float[] loot = new float[]{0.0f};
        for (int i = 0; i < this.dimEPid.size(); ++i) {
            if (this.dimEPid.get(i) != id) continue;
            switch (this.dimGeneration.get(i)) {
                case 0: {
                    return new float[]{SPConfigSystems.generationPoisonHeal0, SPConfigSystems.generationMobHealing0, SPConfigSystems.generationAttackSpeed0};
                }
                case 1: {
                    return new float[]{SPConfigSystems.generationPoisonHeal1, SPConfigSystems.generationMobHealing1, SPConfigSystems.generationAttackSpeed1};
                }
                case 2: {
                    return new float[]{SPConfigSystems.generationPoisonHeal2, SPConfigSystems.generationMobHealing2, SPConfigSystems.generationAttackSpeed2};
                }
                case 3: {
                    return new float[]{SPConfigSystems.generationPoisonHeal3, SPConfigSystems.generationMobHealing3, SPConfigSystems.generationAttackSpeed3};
                }
                case 4: {
                    return new float[]{SPConfigSystems.generationPoisonHeal4, SPConfigSystems.generationMobHealing4, SPConfigSystems.generationAttackSpeed4};
                }
                case 5: {
                    return new float[]{SPConfigSystems.generationPoisonHeal5, SPConfigSystems.generationMobHealing5, SPConfigSystems.generationAttackSpeed5};
                }
            }
            return new float[]{2.5f, 3.0f, 0.5f};
        }
        return loot;
    }

    public int[] getDeveDimsPhases() {
        int points = 0;
        int dims = 0;
        for (int i = 0; i < this.dimEPevolution.size(); ++i) {
            if (this.dimEPevolution.get(i) <= 0) continue;
            points += this.dimEPevolution.get(i).byteValue();
            ++dims;
        }
        return new int[]{points, dims};
    }

    public int getDeveLevel() {
        if (falseLevel != 0) {
            return falseLevel;
        }
        int[] check = this.getDeveDimsPhases();
        int value = check[0];
        int dims = check[1];
        if (value >= SPConfigSystems.devePointsFour && dims >= SPConfigSystems.deveMiniDimsFour) {
            return 4;
        }
        if (value >= SPConfigSystems.devePointsThree && dims >= SPConfigSystems.deveMiniDimsThree) {
            return 3;
        }
        if (value >= SPConfigSystems.devePointsTwo && dims >= SPConfigSystems.deveMiniDimsTwo) {
            return 2;
        }
        if (value >= SPConfigSystems.devePointsOne && dims >= SPConfigSystems.deveMiniDimsOne) {
            return 1;
        }
        return 0;
    }

    public ArrayList<Integer> getColonies(String i) {
        if (i.equals("d")) {
            return this.dimEPid;
        }
        if (i.equals("k")) {
            return this.dimEPtotalKills;
        }
        if (i.equals("p")) {
            ArrayList<Integer> eee = new ArrayList<Integer>();
            for (byte b : this.dimEPevolution) {
                eee.add(Integer.valueOf(b));
            }
            return eee;
        }
        if (i.equals("g")) {
            ArrayList<Integer> eee = new ArrayList<Integer>();
            for (byte b : this.dimGeneration) {
                eee.add(Integer.valueOf(b));
            }
            return eee;
        }
        return null;
    }

    static {
        clientInstance = null;
        falseLevel = 0;
    }
}

