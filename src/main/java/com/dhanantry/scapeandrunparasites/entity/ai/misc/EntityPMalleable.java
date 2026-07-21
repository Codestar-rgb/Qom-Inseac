package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbScary;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIBlockLight;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxB;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.ArrayList;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class EntityPMalleable extends EntityParasiteBase {
   protected static final DataParameter<Byte> HIT = EntityDataManager.func_187226_a(EntityPMalleable.class, DataSerializers.field_187191_a);
   private ArrayList<String> resistanceS;
   private ArrayList<Integer> resistanceI;
   protected int newDamageCooldown;
   protected float pointReduction;
   protected int pointCap;
   protected int DamageTypeCap;
   protected double chanceLearn;
   protected double chanceLearnFire;
   private int onFireA;
   private int onProj;
   protected float adaptationCap;
   protected float regen;
   protected int regenEff;
   protected int regenUse;
   private int stun;
   public boolean colonySpawned;
   protected int fuseOrb;
   protected int orbStartTimer;
   protected int orbItemCool;
   protected int orbVersionCooldown;
   protected float damageAmountReduced;
   protected boolean geneAdaptation = true;
   protected boolean geneBlocksearch = true;
   protected boolean geneResidue = true;
   protected boolean geneOrbbox = true;
   protected int limitOrb;
   protected int borderOrb;
   protected boolean skillOrb;

   public EntityPMalleable(World worldIn) {
      super(worldIn);
      this.resistanceS = new ArrayList<>();
      this.resistanceI = new ArrayList<>();
      this.pointReduction = 0.1F;
      this.pointCap = 10;
      this.DamageTypeCap = 5;
      this.chanceLearn = 0.5;
      this.chanceLearnFire = 0.0;
      this.adaptationCap = 1.0F;
      this.regen = 0.0F;
      this.regenUse = 1;
      this.regenEff = 1;
      this.newDamageCooldown = 0;
      this.foodRott = 0.0;
      this.foodRootNumber = 1;
      if (SRPConfig.canTargetBlock) {
         this.field_70714_bg.func_75776_a(7, new EntityAIBlockLight(this, 20, 5));
      }

      this.setScentHPMultiplier(2.5F);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(HIT, (byte)0);
   }

   @Override
   public boolean getGeneMod(int id) {
      switch (id) {
         case 6:
            return this.geneAdaptation;
         case 7:
            return this.geneBlocksearch;
         case 8:
            return this.geneResidue;
         case 9:
            return this.geneOrbbox;
         default:
            return super.getGeneMod(id);
      }
   }

   @Override
   public void applyGene(boolean[] kool, float[] goon) {
      if (SRPConfigSystems.generationUse) {
         this.geneMindam = kool[0];
         this.geneDamcap = kool[1];
         this.geneLookwall = kool[2];
         this.geneSprinting = kool[3];
         this.geneWaterleap = kool[4];
         this.geneSpecialmove = kool[5];
         this.geneAdaptation = kool[6];
         this.geneBlocksearch = kool[7];
         this.geneResidue = kool[8];
         this.geneOrbbox = kool[9];
         this.genePoisonHealing = goon[0];
         this.geneMobHealing = goon[1];
         this.geneAttackSpeed = goon[2];
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K) {
         if (this.srpTicks == 10) {
            if (this.func_70644_a(SRPPotions.RES_E)) {
               this.removeAllResistance(1);
            }

            if (this.orbVersionCooldown > 0) {
               this.orbVersionCooldown--;
            }

            if (!this.field_70128_L
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

         if (this.onFireA > 0) {
            this.onFireA--;
         }

         if (this.onProj > 0) {
            this.onProj--;
         }

         if (this.newDamageCooldown > 0) {
            this.newDamageCooldown--;
         }

         if (this.stun >= 0) {
            this.stun--;
            if (this.stun < 0 && this.getParasiteStatus() == 25) {
               this.setParasiteStatus(0);
            }
         }
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else {
         this.field_70180_af.func_187227_b(HIT, (byte)0);
         if (SRPConfigSystems.useOneMind
            && source != null
            && source.func_76346_g() instanceof EntityLivingBase
            && ParasiteEventEntity.alertOthers(this, (EntityLivingBase)source.func_76346_g(), this.field_70170_p, 7)) {
            SRPPotions.applySense(this, 400, this.func_70068_e(source.func_76346_g()), SRPConfigSystems.oneMinRangeCap);
         }

         if (source == DamageSource.field_76376_m && this.func_70644_a(MobEffects.field_76436_u) && amount == 1.0F) {
            this.func_70691_i(1.0F * this.genePoisonHealing);
            return false;
         } else if (source != DamageSource.field_76380_i && source != DamageSource.field_76368_d) {
            if ((source == DamageSource.field_76372_a || source == DamageSource.field_76370_b) && this.field_70146_Z.nextDouble() < this.chanceLearnFire) {
               this.onFireA = SRPConfig.fireTickWindow;
            }

            Entity immediate = source.func_76364_f();
            Entity trueSrc = source.func_76346_g();
            float times;
            if (immediate instanceof EntityLivingBase) {
               if (immediate instanceof EntityPlayer) {
                  ItemStack stack = ((EntityPlayer)immediate).func_184582_a(EntityEquipmentSlot.MAINHAND);
                  if (!stack.func_190926_b() && stack.func_77973_b() != null && stack.func_77973_b().getRegistryName() != null) {
                     times = this.hasResistance(stack.func_77973_b().getRegistryName().toString(), (byte)1);
                  } else {
                     times = this.hasResistance(source.func_76355_l(), (byte)2);
                  }
               } else {
                  ResourceLocation key = EntityList.func_191301_a(immediate);
                  if (key == null) {
                     times = this.hasResistance(source.func_76355_l(), (byte)2);
                  } else {
                     times = this.hasResistance(key.toString(), (byte)0);
                  }
               }
            } else if (trueSrc instanceof EntityLivingBase) {
               if (trueSrc instanceof EntityPlayer) {
                  ItemStack stack = ((EntityPlayer)trueSrc).func_184582_a(EntityEquipmentSlot.MAINHAND);
                  if (!stack.func_190926_b() && stack.func_77973_b() != null && stack.func_77973_b().getRegistryName() != null) {
                     times = this.hasResistance(stack.func_77973_b().getRegistryName().toString(), (byte)1);
                  } else {
                     times = this.hasResistance(source.func_76355_l(), (byte)2);
                  }
               } else {
                  ResourceLocation key = EntityList.func_191301_a(trueSrc);
                  if (key == null) {
                     times = this.hasResistance(source.func_76355_l(), (byte)2);
                  } else {
                     times = this.hasResistance(key.toString(), (byte)0);
                  }
               }
            } else {
               times = this.hasResistance(source.func_76355_l(), (byte)2);
            }

            float bonus = times * this.pointReduction * amount;
            float var9 = Math.max(amount - bonus * this.adaptationCap, 0.0F);
            this.damageAmountReduced = amount - var9;
            if (bonus != 0.0F && var9 != 0.0F) {
               this.particleStatus((byte)9);
               this.particleStatus((byte)9);
            }

            if (var9 == 0.0F) {
               this.particleStatus((byte)9);
               this.particleStatus((byte)9);
            }

            boolean flag = super.func_70097_a(source, var9);
            if (flag) {
               switch (this.getHitStatus()) {
                  case 1:
                     this.func_184185_a(
                        SRPSounds.ADAPTATION_P, this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F
                     );
                     return true;
                  case 2:
                     this.func_184185_a(
                        SRPSounds.ADAPTATION_F, this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F
                     );
                     return true;
                  case 3:
                     this.func_184185_a(
                        SRPSounds.CYST_EATING, this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F
                     );
                     return true;
               }
            }

            return flag;
         } else {
            return super.func_70097_a(source, amount);
         }
      }
   }

   @Override
   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
      if (hand == EnumHand.MAIN_HAND && !this.field_70170_p.field_72995_K) {
         Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
         if (wea != SRPItems.itembase) {
            return super.func_184645_a(player, hand);
         } else {
            StringBuilder out = new StringBuilder(
               "Current adaptation for " + Objects.requireNonNull(EntityList.func_191301_a(this)) + " (Damage type, points): "
            );

            for (int i = 0; i < this.resistanceS.size(); i++) {
               out.append("[").append(this.resistanceS.get(i)).append(", ").append(this.resistanceI.get(i)).append("] ");
            }

            player.func_145747_a(new TextComponentString(out.toString()));
            return true;
         }
      } else {
         return super.func_184645_a(player, hand);
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      return this.borderOrb > 0 ? false : super.func_70652_k(entityIn);
   }

   protected int hasResistance(String damage, byte type) {
      if (!this.geneAdaptation) {
         return 0;
      } else if (!this.func_70644_a(SRPPotions.RES_E) && !this.field_70729_aU) {
         if (this.checkList(damage, type)) {
            return 0;
         } else if (this.DamageTypeCap <= 0) {
            return 0;
         } else {
            if (this.field_70146_Z.nextDouble() < this.getChanceLearn()) {
               this.addResistance(damage);
            }

            for (int i = 0; i < this.resistanceS.size(); i++) {
               if (this.resistanceS.get(i).equals(damage)) {
                  int dama = this.resistanceI.get(i);
                  if (dama <= this.pointCap) {
                     this.field_70180_af.func_187227_b(HIT, (byte)1);
                  } else {
                     this.field_70180_af.func_187227_b(HIT, (byte)2);
                  }

                  return Math.min(dama, this.pointCap);
               }
            }

            return 0;
         }
      } else {
         return 0;
      }
   }

   private double getChanceLearn() {
      double bonus = 0.0;
      if (SRPConfig.adaptationDimStrong.length > 0) {
         for (int i = 0; i < SRPConfig.adaptationDimStrong.length; i++) {
            String[] here = SRPConfigSystems.evolutionDimStart[i].split(";");
            int dim = Integer.parseInt(here[0]);
            double b = Double.parseDouble(here[1]);
            if (dim == this.field_70170_p.field_73011_w.getDimension()) {
               bonus = b;
               break;
            }
         }
      }

      return this.chanceLearn + this.chanceLearn * bonus;
   }

   public void addResistance(String damage) {
      if (this.onFireA <= 0) {
         if (this.newDamageCooldown <= 0) {
            boolean flag = true;

            for (int i = 0; i < this.resistanceS.size(); i++) {
               if (this.resistanceS.get(i).equals(damage)) {
                  int iiii = this.resistanceI.get(i) + 1;
                  this.resistanceI.set(i, iiii);
                  flag = false;
                  if (iiii <= this.pointCap) {
                     this.particleStatus((byte)5);
                     this.particleStatus((byte)5);
                  }
                  break;
               }
            }

            if (flag) {
               if (this.resistanceS.size() >= this.DamageTypeCap) {
                  return;
               }

               this.resistanceS.add(damage);
               this.resistanceI.add(1);
               this.particleStatus((byte)5);
               this.particleStatus((byte)5);
            }

            this.newDamageCooldown = SRPConfig.adaptationNewDamageCooldon;
         }
      }
   }

   public void removeAllResistance(int value) {
      for (int i = 0; i < this.resistanceS.size(); i++) {
         int iiii = this.resistanceI.get(i) - value;
         if (iiii <= 0) {
            this.resistanceI.remove(i);
            this.resistanceS.remove(i);
            i--;
         } else {
            this.resistanceI.set(i, iiii);
         }
      }
   }

   public ArrayList<String> getResistanceS() {
      return this.resistanceS;
   }

   public ArrayList<Integer> getResistanceI() {
      return this.resistanceI;
   }

   public void copyResistancesFrom(EntityPMalleable in) {
      this.resistanceS = in.getResistanceS();
      this.resistanceI = in.getResistanceI();
   }

   public void increaseAllResistances() {
      this.resistanceI.replaceAll(integer -> integer + 1);
   }

   public void cutResistances(int in) {
      if (this.DamageTypeCap > 0) {
         this.stun += 40;
         this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 40, 100, false, false));
         this.setParasiteStatus(25);
         this.func_184185_a(SRPSounds.TENDRIL, 3.0F, 1.0F);
         if (SRPConfigSystems.rageEnable) {
            this.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 200, 1, false, false));
         }

         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, this, 100, 0);
         if (in % 2 != 0) {
            in++;
         }

         this.DamageTypeCap -= in;
         if (this.resistanceI.size() != this.resistanceS.size()) {
            this.resistanceI = new ArrayList<>();
            this.resistanceS = new ArrayList<>();
            this.DamageTypeCap = 0;
         } else {
            int size = this.resistanceS.size() - in;
            if (size <= 0) {
               this.resistanceI = new ArrayList<>();
               this.resistanceS = new ArrayList<>();
            } else {
               while (in <= 0) {
                  if (this.resistanceI.isEmpty()) {
                     this.DamageTypeCap = 0;
                     return;
                  }

                  this.resistanceI.remove(0);
                  this.resistanceS.remove(0);
                  in--;
               }
            }
         }
      }
   }

   public void increaseDamageCap(int in) {
      this.DamageTypeCap += in;
   }

   private boolean checkList(String damage, byte type) {
      switch (type) {
         case 0:
            if (ParasiteEventEntity.checkName(damage, SRPConfig.damageTypeBlackListMob, SRPConfig.damageTypeBlackListWhite)) {
               return true;
            }
         case 1:
            if (ParasiteEventEntity.checkName(damage, SRPConfig.damageTypeBlackListItem, SRPConfig.damageTypeBlackListWhite)) {
               return true;
            }
         case 2:
            if (ParasiteEventEntity.checkName(damage, SRPConfig.damageTypeBlackListElse, SRPConfig.damageTypeBlackListWhite)) {
               return true;
            }
         default:
            return false;
      }
   }

   private void outOfRange(DamageSource source) {
      if (this.func_70638_az() == null) {
         if (source.func_76346_g() != null) {
            Entity sss = source.func_76346_g();
            if (sss instanceof EntityPlayer) {
               EntityPlayer pl = (EntityPlayer)sss;
               if (pl.field_71075_bZ.field_75102_a || pl.func_175149_v()) {
                  return;
               }
            }

            if (this.func_70685_l(sss) && SRPPotions.applySense(this, 600, this.func_70068_e(sss), 50)) {
               this.func_70661_as().func_75492_a(sss.field_70165_t, sss.field_70163_u, sss.field_70161_v, 1.3);
            }
         } else if (this.func_70661_as().func_75505_d() == null) {
            Vec3d vec3d = RandomPositionGenerator.func_75461_b(this, 16, 7, new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v));
            if (vec3d != null && !this.func_70661_as().func_75492_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.3)) {
               vec3d = RandomPositionGenerator.func_75461_b(this, 16, 7, new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v));
               if (vec3d != null
                  && !this.func_70661_as().func_75492_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 1.3)
                  && this.field_70173_aa % 20 == 0) {
                  this.func_70661_as().func_75499_g();
                  double dd0 = vec3d.field_72450_a - this.field_70165_t;
                  double dd1 = vec3d.field_72449_c - this.field_70161_v;
                  float f = MathHelper.func_76133_a(dd0 * dd0 + dd1 * dd1);
                  this.field_70159_w = this.field_70159_w + (dd0 / f * 1.0 * 0.8F + this.field_70159_w * 0.2F);
                  this.field_70179_y = this.field_70179_y + (dd1 / f * 1.0 * 0.8F + this.field_70179_y * 0.2F);
                  this.field_70181_x = 0.15;
               }
            }
         }
      }
   }

   public byte getHitStatus() {
      return (Byte)this.field_70180_af.func_187225_a(HIT);
   }

   public void setOrbVersionCooldown(int in) {
      this.orbVersionCooldown = in;
   }

   public String getMostCommonDamage() {
      if (this.func_70644_a(SRPPotions.RES_E)) {
         return null;
      } else {
         String atm = null;
         int times = 0;

         for (int i = 0; i < this.resistanceS.size(); i++) {
            if (this.resistanceI.get(i) > times) {
               times = this.resistanceI.get(i);
               atm = this.resistanceS.get(i);
            }
         }

         return atm;
      }
   }

   public void removeCommonDamage(String damage, int times) {
      for (int i = 0; i < this.resistanceS.size(); i++) {
         if (this.resistanceS.get(i).equals(damage)) {
            int neww = this.resistanceI.get(i) - times;
            if (neww <= 0) {
               this.resistanceS.remove(i);
               this.resistanceI.remove(i);
               i--;
            } else {
               this.resistanceI.set(i, neww);
            }
         }
      }
   }

   public boolean scaryOrbEffect(EntityLivingBase in, int totalMobs) {
      if (in instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)in;
         if (player.field_71075_bZ.field_75098_d) {
            return false;
         }

         player.func_71020_j(this.foodSteal * SRPConfig.orbFoodMult);
         if (this.orbItemCool > 0) {
            CooldownTracker track = player.func_184811_cZ();

            for (int i = 0; i < player.field_71071_by.field_70462_a.size(); i++) {
               if (track.func_185143_a(((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_77973_b(), 1.0F) == 0.0F) {
                  track.func_185145_a(((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_77973_b(), this.orbItemCool);
               }
            }
         }

         this.attackEntityAsMobFood(player, false, this.foodRootNumber, this.foodRott);
      } else {
         if (in == this) {
            return false;
         }

         if (in instanceof EntityPCosmical && ((EntityPCosmical)in).getCloneC()) {
            return false;
         }
      }

      in.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 1200, 3, false, false));
      if (!(in instanceof EntityParasiteBase)) {
         this.attackEntityAsMobMinimum(in, this.getMiniDamage() * 0.5F);
      }

      SRPPotions.applyStackPotion(MobEffects.field_76436_u, in, 100, 0);
      return true;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      super.func_70645_a(cause);
      if (!this.field_70170_p.field_72995_K) {
         ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
         if (this.field_70170_p.field_73012_v.nextInt(3) == 0 && this.func_70027_ad() && this.canModRender == 1) {
            this.madeRng = 0;
            this.field_70170_p.func_72960_a(this, (byte)40);
         }
      }
   }

   @Override
   protected void selfExplode() {
      super.selfExplode();
      if (!this.field_70170_p.field_72995_K && this.func_70027_ad()) {
         EntityCruxB lol = new EntityCruxB(this.field_70170_p);
         lol.func_82149_j(this);
         this.field_70170_p.func_72838_d(lol);
      }
   }

   @Override
   protected void onDeathUpdateOG() {
      this.field_70725_aQ++;
      if (this.field_70725_aQ == 20) {
         if (!this.field_70170_p.field_72995_K
            && (this.func_70684_aJ() || this.field_70718_bc > 0 && this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot"))) {
            int i = this.func_70693_a(this.field_70717_bb);
            i = ForgeEventFactory.getExperienceDrop(this, this.field_70717_bb, i);

            while (i > 0) {
               int j = EntityXPOrb.func_70527_a(i);
               i -= j;
               this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
            }
         }

         this.func_70106_y();
         if (this.field_70170_p.field_72995_K) {
            for (int k = 0; k < 20; k++) {
               this.spawnParticles(SRPEnumParticle.GCLOUD, 127, 0, 0);
            }
         }
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
      compound.func_74757_a("paracolony", this.colonySpawned);
      if (this.resistanceS.size() == this.resistanceI.size()) {
         NBTTagList allResS = new NBTTagList();
         NBTTagList allResI = new NBTTagList();

         for (int i = 0; i < this.resistanceS.size(); i++) {
            String res = this.resistanceS.get(i);
            NBTTagCompound resT = new NBTTagCompound();
            resT.func_74778_a("resistance" + i, res);
            allResS.func_74742_a(resT);
            int resi = this.resistanceI.get(i);
            NBTTagCompound resU = new NBTTagCompound();
            resU.func_74768_a("resistance" + i, resi);
            allResI.func_74742_a(resU);
         }

         compound.func_74768_a("damagetypecap", this.DamageTypeCap);
         compound.func_74782_a("sprresistances", allResS);
         compound.func_74782_a("sprresistancei", allResI);
      }
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("paracolony", 99)) {
         this.colonySpawned = compound.func_74767_n("paracolony");
      }

      if (compound.func_150297_b("damagetypecap", 99)) {
         this.DamageTypeCap = compound.func_74762_e("damagetypecap");
      }

      if (compound.func_74764_b("sprresistances")) {
         NBTTagList allResS = compound.func_150295_c("sprresistances", 10);
         NBTTagList allResI = compound.func_150295_c("sprresistancei", 10);
         if (allResS.func_74745_c() != allResI.func_74745_c()) {
            return;
         }

         for (int i = 0; i < allResS.func_74745_c(); i++) {
            NBTTagCompound resT = allResS.func_150305_b(i);
            String res = resT.func_74779_i("resistance" + i);
            this.resistanceS.add(i, res);
            NBTTagCompound resU = allResI.func_150305_b(i);
            int resi = resU.func_74762_e("resistance" + i);
            this.resistanceI.add(i, resi);
         }
      }
   }

   @Override
   public boolean getFinished(byte attID) {
      switch (attID) {
         case 21:
            return this.skillOrb;
         default:
            return super.getFinished(attID);
      }
   }

   @Override
   public void setFinished(byte attID, boolean in) {
      switch (attID) {
         case 21:
            this.skillOrb = in;
            return;
         default:
            super.setFinished(attID, in);
      }
   }

   @Override
   public void doSpecialSkill(byte id) {
      switch (id) {
         case 21:
            this.scaryOrb();
            return;
         default:
            super.doSpecialSkill(id);
      }
   }

   private void scaryOrb() {
      if (!this.geneOrbbox) {
         this.skillOrb = true;
      } else if (this.borderOrb != -1 && (this.orbVersionCooldown <= 0 || this.borderOrb != 0)) {
         if (this.borderOrb == 0) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
               )
               .func_186662_g(this.fuseOrb / 2.0 + 1.0);

            for (EntityPMalleable mob : this.field_70170_p.func_72872_a(EntityPMalleable.class, axisalignedbb)) {
               if (mob != this && mob.getParasiteType() <= this.getParasiteType()) {
                  mob.setOrbVersionCooldown(4);
               }
            }
         }

         this.setParasiteStatus(9);
         this.func_70661_as().func_75499_g();
         if (this.field_70173_aa % 20 == 0) {
            this.borderOrb++;
            if (this.borderOrb == 1) {
               Entity ttt = new EntityOrbScary(this.field_70170_p, this, this.fuseOrb, this.orbStartTimer);
               ttt.func_82149_j(this);
               this.field_70170_p.func_72838_d(ttt);
               this.func_184185_a(SRPSounds.ORB_S, 1.0F, 1.0F);
            }

            if (this.borderOrb > 3) {
               this.skillOrb = true;
               this.setParasiteStatus(0);
               this.borderOrb = 0;
               this.limitOrb = 0;
            }
         }
      } else {
         this.skillOrb = true;
      }
   }
}
