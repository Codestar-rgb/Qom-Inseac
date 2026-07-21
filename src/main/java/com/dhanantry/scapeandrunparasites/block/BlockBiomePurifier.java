package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationaryArchitect;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.MsgSpawnPureParticles;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.handlers.BiomeUpdateQueue;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class BlockBiomePurifier extends BlockBase {
   public BlockBiomePurifier(String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
      super(Material.field_151583_m, name, hardness, creative, tickRandom, resistance);
      this.func_149672_a(SRPSoundTypes.PURIFIER);
      this.func_180632_j(this.field_176227_L.func_177621_b());
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      boolean flag = super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
      if (worldIn.field_72995_K) {
         return flag;
      } else {
         ItemStack head = new ItemStack(playerIn.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b());
         if (head.func_77973_b() != Items.field_190931_a) {
            return flag;
         } else {
            worldIn.func_184133_a(null, pos, SRPSounds.PURIFIER_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            killBiome(worldIn, pos, 16);
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
            double ax = pos.func_177958_n() + 0.5;
            double ay = pos.func_177956_o() + 0.1;
            double az = pos.func_177952_p() + 0.5;
            SRPNetwork.CHANNEL
               .sendToAllAround(new MsgSpawnPureParticles(ax, ay, az, 24, 0), new TargetPoint(worldIn.field_73011_w.getDimension(), ax, ay, az, 64.0));
            double bx = pos.func_177958_n() + 0.5;
            double by = pos.func_177956_o() + 0.9;
            double bz = pos.func_177952_p() + 0.5;
            SRPNetwork.CHANNEL
               .sendToAllAround(new MsgSpawnPureParticles(bx, by, bz, 24, 1), new TargetPoint(worldIn.field_73011_w.getDimension(), bx, by, bz, 64.0));
            double cx = pos.func_177958_n() + 0.5;
            double cy = pos.func_177956_o() + 0.5;
            double cz = pos.func_177952_p() + 0.5;
            ParticleSpawner.spawnParticle(SRPEnumParticle.FLASH, cx, cy, cz, 0.0, 0.0, 0.0, 0, 20, 20);
            return flag;
         }
      }
   }

   @Override
   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      this.func_176208_a(world, pos, state, player);
      return world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), world.field_72995_K ? 11 : 3);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         if (worldIn.func_175697_a(pos, 3)) {
            ParasiteEventWorld.setDisloWorldPhase(worldIn, SRPAttributes.EVENTPARAPURIFIER, SRPConfigSystems.chanceEventParaPurifier, 0, null);
            int raan = 32;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1
               )
               .func_186662_g(raan);
            List<EntityParasiteBase> moblist = worldIn.func_72872_a(EntityParasiteBase.class, axisalignedbb);
            boolean oneTime = true;
            if (!moblist.isEmpty()) {
               for (EntityParasiteBase mob : moblist) {
                  if (oneTime && mob instanceof EntityPStationaryArchitect) {
                     mob.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 200, 0, false, false));
                     oneTime = false;
                  }

                  mob.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 600, 1, false, false));
               }
            }

            int i1 = pos.func_177956_o();
            double l1 = pos.func_177958_n();
            double i2 = pos.func_177952_p();
            int var28 = 5;
            int BGrange = var28;
            int BGheight = var28;

            for (int k2 = -1 * var28; k2 <= BGrange; k2++) {
               for (int l2 = -1 * BGrange; l2 <= BGrange; l2++) {
                  for (int j = -1 * BGheight; j <= BGheight; j++) {
                     double i3 = l1 + k2;
                     double k = i1 + j;
                     double l = i2 + l2;
                     BlockPos blockpos = new BlockPos(i3, k, l);
                     IBlockState iblockstate = worldIn.func_180495_p(blockpos);
                     Block block = iblockstate.func_177230_c();
                     if (block == SRPBlocks.InfestedStain) {
                        worldIn.func_175656_a(blockpos, SRPBlocks.InfestedStain.func_176223_P().func_177226_a(BlockInfestedStain.STAGE, 5));
                        worldIn.func_175654_a(
                           blockpos, SRPBlocks.InfestedStain.func_176223_P().func_177226_a(BlockInfestedStain.STAGE, 5).func_177230_c(), 40, 5
                        );
                     }

                     if (block == SRPBlocks.InfestedRubble) {
                        worldIn.func_175656_a(blockpos, SRPBlocks.InfestedRubble.func_176223_P().func_177226_a(BlockInfestedRubble.STAGE, 5));
                        worldIn.func_175654_a(
                           blockpos, SRPBlocks.InfestedRubble.func_176223_P().func_177226_a(BlockInfestedRubble.STAGE, 5).func_177230_c(), 40, 5
                        );
                     }
                  }
               }
            }
         }
      }
   }

   public static void killBiome(World worldIn, BlockPos pos, int range) {
      SRPWorldData data = SRPWorldData.get(worldIn);
      int worldTime = 0;

      for (int x = pos.func_177958_n() - range; x <= pos.func_177958_n() + range; x++) {
         for (int z = pos.func_177952_p() - range; z <= pos.func_177952_p() + range; z++) {
            int age = data.nearestHeartAge(pos, true, worldTime);
            int distance = data.getDistanceSpreadByAge(age, false);
            BlockPos convert = new BlockPos(x, pos.func_177956_o(), z);
            if (worldIn.func_180494_b(convert) instanceof BiomeParasiteBase) {
               Biome originalBiome = worldIn.func_72959_q().func_180300_a(pos, Biomes.field_76772_c);
               positionToBiome(worldIn, convert, Biome.func_185362_a(originalBiome));
               BiomeUpdateQueue.enqueue(
                  convert.func_177958_n(),
                  convert.func_177956_o(),
                  convert.func_177952_p(),
                  false,
                  Biome.func_185362_a(originalBiome),
                  worldIn.field_73011_w.getDimension()
               );
            }
         }
      }
   }

   public static void positionToBiome(World worldIn, BlockPos pos, int type) {
      int inChunkX = pos.func_177958_n() & 15;
      int inChunkZ = pos.func_177952_p() & 15;
      worldIn.func_175726_f(pos).func_76605_m()[inChunkZ << 4 | inChunkX] = (byte)type;
      worldIn.func_175726_f(pos).func_76630_e();
      worldIn.func_72975_g(pos.func_177958_n(), pos.func_177952_p(), 0, pos.func_177956_o());
   }
}
