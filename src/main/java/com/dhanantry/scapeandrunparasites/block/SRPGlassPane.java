package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SRPGlassPane extends BlockPane {
   public SRPGlassPane(String name) {
      this(name, true, true);
   }

   public SRPGlassPane(String name, boolean creative, boolean dropsSelf) {
      super(Material.field_151592_s, dropsSelf);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(0.3F);
      this.func_149672_a(SoundType.field_185853_f);
      this.func_149713_g(0);
      this.func_149675_a(true);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = new ItemBlock(this);
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   @SideOnly(Side.CLIENT)
   public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
      return layer == BlockRenderLayer.TRANSLUCENT;
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   protected boolean func_149700_E() {
      return true;
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         int stage = 0;
         BeckonBlockInfestation.beckonInfestation(worldIn, pos, rand, stage, false);
      }
   }

   public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      this.func_180650_b(worldIn, pos, state, rand);
   }
}
