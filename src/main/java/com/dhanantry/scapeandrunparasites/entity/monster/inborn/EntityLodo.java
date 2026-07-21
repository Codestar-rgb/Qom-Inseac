package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLodo extends EntityParasiteBase {
   private int totalGrowtime = 10;
   private int actualGrowtime = 0;
   protected double buried;

   public EntityLodo(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.5F, 0.3F);
      this.field_70728_aV = SRPAttributes.XP_LiTTLE;
      this.totalGrowtime = this.field_70146_Z.nextInt(60) + 60;
      this.killcount = -10.0;
      this.type = 1;
      this.buried = -1.0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 5;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg
         .func_75776_a(
            3,
            new EntityAIAvoidEntity(
               this,
               EntityLivingBase.class,
               new Predicate<EntityLivingBase>() {
                  public boolean apply(@Nullable EntityLivingBase entity) {
                     return !(entity instanceof EntityWaterMob)
                        && !(entity instanceof EntityCreeper)
                        && !(entity instanceof EntityParasiteBase)
                        && !(entity instanceof EntityAnimal);
                  }
               },
               8.0F,
               1.0,
               1.0
            )
         );
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.LODO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.LODO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.LODO_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.LODO_KD_RESISTANCE);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.growTimer();
      this.growStage();
      this.buried();
   }

   public boolean buried() {
      if (this.buried == 1.0) {
         this.field_70170_p.func_184134_a(this.x() + 0.5, this.y() + 0.5, this.z() + 0.5, SRPSounds.LODO_EMERGE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
      }

      if (!(this.buried >= 0.0)) {
         if (this.getParasiteStatus() == 3) {
            this.setParasiteStatus(0);
         }

         return false;
      } else {
         this.func_70661_as().func_75499_g();
         this.field_70165_t = this.field_70169_q;
         this.field_70161_v = this.field_70166_s;
         int id = Block.func_176210_f(
            this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v).func_177977_b())
         );
         this.buried -= 0.02;

         for (int i = 0; i < 2; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.BLOCK_CRACK,
                  this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
                  this.field_70163_u,
                  this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
                  this.field_70146_Z.nextGaussian() * 0.02,
                  this.field_70146_Z.nextGaussian() * 0.02,
                  this.field_70146_Z.nextGaussian() * 0.02,
                  new int[]{id}
               );
         }

         return true;
      }
   }

   private double x() {
      return this.field_70165_t;
   }

   private double y() {
      return this.field_70163_u;
   }

   private double z() {
      return this.field_70161_v;
   }

   @Override
   protected void func_82167_n(Entity entityIn) {
      super.func_82167_n(entityIn);
      if (entityIn instanceof EntityLivingBase && this.field_70173_aa % 20 == 0) {
         SRPPotions.applyStackPotion(SRPPotions.COTH_E, (EntityLivingBase)entityIn, 100, 0);
      }
   }

   protected void growStage() {
      if (!this.field_70170_p.field_72995_K && (this.actualGrowtime > this.totalGrowtime && ParasiteEventEntity.canSpawnNext || this.killcount > 1000.0)) {
         this.func_184185_a(SRPSounds.LODO_MUDO, 1.0F, 1.0F);
         ParasiteEventEntity.spawnNext(this, new EntityMudo(this.field_70170_p), true, false);
      }
   }

   protected void growTimer() {
      if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
         this.actualGrowtime++;
      }
   }

   @Override
   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
      return super.func_184645_a(player, hand);
   }

   public float func_70047_e() {
      return 0.3F;
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      return false;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.LODO_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.LODO_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.LODO_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
   }

   protected SoundEvent getStepSound() {
      return SRPSounds.MOBSILENCE;
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74768_a("ruptergrow", this.actualGrowtime);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("ruptergrow", 99)) {
         this.actualGrowtime = compound.func_74762_e("ruptergrow");
      }
   }

   public void setFloorTimer() {
      this.buried = 1.0;
   }

   @SideOnly(Side.CLIENT)
   public double getFloorTimer() {
      return this.buried;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 50) {
         this.buried = 1.0;
      } else {
         super.func_70103_a(id);
      }
   }
}
