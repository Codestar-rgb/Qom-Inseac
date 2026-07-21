package com.dhanantry.scapeandrunparasites.entity.monster.infected.special;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAssimara;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySpeCow extends EntityPAssimara implements IRangedAttackMob {
   int vomit;

   public EntitySpeCow(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.9F, 1.4F);
      this.canModRender = 1;
      this.type = 11;
      this.fuseTime = 40;
   }

   @Override
   public int getIDSpawn() {
      return 13;
   }

   @Override
   public int getParasiteIDRegister() {
      return 322;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infcowCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 1, 16));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 3.0F));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.0, 200, 7.0F, false));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.MARCOW_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.MARCOW_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2F);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.MARCOW_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.MARCOW_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.assimaraFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70170_p.field_72995_K && this.vomit > 0) {
         this.vomit--;

         for (int i = 0; i < 6; i++) {
            Vec3d vec3d = this.func_70676_i(1.0F);
            double bon = 1.2;
            double offsetX = this.field_70165_t + vec3d.field_72450_a * bon;
            double offsetY = this.field_70163_u + this.func_70047_e() - 0.2;
            double offsetZ = this.field_70161_v + vec3d.field_72449_c * bon;
            double motionX = -MathHelper.func_76126_a(this.field_70177_z * (float) Math.PI / 180.0F) * 0.2;
            double motionZ = MathHelper.func_76134_b(this.field_70177_z * (float) Math.PI / 180.0F) * 0.2;
            double motionY = 0.01 + this.field_70146_Z.nextDouble() * 0.1;
            double spreadFactor = 0.25;
            motionX += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor;
            motionZ += (this.field_70146_Z.nextDouble() - 0.5) * spreadFactor;
            this.spawnParticles(SRPEnumParticle.GCLOUD, 123, 0, 196, offsetX, offsetY, offsetZ, motionX, motionY, motionZ);
         }
      }
   }

   @Override
   protected void selfExplode() {
      super.selfExplode();
      ParasiteSummon.spawnM(this, new String[]{SRPConfigMobs.infcowmob}, 0, false, this.func_95999_t());
   }

   public float func_70047_e() {
      return 1.3F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDCOW_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.INFECTEDCOW_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.INFECTEDCOW_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SoundEvents.field_187566_ao, 0.15F, 1.0F);
   }

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
      this.field_70170_p.func_72960_a(this, (byte)100);
      Vec3d vec3d = this.func_70676_i(1.0F);
      double bon = 4.5;
      EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(
         this.field_70170_p, this.field_70165_t + vec3d.field_72450_a * bon, this.field_70163_u, this.field_70161_v + vec3d.field_72449_c * bon
      );
      entityareaeffectcloud.func_184483_a(3.0F);
      entityareaeffectcloud.func_184486_b(100);
      entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / entityareaeffectcloud.func_184489_o());
      entityareaeffectcloud.func_184496_a(new PotionEffect(SRPPotions.VOMIT_E, 300, 0, false, true));
      entityareaeffectcloud.func_184496_a(new PotionEffect(SRPPotions.VIRA_E, 300, 20, false, true));
      entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76437_t, 300, 20, false, true));
      entityareaeffectcloud.func_184496_a(new PotionEffect(MobEffects.field_76438_s, 300, 20, false, true));
      entityareaeffectcloud.func_184496_a(new PotionEffect(SRPPotions.CORRO_E, 300, 20, false, true));
      this.field_70170_p.func_72838_d(entityareaeffectcloud);
      this.lookAt(target);
      this.setWait(60);
   }

   public void func_184724_a(boolean swingingArms) {
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 100) {
         this.vomit = 40;
      } else {
         super.func_70103_a(id);
      }
   }
}
