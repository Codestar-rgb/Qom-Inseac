package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.compatibility.ModCompatibility;
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
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluid extends BlockFluidClassic {
   private final boolean pushesEntity;
   private static final Material DEADBLOOD_MATERIAL = new MaterialLiquid(MapColor.field_151655_K);

   public BlockFluid(String name, Fluid fluid, Material material, boolean pushEntities) {
      super(fluid, DEADBLOOD_MATERIAL);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.pushesEntity = pushEntities;
      SRPBlocks.SRP_BLOCKS.add(this);
   }

   public EnumBlockRenderType func_149645_b(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   public boolean getPushesEntity() {
      return this.pushesEntity;
   }

   public Vec3d func_176197_a(World world, BlockPos pos, Entity entity, Vec3d vec) {
      return this.getPushesEntity() ? super.func_176197_a(world, pos, entity, vec) : vec;
   }

   public boolean causesDownwardCurrent(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
      IBlockState iblockstate = worldIn.func_180495_p(pos);
      Block block = iblockstate.func_177230_c();
      Material material = iblockstate.func_185904_a();
      if (material == this.field_149764_J) {
         return false;
      } else if (side == EnumFacing.UP) {
         return true;
      } else if (material == Material.field_151588_w) {
         return false;
      } else {
         boolean flag = func_193382_c(block) || block instanceof BlockStairs;
         return !flag && iblockstate.func_193401_d(worldIn, pos, side) == BlockFaceShape.SOLID;
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
      super.func_180655_c(state, world, pos, rand);
      if (this.isCalmDeadBloodSurface(world, pos) && rand.nextInt(90) == 0) {
         double x = pos.func_177958_n() + 0.25 + rand.nextDouble() * 0.5;
         double y = pos.func_177956_o() + 1.02;
         double z = pos.func_177952_p() + 0.25 + rand.nextDouble() * 0.5;
         double dx = (rand.nextDouble() - 0.5) * 0.01;
         double dy = 0.02 + rand.nextDouble() * 0.01;
         double dz = (rand.nextDouble() - 0.5) * 0.01;
         Minecraft.func_71410_x().field_71452_i.func_178927_a(EnumParticleTypes.CLOUD.func_179348_c(), x, y, z, dx, dy, dz, new int[0]);
         if (rand.nextInt(3) == 0) {
            float r = 0.12F;
            float g = 0.28F;
            float b = 0.1F;
            Minecraft.func_71410_x().field_71452_i.func_178927_a(EnumParticleTypes.SPELL_MOB_AMBIENT.func_179348_c(), x, y + 0.02, z, r, g, b, new int[0]);
         }
      }
   }

   @SideOnly(Side.CLIENT)
   private boolean isCalmDeadBloodSurface(World world, BlockPos pos) {
      if (!this.isSourceBlock(world, pos)) {
         return false;
      } else {
         int same = 0;

         for (EnumFacing f : EnumFacing.field_176754_o) {
            if (this.getQuantaValue(world, pos.func_177972_a(f)) > 0) {
               if (++same >= 3) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public Boolean isEntityInsideMaterial(
      IBlockAccess world, BlockPos blockpos, IBlockState iblockstate, Entity entity, double yToTest, Material materialIn, boolean testingHead
   ) {
      Boolean collides = super.isEntityInsideMaterial(
         world, blockpos, iblockstate, entity, yToTest, materialIn == Material.field_151586_h ? iblockstate.func_185904_a() : materialIn, testingHead
      );
      return collides == null ? materialIn == Material.field_151586_h : collides;
   }

   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
      super.func_180634_a(world, pos, state, entity);
      if (entity instanceof EntityParasiteBase) {
         ((EntityLivingBase)entity).func_70691_i(1.0F);
      } else {
         entity.field_70159_w *= 0.85;
         entity.field_70179_y *= 0.85;
         if (entity.field_70181_x < 0.0) {
            entity.field_70181_x *= 0.92;
         }

         entity.field_70143_R = 0.0F;
         if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            this.attackEntityAsMobMinimum((EntityLivingBase)entity, 0.1F);
            if (!ModCompatibility.FLUIDLOGGED_API && !this.isHeadInDeadBlood(world, entity)) {
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

            PotionEffect curVira = mob.func_70660_b(VIRA);
            if (curVira == null || curVira.func_76459_b() < 20) {
               mob.func_70690_d(new PotionEffect(VIRA, 200, 1, false, false));
            }
         }
      }
   }

   private boolean isHeadInDeadBlood(World world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         double eyeY = entity.field_70163_u + entity.func_70047_e();
         BlockPos headPos = new BlockPos(entity.field_70165_t, eyeY, entity.field_70161_v);
         IBlockState state = world.func_180495_p(headPos);
         Block b = state.func_177230_c();
         return b instanceof IFluidBlock ? ((IFluidBlock)b).getFluid() == SRPFluids.DEADBLOOD_FLUID : false;
      }
   }

   public boolean attackEntityAsMobMinimum(EntityLivingBase target, float MinimumDamage) {
      if (MinimumDamage <= 0.0F) {
         return false;
      } else {
         float f1 = target.func_110143_aJ();
         if (f1 <= 0.0F) {
            return false;
         } else if (target instanceof EntityPlayer && ((EntityPlayer)target).field_71075_bZ.field_75098_d) {
            return false;
         } else {
            DamageSource s = DamageSource.field_82727_n;
            float damage = 0.0F;
            if (target.func_70644_a(SRPPotions.VIRA_E)) {
               damage = MinimumDamage * (target.func_70660_b(SRPPotions.VIRA_E).func_76458_c() + 1);
            }

            damage += MinimumDamage;

            try {
               target.func_110142_aN().func_94547_a(s, f1, damage);
            } catch (Exception var12) {
            }

            if (target.func_110139_bj() > 0.0F) {
               target.func_70606_j(f1 - damage / 2.0F);
               target.func_110149_m(target.func_110139_bj() - damage / 2.0F);
            } else {
               target.func_70606_j(f1 - damage);
            }

            target.field_70170_p.func_72960_a(target, (byte)2);
            if (target.func_110143_aJ() <= 0.0F) {
               ItemStack itemstack = null;

               for (EnumHand enumhand : EnumHand.values()) {
                  ItemStack itemstack1 = target.func_184586_b(enumhand);
                  if (itemstack1.func_77973_b() == Items.field_190929_cY) {
                     itemstack = itemstack1.func_77946_l();
                     itemstack1.func_190918_g(1);
                     break;
                  }
               }

               if (itemstack != null) {
                  if (target instanceof EntityPlayerMP) {
                     EntityPlayerMP entityplayermp = (EntityPlayerMP)target;
                     entityplayermp.func_71029_a(StatList.func_188057_b(Items.field_190929_cY));
                     CriteriaTriggers.field_193130_A.func_193187_a(entityplayermp, itemstack);
                  }

                  target.func_70606_j(1.0F);
                  target.func_70674_bp();
                  target.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 900, 1));
                  target.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 100, 1));
                  target.field_70170_p.func_72960_a(target, (byte)35);
               } else {
                  target.func_70645_a(s);
               }
            }

            return true;
         }
      }
   }
}
