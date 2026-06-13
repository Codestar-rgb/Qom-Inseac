/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBiomassBlock
extends Block {
    private static final float DAMAGE_PER_HIT = 1.0f;
    private static final int APPLY_COOLDOWN_TICKS = 20;
    private static final String NBT_KEY_NEXT_APPLY = "srp_biomass_next_apply";
    private static final DamageSource SRC_BIOMASS = new DamageSource("biomass").func_76348_h();

    public BlockBiomassBlock(String name) {
        this(name, true);
    }

    public BlockBiomassBlock(String name, boolean creative) {
        super(Material.field_151571_B);
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.func_149711_c(0.6f);
        this.func_149672_a(SoundType.field_185859_l);
        this.field_149765_K = 0.8f;
        this.func_149715_a(0.4f);
        if (creative) {
            this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        }
        SRPBlocks.SRP_BLOCKS.add(this);
        ItemBlock itemBlock = new ItemBlock((Block)this);
        SRPItems.SRP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
        super.func_176199_a(worldIn, pos, entityIn);
        this.tryAffect(worldIn, pos, entityIn);
    }

    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        this.tryAffect(worldIn, pos, entityIn);
    }

    private void tryAffect(World world, BlockPos pos, Entity entity) {
        NBTTagCompound tag;
        long nextAllowed;
        if (world.field_72995_K) {
            return;
        }
        if (!(entity instanceof EntityLivingBase)) {
            return;
        }
        if (entity instanceof EntityParasiteBase) {
            return;
        }
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).field_71075_bZ.field_75098_d) {
            return;
        }
        EntityLivingBase mob = (EntityLivingBase)entity;
        long now = world.func_82737_E();
        if (now < (nextAllowed = (tag = mob.getEntityData()).func_74763_f(NBT_KEY_NEXT_APPLY))) {
            return;
        }
        tag.func_74772_a(NBT_KEY_NEXT_APPLY, now + 20L);
        mob.func_70097_a(SRC_BIOMASS, 1.0f);
        mob.func_184589_d(SRPPotions.CORRO_E);
        mob.func_70690_d(new PotionEffect(SRPPotions.CORRO_E, 100, 0, false, true));
        mob.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 1000, 3, false, true));
        SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 200, 1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
        BlockPos below;
        boolean openBelow;
        if (rand.nextFloat() < 0.25f) {
            double x = (double)pos.func_177958_n() + 0.2 + rand.nextDouble() * 0.6;
            double y = (double)pos.func_177956_o() + 0.05 + rand.nextDouble() * 0.9;
            double z = (double)pos.func_177952_p() + 0.2 + rand.nextDouble() * 0.6;
            double r = 0.1;
            double g = 0.85;
            double b = 0.2;
            world.func_175688_a(EnumParticleTypes.SPELL_MOB_AMBIENT, x, y, z, r, g, b, new int[0]);
        }
        boolean bl = openBelow = world.func_175623_d(below = pos.func_177977_b()) || !world.func_180495_p(below).isSideSolid((IBlockAccess)world, below, EnumFacing.UP);
        if (openBelow && rand.nextFloat() < 0.1f) {
            double x = (double)pos.func_177958_n() + 0.25 + rand.nextDouble() * 0.5;
            double y = (double)pos.func_177956_o() + 0.01;
            double z = (double)pos.func_177952_p() + 0.25 + rand.nextDouble() * 0.5;
            double vx = (rand.nextDouble() - 0.5) * 0.02;
            double vy = -0.07 - rand.nextDouble() * 0.03;
            double vz = (rand.nextDouble() - 0.5) * 0.02;
            world.func_175688_a(EnumParticleTypes.SLIME, x, y, z, vx, vy, vz, new int[0]);
        }
    }
}

