package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityLeemB;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteNexusProtection3;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityPRooter extends EntityPStationaryArchitect {
   protected int leemRange;
   protected int leemRangeEffect;
   protected int leemBalls;
   protected int leemCooldown;
   protected int leemCooldownReset;
   private int blockR;
   private static final DataParameter<Boolean> RTTS = EntityDataManager.func_187226_a(EntityPRooter.class, DataSerializers.field_187198_h);
   protected int rangeB = 0;

   public EntityPRooter(World worldIn) {
      super(worldIn);
      this.setScentHPMultiplier(1.0F);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(RTTS, false);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K) {
         if (this.srpTicks == 5) {
            this.leemCooldown--;
            this.blockR++;
            if (this.blockR >= 7) {
               this.checkRTTS();
               this.blockR = 0;
            }
         }

         if (this.srpTicks == 10 && this.leemCooldown <= 0) {
            this.leemCooldown = this.leemCooldownReset;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
               )
               .func_72314_b(this.leemRangeEffect, this.leemRangeEffect, this.leemRangeEffect);

            for (EntityParasiteBase mob : this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
               if (mob != this && mob.func_70089_S() && !(mob instanceof EntityPRooter) && mob.getParasiteIDRegister() != 314) {
                  mob.func_70690_d(new PotionEffect(SRPPotions.PIVOT_E, 300, this.stage - 1, false, false));
                  mob.func_70690_d(new PotionEffect(SRPPotions.PARATE_E, 300, this.stage - 1, false, false));
                  mob.SetRooter(this);
                  mob.applyGene(new boolean[]{true, true, true, true, true, true, true, true, true, true}, new float[]{2.5F, 3.0F, 0.5F});
               }
            }
         }
      }
   }

   @Override
   public void func_70690_d(PotionEffect potioneffectIn) {
      if (potioneffectIn.func_188419_a() != SRPPotions.PIVOT_E) {
         super.func_70690_d(potioneffectIn);
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else {
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_72314_b(this.leemRange + 1, this.leemRange, this.leemRange + 1);
         List<EntityLeemB> moblist = this.field_70170_p.func_72872_a(EntityLeemB.class, axisalignedbb);
         if (moblist.size() <= 0) {
            this.spawnLeemB(this.leemBalls);
            return super.func_70097_a(source, amount);
         } else {
            float part = amount / moblist.size();

            for (EntityLeemB mob : moblist) {
               mob.func_70097_a(source, part);
            }

            return super.func_70097_a(source, 0.0F);
         }
      }
   }

   protected void spawnLeemB(int in) {
      int cap = this.field_70146_Z.nextInt(this.leemBalls) + 1;

      for (int i = 0; i < cap; i++) {
         ParasiteSummon.SummonM(this, new String[]{"srparasites:rooterball;1;1"}, 2, 5, null);
      }
   }

   public void checkRTTS() {
      if (this.rangeB != 0) {
         double l1 = this.field_70165_t;
         double i2 = this.field_70161_v;

         for (int k2 = -1 * this.rangeB; k2 <= 1 * this.rangeB; k2++) {
            for (int l2 = -1 * this.rangeB; l2 <= 1 * this.rangeB; l2++) {
               double i3 = l1 + k2;
               double l = i2 + l2;
               IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos(i3, this.field_70163_u - 0.5, l));
               Block block = iblockstate.func_177230_c();
               if (block == Blocks.field_150350_a) {
                  this.setRTTS(false);
                  return;
               }
            }
         }

         this.setRTTS(true);
      }
   }

   @Override
   public void generateStructure() {
      if (SRPConfig.nexusStructures) {
         WorldGenParasiteNexusProtection3 p1 = new WorldGenParasiteNexusProtection3(false, 1);
         p1.func_180709_b(this.field_70170_p, new Random(), this.func_180425_c());
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
   }

   public boolean getRTTS() {
      return (Boolean)this.field_70180_af.func_187225_a(RTTS);
   }

   public void setRTTS(boolean in) {
      this.field_70180_af.func_187227_b(RTTS, in);
   }
}
