package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockResiduePlants extends BlockBase {
   public static final PropertyDirection FACING = PropertyDirection.func_177714_a("facing");
   private static final AxisAlignedBB AABB_CENTER = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.875, 0.875);

   private static boolean isResidueSupport(Block b) {
      return b == SRPBlocks.ResidueBlock || b == SRPBlocks.ResidueBricks;
   }

   public BlockResiduePlants(String name) {
      super(Material.field_151585_k, name, 0.0F, true, true, 0.0F);
      this.func_149672_a(SRPSoundTypes.VOMIT);
      this.func_149713_g(0);
      this.func_149675_a(true);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.UP));
   }

   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      Random rand = world instanceof World ? ((World)world).field_73012_v : new Random();
      int count = 1 + rand.nextInt(2);
      if (fortune > 0) {
         count += rand.nextInt(fortune + 1);
      }

      Item item = Item.func_111206_d("srparasites:infestedremain");
      if (item == null) {
         item = Item.func_111206_d("srparasites:infestremain");
      }

      if (item != null) {
         drops.add(new ItemStack(item, count));
      }
   }

   protected boolean func_149700_E() {
      return true;
   }

   public boolean func_149686_d(IBlockState s) {
      return false;
   }

   public boolean func_149662_c(IBlockState s) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess world, BlockPos pos) {
      return AABB_CENTER;
   }

   @Nullable
   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess world, BlockPos pos) {
      return field_185506_k;
   }

   private boolean canAttach(World world, BlockPos pos, EnumFacing facing) {
      BlockPos supportPos = pos.func_177972_a(facing.func_176734_d());
      return isResidueSupport(world.func_180495_p(supportPos).func_177230_c());
   }

   public boolean func_176196_c(World world, BlockPos pos) {
      for (EnumFacing f : EnumFacing.values()) {
         if (this.canAttach(world, pos, f)) {
            return true;
         }
      }

      return false;
   }

   public boolean func_176198_a(World world, BlockPos pos, EnumFacing side) {
      return this.canAttach(world, pos, side);
   }

   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      return this.func_176223_P().func_177226_a(FACING, side);
   }

   public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (!this.canAttach(world, pos, (EnumFacing)state.func_177229_b(FACING))) {
         world.func_175655_b(pos, false);
      }
   }

   public BlockFaceShape func_193383_a(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }

   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.field_72995_K) {
         if (!this.canAttach(world, pos, (EnumFacing)state.func_177229_b(FACING))) {
            world.func_175655_b(pos, false);
         } else if (rand.nextInt(8) == 0) {
            for (int tries = 0; tries < 3; tries++) {
               EnumFacing f = EnumFacing.values()[rand.nextInt(6)];
               BlockPos newPos = pos.func_177972_a(f);
               if (world.func_175623_d(newPos) && this.canAttach(world, newPos, f)) {
                  world.func_180501_a(newPos, this.func_176223_P().func_177226_a(FACING, f), 2);
                  break;
               }
            }
         }
      }
   }

   public IBlockState func_176203_a(int meta) {
      EnumFacing f = EnumFacing.func_82600_a(MathHelper.func_76125_a(meta, 0, 5));
      return this.func_176223_P().func_177226_a(FACING, f);
   }

   public int func_176201_c(IBlockState state) {
      return ((EnumFacing)state.func_177229_b(FACING)).func_176745_a();
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }
}
