package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.block.BlockColonyStructure;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanColony;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityKol extends EntityParasiteBase implements EntityCanColony {
   EntityKol.EntityAIFindingSpotBlock build;

   public EntityKol(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.65F, 0.65F);
      this.field_70728_aV = SRPAttributes.XP_LiTTLE;
      this.field_70714_bg.func_85156_a(this.folow);
      this.type = 7;
      this.killcount = -10.0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 36;
   }

   public EntityKol(World worldIn, BlockPos origin, int distanceBuilding) {
      this(worldIn);
      this.setTask(origin, distanceBuilding);
   }

   public void setTask(BlockPos origin, int distanceBuilding) {
      this.build = new EntityKol.EntityAIFindingSpotBlock(this, distanceBuilding);
      this.field_70714_bg.func_75776_a(3, this.build);
      this.setOrigin(origin);
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.LODO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.LODO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.LODO_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.LODO_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K && this.srpTicks == 10 && this.field_70146_Z.nextInt(7) == 0) {
         if (!SRPConfigWorld.coloniesActivated) {
            return;
         }

         SRPWorldData data = SRPWorldData.get(this.field_70170_p);
         if (data == null) {
            return;
         }

         BlockPos origin = data.nearestColonyPosition(this.func_180425_c(), false);
         if (origin != null) {
            this.setTask(origin, data.getColonyDistanceSpreadByPosition(origin, false));
         }
      }
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance > 50.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   public float func_70047_e() {
      return 0.5F;
   }

   public void setOrigin(BlockPos pos) {
      this.build.setOrigin(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
   }

   public void setOrigin(int x, int y, int z) {
      this.build.setOrigin(x, y, z);
   }

   @Override
   public boolean func_70601_bi() {
      IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos(this).func_177977_b());
      return iblockstate.func_189884_a(this)
         && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL
         && this.isValidLightLevelTwo()
         && SRPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      if (this.build != null) {
         compound.func_74768_a("parasiteoriginx", this.build.getOrigin(1));
         compound.func_74768_a("parasiteoriginy", this.build.getOrigin(2));
         compound.func_74768_a("parasiteoriginz", this.build.getOrigin(3));
      }
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      int x = 0;
      int y = 0;
      int z = 0;
      if (compound.func_150297_b("parasiteoriginx", 99)) {
         x = compound.func_74762_e("parasiteoriginx");
      }

      if (compound.func_150297_b("parasiteoriginy", 99)) {
         y = compound.func_74762_e("parasiteoriginy");
      }

      if (compound.func_150297_b("parasiteoriginz", 99)) {
         z = compound.func_74762_e("parasiteoriginz");
      }

      this.build = new EntityKol.EntityAIFindingSpotBlock(this, 10);
      this.field_70714_bg.func_75776_a(3, this.build);
      this.setOrigin(x, y, z);
   }

   @Override
   public boolean onlySpawnInside() {
      return true;
   }

   public class EntityAIFindingSpotBlock extends EntityAIBase {
      protected final EntityParasiteBase entity;
      private int building;
      private int defence;
      private int tiick;
      private int originX;
      private int originY;
      private int originZ;
      private int maxDistance;

      public EntityAIFindingSpotBlock(EntityKol creatureIn, int maxDistance) {
         this.entity = creatureIn;
         this.building = 26;
         this.defence = this.building / 2;
         this.tiick = 0;
         this.maxDistance = maxDistance * maxDistance;
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         this.tiick++;
         return this.tiick >= 200 && this.entity.getParasiteStatus() == 0;
      }

      public boolean func_75253_b() {
         return this.entity.func_70661_as().func_75500_f() && this.entity.getParasiteStatus() == 0 && this.tiick != 0;
      }

      public void func_75251_c() {
         this.tiick = 0;
      }

      public void func_75246_d() {
         BlockPos flag = this.entity.func_180425_c();
         int range = 25;

         for (int x = flag.func_177958_n() - range; x <= flag.func_177958_n() + range; x++) {
            for (int z = flag.func_177952_p() - range; z <= flag.func_177952_p() + range; z++) {
               if (this.checkPosition(x, z) == this.defence) {
                  BlockPos spot = this.checkBlock(EntityKol.this.field_70170_p, new BlockPos(x, flag.func_177956_o(), z));
                  if (spot != null) {
                     this.entity
                        .field_70170_p
                        .func_175656_a(spot.func_177977_b(), SRPBlocks.ParasiteStructure.func_176223_P().func_177226_a(BlockColonyStructure.ACTIVE, 2));
                     this.func_75251_c();
                     return;
                  }
               } else if (this.checkPosition(x, z) == this.building) {
                  BlockPos spot = this.checkBlock(EntityKol.this.field_70170_p, new BlockPos(x, flag.func_177956_o(), z));
                  if (spot != null) {
                     this.entity
                        .field_70170_p
                        .func_175656_a(spot.func_177977_b(), SRPBlocks.ParasiteStructure.func_176223_P().func_177226_a(BlockColonyStructure.ACTIVE, 1));
                     this.func_75251_c();
                     return;
                  }
               }
            }
         }

         this.func_75251_c();
      }

      private BlockPos checkBlock(World world, BlockPos pos) {
         if (pos.func_177956_o() <= 2) {
            return null;
         } else if (this.getDistanceSqFromOrigin(pos)) {
            return null;
         } else {
            pos = ParasiteEventEntity.getFloor(world, pos, 5);
            if (pos != null) {
               return this.checkArea(world, pos) ? null : pos;
            } else {
               return null;
            }
         }
      }

      private boolean checkArea(World world, BlockPos pos) {
         int a = 30;
         int yy = pos.func_177956_o();

         for (int i = yy - a; i <= yy + a; i++) {
            Block block = world.func_180495_p(new BlockPos(pos.func_177958_n(), i, pos.func_177952_p())).func_177230_c();
            if (block == SRPBlocks.ParasiteStructure || block == SRPBlocks.ColonyHeart || block == SRPBlocks.BiomeHeart) {
               return true;
            }
         }

         return false;
      }

      private int checkPosition(int posX, int posZ) {
         if (posX % this.defence == 0 && posX % this.building != 0 && posZ % this.defence == 0 && posZ % this.building != 0) {
            return this.defence;
         } else {
            return posX % this.building == 0 && posZ % this.building == 0 ? this.building : 0;
         }
      }

      private boolean getDistanceSqFromOrigin(BlockPos pos) {
         double d0 = this.originX - pos.func_177958_n();
         double d1 = this.originY - pos.func_177956_o();
         double d2 = this.originZ - pos.func_177952_p();
         return d0 * d0 + d1 * d1 + d2 * d2 > this.maxDistance;
      }

      public void setOrigin(int x, int y, int z) {
         this.originX = x;
         this.originY = y;
         this.originZ = z;
      }

      public int getOrigin(int in) {
         switch (in) {
            case 1:
               return this.originX;
            case 2:
               return this.originY;
            case 3:
               return this.originZ;
            default:
               return 0;
         }
      }
   }
}
