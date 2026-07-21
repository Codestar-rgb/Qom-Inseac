package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPResidueFireManager;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockInfestedRemain extends BlockBush {
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
   public static final PropertyInteger SOURCE = PropertyInteger.func_177719_a("source", 0, 1);
   public static final PropertyBool INFESTED_BASE = PropertyBool.func_177716_a("infested_base");

   public BlockInfestedRemain(String name) {
      super(Material.field_151582_l);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149672_a(SRPSoundTypes.VOMIT);
      this.func_149675_a(true);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(SOURCE, 0).func_177226_a(INFESTED_BASE, Boolean.FALSE));
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
      this.setHarvestLevel("shovel", 0);
      this.field_149765_K = 0.52F;
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      return TALL_GRASS_AABB;
   }

   public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
      return this.func_176201_c(state) == 1
         ? worldIn.func_180495_p(pos.func_177977_b()).func_185913_b()
         : this.func_185514_i(worldIn.func_180495_p(pos.func_177977_b()));
   }

   protected boolean func_185514_i(IBlockState state) {
      return state.func_177230_c() == SRPBlocks.optionalDirt || state.func_177230_c() == SRPBlocks.optionalRub;
   }

   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) {
      return true;
   }

   public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
      super.func_176199_a(worldIn, pos, entityIn);
      if (entityIn.field_70122_E && entityIn instanceof EntityLivingBase && !entityIn.func_70093_af()) {
         entityIn.field_70159_w *= 0.84;
         entityIn.field_70179_y *= 0.84;
      }
   }

   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      EntityPlayer harvester = (EntityPlayer)this.harvesters.get();
      if (harvester != null) {
         if (!harvester.field_71075_bZ.field_75098_d) {
            ItemStack main = harvester.func_184614_ca();
            ItemStack off = harvester.func_184592_cb();
            boolean mainIsShovel = !main.func_190926_b() && main.func_77973_b().getToolClasses(main).contains("shovel");
            boolean offIsShovel = !off.func_190926_b() && off.func_77973_b().getToolClasses(off).contains("shovel");
            if (mainIsShovel || offIsShovel) {
               drops.add(new ItemStack(Item.func_150898_a(this)));
            }
         }
      }
   }

   private void scheduleInclineNeighbors(World world, BlockPos pos) {
      if (!world.field_72995_K) {
         for (EnumFacing f : new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST}) {
            BlockPos pSame = pos.func_177972_a(f);
            BlockPos pUp = pSame.func_177984_a();
            BlockPos pDown = pSame.func_177977_b();
            if (world.func_180495_p(pSame).func_177230_c() == this) {
               world.func_175684_a(pSame, this, 1);
            }

            if (world.func_180495_p(pSame).func_185917_h() && world.func_180495_p(pUp).func_177230_c() == this) {
               world.func_175684_a(pUp, this, 1);
            }

            if (world.func_180495_p(pDown).func_177230_c() == this) {
               world.func_175684_a(pDown, this, 1);
            }
         }
      }
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         if (SRPConfigWorld.residueFlammableWave
            && (
               worldIn.func_180495_p(pos.func_177978_c()).func_177230_c() == Blocks.field_150480_ab
                  || worldIn.func_180495_p(pos.func_177968_d()).func_177230_c() == Blocks.field_150480_ab
                  || worldIn.func_180495_p(pos.func_177974_f()).func_177230_c() == Blocks.field_150480_ab
                  || worldIn.func_180495_p(pos.func_177976_e()).func_177230_c() == Blocks.field_150480_ab
                  || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150480_ab
                  || worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150480_ab
            )) {
            worldIn.func_180501_a(pos, Blocks.field_150480_ab.func_176223_P(), 3);
            SRPResidueFireManager.lightAndTrack(worldIn, pos, 6);
            this.scheduleInclineNeighbors(worldIn, pos);
            worldIn.func_184133_a(null, pos, SoundEvents.field_187646_bt, SoundCategory.BLOCKS, 0.7F, 1.1F + worldIn.field_73012_v.nextFloat() * 0.2F);
            if (worldIn instanceof WorldServer) {
               ((WorldServer)worldIn)
                  .func_175739_a(
                     EnumParticleTypes.SMOKE_NORMAL,
                     pos.func_177958_n() + 0.5,
                     pos.func_177956_o() + 0.6,
                     pos.func_177952_p() + 0.5,
                     1,
                     0.0,
                     0.02,
                     0.0,
                     0.0,
                     new int[0]
                  );
            }

            return;
         }

         if (worldIn.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            return;
         }

         if (!worldIn.func_180495_p(pos.func_177977_b()).func_185917_h()) {
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
            return;
         }

         if (rand.nextDouble() < 0.5) {
            if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
               BlockParasiteSpreading.spreadBiomeBlockStain(worldIn, pos, rand);
               return;
            }

            int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
            if (heart > 0) {
               BlockParasiteSpreading.SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
            }
         }

         if (!SRPConfig.allowMobs) {
            return;
         }

         List<Entity> serverList = worldIn.field_72996_f;
         int count = 0;

         for (int x = 0; x < serverList.size(); x++) {
            if (serverList.get(x) instanceof EntityPBeckon) {
               if (++count > SRPConfig.nexusVenkrolCap || this.getDistanceSq(pos, serverList.get(x)) < SRPConfig.nexusVenkrolDis * SRPConfig.nexusVenkrolDis) {
                  return;
               }
            }
         }

         if (SRPConfigSystems.rsResidueY <= pos.func_177956_o()) {
            if (SRPConfigSystems.useEvolution) {
               int chance = this.getEvoResidue(SRPSaveData.get(worldIn, 5).getEvolutionPhase(worldIn.field_73011_w.getDimension()));
               if (chance <= 0) {
                  return;
               }

               if (rand.nextInt(chance) == 0) {
                  if (SRPConfigSystems.rsSkyResidue
                     && !worldIn.func_175678_i(new BlockPos(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5))) {
                     return;
                  }

                  if (ParasiteEventEntity.getRSchance(worldIn) > 0.0) {
                     this.spawnVenkorl(pos, worldIn);
                  }
               }
            } else {
               if (SRPConfigSystems.rsVenkrolChance <= 0) {
                  return;
               }

               if (rand.nextInt(SRPConfigSystems.rsVenkrolChance) == 0) {
                  if (SRPConfigSystems.rsSkyResidue
                     && !worldIn.func_175678_i(new BlockPos(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5))) {
                     return;
                  }

                  this.spawnVenkorl(pos, worldIn);
               }
            }
         }
      }
   }

   private double getDistanceSq(BlockPos pos, Entity entityIn) {
      double d0 = pos.func_177958_n() - entityIn.field_70165_t;
      double d1 = pos.func_177956_o() - entityIn.field_70163_u;
      double d2 = pos.func_177952_p() - entityIn.field_70161_v;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return SRPConfigWorld.residueFlammableWave;
   }

   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return SRPConfigWorld.residueFlammableWave ? 200 : 0;
   }

   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return SRPConfigWorld.residueFlammableWave ? 300 : 0;
   }

   private void spawnVenkorl(BlockPos pos, World worldIn) {
      EntityVenkrol entityOut = new EntityVenkrol(worldIn);
      entityOut.func_70107_b(pos.func_177958_n() + 0.5, pos.func_177956_o() + 1, pos.func_177952_p() + 0.5);
      if (!entityOut.field_70170_p.func_184144_a(entityOut, entityOut.func_174813_aQ()).isEmpty()) {
         entityOut.func_70106_y();
      } else {
         worldIn.func_72838_d(entityOut);
         if (SRPConfigSystems.rsSounds) {
            if (SRPConfigSystems.disloGrowlNoise) {
               if (SRPSaveData.get(entityOut.field_70170_p, 7).getCurrentCode(entityOut.field_70170_p.field_73011_w.getDimension(), 15) == 0) {
                  entityOut.func_184185_a(SRPSounds.VENKROLSI, 4.0F, 1.0F);
               }
            } else {
               entityOut.func_184185_a(SRPSounds.VENKROLSI, 4.0F, 1.0F);
            }
         }
      }
   }

   private void igniteIfPossible(World world, BlockPos pos) {
      if (world.func_175623_d(pos.func_177984_a())) {
         world.func_180501_a(pos.func_177984_a(), Blocks.field_150480_ab.func_176223_P(), 3);
      }
   }

   private boolean anyNeighborIsFire(World world, BlockPos pos) {
      return world.func_180495_p(pos.func_177978_c()).func_177230_c() == Blocks.field_150480_ab
         || world.func_180495_p(pos.func_177968_d()).func_177230_c() == Blocks.field_150480_ab
         || world.func_180495_p(pos.func_177974_f()).func_177230_c() == Blocks.field_150480_ab
         || world.func_180495_p(pos.func_177976_e()).func_177230_c() == Blocks.field_150480_ab
         || world.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150480_ab
         || world.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150480_ab;
   }

   private void sizzle(World world, BlockPos pos) {
      world.func_184133_a(null, pos, SoundEvents.field_187646_bt, SoundCategory.BLOCKS, 0.7F, 1.1F + world.field_73012_v.nextFloat() * 0.2F);
      double x = pos.func_177958_n() + 0.5;
      double y = pos.func_177956_o() + 0.6;
      double z = pos.func_177952_p() + 0.5;
      if (world.field_72995_K) {
         world.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0, 0.02, 0.0, new int[0]);
      } else if (world instanceof WorldServer) {
         ((WorldServer)world).func_175739_a(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 1, 0.0, 0.02, 0.0, 0.0, new int[0]);
      }
   }

   private void igniteShort(World world, BlockPos pos) {
      int TTL = 5;
      SRPResidueFireManager.lightAndTrack(world, pos.func_177984_a(), 5);
   }

   public void func_189540_a(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighborPos) {
      super.func_189540_a(state, world, pos, neighborBlock, neighborPos);
      if (!world.field_72995_K) {
         if (SRPConfigWorld.residueFlammableWave) {
            if (this.anyNeighborIsFire(world, pos)) {
               world.func_175684_a(pos, this, 1);
            }
         }
      }
   }

   public void func_180663_b(World world, BlockPos pos, IBlockState state) {
      super.func_180663_b(world, pos, state);
      BlockPos up = pos.func_177984_a();
      if (world.func_180495_p(up).func_177230_c() == Blocks.field_150480_ab) {
         world.func_175698_g(up);
      }
   }

   public void func_176213_c(World world, BlockPos pos, IBlockState state) {
      super.func_176213_c(world, pos, state);
      if (!world.field_72995_K) {
         if (SRPConfigWorld.residueFlammableWave) {
            if (this.anyNeighborIsFire(world, pos)) {
               world.func_175684_a(pos, this, 1);
            }
         }
      }
   }

   private int getEvoResidue(byte in) {
      int q = -5;
      switch (in) {
         case 1:
            q = SRPConfigSystems.phaseResidueOne;
            break;
         case 2:
            q = SRPConfigSystems.phaseResidueTwo;
            break;
         case 3:
            q = SRPConfigSystems.phaseResidueThree;
            break;
         case 4:
            q = SRPConfigSystems.phaseResidueFour;
            break;
         case 5:
            q = SRPConfigSystems.phaseResidueFive;
            break;
         case 6:
            q = SRPConfigSystems.phaseResidueSix;
            break;
         case 7:
            q = SRPConfigSystems.phaseResidueSeven;
            break;
         case 8:
            q = SRPConfigSystems.phaseResidueEight;
      }

      return q;
   }

   public IBlockState func_176221_a(IBlockState state, IBlockAccess world, BlockPos pos) {
      boolean underIsInfestedNormal = false;
      IBlockState below = world.func_180495_p(pos.func_177977_b());
      Block b = below.func_177230_c();
      ResourceLocation rl = b.getRegistryName();
      if (rl != null) {
         String domain = rl.func_110624_b();
         String path = rl.func_110623_a();
         boolean idMatch = "srparasites".equals(domain) && path.startsWith("infested");
         boolean normalGeom = b.func_149645_b(below) == EnumBlockRenderType.MODEL && b.func_149686_d(below) && b.func_149662_c(below);
         underIsInfestedNormal = idMatch && normalGeom;
      }

      return state.func_177226_a(INFESTED_BASE, underIsInfestedNormal);
   }

   public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      if (!worldIn.field_72995_K) {
         if (worldIn.field_73012_v.nextDouble() < 0.5 && entityIn.field_70173_aa % 20 != 0) {
            return;
         }

         if (!(entityIn instanceof EntityParasiteBase) && entityIn instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)entityIn;
            if (!target.func_70644_a(SRPPotions.COTH_E) && !target.func_70644_a(SRPPotions.EPEL_E)) {
               target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
            }
         }
      }

      if (entityIn.field_70122_E && entityIn instanceof EntityLivingBase && !entityIn.func_70093_af()) {
         entityIn.field_70159_w *= 0.86;
         entityIn.field_70179_y *= 0.86;
      }

      super.func_180634_a(worldIn, pos, state, entityIn);
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return null;
   }

   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
      super.func_180657_a(worldIn, player, pos, state, te, stack);
   }

   public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
      return false;
   }

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      return false;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{SOURCE, INFESTED_BASE});
   }

   public int func_176201_c(IBlockState state) {
      return (Integer)state.func_177229_b(SOURCE);
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(SOURCE, meta & 1);
   }
}
