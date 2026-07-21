package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteLoot;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.gen.WorldGenCustomStructures;
import com.dhanantry.scapeandrunparasites.world.gen.feature.util.WorldGenMeteorImpactUtil;
import com.dhanantry.scapeandrunparasites.world.gen.structure.WorldGenStructure;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WorldGenParasiteMeteorCrash extends WorldGenParasiteColonyBase {
   public WorldGenParasiteMeteorCrash(boolean notify, int stage) {
      super(notify, stage);
      this.wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);
      this.tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
      this.floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
      BlockPos impactCenter = worldIn.func_175672_r(posss).func_177977_b();
      if (this.type != 5) {
         String out = "meteor_fragment_large1";
         switch (worldIn.field_73012_v.nextInt(9)) {
            case 1:
               out = "meteor_fragment_large2";
               break;
            case 2:
               out = "meteor_fragment_large3";
               break;
            case 3:
               out = "meteor_fragment_small1";
               break;
            case 4:
               out = "meteor_fragment_small2";
               break;
            case 5:
               out = "meteor_fragment_small3";
               break;
            case 6:
               out = "meteor_fragment_small4";
               break;
            case 7:
               out = "meteor_fragment_small5";
               break;
            case 8:
               out = "meteor_fragment_small6";
         }

         BlockPos surface = impactCenter;
         BlockPos origin = impactCenter.func_177982_a(2, 2, 2);
         WorldGenCustomStructures.generateInPosition(new WorldGenStructure(out), rand, worldIn, origin, -2, -2, -2);
         int fires = 18 + rand.nextInt(18);
         int fireRadius = 10;

         for (int i = 0; i < fires; i++) {
            int dx = rand.nextInt(fireRadius * 2 + 1) - fireRadius;
            int dz = rand.nextInt(fireRadius * 2 + 1) - fireRadius;
            if (dx * dx + dz * dz <= fireRadius * fireRadius) {
               BlockPos top = worldIn.func_175672_r(surface.func_177982_a(dx, 0, dz)).func_177977_b();
               if (top.func_177956_o() > 5) {
                  BlockPos firePos = top.func_177984_a();
                  if (worldIn.func_175667_e(firePos)) {
                     IBlockState below = worldIn.func_180495_p(top);
                     if (below.func_185904_a() != Material.field_151579_a
                        && below.func_185904_a() != Material.field_151586_h
                        && below.func_185904_a() != Material.field_151587_i) {
                        IBlockState at = worldIn.func_180495_p(firePos);
                        if (at.func_185904_a() == Material.field_151579_a) {
                           worldIn.func_180501_a(firePos, Blocks.field_150480_ab.func_176223_P(), 2);
                        }
                     }
                  }
               }
            }
         }

         return true;
      } else {
         BlockPos enter = impactCenter.func_177979_c(10);

         for (int ix = 0; ix < 20; ix++) {
            this.replaceCircleGround(
               worldIn,
               enter.func_177981_b(ix),
               this.type * 7,
               SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
            );
         }

         int rad = this.type;
         posss = impactCenter.func_177979_c(rad + rad);
         int minCenterY = rad * 16 + 6;
         if (posss.func_177956_o() < minCenterY) {
            posss = new BlockPos(posss.func_177958_n(), minCenterY, posss.func_177952_p());
         }

         this.generateSphere(
            worldIn,
            posss,
            rad * 16,
            rad * 16,
            rand,
            false,
            1,
            false,
            1,
            1,
            5,
            Blocks.field_150350_a.func_176223_P(),
            Blocks.field_150350_a.func_176223_P(),
            Blocks.field_150350_a.func_176223_P(),
            2
         );
         float yaw = rand.nextFloat() * 360.0F;
         double dirX = -MathHelper.func_76126_a(yaw * (float) (Math.PI / 180.0));
         double dirZ = MathHelper.func_76134_b(yaw * (float) (Math.PI / 180.0));
         float steepness = 0.25F + rand.nextFloat() * 0.75F;
         double dirY = -steepness;
         IBlockState rim = SRPBlocks.ParasiteRubbleDense
            .func_176223_P()
            .func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);
         IBlockState rubble = SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS);
         IBlockState stainRed = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED);
         IBlockState cooked = SRPBlocks.CookedFlesh.func_176223_P();
         BlockPos craterSurface = impactCenter;
         BlockPos tunnelStart = impactCenter.func_177979_c(3);
         WorldGenMeteorImpactUtil.TunnelResult tunnel = WorldGenMeteorImpactUtil.carveAngledTunnel(worldIn, tunnelStart, 10, 30, dirX, dirY, dirZ);
         int baseR = rad * 8;
         int baseDepth = (int)(baseR * (0.4F + rand.nextFloat() * 0.2F));
         int adjustedDepth = baseDepth;
         if (tunnel.anyBroken) {
            int openNeeded = impactCenter.func_177956_o() - tunnel.lowestY;
            if (openNeeded > baseDepth) {
               adjustedDepth = openNeeded + 3;
            }
         }

         int adjustedR = baseR;
         int depthDrivenR = (int)(adjustedDepth * 1.6F);
         if (depthDrivenR > baseR) {
            adjustedR = depthDrivenR;
         }

         int bottomY = impactCenter.func_177956_o() - adjustedDepth;
         int placeY = Math.max(bottomY + 1, 6);
         BlockPos structPos = new BlockPos(impactCenter.func_177958_n(), placeY, impactCenter.func_177952_p());
         WorldGenMeteorImpactUtil.clearVegetationInArea(
            worldIn, impactCenter, adjustedR * 2, impactCenter.func_177956_o() - adjustedDepth - 12, impactCenter.func_177956_o() + 50
         );
         WorldGenMeteorImpactUtil.carveCraterBowl(worldIn, rand, impactCenter, adjustedR, adjustedDepth, steepness, rim, stainRed, cooked);
         WorldGenMeteorImpactUtil.scorchRings(worldIn, rand, impactCenter, adjustedR, stainRed);
         WorldGenMeteorImpactUtil.spawnEjecta(worldIn, rand, impactCenter, adjustedR, dirX, dirZ, rubble, stainRed);
         WorldGenMeteorImpactUtil.microCraters(worldIn, rand, impactCenter, adjustedR, dirX, dirZ, stainRed);
         int poolR = Math.max(4, adjustedR / 6);
         int poolRR = poolR * poolR;
         int skipR = 10;
         int skipRR = skipR * skipR;
         int bottomY2 = impactCenter.func_177956_o() - adjustedDepth + 1;
         if (bottomY2 < 6) {
            bottomY2 = 6;
         }

         int poolHeight = 4;
         int topY2 = bottomY2 + poolHeight;

         for (int x = -poolR; x <= poolR; x++) {
            for (int z = -poolR; z <= poolR; z++) {
               int d2 = x * x + z * z;
               if (d2 <= poolRR && d2 > skipRR) {
                  for (int y = bottomY2; y <= topY2; y++) {
                     BlockPos p = new BlockPos(craterSurface.func_177958_n() + x, y, craterSurface.func_177952_p() + z);
                     if (worldIn.func_175667_e(p)) {
                        IBlockState s = worldIn.func_180495_p(p);
                        if (s.func_185904_a() == Material.field_151579_a) {
                           worldIn.func_180501_a(p, SRPBlocks.DeadBlood.func_176223_P(), 2);
                        }
                     }
                  }
               }
            }
         }

         int half = 22;
         int fix = 2;
         BlockPos meteorPos = structPos.func_177981_b(14).func_177982_a(-half - fix, 0, -half - fix);
         WorldGenCustomStructures.generateInPosition(new WorldGenStructure("meteor"), rand, worldIn, meteorPos, 0, 0, 0);
         int i1 = meteorPos.func_177981_b(14).func_177956_o();
         double l1 = meteorPos.func_177981_b(14).func_177982_a(half, 0, 0).func_177958_n();
         double i2 = meteorPos.func_177981_b(14).func_177982_a(0, 0, half).func_177952_p();
         int BGrange = 11;

         for (int k2 = -1 * BGrange; k2 <= 1 * BGrange; k2++) {
            for (int l2 = -1 * BGrange; l2 <= 1 * BGrange; l2++) {
               for (int j = -1 * BGrange; j <= 1 * BGrange; j++) {
                  double i3 = l1 + k2;
                  double k = i1 + j;
                  double l = i2 + l2;
                  BlockPos blockpos = new BlockPos(i3, k, l);
                  IBlockState iblockstate = worldIn.func_180495_p(blockpos);
                  Block block = iblockstate.func_177230_c();
                  if (block != Blocks.field_150359_w
                     && block != Blocks.field_150399_cn
                     && block != Blocks.field_150410_aZ
                     && block != Blocks.field_150397_co
                     && !(block instanceof BlockGlass)
                     && (!(block instanceof BlockPane) || iblockstate.func_185904_a() != Material.field_151592_s)) {
                     String name = block.getRegistryName().toString();
                     if (block == Blocks.field_150339_S || block == Blocks.field_150340_R || block == Blocks.field_150484_ah) {
                        int rollRare = 10;
                        int rollUncommon = 4;
                        if (block == Blocks.field_150340_R) {
                           rollRare = 7;
                           rollUncommon = 3;
                        }

                        if (block == Blocks.field_150484_ah) {
                           rollRare = 4;
                           rollUncommon = 2;
                        }

                        if (rand.nextInt(rollRare) == 0) {
                           this.placeLoot(
                              worldIn,
                              blockpos,
                              SRPConfigWorld.blockLootRare,
                              SRPBlocks.ParasiteLoot.func_176223_P().func_177226_a(BlockParasiteLoot.VARIANT, BlockParasiteLoot.EnumType.RARE)
                           );
                           worldIn.func_175656_a(blockpos.func_177984_a(), SRPBlocks.DeadBlood.func_176223_P());
                        } else if (rand.nextInt(rollUncommon) == 0) {
                           this.placeLoot(
                              worldIn,
                              blockpos,
                              SRPConfigWorld.blockLootUncommon,
                              SRPBlocks.ParasiteLoot.func_176223_P().func_177226_a(BlockParasiteLoot.VARIANT, BlockParasiteLoot.EnumType.UNCOMMON)
                           );
                           worldIn.func_175656_a(blockpos.func_177984_a(), SRPBlocks.DeadBlood.func_176223_P());
                        } else {
                           this.placeLoot(
                              worldIn,
                              blockpos,
                              SRPConfigWorld.blockLootCommon,
                              SRPBlocks.ParasiteLoot.func_176223_P().func_177226_a(BlockParasiteLoot.VARIANT, BlockParasiteLoot.EnumType.COMMON)
                           );
                           worldIn.func_175656_a(blockpos.func_177984_a(), SRPBlocks.DeadBlood.func_176223_P());
                        }
                     }
                  } else {
                     worldIn.func_180501_a(blockpos, SRPBlocks.ParasiteStain.func_176203_a(2), 2);
                  }
               }
            }
         }

         WorldGenMeteorImpactUtil.updateWaterAfterImpact(worldIn, craterSurface, adjustedR, adjustedDepth);
         return true;
      }
   }
}
