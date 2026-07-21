package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.fx.ClientSRPParticles;
import com.dhanantry.scapeandrunparasites.client.fx.ParticleInfestedLeaf;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeafLike extends Block implements IShearable {
   public static final PropertyInteger DECAY_AGE = PropertyInteger.func_177719_a("decay_age", 0, 5);
   private static final int BASE_FALSE_APPLE_CHANCE = 40;

   public BlockLeafLike(String regName) {
      super(Material.field_151584_j);
      this.setRegistryName(regName);
      this.func_149663_c("srparasites." + regName);
      this.func_149711_c(0.2F);
      this.func_149672_a(SoundType.field_185850_c);
      this.func_149713_g(0);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149675_a(true);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(DECAY_AGE, 0));
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
   }

   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.field_72995_K) {
         if (this.touchingAnySRP(world, pos)) {
            int age = (Integer)state.func_177229_b(DECAY_AGE);
            if (age > 0 && rand.nextInt(6) == 0) {
               world.func_180501_a(pos, state.func_177226_a(DECAY_AGE, age - 1), 2);
            }
         } else {
            int age = (Integer)state.func_177229_b(DECAY_AGE);
            if (rand.nextInt(12) == 0) {
               if (age < 5) {
                  world.func_180501_a(pos, state.func_177226_a(DECAY_AGE, age + 1), 2);
               } else {
                  world.func_175655_b(pos, true);
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
      if (world.field_72995_K) {
         if (world.func_175623_d(pos.func_177977_b())) {
            if (rand.nextInt(14) == 0) {
               if (ClientSRPParticles.canSpawn()) {
                  double x = pos.func_177958_n() + 0.2 + rand.nextDouble() * 0.6;
                  double y = pos.func_177956_o() + 0.95;
                  double z = pos.func_177952_p() + 0.2 + rand.nextDouble() * 0.6;
                  Particle p = new ParticleInfestedLeaf(world, x, y, z);
                  ClientSRPParticles.fx().func_78873_a(p);
                  ClientSRPParticles.onSpawn();
               }
            }
         }
      }
   }

   private boolean touchingAnySRP(World world, BlockPos pos) {
      for (EnumFacing f : EnumFacing.field_82609_l) {
         BlockPos n = pos.func_177972_a(f);
         IBlockState s = world.func_180495_p(n);
         Block b = s.func_177230_c();
         ResourceLocation key = (ResourceLocation)Block.field_149771_c.func_177774_c(b);
         if (key != null && "srparasites".equals(key.func_110624_b())) {
            return true;
         }
      }

      return false;
   }

   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      int chance = Math.max(1, 40 - 8 * fortune);
      Random rand = world instanceof World ? ((World)world).field_73012_v : new Random();
      if (rand.nextInt(chance) == 0) {
         drops.add(new ItemStack(SRPItems.falseapple));
      }
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return null;
   }

   public int func_149745_a(Random random) {
      return 0;
   }

   public int func_149679_a(int fortune, Random random) {
      return 0;
   }

   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      return false;
   }

   public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
      return item.func_77973_b() instanceof ItemShears || item.func_77973_b() == Items.field_151097_aZ;
   }

   public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return true;
   }

   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return 60;
   }

   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return 30;
   }

   public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
      List<ItemStack> ret = new ArrayList<>();
      ret.add(new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(((World)world).func_180495_p(pos))));
      return ret;
   }

   public void func_180657_a(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack tool) {
      if (!world.field_72995_K && tool != null && this.isShearable(tool, world, pos)) {
         for (ItemStack drop : this.onSheared(tool, world, pos, 0)) {
            func_180635_a(world, pos, drop);
         }

         if (tool.func_77973_b() instanceof ItemShears) {
            tool.func_77972_a(1, player);
         }

         player.func_71029_a(StatList.func_188055_a(this));
         world.func_175698_g(pos);
      } else {
         super.func_180657_a(world, player, pos, state, te, tool);
      }
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{DECAY_AGE});
   }

   public IBlockState func_176203_a(int meta) {
      int age = Math.max(0, Math.min(5, meta & 7));
      return this.func_176223_P().func_177226_a(DECAY_AGE, age);
   }

   public int func_176201_c(IBlockState state) {
      return (Integer)state.func_177229_b(DECAY_AGE);
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   @SideOnly(Side.CLIENT)
   public boolean func_176225_a(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      IBlockState neighbor = world.func_180495_p(pos.func_177972_a(side));
      return neighbor.func_177230_c() != this || !neighbor.func_185914_p();
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }
}
