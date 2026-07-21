package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SRPDoor extends BlockDoor {
   private final String regName;
   private final Item doorItem;

   public SRPDoor(Material material, String regName) {
      super(material);
      this.regName = regName;
      this.setRegistryName("srparasites", regName);
      this.func_149663_c("srparasites." + regName);
      this.func_149711_c(material == Material.field_151573_f ? 5.0F : 3.0F);
      this.func_149672_a(material == Material.field_151573_f ? SoundType.field_185852_e : SoundType.field_185848_a);
      SRPBlocks.SRP_BLOCKS.add(this);
      ItemDoor door = new ItemDoor(this);
      door.setRegistryName(Objects.requireNonNull(this.getRegistryName()));
      door.func_77655_b("srparasites." + regName);
      door.func_77637_a(SRPMain.SRP_CREATIVETAB);
      SRPItems.SRP_ITEMS.add(door);
      this.doorItem = door;
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return state.func_177229_b(field_176523_O) == EnumDoorHalf.UPPER ? Items.field_190931_a : this.doorItem;
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(this.doorItem);
   }

   public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(this.doorItem);
   }
}
