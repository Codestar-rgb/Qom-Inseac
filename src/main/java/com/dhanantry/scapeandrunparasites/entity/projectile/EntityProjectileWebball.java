package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.block.BlockWebBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.function.Consumer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityProjectileWebball extends EntitySRPProjectile {
   private static final float WEB_BLIND_CHANCE = 0.3F;
   private static final int WEB_BLIND_TICKS = 60;
   private static final int WEB_BLIND_AMPLIFIER = 0;
   private byte type;

   public EntityProjectileWebball(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.3F, 0.3F);
      this.type = 1;
   }

   public EntityProjectileWebball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
      this.type = 1;
   }

   public EntityProjectileWebball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, byte t) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.func_70105_a(0.3F, 0.3F);
      this.type = t;
   }

   protected EnumParticleTypes func_184563_j() {
      return EnumParticleTypes.EXPLOSION_NORMAL;
   }

   protected void func_70227_a(RayTraceResult result) {
      if (!this.field_70170_p.field_72995_K) {
         boolean griefing = ForgeEventFactory.getMobGriefingEvent(this.field_70170_p, this);
         Consumer<BlockPos> tryPlaceWeb = pos -> {
            if (griefing && this.field_70170_p.func_175623_d(pos)) {
               this.field_70170_p.func_180501_a(pos, Blocks.field_150321_G.func_176223_P(), 3);
            }
         };
         if (result.field_72308_g instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)result.field_72308_g;
            if (target instanceof EntityPlayer && !target.func_70644_a(MobEffects.field_76440_q) && this.field_70146_Z.nextFloat() < 0.3F) {
               target.func_70690_d(new PotionEffect(MobEffects.field_76440_q, 60, 0, false, true));
            }

            BlockPos feet = new BlockPos(target.field_70165_t, Math.floor(target.func_174813_aQ().field_72338_b), target.field_70161_v);
            tryPlaceWeb.accept(feet);
         } else if (result.field_72313_a == Type.BLOCK) {
            BlockPos placePos = result.func_178782_a().func_177972_a(result.field_178784_b);
            tryPlaceWeb.accept(placePos);
         }

         this.field_70170_p.func_72960_a(this, (byte)3);
         this.func_70106_y();
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.field_70173_aa > 60) {
         this.setWebsAround();
         this.func_70106_y();
      }
   }

   private void setWebsAround() {
      int totalWebs = this.field_70146_Z.nextInt(3) + 1;
      int[] positionss = new int[]{-1, 0, 1};

      for (int i = 1; i <= totalWebs; i++) {
         int pox = positionss[this.field_70146_Z.nextInt(3)];
         int poy = positionss[this.field_70146_Z.nextInt(3)];
         int poz = positionss[this.field_70146_Z.nextInt(3)];
         if (this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t + pox, this.field_70163_u + poy, this.field_70161_v + poz)).func_177230_c()
            == Blocks.field_150350_a) {
            switch (this.type) {
               case 1:
                  this.field_70170_p
                     .func_175656_a(
                        new BlockPos(this.field_70165_t + pox, this.field_70163_u + poy, this.field_70161_v + poz), SRPBlocks.SRPWeb.func_176223_P()
                     );
                  break;
               case 2:
                  this.field_70170_p
                     .func_175656_a(
                        new BlockPos(this.field_70165_t + pox, this.field_70163_u + poy, this.field_70161_v + poz),
                        SRPBlocks.SRPWeb.func_176223_P().func_177226_a(BlockWebBase.VARIANT, BlockWebBase.EnumType.TWO)
                     );
                  break;
               case 3:
                  this.field_70170_p
                     .func_175656_a(
                        new BlockPos(this.field_70165_t + pox, this.field_70163_u + poy, this.field_70161_v + poz),
                        SRPBlocks.SRPWeb.func_176223_P().func_177226_a(BlockWebBase.VARIANT, BlockWebBase.EnumType.THREE)
                     );
            }
         }
      }
   }
}
