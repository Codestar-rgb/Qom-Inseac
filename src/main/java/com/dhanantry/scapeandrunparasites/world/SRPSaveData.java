package com.dhanantry.scapeandrunparasites.world;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.network.SRPCommandDislodgment;
import com.dhanantry.scapeandrunparasites.network.SRPCommandEvolution;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMusicTrackCancelUpdateClientEvoPhase;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SRPSaveData extends WorldSavedData {
   private static final Logger SRP_LOG = LogManager.getLogger("srparasites");
   private static boolean warnedNoWorld = false;
   private int choice;
   private ArrayList<Integer> dimEPid = new ArrayList<>();
   private ArrayList<Integer> dimEPtotalKills = new ArrayList<>();
   private ArrayList<Byte> dimEPevolution = new ArrayList<>();
   private ArrayList<Integer> dimEPtimeEvolution = new ArrayList<>();
   private ArrayList<Boolean> dimEPcanGainPoints = new ArrayList<>();
   private ArrayList<Boolean> dimEPcanLossPoints = new ArrayList<>();
   private ArrayList<String> dimEPcurrentCodes = new ArrayList<>();
   private ArrayList<String> dimEPcurrentCodesDur = new ArrayList<>();
   private ArrayList<Byte> dimGeneration = new ArrayList<>();
   private ArrayList<Integer> dimGenerationTime = new ArrayList<>();
   private ArrayList<Integer> dimUpdates = new ArrayList<>();
   private ArrayList<Integer> dimEIVHealth = new ArrayList<>();
   private ArrayList<Integer> dimEIVArea = new ArrayList<>();
   private static final String DATA_NAME = "srparasites_global_data";
   private static final String DISLO_R = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
   private static SRPSaveData instance;
   private ArrayList<Integer> lockedParasites = new ArrayList<>();
   private ArrayList<Byte> simRegId = new ArrayList<>();
   private ArrayList<Integer> simRegIdTimes = new ArrayList<>();
   private static SRPSaveData clientInstance = null;
   public static int falseLevel = 0;

   @Nullable
   private static WorldServer getOverworldOrNull() {
      MinecraftServer srv = FMLCommonHandler.instance().getMinecraftServerInstance();
      return srv != null ? srv.func_71218_a(0) : null;
   }

   private static void warnNoWorldOnce(String where) {
      if (!warnedNoWorld) {
         warnedNoWorld = true;
         SRP_LOG.warn("[SRPSaveData] {} called with null world; skipping this tick until worlds are ready.", where);
      }
   }

   @Nullable
   private static WorldServer getWorldByDim(int dimId) {
      MinecraftServer srv = FMLCommonHandler.instance().getMinecraftServerInstance();
      return srv != null ? srv.func_71218_a(dimId) : null;
   }

   private static boolean debugPointConsole() {
      return SRPConfigSystems.debugEvolutionPointsConsole;
   }

   private static boolean debugPointCsv() {
      return SRPConfigSystems.debugEvolutionPointsCsv;
   }

   private static boolean debugPointCallerTrace() {
      return SRPConfigSystems.debugEvolutionPointsCallerTrace;
   }

   private static String getPointSourceName(int srcID) {
      switch (srcID) {
         case 1:
            return "EIV_DAILY_GROWTH";
         case 2:
            return "DIM_START_DEFAULT";
         case 3:
            return "DIM_START_OUTBREAK";
         case 4:
            return "KILL";
         case 5:
            return "ASSIMILATION";
         case 6:
            return "COMMAND";
         case 7:
            return "EVENT";
         case 8:
            return "COLONY";
         case 9:
            return "PENALTY_OR_LOSS";
         case 52:
            return "COTH_DURATION_REFRESH";
         default:
            return "UNKNOWN_" + srcID;
      }
   }

   private static String[] getPointCallerInfo() {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
      boolean foundPointMutationMethod = false;

      for (StackTraceElement element : stack) {
         String className = element.getClassName();
         String methodName = element.getMethodName();
         if (!className.endsWith("SRPSaveData") || !"setTotalKills".equals(methodName) && !"setCurrentCode".equals(methodName)) {
            if (foundPointMutationMethod && !className.endsWith("SRPSaveData")) {
               return new String[]{className, methodName, String.valueOf(element.getLineNumber())};
            }
         } else {
            foundPointMutationMethod = true;
         }
      }

      return new String[]{"unknown", "unknown", "-1"};
   }

   private static String csvSafe(String in) {
      return in == null ? "" : "\"" + in.replace("\"", "\"\"") + "\"";
   }

   private static File getPointDebugFile(World worldIn, int dimID) {
      File root = DimensionManager.getCurrentSaveRootDirectory();
      if (root == null && worldIn != null && worldIn.func_72860_G() != null) {
         root = worldIn.func_72860_G().func_75765_b();
      }

      if (root == null) {
         root = new File(".");
      }

      File folder = new File(root, "srparasites_debug");
      if (!folder.exists()) {
         folder.mkdirs();
      }

      return new File(folder, "evolution_points_dim" + dimID + ".csv");
   }

   private static void writePointCsv(
      World worldIn,
      int dimID,
      int srcID,
      String srcName,
      boolean plus,
      int requestedAmount,
      int processedAmount,
      int appliedDelta,
      int before,
      int after,
      int phaseBefore,
      int phaseAfter,
      boolean canChangePhase,
      boolean isFromSleep,
      String result
   ) {
      if (debugPointCsv()) {
         File file = getPointDebugFile(worldIn, dimID);
         boolean writeHeader = !file.exists() || file.length() == 0L;
         long worldTime = worldIn != null ? worldIn.func_82737_E() : -1L;
         long day = worldTime >= 0L ? worldTime / 24000L : -1L;
         String callerClass = "";
         String callerMethod = "";
         String callerLine = "";
         if (debugPointCallerTrace()) {
            String[] caller = getPointCallerInfo();
            callerClass = caller[0];
            callerMethod = caller[1];
            callerLine = caller[2];
         }

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (writeHeader) {
               writer.write(
                  "real_time_ms,world_time,day,dim,src_id,src_name,caller_class,caller_method,caller_line,plus,requested_amount,processed_amount,applied_delta,before,after,phase_before,phase_after,can_change_phase,is_from_sleep,result"
               );
               writer.newLine();
            }

            writer.write(
               System.currentTimeMillis()
                  + ","
                  + worldTime
                  + ","
                  + day
                  + ","
                  + dimID
                  + ","
                  + srcID
                  + ","
                  + csvSafe(srcName)
                  + ","
                  + csvSafe(callerClass)
                  + ","
                  + csvSafe(callerMethod)
                  + ","
                  + callerLine
                  + ","
                  + plus
                  + ","
                  + requestedAmount
                  + ","
                  + processedAmount
                  + ","
                  + appliedDelta
                  + ","
                  + before
                  + ","
                  + after
                  + ","
                  + phaseBefore
                  + ","
                  + phaseAfter
                  + ","
                  + canChangePhase
                  + ","
                  + isFromSleep
                  + ","
                  + csvSafe(result)
            );
            writer.newLine();
         } catch (IOException var37) {
            SRP_LOG.warn("[SRP EP DEBUG] Failed to write point CSV for dim {} src {}.", dimID, srcName, var37);
         }
      }
   }

   private static void logPointChange(
      World worldIn,
      int dimID,
      int srcID,
      boolean plus,
      int requestedAmount,
      int processedAmount,
      int before,
      int after,
      int phaseBefore,
      int phaseAfter,
      boolean canChangePhase,
      boolean isFromSleep,
      String result
   ) {
      String srcName = getPointSourceName(srcID);
      int appliedDelta = after - before;
      String callerClass = "";
      String callerMethod = "";
      String callerLine = "";
      if (debugPointCallerTrace()) {
         String[] caller = getPointCallerInfo();
         callerClass = caller[0];
         callerMethod = caller[1];
         callerLine = caller[2];
      }

      if (debugPointConsole()) {
         long worldTime = worldIn != null ? worldIn.func_82737_E() : -1L;
         long day = worldTime >= 0L ? worldTime / 24000L : -1L;
         SRP_LOG.debug(
            "[SRP EP DEBUG] result={} dim={} day={} time={} src={}({}) caller={}.{}:{} plus={} requested={} processed={} appliedDelta={} before={} after={} phase={}=>{} canChangePhase={} sleepBypass={}",
            new Object[]{
               result,
               dimID,
               day,
               worldTime,
               srcID,
               srcName,
               callerClass,
               callerMethod,
               callerLine,
               plus,
               requestedAmount,
               processedAmount,
               appliedDelta,
               before,
               after,
               phaseBefore,
               phaseAfter,
               canChangePhase,
               isFromSleep
            }
         );
      }

      writePointCsv(
         worldIn,
         dimID,
         srcID,
         srcName,
         plus,
         requestedAmount,
         processedAmount,
         appliedDelta,
         before,
         after,
         phaseBefore,
         phaseAfter,
         canChangePhase,
         isFromSleep,
         result
      );
   }

   private static void logPointRejected(
      World worldIn,
      int dimID,
      int srcID,
      boolean plus,
      int requestedAmount,
      int processedAmount,
      int before,
      int phaseBefore,
      boolean canChangePhase,
      boolean isFromSleep,
      String reason
   ) {
      logPointChange(
         worldIn,
         dimID,
         srcID,
         plus,
         requestedAmount,
         processedAmount,
         before,
         before,
         phaseBefore,
         phaseBefore,
         canChangePhase,
         isFromSleep,
         "REJECTED_" + reason
      );
   }

   public SRPSaveData() {
      super("srparasites_global_data");
   }

   public SRPSaveData(String name) {
      super(name);
   }

   public static SRPSaveData get(World world, int id) {
      if (world == null) {
         return null;
      } else if (world.field_72995_K) {
         if (clientInstance == null) {
            SRPSaveData tmpInstance = instance;
            instance = new SRPSaveData();
            clientInstance = createData(world, null, id);
            instance = tmpInstance;
         }

         return clientInstance;
      } else {
         MapStorage storage = world.func_175693_T();
         instance = (SRPSaveData)storage.func_75742_a(SRPSaveData.class, "srparasites_global_data");
         if (instance == null) {
            instance = new SRPSaveData();
            storage.func_75745_a("srparasites_global_data", instance);
            instance = createData(world, storage, id);
         }

         return instance;
      }
   }

   private static SRPSaveData createData(World world, MapStorage storage, int iddd) {
      int currentDim = world.field_73011_w.getDimension();
      System.out.println("creating save data------ " + iddd + " world is null? " + (world == null));
      String[] here = new String[3];
      int id = 0;

      for (int i = 0; i < SRPConfigSystems.evolutionParasiteLock.length; i++) {
         if (SRPConfigSystems.evolutionParasiteLock[i] != null) {
            here = SRPConfigSystems.evolutionParasiteLock[i].split(";");
            id = Integer.parseInt(here[2]);
            instance.lockedParasites.add(id);
         }
      }

      here = new String[3];

      for (int ix = 0; ix < SRPConfigSystems.evolutionDimStart.length; ix++) {
         here = SRPConfigSystems.evolutionDimStart[ix].split(";");
         int dim = Integer.parseInt(here[0]);
         int phase = Integer.parseInt(here[1]);
         int points = Integer.parseInt(here[2]);
         addDim(dim);
         instance.setEvolutionPhase(dim, (byte)phase, true, world);
         if (phase == -1) {
            points = Integer.parseInt(here[2]);
            instance.setTotalKills(dim, -points, false, world, true, 3);
         } else if (phase == -2) {
            instance.setGaining(false, dim);
            instance.setLoss(false, dim);
         } else {
            instance.setTotalKills(dim, points, false, world, true, 2);
         }
      }

      for (int ixx = 0; ixx < SRPConfigSystems.generationDimStart.length; ixx++) {
         here = SRPConfigSystems.generationDimStart[ixx].split(";");
         int dim = Integer.parseInt(here[0]);
         int gen = Integer.parseInt(here[1]);
         addDim(dim);
         instance.setGeneration((byte)gen, dim);
      }

      return instance;
   }

   private static void addDim(int id) {
      for (int i = 0; i < instance.dimEPid.size(); i++) {
         if (instance.dimEPid.get(i) == id) {
            return;
         }
      }

      instance.dimEPid.add(id);
      instance.dimEPcanGainPoints.add(true);
      instance.dimEPcanLossPoints.add(true);
      instance.dimEPevolution.add(SRPConfigSystems.defaultEvoPhase);
      instance.dimEPtimeEvolution.add(0);
      instance.dimEPtotalKills.add(SRPConfigSystems.defaultEvoPoints);
      instance.dimEPcurrentCodes.add("0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0");
      instance.dimEPcurrentCodesDur.add("0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0");
      instance.dimGeneration.add(SRPConfigSystems.generationDefa);
      instance.dimGenerationTime.add(0);
      instance.dimUpdates.add(0);
      instance.dimEIVHealth.add(0);
      instance.dimEIVArea.add(0);
      instance.choice = SRPWorldEntitySpawner.choiceNUMBER;
      instance.func_76185_a();
   }

   public void func_76184_a(NBTTagCompound compound) {
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
         if (dimid.func_74745_c() == evolution.func_74745_c()
            && dimid.func_74745_c() == kills.func_74745_c()
            && dimid.func_74745_c() == gaining.func_74745_c()
            && dimid.func_74745_c() == loss.func_74745_c()
            && dimid.func_74745_c() == time.func_74745_c()
            && dimid.func_74745_c() == currentC.func_74745_c()
            && dimid.func_74745_c() == currentCDur.func_74745_c()
            && dimid.func_74745_c() == gene.func_74745_c()
            && dimid.func_74745_c() == genetime.func_74745_c()
            && dimid.func_74745_c() == updates.func_74745_c()
            && dimid.func_74745_c() == eivH.func_74745_c()
            && dimid.func_74745_c() == eivA.func_74745_c()) {
            for (int i = 0; i < dimid.func_74745_c(); i++) {
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
         } else {
            SRPMain.logger.log(Level.ERROR, "Problem while reading Evolution Phases from the World NBT");
         }
      }

      if (compound.func_74764_b("srplockedparasites")) {
         NBTTagList tagListX = compound.func_150295_c("srplockedparasites", 10);

         for (int i = 0; i < tagListX.func_74745_c(); i++) {
            NBTTagCompound tagX = tagListX.func_150305_b(i);
            int id = tagX.func_74762_e("parasiteid" + i);
            this.lockedParasites.add(i, id);
         }
      }

      if (compound.func_74764_b("srpassimilatedtotal")) {
         NBTTagList tagListX = compound.func_150295_c("srpassimilatedtotal", 10);
         NBTTagList tagListY = compound.func_150295_c("srpassimilatedtotaltimes", 10);

         for (int i = 0; i < tagListX.func_74745_c(); i++) {
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
      if (this.dimEPid.size() == this.dimEPevolution.size()
         && this.dimEPid.size() == this.dimEPtotalKills.size()
         && this.dimEPid.size() == this.dimEPcanGainPoints.size()
         && this.dimEPid.size() == this.dimEPcanLossPoints.size()
         && this.dimEPid.size() == this.dimEPtimeEvolution.size()
         && this.dimEPid.size() == this.dimEPcurrentCodes.size()
         && this.dimEPid.size() == this.dimEPcurrentCodesDur.size()
         && this.dimEPid.size() == this.dimEIVHealth.size()
         && this.dimEPid.size() == this.dimEIVArea.size()) {
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

         for (int i = 0; i < this.dimEPid.size(); i++) {
            NBTTagCompound tagA = new NBTTagCompound();
            tagA.func_74768_a("kills" + i, this.dimEPtotalKills.get(i));
            kills.func_74742_a(tagA);
            NBTTagCompound tagB = new NBTTagCompound();
            tagB.func_74768_a("time" + i, this.dimEPtimeEvolution.get(i));
            time.func_74742_a(tagB);
            NBTTagCompound tagC = new NBTTagCompound();
            tagC.func_74768_a("ev" + i, this.dimEPevolution.get(i));
            evolution.func_74742_a(tagC);
            NBTTagCompound tagD = new NBTTagCompound();
            tagD.func_74757_a("gain" + i, this.dimEPcanGainPoints.get(i));
            gaining.func_74742_a(tagD);
            NBTTagCompound tagE = new NBTTagCompound();
            tagE.func_74757_a("canLose" + i, this.dimEPcanLossPoints.get(i));
            loss.func_74742_a(tagE);
            NBTTagCompound tagF = new NBTTagCompound();
            tagF.func_74768_a("dimid" + i, this.dimEPid.get(i));
            dimid.func_74742_a(tagF);
            NBTTagCompound tagG = new NBTTagCompound();
            tagG.func_74778_a("code" + i, this.dimEPcurrentCodes.get(i));
            currentC.func_74742_a(tagG);
            NBTTagCompound tagH = new NBTTagCompound();
            tagH.func_74778_a("codedur" + i, this.dimEPcurrentCodesDur.get(i));
            currentCDur.func_74742_a(tagH);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74774_a("generation" + i, this.dimGeneration.get(i));
            tagListXX.func_74742_a(tagX);
            NBTTagCompound tagY = new NBTTagCompound();
            tagY.func_74768_a("generationtime" + i, this.dimGenerationTime.get(i));
            tagListXY.func_74742_a(tagY);
            NBTTagCompound tagZ = new NBTTagCompound();
            tagZ.func_74768_a("update" + i, this.dimUpdates.get(i));
            tagListXZ.func_74742_a(tagZ);
            NBTTagCompound tagEIVH = new NBTTagCompound();
            tagEIVH.func_74768_a("eivh" + i, this.dimEIVHealth.get(i));
            tagListEIVH.func_74742_a(tagEIVH);
            NBTTagCompound tagEIVA = new NBTTagCompound();
            tagEIVA.func_74768_a("eiva" + i, this.dimEIVArea.get(i));
            tagListEIVA.func_74742_a(tagEIVA);
         }

         compound.func_74782_a("srpparasitepoints", kills);
         compound.func_74782_a("srpparasitetimeevoworld", time);
         compound.func_74782_a("srpparasiteevolution", evolution);
         compound.func_74782_a("srpevolutiongaining", gaining);
         compound.func_74782_a("srpevolutionloss", loss);
         compound.func_74782_a("srpevolutiondimid", dimid);
         compound.func_74782_a("srpevolutioncodes", currentC);
         compound.func_74782_a("srpevolutioncodesdur", currentCDur);
         compound.func_74782_a("srpgeneration", tagListXX);
         compound.func_74782_a("srpgenerationtime", tagListXY);
         compound.func_74782_a("srpworldupdates", tagListXZ);
         compound.func_74782_a("srpeivhealth", tagListEIVH);
         compound.func_74782_a("srpeivarea", tagListEIVA);
      } else {
         SRPMain.logger.log(Level.ERROR, "Problem while writing Evolution Phases in the World NBT");
      }

      NBTTagList tagListXX = new NBTTagList();

      for (int i = 0; i < this.lockedParasites.size(); i++) {
         int id = this.lockedParasites.get(i);
         NBTTagCompound tagX = new NBTTagCompound();
         tagX.func_74768_a("parasiteid" + i, id);
         tagListXX.func_74742_a(tagX);
      }

      compound.func_74782_a("srplockedparasites", tagListXX);
      tagListXX = new NBTTagList();
      NBTTagList tagListYY = new NBTTagList();

      for (int i = 0; i < this.simRegId.size(); i++) {
         int ix = this.simRegId.get(i);
         NBTTagCompound tagX = new NBTTagCompound();
         tagX.func_74768_a("srpassimilatedtotalid" + i, ix);
         tagListXX.func_74742_a(tagX);
         int iy = this.simRegIdTimes.get(i);
         NBTTagCompound tagY = new NBTTagCompound();
         tagY.func_74768_a("srpassimilatedtotalidtimes" + i, iy);
         tagListYY.func_74742_a(tagY);
      }

      compound.func_74782_a("srpassimilatedtotal", tagListXX);
      compound.func_74782_a("srpassimilatedtotaltimes", tagListYY);
      return compound;
   }

   public int getChoice() {
      return this.choice;
   }

   public boolean checkParasiteID(int in) {
      for (int i = 0; i < this.lockedParasites.size(); i++) {
         if (this.lockedParasites.get(i) == in) {
            return true;
         }
      }

      return false;
   }

   public ArrayList<Integer> getLockedList() {
      return this.lockedParasites;
   }

   public boolean unlockParasite(int in) {
      for (int i = 0; i < this.lockedParasites.size(); i++) {
         if (this.lockedParasites.get(i) == in) {
            this.lockedParasites.remove(i);
            this.func_76185_a();
            return true;
         }
      }

      return false;
   }

   public boolean unlockAllParasite() {
      this.lockedParasites = new ArrayList<>();
      this.func_76185_a();
      return false;
   }

   public void resetLock() {
      ArrayList<Integer> aaa = new ArrayList<>();
      String[] here = new String[3];
      int id = 0;

      for (int i = 0; i < SRPConfigSystems.evolutionParasiteLock.length; i++) {
         if (SRPConfigSystems.evolutionParasiteLock[i] != null) {
            here = SRPConfigSystems.evolutionParasiteLock[i].split(";");
            id = Integer.parseInt(here[2]);
            aaa.add(id);
         }
      }

      this.lockedParasites = aaa;
      this.func_76185_a();
   }

   public int getEIVHealth(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEIVHealth.get(i);
         }
      }

      return 0;
   }

   public int getEIVArea(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEIVArea.get(i);
         }
      }

      return 0;
   }

   public void setEIVHealthArea(int id, int health, int area) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            this.dimEIVHealth.set(i, health);
            this.dimEIVArea.set(i, area);
            this.func_76185_a();
         }
      }
   }

   public void addUpdateNumber(int in) {
      if (SRPConfigWorld.originActivated) {
         for (int i = 0; i < this.dimEPid.size(); i++) {
            int current = this.dimUpdates.get(i) + in;
            if (current <= 0) {
               current = 0;
            }

            this.dimUpdates.set(i, current);
            int health = this.dimEIVHealth.get(i);
            int size = this.dimEIVArea.get(i);
            long newHealth = health;
            long newSize = size;
            newHealth = (long)(newHealth + size * (SRPConfigWorld.originDailyHealth + SRPWorldData.getPhaseHealth(this.dimEPevolution.get(i))));
            newSize = (long)(newSize + size * (SRPConfigWorld.originDailySize + SRPWorldData.getPhaseSize(this.dimEPevolution.get(i))));
            health = newHealth > 2147483647L ? Integer.MAX_VALUE : (int)newHealth;
            size = newSize > 2147483647L ? Integer.MAX_VALUE : (int)newSize;
            this.dimEIVHealth.set(i, Math.min(health, SRPConfigWorld.originHealthCap));
            this.dimEIVArea.set(i, Math.min(size, SRPConfigWorld.originRadiusCap));
            if (!debugPointCsv()) {
               SRP_LOG.debug(
                  "[SRP EP DEBUG] VECTOR_DAY dim={} phase={} eivHealth={} eivArea={}",
                  this.dimEPid.get(i),
                  this.dimEPevolution.get(i),
                  this.dimEIVHealth.get(i),
                  this.dimEIVArea.get(i)
               );
            }

            long points = this.dimEPevolution.get(i) == -1
               ? (int)(this.dimEIVHealth.get(i).intValue() * SRPConfigWorld.originDailyEPPointsOutBreak)
               : (int)(this.dimEIVHealth.get(i).intValue() * SRPConfigWorld.originDailyEPPoints);
            int pointToAdd = points > 2147483647L ? Integer.MAX_VALUE : (int)points;
            pointToAdd = Math.min(pointToAdd, SRPCommandEvolution.getVectorPointCap(this.dimEPevolution.get(i)));
            this.setTotalKills(this.dimEPid.get(i), pointToAdd, true, DimensionManager.getWorld(this.dimEPid.get(i)), true, true, 1);
         }
      }

      this.func_76185_a();
   }

   public int getUpdateNumber(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            int u = this.dimUpdates.get(i);
            this.dimUpdates.set(i, 0);
            this.func_76185_a();
            return u;
         }
      }

      addDim(id);
      return this.getGeneration(id);
   }

   public void setGaining(boolean in, int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            this.dimEPcanGainPoints.set(i, in);
            this.func_76185_a();
         }
      }
   }

   public boolean getCanGain(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEPcanGainPoints.get(i);
         }
      }

      addDim(id);
      return this.getCanGain(id);
   }

   public void setLoss(boolean in, int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            this.dimEPcanLossPoints.set(i, in);
            this.func_76185_a();
         }
      }
   }

   public boolean getCanLoss(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEPcanLossPoints.get(i);
         }
      }

      addDim(id);
      return this.getCanLoss(id);
   }

   public byte getEvolutionPhase(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEPevolution.get(i);
         }
      }

      addDim(id);
      return this.getEvolutionPhase(id);
   }

   public void setCooldown(int in, World worldIn, int id, boolean adding) {
      int currentWT = (int)worldIn.func_82737_E();

      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
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
   }

   public int getCooldown(World worldIn, int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            if (this.dimEPtimeEvolution.get(i) == 0) {
               return 0;
            }

            int currentWT = (int)worldIn.func_82737_E();
            return Math.max((this.getDelay(this.getEvolutionPhase(id)) * 20 - (currentWT - this.dimEPtimeEvolution.get(i))) / 20, 0);
         }
      }

      return 0;
   }

   public int getTotalKills(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEPtotalKills.get(i);
         }
      }

      addDim(id);
      return this.getTotalKills(id);
   }

   public boolean setTotalKills(int dimID, int in, boolean plus, World worldIn, boolean canChangePhase, int srcID) {
      return this.setTotalKills(dimID, in, plus, worldIn, canChangePhase, false, srcID);
   }

   public boolean setTotalKills(int dimID, int in, boolean plus, World worldIn, boolean canChangePhase, boolean isFromSleep, int srcID) {
      if (!SRPConfigSystems.useEvolution) {
         return false;
      } else {
         int requestedAmount = in;
         boolean requestedPlus = plus;
         boolean requestedCanChangePhase = canChangePhase;
         boolean change = true;
         if (in > 0) {
            switch (this.choice) {
               case 0:
                  in = (int)(in * 0.5);
               case 1:
               default:
                  break;
               case 2:
                  in *= 3;
                  isFromSleep = true;
                  break;
               case 3:
                  in *= 10;
                  isFromSleep = true;
            }
         }

         if (worldIn != null) {
            if (SRPConfigSystems.evolutionNoPlayerMultipler && worldIn.field_73010_i.isEmpty()) {
               return false;
            }

            int difficultyCap = worldIn.func_175659_aa().func_151525_a() / 2 * SRPConfigSystems.evolutionPointCap;
            if (!isFromSleep && plus) {
               in = Math.min(in, difficultyCap);
            }
         }

         for (int i = 0; i < this.dimEPid.size(); i++) {
            if (this.dimEPid.get(i) == dimID) {
               if (!this.dimEPcanGainPoints.get(i) && plus && in > 0) {
                  logPointRejected(
                     worldIn,
                     dimID,
                     srcID,
                     plus,
                     requestedAmount,
                     in,
                     this.dimEPtotalKills.get(i),
                     this.dimEPevolution.get(i),
                     canChangePhase,
                     isFromSleep,
                     "GAIN_DISABLED"
                  );
                  return false;
               }

               if (!this.dimEPcanLossPoints.get(i) && plus && in < 0) {
                  logPointRejected(
                     worldIn,
                     dimID,
                     srcID,
                     plus,
                     requestedAmount,
                     in,
                     this.dimEPtotalKills.get(i),
                     this.dimEPevolution.get(i),
                     canChangePhase,
                     isFromSleep,
                     "LOSS_DISABLED"
                  );
                  return false;
               }

               if (this.dimEPevolution.get(i) == -2) {
                  logPointRejected(
                     worldIn,
                     dimID,
                     srcID,
                     plus,
                     requestedAmount,
                     in,
                     this.dimEPtotalKills.get(i),
                     this.dimEPevolution.get(i),
                     canChangePhase,
                     isFromSleep,
                     "PHASE_NEGATIVE_TWO"
                  );
                  return false;
               }

               if (this.getDelay(this.dimEPevolution.get(i)) != 0 && worldIn != null && this.choice != 3 && this.getCooldown(worldIn, dimID) != 0 && plus) {
                  logPointRejected(
                     worldIn,
                     dimID,
                     srcID,
                     plus,
                     requestedAmount,
                     in,
                     this.dimEPtotalKills.get(i),
                     this.dimEPevolution.get(i),
                     canChangePhase,
                     isFromSleep,
                     "COOLDOWN"
                  );
                  return false;
               }

               change = false;
               if (worldIn != null) {
                  this.checkGeneration(dimID, worldIn);
               }

               int beforePoints = this.dimEPtotalKills.get(i);
               int phaseBefore = this.dimEPevolution.get(i);
               if (plus) {
                  int kills = this.dimEPtotalKills.get(i);
                  int phase = this.dimEPevolution.get(i);
                  boolean top = kills >= 0 && in > 0;
                  if (in < 0 && phase < 0) {
                     return false;
                  }

                  kills += in;
                  if (SRPConfigSystems.debugEvolutionPointsConsole) {
                     SRP_LOG.debug(
                        "[SRP EP DEBUG] calculated points dim={} src={} before={} change={} after={} phase={} canChangePhase={} sleepBypass={}",
                        dimID,
                        srcID,
                        beforePoints,
                        in,
                        kills,
                        phase,
                        canChangePhase,
                        isFromSleep
                     );
                  }

                  if (top && kills < 0) {
                     kills = this.dimEPtotalKills.get(i);
                  }

                  if (!canChangePhase && phase >= 0) {
                     int lower = SRPCommandEvolution.getNeededPoints(this.getEvolutionPhase(dimID));
                     if (kills < lower) {
                        kills = lower;
                     }
                  }

                  if (kills < 0 && phase >= 0) {
                     kills = 0;
                  }

                  if (kills > SRPConfigSystems.phaseTenTotalPoints) {
                     kills = SRPConfigSystems.phaseTenTotalPoints;
                  }

                  this.checkKills(dimID, kills, worldIn, this.dimEPevolution.get(i));
                  this.dimEPtotalKills.set(i, kills);
                  int phaseAfter = this.dimEPevolution.get(i);
                  logPointChange(
                     worldIn,
                     dimID,
                     srcID,
                     requestedPlus,
                     requestedAmount,
                     in,
                     beforePoints,
                     kills,
                     phaseBefore,
                     phaseAfter,
                     requestedCanChangePhase,
                     isFromSleep,
                     "APPLIED"
                  );
               } else {
                  this.dimEPtotalKills.set(i, in);
                  int phaseAfter = this.dimEPevolution.get(i);
                  logPointChange(
                     worldIn,
                     dimID,
                     srcID,
                     requestedPlus,
                     requestedAmount,
                     in,
                     beforePoints,
                     in,
                     phaseBefore,
                     phaseAfter,
                     requestedCanChangePhase,
                     isFromSleep,
                     "SET_DIRECT"
                  );
               }
            }
         }

         if (change) {
            addDim(dimID);
         }

         this.func_76185_a();
         return true;
      }
   }

   private int getDelay(byte in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseDelayTicksOne;
         case 2:
            return SRPConfigSystems.phaseDelayTicksTwo;
         case 3:
            return SRPConfigSystems.phaseDelayTicksThree;
         case 4:
            return SRPConfigSystems.phaseDelayTicksFour;
         case 5:
            return SRPConfigSystems.phaseDelayTicksFive;
         case 6:
            return SRPConfigSystems.phaseDelayTicksSix;
         case 7:
            return SRPConfigSystems.phaseDelayTicksSeven;
         case 8:
            return SRPConfigSystems.phaseDelayTicksEight;
         case 9:
            return SRPConfigSystems.phaseDelayTicksNine;
         case 10:
            return SRPConfigSystems.phaseDelayTicksTen;
         default:
            return 0;
      }
   }

   private boolean checkKills(int id, int in, World worldIn, byte evoPhase) {
      boolean flag = false;
      boolean changed = false;
      switch (evoPhase) {
         case -1:
            if (in > 0 && this.setEvolutionPhase(id, (byte)0, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningZero, 0);
               changed = true;
            }
            break;
         case 0:
            if (in > SRPConfigSystems.phaseKillsOne && this.setEvolutionPhase(id, (byte)1, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningOne, 1);
               changed = true;
            }
            break;
         case 1:
            if (in > SRPConfigSystems.phaseKillsTwo) {
               if (this.setEvolutionPhase(id, (byte)2, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningTwo, 2);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsOne && this.setEvolutionPhase(id, (byte)0, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 2:
            if (in > SRPConfigSystems.phaseKillsThree) {
               if (this.setEvolutionPhase(id, (byte)3, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningThree, 3);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsTwo && this.setEvolutionPhase(id, (byte)1, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 3:
            if (in > SRPConfigSystems.phaseKillsFour) {
               if (this.setEvolutionPhase(id, (byte)4, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningFour, 4);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsThree && this.setEvolutionPhase(id, (byte)2, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 4:
            if (in > SRPConfigSystems.phaseKillsFive) {
               if (this.setEvolutionPhase(id, (byte)5, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningFive, 5);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsFour && this.setEvolutionPhase(id, (byte)3, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 5:
            if (in > SRPConfigSystems.phaseKillsSix) {
               if (this.setEvolutionPhase(id, (byte)6, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningSix, 6);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsFive && this.setEvolutionPhase(id, (byte)4, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 6:
            if (in > SRPConfigSystems.phaseKillsSeven) {
               if (this.setEvolutionPhase(id, (byte)7, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningSeven, 7);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsSix && this.setEvolutionPhase(id, (byte)5, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 7:
            if (in > SRPConfigSystems.phaseKillsEight) {
               if (this.setEvolutionPhase(id, (byte)8, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningEight, 8);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsSeven && this.setEvolutionPhase(id, (byte)6, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 8:
            if (in > SRPConfigSystems.phaseKillsNine) {
               if (this.setEvolutionPhase(id, (byte)9, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningNine, 9);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsEight && this.setEvolutionPhase(id, (byte)7, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 9:
            if (in > SRPConfigSystems.phaseKillsTen) {
               if (this.setEvolutionPhase(id, (byte)10, false, worldIn)) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigSystems.phaseWarningTen, 10);
                  changed = true;
               }
            } else if (in < SRPConfigSystems.phaseKillsNine && this.setEvolutionPhase(id, (byte)8, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
            break;
         case 10:
            if (in < SRPConfigSystems.phaseKillsTen && this.setEvolutionPhase(id, (byte)9, false, worldIn)) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, "Phase decreased", -7);
            }
      }

      return in > 0 && changed && evoPhase > -3 && evoPhase < 11 ? this.checkKills(id, in, worldIn, (byte)(evoPhase + 1)) : true;
   }

   public boolean setEvolutionPhase(int id, byte in, boolean override, World worldIn) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            SRPMain.network.sendToDimension(new SRPPacketMusicTrackCancelUpdateClientEvoPhase(in), id);
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
      }

      this.func_76185_a();
      return true;
   }

   private void checkForUnlock(byte phase, int worldId, World w) {
      String[] here = new String[3];
      int id = 0;
      int dim = 0;
      byte p = 0;

      for (int i = 0; i < SRPConfigSystems.evolutionParasiteLock.length; i++) {
         if (SRPConfigSystems.evolutionParasiteLock[i] != null) {
            here = SRPConfigSystems.evolutionParasiteLock[i].split(";");
            dim = Integer.parseInt(here[0]);
            if (worldId == dim) {
               p = Byte.parseByte(here[1]);
               if (p == phase) {
                  id = Integer.parseInt(here[2]);
                  if (this.unlockParasite(id)) {
                     ParasiteEventEntity.alertAllPlayerSer(w, SRPConfigSystems.evolutionParasiteLockMessage);
                  }
               }
            }
         }
      }
   }

   private void checkPhase(int id, byte in, World worldIn) {
      switch (in) {
         case -1:
            this.setTotalKills(id, -10, false, worldIn, true, 4);
            break;
         case 0:
            this.setTotalKills(id, 0, false, worldIn, true, 5);
            break;
         case 1:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsOne, false, worldIn, true, 6);
            break;
         case 2:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsTwo, false, worldIn, true, 7);
            break;
         case 3:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsThree, false, worldIn, true, 8);
            break;
         case 4:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsFour, false, worldIn, true, 9);
            break;
         case 5:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsFive, false, worldIn, true, 10);
            break;
         case 6:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsSix, false, worldIn, true, 11);
            break;
         case 7:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsSeven, false, worldIn, true, 12);
            break;
         case 8:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsEight, false, worldIn, true, 13);
            break;
         case 9:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsNine, false, worldIn, true, 14);
            break;
         case 10:
            this.setTotalKills(id, SRPConfigSystems.phaseKillsTen, false, worldIn, true, 15);
      }
   }

   public int getNumberIDDataSpawn(int id) {
      for (int i = 0; i < this.simRegId.size(); i++) {
         if (this.simRegId.get(i) == id) {
            return this.simRegIdTimes.get(i);
         }
      }

      return 0;
   }

   public void addNumberIDDataSpawn(int id) {
      boolean flag = true;

      for (int i = 0; i < this.simRegId.size(); i++) {
         if (this.simRegId.get(i) == id) {
            int tt = this.simRegIdTimes.get(i);
            this.simRegIdTimes.set(i, tt + 1);
            flag = false;
         }
      }

      if (flag) {
         this.simRegId.add((byte)id);
         this.simRegIdTimes.add(1);
      }
   }

   public String getCurrentCodeU(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEPcurrentCodes.get(i);
         }
      }

      addDim(id);
      return "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
   }

   public String getCurrentCodeUD(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimEPcurrentCodesDur.get(i);
         }
      }

      addDim(id);
      return "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
   }

   public int getCurrentCode(int id, int position) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            String[] here = new String[30];
            here = this.dimEPcurrentCodes.get(i).split(";");
            return Integer.parseInt(here[position]);
         }
      }

      addDim(id);
      return 0;
   }

   public int[] getDisloValues(int id) {
      int[] values = new int[30];

      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            String[] here = new String[30];
            here = this.dimEPcurrentCodes.get(i).split(";");

            for (int k = 0; k < here.length; k++) {
               values[k] = Integer.parseInt(here[k]);
            }
         }
      }

      return values;
   }

   public int getCurrentCodeDuration(int id, int position) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            String[] here = new String[30];
            here = this.dimEPcurrentCodesDur.get(i).split(";");
            return Integer.parseInt(here[position]);
         }
      }

      addDim(id);
      return 0;
   }

   public boolean setCurrentCode(int id, int position, int code, int time, World worldIn, boolean start, int pointCost) {
      if (position <= 29 && position >= 0) {
         if (code >= 0 && time >= 0 && pointCost >= 0) {
            if (code == 0) {
               time = 0;
            }

            for (int i = 0; i < this.dimEPid.size(); i++) {
               if (this.dimEPid.get(i) == id) {
                  if (this.getCurrentCodeDuration(id, position) != 0) {
                     return false;
                  }

                  if (pointCost > 0 && SRPConfigSystems.useEvolution) {
                     int beforePoints = this.dimEPtotalKills.get(i);
                     int point = beforePoints - pointCost;
                     if (point < SRPCommandEvolution.getNeededPoints(this.dimEPevolution.get(i))) {
                        return false;
                     }

                     byte phaseBefore = this.dimEPevolution.get(i);
                     this.dimEPtotalKills.set(i, point);
                     byte phaseAfter = this.dimEPevolution.get(i);
                     logPointChange(
                        worldIn,
                        id,
                        70 + position,
                        true,
                        -pointCost,
                        -pointCost,
                        beforePoints,
                        point,
                        phaseBefore,
                        phaseAfter,
                        false,
                        false,
                        "DISLODGEMENT_COST"
                     );
                  }

                  String[] here = new String[30];
                  here = this.dimEPcurrentCodes.get(i).split(";");
                  here[position] = String.valueOf(code);
                  this.dimEPcurrentCodes.set(i, this.fixArray(here));
                  here = this.dimEPcurrentCodesDur.get(i).split(";");
                  here[position] = String.valueOf(time);
                  this.dimEPcurrentCodesDur.set(i, this.fixArray(here));
                  if (worldIn != null) {
                     ParasiteEventEntity.alertAllPlayerDim(worldIn, this.getMessageStartEnd(position, start), 200 + position);
                     if (start) {
                        this.startDislo(i, position, code, time, worldIn);
                     }
                  }
               }
            }

            this.func_76185_a();
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void reduceCodesCooldown(int id, int amount, World worldIn) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            String[] here = new String[30];
            here = this.dimEPcurrentCodesDur.get(i).split(";");

            for (int k = 0; k < here.length; k++) {
               int cool = Integer.parseInt(here[k]);
               if (cool != 0) {
                  boolean positivee = cool > 0;
                  if (positivee) {
                     this.midDislo(id, k, this.getCurrentCode(id, k), worldIn);
                     cool = Math.max(cool - amount, 0);
                  } else {
                     cool = Math.min(cool + amount, 0);
                  }

                  here[k] = String.valueOf(cool);
                  if (cool == 0 && positivee) {
                     int cooldown = -SRPCommandDislodgment.getDisloCooldown(k);
                     cooldown *= (int)SRPCommandDislodgment.getDisloPhaseCooldown(this.dimEPevolution.get(i));
                     here[k] = String.valueOf(cooldown);
                     String[] nohere = new String[30];
                     nohere = this.dimEPcurrentCodes.get(i).split(";");
                     nohere[k] = String.valueOf(0);
                     this.dimEPcurrentCodes.set(i, this.fixArray(nohere));
                     if (worldIn != null) {
                        ParasiteEventEntity.alertAllPlayerDim(worldIn, this.getMessageStartEnd(k, false), 200 + k);
                        this.endDislo(k, worldIn);
                     }
                  }
               }
            }

            this.dimEPcurrentCodesDur.set(i, this.fixArray(here));
         }
      }

      this.func_76185_a();
   }

   private String fixArray(String[] list) {
      String atm = "";

      for (int k = 0; k < list.length; k++) {
         if (k == list.length - 1) {
            atm = atm + list[k];
         } else {
            atm = atm + list[k] + ";";
         }
      }

      return atm;
   }

   private String getMessageStartEnd(int code, boolean start) {
      switch (code) {
         case 0:
            if (start) {
               return SRPConfigSystems.disloCOTHIgnoreAmpStartMess;
            }

            return SRPConfigSystems.disloCOTHIgnoreAmpEndMess;
         case 1:
            if (start) {
               return SRPConfigSystems.disloCOTHTiersStartMess;
            }

            return SRPConfigSystems.disloCOTHTiersEndMess;
         case 2:
            if (start) {
               return SRPConfigSystems.disloSummonByDeathStartMess;
            }

            return SRPConfigSystems.disloSummonByDeathEndMess;
         case 3:
            if (start) {
               return SRPConfigSystems.disloPotiEffStartMess;
            }

            return SRPConfigSystems.disloPotiEffEndMess;
         case 4:
            if (start) {
               return SRPConfigSystems.disloStatsStartMess;
            }

            return SRPConfigSystems.disloStatsEndMess;
         case 5:
            if (start) {
               return SRPConfigSystems.disloDeathRaidS;
            }

            return SRPConfigSystems.disloDeathRaidE;
         case 6:
            if (start) {
               return SRPConfigSystems.disloItemDuraS;
            }

            return SRPConfigSystems.disloItemDuraE;
         case 7:
            if (start) {
               return SRPConfigSystems.disloHealingDeathS;
            }

            return SRPConfigSystems.disloHealingDeathE;
         case 8:
            if (start) {
               return SRPConfigSystems.disloDamageDeathS;
            }

            return SRPConfigSystems.disloDamageDeathE;
         case 9:
            if (start) {
               return SRPConfigSystems.disloFoodDeathS;
            }

            return SRPConfigSystems.disloFoodDeathE;
         case 10:
            if (start) {
               return SRPConfigSystems.disloDeathHighVerionsS;
            }

            return SRPConfigSystems.disloDeathHighVerionsE;
         case 11:
            if (start) {
               return SRPConfigSystems.disloParasiteNoPotionS;
            }

            return SRPConfigSystems.disloParasiteNoPotionE;
         case 12:
            if (start) {
               return SRPConfigSystems.disloHealthDrainingS;
            }

            return SRPConfigSystems.disloHealthDrainingE;
         case 13:
            if (start) {
               return SRPConfigSystems.disloFoodDrainingS;
            }

            return SRPConfigSystems.disloFoodDrainingE;
         case 14:
            if (start) {
               return SRPConfigSystems.disloNextPhaseLS;
            }

            return SRPConfigSystems.disloNextPhaseLE;
         case 15:
            if (start) {
               return SRPConfigSystems.disloGrowlNoiseS;
            }

            return SRPConfigSystems.disloGrowlNoiseE;
         case 16:
            if (start) {
               return SRPConfigSystems.disloWalkNoiseS;
            }

            return SRPConfigSystems.disloWalkNoiseE;
         case 17:
            if (start) {
               return SRPConfigSystems.disloShieldFoodS;
            }

            return SRPConfigSystems.disloShieldFoodE;
         case 18:
            if (start) {
               return SRPConfigSystems.disloLootXpCancS;
            }

            return SRPConfigSystems.disloLootXpCancE;
         case 19:
            if (start) {
               return SRPConfigSystems.disloKillcountIncS;
            }

            return SRPConfigSystems.disloKillcountIncE;
         case 20:
            if (start) {
               return SRPConfigSystems.disloGiveBodiesS;
            }

            return SRPConfigSystems.disloGiveBodiesE;
         case 21:
            if (start) {
               return SRPConfigSystems.disloBurningDeathS;
            }

            return SRPConfigSystems.disloBurningDeathE;
         case 22:
            if (start) {
               return SRPConfigSystems.disloSameVersionDyeingS;
            }

            return SRPConfigSystems.disloSameVersionDyeingE;
         case 23:
            if (start) {
               return SRPConfigSystems.disloColonyNoLimitS;
            }

            return SRPConfigSystems.disloColonyNoLimitE;
         case 24:
            if (start) {
               return SRPConfigSystems.disloNexusGrowthS;
            }

            return SRPConfigSystems.disloNexusGrowthE;
         case 25:
            if (start) {
               return SRPConfigSystems.disloParasiteBlockS;
            }

            return SRPConfigSystems.disloParasiteBlockE;
         default:
            return "missing dislo message";
      }
   }

   private void startDislo(int id, int position, int code, int time, World world) {
      List<Entity> serverList = world.field_72996_f;

      for (int x = 0; x < serverList.size(); x++) {
         boolean flag = serverList.get(x) instanceof EntityParasiteBase;
         switch (position) {
            case 2:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberTwo = true;
               }
               break;
            case 3:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberThree = true;
               }
               break;
            case 4:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberFour = true;
               }
               break;
            case 5:
               if (flag) {
                  serverList.get(x).func_70097_a(DamageSource.field_76380_i, 10000.0F);
               }
               break;
            case 6:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSix = true;
               }
               break;
            case 7:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSeven = true;
               }
               break;
            case 8:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberEight = true;
               }
               break;
            case 9:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberNine = true;
               }
               break;
            case 10:
            case 12:
            case 13:
            case 14:
            default:
               return;
            case 11:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberEleven = time * 20 + 50;
               }
               break;
            case 15:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberFifteen = time * 20 + 50;
                  ((EntityParasiteBase)serverList.get(x)).setDislo15(true);
               }
               break;
            case 16:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSixteen = time * 20 + 50;
                  ((EntityParasiteBase)serverList.get(x)).distanceWalkedOnStepModifiedDislo = serverList.get(x).field_82151_R;
               }
               break;
            case 17:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSeventeen = time * 20 + 50;
               }
               break;
            case 18:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberEighteen = true;
               }
               break;
            case 19:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberNineteen = time * 20 + 50;
               }
               break;
            case 20:
               if (flag) {
                  if (serverList.get(x) instanceof EntityInhooM) {
                     ((EntityInhooM)serverList.get(x)).disloNumberTwenty = true;
                  }

                  if (serverList.get(x) instanceof EntityInhooS) {
                     ((EntityInhooS)serverList.get(x)).disloNumberTwenty = true;
                  }
               }
               break;
            case 21:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberTwentyone = true;
               }
               break;
            case 22:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberTwentytwo = true;
               }
         }
      }
   }

   private void midDislo(int id, int position, int code, World world) {
      switch (position) {
         case 12:
            List<Entity> serverList39 = world.field_72996_f;

            for (int x = 0; x < serverList39.size(); x++) {
               if (serverList39.get(x) instanceof EntityLivingBase && !(serverList39.get(x) instanceof EntityParasiteBase)) {
                  serverList39.get(x).func_70097_a(DamageSource.field_82727_n, (float)code * SRPConfigSystems.disloSeconds);
               }
            }
            break;
         case 13:
            for (EntityPlayer pla : world.field_73010_i) {
               pla.func_71020_j((float)code * SRPConfigSystems.disloSeconds);
            }
      }
   }

   private void endDislo(int position, World world) {
      List<Entity> serverList = world.field_72996_f;

      for (int x = 0; x < serverList.size(); x++) {
         boolean flag = serverList.get(x) instanceof EntityParasiteBase;
         switch (position) {
            case 2:
               ParasiteEventEntity.disloNumber2(world);
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberTwo = false;
               }
               break;
            case 3:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberThree = false;
               }
               break;
            case 4:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberFour = false;
               }
               break;
            case 5:
               ParasiteEventEntity.disloNumber5(null, world);
               break;
            case 6:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSix = false;
               }
               break;
            case 7:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSeven = false;
               }
               break;
            case 8:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberEight = false;
               }
               break;
            case 9:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberNine = false;
               }
               break;
            case 10:
            case 12:
            case 13:
            case 14:
            default:
               return;
            case 11:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberEleven = 0;
               }
               break;
            case 15:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberFifteen = 0;
                  ((EntityParasiteBase)serverList.get(x)).setDislo15(false);
               }
               break;
            case 16:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSixteen = 0;
               }
               break;
            case 17:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberSeventeen = 0;
               }
               break;
            case 18:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberEighteen = false;
               }
               break;
            case 19:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberNineteen = 0;
               }
               break;
            case 20:
               if (flag) {
                  if (serverList.get(x) instanceof EntityInhooM) {
                     ((EntityInhooM)serverList.get(x)).disloNumberTwenty = false;
                  }

                  if (serverList.get(x) instanceof EntityInhooS) {
                     ((EntityInhooS)serverList.get(x)).disloNumberTwenty = false;
                  }
               }
               break;
            case 21:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberTwentyone = false;
               }
               break;
            case 22:
               if (flag) {
                  ((EntityParasiteBase)serverList.get(x)).disloNumberTwentytwo = false;
               }
         }
      }
   }

   public void setGeneration(byte in, int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            this.dimGeneration.set(i, in);
         }
      }

      this.func_76185_a();
   }

   public byte getGeneration(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimGeneration.get(i);
         }
      }

      addDim(id);
      return this.getGeneration(id);
   }

   public void setGenerationTime(int in, int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            this.dimGenerationTime.set(i, in);
         }
      }

      this.func_76185_a();
   }

   public int getGenerationTime(int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            return this.dimGenerationTime.get(i);
         }
      }

      addDim(id);
      return this.getGenerationTime(id);
   }

   public int getGenerationNeededTime(World worldIn, int id) {
      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            int currentWT = (int)worldIn.func_82737_E();
            int timer = currentWT - this.dimGenerationTime.get(i);
            int needed = this.getGenerationNeededTime(this.dimGeneration.get(i));
            if (!this.generationPhaseNeeded(this.dimGeneration.get(i), i)) {
               needed = (int)(needed * SRPConfigSystems.generationPhasePenalty);
            }

            return Math.max(needed - timer, 0);
         }
      }

      return 0;
   }

   public boolean checkGeneration(int id, World worldIn) {
      boolean change = true;

      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            change = false;
            if (this.dimGeneration.get(i) == 5) {
               return false;
            }

            int currentWT = (int)worldIn.func_82737_E();
            int timer = currentWT - this.dimGenerationTime.get(i);
            int needed = this.getGenerationNeededTime(this.dimGeneration.get(i));
            if (!this.generationPhaseNeeded(this.dimGeneration.get(i), i)) {
               needed = (int)(needed * SRPConfigSystems.generationPhasePenalty);
            }

            if (timer > needed) {
               byte gene = (byte)(this.dimGeneration.get(i) + 1);
               this.dimGeneration.set(i, gene);
               this.dimGenerationTime.set(i, currentWT);
            }
         }
      }

      if (change) {
         addDim(id);
      }

      this.func_76185_a();
      return true;
   }

   private boolean generationPhaseNeeded(byte gene, int id) {
      switch (gene) {
         case 0:
            for (int ixxxx = 0; ixxxx < SRPConfigSystems.generationPhases1.length; ixxxx++) {
               if (SRPConfigSystems.generationPhases1[ixxxx] == this.dimEPevolution.get(id)) {
                  return true;
               }
            }
            break;
         case 1:
            for (int ixxx = 0; ixxx < SRPConfigSystems.generationPhases2.length; ixxx++) {
               if (SRPConfigSystems.generationPhases2[ixxx] == this.dimEPevolution.get(id)) {
                  return true;
               }
            }
            break;
         case 2:
            for (int ixx = 0; ixx < SRPConfigSystems.generationPhases3.length; ixx++) {
               if (SRPConfigSystems.generationPhases3[ixx] == this.dimEPevolution.get(id)) {
                  return true;
               }
            }
            break;
         case 3:
            for (int ix = 0; ix < SRPConfigSystems.generationPhases4.length; ix++) {
               if (SRPConfigSystems.generationPhases4[ix] == this.dimEPevolution.get(id)) {
                  return true;
               }
            }
            break;
         case 4:
            for (int i = 0; i < SRPConfigSystems.generationPhases5.length; i++) {
               if (SRPConfigSystems.generationPhases5[i] == this.dimEPevolution.get(id)) {
                  return true;
               }
            }
      }

      return false;
   }

   private int getGenerationNeededTime(byte in) {
      double bonus = 1.0;
      switch (this.choice) {
         case 0:
            bonus *= 0.5;
         case 1:
         default:
            break;
         case 2:
            bonus *= 3.0;
            break;
         case 3:
            bonus *= 10.0;
      }

      if (bonus <= 0.0) {
         bonus = 1.0;
      }

      switch (in) {
         case 0:
            return (int)Math.max(1L, Math.round(SRPConfigSystems.generationTime1 / bonus));
         case 1:
            return (int)Math.max(1L, Math.round(SRPConfigSystems.generationTime2 / bonus));
         case 2:
            return (int)Math.max(1L, Math.round(SRPConfigSystems.generationTime3 / bonus));
         case 3:
            return (int)Math.max(1L, Math.round(SRPConfigSystems.generationTime4 / bonus));
         case 4:
            return (int)Math.max(1L, Math.round(SRPConfigSystems.generationTime5 / bonus));
         default:
            return 0;
      }
   }

   public boolean[] getGeneModi(int id) {
      boolean[] loot = new boolean[]{true};

      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            switch (this.dimGeneration.get(i)) {
               case 0:
                  return new boolean[]{
                     SRPConfigSystems.generationMiniDamage0,
                     SRPConfigSystems.generationDamageCap0,
                     SRPConfigSystems.generationLookWalls0,
                     SRPConfigSystems.generationSprinting0,
                     SRPConfigSystems.generationWaterLeap0,
                     SRPConfigSystems.generationSpecialM0,
                     SRPConfigSystems.generationAdaptation0,
                     SRPConfigSystems.generationBlockSearch0,
                     SRPConfigSystems.generationResidue0,
                     SRPConfigSystems.generationOrbbox0
                  };
               case 1:
                  return new boolean[]{
                     SRPConfigSystems.generationMiniDamage1,
                     SRPConfigSystems.generationDamageCap1,
                     SRPConfigSystems.generationLookWalls1,
                     SRPConfigSystems.generationSprinting1,
                     SRPConfigSystems.generationWaterLeap1,
                     SRPConfigSystems.generationSpecialM1,
                     SRPConfigSystems.generationAdaptation1,
                     SRPConfigSystems.generationBlockSearch1,
                     SRPConfigSystems.generationResidue1,
                     SRPConfigSystems.generationOrbbox1
                  };
               case 2:
                  return new boolean[]{
                     SRPConfigSystems.generationMiniDamage2,
                     SRPConfigSystems.generationDamageCap2,
                     SRPConfigSystems.generationLookWalls2,
                     SRPConfigSystems.generationSprinting2,
                     SRPConfigSystems.generationWaterLeap2,
                     SRPConfigSystems.generationSpecialM2,
                     SRPConfigSystems.generationAdaptation2,
                     SRPConfigSystems.generationBlockSearch2,
                     SRPConfigSystems.generationResidue2,
                     SRPConfigSystems.generationOrbbox2
                  };
               case 3:
                  return new boolean[]{
                     SRPConfigSystems.generationMiniDamage3,
                     SRPConfigSystems.generationDamageCap3,
                     SRPConfigSystems.generationLookWalls3,
                     SRPConfigSystems.generationSprinting3,
                     SRPConfigSystems.generationWaterLeap3,
                     SRPConfigSystems.generationSpecialM3,
                     SRPConfigSystems.generationAdaptation3,
                     SRPConfigSystems.generationBlockSearch3,
                     SRPConfigSystems.generationResidue3,
                     SRPConfigSystems.generationOrbbox3
                  };
               case 4:
                  return new boolean[]{
                     SRPConfigSystems.generationMiniDamage4,
                     SRPConfigSystems.generationDamageCap4,
                     SRPConfigSystems.generationLookWalls4,
                     SRPConfigSystems.generationSprinting4,
                     SRPConfigSystems.generationWaterLeap4,
                     SRPConfigSystems.generationSpecialM4,
                     SRPConfigSystems.generationAdaptation4,
                     SRPConfigSystems.generationBlockSearch4,
                     SRPConfigSystems.generationResidue4,
                     SRPConfigSystems.generationOrbbox4
                  };
               case 5:
                  return new boolean[]{
                     SRPConfigSystems.generationMiniDamage5,
                     SRPConfigSystems.generationDamageCap5,
                     SRPConfigSystems.generationLookWalls5,
                     SRPConfigSystems.generationSprinting5,
                     SRPConfigSystems.generationWaterLeap5,
                     SRPConfigSystems.generationSpecialM5,
                     SRPConfigSystems.generationAdaptation5,
                     SRPConfigSystems.generationBlockSearch5,
                     SRPConfigSystems.generationResidue5,
                     SRPConfigSystems.generationOrbbox5
                  };
               default:
                  return new boolean[]{true, true, true, true, true, true, true, true, true, true};
            }
         }
      }

      return loot;
   }

   public float[] getGeneModi2(int id) {
      float[] loot = new float[]{0.0F};

      for (int i = 0; i < this.dimEPid.size(); i++) {
         if (this.dimEPid.get(i) == id) {
            switch (this.dimGeneration.get(i)) {
               case 0:
                  return new float[]{SRPConfigSystems.generationPoisonHeal0, SRPConfigSystems.generationMobHealing0, SRPConfigSystems.generationAttackSpeed0};
               case 1:
                  return new float[]{SRPConfigSystems.generationPoisonHeal1, SRPConfigSystems.generationMobHealing1, SRPConfigSystems.generationAttackSpeed1};
               case 2:
                  return new float[]{SRPConfigSystems.generationPoisonHeal2, SRPConfigSystems.generationMobHealing2, SRPConfigSystems.generationAttackSpeed2};
               case 3:
                  return new float[]{SRPConfigSystems.generationPoisonHeal3, SRPConfigSystems.generationMobHealing3, SRPConfigSystems.generationAttackSpeed3};
               case 4:
                  return new float[]{SRPConfigSystems.generationPoisonHeal4, SRPConfigSystems.generationMobHealing4, SRPConfigSystems.generationAttackSpeed4};
               case 5:
                  return new float[]{SRPConfigSystems.generationPoisonHeal5, SRPConfigSystems.generationMobHealing5, SRPConfigSystems.generationAttackSpeed5};
               default:
                  return new float[]{2.5F, 3.0F, 0.5F};
            }
         }
      }

      return loot;
   }

   public int[] getDeveDimsPhases() {
      int points = 0;
      int dims = 0;

      for (int i = 0; i < this.dimEPevolution.size(); i++) {
         if (this.dimEPevolution.get(i) > 0) {
            points += this.dimEPevolution.get(i);
            dims++;
         }
      }

      return new int[]{points, dims};
   }

   public int getDeveLevel() {
      if (falseLevel != 0) {
         return falseLevel;
      } else {
         int[] check = this.getDeveDimsPhases();
         int value = check[0];
         int dims = check[1];
         if (value >= SRPConfigSystems.devePointsFour && dims >= SRPConfigSystems.deveMiniDimsFour) {
            return 4;
         } else if (value >= SRPConfigSystems.devePointsThree && dims >= SRPConfigSystems.deveMiniDimsThree) {
            return 3;
         } else if (value >= SRPConfigSystems.devePointsTwo && dims >= SRPConfigSystems.deveMiniDimsTwo) {
            return 2;
         } else {
            return value >= SRPConfigSystems.devePointsOne && dims >= SRPConfigSystems.deveMiniDimsOne ? 1 : 0;
         }
      }
   }

   public ArrayList<Integer> getColonies(String i) {
      if (i.equals("d")) {
         return this.dimEPid;
      } else if (i.equals("k")) {
         return this.dimEPtotalKills;
      } else if (i.equals("p")) {
         ArrayList<Integer> eee = new ArrayList<>();

         for (int b : this.dimEPevolution) {
            eee.add(b);
         }

         return eee;
      } else if (!i.equals("g")) {
         return null;
      } else {
         ArrayList<Integer> eee = new ArrayList<>();

         for (int b : this.dimGeneration) {
            eee.add(b);
         }

         return eee;
      }
   }
}
