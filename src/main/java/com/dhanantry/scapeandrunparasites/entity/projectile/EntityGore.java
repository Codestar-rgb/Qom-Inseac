package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityCanister;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGore extends Entity {
   private static final DataParameter<Integer> SKIN = EntityDataManager.func_187226_a(EntityGore.class, DataSerializers.field_187192_b);
   private int groun;
   private byte type;
   public ArrayList<String> inbBlockName;
   public ArrayList<Integer> inbBlockNumber;
   public String entityName;

   public EntityGore(World worldIn) {
      super(worldIn);
      this.field_70178_ae = true;
      this.func_70105_a(0.4F, 0.4F);
   }

   public EntityGore(World worldIn, double x, double y, double z, EntityLivingBase igniter, float stren) {
      this(worldIn);
      this.func_70107_b(x, y, z);
      float f = (float)(Math.random() * (Math.PI * 2));
      this.field_70159_w = -((float)Math.sin(f)) * 0.02F;
      this.field_70181_x = 0.2F;
      this.field_70179_y = -((float)Math.cos(f)) * 0.02F;
      this.field_70169_q = x;
      this.field_70167_r = y;
      this.field_70166_s = z;
      this.inbBlockName = new ArrayList<>();
      this.inbBlockNumber = new ArrayList<>();
   }

   protected void func_70088_a() {
      this.field_70180_af.func_187214_a(SKIN, 0);
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public boolean func_70067_L() {
      return false;
   }

   public void func_70071_h_() {
      if (this.field_70173_aa % 20 == 0 && this.field_70170_p.func_180495_p(this.func_180425_c()).func_185904_a() == Material.field_151586_h) {
         this.func_70106_y();
      } else {
         this.field_70169_q = this.field_70165_t;
         this.field_70167_r = this.field_70163_u;
         this.field_70166_s = this.field_70161_v;
         if (!this.func_189652_ae()) {
            this.field_70181_x -= 0.04F;
         }

         this.func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
         this.field_70159_w *= 0.98F;
         this.field_70181_x *= 0.98F;
         this.field_70179_y *= 0.98F;
         if (this.field_70122_E) {
            this.field_70159_w *= 0.7F;
            this.field_70179_y *= 0.7F;
         }

         this.func_70072_I();
         if (this.field_70170_p.field_72995_K && this.field_70173_aa % 5 == 0 && !this.field_70122_E) {
            switch (this.getSkin()) {
               case 1:
                  for (int i = 0; i <= 0; i++) {
                     this.spawnParticles(SRPEnumParticle.GSPLASH, 0, 0, 0);
                     this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
                  }
                  break;
               case 2:
                  for (int i = 0; i <= 0; i++) {
                     this.spawnParticles(SRPEnumParticle.GCLOUD, 150, 0, 0);
                     this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
                  }
                  break;
               case 3:
                  for (int i = 0; i <= 0; i++) {
                     this.spawnParticles(SRPEnumParticle.GCLOUD, 200, 200, 0);
                     this.spawnParticles(SRPEnumParticle.GSPLASH, 3, -1, -1);
                  }
                  break;
               case 4:
                  for (int i = 0; i <= 0; i++) {
                     this.spawnParticles(SRPEnumParticle.GSPLASH, 0, 0, 0);
                  }
            }
         }

         if (this.field_70122_E) {
            this.groun++;
         }

         if (this.field_70173_aa >= 200) {
            this.func_70106_y();
         }

         if (this.groun >= 1) {
            if (!this.field_70170_p.field_72995_K) {
               switch (this.type) {
                  case 1:
                     this.placeBlood(SRPBlocks.goreSim.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.SMALL));
                     break;
                  case 2:
                     this.placeBlood(SRPBlocks.gorePri.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.SMALL));
                     break;
                  case 3:
                     this.placeBlood(SRPBlocks.goreAda.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.SMALL));
                     break;
                  case 4:
                     this.placeBlood(SRPBlocks.gorePur.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.SMALL));
                     break;
                  case 5:
                     this.placeBlood(SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.SMALL));
                     break;
                  case 6:
                     this.placeBlood(SRPBlocks.goreMar.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.SMALL));
                     break;
                  case 10:
                     Block blockx = this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c();
                     if (blockx != Blocks.field_150350_a && blockx != Blocks.field_150349_c) {
                        BlockPos helper = this.func_180425_c();
                        int i = 4;

                        for (int randx = this.field_70146_Z.nextInt(4); i >= 0; randx = (randx + 1) % 4) {
                           i--;
                           helper = directionToSpread(this.func_180425_c(), randx);
                           blockx = this.field_70170_p.func_180495_p(helper).func_177230_c();
                           if (blockx == Blocks.field_150350_a || blockx == Blocks.field_150349_c) {
                              this.field_70170_p.func_180501_a(helper, SRPBlocks.ParasiteCanisterActive.func_176223_P(), 3);
                              this.addCystItems(helper);
                              this.func_70106_y();
                              return;
                           }
                        }

                        this.field_70170_p.func_180501_a(this.func_180425_c(), SRPBlocks.ParasiteCanisterActive.func_176223_P(), 3);
                        this.addCystItems(this.func_180425_c());
                     } else {
                        this.field_70170_p.func_180501_a(this.func_180425_c(), SRPBlocks.ParasiteCanisterActive.func_176223_P(), 3);
                        this.addCystItems(this.func_180425_c());
                     }
                     break;
                  case 11:
                     Block block = this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c();
                     if (block != Blocks.field_150350_a && block != Blocks.field_150349_c) {
                        BlockPos helper = this.func_180425_c();
                        int i = 4;

                        for (int rand = this.field_70146_Z.nextInt(4); i >= 0; rand = (rand + 1) % 4) {
                           i--;
                           helper = directionToSpread(this.func_180425_c(), rand);
                           block = this.field_70170_p.func_180495_p(helper).func_177230_c();
                           if (block == Blocks.field_150350_a || block == Blocks.field_150349_c) {
                              this.field_70170_p.func_180501_a(helper, SRPBlocks.dodN.func_176223_P(), 3);
                              this.func_70106_y();
                              return;
                           }
                        }
                     } else {
                        this.field_70170_p.func_180501_a(this.func_180425_c(), SRPBlocks.dodN.func_176223_P(), 3);
                     }
                     break;
                  case 12:
                     IBlockState state = SRPBlocks.InfestRemain.func_176203_a(1);
                     Block block22 = this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c();
                     if ((block22 == Blocks.field_150350_a || block22 instanceof BlockBush) && block22 != SRPBlocks.goreSim) {
                        this.field_70170_p.func_175656_a(this.func_180425_c(), state);
                     } else {
                        BlockPos helper = this.func_180425_c();
                        int i = 4;
                        int rand = this.field_70146_Z.nextInt(4);
                        int ccc = 0;

                        while (i >= 0) {
                           i--;
                           helper = directionToSpread(this.func_180425_c(), rand);
                           block22 = this.field_70170_p.func_180495_p(helper).func_177230_c();
                           if ((block22 == Blocks.field_150350_a || block22 instanceof BlockBush) && block22 != SRPBlocks.goreSim) {
                              this.field_70170_p.func_175656_a(helper, state);
                              if (++ccc > 2) {
                                 return;
                              }

                              rand = (rand + 1) % 4;
                           } else {
                              rand = (rand + 1) % 4;
                           }
                        }
                     }
                     break;
                  case 111:
                     if (this.entityName != null
                        && this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
                        && (
                           this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
                              || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
                        )) {
                        this.field_70170_p
                           .func_175656_a(this.func_180425_c(), SRPBlocks.goreSim.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
                        EntityRemain nnn = new EntityRemain(this.field_70170_p);
                        nnn.func_70012_b(
                           this.func_180425_c().func_177958_n() + 0.5,
                           this.func_180425_c().func_177956_o(),
                           this.func_180425_c().func_177952_p() + 0.5,
                           0.0F,
                           0.0F
                        );
                        nnn.setParasite(this.entityName);
                        nnn.setSkin((byte)this.getSkin());
                        nnn.setGoal(20 * SRPConfig.feralRemainValue);
                        this.field_70170_p.func_72838_d(nnn);
                     }
               }
            }

            this.func_70106_y();
            if (this.field_70170_p.field_72995_K) {
               for (int k = 0; k < 10; k++) {
                  this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
               }
            }
         }
      }
   }

   private void addCystItems(BlockPos pos) {
      if (this.inbBlockName != null) {
         if (!this.inbBlockName.isEmpty()) {
            TileEntity tileentity = this.field_70170_p.func_175625_s(pos);
            if (tileentity instanceof TileEntityCanister) {
               TileEntityCanister cyst = (TileEntityCanister)tileentity;
               String[] here = new String[2];
               List<ItemStack> preDrops = new ArrayList<>();
               int loop = 0;

               for (int i = 0; i < this.inbBlockName.size(); i++) {
                  here = this.inbBlockName.get(i).split(";");
                  String name = here[0];
                  int meta = 0;

                  try {
                     meta = Integer.parseInt(here[1]);
                  } catch (Exception var14) {
                  }

                  for (Block block = Block.func_149684_b(name); loop < this.inbBlockNumber.get(i); loop++) {
                     boolean fat = true;

                     try {
                        preDrops.addAll(block.getDrops(this.field_70170_p, pos, block.func_176203_a(meta), 1));
                        fat = false;
                     } catch (Exception var13) {
                     }

                     if (fat) {
                        preDrops.add(new ItemStack(block));
                     }
                  }

                  cyst.addStack(preDrops);
                  loop = 0;
                  preDrops = new ArrayList<>();
               }
            }
         }
      }
   }

   private void placeBlood(IBlockState state) {
      Block block = this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c();
      if ((block == Blocks.field_150350_a || block instanceof BlockBush) && !(block instanceof BlockGore)) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), state);
      } else {
         BlockPos helper = this.func_180425_c();
         int i = 4;

         for (int rand = this.field_70146_Z.nextInt(4); i >= 0; rand = (rand + 1) % 4) {
            i--;
            helper = directionToSpread(this.func_180425_c(), rand);
            block = this.field_70170_p.func_180495_p(helper).func_177230_c();
            if ((block == Blocks.field_150350_a || block instanceof BlockBush) && !(block instanceof BlockGore)) {
               this.field_70170_p.func_175656_a(helper, state);
               return;
            }
         }
      }
   }

   private static BlockPos directionToSpread(BlockPos atm, int choice) {
      switch (choice) {
         case 0:
            atm = atm.func_177978_c();
            break;
         case 1:
            atm = atm.func_177974_f();
            break;
         case 2:
            atm = atm.func_177976_e();
            break;
         default:
            atm = atm.func_177968_d();
      }

      return atm;
   }

   public void setType(byte in) {
      this.type = in;
      if (this.type == 12) {
         in = 4;
      }

      if (this.type == 111) {
         in = 1;
      }

      this.setSkin(in);
   }

   public void onLivingUpdate() {
   }

   protected void collideWithNearbyEntities() {
   }

   protected void collideWithEntity(Entity entityIn) {
      entityIn.func_70108_f(this);
   }

   public void func_70030_z() {
      super.func_70030_z();
   }

   public void setMotion(double xSpeedIn, double ySpeedIn, double zSpeedIn, double capX, double capY) {
      xSpeedIn = Math.min(xSpeedIn, capX);
      ySpeedIn = Math.min(ySpeedIn, capY);
      zSpeedIn = Math.min(zSpeedIn, capX);
      this.field_70159_w = xSpeedIn * (Math.random() * 2.0 - 1.0);
      this.field_70181_x = ySpeedIn;
      this.field_70179_y = zSpeedIn * (Math.random() * 2.0 - 1.0);
   }

   protected void func_70014_b(NBTTagCompound compound) {
      compound.func_74768_a("parasitetype", this.getSkin());
      compound.func_74768_a("bloodtype", this.type);
      NBTTagList allResS = new NBTTagList();
      NBTTagList allResI = new NBTTagList();
      if (this.inbBlockName != null) {
         for (int i = 0; i < this.inbBlockName.size(); i++) {
            String res = this.inbBlockName.get(i);
            NBTTagCompound resT = new NBTTagCompound();
            resT.func_74778_a("block" + i, res);
            allResS.func_74742_a(resT);
            int resi = this.inbBlockNumber.get(i);
            NBTTagCompound resU = new NBTTagCompound();
            resU.func_74768_a("block" + i, resi);
            allResI.func_74742_a(resU);
         }

         compound.func_74782_a("srpinvblocksname", allResS);
         compound.func_74782_a("srpinvblocksnumber", allResI);
      }
   }

   protected void func_70037_a(NBTTagCompound compound) {
      if (compound.func_150297_b("parasitetype", 99)) {
         this.setSkin(compound.func_74762_e("parasitetype"));
      }

      if (compound.func_150297_b("bloodtype", 99)) {
         this.type = (byte)compound.func_74762_e("bloodtype");
      }

      if (compound.func_74764_b("srpinvblocks")) {
         NBTTagList allResS = compound.func_150295_c("srpinvblocksname", 10);
         NBTTagList allResI = compound.func_150295_c("srpinvblocksnumber", 10);
         if (allResS.func_74745_c() != allResI.func_74745_c()) {
            return;
         }

         for (int i = 0; i < allResS.func_74745_c(); i++) {
            NBTTagCompound resT = allResS.func_150305_b(i);
            String res = resT.func_74779_i("block" + i);
            this.inbBlockName.add(i, res);
            NBTTagCompound resU = allResI.func_150305_b(i);
            int resi = resU.func_74762_e("block" + i);
            this.inbBlockNumber.add(i, resi);
         }
      }
   }

   public float func_70047_e() {
      return 0.0F;
   }

   public int getSkin() {
      return (Integer)this.field_70180_af.func_187225_a(SKIN);
   }

   public void setSkin(int texture) {
      this.field_70180_af.func_187227_b(SKIN, texture);
   }

   @SideOnly(Side.CLIENT)
   public void spawnParticles(SRPEnumParticle particleType, int r, int g, int b) {
      double d0 = this.field_70146_Z.nextGaussian() * 0.02;
      double d1 = this.field_70146_Z.nextGaussian() * 0.02;
      double d2 = this.field_70146_Z.nextGaussian() * 0.02;
      ParticleSpawner.spawnParticle(
         particleType,
         this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
         this.field_70163_u + 0.5 + this.field_70146_Z.nextFloat() * this.field_70131_O,
         this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
         d0,
         d1,
         d2,
         r,
         g,
         b
      );
   }
}
