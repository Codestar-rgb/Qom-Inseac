package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.block.BlockInfestedStain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteFog;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPStationaryArchitect extends EntityPStationary implements EntityCanSummon {
   protected int totalP;
   protected int actualP;
   protected boolean convert = true;
   protected int[] mobID;
   protected int[] mobPT;
   protected byte stage;
   protected float body;
   public int neededTime;
   protected int actualTime;
   protected int topParticles;
   private boolean canGrowTo;
   private int bombC;

   public EntityPStationaryArchitect(World worldIn) {
      super(worldIn);
      this.killcount = -10.0;
      this.canD = SRPConfig.rsDespawn;
      this.canGrowTo = true;
      this.borderOrb = -1;
   }

   public void setCanGrowTo(boolean in) {
      this.canGrowTo = in;
   }

   public void setConvert(boolean in) {
      this.convert = in;
   }

   public int setGT(int minGrowTime, int maxGrowTime) {
      int atm = maxGrowTime - minGrowTime + 1;
      return atm > 0 && maxGrowTime > minGrowTime ? this.field_70146_Z.nextInt(atm) + minGrowTime : 1;
   }

   public void setActualT(int in) {
      if (this.canGrowTo) {
         this.actualTime = in;
      }
   }

   public int getActualT() {
      return this.actualTime;
   }

   public int getNeededTime() {
      return this.neededTime;
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.topParticles > 0) {
         this.topParticles--;
      }

      if (this.bombC > 0) {
         this.bombC--;
      }
   }

   @Override
   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
      if (hand == EnumHand.MAIN_HAND && !this.field_70170_p.field_72995_K) {
         Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
         if (wea == SRPItems.itemEvolve) {
            this.canChangeVariant = true;
            this.setActualT(100000000);
            this.setActualT(100000000);
            return true;
         } else if (wea == SRPItems.itembase) {
            this.generateStructure();
            return true;
         } else {
            return super.func_184645_a(player, hand);
         }
      } else {
         return super.func_184645_a(player, hand);
      }
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      super.func_70645_a(cause);
      if (!this.field_70170_p.field_72995_K) {
         int i1 = this.func_180425_c().func_177956_o();
         double l1 = this.func_180425_c().func_177958_n();
         double i2 = this.func_180425_c().func_177952_p();
         int BGrange = 2;

         for (int k2 = -1 * BGrange; k2 <= 1 * BGrange; k2++) {
            for (int l2 = -1 * BGrange; l2 <= 1 * BGrange; l2++) {
               for (int j = -1 * BGrange; j <= 1 * BGrange; j++) {
                  double i3 = l1 + k2;
                  double k = i1 + j;
                  double l = i2 + l2;
                  BlockPos blockpos = new BlockPos(i3, k, l);
                  IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                  Block block = iblockstate.func_177230_c();
                  if (block == SRPBlocks.ParasiteFog) {
                     this.field_70170_p.func_175656_a(blockpos, SRPBlocks.ParasiteFog.func_176223_P().func_177226_a(BlockParasiteFog.STAGE, 2));
                  }
               }
            }
         }

         if (SRPConfigWorld.originActivated) {
            return;
         }

         if (this.convert || ParasiteEventEntity.getRSchance(this.field_70170_p) == 0.0 || !SRPConfigSystems.useEvolution && SRPConfigSystems.rschance == 0.0) {
            BlockPos floor = new BlockPos(this).func_177977_b();
            Block bbb = this.field_70170_p.func_180495_p(floor).func_177230_c();
            if (bbb == SRPBlocks.InfestedStain || bbb == SRPBlocks.InfestedRubble) {
               int meta = bbb.func_176201_c(this.field_70170_p.func_180495_p(floor));
               if (meta <= SRPConfigSystems.rsBlockRevertStage && meta <= this.stage || ParasiteEventEntity.getRSchance(this.field_70170_p) == 0.0) {
                  this.field_70170_p.func_175656_a(floor, SRPBlocks.InfestedStain.func_176223_P().func_177226_a(BlockInfestedStain.STAGE, 5));
                  this.field_70170_p
                     .func_175654_a(floor, SRPBlocks.InfestedStain.func_176223_P().func_177226_a(BlockInfestedStain.STAGE, 5).func_177230_c(), 40, 5);
               }
            }
         }
      }
   }

   public float getBODY() {
      return this.body;
   }

   public byte getStageV() {
      return this.stage;
   }

   @Override
   public void addID(int id, int points) {
      for (int i = 0; i < this.mobID.length; i++) {
         if (this.mobID[i] == -777) {
            this.mobID[i] = id;
            this.mobPT[i] = points;
            return;
         }
      }
   }

   @Override
   public int IDable() {
      int flag = 0;

      for (int j : this.mobID) {
         if (j == -777) {
            flag++;
         }
      }

      if (flag > this.totalP) {
         flag = this.totalP;
      }

      return flag;
   }

   @Override
   public void checkID() {
      for (int i = 0; i < this.mobID.length; i++) {
         if (this.mobID[i] > 0) {
            Entity flag = this.field_70170_p.func_73045_a(this.mobID[i]);
            if (flag == null) {
               this.mobID[i] = -777;
               int negative = this.mobPT[i] * -1;
               this.setActualParasites(negative);
            }
         }
      }
   }

   @Override
   public int[] getIDList() {
      return this.mobID;
   }

   @Override
   public int[] getPointList() {
      return this.mobPT;
   }

   @Override
   public int getTotalParasites() {
      return this.totalP;
   }

   @Override
   public int getActualParasites() {
      return this.actualP;
   }

   @Override
   public void setActualParasites(int i) {
      this.actualP += i;
   }

   public abstract float getBombDamage();

   public abstract void generateStructure();

   @Override
   public boolean func_70692_ba() {
      return this.field_70170_p.func_180494_b(this.func_180425_c()) instanceof BiomeParasiteBase ? true : super.func_70692_ba();
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (source == DamageSource.field_76376_m && this.func_70644_a(MobEffects.field_76436_u) && amount == 1.0F) {
         this.func_70691_i(1.0F * this.genePoisonHealing);
         return false;
      } else {
         float red = this.getParasiteStatus() == 0 ? 0.4F : 1.0F;
         boolean flag = super.func_70097_a(source, amount * red);
         if (flag && this.bombC == 0 && source != DamageSource.field_76380_i) {
            if (!(source.func_76346_g() instanceof EntityLivingBase)) {
               return flag;
            }

            if (source.func_76346_g().func_70068_e(this) > 36.0) {
               return flag;
            }

            for (int i = 0; i < this.stage * 2; i++) {
               double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
               double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
               double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
               double d3 = d0 - this.field_70165_t;
               double d4 = d1 - this.field_70163_u;
               double d5 = d2 - this.field_70161_v;
               double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
               d3 /= d6;
               d4 /= d6;
               d5 /= d6;
               double d7 = 0.5 / (d6 / 4.0 + 0.1);
               d7 *= this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.65F;
               d3 *= d7;
               d4 = d4 * d7 * this.stage * 3.0;
               d5 *= d7;
               EntityBomb bomb = new EntityBomb(this.field_70170_p, this, false);
               bomb.setFuse(60);
               bomb.setStren(0.0F);
               bomb.setSkin(1);
               bomb.setDamage(this.getBombDamage(), this.stage);
               bomb.field_70125_A -= -20.0F;
               bomb.func_82149_j(this);
               bomb.setMotion(d3, d4, d5, 0.35, this.stage / 10.0F * 2.0F);
               this.field_70170_p.func_72838_d(bomb);
               bomb.updateSTR();
            }

            this.bombC = 80;
         }

         return flag;
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74768_a("tickse", this.actualTime);
      compound.func_74768_a("neededtime", this.neededTime);
      compound.func_74757_a("cangrowto", this.canGrowTo);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("tickse", 99)) {
         this.actualTime = compound.func_74762_e("tickse");
      }

      if (compound.func_150297_b("neededtime", 99)) {
         this.neededTime = compound.func_74762_e("neededtime");
      }

      if (compound.func_150297_b("cangrowto", 99)) {
         this.canGrowTo = compound.func_74767_n("cangrowto");
      }
   }

   @Override
   public void func_70103_a(byte id) {
      if (id == 12) {
         this.topParticles = 20;
      } else {
         super.func_70103_a(id);
      }
   }

   @SideOnly(Side.CLIENT)
   public void spawnParticlesTop(SRPEnumParticle particleType, int r, int g, int b) {
      double d0 = this.field_70146_Z.nextGaussian() * 0.02;
      double d1 = this.field_70146_Z.nextGaussian() * 0.02;
      double d2 = this.field_70146_Z.nextGaussian() * 0.02;
      ParticleSpawner.spawnParticle(
         particleType,
         this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
         this.field_70163_u + this.field_70131_O + this.field_70146_Z.nextFloat() * (this.field_70131_O - this.func_70047_e() + 0.5),
         this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
         d0,
         d1,
         d2,
         r,
         g,
         b
      );
   }
}
