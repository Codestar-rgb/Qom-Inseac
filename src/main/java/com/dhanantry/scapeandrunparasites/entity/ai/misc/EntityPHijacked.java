package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPHijacked extends EntityParasiteBase implements EntityCanSpawn {
   public EntityPHijacked(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4, new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, true, false, null, SRPConfig.hijackedSneakPen, SRPConfig.hijackedInviPen)
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
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.hijackedSneakPen,
                  SRPConfig.hijackedInviPen
               )
            );
      }

      this.field_70728_aV = SRPAttributes.XP_HIJACKED;
      this.damageCap = SRPConfig.hijackedCap;
      this.canD = SRPConfig.hijackeddespawn;
      this.MiniDamage = SRPConfig.hijackedMinDamage;
      this.oneMindDeathValue = SRPConfig.hijackedOneMindDeathV;
      this.foodSteal = 0.1F;
      this.canModRender = 0;
      this.valueEvDeath = SRPConfig.hijackedLoosingEPValue;
      this.cothSpread = SRPConfigSystems.cothHijacked;
      this.setScentHPMultiplier(1.75F);
   }

   @Override
   public int getIDSpawn() {
      return this.getParasiteIDRegister();
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   @Override
   protected void fearPlayer(EntityLivingBase player) {
      try {
         if (player == null) {
            return;
         }

         if (!this.func_70685_l(player)) {
            return;
         }

         if (!player.func_70644_a(SRPPotions.FEAR_E)) {
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, 300, 0, false, false));
         } else if (player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() < 0) {
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, 300, 0, false, false));
         }
      } catch (Exception var3) {
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      return super.func_70097_a(source, amount);
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
   }

   @Override
   protected void spawnGore() {
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void spawnEffectsGore() {
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
