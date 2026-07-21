package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityNodeRelay;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRelayController extends Block {
   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", Plane.HORIZONTAL);

   public BlockRelayController(String name) {
      super(Material.field_151573_f);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(3.0F);
      this.func_149752_b(10.0F);
      this.func_149713_g(0);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      SRPBlocks.SRP_BLOCKS.add(this);
   }

   public EnumBlockRenderType func_149645_b(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(FACING, EnumFacing.func_176731_b(meta & 3));
   }

   @SideOnly(Side.CLIENT)
   public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, ParticleManager manager) {
      return true;
   }

   public int func_176201_c(IBlockState state) {
      return ((EnumFacing)state.func_177229_b(FACING)).func_176736_b();
   }

   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      return this.func_176223_P().func_177226_a(FACING, EnumFacing.NORTH);
   }

   private static void spawnBreakSmoke(World world, BlockPos pos) {
      if (!world.field_72995_K) {
         if (world instanceof WorldServer) {
            WorldServer ws = (WorldServer)world;
            Random r = world.field_73012_v;

            for (int dx = -1; dx <= 1; dx++) {
               for (int dz = -1; dz <= 1; dz++) {
                  int puffs = dx == 0 && dz == 0 ? 10 : 6;

                  for (int i = 0; i < puffs; i++) {
                     double x = pos.func_177958_n() + 0.5 + dx + (r.nextDouble() - 0.5) * 0.9;
                     double y = pos.func_177956_o() + 0.05 + r.nextDouble() * 0.3;
                     double z = pos.func_177952_p() + 0.5 + dz + (r.nextDouble() - 0.5) * 0.9;
                     double vx = (r.nextDouble() - 0.5) * 0.04;
                     double vy = 0.08 + r.nextDouble() * 0.06;
                     double vz = (r.nextDouble() - 0.5) * 0.04;
                     ws.func_175739_a(EnumParticleTypes.SMOKE_LARGE, x, y, z, 1, vx, vy, vz, 0.0, new int[0]);
                  }
               }
            }

            ws.func_175739_a(
               EnumParticleTypes.SMOKE_LARGE,
               pos.func_177958_n() + 0.5,
               pos.func_177956_o() + 1.0,
               pos.func_177952_p() + 0.5,
               10,
               0.35,
               0.45,
               0.35,
               0.0,
               new int[0]
            );
            ws.func_175739_a(
               EnumParticleTypes.SMOKE_LARGE,
               pos.func_177958_n() + 0.5,
               pos.func_177956_o() + 1.6,
               pos.func_177952_p() + 0.5,
               6,
               0.25,
               0.35,
               0.25,
               0.0,
               new int[0]
            );
         }
      }
   }

   public void func_180663_b(World world, BlockPos pos, IBlockState state) {
      spawnBreakSmoke(world, pos);
      super.func_180663_b(world, pos, state);
   }

   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileEntityRelayController();
   }

   public EnumOffsetType func_176218_Q() {
      return EnumOffsetType.NONE;
   }

   public boolean func_180639_a(
      World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      if (world.field_72995_K) {
         return true;
      } else {
         player.openGui("srparasites", 0, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
         return true;
      }
   }

   public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      if (!world.field_72995_K) {
         TileEntity te = world.func_175625_s(pos);
         if (te instanceof TileEntityRelayController) {
            TileEntityRelayController ctrl = (TileEntityRelayController)te;
            EnumFacing facing = (EnumFacing)state.func_177229_b(FACING);
            List<BlockPos> nodes = this.computeNodePositions(pos, facing);
            List<BlockPos> toClear = new ArrayList<>();

            for (BlockPos p : nodes) {
               if (!world.func_175623_d(p)) {
                  IBlockState s = world.func_180495_p(p);
                  Block b = s.func_177230_c();
                  if (!b.func_176200_f(world, p) && !s.func_185904_a().func_76222_j()) {
                     if (placer instanceof EntityPlayer) {
                        ((EntityPlayer)placer).func_146105_b(new TextComponentTranslation("block.srparasites.relay_controller.no_space", new Object[0]), true);
                     }

                     world.func_175655_b(pos, true);
                     return;
                  }

                  toClear.add(p);
               }
            }

            for (BlockPos px : toClear) {
               world.func_175698_g(px);
            }

            for (BlockPos px : nodes) {
               world.func_180501_a(px, SRPBlocks.NODE_RELAY.func_176223_P(), 2);
               TileEntity nte = world.func_175625_s(px);
               if (nte instanceof TileEntityNodeRelay) {
                  ((TileEntityNodeRelay)nte).setControllerPos(pos);
               }
            }

            ctrl.setChildPositions(nodes);
            ctrl.setFormed(true);
            ctrl.func_70296_d();
         }
      }
   }

   public void func_176208_a(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (!world.field_72995_K) {
         TileEntity te = world.func_175625_s(pos);
         if (te instanceof TileEntityRelayController) {
            ((TileEntityRelayController)te).dismantle();
         }
      }

      super.func_176208_a(world, pos, state, player);
   }

   private List<BlockPos> computeNodePositions(BlockPos origin, EnumFacing facingIgnored) {
      List<BlockPos> out = new ArrayList<>();
      List<BlockPos> rel = new ArrayList<>();

      for (int dx = -1; dx <= 1; dx++) {
         for (int dz = -1; dz <= 1; dz++) {
            if (dx != 0 || dz != 0) {
               rel.add(new BlockPos(dx, 0, dz));
            }
         }
      }

      for (int y = 1; y <= 3; y++) {
         for (int dx = -1; dx <= 1; dx++) {
            for (int dzx = -1; dzx <= 1; dzx++) {
               rel.add(new BlockPos(dx, y, dzx));
            }
         }
      }

      for (int y = 4; y <= 6; y++) {
         rel.add(new BlockPos(0, y, 0));
      }

      for (BlockPos r : rel) {
         out.add(origin.func_177982_a(r.func_177958_n(), r.func_177956_o(), r.func_177952_p()));
      }

      return out;
   }
}
