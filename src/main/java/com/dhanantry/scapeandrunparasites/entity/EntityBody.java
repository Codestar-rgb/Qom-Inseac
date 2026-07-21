package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyHit;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBody extends Entity {
   protected EntityParasiteBase parent;
   protected float damageMultiplier;
   private float eye;
   private float offx;
   private float offy;
   private float offz;
   private int inverted;
   private int partId;
   private boolean logicSide;

   public EntityBody(EntityParasiteBase parent, float width, float height, float damageMultiplier, float offset, float offheight, int inv, int id, boolean side) {
      super(parent.field_70170_p);
      this.func_70105_a(width, height);
      this.parent = parent;
      this.damageMultiplier = damageMultiplier;
      this.eye = height * 0.8F;
      this.offx = offset;
      this.offy = offheight;
      this.offz = offset;
      this.inverted = inv;
      this.partId = id;
      this.logicSide = side;
   }

   public EntityBody(
      EntityParasiteBase parent, float width, float height, float damageMultiplier, float offset, float offheight, int inv, int id, boolean side, float eyes
   ) {
      this(parent, width, height, damageMultiplier, offset, offheight, inv, id, side);
      this.eye = eyes;
   }

   public void func_70071_h_() {
      if (this.parent == null) {
         this.field_70170_p.func_72973_f(this);
      } else if (this.parent.field_70128_L) {
         this.field_70170_p.func_72973_f(this);
      } else {
         if (this.logicSide) {
            this.updatePositionWithParentSides();
         } else {
            this.updatePositionWithParentFront();
         }

         super.func_70071_h_();
      }
   }

   private void updatePositionWithParentSides() {
      float f17 = this.parent.field_70177_z * (float) (Math.PI / 180.0);
      float f3 = MathHelper.func_76126_a(f17);
      float f18 = MathHelper.func_76134_b(f17);
      this.field_70177_z = this.parent.field_70177_z;
      this.func_70634_a(
         this.parent.field_70165_t + (double)this.inverted * (f18 * this.offx),
         this.parent.field_70163_u + this.offy,
         this.parent.field_70161_v + (double)this.inverted * (f3 * this.offz)
      );
   }

   private void updatePositionWithParentFront() {
      float f19 = MathHelper.func_76126_a(this.parent.field_70177_z * (float) (Math.PI / 180.0) - this.parent.field_70704_bt * 0.01F);
      float f14 = (float) (Math.PI / 18);
      float f16 = MathHelper.func_76134_b(f14);
      float f4 = MathHelper.func_76134_b(this.parent.field_70177_z * (float) (Math.PI / 180.0) - this.parent.field_70704_bt * 0.01F);
      this.field_70177_z = this.parent.field_70177_z;
      this.func_70634_a(
         this.parent.field_70165_t + (double)this.inverted * (f19 * this.offx * f16),
         this.parent.field_70163_u + this.offy,
         this.parent.field_70161_v - (double)this.inverted * (f4 * this.offx * f16)
      );
   }

   public boolean func_70097_a(DamageSource source, float amount) {
      if (this.field_70170_p.field_72995_K && source.func_76364_f() instanceof EntityPlayer) {
         SRPMain.network.sendToServer(new SRPPacketEntityBodyHit(this.parent.func_145782_y(), this.partId));
         return false;
      } else if (this.parent instanceof EntityBodyParts) {
         EntityBodyParts rTarget = (EntityBodyParts)this.parent;
         return rTarget.attackEntityBodyFrom(source, amount, this.partId, false);
      } else {
         return this.parent != null ? this.parent.func_70097_a(source, amount) : super.func_70097_a(source, amount);
      }
   }

   public void collideWithNearbyEntities() {
      List<Entity> entities = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72314_b(1.0, 1.0, 1.0).func_72317_d(0.0, 0.0, 0.0));
      double d0 = (this.func_174813_aQ().field_72340_a + this.func_174813_aQ().field_72336_d) / 2.0;
      double d1 = (this.func_174813_aQ().field_72339_c + this.func_174813_aQ().field_72334_f) / 2.0;

      for (Entity entity : entities) {
         if (entity instanceof EntityLivingBase && entity != this.parent && !(entity instanceof EntityParasiteBase)) {
            double d2 = entity.field_70165_t - d0;
            double d3 = entity.field_70161_v - d1;
            double d4 = d2 * d2 + d3 * d3;
            entity.func_70024_g(d2 / d4, 0.2F, d3 / d4);
            entity.func_70097_a(DamageSource.func_76358_a(this.parent), 5.0F);
         }
      }
   }

   public int getId() {
      return this.partId;
   }

   public EntityParasiteBase getFather() {
      return this.parent;
   }

   public float func_70047_e() {
      return this.eye;
   }

   public boolean func_70067_L() {
      return true;
   }

   public AxisAlignedBB func_70046_E() {
      return super.func_70046_E();
   }

   protected void func_70037_a(NBTTagCompound compound) {
   }

   protected void func_70014_b(NBTTagCompound compound) {
   }

   protected void func_70088_a() {
   }

   public void setBodySize(float w, float h) {
      this.func_70105_a(w, h);
   }

   public boolean func_70028_i(Entity entityIn) {
      return this == entityIn || this.parent == entityIn;
   }

   @SideOnly(Side.CLIENT)
   public void func_180426_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
   }
}
