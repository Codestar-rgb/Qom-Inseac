package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEscaBulb extends BlockBase {
   public BlockEscaBulb(String name) {
      this(name, true);
   }

   public BlockEscaBulb(String name, boolean creative) {
      super(Material.field_151592_s, name, 0.0F, creative, false);
      this.func_149672_a(SRPSoundTypes.FLESH_LIGHT);
      this.func_149715_a(1.0F);
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT;
   }

   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
      if (rand.nextFloat() < 0.5F) {
         double x = pos.func_177958_n() + 0.2 + rand.nextDouble() * 0.6;
         double y = pos.func_177956_o() + 3.05 + rand.nextDouble() * 0.9;
         double z = pos.func_177952_p() + 0.2 + rand.nextDouble() * 0.6;
         ParticleSpawner.spawnParticle(SRPEnumParticle.BLOOD, x, y, z, 0.0, 0.0, 0.0, 0, 0, 0);
      }
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }
}
