/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.material.EnumPushReaction
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.block.BlockParasiteSapling;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.entity.monster.inborn.EntityKol;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPSoundTypes;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.util.convert.BeckonBlockInfestation;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteBigBall;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyB1;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyB2;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyB3;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyB4;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBS1;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBS2;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteColonyBS3;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteMouth;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteNodeCore;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteSpine;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTallFlower;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTenFlower;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTree;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteTreeThin;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockColonyStructure
extends BlockBase {
    public static final PropertyInteger ACTIVE = PropertyInteger.func_177719_a((String)"active", (int)0, (int)3);

    public BlockColonyStructure(Material material, String name, float hardness, boolean creative, boolean tick, float resistance) {
        super(material, name, hardness, creative, tick, resistance);
        this.func_149672_a(SPSoundTypes.FLESH);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)ACTIVE, (Comparable)Integer.valueOf(0)));
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int i = 101;
        Random rand = new Random();
        worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
        if (worldIn.field_72995_K) {
            return false;
        }
        switch (i) {
            case -2: {
                this.func_180650_b(worldIn, pos, state, rand);
                break;
            }
            case 1: {
                EntityKol out = new EntityKol(worldIn);
                out.func_70080_a(pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p(), 0.0f, 0.0f);
                worldIn.func_72838_d((Entity)out);
                out.setOrigin(pos);
                break;
            }
            case 2: {
                WorldGenParasiteColonyB3 tree = new WorldGenParasiteColonyB3(false, 1);
                tree.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                break;
            }
            case 3: {
                worldIn.func_180501_a(pos, Blocks.field_150355_j.func_176223_P(), 1);
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                ParasiteEventEntity.spawnFromBlock(worldIn, new String[]{"subspaceparasite:worker;1"}, 5, pos.func_177984_a());
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                WorldGenParasiteTreeThin gen = new WorldGenParasiteTreeThin(false);
                if (gen.func_180709_b(worldIn, rand, pos.func_177981_b(3)) || worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(3), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 8: {
                WorldGenParasiteTree gen = new WorldGenParasiteTree(false);
                if (gen.func_180709_b(worldIn, rand, pos.func_177981_b(3)) || worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(3), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 9: {
                WorldGenParasiteTenFlower genF = new WorldGenParasiteTenFlower(false);
                if (genF.func_180709_b(worldIn, rand, pos.func_177981_b(3)) || worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(3), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 10: {
                WorldGenParasiteTallFlower genTF = new WorldGenParasiteTallFlower(false);
                if (genTF.func_180709_b(worldIn, rand, pos.func_177981_b(3)) || worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(3), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 11: {
                WorldGenParasiteSpine genS = new WorldGenParasiteSpine(false);
                if (genS.func_180709_b(worldIn, rand, pos.func_177981_b(3)) || worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(3), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 12: {
                WorldGenParasiteMouth genM = new WorldGenParasiteMouth(false);
                if (genM.func_180709_b(worldIn, rand, pos.func_177981_b(3)) || worldIn.func_180495_p(pos.func_177981_b(3)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(3), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 13: {
                WorldGenParasiteNodeCore genN = new WorldGenParasiteNodeCore(false, 1, 1);
                if (genN.func_180709_b(worldIn, rand, pos.func_177981_b(6)) || worldIn.func_180495_p(pos.func_177981_b(6)).func_177230_c() != Blocks.field_150350_a) break;
                worldIn.func_175656_a(pos.func_177981_b(6), SPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, (Comparable)((Object)BlockParasiteSapling.EnumType.TREE)));
                break;
            }
            case 14: {
                BeckonBlockInfestation.spawnGenRoofInfested(worldIn, pos.func_177979_c(1), rand);
                break;
            }
            case 15: {
                WorldGenParasiteBigBall ball = new WorldGenParasiteBigBall(false);
                ball.func_180709_b(worldIn, new Random(), pos.func_177984_a());
                break;
            }
            case 100: {
                int offset = 20;
                WorldGenParasiteColonyB1 bu1 = new WorldGenParasiteColonyB1(false, 1);
                bu1.func_180709_b(worldIn, new Random(), pos.func_177965_g(offset));
                WorldGenParasiteColonyB2 bu2 = new WorldGenParasiteColonyB2(false, 1);
                bu2.func_180709_b(worldIn, new Random(), pos.func_177964_d(offset));
                WorldGenParasiteColonyB3 bu3 = new WorldGenParasiteColonyB3(false, 1);
                bu3.func_180709_b(worldIn, new Random(), pos.func_177985_f(offset));
                WorldGenParasiteColonyB4 b4 = new WorldGenParasiteColonyB4(false, 1);
                b4.func_180709_b(worldIn, new Random(), pos.func_177970_e(offset));
                break;
            }
            case 101: {
                int n = 20;
            }
        }
        return false;
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3) || worldIn.field_72995_K) {
            return;
        }
        if (!SPConfigWorld.coloniesActivated) {
            return;
        }
        SPWorldData data = SPWorldData.get(worldIn);
        if (data.nearestColonyPosition(pos, false) == null) {
            return;
        }
        int meta = this.func_176201_c(state);
        block0 : switch (meta) {
            case 1: {
                switch (rand.nextInt(3)) {
                    case 1: {
                        WorldGenParasiteColonyB3 b3 = new WorldGenParasiteColonyB3(false, 2);
                        b3.func_180709_b(worldIn, new Random(), pos);
                        break block0;
                    }
                    case 2: {
                        WorldGenParasiteColonyB2 b2 = new WorldGenParasiteColonyB2(false, 2);
                        b2.func_180709_b(worldIn, new Random(), pos);
                        break block0;
                    }
                    case 3: {
                        WorldGenParasiteColonyB4 b4 = new WorldGenParasiteColonyB4(false, 2);
                        b4.func_180709_b(worldIn, new Random(), pos);
                        break block0;
                    }
                }
                WorldGenParasiteColonyB1 b1 = new WorldGenParasiteColonyB1(false, 2);
                b1.func_180709_b(worldIn, new Random(), pos);
                break;
            }
            case 2: {
                switch (rand.nextInt(3)) {
                    case 1: {
                        WorldGenParasiteColonyBS1 bs1 = new WorldGenParasiteColonyBS1(false, 2);
                        bs1.func_180709_b(worldIn, new Random(), pos);
                        break block0;
                    }
                    case 2: {
                        WorldGenParasiteColonyBS3 bs3 = new WorldGenParasiteColonyBS3(false, 2);
                        bs3.func_180709_b(worldIn, new Random(), pos);
                        break block0;
                    }
                }
                WorldGenParasiteColonyBS2 bs2 = new WorldGenParasiteColonyBS2(false, 2);
                bs2.func_180709_b(worldIn, new Random(), pos);
            }
        }
        worldIn.func_175656_a(pos, this.func_176203_a(3));
    }

    private boolean makePillar(World worldIn, BlockPos pos, int totalCheck, int blocks) {
        pos = pos.func_177984_a();
        int times = 0;
        Block look = worldIn.func_180495_p(pos).func_177230_c();
        while (blocks > 0) {
            boolean g = false;
            if (look == SPBlocks.ParasiteStain) {
                pos = pos.func_177984_a();
                look = worldIn.func_180495_p(pos).func_177230_c();
                if (!(g = ++times >= totalCheck)) continue;
                return true;
            }
            if (look == Blocks.field_150350_a || !(look instanceof BlockLeaves)) {
                // empty if block
            }
            g = ++times >= totalCheck;
            this.placePillarBlock(worldIn, pos, --blocks <= 0);
            pos = pos.func_177984_a();
            look = worldIn.func_180495_p(pos).func_177230_c();
            if (!g) continue;
            return true;
        }
        return false;
    }

    private void placePillarBlock(World worldIn, BlockPos pos, boolean flag) {
        AxisAlignedBB axisalignedbb2;
        List moblist2;
        int xx = pos.func_177958_n();
        int zz = pos.func_177952_p();
        int yy = pos.func_177956_o();
        int range = 10;
        for (int x = xx - range; x <= xx + range; ++x) {
            for (int z = zz - range; z <= zz + range; ++z) {
                BlockPos neww = new BlockPos(x, yy, z);
                float har = worldIn.func_180495_p(neww).func_185887_b(worldIn, neww);
                if (har > 7.0f || worldIn.func_180495_p(neww).func_177230_c() instanceof BlockBase) continue;
                worldIn.func_175656_a(neww, Blocks.field_150350_a.func_176223_P());
            }
        }
        worldIn.func_175656_a(pos, SPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, (Comparable)((Object)BlockParasiteStain.EnumType.FLESH)));
        if (flag && (moblist2 = worldIn.func_72872_a(EntityItem.class, axisalignedbb2 = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_72314_b(32.0, 16.0, 32.0))).size() > 7) {
            worldIn.func_180501_a(pos.func_177978_c(), SPBlocks.ParasiteCanisterActive.func_176223_P(), 3);
        }
    }

    public EnumPushReaction func_149656_h(IBlockState state) {
        return EnumPushReaction.BLOCK;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{ACTIVE});
    }

    public int func_176201_c(IBlockState state) {
        return (Integer)state.func_177229_b((IProperty)ACTIVE);
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a((IProperty)ACTIVE, (Comparable)Integer.valueOf(meta & 3));
    }
}

