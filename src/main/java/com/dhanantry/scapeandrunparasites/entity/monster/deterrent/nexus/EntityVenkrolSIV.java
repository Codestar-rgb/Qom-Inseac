package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIVenkrolSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPrimitive;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationaryArchitect;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.logic.VenkrolTornadoLogic;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;
import net.minecraft.world.biome.Biome;

public class EntityVenkrolSIV extends EntityPBeckon implements EntityBodyParts {
   public EntityAIVenkrolSummon summonV = new EntityAIVenkrolSummon(
      this, SRPConfigMobs.venkrolsivlimit, 20 * SRPConfigMobs.venkrolsivCooldown, 4, SRPConfigMobs.venkrolsivCAMinimumV, SRPConfigMobs.venkrolsivCAExtraM
   );
   private int count;
   private int tCount;
   private int ticksss;
   private EntityBody head;
   private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), Color.RED, Overlay.PROGRESS).func_186741_a(false);

   public EntityVenkrolSIV(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.8F, 6.9F);
      this.field_70158_ak = true;
      this.head = new EntityBody(this, 1.9F, 1.9F, 1.0F, 0.0F, 7.0F, -1, 1, false, 0.2F);
      this.totalP = SRPConfigMobs.venkrolsivTotalActiveMobs;
      this.mobID = new int[this.totalP + SRPConfigMobs.venkrolsivlimit];
      this.mobPT = new int[this.totalP + SRPConfigMobs.venkrolsivlimit];
      this.stage = 4;
      this.field_70728_aV = SRPAttributes.XP_ADAPTED * 4;
      Arrays.fill(this.mobID, -777);
      this.setBODY(1.0F);
      this.field_70714_bg.func_75776_a(2, this.summonV);
      this.damageCap = SRPConfig.nexussivCap;
      this.count = 200;
      this.tCount = 300;
      this.ticksss = 0;
      this.pointCap = SRPConfig.nexussivPointCap;
      this.pointReduction = SRPConfig.nexussivPointRed;
      this.chanceLearn = SRPConfig.nexussivChanceLe;
      this.chanceLearnFire = SRPConfig.nexussivChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussivPointDamCap;
      this.valueEvDeath = SRPConfig.nexussivLoosingEPValue;
   }

   @Override
   public void setSkillBreakBlocksValues(float hardness, int heightIn, int rangeIn) {
      this.blockH = hardness;
      this.BGheight = heightIn + 2;
      this.BGrange = rangeIn;
   }

   @Override
   public int getParasiteIDRegister() {
      return 41;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VENKROLSIV_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VENKROLSIV_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VENKROLSIV_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussivFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.head.func_70071_h_();
      this.count--;
      this.tCount--;
      if (this.getParasiteStatus() == 0) {
         this.setBODY(0.04F);
      } else {
         this.setBODY(-0.04F);
      }

      if (!this.field_70170_p.field_72995_K) {
         BlockPos pos = this.func_180425_c();
         if (SRPConfigWorld.venkrolTornadoEnabled
            && this.field_70170_p.func_72896_J()
            && this.field_70170_p.func_72911_I()
            && this.field_70170_p.func_175678_i(pos.func_177984_a())) {
            VenkrolTornadoLogic.tickTornadoEffects(this);
         }
      }

      this.spawnThunder();
      this.placeBiome();
   }

   private void spawnThunder() {
      if (!this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() < 0.015) {
         if (this.tCount > 0) {
            return;
         }

         if (this.count < 0 && this.upgradeParasites()) {
            this.tCount = 300;
            this.count = 300;
            return;
         }

         int range = 64;
         double randomx = this.field_70146_Z.nextInt(range);
         double randomz = this.field_70146_Z.nextInt(range);
         double negative = this.field_70146_Z.nextInt(2);
         if (negative == 0.0) {
            randomx *= -1.0;
         }

         negative = this.field_70146_Z.nextInt(2);
         if (negative == 0.0) {
            randomz *= -1.0;
         }

         BlockPos ray = ParasiteEventEntity.getFloor(
            this.field_70170_p, new BlockPos(randomx + this.field_70165_t, this.field_70163_u, randomz + this.field_70161_v), 5
         );
         if (ray != null) {
            if (SRPConfig.thunderEnable) {
               this.field_70170_p
                  .func_72942_c(new EntityLightningBolt(this.field_70170_p, ray.func_177958_n(), ray.func_177956_o(), ray.func_177952_p(), true));
            }

            this.tCount = 200;
         }
      }
   }

   private boolean upgradeParasites() {
      int count = 3;
      int current = 0;
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
         )
         .func_72314_b(48.0, 34.0, 48.0);
      List<EntityParasiteBase> moblist = this.field_70170_p.func_72872_a(EntityPInfected.class, axisalignedbb);

      for (EntityParasiteBase mob : moblist) {
         if (mob instanceof EntityPStationaryArchitect
            && (mob.getParasiteIDRegister() == 16 || mob.getParasiteIDRegister() == 18 || mob.getParasiteIDRegister() == 19)) {
            ((EntityPStationaryArchitect)mob).setActualT(0);
         }
      }

      if (moblist.size() > SRPConfig.nexussivCapUpgrade) {
         return true;
      } else {
         for (EntityParasiteBase mobx : moblist) {
            if (mobx.getParasiteIDRegister() != 64) {
               current++;
               this.thunderParasite(mobx, 1);
               if (current >= count) {
                  return true;
               }
            }
         }

         if (current >= count) {
            return true;
         } else {
            for (EntityParasiteBase mobxx : this.field_70170_p.func_72872_a(EntityPPrimitive.class, axisalignedbb)) {
               current++;
               this.thunderParasite(mobxx, 2);
               if (current >= count) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private void thunderParasite(EntityParasiteBase in, int type) {
      if (ParasiteEventEntity.canSpawnNext) {
         switch (type) {
            case 1:
               EntityParasiteBase out = ParasiteEventEntity.getRandomPrimitive(in.field_70170_p);
               if (SRPConfig.thunderEnable) {
                  in.field_70170_p.func_72942_c(new EntityLightningBolt(in.field_70170_p, in.field_70165_t, in.field_70163_u, in.field_70161_v, true));
               }

               ParasiteEventEntity.spawnNext(in, out, true, true);
               break;
            case 2:
               if (SRPConfig.thunderEnable) {
                  in.field_70170_p.func_72942_c(new EntityLightningBolt(in.field_70170_p, in.field_70165_t, in.field_70163_u, in.field_70161_v, true));
               }

               in.setKillC(in.getKillC() + 1000.0);
         }
      }
   }

   @Override
   protected void func_70619_bc() {
      super.func_70619_bc();
      this.bossInfo.func_186735_a(this.func_110143_aJ() / this.func_110138_aP());
   }

   public void func_96094_a(String name) {
      super.func_96094_a(name);
      this.bossInfo.func_186739_a(this.func_145748_c_());
   }

   public void func_184178_b(EntityPlayerMP player) {
   }

   public void func_184203_c(EntityPlayerMP player) {
      super.func_184203_c(player);
      this.bossInfo.func_186761_b(player);
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag && source.func_76346_g() != null && source.func_76346_g() instanceof EntityPlayerMP) {
         this.bossInfo.func_186760_a((EntityPlayerMP)source.func_76346_g());
      }

      return flag;
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70146_Z.nextBoolean()) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, this, 80, 0);
      }

      return this.func_70097_a(source, amount * 3.0F);
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      super.func_70106_y();
   }

   public float func_70047_e() {
      return 8.1F;
   }

   public void setBODY(float in) {
      in += this.getBODY();
      if (in > 0.5F) {
         in = 0.5F;
      }

      if (in < 0.0F) {
         in = 0.0F;
      }

      this.body = in;
   }

   private void placeBiome() {
      if (this.field_70173_aa >= 1200) {
         if (!this.field_70170_p.field_72995_K) {
            this.ticksss++;
            if (this.ticksss >= 20) {
               this.ticksss = 0;
               if (ParasiteEventWorld.canBiomeStillExist(this.field_70170_p, this.func_180425_c(), true) >= 1) {
                  this.ticksss = -1000;
               } else {
                  int range = 7;
                  int attemp = 3;
                  int mini = 5;

                  while (attemp > 0) {
                     attemp--;
                     double randomx = this.field_70146_Z.nextInt(range);
                     double randomz = this.field_70146_Z.nextInt(range);
                     double negative = this.field_70146_Z.nextInt(2);
                     if (negative == 0.0) {
                        randomx = randomx * -1.0 - mini;
                     } else {
                        randomx += mini;
                     }

                     negative = this.field_70146_Z.nextInt(2);
                     if (negative == 0.0) {
                        randomz = randomz * -1.0 - mini;
                     } else {
                        randomz += mini;
                     }

                     BlockPos pos = new BlockPos(this.field_70165_t + randomx, this.field_70163_u, this.field_70161_v + randomz);
                     pos = ParasiteEventEntity.getFloor(this.field_70170_p, pos, 5);
                     if (pos != null) {
                        int typeB = 1;
                        Biome biome = this.field_70170_p.func_180494_b(pos);
                        if (biome == Biomes.field_76770_e
                           || biome == Biomes.field_76778_j
                           || biome == Biomes.field_76774_n
                           || biome == Biomes.field_76775_o
                           || biome == Biomes.field_150576_N
                           || biome == Biomes.field_185440_P) {
                           typeB = 3;
                        }

                        if (ParasiteEventWorld.placeHeartInWorld(this.field_70170_p, pos, typeB) == 1) {
                           this.ticksss = -1000;
                           return;
                        }
                     }
                  }

                  this.ticksss = -100;
               }
            }
         }
      }
   }

   @Override
   public boolean func_70692_ba() {
      return SRPConfig.rsDespawn;
   }

   @Override
   public void setBodyPartDead(int id) {
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.VENKROLSIV_ATTACK_DAMAGE;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      if (!this.field_70170_p.field_72995_K) {
         if (!SRPConfigWorld.coloniesActivated && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else if (ParasiteEventWorld.numberofColonies(this.field_70170_p) < 1 && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else {
            ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
            ParasiteEventEntity.spawnNext(this, new EntityVenkrolSIII(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIVD, SRPConfigSystems.chanceEventParaNexusIVD, 0, null);
      return super.onDeathDislo(cause);
   }
}
