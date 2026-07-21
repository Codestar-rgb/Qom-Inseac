package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockRelay extends Block {
   public BlockRelay(String name) {
      super(Material.field_151573_f);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149647_a(CreativeTabs.field_78028_d);
      this.func_149711_c(2.0F);
      this.func_149752_b(10.0F);
      SRPBlocks.SRP_BLOCKS.add(this);
   }

   public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      if (!world.field_72995_K) {
         this.tryFormRelay(world, pos, placer);
      }

      super.func_180633_a(world, pos, state, placer, stack);
   }

   public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (!world.field_72995_K) {
         this.tryFormRelay(world, pos, null);
      }

      super.func_189540_a(state, world, pos, blockIn, fromPos);
   }

   public boolean func_180639_a(
      World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      if (!world.field_72995_K) {
         if (this.isStructureComplete(world, pos)) {
            List<EntityLiving> hostileMobs = world.func_175644_a(EntityLiving.class, e -> e.isCreatureType(EnumCreatureType.MONSTER, false));
            int parasiteHostileCount = 0;

            for (EntityLiving mob : hostileMobs) {
               ResourceLocation rl = EntityList.func_191301_a(mob);
               if (rl != null && "srparasites".equals(rl.func_110624_b())) {
                  parasiteHostileCount++;
               }
            }

            double percentage = hostileMobs.isEmpty() ? 0.0 : parasiteHostileCount * 100.0 / hostileMobs.size();
            player.func_145747_a(
               new TextComponentString(
                  String.format("§a[DEBUG] Hostile mobs: %d | Parasite hostiles: %d (%.1f%%)", hostileMobs.size(), parasiteHostileCount, percentage)
               )
            );
         } else {
            player.func_145747_a(new TextComponentString("§c[DEBUG] Structure incomplete."));
         }
      }

      return true;
   }

   private void tryFormRelay(World world, BlockPos pos, @Nullable EntityLivingBase placer) {
      BlockPos[] candidateMiddles = new BlockPos[]{pos, pos.func_177984_a(), pos.func_177977_b()};

      for (BlockPos mid : candidateMiddles) {
         BlockPos bottom = mid.func_177977_b();
         BlockPos top = mid.func_177984_a();
         IBlockState sBottom = world.func_180495_p(bottom);
         IBlockState sMiddle = world.func_180495_p(mid);
         IBlockState sTop = world.func_180495_p(top);
         if (sBottom.func_177230_c() == SRPBlocks.RelayBase && sMiddle.func_177230_c() == SRPBlocks.RelayMiddle && sTop.func_177230_c() == SRPBlocks.RelayRoof) {
            EnumFacing facing = EnumFacing.NORTH;
            IBlockState controllerState = SRPBlocks.RELAY_CONTROLLER.func_176223_P().func_177226_a(BlockRelayController.FACING, facing);
            world.func_175655_b(mid, false);
            world.func_175655_b(top, false);
            world.func_180501_a(bottom, controllerState, 2);
            ((BlockRelayController)SRPBlocks.RELAY_CONTROLLER).func_180633_a(world, bottom, controllerState, placer, ItemStack.field_190927_a);
            return;
         }
      }
   }

   private boolean isStructureComplete(World world, BlockPos pos) {
      BlockPos bottom = this.findBottom(world, pos);
      Block base = SRPBlocks.RelayBase;
      Block middle = SRPBlocks.RelayMiddle;
      Block roof = SRPBlocks.RelayRoof;
      boolean isBase = world.func_180495_p(bottom).func_177230_c() == base;
      boolean isMiddle = world.func_180495_p(bottom.func_177984_a()).func_177230_c() == middle;
      boolean isRoof = world.func_180495_p(bottom.func_177981_b(2)).func_177230_c() == roof;
      return isBase && isMiddle && isRoof;
   }

   private BlockPos findBottom(World world, BlockPos pos) {
      while (world.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockRelay) {
         pos = pos.func_177977_b();
      }

      return pos;
   }

   private static class FakePlacer extends EntityLivingBase {
      protected FakePlacer(World worldIn) {
         super(worldIn);
      }

      public Iterable<ItemStack> func_184193_aE() {
         return Collections.emptyList();
      }

      public ItemStack func_184582_a(EntityEquipmentSlot slotIn) {
         return ItemStack.field_190927_a;
      }

      public void func_184201_a(EntityEquipmentSlot slotIn, ItemStack stack) {
      }

      public EnumHandSide func_184591_cq() {
         return EnumHandSide.RIGHT;
      }
   }
}
