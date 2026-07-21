package com.dhanantry.scapeandrunparasites.block;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParasiteBarrier extends BlockBase {
   public BlockParasiteBarrier() {
      super(Material.field_175972_I, "parasite_barrier", -1.0F, true, true, 6000000.0F);
      this.func_149722_s();
      this.func_149752_b(6000000.0F);
      this.func_149672_a(SoundType.field_185851_d);
      this.func_149713_g(255);
   }

   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   @Nullable
   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileEntityParasiteBarrier();
   }

   public void func_176213_c(World w, BlockPos pos, IBlockState state) {
      super.func_176213_c(w, pos, state);
      if (!w.field_72995_K) {
         TileEntity te = w.func_175625_s(pos);
         if (te instanceof TileEntityParasiteBarrier) {
            TileEntityParasiteBarrier b = (TileEntityParasiteBarrier)te;
            b.initCenterFromPos(pos);
            b.scrubMobsAroundField();
         }
      }
   }

   public void func_180663_b(World w, BlockPos pos, IBlockState state) {
      super.func_180663_b(w, pos, state);
      w.func_175713_t(pos);
   }

   public boolean func_180639_a(World w, BlockPos pos, IBlockState st, EntityPlayer pl, EnumHand hand, EnumFacing f, float hitX, float hitY, float hitZ) {
      if (w.field_72995_K) {
         return true;
      } else if (!canConfigure(pl)) {
         if (pl instanceof EntityPlayerMP) {
            TextComponentTranslation msg = new TextComponentTranslation("msg.srparasites.barrier.no_permission", new Object[0]);
            msg.func_150255_a(new Style().func_150238_a(TextFormatting.RED));
            ((EntityPlayerMP)pl).func_145747_a(msg);
         }

         return true;
      } else {
         TileEntity te = w.func_175625_s(pos);
         if (te instanceof TileEntityParasiteBarrier) {
            TileEntityParasiteBarrier b = (TileEntityParasiteBarrier)te;
            int r = b.getRadiusChunks();
            r = r % 10 + 1;
            b.setRadiusChunks(r);
            if (pl instanceof EntityPlayerMP) {
               TextComponentTranslation msg = new TextComponentTranslation("msg.srparasites.barrier.radius_set", new Object[]{r});
               msg.func_150255_a(new Style().func_150238_a(TextFormatting.GREEN));
               ((EntityPlayerMP)pl).func_145747_a(msg);
            }
         }

         return true;
      }
   }

   private static boolean canConfigure(EntityPlayer pl) {
      if (pl.field_71075_bZ.field_75098_d) {
         return true;
      } else {
         return pl instanceof EntityPlayerMP ? ((EntityPlayerMP)pl).func_70003_b(2, "srpbarrier") : false;
      }
   }

   public boolean func_149662_c(IBlockState s) {
      return true;
   }

   public boolean func_149686_d(IBlockState s) {
      return true;
   }

   public boolean isNormalCube(IBlockState s, IBlockAccess w, BlockPos p) {
      return true;
   }

   public boolean func_185481_k(IBlockState s) {
      return true;
   }

   public boolean func_176205_b(IBlockAccess w, BlockPos p) {
      return false;
   }

   @Nullable
   public AxisAlignedBB func_180646_a(IBlockState s, IBlockAccess w, BlockPos p) {
      return field_185505_j;
   }

   public EnumBlockRenderType func_149645_b(IBlockState s) {
      return EnumBlockRenderType.MODEL;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.SOLID;
   }

   public boolean func_176225_a(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      IBlockState adj = world.func_180495_p(pos.func_177972_a(side));
      return adj.func_177230_c() != this || super.func_176225_a(state, world, pos, side);
   }

   public float func_176195_g(IBlockState state, World world, BlockPos pos) {
      return -1.0F;
   }

   public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
      return false;
   }

   public boolean func_149659_a(Explosion explosionIn) {
      return false;
   }

   public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
   }

   public EnumPushReaction func_149656_h(IBlockState state) {
      return EnumPushReaction.BLOCK;
   }

   public static class ItemBlockParasiteBarrier extends ItemBlock {
      public ItemBlockParasiteBarrier(Block block) {
         super(block);
      }

      @SideOnly(Side.CLIENT)
      public void func_77624_a(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
         tooltip.add(TextFormatting.RED + I18n.func_135052_a("tooltip.srparasites.parasite_barrier.creative_only", new Object[0]));
         tooltip.add(TextFormatting.WHITE + I18n.func_135052_a("tooltip.srparasites.parasite_barrier.desc1", new Object[0]));
         tooltip.add(TextFormatting.WHITE + I18n.func_135052_a("tooltip.srparasites.parasite_barrier.desc2", new Object[0]));
      }
   }
}
