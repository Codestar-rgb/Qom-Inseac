package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLipomaMass extends BlockBush {
   public BlockLipomaMass(String name) {
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_149711_c(0.0F);
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   private boolean isSRPBlock(Block b) {
      ResourceLocation rl = b.getRegistryName();
      return rl != null && rl.func_110624_b().equals("srparasites");
   }

   public boolean func_176196_c(World w, BlockPos pos) {
      BlockPos up = pos.func_177984_a();
      IBlockState above = w.func_180495_p(up);
      return this.isSRPBlock(above.func_177230_c()) && w.isSideSolid(up, EnumFacing.DOWN, true);
   }

   public void func_189540_a(IBlockState state, World w, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (!this.func_180671_f(w, pos, state)) {
         w.func_175655_b(pos, true);
      }
   }

   public boolean func_180671_f(World w, BlockPos pos, IBlockState state) {
      BlockPos up = pos.func_177984_a();
      IBlockState above = w.func_180495_p(up);
      return this.isSRPBlock(above.func_177230_c()) && w.isSideSolid(up, EnumFacing.DOWN, true);
   }

   public boolean func_149662_c(IBlockState s) {
      return false;
   }

   public boolean func_149686_d(IBlockState s) {
      return false;
   }

   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT;
   }
}
