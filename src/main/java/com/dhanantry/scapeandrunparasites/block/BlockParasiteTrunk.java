package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockParasiteTrunk extends BlockRotatedPillar implements IMetaName {
   public static final PropertyEnum<BlockParasiteTrunk.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockParasiteTrunk.EnumType.class);

   public BlockParasiteTrunk(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
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

      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockParasiteTrunk.EnumType.TREE).func_177226_a(field_176298_M, Axis.Y));
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = this.getItemBlock();
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public int func_180651_a(IBlockState state) {
      return ((BlockParasiteTrunk.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockParasiteTrunk.EnumType variant : BlockParasiteTrunk.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      BlockParasiteTrunk.EnumType[] all = BlockParasiteTrunk.EnumType.values();
      BlockParasiteTrunk.EnumType variant;
      Axis axis;
      if (meta >= 12) {
         int ax = meta - 12;
         variant = all[Math.min(4, all.length - 1)];
         axis = ax == 1 ? Axis.X : (ax == 2 ? Axis.Z : Axis.Y);
      } else {
         variant = all[(meta & 3) % all.length];
         int bits = meta & 12;
         axis = bits == 4 ? Axis.X : (bits == 8 ? Axis.Z : Axis.Y);
      }

      return this.func_176223_P().func_177226_a(VARIANT, variant).func_177226_a(field_176298_M, axis);
   }

   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      BlockParasiteTrunk.EnumType[] all = BlockParasiteTrunk.EnumType.values();
      int idx = Math.min(meta, all.length - 1);
      return this.func_176223_P().func_177226_a(VARIANT, all[idx]).func_177226_a(field_176298_M, facing.func_176740_k());
   }

   public int func_176201_c(IBlockState state) {
      Axis ax = (Axis)state.func_177229_b(field_176298_M);
      int ord = ((BlockParasiteTrunk.EnumType)state.func_177229_b(VARIANT)).ordinal();
      if (ord == 4) {
         int axIdx = ax == Axis.X ? 1 : (ax == Axis.Z ? 2 : 0);
         return 12 + axIdx;
      } else {
         int axisBits = ax == Axis.X ? 4 : (ax == Axis.Z ? 8 : 0);
         return ord & 3 | axisBits;
      }
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, ((BlockParasiteTrunk.EnumType)state.func_177229_b(VARIANT)).ordinal());
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT, field_176298_M});
   }

   protected ItemStack func_180643_i(IBlockState state) {
      return super.func_180643_i(state);
   }

   @Override
   public Enum[] getVariants() {
      return BlockParasiteTrunk.EnumType.values();
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (worldIn.func_175697_a(pos, 3)) {
         if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
            BlockParasiteSpreading.spreadBiomeBlockTrunk(worldIn, pos, rand);
         }
      }
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   public static enum EnumType implements IStringSerializable {
      BALL,
      TREE,
      PLANT,
      CIRCLE,
      DEADHEAD;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
