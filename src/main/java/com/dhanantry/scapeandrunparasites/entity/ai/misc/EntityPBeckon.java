package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteNexusProtection2;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityPBeckon extends EntityPStationaryArchitect {
   int lifeLeftBeckon = -1000;

   public EntityPBeckon(World worldIn) {
      super(worldIn);
      this.setScentHPMultiplier(1.0F);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K && this.lifeLeftBeckon > 0) {
         this.lifeLeftBeckon--;
         if (this.lifeLeftBeckon <= 0 && this.lifeLeftBeckon > -500) {
            this.func_70097_a(DamageSource.field_76380_i, 5000000.0F);
         }
      }
   }

   public void setLifeB(int in) {
      this.lifeLeftBeckon = in;
   }

   public int getLifeB() {
      return this.lifeLeftBeckon;
   }

   @Override
   public void generateStructure() {
      if (SRPConfig.nexusStructures) {
         WorldGenParasiteNexusProtection2 p1 = new WorldGenParasiteNexusProtection2(false, 1);
         p1.func_180709_b(this.field_70170_p, new Random(), this.func_180425_c());
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74768_a("beckonlifeleft", this.lifeLeftBeckon);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("beckonlifeleft", 99)) {
         this.lifeLeftBeckon = compound.func_74762_e("beckonlifeleft");
      }
   }
}
