package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityKol;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteBigBall;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyB1;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyB2;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyB3;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyBS1;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyBase;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteMouth;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteSpine;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTallFlower;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTenFlower;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTree;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTreeThin;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockColonyOutpost extends BlockBase {
   public static final PropertyInteger ACTIVE = PropertyInteger.func_177719_a("active", 0, 3);

   public BlockColonyOutpost(Material material, String name, float hardness, boolean creative, boolean tick, float resistance) {
      super(material, name, hardness, creative, tick, resistance);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(ACTIVE, 0));
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      int i = 2;
      Random rand = new Random();
      if (worldIn.field_72995_K) {
         return false;
      } else {
         switch (i) {
            case -2:
               this.func_180650_b(worldIn, pos, state, rand);
            case -1:
            case 0:
            case 4:
            case 6:
            case 13:
            default:
               break;
            case 1:
               EntityKol out = new EntityKol(worldIn);
               out.func_70080_a(pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p(), 0.0F, 0.0F);
               worldIn.func_72838_d(out);
               out.setOrigin(pos);
               break;
            case 2:
               new WorldGenParasiteColonyB3(false, 1).func_180709_b(worldIn, new Random(), pos.func_177984_a());
               break;
            case 3:
               worldIn.func_180501_a(pos, Blocks.field_150355_j.func_176223_P(), 1);
               break;
            case 5:
               ParasiteEventEntity.spawnFromBlock(worldIn, new String[]{"srparasites:worker;1"}, 5, pos.func_177984_a());
               break;
            case 7:
               if (!new WorldGenParasiteTreeThin(false).func_180709_b(worldIn, rand, pos.func_177981_b(3))
                  && worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_175656_a(
                     pos.func_177981_b(3),
                     SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
                  );
               }
               break;
            case 8:
               if (!new WorldGenParasiteTree(false).func_180709_b(worldIn, rand, pos.func_177981_b(3))
                  && worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_175656_a(
                     pos.func_177981_b(3),
                     SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
                  );
               }
               break;
            case 9:
               if (!new WorldGenParasiteTenFlower(false).func_180709_b(worldIn, rand, pos.func_177981_b(3))
                  && worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_175656_a(
                     pos.func_177981_b(3),
                     SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
                  );
               }
               break;
            case 10:
               if (!new WorldGenParasiteTallFlower(false).func_180709_b(worldIn, rand, pos.func_177981_b(3))
                  && worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_175656_a(
                     pos.func_177981_b(3),
                     SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
                  );
               }
               break;
            case 11:
               if (!new WorldGenParasiteSpine(false).func_180709_b(worldIn, rand, pos.func_177981_b(3))
                  && worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_175656_a(
                     pos.func_177981_b(3),
                     SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
                  );
               }
               break;
            case 12:
               if (!new WorldGenParasiteMouth(false).func_180709_b(worldIn, rand, pos.func_177981_b(3))
                  && worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_175656_a(
                     pos.func_177981_b(3),
                     SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
                  );
               }
               break;
            case 14:
               BeckonBlockInfestation.spawnGenRoofInfested(worldIn, pos.func_177979_c(1), rand);
               break;
            case 15:
               new WorldGenParasiteBigBall(false).func_180709_b(worldIn, new Random(), pos.func_177984_a());
         }

         return false;
      }
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (worldIn.func_175697_a(pos, 3) && !worldIn.field_72995_K) {
         if (SRPConfigWorld.coloniesActivated) {
            SRPWorldData data = SRPWorldData.get(worldIn);
            if (data.nearestColonyPosition(pos, false) != null) {
               int meta = this.func_176201_c(state);
               if (meta > 0) {
                  AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                        pos.func_177958_n(),
                        pos.func_177956_o(),
                        pos.func_177952_p(),
                        pos.func_177958_n() + 1,
                        pos.func_177956_o() + 1,
                        pos.func_177952_p() + 1
                     )
                     .func_72314_b(20.0, 5.0, 20.0);
                  List<EntityKol> moblist = worldIn.func_72872_a(EntityKol.class, axisalignedbb);
                  if (moblist.isEmpty()) {
                     ParasiteEventEntity.spawnFromBlock(worldIn, new String[]{"srparasites:worker;1"}, 5, pos.func_177984_a());
                     ParasiteEventEntity.spawnFromBlock(worldIn, new String[]{"srparasites:worker;1"}, 5, pos.func_177984_a());
                  }
               }

               switch (meta) {
                  case 1:
                     if (rand.nextBoolean()) {
                        if (rand.nextInt(4) == 0) {
                           if (this.makePillar(worldIn, pos, 23, 4)) {
                              WorldGenParasiteColonyBase tree = new WorldGenParasiteColonyB3(false, 2);
                              tree.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                           }
                        } else if (rand.nextBoolean()) {
                           if (this.makePillar(worldIn, pos, 23, 4)) {
                              WorldGenParasiteColonyBase tree = new WorldGenParasiteColonyB2(false, 2);
                              tree.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                           }
                        } else if (this.makePillar(worldIn, pos, 23, 4)) {
                           WorldGenParasiteColonyBase tree = new WorldGenParasiteColonyB1(false, 2);
                           tree.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                        }
                     }
                     break;
                  case 2:
                     if (rand.nextBoolean()) {
                        if (rand.nextBoolean()) {
                           if (this.makePillar(worldIn, pos, 13, 3)) {
                              WorldGenParasiteColonyBS1 tree = new WorldGenParasiteColonyBS1(false, 2);
                              tree.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                           }
                        } else if (this.makePillar(worldIn, pos, 13, 3)) {
                           WorldGenParasiteColonyBS1 tree = new WorldGenParasiteColonyBS1(false, 2);
                           tree.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                        }
                     }
               }
            }
         }
      }
   }

   private boolean makePillar(World worldIn, BlockPos pos, int totalCheck, int blocks) {
      pos = pos.func_177984_a();
      int times = 0;
      Block look = worldIn.func_180495_p(pos).func_177230_c();

      while (blocks > 0) {
         boolean g = false;
         if (look == SRPBlocks.ParasiteStain) {
            times++;
            pos = pos.func_177984_a();
            look = worldIn.func_180495_p(pos).func_177230_c();
            g = times >= totalCheck;
            if (g) {
               return true;
            }
         } else {
            if (look != Blocks.field_150350_a && !(look instanceof BlockLeaves)) {
            }

            times++;
            g = times >= totalCheck;
            blocks--;
            this.placePillarBlock(worldIn, pos, blocks <= 0);
            pos = pos.func_177984_a();
            look = worldIn.func_180495_p(pos).func_177230_c();
            if (g) {
               return true;
            }
         }
      }

      return false;
   }

   private void placePillarBlock(World worldIn, BlockPos pos, boolean flag) {
      int xx = pos.func_177958_n();
      int zz = pos.func_177952_p();
      int yy = pos.func_177956_o();
      int range = 10;

      for (int x = xx - range; x <= xx + range; x++) {
         for (int z = zz - range; z <= zz + range; z++) {
            BlockPos neww = new BlockPos(x, yy, z);
            float har = worldIn.func_180495_p(neww).func_185887_b(worldIn, neww);
            if (!(har > 7.0F) && !(worldIn.func_180495_p(neww).func_177230_c() instanceof BlockBase)) {
               worldIn.func_175655_b(neww, true);
            }
         }
      }

      worldIn.func_175656_a(pos, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH));
      if (flag) {
         AxisAlignedBB axisalignedbb2 = new AxisAlignedBB(
               pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1
            )
            .func_72314_b(32.0, 16.0, 32.0);
         List<EntityItem> moblist2 = worldIn.func_72872_a(EntityItem.class, axisalignedbb2);
         if (moblist2.size() > 7) {
            worldIn.func_180501_a(pos.func_177978_c(), SRPBlocks.ParasiteCanisterActive.func_176223_P(), 3);
         }
      }
   }

   public EnumPushReaction func_149656_h(IBlockState state) {
      return EnumPushReaction.BLOCK;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{ACTIVE});
   }

   public int func_176201_c(IBlockState state) {
      return (Integer)state.func_177229_b(ACTIVE);
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(ACTIVE, meta & 3);
   }
}
