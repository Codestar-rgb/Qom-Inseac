package com.dhanantry.scapeandrunparasites.entity.monster.primitive;

import com.dhanantry.scapeandrunparasites.entity.EntityDamage;
import com.dhanantry.scapeandrunparasites.entity.EntityHitbox;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIDiveBomb;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvade;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPrimitive;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityNoglaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Locale;
import javax.annotation.Nonnull;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityNogla extends EntityPPrimitive {
   private EntityHitbox head;
   private static final byte STATUS_RICARDO_BURST = 77;
   private boolean ricardoBaseCaptured = false;
   private double ricardoBaseMaxHealth;
   private double ricardoBaseArmor;
   private double ricardoBaseToughness;
   private static final DataParameter<Boolean> RICARDO_BALD = EntityDataManager.func_187226_a(EntityNogla.class, DataSerializers.field_187198_h);
   private int attacking;
   private double targetX;
   private double targetY;
   private double targetZ;
   private boolean skillCharge;

   public EntityNogla(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.9F, 2.6F);
      this.field_70138_W = 1.0F;
      this.skillCharge = false;
      this.head = new EntityHitbox(this, 1.6F, 0.9F, 2.2F, 0.7F, 0.8F, 1.25F);
      this.hitboxes = new EntityHitbox[]{this.head};
   }

   @Override
   public int getParasiteIDRegister() {
      return 10;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.095));
      this.field_70714_bg.func_75776_a(1, new EntityAIDiveBomb(this, 1200, 60, 3.8, 3.0F));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 40, 32, 8, true, 1));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 2, 16));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvade(this, 55, 10, 4.0));
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(RICARDO_BALD, false);
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.NOGLA_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.NOGLA_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.31234);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.NOGLA_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.NOGLA_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0 && this.killcount > SRPConfig.adaptedKills && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this, new EntityNoglaAdapted(this.field_70170_p), true, true);
      }

      if (!this.field_70170_p.field_72995_K && this.isRicardoBald() && this.field_70173_aa % 20 == 0) {
         this.applyPermanentRicardoRage();
      }

      if (this.field_70170_p.field_72995_K && this.isRicardoVariant() && (this.field_70173_aa & 3) == 0) {
         for (int i = 0; i < 3; i++) {
            double x = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * this.field_70130_N * 1.6;
            double y = this.field_70163_u + this.field_70146_Z.nextDouble() * (this.field_70131_O * 0.9);
            double z = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * this.field_70130_N * 1.6;
            float r = 1.0F;
            float g = 0.25F;
            float b = 0.75F;
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB_AMBIENT, x, y, z, r, g, b, new int[0]);
         }
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74757_a("RicardoBald", (Boolean)this.field_70180_af.func_187225_a(RICARDO_BALD));
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      this.field_70180_af.func_187227_b(RICARDO_BALD, compound.func_74767_n("RicardoBald"));
      if (!this.field_70170_p.field_72995_K && this.isRicardoBald()) {
         this.applyPermanentRicardoRage();
      }
   }

   @Override
   protected void func_82167_n(Entity entityIn) {
      super.func_82167_n(entityIn);
      if (!this.field_70170_p.field_72995_K) {
         if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
         }
      }
   }

   public float func_70047_e() {
      return 2.4F;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      if (!this.field_70170_p.field_72995_K) {
         if (this.isRicardoVariant()) {
            this.func_70099_a(new ItemStack(SRPItems.bookofvengeance, 1), 0.0F);
         }

         if (!SRPConfigWorld.coloniesActivated && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else if (ParasiteEventWorld.numberofColonies(this.field_70170_p) < 1 && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else {
            ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
            ParasiteEventEntity.spawnNext(this, new EntityLesh(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
         if (entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76436_u, 100));
            switch (this.getSkin()) {
               case 5:
                  SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 40, 0);
                  break;
               case 6:
                  SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 40, 0);
            }
         }

         if (!this.field_70170_p.field_72995_K && entityIn instanceof EntitySlime && this.func_70681_au().nextFloat() < 0.1F) {
            double dx = entityIn.field_70165_t - this.field_70165_t;
            double dz = entityIn.field_70161_v - this.field_70161_v;
            double len = Math.sqrt(dx * dx + dz * dz);
            if (len < 1.0E-4) {
               dx = this.func_70681_au().nextDouble() - 0.5;
               dz = this.func_70681_au().nextDouble() - 0.5;
               len = Math.sqrt(dx * dx + dz * dz);
            }

            dx /= len;
            dz /= len;
            double horizontal = 4.75;
            double yBoost = 1.2;
            entityIn.func_70024_g(dx * horizontal, yBoost, dz * horizontal);
            entityIn.field_70133_I = true;
         }

         if (this.isRicardoVariant() && !this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72885_a(this, entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, 1.8F, false, false);
         }
      }

      return flag;
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      this.particleStatus((byte)5);
      if (!this.field_70170_p.field_72995_K && this.killcount > SRPConfig.adaptedKills && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this, new EntityNoglaAdapted(this.field_70170_p), true, true);
      }
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.NOGLA_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.NOGLA_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.NOGLA_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.noglaOrbEffects, mobs);
      }

      return flag;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SRPSounds.MONSTER_STEP, 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance
         || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant
         || this.canChangeVariant) {
         switch (this.field_70146_Z.nextInt(4)) {
            case 0:
               this.setSkin(5);
               break;
            case 1:
               this.setSkin(6);
               break;
            case 2:
               this.setSkin(1);
               this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.NOGLA_HEALTH * 0.5);
               this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.NOGLA_ATTACK_DAMAGE * 1.5);
               this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
               break;
            case 3:
               this.setSkin(7);
         }
      }

      if (!this.field_70170_p.field_72995_K) {
         this.updateRicardoAttributes();
      }

      return floo;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      switch (id) {
         case 100:
            for (int i = 0; i <= 1; i++) {
               this.spawnParticles(EnumParticleTypes.FLAME);
            }
            break;
         default:
            super.func_70103_a(id);
      }
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 1:
            return this.skillCharge;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 1:
            this.skillCharge = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   public boolean isRicardoVariant() {
      if (!SRPConfigMobs.noglaRicardoVariantEnabled) {
         return false;
      } else if (!this.func_145818_k_()) {
         return false;
      } else {
         String raw = TextFormatting.func_110646_a(this.func_95999_t());
         return raw == null ? false : raw.trim().toLowerCase(Locale.ROOT).equals("ricardo");
      }
   }

   public boolean isRicardoBald() {
      return this.isRicardoVariant() && (Boolean)this.field_70180_af.func_187225_a(RICARDO_BALD);
   }

   private void setRicardoBald(boolean bald) {
      this.field_70180_af.func_187227_b(RICARDO_BALD, bald);
   }

   @Override
   public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.func_184586_b(hand);
      if (!this.field_70170_p.field_72995_K
         && this.isRicardoVariant()
         && !this.isRicardoBald()
         && !stack.func_190926_b()
         && stack.func_77973_b() == Items.field_151097_aZ) {
         this.setRicardoBald(true);
         this.applyPermanentRicardoRage();
         this.grantRicardoShearAdvancement(player);
         stack.func_77972_a(1, player);
         this.field_70170_p
            .func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SRPSounds.NOGLA_HURT, SoundCategory.HOSTILE, 2.0F, 0.65F);
         this.field_70170_p
            .func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SRPSounds.NOGLA_GROWL, SoundCategory.HOSTILE, 2.5F, 0.75F);
         ((WorldServer)this.field_70170_p)
            .func_175739_a(EnumParticleTypes.SPELL_MOB, this.field_70165_t, this.field_70163_u + 1.2, this.field_70161_v, 40, 0.6, 0.8, 0.6, 0.05, new int[0]);
         this.field_70170_p.func_72960_a(this, (byte)77);
         return true;
      } else {
         return super.func_184645_a(player, hand);
      }
   }

   private void grantRicardoShearAdvancement(EntityPlayer player) {
      if (!this.field_70170_p.field_72995_K && player instanceof EntityPlayerMP) {
         EntityPlayerMP playerMP = (EntityPlayerMP)player;
         Advancement advancement = playerMP.func_71121_q().func_191952_z().func_192778_a(new ResourceLocation("srparasites", "tricked_me_did_you"));
         if (advancement != null) {
            AdvancementProgress progress = playerMP.func_192039_O().func_192747_a(advancement);
            if (!progress.func_192105_a()) {
               for (String criterion : progress.func_192107_d()) {
                  playerMP.func_192039_O().func_192750_a(advancement, criterion);
               }
            }
         }
      }
   }

   private void applyPermanentRicardoRage() {
      if (this.isRicardoBald()) {
         this.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 1200, 0, false, true));
      }
   }

   private void captureRicardoBaselines() {
      if (!this.ricardoBaseCaptured) {
         this.ricardoBaseMaxHealth = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
         this.ricardoBaseArmor = this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b();
         this.ricardoBaseToughness = this.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111125_b();
         this.ricardoBaseCaptured = true;
      }
   }

   private void updateRicardoAttributes() {
      this.captureRicardoBaselines();
      double baseSpeed = this.isRicardoVariant() ? 0.45 : 0.3;
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(baseSpeed);
      if (this.isRicardoVariant()) {
         double MAX_HP = 3763.0;
         double ARMOR_NORMAL = 32.0;
         double ARMOR_ENRAGE = 40.0;
         double SPEED_ENRAGE = 0.58;
         double ENRAGE_PCT = 0.25;
         double BERSERK_PCT = 0.1;
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3763.0);
         double cur = this.func_110143_aJ();
         double max = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
         if (max <= 0.0) {
            max = 3763.0;
         }

         double pct = max > 0.0 ? cur / max : 1.0;
         boolean enrage = pct <= 0.25;
         boolean berserk = pct <= 0.1;
         double armorNow = 32.0;
         double speedNow = baseSpeed;
         if (enrage) {
            armorNow = 40.0;
            speedNow = 0.58;
            this.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 60, 1, false, true));
            this.func_70690_d(new PotionEffect(MobEffects.field_76420_g, 60, 0, false, true));
            if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
               ((WorldServer)this.field_70170_p)
                  .func_175739_a(
                     EnumParticleTypes.CRIT_MAGIC, this.field_70165_t, this.field_70163_u + 1.2, this.field_70161_v, 12, 0.35, 0.6, 0.35, 0.02, new int[0]
                  );
            }
         }

         if (berserk) {
            armorNow = Math.max(armorNow, 44.0);
            speedNow = Math.max(speedNow, 0.62);
            this.func_70690_d(new PotionEffect(MobEffects.field_76420_g, 60, 1, false, true));
            this.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 60, 2, false, true));
         }

         this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(armorNow);
         this.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111128_a(this.ricardoBaseToughness);
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(speedNow);
         if (this.func_110143_aJ() > (float)max) {
            this.func_70606_j((float)max);
         } else if (this.func_110143_aJ() <= 0.0F) {
            this.func_70606_j((float)max);
         }
      } else {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.ricardoBaseMaxHealth);
         this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(this.ricardoBaseArmor);
         this.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111128_a(this.ricardoBaseToughness);
         if (this.func_110143_aJ() > (float)this.ricardoBaseMaxHealth) {
            this.func_70606_j((float)this.ricardoBaseMaxHealth);
         }
      }
   }

   public void func_96094_a(String name) {
      boolean wasRicardo = this.isRicardoVariant();
      super.func_96094_a(name);
      System.out.println("[SRP] noglaRicardoVariantEnabled=" + SRPConfigMobs.noglaRicardoVariantEnabled + " name=" + this.func_95999_t());
      if (!this.field_70170_p.field_72995_K) {
         if (!SRPConfigMobs.noglaRicardoVariantEnabled) {
            this.updateRicardoAttributes();
            return;
         }

         boolean isNowRicardo = this.isRicardoVariant();
         this.updateRicardoAttributes();
         if (isNowRicardo && !wasRicardo) {
            this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
            this.field_70170_p
               .func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187754_de, SoundCategory.HOSTILE, 8.0F, 1.0F);
            this.field_70170_p
               .func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187752_dd, SoundCategory.HOSTILE, 4.0F, 1.0F);
            ((WorldServer)this.field_70170_p)
               .func_175739_a(
                  EnumParticleTypes.CRIT_MAGIC, this.field_70165_t, this.field_70163_u + 1.2, this.field_70161_v, 40, 0.6, 0.8, 0.6, 0.1, new int[0]
               );
            this.field_70170_p.func_72960_a(this, (byte)77);
         }
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 1:
            this.charge();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void charge() {
      this.attacking++;
      this.miniCapA = true;
      if (this.attacking < 20) {
         this.field_70170_p.func_72960_a(this, (byte)100);
         if (this.attacking == 2) {
            float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F + 2.0F;
            this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0F, v);
         }

         EntityLivingBase entitylivingbase = this.func_70638_az();
         if (entitylivingbase == null
            || !this.field_70122_E
            || this.func_70090_H()
            || entitylivingbase.field_70163_u > this.field_70163_u && entitylivingbase.field_70122_E) {
            this.skillCharge = true;
            this.attacking = 0;
            this.miniCapA = false;
            this.setParasiteStatus(0);
            return;
         }

         if (!entitylivingbase.func_70089_S()) {
            this.skillCharge = true;
            this.attacking = 0;
            this.miniCapA = false;
            this.setParasiteStatus(0);
            return;
         }

         if (this.attacking <= 19) {
            double dis = this.func_70032_d(entitylivingbase);
            this.setParasiteStatus(3);
            this.func_70661_as().func_75499_g();
            this.targetX = this.field_70165_t + 15.0 * (entitylivingbase.field_70165_t - this.field_70165_t) / dis;
            this.targetY = this.field_70163_u + 15.0 * (entitylivingbase.field_70163_u - this.field_70163_u) / dis;
            this.targetZ = this.field_70161_v + 15.0 * (entitylivingbase.field_70161_v - this.field_70161_v) / dis;
         }
      }

      if (this.attacking == 20) {
         this.func_70661_as().func_75492_a(this.targetX, this.targetY, this.targetZ, 2.5);
      }

      if (this.attacking >= 20) {
         for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(2.0, 0.0, 2.0))) {
            if (mob != this && !(mob instanceof EntityParasiteBase)) {
               float f = (float)MathHelper.func_181159_b(mob.field_70161_v - this.field_70161_v, mob.field_70165_t - this.field_70165_t);
               EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, f, this, 1.0F, false, 0.5F);
               this.field_70170_p.func_72838_d(damage);
            }
         }
      }

      this.skillBreakBlocks();
      if (!this.field_70122_E) {
         this.field_70159_w *= 0.7;
         this.field_70179_y *= 0.7;
      }

      if (this.attacking >= 60 && this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
         this.attacking = 0;
         this.miniCapA = false;
         this.skillCharge = true;
         this.setParasiteStatus(2);
      }
   }
}
