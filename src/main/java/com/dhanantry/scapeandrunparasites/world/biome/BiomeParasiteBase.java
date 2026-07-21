package com.dhanantry.scapeandrunparasites.world.biome;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class BiomeParasiteBase extends Biome {
   protected WorldGenAbstractTree tree;

   public BiomeParasiteBase(BiomeProperties properties) {
      super(properties);
   }

   public void setBlocks() {
      String[] blockList = this.getDirt().split(":");
      Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
      this.field_76752_A = one.func_176203_a(Integer.parseInt(blockList[2]));
      blockList = this.getStone().split(":");
      one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
      this.field_76753_B = one.func_176203_a(Integer.parseInt(blockList[2]));
      this.field_76760_I = new BiomeParasiteDecorator(this);
   }

   public void mobListClear() {
      this.field_76762_K.clear();
      this.field_76761_J.clear();
      this.field_76755_L.clear();
      this.field_82914_M.clear();
   }

   public abstract float getRedValue();

   public abstract float getGreenValue();

   public abstract float getBlueValue();

   public abstract String[] getBlockList();

   public abstract String getDirt();

   public abstract String getGravel();

   public abstract String getSand();

   public abstract String getLog();

   public abstract String getStone();

   public abstract String getCobblestone();

   public abstract String getSandstone();

   public abstract String getLeaves();

   public abstract String getLeavesG();

   public abstract String getPlank();

   public abstract String getBush();

   public abstract void spawnGenFeatureParasite(World var1, BlockPos var2, Random var3);

   public abstract void spawnGenRoofParasite(World var1, BlockPos var2, Random var3);

   public int convertBlock(BlockPos helper, World worldIn, Random rand) {
      IBlockState lookingState = worldIn.func_180495_p(helper);
      Material mat = lookingState.func_185904_a();
      Block lookingBlock = lookingState.func_177230_c();
      if (lookingBlock instanceof IMetaName) {
         return 0;
      } else if (mat != Material.field_151579_a && mat != Material.field_151586_h && mat != Material.field_151587_i) {
         ResourceLocation rl = lookingBlock.getRegistryName();
         if (rl == null) {
            return 0;
         } else {
            String lookingName = rl.toString();
            int currentMeta = lookingBlock.func_176201_c(lookingState);
            String[] blockList = this.getBlockList();
            if (transformBlockList(helper, worldIn, lookingName, currentMeta, blockList)) {
               return 1;
            } else if (ParasiteEventWorld.blockException(
               worldIn, helper, lookingBlock, lookingState, SRPConfigWorld.blockBBiomeList, SRPConfigWorld.blockBBiomeListWhite, SRPConfigWorld.biomeBlockIMaxH
            )) {
               return 0;
            } else if (transformBlockList(helper, worldIn, lookingName, currentMeta, blockList)) {
               return 1;
            } else if (lookingBlock.isWood(worldIn, helper) || lookingBlock == SRPBlocks.InfestedTrunk) {
               blockList = this.getLog().split(":");
               Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
               worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
               return 1;
            } else if (lookingBlock.isLeaves(lookingState, worldIn, helper)) {
               blockList = this.getLeaves().split(":");
               Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
               worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
               if (rand.nextInt(100) < 30) {
                  BlockPos below = helper.func_177977_b();
                  if (worldIn.func_175623_d(below)) {
                     blockList = this.getLeavesG().split(":");
                     one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                     IBlockState growth = setFacingIfPresent(one.func_176203_a(Integer.parseInt(blockList[2])), EnumFacing.DOWN);
                     worldIn.func_180501_a(below, growth, 2);
                  }
               }

               return 1;
            } else {
               if (lookingBlock == SRPBlocks.InfestedStain) {
                  blockList = this.getDirt().split(":");
                  Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                  worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                  this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                  this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
               }

               if (mat == Material.field_151578_c || mat == Material.field_151577_b) {
                  blockList = this.getDirt().split(":");
                  Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                  worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                  this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                  this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
                  return 1;
               } else if (lookingBlock instanceof BlockSand) {
                  blockList = this.getSand().split(":");
                  Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                  worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                  this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                  this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
                  return 1;
               } else if (lookingBlock instanceof BlockSandStone) {
                  blockList = this.getSandstone().split(":");
                  Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                  worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                  this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                  this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
                  return 1;
               } else if (lookingBlock == Blocks.field_150351_n || mat == Material.field_151595_p) {
                  blockList = this.getGravel().split(":");
                  Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                  worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                  this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                  this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
                  return 1;
               } else if (lookingBlock == Blocks.field_150347_e) {
                  blockList = this.getCobblestone().split(":");
                  Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                  worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                  this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                  this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
                  return 1;
               } else if (mat == Material.field_151576_e) {
                  if (lookingBlock != SRPBlocks.BiomeHeart && lookingBlock != SRPBlocks.ColonyHeart) {
                     if (lookingBlock == Blocks.field_150343_Z) {
                        worldIn.func_175656_a(
                           helper, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.OBSIDIAN)
                        );
                     } else if (lookingBlock.getRegistryName().toString().contains("brick")) {
                        worldIn.func_175656_a(
                           helper, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS)
                        );
                     } else {
                        blockList = this.getStone().split(":");
                        Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
                        worldIn.func_175656_a(helper, one.func_176203_a(Integer.parseInt(blockList[2])));
                     }

                     this.spawnGenFeatureParasite(worldIn, helper.func_177984_a(), rand);
                     this.spawnGenRoofParasite(worldIn, helper.func_177977_b(), rand);
                     return 1;
                  } else {
                     return 0;
                  }
               } else if (mat == Material.field_151585_k) {
                  worldIn.func_175656_a(
                     helper, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH)
                  );
                  return 1;
               } else if (mat == Material.field_151588_w || mat == Material.field_151598_x) {
                  worldIn.func_175656_a(helper, SRPBlocks.BloodyIce.func_176223_P());
                  return 1;
               } else if (mat == Material.field_151573_f) {
                  worldIn.func_175656_a(
                     helper, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.METAL)
                  );
                  return 1;
               } else if (mat == Material.field_151575_d && lookingBlock != SRPBlocks.InfestedTrunk) {
                  if (lookingBlock.getRegistryName().toString().contains("mushroom")) {
                     worldIn.func_175656_a(
                        helper, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FUNGUS)
                     );
                  } else {
                     worldIn.func_175656_a(
                        helper, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.WOOD)
                     );
                  }

                  return 1;
               } else {
                  return 0;
               }
            }
         }
      } else {
         return 0;
      }
   }

   private static IBlockState setFacingIfPresent(IBlockState state, EnumFacing face) {
      for (IProperty<?> p : state.func_177227_a()) {
         if (p.func_177701_a().equalsIgnoreCase("facing") && p.func_177699_b() == EnumFacing.class && p.func_177700_c().contains(face)) {
            return state.func_177226_a(p, face);
         }
      }

      return state;
   }

   public static boolean transformBlockList(BlockPos helper, World worldIn, String name, int meta, String[] rules) {
      if (rules != null && rules.length != 0) {
         for (String rule : rules) {
            String[] parts = rule.split(";");
            if (parts.length == 2) {
               String[] src = parts[0].split(":");
               if (src.length == 3) {
                  String[] dst = parts[1].split(":");
                  if (dst.length == 3) {
                     String[] nm = name.split(":");
                     if (nm.length == 2) {
                        if ("minecraft:glass".equals(name)) {
                           System.out.println("[SRP] transformBlockList evaluating GLASS: rule=" + rule + " meta=" + meta);
                        }

                        if (nm[0].equals(src[0]) && nm[1].equals(src[1]) && meta == Integer.parseInt(src[2])) {
                           Block putting = Block.func_149684_b(dst[0] + ":" + dst[1]);
                           if (putting != null) {
                              int dstMeta = Integer.parseInt(dst[2]);
                              worldIn.func_180501_a(helper, putting.func_176203_a(dstMeta), 3);
                              return true;
                           }
                        }
                     }
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }
}
