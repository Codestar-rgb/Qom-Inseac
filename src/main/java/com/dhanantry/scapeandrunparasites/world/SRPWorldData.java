package com.dhanantry.scapeandrunparasites.world;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockBiomeCore;
import com.dhanantry.scapeandrunparasites.block.BlockColonyCore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import org.apache.logging.log4j.Level;

public class SRPWorldData extends WorldSavedData {
   private static final String DATA_NAME = "srparasites_data";
   private static SRPWorldData instance;
   private ArrayList<Integer> nodeX = new ArrayList<>();
   private ArrayList<Integer> nodeY = new ArrayList<>();
   private ArrayList<Integer> nodeZ = new ArrayList<>();
   private ArrayList<Integer> nodeA = new ArrayList<>();
   private ArrayList<Byte> nodeT = new ArrayList<>();
   private ArrayList<Integer> originX = new ArrayList<>();
   private ArrayList<Integer> originY = new ArrayList<>();
   private ArrayList<Integer> originZ = new ArrayList<>();
   private ArrayList<Integer> originA = new ArrayList<>();
   private ArrayList<Integer> originH = new ArrayList<>();
   private ArrayList<Integer> colonyX = new ArrayList<>();
   private ArrayList<Integer> colonyY = new ArrayList<>();
   private ArrayList<Integer> colonyZ = new ArrayList<>();
   private ArrayList<Integer> colonyA = new ArrayList<>();
   private ArrayList<Integer> resistanceI = new ArrayList<>();
   private ArrayList<String> resistanceS = new ArrayList<>();
   private boolean dimMeteor;
   private static SRPWorldData clientInstance = null;

   public SRPWorldData() {
      super("srparasites_data");
   }

   public SRPWorldData(String name) {
      super(name);
   }

   public static SRPWorldData get(World world) {
      if (world == null) {
         return null;
      } else if (world.field_72995_K) {
         return clientInstance;
      } else {
         MapStorage storage = world.getPerWorldStorage();
         instance = (SRPWorldData)storage.func_75742_a(SRPWorldData.class, "srparasites_data");
         if (instance == null) {
            instance = create(world, storage);
         } else {
            instance.updateDays(world);
         }

         return instance;
      }
   }

   private static SRPWorldData create(World world, MapStorage storage) {
      System.out.println("----creating world data data------------------ ");
      instance = new SRPWorldData();
      storage.func_75745_a("srparasites_data", instance);
      boolean flag = true;

      for (int i : SRPConfigWorld.meteorBlacklistDims) {
         if (i == world.field_73011_w.getDimension()) {
            flag = false;
            break;
         }
      }

      if (flag) {
         instance.setTriggerMet(SRPWorldEntitySpawner.triggerSPAWNING || SRPConfigWorld.meteorActive);
      } else {
         instance.setTriggerMet(false);
      }

      instance.func_76185_a();
      return instance;
   }

   public void resetInstance(World world) {
      MapStorage storage = world.getPerWorldStorage();
      instance = create(world, storage);
   }

   public void func_76184_a(NBTTagCompound compound) {
      if (compound.func_74764_b("srpmeteor")) {
         this.dimMeteor = compound.func_74767_n("srpmeteor");
      }

      if (compound.func_74764_b("srpnodescoordsx")) {
         NBTTagList tagListX = compound.func_150295_c("srpnodescoordsx", 10);
         NBTTagList tagListY = compound.func_150295_c("srpnodescoordsy", 10);
         NBTTagList tagListZ = compound.func_150295_c("srpnodescoordsz", 10);
         NBTTagList tagListA = compound.func_150295_c("srpnodesages", 10);
         NBTTagList tagListT = compound.func_150295_c("srpnodestypes", 10);
         if (tagListX.func_74745_c() == tagListY.func_74745_c()
            && tagListX.func_74745_c() == tagListZ.func_74745_c()
            && tagListX.func_74745_c() == tagListA.func_74745_c()
            && tagListX.func_74745_c() == tagListT.func_74745_c()) {
            for (int i = 0; i < tagListX.func_74745_c(); i++) {
               NBTTagCompound tagX = tagListX.func_150305_b(i);
               int coordx = tagX.func_74762_e("nodex" + i);
               this.nodeX.add(i, coordx);
               NBTTagCompound tagY = tagListY.func_150305_b(i);
               int coordy = tagY.func_74762_e("nodey" + i);
               this.nodeY.add(i, coordy);
               NBTTagCompound tagZ = tagListZ.func_150305_b(i);
               int coordz = tagZ.func_74762_e("nodez" + i);
               this.nodeZ.add(i, coordz);
               NBTTagCompound tagA = tagListA.func_150305_b(i);
               int age = tagA.func_74762_e("nodea" + i);
               this.nodeA.add(i, age);
               NBTTagCompound tagT = tagListT.func_150305_b(i);
               int type = tagT.func_74762_e("nodet" + i);
               this.nodeT.add(i, (byte)type);
            }
         } else {
            SRPMain.logger.log(Level.ERROR, "Problem while reading nodes coords");
         }
      }

      if (compound.func_74764_b("srporiginscoordsx")) {
         NBTTagList tagListX = compound.func_150295_c("srporiginscoordsx", 10);
         NBTTagList tagListY = compound.func_150295_c("srporiginscoordsy", 10);
         NBTTagList tagListZ = compound.func_150295_c("srporiginscoordsz", 10);
         NBTTagList tagListA = compound.func_150295_c("srporiginsareas", 10);
         NBTTagList tagListT = compound.func_150295_c("srporiginshealths", 10);
         if (tagListX.func_74745_c() == tagListY.func_74745_c()
            && tagListX.func_74745_c() == tagListZ.func_74745_c()
            && tagListX.func_74745_c() == tagListA.func_74745_c()
            && tagListX.func_74745_c() == tagListT.func_74745_c()) {
            for (int i = 0; i < tagListX.func_74745_c(); i++) {
               NBTTagCompound tagX = tagListX.func_150305_b(i);
               int coordx = tagX.func_74762_e("originx" + i);
               this.originX.add(i, coordx);
               NBTTagCompound tagY = tagListY.func_150305_b(i);
               int coordy = tagY.func_74762_e("originy" + i);
               this.originY.add(i, coordy);
               NBTTagCompound tagZ = tagListZ.func_150305_b(i);
               int coordz = tagZ.func_74762_e("originz" + i);
               this.originZ.add(i, coordz);
               NBTTagCompound tagA = tagListA.func_150305_b(i);
               int age = tagA.func_74762_e("origina" + i);
               this.originA.add(i, age);
               NBTTagCompound tagT = tagListT.func_150305_b(i);
               int type = tagT.func_74762_e("originh" + i);
               this.originH.add(i, type);
            }
         } else {
            SRPMain.logger.log(Level.ERROR, "Problem while reading origins coords");
         }
      }

      if (compound.func_74764_b("srpcoloniescoordsx")) {
         NBTTagList tagListX = compound.func_150295_c("srpcoloniescoordsx", 10);
         NBTTagList tagListY = compound.func_150295_c("srpcoloniescoordsy", 10);
         NBTTagList tagListZ = compound.func_150295_c("srpcoloniescoordsz", 10);
         NBTTagList tagListA = compound.func_150295_c("srpcoloniesages", 10);
         if (tagListX.func_74745_c() == tagListY.func_74745_c()
            && tagListX.func_74745_c() == tagListZ.func_74745_c()
            && tagListX.func_74745_c() == tagListA.func_74745_c()) {
            for (int i = 0; i < tagListX.func_74745_c(); i++) {
               NBTTagCompound tagX = tagListX.func_150305_b(i);
               int coordx = tagX.func_74762_e("colonyx" + i);
               this.colonyX.add(i, coordx);
               NBTTagCompound tagY = tagListY.func_150305_b(i);
               int coordy = tagY.func_74762_e("colonyy" + i);
               this.colonyY.add(i, coordy);
               NBTTagCompound tagZ = tagListZ.func_150305_b(i);
               int coordz = tagZ.func_74762_e("colonyz" + i);
               this.colonyZ.add(i, coordz);
               NBTTagCompound tagA = tagListA.func_150305_b(i);
               int age = tagA.func_74762_e("colonya" + i);
               this.colonyA.add(i, age);
            }
         } else {
            SRPMain.logger.log(Level.ERROR, "Problem while reading colonies coords");
         }
      }

      if (compound.func_74764_b("srpcolonyresistances")) {
         NBTTagList tagListX = compound.func_150295_c("srpcolonyresistancei", 10);
         NBTTagList tagListY = compound.func_150295_c("srpcolonyresistances", 10);
         if (tagListX.func_74745_c() != tagListY.func_74745_c()) {
            SRPMain.logger.log(Level.ERROR, "Problem while reading resistance");
         } else {
            for (int i = 0; i < tagListX.func_74745_c(); i++) {
               NBTTagCompound tagX = tagListX.func_150305_b(i);
               int coordx = tagX.func_74762_e("resistance" + i);
               this.resistanceI.add(i, coordx);
               NBTTagCompound tagY = tagListY.func_150305_b(i);
               String coordy = tagY.func_74779_i("resistance" + i);
               this.resistanceS.add(i, coordy);
            }
         }
      }
   }

   public NBTTagCompound func_189551_b(NBTTagCompound compound) {
      compound.func_74757_a("srpmeteor", SRPWorldEntitySpawner.triggerSPAWNING);
      if (this.nodeX.size() == this.nodeY.size()
         && this.nodeX.size() == this.nodeZ.size()
         && this.nodeX.size() == this.nodeA.size()
         && this.nodeX.size() == this.nodeT.size()) {
         NBTTagList tagListX = new NBTTagList();
         NBTTagList tagListY = new NBTTagList();
         NBTTagList tagListZ = new NBTTagList();
         NBTTagList tagListA = new NBTTagList();
         NBTTagList tagListT = new NBTTagList();

         for (int i = 0; i < this.nodeX.size(); i++) {
            int coordx = this.nodeX.get(i);
            int coordy = this.nodeY.get(i);
            int coordz = this.nodeZ.get(i);
            int age = this.nodeA.get(i);
            int type = this.nodeT.get(i);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74768_a("nodex" + i, coordx);
            tagListX.func_74742_a(tagX);
            NBTTagCompound tagY = new NBTTagCompound();
            tagY.func_74768_a("nodey" + i, coordy);
            tagListY.func_74742_a(tagY);
            NBTTagCompound tagZ = new NBTTagCompound();
            tagZ.func_74768_a("nodez" + i, coordz);
            tagListZ.func_74742_a(tagZ);
            NBTTagCompound tagA = new NBTTagCompound();
            tagA.func_74768_a("nodea" + i, age);
            tagListA.func_74742_a(tagA);
            NBTTagCompound tagT = new NBTTagCompound();
            tagT.func_74768_a("nodet" + i, type);
            tagListT.func_74742_a(tagT);
         }

         compound.func_74782_a("srpnodescoordsx", tagListX);
         compound.func_74782_a("srpnodescoordsy", tagListY);
         compound.func_74782_a("srpnodescoordsz", tagListZ);
         compound.func_74782_a("srpnodesages", tagListA);
         compound.func_74782_a("srpnodestypes", tagListT);
      } else {
         SRPMain.logger.log(Level.ERROR, "Problem while writing nodes coords");
      }

      if (this.originX.size() == this.originY.size()
         && this.originX.size() == this.originZ.size()
         && this.originX.size() == this.originA.size()
         && this.originX.size() == this.originH.size()) {
         NBTTagList tagListX = new NBTTagList();
         NBTTagList tagListY = new NBTTagList();
         NBTTagList tagListZ = new NBTTagList();
         NBTTagList tagListA = new NBTTagList();
         NBTTagList tagListT = new NBTTagList();

         for (int i = 0; i < this.originX.size(); i++) {
            int coordx = this.originX.get(i);
            int coordy = this.originY.get(i);
            int coordz = this.originZ.get(i);
            int age = this.originA.get(i);
            int type = this.originH.get(i);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74768_a("originx" + i, coordx);
            tagListX.func_74742_a(tagX);
            NBTTagCompound tagY = new NBTTagCompound();
            tagY.func_74768_a("originy" + i, coordy);
            tagListY.func_74742_a(tagY);
            NBTTagCompound tagZ = new NBTTagCompound();
            tagZ.func_74768_a("originz" + i, coordz);
            tagListZ.func_74742_a(tagZ);
            NBTTagCompound tagA = new NBTTagCompound();
            tagA.func_74768_a("origina" + i, age);
            tagListA.func_74742_a(tagA);
            NBTTagCompound tagT = new NBTTagCompound();
            tagT.func_74768_a("originh" + i, type);
            tagListT.func_74742_a(tagT);
         }

         compound.func_74782_a("srporiginscoordsx", tagListX);
         compound.func_74782_a("srporiginscoordsy", tagListY);
         compound.func_74782_a("srporiginscoordsz", tagListZ);
         compound.func_74782_a("srporiginsareas", tagListA);
         compound.func_74782_a("srporiginshealths", tagListT);
      } else {
         SRPMain.logger.log(Level.ERROR, "Problem while writing origins coords");
      }

      if (this.colonyX.size() == this.colonyY.size() && this.colonyX.size() == this.colonyZ.size() && this.colonyX.size() == this.colonyA.size()) {
         NBTTagList tagListX = new NBTTagList();
         NBTTagList tagListY = new NBTTagList();
         NBTTagList tagListZ = new NBTTagList();
         NBTTagList tagListA = new NBTTagList();

         for (int i = 0; i < this.colonyX.size(); i++) {
            int coordx = this.colonyX.get(i);
            int coordy = this.colonyY.get(i);
            int coordz = this.colonyZ.get(i);
            int age = this.colonyA.get(i);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74768_a("colonyx" + i, coordx);
            tagListX.func_74742_a(tagX);
            NBTTagCompound tagY = new NBTTagCompound();
            tagY.func_74768_a("colonyy" + i, coordy);
            tagListY.func_74742_a(tagY);
            NBTTagCompound tagZ = new NBTTagCompound();
            tagZ.func_74768_a("colonyz" + i, coordz);
            tagListZ.func_74742_a(tagZ);
            NBTTagCompound tagA = new NBTTagCompound();
            tagA.func_74768_a("colonya" + i, age);
            tagListA.func_74742_a(tagA);
         }

         compound.func_74782_a("srpcoloniescoordsx", tagListX);
         compound.func_74782_a("srpcoloniescoordsy", tagListY);
         compound.func_74782_a("srpcoloniescoordsz", tagListZ);
         compound.func_74782_a("srpcoloniesages", tagListA);
      } else {
         SRPMain.logger.log(Level.ERROR, "Problem while writing colonies coords");
      }

      if (this.resistanceI.size() != this.resistanceS.size()) {
         SRPMain.logger.log(Level.ERROR, "Problem while writing resistance");
      } else {
         NBTTagList tagListX = new NBTTagList();
         NBTTagList tagListY = new NBTTagList();

         for (int i = 0; i < this.resistanceI.size(); i++) {
            int coordx = this.resistanceI.get(i);
            String coordy = this.resistanceS.get(i);
            NBTTagCompound tagX = new NBTTagCompound();
            tagX.func_74768_a("resistance" + i, coordx);
            tagListX.func_74742_a(tagX);
            NBTTagCompound tagY = new NBTTagCompound();
            tagY.func_74778_a("resistance" + i, coordy);
            tagListY.func_74742_a(tagY);
         }

         compound.func_74782_a("srpcolonyresistancei", tagListX);
         compound.func_74782_a("srpcolonyresistances", tagListY);
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
      this.originX = new ArrayList<>();
      this.originY = new ArrayList<>();
      this.originZ = new ArrayList<>();
      this.originA = new ArrayList<>();
      this.originH = new ArrayList<>();
      this.func_76185_a();
   }

   public ArrayList<Integer> getorigins(String i) {
      if (i.equals("x")) {
         return this.originX;
      } else if (i.equals("y")) {
         return this.originY;
      } else if (i.equals("z")) {
         return this.originZ;
      } else if (i.equals("a")) {
         return this.originA;
      } else {
         return i.equals("h") ? this.originH : null;
      }
   }

   public int setOrigin(World world, int x, int y, int z, int heatlh, int radius) {
      if (!this.canOriginBeMade(new BlockPos(x, y, z))) {
         return 6;
      } else {
         SRPSaveData data = SRPSaveData.get(world, 201);
         if (this.originX.size() >= this.getOriginCap(world, data)) {
            return 7;
         } else {
            int canAdd = 1;

            for (int i = 0; i < this.originX.size(); i++) {
               if (this.originX.get(i) == x && this.originY.get(i) == y && this.originZ.get(i) == z) {
                  canAdd = 10;
               }
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
      }
   }

   private int getOriginCap(World in, SRPSaveData data) {
      if (!SRPConfigSystems.useEvolution) {
         return SRPConfigWorld.originCap;
      } else {
         switch (data.getEvolutionPhase(in.field_73011_w.getDimension())) {
            case -1:
               return SRPConfigSystems.phaseOriginMinusOne;
            case 0:
               return SRPConfigSystems.phaseOriginZero;
            case 1:
               return SRPConfigSystems.phaseOriginOne;
            case 2:
               return SRPConfigSystems.phaseOriginTwo;
            case 3:
               return SRPConfigSystems.phaseOriginThree;
            case 4:
               return SRPConfigSystems.phaseOriginFour;
            case 5:
               return SRPConfigSystems.phaseOriginFive;
            case 6:
               return SRPConfigSystems.phaseOriginSix;
            case 7:
               return SRPConfigSystems.phaseOriginSeven;
            case 8:
               return SRPConfigSystems.phaseOriginEight;
            case 9:
               return SRPConfigSystems.phaseOriginNine;
            case 10:
               return SRPConfigSystems.phaseOriginTen;
            default:
               return 0;
         }
      }
   }

   public boolean canOriginBeMade(BlockPos pos) {
      int distance = SRPConfigWorld.originMinimumDistance * SRPConfigWorld.originMinimumDistance;

      for (int i = 0; i < this.originX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.originX.get(i).intValue(),
            this.originY.get(i).intValue(),
            this.originZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         if (distanceSQ <= distance) {
            return false;
         }
      }

      return true;
   }

   public boolean removeOrigin(int x, int y, int z, World worldIn) {
      for (int i = 0; i < this.originX.size(); i++) {
         if (this.originX.get(i) == x && this.originY.get(i) == y && this.originZ.get(i) == z) {
            this.originX.remove(i);
            this.originY.remove(i);
            this.originZ.remove(i);
            this.originA.remove(i);
            this.originH.remove(i);
            this.func_76185_a();
            this.setEIVHealthToData(worldIn, SRPSaveData.get(worldIn, 260));
            return true;
         }
      }

      return false;
   }

   public int nearestInfectionValue(BlockPos pos, boolean health) {
      int heart = this.nearestInfectionIndex(pos);
      if (heart > -1) {
         return health ? this.originH.get(heart) : this.originA.get(heart);
      } else {
         return heart;
      }
   }

   public int nearestInfectionValueArea(BlockPos pos, boolean health) {
      int heart = this.nearestInfectionPositionIndex(true, pos);
      if (heart > -1) {
         return health ? this.originH.get(heart) : this.originA.get(heart);
      } else {
         return heart;
      }
   }

   public BlockPos nearestInfectionPosition(Boolean outside, BlockPos pos) {
      BlockPos heart = null;
      double distance = -1.0;

      for (int i = 0; i < this.originX.size(); i++) {
         long distanceSQ = this.getDistanceSQ(
            this.originX.get(i).intValue(),
            this.originY.get(i).intValue(),
            this.originZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         long ageDistance = this.originA.get(i).intValue();
         ageDistance *= ageDistance;
         if (distanceSQ <= ageDistance || outside && distance == -1.0) {
            if (distance == -1.0) {
               heart = new BlockPos(this.originX.get(i), this.originY.get(i), this.originZ.get(i));
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               heart = new BlockPos(this.originX.get(i), this.originY.get(i), this.originZ.get(i));
               distance = distanceSQ;
            }
         }
      }

      return heart;
   }

   public int nearestInfectionPositionIndex(Boolean outside, BlockPos pos) {
      int heart = -1;
      double distance = -1.0;

      for (int i = 0; i < this.originX.size(); i++) {
         long distanceSQ = this.getDistanceSQ(
            this.originX.get(i).intValue(),
            this.originY.get(i).intValue(),
            this.originZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         long ageDistance = this.originA.get(i).intValue();
         ageDistance *= ageDistance;
         if (distanceSQ <= ageDistance || outside && distance == -1.0) {
            if (distance == -1.0) {
               heart = i;
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               heart = i;
               distance = distanceSQ;
            }
         }
      }

      return heart;
   }

   public int nearestInfectionIndex(BlockPos pos) {
      int heart = -1;
      double distance = -1.0;

      for (int i = 0; i < this.originX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.originX.get(i).intValue(),
            this.originY.get(i).intValue(),
            this.originZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         int ageDistance = this.originA.get(i);
         ageDistance *= ageDistance;
         if (distanceSQ <= ageDistance) {
            if (distance == -1.0) {
               heart = i;
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               heart = i;
               distance = distanceSQ;
            }
         }
      }

      return heart;
   }

   public void updateOriginValues(World world, int updates, SRPSaveData data) {
      int dim = world.field_73011_w.getDimension();
      int phase = data.getEvolutionPhase(dim);

      while (updates > 0) {
         for (int i = 0; i < this.originA.size(); i++) {
            int size = this.originA.get(i);
            int health = this.originH.get(i);
            size = (int)(size + size * (SRPConfigWorld.originDailySize + getPhaseSize(phase)));
            health = (int)(health + size * (SRPConfigWorld.originDailyHealth + getPhaseHealth(phase)));
            this.originA.set(i, Math.min(size, SRPConfigWorld.originRadiusCap));
            this.originH.set(i, Math.min(health, SRPConfigWorld.originHealthCap));
         }

         updates--;
      }

      this.func_76185_a();
      this.setEIVHealthToData(world, data);
   }

   public void setEIVHealthToData(World in, SRPSaveData data) {
      int health = 0;
      int area = 0;

      for (int i = 0; i < this.originH.size(); i++) {
         health += this.originH.get(i);
         area += this.originA.get(i);
      }

      data.setEIVHealthArea(in.field_73011_w.getDimension(), health, area);
   }

   public static double getPhaseSize(int in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseOriginBonusSizeOne;
         case 2:
            return SRPConfigSystems.phaseOriginBonusSizeTwo;
         case 3:
            return SRPConfigSystems.phaseOriginBonusSizeThree;
         case 4:
            return SRPConfigSystems.phaseOriginBonusSizeFour;
         case 5:
            return SRPConfigSystems.phaseOriginBonusSizeFive;
         case 6:
            return SRPConfigSystems.phaseOriginBonusSizeSix;
         case 7:
            return SRPConfigSystems.phaseOriginBonusSizeSeven;
         case 8:
            return SRPConfigSystems.phaseOriginBonusSizeEight;
         case 9:
            return SRPConfigSystems.phaseOriginBonusSizeNine;
         case 10:
            return SRPConfigSystems.phaseOriginBonusSizeTen;
         default:
            return 0.0;
      }
   }

   public static double getPhaseHealth(int in) {
      switch (in) {
         case 1:
            return SRPConfigSystems.phaseOriginBonusHealthOne;
         case 2:
            return SRPConfigSystems.phaseOriginBonusHealthTwo;
         case 3:
            return SRPConfigSystems.phaseOriginBonusHealthThree;
         case 4:
            return SRPConfigSystems.phaseOriginBonusHealthFour;
         case 5:
            return SRPConfigSystems.phaseOriginBonusHealthFive;
         case 6:
            return SRPConfigSystems.phaseOriginBonusHealthSix;
         case 7:
            return SRPConfigSystems.phaseOriginBonusHealthSeven;
         case 8:
            return SRPConfigSystems.phaseOriginBonusHealthEight;
         case 9:
            return SRPConfigSystems.phaseOriginBonusHealthNine;
         case 10:
            return SRPConfigSystems.phaseOriginBonusHealthTen;
         default:
            return 0.0;
      }
   }

   public boolean setOriginHealth(World worldIn, BlockPos pos, int amount, boolean plus) {
      int healthID = -1;
      double distance = -1.0;

      for (int i = 0; i < this.originX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.originX.get(i).intValue(),
            this.originY.get(i).intValue(),
            this.originZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         int ageDistance = this.originA.get(i);
         ageDistance *= ageDistance;
         if (distanceSQ <= ageDistance) {
            if (distance == -1.0) {
               healthID = i;
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               healthID = i;
               distance = distanceSQ;
            }
         }
      }

      if (healthID != -1) {
         SRPSaveData data = SRPSaveData.get(worldIn, 260);
         if (plus) {
            if (data.getEvolutionPhase(worldIn.field_73011_w.getDimension()) == -1 && amount > 0) {
               amount = (int)(amount * SRPConfigSystems.phaseOriginMinusOnePenalty);
            }

            int current = this.originH.get(healthID) + amount;
            if (current <= 0) {
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
      } else {
         return false;
      }
   }

   public void clearNodeList() {
      this.nodeX = new ArrayList<>();
      this.nodeY = new ArrayList<>();
      this.nodeZ = new ArrayList<>();
      this.nodeA = new ArrayList<>();
      this.nodeT = new ArrayList<>();
      this.func_76185_a();
   }

   public ArrayList<Integer> getNodes(String i) {
      if (i.equals("x")) {
         return this.nodeX;
      } else if (i.equals("y")) {
         return this.nodeY;
      } else if (i.equals("z")) {
         return this.nodeZ;
      } else if (i.equals("a")) {
         return this.nodeA;
      } else if (!i.equals("t")) {
         return null;
      } else {
         ArrayList<Integer> eee = new ArrayList<>();

         for (int b : this.nodeT) {
            eee.add(b);
         }

         return eee;
      }
   }

   public int setNode(int x, int y, int z, int type) {
      if (!this.canNodeBeMade(new BlockPos(x, y, z))) {
         return 8;
      } else if (this.nodeX.size() >= SRPConfigWorld.maximumNumberNodes) {
         return 9;
      } else {
         int canAdd = 1;

         for (int i = 0; i < this.nodeX.size(); i++) {
            if (this.nodeX.get(i) == x && this.nodeY.get(i) == y && this.nodeZ.get(i) == z) {
               if (this.nodeA.get(i) != 1) {
                  this.removeNode(x, y, z);
                  this.setNode(x, y, z, type);
               }

               canAdd = 10;
            }
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
   }

   public boolean canNodeBeMade(BlockPos pos) {
      int distance = SRPConfigWorld.minimumDistanceBetweenNodes * SRPConfigWorld.minimumDistanceBetweenNodes;

      for (int i = 0; i < this.nodeX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.nodeX.get(i).intValue(),
            this.nodeY.get(i).intValue(),
            this.nodeZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         if (distanceSQ <= distance) {
            return false;
         }
      }

      return true;
   }

   public boolean removeNode(int x, int y, int z) {
      for (int i = 0; i < this.nodeX.size(); i++) {
         if (this.nodeX.get(i) == x && this.nodeY.get(i) == y && this.nodeZ.get(i) == z) {
            this.nodeX.remove(i);
            this.nodeY.remove(i);
            this.nodeZ.remove(i);
            this.nodeA.remove(i);
            this.nodeT.remove(i);
            this.func_76185_a();
            return true;
         }
      }

      return false;
   }

   public int isInRangeOfHeart(BlockPos pos, double distance) {
      distance++;

      for (int i = 0; i < this.nodeX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.nodeX.get(i).intValue(),
            this.nodeY.get(i).intValue(),
            this.nodeZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         if (distanceSQ < distance * distance) {
            return (int)distanceSQ;
         }
      }

      return -1;
   }

   public int nearestHeartAge(BlockPos pos, boolean spread, int currentDay) {
      int heart = -1;
      double distance = -1.0;

      for (int i = 0; i < this.nodeX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.nodeX.get(i).intValue(),
            this.nodeY.get(i).intValue(),
            this.nodeZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         int ageDistance = this.convertDayToAgeNode(this.nodeA.get(i));
         if (spread) {
            ageDistance = this.getDistanceSpreadByAge(ageDistance, true);
         } else {
            ageDistance = this.getDistanceEffectByAge(ageDistance, true);
         }

         if (distanceSQ <= ageDistance) {
            if (distance == -1.0) {
               heart = ageDistance;
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               heart = ageDistance;
               distance = distanceSQ;
            }
         }
      }

      return heart;
   }

   public int nearestHeartType(BlockPos pos, boolean spread, int currentDay) {
      int type = -1;
      double distance = -1.0;

      for (int i = 0; i < this.nodeX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.nodeX.get(i).intValue(),
            this.nodeY.get(i).intValue(),
            this.nodeZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         int ageDistance = this.convertDayToAgeNode(this.nodeA.get(i));
         if (spread) {
            ageDistance = this.getDistanceSpreadByAge(ageDistance, true);
         } else {
            ageDistance = this.getDistanceEffectByAge(ageDistance, true);
         }

         if (distanceSQ <= ageDistance) {
            if (distance == -1.0) {
               type = this.nodeT.get(i);
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               type = this.nodeT.get(i);
               distance = distanceSQ;
            }
         }
      }

      return type;
   }

   public BlockPos nearestHeartAgePosition(BlockPos pos, int currentDay) {
      BlockPos heart = null;
      double distance = -1.0;

      for (int i = 0; i < this.nodeX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.nodeX.get(i).intValue(),
            this.nodeY.get(i).intValue(),
            this.nodeZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         int ageDistance = this.convertDayToAgeNode(this.nodeA.get(i));
         ageDistance = this.getDistanceEffectByAge(ageDistance, true);
         if (distanceSQ <= ageDistance) {
            if (distance == -1.0) {
               heart = new BlockPos(this.nodeX.get(i), this.nodeY.get(i), this.nodeZ.get(i));
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               heart = new BlockPos(this.nodeX.get(i), this.nodeY.get(i), this.nodeZ.get(i));
               distance = distanceSQ;
            }
         }
      }

      return heart;
   }

   private int convertDayToAgeNode(int nodeBirthday) {
      if (nodeBirthday >= SRPConfigWorld.timeNeedeToNodeThree) {
         return 3;
      } else {
         return nodeBirthday >= SRPConfigWorld.timeNeedeToNodeTwo ? 2 : 1;
      }
   }

   public int getDistanceSpreadByAge(int age, boolean sq) {
      switch (age) {
         case 1:
            if (sq) {
               return SRPConfigWorld.nodeRangeSpreadOne * SRPConfigWorld.nodeRangeSpreadOne;
            }

            return SRPConfigWorld.nodeRangeSpreadOne;
         case 2:
            if (sq) {
               return SRPConfigWorld.nodeRangeSpreadTwo * SRPConfigWorld.nodeRangeSpreadTwo;
            }

            return SRPConfigWorld.nodeRangeSpreadTwo;
         case 3:
            if (sq) {
               return SRPConfigWorld.nodeRangeSpreadThree * SRPConfigWorld.nodeRangeSpreadThree;
            }

            return SRPConfigWorld.nodeRangeSpreadThree;
         default:
            return -1;
      }
   }

   public int getDistanceEffectByAge(int age, boolean sq) {
      switch (age) {
         case 1:
            if (sq) {
               return SRPConfigWorld.nodeRangeEffectsOne * SRPConfigWorld.nodeRangeEffectsOne;
            }

            return SRPConfigWorld.nodeRangeEffectsOne;
         case 2:
            if (sq) {
               return SRPConfigWorld.nodeRangeEffectsTwo * SRPConfigWorld.nodeRangeEffectsTwo;
            }

            return SRPConfigWorld.nodeRangeEffectsTwo;
         case 3:
            if (sq) {
               return SRPConfigWorld.nodeRangeEffectsThree * SRPConfigWorld.nodeRangeEffectsThree;
            }

            return SRPConfigWorld.nodeRangeEffectsThree;
         default:
            return -1;
      }
   }

   public void checkHeartExistance(World worldIn) {
      for (int i = this.nodeX.size() - 1; i >= 0; i--) {
         BlockPos pos = new BlockPos(this.nodeX.get(i), this.nodeY.get(i), this.nodeZ.get(i));
         if (worldIn.func_175697_a(pos, 3)) {
            IBlockState state = worldIn.func_180495_p(pos);
            if (state.func_177230_c() != SRPBlocks.BiomeHeart
               || !state.func_177227_a().contains(BlockBiomeCore.ACTIVE)
               || (Integer)state.func_177229_b(BlockBiomeCore.ACTIVE) <= 0) {
               this.nodeX.remove(i);
               this.nodeY.remove(i);
               this.nodeZ.remove(i);
               this.nodeA.remove(i);
               this.nodeT.remove(i);
               this.func_76185_a();
            }
         }
      }
   }

   public int getHeartPocition(BlockPos pos, int currentDay) {
      for (int i = 0; i < this.nodeX.size(); i++) {
         if (this.nodeX.get(i) == pos.func_177958_n() && this.nodeY.get(i) == pos.func_177956_o() && this.nodeZ.get(i) == pos.func_177952_p()) {
            return this.convertDayToAgeNode(this.nodeA.get(i));
         }
      }

      return -1;
   }

   public int totalNodePoints(int currentDay) {
      int points = 0;

      for (int i = 0; i < this.nodeX.size(); i++) {
         int age = this.convertDayToAgeNode(this.nodeA.get(i));
         points += age;
      }

      return points;
   }

   public void clearColonyList() {
      this.colonyX = new ArrayList<>();
      this.colonyY = new ArrayList<>();
      this.colonyZ = new ArrayList<>();
      this.colonyA = new ArrayList<>();
      this.func_76185_a();
   }

   public ArrayList<Integer> getColonies(String i) {
      if (i.equals("x")) {
         return this.colonyX;
      } else if (i.equals("y")) {
         return this.colonyY;
      } else if (i.equals("z")) {
         return this.colonyZ;
      } else {
         return i.equals("a") ? this.colonyA : null;
      }
   }

   public int setColony(int x, int y, int z) {
      if (!this.canColonyBeMade(new BlockPos(x, y, z))) {
         return 6;
      } else if (this.colonyX.size() >= SRPConfigWorld.maximumNumberColonies) {
         return 7;
      } else {
         int canAdd = 1;

         for (int i = 0; i < this.colonyX.size(); i++) {
            if (this.colonyX.get(i) == x && this.colonyY.get(i) == y && this.colonyZ.get(i) == z) {
               if (this.colonyA.get(i) != 1) {
                  this.removeColony(x, y, z);
                  this.setColony(x, y, z);
               }

               canAdd = 10;
            }
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
   }

   public boolean canColonyBeMade(BlockPos pos) {
      int distance = SRPConfigWorld.minimumDistanceBetweenColonies * SRPConfigWorld.minimumDistanceBetweenColonies;

      for (int i = 0; i < this.colonyX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.colonyX.get(i).intValue(),
            this.colonyY.get(i).intValue(),
            this.colonyZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         if (distanceSQ <= distance) {
            return false;
         }
      }

      return true;
   }

   public boolean removeColony(int x, int y, int z) {
      for (int i = 0; i < this.colonyX.size(); i++) {
         if (this.colonyX.get(i) == x && this.colonyY.get(i) == y && this.colonyZ.get(i) == z) {
            this.colonyX.remove(i);
            this.colonyY.remove(i);
            this.colonyZ.remove(i);
            this.colonyA.remove(i);
            this.func_76185_a();
            return true;
         }
      }

      return false;
   }

   public BlockPos nearestColonyPosition(BlockPos pos, boolean effect) {
      BlockPos posColony = null;
      double distance = -1.0;

      for (int i = 0; i < this.colonyX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.colonyX.get(i).intValue(),
            this.colonyY.get(i).intValue(),
            this.colonyZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         int ageDistance = this.convertDayToPointsColony(this.colonyA.get(i));
         ageDistance = this.getColonyDistanceSpreadByPoints(ageDistance, true, effect);
         if (distanceSQ <= ageDistance) {
            if (distance == -1.0) {
               posColony = new BlockPos(this.colonyX.get(i), this.colonyY.get(i), this.colonyZ.get(i));
               distance = distanceSQ;
            } else if (distance <= distanceSQ) {
               posColony = new BlockPos(this.colonyX.get(i), this.colonyY.get(i), this.colonyZ.get(i));
               distance = distanceSQ;
            }
         }
      }

      return posColony;
   }

   private int convertDayToPointsColony(int nodeBirthday) {
      return Math.min(nodeBirthday, SRPConfigWorld.colonyPointCap);
   }

   public int colonunumber() {
      return this.colonyA.size();
   }

   public int getColonyDistanceSpreadByPoints(int points, boolean sq, boolean effect) {
      if (effect) {
         int baseRange = SRPConfigWorld.colonyBaseEffectRadiusValue;
         int a = points / SRPConfigWorld.colonySpreadEffectPoint;
         int b = a * SRPConfigWorld.colonySpreadEffectValue + baseRange;
         return sq ? b * b : b;
      } else {
         int baseRange = SRPConfigWorld.colonyBaseRadiusValue;
         int a = points / SRPConfigWorld.colonySpreadPoint;
         int b = a * SRPConfigWorld.colonySpreadValue + baseRange;
         return sq ? b * b : b;
      }
   }

   public int getColonyDistanceSpreadByPosition(BlockPos pos, boolean sq) {
      int x = pos.func_177958_n();
      int y = pos.func_177956_o();
      int z = pos.func_177952_p();

      for (int i = 0; i < this.colonyX.size(); i++) {
         if (this.colonyX.get(i) == x && this.colonyY.get(i) == y && this.colonyZ.get(i) == z) {
            int ageDistance = this.convertDayToPointsColony(this.colonyA.get(i));
            return this.getColonyDistanceSpreadByPoints(ageDistance, sq, false);
         }
      }

      return 0;
   }

   public BlockPos isInRangeOfColony(BlockPos pos, double distance) {
      distance++;

      for (int i = 0; i < this.nodeX.size(); i++) {
         double distanceSQ = this.getDistanceSQ(
            this.nodeX.get(i).intValue(),
            this.nodeY.get(i).intValue(),
            this.nodeZ.get(i).intValue(),
            pos.func_177958_n(),
            pos.func_177956_o(),
            pos.func_177952_p()
         );
         if (distanceSQ < distance * distance) {
            return new BlockPos(this.nodeX.get(i), this.nodeY.get(i), this.nodeZ.get(i));
         }
      }

      return null;
   }

   public int totalColonyPoints(int currentDay) {
      int points = 0;

      for (int i = 0; i < this.colonyX.size(); i++) {
         int age = this.convertDayToPointsColony(this.colonyA.get(i));
         points += age;
      }

      return Math.min(points, SRPConfigWorld.colonyTotalPointCap);
   }

   public void checkColonyExistance(World worldIn) {
      for (int i = this.colonyX.size() - 1; i >= 0; i--) {
         BlockPos pos = new BlockPos(this.colonyX.get(i), this.colonyY.get(i), this.colonyZ.get(i));
         if (worldIn.func_175697_a(pos, 3)) {
            IBlockState state = worldIn.func_180495_p(pos);
            if (state.func_177230_c() != SRPBlocks.ColonyHeart && state.func_177230_c() != SRPBlocks.ColonyOutpost
               || !state.func_177227_a().contains(BlockColonyCore.ACTIVE)
               || (Integer)state.func_177229_b(BlockColonyCore.ACTIVE) <= 0) {
               this.colonyX.remove(i);
               this.colonyY.remove(i);
               this.colonyZ.remove(i);
               this.colonyA.remove(i);
               this.func_76185_a();
            }
         }
      }
   }

   public void addGlobalResistance(String damage) {
      boolean flag = true;

      for (int i = 0; i < this.resistanceS.size(); i++) {
         if (this.resistanceS.get(i).equals(damage)) {
            int iiii = this.resistanceI.get(i) + 1;
            this.resistanceI.set(i, iiii);
            flag = false;
            break;
         }
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

      for (int i = 0; i < resistanceAAAI.size(); i++) {
         if (resistanceAAAI.get(i) > times) {
            times = resistanceAAAI.get(i);
            atm = resistanceAAAS.get(i);
         }
      }

      return atm;
   }

   public int getMostCommonDamageI() {
      ArrayList<Integer> resistanceAAAI = this.resistanceI;
      int times = 0;

      for (int i = 0; i < resistanceAAAI.size(); i++) {
         if (resistanceAAAI.get(i) > times) {
            times = resistanceAAAI.get(i);
         }
      }

      return times;
   }

   public void resetGlobalAdaptation() {
      this.resistanceI = new ArrayList<>();
      this.resistanceS = new ArrayList<>();
      this.func_76185_a();
   }

   public ArrayList<String> getAdaptationS() {
      return this.resistanceS;
   }

   public ArrayList<Integer> getAdaptationI() {
      return this.resistanceI;
   }

   public void updateDays(World world) {
      SRPSaveData data = SRPSaveData.get(world, 2120);
      int count = data.getUpdateNumber(world.field_73011_w.getDimension());
      if (count != 0) {
         for (int i = 0; i < this.nodeA.size(); i++) {
            int atm = this.nodeA.get(i) + count;
            this.nodeA.set(i, atm);
         }

         for (int i = 0; i < this.colonyA.size(); i++) {
            int atm = this.colonyA.get(i) + count;
            this.colonyA.set(i, atm);
         }

         this.func_76185_a();
         this.updateOriginValues(world, count, data);
      }
   }
}
