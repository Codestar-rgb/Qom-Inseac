package com.dhanantry.scapeandrunparasites.entity.tile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationaryArchitect;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDod;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIV;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityDod extends TileEntity implements ITickable {
   private int ticksSinceSync;
   private int killC;

   public void func_145839_a(NBTTagCompound compound) {
      super.func_145839_a(compound);
      if (compound.func_150297_b("parasitekillc", 3)) {
         this.killC = compound.func_74762_e("parasitekillc");
      }
   }

   public NBTTagCompound func_189515_b(NBTTagCompound compound) {
      super.func_189515_b(compound);
      compound.func_74768_a("parasitekillc", this.killC);
      return compound;
   }

   public void func_73660_a() {
      if (!this.field_145850_b.field_72995_K) {
         if (this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c() == Blocks.field_150350_a) {
            this.killSelfTile();
            return;
         }

         this.ticksSinceSync++;
         if (this.ticksSinceSync % 20 != 0) {
            return;
         }

         double coll = 0.0;
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_174879_c.func_177958_n(),
               this.field_174879_c.func_177956_o(),
               this.field_174879_c.func_177952_p(),
               this.field_174879_c.func_177958_n() + 1,
               this.field_174879_c.func_177956_o() + 1,
               this.field_174879_c.func_177952_p() + 1
            )
            .func_186662_g(16.0);

         for (EntityParasiteBase mob : this.field_145850_b.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
            if (mob.getKillC() > 1.0) {
               coll += mob.getKillC();
               mob.setKillC(0.0);
            }
         }

         if (coll != 0.0) {
            this.killC += (int)coll;
         }

         if (this.killC > SRPConfig.reinforcerNidusValue) {
            assert Blocks.field_150350_a != null;

            int ccc = 0;
            axisalignedbb = new AxisAlignedBB(
                  this.field_174879_c.func_177958_n(),
                  this.field_174879_c.func_177956_o(),
                  this.field_174879_c.func_177952_p(),
                  this.field_174879_c.func_177958_n() + 1,
                  this.field_174879_c.func_177956_o() + 1,
                  this.field_174879_c.func_177952_p() + 1
               )
               .func_186662_g(SRPConfig.turretFollow);

            for (EntityParasiteBase mobx : this.field_145850_b.func_72872_a(EntityPStationaryArchitect.class, axisalignedbb)) {
               if (mobx instanceof EntityDod || mobx instanceof EntityDodSII || mobx instanceof EntityDodSIII || mobx instanceof EntityDodSIV) {
                  ccc++;
               }
            }

            if (ccc == 0) {
               List<Entity> serverList = this.field_145850_b.field_72996_f;
               int count = 0;

               for (int x = 0; x < serverList.size(); x++) {
                  if (serverList.get(x) instanceof EntityPDispatcher) {
                     if (++count > SRPConfig.nexusDodCap || this.getDistanceSq(serverList.get(x)) < SRPConfig.nexusDodDis * SRPConfig.nexusDodDis) {
                        if (SRPConfigSystems.useEvolution) {
                           SRPSaveData data = SRPSaveData.get(this.field_145850_b, 49);
                           data.setTotalKills(
                              this.field_145850_b.field_73011_w.getDimension(), this.killC * SRPConfig.reinforcerNidusMult, true, this.field_145850_b, true, 45
                           );
                        }

                        return;
                     }
                  }
               }

               if (SRPConfigSystems.useEvolution) {
                  SRPSaveData data = SRPSaveData.get(this.field_145850_b, 47);
                  if (ParasiteEventEntity.getRSchance(this.field_145850_b) == 0.0) {
                     data.setTotalKills(
                        this.field_145850_b.field_73011_w.getDimension(), this.killC * SRPConfig.reinforcerNidusMult, true, this.field_145850_b, true, 46
                     );
                     this.field_145850_b.func_175656_a(this.func_174877_v(), Blocks.field_150350_a.func_176223_P());
                     this.killSelfTile();
                     return;
                  }
               }

               EntityDod dod = new EntityDod(this.field_145850_b);
               dod.func_70107_b(this.field_174879_c.func_177958_n() + 0.5, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 0.5);
               if (!dod.field_70170_p.func_184144_a(dod, dod.func_174813_aQ()).isEmpty()) {
                  dod.func_70106_y();
               } else {
                  this.field_145850_b.func_72838_d(dod);
                  dod.particleStatus((byte)7);
                  if (SRPConfigSystems.rsSounds) {
                     if (SRPConfigSystems.disloGrowlNoise) {
                        if (SRPSaveData.get(this.field_145850_b, 48).getCurrentCode(this.field_145850_b.field_73011_w.getDimension(), 15) == 0) {
                           dod.func_184185_a(SRPSounds.DODSI, 4.0F, 1.0F);
                        }
                     } else {
                        dod.func_184185_a(SRPSounds.DODSI, 4.0F, 1.0F);
                     }
                  }
               }
            }

            this.field_145850_b.func_175656_a(this.func_174877_v(), Blocks.field_150350_a.func_176223_P());
            this.killSelfTile();
            return;
         }
      }
   }

   private void killSelfTile() {
      if (this.field_145850_b != null) {
         this.func_145843_s();
         this.field_145850_b.func_175713_t(this.field_174879_c);
      }
   }

   public double getDistanceSq(Entity entityIn) {
      double d0 = this.field_174879_c.func_177958_n() - entityIn.field_70165_t;
      double d1 = this.field_174879_c.func_177956_o() - entityIn.field_70163_u;
      double d2 = this.field_174879_c.func_177952_p() - entityIn.field_70161_v;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }
}
