package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIFlightAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityTendril;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfDragonEHead;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileDragonE;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketEntityBodyDead;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfDragonE extends EntityPInfected implements EntityCutomAttack, EntityBodyParts {
   protected static final DataParameter<Boolean> FLYING = EntityDataManager.func_187226_a(EntityInfDragonE.class, DataSerializers.field_187198_h);
   private int flying;
   private float aaa;
   private float sss;
   private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), Color.RED, Overlay.PROGRESS).func_186741_a(false);
   private EntityBody leftTendril;
   private EntityBody rightTendril;
   private EntityBody head;
   private float leftTendrilHealth;
   private float rightTendrilHealth;
   private float headlHealth;
   protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityInfDragonE.class, DataSerializers.field_187191_a);
   private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityInfDragonE.class, DataSerializers.field_187198_h);
   private int limit;
   private boolean skillFlame;
   private double tttX;
   private double tttY;
   private double tttZ;
   private double tttH;
   private double tttHH;

   public EntityInfDragonE(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.9F, 3.8F);
      this.field_70138_W = 1.0F;
      this.canModRender = 0;
      this.type = 14;
      this.killcount = -10.0;
      this.field_70714_bg.func_85156_a(this.folow);
      this.flying = 0;
      this.skillFlame = false;
      this.leftTendril = new EntityBody(this, 2.6F, 2.5F, 1.0F, 3.1F, 2.8F, 1, 1, true);
      this.rightTendril = new EntityBody(this, 2.6F, 2.5F, 1.0F, 3.1F, 2.8F, -1, 2, true);
      this.head = new EntityBody(this, 2.2F, 2.2F, 1.0F, 4.0F, 2.0F, -1, 3, false, 0.2F);
      this.leftTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.rightTendrilHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.headlHealth = (float)(this.func_110138_aP() * SRPConfig.tendrilHealth);
      this.field_70158_ak = true;
      this.field_70765_h = new EntityInfDragonE.AIMoveControl(this);
   }

   @Override
   public int getParasiteIDRegister() {
      return 64;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infdragoneCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatusAOE(this, 1.9, false, 8.0, 6.0));
      this.field_70714_bg.func_75776_a(6, new EntityInfDragonE.AIMoveRandom());
      this.field_70714_bg.func_75776_a(6, new EntityInfDragonE.AIFireballAttack(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIFlightAttack(this, 64.0));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 60, 32, 7, true, 1));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFDRAGONE_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFDRAGONE_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFDRAGONE_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFDRAGONE_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0);
   }

   @Override
   public void func_70636_d() {
      if (!this.func_175446_cd()) {
         super.func_70636_d();
         this.killcount = -10.0;
         if (this.headlHealth > 0.0F) {
            this.head.func_70071_h_();
         }

         if (this.leftTendrilHealth > 0.0F) {
            this.leftTendril.func_70071_h_();
         }

         if (this.rightTendrilHealth > 0.0F) {
            this.rightTendril.func_70071_h_();
         }

         if (!this.field_70170_p.field_72995_K && this.srpTicks == 10) {
            if ((
                  this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a
                     || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a
               )
               && this.getFlyingState()
               && this.field_70146_Z.nextInt(3) == 0) {
               this.field_70181_x = 0.5;
            }

            if (this.field_70146_Z.nextInt(9) == 0 && !this.getFlyingState()) {
               this.changeStateTo(true);
               return;
            }
         }

         if (this.flying >= 1) {
            this.flying++;
         }

         if (this.getFlyingState()) {
            this.aaa += 0.08F;
            this.sss += 0.782F;
            if (this.sss >= 24.0F) {
               this.func_184185_a(SoundEvents.field_187524_aN, 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F);
               this.sss = 0.0F;
            }

            if (this.field_70122_E && !this.field_70170_p.field_72995_K && this.flying > 40) {
               this.changeStateTo(false);
            }
         } else {
            this.aaa = 0.08F;
            this.sss = 0.0F;
         }
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.getFlyingState()) {
         this.func_189654_d(true);
      } else {
         this.func_189654_d(false);
      }
   }

   @Override
   protected void func_70619_bc() {
      super.func_70619_bc();
      this.bossInfo.func_186735_a(this.func_110143_aJ() / this.func_110138_aP());
   }

   public void func_96094_a(String name) {
      super.func_96094_a(name);
      this.bossInfo.func_186739_a(this.func_145748_c_());
   }

   public void func_184178_b(EntityPlayerMP player) {
   }

   public void func_184203_c(EntityPlayerMP player) {
      super.func_184203_c(player);
      this.bossInfo.func_186761_b(player);
   }

   public float getaaa() {
      return this.aaa;
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag) {
         if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityPlayerMP) {
            this.bossInfo.func_186760_a((EntityPlayerMP)source.func_76346_g());
         }

         if (this.field_70146_Z.nextInt(12) == 0 && !this.getFlyingState()) {
            this.changeStateTo(true);
         }
      }

      return flag;
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else {
         boolean flag = this.func_70097_a(source, amount);
         if (!flag) {
            return false;
         } else {
            if (this.leftTendril.getId() == id) {
               this.leftTendrilHealth -= amount;
               if (this.leftTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(7);
                  tendril.func_82149_j(this.leftTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.leftTendril);
                  this.field_70170_p.func_72960_a(this, (byte)11);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
                  this.changeStateTo(false);
               }
            } else if (this.rightTendril.getId() == id) {
               this.rightTendrilHealth -= amount;
               if (this.rightTendrilHealth <= 0.0F) {
                  EntityTendril tendril = new EntityTendril(this.field_70170_p);
                  tendril.setSkin(8);
                  tendril.func_82149_j(this.rightTendril);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.rightTendril);
                  this.field_70170_p.func_72960_a(this, (byte)22);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
                  this.changeStateTo(false);
               }
            } else if (this.head.getId() == id) {
               this.headlHealth -= amount;
               if (this.headlHealth <= 0.0F) {
                  EntityInfDragonEHead tendril = new EntityInfDragonEHead(this.field_70170_p);
                  tendril.setSkin(5);
                  tendril.func_82149_j(this.head);
                  this.field_70170_p.func_72838_d(tendril);
                  this.field_70170_p.func_72973_f(this.head);
                  this.field_70170_p.func_72960_a(this, (byte)33);
                  SRPMain.network.sendToAll(new SRPPacketEntityBodyDead(this.func_145782_y(), id));
               }
            }

            return flag;
         }
      }
   }

   @Override
   public void setBodyPartDead(int id) {
      if (this.leftTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      } else if (this.rightTendril.getId() == id) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      } else if (this.head.getId() == id) {
         this.field_70170_p.func_72973_f(this.head);
      }
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      if (this.leftTendril != null) {
         this.field_70170_p.func_72973_f(this.leftTendril);
      }

      if (this.rightTendril != null) {
         this.field_70170_p.func_72973_f(this.rightTendril);
      }

      super.func_70106_y();
   }

   public void func_180430_e(float distance, float damageMultiplier) {
   }

   public void changeStateTo(boolean fly) {
      if (this.limit < 1) {
         if (fly) {
            if (!this.getFlyingState()) {
               if (this.leftTendrilHealth <= 0.0F || this.rightTendrilHealth <= 0.0F) {
                  return;
               }

               this.field_70765_h = new EntityInfDragonE.AIMoveControl(this);
               this.setParasiteStatus(3);
               this.field_70180_af.func_187227_b(FLYING, true);
               this.field_70181_x = 0.5;
               this.aaa += 0.08F;
               this.flying = 1;
               this.sss = 19.85F;
            }
         } else if (this.getFlyingState()) {
            this.field_70765_h = new EntityMoveHelper(this);
            this.setParasiteStatus(0);
            this.field_70180_af.func_187227_b(FLYING, false);
            this.flying = 0;
            this.aaa = 0.0F;
            this.sss = 0.0F;
         }
      }
   }

   public float func_70047_e() {
      return 1.75F;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      return super.func_70652_k(entityIn);
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      return this.func_70652_k(entityIn);
   }

   @Override
   protected void selfExplode() {
   }

   @Override
   protected void spawnGore() {
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SoundEvents.field_187526_aP;
   }

   protected float func_70599_aP() {
      return 5.0F;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.MOBSILENCE;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.field_187823_fN;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }

   public boolean getFlyingState() {
      return (Boolean)this.field_70180_af.func_187225_a(FLYING);
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74776_a("parasiteleftTendril", this.leftTendrilHealth);
      compound.func_74776_a("parasiterightTendril", this.rightTendrilHealth);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("parasiteleftTendril", 99)) {
         this.leftTendrilHealth = compound.func_74760_g("parasiteleftTendril");
         if (this.leftTendrilHealth <= 0.0F) {
            this.field_70170_p.func_72960_a(this, (byte)11);
         }
      }

      if (compound.func_150297_b("parasiterightTendril", 99)) {
         this.rightTendrilHealth = compound.func_74760_g("parasiterightTendril");
         if (this.rightTendrilHealth <= 0.0F) {
            this.field_70170_p.func_72960_a(this, (byte)22);
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public float getLeft() {
      return this.leftTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   public float getRight() {
      return this.rightTendrilHealth;
   }

   @SideOnly(Side.CLIENT)
   public float getHead() {
      return this.headlHealth;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 11) {
         this.leftTendrilHealth = 0.0F;
      } else if (id == 22) {
         this.rightTendrilHealth = 0.0F;
      } else if (id == 33) {
         this.headlHealth = 0.0F;
      } else {
         super.func_70103_a(id);
      }
   }

   public void func_70091_d(MoverType type, double x, double y, double z) {
      super.func_70091_d(type, x, y, z);
      this.func_145775_I();
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(VEX_FLAGS, (byte)0);
      this.field_70180_af.func_187214_a(FLYING, true);
      this.field_70180_af.func_187214_a(ATTACKING, false);
   }

   private boolean getVexFlag(int mask) {
      int i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
      return (i & mask) != 0;
   }

   private void setVexFlag(int mask, boolean value) {
      int i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.field_70180_af.func_187227_b(VEX_FLAGS, (byte)(i & 0xFF));
   }

   @SideOnly(Side.CLIENT)
   public boolean isAttacking() {
      return (Boolean)this.field_70180_af.func_187225_a(ATTACKING);
   }

   public void setAttacking(boolean attacking) {
      this.field_70180_af.func_187227_b(ATTACKING, attacking);
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillFlame;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillFlame = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.flame();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void flame() {
      if (!this.getFlyingState() && !(this.headlHealth <= 0.0F)) {
         if (this.limit == 0) {
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if (entitylivingbase == null) {
               this.skillFlame = true;
               this.limit = 0;
               return;
            }

            this.tttX = entitylivingbase.field_70165_t;
            this.tttY = entitylivingbase.field_70163_u;
            this.tttZ = entitylivingbase.field_70161_v;
            this.tttH = entitylivingbase.func_174813_aQ().field_72338_b;
            this.tttHH = entitylivingbase.field_70131_O;
         }

         this.limit++;
         this.setParasiteStatus(10);
         this.func_70661_as().func_75492_a(this.tttX, this.tttY, this.tttZ, 0.0);
         this.resetIdleTime();
         if (this.field_70173_aa % 10 == 0) {
            double d1 = 4.0;
            Vec3d vec3d = this.func_70676_i(1.0F);
            double d2 = this.tttX - (this.field_70165_t + vec3d.field_72450_a * 4.0);
            double d3 = this.tttH + this.tttHH / 4.0 - (0.5 + this.field_70163_u + this.field_70131_O / 4.0F);
            double d4 = this.tttZ - (this.field_70161_v + vec3d.field_72449_c * 4.0);
            this.field_70170_p.func_180498_a((EntityPlayer)null, 1016, new BlockPos(this), 0);
            EntityProjectileDragonE entitylargefireball = new EntityProjectileDragonE(this.field_70170_p, this, d2, d3, d4);
            entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a * 4.0;
            entitylargefireball.field_70163_u = this.field_70163_u + this.field_70131_O / 2.0F + 0.5;
            entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c * 4.0;
            this.field_70170_p.func_72838_d(entitylargefireball);
            if (this.limit >= 60) {
               this.skillFlame = true;
               this.setParasiteStatus(0);
               this.limit = 0;
            }
         }
      } else {
         this.skillFlame = true;
         this.limit = 0;
      }
   }

   static class AIFireballAttack extends EntityAIBase {
      private final EntityInfDragonE parentEntity;
      public int attackTimer;

      public AIFireballAttack(EntityInfDragonE ghast) {
         this.parentEntity = ghast;
      }

      public boolean func_75250_a() {
         return this.parentEntity.func_70638_az() != null && this.parentEntity.getFlyingState() && this.parentEntity.headlHealth > 0.0F;
      }

      public void func_75249_e() {
         this.attackTimer = 0;
      }

      public void func_75251_c() {
         this.parentEntity.setAttacking(false);
      }

      public void func_75246_d() {
         EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
         double d0 = 64.0;
         if (entitylivingbase != null) {
            if (entitylivingbase.func_70068_e(this.parentEntity) < 4096.0 && this.parentEntity.func_70685_l(entitylivingbase)) {
               World world = this.parentEntity.field_70170_p;
               this.attackTimer++;
               if (this.parentEntity.func_70644_a(SRPPotions.RAGE_E)) {
                  this.attackTimer++;
               }

               this.parentEntity.resetIdleTime();
               if (this.attackTimer == 10) {
               }

               if (this.attackTimer == 20) {
                  double d1 = 4.0;
                  Vec3d vec3d = this.parentEntity.func_70676_i(1.0F);
                  double d2 = entitylivingbase.field_70165_t - (this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0);
                  double d3 = entitylivingbase.func_174813_aQ().field_72338_b
                     + entitylivingbase.field_70131_O / 2.0F
                     - (0.5 + this.parentEntity.field_70163_u + this.parentEntity.field_70131_O / 2.0F);
                  double d4 = entitylivingbase.field_70161_v - (this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0);
                  world.func_180498_a((EntityPlayer)null, 1016, new BlockPos(this.parentEntity), 0);
                  EntityProjectileDragonE entitylargefireball = new EntityProjectileDragonE(world, this.parentEntity, d2, d3, d4);
                  entitylargefireball.field_70165_t = this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0;
                  entitylargefireball.field_70163_u = this.parentEntity.field_70163_u + this.parentEntity.field_70131_O / 2.0F + 0.5;
                  entitylargefireball.field_70161_v = this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0;
                  world.func_72838_d(entitylargefireball);
                  this.parentEntity.field_70708_bq = 0;
                  this.attackTimer = -5;

                  for (int i = 0; i <= 2; i++) {
                     this.parentEntity
                        .field_70170_p
                        .func_175688_a(
                           EnumParticleTypes.FLAME,
                           this.parentEntity.field_70165_t + vec3d.field_72450_a * 4.0,
                           this.parentEntity.field_70163_u + this.parentEntity.field_70131_O / 2.0F + 0.5,
                           this.parentEntity.field_70161_v + vec3d.field_72449_c * 4.0,
                           0.0,
                           -1.0,
                           0.0,
                           new int[0]
                        );
                  }
               }
            } else if (this.attackTimer > 0) {
               this.attackTimer--;
            }

            this.parentEntity.setAttacking(this.attackTimer > 10);
         }
      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityInfDragonE vex) {
         super(vex);
      }

      public void func_75641_c() {
         if (this.field_188491_h == Action.MOVE_TO) {
            double d0 = this.field_75646_b - EntityInfDragonE.this.field_70165_t;
            double d1 = this.field_75647_c - EntityInfDragonE.this.field_70163_u;
            double d2 = this.field_75644_d - EntityInfDragonE.this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (d3 < EntityInfDragonE.this.func_174813_aQ().func_72320_b()) {
               this.field_188491_h = Action.WAIT;
               EntityInfDragonE.this.field_70159_w *= 0.5;
               EntityInfDragonE.this.field_70181_x *= 0.5;
               EntityInfDragonE.this.field_70179_y *= 0.5;
            } else {
               EntityInfDragonE.this.field_70159_w = EntityInfDragonE.this.field_70159_w + d0 / d3 * 0.05 * this.field_75645_e;
               EntityInfDragonE.this.field_70181_x = EntityInfDragonE.this.field_70181_x + d1 / d3 * 0.05 * this.field_75645_e;
               EntityInfDragonE.this.field_70179_y = EntityInfDragonE.this.field_70179_y + d2 / d3 * 0.05 * this.field_75645_e;
               if (EntityInfDragonE.this.func_70638_az() == null) {
                  EntityInfDragonE.this.field_70177_z = -(
                        (float)MathHelper.func_181159_b(EntityInfDragonE.this.field_70159_w, EntityInfDragonE.this.field_70179_y)
                     )
                     * (180.0F / (float)Math.PI);
                  EntityInfDragonE.this.field_70761_aq = EntityInfDragonE.this.field_70177_z;
               } else {
                  double d4 = EntityInfDragonE.this.func_70638_az().field_70165_t - EntityInfDragonE.this.field_70165_t;
                  double d5 = EntityInfDragonE.this.func_70638_az().field_70161_v - EntityInfDragonE.this.field_70161_v;
                  EntityInfDragonE.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * (180.0F / (float)Math.PI);
                  EntityInfDragonE.this.field_70761_aq = EntityInfDragonE.this.field_70177_z;
               }
            }
         }
      }
   }

   class AIMoveRandom extends EntityAIBase {
      public AIMoveRandom() {
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         return EntityInfDragonE.this.field_70146_Z.nextInt(7) == 0 && EntityInfDragonE.this.getFlyingState();
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75246_d() {
         BlockPos blockpos = new BlockPos(EntityInfDragonE.this);
         byte flag = 1;
         double speed = 0.5;
         if (EntityInfDragonE.this.func_70638_az() != null) {
            if (EntityInfDragonE.this.func_70068_e(EntityInfDragonE.this.func_70638_az()) > 100.0) {
               blockpos = new BlockPos(EntityInfDragonE.this.func_70638_az());
               flag = 2;
            } else if (EntityInfDragonE.this.func_70068_e(EntityInfDragonE.this.func_70638_az()) < 36.0) {
               blockpos = new BlockPos(EntityInfDragonE.this.func_70638_az());
               flag = 3;
               speed += 0.25;
            }
         }

         for (int i = 0; i < 3; i++) {
            BlockPos blockpos1 = blockpos.func_177982_a(
               EntityInfDragonE.this.field_70146_Z.nextInt(15) - 7,
               EntityInfDragonE.this.field_70146_Z.nextInt(11) - 5,
               EntityInfDragonE.this.field_70146_Z.nextInt(15) - 7
            );
            if (flag == 2) {
               blockpos1 = blockpos.func_177982_a(
                  EntityInfDragonE.this.field_70146_Z.nextInt(6) - 2,
                  EntityInfDragonE.this.field_70146_Z.nextInt(7) - 2,
                  EntityInfDragonE.this.field_70146_Z.nextInt(6) - 2
               );
            } else if (flag == 3) {
               blockpos1 = blockpos.func_177982_a(
                  EntityInfDragonE.this.field_70146_Z.nextInt(4) + 3,
                  EntityInfDragonE.this.field_70146_Z.nextInt(5) + 4,
                  EntityInfDragonE.this.field_70146_Z.nextInt(4) + 3
               );
            }

            if (EntityInfDragonE.this.field_70170_p.func_175623_d(blockpos1)) {
               EntityInfDragonE.this.field_70765_h
                  .func_75642_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, speed);
               if (EntityInfDragonE.this.func_70638_az() == null) {
                  EntityInfDragonE.this.func_70671_ap()
                     .func_75650_a(blockpos1.func_177958_n() + 0.5, blockpos1.func_177956_o() + 0.5, blockpos1.func_177952_p() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }
      }
   }
}
