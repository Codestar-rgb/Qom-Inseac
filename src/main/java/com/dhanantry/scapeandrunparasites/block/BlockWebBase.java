package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.BlockWeb;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWebBase extends BlockWeb implements IMetaName {
   public static final PropertyEnum<BlockWebBase.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockWebBase.EnumType.class);

   public BlockWebBase(String name) {
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149675_a(true);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockWebBase.EnumType.ONE));
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = this.getItemBlock();
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         worldIn.func_175698_g(pos);
      }
   }

   public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      if (!worldIn.field_72995_K) {
         if (entityIn.field_70173_aa % 20 != 0) {
            return;
         }

         if (!(entityIn instanceof EntityParasiteBase) && entityIn instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)entityIn;
            switch ((BlockWebBase.EnumType)state.func_177229_b(VARIANT)) {
               case ONE:
               default:
                  break;
               case THREE:
                  target.func_70097_a(DamageSource.field_76367_g, 8.0F);
                  break;
               case TWO:
                  target.func_70097_a(DamageSource.field_76367_g, 4.0F);
            }
         }
      }

      super.func_180634_a(worldIn, pos, state, entityIn);
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   protected boolean func_149700_E() {
      return false;
   }

   public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
      return false;
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return null;
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockWebBase.EnumType variant : BlockWebBase.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockWebBase.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockWebBase.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockWebBase.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   public static enum EnumType implements IStringSerializable {
      ONE,
      TWO,
      THREE;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
