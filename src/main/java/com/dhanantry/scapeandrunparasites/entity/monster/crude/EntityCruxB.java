package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAvoidEntityStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAvoidOrAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCruxB extends EntityParasiteBase {
   private float tSize;
   private float aSize;
   private boolean st1;
   private float tSpeed;
   private int growing;
   private int currentgrow;
   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(EntityCruxB.class, DataSerializers.field_187193_c);

   public EntityCruxB(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.31F, 1.1F);
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, false, false, null, SRPConfig.primitiveSneakPen, SRPConfig.primitiveInviPen)
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  false,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.primitiveSneakPen,
                  SRPConfig.primitiveInviPen
               )
            );
      }

      int atm = SRPConfigMobs.cruxMaxGrowTime - SRPConfigMobs.cruxMinGrowTime + 1;
      if (atm <= 0 || SRPConfigMobs.cruxMaxGrowTime <= SRPConfigMobs.cruxMinGrowTime) {
         atm = SRPConfigMobs.cruxMinGrowTime;
      }

      this.growing = this.field_70146_Z.nextInt(atm) + SRPConfigMobs.cruxMinGrowTime;
      this.st1 = false;
      this.tSize = 1.0F;
      this.aSize = 1.0F;
      this.fuseTime = 70;
      this.killcount = -10.0;
      this.type = 5;
   }

   @Override
   public int getParasiteIDRegister() {
      return 320;
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.0, false, -1.0));
      this.field_70714_bg.func_75776_a(4, new EntityAIAvoidOrAttack(this, 0.0F, 10, 2));
      this.field_70714_bg
         .func_75776_a(
            5,
            new EntityAIAvoidEntityStatus(
               this,
               EntityLiving.class,
               new Predicate<EntityLiving>() {
                  public boolean apply(@Nullable EntityLiving entity) {
                     return !(entity instanceof EntityWaterMob)
                        && !(entity instanceof EntityParasiteBase)
                        && !(entity instanceof EntityAnimal)
                        && !(entity instanceof EntityVillager);
                  }
               },
               8.0F,
               1.3
            )
         );
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(SIZE, this.tSize);
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.CRUXA_HEALTH * 0.3);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.CRUXA_ARMOR * 0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.CRUXA_KD_RESISTANCE * 0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.CRUXA_ATTACK_DAMAGE * 0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.gettSize() > this.getaSize()) {
         this.setaSize(0.01F);
         this.func_70105_a(0.7F + (this.getaSize() - 1.0F), 0.5F + (this.getaSize() - 1.0F));
      }

      this.currentgrow++;
      if (this.currentgrow > this.growing * 20) {
         this.setSelfeState(1);
      }

      if (!this.field_70170_p.field_72995_K && !this.field_70128_L && this.func_110143_aJ() > 0.0F && this.func_110143_aJ() < this.func_110138_aP()) {
         this.func_70606_j(this.func_110143_aJ() + 0.007F);
      }
   }

   public void func_70071_h_() {
      if (this.func_70089_S()) {
         this.lastActiveTime = this.timeSinceIgnited;
         this.dyingBurst(false, 2);
      }

      super.func_70071_h_();
   }

   @Override
   protected void dyingBurst(boolean fromDeath, int value) {
      int i = this.getSelfeState();
      if (i > 0 && this.timeSinceIgnited == 0) {
         this.func_184185_a(SRPSounds.FLESH_GROW, 10.0F, 1.0F);
      }

      this.timeSinceIgnited += i * value;
      if (this.timeSinceIgnited < 0) {
         this.timeSinceIgnited = 0;
      }

      if (this.timeSinceIgnited >= this.fuseTime) {
         this.timeSinceIgnited = this.fuseTime;
         this.selfExplode();
      }
   }

   @Override
   protected void selfExplode() {
      if (!this.field_70170_p.field_72995_K) {
         ParasiteEventEntity.spawnNext(this, new EntityCruxA(this.field_70170_p), true, false);
         this.func_184185_a(SRPSounds.FLESH_PRIMITIVE, 1.0F, 1.0F);
      } else {
         this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
      }
   }

   public float func_70047_e() {
      return 0.3F;
   }

   public float gettSize() {
      return (Float)this.field_70180_af.func_187225_a(SIZE);
   }

   public void settSize(float in) {
      this.tSize += in;
      this.field_70180_af.func_187227_b(SIZE, this.tSize);
   }

   public void settSpeed(float in) {
      this.tSpeed = in;
   }

   public float getaSize() {
      return this.aSize;
   }

   public void setaSize(float in) {
      this.aSize += in;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.FLESH_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.FLESH_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.FLESH_DEATH;
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74776_a("leshtotalsize", this.tSize);
      compound.func_74776_a("leshactualsize", this.aSize);
      compound.func_74776_a("leshspeed", this.tSpeed);
      compound.func_74768_a("growing", this.growing);
      compound.func_74768_a("currentg", this.currentgrow);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("leshtotalsize", 99)) {
         this.tSize = compound.func_74760_g("leshtotalsize");
         this.field_70180_af.func_187227_b(SIZE, compound.func_74760_g("leshtotalsize"));
      }

      if (compound.func_150297_b("leshspeed", 99)) {
         this.tSpeed = compound.func_74760_g("leshspeed");
      }

      if (compound.func_150297_b("growing", 99)) {
         this.growing = compound.func_74762_e("growing");
      }

      if (compound.func_150297_b("currentg", 99)) {
         this.currentgrow = compound.func_74762_e("currentg");
      }
   }

   @SideOnly(Side.CLIENT)
   public float getSelfeFlashIntensityS() {
      return this.aSize;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 4) {
         this.tSize += 0.3F;
      } else {
         super.func_70103_a(id);
      }
   }
}
