package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedBush;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedRemain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.util.ArrayDeque;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemGreekFire extends ItemBase {
   private static final int MAX_SPREAD = 12000;
   private static final int LINK_RADIUS = 8;
   private static final int PARTICLE_EVERY = 8;
   private static final int MAX_PARTICLE_SPAWNS = 24;

   public ItemGreekFire(String name, int maxStack, byte id) {
      super(name, maxStack, id);
      this.field_77777_bU = 1;
      this.func_77656_e(4);
   }

   public boolean func_77645_m() {
      return true;
   }

   public EnumActionResult func_180614_a(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (world.field_72995_K) {
         return EnumActionResult.SUCCESS;
      } else {
         IBlockState state = world.func_180495_p(pos);
         Block block = state.func_177230_c();
         if (!this.isTargetBush(block)) {
            return EnumActionResult.FAIL;
         } else {
            this.burnBushCluster(world, pos);
            world.func_184133_a(null, pos, SoundEvents.field_187616_bj, SoundCategory.BLOCKS, 1.0F, 0.9F + world.field_73012_v.nextFloat() * 0.2F);
            if (!player.func_184812_l_()) {
               player.func_184586_b(hand).func_77972_a(1, player);
            }

            return EnumActionResult.SUCCESS;
         }
      }
   }

   private void burnBushCluster(World world, BlockPos origin) {
      ArrayDeque<BlockPos> queue = new ArrayDeque<>();
      LongOpenHashSet visited = new LongOpenHashSet();
      queue.add(origin);
      visited.add(packPos(origin.func_177958_n(), origin.func_177956_o(), origin.func_177952_p()));
      int burned = 0;
      int particleBursts = 0;
      MutableBlockPos scanPos = new MutableBlockPos();

      while (!queue.isEmpty() && burned < 12000) {
         BlockPos current = queue.poll();
         IBlockState state = world.func_180495_p(current);
         Block block = state.func_177230_c();
         if (this.isTargetBush(block)) {
            world.func_175698_g(current);
            if (++burned % 8 == 0 && particleBursts < 24) {
               this.spawnSmoke(world, current);
               particleBursts++;
            }

            int cx = current.func_177958_n();
            int cy = current.func_177956_o();
            int cz = current.func_177952_p();

            for (int dx = -8; dx <= 8; dx++) {
               for (int dy = -8; dy <= 8; dy++) {
                  for (int dz = -8; dz <= 8; dz++) {
                     if (dx != 0 || dy != 0 || dz != 0) {
                        int nx = cx + dx;
                        int ny = cy + dy;
                        int nz = cz + dz;
                        if (ny >= 0 && ny <= 255) {
                           long key = packPos(nx, ny, nz);
                           if (!visited.contains(key)) {
                              visited.add(key);
                              scanPos.func_181079_c(nx, ny, nz);
                              if (this.isTargetBush(world.func_180495_p(scanPos).func_177230_c())) {
                                 queue.add(new BlockPos(nx, ny, nz));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (burned > 0 && particleBursts == 0) {
         this.spawnSmoke(world, origin);
      }
   }

   private void spawnSmoke(World world, BlockPos pos) {
      if (world instanceof WorldServer) {
         WorldServer ws = (WorldServer)world;
         double x = pos.func_177958_n() + 0.5;
         double y = pos.func_177956_o() + 0.5;
         double z = pos.func_177952_p() + 0.5;
         ws.func_175739_a(EnumParticleTypes.CLOUD, x, y, z, 4, 0.25, 0.25, 0.25, 0.01, new int[0]);
         ws.func_175739_a(EnumParticleTypes.SMOKE_LARGE, x, y, z, 2, 0.2, 0.2, 0.2, 0.01, new int[0]);
      }
   }

   private boolean isTargetBush(Block block) {
      return block instanceof BlockParasiteBush || block instanceof BlockInfestedBush || block instanceof BlockInfestedRemain || block instanceof BlockGore;
   }

   private static long packPos(int x, int y, int z) {
      return (long)(x & 67108863) << 38 | (long)(y & 4095) << 26 | z & 67108863;
   }
}
