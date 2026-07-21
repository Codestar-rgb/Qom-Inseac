package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockInfestedTrunk extends BlockRotatedPillar {
   public BlockInfestedTrunk(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material);
      this.setHarvestLevel("axe", 0);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149675_a(tickRandom);
      this.func_149672_a(SoundType.field_185848_a);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176298_M, Axis.Y));
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      boolean flag = super.removedByPlayer(state, world, pos, player, willHarvest);
      if (world.field_72995_K) {
         return flag;
      } else {
         if (SRPConfigSystems.useEvolution && this.func_176201_c(state) > 0) {
            SRPSaveData data = SRPSaveData.get(world, 10);
            data.setTotalKills(world.field_73011_w.getDimension(), -SRPConfigSystems.valueLossBlockTrunk, true, world, false, 22);
         }

         return flag;
      }
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (worldIn.func_175697_a(pos, 3)) {
         if (rand.nextDouble() < 0.5) {
            if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
               worldIn.func_175656_a(pos, SRPBlocks.ParasiteStain.func_176223_P());
            } else {
               int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
               if (heart > 0) {
                  BlockParasiteSpreading.SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
               }
            }
         } else if (rand.nextDouble() <= 0.05) {
            this.func_180650_b(worldIn, pos, state, rand);
         }
      }
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (worldIn.func_175697_a(pos, 3)) {
         if (!worldIn.field_72995_K) {
            this.func_176201_c(state);
            BlockParasiteSpreading.canInfestBlock(worldIn, pos, rand, 2, true);
         }
      }
   }

   public IBlockState func_176203_a(int meta) {
      IBlockState state = this.func_176223_P();
      int i = meta & 12;
      Axis enumfacing$axis = Axis.Y;
      if (i == 4) {
         enumfacing$axis = Axis.X;
      } else if (i == 8) {
         enumfacing$axis = Axis.Z;
      }

      return state.func_177226_a(field_176298_M, enumfacing$axis);
   }

   public int func_176201_c(IBlockState state) {
      int i = 0;
      Axis enumfacing$axis = (Axis)state.func_177229_b(field_176298_M);
      if (enumfacing$axis == Axis.X) {
         i |= 4;
      } else if (enumfacing$axis == Axis.Z) {
         i |= 8;
      }

      return i;
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_176298_M});
   }

   protected ItemStack func_180643_i(IBlockState state) {
      return super.func_180643_i(state);
   }
}
