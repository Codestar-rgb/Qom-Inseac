package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTallFlower;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTree;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTreeThin;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockParasiteSapling extends BlockBush implements IGrowable, IMetaName {
   public static final PropertyEnum<BlockParasiteSapling.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockParasiteSapling.EnumType.class);
   public static final PropertyInteger STAGE = PropertyInteger.func_177719_a("stage", 0, 1);
   protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.099999994F, 0.0, 0.099999994F, 0.9F, 0.8F, 0.9F);

   public BlockParasiteSapling(String name) {
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockParasiteSapling.EnumType.TREE).func_177226_a(STAGE, 0));
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = this.getItemBlock();
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      return SAPLING_AABB;
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         super.func_180650_b(worldIn, pos, state, rand);
         if (!worldIn.func_175697_a(pos, 1)) {
            return;
         }

         if (worldIn.func_175671_l(pos.func_177984_a()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(worldIn, pos, state, rand);
         }
      }
   }

   public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if ((Integer)state.func_177229_b(STAGE) == 0) {
         worldIn.func_180501_a(pos, state.func_177231_a(STAGE), 4);
      } else {
         this.generateTree(worldIn, pos, state, rand);
      }
   }

   public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (TerrainGen.saplingGrowTree(worldIn, rand, pos)) {
         WorldGenerator worldgenerator = (WorldGenerator)(rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true));
         int i = 0;
         int j = 0;
         switch ((BlockParasiteSapling.EnumType)state.func_177229_b(VARIANT)) {
            case TREE:
               worldgenerator = new WorldGenParasiteTree(true);
               break;
            case TREETHIN:
               worldgenerator = new WorldGenParasiteTreeThin(true);
               break;
            case FLOWERTALL:
               worldgenerator = new WorldGenParasiteTallFlower(true);
               break;
            case CONSUMED:
               worldgenerator = new WorldGenParasiteTree(true);
               break;
            case DEADHEAD:
               worldgenerator = new WorldGenParasiteTreeThin(true);
               break;
            case INFESTED:
               worldgenerator = new WorldGenParasiteTallFlower(true);
         }

         worldIn.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 4);
         if (!worldgenerator.func_180709_b(worldIn, rand, pos.func_177982_a(i, 0, j))) {
            worldIn.func_180501_a(pos, state, 4);
         }
      }
   }

   public boolean tryGrowWithInfestedBonemeal(World world, BlockPos pos, IBlockState state, Random rand) {
      if ((Integer)state.func_177229_b(STAGE) == 0) {
         return world.func_180501_a(pos, state.func_177226_a(STAGE, 1), 4);
      } else {
         WorldGenerator g;
         switch ((BlockParasiteSapling.EnumType)state.func_177229_b(VARIANT)) {
            case CONSUMED:
               g = new WorldGenParasiteTree(true);
               break;
            case DEADHEAD:
               g = new WorldGenParasiteTreeThin(true);
               break;
            case INFESTED:
               g = new WorldGenParasiteTallFlower(true);
               break;
            default:
               return false;
         }

         world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 4);
         if (!g.func_180709_b(world, rand, pos)) {
            world.func_180501_a(pos, state, 4);
            return false;
         } else {
            return true;
         }
      }
   }

   private boolean generate(World world, BlockPos pos, IBlockState original, Random rand, WorldGenerator g) {
      world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 4);
      if (!g.func_180709_b(world, rand, pos)) {
         world.func_180501_a(pos, original, 4);
         return false;
      } else {
         return true;
      }
   }

   private WorldGenerator pickExistingGeneratorFor(BlockParasiteSapling.EnumType type, Random rand) {
      switch (type) {
         case TREE:
            return new WorldGenParasiteTree(true);
         case TREETHIN:
            return new WorldGenParasiteTreeThin(true);
         case FLOWERTALL:
            return new WorldGenParasiteTallFlower(true);
         default:
            return new WorldGenTrees(true);
      }
   }

   public boolean func_176196_c(World worldIn, BlockPos pos) {
      return this.checkBush(worldIn.func_180495_p(pos.func_177977_b()));
   }

   public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
      return this.checkBush(worldIn.func_180495_p(pos.func_177977_b()));
   }

   protected boolean checkBush(IBlockState state) {
      Block b = state.func_177230_c();
      ResourceLocation id = (ResourceLocation)Block.field_149771_c.func_177774_c(b);
      if (id == null) {
         return false;
      } else {
         return !"srparasites".equals(id.func_110624_b()) ? false : state.func_185917_h() && state.func_185914_p() && b.func_149730_j(state);
      }
   }

   public boolean isTypeAt(World worldIn, BlockPos pos, BlockParasiteSapling.EnumType type) {
      IBlockState iblockstate = worldIn.func_180495_p(pos);
      return iblockstate.func_177230_c() == this && iblockstate.func_177229_b(VARIANT) == type;
   }

   public int func_180651_a(IBlockState state) {
      return ((BlockParasiteSapling.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockParasiteSapling.EnumType blockplanks$enumtype : BlockParasiteSapling.EnumType.values()) {
         items.add(new ItemStack(this, 1, blockplanks$enumtype.ordinal()));
      }
   }

   public boolean func_176473_a(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
      return true;
   }

   public boolean func_180670_a(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      return worldIn.field_73012_v.nextFloat() < 0.45;
   }

   public void func_176474_b(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      this.grow(worldIn, pos, state, rand);
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockParasiteSapling.EnumType.values()[meta & 7]).func_177226_a(STAGE, (meta & 8) >> 3);
   }

   public int func_176201_c(IBlockState state) {
      int i = 0;
      i |= ((BlockParasiteSapling.EnumType)state.func_177229_b(VARIANT)).ordinal();
      return i | (Integer)state.func_177229_b(STAGE) << 3;
   }

   @Override
   public Enum[] getVariants() {
      return BlockParasiteSapling.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT, STAGE});
   }

   public static enum EnumType implements IStringSerializable {
      TREE,
      TREETHIN,
      FLOWERTALL,
      CONSUMED,
      DEADHEAD,
      INFESTED;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
