package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndGateway;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityAIBlockLight extends EntityAIBase {
   private final EntityParasiteBase parent;
   private int ticks = 0;
   private int range;
   private int lightTrigger;
   private BlockPos target;
   private World world;
   private int progressB;
   private int idle;
   private int neededTime;
   private Block block;
   private int disss;
   private int prevProgressB = -1;
   private ArrayList<Long> cant;

   public EntityAIBlockLight(EntityParasiteBase in, int range, int lightLook) {
      this.parent = in;
      this.world = this.parent.field_70170_p;
      this.range = range;
      this.lightTrigger = lightLook;
      this.cant = new ArrayList<>();
      this.func_75248_a(3);
   }

   public boolean func_75250_a() {
      this.ticks++;
      if (!this.parent.getGeneMod(7)) {
         return false;
      } else if (this.ticks < 40) {
         return false;
      } else {
         this.ticks = 0;
         if (this.parent.field_70170_p.func_82736_K().func_82766_b("mobGriefing") && this.parent.func_70638_az() == null) {
            BlockPos ll = this.findSource();
            if (ll != null) {
               this.target = ll;
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public boolean func_75253_b() {
      return this.target != null && this.world.func_180495_p(this.target).func_177230_c() == this.block && this.parent.func_70638_az() == null;
   }

   public void func_75249_e() {
      this.progressB = 0;
      this.idle = 0;
      this.block = this.world.func_180495_p(this.target).func_177230_c();
      float multiplier = 10.0F;
      IBlockState state = this.world.func_180495_p(this.target);
      this.neededTime = (int)(state.func_185887_b(this.world, this.target) * Math.max(0.0F, multiplier));
   }

   public void func_75251_c() {
      this.parent.func_70661_as().func_75499_g();
      this.prevProgressB = -1;
      this.target = null;
   }

   public void func_75246_d() {
      double r = 5.0;
      double distance = this.realdistanceSq(this.target, this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v);
      if (distance > r) {
         this.parent.func_70661_as().func_75492_a(this.target.func_177958_n(), this.target.func_177956_o(), this.target.func_177952_p(), 1.1);
      }

      if (this.parent.func_184218_aH()) {
         this.func_75251_c();
      } else {
         this.idle++;
         if ((int)Math.round(distance) == this.disss) {
            this.idle++;
         } else {
            this.idle = 0;
         }

         if (this.idle == 120) {
            this.parent.skillBreakBlocks();
         }

         if (this.idle >= 240) {
            boolean canAdd = true;

            for (int p = 0; p < this.cant.size(); p++) {
               if (this.cant.get(p) == this.target.func_177986_g()) {
                  canAdd = false;
               }
            }

            if (canAdd) {
               this.cant.add(this.target.func_177986_g());
            }

            this.func_75251_c();
            this.ticks += 30;
         } else {
            this.disss = (int)Math.round(distance);
            if (distance <= r && this.target != null) {
               this.parent.func_70661_as().func_75492_a(this.target.func_177958_n(), this.target.func_177956_o(), this.target.func_177952_p(), 0.0);
               this.progressB++;
               this.idle = 0;
               int i = (int)((float)this.progressB / this.neededTime);
               if (i != this.prevProgressB) {
                  this.world.func_175715_c(this.parent.func_145782_y(), this.target, i);
                  this.prevProgressB = i;
               }

               if (this.progressB >= this.neededTime) {
                  this.world.func_175655_b(this.target, true);
                  this.progressB = 0;
                  this.ticks += 30;
               }
            }
         }
      }
   }

   private BlockPos findSource() {
      if (this.world.func_175642_b(EnumSkyBlock.BLOCK, this.parent.func_180425_c()) < this.lightTrigger && this.world.field_73012_v.nextInt(3) != 0) {
         return null;
      } else {
         double distanceM = 40000.0;
         BlockPos light = null;
         if (this.parent.func_184218_aH()) {
            return light;
         } else {
            List<BlockPos> sources = new ArrayList<>();

            for (int i = -this.range; i <= this.range; i++) {
               for (int j = -4; j <= this.parent.field_70131_O; j++) {
                  for (int k = -this.range; k <= this.range; k++) {
                     BlockPos atm = new BlockPos(this.parent.field_70165_t + i, this.parent.field_70163_u + j, this.parent.field_70161_v + k);
                     IBlockState state = this.world.func_180495_p(atm);
                     Block block = state.func_177230_c();
                     if (!(block instanceof BlockLiquid)
                        && !(block instanceof BlockPortal)
                        && !(block instanceof BlockEndGateway)
                        && !(block instanceof BlockEndPortalFrame)
                        && !(block instanceof BlockBase)
                        && state.func_185904_a() != Material.field_151567_E
                        && !ParasiteEventEntity.checkName(
                           block.getRegistryName().toString(), SRPConfig.parasiteGriefingBlackList, SRPConfig.parasiteGriefingWhite
                        )
                        && (state.func_185906_d() >= this.lightTrigger || state.func_185904_a() == Material.field_151594_q)) {
                        if (this.cant.size() == 0) {
                           sources.add(atm);
                        } else {
                           boolean canAdd = true;

                           for (int p = 0; p < this.cant.size(); p++) {
                              if (this.cant.get(p) == atm.func_177986_g()) {
                                 canAdd = false;
                                 break;
                              }
                           }

                           if (canAdd) {
                              sources.add(atm);
                           }
                        }
                     }
                  }
               }
            }

            for (BlockPos pos : sources) {
               double distance = pos.func_177954_c(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v);
               if (distance < distanceM) {
                  light = pos;
                  distanceM = distance;
               }
            }

            return light;
         }
      }
   }

   private double realdistanceSq(BlockPos pos, double toX, double toY, double toZ) {
      double d0 = pos.func_177958_n() + 0.5 - toX;
      double d1 = pos.func_177956_o() + 0.5 - toY;
      double d2 = pos.func_177952_p() + 0.5 - toZ;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }
}
