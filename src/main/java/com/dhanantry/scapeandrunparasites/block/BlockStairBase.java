package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.IModelSRP;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;

public class BlockStairBase extends BlockStairs implements IModelSRP {
   public BlockStairBase(String name, boolean creative, IBlockState modelState) {
      super(modelState);
      this.setRegistryName(name + "stairs");
      this.func_149663_c("srparasites." + name + "stairs");
      this.func_180632_j(
         this.field_176227_L
            .func_177621_b()
            .func_177226_a(field_176309_a, EnumFacing.NORTH)
            .func_177226_a(field_176308_b, EnumHalf.BOTTOM)
            .func_177226_a(field_176310_M, EnumShape.STRAIGHT)
      );
      this.func_149713_g(255);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public Block setToolStats(String effectiveTool, int toolLevel) {
      this.setHarvestLevel(effectiveTool, toolLevel);
      return this;
   }

   @Override
   public void registerModels() {
      SRPMain.proxy.modelReg(Item.func_150898_a(this), 0, "inventory");
   }
}
