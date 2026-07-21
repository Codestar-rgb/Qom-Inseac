package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEpitomeInfestationWarpDiffuser extends BlockBase {
   private static final int RADIUS = 256;
   private static final int MAX_BLOCKS_PER_TICK = 8192;
   private static final int MAX_BLOCKS_TOTAL = 2000000;
   private static final Map<String, BlockEpitomeInfestationWarpDiffuser.DiffusionJob> JOBS = new HashMap<>();

   public BlockEpitomeInfestationWarpDiffuser() {
      super(Material.field_151576_e, "epitome_infestation_warp_diffuser", 15.0F, true, false, 120.0F);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_149715_a(0.4F);
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      if (worldIn.field_72995_K) {
         return true;
      } else {
         String key = getJobKey(worldIn, pos);
         if (JOBS.containsKey(key)) {
            playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.diffuser.already_running", new Object[0]), true);
            return true;
         } else {
            BlockEpitomeInfestationWarpDiffuser.DiffusionJob job = new BlockEpitomeInfestationWarpDiffuser.DiffusionJob(pos);
            JOBS.put(key, job);
            worldIn.func_175684_a(pos, this, 1);
            playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.diffuser.started", new Object[0]), true);
            return true;
         }
      }
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         String key = getJobKey(worldIn, pos);
         BlockEpitomeInfestationWarpDiffuser.DiffusionJob job = JOBS.get(key);
         if (job != null) {
            int removedThisTick = this.processJob(worldIn, job);
            if (!job.finished && !job.queue.isEmpty() && job.removed < 2000000) {
               worldIn.func_175684_a(pos, this, 1);
            } else {
               JOBS.remove(key);
               ParasiteEventWorld.checkNodeStatus(worldIn);
               ParasiteEventWorld.checkColonyStatus(worldIn);
               if (job.removed > 0) {
                  worldIn.func_180498_a(null, 2001, pos, func_176210_f(state));
               }
            }
         }
      }
   }

   private int processJob(World world, BlockEpitomeInfestationWarpDiffuser.DiffusionJob job) {
      int removedThisTick = 0;

      while (!job.queue.isEmpty() && removedThisTick < 8192 && job.removed < 2000000) {
         BlockPos current = BlockPos.func_177969_a(job.queue.poll());

         for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
               for (int z = -1; z <= 1; z++) {
                  if (x != 0 || y != 0 || z != 0) {
                     BlockPos next = current.func_177982_a(x, y, z);
                     long nextLong = next.func_177986_g();
                     if (!job.visited.contains(nextLong)) {
                        job.visited.add(nextLong);
                        if (this.isWithinRadius(job.origin, next) && world.func_175667_e(next)) {
                           IBlockState nextState = world.func_180495_p(next);
                           if (this.isRemovableSRPBlock(nextState)) {
                              world.func_180501_a(next, Blocks.field_150350_a.func_176223_P(), 3);
                              job.queue.add(nextLong);
                              job.removed++;
                              if (++removedThisTick >= 8192 || job.removed >= 2000000) {
                                 return removedThisTick;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      job.finished = job.queue.isEmpty();
      return removedThisTick;
   }

   private boolean isWithinRadius(BlockPos origin, BlockPos pos) {
      return Math.abs(pos.func_177958_n() - origin.func_177958_n()) <= 256
         && Math.abs(pos.func_177956_o() - origin.func_177956_o()) <= 256
         && Math.abs(pos.func_177952_p() - origin.func_177952_p()) <= 256;
   }

   private boolean isRemovableSRPBlock(IBlockState state) {
      if (state.func_177230_c() == this) {
         return false;
      } else {
         ResourceLocation id = state.func_177230_c().getRegistryName();
         return id != null && "srparasites".equals(id.func_110624_b());
      }
   }

   private static String getJobKey(World world, BlockPos pos) {
      return world.field_73011_w.getDimension() + ":" + pos.func_177986_g();
   }

   @SideOnly(Side.CLIENT)
   public void func_190948_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(I18n.func_135052_a("tooltip.srparasites.epitome_infestation_warp_diffuser.desc", new Object[0]));
      tooltip.add(I18n.func_135052_a("tooltip.srparasites.epitome_infestation_warp_diffuser.desc2", new Object[0]));
   }

   private static class DiffusionJob {
      private final BlockPos origin;
      private final Queue<Long> queue = new ArrayDeque<>();
      private final Set<Long> visited = new HashSet<>();
      private int removed;
      private boolean finished;

      private DiffusionJob(BlockPos origin) {
         this.origin = origin;
         this.queue.add(origin.func_177986_g());
         this.visited.add(origin.func_177986_g());
         this.removed = 0;
         this.finished = false;
      }
   }
}
