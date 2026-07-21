package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanHaveBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCosmical;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityDodT;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityUnvo;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class EntityAIDodAttack extends EntityAIBase {
   private final EntityPDispatcher parent;
   private byte stage;
   private double tickss;
   private byte maxID;
   private int maxDodT;
   private ArrayList<Integer> resistanceI = new ArrayList<>();
   float maxHardness;
   private int cooldownNak;

   public EntityAIDodAttack(EntityPDispatcher dod, int CURRENTstage, byte limit, float hardness) {
      this.parent = dod;
      this.tickss = 20.0;
      this.stage = (byte)CURRENTstage;
      this.maxID = limit;
      this.maxDodT = CURRENTstage * 3;
      this.maxHardness = hardness;
      this.cooldownNak = 0;
   }

   public boolean func_75250_a() {
      this.tickss--;
      this.cooldownNak--;
      if (this.tickss < 0.0) {
         this.tickss = 80.0;
         if (this.parent.func_70638_az() != null) {
            this.tickss = 10.0;
         }

         return true;
      } else {
         return false;
      }
   }

   public void func_75251_c() {
   }

   public void func_75246_d() {
      double follow = 0.0;
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            this.parent.field_70165_t,
            this.parent.field_70163_u,
            this.parent.field_70161_v,
            this.parent.field_70165_t + 1.0,
            this.parent.field_70163_u + 1.0,
            this.parent.field_70161_v + 1.0
         )
         .func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
      EntityLivingBase target = this.parent.func_70638_az();
      this.unhide(axisalignedbb);
      this.storePara(axisalignedbb);
      if (target == null) {
         this.findT(follow, axisalignedbb);
      } else {
         if (!this.parent.getMobList().isEmpty() && this.parent.field_70170_p.field_73012_v.nextInt(3) != 0) {
            int sel = this.parent.field_70170_p.field_73012_v.nextInt(this.stage * 2);
            if (sel < this.parent.getMobList().size()) {
               for (int i = 0; i <= sel; i++) {
                  this.summonTentacles(target, follow, axisalignedbb, true);
               }
            }
         } else {
            switch (this.stage) {
               case 1:
                  this.parent.checkID();
                  if (this.parent.getActualParasites() < this.parent.getTotalParasites()
                     && this.checkNak()
                     && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                  }
                  break;
               case 2:
                  this.parent.checkID();
                  if (this.parent.getActualParasites() < this.parent.getTotalParasites()
                     && this.checkNak()
                     && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                  }
                  break;
               case 3:
                  if (this.parent.field_70170_p.field_73012_v.nextBoolean()) {
                     this.parent.checkID();
                     if (this.parent.getActualParasites() < this.parent.getTotalParasites()
                        && this.spawnDet(target, new EntityUnvo(this.parent.field_70170_p), 6, 4, true)) {
                     }
                  } else {
                     this.parent.checkID();
                     if (this.parent.getActualParasites() < this.parent.getTotalParasites()
                        && this.checkNak()
                        && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                     }
                  }
                  break;
               case 4:
                  if (this.parent.field_70170_p.field_73012_v.nextBoolean()) {
                     this.parent.checkID();
                     if (this.parent.getActualParasites() < this.parent.getTotalParasites()
                        && this.spawnDet(target, new EntityUnvo(this.parent.field_70170_p), 6, 4, true)) {
                     }
                  } else {
                     this.parent.checkID();
                     if (this.parent.getActualParasites() < this.parent.getTotalParasites()
                        && this.checkNak()
                        && this.spawnDet(target, new EntityNak(this.parent.field_70170_p), 3, 1, true)) {
                     }
                  }
            }
         }

         if (!target.func_70089_S()) {
            this.parent.func_70624_b(null);
         }
      }
   }

   private void findT(double follow, AxisAlignedBB axisalignedbb) {
      for (EntityLivingBase mob : this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
         if (mob != null) {
            if (mob instanceof EntityPlayer) {
               if (this.canAttackPlayer((EntityPlayer)mob)) {
                  this.parent.func_70624_b(mob);
                  return;
               }
            } else if (mob instanceof EntityLiving
               && !ParasiteEventEntity.checkEntity((EntityLiving)mob, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite)
               && mob != this.parent
               && this.canTargetEntity(mob)
               && mob.func_70068_e(this.parent) < 1024.0
               && mob.func_70089_S()
               && this.parent.func_70686_a((Class<? extends EntityLivingBase>)mob.getClass())) {
               this.parent.func_70624_b(mob);
               return;
            }
         }
      }
   }

   private boolean canTargetEntity(EntityLivingBase in) {
      return !(in instanceof EntityParasiteBase) && !(in instanceof EntityAnimal) && !(in instanceof EntityCreeper) && !(in instanceof EntityWaterMob);
   }

   private boolean canAttackPlayer(EntityPlayer in) {
      return in.field_71075_bZ.field_75102_a ? false : EntityAITarget.func_179445_a(this.parent, in, false, true);
   }

   private void summonTentacles(EntityLivingBase in, double follow, AxisAlignedBB axisalignedbb, boolean dod) {
      if (this.parent.field_70170_p.field_73012_v.nextInt(2) == 0) {
         this.parent.checkID();
         this.checkdodts();
         if (this.resistanceI.size() < this.maxDodT || !dod) {
            if ((this.parent.getActualParasites() < this.parent.getTotalParasites() || this.parent.getMobList().size() > 0)
               && this.spawnDet(in, new EntityDodT(this.parent.field_70170_p), 5, 3, dod)) {
            }
         }
      }
   }

   private void checkdodts() {
      for (int i = 0; i < this.resistanceI.size(); i++) {
         Entity flag = this.parent.field_70170_p.func_73045_a(this.resistanceI.get(i));
         if (flag == null) {
            this.resistanceI.remove(i);
            i = 0;
         } else if (flag instanceof EntityDodT) {
            EntityDodT flagg = (EntityDodT)flag;
            if (!flagg.func_70089_S()) {
               this.resistanceI.remove(i);
               i = 0;
            }
         } else {
            this.resistanceI.remove(i);
            i = 0;
         }
      }
   }

   private boolean spawnDet(EntityLivingBase in, EntityPStationary entityout, int max, int mini, boolean addId) {
      boolean flagN = entityout.getParasiteIDRegister() == 72;
      if (flagN) {
         List<Entity> serverList = this.parent.field_70170_p.field_72996_f;
         int count = 0;

         for (int x = 0; x < serverList.size(); x++) {
            if (serverList.get(x) instanceof EntityNak) {
               count++;
            }
         }

         if (count >= 7) {
            entityout.func_70106_y();
            return false;
         }

         if (this.cooldownNak > 0) {
            entityout.func_70106_y();
            return false;
         }
      }

      if (entityout.getParasiteIDRegister() == 30) {
         List<Entity> serverList = this.parent.field_70170_p.field_72996_f;
         int count = 0;

         for (int xx = 0; xx < serverList.size(); xx++) {
            if (serverList.get(xx) instanceof EntityUnvo) {
               count++;
            }
         }

         if (count >= 7) {
            return false;
         }
      }

      boolean tele = entityout.getParasiteIDRegister() == 74;
      if (tele) {
         List<Entity> serverList = this.parent.field_70170_p.field_72996_f;
         int count = 0;

         for (int xxx = 0; xxx < serverList.size(); xxx++) {
            if (serverList.get(xxx) instanceof EntityParasiteBase) {
               count++;
            }
         }

         if (count >= SRPConfig.worldMobCap) {
            return false;
         }
      }

      if (this.parent.getMobList().size() <= 0 && tele) {
         entityout.func_70106_y();
         return false;
      } else {
         Random rand = new Random();
         double xxxx = in.field_70165_t;
         double y = in.field_70163_u;
         double z = in.field_70161_v;
         double randomx = rand.nextInt(max) + mini;
         double randomz = rand.nextInt(max) + mini;
         double negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomx *= -1.0;
         }

         negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomz *= -1.0;
         }

         int limit = 0;
         boolean flag = true;

         while (flag) {
            if (limit >= 5) {
               entityout.func_70106_y();
               return false;
            }

            BlockPos pos = new BlockPos(xxxx + randomx, y, z + randomz);
            pos = ParasiteEventEntity.getFloor(this.parent.field_70170_p, pos, 5);
            if (pos != null) {
               IBlockState state = this.parent.field_70170_p.func_180495_p(pos);
               if (this.parent.field_70170_p.func_180495_p(pos.func_177977_b()).func_185917_h()) {
                  float bHard = 0.0F;
                  IBlockState state2 = this.parent.field_70170_p.func_180495_p(pos.func_177977_b());
                  float atm = state2.func_185887_b(this.parent.field_70170_p, pos.func_177977_b());
                  if (atm <= 0.0F) {
                     randomx = rand.nextInt(max) + mini;
                     randomz = rand.nextInt(max) + mini;
                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomx *= -1.0;
                     }

                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomz *= -1.0;
                     }

                     limit++;
                     continue;
                  }

                  bHard += atm;
                  state2 = this.parent.field_70170_p.func_180495_p(pos.func_177979_c(2));
                  atm = state2.func_185887_b(this.parent.field_70170_p, pos.func_177979_c(2));
                  if (atm <= 0.0F) {
                     randomx = rand.nextInt(max) + mini;
                     randomz = rand.nextInt(max) + mini;
                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomx *= -1.0;
                     }

                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomz *= -1.0;
                     }

                     limit++;
                     continue;
                  }

                  bHard += atm;
                  state2 = this.parent.field_70170_p.func_180495_p(pos.func_177979_c(3));
                  atm = state2.func_185887_b(this.parent.field_70170_p, pos.func_177979_c(3));
                  if (atm <= 0.0F) {
                     randomx = rand.nextInt(max) + mini;
                     randomz = rand.nextInt(max) + mini;
                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomx *= -1.0;
                     }

                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomz *= -1.0;
                     }

                     limit++;
                     continue;
                  }

                  bHard += atm;
                  if (bHard >= this.maxHardness) {
                     randomx = rand.nextInt(max) + mini;
                     randomz = rand.nextInt(max) + mini;
                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomx *= -1.0;
                     }

                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomz *= -1.0;
                     }

                     limit++;
                     continue;
                  }

                  AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                        pos.func_177958_n(),
                        pos.func_177956_o(),
                        pos.func_177952_p(),
                        pos.func_177958_n() + 1,
                        pos.func_177956_o() + 1,
                        pos.func_177952_p() + 1
                     )
                     .func_186662_g(2.0);
                  List<EntityLivingBase> moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                  if (moblist.isEmpty()) {
                     entityout.func_70012_b(xxxx + randomx, y, z + randomz, this.parent.field_70177_z, this.parent.field_70125_A);
                     if (this.parent.field_70170_p.func_184144_a(entityout, entityout.func_174813_aQ()).isEmpty()) {
                        entityout.func_180482_a(this.parent.field_70170_p.func_175649_E(new BlockPos(entityout)), (IEntityLivingData)null);
                        entityout.func_70624_b(in);
                        if (tele) {
                           this.resistanceI.add(entityout.func_145782_y());
                           String name = this.parent.getParasiteStored();
                           if (name == null) {
                              entityout.func_70106_y();
                              return false;
                           }

                           ((EntityDodT)entityout).setTele(name, this.stage);
                        } else {
                           this.parent.setActualParasites(1);
                           this.parent.addID(entityout.func_145782_y(), 1);
                        }

                        entityout.setPeek(true);
                        entityout.setBuried();
                        if (this.parent.func_70027_ad()) {
                           entityout.func_70015_d(8);
                        }

                        this.parent.field_70170_p.func_72838_d(entityout);
                        this.tickss = 10.0;
                        this.parent.field_70170_p.func_72960_a(entityout, (byte)50);
                        if (entityout.getParasiteIDRegister() == 74) {
                           ((EntityDodT)entityout).setFather(this.parent);
                        } else if (flagN) {
                           ((EntityNak)entityout).setFather(this.parent);
                           this.cooldownNak = 40;
                        }

                        flag = false;
                        return true;
                     }

                     randomx = rand.nextInt(max) + mini;
                     randomz = rand.nextInt(max) + mini;
                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomx *= -1.0;
                     }

                     negative = rand.nextInt(2);
                     if (negative == 0.0) {
                        randomz *= -1.0;
                     }

                     limit++;
                     continue;
                  }
               }
            }

            randomx = rand.nextInt(max) + mini;
            randomz = rand.nextInt(max) + mini;
            negative = rand.nextInt(2);
            if (negative == 0.0) {
               randomx *= -1.0;
            }

            negative = rand.nextInt(2);
            if (negative == 0.0) {
               randomz *= -1.0;
            }

            limit++;
         }

         entityout.func_70106_y();
         return false;
      }
   }

   private void unhide(AxisAlignedBB axisalignedbb) {
      List<EntityLivingBase> moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);

      for (EntityLivingBase mob : moblist) {
         if (mob != this.parent && !(mob instanceof EntityParasiteBase) && mob.field_70122_E && !mob.func_70090_H() && !(mob instanceof EntityPlayer)) {
            if (!(mob instanceof EntityWaterMob) && this.parent.field_70170_p.field_73012_v.nextInt(1) == 0 && moblist.size() < this.parent.getMobList().size()
               )
             {
               this.summonTentacles(mob, 0.0, axisalignedbb, true);
            }

            if (mob.func_70644_a(SRPPotions.COTH_E)) {
               NBTTagCompound tags = mob.getEntityData();
               mob.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 600, 5));
               if (tags.func_74764_b("srpcothimmunity")) {
                  int key = tags.func_74762_e("srpcothimmunity");
                  if (key >= 1) {
                     key += 10;
                     tags.func_74768_a("srpcothimmunity", key);
                  }
               }
            }
         }
      }
   }

   private void storePara(AxisAlignedBB axisalignedbb) {
      for (EntityParasiteBase mob : this.parent.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
         if (mob.func_70089_S()
            && !(mob instanceof EntityPStationary)
            && !(mob instanceof EntityPPreeminent)
            && !(mob instanceof EntityPCosmical)
            && !(mob instanceof EntityCanHaveBodies)
            && mob.func_70638_az() == null
            && mob.field_70122_E
            && mob.field_70173_aa > 100
            && mob.canBeStored() == 0) {
            this.parent.storeParasite(mob);
         }
      }
   }

   private boolean checkNak() {
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            this.parent.field_70165_t,
            this.parent.field_70163_u,
            this.parent.field_70161_v,
            this.parent.field_70165_t + 1.0,
            this.parent.field_70163_u + 1.0,
            this.parent.field_70161_v + 1.0
         )
         .func_72314_b(7.0, 5.0, 7.0);
      List<EntityNak> moblist = this.parent.field_70170_p.func_72872_a(EntityNak.class, axisalignedbb);
      return moblist.isEmpty();
   }
}
