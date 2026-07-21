package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockThornshade;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWall;
import net.minecraft.block.IGrowable;
import net.minecraft.block.BlockSandStone.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemInfestedBonemeal extends Item {
   private static final ResourceLocation RL_INFESTEDBUSH = new ResourceLocation("srparasites", "infestedbush");
   private static final ResourceLocation RL_PARASITEBUSH = new ResourceLocation("srparasites", "parasitebush");
   private static final ResourceLocation RL_TRESSES = new ResourceLocation("srparasites", "tresses_hair");
   private static final ResourceLocation RL_HIRSUTE = new ResourceLocation("srparasites", "hirsute_hair");
   private static final ResourceLocation RL_LIPOMA = new ResourceLocation("srparasites", "lipoma_mass");
   private static final ResourceLocation RL_INFESTED_STAIN = new ResourceLocation("srparasites", "infestedstain");
   private static final ResourceLocation RL_INFESTED_SAND = new ResourceLocation("srparasites", "infestedsand");
   private static final ResourceLocation RL_INFESTED_TRUNK = new ResourceLocation("srparasites", "infestedtrunk");
   private static final ResourceLocation RL_INFESTED_PLANKS = new ResourceLocation("srparasites", "infested_planks");
   private static final ResourceLocation RL_INFESTED_LEAVES = new ResourceLocation("srparasites", "infested_leaves");
   private static final ResourceLocation RL_INFESTED_STONEBRICK = new ResourceLocation("srparasites", "infested_stone_bricks");
   private static final ResourceLocation RL_INFESTED_WALL = new ResourceLocation("srparasites", "infestedrubble_wall");
   private static final ResourceLocation RL_INFESTED_FENCE = new ResourceLocation("srparasites", "infested_fence");
   private static final ResourceLocation RL_INFESTED_GLASS = new ResourceLocation("srparasites", "infested_glass");
   private static final ResourceLocation RL_INFESTED_GLASS_PANE = new ResourceLocation("srparasites", "infested_glass_pane");
   private static final ResourceLocation RL_INFESTED_RUBBLE = new ResourceLocation("srparasites", "infestedrubble");
   private static final ResourceLocation RL_INFESTED_COBBLE = new ResourceLocation("srparasites", "infested_cobblestone");
   private static final ResourceLocation RL_INFESTED_PLANKS_STAIRS = new ResourceLocation("srparasites", "infested_planks_stairs");
   private static final ResourceLocation RL_INFESTED_STONE_STAIRS = new ResourceLocation("srparasites", "infested_stone_stairs");
   private static final ResourceLocation RL_INFESTED_SANDSTONE_STAIRS = new ResourceLocation("srparasites", "infested_sandstone_stairs");
   private static final ResourceLocation RL_INFESTED_REMAIN = new ResourceLocation("srparasites", "infestedremain");
   private static final ResourceLocation RL_INF_SS = new ResourceLocation("srparasites", "inf_ss");
   private static final ResourceLocation RL_INF_SS_CHISELED = new ResourceLocation("srparasites", "inf_ss_chiseled");
   private static final ResourceLocation RL_PARASITE_RUBBLE = new ResourceLocation("srparasites", "parasiterubble");
   private static final ResourceLocation RL_INFESTED_ORE = new ResourceLocation("srparasites", "infestedore");
   private static final int RADIUS = 3;
   private static final int SAFE_SET_FLAGS = 18;

   public ItemInfestedBonemeal(String name) {
      this.setRegistryName("srparasites", name);
      this.func_77655_b("srparasites." + name);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
   }

   public static boolean boneMealEffect(World world, BlockPos pos, EnumFacing facing) {
      boolean any = false;
      Random r = world.field_73012_v;
      int r2 = 9;

      for (int dx = -3; dx <= 3; dx++) {
         for (int dy = -3; dy <= 3; dy++) {
            for (int dz = -3; dz <= 3; dz++) {
               if (dx * dx + dy * dy + dz * dz <= r2) {
                  BlockPos p = pos.func_177982_a(dx, dy, dz);
                  if (world.func_175625_s(p) == null && !isLiquid(world, p)) {
                     IBlockState s = world.func_180495_p(p);
                     Block b = s.func_177230_c();
                     if (!hasPendingTick(world, p, b) && b != Blocks.field_150350_a) {
                        ResourceLocation key = (ResourceLocation)Block.field_149771_c.func_177774_c(b);
                        String domain = key.func_110624_b();
                        String path = key.func_110623_a();
                        boolean converted = false;
                        if (b == Blocks.field_150439_ay) {
                           world.func_180501_a(p, Blocks.field_150450_ax.func_176223_P(), 18);
                           ((WorldServer)world).func_152344_a(() -> setBlockMeta(world, p, RL_INFESTED_ORE, 6));
                           smoke(world, p);
                           any = true;
                        } else {
                           Integer oreMeta = oreMetaFor(b, path);
                           if (oreMeta != null) {
                              converted = setBlockMeta(world, p, RL_INFESTED_ORE, oreMeta);
                           }

                           if (!converted && (isFlower(b, path) || isVine(b, path) || isCrop(b, path))) {
                              converted = setAir(world, p);
                           }

                           if (!converted) {
                              if (isGrassPath(b, path)) {
                                 converted = setBlock(world, p, RL_INFESTED_SAND);
                              } else if (b == Blocks.field_150347_e || path.contains("cobblestone")) {
                                 converted = setBlock(world, p, RL_INFESTED_COBBLE);
                              } else if (b == Blocks.field_150458_ak || path.contains("farmland")) {
                                 converted = setBlock(world, p, RL_INFESTED_SAND);
                              } else if (isWool(b, path)) {
                                 converted = setBlockMeta(world, p, RL_PARASITE_RUBBLE, 8);
                              } else if (b != Blocks.field_150322_A && !path.contains("sandstone")) {
                                 if (isSandstoneStairs(b, path)) {
                                    converted = setStairs(world, p, s, RL_INFESTED_SANDSTONE_STAIRS);
                                 } else if (isWoodStairs(b, s, path)) {
                                    converted = setStairs(world, p, s, RL_INFESTED_PLANKS_STAIRS);
                                 } else if (isStoneStairs(b, s, path)) {
                                    converted = setStairs(world, p, s, RL_INFESTED_STONE_STAIRS);
                                 } else if (b == Blocks.field_150348_b || path.equals("stone")) {
                                    converted = setBlock(world, p, RL_INFESTED_RUBBLE);
                                 } else if (b == Blocks.field_150346_d || path.contains("dirt")) {
                                    converted = setBlock(world, p, RL_INFESTED_STAIN);
                                 } else if (b == Blocks.field_150349_c) {
                                    converted = setBlock(world, p, RL_INFESTED_STAIN);
                                 } else if (b == Blocks.field_150354_m) {
                                    converted = setBlock(world, p, RL_INFESTED_SAND);
                                 } else if (isAnyLog(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_TRUNK);
                                 } else if (isAnyPlanks(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_PLANKS);
                                 } else if (isAnyLeaves(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_LEAVES);
                                 } else if (isAnyStoneBrick(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_STONEBRICK);
                                 } else if (isAnyWall(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_WALL);
                                 } else if (isAnyFence(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_FENCE);
                                 } else if (isAnyGlassPane(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_GLASS_PANE);
                                 } else if (isAnyGlassBlock(b, path)) {
                                    converted = setBlock(world, p, RL_INFESTED_GLASS);
                                 } else if (b instanceof BlockCarpet || path.contains("carpet")) {
                                    converted = setBlock(world, p, RL_INFESTED_REMAIN);
                                 } else if (b == Blocks.field_150435_aG || path.contains("clay")) {
                                    converted = setBlock(world, p, RL_INFESTED_SAND);
                                 }
                              } else if (isChiseledSandstone(s, b, path)) {
                                 converted = setBlock(world, p, RL_INF_SS_CHISELED);
                              } else {
                                 converted = setBlock(world, p, RL_INF_SS);
                              }
                           }

                           if (converted) {
                              smoke(world, p);
                              any = true;
                           }

                           s = world.func_180495_p(p);
                           b = s.func_177230_c();
                           key = (ResourceLocation)Block.field_149771_c.func_177774_c(b);
                           domain = key.func_110624_b();
                           path = key.func_110623_a();
                           if ("srparasites".equals(domain)) {
                              if (path.startsWith("infested") && !path.equals("infestedsand")) {
                                 BlockPos up = p.func_177984_a();
                                 IBlockState grow = pickInfestedPlant(r);
                                 if (grow != null && canPlaceClean(world, up, grow) && world.func_180501_a(up, grow, 18)) {
                                    smoke(world, up);
                                    any = true;
                                 }
                              }

                              if (path.startsWith("harleskinn") || path.startsWith("poland_skin_block")) {
                                 if (facing == EnumFacing.DOWN) {
                                    BlockPos under = p.func_177977_b();
                                    IBlockState lip = stateOf(RL_LIPOMA, 0);
                                    if (lip != null && canPlaceClean(world, under, lip) && world.func_180501_a(under, lip, 18)) {
                                       smoke(world, under);
                                       any = true;
                                    }
                                 } else {
                                    BlockPos up = p.func_177984_a();
                                    IBlockState hair = r.nextBoolean() ? stateOf(RL_TRESSES, 0) : stateOf(RL_HIRSUTE, 0);
                                    if (hair != null && canPlaceClean(world, up, hair) && world.func_180501_a(up, hair, 18)) {
                                       smoke(world, up);
                                       any = true;
                                    }
                                 }
                              }
                           }

                           if (!any) {
                              IBlockState above = world.func_180495_p(p.func_177984_a());
                              Block ab = above.func_177230_c();
                              ResourceLocation ak = (ResourceLocation)Block.field_149771_c.func_177774_c(ab);
                              String ad = ak == null ? "" : ak.func_110624_b();
                              String ap = ak == null ? "" : ak.func_110623_a();
                              if ("srparasites".equals(ad) && (ap.startsWith("harleskinn") || ap.startsWith("poland_skin_block"))) {
                                 IBlockState lip = stateOf(RL_LIPOMA, 0);
                                 if (lip != null && canPlaceClean(world, p, lip) && world.func_180501_a(p, lip, 18)) {
                                    smoke(world, p);
                                    any = true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return any;
   }

   public EnumActionResult func_180614_a(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack stack = player.func_184586_b(hand);
      if (world.field_72995_K) {
         return EnumActionResult.SUCCESS;
      } else {
         IBlockState clickedState = world.func_180495_p(pos);
         Block clickedBlock = clickedState.func_177230_c();
         if (clickedBlock instanceof BlockThornshade) {
            BlockThornshade thorn = (BlockThornshade)clickedBlock;
            int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, clickedState, stack, hand);
            if (hook > 0) {
               spawnSaplingUseParticles(world, pos, world.field_73012_v);
               if (!player.field_71075_bZ.field_75098_d) {
                  stack.func_190918_g(1);
               }

               return EnumActionResult.SUCCESS;
            } else if (hook < 0) {
               return EnumActionResult.PASS;
            } else if (thorn.func_176473_a(world, pos, clickedState, false)) {
               thorn.func_176474_b(world, world.field_73012_v, pos, clickedState);
               spawnSaplingUseParticles(world, pos, world.field_73012_v);
               if (!player.field_71075_bZ.field_75098_d) {
                  stack.func_190918_g(1);
               }

               return EnumActionResult.SUCCESS;
            } else {
               return EnumActionResult.PASS;
            }
         } else if (clickedBlock == SRPBlocks.ParasiteSapling) {
            int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, clickedState, stack, hand);
            if (hook > 0) {
               spawnSaplingUseParticles(world, pos, world.field_73012_v);
               if (!player.field_71075_bZ.field_75098_d) {
                  stack.func_190918_g(1);
               }

               return EnumActionResult.SUCCESS;
            } else if (hook < 0) {
               return EnumActionResult.PASS;
            } else {
               if (clickedBlock instanceof IGrowable) {
                  IGrowable growable = (IGrowable)clickedBlock;
                  if (growable.func_176473_a(world, pos, clickedState, world.field_72995_K)) {
                     if (!world.field_72995_K) {
                        if (growable.func_180670_a(world, world.field_73012_v, pos, clickedState)) {
                           growable.func_176474_b(world, world.field_73012_v, pos, clickedState);
                           spawnSaplingUseParticles(world, pos, world.field_73012_v);
                        }

                        if (!player.field_71075_bZ.field_75098_d) {
                           stack.func_190918_g(1);
                        }
                     }

                     return EnumActionResult.SUCCESS;
                  }
               }

               return EnumActionResult.PASS;
            }
         } else {
            boolean any = boneMealEffect(world, pos, facing);
            if (any && !player.field_71075_bZ.field_75098_d) {
               stack.func_190918_g(1);
            }

            return any ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
         }
      }
   }

   private static void spawnSaplingUseParticles(World world, BlockPos pos, Random rand) {
      if (!world.field_72995_K) {
         WorldServer ws = (WorldServer)world;
         double x = pos.func_177958_n() + 0.5;
         double y = pos.func_177956_o() + 0.6;
         double z = pos.func_177952_p() + 0.5;

         for (int i = 0; i < 4; i++) {
            double ox = (rand.nextDouble() - 0.5) * 0.4;
            double oy = rand.nextDouble() * 0.2 + 0.05;
            double oz = (rand.nextDouble() - 0.5) * 0.4;
            ws.func_175739_a(EnumParticleTypes.SMOKE_LARGE, x + ox, y, z + oz, 1, 0.0, 0.02, 0.0, 0.0, new int[0]);
         }

         for (int i = 0; i < 6; i++) {
            double ox = (rand.nextDouble() - 0.5) * 0.6;
            double oy = rand.nextDouble() * 0.3 + 0.1;
            double oz = (rand.nextDouble() - 0.5) * 0.6;
            ws.func_175739_a(EnumParticleTypes.VILLAGER_HAPPY, x + ox, y, z + oz, 1, 0.0, 0.02, 0.0, 0.0, new int[0]);
         }
      }
   }

   private static boolean isAnyLog(Block b, String path) {
      return b instanceof BlockLog || path.contains("log");
   }

   private static boolean isAnyPlanks(Block b, String path) {
      return b instanceof BlockPlanks || path.contains("plank");
   }

   private static boolean isAnyLeaves(Block b, String path) {
      return b instanceof BlockLeaves || path.contains("leaves") || path.contains("leaf");
   }

   private static boolean isAnyStoneBrick(Block b, String path) {
      return path.contains("stonebrick") || path.contains("stone_brick");
   }

   private static boolean isAnyWall(Block b, String path) {
      return b instanceof BlockWall || path.endsWith("_wall") || path.contains("wall");
   }

   private static boolean isAnyFence(Block b, String path) {
      return b instanceof BlockFence || path.endsWith("_fence") || path.contains("fence");
   }

   private static boolean isAnyGlassBlock(Block b, String path) {
      return b instanceof BlockGlass && !(b instanceof BlockPane) || path.contains("glass") && !path.contains("pane");
   }

   private static boolean isAnyGlassPane(Block b, String path) {
      return b instanceof BlockPane || path.contains("pane");
   }

   private static boolean isSandstoneStairs(Block b, String path) {
      return b instanceof BlockStairs && path.contains("sandstone") && path.contains("stairs");
   }

   private static boolean isWoodStairs(Block b, IBlockState s, String path) {
      return b instanceof BlockStairs && s.func_185904_a() == Material.field_151575_d;
   }

   private static boolean isStoneStairs(Block b, IBlockState s, String path) {
      if (!(b instanceof BlockStairs)) {
         return false;
      } else {
         return isSandstoneStairs(b, path) ? false : s.func_185904_a() == Material.field_151576_e;
      }
   }

   private static boolean isChiseledSandstone(IBlockState s, Block b, String path) {
      return b == Blocks.field_150322_A && s.func_177228_b().containsKey(BlockSandStone.field_176297_a)
         ? s.func_177229_b(BlockSandStone.field_176297_a) == EnumType.CHISELED
         : path.contains("chiseled") && path.contains("sandstone");
   }

   private static boolean isFlower(Block b, String path) {
      return b instanceof BlockFlower || b instanceof BlockDoublePlant || path.contains("flower");
   }

   private static boolean isVine(Block b, String path) {
      return b instanceof BlockVine || path.contains("vine");
   }

   private static boolean isCrop(Block b, String path) {
      return b instanceof BlockCrops || b instanceof BlockStem || b instanceof BlockReed || path.contains("wart");
   }

   private static boolean isGrassPath(Block b, String path) {
      return b == Blocks.field_185774_da || path.contains("grass_path") || path.endsWith("_path");
   }

   private static boolean isWool(Block b, String path) {
      return b == Blocks.field_150325_L || path.contains("wool");
   }

   private static Integer oreMetaFor(Block b, String path) {
      if (b == Blocks.field_150365_q) {
         return 0;
      } else if (b == Blocks.field_150482_ag) {
         return 1;
      } else if (b == Blocks.field_150412_bA) {
         return 2;
      } else if (b == Blocks.field_150352_o) {
         return 3;
      } else if (b == Blocks.field_150366_p) {
         return 4;
      } else if (b == Blocks.field_150369_x) {
         return 5;
      } else if (b == Blocks.field_150450_ax) {
         return 6;
      } else {
         return path != null && path.contains("ore") ? 7 : null;
      }
   }

   private static boolean setAir(World w, BlockPos p) {
      return w.func_180501_a(p, Blocks.field_150350_a.func_176223_P(), 18);
   }

   private static boolean setBlock(World w, BlockPos p, ResourceLocation rl) {
      IBlockState st = stateOf(rl, 0);
      return st != null && w.func_180501_a(p, st, 18);
   }

   private static boolean setBlockMeta(World w, BlockPos p, ResourceLocation rl, int meta) {
      IBlockState st = stateOf(rl, meta);
      return st != null && w.func_180501_a(p, st, 18);
   }

   private static boolean hasPendingTick(World world, BlockPos p, Block b) {
      return world instanceof WorldServer && ((WorldServer)world).func_175691_a(p, b);
   }

   private static boolean setStairs(World w, BlockPos p, IBlockState srcStairsState, ResourceLocation targetRL) {
      IBlockState tgt = stateOf(targetRL, 0);
      if (tgt == null) {
         return false;
      } else {
         tgt = copyStairProps(srcStairsState, tgt);
         return w.func_180501_a(p, tgt, 18);
      }
   }

   private static IBlockState copyStairProps(IBlockState from, IBlockState to) {
      to = copyPropByName(from, to, "facing");
      to = copyPropByName(from, to, "half");
      return copyPropByName(from, to, "shape");
   }

   private static IBlockState copyPropByName(IBlockState from, IBlockState to, String propName) {
      IProperty src = findProp(from, propName);
      IProperty tgt = findProp(to, propName);
      if (src != null && tgt != null) {
         Comparable val = from.func_177229_b(src);
         String name = src.func_177702_a(val);

         for (Object cand : tgt.func_177700_c()) {
            if (tgt.func_177702_a((Comparable)cand).equals(name)) {
               to = to.func_177226_a(tgt, (Comparable)cand);
               break;
            }
         }

         return to;
      } else {
         return to;
      }
   }

   private static IProperty<?> findProp(IBlockState st, String name) {
      for (IProperty<?> p : st.func_177227_a()) {
         if (p.func_177701_a().equals(name)) {
            return p;
         }
      }

      return null;
   }

   private static boolean canPlaceClean(World w, BlockPos p, IBlockState stateToPlace) {
      if (!canReplace(w, p)) {
         return false;
      } else {
         return isLiquid(w, p) ? false : stateToPlace.func_177230_c().func_176196_c(w, p);
      }
   }

   private static boolean canReplace(World w, BlockPos p) {
      IBlockState s = w.func_180495_p(p);
      return w.func_175623_d(p) || s.func_177230_c().func_176200_f(w, p);
   }

   private static boolean isLiquid(World w, BlockPos p) {
      return w.func_180495_p(p).func_185904_a().func_76224_d();
   }

   private static void smoke(World w, BlockPos p) {
      if (w instanceof WorldServer) {
         ((WorldServer)w)
            .func_175739_a(
               EnumParticleTypes.SMOKE_LARGE, p.func_177958_n() + 0.5, p.func_177956_o() + 0.5, p.func_177952_p() + 0.5, 6, 0.3, 0.3, 0.3, 0.01, new int[0]
            );
      }
   }

   private static IBlockState stateOf(ResourceLocation rl, int meta) {
      Block b = (Block)ForgeRegistries.BLOCKS.getValue(rl);
      return b == null ? null : b.func_176203_a(meta);
   }

   private static IBlockState pickInfestedPlant(Random r) {
      switch (r.nextInt(5)) {
         case 0:
            return stateOf(RL_INFESTEDBUSH, 0);
         case 1:
            return stateOf(RL_INFESTEDBUSH, 1);
         case 2:
            return stateOf(RL_INFESTEDBUSH, 2);
         case 3:
            return stateOf(RL_INFESTEDBUSH, 5);
         default:
            return stateOf(RL_PARASITEBUSH, 4);
      }
   }
}
