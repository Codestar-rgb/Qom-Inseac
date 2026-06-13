/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityDod;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDod
extends BlockBase
implements ITileEntityProvider {
    private static final AxisAlignedBB DOD_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 1.0, 0.9375);
    private static final DamageSource DOD_DAMAGE = new DamageSource("srp_dod_block").func_76348_h().func_151518_m();
    private static final String NBT_DOD_LAST_HIT = "srp_dod_last_hit";

    public BlockDod(String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
        super(Material.field_151583_m, name, hardness, creative, tickRandom, resistance);
        this.func_180632_j(this.field_176227_L.func_177621_b());
        this.func_149672_a(SRPSoundTypes.FLESH);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.field_72995_K) {
            return;
        }
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileEntityDod();
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.func_175713_t(pos);
        super.func_180663_b(worldIn, pos, state);
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        return DOD_AABB;
    }

    public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return DOD_AABB;
    }

    private void pushAndHit(World worldIn, BlockPos hitPos, EntityPlayer player) {
        if (player.field_70128_L) {
            return;
        }
        float yawRad = (float)Math.toRadians(player.field_70177_z);
        double lookX = -MathHelper.func_76126_a((float)yawRad);
        double lookZ = MathHelper.func_76134_b((float)yawRad);
        double softStrength = 0.3;
        double softX = -lookX * softStrength;
        double softZ = -lookZ * softStrength;
        player.func_70024_g(softX, 0.05, softZ);
        player.field_70133_I = true;
        long now = worldIn.func_82737_E();
        long lastHit = player.getEntityData().func_74763_f(NBT_DOD_LAST_HIT);
        if (now - lastHit < 20L) {
            return;
        }
        player.getEntityData().func_74772_a(NBT_DOD_LAST_HIT, now);
        double hitStrength = 1.2;
        double hitX = -lookX * hitStrength;
        double hitZ = -lookZ * hitStrength;
        player.func_70024_g(hitX, 0.25, hitZ);
        player.field_70133_I = true;
        float damage = 4.0f;
        player.func_70097_a(DOD_DAMAGE, damage);
        worldIn.func_184133_a(null, hitPos, SRPSounds.ADAPTATION_P, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.func_70690_d(new PotionEffect(SRPPotions.DOD_SMOKE_TRAIL_E, 20, 0, false, false));
    }

    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.func_180634_a(worldIn, pos, state, entityIn);
        if (worldIn.field_72995_K) {
            return;
        }
        if (!(entityIn instanceof EntityPlayer)) {
            return;
        }
        this.pushAndHit(worldIn, pos, (EntityPlayer)entityIn);
    }

    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
        super.func_176199_a(worldIn, pos, entityIn);
        if (worldIn.field_72995_K) {
            return;
        }
        if (!(entityIn instanceof EntityPlayer)) {
            return;
        }
        this.pushAndHit(worldIn, pos, (EntityPlayer)entityIn);
    }
}

