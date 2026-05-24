/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.EnchantmentProtection
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.world;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.util.config.SPConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPExplosion
extends Explosion {
    private boolean causesFire;
    private boolean damagesTerrain;
    private Random random = new Random();
    private World world;
    private double x;
    private double y;
    private double z;
    private Entity exploder;
    private float size;
    private List<BlockPos> affectedBlockPositions = Lists.newArrayList();
    private Vec3d position;
    EntityParasiteBase shooterTwo;

    @SideOnly(value=Side.CLIENT)
    public SPExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions) {
        this(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
    }

    @SideOnly(value=Side.CLIENT)
    public SPExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean causesFire, boolean damagesTerrain, List<BlockPos> affectedPositions) {
        this(worldIn, entityIn, x, y, z, size, causesFire, damagesTerrain);
        this.affectedBlockPositions.addAll(affectedPositions);
    }

    public SPExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean damagesTerrain) {
        super(worldIn, entityIn, x, y, z, size, flaming, true);
        this.world = worldIn;
        this.exploder = entityIn;
        this.size = size;
        this.x = x;
        this.y = y;
        this.z = z;
        this.causesFire = flaming;
        this.damagesTerrain = damagesTerrain;
        this.position = new Vec3d(this.x, this.y, this.z);
        if (entityIn instanceof EntityParasiteBase) {
            this.shooterTwo = (EntityParasiteBase)entityIn;
        }
    }

    public void func_77278_a() {
        if (this.world.field_72995_K) {
            return;
        }
        HashSet set = Sets.newHashSet();
        int i = 16;
        for (int j = 0; j < i; ++j) {
            for (int k = 0; k < i; ++k) {
                for (int l = 0; l < i; ++l) {
                    if (j != 0 && j != 15 && k != 0 && k != 15 && l != 0 && l != 15) continue;
                    double d0 = (float)j / 15.0f * 2.0f - 1.0f;
                    double d1 = (float)k / 15.0f * 2.0f - 1.0f;
                    double d2 = (float)l / 15.0f * 2.0f - 1.0f;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    double d4 = this.x;
                    double d6 = this.y;
                    double d8 = this.z;
                    float f1 = 0.3f;
                    for (float f = this.size * (0.7f + this.world.field_73012_v.nextFloat() * 0.6f); f > 0.0f; f -= 0.22500001f) {
                        BlockPos blockpos = new BlockPos(d4, d6, d8);
                        IBlockState iblockstate = this.world.func_180495_p(blockpos);
                        if (iblockstate.func_185904_a() != Material.field_151579_a) {
                            float f2 = this.exploder != null ? this.exploder.func_180428_a((Explosion)this, this.world, blockpos, iblockstate) : iblockstate.func_177230_c().getExplosionResistance(this.world, blockpos, null, (Explosion)this);
                            f -= (f2 + 0.3f) * 0.3f;
                        }
                        if (f > 0.0f && (this.exploder == null || this.exploder.func_174816_a((Explosion)this, this.world, blockpos, iblockstate, f))) {
                            set.add(blockpos);
                        }
                        d4 += d0 * (double)0.3f;
                        d6 += d1 * (double)0.3f;
                        d8 += d2 * (double)0.3f;
                    }
                }
            }
        }
        this.affectedBlockPositions.addAll(set);
        float f3 = this.size * 2.0f;
        double df1 = (double)f3 - 1.0;
        double df2 = (double)f3 + 1.0;
        int k1 = MathHelper.func_76128_c((double)(this.x - df1));
        int l1 = MathHelper.func_76128_c((double)(this.x + df2));
        int i2 = MathHelper.func_76128_c((double)(this.y - df1));
        int i1 = MathHelper.func_76128_c((double)(this.y + df2));
        int j2 = MathHelper.func_76128_c((double)(this.z - df1));
        int j1 = MathHelper.func_76128_c((double)(this.z + df2));
        List list = this.world.func_72839_b(this.exploder, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        ForgeEventFactory.onExplosionDetonate((World)this.world, (Explosion)this, (List)list, (double)f3);
        Vec3d vec3d = new Vec3d(this.x, this.y, this.z);
        for (Entity entity : list) {
            EntityPlayer entityplayer;
            double d9;
            double d7;
            double d5;
            double d13;
            double d12;
            if (entity instanceof EntityParasiteBase && SPConfig.explotionDamPara || entity.func_180427_aV() || !((d12 = entity.func_70011_f(this.x, this.y, this.z) / (double)f3) <= 1.0) || (d13 = (double)MathHelper.func_76133_a((double)((d5 = entity.field_70165_t - this.x) * d5 + (d7 = entity.field_70163_u + (double)entity.func_70047_e() - this.y) * d7 + (d9 = entity.field_70161_v - this.z) * d9))) == 0.0) continue;
            d5 /= d13;
            d7 /= d13;
            d9 /= d13;
            double d14 = this.world.func_72842_a(vec3d, entity.func_174813_aQ());
            double d10 = (1.0 - d12) * d14;
            entity.func_70097_a(DamageSource.func_94539_a((Explosion)this), (float)((int)((d10 * d10 + d10) / 2.0 * 7.0 * (double)f3 + 1.0)));
            double d11 = d10;
            if (entity instanceof EntityLivingBase) {
                d11 = EnchantmentProtection.func_92092_a((EntityLivingBase)((EntityLivingBase)entity), (double)d10);
            }
            entity.field_70159_w += d5 * d11;
            entity.field_70181_x += d7 * d11;
            entity.field_70179_y += d9 * d11;
            if (!(entity instanceof EntityPlayer) || (entityplayer = (EntityPlayer)entity).func_175149_v() || entityplayer.func_184812_l_() && entityplayer.field_71075_bZ.field_75100_b) continue;
            this.func_77277_b().put(entityplayer, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
        }
    }

    public void func_77279_a(boolean spawnParticles) {
        this.world.func_184148_a(null, this.x, this.y, this.z, SoundEvents.field_187539_bB, SoundCategory.BLOCKS, 4.0f, (1.0f + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
        if (this.size >= 2.0f && this.damagesTerrain) {
            this.world.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0, 0.0, 0.0, new int[0]);
        } else {
            this.world.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.x, this.y, this.z, 1.0, 0.0, 0.0, new int[0]);
        }
        if (this.damagesTerrain) {
            for (BlockPos blockpos : this.affectedBlockPositions) {
                IBlockState iblockstate = this.world.func_180495_p(blockpos);
                Block block = iblockstate.func_177230_c();
                if (spawnParticles) {
                    double d0 = (float)blockpos.func_177958_n() + this.world.field_73012_v.nextFloat();
                    double d1 = (float)blockpos.func_177956_o() + this.world.field_73012_v.nextFloat();
                    double d2 = (float)blockpos.func_177952_p() + this.world.field_73012_v.nextFloat();
                    double d3 = d0 - this.x;
                    double d4 = d1 - this.y;
                    double d5 = d2 - this.z;
                    double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5 / (d6 / (double)this.size + 0.1);
                    this.world.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.x) / 2.0, (d1 + this.y) / 2.0, (d2 + this.z) / 2.0, d3 *= (d7 *= (double)(this.world.field_73012_v.nextFloat() * this.world.field_73012_v.nextFloat() + 0.3f)), d4 *= d7, d5 *= d7, new int[0]);
                    this.world.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
                }
                if (iblockstate.func_185904_a() == Material.field_151579_a) continue;
                if (block.func_149659_a((Explosion)this) && this.shooterTwo != null) {
                    this.shooterTwo.addToBlockInv(block.getRegistryName().toString() + ";" + block.func_176201_c(iblockstate));
                }
                block.onBlockExploded(this.world, blockpos, (Explosion)this);
            }
        }
        if (this.causesFire) {
            for (BlockPos blockpos1 : this.affectedBlockPositions) {
                if (this.world.func_180495_p(blockpos1).func_185904_a() != Material.field_151579_a || !this.world.func_180495_p(blockpos1.func_177977_b()).func_185913_b() || this.random.nextInt(3) != 0) continue;
                this.world.func_175656_a(blockpos1, Blocks.field_150480_ab.func_176223_P());
            }
        }
    }
}

