package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Random;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfestedOre extends BlockBase implements IMetaName {
   public static final PropertyEnum<BlockInfestedOre.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockInfestedOre.EnumType.class);

   public BlockInfestedOre(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material, name, hardness, creative, tickRandom);
      this.setHarvestLevel("pickaxe", 1);
      this.func_149672_a(
         new SoundType(
            1.0F,
            0.5F,
            SRPSounds.BLOCKINFEST_BREAK,
            SRPSounds.BLOCKINFEST_STEP,
            SRPSounds.BLOCKINFEST_PLACE,
            SRPSounds.BLOCKINFEST_HIT,
            SoundEvents.field_187876_fn
         )
      );
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockInfestedOre.EnumType.CO));
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockInfestedOre.EnumType variant : BlockInfestedOre.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockInfestedOre.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockInfestedOre.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockInfestedOre.EnumType.values();
   }

   @Override
   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      this.func_176208_a(world, pos, state, player);
      return world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), world.field_72995_K ? 11 : 3);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
   }

   public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      switch ((BlockInfestedOre.EnumType)state.func_177229_b(VARIANT)) {
         case CO:
         case DIA:
         case EME:
         case GOL:
         case IRO:
         case LAP:
         case RED:
         case UN:
         default:
            return this == Blocks.field_150449_bY ? Items.field_151128_bU : Item.func_150898_a(this);
      }
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   @SideOnly(Side.CLIENT)
   public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
      if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a
         || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == SRPBlocks.ParasiteBush) {
         if (rand.nextDouble() <= SRPConfigSystems.rsBlockParticleS) {
            double d0 = pos.func_177958_n() + rand.nextDouble();
            double d1 = pos.func_177956_o() + 2.5;
            double d2 = pos.func_177952_p() + rand.nextDouble();
            ParticleSpawner.spawnParticle(SRPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
         }
      }
   }

   public static enum EnumType implements IStringSerializable {
      CO,
      DIA,
      EME,
      GOL,
      IRO,
      LAP,
      RED,
      UN;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
