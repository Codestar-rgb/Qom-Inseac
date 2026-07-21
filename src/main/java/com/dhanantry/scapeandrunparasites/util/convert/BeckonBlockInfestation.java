package com.dhanantry.scapeandrunparasites.util.convert;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedBush;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.block.IStagedBlock;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteBush;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeckonBlockInfestation {
   private static final Logger INFEST_LOG = LogManager.getLogger("SRP-Infest");
   private static final boolean DEBUG = false;
   public static int blockInfestedCount;
   private static final HashMap<Material, IBlockState> conversionCache = new HashMap<>();
   private static final HashMap<BeckonBlockInfestation.BlockMetaKey, IBlockState> customConvertCache = new HashMap<>();
   private static int customConvertHash = 0;

   public static void beckonInfestation(World worldIn, BlockPos pos, Random rand, int stage, boolean fromVenkrol) {
      if (blockInfestedCount <= SRPConfig.BlockInfestedLimit) {
         if (worldIn.func_175659_aa() != EnumDifficulty.PEACEFUL) {
            int pivot = 0;
            int range;
            int rangeY;
            switch (stage) {
               case 1:
                  range = SRPConfigMobs.venkrolRange - 1;
                  rangeY = SRPConfigMobs.venkrolRangeY;
                  break;
               case 2:
                  range = SRPConfigMobs.venkrolsiiRange - 1;
                  rangeY = SRPConfigMobs.venkrolsiiRangeY;
                  break;
               case 3:
               default:
                  range = SRPConfigMobs.venkrolsiiiRange - 1;
                  rangeY = SRPConfigMobs.venkrolsiiiRangeY;
            }

            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1
               )
               .func_72314_b(range, rangeY, range);
            List<EntityParasiteBase> moblist = worldIn.func_72872_a(EntityParasiteBase.class, axisalignedbb);
            if (fromVenkrol) {
               if (moblist.isEmpty() && stage < 4) {
                  spawnBeckonFromInfestation(worldIn, pos, stage);
                  return;
               }

               if (!moblist.isEmpty() && stage < 4) {
                  int flagV = 0;

                  for (EntityParasiteBase mob : moblist) {
                     if (flagV == 2) {
                        break;
                     }

                     if (mob.func_70644_a(SRPPotions.PIVOT_E)) {
                        pivot = Math.max(mob.func_70660_b(SRPPotions.PIVOT_E).func_76458_c() + 1, pivot);
                     }

                     flagV = 0;
                     BlockPos venPos = mob.func_180425_c();
                     if (worldIn.func_175724_o(venPos.func_177984_a()) < SRPConfigSystems.rsBlockLight / 15.0F) {
                        flagV++;
                     }

                     if (worldIn.func_175642_b(EnumSkyBlock.BLOCK, venPos.func_177984_a()) < SRPConfigSystems.rsBlockLight) {
                        flagV++;
                     }
                  }

                  if (flagV < 2) {
                     return;
                  }
               }
            }

            int covertedBlocks = blockConversions(worldIn, pos, rand, stage);
            if (stage >= 4) {
               if (worldIn.func_180495_p(pos).func_177230_c() == SRPBlocks.InfestedStain) {
                  worldIn.func_175656_a(pos, SRPBlocks.optionalDirt);
               } else if (worldIn.func_180495_p(pos).func_177230_c() == SRPBlocks.InfestedRubble) {
                  worldIn.func_175656_a(pos, SRPBlocks.optionalRub);
               }

               if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a
                  || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == SRPBlocks.ParasiteBush) {
                  worldIn.func_175656_a(pos.func_177984_a(), SRPBlocks.InfestRemain.func_176223_P());
               }
            } else if (covertedBlocks != 0) {
               blockInfestedCount += covertedBlocks;
               if (SRPConfigSystems.useEvolution) {
                  if (pivot != 0) {
                     SRPSaveData.get(worldIn, 75)
                        .setTotalKills(
                           worldIn.field_73011_w.getDimension(),
                           SRPConfigSystems.valueBlock * covertedBlocks * SRPConfigSystems.pivotPointMultiplier * pivot,
                           true,
                           worldIn,
                           true,
                           53
                        );
                  } else {
                     SRPSaveData.get(worldIn, 74)
                        .setTotalKills(worldIn.field_73011_w.getDimension(), SRPConfigSystems.valueBlock * covertedBlocks, true, worldIn, true, 54);
                  }
               }
            }
         }
      }
   }

   private static void generateInfestationFeatures(World worldIn, BlockPos pos, Random rand) {
      spawnGenFeatureInfested(worldIn, pos.func_177984_a(), rand);
      spawnGenRoofInfested(worldIn, pos.func_177977_b(), rand);
   }

   public static void spawnGenFeatureInfested(World worldIn, BlockPos pos, Random rand) {
      double bonus = 0.0;
      if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos).func_185904_a() == Material.field_151586_h) {
         pos = ParasiteEventEntity.getFloor(worldIn, pos, 10);
         bonus = 3.0E-5;
         if (pos == null) {
            return;
         }
      }

      if (rand.nextInt(20) == 0) {
         WorldGenParasiteBush gen = new WorldGenParasiteBush(false, BlockInfestedBush.EnumType.SPINE, 4);
         gen.func_180709_b(worldIn, rand, pos);
      }

      if (rand.nextInt(20) == 0) {
         if (rand.nextInt(2) == 0) {
            WorldGenParasiteBush gen = new WorldGenParasiteBush(false, BlockInfestedBush.EnumType.ARC, 2);
            gen.func_180709_b(worldIn, rand, pos);
         } else {
            WorldGenParasiteBush gen = new WorldGenParasiteBush(false, BlockInfestedBush.EnumType.GRASS1, 1);
            gen.func_180709_b(worldIn, rand, pos);
         }
      }
   }

   public static void spawnGenRoofInfested(World worldIn, BlockPos pos, Random rand) {
      if (worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
         if (rand.nextInt(5) == 0) {
            worldIn.func_175656_a(pos, SRPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, BlockInfestedBush.EnumType.VINE));
            pos = pos.func_177977_b();
            if (rand.nextInt(4) == 0) {
               if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                  return;
               }

               worldIn.func_175656_a(pos, SRPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, BlockInfestedBush.EnumType.VINE));
               pos = pos.func_177977_b();
               if (rand.nextInt(3) == 0) {
                  if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                     return;
                  }

                  worldIn.func_175656_a(pos, SRPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, BlockInfestedBush.EnumType.VINE));
               }
            }
         }
      }
   }

   private static void rebuildCustomConvertCacheIfNeeded() {
      String[] list = SRPConfigWorld.infestationConvertBlocks;
      int h = 1;
      if (list != null) {
         for (String s : list) {
            if (s != null) {
               h = 31 * h + s.hashCode();
            }
         }
      }

      if (h != customConvertHash) {
         customConvertHash = h;
         customConvertCache.clear();
         if (list != null) {
            for (String entry : list) {
               if (entry != null) {
                  entry = entry.trim();
                  if (!entry.isEmpty()) {
                     String[] parts = entry.split(";", 2);
                     if (parts.length == 2) {
                        String left = parts[0].trim();
                        String right = parts[1].trim();
                        BeckonBlockInfestation.ParsedState src = parseStateToken(left);
                        BeckonBlockInfestation.ParsedState dst = parseStateToken(right);
                        if (src != null && dst != null && (dst.block != Blocks.field_150350_a || "minecraft:air".equals(right))) {
                           customConvertCache.put(new BeckonBlockInfestation.BlockMetaKey(src.block, src.meta), dst.state);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static BeckonBlockInfestation.ParsedState parseStateToken(String token) {
      if (token != null && !token.isEmpty()) {
         String id = token.trim();
         int meta = -1;
         String[] split = id.split(":");
         if (split.length == 3) {
            id = split[0] + ":" + split[1];

            try {
               meta = Integer.parseInt(split[2]);
            } catch (Exception var8) {
               meta = -1;
            }
         }

         ResourceLocation rl;
         try {
            rl = new ResourceLocation(id);
         } catch (Exception var7) {
            return null;
         }

         if (!Block.field_149771_c.func_148741_d(rl)) {
            return null;
         } else {
            Block b = (Block)Block.field_149771_c.func_82594_a(rl);
            if (b == null) {
               return null;
            } else if (b == Blocks.field_150350_a && !"minecraft:air".equals(id)) {
               return null;
            } else {
               if (meta < 0 && b instanceof BlockLiquid) {
                  meta = 0;
               }

               IBlockState st = meta >= 0 ? b.func_176203_a(meta) : b.func_176223_P();
               return new BeckonBlockInfestation.ParsedState(b, meta, st);
            }
         }
      } else {
         return null;
      }
   }

   private static IBlockState getCustomConvertedState(Block lookingBlock, IBlockState lookingState, int stage) {
      if (stage >= 4) {
         return null;
      } else {
         rebuildCustomConvertCacheIfNeeded();
         int meta = lookingBlock.func_176201_c(lookingState);
         IBlockState dst = customConvertCache.get(new BeckonBlockInfestation.BlockMetaKey(lookingBlock, meta));
         if (dst == null) {
            dst = customConvertCache.get(new BeckonBlockInfestation.BlockMetaKey(lookingBlock, -1));
         }

         return dst == null ? null : createStagedState(dst, stage);
      }
   }

   private static IBlockState createStagedState(IBlockState baseState, int stage) {
      if (stage < 0) {
         stage = 0;
      }

      if (stage > 5) {
         stage = 5;
      }

      return baseState.func_177230_c() instanceof IStagedBlock ? ((IStagedBlock)baseState.func_177230_c()).withStage(baseState, stage) : baseState;
   }

   private static int blockConversions(World worldIn, BlockPos pos, Random rand, int stage) {
      int convertedBlocks = 0;
      if (stage < 0) {
         stage = 0;
      }

      if (stage > 5) {
         stage = 5;
      }

      for (int dir = 0; dir <= 5; dir++) {
         BlockPos helper = BlockParasiteSpreading.directionToSpread(pos, dir);
         IBlockState lookingState = worldIn.func_180495_p(helper);
         Block lookingBlock = lookingState.func_177230_c();
         Material mat = lookingState.func_185904_a();
         if (!(lookingBlock instanceof IMetaName) && !isSrpInfestedBlock(lookingBlock)) {
            if (stage < 4 && !isSrpBlock(lookingBlock)) {
               IBlockState custom = getCustomConvertedState(lookingBlock, lookingState, stage);
               if (custom != null) {
                  worldIn.func_180501_a(helper, custom, 3);
                  convertedBlocks++;
                  generateInfestationFeatures(worldIn, helper, rand);
                  continue;
               }
            }

            if (stage <= 3) {
               if (isCobbleBlock(lookingBlock, lookingState)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.InfestedCobblestone.func_176223_P(), stage), 3);
                  continue;
               }

               if (isPathBlock(lookingBlock)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.InfestedSand.func_176223_P(), stage), 3);
                  continue;
               }

               if (isFenceBlock(lookingBlock)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.InfestedFence.func_176223_P(), stage), 3);
                  continue;
               }

               if (isAnyPlanks(lookingBlock, lookingState)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.InfestedPlanks.func_176223_P(), stage), 3);
                  continue;
               }

               if (isGlassBlock(lookingBlock, lookingState)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.InfestedGlass.func_176223_P(), stage), 3);
                  continue;
               }

               if (isGlassPaneBlock(lookingBlock)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.INFESTED_GLASS_PANE.func_176223_P(), stage), 3);
                  continue;
               }

               if (isFurnaceBlock(lookingBlock)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.INFESTED_FURNACE.func_176223_P(), stage), 3);
                  continue;
               }

               if (isStoneStairs(lookingBlock, lookingState)) {
                  IBlockState dst = createStagedState(SRPBlocks.InfestedStoneStairs.func_176223_P(), stage);
                  IBlockState copy = copyCommonProps(lookingState, dst);
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, copy, 3);
                  continue;
               }

               if (isWoodStairs(lookingBlock, lookingState)) {
                  IBlockState dst = createStagedState(SRPBlocks.InfestedPlanksStairs.func_176223_P(), stage);
                  IBlockState copy = copyCommonProps(lookingState, dst);
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, copy, 3);
                  continue;
               }

               if (lookingBlock.isWood(worldIn, helper)) {
                  convertedBlocks++;
                  worldIn.func_180501_a(helper, createStagedState(SRPBlocks.InfestedTrunk.func_176223_P(), stage), 3);
                  continue;
               }
            }

            if (!ParasiteEventWorld.blockException(
                  worldIn, helper, lookingBlock, lookingState, SRPConfigSystems.blockBList, SRPConfigSystems.blockBListWhite, SRPConfigSystems.rsBlockIMaxH
               )
               || stage > 3) {
               if (lookingBlock instanceof BlockBase) {
                  if (lookingBlock != SRPBlocks.BiomeHeart && lookingBlock != SRPBlocks.ColonyHeart && !isSrpInfestedBlock(lookingBlock)) {
                     int lookM = lookingBlock.func_176201_c(lookingState);
                     if (conversionCache.containsKey(mat)) {
                        IBlockState newState = createStagedState(conversionCache.get(mat), stage);
                        if (stage < 4 || lookM > 1 && stage != 5) {
                           if (lookM < stage && stage < 4) {
                              worldIn.func_180501_a(helper, newState, 3);
                              convertedBlocks++;
                              generateInfestationFeatures(worldIn, helper, rand);
                           }
                        } else {
                           worldIn.func_180501_a(helper, newState, 3);
                           worldIn.func_175654_a(helper, newState.func_177230_c(), 40, 5);
                        }
                     }
                  }
               } else if (stage < 4 && conversionCache.containsKey(mat) && !isSrpBlock(lookingBlock)) {
                  IBlockState infestedState = createStagedState(conversionCache.get(mat), stage);
                  worldIn.func_180501_a(helper, infestedState, 3);
                  convertedBlocks++;
                  generateInfestationFeatures(worldIn, helper, rand);
               }
            }
         }
      }

      return convertedBlocks;
   }

   private static void spawnBeckonFromInfestation(World worldIn, BlockPos pos, int stage) {
      if (worldIn.field_73012_v.nextDouble() < SRPConfigSystems.rsVenkrolEmpty
         && stage != 1
         && (
            worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a
               || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() instanceof BlockBush
         )) {
         if (worldIn.func_175724_o(pos.func_177984_a()) > 0.46666667F || worldIn.func_175642_b(EnumSkyBlock.BLOCK, pos.func_177984_a()) > 7) {
            return;
         }

         List<Entity> serverList = worldIn.field_72996_f;
         int count = 0;

         for (Entity entity : serverList) {
            if (entity instanceof EntityPBeckon) {
               if (++count > SRPConfig.nexusVenkrolCap || getDistanceSq(pos, entity) < SRPConfig.nexusVenkrolDis * SRPConfig.nexusVenkrolDis) {
                  return;
               }
            }
         }

         EntityVenkrol out = new EntityVenkrol(worldIn);
         out.func_70012_b(pos.func_177958_n() + 0.5, pos.func_177956_o() + 1, pos.func_177952_p() + 0.5, 0.0F, 0.0F);
         worldIn.func_72838_d(out);
         out.cannotDespawn(true);
      }
   }

   private static double getDistanceSq(BlockPos pos, Entity entityIn) {
      double d0 = pos.func_177958_n() - entityIn.field_70165_t;
      double d1 = pos.func_177956_o() - entityIn.field_70163_u;
      double d2 = pos.func_177952_p() - entityIn.field_70161_v;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   private static boolean isSrpInfestedBlock(Block b) {
      ResourceLocation rl = b.getRegistryName();
      if (rl == null) {
         return false;
      } else if (!"srparasites".equals(rl.func_110624_b())) {
         return false;
      } else {
         String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
         return path.contains("infest");
      }
   }

   private static boolean isSrpBlock(Block b) {
      ResourceLocation rl = b.getRegistryName();
      return rl != null && "srparasites".equals(rl.func_110624_b());
   }

   private static boolean isPathBlock(Block b) {
      if (b != Blocks.field_185774_da && !(b instanceof BlockGrassPath)) {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("path");
         }
      } else {
         return true;
      }
   }

   private static boolean isFenceBlock(Block b) {
      if (b instanceof BlockFence) {
         return true;
      } else if (b instanceof BlockFenceGate) {
         return false;
      } else {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("fence") && !path.contains("gate");
         }
      }
   }

   private static boolean isAnyPlanks(Block b, IBlockState s) {
      if (b instanceof BlockPlanks) {
         return true;
      } else {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("planks") || path.endsWith("_plank") || path.contains("wood_plank");
         }
      }
   }

   private static boolean isGlassBlock(Block b, IBlockState s) {
      if (b instanceof BlockGlass) {
         return true;
      } else if (s.func_185904_a() == Material.field_151592_s && !(b instanceof BlockPane)) {
         return true;
      } else {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("glass") && !path.contains("pane");
         }
      }
   }

   private static boolean isGlassPaneBlock(Block b) {
      if (b instanceof BlockPane) {
         return true;
      } else {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("pane");
         }
      }
   }

   private static boolean isFurnaceBlock(Block b) {
      return b == Blocks.field_150460_al || b == Blocks.field_150470_am || b instanceof BlockFurnace;
   }

   private static boolean isStoneStairs(Block b, IBlockState s) {
      if (b instanceof BlockStairs && s.func_185904_a() == Material.field_151576_e) {
         return true;
      } else {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("stairs") && (path.contains("stone") || path.contains("brick") || path.contains("sandstone"));
         }
      }
   }

   private static boolean isWoodStairs(Block b, IBlockState s) {
      if (b instanceof BlockStairs && s.func_185904_a() == Material.field_151575_d) {
         return true;
      } else {
         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("stairs")
               && (
                  path.contains("oak")
                     || path.contains("spruce")
                     || path.contains("birch")
                     || path.contains("jungle")
                     || path.contains("acacia")
                     || path.contains("dark_oak")
                     || path.contains("wood")
               );
         }
      }
   }

   private static IBlockState copyCommonProps(IBlockState from, IBlockState to) {
      IBlockState out = to;

      for (IProperty<?> propTo : to.func_177227_a()) {
         if (from.func_177227_a().contains(propTo)) {
            try {
               Object val = from.func_177229_b(propTo);
               if (val != null && propTo.func_177700_c().contains(val)) {
                  out = out.func_177226_a(propTo, (Comparable)val);
               }
            } catch (Exception var7) {
            }
         }
      }

      return out;
   }

   private static boolean isCobbleBlock(Block b, IBlockState s) {
      if (b == Blocks.field_150347_e) {
         return true;
      } else {
         try {
            if (b == Blocks.field_150341_Y) {
               return true;
            }
         } catch (NoSuchFieldError var4) {
         }

         ResourceLocation rl = b.getRegistryName();
         if (rl == null) {
            return false;
         } else {
            String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
            return path.contains("cobblestone") && !path.contains("wall") && !path.contains("stairs");
         }
      }
   }

   static {
      conversionCache.put(Material.field_151578_c, SRPBlocks.InfestedStain.func_176223_P());
      conversionCache.put(Material.field_151577_b, SRPBlocks.InfestedStain.func_176223_P());
      conversionCache.put(Material.field_151595_p, SRPBlocks.InfestedSand.func_176223_P());
      conversionCache.put(Material.field_151576_e, SRPBlocks.InfestedRubble.func_176223_P());
   }

   private static class BlockMetaKey {
      private final Block block;
      private final int meta;

      private BlockMetaKey(Block block, int meta) {
         this.block = block;
         this.meta = meta;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof BeckonBlockInfestation.BlockMetaKey)) {
            return false;
         } else {
            BeckonBlockInfestation.BlockMetaKey other = (BeckonBlockInfestation.BlockMetaKey)obj;
            return this.block == other.block && this.meta == other.meta;
         }
      }

      @Override
      public int hashCode() {
         return System.identityHashCode(this.block) * 31 + this.meta;
      }
   }

   private static class ParsedState {
      private final Block block;
      private final int meta;
      private final IBlockState state;

      private ParsedState(Block block, int meta, IBlockState state) {
         this.block = block;
         this.meta = meta;
         this.state = state;
      }
   }
}
