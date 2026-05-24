/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.init.SPBiomes;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPReference;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.util.convert.BeckonBlockInfestation;
import com.subspaceparasite.util.handlers.BiomeUpdateQueue;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import com.subspaceparasite.world.biome.BiomeParasiteHarlequin;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BlockParasiteSpreading
extends BlockBase {
    public static int blockParasiteCount;
    public boolean isInfestedBlock;

    public BlockParasiteSpreading(Material material, String name, float hardness, boolean creative, boolean isInfested) {
        super(material, name, hardness, creative, true);
        this.isInfestedBlock = isInfested;
    }

    public BlockParasiteSpreading(Material material, String name, float hardness, boolean creative, boolean isInfested, float resistance) {
        this(material, name, hardness, creative, isInfested);
        this.func_149752_b(resistance);
    }

    public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        boolean hasSnowAbove;
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        IBlockState aboveState = worldIn.func_180495_p(pos.func_177984_a());
        Block aboveBlock = aboveState.func_177230_c();
        boolean bl = hasSnowAbove = aboveBlock == Blocks.field_150431_aC || aboveBlock == Blocks.field_150433_aE;
        if (this == SPBlocks.HarlequinnGrass) {
            if (hasSnowAbove) {
                worldIn.func_180501_a(pos, SPBlocks.LocsBlock.func_176223_P(), 3);
                return;
            }
        } else if (this == SPBlocks.HarleskinnBlock && aboveBlock == SPBlocks.LocsBlock) {
            worldIn.func_180501_a(pos, SPBlocks.HarlequinnGrass.func_176223_P(), 3);
            return;
        }
        if (!this.isInfestedBlock) {
            this.func_180650_b(worldIn, pos, state, rand);
        } else if (rand.nextDouble() < 0.5) {
            if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
                BiomeParasiteBase biomePara = (BiomeParasiteBase)worldIn.func_180494_b(pos);
                String[] blockList = biomePara.getDirt().split(":");
                Block one = Block.func_149684_b((String)(blockList[0] + ":" + blockList[1]));
                worldIn.func_175656_a(pos, one.func_176203_a(Integer.parseInt(blockList[2])));
            } else {
                int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
                if (heart > 0) {
                    BlockParasiteSpreading.SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
                }
            }
        } else if (rand.nextDouble() <= 0.05) {
            this.func_180650_b(worldIn, pos, state, rand);
        }
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.field_72995_K) {
            if (this.isInfestedBlock) {
                BlockParasiteSpreading.canInfestBlock(worldIn, pos, rand, this.func_176201_c(state), true);
            } else if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
                BlockParasiteSpreading.spreadBiomeBlockStain(worldIn, pos, rand);
            } else {
                int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
                if (heart > 0) {
                    BlockParasiteSpreading.SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
                }
            }
        }
    }

    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        boolean flag = super.removedByPlayer(state, world, pos, player, willHarvest);
        if (world.field_72995_K) {
            return flag;
        }
        if (SPConfigSystems.useEvolution && this.func_176201_c(state) > 0) {
            SPSaveData data = SPSaveData.get(world, 9);
            data.setTotalKills(world.field_73011_w.getDimension(), -SPConfigSystems.valueLossBlockStain, true, world, false, 19);
        }
        return flag;
    }

    public static void SpreadBiome(World worldIn, BlockPos pos, int age, int type) {
        if (!SPConfigWorld.nodesActivated || !SPConfigWorld.biomeRegster) {
            return;
        }
        SPWorldData data = SPWorldData.get(worldIn);
        int distance = data.getDistanceSpreadByAge(age, false);
        for (int x = pos.func_177958_n() - 4; x <= pos.func_177958_n() + 4; ++x) {
            for (int z = pos.func_177952_p() - 4; z <= pos.func_177952_p() + 4; ++z) {
                BiomeParasiteBase target;
                Biome current;
                BlockPos convert = new BlockPos(x, pos.func_177956_o(), z);
                if (data.isInRangeOfHeart(convert, distance) == -1 || ParasiteEventEntity.checkName(worldIn.func_180494_b(convert).getRegistryName().toString(), SPConfigWorld.biomeBlackList, SPConfigWorld.biomeBlackListInverted) || (current = worldIn.func_180494_b(convert)) instanceof BiomeParasiteBase || (target = SPReference.getBiomeFromInt(type)) != null && current == target) continue;
                BlockParasiteSpreading.positionToParasiteBiome(worldIn, convert, type);
                BiomeUpdateQueue.enqueue(convert.func_177958_n(), convert.func_177956_o(), convert.func_177952_p(), true, type, worldIn.field_73011_w.getDimension());
            }
        }
    }

    public static void spreadBiomeBlockStain(World worldIn, BlockPos pos, Random rand) {
        if (!(worldIn.func_180494_b(pos) instanceof BiomeParasiteBase)) {
            return;
        }
        BiomeParasiteBase biomePara = (BiomeParasiteBase)worldIn.func_180494_b(pos);
        BlockPos helper = pos;
        int covertedBlocks = 0;
        for (int dir = 0; dir <= 5; ++dir) {
            helper = BlockParasiteSpreading.directionToSpread(pos, dir);
            BlockParasiteSpreading.spreadToAir(worldIn, helper);
            blockParasiteCount += (covertedBlocks += biomePara.convertBlock(helper, worldIn, rand));
        }
        if (covertedBlocks != 0 && SPConfigSystems.useEvolution) {
            SPSaveData.get(worldIn, 76).setTotalKills(worldIn.field_73011_w.getDimension(), SPConfigSystems.valueBlock * covertedBlocks, true, worldIn, true, 20);
        }
    }

    public static void spreadBiomeBlockTrunk(World worldIn, BlockPos pos, Random rand) {
        if (worldIn.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            return;
        }
        BlockPos helper = pos;
        int posx = pos.func_177958_n();
        int posz = pos.func_177952_p();
        int posy = pos.func_177956_o();
        for (int x = -1; x <= 1; ++x) {
            for (int z = -1; z <= 1; ++z) {
                for (int y = -1; y <= 1; ++y) {
                    helper = new BlockPos(posx + x, posy + y, posz + z);
                    IBlockState lookingState = worldIn.func_180495_p(helper);
                    Block lookingBlock = lookingState.func_177230_c();
                    if (!lookingBlock.isWood((IBlockAccess)worldIn, helper) || lookingBlock == SPBlocks.ParasiteTrunk) continue;
                    worldIn.func_175656_a(helper, SPBlocks.ParasiteTrunk.func_176223_P());
                    BlockParasiteSpreading.spreadToAir(worldIn, helper);
                    if (!SPConfigSystems.useEvolution) continue;
                    SPSaveData.get(worldIn, 77).setTotalKills(worldIn.field_73011_w.getDimension(), SPConfigSystems.valueBlock, true, worldIn, true, 21);
                }
            }
        }
    }

    public static void positionToParasiteBiome(World worldIn, BlockPos pos, int type) {
        byte biomeID = (byte)Biome.func_185362_a((Biome)SPBiomes.biomeShrouded);
        GameRules rules = worldIn.func_82736_K();
        if (rules.func_82766_b("srpForceHarlequin")) {
            BiomeParasiteHarlequin forced;
            biomeID = (byte)Biome.func_185362_a((Biome)SPBiomes.biomeHarlequin);
            int inChunkX = pos.func_177958_n() & 0xF;
            int inChunkZ = pos.func_177952_p() & 0xF;
            Biome currentForced = worldIn.func_180494_b(pos);
            if (currentForced == (forced = SPBiomes.biomeHarlequin)) {
                return;
            }
            worldIn.func_175726_f((BlockPos)pos).func_76605_m()[inChunkZ << 4 | inChunkX] = biomeID;
            worldIn.func_175726_f(pos).func_76630_e();
            worldIn.func_72975_g(pos.func_177958_n(), pos.func_177952_p(), 0, pos.func_177956_o());
            return;
        }
        BiomeParasiteBase target = SPReference.getBiomeFromInt(type);
        if (target == null) {
            return;
        }
        Biome current = worldIn.func_180494_b(pos);
        if (current == target) {
            return;
        }
        biomeID = (byte)Biome.func_185362_a((Biome)target);
        int inChunkX = pos.func_177958_n() & 0xF;
        int inChunkZ = pos.func_177952_p() & 0xF;
        worldIn.func_175726_f((BlockPos)pos).func_76605_m()[inChunkZ << 4 | inChunkX] = biomeID;
        worldIn.func_175726_f(pos).func_76630_e();
        worldIn.func_72975_g(pos.func_177958_n(), pos.func_177952_p(), 0, pos.func_177956_o());
    }

    public static BlockPos directionToSpread(BlockPos atm, int choice) {
        switch (choice) {
            case 0: {
                atm = atm.func_177978_c();
                break;
            }
            case 1: {
                atm = atm.func_177974_f();
                break;
            }
            case 2: {
                atm = atm.func_177976_e();
                break;
            }
            case 3: {
                atm = atm.func_177968_d();
                break;
            }
            case 4: {
                atm = atm.func_177984_a();
                break;
            }
            default: {
                atm = atm.func_177977_b();
            }
        }
        return atm;
    }

    public static void spreadToAir(World worldIn, BlockPos atm) {
    }

    public static void canInfestBlock(World worldIn, BlockPos pos, Random rand, int stage, boolean fromVenkrol) {
        BeckonBlockInfestation.beckonInfestation(worldIn, pos, rand, stage, fromVenkrol);
    }
}

