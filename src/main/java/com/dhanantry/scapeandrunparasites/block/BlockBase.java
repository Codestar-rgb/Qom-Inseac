package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBase extends Block {
   public BlockBase(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149675_a(tickRandom);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock;
      if (this instanceof IMetaName) {
         itemBlock = ((IMetaName)this).getItemBlock();
      } else {
         itemBlock = new ItemBlock(this);
      }

      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public BlockBase(Material material, String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
      this(material, name, hardness, creative, tickRandom);
      this.func_149752_b(resistance);
   }

   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      if (!world.field_72995_K) {
         ParasiteEventWorld.setDisloWorldPhase(world, SRPAttributes.EVENTPARABLOCKBR, SRPConfigSystems.chanceEventParaBlockB, 0, null);
         SRPSaveData dataLol = SRPSaveData.get(world, 1);
         int goo = SRPConfigSystems.disloParasiteBlock ? dataLol.getCurrentCode(world.field_73011_w.getDimension(), 25) : 0;
         if (goo != 0 && world.field_73012_v.nextDouble() < SRPConfigSystems.disloParasiteBlockChance) {
            List<Entity> serverList = world.field_72996_f;
            int count = 0;

            for (int x = 0; x < serverList.size(); x++) {
               if (serverList.get(x) instanceof EntityParasiteBase) {
                  count++;
               }
            }

            if (count > SRPConfig.worldMobCap) {
               int var11 = false;
               return super.removedByPlayer(state, world, pos, player, willHarvest);
            }

            EntityParasiteBase halo = ParasiteEventEntity.getRandomFeral(world);
            if (goo >= SRPConfigSystems.disloParasiteBlockValue1) {
               halo = ParasiteEventEntity.getRandomPrimitive(world);
            }

            if (goo >= SRPConfigSystems.disloParasiteBlockValue2) {
               halo = ParasiteEventEntity.getRandomAdapted(world);
            }

            if (goo >= SRPConfigSystems.disloParasiteBlockValue3) {
               halo = ParasiteEventEntity.getRandomPure(world);
            }

            halo.func_70107_b(pos.func_177958_n() + 0.5, pos.func_177956_o(), pos.func_177952_p() + 0.5);
            halo.func_180482_a(world.func_175649_E(new BlockPos(halo)), null);
            world.func_72838_d(halo);
            world.func_180498_a(null, 1026, new BlockPos(halo), 0);
            halo.particleStatus((byte)7);
            halo.func_70690_d(new PotionEffect(SRPPotions.EPEL_E, 600, 0, false, false));
         }
      }

      return super.removedByPlayer(state, world, pos, player, willHarvest);
   }

   public boolean func_189872_a(IBlockState state, Entity entityIn) {
      return !(entityIn instanceof EntityParasiteBase) ? false : super.func_189872_a(state, entityIn);
   }
}
