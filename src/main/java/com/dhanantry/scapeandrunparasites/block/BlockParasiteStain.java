package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Random;
import javax.annotation.Nullable;
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

public class BlockParasiteStain extends BlockParasiteSpreading implements IMetaName {
   public static final PropertyEnum<BlockParasiteStain.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockParasiteStain.EnumType.class);

   public BlockParasiteStain(Material material, String name, float hardness, boolean creative, boolean infested) {
      super(material, name, hardness, creative, infested);
      this.setHarvestLevel("shovel", 0);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockParasiteStain.EnumType.DIRT));
   }

   public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
      switch ((BlockParasiteStain.EnumType)state.func_177229_b(VARIANT)) {
         case DIRT:
            return SoundType.field_185849_b;
         case MUD:
            return SoundType.field_185859_l;
         case SPORE:
            return SoundType.field_185850_c;
         case FLESH:
         case FEELER:
         case RED:
         case SACKFLESH:
         default:
            return SRPSoundTypes.FLESH;
      }
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockParasiteStain.EnumType variant : BlockParasiteStain.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockParasiteStain.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockParasiteStain.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockParasiteStain.EnumType.values();
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
      DIRT,
      MUD,
      FLESH,
      FEELER,
      SPORE,
      RED,
      SACKFLESH;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
