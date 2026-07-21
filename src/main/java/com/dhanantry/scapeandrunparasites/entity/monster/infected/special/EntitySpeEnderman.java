package com.dhanantry.scapeandrunparasites.entity.monster.infected.special;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvade;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAssimara;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntitySpeEnderman extends EntityPAssimara {
   private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   private static final AttributeModifier ATTACKING_SPEED_BOOST = new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", 0.15F, 0)
      .func_111168_a(false);
   private static final DataParameter<Boolean> SCREAMING = EntityDataManager.func_187226_a(EntitySpeEnderman.class, DataSerializers.field_187198_h);
   private int lastCreepySound;
   private int targetChangeTime;
   private int toTeleCool;
   private int spotCool;
   private String tpPlayerName;
   private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntitySpeEnderman.class, DataSerializers.field_187192_b);
   private EntityLivingBase targetedEntity;
   private int pulling;
   private boolean canPull;

   public EntitySpeEnderman(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 2.9F);
      this.canModRender = 0;
      this.type = 14;
      this.field_70714_bg.func_85156_a(this.folow);
      this.killcount = -10.0;
      this.field_70138_W = 1.0F;
      this.tpPlayerName = null;
      this.field_70158_ak = true;
      this.canPull = true;
   }

   @Override
   public int getIDSpawn() {
      return 59;
   }

   @Override
   public int getParasiteIDRegister() {
      return 321;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infendermanCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.2, false, 0.0));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvade(this, 20, 0, 1.35, true, 7, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.MARENDERMAN_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.MARENDERMAN_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.1496);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.MARENDERMAN_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
   }

   @Override
   public void func_70624_b(@Nullable EntityLivingBase entitylivingbaseIn) {
      boolean flag = this.func_70638_az() == null;
      super.func_70624_b(entitylivingbaseIn);
      IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
      if (entitylivingbaseIn == null) {
         this.targetChangeTime = 0;
         this.field_70180_af.func_187227_b(SCREAMING, false);
         iattributeinstance.func_111124_b(ATTACKING_SPEED_BOOST);
      } else {
         this.targetChangeTime = this.field_70173_aa;
         this.field_70180_af.func_187227_b(SCREAMING, true);
         if (flag) {
            this.spotCool = SRPConfigMobs.infendermansaw;
            if (entitylivingbaseIn instanceof EntityPlayer) {
               if (this.tpPlayerName != null) {
                  if (!this.tpPlayerName.equals(((EntityPlayer)entitylivingbaseIn).func_70005_c_())) {
                     this.tpPlayerName = ((EntityPlayer)entitylivingbaseIn).func_70005_c_();
                     this.field_70170_p
                        .func_184148_a(
                           (EntityPlayer)null,
                           entitylivingbaseIn.field_70169_q,
                           entitylivingbaseIn.field_70167_r,
                           entitylivingbaseIn.field_70166_s,
                           SRPSounds.ASSENDERMAN_PORTAL,
                           this.func_184176_by(),
                           0.3F,
                           1.0F
                        );
                  }
               } else {
                  this.tpPlayerName = ((EntityPlayer)entitylivingbaseIn).func_70005_c_();
                  this.field_70170_p
                     .func_184148_a(
                        (EntityPlayer)null,
                        entitylivingbaseIn.field_70169_q,
                        entitylivingbaseIn.field_70167_r,
                        entitylivingbaseIn.field_70166_s,
                        SRPSounds.ASSENDERMAN_PORTAL,
                        this.func_184176_by(),
                        0.3F,
                        1.0F
                     );
               }
            } else {
               this.tpPlayerName = null;
            }
         }

         if (!iattributeinstance.func_180374_a(ATTACKING_SPEED_BOOST)) {
            iattributeinstance.func_111121_a(ATTACKING_SPEED_BOOST);
         }
      }
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(SCREAMING, false);
      this.field_70180_af.func_187214_a(TARGET_ENTITY, 0);
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 60.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   public void playEndermanSound() {
      if (this.field_70173_aa >= this.lastCreepySound + 400) {
         this.lastCreepySound = this.field_70173_aa;
         if (!this.func_174814_R()) {
            this.field_70170_p
               .func_184134_a(
                  this.field_70165_t,
                  this.field_70163_u + this.func_70047_e(),
                  this.field_70161_v,
                  SoundEvents.field_187533_aW,
                  this.func_184176_by(),
                  2.5F,
                  1.0F,
                  false
               );
         }
      }
   }

   public void func_184206_a(DataParameter<?> key) {
      super.func_184206_a(key);
      if (TARGET_ENTITY.equals(key)) {
         this.targetedEntity = null;
      }
   }

   public void func_70108_f(Entity entityIn) {
      if (this.getTargetedEntity() == null || this.getTargetedEntity() != entityIn) {
         super.func_70108_f(entityIn);
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70170_p.field_72995_K) {
         for (int i = 0; i < 2; i++) {
            this.field_70170_p
               .func_175688_a(
                  EnumParticleTypes.PORTAL,
                  this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * this.field_70130_N,
                  this.field_70163_u + this.field_70146_Z.nextDouble() * this.field_70131_O - 0.25,
                  this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * this.field_70130_N,
                  (this.field_70146_Z.nextDouble() - 0.5) * 2.0,
                  -this.field_70146_Z.nextDouble(),
                  (this.field_70146_Z.nextDouble() - 0.5) * 2.0,
                  new int[0]
               );
         }
      } else {
         if (this.func_70638_az() != null
            && this.field_70173_aa % 20 == 0
            && this.func_70068_e(this.func_70638_az()) > 4.0
            && this.field_70146_Z.nextInt(SRPConfigMobs.infendermantelefreq) == 0) {
            this.teleportRandomly();
         }

         if (this.spotCool >= 0) {
            this.spotCool--;
         }

         if (this.toTeleCool >= 0) {
            this.toTeleCool--;
         }

         if (this.srpTicks == 10 && this.func_70644_a(SRPPotions.RAGE_E)) {
            this.spotCool = 0;
            this.toTeleCool = 0;
         }

         if (!this.canPull) {
            this.pulling--;
            if (this.pulling == 0) {
               this.canPull = true;
            }
         }

         if (this.func_70638_az() != null) {
            if (!this.func_70638_az().func_70089_S()) {
               this.func_70624_b(null);
               this.setTargetedEntity(0);
            } else if (this.func_70685_l(this.func_70638_az())
               && this.func_70068_e(this.func_70638_az()) > 0.0
               && this.canPull
               && this.getTargetedEntity() != null) {
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 1, false, false));
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 1, false, false));
               this.lookAt(this.getTargetedEntity());
               this.attackEntityAsMobMinimum(this.func_70638_az(), 0.02F);
               this.setParasiteStatus(3);
               this.pulling++;
               if (this.pulling > 200 || this.func_70068_e(this.func_70638_az()) > 9.0) {
                  this.setTargetedEntity(0);
                  this.canPull = false;
               }
            } else {
               this.setTargetedEntity(0);
            }
         } else {
            this.setTargetedEntity(0);
         }
      }

      this.field_70703_bu = false;
      if (this.getTargetedEntity() != null && this.func_70068_e(this.getTargetedEntity()) > 0.0) {
         EntityLivingBase target = this.getTargetedEntity();
         target.func_184210_p();
         double str = 0.3;
         double deltaX = this.field_70165_t - target.field_70165_t;
         double deltaY = this.field_70163_u - target.field_70163_u;
         double deltaZ = this.field_70161_v - target.field_70161_v;
         str = 0.13;
         double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
         if (distance == 0.0) {
            return;
         }

         deltaX /= distance;
         deltaY /= distance;
         deltaZ /= distance;
         target.field_70159_w += deltaX * str;
         target.field_70181_x += deltaY * str;
         target.field_70179_y += deltaZ * str;
      }
   }

   @Override
   protected void handleParasiteStatus() {
      int k = this.getParasiteStatus();
      if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2 || k == 3) {
         if (this.getAttackCooldownAni() != 0) {
            int i = this.getAttackCooldownAni() - 1;
            this.setAttackCooldownAni(i);
         }

         if (k == 1 || k == 2 || k == 3) {
            if (this.func_70638_az() != null) {
               if (!this.func_70638_az().func_70089_S()) {
                  this.func_70624_b(null);
                  this.setParasiteStatus(0);
               } else if (!this.canPull) {
                  this.setParasiteStatus(Math.min(k, 2));
               }
            } else {
               this.setParasiteStatus(0);
               this.func_70624_b(null);
            }
         }
      }
   }

   public void setTargetedEntity(int entityId) {
      if (this.canPull || entityId == 0) {
         this.pulling = 0;
         this.canPull = true;
         this.field_70180_af.func_187227_b(TARGET_ENTITY, entityId);
      }
   }

   public boolean hasTargetedEntity() {
      return !this.canPull ? false : (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
   }

   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.field_70170_p.field_72995_K) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY));
            if (entity instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)entity;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.func_70638_az();
      }
   }

   protected boolean teleportRandomly() {
      if (this.spotCool > 0) {
         return false;
      } else if (this.getTargetedEntity() != null) {
         return false;
      } else if (this.getTargetedEntity() != null) {
         return false;
      } else {
         double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
         double d1 = this.field_70163_u + (this.field_70146_Z.nextInt(64) - 32);
         double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
         return this.func_70638_az() != null && this.func_70638_az().func_70011_f(d0, d1, d2) < 10.0 ? false : this.teleportTo(d0, d1, d2);
      }
   }

   protected boolean teleportToEntity(Entity in, double dis) {
      double d1 = in.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * dis;
      double d2 = in.field_70163_u + (this.field_70146_Z.nextInt(16) - 8) * dis;
      double d3 = in.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * dis;
      return this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportToPos(double x, double y, double z, double dis) {
      double d1 = x + (this.field_70146_Z.nextDouble() - 0.5) * dis;
      double d2 = y + (this.field_70146_Z.nextInt(16) - 8) * dis;
      double d3 = z + (this.field_70146_Z.nextDouble() - 0.5) * dis;
      return this.func_70638_az() != null && this.func_70638_az().func_70011_f(d1, d2, d3) < 10.0 ? false : this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportEntityTo(EntityParasiteBase in, double x, double y, double z, double dis) {
      double d1 = x + (this.field_70146_Z.nextDouble() - 0.5) * dis;
      double d2 = y + (this.field_70146_Z.nextInt(16) - 8) * dis;
      double d3 = z + (this.field_70146_Z.nextDouble() - 0.5) * dis;
      return this.teleportTo(in, d1, d2, d3);
   }

   private boolean teleportTo(double x, double y, double z) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         boolean flag = this.func_184595_k(event.getTargetX(), event.getTargetY(), event.getTargetZ());
         if (flag) {
            this.field_70170_p
               .func_184148_a(
                  (EntityPlayer)null,
                  this.field_70169_q,
                  this.field_70167_r,
                  this.field_70166_s,
                  SRPSounds.ASSENDERMAN_PORTAL,
                  this.func_184176_by(),
                  1.0F,
                  1.0F
               );
            this.func_184185_a(SRPSounds.ASSENDERMAN_PORTAL, 1.0F, 1.0F);
         }

         return flag;
      }
   }

   public boolean func_184595_k(double x, double y, double z) {
      double d0 = this.field_70165_t;
      double d1 = this.field_70163_u;
      double d2 = this.field_70161_v;
      this.field_70165_t = x;
      this.field_70163_u = y;
      this.field_70161_v = z;
      boolean flag = false;
      BlockPos blockpos = new BlockPos(this);
      World world = this.field_70170_p;
      Random random = this.func_70681_au();
      if (world.func_175667_e(blockpos)) {
         boolean flag1 = false;

         while (!flag1 && blockpos.func_177956_o() > 0) {
            BlockPos blockpos1 = blockpos.func_177977_b();
            IBlockState iblockstate = world.func_180495_p(blockpos1);
            if (!iblockstate.func_185904_a().func_76230_c() && !(iblockstate.func_177230_c() instanceof BlockLiquid)) {
               this.field_70163_u--;
               blockpos = blockpos1;
            } else {
               flag1 = true;
            }
         }

         if (flag1) {
            this.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            if (world.func_184144_a(this, this.func_174813_aQ()).isEmpty() && !world.func_72953_d(this.func_174813_aQ())) {
               flag = true;
            }
         }
      }

      if (!flag) {
         this.func_70634_a(d0, d1, d2);
         return false;
      } else {
         int i = 128;

         for (int j = 0; j < 128; j++) {
            double d6 = j / 127.0;
            float f = (random.nextFloat() - 0.5F) * 0.2F;
            float f1 = (random.nextFloat() - 0.5F) * 0.2F;
            float f2 = (random.nextFloat() - 0.5F) * 0.2F;
            double d3 = d0 + (this.field_70165_t - d0) * d6 + (random.nextDouble() - 0.5) * this.field_70130_N * 2.0;
            double d4 = d1 + (this.field_70163_u - d1) * d6 + random.nextDouble() * this.field_70131_O;
            double d5 = d2 + (this.field_70161_v - d2) * d6 + (random.nextDouble() - 0.5) * this.field_70130_N * 2.0;
            world.func_175688_a(EnumParticleTypes.PORTAL, d3, d4, d5, f, f1, f2, new int[0]);
         }

         if (this instanceof EntityCreature) {
            this.func_70661_as().func_75499_g();
         }

         return true;
      }
   }

   private boolean teleportTo(EntityParasiteBase in, double x, double y, double z) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         boolean flag = in.func_184595_k(event.getTargetX(), event.getTargetY(), event.getTargetZ());
         if (flag) {
            in.field_70170_p
               .func_184148_a(
                  (EntityPlayer)null, in.field_70169_q, in.field_70167_r, in.field_70166_s, SoundEvents.field_187534_aX, in.func_184176_by(), 1.0F, 1.0F
               );
            in.func_184185_a(SoundEvents.field_187534_aX, 1.0F, 1.0F);
         }

         return flag;
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      this.spotCool = 0;
      if (this.func_180431_b(source)) {
         return false;
      } else if (source instanceof EntityDamageSourceIndirect) {
         for (int i = 0; i < 64; i++) {
            if (this.teleportRandomly()) {
               return true;
            }
         }

         return false;
      } else {
         boolean flag = super.func_70097_a(source, amount);
         if (source.func_76363_c() && this.field_70146_Z.nextInt(SRPConfigMobs.infendermantelefreq) == 0) {
            this.teleportRandomly();
         }

         return flag;
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
         if (!this.hasTargetedEntity()) {
            this.setTargetedEntity(entityIn.func_145782_y());
            ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 3, false, false));
         }

         if (this.field_70146_Z.nextDouble() < SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
            SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
         }

         if (this.field_70146_Z.nextInt(SRPConfigMobs.infendermantelefreq) == 0) {
            this.teleportRandomly();
         }
      }

      return flag;
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      this.tpPlayerName = null;
   }

   public boolean isScreaming() {
      return (Boolean)this.field_70180_af.func_187225_a(SCREAMING);
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.88F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.ASSENDERMAN_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.ASSENDERMAN_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.ASSENDERMAN_DEATH;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.field_187939_hm;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }
}
