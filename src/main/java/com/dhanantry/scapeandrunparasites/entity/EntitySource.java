package com.dhanantry.scapeandrunparasites.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySource extends Entity {
   private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), Color.RED, Overlay.PROGRESS).func_186741_a(false);
   private byte type;
   private float total;
   private float charging;

   public EntitySource(World parent) {
      super(parent);
      this.func_70105_a(0.5F, 0.5F);
      this.total = 100.0F;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.bossInfo.func_186735_a(this.charging / this.total);
      if (this.field_70173_aa % 20 == 0 && !this.field_70170_p.field_72995_K) {
         this.charging++;
         if (this.charging > this.total) {
            this.attack();
         }
      }
   }

   private void attack() {
      if (this.charging > 200.0F) {
         this.func_70106_y();
      }
   }

   public void func_96094_a(String name) {
      super.func_96094_a(name);
      this.bossInfo.func_186739_a(this.func_145748_c_());
   }

   public void func_184178_b(EntityPlayerMP player) {
      super.func_184178_b(player);
      this.bossInfo.func_186760_a(player);
   }

   public void func_184203_c(EntityPlayerMP player) {
      super.func_184203_c(player);
      this.bossInfo.func_186761_b(player);
   }

   public boolean func_70067_L() {
      return false;
   }

   public ITextComponent func_145748_c_() {
      return new TextComponentString("The Source");
   }

   protected void func_70037_a(NBTTagCompound compound) {
   }

   protected void func_70014_b(NBTTagCompound compound) {
   }

   protected void func_70088_a() {
   }

   public boolean func_70028_i(Entity entityIn) {
      return this == entityIn;
   }

   @SideOnly(Side.CLIENT)
   public void func_180426_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
   }
}
