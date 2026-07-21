package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvade;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityAta;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPFeral extends EntityParasiteBase implements EntityCanSpawn {
   protected float regen;
   protected int regenEff;
   protected int regenUse;

   public EntityPFeral(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4, new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, true, false, null, SRPConfig.feralSneakPen, SRPConfig.feralInviPen)
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  true,
                  false,
                  entity -> !(entity instanceof EntityWaterMob)
                     && !(entity instanceof EntityAnimal)
                     && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite),
                  SRPConfig.feralSneakPen,
                  SRPConfig.feralInviPen
               )
            );
      }

      this.field_70714_bg.func_75776_a(2, new EntityAIEvade(this, 30, 5, 3.0));
      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70728_aV = SRPAttributes.XP_FERAL;
      this.damageCap = SRPConfig.feralCap;
      this.canD = SRPConfig.feraldespawn;
      this.MiniDamage = SRPConfig.feralMinDamage;
      this.oneMindDeathValue = SRPConfig.feralOneMindDeathV;
      this.foodSteal = SRPConfig.feralFoodSteal;
      this.regen = SRPConfig.feralRegen * SRPConfig.globalHealthMultiplier;
      this.regenEff = 10;
      this.cothSpread = SRPConfigSystems.cothFeral;
      this.valueEvDeath = SRPConfig.feralLoosingEPValue;
      this.attackSpeedT = 14;
      this.setScentHPMultiplier(1.5F);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70170_p.field_72995_K) {
         if (this.field_70146_Z.nextInt(25) == 0) {
            for (int i = 0; i <= 1; i++) {
               this.spawnParticlesGoreBox(SRPEnumParticle.GSPLASH, 0, -1, -1, 0.1, 0.0);
            }
         }
      } else if (this.srpTicks == 10
         && !this.field_70128_L
         && this.func_110143_aJ() > 0.0F
         && !this.func_70027_ad()
         && this.regen > 0.0F
         && this.killcount > 1.0
         && this.func_110143_aJ() < this.func_110138_aP()) {
         this.func_70606_j(this.func_110143_aJ() + this.regen);
         this.regenUse--;
         if (this.regenUse <= 0) {
            this.killcount--;
            this.regenUse = this.regenEff;
         }
      }
   }

   @Override
   protected void fearPlayer(EntityLivingBase player) {
   }

   protected void fearPlayer(EntityLivingBase player, float damageDealt) {
      if (player != null && !player.field_70170_p.field_72995_K) {
         if (!(damageDealt <= 8.0F)) {
            int level = 1 + Math.max(0, (int)Math.floor((damageDealt - 8.0F) / 4.0F));
            int cap = 3;
            level = Math.min(level, cap);
            int duration = 300 + 40 * (level - 1);
            duration = MathHelper.func_76125_a(duration, 200, 500);
            int amplifier = level - 1;
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, duration, amplifier, false, true));
         }
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (this.field_70170_p.field_72995_K) {
         return super.func_70097_a(source, amount);
      } else {
         if (source.func_76346_g() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase)source.func_76346_g();
            if (attacker.func_70644_a(SRPPotions.KILLFER_E)) {
               int amp = Objects.requireNonNull(attacker.func_70660_b(SRPPotions.KILLFER_E)).func_76458_c();
               float totalRed = MathHelper.func_76131_a(SRPConfigSystems.parasiteKillingReduction * (amp + 1.0F), 0.0F, 0.95F);
               float reduced = amount * (1.0F - totalRed);
               return super.func_70097_a(source, Math.max(0.0F, reduced));
            }
         }

         boolean tookDamage = super.func_70097_a(source, amount);
         if (tookDamage && source.func_76346_g() instanceof EntityLivingBase && this.field_70146_Z.nextDouble() < SRPConfig.feralMult) {
            double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
            double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
            double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
            double d3 = d0 - this.field_70165_t;
            double d4 = d1 - this.field_70163_u;
            double d5 = d2 - this.field_70161_v;
            double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
            if (d6 < 1.0E-4) {
               d6 = 1.0E-4;
            }

            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.8 / (d6 / 4.0 + 0.1);
            d7 *= this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3F;
            d3 = d3 * d7 * 0.5;
            d4 = d4 * d7 * 1.0;
            d5 = d5 * d7 * 0.5;
            EntityGore bomb = new EntityGore(this.field_70170_p);
            bomb.setType((byte)111);
            bomb.entityName = EntityList.func_191301_a(this) != null ? Objects.requireNonNull(EntityList.func_191301_a(this)).toString() : "unknown";
            bomb.func_82149_j(this);
            bomb.setMotion(d3, d4, d5, 0.2, 0.5);
            this.field_70170_p.func_72838_d(bomb);
         }

         return tookDamage;
      }
   }

   @Override
   protected void attackEntityFromEffects(int range, int count) {
      this.particleStatus((byte)51);
      double i1 = MathHelper.func_76128_c(this.field_70163_u + 0.1);
      double l1 = this.field_70165_t;
      double i2 = this.field_70161_v;
      int counttt = 0;

      for (int k2 = -1 * range; k2 <= range && SRPConfig.paraGore; k2++) {
         for (int l2 = -1 * range; l2 <= range; l2++) {
            double i3 = l1 + k2;
            double l = i2 + l2;
            BlockPos blockpos = new BlockPos(i3, i1, l);
            Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
            Block blockDown = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
            if (block == Blocks.field_150350_a
               && blockDown != Blocks.field_150350_a
               && this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_185913_b()
               && blockDown != SRPBlocks.InfestedStain
               && this.field_70170_p.field_73012_v.nextInt(4) == 0) {
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
               if (++counttt >= count) {
                  return;
               }
            }
         }
      }
   }

   @Override
   protected void attackEntityFromCap(int go) {
      for (int i = 0; i < go && SRPConfig.paraGore; i++) {
         double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
         double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
         double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
         double d3 = d0 - this.field_70165_t;
         double d4 = d1 - this.field_70163_u;
         double d5 = d2 - this.field_70161_v;
         double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
         d3 /= d6;
         d4 /= d6;
         d5 /= d6;
         double d7 = 0.8 / (d6 / 4.0 + 0.1);
         d7 *= this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3F;
         d3 *= d7;
         d4 = d4 * d7 * 2.0;
         d5 *= d7;
         EntityGore bomb = new EntityGore(this.field_70170_p);
         bomb.setType((byte)1);
         bomb.func_82149_j(this);
         bomb.setMotion(d3, d4, d5, 0.2, 0.8);
         this.field_70170_p.func_72838_d(bomb);
      }
   }

   @Override
   protected void spawnGore() {
      this.attackEntityFromEffects(2, 100);
      List<Entity> serverList = this.field_70170_p.field_72996_f;
      int count = 0;

      for (Entity entity : serverList) {
         if (entity instanceof EntityParasiteBase) {
            count++;
         }
      }

      if (count < SRPConfig.worldMobCap) {
         EntityAta ata = new EntityAta(this.field_70170_p);
         ata.func_82149_j(this);
         this.field_70170_p.func_72838_d(ata);
      }

      if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
         && (
            this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
               || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
         )) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
         EntityRemain nnn = new EntityRemain(this.field_70170_p);
         nnn.func_70012_b(
            this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p() + 0.5, 0.0F, 0.0F
         );
         nnn.setParasite(Objects.requireNonNull(EntityList.func_191301_a(this)).toString());
         nnn.setSkin((byte)this.getSkin());
         nnn.setGoal(20 * SRPConfig.feralRemainValue);
         this.field_70170_p.func_72838_d(nnn);
      }

      this.attackEntityFromCap(3);
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      if (!(entityIn instanceof EntityLivingBase)) {
         return super.func_70652_k(entityIn);
      } else {
         EntityLivingBase target = (EntityLivingBase)entityIn;
         float before = target.func_110143_aJ() + target.func_110139_bj();
         boolean hit = super.func_70652_k(entityIn);
         if (hit && !this.field_70170_p.field_72995_K) {
            float after = target.func_110143_aJ() + target.func_110139_bj();
            float dealt = Math.max(0.0F, before - after);
            if (dealt > 0.0F) {
               this.fearPlayer(target, dealt);
            }
         }

         return hit;
      }
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      if (SRPConfigSystems.disloSameVersionDyeing && this.disloNumberTwentytwo) {
         boolean flag = super.onDeathDislo(cause);
         if (!flag && cause.func_76346_g() instanceof EntityLivingBase) {
            int count = SRPSaveData.get(this.field_70170_p, 31).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 22);
            EntityLivingBase target = (EntityLivingBase)cause.func_76346_g();
            if (count >= 1) {
               target.func_184596_c(SRPPotions.KILLPRI_E);
               target.func_184596_c(SRPPotions.KILLADA_E);
               target.func_184596_c(SRPPotions.KILLPUR_E);
               target.func_184596_c(SRPPotions.KILLCRU_E);
               target.func_184596_c(SRPPotions.KILLNEX_E);
               SRPPotions.applyStackPotion(SRPPotions.KILLFER_E, target, 1200, count);
            }
         }

         return flag;
      } else {
         return super.onDeathDislo(cause);
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void spawnEffectsGore() {
      for (int i = 0; i <= 100; i++) {
         if (i % 5 == 0) {
            this.spawnParticlesGore(SRPEnumParticle.GSPLASH, 0, -1, -1);
         }
      }

      for (int ix = 0; ix <= 20; ix++) {
         if (ix % 5 == 0) {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
         }

         if (ix % 5 == 0) {
            this.spawnParticles(SRPEnumParticle.GSPLASH, 0, -1, -1);
         }
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
   }
}
