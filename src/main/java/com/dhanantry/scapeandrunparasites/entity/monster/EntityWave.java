package com.dhanantry.scapeandrunparasites.entity.monster;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityWave extends EntityParasiteBase {
   private int raaa;
   private EntityLivingBase target;
   private int duration;

   public EntityWave(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.5F, 0.2F);
      this.field_70714_bg.func_85156_a(this.aiWander);
      this.field_70714_bg.func_85156_a(this.folow);
      this.killcount = -10.0;
      this.MiniDamage = 0.1F;
      this.duration = 1;
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.0, false));
   }

   @Override
   public void applyBonuses(SRPSaveData saveData, World world) {
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.45);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(20.0);
   }

   public void setDamages(double baseDamage, float min, int range, int durationF) {
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(baseDamage);
      this.MiniDamage = min;
      this.raaa = range;
      this.duration = durationF;
   }

   @Override
   public int getParasiteIDRegister() {
      return 211;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.field_70170_p.field_72995_K) {
         IBlockState state = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
         if (state.func_177230_c() != Blocks.field_150350_a) {
            int id = Block.func_176210_f(state);

            for (int i = 0; i < 15; i++) {
               this.field_70170_p
                  .func_175688_a(
                     EnumParticleTypes.BLOCK_CRACK,
                     this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
                     this.field_70163_u,
                     this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N,
                     this.field_70146_Z.nextGaussian() * 0.02,
                     this.field_70146_Z.nextGaussian() + 20.0,
                     this.field_70146_Z.nextGaussian() * 0.02,
                     new int[]{id}
                  );
            }
         }
      } else {
         if (this.target != null && !this.target.func_70089_S()) {
            this.func_70106_y();
            return;
         }

         if (this.field_70173_aa > 40) {
            if (this.field_70165_t == this.field_70169_q || this.field_70161_v == this.field_70166_s) {
               this.func_70106_y();
            }

            if (this.field_70173_aa > 20 * this.duration) {
               this.func_70106_y();
               return;
            }
         }

         if (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockLiquid) {
            this.func_70106_y();
            return;
         }

         float f = this.field_70130_N / 2.0F;
         float f1 = this.field_70131_O;
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.field_70165_t - f, this.field_70163_u, this.field_70161_v - f, this.field_70165_t + f, this.field_70163_u + f1, this.field_70161_v + f
            )
            .func_72314_b(0.4, 0.2, 0.4);

         for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (!(mob instanceof EntityParasiteBase)) {
               this.attackEntityAsMobMinimum(mob, this.MiniDamage);
            }
         }
      }
   }

   protected void func_70664_aZ() {
      this.func_70106_y();
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      return false;
   }

   @Override
   public boolean func_70687_e(PotionEffect potioneffectIn) {
      return false;
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      return false;
   }

   public AxisAlignedBB func_70046_E() {
      return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
   }

   @Override
   protected void func_82167_n(Entity entityIn) {
   }

   protected void func_85033_bc() {
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      if (!this.field_70170_p.field_72995_K) {
         if (this.target == entityLivingIn) {
            this.func_70106_y();
         }
      }
   }

   @Override
   public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
      if (this.field_70173_aa > 40) {
         this.func_70106_y();
      }

      super.func_70624_b(entitylivingbaseIn);
      this.target = entitylivingbaseIn;
   }
}
