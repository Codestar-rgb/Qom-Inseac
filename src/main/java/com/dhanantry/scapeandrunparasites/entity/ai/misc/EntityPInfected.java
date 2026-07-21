package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIInfectedSearch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPInfected extends EntityParasiteBase implements EntityCanSpawn {
   private String host;
   private int check;
   private int timer;
   protected boolean thisMelting;

   public EntityPInfected(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4, new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, true, false, null, SRPConfig.infectedSneakPen, SRPConfig.infectedInviPen)
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
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !(entity instanceof EntityVillager)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.infectedSneakPen,
                  SRPConfig.infectedInviPen
               )
            );
      }

      this.field_70728_aV = SRPAttributes.XP_INFECTED;
      this.damageCap = SRPConfig.infectedCap;
      this.canD = SRPConfig.infecteddespawn;
      this.MiniDamage = SRPConfig.infectedMinDamage;
      this.oneMindDeathValue = SRPConfig.infectedOneMindDeathV;
      this.foodSteal = 0.1F;
      this.cothSpread = SRPConfigSystems.cothInfected;
      this.check = 0;
      this.timer = 0;
      this.host = "";
      this.valueEvDeath = SRPConfig.infectedLoosingEPValue;
      this.thisMelting = false;
      this.setScentHPMultiplier(2.5F);
   }

   @Override
   public int getIDSpawn() {
      return this.getParasiteIDRegister();
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.srpTicks == 5 && !this.field_70170_p.field_72995_K) {
         if (ParasiteEventEntity.canSpawnNext) {
            if (this.killcount > SRPConfig.primitiveKills && this.getLevelCreated() >= SRPConfigSystems.deveMergeUse && this.thisMelting) {
               this.field_70714_bg.func_75776_a(3, new EntityAIInfectedSearch(this, 1.1));
               this.killcount = 0.0;
            }

            if (this.killcount > SRPConfig.feralKills) {
               EntityPFeral out = this.getFeral(this.field_70170_p);
               if (out != null) {
                  ParasiteEventEntity.spawnNext(this, out, true, false);
                  return;
               }
            }
         }

         if (this.host.length() != 0) {
            if (this.check == 0) {
               EntityLivingBase entityout = (EntityLivingBase)EntityList.func_188429_b(new ResourceLocation(this.host), this.field_70170_p);
               if (entityout != null && !(entityout instanceof EntityMob) && SRPConfig.infectedAssimilation) {
                  this.check = 2;
                  this.field_70170_p.func_72960_a(this, (byte)14);
               } else {
                  this.check = 1;
                  this.field_70170_p.func_72960_a(this, (byte)15);
               }
            }

            this.timer++;
            if (this.check >= 2 && this.timer > 5) {
               if (SRPConfigSystems.useEvolution) {
                  if (this.phaseCreated < SRPConfigSystems.evolutionAssimilatedDehiding) {
                     this.transform();
                  }
               } else {
                  this.transform();
               }
            }
         }

         this.InfectNearby(this, SRPConfigSystems.cothAura);
      }

      if (this.check >= 3 && this.field_70170_p.field_72995_K) {
         this.spawnParticles(SRPEnumParticle.GSPLASH, 0, 0, 0);
      }
   }

   @Override
   public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
      super.func_70624_b(entitylivingbaseIn);
      this.check = 0;
      this.field_70170_p.func_72960_a(this, (byte)17);
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

   private void transform() {
      if (this.func_70638_az() == null && !this.func_70644_a(SRPPotions.EPEL_E)) {
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0
            )
            .func_72314_b(10.0, 5.0, 10.0);
         List<EntityLivingBase> moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
         boolean flag = true;

         for (EntityLivingBase mob : moblist) {
            if (mob != this && !(mob instanceof EntityParasiteBase)) {
               if (mob instanceof EntityPlayer) {
                  EntityPlayer player = (EntityPlayer)mob;
                  if (player.func_184812_l_()) {
                     continue;
                  }
               }

               NBTTagCompound tags = mob.getEntityData();
               if (!tags.func_74764_b("srpcothimmunity") || tags.func_74762_e("srpcothimmunity") == 0) {
                  flag = false;
                  this.check = 2;
                  this.timer = 0;
                  this.field_70170_p.func_72960_a(this, (byte)14);
                  break;
               }
            }
         }

         if (flag) {
            this.check++;
            this.field_70170_p.func_72960_a(this, (byte)16);
            this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 60, 3, false, false));
            SRPMain.network
               .sendToAll(new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70130_N, this.field_70131_O, (byte)1));
            if (this.timer < 8) {
               return;
            }

            EntityLivingBase entityout = (EntityLivingBase)EntityList.func_188429_b(new ResourceLocation(this.host), this.field_70170_p);
            entityout.func_82149_j(this);
            this.particleStatus((byte)7);
            this.field_70170_p.func_72900_e(this);
            if (this.func_145818_k_()) {
               entityout.func_96094_a(this.func_95999_t());
               entityout.func_174805_g(this.func_174833_aM());
            }

            entityout.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 2, false, false));
            this.field_70170_p.func_72838_d(entityout);
            this.field_70170_p.func_180498_a((EntityPlayer)null, 1026, new BlockPos(entityout), 0);
         }
      } else {
         this.timer = -1;
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      if (entityIn instanceof EntityLivingBase) {
         EntityLivingBase target = (EntityLivingBase)entityIn;
         float before = target.func_110143_aJ();
         boolean hit = super.func_70652_k(entityIn);
         if (hit && !this.field_70170_p.field_72995_K) {
            float dealt = Math.max(0.0F, before - target.func_110143_aJ());
            if (dealt > 0.0F) {
               this.fearPlayer(target, dealt);
            }
         }

         return hit;
      } else {
         return super.func_70652_k(entityIn);
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      this.timer = -200;
      return super.func_70097_a(source, amount);
   }

   @Override
   protected void attackEntityFromEffects(int range, int count) {
      this.particleStatus((byte)51);
      double i1 = MathHelper.func_76128_c(this.field_70163_u + 0.1);
      double l1 = this.field_70165_t;
      double i2 = this.field_70161_v;
      int counttt = 0;

      for (int k2 = -1 * range; k2 <= 1 * range && SRPConfig.paraGore; k2++) {
         for (int l2 = -1 * range; l2 <= 1 * range; l2++) {
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
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.goreSim.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
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
      if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
         && (
            this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
               || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
         )) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.goreSim.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
         EntityRemain nnn = new EntityRemain(this.field_70170_p);
         nnn.func_70012_b(
            this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p() + 0.5, 0.0F, 0.0F
         );
         nnn.setParasite(EntityList.func_191301_a(this).toString());
         nnn.setSkin((byte)this.getSkin());
         nnn.setGoal(20 * SRPConfig.infectedRemainValue);
         this.field_70170_p.func_72838_d(nnn);
      }

      this.attackEntityFromCap(3);
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      this.particleStatus((byte)5);
      if (!this.field_70170_p.field_72995_K
         && this.killcount > SRPConfig.primitiveKills
         && this.getLevelCreated() >= SRPConfigSystems.deveMergeUse
         && this.thisMelting) {
         this.field_70714_bg.func_75776_a(3, new EntityAIInfectedSearch(this, 1.1));
         this.killcount = 0.0;
      }
   }

   private void InfectNearby(EntityLivingBase entity, int range) {
      if (SRPConfigSystems.cothAura != 0) {
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               entity.field_70165_t,
               entity.field_70163_u,
               entity.field_70161_v,
               entity.field_70165_t + 1.0,
               entity.field_70163_u + 1.0,
               entity.field_70161_v + 1.0
            )
            .func_186662_g(range);

         for (EntityLivingBase mob : entity.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (mob != entity && !mob.func_70644_a(SRPPotions.COTH_E) && !mob.func_70644_a(SRPPotions.EPEL_E)) {
               mob.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 4800, 0, false, false));
            }
         }
      }
   }

   public void setHost(String mobname) {
      this.host = mobname;
   }

   public String getHost() {
      return this.host;
   }

   @Override
   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
      Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
      if (wea == SRPItems.itemAssimilate && this.host.length() != 0 && hand == EnumHand.MAIN_HAND) {
         this.field_70170_p.func_72960_a(this, (byte)16);
         this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 60, 3, false, false));
         SRPMain.network
            .sendToAll(new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70130_N, this.field_70131_O, (byte)1));
         EntityLivingBase entityout = (EntityLivingBase)EntityList.func_188429_b(new ResourceLocation(this.host), this.field_70170_p);
         entityout.func_82149_j(this);
         this.particleStatus((byte)7);
         this.field_70170_p.func_72900_e(this);
         if (this.func_145818_k_()) {
            entityout.func_96094_a(this.func_95999_t());
            entityout.func_174805_g(this.func_174833_aM());
         }

         entityout.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 2, false, false));
         this.field_70170_p.func_72838_d(entityout);
         this.field_70170_p.func_180498_a((EntityPlayer)null, 1026, new BlockPos(entityout), 0);
         return true;
      } else {
         return super.func_184645_a(player, hand);
      }
   }

   public EntityPFeral getFeral(World in) {
      return null;
   }

   @Override
   public int canSpawnByIDData() {
      return 0;
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      if (!SRPConfigSystems.disloDeathHighVerions) {
         return false;
      } else {
         int goo = SRPSaveData.get(this.field_70170_p, 30).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 10);
         if (goo != 0 && this.field_70170_p.field_73012_v.nextDouble() < SRPConfigSystems.disloDeathHighVerionsChance) {
            EntityParasiteBase halo = ParasiteEventEntity.getRandomPrimitive(this.field_70170_p);
            if (goo >= SRPConfigSystems.disloDeathHighVerionsValue1) {
               halo = ParasiteEventEntity.getRandomAdapted(this.field_70170_p);
            }

            if (goo >= SRPConfigSystems.disloDeathHighVerionsValue2) {
               halo = ParasiteEventEntity.getRandomPure(this.field_70170_p);
            }

            halo.func_82149_j(this);
            halo.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(this)), null);
            this.field_70170_p.func_72838_d(halo);
            this.field_70170_p.func_180498_a(null, 1026, new BlockPos(halo), 0);
            halo.particleStatus((byte)7);
            halo.func_70690_d(new PotionEffect(SRPPotions.EPEL_E, 600, 0, false, false));
         }

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
      compound.func_74778_a("parasitehost", this.host);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
      if (compound.func_150297_b("parasitehost", 8)) {
         this.setHost(compound.func_74779_i("parasitehost").toString());
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 14) {
         this.check = 2;
      } else if (id == 15) {
         this.check = 1;
      } else if (id == 16) {
         this.check++;
      } else if (id == 17) {
         this.check = 0;
      } else {
         super.func_70103_a(id);
      }
   }
}
