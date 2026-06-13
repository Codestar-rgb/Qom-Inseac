/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.material.MaterialLiquid
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.BlockFaceShape
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumBlockRenderType
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Plane
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$PooledMutableBlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.BlockFluidBase
 *  net.minecraftforge.fluids.BlockFluidClassic
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPFluids;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import java.util.Random;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluid
extends BlockFluidClassic {
    private boolean pushesEntity;
    private static final Material DEADBLOOD_MATERIAL = new MaterialLiquid(MapColor.field_151655_K);

    public BlockFluid(String name, Fluid fluid, Material material, boolean pushEntities) {
        super(fluid, DEADBLOOD_MATERIAL);
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.pushesEntity = pushEntities;
        SRPBlocks.SRP_BLOCKS.add((Block)this);
    }

    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public boolean getPushesEntity() {
        return this.pushesEntity;
    }

    public void setPushesEntity(boolean parPushesEntity) {
        this.pushesEntity = parPushesEntity;
    }

    public Vec3d func_176197_a(World worldIn, BlockPos pos, Entity entityIn, Vec3d motion) {
        if (this.getPushesEntity()) {
            Vec3d flowAdder = this.getFlow((IBlockAccess)worldIn, pos, worldIn.func_180495_p(pos));
            return motion.func_178787_e(flowAdder);
        }
        return motion;
    }

    protected Vec3d getFlow(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
        double d0 = 0.0;
        double d1 = 0.0;
        double d2 = 0.0;
        int i = this.getRenderedDepth(state);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.func_185346_s();
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            blockpos$pooledmutableblockpos.func_189533_g((Vec3i)pos).func_189536_c(enumfacing);
            int j = this.getRenderedDepth(worldIn.func_180495_p((BlockPos)blockpos$pooledmutableblockpos));
            if (j < 0) {
                if (worldIn.func_180495_p((BlockPos)blockpos$pooledmutableblockpos).func_185904_a().func_76230_c() || (j = this.getRenderedDepth(worldIn.func_180495_p(blockpos$pooledmutableblockpos.func_177977_b()))) < 0) continue;
                int k = j - (i - 8);
                d0 += (double)(enumfacing.func_82601_c() * k);
                d1 += (double)(enumfacing.func_96559_d() * k);
                d2 += (double)(enumfacing.func_82599_e() * k);
                continue;
            }
            int l = j - i;
            d0 += (double)(enumfacing.func_82601_c() * l);
            d1 += (double)(enumfacing.func_96559_d() * l);
            d2 += (double)(enumfacing.func_82599_e() * l);
        }
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        if ((Integer)state.func_177229_b((IProperty)LEVEL) >= 8) {
            for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
                blockpos$pooledmutableblockpos.func_189533_g((Vec3i)pos).func_189536_c(enumfacing1);
                if (!this.causesDownwardCurrent(worldIn, (BlockPos)blockpos$pooledmutableblockpos, enumfacing1) && !this.causesDownwardCurrent(worldIn, blockpos$pooledmutableblockpos.func_177984_a(), enumfacing1)) continue;
                vec3d = vec3d.func_72432_b().func_72441_c(0.0, -6.0, 0.0);
                break;
            }
        }
        blockpos$pooledmutableblockpos.func_185344_t();
        return vec3d.func_72432_b();
    }

    protected int getDepth(IBlockState state) {
        return state.func_185904_a() == this.field_149764_J ? (Integer)state.func_177229_b((IProperty)LEVEL) : -1;
    }

    protected int getRenderedDepth(IBlockState state) {
        int i = this.getDepth(state);
        return i >= 8 ? 0 : i;
    }

    public boolean causesDownwardCurrent(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        Block block = iblockstate.func_177230_c();
        Material material = iblockstate.func_185904_a();
        if (material == this.field_149764_J) {
            return false;
        }
        if (side == EnumFacing.UP) {
            return true;
        }
        if (material == Material.field_151588_w) {
            return false;
        }
        boolean flag = BlockFluid.func_193382_c((Block)block) || block instanceof BlockStairs;
        return !flag && iblockstate.func_193401_d(worldIn, pos, side) == BlockFaceShape.SOLID;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
        super.func_180655_c(state, world, pos, rand);
        if (this.isCalmDeadBloodSurface(world, pos) && rand.nextInt(90) == 0) {
            double x = (double)pos.func_177958_n() + 0.25 + rand.nextDouble() * 0.5;
            double y = (double)pos.func_177956_o() + 1.02;
            double z = (double)pos.func_177952_p() + 0.25 + rand.nextDouble() * 0.5;
            double dx = (rand.nextDouble() - 0.5) * 0.01;
            double dy = 0.02 + rand.nextDouble() * 0.01;
            double dz = (rand.nextDouble() - 0.5) * 0.01;
            Minecraft.func_71410_x().field_71452_i.func_178927_a(EnumParticleTypes.CLOUD.func_179348_c(), x, y, z, dx, dy, dz, new int[0]);
            if (rand.nextInt(3) == 0) {
                float r = 0.12f;
                float g = 0.28f;
                float b = 0.1f;
                Minecraft.func_71410_x().field_71452_i.func_178927_a(EnumParticleTypes.SPELL_MOB_AMBIENT.func_179348_c(), x, y + 0.02, z, (double)r, (double)g, (double)b, new int[0]);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private boolean isCalmDeadBloodSurface(World world, BlockPos pos) {
        float filled;
        IBlockState here = world.func_180495_p(pos);
        if (here.func_177230_c() != this) {
            return false;
        }
        if (!world.func_175623_d(pos.func_177984_a())) {
            return false;
        }
        if (this instanceof BlockFluidBase && (filled = this.getFilledPercentage(world, pos)) < 0.99f) {
            return false;
        }
        int same = 0;
        for (EnumFacing f : new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST}) {
            if (world.func_180495_p(pos.func_177972_a(f)).func_177230_c() != this) continue;
            ++same;
        }
        return same >= 3;
    }

    public Boolean isEntityInsideMaterial(IBlockAccess world, BlockPos pos, IBlockState state, Entity entity, double yToTest, Material materialIn, boolean testingHead) {
        if (materialIn == Material.field_151586_h && state.func_177230_c() == this) {
            return true;
        }
        return super.isEntityInsideMaterial(world, pos, state, entity, yToTest, materialIn, testingHead);
    }

    public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.func_180634_a(world, pos, state, entity);
        if (entity instanceof EntityParasiteBase) {
            ((EntityLivingBase)entity).func_70691_i(1.0f);
            return;
        }
        entity.field_70159_w *= 0.85;
        entity.field_70179_y *= 0.85;
        if (entity.field_70181_x < 0.0) {
            entity.field_70181_x *= 0.92;
        }
        entity.field_70143_R = 0.0f;
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            PotionEffect curVira;
            this.attackEntityAsMobMinimum((EntityLivingBase)entity, 0.1f);
            if (!this.isHeadInDeadBlood(world, entity)) {
                return;
            }
            EntityLivingBase mob = (EntityLivingBase)entity;
            int CORRO_DUR = 100;
            int VIRA_DUR = 200;
            Potion CORRO = SRPPotions.CORRO_E;
            Potion VIRA = SRPPotions.VIRA_E;
            PotionEffect curCorro = mob.func_70660_b(CORRO);
            if (curCorro == null || curCorro.func_76459_b() < 20) {
                mob.func_70690_d(new PotionEffect(CORRO, 100, 0, false, false));
            }
            if ((curVira = mob.func_70660_b(VIRA)) == null || curVira.func_76459_b() < 20) {
                mob.func_70690_d(new PotionEffect(VIRA, 200, 1, false, false));
            }
        }
    }

    private boolean isHeadInDeadBlood(World world, Entity entity) {
        if (entity == null) {
            return false;
        }
        double eyeY = entity.field_70163_u + (double)entity.func_70047_e();
        BlockPos headPos = new BlockPos(entity.field_70165_t, eyeY, entity.field_70161_v);
        IBlockState state = world.func_180495_p(headPos);
        Block b = state.func_177230_c();
        if (b instanceof IFluidBlock) {
            return ((IFluidBlock)b).getFluid() == SRPFluids.DEADBLOOD_FLUID;
        }
        if (b instanceof BlockFluidBase) {
            BlockFluidBase bf = (BlockFluidBase)b;
            if (bf.getFluid() != SRPFluids.DEADBLOOD_FLUID) {
                return false;
            }
            float filled = bf.getFilledPercentage(world, headPos);
            return filled > 0.1f;
        }
        return false;
    }

    public boolean attackEntityAsMobMinimum(EntityLivingBase target, float MinimumDamage) {
        if (MinimumDamage <= 0.0f) {
            return false;
        }
        float f1 = target.func_110143_aJ();
        if (f1 <= 0.0f) {
            return false;
        }
        if (target instanceof EntityPlayer && ((EntityPlayer)target).field_71075_bZ.field_75098_d) {
            return false;
        }
        DamageSource s = DamageSource.field_82727_n;
        float damage = 0.0f;
        if (target.func_70644_a(SRPPotions.VIRA_E)) {
            damage = MinimumDamage * (float)(target.func_70660_b(SRPPotions.VIRA_E).func_76458_c() + 1);
        }
        damage += MinimumDamage;
        try {
            if (target.func_110142_aN() != null && s != null) {
                target.func_110142_aN().func_94547_a(s, f1, damage);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (target.func_110139_bj() > 0.0f) {
            target.func_70606_j(f1 - damage / 2.0f);
            target.func_110149_m(target.func_110139_bj() - damage / 2.0f);
        } else {
            target.func_70606_j(f1 - damage);
        }
        target.field_70170_p.func_72960_a((Entity)target, (byte)2);
        if (target.func_110143_aJ() <= 0.0f) {
            ItemStack itemstack = null;
            for (EnumHand enumhand : EnumHand.values()) {
                ItemStack itemstack1 = target.func_184586_b(enumhand);
                if (itemstack1.func_77973_b() != Items.field_190929_cY) continue;
                itemstack = itemstack1.func_77946_l();
                itemstack1.func_190918_g(1);
                break;
            }
            if (itemstack != null) {
                if (target instanceof EntityPlayerMP) {
                    EntityPlayerMP entityplayermp = (EntityPlayerMP)target;
                    entityplayermp.func_71029_a(StatList.func_188057_b((Item)Items.field_190929_cY));
                    CriteriaTriggers.field_193130_A.func_193187_a(entityplayermp, itemstack);
                }
                target.func_70606_j(1.0f);
                target.func_70674_bp();
                target.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 900, 1));
                target.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 100, 1));
                target.field_70170_p.func_72960_a((Entity)target, (byte)35);
            } else {
                target.func_70645_a(s);
            }
        }
        return true;
    }
}

