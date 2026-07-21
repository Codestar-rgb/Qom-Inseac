package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParasiteFog extends Block {
   public static final PropertyInteger STAGE = PropertyInteger.func_177719_a("air", 0, 2);

   public BlockParasiteFog(String name) {
      super(Material.field_151579_a);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149675_a(true);
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(STAGE, 0));
   }

   @SideOnly(Side.CLIENT)
   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
      if (rand.nextFloat() < 0.1F) {
         double x = pos.func_177958_n() + 0.5 + rand.nextDouble() * 0.9;
         double y = pos.func_177956_o() + 0.05 + rand.nextDouble() * 0.9;
         double z = pos.func_177952_p() + 0.5 + rand.nextDouble() * 0.9;
         ParticleSpawner.spawnParticle(SRPEnumParticle.COOLER_FOG, x, y, z, 0.0, 0.0, 0.0, 0, 0, 0);
      }

      if (rand.nextFloat() < 0.005F) {
         world.func_184134_a(
            pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5, SRPSounds.FOG, SoundCategory.AMBIENT, 1.3F, 1.0F, false
         );
      }
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) {
      return true;
   }

   public boolean func_176209_a(IBlockState state, boolean hitIfLiquid) {
      return false;
   }

   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return null;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{STAGE});
   }

   public int func_176201_c(IBlockState state) {
      return (Integer)state.func_177229_b(STAGE);
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(STAGE, meta & 2);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      super.func_180650_b(worldIn, pos, state, rand);
      int meta = worldIn.func_180495_p(pos).func_177230_c().func_176201_c(worldIn.func_180495_p(pos));
      if (meta == 0) {
         worldIn.func_175656_a(pos, SRPBlocks.ParasiteFog.func_176223_P().func_177226_a(STAGE, 1));
      }

      if (meta == 2) {
         int i1 = pos.func_177956_o();
         double l1 = pos.func_177958_n();
         double i2 = pos.func_177952_p();
         int BGrange = 2;

         for (int k2 = -1 * BGrange; k2 <= 1 * BGrange; k2++) {
            for (int l2 = -1 * BGrange; l2 <= 1 * BGrange; l2++) {
               for (int j = -1 * BGrange; j <= 1 * BGrange; j++) {
                  double i3 = l1 + k2;
                  double k = i1 + j;
                  double l = i2 + l2;
                  BlockPos blockpos = new BlockPos(i3, k, l);
                  IBlockState iblockstate = worldIn.func_180495_p(blockpos);
                  Block block = iblockstate.func_177230_c();
                  if (block == SRPBlocks.ParasiteFog) {
                     int meta2 = block.func_176201_c(iblockstate);
                     if (meta2 != 2) {
                        worldIn.func_175656_a(blockpos, SRPBlocks.ParasiteFog.func_176223_P().func_177226_a(STAGE, 2));
                     }
                  }
               }
            }
         }

         worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
      }
   }

   public boolean func_176225_a(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      BlockPos offsetPos = pos.func_177972_a(side);
      IBlockState other = world.func_180495_p(offsetPos);
      return other.func_177230_c() == this ? false : super.func_176225_a(state, world, pos, side);
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   @Nullable
   public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return field_185506_k;
   }

   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.SOLID;
   }
}
