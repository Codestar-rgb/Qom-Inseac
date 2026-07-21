package com.dhanantry.scapeandrunparasites.block.slabs;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import java.util.Objects;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BlockSlabRubble extends BlockSlabBase {
   public static final PropertyEnum<BlockSlabRubble.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockSlabRubble.EnumType.class);

   public BlockSlabRubble(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half) {
      super(materialIn, name, hardness, tickRandom, tickRandom, half);
      this.setHarvestLevel("pickaxe", 1);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      this.field_149783_u = !this.func_176552_j();
      IBlockState state = this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockSlabRubble.EnumType.BONE);
      if (!this.func_176552_j()) {
         state = state.func_177226_a(field_176554_a, EnumBlockHalf.BOTTOM);
      }
   }

   public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(this, 1, ((BlockSlabRubble.EnumType)state.func_177229_b(VARIANT)).ordinal());
   }

   public String func_150002_b(int meta) {
      return super.func_149739_a();
   }

   public IProperty<?> func_176551_l() {
      return VARIANT;
   }

   public Comparable<?> func_185674_a(ItemStack stack) {
      return BlockSlabRubble.EnumType.values()[stack.func_77960_j() & 7];
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockSlabRubble.EnumType variant : BlockSlabRubble.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      IBlockState state = this.func_176223_P().func_177226_a(VARIANT, BlockSlabRubble.EnumType.values()[meta & 7]);
      if (!this.func_176552_j()) {
         state = state.func_177226_a(field_176554_a, (meta & 8) != 0 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
      }

      return state;
   }

   public int func_176201_c(IBlockState state) {
      int meta = ((BlockSlabRubble.EnumType)state.func_177229_b(VARIANT)).ordinal();
      if (!this.func_176552_j() && state.func_177229_b(field_176554_a) == EnumBlockHalf.TOP) {
         meta |= 8;
      }

      return meta;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_176554_a, VARIANT});
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   @Override
   public Enum[] getVariants() {
      return BlockSlabRubble.EnumType.values();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(
         Item.func_150898_a(this.getHalfBlock()), 1, state.func_177230_c().func_176201_c(state.func_177226_a(field_176554_a, EnumBlockHalf.BOTTOM))
      );
   }

   @Override
   public BlockSlab getHalfBlock() {
      return SRPBlocks.ParasiteRubbleSlabHalf;
   }

   @Override
   public BlockSlab getDoubleBlock() {
      return SRPBlocks.ParasiteRubbleSlabDouble;
   }

   public static class BlockSlabRubbleDouble extends BlockSlabRubble {
      public BlockSlabRubbleDouble(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half) {
         super(materialIn, name + "slabdouble", hardness, creative, tickRandom, half);
         SRPBlocks.SRP_BLOCKS.add(this);
         SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
      }

      public boolean func_176552_j() {
         return true;
      }
   }

   public static class BlockSlabRubbleHalf extends BlockSlabRubble {
      public BlockSlabRubbleHalf(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half, BlockSlab doubleSlab) {
         super(materialIn, name + "slabhalf", hardness, creative, tickRandom, half);
         SRPBlocks.SRP_BLOCKS.add(this);
      }

      public boolean func_176552_j() {
         return false;
      }
   }

   public static enum EnumType implements IStringSerializable {
      FLESH,
      BONE,
      STONE,
      STONEDEBRIS,
      WOOD,
      BRICKS,
      METAL,
      OBSIDIAN;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
