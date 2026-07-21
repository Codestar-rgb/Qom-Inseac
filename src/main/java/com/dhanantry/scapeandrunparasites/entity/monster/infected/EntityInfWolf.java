package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanMelt;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfWolf extends EntityPInfected implements EntityCanMelt {
   private static final DataParameter<Boolean> TAMED_WOLF_TEXTURE = EntityDataManager.func_187226_a(EntityInfWolf.class, DataSerializers.field_187198_h);
   private static final DataParameter<Float> HEIGH = EntityDataManager.func_187226_a(EntityInfWolf.class, DataSerializers.field_187193_c);
   private static final DataParameter<Boolean> MELTING = EntityDataManager.func_187226_a(EntityInfWolf.class, DataSerializers.field_187198_h);
   private float aSize;
   private int sound;

   public EntityInfWolf(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 0.85F);
      this.aSize = 1.0F;
      this.canModRender = 1;
      this.type = 13;
      this.fuseTime = 40;
      this.thisMelting = true;
      this.attackSpeedT = 10;
   }

   @Override
   public int getParasiteIDRegister() {
      return 15;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infwolfCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, 0.0));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(4, new EntityAILeapAtTarget(this, 0.4F));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 1, 16));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.INFWOLF_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.INFWOLF_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3F);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.INFWOLF_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.INFWOLF_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(HEIGH, 0.0F);
      this.field_70180_af.func_187214_a(MELTING, false);
      this.field_70180_af.func_187214_a(TAMED_WOLF_TEXTURE, false);
   }

   public boolean hasTamedWolfTexture() {
      return (Boolean)this.field_70180_af.func_187225_a(TAMED_WOLF_TEXTURE);
   }

   public void setTamedWolfTexture(boolean value) {
      this.field_70180_af.func_187227_b(TAMED_WOLF_TEXTURE, value);
   }

   public boolean func_184652_a(EntityPlayer player) {
      return !this.func_110167_bD();
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74757_a("TamedWolfTexture", this.hasTamedWolfTexture());
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      this.setTamedWolfTexture(compound.func_74767_n("TamedWolfTexture"));
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      livingdata = super.func_180482_a(difficulty, livingdata);
      if (!this.field_70170_p.field_72995_K) {
         this.setTamedWolfTexture(this.field_70146_Z.nextInt(100) == 0);
      }

      return livingdata;
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.melting();
   }

   @Override
   protected void func_70609_aI() {
      super.func_70609_aI();
      if (this.getTHeigh() < 1.57F && !this.field_70170_p.field_72995_K) {
         this.setTHeigh(0.17F);
      }

      if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K && this.field_70146_Z.nextDouble() <= SRPAttributes.INFWOLF_HEADCHANCE) {
         ParasiteSummon.spawnM(this, new String[]{"srparasites:sim_wolfhead;1;1"}, 0, false, this.func_95999_t());
      }
   }

   @Override
   public void melt() {
      this.setWait(1000);
      this.field_70180_af.func_187227_b(HEIGH, 0.85F);
      this.field_70180_af.func_187227_b(MELTING, true);
   }

   @Override
   public void melting() {
      if (this.isMelting()) {
         if (this.sound % 20 == 0) {
            this.func_184185_a(SRPSounds.INFECTED_MELT, 1.0F, 1.0F);
         }

         this.sound++;
         if (this.getTHeigh() > 0.7) {
            this.setaSize(-0.005F);
            this.setTHeigh(-0.01F);
            this.func_70105_a(this.field_70130_N, this.getTHeigh());
         }

         if (!this.field_70170_p.field_72995_K) {
            if (this.getTHeigh() <= 0.7 || this.sound >= 19) {
               EntityLesh out = new EntityLesh(this.field_70170_p);
               out.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
               if (this.func_95999_t() != null) {
                  out.func_96094_a(this.func_95999_t());
               }

               this.func_70106_y();
               this.field_70170_p.func_72838_d(out);
               out.setLegs(SRPAttributes.INFWOLF_V, false);
            }
         } else {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 106, 0);
            this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
         }
      }
   }

   @Override
   public boolean isMelting() {
      return (Boolean)this.field_70180_af.func_187225_a(MELTING);
   }

   @Override
   public float getTHeigh() {
      return (Float)this.field_70180_af.func_187225_a(HEIGH);
   }

   @Override
   public void setTHeigh(float in) {
      in += this.getTHeigh();
      this.field_70180_af.func_187227_b(HEIGH, in);
   }

   @Override
   public float getaSize() {
      return this.aSize;
   }

   @Override
   public void setaSize(float in) {
      this.aSize += in;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public float getSelfeFlashIntensity2() {
      return this.aSize;
   }

   @Override
   protected void selfExplode() {
      super.selfExplode();
      ParasiteSummon.spawnM(this, new String[]{SRPConfigMobs.infwolfmob}, 0, false, this.func_95999_t());
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.8F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDWOLF_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.INFECTEDWOLF_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.INFECTEDWOLF_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SoundEvents.field_187869_gK, 0.15F, 1.0F);
   }
}
