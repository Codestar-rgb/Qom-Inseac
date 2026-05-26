/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockParasiteSpreading;
import com.subspaceparasite.block.IStagedBlock;
import com.subspaceparasite.client.particle.ParticleSpawner;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPSoundTypes;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfestedRubble
extends BlockParasiteSpreading
implements IStagedBlock {
    public static final PropertyInteger STAGE = PropertyInteger.func_177719_a((String)"stage", (int)0, (int)5);

    public BlockInfestedRubble(Material material, String name, float hardness, boolean creative, boolean infested, float resistance) {
        super(material, name, hardness, creative, infested, resistance);
        this.setHarvestLevel("pickaxe", 1);
        this.func_149672_a(SPSoundTypes.SOIL);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(0)));
    }

    @Override
    public PropertyInteger getStageProperty() {
        return STAGE;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{STAGE});
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
        rand = new Random();
        if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != SPBlocks.ParasiteBush) {
            return;
        }
        int stage = this.func_176201_c(stateIn);
        if (stage == 2) {
            if (rand.nextDouble() <= (double)SPConfigSystems.rsBlockParticleS) {
                double d0 = (double)pos.func_177958_n() + rand.nextDouble();
                double d1 = (double)pos.func_177956_o() + 2.5;
                double d2 = (double)pos.func_177952_p() + rand.nextDouble();
                ParticleSpawner.spawnParticle(SPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
            }
        } else if (stage == 3) {
            double d2;
            double d1;
            double d0;
            if (rand.nextDouble() <= (double)SPConfigSystems.rsBlockParticleF) {
                d0 = (double)pos.func_177958_n() + rand.nextDouble();
                d1 = (double)pos.func_177956_o() + 1.5;
                d2 = (double)pos.func_177952_p() + rand.nextDouble();
                int g = 200;
                if (rand.nextInt(3) == 0) {
                    g += 50;
                }
                ParticleSpawner.spawnParticle(SPEnumParticle.FOG, d0, d1, d2, 0.0, 1.0E-4, 0.0, 0, g, 0);
            }
            if (rand.nextDouble() <= (double)SPConfigSystems.rsBlockParticleS) {
                d0 = (double)pos.func_177958_n() + rand.nextDouble();
                d1 = (double)pos.func_177956_o() + 2.5;
                d2 = (double)pos.func_177952_p() + rand.nextDouble();
                ParticleSpawner.spawnParticle(SPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
            }
        }
    }
}

