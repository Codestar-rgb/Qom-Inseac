package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGore extends BlockBush implements IMetaName {
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.099999994F, 0.0, 0.099999994F, 0.9F, 0.8F, 0.9F);
   public static final PropertyEnum<BlockGore.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockGore.EnumType.class);

   public BlockGore(String name) {
      super(Material.field_151582_l);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockGore.EnumType.SMALL));
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149675_a(true);
      this.func_149711_c(0.4F);
      this.setHarvestLevel("shovel", 0);
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = this.getItemBlock();
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      BlockGore.EnumType variant = (BlockGore.EnumType)state.func_177229_b(VARIANT);
      return TALL_GRASS_AABB;
   }

   public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
      return this.checkBush(worldIn.func_180495_p(pos.func_177977_b()));
   }

   public boolean func_176196_c(World worldIn, BlockPos pos) {
      return this.checkBush(worldIn.func_180495_p(pos.func_177977_b()));
   }

   protected boolean checkBush(IBlockState state) {
      return state.func_185917_h();
   }

   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
      if (!this.func_180671_f(worldIn, fromPos, state)) {
         worldIn.func_175698_g(pos);
      }
   }

   public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      if (!worldIn.field_72995_K) {
         if (worldIn.field_73012_v.nextDouble() < 0.5 && entityIn.field_70173_aa % 20 != 0) {
            return;
         }

         if (!(entityIn instanceof EntityParasiteBase) && entityIn instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)entityIn;
            if (!target.func_70644_a(SRPPotions.COTH_E) && !target.func_70644_a(SRPPotions.EPEL_E)) {
               target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
            }
         }
      }

      super.func_180634_a(worldIn, pos, state, entityIn);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         int air = 10;
         BlockGore.EnumType c = (BlockGore.EnumType)state.func_177229_b(VARIANT);
         if (c == BlockGore.EnumType.BIG) {
            air = 45;
         }

         if (worldIn.field_73012_v.nextInt(air) == 0) {
            worldIn.func_175698_g(pos);
         }
      }
   }

   public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
      super.func_176199_a(worldIn, pos, entityIn);
   }

   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) {
      return false;
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return null;
   }

   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
      super.func_180657_a(worldIn, player, pos, state, te, stack);
   }

   public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
      return false;
   }

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      return false;
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockGore.EnumType variant : BlockGore.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockGore.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockGore.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockGore.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   public static enum EnumType implements IStringSerializable {
      FLAT,
      SMALL,
      BIG;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
