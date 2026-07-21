package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockBiomeCore;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNodeCore extends WorldGenParasiteGenAbstract {
   private int core;
   private int type;

   public WorldGenParasiteNodeCore(boolean notify, int stage, int biomeType) {
      super(notify);
      this.core = stage;
      this.type = biomeType;
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      switch (this.type) {
         case 3:
            this.placeHeartHarlequin(worldIn, rand, position);
            break;
         default:
            this.placeHeartShrouded(worldIn, rand, position);
      }

      this.placeCore(worldIn, position, this.core);
      return true;
   }

   private boolean placeHeartShrouded(World worldIn, Random rand, BlockPos position) {
      switch (this.core) {
         case 1:
            BlockPos helper = position;
            int down = 5;

            while (down >= 0 && helper.func_177977_b().func_177956_o() >= 1) {
               helper = helper.func_177977_b();
               this.placeTrunk(worldIn, helper);
               down--;

               for (int yyy = 0; yyy <= 0; yyy++) {
                  for (int xs = -1; xs <= 1; xs++) {
                     for (int zs = -1; zs <= 1; zs++) {
                        BlockPos helperTwo = new BlockPos(helper.func_177958_n() + xs, helper.func_177956_o(), helper.func_177952_p() + zs);
                        this.placeTrunk(worldIn, helperTwo);

                        for (int z = 0; z <= 3; z++) {
                           this.placeTrunk(worldIn, this.directionToGrow(helperTwo, z, false));
                           if (xs == 0 && zs == 0) {
                              BlockPos helperTwo2 = helperTwo;

                              for (int kkk = 0; kkk <= 2; kkk++) {
                                 helperTwo = this.directionToGrow(helperTwo, z, false);
                              }

                              this.placeDirt(worldIn, helperTwo);
                              helperTwo = helperTwo2;
                           }
                        }
                     }
                  }
               }
            }

            down = 2;

            while (down >= 0 && helper.func_177977_b().func_177956_o() >= 1) {
               helper = helper.func_177977_b();
               this.placeTrunk(worldIn, helper);
               down--;

               for (int yyy = 0; yyy <= 0; yyy++) {
                  for (int xs = -1; xs <= 1; xs++) {
                     for (int zs = -1; zs <= 1; zs++) {
                        BlockPos var21 = new BlockPos(helper.func_177958_n() + xs, helper.func_177956_o(), helper.func_177952_p() + zs);
                        this.placeTrunk(worldIn, var21);

                        for (int zx = 0; zx <= 3; zx++) {
                           if (worldIn.func_180495_p(this.directionToGrow(var21, zx, false)).func_177230_c() != SRPBlocks.ParasiteRubbleDense) {
                              this.placeDirt(worldIn, this.directionToGrow(var21, zx, false));
                           }
                        }
                     }
                  }
               }
            }

            this.placeDirt(worldIn, position.func_177977_b());
            worldIn.func_175654_a(position.func_177977_b(), worldIn.func_180495_p(position.func_177977_b()).func_177230_c(), 60, 5);

            for (int i = 0; i <= 3; i++) {
               helper = this.directionToGrow(position, i, false);
               if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                  this.placeTrunk(worldIn, helper.func_177977_b());
               }

               this.placeTrunk(worldIn, helper);
               BlockPos rootH = helper;

               for (int oxx = 0; oxx < 2; oxx++) {
                  if (oxx == 0) {
                     helper = this.directionToGrow(rootH, (i + 1) % 4, false);
                  } else {
                     helper = this.directionToGrow(rootH, (i + 3) % 4, false);
                  }

                  if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                     this.placeTrunk(worldIn, helper.func_177977_b());
                  }

                  this.placeTrunk(worldIn, helper);
               }

               helper = this.directionToGrow(rootH, i, false);
               if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                  this.placeTrunk(worldIn, helper.func_177977_b());
               }

               this.placeTrunk(worldIn, helper);
               rootH = helper;

               for (int oxx = 0; oxx < 2; oxx++) {
                  if (oxx == 0) {
                     helper = this.directionToGrow(rootH, (i + 1) % 4, false);
                  } else {
                     helper = this.directionToGrow(rootH, (i + 3) % 4, false);
                  }

                  if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                     this.placeTrunk(worldIn, helper.func_177977_b());
                  }

                  this.placeTrunk(worldIn, helper);
               }

               helper = this.directionToGrow(rootH, i, false);
               if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                  this.placeTrunk(worldIn, helper.func_177977_b());
               }

               this.placeTrunk(worldIn, helper);
            }

            position = position.func_177984_a();
            this.placeTrunk(worldIn, position);
            this.placePeri(worldIn, position);
            position = position.func_177984_a();
            this.placeTrunk(worldIn, position);
            break;
         case 2:
            position = position.func_177981_b(2);
            this.placeLiquid(worldIn, position);

            for (int i = 0; i < 3; i++) {
               this.placePeri(worldIn, position);
               this.placeLiquid(worldIn, position);
               position = position.func_177984_a();
            }

            int dir = 0;

            for (int i = 0; i <= 3; i++) {
               BlockPos var23 = this.getDirectionRoot(position.func_177977_b(), i, 2);
               BlockPos root = var23;

               for (int o = 0; o < 2; o++) {
                  if (o == 0) {
                     if (rand.nextInt(2) == 0) {
                        dir = (i + 1) % 4;
                        var23 = this.directionToGrow(root, dir, false);
                        this.placeTen(worldIn, var23);
                        var23 = this.directionToGrow(var23, i, false);
                        this.placeTen(worldIn, var23);

                        while (!worldIn.func_180495_p(var23.func_177977_b()).func_185913_b() && var23.func_177977_b().func_177956_o() >= 1) {
                           if (rand.nextInt(1) == 0) {
                              var23 = this.directionToGrow(var23, i * 10, true);
                           } else {
                              var23 = this.directionToGrow(var23, i, false);
                           }

                           var23 = this.placeColumn(worldIn, var23, rand.nextInt(2) + 2, rand, 0.0);
                        }

                        this.placeDirt(worldIn, var23.func_177977_b());
                     }
                  } else if (rand.nextInt(2) == 0) {
                     dir = (i + 3) % 4;
                     var23 = this.directionToGrow(root, dir, false);
                     this.placeTen(worldIn, var23);
                     var23 = this.directionToGrow(var23, i, false);
                     this.placeTen(worldIn, var23);

                     while (!worldIn.func_180495_p(var23.func_177977_b()).func_185913_b() && var23.func_177977_b().func_177956_o() >= 1) {
                        if (rand.nextInt(1) == 0) {
                           var23 = this.directionToGrow(var23, i * 10 + 1, true);
                        } else {
                           var23 = this.directionToGrow(var23, i, false);
                        }

                        this.placeTen(worldIn, var23);
                        var23 = this.placeColumn(worldIn, var23, rand.nextInt(2) + 2, rand, 0.0);
                     }

                     this.placeDirt(worldIn, var23.func_177977_b());
                  }
               }
            }

            this.placeLiquid(worldIn, position);

            for (int i = 0; i < 5; i++) {
               this.placePeri(worldIn, position);
               this.placeLiquid(worldIn, position);
               position = position.func_177984_a();
            }

            for (int i = 0; i <= 3; i++) {
               BlockPos var30 = this.getDirectionRoot(position.func_177977_b(), i, 2);
               BlockPos var40 = var30;

               for (int ox = 0; ox < 2; ox++) {
                  if (ox == 0) {
                     if (rand.nextInt(2) == 0) {
                        dir = (i + 1) % 4;
                        var30 = this.directionToGrow(var40, dir, false);
                        this.placeTen(worldIn, var30);
                        var30 = this.directionToGrow(var30, i, false);
                        this.placeTen(worldIn, var30);

                        while (!worldIn.func_180495_p(var30.func_177977_b()).func_185913_b() && var30.func_177977_b().func_177956_o() >= 1) {
                           if (rand.nextInt(1) == 0) {
                              var30 = this.directionToGrow(var30, i * 10, true);
                           } else {
                              var30 = this.directionToGrow(var30, i, false);
                           }

                           this.placeTen(worldIn, var30);
                           var30 = this.placeColumn(worldIn, var30, rand.nextInt(2) + 1, rand, 0.0);
                        }

                        this.placeDirt(worldIn, var30.func_177977_b());
                     }
                  } else if (rand.nextInt(2) == 0) {
                     dir = (i + 3) % 4;
                     var30 = this.directionToGrow(var40, dir, false);
                     this.placeTen(worldIn, var30);
                     var30 = this.directionToGrow(var30, i, false);
                     this.placeTen(worldIn, var30);

                     while (!worldIn.func_180495_p(var30.func_177977_b()).func_185913_b() && var30.func_177977_b().func_177956_o() >= 1) {
                        if (rand.nextInt(1) == 0) {
                           var30 = this.directionToGrow(var30, i * 10 + 1, true);
                        } else {
                           var30 = this.directionToGrow(var30, i, false);
                        }

                        this.placeTen(worldIn, var30);
                        var30 = this.placeColumn(worldIn, var30, rand.nextInt(2) + 1, rand, 0.0);
                     }

                     this.placeDirt(worldIn, var30.func_177977_b());
                  }
               }
            }

            this.placeLiquid(worldIn, position);

            for (int i = 0; i < 5; i++) {
               this.placePeri(worldIn, position);
               this.placeLiquid(worldIn, position);
               position = position.func_177984_a();
            }

            this.placeTrunk(worldIn, position);
         case 3:
      }

      return true;
   }

   private boolean placeHeartBoils(World worldIn, Random rand, BlockPos position) {
      switch (this.core) {
         case 1:
         case 2:
         case 3:
         default:
            return true;
      }
   }

   private boolean placeHeartHarlequin(World worldIn, Random rand, BlockPos position) {
      switch (this.core) {
         case 1:
         case 2:
         case 3:
         default:
            return true;
      }
   }

   private boolean placeHeartDemen(World worldIn, Random rand, BlockPos position) {
      switch (this.core) {
         case 1:
         case 2:
         case 3:
         default:
            return true;
      }
   }

   private void placeTen(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER));
   }

   private void placeTrunk(World worldIn, BlockPos pos) {
      this.func_175903_a(
         worldIn, pos, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.BIOME)
      );
   }

   private void placeCore(World worldIn, BlockPos pos, int stage) {
      this.func_175903_a(worldIn, pos, SRPBlocks.BiomeHeart.func_176223_P().func_177226_a(BlockBiomeCore.ACTIVE, stage));
   }

   private void placeDirt(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P());
   }

   private void placeLiquid(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.DeadBlood.func_176223_P());
   }

   private BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
      switch (direction) {
         case 0:
            return center.func_177964_d(times);
         case 1:
            return center.func_177965_g(times);
         case 2:
            return center.func_177970_e(times);
         default:
            return center.func_177985_f(times);
      }
   }

   private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
      int current = pos.func_177956_o();
      int atm = current;
      int times = 0;

      BlockPos newPos;
      for (newPos = pos; current < atm + in; times++) {
         this.placeTen(worldIn, newPos);
         newPos = newPos.func_177977_b();
         current++;
      }

      this.placeTen(worldIn, newPos);
      return newPos;
   }

   private void placePeri(World worldIn, BlockPos position) {
      for (int i = 0; i <= 3; i++) {
         BlockPos helper = this.directionToGrow(position, i, false);
         if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
            this.placeTrunk(worldIn, helper.func_177977_b());
         }

         this.placeTrunk(worldIn, helper);
         BlockPos rootH = helper;

         for (int o = 0; o < 2; o++) {
            if (o == 0) {
               helper = this.directionToGrow(rootH, (i + 1) % 4, false);
            } else {
               helper = this.directionToGrow(rootH, (i + 3) % 4, false);
            }

            if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
               this.placeTrunk(worldIn, helper.func_177977_b());
            }

            this.placeTrunk(worldIn, helper);
         }
      }
   }

   private BlockPos directionToGrow(BlockPos atm, int choice, boolean sideCurse) {
      if (sideCurse) {
         switch (choice) {
            case 0:
               atm = atm.func_177978_c();
               atm = atm.func_177974_f();
               break;
            case 1:
               atm = atm.func_177978_c();
               atm = atm.func_177976_e();
               break;
            case 10:
               atm = atm.func_177974_f();
               atm = atm.func_177968_d();
               break;
            case 11:
               atm = atm.func_177974_f();
               atm = atm.func_177978_c();
               break;
            case 20:
               atm = atm.func_177968_d();
               atm = atm.func_177976_e();
               break;
            case 21:
               atm = atm.func_177968_d();
               atm = atm.func_177974_f();
               break;
            case 30:
               atm = atm.func_177976_e();
               atm = atm.func_177978_c();
               break;
            default:
               atm = atm.func_177976_e();
               atm = atm.func_177968_d();
         }

         return atm;
      } else {
         switch (choice) {
            case 0:
               atm = atm.func_177978_c();
               break;
            case 1:
               atm = atm.func_177974_f();
               break;
            case 2:
            default:
               atm = atm.func_177968_d();
               break;
            case 3:
               atm = atm.func_177976_e();
         }

         return atm;
      }
   }

   private void positionSides(int choice) {
   }
}
