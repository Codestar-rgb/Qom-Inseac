package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParasiteRubble extends BlockParasiteSpreading implements IMetaName {
   public static final PropertyEnum<BlockParasiteRubble.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockParasiteRubble.EnumType.class);

   public BlockParasiteRubble(Material material, String name, float hardness, boolean creative, boolean infested) {
      super(material, name, hardness, creative, infested);
      this.setHarvestLevel("pickaxe", 1);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockParasiteRubble.EnumType.BONE));
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockParasiteRubble.EnumType variant : BlockParasiteRubble.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockParasiteRubble.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockParasiteRubble.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockParasiteRubble.EnumType.values();
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

   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      Block block = worldIn.func_180495_p(pos.func_177984_a()).func_177230_c();
      switch ((BlockParasiteRubble.EnumType)state.func_177229_b(VARIANT)) {
         case WEATHB:
            if (block != Blocks.field_150433_aE && block != Blocks.field_150431_aC) {
               return state;
            }

            return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHBS);
         case WEATHBS:
            if (block != Blocks.field_150433_aE && block != Blocks.field_150431_aC) {
               return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHB);
            }

            return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHBS);
         case WEATHBC:
            if (block != Blocks.field_150433_aE && block != Blocks.field_150431_aC) {
               return state;
            }

            return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHBCS);
         case WEATHBCS:
            if (block != Blocks.field_150433_aE && block != Blocks.field_150431_aC) {
               return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHBC);
            }

            return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHBCS);
         case WEATHFS:
            if (block != Blocks.field_150433_aE && block != Blocks.field_150431_aC) {
               return state;
            }

            return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHFSS);
         case WEATHFSS:
            if (block != Blocks.field_150433_aE && block != Blocks.field_150431_aC) {
               return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHFS);
            }

            return state.func_177226_a(VARIANT, BlockParasiteRubble.EnumType.WEATHFSS);
         default:
            return state;
      }
   }

   public static enum EnumType implements IStringSerializable {
      FLESH,
      BONE,
      STONE,
      STONEDEBRIS,
      WOOD,
      BRICKS,
      METAL,
      OBSIDIAN,
      FUNGUS,
      WEATHB,
      WEATHBS,
      WEATHBC,
      WEATHBCS,
      WEATHFS,
      WEATHFSS;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
