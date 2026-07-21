package com.dhanantry.scapeandrunparasites.tileentity;

import javax.annotation.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityNodeRelay extends TileEntity {
   @Nullable
   private BlockPos controllerPos;

   public void setControllerPos(@Nullable BlockPos pos) {
      this.controllerPos = pos;
      this.func_70296_d();
      if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
         this.field_145850_b
            .func_184138_a(
               pos == null ? this.func_174877_v() : this.func_174877_v(),
               this.field_145850_b.func_180495_p(this.func_174877_v()),
               this.field_145850_b.func_180495_p(this.func_174877_v()),
               3
            );
      }
   }

   @Nullable
   public BlockPos getControllerPos() {
      return this.controllerPos;
   }

   public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
      super.func_189515_b(nbt);
      if (this.controllerPos != null) {
         nbt.func_74782_a("ControllerPos", NBTUtil.func_186859_a(this.controllerPos));
      }

      return nbt;
   }

   public void func_145839_a(NBTTagCompound nbt) {
      super.func_145839_a(nbt);
      if (nbt.func_150297_b("ControllerPos", 10)) {
         this.controllerPos = NBTUtil.func_186861_c(nbt.func_74775_l("ControllerPos"));
      } else {
         this.controllerPos = null;
      }
   }

   public NBTTagCompound func_189517_E_() {
      return this.func_189515_b(new NBTTagCompound());
   }

   public SPacketUpdateTileEntity func_189518_D_() {
      return new SPacketUpdateTileEntity(this.func_174877_v(), 0, this.func_189517_E_());
   }

   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
      this.func_145839_a(pkt.func_148857_g());
   }
}
