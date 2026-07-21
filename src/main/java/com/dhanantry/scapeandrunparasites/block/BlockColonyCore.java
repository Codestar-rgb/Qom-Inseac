package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import java.util.Random;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockColonyCore extends BlockBase {
   public static final PropertyInteger ACTIVE = PropertyInteger.func_177719_a("active", 0, 3);

   public BlockColonyCore(Material material, String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
      super(material, name, hardness, creative, tickRandom, resistance);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(ACTIVE, 0));
   }

   public EnumPushReaction func_149656_h(IBlockState state) {
      return EnumPushReaction.BLOCK;
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      boolean flag = super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
      if (worldIn.field_72995_K) {
         return flag;
      } else {
         int meta = this.func_176201_c(state);
         System.out.println("meta " + meta);
         System.out.println(" ");
         return flag;
      }
   }

   @Override
   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      boolean flag = super.removedByPlayer(state, world, pos, player, willHarvest);
      if (world.field_72995_K) {
         return flag;
      } else {
         if (this.func_176201_c(state) > 0) {
            ParasiteEventWorld.removeColonyInWorld(world, pos);
         }

         return flag;
      }
   }

   public void onBlockExploded(World worldIn, BlockPos pos, Explosion explosionIn) {
      if (!worldIn.field_72995_K) {
         ParasiteEventWorld.removeColonyInWorld(worldIn, pos);
         super.onBlockExploded(worldIn, pos, explosionIn);
         ParasiteEventWorld.checkColonyStatus(worldIn);
      }
   }

   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
      if (!worldIn.field_72995_K && this.func_176201_c(state) > 0) {
         ParasiteEventWorld.removeColonyInWorld(worldIn, pos);
         ParasiteEventWorld.checkColonyStatus(worldIn);
      }

      super.func_180663_b(worldIn, pos, state);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         if (worldIn.func_175697_a(pos, 3)) {
            int meta = this.func_176201_c(state);
         }
      }
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{ACTIVE});
   }

   public int func_176201_c(IBlockState state) {
      return (Integer)state.func_177229_b(ACTIVE);
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(ACTIVE, meta & 3);
   }
}
