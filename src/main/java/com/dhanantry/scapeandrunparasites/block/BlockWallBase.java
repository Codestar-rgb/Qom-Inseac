package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWallBase extends BlockWall {
   public BlockWallBase(String name, boolean creative, Block modelBlock) {
      super(modelBlock);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      ItemBlock itemBlock = new ItemBlock(this);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
         itemBlock.func_77637_a(SRPMain.SRP_CREATIVETAB);
      }

      this.func_149675_a(true);
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> items) {
      CreativeTabs myTab = this.func_149708_J();
      if (tab == CreativeTabs.field_78027_g || tab == myTab) {
         items.add(new ItemStack(this));
      }
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

   public int func_180651_a(IBlockState state) {
      return 0;
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P();
   }

   public int func_176201_c(IBlockState state) {
      return 0;
   }
}
