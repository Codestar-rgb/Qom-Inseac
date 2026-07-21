package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.EntityBodyModel;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAncientSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIDodAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Arrays;
import javax.annotation.Nonnull;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;

public class EntityDodSIV extends EntityPDispatcher implements EntityBodyParts {
   private EntityBodyModel head;
   private int ticksss;
   private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), Color.RED, Overlay.PROGRESS).func_186741_a(false);

   public EntityDodSIV(World worldIn) {
      super(worldIn);
      this.func_70105_a(4.7F, 5.5F);
      this.field_70158_ak = true;
      this.field_70728_aV = SRPAttributes.XP_ADAPTED * 4;
      this.buried = 0.1;
      this.setParasiteStatus(3);
      this.damageCap = SRPConfig.nexussivCap;
      this.pointCap = SRPConfig.nexussivPointCap;
      this.pointReduction = SRPConfig.nexussivPointRed;
      this.chanceLearn = SRPConfig.nexussivChanceLe;
      this.chanceLearnFire = SRPConfig.nexussivChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussivPointDamCap;
      this.totalP = SRPConfigMobs.dodsivTotalActiveMobs;
      this.mobID = new int[3];
      this.mobPT = new int[3];
      this.stage = 4;
      Arrays.fill(this.mobID, -777);
      this.head = new EntityBodyModel(this, 1.2F, 2.5F, 1.0F, 0.0F, 6.3F, -1, 1, false, 0.2F);
      this.head.setSkin(1);
      this.valueEvDeath = SRPConfig.nexussivLoosingEPValue;
   }

   @Override
   public int getParasiteIDRegister() {
      return 79;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(2, new EntityAIDodAttack(this, 4, (byte)52, 30.0F));
      this.field_70714_bg.func_75776_a(4, new EntityAIAncientSummon(this, 120, 4, new String[]{"srparasites:ancientpod;1;1"}));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DODSIV_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.DODSIV_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DODSIV_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussivFollow * SRPConfigMobs.dodsivFollowRangeMult);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.head.func_70071_h_();
      this.placeColony();
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
      return 2.5F;
   }

   private void placeColony() {
      if (this.field_70146_Z.nextInt(10) == 0) {
         if (this.field_70173_aa >= 1200) {
            if (!this.field_70170_p.field_72995_K) {
               this.ticksss++;
               if (this.ticksss >= 200) {
                  this.ticksss = 0;
                  if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1) {
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
                        if (pos != null && ParasiteEventWorld.placeColonyInWorld(this.field_70170_p, pos) == 1) {
                           this.ticksss = -1000;
                           return;
                        }
                     }

                     this.ticksss = -100;
                  }
               }
            }
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
   public void setBodyPartDead(int id) {
   }

   @Override
   public boolean func_70692_ba() {
      return SRPConfig.rsDespawn;
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.DODSIV_ATTACK_DAMAGE;
   }

   @Override
   public boolean storeParasite(EntityParasiteBase in) {
      if (super.storeParasite(in)) {
         return true;
      } else if (this.storeLodo(in, true)) {
         return true;
      } else if (this.storeInf(in, true)) {
         return true;
      } else if (this.storeCrude(in, true)) {
         return true;
      } else if (this.storeMudo(in, true)) {
         return true;
      } else if (this.storeMangler(in, true)) {
         return true;
      } else {
         this.storeAll(in);
         return false;
      }
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
            ParasiteEventEntity.spawnNext(this, new EntityDodSIII(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIVD, SRPConfigSystems.chanceEventParaNexusIVD, 0, null);
      return super.onDeathDislo(cause);
   }
}
