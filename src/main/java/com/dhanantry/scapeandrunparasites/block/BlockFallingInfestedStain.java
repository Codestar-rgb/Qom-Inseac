/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockFallingBase;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFallingInfestedStain
extends BlockFallingBase {
    public static final PropertyInteger STAGE = PropertyInteger.func_177719_a((String)"stage", (int)0, (int)5);
    public static final PropertyBool SNOWY = PropertyBool.func_177716_a((String)"snowy");

    public BlockFallingInfestedStain(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material, name, hardness, creative, tickRandom);
        this.setHarvestLevel("shovel", 0);
        this.func_149672_a(new SoundType(1.0f, 0.5f, SRPSounds.BLOCKINFEST_BREAK, SRPSounds.BLOCKINFEST_STEP, SRPSounds.BLOCKINFEST_PLACE, SRPSounds.BLOCKINFEST_HIT, SoundEvents.field_187876_fn));
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(0)).func_177226_a((IProperty)SNOWY, (Comparable)Boolean.FALSE));
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        boolean flag = super.removedByPlayer(state, world, pos, player, willHarvest);
        if (world.field_72995_K) {
            return flag;
        }
        if (SRPConfigSystems.useEvolution && this.func_176201_c(state) > 0) {
            SRPSaveData data = SRPSaveData.get(world, 9);
            data.setTotalKills(world.field_73011_w.getDimension(), -SRPConfigSystems.valueLossBlockStain, true, world, false, 33);
        }
        return flag;
    }

    public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        if (rand.nextDouble() < 0.5) {
            if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
                worldIn.func_175656_a(pos, SRPBlocks.ParasiteStain.func_176223_P());
            } else {
                int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
                if (heart > 0) {
                    BlockParasiteSpreading.SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
                }
            }
        } else if (rand.nextDouble() <= 0.05 && !worldIn.field_72995_K) {
            BlockParasiteSpreading.canInfestBlock(worldIn, pos, rand, this.func_176201_c(state), false);
        }
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        super.func_180650_b(worldIn, pos, state, rand);
    }

    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
        super.func_176199_a(worldIn, pos, entityIn);
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{STAGE, SNOWY});
    }

    public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        IBlockState above = worldIn.func_180495_p(pos.func_177984_a());
        boolean snowy = above.func_177230_c() == Blocks.field_150433_aE || above.func_177230_c() == Blocks.field_150431_aC;
        return state.func_177226_a((IProperty)SNOWY, (Comparable)Boolean.valueOf(snowy));
    }

    public int func_176201_c(IBlockState state) {
        return (Integer)state.func_177229_b((IProperty)STAGE);
    }

    public IBlockState func_176203_a(int meta) {
        if (meta >= 5) {
            meta = 5;
        }
        return this.func_176223_P().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(meta & 7));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != SRPBlocks.ParasiteBush) {
            return;
        }
        int stage = this.func_176201_c(stateIn);
        if (stage == 2) {
            if (rand.nextDouble() <= (double)SRPConfigSystems.rsBlockParticleS) {
                double d0 = (double)pos.func_177958_n() + rand.nextDouble();
                double d1 = (double)pos.func_177956_o() + 2.5;
                double d2 = (double)pos.func_177952_p() + rand.nextDouble();
                ParticleSpawner.spawnParticle(SRPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
            }
        } else if (stage == 3) {
            double d2;
            double d1;
            double d0;
            if (rand.nextDouble() <= (double)SRPConfigSystems.rsBlockParticleF) {
                d0 = (double)pos.func_177958_n() + rand.nextDouble();
                d1 = (double)pos.func_177956_o() + 1.5;
                d2 = (double)pos.func_177952_p() + rand.nextDouble();
                int g = 200;
                if (rand.nextInt(3) == 0) {
                    g += 50;
                }
                ParticleSpawner.spawnParticle(SRPEnumParticle.FOG, d0, d1, d2, 0.0, 1.0E-4, 0.0, 0, g, 0);
            }
            if (rand.nextDouble() <= (double)SRPConfigSystems.rsBlockParticleS) {
                d0 = (double)pos.func_177958_n() + rand.nextDouble();
                d1 = (double)pos.func_177956_o() + 2.5;
                d2 = (double)pos.func_177952_p() + rand.nextDouble();
                ParticleSpawner.spawnParticle(SRPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
            }
        }
    }
}

