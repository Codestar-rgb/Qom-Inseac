/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  org.apache.logging.log4j.Level
 */
package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteLoot;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityParasiteLoot;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteGenAbstract;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public abstract class WorldGenParasiteColonyBase
extends WorldGenParasiteGenAbstract {
    protected int type;
    protected IBlockState floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.DIRT));
    protected IBlockState tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FEELER));
    protected IBlockState wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, (Comparable)((Object)BlockParasiteRubbleDense.EnumType.WALL));
    protected IBlockState floorColony = SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, (Comparable)((Object)BlockParasiteRubble.EnumType.FLESH));

    public WorldGenParasiteColonyBase(boolean notify, int stage) {
        super(notify);
        this.type = stage;
    }

    protected void placeBlock(World worldIn, BlockPos pos, IBlockState state) {
        this.func_175903_a(worldIn, pos, state);
    }

    protected void placeVine(World worldIn, BlockPos pos) {
        this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, (Comparable)((Object)BlockParasiteBush.EnumType.BINE)));
    }

    protected void placeReplacement(World worldIn, BlockPos pos, IBlockState in) {
        this.func_175903_a(worldIn, pos, in);
    }

    protected BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
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

    protected BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance, IBlockState state) {
        int current;
        int atm = current = pos.func_177956_o();
        int times = 0;
        BlockPos newPos = pos;
        while (current < atm + in) {
            this.placeBlock(worldIn, newPos, state);
            newPos = extraChance == 1.0 ? newPos.func_177984_a() : newPos.func_177977_b();
            ++current;
            ++times;
        }
        this.placeBlock(worldIn, newPos, state);
        return newPos;
    }

    protected BlockPos directionToGrow(BlockPos atm, int choice, boolean sideCurse) {
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

    protected void addVines(World worldIn, BlockPos position, Random rand, int longer) {
        if (worldIn.func_180495_p(position).func_177230_c() != Blocks.field_150350_a) {
            return;
        }
        this.placeVine(worldIn, position);
        int chance = longer;
        while (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a && worldIn.func_180495_p(position.func_177979_c(2)).func_177230_c() == Blocks.field_150350_a) {
            if (rand.nextInt(chance) == 0) {
                return;
            }
            position = position.func_177977_b();
            this.placeVine(worldIn, position);
            --chance;
        }
    }

    protected void replaceLayer(World worldIn, BlockPos position, int range, IBlockState toReplace, IBlockState in) {
        int xx = position.func_177958_n();
        int zz = position.func_177952_p();
        int yy = position.func_177956_o();
        for (int x = xx - range; x <= xx + range; ++x) {
            for (int z = zz - range; z <= zz + range; ++z) {
                BlockPos neww = new BlockPos(x, yy, z);
                if (worldIn.func_180495_p(neww) != toReplace) continue;
                this.placeReplacement(worldIn, neww, in);
            }
        }
    }

    protected void addFloor(World worldIn, BlockPos position, int range, boolean fill) {
        BlockPos atm;
        if (fill) {
            this.placeReplacement(worldIn, position, this.floorColony);
        }
        int offsetN = 2;
        BlockPos atm2 = position.func_177978_c();
        while (!(worldIn.func_180495_p(atm2).func_177230_c() instanceof BlockBase)) {
            this.placeReplacement(worldIn, atm2, this.floorColony);
            this.genFloorFloor(worldIn, atm2.func_177977_b(), 15, fill);
            this.placeReplacement(worldIn, atm2.func_177970_e(offsetN), this.floorColony);
            this.genFloorFloor(worldIn, atm2.func_177970_e(offsetN).func_177977_b(), 15, fill);
            atm2 = atm2.func_177978_c();
            offsetN += 2;
        }
        BlockPos here = position.func_177965_g(1);
        while (!(worldIn.func_180495_p(here).func_177230_c() instanceof BlockBase)) {
            int offsetN2 = 2;
            atm = here.func_177978_c();
            while (!(worldIn.func_180495_p(atm).func_177230_c() instanceof BlockBase)) {
                this.placeReplacement(worldIn, atm, this.floorColony);
                this.genFloorFloor(worldIn, atm.func_177977_b(), 15, fill);
                this.placeReplacement(worldIn, atm.func_177970_e(offsetN2), this.floorColony);
                this.genFloorFloor(worldIn, atm.func_177970_e(offsetN2).func_177977_b(), 15, fill);
                atm = atm.func_177978_c();
                offsetN2 += 2;
            }
            this.placeReplacement(worldIn, here, this.floorColony);
            this.genFloorFloor(worldIn, here.func_177977_b(), 15, fill);
            here = here.func_177974_f();
        }
        here = position.func_177985_f(1);
        while (!(worldIn.func_180495_p(here).func_177230_c() instanceof BlockBase)) {
            int offsetN3 = 2;
            atm = here.func_177978_c();
            while (!(worldIn.func_180495_p(atm).func_177230_c() instanceof BlockBase)) {
                this.placeReplacement(worldIn, atm, this.floorColony);
                this.genFloorFloor(worldIn, atm.func_177977_b(), 15, fill);
                this.placeReplacement(worldIn, atm.func_177970_e(offsetN3), this.floorColony);
                this.genFloorFloor(worldIn, atm.func_177970_e(offsetN3).func_177977_b(), 15, fill);
                atm = atm.func_177978_c();
                offsetN3 += 2;
            }
            this.placeReplacement(worldIn, here, this.floorColony);
            this.genFloorFloor(worldIn, here.func_177977_b(), 15, fill);
            here = here.func_177976_e();
        }
        if (!fill) {
            this.addFloorSpace(worldIn, position);
        }
    }

    protected void genFloorFloor(World worldIn, BlockPos position, int range, boolean fill) {
        if (fill) {
            BlockPos filler = position;
            while ((worldIn.func_180495_p(filler).func_177230_c() == Blocks.field_150350_a || worldIn.func_180495_p(filler).func_177230_c() == SRPBlocks.ParasiteBush || worldIn.func_180495_p(filler).func_177230_c() instanceof BlockLeaves || worldIn.func_180495_p(filler).func_177230_c() instanceof BlockBush) && range > 0) {
                this.placeReplacement(worldIn, filler, this.floor);
                filler = filler.func_177977_b();
                --range;
            }
        }
    }

    protected void addFloorSpace(World worldIn, BlockPos position) {
        int xx = position.func_177958_n();
        int zz = position.func_177952_p();
        int yy = position.func_177956_o();
        int range = 1;
        for (int x = xx - range; x <= xx + range; ++x) {
            for (int z = zz - range; z <= zz + range; ++z) {
                BlockPos neww = new BlockPos(x, yy, z);
                if (worldIn.func_180495_p(neww) != this.floorColony) continue;
                this.placeReplacement(worldIn, neww, Blocks.field_150350_a.func_176223_P());
            }
        }
    }

    protected void addMobSpawner(World worldIn, BlockPos position, int type, double chance) {
    }

    protected void generateSphere(World worldIn, BlockPos posss, int innerHeight, int outerHeight, Random rand, boolean invertedTip, int valueStarting, boolean random, int heightBelow, int heightAbove, int tip, IBlockState state1, IBlockState state2, IBlockState state3, int incomplete) {
        int heig;
        int xx = valueStarting;
        int zz = valueStarting;
        int test = innerHeight;
        int ticc = 2;
        while (test > 0) {
            --test;
            heig = heightBelow;
            while (heig > 0) {
                --heig;
                this.generateCircle(state1, state2, worldIn, worldIn.field_73012_v, posss, xx, zz, 1, incomplete, 6);
                this.generateCircle(state3, state3, worldIn, worldIn.field_73012_v, posss, xx - ticc, zz - ticc, 1, 50000, 6);
                posss = posss.func_177984_a();
            }
            xx = rand.nextBoolean() && random ? (xx += 2) : ++xx;
            if (rand.nextBoolean() && random) {
                zz += 2;
                continue;
            }
            ++zz;
        }
        test = outerHeight;
        while (test > 0) {
            --test;
            this.generateCircle(state1, state2, worldIn, worldIn.field_73012_v, posss, xx, zz, 1, incomplete, 0);
            this.generateCircle(state3, state3, worldIn, worldIn.field_73012_v, posss, xx - 2, zz - 2, 1, 50000, 0);
            posss = posss.func_177984_a();
        }
        test = valueStarting + tip;
        if (invertedTip) {
            tip = 0;
            heightAbove = (int)((double)heightAbove * 0.5);
        }
        while (test > 0) {
            --test;
            heig = heightAbove;
            while (heig > 0) {
                --heig;
                this.generateCircle(state1, state2, worldIn, worldIn.field_73012_v, posss, xx, zz, 1, incomplete, invertedTip ? 9 : 0);
                this.generateCircle(state3, state3, worldIn, worldIn.field_73012_v, posss, xx - ticc, zz - ticc, 1, 50000, invertedTip ? 9 : 0);
                posss = posss.func_177984_a();
            }
            if (invertedTip) {
                xx = rand.nextBoolean() && random ? (xx += 2) : ++xx;
                if (rand.nextBoolean() && random) {
                    zz += 2;
                    continue;
                }
                ++zz;
                continue;
            }
            xx = rand.nextBoolean() && random ? (xx -= 2) : --xx;
            if (rand.nextBoolean() && random) {
                zz -= 2;
                continue;
            }
            --zz;
        }
    }

    protected boolean generateCircle(IBlockState state, IBlockState state2, World world, Random rand, BlockPos pos, int radiusX, int radiusZ, int height, int incomplete, int veins) {
        if (pos.func_177956_o() <= 2 || pos.func_177956_o() >= 240) {
            return false;
        }
        for (int y = 0; y < height; ++y) {
            BlockPos layerPos = pos.func_177981_b(y);
            for (int x = -radiusX; x <= radiusX; ++x) {
                for (int z = -radiusZ; z <= radiusZ; ++z) {
                    boolean flagAir;
                    double normalizedX = (double)x / (double)radiusX;
                    double normalizedZ = (double)z / (double)radiusZ;
                    if (!(normalizedX * normalizedX + normalizedZ * normalizedZ <= 1.0)) continue;
                    BlockPos newBlockPos = layerPos.func_177982_a(x, 0, z);
                    boolean bl = flagAir = state.func_177230_c() == Blocks.field_150350_a && state2.func_177230_c() == Blocks.field_150350_a;
                    if (world.func_180495_p(newBlockPos).func_177230_c() != Blocks.field_150350_a && !(world.func_180495_p(newBlockPos).func_177230_c() instanceof BlockBase) && !flagAir || rand.nextInt(incomplete) == 0) continue;
                    if ((x == radiusX || z == radiusZ || x == -radiusX || z == -radiusZ || x + 1 == radiusX || z + 1 == radiusZ || x - 1 == -radiusX || z - 1 == -radiusZ) && rand.nextInt(60) == 0) {
                        if (rand.nextInt(4) == 0) {
                            if (flagAir) continue;
                            world.func_175656_a(newBlockPos, SRPBlocks.DeadBlood.func_176223_P());
                            if (veins <= 0) continue;
                            this.addVines(world, newBlockPos.func_177977_b(), rand, veins);
                            continue;
                        }
                        if (flagAir) continue;
                        if (rand.nextInt(10) == 0) {
                            this.placeLoot(world, newBlockPos, SRPConfigWorld.blockLootRare, SRPBlocks.ParasiteLoot.func_176223_P().func_177226_a(BlockParasiteLoot.VARIANT, (Comparable)((Object)BlockParasiteLoot.EnumType.RARE)));
                        } else if (rand.nextInt(4) == 0) {
                            this.placeLoot(world, newBlockPos, SRPConfigWorld.blockLootUncommon, SRPBlocks.ParasiteLoot.func_176223_P().func_177226_a(BlockParasiteLoot.VARIANT, (Comparable)((Object)BlockParasiteLoot.EnumType.UNCOMMON)));
                        } else {
                            this.placeLoot(world, newBlockPos, SRPConfigWorld.blockLootCommon, SRPBlocks.ParasiteLoot.func_176223_P().func_177226_a(BlockParasiteLoot.VARIANT, (Comparable)((Object)BlockParasiteLoot.EnumType.COMMON)));
                        }
                        if (veins <= 0) continue;
                        this.addVines(world, newBlockPos.func_177977_b(), rand, veins);
                        continue;
                    }
                    if (rand.nextBoolean()) {
                        world.func_175656_a(newBlockPos, state);
                        if (veins <= 0 || flagAir) continue;
                        this.addVines(world, newBlockPos.func_177977_b(), rand, veins);
                        continue;
                    }
                    world.func_175656_a(newBlockPos, state2);
                    if (veins <= 0 || flagAir) continue;
                    this.addVines(world, newBlockPos.func_177977_b(), rand, veins);
                }
            }
        }
        return true;
    }

    protected void placeLoot(World world, BlockPos pos, String[] list, IBlockState state) {
        world.func_175656_a(pos, state);
        TileEntity tileentity = world.func_175625_s(pos);
        if (tileentity instanceof TileEntityParasiteLoot) {
            TileEntityParasiteLoot cyst = (TileEntityParasiteLoot)tileentity;
            for (int i = 0; i < cyst.func_70302_i_(); ++i) {
                if (world.field_73012_v.nextInt(2) == 0) continue;
                cyst.func_70299_a(i, new ItemStack(this.loot(world.field_73012_v, list)));
            }
        }
    }

    private Item loot(Random rand, String[] drop) {
        try {
            Item item;
            if (drop.length != 0 && (item = Item.func_111206_d((String)drop[rand.nextInt(drop.length)])) != null) {
                return item;
            }
        }
        catch (Exception e) {
            SRPMain.logger.log(Level.ERROR, "Problem with loot event", (Throwable)e);
        }
        return null;
    }

    protected boolean generateDNAHelix(IBlockState state, World world, Random rand, BlockPos pos, double radius, int numTurns, double pitch) {
        if (pos.func_177956_o() <= 2 || pos.func_177956_o() >= 240) {
            return false;
        }
        double tStep = 0.1;
        for (double t = 0.0; t <= (double)(numTurns * 2) * Math.PI; t += tStep) {
            double yOffset = pitch / (Math.PI * 2) * t;
            int y = pos.func_177956_o() + (int)Math.round(yOffset);
            int x1 = pos.func_177958_n() + (int)Math.round(radius * Math.cos(t));
            int z1 = pos.func_177952_p() + (int)Math.round(radius * Math.sin(t));
            int x2 = pos.func_177958_n() + (int)Math.round(radius * Math.cos(t + Math.PI));
            int z2 = pos.func_177952_p() + (int)Math.round(radius * Math.sin(t + Math.PI));
            BlockPos pos1 = new BlockPos(x1, y, z1);
            BlockPos pos2 = new BlockPos(x2, y, z2);
            world.func_175656_a(pos1, state);
            world.func_175656_a(pos2, state);
            if ((int)Math.round(t / Math.PI) % 2 != 0) continue;
            int midX = (x1 + x2) / 2;
            int midZ = (z1 + z2) / 2;
            BlockPos blockPos = new BlockPos(midX, y, midZ);
        }
        return true;
    }

    public BlockPos getCirclePoint(BlockPos center, int radius, double theta) {
        int x = center.func_177958_n() + (int)Math.round(Math.cos(theta) * (double)radius);
        int z = center.func_177952_p() + (int)Math.round(Math.sin(theta) * (double)radius);
        return new BlockPos(x, center.func_177956_o(), z);
    }

    public List<BlockPos> getCirclePoints(BlockPos center, int radius, int steps) {
        ArrayList<BlockPos> points = new ArrayList<BlockPos>();
        double cx = center.func_177958_n();
        double cz = center.func_177952_p();
        double cy = center.func_177956_o();
        for (int i = 0; i < steps; ++i) {
            double theta = Math.PI * 2 * (double)i / (double)steps;
            int x = center.func_177958_n() + (int)Math.round(Math.cos(theta) * (double)radius);
            int z = center.func_177952_p() + (int)Math.round(Math.sin(theta) * (double)radius);
            points.add(new BlockPos(x, (int)cy, z));
        }
        return points;
    }

    public void generatePillar(World world, BlockPos basePos, int height, IBlockState blockState, IBlockState blockState2) {
        for (int dy = 0; dy < height; ++dy) {
            BlockPos pos = basePos.func_177981_b(dy);
            if (world.field_73012_v.nextBoolean()) {
                world.func_175656_a(pos, blockState);
                continue;
            }
            world.func_175656_a(pos, blockState2);
        }
    }

    public void replaceCircleGround(World world, BlockPos center, int radius, IBlockState targetBlock) {
        int cx = center.func_177958_n();
        int cz = center.func_177952_p();
        int cy = center.func_177956_o();
        if (cy <= 2 || cy >= 240) {
            return;
        }
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dz = -radius; dz <= radius; ++dz) {
                BlockPos pos;
                IBlockState current;
                if (dx * dx + dz * dz > radius * radius || (current = world.func_180495_p(pos = new BlockPos(cx + dx, cy, cz + dz))).func_177230_c() instanceof BlockBase || current.func_177230_c() == Blocks.field_150350_a) continue;
                world.func_175656_a(pos, targetBlock);
            }
        }
    }

    public void generateVerticalCircle(World world, BlockPos center, int radius, boolean useXZPlane) {
        double step = Math.PI / (double)(radius * 4);
        for (double theta = 0.0; theta < Math.PI * 2; theta += step) {
            int dy = (int)Math.round((double)radius * Math.sin(theta));
            int dPrimary = (int)Math.round((double)radius * Math.cos(theta));
            BlockPos target = useXZPlane ? center.func_177982_a(dPrimary, dy, 0) : center.func_177982_a(0, dy, dPrimary);
            world.func_175656_a(target, Blocks.field_150348_b.func_176223_P());
        }
    }

    public void generateFilledVerticalDisk(World world, BlockPos center, int radius, boolean useXZPlane) {
        int rSq = radius * radius;
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= radius; ++dy) {
                BlockPos target;
                if (dx * dx + dy * dy > rSq || world.func_180495_p(target = useXZPlane ? center.func_177982_a(dx, dy, 0) : center.func_177982_a(0, dy, dx)).func_177230_c() == SRPBlocks.DeadBlood || world.func_180495_p(target).func_177230_c() == SRPBlocks.ParasiteBush) continue;
                world.func_175656_a(target, Blocks.field_150350_a.func_176223_P());
            }
        }
    }

    public void addEntrance(World worldIn, Random rand, BlockPos position, int entrance) {
        int direction = rand.nextInt(4);
        int offset = 3;
        while (entrance > 0) {
            if (offset > 0) {
                position = this.directionToGrow(position, direction, false);
                --offset;
                continue;
            }
            --entrance;
            position = this.directionToGrow(position, direction, false);
            this.generateFilledVerticalDisk(worldIn, position.func_177981_b(1), 3, direction != 1 && direction != 3);
        }
    }
}

