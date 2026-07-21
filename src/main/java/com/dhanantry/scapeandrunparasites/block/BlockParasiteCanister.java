package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockParasiteCanister extends BlockBase implements IMetaName {
   public static final PropertyEnum<BlockParasiteCanister.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockParasiteCanister.EnumType.class);
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.55, 0.9);

   public BlockParasiteCanister(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material, name, hardness, creative, tickRandom);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockParasiteCanister.EnumType.SAC));
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      return state.func_177229_b(VARIANT) == BlockParasiteCanister.EnumType.CYST ? TALL_GRASS_AABB : super.func_185496_a(state, source, pos);
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (worldIn.func_175697_a(pos, 3)) {
         if (!worldIn.field_72995_K
            && state.func_177229_b(VARIANT) == BlockParasiteCanister.EnumType.CYST
            && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
         }
      }
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      switch ((BlockParasiteCanister.EnumType)state.func_177229_b(VARIANT)) {
         case SAC:
            return SRPItems.itemlurecomponent6;
         default:
            return super.func_180660_a(state, rand, fortune);
      }
   }

   public int quantityDropped(IBlockState state, int fortune, Random random) {
      if (state.func_177229_b(VARIANT) == BlockParasiteCanister.EnumType.SAC) {
         return random.nextDouble() < 0.05 ? 1 : 0;
      } else {
         return super.quantityDropped(state, fortune, random);
      }
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockParasiteCanister.EnumType variant : BlockParasiteCanister.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockParasiteCanister.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockParasiteCanister.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockParasiteCanister.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   public static enum EnumType implements IStringSerializable {
      SAC,
      CYST,
      LUMP,
      BAG;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
