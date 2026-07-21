package com.dhanantry.scapeandrunparasites.world;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

public class SRPExplosion extends Explosion {
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

   @SideOnly(Side.CLIENT)
   public SRPExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions) {
      this(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
   }

   @SideOnly(Side.CLIENT)
   public SRPExplosion(
      World worldIn, Entity entityIn, double x, double y, double z, float size, boolean causesFire, boolean damagesTerrain, List<BlockPos> affectedPositions
   ) {
      this(worldIn, entityIn, x, y, z, size, causesFire, damagesTerrain);
      this.affectedBlockPositions.addAll(affectedPositions);
   }

   public SRPExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean damagesTerrain) {
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
      if (!this.world.field_72995_K) {
         Set<BlockPos> set = Sets.newHashSet();
         int i = 16;

         for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
               for (int l = 0; l < i; l++) {
                  if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                     double d0 = j / 15.0F * 2.0F - 1.0F;
                     double d1 = k / 15.0F * 2.0F - 1.0F;
                     double d2 = l / 15.0F * 2.0F - 1.0F;
                     double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                     d0 /= d3;
                     d1 /= d3;
                     d2 /= d3;
                     float f = this.size * (0.7F + this.world.field_73012_v.nextFloat() * 0.6F);
                     double d4 = this.x;
                     double d6 = this.y;
                     double d8 = this.z;

                     for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
                        BlockPos blockpos = new BlockPos(d4, d6, d8);
                        IBlockState iblockstate = this.world.func_180495_p(blockpos);
                        if (iblockstate.func_185904_a() != Material.field_151579_a) {
                           float f2 = this.exploder != null
                              ? this.exploder.func_180428_a(this, this.world, blockpos, iblockstate)
                              : iblockstate.func_177230_c().getExplosionResistance(this.world, blockpos, null, this);
                           f -= (f2 + 0.3F) * 0.3F;
                        }

                        if (f > 0.0F && (this.exploder == null || this.exploder.func_174816_a(this, this.world, blockpos, iblockstate, f))) {
                           set.add(blockpos);
                        }

                        d4 += d0 * 0.3F;
                        d6 += d1 * 0.3F;
                        d8 += d2 * 0.3F;
                     }
                  }
               }
            }
         }

         this.affectedBlockPositions.addAll(set);
         float f3 = this.size * 2.0F;
         double df1 = f3 - 1.0;
         double df2 = f3 + 1.0;
         int k1 = MathHelper.func_76128_c(this.x - df1);
         int l1 = MathHelper.func_76128_c(this.x + df2);
         int i2 = MathHelper.func_76128_c(this.y - df1);
         int i1 = MathHelper.func_76128_c(this.y + df2);
         int j2 = MathHelper.func_76128_c(this.z - df1);
         int j1 = MathHelper.func_76128_c(this.z + df2);
         List<Entity> list = this.world.func_72839_b(this.exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
         ForgeEventFactory.onExplosionDetonate(this.world, this, list, f3);
         Vec3d vec3d = new Vec3d(this.x, this.y, this.z);

         for (Entity entity : list) {
            if ((!(entity instanceof EntityParasiteBase) || !SRPConfig.explotionDamPara) && !entity.func_180427_aV()) {
               double d12 = entity.func_70011_f(this.x, this.y, this.z) / f3;
               if (d12 <= 1.0) {
                  double d5 = entity.field_70165_t - this.x;
                  double d7 = entity.field_70163_u + entity.func_70047_e() - this.y;
                  double d9 = entity.field_70161_v - this.z;
                  double d13 = MathHelper.func_76133_a(d5 * d5 + d7 * d7 + d9 * d9);
                  if (d13 != 0.0) {
                     d5 /= d13;
                     d7 /= d13;
                     d9 /= d13;
                     double d14 = this.world.func_72842_a(vec3d, entity.func_174813_aQ());
                     double d10 = (1.0 - d12) * d14;
                     entity.func_70097_a(DamageSource.func_94539_a(this), (int)((d10 * d10 + d10) / 2.0 * 7.0 * f3 + 1.0));
                     double d11 = d10;
                     if (entity instanceof EntityLivingBase) {
                        d11 = EnchantmentProtection.func_92092_a((EntityLivingBase)entity, d10);
                     }

                     entity.field_70159_w += d5 * d11;
                     entity.field_70181_x += d7 * d11;
                     entity.field_70179_y += d9 * d11;
                     if (entity instanceof EntityPlayer) {
                        EntityPlayer entityplayer = (EntityPlayer)entity;
                        if (!entityplayer.func_175149_v() && (!entityplayer.func_184812_l_() || !entityplayer.field_71075_bZ.field_75100_b)) {
                           this.func_77277_b().put(entityplayer, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void func_77279_a(boolean spawnParticles) {
      this.world
         .func_184148_a(
            null,
            this.x,
            this.y,
            this.z,
            SoundEvents.field_187539_bB,
            SoundCategory.BLOCKS,
            4.0F,
            (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F
         );
      if (this.size >= 2.0F && this.damagesTerrain) {
         this.world.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0, 0.0, 0.0, new int[0]);
      } else {
         this.world.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.x, this.y, this.z, 1.0, 0.0, 0.0, new int[0]);
      }

      if (this.damagesTerrain) {
         for (BlockPos blockpos : this.affectedBlockPositions) {
            IBlockState iblockstate = this.world.func_180495_p(blockpos);
            Block block = iblockstate.func_177230_c();
            if (spawnParticles) {
               double d0 = blockpos.func_177958_n() + this.world.field_73012_v.nextFloat();
               double d1 = blockpos.func_177956_o() + this.world.field_73012_v.nextFloat();
               double d2 = blockpos.func_177952_p() + this.world.field_73012_v.nextFloat();
               double d3 = d0 - this.x;
               double d4 = d1 - this.y;
               double d5 = d2 - this.z;
               double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
               d3 /= d6;
               d4 /= d6;
               d5 /= d6;
               double d7 = 0.5 / (d6 / this.size + 0.1);
               d7 *= this.world.field_73012_v.nextFloat() * this.world.field_73012_v.nextFloat() + 0.3F;
               d3 *= d7;
               d4 *= d7;
               d5 *= d7;
               this.world
                  .func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.x) / 2.0, (d1 + this.y) / 2.0, (d2 + this.z) / 2.0, d3, d4, d5, new int[0]);
               this.world.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
            }

            if (iblockstate.func_185904_a() != Material.field_151579_a) {
               if (block.func_149659_a(this) && this.shooterTwo != null) {
                  this.shooterTwo.addToBlockInv(block.getRegistryName().toString() + ";" + block.func_176201_c(iblockstate));
               }

               block.onBlockExploded(this.world, blockpos, this);
            }
         }
      }

      if (this.causesFire) {
         for (BlockPos blockpos1 : this.affectedBlockPositions) {
            if (this.world.func_180495_p(blockpos1).func_185904_a() == Material.field_151579_a
               && this.world.func_180495_p(blockpos1.func_177977_b()).func_185913_b()
               && this.random.nextInt(3) == 0) {
               this.world.func_175656_a(blockpos1, Blocks.field_150480_ab.func_176223_P());
            }
         }
      }
   }
}
