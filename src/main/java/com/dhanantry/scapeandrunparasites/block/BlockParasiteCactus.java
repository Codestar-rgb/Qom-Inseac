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
import net.minecraft.block.BlockCactus;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockParasiteCactus extends BlockCactus {
   private static final String NBT_PCACTUS_LAST_PUSH = "srp_pcactus_last_push";
   private static final double PUSH_H = 0.35;
   private static final double PUSH_Y = 0.08;
   private static final long PUSH_CD = 8L;

   public BlockParasiteCactus(String name, float hardness, boolean creative, float resistance) {
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149752_b(resistance);
      this.func_149672_a(SoundType.field_185854_g);
      this.func_149675_a(true);
      this.setHarvestLevel("axe", 0);
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

   public boolean func_176586_d(World worldIn, BlockPos pos) {
      IBlockState downState = worldIn.func_180495_p(pos.func_177977_b());
      if (downState.func_177230_c() != Blocks.field_150354_m
         && downState.func_177230_c() != Blocks.field_180395_cM
         && downState.func_177230_c() != SRPBlocks.InfestedSand
         && downState.func_177230_c() != this) {
         return false;
      } else {
         for (EnumFacing f : Plane.HORIZONTAL) {
            IBlockState side = worldIn.func_180495_p(pos.func_177972_a(f));
            if (side.func_185904_a().func_76220_a() && side.func_177230_c() != this) {
               return false;
            }
         }

         return true;
      }
   }

   public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      super.func_180634_a(worldIn, pos, state, entityIn);
      if (!worldIn.field_72995_K) {
         if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entityIn;
            if (!player.field_70128_L) {
               long now = worldIn.func_82737_E();
               long last = player.getEntityData().func_74763_f("srp_pcactus_last_push");
               if (now - last >= 8L) {
                  player.getEntityData().func_74772_a("srp_pcactus_last_push", now);
                  double cx = pos.func_177958_n() + 0.5;
                  double cz = pos.func_177952_p() + 0.5;
                  double dx = player.field_70165_t - cx;
                  double dz = player.field_70161_v - cz;
                  double mag = Math.sqrt(dx * dx + dz * dz);
                  if (mag < 1.0E-4) {
                     dx = 0.0;
                     dz = 1.0;
                     mag = 1.0;
                  }

                  dx /= mag;
                  dz /= mag;
                  player.func_70024_g(dx * 0.35, 0.08, dz * 0.35);
                  player.field_70133_I = true;
               }
            }
         }
      }
   }
}
