package com.dhanantry.scapeandrunparasites.entity.monster.ancient;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAncient;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;

public class EntityTerla extends EntityPAncient implements EntityBodyParts, IRangedAttackMob, EntityCutomAttack {
   private EntityBody head;
   private EntityBody middle;
   private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), Color.RED, Overlay.PROGRESS).func_186741_a(false);

   public EntityTerla(World worldIn) {
      super(worldIn);
      this.func_70105_a(2.4F, 2.9F);
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70158_ak = true;
      this.type = 63;
      this.borderOrb = -1;
      this.head = new EntityBody(this, 2.4F, 7.5F, 1.0F, -3.0F, 0.0F, -1, 1, false, 0.2F);
      this.middle = new EntityBody(this, 2.4F, 4.5F, 1.0F, 0.0F, 3.0F, 1, 2, false, 0.2F);
   }

   @Override
   public int getParasiteIDRegister() {
      return 20;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 10.0F));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackMeleeStatusAOE(this, 1.0, false, 100.0, 5.0));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.0, 80, 40.0F, false));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.TERLA_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.TERLA_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23F);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.TERLA_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.ancientFollow);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.head.func_70071_h_();
      this.middle.func_70071_h_();
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

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
      Vec3d vec3d = this.func_70676_i(1.0F);
      double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
      double d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 2.0F - (0.5 + this.field_70163_u + this.field_70131_O / 2.0F);
      double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
      this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0F, 1.0F);
      d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 4.0F - (1.0 + this.field_70163_u + this.field_70131_O / 2.0F);
      EntityProjectileHomming entitylargefireball = new EntityProjectileHomming(this.field_70170_p, this, target, SRPAttributes.ORONCO_ATTACK_DAMAGE);
      entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
      entitylargefireball.field_70163_u = this.field_70163_u + this.func_70047_e() - 0.2;
      entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
      this.field_70170_p.func_72838_d(entitylargefireball);
   }

   @Override
   public boolean attackEntityAsMobAOE(Entity entityIn) {
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(
            entityIn.field_70165_t,
            entityIn.field_70163_u,
            entityIn.field_70161_v,
            entityIn.field_70165_t + 1.0,
            entityIn.field_70163_u + 1.0,
            entityIn.field_70161_v + 1.0
         )
         .func_72314_b(5.0, 2.0, 5.0);
      List<EntityLivingBase> moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
      if (moblist.size() > 4) {
         axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_72314_b(5.0, 3.0, 5.0);
         moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
         float luck = (float)(SRPAttributes.TERLA_ATTACK_DAMAGE * 2.0);

         for (EntityLivingBase mob : moblist) {
            if (mob != null && !(mob instanceof EntityParasiteBase) && mob != this) {
               EntityDamage damage = new EntityDamage(
                  this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, 0.0F, this, luck, false, 3.0F
               );
               this.field_70170_p.func_72838_d(damage);
            }
         }

         return true;
      } else {
         for (EntityLivingBase mobx : moblist) {
            if (mobx != null && !(mobx instanceof EntityParasiteBase)) {
               this.func_70652_k(mobx);
            }
         }

         return !moblist.isEmpty();
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag && source.func_76346_g() instanceof EntityPlayerMP) {
         this.bossInfo.func_186760_a((EntityPlayerMP)source.func_76346_g());
      }

      return flag;
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      return this.func_70097_a(source, amount);
   }

   @Override
   public void setBodyPartDead(int id) {
   }

   public int func_184649_cE() {
      return 3;
   }

   @Override
   public boolean func_70687_e(PotionEffect potioneffectIn) {
      return potioneffectIn.func_188419_a() == MobEffects.field_76436_u ? false : super.func_70687_e(potioneffectIn);
   }

   public float func_70047_e() {
      return 4.7F;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.MOBSILENCE;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
      }

      return flag;
   }

   protected float func_70599_aP() {
      return 5.0F;
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      if (this.middle != null) {
         this.field_70170_p.func_72973_f(this.middle);
      }

      super.func_70106_y();
   }

   public void func_184724_a(boolean swingingArms) {
   }
}
