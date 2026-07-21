package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockHarleskinnSlab extends BlockSlab {
   public static final PropertyEnum<BlockHarleskinnSlab.Variant> VARIANT = PropertyEnum.func_177709_a("variant", BlockHarleskinnSlab.Variant.class);
   private final boolean doubleSlab;
   private final boolean dropsItems;
   private BlockSlab halfPartner;
   private BlockSlab doublePartner;

   public BlockHarleskinnSlab(boolean isDouble, String name, String toolClass, int harvestLevel, SoundType soundType) {
      this(isDouble, name, toolClass, harvestLevel, soundType, 2.0F, 3.0F, true);
   }

   public BlockHarleskinnSlab(
      boolean isDouble, String name, String toolClass, int harvestLevel, SoundType soundType, float hardness, float resistance, boolean dropsItems
   ) {
      super(Material.field_151575_d);
      this.doubleSlab = isDouble;
      this.dropsItems = dropsItems;
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149752_b(resistance);
      if (toolClass != null) {
         this.setHarvestLevel(toolClass, harvestLevel);
      }

      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      IBlockState base = this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockHarleskinnSlab.Variant.DEFAULT);
      this.func_180632_j(isDouble ? base : base.func_177226_a(field_176554_a, EnumBlockHalf.BOTTOM));
      this.func_149675_a(true);
      SRPBlocks.SRP_BLOCKS.add(this);
   }

   public BlockHarleskinnSlab(
      boolean isDouble,
      String name,
      String toolClass,
      int harvestLevel,
      SoundType soundType,
      float hardness,
      float resistance,
      boolean dropsItems,
      BlockSlab partnerDouble
   ) {
      this(isDouble, name, toolClass, harvestLevel, soundType, hardness, resistance, dropsItems);
      if (!this.doubleSlab) {
         this.halfPartner = this;
         this.doublePartner = Objects.requireNonNull(partnerDouble, "partnerDouble");
         Item item = (Item)new ItemSlab(this, this, this.doublePartner).setRegistryName(Objects.requireNonNull(this.getRegistryName()));
         item.func_77637_a(SRPMain.SRP_CREATIVETAB);
         SRPItems.SRP_ITEMS.add(item);
      }
   }

   public BlockHarleskinnSlab setHalfPartner(BlockSlab half) {
      this.halfPartner = half;
      return this;
   }

   public boolean func_176552_j() {
      return this.doubleSlab;
   }

   public IProperty<?> func_176551_l() {
      return VARIANT;
   }

   public Comparable<?> func_185674_a(ItemStack stack) {
      return BlockHarleskinnSlab.Variant.DEFAULT;
   }

   protected BlockStateContainer func_180661_e() {
      return this.func_176552_j()
         ? new BlockStateContainer(this, new IProperty[]{VARIANT})
         : new BlockStateContainer(this, new IProperty[]{VARIANT, field_176554_a});
   }

   public IBlockState func_176203_a(int meta) {
      IBlockState s = this.func_176223_P().func_177226_a(VARIANT, BlockHarleskinnSlab.Variant.DEFAULT);
      if (!this.func_176552_j()) {
         s = s.func_177226_a(field_176554_a, (meta & 8) != 0 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
      }

      return s;
   }

   public int func_176201_c(IBlockState state) {
      int m = 0;
      if (!this.func_176552_j() && state.func_177229_b(field_176554_a) == EnumBlockHalf.TOP) {
         m |= 8;
      }

      return m;
   }

   public String func_150002_b(int meta) {
      return this.func_149739_a();
   }

   private BlockSlab halfForDrops() {
      if (this.halfPartner != null) {
         return this.halfPartner;
      } else {
         return this.doubleSlab ? null : this;
      }
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      if (!this.dropsItems) {
         return Items.field_190931_a;
      } else {
         BlockSlab half = this.halfForDrops();
         return half != null ? Item.func_150898_a(half) : Items.field_190931_a;
      }
   }

   public int func_149745_a(Random rand) {
      if (!this.dropsItems) {
         return 0;
      } else {
         return this.func_176552_j() ? 2 : 1;
      }
   }

   protected ItemStack func_180643_i(IBlockState state) {
      if (!this.dropsItems) {
         return ItemStack.field_190927_a;
      } else {
         BlockSlab half = this.halfForDrops();
         return half != null ? new ItemStack(Item.func_150898_a(half)) : ItemStack.field_190927_a;
      }
   }

   public ItemStack getPickBlock(IBlockState s, RayTraceResult t, World w, BlockPos p, EntityPlayer pl) {
      BlockSlab half = this.halfForDrops();
      return half != null ? new ItemStack(Item.func_150898_a(half)) : ItemStack.field_190927_a;
   }

   public ItemStack func_185473_a(World w, BlockPos p, IBlockState s) {
      BlockSlab half = this.halfForDrops();
      return half != null ? new ItemStack(Item.func_150898_a(half)) : ItemStack.field_190927_a;
   }

   public int func_180651_a(IBlockState state) {
      return 0;
   }

   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
      super.func_176213_c(worldIn, pos, state);
      if (!worldIn.field_72995_K) {
         worldIn.func_175684_a(pos, this, 10);
      }
   }

   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
      if (!worldIn.field_72995_K) {
         worldIn.func_175684_a(pos, this, 10);
      }
   }

   public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      this.func_180650_b(worldIn, pos, state, rand);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         if (touchingAnyInfestation(worldIn, pos)) {
            BeckonBlockInfestation.beckonInfestation(worldIn, pos, rand, 1, false);
            worldIn.func_175684_a(pos, this, 20);
         }
      }
   }

   private static boolean touchingAnyInfestation(World worldIn, BlockPos pos) {
      for (int dir = 0; dir <= 5; dir++) {
         BlockPos helper = BlockParasiteSpreading.directionToSpread(pos, dir);
         IBlockState st = worldIn.func_180495_p(helper);
         Block b = st.func_177230_c();
         if (b instanceof IStagedBlock) {
            return true;
         }

         ResourceLocation rl = b.getRegistryName();
         if (rl != null && "srparasites".equals(rl.func_110624_b())) {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            if (path.contains("infest")) {
               return true;
            }
         }
      }

      return false;
   }

   public static enum Variant implements IStringSerializable {
      DEFAULT;

      public String func_176610_l() {
         return "default";
      }
   }
}
