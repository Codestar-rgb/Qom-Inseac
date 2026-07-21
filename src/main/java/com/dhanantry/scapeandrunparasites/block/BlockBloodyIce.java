package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBloodyIce extends BlockParasiteSpreading {
   private final boolean ignoreSimilarity;

   public BlockBloodyIce(Material material, String name, float hardness, boolean creative, boolean infested) {
      super(material, name, hardness, creative, infested);
      this.field_149765_K = 0.98F;
      this.func_149672_a(SoundType.field_185853_f);
      this.ignoreSimilarity = false;
   }

   public BlockBloodyIce(Material material, String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
      this(material, name, hardness, creative, tickRandom);
      this.func_149752_b(resistance);
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public int func_149745_a(Random random) {
      return 0;
   }

   public EnumPushReaction func_149656_h(IBlockState state) {
      return EnumPushReaction.NORMAL;
   }

   protected boolean func_149700_E() {
      return true;
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      IBlockState iblockstate = blockAccess.func_180495_p(pos.func_177972_a(side));
      Block block = iblockstate.func_177230_c();
      if (block == this) {
         return false;
      } else {
         return !this.ignoreSimilarity && block == this ? false : super.func_176225_a(blockState, blockAccess, pos, side);
      }
   }

   public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
      super.func_180658_a(worldIn, pos, entityIn, fallDistance);
      if (!worldIn.field_72995_K) {
         if (entityIn instanceof EntityPlayer) {
            if (SRPConfigWorld.bloodyIceBreakOnHardLanding) {
               if (!(fallDistance < SRPConfigWorld.bloodyIceBreakFallDistance)) {
                  int diameter = SRPConfigWorld.bloodyIceBreakDiameter;
                  if (diameter < 1) {
                     diameter = 1;
                  }

                  int radius = (diameter - 1) / 2;

                  for (int dx = -radius; dx <= radius; dx++) {
                     for (int dz = -radius; dz <= radius; dz++) {
                        BlockPos p = pos.func_177982_a(dx, 0, dz);
                        IBlockState st = worldIn.func_180495_p(p);
                        if (st.func_177230_c() == this) {
                           worldIn.func_175718_b(2001, p, Block.func_176210_f(st));
                           worldIn.func_175655_b(p, true);
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
