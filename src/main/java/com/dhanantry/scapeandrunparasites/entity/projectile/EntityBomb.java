package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityBomb extends Entity {
   private static final DataParameter<Integer> FUSE = EntityDataManager.func_187226_a(EntityBomb.class, DataSerializers.field_187192_b);
   @Nullable
   private EntityParasiteBase tntPlacedBy;
   private int fuse = 80;
   private float str = 4.0F;
   private float damage;
   private boolean grief;
   private int rangeRad;
   private static final DataParameter<Byte> SKIN = EntityDataManager.func_187226_a(EntityBomb.class, DataSerializers.field_187191_a);

   public EntityBomb(World worldIn) {
      super(worldIn);
      this.field_70156_m = true;
      this.field_70178_ae = true;
      this.func_70105_a(0.68F, 0.68F);
   }

   public EntityBomb(World worldIn, EntityParasiteBase igniter, boolean canGrief) {
      this(worldIn);
      this.tntPlacedBy = igniter;
      this.func_70107_b(igniter.field_70165_t, igniter.field_70163_u + igniter.func_70047_e() - 0.1F, igniter.field_70161_v);
      this.grief = canGrief;
   }

   public EntityBomb(World worldIn, double x, double y, double z, EntityParasiteBase igniter, float stren) {
      this(worldIn);
      this.func_70107_b(x, y, z);
      float f = (float)(Math.random() * (Math.PI * 2));
      this.field_70159_w = -((float)Math.sin(f)) * 0.02F;
      this.field_70181_x = 0.2F;
      this.field_70179_y = -((float)Math.cos(f)) * 0.02F;
      this.setFuse(80);
      this.field_70169_q = x;
      this.field_70167_r = y;
      this.field_70166_s = z;
      this.tntPlacedBy = igniter;
      this.str = stren;
   }

   protected void func_70088_a() {
      this.field_70180_af.func_187214_a(FUSE, 80);
      this.field_70180_af.func_187214_a(SKIN, (byte)0);
   }

   public void func_70110_aj() {
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public void func_70071_h_() {
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
         this.field_70181_x *= -0.5;
      }

      this.fuse--;
      if (this.fuse <= 0) {
         this.explode();
      } else {
         this.func_70072_I();
      }

      this.onLivingUpdate();
   }

   public void onLivingUpdate() {
      this.collideWithNearbyEntities();
   }

   protected void collideWithNearbyEntities() {
      List<Entity> list = this.field_70170_p.func_175674_a(this, this.func_174813_aQ(), EntitySelectors.func_188442_a(this));
      if (!list.isEmpty()) {
         int i = this.field_70170_p.func_82736_K().func_180263_c("maxEntityCramming");
         if (i > 0 && list.size() > i - 1 && this.field_70146_Z.nextInt(4) == 0) {
            int j = 0;

            for (int k = 0; k < list.size(); k++) {
               if (!list.get(k).func_184218_aH()) {
                  j++;
               }
            }

            if (j > i - 1) {
               this.func_70097_a(DamageSource.field_191291_g, 6.0F);
            }
         }

         for (int l = 0; l < list.size(); l++) {
            Entity entity = list.get(l);
            this.collideWithEntity(entity);
         }
      }
   }

   protected void collideWithEntity(Entity entityIn) {
      entityIn.func_70108_f(this);
   }

   public void func_70030_z() {
      super.func_70030_z();
   }

   private void explode() {
      if (this.str > 0.0F) {
         boolean flag = ForgeEventFactory.getMobGriefingEvent(this.field_70170_p, this) && this.grief;
         ParasiteEventEntity.createExplosion(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.str, flag);
      }

      if (!this.field_70170_p.field_72995_K) {
         this.field_70170_p
            .func_184148_a(
               (EntityPlayer)null,
               this.field_70165_t,
               this.field_70163_u,
               this.field_70161_v,
               SoundEvents.field_187539_bB,
               SoundCategory.BLOCKS,
               0.5F,
               (1.0F + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F) * 0.7F
            );
         float f = 4.0F;
         if (this.tntPlacedBy != null) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
               )
               .func_186662_g(this.rangeRad);

            for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
               if (!(mob instanceof EntityParasiteBase) && mob.func_70685_l(this)) {
                  mob.func_70097_a(DamageSource.func_76356_a(this, this.tntPlacedBy), this.damage);
                  SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 300, 0);
                  if (this.tntPlacedBy.func_70089_S()) {
                     this.tntPlacedBy.attackEntityAsMobMinimum(mob, this.tntPlacedBy.getMiniDamage() * 3.0F);
                  }
               }
            }
         }

         if (this.getSkin() == 2) {
            ParasiteEventEntity.spawnFromList(this, SRPConfigMobs.jinjoMobs, null);
         }

         EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
         entityareaeffectcloud.setRadius(this.rangeRad, 0.5F);
         entityareaeffectcloud.setWaitTime(5);
         entityareaeffectcloud.setDuration(60);
         entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
         entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 0));
         entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
         entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.VIRA_E, 3600, 0, false, false));
         this.field_70170_p.func_72838_d(entityareaeffectcloud);
         this.func_70106_y();
      }
   }

   public void setMotion(double xSpeedIn, double ySpeedIn, double zSpeedIn, double capX, double capY) {
      xSpeedIn = Math.min(xSpeedIn, capX);
      ySpeedIn = Math.min(ySpeedIn, capY);
      zSpeedIn = Math.min(zSpeedIn, capX);
      this.field_70159_w = xSpeedIn * (Math.random() * 2.0 - 1.0);
      this.field_70181_x = ySpeedIn;
      this.field_70179_y = zSpeedIn * (Math.random() * 2.0 - 1.0);
   }

   public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
      float f = -MathHelper.func_76126_a(rotationYawIn * (float) (Math.PI / 180.0)) * MathHelper.func_76134_b(rotationPitchIn * (float) (Math.PI / 180.0));
      float f1 = -MathHelper.func_76126_a((rotationPitchIn + pitchOffset) * (float) (Math.PI / 180.0));
      float f2 = MathHelper.func_76134_b(rotationYawIn * (float) (Math.PI / 180.0)) * MathHelper.func_76134_b(rotationPitchIn * (float) (Math.PI / 180.0));
      this.shootTwo(f, f1, f2, velocity, inaccuracy);
      this.field_70159_w = this.field_70159_w + entityThrower.field_70159_w;
      this.field_70179_y = this.field_70179_y + entityThrower.field_70179_y;
      if (!entityThrower.field_70122_E) {
         this.field_70181_x = this.field_70181_x + entityThrower.field_70181_x;
      }
   }

   public void shootTwo(double x, double y, double z, float velocity, float inaccuracy) {
      float f = MathHelper.func_76133_a(x * x + y * y + z * z);
      x /= f;
      y /= f;
      z /= f;
      x += this.field_70146_Z.nextGaussian() * 0.0075F * inaccuracy;
      y += this.field_70146_Z.nextGaussian() * 0.0075F * inaccuracy;
      z += this.field_70146_Z.nextGaussian() * 0.0075F * inaccuracy;
      x *= velocity;
      y *= velocity;
      z *= velocity;
      this.field_70159_w = x;
      this.field_70181_x = y;
      this.field_70179_y = z;
      float f1 = MathHelper.func_76133_a(x * x + z * z);
      this.field_70177_z = (float)(MathHelper.func_181159_b(x, z) * (180.0 / Math.PI));
      this.field_70125_A = (float)(MathHelper.func_181159_b(y, f1) * (180.0 / Math.PI));
      this.field_70126_B = this.field_70177_z;
      this.field_70127_C = this.field_70125_A;
   }

   protected void func_70014_b(NBTTagCompound compound) {
      compound.func_74777_a("Fuse", (short)this.getFuse());
      compound.func_74768_a("parasitetype", this.getSkin());
      compound.func_74776_a("stren", this.str);
      compound.func_74757_a("cangrief", this.grief);
   }

   protected void func_70037_a(NBTTagCompound compound) {
      this.setFuse(compound.func_74765_d("Fuse"));
      if (compound.func_150297_b("parasitetype", 99)) {
         this.setSkin(compound.func_74762_e("parasitetype"));
      }

      if (compound.func_150297_b("stren", 99)) {
         this.str = compound.func_74760_g("stren");
      }

      if (compound.func_150297_b("cangrief", 99)) {
         this.grief = compound.func_74767_n("cangrief");
      }
   }

   @Nullable
   public EntityLivingBase getTntPlacedBy() {
      return this.tntPlacedBy;
   }

   public float func_70047_e() {
      return 0.0F;
   }

   public void setFuse(int fuseIn) {
      this.field_70180_af.func_187227_b(FUSE, fuseIn);
      this.fuse = fuseIn;
   }

   public void setStren(float in) {
      this.str = in;
   }

   public void updateSTR() {
      this.field_70170_p.func_72960_a(this, (byte)this.str);
   }

   public void func_184206_a(DataParameter<?> key) {
      if (FUSE.equals(key)) {
         this.fuse = this.getFuseDataManager();
      }
   }

   public int getFuseDataManager() {
      return (Integer)this.field_70180_af.func_187225_a(FUSE);
   }

   public int getFuse() {
      return this.fuse;
   }

   public byte getSkin() {
      return (Byte)this.field_70180_af.func_187225_a(SKIN);
   }

   public void setSkin(int texture) {
      this.field_70180_af.func_187227_b(SKIN, (byte)texture);
   }

   public void setDamage(float in, int radius) {
      this.damage = in;
      this.rangeRad = radius;
   }

   public void func_70103_a(byte id) {
      this.str = id;
      if (id >= 2) {
         this.grief = true;
      }
   }
}
