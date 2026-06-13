/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockBiomeCore;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteGenAbstract;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNodeCore
extends WorldGenParasiteGenAbstract {
    private int core;
    private int type;

    public WorldGenParasiteNodeCore(boolean notify, int stage, int biomeType) {
        super(notify);
        this.core = stage;
        this.type = biomeType;
    }

    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        switch (this.type) {
            case 3: {
                this.placeHeartHarlequin(worldIn, rand, position);
                break;
            }
            default: {
                this.placeHeartShrouded(worldIn, rand, position);
            }
        }
        this.placeCore(worldIn, position, this.core);
        return true;
    }

    private boolean placeHeartShrouded(World worldIn, Random rand, BlockPos position) {
        switch (this.core) {
            case 1: {
                int z;
                int zs;
                int xs;
                int yyy;
                BlockPos helper = position;
                BlockPos helperTwo = position;
                BlockPos helperTwo2 = position;
                int down = 5;
                while (down >= 0 && helper.func_177977_b().func_177956_o() >= 1) {
                    helper = helper.func_177977_b();
                    this.placeTrunk(worldIn, helper);
                    --down;
                    for (yyy = 0; yyy <= 0; ++yyy) {
                        for (xs = -1; xs <= 1; ++xs) {
                            for (zs = -1; zs <= 1; ++zs) {
                                helperTwo = new BlockPos(helper.func_177958_n() + xs, helper.func_177956_o(), helper.func_177952_p() + zs);
                                this.placeTrunk(worldIn, helperTwo);
                                for (z = 0; z <= 3; ++z) {
                                    this.placeTrunk(worldIn, this.directionToGrow(helperTwo, z, false));
                                    if (xs != 0 || zs != 0) continue;
                                    helperTwo2 = helperTwo;
                                    for (int kkk = 0; kkk <= 2; ++kkk) {
                                        helperTwo = this.directionToGrow(helperTwo, z, false);
                                    }
                                    this.placeDirt(worldIn, helperTwo);
                                    helperTwo = helperTwo2;
                                }
                            }
                        }
                    }
                }
                down = 2;
                while (down >= 0 && helper.func_177977_b().func_177956_o() >= 1) {
                    helper = helper.func_177977_b();
                    this.placeTrunk(worldIn, helper);
                    --down;
                    for (yyy = 0; yyy <= 0; ++yyy) {
                        for (xs = -1; xs <= 1; ++xs) {
                            for (zs = -1; zs <= 1; ++zs) {
                                helperTwo = new BlockPos(helper.func_177958_n() + xs, helper.func_177956_o(), helper.func_177952_p() + zs);
                                this.placeTrunk(worldIn, helperTwo);
                                for (z = 0; z <= 3; ++z) {
                                    if (worldIn.func_180495_p(this.directionToGrow(helperTwo, z, false)).func_177230_c() == SRPBlocks.ParasiteRubbleDense) continue;
                                    this.placeDirt(worldIn, this.directionToGrow(helperTwo, z, false));
                                }
                            }
                        }
                    }
                }
                this.placeDirt(worldIn, position.func_177977_b());
                worldIn.func_175654_a(position.func_177977_b(), worldIn.func_180495_p(position.func_177977_b()).func_177230_c(), 60, 5);
                helper = position;
                for (int i = 0; i <= 3; ++i) {
                    int o;
                    helper = this.directionToGrow(position, i, false);
                    if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                        this.placeTrunk(worldIn, helper.func_177977_b());
                    }
                    this.placeTrunk(worldIn, helper);
                    BlockPos rootH = helper;
                    for (o = 0; o < 2; ++o) {
                        helper = o == 0 ? this.directionToGrow(rootH, (i + 1) % 4, false) : this.directionToGrow(rootH, (i + 3) % 4, false);
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
                    for (o = 0; o < 2; ++o) {
                        helper = o == 0 ? this.directionToGrow(rootH, (i + 1) % 4, false) : this.directionToGrow(rootH, (i + 3) % 4, false);
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
            }
            case 2: {
                int o;
                int i;
                BlockPos atm;
                position = position.func_177981_b(2);
                this.placeLiquid(worldIn, position);
                for (int i2 = 0; i2 < 3; ++i2) {
                    this.placePeri(worldIn, position);
                    this.placeLiquid(worldIn, position);
                    position = position.func_177984_a();
                }
                BlockPos root = atm = position;
                int dir = 0;
                for (i = 0; i <= 3; ++i) {
                    root = atm = this.getDirectionRoot(position.func_177977_b(), i, 2);
                    for (o = 0; o < 2; ++o) {
                        if (o == 0) {
                            if (rand.nextInt(2) != 0) continue;
                            dir = (i + 1) % 4;
                            atm = this.directionToGrow(root, dir, false);
                            this.placeTen(worldIn, atm);
                            atm = this.directionToGrow(atm, i, false);
                            this.placeTen(worldIn, atm);
                            while (!worldIn.func_180495_p(atm.func_177977_b()).func_185913_b() && atm.func_177977_b().func_177956_o() >= 1) {
                                atm = rand.nextInt(1) == 0 ? this.directionToGrow(atm, i * 10, true) : this.directionToGrow(atm, i, false);
                                atm = this.placeColumn(worldIn, atm, rand.nextInt(2) + 2, rand, 0.0);
                            }
                            this.placeDirt(worldIn, atm.func_177977_b());
                            continue;
                        }
                        if (rand.nextInt(2) != 0) continue;
                        dir = (i + 3) % 4;
                        atm = this.directionToGrow(root, dir, false);
                        this.placeTen(worldIn, atm);
                        atm = this.directionToGrow(atm, i, false);
                        this.placeTen(worldIn, atm);
                        while (!worldIn.func_180495_p(atm.func_177977_b()).func_185913_b() && atm.func_177977_b().func_177956_o() >= 1) {
                            atm = rand.nextInt(1) == 0 ? this.directionToGrow(atm, i * 10 + 1, true) : this.directionToGrow(atm, i, false);
                            this.placeTen(worldIn, atm);
                            atm = this.placeColumn(worldIn, atm, rand.nextInt(2) + 2, rand, 0.0);
                        }
                        this.placeDirt(worldIn, atm.func_177977_b());
                    }
                }
                this.placeLiquid(worldIn, position);
                for (i = 0; i < 5; ++i) {
                    this.placePeri(worldIn, position);
                    this.placeLiquid(worldIn, position);
                    position = position.func_177984_a();
                }
                root = atm = position;
                for (i = 0; i <= 3; ++i) {
                    root = atm = this.getDirectionRoot(position.func_177977_b(), i, 2);
                    for (o = 0; o < 2; ++o) {
                        if (o == 0) {
                            if (rand.nextInt(2) != 0) continue;
                            dir = (i + 1) % 4;
                            atm = this.directionToGrow(root, dir, false);
                            this.placeTen(worldIn, atm);
                            atm = this.directionToGrow(atm, i, false);
                            this.placeTen(worldIn, atm);
                            while (!worldIn.func_180495_p(atm.func_177977_b()).func_185913_b() && atm.func_177977_b().func_177956_o() >= 1) {
                                atm = rand.nextInt(1) == 0 ? this.directionToGrow(atm, i * 10, true) : this.directionToGrow(atm, i, false);
                                this.placeTen(worldIn, atm);
                                atm = this.placeColumn(worldIn, atm, rand.nextInt(2) + 1, rand, 0.0);
                            }
                            this.placeDirt(worldIn, atm.func_177977_b());
                            continue;
                        }
                        if (rand.nextInt(2) != 0) continue;
                        dir = (i + 3) % 4;
                        atm = this.directionToGrow(root, dir, false);
                        this.placeTen(worldIn, atm);
                        atm = this.directionToGrow(atm, i, false);
                        this.placeTen(worldIn, atm);
                        while (!worldIn.func_180495_p(atm.func_177977_b()).func_185913_b() && atm.func_177977_b().func_177956_o() >= 1) {
                            atm = rand.nextInt(1) == 0 ? this.directionToGrow(atm, i * 10 + 1, true) : this.directionToGrow(atm, i, false);
                            this.placeTen(worldIn, atm);
                            atm = this.placeColumn(worldIn, atm, rand.nextInt(2) + 1, rand, 0.0);
                        }
                        this.placeDirt(worldIn, atm.func_177977_b());
                    }
                }
                this.placeLiquid(worldIn, position);
                for (i = 0; i < 5; ++i) {
                    this.placePeri(worldIn, position);
                    this.placeLiquid(worldIn, position);
                    position = position.func_177984_a();
                }
                this.placeTrunk(worldIn, position);
                break;
            }
        }
        return true;
    }

    private boolean placeHeartBoils(World worldIn, Random rand, BlockPos position) {
        switch (this.core) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
        }
        return true;
    }

    private boolean placeHeartHarlequin(World worldIn, Random rand, BlockPos position) {
        switch (this.core) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
        }
        return true;
    }

    private boolean placeHeartDemen(World worldIn, Random rand, BlockPos position) {
        switch (this.core) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
        }
        return true;
    }

    private void placeTen(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER)));
    }

    private void placeTrunk(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.BIOME)));
    }

    private void placeCore(World worldIn, BlockPos pos, int stage) {
        this.func_175903_a(worldIn, pos, SRPBlocks.BiomeHeart.func_176223_P().func_177226_a((IProperty)BlockBiomeCore.ACTIVE, (Comparable)Integer.valueOf(stage)));
    }

    private void placeDirt(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P());
    }

    private void placeLiquid(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.DeadBlood.func_176223_P());
    }

    private BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
        switch (direction) {
            case 0: {
                return center.func_177964_d(times);
            }
            case 1: {
                return center.func_177965_g(times);
            }
            case 2: {
                return center.func_177970_e(times);
            }
        }
        return center.func_177985_f(times);
    }

    private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
        int current;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        while (current < atm + in) {
            this.placeTen(worldIn, newPos);
            newPos = newPos.func_177977_b();
            ++current;
            ++times;
        }
        this.placeTen(worldIn, newPos);
        return newPos;
    }

    private void placePeri(World worldIn, BlockPos position) {
        BlockPos helper = position;
        for (int i = 0; i <= 3; ++i) {
            helper = this.directionToGrow(position, i, false);
            if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                this.placeTrunk(worldIn, helper.func_177977_b());
            }
            this.placeTrunk(worldIn, helper);
            BlockPos rootH = helper;
            for (int o = 0; o < 2; ++o) {
                helper = o == 0 ? this.directionToGrow(rootH, (i + 1) % 4, false) : this.directionToGrow(rootH, (i + 3) % 4, false);
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
                case 0: {
                    atm = atm.func_177978_c();
                    atm = atm.func_177974_f();
                    break;
                }
                case 1: {
                    atm = atm.func_177978_c();
                    atm = atm.func_177976_e();
                    break;
                }
                case 10: {
                    atm = atm.func_177974_f();
                    atm = atm.func_177968_d();
                    break;
                }
                case 11: {
                    atm = atm.func_177974_f();
                    atm = atm.func_177978_c();
                    break;
                }
                case 20: {
                    atm = atm.func_177968_d();
                    atm = atm.func_177976_e();
                    break;
                }
                case 21: {
                    atm = atm.func_177968_d();
                    atm = atm.func_177974_f();
                    break;
                }
                case 30: {
                    atm = atm.func_177976_e();
                    atm = atm.func_177978_c();
                    break;
                }
                default: {
                    atm = atm.func_177976_e();
                    atm = atm.func_177968_d();
                }
            }
            return atm;
        }
        switch (choice) {
            case 0: {
                atm = atm.func_177978_c();
                break;
            }
            case 1: {
                atm = atm.func_177974_f();
                break;
            }
            case 3: {
                atm = atm.func_177976_e();
                break;
            }
            default: {
                atm = atm.func_177968_d();
            }
        }
        return atm;
    }

    private void positionSides(int choice) {
    }
}

