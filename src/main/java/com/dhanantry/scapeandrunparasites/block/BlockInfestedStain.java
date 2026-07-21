package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfestedStain extends BlockParasiteSpreading implements IStagedBlock {
   public static final PropertyInteger STAGE = PropertyInteger.func_177719_a("stage", 0, 5);
   public static final PropertyBool SNOWY = PropertyBool.func_177716_a("snowy");

   @Override
   public PropertyInteger getStageProperty() {
      return STAGE;
   }

   public BlockInfestedStain(Material material, String name, float hardness, boolean creative, boolean infested) {
      super(material, name, hardness, creative, infested);
      this.setHarvestLevel("shovel", 0);
      this.func_149672_a(SRPSoundTypes.BECKON);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(STAGE, 0).func_177226_a(SNOWY, Boolean.FALSE));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{STAGE, SNOWY});
   }

   public int func_176201_c(IBlockState state) {
      return (Integer)state.func_177229_b(STAGE);
   }

   public IBlockState func_176203_a(int meta) {
      if (meta >= 5) {
         meta = 5;
      }

      return this.func_176223_P().func_177226_a(STAGE, meta & 7);
   }

   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      IBlockState above = worldIn.func_180495_p(pos.func_177984_a());
      boolean snowy = above.func_177230_c() == Blocks.field_150433_aE || above.func_177230_c() == Blocks.field_150431_aC;
      return state.func_177226_a(SNOWY, snowy);
   }

   @SideOnly(Side.CLIENT)
   public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
      BlockPos above = pos.func_177984_a();
      boolean openAbove = worldIn.func_175623_d(above) || !worldIn.func_180495_p(above).isSideSolid(worldIn, above, EnumFacing.DOWN);
      if (openAbove & rand.nextFloat() < 0.001F) {
         double x = pos.func_177958_n() + 0.5 + rand.nextDouble() * 0.5;
         double y = pos.func_177956_o() + 1.0;
         double z = pos.func_177952_p() + 0.5 + rand.nextDouble() * 0.5;
         ParticleSpawner.spawnParticle(SRPEnumParticle.DOT, x, y, z, 0.0, 0.0, 0.0, 17, 74, 20);
      }

      if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a
         || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == SRPBlocks.ParasiteBush) {
         int stage = this.func_176201_c(stateIn);
         if (stage == 2) {
            if (rand.nextDouble() <= SRPConfigSystems.rsBlockParticleS) {
               double d0 = pos.func_177958_n() + rand.nextDouble();
               double d1 = pos.func_177956_o() + 2.5;
               double d2 = pos.func_177952_p() + rand.nextDouble();
               ParticleSpawner.spawnParticle(SRPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
            }
         } else if (stage == 3) {
            if (rand.nextDouble() <= SRPConfigSystems.rsBlockParticleF) {
               double d0 = pos.func_177958_n() + rand.nextDouble();
               double d1 = pos.func_177956_o() + 1.5;
               double d2 = pos.func_177952_p() + rand.nextDouble();
               int g = 200;
               if (rand.nextInt(3) == 0) {
                  g += 50;
               }

               ParticleSpawner.spawnParticle(SRPEnumParticle.FOG, d0, d1, d2, 0.0, 1.0E-4, 0.0, 0, g, 0);
            }

            if (rand.nextDouble() <= SRPConfigSystems.rsBlockParticleS) {
               double d0 = pos.func_177958_n() + rand.nextDouble();
               double d1 = pos.func_177956_o() + 2.5;
               double d2 = pos.func_177952_p() + rand.nextDouble();
               ParticleSpawner.spawnParticle(SRPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
            }
         }
      }
   }
}
